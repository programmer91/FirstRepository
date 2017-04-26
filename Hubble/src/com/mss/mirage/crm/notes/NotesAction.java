/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  October 9, 2007, 12:21 PM
 *
 * Author  : Charan Raj Devarakonda <cdevarakonda@miraclesoft.com>
 *
 * File    : NotesAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.notes;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ExceptionToListUtility;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;


/**
 *
 * @author Charan Raj Devarakonda <cdevarakonda@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class NotesAction extends ActionSupport implements ServletRequestAware{
    
    /** The httpServletRequest is used for storing the request from the Client . */
    private HttpServletRequest httpServletRequest;
    
        
    /** The applicationDataProvider is   object reference of ApplicationDataProvider   for the accessing  CONSTANT values  . */
    private HibernateDataProvider applicationDataProvider;
    
    /** The notesService is a reference object of NotesService . */
    private NotesService notesService;
    
    
    /** The currentNoteis a reference object of  NotesVTO. */
    private NotesVTO currentNote;
    
    /** The resultType is used for storing the return type of the class . */
    private String resultType;
    
    /** The resultMessage is used for storing the message,after performing the bussiness logic . */
    private String resultMessage;
    
    /** The actionString is used for storing the action of the jsp class . */
    private String actionString ="addNote";
    
    /** The isUpdate is used for storing the boolean value after the update() . */
    private boolean isUpdate;
    
    /** The objectName is used for storing the  name othe Object. */
    private String objectName;
    
    /** The id is used for storing the primary value of the table. */
    private int id;
    
    /** The  contactId is used for storing the Client Id . */
    private int contactId;
    
    /** The accountId is used for storing the account name Id. */
    private int accountId;
    
    /** The objectId is used for storing   id of the Object. */
    private int objectId;
    
    /** The objectType is used for storing the type of the Object. */
    private String objectType;
    
    /** The noteType is used for storing the type of note. */
    private String noteType;
    
    /** The note is used for storing the note data. */
    private String note;
    
    /** The createdDate is used for storing the current Date . */
    private Timestamp createdDate;
    
    /** The noteTypeList  list is used for storing the values of the noteType . */
    private List noteTypeList = new ArrayList();
    
    private int userRoleId;
    
    private String strContactId;
    
    /** Creates a new instance of NotesAction */
    public NotesAction() {
    }
    
    /**
     * The prepare() is used for sending lookUp data  NotesAdd.jsp page.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String prepare(){
        resultType = LOGIN;
      
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_NOTE",userRoleId)){
                
                try{
                    applicationDataProvider = HibernateDataProvider.getInstance();
                    setNoteTypeList(applicationDataProvider
                            .getCrmNote(ApplicationConstants.CRM_NOTE_OPTIONS));
                    
                    if(getContactId() != 0){
                        setObjectId(getContactId());
                        setObjectType("Contact");
                        setObjectName(applicationDataProvider.getContactName(getContactId()));
                    }else if(getAccountId() != 0){
                        setObjectId(getAccountId());
                        setObjectType("Account");
                        setObjectName(applicationDataProvider.getAccountName(getAccountId()));
                        
                    }
                    httpServletRequest.setAttribute("objectName",getObjectName());
                    httpServletRequest.setAttribute("objectId",Integer.valueOf(getObjectId()));
                    httpServletRequest.setAttribute("objectType",getObjectType());
                    
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
     * The execute() is used for performing the bussiness logic of the NotesAdd.jsp page.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String execute() throws Exception {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
        }
        return  resultType;
    }
    
    /**
     * The getNotes() is used for getting th value of the Id from  the NotesAdd.jsp page.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String getNotes(){
        resultType = LOGIN;
       
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_NOTE",userRoleId)){
                try{
                    
                    notesService = ServiceLocator.getNotesService();
                    
                    setActionString("editNote");
                    
                    setCurrentNote(notesService.getNote(getId()));
                    
                    if("Account".equalsIgnoreCase(getCurrentNote().getObjectType())){
                        setAccountId(getCurrentNote().getObjectId());
                    }else if("Contact".equalsIgnoreCase(getCurrentNote().getObjectType())){
                        setContactId(getCurrentNote().getObjectId());
                    }
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
    
    /**
     * The doAdd() is used for insert the data  from  the NotesAdd.jsp page into the databasetable.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String doAdd(){
       
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_NOTE",userRoleId)){
                try{
                    notesService = ServiceLocator.getNotesService();
                    
                    setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                    
                    isUpdate = notesService.addNote(this);
                    
                    setCurrentNote(notesService.getNoteVTO(this));
                    
                    if(isUpdate){
                        resultType = SUCCESS;
                        setActionString("editNote");
                        resultMessage = "Note has been successfully inserted!";
                    }else{
                        resultType = INPUT;
                        setActionString("addNote");
                        resultMessage = "Sorry! Please Try again!";
                    }
                    
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    if("Account".equalsIgnoreCase(getObjectType())){
                        setAccountId(getObjectId());
                    }
                    if("Contact".equalsIgnoreCase(getObjectType())){
                        setContactId(getObjectId());
                    }
                    prepare();
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Sesson Checking
        
        return resultType;
    }
    
    /**
     * The doEdit() is used for update the data  from  the NotesAdd.jsp page and save update data into the databasetable.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String doEdit(){
        resultType = LOGIN;
       
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_NOTE",userRoleId)){
                try{
                notesService = ServiceLocator.getNotesService();
                setActionString("editNote");
                
                isUpdate = notesService.editNote(this);
                
                setCurrentNote(notesService.getNoteVTO(this));
                
                if(isUpdate){
                    resultType = SUCCESS;
                    
                    resultMessage = "Note has been successfully Updated!";
                }else{
                    resultType = INPUT;
                    resultMessage = "Sorry! Please Try again!";
                }
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                if("Account".equalsIgnoreCase(getObjectType())){
                    setAccountId(getObjectId());
                }
                if("Contact".equalsIgnoreCase(getObjectType())){
                    setContactId(getObjectId());
                }
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
    
    /** Handles the HTTP <code>setServletRequest</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    /**
     * Returns an Integer object that can then be used for the retreiving the data.
     * The url argument must specify an absolute {@link URL}. The name
     * argument is a specifier that is relative to the url argument.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the id at the specified URL
     * @see         Id
     */
    public int getId() {
        return id;
    }
    
    /** Handles the  <code>setId</code> method.
     * @param request id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Returns an Integer object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the contactId at the specified URL
     * @see         contactId
     */
    public int getContactId() {
        return contactId;
    }
    
    /** Handles the  <code>setContactId</code> method.
     * @param request contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    
    /**
     * Returns an Integer object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the accountId at the specified URL
     * @see         accountId
     */
    public int getAccountId() {
        return accountId;
    }
    
    /** Handles the  <code>setAccountId</code> method.
     * @param request accountId
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    /**
     * Returns an  object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the objectId at the specified URL
     * @see         objectId
     */
    public int getObjectId() {
        return objectId;
    }
    
    /** Handles the  <code>setObjectId</code> method.
     * @param request objectId
     */
    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
    
    /**
     * Returns an type of the String object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the objectType at the specified URL
     * @see         objectType
     */
    public String getObjectType() {
        return objectType;
    }
    
    /** Handles the  <code>setObjectType</code> method.
     * @param request objectType
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
    
    /**
     * Returns an String object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the noteType at the specified URL
     * @see         noteType
     */
    public String getNoteType() {
        return noteType;
    }
    
    /** Handles the  <code>setNoteType</code> method.
     * @param request noteType
     */
    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }
    
    /** Handles the  <code>setNote</code> method.
     * @param request note
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * Returns an String object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the note at the specified URL
     * @see         note
     */
    public String getNote() {
        return note;
    }
    
    /**
     * Returns an TimeStamp object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the createdDate at the specified URL
     * @see         createdDate
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    
    /** Handles the  <code>setCreatedDate</code> method.
     * @param request createdDate
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    
    /**
     * Returns an List object that can then be used for the retreiving the data from database.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the noteTypeList at the specified URL
     * @see         noteTypeList
     */
    public List getNoteTypeList() {
        return noteTypeList;
    }
    
    /** Handles the  <code>setNOteTypeList</code> method.
     * @param request noteTypeList
     */
    public void setNoteTypeList(List noteTypeList) {
        this.noteTypeList = noteTypeList;
    }
    
    /**
     * Returns an String object that can then be used for changing the action name in jsp page .
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the id at the specified URL
     * @see         Id
     */
    public String getActionString() {
        return actionString;
    }
    
    /** Handles the  <code>setActionString</code> method.
     * @param request actionString
     */
    public void setActionString(String actionString) {
        this.actionString = actionString;
    }
    
    /**
     * Returns an NotesVTO object that can then be used for the retreiving the currentNote.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the currentNote at the specified URL
     * @see         currentNote
     */
    public NotesVTO getCurrentNote() {
        return currentNote;
    }
    
    /** Handles the  <code>setCurrentNote</code> method.
     * @param request currentNote
     */
    public void setCurrentNote(NotesVTO currentNote) {
        this.currentNote = currentNote;
    }
    
    /**
     * Returns an String object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the objectName at the specified URL
     * @see         objectName
     */
    public String getObjectName() {
        return objectName;
    }
    
    /** Handles the  <code>setObjectName</code> method.
     * @param request objectName
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getStrContactId() {
        return strContactId;
    }

    public void setStrContactId(String strContactId) {
       // this.strContactId = strContactId;
        this.setContactId(Integer.parseInt(strContactId));
    }
    
}
