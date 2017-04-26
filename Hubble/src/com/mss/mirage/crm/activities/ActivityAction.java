/*
 * @(#)ActivityAction.java 1.0 November 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 *
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 30, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : ActivityAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */


package com.mss.mirage.crm.activities;

import com.mss.mirage.services.mail.MailAttachment;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ReportProperties;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 * The <code>ActivityAction</code>  class is used for getting adding new Activity from
 * <i>ActivityAdd.jsp</i> page.
 * <p>
 * Then it invokes setter methods in <code>ActivityAction</code> class and invokes execute() method
 * in <code>ActivityAction</code> for inserting activity details in MIRAGE.tblCrmActivity table.
 *
 * @author Rajasekhar Yenduva <a href="mailto:ryenduva@miraclesoft.com">ryenduva@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.util.ServiceLocator
 * @see com.mss.mirage.crm.activities.ActivityVTO
 * @see com.mss.mirage.util.HibernateDataProvider
 *
 * @since 1.0
 */
public class ActivityAction extends ActionSupport implements ServletRequestAware{
    
    /** The resultType is used for storing type of message. */
    private String resultType;
    
    /** The resultMessage is used for storing resulted message. */
    private String resultMessage;
    
    /** The currentActivity is used for storing currentActivity Object.
     *{@link com.mss.mirage.crm.activities.ActivityVTO}
     */
    private ActivityVTO currentActivity;
    
    
    
    /** The activityService is used for invoking activity Service methods.
     * {@link com.mss.mirage.util.ServiceLocator}
     */
    private ActivityService activityService;
    
    /** The hibernateDataProvider is used for invoking application level methods.
     *{@link com.mss.mirage.util.HibernateDataProvider}
     */
    private HibernateDataProvider hibernateDataProvider;
    
    private DefaultDataProvider defaultDataProvider;
    
    private DataSourceDataProvider dataSourceDataProvider;
    
    /** The contactName is used for storing contactPerson Name. */
    private String contactName;
    
    /** The accountName is used for storing accountName. */
    private String accountName;
    
    //early intialization process
    
    /** The activityTypeList is used for storing activityType. */
    private List activityTypeList = new ArrayList();
    
    /** The activityStatusList is used for storing activityStatus. */
    private List activityStatusList = new ArrayList();
    
    /** The prirorityList is used for storing priority.  */
    private List priorityList = new ArrayList();
    
    /** The campaignIdMap is used for storing campaignId. */
    private Map campaignIdMap;
    
    /** The assignedToIdMap is used  for storing assignedToId. */
    private Map assignedToIdMap;
    
    /** The httpServletRequest is used for binding request object to this class. */
    private HttpServletRequest httpServletRequest;
    
    //Form Variables
    
    /** The id used for storing id of activity. */
    private int id;
    
    /** The accountId is used for storing accountID.  */
    private int accountId;
    
    /** The contactId is used for storing contactId.  */
    private int contactId;
    
    
    private String accId;
    
    
    private String contId;
    
    /** The campaignId is used for storing campaignId. */
    private int campaignId;
    
    /** The assignedToId is used for storing assignedToId. */
    private String assignedToId;
    
    /** The assignedById is used for storing assignedById. */
    private String assignedById;
    
    /** The activityType is used for storing activityType. */
    private String activityType;
    
    /** The status is used for storing status of activity. */
    private String status;
    
    /** The priority is used for storing priority of activity. */
    private String priority;
    
    /** The description is used for storing description. */
    private String description;
    
    /** The comments is used for storing comments.  */
    private String comments;
    
    /** The alarm is used for storing alarm. */
    private boolean alarm;
    
    /** The createdDate is used for storing createdDate. */
    private Timestamp createdDate;
    
    /** The createdById is used for storing createdById. */
    private String createdById;
    
    /**  The modifiedDate is used for storing modifiedDate. */
    private Timestamp modifiedDate;
    
    /** The modifiedById is used for storing modifiedById. */
    private String modifiedById;
    
    /** The dueDate is used for storing dueDate. */
    private String dueDate;
    
    private String dateWithoutTime;
    
    /** The dueDateTimeStamp is used for storing dueDateTimeStamp. */
    private Timestamp dueDateTimeStamp;
    
    private String submitFrom;
    
    private int userRoleId;
    
    private String employeeName;
    
    private String startDate;
    
    private String endDate;
    
    private String formAction;
    
    private String dateWithOutTime;
    
    private Date dashBoardStartDate;
    
    private Date dashBoardEndDate;
    private String calAccountName;
    private String calContactName;
    private String calDateStart;
    private String viewMemberSearch;
    
    private String officeEmail;
    private String personalEmail;
    private boolean sendInvitation;
    private String userRoleName;
    private String actionType;
    
    // The userWorkCountry is used to store the WorkingCountry name selected by the user when he logs in
    private String userWorkCountry;
    
    private boolean mailStatus;
    private int activitySummaryFlag;

    private Map contactsMap;		
    private Map oppurtunitiesMap;		
    private int oppurtunityId;		
    private String contactsHidden;

 private String practiceName;
private String contactType;
private List practiceList;
private Map wotEmailContactsMap = new HashMap();
    /** Creates a new instance of ActivityAction */
    public ActivityAction() {
    }
    
    
    /**
     * The execute() is used for executing business logic.
     * @throws Exception
     * @return  Success string
     */
    public String execute() throws Exception {
        
        return SUCCESS;
    }
    
    /**
     * The prepare() is used for executing block of code which is required before execute method get's executed.
     * @throws Exception
     * @return  string variable.
     */
   public String prepare(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_ACTIVITY",userRoleId)){
                try{
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    //httpSession = httpServletRequest.getSession(false);
                    //setOfficeEmail(dataSourceDataProvider.getContactOfficeMail(contactId));
                    
                   // setPersonalEmail(dataSourceDataProvider.getContactPersonalMail(contactId));
                    
                    //set the mutator methods for rendering data on jsp.
                  //  setActivityTypeList(hibernateDataProvider
                         //   .getCrmActivityTypes(ApplicationConstants.CRM_ACTIVITY_TYPE_OPTIONS));
                    setWotEmailContactsMap(DataSourceDataProvider.getInstance().getWotEmailContactsMap(getAccountId()));
                    setActivityStatusList(hibernateDataProvider
                            .getCrmActivityStatus(ApplicationConstants.CRM_ACTIVITY_STATUS_OPTIONS));
                    
                    setPriorityList(defaultDataProvider
                            .getCrmActivityPriority(ApplicationConstants.CRM_ACTIVITY_PRIORITY_OPTIONS));
                    
                 ///   setCampaignIdMap(hibernateDataProvider
                      //      .getCampaignNames(ApplicationConstants.CRM_CAMPAIGN_OPTIONS));
                    setCreatedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    setPracticeList(DataSourceDataProvider.getInstance().getPracticeNamesList(7,1));
                    
        /*setAssignedToIdMap(hibernateDataProvider
                .getEmployeeNamesByUserId((String)hibernateDataProvider
                .getDepartment(ApplicationConstants.DEPARTMENT_OPTION)
                .get(0)));*/
                    if(getId()==0){
                         
                    setActivityTypeList(hibernateDataProvider
                            .getCrmActivityTypes(ApplicationConstants.CRM_ACTIVITY_TYPE_OPTIONS));
                    setCampaignIdMap(DataSourceDataProvider.getInstance().getAllCampaignNames());
                    
                    }else{
                       
                      setCampaignIdMap(DataSourceDataProvider.getInstance().getCampaignNames());
                     setActivityTypeList(hibernateDataProvider
                            .getCrmActivityTypesAll(ApplicationConstants.CRM_ACTIVITY_TYPE_OPTIONS_ALL));
                    }
                    if("Vendor".equalsIgnoreCase(userRoleName))
                        setAssignedToIdMap(dataSourceDataProvider.getEmployeeNamesByUserId("Recruiting"));
                    else
                        setAssignedToIdMap(dataSourceDataProvider.getEmployeeNamesByUserId("sales"));
                    
                    setAccountName(hibernateDataProvider.getAccountName(getAccountId()));
                    
                    setEmployeeName(dataSourceDataProvider.getEmployeeNameByEmpNo(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString())));
                    
                    /*for displaying Default Date*/
                  /*  DateUtility date = DateUtility.getInstance();
                    dueDate = date.sqlTimeStampTousTimeStamp(date.getCurrentMySqlDateTime().toString());
                    dateWithOutTime = dueDate.substring(0,10);
                    setDueDate(dueDate);*/
                    
                   
                   // setCampaignIdMap(DataSourceDataProvider.getInstance().getCampaignNames());
                    Map oppMap = DataSourceDataProvider.getInstance().getOppurtunitiesNames(getAccountId());
                    oppMap.put("-2", "No Opportunity");
	setOppurtunitiesMap(oppMap);
       
        setContactsMap(DataSourceDataProvider.getInstance().getContactsNamesAgainstAccount(getAccountId()));
                    httpServletRequest.setAttribute("currentAccountId",String.valueOf(getAccountId()));
                    httpServletRequest.setAttribute("currentContactId",String.valueOf(getContactId()));
                    
                    if(httpServletRequest.getSession(false).getAttribute("accountName") != null){
                        httpServletRequest.getSession(false).removeAttribute("accountName");
                    }
                    httpServletRequest.getSession(false).setAttribute("accountName",getAccountName());
                    
                    if(httpServletRequest.getSession(false).getAttribute("contactName") != null){
                        httpServletRequest.getSession(false).removeAttribute("contactName");
                    }
                    
                    if(getContactName() != null){
                        httpServletRequest.getSession(false).setAttribute("contactName",getContactName());
                    }
                    setActionType("addActivity");
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
        
        
        
    }
    

    
    
    /**
     * The getActivity() is used for retrieving the activity from database server.
     * @throws Exception
     * @return String variable for navigation.
     */
    public String getActivity(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_ACTIVITY",userRoleId)){
                try{
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                   // setOfficeEmail(dataSourceDataProvider.getContactOfficeMail(contactId));
                    //setPersonalEmail(dataSourceDataProvider.getContactPersonalMail(contactId));
                    setCurrentActivity(ServiceLocator.getActivityService().getActivity(this.getId()));
                    httpServletRequest.setAttribute("currentActivityId",String.valueOf(this.getId()));
                    httpServletRequest.setAttribute("currentActivityType",getCurrentActivity().getActivityType());
                    
                    setAccountId(getCurrentActivity().getAccountId());
                    setContactId(getCurrentActivity().getContactId());
                    setResultMessage(getResultMessage());
                    //Generating content for select fields
                    prepare();
                   // setActionType("editActivity");
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    
    /**
     * The doAdd() is used for adding  the activity to the database server.
     * @throws Exception
     * @return String variable for navigation.
     */
    public String doAdd(){               
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("ADD_ACTIVITY",userRoleId)){
                try{
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        boolean isInserted = false;
                        
                        setCreatedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        
                        this.setAssignedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        
                        this.setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                        
                        this.setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                        
                        activityService = ServiceLocator.getActivityService();
                     
                         setCurrentActivity(activityService.getActivityVTO(this));
                        //if(!getActivityType().equalsIgnoreCase("Alarm"))
                         if(!"Alarm".equalsIgnoreCase(getActivityType()))
                        {
                             setDueDate(DateUtility.getInstance().getCurrentMySqlDate());
                        }
                      //  setDueDateTimeStamp(DateUtility.getInstance().strToTimeStampObj(getDueDate()));
                        //System.out.println("getContactsHidden()-->"+getContactsHidden());
                        isInserted = activityService.addActivity(this,httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        
                      // setId(activityService.getMaxActivityId());
                      //  setId(DataSourceDataProvider.getInstance().getMaxCrmActivityId());
                       

                        if(isInserted){
                            resultType = SUCCESS;
                             /* this.setDescription(" ");
                              this.setComments(" ");*/
                            setResultMessage("<font color=\"green\" size=\"1.5\">Activity has been successfully inserted!</font>");
                        }else{
                            resultType = INPUT;
                            setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>");
                        }
                        
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                        
                        
                       // prepare();
                       // setId(getId());
                      
                        //this.setModifiedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        
                        //this.setAssignedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        
                       // this.setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                        
                       // activityService = ServiceLocator.getActivityService();
                        
                      //  setCurrentActivity(activityService.getActivityVTO(this));
                        
                      //  setDueDateTimeStamp(DateUtility.getInstance().strToTimeStampObj(getDueDate()));
                        
                      
                        //setActionType("updateActivity");
                        
                        
                    }//close for submitFrom checking
                    if(LOGIN.equals(resultType)) resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close for session checking
        return resultType;
    }

    
    public String doUpdate(){
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EDIT_ACTIVITY",userRoleId)){
                try{
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        boolean isUpdated = false;
                        
                        this.setModifiedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        
                        this.setAssignedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        
                        this.setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                        
                        activityService = ServiceLocator.getActivityService();
                        
                        setCurrentActivity(activityService.getActivityVTO(this));
                        
                        setDueDateTimeStamp(DateUtility.getInstance().strToTimeStampObj(getDueDate()));
                        
                        isUpdated = activityService.updateActivity(this);
                        mailStatus=activityService.getMailStatus(getContactId());
                        //for display the resulted message according to below specified condition.
                        setResultMessage("");
                        String webCastFilePath = "";
                        if(sendInvitation && mailStatus == false) {
                            String checkActivityType = Properties.getProperty(activityType);
                            setResultMessage("****Please Select Activity Type as onDemandIBMSupportandDevelopmentServices to send the invitation****");
                            if(checkActivityType.trim().equals(activityType.trim())) {
                                setResultMessage("****Please Provide Atleast one Mail Id of Contact To send the Invitation****");
                                if(!(officeEmail.equals("") || officeEmail==null)) {
                                    webCastFilePath = ReportProperties.getProperty(activityType);
                                    //activityService.sendInvitation(calContactName,officeEmail,activityType);
                                    int check = MailManager.sendInvitation(contactName,officeEmail,webCastFilePath);
                                    if(check == 1) {
                                        setResultMessage("And Invitation  Has been sent to Office Mail Id ");
                                    }else {
                                        setResultMessage("But the Given Mail Id is 'Invalid'");
                                    }
                                }else if(!(personalEmail.equals("")|| personalEmail==null)) {
                                    webCastFilePath = ReportProperties.getProperty(activityType);
                                    //MailManager.sendInvitation(contactName,personalEmail,webCastFilePath);
                                    //activityService.sendInvitation(calContactName,personalEmail,activityType);
                                    //resultMessage = "And Invitation  Has been sent to Personal Mail Id ";
                                    int check = MailManager.sendInvitation(contactName,officeEmail,webCastFilePath);
                                    if(check == 1) {
                                        setResultMessage("And Invitation  Has been sent to Personal Mail Id ");
                                    }else {
                                        setResultMessage("But the Given Mail Id is 'Invalid'");
                                    }
                                }
                            }
                        }
                        if(isUpdated){
                            resultType = SUCCESS;
                            //this.setDescription(" ");
                            //this.setComments(" ");
                            setResultMessage("<font color=\"green\" size=\"1.5\">Activity has been successfully Updated!" + getResultMessage() + "</font>");
                        }else{
                            resultType = INPUT;
                            setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>");
                        }
                        
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                        
                        //   setAccountId(getCurrentActivity().getAccountId());
                        //   setContactId(getCurrentActivity().getContactId());
                        
                        //Generating content for select fields
                        prepare();
                        setActionType("updateActivity");
                        
                    }//close for submitFrom checking
                    if(LOGIN.equals(resultType)) resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    
    
    
    /*
     *  The setdtEndChars(String str,String str1) is  used for  format the vcs fileformat dtstart,dtend value
     *  It will takes 2 strings one for date, second for time.
     */
    public String setdtEndChars(String str,String str1) {
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        
        for(int i=0;i<sb.length();i++) {
            if(sb.charAt(i)== '-') {
                sb.deleteCharAt(i);
            }
        }
        String strDate = sb.toString();
        StringBuilder sb1 = new StringBuilder(str1);
        int length1 = sb1.length();
        for(int i=0;i<sb1.length();i++) {
            if(sb1.charAt(i)== ':') {
                sb1.deleteCharAt(i);
            }
        }
        String strTime = sb1.toString();
        String strDateTime = strDate+"T"+strTime+"Z";
        return strDateTime;
    }
    
    /**
     * The doEdit() is used for editing  the activity to the database server.
     * @throws Exception
     * @return String variable for navigation.
     */
     public String doEdit(){
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EDIT_ACTIVITY",userRoleId)){
                try{
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        boolean isUpdated = false;
                        
                        this.setModifiedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        
                        this.setAssignedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        
                        this.setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                        
                        activityService = ServiceLocator.getActivityService();
                        
                        setCurrentActivity(activityService.getActivityVTO(this));
                        
                     //   setDueDateTimeStamp(DateUtility.getInstance().strToTimeStampObj(getDueDate()));
                        
                        isUpdated = activityService.updateActivity(this);
                        if(isUpdated){
                            resultType = SUCCESS;
                           // this.setDescription(" ");
                           // this.setComments(" ");
                            setResultMessage("<font color=\"green\" size=\"1.5\">Activity has been successfully Updated!</font>");
                        }else{
                            resultType = INPUT;
                            setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>");
                        }
                        
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                        
                        //   setAccountId(getCurrentActivity().getAccountId());
                        //   setContactId(getCurrentActivity().getContactId());
                        
                        //Generating content for select fields
                      //  prepare();
                        
                    }//close for submitFrom checking
                    if(LOGIN.equals(resultType)) resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
	

    
//END:Business Methods
    
    public String doSearch(){
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_ACTIVITY_SEARCH",userRoleId)){
                try{
                    
                    if("SearchAll".equalsIgnoreCase(getSubmitFrom())){
                        StringBuffer queryStringBuffer =new StringBuffer();
                        queryStringBuffer.append("Select activityId,accountName,fName,lName,dueDate,officePhone,description");
                        queryStringBuffer.append(" FROM vwCalendarActivitiesList ");
                        
                        
                        if(null == getStartDate()) setStartDate("");
                        if(null == getEndDate()) setEndDate("");
                        
                        
                        if((!"".equals(getStartDate()))
                        || (!"".equals(getEndDate()))) {
                            //|| (!"".equals(getAccountTeam()))){
                            queryStringBuffer.append("WHERE AssignedToId = '"+loginId+"'");
                        }
                        DateUtility dateUtil = DateUtility.getInstance();
                        int columnCounter = 0;
                        if(getStartDate().trim().equals(getEndDate().trim())){
                            if(!"".equals(getStartDate()) && columnCounter==0 ){
                                queryStringBuffer.append(" AND dueDate ='"+dateUtil.usDateToSqldate(getStartDate())+"'");
                                columnCounter++;
                            }else if(!"".equals(getStartDate()) && columnCounter!=0){
                                queryStringBuffer.append(" AND dueDate ='"+dateUtil.usDateToSqldate(getStartDate())+"'");
                            }
                        }else{
                            
                            if(!"".equals(getStartDate()) && columnCounter==0 && !"".equals(getEndDate()) ){
                                queryStringBuffer.append(" AND dueDate >='"+dateUtil.usDateToSqldate(getStartDate())+"' AND dueDate<='"+dateUtil.usDateToSqldate(getEndDate())+"'");
                                columnCounter++;
                            }else if(!"".equals(getStartDate()) && columnCounter!=0 && !"".equals(getEndDate())){
                                queryStringBuffer.append(" AND dueDate >='"+dateUtil.usDateToSqldate(getStartDate())+"' AND dueDate<='"+dateUtil.usDateToSqldate(getEndDate())+"'");
                            }
                            
                        }
                        
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ACTIVITY_LIST,queryStringBuffer);
                    }
                    
                    
//                if((getSubmitFrom() != null) || !"".equals(getSubmitFrom())){
//                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SUBMIT_FROM_ACC_LIST,getSubmitFrom());
//                }
                    prepare();
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        
        return resultType;
    }
    
     public String getMyActivitiesInfo(){
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userWorkCountry= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_MYACTIVITIES_INFO",userRoleId)){
                try{
                    String activityQueryString=null;
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    String inMembers = null;
                    String queryString="";
                    StringBuilder buf = new StringBuilder();
                    DateUtility date = DateUtility.getInstance();
                    String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    
                    //DateFormating with out Time
                    String  dueDate = date.sqlTimeStampTousTimeStamp(date.getCurrentMySqlDateTime().toString());
                    
                    //Setting date value to this Instance
                    setDateWithOutTime(dueDate.substring(0,10));
                    setFormAction("myActivitiesInfo");
                    setActivitySummaryFlag(1);

                    
                    /*
                     
                     buf.append("SELECT DISTINCT ");
                    buf.append("tblCrmAccount.Name,");
                    buf.append("tblCrmAccount.Id,");
                    buf.append("tblCrmActivity.Id as ActivityId,");
                    buf.append("tblCrmActivity.ActivityType,");
                    buf.append("tblCrmActivity.Description,");
                    buf.append("tblCrmActivity.DateDue,");
                    buf.append("tblCrmActivity.CreatedDate,");
                    buf.append("tblCrmActivity.AssignedToId,");
                    buf.append("tblCrmActivity.CreatedById ");
                    buf.append("FROM ");
                    buf.append("((tblCrmActivity join tblCrmAccount on ");
                    buf.append("(tblCrmAccount.Id = tblCrmActivity.AccountId))");
                    buf.append(" join tblCrmAddress on ");
                    buf.append("(tblCrmAddress.ObjectId = tblCrmAccount.Id))");
                     
                     */
                    
                    
                    //buf.append("SELECT ");
                    
                   /* buf.append("SELECT DISTINCT ");
                    buf.append("tblCrmAccount.Name,tblCrmAccount.Id,");
                    buf.append("tblCrmActivity.Id as ActivityId,");
                    buf.append("tblCrmActivity.ActivityType,");
                    buf.append("tblCrmActivity.Description,");
                    buf.append("tblCrmActivity.DateDue,");
                    buf.append("tblCrmActivity.CreatedDate,");
                    buf.append("tblCrmActivity.AssignedToId,");
                    buf.append("tblCrmActivity.CreatedById ");
                    buf.append("FROM ");
                    buf.append("((tblCrmActivity join tblCrmAccount on ");
                    buf.append("(tblCrmAccount.Id = tblCrmActivity.AccountId))");
                    buf.append(" join tblCrmAddress on ");
                    buf.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`))");
                    buf.append(" WHERE ");
                    buf.append("tblCrmActivity.CreatedById = ");
                    buf.append(" '" + userId + "'");
                    
                    if(getDashBoardStartDate()!=null && getDashBoardEndDate()!=null) {
                        
                        buf.append( " AND date(tblCrmActivity.CreatedDate) >='" + getDashBoardStartDate() + "'");
                        buf.append( " AND date(tblCrmActivity.CreatedDate) <='" + getDashBoardEndDate() + "'");
                    }else if(getDashBoardStartDate()!=null){
                        
                        buf.append( " AND date(tblCrmActivity.CreatedDate) ='" + getDashBoardStartDate() + "'");
                    }else if(getDashBoardEndDate()!=null){
                        
                        buf.append( " AND date(tblCrmActivity.CreatedDate) ='" + getDashBoardEndDate() + "'");
                    } else {
                        buf.append( " AND date(tblCrmActivity.CreatedDate) = CURDATE() ");
                    }
                    buf.append(" AND  tblCrmAddress.Country like '"+userWorkCountry+"' ");
                    buf.append(" ORDER BY tblCrmActivity.CreatedById,");
                    buf.append(" tblCrmAccount.Name, ");
                    buf.append("tblCrmActivity.CreatedDate DESC LIMIT 150");
                    */
                    
                    
buf.append("SELECT ");
buf.append(" DISTINCT tblCrmActivity.Id as ActivityId,");
                    buf.append("tblCrmAccount.Name,tblCrmAccount.Id,");
                    
                    buf.append("tblCrmActivity.ActivityType,");
                    buf.append("tblCrmActivity.Description,");
                    buf.append("tblCrmActivity.DateDue,");
                    buf.append("tblCrmActivity.CreatedDate,");
                    buf.append("tblCrmActivity.AssignedToId,");
                    buf.append("tblCrmActivity.CreatedById,ContactId,OpportunityId, ");
                     buf.append("tblCrmActivity.Comments,");
                    buf.append("tblCrmActivity.STATUS ");
                    buf.append("FROM ");
                    buf.append("((tblCrmActivity join tblCrmAccountTeam on ");
                    buf.append("(tblCrmAccountTeam.AccountId = tblCrmActivity.AccountId))");
                    buf.append(" join tblCrmAccount on ");
                    buf.append("(`tblCrmAccount`.`Id` = `tblCrmAccountTeam`.`AccountId`))");
                    buf.append(" WHERE ");
                    buf.append("tblCrmActivity.CreatedById = ");
                    buf.append(" '" + userId + "'");
                    
                    if(getDashBoardStartDate()!=null && getDashBoardEndDate()!=null) {
                        
                        buf.append( " AND date(tblCrmActivity.CreatedDate) >='" + getDashBoardStartDate() + "'");
                        buf.append( " AND date(tblCrmActivity.CreatedDate) <='" + getDashBoardEndDate() + "'");
                    }else if(getDashBoardStartDate()!=null){
                        
                        buf.append( " AND date(tblCrmActivity.CreatedDate) >='" + getDashBoardStartDate() + "'");
                    }else if(getDashBoardEndDate()!=null){
                        
                        buf.append( " AND date(tblCrmActivity.CreatedDate) <='" + getDashBoardEndDate() + "'");
                    } else {
                      buf.append( " AND date(tblCrmActivity.CreatedDate) = CURDATE() ");
                        //  buf.append(" AND tblCrmActivity.CreatedDate  BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() ");
                    }
                   
                    buf.append(" ORDER BY tblCrmActivity.CreatedById,");
                    buf.append(" tblCrmAccount.Name, ");
                    buf.append("tblCrmActivity.CreatedDate DESC LIMIT 150");
                    
                    activityQueryString = buf.toString();
                    buf = null;
                    
                    httpServletRequest.setAttribute(ApplicationConstants.QS_TEAM_ACTIVITIES_LIST,activityQueryString);
                    
                    setViewMemberSearch("hide");
                    
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    /*
     * The String makeICSFile(String priroty, String dtStart,String dtEnd,String summery,String description,String desMessage,String accName,String conName,String dueTime)
     * returns vcs file format file values as string and send its to the file.
     */
    public String makeICSFile(String priroty, String dtStart,String dtEnd,String summery,String description,String desMessage,String accName,String conName,String dueTime)throws Exception {
        
        String usrId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        String usrName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
        StringBuffer buf = new StringBuffer("BEGIN:VCALENDAR");
        buf.append("\n");
        buf.append("VERSION:5.0");
        buf.append("\n");
        
        String prodId="//Microsoft Corporation//Works 2000//EN";
        buf.append("PRODID:-"+prodId);
        buf.append("\n");
        buf.append("BEGIN:VEVENT");
        buf.append("\n");
        buf.append("SUMMARY;ENCODING=QUOTED-PRINTABLE:"+"AccountName:-"+accName+ ":" +  "ContactName:"+conName+ ":" +"EndDate"+dueTime);
        buf.append("\n");
        buf.append("ORGANIZER:MAILTO:"+usrId+"@miraclesoft.com");
        buf.append("\n");
        buf.append("SUBJECT; ENCODING=QUOTED-PRINTABLE:files");
        buf.append("\n");
        buf.append("DESCRIPTION; ENCODING=QUOTED-PRINTABLE:"+desMessage);
        buf.append("\n");
        buf.append("LOCATION; ENCODING=QUOTED-PRINTABLE:"+summery);
        buf.append("\n");
        buf.append("CATEGORIES;ENCODING=QUOTED-PRINTABLE:Gifts, Holiday");
        buf.append("\n");
        buf.append("DTSTART:"+dtStart);
        buf.append("\n");
        buf.append("DTEND:"+dtEnd);
        buf.append("\n");
        buf.append("PRIORITY:"+priroty);
        buf.append("\n");
        buf.append("END:VEVENT");
        buf.append("\n");
        buf.append("END:VCALENDAR");
        makeFile(buf.toString());
        
        return buf.toString();
    }
    
    
    public String makeFile(String output)throws Exception {
        String filePath = Properties.getProperty("Activiities.VCS.Path");
        String usrId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        filePath = filePath + Properties.getProperty("OS.Compatabliliy.Download")+ usrId+".vcs";
        try {
            File aFile = new File(filePath);
            FileOutputStream file = null;
            
            file = new FileOutputStream(aFile);
            
            FileChannel outChannel = file.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(output.length());
            byte[] bytes = output.getBytes();
            buf.put(bytes);
            buf.flip();
            
            outChannel.write(buf);
            file.close();
            
            
            String usrName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            
            
            MailAttachment  mailAttachment   = new MailAttachment();
            
            mailAttachment.setTo(usrId+"@miraclesoft.com");
            mailAttachment.setSubject("Mirage Calendar Activity");
            mailAttachment.setMessage(output);
            mailAttachment.setUpload(filePath);
            
            
            if(Properties.getProperty("Mail.Flag").equals("1")) {
                 mailAttachment.sendMail(usrId,usrName);
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return  filePath;
    }
    // Start New Field for getactivityManagerDetails
   
     public String getactivityManagerDetails() {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_ACTIVITY",userRoleId)){
                try{
                    DateUtility dateUtility = new DateUtility();
                    setEndDate(dateUtility.LastMonthLastDate());
                    setStartDate(dateUtility.FirstDateOfLastMonth());
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }  
     
 // End New Field for getactivityManagerDetails
	
    /**
     * The setServletRequest(HttpServletRequest httpServletRequest) is for binding request Object this Object mean's ActionObject
     */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
//mutators and accessors for Form attributes
    
    /**
     * The getId() is used for accessing id of Activity.
     * @ return int variable
     */
    public int getId() {
        return id;
    }
    
    /**
     * The setId(int id) is used for setting id of Activity.
     *
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * The getAccountId() is used for accessing AccountId of Activity.
     * @ return int variable
     */
    public int getAccountId() {
        return accountId;
    }
    
    /**
     * The setAccountId(int accountId) is used for setting AccountId of Activity.
     *
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    /**
     * The getContactId() is used for accessing ContactId of Activity.
     * @ return int variable
     */
    public int getContactId() {
        return contactId;
    }
    
    /**
     * The setContactId(int contactId) is used for setting contactId of Activity.
     *
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    
    /**
     * The getCampaignId() is used for accessing campaignId of Activity.
     * @ return int variable
     */
    public int getCampaignId() {
        return campaignId;
    }
    
    /**
     * The setCampaignId(int campaignId) is used for setting campaignId of Activity.
     *
     */
    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }
    
    /**
     * The getAssignedToId() is used for accessing assignedToId of Activity.
     * @ return String variable
     */
    public String getAssignedToId() {
        return assignedToId;
    }
    
    /**
     * The setAssignedToId(String assignedToId) is used for setting assignedToId of Activity.
     *
     */
    public void setAssignedToId(String assignedToId) {
        this.assignedToId = assignedToId;
    }
    
    /**
     * The getAssignedById() is used for accessing assignedById of Activity.
     * @ return String variable
     */
    public String getAssignedById() {
        return assignedById;
    }
    
    /**
     * The setAssignedById(String assignedById) is used for setting assignedById of Activity.
     *
     */
    public void setAssignedById(String assignedById) {
        this.assignedById = assignedById;
    }
    
    /**
     * The getActivityType() is used for accessing activityType of Activity.
     * @ return String variable
     */
    public String getActivityType() {
        return activityType;
    }
    
    /**
     * The setActivityType(String activityType) is used for setting activityType of Activity.
     *
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
    
    /**
     * The getStatus() is used for accessing status of Activity.
     * @ return String variable
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * The setStatus(String status) is used for setting status of Activity.
     *
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * The getPriority() is used for accessing priority of Activity.
     * @ return String variable
     */
    public String getPriority() {
        return priority;
    }
    
    /**
     * The setPriority(String priority) is used for setting priority of Activity.
     *
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    /**
     * The getDescription() is used for accessing description of Activity.
     * @ return String variable
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * The setDescription(String description) is used for setting description of Activity.
     *
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * The getComments() is used for accessing comments of Activity.
     * @ return String variable
     */
    public String getComments() {
        return comments;
    }
    
    /**
     * The setComments(String comments) is used for setting comments of Activity.
     *
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    /**
     * The getAlarm() is used for accessing alarm of Activity.
     * @ return String variable
     */
    public boolean getAlarm() {
        return alarm;
    }
    
    /**
     * The setAlarm(boolean alarm) is used for setting alarm of Activity.
     *
     */
    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }
    
    /**
     * The getCreatedDate() is used for accessing createdDate of Activity.
     * @ return Timestamp variable
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    
    /**
     * The setCreatedDate(Timestamp createdDate) is used for setting createdDate of Activity.
     *
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    
    /**
     * The getCreatedById() is used for accessing createdById of Activity.
     * @ return String variable
     */
    public String getCreatedById() {
        return createdById;
    }
    
    /**
     * The setCreatedById(String createdById) is used for setting createdById of Activity.
     *
     */
    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }
    
    /**
     * The getModifiedDate() is used for accessing modifiedDate of Activity.
     * @ return Timestamp variable
     */
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }
    
    /**
     * The setModifiedDate(Timestamp modifiedDate) is used for setting modifiedDate of Activity.
     *
     */
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    /**
     * The getModifiedById() is used for accessing modifiedById of Activity.
     * @ return String variable
     */
    public String getModifiedById() {
        return modifiedById;
    }
    
    /**
     * The setModifiedById(String modifiedById) is used for setting modifiedById of Activity.
     *
     */
    public void setModifiedById(String modifiedById) {
        this.modifiedById = modifiedById;
    }
    
    /**
     * The getDueDate() is used for accessing dueDate of Activity.
     * @ return String variable
     */
    public String getDueDate() {
        return dueDate;
    }
    
    /**
     * The setDueDate(String dueDate) is used for setting dueDate of Activity.
     *
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
//Getters and Setters for Pre Configurable Varaibles
    
    /**
     * The getActivityTypeList() is used for accessing activityTypeList of Activity.
     * @ return List variable
     */
    public List getActivityTypeList() {
        return activityTypeList;
    }
    
    /**
     * The setActivityTypeList(List activityTypeList) is used for setting activityTypeList of Activity.
     *
     */
    public void setActivityTypeList(List activityTypeList) {
        this.activityTypeList = activityTypeList;
    }
    
    /**
     * The getActivityStatusList() is used for accessing activityStatusList of Activity.
     * @ return List variable
     */
    public List getActivityStatusList() {
        return activityStatusList;
    }
    
    /**
     * The setActivityStatusList(List activityStatusList) is used for setting activityStatusList of Activity.
     *
     */
    public void setActivityStatusList(List activityStatusList) {
        this.activityStatusList = activityStatusList;
    }
    
    /**
     * The getPriorityList() is used for accessing priorityList of Activity.
     * @ return List variable
     */
    public List getPriorityList() {
        return priorityList;
    }
    
    /**
     * The setPriorityList(List priorityList) is used for setting priorityList of Activity.
     *
     */
    public void setPriorityList(List priorityList) {
        this.priorityList = priorityList;
    }
    
    /**
     * The getCampaignIdMap() is used for accessing campaignIdMap of Activity.
     * @ return Map variable
     */
    public Map getCampaignIdMap() {
        return campaignIdMap;
    }
    
    /**
     * The setCampaignIdMap(Map campaignIdMap) is used for setting campaignIdMap of Activity.
     *
     */
    public void setCampaignIdMap(Map campaignIdMap) {
        this.campaignIdMap = campaignIdMap;
    }
    
    /**
     * The getAssignedToIdMap() is used for accessing assignedToIdMap of Activity.
     * @ return Map variable
     */
    public Map getAssignedToIdMap() {
        return assignedToIdMap;
    }
    
    /**
     * The setAssignedToIdMap(Map assignedToIdMap) is used for setting assignedToIdMap of Activity.
     *
     */
    public void setAssignedToIdMap(Map assignedToIdMap) {
        this.assignedToIdMap = assignedToIdMap;
    }
    
    /**
     * The getCurrentActivity() is used for accessing currentActivity of Activity.
     * @ return ActivityVTO variable
     * {@link com.mss.mirage.crm.activities.ActivityVTO}
     */
    public ActivityVTO getCurrentActivity() {
        return currentActivity;
    }
    
    /**
     * The setCurrentActivity(ActivityVTO currentActivity) is used for setting currentActivity of Activity.
     *
     */
    public void setCurrentActivity(ActivityVTO currentActivity) {
        this.currentActivity = currentActivity;
    }
    
    /**
     * The getContactName() is used for accessing contactName of Activity.
     * @ return String variable
     */
    public String getContactName() {
        return contactName;
    }
    
    /**
     * The setContactName(String contactName) is used for setting contactName of Activity.
     *
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    /**
     * The getAccountName() is used for accessing accountName of Activity.
     * @ return String variable
     */
    public String getAccountName() {
        return accountName;
    }
    
    /**
     * The setAccountName(String accountName) is used for setting accountName of Activity.
     *
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    /**
     * The getDueDateTimeStamp() is used for accessing dueDateTimeStamp of Activity.
     * @ return Timestamp variable
     */
    public Timestamp getDueDateTimeStamp() {
        return dueDateTimeStamp;
    }
    
    /**
     * The setDueDateTimeStamp(Timestamp dueDateTimeStamp) is used for setting dueDateTimeStamp of Activity.
     *
     */
    public void setDueDateTimeStamp(Timestamp dueDateTimeStamp) {
        this.dueDateTimeStamp = dueDateTimeStamp;
    }
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
    public String getFormAction() {
        return formAction;
    }
    
    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }
    
    public String getDateWithOutTime() {
        return dateWithOutTime;
    }
    
    public void setDateWithOutTime(String dateWithOutTime) {
        this.dateWithOutTime = dateWithOutTime;
    }
    
    public Date getDashBoardStartDate() {
        return dashBoardStartDate;
    }
    
    public void setDashBoardStartDate(Date dashBoardStartDate) {
        this.dashBoardStartDate = dashBoardStartDate;
    }
    
    public Date getDashBoardEndDate() {
        return dashBoardEndDate;
    }
    
    public void setDashBoardEndDate(Date dashBoardEndDate) {
        this.dashBoardEndDate = dashBoardEndDate;
    }
    
    public String getCalDateStart() {
        return calDateStart;
    }
    
    public void setCalDateStart(String calDateStart) {
        this.calDateStart = calDateStart;
    }
    public String getCalAccountName() {
        return calAccountName;
    }
    
    public void setCalAccountName(String calAccountName) {
        this.calAccountName = calAccountName;
    }
    
    public String getCalContactName() {
        return calContactName;
    }
    
    public void setCalContactName(String calContactName) {
        this.calContactName = calContactName;
    }
    
    public String getViewMemberSearch() {
        return viewMemberSearch;
    }
    
    public void setViewMemberSearch(String viewMemberSearch) {
        this.viewMemberSearch = viewMemberSearch;
    }
    
    public String getAccId() {
        return accId;
    }
    
    public void setAccId(String pAccId) {
        this.accId = pAccId;
        this.setAccountId(Integer.parseInt(accId));
    }
    
    public String getContId() {
        return contId;
    }
    
    public void setContId(String pContId) {
        this.contId = pContId;
        this.setContactId(Integer.parseInt(contId));
    }
    
    public boolean getSendInvitation() {
        return sendInvitation;
    }
    
    public void setSendInvitation(boolean sendInvitation) {
        this.sendInvitation = sendInvitation;
    }
    
    public String getOfficeEmail() {
        return officeEmail;
    }
    
    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }
    
    public String getPersonalEmail() {
        return personalEmail;
    }
    
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    
    public int getActivitySummaryFlag() {
        return activitySummaryFlag;
    }

    public void setActivitySummaryFlag(int activitySummaryFlag) {
        this.activitySummaryFlag = activitySummaryFlag;
    }

    /**
     * @return the contactsMap
     */
    public Map getContactsMap() {
        return contactsMap;
    }

    /**
     * @param contactsMap the contactsMap to set
     */
    public void setContactsMap(Map contactsMap) {
        this.contactsMap = contactsMap;
    }

    /**
     * @return the oppurtunitiesMap
     */
    public Map getOppurtunitiesMap() {
        return oppurtunitiesMap;
    }

    /**
     * @param oppurtunitiesMap the oppurtunitiesMap to set
     */
    public void setOppurtunitiesMap(Map oppurtunitiesMap) {
        this.oppurtunitiesMap = oppurtunitiesMap;
    }

    /**
     * @return the oppurtunityId
     */
    public int getOppurtunityId() {
        return oppurtunityId;
    }

    /**
     * @param oppurtunityId the oppurtunityId to set
     */
    public void setOppurtunityId(int oppurtunityId) {
        this.oppurtunityId = oppurtunityId;
    }

    /**
     * @return the contactsHidden
     */
    public String getContactsHidden() {
        return contactsHidden;
    }

    /**
     * @param contactsHidden the contactsHidden to set
     */
    public void setContactsHidden(String contactsHidden) {
        this.contactsHidden = contactsHidden;
    }

    /**
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * @return the practiceName
     */
    public String getPracticeName() {
        return practiceName;
    }

    /**
     * @param practiceName the practiceName to set
     */
    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    /**
     * @return the contactType
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * @param contactType the contactType to set
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    /**
     * @return the practiceList
     */
    public List getPracticeList() {
        return practiceList;
    }

    /**
     * @param practiceList the practiceList to set
     */
    public void setPracticeList(List practiceList) {
        this.practiceList = practiceList;
    }

    /**
     * @return the wotEmailContactsMap
     */
    public Map getWotEmailContactsMap() {
        return wotEmailContactsMap;
    }

    /**
     * @param wotEmailContactsMap the wotEmailContactsMap to set
     */
    public void setWotEmailContactsMap(Map wotEmailContactsMap) {
        this.wotEmailContactsMap = wotEmailContactsMap;
    }
}
