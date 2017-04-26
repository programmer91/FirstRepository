/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   January 28, 2008, 5:22 PM
 *
 * Author  :  Rajasekhar Yenduva <ryenduva@miraclesoft.com>
 *
 * File    : ContactMailAction .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.services.mail;

import com.mss.mirage.util.ApplicationConstants;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Multipart;
import javax.mail.BodyPart;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import java.sql.Types;


// New imports for authentication

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * The <code>MailManager</code>  class is used for sending mail  from MailWindow.jsp
 * <i>MailWindow.jsp</i> page.
 *
 * @author RajaReddy.Andra <a href="mailto:randra@miraclesoft.com">randra@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 *
 */

public class ContactMailAction extends ActionSupport implements ServletRequestAware{
    
    
    /** @param  to is used for get to address from jsp page. */
    private String to;
    
    /** @param  cc is used for get cc address from jsp page. */
    private String cc;
    
    /** @param  bcc is used for get bcc address from jsp page. */
    private String bcc;
    
    /** @param  subject is used for get subject from jsp page. */
    private String subject;
    
    /** @param message is used for get body of mail from jsp page. */
    private String message;
    
    /** @param upload is used for get attachments from jsp page. */
    private String upload;
    
    private int accountId;
    private int contactId;
    
    private static final String SMTP_AUTH_USER = com.mss.mirage.util.Properties.getProperty("Mail.Auth").toString();
    private static final String SMTP_AUTH_PWD  = com.mss.mirage.util.Properties.getProperty("Mail.Auth.pwd").toString();
    
    
    private HttpServletRequest httpServletRequest;
    
    /** Creates a new instance of MailAction */
    public ContactMailAction() {
        
    }//end of the constructor
    
    public String execute() throws Exception{
        
        return SUCCESS;
        
    }//end of the execute method
    
    
    
    
    
     /**
     * The <code>insertEmailActivity</code>  class is used for insert an activity in mirage.tbCrmActivity Table
     * <i>ContactsMailWindow.jsp</i> page.
     * <p>
      
     * @author Arjun Sanapathi <a href="mailto:asanapathi@miraclesoft.com">Arjun</a>
     *
     * @version 1.0 November 01, 2007
      */
    
    
    
    
    
    public int insertEmailActivity(int accountId,int contactId,String sub,String emailBody) throws ServiceLocatorException {
        int insertedRows=0;
        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement= null;
         DateUtility date = null;
        String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
       
         try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spActivity(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1,accountId);
            callableStatement.setInt(2,contactId);
            callableStatement.setInt(3,0);
            callableStatement.setString(4,"Email-OutBound");
            callableStatement.setString(5,"Closed");
            callableStatement.setString(6,"Low");
            callableStatement.setInt(7,0);
            try {
                date = DateUtility.getInstance();
                
            } catch (ServiceLocatorException ex) {
                ex.printStackTrace();
            }
            callableStatement.setTimestamp(8,date.getCurrentMySqlDateTime());
            callableStatement.setString(9,userId);
            callableStatement.setString(10,userId);
            callableStatement.setString(11,sub);
            callableStatement.setString(12,emailBody);
            callableStatement.setTimestamp(13,date.getCurrentMySqlDateTime());
            callableStatement.setString(14,userId);
            callableStatement.setTimestamp(15,date.getCurrentMySqlDateTime()); 
            callableStatement.setString(16,userId);
            callableStatement.setInt(17,0);
            insertedRows = callableStatement.executeUpdate();
            
            
            
        } catch(Exception e) 
            {
             System.out.println("OK Exception");   
                
            }finally{
             try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(callableStatement != null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
             
         }
           
        return insertedRows;
    }
        
        
        
        
    /* Starting of Send Method
     * @throws Exception
     * @return String variable
     */
    
    
    /**
     * The <code>insertEmailActivity</code>  class is used for insert an activity in mirage.tbCrmActivity Table
     * <i>ContactsMailWindow.jsp</i> page.
     * <p>
      
     * @author Arjun Sanapathi <a href="mailto:asanapathi@miraclesoft.com">Arjun</a>
     *
     * @version 1.0 November 01, 2007
      */
    
    
    
        public String sendMail() throws Exception {
            
            
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            String userName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            /** @param toAddress is used for storing the to address. */
            String toAddress = to;
            
            /** @param fromAddress is used for storing the from address. */
            String fromAddress = "\""+userName+"\"<"+userId+"@miraclesoft.com>";
            
            /** @param  ccAddress is used for storing the cc address. */
            String ccAddress= cc;
            
            /** @param  bccAddress is used for storing the bcc address. */
            String bccAddress=bcc;
            
            /** @param  sub is used for storing the subject of mail. */
            String sub=subject;
            
            /** @param  msge is used for storing the body message of mail. */
            String msge=message;
            
            /** @param  attachment is used for storing the attachments. */
            String attachment=upload;
            
            /** @param  strResult is used for storing the message that is display in jsp page. */
            String strResult;
            
            // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
          //  String host = "192.168.5.5";
            //String host = "mail.miraclesoft.com";
            String host = com.mss.mirage.util.Properties.getProperty("Mail.Host").toString();
            
            // Create properties for the Session
            Properties props = new Properties();
            
            
           if(getAccountId()!=0)
                     {
                        
            int result = insertEmailActivity (getAccountId(),getContactId(),sub,msge);
            httpServletRequest.setAttribute("mailMessage","<font color=\"red\" size=\"1.5\">Mail has been sent...!</font>");  
           
           } 
                    
                     
                   
            
            return SUCCESS;
        }//end of the send method
        
        /** ******* from here bean section ****************/
        
    /* getSubject() used to get subject of the mail
     * @return String variable
     */
        public String getSubject() {
            return subject;
        }
        
        /* setSubject(String subject) used to set subject of the mail */
        public void setSubject(String subject) {
            this.subject = subject;
        }
        
    /* getMessage() used to get message of the mail
     * @return String variable
     */
        public String getMessage() {
            return message;
        }
        
        /*setMessage(String message) used to set message*/
        public void setMessage(String message) {
            this.message = message;
        }
        
    /* getUpload() used to get upload
     * @return String variable
     */
        public String getUpload() {
            return upload;
        }
        
        /* setUpload(String upload) used to set upload*/
        public void setUpload(String upload) {
            this.upload = upload;
        }
        
    /* getTo() used to get to address
     * @return String
     */
        public String getTo() {
            return to;
        }
        
        /* setTo(String to)used to set to address*/
        public void setTo(String to) {
            this.to = to;
        }
        
        public void setServletRequest(HttpServletRequest httpServletRequest) {
            this.httpServletRequest = httpServletRequest;
        }
        
    /* String getCc() used to get cc address
     * @param return String
     */
        public String getCc() {
            return cc;
        }
        
        /*setCc(String cc)used to set cc*/
        public void setCc(String cc) {
            this.cc = cc;
        }
        
    /* getBcc() used to get bcc address
     * @return String variable
     */
        public String getBcc() {
            return bcc;
        }
        
    /*
     *setBcc(String bcc) used to set bcc
     */
        public void setBcc(String bcc) {
            this.bcc = bcc;
        }
        
        public int getAccountId() {
            return accountId;
        }
        
        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }
        
        public int getContactId() {
            return contactId;
        }
        
        public void setContactId(int contactId) {
            this.contactId = contactId;
        }
        
     private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           String username = SMTP_AUTH_USER;
           String password = SMTP_AUTH_PWD;
           return new PasswordAuthentication(username, password);
        }
    }
        
    }
