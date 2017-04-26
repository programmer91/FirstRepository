/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :  com.mss.mirage.crm.accounts
 *
 * Date    :  September 26, 2007, 12:35 PM
 *
 * Author  :  Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    :  AccountAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.crm.accounts;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataServiceLocator;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

//new
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
//import org.apache.log4j.SimpleLayout;
//import org.apache.log4j.FileAppender;
import org.apache.log4j.PropertyConfigurator;
import com.mss.mirage.util.SecurityProperties;







import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocatorException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts2.interceptor.ServletResponseAware;


import java.sql.*;
import java.util.Calendar;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * This Class AccountAction is used for getting new Account details.
 * And also edit, update and listing of Accounts can be done using this
 * AccountAction class.
 *
 * @see    com.mss.mirage.util.ApplicationDataProvider
 * @see    com.mss.mirage.crm.accounts.AccountVTO
 * @see    com.mss.mirage.crm.accounts.AccountService
 */
public class AccountAction extends ActionSupport
        implements ServletRequestAware, ParameterNameAware, ParameterAware,ServletResponseAware {

    /**
     * Here the viewType means the type of Account choosen by the End-user
     * The type may be All Accounts or only Individual Accounts
     *
     */
    private String viewType;
    /**
     * The resultType means the return type of the method.
     * whether the return type is Success or failure or other.
     */
    private String resultType;
    /**
     * The operationType means the type we are calling in the
     * Stored Procedure whether ADD or UPDATE or OTHER.
     */
    private String operationType;
    /**
     * The resultMessage is used to display the stored info. in the form
     */
    private String resultMessage;
    private Date dashBoardEndDate;
    private Date dashBoardStartDate;
    private List activityTypeList = new ArrayList();
    private String activityType;
    /** Creating a reference variable for <code>AccountVTO</code> class .
     *  { @link com.mss.mirage.crm.accounts.AccountVTO }
     */
    private AccountVTO currentAccount;
    /**START: ReAssign Accounts */
    private String frmLoginId;
    private String toLoginId;
    /**END: ReAssign Accounts */
    private SoftwareVTO currentSoftwaresVTO;
    private String accountSearchAction;
    private String unTouchedSearch;
    private String accountSearchBy;
    private String isRequestFromSearch;
    private String isRequestFromGrid;
    private Map accountTeamMap = new TreeMap();
    private Map salesTeamExceptAccountTeamMap = new TreeMap();
    private String primaryTeamMember;
    private String lastActivityFrom;
    private String lastActivityTo;
    private Map accountTeamParameters;
    /** Creating a reference variable for <code>AccountService</code> class .
     *  { @link com.mss.mirage.crm.accounts.AccountService }
     */
    private AccountService accountService;
    /** Creating a reference variable for <code>ApplicationDataProvider</code> class .
     *  { @link com.mss.mirage.util.ApplicationDataProvider }
     */
    private HibernateDataProvider hibernateDataProvider;
    private DataSourceDataProvider dataSourceDataProvider;
    /** Creating a reference variable for HttpServletRequest. */
    private HttpServletRequest httpServletRequest;
    /**
     * Creating a String queryString used to store SQL Query
     */
    private String queryString;
    /**
     * Creating a StringBuffer queryStringBuffer used to append SQL Query
     */
    private StringBuffer queryStringBuffer;
    /** Creating new instance for accountsList. */
    private List accountsList = new ArrayList();
    //Pre Configurable Varaibles
    /** Creating new instance for accountTypeList. */
    private List accountTypeList = new ArrayList();
    /** Creating new instance for accountStatusList. */
    private List accountStatusList = new ArrayList();
    /** Creating new instance for industryList. */
    private List industryList = new ArrayList();
    /** Creating new instance for regionList. */
    private List regionList = new ArrayList();
    /** Creating new instance for territoryList. */
    private List territoryList = new ArrayList();
    /** Creating new instance for countryList. */
    private List countryList = new ArrayList();
    /** Creating new instance for statesList. */
    private List statesList = new ArrayList();
    private Map myTeamMembers = new TreeMap();
    //From Session
    /* Creating a String createdBy and store value from the login user-id in the form. */
    private String createdBy;
    /* Creating a String modifiedBy and store value from the login user-id in the form. */
    private String modifiedBy;
    /* Creating a String activityBY and store value from the login user-id in the form. */
    private String activityBY;
    /* Creating a Timestamp dateLastActivity to get date and time from the local system. */
    private Timestamp dateLastActivity;
    //Default Time
    /* Creating a Timestamp dateCreated to get date and time from the local system. */
    private Timestamp dateCreated;
    /* Creating a Timestamp dateModified to get date and time from the local system. */
    private Timestamp dateModified;
    // Form Variables
    /* Creating a primaryAddressId to store addressId in the database */
    private int primaryAddressId;
    /* Creating id to store AccountID in the database  */
    private int id;
    private String accId;
    /* Creating accountTeam to store in the database */
    private String accountTeam;
    /* Creating a String accountType to store the type of account */
    private String accountType;
    /* Creating a String accountName to store the account name */
    private String accountName;
    /* Creating a String synonyms to store the alias name for account */
    private String synonyms;
    /* The String region is used for storing the region name to which account belons to */
    private String region;
    /* The String territory is used for storing the territory name to which account belons to */
    private String territory;
    /* The String industry is used for storing the industry name to which account belons to */
    private String industry;
    /* The String status is used for storing the status of the account whether it is ACTIVE OR NOT */
    private String status;
    /* The String stockSymbol1 is used for storing the stockSymbol1 of the account */
    private String stockSymbol1;
    /* The String stockSymbol2 is used for storing the stockSymbol2 of the account */
    private String stockSymbol2;
    /* The double revenues is used for storing the type of account revenues */
    private double revenues;
    /* The int noOfEmployees is used for storing the noOfEmployees related to the account */
    private int noOfEmployees;
    /* The double itBudget is used for storing the itBudget for that account */
    private double itBudget;
    /* The String taxId is used for storing the taxId of the particular account */
    private String taxId;
    /* The String leadSource is used for storing the leadSource of the particular account */
    private String leadSource;
    /** The String addressType is used for storing the addressType of the particular account
     *  like Primary Address */
    private String addressType;
    /* The String city is used for storing the city of the particular account */
    private String city;
    /* The String state is used for storing the state of the particular account */
    private String state;
    /* The String country is used for storing the country of the particular account */
    private String country;
    /* The String mailStop is used for storing the mailStop of the particular account */
    private String mailStop;
    /* The String zip is used for storing the zip of the particular account */
    private String zip;
    /* The String addressLine1 is used for storing the addressLine1 of the particular account */
    private String addressLine1;
    /* The String addressLine2 is used for storing the addressLine2 of the particular account */
    private String addressLine2;
    /* The String phone is used for storing the phone of the particular account */
    private String phone;
    /* The String fax is used for storing the fax of the particular account */
    private String fax;
    /* The String url is used for storing the url of the particular account */
    private String url;
    /* The String description is used for storing the description of the particular account */
    private String description;
    private String userLoginId;
    private String accountsListJspName;
    private String dateWithOutTime;
    private int userRoleId;
    private String accountId;
    private String contactEmailId;
    private boolean sap;
    private boolean mercator;
    private boolean messageBroker;
    private boolean gentran;
    private boolean wps;
    private boolean commerce; // It is Named as commerce (WCS) in the backend)
    private boolean ibmPortals;
    private boolean dataPower;
   private String ibmPPANo;
    private String ibmSiteNo;
    private java.sql.Date dateOfPPARenewal;
    //Parameters from dbgrid
    /** The String submitFrom is used for getting the value from the form
     * whether the value is SEARCH OR NOT
     */
    private String submitFrom;
    private String formAction;
//    private String txtSortCol;
//    private String txtSortAsc;
//    private String txtCurr;
    // ----VENDOR------------
    // The userRoleName is used to store the role name selected by the user when he logs in
    private String userRoleName;
    // The userWorkCountry is used to store the WorkingCountry name selected by the user when he logs in
    private String userWorkCountry;
    // End -- For reusable of screen(AccountListAll and AccountList) and there labels in Accounts Module and Vendors module
    private String title = "Account";
    /* The String empId is used for search criteria from ActivitiesInfo.jsp */
    private String empId;
    private int checkId;
    private String viewMemberSearch;
    /** The assignedToIdMap is used  for storing assignedToId. */
    private Map assignedToIdMap;
    private Map vendorTeamMap;
    /** New Variables **/
    private String conatctFName;
    private String conatctLName;
    private List subPracticeList;
    private List practiceList;
    /* New States 1 - 5 for getAccounts in bdm*/
    public String state1, state2, state3, state4, state5;
    private String accList;
    private String practice;
    private int priority;
    private String priority1;
    private int b2bPriority;
    private int bpmPriority;
    private int sapPriority;
    private int ecomPriority;
    private int qaPriority;
    private String pri;
    //parameter to define designation
    private List designationList;
    private String designationName;
    //parameter to get projectsList
    private Map projectsList;
    private String custLoginId;
    private String conatctAliasName;
    private String contactStatus;
    private int activitySummaryFlag;
    private List contactStatusList = new ArrayList();
    private int mainPriority;
    private int fromMonth;
    private int fromYear;
    private int toMonth;
    private int toYear;
    private String practiceName;
    private List typeList = new ArrayList();
    
    private String leadsResultMessage;
    private List opportunityStateList;
    private Map myTeamMembersForRep = new TreeMap();
    private HttpServletResponse httpServletResponse;
    
    private List practiceIdList = new ArrayList();
     private String opportunityState;
     private String requirementstatus;
     
     
     //Client Engagement fields
     private Map preSalesMap = new TreeMap();
     
  private String  requestorId;
private String rvpName;
private String customerName;
private int consultantId;
private String meetingStatus;
private String meetingType;
private String levelEngagement;
private int opportunityId;
private String engagementDetails;
private String insightDetails;
private String meetingDate;
private String startTime;
private String midDayFrom;
private String timeZone;
private String otherMeetingSlots;
private String additionalComments;
private String currentAction;
private int requestId;
private Map opportunitiesMap=new TreeMap();

private String otherMeetingDate;
private String otherStartTime;
private String otherMidDayFrom;
private String otherTimeZone;    

private String requestStage;
private String requestStatus;

private String startDate;
private String endDate;
private String requestorName;
private String requestResources;
private List requestResourcesList;
private String resourcesHidden="";
private Map resourceMap=new HashMap();
private String clientType;
private String opportunityName;
private String preRequestStage;
private Map bdmMap;

private String userName;

private int bdmId=0;

private String resourcesHidden1="";
private String requestType;
private String previousResourceIds="";
private Map campaignIdMap=new TreeMap();
private List requirementStatusList;

 private int pgNo=1;
private String backToFlag="";
private String operator="=";

private Map practiceSalesMap= new HashMap();
private int regionalMgrId;
    public List getContactStatusList() {
        return contactStatusList;
    }

    public void setContactStatusList(List contactStatusList) {
        this.contactStatusList = contactStatusList;
    }

    /** Creates a new instance of AccountAction */
    public AccountAction() {
    }
    //new
    //Logger logger = Logger.getLogger(AccountAction.class);
   /*public String getLogin(){
    resultType = LOGIN;
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
    try{
    dataSourceDataProvider = DataSourceDataProvider.getInstance();
    // setDesignationList((List)httpServletRequest.getSession(false).getAttribute("designationList"));
    accountService = ServiceLocator.getAccountService();
    setContactEmailId(accountService.getContactEmailId(getId()));
    //  setDesignationName(dataSourceDataProvider.getDesignationName(getId()));
    this.setAccountId(getAccountId());
    
    setProjectsList(dataSourceDataProvider.getProjectsByAccountId(getAccountId()));
    
    resultType = SUCCESS;
    
    }catch (Exception ex){
    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
    resultType =  ERROR;
    }
    }
    return resultType;
    }*/

    public String getLogin() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                // setDesignationList((List)httpServletRequest.getSession(false).getAttribute("designationList"));
                accountService = ServiceLocator.getAccountService();
                setContactEmailId(accountService.getContactEmailId(getId()));
                //  setDesignationName(dataSourceDataProvider.getDesignationName(getId()));
                this.setAccountId(getAccountId());
                String emailId = getContactEmailId();
                setCustLoginId(emailId.split("@")[0]);


                resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
    }
    /*
    public String createContactLogin(){
    resultType = LOGIN;
    String contactId="";
    String loginId="";
    String accountId="";
    String password="";
    String projects="";
    int updatedRows=0;
    int insertRows=0;
    String emailId="";
    
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
    try{
    setDesignationList((List)httpServletRequest.getSession(false).getAttribute("designationList"));
    accountService = ServiceLocator.getAccountService();
    contactId = httpServletRequest.getParameter("Id");
    accountId = httpServletRequest.getParameter("accId");
    // System.out.println("accc---->"+contactId);
    emailId = httpServletRequest.getParameter("emailId");
    projects=httpServletRequest.getParameter("listOfProjects");
    setProjectsList(dataSourceDataProvider.getInstance().getProjectsByAccountId(accountId));
    updatedRows = accountService.createContactLogin(Integer.parseInt(contactId),emailId,projects,accountId);
    resultMessage = "Login Details Sent  Successfully !";
    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
    
    resultType = SUCCESS;
    
    }catch (Exception ex){
    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
    ex.printStackTrace();
    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
    resultType =  ERROR;
    }
    }
    return resultType;
    }*/

    public String createContactLogin() {
        resultType = LOGIN;
        String contactId = "";
        String loginId = "";
        String accountId = "";
        String password = "";
        String projects = "";
        int updatedRows = 0;
        int insertRows = 0;
        String emailId = "";
        String val;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                setDesignationList((List) httpServletRequest.getSession(false).getAttribute("designationList"));
                accountService = ServiceLocator.getAccountService();
                contactId = httpServletRequest.getParameter("Id");
                accountId = httpServletRequest.getParameter("accId");
                // System.out.println("accc---->"+contactId);
                setCustLoginId(httpServletRequest.getParameter("custLoginId"));
                //    System.out.println("setCustLoginId--->"+getCustLoginId());
                setAccId(accountId);
                setAccountId(accountId);
                setId(Integer.parseInt(contactId));
                //  System.out.println("accountID-->"+accountId);
                //  System.out.println("getAccountID"+getAccountId());
                //  System.out.println("getAccID()-->"+getAccId());
                emailId = httpServletRequest.getParameter("emailId");
                setContactEmailId(emailId);
                projects = httpServletRequest.getParameter("listOfProjects");
                int count = dataSourceDataProvider.getInstance().checkForCustLoginId(getCustLoginId());


                if (count == 0) {
                    val = "value";
                    httpServletRequest.setAttribute("val", val);
                    updatedRows = accountService.createContactLogin(Integer.parseInt(contactId), accountId, emailId, getCustLoginId());
                    setResultMessage("Login Details Sent  Successfully !");
                } else {
                    val = null;
                    httpServletRequest.setAttribute("val", val);
                    setResultMessage("Login Id already exists please try with different Id");
                }
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());

                resultType = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
    }

    public String getReAssign() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_REASSIGN", userRoleId)) {
                try {
                    accountService = ServiceLocator.getAccountService();
                    setSalesTeamExceptAccountTeamMap((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                    //  setSalesTeamExceptAccountTeamMap(accountService.getAllSalesTeamExceptAccountTeam(getAccountTeamMap()));
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String editReAssignAccounts() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("EDIT_REASSING_ACC", userRoleId)) {
                try {
                    accountService = ServiceLocator.getAccountService();
                    setSalesTeamExceptAccountTeamMap((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                    int count = accountService.updateAssignAccounts(getFrmLoginId(), getToLoginId());
                    if (count > 0) {
                        resultType = SUCCESS;
                        setResultMessage("<font color=\"green\" size=\"1.5\">Accounts Transfered Successfully !</font>");
                    } else {
                        resultType = INPUT;
                        setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try Again !</font>");
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getCrmBackToList() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_CRM_BACKLIST", userRoleId)) {
                try {
                    String actionName = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);

                    if ("accountsListMy".equalsIgnoreCase(actionName) || "accountsListMyTeam".equalsIgnoreCase(actionName)) {
                        setAccountsListJspName("AccountsList");
                    } else if ("accountsListAll".equalsIgnoreCase(actionName)) {
                        setAccountsListJspName("AccountsListAll");
                    }else if("untouchedAccounts".equalsIgnoreCase(actionName) || "untouchedTeamAccounts".equalsIgnoreCase(actionName)){
                         setAccountsListJspName("UnTouchedAccountsList");
                    }

                    // vendors
                    if ("Vendor".equalsIgnoreCase(userRoleName) || "Recruitment".equalsIgnoreCase(userRoleName)) {
                        setTitle("Vendor");
                    }

                    //Retrieving Teammembers Map from session and seting to MyTeamManager Map
                    setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));


                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        prepare();
        return resultType;
    }

    public String getDashBoard() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            String dbUserName = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME);
                   
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_DASH_BOARD", userRoleId)) {
                try {
                    String activityQueryString = null;
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    String inMembers = null;
                    setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));
                    setAssignedToIdMap(dataSourceDataProvider.getEmployeeNamesByUserId("sales"));
                    setStatesList(dataSourceDataProvider.getStates("USA"));
                    setActivityTypeList(hibernateDataProvider.getCrmActivityTypes(ApplicationConstants.CRM_ACTIVITY_TYPE_OPTIONS));
                    setCampaignIdMap(DataSourceDataProvider.getInstance().getCampaignNames());
                    setTypeList(hibernateDataProvider.getOpportunityType(ApplicationConstants.CRM_OPS_TYPE_OPTIONS));
                    //Retrieving Teammembers Map from session and seting to MyTeamManager Map
                  //  setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                  //  setMyTeamMembersForRep(getMyTeamMembers());
                    // getMyTeamMembersForRep().put(loginId, dbUserName);
                     String titleType = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString();

                     if(titleType.equals("BDM")){
                      //  System.out.println("in bdm case");
                            String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();   
                        //    System.out.println("empId is------->"+empId);
                         setMyTeamMembers(DataSourceDataProvider.getInstance().getBdmAssociateList(empId));
                         // System.out.println("getMyTeamMembers()----->"+getMyTeamMembers());
                          setMyTeamMembersForRep(getMyTeamMembers());
                          // System.out.println("getMyTeamMembersForRep() is----->"+getMyTeamMembersForRep());
                           getMyTeamMembersForRep().put(loginId, dbUserName);
                           // System.out.println("getMyTeamMembersForRep() after setting ----->"+getMyTeamMembersForRep());
                      }
                    else{
                        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                    setMyTeamMembersForRep(getMyTeamMembers());
                   // System.out.println("getMyTeamMembersForRep() is----->"+getMyTeamMembersForRep());
                     getMyTeamMembersForRep().put(loginId, dbUserName);
                   //  System.out.println("getMyTeamMembersForRep() after setting ----->"+getMyTeamMembersForRep());
                    }

                            setPracticeIdList(dataSourceDataProvider.getPracticeByDepartment("GDC"));
                    setOpportunityStateList(DataSourceDataProvider.getInstance().getOpportunityStateList());
//                    AccountService accountService = ServiceLocator.getAccountService();
//                    if (loginId.equals("plokam")) {
                     //   setMyTeamMembers(accountService.getAllSalesTeamExceptAccountTeam(new TreeMap()));
//                    } else {
//                        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
//                    }
                    // setPracticeList(DataSourceDataProvider.getInstance().getPracticeByDepartment("Sales"));

                    setPracticeName(DataSourceDataProvider.getInstance().getPracticeIdByLoginId(loginId));

                    //  Retrieve the List of all the Team Members belonging to the Logged in User
                    String myTeamMembers = getKeys(getMyTeamMembers(), ",");

                    StringBuilder buf = new StringBuilder();

                    buf.delete(0, buf.length());

                    buf.append(" SELECT ");
                    buf.append("tblCrmAccount.Teritory");
                    buf.append(",tblCrmAccount.Status");
                    buf.append(",tblCrmAccount.Region");
                    buf.append(",count(tblCrmAccount.Id) as count");
                    buf.append(",tblCrmAccountTeam.TeamMemberId ");
                    buf.append(" FROM tblCrmAccount");
                    buf.append(" JOIN tblCrmAccountTeam on (tblCrmAccount.id = tblCrmAccountTeam.AccountId)");
                    buf.append(" WHERE ");
                    buf.append(" TeamMemberId IN" + "(" + myTeamMembers + ")");
                    buf.append(" GROUP BY");
                    buf.append(" tblCrmAccount.Teritory,tblCrmAccount.Status,tblCrmAccount.Region");
                    buf.append(" ORDER BY");
                    buf.append(" tblCrmAccount.Teritory,COUNT");
                    queryString = buf.toString();

                    // System.out.println("queryString------>"+queryString);
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_COUNT_DASHBOARDTERRITORY_LIST, queryString);

                    DateUtility date = DateUtility.getInstance();
                    String dueDate = date.sqlTimeStampTousTimeStamp(date.getCurrentMySqlDateTime().toString());
                    setDateWithOutTime(dueDate.substring(0, 10));

                    //  This Query is used for Collecting the Activities Summary for the Logged in
                    //  User Team Members


                    buf.delete(0, buf.length());
                    buf.append("SELECT  CreatedById, count(Id) as count  from tblCrmActivity");
                    buf.append(" WHERE CreatedById in ");
                    buf.append(" (" + myTeamMembers + ")");

                    //  If Both the Start and End Dates are provided by the End User then
                    if (getDashBoardStartDate() != null && getDashBoardEndDate() != null) {
                        buf.append(" and date(CreatedDate)  >='" + getDashBoardStartDate() + "'");
                        buf.append(" and  date(CreatedDate) <='" + getDashBoardEndDate() + "'");
                    } //  If only the Start Date is provided by the End User
                    else if (getDashBoardStartDate() != null) {

                        buf.append(" and DATE(CreatedDate) = '" + getDashBoardStartDate() + "'");
                    } //  If only the End Date is provided by the End User
                    else if (getDashBoardEndDate() != null) {

                        buf.append(" and DATE(CreatedDate) = '" + getDashBoardEndDate() + "'");
                    } //  If Both the Dates are not provided by the End User then use the Current Date
                    else {
                        buf.append(" and DATE(CreatedDate) = CURDATE() ");
                    }
                    buf.append(" GROUP BY CreatedById");
                    buf.append(" ORDER BY CreatedById");

                    activityQueryString = buf.toString();

                    // System.out.println("activityQueryString---->"+activityQueryString);
                    buf = null;

                    httpServletRequest.setAttribute(ApplicationConstants.QS_COUNT_DASHBOARDACTIVITY_LIST, activityQueryString);

                    resultType = SUCCESS;
                } catch (Exception ex) {
                    System.out.println("Ex-->"+ex.getMessage());
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

   /* public String getTeamActivitiesInfo() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_TEAM_ACTIVITIES_INFO", userRoleId)) {
                try {

                    String activityQueryString = null;
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    String inMembers = null;
                    String queryString = "";
                    StringBuilder buf = new StringBuilder();
                    DateUtility date = DateUtility.getInstance();
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    //Retrieving Teammembers Map from session and seting to MyTeamManager Map.
                    setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                    accountService = ServiceLocator.getAccountService();
                    if (loginId.equals("plokam")) {
                        setSalesTeamExceptAccountTeamMap(accountService.getAllSalesTeamExceptAccountTeam(getAccountTeamMap()));
                    } else {
                        setSalesTeamExceptAccountTeamMap(getMyTeamMembers());
                    }
                    accountService = ServiceLocator.getAccountService();
                    // setSalesTeamExceptAccountTeamMap(accountService.getAllSalesTeamExceptAccountTeam(getAccountTeamMap()));
                    // setSalesTeamExceptAccountTeamMap(getMyTeamMembers());
                    setActivityTypeList(hibernateDataProvider.getCrmActivityTypes(ApplicationConstants.CRM_ACTIVITY_TYPE_OPTIONS));
                    // Retrieve the List of all the Team Members belonging to the Logged in User
                    String myTeamMembers = getKeys(getMyTeamMembers(), ",");

                    if (getEmpId() != null && !"".equals(getEmpId())) {
                        checkId = myTeamMembers.indexOf(getEmpId());
                        // It returns a position index of a word within the string. if found. Otherwise it returns -1.
                    }
                    if (getActivityType() != null) {
                    }
                    //Setting Form Action
                    setFormAction("teamActivitiesInfo");
                    setActivitySummaryFlag(2);


                    //DateFormating with out Time
                    String dueDate = date.sqlTimeStampTousTimeStamp(date.getCurrentMySqlDateTime().toString());

                    //Setting date value to this Instance
                    setDateWithOutTime(dueDate.substring(0, 10));


                   
                    buf.append("SELECT ");
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
                    buf.append("((tblCrmActivity join tblCrmAccountTeam on ");
                    buf.append("(tblCrmAccountTeam.AccountId = tblCrmActivity.AccountId))");
                    buf.append(" join tblCrmAccount on ");
                    buf.append("(`tblCrmAccount`.`Id` = `tblCrmAccountTeam`.`AccountId`))");
                    buf.append(" WHERE 1=1 ");
                    
                    if (getActivityType() != null && !"".equals(getActivityType())) {
                        buf.append("  AND tblCrmActivity.ActivityType ='" + getActivityType() + "' ");
                    }
                    //  If Both the Start and End Dates are provided by the End User then

                    if (getDashBoardStartDate() != null && getDashBoardEndDate() != null) {

                        buf.append(" AND date(tblCrmActivity.CreatedDate) >='" + getDashBoardStartDate() + "'");
                        buf.append(" AND date(tblCrmActivity.CreatedDate) <='" + getDashBoardEndDate() + "'");

                        //  If only the Start Date is provided by the End User
                    } else if (getDashBoardStartDate() != null) {
                        buf.append(" AND date(tblCrmActivity.CreatedDate) >='" + getDashBoardStartDate() + "'");

                    } else if (getDashBoardEndDate() != null) {

                        buf.append(" AND date(tblCrmActivity.CreatedDate) <='" + getDashBoardEndDate() + "'");

                        // If both date are not provided by the End User then set Current Date
                    } else {
                        buf.append(" AND date(tblCrmActivity.CreatedDate) <= CURDATE() ");
                    }


                    //buf.append("AND tblCrmActivity.CreatedById LIKE '"+getEmpId()+"'");
                  //System.out.println("empId-->"+getEmpId());
                    if (checkId != 0) {
                        buf.append("AND tblCrmActivity.CreatedById LIKE '" + getEmpId() + "'");
                    }else {
                        buf.append("AND tblCrmActivity.CreatedById in ");
                    buf.append(" (" + myTeamMembers + ")");
                    }

                   
                    buf.append(" ORDER BY tblCrmActivity.CreatedById,");
                    buf.append(" tblCrmAccount.Name, ");
                    buf.append("tblCrmActivity.CreatedDate DESC LIMIT 250");
                    
                    activityQueryString = buf.toString();

                    buf = null;

                    httpServletRequest.setAttribute(ApplicationConstants.QS_TEAM_ACTIVITIES_LIST, activityQueryString);

                    setViewMemberSearch("view");

                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }*/
    
    
public String getTeamActivitiesInfo() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_TEAM_ACTIVITIES_INFO", userRoleId)) {
                try {

                    String activityQueryString = null;
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    String inMembers = null;
                    String queryString = "";
                    StringBuilder buf = new StringBuilder();
                    DateUtility date = DateUtility.getInstance();
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    //Retrieving Teammembers Map from session and seting to MyTeamManager Map.
                    setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                    accountService = ServiceLocator.getAccountService();
                    if (loginId.equals("plokam")) {
                        setSalesTeamExceptAccountTeamMap(accountService.getAllSalesTeamExceptAccountTeam(getAccountTeamMap()));
                    } else {
                        setSalesTeamExceptAccountTeamMap(getMyTeamMembers());
                    }
                    accountService = ServiceLocator.getAccountService();
                    // setSalesTeamExceptAccountTeamMap(accountService.getAllSalesTeamExceptAccountTeam(getAccountTeamMap()));
                    // setSalesTeamExceptAccountTeamMap(getMyTeamMembers());
                    setActivityTypeList(hibernateDataProvider.getCrmActivityTypes(ApplicationConstants.CRM_ACTIVITY_TYPE_OPTIONS));
                    // Retrieve the List of all the Team Members belonging to the Logged in User
                    String myTeamMembers = getKeys(getMyTeamMembers(), ",");

                    if (getEmpId() != null && !"".equals(getEmpId())) {
                        checkId = myTeamMembers.indexOf(getEmpId());
                        // It returns a position index of a word within the string. if found. Otherwise it returns -1.
                    }
                    if (getActivityType() != null) {
                    }
                    //Setting Form Action
                    setFormAction("teamActivitiesInfo");
                    setActivitySummaryFlag(2);


                    //DateFormating with out Time
                    String dueDate = date.sqlTimeStampTousTimeStamp(date.getCurrentMySqlDateTime().toString());

                    //Setting date value to this Instance
                    setDateWithOutTime(dueDate.substring(0, 10));


                   /* buf.append("SELECT DISTINCT ");
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
                    buf.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`))");
                    buf.append(" WHERE ");
                    buf.append("tblCrmActivity.CreatedById in ");
                    buf.append(" (" + myTeamMembers + ")");
                    if (getActivityType() != null && !"".equals(getActivityType())) {
                        buf.append("  AND tblCrmActivity.ActivityType ='" + getActivityType() + "' ");
                    }
                    //  If Both the Start and End Dates are provided by the End User then

                    if (getDashBoardStartDate() != null && getDashBoardEndDate() != null) {

                        buf.append(" AND date(tblCrmActivity.CreatedDate) >='" + getDashBoardStartDate() + "'");
                        buf.append(" AND date(tblCrmActivity.CreatedDate) <='" + getDashBoardEndDate() + "'");

                        //  If only the Start Date is provided by the End User
                    } else if (getDashBoardStartDate() != null) {
                        buf.append(" AND date(tblCrmActivity.CreatedDate) >='" + getDashBoardStartDate() + "'");

                    } else if (getDashBoardEndDate() != null) {

                        buf.append(" AND date(tblCrmActivity.CreatedDate) <='" + getDashBoardEndDate() + "'");

                        // If both date are not provided by the End User then set Current Date
                    } else {
                        buf.append(" AND date(tblCrmActivity.CreatedDate) <= CURDATE() ");
                    }


                    //buf.append("AND tblCrmActivity.CreatedById LIKE '"+getEmpId()+"'");
                    if (checkId != 0) {
                        buf.append("AND tblCrmActivity.CreatedById LIKE '" + getEmpId() + "'");
                    }

                    buf.append(" AND  tblCrmAddress.Country like '" + userWorkCountry + "' ");
                    buf.append(" ORDER BY tblCrmActivity.CreatedById,");
                    buf.append(" tblCrmAccount.Name, ");
                    buf.append("tblCrmActivity.CreatedDate DESC LIMIT 250");
                     */
                    buf.append("SELECT ");
                    buf.append(" DISTINCT tblCrmActivity.Id as ActivityId,");
                    buf.append("tblCrmAccount.Name,");
                    buf.append("tblCrmAccount.Id,");
                    
                    buf.append("tblCrmActivity.ActivityType,");
                    buf.append("tblCrmActivity.Description,");
                    buf.append("tblCrmActivity.DateDue,");
                    buf.append("DATE(tblCrmActivity.CreatedDate) AS CreatedDate,");
                    buf.append("tblCrmActivity.AssignedToId,");
                    buf.append("tblCrmActivity.CreatedById, ");
                    buf.append("tblCrmActivity.ContactId,");
                    buf.append("tblCrmActivity.OpportunityId,");
                    buf.append("tblCrmActivity.Comments,");
                    buf.append("tblCrmActivity.STATUS ");
                    
                    buf.append("FROM ");
                    buf.append("((tblCrmActivity join tblCrmAccountTeam on ");
                    buf.append("(tblCrmAccountTeam.AccountId = tblCrmActivity.AccountId))");
                    buf.append(" join tblCrmAccount on ");
                    buf.append("(`tblCrmAccount`.`Id` = `tblCrmAccountTeam`.`AccountId`))");
                    buf.append(" WHERE 1=1 ");
                    
                    if (getActivityType() != null && !"".equals(getActivityType())) {
                        buf.append("  AND tblCrmActivity.ActivityType ='" + getActivityType() + "' ");
                    }
                    //  If Both the Start and End Dates are provided by the End User then

                    if (getDashBoardStartDate() != null && getDashBoardEndDate() != null) {

                        buf.append(" AND date(tblCrmActivity.CreatedDate) >='" + getDashBoardStartDate() + "' ");
                        buf.append(" AND date(tblCrmActivity.CreatedDate) <='" + getDashBoardEndDate() + "' ");

                        //  If only the Start Date is provided by the End User
                    } else if (getDashBoardStartDate() != null) {
                        buf.append(" AND date(tblCrmActivity.CreatedDate) >='" + getDashBoardStartDate() + "' ");

                    } else if (getDashBoardEndDate() != null) {

                        buf.append(" AND date(tblCrmActivity.CreatedDate) <='" + getDashBoardEndDate() + "' ");

                        // If both date are not provided by the End User then set Current Date
                    } else {
                       //buf.append(" AND date(tblCrmActivity.CreatedDate) <= CURDATE() ");
                        buf.append(" AND tblCrmActivity.CreatedDate  BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() ");

                    }


                    //buf.append("AND tblCrmActivity.CreatedById LIKE '"+getEmpId()+"'");
                  //System.out.println("empId-->"+getEmpId());
                    if (checkId != 0) {
                        buf.append(" AND tblCrmActivity.CreatedById LIKE '" + getEmpId() + "' ");
                    }else {
                        buf.append(" AND tblCrmActivity.CreatedById in ");
                    buf.append(" (" + myTeamMembers + ")");
                    }

                   
                    buf.append(" ORDER BY tblCrmActivity.CreatedById,");
                    buf.append(" tblCrmAccount.Name, ");
                    buf.append(" tblCrmActivity.CreatedDate DESC LIMIT 250");
                    
                    activityQueryString = buf.toString();

                    buf = null;

                    httpServletRequest.setAttribute(ApplicationConstants.QS_TEAM_ACTIVITIES_LIST, activityQueryString);

                    setViewMemberSearch("view");

                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }

    /**
     * The prepare method used for getting all variables in the
     * select box and getting these values from a class called ApplicationConstants.
     * And these variables are getting initailised when we see the form
     * everytime because prepare method is also called everytime.
     *
     * @throws Exception
     *         If a required property cannot be accessed.
     *
     * @return The return type of this method is a String
     *         which depends on the action being performed.
     *
     */
    public String prepare() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_ACCOUNT", userRoleId)) {
                try {

                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();

                    accountService = ServiceLocator.getAccountService();

                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *
                     *  {@link com.mss.mirage.ApplicationConstants}
                     *
                     *  @return   The account type list  returned  depends on the CRM_ACCOUNT_TYPE_OPTION
                     */
                    setAccountTypeList(hibernateDataProvider.getCrmAccountType(ApplicationConstants.CRM_ACCOUNT_TYPE_OPTION));

                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *
                     *  {@link com.mss.mirage.ApplicationConstants}
                     *
                     *  @return   The status type list  returned  depends on the CRM_ACCOUNT_STATUS_OPTION
                     */
                    setAccountStatusList(hibernateDataProvider.getCrmAccountStatus(ApplicationConstants.CRM_ACCOUNT_STATUS_OPTION));

                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *
                     *  {@link com.mss.mirage.ApplicationConstants}
                     *
                     *  @return   The industry type list  returned  depends on the INDUSTRY_OPTION
                     */
                    setIndustryList(hibernateDataProvider.getIndustry(ApplicationConstants.INDUSTRY_OPTION));

                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *
                     *  {@link com.mss.mirage.ApplicationConstants}
                     *
                     *  @return   The region type list  returned  depends on the REGION_OPTIONS
                     */
                    /* setRegionList(hibernateDataProvider
                    .getRegion(ApplicationConstants.REGION_OPTIONS));*/
                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *
                     *  {@link com.mss.mirage.ApplicationConstants}
                     *
                     *  @return   The country type list  returned  depends on the COUNTRY_OPTIONS
                     */
                    setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));

                    //setStatesList(hibernateDataProvider.getDefaultStatesList(ApplicationConstants.USA_STATES));

                    //Retrieving Teammembers Map from session and seting to MyTeamManager Map
                    setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                    setVendorTeamMap(accountService.getAllVendorTeam(getAccountTeamMap()));
                    if (getId() != 0) {
                        setPrimaryTeamMember(accountService.getPrimaryTeamMember(getId()));
                        setAccountTeamMap(accountService.getAccountTeamByAccountId(getId(), getPrimaryTeamMember()));
                        setSalesTeamExceptAccountTeamMap(accountService.getAllSalesTeamExceptAccountTeam(getAccountTeamMap()));
                        setVendorTeamMap(accountService.getAllVendorTeam(getAccountTeamMap()));
                         setOpportunityStateList(DataSourceDataProvider.getInstance().getOpportunityStateList());
                         setOpportunityState("Active");
                        httpServletRequest.setAttribute("primaryTeamMember", getPrimaryTeamMember());
                    }

                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SUBMIT_FROM_ACC_LIST) != null) {
                        setSubmitFrom(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SUBMIT_FROM_ACC_LIST).toString());
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SUBMIT_FROM_ACC_LIST);
                    }

                    //Vendor
                    if ("Vendor".equalsIgnoreCase(userRoleName) || "Recruitment".equalsIgnoreCase(userRoleName)) {
                        setTitle("Vendor");
                    }


                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking

        return resultType;
    }

    /**
     * The Add method is used to store all the values submitted in the form to database.
     * Here all the values are being first initalized to a method addOrUpdateAccount from the
     * AccountService Class.
     * And from their the values are stored to the database.
     *
     * @throws Exception
     *         If the required Account value cannot be added to the database.
     *
     * @return The return type of this method is a String
     *         which depends on the action being completely
     *         performed or not.
     *
     */
    public String doAdd() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_ACCOUNT", userRoleId)) {
                try {
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {
                        dataSourceDataProvider = DataSourceDataProvider.getInstance();
                        int insertedRows = 0;
                        setAddressType("Primary Address");
                        setOperationType(ApplicationConstants.SP_ADD);

                        setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

                        setDateCreated(DateUtility.getInstance().getCurrentMySqlDateTime());

                        setActivityBY(getCreatedBy());

                        // setDateLastActivity(getDateCreated());
                        setDateLastActivity(DateUtility.getInstance().getCurrentMySqlDateTime());

                        accountService = ServiceLocator.getAccountService();



                        // Calling addOrUpdateAccount(AccountAction accountPojo) method to add account
                        if (!DataSourceDataProvider.getInstance().checkAccountNameExist(getAccountName().trim())) {
                            insertedRows = accountService.addOrUpdateAccount(this);
                            if (insertedRows == 1) {
                                resultType = SUCCESS;
                                setResultMessage("<font color=\"green\" size=\"1.5\">Account has been successfully inserted!</font>");
                            } else {
                                resultType = INPUT;
                                setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>");
                            }
                        } else {
                            resultType = INPUT;
                            setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Account already existed in Hubble!</font>");
                        }
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());

                        //Generating content for select fields
                        prepare();
                        setCurrentAccount(accountService.getAccountVTO(this));
                        setTerritoryList(dataSourceDataProvider.getTeritory(getCurrentAccount().getRegion()));


                        setStatesList(dataSourceDataProvider.getStates(getCurrentAccount().getCountry()));

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
        }//Close for session checking

        return resultType;
    }

    /**
     * The getAccount method is called to edit the values from each particular account.
     * Here editing means when grid gets selected on the form and each particular account values
     * are to be displayed so that it can be used to edit.
     *
     * @param
     *
     *
     * @throws Exception
     *         If a required Account value cannot be edited or not from the database.
     *
     * @return The return type of this method is a String
     *         which depends on whether all the values are successfully edited or not.
     *
     */
    public String getAccount() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());

            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_ACCOUNT", userRoleId)) {
                try {

                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();

                    accountService = ServiceLocator.getAccountService();

                    //Setting AccountVTO Object
                    setCurrentAccount(accountService.getAccount(getId(), userWorkCountry, userRoleName));
                    // System.out.println(getAccountName()+"getAccountName()--");
                    // System.out.println(getCurrentAccount()+"--getCurrentAccount()--");
                    // System.out.println(getCurrentAccount().getAccountName()+"--getCurrentAccount().getAccountName()--");
                    if ((getCurrentAccount().getAccountName() != null) && (!getCurrentAccount().getAccountName().equals(null))) {

                        //Setting SoftwareVTO Object
                        setCurrentSoftwaresVTO(accountService.getSoftwareDetails(getId()));

                        setAddressLine1(getCurrentAccount().getAddressLine1());
                        setAddressLine2(getCurrentAccount().getAddressLine2());
                        setCity(getCurrentAccount().getCity());
                        setMailStop(getCurrentAccount().getMailStop());
                        setState(getCurrentAccount().getState());
                        setCountry(getCurrentAccount().getCountry());
                        setZip(getCurrentAccount().getZip());
                        setRequirementstatus("Open");

                        httpServletRequest.setAttribute("currentAccountId", String.valueOf(getId()));

                        setTerritoryList(dataSourceDataProvider.getTeritory(getCurrentAccount().getRegion()));

                        setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));
                        setStatesList(dataSourceDataProvider.getStates(getCurrentAccount().getCountry()));

                        setId(currentAccount.getId());
                         setRequirementStatusList(dataSourceDataProvider.getRequirementStatusList());
                        //START: For Contact Search and Initial Query to populate Contacts associated to the account
                        String requestFrom = "";
                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST_FROM) != null) {
                            requestFrom = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST_FROM).toString();
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST_FROM);
                        }
                        if (!(("contactSearch".equalsIgnoreCase(requestFrom)) || "YES".equalsIgnoreCase(getIsRequestFromGrid()))) {

                            queryString = "SELECT  AccountId,Id,Salutation,Gender,LastName,FirstName,AliasName,OfficePhone,Email1,Title FROM tblCrmContact";
                            queryString = queryString + " WHERE AccountId =" + getCurrentAccount().getId() + " ORDER BY FirstName";

                            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST) != null) {
                                httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST);
                            }

                            httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST, queryString.toString());
                        }
                        //END: For Contact Search

                        // setStatesList(hibernateDataProvider.getDefaultStatesList(ApplicationConstants.USA_STATES));
                         setBackToFlag(backToFlag);
                        prepare();
                        if (getResultMessage() != null) {
                            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                        }
                        designationList = new ArrayList<String>();
                        designationList.add("Customer");
                        designationList.add("Consultant");
                        designationList.add("TeamLead");
                        designationList.add("Manager");
                        designationList.add("Director");
                        designationList.add("Operations");
                        httpServletRequest.getSession(false).setAttribute("designationList", designationList);
                        resultType = SUCCESS;
                    } else {
                        resultType = "accessFailed";
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

    /***
     *modified by:prasad kandregula
     *email:vkandregula@miraclesoft.com
     *method:doUpdateAccountTeam
     ***/
    public String doUpdateAccountTeam() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String ptmAfterChange = "";
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_UPDATE_ACCOUNT_TEAM", userRoleId)) {
                try {
                    //getting datasourcedataprovider
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    //Returns Employee login id
                    String loginEmpName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();

                    //getting primary team memberid
                    String primaryTeamMemberId = ServiceLocator.getAccountService().getPrimaryTeamMember(getId());
                    //getting account team map before cahnging
                    Map mapBeforeTeamChange = ServiceLocator.getAccountService().getAccountTeamByAccountId(getId(), primaryTeamMemberId);

                    Map mapAfterTeamChange = new HashMap();
                    //DateUtility.getInstance().getCurrentDate();
                    //new map to strore team after change
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {
                        String assignedTeamMembers[] = (String[]) accountTeamParameters.get("assignedTeamMembers");
                        String availableTeamMembers[] = (String[]) accountTeamParameters.get("availableTeamMembers");
                        if (assignedTeamMembers != null) {
                            for (int i = 0; i < assignedTeamMembers.length; i++) {
                                //System.out.println("Assigned Team Member : "+assignedTeamMembers[i]);
                                //logger.info(assignedTeamMembers[i]);
                                int Id = DataSourceDataProvider.getInstance().getEmpIdByLoginId(assignedTeamMembers[i]);
                                String EmpName = DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Id);
                                // mapAfterTeamChange.put(assignedTeamMembers[i],assignedTeamMembers[i]);
                                mapAfterTeamChange.put(assignedTeamMembers[i], EmpName);
                            }
                        }//comparing before chnage ,after change mpas
                        Set<String> valueBeforeChange = new HashSet<String>(mapBeforeTeamChange.keySet());
                        Set<String> valuesAfterUpdate = new HashSet<String>(mapAfterTeamChange.keySet());
                        boolean equal = valueBeforeChange.equals(valuesAfterUpdate);
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

                        int insertedRows = ServiceLocator.getAccountService().updateAccountTeamMembers(assignedTeamMembers, getId(), primaryTeamMemberId, userRoleId, httpServletRequest.getSession(false));

                        if (!httpServletRequest.getSession(false).getAttribute(ApplicationConstants.AFTER_UPDATEPRIMARYTEAM).toString().equals(primaryTeamMemberId)) {

                            // logger.info("Primary TeamMember ChangedTo :"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.AFTER_UPDATEPRIMARYTEAM).toString());
                            ptmAfterChange = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.AFTER_UPDATEPRIMARYTEAM).toString();
                        }

                        //logger.info("END");
                        if (insertedRows >= 1) {
//                            if (!equal || !httpServletRequest.getSession(false).getAttribute(ApplicationConstants.AFTER_UPDATEPRIMARYTEAM).toString().equals(primaryTeamMemberId)) {
//                                if (Properties.getProperty("Mail.Flag").equals("1")) {
//                                    MailManager.sendNotificationForAccountTeamChange(loginEmpName, getId(), primaryTeamMemberId, mapBeforeTeamChange, mapAfterTeamChange, ptmAfterChange);
//                                }
//                            }

                            resultType = SUCCESS;
                            setResultMessage("<font color=\"green\" size=\"1.5\">Team has been successfully Modified!</font>");

                        } else {
                            resultType = INPUT;
                            setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>");
                        }
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());

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

    /**
     * The doEdit method is called to update the values related to that particular account.
     *
     * @throws Exception
     *         If a required Account value cannot be updated to the database.
     *
     * @return The return type of this method is a String
     *         which depends on whether the values are successfully
     *         updated or not.
     *
     */
    public String doEdit() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_ACCOUNT", userRoleId)) {
                try {
                    if (!("dbGrid".equalsIgnoreCase(getSubmitFrom()))) {

                        hibernateDataProvider = HibernateDataProvider.getInstance();
                        dataSourceDataProvider = DataSourceDataProvider.getInstance();

                        accountService = ServiceLocator.getAccountService();
                        int updatedRows = 0;
                        setAddressType("Primary Address");
                        setOperationType(ApplicationConstants.SP_EDIT);
                        setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        setDateModified(DateUtility.getInstance().getCurrentMySqlDateTime());
                        setActivityBY(getCreatedBy());
                        //  setDateLastActivity(getDateCreated());
                        setDateLastActivity(DateUtility.getInstance().getCurrentMySqlDateTime());

                        // Calling addOrUpdateAccount(AccountAction accountBean) method to add account
                        updatedRows = accountService.addOrUpdateAccount(this);

//            if(updatedRows==1){
//                updatedRows = ServiceLocator.getAccountService().updateAccountTeamDetails(getId(),getAccountTeam());
//            }

                        if (updatedRows == 1) {
                            resultType = SUCCESS;
                            setResultMessage("<font color=\"green\" size=\"2.5\">Account has been successfully updated!</font>");
                        } else {
                            resultType = INPUT;
                            setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>");
                        }
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());


                      //  setCurrentAccount(accountService.getAccountVTO(this));


                     //   setTerritoryList(dataSourceDataProvider.getTeritory(getCurrentAccount().getRegion()));

                      //  setStatesList(dataSourceDataProvider.getStates(getCurrentAccount().getCountry()));
                      //  setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));

                        //Setting SoftwareVTO Object
                       // setCurrentSoftwaresVTO(accountService.getSoftwareDetails(getId()));
                        //Generating content for select fields
                      //  prepare();
                        
                        
                        setId(getId());
                        //   getAccount();
                    }//close for submitFrom checking
                    if (LOGIN.equals(resultType)) {
                        resultType = "dbGrid";
                    }

                    httpServletRequest.setAttribute("currentAccountId", String.valueOf(getId()));
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close for Sessing checking
        return resultType;
    }

    public String getAllAccounts() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_ALL_ACCOUNTS", userRoleId)) {
                try {
                    if (getSubmitFrom() == null) {

                        /* Vendor Specific Implementation
                         */
                        if ("Vendor".equalsIgnoreCase(userRoleName) || "Recruitment".equalsIgnoreCase(userRoleName)) {

                            queryStringBuffer = new StringBuffer();

                            queryStringBuffer.append("SELECT DISTINCT ");
                            queryStringBuffer.append("`tblCrmAccount`.`Id`    AS `Id`,");
                            queryStringBuffer.append("`tblCrmAccount`.`Name`    AS `Name`,");
                            queryStringBuffer.append("`tblCrmAccount`.`Status`   AS `Status`,");
                            queryStringBuffer.append("`tblCrmAccount`.`Industry`   AS `Industry`,");
                            queryStringBuffer.append("`tblCrmAccount`.`WebAddress`   AS `WebAddress`,");
                            queryStringBuffer.append("`tblCrmAccount`.`DateLastActivity`  AS `DateLastActivity`,");
                            queryStringBuffer.append("`tblCrmAccount`.`Region`   AS `Region`,");
                            queryStringBuffer.append("`tblCrmAccount`.`Teritory`   AS `Teritory`,");
                            queryStringBuffer.append("`tblCrmAccount`.`Phone`   AS `Phone`,");
                            queryStringBuffer.append("`tblCrmAddress`.`State`   AS `State`");

                            queryStringBuffer.append(" FROM ");
                            queryStringBuffer.append(" (`tblCrmAccount` left join `tblCrmAddress` on ");
                            queryStringBuffer.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`))");
                            queryStringBuffer.append(" WHERE ");
                            queryStringBuffer.append(" tblCrmAccount.Type in ('Vendor','1099')  AND  tblCrmAddress.Country like '" + userWorkCountry + "' ");
                            queryStringBuffer.append(" ORDER BY `tblCrmAccount`.`Name` ");


                            // left outer join `tblCrmAccountTeam`  on (`tblCrmAccountTeam`.`AccountId` = `tblCrmAccount`.`Id`)

                            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ALL_VEND_LIST) != null) {
                                httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ALL_VEND_LIST);
                            }

                            httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ALL_VEND_LIST, queryStringBuffer.toString());
                            queryString = null;

                            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION) != null) {
                                httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
                            }
                            httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION, "accountsListAll");

                            setTitle("Vendor");

                            //  System.out.println(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ALL_VEND_LIST));

                        } else {

                            queryStringBuffer = new StringBuffer();

                            queryStringBuffer.append("SELECT DISTINCT ");
                            queryStringBuffer.append("`tblCrmAccount`.`Id`    AS `Id`,");
                            queryStringBuffer.append("`tblCrmAccount`.`Name`    AS `Name`,");
                            queryStringBuffer.append("`tblCrmAccount`.`Status`   AS `Status`,");
                            queryStringBuffer.append("`tblCrmAccount`.`Industry`   AS `Industry`,");
                            queryStringBuffer.append("`tblCrmAccount`.`WebAddress`   AS `WebAddress`,");
                            queryStringBuffer.append("`tblCrmAccount`.`DateLastActivity`  AS `DateLastActivity`,");
                            queryStringBuffer.append("`tblCrmAccount`.`Region`   AS `Region`,");
                            queryStringBuffer.append("`tblCrmAccount`.`Teritory`   AS `Teritory`,");
                            queryStringBuffer.append("`tblCrmAccount`.`Phone`   AS `Phone`");
                            queryStringBuffer.append(" FROM ");
                            queryStringBuffer.append(" (`tblCrmAccount` left join `tblCrmAddress` on ");
                            queryStringBuffer.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`))");
                            queryStringBuffer.append(" WHERE ");

                            //System.out.println("UserRoleName---->"+userRoleName);

                            if ("Admin".equalsIgnoreCase(userRoleName)) {
                                queryStringBuffer.append(" tblCrmAddress.Country like '%' ");
                            } else {
                                queryStringBuffer.append(" tblCrmAddress.Country like '" + userWorkCountry + "' ");
                            }

                            queryStringBuffer.append(" ORDER BY `tblCrmAccount`.`Name` ");
                            // System.out.println("Query---->"+queryStringBuffer.toString());
                            httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ALL_ACC_LIST, queryStringBuffer.toString());

                            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST) != null) {
                                httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST);
                            }

                            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION) != null) {
                                httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
                            }
                            httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION, "accountsListAll");
                        }
                    }

                    prepare();
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking

        }
        return resultType;
    }

    public String getMyAccounts() {
        resultType = LOGIN;

        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
                userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                userLoginId = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
                userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                
                resultType = "accessFailed";
               // setOperator("=");
                if (AuthorizationManager.getInstance().isAuthorizedUser("GET_MY_ACCOUNTS", userRoleId)) {

                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH) != null) {
                        isRequestFromSearch = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH).toString();
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH);
                    } else {
                        isRequestFromSearch = "NO";
                    }

                    if (getSubmitFrom() == null && "NO".equalsIgnoreCase(isRequestFromSearch)) {
                        queryStringBuffer = new StringBuffer();

                        queryStringBuffer.append("SELECT DISTINCT ");
                        queryStringBuffer.append("`tblCrmAccount`.`Id`    AS `AccountId`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Name`    AS `AccountName`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Status`   AS `Status`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Industry`   AS `Industry`,");
                        queryStringBuffer.append("`tblCrmAccount`.`WebAddress`   AS `URL`,");
                        queryStringBuffer.append("`tblCrmAccount`.`DateLastActivity`  AS `DateLastActivity`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Region`   AS `Region`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Teritory`   AS `Teritory`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Phone`   AS `Phone`,");
                        queryStringBuffer.append("`tblCrmAddress`.`State`   AS `State`,");
                        queryStringBuffer.append("`tblCrmAccount`.`MainPriority`   AS `MainPriority`");
                        queryStringBuffer.append(" FROM ");
                        queryStringBuffer.append(" ((`tblCrmAccount` left join `tblCrmAccountTeam` on ");
                        queryStringBuffer.append("(`tblCrmAccountTeam`.`AccountId` = `tblCrmAccount`.`Id`))");
                        queryStringBuffer.append(" left join `tblCrmAddress` on ");
                        queryStringBuffer.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`))");
                        queryStringBuffer.append(" WHERE ");
                        queryStringBuffer.append("tblCrmAccountTeam.TeamMemberId ='" + userLoginId + "' ");

                        if ("Admin".equalsIgnoreCase(userRoleName)) {
                            queryStringBuffer.append(" AND tblCrmAddress.Country like '%' ");
                        } else {
                            queryStringBuffer.append(" AND  tblCrmAddress.Country like '" + userWorkCountry + "' ");
                        }

                        queryStringBuffer.append(" ORDER BY `tblCrmAccount`.`Name` ");

                        setAccountSearchBy("myAccounts");


                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION, "accountsListMy");


                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_OTHER_ACC_LIST) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_OTHER_ACC_LIST);
                        }

                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_OTHER_ACC_LIST, queryStringBuffer.toString());
                        queryStringBuffer = null;
                    }

                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST) != null) {
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST);
                    }

                    prepare();
                    resultType = SUCCESS;
                }
            }//END-Authorization Checking
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }//END-Session Checking
        return resultType;
    }

    public String getMyUnTouchedAccounts() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userLoginId = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
            userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();

            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_MY_ACCOUNTS", userRoleId)) {
                try {
                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH) != null) {
                        isRequestFromSearch = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH).toString();
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH);
                    } else {
                        isRequestFromSearch = "NO";
                    }

                    if (getSubmitFrom() == null && "NO".equalsIgnoreCase(isRequestFromSearch)) {

                        queryStringBuffer = new StringBuffer();
                        queryStringBuffer.append("SELECT DISTINCT ");
                        queryStringBuffer.append("`tblCrmAccount`.`Id`    AS `AccountId`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Name`    AS `AccountName`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Status`   AS `Status`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Industry`   AS `Industry`,");
                        queryStringBuffer.append("`tblCrmAccount`.`WebAddress`   AS `URL`,");
                        queryStringBuffer.append("`tblCrmAccount`.`DateLastActivity`  AS `DateLastActivity`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Region`   AS `Region`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Teritory`   AS `Teritory`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Phone`   AS `Phone`,");
                        queryStringBuffer.append("`tblCrmAddress`.`State`   AS `State`");
                        queryStringBuffer.append(" FROM ");
                        queryStringBuffer.append(" ((`tblCrmAccount` ");
                        queryStringBuffer.append(" left outer join `tblCrmAccountTeam`  on (`tblCrmAccountTeam`.`AccountId` = `tblCrmAccount`.`Id`))");
                        queryStringBuffer.append(" left outer join `tblCrmAddress` on ");
                        queryStringBuffer.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`))");
                        queryStringBuffer.append(" WHERE tblCrmAccountTeam.TeamMemberId ='" + userLoginId + "'");
                        queryStringBuffer.append(" AND  tblCrmAddress.Country like '" + userWorkCountry + "' ");
                        queryStringBuffer.append(" AND ");
                        queryStringBuffer.append(" tblCrmAccount.DateLastActivity < DATE_SUB('" + Date.valueOf(DateUtility.getInstance().getToDayDate()) + "',INTERVAL 30 DAY)");
                        queryStringBuffer.append(" ORDER BY `tblCrmAccount`.`Name` ");

                        setAccountSearchBy("myAccounts");


                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.UNTOUCH_SEARCH) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.UNTOUCH_SEARCH);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.UNTOUCH_SEARCH, "untouchedAccounts");


                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_OTHER_ACC_LIST) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_OTHER_ACC_LIST);
                        }

                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_OTHER_ACC_LIST, queryStringBuffer.toString());
                        queryStringBuffer = null;
                    }

                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST) != null) {
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST);
                    }
                    
                    
                    
                    
                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION) != null) {
                                httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
                            }
                            httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION, "untouchedAccounts");

                    

                    prepare();
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//END-Session Checking
        return resultType;

    }

    public String getMyUnTouchedTeamAccounts() {

        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userLoginId = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
            userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_MY_TEAM_ACCOUNTS", userRoleId)) {
                try {
                    prepare();
                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH) != null) {
                        isRequestFromSearch = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH).toString();
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH);
                    } else {
                        isRequestFromSearch = "NO";
                    }

                    if (getSubmitFrom() == null && "NO".equalsIgnoreCase(isRequestFromSearch)) {
                        queryStringBuffer = new StringBuffer();

                        queryStringBuffer.append("SELECT DISTINCT ");
                        queryStringBuffer.append("`tblCrmAccount`.`Id`    AS `AccountId`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Name`    AS `AccountName`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Status`   AS `Status`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Industry`   AS `Industry`,");
                        queryStringBuffer.append("`tblCrmAccount`.`WebAddress`   AS `URL`,");
                        queryStringBuffer.append("`tblCrmAccount`.`DateLastActivity`  AS `DateLastActivity`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Region`   AS `Region`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Teritory`   AS `Teritory`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Phone`   AS `Phone`,");
                        queryStringBuffer.append("`tblCrmAddress`.`State`   AS `State`");
                        queryStringBuffer.append(" FROM ");
                        queryStringBuffer.append(" ((`tblCrmAccount` ");
                        queryStringBuffer.append(" left outer join `tblCrmAccountTeam`  on (`tblCrmAccountTeam`.`AccountId` = `tblCrmAccount`.`Id`))");
                        queryStringBuffer.append(" left outer join `tblCrmAddress` on ");
                        queryStringBuffer.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`))");
                        queryStringBuffer.append(" WHERE tblCrmAccountTeam.TeamMemberId  IN");
                        queryStringBuffer.append("(");
                        queryStringBuffer.append(getKeys(getMyTeamMembers(), ","));
                        queryStringBuffer.append(")");
                        queryStringBuffer.append(" AND  tblCrmAddress.Country like '" + userWorkCountry + "' ");
                        queryStringBuffer.append(" AND ");
                        queryStringBuffer.append(" tblCrmAccount.DateLastActivity < DATE_SUB('" + Date.valueOf(DateUtility.getInstance().getToDayDate()) + "',INTERVAL 30 DAY)");
                        queryStringBuffer.append(" ORDER BY `tblCrmAccount`.`Name`");

                        setAccountSearchBy("myTeamAccounts");


                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.UNTOUCH_SEARCH) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.UNTOUCH_SEARCH);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.UNTOUCH_SEARCH, "untouchedTeamAccounts");


                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_OTHER_ACC_LIST) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_OTHER_ACC_LIST);
                        }

                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_OTHER_ACC_LIST, queryStringBuffer.toString());
                        queryStringBuffer = null;
                    }

                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST) != null) {
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST);
                    }
 if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION) != null) {
                                httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
                            }
                            httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION, "untouchedTeamAccounts");

                    prepare();
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//END-Session Checking
        return resultType;

    }

    public String getMyTeamAccounts() {
        resultType = LOGIN;
        String key = null;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();

            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_MY_TEAM_ACCOUNTS", userRoleId)) {
                try {
                    prepare();

                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH) != null) {
                        isRequestFromSearch = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH).toString();
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH);
                    } else {
                        isRequestFromSearch = "NO";
                    }

                    if (getSubmitFrom() == null && "NO".equalsIgnoreCase(isRequestFromSearch)) {

                        queryStringBuffer = new StringBuffer();

                        queryStringBuffer.append("SELECT DISTINCT ");
                        queryStringBuffer.append("`tblCrmAccount`.`Id`    AS `AccountId`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Name`    AS `AccountName`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Status`   AS `Status`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Industry`   AS `Industry`,");
                        queryStringBuffer.append("`tblCrmAccount`.`WebAddress`   AS `URL`,");
                        queryStringBuffer.append("`tblCrmAccount`.`DateLastActivity`  AS `DateLastActivity`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Region`   AS `Region`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Teritory`   AS `Teritory`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Phone`   AS `Phone`,");
                        queryStringBuffer.append("`tblCrmAddress`.`State`   AS `State`,");
                        queryStringBuffer.append("`tblCrmAccount`.`MainPriority`   AS `MainPriority`");
                        queryStringBuffer.append(" FROM ");
                        queryStringBuffer.append(" ((`tblCrmAccount` left join `tblCrmAccountTeam` on ");
                        queryStringBuffer.append("(`tblCrmAccountTeam`.`AccountId` = `tblCrmAccount`.`Id`))");
                        queryStringBuffer.append(" left join `tblCrmAddress` on ");
                        queryStringBuffer.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`))");
                        queryStringBuffer.append(" WHERE ");
                        queryStringBuffer.append("tblCrmAccountTeam.TeamMemberId IN ");
                        queryStringBuffer.append("(");
                        queryStringBuffer.append(getKeys(getMyTeamMembers(), ","));
                        queryStringBuffer.append(")");

                        if ("Admin".equalsIgnoreCase(userRoleName)) {
                            queryStringBuffer.append(" AND tblCrmAddress.Country like '%' ");
                        } else {
                            queryStringBuffer.append(" AND  tblCrmAddress.Country like '" + userWorkCountry + "' ");
                        }

                        queryStringBuffer.append(" ORDER BY `tblCrmAccount`.`Name` ");
                        // System.err.println("team accounts Query:"+queryStringBuffer);
                        setAccountSearchBy("myTeamAccounts");

                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION, "accountsListMyTeam");


                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_OTHER_ACC_LIST) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_OTHER_ACC_LIST);
                        }

                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_OTHER_ACC_LIST, queryStringBuffer.toString());
                        queryStringBuffer = null;
                    }
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//CLOSE Session Checking
        return resultType;
    }

//=====================================================================================
//  This method is used to loop through all the keys present in a Map Data Structure
//  and formulate them into a String of Keys seperated by a delimiter and return that
//  string of delimited values back to the caller
//=====================================================================================
    private String getKeys(Map map, String delimiter) {
        Iterator tempIterator = map.entrySet().iterator();
        String keys = "";
        String key = "";
        int cnt = 0;

        while (tempIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) tempIterator.next();
            key = entry.getKey().toString();
            entry = null;

            //  Add the Delimiter to the Keys Field for the Second Key onwards
            if (cnt > 0) {
                keys = keys + delimiter;
            }

            keys = keys + "'" + key + "'";
            cnt++;
        }
        tempIterator = null;
        return (keys);
    }

    /**
     * The doSearchOther method is used to search the Account related info.directly from the form
     * And these values are populated thru database using different search
     * criterias like: Status,AccountName,Region,Territory,Industry,Description,AccountType
     *
     * @throws Exception
     *          If any of the required property cannot be accessed.
     */
    public String doSearchOther() {

        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();

            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_ACC_SEARCH_OTHER", userRoleId)) {
                try {

                    if ("SearchOther".equalsIgnoreCase(getSubmitFrom())) {

                        queryStringBuffer = new StringBuffer();

                        queryStringBuffer.append("SELECT DISTINCT ");
                        queryStringBuffer.append("`tblCrmAccount`.`Id`    AS `AccountId`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Name`    AS `AccountName`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Status`   AS `Status`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Industry`   AS `Industry`,");
                        queryStringBuffer.append("`tblCrmAccount`.`WebAddress`   AS `URL`,");
                        queryStringBuffer.append("`tblCrmAccount`.`DateLastActivity`  AS `DateLastActivity`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Region`   AS `Region`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Teritory`   AS `Teritory`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Phone`   AS `Phone`,");
                        queryStringBuffer.append("`tblCrmAddress`.`State`   AS `State`, ");
                        queryStringBuffer.append("`tblCrmAccount`.`MainPriority`   AS `MainPriority` ");
                        queryStringBuffer.append(" FROM ");
                        queryStringBuffer.append(" `tblCrmAccount` left join `tblCrmAccountTeam` on ");
                        queryStringBuffer.append("(`tblCrmAccount`.`Id` =`tblCrmAccountTeam`.`AccountId`)");


                        hibernateDataProvider = HibernateDataProvider.getInstance();
                        dataSourceDataProvider = DataSourceDataProvider.getInstance();

                        //Retrieving Teammembers Map from session and seting to MyTeamManager Map
                        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                        int ref = 0;

                        if (null == getAccountName()) {
                            setAccountName("");
                        }
                        if (null == getRegion()) {
                            setRegion("");
                        }
                        if (null == getTerritory()) {
                            setTerritory("");
                        }
                        if (null == getIndustry()) {
                            setIndustry("");
                        }
                        if (null == getDescription()) {
                            setDescription("");
                        }
                        if (null == getStatus()) {
                            setStatus("");
                        }
                        if (null == getAccountType()) {
                            setAccountType("");
                        }
                        if (null == getViewType()) {
                            setViewType("");
                        }
                        if (null == getAccountTeam()) {
                            setAccountTeam("");
                        }
                        if (null == getTaxId()) {
                            setTaxId("");
                        }
                        if (null == getState()) {
                            setState("");
                        }
                        if (null == getZip()) {
                            setZip("");
                        }
                        if (null == getConatctFName()) {
                            setConatctFName("");
                        }
                        if (null == getConatctLName()) {
                            setConatctLName("");
                        }
                        if (null == getConatctAliasName()) {
                            setConatctAliasName("");
                        }
                        // if(null == getPractice())setPractice("");
                        //if(null   == getPriority1())setPriority1("");

                        if (null == getPhone()) {
                            setPhone("");
                        }
                        if (null == getCity()) {
                            setCity("");
                        }
	

                        if (!"".equals(getConatctFName()) || !"".equals(getConatctLName()) || !"".equals(getConatctAliasName())) {
                            queryStringBuffer.append(" LEFT JOIN `tblCrmContact` ON (`tblCrmAccount`.`Id` = `tblCrmContact`.`AccountId`) ");
                        }
                        queryStringBuffer.append(" left join `tblCrmAddress` on ");
                        queryStringBuffer.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`)");
                        queryStringBuffer.append(" left join `tblCrmAccountDetails`on ( tblCrmAccountDetails.AccountId = tblCrmAccount.Id)");

                        boolean andFlag = false;
                        if ((!"".equals(getAccountName()))
                                || (getRevenues() != 0)
                                
                                || (!"".equals(getRegion()))
                                || (!"".equals(getTerritory()))
                                || (!"".equals(getIndustry()))
                                || (!"".equals(getStatus()))
                                || (!"".equals(getDescription()))
                                || (!"".equals(getAccountType()))
                                || (!"".equals(getViewType()))
                                || (!"".equals(getTaxId()))
                                || (!"".equals(getState()))
                                || (!"".equals(getZip()))
                                || (!"".equals(getAccountTeam()))
                                || (getB2bPriority() != 0)
                                || (getBpmPriority() != 0)
                                || (getSapPriority() != 0)
                                || (getEcomPriority() != 0)
                                || (getQaPriority() != 0)
                                || ((isSap() || isMercator() || isMessageBroker() || isWps() || isGentran() || isCommerce() || isDataPower() || isIbmPortals()) == true)
                                || (!"".equals(getConatctFName())) || (!"".equals(getConatctLName())) || (!"".equals(getConatctAliasName()))
                                || (!"".equals(getPhone()))
                                 || (!"".equals(getCity()))) {
                            queryStringBuffer.append(" WHERE ");
                            ref = 1;
                        }

                        if( getRevenues() != 0  && !andFlag){
    queryStringBuffer.append(" Revenues "+getOperator()+" "+getRevenues());
    andFlag = true;
}else if(getRevenues() != 0 && andFlag){
    queryStringBuffer.append(" AND Revenues "+getOperator()+" "+getRevenues());
}

                        // boolean andFlag = false;

                        if (!"".equals(getAccountName())) {
                            // Check if there is no Wild Card of '*' or '%' then append a '*'
                            if ((getAccountName().indexOf("*") == -1) && (getAccountName().indexOf("%") == -1)) {
                                setAccountName(getAccountName() + "*");
                            }

                            //  Replace '*' in the account Name with '%' so that the SQL Like Clause will work properly
                            setAccountName(getAccountName().replace("*", "%"));

                        }

                        //  In order to decide if we need to add the AND Clause to the SQl Statement we check the
                        //  following criteria
                        if (!"".equals(getAccountName()) && !andFlag) {
                            queryStringBuffer.append(" Name LIKE '" + getAccountName() + "' ");
                            andFlag = true;
                        } else if (!"".equals(getAccountName()) && andFlag) {
                            queryStringBuffer.append(" AND Name LIKE '" + getAccountName() + "' ");
                        }


                        /*new changes */

                        if (!"".equals(getConatctFName()) || !"".equals(getConatctLName()) || !"".equals(getConatctAliasName())) {

                            //if("".equals(getConatctFName()))setConatctFName("%");
                            //if("".equals(getConatctLName()))setConatctLName("%");

                            if (!"".equals(getConatctFName()) && !andFlag) {
                                queryStringBuffer.append(" tblCrmContact.FirstName LIKE '" + getConatctFName() + "%' ");
                                andFlag = true;
                            } else if (!"".equals(getConatctFName()) && andFlag) {
                                queryStringBuffer.append(" AND tblCrmContact.FirstName LIKE '" + getConatctFName() + "%' ");
                            }

                            if (!"".equals(getConatctLName()) && !andFlag) {
                                queryStringBuffer.append(" tblCrmContact.LastName LIKE '" + getConatctLName() + "%' ");
                                andFlag = true;
                            } else if (!"".equals(getConatctLName()) && andFlag) {
                                queryStringBuffer.append(" AND tblCrmContact.LastName LIKE '" + getConatctLName() + "%' ");
                            }
                            if (!"".equals(getConatctAliasName()) && !andFlag) {
                                queryStringBuffer.append(" tblCrmContact.AliasName LIKE '" + getConatctAliasName() + "%' ");
                                andFlag = true;
                            } else if (!"".equals(getConatctAliasName()) && andFlag) {
                                queryStringBuffer.append(" AND tblCrmContact.AliasName LIKE '" + getConatctAliasName() + "%' ");
                            }
                        }

                        /**    tblCrmAccount`.`Phone` AS `Phone`
                         * New change added by Nag....
                         *03 Dec 2012
                         */
                        if (!"".equals(getPhone()) && !andFlag) {
                            queryStringBuffer.append(" tblCrmAccount.Phone LIKE '%" + getPhone() + "%' ");
                            andFlag = true;
                        } else if (!"".equals(getPhone()) && andFlag) {
                            queryStringBuffer.append(" AND tblCrmAccount.Phone LIKE '%" + getPhone() + "%' ");
                        }

                        /*  if(!"".equals(getPhone())){
                        queryStringBuffer.append(" and ");
                        queryStringBuffer.append("tblCrmAccount.Phone like '%"+getPhone()+"%'");
                        
                        }   */

                        if (!"".equals(getTaxId()) && !andFlag) {
                            queryStringBuffer.append(" DateOfPPARenewal= '" + DateUtility.getInstance().convertStringToMySQLDate(getTaxId()) + "' ");
                            andFlag = true;
                        } else if (!"".equals(getTaxId()) && andFlag) {
                            queryStringBuffer.append(" AND DateOfPPARenewal= '" + DateUtility.getInstance().convertStringToMySQLDate(getTaxId()) + "' ");
                        }

                        if ("".equals(getConatctFName()) && "".equals(getConatctLName())) {
                            if (!"".equals(getState()) && !andFlag) {
                                queryStringBuffer.append(" tblCrmAddress.State LIKE '" + getState() + "%' ");
                                andFlag = true;
                            } else if (!"".equals(getState()) && andFlag) {
                                queryStringBuffer.append(" AND tblCrmAddress.State LIKE '" + getState() + "%' ");
                            }

                            if (!"".equals(getZip()) && !andFlag) {
                                queryStringBuffer.append(" tblCrmAddress.Zip LIKE '" + getZip() + "%' ");
                                andFlag = true;
                            } else if (!"".equals(getZip()) && andFlag) {
                                queryStringBuffer.append(" AND tblCrmAddress.Zip LIKE '" + getZip() + "%' ");
                            }
                            if (!"".equals(getCity()) && !andFlag) {
                                queryStringBuffer.append(" tblCrmAddress.City LIKE '" + getCity() + "%' ");
                                andFlag = true;
                            } else if (!"".equals(getCity()) && andFlag) {
                                queryStringBuffer.append(" AND tblCrmAddress.City LIKE '" + getCity() + "%' ");
                            }

                        }
                        if (!"".equals(getRegion()) && !andFlag) {
                            queryStringBuffer.append(" Region LIKE '" + getRegion() + "%' ");
                            andFlag = true;
                        } else if (!"".equals(getRegion()) && andFlag) {
                            queryStringBuffer.append(" AND Region LIKE '" + getRegion() + "%' ");
                        }

                        if (!"".equals(getTerritory()) && !andFlag) {
                            queryStringBuffer.append(" Teritory LIKE '" + getTerritory() + "%' ");
                            andFlag = true;
                        } else if (!"".equals(getTerritory()) && andFlag) {
                            queryStringBuffer.append(" AND Teritory LIKE '" + getTerritory() + "%' ");
                        }

                        if (!"".equals(getIndustry()) && !andFlag) {
                            queryStringBuffer.append(" Industry LIKE '" + getIndustry() + "%' ");
                            andFlag = true;
                        } else if (!"".equals(getIndustry()) && andFlag) {
                            queryStringBuffer.append(" AND Industry LIKE '" + getIndustry() + "%' ");
                        }

                        if (!"".equals(getStatus()) && !andFlag) {
                            queryStringBuffer.append(" Status='" + getStatus() + "' ");
                            andFlag = true;
                        } else if (!"".equals(getStatus()) && andFlag) {
                            queryStringBuffer.append(" AND Status='" + getStatus() + "' ");
                        }

                        if (!"".equals(getDescription())) {
                            // Check if there is no Wild Card of '*' or '%' then append a '*'
                            if ((getDescription().indexOf("*") == -1) && (getDescription().indexOf("%") == -1)) {
                                setDescription(getDescription() + "*");
                            }

                            //  Replace '*' in the account Name with '%' so that the SQL Like Clause will work properly
                            setDescription(getDescription().replace("*", "%"));
                        }

                        if (!"".equals(getDescription()) && !andFlag) {
                            queryStringBuffer.append(" Description LIKE'" + getDescription() + "' ");
                            andFlag = true;
                        } else if (!"".equals(getDescription()) && !andFlag) {
                            queryStringBuffer.append(" AND Description LIKE'" + getDescription() + "' ");
                        }

                        if (!"".equals(getAccountType()) && !andFlag) {
                            queryStringBuffer.append(" Type ='" + getAccountType() + "' ");
                            andFlag = true;
                        } else if (!"".equals(getAccountType()) && andFlag) {
                            // queryStringBuffer.append("AND AccountType ='" + getAccountType() + "' ");
                            queryStringBuffer.append(" AND Type ='" + getAccountType() + "' ");
                        }
                        /*Added by santosh kola
                         * Date : 01/21/2014
                         */
                        //New Search fields satrt
                        if (getB2bPriority() != 0 && !andFlag) {
                            //queryStringBuffer.append("B2BPriority <= "+ getB2bPriority() + " ");
                            queryStringBuffer.append(" B2BPriority Between 1 and " + getB2bPriority() + " ");
                            andFlag = true;
                        } else if (getB2bPriority() != 0 && andFlag) {

                            queryStringBuffer.append(" AND B2BPriority Between 1 and " + getB2bPriority() + " ");
                        }
                        if (getBpmPriority() != 0 && !andFlag) {
                            queryStringBuffer.append(" BPMPriority Between 1 and " + getBpmPriority() + " ");
                            andFlag = true;
                        } else if (getBpmPriority() != 0 && andFlag) {

                            queryStringBuffer.append(" AND BPMPriority Between 1 and " + getBpmPriority() + " ");
                        }
                        if (getSapPriority() != 0 && !andFlag) {
                            queryStringBuffer.append(" SAPPriority Between 1 and " + getSapPriority() + " ");
                            andFlag = true;
                        } else if (getSapPriority() != 0 && andFlag) {

                            queryStringBuffer.append(" AND SAPPriority Between 1 and " + getSapPriority() + " ");
                        }

                        if (getEcomPriority() != 0 && !andFlag) {
                            queryStringBuffer.append(" ECOMPriority Between 1 and " + getEcomPriority() + " ");
                            andFlag = true;
                        } else if (getEcomPriority() != 0 && andFlag) {

                            queryStringBuffer.append(" AND ECOMPriority Between 1 and " + getEcomPriority() + " ");
                        }
                        if (getQaPriority() != 0 && !andFlag) {
                            queryStringBuffer.append(" QAPriority Between 1 and " + getQaPriority() + " ");
                            andFlag = true;
                        } else if (getQaPriority() != 0 && andFlag) {

                            queryStringBuffer.append(" AND QAPriority Between 1 and " + getQaPriority() + " ");
                        }
                        //New Search fields end


                        //kiran added
                        if ("".equals(getConatctFName()) && "".equals(getConatctLName())) {
                            if (isSap() == true && !andFlag) {
                                queryStringBuffer.append(" tblCrmAccountDetails.Sap = 1 ");
                                andFlag = true;
                            } else if (isSap() == true && andFlag) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.Sap = 1 ");
                            }

                            if (isMercator() == true && !andFlag) {
                                queryStringBuffer.append(" tblCrmAccountDetails.Mercator = 1 ");
                                andFlag = true;
                            } else if (isMercator() == true && andFlag) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.Mercator = 1 ");
                            }

                            if (isMessageBroker() == true && !andFlag) {
                                queryStringBuffer.append(" tblCrmAccountDetails.MessageBroker = 1 ");
                                andFlag = true;
                            } else if (isMessageBroker() == true && andFlag) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.MessageBroker = 1 ");
                            }

                            if (isWps() == true && !andFlag) {
                                queryStringBuffer.append(" tblCrmAccountDetails.Wps = 1 ");
                                andFlag = true;
                            } else if (isWps() == true && andFlag) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.Wps = 1 ");
                            }

                            if (isGentran() == true && !andFlag) {
                                queryStringBuffer.append(" tblCrmAccountDetails.Gentran = 1 ");
                                andFlag = true;
                            } else if (isGentran() == true && andFlag) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.Gentran = 1 ");
                            }

                            if (isCommerce() == true && !andFlag) {
                                queryStringBuffer.append(" tblCrmAccountDetails.Commerce = 1 ");
                                andFlag = true;
                            } else if (isCommerce() == true && andFlag) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.Commerce = 1 ");
                            }

                            if (isDataPower() == true && !andFlag) {
                                queryStringBuffer.append(" tblCrmAccountDetails.DataPower = 1 ");
                                andFlag = true;
                            } else if (isDataPower() == true && andFlag) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.DataPower = 1 ");
                            }

                            if (isIbmPortals() == true && !andFlag) {
                                queryStringBuffer.append(" tblCrmAccountDetails.IbmPortals = 1 ");
                                andFlag = true;
                            } else if (isIbmPortals() == true && andFlag) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.IbmPortals = 1 ");
                            }
                        }

                        if ("myAccounts".equals(getAccountSearchBy()) && !andFlag) {

                            // if(!"".equals(getAccountTeam())){
                            if (ref == 1) {
                                queryStringBuffer.append(" Type ='" + getAccountTeam() + "' ");
                            } else {

                                queryStringBuffer.append(" WHERE tblCrmAccountTeam.TeamMemberId ='"
                                        + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' ");

                            }
                            setAccountSearchAction(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION).toString());
                            andFlag = true;
                        } else if ("myAccounts".equals(getAccountSearchBy()) && andFlag) {
                            if (!"".equals(getAccountTeam())) {
                                queryStringBuffer.append(" AND tblCrmAccountTeam.TeamMemberId ='"
                                        + getAccountTeam() + "' ");
                            } else {
                                queryStringBuffer.append(" AND tblCrmAccountTeam.TeamMemberId ='"
                                        + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' ");
                            }
                            setAccountSearchAction(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION).toString());
                        } else if ("myTeamAccounts".equals(getAccountSearchBy()) && !andFlag) {
                            //if(!"".equals(getAccountTeam())){
                            if (ref == 1) {
                                queryStringBuffer.append(" tblCrmAccountTeam.TeamMemberId ='" + getAccountTeam() + "'");

                            } else {
                                queryStringBuffer.append(" WHERE tblCrmAccountTeam.TeamMemberId IN ");
                                // queryStringBuffer.append( " tblCrmAccountTeam.TeamMemberId IN ") ;
                                queryStringBuffer.append(" (");
                                queryStringBuffer.append(getKeys(getMyTeamMembers(), ","));
                                queryStringBuffer.append(" )");
                            }
                            setAccountSearchAction(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION).toString());
                            andFlag = true;
                        } else if ("myTeamAccounts".equals(getAccountSearchBy()) && andFlag) {
                            if (!"".equals(getAccountTeam())) {
                                queryStringBuffer.append(" AND tblCrmAccountTeam.TeamMemberId ='" + getAccountTeam() + "'");
                            } else {
                                queryStringBuffer.append(" AND tblCrmAccountTeam.TeamMemberId IN ");
                                queryStringBuffer.append(" (");
                                queryStringBuffer.append(getKeys(getMyTeamMembers(), ","));
                                queryStringBuffer.append(" )");

                            }
                            setAccountSearchAction(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION).toString());
                        }
                        //if(ref==0 || 
                        if ("".equals(getConatctFName()) || "".equals(getConatctLName())) {
                            if ("Admin".equalsIgnoreCase(userRoleName)) {
                                queryStringBuffer.append(" AND tblCrmAddress.Country like '%' ");
                            } else {
                                queryStringBuffer.append(" AND tblCrmAddress.Country like '" + userWorkCountry + "' ");
                            }
                        }


                        try {
                            if (null != getLastActivityFrom() && !"".equals(getLastActivityFrom())) {

                                queryStringBuffer.append(" and ");
                                queryStringBuffer.append(" DateLastActivity > " + "'" + DateUtility.getInstance().convertStringToMySQLDate(getLastActivityFrom()) + "' ");
                            }

                            if (null != getLastActivityTo() && !"".equals(getLastActivityTo())) {

                                queryStringBuffer.append(" and ");
                                queryStringBuffer.append(" DateLastActivity < " + "'" + DateUtility.getInstance().convertStringToMySQLDate(getLastActivityTo()) + "' ");
                            }
                        } catch (Exception eer) {
                        }




                        queryStringBuffer.append(" ORDER BY `tblCrmAccount`.`Name` ");

                        // System.err.println(" Query String --->"+queryStringBuffer.toString());
                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_OTHER_ACC_LIST) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_OTHER_ACC_LIST);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_OTHER_ACC_LIST, queryStringBuffer.toString());

                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH, "YES");
                    }


                    if ((getSubmitFrom() != null) || !"".equals(getSubmitFrom())) {
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SUBMIT_FROM_ACC_LIST, getSubmitFrom());
                    }


                    //prepare();
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking

        return resultType;
    }

    public String unTouchedSearch() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();

            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_ACC_SEARCH_OTHER", userRoleId)) {
                try {

                    if ("SearchOther".equalsIgnoreCase(getSubmitFrom())) {

                        queryStringBuffer = new StringBuffer();

                        queryStringBuffer.append("SELECT DISTINCT ");
                        queryStringBuffer.append("`tblCrmAccount`.`Id`    AS `AccountId`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Name`    AS `AccountName`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Status`   AS `Status`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Industry`   AS `Industry`,");
                        queryStringBuffer.append("`tblCrmAccount`.`WebAddress`   AS `URL`,");
                        queryStringBuffer.append("`tblCrmAccount`.`DateLastActivity`  AS `DateLastActivity`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Region`   AS `Region`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Teritory`   AS `Teritory`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Phone`   AS `Phone`,");
                        queryStringBuffer.append("`tblCrmAddress`.`State`   AS `State`");
                        queryStringBuffer.append(" FROM ");
                        queryStringBuffer.append(" ((`tblCrmAccount` left join `tblCrmAccountTeam` on ");
                        queryStringBuffer.append("(`tblCrmAccountTeam`.`AccountId` = `tblCrmAccount`.`Id`))");
                        queryStringBuffer.append(" left join `tblCrmAddress` on ");
                        queryStringBuffer.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`))");

                        hibernateDataProvider = HibernateDataProvider.getInstance();
                        dataSourceDataProvider = DataSourceDataProvider.getInstance();

                        //Retrieving Teammembers Map from session and seting to MyTeamManager Map
                        setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));

                        if (null == getAccountName()) {
                            setAccountName("");
                        }
                        if (null == getRegion()) {
                            setRegion("");
                        }
                        if (null == getTerritory()) {
                            setTerritory("");
                        }
                        if (null == getIndustry()) {
                            setIndustry("");
                        }
                        if (null == getDescription()) {
                            setDescription("");
                        }
                        if (null == getStatus()) {
                            setStatus("");
                        }
                        if (null == getAccountType()) {
                            setAccountType("");
                        }
                        if (null == getViewType()) {
                            setViewType("");
                        }
                        if (null == getAccountTeam()) {
                            setAccountTeam("");
                        }
                        if (null == getTaxId()) {
                            setTaxId("");
                        }
                        if (null == getState()) {
                            setState("");
                        } if (null == getCity()) {
                            setCity("");
                        }

                        if ((!"".equals(getAccountName()))
                                || (getRevenues() != 0)
                                || (!"".equals(getRegion()))
                                || (!"".equals(getTerritory()))
                                || (!"".equals(getIndustry()))
                                || (!"".equals(getStatus()))
                                || (!"".equals(getDescription()))
                                || (!"".equals(getAccountType()))
                                || (!"".equals(getViewType()))
                                || (!"".equals(getTaxId()))
                                || (!"".equals(getState()))
                                || (!"".equals(getAccountTeam()))
                                || (!"".equals(getCity()))
                                ) {
                            queryStringBuffer.append(" WHERE ");
                        }
                        boolean andFlag = false;
                        
                        if( getRevenues() != 0  && !andFlag){
    queryStringBuffer.append(" Revenues "+getOperator()+" "+getRevenues());
    andFlag = true;
}else if(getRevenues() != 0 && andFlag){
    queryStringBuffer.append(" AND Revenues "+getOperator()+" "+getRevenues());
}

                        if (!"".equals(getAccountName())) {
                            // Check if there is no Wild Card of '*' or '%' then append a '*'
                            if ((getAccountName().indexOf("*") == -1) && (getAccountName().indexOf("%") == -1)) {
                                setAccountName(getAccountName() + "*");
                            }

                            //  Replace '*' in the account Name with '%' so that the SQL Like Clause will work properly
                            setAccountName(getAccountName().replace("*", "%"));

                        }

                        //  In order to decide if we need to add the AND Clause to the SQl Statement we check the
                        //  following criteria
                        if (!"".equals(getAccountName()) && !andFlag) {
                            queryStringBuffer.append(" Name LIKE '" + getAccountName() + "' ");
                            andFlag = true;
                        } else if (!"".equals(getAccountName()) && andFlag) {
                            queryStringBuffer.append(" AND Name LIKE '" + getAccountName() + "' ");
                        }

                        if (!"".equals(getTaxId()) && !andFlag) {
                            queryStringBuffer.append(" DateOfPPARenewal= '" + DateUtility.getInstance().convertStringToMySQLDate(getTaxId()) + "' ");
                            andFlag = true;
                        } else if (!"".equals(getTaxId()) && andFlag) {
                            queryStringBuffer.append(" AND DateOfPPARenewal= '" + DateUtility.getInstance().convertStringToMySQLDate(getTaxId()) + "' ");
                        }

                        if (!"".equals(getState()) && !andFlag) {
                            queryStringBuffer.append(" tblCrmAddress.State LIKE '" + getState() + "%' ");
                            andFlag = true;
                        } else if (!"".equals(getState()) && andFlag) {
                            queryStringBuffer.append(" AND tblCrmAddress.State LIKE '" + getState() + "%' ");
                        }
                         if (!"".equals(getCity()) && !andFlag) {
                            queryStringBuffer.append(" tblCrmAddress.City LIKE '" + getCity() + "%' ");
                            andFlag = true;
                        } else if (!"".equals(getCity()) && andFlag) {
                            queryStringBuffer.append(" AND tblCrmAddress.City LIKE '" + getCity() + "%' ");
                        }
                         
                        if (!"".equals(getRegion()) && !andFlag) {
                            queryStringBuffer.append(" Region LIKE '" + getRegion() + "%' ");
                            andFlag = true;
                        } else if (!"".equals(getRegion()) && andFlag) {
                            queryStringBuffer.append(" AND Region LIKE '" + getRegion() + "%' ");
                        }

                        if (!"".equals(getTerritory()) && !andFlag) {
                            queryStringBuffer.append(" Teritory LIKE '" + getTerritory() + "%' ");
                            andFlag = true;
                        } else if (!"".equals(getTerritory()) && andFlag) {
                            queryStringBuffer.append(" AND Teritory LIKE '" + getTerritory() + "%' ");
                        }

                        if (!"".equals(getIndustry()) && !andFlag) {
                            queryStringBuffer.append(" Industry LIKE '" + getIndustry() + "%' ");
                            andFlag = true;
                        } else if (!"".equals(getIndustry()) && andFlag) {
                            queryStringBuffer.append(" AND Industry LIKE '" + getIndustry() + "%' ");
                        }

                        if (!"".equals(getStatus()) && !andFlag) {
                            queryStringBuffer.append(" Status='" + getStatus() + "' ");
                            andFlag = true;
                        } else if (!"".equals(getStatus()) && andFlag) {
                            queryStringBuffer.append(" AND Status='" + getStatus() + "' ");
                        }

                        if (!"".equals(getDescription())) {
                            // Check if there is no Wild Card of '*' or '%' then append a '*'
                            if ((getDescription().indexOf("*") == -1) && (getDescription().indexOf("%") == -1)) {
                                setDescription(getDescription() + "*");
                            }

                            //  Replace '*' in the account Name with '%' so that the SQL Like Clause will work properly
                            setDescription(getDescription().replace("*", "%"));
                        }

                        if (!"".equals(getDescription()) && !andFlag) {
                            queryStringBuffer.append(" Description LIKE'" + getDescription() + "' ");
                            andFlag = true;
                        } else if (!"".equals(getDescription()) && !andFlag) {
                            queryStringBuffer.append(" AND Description LIKE'" + getDescription() + "' ");
                        }

                        if (!"".equals(getAccountType()) && !andFlag) {
                            queryStringBuffer.append(" tblCrmAccount.Type ='" + getAccountType() + "' ");
                            andFlag = true;
                        } else if (!"".equals(getAccountType()) && andFlag) {
                            // queryStringBuffer.append("AND AccountType ='" + getAccountType() + "' ");
                            queryStringBuffer.append(" AND tblCrmAccount.Type ='" + getAccountType() + "' ");
                        }

                        if ("myAccounts".equals(getAccountSearchBy()) && !andFlag) {
                            if (!"".equals(getAccountTeam())) {
                                queryStringBuffer.append(" tblCrmAccount.Type ='" + getAccountTeam() + "' ");
                            } else {

                                queryStringBuffer.append(" WHERE tblCrmAccountTeam.TeamMemberId ='"
                                        + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' ");
                            }
//                             if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION) != null){
//                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
//                        }
                            setUnTouchedSearch(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.UNTOUCH_SEARCH).toString());
                            andFlag = true;
                        } else if ("myAccounts".equals(getAccountSearchBy()) && andFlag) {
                            if (!"".equals(getAccountTeam())) {
                                queryStringBuffer.append(" AND tblCrmAccountTeam.TeamMemberId ='"
                                        + getAccountTeam() + "' ");
                            } else {
                                queryStringBuffer.append(" AND tblCrmAccountTeam.TeamMemberId ='"
                                        + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) + "' ");
                            }
//                             if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION) != null){
//                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
//                        }
                            setUnTouchedSearch(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.UNTOUCH_SEARCH).toString());
                        } else if ("myTeamAccounts".equals(getAccountSearchBy()) && !andFlag) {
                            if (!"".equals(getAccountTeam())) {
                                queryStringBuffer.append(" tblCrmAccountTeam.TeamMemberId ='" + getAccountTeam() + "'");

                            } else {
                                queryStringBuffer.append(" WHERE tblCrmAccountTeam.TeamMemberId IN ");
                                queryStringBuffer.append(" (");
                                queryStringBuffer.append(getKeys(getMyTeamMembers(), ","));
                                queryStringBuffer.append(" )");
                            }
//                             if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION) != null){
//                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
//                        }
                            setUnTouchedSearch(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.UNTOUCH_SEARCH).toString());
                            andFlag = true;
                        } else if ("myTeamAccounts".equals(getAccountSearchBy()) && andFlag) {
                            if (!"".equals(getAccountTeam())) {
                                queryStringBuffer.append(" AND tblCrmAccountTeam.TeamMemberId ='" + getAccountTeam() + "'");
                            } else {
                                queryStringBuffer.append(" AND tblCrmAccountTeam.TeamMemberId IN ");
                                queryStringBuffer.append(" (");
                                queryStringBuffer.append(getKeys(getMyTeamMembers(), ","));
                                queryStringBuffer.append(" )");

                            }
//                             if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION) != null){
//                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
//                        }
                            setUnTouchedSearch(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.UNTOUCH_SEARCH).toString());
                        }

                        if ("Admin".equalsIgnoreCase(userRoleName)) {
                            queryStringBuffer.append(" AND tblCrmAddress.Country like '%' ");

                            queryStringBuffer.append("AND tblCrmAccount.DateLastActivity < DATE_SUB('" + Date.valueOf(DateUtility.getInstance().getToDayDate()) + "',INTERVAL 30 DAY)");

                        } else {
                            queryStringBuffer.append(" AND tblCrmAddress.Country like '" + userWorkCountry + "' ");
                            queryStringBuffer.append(" AND tblCrmAccount.DateLastActivity < DATE_SUB('" + Date.valueOf(DateUtility.getInstance().getToDayDate()) + "',INTERVAL 30 DAY)");
                        }

                        queryStringBuffer.append(" ORDER BY `tblCrmAccount`.`Name` ");

                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_OTHER_ACC_LIST) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_OTHER_ACC_LIST);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_OTHER_ACC_LIST, queryStringBuffer.toString());

                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_IS_REQ_FROM_SEARCH, "YES");
                    }


                    if ((getSubmitFrom() != null) || !"".equals(getSubmitFrom())) {
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SUBMIT_FROM_ACC_LIST, getSubmitFrom());
                    }


                    //prepare();
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking

        return resultType;


    }

    /**
     * The doSearchAll method is used to search the Account related info.directly from the form
     * And these values are populated thru database using different search
     * criterias like: Status,AccountName,Region,Territory,Industry,Description,AccountType
     *
     * @throws Exception
     *          If any of the required property cannot be accessed.
     */
    public String doSearchAll() {

        prepare();

        String tempName = null;
        String tempSate = null;
        String tempDesc = null;
        String tempTax = null;
        String tempZip = null;
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();

            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_ACC_SEARCH_ALL", userRoleId)) {
                try {


                    if ("SearchAll".equalsIgnoreCase(getSubmitFrom())) {
                        queryStringBuffer = new StringBuffer();

                        queryStringBuffer.append("SELECT DISTINCT ");
                        queryStringBuffer.append("`tblCrmAccount`.`Id`    AS `Id`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Name`    AS `Name`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Status`   AS `Status`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Industry`   AS `Industry`,");
                        queryStringBuffer.append("`tblCrmAccount`.`WebAddress`   AS `WebAddress`,");
                        queryStringBuffer.append("`tblCrmAccount`.`DateLastActivity`  AS `DateLastActivity`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Region`   AS `Region`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Teritory`   AS `Teritory`,");
                        queryStringBuffer.append("`tblCrmAccount`.`Phone`   AS `Phone`,");
                        queryStringBuffer.append("`tblCrmAddress`.`State`   AS `State`,");
                        queryStringBuffer.append("`tblCrmAccount`.`MainPriority`   AS `MainPriority`");
                        queryStringBuffer.append(" FROM ");


                        if (null == getAccountName()) {
                            setAccountName("");
                        }
                        if (null == getRegion()) {
                            setRegion("");
                        }
                        if (null == getTerritory()) {
                            setTerritory("");
                        }
                        if (null == getIndustry()) {
                            setIndustry("");
                        }
                        if (null == getDescription()) {
                            setDescription("");
                        }
                        if (null == getStatus()) {
                            setStatus("");
                        }
                        if (null == getAccountType()) {
                            setAccountType("");
                        }
                        if (null == getViewType()) {
                            setViewType("");
                        }
                        if (null == getTaxId()) {
                            setTaxId("");
                        }
                        if (null == getState()) {
                            setState("");
                        }
                        if (null == getZip()) {
                            setZip("");
                        }
                        if (null == getLastActivityFrom()) {
                            setLastActivityFrom("");
                        }
                        if (null == getLastActivityTo()) {
                            setLastActivityTo("");
                        }
                        if (null == getAccountTeam()) {
                            setAccountTeam("");
                        }
                        if (null == getConatctFName()) {
                            setConatctFName("");
                        }
                        if (null == getConatctLName()) {
                            setConatctLName("");
                        }
                        if (null == getConatctAliasName()) {
                            setConatctAliasName("");
                        }
                        if (null == getPhone()) {
                            setPhone("");
                        }
                        if (null == getCity()) {
                            setCity("");
                        }

                        if (userRoleName.equals("Vendor")) {
                            queryStringBuffer.append("tblCrmAddress LEFT JOIN `tblCrmAccount` on ");
                            queryStringBuffer.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`)");
                        } else if (!"".equals(getConatctFName()) || !"".equals(getConatctLName()) || !"".equals(getConatctAliasName())) {
                            queryStringBuffer.append(" `tblCrmAccount` left join `tblCrmAccountTeam` on ");
                            queryStringBuffer.append("(`tblCrmAccount`.`Id` =`tblCrmAccountTeam`.`AccountId`)");
                            queryStringBuffer.append(" LEFT JOIN `tblCrmContact` ON (`tblCrmAccount`.`Id` = `tblCrmContact`.`AccountId`) ");
                            queryStringBuffer.append(" left join `tblCrmAddress` on ");
                            queryStringBuffer.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`)");
                            queryStringBuffer.append(" left join `tblCrmAccountDetails`on ( tblCrmAccountDetails.AccountId = tblCrmAccount.Id)");
                        } else {
                            // System.out.println("In Other");
                            queryStringBuffer.append(" `tblCrmAccount` left join `tblCrmAccountTeam` on ");
                            queryStringBuffer.append("(`tblCrmAccount`.`Id` = `tblCrmAccountTeam`.`AccountId`)");
                            queryStringBuffer.append(" left join `tblCrmAddress` on ");
                            queryStringBuffer.append("(`tblCrmAddress`.`Id` = `tblCrmAccount`.`PrimaryAddressId`)");
                            queryStringBuffer.append(" left join `tblCrmAccountDetails`on ( tblCrmAccountDetails.AccountId = tblCrmAccount.Id) ");
                        }


                        int ref = 0;
                        if ((!"".equals(getAccountName()))
                                ||(getRevenues() != 0)
                                || (!"".equals(getRegion()))
                                || (!"".equals(getTerritory()))
                                || (!"".equals(getIndustry()))
                                || (!"".equals(getStatus()))
                                || (!"".equals(getDescription()))
                                || (!"".equals(getAccountType()))
                                || (!"".equals(getViewType()))
                                || (!"".equals(getTaxId()))
                                || (!"".equals(getState()))
                                || (!"".equals(getZip()))
                                || (!"".equals(getLastActivityFrom()))
                                || (!"".equals(getLastActivityTo()))
                                || (!"".equals(getAccountTeam()))
                                || ((isSap() || isMercator() || isMessageBroker() || isWps() || isGentran() || isCommerce() || isDataPower() || isIbmPortals()) == true)
                                || (!"".equals(getConatctFName())) || (!"".equals(getConatctLName())) || (!"".equals(getConatctAliasName()))
                                || (!"".equals(getPhone()))
                                || (getB2bPriority() != 0)
                                || (getBpmPriority() != 0)
                                || (getSapPriority() != 0)
                                || (getEcomPriority() != 0)
                                || (getQaPriority() != 0)
                                || (!"".equals(getCity()))) {
                            queryStringBuffer.append(" WHERE ");
                            ref = 1;
                        }

                        int columnCounter = 0;
                         if(getRevenues() != 0  && columnCounter == 0){
    queryStringBuffer.append(" Revenues "+getOperator()+" "+getRevenues());
    columnCounter++;
}else if( getRevenues() != 0 && columnCounter != 0){
    queryStringBuffer.append(" AND Revenues "+getOperator()+" "+getRevenues());
}
                        if (!"".equals(getAccountName()) && columnCounter == 0) {

                            tempName = getAccountName();
                            if ((getAccountName().indexOf("*") == -1) && (getAccountName().indexOf("%") == -1)) {
                                setAccountName(getAccountName() + "*");
                            }
                            setAccountName(getAccountName().replace("*", "%"));
                            queryStringBuffer.append(" Name LIKE '" + getAccountName() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getAccountName()) && columnCounter != 0) {
                            tempName = getAccountName();
                            if ((getAccountName().indexOf("*") == -1) && (getAccountName().indexOf("%") == -1)) {
                                setAccountName(getAccountName() + "*");
                            }
                            setAccountName(getAccountName().replace("*", "%"));
                            queryStringBuffer.append(" AND Name LIKE '" + getAccountName() + "' ");
                        }


                        //New changes
                        if (!"".equals(getConatctFName()) || !"".equals(getConatctLName()) || !"".equals(getConatctAliasName())) {

                            //if("".equals(getConatctFName()))setConatctFName("%");
                            //if("".equals(getConatctLName()))setConatctLName("%");

                            if (!"".equals(getConatctFName()) && columnCounter == 0) {
                                queryStringBuffer.append(" tblCrmContact.FirstName LIKE '" + getConatctFName() + "%' ");
                                columnCounter++;
                            } else if (!"".equals(getConatctFName()) && columnCounter != 0) {
                                queryStringBuffer.append(" AND tblCrmContact.FirstName LIKE '" + getConatctFName() + "%' ");
                            }

                            if (!"".equals(getConatctLName()) && columnCounter == 0) {
                                queryStringBuffer.append(" tblCrmContact.LastName LIKE '" + getConatctLName() + "%' ");
                                columnCounter++;
                            } else if (!"".equals(getConatctLName()) && columnCounter != 0) {
                                queryStringBuffer.append(" AND tblCrmContact.LastName LIKE '" + getConatctLName() + "%' ");
                            }
                            if (!"".equals(getConatctAliasName()) && columnCounter == 0) {
                                queryStringBuffer.append(" tblCrmContact.AliasName LIKE '" + getConatctAliasName() + "%' ");
                                columnCounter++;
                            } else if (!"".equals(getConatctAliasName()) && columnCounter != 0) {
                                queryStringBuffer.append(" AND tblCrmContact.AliasName LIKE '" + getConatctAliasName() + "%' ");
                            }
                        }

                        /**    tblCrmAccount`.`Phone` AS `Phone`
                         * New change added by Nag....
                         *03 Dec 2012
                         */
                        if (!"".equals(getPhone()) && columnCounter == 0) {
                            queryStringBuffer.append(" tblCrmAccount.Phone LIKE '%" + getPhone() + "%' ");
                            columnCounter++;
                        } else if (!"".equals(getPhone()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND tblCrmAccount.Phone LIKE '%" + getPhone() + "%' ");
                        }


                        /*   if(!"".equals(getPhone())){
                        queryStringBuffer.append(" and ");
                        queryStringBuffer.append("tblCrmAccount.Phone like '%"+getPhone()+"%'");      
                        }  */

                        if ("".equals(getConatctFName()) && "".equals(getConatctLName())) {
                            if (!"".equals(getState()) && columnCounter == 0) {
                                tempSate = getState();
                                if ((getState().indexOf("*") == -1) && (getState().indexOf("%") == -1)) {
                                    setState(getState() + "*");
                                }
                                setState(getState().replace("*", "%"));
                                queryStringBuffer.append(" tblCrmAddress.State LIKE '" + getState() + "' ");
                                columnCounter++;
                            } else if (!"".equals(getState()) && columnCounter != 0) {
                                tempSate = getState();
                                if ((getState().indexOf("*") == -1) && (getState().indexOf("%") == -1)) {
                                    setState(getState() + "*");
                                }
                                setState(getState().replace("*", "%"));
                                queryStringBuffer.append(" AND tblCrmAddress.State LIKE '" + getState() + "' ");
                            }
                            if (!"".equals(getZip()) && columnCounter == 0) {
                                tempZip = getZip();
                                if ((getZip().indexOf("*") == -1) && (getZip().indexOf("%") == -1)) {
                                    setZip(getZip() + "*");
                                }
                                setZip(getZip().replace("*", "%"));
                                queryStringBuffer.append(" tblCrmAddress.Zip LIKE '" + getZip() + "' ");
                                columnCounter++;
                            } else if (!"".equals(getZip()) && columnCounter != 0) {
                                tempZip = getZip();
                                if ((getZip().indexOf("*") == -1) && (getZip().indexOf("%") == -1)) {
                                    setZip(getZip() + "*");
                                }
                                setZip(getZip().replace("*", "%"));
                                queryStringBuffer.append(" AND tblCrmAddress.Zip LIKE '" + getZip() + "' ");
                            }
                            if (!"".equals(getCity()) && columnCounter == 0) {
                               
                                queryStringBuffer.append(" tblCrmAddress.City LIKE '" + getCity() + "%' ");
                                columnCounter++;
                            } else if (!"".equals(getCity()) && columnCounter != 0) {
                                 
                               
                                queryStringBuffer.append("AND tblCrmAddress.City LIKE '" + getCity() + "%' ");
                            }
                        }
                        // code change here
                        // code change here
                        if (!"".equals(getTaxId()) && columnCounter == 0) {
                            tempTax = getTaxId();
                            if ((getTaxId().indexOf("*") == -1) && (getTaxId().indexOf("%") == -1)) {
                                setTaxId(getTaxId() + "*");
                            }
                            setTaxId(getTaxId().replace("*", "%"));
                            queryStringBuffer.append(" DateOfPPARenewal like '" + DateUtility.getInstance().convertStringToMySQLDate(getTaxId()) + "' ");
                            columnCounter++;
                        } else if (!"".equals(getTaxId()) && columnCounter != 0) {
                            tempTax = getTaxId();
                            if ((getTaxId().indexOf("*") == -1) && (getTaxId().indexOf("%") == -1)) {
                                setTaxId(getTaxId() + "*");
                            }
                            setTaxId(getTaxId().replace("*", "%"));
                            queryStringBuffer.append(" AND DateOfPPARenewal like '" + DateUtility.getInstance().convertStringToMySQLDate(getTaxId()) + "' ");
                        }
                        // Code change here
                        // Code change here

                        if (!"".equals(getLastActivityFrom()) && columnCounter == 0) {
                            queryStringBuffer.append("  DateLastActivity  >= '" + DateUtility.getInstance().convertStringToMySQLDate(getLastActivityFrom()) + "' ");
                            columnCounter++;
                        } else if (!"".equals(getLastActivityFrom()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND DateLastActivity >='" + DateUtility.getInstance().convertStringToMySQLDate(getLastActivityFrom()) + "' ");
                        }

                        if (!"".equals(getLastActivityTo()) && columnCounter == 0) {
                            queryStringBuffer.append("  DateLastActivity <='" + DateUtility.getInstance().convertStringToMySQLDate(getLastActivityTo()) + "' ");
                        } else if (!"".equals(getLastActivityTo()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND DateLastActivity <='" + DateUtility.getInstance().convertStringToMySQLDate(getLastActivityTo()) + "' ");
                        }


                        if (!"".equals(getRegion()) && columnCounter == 0) {
                            queryStringBuffer.append(" Region LIKE '" + getRegion() + "%' ");
                            columnCounter++;
                        } else if (!"".equals(getRegion()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND Region LIKE '" + getRegion() + "%' ");
                        }

                        if (!"".equals(getTerritory()) && columnCounter == 0) {
                            queryStringBuffer.append(" Teritory LIKE '" + getTerritory() + "%' ");
                            columnCounter++;
                        } else if (!"".equals(getTerritory()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND Teritory LIKE '" + getTerritory() + "%' ");
                        }

                        if (!"".equals(getIndustry()) && columnCounter == 0) {
                            queryStringBuffer.append(" Industry LIKE '" + getIndustry() + "%' ");
                            columnCounter++;
                        } else if (!"".equals(getIndustry()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND Industry LIKE '" + getIndustry() + "%' ");
                        }

                        if (!"".equals(getStatus()) && columnCounter == 0) {
                            queryStringBuffer.append(" Status='" + getStatus() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getStatus()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND Status='" + getStatus() + "' ");
                        }

                        if (!"".equals(getDescription()) && columnCounter == 0) {
                            tempDesc = getDescription();
                            if ((getDescription().indexOf("*") == -1) && (getDescription().indexOf("%") == -1)) {
                                setDescription(getDescription() + "*");
                            }
                            setDescription(getDescription().replace("*", "%"));
                            queryStringBuffer.append(" Description LIKE'" + getDescription() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getDescription()) && columnCounter != 0) {
                            tempDesc = getDescription();
                            if ((getDescription().indexOf("*") == -1) && (getDescription().indexOf("%") == -1)) {
                                setDescription(getDescription() + "*");
                            }
                            setDescription(getDescription().replace("*", "%"));
                            queryStringBuffer.append(" AND Description LIKE'" + getDescription() + "' ");
                        }
                        //New Search fields satrt
                        /*Added by santosh kola
                         * DAte : 01/21/2014
                         * 
                         * 
                         */
                        if (getB2bPriority() != 0 && columnCounter == 0) {
                            queryStringBuffer.append(" B2BPriority Between 1 and " + getB2bPriority() + " ");
                            columnCounter++;
                        } else if (getB2bPriority() != 0 && columnCounter != 0) {

                            queryStringBuffer.append(" AND B2BPriority Between 1 and " + getB2bPriority() + " ");
                        }
                        if (getBpmPriority() != 0 && columnCounter == 0) {
                            queryStringBuffer.append(" BPMPriority Between 1 and " + getBpmPriority() + " ");
                            columnCounter++;
                        } else if (getBpmPriority() != 0 && columnCounter != 0) {

                            queryStringBuffer.append(" AND BPMPriority Between 1 and " + getBpmPriority() + " ");
                        }
                        if (getSapPriority() != 0 && columnCounter == 0) {
                            queryStringBuffer.append(" SAPPriority Between 1 and " + getSapPriority() + " ");
                            columnCounter++;
                        } else if (getSapPriority() != 0 && columnCounter != 0) {

                            queryStringBuffer.append(" AND SAPPriority Between 1 and " + getSapPriority() + " ");
                        }

                        if (getEcomPriority() != 0 && columnCounter == 0) {
                            queryStringBuffer.append(" ECOMPriority Between 1 and " + getEcomPriority() + " ");
                            columnCounter++;
                        } else if (getEcomPriority() != 0 && columnCounter != 0) {

                            queryStringBuffer.append(" AND ECOMPriority Between 1 and " + getEcomPriority() + " ");
                        }
                        if (getQaPriority() != 0 && columnCounter == 0) {
                            queryStringBuffer.append(" QAPriority Between 1 and " + getQaPriority() + " ");
                            columnCounter++;
                        } else if (getQaPriority() != 0 && columnCounter != 0) {

                            queryStringBuffer.append(" AND QAPriority Between 1 and " + getQaPriority() + " ");
                        }
                        //New Search fields end

                        if (!"".equals(getAccountType()) && columnCounter == 0) {
                            queryStringBuffer.append(" tblCrmAccount.Type ='" + getAccountType() + "' ");
                            columnCounter++;
                        } else if (!"".equals(getAccountType()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND tblCrmAccount.Type ='" + getAccountType() + "' ");
                        }

                        if (!"".equals(getAccountTeam()) && columnCounter == 0) {
                            queryStringBuffer.append(" `tblCrmAccount`.`Id` IN (SELECT AccountId FROM tblCrmAccountTeam WHERE TeamMemberId ='" + getAccountTeam() + "') ");
                            columnCounter++;
                        } else if (!"".equals(getAccountTeam()) && columnCounter != 0) {
                            queryStringBuffer.append(" AND `tblCrmAccount`.`Id` IN (SELECT AccountId FROM tblCrmAccountTeam WHERE TeamMemberId ='" + getAccountTeam() + "') ");
                        }


                        if ("".equals(getConatctFName()) && "".equals(getConatctLName())) {
                            if (isSap() == true && columnCounter == 0) {
                                queryStringBuffer.append(" tblCrmAccountDetails.Sap = 1 ");
                                columnCounter++;
                            } else if (isSap() == true && columnCounter != 0) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.Sap = 1 ");
                            }

                            if (isMercator() == true && columnCounter == 0) {
                                queryStringBuffer.append(" tblCrmAccountDetails.Mercator = 1 ");
                                columnCounter++;
                            } else if (isMercator() == true && columnCounter != 0) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.Mercator = 1 ");
                            }

                            if (isMessageBroker() == true && columnCounter == 0) {
                                queryStringBuffer.append(" tblCrmAccountDetails.MessageBroker = 1 ");
                                columnCounter++;
                            } else if (isMessageBroker() == true && columnCounter != 0) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.MessageBroker = 1 ");
                            }

                            if (isWps() == true && columnCounter == 0) {
                                queryStringBuffer.append(" tblCrmAccountDetails.Wps = 1 ");
                                columnCounter++;
                            } else if (isWps() == true && columnCounter != 0) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.Wps = 1 ");
                            }

                            if (isGentran() == true && columnCounter == 0) {
                                queryStringBuffer.append(" tblCrmAccountDetails.Gentran = 1 ");
                                columnCounter++;
                            } else if (isGentran() == true && columnCounter != 0) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.Gentran = 1 ");
                            }

                            if (isCommerce() == true && columnCounter == 0) {
                                queryStringBuffer.append(" tblCrmAccountDetails.Commerce = 1 ");
                                columnCounter++;
                            } else if (isCommerce() == true && columnCounter != 0) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.Commerce = 1 ");
                            }

                            if (isDataPower() == true && columnCounter == 0) {
                                queryStringBuffer.append(" tblCrmAccountDetails.DataPower = 1 ");
                                columnCounter++;
                            } else if (isDataPower() == true && columnCounter != 0) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.DataPower = 1 ");
                            }

                            if (isIbmPortals() == true && columnCounter == 0) {
                                queryStringBuffer.append(" tblCrmAccountDetails.IbmPortals = 1 ");
                                columnCounter++;
                            } else if (isIbmPortals() == true && columnCounter != 0) {
                                queryStringBuffer.append(" AND tblCrmAccountDetails.IbmPortals = 1 ");
                            }

                        }
                        // vendors


                        /* if("Admin".equalsIgnoreCase(userRoleName)){
                        queryStringBuffer.append("  ");
                        }else{
                        queryStringBuffer.append(" AND tblCrmAddress.Country like '"+userWorkCountry+"' ");
                        }*/

                        if ("Vendor".equalsIgnoreCase(userRoleName) || "Recruitment".equalsIgnoreCase(userRoleName)) {
                            setTitle("Vendor");

                            if (columnCounter == 0 || ref == 0) {
                                queryStringBuffer.append(" WHERE Type in ('Vendor','1099') ");
                                columnCounter++;
                            } else {
                                queryStringBuffer.append(" AND `tblCrmAccount`.`type` in ('Vendor','1099') ");
                            }
                        }

                        //System.out.println("doSearchAll----->"+userRoleName);

                        /* if("Admin".equalsIgnoreCase(userRoleName)){
                        if(columnCounter==0){
                        queryStringBuffer.append(" WHERE tblCrmAddress.Country like '%' ");
                        }else if(!(columnCounter==0)){
                        queryStringBuffer.append(" AND tblCrmAddress.Country like '"+userWorkCountry+"' ");
                        }
                        }else{
                        if(columnCounter==0){
                        queryStringBuffer.append(" WHERE tblCrmAddress.Country like '%' ");
                        }else if(!(columnCounter==0)){
                        queryStringBuffer.append(" AND tblCrmAddress.Country like '"+userWorkCountry+"' ");
                        }
                        }*/


                        queryStringBuffer.append(" ORDER BY `tblCrmAccount`.`Name` ");

                        //  System.out.println("doSearchAll Query----->"+queryStringBuffer.toString()+"columncounter"+columnCounter);

                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ALL_VEND_LIST) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ALL_VEND_LIST);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ALL_VEND_LIST, queryStringBuffer);

                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ALL_ACC_LIST) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ALL_ACC_LIST);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ALL_ACC_LIST, queryStringBuffer);

                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_ACC_SEARCH_ACTION, "accountsListAll");
                    }


                    if ((getSubmitFrom() != null) || !"".equals(getSubmitFrom())) {
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SUBMIT_FROM_ACC_LIST, getSubmitFrom());
                    }

                    setAccountName(tempName);
                    setState(tempSate);
                    setTaxId(tempTax);
                    setDescription(tempDesc);
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//END-Authorization Checking
        }//Close Session Checking

        // System.out.println("Vendor Query"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ALL_VEND_LIST));
        return resultType;


    }

//    public void initGridParams(){
//
//        if (null == getTxtSortCol() || "".equals(getTxtSortCol())) setTxtSortCol("AccountName");
//        if (null == getTxtSortAsc() || "".equals(getTxtSortAsc())) setTxtSortAsc("ASC");
//        // httpServletRequest.setAttribute("reqPage",Integer.valueOf(getReqPage()));
//        //httpServletRequest.setAttribute("txtSortCol",getTxtSortCol());
//    }
    /**
     * The acceptableParameterName method  is used
     *
     * @throws Exception
     *          If any of the required property cannot be accessed.
     */
    public boolean acceptableParameterName(String inputParamName) {
        boolean isParamAcceptable = true;
//        if(inputParamName.equals("primaryAddressId") ||
//                inputParamName.equals("id") ||
//                inputParamName.equals("accountTeam") ||
//                inputParamName.equals("accountType") ||
//                inputParamName.equals("accountName") ||
//                inputParamName.equals("synonyms") ||
//                inputParamName.equals("region") ||
//                inputParamName.equals("territory") ||
//                inputParamName.equals("industry") ||
//                inputParamName.equals("status") ||
//                inputParamName.equals("stockSymbol1") ||
//                inputParamName.equals("stockSymbol2") ||
//                inputParamName.equals("revenues") ||
//                inputParamName.equals("noOfEmployees") ||
//                inputParamName.equals("itBudget") ||
//                inputParamName.equals("taxId") ||
//                inputParamName.equals("leadSource") ||
//                inputParamName.equals("addressType") ||
//                inputParamName.equals("city") ||
//                inputParamName.equals("state") ||
//                inputParamName.equals("country") ||
//                inputParamName.equals("mailStop") ||
//                inputParamName.equals("zip") ||
//                inputParamName.equals("addressLine1") ||
//                inputParamName.equals("addressLine2") ||
//                inputParamName.equals("phone") ||
//                inputParamName.equals("fax") ||
//                inputParamName.equals("url") ||
//                inputParamName.equals("viewType") ||
//                inputParamName.equals("description")){
//            inputParamName.equals("txtCurr");
//            inputParamName.equals("txtSortCol");
//            inputParamName.equals("txtSortAsc");
//            isParamAcceptable = true;
//        }

        if (inputParamName.equals("txtCurr")
                || inputParamName.equals("txtSortCol")
                || inputParamName.equals("txtSortAsc")
                || inputParamName.equals("assignedTeamMembers")
                || inputParamName.equals("availableTeamMembers")
                || inputParamName.equals("txtAccActCurr")
                || inputParamName.equals("txtAllAccActCurr")
                || inputParamName.equals("txtAllAccActSortAsc")
                || inputParamName.equals("txtAllAccActSortCol")
                || inputParamName.equals("txtAttachCurr")
                || inputParamName.equals("txtContactCurr")
                || inputParamName.equals("txtGreenSheetCurr")
                || inputParamName.equals("txtNotesCurr")
                || inputParamName.equals("txtAccActSortAsc")
                || inputParamName.equals("txtAccActSortCol")
                || inputParamName.equals("txtProjectCurr")
                || inputParamName.equals("txtContactSortAsc")
                || inputParamName.equals("txtContactSortCol")
                || inputParamName.equals("txtOppCurr")
                || inputParamName.equals("searchBy")) {

            isParamAcceptable = false;
        }


        return isParamAcceptable;

    }

    //New on 10162012 for getAccounts for BDM
    public String getAccountsForBDM() {
        resultType = LOGIN;

        //Iterator iterator;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                userLoginId = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
                //  System.out.println("LoginId-->"+userLoginId);
                territory = DataSourceDataProvider.getInstance().getTeamNameByLoginId(userLoginId);

                //  System.out.println("territory-->"+territory.length());
                List east_List = dataSourceDataProvider.getInstance().getSubPracticeByPracticeId(1);
                List west_List = dataSourceDataProvider.getInstance().getSubPracticeByPracticeId(2);
                List central_List = dataSourceDataProvider.getInstance().getSubPracticeByPracticeId(3);

                int Contains_result = 0;
                if (east_List.contains(territory) || west_List.contains(territory) || central_List.contains(territory)) {
                    // System.out.println("in if");
                    if (east_List.contains(territory)) {
                        setRegion("East");
                    }
                    if (west_List.contains(territory)) {
                        setRegion("West");
                    }
                    if (central_List.contains(territory)) {
                        setRegion("Central");
                    }
                    Contains_result++;
                } else {
                    // System.out.println("in else");
                    // Contains_result++;
                }

                // if (territory.length()>0 && ){
                // if (territory.length()>0 && !territory.equals("All")){
                if (territory.length() > 0 && !territory.equals("All") && Contains_result == 1) {
                    // System.out.println("in if");
                    setTerritory(territory);

                    // setRegion(DataSourceDataProvider.getInstance().getPracticeIdByLoginId(userLoginId));
                    String stateList = DataSourceDataProvider.getInstance().getStatesByTeritory(territory);
                    String stateL[] = stateList.split(",");

                    if (!stateL[0].equals("null")) {
                        setState1(stateL[0]);
                    } else {
                        setState1(" ");
                    }

                    if (!stateL[1].equals("null")) {
                        setState2(stateL[1]);
                    } else {
                        setState2(" ");
                    }

                    if (!stateL[2].equals("null")) {
                        setState3(stateL[2]);
                    } else {
                        setState3(" ");
                    }

                    if (!stateL[3].equals("null")) {
                        setState4(stateL[3]);
                    } else {
                        setState4(" ");
                    }

                    if (!stateL[4].equals("null")) {
                        // System.out.println("in if1");
                        setState5(stateL[4]);
                    } else {
                        setState5(" ");
                    }
                    resultType = SUCCESS;
                } else {
                    setResultMessage("No Terrritory assigned to you in your profile");
                    httpServletRequest.getSession(false).setAttribute("errorMessage", getResultMessage());
                    resultType = ERROR;
                }
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }

        return resultType;
    }

    public String getAccountsContacts() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_TEAM_ACTIVITIES_INFO", userRoleId)) {
                try {

                    String activityQueryString = null;
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    String inMembers = null;
                    String queryString = "";
                    StringBuilder buf = new StringBuilder();
                    DateUtility date = DateUtility.getInstance();
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    //Retrieving Teammembers Map from session and seting to MyTeamManager Map.
                    setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                    accountService = ServiceLocator.getAccountService();
                    //  if (loginId.equals("plokam")) {
                    setSalesTeamExceptAccountTeamMap(accountService.getAllSalesTeamExceptAccountTeam(getAccountTeamMap()));
                    // } else {
                    //     setSalesTeamExceptAccountTeamMap(getMyTeamMembers());
                    // }
                    accountService = ServiceLocator.getAccountService();
                    setDashBoardStartDate(DateUtility.getInstance().strToTimeStampObj1(DateUtility.getInstance().getDateOfLastYear()));
                    setDashBoardEndDate(DateUtility.getInstance().strToTimeStampObj1(DateUtility.getInstance().getCurrentMySqlDate()));
                    List tempContactStatusList = hibernateDataProvider.getCrmContactStatus(ApplicationConstants.CRM_CONTACT_STATUS_OPTIONS);
                    tempContactStatusList.remove("Please Select");
                    setContactStatusList(tempContactStatusList);

                    // setSalesTeamExceptAccountTeamMap(accountService.getAllSalesTeamExceptAccountTeam(getAccountTeamMap()));
                    // setSalesTeamExceptAccountTeamMap(getMyTeamMembers());
                    // setActivityTypeList(hibernateDataProvider.getCrmActivityTypes(ApplicationConstants.CRM_ACTIVITY_TYPE_OPTIONS));
                    // Retrieve the List of all the Team Members belonging to the Logged in User
                    String myTeamMembers = getKeys(getMyTeamMembers(), ",");

                    if (getEmpId() != null) {
                        checkId = myTeamMembers.indexOf(getEmpId());
                        // It returns a position index of a word within the string. if found. Otherwise it returns -1.
                    }
                    if (getActivityType() != null) {
                    }
                    //Setting Form Action  teamActivitiesInfo
                    setFormAction("accountContactsSearch");


                    //DateFormating with out Time
                    String dueDate = date.sqlTimeStampTousTimeStamp(date.getCurrentMySqlDateTime().toString());

                    //Setting date value to this Instance
                    setDateWithOutTime(dueDate.substring(0, 10));

//queryString = "SELECT AccountId,Id,Salutation,Gender,LastName,FirstName,AliasName,OfficePhone,Email1,Title,iFlag,Designation,ContactStatus FROM tblCrmContact"
//                    + " WHERE AccountId =" + accId + " and ( FirstName like '" + name + "%' OR LastName like '" + name + "%' )"
//                    + "GROUP BY AccountId,Salutation,Gender,LastName,FirstName,AliasName,OfficePhone,Email1,Title"
//                    + " ORDER BY ContactStatus like 'Unsubscribe',ContactStatus='',ContactStatus like 'Deleted',ContactStatus like 'Terminated',ContactStatus like 'Inactive',ContactStatus like 'Active' ,trim(FirstName)";
//            
//                    
                    // setDashBoardStartDate(DateUtility.getInstance().getCurrentMySqlDate());
                    buf.append("SELECT AccountId, tblCrmContact.CreatedBy, tblCrmAccount.NAME AS AccountName,tblCrmContact.Id,tblCrmContact.Salutation,tblCrmContact.Gender,tblCrmContact.LastName,tblCrmContact.FirstName,tblCrmContact.AliasName,"
                            + "tblCrmContact.OfficePhone,tblCrmContact.Email1,tblCrmContact.Title,tblCrmContact.iFlag,tblCrmContact.Designation,tblCrmContact.ContactStatus,DATE(CreatedDate) AS createdDate,tblCrmAccount.Description FROM"
                            + " tblCrmContact  LEFT  JOIN tblCrmAccount ON  (tblCrmContact.AccountId=tblCrmAccount.Id) ");
                    buf.append(" WHERE (tblCrmContact.LastName IS NOT NULL AND tblCrmContact.FirstName IS NOT NULL AND tblCrmAccount.NAME IS NOT NULL) ");

                    buf.append(" AND tblCrmContact.CreatedBy in ");
                    buf.append(" (" + myTeamMembers + ")");

                    if (getDashBoardStartDate() != null && getDashBoardEndDate() != null) {

                        buf.append(" AND date(tblCrmContact.CreatedDate) >='" + getDashBoardStartDate() + "'");
                        buf.append(" AND date(tblCrmContact.CreatedDate) <='" + getDashBoardEndDate() + "'");

                        //  If only the Start Date is provided by the End User
                    }
                    // setDashBoardStartDate(dateWithOutTime.get);
                    buf.append("AND tblCrmContact.ContactStatus LIKE 'Active'");
                    buf.append("  LIMIT 250 ");
                    activityQueryString = buf.toString();
                    setContactStatus("Active");
                    buf = null;
                    // System.out.println("activityQueryString---"+activityQueryString);
                    httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, activityQueryString);

                    setViewMemberSearch("view");

                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getAccountContactsSearch() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userWorkCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_TEAM_ACTIVITIES_INFO", userRoleId)) {
                try {

                    String activityQueryString = null;
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    String inMembers = null;
                    String queryString = "";
                    StringBuilder buf = new StringBuilder();
                    DateUtility date = DateUtility.getInstance();
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    //Retrieving Teammembers Map from session and seting to MyTeamManager Map.
                    setMyTeamMembers((Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                    accountService = ServiceLocator.getAccountService();
                    //  if (loginId.equals("plokam")) {
                    setSalesTeamExceptAccountTeamMap(accountService.getAllSalesTeamExceptAccountTeam(getAccountTeamMap()));
                    //  } else {
                    //      setSalesTeamExceptAccountTeamMap(getMyTeamMembers());
                    // }
                    accountService = ServiceLocator.getAccountService();

                    List tempContactStatusList = hibernateDataProvider.getCrmContactStatus(ApplicationConstants.CRM_CONTACT_STATUS_OPTIONS);
                    tempContactStatusList.remove("Please Select");
                    setContactStatusList(tempContactStatusList);
                    // setSalesTeamExceptAccountTeamMap(accountService.getAllSalesTeamExceptAccountTeam(getAccountTeamMap()));
                    // setSalesTeamExceptAccountTeamMap(getMyTeamMembers());
                    setActivityTypeList(hibernateDataProvider.getCrmActivityTypes(ApplicationConstants.CRM_ACTIVITY_TYPE_OPTIONS));
                    // Retrieve the List of all the Team Members belonging to the Logged in User
                    String myTeamMembers = getKeys(getMyTeamMembers(), ",");

                    if (getEmpId() != null) {
                        checkId = myTeamMembers.indexOf(getEmpId());
                        // It returns a position index of a word within the string. if found. Otherwise it returns -1.
                    }

                    //Setting Form Action
                    setFormAction("accountContactsSearch");


                    //DateFormating with out Time
                    String dueDate = date.sqlTimeStampTousTimeStamp(date.getCurrentMySqlDateTime().toString());

                    //Setting date value to this Instance
                    setDateWithOutTime(dueDate.substring(0, 10));

                    buf.append("SELECT AccountId, tblCrmContact.CreatedBy, tblCrmAccount.NAME AS AccountName,tblCrmContact.Id,tblCrmContact.Salutation,tblCrmContact.Gender,tblCrmContact.LastName,tblCrmContact.FirstName,tblCrmContact.AliasName,"
                            + "tblCrmContact.OfficePhone,tblCrmContact.Email1,tblCrmContact.Title,tblCrmContact.iFlag,tblCrmContact.Designation,tblCrmContact.ContactStatus,DATE(CreatedDate) AS createdDate,tblCrmAccount.Description FROM"
                            + " tblCrmContact  LEFT  JOIN tblCrmAccount ON  (tblCrmContact.AccountId=tblCrmAccount.Id) ");
                    buf.append(" WHERE (tblCrmContact.LastName IS NOT NULL AND tblCrmContact.FirstName IS NOT NULL AND tblCrmAccount.NAME IS NOT NULL) ");
                    if ("".equals(getEmpId())) {
                        buf.append(" AND tblCrmContact.CreatedBy in ");
                        buf.append(" (" + myTeamMembers + ")");
                    }
                    //  If Both the Start and End Dates are provided by the End User then
                    if (getDashBoardStartDate() != null && getDashBoardEndDate() != null) {

                        buf.append(" AND date(tblCrmContact.CreatedDate) >='" + getDashBoardStartDate() + "'");
                        buf.append(" AND date(tblCrmContact.CreatedDate) <='" + getDashBoardEndDate() + "' ");

                        //  If only the Start Date is provided by the End User
                    }

                    //buf.append("AND tblCrmActivity.CreatedById LIKE '"+getEmpId()+"'");
                    if (checkId != 0) {
                        buf.append(" AND tblCrmContact.CreatedBy LIKE '" + getEmpId() + "'");
                    }

                    if (!"".equals(getContactStatus()) && getContactStatus() != null) {
                        buf.append(" AND tblCrmContact.ContactStatus LIKE '" + getContactStatus() + "'");
                    }
                    //buf.append(" GROUP BY AccountId,tblCrmContact.LastName,tblCrmContact.FirstName,tblCrmContact.OfficePhone,tblCrmContact.Email1,tblCrmContact.Title ORDER BY ContactStatus LIKE 'Unsubscribe',ContactStatus='',ContactStatus LIKE 'Deleted',ContactStatus LIKE 'Terminated',ContactStatus LIKE 'Inactive',ContactStatus LIKE 'Active' ,TRIM(FirstName) DESC LIMIT 250 ");
                    buf.append("  LIMIT 250 ");
//                    buf.append(" AND  tblCrmAddress.Country like '" + userWorkCountry + "' ");
//                    buf.append(" ORDER BY tblCrmActivity.CreatedById,");
//                    buf.append(" tblCrmAccount.Name, ");
//                    buf.append("tblCrmActivity.CreatedDate DESC LIMIT 250");

                    activityQueryString = buf.toString();

                    buf = null;

                    httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, activityQueryString);

                    setViewMemberSearch("view");

                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getBDMDashBoard() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                // setPracticeList(DataSourceDataProvider.getInstance().getCrmPracticeNamesList());

                Calendar now = Calendar.getInstance();
                setToYear(now.get(Calendar.YEAR));
                // month start from 0 to 11
                setToMonth(now.get(Calendar.MONTH) + 1);
                //  now.set(Calendar.YEAR, Calendar.YEAR-1);
                //now.set(Calendar.MONTH, -3);
                now.add(Calendar.MONTH, -12);
                setFromMonth(now.get(Calendar.MONTH) + 1);
                setFromYear(now.get(Calendar.YEAR));

                resultType = SUCCESS;
            } //Close Session Checking
            catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }//Close Session Checking
        return resultType;
    }
 
 public String excelUpload() {
       resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
           
                resultType=SUCCESS;
             

        }//Close Session Checking
        return resultType;
    }
 
 
 public String getSampleAccountExcel() throws ServiceLocatorException {
        
        resultType = INPUT;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            //userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String responseString = "";
            try {
                String fileLocation = "";
                //fileLocation = ServiceLocator.getMarketingService().getCampaignContactsExcel(httpServletRequest,getCampaignId());
                fileLocation = Properties.getProperty("SAMPLE.ADD.ACCOUNT.EXCEL");
                httpServletResponse.setContentType("application/force-download");

                
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
                


            } catch (FileNotFoundException ex) {
                try{
                    httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
                }catch(Exception e){
                    e.printStackTrace();
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
 
 
/*Client Request form methods
  * Author : Santosh kola
  * Date : 05/24/2016
  */
 /*
  public String clientReqEngagementSearch() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                
                
               // StringBuffer queryStringBuffer = new StringBuffer("SELECT tblPSCER.Id AS Id,AccountName AS CustomerName,tblPSCER.Stage AS State,MeetingType,MeetingDate,Comments,CreatedDate FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id WHERE tblPSCER.CreatedBy='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString()+"' AND RecordType=1 ");
                StringBuffer queryStringBuffer = new StringBuffer("SELECT tblPSCER.Id AS Id,AccountName AS CustomerName,tblPSCER.Stage AS State,IF(MeetingType = '' ,'N/A',MeetingType ) AS MeetingType,IF(MeetingDate IS NULL ,'N/A',DATE_FORMAT(MeetingDate,'%m/%d/%Y') ) AS MeetingDate,Comments,CreatedDate,RequestType FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id WHERE tblPSCER.CreatedBy='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString()+"' AND RecordType=1 ");
                
                if(getStartDate()!=null&&!"".equals(getStartDate()))
                    queryStringBuffer.append(" AND DATE(CreatedDate)>=DATE('"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"') ");
                
                if(getEndDate()!=null&&!"".equals(getEndDate()))
                    queryStringBuffer.append(" AND DATE(CreatedDate)<=DATE('"+DateUtility.getInstance().convertStringToMySQLDate(getEndDate())+"') ");
                  if(!"".equals(getRequestStage()) && getRequestStage()!=null){
                  queryStringBuffer.append(" AND  Stage='"+getRequestStage()+"'");
                 
                }
                 if(!"".equals(getRequestType()) && getRequestType()!=null){
                  queryStringBuffer.append(" AND  RequestType='"+getRequestType()+"'");
                 
                }
                
                queryStringBuffer.append(" ORDER BY CreatedDate DESC");
                httpServletRequest.setAttribute("crsQueryString", queryStringBuffer.toString());
                
             


                resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }*/
 /*Client Request form methods
  * Author : Teja Kadamanti
  * Date : 05/24/2016
  */
  public String clientReqEngagementSearch() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                
                 if(getBackToFlag().equalsIgnoreCase("Yes")){
                
                    setRequestType((String)httpServletRequest.getSession(false).getAttribute("tempRequestType"));
                setStartDate((String)httpServletRequest.getSession(false).getAttribute("tempStartDate"));
                setEndDate((String)httpServletRequest.getSession(false).getAttribute("tempEndDate"));
                setPgNo((Integer)httpServletRequest.getSession(false).getAttribute("temPgNo"));
                setState((String)httpServletRequest.getSession(false).getAttribute("tempState"));
                 
                }else{
                        setPgNo(pgNo);   
                     
                }
       


                resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }

 
 
 //clientReqEngagementAdd
 
 
  public String clientReqEngagementAdd() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                setCurrentAction("doClientReqEngagementAdd");
                setRequestStage("Creation");
              //getPresalesListByLoginId
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                 setStatesList(dataSourceDataProvider.getStates("USA"));
               // setPreSalesMap(dataSourceDataProvider.getPresalesListByLoginId());
                 
                 setRequestorId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                 setRequestorName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                 setRvpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_PRACTICE).toString());
                  setBdmMap(DataSourceDataProvider.getInstance().getAllBDMs());
                  setPracticeSalesMap(DataSourceDataProvider.getInstance().getEmployeeByDepartmentAndPractice("Sales","Practice"));
                resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }
 
   public String doClientReqEngagementAdd() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                
              //getPresalesListByLoginId
                
                
                
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                 setStatesList(dataSourceDataProvider.getStates("USA"));
                setPreSalesMap(dataSourceDataProvider.getPresalesListByLoginId());
                
                ServiceLocator.getAccountService().doClientReqEngagementAdd(this);
                if(getRequestId()>0){
                        setResultMessage("<font color='green' size='2px'>Request Created successfully.</font>");
                }else {
                        setResultMessage("<font color='red' size='2px'>Oops!please try again.</font>");
                }
                
                resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }
 //getClientEngagementDetails
   
   public String getClientEngagementDetails() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                
              //getPresalesListByLoginId
                 setBdmMap(DataSourceDataProvider.getInstance().getAllBDMs());
                setPracticeSalesMap(DataSourceDataProvider.getInstance().getEmployeeByDepartmentAndPractice("Sales","Practice"));
                setCurrentAction("doClientReqEngagementUpdate");
              //  setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                 setStatesList(dataSourceDataProvider.getStates("USA"));
                setPreSalesMap(dataSourceDataProvider.getPresalesListByLoginId());
                ServiceLocator.getAccountService().getClientEngagementDetails(this);
                if(getConsultantId()>0)
               setOpportunitiesMap(dataSourceDataProvider.getOppurtunitiesNames(getConsultantId()));
                
               
             /*  if(getRequestStage().equals("Approved")){
                   for(int i=0;i<getRequestResourcesList().size();i++){
                       getResourceMap().put(getRequestResourcesList().get(i).toString(), DataSourceDataProvider.getInstance().getFullNameBYLoginId(getRequestResourcesList().get(i).toString()));
                   }
               }*/
              //   if(getRequestStage().equals("Approved") || getRequestStage().equals("Reviewed")){
               
                   if(getRequestResourcesList().size()>0){
                   for(int i=0;i<getRequestResourcesList().size();i++){
                       getResourceMap().put(getRequestResourcesList().get(i).toString(), DataSourceDataProvider.getInstance().getFullNameBYLoginId(getRequestResourcesList().get(i).toString()));
                   }}
                   else{
                   
                   }
            //   }
               
               
               
                resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }
   /*
    public String doClientReqEngagementUpdate() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                
              //getPresalesListByLoginId
             
                setUserName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                 setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                  if("Sales".equals(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString())){
                 
                   setResourcesHidden(getResourcesHidden1());
                   
                  }
                 int updatedRows  =0;
                if(getPreRequestStage().equals(getRequestStage())){
                   
                    updatedRows  =ServiceLocator.getAccountService().doClientReqEngagementUpdate(this);
                }else {
                    //doClientReqEngagementDeleteByStatus
                    if("Rejected".equals(getPreRequestStage())&&"Submitted".equals(getRequestStage())){
                        ServiceLocator.getAccountService().doClientReqEngagementDeleteByStatus(getRequestId());
                        if(ServiceLocator.getAccountService().doClientReqEngagementUpdateByStatus(getRequestId(),getPreRequestStage(),getRequestStage())>0){
                            updatedRows  =ServiceLocator.getAccountService().doClientReqEngagementUpdate(this);
                        
                    }
                    }else {
                       
                        if(ServiceLocator.getAccountService().doClientReqEngagementUpdateByStatus(getRequestId(),getPreRequestStage(),getRequestStage())>0){
                            updatedRows  =ServiceLocator.getAccountService().doClientReqEngagementUpdate(this);
                        
                    }
                    }
                    
                        
                }
             
               
//                System.out.println("updatedRows-->"+updatedRows);
                 if(updatedRows>0){
                        setResultMessage("<font color='green' size='2px'>Request updated successfully.</font>");
                }else {
                        setResultMessage("<font color='red' size='2px'>Oops!please try again.</font>");
                }
                setRequestId(getRequestId());
                if("Sales".equals(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString()))
                resultType = SUCCESS;
                else
                    resultType = "esuccess";

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }
    
    */
    
  public String doClientReqEngagementUpdate() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                
              //getPresalesListByLoginId
             
                setUserName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                  
//                  if("Sales".equals(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString())){
//                   setResourcesHidden(getResourcesHidden1());
//                   
//                  }
                 int updatedRows  =0;
                if(getPreRequestStage().equals(getRequestStage())){
                   
                    updatedRows  =ServiceLocator.getAccountService().doClientReqEngagementUpdate(this);
                }else {
                    //doClientReqEngagementDeleteByStatus
                    if("Rejected".equals(getPreRequestStage())&&"Submitted".equals(getRequestStage())){
                        
                        ServiceLocator.getAccountService().doClientReqEngagementDeleteByStatus(getRequestId());
                        if(ServiceLocator.getAccountService().doClientReqEngagementUpdateByStatus(getRequestId(),getPreRequestStage(),getRequestStage())>0){
                            updatedRows  =ServiceLocator.getAccountService().doClientReqEngagementUpdate(this);
                        
                    }
                    }else {
                       
                        if(ServiceLocator.getAccountService().doClientReqEngagementUpdateByStatus(getRequestId(),getPreRequestStage(),getRequestStage())>0){
                            updatedRows  =ServiceLocator.getAccountService().doClientReqEngagementUpdate(this);
                        
                    }
                    }
                    
                        
                }
             
               
//                System.out.println("updatedRows-->"+updatedRows);
                 if(updatedRows>0){
                        setResultMessage("<font color='green' size='2px'>Request updated successfully.</font>");
                }else {
                        setResultMessage("<font color='red' size='2px'>Oops!please try again.</font>");
                }
                setRequestId(getRequestId());
                if("Sales".equals(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString()))
                resultType = SUCCESS;
                else
                    resultType = "esuccess";

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }
      
 
      
 //clientReqEngagementApprovalsSearch
      
      /*
        public String clientReqEngagementApprovalsSearch() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                
                
           //     StringBuffer queryStringBuffer = new StringBuffer("SELECT tblPSCER.Id AS Id,AccountName AS CustomerName,tblPSCER.Stage AS State,MeetingType,MeetingDate,Comments,CreatedDate,tblPSCER.RequestorId FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id WHERE Stage NOT IN('Creation','Rejected') AND RecordType=1 ");
                    StringBuffer queryStringBuffer = new StringBuffer("SELECT tblPSCER.Id AS Id,AccountName AS CustomerName,tblPSCER.Stage AS State,IF(MeetingType = '' ,'N/A',MeetingType ) AS MeetingType,IF(MeetingDate IS NULL ,'N/A',DATE_FORMAT(MeetingDate,'%m/%d/%Y') ) AS MeetingDate,Comments,CreatedDate,tblPSCER.RequestorId,tblPSCER.RequestType FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id WHERE Stage NOT IN('Creation') AND RecordType=1 ");
                
                if(getStartDate()!=null&&!"".equals(getStartDate()))
                    queryStringBuffer.append(" AND DATE(CreatedDate)>=DATE('"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"') ");
                
                if(getEndDate()!=null&&!"".equals(getEndDate()))
                    queryStringBuffer.append(" AND DATE(CreatedDate)<=DATE('"+DateUtility.getInstance().convertStringToMySQLDate(getEndDate())+"') ");
                if(!"".equals(getRequestStage()) && getRequestStage()!=null){
                  queryStringBuffer.append(" AND  Stage='"+getRequestStage()+"'");
                 
                }
//                  if(!"".equals(getMeetingType()) && getMeetingType()!=null){
//                  queryStringBuffer.append(" AND  MeetingType='"+getMeetingType()+"'");
//                 
//                }
                 if(!"".equals(getRequestType()) && getRequestType()!=null){
                  queryStringBuffer.append(" AND  tblPSCER.RequestType='"+getRequestType()+"'");
                 
                }
                queryStringBuffer.append(" ORDER BY CreatedDate DESC");
                httpServletRequest.setAttribute("crsQueryString", queryStringBuffer.toString());
                
               // System.out.println("queryStringBuffer.toString()...."+queryStringBuffer.toString());


                resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }
      */
  
   public String clientReqEngagementApprovalsSearch() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                
                
                 if(getBackToFlag().equalsIgnoreCase("Yes")){
                
                    setRequestType((String)httpServletRequest.getSession(false).getAttribute("tempRequestType"));
                setStartDate((String)httpServletRequest.getSession(false).getAttribute("tempStartDate"));
                setEndDate((String)httpServletRequest.getSession(false).getAttribute("tempEndDate"));
                setPgNo((Integer)httpServletRequest.getSession(false).getAttribute("temPgNo"));
                setState((String)httpServletRequest.getSession(false).getAttribute("tempState"));
                 
                }else{
                        setPgNo(pgNo);   
                     
                }
                
                
           resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }

      //getClientEngagementApprovalDetails
        
        
        /*   public String getClientEngagementApprovalDetails() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                
              //getPresalesListByLoginId
                
                
                setCurrentAction("doClientReqEngagementUpdate");
              //  setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                 setStatesList(dataSourceDataProvider.getStates("USA"));
                setPreSalesMap(dataSourceDataProvider.getPresalesListByLoginId());
                ServiceLocator.getAccountService().getClientEngagementDetails(this);
                if(getConsultantId()>0)
               setOpportunitiesMap(dataSourceDataProvider.getOppurtunitiesNames(getConsultantId()));
                 if(getRequestStage().equals("Approved")){
                   for(int i=0;i<getRequestResourcesList().size();i++){
                       getResourceMap().put(getRequestResourcesList().get(i).toString(), DataSourceDataProvider.getInstance().getFullNameBYLoginId(getRequestResourcesList().get(i).toString()));
                   }
               }
               
                resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }
           */
           public String getClientEngagementApprovalDetails() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                
              //getPresalesListByLoginId
                
                setPracticeSalesMap(DataSourceDataProvider.getInstance().getEmployeeByDepartmentAndPractice("Sales","Practice"));
                setCurrentAction("doClientReqEngagementUpdate");
              //  setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                 setStatesList(dataSourceDataProvider.getStates("USA"));
                setPreSalesMap(dataSourceDataProvider.getPresalesListByLoginId());
                ServiceLocator.getAccountService().getClientEngagementDetails(this);
                
                if(getConsultantId()>0)
               setOpportunitiesMap(dataSourceDataProvider.getOppurtunitiesNames(getConsultantId()));
//                 if(getRequestStage().equals("Approved")){
//                   for(int i=0;i<getRequestResourcesList().size();i++){
//                       getResourceMap().put(getRequestResourcesList().get(i).toString(), DataSourceDataProvider.getInstance().getFullNameBYLoginId(getRequestResourcesList().get(i).toString()));
//                   }
//               }
                 if(getRequestResourcesList().size()>0){
                   for(int i=0;i<getRequestResourcesList().size();i++){
                       getResourceMap().put(getRequestResourcesList().get(i).toString(), DataSourceDataProvider.getInstance().getFullNameBYLoginId(getRequestResourcesList().get(i).toString()));
                   }
                }
                 
                   setBdmMap(DataSourceDataProvider.getInstance().getAllBDMs());
               
                resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }
           
          /*  public String clientReqEngagementPreSalesSearch() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                
                String loginId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
             //   StringBuffer queryStringBuffer = new StringBuffer("SELECT tblPSCER.Id AS Id,AccountName AS CustomerName,tblPSCER.Stage AS State,MeetingType,MeetingDate,Comments,CreatedDate,tblPSCER.RequestorId FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id WHERE FIND_IN_SET('"+loginId+"',ResourceIds) > 0 AND RecordType=1 AND Stage='Approved' ");
                     StringBuffer queryStringBuffer = new StringBuffer("SELECT tblPSCER.Id AS Id,AccountName AS CustomerName,tblPSCER.Stage AS State,IF(MeetingType = '' ,'N/A',MeetingType ) AS MeetingType,IF(MeetingDate IS NULL ,'N/A',DATE_FORMAT(MeetingDate,'%m/%d/%Y') ) AS MeetingDate,Comments,CreatedDate,tblPSCER.RequestorId,tblPSCER.RequestType FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id WHERE FIND_IN_SET('"+loginId+"',ResourceIds) > 0 AND RecordType=1  ");
                
                
                if(getStartDate()!=null&&!"".equals(getStartDate()))
                    queryStringBuffer.append(" AND DATE(CreatedDate)>=DATE('"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"') ");
                
                if(getEndDate()!=null&&!"".equals(getEndDate()))
                    queryStringBuffer.append(" AND DATE(CreatedDate)<=DATE('"+DateUtility.getInstance().convertStringToMySQLDate(getEndDate())+"') ");
//                 
//                  if(!"".equals(getMeetingType()) && getMeetingType()!=null){
//                  queryStringBuffer.append(" AND  MeetingType='"+getMeetingType()+"'");
//                 
//                }
             if(!"".equals(getRequestType()) && getRequestType()!=null){
                  queryStringBuffer.append(" AND  RequestType='"+getRequestType()+"'");
                 
                }
                queryStringBuffer.append(" ORDER BY CreatedDate DESC");
                httpServletRequest.setAttribute("crsQueryString", queryStringBuffer.toString());
                
               // System.out.println("queryStringBuffer.toString()..."+queryStringBuffer.toString());


                resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }
            
            */
	 public String clientReqEngagementPreSalesSearch() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("CLIENT_ENGAGEMENT_REQUEST", userRoleId)) {
            try {
                 if(getBackToFlag().equalsIgnoreCase("Yes")){
                
                    setRequestType((String)httpServletRequest.getSession(false).getAttribute("tempRequestType"));
                setStartDate((String)httpServletRequest.getSession(false).getAttribute("tempStartDate"));
                setEndDate((String)httpServletRequest.getSession(false).getAttribute("tempEndDate"));
                setPgNo((Integer)httpServletRequest.getSession(false).getAttribute("temPgNo"));
                 
                }else{
                        setPgNo(pgNo);   
                     
                }
            

                resultType = SUCCESS;

            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            }
        }
        return resultType;
    }

//START:Pre Setters and Getters
    /** The getAccountTypeList is used to return all CRM_ACCOUNT_TYPE_OPTION
     */
    public List getAccountTypeList() {
        return accountTypeList;
    }

    /** The setAccountTypeList is used to set all CRM_ACCOUNT_TYPE_OPTION
     */
    public void setAccountTypeList(List accountTypeList) {
        this.accountTypeList = accountTypeList;
    }

    /** The getAccountStatusList is used to return all CRM_ACCOUNT_STATUS_OPTION
     */
    public List getAccountStatusList() {
        return accountStatusList;
    }

    /** The setAccountStatusList is used to set all CRM_ACCOUNT_STATUS_OPTION
     */
    public void setAccountStatusList(List accountStatusList) {
        this.accountStatusList = accountStatusList;
    }

    /** The getIndustryList is used to return all INDUSTRY_OPTION
     */
    public List getIndustryList() {
        return industryList;
    }

    /** The setIndustryList is used to set all INDUSTRY_OPTION
     */
    public void setIndustryList(List industryList) {
        this.industryList = industryList;
    }

    /** The getRegionList is used to return all REGION_OPTIONS
     */
    public List getRegionList() {
        return regionList;
    }

    /** The setRegionList is used to set all REGION_OPTIONS
     */
    public void setRegionList(List regionList) {
        this.regionList = regionList;
    }

    /** The getTerritoryList is used to return all TERRITORY_OPTIONS
     */
    public List getTerritoryList() {
        return territoryList;
    }

    /** The SetTerritoryList is used to set all TERRITORY_OPTIONS
     */
    public void setTerritoryList(List territoryList) {
        this.territoryList = territoryList;
    }

//END : Pre Setters and Getters
    /**
     * The method getCreatedBy is used to get value from login user-id
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * The method setCreatedBy is used to set value from login user-id
     */
    public void setCreatedBy(String createdBy) {
        if (createdBy != null) {
            createdBy = createdBy.trim();
        }
        this.createdBy = createdBy;
    }

    /**
     * The method getDateCreated is used to get date and time from the local system
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * The method setDateCreated is used to set date and time from the local system
     */
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * The method getId is used to get value from Accounts table in the database
     */
    public int getId() {
        return id;
    }

    /**
     * The method setId is used to set value from Accounts table in the database
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The method getAccountTeam is used to get values from the database
     */
    public String getAccountTeam() {
        return accountTeam;
    }

    /**
     * The method setAccountTeam is used to set values from the database
     */
    public void setAccountTeam(String accountTeam) {
        if (accountTeam != null) {
            accountTeam = accountTeam.trim();
        }
        this.accountTeam = accountTeam;
    }

    /**
     * The method getAccountType is used to get the type of Account
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * The method setAccountType is used to set the type of Account
     */
    public void setAccountType(String accountType) {
        if (accountType != null) {
            accountType = accountType.trim();
        }
        this.accountType = accountType;
    }

    /**
     * The method getAccountName is used to get the Account name
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * The method setAccountName is used to set the Account name
     */
    public void setAccountName(String accountName) {
        if (accountName != null) {
            accountName = accountName.trim();
        }
        this.accountName = accountName;
    }

    /**
     * The method getSynonyms is used to get the alias name of Account Name
     */
    public String getSynonyms() {
        return synonyms;
    }

    /**
     * The method setSynonyms is used to set the alias name of Account Name
     */
    public void setSynonyms(String synonyms) {
        if (synonyms != null) {
            synonyms = synonyms.trim();
        }
        this.synonyms = synonyms;
    }

    /**
     * The method getRegion is used to get the region name to which account belongs
     */
    public String getRegion() {
        return region;
    }

    /**
     * The method setRegion is used to set the region name to which account belongs
     */
    public void setRegion(String region) {
        if (region != null) {
            region = region.trim();
        }
        this.region = region;
    }

    /**
     * The method getTerritory is used to get the territory name to which account belongs
     */
    public String getTerritory() {
        return territory;
    }

    /**
     * The method setTerritory is used to set the territory name to which account belongs
     */
    public void setTerritory(String territory) {
        if (territory != null) {
            territory = territory.trim();
        }
        this.territory = territory;
    }

    /**
     * The method getIndustry is used to get the industry name to which account belongs
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * The method setIndustry is used to set the industry name to which account belongs
     */
    public void setIndustry(String industry) {
        if (industry != null) {
            industry = industry.trim();
        }
        this.industry = industry;
    }

    /**
     * The method getStatus is used to get the status of particular account
     */
    public String getStatus() {
        return status;
    }

    /**
     * The method setStatus is used to set the status of particular account
     */
    public void setStatus(String status) {
        if (status != null) {
            status = status.trim();
        }
        this.status = status;
    }

    /**
     * The method getStockSymbol1 is used to get the stocksymbol1 of particular account
     */
    public String getStockSymbol1() {
        return stockSymbol1;
    }

    /**
     * The method setStockSymbol1 is used to set the stocksymbol1 of particular account
     */
    public void setStockSymbol1(String stockSymbol1) {
        if (stockSymbol1 != null) {
            stockSymbol1 = stockSymbol1.trim();
        }
        this.stockSymbol1 = stockSymbol1;
    }

    /**
     * The method getStockSymbol2 is used to get the stocksymbol2 of particular account
     */
    public String getStockSymbol2() {
        return stockSymbol2;
    }

    /**
     * The method setStockSymbol2 is used to set the stocksymbol2 of particular account
     */
    public void setStockSymbol2(String stockSymbol2) {
        if (stockSymbol2 != null) {
            stockSymbol2 = stockSymbol2.trim();
        }
        this.stockSymbol2 = stockSymbol2;
    }

    /**
     * The method getRevenues is used to get the type of revenues for particular account
     */
    public double getRevenues() {
        return revenues;
    }

    /**
     * The method setRevenues is used to set the type of revenues for particular account
     */
    public void setRevenues(double revenues) {
        this.revenues = revenues;
    }

    /**
     * The method getNoOfEmployees is used to get the no. of employees in particular account
     */
    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    /**
     * The method SetNoOfEmployees is used to set the no. of employees in particular account
     */
    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    /**
     * The method getItBudget is used to get the itBudget for the account
     */
    public double getItBudget() {
        return itBudget;
    }

    /**
     * The method setItBudget is used to set the itBudget from the account
     */
    public void setItBudget(double itBudget) {
        this.itBudget = itBudget;
    }

    /**
     * The method getTaxId is used to get the TaxId for the account
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * The method setTaxId is used to set the TaxId for the account
     */
    public void setTaxId(String taxId) {
        if (taxId != null) {
            taxId = taxId.trim();
        }
        this.taxId = taxId;
    }

    /**
     * The method getLeadSource is used to get the lead source for the account
     */
    public String getLeadSource() {
        return leadSource;
    }

    /**
     * The method setLeadSource is used to set the lead source for the account
     */
    public void setLeadSource(String leadSource) {
        if (leadSource != null) {
            leadSource = leadSource.trim();
        }
        this.leadSource = leadSource;
    }

    /**
     * The method getCity is used to get the city for the account
     */
    public String getCity() {
        return city;
    }

    /**
     * The method setCity is used to set the city for the account
     */
    public void setCity(String city) {
        if (city != null) {
            city = city.trim();
        }
        this.city = city;
    }

    /**
     * The method getState is used to get the state for the account
     */
    public String getState() {
        return state;
    }

    /**
     * The method setState is used to set the state for the account
     */
    public void setState(String state) {
        if (state != null) {
            state = state.trim();
        }
        this.state = state;
    }

    /**
     * The method getCountry is used to get the country for the account
     */
    public String getCountry() {
        return country;
    }

    /**
     * The method setCountry is used to set the country for the account
     */
    public void setCountry(String country) {
        if (country != null) {
            country = country.trim();
        }
        this.country = country;
    }

    /**
     * The method getMailStop is used to get the mailstop for the account
     */
    public String getMailStop() {
        return mailStop;
    }

    /**
     * The method setMailStop is used to set the mailstop for the account
     */
    public void setMailStop(String mailStop) {
        if (mailStop != null) {
            mailStop = mailStop.trim();
        }
        this.mailStop = mailStop;
    }

    /**
     * The method getZip is used to get the zip for the account
     */
    public String getZip() {
        return zip;
    }

    /**
     * The method setZip is used to set the zip for the account
     */
    public void setZip(String zip) {
        if (zip != null) {
            zip = zip.trim();
        }
        this.zip = zip;
    }

    /**
     * The method getAddressLine1 is used to get the address line1 for the account
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * The method setAddressLine1 is used to set the address line1 for the account
     */
    public void setAddressLine1(String addressLine1) {
        if (addressLine1 != null) {
            addressLine1 = addressLine1.trim();
        }
        this.addressLine1 = addressLine1;
    }

    /**
     * The method getAddressLine2 is used to set the address line2 for the account
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * The method setAddressLine2 is used to set the address line2 for the account
     */
    public void setAddressLine2(String addressLine2) {
        if (addressLine2 != null) {
            addressLine2 = addressLine2.trim();
        }
        this.addressLine2 = addressLine2;
    }

    /**
     * The method getPhone is used to get the phone number for the account
     */
    public String getPhone() {
        return phone;
    }

    /**
     * The method setPhone is used to set the phone number for the account
     */
    public void setPhone(String phone) {
        if (phone != null) {
            phone = phone.trim();
        }
        this.phone = phone;
    }

    /**
     * The method getFax is used to get the Fax number for the account
     */
    public String getFax() {
        return fax;
    }

    /**
     * The method setFax is used to set the Fax number for the account
     */
    public void setFax(String fax) {
        if (fax != null) {
            fax = fax.trim();
        }
        this.fax = fax;
    }

    /**
     * The method getUrl is used to get the URL for the account
     */
    public String getUrl() {
        return url;
    }

    /**
     * The method setUrl is used to set the URL for the account
     */
    public void setUrl(String url) {
        if (url != null) {
            url = url.trim();
        }
        this.url = url;
    }

    /**
     * The method getDescription is used to get the Description for the account
     */
    public String getDescription() {
        return description;
    }

    /**
     * The method setDescription is used to set the Description for the account
     */
    public void setDescription(String description) {
        if (description != null) {
            description = description.trim();
        }
        this.description = description;
    }

    /**
     * The method setServletRequest is used to set the HttpServletRequest
     */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * The method getModifiedBy is used to get the login user-id for the account
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * The method setModifiedBy is used to set the login user-id for the account
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * The method getDateModified is used to get the modified date from the local-system for the account
     */
    public Timestamp getDateModified() {
        return dateModified;
    }

    /**
     * The method setDateModified is used to set the modified date from the local-system for the account
     */
    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    /**
     * The method getPrimaryAddressId is used to get the primary AddressId for the account
     */
    public int getPrimaryAddressId() {
        return primaryAddressId;
    }

    /**
     * The method setPrimaryAddressId is used to set the primary AddressId for the account
     */
    public void setPrimaryAddressId(int primaryAddressId) {
        this.primaryAddressId = primaryAddressId;
    }

    /**
     * The method getAddressType is used to get the AddressType for the account
     */
    public String getAddressType() {
        return addressType;
    }

    /**
     * The method setAddressType is used to set the AddressType for the account
     * whether it is primary address or some other
     */
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    /**
     * The method getOperationType is used to get the operation type for the account
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * The method setOperationType is used to set the operation type for the account
     * The type may be ADD or Update or Other
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    /**
     * The method getCurrentAccount is used to get the instance for AccountVTO
     */
    public AccountVTO getCurrentAccount() {
        return currentAccount;
    }

    /**
     * The method setCurrentAccount is used to set the instance for AccountVTO
     */
    public void setCurrentAccount(AccountVTO currentAccount) {
        this.currentAccount = currentAccount;
    }

    /**
     * The method getActivityBY is used to get the login user-id for the account
     */
    public String getActivityBY() {
        return activityBY;
    }

    /**
     * The method setActivityBY is used to set the login user-id for the account
     */
    public void setActivityBY(String activityBY) {
        this.activityBY = activityBY;
    }

    /**
     * The method getDateLastActivity is used to get the date and time for the account
     * from the local system
     */
    public Timestamp getDateLastActivity() {
        return dateLastActivity;
    }

    /**
     * The method setDateLastActivity is used to get the date and time for the account
     */
    public void setDateLastActivity(Timestamp dateLastActivity) {
        this.dateLastActivity = dateLastActivity;
    }

    /**
     * The method getCountryList is used to return all COUNTRY_OPTIONS
     */
    public List getCountryList() {
        return countryList;
    }

    /**
     * The method setCountryList is used to set all COUNTRY_OPTIONS
     */
    public void setCountryList(List countryList) {
        this.countryList = countryList;
    }

    /**
     * The method getStatesList is used to return all states list
     */
    public List getStatesList() {
        return statesList;
    }

    /**
     * The method setStatesList is used to set the states list
     */
    public void setStatesList(List statesList) {
        this.statesList = statesList;
    }

    /**
     * The method getViewType is used to get the viewtype means for all accounts
     * or for individual accounts
     */
    public String getViewType() {
        return viewType;
    }

    /**
     * The method setViewType is used to set the viewtype means for all accounts
     * or for individual accounts
     */
    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    /**
     * The method getAccountsList is used to get the Accounts list
     */
    public List getAccountsList() {
        return accountsList;
    }

//    public String getTxtSortCol() {
//        return txtSortCol;
//    }
//
//    public void setTxtSortCol(String txtSortCol) {
//        this.txtSortCol = txtSortCol;
//    }
//
//    public String getTxtSortAsc() {
//        return txtSortAsc;
//    }
//
//    public void setTxtSortAsc(String txtSortAsc) {
//        this.txtSortAsc = txtSortAsc;
//    }
//
//    public String getTxtCurr() {
//        return txtCurr;
//    }
//
//    public void setTxtCurr(String txtCurr) {
//        this.txtCurr = txtCurr;
//    }
    /**
     * The method getSubmitFrom is used to get the value from the form
     */
    public String getSubmitFrom() {
        return submitFrom;
    }

    /**
     * The method setSubmitFrom is used to set the value
     */
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }

    public String getAccountSearchBy() {
        return accountSearchBy;
    }

    public void setAccountSearchBy(String accountSearchBy) {
        this.accountSearchBy = accountSearchBy;
    }

    public String getAccountSearchAction() {
        return accountSearchAction;
    }

    public void setAccountSearchAction(String accountSearchAction) {
        this.accountSearchAction = accountSearchAction;
    }

    public Map getAccountTeamMap() {
        return accountTeamMap;
    }

    public void setAccountTeamMap(Map accountTeamMap) {
        this.accountTeamMap = accountTeamMap;
    }

    public Map getSalesTeamExceptAccountTeamMap() {
        return salesTeamExceptAccountTeamMap;
    }

    public void setSalesTeamExceptAccountTeamMap(Map salesTeamExceptAccountTeamMap) {
        this.salesTeamExceptAccountTeamMap = salesTeamExceptAccountTeamMap;
    }

    public void setParameters(Map map) {
        this.accountTeamParameters = map;

    }

    public String getPrimaryTeamMember() {
        return primaryTeamMember;
    }

    public void setPrimaryTeamMember(String primaryTeamMember) {
        this.primaryTeamMember = primaryTeamMember;
    }

    public String getIsRequestFromGrid() {
        return isRequestFromGrid;
    }

    public void setIsRequestFromGrid(String isRequestFromGrid) {
        this.isRequestFromGrid = isRequestFromGrid;
    }

    public SoftwareVTO getCurrentSoftwaresVTO() {
        return currentSoftwaresVTO;
    }

    public void setCurrentSoftwaresVTO(SoftwareVTO currentSoftwaresVTO) {
        this.currentSoftwaresVTO = currentSoftwaresVTO;
    }

    public String getFrmLoginId() {
        return frmLoginId;
    }

    public void setFrmLoginId(String frmLoginId) {
        this.frmLoginId = frmLoginId;
    }

    public String getToLoginId() {
        return toLoginId;
    }

    public void setToLoginId(String toLoginId) {
        this.toLoginId = toLoginId;
    }

    public String getAccountsListJspName() {
        return accountsListJspName;
    }

    public void setAccountsListJspName(String accountsListJspName) {
        this.accountsListJspName = accountsListJspName;
    }

    public String getDateWithOutTime() {
        return dateWithOutTime;
    }

    public void setDateWithOutTime(String dateWithOutTime) {
        this.dateWithOutTime = dateWithOutTime;
    }

    public String getFormAction() {
        return formAction;
    }

    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    public Map getMyTeamMembers() {
        return myTeamMembers;
    }

    public void setMyTeamMembers(Map myTeamMembers) {
        this.myTeamMembers = myTeamMembers;
    }

    public Date getDashBoardEndDate() {
        return dashBoardEndDate;
    }

    public void setDashBoardEndDate(Date dashBoardEndDate) {
        this.dashBoardEndDate = dashBoardEndDate;
    }

    public Date getDashBoardStartDate() {
        return dashBoardStartDate;
    }

    public void setDashBoardStartDate(Date dashBoardStartDate) {
        this.dashBoardStartDate = dashBoardStartDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getContactEmailId() {
        return contactEmailId;
    }

    public void setContactEmailId(String contactEmailId) {
        this.contactEmailId = contactEmailId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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

    public String getUnTouchedSearch() {
        return unTouchedSearch;
    }

    public void setUnTouchedSearch(String unTouchedSearch) {
        this.unTouchedSearch = unTouchedSearch;
    }

    public List getActivityTypeList() {
        return activityTypeList;
    }

    public void setActivityTypeList(List activityTypeList) {
        this.activityTypeList = activityTypeList;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getLastActivityFrom() {
        return lastActivityFrom;
    }

    public void setLastActivityFrom(String lastActivityFrom) {
        this.lastActivityFrom = lastActivityFrom;
    }

    public String getLastActivityTo() {
        return lastActivityTo;
    }

    public void setLastActivityTo(String lastActivityTo) {
        this.lastActivityTo = lastActivityTo;
    }

    public Map getVendorTeamMap() {
        return vendorTeamMap;
    }

    public void setVendorTeamMap(Map vendorTeamMap) {
        this.vendorTeamMap = vendorTeamMap;
    }

    public boolean isSap() {
        return sap;
    }

    public void setSap(boolean sap) {
        this.sap = sap;
    }

    public boolean isMercator() {
        return mercator;
    }

    public void setMercator(boolean mercator) {
        this.mercator = mercator;
    }

    public boolean isMessageBroker() {
        return messageBroker;
    }

    public void setMessageBroker(boolean messageBroker) {
        this.messageBroker = messageBroker;
    }

    public boolean isGentran() {
        return gentran;
    }

    public void setGentran(boolean gentran) {
        this.gentran = gentran;
    }

    public boolean isWps() {
        return wps;
    }

    public void setWps(boolean wps) {
        this.wps = wps;
    }

    public boolean isCommerce() {
        return commerce;
    }

    public void setCommerce(boolean commerce) {
        this.commerce = commerce;
    }

    public boolean isIbmPortals() {
        return ibmPortals;
    }

    public void setIbmPortals(boolean ibmPortals) {
        this.ibmPortals = ibmPortals;
    }

    public boolean isDataPower() {
        return dataPower;
    }

    public void setDataPower(boolean dataPower) {
        this.dataPower = dataPower;
    }



    public java.sql.Date getDateOfPPARenewal() {
        return dateOfPPARenewal;
    }

    public void setDateOfPPARenewal(java.sql.Date dateOfPPARenewal) {
        this.dateOfPPARenewal = dateOfPPARenewal;
    }

    /**
     * Setters and getters
     */
    public String getConatctFName() {
        return conatctFName;
    }

    public void setConatctFName(String conatctFName) {
        this.conatctFName = conatctFName;
    }

    public String getConatctLName() {
        return conatctLName;
    }

    public void setConatctLName(String conatctLName) {
        this.conatctLName = conatctLName;
    }

    //new on 10162012 for assign Accounts for BDM
    /**
     *setters and getters for states 1 - 5
     */
    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    public String getState3() {
        return state3;
    }

    public void setState3(String state3) {
        this.state3 = state3;
    }

    public String getState4() {
        return state4;
    }

    public void setState4(String state4) {
        this.state4 = state4;
    }

    public String getState5() {
        return state5;
    }

    public void setState5(String state5) {
        this.state5 = state5;
    }

    /**
     * setters and getters for subPracticeList;
     */
    public void setSubPracticeList(List subPracticeList) {
        this.subPracticeList = subPracticeList;
    }

    public List getSubPracticeList() {
        return subPracticeList;
    }

    public void setPracticeList(List practiceList) {
        this.practiceList = practiceList;
    }

    public List getPracticeList() {
        return practiceList;
    }

    public String getAccList() {
        return accList;
    }

    public void setAccList(String accList) {
        this.accList = accList;
    }

    /**
     * @return the practice
     */
    public String getPractice() {
        return practice;
    }

    /**
     * @param practice the practice to set
     */
    public void setPractice(String practice) {
        this.practice = practice;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return the priority1
     */
    public String getPriority1() {
        return priority1;
    }

    /**
     * @param priority1 the priority1 to set
     */
    public void setPriority1(String priority1) {
        this.priority1 = priority1;
    }

    /**
     * @return the b2bPriority
     */
    public int getB2bPriority() {
        return b2bPriority;
    }

    /**
     * @param b2bPriority the b2bPriority to set
     */
    public void setB2bPriority(int b2bPriority) {
        this.b2bPriority = b2bPriority;
    }

    /**
     * @return the bpmPriority
     */
    public int getBpmPriority() {
        return bpmPriority;
    }

    /**
     * @param bpmPriority the bpmPriority to set
     */
    public void setBpmPriority(int bpmPriority) {
        this.bpmPriority = bpmPriority;
    }

    /**
     * @return the sapPriority
     */
    public int getSapPriority() {
        return sapPriority;
    }

    /**
     * @param sapPriority the sapPriority to set
     */
    public void setSapPriority(int sapPriority) {
        this.sapPriority = sapPriority;
    }

    /**
     * @return the ecomPriority
     */
    public int getEcomPriority() {
        return ecomPriority;
    }

    /**
     * @param ecomPriority the ecomPriority to set
     */
    public void setEcomPriority(int ecomPriority) {
        this.ecomPriority = ecomPriority;
    }

    /**
     * @return the qaPriority
     */
    public int getQaPriority() {
        return qaPriority;
    }

    /**
     * @param qaPriority the qaPriority to set
     */
    public void setQaPriority(int qaPriority) {
        this.qaPriority = qaPriority;
    }

    /**
     * @return the pri
     */
    public String getPri() {
        return pri;
    }

    /**
     * @param pri the pri to set
     */
    public void setPri(String pri) {
        this.pri = pri;
    }

    public List getDesignationList() {
        return designationList;
    }

    public void setDesignationList(List designationList) {
        this.designationList = designationList;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public Map getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(Map projectsList) {
        this.projectsList = projectsList;
    }

    public String getCustLoginId() {
        return custLoginId;
    }

    public void setCustLoginId(String custLoginId) {
        this.custLoginId = custLoginId;
    }

    /**
     * @return the conatctAliasName
     */
    public String getConatctAliasName() {
        return conatctAliasName;
    }

    /**
     * @param conatctAliasName the conatctAliasName to set
     */
    public void setConatctAliasName(String conatctAliasName) {
        this.conatctAliasName = conatctAliasName;
    }

    public int getActivitySummaryFlag() {
        return activitySummaryFlag;
    }

    public void setActivitySummaryFlag(int activitySummaryFlag) {
        this.activitySummaryFlag = activitySummaryFlag;
    }

    public String getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(String contactStatus) {
        this.contactStatus = contactStatus;
    }

    public int getMainPriority() {
        return mainPriority;
    }

    public void setMainPriority(int mainPriority) {
        this.mainPriority = mainPriority;
    }

    /**
     * @return the fromMonth
     */
    public int getFromMonth() {
        return fromMonth;
    }

    /**
     * @param fromMonth the fromMonth to set
     */
    public void setFromMonth(int fromMonth) {
        this.fromMonth = fromMonth;
    }

    /**
     * @return the fromYear
     */
    public int getFromYear() {
        return fromYear;
    }

    /**
     * @param fromYear the fromYear to set
     */
    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    /**
     * @return the toMonth
     */
    public int getToMonth() {
        return toMonth;
    }

    /**
     * @param toMonth the toMonth to set
     */
    public void setToMonth(int toMonth) {
        this.toMonth = toMonth;
    }

    /**
     * @return the toYear
     */
    public int getToYear() {
        return toYear;
    }

    /**
     * @param toYear the toYear to set
     */
    public void setToYear(int toYear) {
        this.toYear = toYear;
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
     * @return the typeList
     */
    public List getTypeList() {
        return typeList;
    }

    /**
     * @param typeList the typeList to set
     */
    public void setTypeList(List typeList) {
        this.typeList = typeList;
    }

    /**
     * @return the leadsResultMessage
     */
    public String getLeadsResultMessage() {
        return leadsResultMessage;
    }

    /**
     * @param leadsResultMessage the leadsResultMessage to set
     */
    public void setLeadsResultMessage(String leadsResultMessage) {
        this.leadsResultMessage = leadsResultMessage;
    }

    /**
     * @return the opportunityStateList
     */
    public List getOpportunityStateList() {
        return opportunityStateList;
    }

    /**
     * @param opportunityStateList the opportunityStateList to set
     */
    public void setOpportunityStateList(List opportunityStateList) {
        this.opportunityStateList = opportunityStateList;
    }

    /**
     * @return the myTeamMembersForRep
     */
    public Map getMyTeamMembersForRep() {
        return myTeamMembersForRep;
    }

    /**
     * @param myTeamMembersForRep the myTeamMembersForRep to set
     */
    public void setMyTeamMembersForRep(Map myTeamMembersForRep) {
        this.myTeamMembersForRep = myTeamMembersForRep;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse=httpServletResponse;
    }

    /**
     * @return the practiceIdList
     */
    public List getPracticeIdList() {
        return practiceIdList;
    }

    /**
     * @param practiceIdList the practiceIdList to set
     */
    public void setPracticeIdList(List practiceIdList) {
        this.practiceIdList = practiceIdList;
    }

    /**
     * @return the opportunityState
     */
    public String getOpportunityState() {
        return opportunityState;
    }

    /**
     * @param opportunityState the opportunityState to set
     */
    public void setOpportunityState(String opportunityState) {
        this.opportunityState = opportunityState;
    }

    /**
     * @return the requirementstatus
     */
    public String getRequirementstatus() {
        return requirementstatus;
    }

    /**
     * @param requirementstatus the requirementstatus to set
     */
    public void setRequirementstatus(String requirementstatus) {
        this.requirementstatus = requirementstatus;
    }

    /**
     * @return the preSalesMap
     */
    public Map getPreSalesMap() {
        return preSalesMap;
    }

    /**
     * @param preSalesMap the preSalesMap to set
     */
    public void setPreSalesMap(Map preSalesMap) {
        this.preSalesMap = preSalesMap;
    }

    /**
     * @return the requestorId
     */
    public String getRequestorId() {
        return requestorId;
    }

    /**
     * @param requestorId the requestorId to set
     */
    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    /**
     * @return the rvpName
     */
    public String getRvpName() {
        return rvpName;
    }

    /**
     * @param rvpName the rvpName to set
     */
    public void setRvpName(String rvpName) {
        this.rvpName = rvpName;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the consultantId
     */
    public int getConsultantId() {
        return consultantId;
    }

    /**
     * @param consultantId the consultantId to set
     */
    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    /**
     * @return the meetingStatus
     */
    public String getMeetingStatus() {
        return meetingStatus;
    }

    /**
     * @param meetingStatus the meetingStatus to set
     */
    public void setMeetingStatus(String meetingStatus) {
        this.meetingStatus = meetingStatus;
    }

    /**
     * @return the meetingType
     */
    public String getMeetingType() {
        return meetingType;
    }

    /**
     * @param meetingType the meetingType to set
     */
    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    /**
     * @return the levelEngagement
     */
    public String getLevelEngagement() {
        return levelEngagement;
    }

    /**
     * @param levelEngagement the levelEngagement to set
     */
    public void setLevelEngagement(String levelEngagement) {
        this.levelEngagement = levelEngagement;
    }

    /**
     * @return the opportunityId
     */
    public int getOpportunityId() {
        return opportunityId;
    }

    /**
     * @param opportunityId the opportunityId to set
     */
    public void setOpportunityId(int opportunityId) {
        this.opportunityId = opportunityId;
    }

    /**
     * @return the engagementDetails
     */
    public String getEngagementDetails() {
        return engagementDetails;
    }

    /**
     * @param engagementDetails the engagementDetails to set
     */
    public void setEngagementDetails(String engagementDetails) {
        this.engagementDetails = engagementDetails;
    }

    /**
     * @return the insightDetails
     */
    public String getInsightDetails() {
        return insightDetails;
    }

    /**
     * @param insightDetails the insightDetails to set
     */
    public void setInsightDetails(String insightDetails) {
        this.insightDetails = insightDetails;
    }

    /**
     * @return the meetingDate
     */
    public String getMeetingDate() {
        return meetingDate;
    }

    /**
     * @param meetingDate the meetingDate to set
     */
    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
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
     * @return the timeZone
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * @param timeZone the timeZone to set
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * @return the otherMeetingSlots
     */
    public String getOtherMeetingSlots() {
        return otherMeetingSlots;
    }

    /**
     * @param otherMeetingSlots the otherMeetingSlots to set
     */
    public void setOtherMeetingSlots(String otherMeetingSlots) {
        this.otherMeetingSlots = otherMeetingSlots;
    }

    /**
     * @return the additionalComments
     */
    public String getAdditionalComments() {
        return additionalComments;
    }

    /**
     * @param additionalComments the additionalComments to set
     */
    public void setAdditionalComments(String additionalComments) {
        this.additionalComments = additionalComments;
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
     * @return the requestId
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the opportunitiesMap
     */
    public Map getOpportunitiesMap() {
        return opportunitiesMap;
    }

    /**
     * @param opportunitiesMap the opportunitiesMap to set
     */
    public void setOpportunitiesMap(Map opportunitiesMap) {
        this.opportunitiesMap = opportunitiesMap;
    }

    /**
     * @return the otherMeetingDate
     */
    public String getOtherMeetingDate() {
        return otherMeetingDate;
    }

    /**
     * @param otherMeetingDate the otherMeetingDate to set
     */
    public void setOtherMeetingDate(String otherMeetingDate) {
        this.otherMeetingDate = otherMeetingDate;
    }

    /**
     * @return the otherStartTime
     */
    public String getOtherStartTime() {
        return otherStartTime;
    }

    /**
     * @param otherStartTime the otherStartTime to set
     */
    public void setOtherStartTime(String otherStartTime) {
        this.otherStartTime = otherStartTime;
    }

    /**
     * @return the otherMidDayFrom
     */
    public String getOtherMidDayFrom() {
        return otherMidDayFrom;
    }

    /**
     * @param otherMidDayFrom the otherMidDayFrom to set
     */
    public void setOtherMidDayFrom(String otherMidDayFrom) {
        this.otherMidDayFrom = otherMidDayFrom;
    }

    /**
     * @return the otherTimeZone
     */
    public String getOtherTimeZone() {
        return otherTimeZone;
    }

    /**
     * @param otherTimeZone the otherTimeZone to set
     */
    public void setOtherTimeZone(String otherTimeZone) {
        this.otherTimeZone = otherTimeZone;
    }

    /**
     * @return the requestStage
     */
    public String getRequestStage() {
        return requestStage;
    }

    /**
     * @param requestStage the requestStage to set
     */
    public void setRequestStage(String requestStage) {
        this.requestStage = requestStage;
    }

    /**
     * @return the requestStatus
     */
    public String getRequestStatus() {
        return requestStatus;
    }

    /**
     * @param requestStatus the requestStatus to set
     */
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
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
     * @return the requestorName
     */
    public String getRequestorName() {
        return requestorName;
    }

    /**
     * @param requestorName the requestorName to set
     */
    public void setRequestorName(String requestorName) {
        this.requestorName = requestorName;
    }

    /**
     * @return the requestResources
     */
    public String getRequestResources() {
        return requestResources;
    }

    /**
     * @param requestResources the requestResources to set
     */
    public void setRequestResources(String requestResources) {
        this.requestResources = requestResources;
    }

    /**
     * @return the requestResourcesList
     */
    public List getRequestResourcesList() {
        return requestResourcesList;
    }

    /**
     * @param requestResourcesList the requestResourcesList to set
     */
    public void setRequestResourcesList(List requestResourcesList) {
        this.requestResourcesList = requestResourcesList;
    }

    /**
     * @return the resourcesHidden
     */
    public String getResourcesHidden() {
        return resourcesHidden;
    }

    /**
     * @param resourcesHidden the resourcesHidden to set
     */
    public void setResourcesHidden(String resourcesHidden) {
        this.resourcesHidden = resourcesHidden;
    }

    /**
     * @return the resourceMap
     */
    public Map getResourceMap() {
        return resourceMap;
    }

    /**
     * @param resourceMap the resourceMap to set
     */
    public void setResourceMap(Map resourceMap) {
        this.resourceMap = resourceMap;
    }

    /**
     * @return the clientType
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * @param clientType the clientType to set
     */
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    /**
     * @return the opportunityName
     */
    public String getOpportunityName() {
        return opportunityName;
    }

    /**
     * @param opportunityName the opportunityName to set
     */
    public void setOpportunityName(String opportunityName) {
        this.opportunityName = opportunityName;
    }

    /**
     * @return the preRequestStage
     */
    public String getPreRequestStage() {
        return preRequestStage;
    }

    /**
     * @param preRequestStage the preRequestStage to set
     */
    public void setPreRequestStage(String preRequestStage) {
        this.preRequestStage = preRequestStage;
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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the bdmId
     */
    public int getBdmId() {
        return bdmId;
    }

    /**
     * @param bdmId the bdmId to set
     */
    public void setBdmId(int bdmId) {
        this.bdmId = bdmId;
    }

    /**
     * @return the resourcesHidden1
     */
    public String getResourcesHidden1() {
        return resourcesHidden1;
    }

    /**
     * @param resourcesHidden1 the resourcesHidden1 to set
     */
    public void setResourcesHidden1(String resourcesHidden1) {
        this.resourcesHidden1 = resourcesHidden1;
    }

    /**
     * @return the requestType
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * @param requestType the requestType to set
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * @return the previousResourceIds
     */
    public String getPreviousResourceIds() {
        return previousResourceIds;
    }

    /**
     * @param previousResourceIds the previousResourceIds to set
     */
    public void setPreviousResourceIds(String previousResourceIds) {
        this.previousResourceIds = previousResourceIds;
    }

    /**
     * @return the campaignIdMap
     */
    public Map getCampaignIdMap() {
        return campaignIdMap;
    }

    /**
     * @param campaignIdMap the campaignIdMap to set
     */
    public void setCampaignIdMap(Map campaignIdMap) {
        this.campaignIdMap = campaignIdMap;
    }

    /**
     * @return the requirementStatusList
     */
    public List getRequirementStatusList() {
        return requirementStatusList;
    }

    /**
     * @param requirementStatusList the requirementStatusList to set
     */
    public void setRequirementStatusList(List requirementStatusList) {
        this.requirementStatusList = requirementStatusList;
    }

    /**
     * @return the pgNo
     */
    public int getPgNo() {
        return pgNo;
    }

    /**
     * @param pgNo the pgNo to set
     */
    public void setPgNo(int pgNo) {
        this.pgNo = pgNo;
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
     * @return the ibmPPANo
     */
    public String getIbmPPANo() {
        return ibmPPANo;
    }

    /**
     * @param ibmPPANo the ibmPPANo to set
     */
    public void setIbmPPANo(String ibmPPANo) {
        this.ibmPPANo = ibmPPANo;
    }

    /**
     * @return the ibmSiteNo
     */
    public String getIbmSiteNo() {
        return ibmSiteNo;
    }

    /**
     * @param ibmSiteNo the ibmSiteNo to set
     */
    public void setIbmSiteNo(String ibmSiteNo) {
        this.ibmSiteNo = ibmSiteNo;
    }

    /**
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator the operator to set
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return the practiceSalesMap
     */
    public Map getPracticeSalesMap() {
        return practiceSalesMap;
    }

    /**
     * @param practiceSalesMap the practiceSalesMap to set
     */
    public void setPracticeSalesMap(Map practiceSalesMap) {
        this.practiceSalesMap = practiceSalesMap;
    }

    /**
     * @return the regionalMgrId
     */
    public int getRegionalMgrId() {
        return regionalMgrId;
    }

    /**
     * @param regionalMgrId the regionalMgrId to set
     */
    public void setRegionalMgrId(int regionalMgrId) {
        this.regionalMgrId = regionalMgrId;
    }
}
