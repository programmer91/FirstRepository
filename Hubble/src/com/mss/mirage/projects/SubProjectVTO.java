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
 * File    : SubProjectVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

 
package com.mss.mirage.projects;

import java.sql.Date;

/**
 *
 *  
 */
public class SubProjectVTO {
    
    private int subProjectId;
    private int projectId;
    private String subPrjName;
    private String description;
    private Date startDate;
    private Date endDate;
    private String currentState;
    private int totalResources;
    private String teamLeadUID;
    private String projectType;
    
    private String startDateOne;
    private String endDateOne;
    
    /** Creates a new instance of SubProjectVTO */
    public SubProjectVTO() {
    }

    public int getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(int subProjectId) {
        this.subProjectId = subProjectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getSubPrjName() {
        return subPrjName;
    }

    public void setSubPrjName(String subPrjName) {
        this.subPrjName = subPrjName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public int getTotalResources() {
        return totalResources;
    }

    public void setTotalResources(int totalResources) {
        this.totalResources = totalResources;
    }

    public String getTeamLeadUID() {
        return teamLeadUID;
    }

    public void setTeamLeadUID(String teamLeadUID) {
        this.teamLeadUID = teamLeadUID;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getStartDateOne() {
        return startDateOne;
    }

    public void setStartDateOne(String startDateOne) {
        this.startDateOne = startDateOne;
    }

    public String getEndDateOne() {
        return endDateOne;
    }

    public void setEndDateOne(String endDateOne) {
        this.endDateOne = endDateOne;
    }
    
}
