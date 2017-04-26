/*
 * DownloadAction.java
 *
 * Created on july16 2013
 *Author :vkandregula@miraclesoft.com
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.recruitment.attachments;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

//new
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author vkandregula@miraclesoft.com
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class DownloadActionForResume implements
        Action,ServletRequestAware,ServletResponseAware{
    
    private String inputPath;
    // private String URL="/images/flower.GIF";
    private String contentDisposition="FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private int id;
    private String attachmentLocation;
    private String fileName;
    private String locationAvailable;
    
    /** Creates a new instance of DownloadAction */
    public DownloadActionForResume() {
    }
    
    public String execute() throws Exception {
        return null;
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
        
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preStmt=null;
        String queryString = null;
        Timestamp downloadTime=null;
        String dwnloadTime=null;
        String resumeSubmitDetails=null;
        long hours;
        int isSuccess=0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
              int resumeSubmissionId=Integer.parseInt(httpServletRequest.getParameter("resumeSubmissionId").toString());
              queryString="select DownloadTime from tblRecResumeSubmission WHERE Id='"+resumeSubmissionId+"'";
             statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                downloadTime=resultSet.getTimestamp("DownloadTime");
                
            }
            dwnloadTime=downloadTime.toString();
            if(dwnloadTime.equalsIgnoreCase("1950-01-31 00:00:00.0"))
            {
           
              resumeSubmitDetails="UPDATE tblRecResumeSubmission set DownloadTime=? WHERE Id='"+resumeSubmissionId+"'";
            
                preStmt = connection.prepareStatement(resumeSubmitDetails);
               // preStmt.setTimestamp(1,new Timestamp(new java.util.Date().getTime()));
                 preStmt.setTimestamp(1,DateUtility.getInstance().getCurrentMySqlDateTime());
                isSuccess = preStmt.executeUpdate();  
                  try {
            this.setLocationAvailable(httpServletRequest.getParameter("availableLocation"));
            
            if(this.getLocationAvailable() !=null){
                this.setAttachmentLocation(httpServletRequest.getParameter("resumeLocation"));
            }else {
                this.setId(Integer.parseInt(httpServletRequest.getParameter("id").toString()));
                this.setAttachmentLocation(ServiceLocator.getConsultantAttachmentService().getAttachmentLocation(this.getId()));
            }
            fileName = this.getAttachmentLocation().substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getAttachmentLocation().length());
            //fileName = this.getAttachmentLocation().substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download1"))+1,getAttachmentLocation().length());
            
            httpServletResponse.setContentType("application/force-download");
            
            File file = new File(getAttachmentLocation());
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
        }
                   catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
                
           }
            else
            {
                
              //hours=new Timestamp(new java.util.Date().getTime()).getHours()-downloadTime.getHours();  
              hours=DataSourceDataProvider.getInstance().getTimeDifferenceInHours(downloadTime);
            
              if(hours<=1)
              {
                  try {
            this.setLocationAvailable(httpServletRequest.getParameter("availableLocation"));
            
            if(this.getLocationAvailable() !=null){
                this.setAttachmentLocation(httpServletRequest.getParameter("resumeLocation"));
            }else {
                this.setId(Integer.parseInt(httpServletRequest.getParameter("id").toString()));
                this.setAttachmentLocation(ServiceLocator.getConsultantAttachmentService().getAttachmentLocation(this.getId()));
            }
            fileName = this.getAttachmentLocation().substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getAttachmentLocation().length());
            //fileName = this.getAttachmentLocation().substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download1"))+1,getAttachmentLocation().length());
            
            httpServletResponse.setContentType("application/force-download");
            
            File file = new File(getAttachmentLocation());
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
        }
                   catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }  
              }
              
              else
              {
               httpServletResponse.sendRedirect("/Hubble/exception/AccessFailed.jsp");
              }
              
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                    statement.close();
                    statement = null;
                }
                if(preStmt!=null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }
        
        
     
    }
    }
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getAttachmentLocation() {
        return attachmentLocation;
    }
    
    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }
    
    public String getLocationAvailable() {
        return locationAvailable;
    }
    
    public void setLocationAvailable(String locationAvailable) {
        this.locationAvailable = locationAvailable;
    }
    
}
