/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 27, 2007, 12:35 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : EmpInsuranceAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.insurance;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Rajanikanth Teppala<rteppala@miraclesoft.com>
 *  This Class.... ENTER THE DESCRIPTION HERE
 */

public class EmpInsuranceAction extends ActionSupport implements ServletRequestAware,ParameterNameAware{
    
    /** The empid is used for storing the Insurance Id. */
    private int empId;
    //private int empId =5;
    /** The licPolicyNumber is used for storing the Insurance licPolicyNumber */
    private String licPolicyNumber;
    /** The licPolicyDate is used for storing the Insurance licPolicyDate*/
    private Date licPolicyDate;
    /** The licPolicyValues is used for storing the Insurance licPolicyValues */
    private String licPolicyValues;
    /** The licPolicyComNumber is used for storing the Insurance licPolicyComNumber */
    private String  licPolicyComNumber;
    /** The healthInsureanceType is used for storing the Insurance healthInsuranceType*/
    private String healthInsuranceType;
    /** The healthInsEffecDate is used for storing the Insurance healthInsEffecDate */
    private Date healthInsEffecDate;
    /** The effecChangeDate is used for storing the Insurance effecChangeDate */
    private Date effecChangeDate;
    /** The healthInsuranceCoverage is used for storing the Insurance healthInsuranceCoverage*/
    private int healthInsuranceCoverage;
    
    /** The healthInsComName is used for storing the Insurance healthInsComName */
    private String healthInsComName;
    /** The healthInsurePolicyNum is used for storing the Insurance healthInsurePolicyNumber */
    private String healthInsurePolicyNum;
    /** The healthInsPolicyDate is used for storing the Insurance healthInsPolicyDate */
    private Date healthInsPolicyDate;
    
    /** The healthInsureNumOfDep is used for storing the Insurance healthInsureNumOfDep */
    private int healthInsureNumOfDep;
    /** The healthInsureDedAmt is used for storing the Insurance healthInsureDedAmt */
    private String healthInsureDedAmt;
    /** The cobraNotif is used for storing the Insurance cobraNotif */
    private double cobraNotif;
    
    /** The cobraNotifDate is used for storing the Insurance cobraNotifDate */
    private Date cobraNotifDate;
    /** The cobraStartDate is used for storing the Insurance cobraStartDate */
    private Date cobraStartDate;
    /** The cobraPayment is used for storing the Insurance cobraPayment */
    private double cobraPayment;
    
    /** The dentalInsureType is used for storing the Insurance dentalInsureType */
    private int dentalInsureType;
    /** The dentalInsEffecDate is used for storing the Insurance dentalInsEffecDate */
    private Date dentalInsEffecDate;
    /** The dentalInsureCoverage is used for storing the Insurance dentalInsureCoverage */
    private int dentalInsureCoverage;
    
    /** The medicalLeave is used for storing the Insurance medicalLeave */
    private String medicalLeave;
    /** The medicalLeaveEffecDate is used for storing the Insurance medicalLeaveEffecDate */
    private Date medicalLeaveEffecDate;
    /** The medicalHours is used for storing the Insurance medicalHours */
    private double medicalHours;
    
    /** The shortTermDisability is used for storing the Insurance shortTermDisability */
    private String shortTermDisability;
    /** The longTermDisability is used for storing the Insurance longTermDisability */
    private String longTermDisability;
    /** The comments is used for storing the Insurance comments */
    private String comments;
    
    private String modifiedBy;
    
    private Timestamp modifiedDate;
    
    private String employeeName;
    
    private String resultType;
    private String resultMessage;
    private HttpServletRequest httpServletRequest;
    
    private EmpInsuranceVTO currentEmpInsurance;
    private String formAction = "empInsuranceAdd";
    
    //EmployeeServiceImpl employeeService=new EmployeeServiceImpl();
    
    private List healthInsuranceTypeList = new ArrayList();
    
    private HibernateDataProvider hibernateDataProvider;
    
    private DataSourceDataProvider dataSourceDataProvider;
    
    private int userRoleId;
    
   
    /** Creates a new instance of EmpInsuranceAction */
    public EmpInsuranceAction() {
    }
    public boolean acceptableParameterName(String parameter) {
        boolean isParamAcceptable=true;
        if(parameter.equals("submit")){
            isParamAcceptable=false;
        }
        return isParamAcceptable;
    }
    public String prepare() {
        resultType = LOGIN;
      
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_INSURANCE",userRoleId)){
                try{
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    setEmployeeName(dataSourceDataProvider.getEmployeeNameByEmpNo(getEmpId()));
                    setHealthInsuranceTypeList(hibernateDataProvider.getHealthInsuranceType(ApplicationConstants.EMP_HEALTH_INSURANCE_TYPES));
                    
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
    
    public String checkAction(){
        resultType = LOGIN;
      
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("CHECK_INSURANCE",userRoleId)){
                try{
                    prepare();
                    if(ServiceLocator.getEmpInsuranceService().checkAction(getEmpId()) > 0){
                        setCurrentEmpInsurance(ServiceLocator.getEmpInsuranceService().getInsurance(this.getEmpId()));
                        formAction="insuranceEdit";
                    }else{
                        formAction="empInsuranceAdd";
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
    
    /**
     * This method is using for add a details of the new Insurance
     *@ return resultType
     *@ throws Exception.
     */
    public String doAdd() {
       
        // setEmpId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_INSURANCE",userRoleId)){
                try{
                    boolean isInsert=ServiceLocator.getEmpInsuranceService().doInsuranceAdd(this);
                    if(isInsert){
                        setCurrentEmpInsurance(ServiceLocator.getEmpInsuranceService().getInsurance(this.getEmpId()));
                        formAction="insuranceEdit";
                        resultType = SUCCESS;
                        resultMessage = "Insurance Details has been successfully saved!";
                    }else{
                        resultType = INPUT;
                        resultMessage = "Sorry! Please Try again!";
                    }
                    httpServletRequest.setAttribute("resultMessage",resultMessage);
                    
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
    
    
    public String getInsurance() {
        // getEmpInsuranceVTO
       
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_INSURANCE",userRoleId)){
                try{
                    setCurrentEmpInsurance(ServiceLocator.getEmpInsuranceService().getInsurance(getEmpId())); //getCurrEmpInsurance
                    prepare();
                    resultType = SUCCESS;
                    setFormAction("insuranceEdit");
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
     * This method is using for Edit a details of the new Insurance
     *@ return resultType
     *@ throws Exception.
     */
    
    public String doEdit() {
        resultType = LOGIN;
      
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_INSURANCE",userRoleId)){
                try{
                    setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                    boolean isEdit=ServiceLocator.getEmpInsuranceService().doInsuranceEdit(this);
                    if(isEdit){
                        setCurrentEmpInsurance(ServiceLocator.getEmpInsuranceService().getInsurance(this.getEmpId()));
                        formAction="insuranceEdit";
                        resultType = SUCCESS;
                        resultMessage = "Insurance Details has been updated successfully!";
                    }else{
                        resultType = INPUT;
                        resultMessage = "Sorry! Please Try again!";
                    }
                    httpServletRequest.setAttribute("resultMessage",resultMessage);
                    
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
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    /**
     * setter and getter methods for select tables
     */
    
    public int getEmpId() {
        return empId;
    }
    
    /**
     * The setStatus(String status) is used for setting status of Activity.
     *
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public String getLicPolicyNumber() {
        return licPolicyNumber;
    }
    
    public void setLicPolicyNumber(String licPolicyNumber) {
        this.licPolicyNumber = licPolicyNumber;
    }
    
    public Date getLicPolicyDate() {
        return licPolicyDate;
    }
    
    public void setLicPolicyDate(Date licPolicyDate) {
        this.licPolicyDate = licPolicyDate;
    }
    
    public String getLicPolicyValues() {
        return licPolicyValues;
    }
    
    public void setLicPolicyValues(String licPolicyValues) {
        this.licPolicyValues = licPolicyValues;
    }
    
    public String getLicPolicyComNumber() {
        return licPolicyComNumber;
    }
    
    public void setLicPolicyComNumber(String licPolicyComNumber) {
        this.licPolicyComNumber = licPolicyComNumber;
    }
    
    public Date getHealthInsEffecDate() {
        return healthInsEffecDate;
    }
    
    public void setHealthInsEffecDate(Date healthInsEffecDate) {
        this.healthInsEffecDate = healthInsEffecDate;
    }
    
    public Date getEffecChangeDate() {
        return effecChangeDate;
    }
    
    public void setEffecChangeDate(Date effecChangeDate) {
        this.effecChangeDate = effecChangeDate;
    }
    
    public int getHealthInsuranceCoverage() {
        return healthInsuranceCoverage;
    }
    
    public void setHealthInsuranceCoverage(int healthInsuranceCoverage) {
        this.healthInsuranceCoverage = healthInsuranceCoverage;
    }
    
    public String getHealthInsComName() {
        return healthInsComName;
    }
    
    public void setHealthInsComName(String healthInsComName) {
        this.healthInsComName = healthInsComName;
    }
    
    public String getHealthInsurePolicyNum() {
        return healthInsurePolicyNum;
    }
    
    public void setHealthInsurePolicyNum(String healthInsurePolicyNum) {
        this.healthInsurePolicyNum = healthInsurePolicyNum;
    }
    
    public Date getHealthInsPolicyDate() {
        return healthInsPolicyDate;
    }
    
    public void setHealthInsPolicyDate(Date healthInsPolicyDate) {
        this.healthInsPolicyDate = healthInsPolicyDate;
    }
    
    public int getHealthInsureNumOfDep() {
        return healthInsureNumOfDep;
    }
    
    public void setHealthInsureNumOfDep(int healthInsureNumOfDep) {
        this.healthInsureNumOfDep = healthInsureNumOfDep;
    }
    
    public String getHealthInsureDedAmt() {
        return healthInsureDedAmt;
    }
    
    public void setHealthInsureDedAmt(String healthInsureDedAmt) {
        this.healthInsureDedAmt = healthInsureDedAmt;
    }
    
    public double getCobraNotif() {
        return cobraNotif;
    }
    
    public void setCobraNotif(double cobraNotif) {
        this.cobraNotif = cobraNotif;
    }
    
    public Date getCobraNotifDate() {
        return cobraNotifDate;
    }
    
    public void setCobraNotifDate(Date cobraNotifDate) {
        this.cobraNotifDate = cobraNotifDate;
    }
    
    public Date getCobraStartDate() {
        return cobraStartDate;
    }
    
    public void setCobraStartDate(Date cobraStartDate) {
        this.cobraStartDate = cobraStartDate;
    }
    
    public double getCobraPayment() {
        return cobraPayment;
    }
    
    public void setCobraPayment(double cobraPayment) {
        this.cobraPayment = cobraPayment;
    }
    
    public int getDentalInsureType() {
        return dentalInsureType;
    }
    
    public void setDentalInsureType(int dentalInsureType) {
        this.dentalInsureType = dentalInsureType;
    }
    
    public Date getDentalInsEffecDate() {
        return dentalInsEffecDate;
    }
    
    public void setDentalInsEffecDate(Date dentalInsEffecDate) {
        this.dentalInsEffecDate = dentalInsEffecDate;
    }
    
    public int getDentalInsureCoverage() {
        return dentalInsureCoverage;
    }
    
    public void setDentalInsureCoverage(int dentalInsureCoverage) {
        this.dentalInsureCoverage = dentalInsureCoverage;
    }
    
    public String getMedicalLeave() {
        return medicalLeave;
    }
    
    public void setMedicalLeave(String medicalLeave) {
        this.medicalLeave = medicalLeave;
    }
    
    public Date getMedicalLeaveEffecDate() {
        return medicalLeaveEffecDate;
    }
    
    public void setMedicalLeaveEffecDate(Date medicalLeaveEffecDate) {
        this.medicalLeaveEffecDate = medicalLeaveEffecDate;
    }
    
    public double getMedicalHours() {
        return medicalHours;
    }
    
    public void setMedicalHours(double medicalHours) {
        this.medicalHours = medicalHours;
    }
    
    public String getShortTermDisability() {
        return shortTermDisability;
    }
    
    public void setShortTermDisability(String shortTermDisability) {
        this.shortTermDisability = shortTermDisability;
    }
    
    public String getLongTermDisability() {
        return longTermDisability;
    }
    
    public void setLongTermDisability(String longTermDisability) {
        this.longTermDisability = longTermDisability;
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public EmpInsuranceVTO getCurrentEmpInsurance() {
        return currentEmpInsurance;
    }
    
    public void setCurrentEmpInsurance(EmpInsuranceVTO currentEmpInsurance) {
        this.currentEmpInsurance = currentEmpInsurance;
    }
    
    public String getFormAction() {
        return formAction;
    }
    
    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }
    
    public List getHealthInsuranceTypeList() {
        return healthInsuranceTypeList;
    }
    
    public void setHealthInsuranceTypeList(List healthInsuranceTypeList) {
        this.healthInsuranceTypeList = healthInsuranceTypeList;
    }
    
    public String getHealthInsuranceType() {
        return healthInsuranceType;
    }
    
    public void setHealthInsuranceType(String healthInsuranceType) {
        this.healthInsuranceType = healthInsuranceType;
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
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    
    
    
}
