/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  January 25, 2008, 6:13 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : ScreenVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */


package com.mss.mirage.marketing;

import java.sql.Timestamp;

/**
 *
 * @author Rajanikanth Teppala<rteppala@miraclesoft.com>
 */
public class MarketingVTO {
    
    /* Creating a String accountName to store the account name */
    /**
     *
     * This variable accountName is to get/store Account Name
     */
    private String accountName;
    
    /* The String urlPath is used for storing the urlPath of the particular account */
    /**
     *
     * This variable urlPath is to get/store URL Path
     */
    private String urlPath;
    
    /* The String homePhone is used for storing the homePhone of the particular account */
    /**
     *
     * This variable homePhone is to get/store Home Phone
     */
    private String homePhone;
    
    /* The String stockSymbol is used for storing the stockSymbol of the account */
    /**
     *
     * This variable stockSymbol is to get/store Stock Symbol
     */
    private String stockSymbol;
    
    /* Creating a String lastModifyBy and store value from the login user-id in the form. */
    /**
     *
     * This variable lastModifiedBy is to get/store Last Modified By
     */
    private String lastModifyBy;
    
    /* Creating a Timestamp lastModifyDate to get date and time from the local system. */
    /**
     * 
     * This variable lastModifiedDate is to get/store Last Modified Date
     */
    private Timestamp lastModifyDate;
    
    /**
     * 
     * This variable accName is to get/store Account Name
     */
    private String accName;
    
    /**
     * 
     * This variable accDate is to get/store Account Date
     */
    private String accountDate;
    
    /**
     * 
     * This variable accId is to get/store Account Id
     */
    private int accountId;
    
    /**
     * 
     * This variable contactsId is to get/store ContactsId
     */
    private int contactsId;
    
    /**
     * 
     * This variable accountId is to get/store Account Id
     */
    private int getAccountId;
    /**
     * 
     * This variable contactName is to get/store Contact Name
     */
    private String contactName;
    /** The firstNames is used for storing the first name of particular Contact. */
    private String firstNames;
    /** The lastNames is used for storing the last name of particular Contact. */
    private String lastNames;
    /** The emails is used for storing the office email  of particular Contact. */
    private String emails;
    /** The homephone is used for storing the home Phone number  of particular Contact. */
    private String homePhoneNo;
    /** The leadSource is used for storing the Source of particular Contact. */
    private String source;
    /** The activityType is used for storing activityType. */
    private String activityType;
    /** The contactActivId is used for storing the id of Contact. */
    private int contactActivId;
    /** The accountActivId is used for storing accountID.  */
    private int accountActivId;
    /** The id used for storing id of activity. */
    private int activityId = 0;
    /** The priority is used for storing priority of activity. */
    private String priority;
    /** The campaignId is used for storing campaignId. */
    private int campaignId;
    /** The assignedToId is used for storing assignedToId. */
    private String assignedToId;
    /** The status is used for storing status of activity. */
    private String status;
    /** The dueDate is used for storing dueDate. */
    private Timestamp dueDate;
    /** The alarm is used for storing alarm. */
    private boolean alarm;
    /** The description is used for storing Description. */
    private String description;
    /** The comments is used for storing comments. */
    private String comments;
    
    /** The createdById is used for storing createdById. */
    private String createdById;
    /** The modifiedById is used for storing modifiedById. */
    private String modifiedById;
    /** The assignedById is used for storing assignedById. */
    private String assignedById;
    
    /** The createdDate is used for storing createdDate. */
    private Timestamp createdDate;
    /**  The modifiedDate is used for storing modifiedDate. */
    private Timestamp modifiedDate;
    /**  The employeeName is used for storing Employee Name. */
    private String employeeName;
    /**  The activityTypeName is used for storing activityTypeName. */
    private String activityTypeName;
    /** The id used for storing id of activity. */
    private int id;
    
    /**
     * Creates a new instance of MarketingVTO
     */
    public MarketingVTO() {
    }
    
    /**
     *
     * @return String
     */
    public String getAccountName() {
        return accountName;
    }
    
    /**
     *
     * @param accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    /**
     *
     * @return String
     */
    public String getUrlPath() {
        return urlPath;
    }
    
    /**
     *
     * @param urlPath
     */
    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
    
    /**
     *
     * @return String
     */
    public String getHomePhone() {
        return homePhone;
    }
    
    /**
     *
     * @param homePhone
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
    
    /**
     *
     * @return String
     */
    public String getStockSymbol() {
        return stockSymbol;
    }
    
    /**
     *
     * @param stockSymbol
     */
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }
    
    /**
     *
     * @return String
     */
    public String getLastModifyBy() {
        return lastModifyBy;
    }
    
    /**
     *
     * @param lastModifyBy
     */
    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }
    
    /**
     *
     * @return Timestamp
     */
    public Timestamp getLastModifyDate() {
        return lastModifyDate;
    }
    
    /**
     *
     * @param lastModifyDate
     */
    public void setLastModifyDate(Timestamp lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
    
    /**
     *
     * @return String
     */
    public String getAccName() {
        return accName;
    }
    
    /**
     *
     * @param accName
     */
    public void setAccName(String accName) {
        this.accName = accName;
    }
    
    /**
     *
     * @return String
     */
    public String getAccountDate() {
        return accountDate;
    }
    
    /**
     *
     * @param accountDate
     */
    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }
    
    /**
     *
     * @return int
     */
    public int getAccountId() {
        return accountId;
    }
    
    /**
     *
     * @param accountId
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    /**
     *
     * @return int
     */
    public int getContactsId() {
        return contactsId;
    }
    
    /**
     *
     * @param contactsId
     */
    public void setContactsId(int contactsId) {
        this.contactsId = contactsId;
    }
    
    /**
     *
     * @return int
     */
    public int getGetAccountId() {
        return getAccountId;
    }
    
    /**
     *
     * @param getAccountId
     */
    public void setGetAccountId(int getAccountId) {
        this.getAccountId = getAccountId;
    }
    
    /**
     *
     * @return String
     */
    public String getContactName() {
        return contactName;
    }
    
    /**
     *
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    /**
     *
     * @return String
     */
    public String getFirstNames() {
        return firstNames;
    }
    
    /**
     *
     * @param firstNames
     */
    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }
    
    /**
     *
     * @return String
     */
    public String getLastNames() {
        return lastNames;
    }
    
    /**
     *
     * @param lastNames
     */
    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }
    
    /**
     *
     * @return String
     */
    public String getEmails() {
        return emails;
    }
    
    /**
     *
     * @param emails
     */
    public void setEmails(String emails) {
        this.emails = emails;
    }
    
    /**
     *
     * @return String
     */
    public String getHomePhoneNo() {
        return homePhoneNo;
    }
    
    /**
     *
     * @param homePhoneNo
     */
    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }
    
    /**
     *
     * @return String
     */
    public String getSource() {
        return source;
    }
    
    /**
     *
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }
    
    /**
     *
     * @return String
     */
    public String getActivityType() {
        return activityType;
    }
    
    /**
     *
     * @param activityType
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
    
    /**
     *
     * @return int
     */
    public int getContactActivId() {
        return contactActivId;
    }
    
    /**
     *
     * @param contactActivId
     */
    public void setContactActivId(int contactActivId) {
        this.contactActivId = contactActivId;
    }
    
    /**
     *
     * @return int
     */
    public int getAccountActivId() {
        return accountActivId;
    }
    
    /**
     *
     * @param accountActivId
     */
    public void setAccountActivId(int accountActivId) {
        this.accountActivId = accountActivId;
    }
    
    /**
     *
     * @return int
     */
    public int getActivityId() {
        return activityId;
    }
    
    /**
     *
     * @param activityId
     */
    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
    
    /**
     *
     * @return String
     */
    public String getPriority() {
        return priority;
    }
    
    /**
     *
     * @param priority
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    /**
     *
     * @return int
     */
    public int getCampaignId() {
        return campaignId;
    }
    
    /**
     *
     * @param campaignId
     */
    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }
    
    /**
     *
     * @return String
     */
    public String getAssignedToId() {
        return assignedToId;
    }
    
    /**
     *
     * @param assignedToId
     */
    public void setAssignedToId(String assignedToId) {
        this.assignedToId = assignedToId;
    }
    
    /**
     *
     * @return String
     */
    public String getStatus() {
        return status;
    }
    
    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     *
     * @return boolean
     */
    public boolean isAlarm() {
        return alarm;
    }
    
    /**
     *
     * @param alarm
     */
    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }
    
    /**
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }
    
    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     *
     * @return String
     */
    public String getComments() {
        return comments;
    }
    
    /**
     *
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    /**
     *
     * @return String
     */
    public String getCreatedById() {
        return createdById;
    }
    
    /**
     *
     * @param createdById
     */
    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }
    
    /**
     *
     * @return String
     */
    public String getModifiedById() {
        return modifiedById;
    }
    
    /**
     *
     * @param modifiedById
     */
    public void setModifiedById(String modifiedById) {
        this.modifiedById = modifiedById;
    }
    
    /**
     *
     * @return String
     */
    public String getAssignedById() {
        return assignedById;
    }
    
    /**
     *
     * @param assignedById
     */
    public void setAssignedById(String assignedById) {
        this.assignedById = assignedById;
    }
    
    /**
     *
     * @return Timestamp
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    
    /**
     *
     * @param createdDate
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    
    /**
     *
     * @return Timestamp
     */
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }
    
    /**
     *
     * @param modifiedDate
     */
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    /**
     *
     * @return String
     */
    public String getEmployeeName() {
        return employeeName;
    }
    
    /**
     *
     * @param employeeName
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    /**
     *
     * @return String
     */
    public String getActivityTypeName() {
        return activityTypeName;
    }
    
    /**
     *
     * @param activityTypeName
     */
    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }
    
    /**
     *
     * @return int
     */
    public int getId() {
        return id;
    }
    
    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     *
     * @return Timestamp
     */
    public Timestamp getDueDate() {
        return dueDate;
    }
    
    /**
     *
     * @param dueDate
     */
    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }
    
}
