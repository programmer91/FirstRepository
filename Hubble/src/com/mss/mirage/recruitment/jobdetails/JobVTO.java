/*
 * JobVTO.java
 *
 * Created on January 3, 2010, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.recruitment.jobdetails;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author miracle
 */


public class JobVTO {
    /**
     * this id is used for editing phonelog
     */
    private int JobId;
    /**
     * this variable is used to store the employee LoginId
     */
    private String employeeLoginId;
    /**
     * this variable is used to store httpServletRequest Object
     */
    
    /**
     * this variable is used to store the date object
     */
    private Date date;
    /**
     * this variable is used to store the from phone number
     */
    private String userRoleId;
    private String resultType = null;
    private String resultMessage="";
    private  String title;
    private  int number;
    private  String type;
    private  Float rate;
    private  String reqtravels;
    private  String length;
    private  String tele;
    private  String email;
    private  String city;
    private  String province;
    private  String country;
    private  String areacode;
    private  String postalcode;
    private  String skills;
    private  String desc;
    private  String notes;
    private String time;
    private String userid;
    /**
     * this is reference variable of StringBuilder object
     */
    private  StringBuilder stringBuilder;
    /**
     * this variable is used for submitting the form action
     */
    private String submitFrom;
    /**
     * this variable sets the  currentSearch action like
     * myPhonelog or teamPhonelog
     */
    private String currentSearchAction;
    /**
     * this variable is for setting the current action like
     * edit mode or add mode
     */
    private String currentAction;
    /**
     * this variable is for storing all phonelog values
     */
    private JobVTO currentJobVTO;

    public int getJobId() {
        return JobId;
    }

    public void setJobId(int JobId) {
        this.JobId = JobId;
    }

    public String getEmployeeLoginId() {
        return employeeLoginId;
    }

    public void setEmployeeLoginId(String employeeLoginId) {
        this.employeeLoginId = employeeLoginId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getReqtravels() {
        return reqtravels;
    }

    public void setReqtravels(String reqtravels) {
        this.reqtravels = reqtravels;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getCurrentSearchAction() {
        return currentSearchAction;
    }

    public void setCurrentSearchAction(String currentSearchAction) {
        this.currentSearchAction = currentSearchAction;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public JobVTO getCurrentJobVTO() {
        return currentJobVTO;
    }

    public void setCurrentJobVTO(JobVTO currentJobVTO) {
        this.currentJobVTO = currentJobVTO;
    }
    
}


