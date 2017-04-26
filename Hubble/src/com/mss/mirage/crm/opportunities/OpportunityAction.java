/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  October 8, 2007, 3:43 PM
 *
 * Author  : MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * File    : OpportunityAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.opportunities;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataUtility;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import org.apache.struts2.interceptor.ServletRequestAware;


/**
 * The <code>OpportunityAction</code>  class is used for getting  details from
 * <i>OpportunityAdd.jsp</i> page.
 * <p>
 * Then it invokes setter methods in <code>OpportunityVTO</code> class and invokes doEdit() method
 * in <code>OpportunityAction</code> for inserting employee details in TBLCRMOPPORTUNITY table.
 *
 * @author Charan Raj Devarakonda <a href="mailto:cdevarakonda@miraclesoft.com">cdevarakonda@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.crm.opportunities.OpportunityAction
 * @see com.mss.mirage.crm.opportunities.OpportunityService
 * @see com.mss.mirage.crm.opportunities.OpportunityServiceImpl
 * @see com.mss.mirage.crm.opportunities.OpportunityVTO
 */
public class OpportunityAction extends ActionSupport implements ServletRequestAware{
    
    /** The hibernateDataProvider is   object reference of ApplicationDataProvider   for the accessing  CONSTANT values  . */
    
    private HibernateDataProvider hibernateDataProvider;
    
    private DataSourceDataProvider dataSourceDataProvider;
    
    /** The httpServletRequest is used for storing the request from the Client . */
    private HttpServletRequest httpServletRequest;
    
    /** The currentOpportunity is a reference object of OpportunityVTO . */
    private OpportunityVTO currentOpportunity;
    
    /** The opportunityService is a reference object of OpportunityService . */
    private OpportunityService opportunityService;
    
    /** The actionString is used for storing the action of the jsp class . */
    private String actionString ="addOpportunity";
    
    /** The accountName is used for storing the  names  of the Clients. */
    private String accountName;
    
    /** The isUpdate is used for storing the boolean value after the update() . */
    private boolean isUpdate;
    
    /** The resultType is used for storing the message,after performing the bussiness logic . */
    private String resultType;
    
    /** The resultMessage is used for storing the message,after performing the bussiness logic . */
    private String resultMessage;
    
    /**
     * A Map object with an insideSalesMap object  and read from a
     * full List of data....
     */
    private Map insideSalesMap;
    
    /**
     * A Map object with an bdmMap object  and read from a
     * full List of data....
     */
    private Map bdmMap;
    
    /**
     * A Map object with an regionalMgrMap object  and read from a
     * full List of data....
     */
    private Map regionalMgrMap;
    
    /**
     * A Map object with an vpMap object  and read from a
     * full List of data....
     */
    private Map vpMap;
    
    /**
     * A Map object with an offshorePMMap object  and read from a
     * full List of data....
     */
    private Map offshorePMMap;
    
    /**
     * A Map object with an onsitePMMap object  and read from a
     * full List of data....
     */
    private Map onsitePMMap;
    
    /**
     * A Map object with an architectMap object  and read from a
     * full List of data....
     */
    private Map architectMap;
    
    /**
     * A List object with an typeList object  and read from a
     * full List of data....
     */
    private List typeList = new ArrayList();
    
    /**
     * A List object with an stageList object  and read from a
     * full List of data....
     */
    private List stageList =  new ArrayList();
    
    /** The id is used for storing the primary value of the table. */
    private int id;
    
    /** The accountId is used for storing the account name Id. */
    private int accountId;
    
    /** The title is used for storeding the title of the corresponding Employee. */
    private String title;
    
    /** The description  is used for storing the description or comments of the particular employee. */
    private String description;
    
    /** The value  is used for storing the value of the particular Employee. */
    private double value;
    
    /** The type is used for storing  what type of the work is done by the particular employee. */
    private String type;
    
    /** The stage is used for storing the data in which stage the current employee is working. */
    private String stage;
    
    
    /** The dueDate is used for storing the due Date . */
    private String dueDate;
    
    /** The dueDateTimestamp is used for storing the dueDateTimestamp . */
    private Timestamp dueDateTimestamp;
    
    /** The createdDate is used for storing the current Date . */
    private Timestamp createdDate;
    
    /** The insideSalesId is used for storeing the id  of the insideSales Employee. */
    private int  insideSalesId;
    
    /** The bdmId is used for storeing the id  of the bdm Employee. */
    private int bdmId;
    
    /** The regionalMgrId is used for storeing the id  of the regionalMgr Employee. */
    private int regionalMgrId;
    
    /** The vpId is used for storeing the id  of the vp Employee. */
    private int vpId;
    
    /** The offshorePMId is used for storeing the id  of the offshorePM Employee. */
    private int offshorePMId;
    
    /** The practiceMgrId is used for storeing the id  of the practiceMgr Employee. */
    private int practiceMgrId;
    
    /** The architectId is used for storeing the id  of the architect Employee. */
    private int architectId;
    
    private int userRoleId;
    
    private String accId;
    
    private String createdBy;
    
    private String modifiedBy;
    
    private Timestamp modifiedDate;
    private List practiceList;
    private String practiceName;
    private Map practiceSalesMap= new HashMap();
     private Map insideSalesMap1= new HashMap();
     private Map fieldSalesMap= new HashMap();
     private List opportunityStateList;
      private String state;
private Map contactsMap;
private String contactsHidden;
private int leadSourceId;

    public int getLeadId() {
        return leadId;
    }

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }
  private int leadId;
private Map leadsMap= new HashMap(); 
 private List practiceIdList = new ArrayList();
 
 private String sviList;
private String sviNum;
 
  private int previousLeadSourceId;
 private String leadTitle;
 
 private int previousInsideSalesId;
private int previousRegionalMgrId;
private int previousPracticeMgrId;

private String leadOperationType;
private String leadStatus;
    /** Creates a new instance of OpportunityAction */
    public OpportunityAction() {
    }
    
    /**
     * The execute() is used for performing the bussiness logic of the OpportunityAdd.jsp page.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String execute() throws Exception{
        return SUCCESS;
    }
    
    /**
     * The prepare() is used for sending lookUp data  OpportunityAdd.jsp page.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String prepare(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_OPPORTUNITY",userRoleId)){
                try{
                    
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    
                    
                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *
                     *  {@link com.mss.mirage.ApplicationDataProvider}
                     *
                     *  @return   The InsideSalesMap  returned  depends on the TITLE_TYPE_OPTION
                     */
                  //  setInsideSalesMap(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Jr.Sales Executive","Sr. Sales Executive"));
                      setInsideSalesMap(DataUtility.getInstance().getMapSortByValue(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Jr. Sales Executive","Sr. Sales Executive","Sales trainee","BDE")));
                    
                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *
                     *  {@link com.mss.mirage.ApplicationDataProvider}
                     *
                     *  @return   The BdmMap(  returned  depends on the TITLE_TYPE_OPTION
                     */
                    setBdmMap(DataUtility.getInstance().getMapSortByValue(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("BDM")));
                    
                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *
                     *  {@link com.mss.mirage.ApplicationDataProvider}
                     *
                     *  @return   The RegionalMgrMap(  returned  depends on the TITLE_TYPE_OPTION
                     */
                    //setRegionalMgrMap(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Jr. Manager","Sr. Manager")); before 23-10-2015
                   /*  23-10-2015
                           setRegionalMgrMap(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Jr. Manager","Sr. Manager","Jr. Practice Lead","Sr. Practice Lead"));
                     
                     */
                    setRegionalMgrMap(DataUtility.getInstance().getMapSortByValue(dataSourceDataProvider.getPracticesalesMap()));
                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *
                     *  {@link com.mss.mirage.ApplicationDataProvider}
                     *
                     *  @return   The VpMap(  returned  depends on the TITLE_TYPE_OPTION
                     */
                    setVpMap(DataUtility.getInstance().getMapSortByValue(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Vice President","Sr. Vice President")));
                    
                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *
                     *  {@link com.mss.mirage.ApplicationDataProvider}
                     *
                     *  @return   The OffshorePMp(  returned  depends on the TITLE_TYPE_OPTION
                     */
                    //setOffshorePMMap(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Practice Manager","Project Manager")); 
                    setOffshorePMMap(DataUtility.getInstance().getMapSortByValue(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Practice Manager","Project Manager","Jr. Practice Manager","Sr. Practice Manager")));
                    
                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *
                     *  {@link com.mss.mirage.ApplicationDataProvider}
                     *
                     *  @return   The OnsitePMMap(  returned  depends on the TITLE_TYPE_OPTION
                     */
                    
                    /*old
                    setOnsitePMMap(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Practice Manager","Project Manager"));
                    */
                    setOnsitePMMap(DataUtility.getInstance().getMapSortByValue(dataSourceDataProvider.getPresalesMap()));
                    /**
                     *  Populates user required options to the Screens depending on the  options.
                     *
                     *  {@link com.mss.mirage.ApplicationDataProvider}
                     *
                     *  @return   The ArchitectMap(  returned  depends on the TITLE_TYPE_OPTION
                     */
                    setArchitectMap(DataUtility.getInstance().getMapSortByValue(dataSourceDataProvider.getEmployeeNamesUserIdByTitle("Jr.Architect","Sr. Architect")));
                    
                    
                    
                    setTypeList(hibernateDataProvider.getOpportunityType(ApplicationConstants.CRM_OPS_TYPE_OPTIONS));
                    
                    setStageList(hibernateDataProvider.getStage(ApplicationConstants.CRM_OPS_STAGE_OPTIONS));
                    
                    setAccountName(hibernateDataProvider.getAccountName(getAccountId()));
                      // practice list by department and type
                    setPracticeList(DataSourceDataProvider.getInstance().getPracticeNamesList(7,1));
                    getPracticeList().add("RENEWAL");

                    Collections.sort(getPracticeList());
                    setPracticeSalesMap(DataSourceDataProvider.getInstance().getEmployeeByDepartmentAndPractice("Sales","Practice"));
                    setInsideSalesMap1(DataSourceDataProvider.getInstance().getEmployeeByDepartmentAndPractice("Sales","Inside"));
                    setFieldSalesMap(DataSourceDataProvider.getInstance().getEmployeeByDepartmentAndPractice("Sales","Field"));
                    setOpportunityStateList(DataSourceDataProvider.getInstance().getOpportunityStateList());
                    setContactsMap(DataSourceDataProvider.getInstance().getContactsNamesAgainstAccount(getAccountId()));
                    setLeadsMap(DataSourceDataProvider.getInstance().getLeadsMapByAccountId(getAccountId()));
                    if(getLeadSourceId()!=0){
                        OpportunityVTO opportunityVTO = new OpportunityVTO();
                        opportunityVTO.setLeadSourceId(getLeadSourceId());
                        setCurrentOpportunity(opportunityVTO);
                    }
                    if(httpServletRequest.getSession(false).getAttribute("accountName") != null){
                        httpServletRequest.getSession(false).removeAttribute("accountName");
                    }
                    httpServletRequest.getSession(false).setAttribute("accountName",getAccountName());
                    
                    
                    httpServletRequest.setAttribute("currentAccountId",String.valueOf(getAccountId()));
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session checkig
        return resultType;
    }
    
    /**
     * The getOpportunity() is used for getting th value of the Id from  the OpportunityAdd.jsp page.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String getOpportunity() {
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_OPPORTUNITY",userRoleId)){
                try{
                    setCurrentOpportunity(ServiceLocator.getOpportunityService().getOpportunity(getId()));
                    
                    setAccountId(getCurrentOpportunity().getAccountId());
                    
                    setActionString("editOpportunity");
                    setLeadId(getLeadId());
                    if(getCurrentOpportunity().getLeadSourceId()>0)
                    setLeadTitle(DataSourceDataProvider.getInstance().getLeadTitleByLeadId(getCurrentOpportunity().getLeadSourceId()));
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
    
    /**
     * The doAdd() is used for insert the data  from  the OpportunityAdd.jsp page into the databasetable.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    
  public String doAdd(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_OPPORTUNITY",userRoleId)){
                try{
                    isUpdate = false;
                    int opportunityId=0;
                    opportunityService = ServiceLocator.getOpportunityService();
                    
                    setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    if(dueDate.equals("")) {
                        setDueDateTimestamp(DateUtility.getInstance().strToTimeStampObj("2000-01-01 00:00:00"));
                    } else {
                        setDueDateTimestamp(DateUtility.getInstance().strToTimeStampObj(getDueDate()));
                    }
                    //setDueDateTimestamp(DateUtility.getInstance().strToTimeStampObj(getDueDate()));
                    String createdBy = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                  
                    //isUpdate = opportunityService.addOpportunity(this);
                     if(getLeadSourceId()!=0&&getLeadSourceId()!=-1){
                            //opportunityService.doUpdateLeadStaus(getLeadSourceId(),"Linked",createdBy,"Ins");
                          setLeadStatus("Linked");
                            setLeadOperationType("Ins");
                        }
                    opportunityId = opportunityService.addOrUpdateOpportunity(this);
                    
                  //  setId(DataSourceDataProvider.getInstance().getMaxCrmOpportunityId());
                      setId(opportunityId);
                    setAccountId(getAccountId());
                    //if(isUpdate){
                    if(opportunityId>0){
                        resultType = SUCCESS;
                        setActionString("editOpportunity");
                        setResultMessage("<font color=\"green\" size=\"1.5\">Opportunity has been successfully inserted!</font>");
                       // if(getLeadSourceId()!=0&&getLeadSourceId()!=-1){
                         //   opportunityService.doUpdateLeadStaus(getLeadSourceId(),"Linked",createdBy,"Ins");
                       // }
                        
                         dataSourceDataProvider = DataSourceDataProvider.getInstance();
                        String practiceSalesEmailId = "", preSalesEmailId = "", to = "";
                        int RegionalMgrId1 = -1, PracticeMgrId1 = -1, InsideSalesId1 = -1;
                        if (getRegionalMgrId() != -1) {
                            practiceSalesEmailId = dataSourceDataProvider.getEmailIdForEmployee(getRegionalMgrId());
                        }
                        if (getPracticeMgrId() != -1) {
                            preSalesEmailId = dataSourceDataProvider.getEmailIdForEmployee(getPracticeMgrId());
                        }

                        String insideSalesEmailId = dataSourceDataProvider.getEmailIdForEmployee(getInsideSalesId());
                        to = insideSalesEmailId.trim();
                        if (practiceSalesEmailId != null && !"".equals(practiceSalesEmailId)) {
                            to = to + "," + practiceSalesEmailId.trim();
                        }
                        if (preSalesEmailId != null && !"".equals(preSalesEmailId)) {
                            to = to + "," + preSalesEmailId.trim();
                        }
                       // System.out.println("to..." + to);
                        if(Properties.getProperty("Mail.Flag").equals("1")) {
                              MailManager sendMail = new MailManager();
                        sendMail.sendOpportunityMailToPracticeSalesAndPreSales(getTitle(), getState(), getType(), getPracticeName(), getDueDate(), getCreatedDate(), to, "Added", getRegionalMgrId(), getPracticeMgrId(), getInsideSalesId(), RegionalMgrId1, PracticeMgrId1, InsideSalesId1,getCreatedBy());
                        
                        }
                      
                    }else{
                        resultType = INPUT;
                        setActionString("addOpportunity");
                        setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>");
                    }
                    
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                    setAccountId(getAccountId());
                   // setCurrentOpportunity(opportunityService.getOpportunityVTO(this));
                  //  setAccountId(getCurrentOpportunity().getAccountId());
                    
                    resultType =  prepare();
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    /** The doEdit() is used for update the data  from  the OpportunityAdd.jsp page and save update data into the databasetable.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String doEdit() {
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_OPPORTUNITY",userRoleId)){
                try{
                    isUpdate = false;
                     setActionString("editOpportunity");
                    int opportunityId=0;
                    opportunityService = ServiceLocator.getOpportunityService();
                    if(dueDate.equals("")) {
                        setDueDateTimestamp(DateUtility.getInstance().strToTimeStampObj("2000-01-01 00:00:00"));
                    } else {
                        setDueDateTimestamp(DateUtility.getInstance().strToTimeStampObj(getDueDate()));
                    }
                    setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                    setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    //isUpdate = opportunityService.editOpportunity(this);
                    
                    if(!"Lost".equals(getState())&&((getLeadSourceId()!=0&&getLeadSourceId()!=-1&&getPreviousLeadSourceId()==-1)||((getPreviousLeadSourceId()!=getLeadSourceId())&&getPreviousLeadSourceId()!=-1))){
                          //  opportunityService.doUpdateLeadStaus(getLeadSourceId(),"Linked",createdBy,"Ins");
                            
                            setLeadStatus("Linked");
                            setLeadOperationType("Ins");
                        }else if("Lost".equals(getState())&&getLeadSourceId()>0){
                            //getOpportunityCountByLeadSourceId
                          //  if(DataSourceDataProvider.getInstance().getOpportunityCountByLeadSourceId(getLeadSourceId())==0){
                                setLeadStatus("InActive");
                            setLeadOperationType("Upd");
                           // }
                            // opportunityService.doUpdateLeadStaus(getLeadSourceId(),"Lost",createdBy,"upd");
                        }else{
                            setLeadStatus("NotApplicable");
                        }
                    
                    
                    
                    
                    opportunityId = opportunityService.addOrUpdateOpportunity(this);
                       String createdBy = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                   // if(isUpdate){
                        if(opportunityId>0){
                        resultType = SUCCESS;
                        setResultMessage("<font color=\"green\" size=\"1.5\">Opportunity has been successfully updated!</font>");
                        /*if(!"Lost".equals(getState())&&((getLeadSourceId()!=0&&getLeadSourceId()!=-1&&getPreviousLeadSourceId()==-1)||((getPreviousLeadSourceId()!=getLeadSourceId())&&getPreviousLeadSourceId()!=-1))){
                            opportunityService.doUpdateLeadStaus(getLeadSourceId(),"Linked",createdBy,"Ins");
                        }else if("Lost".equals(getState())&&getLeadSourceId()>0){
                            //getOpportunityCountByLeadSourceId
                            if(DataSourceDataProvider.getInstance().getOpportunityCountByLeadSourceId(getLeadSourceId())==0)
                             opportunityService.doUpdateLeadStaus(getLeadSourceId(),"Lost",createdBy,"upd");
                        }
                        */
                        
                        dataSourceDataProvider = DataSourceDataProvider.getInstance();
                        String practiceSalesEmailId = "", prevPracticeSalesEmailId = "", preSalesEmailId = "", prevPreSalesEmailId = "", insideSalesEmailId = "", prevInsideSalesEmailId = "", to = "";


                        if (getRegionalMgrId() != -1 && getRegionalMgrId() != getPreviousRegionalMgrId()) {
                            practiceSalesEmailId = dataSourceDataProvider.getEmailIdForEmployee(getRegionalMgrId());
                            prevPracticeSalesEmailId = dataSourceDataProvider.getEmailIdForEmployee(getPreviousRegionalMgrId());

                        }
                        if (getPracticeMgrId() != -1 && getPracticeMgrId() != getPreviousPracticeMgrId()) {
                            preSalesEmailId = dataSourceDataProvider.getEmailIdForEmployee(getPracticeMgrId());
                            prevPreSalesEmailId = dataSourceDataProvider.getEmailIdForEmployee(getPreviousPracticeMgrId());

                        }
                        if (getInsideSalesId() != getPreviousInsideSalesId()) {
                            insideSalesEmailId = dataSourceDataProvider.getEmailIdForEmployee(getInsideSalesId());
                            prevInsideSalesEmailId = dataSourceDataProvider.getEmailIdForEmployee(getPreviousInsideSalesId());

                        }
                        if (insideSalesEmailId != null && !"".equals(insideSalesEmailId)) {
                            to = insideSalesEmailId.trim();
                            if (prevInsideSalesEmailId != null && !"".equals(prevInsideSalesEmailId)) {
                                to =to + "," + prevInsideSalesEmailId.trim();
                            }
                        }
                        
                        if (practiceSalesEmailId != null && !"".equals(practiceSalesEmailId) && to != "") {
                            to = to + "," + practiceSalesEmailId.trim();
                            if (prevPracticeSalesEmailId != null && !"".equals(prevPracticeSalesEmailId)) {
                                to =to + "," + prevPracticeSalesEmailId.trim();
                            }
                        } else if (practiceSalesEmailId != null && !"".equals(practiceSalesEmailId)) {
                            to = practiceSalesEmailId.trim() ;
                             if (prevPracticeSalesEmailId != null && !"".equals(prevPracticeSalesEmailId)) {
                                to =to + "," + prevPracticeSalesEmailId.trim();
                            }
                        }
                        
                        if (preSalesEmailId != null && !"".equals(preSalesEmailId) && to != "") {
                            to = to + "," + preSalesEmailId ;
                             if (prevPreSalesEmailId != null && !"".equals(prevPreSalesEmailId)) {
                                to =to + "," + prevPreSalesEmailId;
                            }
                        } else if (preSalesEmailId != null && !"".equals(preSalesEmailId)) {
                            to = preSalesEmailId;
                             if (prevPreSalesEmailId != null && !"".equals(prevPreSalesEmailId)) {
                                to = to + "," + prevPreSalesEmailId;
                            }
                        }

                     //   System.out.println("to..." + to);
                        if (!"".equals(to)) {
                            if(Properties.getProperty("Mail.Flag").equals("1")) {
                                MailManager sendMail = new MailManager();
                            sendMail.sendOpportunityMailToPracticeSalesAndPreSales(getTitle(), getState(), getType(), getPracticeName(), getDueDate(), getModifiedDate(), to, "Updated", getRegionalMgrId(), getPracticeMgrId(), getInsideSalesId(), getPreviousRegionalMgrId(), getPreviousPracticeMgrId(), getPreviousInsideSalesId(),getModifiedBy());
                            
                            }
                            
                        }

                        
                        
                    }else{
                        resultType = INPUT;
                        setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>");
                    }
                    
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                    
                    //setCurrentOpportunity(opportunityService.getOpportunityVTO(this));
                    
                   
                    
                    prepare();
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    
    public String myOpportunities() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_DASH_BOARD", userRoleId)) {
                try {
                    setOpportunityStateList(DataSourceDataProvider.getInstance().getOpportunityStateList());
                    setTypeList(HibernateDataProvider.getInstance().getOpportunityType(ApplicationConstants.CRM_OPS_TYPE_OPTIONS));
                    setPracticeIdList(DataSourceDataProvider.getInstance().getPracticeByDepartment("GDC"));
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
    
    public String custOpportunities() {

        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null) {
            
                try {
                    setOpportunityStateList(DataSourceDataProvider.getInstance().getOpportunityStateList());
                    setTypeList(HibernateDataProvider.getInstance().getOpportunityType(ApplicationConstants.CRM_OPS_TYPE_OPTIONS));
                    setPracticeIdList(DataSourceDataProvider.getInstance().getPracticeByDepartment("GDC"));
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
              

            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

//Setters and getters for form
    
    
    /**
     * Returns an Integer object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the id at the specified URL
     * @see         Id
     */
    public int getId() {
        return id;
    }
    
    /** Handles the  <code>setId</code> method.
     * @param request id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
    /**
     * Returns an Integer object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the accountId at the specified URL
     * @see         accountId
     */
    public int getAccountId() {
        return accountId;
    }
    
    /** Handles the  <code>setAccountId</code> method.
     * @param request accountId
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    
/* * Returns an Integer object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * integer exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the title at the specified URL
 * @see         title
 */
    public String getTitle() {
        return title;
    }
    
    /** Handles the  <code>setTitle</code> method.
     * @param request title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    
        /* * Returns an String object that can then be used for the retreiving the data.
         * <p>
         * This method always returns immediately, whether or not the
         * integer exists. When this "edit button " is clicked to edit the page ,
         * the data will be loaded.
         * @return      the description at the specified URL
         * @see         description
         */
    public String getDescription() {
        return description;
    }
    
    /** Handles the  <code>setDescription</code> method.
     * @param request description
     */
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    
        /* * Returns an double object that can then be used for the retreiving the data.
         * <p>
         * This method always returns immediately, whether or not the
         * integer exists. When this "edit button " is clicked to edit the page ,
         * the data will be loaded.
         * @return      the value at the specified URL
         * @see         value
         */
    public double getValue() {
        return value;
    }
    
    
    /** Handles the  <code>setValue</code> method.
     * @param request value
     */
    public void setValue(double value) {
        this.value = value;
    }
    
    
        /* * Returns an String object that can then be used for the retreiving the data.
         * <p>
         * This method always returns immediately, whether or not the
         * integer exists. When this "edit button " is clicked to edit the page ,
         * the data will be loaded.
         * @return      the title at the specified URL
         * @see         type
         */
    public String getType() {
        return type;
    }
    
    
    /** Handles the  <code>setType</code> method.
     * @param request type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
        /* * Returns an String object that can then be used for the retreiving the data.
         * <p>
         * This method always returns immediately, whether or not the
         * integer exists. When this "edit button " is clicked to edit the page ,
         * the data will be loaded.
         * @return      the stage at the specified URL
         * @see         stage
         */
    public String getStage() {
        return stage;
    }
    
    /** Handles the  <code>setStage</code> method.
     * @param request stage
     */
    public void setStage(String stage) {
        this.stage = stage;
    }
    
    
                /* * Returns an TimeStamp object that can then be used for the retreiving the data.
                 * <p>
                 * This method always returns immediately, whether or not the
                 * integer exists. When this "edit button " is clicked to edit the page ,
                 * the data will be loaded.
                 * @return      the dueDate at the specified URL
                 * @see         dueDate
                 */
    public String getDueDate() {
        return dueDate;
    }
    
    /** Handles the  <code>setDueDate</code> method.
     * @param request dueDate
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    
                        /* * Returns an TimeStamp object that can then be used for the retreiving the data.
                         * <p>
                         * This method always returns immediately, whether or not the
                         * integer exists. When this "edit button " is clicked to edit the page ,
                         * the data will be loaded.
                         * @return      the createdDate at the specified URL
                         * @see         createdDate
                         */
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    
    
    /** Handles the  <code>setCreatedDate</code> method.
     * @param request createdDate
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    
    
                                /* * Returns an Integer object that can then be used for the retreiving the data.
                                 * <p>
                                 * This method always returns immediately, whether or not the
                                 * integer exists. When this "edit button " is clicked to edit the page ,
                                 * the data will be loaded.
                                 * @return      the insideSalesId at the specified URL
                                 * @see         insideSalesId
                                 */
    public int getInsideSalesId() {
        return insideSalesId;
    }
    
    /** Handles the  <code>setInsideSalesId</code> method.
     * @param request insideSalesId
     */
    public void setInsideSalesId(int insideSalesId) {
        this.insideSalesId = insideSalesId;
    }
    
    
                                        /* * Returns an Integer object that can then be used for the retreiving the data.
                                         * <p>
                                         * This method always returns immediately, whether or not the
                                         * integer exists. When this "edit button " is clicked to edit the page ,
                                         * the data will be loaded.
                                         * @return      the bdmId at the specified URL
                                         * @see         bdmId
                                         */
    public int getBdmId() {
        return bdmId;
    }
    
    
    /** Handles the  <code>setBdmId</code> method.
     * @param request bdmId
     */
    public void setBdmId(int bdmId) {
        this.bdmId = bdmId;
    }
    
    
                                        /* * Returns an Integer object that can then be used for the retreiving the data.
                                         * <p>
                                         * This method always returns immediately, whether or not the
                                         * integer exists. When this "edit button " is clicked to edit the page ,
                                         * the data will be loaded.
                                         * @return      the regionalMgrId at the specified URL
                                         * @see         regionalMgrId
                                         */
    public int getRegionalMgrId() {
        return regionalMgrId;
    }
    
    /** Handles the  <code>setRegionalMgrId</code> method.
     * @param request regionalMgrId
     */
    public void setRegionalMgrId(int regionalMgrId) {
        this.regionalMgrId = regionalMgrId;
    }
    
    
                                /* * Returns an Integer object that can then be used for the retreiving the data.
                                 * <p>
                                 * This method always returns immediately, whether or not the
                                 * integer exists. When this "edit button " is clicked to edit the page ,
                                 * the data will be loaded.
                                 * @return      the vpId at the specified URL
                                 * @see         vpId
                                 */
    
    public int getVpId() {
        return vpId;
    }
    
    
    /** Handles the  <code>setVpId</code> method.
     * @param request vpId
     */
    public void setVpId(int vpId) {
        this.vpId = vpId;
    }
    
    
    
                                        /* * Returns an Integer object that can then be used for the retreiving the data.
                                         * <p>
                                         * This method always returns immediately, whether or not the
                                         * integer exists. When this "edit button " is clicked to edit the page ,
                                         * the data will be loaded.
                                         * @return      the offshorePMId at the specified URL
                                         * @see         offshorePMId
                                         */
    public int getOffshorePMId() {
        return offshorePMId;
    }
    
    
    /** Handles the  <code>setOffshorePMId</code> method.
     * @param request offshorePMId
     */
    public void setOffshorePMId(int offshorePMId) {
        this.offshorePMId = offshorePMId;
    }
    
    
                                        /* * Returns an Integer object that can then be used for the retreiving the data.
                                         * <p>
                                         * This method always returns immediately, whether or not the
                                         * integer exists. When this "edit button " is clicked to edit the page ,
                                         * the data will be loaded.
                                         * @return      the practiceMgrId at the specified URL
                                         * @see         practiceMgrId
                                         */
    public int getPracticeMgrId() {
        return practiceMgrId;
    }
    
    
    /** Handles the  <code>setPracticeMgrId</code> method.
     * @param request practiceMgrId
     */
    public void setPracticeMgrId(int practiceMgrId) {
        this.practiceMgrId = practiceMgrId;
    }
    
    
/* * Returns an Integer object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * integer exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the architectId at the specified URL
 * @see         architectId
 */
    public int getArchitectId() {
        return architectId;
    }
    
    
    /** Handles the  <code>setArchitectId</code> method.
     * @param request architectId
     */
    public void setArchitectId(int architectId) {
        this.architectId = architectId;
    }
    
    
// Setters and Getters for prepare
    
    
    
/* * Returns an Map object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * integer exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the insideSalesMap at the specified URL
 * @see         insideSalesMap
 */
    
    public Map getInsideSalesMap() {
        return insideSalesMap;
    }
    
    
    /** Handles the  <code>setInsideSalesMap</code> method.
     * @param request insideSalesMap
     */
    public void setInsideSalesMap(Map insideSalesMap) {
        this.insideSalesMap = insideSalesMap;
    }
    
    
    
/* * Returns an Map object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * integer exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the bdmMap at the specified URL
 * @see         bdmMap
 */
    
    public Map getBdmMap() {
        return bdmMap;
    }
    
    
    /** Handles the  <code>setBdmMap</code> method.
     * @param request bdmMap
     */
    public void setBdmMap(Map bdmMap) {
        this.bdmMap = bdmMap;
    }
    
 /* * Returns an Map object that can then be used for the retreiving the data.
  * <p>
  * This method always returns immediately, whether or not the
  * integer exists. When this "edit button " is clicked to edit the page ,
  * the data will be loaded.
  * @return      the regionalMgrMap at the specified URL
  * @see         regionalMgrMap
  */
    
    public Map getRegionalMgrMap() {
        return regionalMgrMap;
    }
    
    
    /** Handles the  <code>setRegionalMgrMap</code> method.
     * @param request regionalMgrMap
     */
    public void setRegionalMgrMap(Map regionalMgrMap) {
        this.regionalMgrMap = regionalMgrMap;
    }
    
    
/* * Returns an Map object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * integer exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the vpMap at the specified URL
 * @see         vpMap
 */
    public Map getVpMap() {
        return vpMap;
    }
    
    
    /** Handles the  <code>setVpMap</code> method.
     * @param request vpMap
     */
    public void setVpMap(Map vpMap) {
        this.vpMap = vpMap;
    }
    
/* * Returns an Map object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * integer exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the offshorePMMap at the specified URL
 * @see         offshorePMMap
 */
    public Map getOffshorePMMap() {
        return offshorePMMap;
    }
    
    
    /** Handles the  <code>setOffshorePMMap</code> method.
     * @param request offshorePMMap
     */
    public void setOffshorePMMap(Map offshorePMMap) {
        this.offshorePMMap = offshorePMMap;
    }
    
    
 /* * Returns an Map object that can then be used for the retreiving the data.
  * <p>
  * This method always returns immediately, whether or not the
  * integer exists. When this "edit button " is clicked to edit the page ,
  * the data will be loaded.
  * @return      the onsitePMMap at the specified URL
  * @see         onsitePMMap
  */
    public Map getOnsitePMMap() {
        return onsitePMMap;
    }
    
    
    /** Handles the  <code>setOnsitePMMap</code> method.
     * @param request onsitePMMap
     */
    public void setOnsitePMMap(Map onsitePMMap) {
        this.onsitePMMap = onsitePMMap;
    }
    
  /* * Returns an Map object that can then be used for the retreiving the data.
   * <p>
   * This method always returns immediately, whether or not the
   * integer exists. When this "edit button " is clicked to edit the page ,
   * the data will be loaded.
   * @return      the architectMap at the specified URL
   * @see         architectMap
   */
    public Map getArchitectMap() {
        return architectMap;
    }
    
    
    /** Handles the  <code>setArchitectMap</code> method.
     * @param request architectMap
     */
    public void setArchitectMap(Map architectMap) {
        this.architectMap = architectMap;
    }
    
    
/* * Returns an List object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * integer exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the architectMap at the specified URL
 * @see         architectMap
 */
    public List getTypeList() {
        return typeList;
    }
    
    
    /** Handles the  <code>setTypeList</code> method.
     * @param request typeList
     */
    public void setTypeList(List typeList) {
        this.typeList = typeList;
    }
    
 /* * Returns an List object that can then be used for the retreiving the data.
  * <p>
  * This method always returns immediately, whether or not the
  * integer exists. When this "edit button " is clicked to edit the page ,
  * the data will be loaded.
  * @return      the stageList at the specified URL
  * @see         stageList
  */
    public List getStageList() {
        return stageList;
    }
    
    
    /** Handles the  <code>setStageList</code> method.
     * @param request stageList
     */
    public void setStageList(List stageList) {
        this.stageList = stageList;
    }
    
 /* * Returns an String object that can then be used for the retreiving the data.
  * <p>
  * This method always returns immediately, whether or not the
  * string exists. When this "edit button " is clicked to edit the page ,
  * the data will be loaded.
  * @return      the accountName at the specified URL
  * @see         accountName
  */
    
    public String getAccountName() {
        return accountName;
    }
    
    
    /** Handles the  <code>setAccountName</code> method.
     * @param request accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    
    /** Handles the  <code>setServletRequest</code> method.
     * @param request httpServletRequest
     */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    
 /* * Returns an Timestamp object that can then be used for the retreiving the data.
  * <p>
  * This method always returns immediately, whether or not the
  * timestamp exists. When this "edit button " is clicked to edit the page ,
  * the data will be loaded.
  * @return      the dueDateTimestamp at the specified URL
  * @see         dueDateTimestamp
  */
    public Timestamp getDueDateTimestamp() {
        return dueDateTimestamp;
    }
    
    
    /** Handles the  <code>setDueDateTimestamp</code> method.
     * @param request dueDateTimestamp
     */
    public void setDueDateTimestamp(Timestamp dueDateTimestamp) {
        this.dueDateTimestamp = dueDateTimestamp;
    }
    
    
/* * Returns an OpportunityVTO object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * opportunityVTO exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the currentOpportunity at the specified URL
 * @see         currentOpportunity
 */
    public OpportunityVTO getCurrentOpportunity() {
        return currentOpportunity;
    }
    
    
    /** Handles the  <code>setCurrentOpportunity</code> method.
     * @param request setCurrentOpportunity
     */
    public void setCurrentOpportunity(OpportunityVTO currentOpportunity) {
        this.currentOpportunity = currentOpportunity;
    }
    
 /* * Returns an String object that can then be used for the retreiving the data.
  * <p>
  * This method always returns immediately, whether or not the
  * string exists. When this "edit button " is clicked to edit the page ,
  * the data will be loaded.
  * @return      the actionString at the specified URL
  * @see         actionString
  */
    public String getActionString() {
        return actionString;
    }
    
    
    /** Handles the  <code>setActionString</code> method.
     * @param request actionString
     */
    public void setActionString(String actionString) {
        this.actionString = actionString;
    }
    
    public String getAccId() {
        return accId;
    }
    
    public void setAccId(String pAccId) {
        this.accId = pAccId;
        this.setId(Integer.parseInt(pAccId));
    }
    
    public String getCreatedBy() {
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
    
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }
    
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
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
     * @return the insideSalesMap1
     */
    public Map getInsideSalesMap1() {
        return insideSalesMap1;
    }

    /**
     * @param insideSalesMap1 the insideSalesMap1 to set
     */
    public void setInsideSalesMap1(Map insideSalesMap1) {
        this.insideSalesMap1 = insideSalesMap1;
    }

    /**
     * @return the fieldSalesMap
     */
    public Map getFieldSalesMap() {
        return fieldSalesMap;
    }

    /**
     * @param fieldSalesMap the fieldSalesMap to set
     */
    public void setFieldSalesMap(Map fieldSalesMap) {
        this.fieldSalesMap = fieldSalesMap;
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
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
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
     * @return the leadSourceId
     */
    public int getLeadSourceId() {
        return leadSourceId;
    }

    /**
     * @param leadSourceId the leadSourceId to set
     */
    public void setLeadSourceId(int leadSourceId) {
        this.leadSourceId = leadSourceId;
    }

    /**
     * @return the leadsMap
     */
    public Map getLeadsMap() {
        return leadsMap;
    }

    /**
     * @param leadsMap the leadsMap to set
     */
    public void setLeadsMap(Map leadsMap) {
        this.leadsMap = leadsMap;
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
     * @return the sviList
     */
    public String getSviList() {
        return sviList;
    }

    /**
     * @param sviList the sviList to set
     */
    public void setSviList(String sviList) {
        this.sviList = sviList;
    }

    /**
     * @return the sviNum
     */
    public String getSviNum() {
        return sviNum;
    }

    /**
     * @param sviNum the sviNum to set
     */
    public void setSviNum(String sviNum) {
        this.sviNum = sviNum;
    }

    /**
     * @return the previousLeadSourceId
     */
    public int getPreviousLeadSourceId() {
        return previousLeadSourceId;
    }

    /**
     * @param previousLeadSourceId the previousLeadSourceId to set
     */
    public void setPreviousLeadSourceId(int previousLeadSourceId) {
        this.previousLeadSourceId = previousLeadSourceId;
    }

    /**
     * @return the leadTitle
     */
    public String getLeadTitle() {
        return leadTitle;
    }

    /**
     * @param leadTitle the leadTitle to set
     */
    public void setLeadTitle(String leadTitle) {
        this.leadTitle = leadTitle;
    }

    /**
     * @return the previousInsideSalesId
     */
    public int getPreviousInsideSalesId() {
        return previousInsideSalesId;
    }

    /**
     * @param previousInsideSalesId the previousInsideSalesId to set
     */
    public void setPreviousInsideSalesId(int previousInsideSalesId) {
        this.previousInsideSalesId = previousInsideSalesId;
    }

    /**
     * @return the previousRegionalMgrId
     */
    public int getPreviousRegionalMgrId() {
        return previousRegionalMgrId;
    }

    /**
     * @param previousRegionalMgrId the previousRegionalMgrId to set
     */
    public void setPreviousRegionalMgrId(int previousRegionalMgrId) {
        this.previousRegionalMgrId = previousRegionalMgrId;
    }

    /**
     * @return the previousPracticeMgrId
     */
    public int getPreviousPracticeMgrId() {
        return previousPracticeMgrId;
    }

    /**
     * @param previousPracticeMgrId the previousPracticeMgrId to set
     */
    public void setPreviousPracticeMgrId(int previousPracticeMgrId) {
        this.previousPracticeMgrId = previousPracticeMgrId;
    }

    /**
     * @return the leadOperationType
     */
    public String getLeadOperationType() {
        return leadOperationType;
    }

    /**
     * @param leadOperationType the leadOperationType to set
     */
    public void setLeadOperationType(String leadOperationType) {
        this.leadOperationType = leadOperationType;
    }

    /**
     * @return the leadStatus
     */
    public String getLeadStatus() {
        return leadStatus;
    }

    /**
     * @param leadStatus the leadStatus to set
     */
    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }

   
   
    
}
