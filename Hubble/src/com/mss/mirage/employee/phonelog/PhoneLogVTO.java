/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.employee.phonelog
 *
 * Date    : February 7, 2008, 7:44 PM
 *
 * Author  : Venkateswara Rao Nukala<vnukala@miraclesoft.com>
 *
 * File    : PhoneLogVTO.java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.employee.phonelog;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author miracle
 */


public class PhoneLogVTO {
    /**
     * this id is used for editing phonelog
     */
    private int phoneLogId;
    /**
     * this variable is used to store the employee LoginId
     */
    private String employeeLoginId;
    /**
     * this variable is used to store the date object
     */
    private Date date;
    /**
     * this variable is used to store the from phone number
     */
    private String fromPhoneNo;
    /**
     * this variable is used to store the startTime of the
     * phone call
     */
    private String startTime;
    /**
     * this variable is used to store the endTime of the
     * phone call
     */
    private String endTime;
    /**
     * this variable is used to store the toPhone number of the
     * phone call
     */
    private String toPhoneNo;
    /**
     * this variable is used to store the call duration of the
     * phone call
     */
    private String callDuration;
    /**
     * this variable is used to store the call type of the
     * phone call (personal or official)
     */
    private String callType;
    /**
     * this variable is used to store the description of the
     * phone call
     */
    private String description;
    /**
     * this variable is hidden
     */
    private boolean reconcilled;
    private String test;
    /**
     * this variable is used to store the result type (such as
     * success, input, error and login)
     */
    private String resultType;
    /**
     * this variable is for checking the authorization of
     * roles
     */
    
    private int userRoleId;
    /**
     * this variable is used to store the startDate of the
     * phone call
     */
    
    private String startDate;
    /**
     * this variable is used to store the endDate of the
     * phone call
     */
    private  String endDate;
    /**
     * this is reference variable of StringBuilder object
     */
    private  StringBuilder stringBuilder;
    /**
     * this variable is used for submitting the form action
     */
    private String submitFrom;
    /** Creates a new instance of PhoneLogVTO */
    public PhoneLogVTO() {
    }
    
    public int getPhoneLogId() {
        return phoneLogId;
    }
    
    public void setPhoneLogId(int phoneLogId) {
        this.phoneLogId = phoneLogId;
    }
    
    public String getEmployeeLoginId() {
        return employeeLoginId;
    }
    
    public void setEmployeeLoginId(String employeeLoginId) {
        this.employeeLoginId = employeeLoginId;
    }
    
    public String getFromPhoneNo() {
        return fromPhoneNo;
    }
    
    public void setFromPhoneNo(String fromPhoneNo) {
        this.fromPhoneNo = fromPhoneNo;
    }
    
    public String getToPhoneNo() {
        return toPhoneNo;
    }
    
    public void setToPhoneNo(String toPhoneNo) {
        this.toPhoneNo = toPhoneNo;
    }
    
    public String getCallDuration() {
        return callDuration;
    }
    
    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }
    
    public String getCallType() {
        return callType;
    }
    
    public void setCallType(String callType) {
        this.callType=callType;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isReconcilled() {
        return reconcilled;
    }
    
    public void setReconcilled(boolean reconcilled) {
        this.reconcilled = reconcilled;
    }
    
    public String getTest() {
        return test;
    }
    
    public void setTest(String test) {
        this.test = test;
    }
    
    public String getResultType() {
        return resultType;
    }
    
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
    
    public int getUserRoleId() {
        return userRoleId;
    }
    
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }
    
    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
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
    
}
