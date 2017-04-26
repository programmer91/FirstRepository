/*
 * LeaveAction.java
 *
 * Created on January 22, 2008, 12:31 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.leaverequest;

import com.mss.mirage.crm.attachments.AttachmentService;
import com.mss.mirage.util.*;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
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
import com.mss.mirage.util.ServiceLocator;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
/**
 *
 * @author miracle
 */
public class LeaveAction extends ActionSupport implements ServletRequestAware {
    
    /** Creates a new instance of LeaveAction */
    private String resultMessage;
    private String resultType;
    private HttpServletRequest httpServletRequest;
    private String currentAction;
    private String leaveRequiredFrom;
    private String leaveRequiredTo;
    private LeaveService leaveService;
    private String reason;
    private String status;
    private int empId;
    private String operationType;
    private String reportsTo;
    private DataSourceDataProvider dataSourceDataProvider;
    private String levelReportsTo;
    private LeaveVTO currentLeave;
    private int id;
    private String dateWithOutTime;
    private String downLevelReportsTo;
    private String oneLevelReportsTo;
    private String oneLevelreportsToId;
    private String leaveType;
    private String statusId;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private String submitFrom;
    private String departmentId;
    private String orgId;
    
    private String empLeaveLoginId = null;
    private String empLeaveName = null;
    private String finalChar = null;
    
    private String empOrganisation;
    private String mailCc = null;
    
    private int leaveId = 0;
    
    private String empName;
    private String leaveStatus;
    /**
     *  Populates user required options to the Screens depending on the  options.
     *
     *
     * @return  The  Result type is returned after complete the code of prepare
     * Method.
     *
     *
     */
    
    
    public String execute() throws Exception {
          resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        try{
        this.setCurrentAction("addLeave");
        String[] values = new String[2];
        
        if(currentAction.equalsIgnoreCase("addLeave")) {
            this.setOperationType("request");
            
        }
        
        this.setEmpId(Integer.parseInt( (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID)));
        String userId = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
        
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
        
        
        this.setLevelReportsTo(dataSourceDataProvider.getReportsTo(userId));
        this.setOneLevelReportsTo(dataSourceDataProvider.getReportsTOOneLevel(userId));
        //this.setEmpName(DataSourceDataProvider.getInstance().getEmpNameByLeaveId(getId()).toString());
        this.setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
        values = dataSourceDataProvider.getDepartmentWithOrgId(userId);
        this.setDepartmentId(values[0]);
        
        this.setOrgId(values[1]);
        
        String dueDate ="";
        DateUtility date = DateUtility.getInstance();
        dueDate = date.sqlTimeStampTousTimeStamp(date.getCurrentMySqlDateTime().toString());
        dateWithOutTime = dueDate.substring(0,dueDate.length());
        resultType = "success";
        
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            //httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            httpServletRequest.getSession(false).setAttribute("errorMessage","You don't have reporting person please contact operation team.");
            resultType =  "error";
        }
        }
        return resultType;
    }
    
    
    
    public String getVacationList() throws Exception{
          resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        resultType= SUCCESS;
        }
        return resultType;
    }
    
    
    /**
     * This method will  set downlevelReport to NoSerach and return success to the leave Approval Action
     *
     *
     *
     */
    
    
    
public String getLeaveApprovalList() throws Exception{
           resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         try{
        //Map myTeamManagersMap = new TreeMap();
        String userId = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("select tblEmpLeaves.Id ");
        stringBuffer.append("AS LeaveId,");
        stringBuffer.append("tblEmpLeaves.Status AS Status,");
        stringBuffer.append("tblEmpLeaves.EmpId AS EmpId, ");
        stringBuffer.append("concat(tblEmployee.FName,'.',tblEmployee.LName ) ");
        stringBuffer.append("AS EmpName,");
        stringBuffer.append("tblEmpLeaves.StartDate AS StartDate,");
        stringBuffer.append("tblEmpLeaves.EndDate AS EndDate,");
        // stringBuffer.append("tblEmpLeaves.EndDate AS EndDate,");
        stringBuffer.append("tblEmpLeaves.leaveType AS leaveType,");
        stringBuffer.append("tblEmpLeaves.reportsTo AS reportsTo ");
        stringBuffer.append("from (tblEmployee join tblEmpLeaves ");
        stringBuffer.append("on(tblEmployee.Id = tblEmpLeaves.EmpId)) ");
       // stringBuffer.append("where tblEmpLeaves.reportsTo ='"+userId+"'");
        //stringBuffer.append(" OR");
       /* stringBuffer.append("where 1=1 AND ");
        
         Map myTeamMembers = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH);
            String myMemberIds ="";

for (Object key : myTeamMembers.keySet()) 
{
    myMemberIds =myMemberIds + DataSourceDataProvider.getInstance().getEmpIdByLoginId(key.toString()) + ",";
}


        stringBuffer.append(" tblEmpLeaves.EmpId in ("+myMemberIds.substring(0, myMemberIds.length()-1)+") ");
        */
        stringBuffer.append(" WHERE tblEmployee.ReportsTo='"+userId+"'");
        
        
//        stringBuffer.append(" tblEmpLeaves.reportsTo IN");
//        stringBuffer.append("(");
//        stringBuffer.append(dataSourceDataProvider.getInstance().getLeaveApprovalList(userId));
        stringBuffer.append(" ORDER BY EndDate DESC");
        httpServletRequest.setAttribute(ApplicationConstants.EMP_LEAVE_APPROVAL_LIST,stringBuffer.toString());
        this.setDownLevelReportsTo("NoSearch");
        
        resultType = "success";
        
        }catch (Exception ex){
            httpServletRequest.getSession(false).setAttribute("errorMessage","You don't have team, If any Queries please contact operation Team.");
            resultType =  "error";
        }
        }
        return resultType;
        //return  SUCCESS;
    }
    public String teamMembersLeavesSearch() throws Exception{
           resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         try{
        //Map myTeamManagersMap = new TreeMap();
        String userId = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("select tblEmpLeaves.Id ");
        stringBuffer.append("AS LeaveId,");
        stringBuffer.append("tblEmpLeaves.Status AS Status,");
        stringBuffer.append("tblEmpLeaves.EmpId AS EmpId, ");
        stringBuffer.append("concat(tblEmployee.FName,'.',tblEmployee.LName ) ");
        stringBuffer.append("AS EmpName,");
        stringBuffer.append("tblEmpLeaves.StartDate AS StartDate,");
        stringBuffer.append("tblEmpLeaves.EndDate AS EndDate,");
        stringBuffer.append("tblEmpLeaves.leaveType AS leaveType,");
        stringBuffer.append("tblEmpLeaves.reportsTo AS reportsTo ");
        stringBuffer.append("from (tblEmployee join tblEmpLeaves ");
        stringBuffer.append("on(tblEmployee.Id = tblEmpLeaves.EmpId)) where 1=1 ");
        setEmpName(getEmpName());
        setLeaveStatus(getLeaveStatus());
        if(!"".equals(getEmpName()))
        {         
        stringBuffer.append("and tblEmpLeaves.EmpId ="+DataSourceDataProvider.getInstance().getEmpIdByLoginId(getEmpName()));
        }
        else
        {
            Map myTeamMembers = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP_FOR_LEAVESEARCH);
            String myMemberIds ="";

for (Object key : myTeamMembers.keySet()) 
{
   // System.out.println(key.toString());
    myMemberIds =myMemberIds + DataSourceDataProvider.getInstance().getEmpIdByLoginId(key.toString()) + ",";
}
//System.out.println("myMmebrs-->"+myMemberIds.substring(0, myMemberIds.length()-1));

        stringBuffer.append("and tblEmpLeaves.EmpId in ("+myMemberIds.substring(0, myMemberIds.length()-1)+")");
        }
       if(!"".equals(getLeaveStatus()))
       {
       stringBuffer.append(" and tblEmpLeaves.Status='"+getLeaveStatus()+"' ");
       }
       stringBuffer.append(" ORDER BY EndDate DESC");
       
     //  System.out.println("Query-->"+stringBuffer.toString());
        httpServletRequest.setAttribute(ApplicationConstants.EMP_LEAVE_APPROVAL_LIST,stringBuffer.toString());
        this.setDownLevelReportsTo("NoSearch");
        
        resultType = "success";
        
        }catch (Exception ex){
            httpServletRequest.getSession(false).setAttribute("errorMessage","You don't have team, If any Queries please contact operation Team.");
            resultType =  "error";
        }
        }
        return resultType;
        //return  SUCCESS;
    }
	
    
    
    /**
     *  Populates user required options to the Screens depending on the  options.
     *
     *
     * @return  The  Result type is returned after complete the code of prepare
     * Method.
     *
     *changed:  26-10-2012 
     */
    
   
    public String getLeaveApprovalDetails() throws Exception{
        resultType = LOGIN;
       // System.out.println("before getting level reports to."+getId());
      dataSourceDataProvider = DataSourceDataProvider.getInstance();
        if((httpServletRequest.getSession(false) != null) && (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)!=null)) {
          
          //String reportsToChain = dataSourceDataProvider.getReportsToChainByleaveId(getId());
          String reportsToChain = dataSourceDataProvider.getReportsToChainByleaveId(getLeaveId());
          //System.out.println("reports to chain in leave action-->"+reportsToChain);
          String[] reportsToAll = null;
          reportsToAll = reportsToChain.split(";");
          boolean isValid = false;
          for(int i=0;i<reportsToAll.length;i++) {
              if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString().equals(reportsToAll[i])) {
                  isValid = true;
                  i = reportsToAll.length;
              }
          }
          
           // if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
          if(isValid) {
                this.setCurrentAction("editLeaveApproval");
                if(getId() == 0) {
                    int leaveId = Integer.parseInt(httpServletRequest.getParameter("leaveId").toString());
                    setId(leaveId);
                }
                this.setCurrentLeave(ServiceLocator.getLeaveService().getLeave(getId()));
                this.setOperationType("approvles");
                this.setId(getId());  // added on :: 06-11-2012
                this.setEmpName(DataSourceDataProvider.getInstance().getEmpNameByLeaveId(getId()).toString());
                return  SUCCESS;
            } else {
              httpServletRequest.getSession(false).setAttribute("errorMessage","You are not Authorized person to Aprove this leave.");
              return ERROR;
            }
        }if(httpServletRequest.getParameter("leaveId") != null){
            String leaveId = httpServletRequest.getParameter("leaveId");
            httpServletRequest.setAttribute("leaveId",leaveId);
            setLeaveId(Integer.parseInt(httpServletRequest.getAttribute("leaveId").toString()));
        }
        return  LOGIN;
    }
    /**
     *  Populates user required options to the Screens depending on the  options.
     *
     *
     * @return  The  Result type is returned after complete the code of prepare
     * Method.
     *
     *
     */
    
    
    public String getLeaveList() throws Exception{
           resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        String userId = (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
        dataSourceDataProvider = DataSourceDataProvider.getInstance();
        
        //this.setDownLevelReportsTo(dataSourceDataProvider.getDownReportsToFourLevel(userId));
        resultType = SUCCESS;
        }
        return  resultType;
    }
    
    
    /**
     *  Populates user required options to the Screens depending on the  options.
     *
     *
     * @return  The  Result type is returned after complete the code of prepare
     * Method.
     *
     *
     */
    
    
    public String getLeaveListDetails() throws Exception{
                   resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        this.setCurrentAction("editLeave");
        this.setCurrentLeave(ServiceLocator.getLeaveService().getLeave(getId()));
        this.setOperationType("request");
        this.setId(getId());
       // this.setEmpName(getEmpName());  getting Null value Replaced On :: 06-11-2012 
        this.setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
        resultType =  SUCCESS;
        }
        return resultType;
    }
    
    
    /**
     *   This method useful for search a leave  in the database.
     *
     *
     * @return  The  Result type is returned after complete the code of prepare
     * Method.
     *
     *
     */
    
    public String getLeaveSearch() throws Exception{
           resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        int  empId  = Integer.parseInt((String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID));
        if("Search".equalsIgnoreCase(getSubmitFrom())) {
            StringBuilder  stringBuilder = new StringBuilder();
            stringBuilder.append("select id,EmpId,StartDate,EndDate,leaveType,reportsTo,Status from tblEmpLeaves where EmpId="+empId);
            
            if(null == getStatusId()) setStatusId("");
            
            
            boolean andFlag = false;
            
            
            if(!"".equals(getStatusId()) && !andFlag) {
                stringBuilder.append(" and  Status LIKE '" +  getStatusId() + "' ");
                andFlag = true;
            }else  if(!"".equals(getStatusId()) && andFlag){
                stringBuilder.append("AND Status LIKE '" + getStatusId() + "' ");
            }
            
            if(getStartDate()!=null && !andFlag){
                stringBuilder.append(" and date(StartDate) >= '"+getStartDate()+"' ");
            }else if(getStartDate()!=null && andFlag){
                stringBuilder.append("AND date(StartDate) >='"+getStartDate()+"' ");
            }
            
            if(getEndDate()!=null && !andFlag){
                stringBuilder.append(" and date(EndDate) <='"+getEndDate()+"' ");
            } else if(getEndDate()!=null && andFlag){
                stringBuilder.append("AND date(EndDate) <='"+getEndDate()+"' ");
            }
            System.out.println("stringBuilder.toString()-"+stringBuilder.toString());
            
            httpServletRequest.setAttribute("leaveSearchList", stringBuilder.toString());
            
        }
        resultType = SUCCESS;
        }
        return resultType;
    }
    
    
    /**
     *   This method useful for Edit a leave
     *
     *
     * @return  The  Result type is returned after complete the code of prepare
     * Method.
     *
     *
     */
    
     public String doEditLeave() throws Exception{
            resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        String mode="edit";
        leaveService = ServiceLocator.getLeaveService();
        String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
        String userName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
        int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
        try{
            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            if(ServiceLocator.getLeaveService().addOrUpdateLeave(this,userId,userName,empId,mode) > 0) {
                
                setEmpLeaveLoginId(dataSourceDataProvider.getEmailId(getEmpId()));
                if(getEmpLeaveLoginId() != null){
                    finalChar = getEmpLeaveLoginId() + "@miraclesoft.com";
                }
                
                setEmpLeaveName(dataSourceDataProvider.getEmployeeNameByEmpNo(getEmpId()));
                
                setEmpOrganisation(dataSourceDataProvider.getEmployeeOrganisation(getEmpId()));
                
                if(getEmpOrganisation().equalsIgnoreCase("Miracle Software Systems(USA).Inc")){
                    mailCc = "timesheets@miraclesoft.com";
                    //mailCc="hkondala@miraclesoft.com";
                }else {//if(getEmpOrganisation().equalsIgnoreCase("Miracle Software Systems(India), Pvt. Ltd") || getEmpOrganisation().equalsIgnoreCase("IT Lokam Services(India), Pvt. Ltd")){
                    mailCc = "mcitypayroll@miraclesoft.com";
                     //mailCc="sjanana@miraclesoft.com";
                }
                
                if(!(finalChar.equals("")|| finalChar == null)) //if (getStatus() == "Approved")
                {   if(!(mailCc.equals("")|| mailCc == null)) {
                     /*if(Properties.getProperty("Mail.Flag").equals("1")) {
                        MailManager.sendLeaveEmail(finalChar,mailCc,getEmpLeaveName(),userName,getReason(),getStatus(),getLeaveRequiredFrom(),getLeaveRequiredTo(),getLeaveType());
                     }*/
                        resultMessage = "Leave has been Updated and mail is sent!";
                    }
                }else{
                    resultMessage = "Leave has been Updated!";
                }
                //getLeaveApprovalList();
                resultType = SUCCESS;
            } else {
                resultMessage = "Sorry! Please Try again!";
                resultType = INPUT;
            }
            httpServletRequest.setAttribute("resultMessage", resultMessage);
            this.setCurrentAction("editLeave");
            this.setOperationType("request");
            this.setEmpId(Integer.parseInt( (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID)));
            
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        }
        return resultType;
    }
    
    
    /**
     *  This method useful for add a leave
     *
     *
     * @return  The  Result type is returned after complete the code of prepare
     * Method.
     *
     *
     */
    
    public String doAddLeave()  {
         resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        String mode="add";
        try{
            String[] values = new String[2];
            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
            leaveService = ServiceLocator.getLeaveService();
            String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            String userName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            
           // System.out.println("leave addding result --->"+ServiceLocator.getLeaveService().addOrUpdateLeave(this,userId,userName,empId,mode));
            if(ServiceLocator.getLeaveService().addOrUpdateLeave(this,userId,userName,empId,mode) >= 0) {
                resultMessage = "Leave has been Applied!";
                resultType = SUCCESS;
            } else {
                resultMessage = "Sorry! Please Try again!";
                resultType = INPUT;
            }
            httpServletRequest.setAttribute("resultMessage", resultMessage);
            this.setCurrentAction("addLeave");
            //this.setOperationType("Employee"); -- why this is not in comments ????
            this.setOperationType("request");
            this.setEmpId(Integer.parseInt( (String) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID)));
            
            /* newly added -- start */
            this.setLevelReportsTo(dataSourceDataProvider.getReportsTo(userId));
            this.setOneLevelReportsTo(dataSourceDataProvider.getReportsTOOneLevel(userId));
            
            values = dataSourceDataProvider.getDepartmentWithOrgId(userId);
            this.setDepartmentId(values[0]);
            this.setOrgId(values[1]);
            resultType=SUCCESS;
            /* newly added -- end */
        }
        
        catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        }
        return resultType;
    }
    
    

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public String getCurrentAction() {
        return currentAction;
    }
    
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }
    
    
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getEmpId() {
        
        return empId;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public String getLeaveRequiredFrom() {
        return leaveRequiredFrom;
    }
    
    public void setLeaveRequiredFrom(String leaveRequiredFrom) {
        this.leaveRequiredFrom = leaveRequiredFrom;
    }
    
    public String getLeaveRequiredTo() {
        return leaveRequiredTo;
    }
    
    public void setLeaveRequiredTo(String leaveRequiredTo) {
        this.leaveRequiredTo = leaveRequiredTo;
    }
    
    public String getOperationType() {
        return operationType;
    }
    
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    
    public String getReportsTo() {
        return reportsTo;
    }
    
    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }
    
    public String getLevelReportsTo() {
        return levelReportsTo;
    }
    
    public void setLevelReportsTo(String levelReportsTo) {
        this.levelReportsTo = levelReportsTo;
    }
    
    public LeaveVTO getCurrentLeave() {
        return currentLeave;
    }
    
    public void setCurrentLeave(LeaveVTO currentLeave) {
        this.currentLeave = currentLeave;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDownLevelReportsTo() {
        return downLevelReportsTo;
    }
    
    public void setDownLevelReportsTo(String downLevelReportsTo) {
        this.downLevelReportsTo = downLevelReportsTo;
    }
    
    public String getOneLevelReportsTo() {
        return oneLevelReportsTo;
    }
    
    public void setOneLevelReportsTo(String oneLevelReportsTo) {
        this.oneLevelReportsTo = oneLevelReportsTo;
    }
    
    public String getOneLevelreportsToId() {
        return oneLevelreportsToId;
    }
    
    public void setOneLevelreportsToId(String oneLevelreportsToId) {
        this.oneLevelreportsToId = oneLevelreportsToId;
    }
    
    public String getLeaveType() {
        return leaveType;
    }
    
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
    
    public String getStatusId() {
        return statusId;
    }
    
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
    
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
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
    
    public String getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getOrgId() {
        return orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    
    public String getEmpLeaveLoginId() {
        return empLeaveLoginId;
    }
    
    public void setEmpLeaveLoginId(String empLeaveLoginId) {
        this.empLeaveLoginId = empLeaveLoginId;
    }
    
    public String getEmpLeaveName() {
        return empLeaveName;
    }
    
    public void setEmpLeaveName(String empLeaveName) {
        this.empLeaveName = empLeaveName;
    }
    
    public String getEmpOrganisation() {
        return empOrganisation;
    }
    
    public void setEmpOrganisation(String empOrganisation) {
        this.empOrganisation = empOrganisation;
    }
    
    public int getLeaveId() {
        return leaveId;
    }
    
    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    //New 02-20-2013
    
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
      public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @return the leaveStatus
     */
    public String getLeaveStatus() {
        return leaveStatus;
    }

    /**
     * @param leaveStatus the leaveStatus to set
     */
    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
}
