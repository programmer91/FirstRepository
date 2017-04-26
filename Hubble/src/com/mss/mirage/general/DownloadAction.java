/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.general;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import com.opensymphony.xwork2.ActionSupport;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */

import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

 
public class DownloadAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
    
     /*this is abstract method*/
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }    
        private HttpServletRequest httpServletRequest;
        private HttpServletResponse httpServletResponse;
	private InputStream fileInputStream;
        private String fileName;
        public InputStream inputStream;
        public OutputStream outputStream;
        private String resultType;
 
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
 
	public String execute() throws Exception {
            
             try {
            String fileLocation = "";            
            //request.getParameter("eflag").equalsIgnoreCase("e")
            fileLocation= httpServletRequest.getParameter("path").toString();
            httpServletResponse.setContentType("application/force-download");
            // File file = new File(Properties.getProperty("mscvp.docCreationPath")+"SearchedExcelDocument.xls");
            // System.out.println("fileLocation-->"+fileLocation);
            File file = new File(fileLocation);
            Date date = new Date();

            //fileName = (date.getYear() + 1900) + "_" + (date.getMonth() + 1) + "_" + date.getDate() + "_" + file.getName();
           // fileName = getStartdate().substring(0,10) +"To"+ getEnddate().substring(0,10) +"_"+getLeaveType()+"_"+file.getName();
             fileName = file.getName();
            if (file.exists()) {
                resultType = SUCCESS;
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
                                 
              //  httpServletResponse.setContentType(getDownloadType());
               // httpServletResponse.getWriter().write(responseString);
               
            } else {
              //  setResultMessage("File not fount");
            httpServletRequest.getSession(false).setAttribute("errorMessage", "File not foud in specified location!");
            System.out.println(httpServletRequest.getSession(false).getAttribute("errorMessage"));
               
                resultType = ERROR;                             
                //throw new FileNotFoundException("File not found");
            }
        } catch (IOException ex) {
             httpServletRequest.getSession(false).setAttribute("errorMessage", "File not foud in specified location!");             
                resultType = ERROR;
            ex.printStackTrace();            
        }/*catch (ServiceLocatorException ex) {
        ex.printStackTrace();
        }*/ finally {
            try {
                 if(inputStream!=null){
                 inputStream.close();
                 outputStream.close();
                 }
                } catch (IOException ex) {
                     ex.printStackTrace();                
                }
                    }
          // String path= httpServletRequest.getParameter("path").toString();  
	  //  fileInputStream = new FileInputStream(new File(path));
	    return resultType;
	}

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

   
}