/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  October 9, 2007, 3:11 PM
 *
 * Author  : MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * File    : AttachmentAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.attachments;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.Properties;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class AttachmentAction extends ActionSupport implements ServletRequestAware{
    
    private String resultType;
    private String resultMessage;
    private AttachmentService attachmentService;
    private HibernateDataProvider dataProvider;
    private String activityType;
    /** The id is used for storing the id*/
    private int id;
    /** The objectid is used for storing the objectid*/
    private int objectId;
    /** The objectid is used for storing the objectType*/
    private String objectType;
    
    private int contactId;
    private int accountId;
    private String accountName;
    
    /** The objectName is used to store the Name of the Object (i.e AccountName or Contact Name)*/
    private String objectName;
    
    /** The Name of the attachment which we are sending*/
    private String attachmentName;
    /** The location where the attachment is going to save*/
    private String attachmentLocation;
    /**The name of the File which is attached*/
    private String attachmentFileName;
    /**The current date when the file has been uploaded*/
    private Timestamp dateUploaded;
    
    /**Variables for file*/
    /**The actual file*/
    private File upload;
    /**The content type of the file*/
    private String uploadContentType;
    /**The uploaded file name*/
    private String uploadFileName;
    
    private int userRoleId;
    
    private HttpServletRequest httpServletRequest;
    
    
    /** Creates a new instance of AttachmentAction */
    public AttachmentAction() {
    }
    
    public String prepare() throws Exception {
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            dataProvider = HibernateDataProvider.getInstance();
            
            if("ContactActivity".equalsIgnoreCase(getObjectType())){
               // setContactId(dataProvider.getContactIdByActivityId(getObjectId()));
              //  if(getContactId() != 0){
                 //   setAccountId(dataProvider.getAccountIdByContactId(getContactId()));
                //}
                setObjectName(getActivityType());
            }else if("Account".equalsIgnoreCase(getObjectType())){
                setObjectName(dataProvider.getAccountName(getObjectId()));
            }
            resultType = SUCCESS;
        }
        return resultType;
    }
    
    public String execute() throws Exception {
        resultType = LOGIN;
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("CRM_ATTACHMENT",userRoleId)){
                
                resultType = prepare();
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    
    public String doUpload(){
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_UPLOAD_CRM_ATTACHMENT",userRoleId)){
                try{
                    dataProvider = HibernateDataProvider.getInstance();
                    boolean isInserted = false;
                    String generatedPath = null;
                    attachmentService = ServiceLocator.getAttachmentService();
                    generatedPath = attachmentService
                            .generatePath(Properties.getProperty(ApplicationConstants.ATTACHMENTS_PATH),getObjectType());
                    
                    File targetDirectory = new File(generatedPath+"/"+getUploadFileName());
                    
                    setAttachmentFileName(getUploadFileName());
                    setAttachmentLocation(targetDirectory.toString());
                    setDateUploaded(DateUtility.getInstance().getCurrentMySqlDateTime());
                    
                                        
                    setAccountId(dataProvider.getAccountIdByActivityId(getObjectId())); 
                    setAccountName(dataProvider.getAccountName(getAccountId()));
                    
                    if(httpServletRequest.getSession(false).getAttribute("accountName") != null){
                        httpServletRequest.getSession(false).removeAttribute("accountName");
                    }
                    httpServletRequest.getSession(false).setAttribute("accountName",getAccountName());

                    //to copy the uploaded file to filesystem
                    FileUtils.copyFile(getUpload(),targetDirectory);
                    
                    //to insert the attachment information to the tblCrmAttachments table
                    isInserted = attachmentService.insertAttachment(this);
                    
                    if(isInserted){
                        resultType = SUCCESS;
                        resultMessage = "Attachment "+getUploadFileName()+" has been successfully uploaded!";
                    }else{
                        resultType = INPUT;
                        resultMessage = "Sorry! Please Try again!";
                    }
                    
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    resultType = prepare();
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    public String doDelete(){
        
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_UPLOAD_CRM_ATTACHMENT",userRoleId)){
                try{
                    
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
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getObjectId() {
        return objectId;
    }
    
    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
    
    public String getObjectType() {
        return objectType;
    }
    
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
    
    public String getAttachmentName() {
        return attachmentName;
    }
    
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
    
    public String getAttachmentLocation() {
        return attachmentLocation;
    }
    
    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }
    
    public String getAttachmentFileName() {
        return attachmentFileName;
    }
    
    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }
    
    public Timestamp getDateUploaded() {
        return dateUploaded;
    }
    
    public void setDateUploaded(Timestamp dateUploaded) {
        this.dateUploaded = dateUploaded;
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
    
    public String getObjectName() {
        return objectName;
    }
    
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    
    public int getContactId() {
        return contactId;
    }
    
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    
    public String getActivityType() {
        return activityType;
    }
    
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
    
    public int getAccountId() {
        return accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
}
