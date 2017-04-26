/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.timesheets;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.apache.poi.hssf.record.formula.functions.Cell;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

//start


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.util.HSSFColor;
/**
 *
 * @author miracle
 */
public class NewCustTimeSheetDownLoadAction implements
        Action, ServletRequestAware, ServletResponseAware{
    // private String URL="/images/flower.GIF";
    private String contentDisposition = "FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String fileName;
    private String downloadType;
    private String sheetType;
    //new
    private String fname="";
    private String lname="";
    private String country;
    private String leaveType;
    private String startdate;
    private String enddate;
    private String reportsToId;
    private String status;
    private int teamType;
     public String execute() throws Exception {
        return null;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public void setServletResponse(HttpServletResponse httpServletResponse) {

        String responseString = "";
        try {

            String fileLocation = "";
            //For creating Excel grind from Search result Grid
           // System.out.println("StartDate" + getStartdate());
          //  System.out.println("EndDate" + getEnddate());
            fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus(),getTeamType());

            httpServletResponse.setContentType("application/force-download");
            // File file = new File(Properties.getProperty("mscvp.docCreationPath")+"SearchedExcelDocument.xls");
         //   System.out.println("fileLocation-->"+fileLocation);
            File file = new File(fileLocation);
            Date date = new Date();

            //fileName = (date.getYear() + 1900) + "_" + (date.getMonth() + 1) + "_" + date.getDate() + "_" + file.getName();
           // fileName = getStartdate().substring(0,10) +"To"+ getEnddate().substring(0,10) +"_"+getLeaveType()+"_"+file.getName();
             fileName = file.getName();
            if (file.exists()) {
                inputStream = new FileInputStream(file);
                outputStream = httpServletResponse.getOutputStream();
                httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                int noOfBytesRead = 0;
                byte[] byteArray = null;
                while (true) {
                    byteArray = new byte[1024];
                    noOfBytesRead = inputStream.read(byteArray);
                    if (noOfBytesRead == -1) {
                        break;
                    }
                    outputStream.write(byteArray, 0, noOfBytesRead);
                }
                responseString = "downLoaded!!";
                 
              //  httpServletResponse.setContentType(getDownloadType());
               // httpServletResponse.getWriter().write(responseString);
               
            } else {
                throw new FileNotFoundException("File not found");
            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(NewCustTimeSheetDownLoadAction.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }/*catch (ServiceLocatorException ex) {
        ex.printStackTrace();
        }*/ finally {
            try {

                inputStream.close();
                outputStream.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }
  /*
     * Method for Excel Format Logistics DocRepository Download
     */
     static double TotalP1Hrs = 0;
   static double TotalP2Hrs = 0;
    static double TotalP3Hrs = 0;
   static double TotalP4Hrs = 0;
   static double TotalP5Hrs = 0;
   static double ToatalVacationHrs = 0;
   static double TotalHolidayHrs = 0;
   static double TotalComptimeHrs = 0;
    int primaryProjectId = 0;
    boolean isDualReqired = false;
    public String generateEmpTimesheetList(String sDate, String eDate, String reportsToId,String status,int teamType) {
      //  System.out.println("sDate-->"+sDate);
     //   System.out.println("eDate-->"+eDate);
       // System.out.println("reportsToId-->"+reportsToId);
     //   System.out.println("status-->"+status);
        
        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb=null;
        Connection connection = null;

        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;

        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt = null, preStmtTemp = null;

        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString = "";
        Statement statement = null;

        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet = null;
        String timeSheetStatus = "";
         HashMap map = null;
          HashMap map1 = null;
       List finalList=new ArrayList();
        try {
            File file = new File(Properties.getProperty("Cust.Timesheet.Path"));
            
            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "CustomerTimesheetList.xls");
            filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "CustomerTimesheetList.xls";
            //String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            connection = ConnectionProvider.getInstance().getConnection();
            //System.out.println("after connection-->");
            /*preStmt = connection.prepareStatement("SELECT tblEcertValidatorKeys.VKey AS Vkey,tblEcertTopics.TopicName AS NAME, " +
            "tblEcertValidatorKeys.Duration AS Duration,tblEcertValidatorKeys.MinMarks AS minmarks," +
            "tblEcertValidatorKeys.NoOfQuestions AS COUNT FROM " +
            "tblEcertValidatorKeys LEFT OUTER JOIN tblEcertTopics ON " +
            "(tblEcertTopics.ID=tblEcertValidatorKeys.TopicId)" +
            "  WHERE tblEcertValidatorKeys.STATUS='Active' AND tblEcertValidatorKeys.TopicId = ? AND tblEcertValidatorKeys.CreatedBy='"+userId+"'");*/
          //  String startDate = dateutility.convertStringToMySQLDate(sDate);
            //String endDate = dateutility.convertStringToMySQLDate(eDate);
            
            if(status!=null){
                if(status.equalsIgnoreCase("2"))
                   timeSheetStatus =  "Submitted";
                else if(status.equalsIgnoreCase("3"))
                    timeSheetStatus =  "Approved";
                else if(status.equalsIgnoreCase("1"))
                    timeSheetStatus =  "Entered";
                else if (status.equalsIgnoreCase("4"))
                     timeSheetStatus = "Not Entered";
                
            }
            String query = null;
            String accountId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ACC_ID).toString();
           // String accountId1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_PROJECT_ID).toString();
            
            if(timeSheetStatus.equalsIgnoreCase("Not Entered")){
                
             /*   query = "SELECT ResourceName ,ObjectId, Email FROM tblProjectContacts "
                        + "WHERE AccountId = "+accountId+" AND ObjectId NOT IN (SELECT DISTINCT(EmpId) FROM tblTimeSheets "
                        + "WHERE DateStart >='"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"' "
                        + "AND DateEnd <='"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"' AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+")) AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") AND tblProjectContacts.Status='Active' ";          */
               // Map empMap = DataSourceDataProvider.getInstance().getEmployeesByProjectId(projectId);
               String empList = DataSourceDataProvider.getInstance().getEmpIdsByProjectIds(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()).toString();
              /*  query = "SELECT ResourceName ,ObjectId, Email FROM tblProjectContacts "
                        + "WHERE AccountId = "+accountId+" AND ObjectId NOT IN (SELECT DISTINCT(EmpId) FROM tblTimeSheets "
                        + "WHERE DateStart >='"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"' "
                        + "AND DateEnd <='"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"' AND EmpId IN("+empList.substring(1, empList.length()-1) +"))  AND tblProjectContacts.Status='Active' ";          
               */
                query ="SELECT DISTINCT ResourceName ,ObjectId, Email FROM tblProjectContacts WHERE accountid="+accountId+" AND objectid NOT IN("
+"SELECT Empid FROM tblTimeSheets WHERE  DATE(DateStart) BETWEEN '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"'  AND '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"') "
+"AND tblProjectContacts.STATUS='Active'";
            }else{
                /*
              query = "SELECT ResourceName as empname,"
                + "tblTimeSheetLines.Prj1Hrs,tblTimeSheetLines.Prj2Hrs,tblTimeSheetLines.VacationHrs,tblTimeSheetLines.HolidayHrs,"
                + "tblTimeSheetLines.WorkDate,tblLKTimeSheetStatusType.Description"
                + " FROM tblTimeSheets,tblTimeSheetLines,tblLKTimeSheetStatusType,tblProjectContacts "
                + "WHERE tblProjectContacts.ObjectId=tblTimeSheets.EmpId "
                + "AND tblTimeSheetLines.EmpId=? AND DATE(workDate) BETWEEN DATE('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"') "
                + "AND DATE('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"') AND tblTimeSheets.TimeSheetStatusTypeId=tblLKTimeSheetStatusType.Id "
                + "AND tblTimeSheets.EmpId = ? AND DateStart>='"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"' AND DateEnd<='"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"' "
                + "and tblTimeSheets.TimeSheetStatusTypeId=? and tblTimeSheets.TimeSheetId = tblTimeSheetLines.TimeSheetId and tblTimeSheets.ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+")"
                + "GROUP BY WorkDate ";*/
                
             /*   query = "SELECT ResourceName as empname,"
                + "tblTimeSheetLines.Prj1Hrs,tblTimeSheetLines.Prj2Hrs,tblTimeSheetLines.Prj3Hrs,tblTimeSheetLines.Prj4Hrs,tblTimeSheetLines.Prj5Hrs,tblTimeSheetLines.PrjId1,tblTimeSheetLines.PrjId2,tblTimeSheetLines.PrjId3,tblTimeSheetLines.PrjId4,tblTimeSheetLines.PrjId5,tblTimeSheetLines.VacationHrs,tblTimeSheetLines.HolidayHrs,tblTimeSheetLines.CmtHrs,"
                + "tblTimeSheetLines.WorkDate,tblLKTimeSheetStatusType.Description"
                + " FROM tblTimeSheets,tblTimeSheetLines,tblLKTimeSheetStatusType,tblProjectContacts "
                + "WHERE tblProjectContacts.ObjectId=tblTimeSheets.EmpId "
                + "AND tblTimeSheetLines.EmpId=? AND DATE(workDate) BETWEEN DATE('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"') "
                + "AND DATE('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"') AND tblTimeSheets.TimeSheetStatusTypeId=tblLKTimeSheetStatusType.Id "
                + "AND tblTimeSheets.EmpId = ? AND DateStart>='"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"' AND DateEnd<='"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"' "
                + "and tblTimeSheets.TimeSheetStatusTypeId=? and tblTimeSheets.TimeSheetId = tblTimeSheetLines.TimeSheetId "
                + "GROUP BY WorkDate ";*/
                 query = "SELECT ResourceName as empname,"
                + "tblTimeSheetLines.Prj1Hrs,tblTimeSheetLines.Prj2Hrs,tblTimeSheetLines.Prj3Hrs,tblTimeSheetLines.Prj4Hrs,tblTimeSheetLines.Prj5Hrs,tblTimeSheetLines.PrjId1,tblTimeSheetLines.PrjId2,tblTimeSheetLines.PrjId3,tblTimeSheetLines.PrjId4,tblTimeSheetLines.PrjId5,tblTimeSheetLines.VacationHrs,tblTimeSheetLines.HolidayHrs,tblTimeSheetLines.CmtHrs,"
                + "tblTimeSheetLines.WorkDate,tblStatus1.Description as PriDescription,tblStatus2.Description as SecDescription,tblTimeSheets.ProjectId "
                + " FROM tblTimeSheets,tblTimeSheetLines,tblLKTimeSheetStatusType tblStatus1,tblLKTimeSheetStatusType tblStatus2,tblProjectContacts "
                + "WHERE tblProjectContacts.ObjectId=tblTimeSheets.EmpId "
                + "AND tblTimeSheetLines.EmpId=? AND DATE(workDate) BETWEEN DATE('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"') "
                + "AND DATE('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"') AND tblTimeSheets.TimeSheetStatusTypeId=tblStatus1.Id AND tblTimeSheets.SecondReportsToStatusTypeId=tblStatus2.Id "
                + "AND tblTimeSheets.EmpId = ? AND DateStart>='"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(sDate))+"' AND DateEnd<='"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(eDate))+"' ";
                
                       if(teamType==1)  {
                    query = query+ "and tblTimeSheets.TimeSheetStatusTypeId=? and tblTimeSheets.TimeSheetId = tblTimeSheetLines.TimeSheetId ";
                       }           else if(teamType==2){
                             query = query+ "and tblTimeSheets.SecondReportsToStatusTypeId=? and tblTimeSheets.TimeSheetId = tblTimeSheetLines.TimeSheetId ";
                
                       }
                       else {
                             query = query+ "and (tblTimeSheets.TimeSheetStatusTypeId=? OR tblTimeSheets.SecondReportsToStatusTypeId=?) and tblTimeSheets.TimeSheetId = tblTimeSheetLines.TimeSheetId ";
                
                       }
                       
                       query = query+ "GROUP BY WorkDate ";
            }
         
            
            
            //System.out.println("queryggg-->"+query);
            
              String projectAssoIds = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString();
              String reportToName ="";
              List teamList =null;
              List projectIdsList = (List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS);
              if(!"-1".equals(reportsToId)) //{
               reportToName = DataSourceDataProvider.getInstance().getCustomerNameByContactsId(Integer.parseInt(reportsToId));
              if(teamType==1)
                teamList = DataSourceDataProvider.getInstance().getCustomerTeamList(Integer.parseInt(reportsToId),projectIdsList);
              else if(teamType==2) 
                 teamList = DataSourceDataProvider.getInstance().getCustomerSecondaryTeamList(Integer.parseInt(reportsToId),projectIdsList); 
              else
                  teamList = DataSourceDataProvider.getInstance().getCustomerAllTeamList(Integer.parseInt(reportsToId),projectIdsList); 
                  
             // }else {
                 // teamList = DataSourceDataProvider.getInstance().getCustomerTeamList(Integer.parseInt(reportsToId));
             // }
           // resultSet = preStmt.executeQuery();
         //       System.out.println("teamList-->"+teamList);
                  int j=1;
                  preStmt = connection.prepareStatement(query);
                if(timeSheetStatus.equalsIgnoreCase("Not Entered")){
              
               // System.out.println("teamList-->"+teamList);
                preStmt = connection.prepareStatement(query);
                resultSet = preStmt.executeQuery();
                
                    
                        while(resultSet.next()){
                           String EmpName      = resultSet.getString("ResourceName");
                    String Email      = resultSet.getString("Email");
                    int reportsTo = resultSet.getInt("ObjectId");
                    String reportsToName = DataSourceDataProvider.getInstance().getReportsNameByContactId(reportsTo,projectAssoIds);
                    
                    
                    // String Description      = timeSheetStatus;
              map = new HashMap();
                 //   System.out.println("innn_->"+EmpName+" -"+Description+" "+WorkDate+" "+P1Hrs+" "+P2Hrs);
                    map.put("SNO",String.valueOf(j));
                    map.put("ResourceName",EmpName);
                    map.put("Email",Email);
                    map.put("ReportsName",reportsToName);
                finalList.add(map);
                j++;
                        }
                     
                    }
                else{
                for(int i=0;i<teamList.size();i++) {
                    if("-1".equals(reportsToId))
                    reportToName = DataSourceDataProvider.getInstance().getReportsNameByContactId(Integer.parseInt(teamList.get(i).toString()),projectAssoIds);
                    else
                        reportToName = DataSourceDataProvider.getInstance().getCustomerNameByContactsId(Integer.parseInt(reportsToId));
                   // System.out.println("System.-->"+reportToName);
                   // System.out.println("i-->"+Integer.parseInt(teamList.get(i).toString()));
                
                        if(timeSheetStatus.equalsIgnoreCase("Not Entered")){}else{
                  // System.out.print("empid-->"+Integer.parseInt(teamList.get(i).toString()));
                    preStmt.setInt(1, Integer.parseInt(teamList.get(i).toString()));
                     preStmt.setInt(2, Integer.parseInt(teamList.get(i).toString()));
                     preStmt.setInt(3, Integer.parseInt(status));
                     if(teamType==0){
                          preStmt.setInt(4, Integer.parseInt(status));
                     }
                        }
               // System.err.println("Query---"+query);
                /* taking the info from the tblTimeSheets based on the empId and timeSheet Id */
                resultSet = preStmt.executeQuery();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                while(resultSet.next()) {
                    map1 = new HashMap();
                   
                   // else{
                    String EmpName = resultSet.getString("EMPNAME");
                   
                     //String Description      = timeSheetStatus;
                    String Description      = resultSet.getString("PriDescription");;
                     String secDescription = resultSet.getString("SecDescription");
              
                    Date WorkDate = resultSet.getDate("WorkDate");
                    String P1Hrs      = resultSet.getString("Prj1Hrs");
                    String P2Hrs      = resultSet.getString("Prj2Hrs");
                      String P3Hrs      = resultSet.getString("Prj3Hrs");
                    String P4Hrs      = resultSet.getString("Prj4Hrs");
                    String P5Hrs      = resultSet.getString("Prj5Hrs");
                    String VacationHrs = resultSet.getString("VacationHrs");
                    String HolidayHrs = resultSet.getString("HolidayHrs");
                     String ComptimeHrs = resultSet.getString("CmtHrs");
                      primaryProjectId = resultSet.getInt("ProjectId");
                      isDualReqired = DataSourceDataProvider.getInstance().isDualReportsRequired(primaryProjectId);
                     String proj1Name = "--";
                    if(resultSet.getInt("PrjId1")!=0)
                        proj1Name = DataSourceDataProvider.getInstance().getProjectName(resultSet.getInt("PrjId1"));
                    
                     String proj2Name = "--";
                    if(resultSet.getInt("PrjId2")!=0)
                        proj2Name = DataSourceDataProvider.getInstance().getProjectName(resultSet.getInt("PrjId2"));
                    
                     String proj3Name = "--";
                    if(resultSet.getInt("PrjId3")!=0)
                        proj3Name = DataSourceDataProvider.getInstance().getProjectName(resultSet.getInt("PrjId3"));
                    
                     String proj4Name = "--";
                    if(resultSet.getInt("PrjId4")!=0)
                        proj4Name = DataSourceDataProvider.getInstance().getProjectName(resultSet.getInt("PrjId4"));
                    
                     String proj5Name = "--";
                    if(resultSet.getInt("PrjId5")!=0)
                        proj5Name = DataSourceDataProvider.getInstance().getProjectName(resultSet.getInt("PrjId5"));
                    
                    
                    TotalP1Hrs = TotalP1Hrs + resultSet.getDouble("Prj1Hrs");
                    TotalP2Hrs = TotalP2Hrs + resultSet.getDouble("Prj2Hrs");
                     TotalP3Hrs = TotalP3Hrs + resultSet.getDouble("Prj3Hrs");
                    TotalP4Hrs = TotalP4Hrs + resultSet.getDouble("Prj4Hrs");
                    TotalP5Hrs = TotalP5Hrs + resultSet.getDouble("Prj5Hrs");
                    ToatalVacationHrs = ToatalVacationHrs + resultSet.getDouble("VacationHrs");
                    TotalHolidayHrs = TotalHolidayHrs + resultSet.getDouble("HolidayHrs");
                     TotalComptimeHrs = TotalComptimeHrs + resultSet.getDouble("CmtHrs");
                 //   System.out.println("innn_->"+EmpName+" -"+Description+" "+WorkDate+" "+P1Hrs+" "+P2Hrs);
                    map1.put("SNO",String.valueOf(j));
                    map1.put("ResourceName",EmpName);
                    map1.put("Status",Description);
                     map1.put("WorkedDate", sdf.format(WorkDate));
                    //map.put("WorkedDate",WorkDate);
                    map1.put("Prj1Hrs",P1Hrs);
                    map1.put("Prj2Hrs",P2Hrs);
                      map1.put("Prj3Hrs",P3Hrs);
                    map1.put("Prj4Hrs",P4Hrs);
                    map1.put("Prj5Hrs",P5Hrs);
                    
                    map1.put("Prj1Name",proj1Name);
                    map1.put("Prj2Name",proj2Name);
                    map1.put("Prj3Name",proj3Name);
                    map1.put("Prj4Name",proj4Name);
                    map1.put("Prj5Name",proj5Name);
                    map1.put("ReportingTo",reportToName);
                    
                  map1.put("Description",Description);
                  map1.put("VacationHrs",VacationHrs);
                  map1.put("HolidayHrs",HolidayHrs);
                     map1.put("ComptimeHrs",ComptimeHrs);
                   //  if(isDualReqired)
                      if(isDualReqired && !"Entered".equals(secDescription))
                     map1.put("SecDescription",secDescription);
                     else
                         map1.put("SecDescription","");
                    finalList.add(map1);
                    j++;
                }
                 
                }
        }
            HSSFWorkbook hssfworkbook = new HSSFWorkbook();
            HSSFSheet sheet = hssfworkbook.createSheet("Employee Timesheets");

            HSSFCellStyle cs = hssfworkbook.createCellStyle();
            HSSFCellStyle headercs = hssfworkbook.createCellStyle();
            headercs.setFillForegroundColor(HSSFColor.BLACK.index);
        headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headercs.setBorderTop((short) 1); // single line border
        headercs.setBorderBottom((short) 1); // single line border
        
        HSSFFont timesBoldFont = hssfworkbook.createFont();
    timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    timesBoldFont.setColor(HSSFColor.WHITE.index);
    timesBoldFont.setFontName("Arial");
    headercs.setFont(timesBoldFont);
        
        HSSFFont footerFont = hssfworkbook.createFont();
        footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        footerFont.setFontName("Arial");
        
         HSSFCellStyle footercs = hssfworkbook.createCellStyle();
         footercs.setFont(footerFont);
            //footercs.setFillForegroundColor(HSSFColor.GREEN.index);
        //footercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
       // footercs.setBorderTop((short) 1); // single line border
        //footercs.setBorderBottom((short) 1); // single line border
        
        
            HSSFDataFormat df = hssfworkbook.createDataFormat();
             if(timeSheetStatus.equalsIgnoreCase("Not Entered")){
                 
                 
                 HSSFRow row = sheet.createRow((short) 0);
            HSSFCell cell = row.createCell((short) 0);
            // HSSFRow row1 = sheet.createRow((short)0);
            HSSFCell cell1 = row.createCell((short) 1);
             
            HSSFCell cell2 = row.createCell((short) 2);
            HSSFCell cell3 = row.createCell((short) 3);
            
           
            cell.setCellValue("SNO");
            cell1.setCellValue("EmpName");
            cell2.setCellValue("Email");
            cell3.setCellValue("ReportsTo");
           
           // cell7.setCellValue("Leaves");

            cell.setCellStyle(headercs);
            cell1.setCellStyle(headercs);
            cell2.setCellStyle(headercs);
              cell3.setCellStyle(headercs);
          
            int count = 1;
         //   while (resultSet.next()) {
            if(finalList.size()>0 ){
                Map timesheetMap = null;
        for(int i=0;i<finalList.size();i++) {
                timesheetMap = (Map)finalList.get(i);
                row = sheet.createRow((short) count++);
                cell = row.createCell((short) 0);
                //  HSSFRow row2 = sheet.createRow((short)0);
                cell1 = row.createCell((short) 1);
                cell2 = row.createCell((short) 2);
                cell3 = row.createCell((short) 3);
                
                
            
                cell.setCellValue((String)timesheetMap.get("SNO"));
                cell1.setCellValue((String)timesheetMap.get("ResourceName"));
                cell2.setCellValue((String)timesheetMap.get("Email"));
              
                cell3.setCellValue((String)timesheetMap.get("ReportsName"));
               
                cell.setCellStyle(cs);
                cell1.setCellStyle(cs);
                cell2.setCellStyle(cs);
            cell3.setCellStyle(cs);
                
}
row = sheet.createRow((short) count++);
                cell = row.createCell((short) 0);
                //  HSSFRow row2 = sheet.createRow((short)0);
                cell1 = row.createCell((short) 1);
                cell2 = row.createCell((short) 2);
                cell3 = row.createCell((short) 3);
                
                cell.setCellValue("");
                cell1.setCellValue("This Report is from");
                cell2.setCellValue(sDate +" ");
                cell3.setCellValue("to "+eDate);
                 


                cell.setCellStyle(footercs);
                cell1.setCellStyle(footercs);
                cell2.setCellStyle(footercs);
                cell3.setCellStyle(footercs);
                
                
            }
            sheet.autoSizeColumn((short) 0);
            sheet.autoSizeColumn((short) 1);
            sheet.autoSizeColumn((short) 2);
            sheet.autoSizeColumn((short) 3);
                 
             }
            else{
            HSSFRow row = sheet.createRow((short) 0);
            HSSFCell cell = row.createCell((short) 0);
            // HSSFRow row1 = sheet.createRow((short)0);
            HSSFCell cell1 = row.createCell((short) 1);
            HSSFCell cell2 = row.createCell((short) 2);
            HSSFCell cell3 = row.createCell((short) 3);
            HSSFCell cell4 = row.createCell((short) 4);
            HSSFCell cell5 = row.createCell((short) 5);
            HSSFCell cell6 = row.createCell((short) 6);
          HSSFCell cell7 = row.createCell((short) 7);
           HSSFCell cell8 = row.createCell((short) 8);
            HSSFCell cell9 = row.createCell((short) 9);
           HSSFCell cell10 = row.createCell((short) 10);
           HSSFCell cell11 = row.createCell((short) 11);
           HSSFCell cell12 = row.createCell((short) 12);
           HSSFCell cell13 = row.createCell((short) 13);
           HSSFCell cell14 = row.createCell((short) 14);
           HSSFCell cell15 = row.createCell((short) 15);
           HSSFCell cell16 = row.createCell((short) 16);
           HSSFCell cell17 = row.createCell((short) 17);
           HSSFCell cell18 = row.createCell((short) 18);
    cell.setCellValue("SNO");
            cell1.setCellValue("EmpName");
            cell2.setCellValue("ReportsTo");
            cell3.setCellValue("Date");
            cell4.setCellValue("Proj1Name");
            cell5.setCellValue("P(N)Hrs");
            cell6.setCellValue("Proj2Name");
            cell7.setCellValue("P(N+1)Hrs");
            cell8.setCellValue("Proj3Name");
            cell9.setCellValue("P3Hrs");
            cell10.setCellValue("Proj4Name");
            cell11.setCellValue("P4Hrs");
            cell12.setCellValue("Proj5Name");
            cell13.setCellValue("P5Hrs");
            cell14.setCellValue("VacationHrs");
            cell15.setCellValue("HolidayHrs");
            cell16.setCellValue("ComptimeHrs");
            cell17.setCellValue("Status");
            cell18.setCellValue("SecondaryStatus");
           // cell7.setCellValue("Leaves");

            cell.setCellStyle(headercs);
            cell1.setCellStyle(headercs);
            cell2.setCellStyle(headercs);
            cell3.setCellStyle(headercs);
            cell4.setCellStyle(headercs);
            cell5.setCellStyle(headercs);
            cell6.setCellStyle(headercs);
            cell7.setCellStyle(headercs);
             cell8.setCellStyle(headercs);
              cell9.setCellStyle(headercs);
             cell10.setCellStyle(headercs);
             cell11.setCellStyle(headercs);
             cell12.setCellStyle(headercs);
             cell13.setCellStyle(headercs);
             cell14.setCellStyle(headercs);
             cell15.setCellStyle(headercs);
             cell16.setCellStyle(headercs);
             cell17.setCellStyle(headercs);
             cell18.setCellStyle(headercs);
            int count = 1;
         //   while (resultSet.next()) {
            if(finalList.size()>0 ){
                Map timesheetMap = null;
        for(int i=0;i<finalList.size();i++) {
                timesheetMap = (Map)finalList.get(i);
                row = sheet.createRow((short) count++);
                cell = row.createCell((short) 0);
                //  HSSFRow row2 = sheet.createRow((short)0);
                cell1 = row.createCell((short) 1);
                cell2 = row.createCell((short) 2);
                cell3 = row.createCell((short) 3);
                cell4 = row.createCell((short) 4);
                cell5 = row.createCell((short) 5);
                cell6 = row.createCell((short) 6);
                cell7 = row.createCell((short) 7);
                 cell8 = row.createCell((short) 8);
                  cell9 = row.createCell((short) 9);
                cell10 = row.createCell((short) 10);
                cell11 = row.createCell((short) 11);
                cell12 = row.createCell((short) 12);
                cell13 = row.createCell((short) 13);
                cell14 = row.createCell((short) 14);
                cell15 = row.createCell((short) 15);
                cell16 = row.createCell((short) 16);
                cell17 = row.createCell((short) 17);
                cell18 = row.createCell((short) 18);
            
                cell.setCellValue((String)timesheetMap.get("SNO"));
                cell1.setCellValue((String)timesheetMap.get("ResourceName"));
                cell2.setCellValue((String)timesheetMap.get("ReportingTo"));
                cell3.setCellValue((String)timesheetMap.get("WorkedDate"));
               cell4.setCellValue((String)timesheetMap.get("Prj1Name"));
                cell5.setCellValue((String)timesheetMap.get("Prj1Hrs"));
                cell6.setCellValue((String)timesheetMap.get("Prj2Name"));
                cell7.setCellValue((String)timesheetMap.get("Prj2Hrs"));
                cell8.setCellValue((String)timesheetMap.get("Prj3Name"));
                cell9.setCellValue((String)timesheetMap.get("Prj3Hrs"));
                cell10.setCellValue((String)timesheetMap.get("Prj4Name"));
                cell11.setCellValue((String)timesheetMap.get("Prj4Hrs"));
                cell12.setCellValue((String)timesheetMap.get("Prj5Name"));
                cell13.setCellValue((String)timesheetMap.get("Prj5Hrs"));
                cell14.setCellValue((String)timesheetMap.get("VacationHrs"));
                cell15.setCellValue((String)timesheetMap.get("HolidayHrs"));
                cell16.setCellValue((String)timesheetMap.get("ComptimeHrs"));
                cell17.setCellValue((String)timesheetMap.get("Description"));
              
                cell18.setCellValue((String)timesheetMap.get("SecDescription"));
               
               
                cell.setCellStyle(cs);
                cell1.setCellStyle(cs);
                cell2.setCellStyle(cs);
                cell3.setCellStyle(cs);
                cell4.setCellStyle(cs);
                cell5.setCellStyle(cs);
                cell6.setCellStyle(cs);
                cell7.setCellStyle(cs);
                  cell8.setCellStyle(cs);
                 cell9.setCellStyle(cs);
                  cell10.setCellStyle(cs);
                  cell11.setCellStyle(cs);
                  cell12.setCellStyle(cs);
                  cell13.setCellStyle(cs);
                  cell14.setCellStyle(cs);
                  cell15.setCellStyle(cs);
                  cell16.setCellStyle(cs);
                  cell17.setCellStyle(cs);
                  cell18.setCellStyle(cs);
}
                row = sheet.createRow((short) count++);
                cell = row.createCell((short) 0);
                //  HSSFRow row2 = sheet.createRow((short)0);
                cell1 = row.createCell((short) 1);
                cell2 = row.createCell((short) 2);
                cell3 = row.createCell((short) 3);
                cell4 = row.createCell((short) 4);
                cell5 = row.createCell((short) 5);
                cell6 = row.createCell((short) 6);
                cell7 = row.createCell((short) 7);
                 cell8 = row.createCell((short) 8);
                cell9 = row.createCell((short) 9);
                 cell10 = row.createCell((short) 10);
                 cell11 = row.createCell((short) 11);
                 cell12 = row.createCell((short) 12);
                 cell13 = row.createCell((short) 13);
                 cell14 = row.createCell((short) 14);
                 cell15 = row.createCell((short) 15);
                 cell16 = row.createCell((short) 16);
                 cell17 = row.createCell((short) 17);
                 cell18 = row.createCell((short) 18);
                 
                 
                 
                cell.setCellValue("");
                cell1.setCellValue("");
                cell2.setCellValue("");
                cell3.setCellValue("Total : ");
                cell4.setCellValue("");
                cell5.setCellValue(String.valueOf(TotalP1Hrs));
                cell6.setCellValue("");
                cell7.setCellValue(String.valueOf(TotalP2Hrs));
                cell8.setCellValue("");
                cell9.setCellValue(String.valueOf(TotalP3Hrs));
                cell10.setCellValue("");
                cell11.setCellValue(String.valueOf(TotalP4Hrs));
                cell12.setCellValue("");
                cell13.setCellValue(String.valueOf(TotalP5Hrs));
                cell14.setCellValue(String.valueOf(ToatalVacationHrs));
                cell15.setCellValue(String.valueOf(TotalHolidayHrs));
                cell16.setCellValue(String.valueOf(TotalComptimeHrs));
                 cell17.setCellValue("");
                 cell18.setCellValue("");
                 


                cell.setCellStyle(footercs);
                cell1.setCellStyle(footercs);
                cell2.setCellStyle(footercs);
                cell3.setCellStyle(footercs);
                cell4.setCellStyle(footercs);
                cell5.setCellStyle(footercs);
                cell6.setCellStyle(footercs);
                cell7.setCellStyle(footercs);
                  cell8.setCellStyle(footercs);
                  cell9.setCellStyle(footercs);
                  cell10.setCellStyle(footercs);
                  cell11.setCellStyle(footercs);
                  cell12.setCellStyle(footercs);
                  cell13.setCellStyle(footercs);
                  cell14.setCellStyle(footercs);
                  cell15.setCellStyle(footercs);
                  cell16.setCellStyle(footercs);
                  cell17.setCellStyle(footercs);
                  cell18.setCellStyle(footercs);
                
            }
            sheet.autoSizeColumn((short) 0);
            sheet.autoSizeColumn((short) 1);
            sheet.autoSizeColumn((short) 2);
            sheet.autoSizeColumn((short) 3);
            sheet.autoSizeColumn((short) 4);
            sheet.autoSizeColumn((short) 5);
            sheet.autoSizeColumn((short) 6);
            sheet.autoSizeColumn((short) 7);
            sheet.autoSizeColumn((short) 8);
            sheet.autoSizeColumn((short) 9);
            sheet.autoSizeColumn((short) 10);
            sheet.autoSizeColumn((short) 11);
            sheet.autoSizeColumn((short) 12);
            sheet.autoSizeColumn((short) 13);
            sheet.autoSizeColumn((short) 14);
            sheet.autoSizeColumn((short) 15);
            sheet.autoSizeColumn((short) 16);
            sheet.autoSizeColumn((short) 17);
            sheet.autoSizeColumn((short) 18);
            }
            hssfworkbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            




        } catch (FileNotFoundException fne) {
         //   System.out.println("FileNotFoundException-->"+fne.getMessage());
           fne.printStackTrace();
        } catch (IOException ioe) {
          //  System.out.println("IOException-->"+ioe.getMessage());
           ioe.printStackTrace();
        } catch (Exception ex) {
           ex.printStackTrace();
             //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {
            TotalP1Hrs = 0;
            TotalP2Hrs = 0; 
             TotalP3Hrs = 0; 
            TotalP4Hrs = 0; 
            TotalP5Hrs = 0; 
            ToatalVacationHrs = 0;
            TotalHolidayHrs = 0;
              TotalComptimeHrs = 0;
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return filePath;
    }



    
    public String getDownloadType() {
        return downloadType;
    }

    /**
     * @param downloadType the downloadType to set
     */
    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    /**
     * @return the sheetType
     */
    public String getSheetType() {
        return sheetType;
    }

    /**
     * @param sheetType the sheetType to set
     */
    public void setSheetType(String sheetType) {
        this.sheetType = sheetType;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the leaveType
     */
    public String getLeaveType() {
        return leaveType;
    }

    /**
     * @param leaveType the leaveType to set
     */
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    /**
     * @return the startdate
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * @param startdate the startdate to set
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    /**
     * @return the enddate
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * @param enddate the enddate to set
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    /**
     * @return the reportsToId
     */
    public String getReportsToId() {
        return reportsToId;
    }

    /**
     * @param reportsToId the reportsToId to set
     */
    public void setReportsToId(String reportsToId) {
        this.reportsToId = reportsToId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the teamType
     */
    public int getTeamType() {
        return teamType;
    }

    /**
     * @param teamType the teamType to set
     */
    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }
 
}
