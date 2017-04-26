/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.employee.phonelog
 *
 * Date    : February 6, 2008, 11:29 PM
 *
 * Author  : Venkateswara Rao Nukala<vnukala@miraclesoft.com>
 *
 * File    : PhoneLogAction.java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.employee.phonelog;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.sql.Time;
import com.mss.mirage.util.DateUtility;
/**
 *
 * @author miracle
 */
public class PhoneLogAction extends ActionSupport implements ServletRequestAware{
    /**
     * this id is used for editing phonelog
     */
    private int phoneLogId;
    /**
     * this variable is used to store the employee LoginId
     */
    private String employeeLoginId;
    /**
     * this variable is used to store httpServletRequest Object
     */
    private HttpServletRequest httpServletRequest;
    /**
     * this variable is used to store the date object
     */
    private Date date;
    /**
     * this variable is used to store the from phone number
     */
    private String fromPhoneNo;
    /**
     * this variable is used to store the startTime of the
     * phone call
     */
    private String startTime;
    /**
     * this variable is used to store the endTime of the
     * phone call
     */
    private String endTime;
    /**
     * this variable is used to store the toPhone number of the
     * phone call
     */
    private String toPhoneNo;
    /**
     * this variable is used to store the call duration of the
     * phone call
     */
    private String callDuration;
    /**
     * this variable is used to store the call type of the
     * phone call (personal or official)
     */
    private String callType;
    /**
     * this variable is used to store the description of the
     * phone call
     */
    private String description;
    /**
     * this variable is hidden
     */
    private boolean reconcilled;
    /**
     * this variable is used to store the result type (such as
     * success, input, error and login)
     */
    private String resultType;
    /**
     * this variable is for checking the authorization of
     * roles
     */
    private int userRoleId;
    /**
     * this variable is used to store the startDate of the
     * phone call
     */
    private String startDate;
    /**
     * this variable is used to store the endDate of the
     * phone call
     */
    private  String endDate;
    /**
     * this is reference variable of StringBuilder object
     */
    private  StringBuilder stringBuilder;
    /**
     * this variable is used for submitting the form action
     */
    private String submitFrom;
    /**
     * this variable sets the  currentSearch action like
     * myPhonelog or teamPhonelog
     */
    private String currentSearchAction;
    /**
     * this variable is for setting the current action like
     * edit mode or add mode
     */
    private String currentAction;
    /**
     * this variable is for storing all phonelog values
     */
    private PhoneLogVTO currentPhoneLogVTO;
    
    /** Creates a new instance of PhoneLogAction */
    public PhoneLogAction() {
    }
    
    /**
     * this method is default method
     * @throws java.lang.Exception
     * @return
     */
    public String execute()throws Exception{
        
        return SUCCESS;
    }
    /**
     * this method is for setting session variables for EmpLoginId and date
     * @throws java.lang.Exception
     * @return
     */
    public String prepare()throws Exception{
        //set the read only fields
        resultType=LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_PHONELOG",userRoleId)){
                try{
                    setEmployeeLoginId((String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID));
                    setDate(Date.valueOf(DateUtility.getInstance().getToDayDate()));
                    setFromPhoneNo((String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_WORKPHNO));
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    /**
     * this method is for adding the phonelog
     * @throws java.lang.Exception
     * @return
     */
    public String doAdd() throws Exception { // adding of phonelog
        resultType = LOGIN;
        //Checking whether user is login or not
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            //Checking whether action has permission
            if(AuthorizationManager.getInstance().isAuthorizedUser("PHONELOG_ADD",userRoleId)){
                try{
                    boolean isInserted=false;
                    String resultMessage="";
                    isInserted = ServiceLocator.getPhoneLogService().addPhoneLog(this);
                    if(isInserted){
                        resultMessage="Successfully PhoneLog added";
                    }else{
                        resultMessage="please Try again";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    resultType=SUCCESS;
                    
                    //Exception Handling
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        
        return resultType;
    }
    
    /**
     * this method is for editing the phonelog
     * @throws java.lang.Exception
     * @return
     */
    public String doEdit() throws Exception {  //edit the phonelog
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PHONELOG_EDIT",userRoleId)){
                try {
                    boolean isUpdated=false;
                    String resultMessage="";
                    isUpdated=ServiceLocator.getPhoneLogService().editPhoneLog(this,getPhoneLogId());
                    if(isUpdated){
                        resultMessage="Successfully PhoneLog updated";
                    }else{
                        resultMessage="please Try again";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    resultType=SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        
        return  resultType;
    }
    
    /**
     * this method is for deleting the phonelog record
     * @throws java.lang.Exception
     * @return
     */
    public String doDelete() throws Exception {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PHONELOG_DELETE",userRoleId)){
                try {
                    boolean isDeleted=false;
                    String resultMessage="";
                    isDeleted=ServiceLocator.getPhoneLogService().deletePhoneLog(this,getPhoneLogId());
                    if(isDeleted){
                        resultMessage="Successfully PhoneLog updated";
                        
                    }else{
                        resultMessage="please Try again";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    resultType=SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
        
    }
    /**
     * this method is for displaying employ  phonelog
     * @return
     */
    public String myPhoneLogList() {
        resultType = LOGIN;
        
        try{
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
                String userId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                resultType = "accessFailed";
                if(AuthorizationManager.getInstance().isAuthorizedUser("MY_PHONELOG_LIST",userRoleId)){
                    if(getSubmitFrom()==null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("SELECT Id,LoginId,FromNo ,ToNo ,CallStartTime,CallEndTime ,Duration ,CallDate,Description from tblEmpPhoneLog where LoginId='"+userId+"'");
                    }
                    
                    /*if query is already existed remove and add new squery */
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_JOB_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_JOB_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_JOB_LIST,stringBuilder.toString());
                }
                resultType = SUCCESS;
                
            }
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        setCurrentSearchAction("phoneLogSearch");
        return resultType;
    }
    
    /**
     * this method is for searching the employ phonelog
     * @return
     */
    public String myPhoneLogSearch(){
        resultType = LOGIN;
        boolean andFlag = false;
        try{
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
                resultType = "accessFailed";
                if(AuthorizationManager.getInstance().isAuthorizedUser("MY_PHONELOG_SEARCH",userRoleId)){
                    if("Search".equalsIgnoreCase(getSubmitFrom())) {
                        stringBuilder = new StringBuilder();
                        if(!getStartDate().equals("") && !getEndDate().equals("")){
                            stringBuilder.append("SELECT Id,LoginId,FromNo,ToNo,CallDate,CallStartTime,CallEndTime ,Description,Duration  from tblEmpPhoneLog  ");
                            stringBuilder.append(" where ");
                            stringBuilder.append("CallDate between '"+DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"' and  '"+DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"'");
                            stringBuilder.append(" group by  CallDate  ") ;
                        }
                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_PHONE_LIST)!=null){
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_PHONE_LIST);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_PHONE_LIST,stringBuilder.toString());
                    }
                }
                String userId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                setCurrentSearchAction("phoneLogSearch");
                resultType = SUCCESS;
            }
        }catch(Exception ex1){
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex1.toString());
            resultType =  ERROR;
        }
        return resultType;
    }
    
    
    /**
     * this method is for listing the team phonelog
     * @throws java.lang.Exception
     * @return
     */
    public String myTeamPhoneLogList() throws Exception {
        resultType = LOGIN;
        try{
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
                resultType = "accessFailed";
                if(AuthorizationManager.getInstance().isAuthorizedUser("MY_TEAM_PHONELOG_LIST",userRoleId)){
                    String userId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    if(getSubmitFrom()==null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("select Id,CallDate,LoginId,Duration,Description,FromNo,ToNo from tblEmpPhoneLog where LoginId in (select LoginId from tblEmployee where ReportsTo='"+userId+"') ");
                    }
                    /*if query is already existed remove and add new squery */
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_PHONE_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_PHONE_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_PHONE_LIST,stringBuilder.toString());
                }
                resultType = SUCCESS;
            }
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        //  setCurrentAction("myTeamPhoneLogList");
        setCurrentSearchAction("teamPhoneLogSearch");
        return resultType;
    }
    
    /**
     * this method is for searching the team phonelog
     * @throws java.lang.Exception
     * @return
     */
    public String myTeamPhoneLogSearch() throws Exception {
        resultType = LOGIN;
        
        boolean andFlag = false;
        try{
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
                resultType = "accessFailed";
                if(AuthorizationManager.getInstance().isAuthorizedUser("MY_TEAM_PHONELOG_SEARCH",userRoleId)){
                    
                    String userId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    if("Search".equalsIgnoreCase(getSubmitFrom())) {
                        stringBuilder = new StringBuilder();
                        if(!getStartDate().equals("") && !getEndDate().equals("")){
                            stringBuilder.append("SELECT Id,LoginId,FromNo,ToNo,CallDate,CallStartTime,CallEndTime ,Description,Duration  from tblEmpPhoneLog  ");
                            stringBuilder.append(" where LoginId in");
                            stringBuilder.append("(select LoginId from tblEmployee where ReportsTo='"+userId+"')");
                            stringBuilder.append(" and ");
                            stringBuilder.append("  CallDate between '"+DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"' and  '"+DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"'");
                            stringBuilder.append(" group by  CallDate  ") ;
                        }
                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_PHONE_LIST)!=null){
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_PHONE_LIST);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_PHONE_LIST,stringBuilder.toString());
                    }
                }
                setCurrentSearchAction("teamPhoneLogSearch");
                resultType = SUCCESS;
            }
        }catch(Exception ex1){
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex1.toString());
            resultType =  ERROR;
        }
        return resultType;
    }
    
    /**
     * this method is for editing the particular record of phonelog
     * @throws java.lang.Exception
     * @return
     */
    public String getPhoneLog()throws Exception{
        
        resultType=LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_PHONELOG",userRoleId)){
                try{
                    setCurrentPhoneLogVTO(ServiceLocator.getPhoneLogService().getPhoneLog(getPhoneLogId()));
                    return SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    
    public String getEmployeeLoginId() {
        return employeeLoginId;
    }
    
    public void setEmployeeLoginId(String employeeLoginId) {
        this.employeeLoginId = employeeLoginId;
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public  Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getCallDuration() {
        return callDuration;
    }
    
    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCallType() {
        return callType;
    }
    
    public void setCallType(String callType) {
        this.callType = callType;
    }
    
    public boolean isReconcilled() {
        return reconcilled;
    }
    
    public void setReconcilled(boolean reconcilled) {
        this.reconcilled = reconcilled;
    }
    
    public String getFromPhoneNo() {
        return fromPhoneNo;
    }
    
    public void setFromPhoneNo(String fromPhoneNo) {
        this.fromPhoneNo = fromPhoneNo;
    }
    
    public String getToPhoneNo() {
        
        return toPhoneNo;
        
    }
    
    public void setToPhoneNo(String toPhoneNo) {
        this.toPhoneNo = toPhoneNo;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;//.substring(11,19);
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public  String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }
    
    
    public int getPhoneLogId() {
        return phoneLogId;
    }
    
    public void setPhoneLogId(int phoneLogId) {
        this.phoneLogId = phoneLogId;
    }
    
    public String getCurrentSearchAction() {
        return currentSearchAction;
    }
    
    public void setCurrentSearchAction(String currentSearchAction) {
        this.currentSearchAction = currentSearchAction;
    }
    
    public PhoneLogVTO getCurrentPhoneLogVTO() {
        return currentPhoneLogVTO;
    }
    
    public void setCurrentPhoneLogVTO(PhoneLogVTO currentPhoneLogVTO) {
        this.currentPhoneLogVTO = currentPhoneLogVTO;
    }
    
    public String getCurrentAction() {
        return currentAction;
    }
    
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }
    
}
