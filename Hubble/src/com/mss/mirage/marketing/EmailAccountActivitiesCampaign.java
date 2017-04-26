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
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author miracle
 */
public class EmailAccountActivitiesCampaign implements
        Action, ServletRequestAware, ServletResponseAware {

    private String contentDisposition = "FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String fileName;
    private String downloadType;
    private String sheetType;
    private String fromDate;
    private String activityType;
    private int campaignId;

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
            //  fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus());
            fileLocation = generateEmailCampaignList(getCampaignId());
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

    public String generateEmailCampaignList(int campaignId) {
        String filePath = "";
        
        try {
        String CampaignName= DataSourceDataProvider.getInstance().getCampaignNameByCampaignId(campaignId);
            //System.out.println("the Campaign Name="+CampaignName);
            //System.out.println("the from date selected is:"+getFromDate());
            //System.out.println("the activity type is:"+getActivityType());
            //System.out.println("the campaign name is:"+getCampaignId());
        java.util.List finalList = getEmailCampaignList(fromDate,campaignId);
                  File file = new File(Properties.getProperty("Emp.EmailCampaign.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + CampaignName+"_"+DateUtility.getInstance().getMysqlDate(fromDate)+".xls");
            filePath = file.getAbsolutePath() + Properties.getProperty("OS.Compatabliliy.Download") + CampaignName+"_"+DateUtility.getInstance().getMysqlDate(fromDate)+".xls";
         HSSFWorkbook hssfworkbook = new HSSFWorkbook();
            HSSFSheet sheet = hssfworkbook.createSheet("Email Campaign List");

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
            
            
                HSSFRow row = sheet.createRow((short) 0);
                HSSFCell cell = row.createCell((short) 0);
                // HSSFRow row1 = sheet.createRow((short)0);
                //HSSFCell cell1 = row.createCell((short) 1);
                cell.setCellValue("Email");
                //cell1.setCellValue("Date");
                // cell7.setCellValue("Leaves");

                cell.setCellStyle(headercs);
                //cell1.setCellStyle(headercs);
                //   while (resultSet.next()) {
                int count =1;
                if (finalList.size() > 0) {
                    Map emailMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        emailMap = (Map)finalList.get(i);
                        row = sheet.createRow( count++);
                        cell = row.createCell((short) 0);
                        //  HSSFRow row2 = sheet.createRow((short)0);
                        //cell1 = row.createCell((short) 1);
                        cell.setCellValue((String) emailMap.get("Email"));
                        //cell1.setCellValue((String) emailMap.get("Date"));



                        cell.setCellStyle(cs);
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
                sheet.autoSizeColumn((short) 0);
                //sheet.autoSizeColumn((short) 1);
        
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
        }
        return filePath;
    }


    public List getEmailCampaignList(String fromDate,  int campaignId) throws ServiceLocatorException {
        String query = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        HashMap map = null;
        List finalList = new ArrayList();
        query ="SELECT DISTINCT tblCrmContact.Email1 as Email  FROM tblCrmContact,tblCrmActivity WHERE tblCrmActivity.ContactId = tblCrmContact.Id"
        +" AND tblCrmActivity.CampaignId = "+campaignId+"  AND tblCrmActivity.CreatedDate >= '"+ DateUtility.getInstance().getMysqlDate(fromDate) +"%' AND Email1!=''";
       System.out.println("query-->"+query);
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                map = new HashMap();
                map.put("Email",resultSet.getString("Email"));
                //map.put("Date",resultSet.getString("Date"));
                finalList.add(map);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return finalList;
    }
        
    

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
}