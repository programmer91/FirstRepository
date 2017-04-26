 /*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.projects
 *
 * Date    : January 01, 2008, 12:35 PM
 *
 * Author  : Arjun Sanapathi<asanapathi@miraclesoft.com>
 *           Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : IssueVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */


 
package com.mss.mirage.projects;

import java.util.Date;

/**
 *
 * @author miracle
 */
public class IssueVTO {
    
    private int id;
    
    private int projectId;
    private int subProjectId;
    private int accountId;
    
    private String issueStates;
    private String issueNames;    
    
    private String issueTypes;
    
    private int mapId;
    
    private String assignedToUIDs;   
    private Date datesCreated;
    
    private String descriptions;   
    
    private String createdBy;
    
    private String datesCreatedOne;
   
    
    /** Creates a new instance of IssueVTO */
    public IssueVTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     

    public String getIssueStates() {
        return issueStates;
    }

    public void setIssueStates(String issueStates) {
        this.issueStates = issueStates;
    }

    public String getIssueNames() {
        return issueNames;
    }

    public void setIssueNames(String issueNames) {
        this.issueNames = issueNames;
    }

    public String getIssueTypes() {
        return issueTypes;
    }

    public void setIssueTypes(String issueTypes) {
        this.issueTypes = issueTypes;
    }

     
    public Date getDatesCreated() {
        return datesCreated;
    }

    public void setDatesCreated(Date datesCreated) {
        this.datesCreated = datesCreated;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAssignedToUIDs() {
        return assignedToUIDs;
    }

    public void setAssignedToUIDs(String assignedToUIDs) {
        this.assignedToUIDs = assignedToUIDs;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(int subProjectId) {
        this.subProjectId = subProjectId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getDatesCreatedOne() {
        return datesCreatedOne;
    }

    public void setDatesCreatedOne(String datesCreatedOne) {
        this.datesCreatedOne = datesCreatedOne;
    }

    
    
}
