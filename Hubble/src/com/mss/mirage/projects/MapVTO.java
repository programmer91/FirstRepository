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
 * File    : MapVTO.java
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


public class MapVTO {
    
    private String projectNames;
    private String subProjectNames;
    private String bussinessDomain;
    
    private String mapTools;  //    private int tool;
    private String projectManager;
    private String techLead;
    private String mapper;
    private String tester;
    
    private String customerName;
    
    private String mapName;
    private String currentState;
    private Date startDate;
    private Date endDate;
    private String description;
    
    private int mapId;
    private int accountId;
    private int projectId;
    private int subProjectId;
    private int consultantId;
    
    private String startDateOne;
    private String endDateOne;
    
    /** Creates a new instance of MapVTO */
    public MapVTO() {
    }

    
    public String getBussinessDomain() {
        return bussinessDomain;
    }

    public void setBussinessDomain(String bussinessDomain) {
        this.bussinessDomain = bussinessDomain;
    }

     
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public String getMapTools() {
        return mapTools;
    }

    public void setMapTools(String mapTools) {
        this.mapTools = mapTools;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getTechLead() {
        return techLead;
    }

    public void setTechLead(String techLead) {
        this.techLead = techLead;
    }

    public String getMapper() {
        return mapper;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    public String getSubProjectNames() {
        return subProjectNames;
    }

    public void setSubProjectNames(String subProjectNames) {
        this.subProjectNames = subProjectNames;
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
