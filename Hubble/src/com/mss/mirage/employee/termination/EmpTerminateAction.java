/*
 * EmpTerminateAction.java
 *
 * Created on July 7, 2008, 7:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.termination;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import com.mss.mirage.util.DefaultDataProvider;
import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle
 */
public class EmpTerminateAction extends ActionSupport  implements ServletRequestAware{
    
    /** Creates a new instance of EmpTerminateAction */
    public EmpTerminateAction() {
    }
    /**
     *
     * Creating String Designation is to get the designation
     */
    private String designation;
    /**
     *
     *  Creating String TeamName is to get the Team Name
     */
    private String teamName;
    /**
     *
     *  Creating Date dateOfJoin is to get the Date of Join
     */
    private Date dateOfJoin;
    /**
     *
     * Creating Date dateOfTermination is to get the Date of Termination
     */
    private Date dateOfTermination;
    /**
     *
     * Creating Date reasonForTermination is to get the Reason For Termination
     */
    private String resonsForTerminate;
    /**
     *
     * Creating String itgBatch is to get/store the ITG Batch of user
     */
    private String itgBatch;
    /**
     *
     * Creating String currStatus is to get/store Current Status of User
     */
    private String currStatus;
    /**
     *
     *  Creating List currStatusList is to get the List of Status Types
     */
    private List currStatusList;
    /**
     *
     * Creating int empId is to get/store the Employee Id
     */
    private int empId;
    /**
     *
     * Creating String employeeName is to get/store the Employee Name
     */
    private String employeeName;
    /**
     *
     * The defaultDataProvider object provides the data to Screens
     */
    private DefaultDataProvider defaultDataProvider;
    /**
     *
     * The dataSourceDataProvider object provides  the data to Screens
     */
    private DataSourceDataProvider dataSourceDataProvider;
    /**
     *
     * The empTerminateVTO object provides the data to Screens
     */
    private EmpTerminateVTO empTerminateVTO;
    /**
     *
     * Creating int userRoleId is to get/Store User Role of User
     */
    private int userRoleId;
    
    /**
     *
     * The httpServletRequest object representing the HttpServletRequest
     */
    private HttpServletRequest httpServletRequest;
    
    /**
     *
     * This method is used to get the Default Data for Screen
     * @return String
     * @throws java.lang.Exception
     */
    public String prepare() throws Exception {
        String resultType="";
        resultType = LOGIN;
        //Checking whether user is login or not
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            //Checking whether action has permission
            if(AuthorizationManager.getInstance().isAuthorizedUser("TERMINATE_EMP",userRoleId)){
                try{
                    
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    dataSourceDataProvider = dataSourceDataProvider.getInstance();
                    setEmployeeName(dataSourceDataProvider.getEmployeeNameByEmpNo(getEmpId()));
                    setCurrStatusList(defaultDataProvider.getCurrentStatus(ApplicationConstants.CURRENT_STATUS_OPTIONS));
                    // ServiceLocator.getTerminateService().getTermination(empId);
                    setEmpTerminateVTO(ServiceLocator.getTerminateService().getTermination(empId));
                    setEmpId(empId);
                    //System.err.println("empId---"+getEmpTerminateVTO().getEmpId());
                    //System.err.println("empid--------1--"+empId);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        
        return resultType;
        
    }
    /**
     * 
     * This method is used to update the Current Status of the User
     * @return String
     * @throws java.lang.Exception 
     */
    public String update() throws Exception {
        String resultType="";
        resultType = LOGIN;
        String resultMessage="";
        //Checking whether user is login or not
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            //Checking whether action has permission
            if(AuthorizationManager.getInstance().isAuthorizedUser("TERMINATE_EDIT",userRoleId)){
                try{
                    
                    boolean isUpdated=false;
                    isUpdated=ServiceLocator.getTerminateService().updateTermination(this,empId);
                    if(isUpdated){
                        resultMessage="Employee has been terminated Successfully";
                    }else{
                        resultMessage="please Try again";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    prepare();
                    resultType= SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        
        return resultType;
    }
    
    //Setters And Getters
    
    public String getDesignation() {
        return designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public String getTeamName() {
        return teamName;
    }
    
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
    
    
    public String getResonsForTerminate() {
        return resonsForTerminate;
    }
    
    public void setResonsForTerminate(String resonsForTerminate) {
        this.resonsForTerminate = resonsForTerminate;
    }
    
    public String getItgBatch() {
        return itgBatch;
    }
    
    public void setItgBatch(String itgBatch) {
        this.itgBatch = itgBatch;
    }
    
    public String getCurrStatus() {
        return currStatus;
    }
    
    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus;
    }
    
    public List getCurrStatusList() {
        return currStatusList;
    }
    
    public void setCurrStatusList(List currStatusList) {
        this.currStatusList = currStatusList;
    }
    
    public int getEmpId() {
        return empId;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    public EmpTerminateVTO getEmpTerminateVTO() {
        return empTerminateVTO;
    }
    
    public void setEmpTerminateVTO(EmpTerminateVTO empTerminateVTO) {
        this.empTerminateVTO = empTerminateVTO;
    }
    
    public Date getDateOfJoin() {
        return dateOfJoin;
    }
    
    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }
    
    public Date getDateOfTermination() {
        return dateOfTermination;
    }
    
    public void setDateOfTermination(Date dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest=httpServletRequest;
    }
    
}
