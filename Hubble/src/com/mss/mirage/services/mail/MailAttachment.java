/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   January 21, 2008, 3:05 PM
 *
 * Author  :  Arjun Sanapathi <asanapathi@miraclesoft.com>
 *
 * File    : MailAttachment .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.services.mail;

import com.mss.mirage.util.ApplicationConstants;

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

// New imports for authentication

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


/**
 * The <code>MailManager</code>  class is used for sending mail  from MailWindow.jsp
 * <i>MailWindow.jsp</i> page.
 *
 * @author Arjun Sanapathi<a href="mailto:asanapathi@miraclesoft.com">asanapathi@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 *
 */

public class MailAttachment {
    
    
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
    
    
    
    /** Creates a new instance of MailAction */
    public MailAttachment() {
        
    }//end of the constructor
    
       private static final String SMTP_AUTH_USER = com.mss.mirage.util.Properties.getProperty("Mail.Auth").toString();
     private static final String SMTP_AUTH_PWD  = com.mss.mirage.util.Properties.getProperty("Mail.Auth.pwd").toString();
     private static final String SMTP_PORT  = com.mss.mirage.util.Properties.getProperty("Mail.Port").toString();
        
    /* Starting of Send Method
     * @throws Exception
     * @return String variable
     */
     
     
  /* public static void main(String[] args) throws Exception{
      // new SimpleMail().test();
        
        System.out.println("in main");
        MailAttachment mt = new MailAttachment();
        mt.sendMail("nseerapu","Nagireddy");
        //sendLeaveEmail("nseerapu@miraclesoft.com","nseerapu@miraclesoft.com","Nag","nseerapu","testmail","approve","11/02/2011 19:24:11","11/02/2011 19:24:11","test Leave");
        System.out.println("last main");
    }*/
     
     
    public void sendMail(String userId,String userName) throws Exception {
        
        //System.out.println("send leave mail");
        //String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        //String userName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
        /** @param toAddress is used for storing the to address. */
          String toAddress = to;
       // String toAddress= "nseerapu@miraclesoft.com";
        
        /** @param fromAddress is used for storing the from address. */
        String fromAddress = "\""+userName+"\"<"+userId+"@miraclesoft.com>";
        
        /** @param  ccAddress is used for storing the cc address. */
        String ccAddress= cc;
        //String ccAddress= "nseerapu@miraclesoft.com";
        
        /** @param  bccAddress is used for storing the bcc address. */
          String bccAddress=bcc;
        //  String bccAddress="vkandregula@miraclesoft.com";
        
        /** @param  sub is used for storing the subject of mail. */
        String sub=subject;
            //String sub="test mail for leave";
        
        /** @param  msge is used for storing the body message of mail. */
        String msge=message;
        //String msge="hi this is nag please ignore this mail"; 
        /** @param  attachment is used for storing the attachments. */
        String attachment=upload;
        //String attachment = "E:\\nag\\ljampana.vcs";
        //String attachment="d:\\VCALENDAR.ics";
        /** @param  strResult is used for storing the message that is display in jsp page. */
        String strResult;
        
        
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        String host = com.mss.mirage.util.Properties.getProperty("Mail.Host").toString();
        //String host = "192.168.5.5";
        
        // Create properties for the Session
        Properties props = new Properties();
        
        // If using static Transport.send(),
        // need to specify the mail server here
        props.put("mail.smtp.host", host);
         props.put("mail.smtp.starttls.enable", "true");
        // To see what is going on behind the scene
        props.put("mail.debug", "true");
        /** authentication **/ 
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
         
        Authenticator auth = new SMTPAuthenticator();
        // Get a session
        Session session = Session.getInstance(props,auth);
       // Session session = Session.getInstance(props);
        
        try {
            // Get a Transport object to send e-mail
            Transport bus = session.getTransport("smtp");
            
            // Connect only once here
            // Transport.send() disconnects after each send
            // Usually, no username and password is required for SMTP
            bus.connect();
            
            //bus.connect("smtpserver.yourisp.net", "username", "password");
        /*    if(!attachment.equals("")){
                
            //   System.out.println("in ifff");
                MimeMessage mimemessage = new MimeMessage(session);
                // set FROM
                mimemessage.setFrom(new InternetAddress(fromAddress));
                // set DATE
                mimemessage.setSentDate(new java.util.Date());
                // set SUBJECT
                mimemessage.setSubject(sub);
//                mimemessage.setFileName(attachment);
                // set TO address
                
                mimemessage.setRecipients(Message.RecipientType.TO, toAddress);
                
                // set message BODY
                MimeBodyPart mimebodypart = new MimeBodyPart();
                mimebodypart.setText(msge);
                
                // attach message BODY
                MimeMultipart mimemultipart = new MimeMultipart();
                mimemultipart.addBodyPart(mimebodypart);
                
                mimebodypart = new MimeBodyPart();
                
                FileDataSource filedatasource = new FileDataSource(attachment);
                mimebodypart.setDataHandler(new DataHandler(filedatasource));
                
                // set FILENAME
                mimebodypart.setFileName(attachment);
                mimemultipart.addBodyPart(mimebodypart);
                mimemessage.setContent(mimemultipart);
                
                //set CC MAIL and SEND the mail
                if(!toAddress.equals("")) {
                    // send MAIL
                    bus.send(mimemessage);
                }//end of the try block
                
            }//end of the main if
            
            else{*/
                
               // System.out.println("in else");
                
                MimeMessage mimemessage1 = new MimeMessage(session);
                // set FROM
                mimemessage1.setFrom(new InternetAddress(fromAddress));
                // set DATE
                mimemessage1.setSentDate(new java.util.Date());
                // set SUBJECT
                mimemessage1.setSubject(sub);
                
                // set TO address
                mimemessage1.setRecipients(Message.RecipientType.TO, toAddress);
                
                // set message BODY
                MimeBodyPart mimebodypart = new MimeBodyPart();
                mimebodypart.setText(msge);
                
                // attach message BODY
                MimeMultipart mimemultipart = new MimeMultipart();
                
                mimemultipart.addBodyPart(mimebodypart);
                
                mimebodypart = new MimeBodyPart();
                
                mimemessage1.setContent(mimemultipart);
                
                //set CC MAIL and SEND the mail
                if(!toAddress.equals("")) {
                    
                    // set CC MAIL
                    
                    // send MAIL
                    bus.send(mimemessage1);
                    
                }//end of the if
                
           // }//end of the main else block
            bus.close();
        }//end of the main try block
        catch(Exception e){
            
           // System.out.println("exception --->"+e);
            throw new Exception(e);
        }//end of the main catch block
        
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
    
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           String username = SMTP_AUTH_USER;
           String password = SMTP_AUTH_PWD;
           return new PasswordAuthentication(username, password);
        }
    }
}
