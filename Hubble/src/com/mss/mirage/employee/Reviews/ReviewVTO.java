/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.employee.genaral
 *
 * Date    :  September 24, 2007, 10:18 PM
 *
 * Author  : Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * File    : EmployeeVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.Reviews;

import java.sql.Date;
import java.sql.Timestamp;
/*
 * @(#)EmployeeService.java 1.0 November 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 * @since 1.0
 */
public class ReviewVTO implements Comparable{
    
    private int reviewId;
    private String notes;
    private String attachmentLocation;
        private int empId;
        private String reviewType;
        private String attachmentName;
private String attachmentFileName;

private String hrComments;

private String tlComments;

private int rank;
    private String employeeName;
    private String designation;
    private String department;
    private int score;
    private int reviewsSubmitted;
    private int reviewsApproved;
    private double reviewApprovalPercentage;
    private String reportingManager;
    private String userId;
     private int reviewsRejected;
    private int reviewsPending;

private int leadReviewsApproved;
    private int hrReviewsApproved;
    private int leadReviewsRejected;
    private int hrReviewsRejected;
private String region;
private String practice;

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public String getTlComments() {
        return tlComments;
    }

    public void setTlComments(String tlComments) {
        this.tlComments = tlComments;
    }

    public String getHrComments() {
        return hrComments;
    }

    public void setHrComments(String hrComments) {
        this.hrComments = hrComments;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }
private String reviewName;
    /**
     * @return the reviewId
     */
    public int getReviewId() {
        return reviewId;
    }

    /**
     * @param reviewId the reviewId to set
     */
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the attachmentLocation
     */
    public String getAttachmentLocation() {
        return attachmentLocation;
    }

    /**
     * @param attachmentLocation the attachmentLocation to set
     */
    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }

    /**
     * @return the empId
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * @return the reviewType
     */
    public String getReviewType() {
        return reviewType;
    }

    /**
     * @param reviewType the reviewType to set
     */
    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    /**
     * @return the attachmentName
     */
    public String getAttachmentName() {
        return attachmentName;
    }

    /**
     * @param attachmentName the attachmentName to set
     */
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    /**
     * @return the attachmentFileName
     */
    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    /**
     * @param attachmentFileName the attachmentFileName to set
     */
    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    @Override
    public int compareTo(Object obj) {
        ReviewVTO reviewVTO = (ReviewVTO) obj;
        if (score == reviewVTO.score) {
            return 0;
        } else if (score < reviewVTO.score) {
            return 1;
        } else {
            return -1;
        }
    }


    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the reviewsSubmitted
     */
    public int getReviewsSubmitted() {
        return reviewsSubmitted;
    }

    /**
     * @param reviewsSubmitted the reviewsSubmitted to set
     */
    public void setReviewsSubmitted(int reviewsSubmitted) {
        this.reviewsSubmitted = reviewsSubmitted;
    }

    /**
     * @return the reviewsApproved
     */
    public int getReviewsApproved() {
        return reviewsApproved;
    }

    /**
     * @param reviewsApproved the reviewsApproved to set
     */
    public void setReviewsApproved(int reviewsApproved) {
        this.reviewsApproved = reviewsApproved;
    }

    /**
     * @return the reviewApprovalPercentage
     */
    public double getReviewApprovalPercentage() {
        return reviewApprovalPercentage;
    }

    /**
     * @param reviewApprovalPercentage the reviewApprovalPercentage to set
     */
    public void setReviewApprovalPercentage(double reviewApprovalPercentage) {
        this.reviewApprovalPercentage = reviewApprovalPercentage;
    }

    /**
     * @return the reportingManager
     */
    public String getReportingManager() {
        return reportingManager;
    }

    /**
     * @param reportingManager the reportingManager to set
     */
    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the reviewsRejected
     */
    public int getReviewsRejected() {
        return reviewsRejected;
    }

    /**
     * @param reviewsRejected the reviewsRejected to set
     */
    public void setReviewsRejected(int reviewsRejected) {
        this.reviewsRejected = reviewsRejected;
    }

    /**
     * @return the reviewsPending
     */
    public int getReviewsPending() {
        return reviewsPending;
    }

    /**
     * @param reviewsPending the reviewsPending to set
     */
    public void setReviewsPending(int reviewsPending) {
        this.reviewsPending = reviewsPending;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the leadReviewsApproved
     */
    public int getLeadReviewsApproved() {
        return leadReviewsApproved;
    }

    /**
     * @param leadReviewsApproved the leadReviewsApproved to set
     */
    public void setLeadReviewsApproved(int leadReviewsApproved) {
        this.leadReviewsApproved = leadReviewsApproved;
    }

    /**
     * @return the hrReviewsApproved
     */
    public int getHrReviewsApproved() {
        return hrReviewsApproved;
    }

    /**
     * @param hrReviewsApproved the hrReviewsApproved to set
     */
    public void setHrReviewsApproved(int hrReviewsApproved) {
        this.hrReviewsApproved = hrReviewsApproved;
    }

    /**
     * @return the leadReviewsRejected
     */
    public int getLeadReviewsRejected() {
        return leadReviewsRejected;
    }

    /**
     * @param leadReviewsRejected the leadReviewsRejected to set
     */
    public void setLeadReviewsRejected(int leadReviewsRejected) {
        this.leadReviewsRejected = leadReviewsRejected;
    }

    /**
     * @return the hrReviewsRejected
     */
    public int getHrReviewsRejected() {
        return hrReviewsRejected;
    }

    /**
     * @param hrReviewsRejected the hrReviewsRejected to set
     */
    public void setHrReviewsRejected(int hrReviewsRejected) {
        this.hrReviewsRejected = hrReviewsRejected;
    }
    
}
