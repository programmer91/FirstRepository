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
 * File    : ProjectVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.projects;

import java.sql.Date;

/**
 *
 * @author miracle
 */
public class ProjectVTO {

    private int id;
    private int subProjectId;
    private int projectId;
    private int mapId;
    private String prjName;
    private String description;
    private Date startDate;
    private Date endDate;
    private int totalResources;
    private String prjManagerUID;
    private int customerId;
    private String projectType;
    private String startDateOne;
    private String endDateOne;
    private String status;
    //Dual changes
    private boolean isDualReportingRequired;
    private String preSalesMgrId;
    private String pmo;
    private int onSitePlan;
    private int onSiteActual;
    private String customer;
    private int offShorePlan;
    private int offShoreActual;
    private String costModel;
    private int nearShorePlan;
    private int nearShoreActual;
    private String sector;
    private String startDatePlan;
    private String startDateActual;
    private String complexity;
    private String priority;
    private String endDatePlan;
    private String endDateActual;
    private String comments;
    private String state;
    private String software;
    private String practice;
    private String offshoreDelLead;
    private String offshoreTechLead;
    private String onsiteLead;
    private String risk;
    private String resources;
    private String schedule;

    /** Creates a new instance of ProjectVTO */
    public ProjectVTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public int getTotalResources() {
        return totalResources;
    }

    public void setTotalResources(int totalResources) {
        this.totalResources = totalResources;
    }

    public String getPrjManagerUID() {
        return prjManagerUID;
    }

    public void setPrjManagerUID(String prjManagerUID) {
        this.prjManagerUID = prjManagerUID;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
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

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the isDualReportingRequired
     */
    public boolean getIsDualReportingRequired() {
        return isIsDualReportingRequired();
    }

    /**
     * @param isDualReportingRequired the isDualReportingRequired to set
     */
    public void setIsDualReportingRequired(boolean isDualReportingRequired) {
        this.isDualReportingRequired = isDualReportingRequired;
    }

    /**
     * @return the preSalesMgrId
     */
    public String getPreSalesMgrId() {
        return preSalesMgrId;
    }

    /**
     * @param preSalesMgrId the preSalesMgrId to set
     */
    public void setPreSalesMgrId(String preSalesMgrId) {
        this.preSalesMgrId = preSalesMgrId;
    }

    /**
     * @return the pmo
     */
    public String getPmo() {
        return pmo;
    }

    /**
     * @param pmo the pmo to set
     */
    public void setPmo(String pmo) {
        this.pmo = pmo;
    }

    /**
     * @return the isDualReportingRequired
     */
    public boolean isIsDualReportingRequired() {
        return isDualReportingRequired;
    }

    /**
     * @return the onSitePlan
     */
    public int getOnSitePlan() {
        return onSitePlan;
    }

    /**
     * @param onSitePlan the onSitePlan to set
     */
    public void setOnSitePlan(int onSitePlan) {
        this.onSitePlan = onSitePlan;
    }

    /**
     * @return the onSiteActual
     */
    public int getOnSiteActual() {
        return onSiteActual;
    }

    /**
     * @param onSiteActual the onSiteActual to set
     */
    public void setOnSiteActual(int onSiteActual) {
        this.onSiteActual = onSiteActual;
    }

    /**
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * @return the offShorePlan
     */
    public int getOffShorePlan() {
        return offShorePlan;
    }

    /**
     * @param offShorePlan the offShorePlan to set
     */
    public void setOffShorePlan(int offShorePlan) {
        this.offShorePlan = offShorePlan;
    }

    /**
     * @return the offShoreActual
     */
    public int getOffShoreActual() {
        return offShoreActual;
    }

    /**
     * @param offShoreActual the offShoreActual to set
     */
    public void setOffShoreActual(int offShoreActual) {
        this.offShoreActual = offShoreActual;
    }

    /**
     * @return the costModel
     */
    public String getCostModel() {
        return costModel;
    }

    /**
     * @param costModel the costModel to set
     */
    public void setCostModel(String costModel) {
        this.costModel = costModel;
    }

    /**
     * @return the nearShorePlan
     */
    public int getNearShorePlan() {
        return nearShorePlan;
    }

    /**
     * @param nearShorePlan the nearShorePlan to set
     */
    public void setNearShorePlan(int nearShorePlan) {
        this.nearShorePlan = nearShorePlan;
    }

    /**
     * @return the nearShoreActual
     */
    public int getNearShoreActual() {
        return nearShoreActual;
    }

    /**
     * @param nearShoreActual the nearShoreActual to set
     */
    public void setNearShoreActual(int nearShoreActual) {
        this.nearShoreActual = nearShoreActual;
    }

    /**
     * @return the sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * @return the startDatePlan
     */
    public String getStartDatePlan() {
        return startDatePlan;
    }

    /**
     * @param startDatePlan the startDatePlan to set
     */
    public void setStartDatePlan(String startDatePlan) {
        this.startDatePlan = startDatePlan;
    }

    /**
     * @return the startDateActual
     */
    public String getStartDateActual() {
        return startDateActual;
    }

    /**
     * @param startDateActual the startDateActual to set
     */
    public void setStartDateActual(String startDateActual) {
        this.startDateActual = startDateActual;
    }

    /**
     * @return the complexity
     */
    public String getComplexity() {
        return complexity;
    }

    /**
     * @param complexity the complexity to set
     */
    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the endDatePlan
     */
    public String getEndDatePlan() {
        return endDatePlan;
    }

    /**
     * @param endDatePlan the endDatePlan to set
     */
    public void setEndDatePlan(String endDatePlan) {
        this.endDatePlan = endDatePlan;
    }

    /**
     * @return the endDateActual
     */
    public String getEndDateActual() {
        return endDateActual;
    }

    /**
     * @param endDateActual the endDateActual to set
     */
    public void setEndDateActual(String endDateActual) {
        this.endDateActual = endDateActual;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
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
     * @return the software
     */
    public String getSoftware() {
        return software;
    }

    /**
     * @param software the software to set
     */
    public void setSoftware(String software) {
        this.software = software;
    }

    /**
     * @return the practice
     */
    public String getPractice() {
        return practice;
    }

    /**
     * @param practice the practice to set
     */
    public void setPractice(String practice) {
        this.practice = practice;
    }

    /**
     * @return the offshoreDelLead
     */
    public String getOffshoreDelLead() {
        return offshoreDelLead;
    }

    /**
     * @param offshoreDelLead the offshoreDelLead to set
     */
    public void setOffshoreDelLead(String offshoreDelLead) {
        this.offshoreDelLead = offshoreDelLead;
    }

    /**
     * @return the offshoreTechLead
     */
    public String getOffshoreTechLead() {
        return offshoreTechLead;
    }

    /**
     * @param offshoreTechLead the offshoreTechLead to set
     */
    public void setOffshoreTechLead(String offshoreTechLead) {
        this.offshoreTechLead = offshoreTechLead;
    }

    /**
     * @return the onsiteLead
     */
    public String getOnsiteLead() {
        return onsiteLead;
    }

    /**
     * @param onsiteLead the onsiteLead to set
     */
    public void setOnsiteLead(String onsiteLead) {
        this.onsiteLead = onsiteLead;
    }

    /**
     * @return the risk
     */
    public String getRisk() {
        return risk;
    }

    /**
     * @param risk the risk to set
     */
    public void setRisk(String risk) {
        this.risk = risk;
    }

    /**
     * @return the resources
     */
    public String getResources() {
        return resources;
    }

    /**
     * @param resources the resources to set
     */
    public void setResources(String resources) {
        this.resources = resources;
    }

    /**
     * @return the schedule
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
