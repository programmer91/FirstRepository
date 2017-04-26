
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment.consultantdetails
 *
 * Date    :   Created on October 5, 2007, 8:36 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantDetailsAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.recruitment.consultantdetails;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 * The <code>ConsultantDetailsAction</code>  class is used for getting Consultant skills from
 * <i>ConsultantDetails.jsp</i> page.
 * <p>
 * Then it invokes setter methods in <code>ConsultantDetailsAction</code> class and invokes doAdd() method
 * in <code>ConsultantDetailsAction</code> for inserting Consultant skills in mirage.tblRecSkills.
 *invokes doGet() method in <code>ConsultantDetailsAction</code> for getting a consultant skills values from table mirage.tblRecSkills.
 * invokes doEdit()method in  <code>ConsultantDetailsAction</code> for editing a skills of particular consultant .
 *invokes doDelete()method in <code>ConsultantDetailsAction</code> for deleting  a skills of particular consultant .
 *
 *
 * @author Kondaiah Adapa <a href="mailto:kadapa@miraclesoft.com">kadapa@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2006
 *
 *
 *
 * /**
 * @author kondaiah Adapa <kadapa@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 *
 */
public class ConsultantDetailsAction extends ActionSupport implements ServletRequestAware {
    
    /** Creates a new instance of ConsultantDetailsAction */
    public ConsultantDetailsAction() {
    }
    
    /** The yearsOfExperience is Useful to get a years of experience of the Consultant */
    private String yearsOfExperience;
    
    /** The skillSet is Useful to get a different technical skills of the Consultant */
    private String skillSet;
    
    /** The skillSetUtilized is Useful to get a skills utilized by the Consultant */
    private String skillSetUtilized;
    
    /** The projectInfo is Useful to get a project information */
    private String projectInfo;
    
    /** The id  is Useful to get a skills table id */
    private int id;
    
    /** The empId  is Useful to get a id of the employee */
    private int empId;
    
    /** The actionType  is Useful to get a actionType */
    private String actionType;
    
    /** The consultantId  is Useful to get a id of the consultant */
    private String consultantId;
    
    private String consultantName;
    
    private DataSourceDataProvider datasourceDataProvider;
    
    /**
     *  The skillBean is bean
     * object  to persist the data.
     */
    private ConsultantSkillBean skillBean;
    
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
     * @link #HttpServletRequest
     * }
     * </code>.
     */
    private HttpServletRequest request;
    
    
    
    /**
     *  The consultantDetails is bean
     * object  to persist the data.
     */
    private ConsultantVTO consultantDetails;
    private String submitFrom;
    private int userRoleId;
    private String reqList;
    /** This Method is useful to view the ConsultantDetailsView screen
     *
     * @ see com.mss.mirage.recruitment.consultantdetails.consultantVTO
     *
     *
     * @throws NullPointerException
     *         If a NullPointerException exists and its <code>{
     * @link   java.lang.NullPointerException
     * }</code>
     *
     *@ return String SUCCESS-its returns SUCCESS when the ConsultantDetailsView screen getting successfully.
     */
    public String viewConsultant(){
        
        resultType = LOGIN;
        if(request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("VIEW_CONSULTANT_RECRUITMENT_CONSULTANT",userRoleId)){
                try{
                    setConsultantDetails(ServiceLocator.getConsultantDetailsService().getConsultantDetails(Integer.parseInt(getConsultantId())));
                    request.getSession(true).setAttribute("ConsultVTO",getConsultantDetails());
                    
                    
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    request.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    /** doAdd method for  add a consultant skills
     *
     * This method is using for add a details of the new consultant
     *
     * @throws NullPointerException
     *         If a NullPointerException exists and its <code>{
     * @link   java.lang.NullPointerException}</code>
     *
     *
     *@ return  String SUCCESS-if all the consultant details are inserted into a database successfully.
     *
     *
     */
    
    public String doAdd(){
        
        resultType = LOGIN;
        boolean isAdd=false;
        if(request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_RECRUITMENT_CONSULTANT",userRoleId)){
                try{
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        isAdd=ServiceLocator.getConsultantDetailsService().addConsultantSkills(this);
                    }
                    /** To view the consultant details on consultantDetailsView.jsp after inserting all the consultant details into database.
                     *
                     *@see com.mss.mirage.recruitment.consultantdetails.ConsultantVTO.
                     */
                    setConsultantDetails( (ConsultantVTO)request.getSession(false).getAttribute("ConsultVTO"));
                    ConsultantSkillBean bean=new ConsultantSkillBean();
                    bean.setActionName("consultantSkills");
                    bean.setEmpId(getEmpId());
                    
                    setSkillBean(bean);
                    
                    request.getSession(false).setAttribute("reqList",reqList);
                    setConsultantName(datasourceDataProvider.getInstance().getConsultantName(getEmpId()));
                    resultMessage = "<font color=\"green\" size=\"1.5\">Skill Set has been Added Successfully!</font>";
                    
                    
                    request.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    request.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                    resultMessage = "<font color=\"red\" size=\"1.5\">Please Try Again</font>";
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    /**
     * This method is useful to view the a consultant details screen.
     *
     * @throws NullPointerException
     *         If a NullPointerException exists and its <code>{
     * @link   java.lang.NullPointerException}</code>
     *
     *@ return String SUCCESS-its returs SUCCESS when we get a consultant details screen.
     * Consultant Skills Adding method.
     */
    public String viewConsultantSkill(){
        
        resultType = LOGIN;
        if(request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("VIEW_SKILLS_RECRUITMENT_CONSULTANT",userRoleId)){
                try{
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    ConsultantSkillBean bean=new ConsultantSkillBean();
                    bean.setActionName("consultantSkills");
                    bean.setEmpId(Integer.parseInt(getConsultantId()));
                    setReqList(getReqList());
                    setSkillBean(bean);
                        request.getSession(false).setAttribute("reqList",getReqList());
                    
                    setConsultantName(datasourceDataProvider.getInstance().getConsultantName(Integer.parseInt(getConsultantId())));
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    request.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    
    public String consultantResume(){
        resultType = LOGIN;
        if(request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("VIEW_SKILLS_RECRUITMENT_CONSULTANT",userRoleId)){
                try{
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    ConsultantSkillBean bean=new ConsultantSkillBean();
                   // bean.setActionName("consultantSkills");
                    bean.setEmpId(Integer.parseInt(getConsultantId()));
                    setReqList(getReqList());
                    setSkillBean(bean);
                        request.getSession(false).setAttribute("reqList",getReqList());
                    
                    setConsultantName(datasourceDataProvider.getInstance().getConsultantName(Integer.parseInt(getConsultantId())));
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    request.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    
    
    
    
    
    /**
     * This method is used for get a consultant skills from
     *  the data base tables.
     *
     *
     * @throws NullPointerException
     *         If a NullPointerException exists and its <code>
     * {
     * @link java.lang.NullPointerException
     * }</code>
     *
     * @ return  String SUCCESS-its returns SUCCESS when the consultant skills get from the database successfully.
     *  This Method is for populating Skill set Fiels for Edit.
     */
    public String doGet(){
        
        resultType = LOGIN;
        if(request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_GET_RECRUITMENT_CONSULTANT",userRoleId)){
                try{
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                  //  System.out.println("getConsultantId()"+getConsultantId());
                    
                    setSkillBean(ServiceLocator.getConsultantDetailsService().getConsultantDetailsAction(getId()));
                    setConsultantName(datasourceDataProvider.getInstance().getConsultantName(Integer.parseInt(getConsultantId())));
                    
                    // System.out.println("ConsultantDetailsAction reqList --->"+request.getSession(false).getAttribute("skillUniqueID"));
                    
                    if(request.getSession(false).getAttribute("skillUniqueID")!=null){
                     //if(getReqList()!=null){
                    setReqList(request.getSession(false).getAttribute("skillUniqueID").toString());
                    
                       //  request.getSession(false).removeAttribute("skillEdit");
                    //System.out.println("ConsultantDetailsAction reqList --->"+);
                    }
                    else
                        setReqList("-1");
                    

                    //setReqList("-1");
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    request.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    /**
     * This method is used for edit  a consultant skills
     *
     * @throws NullPointerException
     *         If a NullPointerException exists and its <code>
     * {
     * @link java.lang.NullPointerException
     * }</code>
     *
     *
     *@ return  String SUCCESS-its returns SUCCESS when the consultant details edited successfully.
     * for updating skills set
     *
     */
    public String doEdit(){
        
        resultType = LOGIN;
        if(request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_RECRUITMENT_CONSULTANT",userRoleId)){
                try{
                    datasourceDataProvider = DataSourceDataProvider.getInstance();
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        
                        boolean isUpdate=ServiceLocator.getConsultantDetailsService().editConsultantSkills(this);
                    }
                    
                    
                    /** To view the consultant details on consultantDetailsView.jsp after editing the consultant details.
                     *
                     *@see com.mss.mirage.recruitment.consultantdetails.ConsultantVTO.
                     */
                    //System.out.println(skillBean.getEmpId()+"this is in the bean ");
                    // setConsultantId(Integer.toString());
                    setConsultantDetails( (ConsultantVTO)request.getSession(false).getAttribute("ConsultVTO"));
                    //kiran
                    ConsultantSkillBean bean=new ConsultantSkillBean();
                    bean.setEmpId(getEmpId());
                    bean.setActionName("consultantSkills");
                    setSkillBean(bean);
                    request.getSession(false).setAttribute("reqList",reqList);
                    setConsultantName(datasourceDataProvider.getInstance().getConsultantName(getEmpId()));
                    setEmpId(getEmpId());
                    resultMessage = "<font color=\"green\" size=\"1.5\">Skill Set has been Modified Successfully!</font>";
                    request.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    // kiran end
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    request.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    /**
     * This method is used for delete  a consultant skills
     *
     * @throws NullPointerException
     *         If a NullPointerException exists and its <code>
     * {
     * @link java.lang.NullPointerException
     * }
     * </code>
     *
     *
     *@ return  String SUCCESS-its returns SUCCESS when the consultant details are deleted successfully from the data base.
     *
     */
    public String doDelete(){
        
        resultType = LOGIN;
        if(request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_DELETE_RECRUITMENT_CONSULTANT",userRoleId)){
                try{
                    boolean isDelete=ServiceLocator.getConsultantDetailsService().deleteConsultantSkills(this);
                    /** To view the consultant details on consultantDetailsView.jsp after deleting  the consultant details.
                     *
                     *@see com.mss.mirage.recruitment.consultantdetails.ConsultantVTO.
                     */
                    setConsultantDetails( (ConsultantVTO)request.getSession(false).getAttribute("ConsultVTO"));
                    resultType = SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    request.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
        
    }
    
    /*** setter and getter methods for consultant skills */
    
    public String getYearsOfExperience() {
        return yearsOfExperience;
    }
    
    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    
    public String getSkillSet() {
        return skillSet;
    }
    
    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }
    
    public String getProjectInfo() {
        return projectInfo;
    }
    
    public void setProjectInfo(String projectInfo) {
        this.projectInfo = projectInfo;
    }
    
    public String getSkillSetUtilized() {
        return skillSetUtilized;
    }
    
    public void setSkillSetUtilized(String skillSetUtilized) {
        this.skillSetUtilized = skillSetUtilized;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getEmpId() {
        return empId;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public void setServletRequest(HttpServletRequest request) {
        this.setRequest(request);
    }
    
    public String getActionType() {
        return actionType;
    }
    
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    
    public HttpServletRequest getRequest() {
        return request;
    }
    
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public String getConsultantId() {
        return consultantId;
    }
    
    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }
    
    public ConsultantSkillBean getSkillBean() {
        return skillBean;
    }
    
    public void setSkillBean(ConsultantSkillBean skillBean) {
        this.skillBean = skillBean;
    }
    
    public ConsultantVTO getConsultantDetails() {
        return consultantDetails;
    }
    
    public void setConsultantDetails(ConsultantVTO consultantDetails) {
        this.consultantDetails = consultantDetails;
    }
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }
    
    public String getConsultantName() {
        return consultantName;
    }
    
    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getReqList() {
        return reqList;
    }

    public void setReqList(String reqList) {
        this.reqList = reqList;
    }
}
