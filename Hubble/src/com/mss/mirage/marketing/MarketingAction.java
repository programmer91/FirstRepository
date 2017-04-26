/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  January 25, 2008, 6:13 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : ScreenAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.marketing;

import com.constantcontact.components.contacts.ContactList;
import com.constantcontact.services.contactlists.ContactListService;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author Rajanikanth Teppala<rteppala@miraclesoft.com>
 */
public class MarketingAction implements Action,ServletRequestAware,ParameterNameAware, ParameterAware,ServletResponseAware{
    
    /** The resultType is used for storing type of message. */
    private String resultType;
    
    /** The resultMessage is used for storing resulted message. */
    private String resultMessage;
    
    /** The httpServletRequest is used for binding request object to this class. */
    private HttpServletRequest httpServletRequest;
    
    /** The hibernateDataProvider is used for invoking application level methods.
     *{@link com.mss.mirage.util.HibernateDataProvider}
     */
    private HibernateDataProvider hibernateDataProvider;
    
    /**
     * 
     * This Object defaultDataProvider provides the reference variable for DefaultDataProvider
     */
    private DefaultDataProvider defaultDataProvider;
    
    /**
     * 
     * This Object dataSourceDataProvider provides the reference variable for DataSourceDataProvider
     */
    private DataSourceDataProvider dataSourceDataProvider;
    
    /**
     * Creating a reference variable for <code>MarketingService</code> class .
     *  { @link com.mss.mirage.screens.MarketingService }
     */
    private MarketingService screenService;
    
    /**
     * Creating a reference variable for <code>MarketingVTO</code> class .
     *  { @link com.mss.mirage.screens.MarketingVTO }
     */
    private MarketingVTO currentMarketing;
    
    /** The activityTypeList is used for storing activityType. */
    private List activityTypeList = new ArrayList();
    
    /** The priorityList is used for storing priority. */
    private List priorityList = new ArrayList();
    
    /** The campaignIdMap is used for storing campaignId. */
    private Map campaignIdMap;
    
    /** The assignedToIdMap is used  for storing assignedToId. */
    private Map assignedToIdMap;
    
    /** The activityStatusList is used for storing activityStatus. */
    private List activityStatusList = new ArrayList();
    
    /* Creating a String accountName to store the account name */
    /**
     * 
     * This variable accountName is to get/store Account Name
     */
    private String accountName;
    /* The String urlPath is used for storing the urlPath of the particular account */
    /**
     * 
     * This variable urlPath is to get/store URL Path
     */
    private String urlPath;
    /* The String homePhone is used for storing the homePhone of the particular account */
    /**
     * 
     * This variable homePhone is to get/store Home Phone
     */
    private String homePhone;
    /* The String stockSymbol is used for storing the stockSymbol of the account */
    /**
     * 
     * This variable stockSymbol is to get/store Stock Symbol
     */
    private String stockSymbol;
    /* Creating a String lastModifyBy and store value from the login user-id in the form. */
    /**
     * 
     * This variable lastModifiedBy is to get/store Last Modified By
     */
    private String lastModifyBy;
    /* Creating a Timestamp lastModifyDate to get date and time from the local system. */
    /**
     * 
     * This variable lastModifiedDate is to get/store Last Modified Date
     */
    private Timestamp lastModifyDate;
    
    /**
     * 
     * This variable accName is to get/store Account Name
     */
    private String accName;
    /**
     * 
     * This variable accId is to get/store Account Id
     */
    private int accountId;
    /**
     * 
     * This variable contactsId is to get/store ContactsId
     */
    private int contactsId;
    /**
     * 
     * This variable accountId is to get/store Account Id
     */
    private int getAccountId;
    /**
     * 
     * This variable contactName is to get/store Contact Name
     */
    private String contactName;
    
    /** The firstNames is used for storing the first name of particular Contact. */
    private String firstNames;
    /** The lastNames is used for storing the last name of particular Contact. */
    private String lastNames;
    /** The middleNames is used for storing the middle name of particular Contact. */
    private String middleName;
    /** The emails is used for storing the office email  of particular Contact. */
    private String emails;
    /** The homephone is used for storing the home Phone number  of particular Contact. */
    private String homePhoneNo;
    /** The leadSource is used for storing the Source of particular Contact. */
    private String source;
    
    /* The Boolean mercator is used for storing the mercator of the particular account */
    /**
     * 
     * This variable mercator is to get/store Mercator value
     */
    private boolean mercator;
    /* The Boolean gentran is used for storing the gentran of the particular account */
    /**
     * 
     * This variable gentran is to get/store gentran value
     */
    private boolean gentran;
    /* The Boolean seeBeyond is used for storing the seeBeyond of the particular account */
    /**
     * 
     * This variable seeBeyond is to get/store SeeBeyond value
     */
    private boolean seeBeyond;
    /* The Boolean messageBroker is used for storing the messageBroker of the particular account */
    /**
     * 
     * This variable messageBroker is to get/store Messgae Broker value
     */
    private boolean messageBroker;
    /* The Boolean ics is used for storing the ics of the particular account */
    /**
     * 
     * This variable ics is to get/store ICS value
     */
    private boolean ics;
    /* The Boolean wps is used for storing the wps of the particular account */
    /**
     * 
     * This variable wps is to get/store WPS value
     */
    private boolean wps;
    /* The Boolean tibco is used for storing the tibco of the particular account */
    /**
     * 
     * This variable tibco is to get/store Tibco value
     */
    private boolean tibco;
    /* The Boolean webMethods is used for storing the webMethods of the particular account */
    /**
     * 
     * This variable webMethods is to get/store WebMethods value
     */
    private boolean webMethods;
    /* The Boolean sap is used for storing the sap of the particular account */
    /**
     * 
     * This variable sap is to get/store SAP value
     */
    private boolean sap;
    /* The Boolean oracleApps is used for storing the oracleApps of the particular account */
    /**
     * 
     * This variable oracleApps is to get/store Oracle Apps
     */
    private boolean oracleApps;
    /* The Boolean jdEdwards is used for storing the jdEdwards of the particular account */
    /**
     * 
     * This variable jdEdwards is to get/store JDEdwards
     */
    private boolean jdEdwards;
    /* The Boolean peopleSoft is used for storing the peopleSoft of the particular account */
    /**
     * 
     * This variable peopleSoft is to get/store PeopleSoft value
     */
    private boolean peopleSoft;
    /* The Boolean harbinger is used for storing the harbinger of the particular account */
    /**
     * 
     * This variable harbinger is to get/store Harbinger value
     */
    private boolean harbinger;
    /* The Boolean wdi is used for storing the wdi of the particular account */
    /**
     * 
     * This variable wdi is to get/store WDI value
     */
    private boolean wdi;
    /* The Boolean vitria is used for storing the vitria of the particular account */
    /**
     * 
     *  This variable vitria is to get/store Vitria value
     */
    private boolean vitria;
    /* The Boolean biztalkServer is used for storing the biztalkServer of the particular account */
    /**
     * 
     * This variable biztalkServer is to get/store BiztalkServer value
     */
    private boolean biztalkServer;
    /* The Boolean siebel is used for storing the siebel of the particular account */
    /**
     * 
     * This variable siebel is to get/store Siebel Value
     */
    private boolean siebel;
    /* The Boolean baan is used for storing the baan of the particular account */
    /**
     * 
     * This variable baan is to get/store Baan value
     */
    private boolean baan;
    /* The Boolean beaPortals is used for storing the beaPortals of the particular account */
    /**
     * 
     * This variable beaPortals is to get/store beaPortals
     */
    private boolean beaPortals;
    /* The Boolean oraclePortals is used for storing the oraclePortals of the particular account */
    /**
     * 
     * This variable oraclePortals is to get/store Oracle Portals value
     */
    private boolean oraclePortals;
    /* The Boolean ibmPortals is used for storing the ibmPortals of the particular account */
    /**
     * 
     * This variable ibmPortals is to get/store IBM Portals value
     */
    private boolean ibmPortals;
    /* The Boolean sharePoint is used for storing the sharePoint of the particular account */
    /**
     * 
     * This variable sharePoint is to get/store SharePoint value
     */
    private boolean sharePoint;
    /* The Boolean sapPortals is used for storing the sapPortals of the particular account */
    /**
     * 
     * This variable sapPortals is to get/store SAPPortals Value
     */
    private boolean sapPortals;
    /* The Boolean microsoft is used for storing the microsoft of the particular account */
    /**
     * 
     * This variable microsoft is to get/store Microsoft value
     */
    private boolean microsoft;
    
    /** The activityType is used for storing activityType. */
    private String activityType;
    /** The contactActivId is used for storing the id of Contact. */
    private int contactActivId;
    /** The accountActivId is used for storing accountID.  */
    private int accountActivId;
    /** The id used for storing id of activity. */
    private int activityId = 0;
    /** The priority is used for storing priority of activity. */
    private String priority;
    /** The campaignId is used for storing campaignId. */
    private int campaignId;
    
    /** The assignedToId is used for storing assignedToId. */
    private String assignedToId;
    /** The status is used for storing status of activity. */
    private String status;
    /** The dueDate is used for storing dueDate. */
    private Timestamp dueDate;
    /** The alarm is used for storing alarm. */
    private boolean alarm;
    /** The description is used for storing description. */
    private String description;
    /** The comments is used for storing comments.  */
    private String comments;
    
    /** The createdById is used for storing createdById. */
    private String createdById;
    /** The modifiedById is used for storing modifiedById. */
    private String modifiedById;
    /** The assignedById is used for storing assignedById. */
    private String assignedById;
    /** The createdDate is used for storing createdDate. */
    private Timestamp createdDate;
    /**  The modifiedDate is used for storing modifiedDate. */
    private Timestamp modifiedDate;
    
    /** The employeeName is used for storing employeeName. */
    private String employeeName;
    /** The activityType is used for storing activityType. */
    private String activityTypeName;
    
    /**
     * 
     * This variable id is to get/store Id
     */
    private int id;
    
    /**
     * 
     * This variable submitForm is to get submitForm
     */
    private String submitFrom;
    
    /**
     * 
     * This variable accountSearchBy is to get/store Account SearchBy
     */
    private String accountSearchBy;
    /**
     * 
     * This variable userRoleId is to get/store userRoleId
     */
    private int userRoleId;
    /**
     * Creating a StringBuffer queryStringBuffer used to append SQL Query
     */
    private StringBuffer queryStringBuffer;
    
    /**
     * 
     * This variable selectedTab is to get/store selectedTab
     */
    private String selectedTab ="accountsListTab";
    
    /**
     * 
     * This variable showGrid is to get/store showGrid
     */
    private String showGrid = "Invisible";
    
    /**
     * Creates a new instance of MarketingAction
     */
    
    private String fromDate;
    //Search Fields
    private String createdDateFrom;
    private String createdDateTo;
    private String jobId;
    private String title;
     
    private String name;
    private String email;
     private List locationList;
    
    private int jobConsultantId;
    
    
    
private List timeList;
private String startTime;
private String endTime;
private String midDayFrom;
private String midDayTo;
private String eventId;
private String eventSearchType;
private String primarySpeakerExist;
private String tableName;
private List trackNamesList;
private String trackName;
private String qmeetYear;
private Map qmeetMap;
private String qmeetTitleId;
private Map associatedEventMap = new HashMap();

private String dateOfPublish;
private String searchSeriesTitle;
private String searchSeriesStatus;
private String eventType;
private String searchSeriesTrack;
private String seriesId;
private Map unassociatedEventMap = new HashMap();
private String associatedEventId;
private String primarySpeakerId;

//-------------
private Map eventSpeakerMap = new TreeMap();
 //   private Map salesTeamExceptAccountTeamMap = new TreeMap();
   private Map speakersMapExceptEventSpeakerMap = new TreeMap();
    //private String primarySpeakerId;
  
    //private Map accountTeamParameters;
    private Map speakersParameters;
    private String eventTitle;
    private String objectType;
    private Map trackNamesMap;
    
    
    
private String accessType;
    
    private String startDate;
    private String endDate;
    //newly creadted by me;
    private String investmentName1;
    private String startDateInvestment;
    private String endDateInvestment;
    private String countryInvestment;
    private double totalExpenseAmount;
    private String locationInvestment;
    private String investmentDesc;
    private String attachInvestment;
    private double expensesAmountFrom;
    private double expensesAmountTo;
    private HttpServletResponse httpServletResponse;
private int investmentId;

private Map contactsList;
 private String accContacts;
private String createdBy;
private String contactsHidden;
private Timestamp expiryDate;
private int leadId;
private List contactsIdList;
  private String currentAction;
private Map investmentList;
    private String campaignTitle;
    private String campaignStatus;
    private String campaignStartDate;
    private String campaignEndDate;
    private String campaignName;
    private int navId;
    private int tempVar;
    private String sendDates;
    private List clientCurrencyList;
    private int isInvestmentRecordsExist;
    // Emeet Fields start
    private Map activeEmployeeMap;
    private List accessTypeList;
    private String emeetAccessType;
     private String emeetAttendeesEmail;
     private String emeetMeetingAccessStatus;
     private String emeetStatusByDate;
     private String executiveMeetMonthSearch;
     private String emeetType;
    private String emeetToDate;
    private String emeetFromDate;

    private String contactStartDate;
private String contactEndDate;
    // Emeet Fields end

 private String iotNameSearch;
private String iotZipCodeSearch;
 private String signatureNameSearch;
private String signatureEmailSearch;


private String investmentTitle;
private Map investmentBDMsMap= new TreeMap();;
private Map bdmsMapExceptInvestmentBDMMap= new TreeMap();;
private Map bdmParameters;
private Map analystMap;
private Map linkedLeadAccountsMap;
private int dashboardFlag;


private Map bdmMap;
private String bdmId;
 private Map accountsMap=new HashMap();
private List leadStatesList;

private String investmentName;
private Map salesMap;
private String oppurtunityAccountName;

 private Map locationsMap;
private Map constantContactList;

private Map insideSalesBDEMap;
private String bdeLoginId;
  private String lastActivityFrom;
    private String lastActivityTo;
    private String opportunity;
    private String touched;
    private String backToFlag;
    
    
  private String passedBy1hidden;
   private String passedBy2hidden;
   private String passedBy3hidden;
   private String comments1;
   private String comments2;
   private String comments3;
   private String nextSteps1;
   private String nextSteps2;
   private String nextSteps3; 
   
   private List passedBy1List;
   private List passedBy2List;
   private List passedBy3List;
   private String investmentType;
   private String reportsTo;
    private Map allEmployeesList;
     private Map upcommingConferencesList;
    private Map totalConferencesList;
    public MarketingAction() {
    }
    
    /**
     * The prepare method used for getting all variables in the
     * select box and getting these values from a class called ApplicationConstants.
     * And these variables are getting initailised when we see the form
     * everytime because prepare method is also called everytime.
     * 
     * 
     * @return The return type of this method is a String
     *         which depends on the action being performed.
     */
    public String prepare(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_MARKETING",userRoleId)){
                try{
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    setActivityTypeList(hibernateDataProvider
                            .getCrmActivityTypes(ApplicationConstants.CRM_ACTIVITY_TYPE_OPTIONS));
                    setPriorityList(defaultDataProvider
                            .getCrmActivityPriority(ApplicationConstants.CRM_ACTIVITY_PRIORITY_OPTIONS));
                    setCampaignIdMap(hibernateDataProvider
                            .getCampaignNames(ApplicationConstants.CRM_CAMPAIGN_OPTIONS));
                    
                    //List of Employees from Sales Department only
                    
                    setAssignedToIdMap(dataSourceDataProvider.getEmployeeNamesByUserId("sales"));
                    
                    setActivityStatusList(hibernateDataProvider
                            .getCrmActivityStatus(ApplicationConstants.CRM_ACTIVITY_STATUS_OPTIONS));
                    
                    setEmployeeName(dataSourceDataProvider.getEmployeeNameByEmpNo(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString())));
                    
                    setCreatedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    setModifiedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    setAssignedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//Close Session Checking
        }
        return resultType;
    }
    
    /**
     * The getActivityQuery method is used to search the Activty related info.directly from the form
     * And these values are populated thru database using different search
     * criterias like: Activity Type,AccountId,ContactId
     * @return String
     */
    public String getActivityQuery() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_QUERY_MARKETING",userRoleId)){
                try{
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    String accId = null;
                    accId = httpServletRequest.getParameter("accId");
                    String contId = httpServletRequest.getParameter("contId");
                    String actType = httpServletRequest.getParameter("actType");
                    
                    if(accId != null){
                        setActivityTypeName(actType);
                        setAccountActivId(Integer.parseInt(accId));
                        setContactActivId(Integer.parseInt(contId));
                        
                        setContactsId(getContactActivId());
                        setGetAccountId(getAccountActivId());
                        setAccountId(getAccountActivId());
                    }
                    setSelectedTab("ActivityTab");
                    setAccName(dataSourceDataProvider.getAccountName(getAccountActivId()));
                    setContactName(dataSourceDataProvider.getContactName(getContactActivId()));
                    
                    setShowGrid("Visible");
                    
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        queryStringBuffer = new StringBuffer();
                        //dataSourceDataProvider = DataSourceDataProvider.getInstance();
                        
                        //setSelectedTab("ActivityTab");
                        
                        queryStringBuffer.append("SELECT DISTINCT ");
                        //SELECT Id,ActivityType,Status,Description,DateDue,CreatedDate,AssignedToId FROM tblCrmActivity";
                        queryStringBuffer.append("Id,AccountId,ContactId,ActivityType,Status,Description,DateDue,CreatedDate,AssignedToId ");
                        queryStringBuffer.append("FROM tblCrmActivity ");
                        
                        if(null == getActivityType()) setActivityType("");
                        if(null == getPriority()) setPriority("");
                        if(null == getAssignedToId()) setAssignedToId("");
                        if(null == getStatus()) setStatus("");                        
                        if(null == getDescription()) setDescription("");
                        if(null == getComments()) setComments("");
                        if(null == getCreatedById()) setCreatedById("");
                        if(null == getModifiedById()) setModifiedById("");
                        if(null == getAssignedById()) setAssignedById("");
                        if(null == getActivityTypeName()) setActivityTypeName("");
                        
                        if(null == getSubmitFrom()) setSubmitFrom("");
                        
                        
                        
                        if(false == isAlarm()) setAlarm(false);
                        
                        if((!"".equals(getActivityType()))
                        || (!"".equals(getPriority()))
                        || (!"".equals(getAssignedToId()))
                        || (!"".equals(getStatus()))
                        ||(!"".equals(getDescription()))
                        || (!"".equals(getComments()))
                        || (!"".equals(getCreatedById()))
                        || (!"".equals(getModifiedById()))
                        || (!"".equals(getAssignedById()))
                        || (!"".equals(getDueDate()))
                        || (!"".equals(getSubmitFrom()))
                        
                        || (!"".equals(getActivityTypeName()))) {
                            queryStringBuffer.append(" WHERE ");
                        }
                        
                        int columnCounter = 0;
                        
                        if(!"".equals(getActivityTypeName()) && columnCounter==0){
                            if((getActivityTypeName().indexOf("*") == -1)&&(getActivityTypeName().indexOf("%") == -1)) setActivityTypeName(getActivityTypeName()+"*");
                            setActivityTypeName(getActivityTypeName().replace("*","%"));
                            queryStringBuffer.append("ActivityType LIKE '" + getActivityTypeName() + "' ");
                            columnCounter ++;
                        }else if(!"".equals(getActivityTypeName()) && columnCounter!=0){
                            if((getActivityTypeName().indexOf("*") == -1)&&(getActivityTypeName().indexOf("%") == -1)) setActivityTypeName(getActivityTypeName()+"*");
                            setActivityTypeName(getActivityTypeName().replace("*","%"));
                            queryStringBuffer.append(" AND ActivityType LIKE '" + getActivityTypeName() + "' ");
                        }
                        
                        if(!"".equals(getAccountActivId()) && columnCounter==0){
                            queryStringBuffer.append(" AccountId ="+getAccountActivId());
                            columnCounter ++;
                        }else if(!"".equals(getAccountActivId()) && columnCounter!=0){
                            queryStringBuffer.append(" AND AccountId ="+getAccountActivId());
                        }
                        
                        if(!"".equals(getContactActivId()) && columnCounter==0){
                            queryStringBuffer.append(" ContactId ="+getContactActivId());
                            columnCounter ++;
                        }else if(!"".equals(getContactActivId()) && columnCounter!=0){
                            queryStringBuffer.append(" AND ContactId ="+getContactActivId());
                        }
                        
                        queryStringBuffer.append(" ORDER BY CreatedDate LIMIT 40");
                        
                        setAccountSearchBy("myAccounts");
                        
//                        setAccName(dataSourceDataProvider.getAccountName(getAccountActivId()));
//                        setContactName(dataSourceDataProvider.getContactName(getContactActivId()));
//                        screenService = ServiceLocator.getScreenService();
//                        setCurrentMarketing(screenService.getScreenActivityVTO(this));
                        
                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ACTIVITY_QUERY) != null){
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ACTIVITY_QUERY);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ACTIVITY_QUERY,queryStringBuffer.toString());
                        
                    }
                    prepare();
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    //ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        //resultType = SUCCESS;
        return resultType;
    }
    
    /**
     * The getAllActivityQuery method is used to search the AllActivityTypes Activty related info.directly from the form
     * And these values are populated thru database using different search
     * criterias like: Activity Type,AccountId,ContactId
     * 
     * 
     * @return String
     */
    public String getAllActivityQuery(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_QUERY_ALL_MARKETING",userRoleId)){
                try{
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    String accId = null;
                    accId = httpServletRequest.getParameter("accId");
                    String contId = httpServletRequest.getParameter("contId");
                    String actType = httpServletRequest.getParameter("actType");
                    
                    if(accId != null){
                        
                        setAccountActivId(Integer.parseInt(accId));
                        setContactActivId(Integer.parseInt(contId));
                        
                        setContactsId(getContactActivId());
                        setGetAccountId(getAccountActivId());
                        setAccountId(getAccountActivId());
                    }
                    setSelectedTab("ActivityTab");
                    setAccName(dataSourceDataProvider.getAccountName(getAccountActivId()));
                    setContactName(dataSourceDataProvider.getContactName(getContactActivId()));
                    
                    setShowGrid("Visible");
                    
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        queryStringBuffer = new StringBuffer();
                        
                        //setSelectedTab("ActivityTab");
                        
                        queryStringBuffer.append("SELECT DISTINCT ");
                        //SELECT Id,ActivityType,Status,Description,DateDue,CreatedDate,AssignedToId FROM tblCrmActivity";
                        queryStringBuffer.append("Id,AccountId,ContactId,ActivityType,Status,Description,DateDue,CreatedDate,AssignedToId ");
                        queryStringBuffer.append("FROM tblCrmActivity ");
                        
                        if(null == getActivityType()) setActivityType("");
                        if(null == getPriority()) setPriority("");
                        if(null == getAssignedToId()) setAssignedToId("");
                        if(null == getStatus()) setStatus("");                        
                        if(null == getDescription()) setDescription("");
                        if(null == getComments()) setComments("");
                        if(null == getCreatedById()) setCreatedById("");
                        if(null == getModifiedById()) setModifiedById("");
                        if(null == getAssignedById()) setAssignedById("");
                        if(null == getActivityTypeName()) setActivityTypeName("");
                        if(null == getSubmitFrom()) setSubmitFrom("");
                        
                        
                        if(false == isAlarm()) setAlarm(false);
                        
                        if((!"".equals(getActivityType()))
                        || (!"".equals(getPriority()))
                        || (!"".equals(getAssignedToId()))
                        || (!"".equals(getStatus()))
                        ||(!"".equals(getDescription()))
                        || (!"".equals(getComments()))
                        || (!"".equals(getCreatedById()))
                        || (!"".equals(getModifiedById()))
                        || (!"".equals(getAssignedById()))
                        || (!"".equals(getDueDate()))
                        
                        || (!"".equals(getSubmitFrom())))
                            
                            queryStringBuffer.append(" WHERE ");
                        else
                            queryStringBuffer.append(" WHERE ");
                        int columnCounter = 0;
                        
                        if(!"".equals(getAccountActivId()) && columnCounter==0){
                            queryStringBuffer.append(" AccountId ="+getAccountActivId());
                            columnCounter ++;
                        }else if(!"".equals(getAccountActivId()) && columnCounter!=0){
                            queryStringBuffer.append(" AND AccountId ="+getAccountActivId());
                        }
                        
                        if(!"".equals(getContactActivId()) && columnCounter==0){
                            queryStringBuffer.append(" ContactId ="+getContactActivId());
                            columnCounter ++;
                        }else if(!"".equals(getContactActivId()) && columnCounter!=0){
                            queryStringBuffer.append(" AND ContactId ="+getContactActivId());
                        }
                        
                        queryStringBuffer.append(" ORDER BY CreatedDate LIMIT 40");
                        
                        setAccountSearchBy("myAccounts");
//                       please refer backup for commented code
                        
                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ACTIVITY_QUERY) != null){
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ACTIVITY_QUERY);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ACTIVITY_QUERY,queryStringBuffer.toString());
                        
                    }
                    prepare();
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    //ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        //resultType = SUCCESS;
        return resultType;
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
     * The getActivity() is used for retrieving the activity from database server.
     * Here editing means when grid gets selected on the form and each particular activity values
     * are to be displayed so that it can be used to edit.
     * 
     * 
     * @return The return type of this method is a String
     *         which depends on whether all the values are successfully edited or not.
     */
    public String getActivity(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_ACTIVITY_MARKETING",userRoleId)){
                try{
                    //hibernateDataProvider = HibernateDataProvider.getInstance();
                    
                    setCurrentMarketing(ServiceLocator.getMarketingService().getActivity(this.getId()));
                    setSelectedTab("ActivityTab");
                    setAccountSearchBy("myAccounts");
                    setShowGrid("Visible");
                    //Generating content for select fields
                    prepare();
                    
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
    
     public String getEamilCampaignList()
		{
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    setCampaignIdMap(hibernateDataProvider
                            .getCampaignNames(ApplicationConstants.CRM_CAMPAIGN_OPTIONS));
                    setCreatedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                   
        resultType= SUCCESS;
        }
        
    }
        return resultType;
    }
    
//    public String websiteJobSearch()
//    {
//          resultType = LOGIN;
//        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
//            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
//            resultType = "accessFailed";
//            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
//                try{ 
//         
//           
////httpServletRequest.setAttribute("list", listOLists); 
//         if(httpServletRequest.getSession(false).getAttribute("jobslist")!=null){
//             httpServletRequest.getSession(false).removeAttribute("jobslist");
//         }
//                   
//                              
//                    
//                    resultType = SUCCESS;
//                    
//                }catch (Exception ex){
//                    //ex.printStackTrace();
//                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
//                    resultType =  ERROR;
//                }
//            }
//        }//Close Session Checking
//        return resultType;
//    }
//                
//        public String jobSearch()
//    {
//          resultType = LOGIN;
//        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
//            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
//            resultType = "accessFailed";
//            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
//                try{ 
//                    List mainList = new ArrayList();
//                   
//           
//mainList = ServiceLocator.getMarketingService().doJobSearch(this);
//    
//        httpServletRequest.getSession(false).setAttribute("jobslist", mainList);
//
//                              
//                    
//                    resultType = SUCCESS;
//                    
//                }catch (Exception ex){
//                    //ex.printStackTrace();
//                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
//                    resultType =  ERROR;
//                }
//            }
//        }//Close Session Checking
//        return resultType;
//    }
//           
//   public String appliedConsultantList(){
//        resultType = LOGIN;
//        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
//            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
//            resultType = "accessFailed";
//            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
//                try{ setJobId(getJobId());
//                     List mainList = null;
//                    mainList = ServiceLocator.getMarketingService().getApplicantsList(this);
//    
//        httpServletRequest.getSession(false).setAttribute("applicantslist", mainList);
//                resultType = SUCCESS;
//                    
//                }catch (Exception ex){
//                    //ex.printStackTrace();
//                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
//                    resultType =  ERROR;
//                }
//            }
//        }//Close Session Checking
//        return resultType;
//   }                
//   
    
 public String websiteJobSearch()
    {
          resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
           
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
         
           
//httpServletRequest.setAttribute("list", listOLists); 
         if(httpServletRequest.getSession(false).getAttribute("jobslist")!=null){
             httpServletRequest.getSession(false).removeAttribute("jobslist");
         }
         setLocationList(ServiceLocator.getMarketingService().getLocationList());
                    /*   	String[] csvStrings = new String[] {
    			"1001,c.cdesc,good",
    			"1002,java,javadesc,good",
    			"1003,sterer,srdesc,bad"
    	};

    	List<List<String>> csvList = new ArrayList<List<String>>();

    	//pretend you're looping through lines in a file here
    	for(String line : csvStrings)
    	{
    		String[] linePieces = line.split(",");
    		List<String> csvPieces = new ArrayList<String>(linePieces.length);
    		for(String piece : linePieces)
    		{
    			csvPieces.add(piece);
    		}
    		csvList.add(csvPieces);
                System.out.println("---"+csvList);
    	}
        httpServletRequest.setAttribute("list", csvList);*/

                              
                    
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
                
        public String jobSearch()
    {
          resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                    List mainList =null;
                   /* List subList = new ArrayList();
                    subList.add(10001);
                    subList.add("JavaDeveloper");
                    subList.add("Java/J2ee");
                    subList.add("Active");
                    mainList.add(subList);
                    subList = new ArrayList();
                    subList.add(10002);
                    subList.add(".NetDeveloper");
                    subList.add("C,C#");
                    subList.add("Active");
                    mainList.add(subList);subList = new ArrayList();
                    subList.add(10003);
                    subList.add("SAPDeveloper");
                    subList.add("SAP");
                    subList.add("Active");
                    mainList.add(subList);subList = new ArrayList();
                    subList.add(10004);
                    subList.add("DBDeveloper");
                    subList.add("Sql,Pl/Sql");
                    subList.add("Active");
                    mainList.add(subList);
                   */ 
           setLocationList(ServiceLocator.getMarketingService().getLocationList());
mainList = ServiceLocator.getMarketingService().doJobSearch(this);
    
        httpServletRequest.getSession(false).setAttribute("jobslist", mainList);

                              
                    
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
           
   public String appliedConsultantList(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ setJobId(getJobId());
                     List mainList = null;
                    mainList = ServiceLocator.getMarketingService().getApplicantsList(this);
    
        httpServletRequest.getSession(false).setAttribute("applicantslist", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }                
   
   public String websiteLatestJobApplications() {
      // System.out.println("websiteLatestJobApplications-->");
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ setJobId(getJobId());
                     List mainList = null;
                    mainList = ServiceLocator.getMarketingService().websiteLatestJobApplications();
                    // mainList = ServiceLocator.getMarketingService().getLocationList1();
                    
    if(httpServletRequest.getSession(false).getAttribute("latestJobsAppliedlist")!=null){
        httpServletRequest.getSession(false).removeAttribute("latestJobsAppliedlist");
    }
  //  System.out.println("mainList-->"+mainList);
        httpServletRequest.getSession(false).setAttribute("latestJobsAppliedlist", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
   
 /*Event posting
    * Author : Santosh Kola
    * Date : 07/15/2015
    * 
    */
   public String eventManagement() {
       
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                    setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
    if(httpServletRequest.getSession(false).getAttribute("eventslist")!=null){
        httpServletRequest.getSession(false).removeAttribute("eventslist");
    }
 
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
   
   
   
   public String webEventSearch() {
       
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                    setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    List mainList =null;
                    
                     setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                  //  System.out.println("Eventtype11--->"+getEventSearchType());
        mainList = ServiceLocator.getMarketingService().doEventSearch(this);
   // System.out.println("mainList size-->"+mainList.size());
        httpServletRequest.getSession(false).setAttribute("eventslist", mainList);
        resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
   
   
   
public String getEventSpeakers(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                    /* List mainList = null;
                    mainList = ServiceLocator.getMarketingService().getEventSpeakers(this);
                    setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("eventSpeakersList", mainList);
                     * */
                    setEventId(getEventId());
                    setObjectType(getObjectType());
                   // setPrimarySpeakerId(ServiceLocator.getMarketingService().getPrimarySpeaker(Integer.parseInt(getEventId()),"EV"));
                     setPrimarySpeakerId(ServiceLocator.getMarketingService().getPrimarySpeaker(Integer.parseInt(getEventId()),getObjectType()));
                   // System.out.println("setPrimarySpeakerId size-->"+getPrimarySpeakerId());
                      //  setAccountTeamMap(accountService.getAccountTeamByAccountId(getId(), getPrimaryTeamMember()));
                     // setEventSpeakerMap(ServiceLocator.getMarketingService().getSpeakersByEventId(Integer.parseInt(getEventId()), getPrimarySpeakerId(),"EV"));
                     setEventSpeakerMap(ServiceLocator.getMarketingService().getSpeakersByEventId(Integer.parseInt(getEventId()), getPrimarySpeakerId(),getObjectType()));
                   setEventTitle(ServiceLocator.getMarketingService().getEventTitleByEventId(getEventId(),getObjectType()));
                      
                     // System.out.println("setEventSpeakerMap size-->"+getEventSpeakerMap());
                        //setSalesTeamExceptAccountTeamMap(accountService.getAllSalesTeamExceptAccountTeam(getAccountTeamMap()));
                      setSpeakersMapExceptEventSpeakerMap(ServiceLocator.getMarketingService().getAllPeaopleExceptEventSpeakers(getEventSpeakerMap()));
                    //  System.out.println("setSpeakersMapExceptEventSpeakerMap size-->"+getSpeakersMapExceptEventSpeakerMap());
                      //  setVendorTeamMap(accountService.getAllVendorTeam(getAccountTeamMap()));

                        httpServletRequest.setAttribute("primarySpeakerId", getPrimarySpeakerId());
                     setResultMessage(getResultMessage());
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   } 
   
    
    
     public String completedEvents() {
       
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                    setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
    if(httpServletRequest.getSession(false).getAttribute("completedEventslist")!=null){
        httpServletRequest.getSession(false).removeAttribute("completedEventslist");
    }
 
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
      public String completedEventSearch() {
       
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                    setTimeList(DataSourceDataProvider.getInstance().getTimeList());
                    List mainList =null;
                    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                    
                 //   System.out.println("Eventtype11--->"+getEventSearchType());
        mainList = ServiceLocator.getMarketingService().doCompletedEventSearch(this);
    
        httpServletRequest.getSession(false).setAttribute("completedEventslist", mainList);
        resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
   //getCompletedEventSpeakers
       public String getCompletedEventSpeakers(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ setJobId(getJobId());
                     List mainList = null;
                    mainList = ServiceLocator.getMarketingService().getEventSpeakers(this);
                    setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("eventSpeakersList", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   } 
       //getContactUs
       
      public String getContactUs(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     if(httpServletRequest.getSession(false).getAttribute("websiteInfoList")!=null){
        httpServletRequest.getSession(false).removeAttribute("websiteInfoList");
    }
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }   
      
      public String searchContactUsData(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                    setTableName(getTableName());
                     List mainList = null;
                   mainList = ServiceLocator.getMarketingService().searchWebsiteInfo(this);
                   // setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("websiteInfoList", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   } //getEmpVerification
         public String getEmpVerification(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     if(httpServletRequest.getSession(false).getAttribute("websiteInfoList")!=null){
        httpServletRequest.getSession(false).removeAttribute("websiteInfoList");
    }
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }   
          public String searchEmpVerification(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                    setTableName(getTableName());
                     List mainList = null;
                   mainList = ServiceLocator.getMarketingService().searchWebsiteInfo(this);
                   // setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("websiteInfoList", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   } public String getResourceDepot(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     if(httpServletRequest.getSession(false).getAttribute("websiteInfoList")!=null){
        httpServletRequest.getSession(false).removeAttribute("websiteInfoList");
    }
                     //setTrackNamesList(ServiceLocator.getMarketingService().getTrackNamesList());
                     setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }   
          public String searchResourceDepot(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                    setTableName(getTableName());
                     List mainList = null;
                    //  setTrackNamesList(ServiceLocator.getMarketingService().getTrackNamesList());
                     setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                   mainList = ServiceLocator.getMarketingService().searchWebsiteInfo(this);
                   // setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("websiteInfoList", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   } public String getSuggestionBox(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("SUGGESTION_BOX_ACCESS",userRoleId)){
                try{ 
                     if(httpServletRequest.getSession(false).getAttribute("websiteInfoList")!=null){
        httpServletRequest.getSession(false).removeAttribute("websiteInfoList");
    }
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }   
          public String searchSuggestionBox(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("SUGGESTION_BOX_ACCESS",userRoleId)){
                try{ //setJobId(getJobId());
                    setTableName(getTableName());
                     List mainList = null;
                   mainList = ServiceLocator.getMarketingService().searchWebsiteInfo(this);
                   // setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("websiteInfoList", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   } public String getQuarterlyMeet(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     if(httpServletRequest.getSession(false).getAttribute("websiteInfoList")!=null){
        httpServletRequest.getSession(false).removeAttribute("websiteInfoList");
    }
                     setQmeetYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                     setQmeetMap(ServiceLocator.getMarketingService().getQmeetMap(this));
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }   
          public String searchQuarterlyMeet(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                    setTableName(getTableName());
                     List mainList = null;
                     setQmeetMap(ServiceLocator.getMarketingService().getQmeetMap(this));
                     setQmeetYear(getQmeetYear());
                   mainList = ServiceLocator.getMarketingService().searchWebsiteInfo(this);
                   // setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("websiteInfoList", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   } 
          
          //getResources
          
          
      public String getResources(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     if(httpServletRequest.getSession(false).getAttribute("websiteInfoList")!=null){
        httpServletRequest.getSession(false).removeAttribute("websiteInfoList");
    }
                     setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());
                     //setQmeetYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                   //  setQmeetMap(ServiceLocator.getMarketingService().getQmeetMap(this));
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }        
    //webinarSeries      
      
       public String webinarSeries(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     if(httpServletRequest.getSession(false).getAttribute("websiteInfoList")!=null){
        httpServletRequest.getSession(false).removeAttribute("websiteInfoList");
    } if(httpServletRequest.getSession(false).getAttribute("webinarSeriesList")!=null){
        httpServletRequest.getSession(false).removeAttribute("webinarSeriesList");
    }
    setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                 //    setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());
                     //setQmeetYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                   //  setQmeetMap(ServiceLocator.getMarketingService().getQmeetMap(this));
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }     
       public String searchWebinarSeries(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                  //  setTableName(getTableName());
                     if(httpServletRequest.getSession(false).getAttribute("webinarSeriesList")!=null){
        httpServletRequest.getSession(false).removeAttribute("webinarSeriesList");
    }
                     setTrackNamesMap(ServiceLocator.getMarketingService().getLkpTrackNamesMap());
                     List mainList = null;
                   mainList = ServiceLocator.getMarketingService().getWebinarSeriesList(this);
                   // setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("webinarSeriesList", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
      
       
     //getSeriesEvents  
       public String getEventsBySeries(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                  //  setTableName(getTableName());
                     if(httpServletRequest.getSession(false).getAttribute("webinarEventsListBySeries")!=null){
        httpServletRequest.getSession(false).removeAttribute("webinarEventsListBySeries");
    }
                     
                     
                     setUnassociatedEventMap(ServiceLocator.getMarketingService().getUnAssociatedEvents(getSeriesId()));
                     List mainList = null;
                   mainList = ServiceLocator.getMarketingService().getEventsListBySeries(this);
                   // setEventId(getEventId());
                   setSeriesId(getSeriesId());

        httpServletRequest.getSession(false).setAttribute("webinarEventsListBySeries", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
       public String addEventToSeries(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                  //  setTableName(getTableName());
                     if(httpServletRequest.getSession(false).getAttribute("webinarEventsListBySeries")!=null){
        httpServletRequest.getSession(false).removeAttribute("webinarEventsListBySeries");
    }
                    // System.out.println("SeriesId-->"+getSeriesId());
                    // System.out.println("eventId-->"+getAssociatedEventId());
                   
                     
                     List mainList = null;
                   
               String resultMessage = ServiceLocator.getMarketingService().addEventToSeries(getSeriesId(),getAssociatedEventId());
                  //  httpServletRequest.setAttribute("resultMessage", resultMessage);
                      setResultMessage(resultMessage);
                      setSeriesId(getSeriesId());
                   mainList = ServiceLocator.getMarketingService().getEventsListBySeries(this);
                   setUnassociatedEventMap(ServiceLocator.getMarketingService().getUnAssociatedEvents(getSeriesId()));
                   // setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("webinarEventsListBySeries", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
       
        public String removeEventFromSeries(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                  //  setTableName(getTableName());
                     if(httpServletRequest.getSession(false).getAttribute("webinarEventsListBySeries")!=null){
        httpServletRequest.getSession(false).removeAttribute("webinarEventsListBySeries");
    }
                  
                     
                     List mainList = null;
                   
                   String resultMessage = ServiceLocator.getMarketingService().addEventToSeries("0",getEventId());
                    httpServletRequest.setAttribute("resultMessage", resultMessage);
                   mainList = ServiceLocator.getMarketingService().getEventsListBySeries(this);
                   setUnassociatedEventMap(ServiceLocator.getMarketingService().getUnAssociatedEvents(getSeriesId()));
                   // setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("webinarEventsListBySeries", mainList);
        setResultMessage(resultMessage);
                      setSeriesId(getSeriesId());
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
   
/*
      * Speaker Team update
      * Date : 08/07/2015
      * Author : Santosh Kola
      */
       public String eventSpeakerSubmit() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String ptmAfterChange = "";
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", userRoleId)) {
                try {
                    //getting datasourcedataprovider
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    //Returns Employee login id
                   
                    setObjectType(getObjectType());
                    //getting primary team memberid
                    String primaryTeamMemberId = ServiceLocator.getMarketingService().getPrimarySpeaker(Integer.parseInt(getEventId()),getObjectType());
                    //getting account team map before cahnging
                    Map mapBeforeTeamChange = ServiceLocator.getMarketingService().getSpeakersByEventId(Integer.parseInt(getEventId()), getPrimarySpeakerId(),getObjectType());

                    Map mapAfterTeamChange = new HashMap();
                    //DateUtility.getInstance().getCurrentDate();
                    //new map to strore team after change
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {
                        String assignedSpeakers[] = (String[]) speakersParameters.get("assignedSpeakers");
                        String availableSpeakers[] = (String[]) speakersParameters.get("availableSpeakers");
                        
                    
                       // if (assignedSpeakers != null) {
                           // for (int i = 0; i < assignedSpeakers.length; i++) {
                                //System.out.println("Assigned Team Member : "+assignedTeamMembers[i]);
                                //logger.info(assignedTeamMembers[i]);
                               // int Id = DataSourceDataProvider.getInstance().getEmpIdByLoginId(assignedSpeakers[i]);
                            //    String EmpName = DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Id);
                                // mapAfterTeamChange.put(assignedTeamMembers[i],assignedTeamMembers[i]);
                              //  mapAfterTeamChange.put(assignedTeamMembers[i], EmpName);
                           // }
                       // }//comparing before chnage ,after change mpas
                       // Set<String> valueBeforeChange = new HashSet<String>(mapBeforeTeamChange.keySet());
                       // Set<String> valuesAfterUpdate = new HashSet<String>(mapAfterTeamChange.keySet());
                       // boolean equal = valueBeforeChange.equals(valuesAfterUpdate);
                        //logger.info("START");
                        //logger.info("Date:"+DateUtility.getInstance().getCurrentMySqlDateTime());
                        //logger.info("Date:"+DateUtility.getInstance().getCurrentMySqlDateTime()+ "&&& LoginId:"+loginEmpName+" &&& AccountId:"+getId()+" &&& PrimaryTeamMember:"+primaryTeamMemberId);
                        /*if(equal) {
                        //logger.info("Not changed");
                        } else {
                        
                        logger.info("Account Team Before Change::"+mapBeforeTeamChange);
                        logger.info("Account Updated Team::"+mapAfterTeamChange);
                        }*/

                        //for(int i=0;i<availableTeamMembers.length;i++){
                        //System.out.println("Available Team Member : "+availableTeamMembers[i]);
                        //logger.info(availableTeamMembers);*/
                        //}

                      //  int insertedRows = ServiceLocator.getAccountService().updateAccountTeamMembers(assignedTeamMembers, getId(), primaryTeamMemberId, userRoleId, httpServletRequest.getSession(false));
                        
                          int insertedRows = ServiceLocator.getMarketingService().updateEventSpeakers(assignedSpeakers, Integer.parseInt(getEventId()), primarySpeakerId, httpServletRequest.getSession(false),getObjectType());

                        //if (!httpServletRequest.getSession(false).getAttribute(ApplicationConstants.AFTER_UPDATEPRIMARYTEAM).toString().equals(primaryTeamMemberId)) {

                            // logger.info("Primary TeamMember ChangedTo :"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.AFTER_UPDATEPRIMARYTEAM).toString());
                           // ptmAfterChange = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.AFTER_UPDATEPRIMARYTEAM).toString();
                       // }

                        //logger.info("END");
                        if (insertedRows >= 1) {
                            

                            resultType = SUCCESS;
                            if("EV".equals(getObjectType())){
                            
                            resultMessage = "<font color=\"green\" size=\"1.5\">Speakers has been successfully Modified!</font>";
                            }else {
                                  resultMessage = "<font color=\"green\" size=\"1.5\">Authors has been successfully Modified!</font>";
                            }
                        } else {
                            resultType = INPUT;
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        }
                        
                        
                        
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                    }//close for submitFrom checking
                    if (LOGIN.equals(resultType)) {
                        resultType = SUCCESS;
                    }
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
 
 
public String eventSpeakerDetailsList(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     List mainList = null;
                   
                //   String resultMessage = ServiceLocator.getMarketingService().addEventToSeries("0",getEventId());
                    //httpServletRequest.setAttribute("resultMessage", resultMessage);
                     if(httpServletRequest.getSession(false).getAttribute("peopleDetailsList")!=null){
                       httpServletRequest.getSession(false).removeAttribute("peopleDetailsList");
                       
                     }
                     
                   mainList = ServiceLocator.getMarketingService().eventSpeakerDetailsList(getEventId(),getObjectType());
                   httpServletRequest.getSession(false).setAttribute("peopleDetailsList", mainList);
                 //    setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());
                     //setQmeetYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                   //  setQmeetMap(ServiceLocator.getMarketingService().getQmeetMap(this));
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
   
public String getLkpTrackNames(){
             
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                    Map mainMap = null;
                    mainMap = ServiceLocator.getMarketingService().getLkpTrackNamesMap();
                  
        httpServletRequest.getSession(false).setAttribute("trackNamesMap", mainMap);
                   
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
            
            
        }//Close Session Checking
        return resultType;
   } 
   
public String getPeople(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     
                 //    setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());
                     //setQmeetYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                   //  setQmeetMap(ServiceLocator.getMarketingService().getQmeetMap(this));
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }      
       //searchPeopleList 
           public String searchPeopleList(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     List mainList = null;
                   
                //   String resultMessage = ServiceLocator.getMarketingService().addEventToSeries("0",getEventId());
                    //httpServletRequest.setAttribute("resultMessage", resultMessage);
                   mainList = ServiceLocator.getMarketingService().searchPeopleList(this);
                   httpServletRequest.getSession(false).setAttribute("peopleList", mainList);
                 //    setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());
                     //setQmeetYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                   //  setQmeetMap(ServiceLocator.getMarketingService().getQmeetMap(this));
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
   
public String leadGen() throws ServiceLocatorException {
      // System.out.println("----leadGen---");
        String queryString = "";
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                
                setStartDate(DateUtility.getInstance().getLastYearFirstMonth());
                setEndDate(DateUtility.getInstance().getCurrentMySqlDate());
               setClientCurrencyList(DataSourceDataProvider.getInstance().getCurrencyList());
                setBdmMap(DataSourceDataProvider.getInstance().getAllBDMs());
                setUpcommingConferencesList(ServiceLocator.getMarketingService().getConferencesList("Active"));
                 setTotalConferencesList(ServiceLocator.getMarketingService().getConferencesList("none"));
                queryString = "SELECT * from vwInvestments WHERE 1=1";

                if (getStartDate() != null && !"".equals(getStartDate()) && getEndDate() != null && !"".equals(getEndDate())) {
                   // System.out.println("In ifff");
                    queryString = queryString + (" AND (DATE(CreatedDate) >= '" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "') AND (DATE(CreatedDate) <= '" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "') ");
                   
                }
                queryString = queryString + " AND STATUS='Active' ORDER BY createdDate DESC";
                setIsInvestmentRecordsExist(ServiceLocator.getMarketingService().generateExcelInvestment(queryString));
             //   System.out.println("Left Menu-->" + queryString);
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.INVESTMENT_DETAILS)!=null)
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.INVESTMENT_DETAILS);
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.INVESTMENT_DETAILS, queryString);
                resultType = SUCCESS;
            } //Close Session Checking
            catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }//Close Session Checking
        return resultType;

    }
	
    public String deleteInvestment() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                boolean isDeleted = ServiceLocator.getMarketingService().deleteInvestment(getId());
                
                if (!isDeleted) {
                    setResultMessage("<font color=green size=2px> deleted successfully.</font>");
                } else {
                    setResultMessage("<font color=red size=2px>Please try again.</font>");
                }
                 setClientCurrencyList(DataSourceDataProvider.getInstance().getCurrencyList());
                  
                resultType = SUCCESS;
            } catch (Exception ex) { //List errorMsgList = ExceptionToListUtility.errorMessages(ex); 
                ex.printStackTrace();
                setResultMessage("<font color=red size=2px>" + ex.getMessage() + "</font>");
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        httpServletRequest.setAttribute("resultMessage", getResultMessage());
        return resultType;
    }

    
public String investmentSearch() {
        resultType = LOGIN;
        String queryString = "";

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                setClientCurrencyList(DataSourceDataProvider.getInstance().getCurrencyList());
                queryString = "SELECT  * from vwInvestments WHERE 1=1 ";              
                if (!"".equalsIgnoreCase(getCountryInvestment().toString()) && !"-1".equalsIgnoreCase(getCountryInvestment().toString())) {
                    queryString = queryString + " AND Country LIKE '%" + getCountryInvestment().trim() + "%'";
                }

                if (getStartDate() != null && !"".equals(getStartDate()) && getEndDate() != null && !"".equals(getEndDate())) {                   
                    queryString = queryString + (" AND DATE(CreatedDate) >= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "') AND DATE(CreatedDate) <= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "') ");
                    
                }
                    else 
                    {
                        if(getStartDate() != null && !"".equals(getStartDate()))
                        {
                             queryString = queryString+(" AND DATE(CreatedDate) >= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "') ");
                        } if(getEndDate() != null && !"".equals(getEndDate()))
                        {
                             queryString = queryString+(" AND DATE(CreatedDate) <= DATE('" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "') ");
                        }
                    }

                if (getInvestmentName1() != null && !"".equals(getInvestmentName1())) {
                    queryString = queryString + " AND Inv_Name LIKE '%" + getInvestmentName1().trim() + "%'";
                }


                    if(!"".equals(getExpensesAmountFrom()) && getExpensesAmountFrom()!=0.0 && getExpensesAmountTo()==0.0)
                    {                         
                         queryString = queryString + " AND TotalExpenses>= '"+ getExpensesAmountFrom() + "'";
                    }
                    
                    if(!"".equals(getExpensesAmountTo()) && getExpensesAmountTo()!=0.0 && getExpensesAmountFrom()==0.0)
                    {
                         queryString = queryString + " AND TotalExpenses<='"+ getExpensesAmountTo() + "'";
                    }
                    if(!"".equals(getExpensesAmountFrom()) && getExpensesAmountFrom()!=0.0 && !"".equals(getExpensesAmountTo()) && getExpensesAmountTo()!=0.0)
                    {
                         queryString = queryString + " AND TotalExpenses>= '"+ getExpensesAmountFrom() + "' AND TotalExpenses<='"+ getExpensesAmountTo() + "'";
                    }
              
                       if (getBdmId() != null && !"".equals(getBdmId())) {
                           String bdmList = DataSourceDataProvider.getInstance().getInvestmentIdsByBdmId(getBdmId());
                           
                    queryString = queryString + " AND Inv_Id IN ("+bdmList+") ";
                }
                queryString = queryString + " AND STATUS='Active'  ORDER BY CreatedDate DESC"; 
                
              //  System.out.println("Query-->"+queryString);
                setIsInvestmentRecordsExist(ServiceLocator.getMarketingService().generateExcelInvestment(queryString));
                  setBdmMap(DataSourceDataProvider.getInstance().getAllBDMs());
                  setUpcommingConferencesList(ServiceLocator.getMarketingService().getConferencesList("Active"));
                  setTotalConferencesList(ServiceLocator.getMarketingService().getConferencesList("none"));
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.INVESTMENT_DETAILS)!=null)
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.INVESTMENT_DETAILS);
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.INVESTMENT_DETAILS, queryString);
                resultType = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
    }

    public String generateInvestmentXls() throws ServiceLocatorException {
        
        resultType = INPUT;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String responseString = "";
            try {
                String fileLocation = "";
                String queryString="";
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.INVESTMENT_DETAILS)!=null)
              queryString=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.INVESTMENT_DETAILS).toString();
                fileLocation = ServiceLocator.getMarketingService().generateInvestmentXls(queryString);
                httpServletResponse.setContentType("application/force-download");

                if (!"".equals(fileLocation)) {
                    File file = new File(fileLocation);

                    String fileName = "";

                    fileName = file.getName();
                    if (file.exists()) {
                        inputStream = new FileInputStream(file);
                        outputStream = httpServletResponse.getOutputStream();
                        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                        int noOfBytesRead = 0;
                        byte[] byteArray = null;
                        while (true) {
                            byteArray = new byte[1024];
                            noOfBytesRead = inputStream.read(byteArray);
                            if (noOfBytesRead == -1) {
                                break;
                            }
                            outputStream.write(byteArray, 0, noOfBytesRead);
                        }

                    } else {
                        throw new FileNotFoundException("File not found");
                    }
                } else {

                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! No records for this Employee!</font>";
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                }


            } catch (FileNotFoundException ex) {
                try {
                    httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
                } catch (IOException ex1) {
                    // Logger.getLogger(MarketingAction.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                   
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }




        }//Close Session Checking
        resultType = SUCCESS;
        return resultType;
    }
	
	
          public String downloadInvestmentAttachment() throws ServiceLocatorException {
        
        resultType = INPUT;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String responseString = "";
            try {
                String fileLocation = "";
                fileLocation = ServiceLocator.getMarketingService().getInvestmentAttachments(getInvestmentId());
                httpServletResponse.setContentType("application/force-download");

                if (!"".equals(fileLocation)) {
                    File file = new File(fileLocation);

                    String fileName = "";

                    fileName = file.getName();
                    if (file.exists()) {
                        inputStream = new FileInputStream(file);
                        outputStream = httpServletResponse.getOutputStream();
                        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                        int noOfBytesRead = 0;
                        byte[] byteArray = null;
                        while (true) {
                            byteArray = new byte[1024];
                            noOfBytesRead = inputStream.read(byteArray);
                            if (noOfBytesRead == -1) {
                                break;
                            }
                            outputStream.write(byteArray, 0, noOfBytesRead);
                        }

                    } else {
                        throw new FileNotFoundException("File not found");
                    }
                } else {

                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! No records for this Employee!</font>";
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                }


            } catch (FileNotFoundException ex) {
                try {
                    httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
                } catch (IOException ex1) {
                    // Logger.getLogger(MarketingAction.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                   
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }




        }//Close Session Checking
        resultType = SUCCESS;
        return resultType;
    }   
          
          
           public String campaignSearch() throws ServiceLocatorException {      
        String queryString = "";
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
              userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
             // System.out.println("userRoleId-->"+userRoleId);
            try {                
               // setStartDate(DateUtility.getInstance().getLastYearFirstMonth());
              //  setEndDate(DateUtility.getInstance().getCurrentMySqlDate());      
                setCampaignStartDate(DateUtility.getInstance().getLastYearFirstMonth());
                setCampaignEndDate(DateUtility.getInstance().getCurrentMySqlDate()); 
                queryString = "SELECT * from tblCrmCampaign WHERE 1=1";
                if (getCampaignStartDate() != null && !"".equals(getCampaignStartDate())) {                   
                    queryString = queryString + " AND (DATE(CampaignStartDate) = '" + DateUtility.getInstance().getMysqlDate(getCampaignStartDate()) + "')";                          
                    
                }
                if(getCampaignEndDate() != null && !"".equals(getCampaignEndDate())){
                 queryString = queryString + "  AND (DATE(CampaignEndDate) = '" + DateUtility.getInstance().getMysqlDate(getCampaignEndDate()) + "')";                
                }
               // System.out.println("getCampaignStatus"+getCampaignStatus());
                if(!"-1".equals(getCampaignStatus()) && getCampaignStatus() != null){
                 queryString = queryString + " AND STATUS='"+getCampaignStatus()+"' ";
                }else{                   
                    if(userRoleId==4){
                      queryString = queryString + " AND STATUS!='InActive'";                    
                    }                                           
                }
                
                     if(getCampaignTitle() != null && !"".equals(getCampaignTitle())){
                 queryString = queryString + " AND CampaignName like '%"+ getCampaignTitle().trim()+"%'";
                }
                queryString = queryString + "  ORDER BY CreatedDate DESC";           
                httpServletRequest.setAttribute(ApplicationConstants.INVESTMENT_DETAILS, queryString);
                resultType = SUCCESS;
            } //Close Session Checking
            catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }//Close Session Checking
        return resultType;

    }
        
         
        
        public String addOrUpdateCampaign(){          
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setCreatedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            resultType = "accessFailed";
          
                try{                                  
                  int inserted=0;    
                  if(getTempVar()==1){
              inserted = ServiceLocator.getMarketingService().addCampaign(this);
               setResultMessage("<font color=green size=3px> Added successfully.</font>");
                  }else if(getTempVar()==2){
            inserted = ServiceLocator.getMarketingService().updateCampaign(this);
                   setResultMessage("<font color=green size=3px> Updated successfully.</font>");
                  }
                resultType = SUCCESS;
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            
        }//Close Session Checking
        return resultType;
       
   }
        
        
        
           public String redirectCampaign(){          
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setCreatedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            resultType = "accessFailed";
          
                try{                               
                                             
                resultType = SUCCESS;
                
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            
        }//Close Session Checking
        return resultType;
   } 
        
        public String selectCampaignDetails(){         
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setCreatedById(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            resultType = "accessFailed";          
                try{                                  
                    setNavId(1);                 
              ServiceLocator.getMarketingService().getCampaignDetails(this);               
              
                resultType = SUCCESS;
                
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            
        }//Close Session Checking
        return resultType;
   }
   
    public String getCampaignContactsExcel() throws ServiceLocatorException {
        
        resultType = INPUT;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String responseString = "";
            try {
                String fileLocation = "";
                //fileLocation = ServiceLocator.getMarketingService().getCampaignContactsExcel(httpServletRequest,getCampaignId());
                fileLocation = ServiceLocator.getMarketingService().getCampaignContactsExcel(httpServletRequest,this);
                httpServletResponse.setContentType("application/force-download");

                if (!"".equals(fileLocation)) {
                    File file = new File(fileLocation);

                    String fileName = "";

                    fileName = file.getName();
                    if (file.exists()) {
                        inputStream = new FileInputStream(file);
                        outputStream = httpServletResponse.getOutputStream();
                        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                        int noOfBytesRead = 0;
                        byte[] byteArray = null;
                        while (true) {
                            byteArray = new byte[1024];
                            noOfBytesRead = inputStream.read(byteArray);
                            if (noOfBytesRead == -1) {
                                break;
                            }
                            outputStream.write(byteArray, 0, noOfBytesRead);
                        }

                    } else {
                        throw new FileNotFoundException("File not found");
                    }
                } else {

                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! No records for this Employee!</font>";
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                }


            } catch (FileNotFoundException ex) {
                try {
                    httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
                } catch (IOException ex1) {
                    // Logger.getLogger(MarketingAction.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                   
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }




        }//Close Session Checking
        resultType = SUCCESS;
        return resultType;
    }
	
public String doAddLeads() throws ServiceLocatorException {
        
           resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
        //    if(AuthorizationManager.getInstance().isAuthorizedUser("GET_ACTIVITY_MARKETING",userRoleId)){
                try{
                   
                     setAllEmployeesList(DataSourceDataProvider.getInstance().getAllEmployees());
                    
                    if(getAccountId()>0){
                    setContactsList(DataSourceDataProvider.getInstance().getContactsNamesAgainstAccount(getAccountId()));
                     setAccountName(HibernateDataProvider.getInstance().getAccountName(getAccountId()));
                     setInvestmentList(DataSourceDataProvider.getInstance().getInvestmentsNamesById());
                    }else {
                        setInvestmentList(DataSourceDataProvider.getInstance().getInvestmentsNamesFromDashboard());
                        
                        setAccountId(0);
                    }
                    setCurrentAction("addLeads");
                    setDashboardFlag(getDashboardFlag());
                    //setAccountsMap(accountsMap);
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            //}
        }//Close Session Checking
        return resultType;
    }
         
        public String addLeads(){
          
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
          //  if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                
                setReportsTo( httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_REPORTSTO_ID).toString());
                
                
                setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                setExpiryDate(DateUtility.getInstance().getNextYearTimeStamp());
               
                String resultMessage = ServiceLocator.getMarketingService().doAddLeadsDetails(this);
                    
                  //  System.out.println("resultMessage"+resultMessage);
                setResultMessage(resultMessage);
                      //setSeriesId(getSeriesId());
                  setAccountId(getAccountId());
                setDashboardFlag(getDashboardFlag());
                   // setEventId(getEventId());
                
                //System.out.println("getDashboardFlag-->"+getDashboardFlag());
                  if(getDashboardFlag()>0)
                      resultType = "dashboardsuccess";
                      else
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           // }
        }//Close Session Checking
        return resultType;
   }
        
          public String doEditLeads(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
          //  if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                setExpiryDate(DateUtility.getInstance().getNextYearTimeStamp());
                ServiceLocator.getMarketingService().doEditLeadsDetails(this);
                      setResultMessage(resultMessage);
                      //setSeriesId(getSeriesId());
                  setAccountId(getAccountId());
                  
                setInvestmentList(DataSourceDataProvider.getInstance().getInvestmentsNamesById());
                    setContactsList(DataSourceDataProvider.getInstance().getContactsNamesAgainstAccount(getAccountId()));
                    setAccountName(HibernateDataProvider.getInstance().getAccountName(getAccountId()));
                    setAllEmployeesList(DataSourceDataProvider.getInstance().getAllEmployees());
                   // setEventId(getEventId());
                    setCurrentAction("doUpdateLeads");
                  setDashboardFlag(getDashboardFlag());
                 
                      resultType = SUCCESS;
                 
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           // }
        }//Close Session Checking
        return resultType;
   }  
          public String doUpdateLeads(){
              //System.out.println("doUpdateLeads");
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
          //  if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                //setExpiryDate(DateUtility.getInstance().getNextYearTimeStamp());
                String resultMessage = ServiceLocator.getMarketingService().doUpdateLeads(this);
                      setResultMessage(resultMessage);
                      //setSeriesId(getSeriesId());
                  setAccountId(getAccountId());
                setDashboardFlag(getDashboardFlag());
                   // setEventId(getEventId());
                  
                 if(getDashboardFlag()>0)
                      resultType = "dashboardsuccess";
                      else
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
           // }
        }//Close Session Checking
        return resultType;
   }
   
          /*
           * Emeet methods
           * Date : 01/29/2016
           * Author : Phani Kanuri
           */
public String executiveMeetingSearch()
    {
          resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
           
            if(AuthorizationManager.getInstance().isAuthorizedUser("EMEET_ACCESS",userRoleId)){
                try{ 
         
                    if (httpServletRequest.getSession(false).getAttribute("ExecutiveMeetingslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("ExecutiveMeetingslist");
                    }


                              
                    
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
       
    }
                  
  public String emeetSearch()
    {
          resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EMEET_ACCESS",userRoleId)){
                try{ 
                    List mainList =null;
                  
          // setLocationList(ServiceLocator.getMarketingService().getLocationList());
//mainList = ServiceLocator.getMarketingService().doJobSearch(this);
mainList = ServiceLocator.getMarketingService().doEmeetSearch(this);
    
        httpServletRequest.getSession(false).setAttribute("ExecutiveMeetingslist", mainList);

                              
                    
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
  
   public String emeetAttendiesList()
    {
          resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
           
            if(AuthorizationManager.getInstance().isAuthorizedUser("EMEET_ACCESS",userRoleId)){
                try{ 
                     if (httpServletRequest.getSession(false).getAttribute("ExecutiveMeetingsAttendeeslist") != null) {
                        httpServletRequest.getSession(false).removeAttribute("ExecutiveMeetingsAttendeeslist");
                    }
                    setActiveEmployeeMap(DataSourceDataProvider.getInstance().getActiveEmployeesList());
                   // setAccessTypeList(dataSourceDataProvider.getInstance().getExecuteMeetAccessTyoes());
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
       
    }
   public String emeetAttendeesSearch()
    {
          resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EMEET_ACCESS",userRoleId)){
                try{ 
                    List mainList =null;
                    setActiveEmployeeMap(DataSourceDataProvider.getInstance().getActiveEmployeesList());
                 //   setAccessTypeList(DataSourceDataProvider.getInstance().getExecuteMeetAccessTyoes());
                  
//mainList = ServiceLocator.getMarketingService().doJobSearch(this);
mainList = ServiceLocator.getMarketingService().doEmeetAttendeesSearch(this);
    
        httpServletRequest.getSession(false).setAttribute("ExecutiveMeetingsAttendeeslist", mainList);

                              
                    
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
   
   
    
    public String doPublishExeMeet() {
        resultType = LOGIN;
           
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("EMEET_ACCESS", userRoleId)) {
                try {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                   
                    setResultMessage(ServiceLocator.getMarketingService().doPublishExeMeet(this));

                    // Map eventSpeakerMap = new TreeMap();
                    //  setSpeakersMapExceptEventSpeakerMap(ServiceLocator.getMarketingService().getAllPeaopleExceptEventSpeakers(eventSpeakerMap));
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
    
    
    
    public String doActiveExeMeet() {
        resultType = LOGIN;
           
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("EMEET_ACCESS", userRoleId)) {
                try {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                   
                    setResultMessage(ServiceLocator.getMarketingService().doActiveExeMeet(this));

                    // Map eventSpeakerMap = new TreeMap();
                    //  setSpeakersMapExceptEventSpeakerMap(ServiceLocator.getMarketingService().getAllPeaopleExceptEventSpeakers(eventSpeakerMap));
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }
 public String getIOTDetails(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     if(httpServletRequest.getSession(false).getAttribute("websiteInfoList")!=null){
        httpServletRequest.getSession(false).removeAttribute("websiteInfoList");
    }
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
     public String searchIOTDetails(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                   setTableName(getTableName());
                     List mainList = null;
                     mainList = ServiceLocator.getMarketingService().searchWebsiteInfo(this);
//                   // setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("websiteInfoList", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
     
   
/*
      * Signature Data
      * Date : 04/22/2016
      * Author : Asha 
      */
 
     public String getSignatureDetails(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     if(httpServletRequest.getSession(false).getAttribute("websiteInfoList")!=null){
        httpServletRequest.getSession(false).removeAttribute("websiteInfoList");
    }
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
     
     public String searchSignatureDetails(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ //setJobId(getJobId());
                   setTableName(getTableName());
                     List mainList = null;
                     mainList = ServiceLocator.getMarketingService().searchWebsiteInfo(this);
//                   // setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("websiteInfoList", mainList);
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }

       
     
 /*Add BDM to Investment
      * Date : 04/12/2016
      * Author : Santosh Kola
      */
     
public String getInvestmentBdms(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                    /* List mainList = null;
                    mainList = ServiceLocator.getMarketingService().getEventSpeakers(this);
                    setEventId(getEventId());
        httpServletRequest.getSession(false).setAttribute("eventSpeakersList", mainList);
                     * */
                   setInvestmentId(getInvestmentId());
                   // setPrimarySpeakerId(ServiceLocator.getMarketingService().getPrimarySpeaker(Integer.parseInt(getEventId()),"EV"));
                 //    setPrimarySpeakerId(ServiceLocator.getMarketingService().getPrimarySpeaker(Integer.parseInt(getEventId()),getObjectType()));
                   // System.out.println("setPrimarySpeakerId size-->"+getPrimarySpeakerId());
                      //  setAccountTeamMap(accountService.getAccountTeamByAccountId(getId(), getPrimaryTeamMember()));
                     // setEventSpeakerMap(ServiceLocator.getMarketingService().getSpeakersByEventId(Integer.parseInt(getEventId()), getPrimarySpeakerId(),"EV"));
                   
                   setInvestmentTitle(DataSourceDataProvider.getInstance().getInvestmentsNameById(getInvestmentId()));
                   
                   
                   
                     setInvestmentBDMsMap(ServiceLocator.getMarketingService().getBDMsByInvestmentId(getInvestmentId()));
                   
                      setBdmsMapExceptInvestmentBDMMap(ServiceLocator.getMarketingService().getAllBDMsExceptInvestmentBDMs(getInvestmentBDMsMap()));
                 

                        //httpServletRequest.setAttribute("primarySpeakerId", getPrimarySpeakerId());
                     setResultMessage(getResultMessage());
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   } 
   public String investmentBDMSubmit() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String ptmAfterChange = "";
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY", userRoleId)) {
                try {
                    //getting datasourcedataprovider
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    //Returns Employee login id
                   
                    //setObjectType(getObjectType());
                    //getting primary team memberid
                   // String primaryTeamMemberId = ServiceLocator.getMarketingService().getPrimarySpeaker(Integer.parseInt(getEventId()),getObjectType());
                    //getting account team map before cahnging
                    //Map mapBeforeTeamChange = ServiceLocator.getMarketingService().getSpeakersByEventId(Integer.parseInt(getEventId()), getPrimarySpeakerId(),getObjectType());

                   // Map mapAfterTeamChange = new HashMap();
                    //DateUtility.getInstance().getCurrentDate();
                    //new map to strore team after change
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {
                        String assignedBdms[] = (String[]) bdmParameters.get("assignedBdms");
                        String availableBdms[] = (String[]) bdmParameters.get("availableBdms");
                        
                    
                       // if (assignedSpeakers != null) {
                           // for (int i = 0; i < assignedSpeakers.length; i++) {
                                //System.out.println("Assigned Team Member : "+assignedTeamMembers[i]);
                                //logger.info(assignedTeamMembers[i]);
                               // int Id = DataSourceDataProvider.getInstance().getEmpIdByLoginId(assignedSpeakers[i]);
                            //    String EmpName = DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Id);
                                // mapAfterTeamChange.put(assignedTeamMembers[i],assignedTeamMembers[i]);
                              //  mapAfterTeamChange.put(assignedTeamMembers[i], EmpName);
                           // }
                       // }//comparing before chnage ,after change mpas
                       // Set<String> valueBeforeChange = new HashSet<String>(mapBeforeTeamChange.keySet());
                       // Set<String> valuesAfterUpdate = new HashSet<String>(mapAfterTeamChange.keySet());
                       // boolean equal = valueBeforeChange.equals(valuesAfterUpdate);
                        //logger.info("START");
                        //logger.info("Date:"+DateUtility.getInstance().getCurrentMySqlDateTime());
                        //logger.info("Date:"+DateUtility.getInstance().getCurrentMySqlDateTime()+ "&&& LoginId:"+loginEmpName+" &&& AccountId:"+getId()+" &&& PrimaryTeamMember:"+primaryTeamMemberId);
                        /*if(equal) {
                        //logger.info("Not changed");
                        } else {
                        
                        logger.info("Account Team Before Change::"+mapBeforeTeamChange);
                        logger.info("Account Updated Team::"+mapAfterTeamChange);
                        }*/

                        //for(int i=0;i<availableTeamMembers.length;i++){
                        //System.out.println("Available Team Member : "+availableTeamMembers[i]);
                        //logger.info(availableTeamMembers);*/
                        //}

                      //  int insertedRows = ServiceLocator.getAccountService().updateAccountTeamMembers(assignedTeamMembers, getId(), primaryTeamMemberId, userRoleId, httpServletRequest.getSession(false));
                        
                          int insertedRows = ServiceLocator.getMarketingService().updateInvestmentBdms(assignedBdms, getInvestmentId());

                        //if (!httpServletRequest.getSession(false).getAttribute(ApplicationConstants.AFTER_UPDATEPRIMARYTEAM).toString().equals(primaryTeamMemberId)) {

                            // logger.info("Primary TeamMember ChangedTo :"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.AFTER_UPDATEPRIMARYTEAM).toString());
                           // ptmAfterChange = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.AFTER_UPDATEPRIMARYTEAM).toString();
                       // }

                        //logger.info("END");
                        if (insertedRows >= 1) {
                            

                            resultType = SUCCESS;
                          
                                  resultMessage = "<font color=\"green\" size=\"1.5\">BDM's has been successfully Modified!</font>";
                            
                        } else {
                            resultType = INPUT;
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                        }
                        
                        
                        
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                    }//close for submitFrom checking
                    if (LOGIN.equals(resultType)) {
                        resultType = SUCCESS;
                    }
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
     
public String investmentBDMrDetailsList(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     List mainList = null;
                   
                //   String resultMessage = ServiceLocator.getMarketingService().addEventToSeries("0",getEventId());
                    //httpServletRequest.setAttribute("resultMessage", resultMessage);
                     if(httpServletRequest.getSession(false).getAttribute("bdmsDetailsList")!=null){
                       httpServletRequest.getSession(false).removeAttribute("bdmsDetailsList");
                       
                     }
                     
                   mainList = ServiceLocator.getMarketingService().investmentBdmDetailsList(getInvestmentId());
                   httpServletRequest.getSession(false).setAttribute("bdmsDetailsList", mainList);
                 //    setDateOfPublish(DateUtility.getInstance().getCurrentMySqlDate());
                     //setQmeetYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                   //  setQmeetMap(ServiceLocator.getMarketingService().getQmeetMap(this));
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }

//leadDashboard
public String leadDashboard(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                    setInvestmentList(DataSourceDataProvider.getInstance().getActiveInvestmentsMap());
                  //  setAnalystMap(DataSourceDataProvider.getInstance().getMarketingAnalystMap());
                    //setLinkedLeadAccountsMap(ServiceLocator.getMarketingService().getLeadLinkedAccountsMap());
                    setLeadStatesList(DataSourceDataProvider.getInstance().getStatesList());
                    
                    // Anaylist map setting based on Hierarchy
                    String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();

			 Map rolesMap=(Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
                     if(rolesMap.containsValue("Admin"))
                     {
                                 setAnalystMap(DataSourceDataProvider.getInstance().getMarketingAnalystMap());
                     }
                     else
                     {
                         Map teamMap=(Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
                         Map analistMap=DataSourceDataProvider.getInstance().getMarketingAnalystMap();
                         Map tempAnalistsMap=new HashMap();
                          Iterator iterator = teamMap.entrySet().iterator();
                          while(iterator.hasNext())
                          {
                              Map.Entry<String,String> entry = (Map.Entry<String,String>) iterator.next();
                             if( analistMap.containsKey(entry.getKey()))
                             {
                                tempAnalistsMap.put(entry.getKey(), entry.getValue()) ;
                             }
                              
                          }
                          tempAnalistsMap.put(userId,DataSourceDataProvider.getInstance().getFullNameBYLoginId(userId));
                          setAnalystMap(tempAnalistsMap);
                              //  setAnalystMap(DataSourceDataProvider.getInstance().getMyTeamMembers(userId, "Marketing"));
                     }
                    
                    
                    
                    
                    
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
 public String getOppurtunityList() throws ServiceLocatorException {
      // System.out.println("----leadGen---");
        String queryString = "";
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
               // hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                   
                     setSalesMap(dataSourceDataProvider.getAllSalesTeam());
                     setInvestmentId(getInvestmentId());
                   
             //   System.out.println("Left Menu-->" + queryString);
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.INVESTMENT_DETAILS)!=null)
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.INVESTMENT_DETAILS);
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.INVESTMENT_DETAILS, queryString);
                resultType = SUCCESS;
            } //Close Session Checking
            catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }//Close Session Checking
        return resultType;

    }

     
     public String doGetConstnatList(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("WEB_ADMIN_ACTIVITY",userRoleId)){
                try{ 
                     
                  String Api_Key=Properties.getProperty("API_KEY");
                  String Access_Tocken=Properties.getProperty("ACCESS_TOCKEN");
                    ContactListService cls=new ContactListService(Api_Key, Access_Tocken);
                      ArrayList conList=new ArrayList();
                      Map constantMap=new HashMap();
                    conList= (ArrayList) cls.getLists(null);
                      for(int j=0;j<conList.size();j++){
                  ContactList cli=(ContactList) conList.get(j);
                 constantMap.put(cli.getId(), cli.getName());
                      }
             constantMap.put("REMOVED", "REMOVED LIST");
                  setConstantContactList(constantMap);
                 
         
               setLocationsMap(DataSourceDataProvider.getInstance().getEmployeeLocationsLst());
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
     
     
     
     public String getInsideSalesStatus(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("INSIDE_SALES_STATUS",userRoleId)){
                try{ 
                   dataSourceDataProvider=DataSourceDataProvider.getInstance();
                  setInsideSalesBDEMap(dataSourceDataProvider.getInsideSalesBDEByLoginId());
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
       public String getAccountDetailsForInsideSalesStatus(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("INSIDE_SALES_STATUS",userRoleId)){
                try{ 
                    
                    if(getBackToFlag().equalsIgnoreCase("Yes")){
                setBdeLoginId((String)httpServletRequest.getSession(false).getAttribute("tempBdeLoginId"));
                setAccountId((Integer)httpServletRequest.getSession(false).getAttribute("tempAccId"));
                setLastActivityFrom((String)httpServletRequest.getSession(false).getAttribute("tempLastActivityFrom"));
                setLastActivityTo((String)httpServletRequest.getSession(false).getAttribute("tempLastActivityTo"));
                setOpportunity((String)httpServletRequest.getSession(false).getAttribute("tempOpportunity"));
                setTouched((String)httpServletRequest.getSession(false).getAttribute("tempTouched"));
                setEmployeeName((String)httpServletRequest.getSession(false).getAttribute("tempEmployeeName"));
                }else{ 
                    
               setBdeLoginId(bdeLoginId);
               setEmployeeName(employeeName);
                    }
                resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
   }
     
     
    /**
     * 
     * @return List
     */
    public List getActivityTypeList() {
        return activityTypeList;
    }
    
    /**
     * 
     * @param activityTypeList 
     */
    public void setActivityTypeList(List activityTypeList) {
        this.activityTypeList = activityTypeList;
    }
    
    /**
     * The setServletRequest(HttpServletRequest httpServletRequest) is for binding request Object this Object mean's ActionObject
     */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    /**
     * 
     * @return 
     */
    public String getAccountName() {
        return accountName;
    }
    
    /**
     * 
     * @param accountName 
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    /**
     * 
     * @return String
     */
    public String getUrlPath() {
        return urlPath;
    }
    
    /**
     * 
     * @param urlPath 
     */
    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
    
    /**
     * 
     * @return String
     */
    public String getHomePhone() {
        return homePhone;
    }
    
    /**
     * 
     * @param homePhone 
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
    
    /**
     * 
     * @return String
     */
    public String getStockSymbol() {
        return stockSymbol;
    }
    
    /**
     * 
     * @param stockSymbol 
     */
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }
    
    /**
     * 
     * @return String
     */
    public String getLastModifyBy() {
        return lastModifyBy;
    }
    
    /**
     * 
     * @param lastModifyBy 
     */
    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }
    
    /**
     * 
     * @return Timestamp
     */
    public Timestamp getLastModifyDate() {
        return lastModifyDate;
    }
    
    /**
     * 
     * @param lastModifyDate 
     */
    public void setLastModifyDate(Timestamp lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
    
    /**
     * 
     * @return String
     */
    public String getAccName() {
        return accName;
    }
    
    /**
     * 
     * @param accName 
     */
    public void setAccName(String accName) {
        this.accName = accName;
    }
    
    /**
     * 
     * @return int
     */
    public int getAccountId() {
        return accountId;
    }
    
    /**
     * 
     * @param accountId 
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    /**
     * 
     * @return int
     */
    public int getContactsId() {
        return contactsId;
    }
    
    /**
     * 
     * @param contactsId 
     */
    public void setContactsId(int contactsId) {
        this.contactsId = contactsId;
    }
    
    /**
     * 
     * @return int
     */
    public int getGetAccountId() {
        return getAccountId;
    }
    
    /**
     * 
     * @param getAccountId 
     */
    public void setGetAccountId(int getAccountId) {
        this.getAccountId = getAccountId;
    }
    
    /**
     * 
     * @return String
     */
    public String getContactName() {
        return contactName;
    }
    
    /**
     * 
     * @param contactName 
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    /**
     * 
     * @return String
     */
    public String getFirstNames() {
        return firstNames;
    }
    
    /**
     * 
     * @param firstNames 
     */
    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }
    
    /**
     * 
     * @return String
     */
    public String getLastNames() {
        return lastNames;
    }
    
    /**
     * 
     * @param lastNames 
     */
    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }
    
    /**
     * 
     * @return String
     */
    public String getEmails() {
        return emails;
    }
    
    /**
     * 
     * @param emails 
     */
    public void setEmails(String emails) {
        this.emails = emails;
    }
    
    /**
     * 
     * @return String
     */
    public String getHomePhoneNo() {
        return homePhoneNo;
    }
    
    /**
     * 
     * @param homePhoneNo 
     */
    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }
    
    /**
     * 
     * @return String
     */
    public String getSource() {
        return source;
    }
    
    /**
     * 
     * @param source 
     */
    public void setSource(String source) {
        this.source = source;
    }
    
    /**
     * 
     * @return MarketingVTO
     */
    public MarketingVTO getCurrentMarketing() {
        return currentMarketing;
    }
    
    /**
     * 
     * @param currentMarketing 
     */
    public void setCurrentMarketing(MarketingVTO currentMarketing) {
        this.currentMarketing = currentMarketing;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isMercator() {
        return mercator;
    }
    
    /**
     * 
     * @param mercator 
     */
    public void setMercator(boolean mercator) {
        this.mercator = mercator;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isGentran() {
        return gentran;
    }
    
    /**
     * 
     * @param gentran 
     */
    public void setGentran(boolean gentran) {
        this.gentran = gentran;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isSeeBeyond() {
        return seeBeyond;
    }
    
    /**
     * 
     * @param seeBeyond 
     */
    public void setSeeBeyond(boolean seeBeyond) {
        this.seeBeyond = seeBeyond;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isMessageBroker() {
        return messageBroker;
    }
    
    /**
     * 
     * @param messageBroker 
     */
    public void setMessageBroker(boolean messageBroker) {
        this.messageBroker = messageBroker;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isIcs() {
        return ics;
    }
    
    /**
     * 
     * @param ics 
     */
    public void setIcs(boolean ics) {
        this.ics = ics;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isWps() {
        return wps;
    }
    
    /**
     * 
     * @param wps 
     */
    public void setWps(boolean wps) {
        this.wps = wps;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isTibco() {
        return tibco;
    }
    
    /**
     * 
     * @param tibco 
     */
    public void setTibco(boolean tibco) {
        this.tibco = tibco;
    }
    
    
    /**
     * 
     * @return boolean
     */
    public boolean isWebMethods() {
        return webMethods;
    }
    
    /**
     * 
     * @param webMethods 
     */
    public void setWebMethods(boolean webMethods) {
        this.webMethods = webMethods;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isSap() {
        return sap;
    }
    
    /**
     * 
     * @param sap 
     */
    public void setSap(boolean sap) {
        this.sap = sap;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isOracleApps() {
        return oracleApps;
    }
    
    /**
     * 
     * @param oracleApps 
     */
    public void setOracleApps(boolean oracleApps) {
        this.oracleApps = oracleApps;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isJdEdwards() {
        return jdEdwards;
    }
    
    /**
     * 
     * @param jdEdwards 
     */
    public void setJdEdwards(boolean jdEdwards) {
        this.jdEdwards = jdEdwards;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isPeopleSoft() {
        return peopleSoft;
    }
    
    /**
     * 
     * @param peopleSoft 
     */
    public void setPeopleSoft(boolean peopleSoft) {
        this.peopleSoft = peopleSoft;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isHarbinger() {
        return harbinger;
    }
    
    /**
     * 
     * @param harbinger 
     */
    public void setHarbinger(boolean harbinger) {
        this.harbinger = harbinger;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isWdi() {
        return wdi;
    }
    
    /**
     * 
     * @param wdi 
     */
    public void setWdi(boolean wdi) {
        this.wdi = wdi;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isVitria() {
        return vitria;
    }
    
    /**
     * 
     * @param vitria 
     */
    public void setVitria(boolean vitria) {
        this.vitria = vitria;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isBiztalkServer() {
        return biztalkServer;
    }
    
    /**
     * 
     * @param biztalkServer 
     */
    public void setBiztalkServer(boolean biztalkServer) {
        this.biztalkServer = biztalkServer;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isSiebel() {
        return siebel;
    }
    
    /**
     * 
     * @param siebel 
     */
    public void setSiebel(boolean siebel) {
        this.siebel = siebel;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isBaan() {
        return baan;
    }
    
    /**
     * 
     * @param baan 
     */
    public void setBaan(boolean baan) {
        this.baan = baan;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isBeaPortals() {
        return beaPortals;
    }
    
    /**
     * 
     * @param beaPortals 
     */
    public void setBeaPortals(boolean beaPortals) {
        this.beaPortals = beaPortals;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isOraclePortals() {
        return oraclePortals;
    }
    
    /**
     * 
     * @param oraclePortals 
     */
    public void setOraclePortals(boolean oraclePortals) {
        this.oraclePortals = oraclePortals;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isIbmPortals() {
        return ibmPortals;
    }
    
    /**
     * 
     * @param ibmPortals 
     */
    public void setIbmPortals(boolean ibmPortals) {
        this.ibmPortals = ibmPortals;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isSharePoint() {
        return sharePoint;
    }
    
    /**
     * 
     * @param sharePoint 
     */
    public void setSharePoint(boolean sharePoint) {
        this.sharePoint = sharePoint;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isSapPortals() {
        return sapPortals;
    }
    
    /**
     * 
     * @param sapPortals 
     */
    public void setSapPortals(boolean sapPortals) {
        this.sapPortals = sapPortals;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isMicrosoft() {
        return microsoft;
    }
    
    /**
     * 
     * @param microsoft 
     */
    public void setMicrosoft(boolean microsoft) {
        this.microsoft = microsoft;
    }
    
    /**
     * 
     * @return String
     */
    public String getMiddleName() {
        return middleName;
    }
    
    /**
     * 
     * @param middleName 
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    /**
     * 
     * @return List
     */
    public List getPriorityList() {
        return priorityList;
    }
    
    /**
     * 
     * @param priorityList 
     */
    public void setPriorityList(List priorityList) {
        this.priorityList = priorityList;
    }
    
    /**
     * 
     * @return Map
     */
    public Map getCampaignIdMap() {
        return campaignIdMap;
    }
    
    /**
     * 
     * @param campaignIdMap 
     */
    public void setCampaignIdMap(Map campaignIdMap) {
        this.campaignIdMap = campaignIdMap;
    }
    
    /**
     * 
     * @return Map
     */
    public Map getAssignedToIdMap() {
        return assignedToIdMap;
    }
    
    /**
     * 
     * @param assignedToIdMap 
     */
    public void setAssignedToIdMap(Map assignedToIdMap) {
        this.assignedToIdMap = assignedToIdMap;
    }
    
    /**
     * 
     * @return List
     */
    public List getActivityStatusList() {
        return activityStatusList;
    }
    
    /**
     * 
     * @param activityStatusList 
     */
    public void setActivityStatusList(List activityStatusList) {
        this.activityStatusList = activityStatusList;
    }
    
    /**
     * 
     * @return String
     */
    public String getActivityType() {
        return activityType;
    }
    
    /**
     * 
     * @param activityType 
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
    
    /**
     * 
     * @return int
     */
    public int getContactActivId() {
        return contactActivId;
    }
    
    /**
     * 
     * @param contactActivId 
     */
    public void setContactActivId(int contactActivId) {
        this.contactActivId = contactActivId;
    }
    
    /**
     * 
     * @return int
     */
    public int getAccountActivId() {
        return accountActivId;
    }
    
    /**
     * 
     * @param accountActivId 
     */
    public void setAccountActivId(int accountActivId) {
        this.accountActivId = accountActivId;
    }
    
    /**
     * 
     * @return int
     */
    public int getActivityId() {
        return activityId;
    }
    
    /**
     * 
     * @param activityId 
     */
    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
    
    /**
     * 
     * @return String
     */
    public String getPriority() {
        return priority;
    }
    
    /**
     * 
     * @param priority 
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    /**
     * 
     * @return int
     */
    public int getCampaignId() {
        return campaignId;
    }
    
    /**
     * 
     * @param campaignId 
     */
    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }
    
    /**
     * 
     * @return String
     */
    public String getAssignedToId() {
        return assignedToId;
    }
    
    /**
     * 
     * @param assignedToId 
     */
    public void setAssignedToId(String assignedToId) {
        this.assignedToId = assignedToId;
    }
    
    /**
     * 
     * @return String
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * 
     * @param status 
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isAlarm() {
        return alarm;
    }
    
    /**
     * 
     * @param alarm 
     */
    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }
    
    /**
     * 
     * @return String
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * 
     * @return String
     */
    public String getComments() {
        return comments;
    }
    
    /**
     * 
     * @param comments 
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    /**
     * 
     * @return String
     */
    public String getCreatedById() {
        return createdById;
    }
    
    /**
     * 
     * @param createdById 
     */
    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }
    
    /**
     * 
     * @return String
     */
    public String getModifiedById() {
        return modifiedById;
    }
    
    /**
     * 
     * @param modifiedById 
     */
    public void setModifiedById(String modifiedById) {
        this.modifiedById = modifiedById;
    }
    
    /**
     * 
     * @return String
     */
    public String getAssignedById() {
        return assignedById;
    }
    
    /**
     * 
     * @param assignedById 
     */
    public void setAssignedById(String assignedById) {
        this.assignedById = assignedById;
    }
    
    /**
     * 
     * @return Timestamp
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    
    /**
     * 
     * @param createdDate 
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    
    /**
     * 
     * @return Timestamp
     */
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }
    
    /**
     * 
     * @param modifiedDate 
     */
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    /**
     * 
     * @return String
     */
    public String getEmployeeName() {
        return employeeName;
    }
    
    /**
     * 
     * @param employeeName 
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    /**
     * 
     * @return String
     */
    public String getActivityTypeName() {
        return activityTypeName;
    }
    
    /**
     * 
     * @param activityTypeName 
     */
    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }
    
    /**
     * 
     * @return int
     */
    public int getId() {
        return id;
    }
    
    /**
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * 
     * @return String
     */
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    /**
     * 
     * @param submitFrom 
     */
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }
    /**
     * 
     * @return String
     */
    public String getAccountSearchBy() {
        return accountSearchBy;
    }
    
    /**
     * 
     * @param accountSearchBy 
     */
    public void setAccountSearchBy(String accountSearchBy) {
        this.accountSearchBy = accountSearchBy;
    }
    
    /**
     * 
     * @return String
     */
    public String getSelectedTab() {
        return selectedTab;
    }
    
    /**
     * 
     * @param selectedTab 
     */
    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }
    
    /**
     * 
     * @return String
     */
    public String getShowGrid() {
        return showGrid;
    }
    
    /**
     * 
     * @param showGrid 
     */
    public void setShowGrid(String showGrid) {
        this.showGrid = showGrid;
    }

    /**
     * 
     * @return Timestamp
     */
    public Timestamp getDueDate() {
        return dueDate;
    }

    /**
     * 
     * @param dueDate 
     */
    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the createdDateFrom
     */
    public String getCreatedDateFrom() {
        return createdDateFrom;
    }

    /**
     * @param createdDateFrom the createdDateFrom to set
     */
    public void setCreatedDateFrom(String createdDateFrom) {
        this.createdDateFrom = createdDateFrom;
    }

    /**
     * @return the createdDateTo
     */
    public String getCreatedDateTo() {
        return createdDateTo;
    }

    /**
     * @param createdDateTo the createdDateTo to set
     */
    public void setCreatedDateTo(String createdDateTo) {
        this.createdDateTo = createdDateTo;
    }

    /**
     * @return the jobId
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * @param jobId the jobId to set
     */
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the locationList
     */
    public List getLocationList() {
        return locationList;
    }

    /**
     * @param locationList the locationList to set
     */
    public void setLocationList(List locationList) {
        this.locationList = locationList;
    }

    /**
     * @return the jobConsultantId
     */
    public int getJobConsultantId() {
        return jobConsultantId;
    }

    /**
     * @param jobConsultantId the jobConsultantId to set
     */
    public void setJobConsultantId(int jobConsultantId) {
        this.jobConsultantId = jobConsultantId;
    }

    /**
     * @return the timeList
     */
    public List getTimeList() {
        return timeList;
    }

    /**
     * @param timeList the timeList to set
     */
    public void setTimeList(List timeList) {
        this.timeList = timeList;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the midDayFrom
     */
    public String getMidDayFrom() {
        return midDayFrom;
    }

    /**
     * @param midDayFrom the midDayFrom to set
     */
    public void setMidDayFrom(String midDayFrom) {
        this.midDayFrom = midDayFrom;
    }

    /**
     * @return the midDayTo
     */
    public String getMidDayTo() {
        return midDayTo;
    }

    /**
     * @param midDayTo the midDayTo to set
     */
    public void setMidDayTo(String midDayTo) {
        this.midDayTo = midDayTo;
    }

    /**
     * @return the eventId
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * @param eventId the eventId to set
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * @return the eventSearchType
     */
    public String getEventSearchType() {
        return eventSearchType;
    }

    /**
     * @param eventSearchType the eventSearchType to set
     */
    public void setEventSearchType(String eventSearchType) {
        this.eventSearchType = eventSearchType;
    }

    /**
     * @return the primarySpeakerExist
     */
    public String getPrimarySpeakerExist() {
        return primarySpeakerExist;
    }

    /**
     * @param primarySpeakerExist the primarySpeakerExist to set
     */
    public void setPrimarySpeakerExist(String primarySpeakerExist) {
        this.primarySpeakerExist = primarySpeakerExist;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return the trackNamesList
     */
    public List getTrackNamesList() {
        return trackNamesList;
    }

    /**
     * @param trackNamesList the trackNamesList to set
     */
    public void setTrackNamesList(List trackNamesList) {
        this.trackNamesList = trackNamesList;
    }

    /**
     * @return the trackName
     */
    public String getTrackName() {
        return trackName;
    }

    /**
     * @param trackName the trackName to set
     */
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    /**
     * @return the qmeetYear
     */
    public String getQmeetYear() {
        return qmeetYear;
    }

    /**
     * @param qmeetYear the qmeetYear to set
     */
    public void setQmeetYear(String qmeetYear) {
        this.qmeetYear = qmeetYear;
    }

    /**
     * @return the qmeetMap
     */
    public Map getQmeetMap() {
        return qmeetMap;
    }

    /**
     * @param qmeetMap the qmeetMap to set
     */
    public void setQmeetMap(Map qmeetMap) {
        this.qmeetMap = qmeetMap;
    }

    /**
     * @return the qmeetTitleId
     */
    public String getQmeetTitleId() {
        return qmeetTitleId;
    }

    /**
     * @param qmeetTitleId the qmeetTitleId to set
     */
    public void setQmeetTitleId(String qmeetTitleId) {
        this.qmeetTitleId = qmeetTitleId;
    }

    /**
     * @return the associatedEventMap
     */
    public Map getAssociatedEventMap() {
        return associatedEventMap;
    }

    /**
     * @param associatedEventMap the associatedEventMap to set
     */
    public void setAssociatedEventMap(Map associatedEventMap) {
        this.associatedEventMap = associatedEventMap;
    }

    /**
     * @return the dateOfPublish
     */
    public String getDateOfPublish() {
        return dateOfPublish;
    }

    /**
     * @param dateOfPublish the dateOfPublish to set
     */
    public void setDateOfPublish(String dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    /**
     * @return the searchSeriesTitle
     */
    public String getSearchSeriesTitle() {
        return searchSeriesTitle;
    }

    /**
     * @param searchSeriesTitle the searchSeriesTitle to set
     */
    public void setSearchSeriesTitle(String searchSeriesTitle) {
        this.searchSeriesTitle = searchSeriesTitle;
    }

    /**
     * @return the searchSeriesStatus
     */
    public String getSearchSeriesStatus() {
        return searchSeriesStatus;
    }

    /**
     * @param searchSeriesStatus the searchSeriesStatus to set
     */
    public void setSearchSeriesStatus(String searchSeriesStatus) {
        this.searchSeriesStatus = searchSeriesStatus;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the searchSeriesTrack
     */
    public String getSearchSeriesTrack() {
        return searchSeriesTrack;
    }

    /**
     * @param searchSeriesTrack the searchSeriesTrack to set
     */
    public void setSearchSeriesTrack(String searchSeriesTrack) {
        this.searchSeriesTrack = searchSeriesTrack;
    }

    /**
     * @return the seriesId
     */
    public String getSeriesId() {
        return seriesId;
    }

    /**
     * @param seriesId the seriesId to set
     */
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    /**
     * @return the unassociatedEventMap
     */
    public Map getUnassociatedEventMap() {
        return unassociatedEventMap;
    }

    /**
     * @param unassociatedEventMap the unassociatedEventMap to set
     */
    public void setUnassociatedEventMap(Map unassociatedEventMap) {
        this.unassociatedEventMap = unassociatedEventMap;
    }

    /**
     * @return the associatedEventId
     */
    public String getAssociatedEventId() {
        return associatedEventId;
    }

    /**
     * @param associatedEventId the associatedEventId to set
     */
    public void setAssociatedEventId(String associatedEventId) {
        this.associatedEventId = associatedEventId;
    }

    /**
     * @return the primarySpeakerId
     */
    public String getPrimarySpeakerId() {
        return primarySpeakerId;
    }

    /**
     * @param primarySpeakerId the primarySpeakerId to set
     */
    public void setPrimarySpeakerId(String primarySpeakerId) {
        this.primarySpeakerId = primarySpeakerId;
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
     * @return the eventSpeakerMap
     */
    public Map getEventSpeakerMap() {
        return eventSpeakerMap;
    }

    /**
     * @param eventSpeakerMap the eventSpeakerMap to set
     */
    public void setEventSpeakerMap(Map eventSpeakerMap) {
        this.eventSpeakerMap = eventSpeakerMap;
    }

    /**
     * @return the speakersMapExceptEventSpeakerMap
     */
    public Map getSpeakersMapExceptEventSpeakerMap() {
        return speakersMapExceptEventSpeakerMap;
    }

    /**
     * @param speakersMapExceptEventSpeakerMap the speakersMapExceptEventSpeakerMap to set
     */
    public void setSpeakersMapExceptEventSpeakerMap(Map speakersMapExceptEventSpeakerMap) {
        this.speakersMapExceptEventSpeakerMap = speakersMapExceptEventSpeakerMap;
    }

    /**
     * @return the speakersParameters
     */
    public Map getSpeakersParameters() {
        return speakersParameters;
    }

    /**
     * @param speakersParameters the speakersParameters to set
     */
    public void setSpeakersParameters(Map speakersParameters) {
        this.speakersParameters = speakersParameters;
    }

    /**
     * @return the eventTitle
     */
    public String getEventTitle() {
        return eventTitle;
    }

    /**
     * @param eventTitle the eventTitle to set
     */
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    /**
     * @return the objectType
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * @param objectType the objectType to set
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     * @return the trackNamesMap
     */
    public Map getTrackNamesMap() {
        return trackNamesMap;
    }

    /**
     * @param trackNamesMap the trackNamesMap to set
     */
    public void setTrackNamesMap(Map trackNamesMap) {
        this.trackNamesMap = trackNamesMap;
    }

    @Override
    public boolean acceptableParameterName(String inputParamName) {
        boolean isParamAcceptable = true;
        if(inputParamName.equals("assignedTeamMembers")||inputParamName.equals("availableSpeakers")){
            isParamAcceptable = false;
        }
        return isParamAcceptable;
    }

    @Override
    public void setParameters(Map map) {
        this.speakersParameters=map;
        this.bdmParameters=map;
    }

    /**
     * @return the accessType
     */
    public String getAccessType() {
        return accessType;
    }

    /**
     * @param accessType the accessType to set
     */
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the investmentName1
     */
    public String getInvestmentName1() {
        return investmentName1;
    }

    /**
     * @param investmentName1 the investmentName1 to set
     */
    public void setInvestmentName1(String investmentName1) {
        this.investmentName1 = investmentName1;
    }

    /**
     * @return the startDateInvestment
     */
    public String getStartDateInvestment() {
        return startDateInvestment;
    }

    /**
     * @param startDateInvestment the startDateInvestment to set
     */
    public void setStartDateInvestment(String startDateInvestment) {
        this.startDateInvestment = startDateInvestment;
    }

    /**
     * @return the endDateInvestment
     */
    public String getEndDateInvestment() {
        return endDateInvestment;
    }

    /**
     * @param endDateInvestment the endDateInvestment to set
     */
    public void setEndDateInvestment(String endDateInvestment) {
        this.endDateInvestment = endDateInvestment;
    }

    /**
     * @return the countryInvestment
     */
    public String getCountryInvestment() {
        return countryInvestment;
    }

    /**
     * @param countryInvestment the countryInvestment to set
     */
    public void setCountryInvestment(String countryInvestment) {
        this.countryInvestment = countryInvestment;
    }

    /**
     * @return the totalExpenseAmount
     */
    public double getTotalExpenseAmount() {
        return totalExpenseAmount;
    }

    /**
     * @param totalExpenseAmount the totalExpenseAmount to set
     */
    public void setTotalExpenseAmount(double totalExpenseAmount) {
        this.totalExpenseAmount = totalExpenseAmount;
    }

    /**
     * @return the locationInvestment
     */
    public String getLocationInvestment() {
        return locationInvestment;
    }

    /**
     * @param locationInvestment the locationInvestment to set
     */
    public void setLocationInvestment(String locationInvestment) {
        this.locationInvestment = locationInvestment;
    }

    /**
     * @return the investmentDesc
     */
    public String getInvestmentDesc() {
        return investmentDesc;
    }

    /**
     * @param investmentDesc the investmentDesc to set
     */
    public void setInvestmentDesc(String investmentDesc) {
        this.investmentDesc = investmentDesc;
    }

    /**
     * @return the attachInvestment
     */
    public String getAttachInvestment() {
        return attachInvestment;
    }

    /**
     * @param attachInvestment the attachInvestment to set
     */
    public void setAttachInvestment(String attachInvestment) {
        this.attachInvestment = attachInvestment;
    }

    /**
     * @return the expensesAmountFrom
     */
    public double getExpensesAmountFrom() {
        return expensesAmountFrom;
    }

    /**
     * @param expensesAmountFrom the expensesAmountFrom to set
     */
    public void setExpensesAmountFrom(double expensesAmountFrom) {
        this.expensesAmountFrom = expensesAmountFrom;
    }

    /**
     * @return the expensesAmountTo
     */
    public double getExpensesAmountTo() {
        return expensesAmountTo;
    }

    /**
     * @param expensesAmountTo the expensesAmountTo to set
     */
    public void setExpensesAmountTo(double expensesAmountTo) {
        this.expensesAmountTo = expensesAmountTo;
    }

    /**
     * @return the httpServletResponse
     */
    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    /**
     * @param httpServletResponse the httpServletResponse to set
     */
    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * @return the investmentId
     */
    public int getInvestmentId() {
        return investmentId;
    }

    /**
     * @param investmentId the investmentId to set
     */
    public void setInvestmentId(int investmentId) {
        this.investmentId = investmentId;
    }

    /**
     * @return the contactsList
     */
    public Map getContactsList() {
        return contactsList;
    }

    /**
     * @param contactsList the contactsList to set
     */
    public void setContactsList(Map contactsList) {
        this.contactsList = contactsList;
    }

    /**
     * @return the accContacts
     */
    public String getAccContacts() {
        return accContacts;
    }

    /**
     * @param accContacts the accContacts to set
     */
    public void setAccContacts(String accContacts) {
        this.accContacts = accContacts;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
     * @return the expiryDate
     */
    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the leadId
     */
    public int getLeadId() {
        return leadId;
    }

    /**
     * @param leadId the leadId to set
     */
    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    /**
     * @return the contactsIdList
     */
    public List getContactsIdList() {
        return contactsIdList;
    }

    /**
     * @param contactsIdList the contactsIdList to set
     */
    public void setContactsIdList(List contactsIdList) {
        this.contactsIdList = contactsIdList;
    }

    /**
     * @return the currentAction
     */
    public String getCurrentAction() {
        return currentAction;
    }

    /**
     * @param currentAction the currentAction to set
     */
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    /**
     * @return the investmentList
     */
    public Map getInvestmentList() {
        return investmentList;
    }

    /**
     * @param investmentList the investmentList to set
     */
    public void setInvestmentList(Map investmentList) {
        this.investmentList = investmentList;
    }

    /**
     * @return the campaignTitle
     */
    public String getCampaignTitle() {
        return campaignTitle;
    }

    /**
     * @param campaignTitle the campaignTitle to set
     */
    public void setCampaignTitle(String campaignTitle) {
        this.campaignTitle = campaignTitle;
    }

    /**
     * @return the campaignStatus
     */
    public String getCampaignStatus() {
        return campaignStatus;
    }

    /**
     * @param campaignStatus the campaignStatus to set
     */
    public void setCampaignStatus(String campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    /**
     * @return the campaignStartDate
     */
    public String getCampaignStartDate() {
        return campaignStartDate;
    }

    /**
     * @param campaignStartDate the campaignStartDate to set
     */
    public void setCampaignStartDate(String campaignStartDate) {
        this.campaignStartDate = campaignStartDate;
    }

    /**
     * @return the campaignEndDate
     */
    public String getCampaignEndDate() {
        return campaignEndDate;
    }

    /**
     * @param campaignEndDate the campaignEndDate to set
     */
    public void setCampaignEndDate(String campaignEndDate) {
        this.campaignEndDate = campaignEndDate;
    }

    /**
     * @return the campaignName
     */
    public String getCampaignName() {
        return campaignName;
    }

    /**
     * @param campaignName the campaignName to set
     */
    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    /**
     * @return the navId
     */
    public int getNavId() {
        return navId;
    }

    /**
     * @param navId the navId to set
     */
    public void setNavId(int navId) {
        this.navId = navId;
    }

    /**
     * @return the tempVar
     */
    public int getTempVar() {
        return tempVar;
    }

    /**
     * @param tempVar the tempVar to set
     */
    public void setTempVar(int tempVar) {
        this.tempVar = tempVar;
    }

    /**
     * @return the sendDates
     */
    public String getSendDates() {
        return sendDates;
    }

    /**
     * @param sendDates the sendDates to set
     */
    public void setSendDates(String sendDates) {
        this.sendDates = sendDates;
    }

    /**
     * @return the clientCurrencyList
     */
    public List getClientCurrencyList() {
        return clientCurrencyList;
    }

    /**
     * @param clientCurrencyList the clientCurrencyList to set
     */
    public void setClientCurrencyList(List clientCurrencyList) {
        this.clientCurrencyList = clientCurrencyList;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
       this.httpServletResponse = httpServletResponse;
    }

    /**
     * @return the isInvestmentRecordsExist
     */
    public int getIsInvestmentRecordsExist() {
        return isInvestmentRecordsExist;
    }

    /**
     * @param isInvestmentRecordsExist the isInvestmentRecordsExist to set
     */
    public void setIsInvestmentRecordsExist(int isInvestmentRecordsExist) {
        this.isInvestmentRecordsExist = isInvestmentRecordsExist;
    }

    /**
     * @return the activeEmployeeMap
     */
    public Map getActiveEmployeeMap() {
        return activeEmployeeMap;
    }

    /**
     * @param activeEmployeeMap the activeEmployeeMap to set
     */
    public void setActiveEmployeeMap(Map activeEmployeeMap) {
        this.activeEmployeeMap = activeEmployeeMap;
    }

    /**
     * @return the accessTypeList
     */
    public List getAccessTypeList() {
        return accessTypeList;
    }

    /**
     * @param accessTypeList the accessTypeList to set
     */
    public void setAccessTypeList(List accessTypeList) {
        this.accessTypeList = accessTypeList;
    }

    /**
     * @return the emeetAccessType
     */
    public String getEmeetAccessType() {
        return emeetAccessType;
    }

    /**
     * @param emeetAccessType the emeetAccessType to set
     */
    public void setEmeetAccessType(String emeetAccessType) {
        this.emeetAccessType = emeetAccessType;
    }

    /**
     * @return the emeetAttendeesEmail
     */
    public String getEmeetAttendeesEmail() {
        return emeetAttendeesEmail;
    }

    /**
     * @param emeetAttendeesEmail the emeetAttendeesEmail to set
     */
    public void setEmeetAttendeesEmail(String emeetAttendeesEmail) {
        this.emeetAttendeesEmail = emeetAttendeesEmail;
    }

    /**
     * @return the emeetMeetingAccessStatus
     */
    public String getEmeetMeetingAccessStatus() {
        return emeetMeetingAccessStatus;
    }

    /**
     * @param emeetMeetingAccessStatus the emeetMeetingAccessStatus to set
     */
    public void setEmeetMeetingAccessStatus(String emeetMeetingAccessStatus) {
        this.emeetMeetingAccessStatus = emeetMeetingAccessStatus;
    }

    /**
     * @return the emeetStatusByDate
     */
    public String getEmeetStatusByDate() {
        return emeetStatusByDate;
    }

    /**
     * @param emeetStatusByDate the emeetStatusByDate to set
     */
    public void setEmeetStatusByDate(String emeetStatusByDate) {
        this.emeetStatusByDate = emeetStatusByDate;
    }

    /**
     * @return the executiveMeetMonthSearch
     */
    public String getExecutiveMeetMonthSearch() {
        return executiveMeetMonthSearch;
    }

    /**
     * @param executiveMeetMonthSearch the executiveMeetMonthSearch to set
     */
    public void setExecutiveMeetMonthSearch(String executiveMeetMonthSearch) {
        this.executiveMeetMonthSearch = executiveMeetMonthSearch;
    }

    /**
     * @return the emeetType
     */
    public String getEmeetType() {
        return emeetType;
    }

    /**
     * @param emeetType the emeetType to set
     */
    public void setEmeetType(String emeetType) {
        this.emeetType = emeetType;
    }

    /**
     * @return the emeetToDate
     */
    public String getEmeetToDate() {
        return emeetToDate;
    }

    /**
     * @param emeetToDate the emeetToDate to set
     */
    public void setEmeetToDate(String emeetToDate) {
        this.emeetToDate = emeetToDate;
    }

    /**
     * @return the emeetFromDate
     */
    public String getEmeetFromDate() {
        return emeetFromDate;
    }

    /**
     * @param emeetFromDate the emeetFromDate to set
     */
    public void setEmeetFromDate(String emeetFromDate) {
        this.emeetFromDate = emeetFromDate;
    }

    /**
     * @return the contactStartDate
     */
    public String getContactStartDate() {
        return contactStartDate;
    }

    /**
     * @param contactStartDate the contactStartDate to set
     */
    public void setContactStartDate(String contactStartDate) {
        this.contactStartDate = contactStartDate;
    }

    /**
     * @return the contactEndDate
     */
    public String getContactEndDate() {
        return contactEndDate;
    }

    /**
     * @param contactEndDate the contactEndDate to set
     */
    public void setContactEndDate(String contactEndDate) {
        this.contactEndDate = contactEndDate;
    }

    /**
     * @return the iotNameSearch
     */
    public String getIotNameSearch() {
        return iotNameSearch;
    }

    /**
     * @param iotNameSearch the iotNameSearch to set
     */
    public void setIotNameSearch(String iotNameSearch) {
        this.iotNameSearch = iotNameSearch;
    }

    /**
     * @return the iotZipCodeSearch
     */
    public String getIotZipCodeSearch() {
        return iotZipCodeSearch;
    }

    /**
     * @param iotZipCodeSearch the iotZipCodeSearch to set
     */
    public void setIotZipCodeSearch(String iotZipCodeSearch) {
        this.iotZipCodeSearch = iotZipCodeSearch;
    }

    /**
     * @return the signatureNameSearch
     */
    public String getSignatureNameSearch() {
        return signatureNameSearch;
    }

    /**
     * @param signatureNameSearch the signatureNameSearch to set
     */
    public void setSignatureNameSearch(String signatureNameSearch) {
        this.signatureNameSearch = signatureNameSearch;
    }

    /**
     * @return the signatureEmailSearch
     */
    public String getSignatureEmailSearch() {
        return signatureEmailSearch;
    }

    /**
     * @param signatureEmailSearch the signatureEmailSearch to set
     */
    public void setSignatureEmailSearch(String signatureEmailSearch) {
        this.signatureEmailSearch = signatureEmailSearch;
    }

    /**
     * @return the investmentTitle
     */
    public String getInvestmentTitle() {
        return investmentTitle;
    }

    /**
     * @param investmentTitle the investmentTitle to set
     */
    public void setInvestmentTitle(String investmentTitle) {
        this.investmentTitle = investmentTitle;
    }

    /**
     * @return the investmentBDMsMap
     */
    public Map getInvestmentBDMsMap() {
        return investmentBDMsMap;
    }

    /**
     * @param investmentBDMsMap the investmentBDMsMap to set
     */
    public void setInvestmentBDMsMap(Map investmentBDMsMap) {
        this.investmentBDMsMap = investmentBDMsMap;
    }

    /**
     * @return the bdmsMapExceptInvestmentBDMMap
     */
    public Map getBdmsMapExceptInvestmentBDMMap() {
        return bdmsMapExceptInvestmentBDMMap;
    }

    /**
     * @param bdmsMapExceptInvestmentBDMMap the bdmsMapExceptInvestmentBDMMap to set
     */
    public void setBdmsMapExceptInvestmentBDMMap(Map bdmsMapExceptInvestmentBDMMap) {
        this.bdmsMapExceptInvestmentBDMMap = bdmsMapExceptInvestmentBDMMap;
    }

    /**
     * @return the bdmParameters
     */
    public Map getBdmParameters() {
        return bdmParameters;
    }

    /**
     * @param bdmParameters the bdmParameters to set
     */
    public void setBdmParameters(Map bdmParameters) {
        this.bdmParameters = bdmParameters;
    }

    /**
     * @return the analystMap
     */
    public Map getAnalystMap() {
        return analystMap;
    }

    /**
     * @param analystMap the analystMap to set
     */
    public void setAnalystMap(Map analystMap) {
        this.analystMap = analystMap;
    }

    /**
     * @return the linkedLeadAccountsMap
     */
    public Map getLinkedLeadAccountsMap() {
        return linkedLeadAccountsMap;
    }

    /**
     * @param linkedLeadAccountsMap the linkedLeadAccountsMap to set
     */
    public void setLinkedLeadAccountsMap(Map linkedLeadAccountsMap) {
        this.linkedLeadAccountsMap = linkedLeadAccountsMap;
    }

    /**
     * @return the dashboardFlag
     */
    public int getDashboardFlag() {
        return dashboardFlag;
    }

    /**
     * @param dashboardFlag the dashboardFlag to set
     */
    public void setDashboardFlag(int dashboardFlag) {
        this.dashboardFlag = dashboardFlag;
    }

    /**
     * @return the bdmMap
     */
    public Map getBdmMap() {
        return bdmMap;
    }

    /**
     * @param bdmMap the bdmMap to set
     */
    public void setBdmMap(Map bdmMap) {
        this.bdmMap = bdmMap;
    }

    /**
     * @return the bdmId
     */
    public String getBdmId() {
        return bdmId;
    }

    /**
     * @param bdmId the bdmId to set
     */
    public void setBdmId(String bdmId) {
        this.bdmId = bdmId;
    }

    /**
     * @return the accountsMap
     */
    public Map getAccountsMap() {
        return accountsMap;
    }

    /**
     * @param accountsMap the accountsMap to set
     */
    public void setAccountsMap(Map accountsMap) {
        this.accountsMap = accountsMap;
    }

    /**
     * @return the leadStatesList
     */
    public List getLeadStatesList() {
        return leadStatesList;
    }

    /**
     * @param leadStatesList the leadStatesList to set
     */
    public void setLeadStatesList(List leadStatesList) {
        this.leadStatesList = leadStatesList;
    }

    /**
     * @return the investmentName
     */
    public String getInvestmentName() {
        return investmentName;
    }

    /**
     * @param investmentName the investmentName to set
     */
    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }

    /**
     * @return the salesMap
     */
    public Map getSalesMap() {
        return salesMap;
    }

    /**
     * @param salesMap the salesMap to set
     */
    public void setSalesMap(Map salesMap) {
        this.salesMap = salesMap;
    }

    /**
     * @return the oppurtunityAccountName
     */
    public String getOppurtunityAccountName() {
        return oppurtunityAccountName;
    }

    /**
     * @param oppurtunityAccountName the oppurtunityAccountName to set
     */
    public void setOppurtunityAccountName(String oppurtunityAccountName) {
        this.oppurtunityAccountName = oppurtunityAccountName;
    }

    /**
     * @return the locationsMap
     */
    public Map getLocationsMap() {
        return locationsMap;
    }

    /**
     * @param locationsMap the locationsMap to set
     */
    public void setLocationsMap(Map locationsMap) {
        this.locationsMap = locationsMap;
    }

    /**
     * @return the constantContactList
     */
    public Map getConstantContactList() {
        return constantContactList;
    }

    /**
     * @param constantContactList the constantContactList to set
     */
    public void setConstantContactList(Map constantContactList) {
        this.constantContactList = constantContactList;
    }

    /**
     * @return the insideSalesBDEMap
     */
    public Map getInsideSalesBDEMap() {
        return insideSalesBDEMap;
    }

    /**
     * @param insideSalesBDEMap the insideSalesBDEMap to set
     */
    public void setInsideSalesBDEMap(Map insideSalesBDEMap) {
        this.insideSalesBDEMap = insideSalesBDEMap;
    }

    /**
     * @return the bdeLoginId
     */
    public String getBdeLoginId() {
        return bdeLoginId;
    }

    /**
     * @param bdeLoginId the bdeLoginId to set
     */
    public void setBdeLoginId(String bdeLoginId) {
        this.bdeLoginId = bdeLoginId;
    }

    /**
     * @return the lastActivityFrom
     */
    public String getLastActivityFrom() {
        return lastActivityFrom;
    }

    /**
     * @param lastActivityFrom the lastActivityFrom to set
     */
    public void setLastActivityFrom(String lastActivityFrom) {
        this.lastActivityFrom = lastActivityFrom;
    }

    /**
     * @return the lastActivityTo
     */
    public String getLastActivityTo() {
        return lastActivityTo;
    }

    /**
     * @param lastActivityTo the lastActivityTo to set
     */
    public void setLastActivityTo(String lastActivityTo) {
        this.lastActivityTo = lastActivityTo;
    }

    /**
     * @return the opportunity
     */
    public String getOpportunity() {
        return opportunity;
    }

    /**
     * @param opportunity the opportunity to set
     */
    public void setOpportunity(String opportunity) {
        this.opportunity = opportunity;
    }

    /**
     * @return the touched
     */
    public String getTouched() {
        return touched;
    }

    /**
     * @param touched the touched to set
     */
    public void setTouched(String touched) {
        this.touched = touched;
    }

    /**
     * @return the backToFlag
     */
    public String getBackToFlag() {
        return backToFlag;
    }

    /**
     * @param backToFlag the backToFlag to set
     */
    public void setBackToFlag(String backToFlag) {
        this.backToFlag = backToFlag;
    }

    /**
     * @return the passedBy1hidden
     */
    public String getPassedBy1hidden() {
        return passedBy1hidden;
    }

    /**
     * @param passedBy1hidden the passedBy1hidden to set
     */
    public void setPassedBy1hidden(String passedBy1hidden) {
        this.passedBy1hidden = passedBy1hidden;
    }

    /**
     * @return the passedBy2hidden
     */
    public String getPassedBy2hidden() {
        return passedBy2hidden;
    }

    /**
     * @param passedBy2hidden the passedBy2hidden to set
     */
    public void setPassedBy2hidden(String passedBy2hidden) {
        this.passedBy2hidden = passedBy2hidden;
    }

    /**
     * @return the passedBy3hidden
     */
    public String getPassedBy3hidden() {
        return passedBy3hidden;
    }

    /**
     * @param passedBy3hidden the passedBy3hidden to set
     */
    public void setPassedBy3hidden(String passedBy3hidden) {
        this.passedBy3hidden = passedBy3hidden;
    }

    /**
     * @return the comments1
     */
    public String getComments1() {
        return comments1;
    }

    /**
     * @param comments1 the comments1 to set
     */
    public void setComments1(String comments1) {
        this.comments1 = comments1;
    }

    /**
     * @return the comments2
     */
    public String getComments2() {
        return comments2;
    }

    /**
     * @param comments2 the comments2 to set
     */
    public void setComments2(String comments2) {
        this.comments2 = comments2;
    }

    /**
     * @return the comments3
     */
    public String getComments3() {
        return comments3;
    }

    /**
     * @param comments3 the comments3 to set
     */
    public void setComments3(String comments3) {
        this.comments3 = comments3;
    }

    /**
     * @return the nextSteps1
     */
    public String getNextSteps1() {
        return nextSteps1;
    }

    /**
     * @param nextSteps1 the nextSteps1 to set
     */
    public void setNextSteps1(String nextSteps1) {
        this.nextSteps1 = nextSteps1;
    }

    /**
     * @return the nextSteps2
     */
    public String getNextSteps2() {
        return nextSteps2;
    }

    /**
     * @param nextSteps2 the nextSteps2 to set
     */
    public void setNextSteps2(String nextSteps2) {
        this.nextSteps2 = nextSteps2;
    }

    /**
     * @return the nextSteps3
     */
    public String getNextSteps3() {
        return nextSteps3;
    }

    /**
     * @param nextSteps3 the nextSteps3 to set
     */
    public void setNextSteps3(String nextSteps3) {
        this.nextSteps3 = nextSteps3;
    }

    /**
     * @return the passedBy1List
     */
    public List getPassedBy1List() {
        return passedBy1List;
    }

    /**
     * @param passedBy1List the passedBy1List to set
     */
    public void setPassedBy1List(List passedBy1List) {
        this.passedBy1List = passedBy1List;
    }

    /**
     * @return the passedBy2List
     */
    public List getPassedBy2List() {
        return passedBy2List;
    }

    /**
     * @param passedBy2List the passedBy2List to set
     */
    public void setPassedBy2List(List passedBy2List) {
        this.passedBy2List = passedBy2List;
    }

    /**
     * @return the passedBy3List
     */
    public List getPassedBy3List() {
        return passedBy3List;
    }

    /**
     * @param passedBy3List the passedBy3List to set
     */
    public void setPassedBy3List(List passedBy3List) {
        this.passedBy3List = passedBy3List;
    }

    /**
     * @return the investmentType
     */
    public String getInvestmentType() {
        return investmentType;
    }

    /**
     * @param investmentType the investmentType to set
     */
    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    /**
     * @return the reportsTo
     */
    public String getReportsTo() {
        return reportsTo;
    }

    /**
     * @param reportsTo the reportsTo to set
     */
    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    /**
     * @return the allEmployeesList
     */
    public Map getAllEmployeesList() {
        return allEmployeesList;
    }

    /**
     * @param allEmployeesList the allEmployeesList to set
     */
    public void setAllEmployeesList(Map allEmployeesList) {
        this.allEmployeesList = allEmployeesList;
    }

    /**
     * @return the upcommingConferencesList
     */
    public Map getUpcommingConferencesList() {
        return upcommingConferencesList;
    }

    /**
     * @param upcommingConferencesList the upcommingConferencesList to set
     */
    public void setUpcommingConferencesList(Map upcommingConferencesList) {
        this.upcommingConferencesList = upcommingConferencesList;
    }

    /**
     * @return the totalConferencesList
     */
    public Map getTotalConferencesList() {
        return totalConferencesList;
    }

    /**
     * @param totalConferencesList the totalConferencesList to set
     */
    public void setTotalConferencesList(Map totalConferencesList) {
        this.totalConferencesList = totalConferencesList;
    }
    
    
}
