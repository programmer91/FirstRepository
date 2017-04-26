/*
 * DownloadPayRoll.java
 *
 * Created on October 1, 2012, 10:39 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.payroll;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.mss.mirage.util.Properties;

/**
 *
 * @author miracle
 */
public class DownloadPayRoll {
    
 private InputStream fileInputStream;
 private String file;
 private String path;
 
 private String locationAvailable;
 private String attachmentLocation;
 
 
 public InputStream getFileInputStream() {
		return fileInputStream;
 }
 
       public String execute() throws Exception {
	   // fileInputStream = new FileInputStream(new File(path,file));
           this.setAttachmentLocation(locationAvailable);
           
           
           file = this.getAttachmentLocation().substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getAttachmentLocation().length());
           
           path = this.getAttachmentLocation().substring(0,this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1);
             
           
           fileInputStream = new FileInputStream(new File(path,file));
           
	    //return null;
            return "success";
	}
 
    
       
    public String getLocationAvailable() {
        return locationAvailable;
    }
    
    public void setLocationAvailable(String locationAvailable) {
        this.locationAvailable = locationAvailable;
    }
    
     public String getAttachmentLocation() {
        return attachmentLocation;
    }
    
    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }
    
    
     public String getFile() {
        return file;
    }
    
    public void setFile(String file) {
        this.file = file;
    }
    
}
