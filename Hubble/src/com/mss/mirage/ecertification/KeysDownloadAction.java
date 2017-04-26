/*******************************************************************************
 *
 * Project : Miracle Supply Chain Visibility Portal v1.0
 *
 * Package : com.mss.ediscv.griddownload
 *
 * Date    : april 17, 2013 5:22:19 pm
 *
 * Author  : Santosh kola <skola2@miraclesoft.com>
 *
 * File    : GridDownloadAction.java
 *
 * 
 * *****************************************************************************
 */



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.ecertification;



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
public class KeysDownloadAction implements
        Action,ServletRequestAware,ServletResponseAware{
    
   
    // private String URL="/images/flower.GIF";
    private String contentDisposition="FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String fileName;
    private String downloadType;
    private String sheetType;
    private int practiceId;
    private int topicId;
     private String createdDate;
    /** Creates a new instance of DownloadAction */
    public KeysDownloadAction() {
    }
    
 
    public String execute() throws Exception {
        return null;
    }
    
 
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
  
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        
        String responseString="";
        try {
         
            String fileLocation ="";
            //For creating Excel grind from Search result Grid
            
           
           fileLocation = generateExamKeysList(getTopicId() ,getCreatedDate());
             
            httpServletResponse.setContentType("application/force-download");
           // File file = new File(Properties.getProperty("mscvp.docCreationPath")+"SearchedExcelDocument.xls");
                    File file = new File(fileLocation); 
                   Date date = new Date();
                   
                   fileName = (date.getYear()+1900)+"_"+(date.getMonth()+1)+"_"+date.getDate()+"_"+file.getName(); 
            if(file.exists()){
            inputStream = new FileInputStream(file);
            outputStream =  httpServletResponse.getOutputStream();
            httpServletResponse.setHeader("Content-Disposition","attachment;filename=\"" + fileName +"\"");
            int noOfBytesRead = 0;
            byte[] byteArray = null;
            while(true) {
                byteArray = new byte[1024];
                noOfBytesRead = inputStream.read(byteArray);
                if(noOfBytesRead==-1) break;
                outputStream.write(byteArray, 0, noOfBytesRead);
            }
            responseString = "downLoaded!!";
                httpServletResponse.setContentType(getDownloadType());
                httpServletResponse.getWriter().write(responseString);
            }else{
                throw new FileNotFoundException("File not found");
            } 
        }catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(KeysDownloadAction.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }/*catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }*/finally {
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
    
    public String generateExamKeysList(int topicId ,String createdDate){
        String filePath = "";
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            File file = new File(Properties.getProperty("ExamKeys.Path"));
            if(!file.exists())
                file.mkdirs();

             FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath()+Properties.getProperty("OS.Compatabliliy.Download")+"ExamKeysList.xls");
            filePath = file.getAbsolutePath()+Properties.getProperty("OS.Compatabliliy.Download")+"ExamKeysList.xls";
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
             connection=ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("SELECT tblEcertValidatorKeys.VKey AS Vkey,tblEcertTopics.TopicName AS NAME, " +
              "tblEcertValidatorKeys.Duration AS Duration,tblEcertValidatorKeys.MinMarks AS minmarks," +
              "tblEcertValidatorKeys.NoOfQuestions AS COUNT FROM " +
                    "tblEcertValidatorKeys LEFT OUTER JOIN tblEcertTopics ON " +
                    "(tblEcertTopics.ID=tblEcertValidatorKeys.TopicId)" +
                    "  WHERE tblEcertValidatorKeys.STATUS='Active' AND tblEcertValidatorKeys.TopicId = ? AND tblEcertValidatorKeys.CreatedBy='"+userId+"' AND DATE(tblEcertValidatorKeys.CreatedDate) = DATE('"+DateUtility.getInstance().convertStringToMySQLDate(createdDate)+"')");
            

            //preStmt.setInt(1,id);
           
            preStmt.setInt(1,topicId);
           
           
            resultSet = preStmt.executeQuery();
            
             HSSFWorkbook hssfworkbook = new HSSFWorkbook();
      HSSFSheet sheet = hssfworkbook.createSheet("Active Keys");
     
      HSSFCellStyle cs = hssfworkbook.createCellStyle();
      HSSFDataFormat df = hssfworkbook.createDataFormat();
      HSSFRow row = sheet.createRow((short)0);
      HSSFCell cell = row.createCell((short)0);
     // HSSFRow row1 = sheet.createRow((short)0);
      HSSFCell cell1 = row.createCell((short)1);
      HSSFCell cell2 = row.createCell((short)2);
      HSSFCell cell3 = row.createCell((short)3);
      HSSFCell cell4 = row.createCell((short)4);
      cell.setCellValue("Exam Name");
       cell1.setCellValue("Exam Key");
       cell2.setCellValue("No of Questions");
       cell3.setCellValue("Minimum Marks");
       cell4.setCellValue("Duration");
       
       
      cell.setCellStyle(cs);
      cell1.setCellStyle(cs);
      cell2.setCellStyle(cs);
        cell3.setCellStyle(cs);
         cell4.setCellStyle(cs);
     int count = 1;
      while(resultSet.next()){
     
        row = sheet.createRow((short)count++);
       cell = row.createCell((short)0);
    //  HSSFRow row2 = sheet.createRow((short)0);
      cell1 = row.createCell((short)1);
      cell2 = row.createCell((short)2);
      cell3 = row.createCell((short)3);
      cell4 = row.createCell((short)4);
        cell.setCellValue(resultSet.getString("Name"));
       cell1.setCellValue(resultSet.getString("vkey"));
       cell2.setCellValue(resultSet.getString("COUNT"));
       cell3.setCellValue(resultSet.getString("minmarks"));
       cell4.setCellValue(resultSet.getString("Duration"));
      cell.setCellStyle(cs);
      cell1.setCellStyle(cs);
       cell2.setCellStyle(cs);
      cell3.setCellStyle(cs);
      cell4.setCellStyle(cs);
      
      }
      sheet.autoSizeColumn((short)0);
      sheet.autoSizeColumn((short)1);
       sheet.autoSizeColumn((short)2);
        sheet.autoSizeColumn((short)3);
         sheet.autoSizeColumn((short)4);
      hssfworkbook.write(fileOut);
      fileOut.flush();
      fileOut.close();
      
      
            
            
            
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        catch (Exception e) {
			e.printStackTrace();
		}finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(Exception se){
                se.printStackTrace();
            }
        }
        return filePath;
    }
    
   
    
    
    /**
     * @return the downloadType
     */
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

    public int getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(int practiceId) {
        this.practiceId = practiceId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    /**
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
}
