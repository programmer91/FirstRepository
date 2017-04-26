/*
 * MailAction.java
 *
 * Created on November 19, 2007, 10:34 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.services.mail;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ServiceLocatorException;
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

public class MailAction extends ActionSupport implements ServletRequestAware{
    
    
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
    
    private HttpServletRequest httpServletRequest;
    
    private int mailsent;
    
     private static final String SMTP_AUTH_USER = com.mss.mirage.util.Properties.getProperty("Mail.Auth").toString();
     private static final String SMTP_AUTH_PWD  = com.mss.mirage.util.Properties.getProperty("Mail.Auth.pwd").toString();
      private static final String SMTP_PORT  = com.mss.mirage.util.Properties.getProperty("Mail.Port").toString();
    /** Creates a new instance of MailAction */
    public MailAction() {
        
    }//end of the constructor
    
    public String execute() throws Exception{
        
        return SUCCESS;
        
    }//end of the execute method
    
    /* Starting of Send Method
     * @throws Exception
     * @return String variable
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
        String host = com.mss.mirage.util.Properties.getProperty("Mail.Host").toString();
        // Create properties for the Session
        Properties props = new Properties();
        
        // If using static Transport.send(),
        // need to specify the mail server here
        props.put("mail.host", host);
         props.put("mail.smtp.starttls.enable", "true");
        // To see what is going on behind the scene
       // props.put("mail.debug", "true");
       /** authentication **/ 
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.port", SMTP_PORT);
         
        Authenticator auth = new SMTPAuthenticator();
        // Get a session
        Session session = Session.getInstance(props,auth);
        
        try {
            // Get a Transport object to send e-mail
            Transport bus = session.getTransport("smtp");
            
            // Connect only once here
            // Transport.send() disconnects after each send
            // Usually, no username and password is required for SMTP
            bus.connect();
            
            //bus.connect("smtpserver.yourisp.net", "username", "password");
//            if(!attachment.equals("")){
//                
//                MimeMessage mimemessage = new MimeMessage(session);
//                // set FROM
//                mimemessage.setFrom(new InternetAddress(fromAddress));
//                // set DATE
//                mimemessage.setSentDate(new java.util.Date());
//                // set SUBJECT
//                mimemessage.setSubject(sub);
//                
//                // set TO address
//                try {
//                    mimemessage.setRecipients(Message.RecipientType.TO, toAddress);
//                } catch(Exception exception1) {
//                    System.out.println("\tError in setting recipients ......\t" + exception1.getMessage());
//                }
//                
//                // set message BODY
//                MimeBodyPart mimebodypart = new MimeBodyPart();
//                mimebodypart.setText(msge);
//                
//                // attach message BODY
//                MimeMultipart mimemultipart = new MimeMultipart();
//                mimemultipart.addBodyPart(mimebodypart);
//                
//                
//                mimebodypart = new MimeBodyPart();
//                try {
//                    FileDataSource filedatasource = new FileDataSource(attachment);
//                    mimebodypart.setDataHandler(new DataHandler(filedatasource));
//                    
//                } catch(Exception exception3) {
//                    System.out.println("\tError in sending file not been able to attach ......\t" + exception3.getMessage());
//                }
//                // set FILENAME
//                mimebodypart.setFileName(attachment);
//                mimemultipart.addBodyPart(mimebodypart);
//                mimemessage.setContent(mimemultipart);
//                
//                //set CC MAIL and SEND the mail
//                if(!toAddress.equals("")) {
//                    // set CC MAIL
//                    if(!ccAddress.equals("")||!bccAddress.equals(""))
//                        mimemessage.setRecipients(Message.RecipientType.CC, ccAddress);
//                    mimemessage.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(bccAddress, false));
//                    try {
//                        // send MAIL
//                        bus.send(mimemessage);
//                        
//                        httpServletRequest.setAttribute("mailMessage","<font color=\"red\" size=\"1.5\">Your Message Sent Successfully..........!</font>");
//                        
//                    }//end of the try block
//                    catch(Exception exception4) {
//                        System.out.println("\tError in sending Address Try........." + exception4.getMessage());
//                    }//end of the catch block
//                }//end of the outer if
//                else {
//                    
//                    httpServletRequest.setAttribute("mailMessage","<font color=\"red\" size=\"1.5\">Your Message Mail operation Failed..........!</font>");
//                    
//                }//end of the else
//            }//end of the main if
//            
//            else{
                
                MimeMessage mimemessage1 = new MimeMessage(session);
                // set FROM
                mimemessage1.setFrom(new InternetAddress(fromAddress));
                // set DATE
                mimemessage1.setSentDate(new java.util.Date());
                // set SUBJECT
                mimemessage1.setSubject(sub);
                
                // set TO address
                try {
                    mimemessage1.setRecipients(Message.RecipientType.TO, toAddress);
                    
                } catch(Exception ex) {
                    throw new ServiceLocatorException(ex);
                }
                
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
                    if(!ccAddress.equals("")||!bccAddress.equals(""))
                        mimemessage1.setRecipients(Message.RecipientType.CC, ccAddress);
                    mimemessage1.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(bccAddress, false));
                    try {
                        // send MAIL
                        bus.send(mimemessage1);
                        
                        httpServletRequest.setAttribute("mailMessage","<font color=\"green\" size=\"1.5\">Your Message Sent Successfully..........!</font>");
                        setMailsent(1);
                    }//end of the try block
                    catch(Exception exception4) {
                        System.out.println("\tError in sending Address Try........." + exception4.getMessage());
                    }//end of the catch block
                }//end of the if
                else {
                    httpServletRequest.setAttribute("mailMessage","<font color=\"red\" size=\"1.5\">Your Message Mail operation Failed..........!</font>");
                }//end of the else block
                     bus.close();
            }//end of the main else block
//        }//end of the main try block
        catch(Exception e){
            throw new ServiceLocatorException(e);
        }//end of the main catch block
        
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

    public int getMailsent() {
        return mailsent;
    }

    public void setMailsent(int mailsent) {
        this.mailsent = mailsent;
    }
    
        private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           String username = SMTP_AUTH_USER;
           String password = SMTP_AUTH_PWD;
           return new PasswordAuthentication(username, password);
        }
    }
}

