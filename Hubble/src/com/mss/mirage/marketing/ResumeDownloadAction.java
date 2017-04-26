/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.marketing;

import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

/**
 *
 * @author miracle
 */
public class ResumeDownloadAction  implements
        Action, ServletRequestAware, ServletResponseAware{
        private String contentDisposition = "FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String fileName;
    private String downloadType;
    private String sheetType;
    private String path;
     private int jobConsultantId;
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
       
        String responseString = "";
        try {

            String fileLocation = "";
            //For creating Excel grind from Search result Grid
           // System.out.println("StartDate" + getStartdate());
          //  System.out.println("EndDate" + getEnddate());
          //  fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus());

            httpServletResponse.setContentType("application/force-download");
            // File file = new File(Properties.getProperty("mscvp.docCreationPath")+"SearchedExcelDocument.xls");
         //   System.out.println("fileLocation-->"+fileLocation);
         //   File file = new File(getPath());
            File file = new File(getFilePathById(getJobConsultantId()));
       //     Date date = new Date();

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
                Logger.getLogger(ResumeDownloadAction.class.getName()).log(Level.SEVERE, null, ex1);
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

   
    public String getFilePathById(int jobConsultantId)   {
      String responseString = "";
        String filePath = "";
        try {
             responseString = ServiceLocator.getAjaxHandlerWebService().getApplicantDetails(jobConsultantId);
            JSONObject jObject  = new JSONObject(responseString);
             String ResumePath = (String)jObject.get("ResumePath"); 
            filePath = ResumePath;
            
            
        } catch(Exception exception){
            exception.printStackTrace();
        }
           
        
        
        //System.out.println("filePath--->"+filePath);
        return filePath; // returning the object.
    }
  
  
  
    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the jobConsultantId
     */
    public int getJobConsultantId() {
        return jobConsultantId;
    }

    /**
     * @param jobConsultantId the jobConsultantId to set
     */
    public void setJobConsultantId(int jobConsultantId) {
        this.jobConsultantId = jobConsultantId;
    }
      
    
    
    
    
}
