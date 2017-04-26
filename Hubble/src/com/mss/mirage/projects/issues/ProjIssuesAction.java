/*
 * ProjIssuesAction.java
 *
 * Created on April 23, 2008, 3:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.projects.issues;

import com.mss.mirage.crm.attachments.AttachmentService;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import com.mss.mirage.util.DateUtility;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author miracle
 */
public class ProjIssuesAction extends ActionSupport implements ServletRequestAware{
    
    private int id;
    private String title;
    private String categoryId;
    
    private String department;
    private String organization;
    private String createdBy;
    private String dateCreated;
    private String dateAssigned;
    private String dateClosed;
    private String assignedToUID;
    private String status;
    private String comments;
    private int attachmentId;
    private String description;
    private String operationType;
    private String requestType;
    
    private String issueId;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String attachmentName;
    private int objectid;
    private String objectType;
    
    private Timestamp date;
    private String filepath;
    private String fileLocation;
    private Map assignedToIdMap;
    private List issueCategoryList;
    private List issueStatusList;
    private List departmentList;
    private List organizationList;
    private List managersList;
    private HibernateDataProvider hibernateDataProvider;
    private DataSourceDataProvider dataSourceDataProvider;
    
    private String resultMessage;
    private String resultType;
    private HttpServletRequest httpServletRequest;
    
    private ProjIssuesVTO currentIssue;
    private String currentAction;
    private List attachedFiles;
    private String submitFrom;
    private String currentSearch;
    private StringBuilder stringBuilder;
    private AttachmentService attachmentService;
    private String generatedPath;
    private int userRoleId;
    
    // Issue Modified
    
    private String subCategoryId;
    private String typeId;
    private String statusId;
    private String resolution;
    private String severityId;
    private String accessType;
    private String navigateTo;
    
    private Date startDate;
    private Date endDate;
    
    private String dateWithOutTime;
    
    private Map assignMembersMap  = new TreeMap();
    
    private String addBtnVisible;
    private String userRoleName;
    private List projectsList;
    private List subProjectsList;
    private List mapList;
    private int customerId;
    private String issueType;
    
    private int projectId;
    private int subProjectId;
    private int projectMapId;
    
//    private String mapId;
//    private String issueName;
    private int accountId;
    
//    private int issueId;
//    private int projectId;
//    private int subProjectId;
//    private int accountId;
//    private String accessType;
    private int mapId;
    private String navigate;
    private String valid;
    private String validity;
    private String mapNameId;
    
    /* The String accountName is used to store the name for partcular Account in the database*/
    private String accountName;
    /* The String projectName is used to store the name for partcular Project in the database*/
    private String projectName;
    /* The String projectName is used to store the name for partcular SubProject in the database*/
    private String subProjectName;
    /* The String mapName is used for storing the MapName of Map */
    private String mapName;
    /* The String issueName is used for storing the issueName for Issues */
    private String issueName;
    
    private ProjIssuesService projIssuesService;
    
    /** Creates a new instance of ProjIssuesAction */
    public ProjIssuesAction() {
    }
    
    public String prepare(){
        resultType = LOGIN;
        if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)){
            //customerId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ACC_ID).toString());
            userRoleName= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PROJ_PREPARE_ISSUE",userRoleId)){
                try{
                    if(issueId == null || issueId.equals(""))
                        currentAction = "addIssue";
                    else
                        currentAction = "editIssue";
                    
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    
                    if("Customer Manager".equalsIgnoreCase(userRoleName)){
                        customerId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ACC_ID).toString()); 
                    }else{
                        customerId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PROJ_ISSUE_ACCOUNT_ID).toString());                     
                    }
                    
                    projIssuesService = ServiceLocator.getProjIssuesService();
                    
                    setProjectsList(dataSourceDataProvider.getCustomerProjects(customerId));
                    setSubProjectsList(dataSourceDataProvider.getCustomerSubProjects(customerId));
                    setMapList(dataSourceDataProvider.getCustomerMaps(customerId));
                    //setIssueCategoryList(hibernateDataProvider.getIssueCategories("categoryKey"));
                    //setIssueStatusList(hibernateDataProvider.getIssueStatus("issueStatusKey"));
                    //setDepartmentList(hibernateDataProvider.getDepartment("departmentKey"));
                    //setOrganizationList(hibernateDataProvider.getLkOrganization("lkOrganizationKey"));
                    //setAssignMembersMap(dataSourceDataProvider.getAssingnedMembers());
                    setManagersList(dataSourceDataProvider.AddAssignedMembers("managersKey"));
                    
                    String dueDate ="";
                    DateUtility date = DateUtility.getInstance();
                    dueDate = date.sqlTimeStampTousTimeStamp(date.getCurrentMySqlDateTime().toString());
                    dateWithOutTime = dueDate.substring(0,dueDate.length());
                    
                    setAccountName(hibernateDataProvider.getAccountName(getAccountId()));
                    setProjectName(hibernateDataProvider.getProjectName(getProjectId()));
                    setSubProjectName(hibernateDataProvider.getSubProjectName(getSubProjectId()));
                    setMapName(hibernateDataProvider.getMapName(getMapId()));
                    
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ISSUE_NAVIGATE)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ISSUE_NAVIGATE);
                    }
                    
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
    
    public String getProjectIssue(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null){
            try{
                prepare();
                
                if(getMapId() == 0){
                    setMapName(" ");
                }
                
                //Setting ProjIssuesVTO Object
                setCurrentIssue(projIssuesService.getProject(this));
                setManagersList(dataSourceDataProvider.getProjectEmployeeName(getProjectId()));
                String getNavigate = "false";
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ISSUE_NAVIGATE)!=null){
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ISSUE_NAVIGATE);
                }
                if("true".equalsIgnoreCase(getNavigate())){
                    getNavigate = "true";
                    setId(getProjectId());
                    setAccountName(hibernateDataProvider.getAccountName(getAccountId()));
                    setProjectName(hibernateDataProvider.getProjectName(getId()));
                    setSubProjectName(hibernateDataProvider.getSubProjectName(getSubProjectId()));
                    setMapName(hibernateDataProvider.getMapName(getMapId()));
                    
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ISSUE_NAVIGATE,getNavigate);
                }else{
                    getNavigate = "false";
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ISSUE_NAVIGATE,getNavigate);
                }
                
                
                if("true".equalsIgnoreCase(getValid())){
                    setValidity("GetData");
                }else{
                    setValidity("NoData");
                }
                
                
                resultType = SUCCESS;
            }catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
            
        }//Close Session Checking
        return resultType;
    }
    
    public String myIssues(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userRoleName= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            String userId;
            
            if("Customer Manager".equalsIgnoreCase(userRoleName))
                userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString();
            else
                userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            
            
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PROJ_PREPARE_MY_ISSUES",userRoleId)){
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                    /* Search Query Based on SearchForm */
                    if(getSubmitFrom() == null){
                        /*
                        // if role is Employee
                        if(userRoleId !=1){
                            stringBuilder.append("SELECT Id,CreatedBy,DateCreated,Status,AssignedTo,Description FROM tblPrjIssues");
                            stringBuilder.append(" where CreatedBy='"+userId+"' ORDER BY DateCreated DESC ");
                        }
                        // if role is OperationExecutive
                        if(userRoleId==1){
                            stringBuilder.append("SELECT Id,CreatedBy,DateCreated,Status,AssignedTo,Description FROM tblPrjIssues ORDER BY DateCreated DESC ");
                        }*/
                        
                        if("All".equalsIgnoreCase(getIssueType())){
                            stringBuilder.append("SELECT Id,CreatedBy,DateCreated,Status,AssignedTo,Description FROM tblPrjIssues ORDER BY DateCreated DESC ");
                        }else if("My".equalsIgnoreCase(getIssueType())){
                            stringBuilder.append("SELECT Id,CreatedBy,DateCreated,Status,AssignedTo,Description FROM tblPrjIssues");
                            stringBuilder.append(" where CreatedBy='"+userId+"' ORDER BY DateCreated DESC ");
                        }else if("Bug".equalsIgnoreCase(getIssueType())){
                            stringBuilder.append("SELECT Id,CreatedBy,DateCreated,Status,AssignedTo,Description,IssueType FROM tblPrjIssues");
                            stringBuilder.append(" where IssueType = 'Bug' ORDER BY DateCreated DESC ");
                        }
                    }
                    
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST,stringBuilder.toString());
                    /*Only Operations and Admin can Search all Issues
                     * 3 means Operations
                     * 1 means Admin
                     */
                    
                    if(userRoleId==1) setCurrentSearch("doSearchIssues");
                    
                    /*Remaining Roles Can Search their issues only
                     * 3 means Operations
                     * 1 means Admin
                     */
                    if(userRoleId!=1) setCurrentSearch("doSearchEmpIssues");
                    setAddBtnVisible("MyIssues");
                    
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
    
    public String doSearchIssues()  {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PROJ_DO_SEARCH_ISSUES",userRoleId)){
                try{
                    if("Search".equalsIgnoreCase(getSubmitFrom())) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("SELECT Id,CreatedBy,DateCreated,Status,AssignedTo,Description FROM tblEmpIssues");
                        if(null == getStatusId()) setStatusId("");
                        if(null == getCategoryId()) setCategoryId("");
                        
                        
                        if(!"".equals(getStatusId()) || !"".equals(getCategoryId()) || getStartDate()!=null || getEndDate()!=null)
                            stringBuilder.append(" WHERE ");
                        
                        boolean andFlag = false;
                        
                        if(!"".equals(getStatusId()) && !andFlag) {
                            stringBuilder.append("Status LIKE '" + getStatusId() + "' ");
                            andFlag = true;
                        } else  if(!"".equals(getStatusId()) && andFlag){
                            stringBuilder.append("AND Status LIKE '" + getStatusId() + "' ");
                        }
                        
                        if(!"".equals(getCategoryId()) && !andFlag) {
                            stringBuilder.append("Category LIKE '" + getCategoryId() + "' ");
                            andFlag = true;
                        } else if(!"".equals(getCategoryId()) && andFlag){
                            stringBuilder.append("AND Category LIKE '" + getCategoryId() + "' ");
                        }
                        if(getStartDate()!=null && !andFlag){
                            stringBuilder.append("date(DateCreated) >= '"+getStartDate()+"' ");
                        } else if(getStartDate()!=null && andFlag){
                            stringBuilder.append("AND date(DateCreated) >= '"+getStartDate()+"' ");
                        }
                        
                        if(getEndDate()!=null && !andFlag){
                            stringBuilder.append("date(DateCreated) <= '"+getEndDate()+"' ");
                        } else if(getEndDate()!=null && andFlag){
                            stringBuilder.append("AND date(DateCreated) <= '"+getEndDate()+"' ");
                        }
                        stringBuilder.append("ORDER BY DateCreated DESC ");
                        
                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST)!=null){
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST);
                        }
                        
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST, stringBuilder.toString());
                    }
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
    
    
    public String doSearchEmployeeIssues() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userRoleName= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            String userId;
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PROJ_DO_SEARCH_EMP_ISSUES",userRoleId)){
                try{
                    if("Search".equalsIgnoreCase(getSubmitFrom())) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("SELECT Id,CreatedBy,DateCreated,Status,AssignedTo,Description FROM tblPrjIssues");
                        
                        if("Customer Manager".equalsIgnoreCase(userRoleName))
                            userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString();
                        else
                            userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                        
                        //String userId = httpServletRequest.getSession(false).getAttribute("userId").toString();
                        stringBuilder.append(" WHERE CreatedBy ='" + userId + "' ");
                        
                        if(null == getStatusId()) setStatusId("");
                        if(null == getCategoryId()) setCategoryId("");
                        
                        
                        boolean andFlag = false;
                        
                        
                        if(!"".equals(getStatusId()) && !andFlag) {
                            stringBuilder.append("AND Status LIKE '" + getStatusId() + "' ");
                            andFlag = true;
                        } else  if(!"".equals(getStatusId()) && andFlag){
                            stringBuilder.append("AND Status LIKE '" + getStatusId() + "' ");
                        }
                        
                        if(!"".equals(getCategoryId()) && !andFlag) {
                            stringBuilder.append("AND Category LIKE '" + getCategoryId() + "' ");
                            andFlag = true;
                        } else if(!"".equals(getCategoryId()) && andFlag){
                            stringBuilder.append("AND Category LIKE '" + getCategoryId() + "' ");
                        }
                        if(getStartDate()!=null && !andFlag){
                            stringBuilder.append("AND date(DateCreated) >= '"+getStartDate()+"' ");
                        } else if(getStartDate()!=null && andFlag){
                            stringBuilder.append("AND date(DateCreated) >= '"+getStartDate()+"' ");
                        }
                        
                        if(getEndDate()!=null && !andFlag){
                            stringBuilder.append("AND date(DateCreated) <= '"+getEndDate()+"' ");
                        } else if(getEndDate()!=null && andFlag){
                            stringBuilder.append("AND date(DateCreated) <= '"+getEndDate()+"' ");
                        }
                        stringBuilder.append("ORDER BY DateCreated DESC ");
                        
                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST)!=null){
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST);
                        }
                        
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST, stringBuilder.toString());
                    }
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
    
    public String doAddIssue()  {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null){
            
            customerId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ACC_ID).toString());
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            
            resultType = "accessFailed";
            
            String userId;
            userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString();
            if(AuthorizationManager.getInstance().isAuthorizedUser("PROJ_DO_ADD_ISSUE",userRoleId)){
                try{
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    attachmentService = ServiceLocator.getAttachmentService();
                    
                    generatedPath = null;
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString());
                    setObjectid(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ACC_ID)));
                    setOperationType("Ins");
                    
                    setProjectId(dataSourceDataProvider.getProjectIdByName(getCategoryId()));
                    setSubProjectId(dataSourceDataProvider.getSubProjectIdByName(getSubCategoryId()));
                    setProjectMapId(dataSourceDataProvider.getMapIdByName(getMapNameId()));
                    
                    setAccountId(customerId);
                    setObjectType("Project Issues");
                    
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("SELECT Id,CreatedBy,DateCreated,Status,AssignedTo,Description FROM tblPrjIssues");
                    stringBuilder.append(" where CreatedBy='"+userId+"' ORDER BY DateCreated DESC ");
                    
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.PROJ_QS_ISSUES_LIST, stringBuilder.toString());
                    
                    if(getUploadFileName()!=null) {
                        generatedPath = attachmentService.generatePath(Properties.getProperty("Attachments.Path"), getObjectType());
                        File targetDirectory = new File(generatedPath + "/" + getUploadFileName());
                        setFileLocation(targetDirectory.toString());
                        setAttachmentName(getUploadFileName());
                        FileUtils.copyFile(getUpload(), targetDirectory);
                    } else {
                        this.fileLocation="";
                        this.filepath="";
                        this.attachmentName="";
                    }
                    if(ServiceLocator.getProjIssuesService().addOrUpdateIssue(this) > 0) {
                        resultMessage = "Issue has been successfully inserted!";
                        resultType = SUCCESS;
                    } else {
                        resultMessage = "Sorry! Please Try again!";
                        resultType = INPUT;
                    }
                    httpServletRequest.setAttribute("resultMessage", resultMessage);
                    httpServletRequest.setAttribute("accessType","Issue");
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
    
    public String doEditIssue() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null){
            
            customerId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ACC_ID).toString());
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("PROJ_DO_EDIT_ISSUE",userRoleId)){
                try{
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    attachmentService = ServiceLocator.getAttachmentService();
                    setOperationType("Upd");
                    
                    generatedPath = null;
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString());
                    setObjectid(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ACC_ID)));
                    
                    setProjectId(dataSourceDataProvider.getProjectIdByName(getCategoryId()));
                    setSubProjectId(dataSourceDataProvider.getSubProjectIdByName(getSubCategoryId()));
                    setProjectMapId(dataSourceDataProvider.getMapIdByName(getMapNameId()));
                    setAccountId(customerId);
                    
//                    setProjectName(hibernateDataProvider.getProjectName(getProjectId()));
//                    setSubProjectName(hibernateDataProvider.getSubProjectName(getSubProjectId()));
//                    setMapName(hibernateDataProvider.getMapName(getMapId()));
                    
                    if(ServiceLocator.getProjIssuesService().addOrUpdateIssue(this) > 0) {
                        resultMessage = "Issue has been successfully Updated!";
                        resultType = SUCCESS;
                    } else {
                        resultMessage = "Sorry! Please Try again!";
                        resultType = INPUT;
                    }
                    httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
                    httpServletRequest.setAttribute("accessType",getAccessType());
                    
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
    
    public String getIssue(){
        resultType = LOGIN;        
            if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            userRoleName= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PROJ_GET_ISSUE",userRoleId)){
                
                try{
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    if(httpServletRequest.getAttribute("submitFrom") != null) httpServletRequest.removeAttribute("submitFrom");
                    
                    prepare();
                    
                    //setObjectid(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute("empId")));
                    if("Customer Manager".equalsIgnoreCase(userRoleName)){
                    setObjectid(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ACC_ID)));
                    }
                    else{
                    setObjectid(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PROJ_ISSUE_ACCOUNT_ID)));    
                    }
                    
                    //setObjectid(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute("empId")));
                    //setObjectid(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ACC_ID)));
                    
                    setCurrentIssue(ServiceLocator.getProjIssuesService().getIssue(getIssueId(), getObjectid()));
                    //System.out.println("my issue assigned "+getCurrentIssue().getAssignedToUID());
                    
                    setFileLocation(getCurrentIssue().getFileLocation());
                    
                    httpServletRequest.setAttribute("accessType",getAccessType());
                    
                    if("Task".equalsIgnoreCase(getAccessType())){
                        setNavigateTo("getMyTasks");
                    }else if("Issue".equalsIgnoreCase(getAccessType())){
                        setNavigateTo("getIssues");
                    }
                    
                    String getNavigate = "false";
                    
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ISSUE_NAVIGATE)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ISSUE_NAVIGATE);
                    }
                    if("true".equalsIgnoreCase(getNavigate())){
                        getNavigate = "true";
                        setId(getProjectId());
                        setAccountName(hibernateDataProvider.getAccountName(getAccountId()));
                        setProjectName(hibernateDataProvider.getProjectName(getId()));
                        setSubProjectName(hibernateDataProvider.getSubProjectName(getSubProjectId()));
                        setMapName(hibernateDataProvider.getMapName(getMapId()));
                        
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ISSUE_NAVIGATE,getNavigate);
                    }else{
                        getNavigate = "false";
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ISSUE_NAVIGATE,getNavigate);
                    }
                    
                    if("true".equalsIgnoreCase(getValid())){
                        setValidity("GetData");
                    }else{
                        setValidity("NoData");
                    }
                    
                    
                    
                    resultType=SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    public String getMyTaskList(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID) != null){
            resultType = SUCCESS;
        }//Close Session Checking
        return resultType;
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getOrganization() {
        return organization;
    }
    
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public String getDateCreated() {
        return dateCreated;
    }
    
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    public String getDateAssigned() {
        return dateAssigned;
    }
    
    public void setDateAssigned(String dateAssigned) {
        this.dateAssigned = dateAssigned;
    }
    
    public String getDateClosed() {
        return dateClosed;
    }
    
    public void setDateClosed(String dateClosed) {
        this.dateClosed = dateClosed;
    }
    
    public String getAssignedToUID() {
        return assignedToUID;
    }
    
    public void setAssignedToUID(String assignedToUID) {
        this.assignedToUID = assignedToUID;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public int getAttachmentId() {
        return attachmentId;
    }
    
    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Map getAssignedToIdMap() {
        return assignedToIdMap;
    }
    
    public void setAssignedToIdMap(Map assignedToIdMap) {
        this.assignedToIdMap = assignedToIdMap;
    }
    
    public List getIssueCategoryList() {
        return issueCategoryList;
    }
    
    public void setIssueCategoryList(List issueCategoryList) {
        this.issueCategoryList = issueCategoryList;
    }
    
    public List getIssueStatusList() {
        return issueStatusList;
    }
    
    public void setIssueStatusList(List issueStatusList) {
        this.issueStatusList = issueStatusList;
    }
    
    public List getDepartmentList() {
        return departmentList;
    }
    
    public void setDepartmentList(List departmentList) {
        this.departmentList = departmentList;
    }
    
    public File getUpload() {
        return upload;
    }
    
    public void setUpload(File upload) {
        this.upload = upload;
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
    
    public int getObjectid() {
        return objectid;
    }
    
    public void setObjectid(int objectid) {
        this.objectid = objectid;
    }
    
    public String getObjectType() {
        return objectType;
    }
    
    public void setObjectType(String objectType) {
        this.objectType = objectType;
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
    
    public String getOperationType() {
        return operationType;
    }
    
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    
    public List getOrganizationList() {
        return organizationList;
    }
    
    public void setOrganizationList(List organizationList) {
        this.organizationList = organizationList;
    }
    
    public String getIssueId() {
        return issueId;
    }
    
    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }
    
    
    
    public List getManagersList() {
        return managersList;
    }
    
    public void setManagersList(List managersList) {
        this.managersList = managersList;
    }
    
    public String getCurrentAction() {
        return currentAction;
    }
    
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }
    
    public List getAttachedFiles() {
        return attachedFiles;
    }
    
    public void setAttachedFiles(List attachedFiles) {
        this.attachedFiles = attachedFiles;
    }
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }
    
    public String getCurrentSearch() {
        return currentSearch;
    }
    
    public void setCurrentSearch(String currentSearch) {
        this.currentSearch = currentSearch;
    }
    
    public String getSubCategoryId() {
        return subCategoryId;
    }
    
    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
    
    public String getTypeId() {
        return typeId;
    }
    
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    
    public String getStatusId() {
        return statusId;
    }
    
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
    
    public String getResolution() {
        return resolution;
    }
    
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
    
    public String getSeverityId() {
        return severityId;
    }
    
    public void setSeverityId(String severityId) {
        this.severityId = severityId;
    }
    
    public String getDateWithOutTime() {
        return dateWithOutTime;
    }
    
    public void setDateWithOutTime(String dateWithOutTime) {
        this.dateWithOutTime = dateWithOutTime;
    }
    
    public String getAccessType() {
        return accessType;
    }
    
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
    
    public Map getAssignMembersMap() {
        return assignMembersMap;
    }
    
    public void setAssignMembersMap(Map assignMembersMap) {
        this.assignMembersMap = assignMembersMap;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String getAddBtnVisible() {
        return addBtnVisible;
    }
    
    public void setAddBtnVisible(String addBtnVisible) {
        this.addBtnVisible = addBtnVisible;
    }
    
    public String getNavigateTo() {
        return navigateTo;
    }
    
    public void setNavigateTo(String navigateTo) {
        this.navigateTo = navigateTo;
    }
    
    public List getProjectsList() {
        return projectsList;
    }
    
    public void setProjectsList(List projectsList) {
        this.projectsList = projectsList;
    }
    
    public ProjIssuesVTO getCurrentIssue() {
        return currentIssue;
    }
    
    public void setCurrentIssue(ProjIssuesVTO currentIssue) {
        this.currentIssue = currentIssue;
    }
    
    public String getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    public int getProjectId() {
        return projectId;
    }
    
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    public String getIssueType() {
        return issueType;
    }
    
    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }
    
    public int getAccountId() {
        return accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    public int getSubProjectId() {
        return subProjectId;
    }
    
    public void setSubProjectId(int subProjectId) {
        this.subProjectId = subProjectId;
    }
    
    public int getProjectMapId() {
        return projectMapId;
    }
    
    public void setProjectMapId(int projectMapId) {
        this.projectMapId = projectMapId;
    }
    
    public List getSubProjectsList() {
        return subProjectsList;
    }
    
    public void setSubProjectsList(List subProjectsList) {
        this.subProjectsList = subProjectsList;
    }
    
    public List getMapList() {
        return mapList;
    }
    
    public void setMapList(List mapList) {
        this.mapList = mapList;
    }
    
    public String getNavigate() {
        return navigate;
    }
    
    public void setNavigate(String navigate) {
        this.navigate = navigate;
    }
    
    public String getAccountName() {
        return accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public String getSubProjectName() {
        return subProjectName;
    }
    
    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }
    
    public String getMapName() {
        return mapName;
    }
    
    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
    
    public String getIssueName() {
        return issueName;
    }
    
    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }
    
    public int getMapId() {
        return mapId;
    }
    
    public void setMapId(int mapId) {
        this.mapId = mapId;
    }
    
    public String getMapNameId() {
        return mapNameId;
    }
    
    public void setMapNameId(String mapNameId) {
        this.mapNameId = mapNameId;
    }
    
    public String getValid() {
        return valid;
    }
    
    public void setValid(String valid) {
        this.valid = valid;
    }
    
    public String getValidity() {
        return validity;
    }
    
    public void setValidity(String validity) {
        this.validity = validity;
    }
    
    
}
