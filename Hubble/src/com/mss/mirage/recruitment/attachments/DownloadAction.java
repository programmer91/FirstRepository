/*
 * DownloadAction.java
 *
 * Created on December 5, 2007, 11:11 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.recruitment.attachments;

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
public class DownloadAction implements
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
       //for knowing where the control is from
    
    private String consultantId;
    /** Creates a new instance of DownloadAction */
    public DownloadAction() {
    }
    
    public String execute() throws Exception {
        return null;
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        
        try {
            this.setLocationAvailable(httpServletRequest.getParameter("availableLocation"));
            
            if(this.getLocationAvailable() !=null){
                this.setAttachmentLocation(httpServletRequest.getParameter("resumeLocation"));
            }else {
                this.setId(Integer.parseInt(httpServletRequest.getParameter("id").toString()));
                
                if(getConsultantId()!=null)
                {
                    //System.out.println("in Download");
                    this.setAttachmentLocation(ServiceLocator.getConsultantAttachmentService().getConsultantAttachmentLocation(this.getId()));
                    //System.out.println("in Download--->"+getAttachmentLocation());
                }else{
                    this.setAttachmentLocation(ServiceLocator.getConsultantAttachmentService().getAttachmentLocation(this.getId()));
                    // System.out.println("in else Download--->"+getAttachmentLocation());
                }
               // this.setAttachmentLocation(ServiceLocator.getConsultantAttachmentService().getAttachmentLocation(this.getId()));
            }
           // fileName = this.getAttachmentLocation().substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getAttachmentLocation().length());
            //fileName = this.getAttachmentLocation().substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download1"))+1,getAttachmentLocation().length());
            
            httpServletResponse.setContentType("application/force-download");
            
            File file = new File(getAttachmentLocation());
            fileName = file.getName();
            inputStream = new FileInputStream(file);
            outputStream =  httpServletResponse.getOutputStream();
            httpServletResponse.setHeader("Content-Disposition","attachment;filename=\"" + fileName +"\"");
            int noOfBytesRead = 0;
            byte[] byteArray = null;
            while(true) {
                byteArray = new byte[1024];
                noOfBytesRead = inputStream.read(byteArray);
                if(noOfBytesRead == -1)
                    break;
                else
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
                
                if(inputStream != null){
                    inputStream.close();
                    inputStream = null;
                }
                if(outputStream != null){
                     outputStream.close();
                    outputStream = null;
                }
               
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

    /**
     * @return the consultantId
     */
    public String getConsultantId() {
        return consultantId;
    }

    /**
     * @param consultantId the consultantId to set
     */
    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }
    
}
