 /*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.projects
 *
 * Date    : January 01, 2008, 12:35 PM
 *
 * Author  : Arjun Sanapathi<asanapathi@miraclesoft.com>
 *           Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : DownloadAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.projects;

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
 * This Class.... we use for downloading Files
 *
 *
 */
public class DownloadAction implements Action,ServletRequestAware,ServletResponseAware{
    
    
    private String inputPath;
    
    private String contentDisposition="FileName=inline";
    
    /** Creating a reference variable for InputStream. */
    public InputStream inputStream;
    
    /** Creating a reference variable for OutputStream. */
    public OutputStream outputStream;
    
    /** Creating a reference variable for HttpServletRequest. */
    private HttpServletRequest httpServletRequest;
    
    /** Creating a reference variable for HttpServletResponse. */
    private HttpServletResponse httpServletResponse;
    
    /* Creating id to get the attachment download from the database  */
    private int id;
    
    /* Creating Path to get the attachment location path from the database  */
    private String Path;    
    
    /* Creating fileName to Store the path*/
    private String fileName;

    //private String attachmentLocation;
    
    /** Creates a new instance of DownloadAction */
    public DownloadAction() {
    }
    
    public String execute() throws Exception {
        return null;
    }
    
     /**
     * The method setServletRequest is used to set the HttpServletRequest
     */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    /**
     * The method setServletResponse is used to set the HttpServletResponse
     */
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        
        try {
            this.setId(Integer.parseInt(httpServletRequest.getParameter("Id").toString()));
            this.setPath(ServiceLocator.getProjectService().getPath(this.getId()));
            //this.setAttachmentLocation(ServiceLocator.getAttachmentService().getAttachmentLocation(this.getId()));
           // fileName = this.getPath()
           // .substring(this.getPath().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getPath().length());
            httpServletResponse.setContentType("application/force-download");
            
            File file = new File(getPath());
            fileName = file.getName();
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
    
    /**
     * The method getId is used to get id from related table in the database
     */
    public int getId() {
        return id;
    }
    
    /**
     * The method setId is used to set id from related table in the database
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /*
    public String getAttachmentLocation() {
        return attachmentLocation;
    }
    
    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }*/
    
    /**
     * The method getPath is used to get path from related table in the database
     */
    public String getPath() {
        return Path;
    }
    
     /**
     * The method setPath is used to set path from related table 
     */
    public void setPath(String Path) {
        this.Path = Path;
    }
    
}
