/*
 * DownloadAction.java
 *
 * Created on December 5, 2007, 3:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.reports;


import com.mss.mirage.util.ReportProperties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.Action;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
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
    
    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;
    private BufferedOutputStream bufferedOutputStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private int id;
    private String reportName;
    private String fileName;
    private String destinationDirectory;
    
    /** Creates a new instance of DownloadAction */
    public DownloadAction() {
    }
    
    public String execute() throws Exception {
        return null;
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public void setServletResponse(HttpServletResponse httpServletResponse){
        
        try {
            
            this.setId(Integer.parseInt(httpServletRequest.getParameter("id").toString()));
            
            this.setReportName(ServiceLocator.getReportsService().getReportName(this.getId()));
            
            destinationDirectory = ReportProperties.getProperty("EmpAvailabilityDestPDFDirectory");
            
            
            
            File file = new File(destinationDirectory+getReportName());
            
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            byteArrayOutputStream = new ByteArrayOutputStream();
            //outputStream =  httpServletResponse.getOutputStream();
            
            int start = 0;
            int length = 1024;
            int offset = -1;
            byte[] buffer = new byte[length];
            while((offset = bufferedInputStream.read(buffer,start,length)) != -1) {
                byteArrayOutputStream.write(buffer, start, offset);
            }
            bufferedInputStream.close();
            byteArrayOutputStream.flush();
            buffer = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            
            //Adding in to the response
            httpServletResponse.setHeader("Content-Disposition","attachment;filename=\"" + getReportName() +"\"");
            httpServletResponse.addHeader("Content-Transfer-Encoding", "binary");
            httpServletResponse.setContentType("application/octet-stream");
            //httpServletResponse.setContentType("application/force-download");
            httpServletResponse.setContentLength((int)file.length());
            httpServletResponse.getOutputStream().write(buffer);
            httpServletResponse.getOutputStream().flush();
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getReportName() {
        return reportName;
    }
    
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    
}
