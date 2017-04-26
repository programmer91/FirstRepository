/*
 * DownloadAction.java
 *
 * Created on December 5, 2007, 11:11 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.ajax;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author Arjun Sanapathi <asanapathi@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class PayRollDownloadAction implements
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
    public PayRollDownloadAction() {
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
            //String attLocation = httpServletRequest.getParameter("AttachmentLocation");
           // this.setLocationAvailable(httpServletRequest.getParameter("availableLocation"));
            
            this.setAttachmentLocation(locationAvailable);
            
            //fileName = this.getAttachmentLocation().substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getAttachmentLocation().length());
            fileName = this.getAttachmentLocation().substring(this.getAttachmentLocation().lastIndexOf(locationAvailable)+1,getAttachmentLocation().length());
            
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
            responseString = "downLoaded!!";
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
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
