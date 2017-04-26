/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 26, 2007, 12:36 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : AccountVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.accounts;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class AccountVTO {
    
    //From Session
    private String createdBy;
    private String modifiedBy;
    private String activityBY;
     
    //Default Time
    private Timestamp dateCreated;
    private Timestamp dateModified;
    private Timestamp dateLastActivity;
    
    // Form Variables
    
    private int primaryAddressId;
    private int id;

    public void setAptm(String aptm) {
        this.aptm = aptm;
    }

    public String getAptm() {
        return aptm;
    }
    //new
    private String aptm;
    
    private String accountTeam;
    private String accountTeamName;
    
    private String accountType;
    private String accountName;
    private String synonyms;
    
    private String region;
    private String territory;
    private String industry;
    private String status;
    
    private String stockSymbol1;
    private String stockSymbol2;
    
    private double revenues;
    private int noOfEmployees;
    private double itBudget;
    
    private String taxId;
    private String leadSource;
    
    private String addressType;
    private String city;
    private String state;
    private String country;
    private String mailStop;
    private String zip;
    private String addressLine1;
    private String addressLine2;
    
    
    private String phone;
    private String fax;
    private String url;
    
    private String ibmPPANo;
    private String ibmSiteNo;
    private Date dateOfPPARenewal;
    
    private String description;
    
     private String practice;
    
    private int priority;
    
     private int b2bPriority;
    private int bpmPriority;
    private int sapPriority;
    private int ecomPriority;
    private int qaPriority;
    private int mainPriority;
    /** Creates a new instance of AccountVTO */
    public AccountVTO() {
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public int getPrimaryAddressId() {
        return primaryAddressId;
    }

    public void setPrimaryAddressId(int primaryAddressId) {
        this.primaryAddressId = primaryAddressId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountTeam() {
        return accountTeam;
    }

    public void setAccountTeam(String accountTeam) {
        this.accountTeam = accountTeam;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStockSymbol1() {
        return stockSymbol1;
    }

    public void setStockSymbol1(String stockSymbol1) {
        this.stockSymbol1 = stockSymbol1;
    }

    public String getStockSymbol2() {
        return stockSymbol2;
    }

    public void setStockSymbol2(String stockSymbol2) {
        this.stockSymbol2 = stockSymbol2;
    }

    public double getRevenues() {
        return revenues;
    }

    public void setRevenues(double revenues) {
        this.revenues = revenues;
    }

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    public double getItBudget() {
        return itBudget;
    }

    public void setItBudget(double itBudget) {
        this.itBudget = itBudget;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMailStop() {
        return mailStop;
    }

    public void setMailStop(String mailStop) {
        this.mailStop = mailStop;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivityBY() {
        return activityBY;
    }

    public void setActivityBY(String activityBY) {
        this.activityBY = activityBY;
    }

    public Timestamp getDateLastActivity() {
        return dateLastActivity;
    }

    public void setDateLastActivity(Timestamp dateLastActivity) {
        this.dateLastActivity = dateLastActivity;
    }

    public String getAccountTeamName() {
        return accountTeamName;
    }

    public void setAccountTeamName(String accountTeamName) {
        this.accountTeamName = accountTeamName;
    }

  

    public Date getDateOfPPARenewal() {
        return dateOfPPARenewal;
    }

    public void setDateOfPPARenewal(Date dateOfPPARenewal) {
        this.dateOfPPARenewal = dateOfPPARenewal;
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
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return the b2bPriority
     */
    public int getB2bPriority() {
        return b2bPriority;
    }

    /**
     * @param b2bPriority the b2bPriority to set
     */
    public void setB2bPriority(int b2bPriority) {
        this.b2bPriority = b2bPriority;
    }

    /**
     * @return the bpmPriority
     */
    public int getBpmPriority() {
        return bpmPriority;
    }

    /**
     * @param bpmPriority the bpmPriority to set
     */
    public void setBpmPriority(int bpmPriority) {
        this.bpmPriority = bpmPriority;
    }

    /**
     * @return the sapPriority
     */
    public int getSapPriority() {
        return sapPriority;
    }

    /**
     * @param sapPriority the sapPriority to set
     */
    public void setSapPriority(int sapPriority) {
        this.sapPriority = sapPriority;
    }

    /**
     * @return the ecomPriority
     */
    public int getEcomPriority() {
        return ecomPriority;
    }

    /**
     * @param ecomPriority the ecomPriority to set
     */
    public void setEcomPriority(int ecomPriority) {
        this.ecomPriority = ecomPriority;
    }

    /**
     * @return the qaPriority
     */
    public int getQaPriority() {
        return qaPriority;
    }

    /**
     * @param qaPriority the qaPriority to set
     */
    public void setQaPriority(int qaPriority) {
        this.qaPriority = qaPriority;
    }

    public int getMainPriority() {
        return mainPriority;
    }

    public void setMainPriority(int mainPriority) {
        this.mainPriority = mainPriority;
    }

    /**
     * @return the ibmPPANo
     */
    public String getIbmPPANo() {
        return ibmPPANo;
    }

    /**
     * @param ibmPPANo the ibmPPANo to set
     */
    public void setIbmPPANo(String ibmPPANo) {
        this.ibmPPANo = ibmPPANo;
    }

    /**
     * @return the ibmSiteNo
     */
    public String getIbmSiteNo() {
        return ibmSiteNo;
    }

    /**
     * @param ibmSiteNo the ibmSiteNo to set
     */
    public void setIbmSiteNo(String ibmSiteNo) {
        this.ibmSiteNo = ibmSiteNo;
    }
    
}
