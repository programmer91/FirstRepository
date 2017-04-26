/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 26, 2007, 12:35 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : AncillaryAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.ancillary;

import com.mss.mirage.crm.accounts.AccountService;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Rajanikanth Teppala<rteppala@miraclesoft.com>
 *  This Class.... ENTER THE DESCRIPTION HERE
 */

public class AncillaryAction extends ActionSupport implements ServletRequestAware{
    
    /** The resultType is used for storing the message,after performing the bussiness logic . */
    private String resultType;
    
    /** The resultMessage is used for storing the message,after performing the bussiness logic . */
    private String resultMessage;
    
    /** The httpServletRequest is used for storing the request from the Client . */
    private HttpServletRequest httpServletRequest;
    
    /** The AncillaryService is used for invoking ancillary Service methods.
     * {@link com.mss.mirage.util.ServiceLocator}
     */
    private AncillaryService ancillaryService;
    
    /** The currentAncillary is reference object of the AncillaryVTO. */
    private AncillaryVTO currentAncillary;
    
    
    
    /** The String formAction is used for storing the form name for the EmpAncillary.jsp   */
    private String formAction ="";
    
    /** The id is used for storing the primary value of the table. */
    private int id;
    
    // Form Variables
    
    
    private int empId;
    
    /** The fatherName  is used for storing the father name  value in the table. */
    private String fatherName;
    
    /** The fatherTitle  is used for storing the father title value in the table. */
    private String fatherTitle;
    
    /** The fatherPhone  is used for storing the father phone value in the table. */
    private String fatherPhone;
    
    /** The fatherAddress  is used for storing the father address  value in the table. */
    private String fatherAddress;
    
    /** The laptopType  is used for storing the laptop type value in the table. */
    private String laptopType;
    
    /** The memory  is used for storing the memory value in the table. */
    private String memory;
    
    /** The hardDisk  is used for storing the hard disk  value in the table. */
    private String hardDisk;
    
    /** The model  is used for storing the model value in the table. */
    private String model;
    
    /** The serialNo  is used for storing the serialNo  value in the table. */
    private String serialNo;
    
    /** The purchaseDate  is used for storing the purchaseDate  value in the table. */
    private Date purchaseDate;
    
    /** The warrantyExp  is used for storing the warrantyExp value in the table. */
    private Date warrantyExp;
    
    /** The referalName  is used for storing the referalName  value in the table. */
    private String referalName;
    
    /** The referalRelation  is used for storing the referalRelation  value in the table. */
    private String referalRelation;
    
    /** The referalPhone  is used for storing the referalPhone  value in the table. */
    private String referalPhone;
    
    /** The referalEmail  is used for storing the referalEmail value in the table. */
    private String referalEmail;
    
    /** The referalAltPhone  is used for storing the referalAltPhone  value in the table. */
    private String referalAltPhone;
    
    /** The referalComments  is used for storing the referalComments  value in the table. */
    private String referalComments;
    
    /** The contractOnField  is used for storing the contractOnField  value in the table. */
    private int contractOnField;
    
    /** The contractPeriod  is used for storing the contractPeriod  value in the table. */
    private String contractPeriod;
    
    /** The TrainPeriod  is used for storing the TrainPeriod value in the table. */
    private int TrainPeriod;
    
    /** The contractSignDate  is used for storing the contractSignDate  value in the table. */
    private Date contractSignDate;
    
    /** The contractExpDate  is used for storing the contractExpDate value in the table. */
    private Date contractExpDate;
    
    /** The trainStartDate  is used for storing the trainStartDate  value in the table. */
    private Date trainStartDate;
    
    /** The bondProvidedBy  is used for storing the bondProvidedBy  value in the table. */
    private String bondProvidedBy;
    
    /** The relationToEmployee  is used for storing the relationToEmployee  value in the table. */
    private String relationToEmployee;
    
    /** The jobTitle  is used for storing the jobTitle value in the table. */
    private String jobTitle;
    
    /** The jobCompany  is used for storing the jobCompany value in the table. */
    private String jobCompany;
    
    /** The jobPhone  is used for storing the jobPhone value in the table. */
    private String jobPhone;
    
    /** The jobAddress  is used for storing the jobAddress  value in the table. */
    private String jobAddress;
    
    /** The comments  is used for storing the comments value in the table. */
    private String comments;
    
    private String modifiedBy;
    
    private Timestamp modifiedDate;
    
    private String employeeName;
    
    private int userRoleId;
    
    private String degree;
    
    private String specialization;
    
    private double marks;
    
    private String university;
    
    private String passOut;
    
    private String college;
    
    private String pg;
    
    private String pgSpecialization;
    
    private double pgMarks;
    
    private String pgUniversity;
    
    private String pgPassOut;
    
    private String pgCollege;
    
    
    /** Creates a new instance of AncillaryAction */
    public AncillaryAction() {
    }
    public String execute()throws Exception{
        return SUCCESS;
    }
    
    public String checkAction(){
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("CHECK_ANCILLARY",userRoleId)){
                try{
                    setEmployeeName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(getId()));
                    if(ServiceLocator.getAncillaryService().checkAction(getId()) > 0){
                        setCurrentAncillary(ServiceLocator.getAncillaryService().getAncillary(getId()));
                        formAction="ancillaryEdit";
                    }else{
                        formAction="ancillaryAdd";
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
    
    /**
     * The doAdd() is used for adding  the ancillary details to the database server.
     * @throws Exception
     * @return String variable for navigation.
     */
    public String doAdd(){
        resultType = LOGIN;
      
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_ANCILLARY",userRoleId)){
                try{
                    int insertedRows=0;
                    ancillaryService =ServiceLocator.getAncillaryService();
                    setEmployeeName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(getId()));
                    //   setId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));
                    insertedRows =ancillaryService.addorUpdateAncillary(this);
                  //  setCurrentAncillary(ServiceLocator.getAncillaryService().getAncillary(getId()));
                    if(insertedRows == 1){
                        //change 
                       // setCurrentAncillary(ServiceLocator.getAncillaryService().getAncillary(getId()));
                       //  setFormAction("ancillaryEdit");
                        resultType = SUCCESS;
                        setResultMessage("Ancillary Details has been successfully inserted!");
                    }else{
                        resultType = INPUT;
                        setResultMessage("Sorry! Please Try again!");
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
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
     * The getActivity() is used for retrieving the ancillary details from database server.
     * @throws Exception
     * @return String variable for navigation.
     */
    public String getAncillary(){
        resultType = LOGIN;
      
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_ANICILLARY",userRoleId)){
                try{
                    setCurrentAncillary(ServiceLocator.getAncillaryService().getAncillary(getId()));
                    setFormAction("ancillaryEdit");
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
    
    /**
     * The doEdit() is used for editing  the ancillary details to the database server.
     * @throws Exception
     * @return String variable for navigation.
     */
    public String doEdit(){
        resultType = LOGIN;
     
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_ANCILLARY",userRoleId)){
                try{
                    int updatedRows = 0;
                    ancillaryService =ServiceLocator.getAncillaryService();
                    setEmployeeName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(getId()));
                    setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                    updatedRows = ancillaryService.UpdateAncillary(this);
                   // setCurrentAncillary(ServiceLocator.getAncillaryService().getAncillary(getId()));
                    
                    if(updatedRows >= 1){
                        //change
                       // setFormAction("ancillaryEdit");
                        resultType = SUCCESS;
                        setResultMessage("Ancillary Details has been successfully updated!");
                    }else{
                        resultType = INPUT;
                        setResultMessage("Sorry! Please Try again!");
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
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
     * The setServletRequest(HttpServletRequest httpServletRequest) is for binding request Object
     * this Object mean's ActionObject
     */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public int getEmpId() {
        return empId;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    /**
     * The getFatherName() is used for accessing father name for Ancillary.
     * @ return String variable
     */
    public String getFatherName() {
        return fatherName;
    }
    
    /**
     * The setFatherName(String fatherName) is used for setting father name for Ancillary.
     *
     */
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    
    /**
     * The getFatherTitle() is used for accessing father title for Ancillary.
     * @ return String variable
     */
    public String getFatherTitle() {
        return fatherTitle;
    }
    
    /**
     * The setFatherTitle(String fatherTitle) is used for setting father title for Ancillary.
     *
     */
    public void setFatherTitle(String fatherTitle) {
        this.fatherTitle = fatherTitle;
    }
    
    /**
     * The getFatherPhone() is used for accessing father phone for Ancillary.
     * @ return String variable
     */
    public String getFatherPhone() {
        return fatherPhone;
    }
    
    /**
     * The setFatherPhone(String fatherPhone) is used for setting father phone for Ancillary.
     *
     */
    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }
    
    /**
     * The getFatherAddress() is used for accessing father address for Ancillary.
     * @ return String variable
     */
    public String getFatherAddress() {
        return fatherAddress;
    }
    
    /**
     * The setFatherAddress(String fatherAddress) is used for setting father address for Ancillary.
     *
     */
    public void setFatherAddress(String fatherAddress) {
        this.fatherAddress = fatherAddress;
    }
    
    /**
     * The getLaptopType() is used for accessing LaptopType for Ancillary.
     * @ return String variable
     */
    public String getLaptopType() {
        return laptopType;
    }
    
    /**
     * The setLaptopType(String laptopType) is used for setting laptopType for Ancillary.
     *
     */
    public void setLaptopType(String laptopType) {
        this.laptopType = laptopType;
    }
    
    /**
     * The getMemory() is used for accessing Memory for Ancillary.
     * @ return String variable
     */
    public String getMemory() {
        return memory;
    }
    
    /**
     * The setMemory(String memory) is used for setting memory for Ancillary.
     *
     */
    public void setMemory(String memory) {
        this.memory = memory;
    }
    
    /**
     * The getHardDisk() is used for accessing hardDisk for Ancillary.
     * @ return String variable
     */
    public String getHardDisk() {
        return hardDisk;
    }
    
    /**
     * The setHardDisk(String hardDisk) is used for setting hardDisk for Ancillary.
     *
     */
    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }
    
    /**
     * The getModel() is used for accessing model for Ancillary.
     * @ return String variable
     */
    public String getModel() {
        return model;
    }
    
    /**
     * The setModel(String model) is used for setting model for Ancillary.
     *
     */
    public void setModel(String model) {
        this.model = model;
    }
    
    /**
     * The getSerialNo() is used for accessing serialNo for Ancillary.
     * @ return String variable
     */
    public String getSerialNo() {
        return serialNo;
    }
    
    /**
     * The setSerialNo(String serialNo) is used for setting serialNo for Ancillary.
     *
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
    
    /**
     * The getPurchaseDate() is used for accessing purchaseDate for Ancillary.
     * @ return Date variable
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    
    /**
     * The setPurchaseDate(Date purchaseDate) is used for setting purchaseDate for Ancillary.
     *
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    /**
     * The getWarrantyExp() is used for accessing warrantyExp for Ancillary.
     * @ return Date variable
     */
    public Date getWarrantyExp() {
        return warrantyExp;
    }
    
    /**
     * The setWarrantyExp(Date warrantyExp) is used for setting warrantyExp for Ancillary.
     *
     */
    public void setWarrantyExp(Date warrantyExp) {
        this.warrantyExp = warrantyExp;
    }
    
    /**
     * The getReferalName() is used for accessing ReferalName for Ancillary.
     * @ return String variable
     */
    public String getReferalName() {
        return referalName;
    }
    
    /**
     * The setReferalName(String referalName) is used for setting ReferalName for Ancillary.
     *
     */
    public void setReferalName(String referalName) {
        this.referalName = referalName;
    }
    
    /**
     * The getReferalRelation() is used for accessing ReferalName for Ancillary.
     * @ return String variable
     */
    public String getReferalRelation() {
        return referalRelation;
    }
    
    /**
     * The setReferalRelation(String referalRelation) is used for setting ReferalName for Ancillary.
     *
     */
    public void setReferalRelation(String referalRelation) {
        this.referalRelation = referalRelation;
    }
    
    /**
     * The getReferalPhone() is used for accessing referalPhone for Ancillary.
     * @ return String variable
     */
    public String getReferalPhone() {
        return referalPhone;
    }
    
    /**
     * The setReferalPhone(String referalPhone) is used for setting referalPhone for Ancillary.
     *
     */
    public void setReferalPhone(String referalPhone) {
        this.referalPhone = referalPhone;
    }
    
    /**
     * The getReferalEmail() is used for accessing referalEmail for Ancillary.
     * @ return String variable
     */
    public String getReferalEmail() {
        return referalEmail;
    }
    
    /**
     * The setReferalEmail(String referalEmail) is used for setting referalEmail for Ancillary.
     *
     */
    public void setReferalEmail(String referalEmail) {
        this.referalEmail = referalEmail;
    }
    
    /**
     * The getReferalAltPhone() is used for accessing referalAltPhone for Ancillary.
     * @ return String variable
     */
    public String getReferalAltPhone() {
        return referalAltPhone;
    }
    
    /**
     * The setReferalAltPhone(String referalAltPhone) is used for setting referalAltPhone for Ancillary.
     *
     */
    public void setReferalAltPhone(String referalAltPhone) {
        this.referalAltPhone = referalAltPhone;
    }
    
    /**
     * The getReferalComments() is used for accessing referalComments for Ancillary.
     * @ return String variable
     */
    public String getReferalComments() {
        return referalComments;
    }
    
    /**
     * The setReferalComments(String referalComments) is used for setting referalComments for Ancillary.
     *
     */
    public void setReferalComments(String referalComments) {
        this.referalComments = referalComments;
    }
    
    /**
     * The getContractOnField() is used for accessing contractOnField for Ancillary.
     * @ return int variable
     */
    public int getContractOnField() {
        return contractOnField;
    }
    
    /**
     * The setContractOnField(int contractOnField) is used for setting contractOnField for Ancillary.
     *
     */
    public void setContractOnField(int contractOnField) {
        this.contractOnField = contractOnField;
    }
    
    /**
     * The getContractPeriod() is used for accessing contractPeriod for Ancillary.
     * @ return String variable
     */
    public String getContractPeriod() {
        return contractPeriod;
    }
    
    /**
     * The setContractPeriod(String contractPeriod) is used for setting contractPeriod for Ancillary.
     *
     */
    public void setContractPeriod(String contractPeriod) {
        this.contractPeriod = contractPeriod;
    }
    
    /**
     * The getTrainPeriod() is used for accessing TrainPeriod for Ancillary.
     * @ return int variable
     */
    public int getTrainPeriod() {
        return TrainPeriod;
    }
    
    /**
     * The setTrainPeriod(int TrainPeriod) is used for setting TrainPeriod for Ancillary.
     *
     */
    public void setTrainPeriod(int TrainPeriod) {
        this.TrainPeriod = TrainPeriod;
    }
    
    /**
     * The getContractSignDate() is used for accessing contractSignDate for Ancillary.
     * @ return Date variable
     */
    public Date getContractSignDate() {
        return contractSignDate;
    }
    
    /**
     * The setContractSignDate(Date contractSignDate) is used for setting contractSignDate for Ancillary.
     *
     */
    public void setContractSignDate(Date contractSignDate) {
        this.contractSignDate = contractSignDate;
    }
    
    /**
     * The getContractExpDate() is used for accessing contractExpDate for Ancillary.
     * @ return Date variable
     */
    public Date getContractExpDate() {
        return contractExpDate;
    }
    
    /**
     * The setContractExpDate(Date contractExpDate) is used for setting contractExpDate for Ancillary.
     *
     */
    public void setContractExpDate(Date contractExpDate) {
        this.contractExpDate = contractExpDate;
    }
    
    /**
     * The getTrainStartDate() is used for accessing trainStartDate for Ancillary.
     * @ return Date variable
     */
    public Date getTrainStartDate() {
        return trainStartDate;
    }
    
    /**
     * The setTrainStartDate(Date trainStartDate) is used for setting trainStartDate for Ancillary.
     *
     */
    public void setTrainStartDate(Date trainStartDate) {
        this.trainStartDate = trainStartDate;
    }
    
    /**
     * The getBondProvidedBy() is used for accessing bondProvidedBy for Ancillary.
     * @ return String variable
     */
    public String getBondProvidedBy() {
        return bondProvidedBy;
    }
    
    /**
     * The setBondProvidedBy(String bondProvidedBy) is used for setting bondProvidedBy for Ancillary.
     *
     */
    public void setBondProvidedBy(String bondProvidedBy) {
        this.bondProvidedBy = bondProvidedBy;
    }
    
    /**
     * The getRelationToEmployee() is used for accessing relationToEmployee for Ancillary.
     * @ return String variable
     */
    public String getRelationToEmployee() {
        return relationToEmployee;
    }
    
    /**
     * The setRelationToEmployee(String relationToEmployee) is used for setting relationToEmployee for Ancillary.
     *
     */
    public void setRelationToEmployee(String relationToEmployee) {
        this.relationToEmployee = relationToEmployee;
    }
    
    /**
     * The getJobTitle() is used for accessing jobTitle for Ancillary.
     * @ return String variable
     */
    public String getJobTitle() {
        return jobTitle;
    }
    
    /**
     * The setJobTitle(String jobTitle) is used for setting jobTitle for Ancillary.
     *
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    /**
     * The getJobCompany() is used for accessing jobCompany for Ancillary.
     * @ return String variable
     */
    public String getJobCompany() {
        return jobCompany;
    }
    
    /**
     * The setJobCompany(String jobCompany) is used for setting jobCompany for Ancillary.
     *
     */
    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }
    
    /**
     * The getJobPhone() is used for accessing jobPhone for Ancillary.
     * @ return String variable
     */
    public String getJobPhone() {
        return jobPhone;
    }
    
    /**
     * The setJobPhone(String jobPhone) is used for setting jobPhone for Ancillary.
     *
     */
    public void setJobPhone(String jobPhone) {
        this.jobPhone = jobPhone;
    }
    
    /**
     * The getJobAddress() is used for accessing jobAddress for Ancillary.
     * @ return String variable
     */
    public String getJobAddress() {
        return jobAddress;
    }
    
    /**
     * The setJobAddress(String jobAddress) is used for setting jobAddress for Ancillary.
     *
     */
    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }
    
    /**
     * The getComments() is used for accessing comments for Ancillary.
     * @ return String variable
     */
    public String getComments() {
        return comments;
    }
    
    /**
     * The setComments(String comments) is used for setting comments for Ancillary.
     *
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    /**
     * The getId() is used for accessing id for Ancillary.
     * @ return int variable
     */
    public int getId() {
        return id;
    }
    
    /**
     * The setId(int id) is used for setting id for Ancillary.
     *
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    /**
     * The getFormAction() is used for accessing formAction for EmpAncillary.jsp.
     * @ return String variable
     */
    public String getFormAction() {
        return formAction;
    }
    
    /**
     * The setFormAction(String formAction) is used for setting formAction for EmpAncillary.jsp.
     *
     */
    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }
    
    public AncillaryVTO getCurrentAncillary() {
        return currentAncillary;
    }
    
    public void setCurrentAncillary(AncillaryVTO currentAncillary) {
        this.currentAncillary = currentAncillary;
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getPassOut() {
        return passOut;
    }

    public void setPassOut(String passOut) {
        this.passOut = passOut;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }
    
    public String getPgSpecialization() {
        return pgSpecialization;
    }

    public void setPgSpecialization(String pgSpecialization) {
        this.pgSpecialization = pgSpecialization;
    }

    public double getPgMarks() {
        return pgMarks;
    }

    public void setPgMarks(double pgMarks) {
        this.pgMarks = pgMarks;
    }

    public String getPgUniversity() {
        return pgUniversity;
    }

    public void setPgUniversity(String pgUniversity) {
        this.pgUniversity = pgUniversity;
    }

    public String getPgPassOut() {
        return pgPassOut;
    }

    public void setPgPassOut(String pgPassOut) {
        this.pgPassOut = pgPassOut;
    }

    public String getPgCollege() {
        return pgCollege;
    }

    public void setPgCollege(String pgCollege) {
        this.pgCollege = pgCollege;
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

}
