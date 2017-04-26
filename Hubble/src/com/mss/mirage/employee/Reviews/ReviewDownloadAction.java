/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.Reviews;

/**
 *
 * @author miracle
 * 
 */
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
public class ReviewDownloadAction implements Action,ServletRequestAware,ServletResponseAware {
    
    
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

    @Override
    public String execute() throws Exception {
         return null;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
       this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
          try {
            //this.setId(Integer.parseInt(httpServletRequest.getParameter("reviewId").toString()));
             
            String fileLocation=getAttachmentLocationByReviewId(Integer.parseInt(httpServletRequest.getParameter("reviewId").toString()));
             
            //fileName = this.getAttachmentLocation()
            //.substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getAttachmentLocation().length());
           // fileName = httpServletRequest.getParameter("fileName").toString();
            httpServletResponse.setContentType("application/force-download");
            
            File file = new File(fileLocation);
            fileName = file.getName();
            System.out.println("Filename"+fileName);
            inputStream = new FileInputStream(file);
            outputStream =  httpServletResponse.getOutputStream();
            httpServletResponse.setHeader("Content-Disposition","attachment; filename=\"" + fileName +"\"");
            int noOfBytesRead = 0;
            byte[] byteArray = null;
            while(true) {
                byteArray = new byte[1024];
                noOfBytesRead = inputStream.read(byteArray);
                if(noOfBytesRead==-1) break;
                outputStream.write(byteArray, 0, noOfBytesRead);
            }
        }catch (FileNotFoundException ex) {
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
      
  
    
  public String getAttachmentLocationByReviewId(int reviewId)  throws ServiceLocatorException{
       
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
           String fileLocation = null;
           try {
               
          queryString = "SELECT AttachmentLocation FROM tblEmpReview WHERE Id = "+reviewId;
          preparedStatement = connection.prepareStatement(queryString);
          resultSet=preparedStatement.executeQuery();
               if(resultSet.next())
               {
              fileLocation = resultSet.getString("AttachmentLocation");
               }
               
               
           // }//Closing Cache Checking
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                   if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
     
        System.out.println("I am Out of Data Source Provider");
        return fileLocation; // returning the object.
    }
    
    
    
}
