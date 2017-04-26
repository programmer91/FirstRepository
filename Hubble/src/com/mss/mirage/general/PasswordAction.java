/*
 * @(#)PasswordAction.java 1.0 Nov 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */
package com.mss.mirage.general;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.omg.PortableInterceptor.SUCCESSFUL;

/**
 * The <code>PasswordAction</code>  class is used for displaying messages in
 * <i>Login.jsp</i> page after submit details in <i>ForgotPassword.jsp</i> and <i>Registration.jsp</i>.
 *
 * @author RajaReddy.Andra <a href="mailto:randra@miraclesoft.com">randra@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 *
 * @see  com.mss.mirage.util.ApplicationConstants
 * @see  com.mss.mirage.util.ApplicationDataProvider
 * @see  com.mss.mirage.util.ConnectionProvider
 * @see  com.mss.mirage.util.ServiceLocator
 */
public class PasswordAction extends ActionSupport implements ServletRequestAware{
    
    /*@param resultType used to store type of the result*/
    private String resultType;
    
    /*@param resulMessage used to store message to display on login page*/
    private String resultMessage = null;
    
    /*@param id used to store id of the employee */
    private int id;
    
    /*@param prefQuestion used to store preffered question of the employee*/
    private int prefQuestionId;
    
    /*@param prefAnswer used to store preffered answer of the employee*/
    private String prefAnswer;
    
    /*@param loginId used to store login id of the employee*/
    private String loginId;
    
    /*@param userName used to store user name of the employee*/
    private String userName;
    
    /*@param oldPassword used to store old password of the employee*/
    private String oldPassword;
    
    /*@param newPassword used to store new password of the employee*/
    private String newPassword;
    
    /*@param confirmPassword used to store confirmation password of the employee*/
    private String confirmPassword;
    
    /*@param prefferedQuestionsMap used to store application constant question*/
    private Map prefferedQuestionsMap;
    
    private HttpServletRequest httpServletRequest;
   
    private String custLoginId;
    private String oldCustPassword;
    private String newCustPassword;
    
    private String emailId;

    
    private String eflag;
    
    /** Creates a new instance of PasswordAction */
    public PasswordAction() {
    }
    
    
    public String prepare() throws Exception{
        
        /*here all question getting from applicationConstants and those set to map*/
        setPrefferedQuestionsMap(DefaultDataProvider.getInstance().getPrefferedQuestions(ApplicationConstants.PREFERED_QUESTION_OPTIONS));
        resultType = SUCCESS;
        
        return resultType;
    }
    
    /**
     * resetPasswor() method used for mainly to reset to display the message on login page
     * user will provide some security information in resetPassword.jsp page
     * suppose he provide exact infirmation ,then he can reset password successfully,he will get
     * success message, other wise he will get sorry message.
     */
    public String resetPassword() throws Exception{
//        applicationDataProvider = applicationDataProvider.getInstance();
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                
                try{
                    setLoginId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    //boolean isReset = ServiceLocator.getGeneralService().updatePassword(this);
                    int updatedRows = ServiceLocator.getGeneralService().updatePassword(this);
                    if(updatedRows == 1){//isReset
                        resultMessage = "<font color=\"green\" size=\"1.5\">Congrats! You have changed your password succesfully </font>";
                        resultType = SUCCESS;
                    }else{
                        resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! We are not able to change your password. Please enter valid password! </font>";
                        resultType = INPUT;
                    }
                    httpServletRequest.setAttribute("resultMessage",resultMessage);
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//Closing Sessiong Checking
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        return resultType;
        
    }//end of the resetPassword() method
    
    /**
     *forgotPassword() method used for mainly to display message on login page
     *suppose user forgot his password ,he should provide some information in forgotPassword.jsp to
     *reget his password.He will get Congrats message,if he provide correct details other wise
     *he will get sorry message
     */
   /* public String forgotPassword() throws Exception{
        
        
        boolean isGotpass = ServiceLocator.getGeneralService().regetPassword(this);
        if(isGotpass){
            resultMessage = "<font color=\"green\" size=\"1.5\">Congrats! We have sent the password to your Official EmailId. </font>";
            resultType = SUCCESS;
        }else{
            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! We are not able to send the password. Please contact with our technical team mirage@miraclesoft.com. </font>";
            resultType = INPUT;
        }
        httpServletRequest.setAttribute("resultMessage",resultMessage);
        
        return SUCCESS;
    }*///end of the forgotPassword() method
    
     /*public String resetCustPassword() throws Exception{
     //        applicationDataProvider = applicationDataProvider.getInstance();
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null){
                
                try{
                    setCustLoginId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString());
                    //boolean isReset = ServiceLocator.getGeneralService().updatePassword(this);
                    int updatedRows = ServiceLocator.getGeneralService().updateCustPassword(this);
                    if(updatedRows == 1){//isReset
                        resultMessage = "<font color=\"green\" size=\"1.5\">Congrats! You have changed your password succesfully </font>";
                        resultType = SUCCESS;
                    }else{
                        resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! We are not able to change your password. Please enter valid password! </font>";
                        resultType = INPUT;
                    }
                    httpServletRequest.setAttribute("resultMessage",resultMessage);
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//Closing Sessiong Checking
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        return resultType;
        
    }//end of the resetPassword() method*/
     public String resetCustPassword() throws Exception{
     //        applicationDataProvider = applicationDataProvider.getInstance();
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null){
                
                try{
                    setCustLoginId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString());
                    //boolean isReset = ServiceLocator.getGeneralService().updatePassword(this);
                    int updatedRows = ServiceLocator.getGeneralService().updateCustPassword(this);
                    if(updatedRows >= 1){//isReset
                        resultMessage = "<font color=\"green\" size=\"1.5\">Congrats! You have changed your password succesfully </font>";
                        resultType = SUCCESS;
                    }else{
                        resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! We are not able to change your password. Please enter valid password! </font>";
                        resultType = INPUT;
                    }
                    httpServletRequest.setAttribute("resultMessage",resultMessage);
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//Closing Sessiong Checking
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        return resultType;
        
    }//end of the resetPassword() method
    /*Form here Bean section*/
     
     // Nwe For ForgetPassword 
   
public String forgotPassword() throws Exception{
         
         resultType = SUCCESS;
         boolean isGotpass = false;
         
         
         //System.out.println("in forgot pwd --->"+getEmailId()+"-- emp type--->"+getEflag());
         String email=getEmailId().trim();
             resultMessage = ServiceLocator.getGeneralService().regetPwd(email,getEflag());
    
        setEmailId("");
        setEflag(getEflag());
        /*  if(getEflag().equalsIgnoreCase("e")){
            resultType = SUCCESS;
        }else{
           resultType="CustSuccess"; 
        }*/
         httpServletRequest.setAttribute("resultMessage",resultMessage);
        
        return resultType;
    }
	
	

    
    /*getPrefQuestionId() used to get preffered question id's*/
    public int getPrefQuestionId() {
        return prefQuestionId;
    }
    
    /*setPrefQuestionId() used to set preffered question id's*/
    public void setPrefQuestionId(int prefQuestionId) {
        this.prefQuestionId = prefQuestionId;
    }
    
    /*getPrefAnswer() used to get preffered answers by users*/
    public String getPrefAnswer() {
        return prefAnswer;
    }
    
    /*setPrefAnswer() used to set preffered answers by users*/
    public void setPrefAnswer(String prefAnswer) {
        this.prefAnswer = prefAnswer;
    }
    
    /*getLoginId() used to get loginId of employee*/
    public String getLoginId() {
        return loginId;
    }
    
    /*setLoginId() used to set loginId of employee*/
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
    /*getPrefferedQuestionsMap() used to get preffered questions*/
    public Map getPrefferedQuestionsMap() {
        return prefferedQuestionsMap;
    }
    
    /*setPrefferedQuestionsMap() used to set preffered questions*/
    public void setPrefferedQuestionsMap(Map prefferedQuestionsMap) {
        this.prefferedQuestionsMap = prefferedQuestionsMap;
    }
    
    /*this is abstract method*/
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    /*getOldPassword() used to get old password of the employee*/
    public String getOldPassword() {
        return oldPassword;
    }
    
    /*setOldPassword(String oldPassword) used to set old password of the employee*/
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    
    /*getNewPassword(String newPassword) used to get new password of the employee*/
    public String getNewPassword() {
        return newPassword;
    }
    
    /*setNewPassword(String newPassword) used to set new password of the employee*/
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    /*getConfirmPassword() used to get confirm password of the employee*/
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    /*setConfirmPassword() used to set confirm password of the employee*/
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    /*getUserName() used to get user name of the employee*/
    public String getUserName() {
        return userName;
    }
    
    /*setUserName() used to set user name of the employee*/
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /*getId() uset to get id of the employee*/
    public int getId() {
        return id;
    }
    
    /*setId() uset to set id of the employee*/
    public void setId(int id) {
        this.id = id;
    }
    public String getCustLoginId() {
        return custLoginId;
    }

    public void setCustLoginId(String custLoginId) {
        this.custLoginId = custLoginId;
    }
    public String getOldCustPassword() {
        return oldCustPassword;
    }

    public void setOldCustPassword(String oldCustPassword) {
        this.oldCustPassword = oldCustPassword;
    }
    public String getNewCustPassword() {
        return newCustPassword;
    }

    public void setNewCustPassword(String newCustPassword) {
        this.newCustPassword = newCustPassword;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the eflag
     */
    public String getEflag() {
        return eflag;
    }

    /**
     * @param eflag the eflag to set
     */
    public void setEflag(String eflag) {
        this.eflag = eflag;
    }

    
    
}
