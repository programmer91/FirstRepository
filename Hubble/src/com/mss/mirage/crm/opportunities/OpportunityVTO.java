/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.crm.opportunities;
 *
 * Date    :  October 8, 2007, 3:43 PM
 *
 * Author  : Charan Raj Devarakonda <cdevarakonda@miraclesoft.com>
 *
 * File    : OpportunityVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.opportunities;

import java.sql.Timestamp;
import java.util.List;

/**
 * The <code>OpportunityVTO</code>  class is used for  all the setters and getters values of  fieldlable
 *  which are used in  the jsp page as <i>OpportunityAdd.jsp</i>
 * <p>
 * Then these setters and getters  are used for  the methods in <code>OpportunityService</code> class and performs doEdit() method and doAdd()
 * in <code>OpportunityAction</code> for inserting employee details in TBLCRMOPPORTUNITY table.
 *
 * @author Charan Raj Devarakonda <a href="mailto:cdevarakonda@miraclesoft.com">cdevarakonda@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.crm.opportunities.OpportunityAction
 * @see com.mss.mirage.crm.opportunities.OpportunityService
 * @see com.mss.mirage.crm.opportunities.OpportunityServiceImpl
 * @see com.mss.mirage.crm.opportunities.OpportunityVTO
 */
public class OpportunityVTO {
    
    /** Creates a new instance of OpportunityVTO */
    public OpportunityVTO() {
    }
    
    
    /** The id is used for storing the primary value of the table. */
    private int id;
    
    /** The accountId is used for storing the account name Id. */
    private int accountId;
    
    /** The title is used for storeding the title of the corresponding Employee. */
    private String title;
    
    /** The description  is used for storing the description or comments of the particular employee. */
    private String description;
    
    /** The value  is used for storing the value of the particular Employee. */
    private double value;
    
    /** The type is used for storing  what type of the work is done by the particular employee. */
    private String type;
    
    /** The stage is used for storing the data in which stage the current employee is working. */
    private String stage;
    
    /** The dueDate is used for storing the due Date . */
    private String dueDate;
    
    /** The createdDate is used for storing the current Date . */
    private Timestamp createdDate;
    
    /** The insideSalesId is used for storeing the id  of the insideSales Employee. */
    private int  insideSalesId;
    
    /** The bdmId is used for storeing the id  of the bdm Employee. */
    private int bdmId;
    
    /** The regionalMgrId is used for storeing the id  of the regionalMgr Employee. */
    private int regionalMgrId;
    
    /** The vpId is used for storeing the id  of the vp Employee. */
    private int vpId;
    
    /** The offshorePMId is used for storeing the id  of the offshorePM Employee. */
    private int offshorePMId;
    
    /** The practiceMgrId is used for storeing the id  of the practiceMgr Employee. */
    private int practiceMgrId;
    
    /** The architectId is used for storeing the id  of the architect Employee. */
    private int architectId;
    
    private String practiceName;
     private String state;
     
     private int leadSourceId;
     private String contactsHidden;
     private List contactsList;
private String sviList;
private String sviNum;
    /**
     * Returns an Integer object that can then be used for the retreiving the data.
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
     * Returns an String object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * string exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the title at the specified URL
     * @see         title
     */
    public String getTitle() {
        return title;
    }
    
    
    /** Handles the  <code>setTitle</code> method.
     * @param request title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    
    /**
     * Returns an String object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * string exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the description at the specified URL
     * @see         description
     */
    public String getDescription() {
        return description;
    }
    
    
    /** Handles the  <code>setDescription</code> method.
     * @param request description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    /**
     * Returns an double object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * double exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the value at the specified URL
     * @see         value
     */
    public double getValue() {
        return value;
    }
    
    
    /** Handles the  <code>setValue</code> method.
     * @param request value
     */
    public void setValue(double value) {
        this.value = value;
    }
    
    
    /**
     * Returns an String object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * string exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the value at the specified URL
     * @see         value
     */
    public String getType() {
        return type;
    }
    
    
    /** Handles the  <code>setType</code> method.
     * @param request type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
    /**
     * Returns an String object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * string exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the stage at the specified URL
     * @see         stage
     */
    public String getStage() {
        return stage;
    }
    
    
    /** Handles the  <code>setStage</code> method.
     * @param request stage
     */
    public void setStage(String stage) {
        this.stage = stage;
    }
    
    
    /**
     * Returns an String object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * string exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the dueDate at the specified URL
     * @see         dueDate
     */
    public String getDueDate() {
        return dueDate;
    }
    
    
    /** Handles the  <code>setDueDate</code> method.
     * @param request dueDate
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    
    /**
     * Returns an Timestamp object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * Timestamp exists. When this "edit button " is clicked to edit the page ,
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
     * Returns an Integer object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the insideSalesId at the specified URL
     * @see         insideSalesId
     */
    public int getInsideSalesId() {
        return insideSalesId;
    }
    
    
    /** Handles the  <code>setInsideSalesId</code> method.
     * @param request insideSalesId
     */
    public void setInsideSalesId(int insideSalesId) {
        this.insideSalesId = insideSalesId;
    }
    
    
    /**
     * Returns an Integer object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the bdmId at the specified URL
     * @see         bdmId
     */
    public int getBdmId() {
        return bdmId;
    }
    
    
    /** Handles the  <code>setBdmId</code> method.
     * @param request bdmId
     */
    public void setBdmId(int bdmId) {
        this.bdmId = bdmId;
    }
    
    
    /**
     * Returns an Integer object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the regionalMgrId at the specified URL
     * @see         regionalMgrId
     */
    public int getRegionalMgrId() {
        return regionalMgrId;
    }
    
    
    /** Handles the  <code>setRegionalMgrId</code> method.
     * @param request regionalMgrId
     */
    public void setRegionalMgrId(int regionalMgrId) {
        this.regionalMgrId = regionalMgrId;
    }
    
    
    /**
     * Returns an Integer object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the vpId at the specified URL
     * @see         vpId
     */
    public int getVpId() {
        return vpId;
    }
    
    
    /** Handles the  <code>setVpId</code> method.
     * @param request vpId
     */
    public void setVpId(int vpId) {
        this.vpId = vpId;
    }
    
    
    /**
     * Returns an Integer object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the offshorePMId at the specified URL
     * @see         offshorePMId
     */
    public int getOffshorePMId() {
        return offshorePMId;
    }
    
    
    /** Handles the  <code>setOffshorePMId</code> method.
     * @param request offshorePMId
     */
    public void setOffshorePMId(int offshorePMId) {
        this.offshorePMId = offshorePMId;
    }
    
    
    /**
     * Returns an Integer object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the practiceMgrId at the specified URL
     * @see         practiceMgrId
     */
    public int getPracticeMgrId() {
        return practiceMgrId;
    }
    
    
    /** Handles the  <code>setPracticeMgrId</code> method.
     * @param request practiceMgrId
     */
    public void setPracticeMgrId(int practiceMgrId) {
        this.practiceMgrId = practiceMgrId;
    }
    
    /**
     * Returns an Integer object that can then be used for the retreiving the data.
     * <p>
     * This method always returns immediately, whether or not the
     * integer exists. When this "edit button " is clicked to edit the page ,
     * the data will be loaded.
     * @return      the architectId at the specified URL
     * @see         architectId
     */
    public int getArchitectId() {
        return architectId;
    }
    
    
    /** Handles the  <code>setArchitectId</code> method.
     * @param request architectId
     */
    public void setArchitectId(int architectId) {
        this.architectId = architectId;
    }

    /**
     * @return the practiceName
     */
    public String getPracticeName() {
        return practiceName;
    }

    /**
     * @param practiceName the practiceName to set
     */
    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the leadSourceId
     */
    public int getLeadSourceId() {
        return leadSourceId;
    }

    /**
     * @param leadSourceId the leadSourceId to set
     */
    public void setLeadSourceId(int leadSourceId) {
        this.leadSourceId = leadSourceId;
    }

    /**
     * @return the contactsHidden
     */
    public String getContactsHidden() {
        return contactsHidden;
    }

    /**
     * @param contactsHidden the contactsHidden to set
     */
    public void setContactsHidden(String contactsHidden) {
        this.contactsHidden = contactsHidden;
    }

    /**
     * @return the contactsList
     */
    public List getContactsList() {
        return contactsList;
    }

    /**
     * @param contactsList the contactsList to set
     */
    public void setContactsList(List contactsList) {
        this.contactsList = contactsList;
    }

    /**
     * @return the sviList
     */
    public String getSviList() {
        return sviList;
    }

    /**
     * @param sviList the sviList to set
     */
    public void setSviList(String sviList) {
        this.sviList = sviList;
    }

    /**
     * @return the sviNum
     */
    public String getSviNum() {
        return sviNum;
    }

    /**
     * @param sviNum the sviNum to set
     */
    public void setSviNum(String sviNum) {
        this.sviNum = sviNum;
    }
    
}
