/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   September 9, 2008, 7:28 PM
 *
 * Author  :  Hari Krishna Kondala <hkondala@miraclesoft.com>
 *
 * File    : RequirementAction .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.requirement;

import com.mss.mirage.crm.attachments.AttachmentService;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.FileUploadUtility;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class RequirementAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
    
    /** Creates a new instance of RequirementAction */
    public RequirementAction() {
    }
    
    /**
     * The resultType  is Useful to get the ResultType of an Action and depend on this
     * resultType the navigation of screens happens.
     */
    private String resultType;
    
    /**
     * The resultMessage  capture the resut and it is stored  as the request attribute.
     */
    private String resultMessage;
    
    /**
     * This variable httpServletRequest store the HttpServletRequest object reference
     * <code>{
     * @link    #HttpServletRequest
     * }</code>.
     */
    private HttpServletRequest httpServletRequest;
    
    /**
     * A List object with an statesList object  read from a
     * full List of data.
     */
    private List statesList;
    
    private DataSourceDataProvider datasourceDataProvider = null;
    
    /** The createdBy  is Useful to identify by whom it was created. */
    private String createdBy;
    
    /** The createdDate  is useful to  identify on which date it was created. */
    private Timestamp createdDate;
    
    /** The modifiedBy  is useful to  identify by whom modified this screen */
    private String modifiedBy;
    
    /** The modifiedDate  is useful to  identify ModifiedDate. */
    private Timestamp modifiedDate;
    
    /** The queryString is used for storing sqlQuery String. */
    private StringBuffer queryStringBuffer;
    
    private String queryString;
  
    
    private RequirementVTO currentRequirement;
    
    private String actionType;
    
    private String objectId;
    
    private int userRoleId;
    
    private String state;
    
    private String city;
    
    private String country;
    
    private String comments;
    
    private String title;
    
    private String practiceId;
    
    private String startDate;
    
    private String endDate;
    
    private String contactNo;
    
    private String assignedTo;
    
    private String secondaryRecruiter;
    
    private String assignToTechLead;
    
    private String secondaryTechLead;
    
    private int noResumes;
    
    private String functions;
    
    private String responsibilities;
    
    private String education;
    
    private String experience;
    
    private String taxTerm;
    
    private String targetRate;
    
    private String targetSalary;
    
    private String status;
    
    private String skills;
    
    private List assignedMembers;
    
    private List techLeadList;
    
    private Map createdMemebers;
    
    private String postedDate1;
    
    private String postedDate2;
    
    private String requirementId;
    
    private String consultantId;
    
    private int techRate;
    
    private String email2;
    
    private String resumeId;
    
    private String resumes;
    
    private String consultId;
    
    private String duration;
    
    private String location;
    
    private String divType;
    
    private String rejectReason;
    
    /** The String submitFrom is used for getting the value from the form
     * whether the value is SEARCH OR NOT
     */
    private String submitFrom;
    
    // The userWorkCountry is used to store the WorkingCountry name selected by the user when he logs in
    private String userWorkCountry;
    
    private int accId;
    
    private DefaultDataProvider defaultDataProvider;
    
    private HibernateDataProvider hibernateDataProvider;
    
    private String accountName;

  
    //new varilble for ajax back to list
    private String ajaxId;
    
    
 //new variables for resume submission on 07162013
    
  private String resumeRecId;
  private String resumeRequirementId;
  private String resumeConsultantId;
  private String resumeAttachmentId;
  private String customerEmail;  
  private int isSuccess;
  private String cc; 
  private String bcc; 
  private String subject; 
  private String Message;
  private String region;
  private String secondarySkills;
   private String recruiterComments;
private Map assignedByMap;
private Map preSalesMap;
 private List consultantStatusList;
 private String availableFrom;
    private String cellPhoneNo;
    private String workAuthorization;
    private String currentEmployer; 
    private String assignedBy;
    
 private String recruitmentRoleType;
    
    private String requirementAdminFlag;
    
    
      private String uploadFileName;
    private String fileLocation;
    private String filepath;
    private String attachmentName;
    private File upload;
    private String attachmentType;
    private String oppFlag;
    private AttachmentService attachmentService;
    private String generatedPath;
    
     private Map clientMap=new LinkedHashMap();
    private String clientId;
    private List requirementStatusList;
 private String skypeId;
private String currentLocation;
private String workAuthorizationCopyAttachment;
private String workAuthorizationCopy;
private int onProject;
private String dlCopyAttachedAttachment;
private String dlCopyAttached;
private Date projectEnd;
private String relocation;
private String changeReason;
private int yearOfCompletion;
private String availability;
private Date startDatetoUs;
private String educationDetails;
private String reference;
private String consultantName;


     private HttpServletResponse httpServletResponse;

    public String execute() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_GET_REQUIREMENT",userRoleId)){
                try {
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    RequirementVTO vto = new RequirementVTO();
                    vto.setActionType("requirementAdd");
                    vto.setRegion(getRegion());
                    vto.setStatus("open");
                    setCurrentRequirement(vto);
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesList("Recruiting"));
                    setTechLeadList(datasourceDataProvider.getTechLead());
                    setRequirementStatusList(datasourceDataProvider.getRequirementStatusList());
                    
                    //setCreatedMemebers(datasourceDataProvider.getEmployeeNamesList("Sales"));
                    setCreatedMemebers(datasourceDataProvider.getAllSalesTeamExceptAccountTeam());
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                    populateAccountNameForRequirement();
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    public String getRequirement() {
        resultType = LOGIN;
       // System.out.println("getRequirement method");
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            setUserWorkCountry(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_GET_REQUIREMENT",userRoleId)){
                try {
                  //  System.out.println("if block");
                    defaultDataProvider = DefaultDataProvider.getInstance();
                   setCurrentRequirement(ServiceLocator.getRequirementService().getRequirement(Integer.parseInt(getObjectId()),getUserWorkCountry()));
                    setRequirementAdminFlag(getRequirementAdminFlag());
                    // System.err.println(currentRequirement.getSecondaryRecruiter()+"========="+currentRequirement.getSecondaryTechLead());
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesList("Recruiting"));
                    setTechLeadList(datasourceDataProvider.getTechLead());
                    //setCreatedMemebers(datasourceDataProvider.getEmployeeNamesList("Sales"));
                    setCreatedMemebers(datasourceDataProvider.getAllSalesTeamExceptAccountTeam());
                    setRequirementStatusList(datasourceDataProvider.getRequirementStatusList());
                    populateAccountNameForRequirement();
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                   // System.out.println("title "+currentRequirement.getTitle());
                    httpServletRequest.setAttribute("currentAccountId", String.valueOf(getAccId()));
                    if(currentRequirement.getTitle() == null) resultType = "accessFailed";
                    else
                        resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    
  /*  public String getRequirementListAll() {
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            userWorkCountry= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_SEARCH_REQUIREMENT",userRoleId)){
                try {
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    queryStringBuffer =new StringBuffer();
                   // queryString = "select TRIM(Id) AS RequirementId,TRIM(JobTitle) AS JobTitle ,CONCAT(`tblRecRequirement`.`State`,', ',`tblRecRequirement`.`Country`) AS Location,STATUS,StartDate,AssignedTo AS Recruiter,AssignToTechLead AS PreSales,Skills FROM tblRecRequirement WHERE `tblRecRequirement`.`Country` like '"+userWorkCountry+"' ORDER BY DatePosted DESC";
                   // queryString = "select TRIM(Id) AS RequirementId,TRIM(JobTitle) AS JobTitle ,CONCAT(`tblRecRequirement`.`State`,', ',`tblRecRequirement`.`Country`) AS Location,STATUS,StartDate,AssignedTo AS Recruiter,SecondaryRecruiter AS SecondaryRecruiter,AssignToTechLead AS PreSales,Skills FROM tblRecRequirement WHERE `tblRecRequirement`.`Country` like '"+userWorkCountry+"' ORDER BY DatePosted DESC";
                    if(getAjaxId()!=null)
                    {
                    httpServletRequest.getSession(false).removeAttribute("REQ_SEARCH_QUERY");    
                  }
                      else
                    { 
                                queryStringBuffer.append((String) httpServletRequest.getSession(false).getAttribute("REQ_SEARCH_QUERY"));
                                setAssignedTo((String)httpServletRequest.getSession(false).getAttribute("dummyAssignedTo"));
                                setStatus((String)httpServletRequest.getSession(false).getAttribute("dummyStatus"));
                             
                                setTitle((String)httpServletRequest.getSession(false).getAttribute("dummyTitle"));
                                setPostedDate1((String)httpServletRequest.getSession(false).getAttribute("dummyStartDate"));
                                setPostedDate2((String)httpServletRequest.getSession(false).getAttribute("dummyEndDate"));
                                setCreatedBy((String)httpServletRequest.getSession(false).getAttribute("dummyCreatedBy"));
                    }
                    //queryStringBuffer="SELECT DISTINCT TRIM(tblRecRequirement.Id) AS RequirementId,TRIM(tblRecRequirement.JobTitle) AS JobTitle ,RecConsultant.FName,'.',tblRecConsultant.LName) AS NAME ,tblRec.CreatedDate AS SubmittedDate,tblRecRequirement.AssignedDate,CONCAT(tblRecRequirement.State,', ',tblRecRequirement.Country) AS Location,tblRecRequirement.STATUS,tblRecRequirement.StartDate,tblRecRequirement.AssignedTo AS Recruiter,tblRecRequirement.SecondaryRecruiter AS SecondaryRecruiter,tblRecRequirement.AssignToTechLead AS PreSales,tblRecRequirement.SkillsFROM (tblRecRequirement LEFT JOIN  tblRec ON (tblRecRequirement.Id = tblRec.RequirementId)LEFT JOIN tblRecConsultant ON (tblRecConsultant.Id=tblRec.ConsultantId)) WHERE tblRecRequirement.Country LIKE '%' ORDER BY tblRecRequirement.DatePosted DESC,SubmittedDate DESC ";
                    //httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryString);
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryStringBuffer.toString());
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    
                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesList("Recruiting"));
                    setTechLeadList(datasourceDataProvider.getTechLead());
                    //setCreatedMemebers(datasourceDataProvider.getEmployeeNamesList("Sales"));
                   // setCreatedMemebers(datasourceDataProvider.getAllSalesTeamExceptAccountTeam());
                     setCreatedMemebers(datasourceDataProvider.getEmployeeNamesBySalesRoleId());
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }*/
    
 public String getRequirementListAll() {
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            userWorkCountry= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_SEARCH_REQUIREMENT",userRoleId)){
                try {
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    queryStringBuffer =new StringBuffer();
                   // queryString = "select TRIM(Id) AS RequirementId,TRIM(JobTitle) AS JobTitle ,CONCAT(`tblRecRequirement`.`State`,', ',`tblRecRequirement`.`Country`) AS Location,STATUS,StartDate,AssignedTo AS Recruiter,AssignToTechLead AS PreSales,Skills FROM tblRecRequirement WHERE `tblRecRequirement`.`Country` like '"+userWorkCountry+"' ORDER BY DatePosted DESC";
                   // queryString = "select TRIM(Id) AS RequirementId,TRIM(JobTitle) AS JobTitle ,CONCAT(`tblRecRequirement`.`State`,', ',`tblRecRequirement`.`Country`) AS Location,STATUS,StartDate,AssignedTo AS Recruiter,SecondaryRecruiter AS SecondaryRecruiter,AssignToTechLead AS PreSales,Skills FROM tblRecRequirement WHERE `tblRecRequirement`.`Country` like '"+userWorkCountry+"' ORDER BY DatePosted DESC";
                    if(getAjaxId()!=null)
                    {
                        //setStatus("Open"); 
                    httpServletRequest.getSession(false).removeAttribute("REQ_SEARCH_QUERY");    
                  
                    
                    }
                      else
                    {    
               
                         queryStringBuffer.append((String) httpServletRequest.getSession(false).getAttribute("REQ_SEARCH_QUERY"));
                        
                                setAssignedTo((String)httpServletRequest.getSession(false).getAttribute("dummyAssignedTo"));
                                if(httpServletRequest.getSession(false).getAttribute("dummyStatus")!=null)
                                setStatus((String)httpServletRequest.getSession(false).getAttribute("dummyStatus"));
                                //else
                                   //setStatus("Open"); 
                             
                                setTitle((String)httpServletRequest.getSession(false).getAttribute("dummyTitle"));
                                setPostedDate1((String)httpServletRequest.getSession(false).getAttribute("dummyStartDate"));
                                setPostedDate2((String)httpServletRequest.getSession(false).getAttribute("dummyEndDate"));
                                setCreatedBy((String)httpServletRequest.getSession(false).getAttribute("dummyCreatedBy"));
                               
                      setAssignedBy((String)httpServletRequest.getSession(false).getAttribute("dummyAssignedBy"));
                      setClientId((String)httpServletRequest.getSession(false).getAttribute("dummyClientId"));
                    
                    }
                    //queryStringBuffer="SELECT DISTINCT TRIM(tblRecRequirement.Id) AS RequirementId,TRIM(tblRecRequirement.JobTitle) AS JobTitle ,RecConsultant.FName,'.',tblRecConsultant.LName) AS NAME ,tblRec.CreatedDate AS SubmittedDate,tblRecRequirement.AssignedDate,CONCAT(tblRecRequirement.State,', ',tblRecRequirement.Country) AS Location,tblRecRequirement.STATUS,tblRecRequirement.StartDate,tblRecRequirement.AssignedTo AS Recruiter,tblRecRequirement.SecondaryRecruiter AS SecondaryRecruiter,tblRecRequirement.AssignToTechLead AS PreSales,tblRecRequirement.SkillsFROM (tblRecRequirement LEFT JOIN  tblRec ON (tblRecRequirement.Id = tblRec.RequirementId)LEFT JOIN tblRecConsultant ON (tblRecConsultant.Id=tblRec.ConsultantId)) WHERE tblRecRequirement.Country LIKE '%' ORDER BY tblRecRequirement.DatePosted DESC,SubmittedDate DESC ";
                    //httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryString);
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryStringBuffer.toString());
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    setAssignedByMap(datasourceDataProvider.getRequirementAdminMap());
                    setClientMap(datasourceDataProvider.getClientMap());
                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesList("Recruiting"));
                    setTechLeadList(datasourceDataProvider.getTechLead());
                    //setCreatedMemebers(datasourceDataProvider.getEmployeeNamesList("Sales"));
                   // setCreatedMemebers(datasourceDataProvider.getAllSalesTeamExceptAccountTeam());
                     setCreatedMemebers(datasourceDataProvider.getEmployeeNamesBySalesRoleId());
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
	
    
      public String requirementSearch() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            DateUtility dateUtility=new DateUtility();
            userWorkCountry= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_SEARCH_REQUIREMENT",userRoleId)){
                try {
                    if(getSubmitFrom() == null) {
                     queryStringBuffer = new StringBuffer();
                     queryStringBuffer.append("SELECT TRIM(tblRecRequirement .Id) AS RequirementId,TRIM(tblRecConsultant.Id) AS ConsultantId,TRIM(JobTitle) AS JobTitle, ");
                     queryStringBuffer.append("CONCAT(tblRecConsultant.FName,'.',tblRecConsultant.LName) AS ConsultantNAME ,");
                     queryStringBuffer.append("CONCAT(`tblRecRequirement`.`State`,',',`tblRecRequirement`.`Country`) AS Location,");
                     queryStringBuffer.append("tblRecRequirement .STATUS,tblRec.CreatedDate as SubmittedDate," );
                     queryStringBuffer.append("tblRecRequirement.AssignedDate,tblRecRequirement.StartDate,tblRecRequirement.NoResumes," );
                     queryStringBuffer.append("AssignedTo AS Recruiter,SecondaryRecruiter AS SecondaryRecruiter,AssignToTechLead AS PreSales," );
                     queryStringBuffer.append("Skills FROM tblRecRequirement LEFT JOIN(tblRecConsultant, tblRec)" );
                     queryStringBuffer.append("ON (tblRecRequirement.Id=tblRec.RequirementId AND tblRecConsultant.Id=tblRec.ConsultantId) " );
                        //queryStringBuffer.append("select TRIM(Id) AS RequirementId,TRIM(JobTitle) AS JobTitle ,CONCAT(`tblRecRequirement`.`State`,', ',`tblRecRequirement`.`Country`) AS Location,STATUS,StartDate,AssignedTo AS Recruiter,SecondaryRecruiter AS SecondaryRecruiter,AssignToTechLead AS PreSales,Skills FROM tblRecRequirement ");
                        
                        if(null == getCreatedBy()) setCreatedBy("");
                        if("All".equalsIgnoreCase(getCreatedBy())) setCreatedBy("");
                        if(null == getAssignedTo()) setAssignedTo("");
                        if("All".equalsIgnoreCase(getAssignedTo())) setAssignedTo("");
                        if(null == getStatus()) setStatus("");
                        if("All".equalsIgnoreCase(getStatus())) setStatus("");
                        if(null == getTitle()) setTitle("");
                        if(null == getPostedDate1()) setPostedDate1("");
                        if(null == getPostedDate2()) setPostedDate2("");
                        //commented by prasad
                        if(!"".equalsIgnoreCase(getPostedDate1())) {
                            setPostedDate1(DateUtility.getInstance().convertStringToMySQLDate(getPostedDate1()));
                        }
                        
                        if(!"".equalsIgnoreCase(getPostedDate2())) {
                            setPostedDate2(DateUtility.getInstance().convertStringToMySQLDate(getPostedDate2()));
                        }
                        
                        if((!"".equalsIgnoreCase(getCreatedBy())) || (!"".equalsIgnoreCase(getAssignedTo())) || (!"".equalsIgnoreCase(getStatus())) || (!"".equalsIgnoreCase(getTitle())) || (!"".equalsIgnoreCase(getPostedDate1())) || (!"".equalsIgnoreCase(getPostedDate2()))) {
                            queryStringBuffer.append(" WHERE ");
                        }
                        
                        int columnCounter = 0;
                        
                        if(!"".equalsIgnoreCase(getCreatedBy()) && columnCounter==0) {
                            if((getCreatedBy().indexOf("*") == -1) && (getCreatedBy().indexOf("%") == -1)) setCreatedBy(getCreatedBy()+"*");
                            setCreatedBy(getCreatedBy().replace("*","%"));
                            queryStringBuffer.append("`tblRecRequirement`.`CreatedBy` LIKE '"+getCreatedBy()+"'");
                            columnCounter++;
                            
                            setCreatedBy(getCreatedBy().replace("%",""));
                        }else if(!"".equalsIgnoreCase(getCreatedBy()) && columnCounter!=0) {
                            if((getCreatedBy().indexOf("*") == -1) && (getCreatedBy().indexOf("%") == -1)) setCreatedBy(getCreatedBy()+"*");
                            setCreatedBy(getCreatedBy().replace("*","%"));
                            queryStringBuffer.append("AND `tblRecRequirement`.`CreatedBy` LIKE '"+getCreatedBy()+"'");
                            columnCounter++;
                            
                            setCreatedBy(getCreatedBy().replace("%",""));
                        }
                        
                        if(!"".equalsIgnoreCase(getAssignedTo()) && columnCounter==0) {
                            if((getAssignedTo().indexOf("*") == -1) && (getAssignedTo().indexOf("%") == -1)) setAssignedTo(getAssignedTo()+"*");
                            setAssignedTo(getAssignedTo().replace("*","%"));
                            queryStringBuffer.append("`tblRecRequirement`.`AssignedTo` LIKE '"+getAssignedTo()+"'");
                            columnCounter++;
                            
                            setAssignedTo(getAssignedTo().replace("%",""));
                        }else if(!"".equalsIgnoreCase(getAssignedTo()) && columnCounter!=0) {
                            if((getAssignedTo().indexOf("*") == -1) && (getAssignedTo().indexOf("%") == -1)) setAssignedTo(getAssignedTo()+"*");
                            setAssignedTo(getAssignedTo().replace("*","%"));
                            queryStringBuffer.append("AND `tblRecRequirement`.`AssignedTo` LIKE '"+getAssignedTo()+"'");
                            columnCounter++;
                            
                           setAssignedTo(getAssignedTo().replace("%","")); 
                        }
                        
                        if(!"".equalsIgnoreCase(getStatus()) && columnCounter==0) {
                            if((getStatus().indexOf("*") == -1) && (getStatus().indexOf("%") == -1)) setStatus(getStatus()+"*");
                            setStatus(getStatus().replace("*","%"));
                            queryStringBuffer.append("`tblRecRequirement`.`Status` LIKE '"+getStatus()+"'");
                            columnCounter++;
                            
                            setStatus(getStatus().replace("%",""));
                        }else if(!"".equalsIgnoreCase(getStatus()) && columnCounter!=0) {
                            if((getStatus().indexOf("*") == -1) && (getStatus().indexOf("%") == -1)) setStatus(getStatus()+"*");
                            setStatus(getStatus().replace("*","%"));
                            queryStringBuffer.append("AND `tblRecRequirement`.`Status` LIKE '"+getStatus()+"'");
                            columnCounter++;
                            
                            setStatus(getStatus().replace("%",""));
                        }
                        
                        if(!"".equalsIgnoreCase(getTitle()) && columnCounter==0) {
                            if((getTitle().indexOf("*") == -1) && (getTitle().indexOf("%") == -1)) setTitle(getTitle()+"*");
                            setTitle(getTitle().replace("*","%"));
                            queryStringBuffer.append("`tblRecRequirement`.`JobTitle` LIKE '"+getTitle()+"'");
                            columnCounter++;
                            
                            
                        }else if(!"".equalsIgnoreCase(getTitle()) && columnCounter!=0) {
                            if((getTitle().indexOf("*") == -1) && (getTitle().indexOf("%") == -1)) setTitle(getTitle()+"*");
                            setTitle(getTitle().replace("*","%"));
                            queryStringBuffer.append("AND `tblRecRequirement`.`JobTitle` LIKE '"+getTitle()+"'");
                            columnCounter++;
                        }
                        
                        if(!"".equalsIgnoreCase(getPostedDate1()) && "".equalsIgnoreCase(getPostedDate2()) && columnCounter==0) {
                            if((getPostedDate1().indexOf("*") == -1) && (getPostedDate1().indexOf("%") == -1)) setPostedDate1(getPostedDate1()+"*");
                            setPostedDate1(getPostedDate1().replace("*","%"));
                            queryStringBuffer.append("date(`tblRecRequirement`.`DatePosted`) LIKE '"+getPostedDate1()+"'");
                            columnCounter++;
                            
                             setPostedDate1(dateUtility.convertToviewFormat(getPostedDate1().replace("%","")));
                        }else if(!"".equalsIgnoreCase(getPostedDate1()) && "".equalsIgnoreCase(getPostedDate2()) && columnCounter!=0) {
                            if((getPostedDate1().indexOf("*") == -1) && (getPostedDate1().indexOf("%") == -1)) setPostedDate1(getPostedDate1()+"*");
                            setPostedDate1(getPostedDate1().replace("*","%"));
                            queryStringBuffer.append("AND date(`tblRecRequirement`.`DatePosted`) LIKE '"+getPostedDate1()+"'");
                            columnCounter++;
                            
                            setPostedDate1(dateUtility.convertToviewFormat(getPostedDate1().replace("%","")));
                        }
                        
                        if(!"".equalsIgnoreCase(getPostedDate2()) && "".equalsIgnoreCase(getPostedDate1()) && columnCounter==0) {
                            if((getPostedDate2().indexOf("*") == -1) && (getPostedDate2().indexOf("%") == -1)) setPostedDate2(getPostedDate2()+"*");
                            setPostedDate2(getPostedDate2().replace("*","%"));
                            queryStringBuffer.append("date(`tblRecRequirement`.`DatePosted`) LIKE '"+getPostedDate2()+"'");
                            columnCounter++;
                            
                            setPostedDate2(dateUtility.convertToviewFormat(getPostedDate2().replace("%","")));
                        }else if(!"".equalsIgnoreCase(getPostedDate2()) && "".equalsIgnoreCase(getPostedDate1()) && columnCounter!=0) {
                            if((getPostedDate2().indexOf("*") == -1) && (getPostedDate2().indexOf("%") == -1)) setPostedDate2(getPostedDate2()+"*");
                            setPostedDate2(getPostedDate2().replace("*","%"));
                            queryStringBuffer.append("AND date(`tblRecRequirement`.`DatePosted`) LIKE '"+getPostedDate2()+"'");
                            columnCounter++;
                            
                            setPostedDate2(dateUtility.convertToviewFormat(getPostedDate2().replace("%",""))); 
                        }
                        
                        if(!"".equalsIgnoreCase(getPostedDate1()) && !"".equalsIgnoreCase(getPostedDate2()) && columnCounter==0) {
                            if((getPostedDate1().indexOf("*") == -1) && (getPostedDate1().indexOf("%") == -1)) setPostedDate1(getPostedDate1()+"*");
                            setPostedDate1(getPostedDate1().replace("*","%"));
                            
                            if((getPostedDate2().indexOf("*") == -1) && (getPostedDate2().indexOf("%") == -1)) setPostedDate2(getPostedDate2()+"*");
                            setPostedDate2(getPostedDate2().replace("*","%"));
                            
                            queryStringBuffer.append("date(`tblRecRequirement`.`DatePosted`) BETWEEN '"+getPostedDate1()+"' AND '"+getPostedDate2()+"'");
                            columnCounter++;
                            
                            setPostedDate1(dateUtility.convertToviewFormat(getPostedDate1().replace("%","")));
                            setPostedDate2(dateUtility.convertToviewFormat(getPostedDate2().replace("%",""))); 
                            
                        }else if(!"".equalsIgnoreCase(getPostedDate1()) && !"".equalsIgnoreCase(getPostedDate2()) && columnCounter!=0) {
                            if((getPostedDate1().indexOf("*") == -1) && (getPostedDate1().indexOf("%") == -1)) setPostedDate1(getPostedDate1()+"*");
                            setPostedDate1(getPostedDate1().replace("*","%"));
                            
                            if((getPostedDate2().indexOf("*") == -1) && (getPostedDate2().indexOf("%") == -1)) setPostedDate2(getPostedDate2()+"*");
                            setPostedDate2(getPostedDate2().replace("*","%"));
                            
                            queryStringBuffer.append("AND date(`tblRecRequirement`.`DatePosted`) BETWEEN '"+getPostedDate1()+"' AND '"+getPostedDate2()+"'");
                            columnCounter++;
                            
                            setPostedDate1(dateUtility.convertToviewFormat(getPostedDate1().replace("%","")));
                            setPostedDate2(dateUtility.convertToviewFormat(getPostedDate2().replace("%","")));
                        }
                        if(columnCounter == 0)
                            //queryStringBuffer.append(" Where `tblRecRequirement`.`Country` like '"+userWorkCountry+"' ORDER BY date(`tblRecRequirement`.`DatePosted`),Country DESC"); ,tblRecRequirement.Country
                            queryStringBuffer.append(" Where `tblRecRequirement`.`Country` like '"+userWorkCountry+"' ORDER BY date(`tblRecRequirement`.`DatePosted`) DESC");
                        else
                            //queryStringBuffer.append(" AND `tblRecRequirement`.`Country` like '"+userWorkCountry+"' ORDER BY date(`tblRecRequirement`.`DatePosted`),Country DESC");,tblRecRequirement.Country
                            queryStringBuffer.append(" AND `tblRecRequirement`.`Country` like '"+userWorkCountry+"' ORDER BY date(`tblRecRequirement`.`DatePosted`) DESC");
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryStringBuffer.toString());
                    }
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesList("Recruiting"));
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    setTechLeadList(datasourceDataProvider.getTechLead());
                    //setCreatedMemebers(datasourceDataProvider.getEmployeeNamesList("Sales"));
                    setCreatedMemebers(datasourceDataProvider.getAllSalesTeamExceptAccountTeam());
                    
                    /*setCreatedBy(getCreatedBy().replace("%",""));
                    setAssignedTo(getAssignedTo().replace("%",""));
                    setStatus(getStatus().replace("%",""));*/
                    resultType = SUCCESS;
                } catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    public String requirementAdd() {
        int isAdd;
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_REQUIREMENT",userRoleId)){
                try{
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    isAdd = ServiceLocator.getRequirementService().doAddRequirement(this);
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesList("Recruiting"));
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    setTechLeadList(datasourceDataProvider.getTechLead());
                    setRequirementStatusList(datasourceDataProvider.getRequirementStatusList());
                    //setCreatedMemebers(datasourceDataProvider.getEmployeeNamesList("Sales"));
                    setCreatedMemebers(datasourceDataProvider.getAllSalesTeamExceptAccountTeam());
                    resultType = SUCCESS;
                    
                    if(isAdd == 1) {
                         if(Properties.getProperty("Mail.Flag").equals("1")) {
                        MailManager.sendRequirmentDetails(getPracticeId(),getTitle(),getTargetRate(),getCountry(),getState(),getSkills(),getSecondarySkills());//getTargetSalary(),getExperience(),
                         }
                        resultMessage = "<font color=\"green\" size=\"1.5\">Requirement Details Added and Mail has been sent Successfully!</font>";
                    }else {
                        resultMessage = "<font color=\"red\" size=\"1.5\">Please Try Again</font>";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",resultMessage);
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }
        RequirementVTO vto = new RequirementVTO();
          vto.setActionType("requirementAdd");
           setCurrentRequirement(vto);
        return resultType;
    }
    
//    public String requirementEdit() {
//        
//        
//        int isEdit;
//        resultType = LOGIN;
//        
//        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
//            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
//            resultType = "accessFailed";
//            
//            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_REQUIREMENT",userRoleId)){
//                try{
//                    datasourceDataProvider = DataSourceDataProvider.getInstance();
//            setRequirementAdminFlag(getRequirementAdminFlag());
//                   setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
//                    
//                    isEdit=ServiceLocator.getRequirementService().doEdit(this);
//                    
//                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesList("Recruiting"));
//                    defaultDataProvider = DefaultDataProvider.getInstance();
//                    setTechLeadList(datasourceDataProvider.getTechLead());
//                    
//                    //setCreatedMemebers(datasourceDataProvider.getEmployeeNamesList("Sales"));
//                    setCreatedMemebers(datasourceDataProvider.getAllSalesTeamExceptAccountTeam());
//                    resultType = SUCCESS;
//                    //getRequirement();
//                    if(isEdit == 1) {
//                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Recruitment") || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Sales")) {
//                            String mailListOfTo=getAssignToTechLead()+"#"+getSecondaryRecruiter()+"#"+getSecondaryTechLead()+"#"+getAssignedTo();
//                            System.err.println("mailListOfTo-->"+mailListOfTo.replaceAll("null",""));
//                             
//                       //  if(!mailListOfTo.replaceAll("null","").equals("####")){
//                            int i=0;
//                            
//                            if(mailListOfTo.replaceAll("null","").equals("###") || mailListOfTo.replaceAll("null","").equals("####"))
//                                i=1;
//                            
//                           if(i==0){ 
//                                 String subjectinfo = " ";
//                                if(getStatus().equalsIgnoreCase("open")){
//                                    subjectinfo = "A new requirement has been assigned";
//                                } else if(getStatus().equalsIgnoreCase("Forecast")){
//                                    subjectinfo = "A forecast requirement has been assigned";
//                                }else if(getStatus().equalsIgnoreCase("InProgress")){
//                                    subjectinfo = "Requirement is in progress";
//                                }else if(getStatus().equalsIgnoreCase("Hold")){
//                                    subjectinfo = "Requirement has been put on HOLD";
//                                }else if(getStatus().equalsIgnoreCase("Withdrawn")){
//                                    subjectinfo = "Requirement has been withdrawn";
//                                }else if(getStatus().equalsIgnoreCase("Won")){
//                                    subjectinfo = "Requirement has been won";
//                                }else {
//                                    subjectinfo = "Requirements has been lost";
//                                }
//                               
//                                
//                                //,getTargetRate(),getTargetSalary()
//                                 if(Properties.getProperty("Mail.Flag").equals("1")) {
//                           MailManager.sendUpdatedRequirementDetails(subjectinfo,getTitle(),mailListOfTo,getTargetRate(),getObjectId(),getCountry(),getState());
//                                 }
//                               }
//                           
//                            
//                            resultMessage = "<font color=\"green\" size=\"1.5\">Requirement has been assigned Successfully!</font>";
//                           
//                      
//                        }else {
//                            resultMessage = "<font color=\"green\" size=\"1.5\">Requirement Details Modified Successfully!</font>";
//                        }
//                    }else {
//                        resultMessage = "<font color=\"red\" size=\"1.5\">Please Try Again</font>";
//                    }
//                    if("YES".equals(getRequirementAdminFlag())){
//                         setAssignedTo((String)httpServletRequest.getSession(false).getAttribute("tempAssignedTo"));
//                                setStatus((String)httpServletRequest.getSession(false).getAttribute("tempStatus"));
//                                setTitle((String)httpServletRequest.getSession(false).getAttribute("tempTitle"));
//                                setPostedDate1((String)httpServletRequest.getSession(false).getAttribute("tempStartDate"));
//                                setPostedDate2((String)httpServletRequest.getSession(false).getAttribute("tempEndDate"));
//                                setCreatedBy((String)httpServletRequest.getSession(false).getAttribute("tempCreatedBy"));
//                    }else {
//                         setAssignedTo((String)httpServletRequest.getSession(false).getAttribute("dummyAssignedTo"));
//                                setStatus((String)httpServletRequest.getSession(false).getAttribute("dummyStatus"));
//                                setTitle((String)httpServletRequest.getSession(false).getAttribute("dummyTitle"));
//                                setPostedDate1((String)httpServletRequest.getSession(false).getAttribute("dummyStartDate"));
//                                setPostedDate2((String)httpServletRequest.getSession(false).getAttribute("dummyEndDate"));
//                                setCreatedBy((String)httpServletRequest.getSession(false).getAttribute("dummyCreatedBy"));
//                    }
//                               
//
//                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
//                }catch (Exception ex){
//                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
//                    ex.printStackTrace();
//                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
//                    resultType =  ERROR;
//                }
//                
//            }//END-Authorization Checking
//        }//Close Session Checking
//        //Calling setAddAction() method to populate the login details of the consultant
//        
//        return resultType;
//    }
    public String requirementEdit() {
        
        
        int isEdit;
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_REQUIREMENT",userRoleId)){
                try{
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
            setRequirementAdminFlag(getRequirementAdminFlag());
                   setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    
                   Map assignedMap = DataSourceDataProvider.getInstance().getRequirementAssignToMap(getObjectId());
                   
                   String previousAssignedBy = (String)assignedMap.get("AssignedBy");
                   String previousRecruiter1 = (String)assignedMap.get("Recruiter1");
                   String previousRecruiter2 = (String)assignedMap.get("Recruiter2");
                   String previousPreSales1 = (String)assignedMap.get("PreSales1");
                   String previousPreSales2 = (String)assignedMap.get("PreSales2");
setObjectId(getObjectId());
setRequirementAdminFlag(getRequirementAdminFlag());
                    boolean isChanged = false;
                   String transType = "i";
                   
                 Timestamp AssignedDateFirst=null;
                  AssignedDateFirst = (Timestamp)assignedMap.get("AssignedDate");
                   // System.out.println(AssignedDate);
                   if((previousRecruiter1==null||"".equals(previousRecruiter1))&&!"".equals(getAssignedTo())){
                       isChanged = true;
                   }
                   else if(!previousRecruiter1.equals(getAssignedTo())){
                       isChanged = true;
                       transType = "u";
                   }
                   else if(!previousRecruiter2.equals(getSecondaryRecruiter())){
                        isChanged = true;
                   }
                    String assignedTo = DataSourceDataProvider.getInstance().getAssignedTo(getObjectId());
                    isEdit=ServiceLocator.getRequirementService().doEdit(this,transType);
                     int insertedRows = 0;
                    if(isChanged){
                        if("u".equals(transType)){
                             insertedRows = ServiceLocator.getRequirementService().insertRequirementAssignedToTrack(transType,getModifiedBy(),getAssignedTo(),getSecondaryRecruiter(),getObjectId(),getModifiedBy(),DateUtility.getInstance().getCurrentMySqlDateTime()); 
                            
                            
                        }
                       
                        else {
                          Timestamp  AssignedDate = (Timestamp)assignedMap.get("AssignedDate");
                           if(AssignedDate == null){
                               AssignedDate = DateUtility.getInstance().getCurrentMySqlDateTime();
                               previousAssignedBy = getModifiedBy();
                           }
                              insertedRows = ServiceLocator.getRequirementService().insertRequirementAssignedToTrack(transType,previousAssignedBy,getAssignedTo(),getSecondaryRecruiter(),getObjectId(),getModifiedBy(),AssignedDate); 
                           
                        }
                    }

                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesList("Recruiting"));
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    setTechLeadList(datasourceDataProvider.getTechLead());
                    setAssignedByMap(datasourceDataProvider.getRequirementAdminMap());
                    //setCreatedMemebers(datasourceDataProvider.getEmployeeNamesList("Sales"));
                    setCreatedMemebers(datasourceDataProvider.getAllSalesTeamExceptAccountTeam());
                    resultType = SUCCESS;
                    //getRequirement();
                    if(isEdit == 1) {
                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Recruitment") || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Sales")) {
                            String mailListOfTo=getAssignedTo()+"#"+getSecondaryRecruiter()+"#"+getAssignToTechLead()+"#"+getSecondaryTechLead();
                          //  System.err.println("mailListOfTo-->"+mailListOfTo.replaceAll("null",""));
                             
                       //  if(!mailListOfTo.replaceAll("null","").equals("####")){
                            int i=0;
                           // String recruiters=getSecondaryRecruiter()+"#"+getAssignedTo();
                           // String presales=getAssignToTechLead()+"#"+getSecondaryTechLead();
                             resultMessage = "<font color=\"green\" size=\"1.5\">Requirement Details Modified Successfully!</font>";
                            if(mailListOfTo.replaceAll("null","").equals("###") || mailListOfTo.replaceAll("null","").equals("####"))
                                i=1;
                            
                          if(i==0){ 
                                 String subjectinfo = "A new requirement has been Updated";
                                if(getStatus().equalsIgnoreCase("open") && "".equals(assignedTo)){
                                    subjectinfo = "A new requirement has been Updated";
                                   
                                }
                                if(getStatus().equalsIgnoreCase("open") && !"".equals(assignedTo) &&  AssignedDateFirst != null){
                                     subjectinfo = "A new requirement has been Updated";
                                }
                                else if(getStatus().equalsIgnoreCase("open") && !"".equals(assignedTo)){
                                    subjectinfo = "New requirement assigned";
                                     resultMessage = "<font color=\"green\" size=\"1.5\">Requirement has been assigned Successfully!</font>";
                                } else if(getStatus().equalsIgnoreCase("Forecast")){
                                    subjectinfo = "An assigned Requirement is in forecast";
                                }else if(getStatus().equalsIgnoreCase("InProgress")){
                                    subjectinfo = "An assigned Requirement is in progress";
                                }else if(getStatus().equalsIgnoreCase("Hold")){
                                    subjectinfo = "An assigned Requirement has been put on HOLD";
                                }else if(getStatus().equalsIgnoreCase("Withdrawn")){
                                    subjectinfo = "An assigned Requirement has been withdrawn/Closed";
                                }else if(getStatus().equalsIgnoreCase("Won")){
                                    subjectinfo = "An assigned Requirement has been won";
                                } else if(getStatus().equalsIgnoreCase("lost")){
                                    subjectinfo = "An assigned Requirements has been lost";
                                }
                                //System.out.println("getDivType()---"+getDivType());
                               if(getDivType().equals("requirementDetails")){
                                //,getTargetRate(),getTargetSalary()
                                 if(Properties.getProperty("Mail.Flag").equals("1")) {
                         //  MailManager.sendUpdatedRequirementDetails(subjectinfo,getTitle(),mailListOfTo,getTargetRate(),getObjectId(),getCountry(),getState());
                          // ServiceLocator.getRequirementService().doAddMailsForRequirement(subjectinfo,getTitle(),mailListOfTo,getTargetRate(),getObjectId(),getCountry(),getState(),getSkills(),getSecondarySkills());
                                     ServiceLocator.getRequirementService().doAddMailsForRequirement(subjectinfo,getTitle(),mailListOfTo,getTargetRate(),getObjectId(),getCountry(),getState(),getSkills(),getSecondarySkills());

                                 }
                               }
                               }
                           
                            
                           
                           
                      
                        }else {
                            resultMessage = "<font color=\"green\" size=\"1.5\">Requirement Details Modified Successfully!</font>";
                        }
                    }else {
                        resultMessage = "<font color=\"red\" size=\"1.5\">Please Try Again</font>";
                    }
                    if("YES".equals(getRequirementAdminFlag())){
                         setAssignedTo((String)httpServletRequest.getSession(false).getAttribute("tempAssignedTo"));
                                setStatus((String)httpServletRequest.getSession(false).getAttribute("tempStatus"));
                                setTitle((String)httpServletRequest.getSession(false).getAttribute("tempTitle"));
                                setPostedDate1((String)httpServletRequest.getSession(false).getAttribute("tempStartDate"));
                                setPostedDate2((String)httpServletRequest.getSession(false).getAttribute("tempEndDate"));
                                setCreatedBy((String)httpServletRequest.getSession(false).getAttribute("tempCreatedBy"));
                    }else {
                         setAssignedTo((String)httpServletRequest.getSession(false).getAttribute("dummyAssignedTo"));
                                setStatus((String)httpServletRequest.getSession(false).getAttribute("dummyStatus"));
                                setTitle((String)httpServletRequest.getSession(false).getAttribute("dummyTitle"));
                                setPostedDate1((String)httpServletRequest.getSession(false).getAttribute("dummyStartDate"));
                                setPostedDate2((String)httpServletRequest.getSession(false).getAttribute("dummyEndDate"));
                                setCreatedBy((String)httpServletRequest.getSession(false).getAttribute("dummyCreatedBy"));
                    }
                               
                  //  System.out.println("resultMessage---"+resultMessage);
                    //setResultMessage(resultMessage);
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        //Calling setAddAction() method to populate the login details of the consultant
        
        return resultType;
    }

    public String consultantForRequirementList() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_GET_REQUIREMENT",userRoleId)){
                try {
                    queryString = "select tblRec.Id as Id,tblRec.RequirementId as Id1,concat(FName,'.',LName) as Name,TechRate,tblRec.RateNegotiated,StartDate,Status,tblRec.Comments from tblRec,tblRecConsultant where " +
                    "tblRecConsultant.Id=tblRec.ConsultantId and tblRec.RequirementId ="+getObjectId();
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryString);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    
    public String consultantForRequirement() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_GET_REQUIREMENT",userRoleId)){
                try {
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    setTitle(datasourceDataProvider.getRequirementTitle(getObjectId()));
                    setConsultantStatusList(DataSourceDataProvider.getInstance().getConsultantStatusList());
//setStatus(status);
                    currentRequirement = new RequirementVTO();
                    currentRequirement.setActionType("addConsultantRequirement");
                    currentRequirement.setStatus("Assigned");
                    
                    resultType = SUCCESS;
                }catch (Exception ex){
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    
  public String addConsultantRequirement() {
        int isAdd;
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_GET_REQUIREMENT",userRoleId)){
                try {
                     String name=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();

                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                  
                    String path=com.mss.mirage.util.Properties.getProperty("WorkAuthoriztionCopy.AttachmentPath");
                     
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }

                    
                    String path1=com.mss.mirage.util.Properties.getProperty("DLCopy.AttachmentPath");
                     File file1 = new File(path1);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            if(getWorkAuthorizationCopy() != null && !"".equals(getWorkAuthorizationCopy())){
                    setWorkAuthorizationCopy(getRequirementId() + "_" +getConsultantId() + "_" + getWorkAuthorizationCopy());
                    File targetDirectory = new File(path+ "/" + getWorkAuthorizationCopy());
                    setWorkAuthorizationCopyAttachment(targetDirectory.toString());
            }else{
                   setWorkAuthorizationCopy("");
                   setWorkAuthorizationCopyAttachment("");
            }
             if(getDlCopyAttached() != null && !"".equals(getDlCopyAttached())){
                    setDlCopyAttached(getRequirementId() + "_" +getConsultantId() + "_" + getDlCopyAttached());
                    File targetDirectory1 = new File(path1+ "/" + getDlCopyAttached());
                    setDlCopyAttachedAttachment(targetDirectory1.toString());
             }else{
                 setDlCopyAttached("");
                 setDlCopyAttachedAttachment("");
             }

          
                    isAdd = ServiceLocator.getRequirementService().addConsultantForRequirement(this);
                    setRequirementAdminFlag(getRequirementAdminFlag());

                 if(isAdd == 1) {
//                         if(Properties.getProperty("Mail.Flag").equals("1")) {
//                             String maxRecId=DataSourceDataProvider.getInstance().getTblRecId();
//
//                     //  MailManager.sendConsultantDetailsForRequirement(getTitle(),getRequirementId(),getConsultantId(),getTargetRate(),getStartDate());
//                             MailManager.sendConsultantDetailsForRequirement(getTitle(),getRequirementId(),getConsultantId(),getTargetRate(),getStartDate() ,name,getEmail2(),getCellPhoneNo(),getObjectId(),maxRecId);
//
//                         }
                        resultMessage = "<font color=\"green\" size=\"1.5\">Consultant Details Added Successfully!</font>";
                    }else {
                        resultMessage = "<font color=\"red\" size=\"1.5\">Please Try Again</font>";
                    }
                    setResultMessage(resultMessage);

                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    setObjectId(getObjectId());
                    resultType =  SUCCESS;
                }catch (Exception ex){
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    
    public String getConsultantRequirement() {
        int isAdd;
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_GET_REQUIREMENT",userRoleId)){
                try {
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    setRequirementAdminFlag(getRequirementAdminFlag());
                     setConsultantStatusList(DataSourceDataProvider.getInstance().getConsultantStatusList());
                     setTitle(DataSourceDataProvider.getInstance().getRequirementTitle(String.valueOf(getObjectId())));
                    setCurrentRequirement(ServiceLocator.getRequirementService().getConsultantRequuirement(Integer.parseInt(getConsultId())));
                    //System.err.println("object id is"+getObjectId());
                    currentRequirement.setTitle(datasourceDataProvider.getRequirementTitle(getObjectId()));
                    currentRequirement.setEmail2(datasourceDataProvider.getConsultantEmail(currentRequirement.getConsultantId()));
                    currentRequirement.setResumes(datasourceDataProvider.getResumeName(currentRequirement.getResumeId()));
                    resultType = SUCCESS;
                }catch (Exception ex){
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        if(httpServletRequest.getParameter("objectId")!=null){
               String objectId = httpServletRequest.getParameter("objectId");
               String requirementAdminFlag = httpServletRequest.getParameter("requirementAdminFlag");
               String consultId = httpServletRequest.getParameter("consultId");
               setObjectId(objectId);
               setRequirementAdminFlag(requirementAdminFlag);
               setConsultId(consultId);
         }

        return resultType;
    }
    
    public String editConsultantRequirement() {
        int isEdit;
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_REQUIREMENT",userRoleId)){
                try{
                    setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                   
                       String path=com.mss.mirage.util.Properties.getProperty("WorkAuthoriztionCopy.AttachmentPath");
                     
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }

                    
                    String path1=com.mss.mirage.util.Properties.getProperty("DLCopy.AttachmentPath");
                     File file1 = new File(path1);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            if(!getWorkAuthorizationCopy().contains(getRequirementId()))
            {
                  setWorkAuthorizationCopy(getRequirementId() + "_" +getConsultantId() + "_" + getWorkAuthorizationCopy());
                  
            }
            if(!getDlCopyAttached().contains(getRequirementId())){
                 setDlCopyAttached(getRequirementId() + "_" +getConsultantId() + "_" + getDlCopyAttached());
            }
            if(getWorkAuthorizationCopy() != null && !"".equals(getWorkAuthorizationCopy())){
                    File targetDirectory = new File(path+ "/" + getWorkAuthorizationCopy());
                    setWorkAuthorizationCopyAttachment(targetDirectory.toString());
            }else{
                   setWorkAuthorizationCopy("");
                   setWorkAuthorizationCopyAttachment("");
            }
             if(getDlCopyAttached() != null && !"".equals(getDlCopyAttached())){
                    File targetDirectory1 = new File(path1+ "/" + getDlCopyAttached());
                    setDlCopyAttachedAttachment(targetDirectory1.toString());
             }else{
                 setDlCopyAttached("");
                 setDlCopyAttachedAttachment("");
             }
                    
                    
                    isEdit=ServiceLocator.getRequirementService().editConsultantRequirement(this);
                    setConsultId(getConsultId());
                     setObjectId(getObjectId());
                    setRequirementId(getRequirementId());
                    setRequirementAdminFlag(getRequirementAdminFlag());

                    //getConsultantRequirement();
                    if(isEdit == 1) {
                        resultMessage = "<font color=\"green\" size=\"1.5\">Consultant Details Modified Successfully!</font>";
                    }else {
                        resultMessage = "<font color=\"red\" size=\"1.5\">Please Try Again</font>";
                    }
                    setResultMessage(resultMessage);
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    resultType =  SUCCESS;
                }catch (Exception ex){
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    

    
    public void populateAccountNameForRequirement() throws Exception{
        
         hibernateDataProvider = HibernateDataProvider.getInstance();
          setAccountName(HibernateDataProvider.getInstance().getAccountName(getAccId()));
          httpServletRequest.setAttribute("currentAccountId",String.valueOf(getAccId()));
          if(httpServletRequest.getSession(false).getAttribute("accountName") != null){
               httpServletRequest.getSession(false).removeAttribute("accountName");
            }
          httpServletRequest.getSession(false).setAttribute("accountName",getAccountName());
     
    }
    
    /**
     *Author :Prasad kandregula
     * new method for resume submission on 07162013
     *
     */
    
       public String resumeSubmissionPopup() {
        
        String userId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        String userName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
        try {
            String responseString = "";
            int isAdd=0;
            
            setResumeRecId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.RESUME_REC_ID).toString());
            setResumeRequirementId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.RESUME_REQUIREMENT_ID).toString());
            setResumeConsultantId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.RESUME_CONSULTANT_ID).toString());
            setResumeAttachmentId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.RESUME_ATTACHMENT_ID).toString());
            //setResumeRecId(httpServletRequest.getParameter("resumeRecId"));
            isAdd=ServiceLocator.getRequirementService().addResumeSubmissionDetails(getCustomerEmail(),getCc(),getBcc(),getSubject(),getMessage(),getResumeRecId(),getResumeRequirementId(),getResumeConsultantId(),getResumeAttachmentId(),userId);
            if(isAdd>=1)
            {
            setIsSuccess(1);
            }
            else
            {
             setIsSuccess(2);   
            }
 
        } catch (Exception ex) {
            setIsSuccess(2);
            ex.printStackTrace();
        }
        return SUCCESS;
    }

 public String getRequirementAdminList() {
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            userWorkCountry= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_SEARCH_REQUIREMENT",userRoleId)){
                try {
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    queryStringBuffer =new StringBuffer();
                    setRequirementAdminFlag("YES");
                   // queryString = "select TRIM(Id) AS RequirementId,TRIM(JobTitle) AS JobTitle ,CONCAT(`tblRecRequirement`.`State`,', ',`tblRecRequirement`.`Country`) AS Location,STATUS,StartDate,AssignedTo AS Recruiter,AssignToTechLead AS PreSales,Skills FROM tblRecRequirement WHERE `tblRecRequirement`.`Country` like '"+userWorkCountry+"' ORDER BY DatePosted DESC";
                   // queryString = "select TRIM(Id) AS RequirementId,TRIM(JobTitle) AS JobTitle ,CONCAT(`tblRecRequirement`.`State`,', ',`tblRecRequirement`.`Country`) AS Location,STATUS,StartDate,AssignedTo AS Recruiter,SecondaryRecruiter AS SecondaryRecruiter,AssignToTechLead AS PreSales,Skills FROM tblRecRequirement WHERE `tblRecRequirement`.`Country` like '"+userWorkCountry+"' ORDER BY DatePosted DESC";
                    if(getAjaxId()!=null)
                    {
                      //  setStatus("Open");
                    httpServletRequest.getSession(false).removeAttribute("REQ_ADMIN_SEARCH_QUERY");    
                    /*queryStringBuffer.append(" SELECT TRIM(tblRecRequirement .Id) AS RequirementId,TRIM(tblRecConsultant.Id) AS ConsultantId,TRIM(JobTitle) AS JobTitle, ");
                     queryStringBuffer.append("CONCAT(tblRecConsultant.FName,'.',tblRecConsultant.LName) AS ConsultantNAME ,");
                     queryStringBuffer.append("CONCAT(`tblRecRequirement`.`State`,',',`tblRecRequirement`.`Country`) AS Location,");
                     queryStringBuffer.append("tblRecRequirement .STATUS,tblRec.CreatedDate as SubmittedDate," );
                     queryStringBuffer.append("tblRecRequirement.AssignedDate,tblRecRequirement.StartDate,tblRecRequirement.NoResumes," );
                    queryStringBuffer.append("AssignedTo AS Recruiter,SecondaryRecruiter AS SecondaryRecruiter,AssignToTechLead AS PreSales," );
                     queryStringBuffer.append("Skills FROM tblRecRequirement LEFT JOIN(tblRecConsultant, tblRec)" );
                     queryStringBuffer.append("ON (tblRecRequirement.Id=tblRec.RequirementId AND tblRecConsultant.Id=tblRec.ConsultantId) " );
                    queryStringBuffer.append("WHERE tblRecRequirement.Country LIKE '"+userWorkCountry+"'  " );
                    
                    queryStringBuffer.append(" ORDER BY tblRecRequirement.DatePosted DESC");*/
                    
                    }
                      else
                    {    
                 /*   queryStringBuffer.append(" SELECT TRIM(tblRecRequirement .Id) AS RequirementId,TRIM(tblRecConsultant.Id) AS ConsultantId,TRIM(JobTitle) AS JobTitle, ");
                     queryStringBuffer.append("CONCAT(tblRecConsultant.FName,'.',tblRecConsultant.LName) AS ConsultantNAME ,");
                     queryStringBuffer.append("CONCAT(`tblRecRequirement`.`State`,',',`tblRecRequirement`.`Country`) AS Location,");
                     queryStringBuffer.append("tblRecRequirement .STATUS,tblRec.CreatedDate as SubmittedDate," );
                     queryStringBuffer.append("tblRecRequirement.AssignedDate,tblRecRequirement.StartDate,tblRecRequirement.NoResumes," );
                    queryStringBuffer.append("AssignedTo AS Recruiter,SecondaryRecruiter AS SecondaryRecruiter,AssignToTechLead AS PreSales," );
                     queryStringBuffer.append("Skills FROM tblRecRequirement LEFT JOIN(tblRecConsultant, tblRec)" );
                     queryStringBuffer.append("ON (tblRecRequirement.Id=tblRec.RequirementId AND tblRecConsultant.Id=tblRec.ConsultantId) " );
                    queryStringBuffer.append("WHERE tblRecRequirement.Country LIKE '"+userWorkCountry+"'  " );
                    
                    queryStringBuffer.append(" ORDER BY tblRecRequirement.DatePosted DESC");*/
                         queryStringBuffer.append((String) httpServletRequest.getSession(false).getAttribute("REQ_ADMIN_SEARCH_QUERY"));
                        
                                setAssignedTo((String)httpServletRequest.getSession(false).getAttribute("tempAssignedTo"));
                                if(httpServletRequest.getSession(false).getAttribute("tempStatus")!=null)
                                setStatus((String)httpServletRequest.getSession(false).getAttribute("tempStatus"));
                              //  else
                                    //setStatus("Open");
                                setTitle((String)httpServletRequest.getSession(false).getAttribute("tempTitle"));
                                setPostedDate1((String)httpServletRequest.getSession(false).getAttribute("tempStartDate"));
                                setPostedDate2((String)httpServletRequest.getSession(false).getAttribute("tempEndDate"));
                                setCreatedBy((String)httpServletRequest.getSession(false).getAttribute("tempCreatedBy"));
                               setClientId((String)httpServletRequest.getSession(false).getAttribute("tempClientId"));
                     
setAssignedBy((String)httpServletRequest.getSession(false).getAttribute("tempAssignedBy"));
                    
                    }
                    //queryStringBuffer="SELECT DISTINCT TRIM(tblRecRequirement.Id) AS RequirementId,TRIM(tblRecRequirement.JobTitle) AS JobTitle ,RecConsultant.FName,'.',tblRecConsultant.LName) AS NAME ,tblRec.CreatedDate AS SubmittedDate,tblRecRequirement.AssignedDate,CONCAT(tblRecRequirement.State,', ',tblRecRequirement.Country) AS Location,tblRecRequirement.STATUS,tblRecRequirement.StartDate,tblRecRequirement.AssignedTo AS Recruiter,tblRecRequirement.SecondaryRecruiter AS SecondaryRecruiter,tblRecRequirement.AssignToTechLead AS PreSales,tblRecRequirement.SkillsFROM (tblRecRequirement LEFT JOIN  tblRec ON (tblRecRequirement.Id = tblRec.RequirementId)LEFT JOIN tblRecConsultant ON (tblRecConsultant.Id=tblRec.ConsultantId)) WHERE tblRecRequirement.Country LIKE '%' ORDER BY tblRecRequirement.DatePosted DESC,SubmittedDate DESC ";
                    //httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryString);
                  //  httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryStringBuffer.toString());
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                     setAssignedByMap(datasourceDataProvider.getRequirementAdminMap());
                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesList("Recruiting"));
                    setTechLeadList(datasourceDataProvider.getTechLead());
                    setClientMap(datasourceDataProvider.getClientMap());
                    //setCreatedMemebers(datasourceDataProvider.getEmployeeNamesList("Sales"));
                   // setCreatedMemebers(datasourceDataProvider.getAllSalesTeamExceptAccountTeam());
                     setCreatedMemebers(datasourceDataProvider.getEmployeeNamesBySalesRoleId());
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
	
    public String getRequirementDetails() {
        resultType = LOGIN;
            //System.out.println("getRequirementDetails method");
            
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            setUserWorkCountry(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_GET_REQUIREMENT",userRoleId)){
                try {
                   
                        resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        
         if(httpServletRequest.getParameter("objectId")!=null){
               String objectId = httpServletRequest.getParameter("objectId");
               String requirementAdminFlag = httpServletRequest.getParameter("requirementAdminFlag");
               String recruitmentRoleType = httpServletRequest.getParameter("recruitmentRoleType");
               //System.out.println("roleType--"+recruitmentRoleType);
               setObjectId(objectId);
               setRequirementAdminFlag(requirementAdminFlag);
               setRecruitmentRoleType(recruitmentRoleType);
               
         }
       //  System.out.println("method resultType----"+resultType);
        return resultType;
    }
    
    
    
  public String getRequirementAttachment() {
        resultType = LOGIN;
        //    System.out.println("getRequirementAttachment method");
         if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            setUserWorkCountry(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_GET_REQUIREMENT",userRoleId)){
                try {
                 //   System.out.println("getObjectId "+getObjectId());
                  if(httpServletRequest.getParameter("oppFlag")!=null && "opp".equals(httpServletRequest.getParameter("oppFlag")))
                  {
                      setTitle(DataSourceDataProvider.getInstance().getOpportunityTitle(Integer.parseInt(getObjectId())));
                  }
                  else
                  {
                   setTitle(DataSourceDataProvider.getInstance().getRequirementTitle(getObjectId()));
                  }
                  
                   setObjectId(getObjectId());
                   setAccId(getAccId());
                        resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
       //  System.out.println("method resultType----"+resultType);
        return resultType;
    }
    
     public String doRequirementAttachmentAdd() {
        resultType = LOGIN;
        //   if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            try {
              
                attachmentService = ServiceLocator.getAttachmentService();
                //  setCurrentTask(tasksVTO);
                generatedPath = null;
                   setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
              
                //setObjectid(getObjectId());
                 setObjectId(getObjectId());
                 setAccId(getAccId());
                 
                 setTitle(getTitle());
                 
                   //     System.out.println("setTitle "+getTitle());
                 if (getUploadFileName() != null) {
                    String theFile;
                    if ("opp".equals(getOppFlag())) {
                          generatedPath = Properties.getProperty("Attachments.Opportunity.Path")+ "//MirageV2//" + "opportunity Attatchments";
                        theFile = FileUploadUtility.getInstance().filePathGeneration(generatedPath);
                    } else {
                         generatedPath = Properties.getProperty("Attachments.Requirement.Path")+ "//MirageV2//" + "requirement Attatchments";
                       theFile = FileUploadUtility.getInstance().filePathGeneration(generatedPath);
                    }
                    String fileName=FileUploadUtility.getInstance().fileNameGeneration(getUploadFileName());
                    File targetDirectory = new File(theFile + Properties.getProperty("OS.Compatabliliy.Download") +fileName);

                   

                    //  File targetDirectory = new File(generatedPath + Properties.getProperty("OS.Compatabliliy.Download") + getUploadFileName());
                    setFileLocation(targetDirectory.toString());
                    FileUtils.copyFile(getUpload(), targetDirectory);

                    //     setObjectType("Emp Task");
                } 
                 else {
               //     this.objectType = "NoFile";
                    this.setFileLocation("");
                    this.setFilepath("");
                    this.setAttachmentName("");
                }

                ServiceLocator.getRequirementService().addReqAttachmentLocation(this);
                httpServletRequest.getSession(false).setAttribute("resultMsg", "Attachment uploaded successfully");
                // System.out.println("After insertion");
               
                    if("opp".equals(getOppFlag()))
                    {
                        resultType="Opp";
                    }
                   
                    else
                    {
                        resultType="Req";
                    }
                setResultMessage("Attachment uploaded successfully");
            } catch (Exception ex) {
             //   System.out.println("Exception "+ex);
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
    }
	
 public String getRequirementListForPresalesMy() {
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            userWorkCountry= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_SEARCH_REQUIREMENT",userRoleId)){
                try {
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    queryStringBuffer =new StringBuffer();
                  
                   // httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QUERY_STRING,queryStringBuffer.toString());
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    setAssignedByMap(datasourceDataProvider.getRequirementAdminMap());
                    
                    setAssignedMembers(datasourceDataProvider.getEmployeeNamesList("Recruiting"));
                  //  setTechLeadList(datasourceDataProvider.getTechLead());
                    //setCreatedMemebers(datasourceDataProvider.getEmployeeNamesList("Sales"));
                   // setCreatedMemebers(datasourceDataProvider.getAllSalesTeamExceptAccountTeam());
                     setCreatedMemebers(datasourceDataProvider.getEmployeeNamesBySalesRoleId());
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    ex.printStackTrace();
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
 
   public String getDownloadAuthCopy() throws ServiceLocatorException{
        
         String resultMessage= null;
       resultType = INPUT;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
           // userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String responseString = "";
            try {
                String fileLocation = "";
                //fileLocation = ServiceLocator.getMarketingService().getCampaignContactsExcel(httpServletRequest,getCampaignId());
                
             //   System.out.println(getRequirementId()+"-------------------------------"+getConsultantId());
                fileLocation = ServiceLocator.getRequirementService().getAuthCopyLocation(this);
                httpServletResponse.setContentType("application/force-download");

                if (!"".equals(fileLocation)) {
                    File file = new File(fileLocation);

                    String fileName = "";

                    fileName = file.getName();
                   // System.out.println("fileName...."+fileName);
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
resultType = SUCCESS;
                    } else {
                        resultType = ERROR;
                     //   throw new FileNotFoundException("File not found");
                    }
                } else {
resultType = ERROR;
                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! No records for this Search!</font>";
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                }


            } catch (FileNotFoundException ex) {
                try {
                    resultType = ERROR;
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
        }
        return null;
     }
     
     
      public String getDownloadDLCopy() throws ServiceLocatorException, FileNotFoundException, IOException{
        
         String resultMessage= null;
       resultType = INPUT;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
           // userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String responseString = "";
            try {
                String fileLocation = "";
                //fileLocation = ServiceLocator.getMarketingService().getCampaignContactsExcel(httpServletRequest,getCampaignId());
              //   System.out.println(getRequirementId()+"-----///////------"+getConsultantId());
                fileLocation = ServiceLocator.getRequirementService().getDLCopyLocation(this);
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
resultType = SUCCESS;
                    } else {
                        resultType = ERROR;
                     //   throw new FileNotFoundException("File not found");
                    }
                } else {
resultType = ERROR;
                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! No records for this Search!</font>";
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);

                }


            } catch (FileNotFoundException ex) {
                try {
                    resultType = ERROR;
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
        }
        return null;
     }
    

    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.setHttpServletRequest(httpServletRequest);
    }
    
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
    
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public List getStatesList() {
        return statesList;
    }
    
    public void setStatesList(List statesList) {
        this.statesList = statesList;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
    
    public int getUserRoleId() {
        return userRoleId;
    }
    
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getPracticeId() {
        return practiceId;
    }
    
    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
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
    
    public String getContactNo() {
        return contactNo;
    }
    
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    
    public String getAssignedTo() {
        return assignedTo;
    }
    
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
    
    public int getNoResumes() {
        return noResumes;
    }
    
    public void setNoResumes(int noResumes) {
        this.noResumes = noResumes;
    }
    
    public String getFunctions() {
        return functions;
    }
    
    public void setFunctions(String functions) {
        this.functions = functions;
    }
    
    public String getResponsibilities() {
        return responsibilities;
    }
    
    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
    
    public String getEducation() {
        return education;
    }
    
    public void setEducation(String education) {
        this.education = education;
    }
    
    public String getExperience() {
        return experience;
    }
    
    public void setExperience(String experience) {
        this.experience = experience;
    }
    
    public String getTaxTerm() {
        return taxTerm;
    }
    
    public void setTaxTerm(String taxTerm) {
        this.taxTerm = taxTerm;
    }
    
    public String getTargetRate() {
        return targetRate;
    }
    
    public void setTargetRate(String targetRate) {
        this.targetRate = targetRate;
    }
    
    public String getTargetSalary() {
        return targetSalary;
    }
    
    public void setTargetSalary(String targetSalary) {
        this.targetSalary = targetSalary;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getSkills() {
        return skills;
    }
    
    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    public List getAssignedMembers() {
        return assignedMembers;
    }
    
    public void setAssignedMembers(List assignedMembers) {
        this.assignedMembers = assignedMembers;
    }
    
    public String getObjectId() {
        return objectId;
    }
    
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    
    public String getResultMessage() {
        return resultMessage;
    }
    
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    
    public RequirementVTO getCurrentRequirement() {
        return currentRequirement;
    }
    
    public void setCurrentRequirement(RequirementVTO currentRequirement) {
        this.currentRequirement = currentRequirement;
    }
    
    public String getCurrentAction() {
        return actionType;
    }
    
    public void setCurrentAction(String actionType) {
        this.actionType = actionType;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public Map getCreatedMemebers() {
        return createdMemebers;
    }
    
    public void setCreatedMemebers(Map createdMemebers) {
        this.createdMemebers = createdMemebers;
    }
    
    public String getPostedDate1() {
        return postedDate1;
    }
    
    public void setPostedDate1(String postedDate1) {
        this.postedDate1 = postedDate1;
    }
    
    public String getPostedDate2() {
        return postedDate2;
    }
    
    public void setPostedDate2(String postedDate2) {
        this.postedDate2 = postedDate2;
    }
    
    public String getRequirementId() {
        return requirementId;
    }
    
    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }
    
    public String getConsultantId() {
        return consultantId;
    }
    
    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }
    
    public int getTechRate() {
        return techRate;
    }
    
    public void setTechRate(int techRate) {
        this.techRate = techRate;
    }
    
    public String getEmail2() {
        return email2;
    }
    
    public void setEmail2(String email2) {
        this.email2 = email2;
    }
    
    public String getResumeId() {
        return resumeId;
    }
    
    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }
    
    public String getResumes() {
        return resumes;
    }
    
    public void setResumes(String resumes) {
        this.resumes = resumes;
    }
    
    public String getConsultId() {
        return consultId;
    }
    
    public void setConsultId(String consultId) {
        this.consultId = consultId;
    }
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }
    
    public String getUserWorkCountry() {
        return userWorkCountry;
    }
    
    public void setUserWorkCountry(String userWorkCountry) {
        this.userWorkCountry = userWorkCountry;
    }
    
    public String getAssignToTechLead() {
        return assignToTechLead;
    }
    
    public void setAssignToTechLead(String assignToTechLead) {
        this.assignToTechLead = assignToTechLead;
    }
    
    public String getSecondaryRecruiter() {
        return secondaryRecruiter;
    }
    
    public void setSecondaryRecruiter(String secondaryRecruiter) {
        this.secondaryRecruiter = secondaryRecruiter;
    }
    
    public String getSecondaryTechLead() {
        return secondaryTechLead;
    }
    
    public void setSecondaryTechLead(String secondaryTechLead) {
        this.secondaryTechLead = secondaryTechLead;
    }
    
    public List getTechLeadList() {
        return techLeadList;
    }
    
    public void setTechLeadList(List techLeadList) {
        this.techLeadList = techLeadList;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getDivType() {
        return divType;
    }
    
    public void setDivType(String divType) {
        this.divType = divType;
    }
    
    public String getRejectReason() {
        return rejectReason;
    }
    
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
    
    public int getAccId() {
        return accId;
    }
    
    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    //new
      public void setAjaxId(String ajaxId) {
        this.ajaxId = ajaxId;
    }

    public String getAjaxId() {
        return ajaxId;
    }

    public String getResumeRecId() {
        return resumeRecId;
    }

    public void setResumeRecId(String resumeRecId) {
        this.resumeRecId = resumeRecId;
    }

    public String getResumeConsultantId() {
        return resumeConsultantId;
    }

    public void setResumeConsultantId(String resumeConsultantId) {
        this.resumeConsultantId = resumeConsultantId;
    }

    public String getResumeAttachmentId() {
        return resumeAttachmentId;
    }

    public void setResumeAttachmentId(String resumeAttachmentId) {
        this.resumeAttachmentId = resumeAttachmentId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getResumeRequirementId() {
        return resumeRequirementId;
    }

    public void setResumeRequirementId(String resumeRequirementId) {
        this.resumeRequirementId = resumeRequirementId;
    }

    /**
     * @return the requirementAdminFlag
     */
    public String getRequirementAdminFlag() {
        return requirementAdminFlag;
    }

    /**
     * @param requirementAdminFlag the requirementAdminFlag to set
     */
    public void setRequirementAdminFlag(String requirementAdminFlag) {
        this.requirementAdminFlag = requirementAdminFlag;
    }
    
    public String getRecruiterComments() {
        return recruiterComments;
    }

    public void setRecruiterComments(String recruiterComments) {
        this.recruiterComments = recruiterComments;
    }


    public String getSecondarySkills() {
        return secondarySkills;
    }

    public void setSecondarySkills(String secondarySkills) {
        this.secondarySkills = secondarySkills;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Map getAssignedByMap() {
        return assignedByMap;
    }

    public void setAssignedByMap(Map assignedByMap) {
        this.assignedByMap = assignedByMap;
    }

    public Map getPreSalesMap() {
        return preSalesMap;
    }

    public void setPreSalesMap(Map preSalesMap) {
        this.preSalesMap = preSalesMap;
    }

    public List getConsultantStatusList() {
        return consultantStatusList;
    }

    public void setConsultantStatusList(List consultantStatusList) {
        this.consultantStatusList = consultantStatusList;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getCurrentEmployer() {
        return currentEmployer;
    }

    public void setCurrentEmployer(String currentEmployer) {
        this.currentEmployer = currentEmployer;
    }

    public String getWorkAuthorization() {
        return workAuthorization;
    }

    public void setWorkAuthorization(String workAuthorization) {
        this.workAuthorization = workAuthorization;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getRecruitmentRoleType() {
        return recruitmentRoleType;
    }

    public void setRecruitmentRoleType(String recruitmentRoleType) {
        this.recruitmentRoleType = recruitmentRoleType;
    }

    /**
     * @return the uploadFileName
     */
    public String getUploadFileName() {
        return uploadFileName;
    }

    /**
     * @param uploadFileName the uploadFileName to set
     */
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    /**
     * @return the fileLocation
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * @param fileLocation the fileLocation to set
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath the filepath to set
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @return the attachmentName
     */
    public String getAttachmentName() {
        return attachmentName;
    }

    /**
     * @param attachmentName the attachmentName to set
     */
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    /**
     * @param upload the upload to set
     */
    public void setUpload(File upload) {
        this.upload = upload;
    }

    /**
     * @return the attachmentType
     */
    public String getAttachmentType() {
        return attachmentType;
    }

    /**
     * @param attachmentType the attachmentType to set
     */
    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    /**
     * @return the oppFlag
     */
    public String getOppFlag() {
        return oppFlag;
    }

    /**
     * @param oppFlag the oppFlag to set
     */
    public void setOppFlag(String oppFlag) {
        this.oppFlag = oppFlag;
    }

    /**
     * @return the upload
     */
    public File getUpload() {
        return upload;
    }

    /**
     * @return the clientMap
     */
    public Map getClientMap() {
        return clientMap;
    }

    /**
     * @param clientMap the clientMap to set
     */
    public void setClientMap(Map clientMap) {
        this.clientMap = clientMap;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
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
     * @return the skypeId
     */
    public String getSkypeId() {
        return skypeId;
    }

    /**
     * @param skypeId the skypeId to set
     */
    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }

    /**
     * @return the currentLocation
     */
    public String getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @param currentLocation the currentLocation to set
     */
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * @return the workAuthorizationCopyAttachment
     */
    public String getWorkAuthorizationCopyAttachment() {
        return workAuthorizationCopyAttachment;
    }

    /**
     * @param workAuthorizationCopyAttachment the workAuthorizationCopyAttachment to set
     */
    public void setWorkAuthorizationCopyAttachment(String workAuthorizationCopyAttachment) {
        this.workAuthorizationCopyAttachment = workAuthorizationCopyAttachment;
    }

    /**
     * @return the workAuthorizationCopy
     */
    public String getWorkAuthorizationCopy() {
        return workAuthorizationCopy;
    }

    /**
     * @param workAuthorizationCopy the workAuthorizationCopy to set
     */
    public void setWorkAuthorizationCopy(String workAuthorizationCopy) {
        this.workAuthorizationCopy = workAuthorizationCopy;
    }

    /**
     * @return the onProject
     */
    public int getOnProject() {
        return onProject;
    }

    /**
     * @param onProject the onProject to set
     */
    public void setOnProject(int onProject) {
        this.onProject = onProject;
    }

    /**
     * @return the dlCopyAttachedAttachment
     */
    public String getDlCopyAttachedAttachment() {
        return dlCopyAttachedAttachment;
    }

    /**
     * @param dlCopyAttachedAttachment the dlCopyAttachedAttachment to set
     */
    public void setDlCopyAttachedAttachment(String dlCopyAttachedAttachment) {
        this.dlCopyAttachedAttachment = dlCopyAttachedAttachment;
    }

    /**
     * @return the dlCopyAttached
     */
    public String getDlCopyAttached() {
        return dlCopyAttached;
    }

    /**
     * @param dlCopyAttached the dlCopyAttached to set
     */
    public void setDlCopyAttached(String dlCopyAttached) {
        this.dlCopyAttached = dlCopyAttached;
    }

    /**
     * @return the projectEnd
     */
    public Date getProjectEnd() {
        return projectEnd;
    }

    /**
     * @param projectEnd the projectEnd to set
     */
    public void setProjectEnd(Date projectEnd) {
        this.projectEnd = projectEnd;
    }

    /**
     * @return the relocation
     */
    public String getRelocation() {
        return relocation;
    }

    /**
     * @param relocation the relocation to set
     */
    public void setRelocation(String relocation) {
        this.relocation = relocation;
    }

    /**
     * @return the changeReason
     */
    public String getChangeReason() {
        return changeReason;
    }

    /**
     * @param changeReason the changeReason to set
     */
    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    /**
     * @return the yearOfCompletion
     */
    public int getYearOfCompletion() {
        return yearOfCompletion;
    }

    /**
     * @param yearOfCompletion the yearOfCompletion to set
     */
    public void setYearOfCompletion(int yearOfCompletion) {
        this.yearOfCompletion = yearOfCompletion;
    }

    /**
     * @return the availability
     */
    public String getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    /**
     * @return the startDatetoUs
     */
    public Date getStartDatetoUs() {
        return startDatetoUs;
    }

    /**
     * @param startDatetoUs the startDatetoUs to set
     */
    public void setStartDatetoUs(Date startDatetoUs) {
        this.startDatetoUs = startDatetoUs;
    }

    /**
     * @return the educationDetails
     */
    public String getEducationDetails() {
        return educationDetails;
    }

    /**
     * @param educationDetails the educationDetails to set
     */
    public void setEducationDetails(String educationDetails) {
        this.educationDetails = educationDetails;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return the consultantName
     */
    public String getConsultantName() {
        return consultantName;
    }

    /**
     * @param consultantName the consultantName to set
     */
    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }
    
   
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    
}
