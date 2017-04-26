/*
 * @(#)ActivityVTO.java 1.0 November 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 *
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.crm.activities
 *
 * Date    :  September 30, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : ActivityVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.crm.activities;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class ActivityVTO {

    /**
     * Internal constructor for ActivityVTO.
     */
    public ActivityVTO() {
    }
    /** The id used for storing id of activity. */
    private int id;
    /** The accountId is used for storing accountID.  */
    private int accountId;
    /** The contactId is used for storing contactId.  */
    private int contactId;
    /** The campaignId is used for storing campaignId. */
    private int campaignId;
    /** The assignedToId is used for storing assignedToId. */
    private String assignedToId;
    /** The assignedById is used for storing assignedById. */
    private String assignedById;
    /** The activityType is used for storing activityType. */
    private String activityType;
    /** The status is used for storing status of activity. */
    private String status;
    /** The priority is used for storing priority of activity. */
    private String priority;
    /** The description is used for storing description. */
    private String description;
    /** The comments is used for storing comments.  */
    private String comments;
    /** The alarm is used for storing alarm. */
    private boolean alarm;
    /** The createdDate is used for storing createdDate. */
    private Timestamp createdDate;
    /** The createdById is used for storing createdById. */
    private String createdById;
    /**  The modifiedDate is used for storing modifiedDate. */
    private Timestamp modifiedDate;
    /** The modifiedById is used for storing modifiedById. */
    private String modifiedById;
    /** The dueDate is used for storing dueDate. */
    private String dueDate;
    private String officeEmail;
    private String personalEmail;
    private List contactsIdList;
    private int oppurtunityId;
    private String practiceName;
    private String contactType;

    /**
     * The getId() is used for accessing id of Activity.
     * @ return int variable
     */
    public int getId() {
        return id;
    }

    /**
     * The setId(int id) is used for setting id of Activity.
     *
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The getAccountId() is used for accessing accountId of Activity.
     * @ return int variable
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * The setAccountId(int accountId) is used for setting AccountId of Activity.
     *
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * The getContactId() is used for accessing ContactId of Activity.
     * @ return int variable
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * The setContactId(int contactId) is used for setting contactId of Activity.
     *
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * The getCampaignId() is used for accessing campaignId of Activity.
     * @ return int variable
     */
    public int getCampaignId() {
        return campaignId;
    }

    /**
     * The setCampaignId(int campaignId) is used for setting campaignId of Activity.
     *
     */
    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * The getAssignedToId() is used for accessing assignedToId of Activity.
     * @ return String variable
     */
    public String getAssignedToId() {
        return assignedToId;
    }

    /**
     * The setAssignedToId(String assignedToId) is used for setting assignedToId of Activity.
     *
     */
    public void setAssignedToId(String assignedToId) {
        this.assignedToId = assignedToId;
    }

    /**
     * The getAssignedById() is used for accessing assignedById of Activity.
     * @ return String variable
     */
    public String getAssignedById() {
        return assignedById;
    }

    /**
     * The setAssignedById(String assignedById) is used for setting assignedById of Activity.
     *
     */
    public void setAssignedById(String assignedById) {
        this.assignedById = assignedById;
    }

    /**
     * The getActivityType() is used for accessing activityType of Activity.
     * @ return String variable
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     * The setActivityType(String activityType) is used for setting activityType of Activity.
     *
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    /**
     * The getStatus() is used for accessing status of Activity.
     * @ return String variable
     */
    public String getStatus() {
        return status;
    }

    /**
     * The setStatus(String status) is used for setting status of Activity.
     *
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * The getPriority() is used for accessing priority of Activity.
     * @ return String variable
     */
    public String getPriority() {
        return priority;
    }

    /**
     * The setPriority(String priority) is used for setting priority of Activity.
     *
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * The getDescription() is used for accessing description of Activity.
     * @ return String variable
     */
    public String getDescription() {
        return description;
    }

    /**
     * The setDescription(String description) is used for setting description of Activity.
     *
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The getComments() is used for accessing comments of Activity.
     * @ return String variable
     */
    public String getComments() {
        return comments;
    }

    /**
     * The setComments(String comments) is used for setting comments of Activity.
     *
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * The getAlarm() is used for accessing alarm of Activity.
     * @ return String variable
     */
    public boolean getAlarm() {
        return alarm;
    }

    /**
     * The setAlarm(boolean alarm) is used for setting alarm of Activity.
     *
     */
    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    /**
     * The getCreatedDate() is used for accessing createdDate of Activity.
     * @ return Timestamp variable
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * The setCreatedDate(Timestamp createdDate) is used for setting createdDate of Activity.
     *
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * The getCreatedById() is used for accessing createdById of Activity.
     * @ return String variable
     */
    public String getCreatedById() {
        return createdById;
    }

    /**
     * The setCreatedById(String createdById) is used for setting createdById of Activity.
     *
     */
    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    /**
     * The getModifiedDate() is used for accessing modifiedDate of Activity.
     * @ return Timestamp variable
     */
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    /**
     * The setModifiedDate(Timestamp modifiedDate) is used for setting modifiedDate of Activity.
     *
     */
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * The getModifiedById() is used for accessing modifiedById of Activity.
     * @ return String variable
     */
    public String getModifiedById() {
        return modifiedById;
    }

    /**
     * The setModifiedById(String modifiedById) is used for setting modifiedById of Activity.
     *
     */
    public void setModifiedById(String modifiedById) {
        this.modifiedById = modifiedById;
    }

    /**
     * The getDueDate() is used for accessing dueDate of Activity.
     * @ return String variable
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * The setDueDate(String dueDate) is used for setting dueDate of Activity.
     *
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    /**
     * @return the contactsIdList
     */
    public List getContactsIdList() {
        return contactsIdList;
    }

    /**
     * @param contactsIdList the contactsIdList to set
     */
    public void setContactsIdList(List contactsIdList) {
        this.contactsIdList = contactsIdList;
    }

    /**
     * @return the oppurtunityId
     */
    public int getOppurtunityId() {
        return oppurtunityId;
    }

    /**
     * @param oppurtunityId the oppurtunityId to set
     */
    public void setOppurtunityId(int oppurtunityId) {
        this.oppurtunityId = oppurtunityId;
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
     * @return the contactType
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * @param contactType the contactType to set
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
}
