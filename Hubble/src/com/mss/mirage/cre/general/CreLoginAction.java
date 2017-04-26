/*
 * CreLoginAction.java
 *
 * Created on August 27, 2013, 3:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.cre.general;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.Iterator;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.mss.mirage.util.DateUtility;
import java.sql.PreparedStatement;
import com.mss.mirage.util.ServiceLocatorException;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PropertyConfigurator;
import com.mss.mirage.util.SecurityProperties;
/**
 *
 * @author miracle
 */
public class CreLoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
    
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String resultType;
    private String consultantId;
    private String consultantEmail;
    /** Creates a new instance of CreLoginAction */
    public CreLoginAction() {
    }
    
    /* public String execute() throws Exception {
         resultType = LOGIN;
        // System.out.println("Consultant Id--->"+getConsultantId());
         String consultantDetails[] = null;
         try{
         HttpSession session = httpServletRequest.getSession(true);
        // System.out.println("consultantDetails before-->"+consultantDetails);
         consultantDetails = ServiceLocator.getCreLoginService().creLoginCheck(getConsultantId());
         if(consultantDetails[0] != null) {
             if("Active".equals(consultantDetails[5])){
               if(getConsultantEmail().equals(consultantDetails[2])) {
                  // session.setAttribute(ApplicationConstants.SES_CONSULTANT_LOGIN_ID, consultantDetails[0]);
                  session.setAttribute(ApplicationConstants.SESSION_USER_ID, consultantDetails[0]);
                  session.setAttribute(ApplicationConstants.SES_CONSULTANT_NAME, consultantDetails[1]);
                  //session.setAttribute(ApplicationConstants.SES_CONSULTANT_ID, consultantDetails[4]);
                  session.setAttribute(ApplicationConstants.SESSION_EMP_ID, consultantDetails[4]);
                  session.setAttribute(ApplicationConstants.SESSION_CRE_MIN_MARKS, consultantDetails[7]);
                  session.setAttribute(ApplicationConstants.SESSION_CRE_TOTAL_QUESTIONS, consultantDetails[6]);
                  session.setAttribute(ApplicationConstants.SESSION_CRE_TOTAL_MARKS, consultantDetails[6]);
                  session.setAttribute(ApplicationConstants.SESSION_CRE_DURATION, consultantDetails[8]);
                  session.setAttribute(ApplicationConstants.SESSION_CRE_SUBTOPICS, DataSourceDataProvider.getInstance().getSubTopicNamesByCreId(Integer.parseInt(consultantDetails[4])));
                  resultType = SUCCESS;
               }else {
                   resultType = LOGIN;
           httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='2px'> Invalid Email!</font>");
               }
             }else {
                 resultType = LOGIN;
                 if(consultantDetails[5].equals("Registered")) {
           httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='1px'> Oops your details are not activate. Please contact HR Team!</font>"); 
             }else  if(consultantDetails[5].equals("Process")){
                 httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='1px'> Oops you details are in Process!</font>"); 
             }else  if(consultantDetails[5].equals("Hold")){
                 httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='1px'>Oops you are in Hold!</font>"); 
             }else  if(consultantDetails[5].equals("Selected")){
                 httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='green' size='1px'> Oops you details are in Process!</font>"); 
             }else  if(consultantDetails[5].equals("Rejected")){
                 httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='1px'>Sorry, you have Rejected!</font>"); 
             }else  if(consultantDetails[5].equals("Joined")){
                 httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='1px'> Your Process is Completed!</font>"); 
             }else  if(consultantDetails[5].equals("Deleted")){
                 httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='1px'>Oops you details are in Process!</font>"); 
             }
         }
         }else {
           resultType = LOGIN;
           httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='2px'>Invalid Consultant Id!</font>");
         }
         
         
         }catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
         
         return resultType;
     }*/
    public String execute() throws Exception {
         resultType = LOGIN;
        // System.out.println("Consultant Id--->"+getConsultantId());
         String consultantDetails[] = null;
         try{
         HttpSession session = httpServletRequest.getSession(true);
        // System.out.println("consultantDetails before-->"+consultantDetails);
         consultantDetails = ServiceLocator.getCreLoginService().creLoginCheck(getConsultantId());
         if(consultantDetails[1] != null) {
             if("Active".equals(consultantDetails[4])){
                 //String creId =  getConsultantId().substring(6, getConsultantId().length());
                 String creId =  consultantDetails[0];
                 //String status = DataSourceDataProvider.getInstance().getExamKeyIdStatus(creId);
                 String status = consultantDetails[5];
                 if(status.equals("Pending")){
                     resultType = LOGIN;
           httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='2px'> Your exam terminated abruptly.Please,Contact Operations team.</font>");
                 }
                else{ 
                    // System.out.println("before eqials--->"+getConsultantEmail() + " gffdgdfg"+consultantDetails[2]);
               if(getConsultantEmail().equals(consultantDetails[3])) {
                   // session.setAttribute(ApplicationConstants.SES_CONSULTANT_LOGIN_ID, consultantDetails[0]);
                   session.setAttribute(ApplicationConstants.SESSION_USER_ID, consultantDetails[1]);
                  session.setAttribute(ApplicationConstants.SES_CONSULTANT_NAME, consultantDetails[2]);
                  //session.setAttribute(ApplicationConstants.SES_CONSULTANT_ID, consultantDetails[4]);
                  session.setAttribute(ApplicationConstants.SESSION_EMP_ID, consultantDetails[0]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_MIN_MARKS, consultantDetails[7]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_TOTAL_QUESTIONS, consultantDetails[6]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_TOTAL_MARKS, consultantDetails[6]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_DURATION, consultantDetails[8]);
                 // session.setAttribute(ApplicationConstants.SESSION_CRE_ExamIds_List, DataSourceDataProvider.getInstance().getExamsList(Integer.parseInt(creId)));
                  
                // System.out.println("Exams List-->"+consultantDetails[6]);
                   session.setAttribute(ApplicationConstants.SESSION_CRE_ExamIds_List, DataSourceDataProvider.getInstance().getExamsListByListString(consultantDetails[6]));
                  
                  //session.setAttribute(ApplicationConstants.SESSION_CRE_SUBTOPICS, DataSourceDataProvider.getInstance().getSubTopicNamesByCreId(Integer.parseInt(consultantDetails[4])));
                   resultType = SUCCESS;
               }else {
                   resultType = LOGIN;
           httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='2px'> Invalid Email!</font>");
               }
             }
             }else {
                 resultType = LOGIN;
                 if(consultantDetails[4].equals("Registered")) {
                httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='1px'> Oops your details are not activated. Please contact HR Team!</font>"); 
             }else  if(consultantDetails[4].equals("Process")){
                 httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='1px'> Oops you details are in Process!</font>"); 
             }else  if(consultantDetails[4].equals("Hold")){
                 httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='1px'>Oops you are in Hold!</font>"); 
             }else  if(consultantDetails[4].equals("Selected")){
                 httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='green' size='1px'> Oops you details are in Process!</font>"); 
             }else  if(consultantDetails[4].equals("Rejected")){
                 httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='1px'>Sorry, you have Rejected!</font>"); 
             }else  if(consultantDetails[4].equals("Joined")){
                 httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='1px'> Your Process is Completed!</font>"); 
             }
             else  if(consultantDetails[4].equals("PASS") || consultantDetails[4].equals("FAIL")){
                 httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='1px'>Oops your exam completed!</font>"); 
             }   
         }
         }else {
           resultType = LOGIN;
           httpServletRequest.setAttribute(ApplicationConstants.REQ_ERR_MESSAGE, "<font color='red' size='2px'>Invalid Consultant Id!</font>");
         }
         
         
         }catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
         
         return resultType;
     }
    
    
       /**
     *doLogout() method is used to invalidate session
     */
    public String doLogout() throws Exception {
        System.out.println("CRE Logout");
        try{
            if(getHttpServletRequest().getSession(false) != null){
                getHttpServletRequest().getSession(false).invalidate();
              }
            resultType =SUCCESS;
        }catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        return resultType;
    }
    
       public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
          public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse=httpServletResponse;
    }
   public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getConsultantEmail() {
        return consultantEmail;
    }

    public void setConsultantEmail(String consultantEmail) {
        this.consultantEmail = consultantEmail;
    }
}
