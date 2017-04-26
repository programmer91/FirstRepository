/*
 * LeaveReportServiceImpl.java
 *
 * Created on October 15, 2008, 8:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.hr.leavereports;


import com.mss.mirage.util.ReportProperties;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.util.Calendar;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author miracle
 */
public class LeaveReportServiceImpl implements LeaveReportService{
    
    private int accId;
    private String format;
    
    private String status;
    private String sdate;
    private String edate;
    
    private int avalLeaves1 = 0;
    private int avalLeaves = 1;
    private int appliedLeaves = 0;
    private int usedLeaves = 0;
    private int days1 = 0;
    private int firDate = 0;
    private int firMonth = 0;
    private int firYear = 0;
    private int days2 = 0;
    private int secDate = 0;
    private int secMonth = 0;
    private int secYear = 0;
    private String reportResult;
    
    String leaveStatusReportSource = ReportProperties.getProperty("LeaveStatusReportsJRXML");
    String consolidatedLeaveReportSource = ReportProperties.getProperty("ConsolidatedLeaveReportJRXML");
    String avaliableLeaveReportSource = ReportProperties.getProperty("AvaliableLeaveReportsJRXML");
    String timeSheetsReport = ReportProperties.getProperty("TimeSheetsReportJRXML");
    String BdayreportsSource = ReportProperties.getProperty("BirthdayReportJRXML");
    
    JasperPrint jasperPrint = null;
    JasperReport jasperReport = null;
    ServletOutputStream outStream =null;
    Connection connection = null;
    Map RBMap = null;
    List finalList = null;
    String query =  null;
    /** Creates a new instance of Jasper */
    public LeaveReportServiceImpl() {
    }
    
    
    public String generateLeaveStatusReport(LeaveReportAction leaveReportAction,String empName,String query,HttpServletResponse httpServletResponse) throws ServiceLocatorException{
        
        RBMap=new HashMap();
        RBMap.put("sdate",leaveReportAction.getStartDate());
        RBMap.put("edate",leaveReportAction.getEndDate());
        RBMap.put("empName",empName);
        
        
        
        
        try {
            //System.out.println("FutureLeaves ::"+leaveReportAction.getFutureLeaves());
           // System.out.println("before compilation");
            if(leaveReportAction.getFutureLeaves().equals("true")) {
                jasperReport = JasperCompileManager.compileReport(consolidatedLeaveReportSource);
            }else {
                jasperReport = JasperCompileManager.compileReport(leaveStatusReportSource);
            }
            //System.out.println("after compilation");
            List dataList = getLeaveStatusData(query);
           // System.out.println("after get list compilation-->"+dataList.size());
            if(dataList.size()==0){
                //System.out.println("FutureLeaves ::"+dataList.size());
                reportResult = "No records are available between selected dates";
            }else {
                //System.out.println(usedLeaves+"%%%%%%%%"+appliedLeaves+"%%%%%%%%%%"+(avalLeaves-appliedLeaves));
                JRBeanCollectionDataSource jrxmlds = new JRBeanCollectionDataSource(getCollection(dataList));
                jasperPrint = JasperFillManager.fillReport(jasperReport, RBMap, jrxmlds);
                //JasperViewer.viewReport(jasperPrint);
                byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
                outStream = httpServletResponse.getOutputStream();
                httpServletResponse.setContentType("application/pdf");
                httpServletResponse.setContentLength(pdf.length);
                httpServletResponse.setHeader("Content-disposition","inline; filename=\"Report.pdf\"");
                outStream.write(pdf);
                outStream.flush();
                outStream.close();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex.getMessage());
        }
        return reportResult;
    }
    
    
    
    public Collection getCollection(Object obj){
        List list=(ArrayList)obj;
        return list;
    }
    
    //Getting Data from DataBase
    
    public List getLeaveStatusData(String query) throws ServiceLocatorException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List lt=null;
        HashMap map = null;
        finalList=new ArrayList();
        
        try {
            /* getting the connection from the ConnectionProvider */
            connection = ConnectionProvider.getInstance().getConnection();
            if(connection!=null) {
                preparedStatement = connection.prepareStatement(query);
                //System.err.println("Query---"+query);
                /* taking the info from the tblTimeSheets based on the empId and timeSheet Id */
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    map = new HashMap();
                    String ename      = resultSet.getString("Name");
                    Date sdate1       = resultSet.getDate("StartDate");
                    Date edate1       = resultSet.getDate("EndDate");
                    String leaveType  = resultSet.getString("leaveType");
                    String status     = resultSet.getString("status");
                    String reportsTO  = resultSet.getString("reportsTO");
                    String department = resultSet.getString("DepartmentId");
                    
                    map.put("Name",resultSet.getString("Name"));
                    map.put("StartDate",resultSet.getDate("StartDate"));
                    map.put("EndDate",resultSet.getDate("EndDate"));
                    map.put("leaveType",resultSet.getString("leaveType"));
                    map.put("Status",resultSet.getString("status"));
                    map.put("reportsTO",resultSet.getString("reportsTO"));
                    map.put("DepartmentId",resultSet.getString("DepartmentId"));
                    finalList.add(map);
                }
                
            }
        } catch(Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }
        }
        return finalList;
    }
    
    
    public List getBirthdayStatusData(String query) throws ServiceLocatorException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List lt=null;
        HashMap map = null;
        finalList=new ArrayList();
        
        try {
            /* getting the connection from the ConnectionProvider */
            connection = ConnectionProvider.getInstance().getConnection();
            if(connection!=null) {
                preparedStatement = connection.prepareStatement(query);
                //System.err.println("Query---"+query);
                /* taking the info from the tblTimeSheets based on the empId and timeSheet Id */
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    map = new HashMap();
                    String EmpName      = resultSet.getString("EmpName");
                    String departmentid  = resultSet.getString("departmentid");
                    String orgid     = resultSet.getString("orgid");
                    Date BirthDate  = resultSet.getDate("BirthDate");
                    Date AnniversaryDate = resultSet.getDate("AnniversaryDate");
                    String Email1 = resultSet.getString("Email1");
                    
                    map.put("EmpName",resultSet.getString("EmpName"));
                    map.put("departmentid",resultSet.getString("departmentid"));
                    map.put("orgid",resultSet.getString("orgid"));
                    map.put("BirthDate",resultSet.getDate("BirthDate"));
                    map.put("AnniversaryDate",resultSet.getDate("AnniversaryDate"));
                    map.put("Email1",resultSet.getString("Email1"));
                    finalList.add(map);
                }
                
            }
        } catch(Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle.getMessage());
            }
        }
        return finalList;
    }
    
    public String generateBirthdayReport(LeaveReportAction leaveReportAction,String empName,String query,HttpServletResponse httpServletResponse) throws ServiceLocatorException{
        
        RBMap=new HashMap();
       // RBMap.put("sdate",leaveReportAction.getStartDate());
       // RBMap.put("edate",leaveReportAction.getEndDate());
        RBMap.put("empName",empName);
        
        try {
            //System.out.println("FutureLeaves ::"+leaveReportAction.getFutureLeaves());
            
            jasperReport = JasperCompileManager.compileReport(BdayreportsSource);
            List dataList = getBirthdayStatusData(query);
            if(dataList.size()==0){
                //System.out.println("FutureLeaves ::"+dataList.size());
                reportResult = "No records are available between selected dates";
            }else {
                //System.out.println(usedLeaves+"%%%%%%%%"+appliedLeaves+"%%%%%%%%%%"+(avalLeaves-appliedLeaves));
                JRBeanCollectionDataSource jrxmlds = new JRBeanCollectionDataSource(getCollection(dataList));
                jasperPrint = JasperFillManager.fillReport(jasperReport, RBMap, jrxmlds);
                //JasperViewer.viewReport(jasperPrint);
                byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
                outStream = httpServletResponse.getOutputStream();
                httpServletResponse.setContentType("application/pdf");
                httpServletResponse.setContentLength(pdf.length);
                httpServletResponse.setHeader("Content-disposition","inline; filename=\"Report.pdf\"");
                outStream.write(pdf);
                outStream.flush();
                outStream.close();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex.getMessage());
        }
        return reportResult;
    }
    
    public void generateAvaliableLeaveReport(String empId,String empName,String sdate, String edate,HttpServletResponse httpServletResponse) throws ServiceLocatorException {
        //String reportSource = "D:/ProjectFiles/Reports/Jrxml/Employee/Availability/EmployeeAvaliableLeaves.jrxml";
        RBMap=new HashMap();
        RBMap.put("sdate",sdate);
        RBMap.put("edate",edate);
        RBMap.put("empName",empName);
        try {
            jasperReport = JasperCompileManager.compileReport(avaliableLeaveReportSource);
            List dataList = getAvaliableLeaveData(empId,empName,sdate,edate);
            
            
            JRBeanCollectionDataSource jrxmlds = new JRBeanCollectionDataSource(getCollection(dataList));
            jasperPrint = JasperFillManager.fillReport(jasperReport, RBMap, jrxmlds);
            //JasperViewer.viewReport(jasperPrint);
            byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
            outStream = httpServletResponse.getOutputStream();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setContentLength(pdf.length);
            httpServletResponse.setHeader("Content-disposition","inline; filename=\"Report.pdf\"");
            outStream.write(pdf);
            outStream.flush();
            outStream.close();
        } catch (Exception ex) {
           throw new ServiceLocatorException(ex.getMessage());
        }
    }
    
    public List getAvaliableLeaveData(String empId,String empName,String sdate,String edate) {
        
        Statement statement = null;
        ResultSet resultSet = null;
        List lt=null;
        HashMap map = null;
        int tempAppliedLeaves [] = new int[12];
        finalList=new ArrayList();
        try {
            
            /* getting the connection from the ConnectionProvider */
            connection = ConnectionProvider.getInstance().getConnection();
            if(connection!=null) {
                
                String query = "SELECT  concat(tblEmployee.FName,'',tblEmployee.MName,'.',tblEmployee.LName) as Name,tblEmpLeaves.StartDate,"+
                        "tblEmpLeaves.EndDate FROM tblEmpLeaves join tblEmployee on(tblEmployee.Id=tblEmpLeaves.EmpId and tblEmployee.CurStatus  = 'active')"+
                        " where tblEmpLeaves.StartDate > '"+sdate+"%' and tblEmpLeaves.EndDate < '"+edate+"%' and tblEmpLeaves.status='approved' and tblEmpLeaves.EmpId like '"+empId+"' order by startDate";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                int index = 0;
                while(resultSet.next()) {
                    
                    map = new HashMap();
                    String ename      = resultSet.getString("Name");
                    Date sdate1       = resultSet.getDate("StartDate");
                    Date edate1       = resultSet.getDate("EndDate");
                    String sdateArr[] = sdate1.toString().split("-");
                    String edateArr[] = edate1.toString().split("-");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(sdate1);
                    int startDate = Integer.parseInt(sdateArr[2]);
                    int startMonth = Integer.parseInt(sdateArr[1]);
                    int startYear = Integer.parseInt(sdateArr[0]);
                    int lastDateOfStartMonth = cal.getActualMaximum(Calendar.DATE);
                    
                    cal.setTime(edate1);
                    int endDate = Integer.parseInt(edateArr[2]);
                    int endMonth = Integer.parseInt(edateArr[1]);
                    int endYear = Integer.parseInt(edateArr[0]);
                    int lastDateOfEndMonth = cal.getActualMaximum(Calendar.DATE);
                    
                    if(startYear==endYear){
                        if(startMonth==endMonth) {
                            if(startDate==endDate) {
                                tempAppliedLeaves[startMonth-1] = tempAppliedLeaves[startMonth-1]+1;
                            } else {
                                tempAppliedLeaves[startMonth-1] = tempAppliedLeaves[startMonth-1] + ((endDate - startDate)+1);
                            }
                        } else {
                            tempAppliedLeaves[startMonth-1] = tempAppliedLeaves[startMonth-1] + (lastDateOfStartMonth-startDate)+1;
                            tempAppliedLeaves[endMonth-1] = tempAppliedLeaves[endMonth-1] + endDate;
                        }
                    } else{
                        tempAppliedLeaves[startMonth-1] = endDate;
                    }
                    
                } //end of while loop
                for(int i=0;i<tempAppliedLeaves.length;i++){
                    usedLeaves = usedLeaves + tempAppliedLeaves[i];;
                }
                avalLeaves =  getAvalLeaves(tempAppliedLeaves);
                map.put("EmpName",empName);
                map.put("AvalLeaves",avalLeaves);
                map.put("UsedLeaves",usedLeaves);
                finalList.add(map);
            }
        } catch(Exception e) { e.printStackTrace(); }
        finally{
            try{
                if(resultSet!=null){
                   resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                   statement.close();
                    statement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
               // throw new ServiceLocatorException(sqle);
                System.err.println("in Leave ReportsServioceImpl --->"+sqle.getMessage());
            }
        }
        
        return finalList;
    } //end of method getData()
    
    
    //Getting avaliableLeaves from DataBase
    
    public int getAvalLeaves(int tempAppliedLeaves[]) {
        //int avalLeaves = 1;
        int inum=0;
        for(int index=0;index<tempAppliedLeaves.length;index++) {
            
            if(tempAppliedLeaves[index]==0 || avalLeaves==0 ){
                avalLeaves =avalLeaves + inum;
            } else{
                avalLeaves =avalLeaves + inum;
            }
            if(avalLeaves >= tempAppliedLeaves[index]) {
                avalLeaves = (avalLeaves)-tempAppliedLeaves[index];
            } else {
                tempAppliedLeaves[index] = avalLeaves;
                avalLeaves = 0;
            }
            inum=1;
            
        } //end of for loop
        return avalLeaves;
    } //end of method avalLeaves()
    
    public String getTimeSheetPDFReport(String empName,String query,String timeSheetWeekStartDate,String timeSheetWeekEndDate,HttpServletResponse httpServletResponse) throws ServiceLocatorException {
        
        Statement statement = null;
        ResultSet resultSet = null;
        HashMap map = null;
        List timeSheetList = new ArrayList();
        
        RBMap=new HashMap();
        RBMap.put("sdate",timeSheetWeekStartDate);
        RBMap.put("edate",timeSheetWeekEndDate);
        RBMap.put("empName",empName);
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if(connection!=null) {
                
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                
                while(resultSet.next()) {
                    map = new HashMap();
                    String ename      = resultSet.getString("empName");
                    String department = resultSet.getString("DepartmentId");
                    String reportsTO  = resultSet.getString("reportsTO");
                    //System.out.println("ename----->"+ename);
                    
                    map.put("EmployeeName",resultSet.getString("empName"));
                    map.put("DepartmentID",resultSet.getString("DepartmentId"));
                    map.put("ReportsTO",resultSet.getString("reportsTO"));
                    timeSheetList.add(map);
                }
            }
            if(timeSheetList.size()==0){
                //  System.out.println("FutureLeaves ::"+timeSheetList.size());
                reportResult = "No records are available between selected dates";
            }else {
                jasperReport = JasperCompileManager.compileReport(timeSheetsReport);
                JRBeanCollectionDataSource jrxmlds = new JRBeanCollectionDataSource(getCollection(timeSheetList));
                jasperPrint = JasperFillManager.fillReport(jasperReport, RBMap, jrxmlds);
                //JasperViewer.viewReport(jasperPrint);
                byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
                outStream = httpServletResponse.getOutputStream();
                httpServletResponse.setContentType("application/pdf");
                httpServletResponse.setContentLength(pdf.length);
                httpServletResponse.setHeader("Content-disposition","inline; filename=\"Report.pdf\"");
                outStream.write(pdf);
                outStream.flush();
                outStream.close();
            }
        } catch(Exception ex) {
            throw new ServiceLocatorException(ex.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle.getMessage());
            }
        }
        
        
        return reportResult;
    }
}
