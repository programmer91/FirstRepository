/*
 * DownloadPayRoll.java
 *
 * Created on October 1, 2012, 10:39 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.mss.mirage.employee.payroll;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocatorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ReportProperties;
import com.opensymphony.xwork2.Action;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import java.io.OutputStream;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class DownloadExcelPayrollReport implements
        Action, ServletRequestAware, ServletResponseAware {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private InputStream fileInputStream;
    private String file;
    private String path;
    private String locationAvailable;
    private String attachmentLocation;
    private String departmentId;
    private String empnameById;
    private int month;
    private String year;
    private String fileName;
    public InputStream inputStream;
    public OutputStream outputStream;
    private String resultMessage;
    private String result;
    private String country;
    private String bankName;
    private String orgName;
    private int tefId;
    private int payRollId;
    private int empId;
    private String tempFlag;
    private String reportFor;
    private int doRepaymentFlag;
    private int bankReportFlag;
private String bankReportsResponse;
 private String empNo;
 private int noOfDays;
 private int yearOverlay;
                         private int monthOverlay;
                         private int orgId;
                   private int noOfWeekendDays;
                  private String paymentDateEmp;
                    private int noOfWorkingDays;
                    private String fromTef;
    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    @Override
     public String execute() throws Exception {
        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            //For creating Excel grind from Search result Grid
            // System.out.println("StartDate" + getStartdate());
            //  System.out.println("EndDate" + getEnddate());
            // fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus());
            setDepartmentId(getDepartmentId());
            setEmpnameById(getEmpnameById());
            setYear(getYear());
            setMonth(getMonth());
            setEmpNo(getEmpNo());
            int empId =0;
            int temp = 0;
              
          if( !"".equals(getEmpnameById()) && !"".equals(getEmpNo()) ){
                      
                      empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(httpServletRequest.getParameter("empnameById").toString());
                      int empIdbyNo = DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(getEmpNo()));
                    
                      if(empIdbyNo != empId){
                        temp = 1;
                     }
                }
                  
                      
                 else {
                     if(!"".equals(getEmpnameById())  ){
//                      System.out.println("getEmpnameById"+getEmpnameById());
                  
                empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(httpServletRequest.getParameter("empnameById").toString());
//                  System.out.println("emoName empId----->"+empId);
                }
                else if( !"".equals(getEmpNo()) ){
                 empId = DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(getEmpNo()));
//                 System.out.println("empId----->"+empId);
                }
                 }
              // System.out.println("temp"+temp);
             
                
                
                 
            
          //  System.out.println("fileLocation-------->" + getMonth() + "----------" + getYear() + "-------" + getDepartmentId() + "=======" + getEmpnameById());
            if(temp!=1){
               fileLocation = generatePayrollExcellList(empId, getYear(), getMonth());
            }
                
        //    System.out.println("fileLocation-------->" + fileLocation);
            if (!"".equals(fileLocation)) {
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
                    // responseString = "downLoaded!!";

                    //  httpServletResponse.setContentType(getDownloadType());
                    // httpServletResponse.getWriter().write(responseString);

                } else {
                    throw new FileNotFoundException("File not found");
                }

                inputStream.close();
                outputStream.close();

            } else {
                 if(temp == 1){
                  responseString = "<font style='color:red;font-size:15px;'>Employee No and name Doesn't match</font>";
                }
                 else{
                responseString = "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>";
                 }
                setBankReportsResponse(responseString);
                setBankReportFlag(2);
               // setResultMessage("No records exists !!");
               // httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                result = INPUT;

            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }/*catch (ServiceLocatorException ex) {
         ex.printStackTrace();
         } finally {
         try {
        
         inputStream.close();
         outputStream.close();
        
         } catch (IOException ex) {
         ex.printStackTrace();
         }
         }*/
        return result;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {

        this.httpServletResponse = httpServletResponse;

    }
    /*
     * Method for Excel Format Leaves Count download
     */

    public String generateLeavesCountReport() throws Exception {
        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            setDepartmentId(getDepartmentId());
            setEmpnameById(getEmpnameById());
            setYear(getYear());
            setMonth(getMonth());
            setCountry(getCountry());
          //  System.out.println(getMonth() + "-----" + getYear() + "-------" + getDepartmentId() + "--------" + getEmpnameById() + "-------" + getCountry());
            fileLocation = generateLeavesCountList(getDepartmentId(), getEmpnameById(), getYear(), getMonth(), getCountry());
        //    System.out.println("fileLocation-------->" + fileLocation);
            if (!"".equals(fileLocation)) {
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
                    // responseString = "downLoaded!!";

                    //  httpServletResponse.setContentType(getDownloadType());
                    // httpServletResponse.getWriter().write(responseString);

                } else {
                    throw new FileNotFoundException("File not found");
                }

                inputStream.close();
                outputStream.close();

            } else {
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                result = INPUT;

            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    /*
     Method for biomteric vacations report download
     * 
     */

    public String generateBiometricReport() throws Exception {
        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            setDepartmentId(getDepartmentId());
            setEmpnameById(getEmpnameById());
            setYear(getYear());
            setMonth(getMonth());

       //     System.out.println(getMonth() + "-----" + getYear() + "-------" + getDepartmentId() + "--------" + getEmpnameById());
            fileLocation = generateBiometricReportList(getDepartmentId(), getEmpnameById(), getYear(), getMonth());
         //   System.out.println("fileLocation-------->" + fileLocation);
            if (!"".equals(fileLocation)) {
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
                    // responseString = "downLoaded!!";

                    //  httpServletResponse.setContentType(getDownloadType());
                    // httpServletResponse.getWriter().write(responseString);

                } else {
                    throw new FileNotFoundException("File not found");
                }

                inputStream.close();
                outputStream.close();

            } else {
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                result = INPUT;

            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /*public String getBankReport() {
    
     result = SUCCESS;
     String responseString = "";
     try {
     httpServletRequest.getSession(false).removeAttribute("resultMessage");
     String fileLocation = "";
     //For creating Excel grind from Search result Grid
     // System.out.println("StartDate" + getStartdate());
     //  System.out.println("EndDate" + getEnddate());
     // fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus());
     //setDepartmentId(getDepartmentId());
     //setEmpnameById(getEmpnameById());
     setYear(getYear());
     setMonth(getMonth());
     getBankName();
     getOrgName();
     System.out.println("getBankName-->" + getBankName() + "getOrgName-->" + getOrgName());
    
     fileLocation = generateBankReportsheetList(getBankName(), getOrgName(), getYear(), getMonth());
     System.out.println("fileLocation-------->" + fileLocation);
     if (!"".equals(fileLocation)) {
     httpServletResponse.setContentType("application/force-download");
     File file = new File(fileLocation);
     Date date = new Date();
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
    
     } else {
     throw new FileNotFoundException("File not found");
     }
    
     inputStream.close();
     outputStream.close();
    
     } else {
     setResultMessage("No records exists !!");
     httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
     result = INPUT;
    
     }
     } catch (FileNotFoundException ex) {
     try {
     httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
     } catch (IOException ex1) {
     Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
     }
     } catch (IOException ex) {
     ex.printStackTrace();
     }
     return result;
     }
     */
    public String getBankReport() {

        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            //For creating Excel grind from Search result Grid
            // System.out.println("StartDate" + getStartdate());
            //  System.out.println("EndDate" + getEnddate());
            // fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus());
            //setDepartmentId(getDepartmentId());
            //setEmpnameById(getEmpnameById());
            setYear(getYear());
            setMonth(getMonth());
            getBankName();
            getOrgName();
            //System.out.println("getBankName-->" + getBankName() + "getOrgName-->" + getOrgName());

            fileLocation = generateBankReportsheetList(getBankName(), getOrgName(), getYear(), getMonth());
         //   System.out.println("fileLocation-------->" + fileLocation);
            if (!"".equals(fileLocation)) {
                httpServletResponse.setContentType("application/force-download");
                File file = new File(fileLocation);
                Date date = new Date();
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

                } else {
                    throw new FileNotFoundException("File not found");
                }

                inputStream.close();
                outputStream.close();

            } else {
                setResultMessage("No records exists !!");
               // httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                String resultMessage = "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>";
              //  httpServletRequest.getSession(false).setAttribute("bankReportsResponse",resultMessage);
              setBankReportsResponse(resultMessage);
                     setBankReportFlag(1);
                     
                   //  System.out.println(resultMessage+"---->setBankRepotFlag"+getBankReportFlag());
                    
                result = INPUT;

            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /*public String generateBankReportsheetList(String bankName, String orgId, String year, int month) {
    
    
     DateUtility dateutility = new DateUtility();
     String filePath = "";
     StringBuffer sb = null;
     Connection connection = null;
    
    
     CallableStatement callableStatement = null;
    
    
     PreparedStatement preStmt = null, preStmtTemp = null;
    
    
     String queryString = "";
     Statement statement = null;
    
    
     ResultSet resultSet = null;
     String timeSheetStatus = "";
     HashMap map = null;
     HashMap map1 = null;
     List finalList = new ArrayList();
     try {
    
    
     File file = new File(Properties.getProperty("Payroll.Report.Path"));
    
     if (!file.exists()) {
     file.mkdirs();
     }
     System.out.println("test");
     FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "BankReport.xls");
    
     connection = ConnectionProvider.getInstance().getConnection();
     String query = null;
     String orgName = "";
    
     System.out.println("orgName-->" + DataSourceDataProvider.getInstance().getOrgNameById(Integer.parseInt(orgId)));
     System.out.println("bankName-->" + bankName);
     orgName = DataSourceDataProvider.getInstance().getOrgNameById(Integer.parseInt(orgId));
    
     if(orgName.equals("Chikiniki Enterprises(India) Pvt. Ltd")){
     query = "SELECT PayRoll_Id,tblEmpWages.NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblChikinikiPayrolls.OrgName,tblEmpPayRoll.Bank_Name FROM tblEmpWages LEFT JOIN tblChikinikiPayrolls ON (tblChikinikiPayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblChikinikiPayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE MONTH(tblEmpWages.CreateDate) = " + month + " AND YEAR(tblEmpWages.CreateDate)=" + year + "";
     if (!"".equals(orgId)) {
     query = query + " and tblEmpPayRoll.OrgAccId = " + orgId + " ";
     }
     if (!"".equals(bankName)) {
     query = query + " and tblEmpPayRoll.Bank_Name = '" + bankName + "' ";
     }
    
     }
     else{
     query = "SELECT PayRoll_Id,tblEmpWages.NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblMiraclePayrolls.OrgName,tblEmpPayRoll.Bank_Name FROM tblEmpWages LEFT JOIN tblMiraclePayrolls ON (tblMiraclePayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblMiraclePayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE MONTH(tblEmpWages.CreateDate) = " + month + " AND YEAR(tblEmpWages.CreateDate)=" + year + "";
     if (!"".equals(orgId)) {
     query = query + " and tblEmpPayRoll.OrgAccId = " + orgId + " ";
     }
     if (!"".equals(bankName)) {
     query = query + " and tblEmpPayRoll.Bank_Name = '" + bankName + "' ";
     }}
     System.out.println("query-->" + query);
    
     //  System.out.println("query123-->"+query);
     String reportToName = "";
     List teamList = null;
    
     int j = 1;
     preStmt = connection.prepareStatement(query);
    
     resultSet = preStmt.executeQuery();
    
    
     while (resultSet.next()) {
     int PayRoll_Id = resultSet.getInt("PayRoll_Id");
     double NetPaid = resultSet.getDouble("NetPaid");
     double GrossPay = resultSet.getDouble("GrossPay");
     // int DaysWorked = resultSet.getInt("DaysWorked");
     String OrgName = resultSet.getString("OrgName");
     String Bank_Name = resultSet.getString("Bank_Name");
     double VariablePay = resultSet.getDouble("VariablePay");
    
     String reportsTo = "";
    
     // String Description      = timeSheetStatus;
     map = new HashMap();
     //   System.out.println("innn_->"+EmpName+" -"+Description+" "+WorkDate+" "+P1Hrs+" "+P2Hrs);
     map.put("SNO", String.valueOf(j));
     map.put("PayRoll_Id", PayRoll_Id);
     map.put("NetPaid", NetPaid);
     map.put("GrossPay", GrossPay);
     //  map.put("DaysWorked", DaysWorked);
     map.put("OrgName", OrgName);
     map.put("Bank_Name", DataSourceDataProvider.getInstance().getBankNameById(Integer.parseInt(Bank_Name)));
     map.put("VariablePay", VariablePay);
     finalList.add(map);
     j++;
     }
    
    
     if (finalList.size() > 0) {
     filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "BankReport.xls";
     HSSFWorkbook hssfworkbook = new HSSFWorkbook();
     HSSFSheet sheet = hssfworkbook.createSheet("Employee Bank Report");
    
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
    
    
    
     HSSFDataFormat df = hssfworkbook.createDataFormat();
     HSSFRow row = sheet.createRow((short) 0);
     HSSFCell cell = row.createCell((short) 0);
     // HSSFRow row1 = sheet.createRow((short)0);
     HSSFCell cell1 = row.createCell((short) 1);
    
     HSSFCell cell2 = row.createCell((short) 2);
     HSSFCell cell3 = row.createCell((short) 3);
    
     HSSFCell cell4 = row.createCell((short) 4);
     HSSFCell cell5 = row.createCell((short) 5);
     HSSFCell cell6 = row.createCell((short) 6);
     cell.setCellValue("SNO");
     cell1.setCellValue("PayRoll_Id");
     cell2.setCellValue("NetPaid");
     cell3.setCellValue("GrossPay");
     cell4.setCellValue("OrgName");
     cell5.setCellValue("Bank_Name");
     cell6.setCellValue("VariablePay");
    
    
     // cell7.setCellValue("Leaves");
    
     cell.setCellStyle(headercs);
     cell1.setCellStyle(headercs);
     cell2.setCellStyle(headercs);
     cell3.setCellStyle(headercs);
     cell4.setCellStyle(headercs);
     cell5.setCellStyle(headercs);
     cell6.setCellStyle(headercs);
     int count = 1;
     //   while (resultSet.next()) {
     if (finalList.size() > 0) {
     Map payrollMap = null;
     for (int i = 0; i < finalList.size(); i++) {
     payrollMap = (Map) finalList.get(i);
     row = sheet.createRow((short) count++);
     cell = row.createCell((short) 0);
     //  HSSFRow row2 = sheet.createRow((short)0);
     cell1 = row.createCell((short) 1);
     cell2 = row.createCell((short) 2);
     cell3 = row.createCell((short) 3);
     cell4 = row.createCell((short) 4);
     cell5 = row.createCell((short) 5);
     cell6 = row.createCell((short) 6);
    
    
     cell.setCellValue((String) payrollMap.get("SNO"));
     cell1.setCellValue((Integer) payrollMap.get("PayRoll_Id"));
     cell2.setCellValue((Double) payrollMap.get("NetPaid"));
     cell3.setCellValue((Double) payrollMap.get("GrossPay"));
     cell4.setCellValue((String) payrollMap.get("OrgName"));
     cell5.setCellValue((String) payrollMap.get("Bank_Name"));
     cell6.setCellValue((Double) payrollMap.get("VariablePay"));
    
    
     cell.setCellStyle(cs);
     cell1.setCellStyle(cs);
     cell2.setCellStyle(cs);
     cell3.setCellStyle(cs);
     cell4.setCellStyle(cs);
     cell5.setCellStyle(cs);
     cell6.setCellStyle(cs);
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
     cell.setCellValue("");
     cell1.setCellValue("This Report is from");
     cell2.setCellValue(month + " month ");
     cell3.setCellValue("year " + year);
     cell4.setCellValue("");
    
    
     cell.setCellStyle(footercs);
     cell1.setCellStyle(footercs);
     cell2.setCellStyle(footercs);
     cell3.setCellStyle(footercs);
    
     cell4.setCellStyle(footercs);
     }
     sheet.autoSizeColumn((short) 0);
     sheet.autoSizeColumn((short) 1);
     sheet.autoSizeColumn((short) 2);
     sheet.autoSizeColumn((short) 3);
     sheet.autoSizeColumn((short) 4);
     sheet.autoSizeColumn((short) 5);
     sheet.autoSizeColumn((short) 6);
     hssfworkbook.write(fileOut);
     fileOut.flush();
     fileOut.close();
     }
    
    
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
     System.out.println("path=" + filePath);
     return filePath;
    
     }
     */
     public String generateBankReportsheetList(String bankName, String orgId, String year, int month) {


        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;


        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null;



        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;

        HashMap map = null;

        List finalList = new ArrayList();
        try {


            File file = new File(Properties.getProperty("Payroll.Report.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }
          //  System.out.println("test");
            FileOutputStream fileOut = null;
            String orgName = "";
            orgName = DataSourceDataProvider.getInstance().getOrgNameById(Integer.parseInt(orgId));

            String BankName = DataSourceDataProvider.getInstance().getBankNameById(Integer.parseInt(bankName));
            fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + BankName + " Deposit for " + orgName + " Report.xls");

            //fileOut  = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "BankReport.xls");

            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;


        //    System.out.println("orgName-->" + DataSourceDataProvider.getInstance().getOrgNameById(Integer.parseInt(orgId)));
        //    System.out.println("bankName-->" + bankName);
            orgName = DataSourceDataProvider.getInstance().getOrgNameById(Integer.parseInt(orgId));

            if (getDoRepaymentFlag() == 0) {
                        query = "SELECT tblEmpWages.EmpId as EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.Earned_Net_Paid as NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tbllkpayrollorganization.Description as OrgName,tblEmpWages.Bank_Name,tblEmpWages.BankAccount as BankAccountNumber FROM tblEmpWages  LEFT JOIN tbllkpayrollorganization ON (tbllkpayrollorganization.AccountId=tblEmpWages.OrgId) WHERE MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + " AND tblEmpWages.Earned_Net_Paid > 0 ";
                } else {
                        query = "SELECT tblEmpWages.EmpId as EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.Earned_Net_Paid as NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tbllkpayrollorganization.Description as OrgName,tblEmpWages.Bank_Name,tblEmpWages.BankAccount as BankAccountNumber FROM tblEmpWages  LEFT JOIN tbllkpayrollorganization ON (tbllkpayrollorganization.AccountId=tblEmpWages.OrgId) WHERE DoRepaymentFlag=1 and MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + "  AND tblEmpWages.Earned_Net_Paid > 0 ";
                }
                if (!"".equals(getOrgName())) {
                    query = query + " and tblEmpWages.OrgId = " + getOrgName() + " ";
                }
                if (!"".equals(getBankName())) {
                    query = query + " and tblEmpWages.Bank_Name = '" + getBankName() + "' ";
                }
               // System.out.println("query "+query);
//            
//            
//            
//            if (orgName.equals("Chikiniki Enterprises(India) Pvt. Ltd")) {
//                if (getDoRepaymentFlag() == 0) {
//                     query = "SELECT tblEmpWages.EmpId as EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.Earned_Net_Paid as NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblChikinikiPayrolls.OrgName,tblEmpPayRoll.Bank_Name,BankAccountNumber FROM tblEmpWages LEFT JOIN tblChikinikiPayrolls ON (tblChikinikiPayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblChikinikiPayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + " AND tblEmpWages.Earned_Net_Paid > 0 ";
//                } else {
//                    query = "SELECT tblEmpWages.EmpId as EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.RepaymentNetPaid as NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblChikinikiPayrolls.OrgName,tblEmpPayRoll.Bank_Name,BankAccountNumber FROM tblEmpWages LEFT JOIN tblChikinikiPayrolls ON (tblChikinikiPayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblChikinikiPayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE DoRepaymentFlag=1 and MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + "  AND tblEmpWages.Earned_Net_Paid > 0 ";
//                }
//                if (!"".equals(getOrgName())) {
//                    query = query + " and tblEmpPayRoll.OrgAccId = " + getOrgName() + " ";
//                }
//                if (!"".equals(getBankName())) {
//                    query = query + " and tblEmpPayRoll.Bank_Name = '" + getBankName() + "' ";
//                }
//
//            }
//            else if(orgName.equals("IT Lokam Services(India), Pvt. Ltd")){
//                 if (getDoRepaymentFlag() == 0) {
//                     query = "SELECT tblEmpWages.EmpId AS EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.Earned_Net_Paid AS NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblITLokamPayrolls.OrgName,tblEmpPayRoll.Bank_Name,BankAccountNumber FROM tblEmpWages LEFT JOIN tblITLokamPayrolls ON (tblITLokamPayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblITLokamPayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + " AND tblEmpWages.Earned_Net_Paid > 0 ";
//                } else {
//                    query = "SELECT tblEmpWages.EmpId AS EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.RepaymentNetPaid AS NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblITLokamPayrolls.OrgName,tblEmpPayRoll.Bank_Name,BankAccountNumber FROM tblEmpWages LEFT JOIN tblITLokamPayrolls ON (tblITLokamPayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblITLokamPayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE DoRepaymentFlag=1 AND MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + " AND tblEmpWages.Earned_Net_Paid > 0 ";
//                }
//                if (!"".equals(getOrgName())) {
//                    query = query + " and tblEmpPayRoll.OrgAccId = " + getOrgName() + " ";
//                }
//                if (!"".equals(getBankName())) {
//                    query = query + " and tblEmpPayRoll.Bank_Name = '" + getBankName() + "' ";
//                }
//            }
//           
//            else {
//                if (getDoRepaymentFlag() == 0) {
//                    query = "SELECT tblEmpWages.EmpId as EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.Earned_Net_Paid as NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblMiraclePayrolls.OrgName,tblEmpPayRoll.Bank_Name,BankAccountNumber FROM tblEmpWages LEFT JOIN tblMiraclePayrolls ON (tblMiraclePayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblMiraclePayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + " AND tblEmpWages.Earned_Net_Paid > 0 ";
//                 
//                } else {
//                      query = "SELECT tblEmpWages.EmpId as EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.RepaymentNetPaid as NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblMiraclePayrolls.OrgName,tblEmpPayRoll.Bank_Name,BankAccountNumber FROM tblEmpWages LEFT JOIN tblMiraclePayrolls ON (tblMiraclePayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblMiraclePayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE DoRepaymentFlag=1 and MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + " AND tblEmpWages.Earned_Net_Paid > 0 ";
//                }
//                if (!"".equals(getOrgName())) {
//                    query = query + " and tblEmpPayRoll.OrgAccId = " + getOrgName() + " ";
//                }
//                if (!"".equals(getBankName())) {
//                    query = query + " and tblEmpPayRoll.Bank_Name = '" + getBankName() + "' ";
//                }
//            }
         //   System.out.println("query-->" + query);

            //  System.out.println("query123-->"+query);
            String reportToName = "";
            List teamList = null;

            int j = 1;
            preStmt = connection.prepareStatement(query);

            resultSet = preStmt.executeQuery();
            String bank_Name = "";
            NumberFormat formatter = new DecimalFormat("#0.00");
            while (resultSet.next()) {


                int EmpId = resultSet.getInt("EmpId");
                String payPeriodStartDate = resultSet.getString("PayPeriodStartDate");
                double NetPaid = resultSet.getDouble("NetPaid");
                double GrossPay = resultSet.getDouble("GrossPay");
                // int DaysWorked = resultSet.getInt("DaysWorked");
                String OrgName = resultSet.getString("OrgName");
                String BankId = resultSet.getString("Bank_Name");
                String BankAccountNumber = "";
                if( resultSet.getString("BankAccountNumber")!=null && !"".equals( resultSet.getString("BankAccountNumber"))){
                 BankAccountNumber = resultSet.getString("BankAccountNumber");
                }
                double VariablePay = resultSet.getDouble("VariablePay");
                if(BankId!=null && !"".equals(BankId)){
                bank_Name = DataSourceDataProvider.getInstance().getBankNameById(Integer.parseInt(BankId));
                }
                String reportsTo = "";

                // String Description      = timeSheetStatus;
                map = new HashMap();
                //   System.out.println("innn_->"+EmpName+" -"+Description+" "+WorkDate+" "+P1Hrs+" "+P2Hrs);
                map.put("SNO", String.valueOf(j));
                map.put("EmpId", EmpId);
                map.put("NetPaid", formatter.format(NetPaid));
                map.put("AccNum", BankAccountNumber);
                //  map.put("DaysWorked", DaysWorked);
                map.put("Name", DataSourceDataProvider.getInstance().getNameAsPerAccById(EmpId));

                map.put("VariablePay", formatter.format(VariablePay));
                map.put("PayPeriodStartDate", payPeriodStartDate);
                finalList.add(map);
                j++;
            }

            double totalNetPaid = 0.0;
            if (finalList.size() > 0) {

                String bnkName = DataSourceDataProvider.getInstance().getBankNameById(Integer.parseInt(bankName));
                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + bnkName + " Deposit for " + orgName + " Report.xls";

                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Employee Bank Report");

                HSSFCellStyle cs = hssfworkbook.createCellStyle();
                cs.setWrapText(true);
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



                HSSFDataFormat df = hssfworkbook.createDataFormat();
                HSSFRow row = sheet.createRow((short) 0);
                int cellCount = 0;

                cellCount = 5;

                HSSFCell cell[] = new HSSFCell[cellCount];

//                HSSFCell cell = row.createCell((short) 0);
//                // HSSFRow row1 = sheet.createRow((short)0);
//                HSSFCell cell1 = row.createCell((short) 1);
//
//                HSSFCell cell2 = row.createCell((short) 2);
//                HSSFCell cell3 = row.createCell((short) 3);
//
//                HSSFCell cell4 = row.createCell((short) 4);
//                HSSFCell cell5 = row.createCell((short) 5);
//                HSSFCell cell6 = row.createCell((short) 6);
                row = sheet.createRow((short) 0);
                cell[0] = row.createCell((short) 0);
                cell[4] = row.createCell((short) 4);
                cell[0].setCellValue("To");
                String monthName = "";
                DateFormatSymbols dfs = new DateFormatSymbols();
                String[] months = dfs.getMonths();
                monthName = months[getMonth()];
              //  System.out.println("monthNaem-->" + monthName);

                cell[4].setCellValue("10-" + monthName + "-" + getYear());
                cell[0].setCellStyle(footercs);
                cell[4].setCellStyle(footercs);
                row = sheet.createRow((short) 1);
                cell[0] = row.createCell((short) 0);
                cell[0].setCellValue("The Branch Manager,");
                cell[0].setCellStyle(footercs);
                row = sheet.createRow((short) 2);
                cell[0] = row.createCell((short) 0);
                cell[0].setCellValue(bank_Name + " Bank Ltd.,");
                cell[0].setCellStyle(footercs);
                row = sheet.createRow((short) 3);
                cell[0] = row.createCell((short) 0);
                cell[0].setCellValue("XXXXXX Branch,");
                cell[0].setCellStyle(footercs);
                row = sheet.createRow((short) 4);
                cell[0] = row.createCell((short) 0);
                cell[0].setCellValue("Visakhapatnam.");
                cell[0].setCellStyle(footercs);

                row = sheet.createRow((short) 6);
                cell[0] = row.createCell((short) 0);
                cell[0].setCellValue("Dear Sir,");
                cell[0].setCellStyle(footercs);

                row = sheet.createRow((short) 7);
                cell[0] = row.createCell((short) 0);
                cell[0].setCellValue("The following are the Account Numbers and Salary details of employees.Please Credit the following Salaries into their respective Accounts. ");
                cell[0].setCellStyle(cs);

                sheet.addMergedRegion(CellRangeAddress.valueOf("A8:E10"));

//                  cell[cellval++].setCellValue("SNO");
//                   cell[cellval++].setCellValue("SNO");
                int p = 11;
                row = sheet.createRow((short) p);
                for (int i = 0; i < cellCount; i++) {

                    cell[i] = row.createCell((short) i);

                }
                int cellval = 0;
                cell[cellval++].setCellValue("SNO");

                cell[cellval++].setCellValue("PayPeriodStartDate");
                cell[cellval++].setCellValue("Name");
                cell[cellval++].setCellValue("Bank ACC Number");

                // cell3.setCellValue("GrossPay");
                cell[cellval++].setCellValue("Ned Paid");
//                cell5.setCellValue("Bank_Name");
//                cell6.setCellValue("VariablePay");


                // cell7.setCellValue("Leaves");
                for (int i = 0; i < cellCount; i++) {
                    cell[i].setCellStyle(headercs);
                }
//                cell.setCellStyle(headercs);
//                cell1.setCellStyle(headercs);
//                cell2.setCellStyle(headercs);
//                cell3.setCellStyle(headercs);
//                cell4.setCellStyle(headercs);
//                cell5.setCellStyle(headercs);
//                cell6.setCellStyle(headercs);
                int count = 12;
                //   while (resultSet.next()) {
                if (finalList.size() > 0) {
                    Map payrollMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        payrollMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        // cell = row.createCell((short) 0);
                        //  HSSFRow row2 = sheet.createRow((short)0);
                        for (j = 0; j < cellCount; j++) {
                            cell[j] = row.createCell((short) j);
                        }
//                        cell1 = row.createCell((short) 1);
//                        cell2 = row.createCell((short) 2);
//                        cell3 = row.createCell((short) 3);
//                        cell4 = row.createCell((short) 4);
//                        cell5 = row.createCell((short) 5);
//                        cell6 = row.createCell((short) 6);

                        cellval = 0;
                        cell[cellval++].setCellValue((String) payrollMap.get("SNO"));

                        cell[cellval++].setCellValue((String) payrollMap.get("PayPeriodStartDate"));
                        cell[cellval++].setCellValue((String) payrollMap.get("Name"));
                        cell[cellval++].setCellValue((String) payrollMap.get("AccNum"));

                        //cell[cellval++].setCellValue((Double) payrollMap.get("GrossPay"));

                        cell[cellval++].setCellValue((String) payrollMap.get("NetPaid"));
                        totalNetPaid += Double.parseDouble((String) payrollMap.get("NetPaid"));
                        // cell5.setCellValue((String) payrollMap.get("Bank_Name"));
                        //  cell6.setCellValue((Double) payrollMap.get("VariablePay"));

                        for (j = 0; j < cellCount; j++) {
                            cell[j].setCellStyle(cs);
                        }
//                        cell.setCellStyle(cs);
//                        cell1.setCellStyle(cs);
//                        cell2.setCellStyle(cs);
//                        cell3.setCellStyle(cs);
//                        cell4.setCellStyle(cs);
//                        cell5.setCellStyle(cs);
//                        cell6.setCellStyle(cs);
                    }


                    row = sheet.createRow((short) count++);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }


                    cell[3].setCellValue("Total NetPaid Value : ");
                    cell[4].setCellValue(formatter.format(totalNetPaid));

                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }

                    row = sheet.createRow((short) count++);
                    cell[0] = row.createCell((short) 0);
                    String date="10-" + monthName + "-" + getYear();
                    String data="Please find enclosed the " + bnkName + "  Bank Ltd., Cheque  No:  XXXX             for an amount of Rs: "+formatter.format(totalNetPaid)+"/- (Rupees ), Dated: "+date+".";
                  HSSFFont font = hssfworkbook.createFont();
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
HSSFRichTextString string = new HSSFRichTextString(data);
string.applyFont(data.indexOf(date),data.indexOf(date)+date.length(),font);
                    cell[0].setCellValue(string);
                    cell[0].setCellStyle(cs);
                    int nextLine = count + 1;
                   // System.out.println("count-->" + count + "--" + nextLine);
                    sheet.addMergedRegion(CellRangeAddress.valueOf("A" + count + ":E" + nextLine));
                    int nextRow = nextLine + 1;
                    row = sheet.createRow((short) nextRow++);
                    cell[0] = row.createCell((short) 0);
                    cell[0].setCellValue("Thanking You,");
                    cell[0].setCellStyle(footercs);
                    row = sheet.createRow((short) nextRow++);
                    cell[0] = row.createCell((short) 0);
                    cell[0].setCellValue("Authorised Signatory");
                    cell[0].setCellStyle(footercs);
                    row = sheet.createRow((short) nextRow++);
                    cell[0] = row.createCell((short) 0);
                    cell[0].setCellValue("Dr.L.V.N Madhavi");
                    cell[0].setCellStyle(footercs);
                    row = sheet.createRow((short) nextRow++);
                    cell[0] = row.createCell((short) 0);
                    cell[0].setCellValue("Managing Director");
                    cell[0].setCellStyle(footercs);
//                    row = sheet.createRow((short) count + 2);
//                    for (j = 0; j < cellCount; j++) {
//                        cell[j] = row.createCell((short) j);
//                    }

//                    cell = row.createCell((short) 0);
//                    //  HSSFRow row2 = sheet.createRow((short)0);
//                    cell1 = row.createCell((short) 1);
//                    cell2 = row.createCell((short) 2);
//                    cell3 = row.createCell((short) 3);
//                    cell4 = row.createCell((short) 4);
//                    cell5 = row.createCell((short) 5);
//                    cell6 = row.createCell((short) 6);
//                    cell[1].setCellValue("");
//                    cell[2].setCellValue("This Report is from");
//                    cell[3].setCellValue(month + " month year " + year);

                    // cell[3].setCellValue("year " + year);
                    // cell[4].setCellValue("");



//                    for (j = 0; j < cellCount; j++) {
//                        cell[j].setCellStyle(footercs);
//                    }
//                    cell.setCellStyle(footercs);
//                    cell1.setCellStyle(footercs);
//                    cell2.setCellStyle(footercs);
//                    cell3.setCellStyle(footercs);
//
//                    cell4.setCellStyle(footercs);
                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }


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
       // System.out.println("path=" + filePath);
        return filePath;

    }

     public String generatePayrollExcellList(int empId, String year, int month) {

        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;

        /**
         * callableStatement is a reference variable for CallableStatement .
         */
        CallableStatement callableStatement = null;

        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null, preStmtTemp = null;

        /**
         * The queryString is useful to get queryString result to the particular
         * jsp page
         */
        String queryString = "";
        Statement statement = null;

        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        String timeSheetStatus = "";
        HashMap map = null;
        HashMap map1 = null;
        List finalList = new ArrayList();
        try {


            File file = new File(Properties.getProperty("Payroll.Report.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "PayrollReport.xls");

            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;


            query = "SELECT DaysInMonth,DaysWorked,DaysProject,DaysVacation,LeavesAvailable,DaysHolidays,Daysweekends,Earned_Gross_Pay,Earned_Net_Paid,PayPeriodStartDate,tblEmpWages.EmpId,PayRoll_Id,NetPaid,GrossPay,DaysWorked,CONCAT (tblEmployee.FName,'.',tblEmployee.LName) AS NAME FROM tblEmpWages "
                    + " left outer join tblEmployee on(tblEmpWages.EmpId = tblEmployee.Id) WHERE MONTH(PayPeriodStartDate) = '" + month + "' AND YEAR(PayPeriodStartDate)=" + year + " AND IsBlock=0 ";

            if (empId != 0 && !"".equals(empId)) {
                query = query + " and empId ='" + empId + "' ";
            }
           
          //  System.out.println("query123-->" + query);
            String reportToName = "";
            List teamList = null;




            int j = 1;
            preStmt = connection.prepareStatement(query);

            resultSet = preStmt.executeQuery();


            while (resultSet.next()) {
                int PayRoll_Id = resultSet.getInt("PayRoll_Id");
                int EmpId = resultSet.getInt("EmpId");
                double NetPaid = resultSet.getDouble("Earned_Net_Paid");
                double GrossPay = resultSet.getDouble("Earned_Gross_Pay");
                int DaysWorked = resultSet.getInt("DaysWorked");
                int DaysInMonth = resultSet.getInt("DaysInMonth");
                int DaysProject = resultSet.getInt("DaysProject");
                int DaysVacation = resultSet.getInt("DaysVacation");
                int DaysHolidays = resultSet.getInt("DaysHolidays");
                int Daysweekends = resultSet.getInt("Daysweekends");
                String payPeriodStartDate = resultSet.getString("PayPeriodStartDate");
                String employeeName = resultSet.getString("NAME");
                String reportsTo = "";

                // String Description      = timeSheetStatus;
                map = new HashMap();
                //   System.out.println("innn_->"+EmpName+" -"+Description+" "+WorkDate+" "+P1Hrs+" "+P2Hrs);
                map.put("SNO", String.valueOf(j));
                map.put("EmpId", empId);
                map.put("PayPeriodStartDate", payPeriodStartDate);
                map.put("EmployeeName", employeeName);
                map.put("NetPaid", NetPaid);
                map.put("GrossPay", GrossPay);
                map.put("DaysInMonth", DaysInMonth);
                map.put("DaysWorked", DaysWorked);
                map.put("DaysProject", DaysProject);
                map.put("DaysVacation", DaysVacation);
                map.put("DaysHolidays", DaysHolidays);
                map.put("Daysweekends", Daysweekends);
                finalList.add(map);
                j++;
            }


            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "PayrollReport.xls";
                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Employee Payroll Report");

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
                cell.setCellValue("SNO");
                cell1.setCellValue("EmpId");
                cell2.setCellValue("PayPeriodStartDate");
                cell3.setCellValue("EmployeeName");
                cell4.setCellValue("DaysInMonth");
                cell5.setCellValue("DaysWorked");
                cell6.setCellValue("DaysProject");
                cell7.setCellValue("DaysVacation");
                cell8.setCellValue("DaysHolidays");
                cell9.setCellValue("Daysweekends");

                cell10.setCellValue("NetPaid");
                cell11.setCellValue("GrossPay");

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

                int count = 1;
                //   while (resultSet.next()) {
                if (finalList.size() > 0) {
                    Map payrollMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        payrollMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        cell = row.createCell((short) 0);
                        //  HSSFRow row2 = sheet.createRow((short)0);
                        cell1 = row.createCell((short) 1);
                        cell2 = row.createCell((short) 2);
                        cell3 = row.createCell((short) 3);
                        cell4 = row.createCell((short) 4);
                        cell5 = row.createCell((short) 5);
                        //  HSSFRow row2 = sheet.createRow((short)0);
                        cell6 = row.createCell((short) 6);
                        cell7 = row.createCell((short) 7);
                        cell8 = row.createCell((short) 8);
                        cell9 = row.createCell((short) 9);
                        cell10 = row.createCell((short) 10);
                        //  HSSFRow row2 = sheet.createRow((short)0);
                        cell11 = row.createCell((short) 11);



                        cell.setCellValue((String) payrollMap.get("SNO"));
                        cell1.setCellValue((Integer) payrollMap.get("EmpId"));
                        cell2.setCellValue((String) payrollMap.get("PayPeriodStartDate"));
                        cell3.setCellValue((String) payrollMap.get("EmployeeName"));
                        cell4.setCellValue((Integer) payrollMap.get("DaysInMonth"));
                        cell5.setCellValue((Integer) payrollMap.get("DaysWorked"));
                        cell6.setCellValue((Integer) payrollMap.get("DaysProject"));
                        cell7.setCellValue((Integer) payrollMap.get("DaysVacation"));
                        cell8.setCellValue((Integer) payrollMap.get("DaysHolidays"));
                        cell9.setCellValue((Integer) payrollMap.get("Daysweekends"));
                        cell10.setCellValue((Double) payrollMap.get("NetPaid"));
                        cell11.setCellValue((Double) payrollMap.get("GrossPay"));


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

                    }
                    row = sheet.createRow((short) count++);
                    cell = row.createCell((short) 0);
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    cell1 = row.createCell((short) 1);
                    cell2 = row.createCell((short) 2);
                    cell3 = row.createCell((short) 3);
                    cell4 = row.createCell((short) 4);
                    cell.setCellValue("");
                    cell1.setCellValue("This Report is from");
                    cell2.setCellValue(month + " month ");
                    cell3.setCellValue("year " + year);
                    cell4.setCellValue("");


                    cell.setCellStyle(footercs);
                    cell1.setCellStyle(footercs);
                    cell2.setCellStyle(footercs);
                    cell3.setCellStyle(footercs);

                    cell4.setCellStyle(footercs);
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


                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();


            }


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

    public String generateLeavesCountList(String deptId, String empName, String year, int month, String country) {

        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;
        Connection connection1 = null;
        /**
         * callableStatement is a reference variable for CallableStatement .
         */
        CallableStatement callableStatement = null;
        CallableStatement callableStatement1 = null;
        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null, preStmtTemp = null;
        PreparedStatement preStmt1 = null;
        /**
         * The queryString is useful to get queryString result to the particular
         * jsp page
         */
        String queryString = "";
        Statement statement = null;

        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        String timeSheetStatus = "";
        HashMap map = null;
        HashMap map1 = null;
        List finalList = new ArrayList();
        List finalLeavesList = new ArrayList();
        int totalVacations = 0;
        int totalComptimes = 0;
        int totalLeavesNotApproved = 0;
        int totalCancelLeaves = 0;
        try {


            File file = new File(Properties.getProperty("Payroll.Report.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "LeavesCountReport.xls");

            connection1 = ConnectionProvider.getInstance().getConnection();
            String query = null;
            String query1 = null;


            if (!"".equals(empName)) {
             //   System.out.println("in if->");
                String result1 = "";
                connection = ConnectionProvider.getInstance().getConnection();
                query = "SELECT Id,EmpNo,CONCAT(FName,'.',LName) as EmployeeName FROM tblEmployee WHERE DepartmentId = '" + deptId + "' AND Country ='" + country + "' AND CurStatus = 'Active' and LoginId ='" + empName + "'";
                preStmt = connection.prepareStatement(query);

                resultSet = preStmt.executeQuery();

                int j = 1;
                while (resultSet.next()) {
                    callableStatement1 = connection.prepareCall("{call spTotalLeaves(?,?,?,?)}");
                    callableStatement1.setInt(1, month);
                    callableStatement1.setInt(2, Integer.parseInt(year));
                    callableStatement1.setInt(3, resultSet.getInt("Id"));
                    callableStatement1.registerOutParameter(4, Types.VARCHAR);
                    callableStatement1.executeUpdate();
                    result1 = callableStatement1.getString(4);
                  //  System.out.println("result1->" + result1);
                  //  System.out.println("result1->" + result1.split("\\^")[4]);
                    map = new HashMap();
                    //   System.out.println("innn_->"+EmpName+" -"+Description+" "+WorkDate+" "+P1Hrs+" "+P2Hrs);
                    map.put("SNO", String.valueOf(j));
                    map.put("employeeName", resultSet.getString("EmployeeName"));
                    map.put("empNo", resultSet.getInt("EmpNo"));
                    map.put("totalLeavs", result1.split("\\^")[4]);

                    finalList.add(map);
                    j++;
                }

            } else {
              //  System.out.println("in else->");
                String result = "";
                query1 = "SELECT Id,EmpNo,CONCAT(FName,'.',LName) as EmployeeName FROM tblEmployee WHERE DepartmentId = '" + deptId + "' AND Country ='" + country + "' AND CurStatus = 'Active' ";
                preStmt1 = connection1.prepareStatement(query1);

                resultSet1 = preStmt1.executeQuery();

                int j = 1;
                while (resultSet1.next()) {
                  //  System.out.println("Id ->" + resultSet1.getInt("Id"));
                    callableStatement = connection1.prepareCall("{call spTotalLeaves(?,?,?,?)}");
                    callableStatement.setInt(1, month);
                    callableStatement.setInt(2, Integer.parseInt(year));
                    callableStatement.setInt(3, resultSet1.getInt("Id"));
                    callableStatement.registerOutParameter(4, Types.VARCHAR);
                    callableStatement.executeUpdate();
                    result = callableStatement.getString(4);
                   // System.out.println("result->" + result);
                   // System.out.println("result->" + result.split("\\^")[4]);
                    map = new HashMap();

                    map.put("SNO", String.valueOf(j));
                    map.put("employeeName", resultSet1.getString("EmployeeName"));
                    map.put("empNo", resultSet1.getInt("EmpNo"));
                    map.put("totalLeavs", result.split("\\^")[4]);

                    finalList.add(map);
                    j++;


                }

            }
            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "PayrollReport.xls";
                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Employee Payroll Report");

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
                HSSFRow row = sheet.createRow((short) 0);
                HSSFCell cell = row.createCell((short) 0);
                // HSSFRow row1 = sheet.createRow((short)0);
                HSSFCell cell1 = row.createCell((short) 1);

                HSSFCell cell2 = row.createCell((short) 2);
                HSSFCell cell3 = row.createCell((short) 3);


                cell.setCellValue("SNO");
                cell1.setCellValue("employeeName");
                cell2.setCellValue("empNo");
                cell3.setCellValue("totalLeaves");


                // cell7.setCellValue("Leaves");

                cell.setCellStyle(headercs);
                cell1.setCellStyle(headercs);
                cell2.setCellStyle(headercs);
                cell3.setCellStyle(headercs);

                int count = 1;
                //   while (resultSet.next()) {
                if (finalList.size() > 0) {
                    Map payrollMap = null;
                    for (int i = 0; i < finalList.size(); i++) {

                        payrollMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        cell = row.createCell((short) 0);
                        //  HSSFRow row2 = sheet.createRow((short)0);
                        cell1 = row.createCell((short) 1);
                        cell2 = row.createCell((short) 2);
                        cell3 = row.createCell((short) 3);



                        cell.setCellValue((String) payrollMap.get("SNO"));
                        cell1.setCellValue((String) payrollMap.get("employeeName"));
                        cell2.setCellValue((Integer) payrollMap.get("empNo"));
                        cell3.setCellValue((String) payrollMap.get("totalLeavs"));


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
                    cell2.setCellValue(month + " month ");
                    cell3.setCellValue("year " + year);



                    cell.setCellStyle(footercs);
                    cell1.setCellStyle(footercs);
                    cell2.setCellStyle(footercs);
                    cell3.setCellStyle(footercs);


                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);


                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();


            }


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
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (preStmt1 != null) {
                    preStmt1.close();
                    preStmt1 = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (callableStatement1 != null) {
                    callableStatement1.close();
                    callableStatement1 = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return filePath;
    }

    public String generateBiometricReportList(String deptId, String empName, String year, int month) {

        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;

        /**
         * callableStatement is a reference variable for CallableStatement .
         */
        CallableStatement callableStatement = null;

        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null, preStmtTemp = null;

        /**
         * The queryString is useful to get queryString result to the particular
         * jsp page
         */
        String queryString = "";
        Statement statement = null;

        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        String timeSheetStatus = "";
        HashMap map = null;
        HashMap map1 = null;
        List finalList = new ArrayList();
        try {


            File file = new File(Properties.getProperty("Payroll.Report.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "BiometricReport.xls");

            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;


            query = "SELECT PayRoll_Id,DaysVacationFromBiometric FROM tblEmpWages WHERE MONTH(PayPeriodStartDate) = " + month + " AND YEAR(PayPeriodStartDate)=" + year + " AND IsBlock=0 ";


            //  System.out.println("query123-->"+query);
            String reportToName = "";
            List teamList = null;




            int j = 1;
            preStmt = connection.prepareStatement(query);

            resultSet = preStmt.executeQuery();


            while (resultSet.next()) {
                int PayRoll_Id = resultSet.getInt("PayRoll_Id");

                int DaysVacationForBiometric = resultSet.getInt("DaysVacationFromBiometric");

                String reportsTo = "";

                // String Description      = timeSheetStatus;
                map = new HashMap();
                //   System.out.println("innn_->"+EmpName+" -"+Description+" "+WorkDate+" "+P1Hrs+" "+P2Hrs);
                map.put("SNO", String.valueOf(j));
                map.put("PayRoll_Id", PayRoll_Id);
                map.put("DaysVacationForBiometric", DaysVacationForBiometric);

                finalList.add(map);
                j++;
            }


            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "BiometricReport.xls";
                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Employee Payroll Report");

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
                HSSFRow row = sheet.createRow((short) 0);
                HSSFCell cell = row.createCell((short) 0);
                // HSSFRow row1 = sheet.createRow((short)0);
                HSSFCell cell1 = row.createCell((short) 1);

                HSSFCell cell2 = row.createCell((short) 2);

                cell.setCellValue("SNO");
                cell1.setCellValue("PayRoll_Id");
                cell2.setCellValue("DaysVacationForBiometric");

                // cell7.setCellValue("Leaves");

                cell.setCellStyle(headercs);
                cell1.setCellStyle(headercs);
                cell2.setCellStyle(headercs);

                int count = 1;
                //   while (resultSet.next()) {
                if (finalList.size() > 0) {
                    Map payrollMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        payrollMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        cell = row.createCell((short) 0);
                        //  HSSFRow row2 = sheet.createRow((short)0);
                        cell1 = row.createCell((short) 1);
                        cell2 = row.createCell((short) 2);
                        cell.setCellValue((String) payrollMap.get("SNO"));
                        cell1.setCellValue((Integer) payrollMap.get("PayRoll_Id"));
                        cell2.setCellValue((Integer) payrollMap.get("DaysVacationForBiometric"));
                        cell.setCellStyle(cs);
                        cell1.setCellStyle(cs);
                        cell2.setCellStyle(cs);
                    }
                    row = sheet.createRow((short) count++);
                    cell = row.createCell((short) 0);
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    cell1 = row.createCell((short) 1);
                    cell2 = row.createCell((short) 2);

                    cell.setCellValue("");
                    cell1.setCellValue("This Report is from");
                    cell2.setCellValue(month + " month and year=" + year);



                    cell.setCellStyle(footercs);
                    cell1.setCellStyle(footercs);
                    cell2.setCellStyle(footercs);

                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);


                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();


            }


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

    public String getTimeSheetReport() {
       // System.out.println("getTimeSheetReport method");
        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            //For creating Excel grind from Search result Grid
            // System.out.println("StartDate" + getStartdate());
            //  System.out.println("EndDate" + getEnddate());
            // fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus());
            setDepartmentId(getDepartmentId());
            setEmpnameById(getEmpnameById());
            setYear(getYear());
            setMonth(getMonth());
            setCountry(getCountry());
            fileLocation = generateTimesheet(getDepartmentId(), getEmpnameById(), getYear(), getMonth());
        //    System.out.println("fileLocation-------->" + fileLocation);
            if (!"".equals(fileLocation)) {
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
                    // responseString = "downLoaded!!";

                    //  httpServletResponse.setContentType(getDownloadType());
                    // httpServletResponse.getWriter().write(responseString);

                } else {
                    throw new FileNotFoundException("File not found");
                }

                inputStream.close();
                outputStream.close();

            } else {
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessageForTimeSheets", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                result = INPUT;

            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }/*catch (ServiceLocatorException ex) {
         ex.printStackTrace();
         } finally {
         try {
        
         inputStream.close();
         outputStream.close();
        
         } catch (IOException ex) {
         ex.printStackTrace();
         }
         }*/
        return result;
    }

    public String generateTimesheet(String deptId, String empName, String year, int month) {

        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;

        /**
         * callableStatement is a reference variable for CallableStatement .
         */
        CallableStatement callableStatement = null;

        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null, preStmtTemp = null;

        /**
         * The queryString is useful to get queryString result to the particular
         * jsp page
         */
        String queryString = "";
        Statement statement = null;

        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        String timeSheetStatus = "";
        HashMap map = null;
        HashMap map1 = null;
        List finalList = new ArrayList();
        try {


            File file = new File(Properties.getProperty("TimeSheet.Report.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "TimeSheetReport.xls");

            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;

            query = "SELECT tblTimeSheetLines.EmpId, workDate, CONCAT (tblEmployee.FName,'.',tblEmployee.LName) AS NAME, IFNULL(Prj1Hrs,0)+IFNULL(Prj2Hrs,0)+IFNULL(InternalHrs,0)+IFNULL(Prj3Hrs,0)+IFNULL(Prj4Hrs,0)+IFNULL(Prj5Hrs,0) AS billingHrs FROM tblTimeSheetLines LEFT JOIN tblEmployee ON (tblTimeSheetLines.EmpId=tblEmployee.Id) WHERE EXTRACT(YEAR FROM WorkDate)=" + getYear() + " AND EXTRACT(MONTH FROM WorkDate)=" + getMonth() + " ";

            if (!"".equals(getDepartmentId())) {
                query += "AND tblEmployee.DepartmentId = '" + getDepartmentId() + "' ";
            }
            if (!"".equals(getEmpnameById())) {
                query += "AND tblEmployee.loginId = '" + getEmpnameById() + "' ";
            }
            query += "ORDER BY tblEmployee.Id";

         //   System.out.println("query123-->" + query);
            String reportToName = "";
            List teamList = null;

            int j = 1;
            preStmt = connection.prepareStatement(query);

            resultSet = preStmt.executeQuery();

            Set EmpIdSet = new HashSet();
            while (resultSet.next()) {
                int EmpID = resultSet.getInt("EmpId");
                int billingHrs = resultSet.getInt("billingHrs");
                String EmpName = resultSet.getString("NAME");
                String workDate = resultSet.getString("workDate");
                workDate = workDate.substring(0, 10);
              //  System.out.println("workDate==" + workDate);
                String reportsTo = "";

                // String Description      = timeSheetStatus;
                map = new HashMap();
                //   System.out.println("innn_->"+EmpName+" -"+Description+" "+WorkDate+" "+P1Hrs+" "+P2Hrs);
                map.put("SNO", String.valueOf(j));
                map.put("EmpID", EmpID);
                map.put("billingHrs", billingHrs);
                map.put("EmpName", EmpName);
                map.put("workDate", workDate);
                finalList.add(map);
                EmpIdSet.add(EmpID);
                j++;
            }
            int setId = 0;
            ArrayList al = null;
            Map empMap = new HashMap();
            for (Object object : EmpIdSet) {
                al = new ArrayList();
                setId = (Integer) object;
                for (int i = 0; i < finalList.size(); i++) {
                    map = (HashMap) finalList.get(i);
                    int eid = (Integer) map.get("EmpID");
                    if (eid == setId) {
                        al.add(map);
                    }
                    empMap.put(setId, al);
                }
            }


            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "TimeSheetReport.xls";
                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Employee Time Sheet Report");

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



                HSSFDataFormat df = hssfworkbook.createDataFormat();
                HSSFRow row = sheet.createRow((short) 0);
                int iYear = Integer.parseInt(getYear());
                int iMonth = getMonth();
                int iDay = 1;
             //   System.out.println("    month=" + iMonth);
// Create a calendar object and set year and month
                Calendar mycal = new GregorianCalendar(iYear, iMonth - 1, iDay);

// Get the number of days in that month
                int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
               // System.out.println("days==" + daysInMonth);
                String months[] = new String[daysInMonth];
                HSSFCell cell = row.createCell((short) 0);
                HSSFCell cell1 = row.createCell((short) 1);
                HSSFCell cell2 = row.createCell((short) 2);
                HSSFCell cells[] = new HSSFCell[daysInMonth];
             //   System.out.println("111");
                for (int i = 1; i <= daysInMonth; i++) {

                    cells[i - 1] = row.createCell((short) (i + 2));
                 //   System.out.println("222222");
                }

                cell.setCellValue("SNO");
                cell1.setCellValue("EmpId");
                cell2.setCellValue("NAme");
                for (int i = 1; i <= daysInMonth; i++) {
                    String day = "";
                    String mont = "";
                    if (i < 10) {
                        day = "0" + i;
                    } else {
                        day = i + "";
                    }
                    if (iMonth < 10) {
                        mont = "0" + iMonth;
                    } else {
                        mont = "0" + iMonth;
                    }


                    cells[i - 1].setCellValue(iYear + "-" + mont + "-" + day);
                    months[i - 1] = iYear + "-" + mont + "-" + day;

                    months[i - 1] = iYear + "-" + mont + "-" + day;
                   // System.out.println("months==" + months[i - 1] + "asd");
                }


                cell.setCellStyle(headercs);
                cell1.setCellStyle(headercs);
                cell2.setCellStyle(headercs);
                HSSFCell cellseq = null;
                for (int i = 1; i <= daysInMonth; i++) {
                    cells[i - 1].setCellStyle(headercs);

                }

                int count = 1;
                //   while (resultSet.next()) {
                if (EmpIdSet.size() > 0) {
                    Map payrollMap = null;
                    //     for (int i = 0; i < EmpIdSet.size(); i++) {
                    for (Object object : EmpIdSet) {

                        setId = (Integer) object;
                        row = sheet.createRow((short) count++);
                        cell = row.createCell((short) 0);
                        //  HSSFRow row2 = sheet.createRow((short)0);
                        cell1 = row.createCell((short) 1);
                        cell2 = row.createCell((short) 2);
                        //  cell3 = row.createCell((short) 3);
                        // cell4 = row.createCell((short) 4);

                        for (int l = 1; l <= daysInMonth; l++) {
                            cells[l - 1] = row.createCell((short) (l + 2));
                        }
                        cell.setCellValue((Integer) (count - 1));
                        // Iterator iterator = empMap.entrySet().iterator();
                        for (int l = 0; l < daysInMonth; l++) {
                            //  if(k==l)
                            //  cells[l].setCellValue((Integer) payrollMap.get("billingHrs"));
                            //else
                            cells[l].setCellValue((int) 0);
                            cells[l].setCellStyle(cs);
                        }
                        al = (ArrayList) empMap.get(setId);
                        for (int i = 0; i < al.size(); i++) {
                            payrollMap = (Map) al.get(i);

                            cell1.setCellValue((Integer) payrollMap.get("EmpID"));
                            cell2.setCellValue((String) payrollMap.get("EmpName"));

                            cell.setCellStyle(cs);
                            cell1.setCellStyle(cs);
                            cell2.setCellStyle(cs);
                            String dbmonth = (String) payrollMap.get("workDate");
                            int k = 0;
                            for (k = 0; k < months.length; k++) {
                                //cells[k].setCellValue((int)0);
                                if (dbmonth.equalsIgnoreCase(months[k])) {
                                  //  System.out.println("equals at " + k);
                                  //  System.out.print("==" + payrollMap.get("billingHrs"));
                                    cells[k].setCellValue((Integer) payrollMap.get("billingHrs"));
                                    break;
                                }

                            }

                            for (int l = 0; l < daysInMonth; l++) {
                                if (k == l) {
                                    cells[l].setCellValue((Integer) payrollMap.get("billingHrs"));
                                }
                                //else
                                // cells[l].setCellValue((int)0);
                                cells[l].setCellStyle(cs);
                            }
                        }

                    }
                    row = sheet.createRow((short) count++);
                    cell = row.createCell((short) 0);
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    cell1 = row.createCell((short) 1);
                    cell2 = row.createCell((short) 2);

                    cell.setCellStyle(footercs);
                    cell1.setCellStyle(footercs);
                    cell2.setCellStyle(footercs);

                }
               // System.out.println("last");
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);

             //   System.out.println("last2");
                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();

            }
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

    /*
     * Payroll Check Excel download
     * Date : 04/29/2015
     * 
     */
    public String getPayrollCheckReport() {
       // System.out.println("getTimeSheetReport method");
        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            //For creating Excel grind from Search result Grid
            // System.out.println("StartDate" + getStartdate());
            //  System.out.println("EndDate" + getEnddate());
            // fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus());

            setYear(getYear());
            setMonth(getMonth());

            fileLocation = payrollCheckExcelDownload(getYear(), getMonth());
          //  System.out.println("fileLocation-------->" + fileLocation);
            if (!"".equals(fileLocation)) {
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
                    // responseString = "downLoaded!!";

                    //  httpServletResponse.setContentType(getDownloadType());
                    // httpServletResponse.getWriter().write(responseString);

                } else {
                    throw new FileNotFoundException("File not found");
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }

            } else {
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessageForTimeSheets", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                result = INPUT;

            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }/*catch (ServiceLocatorException ex) {
         ex.printStackTrace();
         } finally {
         try {
        
         inputStream.close();
         outputStream.close();
        
         } catch (IOException ex) {
         ex.printStackTrace();
         }
         }*/
        return result;
    }

    public String payrollCheckExcelDownload(String year, int month) {

        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;

        /**
         * callableStatement is a reference variable for CallableStatement .
         */
        CallableStatement callableStatement = null;

        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null, preStmtTemp = null;

        /**
         * The queryString is useful to get queryString result to the particular
         * jsp page
         */
        String queryString = "";
        Statement statement = null;

        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        String timeSheetStatus = "";
        HashMap map = null;
        HashMap map1 = null;
        List finalList = new ArrayList();
        try {


            File file = new File(Properties.getProperty("Payroll.Report.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "PayRollCheck_" + month + "_" + year + ".xls");
            filePath = file.getAbsolutePath() + File.separator + "PayRollCheck_" + month + "_" + year + ".xls";
            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
            Map employeeMap = new HashMap();
            //   query="SELECT Id,CONCAT(FName,'.',LName) AS Name FROM tblEmployee WHERE CurStatus = 'Active'";
            query = "SELECT Id,CONCAT(FName,'.',LName) AS NAME FROM tblEmployee INNER JOIN tblEmpPayRoll ON tblEmployee.Id = tblEmpPayRoll.EmpId  WHERE CurStatus = 'Active'";
            preStmt = connection.prepareStatement(query);

            resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                employeeMap.put(resultSet.getInt("Id"), resultSet.getString("Name"));
            }
            resultSet.close();
            preStmt.close();
            Iterator entries = employeeMap.entrySet().iterator();
            //------------------
            DateFormat inputDF = new SimpleDateFormat("MM/dd/yyyy");
            Date date1 = inputDF.parse(month + "/1/" + year);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            int currmonth = cal.get(Calendar.MONTH);
            int curryear = cal.get(Calendar.YEAR);


            Calendar aCalendar = Calendar.getInstance();
            aCalendar.setTime(date1);
// add -1 month to current month
            aCalendar.add(Calendar.MONTH, -1);
// set DATE to 1, so first date of previous month
            aCalendar.set(Calendar.DATE, 1);



            int lastMonth = aCalendar.get(Calendar.MONTH);
            int lastYesr = aCalendar.get(Calendar.YEAR);

            Map wagesMap = null;
            List empWagesList = new ArrayList();
            query = "SELECT tblEmpWages.NetPaid,PayrollDate,tblEmpPayRoll.EmpId FROM tblEmpWages  LEFT OUTER JOIN tblEmpPayRoll ON tblEmpWages.PayRoll_Id=tblEmpPayRoll.PayRollId WHERE MONTH(PayPeriodStartDate) = ? AND YEAR(PayPeriodStartDate)=? AND IsBlock=0 AND tblEmpPayRoll.EmpId = ?";
            preStmt = connection.prepareStatement(query);
            int sno = 0;
            double currNetPaid = 0.00;
            double lastNetPaid = 0.00;
            boolean isExist = false;
            while (entries.hasNext()) {
                Entry thisEntry = (Entry) entries.next();
                int key = Integer.parseInt(thisEntry.getKey().toString());
                String employeeName = thisEntry.getValue().toString();
                //------------------

                preStmt.setInt(1, (currmonth + 1));
                preStmt.setInt(2, curryear);
                preStmt.setInt(3, key);
                resultSet = preStmt.executeQuery();

                if (resultSet.next()) {
                    isExist = true;
                    sno++;
                    wagesMap = new HashMap();
                    wagesMap.put("SNO", sno);
                    wagesMap.put("EmpName", employeeName);
                    wagesMap.put("PayrollDate", resultSet.getString("PayrollDate"));
                    wagesMap.put("NetPaid", resultSet.getString("NetPaid"));
                    currNetPaid = resultSet.getDouble("NetPaid");
                    resultSet.close();
                }
                if (isExist) {
                    preStmt.setInt(1, (lastMonth + 1));
                    preStmt.setInt(2, lastYesr);
                    preStmt.setInt(3, key);
                    resultSet = preStmt.executeQuery();
                    if (resultSet.next()) {
                        wagesMap.put("LastPayrollDate", resultSet.getString("PayrollDate"));
                        wagesMap.put("LastNetPaid", resultSet.getString("NetPaid"));
                        lastNetPaid = resultSet.getDouble("NetPaid");
                        resultSet.close();
                        double diff = ((currNetPaid - lastNetPaid) / currNetPaid) * 100;
                        wagesMap.put("Difference", diff);

                    }

                    // ...
                }
                empWagesList.add(wagesMap);
                isExist = false;
                currNetPaid = 0.00;
                lastNetPaid = 0.00;
            }

            if (empWagesList.size() > 0) {
                //  filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "PayrollReport.xls";
                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("PayrollCheck");

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
                HSSFRow row = sheet.createRow((short) 0);
                HSSFCell cell = row.createCell((short) 0);
                // HSSFRow row1 = sheet.createRow((short)0);
                HSSFCell cell1 = row.createCell((short) 1);

                HSSFCell cell2 = row.createCell((short) 2);
                HSSFCell cell3 = row.createCell((short) 3);
                HSSFCell cell4 = row.createCell((short) 4);
                HSSFCell cell5 = row.createCell((short) 5);
                HSSFCell cell6 = row.createCell((short) 6);

                cell.setCellValue("SNO");
                cell1.setCellValue("EmpName");
                cell2.setCellValue("PayrollDate");
                cell3.setCellValue("NetPaid");
                cell4.setCellValue("LastPayrollDate");
                cell5.setCellValue("LastNetPaid");
                cell6.setCellValue("Difference");


                // cell7.setCellValue("Leaves");

                cell.setCellStyle(headercs);
                cell1.setCellStyle(headercs);
                cell2.setCellStyle(headercs);
                cell3.setCellStyle(headercs);
                cell4.setCellStyle(headercs);
                cell5.setCellStyle(headercs);
                cell6.setCellStyle(headercs);


                int count = 1;
                //   while (resultSet.next()) {
                if (empWagesList.size() > 0) {
                    Map payrollMap = null;
                    for (int i = 0; i < empWagesList.size(); i++) {
                        payrollMap = (Map) empWagesList.get(i);
                        row = sheet.createRow((short) count++);
                        cell = row.createCell((short) 0);
                        //  HSSFRow row2 = sheet.createRow((short)0);
                        cell1 = row.createCell((short) 1);
                        cell2 = row.createCell((short) 2);
                        cell3 = row.createCell((short) 3);
                        cell4 = row.createCell((short) 4);
                        cell5 = row.createCell((short) 5);
                        cell6 = row.createCell((short) 6);

                        cell.setCellValue((Integer) payrollMap.get("SNO"));
                        cell1.setCellValue((String) payrollMap.get("EmpName"));
                        cell2.setCellValue((String) payrollMap.get("PayrollDate"));
                        cell3.setCellValue((String) payrollMap.get("NetPaid"));
                        cell4.setCellValue((String) payrollMap.get("LastPayrollDate"));
                        cell5.setCellValue((String) payrollMap.get("LastNetPaid"));
                        // String.format("%.2f", (Double)payrollMap.get("Difference"));
                        cell6.setCellValue(String.format("%.2f", (Double) payrollMap.get("Difference")));
                        cell.setCellStyle(cs);
                        cell1.setCellStyle(cs);
                        cell2.setCellStyle(cs);
                        cell3.setCellStyle(cs);
                        cell4.setCellStyle(cs);
                        cell5.setCellStyle(cs);
                        cell6.setCellStyle(cs);
                    }

                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);



                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();


            }



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

//generate pdf for bank reports
    public String getBankReportAsPDF() {
        //System.out.println("getBankReportAsPDF1 method");
        String empName = "";
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                empName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                String reportResult = generateBankReport(empName, httpServletResponse);
               httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, reportResult);
                result = INPUT;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                result = ERROR;
            }

        }
        return result;
    }

    private String generateBankReport(String empName, HttpServletResponse httpServletResponse) {
        Map RBMap = new HashMap();
        RBMap.put("empName", empName);
        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet resultSet = null;
        HashMap map = null;
        String reportResult = "";
        
        List finalList = new ArrayList();
        try {
            String BankReportsSource = "";
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            if (!"".equals(getBankName())) {
                BankReportsSource = ReportProperties.getProperty("BankReportJRXML");
            } else {
                BankReportsSource = ReportProperties.getProperty("BankReportALLJRXML");
            }
        //    System.out.println("BankReportsSource==" + BankReportsSource);
            JasperReport jasperReport = JasperCompileManager.compileReport(BankReportsSource);
            //----------------------------------------
           // System.out.println("months-->" + getMonth());
           // System.out.println("year--->" + getYear());
          //  System.out.println("orgName--->" + getOrgName());
          //  System.out.println("BankName--->" + getBankName());
            String query = null;
            connection = ConnectionProvider.getInstance().getConnection();
            String orgName = DataSourceDataProvider.getInstance().getOrgNameById(Integer.parseInt(getOrgName()));
             if (getDoRepaymentFlag() == 0) {
                        query = "SELECT tblEmpWages.EmpId as EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.Earned_Net_Paid as NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tbllkpayrollorganization.Description as OrgName,tblEmpWages.Bank_Name,tblEmpWages.BankAccount as BankAccountNumber FROM tblEmpWages  LEFT JOIN tbllkpayrollorganization ON (tbllkpayrollorganization.AccountId=tblEmpWages.OrgId) WHERE MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + " AND tblEmpWages.Earned_Net_Paid > 0 ";
                } else {
                        query = "SELECT tblEmpWages.EmpId as EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.Earned_Net_Paid as NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tbllkpayrollorganization.Description as OrgName,tblEmpWages.Bank_Name,tblEmpWages.BankAccount as BankAccountNumber FROM tblEmpWages  LEFT JOIN tbllkpayrollorganization ON (tbllkpayrollorganization.AccountId=tblEmpWages.OrgId) WHERE DoRepaymentFlag=1 and MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + "  AND tblEmpWages.Earned_Net_Paid > 0 ";
                }
                if (!"".equals(getOrgName())) {
                    query = query + " and tblEmpWages.OrgId = " + getOrgName() + " ";
                }
                if (!"".equals(getBankName())) {
                    query = query + " and tblEmpWages.Bank_Name = '" + getBankName() + "' ";
                }
          /*   if (orgName.equals("Chikiniki Enterprises(India) Pvt. Ltd")) {
                if (getDoRepaymentFlag() == 0) {
                     query = "SELECT tblEmpWages.EmpId as EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.Earned_Net_Paid as NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblChikinikiPayrolls.OrgName,tblEmpPayRoll.Bank_Name,BankAccountNumber FROM tblEmpWages LEFT JOIN tblChikinikiPayrolls ON (tblChikinikiPayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblChikinikiPayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + " AND tblEmpWages.Earned_Net_Paid > 0 ";
                } else {
                    query = "SELECT tblEmpWages.EmpId as EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.RepaymentNetPaid as NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblChikinikiPayrolls.OrgName,tblEmpPayRoll.Bank_Name,BankAccountNumber FROM tblEmpWages LEFT JOIN tblChikinikiPayrolls ON (tblChikinikiPayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblChikinikiPayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE DoRepaymentFlag=1 and MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + "  AND tblEmpWages.Earned_Net_Paid > 0 ";
                }
                if (!"".equals(getOrgName())) {
                    query = query + " and tblEmpPayRoll.OrgAccId = " + getOrgName() + " ";
                }
                if (!"".equals(getBankName())) {
                    query = query + " and tblEmpPayRoll.Bank_Name = '" + getBankName() + "' ";
                }

            }
            else if(orgName.equals("IT Lokam Services(India), Pvt. Ltd")){
                 if (getDoRepaymentFlag() == 0) {
                     query = "SELECT tblEmpWages.EmpId AS EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.Earned_Net_Paid AS NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblITLokamPayrolls.OrgName,tblEmpPayRoll.Bank_Name,BankAccountNumber FROM tblEmpWages LEFT JOIN tblITLokamPayrolls ON (tblITLokamPayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblITLokamPayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + " AND tblEmpWages.Earned_Net_Paid > 0 ";
                } else {
                    query = "SELECT tblEmpWages.EmpId AS EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.RepaymentNetPaid AS NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblITLokamPayrolls.OrgName,tblEmpPayRoll.Bank_Name,BankAccountNumber FROM tblEmpWages LEFT JOIN tblITLokamPayrolls ON (tblITLokamPayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblITLokamPayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE DoRepaymentFlag=1 AND MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + " AND tblEmpWages.Earned_Net_Paid > 0 ";
                }
                if (!"".equals(getOrgName())) {
                    query = query + " and tblEmpPayRoll.OrgAccId = " + getOrgName() + " ";
                }
                if (!"".equals(getBankName())) {
                    query = query + " and tblEmpPayRoll.Bank_Name = '" + getBankName() + "' ";
                }
            }
           
            else {
                if (getDoRepaymentFlag() == 0) {
                    query = "SELECT tblEmpWages.EmpId as EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.Earned_Net_Paid as NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblMiraclePayrolls.OrgName,tblEmpPayRoll.Bank_Name,BankAccountNumber FROM tblEmpWages LEFT JOIN tblMiraclePayrolls ON (tblMiraclePayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblMiraclePayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + " AND tblEmpWages.Earned_Net_Paid > 0 ";
                 
                } else {
                      query = "SELECT tblEmpWages.EmpId as EmpId,tblEmpWages.PayPeriodStartDate,tblEmpWages.RepaymentNetPaid as NetPaid,tblEmpWages.GrossPay,tblEmpWages.VariablePay,tblMiraclePayrolls.OrgName,tblEmpPayRoll.Bank_Name,BankAccountNumber FROM tblEmpWages LEFT JOIN tblMiraclePayrolls ON (tblMiraclePayrolls.PayRollId=tblEmpWages.PayRoll_Id) LEFT JOIN tblEmpPayRoll ON (tblMiraclePayrolls.PayRollId=tblEmpPayRoll.PayRollId) WHERE DoRepaymentFlag=1 and MONTH(tblEmpWages.PayrollDate) = " + month + " AND YEAR(tblEmpWages.PayrollDate)=" + year + " AND tblEmpWages.Earned_Net_Paid > 0 ";
                }
                if (!"".equals(getOrgName())) {
                    query = query + " and tblEmpPayRoll.OrgAccId = " + getOrgName() + " ";
                }
                if (!"".equals(getBankName())) {
                    query = query + " and tblEmpPayRoll.Bank_Name = '" + getBankName() + "' ";
                }
            } */
          //  System.out.println("query-->" + query);

            //  System.out.println("query123-->"+query);


            int j = 1;
            preStmt = connection.prepareStatement(query);

            resultSet = preStmt.executeQuery();

            double totalNetPaid = 0.00;
            while (resultSet.next()) {
                int EmpId = resultSet.getInt("EmpId");
                double NetPaid = resultSet.getDouble("NetPaid");
                double GrossPay = resultSet.getDouble("GrossPay");
                // int DaysWorked = resultSet.getInt("DaysWorked");
                String OrgName = resultSet.getString("OrgName");
               
                double VariablePay = resultSet.getDouble("VariablePay");

               
                String payPeriodStartDate = resultSet.getString("PayPeriodStartDate");
               
                
              
                String BankId = resultSet.getString("Bank_Name");
                String BankAccountNumber = "";
                if( resultSet.getString("BankAccountNumber")!=null && !"".equals( resultSet.getString("BankAccountNumber"))){
                 BankAccountNumber = resultSet.getString("BankAccountNumber");
                }
                String bank_Name ="";
                if(BankId!=null && !"".equals(BankId)){
                bank_Name = DataSourceDataProvider.getInstance().getBankNameById(Integer.parseInt(BankId));
                }
                
                String reportsTo = "";

                // String Description      = timeSheetStatus;
                map = new HashMap();

                //   System.out.println("innn_->"+EmpName+" -"+Description+" "+WorkDate+" "+P1Hrs+" "+P2Hrs);
                map.put("SNO", String.valueOf(j));
                map.put("Bank_Name", bank_Name);
                map.put("AccNum", BankAccountNumber + "");
                map.put("Name", DataSourceDataProvider.getInstance().getNameAsPerAccById(EmpId));
                //map.put("EmpId", EmpId);
                map.put("NetPaid", NetPaid + "");

                totalNetPaid += (Double) NetPaid;
                //  map.put("DaysWorked", DaysWorked);


                //map.put("VariablePay", VariablePay);
                finalList.add(map);
                j++;
            }
            NumberFormat formatter = new DecimalFormat("#0.00");

            RBMap.put("TotalNetPaid", formatter.format(totalNetPaid));
          //  System.out.println("Total NEtPaid==" + formatter.format(totalNetPaid));
            // List dataList = getBirthdayStatusData(query);
            if (finalList.size() > 0) {
                //System.out.println("FutureLeaves ::"+dataList.size());
                //System.out.println(usedLeaves+"%%%%%%%%"+appliedLeaves+"%%%%%%%%%%"+(avalLeaves-appliedLeaves));
                JRBeanCollectionDataSource jrxmlds = new JRBeanCollectionDataSource(finalList);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, RBMap, jrxmlds);
                //JasperViewer.viewReport(jasperPrint);
                byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
                outputStream = httpServletResponse.getOutputStream();
                httpServletResponse.setContentType("application/pdf");
                httpServletResponse.setContentLength(pdf.length);
                if (!"".equals(getBankName())) {
                    httpServletResponse.setHeader("Content-disposition", "inline; filename=\"" + getBankName() + " Deposit for " + orgName + " Report.pdf\"");
                } else {
                    httpServletResponse.setHeader("Content-disposition", "inline; filename=\"All Banks Deposits for " + orgName + " Report.pdf\"");
                }

                outputStream.write(pdf);
                outputStream.flush();
                outputStream.close();
            } else {
 
                reportResult = "<font style='color:red;font-size:15px;'>No records are available between selected dates</font>";
                //httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
            setBankReportsResponse(reportResult);
                     setBankReportFlag(1);
            }
        } catch (Exception ex) {
           // ex.printStackTrace();
        } finally {

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
        return reportResult;
    }

    public String downloadTefAttachment() throws Exception {
        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            setTefId(getTefId());
            setEmpId(getEmpId());
            setPayRollId(DataSourceDataProvider.getInstance().getPayrollIdByEmpId(getEmpId()));
            fileLocation = DataSourceDataProvider.getInstance().getFileLocationByTefId(getTefId());
            //System.out.println("getFromTef()-------->" + getFromTef());
            
            if (!"".equals(fileLocation) && !"null".equals(fileLocation) && fileLocation != null && fileLocation.length() != 0) {
                httpServletResponse.setContentType("application/force-download");
                // File file = new File(Properties.getProperty("mscvp.docCreationPath")+"SearchedExcelDocument.xls");
                //   System.out.println("fileLocation-->"+fileLocation);
                File file = new File(fileLocation);
                Date date = new Date();

                //fileName = (date.getYear() + 1900) + "_" + (date.getMonth() + 1) + "_" + date.getDate() + "_" + file.getName();
                // fileName = getStartdate().substring(0,10) +"To"+ getEnddate().substring(0,10) +"_"+getLeaveType()+"_"+file.getName();
                 if (file.exists()) {
                fileName = file.getName();
              
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
                    // responseString = "downLoaded!!";

                    //  httpServletResponse.setContentType(getDownloadType());
                    // httpServletResponse.getWriter().write(responseString);
  inputStream.close();
                outputStream.close();
                } else {
                    // System.out.println("in else");
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No Attachment exists !!</font>");
                if(getFromTef().equals("1")){
                    result = "tef";
                }else{
                result = INPUT;
                }
                }

              

            } else {
               // System.out.println("in else");
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No Attachment exists !!</font>");
                if(getFromTef().equals("1")){
                    result = "tef";
                }else{
                result = INPUT;
                }

            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }


    public String generateExcelReportForLockAmt() throws ServiceLocatorException {

        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            setDepartmentId(getDepartmentId());
            setEmpnameById(getEmpnameById());
            String lockDate = getMonth() + "/01/" + getYear();
            java.util.Date referenceDate = DateUtility.getInstance().convertStringToMySql(lockDate);
            Calendar c = Calendar.getInstance();
            c.setTime(referenceDate);
            c.add(Calendar.MONTH, -12);
            String previousYearDate = DateUtility.getInstance().convertDateToMySql1(c.getTime());
         //   System.out.println("previousYearDate -->" + previousYearDate);
            setYear(previousYearDate.split("-")[0]);
            setMonth(Integer.parseInt(previousYearDate.split("-")[1]));

            if (getEmpnameById().length() != 0) {
                fileLocation = generateLockReportsheetListOfSingleEmploee(getDepartmentId(), getEmpnameById(), getYear(), getMonth());
            } else {
                fileLocation = generateLockReportsheetList(getDepartmentId(), getEmpnameById(), getYear(), getMonth());

            }
         //   System.out.println("fileLocation-------->" + fileLocation);
            if (!"".equals(fileLocation)) {
                httpServletResponse.setContentType("application/force-download");
                File file = new File(fileLocation);
                Date date = new Date();
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

                } else {
                    throw new FileNotFoundException("File not found");
                }

                inputStream.close();
                outputStream.close();

            } else {
                setTempFlag("1");
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessageForLOck", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                result = INPUT;

            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public String generateLockReportsheetList(String departmentId, String empName, String year, int month) {


        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;


        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null;



        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;

        HashMap map = null;

        List finalList = new ArrayList();
        try {


            File file = new File(Properties.getProperty("Payroll.Report.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }
         //   System.out.println("test");
            FileOutputStream fileOut = null;

            if (!"".equals(departmentId)) {
                fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "LocakAmt_OfAllEmps_" + departmentId + " Report.xls");
            } else {
                fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "LocakAmt_OfAllEmps_Report.xls");
            }


            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;


            query = "SELECT DISTINCT tblEmpPayRoll.EmpId as EmpId,COUNT(tblEmpPayRoll.EmpId) AS TotalMonths,SUM(tblEmpWages.Earned_LongTermBonus) as totalLongTermBonus, CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,MONTHNAME(PayrollDate) AS MONTH,YEAR(PayrollDate) AS YEAR FROM tblEmpWages "
                    + "LEFT OUTER JOIN tblEmpPayRoll ON (tblEmpWages.PayRoll_Id = tblEmpPayRoll.PayRollId) "
                    + "LEFT OUTER JOIN tblEmployee ON(tblEmpPayRoll.EmpId = tblEmployee.Id) "
                    + "WHERE  tblEmpWages.Earned_LongTermBonus>0 and "
                    + "MONTH(tblEmpPayRoll.LockAmtStartDate) = " + getMonth() + "  AND YEAR(tblEmpPayRoll.LockAmtStartDate) = " + getYear();
            if (!"".equals(departmentId)) {
                query = query + " and tblEmployee.DepartmentId = '" + getDepartmentId() + "'";
            }

            query = query + " GROUP BY EmpId";



          //  System.out.println("query-->" + query);

            //  System.out.println("query123-->"+query);

            List teamList = null;

            int j = 1;
            preStmt = connection.prepareStatement(query);

            resultSet = preStmt.executeQuery();


            while (resultSet.next()) {
                int EmpId = resultSet.getInt("EmpId");
                int totalMonths = resultSet.getInt("TotalMonths");
                double totalLongTermBonus = resultSet.getDouble("totalLongTermBonus");

                String EmpName = resultSet.getString("EmpName");

                String reportsTo = "";

                // String Description      = timeSheetStatus;
                map = new HashMap();
                //   System.out.println("innn_->"+EmpName+" -"+Description+" "+WorkDate+" "+P1Hrs+" "+P2Hrs);
                map.put("SNO", String.valueOf(j));
                map.put("EmpId", EmpId);
                map.put("Employee_Name", EmpName);
                map.put("Total_LongTerm_Bonus", totalLongTermBonus);
                map.put("Total_Months", totalMonths);

                finalList.add(map);
                j++;
            }

            double totalNetPaid = 0.0;
            if (finalList.size() > 0) {

                if (!"".equals(departmentId)) {
                    filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "LocakAmt_OfAllEmps_" + departmentId + " Report.xls";
                } else {
                    filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "LocakAmt_OfAllEmps_Report.xls";
                }

                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Employee Lock Amount Report");

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



                HSSFDataFormat df = hssfworkbook.createDataFormat();
                HSSFRow row = sheet.createRow((short) 0);
                int cellCount = 5;
                HSSFCell cell[] = new HSSFCell[cellCount];
                for (int i = 0; i < cellCount; i++) {
                    cell[i] = row.createCell((short) i);
                }
                int cellval = 0;
                cell[cellval++].setCellValue("SNO");
                cell[cellval++].setCellValue("EmpId");
                cell[cellval++].setCellValue("Employee_Name");
                cell[cellval++].setCellValue("Total Long Term Bonus");
                cell[cellval++].setCellValue("Total Months");
                for (int i = 0; i < cellCount; i++) {
                    cell[i].setCellStyle(headercs);
                }
                int count = 1;
                if (finalList.size() > 0) {
                    Map payrollLockMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        payrollLockMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        for (j = 0; j < cellCount; j++) {
                            cell[j] = row.createCell((short) j);
                        }

                        cellval = 0;
                        cell[cellval++].setCellValue((String) payrollLockMap.get("SNO"));
                        cell[cellval++].setCellValue((Integer) payrollLockMap.get("EmpId"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Employee_Name"));
                        cell[cellval++].setCellValue((Double) payrollLockMap.get("Total_LongTerm_Bonus"));
                        cell[cellval++].setCellValue((Integer) payrollLockMap.get("Total_Months"));


                        for (j = 0; j < cellCount; j++) {
                            cell[j].setCellStyle(cs);
                        }
                    }

                    row = sheet.createRow((short) count++);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }


                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }
                    row = sheet.createRow((short) count + 2);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }
                    cell[0].setCellValue("");
                    cell[1].setCellValue("This Report is from");
                    cell[2].setCellValue(month + " month year " + year);

                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }

                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }


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
       // System.out.println("path=" + filePath);
        return filePath;

    }

    public String generateLockReportsheetListOfSingleEmploee(String bankName, String orgId, String year, int month) {
        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;
        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null;
        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        HashMap map = null;
        List finalList = new ArrayList();
        try {
            File file = new File(Properties.getProperty("Payroll.Report.Path"));
            if (!file.exists()) {
                file.mkdirs();
            }
           // System.out.println("test");
            FileOutputStream fileOut = null;
            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
            query = "SELECT tblEmpWages.Earned_LongTermBonus,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,MONTHNAME(PayrollDate) AS MONTH,YEAR(PayrollDate) AS YEAR FROM tblEmpWages "
                    + "LEFT OUTER JOIN tblEmpPayRoll ON (tblEmpWages.PayRoll_Id = tblEmpPayRoll.PayRollId) "
                    + "LEFT OUTER JOIN tblEmployee ON(tblEmpPayRoll.EmpId = tblEmployee.Id) "
                    + "WHERE tblEmpWages.Earned_LongTermBonus>0 and tblEmployee.DepartmentId = '" + getDepartmentId() + "' AND tblEmployee.Id = '" + DataSourceDataProvider.getInstance().getEmpIdByLoginId(getEmpnameById()) + "' AND "
                    + "MONTH(tblEmpPayRoll.LockAmtStartDate) = " + getMonth() + " AND YEAR(tblEmpPayRoll.LockAmtStartDate) = " + getYear();
          //  System.out.println("query-->" + query);
            List teamList = null;
            int j = 1;
            preStmt = connection.prepareStatement(query);
            resultSet = preStmt.executeQuery();
            String EmpName = "";
            while (resultSet.next()) {
                String monthSelected = resultSet.getString("MONTH");
                int yearSelected = resultSet.getInt("YEAR");
                double totalLongTermBonus = resultSet.getDouble("Earned_LongTermBonus");
                EmpName = resultSet.getString("EmpName");
                String reportsTo = "";
                map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("Employee_Name", EmpName);
                map.put("Total_LongTerm_Bonus", totalLongTermBonus);
                map.put("Month", monthSelected);
                map.put("Year", yearSelected);

                finalList.add(map);
                j++;
            }
            fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "LocakAmt_Report_Of_" + EmpName + ".xls");
            if (finalList.size() > 0) {

                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "LocakAmt_Report_Of_" + EmpName + ".xls";


                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Employee Lock Amount Report");

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



                HSSFDataFormat df = hssfworkbook.createDataFormat();
                HSSFRow row = sheet.createRow((short) 0);
                int cellCount = 5;
                HSSFCell cell[] = new HSSFCell[cellCount];
                for (int i = 0; i < cellCount; i++) {
                    cell[i] = row.createCell((short) i);
                }
                int cellval = 0;
                cell[cellval++].setCellValue("SNO");
                cell[cellval++].setCellValue("Employee_Name");
                cell[cellval++].setCellValue("Long Term Bonus");
                cell[cellval++].setCellValue("Month");
                cell[cellval++].setCellValue("Year");
                for (int i = 0; i < cellCount; i++) {
                    cell[i].setCellStyle(headercs);
                }
                int count = 1;
                if (finalList.size() > 0) {
                    Map payrollLockMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        payrollLockMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        for (j = 0; j < cellCount; j++) {
                            cell[j] = row.createCell((short) j);
                        }

                        cellval = 0;
                        cell[cellval++].setCellValue((String) payrollLockMap.get("SNO"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Employee_Name"));
                        cell[cellval++].setCellValue((Double) payrollLockMap.get("Total_LongTerm_Bonus"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Month"));
                        cell[cellval++].setCellValue((Integer) payrollLockMap.get("Year"));

                        for (j = 0; j < cellCount; j++) {
                            cell[j].setCellStyle(cs);
                        }
                    }

                    row = sheet.createRow((short) count++);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }


                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }
                    row = sheet.createRow((short) count + 2);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }
                    cell[0].setCellValue("");
                    cell[1].setCellValue("This Report is from");
                    cell[2].setCellValue(month + " month year " + year);

                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }

                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }


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
      //  System.out.println("path=" + filePath);
        return filePath;
    }

    //generatePfOrPTExcel
    public String generatePfOrPTExcel() throws ServiceLocatorException {

        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            setYear(getYear());
            setMonth(getMonth());


            if (getReportFor().equals("PF")) {
                fileLocation = generatePFReportsheet(getYear(), getMonth());
            } else if (getReportFor().equals("Professional Tax")) {
                fileLocation = generatePTReportsheet(getYear(), getMonth());

            } else {
                fileLocation = generateTDSReportsheet(getYear(), getMonth());
            }
         //   System.out.println("fileLocation-------->" + fileLocation);
            if (!"".equals(fileLocation)) {
                httpServletResponse.setContentType("application/force-download");
                File file = new File(fileLocation);
                Date date = new Date();
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

                } else {
                    throw new FileNotFoundException("File not found");
                }

                inputStream.close();
                outputStream.close();

            } else {
                setTempFlag("2");
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessageForPForPTReports", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                result = INPUT;

            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public String generatePFReportsheet(String year, int month) {
        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;
        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null;
        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        HashMap map = null;
        List finalList = new ArrayList();
        try {
            File file = new File(Properties.getProperty("Payroll.Report.Path"));
            if (!file.exists()) {
                file.mkdirs();
            }
         //   System.out.println("test");
            FileOutputStream fileOut = null;
            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
            query = "SELECT tblEmpWages.Earned_Employeer_Pf,tblEmpWages.PayPeriodStartDate,tblEmployee.PFNo,tblEmpWages.Earned_Gross_Pay,tblEmpWages.Earned_HRA,tblEmpWages.Earned_EmployeePF,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,MONTHNAME(PayrollDate) AS MONTH,YEAR(PayrollDate) AS YEAR FROM tblEmpWages "
                    + "LEFT OUTER JOIN tblEmployee ON(tblEmpWages.EmpId = tblEmployee.Id) "
                    + "WHERE MONTH(tblEmpWages.PayrollDate) = " + getMonth() + " AND YEAR(tblEmpWages.PayrollDate) = " + getYear();
          //  System.out.println("query-->" + query);
            List teamList = null;
            int j = 1;
            preStmt = connection.prepareStatement(query);
            resultSet = preStmt.executeQuery();
            String EmpName = "";
            String monthSelected = "";
            int yearSelected = 0;
            while (resultSet.next()) {
                NumberFormat formatter = new DecimalFormat("#0.00");
                monthSelected = resultSet.getString("MONTH");
                yearSelected = resultSet.getInt("YEAR");
                String payPeriodStartdDate = resultSet.getString("PayPeriodStartDate");
                String pfNo = resultSet.getString("PFNo");
                double earned_Gross = resultSet.getDouble("Earned_Gross_Pay");
                double earned_HRA = resultSet.getDouble("Earned_HRA");
                double PFGross = earned_Gross - earned_HRA;
                double employerPF = resultSet.getDouble("Earned_Employeer_Pf");
                double employeePF = resultSet.getDouble("Earned_EmployeePF");
                EmpName = resultSet.getString("EmpName");
                String reportsTo = "";
                map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("PayPeriodStartDate", payPeriodStartdDate);
                map.put("Employee_Name", EmpName);
                map.put("PF_Num", pfNo);
                if (PFGross > 15000) {
                    map.put("PF_Gross", formatter.format(15000));
                } else {
                    map.put("PF_Gross", formatter.format(PFGross));
                }
                map.put("Employer_PF", formatter.format(employerPF));
                map.put("Employee_PF", formatter.format(employeePF));


                finalList.add(map);
                j++;
            }
            fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "PF_Report_For_" + monthSelected + "_" + yearSelected + ".xls");
            if (finalList.size() > 0) {

                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "PF_Report_For_" + monthSelected + "_" + yearSelected + ".xls";


                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Employee PF Amount Report");

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



                HSSFDataFormat df = hssfworkbook.createDataFormat();
                HSSFRow row = sheet.createRow((short) 0);
                int cellCount = 7;
                HSSFCell cell[] = new HSSFCell[cellCount];
                for (int i = 0; i < cellCount; i++) {
                    cell[i] = row.createCell((short) i);
                }
                int cellval = 0;
                cell[cellval++].setCellValue("SNO");
                cell[cellval++].setCellValue("PayPeriodStartDate");
                cell[cellval++].setCellValue("Employee_Name");
                cell[cellval++].setCellValue("PF_Num");
                cell[cellval++].setCellValue("PF Gross");
                cell[cellval++].setCellValue("Employer PF");
                cell[cellval++].setCellValue("Employee PF");

                for (int i = 0; i < cellCount; i++) {
                    cell[i].setCellStyle(headercs);
                }
                int count = 1;
                if (finalList.size() > 0) {
                    Map payrollLockMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        payrollLockMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        for (j = 0; j < cellCount; j++) {
                            cell[j] = row.createCell((short) j);
                        }

                        cellval = 0;
                        cell[cellval++].setCellValue((String) payrollLockMap.get("SNO"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("PayPeriodStartDate"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Employee_Name"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("PF_Num"));
                        //PF_Num
                        cell[cellval++].setCellValue((String) payrollLockMap.get("PF_Gross"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Employer_PF"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Employee_PF"));


                        for (j = 0; j < cellCount; j++) {
                            cell[j].setCellStyle(cs);
                        }
                    }

                    row = sheet.createRow((short) count++);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }


                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }
                    row = sheet.createRow((short) count + 2);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }
                    cell[0].setCellValue("");
                    cell[1].setCellValue("This Report is from");
                    cell[2].setCellValue(monthSelected + " month year " + year);

                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }

                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }


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
       // System.out.println("path=" + filePath);
        return filePath;
    }

    public String generatePTReportsheet(String year, int month) {
        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;
        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null;
        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        HashMap map = null;
        List finalList = new ArrayList();
        try {
            File file = new File(Properties.getProperty("Payroll.Report.Path"));
            if (!file.exists()) {
                file.mkdirs();
            }
           // System.out.println("test");
            FileOutputStream fileOut = null;
            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
            query = "SELECT tblEmpWages.Ded_Professional_Tax,tblEmpWages.Earned_Gross_Pay,tblEmpWages.Earned_Net_Paid,tblEmpWages.PayPeriodStartDate,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,MONTHNAME(PayrollDate) AS MONTH,YEAR(PayrollDate) AS YEAR FROM tblEmpWages "
                    + "LEFT OUTER JOIN tblEmployee ON(tblEmpWages.EmpId = tblEmployee.Id) "
                    + "WHERE MONTH(tblEmpWages.PayrollDate) = " + getMonth() + " AND YEAR(tblEmpWages.PayrollDate) = " + getYear();
           // System.out.println("query-->" + query);
            List teamList = null;
            int j = 1;
            preStmt = connection.prepareStatement(query);
            resultSet = preStmt.executeQuery();
            String EmpName = "";
            String monthSelected = "";
            int yearSelected = 0;
            while (resultSet.next()) {
                NumberFormat formatter = new DecimalFormat("#0.00");
                monthSelected = resultSet.getString("MONTH");
                yearSelected = resultSet.getInt("YEAR");
                double dedProfessionalTax = resultSet.getDouble("Ded_Professional_Tax");
                double earned_Gross = resultSet.getDouble("Earned_Gross_Pay");
                double earned_Net = resultSet.getDouble("Earned_Net_Paid");
                EmpName = resultSet.getString("EmpName");
                String payPeriodStartDate = resultSet.getString("PayPeriodStartDate");
                String reportsTo = "";
                map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("PayPeriodStartDate", payPeriodStartDate);
                map.put("Employee_Name", EmpName);
                map.put("Gross_Pay", formatter.format(earned_Gross));
                map.put("Net_Paid", formatter.format(earned_Net));
                map.put("Professional_Tax", formatter.format(dedProfessionalTax));


                finalList.add(map);
                j++;
            }
            fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "ProfessionalTax_Report_Of_" + monthSelected + "_" + yearSelected + ".xls");
            if (finalList.size() > 0) {

                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "ProfessionalTax_Report_Of_" + monthSelected + "_" + yearSelected + ".xls";


                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Professional Tax Report");

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



                HSSFDataFormat df = hssfworkbook.createDataFormat();
                HSSFRow row = sheet.createRow((short) 0);
                int cellCount = 6;
                HSSFCell cell[] = new HSSFCell[cellCount];
                for (int i = 0; i < cellCount; i++) {
                    cell[i] = row.createCell((short) i);
                }
                int cellval = 0;
                cell[cellval++].setCellValue("SNO");
                cell[cellval++].setCellValue("PayPeriodStartDate");
                cell[cellval++].setCellValue("Employee_Name");
                cell[cellval++].setCellValue("Gross_Pay");
                cell[cellval++].setCellValue("Net_Paid");
                cell[cellval++].setCellValue("Professional_Tax");

                for (int i = 0; i < cellCount; i++) {
                    cell[i].setCellStyle(headercs);
                }
                int count = 1;
                if (finalList.size() > 0) {
                    Map payrollLockMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        payrollLockMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        for (j = 0; j < cellCount; j++) {
                            cell[j] = row.createCell((short) j);
                        }

                        cellval = 0;
                        cell[cellval++].setCellValue((String) payrollLockMap.get("SNO"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("PayPeriodStartDate"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Employee_Name"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Gross_Pay"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Net_Paid"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Professional_Tax"));


                        for (j = 0; j < cellCount; j++) {
                            cell[j].setCellStyle(cs);
                        }
                    }

                    row = sheet.createRow((short) count++);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }


                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }
                    row = sheet.createRow((short) count + 2);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }
                    cell[0].setCellValue("");
                    cell[1].setCellValue("This Report is from");
                    cell[2].setCellValue(monthSelected + " month year " + year);

                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }

                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }


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
       // System.out.println("path=" + filePath);
        return filePath;
    }

    public String generateTDSReportsheet(String year, int month) {
        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;
        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null;
        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        HashMap map = null;
        List finalList = new ArrayList();
        try {
            File file = new File(Properties.getProperty("Payroll.Report.Path"));
            if (!file.exists()) {
                file.mkdirs();
            }
          //  System.out.println("test");
            FileOutputStream fileOut = null;
            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
            query = "SELECT tblEmpWages.Earned_Employeer_Pf,tblEmpWages.PayPeriodStartDate,tblEmployee.SSN,tblEmpWages.Earned_Gross_Pay,tblEmpWages.Earned_Net_Paid,tblEmpWages.Earned_HRA,tblEmpWages.Earned_EmployeePF,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,MONTHNAME(PayrollDate) AS MONTH,YEAR(PayrollDate) AS YEAR,tblEmpWages.EmpId,tblEmpWages.TaxableIncome,tblEmpWages.Ded_Income_Tax FROM tblEmpWages "
                    + "LEFT OUTER JOIN tblEmployee ON(tblEmpWages.EmpId = tblEmployee.Id) "
                    + "WHERE MONTH(tblEmpWages.PayrollDate) = " + getMonth() + " AND YEAR(tblEmpWages.PayrollDate) = " + getYear() + " and Ded_Income_Tax!=0 ";
           // System.out.println("query-->" + query);
            List teamList = null;
            int j = 1;
            preStmt = connection.prepareStatement(query);
            resultSet = preStmt.executeQuery();
            String EmpName = "";
            String monthSelected = "";
            int yearSelected = 0;
            double Ded_Income_Tax = 0.0;
            while (resultSet.next()) {
                NumberFormat formatter = new DecimalFormat("#0.00");
                monthSelected = resultSet.getString("MONTH");
                yearSelected = resultSet.getInt("YEAR");
                String payPeriodStartdDate = resultSet.getString("PayPeriodStartDate");
                String pan = resultSet.getString("SSN");
                double earned_Gross = resultSet.getDouble("Earned_Gross_Pay");
                double earned_Net = resultSet.getDouble("Earned_Net_Paid");
                double taxableIncome = resultSet.getDouble("TaxableIncome");
                Ded_Income_Tax = resultSet.getDouble("Ded_Income_Tax");

                int empId = resultSet.getInt("EmpId");

                EmpName = resultSet.getString("EmpName");
                String reportsTo = "";
                map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("PAN_Number", pan);
                map.put("PayPeriodStartDate", payPeriodStartdDate);
                map.put("Employee_Name", EmpName);
                map.put("Earned_Gross", formatter.format(earned_Gross));
                map.put("Earned_Net", formatter.format(earned_Net));
                map.put("TaxableIncome", formatter.format(taxableIncome));
                map.put("Ded_Income_Tax", formatter.format(Ded_Income_Tax));


                finalList.add(map);
                j++;
            }
            fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "TDS_Report_For_" + monthSelected + "_" + yearSelected + ".xls");
            if (finalList.size() > 0) {

                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "TDS_Report_For_" + monthSelected + "_" + yearSelected + ".xls";


                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Employee PF Amount Report");

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



                HSSFDataFormat df = hssfworkbook.createDataFormat();
                HSSFRow row = sheet.createRow((short) 0);
                int cellCount = 8;
                HSSFCell cell[] = new HSSFCell[cellCount];
                for (int i = 0; i < cellCount; i++) {
                    cell[i] = row.createCell((short) i);
                }
                int cellval = 0;
                cell[cellval++].setCellValue("SNO");
                cell[cellval++].setCellValue("PayPeriodStartDate");
                cell[cellval++].setCellValue("Employee_Name");
                cell[cellval++].setCellValue("PAN Number");
                cell[cellval++].setCellValue("Gross Pay");
                cell[cellval++].setCellValue("Net Paid");
                cell[cellval++].setCellValue("Taxable Income");

                cell[cellval++].setCellValue("Ded Income Tax");

                for (int i = 0; i < cellCount; i++) {
                    cell[i].setCellStyle(headercs);
                }
                int count = 1;
                if (finalList.size() > 0) {
                    Map payrollLockMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        payrollLockMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        for (j = 0; j < cellCount; j++) {
                            cell[j] = row.createCell((short) j);
                        }

                        cellval = 0;
                        cell[cellval++].setCellValue((String) payrollLockMap.get("SNO"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("PayPeriodStartDate"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Employee_Name"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("PAN_Number"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Earned_Gross"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Earned_Net"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("TaxableIncome"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Ded_Income_Tax"));


                        for (j = 0; j < cellCount; j++) {
                            cell[j].setCellStyle(cs);
                        }
                    }

                    row = sheet.createRow((short) count++);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }


                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }
                    row = sheet.createRow((short) count + 2);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }
                    cell[0].setCellValue("");
                    cell[1].setCellValue("This Report is from");
                    cell[2].setCellValue(monthSelected + " month year " + year);

                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }

                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }


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
     //   System.out.println("path=" + filePath);
        return filePath;
    }

     public String tdsReportGeneration() {

        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            //For creating Excel grind from Search result Grid
            // System.out.println("StartDate" + getStartdate());
            //  System.out.println("EndDate" + getEnddate());
            // fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus());
            //setDepartmentId(getDepartmentId());
            //setEmpnameById(getEmpnameById());
            setYear(getYear());
            setMonth(getMonth());
            getBankName();
            getOrgName();
           
            //System.out.println("getBankName-->" + getBankName() + "getOrgName-->" + getOrgName());
  boolean exists = DataSourceDataProvider.getInstance().checkForPayrollDateForRunWages(month, Integer.parseInt(year),Integer.parseInt(getOrgName()));
            boolean checkLoadLeavesExists = DataSourceDataProvider.getInstance().checkLoadLeaves(month, Integer.parseInt(year),Integer.parseInt(getOrgName()));
           if (checkLoadLeavesExists) {
                if (!exists) {
            
            fileLocation = tdsReportGenerationList();
         //   System.out.println("fileLocation-------->" + fileLocation);
            if (!"".equals(fileLocation)) {
                httpServletResponse.setContentType("application/force-download");
                File file = new File(fileLocation);
                Date date = new Date();
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

                } else {
                    throw new FileNotFoundException("File not found");
                }

                inputStream.close();
                outputStream.close();

            } else {
                setResultMessage("No records exists !!");
               resultMessage = "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!";
                result = INPUT;

            }
               } else {
                     setResultMessage("Running of wages already done for this month");
                     resultMessage = "<font style='color:red;'>Running of wages already done for this month</font>";
                     
                  //  httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                result = INPUT;
                   
                }


            } else {
            //   httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                result = INPUT;
                  setResultMessage("Loading of leaves is not done for this month");
                resultMessage = "<font style='color:red;'>Loading of leaves is not done for this month</font>";
            }
//             System.out.println("OrgId ---- >"+getOrgName());
//                     System.out.println("getYearOverlay ---- >"+getYear());
//                      System.out.println("getMonthOverlay ---- >"+getMonth());
//                       System.out.println("getPaymentDate ---- >"+getNoOfDays());
                       // System.out.println("getPaymentDateEmp ---- >"+getPaymentDateEmp());
                        setYearOverlay(Integer.parseInt(getYear()));
                        setMonthOverlay(getMonth());
                        setOrgId(Integer.parseInt(getOrgName()));
                      setPaymentDateEmp(getPaymentDateEmp());
           httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
           
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
 
 public String tdsReportGenerationList() {
        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        
        Connection connection = null;
        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        CallableStatement callableStatement = null;
        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        HashMap map = null;
        List finalList = new ArrayList();
        try {
            File file = new File(Properties.getProperty("Payroll.Report.Path"));
            if (!file.exists()) {
                file.mkdirs();
            }
          //  System.out.println("test");
            FileOutputStream fileOut = null;
             
           connection = ConnectionProvider.getInstance().getConnection();
                    callableStatement = connection.prepareCall("{call spTDSGeneration(?,?,?,?,?,?,?,?,?) }");

                    callableStatement.setInt(1, month);
                    callableStatement.setInt(2, Integer.parseInt(year));
                    callableStatement.setInt(3, getNoOfDays());
                    callableStatement.setInt(4, getNoOfWeekendDays());
                    //  callableStatement.setInt(5, payrollAjaxHandlerAction.getNoOfHolidays());
                    callableStatement.setInt(5, getNoOfWorkingDays());
                  
                    

                //    System.out.println("1month----" + month);
               //     System.out.println("2year----" + year);
               //     System.out.println("3payrollAjaxHandlerAction.getNoOfDays()----" + payrollAjaxHandlerAction.getNoOfDays());
                //    System.out.println("4payrollAjaxHandlerAction.getNoOfWeekedDays()----" + payrollAjaxHandlerAction.getNoOfWeekedDays());
                //    System.out.println("5payrollAjaxHandlerAction.getNoOfWeekedDays()----" + payrollAjaxHandlerAction.getWorkingDays());
                //    System.out.println("6payrollAjaxHandlerAction.getCreatedBy()----" + payrollAjaxHandlerAction.getCreatedBy());
                    int yearForWageRun = Integer.parseInt(year);
                    int monthForWageRun = month;
                    String sdate = "";
                    String edate = "";

                    if (monthForWageRun < 4) {
                        sdate = (yearForWageRun - 1) + "-04-01";
                    //    System.out.println("sdate" + sdate);
                        edate = yearForWageRun + "-0" + (monthForWageRun + 1) + "-01";
                     //   System.out.println("edate" + edate);
                    } else {
                        sdate = (yearForWageRun) + "-04-01";
                     //   System.out.println("sdate" + sdate);
                        if (monthForWageRun <= 9) {
                            edate = (yearForWageRun) + "-0" + (monthForWageRun + 1) + "-01";
                       //     System.out.println("edate" + edate);
                        } else {

                            if (monthForWageRun == 12) {
                                edate = (yearForWageRun + 1) + "-" + (monthForWageRun - 11) + "-01";
                            } else {
                                edate = (yearForWageRun) + "-" + (monthForWageRun + 1) + "-01";
                            }
                        }
                    }
                     String monthSelected = "";
            int yearSelected = yearForWageRun;
 switch (monthForWageRun) {
                case 0:
                    monthSelected = "Jan";
                    break;
                case 1:
                    monthSelected = "Feb";
                    break;
                case 2:
                    monthSelected = "Mar";
                    break;
                case 3:
                    monthSelected = "Apr";
                    break;
                case 4:
                    monthSelected = "May";
                    break;
                case 5:
                    monthSelected = "June";
                    break;
                case 6:
                    monthSelected = "July";
                    break;
                case 7:
                    monthSelected = "Aug";
                    break;
                case 8:
                    monthSelected = "Sept";
                    break;
                case 9:
                    monthSelected = "Oct";
                    break;
                case 10:
                    monthSelected = "Nov";
                    break;
                case 11:
                    monthSelected = "Dec";
                    break;

            }
                    int earned_monthDiff = DataSourceDataProvider.getInstance().getMonthsBetweenDates(sdate, edate);
                    if (monthForWageRun <= 9) {
                        sdate = yearForWageRun + "-0" + (monthForWageRun) + "-01";// 2015-02-01
                      //  System.out.println("wagerunsdate" + sdate);
                    } else {
                        sdate = yearForWageRun + "-" + (monthForWageRun) + "-01";// 
                      //  System.out.println("wagerunsdate" + sdate);
                    }
                    if (monthForWageRun <= 03) {
                        edate = yearForWageRun + "-03-31"; //2015-03-01
                     //   System.out.println("wagerunsdate" + edate);
                    } else {
                        edate = (yearForWageRun + 1) + "-03-31";// 2016-03-01
                     //   System.out.println("wagerunsdate" + edate);
                    }
                    int projected_monthDiff = DataSourceDataProvider.getInstance().getMonthsBetweenDates(sdate, edate);
                   // System.out.println("EarnedMonths -->" + earned_monthDiff);
                   // System.out.println("ProjectedMonths -->" + projected_monthDiff);
                    callableStatement.setInt(6, projected_monthDiff);
                    callableStatement.setInt(7, earned_monthDiff);
                   
                     callableStatement.setInt(8,Integer.parseInt(getOrgName()));
                    callableStatement.registerOutParameter(9, Types.VARCHAR);

 int insertedRows = callableStatement.executeUpdate();

                String result=   callableStatement.getString(9);
              //  System.out.println("result---"+result);
                
            List teamList = null;
            int j = 1;
           
            String EmpName = "";
            String EmpNo="";
           
            double Ded_Income_Tax = 0.0;
            if(result.contains("*@!")){
           String rowsData[]=result.split(Pattern.quote("*@!"));
                for (int i = 0; i < rowsData.length; i++) {
                    String columnData[] = rowsData[i].split(Pattern.quote("#^$"));
                    
             
                NumberFormat formatter = new DecimalFormat("#0.00");
              
                String payPeriodStartdDate = columnData[0];
                   EmpNo = columnData[1];
                   EmpName = columnData[2];
                String pan = columnData[3];
               
              double taxableIncome= Double.parseDouble(columnData[4]);
              double tds= Double.parseDouble(columnData[5]);
              
              

               

             
                String reportsTo = "";
                map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("PAN_Number", pan);
                map.put("PayPeriodStartDate", payPeriodStartdDate);
                map.put("EmpNo", EmpNo);
                map.put("Employee_Name", EmpName);
                
              
                map.put("TaxableIncome", formatter.format(taxableIncome));
                map.put("tds", formatter.format(tds));


                finalList.add(map);
                j++;
            }
            }
            fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "TDS_Report_For_" + monthSelected + "_" + yearSelected + ".xls");
            if (finalList.size() > 0) {

                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "TDS_Report_For_" + monthSelected + "_" + yearSelected + ".xls";


                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("TDS Report");

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



                HSSFDataFormat df = hssfworkbook.createDataFormat();
                HSSFRow row = sheet.createRow((short) 0);
                int cellCount = 8;
                HSSFCell cell[] = new HSSFCell[cellCount];
                for (int i = 0; i < cellCount; i++) {
                    cell[i] = row.createCell((short) i);
                }
                int cellval = 0;
                cell[cellval++].setCellValue("S.No");
                cell[cellval++].setCellValue("PayPeriodStartDate");
                cell[cellval++].setCellValue("Emp Id");
                cell[cellval++].setCellValue("Name");
                cell[cellval++].setCellValue("PAN Number");
               
              
                cell[cellval++].setCellValue("Taxable Income");

                cell[cellval++].setCellValue("Ded Income Tax");

                for (int i = 0; i < cellCount; i++) {
                    cell[i].setCellStyle(headercs);
                }
                int count = 1;
                if (finalList.size() > 0) {
                    Map payrollLockMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        payrollLockMap = (Map) finalList.get(i);
                        row = sheet.createRow((short) count++);
                        for (j = 0; j < cellCount; j++) {
                            cell[j] = row.createCell((short) j);
                        }
                
              

                        cellval = 0;
                        cell[cellval++].setCellValue((String) payrollLockMap.get("SNO"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("PayPeriodStartDate"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("EmpNo"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("Employee_Name"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("PAN_Number"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("TaxableIncome"));
                        cell[cellval++].setCellValue((String) payrollLockMap.get("tds"));


                        for (j = 0; j < cellCount; j++) {
                            cell[j].setCellStyle(cs);
                        }
                    }

                    row = sheet.createRow((short) count++);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }


                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }
                    row = sheet.createRow((short) count + 2);
                    for (j = 0; j < cellCount; j++) {
                        cell[j] = row.createCell((short) j);
                    }
//                    cell[0].setCellValue("");
//                    cell[1].setCellValue("This Report is from");
//                    cell[2].setCellValue(monthSelected + " month year " + year);

                    for (j = 0; j < cellCount; j++) {
                        cell[j].setCellStyle(footercs);
                    }

                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                hssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }


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

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
     //   System.out.println("path=" + filePath);
        return filePath;
    }
    /*
  * Tax savings generaated
  */
    
   public String generateTaxSaving() {

        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            //For creating Excel grind from Search result Grid
            // System.out.println("StartDate" + getStartdate());
            //  System.out.println("EndDate" + getEnddate());
            // fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus());
            //setDepartmentId(getDepartmentId());
            //setEmpnameById(getEmpnameById());
            setYear(getYear());
            setMonth(getMonth());
            getBankName();
            getOrgName();
            //System.out.println("getBankName-->" + getBankName() + "getOrgName-->" + getOrgName());

            fileLocation = generateTaxSavings(getOrgId(), Integer.parseInt(getYear()), getMonth());
         //   System.out.println("fileLocation-------->" + fileLocation);
            if (!"".equals(fileLocation)) {
                httpServletResponse.setContentType("application/force-download");
                File file = new File(fileLocation);
                Date date = new Date();
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

                } else {
                    throw new FileNotFoundException("File not found");
                }

                inputStream.close();
                outputStream.close();

            } else {
                setResultMessage("No records exists !!");
               // httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                String resultMessage = "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>";
                httpServletRequest.getSession(false).setAttribute("taxSavingsResultMessage",resultMessage);
             
                     
                   //  System.out.println(resultMessage+"---->setBankRepotFlag"+getBankReportFlag());
                    
                result = INPUT;

            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
   
   
     public String generateTaxSavings(int orgId, int year, int month) {


        DateUtility dateutility = new DateUtility();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;


        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null;



        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;

        HashMap map = null;

        List finalList = new ArrayList();
        try {


            File file = new File(Properties.getProperty("Payroll.Report.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }
          //  System.out.println("test");
            FileOutputStream fileOut = null;
            String orgName = "";
         if (orgId == 104124) {
            orgName = "MSS";
        } else if (orgId == 116866) {
            orgName = "CNE";
        
        } else if (orgId == 117009) {
            orgName = "ITL";
        }
String monthName="";
           if(month==1) monthName="January";
        else if(month==2) monthName="February";
        else if(month==3) monthName="March";
        else if(month==4) monthName="April";
        else if(month==5) monthName="May";
        else if(month==6) monthName="June";
        else if(month==7) monthName="July";
        else if(month==8) monthName="August";
        else if(month==9) monthName="September";
        else if(month==10) monthName="October";
        else if(month==11) monthName="November";
        else if(month==12) monthName="December";
         
            fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download")+"Cosilidated_" + orgName + "_"+monthName+year+".xls");

            //fileOut  = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "BankReport.xls");

            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
String FinancialYear="";

  if (month > 3) {
                   
                   
                    FinancialYear="April" + year + "-March" + (year + 1);
                  
                } else {
                    FinancialYear="April" + (year-1) + "-March" + (year);
                   
                }
     
            
                        
            query="SELECT EmpId,EmpNo,CONCAT(Fname,' ',Mname,'.',Lname) AS empName,"+

"SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 1 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
                    "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS LIC ,"+
"SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 2 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
                   " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS HouseLoanPrinciple,"+
"SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 3 AND tblEmpTaxExemptionDetails.STATUS='Approved')"+
                    "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS NSC,"+
"SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 4 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
                     "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS PPF, "+
"SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 5 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
                     "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS TuitionFee, "+
"SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 6 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
                     "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS ELSS, "+
"SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 7 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
                     "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS POTD ,"+
"SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 8 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
                     "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS FixedDeposit ,"+
"SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 10 AND tblEmpTaxExemptionDetails.STATUS='Approved') "+
                    " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS Sukanya, "+
 "SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 12 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "+
                     "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS NPS ,"+
"SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 14 AND tblEmpTaxExemptionDetails.STATUS='Approved')  "+
                     "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS HousingLoanInterest, "+
"SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 15 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "+
                    " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS InsuranceOnParents, "+
"SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 16 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "+
                     "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS InsurenceOnOther, "+
"SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 17 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "+
                     "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS InterestOnEdu, "+
"SUM(CASE WHEN ((tblEmpTaxExemptionDetails.`ExemptionId` = 18 AND tblEmpTaxExemptionDetails.STATUS='Approved')) "+
                     "THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS HouseRent "+

"FROM tblEmpTaxExemptionDetails LEFT JOIN tblEmployee ON (tblEmpTaxExemptionDetails.EmpId=tblEmployee.Id) WHERE IsActive='Active'  "
                    + "AND tblEmpTaxExemptionDetails.OrgId=? AND FinancialYear=? GROUP BY tblEmpTaxExemptionDetails.EmpId " 
                    +"HAVING (LIC>0 OR HouseLoanPrinciple>0 OR NSC>0 OR PPF>0 OR TuitionFee>0 OR ELSS>0 "
                    +"OR POTD>0 OR FixedDeposit>0 OR Sukanya>0 OR NPS>0 OR HousingLoanInterest>0 "
                    +"OR InsuranceOnParents>0 OR InsurenceOnOther>0 OR InterestOnEdu>0 OR HouseRent>0 )";
 

               // System.out.println("query "+query);
               // System.out.println("orgId "+orgId);
               // System.out.println("FinancialYear "+FinancialYear);

        

            

            int j = 1;
            preStmt = connection.prepareStatement(query);
preStmt.setInt(1, orgId);
preStmt.setString(2, FinancialYear);
            resultSet = preStmt.executeQuery();
            String bank_Name = "";
            NumberFormat formatter = new DecimalFormat("#0.00");
            while (resultSet.next()) {


                int EmpNo = resultSet.getInt("EmpNo");
                int EmpId = resultSet.getInt("EmpId");
              
                String empName = resultSet.getString("empName");
                double TuitionFee = resultSet.getDouble("TuitionFee");
                  double NSC = resultSet.getDouble("NSC");
                double LIC = resultSet.getDouble("LIC");
                  double Sukanya = resultSet.getDouble("Sukanya");
                   double FixedDeposit = resultSet.getDouble("FixedDeposit");
                   
                double HouseLoanPrinciple = resultSet.getDouble("HouseLoanPrinciple");
               double HousingLoanInterest = resultSet.getDouble("HousingLoanInterest");
                double MadicalInsurence = resultSet.getDouble("InsurenceOnOther");
                 double MutualFunds = resultSet.getDouble("ELSS");
                  double EDULoan = resultSet.getDouble("InterestOnEdu");
                double PPF = resultSet.getDouble("PPF");
                
               
                double POTD = resultSet.getDouble("POTD");
               
              
                double NPS = resultSet.getDouble("NPS");
               
                double InsuranceOnParents = resultSet.getDouble("InsuranceOnParents");
               
               
                double HouseRent = resultSet.getDouble("HouseRent");
               
                
               
             
                String reportsTo = "";

                // String Description      = timeSheetStatus;
                map = new HashMap();
                //   System.out.println("innn_->"+EmpName+" -"+Description+" "+WorkDate+" "+P1Hrs+" "+P2Hrs);
                map.put("SNO", String.valueOf(j));
                map.put("EmpNo", EmpNo);
             
                map.put("Name", empName);
                map.put("TuitionFee", TuitionFee);
                map.put("NSC", NSC);
                map.put("LIC", LIC);
                map.put("Sukanya", Sukanya);
                map.put("FixedDeposit", FixedDeposit);
                map.put("HouseLoanPrinciple", HouseLoanPrinciple);
                map.put("HousingLoanInterest", HousingLoanInterest);
                map.put("MadicalInsurence", MadicalInsurence);
                map.put("MutualFunds", MutualFunds);
                map.put("EDULoan", EDULoan);
                map.put("PPF", PPF);
                map.put("POTD", POTD);
                map.put("NPS", NPS);
                map.put("InsuranceOnParents", InsuranceOnParents);
                
                if(HouseRent>0) {
                   HouseRent= DataSourceDataProvider.getInstance().getHRExemptionYearly(EmpId, month, year);
                }
                    map.put("HouseRent", HouseRent);
                
                        
                
                map.put("comments", "");
                
                

              
                finalList.add(map);
                j++;
            }

            double totalNetPaid = 0.0;
            if (finalList.size() > 0) {

          
                                                    
                filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download")+"Cosilidated_" + orgName + "_"+monthName+year+".xls";

                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet worksheet = workbook.createSheet("Savings");
  HSSFRow row1;
                for (int i = 0; i < 18; i++) {
                    worksheet.setColumnWidth(i, 10 * 256);
                   
                }
                HSSFCellStyle cs = workbook.createCellStyle();
                cs.setFillForegroundColor(HSSFColor.WHITE.index);
                cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cs1 = workbook.createCellStyle();

                cs1.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cs1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border



                HSSFCellStyle headercs = workbook.createCellStyle();
                headercs.setFillForegroundColor(HSSFColor.AQUA.index);
                headercs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                headercs.setBorderTop((short) 1); // single line border
                headercs.setBorderBottom((short) 1); // single line border

                HSSFFont timesBoldFont = workbook.createFont();
                timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                timesBoldFont.setColor(HSSFColor.WHITE.index);
                timesBoldFont.setFontName("Arial");
                headercs.setFont(timesBoldFont);

                HSSFFont footerFont = workbook.createFont();
                footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                footerFont.setFontName("Arial");

                HSSFCellStyle footercs = workbook.createCellStyle();
                footercs.setFont(footerFont);
                footercs.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
                footercs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                footercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs.setBorderTop((short) 1); // single line border
                footercs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle footercs1 = workbook.createCellStyle();
                footercs1.setFont(footerFont);
                footercs1.setFillForegroundColor(HSSFColor.YELLOW.index);
                footercs1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                footercs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs1.setBorderTop((short) 1); // single line border
                footercs1.setBorderBottom((short) 1); // single line border
                
                 HSSFCellStyle footercs2 = workbook.createCellStyle();
                footercs2.setFont(footerFont);
                footercs2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
               // footercs2.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
                 footercs2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                footercs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs2.setBorderTop((short) 1); // single line border
                footercs2.setBorderBottom((short) 1); // single line border

                // HSSFRow row1;
                // LogisticsDocBean logisticsDocBean = null;
                // index from 0,0... cell A1 is cell(0,0)

                // if(list.size()!=0){//
                //System.out.println("list size-->"+list.size());
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                HSSFCellStyle cellStyle1 = workbook.createCellStyle();
                HSSFCellStyle cellStyle2 = workbook.createCellStyle();
                HSSFCellStyle cellStyle3 = workbook.createCellStyle();
                HSSFCellStyle cellStyleHead = workbook.createCellStyle();
                HSSFFont font1 = workbook.createFont();
                HSSFFont font2 = workbook.createFont();
                HSSFFont font3 = workbook.createFont();
                HSSFFont font4 = workbook.createFont();
                HSSFFont fontHead = workbook.createFont();
                fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                // fontHead.setFontHeightInPoints((short)15);  //for font Size
                font4.setColor(HSSFColor.BLACK.index);

                cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

                cellStyle.setFont(font4);
                //start	
                Date date = new Date();
                row1 = worksheet.createRow((short) 0);

                HSSFCell cellpo0 = row1.createCell((short) 0);
                // cellpo0.setCellValue("Purchase Order");
                HSSFCell cellpo1 = row1.createCell((short) 1);
                HSSFCell cellpo2 = row1.createCell((short) 2);
                // cellpo2.setCellValue("Created Date");
                HSSFCell cellpo3 = row1.createCell((short) 3);
                //cellpo3.setCellValue((date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());

                HSSFCell cellpo4 = row1.createCell((short) 4);
                HSSFCell cellpo5 = row1.createCell((short) 5);
                HSSFCell cellpo6 = row1.createCell((short) 6);
                HSSFCell cellpo7 = row1.createCell((short) 7);
                HSSFCell cellpo8 = row1.createCell((short) 8);
                HSSFCell cellpo9 = row1.createCell((short) 9);
                HSSFCell cellpo10 = row1.createCell((short) 10);
                HSSFCell cellpo11 = row1.createCell((short) 11);
                row1 = worksheet.createRow((short) 0);
                Cell cell[] = new Cell[18];
                for (int i = 0; i < 18; i++) {
                    cell[i] = row1.createCell((short) i);
                }



             //   worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:AP1"));

                //sno
                row1 = worksheet.createRow((short) 0);
                cell[0] = row1.createCell((short) 0);
                cell[0].setCellValue("Empl.Id");
                cellStyleHead.setFont(fontHead);
                
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               // cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(footercs2);
                
                worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:A2"));

                cell[0] = row1.createCell((short) 1);
                cell[0].setCellValue("Employee Name");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               // cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B1:B2"));

                cell[0] = row1.createCell((short) 2);
                cell[0].setCellValue("Tution Fees");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("C1:C2"));

                cell[0] = row1.createCell((short) 3);
                cell[0].setCellValue("NSC");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
               // cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("D1:D2"));

                cell[0] = row1.createCell((short) 4);
                cell[0].setCellValue("Life Ins.  Premium");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("E1:E2"));

                cell[0] = row1.createCell((short) 5);
                cell[0].setCellValue("Sukanya Samriddhi Yojana");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("F1:F2"));

                cell[0] = row1.createCell((short) 6);
                cell[0].setCellValue("FD - Tax Saving");
                
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("G1:G2"));

                cell[0] = row1.createCell((short) 7);
                cell[0].setCellValue("HB Loan Priciple");
                
                
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("H1:H2"));

                cell[0] = row1.createCell((short) 8);
                cell[0].setCellValue("HB Loan  Interest");
              
                
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("I1:I2"));

                cell[0] = row1.createCell((short) 9);
                  cell[0].setCellValue("Medical Ins.");
               
                
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("J1:J2"));
                
                cell[0] = row1.createCell((short) 10);
                 cell[0].setCellValue("Mutual Funds");
               
               
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("K1:K2"));
                
                cell[0] = row1.createCell((short) 11);
                 cell[0].setCellValue("Edu.Loan Int.");
               
              
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("L1:L2"));

                cell[0] = row1.createCell((short) 12);
                  cell[0].setCellValue("PPF");
                 
                
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("M1:M2"));
                
                
                 cell[0] = row1.createCell((short) 13);
                
                  cell[0].setCellValue("RentReceipts");
                
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("N1:N2"));
                
                cell[0] = row1.createCell((short) 14);
                
                  cell[0].setCellValue("NPS");
                  
                   cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("O1:O2"));
                
                cell[0] = row1.createCell((short) 15);
                
                  cell[0].setCellValue("Insurance for parents-80D");
                  
                   cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("P1:P2"));
                
                cell[0] = row1.createCell((short) 16);
                
                  cell[0].setCellValue("Post Office time Deposit (POTD)-80C");
                  
                   cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("Q1:Q2"));
                
                cell[0] = row1.createCell((short) 17);
                
                  cell[0].setCellValue("Comment");
                
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(footercs2);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("R1:R2"));
              
               


             
//               
               int count = 2;
                //   while (resultSet.next()) {
                if (finalList.size() > 0) {
                    Map taxSavings = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        taxSavings = (Map) finalList.get(i);
                        row1 = worksheet.createRow((short) count++);
                        // row1 = sheet.createRow((short) count++);
                        for (int c = 0; c < 18; c++) {
                            cell[c] = row1.createCell((short) c);
                        }

                       

                        
                        cell[0].setCellValue((Integer) taxSavings.get("EmpNo"));
                        cell[1].setCellValue((String) taxSavings.get("Name"));
                        cell[2].setCellValue((Double) taxSavings.get("TuitionFee"));
                        cell[3].setCellValue((Double) taxSavings.get("NSC"));
                        cell[4].setCellValue((Double) taxSavings.get("LIC"));
                        cell[5].setCellValue((Double) taxSavings.get("Sukanya"));

                        cell[6].setCellValue((Double) taxSavings.get("FixedDeposit"));
                        cell[7].setCellValue((Double) taxSavings.get("HouseLoanPrinciple"));
                        cell[8].setCellValue((Double) taxSavings.get("HousingLoanInterest"));
                        cell[9].setCellValue((Double) taxSavings.get("MadicalInsurence"));

                        cell[10].setCellValue((Double) taxSavings.get("MutualFunds"));
                        cell[11].setCellValue((Double) taxSavings.get("EDULoan"));
                        cell[12].setCellValue((Double) taxSavings.get("PPF"));
                        cell[13].setCellValue((Double) taxSavings.get("HouseRent"));
                        cell[14].setCellValue((Double) taxSavings.get("NPS"));
                        cell[15].setCellValue((Double) taxSavings.get("InsuranceOnParents"));
                        cell[16].setCellValue((Double) taxSavings.get("POTD"));
                        cell[17].setCellValue((String) taxSavings.get("comments"));
                        
                       
                        

                        




                        for (int h = 0; h < 18; h++) {
                            if (count % 2 == 0) {
                                cell[h].setCellStyle(cs);
                            } else {
                                cell[h].setCellStyle(cs1);
                            }
                        worksheet.setColumnWidth(h, 15 * 256);
                        }
                        


                    }
               
                worksheet.autoSizeColumn((short) 1);
                
                workbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }

            }
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
       // System.out.println("path=" + filePath);
        return filePath;

    }
     
      public String downloadTefAttachmentForm12BB() throws Exception {
        result = SUCCESS;
        String responseString = "";
        try {
            httpServletRequest.getSession(false).removeAttribute("resultMessage");
            String fileLocation = "";
            setTefId(getTefId());
            setEmpId(getEmpId());
            setPayRollId(DataSourceDataProvider.getInstance().getPayrollIdByEmpId(getEmpId()));
           String fileDetails = DataSourceDataProvider.getInstance().getAttachmentDetailsForm12BB(getTefId());
           fileLocation = fileDetails.split("#")[1];
        //   System.out.println("fileLocation()-------->" + fileLocation);
            
            if (!"".equals(fileLocation) && !"null".equals(fileLocation) && fileLocation != null && fileLocation.length() != 0) {
                httpServletResponse.setContentType("application/force-download");
                // File file = new File(Properties.getProperty("mscvp.docCreationPath")+"SearchedExcelDocument.xls");
                //   System.out.println("fileLocation-->"+fileLocation);
                File file = new File(fileLocation);
                Date date = new Date();

                //fileName = (date.getYear() + 1900) + "_" + (date.getMonth() + 1) + "_" + date.getDate() + "_" + file.getName();
                // fileName = getStartdate().substring(0,10) +"To"+ getEnddate().substring(0,10) +"_"+getLeaveType()+"_"+file.getName();
                 if (file.exists()) {
                fileName = file.getName();
              
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
                    // responseString = "downLoaded!!";

                    //  httpServletResponse.setContentType(getDownloadType());
                    // httpServletResponse.getWriter().write(responseString);
  inputStream.close();
                outputStream.close();
                } else {
                    // System.out.println("in else");
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No Attachment exists !!</font>");
                if(getFromTef().equals("1")){
                    result = "tef";
                }else{
                result = INPUT;
                }
                }

              

            } else {
               // System.out.println("in else");
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No Attachment exists !!</font>");
                if(getFromTef().equals("1")){
                    result = "tef";
                }else{
                result = INPUT;
                }

            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadExcelPayrollReport.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

     
     public String getLocationAvailable() {
        return locationAvailable;
    }

    public void setLocationAvailable(String locationAvailable) {
        this.locationAvailable = locationAvailable;
    }

    public String getAttachmentLocation() {
        return attachmentLocation;
    }

    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmpnameById() {
        return empnameById;
    }

    public void setEmpnameById(String empnameById) {
        this.empnameById = empnameById;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getTefId() {
        return tefId;
    }

    public void setTefId(int tefId) {
        this.tefId = tefId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getPayRollId() {
        return payRollId;
    }

    public void setPayRollId(int payRollId) {
        this.payRollId = payRollId;
    }

    public String getTempFlag() {
        return tempFlag;
    }

    public void setTempFlag(String tempFlag) {
        this.tempFlag = tempFlag;
    }

    public String getReportFor() {
        return reportFor;
    }

    public void setReportFor(String reportFor) {
        this.reportFor = reportFor;
    }

    /**
     * @return the doRepaymentFlag
     */
    public int getDoRepaymentFlag() {
        return doRepaymentFlag;
    }

    /**
     * @param doRepaymentFlag the doRepaymentFlag to set
     */
    public void setDoRepaymentFlag(int doRepaymentFlag) {
        this.doRepaymentFlag = doRepaymentFlag;
    }

    /**
     * @return the bankReportFlag
     */
    public int getBankReportFlag() {
        return bankReportFlag;
    }

    /**
     * @param bankReportFlag the bankReportFlag to set
     */
    public void setBankReportFlag(int bankReportFlag) {
        this.bankReportFlag = bankReportFlag;
    }

    /**
     * @return the bankReportsResponse
     */
    public String getBankReportsResponse() {
        return bankReportsResponse;
    }

    /**
     * @param bankReportsResponse the bankReportsResponse to set
     */
    public void setBankReportsResponse(String bankReportsResponse) {
        this.bankReportsResponse = bankReportsResponse;
    }

    /**
     * @return the empNo
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     * @param empNo the empNo to set
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * @return the noOfDays
     */
    public int getNoOfDays() {
        return noOfDays;
    }

    /**
     * @param noOfDays the noOfDays to set
     */
    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    /**
     * @return the noOfWeekendDays
     */
    public int getNoOfWeekendDays() {
        return noOfWeekendDays;
    }

    /**
     * @param noOfWeekendDays the noOfWeekendDays to set
     */
    public void setNoOfWeekendDays(int noOfWeekendDays) {
        this.noOfWeekendDays = noOfWeekendDays;
    }

    /**
     * @return the noOfWorkingDays
     */
    public int getNoOfWorkingDays() {
        return noOfWorkingDays;
    }

    /**
     * @param noOfWorkingDays the noOfWorkingDays to set
     */
    public void setNoOfWorkingDays(int noOfWorkingDays) {
        this.noOfWorkingDays = noOfWorkingDays;
    }

    /**
     * @return the yearOverlay
     */
    public int getYearOverlay() {
        return yearOverlay;
    }

    /**
     * @param yearOverlay the yearOverlay to set
     */
    public void setYearOverlay(int yearOverlay) {
        this.yearOverlay = yearOverlay;
    }

    /**
     * @return the monthOverlay
     */
    public int getMonthOverlay() {
        return monthOverlay;
    }

    /**
     * @param monthOverlay the monthOverlay to set
     */
    public void setMonthOverlay(int monthOverlay) {
        this.monthOverlay = monthOverlay;
    }

    /**
     * @return the orgId
     */
    public int getOrgId() {
        return orgId;
    }

    /**
     * @param orgId the orgId to set
     */
    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    /**
     * @return the paymentDate
     */
    public String getPaymentDateEmp() {
        return paymentDateEmp;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDateEmp(String paymentDateEmp) {
        this.paymentDateEmp = paymentDateEmp;
    }

    /**
     * @return the fromTef
     */
    public String getFromTef() {
        return fromTef;
    }

    /**
     * @param fromTef the fromTef to set
     */
    public void setFromTef(String fromTef) {
        this.fromTef = fromTef;
    }
}

