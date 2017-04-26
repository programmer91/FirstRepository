  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.marketing;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.apache.poi.hssf.record.formula.functions.Cell;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.apache.poi.hssf.usermodel.*;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.interceptor.ServletResponseAware;


/**
 *
 * @author miracle
 */
public class WebsiteDataDownloadAction implements
        Action, ServletRequestAware, ServletResponseAware{

    private String contentDisposition = "fileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String tableName;
    private String fileName;
private String surveyTitle;
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String execute() throws Exception {
        return null;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {

        String responseString = "";
        String tableNamee = "";
        try {

            String fileLocation = "";
            //For creating Excel grind from Search result Grid
            // System.out.println("StartDate" + getStartdate());
            //  System.out.println("EndDate" + getEnddate());
            //  fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus());
            tableNamee=getTableName();
            if(tableNamee.equals("tblEventAttendies")){
                  fileLocation = getQmeetAttendiesList();
            }else if(tableNamee.equals("tblContactus")){
                 fileLocation = getContactUsList();
            }else if(tableNamee.equals("tblEmpVerfication")){
                 fileLocation = getEmployeeVerificationList();
            }else if(tableNamee.equals("tblResourceDepotDetails")){
                 fileLocation = getResourceDepotList();
            }else if(tableNamee.equals("tblSuggestions")){
                 fileLocation = getSuggestionBoxList();
            }else if (tableNamee.equals("tblSurveyInfoDetails")) {
                fileLocation = getEmployeeServeyDetailsList();
            }else if (tableNamee.equals("downloadSurveyAttachments")) {
                fileLocation = getEmployeeServeyAttachments();
            }else if (tableNamee.equals("tblIot")) {
                fileLocation = getIOTDeatilsList();
            }else if (tableNamee.equals("tblSignature")) {
                fileLocation = getSignatureDeatilsList();
            }
          
            
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
                Logger.getLogger(EmailAccountActivitiesCampaign.class.getName()).log(Level.SEVERE, null, ex1);
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

    public String getQmeetAttendiesList() {
        String filePath = "";
        try {


            java.util.List AttendiesList = (java.util.List) httpServletRequest.getSession(false).getAttribute("websiteInfoList");
            File file = new File(Properties.getProperty("Emp.Qmeet.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }
            
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "QMeet_Attendees.xls");
          filePath = file.getAbsolutePath() + File.separator + "QMeet_Attendees.xls";
            HSSFRow row1;
                //LogisticsDocBean logisticsDocBean = null;
                // index from 0,0... cell A1 is cell(0,0)

                // if(list.size()!=0){//
                //System.out.println("list size-->"+list.size());
                HSSFWorkbook workbook = new HSSFWorkbook();
                System.out.println("filePath " + filePath);
                HSSFSheet worksheet = workbook.createSheet("QMeetAttendees");
                for (int i = 0; i < 12; i++) {
                    worksheet.setColumnWidth(i, 10 * 256);
                   
                }
                HSSFCellStyle cs = workbook.createCellStyle();
                cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cs1 = workbook.createCellStyle();

                cs1.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border



                HSSFCellStyle headercs = workbook.createCellStyle();
                headercs.setFillForegroundColor(HSSFColor.AQUA.index);
                headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
                footercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                footercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs.setBorderTop((short) 1); // single line border
                footercs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle footercs1 = workbook.createCellStyle();
                footercs1.setFont(footerFont);
                footercs1.setFillForegroundColor(HSSFColor.YELLOW.index);
                footercs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                footercs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs1.setBorderTop((short) 1); // single line border
                footercs1.setBorderBottom((short) 1); // single line border
                
                 HSSFCellStyle footercs2 = workbook.createCellStyle();
                footercs2.setFont(footerFont);
                footercs2.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
                 footercs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
                Cell cell[] = new Cell[12];
                for (int i = 0; i < 12; i++) {
                    cell[i] = row1.createCell((short) i);
                }

                // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
                cell[0].setCellValue("QMeet Attenties");
                cellStyleHead.setFont(fontHead);
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cellStyle.setFillBackgroundColor(HSSFColor.AQUA.index);
                 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cell[0].setCellStyle(cellStyle);


                worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:L2"));

                //sno
                row1 = worksheet.createRow((short) 2);
                cell[0] = row1.createCell((short) 0);
                cell[0].setCellValue("SNo");
                cellStyleHead.setFont(fontHead);
                
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
               // cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                
                worksheet.addMergedRegion(CellRangeAddress.valueOf("A3:A4"));

                cell[0] = row1.createCell((short) 1);
                cell[0].setCellValue("First Name");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
               // cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B3:B4"));

                cell[0] = row1.createCell((short) 2);
                cell[0].setCellValue("Event Name");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("C3:C4"));

                cell[0] = row1.createCell((short) 3);
                cell[0].setCellValue("Email_Id");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
               // cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("D3:D4"));

                cell[0] = row1.createCell((short) 4);
                cell[0].setCellValue("Phone_No");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));

                cell[0] = row1.createCell((short) 5);
                cell[0].setCellValue("Department");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("F3:F4"));

                cell[0] = row1.createCell((short) 6);
                cell[0].setCellValue("Location");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("G3:G4"));

                cell[0] = row1.createCell((short) 7);
                cell[0].setCellValue("Food Pref");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("H3:H4"));

                cell[0] = row1.createCell((short) 8);
                cell[0].setCellValue("Along Member");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("I3:I4"));

                cell[0] = row1.createCell((short) 9);
                cell[0].setCellValue("Cor_Transport");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("J3:J4"));
                
                cell[0] = row1.createCell((short) 10);
                cell[0].setCellValue("Drop Point");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("K3:K4"));
                
                cell[0] = row1.createCell((short) 11);
                cell[0].setCellValue("Created Date");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("L3:L4"));

                

            //cell1.setCellStyle(headercs);
            //   while (resultSet.next()) {
            int count = 4;
            if (AttendiesList.size() > 0) {
                Map qMeetAttendeeMap = null;
                for (int i = 0; i < AttendiesList.size(); i++) {
                    qMeetAttendeeMap = (Map) AttendiesList.get(i);
                     row1 = worksheet.createRow((short) count++);
                    for (int j = 0; j < 12; j++) {
                        cell[j] = row1.createCell((short) j);
                    }
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    //cell1 = row.createCell((short) 1);
                    cell[0].setCellValue((Integer) (i+1));
                    cell[1].setCellValue((String) qMeetAttendeeMap.get("firstname"));
                    cell[2].setCellValue((String) qMeetAttendeeMap.get("EventName"));
                    cell[3].setCellValue((String) qMeetAttendeeMap.get("email_id"));
                    cell[4].setCellValue((String) qMeetAttendeeMap.get("phone_no"));
                    cell[5].setCellValue((String) qMeetAttendeeMap.get("department"));
                    cell[6].setCellValue((String) qMeetAttendeeMap.get("location"));
                    cell[7].setCellValue((String) qMeetAttendeeMap.get("foodpref"));
                    cell[8].setCellValue((String) qMeetAttendeeMap.get("alongmember"));
                    cell[9].setCellValue((String) qMeetAttendeeMap.get("cor_transport"));
                    cell[10].setCellValue((String) qMeetAttendeeMap.get("DropPoint"));
                    cell[11].setCellValue((String) qMeetAttendeeMap.get("CreatedDate"));
                    //cell1.setCellValue((String) emailMap.get("Date"));


                    for (int h = 0; h < 12; h++) {
                            if (count % 2 == 0) {
                                cell[h].setCellStyle(cs1);
                            } else {
                                cell[h].setCellStyle(cs);
                            }

                        }

                    //cell1.setCellStyle(cs);
                    //}
                   /* row = sheet.createRow((short) count++);
                    cell = row.createCell((short) 0);
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    //cell1 = row.createCell((short) 1);
                    cell.setCellValue("");
                    //cell1.setCellValue("");
                    
                    
                    cell.setCellStyle(footercs);*/
                    //cell1.setCellStyle(footercs);

                }
                for(int i=0;i<12;i++){
                     worksheet.autoSizeColumn((short) i);
                }
                
                
                 worksheet.setColumnWidth(0, 15 * 256);
                 worksheet.setColumnWidth(1, 15 * 256);
                 worksheet.setColumnWidth(5, 15 * 256);
                 worksheet.setColumnWidth(8, 15 * 256);
                 worksheet.setColumnWidth(9, 15 * 256);

                workbook.write(fileOut);
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
        }


        return filePath;
    }
    //contact us method
    public String getContactUsList() {
        String filePath = "";
        try {


            java.util.List ContactUsList = (java.util.List) httpServletRequest.getSession(false).getAttribute("websiteInfoList");
            File file = new File(Properties.getProperty("Emp.ContactUs.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }
            
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "ContactUs.xls");
          filePath = file.getAbsolutePath() + File.separator + "ContactUs.xls";
            HSSFRow row1;
                //LogisticsDocBean logisticsDocBean = null;
                // index from 0,0... cell A1 is cell(0,0)

                // if(list.size()!=0){//
                //System.out.println("list size-->"+list.size());
                HSSFWorkbook workbook = new HSSFWorkbook();
                System.out.println("filePath " + filePath);
                HSSFSheet worksheet = workbook.createSheet("ContactUs");
                for (int i = 0; i < 13; i++) {
                    worksheet.setColumnWidth(i, 10 * 256);
                   
                }
                HSSFCellStyle cs = workbook.createCellStyle();
                cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cs1 = workbook.createCellStyle();

                cs1.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border



                HSSFCellStyle headercs = workbook.createCellStyle();
                headercs.setFillForegroundColor(HSSFColor.AQUA.index);
                headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
                footercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                footercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs.setBorderTop((short) 1); // single line border
                footercs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle footercs1 = workbook.createCellStyle();
                footercs1.setFont(footerFont);
                footercs1.setFillForegroundColor(HSSFColor.YELLOW.index);
                footercs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                footercs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs1.setBorderTop((short) 1); // single line border
                footercs1.setBorderBottom((short) 1); // single line border
                
                 HSSFCellStyle footercs2 = workbook.createCellStyle();
                footercs2.setFont(footerFont);
                footercs2.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
                 footercs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
                Cell cell[] = new Cell[13];
                for (int i = 0; i < 13; i++) {
                    cell[i] = row1.createCell((short) i);
                }

                // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
                cell[0].setCellValue("Contact Us");
                cellStyleHead.setFont(fontHead);
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cellStyle.setFillBackgroundColor(HSSFColor.AQUA.index);
                 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cell[0].setCellStyle(cellStyle);


                worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:M2"));

                //sno
                row1 = worksheet.createRow((short) 2);
                cell[0] = row1.createCell((short) 0);
                cell[0].setCellValue("SNo");
                cellStyleHead.setFont(fontHead);
                
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
               // cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                
                worksheet.addMergedRegion(CellRangeAddress.valueOf("A3:A4"));

                

                cell[0] = row1.createCell((short) 1);
                cell[0].setCellValue("First Name");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B3:B4"));

                cell[0] = row1.createCell((short) 2);
                cell[0].setCellValue("Last Name");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
               // cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("C3:C4"));

                cell[0] = row1.createCell((short) 3);
                cell[0].setCellValue("Email");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("D3:D4"));

                cell[0] = row1.createCell((short) 4);
                cell[0].setCellValue("Organization");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));

                cell[0] = row1.createCell((short) 5);
                cell[0].setCellValue("Designation");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("F3:F4"));

                cell[0] = row1.createCell((short) 6);
                cell[0].setCellValue("Work Phone");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("G3:G4"));

                cell[0] = row1.createCell((short) 7);
                cell[0].setCellValue("City");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("H3:H4"));

                cell[0] = row1.createCell((short) 8);
                cell[0].setCellValue("State");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("I3:I4"));
                
                cell[0] = row1.createCell((short) 9);
                cell[0].setCellValue("Country");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("J3:J4"));
                
                cell[0] = row1.createCell((short) 10);
                cell[0].setCellValue("Zip");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("k3:K4"));
                
                 cell[0] = row1.createCell((short) 11);
                cell[0].setCellValue("Description");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("L3:L4"));
                
                 cell[0] = row1.createCell((short) 12);
                cell[0].setCellValue("CreatedDate");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("M3:M4"));

                

            //cell1.setCellStyle(headercs);
            //   while (resultSet.next()) {
            int count = 4;
            if (ContactUsList.size() > 0) {
                Map contactUsMap = null;
                for (int i = 0; i < ContactUsList.size(); i++) {
                    contactUsMap = (Map) ContactUsList.get(i);
                     row1 = worksheet.createRow((short) count++);
                    for (int j = 0; j < 13; j++) {
                        cell[j] = row1.createCell((short) j);
                    }
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    //cell1 = row.createCell((short) 1);
                    cell[0].setCellValue((Integer) (i+1));
                   // cell[1].setCellValue((String) contactUsMap.get("Id"));
                    cell[1].setCellValue((String) contactUsMap.get("Fname"));
                    cell[2].setCellValue((String) contactUsMap.get("Lname"));
                    cell[3].setCellValue((String) contactUsMap.get("Email"));
                    cell[4].setCellValue((String) contactUsMap.get("Organization"));
                    cell[5].setCellValue((String) contactUsMap.get("Designation"));
                    cell[6].setCellValue((String) contactUsMap.get("WorkPhone"));
                    cell[7].setCellValue((String) contactUsMap.get("City"));
                    cell[8].setCellValue((String) contactUsMap.get("State"));
                    cell[9].setCellValue((String) contactUsMap.get("Country"));
                    cell[10].setCellValue((String) contactUsMap.get("Zip"));
                    cell[11].setCellValue((String) contactUsMap.get("Description"));
                    cell[12].setCellValue((String) contactUsMap.get("CreatedDate"));
                    //cell1.setCellValue((String) emailMap.get("Date"));


                    for (int h = 0; h < 13; h++) {
                            if (count % 2 == 0) {
                                cell[h].setCellStyle(cs1);
                            } else {
                                cell[h].setCellStyle(cs);
                            }

                        }

                    //cell1.setCellStyle(cs);
                    //}
                   /* row = sheet.createRow((short) count++);
                    cell = row.createCell((short) 0);
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    //cell1 = row.createCell((short) 1);
                    cell.setCellValue("");
                    //cell1.setCellValue("");
                    
                    
                    cell.setCellStyle(footercs);*/
                    //cell1.setCellStyle(footercs);

                }
                for(int i=0;i<13;i++){
                     worksheet.autoSizeColumn((short) i);
                }
                
                
                 worksheet.setColumnWidth(0, 15 * 256);
                 worksheet.setColumnWidth(1, 15 * 256);
               
                 worksheet.setColumnWidth(10, 15 * 256);

                workbook.write(fileOut);
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
        }


        return filePath;
    }
    
 // employee verification
    
     public String getEmployeeVerificationList() {
        String filePath = "";
        try {


            java.util.List EmpVerificationList = (java.util.List) httpServletRequest.getSession(false).getAttribute("websiteInfoList");
            File file = new File(Properties.getProperty("Emp.Empverification.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }
            
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "EmployeeVerificationData.xls");
          filePath = file.getAbsolutePath() + File.separator + "EmployeeVerificationData.xls";
            HSSFRow row1;
                //LogisticsDocBean logisticsDocBean = null;
                // index from 0,0... cell A1 is cell(0,0)

                // if(list.size()!=0){//
                //System.out.println("list size-->"+list.size());
                HSSFWorkbook workbook = new HSSFWorkbook();
                System.out.println("filePath " + filePath);
                HSSFSheet worksheet = workbook.createSheet("EmployeeVerification");
                for (int i = 0; i < 15; i++) {
                    worksheet.setColumnWidth(i, 10 * 256);
                   
                }
                HSSFCellStyle cs = workbook.createCellStyle();
                cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cs1 = workbook.createCellStyle();

                cs1.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border



                HSSFCellStyle headercs = workbook.createCellStyle();
                headercs.setFillForegroundColor(HSSFColor.AQUA.index);
                headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
                footercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                footercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs.setBorderTop((short) 1); // single line border
                footercs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle footercs1 = workbook.createCellStyle();
                footercs1.setFont(footerFont);
                footercs1.setFillForegroundColor(HSSFColor.YELLOW.index);
                footercs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                footercs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs1.setBorderTop((short) 1); // single line border
                footercs1.setBorderBottom((short) 1); // single line border
                
                 HSSFCellStyle footercs2 = workbook.createCellStyle();
                footercs2.setFont(footerFont);
                footercs2.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
                 footercs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
                Cell cell[] = new Cell[15];
                for (int i = 0; i < 15; i++) {
                    cell[i] = row1.createCell((short) i);
                }

                // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
                cell[0].setCellValue("Employee Verification");
                cellStyleHead.setFont(fontHead);
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cellStyle.setFillBackgroundColor(HSSFColor.AQUA.index);
                 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cell[0].setCellStyle(cellStyle);


                worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:O2"));

                //sno
                row1 = worksheet.createRow((short) 2);
                cell[0] = row1.createCell((short) 0);
                cell[0].setCellValue("SNo");
                cellStyleHead.setFont(fontHead);
                
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
               // cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                
                worksheet.addMergedRegion(CellRangeAddress.valueOf("A3:A4"));

                

                cell[0] = row1.createCell((short) 1);
                cell[0].setCellValue("First Name");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B3:B4"));

                cell[0] = row1.createCell((short) 2);
                cell[0].setCellValue("Last Name");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
               // cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("C3:C4"));

                cell[0] = row1.createCell((short) 3);
                cell[0].setCellValue("Email Id");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("D3:D4"));

                cell[0] = row1.createCell((short) 4);
                cell[0].setCellValue("Organization");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));

                cell[0] = row1.createCell((short) 5);
                cell[0].setCellValue("Designation");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("F3:F4"));

                cell[0] = row1.createCell((short) 6);
                cell[0].setCellValue("Phone");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("G3:G4"));

                cell[0] = row1.createCell((short) 7);
                cell[0].setCellValue("Employee Name");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("H3:H4"));

                cell[0] = row1.createCell((short) 8);
                cell[0].setCellValue("Employee Designation");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("I3:I4"));
                
                cell[0] = row1.createCell((short) 9);
                cell[0].setCellValue("Department");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("J3:J4"));
                
                cell[0] = row1.createCell((short) 10);
                cell[0].setCellValue("Employee ID");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("K3:K4"));
                
                cell[0] = row1.createCell((short) 11);
                cell[0].setCellValue("Employment Started");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("L3:L4"));
                
                cell[0] = row1.createCell((short) 12);
                cell[0].setCellValue("Employment Ended");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("M3:M4"));
                
                cell[0] = row1.createCell((short) 13);
                cell[0].setCellValue("Employment Location");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("N3:N4"));
                
                cell[0] = row1.createCell((short) 14);
                cell[0].setCellValue("Created Date");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("O3:O4"));

                

            //cell1.setCellStyle(headercs);
            //   while (resultSet.next()) {
            int count = 4;
            if (EmpVerificationList.size() > 0) {
                Map employeeVerificationMap = null;
                for (int i = 0; i < EmpVerificationList.size(); i++) {
                    employeeVerificationMap = (Map) EmpVerificationList.get(i);
                     row1 = worksheet.createRow((short) count++);
                    for (int j = 0; j < 15; j++) {
                        cell[j] = row1.createCell((short) j);
                    }
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    //cell1 = row.createCell((short) 1);
                    cell[0].setCellValue((Integer) (i+1));
                   // cell[1].setCellValue((String) employeeVerificationMap.get("ID"));
                    cell[1].setCellValue((String) employeeVerificationMap.get("FirstName"));
                    cell[2].setCellValue((String) employeeVerificationMap.get("LastName"));
                    cell[3].setCellValue((String) employeeVerificationMap.get("EmailId"));
                    cell[4].setCellValue((String) employeeVerificationMap.get("Organization"));
                    cell[5].setCellValue((String) employeeVerificationMap.get("Designation"));
                    cell[6].setCellValue((String) employeeVerificationMap.get("Phone"));
                    cell[7].setCellValue((String) employeeVerificationMap.get("EmployeeName"));
                    cell[8].setCellValue((String) employeeVerificationMap.get("EmployeeDesignation"));
                    cell[9].setCellValue((String) employeeVerificationMap.get("Department"));
                    cell[10].setCellValue((String) employeeVerificationMap.get("EmployeeID"));
                    cell[11].setCellValue((String) employeeVerificationMap.get("EmploymentStarted"));
                    cell[12].setCellValue((String) employeeVerificationMap.get("EmploymentEnded"));
                    cell[13].setCellValue((String) employeeVerificationMap.get("EmploymentLocation"));
                    cell[14].setCellValue((String) employeeVerificationMap.get("CreatedDate"));
                    //cell1.setCellValue((String) emailMap.get("Date"));


                    for (int h = 0; h < 15; h++) {
                            if (count % 2 == 0) {
                                cell[h].setCellStyle(cs1);
                            } else {
                                cell[h].setCellStyle(cs);
                            }

                        }

                    //cell1.setCellStyle(cs);
                    //}
                   /* row = sheet.createRow((short) count++);
                    cell = row.createCell((short) 0);
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    //cell1 = row.createCell((short) 1);
                    cell.setCellValue("");
                    //cell1.setCellValue("");
                    
                    
                    cell.setCellStyle(footercs);*/
                    //cell1.setCellStyle(footercs);

                }
                for(int i=0;i<15;i++){
                     worksheet.autoSizeColumn((short) i);
                }
                
                
               worksheet.setColumnWidth(0, 15 * 256);
//                 worksheet.setColumnWidth(1, 15 * 256);
//                 worksheet.setColumnWidth(5, 15 * 256);
//                 worksheet.setColumnWidth(8, 15 * 256);
//                 worksheet.setColumnWidth(9, 15 * 256);

                workbook.write(fileOut);
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
        }


        return filePath;
    }
     
     // Resourcr depot Method
    
     public String getResourceDepotList() {
        String filePath = "";
        try {


            java.util.List ResourceDepotList = (java.util.List) httpServletRequest.getSession(false).getAttribute("websiteInfoList");
            File file = new File(Properties.getProperty("Emp.ResourceDepot.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }
            
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "ResourceDepotData.xls");
          filePath = file.getAbsolutePath() + File.separator + "ResourceDepotData.xls";
            HSSFRow row1;
                //LogisticsDocBean logisticsDocBean = null;
                // index from 0,0... cell A1 is cell(0,0)

                // if(list.size()!=0){//
                //System.out.println("list size-->"+list.size());
                HSSFWorkbook workbook = new HSSFWorkbook();
                System.out.println("filePath " + filePath);
                HSSFSheet worksheet = workbook.createSheet("Resource Depot Data");
                for (int i = 0; i < 11; i++) {
                    worksheet.setColumnWidth(i, 10 * 256);
                   
                }
                HSSFCellStyle cs = workbook.createCellStyle();
                cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cs1 = workbook.createCellStyle();

                cs1.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border



                HSSFCellStyle headercs = workbook.createCellStyle();
                headercs.setFillForegroundColor(HSSFColor.AQUA.index);
                headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
                footercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                footercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs.setBorderTop((short) 1); // single line border
                footercs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle footercs1 = workbook.createCellStyle();
                footercs1.setFont(footerFont);
                footercs1.setFillForegroundColor(HSSFColor.YELLOW.index);
                footercs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                footercs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs1.setBorderTop((short) 1); // single line border
                footercs1.setBorderBottom((short) 1); // single line border
                
                 HSSFCellStyle footercs2 = workbook.createCellStyle();
                footercs2.setFont(footerFont);
                footercs2.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
                 footercs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
                Cell cell[] = new Cell[11];
                for (int i = 0; i < 10; i++) {
                    cell[i] = row1.createCell((short) i);
                }

                // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
                cell[0].setCellValue("Resource Depot Data");
                cellStyleHead.setFont(fontHead);
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cellStyle.setFillBackgroundColor(HSSFColor.AQUA.index);
                 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cell[0].setCellStyle(cellStyle);


                worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:J2"));

                //sno
                row1 = worksheet.createRow((short) 2);
                cell[0] = row1.createCell((short) 0);
                cell[0].setCellValue("SNo");
                cellStyleHead.setFont(fontHead);
                
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
               // cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                
                worksheet.addMergedRegion(CellRangeAddress.valueOf("A3:A4"));

                

                cell[0] = row1.createCell((short) 1);
                cell[0].setCellValue("First Name");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B3:B4"));

                cell[0] = row1.createCell((short) 2);
                cell[0].setCellValue("Last Name");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
               // cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("C3:C4"));

                cell[0] = row1.createCell((short) 3);
                cell[0].setCellValue("Organization");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("D3:D4"));

                cell[0] = row1.createCell((short) 4);
                cell[0].setCellValue("Designation");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));

                cell[0] = row1.createCell((short) 5);
                cell[0].setCellValue("Email");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("F3:F4"));

                cell[0] = row1.createCell((short) 6);
                cell[0].setCellValue("WorkPhone");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("G3:G4"));

                cell[0] = row1.createCell((short) 7);
                cell[0].setCellValue("Applied Date");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("H3:H4"));

                cell[0] = row1.createCell((short) 8);
                cell[0].setCellValue("DocTitle");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("I3:I4"));
                
                cell[0] = row1.createCell((short) 9);
                cell[0].setCellValue("DocType");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //    cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("J3:J4"));
                
               

                

            //cell1.setCellStyle(headercs);
            //   while (resultSet.next()) {
            int count = 4;
            if (ResourceDepotList.size() > 0) {
                Map resourceDepotMap = null;
                for (int i = 0; i < ResourceDepotList.size(); i++) {
                    resourceDepotMap = (Map) ResourceDepotList.get(i);
                     row1 = worksheet.createRow((short) count++);
                    for (int j = 0; j < 10; j++) {
                        cell[j] = row1.createCell((short) j);
                    }
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    //cell1 = row.createCell((short) 1);
                    cell[0].setCellValue((Integer) (i+1));
                  //  cell[1].setCellValue((String) resourceDepotMap.get("Id"));
                    cell[1].setCellValue((String) resourceDepotMap.get("FName"));
                    cell[2].setCellValue((String) resourceDepotMap.get("LName"));
                    cell[3].setCellValue((String) resourceDepotMap.get("Organization"));
                    cell[4].setCellValue((String) resourceDepotMap.get("Designation"));
                    cell[5].setCellValue((String) resourceDepotMap.get("Email"));
                    cell[6].setCellValue((String) resourceDepotMap.get("WorkPhone"));
                    cell[7].setCellValue((String) resourceDepotMap.get("AppliedDate"));
                    cell[8].setCellValue((String) resourceDepotMap.get("DocTitle"));
                    cell[9].setCellValue((String) resourceDepotMap.get("DocType"));
                  
                    
                    //cell1.setCellValue((String) emailMap.get("Date"));


                    for (int h = 0; h < 10; h++) {
                            if (count % 2 == 0) {
                                cell[h].setCellStyle(cs1);
                            } else {
                                cell[h].setCellStyle(cs);
                            }

                        }

                    //cell1.setCellStyle(cs);
                    //}
                   /* row = sheet.createRow((short) count++);
                    cell = row.createCell((short) 0);
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    //cell1 = row.createCell((short) 1);
                    cell.setCellValue("");
                    //cell1.setCellValue("");
                    
                    
                    cell.setCellStyle(footercs);*/
                    //cell1.setCellStyle(footercs);

                }
                for(int i=0;i<10;i++){
                     worksheet.autoSizeColumn((short) i);
                }
                
                
                 worksheet.setColumnWidth(0, 15 * 256);
             
//                 worksheet.setColumnWidth(5, 15 * 256);
//                 worksheet.setColumnWidth(8, 15 * 256);
//                 worksheet.setColumnWidth(9, 15 * 256);

                workbook.write(fileOut);
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
        }


        return filePath;
    }
     // Suggestion Box Data Method
    
     public String getSuggestionBoxList() {
        String filePath = "";
        try {


            java.util.List SuggestionBoxList = (java.util.List) httpServletRequest.getSession(false).getAttribute("websiteInfoList");
            File file = new File(Properties.getProperty("Emp.SuggestionBoxData.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }
            
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "SuggestionBoxData.xls");
          filePath = file.getAbsolutePath() + File.separator + "SuggestionBoxData.xls";
            HSSFRow row1;
                //LogisticsDocBean logisticsDocBean = null;
                // index from 0,0... cell A1 is cell(0,0)

                // if(list.size()!=0){//
                //System.out.println("list size-->"+list.size());
                HSSFWorkbook workbook = new HSSFWorkbook();
                System.out.println("filePath " + filePath);
                HSSFSheet worksheet = workbook.createSheet("Suggestion Box Data");
                for (int i = 0; i < 6; i++) {
                    worksheet.setColumnWidth(i, 10 * 256);
                   
                }
                HSSFCellStyle cs = workbook.createCellStyle();
                cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle cs1 = workbook.createCellStyle();

                cs1.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
                cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border



                HSSFCellStyle headercs = workbook.createCellStyle();
                headercs.setFillForegroundColor(HSSFColor.AQUA.index);
                headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
                footercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                footercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs.setBorderTop((short) 1); // single line border
                footercs.setBorderBottom((short) 1); // single line border

                HSSFCellStyle footercs1 = workbook.createCellStyle();
                footercs1.setFont(footerFont);
                footercs1.setFillForegroundColor(HSSFColor.YELLOW.index);
                footercs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                footercs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                footercs1.setBorderTop((short) 1); // single line border
                footercs1.setBorderBottom((short) 1); // single line border
                
                 HSSFCellStyle footercs2 = workbook.createCellStyle();
                footercs2.setFont(footerFont);
                footercs2.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
                 footercs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
                Cell cell[] = new Cell[6];
                for (int i = 0; i < 6; i++) {
                    cell[i] = row1.createCell((short) i);
                }

                // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
                cell[0].setCellValue("Suggestion Box Data");
                cellStyleHead.setFont(fontHead);
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cellStyle.setFillBackgroundColor(HSSFColor.AQUA.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cell[0].setCellStyle(cellStyle);


                worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:F2"));

                //sno
                row1 = worksheet.createRow((short) 2);
                cell[0] = row1.createCell((short) 0);
                cell[0].setCellValue("SNo");
                cellStyleHead.setFont(fontHead);
                
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
               // cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                
                worksheet.addMergedRegion(CellRangeAddress.valueOf("A3:A4"));

                

                cell[0] = row1.createCell((short) 1);
                cell[0].setCellValue("First Name");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B3:B4"));

                cell[0] = row1.createCell((short) 2);
                cell[0].setCellValue("Last Name");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
               // cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("C3:C4"));

                cell[0] = row1.createCell((short) 3);
                cell[0].setCellValue("Anonymously");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("D3:D4"));

                cell[0] = row1.createCell((short) 4);
                cell[0].setCellValue("Suggestions");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
             //   cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));

                cell[0] = row1.createCell((short) 5);
                cell[0].setCellValue("CreatedDate");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              //  cell[0].setCellStyle(cellStyleHead);
                 cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("F3:F4"));


            int count = 4;
            if (SuggestionBoxList.size() > 0) {
                Map suggestionBoxMap = null;
                for (int i = 0; i < SuggestionBoxList.size(); i++) {
                    suggestionBoxMap = (Map) SuggestionBoxList.get(i);
                         row1 = worksheet.createRow((short) count++);
                    for (int j = 0; j < 6; j++) {
                        cell[j] = row1.createCell((short) j);
                    }
                    //  HSSFRow row2 = sheet.createRow((short)0);
                    //cell1 = row.createCell((short) 1);
                    cell[0].setCellValue((Integer) (i+1));
                  //  cell[1].setCellValue((String) resourceDepotMap.get("Id"));
                    cell[1].setCellValue((String) suggestionBoxMap.get("FirstName"));
                    cell[2].setCellValue((String) suggestionBoxMap.get("LastName"));
                    cell[3].setCellValue((String) suggestionBoxMap.get("Anonymously"));
                    cell[4].setCellValue((String) suggestionBoxMap.get("Suggestions"));
                    cell[5].setCellValue((String) suggestionBoxMap.get("CreatedDate"));
                   
                    for (int h = 0; h < 6; h++) {
                            if (count % 2 == 0) {
                                cell[h].setCellStyle(cs1);
                            } else {
                                cell[h].setCellStyle(cs);
                            }

                        }
                }
                for(int i=0;i<6;i++){
                     worksheet.autoSizeColumn((short) i);
                }
                
                 worksheet.setColumnWidth(0, 15 * 256);
                 worksheet.setColumnWidth(3, 15 * 256);
             
                workbook.write(fileOut);
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
        }
        return filePath;
    }

 //commenting method before asha changed    
  /*  public String getEmployeeServeyDetailsList() {
        //System.out.println("getEmployeeServeyDetailsList start");
        String filePath = "";
        String[] ServeyReview = new String[15];
        for (int i = 0; i < ServeyReview.length; i++) {
            ServeyReview[i] = "";
        }
        try {


            java.util.List questionaryList = (java.util.List) httpServletRequest.getSession(false).getAttribute("surveyFormQuestionnaireList");
            java.util.List mainList = (java.util.List) httpServletRequest.getSession(false).getAttribute("surveyFormReviewList");

            // Map firstMap = (Map)surveyFormQuestionnaireHeaderList;
            for (int qes = 0; qes < questionaryList.size(); qes++) {
                Map firstMap = (Map) questionaryList.get(qes);
                ServeyReview[qes] = (String) firstMap.get("Query");
               // System.out.println("header-->" + firstMap.get("Query"));

            }

            File file = new File(Properties.getProperty("Emp.ServeyReviewList.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator +getSurveyTitle().trim().replaceAll(" ", "_")+".xls");
            filePath = file.getAbsolutePath() + File.separator + getSurveyTitle().trim().replaceAll(" ", "_") +".xls";
            HSSFRow row1;
            //LogisticsDocBean logisticsDocBean = null;
            // index from 0,0... cell A1 is cell(0,0)

            // if(list.size()!=0){//
            //System.out.println("list size-->"+list.size());
            HSSFWorkbook workbook = new HSSFWorkbook();
            System.out.println("filePath " + filePath);
            HSSFSheet worksheet = workbook.createSheet("Servey Review Data");
            for (int i = 0; i < 15; i++) {
                worksheet.setColumnWidth(i, 10 * 256);

            }
       

         




           

            HSSFFont timesBoldFont = workbook.createFont();
            timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            timesBoldFont.setColor(HSSFColor.WHITE.index);
            
            timesBoldFont.setFontName("Calibri");
          //  timesBoldFont.setFontHeightInPoints((short)14);  //for font Size
             HSSFCellStyle headercs = workbook.createCellStyle();
            headercs.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
            headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            headercs.setBorderTop((short) 1); // single line border
            headercs.setBorderBottom((short) 1); // single line border
            headercs.setFont(timesBoldFont);

            HSSFFont footerFont = workbook.createFont();
            footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            footerFont.setFontName("Calibri");

          

        

            // HSSFRow row1;
            // LogisticsDocBean logisticsDocBean = null;
            // index from 0,0... cell A1 is cell(0,0)

            // if(list.size()!=0){//
            //System.out.println("list size-->"+list.size());
            
        
            
            
            HSSFFont font4 = workbook.createFont();
             font4.setColor(HSSFColor.WHITE.index);
             font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
             font4.setFontHeightInPoints((short)14);
             font4.setFontName("Calibri");
       
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setFont(font4);
            
            
            HSSFFont font1 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
             font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
             font1.setFontHeightInPoints((short)14);
             font1.setFontName("Calibri");
                 HSSFCellStyle cs = workbook.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs.setBorderTop((short) 1); // single line border
            cs.setBorderBottom((short) 1); // single line border
            cs.setFont(font1);
            
            
               HSSFCellStyle cs1 = workbook.createCellStyle();
            cs1.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
            cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs1.setFont(font4);
            cs1.setBorderTop((short) 1); // single line border
            cs1.setBorderBottom((short) 1); // single line border
            
            
            
            HSSFCellStyle cs2 = workbook.createCellStyle();

            cs2.setFillForegroundColor(HSSFColor.WHITE.index);
            cs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs2.setBorderTop((short) 1); // single line border
            cs2.setBorderBottom((short) 1); // single line border
            cs2.setFont(font1);
            
            
              HSSFFont font3 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
           //  font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
             font3.setFontHeightInPoints((short)14);
             font3.setFontName("Calibri");
            
            HSSFCellStyle cs3 = workbook.createCellStyle();
            cs3.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs3.setFont(font3);
            cs3.setBorderTop((short) 1); // single line border
            cs3.setBorderBottom((short) 1); // single line border
            
            
            
            HSSFCellStyle cs4 = workbook.createCellStyle();

            cs4.setFillForegroundColor(HSSFColor.WHITE.index);
            cs4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs4.setBorderTop((short) 1); // single line border
            cs4.setBorderBottom((short) 1); // single line border
            cs4.setFont(font3);
            
            
            
            //start	
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
            Date today = new Date();
            String date = DATE_FORMAT.format(today);
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
            Cell cell[] = new Cell[15];
            for (int i = 0; i < 15; i++) {
                cell[i] = row1.createCell((short) i);
            }

            // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
            cell[0].setCellValue("Survey Review Data : "+getSurveyTitle()+"-"+date);
            HSSFCellStyle cellStyleHead = workbook.createCellStyle();
            cellStyleHead.setFont(timesBoldFont);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cell[0].setCellStyle(cellStyle);
            int len=questionaryList.size();
            String mergingInterval = "";
                    switch (len+1) {
                    case 1:
                        mergingInterval = "D";
                        break;
                    case 2:
                        mergingInterval = "E";
                        break;
                    case 3:
                        mergingInterval = "F";
                        break;
                    case 4:
                        mergingInterval = "G";
                        break;
                    case 5:
                        mergingInterval = "H";
                        break;
                    case 6:
                        mergingInterval = "I";
                        break;
                    case 7:
                        mergingInterval = "J";
                        break;
                    case 8:
                        mergingInterval = "K";
                        break;
                    case 9:
                        mergingInterval = "L";
                        break;
                    case 10:
                        mergingInterval = "M";
                        break;
                    case 11:
                        mergingInterval = "N";
                        break;
                    case 12:
                        mergingInterval = "O";
                        break;
                    case 13:
                        mergingInterval = "P";
                        break;
                    case 14:
                        mergingInterval = "Q";
                        break;
                    case 15:
                        mergingInterval = "R";
                        break;
                    case 16:
                        mergingInterval = "S";
                        break;
                    case 17:
                        mergingInterval = "T";
                        break;
                    case 18:
                        mergingInterval = "U";
                        break;
                    case 19:
                        mergingInterval = "V";
                        break;
                }

             worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:" + mergingInterval + "2"));

            //sno
            row1 = worksheet.createRow((short) 2);
            cell[0] = row1.createCell((short) 0);
            cell[0].setCellValue("SNo");
           // cellStyleHead.setFont(font4);
            cellStyleHead.setFont(timesBoldFont);
          //  cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //cellStyleHead.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
           // cellStyleHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);

            worksheet.addMergedRegion(CellRangeAddress.valueOf("A3:A4"));



            cell[0] = row1.createCell((short) 1);
            cell[0].setCellValue("Name");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("B3:B4"));

            cell[0] = row1.createCell((short) 2);
            cell[0].setCellValue("E-Mail");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("C3:C4"));

            cell[0] = row1.createCell((short) 3);
            cell[0].setCellValue("Phone");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //  cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("D3:D4"));
            if (ServeyReview[0] != "") {
                cell[0] = row1.createCell((short) 4);
                cell[0].setCellValue(ServeyReview[0]);
                cell[0].setCellStyle(cs1);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //   cell[0].setCellStyle(cellStyleHead);
                
                worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));
            }
            if (ServeyReview[1] != "") {
                cell[0] = row1.createCell((short) 5);
                cell[0].setCellValue(ServeyReview[1]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("F3:F4"));
            }
            if (ServeyReview[2] != "") {
                cell[0] = row1.createCell((short) 6);
                cell[0].setCellValue(ServeyReview[2]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("G3:G4"));
            }
            if (ServeyReview[3] != "") {
                cell[0] = row1.createCell((short) 7);
                cell[0].setCellValue(ServeyReview[3]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("H3:H4"));
            }
            if (ServeyReview[4] != "") {
                cell[0] = row1.createCell((short) 8);
                cell[0].setCellValue(ServeyReview[4]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("I3:I4"));
            }
            if (ServeyReview[5] != "") {
                cell[0] = row1.createCell((short) 9);
                cell[0].setCellValue(ServeyReview[5]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("J3:J4"));
            }
            if (ServeyReview[6] != "") {
                cell[0] = row1.createCell((short) 10);
                cell[0].setCellValue(ServeyReview[6]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("K3:K4"));
            }
            if (ServeyReview[7] != "") {
                cell[0] = row1.createCell((short) 11);
                cell[0].setCellValue(ServeyReview[7]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("L3:L4"));
            }
            if (ServeyReview[8] != "") {
                cell[0] = row1.createCell((short) 12);
                cell[0].setCellValue(ServeyReview[8]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("M3:M4"));
            }
            if (ServeyReview[9] != "") {
                cell[0] = row1.createCell((short) 13);
                cell[0].setCellValue(ServeyReview[9]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("N3:N4"));
            }
            if (ServeyReview[10] != "") {
                cell[0] = row1.createCell((short) 14);
                cell[0].setCellValue(ServeyReview[10]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("O3:O4"));
            }
            if (ServeyReview[11] != "") {
                cell[0] = row1.createCell((short) 15);
                cell[0].setCellValue(ServeyReview[11]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("P3:P4"));
            }
            int count = 4;
//            if (mainList.size() > 0) {
//                Map serveyReviewMap = null;
//                for (int i = 0; i < mainList.size(); i++) {
//                    serveyReviewMap = (Map) mainList.get(i);
//                    row1 = worksheet.createRow((short) count++);
//                    for (int j = 0; j < 6; j++) {
//                        cell[j] = row1.createCell((short) j);
//                    }
//                    cell[0].setCellValue((Integer) (i + 1));
//                    cell[1].setCellValue((String) serveyReviewMap.get("Name"));
//                    cell[2].setCellValue((String) serveyReviewMap.get("Email"));
//                    cell[3].setCellValue((String) serveyReviewMap.get("Phone"));
//                    for (int j = 0; j < mainList.size(); j++) {
//                        java.util.List subList = (java.util.List) mainList.get(j);
//                        for (int qes = 0, ccount = 4; qes < questionaryList.size(); qes++, ccount++) {
//                            Map firstMap = (Map) questionaryList.get(qes);
//                            cell[ccount].setCellValue((String) subList.get(j));
//                        }
//                    }
            //---------------------
           // System.out.println("mainList Size"+mainList.size());
            if (mainList.size() > 0) {

                for (int k = 0; k < mainList.size(); k++) {

                    java.util.Map subList = (java.util.Map) mainList.get(k);
                    row1 = worksheet.createRow((short) count++);
                    for (int j = 0; j < 15; j++) {
                        cell[j] = row1.createCell((short) j);
                    }
                    //System.out.println("subList-->"+subList);
                    cell[0].setCellValue((Integer) (k + 1));
                    cell[1].setCellValue((String) subList.get("Name"));

                    cell[2].setCellValue((String) subList.get("Email").toString());

                    cell[3].setCellValue((String) subList.get("Phone").toString());

                    for (int qes = 0, ccount = 4; qes < questionaryList.size(); qes++, ccount++) {

                        Map firstMap = (Map) questionaryList.get(qes);

                       if(firstMap.get("OptionType").toString().equals("Attachment")){
                           /* File filee=new File((String) subList.get(firstMap.get("Query")));
                            if(filee.exists()){
                               // System.out.println("exits");
                                 cell[ccount].setCellValue("Attachment is Available plz download it from Hubble");
                            }else{
                               // System.out.println("not exits");
                                cell[ccount].setCellValue(" ");
                            }
                           if(subList.get(firstMap.get("Query"))!=null && !"".equals(subList.get(firstMap.get("Query")))){
                                File filee=new File((String) subList.get(firstMap.get("Query")));
                            if(filee.exists()){
                               // System.out.println("exits");
                                 cell[ccount].setCellValue("Attachment is Available please download it from Hubble");
                            }else{
                               // System.out.println("not exits");
                                cell[ccount].setCellValue("Attachment is Not Available .");
                            }
                           }else {
                               cell[ccount].setCellValue("Attachment is Not Available .");
                           }
                        }else{
                            cell[ccount].setCellValue((String) subList.get(firstMap.get("Query")));
                        }



                        //---------------------


                        for (int h = 0; h < len+4; h++) {
                            if (count % 2 == 0) {
                                if(h==1)
                                cell[h].setCellStyle(cs);
                                else
                                    cell[h].setCellStyle(cs3);
                                
                            }else{
                                  if(h==1)
                                cell[h].setCellStyle(cs2);
                                  else
                                       cell[h].setCellStyle(cs4);
                            }
                        }
                        
                    }
                    for (int i = 0; i < 15; i++) {
                        worksheet.autoSizeColumn((short) i);
                    }
}
               // for(int i =0;i<1;i++){
                    worksheet.setColumnWidth(0, 10 * 256);
                    
                //}
                    workbook.write(fileOut);
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
        }
        return filePath;
    }*/

    
     
     
     
     public String getEmployeeServeyDetailsList() {
        //System.out.println("getEmployeeServeyDetailsList start");
        String filePath = "";
        String[] ServeyReview = new String[15];
        for (int i = 0; i < ServeyReview.length; i++) {
            ServeyReview[i] = "";
        }
        try {


            java.util.List questionaryList = (java.util.List) httpServletRequest.getSession(false).getAttribute("surveyFormQuestionnaireList");
            java.util.List mainList = (java.util.List) httpServletRequest.getSession(false).getAttribute("surveyFormReviewList");

            // Map firstMap = (Map)surveyFormQuestionnaireHeaderList;
            for (int qes = 0; qes < questionaryList.size(); qes++) {
                Map firstMap = (Map) questionaryList.get(qes);
                ServeyReview[qes] = (String) firstMap.get("Query");
               // System.out.println("header-->" + firstMap.get("Query"));

            }

            File file = new File(Properties.getProperty("Emp.ServeyReviewList.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }
            
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator +getSurveyTitle().trim().replaceAll(" ", "_")+".xls");
            filePath = file.getAbsolutePath() + File.separator + getSurveyTitle().trim().replaceAll(" ", "_") +".xls";
            HSSFRow row1;
            //LogisticsDocBean logisticsDocBean = null;
            // index from 0,0... cell A1 is cell(0,0)

            // if(list.size()!=0){//
            //System.out.println("list size-->"+list.size());
            HSSFWorkbook workbook = new HSSFWorkbook();
            System.out.println("filePath " + filePath);
            HSSFSheet worksheet = workbook.createSheet("Servey Review Data");
            for (int i = 0; i < 15; i++) {
                worksheet.setColumnWidth(i, 10 * 256);

            }
       

         




           

            HSSFFont timesBoldFont = workbook.createFont();
            timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            timesBoldFont.setColor(HSSFColor.WHITE.index);
            
            timesBoldFont.setFontName("Calibri");
          //  timesBoldFont.setFontHeightInPoints((short)14);  //for font Size
             HSSFCellStyle headercs = workbook.createCellStyle();
            headercs.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
            headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            headercs.setBorderTop((short) 1); // single line border
            headercs.setBorderBottom((short) 1); // single line border
            headercs.setFont(timesBoldFont);

            HSSFFont footerFont = workbook.createFont();
            footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            footerFont.setFontName("Calibri");

          

        

            // HSSFRow row1;
            // LogisticsDocBean logisticsDocBean = null;
            // index from 0,0... cell A1 is cell(0,0)

            // if(list.size()!=0){//
            //System.out.println("list size-->"+list.size());
            
        
            
            
            HSSFFont font4 = workbook.createFont();
             font4.setColor(HSSFColor.WHITE.index);
             font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
             font4.setFontHeightInPoints((short)14);
             font4.setFontName("Calibri");
       
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setFont(font4);
            
            
            HSSFFont font1 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
             font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
             font1.setFontHeightInPoints((short)14);
             font1.setFontName("Calibri");
                 HSSFCellStyle cs = workbook.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs.setBorderTop((short) 1); // single line border
            cs.setBorderBottom((short) 1); // single line border
            cs.setFont(font1);
            
            
               HSSFCellStyle cs1 = workbook.createCellStyle();
            cs1.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
            cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs1.setFont(font4);
            cs1.setBorderTop((short) 1); // single line border
            cs1.setBorderBottom((short) 1); // single line border
            
            
            
            HSSFCellStyle cs2 = workbook.createCellStyle();

            cs2.setFillForegroundColor(HSSFColor.WHITE.index);
            cs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs2.setBorderTop((short) 1); // single line border
            cs2.setBorderBottom((short) 1); // single line border
            cs2.setFont(font1);
            
            
              HSSFFont font3 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
           //  font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
             font3.setFontHeightInPoints((short)14);
             font3.setFontName("Calibri");
            
            HSSFCellStyle cs3 = workbook.createCellStyle();
            cs3.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs3.setFont(font3);
            cs3.setBorderTop((short) 1); // single line border
            cs3.setBorderBottom((short) 1); // single line border
            
            
            
            HSSFCellStyle cs4 = workbook.createCellStyle();

            cs4.setFillForegroundColor(HSSFColor.WHITE.index);
            cs4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs4.setBorderTop((short) 1); // single line border
            cs4.setBorderBottom((short) 1); // single line border
            cs4.setFont(font3);
            
            
            
            //start	
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
            Date today = new Date();
            String date = DATE_FORMAT.format(today);
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
            Cell cell[] = new Cell[16];
            for (int i = 0; i < 16; i++) {
                cell[i] = row1.createCell((short) i);
            }

            // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
            cell[0].setCellValue("Survey Review Data : "+getSurveyTitle()+"-"+date);
            HSSFCellStyle cellStyleHead = workbook.createCellStyle();
            cellStyleHead.setFont(timesBoldFont);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cell[0].setCellStyle(cellStyle);
            int len=questionaryList.size();
            String mergingInterval = "";
                    switch (len+2) {
                    case 1:
                        mergingInterval = "D";
                        break;
                    case 2:
                        mergingInterval = "E";
                        break;
                    case 3:
                        mergingInterval = "F";
                        break;
                    case 4:
                        mergingInterval = "G";
                        break;
                    case 5:
                        mergingInterval = "H";
                        break;
                    case 6:
                        mergingInterval = "I";
                        break;
                    case 7:
                        mergingInterval = "J";
                        break;
                    case 8:
                        mergingInterval = "K";
                        break;
                    case 9:
                        mergingInterval = "L";
                        break;
                    case 10:
                        mergingInterval = "M";
                        break;
                    case 11:
                        mergingInterval = "N";
                        break;
                    case 12:
                        mergingInterval = "O";
                        break;
                    case 13:
                        mergingInterval = "P";
                        break;
                    case 14:
                        mergingInterval = "Q";
                        break;
                    case 15:
                        mergingInterval = "R";
                        break;
                    case 16:
                        mergingInterval = "S";
                        break;
                    case 17:
                        mergingInterval = "T";
                        break;
                    case 18:
                        mergingInterval = "U";
                        break;
                    case 19:
                        mergingInterval = "V";
                        break;
                }

             worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:" + mergingInterval + "2"));

            //sno
            row1 = worksheet.createRow((short) 2);
            cell[0] = row1.createCell((short) 0);
            cell[0].setCellValue("SNo");
           // cellStyleHead.setFont(font4);
            cellStyleHead.setFont(timesBoldFont);
          //  cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //cellStyleHead.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
           // cellStyleHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);

            worksheet.addMergedRegion(CellRangeAddress.valueOf("A3:A4"));

cell[0] = row1.createCell((short) 1);
            cell[0].setCellValue("EmpNo");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("B3:B4"));

            cell[0] = row1.createCell((short) 2);
            cell[0].setCellValue("Name");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("C3:C4"));

            cell[0] = row1.createCell((short) 3);
            cell[0].setCellValue("E-Mail");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("D3:D4"));

            cell[0] = row1.createCell((short) 4);
            cell[0].setCellValue("Phone");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //  cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));
            if (ServeyReview[0] != "") {
                cell[0] = row1.createCell((short) 5);
                cell[0].setCellValue(ServeyReview[0]);
                cell[0].setCellStyle(cs1);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //   cell[0].setCellStyle(cellStyleHead);
                
                worksheet.addMergedRegion(CellRangeAddress.valueOf("F3:F4"));
            }
            if (ServeyReview[1] != "") {
                cell[0] = row1.createCell((short) 6);
                cell[0].setCellValue(ServeyReview[1]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("G3:G4"));
            }
            if (ServeyReview[2] != "") {
                cell[0] = row1.createCell((short) 7);
                cell[0].setCellValue(ServeyReview[2]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("H3:H4"));
            }
            if (ServeyReview[3] != "") {
                cell[0] = row1.createCell((short) 8);
                cell[0].setCellValue(ServeyReview[3]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("I3:I4"));
            }
            if (ServeyReview[4] != "") {
                cell[0] = row1.createCell((short) 9);
                cell[0].setCellValue(ServeyReview[4]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("J3:J4"));
            }
            if (ServeyReview[5] != "") {
                cell[0] = row1.createCell((short) 10);
                cell[0].setCellValue(ServeyReview[5]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("K3:K4"));
            }
            if (ServeyReview[6] != "") {
                cell[0] = row1.createCell((short) 11);
                cell[0].setCellValue(ServeyReview[6]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("L3:L4"));
            }
            if (ServeyReview[7] != "") {
                cell[0] = row1.createCell((short) 12);
                cell[0].setCellValue(ServeyReview[7]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("M3:M4"));
            }
            if (ServeyReview[8] != "") {
                cell[0] = row1.createCell((short) 13);
                cell[0].setCellValue(ServeyReview[8]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("N3:N4"));
            }
            if (ServeyReview[9] != "") {
                cell[0] = row1.createCell((short) 14);
                cell[0].setCellValue(ServeyReview[9]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("O3:O4"));
            }
            if (ServeyReview[10] != "") {
                cell[0] = row1.createCell((short) 15);
                cell[0].setCellValue(ServeyReview[10]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("P3:P4"));
            }
            if (ServeyReview[11] != "") {
                cell[0] = row1.createCell((short) 16);
                cell[0].setCellValue(ServeyReview[11]);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //  cell[0].setCellStyle(cellStyleHead);
                cell[0].setCellStyle(cs1);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("Q3:Q4"));
            }
            int count = 4;
//            if (mainList.size() > 0) {
//                Map serveyReviewMap = null;
//                for (int i = 0; i < mainList.size(); i++) {
//                    serveyReviewMap = (Map) mainList.get(i);
//                    row1 = worksheet.createRow((short) count++);
//                    for (int j = 0; j < 6; j++) {
//                        cell[j] = row1.createCell((short) j);
//                    }
//                    cell[0].setCellValue((Integer) (i + 1));
//                    cell[1].setCellValue((String) serveyReviewMap.get("Name"));
//                    cell[2].setCellValue((String) serveyReviewMap.get("Email"));
//                    cell[3].setCellValue((String) serveyReviewMap.get("Phone"));
//                    for (int j = 0; j < mainList.size(); j++) {
//                        java.util.List subList = (java.util.List) mainList.get(j);
//                        for (int qes = 0, ccount = 4; qes < questionaryList.size(); qes++, ccount++) {
//                            Map firstMap = (Map) questionaryList.get(qes);
//                            cell[ccount].setCellValue((String) subList.get(j));
//                        }
//                    }
            //---------------------
           // System.out.println("mainList Size"+mainList.size());
            if (mainList.size() > 0) {

                for (int k = 0; k < mainList.size(); k++) {

                    java.util.Map subList = (java.util.Map) mainList.get(k);
                    row1 = worksheet.createRow((short) count++);
                    for (int j = 0; j < 16; j++) {
                        cell[j] = row1.createCell((short) j);
                    }
                    //System.out.println("subList-->"+subList);
                    cell[0].setCellValue((Integer) (k + 1));
                  //  cell[1].setCellValue((String) subList.get("EmpNo"));
                    if(subList.get("EmpNo")!=null&&!"-1".equals(subList.get("EmpNo").toString())){
                         cell[1].setCellValue((String) subList.get("EmpNo"));
                    }else{
                        cell[1].setCellValue("-");
                    }
                    cell[2].setCellValue((String) subList.get("Name"));

                    cell[3].setCellValue((String) subList.get("Email").toString());

                    cell[4].setCellValue((String) subList.get("Phone").toString());

                    for (int qes = 0, ccount = 5; qes < questionaryList.size(); qes++, ccount++) {

                        Map firstMap = (Map) questionaryList.get(qes);

                       if(firstMap.get("OptionType").toString().equals("Attachment")){
                           /* File filee=new File((String) subList.get(firstMap.get("Query")));
                            if(filee.exists()){
                               // System.out.println("exits");
                                 cell[ccount].setCellValue("Attachment is Available plz download it from Hubble");
                            }else{
                               // System.out.println("not exits");
                                cell[ccount].setCellValue(" ");
                            }*/
                           if(subList.get(firstMap.get("Query"))!=null && !"".equals(subList.get(firstMap.get("Query")))){
                                File filee=new File((String) subList.get(firstMap.get("Query")));
                            if(filee.exists()){
                               // System.out.println("exits");
                                 cell[ccount].setCellValue("Attachment is Available please download it from Hubble");
                            }else{
                               // System.out.println("not exits");
                                cell[ccount].setCellValue("Attachment is Not Available .");
                            }
                           }else {
                               cell[ccount].setCellValue("Attachment is Not Available .");
                           }
                        }else{
                            cell[ccount].setCellValue((String) subList.get(firstMap.get("Query")));
                        }



                        //---------------------


                        for (int h = 0; h < len+5; h++) {
                            if (count % 2 == 0) {
                                if(h==1)
                                cell[h].setCellStyle(cs);
                                else
                                    cell[h].setCellStyle(cs3);
                                
                            }else{
                                  if(h==1)
                                cell[h].setCellStyle(cs2);
                                  else
                                       cell[h].setCellStyle(cs4);
                            }
                        }
                        
                    }
                    for (int i = 0; i < 16; i++) {
                        worksheet.autoSizeColumn((short) i);
                    }
}
               // for(int i =0;i<1;i++){
                    worksheet.setColumnWidth(0, 10 * 256);
                    
                //}
                    workbook.write(fileOut);
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
        }
        return filePath;
    }
     
     
     
 public String getEmployeeServeyAttachments() {
        String filePath = "";
        try {
            java.util.List questionaryList = (java.util.List) httpServletRequest.getSession(false).getAttribute("surveyFormQuestionnaireList");
            java.util.List mainList = (java.util.List) httpServletRequest.getSession(false).getAttribute("surveyFormReviewList");
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
            Date today = new Date();
            String date = DATE_FORMAT.format(today);
            File file = new File(Properties.getProperty("Emp.DownloadBulkAttchments.Path")+"/"+getSurveyTitle().trim().replaceAll(" ", "_")+"_"+date);
            File filee=null;
             File theFile=null;
              if (!file.exists()) {
                file.mkdirs();
            }
               for (int k = 0; k < mainList.size(); k++) {
                    java.util.Map subList = (java.util.Map) mainList.get(k);
                    for (int qes = 0; qes < questionaryList.size(); qes++) {
                        Map firstMap = (Map) questionaryList.get(qes);
                        if (firstMap.get("OptionType").toString().equals("Attachment")) {
                            String subFolder = (String) (firstMap.get("Query"));
                            File  subFolderFile = new File(file+"/"+subFolder);
                         if (!subFolderFile.exists()) {
                             subFolderFile.mkdirs();
                         }
                      //  System.out.println("In fillee Query-->"+subList.get(firstMap.get("Query")));
                        if(subList.get(firstMap.get("Query"))!=null){
                             filee = new File((String) subList.get(firstMap.get("Query")));
                             // System.out.println("In fillee1233-->"+filee.getAbsolutePath());
                            if (filee.exists()) {
                               // System.out.println("In fillee-->"+filee);
                                FileUtils.copyFileToDirectory(filee, subFolderFile);
                                zipFolder(file.getAbsolutePath(), file.getAbsolutePath()+".zip");
         filePath = file.getAbsolutePath()+".zip";
                }
     
                        }
                        }
                    }
               }
               
        } catch (Exception ex) {
            ex.printStackTrace();
           
        }
        return filePath;
    }
    
   public void zipFolder(String srcFolder, String destZipFile) throws Exception {
    ZipOutputStream zip = null;
    FileOutputStream fileWriter = null;
    fileWriter = new FileOutputStream(destZipFile);
    zip = new ZipOutputStream(fileWriter);
    addFolderToZip("", srcFolder, zip);
    zip.flush();
    zip.close();
  }

   private void addFileToZip(String path, String srcFile, ZipOutputStream zip)
      throws Exception {
    File folder = new File(srcFile);
    if (folder.isDirectory()) {
      addFolderToZip(path, srcFile, zip);
    } else {
      byte[] buf = new byte[1024];
      int len;
      FileInputStream in = new FileInputStream(srcFile);
      zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
      while ((len = in.read(buf)) > 0) {
        zip.write(buf, 0, len);
      }
    }
  }

   private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
      throws Exception {
    File folder = new File(srcFolder);
    for (String fileName : folder.list()) {
      if (path.equals("")) {
        addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
      } else {
        addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
      }
    }
  }public String getIOTDeatilsList() {
        String filePath = "";
        String[] IOTDeatilsList = new String[15];
        for (int i = 0; i < IOTDeatilsList.length; i++) {
            IOTDeatilsList[i] = "";
        }
        try {

            java.util.List mainList = (java.util.List) httpServletRequest.getSession(false).getAttribute("websiteInfoList");
            File file = new File(Properties.getProperty("Emp.IOTDeatailsList.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() +"/IOTDetails.xls");
            filePath = file.getAbsolutePath() +  "/IOTDetails.xls";
            HSSFRow row1;
            //LogisticsDocBean logisticsDocBean = null;
            // index from 0,0... cell A1 is cell(0,0)

            // if(list.size()!=0){//
            //System.out.println("list size-->"+list.size());
            HSSFWorkbook workbook = new HSSFWorkbook();
            System.out.println("filePath " + filePath);
            HSSFSheet worksheet = workbook.createSheet("IOT Details");

            HSSFFont timesBoldFont = workbook.createFont();
            timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            timesBoldFont.setColor(HSSFColor.WHITE.index);

            timesBoldFont.setFontName("Calibri");
            //  timesBoldFont.setFontHeightInPoints((short)14);  // for font Size
            HSSFCellStyle headercs = workbook.createCellStyle();
            headercs.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
            headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            headercs.setBorderTop((short) 1); // single line border
            headercs.setBorderBottom((short) 1); // single line border
            headercs.setFont(timesBoldFont);

            HSSFFont footerFont = workbook.createFont();
            footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            footerFont.setFontName("Calibri");

            HSSFFont font4 = workbook.createFont();
            font4.setColor(HSSFColor.WHITE.index);
            font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font4.setFontHeightInPoints((short) 14);
            font4.setFontName("Calibri");

            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setFont(font4);


            HSSFFont font1 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
            font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font1.setFontHeightInPoints((short) 14);
            font1.setFontName("Calibri");

            HSSFCellStyle cs = workbook.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs.setBorderTop((short) 1); // single line border
            cs.setBorderBottom((short) 1); // single line border
            cs.setFont(font1);

            HSSFCellStyle cs1 = workbook.createCellStyle();
            cs1.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
            cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs1.setFont(font4);
            cs1.setBorderTop((short) 1); // single line border
            cs1.setBorderBottom((short) 1); // single line border

            HSSFCellStyle cs2 = workbook.createCellStyle();
            cs2.setFillForegroundColor(HSSFColor.WHITE.index);
            cs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs2.setBorderTop((short) 1); // single line border
            cs2.setBorderBottom((short) 1); // single line border
            cs2.setFont(font1);

            HSSFFont font3 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
            //  font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font3.setFontHeightInPoints((short) 14);
            font3.setFontName("Calibri");

            HSSFCellStyle cs3 = workbook.createCellStyle();
            cs3.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs3.setFont(font3);
            cs3.setBorderTop((short) 1); // single line border 
            cs3.setBorderBottom((short) 1); // single line border

            HSSFCellStyle cs4 = workbook.createCellStyle();
            cs4.setFillForegroundColor(HSSFColor.WHITE.index);
            cs4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs4.setBorderTop((short) 1); // single line border
            cs4.setBorderBottom((short) 1); // single line border
            cs4.setFont(font3);

            //start	
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
            Date today = new Date();
            String date = DATE_FORMAT.format(today);
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
            Cell cell[] = new Cell[11];
            for (int i = 0; i < 11; i++) {
                cell[i] = row1.createCell((short) i);
            }
            // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
            cell[0].setCellValue("IOT Details : " + date);
            HSSFCellStyle cellStyleHead = workbook.createCellStyle();
            cellStyleHead.setFont(timesBoldFont);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cell[0].setCellStyle(cellStyle);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:K2"));

            row1 = worksheet.createRow((short) 2);
            cell[0] = row1.createCell((short) 0);
            cell[0].setCellValue("SNo");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("A3:A4"));

            cell[0] = row1.createCell((short) 1);
            cell[0].setCellValue("First Name");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("B3:B4"));

            cell[0] = row1.createCell((short) 2);
            cell[0].setCellValue("Last Name");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("C3:C4"));

            cell[0] = row1.createCell((short) 3);
            cell[0].setCellValue("E-Mail");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("D3:D4"));

            cell[0] = row1.createCell((short) 4);
            cell[0].setCellValue("Phone");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));

            cell[0] = row1.createCell((short) 5);
            cell[0].setCellValue("Organization");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("F3:F4"));

            cell[0] = row1.createCell((short) 6);
            cell[0].setCellValue("City");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("G3:G4"));

            cell[0] = row1.createCell((short) 7);
            cell[0].setCellValue("Designation");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("H3:H4"));

            cell[0] = row1.createCell((short) 8);
            cell[0].setCellValue("Zip Code");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("I3:I4"));

            cell[0] = row1.createCell((short) 9);
            cell[0].setCellValue("Description");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("J3:J4"));

            cell[0] = row1.createCell((short) 10);
            cell[0].setCellValue("Created Date");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("K3:K4"));

            int count = 4;
            if (mainList.size() > 0) {
                for (int k = 0; k < mainList.size(); k++) {
                    java.util.Map subList = (java.util.Map) mainList.get(k);
                    row1 = worksheet.createRow((short) count++);
                    for (int j = 0; j < 11; j++) {
                        cell[j] = row1.createCell((short) j);
                    }
                    //System.out.println("subList-->"+subList);
                    cell[0].setCellValue((Integer) (k + 1));
                    cell[1].setCellValue((String) subList.get("FirstName"));
                    cell[2].setCellValue((String) subList.get("LastName"));
                    cell[3].setCellValue((String) subList.get("EmailId").toString());
                    cell[4].setCellValue((String) subList.get("Phone").toString());
                    cell[5].setCellValue((String) subList.get("Organization").toString());
                    cell[6].setCellValue((String) subList.get("City").toString());
                    cell[7].setCellValue((String) subList.get("Designation").toString());
                    cell[8].setCellValue((String) subList.get("ZipCode").toString());
                    cell[9].setCellValue((String) subList.get("Description").toString());
                    cell[10].setCellValue((String) subList.get("CreatedDate").toString().substring(0, 10));

                    for (int h = 0; h < 11; h++) {
                        if (count % 2 == 0) {
//                            if (h == 1) {
//                                cell[h].setCellStyle(cs);
//                            } else {
                            cell[h].setCellStyle(cs3);
                            // }
                        } else {
//                            if (h == 1) {
//                                cell[h].setCellStyle(cs2);
//                            } else {
                            cell[h].setCellStyle(cs4);
                            //  }
                        }
                    }

                    for (int i = 0; i < 11; i++) {
                        worksheet.autoSizeColumn((short) i);
                    }
                }
                // for(int i =0;i<1;i++){
                worksheet.setColumnWidth(0, 10 * 256);
                worksheet.setColumnWidth(1, 30 * 256);
                worksheet.setColumnWidth(2, 30 * 256);
                worksheet.setColumnWidth(5, 30 * 256);
                worksheet.setColumnWidth(8, 25 * 256);
                worksheet.setColumnWidth(10, 25 * 256);
                //}
                workbook.write(fileOut);
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
        }
        return filePath;
    }
  
  
public String getSignatureDeatilsList() {
        String filePath = "";
        String[] IOTDeatilsList = new String[15];
        for (int i = 0; i < IOTDeatilsList.length; i++) {
            IOTDeatilsList[i] = "";
        }
        try {

            java.util.List mainList = (java.util.List) httpServletRequest.getSession(false).getAttribute("websiteInfoList");
            File file = new File(Properties.getProperty("Emp.SignatureDeatailsList.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() +"/SignatureDetails.xls");
            filePath = file.getAbsolutePath() +  "/SignatureDetails.xls";
            HSSFRow row1;
            //LogisticsDocBean logisticsDocBean = null;
            // index from 0,0... cell A1 is cell(0,0)

            // if(list.size()!=0){//
            //System.out.println("list size-->"+list.size());
            HSSFWorkbook workbook = new HSSFWorkbook();
            System.out.println("filePath " + filePath);
            HSSFSheet worksheet = workbook.createSheet("Signature Details");

            HSSFFont timesBoldFont = workbook.createFont();
            timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            timesBoldFont.setColor(HSSFColor.WHITE.index);

            timesBoldFont.setFontName("Calibri");
            //  timesBoldFont.setFontHeightInPoints((short)14);  // for font Size
            HSSFCellStyle headercs = workbook.createCellStyle();
            headercs.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
            headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            headercs.setBorderTop((short) 1); // single line border
            headercs.setBorderBottom((short) 1); // single line border
            headercs.setFont(timesBoldFont);

            HSSFFont footerFont = workbook.createFont();
            footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            footerFont.setFontName("Calibri");

            HSSFFont font4 = workbook.createFont();
            font4.setColor(HSSFColor.WHITE.index);
            font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font4.setFontHeightInPoints((short) 14);
            font4.setFontName("Calibri");

            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setFont(font4);


            HSSFFont font1 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
            font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font1.setFontHeightInPoints((short) 14);
            font1.setFontName("Calibri");

            HSSFCellStyle cs = workbook.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs.setBorderTop((short) 1); // single line border
            cs.setBorderBottom((short) 1); // single line border
            cs.setFont(font1);

            HSSFCellStyle cs1 = workbook.createCellStyle();
            cs1.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
            cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs1.setFont(font4);
            cs1.setBorderTop((short) 1); // single line border
            cs1.setBorderBottom((short) 1); // single line border

            HSSFCellStyle cs2 = workbook.createCellStyle();
            cs2.setFillForegroundColor(HSSFColor.WHITE.index);
            cs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs2.setBorderTop((short) 1); // single line border
            cs2.setBorderBottom((short) 1); // single line border
            cs2.setFont(font1);

            HSSFFont font3 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
            //  font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font3.setFontHeightInPoints((short) 14);
            font3.setFontName("Calibri");

            HSSFCellStyle cs3 = workbook.createCellStyle();
            cs3.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs3.setFont(font3);
            cs3.setBorderTop((short) 1); // single line border 
            cs3.setBorderBottom((short) 1); // single line border

            HSSFCellStyle cs4 = workbook.createCellStyle();
            cs4.setFillForegroundColor(HSSFColor.WHITE.index);
            cs4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs4.setBorderTop((short) 1); // single line border
            cs4.setBorderBottom((short) 1); // single line border
            cs4.setFont(font3);

            //start	
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
            Date today = new Date();
            String date = DATE_FORMAT.format(today);
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
            Cell cell[] = new Cell[9];
            for (int i = 0; i < 9; i++) {
                cell[i] = row1.createCell((short) i);
            }
            // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
            cell[0].setCellValue("Signature Details : " + date);
            HSSFCellStyle cellStyleHead = workbook.createCellStyle();
            cellStyleHead.setFont(timesBoldFont);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cell[0].setCellStyle(cellStyle);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:K2"));

            row1 = worksheet.createRow((short) 2);
            cell[0] = row1.createCell((short) 0);
            cell[0].setCellValue("SNo");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("A3:A4"));

            cell[0] = row1.createCell((short) 1);
            cell[0].setCellValue("Employee Name");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("B3:B4"));

            cell[0] = row1.createCell((short) 2);
            cell[0].setCellValue("Designation");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("C3:C4"));

            cell[0] = row1.createCell((short) 3);
            cell[0].setCellValue("E-Mail");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("D3:D4"));

            cell[0] = row1.createCell((short) 4);
            cell[0].setCellValue("Work Phone");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));

            cell[0] = row1.createCell((short) 5);
            cell[0].setCellValue("Mobile phone");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("F3:F4"));

            cell[0] = row1.createCell((short) 6);
            cell[0].setCellValue("Location");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("G3:G4"));

            cell[0] = row1.createCell((short) 7);
            cell[0].setCellValue("Created Date");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("H3:H4"));

            cell[0] = row1.createCell((short) 8);
            cell[0].setCellValue("Activity");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("I3:I4"));

        

            int count = 4;
            if (mainList.size() > 0) {
                for (int k = 0; k < mainList.size(); k++) {
                    java.util.Map subList = (java.util.Map) mainList.get(k);
                    row1 = worksheet.createRow((short) count++);
                    for (int j = 0; j < 9; j++) {
                        cell[j] = row1.createCell((short) j);
                    }
                    //System.out.println("subList-->"+subList);
                    cell[0].setCellValue((Integer) (k + 1));
                    cell[1].setCellValue((String) subList.get("EmployeeName"));
                    cell[2].setCellValue((String) subList.get("Designation"));
                    cell[3].setCellValue((String) subList.get("EmailId").toString());
                    cell[4].setCellValue((String) subList.get("WorkPhone").toString());
                    cell[5].setCellValue((String) subList.get("Mobile").toString());
                    cell[6].setCellValue((String) subList.get("Location").toString());
                    cell[7].setCellValue((String) subList.get("CreatedDate").toString().substring(0, 10));
                    cell[8].setCellValue((String) subList.get("Activity").toString());
                  
                    for (int h = 0; h < 9; h++) {
                        if (count % 2 == 0) {
//                            if (h == 1) {
//                                cell[h].setCellStyle(cs);
//                            } else {
                            cell[h].setCellStyle(cs3);
                            // }
                        } else {
//                            if (h == 1) {
//                                cell[h].setCellStyle(cs2);
//                            } else {
                            cell[h].setCellStyle(cs4);
                            //  }
                        }
                    }

                    for (int i = 0; i < 11; i++) {
                        worksheet.autoSizeColumn((short) i);
                    }
                }
                // for(int i =0;i<1;i++){
                worksheet.setColumnWidth(0, 10 * 256);
                worksheet.setColumnWidth(1, 30 * 256);
                worksheet.setColumnWidth(2, 30 * 256);
                worksheet.setColumnWidth(5, 30 * 256);
                worksheet.setColumnWidth(8, 25 * 256);
                worksheet.setColumnWidth(10, 25 * 256);
                //}
                workbook.write(fileOut);
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
        }
        return filePath;
    }


    /**
     * @return the surveyTitle
     */
    public String getSurveyTitle() {
        return surveyTitle;
    }

    /**
     * @param surveyTitle the surveyTitle to set
     */
    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }
}



