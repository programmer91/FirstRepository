/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment.consultantresume
 *
 * Date    :   October 17, 2007, 10:58 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantResumeAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.recruitment.attachments;
import com.mss.mirage.recruitment.consultantdetails.ConsultantVTO;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.FileUploadUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;


/**
 * The <code>ConsultantAttachmentAction</code>  class is use to attach a  Consultant resumes from
 * <i>ConsultantResume.jsp</i> page.
 * <p>
 * Then it invokes setter methods in <code>ConsultantAttachmentAction</code> class and invokes input() method
 * in <code>ConsultantAttachmentAction</code> for inserting Consultant resume in mirage.tblRecAttachments.
 * invokes doDelete()method in <code>ConsultantAttachmentAction</code> for deleting  a resume of the consultant .
 *
 *
 *
 * @author Kondaiah Adapa <a href="mailto:kadapa@miraclesoft.com">kadapa@miraclesoft.com</a>
 * @author This Class.... ENTER THE DESCRIPTION HERE
 * @version 1.0 November 01, 2006
 *
 *
 *
 * /**
 */
public class ConsultantAttachmentAction extends ActionSupport implements  ServletRequestAware{
    
    /** The File upload is useful to get a name of the upload file */
    private File upload;
    
    /** The uploadContentType is useful to get a type of the upload file*/
    private String uploadContentType;
    
    /** The uploadFileName is useful to get a upload File Name */
    private String uploadFileName;
    
    /** The requestType is useful to get a type of the request*/
    private String requestType;
    
    /** The attachmentName is useful to get a name of the attachment*/
    private String attachmentName;
    
    /** The  id is useful to get a id of the Consultant */
    private int id;
    
    /** The objectid is useful to get a object id of attachment file*/
    private String objectId;
    
    private int objectIdinInt ;
    
    /** The date is useful to get a date of the upload */
    private Timestamp date;
    
    /** The filepath is useful to get a path of the attachment file */
    private String filepath;
    
    /**
     * This variable httpServletRequest store the HttpServletRequest object reference
     * <code>{
     * @link   #HttpServletRequest
     * }</code>.
     */
    private HttpServletRequest request;
    
    /** The fileLocation is useful to get  a location of the file */
    private String fileLocation;
    
    /** The consultantId is useful to get a consulatnt id. */
    private String consultantId;
    
    /**
     *  The consultantDetails is bean
     * object  to persist the data.
     */
    private ConsultantVTO consultantDetails;
    
    /** The String submitFrom is used for getting the value from the form
     * whether the value is SEARCH OR NOT
     */
    private String submitFrom;
    
    
    /**
     * The resultType  is Useful to get the ResultType of an Action and depend on this
     * resultType the navigation of screens happens.
     */
    
    private String resultType;
    private int userRoleId;
    
    
        //new 
    
    private String reqList;
    

    public void setReqList(String reqList) {
        this.reqList = reqList;
    }

    public String getReqList() {
        return reqList;
    }
    
    
    /**
     * Creates a new instance of ConsultantAttachmentAction
     */
    public ConsultantAttachmentAction() {
    }
    
    
    /**
     * This method is using  attach a resume for consultant
     *
     * @throws NullPointerException
     * If a NullPointerException exists and its <code>{
     * @link    java.lang.NullPointerException
     * }</code>
     *
     *@ return  String SUCCESS -its returns SUCCESS when the resume attached to consultant successfully.
     *
     */
   public String input() {
        
        resultType = LOGIN;
       if(request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("INPUT_RECRUITMENT_ATTACHMENT",userRoleId)){
                try{
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        
                             String basePath = Properties.getProperty("Resume.Attachments")+ "//MirageV2//" + getRequestType();
                         String theFilePath = FileUploadUtility.getInstance().filePathGeneration(basePath);
                    String fileName = FileUploadUtility.getInstance().fileNameGeneration(getUploadFileName());
                    File theFile = new File(theFilePath + "//" + fileName);
                        setFilepath(theFile.toString());
                        /*copies the file to the destination*/
                        FileUtils.copyFile(upload,theFile);
                        boolean isInsert=ServiceLocator.getConsultantAttachmentService().insert(this);
                        /** To view the consultant details on consultantDetailsView.jsp after attaching  a resume of consultant.
                         *
                         *@see com.mss.mirage.recruitment.consultantdetails.ConsultantVTO.
                         */
                        setConsultantDetails( (ConsultantVTO)request.getSession(false).getAttribute("ConsultVTO"));
                       // System.out.println("reqList --- "+getReqList());
                        setReqList(getReqList());
                        
                    }//close for submitFrom checking
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    request.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    /*    resultType=SUCCESS;
    }
    return resultType;*/
    }	
    /**
     * This method is using  submitt a attached resume of the consultant
     *
     * @throws NullPointerException
     * If a NullPointerException exists and its <code>{
     * @link    java.lang.NullPointerException
     * }</code>
     *
     *@ return  String SUCCESS -its returns SUCCESS when the resume submitted successfully.
     *
     */
    
  public String resumesubmitt() {
        
        resultType = LOGIN;
        if(request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("RESUME_SUBMITT_RECRUITMENT_ATTACHMENT",userRoleId)){
                try{
                    
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                      
                          String basePath = Properties.getProperty("Resume.Download")+ "//MirageV2//" + getRequestType();
                         String theFilePath = FileUploadUtility.getInstance().filePathGeneration(basePath);
                    String fileName = FileUploadUtility.getInstance().fileNameGeneration(getUploadFileName());
                    File theFile = new File(theFilePath + "//" + fileName);
                        setFilepath(theFile.toString());
                        /*copies the file to the destination*/
                        FileUtils.copyFile(upload,theFile);
                        boolean isSubmitt=ServiceLocator.getConsultantAttachmentService().submitt(this);
                        /** To view the consultant details on consultantDetailsView.jsp after submitting a resume of consultant.
                         *
                         *@see com.mss.mirage.recruitment.consultantdetails.ConsultantVTO.
                         */
                        setConsultantDetails( (ConsultantVTO)request.getSession(false).getAttribute("ConsultVTO"));
                        //System.out.println("reqList --- "+getReqList());
                        setReqList(getReqList());
                    }//close for submitFrom checking
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
     * This method is using  submitt a techreview of the consultant
     *
     * @throws NullPointerException
     * If a NullPointerException exists and its <code>{
     * @link    java.lang.NullPointerException
     * }</code>
     *
     *@ return  String SUCCESS -its returns SUCCESS when the techreview submitted successfully.
     *
     */
    
    public String consultechreview(){
        
        resultType = LOGIN;
        if(request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("CONSULTANT_TECHREVIEW_RECRUITMENT_ATTACHMENT",userRoleId)){
                try{
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        
                      
                         String basePath = Properties.getProperty("Consultant.Reviews")+ "//MirageV2//" + getRequestType();
                         String theFilePath = FileUploadUtility.getInstance().filePathGeneration(basePath);
                    String fileName = FileUploadUtility.getInstance().fileNameGeneration(getUploadFileName());
                    File theFile = new File(theFilePath + "//" + fileName);
                        setFilepath(theFile.toString());
                        /*copies the file to the destination*/
                        FileUtils.copyFile(upload,theFile);
                        boolean isTechreview=ServiceLocator.getConsultantAttachmentService().techreview(this);
                        
                        /** To view the consultant details on consultantDetailsView.jsp after submitting a techreview of consultant.
                         *
                         *@see com.mss.mirage.recruitment.consultantdetails.ConsultantVTO.
                         */
                        setConsultantDetails( (ConsultantVTO)request.getSession(false).getAttribute("ConsultVTO"));
                       // System.out.println("reqList --- "+getReqList());
                        setReqList(getReqList());
                    }//close for submitFrom checking
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
     * This method is using delete a attached resume of the consultant
     *
     * @throws NullPointerException
     *         If a NullPointerException exists and its <code>{
     * @link    java.lang.NullPointerException
     * }</code>
     *
     *@ return  String SUCCESS-its returns SUCCESS when the attached resume of the consultant
     * deleted successfully from data base.
     */
    public String doDelete(){
        resultType = LOGIN;
        if(request.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(request.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_DELETE_RECRUITMENT_ATTACHMENT",userRoleId)){
                try{
                    boolean isDelete=ServiceLocator.getConsultantAttachmentService().deleteConsultantAttachmentById(this.getId());
                    
                    /** To view the consultant details on consultantDetailsView.jsp after deleting a resume of consultant.
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
    
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        request=httpServletRequest;
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
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public Timestamp getDate() {
        return date;
    }
    
    public void setDate(Timestamp date) throws ServiceLocatorException {
        
        //date=new Timestamp(new java.util.Date().getTime());
        date=DateUtility.getInstance().getCurrentMySqlDateTime();
        
        this.date=date;
        
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
    
    public int getObjectIdinInt() {
        return objectIdinInt;
    }
    
    public void setObjectIdinInt(int objectIdinInt) {
        this.objectIdinInt = objectIdinInt;
    }
    
    public String getObjectId() {
        return objectId;
    }
    
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    
    public ConsultantVTO getConsultantDetails() {
        return consultantDetails;
    }
    
    public void setConsultantDetails(ConsultantVTO consultantDetails) {
        this.consultantDetails = consultantDetails;
    }
    
    public String getConsultantId() {
        return consultantId;
    }
    
    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }
    
}
