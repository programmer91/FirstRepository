/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  October 11, 2007, 5:01 PM
 *
 * Author  : Arjun Sanapathi <asanapathi@miraclesoft.com>
 *
 * File    : DownloadAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.attachments;

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
public class DownloadAction implements Action,ServletRequestAware,ServletResponseAware{
    
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
            this.setId(Integer.parseInt(httpServletRequest.getParameter("Id").toString()));
            this.setAttachmentLocation(ServiceLocator.getAttachmentService().getAttachmentLocation(this.getId()));
            //fileName = this.getAttachmentLocation()
          //  .substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getAttachmentLocation().length());
            httpServletResponse.setContentType("application/force-download");
            
            File file = new File(getAttachmentLocation());
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
    
}
