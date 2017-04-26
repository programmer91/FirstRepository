/*
 * @(#)EmployeeAction.java 1.0 November 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 *
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.employee.genaralgetEmployee
 *
 * Date    :  September 24, 2007, 10:18 PM
 *
 * Author  : Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * File    : EmployeeAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.Reviews;

import com.mss.mirage.crm.attachments.AttachmentService;
import com.mss.mirage.employee.general.EmployeeService;
import com.mss.mirage.employee.general.EmployeeVTO;
import com.mss.mirage.employee.general.StateVTO;
import com.mss.mirage.employee.profile.EmpProfileVTO;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.sql.Timestamp;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

//new
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import java.sql.Blob;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;



/**
 * The <code>EmployeeAction</code>  class is used for getting adding new Activity from
 * <i>ActivityAdd.jsp</i> page.
 * <p>
 * Then it invokes setter methods in <code>EmployeeAction</code> class and invokes execute() method
 * in <code>EmployeeAction</code> for inserting activity details in MIRAGE.tblCrmActivity table.
 *
 * @author Praveen Kumar Ralla<a href="mailto:pralla@miraclesoft.com">pralla@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.util.ServiceLocator
 * @see com.mss.mirage.employee.general.EmployeeVTO
 * @see com.mss.mirage.util.ApplicationDataProvider
 *
 * @since 1.0
 */
public class ReviewsAction extends ActionSupport implements ServletRequestAware{
    
    /** The empId is used for storing employee id. */
    private int empId;
    
    /** The resultType is used for storing type of result. */
    private String resultType;
    
    /** The queryString is used for storing sqlQuery String. */
    private StringBuffer queryStringBuffer;
    
    private String queryString;
    
private HttpServletRequest httpServletRequest;

private int userRoleId;
private String review;
private Map reviewList;
private ReviewVTO currentReview;
private AttachmentService attachmentService;
private String generatedPath;

private String reviewType;
private String notes;

private String attachmentName;
private String attachmentLocation;
private String attachmentFileName;


 private File upload;
 private String fileLocation;
 private String filepath;
 private String objectType;
 
   private String uploadContentType;
    private String uploadFileName;
private int reviewId;
public DataSourceDataProvider dataSourceDataProvider ;

private String createdBy;
private String submitFrom;
private String currentAction;
private String resultMessage;

private Map empnamesList;
private String empnameById;

private String hrComments;
private int reviewFlag;

private String tlComments;
private String reviewStatus;
private String startDate;
private String overlayReviewDate;
private Map searchReviewMap;
        private String endDate;
         private String userId;
          private String employeeName;
        HibernateDataProvider hibernateDataProvider;
        private List departmentIdList;
        private String departmentId;
        private int year;
        private int month;
         private EmployeeVTO currentEmployee;
         private Collection currentStateHistoryCollection;
          private EmployeeService employeeService;
          
          
           private double reviewApprovalPercentage;
    private String reportingManager;
    private int score;
    private int reviewsSubmitted;
    private int reviewsApproved;
    private int reviewsRejected;
    private int reviewsPending;
    private int rank;
    private boolean managerCheck;
     private String reviewTlStatus;
    private String reviewHrStatus;
    public String getTlComments() {
        return tlComments;
    }

    public void setTlComments(String tlComments) {
        this.tlComments = tlComments;
    }

    public String getHrComments() {
        return hrComments;
    }

    public void setHrComments(String hrComments) {
        this.hrComments = hrComments;
    }

    public String getEmpnameById() {
        return empnameById;
    }

    public void setEmpnameById(String empnameById) {
        this.empnameById = empnameById;
    }

    public Map getEmpnamesList() {
        return empnamesList;
    }

    public void setEmpnamesList(Map empnamesList) {
        this.empnamesList = empnamesList;
    }

    

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }
private String reviewName;


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

    public String getGeneratedPath() {
        return generatedPath;
    }

    public void setGeneratedPath(String generatedPath) {
        this.generatedPath = generatedPath;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }


    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    public String getAttachmentLocation() {
        return attachmentLocation;
    }

    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

   

    

   

   
    /** Creates a new instance of EmployeeAction */
    public ReviewsAction() {
    }
    
   
    
   
   
    public String execute(){
        
        return SUCCESS;
    }
 /*   public String getPMOActivity() {
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            System.out.println("role"+userRoleId);
            String objectId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_PMO_ACTIVITY",userRoleId)){
                try{
                    setMyProjects(new HashMap());
                   // setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(objectId)));
                    setMyAccounts(dataSourceDataProvider.getInstance().getMyCrmAccountList(Integer.parseInt(objectId)));
                    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                    
                   // String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                    if(getSubmitFrom()==null){
                        queryString = "SELECT ProjectName,AccountId,StartDate,tblProjectContacts.STATUS,TotalResources,NAME,ProjectId "
                                + "FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON "
                                + "(tblProjectContacts.ProjectId = tblProjects.Id) "
                                + "LEFT OUTER JOIN tblCrmAccount "
                                + "ON (tblCrmAccount.Id = tblProjectContacts.AccountId) "
                                + "WHERE ObjectId = "+empId+" AND IsPMO =1 AND ResourceTitle!=8";
                       
                        // queryString = queryString+" FROM tblEmployee WHERE ReportsTo='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"' AND DeletedFlag != 1 ORDER BY CurStatus,LName";
                        //setSubmitFrom("searchFormMyTeam");
                       // httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY,"empSearchMyTeam");
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY,queryString);
                    }
                   // searchPrepare();
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
       
       
               
       public String pmoActivitySearch() {
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
          //  System.out.println("role"+userRoleId);
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_PMO_ACTIVITY",userRoleId)){
                try{
                    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                    if(getSubmitFrom()==null){
                        queryString = "SELECT ProjectName,StartDate,AccountId,tblProjectContacts.STATUS,TotalResources,NAME,ProjectId FROM tblProjectContacts LEFT OUTER JOIN tblProjects ON (tblProjectContacts.ProjectId = tblProjects.Id) LEFT OUTER JOIN tblCrmAccount ON (tblCrmAccount.Id = tblProjectContacts.AccountId)  ";
                       
                        // queryString = queryString+" FROM tblEmployee WHERE ReportsTo='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"' AND DeletedFlag != 1 ORDER BY CurStatus,LName";
                        //setSubmitFrom("searchFormMyTeam");
                       // httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY,"empSearchMyTeam");
                        
                        //if((getProjectName()!=null && !"".equals(getProjectName())) || (getStatus()!=null && !"".equals(getStatus())) || (getStartDate()!=null && !"".equals(getStartDate())))
                            
                            queryString = queryString + "  WHERE ObjectId = "+empId+" AND IsPMO =1 AND ResourceTitle!=8 ";

                    //    int count = 0;
                                    
                        if(getProjectName()!=null && !"".equals(getProjectName()))
                        {
                            queryString = queryString + "AND ProjectName LIKE '"+getProjectName()+"%' ";
                           // count++;
                        }
                        if(getStatus()!=null && !"".equals(getStatus()))
                        {
                          //  if(count==0)
                          //  queryString = queryString + "Status LIKE '"+getStatus()+"%' ";
                          //  else
                                queryString = queryString + "AND tblProjectContacts.Status LIKE '"+getStatus()+"%' ";
                          //  count++;
                        }
                        
                        if(getStartDate()!=null && !"".equals(getStartDate()))
                        {
                           // if(count==0)
                           // queryString = queryString + "date(StartDate) = '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ";
                           // else
                                queryString = queryString + "AND date(StartDate) = '"+DateUtility.getInstance().convertStringToMySQLDate(getStartDate())+"' ";
                       }
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_PMO_ACTIVITY,queryString);
                    }
                   // searchPrepare();
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }*/
     public String getMyReviewList()
   {
        setResultType(LOGIN);
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL",userRoleId))
            {
        
        try{
            
             setEndDate(DateUtility.getInstance().getCurrentMySqlDate());
               setOverlayReviewDate(DateUtility.getInstance().getCurrentMySqlDate());
                      //  setDateAssigned(DateUtility.getInstance().getCurrentMySqlDate());
               //  setStartDate(DateUtility.getInstance().FirstDateOfLastMonth());
                 setStartDate(DateUtility.getInstance().getDateOfLastYear());
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
        
        Map rolesMap = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
        if(rolesMap.containsValue("Sales")&&rolesMap.containsValue("Recruitment"))
            setReviewList(dataSourceDataProvider.getInstance().getMyReviews(3));// 3 for employee and sales(flag 1 and 4 and 5)
        else if(rolesMap.containsValue("Sales"))  
        setReviewList(dataSourceDataProvider.getInstance().getMyReviews(1));// 1 for employee and sales(flag 1 and 4)
         else if(rolesMap.containsValue("Recruitment"))  
        setReviewList(dataSourceDataProvider.getInstance().getMyReviews(2));// 2 for employee and sales(flag 1 and 5)
        else
              setReviewList(dataSourceDataProvider.getInstance().getMyReviews(0));
        //setSearchReviewMap(dataSourceDataProvider.getInstance().getAllReviewTypes());
        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Operations"))
        setEmpnamesList(dataSourceDataProvider.getInstance().getEmployeeNamesByID((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString())));
       queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewType,EmpId,ReviewTypeId,tblEmpReview.Status,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments,TLComments,ReviewDate,TLRating,HRRating,MaxPoints,ApprovedBy1,Approver1Date,Approver2Date,ApprovedBy2,tblEmpReview.UserId as UserId,tblEmpReview.HrStatus FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.UserId = tblEmployee.LoginId) WHERE 1=1 ";
        
            
         // queryString=queryString+" AND tblEmpReview.EmpId="+empId;  
        queryString=queryString+" AND tblEmpReview.UserId='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString()+"' AND CreatdBy='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString()+"' ";  
            
               
       
      
       queryString= queryString+" AND tblEmpReview.ReviewDate between '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"' AND '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"'";
        
        queryString = queryString+" ORDER BY ReviewDate DESC";
        httpServletRequest.setAttribute(ApplicationConstants.EMP_REVIEWS_LIST, queryString);
                    setResultType(SUCCESS);
        }
        catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    setResultType(ERROR);
                }
        
    }
   }
        return getResultType();
   }
     
     
     public String teamReviewList(){
          setResultType(LOGIN);
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
           String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            int isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
           
            setResultType("accessFailed");
          //  if(AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL",userRoleId))
            if((roleName.equalsIgnoreCase("Employee") &&(isManager==1||isTeamLead==1))||roleName.equalsIgnoreCase("Operations"))
            {
        
        try{
             setOverlayReviewDate(DateUtility.getInstance().getCurrentMySqlDate());
           // setReviewFlag(getReviewFlag());
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
        setSearchReviewMap(dataSourceDataProvider.getInstance().getAllReviewTypes());
        setEndDate(DateUtility.getInstance().getCurrentMySqlDate());
               
                      //  setDateAssigned(DateUtility.getInstance().getCurrentMySqlDate());
               //  setStartDate(DateUtility.getInstance().FirstDateOfLastMonth());
         setStartDate(DateUtility.getInstance().getDateOfLastYear());
        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
      //  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Hr"))
       // setEmpnamesList(dataSourceDataProvider.getInstance().getEmployeeNamesByID((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString())));
      // queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewType,EmpId,ReviewTypeId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments,TLComments,ReviewDate,tblEmpReview.STATUS,Approver1Comments FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.EmpId = tblEmployee.Id) WHERE 1=1 ";
         queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewDate,ReviewType,EmpId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.Status,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments,TLComments,TLRating,HRRating,MaxPoints,ApprovedBy1,Approver1Date,Approver2Date,ApprovedBy2,tblEmpReview.UserId as UserId,HRStatus FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.UserId = tblEmployee.LoginId) WHERE 1=1 ";
       
        
         
         if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Employee")){
      
               setReviewList(dataSourceDataProvider.getInstance().getTeamReviewTypes(3));
           // Map myTeamMemebrs = dataSourceDataProvider.getMyTeamMembersForReview(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString(),httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString());
            Map myTeamMemebrs = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
            //   Map myTeamMemebrs = dataSourceDataProvider.getMyTeamMembersForReview(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString(),httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString());
       //     List myTeamList = dataSourceDataProvider.getListFromMap(myTeamMemebrs);
            String teamList = dataSourceDataProvider.getTeamLoginIdList(myTeamMemebrs);
              setEmpnamesList(myTeamMemebrs);
              
             // System.out.println("setEmpnamesList-->"+getEmpnamesList());
            if(!"".equals(teamList)){
                         queryString=queryString+" AND tblEmpReview.UserId IN("+teamList +")";  
            }else {
                  queryString=queryString+" AND tblEmpReview.UserId IN('')";  
            }
               
       }else if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Operations")){
           setReviewList(dataSourceDataProvider.getInstance().getTeamReviewTypes(2));
        
          // setEmpnamesList(dataSourceDataProvider.getInstance().getEmployeeNamesByOperationsContactId((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString())));
        
          //queryString=queryString+" AND tblEmployee.OpsContactId="+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID); 
          
          
          String teamList = "";
                        Map myTeamMemebrs;
                        // Map myTeamMemebrs = dataSourceDataProvider.getMyTeamMembersForReview(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString(),httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString());
                        Map rolesMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
                        if (rolesMap.containsValue("Admin")) {
                            myTeamMemebrs = dataSourceDataProvider.getAllEmployees();
                            teamList = dataSourceDataProvider.getTeamLoginIdList(myTeamMemebrs);
                            setEmpnamesList(myTeamMemebrs);
                            queryString = queryString + " AND tblEmpReview.UserId IN(" + teamList + ")";
                        } else {
                            setEmpnamesList(dataSourceDataProvider.getInstance().getEmployeeNamesByOperationsContactId((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString())));
                            queryString = queryString + " AND tblEmployee.OpsContactId=" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID);

                        } 

          
          
          
       }
      
       
        queryString= queryString+" AND tblEmpReview.ReviewDate between '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"' AND '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"'";
        
        queryString = queryString+" ORDER BY ReviewDate DESC";
        httpServletRequest.setAttribute(ApplicationConstants.EMP_REVIEWS_LIST, queryString);
                    setResultType(SUCCESS);
        }
        catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    setResultType(ERROR);
                }
        
    }
   }
        return getResultType();
     }
     
     
     
    
  public String getMyReview()
   {
        setResultType(LOGIN);
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL",userRoleId))
            {
        
        try{
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
    //    setReviewList(dataSourceDataProvider.getInstance().getMyReviews());
         Map rolesMap = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
        if(rolesMap.containsValue("Sales"))
        setReviewList(dataSourceDataProvider.getInstance().getMyReviews(1));// 1 for employee and sales(flag 1 and 4)
        else
              setReviewList(dataSourceDataProvider.getInstance().getMyReviews(0));
        setCurrentAction("doAddReview");
                    setResultType(SUCCESS);
        }
        catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    setResultType(ERROR);
                }
        
    }
   }
        return getResultType();
   }
  
  public String getInsertReview(){
      
        setResultType(LOGIN);
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            setEmpId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));
            setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
          //  setEmpId(empId);
            setResultType("accessFailed");
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL",userRoleId)){
                try{
                    if(getSubmitFrom()==null){
                        
                           attachmentService = ServiceLocator.getAttachmentService();
                //  setCurrentTask(tasksVTO);
                generatedPath = null;
                //   setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
               
                //  setReviewList(dataSourceDataProvider.getInstance().getMyReviews());
                 Map rolesMap = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
        if(rolesMap.containsValue("Sales"))
        setReviewList(dataSourceDataProvider.getInstance().getMyReviews(1));// 1 for employee and sales(flag 1 and 4)
        else
              setReviewList(dataSourceDataProvider.getInstance().getMyReviews(0));
                 
                
                if (getUploadFileName() != null) {
                    generatedPath = attachmentService.generatePath(Properties.getProperty("Attachments.Path"), "Emp Reviews");
                    File targetDirectory = new File(generatedPath + Properties.getProperty("OS.Compatabliliy.Download") + getUploadFileName());
                    setAttachmentLocation(targetDirectory.toString());
                    FileUtils.copyFile(getUpload(), targetDirectory);
                    setObjectType("Emp Reviews");
                } else {
                    this.objectType = "NoFile";
                    this.setAttachmentLocation("");
                    this.setFilepath("");
                    this.attachmentName = "";
                }
                            System.out.println("filename"+getAttachmentName());
                            System.out.println("filename"+getAttachmentLocation());
                            System.out.println("filename"+getUploadFileName());
                          if( !ServiceLocator.getReviewsService().getInsertReview(this)){
                              setResultMessage("<font color=green size=2px>Review added successfully.</font>");
                          }else {
                              setResultMessage("<font color=red size=2px>Please try again.</font>");
                          }
                          
          //  ServiceLocator.getReviewsService().addAttachmentLocation(this);
                httpServletRequest.getSession(false).setAttribute("resultMsg", "Task Attachment uploaded successfully");
                // System.out.println("After insertion");
                        setResultType(SUCCESS);
                           
                           
                      
                       
                    }
                   
                    setResultType(SUCCESS);
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                     setResultMessage("<font color=red size=2px>"+ex.getMessage()+"</font>");
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    setResultType(ERROR);
                }
            }//END-Authorization Checking
        }//Close Session Checking
        httpServletRequest.setAttribute("resultMessage", getResultMessage());
        return getResultType();
  
  }
  /*
   public String getEditReview()
   {
        setResultType(LOGIN);
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL",userRoleId))
            {
        
        try{
            setReviewFlag(getReviewFlag());
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
        setCurrentAction("doEditReview");
        setReviewList(dataSourceDataProvider.getInstance().getMyReviews());
        setCurrentReview(ServiceLocator.getReviewsService().getReview(getReviewId()));
            
                    setResultType(SUCCESS);
        }
        catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    setResultType(ERROR);
                }
        
    }
   }
        return getResultType();
   }
    
      public String doEditReview()
   {
        setResultType(LOGIN);
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL",userRoleId))
            {
        
        try{
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
        setCurrentAction("doEditReview");
        setReviewList(dataSourceDataProvider.getInstance().getMyReviews());
        setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
        //setCurrentReview(ServiceLocator.getReviewsService().getReview(getReviewId()));
            // ServiceLocator.getReviewsService().doEditReview(this); 
              setReviewFlag(getReviewFlag());
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Hr")){
        setEmpnamesList(dataSourceDataProvider.getInstance().getEmployeeNamesByID((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString())));
        }else if(getReviewFlag()==1){
                 Map myTeamMemebrs = dataSourceDataProvider.getMyTeamMembersForReview(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString(),httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString());
            List myTeamList = dataSourceDataProvider.getListFromMap(myTeamMemebrs);
              setEmpnamesList(myTeamMemebrs);
        }
        if( !ServiceLocator.getReviewsService().doEditReview(this)){
                              setResultMessage("<font color=green size=2px>Review Updated successfully.</font>");
                          }else {
                              setResultMessage("<font color=red size=2px>Please try again.</font>");
                          }
                    setResultType(SUCCESS);
        }
        catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    setResultType(ERROR);
                }
        
    }
   }
        return getResultType();
   }
      
      */
      public String doReviewSearch() {
           setResultType(LOGIN);
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL",userRoleId))
            {
        
        try{
            setReviewFlag(getReviewFlag());
            setOverlayReviewDate(DateUtility.getInstance().getCurrentMySqlDate());
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
      //  setReviewList(dataSourceDataProvider.getInstance().getMyReviews());
         Map rolesMap = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
       if(rolesMap.containsValue("Sales")&&rolesMap.containsValue("Recruitment"))
            setReviewList(dataSourceDataProvider.getInstance().getMyReviews(3));// 3 for employee and sales(flag 1 and 4 and 5)
        else if(rolesMap.containsValue("Sales"))  
        setReviewList(dataSourceDataProvider.getInstance().getMyReviews(1));// 1 for employee and sales(flag 1 and 4)
         else if(rolesMap.containsValue("Recruitment"))  
        setReviewList(dataSourceDataProvider.getInstance().getMyReviews(2));// 2 for employee and sales(flag 1 and 5)
        else
              setReviewList(dataSourceDataProvider.getInstance().getMyReviews(0));
      //  setSearchReviewMap(dataSourceDataProvider.getInstance().getAllReviewTypes());
        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
      //  queryString="SELECT ReviewType,EmpId,ReviewTypeId,EmpComments,CreatdBy,CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id as Id FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) where EmpId= "+empId ;
         queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewDate,ReviewType,EmpId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.Status,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments,TLComments,TLRating,HRRating,MaxPoints,ApprovedBy1,Approver1Date,Approver2Date,ApprovedBy2,tblEmpReview.UserId as UserId,tblEmpReview.HrStatus FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.UserId = tblEmployee.LoginId) WHERE 1=1 ";
    //    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Employee")&&getReviewFlag()==0){
           
         //   queryString=queryString+" AND tblEmpReview.UserId='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString()+"'";   
         queryString=queryString+" AND tblEmpReview.UserId='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString()+"' AND CreatdBy='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString()+"' ";  
      // }
    
         if(! getStartDate().equals("") && !getEndDate().equals("") )   {
                        queryString= queryString+" AND tblEmpReview.ReviewDate between '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"' AND '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"'";
                       
                            } 
        if(!"".equals(getReviewType()) && getReviewType()!=null){
         queryString = queryString +" AND ReviewTypeId="+ getReviewType();  
        }
         /*if(!"".equals(getReviewStatus()) && getReviewStatus()!=null){
         queryString = queryString +" AND tblEmpReview.Status='"+ getReviewStatus()+"'";  
        }*/
        if(!"".equals(getReviewTlStatus()) && getReviewTlStatus()!=null){
         queryString = queryString +" AND tblEmpReview.Status='"+ getReviewTlStatus()+"'";  
        }
          if(!"".equals(getReviewHrStatus()) && getReviewHrStatus()!=null){
         queryString = queryString +" AND tblEmpReview.HrStatus='"+ getReviewHrStatus()+"'";  
        }

          if(!"".equals(getReviewName()) && getReviewName()!=null){
         queryString = queryString +" AND ReviewName like '%"+ getReviewName().trim()+"%'";  
        }  
                
      
        queryString = queryString+" ORDER BY ReviewDate DESC";
        httpServletRequest.setAttribute(ApplicationConstants.EMP_REVIEWS_LIST, queryString);
                    setResultType(SUCCESS);
        }
        catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    setResultType(ERROR);
                }
        
    }
   }
        return getResultType();
          
      
      }
    public String doTeamReviewSearch() {
           setResultType(LOGIN);
        // System.out.println("in team reviews list");
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
          String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            int isManager = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString());
            int isTeamLead = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString());
           
            setResultType("accessFailed");
          //  if(AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL",userRoleId))
            if((roleName.equalsIgnoreCase("Employee") &&(isManager==1||isTeamLead==1))||roleName.equalsIgnoreCase("Operations"))
            {
        
        try{
           // System.out.println("in team reviews list");
            //setReviewFlag(getReviewFlag());
             setOverlayReviewDate(DateUtility.getInstance().getCurrentMySqlDate());
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
       // setReviewList(dataSourceDataProvider.getInstance().getMyReviews());
         Map rolesMap = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
        if(rolesMap.containsValue("Sales"))
        setReviewList(dataSourceDataProvider.getInstance().getMyReviews(1));// 1 for employee and sales(flag 1 and 4)
        else
              setReviewList(dataSourceDataProvider.getInstance().getMyReviews(0));
        
        setSearchReviewMap(dataSourceDataProvider.getInstance().getAllReviewTypes());
        empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
      //  queryString="SELECT ReviewType,EmpId,ReviewTypeId,EmpComments,CreatdBy,CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id as Id FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) where EmpId= "+empId ;
      //   queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewDate,ReviewType,EmpId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.Status,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments,Approver1Comments FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.EmpId = tblEmployee.Id) WHERE 1=1 ";
         queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewDate,ReviewType,EmpId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.Status,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments,TLComments,TLRating,HRRating,MaxPoints,ApprovedBy1,Approver1Date,Approver2Date,ApprovedBy2,tblEmpReview.UserId as UserId,HRStatus FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.UserId = tblEmployee.LoginId) WHERE 1=1 ";

 
           //   String teamList = dataSourceDataProvider.getTeamLoginIdList(myTeamMemebrs);
             
                   if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Employee")){
                  setReviewList(dataSourceDataProvider.getInstance().getTeamReviewTypes(3));
                       Map myTeamMemebrs = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP);
    String teamList = dataSourceDataProvider.getTeamLoginIdList(myTeamMemebrs);
             setEmpnamesList(myTeamMemebrs);
                        if(getEmpnameById().equals("-1")){
                       if(!"".equals(teamList)){
                         queryString=queryString+" AND tblEmpReview.UserId IN("+teamList +")";  
            }else {
                  queryString=queryString+" AND tblEmpReview.UserId IN('')";  
            }
                   }else {
                  queryString=queryString+" AND tblEmpReview.UserId IN('"+getEmpnameById() +"')";  
              }
                   }else{
                       setReviewList(dataSourceDataProvider.getInstance().getTeamReviewTypes(2));
//                       setEmpnamesList(dataSourceDataProvider.getInstance().getEmployeeNamesByOperationsContactId((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString())));
//                      if(getEmpnameById().equals("-1")){
//                      queryString=queryString+" AND tblEmployee.OpsContactId="+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID); 
//                        }else{
//                            queryString=queryString+" AND tblEmpReview.UserId IN('"+getEmpnameById() +"')";  
//                        }
                       String teamList = "";
                        Map myTeamMemebrs;
                        // Map myTeamMemebrs = dataSourceDataProvider.getMyTeamMembersForReview(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString(),httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString());
                        if (rolesMap.containsValue("Admin")) {
                            myTeamMemebrs = dataSourceDataProvider.getAllEmployees();
                            teamList = dataSourceDataProvider.getTeamLoginIdList(myTeamMemebrs);
                            setEmpnamesList(myTeamMemebrs);
                             if (getEmpnameById().equals("-1")) {
                            queryString = queryString + " AND tblEmpReview.UserId IN(" + teamList + ")";
                             } else {
                            queryString = queryString + " AND tblEmpReview.UserId IN('" + getEmpnameById() + "')";
                        }
                        }else{
                         setEmpnamesList(dataSourceDataProvider.getInstance().getEmployeeNamesByOperationsContactId((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString())));
                       
                        // setEmpnamesList(dataSourceDataProvider.getInstance().getEmployeeNamesByWorkingCountry((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString())));
                        if (getEmpnameById().equals("-1")) {
                            //  queryString = queryString+" AND  WorkingCountry LIKE '"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString()+"'";
                            queryString = queryString + " AND tblEmployee.OpsContactId=" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID);
                        } else {
                            queryString = queryString + " AND tblEmpReview.UserId IN('" + getEmpnameById() + "')";
                        }
                        }

                       
                       
                       
                   } 
                  
        
         if(! getStartDate().equals("") && !getEndDate().equals("") )   {
                        queryString= queryString+" AND tblEmpReview.ReviewDate between '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"' AND '"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"'";
                       
                            } 
        if(!"".equals(getReviewType()) && getReviewType()!=null){
         queryString = queryString +" AND ReviewTypeId="+ getReviewType();  
        }
      /*   if(!"".equals(getReviewStatus()) && getReviewStatus()!=null){
         queryString = queryString +" AND tblEmpReview.Status='"+ getReviewStatus()+"'";  
        }*/
        if(!"".equals(getReviewTlStatus()) && getReviewTlStatus()!=null){
         queryString = queryString +" AND tblEmpReview.Status='"+ getReviewTlStatus()+"'";  
        }
          if(!"".equals(getReviewHrStatus()) && getReviewHrStatus()!=null){
         queryString = queryString +" AND tblEmpReview.HrStatus='"+ getReviewHrStatus()+"'";  
        }

          if(!"".equals(getReviewName()) && getReviewName()!=null){
         queryString = queryString +" AND ReviewName like '%"+ getReviewName().trim()+"%'";  
        }  
              /* if(!"-1".equals(getEmpnameById())){
                 int empId=  DataSourceDataProvider.getInstance().getEmpIdByLoginId(getEmpnameById());
         queryString = queryString +" AND tblEmpReview.EmpId="+ empId;  
        }    */
      /*  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Hr")){
              if(getEmpnameById()!=-1){
         queryString = queryString +" AND tblEmpReview.EmpId="+ getEmpnameById();  
        }
        }*/
        queryString = queryString+" ORDER BY ReviewDate DESC";
        httpServletRequest.setAttribute(ApplicationConstants.EMP_REVIEWS_LIST, queryString);
                    setResultType(SUCCESS);
        }
        catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    setResultType(ERROR);
                }
        
    }
   }
        return getResultType();
          
      
      } 
    
    
     public String deleteReview()
   {
        setResultType(LOGIN);
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL",userRoleId))
            {
        
        try{
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
        setCurrentAction("doEditReview");
      //  setReviewList(dataSourceDataProvider.getInstance().getMyReviews());
         Map rolesMap = (Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_ROLES);
        if(rolesMap.containsValue("Sales"))
        setReviewList(dataSourceDataProvider.getInstance().getMyReviews(1));// 1 for employee and sales(flag 1 and 4)
        else
              setReviewList(dataSourceDataProvider.getInstance().getMyReviews(0));
        setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
        //setCurrentReview(ServiceLocator.getReviewsService().getReview(getReviewId()));
            // ServiceLocator.getReviewsService().deleteReview(getReviewId()); 
        setSearchReviewMap(dataSourceDataProvider.getInstance().getAllReviewTypes());
        boolean isDeleted=ServiceLocator.getReviewsService().deleteReview(getReviewId());
        //System.out.println("isDeleted-->"+isDeleted);
              if( !isDeleted){
                              setResultMessage("<font color=green size=2px>Review deleted successfully.</font>");
                          }else {
                              setResultMessage("<font color=red size=2px>Please try again.</font>");
                          }
                    setResultType(SUCCESS);
        }
        catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                     setResultMessage("<font color=red size=2px>"+ex.getMessage()+"</font>");
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    setResultType(ERROR);
                }
        
    }
   }
        httpServletRequest.setAttribute("resultMessage", getResultMessage());
        return getResultType();
   }
     
    
 public String getAppraisalsList()
   {
        setResultType(LOGIN);
         
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL",userRoleId))
            {
        
        try{
            hibernateDataProvider = HibernateDataProvider.getInstance();
            
            setYear(Calendar.getInstance().get(Calendar.YEAR));
            setMonth(Calendar.getInstance().get(Calendar.MONTH)+1);
            
            
            setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
          //  queryString = "SELECT tblEmployee.Id ,CONCAT(FName,' ',LName) AS EmpName,Email1,COUNT(tblEmpReview.Id) AS ReviwCount,LoginId  FROM tblEmployee LEFT OUTER JOIN tblEmpReview ON (tblEmployee.LoginId=tblEmpReview.UserId) WHERE YEAR(NextRevisedDate) = YEAR(CURDATE()) AND MONTH(NextRevisedDate) =  MONTH(CURDATE()) AND tblEmpReview.Status IN ('Approved','Denied')";
            //  queryString = "SELECT tblEmployee.Id ,CONCAT(FName,' ',LName) AS EmpName,Email1,COUNT(tblEmpReview.Id) AS ReviwCount,LoginId  FROM tblEmployee LEFT OUTER JOIN tblEmpReview ON (tblEmployee.LoginId=tblEmpReview.UserId) WHERE YEAR(NextRevisedDate) = YEAR(CURDATE()) AND MONTH(NextRevisedDate) =  MONTH(CURDATE()) ";
           // setSearchReviewMap(dataSourceDataProvider.getInstance().getAllReviewTypes());
        //  setEmpnamesList(dataSourceDataProvider.getInstance().getEmployeeNamesByWorkingCountry((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString())));
              setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
            queryString = "SELECT * FROM vwEmpReviews WHERE YEAR(NextRevisedDate) = YEAR(CURDATE()) AND MONTH(NextRevisedDate) =  MONTH(CURDATE()) ";
            queryString = queryString+" AND  Country LIKE '"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY).toString()+"'";
           
           queryString =queryString+" ORDER BY EmpName ASC";
       httpServletRequest.setAttribute(ApplicationConstants.EMP_REVIEWS_LIST, queryString);
                    setResultType(SUCCESS);
        }
        catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    setResultType(ERROR);
                }
        
    }
   }
        return getResultType();
   }
       public String getEmpReviewsList()
   {
        setResultType(LOGIN);
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL",userRoleId))
            {
        
        try{
            setUserId(getUserId());
            setEmployeeName(DataSourceDataProvider.getInstance().getFname_Lname(getUserId()));
             setSearchReviewMap(dataSourceDataProvider.getInstance().getAllReviewTypes());
              employeeService = ServiceLocator.getEmployeeService();
              setCurrentStateHistoryCollection(employeeService.getStateHistoryCollection(getUserId(),15));
             
             
                    setResultType(SUCCESS);
        }
        catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    setResultType(ERROR);
                }
        
    }
   }
        return getResultType();
   } 
      public String doEmployeeRevisedSearch()
   {
        setResultType(LOGIN);
         
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL",userRoleId))
            {
        
        try{
            hibernateDataProvider = HibernateDataProvider.getInstance();
            
            setYear(getYear());
            setMonth(getMonth());
            
            //setEmpnamesList(dataSourceDataProvider.getInstance().getEmployeeNamesByWorkingCountry((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString())));
             setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
            setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
          //  queryString = "SELECT tblEmployee.Id ,CONCAT(FName,' ',LName) AS EmpName,Email1,COUNT(tblEmpReview.Id) AS ReviwCount,LoginId  FROM tblEmployee LEFT OUTER JOIN tblEmpReview ON (tblEmployee.LoginId=tblEmpReview.UserId) WHERE  tblEmpReview.Status IN ('Approved','Denied')";
               queryString = "SELECT * FROM vwEmpReviews WHERE  ";
            queryString = queryString+" Country LIKE '"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY).toString()+"'";
           
           if(getMonth()!=0) {
            queryString = queryString+" AND MONTH(NextRevisedDate) ="+getMonth();
           }
           if(getYear()!=0){
                queryString = queryString+" AND YEAR(NextRevisedDate) ="+getYear();
           }
           
           if(!"".equals(getDepartmentId())){
                queryString = queryString+" AND DepartmentId ='"+getDepartmentId()+"' ";
           }
            if(!"-1".equals(getEmpnameById())){
                queryString = queryString+" AND UserId ='"+getEmpnameById()+"' ";
           }
         //  queryString = queryString+" GROUP BY tblEmployee.Id";
              queryString =queryString+" ORDER BY EmpName ASC";
            //MONTH(NextRevisedDate) =  MONTH(CURDATE()) AND
            
       
       httpServletRequest.setAttribute(ApplicationConstants.EMP_REVIEWS_LIST, queryString);
                    setResultType(SUCCESS);
        }
        catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    setResultType(ERROR);
                }
        
    }
   }
        return getResultType();
   }
      
      
/*
       * New methods for performance dashboard start 
       * 
       */
      
 public String perfDashboard() {
        setResultType(LOGIN);

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL", userRoleId)) {

                try {

                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    setEndDate(DateUtility.getInstance().getCurrentMySqlDate());
                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                    //  setDateAssigned(DateUtility.getInstance().getCurrentMySqlDate());
                    //  setStartDate(DateUtility.getInstance().FirstDateOfLastMonth());
                    setStartDate(DateUtility.getInstance().getDateOfLastYear());

                    setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.COMPETITION_BOARD_LIST) != null) {
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.COMPETITION_BOARD_LIST);
                    }
                    setResultType(SUCCESS);
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    setResultType(ERROR);
                }

            }
        }
        return getResultType();
    }
	
	 public String getCompetitionBoard() {
        setResultType(LOGIN);

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL", userRoleId)) {

                try {

                    hibernateDataProvider = HibernateDataProvider.getInstance();
                  //  setEndDate(DateUtility.getInstance().getCurrentMySqlDate());
                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                    setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                    //  setDateAssigned(DateUtility.getInstance().getCurrentMySqlDate());
                    //  setStartDate(DateUtility.getInstance().FirstDateOfLastMonth());
                 //   setStartDate(DateUtility.getInstance().getDateOfLastYear());
                    List competitionList = ServiceLocator.getReviewsService().getCompetitionBoard(getEmployeeName(), getDepartmentId(),getStartDate(),getEndDate());

                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.COMPETITION_BOARD_LIST) != null) {
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.COMPETITION_BOARD_LIST);
                    }

                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.COMPETITION_BOARD_LIST, competitionList);

                    //EMP_REVIEWS_LIST
                    setResultType(SUCCESS);
                     String roleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                // System.out.println("roleName--------"+roleName);
                 if(roleName.equalsIgnoreCase("Employee")){
                     if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString().equals("1") || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString().equals("1")){
                       setManagerCheck(true);
                  }
                  else{
                      setManagerCheck(false);
                  }
                     String loginId= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
//                                 String  responseString = ServiceLocator.getAjaxHandlerService().getEmployeeReviewDetails(getStartDate(),getEndDate(),loginId,false);
//                   //document.getElementById("rank").innerHTML=resText.split("#^$")[0];
//                    System.out.println("responseString--->");
//                    String test[]=responseString.split(Pattern.quote("#^$"));
//                    System.out.println("lenth---"+test.length);
//                  String result[]=responseString.split(Pattern.quote("#^$"));
//                  for(int i=0;i<result.length;i++){
//                      System.out.println(i+"--------"+result[i]);
//                  }
//                   setRank(Integer.parseInt(result[0]));
//                   setScore(Integer.parseInt(result[1]));
//                   setReportingManager(result[2]);
//                   setReviewsSubmited(Integer.parseInt(result[3]));
//                   setReviewsApproved(Integer.parseInt(result[4]));
//                   setReviewApprovalPercentage(Double.parseDouble(result[5]));
//                   setReviewsRejected(Integer.parseInt(result[6]));
//                   setReviewsPending(Integer.parseInt(result[7]));
      
                     setResultType("esuccess");
                 }
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    setResultType(ERROR);
                }

            }
        }
        return getResultType();
    }

	 public String employeePerfDashboard() {
        setResultType(LOGIN);

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            if (AuthorizationManager.getInstance().isAuthorizedUser("GET_EMP_SEARCH_ALL", userRoleId)) {

                try {

                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    setEndDate(DateUtility.getInstance().getCurrentMySqlDate());
                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                    //  setDateAssigned(DateUtility.getInstance().getCurrentMySqlDate());
                    //  setStartDate(DateUtility.getInstance().FirstDateOfLastMonth());
                    setStartDate(DateUtility.getInstance().getDateOfLastYear());

                    setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.COMPETITION_BOARD_LIST) != null) {
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.COMPETITION_BOARD_LIST);
                    }
                    String loginId= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString().equals("1") || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString().equals("1")){
                       setManagerCheck(true);
                  }
                  else{
                      setManagerCheck(false);
                  }
//                  String  responseString = ServiceLocator.getAjaxHandlerService().getEmployeeReviewDetails(getStartDate(),getEndDate(),loginId,false);
//                   //document.getElementById("rank").innerHTML=resText.split("#^$")[0];
//                    System.out.println("responseString--->");
//                    String test[]=responseString.split(Pattern.quote("#^$"));
//                    System.out.println("lenth---"+test.length);
//                  String result[]=responseString.split(Pattern.quote("#^$"));
//                  for(int i=0;i<result.length;i++){
//                      System.out.println(i+"--------"+result[i]);
//                  }
//                   setRank(Integer.parseInt(result[0]));
//                   setScore(Integer.parseInt(result[1]));
//                   setReportingManager(result[2]);
//                   setReviewsSubmited(Integer.parseInt(result[3]));
//                   setReviewsApproved(Integer.parseInt(result[4]));
//                   setReviewApprovalPercentage(Double.parseDouble(result[5]));
//                   setReviewsRejected(Integer.parseInt(result[6]));
//                   setReviewsPending(Integer.parseInt(result[7]));
//      
                  setReviewApprovalPercentage(reviewApprovalPercentage);
                  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_USER_MANAGER).toString().equals("1") || httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_IS_TEAM_LEAD).toString().equals("1")){
                       setManagerCheck(true);
                  }
                  else{
                      setManagerCheck(false);
                  }
                    setResultType(SUCCESS);
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    setResultType(ERROR);
                }

            }
        }
        return getResultType();
    }

      
      /*
       * New methods for performance dashboard end 
       * 
       */
      
     public Map getReviewList() {
        return reviewList;
    }

    public void setReviewList(Map reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
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
     * @return the submitFrom
     */
    public String getSubmitFrom() {
        return submitFrom;
    }

    /**
     * @param submitFrom the submitFrom to set
     */
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }

    /**
     * @return the empId
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * @return the resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * @param resultType the resultType to set
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    /**
     * @return the review
     */
    public String getReview() {
        return review;
    }

    /**
     * @param review the review to set
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * @return the currentReview
     */
    public ReviewVTO getCurrentReview() {
        return currentReview;
    }

    /**
     * @param currentReview the currentReview to set
     */
    public void setCurrentReview(ReviewVTO currentReview) {
        this.currentReview = currentReview;
    }

    /**
     * @return the reviewId
     */
    public int getReviewId() {
        return reviewId;
    }

    /**
     * @param reviewId the reviewId to set
     */
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
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
     * @return the reviewFlag
     */
    public int getReviewFlag() {
        return reviewFlag;
    }

    /**
     * @param reviewFlag the reviewFlag to set
     */
    public void setReviewFlag(int reviewFlag) {
        this.reviewFlag = reviewFlag;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getOverlayReviewDate() {
        return overlayReviewDate;
    }

    public void setOverlayReviewDate(String overlayReviewDate) {
        this.overlayReviewDate = overlayReviewDate;
    }

    /**
     * @return the searchReviewMap
     */
    public Map getSearchReviewMap() {
        return searchReviewMap;
    }

    /**
     * @param searchReviewMap the searchReviewMap to set
     */
    public void setSearchReviewMap(Map searchReviewMap) {
        this.searchReviewMap = searchReviewMap;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the departmentIdList
     */
    public List getDepartmentIdList() {
        return departmentIdList;
    }

    /**
     * @param departmentIdList the departmentIdList to set
     */
    public void setDepartmentIdList(List departmentIdList) {
        this.departmentIdList = departmentIdList;
    }

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the currentEmployee
     */
    public EmployeeVTO getCurrentEmployee() {
        return currentEmployee;
    }

    /**
     * @param currentEmployee the currentEmployee to set
     */
    public void setCurrentEmployee(EmployeeVTO currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

    /**
     * @return the currentStateHistoryCollection
     */
    public Collection getCurrentStateHistoryCollection() {
        return currentStateHistoryCollection;
    }

    /**
     * @param currentStateHistoryCollection the currentStateHistoryCollection to set
     */
    public void setCurrentStateHistoryCollection(Collection currentStateHistoryCollection) {
        this.currentStateHistoryCollection = currentStateHistoryCollection;
    }

    /**
     * @return the reviewApprovalPercentage
     */
    public double getReviewApprovalPercentage() {
        return reviewApprovalPercentage;
    }

    /**
     * @param reviewApprovalPercentage the reviewApprovalPercentage to set
     */
    public void setReviewApprovalPercentage(double reviewApprovalPercentage) {
        this.reviewApprovalPercentage = reviewApprovalPercentage;
    }

    /**
     * @return the reportingManager
     */
    public String getReportingManager() {
        return reportingManager;
    }

    /**
     * @param reportingManager the reportingManager to set
     */
    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the reviewsSubmitted
     */
    public int getReviewsSubmitted() {
        return reviewsSubmitted;
    }

    /**
     * @param reviewsSubmitted the reviewsSubmitted to set
     */
    public void setReviewsSubmitted(int reviewsSubmitted) {
        this.reviewsSubmitted = reviewsSubmitted;
    }

    /**
     * @return the reviewsApproved
     */
    public int getReviewsApproved() {
        return reviewsApproved;
    }

    /**
     * @param reviewsApproved the reviewsApproved to set
     */
    public void setReviewsApproved(int reviewsApproved) {
        this.reviewsApproved = reviewsApproved;
    }

    /**
     * @return the reviewsRejected
     */
    public int getReviewsRejected() {
        return reviewsRejected;
    }

    /**
     * @param reviewsRejected the reviewsRejected to set
     */
    public void setReviewsRejected(int reviewsRejected) {
        this.reviewsRejected = reviewsRejected;
    }

    /**
     * @return the reviewsPending
     */
    public int getReviewsPending() {
        return reviewsPending;
    }

    /**
     * @param reviewsPending the reviewsPending to set
     */
    public void setReviewsPending(int reviewsPending) {
        this.reviewsPending = reviewsPending;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * @return the managerCheck
     */
    public boolean isManagerCheck() {
        return managerCheck;
    }

    /**
     * @param managerCheck the managerCheck to set
     */
    public void setManagerCheck(boolean managerCheck) {
        this.managerCheck = managerCheck;
    }

    /**
     * @return the reviewTlStatus
     */
    public String getReviewTlStatus() {
        return reviewTlStatus;
    }

    /**
     * @param reviewTlStatus the reviewTlStatus to set
     */
    public void setReviewTlStatus(String reviewTlStatus) {
        this.reviewTlStatus = reviewTlStatus;
    }

    /**
     * @return the reviewHrStatus
     */
    public String getReviewHrStatus() {
        return reviewHrStatus;
    }

    /**
     * @param reviewHrStatus the reviewHrStatus to set
     */
    public void setReviewHrStatus(String reviewHrStatus) {
        this.reviewHrStatus = reviewHrStatus;
    }

   
    
}
