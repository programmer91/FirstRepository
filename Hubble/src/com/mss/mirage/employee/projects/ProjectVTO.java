/*
 * ProjectVTO.java
 *
 * Created on December 21, 2008, 10:32 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.projects;

import java.sql.Date;

/**
 *
 * @author miracle
 */
public class ProjectVTO {
    private String prjName;
    private String prjManagerUID;
   
    private String startDate;
    private String endDate;
    private String projectDuration;
    private String projectType;
    private String customerName;
    private int accountId;
    private int userRoleId;
    private String resultType="";
    private String empId;
    private String accName;
    /** Creates a new instance of ProjectVTO */
    public ProjectVTO() {
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getPrjManagerUID() {
        return prjManagerUID;
    }

    public void setPrjManagerUID(String prjManagerUID) {
        this.prjManagerUID = prjManagerUID;
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

    public String getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }
    
}
