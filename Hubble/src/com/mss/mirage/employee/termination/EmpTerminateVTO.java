/*
 * EmpTerminateActionVTO.java
 *
 * Created on July 7, 2008, 7:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.termination;

import java.sql.Date;

/**
 *
 * @author miracle
 */
public class EmpTerminateVTO {
    
    /** Creates a new instance of EmpTerminateActionVTO */
    public EmpTerminateVTO() {
    }
    /**
     * 
     * Creating String designation is to get/store designation
     */
     private String designation;
    /**
     * 
     * Creating String teamName is to get/store Team Name
     */
    private String teamName;
    /**
     * 
     *  Creating Date dateOfJoin is to get/store Date Of Join
     */
    private Date dateOfJoin;
    /**
     * 
     * Creating Date dateOfTermination is to get/store Date Of Termination
     */
    private Date dateOfTermination;
    /**
     * 
     * Creating String reasonForTermiation is to get/store Reason For Termiation
     */
    private String resonsForTerminate;
    /**
     * 
     * Creating String itgBatch is to get/Store ITGBatch of user
     */
    private String itgBatch;
    /**
     * 
     * Creating String currStatus is to get/store Current Status of Employee
     */
    private String currStatus;
   
    /**
     * 
     * Creating int empId is to get/store Employee Id
     */
    private int empId;
    /**
     * 
     * Creating String employeeName is to get/store Name of Employee
     */
    private String employeeName;

    /**
     * 
     * @return String
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * 
     * @param designation 
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * 
     * @return String
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * 
     * @param teamName 
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

   

    /**
     * 
     * @return String
     */
    public String getResonsForTerminate() {
        return resonsForTerminate;
    }

    /**
     * 
     * @param resonsForTerminate 
     */
    public void setResonsForTerminate(String resonsForTerminate) {
        this.resonsForTerminate = resonsForTerminate;
    }

    /**
     * 
     * @return String
     */
    public String getItgBatch() {
        return itgBatch;
    }

    /**
     * 
     * @param itgBatch 
     */
    public void setItgBatch(String itgBatch) {
        this.itgBatch = itgBatch;
    }

    /**
     * 
     * @return String
     */
    public String getCurrStatus() {
        return currStatus;
    }

    /**
     * 
     * @param currStatus 
     */
    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus;
    }

   

    /**
     * 
     * @return int
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * 
     * @param empId 
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * 
     * @return String
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * 
     * @param employeeName 
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * 
     * @return Date
     */
    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    /**
     * 
     * @param dateOfJoin 
     */
    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    /**
     * 
     * @return Date
     */
    public Date getDateOfTermination() {
        return dateOfTermination;
    }

    /**
     * 
     * @param dateOfTermination 
     */
    public void setDateOfTermination(Date dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

   
    
}
