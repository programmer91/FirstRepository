/*
 * HistoryVTO.java
 *
 * Created on November 23, 2007, 6:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.general;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author miracle
 */
public class StateVTO {
    
    /** Creates a new instance of HistoryVTO */
    public StateVTO() {
    }
    
    private int id;
    private int empId;
    private String loginId;
    private String empState;
    private Date stateStartDate;
    private Date stateEndDate;
 //   private float intRatePerHour;
  //  private float invRatePerHour;
    private String skillSet;
    private Timestamp createdDate;
    private String prjName;
   // private double ctcPerYear;
    private String itgBatch;
private int utilization;
 private String createdDate1;

   private String modifiedBy;
    /**
     * The modifiedDate is used for storing modifiedDate.
     */
    private Timestamp modifiedDate;
   private String uploadFileName;
private String filepath;
private String resumeName;
private String resumePath;
    private String projectStatus;
  private int projectContactId;	

    public int getEmpId() {
        return empId;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public String getLoginId() {
        return loginId;
    }
    
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
    public String getEmpState() {
        return empState;
    }
    
    public void setEmpState(String empState) {
        this.empState = empState;
    }
    
    public Date getStateStartDate() {
        return stateStartDate;
    }
    
    public void setStateStartDate(Date stateStartDate) {
        this.stateStartDate = stateStartDate;
    }
    
    public Date getStateEndDate() {
        return stateEndDate;
    }
    
    public void setStateEndDate(Date stateEndDate) {
        this.stateEndDate = stateEndDate;
    }
    
//    public float getIntRatePerHour() {
//        return intRatePerHour;
//    }
//    
//    public void setIntRatePerHour(float intRatePerHour) {
//        this.intRatePerHour = intRatePerHour;
//    }
//    
//    public float getInvRatePerHour() {
//        return invRatePerHour;
//    }
//    
//    public void setInvRatePerHour(float invRatePerHour) {
//        this.invRatePerHour = invRatePerHour;
//    }
    
    public String getSkillSet() {
        return skillSet;
    }
    
    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }
    
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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

//    public double getCtcPerYear() {
//        return ctcPerYear;
//    }
//
//    public void setCtcPerYear(double ctcPerYear) {
//        this.ctcPerYear = ctcPerYear;
//    }

    public String getItgBatch() {
        return itgBatch;
    }

    public void setItgBatch(String itgBatch) {
        this.itgBatch = itgBatch;
    }

    /**
     * @return the utilization
     */
    public int getUtilization() {
        return utilization;
    }

    /**
     * @param utilization the utilization to set
     */
    public void setUtilization(int utilization) {
        this.utilization = utilization;
    }

    /**
     * @return the createdDate1
     */
    public String getCreatedDate1() {
        return createdDate1;
    }

    /**
     * @param createdDate1 the createdDate1 to set
     */
    public void setCreatedDate1(String createdDate1) {
        this.createdDate1 = createdDate1;
    }

    /**
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the modifiedDate
     */
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @return the uploadFileName
     */
    public String getUploadFileName() {
        return uploadFileName;
    }

    /**
     * @param uploadFileName the uploadFileName to set
     */
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    /**
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath the filepath to set
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @return the resumeName
     */
    public String getResumeName() {
        return resumeName;
    }

    /**
     * @param resumeName the resumeName to set
     */
    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    /**
     * @return the resumePath
     */
    public String getResumePath() {
        return resumePath;
    }

    /**
     * @param resumePath the resumePath to set
     */
    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    /**
     * @return the projectStatus
     */
    public String getProjectStatus() {
        return projectStatus;
    }

    /**
     * @param projectStatus the projectStatus to set
     */
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    /**
     * @return the projectContactId
     */
    public int getProjectContactId() {
        return projectContactId;
    }

    /**
     * @param projectContactId the projectContactId to set
     */
    public void setProjectContactId(int projectContactId) {
        this.projectContactId = projectContactId;
    }
    
}
