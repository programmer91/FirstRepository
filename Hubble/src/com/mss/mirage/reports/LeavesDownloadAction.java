/*******************************************************************************
 *
 * Project : HubbleV2.0
 *
 * Package : com.mss.mirage.reports
 *
 * Date    : Nov 21, 2013 5:22:19 pm
 *
 * Author  : Prasad Kandregula <vkandregula@miraclesoft.com>
 *
 * File    : LeavesDownloadAction.java
 *
 * 
 * *****************************************************************************
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.reports;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
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
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 *
 * @author Ajay Tummapala <atummapala@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class LeavesDownloadAction implements
        Action, ServletRequestAware, ServletResponseAware {

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

    /** Creates a new instance of DownloadAction */
    public LeavesDownloadAction() {
    }

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
            System.out.println("StartDate" + getStartdate());
            System.out.println("EndDate" + getEnddate());
            fileLocation = generateEmpLeavesList(getFname(), getLname(), getCountry(), getStartdate(), getEnddate(), getLeaveType());

            httpServletResponse.setContentType("application/force-download");
            // File file = new File(Properties.getProperty("mscvp.docCreationPath")+"SearchedExcelDocument.xls");
            File file = new File(fileLocation);
            Date date = new Date();

            //fileName = (date.getYear() + 1900) + "_" + (date.getMonth() + 1) + "_" + date.getDate() + "_" + file.getName();
            fileName = getStartdate().substring(0,10) +"To"+ getEnddate().substring(0,10) +"_"+getLeaveType()+"_"+file.getName();
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
                 
                httpServletResponse.setContentType(getDownloadType());
                httpServletResponse.getWriter().write(responseString);
               
            } else {
                throw new FileNotFoundException("File not found");
            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(LeavesDownloadAction.class.getName()).log(Level.SEVERE, null, ex1);
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
    public String generateEmpLeavesList(String fname, String lname, String country, String sDate, String eDate, String leaveType) {
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
        try {
            File file = new File(Properties.getProperty("ExamKeys.Path"));
            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "LeavesList.xls");
            filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + "LeavesList.xls";
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            connection = ConnectionProvider.getInstance().getConnection();
            /*preStmt = connection.prepareStatement("SELECT tblEcertValidatorKeys.VKey AS Vkey,tblEcertTopics.TopicName AS NAME, " +
            "tblEcertValidatorKeys.Duration AS Duration,tblEcertValidatorKeys.MinMarks AS minmarks," +
            "tblEcertValidatorKeys.NoOfQuestions AS COUNT FROM " +
            "tblEcertValidatorKeys LEFT OUTER JOIN tblEcertTopics ON " +
            "(tblEcertTopics.ID=tblEcertValidatorKeys.TopicId)" +
            "  WHERE tblEcertValidatorKeys.STATUS='Active' AND tblEcertValidatorKeys.TopicId = ? AND tblEcertValidatorKeys.CreatedBy='"+userId+"'");*/
            String startDate = dateutility.convertStringToMySQLDate(sDate);
            String endDate = dateutility.convertStringToMySQLDate(eDate);

              if(leaveType.equalsIgnoreCase("All"))
              {
             sb = new StringBuffer("SELECT EmpNo,loginId AS EmpName,emp.reportsTo AS reportsTo,"
                    + "empLeave.STATUS,leaveType,empLeave.StartDate,empLeave.EndDate "
                    + "FROM tblEmpLeaves empLeave,tblEmployee emp WHERE empLeave.EmpId=emp.Id "
                    + "AND  emp.Country LIKE '" + country + "' AND empLeave.StartDate >='" + startDate + "%'AND "
                    + "empLeave.EndDate <='" + endDate + "%' AND leaveType LIKE '%'");
              }
              
              else
              {
                 sb = new StringBuffer("SELECT EmpNo,loginId AS EmpName,emp.reportsTo AS reportsTo,"
                    + "empLeave.STATUS,leaveType,empLeave.StartDate,empLeave.EndDate "
                    + "FROM tblEmpLeaves empLeave,tblEmployee emp WHERE empLeave.EmpId=emp.Id "
                    + "AND  emp.Country LIKE '" + country + "' AND empLeave.StartDate >='" + startDate + "%'AND "
                    + "empLeave.EndDate <='" + endDate + "%' AND leaveType='" + leaveType + "'");  
              }
            String qs = sb.toString();
            if (!fname.equals("")) {
                qs = qs + "and emp.FNAME like '%" + fname + "%' ";
            }
            if (!lname.equals("")) {
                qs = qs + "and emp.LNAME like '" + lname + "%' ";
            }
            qs = qs + "ORDER BY EmpNo";
            preStmt = connection.prepareStatement(qs);

            System.out.println("Query String-->" + qs);

            //preStmt.setInt(1,id);
            /*preStmt.setString( , );
            preStmt.setString( , );
            preStmt.setString( , );
            preStmt.setDTimestamp(parameterIndex, null);
            preStmt.setString( , );
            preStmt.setInt(1,topicId);*/


            resultSet = preStmt.executeQuery();

            HSSFWorkbook hssfworkbook = new HSSFWorkbook();
            HSSFSheet sheet = hssfworkbook.createSheet("Employee Leaves");

            HSSFCellStyle cs = hssfworkbook.createCellStyle();
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
            cell.setCellValue("EmpNo");
            cell1.setCellValue("EmpName");
            cell2.setCellValue("ReportsTo");
            cell3.setCellValue("Status");
            cell4.setCellValue("LeaveType");
            cell5.setCellValue("StartDate");
            cell6.setCellValue("EndDate");
            cell7.setCellValue("Leaves");

            cell.setCellStyle(cs);
            cell1.setCellStyle(cs);
            cell2.setCellStyle(cs);
            cell3.setCellStyle(cs);
            cell4.setCellStyle(cs);
            cell5.setCellStyle(cs);
            cell6.setCellStyle(cs);
            cell7.setCellStyle(cs);
            int count = 1;
            while (resultSet.next()) {

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
                String empNo=resultSet.getString("EmpNo");
                String empName=resultSet.getString("EmpName");
                String reportsTo=resultSet.getString("reportsTo");
                String status=resultSet.getString("Status");
                
                String lType=resultSet.getString("leaveType");
                String stDate=resultSet.getString("StartDate");
                String enDate=resultSet.getString("EndDate");
                
                int workDays=0;
                
                /*cell.setCellValue(resultSet.getString("EmpNo"));
                cell1.setCellValue(resultSet.getString("EmpName"));
                cell2.setCellValue(resultSet.getString("reportsTo"));
                cell3.setCellValue(resultSet.getString("Status"));
                cell4.setCellValue(resultSet.getString("leaveType"));
                cell5.setCellValue(resultSet.getString("StartDate"));
                cell6.setCellValue(resultSet.getString("EndDate"));*/
                
                
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                 try{
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(format.parse(stDate));
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(format.parse(enDate));
         workDays=getdays(cal1.getTime(),cal2.getTime());
        //System.out.println("Total working Days = "+workDays);
    }
    catch(ParseException ex)
    {ex.printStackTrace();
    }
                cell.setCellValue(empNo);
                cell1.setCellValue(empName);
                cell2.setCellValue(reportsTo);
                cell3.setCellValue(status);
                cell4.setCellValue(lType);
                cell5.setCellValue(stDate);
                cell6.setCellValue(enDate);
                cell7.setCellValue(workDays);
                 


                cell.setCellStyle(cs);
                cell1.setCellStyle(cs);
                cell2.setCellStyle(cs);
                cell3.setCellStyle(cs);
                cell4.setCellStyle(cs);
                cell5.setCellStyle(cs);
                cell6.setCellStyle(cs);
                cell7.setCellStyle(cs);

            }
            sheet.autoSizeColumn((short) 0);
            sheet.autoSizeColumn((short) 1);
            sheet.autoSizeColumn((short) 2);
            sheet.autoSizeColumn((short) 3);
            sheet.autoSizeColumn((short) 4);
            sheet.autoSizeColumn((short) 5);
            sheet.autoSizeColumn((short) 6);
            sheet.autoSizeColumn((short) 7);
            hssfworkbook.write(fileOut);
            fileOut.flush();
            fileOut.close();





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
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
    public String logisticsDocExcelDownload() {
    String filePath = "";
    try {
    java.util.List list = (java.util.List) httpServletRequest.getSession(false).getAttribute("");
    
    File file = new File(Properties.getProperty("mscvp.logisticsDocCreationPath"));
    if(!file.exists())
    file.mkdirs();
    
    
    //SearchedDocument.xls
    //FileOutputStream fileOut = new FileOutputStream("C:\\docExcel.xls");
    //for linux
    // FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath()+"/Payment.xls");
    
    //filePath = file.getAbsolutePath()+"/Payment.xls";
    
    //for XP
    FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath()+Properties.getProperty("os.compatability")+"logisticsDoc.xls");
    filePath = file.getAbsolutePath()+Properties.getProperty("OS.Compatabliliy.Download")+"ExamKeysList.xls";
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet worksheet = workbook.createSheet("logisticsDoc");
    HSSFRow row1;
    
    // index from 0,0... cell A1 is cell(0,0)
    
    if(list.size()!=0){
    
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
    font4.setColor(HSSFColor.WHITE.index);
    
    cellStyle.setFillForegroundColor(HSSFColor.BLACK.index);
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
    //            HSSFCell cellpo8 = row1.createCell((short) 8);
    row1 = worksheet.createRow((short) 1);
    Cell cell = row1.createCell((short) 1);
    cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
    cellStyleHead.setFont(fontHead);
    cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    cell.setCellStyle(cellStyleHead);
    worksheet.addMergedRegion(CellRangeAddress.valueOf("B2:F2"));
    
    
    
    
    //end
    row1 = worksheet.createRow((short) 3);
    
    HSSFCell cella1 = row1.createCell((short) 0);
    cella1.setCellValue("FileFormat");
    cella1.setCellStyle(cellStyle);
    //HSSFCellStyle cellStyle = workbook.createCellStyle();
    
    HSSFCell cellb1 = row1.createCell((short) 1);
    cellb1.setCellValue("InstanceId");
    cellb1.setCellStyle(cellStyle);
    HSSFCell cellc1 = row1.createCell((short) 2);
    cellc1.setCellValue("Partner");
    cellc1.setCellStyle(cellStyle);
    HSSFCell celld1 = row1.createCell((short) 3);
    celld1.setCellValue("DateTime");
    celld1.setCellStyle(cellStyle);
    HSSFCell celle1 = row1.createCell((short) 4);
    celle1.setCellValue("TransType");
    celle1.setCellStyle(cellStyle);
    HSSFCell cellf1 = row1.createCell((short) 5);
    cellf1.setCellValue("Direction");
    cellf1.setCellStyle(cellStyle);
    HSSFCell cellg1 = row1.createCell((short) 6);
    cellg1.setCellValue("Status");
    cellg1.setCellStyle(cellStyle);
    HSSFCell cellh1 = row1.createCell((short) 7);
    cellh1.setCellValue("Reprocess");
    cellh1.setCellStyle(cellStyle);
    //             HSSFCell celli1 = row1.createCell((short) 8);
    //             celli1.setCellValue("Ack Status");
    //             celli1.setCellStyle(cellStyle);
    
    for (int i = 0; i < list.size(); i++) {
    // logisticsDocBean = (LogisticsDocBean) list.get(i);
    
    row1 = worksheet.createRow((short) i+4);
    
    HSSFCell cellA1 = row1.createCell((short) 0);
    
    //   cellA1.setCellValue(logisticsDocBean.getFile_type());  
    
    
    HSSFCell cellB1 = row1.createCell((short) 1);
    
    //   cellB1.setCellValue(logisticsDocBean.getFile_id());  
    HSSFCell cellC1 = row1.createCell((short) 2);
    
    //   cellC1.setCellValue(logisticsDocBean.getPname());  
    
    HSSFCell cellD1 = row1.createCell((short) 3);
    
    //   cellD1.setCellValue(logisticsDocBean.getDate_time_rec().toString().substring(0, logisticsDocBean.getDate_time_rec().toString().lastIndexOf(":")));  
    HSSFCell cellE1 = row1.createCell((short) 4);
    
    //   cellE1.setCellValue(logisticsDocBean.getTransaction_type());  
    
    HSSFCell cellF1 = row1.createCell((short) 5);
    //  cellF1.setCellValue(logisticsDocBean.getDirection());  
    //                          HSSFCell cellG1 = row1.createCell((short) 6);
    //                        cellG1.setCellValue(logisticsDocBean.getCheckAmount()); 
    
    // cellF1.setCellValue(shipmentBean.getStatus());   
    
    // HSSFCell cellG1 = row1.createCell((short) 5);
    HSSFCell cellG1 = row1.createCell((short) 6);
    //   if(logisticsDocBean.getStatus()!=null) {
    //cellG1.setCellValue(purchaseOrderBean.getStatus());  
    //       if(logisticsDocBean.getStatus().equalsIgnoreCase("SUCCESS")){
    
    
    font1.setColor(HSSFColor.GREEN.index);
    //cellG1.setCellValue(docRepositoryBean.getStatus());  
    //   cellG1.setCellValue(logisticsDocBean.getStatus().toUpperCase()); 
    cellStyle1.setFont(font1);
    cellG1.setCellStyle(cellStyle1);
    
    //   }else if(logisticsDocBean.getStatus().equalsIgnoreCase("ERROR")){
    
    font2.setColor(HSSFColor.RED.index);
    //cellG1.setCellValue(docRepositoryBean.getStatus());  
    //  cellG1.setCellValue(logisticsDocBean.getStatus().toUpperCase()); 
    cellStyle2.setFont(font2);
    cellG1.setCellStyle(cellStyle2);
    
    //  }else{
    
    font3.setColor(HSSFColor.ORANGE.index);
    //cellG1.setCellValue(docRepositoryBean.getStatus());  
    //   cellG1.setCellValue(logisticsDocBean.getStatus().toUpperCase()); 
    cellStyle3.setFont(font3);
    cellG1.setCellStyle(cellStyle3);
    
    //  }
    
    
    }
    HSSFCell cellH1 = row1.createCell((short) 7);
    //   cellH1.setCellValue(logisticsDocBean.getReProcessStatus()); 
    
    }      
    //  } 
    workbook.write(fileOut);
    fileOut.flush();
    fileOut.close();
    } catch (FileNotFoundException e) {
    e.printStackTrace();
    } catch (IOException e) {
    e.printStackTrace();
    }
    return filePath;
    }
    
     */
    /**
     * @return the total no of days between two days excluding saturday and sunday .
     */
    
    public static int getdays(Date startDate, Date endDate) {
    Calendar startCal;
    Calendar endCal;
    startCal = Calendar.getInstance();
    startCal.setTime(startDate);
    endCal = Calendar.getInstance();
    endCal.setTime(endDate);
    int workDays = 0;
    //If working dates are same,then checking what is the day on that date.
    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
        //System.out.println("startCal------->"+startCal.getTimeInMillis());
        //System.out.println("endCal------->"+endCal.getTimeInMillis());
        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
        {
            ++workDays;
            //System.out.println("workdays------>"+workDays);
        }
        }
    /*If start date is coming after end date, Then shuffling Dates and storing dates 
by incrementing upto end date in do-while part.*/
    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
    }
    if(startCal.getTimeInMillis() < endCal.getTimeInMillis()) {
    do {
        
        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            ++workDays;
            //System.out.println("workdays in do------>"+workDays);
            }
    startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
    }
    //System.out.println("workdays------>"+workDays);
    return workDays; 
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
}
