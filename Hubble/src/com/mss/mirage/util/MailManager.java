/*
 * SendMail.java
 *
 * Created on September 7, 2007, 1:12 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.util;
import com.mss.mirage.crm.greensheets.GreenSheetAction;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
//import java.io.UnsupportedEncodingException;
import java.io.*;
//new
import java.util.Set;
import java.util.HashSet;
import com.mss.mirage.util.DataSourceDataProvider;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
// New imports for authentication
import javax.activation.MailcapCommandMap;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 * The <code>MailManager</code>  class is used for getting user password details from
 * <i>ForgotPassword.jsp</i> page.
 *
 * @author RajaReddy.Andra <a href="mailto:randra@miraclesoft.com">randra@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 *
 */


public class MailManager {
    public MailManager(){}
    
    
   // private static final String SMTP_AUTH_USER = "hubbleapp@miraclesoft.com";
   // private static final String SMTP_AUTH_PWD  = "R0leMod3l";
   // private static final String SMTP_HOST  = "mail.miraclesoft.com";
   
    private static final String SMTP_AUTH_USER = com.mss.mirage.util.Properties.getProperty("Mail.Auth").toString();
    private static final String SMTP_AUTH_PWD  = com.mss.mirage.util.Properties.getProperty("Mail.Auth.pwd").toString();
    private static final String SMTP_HOST  = com.mss.mirage.util.Properties.getProperty("Mail.Host").toString();
    private static final String SMTP_PORT  = com.mss.mirage.util.Properties.getProperty("Mail.Port").toString();
    // private static final String SMTP_HOST  = "192.168.5.5";
    
    /** Starting of Send Method
     * @param loginId it is mailId of user
     * @param userName it is Name of the user
     * @param password it is password of the MirageV2 account
     * @throws  NoSuchProviderException
     * @throws  MessagingException
     *  UPDATED
     */
    
    
    public static void sendUserIdPwd(String loginId,String userName,String password) {
        
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        String to = loginId+"@miraclesoft.com";
        
        /** The from is used for storing the from address. */
        String from = "hubbleapp@miraclesoft.com";
        
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        Authenticator auth = new SMTPAuthenticator();
        // Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
       // mailSession.setDebug(true);
         mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Password Details");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //message.addRecipient(Message.RecipientType.CC,new InternetAddress("mchennu@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><table align='center' width='640px'");
            htmlText.append(" background='cid:image' border='0' cellpadding=''");
            htmlText.append(" cellspacing='' height='815px'><tr>");
            htmlText.append("<td width='600px' height='110px'></td></tr>");
            htmlText.append("<tr><td align='top' colspan='2' height='30px'>");
            htmlText.append("<font color='#151B54' size='4px'>Dear  </font>");
            htmlText.append("<font color='red' size='4px'>"+userName+",</font>");
            htmlText.append("</td></tr><tr><td height='30px' colspan='2'>");
            htmlText.append("<font color='#151B54' size='4px'>Greetings from Miracle Software Systems,Inc.</font>");
            htmlText.append("</td></tr><tr><td height='90px' colspan='2'>");
            htmlText.append("<font color='#3090C7' size='3px'>Miracle Software ");
            htmlText.append("Systems, Inc. is a leading global consulting and ");
            htmlText.append("offshore outsourcing company,<br>offering a wide ");
            htmlText.append("array of solutions customized for a range of key ");
            htmlText.append("verticals and horizontals.<br>Miracle's network ");
            htmlText.append("spans across 5 continents. A large number of ");
            htmlText.append("dedicated and highly skilled IT professionals,");
            htmlText.append("work in locations in the USA , UK, Canada, India, ");
            htmlText.append("Singapore and Australia .</font></td></tr><tr>");
            htmlText.append("<td width='20%' height='20px'><font color='#151B54'");
            htmlText.append(" size='4px'>LoginId :</font></td><td width='550px' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+loginId);
            htmlText.append("</font></td></tr><tr><td width='20%' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'>Password :</font>");
            htmlText.append("</td><td width='550px' height='20px'>");
            htmlText.append("<font color='red' size='4px'>"+password+"</font>");
            htmlText.append("</td></tr><tr><td colspan='2' height='50px'>");
            htmlText.append("<font color='#3090C7' size='4px'>Please Note:</font>");
            htmlText.append("<font color='#3090C7' size='3px'>To better protect your account,");
            htmlText.append(" make sure that your password is memorable ");
            htmlText.append("for you but difficult for others to guess. Never use the same password that ");
            htmlText.append("you have used in the past, and do not share your password with anyone.</font>");
            htmlText.append("</td></tr><tr><td colspan='2' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'>Regards,</font>");
            htmlText.append("</td></tr><tr><td colspan='2' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'>CorporateApplicationSupport Team,</font>");
            htmlText.append("</td></tr><tr><td colspan='2' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'>Miracle Software Systems, Inc.</font>");
            htmlText.append("</td></tr><tr><td width='600px' height='40px'></td></tr></table></body></html>");
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("MailImage.Path"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID","<image>");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    /**
     * ending of send method
     *
     * UPDATED
     */
    
  /*  public static void sendContactEmail(String loginId,String password,String emailId) {
        
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
       
        String to = emailId;
        
       
        String from = "hubbleapp@miraclesoft.com";
        
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
       
        Properties props = new Properties();
        
      
        props.setProperty("mail.transport.protocol", "smtp");
        
      
        props.setProperty("mail.host", SMTP_HOST);
        
       
        props.put("mail.smtp.auth", "true");
        
        Authenticator auth = new SMTPAuthenticator();
        // Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
      //  mailSession.setDebug(true);
          mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Login  Details");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //message.addRecipient(Message.RecipientType.CC,new InternetAddress("mchennu@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><table align='center' width='600px'");
            htmlText.append(" background='cid:image' border='0' cellpadding='' ");
            htmlText.append("cellspacing='' height='800px'><tr><td width='580px'");
            htmlText.append(" height='110px'></td></tr><tr><td height='30px' colspan='2'>");
            htmlText.append("<font color='#151B54' size='4px'>Dear Customer,</font>");
            htmlText.append("</td></tr><tr><td height='30px' colspan='2'><font ");
            htmlText.append("color='#151B54' size='4px'>Greetings from Miracle ");
            htmlText.append("Software Systems,Inc.</font></td></tr><tr><td height='90px' colspan='2'>");
            htmlText.append("<font color='#3090C7' size='3px'>Miracle Software ");
            htmlText.append("Systems, Inc. is a leading global consulting and ");
            htmlText.append("offshore outsourcing company,<br>offering a wide array");
            htmlText.append(" of solutions customized for a range of key verticals ");
            htmlText.append("and horizontals.<br>Miracle's network spans across ");
            htmlText.append("5 continents. A large number of dedicated and highly");
            htmlText.append(" skilled IT professionals,work in locations in the ");
            htmlText.append("USA , UK, Canada, India, Singapore and Australia .</font>");
            htmlText.append("</td></tr><tr><td width='20%' height='20px'><font ");
            htmlText.append("color='#151B54' size='4px'>LoginId :</font></td>");
            htmlText.append("<td width='550px' height='20px'><font color='red' ");
            htmlText.append("size='4px'>"+loginId+"</font></td></tr><tr><td ");
            htmlText.append("width='20%' height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>Password :</font></td><td width='550px' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+password+"</font>");
            htmlText.append("</td></tr><tr><td width='20%' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'>WebSite :</font>");
            htmlText.append("</td><td width='550px' height='20px'><a ");
            htmlText.append("href='w3.miraclesoft.com/Hubble/customer/login.action'>");
            htmlText.append("w3.miraclesoft.com/Hubble/customer/login.action</a>");
            htmlText.append("</td></tr><tr><td colspan='2' height='50px'>");
            htmlText.append("<font color='#3090C7' size='4px'>Please Note:</font>");
            htmlText.append("<font color='#3090C7' size='3px'>To better protect ");
            htmlText.append("your account, make sure that your password is memorable ");
            htmlText.append("for you but difficult for others to guess. Never ");
            htmlText.append("use the same password that you have used in the past,");
            htmlText.append(" and do not share your password with anyone.</font>");
            htmlText.append("</td></tr><tr><td colspan='2' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'>Regards,</font>");
            htmlText.append("</td></tr><tr><td colspan='2' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'>Corporate ");
            htmlText.append("Application Support Team,</font></td></tr><tr>");
            htmlText.append("<td colspan='2' height='20px'><font color='#151B54'");
            htmlText.append(" size='4px'>Miracle Software Systems, Inc.</font>");
            htmlText.append("</td></tr><tr><td width='600px' height='40px'></td></tr>");
            htmlText.append("</table></body></html>");
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("MailImage.Path"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID","<image>");
            
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
        
    }*/
    public static void sendContactEmail(String loginId,String password,String emailId,String contactName,String accountType) {
       
            
        
       // System.out.println("reportsTo--->"+to);
        
        
        
       // System.out.println("to--->"+to);
        /** The from is used for storing the from address. */
        //String fromAdd = httpServletrequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        String from = "hubbleapp@miraclesoft.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        
        
        // String host = "mail.miraclesoft.com";
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        Authenticator auth = new SMTPAuthenticator();
        //  Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
      //  mailSession.setDebug(true);
          mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("REG : Hubble time tracking system login details");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailId));
          //  message.addRecipient(Message.RecipientType.CC,new InternetAddress("timesheets@miraclesoft.com"));
           // message.addRecipient(Message.RecipientType.CC,new InternetAddress("nseerapu@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
      
                htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>Dear <b>"+contactName+"</b> </p>");
                htmlText.append("<p>Welcome to Hubble Time Tracking System.</p>");
                htmlText.append("<p>Here are your login details.</p><br>");
                htmlText.append("<p>LoginId : <b>"+loginId+"</b></p>");
               htmlText.append("<p>Password : <b>"+password+"</b></p>");
               if(accountType.equalsIgnoreCase("customer")){
                htmlText.append("\n\n<font>Website :<a href='http://www.miraclesoft.com/Hubble/customer/login.action'>");
                 htmlText.append(" www.miraclesoft.com/Hubble/customer/login.action </a> </font><br>");
               }else{
                   htmlText.append("\n\n<font>Website :<a href='http://www.miraclesoft.com/Hubble/vendor/vendorlogin.action'>");
                    htmlText.append(" www.miraclesoft.com/Hubble/vendor/vendorlogin.action </a> </font><br>");
               }
                       
                     //   htmlText.append(" w3.miraclesoft.com/Hubble/customer/login.action </a> </font><br>");
                 htmlText.append("<p><b>Please Note:</b>To better protect your account, make sure that your password is ");
                 htmlText.append("memorable for you but difficult for others to guess.</p><p> Never use the same password ");
                 htmlText.append("that you have used in the past, and do not share your password with anyone. </p>");
                
                htmlText.append("<br><br><p> Regards,</p>");
                htmlText.append("<p>Corporate Application Support Team, </p>");
                htmlText.append("<p>Miracle Software Systems, Inc. </p>");
                
                htmlText.append("<p> For correspondence use <b>hubble@miraclesoft.com</b></p>");
                htmlText.append("<p> You can reach us at <b>1-248-233-1814</b></p>");
                
                htmlText.append("</font>");
                
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
                        
                        
                htmlText.append("</body></html>");
            
            
           
               /* htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>Hello "+empName+"</p>");
                htmlText.append("<p>Your Time Sheet has been "+status+".</p>");
                htmlText.append("<p><u><b>Time Sheet Details:</b></u><br>");
                htmlText.append("Week Start Date: "+startDate+"<br>");
                htmlText.append("Week End Date: "+endDate+"<br>");
                if(status.equals("Disapproved") || status.equals("Approved")) {
                htmlText.append(" <br><br>\n\n\n Please click here <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"'> " +
                        "View The TimeSheet. </a> </font>");
                }else{
                htmlText.append(" <br><br>\n\n\n Please click here <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"'> " +
                        "View The TimeSheet </a> </font>  to Approve this TimeSheet.");
                }
                htmlText.append("<br>Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
                htmlText.append("</body></html>");
            
            */
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
       
           
                transport.sendMessage(message,
                        message.getRecipients(Message.RecipientType.TO));
           
          //  transport.sendMessage(message,message.getRecipients(Message.RecipientType.CC));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    

    
    /**
     * Timesheet reminder
     * PENDING
     */
    /*
    public static void sendReminders(String empName, String timeSheetID,String EmpId,String empType,HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        
      //  System.out.println("empName--->"+empName+"----empId---->"+EmpId);
        
     //   System.out.println("timeSheetID--->"+timeSheetID+"----empId---->"+EmpId+"-------empType------>"+empType);
        String loginId = "";
       // String status = DataSourceDataProvider.getInstance().getTimeSheetStatus(empName,timeSheetID);
        
         String status = DataSourceDataProvider.getInstance().getTimeSheetStatus(EmpId,timeSheetID,empType);
        
        String startDate = DataSourceDataProvider.getInstance().getTimeSheetStartDate(empName,timeSheetID,empType);
        
        String endDate = DataSourceDataProvider.getInstance().getTimeSheetEndDate(empName,timeSheetID,empType);
        String reportsTo="";
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
     
        String to= "";
        
      
        if(status.equals("Disapproved") || status.equals("Approved")){ 
          //  System.out.println("in approve or diapprove---->"+empType);
            
            if(empType.equalsIgnoreCase("e")){
                loginId = DataSourceDataProvider.getInstance().getEmpLoginIdById(EmpId)+"@miraclesoft.com";
                
            }else{
                //Customer email Id.
               loginId =  DataSourceDataProvider.getInstance().getEmailIdByContactId(Integer.parseInt(EmpId));
            }
            to = loginId;
        }else if(status.equals("Submitted")){
            if(empType.equalsIgnoreCase("e")){
                reportsTo = DataSourceDataProvider.getInstance().reportsTo(EmpId)+"@miraclesoft.com";
            }else{
                 String resourceType= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_RESOURCETYPE).toString();
                reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(Integer.parseInt(EmpId),resourceType);
            }
            to = reportsTo;
        }
      //  System.out.println("reportsTo--->"+to);
        
        
        
       // System.out.println("to--->"+to);
     
        //String fromAdd = httpServletrequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        String from = "hubbleapp@miraclesoft.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
     
        
        
        // String host = "mail.miraclesoft.com";
        
       
        Properties props = new Properties();
        
       
        props.setProperty("mail.transport.protocol", "smtp");
        
      
        props.setProperty("mail.host", SMTP_HOST);
        
      
        props.put("mail.smtp.auth", "true");
        
        Authenticator auth = new SMTPAuthenticator();
        //  Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
      //  mailSession.setDebug(true);
          mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("TimeSheet Reminder");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
          //  message.addRecipient(Message.RecipientType.CC,new InternetAddress("timesheets@miraclesoft.com"));
           // message.addRecipient(Message.RecipientType.CC,new InternetAddress("nseerapu@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            if(status.equals("Disapproved")) {
                htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>Hello "+empName+"</p>");
                htmlText.append("<p>Your Time Sheet has been Rejected.</p>");
                htmlText.append("<p><u><b>Time Sheet Details:</b></u><br>");
                htmlText.append("Week Start Date: "+startDate+"<br>");
                htmlText.append("Week End Date: "+endDate+"<br>");
                htmlText.append("Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
                htmlText.append("</body></html>");
            }else if(status.equals("Approved")){
                 htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>Hello "+empName+"</p>");
                htmlText.append("<p>Your Time Sheet has been Approved.</p>");
                htmlText.append("<p><u><b>Time Sheet Details:</b></u><br>");
                htmlText.append("Week Start Date: "+startDate+"<br>");
                htmlText.append("Week End Date: "+endDate+"<br>");
                htmlText.append("Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
                htmlText.append("</body></html>");
            }else {
                htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>A TimeSheet has been submitted by "+empName+" <br>");
                htmlText.append("<p><u>Time Sheet Details:</u><br>");
                htmlText.append("Week Start Date: "+startDate+"<br>");
                htmlText.append("Week End Date: "+endDate+"<br>");
                if(empType.equalsIgnoreCase("e")){
                htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"&type=e'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"&type=c'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }
                
                htmlText.append("<br><br> Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
                htmlText.append("</body></html>");
            }
            
           
             
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            if(!reportsTo.equalsIgnoreCase("")){
                transport.sendMessage(message,
                        message.getRecipients(Message.RecipientType.TO));
            }else if(!loginId.equalsIgnoreCase("")){
                transport.sendMessage(message,
                        message.getRecipients(Message.RecipientType.TO));
            }
          //  transport.sendMessage(message,message.getRecipients(Message.RecipientType.CC));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    */
    
    /*
     
     public static void sendReminders(String empName, String timeSheetID,String EmpId,String empType,HttpServletRequest httpServletRequest,String resourceType1,int projectId) throws ServiceLocatorException {
        
      //  System.out.println("empName--->"+empName+"----empId---->"+EmpId+"resourceType-->"+resourceType1);
        
      //  System.out.println("timeSheetID--->"+timeSheetID+"----empId---->"+EmpId+"-------empType------>"+empType + "projectID"+projectId);
        String loginId = "";
       // String status = DataSourceDataProvider.getInstance().getTimeSheetStatus(empName,timeSheetID);
        
         String status = DataSourceDataProvider.getInstance().getTimeSheetStatus(EmpId,timeSheetID,empType,resourceType1);
        
        String startDate = DataSourceDataProvider.getInstance().getTimeSheetStartDate(EmpId,timeSheetID,empType,resourceType1);
        
        String endDate = DataSourceDataProvider.getInstance().getTimeSheetEndDate(EmpId,timeSheetID,empType,resourceType1);
        String reportsTo="";
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        
        String to= "";
        String to1= "";
        String reportsToType = null;
        
  //      System.out.println("startDate--->"+startDate);
  //      System.out.println("endDate--->"+endDate);
 System.out.println("in c1-->"+resourceType1);
        if(status.equals("Disapproved") || status.equals("Approved")){ 
    //        System.out.println("in approve or diapprove---->"+empType);
            
            if(empType.equalsIgnoreCase("e")){
                if(resourceType1.equalsIgnoreCase("e")){
                    if(projectId!=0){
                         
                         
                            loginId = DataSourceDataProvider.getInstance().getEmpEmailIdbyId(EmpId);
      //                      System.out.println("reportsTo--->"+loginId);     
                     }
                    else
                    {
        //            System.out.println("in employee employyee");
                     loginId = DataSourceDataProvider.getInstance().getEmpLoginIdById(EmpId)+"@miraclesoft.com^-";
                     
                     
                    }
                }else{
          //          System.out.println("in employee customer");
                   // int reportsToId= DataSourceDataProvider.getInstance().getReportsToIdByContactId(Integer.parseInt(EmpId)); 
                     //          reportsToType=DataSourceDataProvider.getInstance().getReportsToTypeByReportsToId(reportsToId); 
                            loginId = DataSourceDataProvider.getInstance().getContactOfficeMail(Integer.parseInt(EmpId));
                   // loginId = DataSourceDataProvider.getInstance().getContactOfficeMail(Integer.parseInt(EmpId));
                            //System.out.println("reportsToId"+reportsToId);
                           // System.out.println("reportsToType"+reportsToType);
                }
            }else{
                //Customer email Id.
                System.out.println("in c-->"+resourceType1);
                 if(resourceType1.equalsIgnoreCase("c") || resourceType1.equalsIgnoreCase("v")){
                    loginId =  DataSourceDataProvider.getInstance().getEmailIdByContactId(Integer.parseInt(EmpId));
                     System.out.println("in if c-->"+loginId);
                 }else{
                     loginId = DataSourceDataProvider.getInstance().getEmpLoginIdById(EmpId)+"@miraclesoft.com^-";
                      System.out.println("in else c-->"+loginId);
                 }
            }
            StringTokenizer st = new StringTokenizer(loginId, "^");
             
            to = st.nextToken();
            to1= st.nextToken();
          //  System.out.println("to--->"+to);
            // System.out.println("to1--->"+to1);
        }else if(status.equals("Submitted")){
            if(empType.equalsIgnoreCase("e")){
                
                 if(resourceType1.equalsIgnoreCase("e")){
                     if(projectId!=0){
                         
                         int reportsToId= DataSourceDataProvider.getInstance().getReportsToIdByContactId(Integer.parseInt(EmpId),projectId); 
                               reportsToType=DataSourceDataProvider.getInstance().getReportsToTypeByReportsToId(reportsToId,projectId); 
                            reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(reportsToId,reportsToType);
              //              System.out.println("reportsTo--->"+reportsTo);
                           
                         
                     }else{
                reportsTo = DataSourceDataProvider.getInstance().reportsTo(EmpId)+"@miraclesoft.com^-";
                // System.out.println("reportsTo--->"+reportsTo);
               
                     }
                    
                }else{
                     int reportsToId= DataSourceDataProvider.getInstance().getReportsToIdByContactId(Integer.parseInt(EmpId),projectId); 
                               reportsToType=DataSourceDataProvider.getInstance().getReportsToTypeByReportsToId(reportsToId,projectId); 
                            reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(reportsToId,reportsToType);
                    //reportsTo = DataSourceDataProvider.getInstance().getContactOfficeMail(Integer.parseInt(EmpId));
                }
            }else{
                 String resourceType= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_RESOURCETYPE).toString();
               // reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(Integer.parseInt(EmpId),resourceType);
                  int reportsToId= DataSourceDataProvider.getInstance().getReportsToIdByContactId(Integer.parseInt(EmpId),projectId); 
                               reportsToType=DataSourceDataProvider.getInstance().getReportsToTypeByReportsToId(reportsToId,projectId); 
                            reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(reportsToId,reportsToType);
            }
            StringTokenizer st = new StringTokenizer(reportsTo, "^");
            to = st.nextToken();
            to1= st.nextToken();
            //System.out.println("to-->"+to);
            //System.out.println("to1-->"+to1);
            //System.out.println("resportsToType--->"+reportsToType);
        }
      //  System.out.println("reportsTo--->"+to);
        
        
        
       // System.out.println("to--->"+to);
        
        //String fromAdd = httpServletrequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        String from = "hubbleapp@miraclesoft.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        
        
        
        // String host = "mail.miraclesoft.com";
        
        
        Properties props = new Properties();
        
        
        props.setProperty("mail.transport.protocol", "smtp");
        
        
        props.setProperty("mail.host", SMTP_HOST);
        
        
        props.put("mail.smtp.auth", "true");
        
        Authenticator auth = new SMTPAuthenticator();
        //  Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
      //  mailSession.setDebug(true);
          mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("TimeSheet Reminder");
            message.setFrom(new InternetAddress(from));
            if(to!=null)
            {
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            }
            if(!"-".equals(to1) && to1!=null)
            {
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to1));
            }
          //  message.addRecipient(Message.RecipientType.CC,new InternetAddress("timesheets@miraclesoft.com"));
           // message.addRecipient(Message.RecipientType.CC,new InternetAddress("nseerapu@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            if(status.equals("Disapproved")) {
                htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>Hello "+empName+"</p>");
                htmlText.append("<p>Your Time Sheet has been Rejected.</p>");
                htmlText.append("<p><u><b>Time Sheet Details:</b></u><br>");
                htmlText.append("Week Start Date: "+startDate+"<br>");
                htmlText.append("Week End Date: "+endDate+"<br>");
                htmlText.append("Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
                htmlText.append("</body></html>");
            }else if(status.equals("Approved")){
                 htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>Hello "+empName+"</p>");
                htmlText.append("<p>Your Time Sheet has been Approved.</p>");
                htmlText.append("<p><u><b>Time Sheet Details:</b></u><br>");
                htmlText.append("Week Start Date: "+startDate+"<br>");
                htmlText.append("Week End Date: "+endDate+"<br>");
                htmlText.append("Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
                htmlText.append("</body></html>");
            }else {
                htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>A TimeSheet has been submitted by "+empName+" <br>");
                htmlText.append("<p><u>Time Sheet Details:</u><br>");
                htmlText.append("Week Start Date: "+startDate+"<br>");
                htmlText.append("Week End Date: "+endDate+"<br>");
                if(!(reportsToType!=null)){
                htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"&type=e'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("e"))
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"&type=e'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("v"))
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"&type=v'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("c"))
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"&type=c'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }
                
                htmlText.append("<br><br> Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
                htmlText.append("</body></html>");
            }
            
           
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            if(!reportsTo.equalsIgnoreCase("")){
                transport.sendMessage(message,
                        message.getRecipients(Message.RecipientType.TO));
            }else if(!loginId.equalsIgnoreCase("")){
                transport.sendMessage(message,
                        message.getRecipients(Message.RecipientType.TO));
            }
          //  transport.sendMessage(message,message.getRecipients(Message.RecipientType.CC));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
     
     */
public static void sendReminders(String empName, String timeSheetID,String EmpId,String empType,HttpServletRequest httpServletRequest,String resourceType1,int projectId ) throws ServiceLocatorException {
        
      //  System.out.println("empName--->"+empName+"----empId---->"+EmpId+"resourceType-->"+resourceType1);
        
       //System.out.println("timeSheetID--->"+timeSheetID+"----empId---->"+EmpId+"-------empType------>"+empType + "projectID"+projectId);
        String loginId = "";
       // String status = DataSourceDataProvider.getInstance().getTimeSheetStatus(empName,timeSheetID);
        
         String status = DataSourceDataProvider.getInstance().getTimeSheetStatus(EmpId,timeSheetID,empType,resourceType1);
        
        String startDate = DataSourceDataProvider.getInstance().getTimeSheetStartDate(EmpId,timeSheetID,empType,resourceType1);
        
        String endDate = DataSourceDataProvider.getInstance().getTimeSheetEndDate(EmpId,timeSheetID,empType,resourceType1);
        String reportsTo="";
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        String to= "";
        String to1= "";
        String reportsToType = null;
        
  //      System.out.println("startDate--->"+startDate);
      //  System.out.println("status--->"+status);
 //System.out.println("in c1-->"+resourceType1);
 //System.out.println("in c1-->"+status);
        if(status.equals("Disapproved") || status.equals("Approved")){ 
         //  System.out.println("in approve or diapprove---->"+empType);
            
            if(empType.equalsIgnoreCase("e")){
                if(resourceType1.equalsIgnoreCase("e")){
                    if(projectId!=0){
                         
     //                    System.out.println("in if1-->");
                            loginId = DataSourceDataProvider.getInstance().getEmpEmailIdbyId(EmpId);
       //                     System.out.println("reportsTo--->"+loginId);     
                     }
                    else
                    {
         //          System.out.println("in employee employyee");
                     loginId = DataSourceDataProvider.getInstance().getEmpLoginIdById(EmpId)+"@miraclesoft.com^-";
                     
                     
                    }
                }else{
           //      System.out.println("in employee customer");
                   // int reportsToId= DataSourceDataProvider.getInstance().getReportsToIdByContactId(Integer.parseInt(EmpId)); 
                     //          reportsToType=DataSourceDataProvider.getInstance().getReportsToTypeByReportsToId(reportsToId); 
                            loginId = DataSourceDataProvider.getInstance().getContactOfficeMail(Integer.parseInt(EmpId));
                   // loginId = DataSourceDataProvider.getInstance().getContactOfficeMail(Integer.parseInt(EmpId));
                            //System.out.println("reportsToId"+reportsToId);
                           // System.out.println("reportsToType"+reportsToType);
                }
            }else{
                //Customer email Id.
              // System.out.println("in c1-->"+resourceType1);
                 if(resourceType1.equalsIgnoreCase("c") || resourceType1.equalsIgnoreCase("v")){
                     //loginId =  DataSourceDataProvider.getInstance().getEmailIdByContactId(Integer.parseInt(EmpId));
                    loginId =  DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(Integer.parseInt(EmpId),resourceType1);
                   // System.out.println("in if c1-->"+loginId);
                 }else{
                     loginId = DataSourceDataProvider.getInstance().getEmpLoginIdById(EmpId)+"@miraclesoft.com^-";
                    //  System.out.println("in else c1-->"+loginId);
                 }
            }
            StringTokenizer st = new StringTokenizer(loginId, "^");
             
            to = st.nextToken();
            to1= st.nextToken();
            //System.out.println("to in if e--->"+to);
             //System.out.println("to1 in if e--->"+to1);
        }else if(status.equals("Submitted")){
            if(empType.equalsIgnoreCase("e")){
                
                 if(resourceType1.equalsIgnoreCase("e")){
                     if(projectId!=0){
                         
                         int reportsToId= DataSourceDataProvider.getInstance().getReportsToIdByContactId(Integer.parseInt(EmpId),projectId); 
                               reportsToType=DataSourceDataProvider.getInstance().getReportsToTypeByReportsToId(reportsToId,projectId); 
                            reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(reportsToId,reportsToType);
              //             System.out.println("reportsTo in if submitted--->"+reportsTo);
                           
                         
                     }else{
                reportsTo = DataSourceDataProvider.getInstance().reportsTo(EmpId)+"@miraclesoft.com^-";
             //  System.out.println("reportsTo in else submitted--->"+reportsTo);
               
                     }
                    
                }else{
                     int reportsToId= DataSourceDataProvider.getInstance().getReportsToIdByContactId(Integer.parseInt(EmpId),projectId); 
                               reportsToType=DataSourceDataProvider.getInstance().getReportsToTypeByReportsToId(reportsToId,projectId); 
                            reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(reportsToId,reportsToType);
                    //reportsTo = DataSourceDataProvider.getInstance().getContactOfficeMail(Integer.parseInt(EmpId));
               //             System.out.println("reportsTo in else customer1--->"+reportsTo);
                }
            }else{
                 String resourceType= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_RESOURCETYPE).toString();
               // reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(Integer.parseInt(EmpId),resourceType);
                  int reportsToId= DataSourceDataProvider.getInstance().getReportsToIdByContactId(Integer.parseInt(EmpId),projectId); 
                               reportsToType=DataSourceDataProvider.getInstance().getReportsToTypeByReportsToId(reportsToId,projectId); 
                            reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(reportsToId,reportsToType);
                 //            System.out.println("reportsTo in else customer2--->"+reportsTo);
            }
            StringTokenizer st = new StringTokenizer(reportsTo, "^");
            to = st.nextToken();
            to1= st.nextToken();
            //System.out.println("to-->"+to);
            //System.out.println("to1-->"+to1);
            //System.out.println("resportsToType--->"+reportsToType);
        }
        //System.out.println("reportsTo--->"+to);
        
        
        
        //System.out.println("to--->"+to);
        /** The from is used for storing the from address. */
        //String fromAdd = httpServletrequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        String from = "hubbleapp@miraclesoft.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        
        
        // String host = "mail.miraclesoft.com";
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        //  Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
      //  mailSession.setDebug(true);
          mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("TimeSheet Reminder");
            message.setFrom(new InternetAddress(from));
            if(to!=null)
            {
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            }
            if(!"-".equals(to1) && to1!=null)
            {
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to1));
            }
          //  message.addRecipient(Message.RecipientType.CC,new InternetAddress("timesheets@miraclesoft.com"));
           // message.addRecipient(Message.RecipientType.CC,new InternetAddress("nseerapu@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            if(status.equals("Disapproved")) {
                htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>Hello "+empName+"</p>");
                htmlText.append("<p>Your Time Sheet has been Rejected.</p>");
                htmlText.append("<p><u><b>Time Sheet Details:</b></u><br>");
                htmlText.append("Week Start Date: "+startDate+"<br>");
                htmlText.append("Week End Date: "+endDate+"<br>");
                htmlText.append("Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
                htmlText.append("</body></html>");
            }else if(status.equals("Approved")){
                 htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>Hello "+empName+"</p>");
                htmlText.append("<p>Your Time Sheet has been Approved.</p>");
                htmlText.append("<p><u><b>Time Sheet Details:</b></u><br>");
                htmlText.append("Week Start Date: "+startDate+"<br>");
                htmlText.append("Week End Date: "+endDate+"<br>");
                htmlText.append("Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
                htmlText.append("</body></html>");
            }else {
                htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>A TimeSheet has been submitted by "+empName+" <br>");
                htmlText.append("<p><u>Time Sheet Details:</u><br>");
                htmlText.append("Week Start Date: "+startDate+"<br>");
                htmlText.append("Week End Date: "+endDate+"<br>");
                if(!(reportsToType!=null)){
                htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://www.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"&type=e'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("e"))
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://www.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"&type=e'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("v"))
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://www.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"&type=v'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("c"))
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://www.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"&type=c'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }
                
                htmlText.append("<br><br> Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
                htmlText.append("</body></html>");
            }
            
           
               /* htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>Hello "+empName+"</p>");
                htmlText.append("<p>Your Time Sheet has been "+status+".</p>");
                htmlText.append("<p><u><b>Time Sheet Details:</b></u><br>");
                htmlText.append("Week Start Date: "+startDate+"<br>");
                htmlText.append("Week End Date: "+endDate+"<br>");
                if(status.equals("Disapproved") || status.equals("Approved")) {
                htmlText.append(" <br><br>\n\n\n Please click here <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"'> " +
                        "View The TimeSheet. </a> </font>");
                }else{
                htmlText.append(" <br><br>\n\n\n Please click here <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/getTeamTimeSheet.action?" +
                        "employeeID="+EmpId+"&emptimeSheetID="+timeSheetID+"'> " +
                        "View The TimeSheet </a> </font>  to Approve this TimeSheet.");
                }
                htmlText.append("<br>Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
                htmlText.append("</body></html>");
            
            */
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            if(!reportsTo.equalsIgnoreCase("")){
                transport.sendMessage(message,
                        message.getRecipients(Message.RecipientType.TO));
            }else if(!loginId.equalsIgnoreCase("")){
                transport.sendMessage(message,
                        message.getRecipients(Message.RecipientType.TO));
            }
          //  transport.sendMessage(message,message.getRecipients(Message.RecipientType.CC));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    /*
     public static void sendIssueEmail(String loginId, String empName, String createdBy, String description, String comments,
            String category, String severity, String type) throws ServiceLocatorException {
     *(String loginId,String password,String emailId)
     */
    
    /***
     *  ISSUE Tracking sysytem in hubble.
     *
     * UPDATED
     */
    
    public static void sendIssueEmail(String loginId, String secAssignTo, String createdBy, String description, String comments,
            String category, String severity, String type,String userName,String workPhno,String teamName,String custometName,String issueName,int issueId) {
        
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        String to = loginId;
        
        /** The from is used for storing the from address. */
        String from = "hubbleapp@miraclesoft.com";
        
        
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        
        //  String host = "mail.miraclesoft.com";
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        // Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
        //mailSession.setDebug(true);
        mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("New Issue Assigned");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            
            if(secAssignTo!=null && !"".equals(secAssignTo)){
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(secAssignTo+"@miraclesoft.com"));
            }
            
            
            //message.addRecipient(Message.RecipientType.CC,new InternetAddress("mchennu@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><table align='center' width='635px' style='padding-left=15px'");
            htmlText.append(" background='cid:image' border='0' cellpadding='' ");
            htmlText.append("cellspacing='' height='800px'><tr><td width='580px'");
            htmlText.append(" height='150px'></td></tr><tr><td height='20px' colspan='2'>");
            htmlText.append("<font color='#151B54' size='4px'>Hello,</font> <br><br>");
            htmlText.append("</td></tr><tr><td height='30px' colspan='2'><font ");
            htmlText.append("color='#151B54' size='4px'>This is Issue Tracking ");
            htmlText.append("System in Hubble. <br> A New Issue was just");
            htmlText.append(" created in the System.</font><br></br></td></tr><tr><td colspan='2'>");
            
            
            htmlText.append("<font color='#3090C7' size='3px'>To View More");
            htmlText.append("Details of this Issue, or to Update please visit ");
            htmlText.append("the following URL :<a");
            //htmlText.append(" href='http://w3.miraclesoft.com/Hubble/'>");
            //htmlText.append(" href='http://w3.miraclesoft.com/Hubble//employee/issues/getIssue.action?issueId="+issueId+"&accessType=Issue'>");
            htmlText.append(" href='http://www.miraclesoft.com/Hubble/employee/issues/getIssue.action?issueId="+issueId+"&accessType=Issue&resM='>");
            htmlText.append("Click Here To view Issue Details</a></font></td></tr> ");
            
            /*-- add condition  ---*/
            // custometName,String issueName) {
            
            if(!custometName.equals("")){
                htmlText.append("<tr><td> <table border='0' align='left'> <tr><td height='20px'><font ");
                htmlText.append("color='#151B54' size='4px'>Customer Name :</font></td>");
                htmlText.append("<td align='left' height='20px'><font color='red' ");
                htmlText.append("size='4px'>"+custometName+"</font></td></tr>");
            }
            if(!issueName.equals("")){
                htmlText.append(" <tr><td  height='20px'><font color='#151B54' ");
                htmlText.append("size='4px'>Issue Title :</font></td><td align='left' ");
                htmlText.append("height='20px'><font color='red' size='4px'>"+issueName+"</font></td></tr>");
            }
            
         /*   if(!category.equals("")){
            htmlText.append("<tr><td> <table border='0' align='left'> <tr><td height='20px'><font ");
            htmlText.append("color='#151B54' size='4px'>IssueCategory :</font></td>");
            htmlText.append("<td align='left' height='20px'><font color='red' ");
            htmlText.append("size='4px'>"+category+"</font></td></tr>");
             }*/
            if(!severity.equals("")){
                htmlText.append(" <tr><td  height='20px'><font color='#151B54' ");
                htmlText.append("size='4px'>Issue Severity :</font></td><td align='left' ");
                htmlText.append("height='20px'><font color='red' size='4px'>"+severity+"</font></td></tr>");
            }
           /*  if(!type.equals("")){
            htmlText.append(" <tr><td height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>Issue Type :</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+type+"</font></td></tr>");
             }*/
            if(!description.equals("")){
                htmlText.append(" <tr><td height='20px'><font color='#151B54' ");
                htmlText.append("size='4px'>Description :</font></td><td align='left' ");
                htmlText.append("height='20px'><font color='red' size='4px'>"+description+"</font></td></tr>");
            }
            if(!comments.equals("")){
                htmlText.append(" <tr><td height='20px'><font color='#151B54' ");
                htmlText.append("size='4px'>Comments :</font></td><td align='left' ");
                htmlText.append(" height='20px'><font color='red' size='4px'>"+comments+"</font></td></tr>");
            }
            
            /*-- end of add condition  ---*/
            
            
            htmlText.append(" <tr><td height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>Created By :</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+userName+"</font></td></tr> <tr><td");
            
            htmlText.append(" height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>Email:</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+createdBy+"@miraclesoft.com"+"</font></td></tr> <tr><td");
            
            
            htmlText.append(" height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>Work Phno :</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+workPhno+"</font></td></tr> <tr><td");
            
            htmlText.append(" height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>Team :</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+teamName+"</font>");
            
            
            htmlText.append("</td></tr>  </table> </td></tr> <tr><td colspan='2' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'><br>Regards,</font>");
            htmlText.append("</td></tr><tr><td colspan='2' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'>Corporate ");
            htmlText.append("Application Support Team,</font></td></tr><tr>");
            htmlText.append("<td colspan='2' height='20px'><font color='#151B54'");
            htmlText.append(" size='4px'>Miracle Software Systems, Inc.</font>");
            htmlText.append("</td></tr><tr><td width='600px' height='60px'></td></tr>");
            htmlText.append("</table></body></html>");
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("MailImage.Path"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID","<image>");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
        
    }
    
    /**
     * Name : sendInvitation
     * DESC : Invitation From Miracle Software Systems
     *
     *  UPDATED
     */
    
    
    public static int sendInvitation(String contactName, String emailId, String path) throws ServiceLocatorException {
        
        int check = 0;
        FileInputStream fileInput = null;
        DataInputStream dataInputStream = null;
        BufferedReader bufferedReader =null;
        String to = emailId;
        
        /** The from is used for storing the from address. */
        //String from = "soa-webcast@miraclesoft.com";
        //String from = "b2bwebinar@miraclesoft.com";
        //String from = "datapowerwebinar@miraclesoft.com";
        String from = "marketing@miraclesoft.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        
        // String host = "mail.miraclesoft.com";
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
        /** Here set the authentication for the host **/
         props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        //Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
        //mailSession.setDebug(true);
        mailSession.setDebug(false);
        
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Invitation From Miracle Software Systems");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //message.addRecipient(Message.RecipientType.CC,new InternetAddress("mchennu@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            fileInput = new FileInputStream(path);
            dataInputStream = new DataInputStream(fileInput);
            bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
            String tempStr = null;
            while ((tempStr = bufferedReader.readLine()) != null) {
                htmlText.append(tempStr);
            }
            htmlText.append("<div align='center'><font size='1px' face='Sans'>Please <a href='http://www.miraclesoft.com/msws/subscriptions.html?emailId="+emailId+"&subscription=unsubscribe'>Click Here</a> to Unsubscribe</font></div>");
            //System.err.println(tempStr);
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("B2BWebinarImage.Path"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID","<image>");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            transport.connect();
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            transport.close();
            check = 1;
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }catch(Exception ie) {
            ie.printStackTrace();
        }
        return check;
    }
    
    /**
     * Name : sendRequirmentDetails
     * DESC : To send the requirement Details.
     * UPDATED
     */
    
//    public static void sendRequirmentDetails(String practise,String title, String rate,String country,String state) throws ServiceLocatorException {
//        // String salary, String exp,
//        List userId = DataSourceDataProvider.getInstance().getManagerUserIdOfRecruitment();
//        //System.out.println("MAnagers list-->"+userId);
//        /** The from is used for storing the from address. */
//        String from = "hubbleapp@miraclesoft.com";
//        
//        /**The props is instance variabel to <code>Properties</code> class */
//        Properties props = new Properties();
//        
//        /**Here set smtp protocal to props */
//        props.setProperty("mail.transport.protocol", "smtp");
//        
//        //**Here set the address of the host to props */
//        props.setProperty("mail.host", SMTP_HOST);
//        /** Here set the authentication for the host **/
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", SMTP_PORT);
//        
//        
//        Authenticator auth = new SMTPAuthenticator();
//        // Session mailSession = Session.getDefaultInstance(props, null);
//        Session mailSession = Session.getDefaultInstance(props, auth);
//        //mailSession.setDebug(true);
//        mailSession.setDebug(false);
//        Transport transport;
//        List practiseManagers = DefaultDataProvider.getInstance().getPracticeManager();
//        try {
//            
//            if(practise.equals("SAP")) {
//                for(Iterator it = practiseManagers.iterator(); it.hasNext();) {
//                    userId.add(it.next().toString()+"@miraclesoft.com");
//                }
//                //userId.add("sghandikota");
//                
//            }
//            
//            for(Iterator it = userId.iterator(); it.hasNext();) {
//                /** The to is used for storing the user mail id to send details. */
//                String loginId = it.next().toString();
//                //System.out.println(loginId +" Practice Managers List and Tech leads...");
//                
//                //String to = "nseerapu@miraclesoft.com";
//                String to = loginId+"@miraclesoft.com";
//                //String bcc = "nseerapu@miraclesoft.com";
//                String assignedToName = DataSourceDataProvider.getInstance().getFname_Lname(loginId);
//                transport = mailSession.getTransport();
//                MimeMessage message = new MimeMessage(mailSession);
//                message.setSubject("New Requirement Added");
//                message.setFrom(new InternetAddress(from));
//                message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//                //message.addRecipient(Message.RecipientType.BCC,new InternetAddress(bcc));
//                //message.addRecipient(Message.RecipientType.CC,new InternetAddress("mchennu@miraclesoft.com"));
//                
//                
//                // This HTML mail have to 2 part, the BODY and the embedded image
//                //
//                MimeMultipart multipart = new MimeMultipart("related");
//                
//                // first part  (the html)
//                BodyPart messageBodyPart = new MimeBodyPart();
//                StringBuilder htmlText = new StringBuilder();
//                htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
//                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
//                htmlText.append("<p>Hello "+assignedToName+"</p>");
//                htmlText.append("<p>New Requirement has been Assigned.</p>");
//                htmlText.append("<p><u><b>Requirement Details:</b></u><br>");
//                htmlText.append("Job Title: "+title+" <br>");
//                htmlText.append("Location: "+state+","+country+"<br>");
//                htmlText.append("Target Rate: "+rate+" <br>");
//                // htmlText.append("Target Salary: "+salary+" <br>");
//                // htmlText.append("Experience(In Years): "+exp+" <br>");
//                htmlText.append("Thank you.<br>");
//                htmlText.append("For More Details about this Requirement ");
//                htmlText.append("Visit the following URL :<a");
//                htmlText.append(" href='http://www.miraclesoft.com/Hubble/'>");
//                htmlText.append("http://www.miraclesoft.com/Hubble/</a></p></font>");
//                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
//                htmlText.append("</body></html>");
//                
//                
//                messageBodyPart.setContent(htmlText.toString(), "text/html");
//                
//                // add it
//                multipart.addBodyPart(messageBodyPart);
//                
//                // second part (the image)
//                messageBodyPart = new MimeBodyPart();
//                DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("MailImage.Path"));
//                messageBodyPart.setDataHandler(new DataHandler(fds));
//                messageBodyPart.setHeader("Content-ID","<image>");
//                
//                // add it
//                multipart.addBodyPart(messageBodyPart);
//                
//                // put everything together
//                message.setContent(multipart);
//                
//                transport.connect();
//                transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
//                //transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
//                transport.close();
//            }
//        } catch (NoSuchProviderException ex) {
//            ex.printStackTrace();
//        }  catch (MessagingException ex) {
//            ex.printStackTrace();
//        }catch(NullPointerException nex){
//            nex.printStackTrace();
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//    }

    public static void sendRequirmentDetails(String practise, String title, String rate, String country, String state, String primarySkills, String secondarySkills) throws ServiceLocatorException {

        Map managersMap = DataSourceDataProvider.getInstance().getManagerEmailsOfRecruitment(country);
        String managers=com.mss.mirage.util.Properties.getProperty("RecruitManager");
           String mgrs[] = managers.split(Pattern.quote(","));
            for (int i = 0; i < mgrs.length; i++) {
                managersMap.put(mgrs[i]+"@miraclesoft.com",mgrs[i]);
            }
        String url = com.mss.mirage.util.Properties.getProperty("PROD.URL");
        int id = DataSourceDataProvider.getInstance().getRequirementMaxId();
        String from = "hubbleapp@miraclesoft.com";
        Authenticator auth = new SMTPAuthenticator();
        List practiseManagers = DefaultDataProvider.getInstance().getPracticeManager();
        try {

            if (practise.equals("SAP")) {
                for (Iterator it = practiseManagers.iterator(); it.hasNext();) {
                    String loginId = it.next().toString();

                    String assignedToName = DataSourceDataProvider.getInstance().getFname_Lname(loginId);
                    managersMap.put(loginId + "@miraclesoft.com", assignedToName);
                }

            }


            //System.out.println("To email-->"+to);
            //System.out.println("assignedToName-->"+assignedToName);

            String to = "";
            String subject = "New Requirement Added";
            Iterator entries = managersMap.entrySet().iterator();
            while (entries.hasNext()) {
                Entry thisEntry = (Entry) entries.next();
                Object key = thisEntry.getKey();
                Object value = thisEntry.getValue();
                String email = key.toString();
                to += email + ",";
                String assignedToName = value.toString();
            }

            to = to.substring(0, to.length() - 1);
            MimeMultipart multipart = new MimeMultipart("related");

            StringBuilder htmlText = new StringBuilder();
            /*
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
            // htmlText.append("<p>Hello "+assignedToName+"</p>");
            htmlText.append("<p>Hello Team,</p>");
            htmlText.append("<p>New Requirement has been Added.</p>");
            htmlText.append("<p><u><b>Requirement Details:</b></u><br>");
            htmlText.append("Job Title: "+title+" <br>");
            htmlText.append("Location: "+state+","+country+"<br>");
            htmlText.append("Target Rate: "+rate+" <br>");
            // htmlText.append("Target Salary: "+salary+" <br>");
            // htmlText.append("Experience(In Years): "+exp+" <br>");
            htmlText.append("Thank you.<br>");
            htmlText.append("For More Details about this Requirement ");
            htmlText.append("Visit the following URL :<a");
            htmlText.append(" href='http://www.miraclesoft.com/Hubble/'>");
            htmlText.append("http://www.miraclesoft.com/Hubble/</a></p></font>");
            htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
            htmlText.append("</body></html>");
            
             */

          /*  htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><table align='center'><tr style='background:#07BBD7;height:40px;'><td><font color='white' size='4' face='Arial'>");

            htmlText.append("<p>New Requirement Added.</p></font></td></tr>");
            htmlText.append("<tr><td><table style='background:#CCDBDE;width:100%;'><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<p>Hello Team,</p>");
            htmlText.append("</font></td></tr><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<p>New Requirement has been Added.</font>  </td></tr>");
            htmlText.append("<tr><td><font color='#111728' size='2' face='Arial' style='font-weight:600;'><p><u><b>Requirement Details:</b></u><br><br>");


            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Job Title: </font>");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + title + " </font>");
            htmlText.append("</td></tr>");

            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Location: </font>");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + state + "," + country + " </font>");
            htmlText.append("</td></tr>");

            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Primary Skills: </font>");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + primarySkills + " </font>");
            htmlText.append("</td></tr>");

            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Secondary Skills: </font>");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + secondarySkills + " </font>");
            htmlText.append("</td></tr>");

            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Target Rate: </font>");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + rate + " </font>");
            htmlText.append("</td></tr>");

            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'> ");
            htmlText.append("<p>For More Details about this Requirement <u><b><a href='" + url + "crm/requirement/getRequirementDetails.action?objectId=" + id + "&requirementAdminFlag=YES&recruitmentRoleType=6'>Click Here</a>");
            htmlText.append(" </b></u></p></font></td></tr></td></tr></table><tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("*Note:Please do not reply to this e-mail. It was generated by our System.</font> </td></tr></table>");
            htmlText.append("</body></html>");
            
            */
            htmlText.append("<!DOCTYPE html>");
htmlText.append("<html>");
   htmlText.append("<head>");
     htmlText.append("<meta charset='utf-8'>");
      htmlText.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
      htmlText.append("<meta http-equiv='X-UA-Compatible' content='IE=edge' />");
      htmlText.append("<style type='text/css'>");
        
         htmlText.append("body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;} table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;} img{-ms-interpolation-mode: bicubic;}"); 
         
         htmlText.append("img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}table{border-collapse: collapse !important;}body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}");
         
         htmlText.append("a[x-apple-data-detectors] {color: inherit !important;text-decoration: none !important;font-size: inherit !important; font-family: inherit !important;font-weight: inherit !important;line-height: inherit !important;}");
        
         htmlText.append("@media screen and (max-width: 525px) {");
         
         htmlText.append(".wrapper {width: 100% !important; max-width: 100% !important;}");
       
         htmlText.append(".logo img {margin: 0 auto !important;}");
       
         htmlText.append(".mobile-hide {display: none !important;}");
         htmlText.append(".img-max {max-width: 100% !important;width: 100% !important;height: auto !important;}");
         
        htmlText.append(" .responsive-table {width: 100% !important;}");
        
         htmlText.append(".padding {padding: 10px 5% 15px 5% !important;}");
         htmlText.append(".padding-meta {padding: 30px 5% 0px 5% !important;}");
         htmlText.append(".padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}");
         htmlText.append(".no-padding {padding: 0 !important;}");
         htmlText.append(".section-padding {padding: 50px 15px 50px 15px !important;}");
         
         htmlText.append(".mobile-button-container {margin: 0 auto;width: 100% !important;}");
         htmlText.append(".mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important;display: block !important;}}");
        
         htmlText.append("div[style*='margin: 16px 0;'] { margin: 0 !important; }");
      htmlText.append("</style>");
   htmlText.append("</head>");
   htmlText.append("<body style='margin: 0 !important; padding: 0 !important;'>");
     
      htmlText.append("<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>Job Title:</b> " + title + "</div>");
     
      htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
         htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center'>");
             
                        htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper'>");
                           htmlText.append("<tr>");
                              htmlText.append("<td align='center' valign='top' style='padding: 15px 0;' class='logo'>");
                                 htmlText.append("<a href='http://www.miraclesoft.com/' target='_blank'><img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'></a>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                        htmlText.append("</table>");
                        
            htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
              
                        htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
                           htmlText.append("<tr>");
                              htmlText.append("<td>");
                                
                                 htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>New Requirement Add</b></td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                        htmlText.append("</table>");
                        
            htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");
              
                        htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
                           htmlText.append("<tr>");
                              htmlText.append("<td>");
                                 htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>Team,</b><br>A new requirement has been added and following are the details of it.");
                                       htmlText.append("</td>");
                                    htmlText.append("</tr>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Job Title:</b> " + title + "<br>");
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Location:</b> " + state + "," + country + "</b><br>");
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Primary Skills:</b> " + primarySkills + "<br>");
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Secondary Skills:</b> " + secondarySkills + "<br>");
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Target Rate:</b> " + rate + "<br>");
                                       htmlText.append("</td>");
                                    htmlText.append("</tr>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b>Click <a href='" + url + "crm/requirement/getRequirementDetails.action?objectId=" + id + "&requirementAdminFlag=YES&recruitmentRoleType=6' target='_blank' style='font-size: 14px; color: #2368a0;'>here</a> to know more about this requirement</b>");
                                       htmlText.append("</td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                           htmlText.append("<tr>");
                              htmlText.append("<td>");
                                 
                                 htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>Thanks & Regards,<br>Corporate Application Support Team, <br>Miracle Software Systems, Inc. <br>Email: hubble@miraclesoft.com <br>Phone: (+1)248-233-1814");
                                       htmlText.append("</td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                           htmlText.append("<tr>");
                              htmlText.append("<td>");
                              
                                 htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                        htmlText.append("</table>");
                      
            htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");
               
                        htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' class='responsive-table'>");
                           htmlText.append("<tr>");
                              htmlText.append("<td width='200' align='center' style='text-align: center;'>");
                                 htmlText.append("<table width='200' cellpadding='0' cellspacing='0' align='center'>");
                                    htmlText.append("<tr>");
                                      htmlText.append("<td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                       htmlText.append("<td width='10'><a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                       htmlText.append("<td width='10'><a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                       htmlText.append("<td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                       htmlText.append("<td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                           htmlText.append("<tr>");
                              htmlText.append("<td height='10'>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                           htmlText.append("<tr>");
                              htmlText.append("<td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems<br></td>");
                           htmlText.append("</tr>");
                        htmlText.append("</table>");
                     
            htmlText.append("</td>");
         htmlText.append("</tr>");
      htmlText.append("</table>");
   htmlText.append("</body>");
htmlText.append("</html>");


            
            
            
           // System.out.println("htmlText.toString()--->" + htmlText.toString());



            // add it

            // second part (the image)

            // add it

            // put everything together

            ServiceLocator.getMailServices().doAddEmailLogNew(to, "", subject, htmlText.toString(), "", "","Requirement Notification");
            //  transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            //transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
            //}
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Name : sendUpdatedRequirementDetails
     * DESC : To send the Updated requirement details to sales and requirement people.
     *  UPDATED
     */
    
    public static void sendUpdatedRequirementDetails(String subjectinfo,String title, String user,String rate, String id,String country,String state) throws ServiceLocatorException {
        //, String salary, String exp
        // StringBuffer toList = new StringBuffer();
        
        // String loginId = DataSourceDataProvider.getInstance().getEmpLoginIdById(user);
        
        String createdBy = DataSourceDataProvider.getInstance().getCreatedByRequirementId(id);
        
        String createdByName = DataSourceDataProvider.getInstance().getFname_Lname(createdBy);
        
        String workPhoneNo = DataSourceDataProvider.getInstance().getWorkPhNoByLoginId(createdBy);
        
        
        String cc = createdBy+"@miraclesoft.com";
        //String cc = "nseerapu@miraclesoft.com";
        String bcc = "vkandregula@miraclesoft.com";
        //to send mail to ajay bhat
        
        String cc2=com.mss.mirage.util.Properties.getProperty("RecruitManager");
        cc2= cc2+"@miraclesoft.com";
        //System.out.println("abhatt--->"+cc2);
        /** The from is used for storing the from address. */
        String from = "hubbleapp@miraclesoft.com";
        
        String loginId="";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        
        // String host = "mail.miraclesoft.com";
        // String host="mail.miraclesoft.com";
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        // Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
       // mailSession.setDebug(true);
         mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            
            // message.setSubject("Requirement Notification");
            message.setSubject(subjectinfo);
            message.setFrom(new InternetAddress(from));
            
            
            String MailToUserList[]=user.split("#");
            
            for(int i=0;i<MailToUserList.length;i++){
                
                //loginId = DataSourceDataProvider.getInstance().getEmpLoginIdById(MailToUserList[i]);
                loginId = DataSourceDataProvider.getInstance().getEmpEmailById(MailToUserList[i]);
                if(loginId!=null && loginId!=""){
                    //String toAddr=loginId+"@miraclesoft.com";
                    String toAddr=loginId;
                    message.addRecipient(Message.RecipientType.TO,new InternetAddress(toAddr));
                    // message.addRecipient(Message.RecipientType.TO,new InternetAddress("nseerapu@miraclesoft.com"));
                    // toList.append(loginId+"@miraclesoft.com;");
                }
                // loginId="";
            }
            
            message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
            message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc2));
           // message.addRecipient(Message.RecipientType.BCC,new InternetAddress(bcc));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            
            
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
            htmlText.append("<p>Hello "+createdByName+"</p>");
            htmlText.append("<p>"+subjectinfo+"</p>");
            htmlText.append("<p><u><b>Requirement Details:</b></u><br>");
            htmlText.append("Job Title: "+title+" <br>");
            htmlText.append("Location: "+state+","+country+"<br>");
            htmlText.append("Target Rate: "+rate+" <br>");
            //htmlText.append("Target Salary: "+salary+" <br>");
            //htmlText.append("Experience(In Years): "+exp+" <br><br>");
            htmlText.append("<b>This Requirement has been assigned to "+user+", Work Phone:"+workPhoneNo+"</b><br>");
            htmlText.append("Thank you.<br><br>");
            htmlText.append("For More Details about this Requirement ");
            htmlText.append("<a");
            htmlText.append(" href='http://www.miraclesoft.com/Hubble/crm/requirement/getRequirement.action?objectId="+id+"'>");
            htmlText.append("Click Here</a></p></font>");
            htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
            htmlText.append("</body></html>");
            
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("MailImage.Path"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID","<image>");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.CC));
           // transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
            transport.close();
            
            
            
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
        
        
        
        
    }
    
    /***
     * Name : sendGreenSheetDetails
     * DESC : To send the green sheet details.
     * UPDATED
     */
    
    public static void sendGreenSheetDetails(GreenSheetAction greenSheetAction,String loginEmpName) throws ServiceLocatorException {
        
        String name = greenSheetAction.getFname()+" " + greenSheetAction.getLastName();
        
        String customerName = greenSheetAction.getCustomerName();
        
        String billingRate = greenSheetAction.getClientBillingRate();
        
        Double duration = greenSheetAction.getDuration();
        
        
        String primarySalesPerson =DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(greenSheetAction.getPrimarySalesPerson()));
        String primarySalesManager =DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(greenSheetAction.getPrimarySalesManager()));
        String secondarySalesPerson =DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(greenSheetAction.getSecondarySalesPerson()));
        String primaryVicePresident =DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(greenSheetAction.getPrimaryVicePresident()));
        String secondaryVicePresident =DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(greenSheetAction.getSecondaryVicePresident()));
        Double primarySalesPersonCommission =greenSheetAction.getPriSalesPersonCommission();
        Double primarySalesManagerCommission =greenSheetAction.getPriSalesMngCommission();
        Double secondarySalesPersonCommission =greenSheetAction.getSecondarySalesPersonCommission();
        Double primaryVicePresidentCommission =greenSheetAction.getPrimaryVicePresidentCommission();
        Double secondaryVicePresidentCommission =greenSheetAction.getSecondaryVicePresidentCommission();
        //newly added on 03062013
      /*  String poStartDate=greenSheetAction.getStartDate().toString();
        String poEndtDate=greenSheetAction.getEndDate().toString();
        String conStartDate=greenSheetAction.getConsStartDate().toString();
        */
        String poStartDate= "";
        String poEndtDate="";
        String conStartDate="";
        if(greenSheetAction.getStartDate()!=null)
        poStartDate=greenSheetAction.getStartDate().toString();
        if(greenSheetAction.getEndDate()!=null)
         poEndtDate=greenSheetAction.getEndDate().toString();
        if(greenSheetAction.getConsStartDate()!=null)
         conStartDate=greenSheetAction.getConsStartDate().toString();
        int greendheetId=greenSheetAction.getId();
        
        
        String empcreatedId=DataSourceDataProvider.getInstance().getGreensheetCreatedByName(greendheetId);
        // String empName= DataSourceDataProvider.getInstance().getFname_Lname(empcreatedId);
        String reportsTo=DataSourceDataProvider.getInstance().getReportsTOOneLevel(empcreatedId);
        
        /** The to is used for storing the user mail id to send details.  */
       
        String to = com.mss.mirage.util.Properties.getProperty("Greensheet.To");
        // String bcc = "ssg@miraclesoft.com";
        
       // String cc = "sratnala@miraclesoft.com";
        String ccMailIds = com.mss.mirage.util.Properties.getProperty("Greensheet.cc");
        //System.out.println("cc mail ids-->"+ccMailIds);
        String cc="";
        StringTokenizer st = null;
          int count=0;
          if(ccMailIds!=null && !"".equals(ccMailIds))
          {
         st = new StringTokenizer(ccMailIds,",");
        count=st.countTokens();
          }
        //System.out.println("count-->"+count);
        
        String cc2 =  reportsTo+"@miraclesoft.com";
       // String cc3 = com.mss.mirage.util.Properties.getProperty("Greensheet.cc3");
       // String bcc = "nseerapu@miraclesoft.com";
         String bcc = com.mss.mirage.util.Properties.getProperty("Greensheet.bcc");
        
        
        /* String to = "invoicing@miraclesoft.com";
        //String to = "nseerapu@miraclesoft.com";
        // String bcc = "ssg@miraclesoft.com";
        
        String cc = "sratnala@miraclesoft.com";
        String cc2 =  reportsTo+"@miraclesoft.com";
        
        String bcc = "nseerapu@miraclesoft.com";
        //String cc = "rdadi@miraclesoft.com";
        /** The from is used for storing the from address.*/
        String from = "hubbleapp@miraclesoft.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail*/
        // String host = "192.168.5.5";//mail.miraclesoft.com local
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //String bcc = "vkandregula@miraclesoft.com";
        
        //**Here set the address of the host to props
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        //  Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
      //  mailSession.setDebug(true);
          mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            //message.setSubject("New Requirement Assigned");
            message.setSubject("New GreenSheet Added");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            if(ccMailIds!=null && !"".equals(ccMailIds))
          {
            for(int i=0;i<count;i++)
            {
                cc=st.nextToken();
               // System.out.println("actual mails-->"+cc);
            message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
            
            }
          }
            message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc2));
           // message.addRecipient(Message.RecipientType.BCC,new InternetAddress(bcc));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
            htmlText.append("<p>Hello Team, </p>");
            htmlText.append("<p>A new GreenSheet has been Added.</p>");
            htmlText.append("<p><u><b>GreenSheet Details:</b></u><br><br>");
            htmlText.append("<b>Created By : </b> "+loginEmpName+" <br>");
            htmlText.append("<b>Consultant Name : </b> "+name+" <br>");
            htmlText.append("<b>Customer Name : </b>"+customerName+"<br>");
            htmlText.append("<b>Billing Rate : </b> "+billingRate+" <br>");
            htmlText.append("<b>Duration : </b> "+duration+"  Months <br>");
            htmlText.append("<b>POStartDate : </b> "+poStartDate+" <br>");
            htmlText.append("<b>POEndtDate : </b> "+poEndtDate+" <br>");
            htmlText.append("<b>Consultant Start Date : </b> "+conStartDate+" <br>");
            htmlText.append(" <table border=\"0\" cellpadding=\"0\" cellspacing=\"5\">");
            htmlText.append(" <th align=\"left\"> <font color=\"blue\" size=\"3\">Commissions: </font></th>");
            htmlText.append("<tr><td width=\"70\" align=\"left\"><b><font color=\"blue\" size=\"2\"> Designation </font></b></td><td align=\"left\" width=\"80\"><font color=\"blue\" size=\"2\"><b> Name</b></font></td><td align=\"left\" width=\"80\"><font color=\"blue\" size=\"2\"><b>    Commission<b></font></td</tr>");
            htmlText.append("<tr><td><font color=\"blue\" size=\"2\"> PrimarySalesPerson:</font></td><td align=\"left\"><font color=\"blue\" size=\"2\"> "+primarySalesPerson+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+primarySalesPersonCommission+"%</font></td></tr> <br>");
            htmlText.append("<tr><td><font color=\"blue\" size=\"2\"> PrimarySalesManager:</font></td><td align=\"left\"> <font color=\"blue\" size=\"2\"> "+primarySalesManager+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+primarySalesManagerCommission+"%</font></td></tr> <br>");
            htmlText.append("<tr><td><font color=\"blue\" size=\"2\">SecondarySalesPerson:</font></td><td align=\"left\"><font color=\"blue\" size=\"2\"> "+secondarySalesPerson+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+secondarySalesPersonCommission+"%</font></td></tr> <br>");
             if(primaryVicePresident !=null && !"".equals(primaryVicePresident)) {
                htmlText.append("<tr><td><font color=\"blue\" size=\"2\">PrimaryVicePresident:</font></td><td align=\"left\"><font color=\"blue\" size=\"2\"> "+primaryVicePresident+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+primaryVicePresidentCommission+"%</font></td></tr> <br>");
            }
              if(secondaryVicePresident !=null && !"".equals(secondaryVicePresident)) {
            
                htmlText.append("<tr><td><font color=\"blue\" size=\"2\">SecondaryVicePresident:</font></td><td align=\"left\"><font color=\"blue\" size=\"2\"> "+secondaryVicePresident+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+secondaryVicePresidentCommission+"%</font></td></tr>");
            }
            
            htmlText.append("</table>");
            htmlText.append("<br><br> Thank you.</p></font>");
            htmlText.append("<font color='black' size='2' face='Arial'>For More Details about this GreenSheet ");
            htmlText.append("Visit the following URL to approve greensheet:</font>");
            
            htmlText.append(" <br>\n\n\n <font color=\"red\"> <a href='http://www.miraclesoft.com/Hubble/crm/greensheets/getGreenSheetByID.action?id="+greendheetId+"&teamGreensheets=true'> Click Here To Approve GreenSheet</a> </font><br>");
            htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
            
            //htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
            htmlText.append("</body></html>");
            
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // second part (the image)
           // messageBodyPart = new MimeBodyPart();
          //  DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("MailImage.Path"));
          //  messageBodyPart.setDataHandler(new DataHandler(fds));
           // messageBodyPart.setHeader("Content-ID","<image>");
            
            // add it
          //  multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.CC));
            //transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    
    
    /***
     *  Name : sendLeaveEmail
     *  DESC: To send the leave Details.
     *
     *  UPDATED
     */
    
    public static void sendLeaveEmail(String leaveLoginId, String leaveCc, String leavePersonName, String userLoginName, String reason, String status, String leaveRequiredFrom,
            String leaveRequiredTo, String leaveType) {
        
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        String to = leaveLoginId;
        String cc = leaveCc;
        
        /** The from is used for storing the from address. */
        String from = "hubbleapp@miraclesoft.com";
        
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        // String host = "192.168.5.5";
        
        
        //  String host = "mail.miraclesoft.com";
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
       //  System.out.println("in Leave mailing...........");
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        //  System.err.println("before auth");
        Authenticator auth = new SMTPAuthenticator();
        
        //   System.err.println("After auth"+auth);
        //Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
       // mailSession.setDebug(true);
         mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("New Leave Request Mail");
            message.setFrom(new InternetAddress(from));
            
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //    message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
            //message.addRecipient(Message.RecipientType.BCC,new InternetAddress(cc));
            
            // -- We could include CC recipients too --
            //if (cc != null)
            //  message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc, false));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><table align='center' width='600px' style='padding-left=15px'");
            htmlText.append(" background='cid:image' border='0' cellpadding='' ");
            htmlText.append("cellspacing='' height='800px'><tr><td width='580px'");
            htmlText.append(" height='150px'></td></tr><tr><td height='20px' colspan='2'><br>");
            //htmlText.append("<font color='#151B54' size='4px'>Employee Name: "+leavePersonName+",</font> <br><br>");
            htmlText.append("</td></tr><tr><td height='30px' colspan='2'><font ");
            htmlText.append("color='#151B54' size='4px'>This is Leave Tracking ");
            htmlText.append("System in Hubble. <br> A New Leave Request was just ");
            htmlText.append(status+ "</font><br></br></td></tr><tr><td colspan='2'>");
            
            
            htmlText.append("<font color='#3090C7' size='3px'>To View More");
            htmlText.append("Details of this Leave, or to Update please visit ");
            htmlText.append("the following URL :<a");
            htmlText.append(" href='http://www.miraclesoft.com/Hubble/'>");
            htmlText.append("http://www.miraclesoft.com/Hubble/</a></font>");
            
            htmlText.append("</td></tr> <tr><td> <table border='0' align='left'> <tr><td height='20px'><font ");
            htmlText.append("color='#151B54' size='4px'>LeaveStatus :</font></td>");
            htmlText.append("<td width='85%' align='left' height='20px'><font color='red' ");
            htmlText.append("size='4px'>"+status+"</font></td></tr> <tr><td ");
            
            htmlText.append(" height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>ApprovedBy :</font></td><td align='left' ");
            htmlText.append("height='20px' width='30px'><font color='red' size='4px'>"+userLoginName+"</font></td></tr> <tr><td");
            
            htmlText.append(" height='20px'><br><font color='#151B54' ");
            htmlText.append("size='4px'><b>Leave Details :</b></font></td><td align='left' ");
            htmlText.append("height='20px'></td></tr><br> <tr><td");
            
            
            htmlText.append(" height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>LeaveType :</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+leaveType+"</font></td></tr> <tr><td");
            
            htmlText.append(" height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>StartDate :</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+leaveRequiredFrom+"</font></td></tr> <tr><td");
            
            
            htmlText.append(" height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>EndDate :</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+leaveRequiredTo+"</font></td></tr> <tr><td");
            
            htmlText.append(" valign='top' height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>Reason :</font></td><td align='left' ");
            htmlText.append(" height='20px'><font color='red' size='4px'>"+reason+"</font></td></tr> <tr><td");
            
            htmlText.append(" height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>AppliedBy :</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+leavePersonName+"</font>");
            
            htmlText.append("</td></tr>  </table> </td></tr> <tr><td colspan='2' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'><br>Regards,</font>");
            htmlText.append("</td></tr><tr><td colspan='2' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'>Corporate ");
            htmlText.append("Application Support Team,</font></td></tr><tr>");
            htmlText.append("<td colspan='2' height='20px'><font color='#151B54'");
            htmlText.append(" size='4px'>Miracle Software Systems, Inc.</font>");
            htmlText.append("</td></tr><tr><td width='600px' height='60px'></td></tr>");
            htmlText.append("</table></body></html>");
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("MailImage.Path"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID","<image>");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            //transport.sendMessage(message,message.getRecipients(Message.RecipientType.CC));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
        
    }
    
    /**
     * NAme :sendConsultantDetailsForRequirement
     * DESC:sendConsultantDetailsForRequirement
     * PENDING
     */
    
    
//    public static void sendConsultantDetailsForRequirement(String title,String requirementId,String consultantId,String rate,String startDate) throws ServiceLocatorException {
//        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
//        
//        try {
//            String details = DataSourceDataProvider.getInstance().getDetailsByRequirementId(requirementId);
//            
//            String createdBy = details.substring(0,details.indexOf("#"));
//            
//            String practice = details.substring(details.indexOf("#")+1,details.length());
//            
//            String createdByName = DataSourceDataProvider.getInstance().getFname_Lname(createdBy);
//            
//            String consultantName = DataSourceDataProvider.getInstance().getConsultantName(Integer.parseInt(consultantId)) ;
//            /** The to is used for storing the user mail id to send details. */
//            String to = createdBy+"@miraclesoft.com";
//            //String cc = leaveCc;
//            
//            /** The from is used for storing the from address. */
//            String from = "hubbleapp@miraclesoft.com";
//            
//            
//            /**The props is instance variabel to <code>Properties</code> class */
//            Properties props = new Properties();
//            
//            /**Here set smtp protocal to props */
//            props.setProperty("mail.transport.protocol", "smtp");
//            
//            //**Here set the address of the host to props */
//            props.setProperty("mail.host", SMTP_HOST);
//            
//            /** Here set the authentication for the host **/
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.port", SMTP_PORT);
//            
//            Authenticator auth = new SMTPAuthenticator();
//            //    Session mailSession = Session.getDefaultInstance(props, null);
//            Session mailSession = Session.getDefaultInstance(props, auth);
//           // mailSession.setDebug(true);
//             mailSession.setDebug(false);
//            List practiseManagers = DefaultDataProvider.getInstance().getPracticeManager();
//            
//            Transport transport;
//            
//            transport = mailSession.getTransport();
//            MimeMessage message = new MimeMessage(mailSession);
//            message.setSubject("New Consultant has been added for A Requirement");
//            message.setFrom(new InternetAddress(from));
//            if(practice.equals("SAP")) {
//                for(Iterator it = practiseManagers.iterator(); it.hasNext();) {
//                    message.addRecipient(Message.RecipientType.TO,new InternetAddress(it.next().toString()+"@miraclesoft.com"));
//                }
//            }
//            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//            //message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
//            //message.addRecipient(Message.RecipientType.BCC,new InternetAddress("rdadi@miraclesoft.com"));
//            
//            // -- We could include CC recipients too --
//            //if (cc != null)
//            //  message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc, false));
//            
//            
//            // This HTML mail have to 2 part, the BODY and the embedded image
//            //
//            MimeMultipart multipart = new MimeMultipart("related");
//            
//            // first part  (the html)
//            BodyPart messageBodyPart = new MimeBodyPart();
//            StringBuilder htmlText = new StringBuilder();
//            
//            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
//            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
//            htmlText.append("<p>Hello Team </p>");
//            htmlText.append("<p>A New Consultant has been added for "+title+" Requirement</p>");
//            htmlText.append("<p><u><b>Consultant Details:</b></u><br>");
//            htmlText.append("Consultant Name: "+consultantName+" <br>");
//            htmlText.append("Target Rate:"+rate+" <br>");
//            htmlText.append("Date Available:"+startDate+" <br>");
//            htmlText.append("For More Details ");
//            htmlText.append("Visit the following URL :<a");
//            htmlText.append(" href='http://www.miraclesoft.com/Hubble/'>");
//            htmlText.append("http://www.miraclesoft.com/Hubble/</a></p></font>");
//            htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
//            htmlText.append("</body></html>");
//            
//            messageBodyPart.setContent(htmlText.toString(), "text/html");
//            
//            // add it
//            multipart.addBodyPart(messageBodyPart);
//            
//            // second part (the image)
//            messageBodyPart = new MimeBodyPart();
//            DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("MailImage.Path"));
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setHeader("Content-ID","<image>");
//            
//            // add it
//            multipart.addBodyPart(messageBodyPart);
//            
//            // put everything together
//            message.setContent(multipart);
//            
//            transport.connect();
//            
//            transport.sendMessage(message,
//                    message.getRecipients(Message.RecipientType.TO));
//            //transport.sendMessage(message,
//            //  message.getRecipients(Message.RecipientType.BCC));
//            transport.close();
//        } catch (NoSuchProviderException ex) {
//            ex.printStackTrace();
//        }  catch (MessagingException ex) {
//            ex.printStackTrace();
//        }catch(NullPointerException nex){
//            nex.printStackTrace();
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//    }
    public static void sendConsultantDetailsForRequirement(String title, String requirementId, String consultantId, String rate, String startDate, String name, String email, String cellNo, String objectId, String maxRecId) throws ServiceLocatorException {
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        //System.out.println("sendConsultantDetailsForRequirement");
        try {
            String details = DataSourceDataProvider.getInstance().getDetailsByRequirementId(requirementId);
           // System.out.println("details---" + details);
            String url = com.mss.mirage.util.Properties.getProperty("PROD.URL");
            String Managers = com.mss.mirage.util.Properties.getProperty("RecruitManager");
            String mgrs[] = Managers.split(Pattern.quote(","));
            HashSet CC2 = new HashSet();
            for (int i = 0; i < mgrs.length; i++) {
                CC2.add(mgrs[i]);
            }
            String allDetails[] = details.replaceAll("null", "").split("#");
          //  System.out.println("allDetails.length--->" + allDetails.length);
//            for (int i = 0; i < allDetails.length; i++) {
//                System.out.println("allDetails[" + i + "]-->" + allDetails[i]);
//            }

//            String loginId1=DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(requirementId);
//                        String reportsToTopLevel=DataSourceDataProvider.getInstance().getReportsTo(loginId1);

            //String createdBy = details.substring(0,details.indexOf("#"));
            String createdBy = allDetails[0];

            //String practice = details.substring(details.indexOf("#")+1,details.length());
            String practice = allDetails[1];

            //String assignedByEmail  = DataSourceDataProvider.getInstance().getRequirementAssignedByEmail(requirementId);
            // String recruiter1Email  = DataSourceDataProvider.getInstance().getRequirementRecruiter1Email(requirementId);
            String assignedByEmail = allDetails[2] + "@miraclesoft.com";
            String recruiter1 = allDetails[3];
            String recruiter2 = allDetails[4].replaceAll("_", "");
            HashSet<String> reportsToTopLevel = new HashSet<String>();
            if (!recruiter1.equals("") && recruiter1 != null) {
                String recruiter1loginId = DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(recruiter1);
                HashSet reportsToTopLevel1 = DataSourceDataProvider.getInstance().getTopLevelReportsLoginIds(recruiter1loginId);
                reportsToTopLevel.add(recruiter1loginId);
                CC2.addAll(reportsToTopLevel1);
            }
            if (!recruiter2.equals("") && recruiter2 != null) {
                String recruiter2loginId = DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(recruiter2);
                HashSet reportsToTopLevel2 = DataSourceDataProvider.getInstance().getTopLevelReportsLoginIds(recruiter2loginId);
                reportsToTopLevel.add(recruiter2loginId);
                CC2.addAll(reportsToTopLevel2);
            }
            String createdByName = DataSourceDataProvider.getInstance().getFname_Lname(createdBy);

            String consultantName = DataSourceDataProvider.getInstance().getConsultantName(Integer.parseInt(consultantId));
            /** The to is used for storing the user mail id to send details. */
            // String to = createdBy+"@miraclesoft.com";
            String to = "";
            CC2.add(createdBy);
            //String cc = leaveCc;

            /** The from is used for storing the from address. */
            String from = "hubbleapp@miraclesoft.com";




            Authenticator auth = new SMTPAuthenticator();
            List practiseManagers = DefaultDataProvider.getInstance().getPracticeManager();


            String subject = "Resume submitted against "+title+" for TL/Manager evaluation";
//            if (assignedByEmail != null && !"".equals(assignedByEmail)) {
//                System.out.println("assignedByEmail---" + assignedByEmail);
//            }
            Iterator<String> itr = reportsToTopLevel.iterator();
            String tomail = "";
           // System.out.println("while loop");
            while (itr.hasNext()) {
                tomail += itr.next().toString() + "@miraclesoft.com,";


            }
             tomail = tomail.substring(0, tomail.length() - 1);
           // System.out.println("tomail---" + tomail);
            if (practice.equals("SAP")) {
                for (Iterator it = practiseManagers.iterator(); it.hasNext();) {
                    //  message.addRecipient(Message.RecipientType.TO,new InternetAddress(it.next().toString()+"@miraclesoft.com"));
                    // tomail+=it.next().toString()+"@miraclesoft.com";
                    CC2.add(it.next().toString());
                }
            }

            itr = CC2.iterator();
            String cc = "";
            while (itr.hasNext()) {
                cc += itr.next().toString() + "@miraclesoft.com,";


            }
             cc = cc.substring(0, cc.length() - 1);
            //System.out.println("cc---" + cc);
            StringBuilder htmlText = new StringBuilder();
         

         /*   htmlText.append("<html><head><title>Mail From Hubble Portal</title></head>");
            htmlText.append("<body><table align='center'><tr style='background:#07BBD7;height:40px;'><td><font color='white' size='4' face='Arial'>");
            htmlText.append("<p>"+subject+"</p></font></td></tr><tr><td><table style='background:#CCDBDE;width:100%;'><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<p>Hello Team,</p></font> </td></tr><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("</font>  </td></tr><tr><td><font color='#111728' size='2' face='Arial' style='font-weight:600;'><p><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>" + consultantName + "</font> has been submitted against <font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>" + title + "</font>  requirement for Evaluation.</p></font></td></tr>");
             htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'><br>For More Details about this Requirement/candidate<a href='" + url + "crm/requirement/getConsultantRequirement.action?consultId=" + maxRecId + "&objectId=" + objectId + "&requirementAdminFlag=YES'>click here</a></font></td></tr>");
             htmlText.append("</table></font></td></tr><tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font> </td></tr></table></body></html>");
*/
            
            htmlText.append("<!DOCTYPE html>");
htmlText.append("<html>");

htmlText.append("<head>");
htmlText.append("<meta charset='utf-8'>");
htmlText.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
htmlText.append("<meta http-equiv='X-UA-Compatible' content='IE=edge' />");
htmlText.append("<style type='text/css'>");
     
      
      htmlText.append("body,table,td,a {-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;}");
      
      
      htmlText.append("table,td {mso-table-lspace: 0pt;mso-table-rspace: 0pt;}");
      
      
      htmlText.append("img {-ms-interpolation-mode: bicubic;}");
      
      
      htmlText.append("img {border: 0;height: auto;line-height: 100%;outline: none;text-decoration: none;}");
      
      htmlText.append("table {border-collapse: collapse !important;}");
      
      htmlText.append("body {height: 100% !important;margin: 0 !important;padding: 0 !important;width: 100% !important;}");
      
      
      htmlText.append("a[x-apple-data-detectors] {color: inherit !important;text-decoration: none !important;font-size: inherit !important;font-family: inherit !important;font-weight: inherit !important;line-height: inherit !important;}");
    
      
      htmlText.append("@media screen and (max-width: 525px) {.wrapper {width: 100% !important;max-width: 100% !important;}");
         
         htmlText.append(".logo img {margin: 0 auto !important;}");
        
         htmlText.append(".mobile-hide {display: none !important;}");
         htmlText.append(".img-max {max-width: 100% !important;width: 100% !important;height: auto !important;}");
         
         htmlText.append(".responsive-table {width: 100% !important;}");
         
         htmlText.append(".padding {padding: 10px 5% 15px 5% !important;}");
         htmlText.append(".padding-meta {padding: 30px 5% 0px 5% !important;text-align: center;}");
         htmlText.append(".padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}");
         htmlText.append(".no-padding {padding: 0 !important;}");
         htmlText.append(".section-padding {padding: 50px 15px 50px 15px !important;}");
         
         htmlText.append(".mobile-button-container {margin: 0 auto;width: 100% !important;}");
         htmlText.append(".mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important; display: block !important;}}");
      
      
      htmlText.append("div[style*='margin: 16px 0;'] {margin: 0 !important;}");
   htmlText.append("</style>");
htmlText.append("</head>");
htmlText.append("<body style='margin: 0 !important; padding: 0 !important;'>");
   htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
      htmlText.append("<tr>");
         htmlText.append("<td bgcolor='#ffffff' align='center'>");
            
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper'>");
               htmlText.append("<tr>");
                  htmlText.append("<td align='center' valign='top' style='padding: 15px 0;' class='logo'><a href='http://www.miraclesoft.com/' target='_blank'><img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'></a></td>");
               htmlText.append("</tr>");
            htmlText.append("</table>");
            
         htmlText.append("</td>");
      htmlText.append("</tr>");
      htmlText.append("<tr>");
         htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
            
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
               htmlText.append("<tr>");
                  htmlText.append("<td>");
                     
                     htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                        htmlText.append("<tr>");
                           htmlText.append("<td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>Resume Submitted</b></td>");
                        htmlText.append("</tr>");
                     htmlText.append("</table>");
                  htmlText.append("</td>");
               htmlText.append("</tr>");
            htmlText.append("</table>");
            
         htmlText.append("</td>");
htmlText.append("</tr>");
      htmlText.append("<tr>");
         htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");
            
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
               htmlText.append("<tr>");
                  htmlText.append("<td>");
                     htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                        htmlText.append("<tr>");
                           htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>Team,</b>");
   htmlText.append("</td>");
                        htmlText.append("</tr>");
                        htmlText.append("<tr>");
                           htmlText.append("<td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b>" + consultantName + "</b> has been submitted against <b>" + title + "</b> requirement for Evaluation.</td>");
                        htmlText.append("</tr>");
                        htmlText.append("<tr>");
                           htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b>Click <a href='" + url + "crm/requirement/getConsultantRequirement.action?consultId=" + maxRecId + "&objectId=" + objectId + "&requirementAdminFlag=YES' target='_blank' style='font-size: 14px; color: #2368a0;'>here</a> to view more details about this Requirement/Candidate</b></td>");
                        htmlText.append("</tr>");
                     htmlText.append("</table>");
                  htmlText.append("</td>");
               htmlText.append("</tr>");
               htmlText.append("<tr>");
                  htmlText.append("<td>");
                    
                     htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                        htmlText.append("<tr>");
                           htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>Thanks & Regards,<br> Corporate Application Support Team,<br> Miracle Software Systems, Inc.<br> Email: hubble@miraclesoft.com<br> Phone: (+1)248-233-1814</td>");
                        htmlText.append("</tr>");
                     htmlText.append("</table>");
                  htmlText.append("</td>");
               htmlText.append("</tr>");
               htmlText.append("<tr>");
                  htmlText.append("<td>");
                     
                     htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                        htmlText.append("<tr>");
                           htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td>");
                        htmlText.append("</tr>");
                     htmlText.append("</table>");
                  htmlText.append("</td>");
               htmlText.append("</tr>");
            htmlText.append("</table>");
            
         htmlText.append("</td>");
      htmlText.append("</tr>");
      htmlText.append("<tr>");
         htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");
           
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' class='responsive-table'>");
               htmlText.append("<tr>");
                  htmlText.append("<td width='200' align='center' style='text-align: center;'>");
                     htmlText.append("<table width='200' cellpadding='0' cellspacing='0' align='center'>");
                        htmlText.append("<tr>");
                           htmlText.append("<td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true'></a></td>");
                           htmlText.append("<td width='10'><a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true'></a></td>");
                           htmlText.append("<td width='10'><a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true'></a></td>");
                           htmlText.append("<td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true'></a></td>");
                           htmlText.append("<td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true'></a></td>");
                        htmlText.append("</tr>");
                     htmlText.append("</table>");
                  htmlText.append("</td>");
               htmlText.append("</tr>");
               htmlText.append("<tr>");
                  htmlText.append("<td height='10'>");
                  htmlText.append("</td>");
               htmlText.append("</tr>");
               htmlText.append("<tr>");
                  htmlText.append("<td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems<br></td>");
               htmlText.append("</tr>");
            htmlText.append("</table>");
            
         htmlText.append("</td>");
      htmlText.append("</tr>");
   htmlText.append("</table>");
htmlText.append("</body>");

htmlText.append("</html>");

        //    System.out.println("htmlText.toString()-->" + htmlText.toString());
            // add it

            // second part (the image)
            DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("MailImage.Path"));

            // add it

            // put everything together

            ServiceLocator.getMailServices().doAddEmailLogNew(tomail, cc, subject, htmlText.toString(), "", "","Requirement Notification");
            //transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            //transport.sendMessage(message,
            //  message.getRecipients(Message.RecipientType.BCC));

        } catch (NullPointerException nex) {
            nex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * NAme :sendRegisteredCandidateDetails
     * DESC:sendRegisteredCandidateDetails
     * UPDATED
     */
    public static void sendRegisteredCandidateDetails(String loginId, String firstName, String lastName,String workingCountry, String sendMailId) throws ServiceLocatorException {
        
        List userId = DataSourceDataProvider.getInstance().getManagerUserIdOfRecruitment();
        
        /** The from is used for storing the from address. */
        String from = "hubbleapp@miraclesoft.com";
        
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        //String host = "192.168.5.5";
        
        
        //String host = "mail.miraclesoft.com";
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        //   Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
        //mailSession.setDebug(true);
        mailSession.setDebug(false);
        Transport transport;
        try {
            
            /** The to is used for storing the user mail id to send details. */
            String to = sendMailId+"@miraclesoft.com";
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("New Person Registrered");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
           // message.addRecipient(Message.RecipientType.BCC,new InternetAddress("vkandregula@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
            htmlText.append("<p>Hello Team, </p>");
            htmlText.append("<p>New Person has been Registered in Hubble, Please find the Registration details below " +
                    "and activate the registered person.</p>");
            htmlText.append("<p><u><b>Registration Details:</b></u><br><br>");
            htmlText.append("First Name: "+firstName+" <br><br>");
            htmlText.append("Last Name: "+lastName+"<br><br>");
            htmlText.append("Employee LoginId: "+loginId+" <br><br>");
            htmlText.append("Employee Working Country: "+workingCountry+" <br><br>");
            htmlText.append("Thank you.<br><br>");
            htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
            htmlText.append("</body></html>");
            
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            /* second part (the image)*/
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("MailImage.Path"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID","<image>");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
          //  transport.sendMessage(message,
             //       message.getRecipients(Message.RecipientType.BCC));
            transport.close();
            
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    
    
    //new mail method for sending email notification when account team is changed
    /**
     *Author:prasad kandregula
     *email:vkandregula@miraclesoft.com
     *method:sendNotificationForAccountTeamChange
     * UPDATED
     **/
    public static void sendNotificationForAccountTeamChange(String modifiedBy,int id,String primaryTeamMemberIdBefore,Map mapBeforeTeamChange, Map mapAfterTeamChange,String primaryTeamMemberIdAfter) throws ServiceLocatorException {
        
        DateUtility.getInstance().getCurrentDate();
        String modifiedDate=DateUtility.getInstance().getCurrentMySqlDateTime().toString();
        String primaryTeamMemberNameBefore=DataSourceDataProvider.getInstance().getFname(primaryTeamMemberIdBefore);
        DataSourceDataProvider dataSourceDataProvider;
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
        String reportsTo=dataSourceDataProvider.getReportsTOOneLevel(primaryTeamMemberIdBefore);
        //System.err.println("Reporting person of primary team member:"+reportsTo);
        String accountName=DataSourceDataProvider.getInstance().getAccountName(id);
        Set<String> valueBeforeChange = new HashSet<String>(mapBeforeTeamChange.keySet());
        Set<String> valuesAfterUpdate = new HashSet<String>(mapAfterTeamChange.keySet());
        boolean equal = valueBeforeChange.equals(valuesAfterUpdate);
        
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        String to= primaryTeamMemberIdBefore+"@miraclesoft.com";
        //String cc= reportsTo+"@miraclesoft.com";
        String cc= "";
        // System.err.println("reports to to:"+to);
        // System.err.println("reports to cc before append:"+cc);
        
        
        /** The from is used for storing the from address. */
        //String fromAdd = httpServletrequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        String from = "hubbleapp@miraclesoft.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        // String host = "192.168.5.5";
        
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        //  Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
       // mailSession.setDebug(true);
         mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Account Team Notification");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            if(!reportsTo.equals("")) {
                cc= reportsTo+"@miraclesoft.com";
                //System.err.println("reports to after append:"+cc);
                message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
                
            }
           // message.addRecipient(Message.RecipientType.BCC,new InternetAddress("vkandregula@miraclesoft.com"));
            //message.addRecipient(Message.RecipientType.BCC,new InternetAddress("vkandregula@miraclesoft.com"));
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
            htmlText.append("<p>Hello "+primaryTeamMemberNameBefore+",</p>");
            htmlText.append("<p>Account Team details has been modified.</p>");
            //htmlText.append("<p>Account Team details has been modified</p>");
            // htmlText.append("<p>Please look into below Account Team Deatils for more information.</p>");
            // htmlText.append("<p>Accoount Name:"+accountName+"</p>");
            htmlText.append("<p><u><b>Account Team Details:</b></u><br>");
            htmlText.append("Account Name : "+accountName+"<br>");
            htmlText.append("Modified By: "+modifiedBy+"<br>");
            htmlText.append("Modified Date: "+modifiedDate+"<br>");
            
            if(!equal) {
                htmlText.append("Account Team Before :");
                Iterator iterator= mapBeforeTeamChange.entrySet().iterator();
                while(iterator.hasNext()) {
                    Entry entry =(Entry)iterator.next();
                    //System.out.println(" entries= "+entry.getKey().toString());
                    
                    int Id=DataSourceDataProvider.getInstance().getEmpIdByLoginId(entry.getKey().toString());
                    String EmpName=DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Id);
                    htmlText.append(EmpName+",");
                }
                
                htmlText.append("<br>");
                
                
                htmlText.append("Account Team After: ");
                Iterator iterator1= mapAfterTeamChange.entrySet().iterator();
                while(iterator1.hasNext()) {
                    Entry entry =(Entry)iterator1.next();
                    //System.out.println(" entries= "+entry.getKey().toString());
                    
                    int Id=DataSourceDataProvider.getInstance().getEmpIdByLoginId(entry.getKey().toString());
                    String EmpName=DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Id);
                    htmlText.append(EmpName+",");
                }
                
                htmlText.append("<br>");
            }
            //htmlText.append("Account team after: "+Fname+" "+Lname+"<br>");
            // htmlText.append("Account team after: "+mapAfterTeamChange+"<br>");
            
            htmlText.append("Primary Team Member : "+primaryTeamMemberIdBefore+"<br>");
            if(!primaryTeamMemberIdAfter.equals("") && !primaryTeamMemberIdBefore.equals(primaryTeamMemberIdAfter)) {
                htmlText.append("Primary Team Member Changed To: "+primaryTeamMemberIdAfter+"<br>");
            }
            
            htmlText.append("<br>");
            htmlText.append("Thank you.</p></font>");
            htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
            htmlText.append("</body></html>");
            
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            if(!reportsTo.equals("")) {
                
                transport.sendMessage(message,message.getRecipients(Message.RecipientType.CC));
                //transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
            }
           // transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    
    //new mail method to send mail when sales person add contact or note
    /**
     *Author:prasad kandregula
     *email:vkandregula@miraclesoft.com
     *method:sendReminderForContacts
     *   UPDATED
     **/
    public static void sendReminderForContacts(String createdby, String Fname,String Lname,int accountId,String createdDate,String title,String email) throws ServiceLocatorException {
        
        //String loginId = DataSourceDataProvider.getInstance().getEmpLoginIdById(empName);
        
        String primaryManager=DataSourceDataProvider.getInstance().getPrimaryTeamMember(accountId);
        // int primaryManagerEmpId=DataSourceDataProvider.getInstance().getEmpIdByLoginId(primaryManager);
        String primaryManagerName=DataSourceDataProvider.getInstance().getFname(primaryManager);
        
        String accountName=DataSourceDataProvider.getInstance().getAccountName(accountId);
        
        
        //String reportsTo="";
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        String to= primaryManager+"@miraclesoft.com";
        
        
        /** The from is used for storing the from address. */
        //String fromAdd = httpServletrequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        String from = "hubbleapp@miraclesoft.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        // String host = "192.168.5.5";
        
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        //     Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
       // mailSession.setDebug(true);
         mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Contacts Reminder");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //message.addRecipient(Message.RecipientType.CC,new InternetAddress("mchennu@miraclesoft.com"));
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
            htmlText.append("<p>Hello "+primaryManagerName+",</p>");
            htmlText.append("<p>New Contact has been added to the Account: "+accountName+"</p>");
            // htmlText.append("<p>Accoount Name:"+accountName+"</p>");
            htmlText.append("<p><u><b>Contact Details:</b></u><br>");
            htmlText.append("Created By: "+createdby+"<br>");
            htmlText.append("Created Date: "+createdDate+"<br>");
            htmlText.append("Contact Name: "+Fname+" "+Lname+"<br>");
            htmlText.append("Contact Title: "+title+"<br>");
            htmlText.append("Contact Email: "+email+"<br>");
            htmlText.append("<br>");
            htmlText.append("Thank you.</p></font>");
            htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
            htmlText.append("</body></html>");
            
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            //transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    //new method to send email based on green sheet status
    
    /***
     * NAME : sendReminderForGreenSheets
     * DESC: sendReminderForGreenSheets
     * UPDATED
     */
    public static void sendReminderForGreenSheets(GreenSheetAction greenSheetAction,String loginEmpName,String tagLine) throws ServiceLocatorException {
        
        String name = greenSheetAction.getFname()+" " + greenSheetAction.getLastName();
        
        String customerName = greenSheetAction.getCustomerName();
        
        String billingRate = greenSheetAction.getClientBillingRate();
        
        Double duration = greenSheetAction.getDuration();
        
        
        String primarySalesPerson =DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(greenSheetAction.getPrimarySalesPerson()));
        String primarySalesManager =DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(greenSheetAction.getPrimarySalesManager()));
        String secondarySalesPerson =DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(greenSheetAction.getSecondarySalesPerson()));
        String primaryVicePresident =DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(greenSheetAction.getPrimaryVicePresident()));
        String secondaryVicePresident =DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(greenSheetAction.getSecondaryVicePresident()));
        Double primarySalesPersonCommission =greenSheetAction.getPriSalesPersonCommission();
        Double primarySalesManagerCommission =greenSheetAction.getPriSalesMngCommission();
        Double secondarySalesPersonCommission =greenSheetAction.getSecondarySalesPersonCommission();
        Double primaryVicePresidentCommission =greenSheetAction.getPrimaryVicePresidentCommission();
        Double secondaryVicePresidentCommission =greenSheetAction.getSecondaryVicePresidentCommission();
        //newly added on 03062013
      /*  String poStartDate=greenSheetAction.getStartDate().toString();
        String poEndtDate=greenSheetAction.getEndDate().toString();
        String conStartDate=greenSheetAction.getConsStartDate().toString();
        */
        
        String poStartDate= "";
        String poEndtDate="";
        String conStartDate="";
        if(greenSheetAction.getStartDate()!=null)
        poStartDate=greenSheetAction.getStartDate().toString();
        if(greenSheetAction.getEndDate()!=null)
         poEndtDate=greenSheetAction.getEndDate().toString();
        if(greenSheetAction.getConsStartDate()!=null)
         conStartDate=greenSheetAction.getConsStartDate().toString();
        
        int greendheetId = greenSheetAction.getId();
        
        String empId =greenSheetAction.getEmpId();
        
        String empcreatedId=DataSourceDataProvider.getInstance().getGreensheetCreatedByName(greenSheetAction.getId());
        String empName= DataSourceDataProvider.getInstance().getFname_Lname(empcreatedId);
        String reportsTo=DataSourceDataProvider.getInstance().getReportsTOOneLevel(empcreatedId);
        
        String status=greenSheetAction.getStatus();
        String Comments=greenSheetAction.getRejectesReason();
        
        
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        // String to="nseerapu@miraclesoft.com";
        String to=empcreatedId+"@miraclesoft.com";
        String cc=reportsTo+"@miraclesoft.com";
        
        String ccMailIds = com.mss.mirage.util.Properties.getProperty("GreensheetUpdate.cc");
        System.out.println("cc mail ids-->"+ccMailIds);
        String ccActual="";
        StringTokenizer st=null;
        int count=0;
        if(ccMailIds!=null || !"".equals(ccMailIds))
        {
         st = new StringTokenizer(ccMailIds,",");
        count=st.countTokens();
        }
        System.out.println("count-->"+count);
       // String  cc2 = "sratnala@miraclesoft.com";
       // String cc3 = "invoicing@miracesoft.com";
        
        String bcc = com.mss.mirage.util.Properties.getProperty("Greensheet.bcc");
        //String cc2 ="nseerapu@miraclesoft.com";
        
        String from = "hubbleapp@miraclesoft.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        // String host = "192.168.5.5";
        
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        // Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
      //  mailSession.setDebug(true);
          mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("GreenSheet Reminder");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
           if(ccMailIds!=null || !"".equals(ccMailIds))
        {
            for(int i=0;i<count;i++)
            {
                ccActual=st.nextToken();
               // System.out.println("ccactual-->"+ccActual);
            message.addRecipient(Message.RecipientType.CC,new InternetAddress(ccActual));
            }
        }
            message.addRecipient(Message.RecipientType.BCC,new InternetAddress(bcc));
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            if(status.equals("Rejected")) {
                htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>Hello "+empName+",</p>");
                htmlText.append("<p>GreenSheet has been "+tagLine+" :"+loginEmpName+"<br>");
                htmlText.append("<p><u><b>Green Sheet Details:</b></u></p>");
                htmlText.append("<b>Created By : </b> "+empName+" <br>");
                htmlText.append("<b>Status : </b> "+status+"<br>");
                htmlText.append("<b>Consultant Name : </b> "+name+" <br>");
                htmlText.append("<b>Customer Name : </b>"+customerName+"<br>");
                htmlText.append("<b>Billing Rate : </b> "+billingRate+" <br>");
                htmlText.append("<b>Duration : </b> "+duration+"  Months<br>");
                // neww changes 03-07-2013
                htmlText.append("<b>POStartDate : </b> "+poStartDate+" <br>");
                htmlText.append("<b>POEndtDate : </b> "+poEndtDate+" <br>");
                htmlText.append("<b>Consultant Start Date : </b> "+conStartDate+" <br>");
                htmlText.append("<b>Comments On Rejection : </b>"+Comments);
                //htmlText.append("<b><font color='blue' size='2' face='Arial'><u>Commissions : </u></b>");
                htmlText.append(" <table border=\"0\" cellpadding=\"0\" cellspacing=\"5\">");
                htmlText.append(" <th align=\"left\"> <font color=\"blue\" size=\"3\">Commissions: </font></th>");
                htmlText.append("<tr><td width=\"70\" align=\"left\"><b><font color=\"blue\" size=\"2\"> Designation </font></b></td><td align=\"left\" width=\"80\"><font color=\"blue\" size=\"2\"><b> Name</b></font></td><td align=\"left\" width=\"80\"><font color=\"blue\" size=\"2\"><b>    Commission<b></font></td</tr>");
                htmlText.append("<tr><td><font color=\"blue\" size=\"2\"> PrimarySalesPerson:</font></td><td align=\"left\"><font color=\"blue\" size=\"2\"> "+primarySalesPerson+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+primarySalesPersonCommission+"%</font></td></tr> <br>");
                htmlText.append("<tr><td><font color=\"blue\" size=\"2\"> PrimarySalesManager:</font></td><td align=\"left\"> <font color=\"blue\" size=\"2\"> "+primarySalesManager+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+primarySalesManagerCommission+"%</font></td></tr> <br>");
                htmlText.append("<tr><td><font color=\"blue\" size=\"2\">SecondarySalesPerson:</font></td><td align=\"left\"><font color=\"blue\" size=\"2\"> "+secondarySalesPerson+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+secondarySalesPersonCommission+"%</font></td></tr> <br>");
                if(!primaryVicePresident.equals("")) {
                    htmlText.append("<tr><td><font color=\"blue\" size=\"2\">PrimaryVicePresident:</font></td><td align=\"left\"><font color=\"blue\" size=\"2\"> "+primaryVicePresident+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+primaryVicePresidentCommission+"%</font></td></tr> <br>");
                }
                if(!secondaryVicePresident.equals("")) {
                    htmlText.append("<tr><td><font color=\"blue\" size=\"2\">SecondaryVicePresident:</font></td><td align=\"left\"><font color=\"blue\" size=\"2\"> "+secondaryVicePresident+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+secondaryVicePresidentCommission+"%</font></td></tr>");
                }
                
                htmlText.append("</table>");
                htmlText.append("<br><br> Thank you.</p></font>");
                htmlText.append("<font color='black' size='2' face='Arial'>For More Details about this GreenSheet ");
                htmlText.append("Visit the following URL :</font>");
                
                htmlText.append(" <br>\n\n\n <font color=\"red\"> <a href='http://www.miraclesoft.com/Hubble/crm/greensheets/getGreenSheetByID.action?id="+greendheetId+"&teamGreensheets=true'> Click Here To View The GreenSheet Details</a> </font><br>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
                
                
            }else {
                htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>Hello "+empName+",</p>");
                htmlText.append("<p>GreenSheet has been "+tagLine+" :"+loginEmpName+"<br>");
                htmlText.append("<p><b><font color='blue' size='2' face='Arial'><u>Green Sheet Details:</u></b></p>");
                htmlText.append("<b>Created By : </b> "+empName+" <br>");
                htmlText.append("<b>Status : </b> "+status+"<br>");
                htmlText.append("<b>Consultant Name : </b> "+name+" <br>");
                htmlText.append("<b>Customer Name : </b>"+customerName+"<br>");
                htmlText.append("<b>Billing Rate : </b> "+billingRate+" <br>");
                htmlText.append("<b>Duration : </b> "+duration+"  Months<br>");
                
                htmlText.append("<b>Comments : </b>"+Comments);
                htmlText.append(" <table border=\"0\" cellpadding=\"0\" cellspacing=\"5\">");
                htmlText.append(" <th align=\"left\"> <font color=\"blue\" size=\"3\">Commissions: </font></th>");
                htmlText.append("<tr><td width=\"70\" align=\"left\"><b><font color=\"blue\" size=\"2\"> Designation </font></b></td><td align=\"left\" width=\"80\"><font color=\"blue\" size=\"2\"><b> Name</b></font></td><td align=\"left\" width=\"80\"><font color=\"blue\" size=\"2\"><b>    Commission<b></font></td</tr>");
                htmlText.append("<tr><td><font color=\"blue\" size=\"2\"> PrimarySalesPerson:</font></td><td align=\"left\"><font color=\"blue\" size=\"2\"> "+primarySalesPerson+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+primarySalesPersonCommission+"%</font></td></tr> <br>");
                htmlText.append("<tr><td><font color=\"blue\" size=\"2\"> PrimarySalesManager:</font></td><td align=\"left\"> <font color=\"blue\" size=\"2\"> "+primarySalesManager+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+primarySalesManagerCommission+"%</font></td></tr> <br>");
                htmlText.append("<tr><td><font color=\"blue\" size=\"2\">SecondarySalesPerson:</font></td><td align=\"left\"><font color=\"blue\" size=\"2\"> "+secondarySalesPerson+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+secondarySalesPersonCommission+"%</font></td></tr> <br>");
                if(!primaryVicePresident.equals("")) {
                    htmlText.append("<tr><td><font color=\"blue\" size=\"2\">PrimaryVicePresident:</font></td><td align=\"left\"><font color=\"blue\" size=\"2\"> "+primaryVicePresident+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+primaryVicePresidentCommission+"%</font></td></tr> <br>");
                }
                if(!secondaryVicePresident.equals("")) {
                    htmlText.append("<tr><td><font color=\"blue\" size=\"2\">SecondaryVicePresident:</font></td><td align=\"left\"><font color=\"blue\" size=\"2\"> "+secondaryVicePresident+"</font></td><td align=\"left\"><font color=\"blue\" size=\"2\">  "+secondaryVicePresidentCommission+"%</font></td></tr>");
                }
                
                htmlText.append("</table>");
                htmlText.append("<br><br> Thank you.</p></font>");
                htmlText.append("For More Details about this GreenSheet ");
                htmlText.append("Visit the following URL :");
                
                htmlText.append(" <br>\n\n\n <font color=\"red\"> <a href='http://www.miraclesoft.com/Hubble/crm/greensheets/getGreenSheetByID.action?id="+greendheetId+"&teamGreensheets=true'> Click Here To View The GreenSheet Details</a> </font><br>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
                
                htmlText.append("</body></html>");
            }
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            /*if(!reportsTo.equalsIgnoreCase("")){
                transport.sendMessage(message,
                        message.getRecipients(Message.RecipientType.TO));
            }else if(!loginId.equalsIgnoreCase("")){
                transport.sendMessage(message,
                        message.getRecipients(Message.RecipientType.TO));
            }*/
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.CC));
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
            
            
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    
    //new mail method to send issue reminder
    /**
     *Author:prasad kandregula
     *06122013
     *email:vkandregula@miraclesoft.com
     *method:sendReminderForIssue
     *
     **/
    public static String sendReminderForIssue(String userId,String custName,String priEmail,String secEmail,String createdBy,String issueTitle,String dateAssigned,String dateClosed, String percentageComplted,String comments) throws ServiceLocatorException {
        //String reportsTo="";
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        //String to= primaryManager+"@miraclesoft.com";
        String errormsg="success";
        String to1="";
        String to2="";
        to1= priEmail+"@miraclesoft.com";
        if(!secEmail.equals("")) {
            to2= secEmail+"@miraclesoft.com";
        }
        /** The from is used for storing the from address. */
        //String fromAdd = httpServletrequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        String from = userId+"@miraclesoft.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        // String host = "192.168.5.5";
        
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        //     Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
      //  mailSession.setDebug(true);
          mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            if(!issueTitle.equals("")) {
                message.setSubject(issueTitle+"IssueReminder");
            } else {
                message.setSubject("IssueReminder");
            }
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to1));
            if(!to2.equals("")){
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(to2));
            }
            //message.addRecipient(Message.RecipientType.CC,new InternetAddress("mchennu@miraclesoft.com"));
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
            htmlText.append("<p>Hello ,</p>");
            
            htmlText.append("<p><u><b>Issue Details:</b></u><br>");
            htmlText.append("Customer Name: "+custName+"<br>");
            htmlText.append("Issue Tiltle: "+issueTitle+"<br>");
            htmlText.append("CreatedBy: "+createdBy+"<br>");
            if(!dateAssigned.equals("")) {
                htmlText.append("DateAssigned: "+dateAssigned+"<br>");
            }
            if(!dateClosed.equals("")) {
                htmlText.append("DueDate: "+dateClosed+"<br>");
            }
            if(!percentageComplted.equals("")) {
                htmlText.append("Percentage Completed: "+percentageComplted+"<br>");
            }
            if(!comments.equals("")) {
                htmlText.append("Comments: "+comments+"<br>");
            }
            htmlText.append("<br>");
            htmlText.append("Thank you.</p></font>");
            htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
            htmlText.append("</body></html>");
            
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            //transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
            transport.close();
        } catch (NoSuchProviderException ex) {
            
            errormsg="failure";
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            errormsg="failure";
            ex.printStackTrace();
        }
        return errormsg;
    }
    /**
     *Desc : for sending task detaisl
     * Author : ajay Tummapala
     *
     *
     *
     */
    public static void sendTaskEmail(String loginId, String secAssignTo, String createdBy, String description,String severity,String userName,String workPhno,String teamName,String custometName,String title,int issueId,String resourceType) {
        
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        String primaryTo="";
        String primaryTo1="";
        String secondaryTo="";
        String secondaryTo1="";
        String primary=loginId;
       StringTokenizer st = new StringTokenizer(primary, "^");
             
            primaryTo = st.nextToken();
            primaryTo1= st.nextToken();
            
        //System.out.println("loginId in mail-->"+primary);
        //System.out.println("loginId1 in mail-->"+primaryTo);
        //System.out.println("loginId2 in mail-->"+primaryTo1);
        
        String secondary=secAssignTo;
          StringTokenizer st1 = new StringTokenizer(secondary, "^");
          secondaryTo = st1.nextToken();
            secondaryTo1= st1.nextToken();
            //System.out.println("secAssignTo1 in mail-->"+secondaryTo);
        //System.out.println("secAssignTo2 in mail-->"+secondaryTo1);
        //System.out.println("secAssignTo in mail-->"+secondary);
      //  String to = loginId;
        
        /** The from is used for storing the from address. */
        String from = "hubbleapp@miraclesoft.com";
        
       // System.out.println("createdBy-->"+createdBy);
       // System.out.println("workPhno-->"+workPhno);
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        
        //  String host = "mail.miraclesoft.com";
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        // Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
        //mailSession.setDebug(true);
        mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("New Task : "+title);
            message.setFrom(new InternetAddress(from));
           // message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            
            if(primaryTo!=null&& !"".equals(primaryTo))
            {
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(primaryTo));
            }
              if(!"-".equals(primaryTo1) && primaryTo1!=null)
            {
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(primaryTo1));
            }
            
            if(secondaryTo!=null && !"".equals(secondaryTo)){
               // message.addRecipient(Message.RecipientType.TO,new InternetAddress(secAssignTo+"@miraclesoft.com"));
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(secondaryTo));
            }
              if(!"-".equals(secondaryTo1) && secondaryTo1!=null)
            {
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(secondaryTo1));
            }
            
            //message.addRecipient(Message.RecipientType.CC,new InternetAddress("mchennu@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><table align='center' width='635px' style='padding-left=15px'");
            htmlText.append(" background='cid:image' border='0' cellpadding='' ");
            htmlText.append("cellspacing='' height='800px'><tr><td width='580px'");
            htmlText.append(" height='150px'></td></tr><tr><td height='20px' colspan='2'>");
            htmlText.append("<font color='#151B54' size='4px'>Hello,</font></td></tr> ");
            
            
            
           // if(!custometName.equals("")){
            if((custometName!=null) && !"".equals(custometName)){
                htmlText.append("<tr><td height='20px' width='30%'><font ");
                htmlText.append("color='#151B54' size='4px'>Customer Name :</font></td>");
                htmlText.append("<td align='left' height='20px'><font color='red' ");
                htmlText.append("size='4px'>"+custometName+"</font></td></tr>");
            }
           // if(!title.equals("")){
            if((title!=null) && !"".equals(title)){
                htmlText.append(" <tr><td  height='20px'><font color='#151B54' ");
                htmlText.append("size='4px'>Title :</font></td><td align='left' ");
                htmlText.append("height='20px'><font color='red' size='4px'>"+title+"</font></td></tr>");
            }
            
            //if(!severity.equals("")){
             if((severity!=null) && !"".equals(severity)){
                htmlText.append(" <tr><td  height='20px'><font color='#151B54' ");
                htmlText.append("size='4px'>Issue Severity :</font></td><td align='left' ");
                htmlText.append("height='20px'><font color='red' size='4px'>"+severity+"</font></td></tr>");
            }
            
            //if(!description.equals("")){
             if((description!=null) && !"".equals(description)){
                htmlText.append(" <tr><td height='20px'><font color='#151B54' ");
                htmlText.append("size='4px'>Description :</font></td><td align='left' ");
                htmlText.append("height='20px'><font color='red' size='4px'>"+description+"</font></td></tr>");
            }
            
            
            /*-- end of add condition  ---*/
            
            
            htmlText.append(" <tr><td height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>Created By :</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+userName+"</font></td></tr> <tr><td");
            
          //  System.out.println("createdBy-->"+createdBy);
        //System.out.println("workPhno-->"+workPhno);
            if(resourceType.equalsIgnoreCase("e"))
            {
            if((createdBy!=null) && !"".equals(createdBy)){
            htmlText.append(" height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>Email:</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+createdBy+"@miraclesoft.com"+"</font></td></tr> <tr><td");
            }
            }
           
                
            if((workPhno!=null) && !"".equals(workPhno)){
            htmlText.append(" height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>Work Phno :</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+workPhno+"</font></td></tr> <tr><td");
            }
            htmlText.append(" height='20px'><font color='#151B54' ");
            htmlText.append("size='4px'>Team :</font></td><td align='left' ");
            htmlText.append("height='20px'><font color='red' size='4px'>"+teamName+"</font></td></tr> ");
            
            htmlText.append("<tr><td colspan='2' height='20px'><a href='http://www.miraclesoft.com/Hubble/employee/tasks/getTask.action?TaskId="+issueId+"&resM='>");
            htmlText.append("Click Here To view Task Details</a></font></td></tr> ");
            
            htmlText.append("   <tr><td colspan='2' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'><br>Regards,</font>");
            htmlText.append("</td></tr><tr><td colspan='2' height='20px'>");
            htmlText.append("<font color='#151B54' size='4px'>Corporate ");
            htmlText.append("Application Support Team,</font></td></tr><tr>");
            htmlText.append("<td colspan='2' height='20px'><font color='#151B54'");
            htmlText.append(" size='4px'>Miracle Software Systems, Inc.</font>");
            htmlText.append("</td></tr><tr><td width='600px' height='60px'></td></tr>");
            htmlText.append("</table></body></html>");
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            //System.out.println(htmlText.toString());
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("MailImage.Path"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID","<image>");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
public static String sendReminderForTask(String userId,String custName,String priEmail,String secEmail,String createdBy,String issueTitle,String dateAssigned,String dateClosed, String percentageComplted,String comments,String resourceType) throws ServiceLocatorException {
        //String reportsTo="";
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        //String to= primaryManager+"@miraclesoft.com";
        String errormsg="success";
        String to1="";
        String to2="";
        String primaryTo = "";
        String primaryTo1 = "";
         String secondaryTo = "";
        String secondaryTo1 = "";
        
       // System.out.println("priEmail---->"+priEmail);
       // System.out.println("secEmail---->"+secEmail);
        StringTokenizer st = new StringTokenizer(priEmail, "^");
             
            primaryTo = st.nextToken();
            primaryTo1= st.nextToken();
         //   System.out.println("primaryTo---->"+primaryTo);
          //  System.out.println("primaryTo1---->"+primaryTo1);
       // to1= priEmail+"@miraclesoft.com";
       //  to1= priEmail;
        if(!secEmail.equals("")) {
           // to2= secEmail+"@miraclesoft.com";
         StringTokenizer st1 = new StringTokenizer(secEmail, "^");
             
            secondaryTo = st1.nextToken();
            secondaryTo1= st1.nextToken();
            //System.out.println("secondaryTo---->"+secondaryTo);
            //System.out.println("secondaryTo1---->"+secondaryTo1);
            //    to2= secEmail;
        }
        /** The from is used for storing the from address. */
        //String fromAdd = httpServletrequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
       String from ="";
       String from1 ="";
       String from2 ="";
        if(resourceType.equalsIgnoreCase("e"))
       {
        from= userId+"@miraclesoft.com^-";
       }
        else if(resourceType.equalsIgnoreCase("c")||resourceType.equalsIgnoreCase("v"))
        {
        from = DataSourceDataProvider.getInstance().getEmailIdByPrimaryAssignId(userId, resourceType);
        }
          StringTokenizer st2 = new StringTokenizer(from, "^");
             
            from1 = st2.nextToken();
            from2= st2.nextToken();
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        // String host = "192.168.5.5";
        
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        //     Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
       // mailSession.setDebug(true);
         mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            if(!issueTitle.equals("")) {
                message.setSubject(issueTitle+"TaskReminder");
            } else {
                message.setSubject("TaskReminder");
            }
            
             if(from1!=null&& !"".equals(from1))
            {
            message.setFrom(new InternetAddress(from1));
            }
              if(!"-".equals(from2) && from2!=null)
            {
             message.setFrom(new InternetAddress(from2));
            }
             
             if(primaryTo!=null&& !"".equals(primaryTo))
            {
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(primaryTo));
            }
              if(!"-".equals(primaryTo1) && primaryTo1!=null)
            {
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(primaryTo1));
            }
            
            if(secondaryTo!=null && !"".equals(secondaryTo)){
               // message.addRecipient(Message.RecipientType.TO,new InternetAddress(secAssignTo+"@miraclesoft.com"));
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(secondaryTo));
            }
              if(!"-".equals(secondaryTo1) && secondaryTo1!=null)
            {
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(secondaryTo1));
            }
         /*   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to1));
            if(!to2.equals("")){
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(to2));
            }*/
            //message.addRecipient(Message.RecipientType.CC,new InternetAddress("mchennu@miraclesoft.com"));
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
            htmlText.append("<p>Hello ,</p>");
            
            htmlText.append("<p><u><b>Task Details:</b></u><br>");
            htmlText.append("Customer Name: "+custName+"<br>");
            htmlText.append("Task Title: "+issueTitle+"<br>");
            htmlText.append("CreatedBy: "+createdBy+"<br>");
            if(!dateAssigned.equals("")) {
                htmlText.append("Start Date: "+dateAssigned+"<br>");
            }
            if(!dateClosed.equals("")) {
                htmlText.append("Due Date: "+dateClosed+"<br>");
            }
            if(!percentageComplted.equals("")) {
                htmlText.append("Percentage Completed: "+percentageComplted+"<br>");
            }
            if(!comments.equals("")) {
                htmlText.append("Comments: "+comments+"<br>");
            }
            htmlText.append("<br>");
            htmlText.append("Thank you.</p></font>");
            htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
            htmlText.append("</body></html>");
            
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            //transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
            transport.close();
        } catch (NoSuchProviderException ex) {
            
            errormsg="failure";
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            errormsg="failure";
            ex.printStackTrace();
        }
        return errormsg;
    }
    


    /**
     * Author : Prasad Kandregula
     * New Method for sendResumeDetailsToCustomer
     * Date : 07172013
     */
    
    public static String sendResumeDetailsToCustomer(int id,String resumeRecId,String resumeConsultantId,String resumeAttachmentId,String custEmail,String cc,String bcc,String subject,String mailMessage,String userId) throws ServiceLocatorException {
        //String reportsTo="";
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        //String to= primaryManager+"@miraclesoft.com";
        List consultantDetails = new ArrayList();//Description
        int resAttachmentId=Integer.parseInt(resumeAttachmentId);
        String consultantName="";
        String consultantEmail="";
        String consultantPhoneNum="";
        
        /*String consultantName=DataSourceDataProvider.getInstance().getConsultantName(Integer.parseInt(resumeConsultantId));
        String consultantEmail=DataSourceDataProvider.getInstance().getConsultantEmail(resumeConsultantId.toString());
        String consultantPhoneNum=DataSourceDataProvider.getInstance().getConsultantPhoneNumber(resumeConsultantId.toString());*/
        consultantDetails=DataSourceDataProvider.getInstance().getResumeSubmissionConsultantDetails(Integer.parseInt(resumeConsultantId));
        for (int i = 0; i < consultantDetails.size(); i++) {
            consultantName=consultantDetails.get(0).toString();
            consultantEmail=consultantDetails.get(1).toString();
            consultantPhoneNum=consultantDetails.get(2).toString();
        }
        
        String errormsg="success";
        String to=custEmail;
        
        
        /** The from is used for storing the from address. */
        //String fromAdd = httpServletrequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        String from = userId+"@miraclesoft.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        // String host = "192.168.5.5";
        
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        //     Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
       // mailSession.setDebug(true);
         mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(subject);
            
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            if(cc!=null && !"".equals(cc)) {
                String [] stringParts = cc.split(";");
                
                for(int i=0;i<=stringParts.length-1;i++) {
                    message.addRecipient(Message.RecipientType.CC,new InternetAddress(stringParts[i]));
                }
            }
            if(bcc!=null && !"".equals(bcc)) {
                message.addRecipient(Message.RecipientType.BCC,new InternetAddress(bcc));
            }
            // This HTML mail have to 2 part, the BODY and the embedded image
            //String HubbleUrl=com.mss.mirage.util.Properties.getProperty("HubbleUrl");
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><table align='center' width='630px'");
            htmlText.append(" background='cid:image' border='0' cellpadding=''");
            htmlText.append("  cellspacing='' height='415px'><tr>");
            htmlText.append(" <td width='600px' height='120px'></td></tr>");
            htmlText.append("<tr><td height='10px' colspan='2'>");
            htmlText.append("  <font color='#151B54' size='4px'>"+mailMessage+"");
            htmlText.append("</font>");
            htmlText.append(" </td></tr><tr><td width='20%' height='5px'><font color='#151B54' size='4px' align='center'><u>Consultant&nbsp;Details&nbsp;:</u></font></td></tr><tr>");
            htmlText.append(" <td width='20%' height='5px' align='center'><font color='#151B54'");
            htmlText.append("  size='4px'>Name :</font></td><td width='550px' ");
            htmlText.append("  height='5px'><font color='red' size='4px'>"+consultantName+"");
            htmlText.append("  </font></td></tr><tr><td width='20%' height='5px' align='center'>");
            htmlText.append("  <font color='#151B54' size='4px'>Email :</font>");
            htmlText.append(" </td><td width='550px' height='5px'>");
            htmlText.append(" <font color='red' size='4px'>"+consultantEmail+"</font>");
            htmlText.append(" </td></tr>");
            htmlText.append("<tr><td width='20%' height='5px' align='center'>");
            htmlText.append(" <font color='#151B54' size='4px' >Phone :</font>");
            htmlText.append(" </td><td width='550px' height='5px' align='center'>");
            htmlText.append(" <font color='red' size='4px'>"+consultantPhoneNum+"</font>");
            htmlText.append(" </td></tr>");
            htmlText.append("<tr><td width='20%' height='5px' align='center'>");
            htmlText.append(" <font color='#151B54' size='4px'>Resume Link :</font>");
            htmlText.append(" </td><td width='550px' height='5px'>");
            htmlText.append(" <font color='red' size='4px'><a href='http://www.miraclesoft.com/Hubble/recruitment/getAttachmentForResume.action?id="+resAttachmentId+"&resumeSubmissionId="+id+"'>  DownLoad Resume</a> </font>");
            htmlText.append(" </td></tr><tr><td width='600px' height='10px'></td></tr></table></body></html>");
            
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            
            // messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("ConsultantResumetMailImage.Path"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID","<image>");
            
            // add it
            //  multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            // message.setContent(multipart);
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            if(cc!=null && !"".equals(cc)) {
                transport.sendMessage(message,message.getRecipients(Message.RecipientType.CC));
            }
            if(bcc!=null && !"".equals(bcc)) {
                transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
            }
            transport.close();
        } catch (NoSuchProviderException ex) {
            
            errormsg="failure";
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            errormsg="failure";
            ex.printStackTrace();
        }
        return errormsg;
    }
    
    //For tech REviews
    
    
//     public static void sendConsultantDetailsForTechReviews1(int id,int conId,String recruiterEmail,String recruiterName,String consultantName,String forwardedToName,String operationType,String forwardTo1,String referredEmail,String comments, String rating) throws ServiceLocatorException {
//       
//        String errormsg="success";
//        String to1="";
//        String from = "hubbleapp@miraclesoft.com";
//        Properties props = new Properties();
//        props.setProperty("mail.transport.protocol", "smtp");
//        props.setProperty("mail.host", SMTP_HOST);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", SMTP_PORT);
//        
//        Authenticator auth = new SMTPAuthenticator();
//       
//        Session mailSession = Session.getDefaultInstance(props, auth);
//       
//         mailSession.setDebug(false);
//        Transport transport;
//        try {
//            String forwardToNameFinal="";
//             String referredTechieName="";
//            //System.out.println("in mail manager-->"+referredEmail);
//            // if(referredEmail.toLowerCase().contains("j2ee")||referredEmail.toLowerCase().contains("b2b")||referredEmail.toLowerCase().contains("wps")||referredEmail.toLowerCase().contains("wcs"))
//              if(referredEmail.toLowerCase().contains("eai"))
//               {
//               referredTechieName = referredEmail.split("@")[0]+" Group";
//               }
//             else
//             {
//          referredTechieName =DataSourceDataProvider.getInstance().getFname_Lname(referredEmail.split("@")[0]);
//             }
//             // System.out.println("in mail manager referredTechieName-->"+referredTechieName);
//             //  if(forwardedToName.toLowerCase().contains("j2ee")||forwardedToName.toLowerCase().contains("b2b")||forwardedToName.toLowerCase().contains("wps")||forwardedToName.toLowerCase().contains("wcs"))
//               if(forwardedToName.toLowerCase().contains("eai"))
//               {
//               forwardToNameFinal = DataSourceDataProvider.getInstance().getFname_Lname(forwardTo1.split("@")[0]);
//               }
//               else
//               {
//               forwardToNameFinal = forwardedToName;
//               }
//               //System.out.println("forwardToNameFinal--->"+forwardToNameFinal);
//            transport = mailSession.getTransport();
//            MimeMessage message = new MimeMessage(mailSession);
//             
//                message.setSubject("Consultant Technical Review");
//            
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO,new InternetAddress(recruiterEmail));
//            MimeMultipart multipart = new MimeMultipart("related");
//            BodyPart messageBodyPart = new MimeBodyPart();
//            StringBuilder htmlText = new StringBuilder();          
//            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
//            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
//            htmlText.append("<p>Hello "+recruiterName+" ,</p>");
//            htmlText.append("<p>Greetings from Miracle Software Systems, Inc.</p>");
//           if(operationType.equalsIgnoreCase("Review & Forward"))
//           {
//            //if(forwardedToName.toLowerCase().contains("j2ee")||forwardedToName.toLowerCase().contains("b2b")||forwardedToName.toLowerCase().contains("wps")||forwardedToName.toLowerCase().contains("wcs"))
//               if(forwardedToName.toLowerCase().contains("eai"))
//            {
//            htmlText.append("<p><p>The technical review from the consultant <b>"+consultantName+"</b> has been rated and forwarded, below are the details..<br></p> ");
//            htmlText.append("<p> Forwarded By : <b>"+forwardToNameFinal+"</b><br> </p>");
//            htmlText.append("<p> Group Name: <b>"+forwardedToName+"</b> <br></p>");
//            htmlText.append("<p> Ratings Given : <b>"+rating+"</b><br> </p>");
//            htmlText.append("<p> Forwarded To: <b>"+referredTechieName+"</b><br> </p>");
//            htmlText.append("<p> Comments: <b>"+comments+"</b><br> </p>");
//           }
//            else
//            {
//            htmlText.append("<p><p>The technical review from the consultant <b>"+consultantName+"</b> has been rated and forwarded, below are the details..<br></p> ");
//            htmlText.append("<p> Forwarded By : <b>"+forwardToNameFinal+"</b><br> </p>");
//           // htmlText.append("<p> Group Name: <b>"+forwardedToName+"</b> </p>");
//            htmlText.append("<p> Ratings Given : <b>"+rating+"</b> <br></p>");
//            htmlText.append("<p> Forwarded To: <b>"+referredTechieName+"</b> <br></p>");
//            htmlText.append("<p> Comments: <b>"+comments+"</b><br> </p>");
//            }
//           }
//           else
//           {
//              // if(forwardedToName.toLowerCase().contains("j2ee")||forwardedToName.toLowerCase().contains("b2b")||forwardedToName.toLowerCase().contains("wps")||forwardedToName.toLowerCase().contains("wcs"))
//               if(forwardedToName.toLowerCase().contains("eai"))
//            {
//          // htmlText.append("<p>The technical review from the consultant <b>"+consultantName+"</b> has been rated by <b>"+forwardToNameFinal+"</b> from <b>"+forwardedToName+"</b> <br>");
//                htmlText.append("<p><p>The technical review from the consultant <b>"+consultantName+"</b> has been rated, below are the details..<br></p> ");
//            htmlText.append("<p> Rated By : <b>"+forwardToNameFinal+"</b> <br></p>");
//            htmlText.append("<p> Group Name: <b>"+forwardedToName+"</b> <br></p>");
//            htmlText.append("<p> Ratings Given : <b>"+rating+"</b><br> </p>");
//           // htmlText.append("<p> Forwarded To: <b>"+referredTechieName+"</b><br> </p>");
//            htmlText.append("<p> Comments: <b>"+comments+"</b><br> </p>");
//           }
//             else
//            {   
//              //   htmlText.append("<p>The technical review from the consultant <b>"+consultantName+"</b> has been rated by <b>"+forwardToNameFinal+"</b> <br>");
//                  htmlText.append("<p><p>The technical review from the consultant <b>"+consultantName+"</b> has been rated, below are the details..<br></p> ");
//            htmlText.append("<p> Rated By : <b>"+forwardToNameFinal+"</b><br> </p>");
//          //  htmlText.append("<p> Group Name: <b>"+forwardedToName+"</b> </p>");
//            htmlText.append("<p> Ratings Given : <b>"+rating+"</b><br> </p>");
//           // htmlText.append("<p> Forwarded To: <b>"+referredTechieName+"</b> <br></p>");
//            htmlText.append("<p> Comments: <b>"+comments+"</b><br> </p>");
//           }
//           }
//            //htmlText.append("<p><u><b>http://www.miraclesoft.com/Hubble/recruitment/consultant/reviewTechnical.action?name=jheDudcD&lee=djhgd343D&id="+Id+"&consultantId="+consultantId+"</b></u><br>");
//            // htmlText.append("<p><u><b><a href='http://w3.miraclesoft.com/Hubble/recruitment/consultant/reviewTechnical.action?name=jheDudcD&lee=djhgd343D&id="+Id+"&consultantId="+consultantId+"' >Click to View the details</a></b></u><br>");
//            
//            
//          //  htmlText.append("Customer Name: "+custName+"<br>");
//           
//          /*  if(!dateAssigned.equals("")) {
//                htmlText.append("Start Date: "+dateAssigned+"<br>");
//            }
//            if(!dateClosed.equals("")) {
//                htmlText.append("Due Date: "+dateClosed+"<br>");
//            }
//            if(!percentageComplted.equals("")) {
//                htmlText.append("Percentage Completed: "+percentageComplted+"<br>");
//            }
//            if(!comments.equals("")) {
//                htmlText.append("Comments: "+comments+"<br>");
//            }*/
//            htmlText.append("<br>");
//            htmlText.append("Thank you.</p></font>");
//            htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
//            htmlText.append("</body></html>");
//            
//            
//            messageBodyPart.setContent(htmlText.toString(), "text/html");
//            
//            // add it
//            multipart.addBodyPart(messageBodyPart);
//            
//            // put everything together
//            message.setContent(multipart);
//            
//            transport.connect();
//            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
//            //transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));
//            transport.close();
//             
//        } catch (NoSuchProviderException ex) {
//            
//            errormsg="failure";
//            ex.printStackTrace();
//        }  catch (MessagingException ex) {
//            errormsg="failure";
//            ex.printStackTrace();
//        }
//        catch (Exception ex) {
//            
//            ex.printStackTrace();
//        }
//       // return errormsg;
//    }
     public static void sendConsultantDetailsForTechReviews(int Id, int consultantId, int presales1, int presales2, String techName, String status, int requirementId, int recConsultantId, String tittle, String sedDate, String secTime, String secTimeFormate, String zone) throws ServiceLocatorException {
        //String reportsTo="";
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /**
         * The to is used for storing the user mail id to send details.
         */
        //String to= primaryManager+"@miraclesoft.com";
        String email = "";
        String email1 = "";
        HashSet reportsToTopLevel1 = null;
        String recruiter1loginId = "";
        String url = com.mss.mirage.util.Properties.getProperty("PROD.URL");
        String Managers = com.mss.mirage.util.Properties.getProperty("RecruitManager");
            String mgrs[] = Managers.split(Pattern.quote(","));
            HashSet CC2 = new HashSet();
            for (int i = 0; i < mgrs.length; i++) {
                CC2.add(mgrs[i]);
            }
        //System.out.println(presales1 + " " + presales2);
        if (presales1 > 0) {
            email = DataSourceDataProvider.getInstance().getEmailIdForEmployee(presales1);
          //  System.out.println("email--" + email);
        }
        if (presales2 > 0) {
            email1 = DataSourceDataProvider.getInstance().getEmailIdForEmployee(presales2);
         //   System.out.println("email1--" + email1);
        }
        String consultantName = DataSourceDataProvider.getInstance().getConsultantName(consultantId);
        String details = DataSourceDataProvider.getInstance().getDetailsByRequirementId(String.valueOf(requirementId));
        String allDetails[] = details.replaceAll("null", "").split("#");


//            String loginId1=DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(requirementId);
//                        String reportsToTopLevel=DataSourceDataProvider.getInstance().getReportsTo(loginId1);

        //String createdBy = details.substring(0,details.indexOf("#"));
        String createdBy = allDetails[0];
CC2.add(createdBy);
        //String practice = details.substring(details.indexOf("#")+1,details.length());
        String practice = allDetails[1];

        //String assignedByEmail  = DataSourceDataProvider.getInstance().getRequirementAssignedByEmail(requirementId);
        // String recruiter1Email  = DataSourceDataProvider.getInstance().getRequirementRecruiter1Email(requirementId);
        String assignedByEmail = allDetails[2] + "@miraclesoft.com";
        String recruiter1 = allDetails[3];
        String recruiter2 = allDetails[4].replaceAll("_", "");
        HashSet<String> reportsToTopLevel = new HashSet<String>();
        if (!recruiter1.equals("") && recruiter1 != null) {
            recruiter1loginId = DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(recruiter1);
            reportsToTopLevel1 = DataSourceDataProvider.getInstance().getTopLevelReportsLoginIds(recruiter1loginId);
            CC2.addAll(reportsToTopLevel1);
        }
 if (!recruiter2.equals("") && recruiter2 != null) {
                String recruiter2loginId = DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(recruiter2);
                HashSet reportsToTopLevel2 = DataSourceDataProvider.getInstance().getTopLevelReportsLoginIds(recruiter2loginId);
               // reportsToTopLevel.add(recruiter2loginId);
                CC2.addAll(reportsToTopLevel2);
            }
        String errormsg = "success";
        //      String to1=EmailId;
        //String errormsg="success";
        String to = "";
       
        try {
            // System.out.println("in mail manager");
            String subject = consultantName +"-requirement for Tech Screen Evaluation";


            if (email != null && !email.equals("")) {
                to += email + ",";
            }
          //  System.out.println("email2---" + email1);
            if (email1 != null && !email1.equals("")) {
                to += email1 + ",";
            }
            to = to.substring(0, to.length() - 1);
Iterator<String> itr  = CC2.iterator();
            String cc = "";
            while (itr.hasNext()) {
                cc += itr.next().toString() + "@miraclesoft.com,";


            }
             cc = cc.substring(0, cc.length() - 1);

            MimeMultipart multipart = new MimeMultipart("related");

            StringBuilder htmlText = new StringBuilder();
      
/*
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><table align='center'><tr style='background:#07BBD7;height:40px;'>");
            htmlText.append("<td><font color='white' size='4' face='Arial'><p>Tech Screen Evaluation</p></font></td></tr><tr><td>");
            htmlText.append("<table style='background:#CCDBDE;width:100%;'><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");

            htmlText.append("<p>Hello Team,</p>");
            htmlText.append("</font></td></tr><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<p>" + consultantName + "</font> has been submitted for <font color='#3960D2' size='2' face='Arial' style='font-weight:600;'> " + status + " </font> for <font color='#3960D2' size='2' face='Arial' style='font-weight:600;'> " + tittle + " </font>. <br><br><u>Please find the availability below.</u></td></tr><tr><td>");

            htmlText.append("<p>" + status + " Schedule : <br>Date: <font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>" + sedDate + " </font>Time:<font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>" + secTime + " </font>Time Zone:<font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>" + zone + "</font></p>");
            htmlText.append("<p>For further support please contact: <br> ");

            String fullName = DataSourceDataProvider.getInstance().getFullNameBYLoginId(recruiter1loginId);
            htmlText.append("Name: <font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>" + fullName + "</font> E-mail:<font color='#3960D2' size='2' face='Arial' style='font-weight:600;'> " + recruiter1loginId + "@miraclesoft.com</font></p>");

            Iterator iter = reportsToTopLevel1.iterator();
            if (iter.hasNext()) {
                String loginId = (String) iter.next();
                String fullNamee = DataSourceDataProvider.getInstance().getFullNameBYLoginId(loginId);
                htmlText.append("Name: <font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>" + fullNamee + "</font> E-mail:<font color='#3960D2' size='2' face='Arial' style='font-weight:600;'> " + loginId + "@miraclesoft.com</font></p>");
            }

            htmlText.append("</font></td></tr><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<p><u><b><a href='" + url + "recruitment/consultant/reviewTechnical.action?name=jheDudcD&lee=djhgd343D&id=" + Id + "&consultantId=" + consultantId + "&status=" + status + "&requirementId=" + requirementId + "&recConsultantId=" + recConsultantId + "' >Click to View the details</a></b></u>");
            htmlText.append("<br><br>Thank you.</p>");
            htmlText.append("</font></td></tr></td></tr></table><tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font> </td></tr></table>");

            htmlText.append("</body></html>");
            
            */
            
htmlText.append("<!DOCTYPE html>");
            htmlText.append("<html>");
            htmlText.append("<head>");
            htmlText.append("<meta charset='utf-8'>");
            htmlText.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
            htmlText.append("<meta http-equiv='X-UA-Compatible' content='IE=edge' />");
            htmlText.append("<style type='text/css'>");

            htmlText.append("body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;} ");
            htmlText.append("table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;} ");
            htmlText.append("{-ms-interpolation-mode: bicubic;} ");

            htmlText.append("{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}");
            htmlText.append("table{border-collapse: collapse !important;}");
            htmlText.append("body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}");

            htmlText.append("a[x-apple-data-detectors] {color: inherit !important;text-decoration: none !important;font-size: inherit !important;font-family: inherit !important;font-weight: inherit !important;line-height: inherit !important;}");

            htmlText.append("@media screen and (max-width: 525px) {");

            htmlText.append(".wrapper {width: 100% !important;max-width: 100% !important;}");

            htmlText.append(".logo img {margin: 0 auto !important;}");

            htmlText.append(".mobile-hide {display: none !important;}");
            htmlText.append(".img-max {max-width: 100% !important;width: 100% !important;height: auto !important;}");

            htmlText.append(".responsive-table {width: 100% !important;}");

            htmlText.append(".padding {padding: 10px 5% 15px 5% !important;}");
            htmlText.append(".padding-meta {padding: 30px 5% 0px 5% !important;text-align: center;}");
            htmlText.append(".padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}");
            htmlText.append(".no-padding {padding: 0 !important;}");
            htmlText.append(".section-padding {padding: 50px 15px 50px 15px !important;}");

            htmlText.append(".mobile-button-container {margin: 0 auto;width: 100% !important;}");
            htmlText.append(".mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important;display: block !important;}}");

            htmlText.append("div[style*='margin: 16px 0;'] { margin: 0 !important; }");
            htmlText.append("</style>");
            htmlText.append("</head>");
            htmlText.append("<body style='margin: 0 !important; padding: 0 !important;'>");

            htmlText.append("<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>Hello <b>Team,</b><br> <b>" + consultantName + "</b> has been submitted for <b>" + status + "</b> for <b>" + tittle + "</b>.</div>");

            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
            htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center'>");

            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper'>");
            htmlText.append("<tr>");
            htmlText.append("<td align='center' valign='top' style='padding: 15px 0;' class='logo'><a href='http://www.miraclesoft.com/' target='_blank'><img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'></a></td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");

            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 5px;'>");

            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("<tr>");
            htmlText.append("<td>");

            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr>");
            htmlText.append("<td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>Tech Screen Evaluation</b></td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");

            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");

            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr>");
            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>Team,</b><br> <b>" + consultantName + "</b> has been submitted for <b>" + status + "</b> for <b>" + tittle + "</b>.</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>" + status + " Schedule:</b> <br>");
            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Date: :</b> " + sedDate + "<br>");

            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Time:</b> " + secTime + "<br>");
            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Time Zone:</b> " + zone + "");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
            htmlText.append("<b style='font-size: 14px;'>For further details please contact</b><br>");
            String fullName = DataSourceDataProvider.getInstance().getFullNameBYLoginId(recruiter1loginId);
            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Name:</b> " + fullName + "<br>");
            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>E-mail:</b> " + recruiter1loginId + "@miraclesoft.com<br>");
             Iterator iter = reportsToTopLevel1.iterator();
            if (iter.hasNext()) {
                String loginId = (String) iter.next();
                String fullNamee = DataSourceDataProvider.getInstance().getFullNameBYLoginId(loginId);
            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Name:</b> " + fullNamee + "<br>");
            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>E-mail:</b> " + loginId + "@miraclesoft.com<br>");
            }
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b>Click <a href='" + url + "recruitment/consultant/reviewTechnical.action?name=jheDudcD&lee=djhgd343D&id=" + Id + "&consultantId=" + consultantId + "&status=" + status + "&requirementId=" + requirementId + "&recConsultantId=" + recConsultantId + "' target='_blank' style='font-size: 14px; color: #2368a0;'>here</a> to view more details about this Requirement, </b></td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td>");

            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr>");
            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>Thanks & Regards,<br>Corporate Application Support Team, <br>Miracle Software Systems, Inc. <br>Email: hubble@miraclesoft.com <br>Phone: (+1)248-233-1814</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td>");

            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr>");
            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");

            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");

            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("<tr>");
            htmlText.append("<td width='200' align='center' style='text-align: center;'>");
            htmlText.append("<table width='200' cellpadding='0' cellspacing='0' align='center'>");
            htmlText.append("<tr>");
            htmlText.append("<td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("<td width='10'><a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("<td width='10'><a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("<td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("<td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td height='10'></td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems<br></td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");

            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</body>");
            htmlText.append("</html>");
           // System.out.println("htmlText.toString()--->" + htmlText.toString());


            // add it

            // put everything together

            ServiceLocator.getMailServices().doAddEmailLogNew(to, cc, subject, htmlText.toString(), "", "","Requirement Notification");
            //  transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            //transport.sendMessage(message,message.getRecipients(Message.RecipientType.BCC));

        
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        // return errormsg;
    }

    /*Method : sendRepFeedBackDetails(----)
     * Author : santosh kola
     * Date : 01/02/2013
     * Description : Send mail to  sales reprentative, feedback about work.
     */
    
     public static String sendRepFeedBackDetails(String empName, String loginId, String reportsTo,int totalAccs, int nofActivities, int workedAccs, String fromId) throws ServiceLocatorException {
        String responseText = "Mail sent failed, please try later!";
        List userId = DataSourceDataProvider.getInstance().getManagerUserIdOfRecruitment();
        
        /** The from is used for storing the from address. */
        String from = fromId+"@miraclesoft.com";
        
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        //String host = "192.168.5.5";
        
        
        //String host = "mail.miraclesoft.com";
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        //   Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
       // mailSession.setDebug(true);
         mailSession.setDebug(false);
        Transport transport;
        try {
            
            /** The to is used for storing the user mail id to send details. */
            String to = loginId+"@miraclesoft.com";
            String cc = reportsTo+"@miraclesoft.com";
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Your currently logged in Accounts");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
          //  message.addRecipient(Message.RecipientType.BCC,new InternetAddress("vkandregula@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
            htmlText.append("<p>Hello "+empName+", </p>");
            htmlText.append("<p> You have a Total of <b>"+totalAccs+"</b> accounts and in the last 30 Days you only have worked " +
                    "with <b>"+workedAccs+"</b> Accounts and you only had <b>"+nofActivities+"</b> activities.</p>");
           /* htmlText.append("<p><u><b>Registration Details:</b></u><br><br>");
            htmlText.append("First Name: "+firstName+" <br><br>");
            htmlText.append("Last Name: "+lastName+"<br><br>");
            htmlText.append("Employee LoginId: "+loginId+" <br><br>");
            htmlText.append("Employee Working Country: "+workingCountry+" <br><br>");*/
            htmlText.append("Thank you.<br><br>");
            /*htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");*/
            htmlText.append("</body></html>");
            
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            /* second part (the image)*/
           // messageBodyPart = new MimeBodyPart();
            //DataSource fds = new FileDataSource(com.mss.mirage.util.Properties.getProperty("MailImage.Path"));
            //messageBodyPart.setDataHandler(new DataHandler(fds));
           // messageBodyPart.setHeader("Content-ID","<image>");
            
            // add it
           // multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.CC));
           // transport.sendMessage(message,
             //       message.getRecipients(Message.RecipientType.BCC));
            transport.close();
            responseText = "Mail sent successfully.";
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return responseText;
    }
public static String sendRepEmailDetails(String loginId, String reportsTo,String fromId,String accountName,String lastActivityDate) throws ServiceLocatorException {
        String responseText = "Mail sent failed, please try later!";
        List userId = DataSourceDataProvider.getInstance().getManagerUserIdOfRecruitment();
       // System.err.println("loginId---->"+loginId);
        reportsTo=DataSourceDataProvider.getInstance().getReportsTOOneLevel(loginId);
       // System.err.println("reportsTo---->"+reportsTo);
       // System.err.println("fromId---->"+fromId);
        /** The from is used for storing the from address. */
        String from = fromId+"@miraclesoft.com";
      
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        //   Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
       // mailSession.setDebug(true);
         mailSession.setDebug(false);
        Transport transport;
        try {
            
            /** The to is used for storing the user mail id to send details. */
            String to = loginId+"@miraclesoft.com";
            String cc = reportsTo+"@miraclesoft.com";
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Your currently logged in Accounts");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
           // message.addRecipient(Message.RecipientType.BCC,new InternetAddress("vkandregula@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
            
            htmlText.append("<p>Hello "+loginId+",<br><br>");
            
             htmlText.append("<p> You have an account <b>"+accountName+"</b> and last activity on "+lastActivityDate);
            
            htmlText.append("<br><br>Thank you.<br><br>");
           /* htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");*/
            htmlText.append("</body></html>");
            
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
           
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.CC));
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.BCC));
            transport.close();
            responseText = "Mail sent successfully.";
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return responseText;
    }

// New For reset Password send mail
    
    public static void sendResetPwd(String emailId,String loginId,String userName,String password) {
        
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        String to = emailId;
        
        /** The from is used for storing the from address. */
        String from = "hubbleapp@miraclesoft.com";
        
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
        props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        // Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
       // mailSession.setDebug(true);
         mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Password Details");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //message.addRecipient(Message.RecipientType.CC,new InternetAddress("mchennu@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");            
            htmlText.append("<p>Dear "+userName+",<br><br>");			
	    htmlText.append("<p>LoginId : "+loginId);
	    htmlText.append("<br>Password : "+password);
	    htmlText.append("<p><font color='red', size='1' face='Arial'>Note: To better protect your account,make sure that your password is memorable");
	    htmlText.append("for you but difficult for others to guess. Never use the same password that ");
            htmlText.append("you have used in the past, and do not share your password with anyone.</font>");	
            htmlText.append("<br><br>Thank you.<br><br>");			
	    htmlText.append("<p>Regards,");            
            htmlText.append("<p>CorporateApplicationSupport Team,");
            htmlText.append("<br>Miracle Software Systems, Inc.");
            htmlText.append("</body></html>");           
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");              
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            //System.out.println("Mail Sent ----->");
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
        
    }
//apprecition Email start

           public static String sendAppreciationEmailDetails(Map appreciationEmailMap) throws ServiceLocatorException {

        String To = (String) appreciationEmailMap.get("To");
        String CC = (String) appreciationEmailMap.get("CC");
        String BCC = (String) appreciationEmailMap.get("BCC");
        String Title = (String) appreciationEmailMap.get("Title");
        String SubTitle = (String) appreciationEmailMap.get("SubTitle");
        String subject = (String) appreciationEmailMap.get("Subject");
        String BodyContent = appreciationEmailMap.get("BodyContent").toString().replace("\n", "<br/>");
        String LoginId = (String) appreciationEmailMap.get("LoginId");
        String createdBy = (String) appreciationEmailMap.get("createdBy");

        String userName = (String) appreciationEmailMap.get("userName").toString().replace(".", " ");
        String Designation = (String) appreciationEmailMap.get("Designation");
        String Searchflag = (String) appreciationEmailMap.get("Searchflag");
        String createdByLogin = (String) appreciationEmailMap.get("createdByLogin");
        String fromAddress = DataSourceDataProvider.getInstance().getEmailIdForLoginId(createdByLogin);
        String result = "";
        String reportsEmail = "";
        String ccAddresses = "";
        String bccAddresses = "";
        String bccAddresses1 = "";
        String ccAddresses1 = "";

        StringTokenizer stk0 = new StringTokenizer(To, ",");

        while (stk0.hasMoreTokens()) {

            reportsEmail = DataSourceDataProvider.getInstance().getHierarchyEmail(stk0.nextToken());
            StringTokenizer stkEmail = new StringTokenizer(reportsEmail, "|");
            List Emails = new ArrayList();
            while (stkEmail.hasMoreTokens()) {

                Emails.add(stkEmail.nextToken());

            }
            if (Searchflag.equalsIgnoreCase("team")) {
                for (int i = 1; i < Emails.size(); i++) {
                    ccAddresses = ccAddresses + Emails.get(i).toString().trim() + ",";
                }
                bccAddresses = bccAddresses + Emails.get(0).toString().trim() + ",";
            }

            if (Searchflag.equalsIgnoreCase("opt")) {
                for (int i = 0; i < Emails.size(); i++) {
                    ccAddresses = ccAddresses + Emails.get(i).toString().trim() + ",";
                }
                bccAddresses = bccAddresses + fromAddress.trim() + ",";;

            }
            Emails.clear();
        }
        StringTokenizer stk1 = new StringTokenizer(To, ",");

        String toEmail = "";

        int count = 0;
        String toAddresses = "";
        while (stk1.hasMoreTokens()) {
            toEmail = stk1.nextToken() + "@miraclesoft.com";
            toAddresses = toAddresses + toEmail + ",";
            count++;

        }
        if (count == 1) {
            LoginId = userName;
        } else if (count >= 2) {
            LoginId = "Team";

        }
        toAddresses = StringUtils.chop(toAddresses);


        StringTokenizer stk2 = new StringTokenizer(CC, ",");
        String ccEmail = "";

        while (stk2.hasMoreTokens()) {
            ccEmail = DataSourceDataProvider.getInstance().getEmailIdForLoginId(stk2.nextToken());

            ccAddresses = ccAddresses + ccEmail + ",";
        }
        ccAddresses = StringUtils.chop(ccAddresses);


        StringTokenizer stk3 = new StringTokenizer(BCC, ",");
        String bccEmail = "";

        while (stk3.hasMoreTokens()) {
            bccEmail = DataSourceDataProvider.getInstance().getEmailIdForLoginId(stk3.nextToken());

            bccAddresses = bccAddresses + bccEmail + ",";
        }
        bccAddresses = StringUtils.chop(bccAddresses);


        List bcclist = new ArrayList();
        StringTokenizer stkbcc = new StringTokenizer(bccAddresses, ",");
        while (stkbcc.hasMoreTokens()) {


            bcclist.add(stkbcc.nextToken());
        }


        int count1 = 0;
        for (int i = 0; i < bcclist.size(); i++) {

            //  System.out.println("check+++++" + bcclist.get(i).toString());
            for (int j = i; j < bcclist.size(); j++) {

                if (bcclist.get(i).toString().equalsIgnoreCase(bcclist.get(j).toString())) {
                    //     System.out.println("count1======" + bcclist.get(i).toString() + "====" + bcclist.get(j).toString());
                    count1 = count1 + 1;

                } else {
                    count1 = count1;

                }

            }

            if (count1 > 1) {
                bccAddresses1 = bccAddresses1;

            } else {
                bccAddresses1 = bccAddresses1 + bcclist.get(i).toString().trim() + ",";

            }
            count1 = 0;

        }
        bccAddresses1 = StringUtils.chop(bccAddresses1);
        List cclist = new ArrayList();
        StringTokenizer stkcc = new StringTokenizer(ccAddresses, ",");
        while (stkcc.hasMoreTokens()) {


            cclist.add(stkcc.nextToken());
        }


        int count2 = 0;
        for (int i = 0; i < cclist.size(); i++) {

            // System.out.println("check+++++" + cclist.get(i).toString());
            for (int j = i; j < cclist.size(); j++) {

                if (cclist.get(i).toString().equalsIgnoreCase(cclist.get(j).toString())) {
                    //  System.out.println("count1======" + cclist.get(i).toString() + "====" + cclist.get(j).toString());
                    count2 = count2 + 1;

                } else {
                    count2 = count2;

                }

            }

            if (count2 > 1) {
                ccAddresses1 = ccAddresses1;

            } else {
                ccAddresses1 = ccAddresses1 + cclist.get(i).toString().trim() + ",";

            }
            count2 = 0;

        }
        ccAddresses1 = StringUtils.chop(ccAddresses1);

        StringBuilder htmlText = new StringBuilder();
        htmlText.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
        htmlText.append("<head>");
        htmlText.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        htmlText.append("<meta name='viewport' content='width=device-width; initial-scale=1.0; maximum-scale=1.0;'>");
        htmlText.append("<link href='http://fonts.googleapis.com/css?family=calibri:300,400,700' rel='stylesheet' type='text/css'>");
        htmlText.append("<title>");
        htmlText.append("Appreciation Mail");
        htmlText.append("</title>");
        htmlText.append("<style type='text/css'>");
        htmlText.append("html");
        htmlText.append("{");
        htmlText.append("width: 100%;");
        htmlText.append("}");
        htmlText.append("::-moz-selection{");
        htmlText.append("background:#fefac7;");
        htmlText.append("color:#4a4a4a;");
        htmlText.append("}");
        htmlText.append("::selection{");
        htmlText.append("background:#fefac7;");
        htmlText.append("color:#4a4a4a;");
        htmlText.append("}");
        htmlText.append("body {");
        htmlText.append("margin: 0;");
        htmlText.append("padding: 0;");
        htmlText.append("}");
        htmlText.append(".ReadMsgBody");
        htmlText.append("{");
        htmlText.append("width: 100%;");
        htmlText.append("background-color: #f1f1f1;");
        htmlText.append("}");
        htmlText.append(".ExternalClass");
        htmlText.append("{");
        htmlText.append("width: 100%;");
        htmlText.append("background-color: #f1f1f1;");
        htmlText.append("}");
        htmlText.append("a {");
        htmlText.append("color:#ef4048;");
        htmlText.append("text-decoration: none;");
        htmlText.append("font-weight: normal;");
        htmlText.append("font-style: normal;");
        htmlText.append("}");
        htmlText.append("p, div, span {");
        htmlText.append("margin: 0 !important;");
        htmlText.append("}");
        htmlText.append("table {");
        htmlText.append("border-collapse: collapse;");
        htmlText.append("}");
        htmlText.append("@media only screen and (max-width: 640px)  {");
        htmlText.append("body {");
        htmlText.append("width: auto !important;");
        htmlText.append("}");
        htmlText.append("body table table{");
        htmlText.append("width:100% !important;");
        htmlText.append("}");
        htmlText.append("body table[class='table-wrapper'] {");
        htmlText.append("width:auto !important;");
        htmlText.append("margin: 0px 20px !important;");
        htmlText.append("}");
        htmlText.append("body table[class='table-inner'] {");
        htmlText.append("width: 100% !important;");
        htmlText.append("margin: 0 auto !important;");
        htmlText.append("}");
        htmlText.append("body table[class='full'] {");
        htmlText.append("width: 100% !important;");
        htmlText.append("margin:0 auto !important;");
        htmlText.append("}");
        htmlText.append("body table[class='center'] {");
        htmlText.append("margin: 0 auto !important;");
        htmlText.append("}");
        htmlText.append("body td[class='center'] {");
        htmlText.append("text-align: center !important;");
        htmlText.append("}");
        htmlText.append("body td[class='rewrite_padding'] {");
        htmlText.append("padding:0px !important;");
        htmlText.append("border-left: none !important;");
        htmlText.append("}");
        htmlText.append("body img[class='img_scale'] {");
        htmlText.append("width: 100% !important;");
        htmlText.append("height: auto !Important;");
        htmlText.append("}");
        htmlText.append("body img[class='divider'] {");
        htmlText.append("width: 100% !important;");
        htmlText.append("height: 2px !Important;");
        htmlText.append("}");
        htmlText.append("}");
        htmlText.append("@media only screen and (max-width: 479px)  {");
        htmlText.append("body {");
        htmlText.append("width: auto !important;");
        htmlText.append("}");
        htmlText.append("body table table{");
        htmlText.append("width:100% !important;");
        htmlText.append("}");
        htmlText.append("body table[class='table-wrapper'] {");
        htmlText.append("width: auto !important;");
        htmlText.append("margin: 0px 20px !important;");
        htmlText.append("}");
        htmlText.append("body table[class='table-inner'] {");
        htmlText.append("width: 100% !important;");
        htmlText.append("margin: 0 auto !important;");
        htmlText.append("}");
        htmlText.append("body table[class='full'] {");
        htmlText.append("width: 100% !important;");
        htmlText.append("margin: 0 auto !important;");
        htmlText.append("}");
        htmlText.append("body table[class='center'] {");
        htmlText.append("margin: 0 auto !important;");
        htmlText.append("}");
        htmlText.append("body td[class='center'] {");
        htmlText.append("text-align: center !important;");
        htmlText.append("}");
        htmlText.append("body td[class='rewrite_padding'] {");
        htmlText.append("padding:0px !important;");
        htmlText.append("border-left: none !important;");
        htmlText.append("}");
        htmlText.append("body img[class='img_scale'] {");
        htmlText.append("width: 100% !important;");
        htmlText.append("height: auto !Important;");
        htmlText.append("}");
        htmlText.append("body img[class='divider'] {");
        htmlText.append("width: 100% !important;");
        htmlText.append("height: 2px !Important;");
        htmlText.append("}");
        htmlText.append("body td[class='one_third'] {");
        htmlText.append("width: 100% !important;");
        htmlText.append("display: block !important;");
        htmlText.append("padding-bottom: 20px;");
        htmlText.append("margin: 0 auto !important;");
        htmlText.append("text-align: center !important;");
        htmlText.append("}");
        htmlText.append("body td[class='one_third_last'] {");
        htmlText.append("width: 100% !important;");
        htmlText.append("display: block !important;");
        htmlText.append("margin: 0 auto !important;");
        htmlText.append("text-align: center !important;");
        htmlText.append("}");
        htmlText.append("}");
        htmlText.append("div.preheader{");
        htmlText.append("line-height:0px;");
        htmlText.append("font-size:0px;");
        htmlText.append("height:0px;");
        htmlText.append("display:none !important;");
        htmlText.append("display:none;");
        htmlText.append("visibility:hidden;");
        htmlText.append("}");
        htmlText.append("table.textbutton td{");
        htmlText.append("display:block;");
        htmlText.append("text-align: center;");
        htmlText.append("}");
        htmlText.append("table.textbutton a {");
        htmlText.append("text-decoration: none;");
        htmlText.append("font-style: normal;");
        htmlText.append("font-weight: normal;");
        htmlText.append("color:#ffffff;");
        htmlText.append("}");
        htmlText.append("table.whitebutton td{");
        htmlText.append("display:block}");
        htmlText.append("a.white_links {");
        htmlText.append("color:#ffffff;");
        htmlText.append("font-weight: normal;");
        htmlText.append("text-decoration: none;");
        htmlText.append("}");
        htmlText.append("a.heading_links {");
        htmlText.append("text-decoration: none;");
        htmlText.append("font-style: normal;");
        htmlText.append("font-weight: normal;");
        htmlText.append("color:#333333;");
        htmlText.append("font-family: 'calibri' !important;");
        htmlText.append("}");
        htmlText.append("td.footer_links a{");
        htmlText.append("text-decoration: none;");
        htmlText.append("font-style: normal;");
        htmlText.append("font-weight: normal;");
        htmlText.append("color:#7a7a7a;");
        htmlText.append("font-family: 'calibri' !important;");
        htmlText.append("}");
        htmlText.append("span,");
        htmlText.append("a{");
        htmlText.append("font-family: 'calibri' !important;");
        htmlText.append("}");
        htmlText.append("</style>");
        htmlText.append("</head>");
        htmlText.append("<body>");
        htmlText.append("<modules>");
        htmlText.append("<module label='header'>");
        htmlText.append("<table style='background-color: #b3b2b7;' align='center' border='0' cellpadding='0' cellspacing='0' width='100%'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top' width='100%'>");
        htmlText.append("<table width='50%' border='0' align='center'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td height='25'>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("<modules>");
        htmlText.append("<module label='header'>");
        htmlText.append("<table style='background-color: #b3b2b7;' align='center' border='0' cellpadding='0' cellspacing='0' width='100%'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top' width='100%'>");
        htmlText.append("<table width='50%' bgcolor='#333333' border='0' background='http://www.miraclesoft.com/images/newsletters/bg-image-1.png' align='center' style='background-image: url(http://www.miraclesoft.com/images/newsletters/bg-image-1.png); border-radius : 20px 20px 0px 0px; background-position: center center; -webkit-background-size: cover; -moz-background-size: cover; -o-background-size: cover; background-size: cover; '>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top' align='center'>");
        htmlText.append("<table class='table-wrapper' width='660' border='0' align='center' cellpadding='0' cellspacing='0'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td style='padding: 25px 20px;'>");
        htmlText.append("<table class='table-inner' width='640' border='0' align='center' cellpadding='0' cellspacing='0'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top'>");
        htmlText.append("<table class='full' width='640' border='0' align='left' cellpadding='0' cellspacing='0' style='border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td class='one_third' width='230' align='left' valign='middle' style=' margin: 0;font-weight: normal; font-size:14px ; color:#ffffff; font-family: calibri; line-height: 18px;mso-line-height-rule: exactly;'>");
        htmlText.append("<multi>");
        htmlText.append("<span>");
        htmlText.append("</span>");
        htmlText.append("</multi>");
        htmlText.append("</td>");
        htmlText.append("<td class='one_third' width='180' align='center' valign='top' style=''>");
        htmlText.append("<span>");
        htmlText.append("<a href='http://www.miraclesoft.com/' target='blank' style='color:#ef4048;'>");
        htmlText.append("<img editable='' label='logo' src='http://www.miraclesoft.com/images/newsletters/logo.png' alt='logo' width='150' height='auto' border='0' style='display: inline-block;'>");
        htmlText.append("</a>");
        htmlText.append("</span>");
        htmlText.append("</td>");
        htmlText.append("<td class='one_third_last' width='230' align='right' valign='middle' style=' margin: 0;font-weight: normal; font-size:14px ; color:#ffffff; font-family: calibri; line-height: 18px;mso-line-height-rule: exactly;'>");
        htmlText.append("<span>");
        htmlText.append("<a class='white_links' href='http://www.miraclesoft.com/library' target='blank' style='color:#ffffff; font-weight: normal; text-decoration: none;'>");
        htmlText.append("</a>");
        htmlText.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        htmlText.append("<a class='white_links' href='http://www.miraclesoft.com/events' target='blank' style='color:#ffffff; font-weight: normal; text-decoration: none;'>");
        htmlText.append("</a>");
        htmlText.append("</span>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td colspan='3' align='center' valign='middle' style='margin: 0; padding-top: 30px; padding-bottom: 10px; text-transform: uppercase; font-size:28px ; font-weight: bold; color:#ffffff; font-family: calibri; line-height: 140%;  mso-line-height-rule: exactly;'>");
        htmlText.append("<multi>");
        htmlText.append("<span>");
        htmlText.append("" + Title + ",");
        htmlText.append("</span>");
        htmlText.append("</multi>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td colspan='3' align='center' valign='middle' style='margin: 0; padding-bottom: 10px; font-size:14px ; font-weight: normal; color:#ffffff; font-family: calibri; line-height:100%;'>");
        htmlText.append("<span>");
        htmlText.append("<img editable='' label='image' src='http://www.miraclesoft.com/images/newsletters/heading-divider-img.png' alt='heading-divider-img' width='200' height='auto' border='0' style='display: inline-block;'>");
        htmlText.append("</span>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td colspan='3' align='center' valign='middle' style='margin: 0; padding-bottom: 0; font-size:16px ; font-weight: normal; color:#ffffff; font-family: calibri; line-height: 140%;  mso-line-height-rule: exactly;'>");
        htmlText.append("<multi>");
        htmlText.append("<span>");
        htmlText.append("" + SubTitle + ",");
        htmlText.append("</span>");
        htmlText.append("</multi>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("<modules>");
        htmlText.append("<module label='header'>");
        htmlText.append("<table style='background-color: #b3b2b7;' align='center' border='0' cellpadding='0' cellspacing='0' width='100%'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top' width='100%'>");
        htmlText.append("<table width='50%' bgcolor='#ededed' border='0' align='center'>");
        htmlText.append("<tbody>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("<module label='1 column article'>");
        htmlText.append("<table style='background-color: #b3b2b7;' align='center' border='0' cellpadding='0' cellspacing='0' width='100%'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top' width='100%'>");
        htmlText.append("<table width='50%' bgcolor='#ffffff' border='0' align='center'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td height='10' style='padding:0; line-height: 0;'>");
        htmlText.append("&nbsp;");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top' align='center'>");
        htmlText.append("<table class='table-wrapper' width='660' border='0' align='center' cellpadding='0' cellspacing='0'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td style='padding: 0px 20px;'>");
        htmlText.append("<table class='table-inner' width='640' border='0' align='center' cellpadding='0' cellspacing='0'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top'>");
        htmlText.append("<table class='full' width='640' border='0' align='left' cellpadding='0' cellspacing='0' style='border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td align='justify' valign='top' style='margin: 0; padding-top: 5px; font-size:15px ; font-weight: normal; color:#8c8c8c; font-family: calibri; line-height: 180%;  mso-line-height-rule: exactly;'>");
        htmlText.append("<multi>");
        htmlText.append("<span>");
        htmlText.append("<b>Dear " + LoginId + ",</b><br><br>");
        htmlText.append("" + BodyContent + ",");
        htmlText.append("<br><br><b>Best Regards,</b><br>" + createdBy + ",<br>" + Designation + ".</span></multi>");
        htmlText.append("</span>");
        htmlText.append("</multi>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td height='10' style='padding:0; line-height: 0;'>");
        htmlText.append("&nbsp;");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td align='justify' valign='top' style='margin: 0; padding-top: 5px; font-size:15px ; font-weight: normal; color:#8c8c8c; font-family: calibri; line-height: 180%;  mso-line-height-rule: exactly;'>");
        htmlText.append("<multi>");
        htmlText.append("<span>");
        htmlText.append("</span>");
        htmlText.append("</multi>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("<module label='header'>");
        htmlText.append("<table style='background-color: #b3b2b7;' align='center' border='0' cellpadding='0' cellspacing='0' width='100%'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top' width='100%'>");
        htmlText.append("<table width='50%' bgcolor='#ededed' border='0' align='center'>");
        htmlText.append("<tbody>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("<module label='1 column article'>");
        htmlText.append("<table style='background-color: #b3b2b7;' align='center' border='0' cellpadding='0' cellspacing='0' width='100%'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top' width='100%'>");
        htmlText.append("<table width='50%' bgcolor='#232527' border='0' align='center' style='border-radius : 0px 0px 20px 20px;'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td height='20' style='padding:0; line-height: 0;'>");
        htmlText.append("&nbsp;");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top' align='center'>");
        htmlText.append("<table class='table-wrapper' width='660' border='0' align='center' cellpadding='0' cellspacing='0'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td style='padding: 0px 20px;'>");
        htmlText.append("<table class='table-inner' width='640' border='0' align='center' cellpadding='0' cellspacing='0'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top' style='padding-bottom: 10px;'>");
        htmlText.append("<table class='full' width='640' border='0' align='center' cellpadding='0' cellspacing='0' style='border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td align='center' style='font-size:14px ; font-weight: normal; color:#ffffff; font-family: calibri; line-height: 100%;'>");
        htmlText.append("<span>");
        htmlText.append("<a href='http://www.miraclesoft.com/' target='blank' style='color:#ef4048;'>");
        htmlText.append("<img editable='' label='logo' src='http://www.miraclesoft.com/images/newsletters/logo.png' alt='logo' width='140' height='auto' border='0' style='display: inline-block;'>");
        htmlText.append("</a>");
        htmlText.append("</span>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("<table width='1' border='0' align='left' cellpadding='0' cellspacing='0' style='border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td width='100%' height='20' style='line-height:0;'>");
        htmlText.append("&nbsp;");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("<table class='full' width='640' border='0' align='center' cellpadding='0' cellspacing='0' style='border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td align='center' style='font-size:14px ; font-weight: normal; color:#ffffff; font-family: 'trebuchet ms'; line-height: 100%;'>");
        htmlText.append("<span>");
        htmlText.append("<a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='blank' style='color:#ef4048; text-decoration: none;'>");
        htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/ln.png' alt='twitter' width='25' height='auto' border='0' style=' display: inline-block;'>");
        htmlText.append("</a>");
        htmlText.append("&nbsp;&nbsp;&nbsp;");
        htmlText.append("<a href='https://plus.google.com/+Team_MSS' target='blank' style='color:#ef4048; text-decoration: none;'>");
        htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/gs.png' alt='pinterest' width='25' height='auto' border='0' style='display:inline-block;'>");
        htmlText.append("</a>");
        htmlText.append("&nbsp;&nbsp;&nbsp;");
        htmlText.append("<a href='https://facebook.com/miracle45625' target='blank' style='color:#ef4048; text-decoration: none;'>");
        htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/fk.png' alt='facebook' width='25' height='auto' border='0' style='display: inline-block;'>");
        htmlText.append("</a>");
        htmlText.append("&nbsp;&nbsp;&nbsp;");
        htmlText.append("<a href='https://twitter.com/Team_MSS' target='blank' style='color:#ef4048; text-decoration: none;'>");
        htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/tr.png' alt='instagram' width='25' height='auto' border='0' style=' display: inline-block;'>");
        htmlText.append("</a>");
        htmlText.append("&nbsp;&nbsp;&nbsp;");
        htmlText.append("<a href='https://www.youtube.com/c/Team_MSS' target='blank' style='color:#ef4048; text-decoration: none;'>");
        htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/ye.png' alt='dribbble' width='25' height='auto' border='0' style='display: inline-block;'>");
        htmlText.append("</a>");
        htmlText.append("&nbsp;&nbsp;&nbsp;");
        htmlText.append("<a href='https://www.flickr.com/photos/team_miracle' target='blank' style='color:#ef4048; text-decoration: none;'>");
        htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/fr.png' alt='linkedin' width='25' height='auto' border='0' style=' display: inline-block;'>");
        htmlText.append("</a>");
        htmlText.append("</span>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top'>");
        htmlText.append("<table class='full' width='640' border='0' align='center' cellpadding='0' cellspacing='0' style='border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td class='footer_links' align='center' valign='top' style='margin: 0; padding-bottom: 0; font-size:14px ; font-weight: normal; color:#8c8c8c; font-family: calibri; line-height: 180%;  mso-line-height-rule: exactly;'>");
        htmlText.append("<multi>");
        htmlText.append("<span>");
        htmlText.append("</span>");
        htmlText.append("</multi>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("<table class='full' width='640' border='0' align='center' cellpadding='0' cellspacing='0' style='border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td class='footer_links' width='640' align='center' valign='top' style='margin: 0; font-size:14px ; font-weight: normal; color:#8c8c8c; font-family: calibri; line-height: 180%;  mso-line-height-rule: exactly;'>");
        htmlText.append("<multi>");
        htmlText.append("<span>");
        htmlText.append("<a href='http://www.miraclesoft.com/company/about-us.php' target='blank' style='text-decoration: none; font-style: normal; font-weight: bold; color:#8c8c8c;'>");
        htmlText.append("Company");
        htmlText.append("</a>");
        htmlText.append("</span>");
        htmlText.append("<span>");
        htmlText.append("<a href='http://www.miraclesoft.com/library/' target='blank' style='text-decoration: none; font-style: normal; font-weight: bold; color:#8c8c8c;'>");
        htmlText.append("Library");
        htmlText.append("</a>");
        htmlText.append("</span>");
        htmlText.append("|");
        htmlText.append("<span>");
        htmlText.append("<a href='http://www.miraclesoft.com/company/partnerships.php' target='blank' style='text-decoration: none; font-style: normal; font-weight: bold; color:#8c8c8c;'>");
        htmlText.append("Partnerships");
        htmlText.append("</a>");
        htmlText.append("</span>");
        htmlText.append("|");
        htmlText.append("<span>");
        htmlText.append("<a href='http://www.miraclesoft.com/events/' target='blank' style='text-decoration: none; font-style:normal;font-weight: bold; color:#8c8c8c;'>");
        htmlText.append("Events");
        htmlText.append("</a>");
        htmlText.append("</span>");
        htmlText.append("</multi>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td height='10'>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("<table class='full' width='640' border='0' align='center' cellpadding='0' cellspacing='0' style='border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td class='footer_links' width='640' align='center' valign='top' style='margin: 0; font-size:13px ; font-weight: normal; color:#8c8c8c; font-family: calibri; line-height: 180%;  mso-line-height-rule: exactly;'>");
        htmlText.append("<multi>");
        htmlText.append("<span style='text-decoration: none; font-style: normal; font-weight: normal; color:#8c8c8c;'>");
        htmlText.append("&#169 2015 Miracle Software Systems, Inc.");
        htmlText.append("</span>");
        htmlText.append("</multi>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td height='25' style='padding:0; line-height: 0;'>");
        htmlText.append("&nbsp;");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("<modules>");
        htmlText.append("<module label='header'>");
        htmlText.append("<table style='background-color: #b3b2b7;' align='center' border='0' cellpadding='0' cellspacing='0' width='100%'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td valign='top' width='100%'>");
        htmlText.append("<table width='50%' border='0' align='center'>");
        htmlText.append("<tbody>");
        htmlText.append("<tr>");
        htmlText.append("<td height='35'>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("<modules>");
        htmlText.append("<module label='header'>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</tbody>");
        htmlText.append("</table>");
        htmlText.append("</body>");
        htmlText.append("</html>");


        result = ServiceLocator.getMailServices().doAddEmailLogWithFromAddress(toAddresses, ccAddresses1, subject, htmlText.toString(), "", bccAddresses1, "EmpAppreciation", fromAddress);


        return result;

    }

public static void sendBridgeInvitation(String attendees,String organizer,String BirdgeDate,String startTime,String midDayFrom,String endTime,String midDayTo,String timeZone,String comments,String location) throws Exception {
        
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        String to = "apatnaik@miraclesoft.com";
        
        /** The from is used for storing the from address. */
       // String from = "apatnaik@miraclesoft.com";
        String from = "hubbleapp@miraclesoft.com";
        
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        
        Authenticator auth = new SMTPAuthenticator();
        // Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
       // mailSession.setDebug(true);
         mailSession.setDebug(false);
        Transport transport;
        try {
             MimetypesFileTypeMap mimetypes = (MimetypesFileTypeMap) MimetypesFileTypeMap.getDefaultFileTypeMap();
                mimetypes.addMimeTypes("text/calendar ics ICS");
//register the handling of text/calendar mime type
                MailcapCommandMap mailcap = (MailcapCommandMap) MailcapCommandMap.getDefaultCommandMap();
                  mailcap.addMailcap("text/calendar;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Event Invitation");
            message.setFrom(new InternetAddress(from));
            String attendeesMails[]=attendees.split(Pattern.quote(","));
            for(int i=0;i<attendeesMails.length;i++){
                  message.addRecipient(Message.RecipientType.TO,new InternetAddress(attendeesMails[i]));
            }
          
            
            
            // Create an alternative Multipart
                Multipart multipart = new MimeMultipart("alternative");


            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
           String attendee=attendees.replaceAll(",", "<br>");
              htmlText.append("<html>");
        htmlText.append("<body>");
        htmlText.append("<table align='center'>");
        htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
        htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
        htmlText.append("<td><font color='white' size='4' face='Arial'><p>Conference Call Details</p></font></td>");
        htmlText.append("</tr>");
        htmlText.append("<tr><td>");
        htmlText.append("<table style='background:#FFFFCC;width:100%;' color='#151B54' size='4px' height='20px' >");
        htmlText.append("<tr><td><font color='#3399FF' size='2' face='Arial' style='font-weight:600;'><br><font color='black'>Dear</font>" + " Attendee" + ",</font><br></td></tr>");
        htmlText.append("<tr><td colspan='2'>We have a call On:<b><font color='#6666FF'>" + BirdgeDate + " "+startTime+ " " + midDayFrom + "-"+endTime+ " " + midDayTo  + " "+timeZone+" </font></b></td></tr>");
        htmlText.append(" <tr><td>");
        htmlText.append("<table border='0' align='left'>  ");
        htmlText.append(" <tr><td valign='top' ><font >Location :</font></td><td align='left'><font color='#6666FF' >" + location + "</font></td></tr>");
      
        htmlText.append(" <tr><td><font>Description :</font></td></tr>");
        htmlText.append(" <tr><td valign='top' ><font ></font></td><td align='left'><font color='#6666FF' >" + comments + "</font></td></tr>");
        htmlText.append(" <tr><td valign='top' ><font >attendees</font></td><td align='left'><font color='#6666FF' >" + attendee + "</font></td></tr>");
       
        htmlText.append(" <tr><td></td></tr></table></td></tr>");

       
        

        htmlText.append("<tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font></td></tr> ");
        htmlText.append("</table></td></tr></table></body></html> ");        
            
            messageBodyPart.setContent(htmlText.toString(), "text/html");              
            // add it
            multipart.addBodyPart(messageBodyPart);
            
                             BodyPart calendarPart = buildCalendarPart(attendees,organizer,BirdgeDate,startTime,midDayFrom,endTime,midDayTo,timeZone,comments,location);
                multipart.addBodyPart(calendarPart);
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            //System.out.println("Mail Sent ----->");
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
       

    }
  private static BodyPart buildCalendarPart(String attendees,String organizer,String BirdgeDate,String startTime,String midDayFrom,String endTime,String midDayTo,String timeZone,String comments,String location) throws Exception {

        BodyPart calendarPart = new MimeBodyPart();
organizer=DataSourceDataProvider.getInstance().getemployeenamebyloginId(organizer);
        SimpleDateFormat iCalendarDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
//if ("EST".equals(timezone)) {
//                timezone = "America/New_York";
//            }
            
            sdfInput.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        java.util.Date date = null;
       // System.out.println("getInterviewDate()-->" + recruitmentAction.getInterviewDate());
        date = sdfInput.parse(BirdgeDate+" " +startTime+" "+midDayFrom);
       java.util.Date enddate = sdfInput.parse(BirdgeDate+" " +endTime+" "+midDayTo);
        //System.out.println("iCalendarDateFormat.format(date);" + iCalendarDateFormat.format(date));
      //System.out.println("date---"+date);
        Calendar cal = Calendar.getInstance();
     //   System.out.println("recruitmentAction.getInterviewDate()" + recruitmentAction.getInterviewDate());

        // SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.EN_US);
        // cal.setTime(iCalendarDateFormat.parse(recruitmentAction.getInterviewDate()));// all done
        Date start = cal.getTime();
        //   System.out.println("iCalendarDateFormat.format(start)"+iCalendarDateFormat.format(start));
        //check the icalendar spec in order to build a more complicated meeting request
comments=comments.replaceAll("<br/>", "\\\\n");
String attendee[]=attendees.split(Pattern.quote(","));
     // System.out.println("comments--->"+comments);
       String calendarContent =
                "BEGIN:VCALENDAR\n"
                + "METHOD:REQUEST\n"
               // + "PRODID: BCP - Meeting\n"
              +"PRODID: -//Mozilla.org/NONSGML Mozilla Calendar V1.1//EN\n"
                + "VERSION:2.0\n"
                + "BEGIN:VEVENT\n"
                + "DTSTAMP:" + iCalendarDateFormat.format(date) + "\n"
                + "DTSTART:" + iCalendarDateFormat.format(date) + "\n"
                + "DTEND:" + iCalendarDateFormat.format(enddate) + "\n"
                + "SUMMARY:Conference Call\n"
                + "UID:324\n"
              //  + "ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;RSVP=TRUE:MAILTO:organizer@yahoo.com\n"
                + "ORGANIZER:MAILTO: "+organizer+"\n"
                + "LOCATION:"+ location+"\n";
        for (int i = 0; i < attendee.length; i++) {
           calendarContent += "ATTENDEE;REQ-PARTICIPANT:"+attendee[i]+"\n";
          
      }
               //calendarContent +="ATTENDEE;REQ-PARTICIPANT:Anand apatnaik@miraclesoft.com\n"
                calendarContent += "DESCRIPTION:"+comments+"\n"
                + "SEQUENCE:0\n"
                + "PRIORITY:5\n"
                + "CLASS:PUBLIC\n"
                + "STATUS:CONFIRMED\n"
                + "TRANSP:OPAQUE\n"
                + "BEGIN:VALARM\n"
                + "ACTION:DISPLAY\n"
                + "DESCRIPTION:REMINDER\n"
                + "TRIGGER;RELATED=START:-PT00H15M00S\n"
                + "END:VALARM\n"
                + "END:VEVENT\n"
                + "END:VCALENDAR";


   //   System.out.println("calendarContent--->"+calendarContent);
        calendarPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
        calendarPart.setContent(calendarContent, "text/calendar;method=CANCEL");

        return calendarPart;
    }
  
  
    //Apprecation email end    
  /* PayRoll password end*/
  public static void sendPayrollPassword(String email,String userName,String password) {
        
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /** The to is used for storing the user mail id to send details. */
        String to = email;
      //  to="apatnaik@miraclesoft.com";
        /** The from is used for storing the from address. */
        String from = "hubbleapp@miraclesoft.com";
        
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        
        /**The props is instance variabel to <code>Properties</code> class */
        Properties props = new Properties();
        
        /**Here set smtp protocal to props */
        props.setProperty("mail.transport.protocol", "smtp");
        
        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);
         props.put("mail.smtp.starttls.enable", "true");
        /** Here set the authentication for the host **/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        Authenticator auth = new SMTPAuthenticator();
        // Session mailSession = Session.getDefaultInstance(props, null);
        Session mailSession = Session.getDefaultInstance(props, auth);
       // mailSession.setDebug(true);
         mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Payroll Password Details");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //message.addRecipient(Message.RecipientType.CC,new InternetAddress("mchennu@miraclesoft.com"));
            
            
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            
           
            
             htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><table align='center'><tr style='background:#07BBD7;height:40px;'>");
            htmlText.append("<td><font color='white' size='4' face='Arial'><p>Payroll Password</p></font></td></tr><tr><td>");
            htmlText.append("<table style='background:#CCDBDE;width:100%;'><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");

            htmlText.append("<p>Dear "+userName+",</p>");
            htmlText.append("</font></td></tr><tr><td>");
            htmlText.append(" <font  size='2' face='Arial' style='font-weight:600;'>Your Payroll Password is </font><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>"+password+ "<br><br></font></td></tr>");

            htmlText.append("<tr><td colspan='2' height='50px'><font color='red' size='3'>Please Note:</font><font color='#3090C7' size='3px'>");
 htmlText.append("To better protect your account, make sure that your password is memorable for you but ");
 htmlText.append("difficult for others to guess. Never use the same password that you have used in the past,");
  htmlText.append("and do not share your password with anyone.</font></td></tr>");

           
            htmlText.append("<tr><td></font></td></tr><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'><br><br>Thank you.</p></font></td></tr>");
htmlText.append("<tr><td colspan='2' height='20px'><font color='#151B54' size='3'>Regards,</font></td></tr><tr><td colspan='2' height='20px'><font color='#151B54' size='3'>CorporateApplicationSupport Team,</font></td></tr><tr><td colspan='2' height='20px'><font color='#151B54' size='3'>Miracle Software Systems, Inc.</font></td></tr><tr><td width='600px' height='40px'></td></tr>");
            htmlText.append("</td></tr></table><tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font> </td></tr></table>");

            htmlText.append("</body></html>");
           //  System.out.println("htmlText.toString()---"+htmlText.toString());
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
           
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);
            
            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }  catch (MessagingException ex) {
            ex.printStackTrace();
        }
        
    }
  /* PayRoll password end*/

    public static void sendOpportunityMailToPracticeSalesAndPreSales(String title, String state, String type, String practiceName, String dueDate, Timestamp createdDate, String to,String flag,int RegionalMgrId,int PracticeMgrId,int InsideSalesId,int RegionalMgrId1,int PracticeMgrId1,int InsideSalesId1,String modifiedBy) throws ServiceLocatorException {
        System.out.println("sendOpportunityMailToPracticeSalesAndPreSales..");
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        /**
         * The to is used for storing the user mail id to send details.
         */
        //   String to = "tkadamanti@miraclesoft.com";
        /**
         * The from is used for storing the from address.
         */
        String from = "hubbleapp@miraclesoft.com";

        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!

        Authenticator auth = new SMTPAuthenticator();

        try {
           String InsideSalesName="",PracticeMgrName="",RegionalMgrName="",prevInsideSalesName="",prevPracticeMgrName="",prevRegionalMgrName="";
             String subject ="";
             if(flag.equalsIgnoreCase("Added")){
            subject = "New opportunity was added";
             }else{
             subject = " Opportunity was updated";    
             }
            System.out.println("inside..."+InsideSalesId+"PracticeMgrId.."+PracticeMgrId+"RegionalMgrId.."+RegionalMgrId);
            DataSourceDataProvider dataSourceDataProvider = DataSourceDataProvider.getInstance();
             if(InsideSalesId!=-1){
            InsideSalesName =  dataSourceDataProvider.getEmployeeNameByEmpNo(InsideSalesId);
              prevInsideSalesName =  dataSourceDataProvider.getEmployeeNameByEmpNo(InsideSalesId1);
             }
             if(PracticeMgrId!=-1){
             PracticeMgrName =  dataSourceDataProvider.getEmployeeNameByEmpNo(PracticeMgrId); 
              prevPracticeMgrName =  dataSourceDataProvider.getEmployeeNameByEmpNo(PracticeMgrId1); 
             }
              if(RegionalMgrId!=-1)
              {
             RegionalMgrName =  dataSourceDataProvider.getEmployeeNameByEmpNo(RegionalMgrId);
              prevRegionalMgrName =  dataSourceDataProvider.getEmployeeNameByEmpNo(RegionalMgrId1);
            
              }
           System.out.println("InsideSalesName..."+InsideSalesName+"PracticeMgrName.."+PracticeMgrName+"RegionalMgrName.."+RegionalMgrName);
           //  modifiedBy= dataSourceDataProvider.getemployeenamebyloginId(modifiedBy);
  // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part  (the html)
            // BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            /* htmlText.append("<html><head><title>Mail From Hubble Portal</title></head>");
             htmlText.append("<body>");
             htmlText.append("<table align='center'>");
             htmlText.append("<tr style='background:#07BBD7;height:40px;'>");
             htmlText.append("<td><font color='white' size='4' face='Arial'><p>Hubble Mail Tracking System </p></font></td></tr>");
             htmlText.append("<tr><td><table style='background:#CCDBDE;width:100%;'><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
             htmlText.append("<p>Hello Team,</td></tr>");
             htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'><p>New Opportunity was Assigned");
             htmlText.append("</font>  <font color='#111728' size='2' face='Arial' style='font-weight:600;'</p></font> </td></tr>");
             htmlText.append("<td><font color='#111728' size='2' face='Arial' style='font-weight:600;'><p><u>Opportunity Details:</u></p></font></td></tr>");

             htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>  Title</font>:  <font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + title);
             htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>  Type</font>:  <font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + type);
             htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>  State</font>:  <font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + state);
             htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>  Practice Name</font>:  <font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + practiceName);
             htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'> Due Date</font>:  <font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + dueDate);


             htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'><br><br>Regards,</td></tr>");

             htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Corporate Application Support Team,</td></tr>");
             htmlText.append(" <tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Miracle Software Systems</td></tr>");
             htmlText.append("</table></font></td></tr><tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font> </td></tr></table></body></html>");*/


            htmlText.append("<!DOCTYPE html>");
            htmlText.append("<html>");
            htmlText.append("  <head>");
            htmlText.append("   <meta charset='utf-8'>");
            htmlText.append("   <meta name='viewport' content='width=device-width, initial-scale=1'>");
            htmlText.append(" <meta http-equiv='X-UA-Compatible' content='IE=edge' />");
            htmlText.append("  <style type='text/css'>");

            htmlText.append("    body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;} ");
            htmlText.append("   table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;} ");
            htmlText.append(" img{-ms-interpolation-mode: bicubic;} ");

            htmlText.append("  img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}");
            htmlText.append("  table{border-collapse: collapse !important;}");
            htmlText.append(" body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}");

            htmlText.append(" a[x-apple-data-detectors] {");
            htmlText.append(" color: inherit !important;");
            htmlText.append(" text-decoration: none !important;");
            htmlText.append("  font-size: inherit !important;");
            htmlText.append("  font-family: inherit !important;");
            htmlText.append("  font-weight: inherit !important;");
            htmlText.append("    line-height: inherit !important;");
            htmlText.append("   }");
            htmlText.append("  @media screen and (max-width: 525px) {");
            htmlText.append(" .wrapper {");
            htmlText.append(" width: 100% !important;");
            htmlText.append(" max-width: 100% !important;");
            htmlText.append(" }");
            htmlText.append(" .logo img {");
            htmlText.append(" margin: 0 auto !important;");
            htmlText.append(" }");
            htmlText.append(" .mobile-hide {");
            htmlText.append(" display: none !important;");
            htmlText.append(" }");
            htmlText.append(" .img-max {");
            htmlText.append(" max-width: 100% !important;");
            htmlText.append("  width: 100% !important;");
            htmlText.append("  height: auto !important;");
            htmlText.append(" }");
            htmlText.append(".responsive-table {");
            htmlText.append(" width: 100% !important;");
            htmlText.append(" }");
            htmlText.append(" .padding {");
            htmlText.append(" padding: 10px 5% 15px 5% !important;");
            htmlText.append(" }");
            htmlText.append(" .padding-meta {");
            htmlText.append(" padding: 30px 5% 0px 5% !important;");
            htmlText.append("  text-align: center;");
            htmlText.append("  }");
            htmlText.append("  .padding-copy {");
            htmlText.append(" padding: 10px 5% 10px 5% !important;");
            htmlText.append(" text-align: center;");
            htmlText.append(" }");
            htmlText.append(" .no-padding {");
            htmlText.append("  padding: 0 !important;");
            htmlText.append("  }");
            htmlText.append("  .section-padding {");
            htmlText.append("  padding: 50px 15px 50px 15px !important;");
            htmlText.append("  }");
            htmlText.append(" .mobile-button-container {");
            htmlText.append("  margin: 0 auto;");
            htmlText.append("  width: 100% !important;");
            htmlText.append(" }");
            htmlText.append(" .mobile-button {");
            htmlText.append(" padding: 15px !important;");
            htmlText.append("  border: 0 !important;");
            htmlText.append("  font-size: 16px !important;");
            htmlText.append("  display: block !important;");
            htmlText.append(" }");
            htmlText.append(" }");
            htmlText.append("  div[style*='margin: 16px 0;'] { margin: 0 !important; }");
            htmlText.append("</style>");
            htmlText.append(" </head>");
            htmlText.append(" <body style='margin: 0 !important; padding: 0 !important;'>");
            htmlText.append(" <div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica,  Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>");
            htmlText.append("   Opportunity "+flag+"");
            htmlText.append("    </div>");

            htmlText.append(" <table border='0' cellpadding='0' cellspacing='0' width='100%'>");
            htmlText.append("  <tr>");
            htmlText.append("   <td bgcolor='#ffffff' align='center'>");

            htmlText.append("     <table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:   500px;' class='wrapper'>");
            htmlText.append("       <tr>");
            htmlText.append("         <td align='center' valign='top' style='padding: 15px 0;' class='logo'>");
            htmlText.append("         <a href='http://www.miraclesoft.com/' target='_blank'>");
            htmlText.append("    <img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'>");
            htmlText.append("    </a>");
            htmlText.append("   </td>");
            htmlText.append("    </tr>");
            htmlText.append("    </table>");

            htmlText.append("    </td>");
            htmlText.append("    </tr>");


            htmlText.append("        <tr>");
            htmlText.append("  <td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
            htmlText.append(" <table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("    <tr>");
            htmlText.append("    <td>");

            htmlText.append("   <table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("      <tr>");
            if(flag.equalsIgnoreCase("Added")){
            htmlText.append("     <td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>New Opportunity "+flag+"</b></td>");
            } else{
                htmlText.append("     <td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b> Opportunity "+flag+"</b></td>");
            }
            htmlText.append("  </tr>");
            htmlText.append("   </table>");
            htmlText.append("   </td>");
            htmlText.append("  </tr>");
            htmlText.append("  </table>");

            htmlText.append("   </td>");
            htmlText.append("   </tr>");


            htmlText.append("    <tr>");
            htmlText.append("     <td bgcolor='#ffffff' align='center' style='padding: 5px;'>");

            htmlText.append("      <table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:    500px;' class='responsive-table'>");
            htmlText.append(" <tr>");
            htmlText.append("  <td>");
            htmlText.append("   <table width='100%' border='0' cellspacing='0' cellpadding='0'>");

            htmlText.append("   <tr>");
            htmlText.append("    <td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");

            htmlText.append("   Hello <b>Team,</b> ");
            htmlText.append("   <br> A New Opportunity has been assigned to you and following are the details of it.");
            htmlText.append("   </td>");
            htmlText.append("  </tr>");
            htmlText.append("   <tr>");
            htmlText.append("   <td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
            htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>Title:</b> " + title + " <br>");
            htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>Type:</b> " + type + " <br>");
            htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>State:</b> " + state + " <br>");
            htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>Practice Name:</b> " + practiceName + " <br>");
            htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>Due Date:</b> " + dueDate + " <br>");
            if(flag.equalsIgnoreCase("Added")){
              htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>CreatedBy:</b> " + modifiedBy + " <br>");     
            }else{
              htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>ModifiedBy:</b> " + modifiedBy + " <br>");    
            }
            if(flag.equalsIgnoreCase("Added")){
             if(PracticeMgrId!=-1 ){
               htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>PreSalesAssignedTo:</b> " + PracticeMgrName + " <br>"); 
                
             } 
              if(RegionalMgrId!=-1 )
                 {
               htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>PracticeSalesAssignedTo:</b> " + RegionalMgrName + " <br>"); 
                 }
               if(InsideSalesId!=-1 )
               {
               htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>InsideSalesAssignedTo:</b> " + InsideSalesName + " <br>"); 
                }
            }
           if(flag.equalsIgnoreCase("Updated")){
                if(PracticeMgrId!=-1 && PracticeMgrId!=PracticeMgrId1){
              htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>CurrentPreSales:</b> " + PracticeMgrName + " <br>"); 
                 htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>PreviousPreSales:</b> " + prevPracticeMgrName + " <br>"); 
                }
                 if(RegionalMgrId!=-1 && RegionalMgrId!=RegionalMgrId1)
                 {
               htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>CurrentPracticeSales:</b> " + RegionalMgrName + " <br>"); 
                  htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>PreviousPracticeSales:</b> " + prevRegionalMgrName + " <br>"); 
                 }
               if(InsideSalesId!=-1 && InsideSalesId!=InsideSalesId1)
               {
               htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>CurrentInsideSales:</b> " + InsideSalesName + " <br>"); 
                htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>PreviousInsideSales:</b> " + prevInsideSalesName + " <br>"); 
               }
               }
            htmlText.append("  </td>");
            htmlText.append("  </tr>");
            htmlText.append("  </table>");
            htmlText.append("  </td>");
            htmlText.append("  </tr>");

            htmlText.append("  <tr>");
            htmlText.append("   <td>");
            htmlText.append("  <table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("   <tr>");
            htmlText.append("     <td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>");


            htmlText.append("    Thanks & Regards,<br>");
            htmlText.append("    Corporate Application Support Team, <br>");
            htmlText.append("    Miracle Software Systems, Inc. <br>");
            htmlText.append("   Email: hubble@miraclesoft.com <br>");
            htmlText.append("   Phone: (+1)248-233-1814");
            htmlText.append("      </td>");
            htmlText.append("   </tr>");
            htmlText.append("   </table>");
            htmlText.append("   </td>");
            htmlText.append("  </tr>");
            htmlText.append("   <tr>");
            htmlText.append("    <td>");

            htmlText.append("    <table width=' 100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("       <tr>");
            htmlText.append("      <td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>");
            htmlText.append("       *Note: Please do not reply to this email as this is an automated notification");
            htmlText.append(" </td>");
            htmlText.append("   </tr>");
            htmlText.append("     </table>");
            htmlText.append("   </td>");
            htmlText.append("  </tr>");
            htmlText.append("   </table>");

            htmlText.append("      </td>");
            htmlText.append("   </tr>");
            htmlText.append("   <tr>");
            htmlText.append("      <td bgcolor='#ffffff' align='center' style='padding:15px 0px;   '>");
            htmlText.append("     <table width=' 100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max - width : 500px;  ' class=' responsive - table '>");

            htmlText.append("       <tr>");
            htmlText.append(" <td width='200' align='center' style='text-align: center;'>");
            htmlText.append(" <table width='200' cellpadding='0' cellspacing='0' align='center'>");
            htmlText.append(" <tr>");
            htmlText.append(" <td width='10'>  <a href='https://www.facebook.com/miracle45625' target='_blank'> <img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' >  </a> </td>");
            htmlText.append(" <td width='10'>  <a href='https://plus.google.com/+Team_MSS/videos' target='_blank'> <img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' >  </a> </td>");
            htmlText.append(" <td width='10'>  <a href='https://www.youtube.com/c/Team_MSS' target='_blank'> <img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' >  </a> </td>");
            htmlText.append(" <td width='10'>  <a href='https://twitter.com/Team_MSSs' target='_blank'> <img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' >  </a> </td>");

            htmlText.append("     </tr>");
            htmlText.append("    </table>");
            htmlText.append("   </td>");
            htmlText.append("    </tr>");


            htmlText.append("  <tr>");
            htmlText.append("   <td height='10'>");
            htmlText.append("   </td>");
            htmlText.append("  </tr>");
            htmlText.append("  <tr>");
            htmlText.append("    <td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>");
            htmlText.append("      &copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems");
            htmlText.append("    <br>");
            htmlText.append("  </td>");
            htmlText.append("   </tr>");
            htmlText.append("   </table>");

            htmlText.append("   </td>");
            htmlText.append("    </tr>");
            htmlText.append("  </table>");
            htmlText.append("   </body>");
            htmlText.append(" </html>");

            ServiceLocator.getMailServices().doAddEmailLogNew(to, "", subject, htmlText.toString(), createdDate.toString(), "", "Opprotunity Notification");

        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }

//Apprecation email end    
    
   

    /* Send a mail to the employees When the TEF is Added or Updated By the Payroll Team */
    public void sendTEFStatusMailToEmp(String overLayStatus, String empId, double overlayApprovedAmount,String flag,String exemptionName,double savingsAmount,String tefType,String comments) throws ServiceLocatorException {
     //   System.out.println("sendTEFStatusMailToEmp"); 
        String from = "hubbleapp@miraclesoft.com";
         
String to = DataSourceDataProvider.getInstance().getEmailIdForEmployee(Integer.parseInt(empId));
     
       String empName = DataSourceDataProvider.getInstance().getEmpNameByEmpId(Integer.parseInt(empId)); // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
// System.out.println(empName+"----> to "+to);
        Authenticator auth = new SMTPAuthenticator();

        try {
       
             String subject ="";
             if(flag.equalsIgnoreCase("updated")){
                subject = exemptionName+" Exemption was "+overLayStatus;    
             }
          
            MimeMultipart multipart = new MimeMultipart("related");
            
         
             BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
        

            htmlText.append("<!DOCTYPE html>");
            htmlText.append("<html>");
            htmlText.append("  <head>");
            htmlText.append("   <meta charset='utf-8'>");
            htmlText.append("   <meta name='viewport' content='width=device-width, initial-scale=1'>");
            htmlText.append(" <meta http-equiv='X-UA-Compatible' content='IE=edge' />");
            htmlText.append("  <style type='text/css'>");

            htmlText.append("    body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;} ");
            htmlText.append("   table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;} ");
            htmlText.append(" img{-ms-interpolation-mode: bicubic;} ");

            htmlText.append("  img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}");
            htmlText.append("  table{border-collapse: collapse !important;}");
            htmlText.append(" body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}");

            htmlText.append(" a[x-apple-data-detectors] {");
            htmlText.append(" color: inherit !important;");
            htmlText.append(" text-decoration: none !important;");
            htmlText.append("  font-size: inherit !important;");
            htmlText.append("  font-family: inherit !important;");
            htmlText.append("  font-weight: inherit !important;");
            htmlText.append("    line-height: inherit !important;");
            htmlText.append("   }");
            htmlText.append("  @media screen and (max-width: 525px) {");
            htmlText.append(" .wrapper {");
            htmlText.append(" width: 100% !important;");
            htmlText.append(" max-width: 100% !important;");
            htmlText.append(" }");
            htmlText.append(" .logo img {");
            htmlText.append(" margin: 0 auto !important;");
            htmlText.append(" }");
            htmlText.append(" .mobile-hide {");
            htmlText.append(" display: none !important;");
            htmlText.append(" }");
            htmlText.append(" .img-max {");
            htmlText.append(" max-width: 100% !important;");
            htmlText.append("  width: 100% !important;");
            htmlText.append("  height: auto !important;");
            htmlText.append(" }");
            htmlText.append(".responsive-table {");
            htmlText.append(" width: 100% !important;");
            htmlText.append(" }");
            htmlText.append(" .padding {");
            htmlText.append(" padding: 10px 5% 15px 5% !important;");
            htmlText.append(" }");
            htmlText.append(" .padding-meta {");
            htmlText.append(" padding: 30px 5% 0px 5% !important;");
            htmlText.append("  text-align: center;");
            htmlText.append("  }");
            htmlText.append("  .padding-copy {");
            htmlText.append(" padding: 10px 5% 10px 5% !important;");
            htmlText.append(" text-align: center;");
            htmlText.append(" }");
            htmlText.append(" .no-padding {");
            htmlText.append("  padding: 0 !important;");
            htmlText.append("  }");
            htmlText.append("  .section-padding {");
            htmlText.append("  padding: 50px 15px 50px 15px !important;");
            htmlText.append("  }");
            htmlText.append(" .mobile-button-container {");
            htmlText.append("  margin: 0 auto;");
            htmlText.append("  width: 100% !important;");
            htmlText.append(" }");
            htmlText.append(" .mobile-button {");
            htmlText.append(" padding: 15px !important;");
            htmlText.append("  border: 0 !important;");
            htmlText.append("  font-size: 16px !important;");
            htmlText.append("  display: block !important;");
            htmlText.append(" }");
            htmlText.append(" }");
            htmlText.append("  div[style*='margin: 16px 0;'] { margin: 0 !important; }");
            htmlText.append("</style>");
            htmlText.append(" </head>");
            htmlText.append(" <body style='margin: 0 !important; padding: 0 !important;'>");
            htmlText.append(" <div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica,  Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>");
            htmlText.append("   Tax Exemption "+exemptionName+""+overLayStatus+"");
            htmlText.append("    </div>");

            htmlText.append(" <table border='0' cellpadding='0' cellspacing='0' width='100%'>");
            htmlText.append("  <tr>");
            htmlText.append("   <td bgcolor='#ffffff' align='center'>");

            htmlText.append("     <table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:   500px;' class='wrapper'>");
            htmlText.append("       <tr>");
            htmlText.append("         <td align='center' valign='top' style='padding: 15px 0;' class='logo'>");
            htmlText.append("         <a href='http://www.miraclesoft.com/' target='_blank'>");
            htmlText.append("    <img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'>");
            htmlText.append("    </a>");
            htmlText.append("   </td>");
            htmlText.append("    </tr>");
            htmlText.append("    </table>");

            htmlText.append("    </td>");
            htmlText.append("    </tr>");


            htmlText.append("        <tr>");
            htmlText.append("  <td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
            htmlText.append(" <table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("    <tr>");
            htmlText.append("    <td>");

            htmlText.append("   <table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("      <tr>");
          
                htmlText.append("     <td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b> Tax Exemption "+flag+"</b></td>");
          
            htmlText.append("  </tr>");
            htmlText.append("   </table>");
            htmlText.append("   </td>");
            htmlText.append("  </tr>");
            htmlText.append("  </table>");

            htmlText.append("   </td>");
            htmlText.append("   </tr>");


            htmlText.append("    <tr>");
            htmlText.append("     <td bgcolor='#ffffff' align='center' style='padding: 5px;'>");

            htmlText.append("      <table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:    500px;' class='responsive-table'>");
            htmlText.append(" <tr>");
            htmlText.append("  <td>");
            htmlText.append("   <table width='100%' border='0' cellspacing='0' cellpadding='0'>");

            htmlText.append("   <tr>");
            htmlText.append("    <td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");

         
                htmlText.append("   Hello <b>"+empName+",</b> ");
            htmlText.append("   <br>  Tax Exemption -"+exemptionName+" has been "+overLayStatus);
            htmlText.append("   </td>");
            htmlText.append("  </tr>");
            htmlText.append("   <tr>");
            htmlText.append("   <td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
             htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>Applied Amount :</b> " + savingsAmount + " <br>");
             htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>"+overLayStatus+" Amount :</b> " + overlayApprovedAmount + " <br>");
              htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>Saving Type :</b> " + tefType + " <br>");
        htmlText.append(" <b style='font - size : 14px;  color: #ef4048;  '>Comments :</b> " + comments + " <br>");
          

            htmlText.append("  </td>");
            htmlText.append("  </tr>");
            htmlText.append("  </table>");
            htmlText.append("  </td>");
            htmlText.append("  </tr>");

            htmlText.append("  <tr>");
            htmlText.append("   <td>");
            htmlText.append("  <table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("   <tr>");
            htmlText.append("     <td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>");


            htmlText.append("    Thanks & Regards,<br>");
            htmlText.append("    Corporate Application Support Team, <br>");
            htmlText.append("    Miracle Software Systems, Inc. <br>");
            htmlText.append("   Email: hubble@miraclesoft.com <br>");
            htmlText.append("   Phone: (+1)248-233-1814");
            htmlText.append("      </td>");
            htmlText.append("   </tr>");
            htmlText.append("   </table>");
            htmlText.append("   </td>");
            htmlText.append("  </tr>");
            htmlText.append("   <tr>");
            htmlText.append("    <td>");

            htmlText.append("    <table width=' 100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("       <tr>");
            htmlText.append("      <td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>");
            htmlText.append("       *Note: Please do not reply to this email as this is an automated notification");
            htmlText.append(" </td>");
            htmlText.append("   </tr>");
            htmlText.append("     </table>");
            htmlText.append("   </td>");
            htmlText.append("  </tr>");
            htmlText.append("   </table>");

            htmlText.append("      </td>");
            htmlText.append("   </tr>");
            htmlText.append("   <tr>");
            htmlText.append("      <td bgcolor='#ffffff' align='center' style='padding:15px 0px;   '>");
            htmlText.append("     <table width=' 100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max - width : 500px;  ' class=' responsive - table '>");

            htmlText.append("       <tr>");
            htmlText.append(" <td width='200' align='center' style='text-align: center;'>");
            htmlText.append(" <table width='200' cellpadding='0' cellspacing='0' align='center'>");
            htmlText.append(" <tr>");
            htmlText.append(" <td width='10'>  <a href='https://www.facebook.com/miracle45625' target='_blank'> <img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' >  </a> </td>");
            htmlText.append(" <td width='10'>  <a href='https://plus.google.com/+Team_MSS/videos' target='_blank'> <img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' >  </a> </td>");
            htmlText.append(" <td width='10'>  <a href='https://www.youtube.com/c/Team_MSS' target='_blank'> <img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' >  </a> </td>");
            htmlText.append(" <td width='10'>  <a href='https://twitter.com/Team_MSSs' target='_blank'> <img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' >  </a> </td>");

            htmlText.append("     </tr>");
            htmlText.append("    </table>");
            htmlText.append("   </td>");
            htmlText.append("    </tr>");


            htmlText.append("  <tr>");
            htmlText.append("   <td height='10'>");
            htmlText.append("   </td>");
            htmlText.append("  </tr>");
            htmlText.append("  <tr>");
            htmlText.append("    <td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>");
            htmlText.append("      &copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems");
            htmlText.append("    <br>");
            htmlText.append("  </td>");
            htmlText.append("   </tr>");
            htmlText.append("   </table>");

            htmlText.append("   </td>");
            htmlText.append("    </tr>");
            htmlText.append("  </table>");
            htmlText.append("   </body>");
            htmlText.append(" </html>");
   Timestamp createdDate = DateUtility.getInstance().getCurrentMySqlDateTime();
            ServiceLocator.getMailServices().doAddEmailLogNew(to, "", subject, htmlText.toString(), createdDate.toString(), "", "Payroll-TENotification");

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
    
    
    //-----------------
     public static void sendPSCERStatusMailsToSalesAndPreSales(String previousStage, String currentStage, String RequestorName,String comments,String userName,String PreSalesEmails) throws ServiceLocatorException {
        //  System.out.println("sendOpportunityMailToPracticeSalesAndPreSales..");
//        System.out.println("sendPSCERStatusMailsToSalesAndPreSales..");
//        System.out.println("previousStage---" + previousStage);
//        System.out.println("currentStage---" + currentStage);
//        System.out.println("RequestorName---" + RequestorName);

     
        String from = "hubbleapp@miraclesoft.com";

        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!

        Authenticator auth = new SMTPAuthenticator();

        try {
            //       String InsideSalesName = "", PracticeMgrName = "", RegionalMgrName = "", prevInsideSalesName = "", prevPracticeMgrName = "", prevRegionalMgrName = "";
            String subject = "";
            String title = "";
            MimeMultipart multipart = new MimeMultipart("related");
            DataSourceDataProvider dataSourceDataProvider = DataSourceDataProvider.getInstance();
            String RequestorFullName = dataSourceDataProvider.getemployeenamebyloginId(RequestorName);

            StringBuilder htmlText = new StringBuilder();
String requestTypeSubject="";
       
             requestTypeSubject="Client Engagement Request";
           
            if ("Submitted".equals(currentStage)) {
                title = requestTypeSubject+" Submitted";
               if("".equals(userName)){
              subject="Reg: "+requestTypeSubject +" Submitted";
            }else{
             subject="Reg: Updation of "+ requestTypeSubject +" by "+userName;
            }
            } else if ("Reviewed".equals(currentStage)) {
                title = requestTypeSubject+" Reviewed";
                    if("".equals(userName)){
              subject="Reg: "+ requestTypeSubject +" Reviewed";
            }else{
             subject="Reg: Updation of "+requestTypeSubject+" by "+userName;
            }
               
            } else  if ("Approved".equals(currentStage)) {
                title = requestTypeSubject+" Approved";
                 if("".equals(userName)){
           subject="Reg: "+requestTypeSubject+" Approved";
            }else{
             subject="Reg: Updation of "+requestTypeSubject+" by "+userName;
            }
               
            } else if ("Rejected".equals(currentStage)) {
                title = requestTypeSubject+" Rejected";
                 if("".equals(userName)){
           subject="Reg: "+requestTypeSubject+" Rejected";
            }else{
             subject="Reg: Updation of "+requestTypeSubject+" by "+userName;
            }
               
            }
            String requestType="PSCER";
            htmlText.append("<!DOCTYPE html><html><head><meta charset='utf-8'>");
            htmlText.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
            htmlText.append("<meta http-equiv='X-UA-Compatible' content='IE=edge' />");
            htmlText.append("<style type='text/css'>body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;}table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;}img{-ms-interpolation-mode: bicubic;}img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}table{border-collapse: collapse !important;}body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}a[x-apple-data-detectors] {color: inherit !important;text-decoration: none !important;font-size: inherit !important;font-family: inherit !important;font-weight: inherit !important;line-height: inherit !important;}@media screen and (max-width: 525px) { .wrapper {width: 100% !important;max-width: 100% !important;}.logo img {margin: 0 auto !important;} .mobile-hide {display: none !important;} .img-max {max-width: 100% !important;width: 100% !important; height: auto !important; }.responsive-table {width: 100% !important;}.padding {padding: 10px 5% 15px 5% !important;}.padding-meta {padding: 30px 5% 0px 5% !important;text-align: center;}.padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}.no-padding {padding: 0 !important;}.section-padding {padding: 50px 15px 50px 15px !important;}.mobile-button-container {margin: 0 auto;width: 100% !important;}.mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important;display: block !important;} }</style>");
            htmlText.append("</head><body style='margin: 0 !important; padding: 0 !important;'>");
            //htmlText.append("<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>Entice the open with some amazing preheader text. Use a little mystery and get those subscribers to read through...</div>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
            htmlText.append("<tr><td bgcolor='#ffffff' align='center'><table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper'>");
            htmlText.append("<tr><td align='center' valign='top' style='padding: 15px 0;' class='logo'>");
            htmlText.append("<a href='http://www.miraclesoft.com/' target='_blank'>");
            htmlText.append("<img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'>");
            htmlText.append("</a></td></tr></table></td></tr><tr><td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("<tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>" + title + "</b>");
            htmlText.append("</td></tr></table></td></tr></table></td></tr><tr><td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("<tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>" + RequestorFullName + ",</b>");
            if ("Submitted".equals(currentStage)) {
                htmlText.append("<br>Thank you for following the "+requestType+" process. Your request has been successfully submitted. You will receive notification once the status of the request is updated. If you have any questions or concerns, please reach out to <a href='https://www.PSCER@miraclesoft.com' target='_blank'>PSCER@miraclesoft.com.  </a>      </td></tr>");
            } else if ("Reviewed".equals(currentStage)) {
                htmlText.append("<br>Your request has been reviewed. Thank you for your patience and will assign a PreSales person to assist you with your Client Meeting here shortly. If you have any questions or concerns, please reach out to <a href='https://www.PSCER@miraclesoft.com' target='_blank'>PSCER@miraclesoft.com.     </td></tr>");
            } else  if ("Approved".equals(currentStage)) {
                htmlText.append("<br>Your request has been completed Please log into Hubble, check your "+requestType+" request, and update the Opportunity with the PreSales member assigned to the "+requestType+" request. Please create and send the meeting invite to the selected PreSales Members. Thank you for following the "+requestType+" process!</td></tr>");
                htmlText.append("<tr><td style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><p>Steps:</p>");
                htmlText.append("<ol>");
                htmlText.append("<li>Check which PreSales members have been assigned to your call.</li>");
                htmlText.append("<li>Update the Opportunity associated with the "+requestType+" and enter the PreSales member from the "+requestType+".</li>");
                htmlText.append(" <li>Create and Send a meeting invite to the selected PreSales Members. </li>");
                htmlText.append("</ol></td></tr>");
            } else if ("Rejected".equals(currentStage)) {
                htmlText.append("<br>Your request has been Rejected.  If you have any questions or concerns, please reach out to <a href='mailto:PSCER@miraclesoft.com' target='_blank'>PSCER@miraclesoft.com.</td></tr>");
            }
            
htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Comments:"+comments+"</td></td>");

            htmlText.append("<tr><td align='justify' style='padding: 5px 0 5px 0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
            htmlText.append("</td></tr></tr></table></td></tr><tr><td>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>");
            htmlText.append("Thanks,<br>PreSales Client Engagements</td></tr>");
            htmlText.append("</table></td></tr><tr><td> <table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' ");
            htmlText.append("class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td></tr></table>");
            htmlText.append("</td></tr></table></td></tr><tr><td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' ");
            htmlText.append("class='responsive-table'><tr><td width='200' align='center' style='text-align: center;'>");
            htmlText.append("<table width='200' cellpadding='0' cellspacing='0' align='center'><tr><td width='10'>");
            htmlText.append("<a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' ");
            htmlText.append("alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td><td width='10'>");
            htmlText.append("<a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' ");
            htmlText.append("alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td><td width='10'>");
            htmlText.append("<a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'>");
            htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' >");
            htmlText.append("</a></td><td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'>");
            htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td><td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'>");
            htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td></tr></table></td></tr><tr><td height='10'></td></tr>");
            htmlText.append("<tr><td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems<br>");
            htmlText.append("</td></tr></table></td></tr></table></body></html>");
           String cc = com.mss.mirage.util.Properties.getProperty("PSCER.CC");
              if(PreSalesEmails.trim().length()>0){
              cc=cc+","+PreSalesEmails;
              }
            String to = dataSourceDataProvider.getEmailIdForLoginId(RequestorName);
            //String to = dataSourceDataProvider.getemployeenamebyloginId(RequestorName);
            Timestamp createdDate = DateUtility.getInstance().getCurrentMySqlDateTime();
           // System.out.println("subject  "+subject);
            ServiceLocator.getMailServices().doAddEmailLogNew(to, cc, subject, htmlText.toString(), createdDate.toString(), "", ""+requestType+" Notification");

        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }
    
     public static void sendRFPStatusMailsToSalesAndPreSales(String previousStage, String currentStage, String RequestorName,String comments,String userName,String PreSalesEmails) throws ServiceLocatorException {
        //  System.out.println("sendOpportunityMailToPracticeSalesAndPreSales..");
//        System.out.println("sendPSCERStatusMailsToSalesAndPreSales..");
//        System.out.println("previousStage---" + previousStage);
//        System.out.println("currentStage---" + currentStage);
//        System.out.println("RequestorName---" + RequestorName);

     
        String from = "hubbleapp@miraclesoft.com";

        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!

        Authenticator auth = new SMTPAuthenticator();

        try {
            //       String InsideSalesName = "", PracticeMgrName = "", RegionalMgrName = "", prevInsideSalesName = "", prevPracticeMgrName = "", prevRegionalMgrName = "";
            String subject = "";
            String title = "";
            MimeMultipart multipart = new MimeMultipart("related");
            DataSourceDataProvider dataSourceDataProvider = DataSourceDataProvider.getInstance();
            String RequestorFullName = dataSourceDataProvider.getemployeenamebyloginId(RequestorName);

            StringBuilder htmlText = new StringBuilder();
String requestTypeSubject="";
          
               requestTypeSubject="Requests For Proposals (RFP's)";
            
            if ("Submitted".equals(currentStage)) {
                title = requestTypeSubject+" Submitted";
               if("".equals(userName)){
              subject="Reg: "+requestTypeSubject +" Submitted";
            }else{
             subject="Reg: Updation of "+ requestTypeSubject +" by "+userName;
            }
            } else if ("Reviewed".equals(currentStage)) {
                title = requestTypeSubject+" Reviewed";
                    if("".equals(userName)){
              subject="Reg:"+ requestTypeSubject +" Reviewed";
            }else{
             subject="Reg: Updation of "+requestTypeSubject+" by "+userName;
            }
               
            } else  if ("Approved".equals(currentStage)) {
                title = requestTypeSubject+" Approved";
                 if("".equals(userName)){
           subject="Reg: "+requestTypeSubject+" Approved";
            }else{
             subject="Reg: Updation of "+requestTypeSubject+" by "+userName;
            }
               
            } else if ("Rejected".equals(currentStage)) {
                title = requestTypeSubject+" Rejected";
                 if("".equals(userName)){
           subject="Reg: "+requestTypeSubject+" Rejected";
            }else{
             subject="Reg:Updation of "+requestTypeSubject+" by "+userName;
            }
               
            }
            String requestType="RFP";
            htmlText.append("<!DOCTYPE html><html><head><meta charset='utf-8'>");
            htmlText.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
            htmlText.append("<meta http-equiv='X-UA-Compatible' content='IE=edge' />");
            htmlText.append("<style type='text/css'>body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;}table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;}img{-ms-interpolation-mode: bicubic;}img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}table{border-collapse: collapse !important;}body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}a[x-apple-data-detectors] {color: inherit !important;text-decoration: none !important;font-size: inherit !important;font-family: inherit !important;font-weight: inherit !important;line-height: inherit !important;}@media screen and (max-width: 525px) { .wrapper {width: 100% !important;max-width: 100% !important;}.logo img {margin: 0 auto !important;} .mobile-hide {display: none !important;} .img-max {max-width: 100% !important;width: 100% !important; height: auto !important; }.responsive-table {width: 100% !important;}.padding {padding: 10px 5% 15px 5% !important;}.padding-meta {padding: 30px 5% 0px 5% !important;text-align: center;}.padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}.no-padding {padding: 0 !important;}.section-padding {padding: 50px 15px 50px 15px !important;}.mobile-button-container {margin: 0 auto;width: 100% !important;}.mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important;display: block !important;} }</style>");
            htmlText.append("</head><body style='margin: 0 !important; padding: 0 !important;'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
            htmlText.append("<tr><td bgcolor='#ffffff' align='center'><table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper'>");
            htmlText.append("<tr><td align='center' valign='top' style='padding: 15px 0;' class='logo'>");
            htmlText.append("<a href='http://www.miraclesoft.com/' target='_blank'>");
            htmlText.append("<img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'>");
            htmlText.append("</a></td></tr></table></td></tr><tr><td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("<tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>" + title + "</b>");
            htmlText.append("</td></tr></table></td></tr></table></td></tr><tr><td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("<tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>" + RequestorFullName + ",</b>");
            if ("Submitted".equals(currentStage)) {
                htmlText.append("<br>Thank you for following the "+requestType+" process. Your request has been successfully submitted. You will receive notification once the status of the request is updated. If you have any questions or concerns, please reach out to <a href='https://www.PSCER@miraclesoft.com' target='_blank'>PSCER@miraclesoft.com.  </a>      </td></tr>");
            } else if ("Reviewed".equals(currentStage)) {
                htmlText.append("<br>Your request has been reviewed. Thank you for your patience and will assign a PreSales person to assist you with your "+requestType+" here shortly. . If you have any questions or concerns, please reach out to <a href='https://www.PSCER@miraclesoft.com' target='_blank'>PSCER@miraclesoft.com.     </td></tr>");
            } else  if ("Approved".equals(currentStage)) {
                htmlText.append("<br>Your request has been completed Please log into Hubble, check your "+requestType+" request, and update the Opportunity with the PreSales member assigned to the "+requestType+" request. Please create and send the meeting invite to the selected PreSales Members to discuss the "+requestType+". Thank you for following the "+requestType+" process!</td></tr>");
                htmlText.append("<tr><td style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><p>Steps:</p>");
                htmlText.append("<ol>");
                htmlText.append("<li>Check which PreSales members have been assigned to your request.</li>");
                htmlText.append("<li>Update the Opportunity associated with the "+requestType+" and enter the PreSales member.</li>");
                htmlText.append(" <li>3. Create and Send a meeting invite to the selected PreSales member to discuss the "+requestType+". </li>");
                htmlText.append("</ol></td></tr>");
            } else if ("Rejected".equals(currentStage)) {
                htmlText.append("<br>Your request has been Rejected.  If you have any questions or concerns, please reach out to <a href='mailto:PSCER@miraclesoft.com' target='_blank'>PSCER@miraclesoft.com.</td></tr>");
            }
            
htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Comments:"+comments+"</td></td>");

            htmlText.append("<tr><td align='justify' style='padding: 5px 0 5px 0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
            htmlText.append("</td></tr></tr></table></td></tr><tr><td>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>");
            htmlText.append("Thanks,<br>PreSales Client Engagements</td></tr>");
            htmlText.append("</table></td></tr><tr><td> <table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' ");
            htmlText.append("class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td></tr></table>");
            htmlText.append("</td></tr></table></td></tr><tr><td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' ");
            htmlText.append("class='responsive-table'><tr><td width='200' align='center' style='text-align: center;'>");
            htmlText.append("<table width='200' cellpadding='0' cellspacing='0' align='center'><tr><td width='10'>");
            htmlText.append("<a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' ");
            htmlText.append("alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td><td width='10'>");
            htmlText.append("<a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' ");
            htmlText.append("alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td><td width='10'>");
            htmlText.append("<a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'>");
            htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' >");
            htmlText.append("</a></td><td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'>");
            htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td><td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'>");
            htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td></tr></table></td></tr><tr><td height='10'></td></tr>");
            htmlText.append("<tr><td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems<br>");
            htmlText.append("</td></tr></table></td></tr></table></body></html>");
          
            String cc = com.mss.mirage.util.Properties.getProperty("PSCER.CC");
              if(PreSalesEmails.trim().length()>0){
              cc=cc+","+PreSalesEmails;
              }
            String to = dataSourceDataProvider.getEmailIdForLoginId(RequestorName);
            
            Timestamp createdDate = DateUtility.getInstance().getCurrentMySqlDateTime();
        
            ServiceLocator.getMailServices().doAddEmailLogNew(to, cc, subject, htmlText.toString(), createdDate.toString(), "", ""+requestType+" Notification");

        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
public static void sendPSCER_RFP_StatusMailsToPreSales(String toEmail,String toName,String RequestorName, String AccountName, String currentStage,String comments,String userName,String meetingType,String PreSalesEmails,String requestType,String meetingDate,String meetingTime) throws ServiceLocatorException {
   
     
        String from = "hubbleapp@miraclesoft.com";

        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!

     

        try {
           
            String subject = "";
            String title = "";
            MimeMultipart multipart = new MimeMultipart("related");
            DataSourceDataProvider dataSourceDataProvider = DataSourceDataProvider.getInstance();
            String RequestorFullName = dataSourceDataProvider.getemployeenamebyloginId(RequestorName);

            StringBuilder htmlText = new StringBuilder();
String requestTypeSubject="";
          
if(requestType.equalsIgnoreCase("PSCER")){
                          requestTypeSubject="Client Engagement Request";
}else{
               requestTypeSubject="Requests For Proposals (RFP's)";}
            
title=requestTypeSubject;
subject="Reg: "+requestTypeSubject +" Added";

//            if ("Submitted".equals(currentStage)) {
//                title = requestTypeSubject+" Submitted";
//               if("".equals(userName)){
//              subject="Reg:"+requestTypeSubject +" Submitted";
//            }else{
//             subject="Reg:Updation of "+ requestTypeSubject +" by "+userName;
//            }
//            } else if ("Reviewed".equals(currentStage)) {
//                title = requestTypeSubject+" Reviewed";
//                    if("".equals(userName)){
//              subject="Reg:"+ requestTypeSubject +" Reviewed";
//            }else{
//             subject="Reg:Updation of "+requestTypeSubject+" by "+userName;
//            }
//               
//            } else  if ("Approved".equals(currentStage)) {
//                title = requestTypeSubject+" Approved";
//                 if("".equals(userName)){
//           subject="Reg:"+requestTypeSubject+" Approved";
//            }else{
//             subject="Reg:Updation of "+requestTypeSubject+" by "+userName;
//            }
//               
//            } else if ("Rejected".equals(currentStage)) {
//                title = requestTypeSubject+" Rejected";
//                 if("".equals(userName)){
//           subject="Reg:"+requestTypeSubject+" Rejected";
//            }else{
//             subject="Reg:Updation of "+requestTypeSubject+" by "+userName;
//            }
//               
//            }
            
           htmlText.append("<!DOCTYPE html>");
htmlText.append("<html>");
htmlText.append("<head>");
htmlText.append("<meta charset='utf-8'>");
htmlText.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
htmlText.append("<meta http-equiv='X-UA-Compatible' content='IE=edge' />");
htmlText.append("<style type='text/css'>");
htmlText.append("body, table, td, ");
htmlText.append("a{");
htmlText.append("-webkit-text-size-adjust: 100%; ");
htmlText.append("-ms-text-size-adjust: 100%;}");
htmlText.append("table,td{");
htmlText.append("mso-table-lspace: 0pt; ");
htmlText.append("mso-table-rspace: 0pt;");
htmlText.append("}");
htmlText.append("img{");
htmlText.append("-ms-interpolation-mode: bicubic;");
htmlText.append("}");
htmlText.append("img{");
htmlText.append("border: 0; ");
htmlText.append("height: auto; ");
htmlText.append("line-height: 100%; ");
htmlText.append("outline: none; ");
htmlText.append("text-decoration: none;");
htmlText.append("}");
htmlText.append("table{");
htmlText.append("border-collapse: collapse !important;");
htmlText.append("}");
htmlText.append("body{");
htmlText.append("height: 100% !important; ");
htmlText.append("margin: 0 !important; ");
htmlText.append("padding: 0 !important; ");
htmlText.append("width: 100% !important;");
htmlText.append("}");
htmlText.append("a[x-apple-data-detectors] {");
htmlText.append("color: inherit !important;");
htmlText.append("text-decoration: none !important;");
htmlText.append("font-size: inherit !important;font-family: inherit !important;");
htmlText.append("font-weight: inherit !important;");
htmlText.append("line-height: inherit !important;");
htmlText.append("}");
htmlText.append("@media screen and (max-width: 525px) {");
htmlText.append(" .wrapper {width: 100% !important;max-width: 100% !important;");
htmlText.append(" }");
htmlText.append(" .logo img {");
htmlText.append(" margin: 0 auto !important;");
htmlText.append(" } ");
htmlText.append(" .mobile-hide {d");
htmlText.append(" isplay: none !important;");
htmlText.append(" } ");
htmlText.append(" .img-max {");
htmlText.append(" max-width: 100% !important;");
htmlText.append(" width: 100% !important; ");
htmlText.append(" height: auto !important;");
htmlText.append(" }");
htmlText.append(" .responsive-table {");
htmlText.append(" width: 100% !important;");
htmlText.append(" }");
htmlText.append(" .padding {");
htmlText.append(" padding: 10px 5% 15px 5% !important;");
htmlText.append(" }");
htmlText.append(" .padding-meta {");
htmlText.append(" padding: 30px 5% 0px 5% !important;");
htmlText.append(" text-align: center;");
htmlText.append(" }");
htmlText.append(" .padding-copy {");
htmlText.append(" padding: 10px 5% 10px 5% !important;");
htmlText.append(" text-align: center;");
htmlText.append(" }");
htmlText.append(" .no-padding {");
htmlText.append(" padding: 0 !important;");
htmlText.append(" }");
htmlText.append(" .section-padding {");
htmlText.append(" padding: 50px 15px 50px 15px !important;");
htmlText.append(" }");
htmlText.append(" .mobile-button-container {");
htmlText.append(" margin: 0 auto;");
htmlText.append(" width: 100% !important;");
htmlText.append(" }");
htmlText.append(" .mobile-button {");
htmlText.append(" padding: 15px !important;");
htmlText.append(" border: 0 !important;font-size: 16px !important;");
htmlText.append(" display: block !important;");
htmlText.append(" }");
htmlText.append(" }");
htmlText.append("</style></head>");
htmlText.append("<body style='margin: 0 !important; padding: 0 !important;'><table border='0' cellpadding='0' cellspacing='0' width='100%'>");
htmlText.append("<tr><td bgcolor='#ffffff' align='center'>");
htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper'>");
htmlText.append("<tr><td align='center' valign='top' style='padding: 15px 0;' class='logo'>");
htmlText.append("<a href='http://www.miraclesoft.com/' target='_blank'><img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'></a></td></tr></table></td></tr>");
htmlText.append("<tr><td bgcolor='#ffffff' align='center' style='padding: 5px;'><table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
htmlText.append("<tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>"+title+"</b></td></tr></table></td></tr></table></td></tr>");
htmlText.append("<tr><td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");
htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
htmlText.append("<tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'>");
htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b>Hello "+toName+",</b>");
htmlText.append("<br> You have added to below PSER.</td></tr>");
htmlText.append("<tr><td style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b>Requestor Name:</b> "+RequestorFullName+"<br/>");
htmlText.append("							  <b>Client Name:</b> "+AccountName+"<br/>");
htmlText.append("							  <b>Stage:</b> "+currentStage+"<br/>");
if(requestType.equalsIgnoreCase("PSCER")){
htmlText.append("							  <b>Meeting type:</b> "+meetingType+"<br/>");
htmlText.append("							  <b>Meeting Date/Time:</b> "+meetingDate+" "+meetingTime+"<br/>");}
htmlText.append("							  <b>Resources :</b> "+PreSalesEmails+"<br/>");
htmlText.append("							  <b>Additional Comment(s):</b> "+comments+"<br/>");
htmlText.append("							  ");
htmlText.append("							  </td></tr><tr></td>");
htmlText.append("<tr><td align='justify' style='padding: 5px 0 5px 0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'></td></tr></tr></table></td></tr>");
htmlText.append("<tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>Thanks & Regards,<br/><b>PreSales Client Engagements</b><br/>");
htmlText.append("							 ");
htmlText.append("							  </font></td></tr></table></td></tr>");
htmlText.append("<tr><td> <table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td></tr></table></td></tr></table></td></tr><tr><td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");
htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' class='responsive-table'><tr><td width='200' align='center' style='text-align: center;'><table width='200' cellpadding='0' cellspacing='0' align='center'><tr><td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
htmlText.append("<td width='10'><a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td><td width='10'><a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td><td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td><td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td></tr></table></td></tr>");
htmlText.append("<tr><td height='10'></td></tr><tr><td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems<br></td></tr></table></td></tr></table></body></html>");

            String cc = com.mss.mirage.util.Properties.getProperty("PSCER.CC");
             
            
            Timestamp createdDate = DateUtility.getInstance().getCurrentMySqlDateTime();
         
            ServiceLocator.getMailServices().doAddEmailLogNew(toEmail, cc, subject, htmlText.toString(), createdDate.toString(), "", ""+requestType+" Notification");

        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }
    
    
    public static void sendAddingLeadsInfo(String To,String title, String accName, String contactName,String CC,String invId,String pass1,String pass2,String pass3,String teamMemberEmails,String com1,String com2,String com3,String next1,String next2,String next3,String leadPassDate,String createdBy) throws ServiceLocatorException {
        //  System.out.println("sendOpportunityMailToPracticeSalesAndPreSales..");
//        System.out.println("sendPSCERStatusMailsToSalesAndPreSales..");
//        System.out.println("previousStage---" + previousStage);
//        System.out.println("currentStage---" + currentStage);
//        System.out.println("RequestorName---" + RequestorName);

     
        String from = "hubbleapp@miraclesoft.com";

        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
To=To+","+teamMemberEmails;
          System.out.println(To);
        Authenticator auth = new SMTPAuthenticator();

        try {
            //       String InsideSalesName = "", PracticeMgrName = "", RegionalMgrName = "", prevInsideSalesName = "", prevPracticeMgrName = "", prevRegionalMgrName = "";
       
      

            StringBuilder htmlText = new StringBuilder();

     
               String subject="Reg:Leads Adding Notification";
               
             
      

            htmlText.append("<!DOCTYPE html><html><head><meta charset='utf-8'>");
            htmlText.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
            htmlText.append("<meta http-equiv='X-UA-Compatible' content='IE=edge' />");
            htmlText.append("<style type='text/css'>body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;}table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;}img{-ms-interpolation-mode: bicubic;}img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}table{border-collapse: collapse !important;}body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}a[x-apple-data-detectors] {color: inherit !important;text-decoration: none !important;font-size: inherit !important;font-family: inherit !important;font-weight: inherit !important;line-height: inherit !important;}@media screen and (max-width: 525px) { .wrapper {width: 100% !important;max-width: 100% !important;}.logo img {margin: 0 auto !important;} .mobile-hide {display: none !important;} .img-max {max-width: 100% !important;width: 100% !important; height: auto !important; }.responsive-table {width: 100% !important;}.padding {padding: 10px 5% 15px 5% !important;}.padding-meta {padding: 30px 5% 0px 5% !important;text-align: center;}.padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}.no-padding {padding: 0 !important;}.section-padding {padding: 50px 15px 50px 15px !important;}.mobile-button-container {margin: 0 auto;width: 100% !important;}.mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important;display: block !important;} }</style>");
            htmlText.append("</head><body style='margin: 0 !important; padding: 0 !important;'>");
          //  htmlText.append("<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>Entice the open with some amazing preheader text. Use a little mystery and get those subscribers to read through...</div>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
            htmlText.append("<tr><td bgcolor='#ffffff' align='center'><table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper'>");
            htmlText.append("<tr><td align='center' valign='top' style='padding: 15px 0;' class='logo'>");
            htmlText.append("<a href='http://www.miraclesoft.com/' target='_blank'>");
            htmlText.append("<img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'>");
            htmlText.append("</a></td></tr></table></td></tr><tr><td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("<tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b> Lead Notifications</b>");
            htmlText.append("</td></tr></table></td></tr></table></td></tr><tr><td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("<tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>Team,</b>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Title :<b>"+title+",</b>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Acc Name :<b>"+accName+",</b>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Associated Contacts :<b>"+contactName+",</b>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Created By :<b>"+createdBy+",</b>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Created Date :<b>"+leadPassDate+",</b>");
            
            if(!"".equals(pass1)){
             htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Lead PassedBy1 :<b>"+DataSourceDataProvider.getInstance().getFname_Lname(pass1)+",</b>");
             htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Lead Comments1 :<b>"+com1+",</b>");
             htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Next Step1 :<b>"+next1+",</b>");
           
            }
             if(!"".equals(pass2)){
             htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Lead PassedBy2 :<b>"+DataSourceDataProvider.getInstance().getFname_Lname(pass2)+",</b>");
             htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Lead Comments2 :<b>"+com2+",</b>");
             htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Next Step2 :<b>"+next2+",</b>");
           
            }
             if(!"".equals(pass3)){
             htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Lead PassedBy3 :<b>"+DataSourceDataProvider.getInstance().getFname_Lname(pass3)+",</b>");
             htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Lead Comments3 :<b>"+com3+",</b>");
             htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Next Step3 :<b>"+next3+",</b>");
           
            }
                
          

            htmlText.append("<tr><td align='justify' style='padding: 5px 0 5px 0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
            htmlText.append("</td></tr></tr></table></td></tr><tr><td>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>");
            htmlText.append("<p style='text-align: justify; font-size: 14px;'><font color='#000000' face='trebuchet ms'><b>Thanks & Regards,</b><br/> <b>Marketing Team,</b><br/> Miracle Software Systems, Inc.<br/> 45625 Grand River Avenue, Novi, MI(USA)<br/> <b>Email :</b> marketing@miraclesoft.com<br/>");
            htmlText.append(" <b>Phone :</b> (+1)248-412-0426</font></p></td></tr>");
            htmlText.append("</table></td></tr><tr><td> <table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' ");
            htmlText.append("class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td></tr></table>");
            htmlText.append("</td></tr></table></td></tr><tr><td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' ");
            htmlText.append("class='responsive-table'><tr><td width='200' align='center' style='text-align: center;'>");
            htmlText.append("<table width='200' cellpadding='0' cellspacing='0' align='center'><tr><td width='10'>");
            htmlText.append("<a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' ");
            htmlText.append("alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td><td width='10'>");
            htmlText.append("<a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' ");
            htmlText.append("alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td><td width='10'>");
            htmlText.append("<a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'>");
            htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' >");
            htmlText.append("</a></td><td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'>");
            htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td><td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'>");
            htmlText.append("<img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td></tr></table></td></tr><tr><td height='10'></td></tr>");
            htmlText.append("<tr><td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems<br>");
            htmlText.append("</td></tr></table></td></tr></table></body></html>");
           
            //String to = dataSourceDataProvider.getemployeenamebyloginId(RequestorName);
            Timestamp createdDate = DateUtility.getInstance().getCurrentMySqlDateTime();
            ServiceLocator.getMailServices().doAddEmailLogNew(To, CC, subject, htmlText.toString(), createdDate.toString(), "", "Lead Adding Notification");

        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }
    
    
    
    /* Sending tax Exemption Status mail to employee end */
    /**
     * Author : Nagireddy
     * New Method for Authentication...
     * Date : 03-jan-2012
     */
    private static class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
}
