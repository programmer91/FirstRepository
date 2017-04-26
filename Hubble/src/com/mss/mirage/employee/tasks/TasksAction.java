/*
 * TaskAction.java
 *
 * Created on July 2, 2013, 6:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.mss.mirage.employee.tasks;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.opensymphony.xwork2.ActionSupport;
//import org.apache.struts2.interceptor.ServletRequestAware;
import com.mss.mirage.crm.attachments.AttachmentService;
import com.mss.mirage.util.*;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
//import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.mss.mirage.util.DateUtility;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author miracle
 */
public class TasksAction extends ActionSupport implements ServletRequestAware {

    private int id;
    private HttpServletRequest httpServletRequest;
    private String resultType;
    private int userRoleId;
    private HibernateDataProvider hibernateDataProvider;
    private DataSourceDataProvider dataSourceDataProvider;
    private List issueCategoryList;
    private List issueStatusList;
    private List departmentList;
    private List organizationList;
    private List departmentIdList;
    private List practiceIdList;
    private String categoryId;
    private Map projectNamesMap;
    private String dateAssigned;
    private String dateClosed;
    private TasksVTO currentTask;
    private List activityTypeList = new ArrayList();
    private String currentAction;
    private List managersList;
    private String dateWithOutTime;
    //jsp setter and getter
    private String customerName;
    private String customerId;
    private String projectName;
    private String title;
    private String severityId;
    private String statusId;
    private String assignedToUID;
    private String preAssignEmpId;
    private String postAssignedToUID;
    private String postAssignEmpId;
    private String perCompleted;
    private String resolution;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String requestType;
    private String attachmentName;
    private String days;
    private String description;
    private int objectid;
    private String objectType;
    private String createdBy;
    private String operationType;
    private String generatedPath;
    private AttachmentService attachmentService;
    private String fileLocation;
    private String filepath;
    private String submitFrom;
    private String currentSearch;
    private String subnavigateTo;
    private String issueList;
    private String check;
    private StringBuilder stringBuilder;
    private String startDate;
    private String endDate;
    private String issueName;
    private String taskId;
    private String navTo;
    private String resM;
    private String navigateTo;
    private String taskReminderId;
    private String message;
    private int mailsent;
    private String projectId;
    private String addBtnVisible;
    private int repeat;
    private Map repeatMap;
    //new variables for task attachments
    private int taskObjectId;
    private String taskFileName;
    //For customer task
    private String resourceType;
    private Map myAccounts;
    //new
    private String type;
    private String priority;
    private String issueType;
    private String comments;
    private String primaryAssignTo;
    private String secondaryAssignTo;
    private String priorityHubble;
    private String issueTypeHubble;
    private String titleHubble;
    private String primaryAssignToHubble;
    private String secondaryAssignToHubble;
    private String commentsHubble;
    private String priorityProject;
    private String issueTypeProject;
    private String titleProject;
    private String primaryAssignToProject;
    private String secondaryAssignToProject;
    private String commentsPorject;
    private String priorityNetwork;
    private String issueTypeNetwork;
    private String titleNetwork;
    private String primaryAssignToNetwork;
    private String secondaryAssignToNetwork;
    private String commentsNetwork;
    private String priorityInfra;
    private String issueTypeInfra;
    private String titleInfra;
    private String primaryAssignToInfra;
    private String secondaryAssignToInfra;
    private String commentsInfra;
    private Map projectsList;
    private Map primaryAssignToMap;
    private Map secondaryAssignToMap;
    private Map primaryAssignToMapForProject;
    private Map secondaryAssignToMapForProject;
    private Map primaryAssignToMapForHubble;
    private Map secondaryAssignToMapForHubble;
    private Map primaryAssignToMapForNetwork;
    private Map secondaryAssignToMapForNetwork;
    private Map primaryAssignToMapForInfra;
    private Map secondaryAssignToMapForInfra;
    private String perCompletedHR;
    private int iFlag;
    private String issueRelType;
    private String secondaryAssignToLoginId;
    private String secondaryAssignToLoginIdForHubble;
    private String secondaryAssignToLoginIdForProject;
    private String secondaryAssignToLoginIdForNetwork;
    private String secondaryAssignToLoginIdForInfra;
    private String resolutionInfra;
    private String resolutionNetwork;
    private String resolutionProject;
    private String resolutionHubble;
    private Map issuerelatedtoList;
    private Map issueTypeMap;
    private Map issuecusrelatedtoList;
    private String modifiedBy;
     private String notes;
     private int notesId;
     private String primaryAssignToLoginIdforOthers;
     private String primaryAssignToforOthers;
     
     
     
       private String priorityOthers;
    private String issueTypeOthers;
    private String titleOthers;
   
     private String resolutionOthers;
     private String commentsOthers;
      private String secondaryAssignToLoginIdForOthers;
       private String secondaryAssignToOthers;
       private String durationTotheTask;
     private String bridgeCode;
     
     private String  progress;
     
     private Map reportsToHierarchyMap;
    
    private String taskStartDate;
    private String taskEndDate;
    private String reportsTo;
      private String statusPrev;
    /** Creates a new instance of TaskAction */
    //Desc: prepare method for setting any list or maps 
    public String prepare() {
        resultType = LOGIN;
        //     if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_ISSUE", userRoleId)) {
                try {

                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    setIssueCategoryList(hibernateDataProvider.getIssueCategories("categoryKey"));
                    setIssueStatusList(hibernateDataProvider.getIssueStatus("issueStatusKey"));
                    // setDepartmentList(hibernateDataProvider.getDepartment("departmentKey"));
                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                    setPracticeIdList(dataSourceDataProvider.getPracticeByDepartment(getCategoryId()));
                    setOrganizationList(hibernateDataProvider.getLkOrganization("lkOrganizationKey"));
                    // IssuesVTO issuesVTO = new IssuesVTO();
                    setDateAssigned(DateUtility.getInstance().getCurrentMySqlDateTime1());
                    setDateClosed(DateUtility.getInstance().getCurrentMySqlDateTime1());
                    setActivityTypeList(hibernateDataProvider.getCrmActivityTypes(ApplicationConstants.CRM_ACTIVITY_TYPE_OPTIONS));

                    if (getCurrentAction() == "editIssue") {
                        // setManagersList(dataSourceDataProvider.EditAssignedMembers(currentIssue.getCategoryId()));
                        setManagersList(dataSourceDataProvider.EditAssignedMembers(getCategoryId()));
                    }

                    if (getCurrentAction() == "addIssue") {
                        setManagersList(dataSourceDataProvider.AddAssignedMembers("managerskey"));
                    }

                    String dueDate = "";
                    DateUtility date = DateUtility.getInstance();
                    dueDate = date.sqlTimeStampTousTimeStamp(date.getCurrentMySqlDateTime().toString());
                    setDateWithOutTime(dueDate.substring(0, dueDate.length()));

                    Map repeatMap = new TreeMap();
                    repeatMap.put(0, "Not Repeat");
                    repeatMap.put(7, "Every 7 Days");
                    repeatMap.put(30, "Every 30 Days");
                    repeatMap.put(60, "Every 60 Days");
                    setRepeatMap(repeatMap);

                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking

        return resultType;
    }

    //Desc : For getting page for Creatng new task 
                 public String doNewCreateTask() {
        resultType = LOGIN;
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            int isTeamLead = 0;
            int isManager = 0;
            String loginId = "";
            String Org = "";
            int empId = 0;
            setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
            //  System.out.println("resourceType---->"+getResourceType());
            if (getResourceType().equalsIgnoreCase("c")) {
                isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
                loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString();
                empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
            } else if (getResourceType().equalsIgnoreCase("v")) {
                isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
                loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_VENDOR_USER_ID).toString();
                empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
            } else if (getResourceType().equalsIgnoreCase("e")) {
                isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
                isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
                loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            }
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());

            resultType = "accessFailed";

            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_MY_ISSUES", userRoleId)) {
                try {
                    //       System.out.println("userRoleId1--->"+userRoleId);
                    TasksVTO tasksVTO = new TasksVTO();
                    if (getResourceType().equalsIgnoreCase("e")) {
                        tasksVTO.setEmpType("e");
                    } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                        setMyAccounts(dataSourceDataProvider.getInstance().getMyCrmAccountList(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString())));
                                               tasksVTO.setEmpType("c");
                    }
                    setProjectsList(dataSourceDataProvider.getInstance().getProjectNamesListByEmpId(empId));  
                    setIssuerelatedtoList(dataSourceDataProvider.getInstance().getIssuedRelatedTo());
                     setIssuecusrelatedtoList(dataSourceDataProvider.getInstance().getIssuedCustomerRelatedTo());
                     tasksVTO.setProjectId(dataSourceDataProvider.getInstance().getPrimaryProjectNameByEmpId(empId));
                    tasksVTO.setPriority("Low");
                    tasksVTO.setResourceType(getResourceType());
                    HashMap tempEmpMap = new HashMap();
                    tempEmpMap.put("", "");
                    setPrimaryAssignToMap(tempEmpMap);
                    setIssueTypeMap(tempEmpMap);
                    setCurrentTask(tasksVTO);
                    prepare();
                    setCurrentAction("addTask");
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking

        return resultType;
    }
    //Desc : for addign tasks

   public String doNewAddTask() {
        resultType = LOGIN;

        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            //      System.out.println("in doaddtask if--->");
            int isTeamLead = 0;
            int isManager = 0;
            int taskId = 0;
            String result = null;
            String loginId = "";
            String issueTitle = "";
            String issueType = "";
            String priority = "";
            String primaryAssignTo = "";
            String secondaryAssignToLoginId = "";
            String secondaryAssignTo = "";
            String comments = "";
            setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
            int empId = 0;
            String loggedInEmpName = "";
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            //    System.out.println("userRoleId-->"+userRoleId);
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_MY_ISSUES", userRoleId)) {
                //      System.out.println("in doaddtask3");
                try {

                    if (getResourceType().equalsIgnoreCase("c")) {
                        //System.out.println("in doaddtask4");
                        isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
                        loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString();
                        //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                        setCreatedBy(loginId);
                        setObjectid(Integer.parseInt((String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID)));
                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                        loggedInEmpName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString();

                    } else if (getResourceType().equalsIgnoreCase("v")) {
                        isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
                        loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_VENDOR_USER_ID).toString();
                        //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                        setCreatedBy(loginId);
                        setObjectid(Integer.parseInt((String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID)));
                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                        loggedInEmpName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString();
                    } else if (getResourceType().equalsIgnoreCase("e")) {
                        //  System.out.println("in doaddtask5");
                        isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
                        isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
                        loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                        setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                        loggedInEmpName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                        setObjectid(empId);
                    }
                    // System.out.println("in doaddtask6");
                    attachmentService = ServiceLocator.getAttachmentService();
                    TasksVTO tasksVTO = new TasksVTO();
                    //  setCurrentTask(tasksVTO);
                    generatedPath = null;
                    setOperationType("Ins");

                    if (getUploadFileName() != null) {
                        //         System.out.println("in doaddtask9");
                        generatedPath = attachmentService.generatePath(Properties.getProperty("Attachments.Path"), "Emp Tasks");
                        File targetDirectory = new File(generatedPath + "/" + getUploadFileName());
                        setFileLocation(targetDirectory.toString());
                        FileUtils.copyFile(getUpload(), targetDirectory);
                        if (getResourceType().equalsIgnoreCase("e")) {
                            setObjectType("Emp Task");
                        }
                        if (getResourceType().equalsIgnoreCase("c")) {
                            setObjectType("Customer Task");
                        }
                        if (getResourceType().equalsIgnoreCase("v")) {
                            setObjectType("Vendor Task");
                        }

                    } else {
                        //       System.out.println("in doaddtask10");
                        this.objectType = "NoFile";
                        this.setFileLocation("");
                        this.setFilepath("");
                        this.attachmentName = "";
                    }
                   // if (getType().equalsIgnoreCase("1")) {
                        issueTitle = getTitle();
                        issueType = getIssueType();
                        priority = getPriority();
                        
                        if(Integer.parseInt(getType())!=5)
                        primaryAssignTo = getPrimaryAssignTo();
                        else
                            primaryAssignTo = getPrimaryAssignToLoginIdforOthers();
                        secondaryAssignTo = getSecondaryAssignTo();
                        comments = getComments();
                        secondaryAssignToLoginId = getSecondaryAssignToLoginId();
                        tasksVTO.setTitle(issueTitle);
                        tasksVTO.setIssueType(issueType);
                        tasksVTO.setPriority(priority);
                        tasksVTO.setPrimaryAssignTo(primaryAssignTo);
                        tasksVTO.setSecondaryAssignTo(secondaryAssignTo);
                        tasksVTO.setComments(comments);
                        tasksVTO.setType(getType());
                        tasksVTO.setSecondaryAssignToLoginId(secondaryAssignToLoginId);
                 if(issueType.equals("4") && getType().equals("3")){
                            dataSourceDataProvider.getInstance().doUpdateBMSBridgeDetails(getBridgeCode(),loginId);
                        }
                    //   System.out.println("secondaryAssignToLoginId-->"+secondaryAssignToLoginId);
                    taskId = ServiceLocator.getTasksService().addOrUpdateTask(this, httpServletRequest, issueTitle, issueType, priority, primaryAssignTo, secondaryAssignTo, comments, secondaryAssignToLoginId);
                    setProjectsList(dataSourceDataProvider.getInstance().getProjectNamesListByEmpId(empId));
                    setiFlag(0);
                    tasksVTO.setiFlag(getiFlag());
                    setCurrentTask(tasksVTO);
                    setCurrentAction("addTask");
                     setProjectsList(dataSourceDataProvider.getInstance().getProjectNamesListByEmpId(empId));
                    setIssuerelatedtoList(dataSourceDataProvider.getInstance().getIssuedRelatedTo());
                    tasksVTO.setPriority("Low");
                    tasksVTO.setResourceType(getResourceType());
                    HashMap tempEmpMap = new HashMap();
                    tempEmpMap.put("", "");
                    setPrimaryAssignToMap(tempEmpMap);
                    setIssueTypeMap(tempEmpMap);
                   // setCurrentTask(tasksVTO);
                    prepare();
                 //   doNewCreateTask();
                    if (taskId > 0) {
                        if (Properties.getProperty("Mail.Flag").equals("1")) {
                            String toAddress = "";
                            String cCAddress = "";
                            String subject = "";
                            String bodyContent = "";
                            String createdDate = "";
                            String mailDeliverDate = "";
                            String mailFlag = "0";
                            String bCCAddress = "";
                            createdDate = DateUtility.getInstance().getCurrentMySqlDateTime1();
                            mailDeliverDate = DateUtility.getInstance().getCurrentMySqlDateTime1();
                            StringBuilder htmlText = new StringBuilder();
                            if(issueType.equals("4") && getType().equals("3")){
                            cCAddress="itteam@miraclesoft.com";
                        }
                            bCCAddress = Properties.getProperty("bccAddress");
                           htmlText.append("<!DOCTYPE html><html><head>");
                            htmlText.append("<meta charset='utf-8'><meta name='viewport' content='width=device-width, initial-scale=1'><meta http-equiv='X-UA-Compatible' content='IE=edge' /><style type='text/css'>");
                            htmlText.append("body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;}");
                            htmlText.append("table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;}");
                            htmlText.append("img{-ms-interpolation-mode: bicubic;}");
                            htmlText.append("img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}");
                            htmlText.append("table{border-collapse: collapse !important;}");
                            htmlText.append("body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}");
                            htmlText.append("a[x-apple-data-detectors] {");
                            htmlText.append("color: inherit !important;");
                            htmlText.append("text-decoration: none !important;");
                            htmlText.append("font-size: inherit !important;");
                            htmlText.append("font-family: inherit !important;");
                            htmlText.append("font-weight: inherit !important;");
                            htmlText.append("line-height: inherit !important;");
                            htmlText.append("}");
                            htmlText.append("@media screen and (max-width: 525px) {");
                            htmlText.append(".wrapper {");
                            htmlText.append("width: 100% !important;");
                            htmlText.append("max-width: 100% !important;");
                            htmlText.append("}");
                            htmlText.append(".logo img {");
                            htmlText.append("margin: 0 auto !important;");
                            htmlText.append("}");
                            htmlText.append(".mobile-hide {");
                            htmlText.append("display: none !important;");
                            htmlText.append("}");
                            htmlText.append(".img-max {");
                            htmlText.append("max-width: 100% !important;");
                            htmlText.append("width: 100% !important;");
                            htmlText.append("height: auto !important;");
                            htmlText.append("}");
                            htmlText.append(".responsive-table {");
                            htmlText.append(" width: 100% !important;}");
                            htmlText.append(".padding {padding: 10px 5% 15px 5% !important;}");
                            htmlText.append(".padding-meta {padding: 30px 5% 0px 5% !important;text-align: center;}");
                            htmlText.append(".padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}");
                            htmlText.append(".no-padding {padding: 0 !important;}");
                            htmlText.append(".section-padding {padding: 50px 15px 50px 15px !important;}");
                            htmlText.append(".mobile-button-container {margin: 0 auto;width: 100% !important;}");
                            htmlText.append(".mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important;display: block !important;}}");
                            htmlText.append("div[style*='margin: 16px 0;'] { margin: 0 !important; }");
                            htmlText.append("</style></head>");
                            htmlText.append("<body style='margin: 0 !important; padding: 0 !important;'>");
                            htmlText.append("<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>Task has been created by <b>" + loggedInEmpName + "</b></div>");
                            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td bgcolor='#ffffff' align='center'>");
                            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper;>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='center' valign='top' style='padding: 15px 0;' class='logo'>");
                            htmlText.append("<a href='http://www.miraclesoft.com/' target='_blank'><img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'></a>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
                            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td>");
                            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>Task Management System</b></td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");
                            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td>");
                            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>Team,</b><br>A task has been created by <b>" + loggedInEmpName + "</b></td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
                            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Issue Id:</b> " + taskId + "<br>");
                            if ((issueTitle != null) && !"".equals(issueTitle)) {
                                htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Issue Title:</b> " + issueTitle + "</b><br>");
                            }
                            if ((priority != null) && !"".equals(priority)) {
                                htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Issue Severity:</b> " + priority + "<br>");
                            }
                            if ((getCreatedBy() != null) && !"".equals(getCreatedBy())) {
                                htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Created By:</b> " + getCreatedBy() + "<br>");
                            }
                            if (getResourceType().equalsIgnoreCase("e")) {
                                if ((getCreatedBy() != null) && !"".equals(getCreatedBy())) {
                                    htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Created By Email:</b> " + getCreatedBy() + "@miraclesoft.com<br>");
                                }
                            } else {
                                if ((getCreatedBy() != null) && !"".equals(getCreatedBy())) {
                                    htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Created By Email:</b> " + DataSourceDataProvider.getInstance().getContactOfficeMail(empId) + "<br>");
                                }
                            }
                            if ((comments != null) && !"".equals(comments)) {
                                htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Comments:</b> " + comments + "<br>");
                            }
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td>");
                            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>");
                            htmlText.append("Thanks & Regards,<br>Corporate Application Support Team, <br>Miracle Software Systems, Inc. <br>Email: hubble@miraclesoft.com <br>Phone: (+1)248-233-1814");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td>");
                            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");
                            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' class='responsive-table'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td width='200' align='center' style='text-align: center;'><table width='200' cellpadding='0' cellspacing='0' align='center'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                            htmlText.append("<td width='10'><a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                            htmlText.append("<td width='10'><a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                            htmlText.append("<td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                            htmlText.append("<td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                            htmlText.append("<td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td height='10'>");
                            htmlText.append("</td>");
                            htmlText.append(" </tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems");

                            htmlText.append("<br>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");

                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");

                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</body>");
                            htmlText.append("</html>");
                            bodyContent = htmlText.toString();
                            toAddress = dataSourceDataProvider.getInstance().getPrimaryAssignToMailByLoginId(taskId, primaryAssignTo);
                           // System.out.println("before if sec-->"+secondaryAssignTo);
                            if (!"".equals(secondaryAssignTo) && !"-1".equals(secondaryAssignTo)) {
                                //System.out.println("after if-->"+secondaryAssignTo);
                                if (getType().equalsIgnoreCase("0")) {
                                    toAddress = toAddress + "," + dataSourceDataProvider.getInstance().getSecondaryAssignToMailByLoginId(taskId, secondaryAssignToLoginId);
                                } else if (getType().equalsIgnoreCase("1")) {
                                    toAddress = toAddress + "," + dataSourceDataProvider.getInstance().getSecondaryAssignToMailByLoginId(taskId, secondaryAssignToLoginId);
                                } else if (getType().equalsIgnoreCase("2")) {
                                    toAddress = toAddress + "," + dataSourceDataProvider.getInstance().getSecondaryAssignToMailByLoginId(taskId, secondaryAssignToLoginId);
                                } else if (getType().equalsIgnoreCase("3")) {
                                    toAddress = toAddress + "," + dataSourceDataProvider.getInstance().getSecondaryAssignToMailByLoginId(taskId, secondaryAssignToLoginId);
                                } else if (getType().equalsIgnoreCase("4")) {
                                    toAddress = toAddress + "," + dataSourceDataProvider.getInstance().getSecondaryAssignToMailByLoginId(taskId, secondaryAssignToLoginId);
                                }else if (getType().equalsIgnoreCase("5")) {
                                    toAddress = toAddress + "," + dataSourceDataProvider.getInstance().getSecondaryAssignToMailByLoginId(taskId, secondaryAssignToLoginId);
                                }
                            }  
                            //ServiceLocator.getMailServices().doAddEmailLog(toAddress, cCAddress, issueTitle, bodyContent, createdDate, bCCAddress);
                            if(Properties.getProperty("Mail.Flag").equals("1")) {
                                ServiceLocator.getMailServices().doAddEmailLogNew(toAddress, cCAddress, issueTitle, bodyContent, createdDate, bCCAddress,"Tasks");
                            }
                            
                            resultType = SUCCESS;
                            if (getType().equalsIgnoreCase("0")) {
                                result = "Task has been successfully inserted and mail is sent for issue related to HR!";
                            } else if (getType().equalsIgnoreCase("1")) {
                                result = "Task has been successfully inserted and mail is sent for issue related to Hubble!";
                            } else if (getType().equalsIgnoreCase("2")) {
                                result = "Task has been successfully inserted and mail is sent for issue related to Projects!";
                            } else if (getType().equalsIgnoreCase("3")) {
                                result = "Task has been successfully inserted and mail is sent for issue related to Systems!";
                            } else if (getType().equalsIgnoreCase("4")) {
                                result = "Task has been successfully inserted and mail is sent for issue related to Facilities Provided!";
                            }else {
                                result = "Task has been successfully inserted!";
                            }
                          
                        
                    }  else {
                            resultType = SUCCESS;
                            result = "Task has been successfully inserted!";
                        }
                   

                }else {
                        result = "<font size='2' color='red'>Sorry! Please Try again!</font>";
                        resultType = INPUT;

                    }
                     httpServletRequest.setAttribute("resultMessage", result);
                    setResM(result);
                }catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    result = ex.toString();
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking

        return resultType;
    }
	

    //Description : for displaying my tasks
    public String myTasks() {
        resultType = LOGIN;
        String primaryAssignTo = null;
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            String userId = "";
            String loginId = null;
            setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());

            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String userName = null;
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_MY_ISSUES", userRoleId)) {
                try {
                    if (getResourceType().equalsIgnoreCase("e")) {
                        userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    } else if (getResourceType().equalsIgnoreCase("c")) {
                        userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                        loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString();
                        setMyAccounts(dataSourceDataProvider.getInstance().getMyCrmAccountList(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString())));
                    } else if (getResourceType().equalsIgnoreCase("v")) {
                        userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                        loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_VENDOR_USER_ID).toString();
                        setMyAccounts(dataSourceDataProvider.getInstance().getMyCrmAccountList(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString())));
                    }
                      setStartDate(DateUtility.getInstance().getLastSixtyDaysDateFromCurrentDate());
  setEndDate(DateUtility.getInstance().getCurrentMySqlDate());
                
                    StringBuilder stringBuilder = new StringBuilder();
                    /* Search Query Based on SearchForm */
                    if (getSubmitFrom() == null) {
                       // stringBuilder.append("SELECT Id,CreatedDate,STATUS,Severity,Description,PriAssignTO,SecAssignTo,Title,CreatedBy,IssueRel,Project_Id,DueDate FROM tblEmpTasks");
                        // stringBuilder.append("SELECT tblEmpTasks.Id,tblEmpTasks.CreatedDate,tblEmpTasks.STATUS,tblEmpTasks.Severity,tblEmpTasks.Description,tblEmpTasks.SecAssignTo,tblEmpTasks.Title,tblEmpTasks.CreatedBy,tblEmpTasks.IssueRel,tblEmpTasks.Project_Id,tblEmpTasks.DueDate,tblEmpTasks.percentageCompleted,CONCAT(TRIM(FName),'.',TRIM(LName)) AS PriAssignTO FROM tblEmpTasks LEFT JOIN tblEmployee ON(tblEmpTasks.PriAssignTO=tblEmployee.LoginId) ");
                        stringBuilder.append("SELECT tblEmpTasks.Id,tblEmpTasks.CreatedDate,tblEmpTasks.STATUS,tblEmpTasks.Severity,tblEmpTasks.Description,(CASE WHEN tblEmpTasks.PriAssignTO<>'' THEN  CONCAT(TRIM(t1.FName),'.',TRIM(t1.LName)) ELSE '' END)  AS PriAssignTO,(CASE WHEN tblEmpTasks.CreatedBy<>'' THEN  CONCAT(TRIM(t2.FName),'.',TRIM(t2.LName)) ELSE '' END) AS CreatedBy,tblEmpTasks.Title,tblEmpTasks.IssueRel,tblEmpTasks.Project_Id,tblEmpTasks.percentageCompleted,tblEmpTasks.DueDate FROM tblEmpTasks  LEFT JOIN tblEmployee t1 ON(tblEmpTasks.PriAssignTO=t1.LoginId) LEFT JOIN tblEmployee t2 ON(tblEmpTasks.CreatedBy=t2.LoginId)  "); if (getResourceType().equalsIgnoreCase("e")) {
                            stringBuilder.append(" where tblEmpTasks.Status <> 'Closed'  and (tblEmpTasks.CreatedBy='" + userId + "' or tblEmpTasks.PriAssignTO='" + userId + "' or tblEmpTasks.SecAssignTo = '" + userId + "') ");
                        } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                            //stringBuilder.append(" where Status <> 'Closed'  and (CreatedBy='"+userId+"' or PriAssignTO='"+userId+"' or SecAssignTo = '"+userId+"') ORDER BY CreatedDate DESC LIMIT 150");					   
                            stringBuilder.append(" where tblEmpTasks.Status <> 'Closed'  and (tblEmpTasks.CreatedBy='" + loginId + "' or tblEmpTasks.PriAssignTO='" + loginId + "' or tblEmpTasks.SecAssignTo = '" + loginId + "') ");
                        }
                    }
                    

                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_TASKS_LIST) != null) {
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_TASKS_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_TASKS_LIST, stringBuilder.toString());

                    setCurrentSearch("doSearchEmpTasks");

                    setSubnavigateTo("getTasks");

                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.TASKNAVTO, "doSearchEmpTasks");
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    //Desc: for searching employee my tasks

    public String doSearchEmployeeTasks() {
        resultType = LOGIN;
        // System.out.println("inside search m,ethod");
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
            String loginId = null;
            resultType = "accessFailed";
            String userId = "";
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_SEARCH_EMP_ISSUES", userRoleId)) {
                try {
                    //           System.out.println("inside search m,ethod1--->"+getSubmitFrom());
                    if ("Search".equalsIgnoreCase(getSubmitFrom())) {
                        stringBuilder = new StringBuilder();
                     //   stringBuilder.append("SELECT Id,CreatedDate,STATUS,Severity,Description,PriAssignTO,SecAssignTo,Title,CreatedBy,IssueRel,Project_Id FROM tblEmpTasks");
                       // stringBuilder.append("SELECT Id,CreatedDate,STATUS,Severity,Description,PriAssignTO,SecAssignTo,Title,CreatedBy,IssueRel,Project_Id,percentageCompleted,DueDate FROM tblEmpTasks");
                       stringBuilder.append("SELECT tblEmpTasks.Id,tblEmpTasks.CreatedDate,tblEmpTasks.STATUS,tblEmpTasks.Severity,tblEmpTasks.Description,(CASE WHEN tblEmpTasks.PriAssignTO<>'' THEN  CONCAT(TRIM(t1.FName),'.',TRIM(t1.LName)) ELSE '' END)  AS PriAssignTO,(CASE WHEN tblEmpTasks.CreatedBy<>'' THEN  CONCAT(TRIM(t2.FName),'.',TRIM(t2.LName)) ELSE '' END) AS CreatedBy,tblEmpTasks.Title,tblEmpTasks.IssueRel,tblEmpTasks.Project_Id,tblEmpTasks.percentageCompleted,tblEmpTasks.DueDate FROM tblEmpTasks  LEFT JOIN tblEmployee t1 ON(tblEmpTasks.PriAssignTO=t1.LoginId) LEFT JOIN tblEmployee t2 ON(tblEmpTasks.CreatedBy=t2.LoginId)  ");  if (null == getStatusId()) {
                            setStatusId("");
                        }
                        if (null == getCustomerId()) {
                            setCustomerId("");
                        }
                        if (null == getProjectId()) {
                            setProjectId("");
                        }if(null==getIssueType()){
                           setIssueType(""); 
                        }

                        //  System.out.println("before if e-->");
                        if (getResourceType().equalsIgnoreCase("e")) {
                            userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                            stringBuilder.append(" WHERE 1=1 AND (tblEmpTasks.CreatedBy='" + userId + "' or tblEmpTasks.PriAssignTO='" + userId + "' OR tblEmpTasks.SecAssignTo='" + userId + "') ");
                        } else if (getResourceType().equalsIgnoreCase("c")) {
                            //    System.out.println("in ifelse c-->");
                            loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString();
                            userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                            setMyAccounts(dataSourceDataProvider.getInstance().getMyCrmAccountList(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString())));
                            //    System.out.println("in ifelse c-->"+getMyAccounts());
                            stringBuilder.append(" WHERE 1=1 AND (tblEmpTasks.CreatedBy='" + loginId + "' or tblEmpTasks.PriAssignTO='" + loginId + "' OR tblEmpTasks.SecAssignTo='" + loginId + "') ");
                        } else if (getResourceType().equalsIgnoreCase("v")) {
                            loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_VENDOR_USER_ID).toString();
                            userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                            setMyAccounts(dataSourceDataProvider.getInstance().getMyCrmAccountList(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString())));
                            //   System.out.println("in ifelse c-->"+getMyAccounts());
                            stringBuilder.append(" WHERE 1=1 AND (tblEmpTasks.CreatedBy='" + loginId + "' or tblEmpTasks.PriAssignTO='" + loginId + "' OR tblEmpTasks.SecAssignTo='" + loginId + "') ");
                        }
                        if (!"".equals(getIssueName())) {
                            stringBuilder.append(" AND tblEmpTasks.Title LIKE '%" + getIssueName() + "%' ");
                        }

                        if (!"".equals(getCustomerId()) && !"-1".equals(getCustomerId())) {
                            stringBuilder.append(" AND tblEmpTasks.CustomerId = " + getCustomerId() + " ");
                        }

                        if (!"".equals(getProjectId()) && !"-1".equals(getProjectId())) {
                            stringBuilder.append(" AND tblEmpTasks.Project_Id = " + getProjectId() + " ");
                        }


                       

                        if (!"".equals(getIssueRelType()) && !"-1".equals(getIssueRelType())) {
                            stringBuilder.append(" AND tblEmpTasks.IssueRel = " + getIssueRelType() + " ");
                        }
                        if (!"".equals(getStatusId())) {
                            stringBuilder.append(" AND tblEmpTasks.Status LIKE '" + getStatusId() + "' ");
                        }

                        if (getStartDate() != null) {
                            stringBuilder.append(" AND date(tblEmpTasks.CreatedDate) >= '" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "' ");
                        }

                        if (getEndDate() != null) {
                            stringBuilder.append(" AND date(tblEmpTasks.CreatedDate) <= '" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "' ");
                        }
                        if(!"".equals(getIssueType()) && getIssueType()!=null){
                           stringBuilder.append(" AND tblEmpTasks.IssueType = " + Integer.parseInt(getIssueType()) + " ");
                      
                        }	
                        
	if(!"".equals(getProgress())){
                           stringBuilder.append("AND tblEmpTasks.percentageCompleted = " + Integer.parseInt(getProgress()) + " ");
                      
                        }								
                       // stringBuilder.append("ORDER BY tblEmpTasks.CreatedDate DESC LIMIT 150");


                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_TASKS_LIST) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_TASKS_LIST);
                        }

                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_TASKS_LIST, stringBuilder.toString());
                    }

                    if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                        setMyAccounts(dataSourceDataProvider.getInstance().getMyCrmAccountList(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString())));
                    }
                    if (getCheck() == null) {

                        setCheck("1");
                    } else if (getCheck().equals("")) {

                        setCheck("1");
                    }

                    setEndDate(getEndDate());
                    setStartDate(getStartDate());
setProgress(getProgress());
                    setIssueType(getIssueType());
                    setIssueName(getIssueName());
                   setStatusId(getStatusId()); 
                   setIssueRelType(getIssueRelType());
                   setCustomerId(getCustomerId());
                   setProjectId(getProjectId());
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
    //Description: for getting task details

    public String getTask() {
        resultType = LOGIN;
        //  System.out.println("in gettask->");
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            int empId = 0;

            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_ISSUE", userRoleId)) {

                try {

                    if (httpServletRequest.getAttribute("submitFrom") != null) {
                        httpServletRequest.removeAttribute("submitFrom");
                    }

                    if (getResourceType().equalsIgnoreCase("e")) {
                        setObjectid(Integer.parseInt((String) httpServletRequest.getSession(false).getAttribute("empId")));
                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                    } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                        setObjectid(Integer.parseInt((String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID)));
                        setMyAccounts(dataSourceDataProvider.getInstance().getMyCrmAccountList(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString())));
                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                    }
                    //     System.out.println("object Id--->"+getObjectid());
                    HashMap tempEmpMap = new HashMap();
                    tempEmpMap.put("", "");
                    //   System.out.println(getType());
                    if (getType().equalsIgnoreCase("0")) {
                        setPrimaryAssignToMap(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnIssueRel(getType()));
                        //setSecondaryAssignToMap(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnIssueRel(getType()));
                        setPrimaryAssignToMapForProject(tempEmpMap);
                        setPrimaryAssignToMapForHubble(tempEmpMap);
                        setPrimaryAssignToMapForInfra(tempEmpMap);
                        setPrimaryAssignToMapForNetwork(tempEmpMap);
                        // setSecondaryAssignToMapForProject(tempEmpMap);
                        // setSecondaryAssignToMapForHubble(tempEmpMap);
                        // setSecondaryAssignToMapForInfra(tempEmpMap);
                        // setSecondaryAssignToMapForNetwork(tempEmpMap);
                    }
                    if (getType().equalsIgnoreCase("1")) {
                        setPrimaryAssignToMap(tempEmpMap);
                        //setSecondaryAssignToMap(tempEmpMap);
                        setPrimaryAssignToMapForProject(tempEmpMap);
                        setPrimaryAssignToMapForHubble(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnIssueRel(getType()));
                        setPrimaryAssignToMapForInfra(tempEmpMap);
                        setPrimaryAssignToMapForNetwork(tempEmpMap);
                        // setSecondaryAssignToMapForProject(tempEmpMap);
                        // setSecondaryAssignToMapForHubble(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue(getType()));
                        // setSecondaryAssignToMapForInfra(tempEmpMap);
                        //  setSecondaryAssignToMapForNetwork(tempEmpMap);
                    }
                    if (getType().equalsIgnoreCase("3")) {
                        setPrimaryAssignToMap(tempEmpMap);
                        //  setSecondaryAssignToMap(tempEmpMap);
                        setPrimaryAssignToMapForProject(tempEmpMap);
                        setPrimaryAssignToMapForHubble(tempEmpMap);
                        setPrimaryAssignToMapForInfra(tempEmpMap);
                        setPrimaryAssignToMapForNetwork(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnIssueRel(getType()));
                        // setSecondaryAssignToMapForProject(tempEmpMap);
                        // setSecondaryAssignToMapForHubble(tempEmpMap);
                        // setSecondaryAssignToMapForInfra(tempEmpMap);
                        //  setSecondaryAssignToMapForNetwork(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue(getType()));
                    }
                    if (getType().equalsIgnoreCase("4")) {
                        setPrimaryAssignToMap(tempEmpMap);
                        //setSecondaryAssignToMap(tempEmpMap);
                        setPrimaryAssignToMapForProject(tempEmpMap);
                        setPrimaryAssignToMapForHubble(tempEmpMap);
                        setPrimaryAssignToMapForInfra(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnIssueRel(getType()));
                        setPrimaryAssignToMapForNetwork(tempEmpMap);
                        // setSecondaryAssignToMapForProject(tempEmpMap);
                        // setSecondaryAssignToMapForHubble(tempEmpMap);
                        // setSecondaryAssignToMapForInfra(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue(getType()));
                        // setSecondaryAssignToMapForNetwork(tempEmpMap);
                    }
                    if (getType().equalsIgnoreCase("2")) {
                        if (!"0".equals(getProjectId())) {
                            setPrimaryAssignToMap(tempEmpMap);
                            // setSecondaryAssignToMap(tempEmpMap);

                            setPrimaryAssignToMapForProject(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnPrjIssue(getProjectId()));
                            setPrimaryAssignToMapForHubble(tempEmpMap);
                            setPrimaryAssignToMapForInfra(tempEmpMap);
                            setPrimaryAssignToMapForNetwork(tempEmpMap);
                            // setSecondaryAssignToMapForProject(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnPrjIssue(getProjectId()));
                            // setSecondaryAssignToMapForHubble(tempEmpMap);
                            // setSecondaryAssignToMapForInfra(tempEmpMap);
                            //  setSecondaryAssignToMapForNetwork(tempEmpMap);
                        }
                    }
                    
                    
                     if (getType().equalsIgnoreCase("5")) {
                        setPrimaryAssignToMap(tempEmpMap);
                        //setSecondaryAssignToMap(tempEmpMap);
                        setPrimaryAssignToMapForProject(tempEmpMap);
                        setPrimaryAssignToMapForHubble(tempEmpMap);
                        setPrimaryAssignToMapForInfra(tempEmpMap);
                        setPrimaryAssignToMapForNetwork(tempEmpMap);
                        // setSecondaryAssignToMapForProject(tempEmpMap);
                        // setSecondaryAssignToMapForHubble(tempEmpMap);
                        // setSecondaryAssignToMapForInfra(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue(getType()));
                        // setSecondaryAssignToMapForNetwork(tempEmpMap);
                    }
                    
                    
                    setProjectsList(dataSourceDataProvider.getInstance().getProjectNamesListByEmpId(empId));
                    setCurrentTask(ServiceLocator.getTasksService().getTask(getTaskId(), getObjectid(), getResourceType(), getType()));
                    setTaskObjectId(getCurrentTask().getId());
                    setFileLocation(getCurrentTask().getFileLocation());
                    setType(getType());
                    setIssueTypeMap(dataSourceDataProvider.getInstance().getIssueTypeMapByIssueID(getType()));
                    if (httpServletRequest.getSession(false).getAttribute("resultMessage") != null) {
                        httpServletRequest.getSession(false).removeAttribute("resultMessage");
                    }
                    setCustomerId(getCurrentTask().getCustomerId());
                    setId(getCurrentTask().getId());
                    setCurrentSearch(getCurrentSearch());
                    setCurrentAction("newEditTask");
                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TASKNAVTO) != null) {
                        setNavTo(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TASKNAVTO).toString());
                    } else {
                        setNavTo("");
                    }
                    prepare();

                   /* setDateAssigned(getCurrentTask().getAssigned().toString());
                    setDateClosed(getCurrentTask().getClosed().toString()); */
                    
                    
 if(getCurrentTask().getAssigned().toString()!=null && !"".equals(getCurrentTask().getAssigned().toString())){
                      setDateAssigned(getCurrentTask().getAssigned().toString());
                      
                    }
                     if(getCurrentTask().getClosed().toString()!=null && !"".equals(getCurrentTask().getClosed().toString())){
                   
                    setDateClosed(getCurrentTask().getClosed().toString());
                     }	


                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking

        if (httpServletRequest.getParameter("taskId") != null) {
            String taskId = httpServletRequest.getParameter("taskId");
            httpServletRequest.setAttribute("taskId", taskId);
            setTaskId(httpServletRequest.getAttribute("taskId").toString());
            String type = httpServletRequest.getParameter("type");
            httpServletRequest.setAttribute("type", type);
            setType(httpServletRequest.getAttribute("type").toString());
            String projectId = httpServletRequest.getParameter("projectId");
            httpServletRequest.setAttribute("projectId", projectId);
            setProjectId(httpServletRequest.getAttribute("projectId").toString());

        }
        return resultType;
    }

    //Description: For editing tasks
    public String doNewEditTask() {
        //   System.out.println("In Do Edit Issue!!");
        resultType = LOGIN;
        //   System.out.println("in doedittask()-->");
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            //  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            int empId = 0;
            resultType = "accessFailed";
            String loginId = null;
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_ISSUE", userRoleId)) {
                try {
                    attachmentService = ServiceLocator.getAttachmentService();
                    setOperationType("Upd");
                    generatedPath = null;
                    String loggedInEmpName = "";
                    if (getResourceType().equalsIgnoreCase("e")) {
                        setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
                        setObjectid(Integer.parseInt((String) httpServletRequest.getSession(false).getAttribute("empId")));
                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                        loggedInEmpName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                    } else if (getResourceType().equalsIgnoreCase("c")) {

                        loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString();
                        //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                        setCreatedBy(loginId);
                        setObjectid(Integer.parseInt((String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID)));
                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                        loggedInEmpName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString();
                    } else if (getResourceType().equalsIgnoreCase("v")) {
                        loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_VENDOR_USER_ID).toString();
                        //setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                        setCreatedBy(loginId);
                        setObjectid(Integer.parseInt((String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID)));
                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                        loggedInEmpName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString();
                    }
                    String resultMessage = null;
                    //setTaskId(getTaskId());
                    // System.out.println("filename-->"+getUploadFileName());
                    if (getUploadFileName() != null) {
                        //         System.out.println("in doaddtask9");
                        generatedPath = attachmentService.generatePath(Properties.getProperty("Attachments.Path"), "Emp Tasks");
                        File targetDirectory = new File(generatedPath + "/" + getUploadFileName());
                        setFileLocation(targetDirectory.toString());
                        FileUtils.copyFile(getUpload(), targetDirectory);
                        if (getResourceType().equalsIgnoreCase("e")) {
                            setObjectType("Emp Task");
                        }
                        if (getResourceType().equalsIgnoreCase("c")) {
                            setObjectType("Customer Task");
                        }
                        if (getResourceType().equalsIgnoreCase("v")) {
                            setObjectType("Vendor Task");
                        }

                    } else {
                        //       System.out.println("in doaddtask10");
                        this.objectType = "NoFile";
                        this.setFileLocation("");
                        this.setFilepath("");
                        this.attachmentName = "";
                    }
                    //  System.out.println("gettype-->"+getType());

                    setTaskId(String.valueOf(getId()));
                    TasksVTO tasksVTO = new TasksVTO();
                    String issueTitle = "";
                    String issueType = "";
                    String priority = "";
                    String primaryAssignTo = "";
                    String secondaryAssignTo = "";
                    String secondaryAssignToLoginId = "";
                    String secondaryAssignToLoginIdForHubble = "";
                    String secondaryAssignToLoginIdForProject = "";
                    String secondaryAssignToLoginIdForNetwork = "";
                    String secondaryAssignToLoginIdForInfra = "";
                    String secondaryAssignToLoginIdForOthers = "";
                    String comments = "";
                    String resolution = "";
                    if (getType().equalsIgnoreCase("0")) {
                        //        System.out.println("issueTitle-->"+getTitle());
                        issueTitle = getTitle();
                        issueType = getIssueType();
                        priority = getPriority();
                        primaryAssignTo = getPrimaryAssignTo();
                        secondaryAssignTo = getSecondaryAssignTo();
                        secondaryAssignToLoginId = getSecondaryAssignToLoginId();
                        comments = getComments();
                        resolution = getResolution();
                        tasksVTO.setResolution(resolution);
                        tasksVTO.setTitle(issueTitle);
                        tasksVTO.setIssueType(issueType);
                        tasksVTO.setPriority(priority);
                        tasksVTO.setPriorityHubble("Low");
                        tasksVTO.setPriorityProject("Low");
                        tasksVTO.setPriorityInfra("Low");
                        tasksVTO.setPriorityNetwork("Low");
                        tasksVTO.setPrimaryAssignTo(primaryAssignTo);
                        tasksVTO.setSecondaryAssignTo(secondaryAssignTo);
                        tasksVTO.setComments(comments);
                        tasksVTO.setType(getType());
                        tasksVTO.setSecondaryAssignToLoginId(secondaryAssignToLoginId);
                    }
                    if (getType().equalsIgnoreCase("1")) {
                        issueTitle = getTitleHubble();
                        issueType = getIssueTypeHubble();
                        priority = getPriorityHubble();
                        primaryAssignTo = getPrimaryAssignToHubble();
                        secondaryAssignTo = getSecondaryAssignToHubble();
                        secondaryAssignToLoginIdForHubble = getSecondaryAssignToLoginIdForHubble();
                        comments = getCommentsHubble();
                        resolution = getResolutionHubble();
                        tasksVTO.setResolutionHubble(resolution);
                        tasksVTO.setTitleHubble(issueTitle);
                        tasksVTO.setIssueTypeHubble(issueType);
                        tasksVTO.setPriorityHubble(priority);
                        tasksVTO.setPriority("Low");
                        tasksVTO.setPriorityProject("Low");
                        tasksVTO.setPriorityInfra("Low");
                        tasksVTO.setPriorityNetwork("Low");
                        tasksVTO.setPrimaryAssignToHubble(primaryAssignTo);
                        tasksVTO.setSecondaryAssignToHubble(secondaryAssignTo);
                        tasksVTO.setCommentsHubble(comments);
                        tasksVTO.setType(getType());
                        tasksVTO.setSecondaryAssignToLoginIdForHubble(secondaryAssignToLoginIdForHubble);
                        // System.out.println("in doaddtask hubble-->"+getTitleHubble()+" -- "+getIssueTypeHubble()+" -- "+getPriorityHubble()+" -- "+getPrimaryAssignToHubble()+" -- "+getSecondaryAssignToHubble()+" -- "+getCommentsHubble());
                    }
                    if (getType().equalsIgnoreCase("2")) {
                        issueTitle = getTitleProject();
                        issueType = getIssueTypeProject();
                        priority = getPriorityProject();
                        primaryAssignTo = getPrimaryAssignToProject();
                        secondaryAssignTo = getSecondaryAssignToProject();
                        secondaryAssignToLoginIdForProject = getSecondaryAssignToLoginIdForProject();
                        comments = getCommentsPorject();
                        resolution = getResolutionProject();
                        tasksVTO.setResolutionProject(resolution);
                        tasksVTO.setTitleProject(issueTitle);
                        tasksVTO.setIssueTypeProject(issueType);
                        tasksVTO.setPriorityProject(priority);
                        tasksVTO.setPriority("Low");
                        tasksVTO.setPriorityHubble("Low");
                        tasksVTO.setPriorityInfra("Low");
                        tasksVTO.setPriorityNetwork("Low");
                        tasksVTO.setPrimaryAssignToProject(primaryAssignTo);
                        tasksVTO.setSecondaryAssignToProject(secondaryAssignTo);
                        tasksVTO.setCommentsProject(comments);
                        tasksVTO.setProjectId(getProjectId());
                        tasksVTO.setType(getType());
                        tasksVTO.setSecondaryAssignToLoginIdForProject(secondaryAssignToLoginIdForProject);
                        //System.out.println("in doaddtask project-->"+getTitleProject()+" -- "+getIssueTypeProject()+" -- "+getPriorityProject()+" -- "+getPrimaryAssignToProject()+" -- "+getSecondaryAssignToProject()+" -- "+getCommentsPorject());
                    }
                    if (getType().equalsIgnoreCase("3")) {
                        issueTitle = getTitleNetwork();
                        issueType = getIssueTypeNetwork();
                        priority = getPriorityNetwork();
                        primaryAssignTo = getPrimaryAssignToNetwork();
                        secondaryAssignTo = getSecondaryAssignToNetwork();
                        secondaryAssignToLoginIdForNetwork = getSecondaryAssignToLoginIdForNetwork();
                        comments = getCommentsNetwork();
                        resolution = getResolutionNetwork();
                        tasksVTO.setResolutionNetwork(resolution);
                        tasksVTO.setTitleNetwork(issueTitle);
                        tasksVTO.setIssueTypeNetwork(issueType);
                        tasksVTO.setPriorityNetwork(priority);
                        tasksVTO.setPriority("Low");
                        tasksVTO.setPriorityHubble("Low");
                        tasksVTO.setPriorityProject("Low");
                        tasksVTO.setPriorityInfra("Low");
                        tasksVTO.setPrimaryAssignToNetwork(primaryAssignTo);
                        tasksVTO.setSecondaryAssignToNetwork(secondaryAssignTo);
                        tasksVTO.setCommentsNetwork(comments);
                        tasksVTO.setType(getType());
                        tasksVTO.setSecondaryAssignToLoginIdForNetwork(secondaryAssignToLoginIdForNetwork);
                        //System.out.println("in doaddtask network-->"+getTitleNetwork()+" -- "+getIssueTypeNetwork()+" -- "+getPriorityNetwork()+" -- "+getPrimaryAssignToNetwork()+" -- "+getSecondaryAssignToNetwork()+" -- "+getCommentsNetwork());
                    }
                    if (getType().equalsIgnoreCase("4")) {
                        issueTitle = getTitleInfra();
                        issueType = getIssueTypeInfra();
                        priority = getPriorityInfra();
                        primaryAssignTo = getPrimaryAssignToInfra();
                        secondaryAssignTo = getSecondaryAssignToInfra();
                        secondaryAssignToLoginIdForInfra = getSecondaryAssignToLoginIdForInfra();
                        comments = getCommentsInfra();
                        resolution = getResolutionInfra();
                        tasksVTO.setResolutionInfra(resolution);
                        tasksVTO.setTitleInfra(issueTitle);
                        tasksVTO.setIssueTypeInfra(issueType);
                        tasksVTO.setPriorityInfra(priority);
                        tasksVTO.setPriority("Low");
                        tasksVTO.setPriorityHubble("Low");
                        tasksVTO.setPriorityProject("Low");
                        tasksVTO.setPriorityNetwork("Low");
                        tasksVTO.setPrimaryAssignToInfra(primaryAssignTo);
                        tasksVTO.setSecondaryAssignToInfra(secondaryAssignTo);
                        tasksVTO.setCommentsInfra(comments);
                        tasksVTO.setType(getType());
                        tasksVTO.setSecondaryAssignToLoginIdForInfra(secondaryAssignToLoginIdForInfra);
                        //System.out.println("in doaddtask inra-->"+getTitleInfra()+" -- "+getIssueTypeInfra()+" -- "+getPriorityInfra()+" -- "+getPrimaryAssignToInfra()+" -- "+getSecondaryAssignToInfra()+" -- "+getCommentsInfra());
                    }
                    if (getType().equalsIgnoreCase("5")) {
                        issueTitle = getTitleOthers();
                        issueType = getIssueTypeOthers();
                        priority = getPriorityOthers();
                        primaryAssignTo = getPrimaryAssignToLoginIdforOthers();
                        String primaryassignedToName = getPrimaryAssignToforOthers();
                        secondaryAssignTo = getSecondaryAssignToOthers();
                        secondaryAssignToLoginIdForOthers = getSecondaryAssignToLoginIdForOthers();
                        comments = getCommentsOthers();
                        resolution = getResolutionOthers();
                        tasksVTO.setResolutionInfra(resolution);
                        tasksVTO.setTitleOthers(issueTitle);
                        tasksVTO.setIssueTypeOthers(issueType);
                        tasksVTO.setPriorityOthers(priority);
                        tasksVTO.setPriority("Low");
                        tasksVTO.setPriorityHubble("Low");
                        tasksVTO.setPriorityProject("Low");
                        tasksVTO.setPriorityNetwork("Low");
                        tasksVTO.setPrimaryAssignToforOthers(primaryassignedToName);
                        tasksVTO.setSecondaryAssignToOthers(secondaryAssignTo);
                        tasksVTO.setCommentsInfra(comments);
                        tasksVTO.setType(getType());
                        tasksVTO.setSecondaryAssignToLoginIdForInfra(secondaryAssignToLoginIdForInfra);
                        tasksVTO.setPrimaryAssignToforOthers(primaryAssignTo);
                        //System.out.println("in doaddtask inra-->"+getTitleInfra()+" -- "+getIssueTypeInfra()+" -- "+getPriorityInfra()+" -- "+getPrimaryAssignToInfra()+" -- "+getSecondaryAssignToInfra()+" -- "+getCommentsInfra());
                    }
                    
                    
                    HashMap tempEmpMap = new HashMap();
                    tempEmpMap.put("", "");
                    if (getType().equalsIgnoreCase("0")) {
                        setPrimaryAssignToMap(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnIssueRel(getType()));
                        //  setSecondaryAssignToMap(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnIssueRel(getType()));
                        setPrimaryAssignToMapForProject(tempEmpMap);
                        setPrimaryAssignToMapForHubble(tempEmpMap);
                        setPrimaryAssignToMapForInfra(tempEmpMap);
                        setPrimaryAssignToMapForNetwork(tempEmpMap);
                        //  setSecondaryAssignToMapForProject(tempEmpMap);
                        // setSecondaryAssignToMapForHubble(tempEmpMap);
                        //  setSecondaryAssignToMapForInfra(tempEmpMap);
                        //  setSecondaryAssignToMapForNetwork(tempEmpMap);
                    }
                    if (getType().equalsIgnoreCase("1")) {
                        setPrimaryAssignToMap(tempEmpMap);
                        //  setSecondaryAssignToMap(tempEmpMap);
                        setPrimaryAssignToMapForProject(tempEmpMap);
                        setPrimaryAssignToMapForHubble(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnIssueRel(getType()));
                        setPrimaryAssignToMapForInfra(tempEmpMap);
                        setPrimaryAssignToMapForNetwork(tempEmpMap);
                        //  setSecondaryAssignToMapForProject(tempEmpMap);
                        //  setSecondaryAssignToMapForHubble(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue(getType()));
                        //   setSecondaryAssignToMapForInfra(tempEmpMap);
                        //   setSecondaryAssignToMapForNetwork(tempEmpMap);
                    }
                    if (getType().equalsIgnoreCase("3")) {
                        setPrimaryAssignToMap(tempEmpMap);
                        //   setSecondaryAssignToMap(tempEmpMap);
                        setPrimaryAssignToMapForProject(tempEmpMap);
                        setPrimaryAssignToMapForHubble(tempEmpMap);
                        setPrimaryAssignToMapForInfra(tempEmpMap);
                        setPrimaryAssignToMapForNetwork(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnIssueRel(getType()));
                        //   setSecondaryAssignToMapForProject(tempEmpMap);
                        //  setSecondaryAssignToMapForHubble(tempEmpMap);
                        //  setSecondaryAssignToMapForInfra(tempEmpMap);
                        //  setSecondaryAssignToMapForNetwork(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue(getType()));
                    }
                    if (getType().equalsIgnoreCase("4")) {
                        setPrimaryAssignToMap(tempEmpMap);
                        //setSecondaryAssignToMap(tempEmpMap);
                        setPrimaryAssignToMapForProject(tempEmpMap);
                        setPrimaryAssignToMapForHubble(tempEmpMap);
                        setPrimaryAssignToMapForInfra(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnIssueRel(getType()));
                        setPrimaryAssignToMapForNetwork(tempEmpMap);
                        // setSecondaryAssignToMapForProject(tempEmpMap);
                        //  setSecondaryAssignToMapForHubble(tempEmpMap);
                        //  setSecondaryAssignToMapForInfra(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue(getType()));
                        // setSecondaryAssignToMapForNetwork(tempEmpMap);
                    }
                     if (getType().equalsIgnoreCase("4")) {
                        setPrimaryAssignToMap(tempEmpMap);
                        //setSecondaryAssignToMap(tempEmpMap);
                        setPrimaryAssignToMapForProject(tempEmpMap);
                        setPrimaryAssignToMapForHubble(tempEmpMap);
                        setPrimaryAssignToMapForInfra(tempEmpMap);
                        setPrimaryAssignToMapForNetwork(tempEmpMap);
                        // setSecondaryAssignToMapForProject(tempEmpMap);
                        //  setSecondaryAssignToMapForHubble(tempEmpMap);
                        //  setSecondaryAssignToMapForInfra(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnHubbleNetworkInfraIssue(getType()));
                        // setSecondaryAssignToMapForNetwork(tempEmpMap);
                    }
                    if (getType().equalsIgnoreCase("2")) {
                        if (!"0".equals(getProjectId())) {
                            setPrimaryAssignToMap(tempEmpMap);
                            // setSecondaryAssignToMap(tempEmpMap);
                            setPrimaryAssignToMapForProject(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnPrjIssue(getProjectId()));
                            // System.out.println("setPrimaryAssignToMapForProject-->"+getPrimaryAssignToMapForProject());
                            setPrimaryAssignToMapForHubble(tempEmpMap);
                            setPrimaryAssignToMapForInfra(tempEmpMap);
                            setPrimaryAssignToMapForNetwork(tempEmpMap);
                            // setSecondaryAssignToMapForProject(dataSourceDataProvider.getInstance().getTaskEmpDetailsBasedOnPrjIssue(getProjectId()));
                            // System.out.println("setPrimaryAssignToMapForProject-->"+getSecondaryAssignToMapForProject());
                            // setSecondaryAssignToMapForHubble(tempEmpMap);
                            //setSecondaryAssignToMapForInfra(tempEmpMap);
                            // setSecondaryAssignToMapForNetwork(tempEmpMap);
                        }
                    }
                    setProjectsList(dataSourceDataProvider.getInstance().getProjectNamesListByEmpId(empId));
                    //  System.out.println("percom-->"+getPerCompleted());
                    int tasksId = ServiceLocator.getTasksService().addOrUpdateTask1(this, httpServletRequest, issueTitle, issueType, priority, primaryAssignTo, secondaryAssignTo, comments, secondaryAssignToLoginId, secondaryAssignToLoginIdForHubble, secondaryAssignToLoginIdForProject, secondaryAssignToLoginIdForNetwork, secondaryAssignToLoginIdForInfra,secondaryAssignToLoginIdForOthers);
                    if (tasksId > 0 && getStatusId().equalsIgnoreCase("closed")) {
                        String toAddress = "";
                        String cCAddress = "";
                        String subject = "";
                        String bodyContent = "";
                        String createdDate = "";
                        String mailDeliverDate = "";
                        String mailFlag = "0";
                        String bCCAddress = "";
                        toAddress = dataSourceDataProvider.getInstance().getTasksCreatedBy(tasksId);
                        cCAddress = dataSourceDataProvider.getInstance().getPrimaryAssignToMailByLoginId(tasksId, primaryAssignTo);
                        //System.out.println("secondaryAssignTo-->"+secondaryAssignToLoginId);
                        if (!"".equals(secondaryAssignTo) && !"-1".equals(secondaryAssignTo)) {
                            //System.out.println("secondaryAssignTo1-->"+secondaryAssignToLoginId);
                            if (getType().equalsIgnoreCase("0")) {
                                cCAddress = cCAddress + "," + dataSourceDataProvider.getInstance().getSecondaryAssignToMailByLoginId(tasksId, secondaryAssignToLoginId);
                            } else if (getType().equalsIgnoreCase("1")) {
                                cCAddress = cCAddress + "," + dataSourceDataProvider.getInstance().getSecondaryAssignToMailByLoginId(tasksId, secondaryAssignToLoginIdForHubble);
                            } else if (getType().equalsIgnoreCase("2")) {
                                cCAddress = cCAddress + "," + dataSourceDataProvider.getInstance().getSecondaryAssignToMailByLoginId(tasksId, secondaryAssignToLoginIdForProject);
                            } else if (getType().equalsIgnoreCase("3")) {
                                cCAddress = cCAddress + "," + dataSourceDataProvider.getInstance().getSecondaryAssignToMailByLoginId(tasksId, secondaryAssignToLoginIdForNetwork);
                            } else if (getType().equalsIgnoreCase("4")) {
                                cCAddress = cCAddress + "," + dataSourceDataProvider.getInstance().getSecondaryAssignToMailByLoginId(tasksId, secondaryAssignToLoginIdForInfra);
                            }else if (getType().equalsIgnoreCase("5")) {
                                cCAddress = cCAddress + "," + dataSourceDataProvider.getInstance().getSecondaryAssignToMailByLoginId(tasksId, secondaryAssignToLoginIdForOthers);
                            }
                        }

                        subject = issueTitle;
                        StringBuilder htmlText = new StringBuilder();

                      htmlText.append("<!DOCTYPE html><html><head>");
                            htmlText.append("<meta charset='utf-8'><meta name='viewport' content='width=device-width, initial-scale=1'><meta http-equiv='X-UA-Compatible' content='IE=edge' /><style type='text/css'>");
                            htmlText.append("body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;}");
                            htmlText.append("table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;}");
                            htmlText.append("img{-ms-interpolation-mode: bicubic;}");
                            htmlText.append("img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}");
                            htmlText.append("table{border-collapse: collapse !important;}");
                            htmlText.append("body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}");
                            htmlText.append("a[x-apple-data-detectors] {");
                            htmlText.append("color: inherit !important;");
                            htmlText.append("text-decoration: none !important;");
                            htmlText.append("font-size: inherit !important;");
                            htmlText.append("font-family: inherit !important;");
                            htmlText.append("font-weight: inherit !important;");
                            htmlText.append("line-height: inherit !important;");
                            htmlText.append("}");
                            htmlText.append("@media screen and (max-width: 525px) {");
                            htmlText.append(".wrapper {");
                            htmlText.append("width: 100% !important;");
                            htmlText.append("max-width: 100% !important;");
                            htmlText.append("}");
                            htmlText.append(".logo img {");
                            htmlText.append("margin: 0 auto !important;");
                            htmlText.append("}");
                            htmlText.append(".mobile-hide {");
                            htmlText.append("display: none !important;");
                            htmlText.append("}");
                            htmlText.append(".img-max {");
                            htmlText.append("max-width: 100% !important;");
                            htmlText.append("width: 100% !important;");
                            htmlText.append("height: auto !important;");
                            htmlText.append("}");
                            htmlText.append(".responsive-table {");
                            htmlText.append(" width: 100% !important;}");
                            htmlText.append(".padding {padding: 10px 5% 15px 5% !important;}");
                            htmlText.append(".padding-meta {padding: 30px 5% 0px 5% !important;text-align: center;}");
                            htmlText.append(".padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}");
                            htmlText.append(".no-padding {padding: 0 !important;}");
                            htmlText.append(".section-padding {padding: 50px 15px 50px 15px !important;}");
                            htmlText.append(".mobile-button-container {margin: 0 auto;width: 100% !important;}");
                            htmlText.append(".mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important;display: block !important;}}");
                            htmlText.append("div[style*='margin: 16px 0;'] { margin: 0 !important; }");
                            htmlText.append("</style></head>");
                            htmlText.append("<body style='margin: 0 !important; padding: 0 !important;'>");
                            htmlText.append("<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>Task has been closed by <b>" + loggedInEmpName + "</b></div>");
                            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td bgcolor='#ffffff' align='center'>");
                            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper;>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='center' valign='top' style='padding: 15px 0;' class='logo'>");
                            htmlText.append("<a href='http://www.miraclesoft.com/' target='_blank'><img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'></a>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
                            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td>");
                            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>Task Management System</b></td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");
                            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td>");
                            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>Team,</b><br>A task has been closed by <b>" + loggedInEmpName + "</b></td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
                            if ((getStatusId() != null) && !"".equals(getStatusId())) {
                            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Status:</b> Closed <br> ");
                        }
                           
                            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Issue Id:</b> " + taskId + "<br>");
                           
                            if ((issueTitle != null) && !"".equals(issueTitle)) {
                                htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Issue Title:</b> " + issueTitle + "</b><br>");
                            }
                            
                            

                            if ((priority != null) && !"".equals(priority)) {
                                htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Issue Severity:</b> " + priority + "<br>");
                            }
                            if ((getCreatedBy() != null) && !"".equals(getCreatedBy())) {
                                htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Created By:</b> " + toAddress.split("@")[0] + "<br>");
                            }
                            if (getResourceType().equalsIgnoreCase("e")) {
                                if ((getCreatedBy() != null) && !"".equals(getCreatedBy())) {
                                    htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Created By Email:</b> " + toAddress + "<br>");
                                }
                            } else {
                                if ((getCreatedBy() != null) && !"".equals(getCreatedBy())) {
                                    htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Created By Email:</b> " + DataSourceDataProvider.getInstance().getContactOfficeMail(empId) + "<br>");
                                }
                            }
                            if (getResourceType().equalsIgnoreCase("e")) {
                            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Modified By:</b> " + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString() + " <br> ");
                        } else {
                            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Modified By:</b> " + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString() + " <br> ");
                        }
                            
                            if ((comments != null) && !"".equals(comments)) {
                                htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Comments:</b> " + comments + "<br>");
                            }
                            if ((resolution != null) && !"".equals(resolution)) {
                            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Resolution :</b> " + resolution + " <br>");
                        } else {
                            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Resolution :</b> ----- <br>");
                        }
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td>");
                            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>");
                            htmlText.append("Thanks & Regards,<br>Corporate Application Support Team, <br>Miracle Software Systems, Inc. <br>Email: hubble@miraclesoft.com <br>Phone: (+1)248-233-1814");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td>");
                            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");
                            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' class='responsive-table'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td width='200' align='center' style='text-align: center;'><table width='200' cellpadding='0' cellspacing='0' align='center'>");
                            htmlText.append("<tr>");
                            htmlText.append("<td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                            htmlText.append("<td width='10'><a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                            htmlText.append("<td width='10'><a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                            htmlText.append("<td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                            htmlText.append("<td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                            htmlText.append("<td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td height='10'>");
                            htmlText.append("</td>");
                            htmlText.append(" </tr>");
                            htmlText.append("<tr>");
                            htmlText.append("<td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems");

                            htmlText.append("<br>");
                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");

                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");

                            htmlText.append("</td>");
                            htmlText.append("</tr>");
                            htmlText.append("</table>");
                            htmlText.append("</body>");
                            htmlText.append("</html>");
                            
                            
                            bodyContent = htmlText.toString();
                        // System.out.println(bodyContent.length());
                        bCCAddress = Properties.getProperty("bccAddress");
                       // ServiceLocator.getMailServices().doAddEmailLog(toAddress, cCAddress, issueTitle, bodyContent, createdDate, bCCAddress);
                        
                         if(Properties.getProperty("Mail.Flag").equals("1")) {
                              ServiceLocator.getMailServices().doAddEmailLogNew(toAddress, cCAddress, issueTitle, bodyContent, createdDate, bCCAddress,"Tasks");
                         }
                        
                        
                    }
                    if (tasksId > 0) {
                        resultMessage = "Task has been successfully Updated!";
                        setNavigateTo("getTask");
                        setResM(resultMessage);
                        resultType = SUCCESS;
                    } else {

                        resultMessage = "Sorry! Please Try again!";
                        resultType = INPUT;
                    }

                    //  httpServletRequest.setAttribute("resultMessage", resultMessage);
                    httpServletRequest.getSession(false).setAttribute("resultMessage", resultMessage);
                    //   httpServletRequest.setAttribute("accessType",getAccessType());
                    setCurrentTask(ServiceLocator.getTasksService().getTask(getTaskId(), getObjectid(), getResourceType(), getType()));
                    prepare();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    //Description : For reaminder Popup

    public String taskReminderPopup() {
        setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
        String userId = "";
        String userName = "";
        if (getResourceType().equalsIgnoreCase("c")) {

            userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString();
        } else if (getResourceType().equalsIgnoreCase("v")) {
            userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_VENDOR_USER_ID).toString();
        } else if (getResourceType().equalsIgnoreCase("e")) {
            userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
        }
        try {
            String responseString = "";
            List taskDetails = new ArrayList();
            String priEmail = "";
            String secEmail = "";
            //CreatedBy,TaskId,IssueTitle,DateAssigned,DateClosed,AssignedTo,SecAssignTO,PercentageComplted
            String createdBy = "";
            String taskId = "";
            String issueTitle = "";
            String dateAssigned = "";
            String dateClosed = "";
            String assignedTo = "";
            String secAssignTO = "";
            String percentageComplted = "";
            String cutomerName = "";
            String assignedToType = "";
            String secAssignTOType = "";
            setTaskReminderId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TASK_REMINDER_Id).toString());
            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            taskDetails = dataSourceDataProvider.getTaskDetails(getTaskReminderId());
            createdBy = taskDetails.get(0).toString();

            taskId = taskDetails.get(1).toString();
            issueTitle = taskDetails.get(2).toString();
            dateAssigned = taskDetails.get(3).toString();
            dateClosed = taskDetails.get(4).toString();
            assignedTo = taskDetails.get(5).toString();
            assignedToType = taskDetails.get(8).toString();
            if (!taskDetails.get(7).equals("")) {
                percentageComplted = taskDetails.get(7).toString();
            }
            cutomerName = dataSourceDataProvider.getAccountName(Integer.parseInt(taskId));
            //  priEmail=assignedTo;
            priEmail = dataSourceDataProvider.getEmailIdByPrimaryAssignId(assignedTo, assignedToType);
            if (!taskDetails.get(6).equals("")) {
                secAssignTO = taskDetails.get(6).toString();
                secAssignTOType = taskDetails.get(9).toString();
            }
            secEmail = dataSourceDataProvider.getEmailIdByPrimaryAssignId(secAssignTO, secAssignTOType);
            String isMailSent = "";
            if (!priEmail.equals("")) {
                if (Properties.getProperty("Mail.Flag").equals("1")) {
                    isMailSent = MailManager.sendReminderForTask(userId, cutomerName, priEmail, secEmail, createdBy, issueTitle, dateAssigned, dateClosed, percentageComplted, getMessage(), getResourceType());
                }
            }
            if (isMailSent.equalsIgnoreCase("success")) {
                setMailsent(1);
            } else {
                setMailsent(2);
            }

        } catch (Exception ex) {
            setMailsent(2);
            ex.printStackTrace();
        }
        return SUCCESS;
    }
    //Description: For getting team tasks

    public String myTeamTasks() {
        resultType = LOGIN;
        //  System.out.println("in mytasks-->");
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
            String userId = null;
            String custId = null;
            String deptId = null;
            int empId = 0;
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_MYTEAM_ISSUES", userRoleId)) {
                try {
                    //       System.out.println("in mytasks befor if-->");
                    if (getResourceType().equalsIgnoreCase("e")) {
                        userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                        deptId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString();
                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                    } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                        custId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                        setMyAccounts(dataSourceDataProvider.getInstance().getMyCrmAccountList(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString())));
                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                    }
                    setStartDate(DateUtility.getInstance().getLastSixtyDaysDateFromCurrentDate());
                    setEndDate(DateUtility.getInstance().getCurrentMySqlDate());
                    StringBuilder stringBuilder = new StringBuilder();
                    /* Search Query Based on SearchForm */
                    if (getSubmitFrom() == null) {
                       // stringBuilder.append("SELECT Id,CreatedDate,STATUS,Severity,Description,PriAssignTO,SecAssignTo,Title,CreatedBy,IssueRel,Project_Id,percentageCompleted,DueDate FROM tblEmpTasks");
                       stringBuilder.append("SELECT tblEmpTasks.Id,tblEmpTasks.CreatedDate,tblEmpTasks.STATUS,tblEmpTasks.Severity,tblEmpTasks.Description,(CASE WHEN tblEmpTasks.PriAssignTO<>'' THEN  CONCAT(TRIM(t1.FName),'.',TRIM(t1.LName)) ELSE '' END)  AS PriAssignTO,(CASE WHEN tblEmpTasks.CreatedBy<>'' THEN  CONCAT(TRIM(t2.FName),'.',TRIM(t2.LName)) ELSE '' END) AS CreatedBy,tblEmpTasks.Title,tblEmpTasks.IssueRel,tblEmpTasks.Project_Id,tblEmpTasks.percentageCompleted,tblEmpTasks.DueDate FROM tblEmpTasks  LEFT JOIN tblEmployee t1 ON(tblEmpTasks.PriAssignTO=t1.LoginId) LEFT JOIN tblEmployee t2 ON(tblEmpTasks.CreatedBy=t2.LoginId)  ");  stringBuilder.append(" where tblEmpTasks.Status <> 'Closed' AND tblEmpTasks.CreatedDate > '2013-06-14 00:00:00' and ");//CreatedBy='"+userId+"' OR ");      
                        stringBuilder.append(" ((tblEmpTasks.CreatedBy IN");
                        stringBuilder.append("(");
                        if (getResourceType().equalsIgnoreCase("e")) {
                            stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue1(userId, deptId, empId));
                        } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                            stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForCustIssue(custId));
                        }
                        stringBuilder.append("))");
                        //stringBuilder.append(" OR AssignedTo ='"+userId+"'");
                        stringBuilder.append(" OR (tblEmpTasks.PriAssignTO IN");
                        stringBuilder.append("(");
                        if (getResourceType().equalsIgnoreCase("e")) {
                            stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue1(userId, deptId, empId));
                        } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                            stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForCustIssue(custId));
                        }
                        stringBuilder.append("))");
                        //stringBuilder.append(" OR SecAssignTO ='"+userId+"'");
                        stringBuilder.append(" OR (tblEmpTasks.SecAssignTo IN");
                        stringBuilder.append("(");
                        if (getResourceType().equalsIgnoreCase("e")) {
                            stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue1(userId, deptId, empId));
                        } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                            stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForCustIssue(custId));
                        }
                        stringBuilder.append(")))");
                   //     stringBuilder.append(" ORDER BY Startdate DESC LIMIT 150");
                        // stringBuilder.append(" where DATE_SUB(CURDATE(),INTERVAL 3 MONTH) <= DateCreated and CreatedBy IN ("+DataSourceDataProvider.getInstance().getLeaveApprovalList(userId)+")  ORDER BY DateCreated DESC LIMIT 150");      
                    }

                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_TASKS_LIST) != null) {
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_TASKS_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_TASKS_LIST, stringBuilder.toString());

                    setCurrentSearch("doSearchTeamTasks");

                    setAddBtnVisible("TeamTasks");
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.TASKNAVTO, "doSearchTeamTasks");
                    setSubnavigateTo("getTeamTasks");

                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    //Description: Query setting For Searching team tasks 

    public String doSearchTeamTasks() {
        resultType = LOGIN;
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            String userId = null;
            String custId = null;
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String deptId = null;
            int empId = 0;
            resultType = "accessFailed";
            setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("DO_SEARCH_TEAM_ISSUES", userRoleId)) {
                try {
                    if (getResourceType().equalsIgnoreCase("e")) {
                        userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                        deptId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString();
                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());

                    } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                        custId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                        setMyAccounts(dataSourceDataProvider.getInstance().getMyCrmAccountList(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString())));
                        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                    }

                    if ("Search".equalsIgnoreCase(getSubmitFrom())) {
                        stringBuilder = new StringBuilder();

                    //    stringBuilder.append("SELECT Id,CreatedDate,STATUS,Severity,Description,PriAssignTO,SecAssignTo,Title,CreatedBy,IssueRel,Project_Id,percentageCompleted,DueDate FROM tblEmpTasks");
                 stringBuilder.append("SELECT tblEmpTasks.Id,tblEmpTasks.CreatedDate,tblEmpTasks.STATUS,tblEmpTasks.Severity,tblEmpTasks.Description,(CASE WHEN tblEmpTasks.PriAssignTO<>'' THEN  CONCAT(TRIM(t1.FName),'.',TRIM(t1.LName)) ELSE '' END)  AS PriAssignTO,(CASE WHEN tblEmpTasks.CreatedBy<>'' THEN  CONCAT(TRIM(t2.FName),'.',TRIM(t2.LName)) ELSE '' END) AS CreatedBy,tblEmpTasks.Title,tblEmpTasks.IssueRel,tblEmpTasks.Project_Id,tblEmpTasks.percentageCompleted,tblEmpTasks.DueDate FROM tblEmpTasks  LEFT JOIN tblEmployee t1 ON(tblEmpTasks.PriAssignTO=t1.LoginId) LEFT JOIN tblEmployee t2 ON(tblEmpTasks.CreatedBy=t2.LoginId)  ");        if (null == getStatusId()) {
                            setStatusId("");
                        }
                        // if(null == getCategoryId()) setCategoryId("");
                        if (null == getPreAssignEmpId()) {
                            setPreAssignEmpId("");
                        }
                        if (null == getPostAssignEmpId()) {
                            setPostAssignEmpId("");
                        }

                        //stringBuilder.append(" where Status <> 'Closed' ");
                        stringBuilder.append(" where 1=1 ");

                        if ("".equals(getPreAssignEmpId()) && "".equals(getPostAssignEmpId())) {
                            stringBuilder.append(" AND((");
                            stringBuilder.append(" tblEmpTasks.CreatedBy IN");
                            stringBuilder.append("(");
                            if (getResourceType().equalsIgnoreCase("e")) {
                                //stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue(userId,deptId));
                                stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue1(userId, deptId, empId));
                            } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                                stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForCustIssue(custId));
                            }
                            stringBuilder.append("))");
                            stringBuilder.append(" OR (tblEmpTasks.PriAssignTO IN");
                            stringBuilder.append("(");
                            if (getResourceType().equalsIgnoreCase("e")) {
                                stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue1(userId, deptId, empId));
                            } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                                stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForCustIssue(custId));
                            }
                            stringBuilder.append("))");
                            // stringBuilder.append(" OR (SecAssignTO ='"+userId+"'");
                            stringBuilder.append(" OR (tblEmpTasks.SecAssignTo IN");
                            stringBuilder.append("(");
                            if (getResourceType().equalsIgnoreCase("e")) {
                                stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForIssue1(userId, deptId, empId));
                            } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                                stringBuilder.append(dataSourceDataProvider.getInstance().getMyTeamMembersForCustIssue(custId));
                            }
                            stringBuilder.append(")))");
                        }
                        String myTeam = null;
                        // " AssignedTo IN ("+ DataSourceDataProvider.getInstance().getEmpNameUsingReportsTo(userId)+")"
                     /*   if (getResourceType().equalsIgnoreCase("e")) {
                            myTeam = DataSourceDataProvider.getInstance().getMyTeamMembersForIssue1(userId, deptId, empId);
                        } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                            myTeam = DataSourceDataProvider.getInstance().getMyTeamMembersForCustIssue(custId);
                        } */
                        boolean checkPre = DataSourceDataProvider.getInstance().isInteger(getPreAssignEmpId());
                        if (!checkPre) {
                            if (!"".equals(getPreAssignEmpId())) {
                             //   if (myTeam.contains(getPreAssignEmpId())) {

                                    stringBuilder.append(" AND tblEmpTasks.CreatedBy='" + getPreAssignEmpId() + "' ");
                              //  }
                            }
                        } else {
                            // System.out.println("in else");
                            boolean checkEmp = DataSourceDataProvider.getInstance().checkEmployeeOrCustomer(Integer.parseInt(getPreAssignEmpId()));
                            String loginId = "";
                            if (checkEmp) {
                                loginId = DataSourceDataProvider.getInstance().getEmpLoginIdById(getPreAssignEmpId());
                            } else {
                                loginId = DataSourceDataProvider.getInstance().getCustLoginIdByCustId(Integer.parseInt(getPreAssignEmpId()));
                            }
                            // System.out.println("loginId--->" + loginId);
                            if (!"".equals(getPreAssignEmpId())) {
                              //  if (myTeam.contains(loginId)) {
                                    stringBuilder.append(" AND tblEmpTasks.CreatedBy='" + loginId + "' ");
                               // }
                            }
                        }
                        // System.out.println(getPostAssignEmpId());
                        // System.out.println(getPostAssignedToUID());
                        boolean check = DataSourceDataProvider.getInstance().isInteger(getPostAssignEmpId());
                        if (!check) {
                            if (!"".equals(getPostAssignedToUID())) {

                               // if (myTeam.contains(getPostAssignEmpId())) {

                                    stringBuilder.append(" AND (tblEmpTasks.PriAssignTO='" + getPostAssignEmpId() + "' OR tblEmpTasks.SecAssignTo='" + getPostAssignEmpId() + "')");
                               // }
                            }
                        }  else {
                            
                             boolean checkEmp = DataSourceDataProvider.getInstance().checkEmployeeOrCustomer(Integer.parseInt(getPostAssignEmpId()));
                            String loginId = "";
                            if (checkEmp) {
                                loginId = DataSourceDataProvider.getInstance().getEmpLoginIdById(getPostAssignEmpId());
                            } else {
                                loginId = DataSourceDataProvider.getInstance().getCustLoginIdByCustId(Integer.parseInt(getPostAssignEmpId()));
                            }
                            //    String loginId = DataSourceDataProvider.getInstance().getCustLoginIdByCustId(Integer.parseInt(getPostAssignEmpId()));
                             if (!"".equals(getPostAssignedToUID())) {
                            //     if (myTeam.contains(loginId)) {
                                      stringBuilder.append(" AND (tblEmpTasks.PriAssignTO='" + loginId + "' OR tblEmpTasks.SecAssignTo='" + loginId + "')");
                           //     }

                            }
                        }
                        if (!"".equals(getStatusId())) {
                            stringBuilder.append(" AND tblEmpTasks.Status LIKE '" + getStatusId() + "' ");
                        }
                        if (!"".equals(getIssueName())) {
                            stringBuilder.append(" AND tblEmpTasks.Title LIKE '%" + getIssueName() + "%' ");
                        }
                        if (getResourceType().equalsIgnoreCase("e")) {
                            if (!"".equals(getCustomerId()) && !"-1".equals(getCustomerId())) {
                                stringBuilder.append(" AND tblEmpTasks.CustomerId = " + getCustomerId() + " ");
                            }
                            // System.out.println(getProjectId());
                            if (!"".equals(getProjectId()) && !"-1".equals(getProjectId())) {
                                stringBuilder.append(" AND tblEmpTasks.Project_Id = " + getProjectId() + " ");
                            }
                        } else if (getResourceType().equalsIgnoreCase("c") || getResourceType().equalsIgnoreCase("v")) {
                             if (!"".equals(getCustomerId()) && !"-1".equals(getCustomerId())) {
                                stringBuilder.append(" AND tblEmpTasks.CustomerId = " + getCustomerId() + " ");
                            }

                           if (!"".equals(getProjectId()) && !"-1".equals(getProjectId())) {
                                stringBuilder.append(" AND tblEmpTasks.Project_Id = " + getProjectId() + " ");
                            }
                        }
                        if (getStartDate() != null) {
                            stringBuilder.append(" AND date(tblEmpTasks.CreatedDate) >= '" + DateUtility.getInstance().convertStringToMySQLDate(getStartDate()) + "' ");
                        }

                        if (getEndDate() != null) {
                            stringBuilder.append(" AND date(tblEmpTasks.CreatedDate) <= '" + DateUtility.getInstance().convertStringToMySQLDate(getEndDate()) + "' ");
                        }

                       /* if (!"".equals(getTaskId())) {
                            stringBuilder.append("AND Id =" + getTaskId() + "  ");
                        }*/

                        if (!"".equals(getIssueRelType()) && !"-1".equals(getIssueRelType())) {
                            stringBuilder.append(" AND tblEmpTasks.IssueRel = " + getIssueRelType() + " ");
                        }
                        if(!"".equals(getIssueType()) && getIssueType()!=null){
                           stringBuilder.append(" AND tblEmpTasks.IssueType = " + Integer.parseInt(getIssueType()) + " ");
                      
                        }
                           if(!"".equals(getProgress())){
                           stringBuilder.append(" AND tblEmpTasks.percentageCompleted = " + Integer.parseInt(getProgress()) + " ");
                      
                        }	
                      //  stringBuilder.append("ORDER BY CreatedDate DESC LIMIT 150");
                        //     System.out.println("query in search-->"+stringBuilder.toString());
                        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_TASKS_LIST) != null) {
                            httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_TASKS_LIST);
                        }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_TASKS_LIST, stringBuilder.toString());
                    }
                    if (getCheck() == null) {
                        // System.out.println("in if"+getCheck());
                        setCheck("1");
                    } else if (getCheck().equals("")) {
                        // System.out.println("in else"+getCheck());
                        setCheck("1");
                    }
                    setCurrentSearch("doSearchTeamTasks");
                    setStartDate(getStartDate());
                    setEndDate(getEndDate());
                    setProgress(getProgress());
                    setIssueType(getIssueType());
                    setIssueName(getIssueName());
                   setStatusId(getStatusId()); 
                   setCustomerId(getCustomerId());
                   setProjectId(getProjectId());
                   setPreAssignEmpId(getPreAssignEmpId());
                   setPostAssignEmpId(getPostAssignEmpId());
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    //Description: For Adding Attachments for the corresponding tasks
    public String addTaskAttachment() throws Exception {
        resultType = LOGIN;
        //  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            setTaskId(getTaskId());
            setType(getType());
            setProjectId(getProjectId());
            //System.out.println("------->"+getType());
            setTitle(DataSourceDataProvider.getInstance().getTaskNameById(Integer.parseInt(getTaskId())));
            resultType = SUCCESS;
        }
        return resultType;
    }

    //Description: For Adding Attachments for the corresponding tasks
    public String doAddTaskAttachment() {
        resultType = LOGIN;
        //   if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            try {
                setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
                setTaskId(getTaskId());
                //-----------------------
                attachmentService = ServiceLocator.getAttachmentService();
                //  setCurrentTask(tasksVTO);
                generatedPath = null;
                //   setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
                if (getResourceType().equalsIgnoreCase("e")) {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
                } else if (getResourceType().equalsIgnoreCase("c")) {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString());
                } else if (getResourceType().equalsIgnoreCase("v")) {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_VENDOR_USER_ID).toString());
                }
                setObjectid(Integer.parseInt(getTaskId()));
                if (getUploadFileName() != null) {
                    generatedPath = attachmentService.generatePath(Properties.getProperty("Attachments.Path"), "Emp Tasks");
                    File targetDirectory = new File(generatedPath + Properties.getProperty("OS.Compatabliliy.Download") + getUploadFileName());
                    setFileLocation(targetDirectory.toString());
                    FileUtils.copyFile(getUpload(), targetDirectory);
                    setObjectType("Emp Task");
                } else {
                    this.objectType = "NoFile";
                    this.setFileLocation("");
                    this.setFilepath("");
                    this.attachmentName = "";
                }

                ServiceLocator.getTasksService().addAttachmentLocation(this);
                httpServletRequest.getSession(false).setAttribute("resultMsg", "Task Attachment uploaded successfully");
                // System.out.println("After insertion");
                resultType = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
    }
    
            		  public String addTaskNotes() throws Exception {
        resultType = LOGIN;
        //  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            setTaskId(getTaskId());
            setType(getType());
            setProjectId(getProjectId());
             setCurrentAction("doaddTaskNotes");
            //System.out.println("------->"+getType());
            setTitle(DataSourceDataProvider.getInstance().getTaskNameById(Integer.parseInt(getTaskId())));
            resultType = SUCCESS;
        }
        return resultType;
    }
                          
      public String doaddTaskNotes() {
        resultType = LOGIN;
        //   if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
            try {
                setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
                setTaskId(getTaskId());
             
                if (getResourceType().equalsIgnoreCase("e")) {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
                } else if (getResourceType().equalsIgnoreCase("c")) {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString());
                } else if (getResourceType().equalsIgnoreCase("v")) {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_VENDOR_USER_ID).toString());
                }
                setObjectid(Integer.parseInt(getTaskId()));
                  setCurrentAction("doaddTaskNotes");
              ServiceLocator.getTasksService().addNotes(this);
          //ServiceLocator.getTasksService().addOrUpdateTask1
                httpServletRequest.getSession(false).setAttribute("resultMsg", "Task Notes uploaded successfully");
                // System.out.println("After insertion");
                resultType = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
    }
      
      public String dogetNotes() throws Exception{
           resultType = LOGIN;
        //  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
          setTaskId(getTaskId());
        
              setNotesId(getNotesId());
            //System.out.println("------->"+getType());
               setTitle(DataSourceDataProvider.getInstance().getTaskNameById(Integer.parseInt(getTaskId())));
               setNotes(ServiceLocator.getTasksService().getTaskNotes(getNotesId()));
             setCurrentAction("doUpdateNotes");
            //System.out.println("------->"+getType());
            
            resultType = SUCCESS;
        }
        return resultType;
    }
      
        public String getdoUpdateNotes() throws Exception {
        resultType = LOGIN;
        //   if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {
          
                    try {
                setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
                setTaskId(getTaskId());
             
                if (getResourceType().equalsIgnoreCase("e")) {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
                } else if (getResourceType().equalsIgnoreCase("c")) {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_USER_ID).toString());
                } else if (getResourceType().equalsIgnoreCase("v")) {
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_VENDOR_USER_ID).toString());
                }
               // setResourceType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString());
               
                 setNotesId(getNotesId());
                  setObjectid(Integer.parseInt(getTaskId()));
           
              setCurrentAction("doUpdateNotes");
          ServiceLocator.getTasksService().updateNotes(this);
       
                httpServletRequest.getSession(false).setAttribute("resultMsg", "Task Notes updated successfully");
                // System.out.println("After insertion");
                resultType = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
           }
public String getTaskDashBoard() throws Exception {
        resultType = LOGIN;
        //   if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) || (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)) {

            try {
                String userId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                setReportsToHierarchyMap(DataSourceDataProvider.getInstance().getMyTeamList(userId));
                DateUtility dateUtility = new DateUtility();
                    
                    setTaskStartDate(dateUtility.YearBackFromCurrentDate());
                    setTaskEndDate(dateUtility.getCurrentMySqlDate());

                resultType = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
    }
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
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

    public List getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List organizationList) {
        this.organizationList = organizationList;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Map getProjectNamesMap() {
        return projectNamesMap;
    }

    public void setProjectNamesMap(Map projectNamesMap) {
        this.projectNamesMap = projectNamesMap;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public List getActivityTypeList() {
        return activityTypeList;
    }

    public void setActivityTypeList(List activityTypeList) {
        this.activityTypeList = activityTypeList;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public List getManagersList() {
        return managersList;
    }

    public void setManagersList(List managersList) {
        this.managersList = managersList;
    }

    public String getDateWithOutTime() {
        return dateWithOutTime;
    }

    public void setDateWithOutTime(String dateWithOutTime) {
        this.dateWithOutTime = dateWithOutTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TasksVTO getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(TasksVTO currentTask) {
        this.currentTask = currentTask;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeverityId() {
        return severityId;
    }

    public void setSeverityId(String severityId) {
        this.severityId = severityId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getAssignedToUID() {
        return assignedToUID;
    }

    public void setAssignedToUID(String assignedToUID) {
        this.assignedToUID = assignedToUID;
    }

    public String getPreAssignEmpId() {
        return preAssignEmpId;
    }

    public void setPreAssignEmpId(String preAssignEmpId) {
        this.preAssignEmpId = preAssignEmpId;
    }

    public String getPostAssignedToUID() {
        return postAssignedToUID;
    }

    public void setPostAssignedToUID(String postAssignedToUID) {
        this.postAssignedToUID = postAssignedToUID;
    }

    public String getPostAssignEmpId() {
        return postAssignEmpId;
    }

    public void setPostAssignEmpId(String postAssignEmpId) {
        this.postAssignEmpId = postAssignEmpId;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
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

    public String getSubnavigateTo() {
        return subnavigateTo;
    }

    public void setSubnavigateTo(String subnavigateTo) {
        this.subnavigateTo = subnavigateTo;
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

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getNavTo() {
        return navTo;
    }

    public void setNavTo(String navTo) {
        this.navTo = navTo;
    }

    public String getResM() {
        return resM;
    }

    public void setResM(String resM) {
        this.resM = resM;
    }

    public String getPerCompleted() {
        return perCompleted;
    }

    public void setPerCompleted(String perCompleted) {
        this.perCompleted = perCompleted;
    }

    public String getNavigateTo() {
        return navigateTo;
    }

    public void setNavigateTo(String navigateTo) {
        this.navigateTo = navigateTo;
    }

    public String getTaskReminderId() {
        return taskReminderId;
    }

    public void setTaskReminderId(String taskReminderId) {
        this.taskReminderId = taskReminderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMailsent() {
        return mailsent;
    }

    public void setMailsent(int mailsent) {
        this.mailsent = mailsent;
    }

    public String getAddBtnVisible() {
        return addBtnVisible;
    }

    public void setAddBtnVisible(String addBtnVisible) {
        this.addBtnVisible = addBtnVisible;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public Map getRepeatMap() {
        return repeatMap;
    }

    public void setRepeatMap(Map repeatMap) {
        this.repeatMap = repeatMap;
    }

    public int getTaskObjectId() {
        return taskObjectId;
    }

    public void setTaskObjectId(int taskObjectId) {
        this.taskObjectId = taskObjectId;
    }

    public String getTaskFileName() {
        return taskFileName;
    }

    public void setTaskFileName(String taskFileName) {
        this.taskFileName = taskFileName;
    }

    /**
     * @return the resourceType
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * @param resourceType the resourceType to set
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * @return the myAccounts
     */
    public Map getMyAccounts() {
        return myAccounts;
    }

    /**
     * @param myAccounts the myAccounts to set
     */
    public void setMyAccounts(Map myAccounts) {
        this.myAccounts = myAccounts;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPrimaryAssignTo() {
        return primaryAssignTo;
    }

    public void setPrimaryAssignTo(String primaryAssignTo) {
        this.primaryAssignTo = primaryAssignTo;
    }

    public String getSecondaryAssignTo() {
        return secondaryAssignTo;
    }

    public void setSecondaryAssignTo(String secondaryAssignTo) {
        this.secondaryAssignTo = secondaryAssignTo;
    }

    public String getCommentsHubble() {
        return commentsHubble;
    }

    public void setCommentsHubble(String commentsHubble) {
        this.commentsHubble = commentsHubble;
    }

    public String getCommentsNetwork() {
        return commentsNetwork;
    }

    public void setCommentsNetwork(String commentsNetwork) {
        this.commentsNetwork = commentsNetwork;
    }

    public String getCommentsPorject() {
        return commentsPorject;
    }

    public void setCommentsPorject(String commentsPorject) {
        this.commentsPorject = commentsPorject;
    }

    public String getIssueTypeHubble() {
        return issueTypeHubble;
    }

    public void setIssueTypeHubble(String issueTypeHubble) {
        this.issueTypeHubble = issueTypeHubble;
    }

    public String getIssueTypeNetwork() {
        return issueTypeNetwork;
    }

    public void setIssueTypeNetwork(String issueTypeNetwork) {
        this.issueTypeNetwork = issueTypeNetwork;
    }

    public String getIssueTypeProject() {
        return issueTypeProject;
    }

    public void setIssueTypeProject(String issueTypeProject) {
        this.issueTypeProject = issueTypeProject;
    }

    public String getPrimaryAssignToHubble() {
        return primaryAssignToHubble;
    }

    public void setPrimaryAssignToHubble(String primaryAssignToHubble) {
        this.primaryAssignToHubble = primaryAssignToHubble;
    }

    public String getPrimaryAssignToNetwork() {
        return primaryAssignToNetwork;
    }

    public void setPrimaryAssignToNetwork(String primaryAssignToNetwork) {
        this.primaryAssignToNetwork = primaryAssignToNetwork;
    }

    public String getPrimaryAssignToProject() {
        return primaryAssignToProject;
    }

    public void setPrimaryAssignToProject(String primaryAssignToProject) {
        this.primaryAssignToProject = primaryAssignToProject;
    }

    public String getPriorityHubble() {
        return priorityHubble;
    }

    public void setPriorityHubble(String priorityHubble) {
        this.priorityHubble = priorityHubble;
    }

    public String getPriorityNetwork() {
        return priorityNetwork;
    }

    public void setPriorityNetwork(String priorityNetwork) {
        this.priorityNetwork = priorityNetwork;
    }

    public String getPriorityProject() {
        return priorityProject;
    }

    public void setPriorityProject(String priorityProject) {
        this.priorityProject = priorityProject;
    }

    public String getSecondaryAssignToHubble() {
        return secondaryAssignToHubble;
    }

    public void setSecondaryAssignToHubble(String secondaryAssignToHubble) {
        this.secondaryAssignToHubble = secondaryAssignToHubble;
    }

    public String getSecondaryAssignToNetwork() {
        return secondaryAssignToNetwork;
    }

    public void setSecondaryAssignToNetwork(String secondaryAssignToNetwork) {
        this.secondaryAssignToNetwork = secondaryAssignToNetwork;
    }

    public String getSecondaryAssignToProject() {
        return secondaryAssignToProject;
    }

    public void setSecondaryAssignToProject(String secondaryAssignToProject) {
        this.secondaryAssignToProject = secondaryAssignToProject;
    }

    public String getTitleHubble() {
        return titleHubble;
    }

    public void setTitleHubble(String titleHubble) {
        this.titleHubble = titleHubble;
    }

    public String getTitleNetwork() {
        return titleNetwork;
    }

    public void setTitleNetwork(String titleNetwork) {
        this.titleNetwork = titleNetwork;
    }

    public String getTitleProject() {
        return titleProject;
    }

    public void setTitleProject(String titleProject) {
        this.titleProject = titleProject;
    }

    public String getCommentsInfra() {
        return commentsInfra;
    }

    public void setCommentsInfra(String commentsInfra) {
        this.commentsInfra = commentsInfra;
    }

    public String getIssueTypeInfra() {
        return issueTypeInfra;
    }

    public void setIssueTypeInfra(String issueTypeInfra) {
        this.issueTypeInfra = issueTypeInfra;
    }

    public String getPrimaryAssignToInfra() {
        return primaryAssignToInfra;
    }

    public void setPrimaryAssignToInfra(String primaryAssignToInfra) {
        this.primaryAssignToInfra = primaryAssignToInfra;
    }

    public String getPriorityInfra() {
        return priorityInfra;
    }

    public void setPriorityInfra(String priorityInfra) {
        this.priorityInfra = priorityInfra;
    }

    public String getSecondaryAssignToInfra() {
        return secondaryAssignToInfra;
    }

    public void setSecondaryAssignToInfra(String secondaryAssignToInfra) {
        this.secondaryAssignToInfra = secondaryAssignToInfra;
    }

    public String getTitleInfra() {
        return titleInfra;
    }

    public void setTitleInfra(String titleInfra) {
        this.titleInfra = titleInfra;
    }

    public Map getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(Map projectsList) {
        this.projectsList = projectsList;
    }

    public Map getPrimaryAssignToMap() {
        return primaryAssignToMap;
    }

    public void setPrimaryAssignToMap(Map primaryAssignToMap) {
        this.primaryAssignToMap = primaryAssignToMap;
    }

    public Map getSecondaryAssignToMap() {
        return secondaryAssignToMap;
    }

    public void setSecondaryAssignToMap(Map secondaryAssignToMap) {
        this.secondaryAssignToMap = secondaryAssignToMap;
    }

    public Map getPrimaryAssignToMapForProject() {
        return primaryAssignToMapForProject;
    }

    public void setPrimaryAssignToMapForProject(Map primaryAssignToMapForProject) {
        this.primaryAssignToMapForProject = primaryAssignToMapForProject;
    }

    public Map getSecondaryAssignToMapForProject() {
        return secondaryAssignToMapForProject;
    }

    public void setSecondaryAssignToMapForProject(Map secondaryAssignToMapForProject) {
        this.secondaryAssignToMapForProject = secondaryAssignToMapForProject;
    }

    public Map getPrimaryAssignToMapForHubble() {
        return primaryAssignToMapForHubble;
    }

    public void setPrimaryAssignToMapForHubble(Map primaryAssignToMapForHubble) {
        this.primaryAssignToMapForHubble = primaryAssignToMapForHubble;
    }

    public Map getPrimaryAssignToMapForInfra() {
        return primaryAssignToMapForInfra;
    }

    public void setPrimaryAssignToMapForInfra(Map primaryAssignToMapForInfra) {
        this.primaryAssignToMapForInfra = primaryAssignToMapForInfra;
    }

    public Map getPrimaryAssignToMapForNetwork() {
        return primaryAssignToMapForNetwork;
    }

    public void setPrimaryAssignToMapForNetwork(Map primaryAssignToMapForNetwork) {
        this.primaryAssignToMapForNetwork = primaryAssignToMapForNetwork;
    }

    public Map getSecondaryAssignToMapForHubble() {
        return secondaryAssignToMapForHubble;
    }

    public void setSecondaryAssignToMapForHubble(Map secondaryAssignToMapForHubble) {
        this.secondaryAssignToMapForHubble = secondaryAssignToMapForHubble;
    }

    public Map getSecondaryAssignToMapForInfra() {
        return secondaryAssignToMapForInfra;
    }

    public void setSecondaryAssignToMapForInfra(Map secondaryAssignToMapForInfra) {
        this.secondaryAssignToMapForInfra = secondaryAssignToMapForInfra;
    }

    public Map getSecondaryAssignToMapForNetwork() {
        return secondaryAssignToMapForNetwork;
    }

    public void setSecondaryAssignToMapForNetwork(Map secondaryAssignToMapForNetwork) {
        this.secondaryAssignToMapForNetwork = secondaryAssignToMapForNetwork;
    }

    public int getiFlag() {
        return iFlag;
    }

    public void setiFlag(int iFlag) {
        this.iFlag = iFlag;
    }

    public String getPerCompletedHR() {
        return perCompletedHR;
    }

    public void setPerCompletedHR(String perCompletedHR) {
        this.perCompletedHR = perCompletedHR;
    }

    public String getIssueRelType() {
        return issueRelType;
    }

    public void setIssueRelType(String issueRelType) {
        this.issueRelType = issueRelType;
    }

    public String getSecondaryAssignToLoginId() {
        return secondaryAssignToLoginId;
    }

    public void setSecondaryAssignToLoginId(String secondaryAssignToLoginId) {
        this.secondaryAssignToLoginId = secondaryAssignToLoginId;
    }

    public String getSecondaryAssignToLoginIdForHubble() {
        return secondaryAssignToLoginIdForHubble;
    }

    public void setSecondaryAssignToLoginIdForHubble(String secondaryAssignToLoginIdForHubble) {
        this.secondaryAssignToLoginIdForHubble = secondaryAssignToLoginIdForHubble;
    }

    public String getSecondaryAssignToLoginIdForInfra() {
        return secondaryAssignToLoginIdForInfra;
    }

    public void setSecondaryAssignToLoginIdForInfra(String secondaryAssignToLoginIdForInfra) {
        this.secondaryAssignToLoginIdForInfra = secondaryAssignToLoginIdForInfra;
    }

    public String getSecondaryAssignToLoginIdForNetwork() {
        return secondaryAssignToLoginIdForNetwork;
    }

    public void setSecondaryAssignToLoginIdForNetwork(String secondaryAssignToLoginIdForNetwork) {
        this.secondaryAssignToLoginIdForNetwork = secondaryAssignToLoginIdForNetwork;
    }

    public String getSecondaryAssignToLoginIdForProject() {
        return secondaryAssignToLoginIdForProject;
    }

    public void setSecondaryAssignToLoginIdForProject(String secondaryAssignToLoginIdForProject) {
        this.secondaryAssignToLoginIdForProject = secondaryAssignToLoginIdForProject;
    }

    public String getResolutionHubble() {
        return resolutionHubble;
    }

    public void setResolutionHubble(String resolutionHubble) {
        this.resolutionHubble = resolutionHubble;
    }

    public String getResolutionInfra() {
        return resolutionInfra;
    }

    public void setResolutionInfra(String resolutionInfra) {
        this.resolutionInfra = resolutionInfra;
    }

    public String getResolutionNetwork() {
        return resolutionNetwork;
    }

    public void setResolutionNetwork(String resolutionNetwork) {
        this.resolutionNetwork = resolutionNetwork;
    }

    public String getResolutionProject() {
        return resolutionProject;
    }

    public void setResolutionProject(String resolutionProject) {
        this.resolutionProject = resolutionProject;
    }

    public Map getIssuerelatedtoList() {
        return issuerelatedtoList;
    }

    public void setIssuerelatedtoList(Map issuerelatedtoList) {
        this.issuerelatedtoList = issuerelatedtoList;
    }

    public Map getIssueTypeMap() {
        return issueTypeMap;
    }

    public void setIssueTypeMap(Map issueTypeMap) {
        this.issueTypeMap = issueTypeMap;
    }

    public Map getIssuecusrelatedtoList() {
        return issuecusrelatedtoList;
    }

    public void setIssuecusrelatedtoList(Map issuecusrelatedtoList) {
        this.issuecusrelatedtoList = issuecusrelatedtoList;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getNotesId() {
        return notesId;
    }

    public void setNotesId(int notesId) {
        this.notesId = notesId;
    }

    /**
     * @return the primaryAssignToLoginIdforOthers
     */
    public String getPrimaryAssignToLoginIdforOthers() {
        return primaryAssignToLoginIdforOthers;
    }

    /**
     * @param primaryAssignToLoginIdforOthers the primaryAssignToLoginIdforOthers to set
     */
    public void setPrimaryAssignToLoginIdforOthers(String primaryAssignToLoginIdforOthers) {
        this.primaryAssignToLoginIdforOthers = primaryAssignToLoginIdforOthers;
    }

    /**
     * @return the primaryAssignToforOthers
     */
    public String getPrimaryAssignToforOthers() {
        return primaryAssignToforOthers;
    }

    /**
     * @param primaryAssignToforOthers the primaryAssignToforOthers to set
     */
    public void setPrimaryAssignToforOthers(String primaryAssignToforOthers) {
        this.primaryAssignToforOthers = primaryAssignToforOthers;
    }

    /**
     * @return the priorityOthers
     */
    public String getPriorityOthers() {
        return priorityOthers;
    }

    /**
     * @param priorityOthers the priorityOthers to set
     */
    public void setPriorityOthers(String priorityOthers) {
        this.priorityOthers = priorityOthers;
    }

    /**
     * @return the issueTypeOthers
     */
    public String getIssueTypeOthers() {
        return issueTypeOthers;
    }

    /**
     * @param issueTypeOthers the issueTypeOthers to set
     */
    public void setIssueTypeOthers(String issueTypeOthers) {
        this.issueTypeOthers = issueTypeOthers;
    }

    /**
     * @return the titleOthers
     */
    public String getTitleOthers() {
        return titleOthers;
    }

    /**
     * @param titleOthers the titleOthers to set
     */
    public void setTitleOthers(String titleOthers) {
        this.titleOthers = titleOthers;
    }

    /**
     * @return the resolutionOthers
     */
    public String getResolutionOthers() {
        return resolutionOthers;
    }

    /**
     * @param resolutionOthers the resolutionOthers to set
     */
    public void setResolutionOthers(String resolutionOthers) {
        this.resolutionOthers = resolutionOthers;
    }

    /**
     * @return the commentsOthers
     */
    public String getCommentsOthers() {
        return commentsOthers;
    }

    /**
     * @param commentsOthers the commentsOthers to set
     */
    public void setCommentsOthers(String commentsOthers) {
        this.commentsOthers = commentsOthers;
    }

    /**
     * @return the secondaryAssignToLoginIdForOthers
     */
    public String getSecondaryAssignToLoginIdForOthers() {
        return secondaryAssignToLoginIdForOthers;
    }

    /**
     * @param secondaryAssignToLoginIdForOthers the secondaryAssignToLoginIdForOthers to set
     */
    public void setSecondaryAssignToLoginIdForOthers(String secondaryAssignToLoginIdForOthers) {
        this.secondaryAssignToLoginIdForOthers = secondaryAssignToLoginIdForOthers;
    }

    /**
     * @return the secondaryAssignToOthers
     */
    public String getSecondaryAssignToOthers() {
        return secondaryAssignToOthers;
    }

    /**
     * @param secondaryAssignToOthers the secondaryAssignToOthers to set
     */
    public void setSecondaryAssignToOthers(String secondaryAssignToOthers) {
        this.secondaryAssignToOthers = secondaryAssignToOthers;
    }

    /**
     * @return the durationTotheTask
     */
    public String getDurationTotheTask() {
        return durationTotheTask;
    }

    /**
     * @param durationTotheTask the durationTotheTask to set
     */
    public void setDurationTotheTask(String durationTotheTask) {
        this.durationTotheTask = durationTotheTask;
    }

    /**
     * @return the bridgeCode
     */
    public String getBridgeCode() {
        return bridgeCode;
    }

    /**
     * @param bridgeCode the bridgeCode to set
     */
    public void setBridgeCode(String bridgeCode) {
        this.bridgeCode = bridgeCode;
    }

    /**
     * @return the progress
     */
    public String getProgress() {
        return progress;
    }

    /**
     * @param progress the progress to set
     */
    public void setProgress(String progress) {
        this.progress = progress;
    }

    /**
     * @return the reportsToHierarchyMap
     */
    public Map getReportsToHierarchyMap() {
        return reportsToHierarchyMap;
    }

    /**
     * @param reportsToHierarchyMap the reportsToHierarchyMap to set
     */
    public void setReportsToHierarchyMap(Map reportsToHierarchyMap) {
        this.reportsToHierarchyMap = reportsToHierarchyMap;
    }

    /**
     * @return the taskStartDate
     */
    public String getTaskStartDate() {
        return taskStartDate;
    }

    /**
     * @param taskStartDate the taskStartDate to set
     */
    public void setTaskStartDate(String taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    /**
     * @return the taskEndDate
     */
    public String getTaskEndDate() {
        return taskEndDate;
    }

    /**
     * @param taskEndDate the taskEndDate to set
     */
    public void setTaskEndDate(String taskEndDate) {
        this.taskEndDate = taskEndDate;
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
     * @return the statusPrev
     */
    public String getStatusPrev() {
        return statusPrev;
    }

    /**
     * @param statusPrev the statusPrev to set
     */
    public void setStatusPrev(String statusPrev) {
        this.statusPrev = statusPrev;
    }

 
}
