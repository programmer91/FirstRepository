/*
 * VenusReportAction.java
 *
 * Created on January 7, 2009, 8:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.venus;

import com.mss.mirage.util.DateUtility;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;

/**
 *
 * @author miracle
 */
public class VenusReportAction extends ActionSupport implements ServletRequestAware {
    
    private HttpServletRequest httpServletRequest;
    
    private String resultType;
    
    private int userRoleId;
    
    private StringBuilder stringBuilder;
    
    private String empName;
    private String departmentId;
    private Date startDate;
    private Date endDate;
    private boolean timeViolators;
    
    /** Creates a new instance of VenusReportAction */
    public VenusReportAction() {
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest=httpServletRequest;
    }
    public String prepare() throws Exception {
        
        resultType = LOGIN;
        
        try{
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
                String userId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                resultType = "accessFailed";
                // if(AuthorizationManager.getInstance().isAuthorizedUser("MY_VENUS_REPORT",userRoleId)){
                
                stringBuilder = new StringBuilder();
                //stringBuilder.append("SELECT Id,LoginId,FromNo ,ToNo ,CallStartTime,CallEndTime ,Duration ,CallDate,Description from tblEmpPhoneLog where LoginId='"+userId+"'");
                stringBuilder.append("select distinct concat(FName,'.',LName) as Name, ");
                stringBuilder.append("LoginTime, LogoutTime, ");
                stringBuilder.append("TotalBreakTime, TotalWorkedTime, TotalMeetingTime ");
                stringBuilder.append("from tblMyCopAttendance, ");
                stringBuilder.append("tblEmployee ");
                stringBuilder.append("where tblEmployee.LoginId=tblMyCopAttendance.LoginId ");
                stringBuilder.append("and ");
                stringBuilder.append(" tblEmployee.CurStatus='Active' ");
                stringBuilder.append("and ");
//                stringBuilder.append(" (LName  like '"+empName+"%"+"'"+" or FName like '"+empName+"%"+"')");
//                stringBuilder.append(" and ");
//                stringBuilder.append("a.DepartmentId='"+departmentId+"'");
                stringBuilder.append(" Date(LoginTime) ");
                stringBuilder.append("between ");
                stringBuilder.append("DATE_SUB('" +Date.valueOf(DateUtility.getInstance().getToDayDate())+"',INTERVAL 1 DAY) ");
                stringBuilder.append("and ");
                stringBuilder.append("DATE_SUB('" +Date.valueOf(DateUtility.getInstance().getToDayDate())+"',INTERVAL 1 DAY) ");
                stringBuilder.append("order by FName ");
                stringBuilder.append("limit 30");
                
                
                
                
                /*if query is already existed remove and add new squery */
                if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.EMP_VENUS_REPORT_LIST)!=null){
                    httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.EMP_VENUS_REPORT_LIST);
                }
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.EMP_VENUS_REPORT_LIST,stringBuilder.toString());
                //}
                resultType = SUCCESS;
                
            }
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        // setCurrentSearchAction("phoneLogSearch");
        return resultType;
    }
    public String getVenusReport() throws Exception {
        resultType = LOGIN;
        
        try{
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
                String userId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                resultType = "accessFailed";
                if(AuthorizationManager.getInstance().isAuthorizedUser("MY_VENUS_REPORT",userRoleId)){
                    if("All".equalsIgnoreCase(getDepartmentId())) setDepartmentId("");
                    stringBuilder = new StringBuilder();
                    //stringBuilder.append("SELECT Id,LoginId,FromNo ,ToNo ,CallStartTime,CallEndTime ,Duration ,CallDate,Description from tblEmpPhoneLog where LoginId='"+userId+"'");
                    stringBuilder.append("select distinct concat(FName,'.',LName) as Name, ");
                    stringBuilder.append("LoginTime, LogoutTime, ");
                    stringBuilder.append("TotalBreakTime, TotalWorkedTime, TotalMeetingTime ");
                    stringBuilder.append("from tblMyCopAttendance, ");
                    stringBuilder.append("tblEmployee ");
                    stringBuilder.append("where tblEmployee.LoginId=tblMyCopAttendance.LoginId ");
                    stringBuilder.append("and ");
                    stringBuilder.append(" tblEmployee.CurStatus='Active' ");
                    stringBuilder.append("and ");
                    stringBuilder.append(" (LName  like '"+getEmpName()+"%"+"'"+" or FName like '"+getEmpName()+"%"+"')");
                    if(!"".equals(getDepartmentId())) {
                        stringBuilder.append(" and ");
                        stringBuilder.append("tblEmployee.DepartmentId='"+getDepartmentId()+"' ");
                    }
                    stringBuilder.append(" and Date(tblMyCopAttendance.LoginTime) ");
                    stringBuilder.append(" between '"+getStartDate()+"'");
                    stringBuilder.append(" and ");
                    stringBuilder.append("'"+getEndDate()+"'");
                    
                    if(isTimeViolators()==true){
                        stringBuilder.append(" and  TotalWorkedTime < '08:30' ");
                    }
                    stringBuilder.append( "order by FName ");
                    
                    
                    
                    /*if query is already existed remove and add new squery */
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.EMP_VENUS_REPORT_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.EMP_VENUS_REPORT_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.EMP_VENUS_REPORT_LIST,stringBuilder.toString());
                }
                resultType = SUCCESS;
                
            }
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        // setCurrentSearchAction("phoneLogSearch");
        return resultType;
    }
    
    public String getEmpName() {
        return empName;
    }
    
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    public String getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
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
    
    public boolean isTimeViolators() {
        return timeViolators;
    }
    
    public void setTimeViolators(boolean timeViolators) {
        this.timeViolators = timeViolators;
    }
    
}
