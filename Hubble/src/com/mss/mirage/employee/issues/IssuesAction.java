

package com.mss.mirage.employee.issues;

import com.mss.mirage.crm.attachments.AttachmentService;
import com.mss.mirage.util.*;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.mss.mirage.util.DateUtility;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;


public class IssuesAction extends ActionSupport implements ServletRequestAware {
    private int id;
    private String strId;
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
    private String strAttachmentId;
    private String description;
    private String operationType;
    private String issueId;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String requestType;
    private String attachmentName;
    private int objectid;
    private String objectType;
    private Timestamp date;
    private String filepath;
    private String fileLocation;
    private Map assignedToIdMap;
    private Map projectNamesMap;
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
    
    private IssuesVTO currentIssue;
    private String currentAction;
    private List attachedFiles;
    private String submitFrom;
    private String currentSearch;
    private StringBuilder stringBuilder;
    private AttachmentService attachmentService;
    private String generatedPath;
    private int userRoleId;
    private String resM;
    private String resMsg;
    
    // Issue Modified
    
    private String subCategoryId;
    private String typeId;
    private String statusId;
    private String resolution;
    private String severityId;
    private String accessType;
    private String navigateTo;
    
     //New SubnavigateTo for pages based on team or my
    private String subnavigateTo;
    
    private String startDate;
    private String endDate;
    
    private String dateWithOutTime;
    
    private Map assignMembersMap  = new TreeMap();
    
    private String addBtnVisible;
    
    private String finalChar = null;
    
    private String empName = null;
    private String postAssignedToUID;
    private String preAssignEmpId;
    private String postAssignEmpId;
    private String issueName;
   /** The departmentIdList is used for storing departmentId Options.*/
    private List departmentIdList;
    private List practiceIdList;
    private String perCompleted;
  //  private HibernateDataProvider hibernateDataProvider;
    //New
     private int taskId;
     private int contactId;
     private String customerName;
     private String customerId;
    private String projectName;
    private String projectId;
    private Map projectMap;

    private int iFlag;
    private int activityId;
    private String navTo;
    
    //New variables to issue reminder 
    private int mailsent;
    private String message;
    private String issueReminderId;
    private String issueList;
    private String check;
    
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    } 

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getContactId() {
        return contactId;
    }
  
    
    /* private String editAction;
     
    public void setEditAction(String editAction) {
        this.editAction = editAction;
    }

    public String getEditAction() {
        return editAction;
    }*/
    
   
    //List of Activities for sales peple or for Recruitment people.
      /** The activityTypeList is used for storing activityType. */
    private List activityTypeList = new ArrayList();
 
    public IssuesAction() {
    }
    
    public String getMyTaskList(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            resultType = SUCCESS;
        }//Close Session Checking
        
        //httpServletRequest.getSession(false).setAttribute("getMyTasks",ApplicationConstants.ISSUE_TO_TASK);
        setSubnavigateTo("getMyTasks");
        return resultType;
    }
    public String getMyIssuesList(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            resultType = SUCCESS;
        }//Close Session Checking
        
       
        
        return resultType;
    }
    
  //  setIssuesadd("add");
    
     /**
     * NAME : createIssue()
     * DESC : To create Issue
     * Date : 06-07-2013
     */
    
    public String prepare() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_ISSUE",userRoleId)){
                try{
                   /* if(issueId == null || issueId.equals(""))
                        currentAction = "addIssue";
                    else {
                        currentAction = "editIssue";
                    }*/
                     if(id == 0)
                        currentAction = "addIssue";
                    else {
                        currentAction = "editIssue";
                    }
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    setIssueCategoryList(hibernateDataProvider.getIssueCategories("categoryKey"));
                    setIssueStatusList(hibernateDataProvider.getIssueStatus("issueStatusKey"));
                   // setDepartmentList(hibernateDataProvider.getDepartment("departmentKey"));
                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                    setPracticeIdList(dataSourceDataProvider.getPracticeByDepartment(getCategoryId()));
                   
                    setProjectNamesMap(dataSourceDataProvider.getProjectsByAccountId(getCustomerId()));
                    
                    setOrganizationList(hibernateDataProvider.getLkOrganization("lkOrganizationKey"));
                   // IssuesVTO issuesVTO = new IssuesVTO();
                    setDateAssigned(DateUtility.getInstance().getCurrentMySqlDateTime1());
                    setDateClosed(DateUtility.getInstance().getCurrentMySqlDateTime1());
                  
                    //setCurrentIssue(issuesVTO);
                   
                   
                   
                   
                    setActivityTypeList(hibernateDataProvider.getCrmActivityTypes(ApplicationConstants.CRM_ACTIVITY_TYPE_OPTIONS));
                    //setAssignMembersMap(dataSourceDataProvider.getAssingnedMembers());
                    //setManagersList(dataSourceDataProvider.AssignedMembers("managersKey"));
                    
                    //setAssignMembersMap(dataSourceDataProvider.getAssingnedMembers());
                     if(currentAction=="editIssue"){
                   // setManagersList(dataSourceDataProvider.EditAssignedMembers(currentIssue.getCategoryId()));
                        setManagersList(dataSourceDataProvider.EditAssignedMembers(getCategoryId()));
                     }
                    
                    if(currentAction=="addIssue"){
                      setManagersList(dataSourceDataProvider.AddAssignedMembers("managerskey"));  
                    }
                    
                    String dueDate ="";
                    DateUtility date = DateUtility.getInstance();
                    dueDate = date.sqlTimeStampTousTimeStamp(date.getCurrentMySqlDateTime().toString());
                    dateWithOutTime = dueDate.substring(0,dueDate.length());
                    
                    
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
    *  DESC:to ghet the Create issue Page
    *  Name : doCreateIssue()
    */
     public String doCreateIssue() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
             int isTeamLead = 0;
            int isManager = 0;
             isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
            isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
          //  if(AuthorizationManager.getInstance().isAuthorizedUserCreateIssue(isManager,isTeamLead,"CREATE_ISSUE")){
             if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_MY_ISSUES",userRoleId)){
                try{
                    if(getCurrentIssue() == null){
                       IssuesVTO issuesVTO = new IssuesVTO();
                       setCurrentIssue(issuesVTO);
                       
                        getCurrentIssue().setIFlag(0);
                   }else{
                       getCurrentIssue().setIFlag(0);
                   }
                   
                  prepare();
                  setNavTo("create");
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
    public String myIssues(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userName = null; 
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_MY_ISSUES",userRoleId)){
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                    userName = dataSourceDataProvider.getInstance().getFname_Lname(userId);
                    /* Search Query Based on SearchForm */
                    if(getSubmitFrom() == null){
                        
                            stringBuilder.append("SELECT Id,Category,IssueTitle,IssueType,DateCreated,STATUS,Severity,Comments,AssignedTo,SecAssignTO FROM tblEmpIssues");
                            stringBuilder.append(" where Status <> 'Closed' AND DateCreated > '2013-06-14 00:00:00' and (CreatedBy='"+userId+"' or AssignedTo='"+userId+"' or SecAssignTO = '"+userId+"') ORDER BY DateCreated DESC LIMIT 150");
                     
                        
                    }
                    
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ISSUES_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ISSUES_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ISSUES_LIST,stringBuilder.toString());
                    /*Only Operations and Admin can Search all Issues
                     * 3 means Operations
                     * 1 means Admin
                     */
                   
                     setCurrentSearch("doSearchEmpIssues");
                    /*Remaining Roles Can Search their issues only
                     * 3 means Operations
                     * 1 means Admin
                     */
                 /*   if(userRoleId!=1) setCurrentSearch("doSearchEmpIssues");
                    setAddBtnVisible("MyIssues");*/
                    
                   // httpServletRequest.getSession(false).setAttribute("getIssues",ApplicationConstants.ISSUE_TO_TASK);
                    setSubnavigateTo("getIssues");
                    
                   dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    setProjectNamesMap(dataSourceDataProvider.getProjectsByAccountId(getCustomerId()));
                    
                   //  setNavTo("getIssues");
                   //  httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ISSUENAVTO,"getIssue");
                       httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ISSUENAVTO,"doSearchEmpIssues");
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
    
    public String myTeamIssues(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            
            //session.setAttribute(ApplicationConstants.SESSION_MY_DEPT_ID,dbDepartmentId);
                         //   public static final String SESSION_MY_DEPT_ID = "myDeptId";
            String deptId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString();  
            
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_MYTEAM_ISSUES",userRoleId)){
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                    /* Search Query Based on SearchForm */ 
                    if(getSubmitFrom() == null){
                        /* if role is Employee  */
                        
                        stringBuilder.append("SELECT Id,Category,IssueTitle,IssueType,DateCreated,STATUS,Severity,Comments,AssignedTo,SecAssignTO FROM tblEmpIssues");
                        // changed on 11-01-2012
                           //stringBuilder.append(" where DateCreated > '2012-04-11 00:00:00' and CreatedBy IN (select LoginId FROM tblEmployee  WHERE ReportsTo ='"+userId+"')  ORDER BY DateCreated DESC LIMIT 150");      
                        stringBuilder.append(" where Status <> 'Closed' AND DateCreated > '2013-06-14 00:00:00' and ");//CreatedBy='"+userId+"' OR ");      
                        
                       
                        stringBuilder.append(" ((CreatedBy IN");
                        stringBuilder.append("(");
                        stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue(userId,deptId));
                        stringBuilder.append("))");
                        //stringBuilder.append(" OR AssignedTo ='"+userId+"'");
                        stringBuilder.append(" OR (AssignedTo IN");
                        stringBuilder.append("(");
                        stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue(userId,deptId));
                        stringBuilder.append("))");
                         //stringBuilder.append(" OR SecAssignTO ='"+userId+"'");
                        stringBuilder.append(" OR (SecAssignTO IN");
                        stringBuilder.append("(");
                        stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue(userId,deptId));
                        stringBuilder.append(")))");
                        stringBuilder.append(" ORDER BY DateCreated DESC LIMIT 150");
                       // stringBuilder.append(" where DATE_SUB(CURDATE(),INTERVAL 3 MONTH) <= DateCreated and CreatedBy IN ("+DataSourceDataProvider.getInstance().getLeaveApprovalList(userId)+")  ORDER BY DateCreated DESC LIMIT 150");      
                    }
                   /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ISSUES_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ISSUES_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ISSUES_LIST,stringBuilder.toString());
                    /*Only Operations and Admin can Search all Issues
                     * 3 means Operations
                     * 1 means Admin
                     */
                   // if(userRoleId==1) 
                       
                    
                    /*Remaining Roles Can Search their issues only
                     * 3 means Operations
                     * 1 means Admin
                     */
                   // if(userRoleId!=1)
                        setCurrentSearch("doSearchTeamIssues");
                    
                    setAddBtnVisible("TeamIssues");
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ISSUENAVTO,"doSearchTeamIssues");
                    setSubnavigateTo("getTeamIssues");
                    
                   // httpServletRequest.getSession(false).setAttribute("getTeamIssues",ApplicationConstants.ISSUE_TO_TASK);
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    setProjectNamesMap(dataSourceDataProvider.getProjectsByAccountId(getCustomerId()));
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
     *  Author : Nag
     *  Method : getMyTeamTasks
     *  Return : Team task list
     */
    
    public String getMyTeamTasks(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_MYTEAM_ISSUES",userRoleId)){
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                    /* Search Query Based on SearchForm */
                    if(getSubmitFrom() == null){
                       
                        String departmentName = DataSourceDataProvider.getInstance().getDepartmentName(userId);
                        if(departmentName.equalsIgnoreCase("Sales") || departmentName.equalsIgnoreCase("Admin")){
                        stringBuilder.append("SELECT tblEmpIssues.Id as Id,tblEmpIssues.Category as Category,tblEmpIssues.IssueType as IssueType,tblEmpIssues.DateCreated as DateCreated," +
                                "tblEmpIssues.CreatedBy as CreatedBy,tblEmpIssues.AssignedTo as AssignedTo,tblEmpIssues.Status as Status,tblEmpIssues.Description as Description,tblCrmAccount.Name as Name FROM " +
                                "(tblEmpIssues left join tblCrmActivity on (tblCrmActivity.Id=TaskID)left join tblCrmAccount on(tblCrmActivity.AccountId=tblCrmAccount.Id))");
                       //  Date changed : 01-11-2012 .
                        // stringBuilder.append(" where tblEmpIssues.DateCreated > '2012-04-11 00:00:00' and tblEmpIssues.AssignedTo IN ( SELECT CONCAT(TRIM(tblEmployee.FName),'.',TRIM(tblEmployee.LName)) AS NAME FROM tblEmployee where tblEmployee.ReportsTo ='"+userId+"')ORDER BY tblEmpIssues.DateCreated DESC LIMIT 150");
                        stringBuilder.append(" where tblEmpIssues.DateCreated > '2012-04-11 00:00:00' and tblEmpIssues.AssignedTo IN ("+ DataSourceDataProvider.getInstance().getEmpNameUsingReportsTo(userId)+")ORDER BY tblEmpIssues.DateCreated DESC LIMIT 150");
                        //stringBuilder.append(" where DATE_SUB(CURDATE(),INTERVAL 3 MONTH) <= tblEmpIssues.DateCreated and tblEmpIssues.AssignedTo IN ("+ DataSourceDataProvider.getInstance().getEmpNameUsingReportsTo(userId)+")ORDER BY tblEmpIssues.DateCreated DESC LIMIT 150");
                        }else{
                            stringBuilder.append("SELECT Id,Category,IssueType,DateCreated,CreatedBy,AssignedTo,Status,Description FROM tblEmpIssues");
                            // date chabged : 01-11-2012
                           // stringBuilder.append(" where DateCreated > '2012-04-11 00:00:00' and AssignedTo IN ( SELECT CONCAT(TRIM(FName),'.',TRIM(LName)) AS NAME FROM tblEmployee where ReportsTo ='"+userId+"') ORDER BY DateCreated DESC LIMIT 150");
                            stringBuilder.append(" where DateCreated > '2012-04-11 00:00:00' and AssignedTo IN ("+ DataSourceDataProvider.getInstance().getEmpNameUsingReportsTo(userId)+")ORDER BY DateCreated DESC LIMIT 150");
                           // stringBuilder.append(" where DATE_SUB(CURDATE(),INTERVAL 3 MONTH) <= DateCreated and AssignedTo IN ("+ DataSourceDataProvider.getInstance().getEmpNameUsingReportsTo(userId)+")ORDER BY DateCreated DESC LIMIT 150");
                        }

                        
                    }
                    
                    /*Removing Existed Session Object*/
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ISSUES_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ISSUES_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ISSUES_LIST,stringBuilder.toString());
                    /*Only Operations and Admin can Search all Issues
                     * 3 means Operations
                     * 1 means Admin
                     */
                    if(userRoleId==1) setCurrentSearch("doSearchIssues");
                    
                    /*Remaining Roles Can Search their issues only
                     * 3 means Operations
                     * 1 means Admin
                     */
                    if(userRoleId!=1) setCurrentSearch("doSearchTeamIssues");
                    
                    setAddBtnVisible("TeamTasks");
                     
                     // setNavTo("getIssues");
                     httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ISSUENAVTO,"doSearchTeamIssues");
                   setSubnavigateTo("getTeamTasks");
                   // httpServletRequest.getSession(false).setAttribute("getTeamTasks",ApplicationConstants.ISSUE_TO_TASK);
                    
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
    
    
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public String execute() throws Exception {
        
        return SUCCESS;
    }
    
    public String getIssue(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_ISSUE",userRoleId)){
                
                try{
                    
                    if(httpServletRequest.getAttribute("submitFrom") != null) httpServletRequest.removeAttribute("submitFrom");
                    // prepare();
                    setObjectid(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute("empId")));
                    
                    setCurrentIssue(ServiceLocator.getIssuesService().getIssue(getIssueId(), getObjectid()));
                    
                    setFileLocation(getCurrentIssue().getFileLocation());
                    
                    httpServletRequest.setAttribute("accessType",getAccessType());
                    
                    if(httpServletRequest.getSession(false).getAttribute("resultMessage")!=null){
                        httpServletRequest.getSession(false).removeAttribute("resultMessage");
                    }
                    
                    setCategoryId(getCurrentIssue().getCategoryId());
                    setCustomerId(getCurrentIssue().getCustomerId());
                    setIssueId(getCurrentIssue().getIssueName());
                    setId(getCurrentIssue().getId());
                   // System.out.println("-------currentSearch---"+getCurrentSearch());
                    
                    
                    setCurrentSearch(getCurrentSearch());
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ISSUENAVTO)!=null){
                     setNavTo(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ISSUENAVTO).toString());
                    }else{
                        setNavTo("");
                    }
                    prepare();
                    setDateClosed(getCurrentIssue().getClosed().toString());
                    
                    setDateAssigned(getCurrentIssue().getAssigned().toString());
                    resultType=SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        
        if(httpServletRequest.getParameter("issueId") != null){
            String issueId = httpServletRequest.getParameter("issueId");
            httpServletRequest.setAttribute("issueId",issueId);
            setIssueId(httpServletRequest.getAttribute("issueId").toString());
            String resM = httpServletRequest.getParameter("resM");
            httpServletRequest.setAttribute("resM",resM);
            setResM(httpServletRequest.getAttribute("resM").toString());
        }
        return resultType;
    }
    
    public String doAddIssue()  {
        resultType = LOGIN;
        int issueMaxId = 0;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_ISSUE",userRoleId)){
                try{
                    attachmentService = ServiceLocator.getAttachmentService();
                    
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                   
                    generatedPath = null;
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
                    setObjectid(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute("empId")));
                    setOperationType("Ins");
                    
                    
                    if(getUploadFileName()!=null) {
                        generatedPath = attachmentService.generatePath(Properties.getProperty("Attachments.Path"), "Emp Issues");
                        File targetDirectory = new File(generatedPath + "/" + getUploadFileName());
                        setFileLocation(targetDirectory.toString());
                        FileUtils.copyFile(getUpload(), targetDirectory);
                        setObjectType("Emp Issue");
                    } else {
                        this.objectType = "NoFile";
                        this.fileLocation="";
                        this.filepath="";
                        this.attachmentName="";                        
                    }
                    issueMaxId = ServiceLocator.getIssuesService().addOrUpdateIssue(this);
                   if(issueMaxId >= 0) {                       
                     
                       empName = getAssignedToUID();
                       if(empName != null){
                        //finalChar = dataSourceDataProvider.getLoginIdByEmpId(Integer.parseInt(getPreAssignEmpId()));
                        finalChar = getPreAssignEmpId();
                        finalChar = finalChar + "@miraclesoft.com";  
                        }
                      
                        if(!(empName.equals("")|| empName == null)){                        
                        //MailManager.sendIssueEmail(finalChar,login, passwd);
                       String workPhno= (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_WORKPHNO);
                       String userName=(String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME);
                       String teamName=dataSourceDataProvider.getInstance().getTeamNameByLoginId(getCreatedBy());
                        if(Properties.getProperty("Mail.Flag").equals("1")) {
                       MailManager.sendIssueEmail(finalChar,getPostAssignEmpId(),getCreatedBy(),getDescription(),getComments(),getCategoryId(),getSeverityId(),getTypeId(),userName,workPhno,teamName,getCustomerName(),getIssueName(),issueMaxId);
                        }
                      // MailManager.sendIssueEmail(finalChar,empName,getCreatedBy(),getDescription(),getComments(),getCategoryId(),getSeverityId(),getTypeId(),userName,workPhno,teamName,issueMaxId);
                        resultMessage = "Task has been successfully inserted and mail is sent!";
                        }else{
                            resultMessage = "Task has been successfully inserted!";
                         }
                        resultType = SUCCESS;
                    } else {
                        resultMessage = "Sorry! Please Try again!";
                        resultType = INPUT;
                    }
                   
                    httpServletRequest.setAttribute("resultMessage", resultMessage);
                    httpServletRequest.setAttribute("accessType","Issue");
                    prepare();
                     IssuesVTO issuesVTO = new IssuesVTO();
                       setCurrentIssue(issuesVTO);
                        getCurrentIssue().setIFlag(0);
                 
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
     //   System.out.println("In Do Edit Issue!!");
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_ISSUE",userRoleId)){
                try{
                    attachmentService = ServiceLocator.getAttachmentService();
                    setOperationType("Upd");
                    
                    generatedPath = null;
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
                    
                    setObjectid(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute("empId")));
                    
                    if(ServiceLocator.getIssuesService().addOrUpdateIssue(this) > 0) {
                        resultMessage = "Task has been successfully Updated!";
                        
                    //    System.out.println("--11111--"+getCurrentAction()+"====================NavTO"+getNavTo());
                        //issueId=896&accessType=Issue
                        
                        setIssueId(getIssueId());
                        setAccessType("Issue");
                        setNavigateTo("getIssue");
                        setResM(resultMessage);
                        resultType = SUCCESS;
                    } else {
                         
                        resultMessage = "Sorry! Please Try again!";
                        resultType = INPUT;
                    }
                   //  httpServletRequest.setAttribute("resultMessage", resultMessage);
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
    
    
    /**
     * DESC: team issue search 
     *  
     */
    public String doSearchTeamIssues()  {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
             //session.setAttribute(ApplicationConstants.SESSION_MY_DEPT_ID,dbDepartmentId);
                         //   public static final String SESSION_MY_DEPT_ID = "myDeptId";
            String deptId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString();               
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_SEARCH_TEAM_ISSUES",userRoleId)){
                try{
                    if("Search".equalsIgnoreCase(getSubmitFrom())) {
                        stringBuilder = new StringBuilder();
                        
                        stringBuilder.append("SELECT Id,Category,IssueTitle,IssueType,DateCreated,STATUS,Severity,Comments,AssignedTo,SecAssignTO FROM tblEmpIssues ");
                        
                        if(null == getStatusId()) setStatusId("");
                        if(null == getCategoryId()) setCategoryId("");
                         if(null == getPreAssignEmpId()) setPreAssignEmpId("");
                         if(null == getPostAssignEmpId()) setPostAssignEmpId("");
                        
                        stringBuilder.append(" where Status <> 'Closed' ");
                        
                        if("".equals(getPreAssignEmpId()) && "".equals(getPostAssignEmpId())){
                        stringBuilder.append(" AND((");      
                        stringBuilder.append(" CreatedBy IN");
                        stringBuilder.append("(");
                        stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue(userId,deptId));
                        stringBuilder.append("))");
                        stringBuilder.append(" OR (AssignedTo IN");
                        stringBuilder.append("(");
                        stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue(userId,deptId));
                        stringBuilder.append("))");
                       // stringBuilder.append(" OR (SecAssignTO ='"+userId+"'");
                        stringBuilder.append(" OR (SecAssignTO IN");
                        stringBuilder.append("(");
                        stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue(userId,deptId));
                        stringBuilder.append(")))");
                        }
                        
                       // " AssignedTo IN ("+ DataSourceDataProvider.getInstance().getEmpNameUsingReportsTo(userId)+")"
                                String myTeam = DataSourceDataProvider.getInstance().getMyTeamMembersForIssue(userId,deptId);
                                
                                
                                 if(!"".equals(getPreAssignEmpId())){
                                         if(myTeam.contains(getPreAssignEmpId())){
                                               
                                                    stringBuilder.append("AND CreatedBy='" + getPreAssignEmpId() + "' ");
                                          }
                                  }
                                
                            if(!"".equals(getPostAssignEmpId())){
                                    if(myTeam.contains(getPostAssignEmpId())){  
                                       
                                         stringBuilder.append("AND (AssignedTo='" + getPostAssignEmpId() + "' OR SecAssignTO='"+getPostAssignEmpId()+"')");
                                    }
                           }
                      /*  if(!"".equals(getPostAssignEmpId())){
                            stringBuilder.append("AND (SecAssignTO LIKE '" + getPostAssignEmpId() + "' OR SecAssignTO LIKE '"+getPostAssignEmpId()+"')");
                        }*/
                        
                        if(!"".equals(getStatusId())){
                            stringBuilder.append("AND Status LIKE '" + getStatusId() + "' ");
                        }
                        if(!"".equals(getIssueName())){
                            stringBuilder.append("AND IssueTitle LIKE '" + getIssueName() + "%' ");
                        }
                       if(!"".equals(getCategoryId())){
                            stringBuilder.append("AND Category LIKE '" + getCategoryId() + "' ");
                        }
                        
                         if(!"".equals(getProjectName())){
                            stringBuilder.append("AND ProjectId LIKE '" + getProjectName() + "' ");
                        }
                       
                        if(getStartDate()!=null){
                            stringBuilder.append("AND date(DateCreated) >= '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ");
                        }
                        
                        if(getEndDate()!=null){
                            stringBuilder.append("AND date(DateCreated) <= '"+DateUtility.getInstance().convertStringToMySQLDate(getEndDate())+"' ");
                        }
                        stringBuilder.append("ORDER BY DateCreated DESC LIMIT 150");
                        
                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ISSUES_LIST)!=null){
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ISSUES_LIST);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ISSUES_LIST, stringBuilder.toString());
                    }
                    prepare();
                    
                         if(getCheck()==null)
                                    {
                                       // System.out.println("in if"+getCheck());
                                        setCheck("1");
                                    }
                                    else if(getCheck().equals(""))
                                        {
                                       // System.out.println("in else"+getCheck());
                                        setCheck("1");
                                        }
                    
                    /*String s=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ISSUE_TO_TASK).toString();
                    setSubnavigateTo(s);*/
                    
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
     * DESC : Issue search notttttttttttttttttttttt
     *
     */
    public String doSearchIssues()  {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_SEARCH_ISSUES",userRoleId)){
                try{
                    if("Search".equalsIgnoreCase(getSubmitFrom())) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("SELECT Id,CreatedBy,DateCreated,Status,AssignedTo,Description FROM tblEmpIssues");
                        if(null == getStatusId()) setStatusId("");
                        if(null == getCategoryId()) setCategoryId("");
                        
                        
                        if(!"".equals(getStatusId()) || !"".equals(getCategoryId()) || getStartDate()!=null || getEndDate()!=null)
                            stringBuilder.append(" WHERE Status <> 'Closed' AND CreatedBy='"+userId+"' or AssignedTo='"+userId+"'");
                        
                       if(!"".equals(getIssueName())){
                            stringBuilder.append("AND IssueTitle LIKE '" + getIssueName() + "%' ");
                        }
                        if(!"".equals(getStatusId())){
                            stringBuilder.append("AND Status LIKE '" + getStatusId() + "' ");
                        }
                        if(getStartDate()!=null){
                            stringBuilder.append("AND date(DateCreated) >= '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ");
                        }
                        if(getEndDate()!=null){
                            stringBuilder.append("AND date(DateCreated) <= '"+DateUtility.getInstance().convertStringToMySQLDate(getEndDate())+"' ");
                        }
                        
                       
                        
                        stringBuilder.append("ORDER BY DateCreated DESC LIMIT 150");
                        
                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ISSUES_LIST)!=null){
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ISSUES_LIST);
                        }
                        
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ISSUES_LIST, stringBuilder.toString());
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
    
    /**
     * Search in 
     *
     */
    public String doSearchEmployeeIssues() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
           if(AuthorizationManager.getInstance().isAuthorizedUser("DO_SEARCH_EMP_ISSUES",userRoleId)){
                try{
                    if("Search".equalsIgnoreCase(getSubmitFrom())) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("SELECT Id,Category,IssueTitle,IssueType,DateCreated,STATUS,Severity,Comments,AssignedTo,SecAssignTO FROM tblEmpIssues");
                        String userId = httpServletRequest.getSession(false).getAttribute("userId").toString();
                         String userName = dataSourceDataProvider.getInstance().getFname_Lname(userId);
                        
                        
                        if(null == getStatusId()) setStatusId("");
                        if(null == getCategoryId()) setCategoryId("");
                        
                        if(!"".equals(getStatusId()) || !"".equals(getCategoryId()) || getStartDate()!=null || getEndDate()!=null)
                            stringBuilder.append(" WHERE Status <> 'Closed' AND (CreatedBy='"+userId+"' or AssignedTo='"+userId+"' OR SecAssignTO='"+userId+"') ");
                         
                        if(!"".equals(getIssueName())){
                            stringBuilder.append("AND IssueTitle LIKE '" + getIssueName() + "%' ");
                        }
                        
                       /* if(!"".equals(getStatusId()) && !andFlag) {
                            stringBuilder.append("Status LIKE '" + getStatusId() + "' ");
                            andFlag = true;
                        } else*/
                        if(!"".equals(getStatusId())){
                            stringBuilder.append("AND Status LIKE '" + getStatusId() + "' ");
                        }
                      /*  if(getStartDate()!=null && !andFlag){
                            stringBuilder.append("date(DateCreated) >= '"+getStartDate()+"' ");
                        } else*/
                         //System.out.println(getStartDate() + "---------------"+getEndDate());
                        if(getStartDate()!=null){
                            stringBuilder.append("AND date(DateCreated) >= '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ");
                        }
                      //  setStartDate(DateUtility.getInstance().convertStringToMySql(getStartDate().toString()));
                      /*  if(getEndDate()!=null && !andFlag){
                            stringBuilder.append("date(DateCreated) <= '"+getEndDate()+"' ");
                        } else */
                        if(getEndDate()!=null){
                            stringBuilder.append("AND date(DateCreated) <= '"+DateUtility.getInstance().convertStringToMySQLDate(getEndDate())+"' ");
                        }
                        
                       
                        
                        stringBuilder.append("ORDER BY DateCreated DESC LIMIT 150");
                        
                        
                        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_ISSUES_LIST)!=null){
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_ISSUES_LIST);
                        }
                        
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_ISSUES_LIST, stringBuilder.toString());
                    }
                    prepare();
                    
                         if(getCheck()==null)
                                    {
                                       // System.out.println("in if"+getCheck());
                                        setCheck("1");
                                    }
                                    else if(getCheck().equals(""))
                                        {
                                       // System.out.println("in else"+getCheck());
                                        setCheck("1");
                                        }
                    
                   // String s=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ISSUE_TO_TASK).toString();
                   // setSubnavigateTo(s);
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
    
    //new method for issue reminder on 06112013 by vkandregula
    public String issueReminderPopup() {
        
        String userId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        String userName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
        try {
            String responseString = "";
            List issueDetails=new ArrayList();
            
            String priEmail= "";
            String secEmail="";
            //CreatedBy,TaskId,IssueTitle,DateAssigned,DateClosed,AssignedTo,SecAssignTO,PercentageComplted
            String createdBy="";
            String taskId="";
            String issueTitle="";
            String dateAssigned="";
            String dateClosed="";
            String assignedTo="";
            String secAssignTO="";
            String percentageComplted="";
            String cutomerName="";
            setIssueReminderId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ISSUE_REMINDER_Id).toString());
            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            issueDetails=dataSourceDataProvider.getIssueDetails(getIssueReminderId());
            createdBy=issueDetails.get(0).toString();
            
            taskId=issueDetails.get(1).toString();
            issueTitle=issueDetails.get(2).toString();
            dateAssigned=issueDetails.get(3).toString();
            dateClosed =issueDetails.get(4).toString();
            assignedTo=issueDetails.get(5).toString();
            //secAssignTO=issueDetails.get(6).toString();
            
            if(!issueDetails.get(7).equals(""))
            {
            percentageComplted=issueDetails.get(7).toString();
            }
            cutomerName =dataSourceDataProvider.getAccountName(Integer.parseInt(taskId));
           
            priEmail=assignedTo;
             if(!issueDetails.get(6).equals(""))
            {
            secAssignTO=issueDetails.get(6).toString();    
            
             }
            secEmail=secAssignTO;
            
            String isMailSent="";
            
            
            if(!priEmail.equals("")){
                 if(Properties.getProperty("Mail.Flag").equals("1")) {
            isMailSent=MailManager.sendReminderForIssue(userId,cutomerName,priEmail,secEmail,createdBy,issueTitle,dateAssigned,dateClosed,percentageComplted,getMessage());
                 }
            }
            if(isMailSent.equalsIgnoreCase("success"))
            {
            //responseString=ma.sendIssueReminderMail(userId,userName,httpServletRequest,getMessage());
            setMailsent(1);
            }
            else
            {
             setMailsent(2);   
            }
            
        } catch (Exception ex) {
            setMailsent(2);
            ex.printStackTrace();
        }
        return SUCCESS;
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
    
    public String getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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
    
    public IssuesVTO getCurrentIssue() {
        return currentIssue;
    }
    
    public void setCurrentIssue(IssuesVTO currentIssue) {
        this.currentIssue = currentIssue;
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
    
    public String getStrId() {
        return strId;
    }
    
    public void setStrId(String strId) {
        this.strId = strId;
        if(!"".equals(strId))
            this.setId(Integer.parseInt(strId));
    }
    
    public String getStrAttachmentId() {
        return strAttachmentId;
    }
    
    public void setStrAttachmentId(String strAttachmentId) {
        this.strAttachmentId = strAttachmentId;
        if(!"".equals(strAttachmentId))
            this.setAttachmentId(Integer.parseInt(strAttachmentId));
    }   
    
    //New Setters and getters
    
        public String getSubnavigateTo() {
        return subnavigateTo;
    }
    
    public void setSubnavigateTo(String subnavigateTo) {
        this.subnavigateTo = subnavigateTo;
    }
    
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

    public Map getProjectNamesMap() {
        return projectNamesMap;
    }

    public void setProjectNamesMap(Map projectNamesMap) {
        this.projectNamesMap = projectNamesMap;
    }

    public String getPostAssignedToUID() {
        return postAssignedToUID;
    }

    public void setPostAssignedToUID(String postAssignedToUID) {
        this.postAssignedToUID = postAssignedToUID;
    }

    public String getPreAssignEmpId() {
        return preAssignEmpId;
    }

    public void setPreAssignEmpId(String preAssignEmpId) {
        this.preAssignEmpId = preAssignEmpId;
    }

    public String getPostAssignEmpId() {
        return postAssignEmpId;
    }

    public void setPostAssignEmpId(String postAssignEmpId) {
        this.postAssignEmpId = postAssignEmpId;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public List getDepartmentIdList() {
        return departmentIdList;
    }

    public void setDepartmentIdList(List departmentIdList) {
        this.departmentIdList = departmentIdList;
    }

    public List getPracticeIdList() {
        return practiceIdList;
    }

    public void setPracticeIdList(List practiceIdList) {
        this.practiceIdList = practiceIdList;
    }

    public String getPerCompleted() {
        return perCompleted;
    }

    public void setPerCompleted(String perCompleted) {
        this.perCompleted = perCompleted;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getIFlag() {
        return iFlag;
    }

    public void setIFlag(int iFlag) {
        this.iFlag = iFlag;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getNavTo() {
        return navTo;
    }

    public void setNavTo(String navTo) {
        this.navTo = navTo;
    }

    public int getMailsent() {
        return mailsent;
    }

    public void setMailsent(int mailsent) {
        this.mailsent = mailsent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIssueReminderId() {
        return issueReminderId;
    }

    public void setIssueReminderId(String issueReminderId) {
        this.issueReminderId = issueReminderId;
    }

    public String getResM() {
        return resM;
    }

    public void setResM(String resM) {
        this.resM = resM;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public String getIssueList() {
        return issueList;
    }

    public void setIssueList(String issueList) {
        this.issueList = issueList;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}