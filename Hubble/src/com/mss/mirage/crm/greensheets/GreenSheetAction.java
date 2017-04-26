/*******************************************************************************
 * /*
 * @(#)GreenSheetAction.java	September 26, 2007, 12:13 AM
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.mss.mirage.crm.greensheets;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.FileUploadUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.mss.mirage.util.HibernateDataProvider;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.commons.io.FileUtils;
import java.io.File;


/**
 * The <code>GreenSheetAction</code>  class is used for getting new Employee details from
 * <i>GreenSheetAdd.jsp</i> page.
 * <p>
 * Then it invokes setter methods in <code>AddEmployee</code> class and invokes doEdit() method
 * in <code>GreenSheetAction</code> for inserting employee details in TBLGREENSHEETS table.
 *
 * @author Suresh Nalla <a href="mailto:snalla@miraclesoft.com">snalla@miraclesoft.com</a>
 *
 * @version 1.0 November 29, 2007
 *
 * @see com.mss.mirage.crm.greensheets.GreenSheetAction
 */
public class GreenSheetAction extends ActionSupport implements ServletRequestAware{
    
    /**
     * This variable httpServletRequest store the HttpServletRequest object reference
     * <code>{@link #HttpServletRequest}</code>.
     */
    public GreenSheetAction() {
    }
    
    /**
     * A Map object with an salesMap  object and read from a
     * full List of data.
     */
    private Map salesMap;
    
    /**
     * A Map object with an unitsMap  object and read from a
     * full List of data.
     */
    private Map unitsMap;
    
    /**
     * A List object with an expencesList  object and read from a
     * full List of data.
     */
    private List expencesList;
    
    /**
     * A Map object with an clientCurrencyMap  object and read from a
     * full List of data.
     */
    private Map clientCurrencyMap;
     private HibernateDataProvider hibernateDataProvider;
    
    /**
     * A List object with an greensheetStautsList  object and read from a
     * full List of data.
     */
    private List greensheetStautsList;
    
    /**
     * A List object with an greensheetStautsList  object and read from a
     * full List of data.
     */
    private List greensheetPOStautsList;
    
    /**
     * A Map object with an greensheetScopeOfWork  object and read from a
     * full List of data.
     */
    private Map greensheetScopeOfWork;
    
    /**
     * A List object with an greensheetVPSalesList  object and read from a
     * full List of data.
     */
    private List greensheetVPSalesList;
    
    /**
     * A Map object with an greensheetPaymentTerms  object and read from a
     * full List of data.
     */
    private Map greensheetPaymentTerms;
    
    /**
     * A List object with an greensheetVendorNamesList  object and read from a
     * full List of data.
     */
    private List greensheetVendorNamesList;
    
    /**
     * A Map object with an greensheetCustomerNamesMap  object and read from a
     * full List of data.
     */
    private Map greensheetCustomerNamesMap;
    
    private List accountTypeList = new ArrayList();
    private List accountStatusList = new ArrayList();
    private List industryList = new ArrayList();
    private List regionList = new ArrayList();
    private List territoryList = new ArrayList();
    private HttpServletRequest httpServletRequest;
    private GreenSheetVTO greensheetVTO;
    
    // for upload
    
    /** The upload is used for storing the upload of the tblCrmAttachments. */
    private File upload;
    
    /** The uploadContentType is used for storing the uploadcontenttype of the tblCrmAttachments. */
    private String uploadContentType;
    
    /** The uploadFileName is used for storing the uploadfilename of the tblCrmAttachments. */
    private String uploadFileName;
    
    /** The requestType is used for storing the requestType of the tblCrmAttachments. */
    private String requestType;
    
    /** The attachmentName is used for storing the attachmentName of the tblCrmAttachments. */
    private String attachmentName;
    
    /** The objectid is used for storing the objectid of the tblCrmAttachments. */
    private String objectid;
    
    /** The date is used for storing the date of the tblCrmAttachments. */
    private Timestamp date;
    
    /** The filepath is used for storing the filepath of the tblCrmAttachments. */
    private String filepath;
    
    /** The fileLocation is used for storing the fileLocation of the tblCrmAttachments. */
    private String fileLocation;
    
    //private File upload;
    
    
    private String submitFrom;
    
    private StringBuffer queryStringBuffer;
    
    private DataSourceDataProvider dataSourceDataProvider;
    
   
    
    private int userRoleId;
    // The userRoleName is used to store the role name selected by the user when he logs in
    private String userRoleName;
    
    private Map salesManagerMap;
    
    private String empCode;
    private String renewalEmail;
    private String renewalIntEmail;
    private String poType;
    private String poNumber;
    private String poLineno;
    private String intRefcode;
    
    private String maxQuantity;
    private double totalValue;
    private String location;
    private java.sql.Date consStartDate;
    private java.sql.Date consEndDate;
    private String nocValue;
    private java.sql.Date nocDate;
    private int consultantTypeId;
    private String comments;
    
    private String primaryVicePresident;
    private double primaryVicePresidentCommission;
    private String secondaryVicePresident;
    private double secondaryVicePresidentCommission;
    private String createdBy;
    private String modifedBy;
    
    private String invoiceNo;
    private String projectName;
    private String customerLocation;
    private java.sql.Date resaleDate;
    private String softDetails;
    private String customerPrice;
    private String avnetPrice;
    private String ibmIcsf;
    private String addServices;
    private String salesTax;
    private String salesTaxState;
    private String profitAmt;
    private String profitPercent;
    
    /* Creating a Timestamp dateCreated to get date and time from the local system. */
    private Timestamp dateCreatedOn;
    
    /* Creating a Timestamp dateModified to get date and time from the local system. */
    private Timestamp dateModifiedOn;
    private String resultMessageList;
    private Map myTeamMembers = new TreeMap();
    private String teamGreensheets;
    private String mspVendor;
    /** Creating new instance for countryList. */
    private List countryList = new ArrayList();
    private String country;
    private String modeType;
    
  
    /** new Nag**/
    
    private String invFaxNumber;
    private String invAttnComments;
    private String vendorTaxId;
    private String vendorFaxNumber;
    private String vendorPaymentAddress;
    private String vendorComments;
    private String typeOfResale;
    private String partNumber;
    //private String contactName;
    //private String contactPhoneNumber;
    private String contactFaxNumber;
     private String rejectesReason;
     private List practiceList;
    private String practiceName;
    
    private String accountEdit;
    private String accId;
    
    public String execute() throws Exception{
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            try{                    
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    setCountryList(hibernateDataProvider
                            .getContries(ApplicationConstants.COUNTRY_OPTIONS));
                    resultType = SUCCESS;
            }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }                    
        }        
        return resultType;
    }

    
    /**
     * The doAdd() is used for insert the data  from  the GreenSheetAdd.jsp page into the databasetable.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String doAdd(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_GREENSHEET",userRoleId)){
                try{
                    
                    boolean isInsert=false;
                  String loginEmpName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();  
                    //System.out.println("hi "+getCustomerName()+"length"+getCustomerName().length());
                    setOperationType(ApplicationConstants.SP_ADD);
                    if(getUploadFileName() != null && !getUploadFileName().equals(null) && getUploadFileName().length() > 1){
                        saveFileToDisk();
                    }
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                   
                    //System.err.print("vendorContact-->"+getVendorContactPerson());
                    
                    setDateCreatedOn(DateUtility.getInstance().getCurrentMySqlDateTime());
                    
                    setEmpId((String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID));
                    isInsert=ServiceLocator.getGreenSheetService().insertGreenSheet(this);
                    setAccountId(Integer.parseInt(getConsultantId()));
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    int count = dataSourceDataProvider.touchAccount(getAccountId());
                    //System.out.println("teamGreensheets in doAdd -- "+count);
                    if(isInsert){
                         if(Properties.getProperty("Mail.Flag").equals("1")) {
                      MailManager.sendGreenSheetDetails(this,loginEmpName); 
                         }
                        resultType = SUCCESS;
                        resultMessage = "GreenSheet has been successfully inserted!";
                        setResultMessageList(resultMessage);
                        
                    }else{
                        resultType = INPUT;
                        resultMessage = "Sorry! Please Try again!";
                        setResultMessageList(resultMessage);
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    setLookUpData();
                    setAccountName(getCustomerName());
                    
                    
                    //if(LOGIN.equals(resultType)) resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    //ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close for session checking
        return resultType;
    }
    
   public boolean saveFileToDisk() throws ServiceLocatorException {
        boolean isSave=false;
        
        String basePath=Properties.getProperty("Attachments.PO.Path")+ "//MirageV2//" + getRequestType();
        String theFilePath = FileUploadUtility.getInstance().filePathGeneration(basePath);
        String fileName=FileUploadUtility.getInstance().fileNameGeneration( getUploadFileName());
        File theFile = new File(theFilePath + "//" + fileName);
        setFilepath(theFile.toString());
        
        /*copies the file to the destination*/
        try{
            FileUtils.copyFile(upload,theFile);
            isSave=true;
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSave;
    }

    /**
     * The prepare() is used for sending lookUp data  GreenSheetAdd.jsp page.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    
    public String prepare(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_GREENSHEET",userRoleId)){
                try{
                    //System.err.println("Account name-----"+getId());
                    //setAccountId(getId());
                    setAccountId(getAccountId());
                    setModeType("Add Greensheet");
                    setLookUpData();
                    GreenSheetVTO vto=new GreenSheetVTO();
                    // to set Customer name
                    vto.setCustomerName(getCustomerName());
                    
                    vto.setActionType("newGreenSheetSubmit");
                    
                    setGreensheetVTO(vto);
                     // practice list by department and type
                    setPracticeList(DataSourceDataProvider.getInstance().getPracticeNamesList(7,1));
                    String teamScreen = httpServletRequest.getParameter("teamGreensheets");
                    if(teamScreen.equalsIgnoreCase("true")) {
                        setTeamGreensheets("notAvailable");
                    }
                    
                    if(getAccountId() != 0){
                        setAccountName(HibernateDataProvider.getInstance().getAccountName(getAccountId()));
                    }
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close for session checking
        return resultType;
    }
    
    public String prepareSearch(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userRoleName= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            String userId = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String teamGsheets = httpServletRequest.getParameter("teamValue");
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_SEARCH_GREENSHEET",userRoleId)){
                try{
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    setCountryList(hibernateDataProvider
                            .getContries(ApplicationConstants.COUNTRY_OPTIONS));
                     setSalesMap(hibernateDataProvider.getCrmBDM(ApplicationConstants.CRM_BDM_OPTIONS));
                     setSalesManagerMap(dataSourceDataProvider.getCrmManagers());
                    //setGreensheetPOStautsList(HibernateDataProvider.getInstance().getGreenSheetPOStatus(ApplicationConstants.GEENSHEET_POSTAUTS_OPTIONS));                    
                    //Retrieving Teammembers Map from session and seting to MyTeamManager Map
                    setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                    // Retrieve the List of all the Team Members belonging to the Logged in User
                    String myTeamMembers = getKeys(getMyTeamMembers(),",");
                    
                    queryStringBuffer = new StringBuffer();
                    
                    queryStringBuffer.append("SELECT Id,CustomerName,concat(FirstName,' ',MiddleName,' ',LastName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy," +
                            "ModifiedBy,UnitsRate,Status FROM vwGreenSheets WHERE ");
                    
                    if(userRoleName.equalsIgnoreCase("Admin")) {  //|| (userRoleName.equalsIgnoreCase("Operations"))){
                        queryStringBuffer.append(" ");
                        setTeamGreensheets("true");
                    }else if(userRoleName.equalsIgnoreCase("Operations")) {
                       // queryStringBuffer.append(" Country like '"+workingCountry+"' AND ");
                        setTeamGreensheets("true");
                    }else{
                       // queryStringBuffer.append(" Country like '"+workingCountry+"' AND ");
                        if(teamGsheets.equalsIgnoreCase("available")) {
                            // Team Greensheets
                            queryStringBuffer.append(" CreatedBy in ");
                            queryStringBuffer.append(" (" + myTeamMembers + ")");
                            setTeamGreensheets("false");
                        }else if(teamGsheets.equalsIgnoreCase("notAvailable")) {
                            // My Greensheets
                            queryStringBuffer.append(" CreatedBy='"+userId+"'  ");
                            setTeamGreensheets("true");
                        }
                        queryStringBuffer.append(" AND");
                    }
                    
                    
                    queryStringBuffer.append(" RecordType=1");
                   // queryStringBuffer.append(" ORDER BY CustomerName,POStatus,DateEnd");
                    //System.err.println("query--"+queryStringBuffer.toString());
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_GS_LIST) != null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_GS_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_GS_LIST,queryStringBuffer);
                    
                    //System.out.println("query is   "+queryStringBuffer.toString());
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close for session checking
        return resultType;
    }
    
    
    /**
     * The getQuery() is used for getting the value of the Id from  the GreenSheetAdd.jsp page.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    
    public String getQuery(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userRoleName= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            String userId = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            resultType = "accessFailed";
            String teamScreen = httpServletRequest.getParameter("teamScreen");
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_QUERY_GREENSHEET",userRoleId)){
                try{
                     hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    setCountryList(hibernateDataProvider
                            .getContries(ApplicationConstants.COUNTRY_OPTIONS));
                     setSalesMap(hibernateDataProvider.getCrmBDM(ApplicationConstants.CRM_BDM_OPTIONS));
                     setSalesManagerMap(dataSourceDataProvider.getCrmManagers());
                    
                    if("Search".equalsIgnoreCase(getSubmitFrom())){
                        queryStringBuffer = new StringBuffer();
                        
                        //queryStringBuffer.append("SELECT * FROM vwGreenSheetList Where");
                        
                        //Retrieving Teammembers Map from session and seting to MyTeamManager Map
                        setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                        
                        // Retrieve the List of all the Team Members belonging to the Logged in User
                        String myTeamMembers = getKeys(getMyTeamMembers(),",");
                        
                        setTeamGreensheets("none");
                        if("Admin".equalsIgnoreCase(userRoleName)) { 
                            queryStringBuffer.append("SELECT Id,CustomerName,concat(LastName,' ',FirstName,' ',MiddleName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy,ModifiedBy,UnitsRate,Status  FROM vwGreenSheets Where");
                            setTeamGreensheets("true");
                        }else if("Operations".equalsIgnoreCase(userRoleName)) { 
                            //queryStringBuffer.append("SELECT Id,CustomerName,concat(LastName,' ',FirstName,' ',MiddleName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy,ModifiedBy,UnitsRate,Status FROM vwGreenSheets Where Country like '"+workingCountry+"' AND ");
                            queryStringBuffer.append("SELECT Id,CustomerName,concat(LastName,' ',FirstName,' ',MiddleName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy,ModifiedBy,UnitsRate,Status FROM vwGreenSheets Where  ");
                            setTeamGreensheets("true");
                        }else{
                            if(teamScreen.equalsIgnoreCase("false")) {
                               // queryStringBuffer.append("SELECT Id,CustomerName,concat(LastName,' ',FirstName,' ',MiddleName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy,ModifiedBy,UnitsRate,Status FROM vwGreenSheets Where Country like '"+workingCountry+"' AND ");
                                queryStringBuffer.append("SELECT Id,CustomerName,concat(LastName,' ',FirstName,' ',MiddleName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy,ModifiedBy,UnitsRate,Status FROM vwGreenSheets Where ");
                                queryStringBuffer.append(" CreatedBy in ");
                                queryStringBuffer.append(" (" + myTeamMembers + ")");
                                queryStringBuffer.append(" AND");
                                setTeamGreensheets("false");
                            }else if(teamScreen.equalsIgnoreCase("true")) {
                               // queryStringBuffer.append("SELECT Id,CustomerName,concat(LastName,' ',FirstName,' ',MiddleName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy,ModifiedBy,UnitsRate,Status FROM vwGreenSheets Where Country like '"+workingCountry+"' AND CreatedBy='"+userId+"' AND");
                                queryStringBuffer.append("SELECT Id,CustomerName,concat(LastName,' ',FirstName,' ',MiddleName) AS ConsultantName,ConsultantType,DateStart,DateEnd,POStatus,CreatedBy,ModifiedBy,UnitsRate,Status FROM vwGreenSheets Where CreatedBy='"+userId+"' AND");
                                setTeamGreensheets("true");
                            }
                        }
                        
                        if(!"".equals(getCustomerName())){
                            queryStringBuffer.append(" CustomerName like '"+getCustomerName().trim()+"%'");
                            if(!"".equals(getPoStatusType().trim())){
                                queryStringBuffer.append(" AND POStatus='"+getPoStatusType().trim()+"'");
                            }
                        } else{
                            if(!"".equals(getPoStatusType().trim())){
                                queryStringBuffer.append("POStatus='"+getPoStatusType().trim()+"'");
                            }
                        }
                        
                        if(!"".equals(getFname())){
                            queryStringBuffer.append(" AND FirstName like '"+getFname().trim()+"%'");
                        }
                        if(!"".equals(getLastName())){
                            queryStringBuffer.append(" AND LastName like '"+getLastName().trim()+"%'");
                        }
                        //System.out.println("--->>>"+getPrimarySalesManager()+"--->"+getPrimaryVicePresident());
                        if(! getPrimarySalesPerson().equals("") && !getPrimarySalesPerson().equals("1")){
                            queryStringBuffer.append(" AND PriSalesPersonId = '"+getPrimarySalesPerson()+"'");
                        }
                        
                         if(! getPrimarySalesManager().equals("") && !getPrimarySalesManager().equals("1")){
                            queryStringBuffer.append(" AND PriSalesMgrId = '"+getPrimarySalesManager()+"'");
                        }
                        
                          if(! getPrimaryVicePresident().equals("") && !getPrimaryVicePresident().equals("1")){
                            queryStringBuffer.append(" AND PrimaryVPId = '"+getPrimaryVicePresident()+"'");
                        }
                        
                        /**
                         *New search freature
                         *  based on greensheet status
                         **/
                        if(!"1".equals(getStatus())){
                            queryStringBuffer.append(" AND Status like '"+getStatus()+"'");
                        }
                        
                
                        //System.out.println("--------"+getPrimarySalesPerson()+"--->>>"+getPrimarySalesManager()+"--->"+getPrimaryVicePresident());
                        if(! getPoStartDateFrom().equals("") && !getPoStartDateTo().equals("") )   {                                  
                            queryStringBuffer.append(" AND date(DateStart) between date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getPoStartDateFrom()))+ "') AND date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getPoStartDateTo()))+"')");
                        }
                        if(! getPoEndDateFrom().equals("") && !getPoEndDateTo().equals("") )   {                                                                                    
                            queryStringBuffer.append(" AND date(DateEnd) between date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getPoEndDateFrom()))+ "') AND date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getPoEndDateTo()))+"')");
                        }
                        if(! getEmpStartDateFrom().equals("") && !getEmpStartDateTo().equals("") )   {                                                                                    
                            queryStringBuffer.append(" AND date(ConsulStartDate) between date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEmpStartDateFrom()))+ "') AND date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEmpStartDateTo()))+"')");
                        }                        
                        if(! getEmpEndDateFrom().equals("") && !getEmpEndDateTo().equals("") )   {                                                                                    
                            queryStringBuffer.append(" AND date(ConsulEndDate) between date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEmpEndDateFrom()))+ "') AND date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEmpEndDateTo()))+"')");
                        }
                        
                        if("Admin".equalsIgnoreCase(userRoleName)) { 
                            if(!"".equals(getCountry().trim())){
                                queryStringBuffer.append(" AND Country='"+getCountry().trim()+"'");
                            }
                        }
                        
                        
                        //queryStringBuffer.append("  );
                        queryStringBuffer.append(" AND RecordType=1");
                       // queryStringBuffer.append(" ORDER BY CustomerName");
                       // System.out.println("");
                        
                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_GS_LIST) != null){
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_GS_LIST);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_GS_LIST,queryStringBuffer);
                        
                    }
                    //System.out.println("query1 is   "+queryStringBuffer.toString());
                    //prepareSearch();
                    
                    if( null == getPoStatusType() && "".equals(getPoStatusType())) {
                        setPoStatusType(getGreensheetPOStautsList().get(1).toString());
                    }
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close for session checking
        return resultType;
    }
    
    
    /**
     * The doEdit() is used for update the data  from  the GreenSheetAdd.jsp page and save update data into the databasetable.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String doEdit(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_GREENSHEET",userRoleId)){
                try{
                    //if(getUploadFileName().length() >1){
                    if(getUploadFileName() != null && !getUploadFileName().equals(null) && getUploadFileName().length() > 1){
                        saveFileToDisk();
                    }
                   String loginEmpName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();   
                   String statusBeforeChange=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.GREENSHEETSTATUS_BEFORE).toString();
                   //System.out.println("GreenSheetstatus before in action --->"+statusBeforeChange);
                   //System.out.println("Green Sheet status after --->"+getStatus());
                    setEmpId((String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID));
                    setOperationType(ApplicationConstants.SP_EDIT);
                    
                    setModifedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    setDateModifiedOn(DateUtility.getInstance().getCurrentMySqlDateTime());
                    //System.out.println("team in edit "+getTeamGreensheets()); 
                    setTeamGreensheets(getTeamGreensheets());
                    if(ServiceLocator.getGreenSheetService().insertGreenSheet(this)){
                        //System.out.println("teamGreensheets in doEdit -- "+getTeamGreensheets());
                          //if((getStatus().toString().equals("Approved")) || (getStatus().toString().equals("Rejected")) )
                        String tagLine = "";
                        if((getStatus().toString().equals("Approved")))
                        {
                            
                            if(statusBeforeChange.equals("Approved")){
                                tagLine = "Updated By";
                                
                            }else{
                                tagLine = "Approved By";
                            }
                             if(Properties.getProperty("Mail.Flag").equals("1")) {
                        MailManager.sendReminderForGreenSheets(this,loginEmpName,tagLine); 
                             }
                        }
                        //if((getStatus().toString().equals("Rejected")) && !(statusBeforeChange.equals("Rejected")))
                        if((getStatus().toString().equals("Rejected")))
                        {
                             if(statusBeforeChange.toString().equals("Rejected")){
                                tagLine = "Updated By";
                                
                            }else{
                                tagLine = "Rejected By";
                            }
                              if(Properties.getProperty("Mail.Flag").equals("1")) {
                            MailManager.sendReminderForGreenSheets(this,loginEmpName,tagLine);  
                              }
                        }
                        
                        //System.out.print("green sheets status--->"+getStatus()+"-------------"+getId());
                      //  resultType = SUCCESS;
                        
                         setAccId(getAccId());
                        if("1".equalsIgnoreCase(getAccountEdit())){
                        resultType = "Accountedit";
                        }
                        else{
                        resultType = SUCCESS;
                        }
                        
                        resultMessage = "GreenSheet has been successfully Updated!";
                        setResultMessageList(resultMessage);
                        setLookUpData();
                        setAccountId(Integer.parseInt(getConsultantId()));
                        setAccountName(getCustomerName());
                        dataSourceDataProvider = DataSourceDataProvider.getInstance();
                        int count = dataSourceDataProvider.touchAccount(getAccountId());
                        
                    }else{
                        resultMessage = "Sorry! Please Try again!";
                        resultType = ERROR;
                        setResultMessageList(resultMessage);
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    
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
    
    
    /**
     * The getGreenSheet() is used for update the data  from  the GreenSheetAdd.jsp page and save updated data into the databasetable.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String getGreenSheet(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_GREENSHEET",userRoleId)){
                try{
                    setModeType("Edit Greensheet");
                    setGreensheetVTO(ServiceLocator.getGreenSheetService().getGreenSheetByID(getGreenSheetId(),httpServletRequest.getSession(false)));
                    setAccountName(getGreensheetVTO().getCustomerName());
                      // practice list by department and type
                    setPracticeList(DataSourceDataProvider.getInstance().getPracticeNamesList(7,1));
                    //setAccountName(HibernateDataProvider.getInstance().getAccountName(getGreensheetVTO().getConsultantId()));
                    setAccountId(getGreensheetVTO().getConsultantId());
                    setLookUpData();
                    
                    if(httpServletRequest.getParameter("teamGreensheets") != null){
                        String teamScreen = httpServletRequest.getParameter("teamGreensheets");
                        if(teamScreen.equalsIgnoreCase("true")) {
                            setTeamGreensheets("notAvailable");
                        }else if(teamScreen.equalsIgnoreCase("false")) {
                            setTeamGreensheets("available");
                        }
                    }else{
                        setTeamGreensheets("none");
                    }
                    //System.out.println("greensheet in action:"+getGreensheetVTO().getStartDate());
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close for session checking
        
        return resultType;
    }
    
    public void setAccountAddLookupDate() throws Exception {
        hibernateDataProvider = HibernateDataProvider.getInstance();
        //setAccountTypeList(ApplicationDataProvider.getInstance().getCrmAccountType(ApplicationConstants.CRM_ACCOUNT_OPTION));
        //setAccountStatusList(ApplicationDataProvider.getInstance().getCrmAccountStatus(ApplicationConstants.CRM_ACCOUNT__STATUS_OPTION));
        setIndustryList(hibernateDataProvider.getIndustry(ApplicationConstants.INDUSTRY_OPTION));
        setRegionList(hibernateDataProvider.getRegion(ApplicationConstants.REGION_OPTIONS));
        //setTerritoryList(ApplicationDataProvider.getInstance().getTeritory(ApplicationConstants.TERITORY_OPTIONS));
    }
    public void setLookUpData() throws Exception{
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
        hibernateDataProvider = HibernateDataProvider.getInstance();
      //  setSalesMap(hibernateDataProvider.getCrmBDM(ApplicationConstants.CRM_BDM_OPTIONS));
      //  setSalesManagerMap(dataSourceDataProvider.getCrmManagers());
         setSalesMap(dataSourceDataProvider.sortMapByValues(hibernateDataProvider.getCrmBDM(ApplicationConstants.CRM_BDM_OPTIONS)));
        setSalesManagerMap(dataSourceDataProvider.sortMapByValues(dataSourceDataProvider.getCrmManagers()));
        setUnitsMap(hibernateDataProvider.getGreenSheetUnits(ApplicationConstants.GEENSHEET_UNTIS_OPTIONS));
        setExpencesList(hibernateDataProvider.getGreenSheetExpences(ApplicationConstants.GREEN_SHEET_EXPENSES_OPTIONS));
        setClientCurrencyMap(hibernateDataProvider.getGreenSheetClientBillingRate(ApplicationConstants.GEENSHEET_CURRENCY_OPTIONS));
        setGreensheetStautsList(hibernateDataProvider.getGreenSheetStatus(ApplicationConstants.GEENSHEET_STAUTS_OPTIONS));
        setGreensheetPOStautsList(hibernateDataProvider.getGreenSheetPOStatus(ApplicationConstants.GEENSHEET_POSTAUTS_OPTIONS));
        setGreensheetScopeOfWork(hibernateDataProvider.getGreenSheetScopeOfWorkList(ApplicationConstants.GEENSHEET_SCOPEOFWORK_OPTIONS));
        setGreensheetVPSalesList(hibernateDataProvider.getGreenSheetVPSalesList(ApplicationConstants.GEENSHEET_VPSALES_OPTIONS));
        setGreensheetPaymentTerms(hibernateDataProvider.getGreenSheetPaymentTerms(ApplicationConstants.GEENSHEET_PAYMENTTERMS_OPTIONS));
        setGreensheetVendorNamesList(dataSourceDataProvider.getGreenSheetVendorNamesList(ApplicationConstants.GEENSHEET_VENDORNAMES_OPTIONS));
        setGreensheetCustomerNamesMap(dataSourceDataProvider.getGreenSheetCustomerNamesMap(ApplicationConstants.GEENSHEET_CUSTOMERNAMES_OPTIONS));
        setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));
    }
    
    public String doAccountView() throws Exception {
        setAccountAddLookupDate();
        return SUCCESS;
    }
    
    //=====================================================================================
//  This method is used to loop through all the keys present in a Map Data Structure
//  and formulate them into a String of Keys seperated by a delimiter and return that
//  string of delimited values back to the caller
//=====================================================================================
    private String getKeys(Map map, String delimiter) {
        Iterator tempIterator = map.entrySet().iterator();
        String keys = "";
        String key="";
        int cnt = 0;
        
        while(tempIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) tempIterator.next();
            key = entry.getKey().toString();
            entry = null;
            
            //  Add the Delimiter to the Keys Field for the Second Key onwards
            if (cnt > 0) keys = keys + delimiter;
            
            keys = keys + "'" + key +"'";
            //System.out.println("keyssss------"+ keys);
            cnt++;
        }
        tempIterator = null;
        return(keys);
    }
    
    
    
    // for search
    
    /** The custName is used for storing the custname of the tblgreenSheets. */
    private String custName;
    
    /** The poStatusType is used for storing postatustype of the tblgreenSheets. */
    private String poStatusType;
    
    //private int consultantType;
    /** The empId is used for storing empid of the tblgreenSheets. */
    private String empId;
    
    /** The consultantId is used for storing consultantid of the tblgreenSheets. */
    private String consultantId;
    
    /** The consultantType is used for storing consultanttype of the tblgreenSheets. */
    private String consultantType;
    
    /** The customerName is used for storing customername of the tblgreenSheets. */
    private String customerName; // Account Id in DB
    
    /** The fName is used for storing fname of the tblgreenSheets. */
    //private String fName;
    private String fname;
    
    /** The lastName is used for storing lastname of the tblgreenSheets. */
    private String lastName;
    
    /** The middleName is used for storing middlename of the tblgreenSheets. */
    private String middleName;
    
    /** The phone is used for storing phone of the tblgreenSheets. */
    private String phone;
    
    /** The startDate is used for storing startdate of the tblgreenSheets. */
    private java.sql.Date startDate;
    
    /** The endDate is used for storing enddate of the tblgreenSheets. */
    private java.sql.Date endDate;
    
    /** The reportingManager is used for storing reportingmanager of the tblgreenSheets. */
    private String reportingManager;
    
    /** The reportingAddress is used for storing reportingaddress of the tblgreenSheets. */
    private String reportingAddress;
    
    /** The reportingManagerEmail is used for storing reportingmanageremail of the tblgreenSheets. */
    private String reportingManagerEmail;
    
    /** The reportingMGRPhone is used for storing reportingMGRphone of the tblgreenSheets. */
    private String reportingMGRPhone;
    
    /** The expenses is used for storing expenses of the tblgreenSheets. */
    private String expenses;
    
    /** The clientBillingRate is used for storing clientbillingrate of the tblgreenSheets. */
    private String clientBillingRate;
    
    /** The clientBillingRateType is used for storing clientBillingratetype of the tblgreenSheets. */
    private String clientBillingRateType;
    
    /** The dateCreated is used for storing dateCreated of the tblgreenSheets. */
    private Date dateCreated;
    
    /** The expensesDetails is used for storing expensesdetails of the tblgreenSheets. */
    private String expensesDetails;
    
    /** The equipmentNeeded is used for storing equipmentneeded of the tblgreenSheets. */
    private String equipmentNeeded;
    
    /** The otAllowed is used for storing otallowed of the tblgreenSheets. */
    private String otAllowed;
    
    /** The agencyName is used for storing agencyname of the tblgreenSheets. */
    private String agencyName;
    
    /** The vendorPhone is used for storing vendorphone of the tblgreenSheets. */
    private String vendorPhone;
    
    /** The vendorEmail is used for storing vendoremail of the tblgreenSheets. */
    private String vendorEmail;
    
    /** The vendorContactPerson is used for storing vendorcontactperson of the tblgreenSheets. */
    private String vendorContactPerson;
    
    /** The priBDMId is used for storing priBDMid of the tblgreenSheets. */
    private String priBDMId;
    
    /** The priBDMCommission is used for storing priBDMcommission of the tblgreenSheets. */
    private double priBDMCommission;
    
    /** The billingManager is used for storing billingmanager of the tblgreenSheets. */
    private String billingManager;
    
    /** The billingphone is used for storing billingphone of the tblgreenSheets. */
    private String billingphone;
    
    /** The billingAddress is used for storing billingaddress of the tblgreenSheets. */
    private String billingAddress;
    
    /** The billingManagerEmail is used for storing billingmanageremail of the tblgreenSheets. */
    private String billingManagerEmail;
    
    /** The status is used for storing status of the tblgreenSheets. */
    private String status;
    
    /** The poStatus is used for storing postatus of the tblgreenSheets. */
    private String poStatus;
    
    /** The duration is used for storing duration of the tblgreenSheets. */
    private double duration;
    
    /** The units is used for storing units of the tblgreenSheets. */
    private String units;
    
    /** The primarySalesPerson is used for storing primarysalesperson of the tblgreenSheets. */
    private String primarySalesPerson;
    
    /** The priSalesPersonCommission is used for storing priSalesPersoncommission of the tblgreenSheets. */
    private double priSalesPersonCommission;
    
    /** The primarySalesManager is used for storing primarysalesmanager of the tblgreenSheets. */
    private String primarySalesManager;
    
    /** The priSalesMngCommission is used for storing priSalesMngcommission of the tblgreenSheets. */
    private double priSalesMngCommission;
    
    /** The secondarySalesPersonCommission is used for storing secondarySalesPersoncommission of the tblgreenSheets. */
    private double secondarySalesPersonCommission;
    
    /** The vendorUnits is used for storing vendorunits of the tblgreenSheets. */
    private String vendorUnits;
    
    /** The rate is used for storing rate of the tblgreenSheets. */
    private String rate;
    
    /** The vendorRate is used for storing vendorrate of the tblgreenSheets. */
    private String vendorRate;
    
    /** The secondarySalesPerson is used for storing secondarysalesperson of the tblgreenSheets. */
    private String secondarySalesPerson;
    
    /** The scopeOfWork is used for storing scopeofwork of the tblgreenSheets. */
    private String scopeOfWork;
    
    /** The payementTerms is used for storing payementterms of the tblgreenSheets. */
    private String payementTerms;
    
    /** The invoicing is used for storing invoicing of the tblgreenSheets. */
    private String invoicing;
    
    /** The vpSalesApproved is used for storing vpsalesapproved of the tblgreenSheets. */
    private String vpSalesApproved;
    
    /** The venUnitRate is used for storing venunitrate of the tblgreenSheets. */
    private double venUnitRate;
    
    /** The priSalesPersonId is used for storing priSalespersonid of the tblgreenSheets. */
    private int priSalesPersonId;
    
    /** The secSalesPersonId is used for storing secSalespersonid of the tblgreenSheets. */
    private int secSalesPersonId;
    
    /** The reportingPhone is used for storing reportingphone of the tblgreenSheets. */
    private String reportingPhone;
    
    /** The resultType is used for storing resulttype of the tblgreenSheets. */
    private String resultType;
    
    /** The operationType is used for storing operationtype of the tblgreenSheets. */
    private String operationType;
    
    /** The resultMessage is used for storing resultmessage of the tblgreenSheets. */
    private String resultMessage;
    
    /** The id is used for storing id of the tblgreenSheets. */
    private int id;
    
    //To Show the Customer Name(Account Name) on top of the page to navigate
    /** The accountName is used for storing accountname of the tblgreenSheets. */
    private String accountName;
    
    /** The accountId is used for storing accountid of the tblgreenSheets. */
    private int accountId;
    
    /** The cusName is used for storing cusname of the tblgreenSheets. */
    private String cusName;
    
    //private String secondarySalesPerson;
    //private double secondarySalesPersonCommission;
    
    private String poStartDateFrom;
    private String poStartDateTo;
    private String poEndDateFrom;
    private String poEndDateTo;
    private String empStartDateFrom;
    private String empStartDateTo;
    private String empEndDateFrom;
    private String empEndDateTo;

  /*
    // for newAccount
   
   
    //From Session
    private String createdBy;
    private String modifiedBy;
    private String activityBY;
    private Timestamp dateLastActivity;
    //Default Time
    private Timestamp accountCreatedDate;
    private Timestamp dateModified;
   
   
    // Form Variables
   
    private int primaryAddressId;
   
    private int accountTeam;
   
    private String accountType;
    private String accountName;
    private String synonyms;
   
    private String region;
    private String territory;
    private String industry;
    // private String status;
   
    private String stockSymbol1;
    private String stockSymbol2;
   
    private double revenues;
    private int noOfEmployees;
    private double itBudget;
   
    private String taxId;
    private String leadSource;
   
    private String addressType;
    private String city;
    private String state;
    private String country;
    private String mailStop;
    private String zip;
    private String addressLine1;
    private String addressLine2;
   
   
    //   String phone;
    private String fax;
    private String url;
   
    private String description;
   */
    
    
    
    
    // for edit purpose
    
    private int greenSheetId;
    
    public int getGreenSheetId() {
        return greenSheetId;
    }
    public void setGreenSheetId(int greenSheetId) {
        this.greenSheetId=greenSheetId;
    }
    public String getConsultantType() {
        return consultantType;
    }
    
    public void setConsultantType(String consultantType) {
        this.consultantType = consultantType;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
   
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getMiddleName() {
        return middleName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public java.sql.Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }
    
    public java.sql.Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }
    
    public String getReportingManager() {
        return reportingManager;
    }
    
    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }
    
    public String getReportingAddress() {
        return reportingAddress;
    }
    
    public void setReportingAddress(String reportingAddress) {
        this.reportingAddress = reportingAddress;
    }
    
    public String getReportingManagerEmail() {
        return reportingManagerEmail;
    }
    
    public void setReportingManagerEmail(String reportingManagerEmail) {
        this.reportingManagerEmail = reportingManagerEmail;
    }
    
    public String getExpenses() {
        return expenses;
    }
    
    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }
    
    public String getClientBillingRate() {
        return clientBillingRate;
    }
    
    public void setClientBillingRate(String clientBillingRate) {
        this.clientBillingRate = clientBillingRate;
    }
    
    public Date getDateCreated() {
        return dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    public String getExpensesDetails() {
        return expensesDetails;
    }
    
    public void setExpensesDetails(String expensesDetails) {
        this.expensesDetails = expensesDetails;
    }
    
    public String getEquipmentNeeded() {
        return equipmentNeeded;
    }
    
    public void setEquipmentNeeded(String equipmentNeeded) {
        this.equipmentNeeded = equipmentNeeded;
    }
    
    public String getOtAllowed() {
        return otAllowed;
    }
    
    public void setOtAllowed(String otAllowed) {
        this.otAllowed = otAllowed;
    }
    
    public String getAgencyName() {
        return agencyName;
    }
    
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    
    public String getVendorPhone() {
        return vendorPhone;
    }
    
    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }
    
    public String getVendorEmail() {
        return vendorEmail;
    }
    
    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }
    
    public String getVendorContactPerson() {
        return vendorContactPerson;
    }
    
    public void setVendorContactPerson(String vendorContactPerson) {
        this.vendorContactPerson = vendorContactPerson;
    }
    
    public String getBillingManager() {
        return billingManager;
    }
    
    public void setBillingManager(String billingManager) {
        this.billingManager = billingManager;
    }
    
    public String getBillingphone() {
        return billingphone;
    }
    
    public void setBillingphone(String billingphone) {
        this.billingphone = billingphone;
    }
    
    public String getBillingAddress() {
        return billingAddress;
    }
    
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
    
    public String getBillingManagerEmail() {
        return billingManagerEmail;
    }
    
    public void setBillingManagerEmail(String billingManagerEmail) {
        this.billingManagerEmail = billingManagerEmail;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPoStatus() {
        return poStatus;
    }
    
    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus;
    }
    
    public double getDuration() {
        return duration;
    }
    
    public void setDuration(double duration) {
        this.duration = duration;
    }
    
    
    public String getPrimarySalesPerson() {
        return primarySalesPerson;
    }
    
    public void setPrimarySalesPerson(String primarySalesPerson) {
        this.primarySalesPerson = primarySalesPerson;
    }
    
    public double getPriSalesPersonCommission() {
        return priSalesPersonCommission;
    }
    
    public void setPriSalesPersonCommission(double priSalesPersonCommission) {
        this.priSalesPersonCommission = priSalesPersonCommission;
    }
    
    public String getPrimarySalesManager() {
        return primarySalesManager;
    }
    
    public void setPrimarySalesManager(String primarySalesManager) {
        this.primarySalesManager = primarySalesManager;
    }
    
    public double getPriSalesMngCommission() {
        return priSalesMngCommission;
    }
    
    public void setPriSalesMngCommission(double priSalesMngCommission) {
        this.priSalesMngCommission = priSalesMngCommission;
    }
    
    public double getSecondarySalesPersonCommission() {
        return secondarySalesPersonCommission;
    }
    
    public void setSecondarySalesPersonCommission(double secondarySalesPersonCommission) {
        this.secondarySalesPersonCommission = secondarySalesPersonCommission;
    }
    
    
    
    public String getRate() {
        return rate;
    }
    
    public void setRate(String rate) {
        this.rate = rate;
    }
    
    public String getSecondarySalesPerson() {
        return secondarySalesPerson;
    }
    
    public void setSecondarySalesPerson(String secondarySalesPerson) {
        this.secondarySalesPerson = secondarySalesPerson;
    }
    
    public String getScopeOfWork() {
        return scopeOfWork;
    }
    
    public void setScopeOfWork(String scopeOfWork) {
        this.scopeOfWork = scopeOfWork;
    }
    
    public String getPayementTerms() {
        return payementTerms;
    }
    
    public void setPayementTerms(String payementTerms) {
        this.payementTerms = payementTerms;
    }
    
    public String getInvoicing() {
        return invoicing;
    }
    
    public void setInvoicing(String invoicing) {
        this.invoicing = invoicing;
    }
    
    public String getVpSalesApproved() {
        return vpSalesApproved;
    }
    
    public void setVpSalesApproved(String vpSalesApproved) {
        this.vpSalesApproved = vpSalesApproved;
    }
    
    public String getEmpId() {
        return empId;
    }
    
    public void setEmpId(String empId) {
        this.empId = empId;
    }
    
    
    public double getPriBDMCommission() {
        return priBDMCommission;
    }
    
    public void setPriBDMCommission(double priBDMCommission) {
        this.priBDMCommission = priBDMCommission;
    }
    
    /* setter and getters for lookup tables */
    
    public Map getSalesMap() {
        return salesMap;
    }
    
    public void setSalesMap(Map salesMap) {
        this.salesMap = salesMap;
    }
    
    
    
    
    public double getVenUnitRate() {
        return venUnitRate;
    }
    
    public void setVenUnitRate(double venUnitRate) {
        this.venUnitRate = venUnitRate;
    }
    
    public int getPriSalesPersonId() {
        return priSalesPersonId;
    }
    
    public void setPriSalesPersonId(int priSalesPersonId) {
        this.priSalesPersonId = priSalesPersonId;
    }
    
    public int getSecSalesPersonId() {
        return secSalesPersonId;
    }
    
    public void setSecSalesPersonId(int secSalesPersonId) {
        this.secSalesPersonId = secSalesPersonId;
    }
    
    public String getConsultantId() {
        return consultantId;
    }
    
    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }
    
    public String getUnits() {
        return units;
    }
    
    public void setUnits(String units) {
        this.units = units;
    }
    
    public String getPriBDMId() {
        return priBDMId;
    }
    
    public void setPriBDMId(String priBDMId) {
        this.priBDMId = priBDMId;
    }
    
    public String getVendorUnits() {
        return vendorUnits;
    }
    
    public void setVendorUnits(String vendorUnits) {
        this.vendorUnits = vendorUnits;
    }
    
    public String getReportingPhone() {
        return reportingPhone;
    }
    
    public void setReportingPhone(String reportingPhone) {
        this.reportingPhone = reportingPhone;
    }
    
    public Map getUnitsMap() {
        return unitsMap;
    }
    
    public void setUnitsMap(Map unitsMap) {
        this.unitsMap = unitsMap;
    }
    
    
    public Map getClientCurrencyMap() {
        return clientCurrencyMap;
    }
    
    public void setClientCurrencyMap(Map clientCurrencyMap) {
        this.clientCurrencyMap = clientCurrencyMap;
    }
    
    public List getGreensheetStautsList() {
        return greensheetStautsList;
    }
    
    public void setGreensheetStautsMap(List greensheetStautsList) {
        this.setGreensheetStautsList(greensheetStautsList);
    }
    
    public List getGreensheetPOStautsList() {
        return greensheetPOStautsList;
    }
    
    public void setGreensheetPOStautsList(List greensheetPOStautsList) {
        this.greensheetPOStautsList = greensheetPOStautsList;
    }
    
    
    
    public Map getGreensheetCustomerNamesMap() {
        return greensheetCustomerNamesMap;
    }
    
    public void setGreensheetCustomerNamesMap(Map greensheetCustomerNamesMap) {
        this.greensheetCustomerNamesMap = greensheetCustomerNamesMap;
    }
    
    public String getVendorRate() {
        return vendorRate;
    }
    
    public void setVendorRate(String vendorRate) {
        this.vendorRate = vendorRate;
    }
    
    public void setGreensheetStautsList(List greensheetStautsList) {
        this.greensheetStautsList = greensheetStautsList;
    }
    
    
    public List getExpencesList() {
        return expencesList;
    }
    
    public void setExpencesList(List expencesList) {
        this.expencesList = expencesList;
    }
    
    
    public List getGreensheetVPSalesList() {
        return greensheetVPSalesList;
    }
    
    public void setGreensheetVPSalesList(List greensheetVPSalesList) {
        this.greensheetVPSalesList = greensheetVPSalesList;
    }
    
    public GreenSheetVTO getGreensheetVTO() {
        return greensheetVTO;
    }
    
    public void setGreensheetVTO(GreenSheetVTO greensheetVTO) {
        this.greensheetVTO = greensheetVTO;
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest=httpServletRequest;
    }
    
    public List getGreensheetVendorNamesList() {
        return greensheetVendorNamesList;
    }
    
    public void setGreensheetVendorNamesList(List greensheetVendorNamesList) {
        this.greensheetVendorNamesList = greensheetVendorNamesList;
    }
    
    public String getCustName() {
        return custName;
    }
    
    public void setCustName(String custName) {
        this.custName = custName;
    }
    
    public String getPoStatusType() {
        return poStatusType;
    }
    
    public void setPoStatusType(String poStatusType) {
        this.poStatusType = poStatusType;
    }
    
    public String getClientBillingRateType() {
        return clientBillingRateType;
    }
    
    public void setClientBillingRateType(String clientBillingRateType) {
        this.clientBillingRateType = clientBillingRateType;
    }
    
    public List getAccountTypeList() {
        return accountTypeList;
    }
    
    public void setAccountTypeList(List accountTypeList) {
        this.accountTypeList = accountTypeList;
    }
    
    public List getAccountStatusList() {
        return accountStatusList;
    }
    
    public void setAccountStatusList(List accountStatusList) {
        this.accountStatusList = accountStatusList;
    }
    
    public List getIndustryList() {
        return industryList;
    }
    
    public void setIndustryList(List industryList) {
        this.industryList = industryList;
    }
    
    public List getRegionList() {
        return regionList;
    }
    
    public void setRegionList(List regionList) {
        this.regionList = regionList;
    }
    
    public List getTerritoryList() {
        return territoryList;
    }
    
    public void setTerritoryList(List territoryList) {
        this.territoryList = territoryList;
    }
    
    public Map getGreensheetScopeOfWork() {
        return greensheetScopeOfWork;
    }
    
    public void setGreensheetScopeOfWork(Map greensheetScopeOfWork) {
        this.greensheetScopeOfWork = greensheetScopeOfWork;
    }
    
    public Map getGreensheetPaymentTerms() {
        return greensheetPaymentTerms;
    }
    
    public void setGreensheetPaymentTerms(Map greensheetPaymentTerms) {
        this.greensheetPaymentTerms = greensheetPaymentTerms;
    }
    
    
    public String getAccountName() {
        return accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
   /* public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public String getModifiedBy() {
        return modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    public String getActivityBY() {
        return activityBY;
    }
    
    public void setActivityBY(String activityBY) {
        this.activityBY = activityBY;
    }
    
    public Timestamp getDateLastActivity() {
        return dateLastActivity;
    }
    
    public void setDateLastActivity(Timestamp dateLastActivity) {
        this.dateLastActivity = dateLastActivity;
    }
    
    public Timestamp getAccountCreatedDate() {
        return accountCreatedDate;
    }
    
    public void setAccountCreatedDate(Timestamp accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }
    
    public Timestamp getDateModified() {
        return dateModified;
    }
    
    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }
    
    public int getPrimaryAddressId() {
        return primaryAddressId;
    }
    
    public void setPrimaryAddressId(int primaryAddressId) {
        this.primaryAddressId = primaryAddressId;
    }
    
    
    public int getAccountTeam() {
        return accountTeam;
    }
    
    public void setAccountTeam(int accountTeam) {
        this.accountTeam = accountTeam;
    }
    
    public String getAccountType() {
        return accountType;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    
    
    public String getSynonyms() {
        return synonyms;
    }
    
    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }
    
    public String getRegion() {
        return region;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }
    
    public String getTerritory() {
        return territory;
    }
    
    public void setTerritory(String territory) {
        this.territory = territory;
    }
    
    public String getIndustry() {
        return industry;
    }
    
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
    public String getStockSymbol1() {
        return stockSymbol1;
    }
    
    public void setStockSymbol1(String stockSymbol1) {
        this.stockSymbol1 = stockSymbol1;
    }
    
    public String getStockSymbol2() {
        return stockSymbol2;
    }
    
    public void setStockSymbol2(String stockSymbol2) {
        this.stockSymbol2 = stockSymbol2;
    }
    
    public double getRevenues() {
        return revenues;
    }
    
    public void setRevenues(double revenues) {
        this.revenues = revenues;
    }
    
    public int getNoOfEmployees() {
        return noOfEmployees;
    }
    
    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }
    
    public double getItBudget() {
        return itBudget;
    }
    
    public void setItBudget(double itBudget) {
        this.itBudget = itBudget;
    }
    
    public String getTaxId() {
        return taxId;
    }
    
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }
    
    public String getLeadSource() {
        return leadSource;
    }
    
    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }
    
    public String getAddressType() {
        return addressType;
    }
    
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getMailStop() {
        return mailStop;
    }
    
    public void setMailStop(String mailStop) {
        this.mailStop = mailStop;
    }
    
    public String getZip() {
        return zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public String getAddressLine1() {
        return addressLine1;
    }
    
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    
    public String getAddressLine2() {
        return addressLine2;
    }
    
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    
    public String getFax() {
        return fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    */
    public String getResultType() {
        return resultType;
    }
    
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
    
    public String getOperationType() {
        return operationType;
    }
    
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    
    public String getResultMessage() {
        return resultMessage;
    }
    
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    
    public String getUploadContentType() {
        return uploadContentType;
    }
    
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    
    public String getUploadFileName() {
        return uploadFileName;
    }
    
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
    
    public String getRequestType() {
        return requestType;
    }
    
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    
    public String getAttachmentName() {
        return attachmentName;
    }
    
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
    
    public String getObjectid() {
        return objectid;
    }
    
    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }
    
    public Timestamp getDate() {
        return date;
    }
    
    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    public String getFilepath() {
        return filepath;
    }
    
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    
    public String getFileLocation() {
        return fileLocation;
    }
    
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
    
    public File getUpload() {
        return upload;
    }
    
    public void setUpload(File upload) {
        this.upload = upload;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getReportingMGRPhone() {
        return reportingMGRPhone;
    }
    
    public void setReportingMGRPhone(String reportingMGRPhone) {
        this.reportingMGRPhone = reportingMGRPhone;
    }
    
    public String getCusName() {
        return cusName;
    }
    
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }
    
    public int getAccountId() {
        return accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }
    
    public Map getSalesManagerMap() {
        return salesManagerMap;
    }
    
    public void setSalesManagerMap(Map salesManagerMap) {
        this.salesManagerMap = salesManagerMap;
    }
    
    // added
    
    public String getEmpCode() {
        return empCode;
    }
    
    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
    
    public String getRenewalEmail() {
        return renewalEmail;
    }
    
    public void setRenewalEmail(String renewalEmail) {
        this.renewalEmail = renewalEmail;
    }
    
    public String getRenewalIntEmail() {
        return renewalIntEmail;
    }
    
    public void setRenewalIntEmail(String renewalIntEmail) {
        this.renewalIntEmail = renewalIntEmail;
    }
    
    public String getPoType() {
        return poType;
    }
    
    public void setPoType(String poType) {
        this.poType = poType;
    }
    
    public String getPoNumber() {
        return poNumber;
    }
    
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }
    
    public String getPoLineno() {
        return poLineno;
    }
    
    public void setPoLineno(String poLineno) {
        this.poLineno = poLineno;
    }
    
    public String getIntRefcode() {
        return intRefcode;
    }
    
    public void setIntRefcode(String intRefcode) {
        this.intRefcode = intRefcode;
    }
    
    public String getMaxQuantity() {
        return maxQuantity;
    }
    
    public void setMaxQuantity(String maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
    
    public double getTotalValue() {
        return totalValue;
    }
    
    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public java.sql.Date getConsStartDate() {
        return consStartDate;
    }
    
    public void setConsStartDate(java.sql.Date consStartDate) {
        this.consStartDate = consStartDate;
    }
    
    public java.sql.Date getConsEndDate() {
        return consEndDate;
    }
    
    public void setConsEndDate(java.sql.Date consEndDate) {
        this.consEndDate = consEndDate;
    }
    
    public String getNocValue() {
        return nocValue;
    }
    
    public void setNocValue(String nocValue) {
        this.nocValue = nocValue;
    }
    
    public java.sql.Date getNocDate() {
        return nocDate;
    }
    
    public void setNocDate(java.sql.Date nocDate) {
        this.nocDate = nocDate;
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public int getConsultantTypeId() {
        return consultantTypeId;
    }
    
    public void setConsultantTypeId(int consultantTypeId) {
        this.consultantTypeId = consultantTypeId;
    }
    
    public String getPrimaryVicePresident() {
        return primaryVicePresident;
    }
    
    public void setPrimaryVicePresident(String primaryVicePresident) {
        this.primaryVicePresident = primaryVicePresident;
    }
    
    public double getPrimaryVicePresidentCommission() {
        return primaryVicePresidentCommission;
    }
    
    public void setPrimaryVicePresidentCommission(double primaryVicePresidentCommission) {
        this.primaryVicePresidentCommission = primaryVicePresidentCommission;
    }
    
    public String getSecondaryVicePresident() {
        return secondaryVicePresident;
    }
    
    public void setSecondaryVicePresident(String secondaryVicePresident) {
        this.secondaryVicePresident = secondaryVicePresident;
    }
    
    public double getSecondaryVicePresidentCommission() {
        return secondaryVicePresidentCommission;
    }
    
    public void setSecondaryVicePresidentCommission(double secondaryVicePresidentCommission) {
        this.secondaryVicePresidentCommission = secondaryVicePresidentCommission;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public String getModifedBy() {
        return modifedBy;
    }
    
    public void setModifedBy(String modifedBy) {
        this.modifedBy = modifedBy;
    }
    
    /**
     * The method getDateCreated is used to get date and time from the local system
     */
    public Timestamp getDateCreatedOn() {
        return dateCreatedOn;
    }
    
    /**
     * The method setDateCreated is used to set date and time from the local system
     */
    public void setDateCreatedOn(Timestamp dateCreatedOn) {
        this.dateCreatedOn = dateCreatedOn;
    }
    
    /**
     * The method getDateModified is used to get the modified date from the local-system for the account
     */
    public Timestamp getDateModifiedOn() {
        return dateModifiedOn;
    }
    
    /**
     * The method setDateModified is used to set the modified date from the local-system for the account
     */
    public void setDateModifiedOn(Timestamp dateModifiedOn) {
        this.dateModifiedOn = dateModifiedOn;
    }
    
    /////
    
    
    
    public String getInvoiceNo() {
        return invoiceNo;
    }
    
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
    
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public String getCustomerLocation() {
        return customerLocation;
    }
    
    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }
    
    public java.sql.Date getResaleDate() {
        return resaleDate;
    }
    
    public void setResaleDate(java.sql.Date resaleDate) {
        this.resaleDate = resaleDate;
    }
    
    public String getSoftDetails() {
        return softDetails;
    }
    
    public void setSoftDetails(String softDetails) {
        this.softDetails = softDetails;
    }
    
    public String getCustomerPrice() {
        return customerPrice;
    }
    
    public void setCustomerPrice(String customerPrice) {
        this.customerPrice = customerPrice;
    }
    
    public String getAvnetPrice() {
        return avnetPrice;
    }
    
    public void setAvnetPrice(String avnetPrice) {
        this.avnetPrice = avnetPrice;
    }
    
    public String getIbmIcsf() {
        return ibmIcsf;
    }
    
    public void setIbmIcsf(String ibmIcsf) {
        this.ibmIcsf = ibmIcsf;
    }
    
    public String getAddServices() {
        return addServices;
    }
    
    public void setAddServices(String addServices) {
        this.addServices = addServices;
    }
    
    public String getSalesTax() {
        return salesTax;
    }
    
    public void setSalesTax(String salesTax) {
        this.salesTax = salesTax;
    }
    
    public String getSalesTaxState() {
        return salesTaxState;
    }
    
    public void setSalesTaxState(String salesTaxState) {
        this.salesTaxState = salesTaxState;
    }
    
    public String getProfitAmt() {
        return profitAmt;
    }
    
    public void setProfitAmt(String profitAmt) {
        this.profitAmt = profitAmt;
    }
    
    public String getProfitPercent() {
        return profitPercent;
    }
    
    public void setProfitPercent(String profitPercent) {
        this.profitPercent = profitPercent;
    }
    
    public String getResultMessageList() {
        return resultMessageList;
    }
    
    public void setResultMessageList(String resultMessageList) {
        this.resultMessageList = resultMessageList;
    }
    
    public Map getMyTeamMembers() {
        return myTeamMembers;
    }
    
    public void setMyTeamMembers(Map myTeamMembers) {
        this.myTeamMembers = myTeamMembers;
    }
    
    public String getTeamGreensheets() {
        return teamGreensheets;
    }
    
    public void setTeamGreensheets(String teamGreensheets) {
        this.teamGreensheets = teamGreensheets;
    }
    
    public String getMspVendor() {
        return mspVendor;
    }
    
    public void setMspVendor(String mspVendor) {
        this.mspVendor = mspVendor;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * 
     * @return String
     */
    public String getModeType() {
        return modeType;
    }
    
    /**
     * 
     * @param modeType 
     */
    public void setModeType(String modeType) {
        this.modeType = modeType;
    }

    public String getPoStartDateFrom() {
        return poStartDateFrom;
    }

    public void setPoStartDateFrom(String poStartDateFrom) {
        this.poStartDateFrom = poStartDateFrom;
    }

    public String getPoStartDateTo() {
        return poStartDateTo;
    }

    public void setPoStartDateTo(String poStartDateTo) {
        this.poStartDateTo = poStartDateTo;
    }

    public String getPoEndDateFrom() {
        return poEndDateFrom;
    }

    public void setPoEndDateFrom(String poEndDateFrom) {
        this.poEndDateFrom = poEndDateFrom;
    }

    public String getPoEndDateTo() {
        return poEndDateTo;
    }

    public void setPoEndDateTo(String poEndDateTo) {
        this.poEndDateTo = poEndDateTo;
    }

    public String getEmpStartDateFrom() {
        return empStartDateFrom;
    }

    public void setEmpStartDateFrom(String empStartDateFrom) {
        this.empStartDateFrom = empStartDateFrom;
    }

    public String getEmpStartDateTo() {
        return empStartDateTo;
    }

    public void setEmpStartDateTo(String empStartDateTo) {
        this.empStartDateTo = empStartDateTo;
    }

    public String getEmpEndDateFrom() {
        return empEndDateFrom;
    }

    public void setEmpEndDateFrom(String empEndDateFrom) {
        this.empEndDateFrom = empEndDateFrom;
    }

    public String getEmpEndDateTo() {
        return empEndDateTo;
    }

    public void setEmpEndDateTo(String empEndDateTo) {
        this.empEndDateTo = empEndDateTo;
    }
    
    /** New NAg **/
     /** new **/
    
    public String getContactFaxNumber() {
		return contactFaxNumber;
	}

	public void setContactFaxNumber(String contactFaxNumber) {
		this.contactFaxNumber = contactFaxNumber;
	}

	/*public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}*/

	public String getInvAttnComments() {
		return invAttnComments;
	}

	public void setInvAttnComments(String invAttnComments) {
		this.invAttnComments = invAttnComments;
	}

	public String getInvFaxNumber() {
		return invFaxNumber;
	}

	public void setInvFaxNumber(String invFaxNumber) {
		this.invFaxNumber = invFaxNumber;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getTypeOfResale() {
		return typeOfResale;
	}

	public void setTypeOfResale(String typeOfResale) {
		this.typeOfResale = typeOfResale;
	}

	public String getVendorComments() {
		return vendorComments;
	}

	public void setVendorComments(String vendorComments) {
		this.vendorComments = vendorComments;
	}

	public String getVendorFaxNumber() {
		return vendorFaxNumber;
	}

	public void setVendorFaxNumber(String vendorFaxNumber) {
		this.vendorFaxNumber = vendorFaxNumber;
	}

	public String getVendorPaymentAddress() {
		return vendorPaymentAddress;
	}

	public void setVendorPaymentAddress(String vendorPaymentAddress) {
		this.vendorPaymentAddress = vendorPaymentAddress;
	}

	public String getVendorTaxId() {
		return vendorTaxId;
	}

	public void setVendorTaxId(String vendorTaxId) {
		this.vendorTaxId = vendorTaxId;
	}
        public String getRejectesReason() {
		return rejectesReason;
	}

	public void setRejectesReason(String rejectesReason) {
		this.rejectesReason = rejectesReason;
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
     * @return the accountEdit
     */
    public String getAccountEdit() {
        return accountEdit;
    }

    /**
     * @param accountEdit the accountEdit to set
     */
    public void setAccountEdit(String accountEdit) {
        this.accountEdit = accountEdit;
    }

    /**
     * @return the accId
     */
    public String getAccId() {
        return accId;
    }

    /**
     * @param accId the accId to set
     */
    public void setAccId(String accId) {
        this.accId = accId;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }
}
