/*
 * BridgeConferenceVTO.java
 *
 * Created on April 3, 2008, 7:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.bridgeconference;

import java.sql.Date;

/**
 *
 * @author miracle
 */
public class BridgeConferenceVTO {
    
    private String contactNo;
    private String  bridgeNumber;
    private String engagedBy;
    private Date date;
    private String startTime;
    private String endTime;
    private String title;
    private String listOfEmailAdd;
    private String status;
    private String bridgeId;
    /** Creates a new instance of BridgeConferenceVTO */
    public BridgeConferenceVTO() {
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getBridgeNumber() {
        return bridgeNumber;
    }

    public void setBridgeNumber(String bridgeNumber) {
        this.bridgeNumber = bridgeNumber;
    }

 

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getListOfEmailAdd() {
        return listOfEmailAdd;
    }

    public void setListOfEmailAdd(String listOfEmailAdd) {
        this.listOfEmailAdd = listOfEmailAdd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  
    public String getEngagedBy() {
        return engagedBy;
    }

    public void setEngagedBy(String engagedBy) {
        this.engagedBy = engagedBy;
    }

    public String getBridgeId() {
        return bridgeId;
    }

    public void setBridgeId(String bridgeId) {
        this.bridgeId = bridgeId;
    }
    
}
