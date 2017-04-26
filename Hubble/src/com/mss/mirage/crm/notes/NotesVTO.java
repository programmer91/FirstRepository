/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.crm.notes;
 *
 * Date    : October 9, 2007, 12:22 PM
 *
 * Author  : Charan Raj Devarakonda <cdevarakonda@miraclesoft.com>
 *
 * File    : NotesVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.notes;

import java.sql.Timestamp;

/**
 * The <code>NotesVTO</code>  class is used for setters  and getters  for the  field lables  details from
 * <i>NotesAdd.jsp</i> page.
 * <p>
 * Then these setters and getters  are used for  the methods in <code>NotesService</code> class and performs doEdit() method and doAdd()
 * in <code>NotesAction</code> for inserting employee details in TBLCRMNOTES table.
 *
 * @author Charan Raj Devarakonda <a href="mailto:cdevarakonda@miraclesoft.com">cdevarakonda@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.crm.notes
 * @see com.mss.mirage.crm.notes.NotesAction
 * @see com.mss.mirage.crm.notes.NotesService
 * @see com.mss.mirage.crm.notes.NotesServiceImpl
 * @see com.mss.mirage.crm.notes.NotesVTO
 */
public class NotesVTO {
    
    /** Creates a new instance of NotesVTO */
    public NotesVTO() {
    }
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
     * The url argument must specify an absolute {@link URL}. The name
     * argument is a specifier that is relative to the url argument.
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
     * The url argument must specify an absolute {@link URL}. The name
     * argument is a specifier that is relative to the url argument.
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
     * The url argument must specify an absolute {@link URL}. The name
     * argument is a specifier that is relative to the url argument.
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
     * The url argument must specify an absolute {@link URL}. The name
     * argument is a specifier that is relative to the url argument.
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
     * The url argument must specify an absolute {@link URL}. The name
     * argument is a specifier that is relative to the url argument.
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
     * The url argument must specify an absolute {@link URL}. The name
     * argument is a specifier that is relative to the url argument.
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
     * The url argument must specify an absolute {@link URL}. The name
     * argument is a specifier that is relative to the url argument.
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
}
