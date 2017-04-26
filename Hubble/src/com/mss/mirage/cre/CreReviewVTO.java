/*
 * CreReviewVTO.java
 *
 * Created on August 30, 2013, 5:13 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.cre;

import java.util.Map;

/**
 *
 * @author miracle
 */
public class CreReviewVTO {
    
    /** Creates a new instance of CreReviewVTO */
    public CreReviewVTO() {
    }
    private int creId;
    private int techReviewId;
    private int navId;
    private String techReviewStatus;
    private String techCreatedDate;
    private String techLead;
    private String consultantId;
    private String consultantName;
    private String techModifiedDate;
    private String techModifiedLead;
    private String techLeadComments;
     
     
    private String hrReviewStatus;
    private String hrComments;
    private String hrCreatedDate;
    private String hrLoginId;
    private int hrReviewId;
      
      private String vpReviewStatus;
    private String vpComments;
    private String vpCreatedDate;
    private String vpLoginId;
    private int vpReviewId;
    
    
     private String creLoginId;
    private int marks;
    private int totalQuestions;
    private int attemptedQuestions;
    private String dateSubmitted;
    private String examStatus;
    
    private int totalQues;
    private int totDuration;
    private int minMarks;
    
    private String examKeyId;
    private int examKey;
    
      //Added by santosh kola
    private String examTypeName;
private int examTypeId;
private Map questionVtoMap;
    private Map subTopicsMap;
    
    /*
     * New Properties For Onboard Comments
     * Date : 08/15/2014
     * Author : Santosh Kola
     */
    private String onboardComments;
    private String onboardCreatedDate;
    private String onboardLoginId;
    private int onboardReviewId;
	
    public int getTechReviewId() {
        return techReviewId;
    }

    public void setTechReviewId(int techReviewId) {
        this.techReviewId = techReviewId;
    }

    public int getNavId() {
        return navId;
    }

    public void setNavId(int navId) {
        this.navId = navId;
    }

    public String getTechReviewStatus() {
        return techReviewStatus;
    }

    public void setTechReviewStatus(String techReviewStatus) {
        this.techReviewStatus = techReviewStatus;
    }

    public String getTechCreatedDate() {
        return techCreatedDate;
    }

    public void setTechCreatedDate(String techCreatedDate) {
        this.techCreatedDate = techCreatedDate;
    }

    public String getTechLead() {
        return techLead;
    }

    public void setTechLead(String techLead) {
        this.techLead = techLead;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getTechModifiedDate() {
        return techModifiedDate;
    }

    public void setTechModifiedDate(String techModifiedDate) {
        this.techModifiedDate = techModifiedDate;
    }

    public String getTechModifiedLead() {
        return techModifiedLead;
    }

    public void setTechModifiedLead(String techModifiedLead) {
        this.techModifiedLead = techModifiedLead;
    }

    public int getCreId() {
        return creId;
    }

    public void setCreId(int creId) {
        this.creId = creId;
    }

    public String getTechLeadComments() {
        return techLeadComments;
    }

    public void setTechLeadComments(String techLeadComments) {
        this.techLeadComments = techLeadComments;
    }

    public String getHrReviewStatus() {
        return hrReviewStatus;
    }

    public void setHrReviewStatus(String hrReviewStatus) {
        this.hrReviewStatus = hrReviewStatus;
    }

    public String getHrComments() {
        return hrComments;
    }

    public void setHrComments(String hrComments) {
        this.hrComments = hrComments;
    }

    public String getHrCreatedDate() {
        return hrCreatedDate;
    }

    public void setHrCreatedDate(String hrCreatedDate) {
        this.hrCreatedDate = hrCreatedDate;
    }

    public String getHrLoginId() {
        return hrLoginId;
    }

    public void setHrLoginId(String hrLoginId) {
        this.hrLoginId = hrLoginId;
    }

    public int getHrReviewId() {
        return hrReviewId;
    }

    public void setHrReviewId(int hrReviewId) {
        this.hrReviewId = hrReviewId;
    }

    public String getVpReviewStatus() {
        return vpReviewStatus;
    }

    public void setVpReviewStatus(String vpReviewStatus) {
        this.vpReviewStatus = vpReviewStatus;
    }

    public String getVpComments() {
        return vpComments;
    }

    public void setVpComments(String vpComments) {
        this.vpComments = vpComments;
    }

    public String getVpCreatedDate() {
        return vpCreatedDate;
    }

    public void setVpCreatedDate(String vpCreatedDate) {
        this.vpCreatedDate = vpCreatedDate;
    }

    public String getVpLoginId() {
        return vpLoginId;
    }

    public void setVpLoginId(String vpLoginId) {
        this.vpLoginId = vpLoginId;
    }

    public int getVpReviewId() {
        return vpReviewId;
    }

    public void setVpReviewId(int vpReviewId) {
        this.vpReviewId = vpReviewId;
    }

    /**
     * @return the creLoginId
     */
    public String getCreLoginId() {
        return creLoginId;
    }

    /**
     * @param creLoginId the creLoginId to set
     */
    public void setCreLoginId(String creLoginId) {
        this.creLoginId = creLoginId;
    }

    /**
     * @return the marks
     */
    public int getMarks() {
        return marks;
    }

    /**
     * @param marks the marks to set
     */
    public void setMarks(int marks) {
        this.marks = marks;
    }

    /**
     * @return the totalQuestions
     */
    public int getTotalQuestions() {
        return totalQuestions;
    }

    /**
     * @param totalQuestions the totalQuestions to set
     */
    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    /**
     * @return the attemptedQuestions
     */
    public int getAttemptedQuestions() {
        return attemptedQuestions;
    }

    /**
     * @param attemptedQuestions the attemptedQuestions to set
     */
    public void setAttemptedQuestions(int attemptedQuestions) {
        this.attemptedQuestions = attemptedQuestions;
    }

    /**
     * @return the dateSubmitted
     */
    public String getDateSubmitted() {
        return dateSubmitted;
    }

    /**
     * @param dateSubmitted the dateSubmitted to set
     */
    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    /**
     * @return the examStatus
     */
    public String getExamStatus() {
        return examStatus;
    }

    /**
     * @param examStatus the examStatus to set
     */
    public void setExamStatus(String examStatus) {
        this.examStatus = examStatus;
    }

    /**
     * @return the totalQues
     */
    public int getTotalQues() {
        return totalQues;
    }

    /**
     * @param totalQues the totalQues to set
     */
    public void setTotalQues(int totalQues) {
        this.totalQues = totalQues;
    }

    /**
     * @return the totDuration
     */
    public int getTotDuration() {
        return totDuration;
    }

    /**
     * @param totDuration the totDuration to set
     */
    public void setTotDuration(int totDuration) {
        this.totDuration = totDuration;
    }

    /**
     * @return the minMarks
     */
    public int getMinMarks() {
        return minMarks;
    }

    /**
     * @param minMarks the minMarks to set
     */
    public void setMinMarks(int minMarks) {
        this.minMarks = minMarks;
    }

    /**
     * @return the examKeyId
     */
    public String getExamKeyId() {
        return examKeyId;
    }

    /**
     * @param examKeyId the examKeyId to set
     */
    public void setExamKeyId(String examKeyId) {
        this.examKeyId = examKeyId;
    }

    /**
     * @return the examKey
     */
    public int getExamKey() {
        return examKey;
    }

    /**
     * @param examKey the examKey to set
     */
    public void setExamKey(int examKey) {
        this.examKey = examKey;
    }

    /**
     * @return the examTypeName
     */
    public String getExamTypeName() {
        return examTypeName;
    }

    /**
     * @param examTypeName the examTypeName to set
     */
    public void setExamTypeName(String examTypeName) {
        this.examTypeName = examTypeName;
    }

    /**
     * @return the examTypeId
     */
    public int getExamTypeId() {
        return examTypeId;
    }

    /**
     * @param examTypeId the examTypeId to set
     */
    public void setExamTypeId(int examTypeId) {
        this.examTypeId = examTypeId;
    }

    /**
     * @return the questionVtoMap
     */
    public Map getQuestionVtoMap() {
        return questionVtoMap;
    }

    /**
     * @param questionVtoMap the questionVtoMap to set
     */
    public void setQuestionVtoMap(Map questionVtoMap) {
        this.questionVtoMap = questionVtoMap;
    }

    /**
     * @return the subTopicsMap
     */
    public Map getSubTopicsMap() {
        return subTopicsMap;
    }

    /**
     * @param subTopicsMap the subTopicsMap to set
     */
    public void setSubTopicsMap(Map subTopicsMap) {
        this.subTopicsMap = subTopicsMap;
    }

    /**
     * @return the onboardComments
     */
    public String getOnboardComments() {
        return onboardComments;
    }

    /**
     * @param onboardComments the onboardComments to set
     */
    public void setOnboardComments(String onboardComments) {
        this.onboardComments = onboardComments;
    }

    /**
     * @return the onboardCreatedDate
     */
    public String getOnboardCreatedDate() {
        return onboardCreatedDate;
    }

    /**
     * @param onboardCreatedDate the onboardCreatedDate to set
     */
    public void setOnboardCreatedDate(String onboardCreatedDate) {
        this.onboardCreatedDate = onboardCreatedDate;
    }

    /**
     * @return the onboardLoginId
     */
    public String getOnboardLoginId() {
        return onboardLoginId;
    }

    /**
     * @param onboardLoginId the onboardLoginId to set
     */
    public void setOnboardLoginId(String onboardLoginId) {
        this.onboardLoginId = onboardLoginId;
    }

    /**
     * @return the onboardReviewId
     */
    public int getOnboardReviewId() {
        return onboardReviewId;
    }

    /**
     * @param onboardReviewId the onboardReviewId to set
     */
    public void setOnboardReviewId(int onboardReviewId) {
        this.onboardReviewId = onboardReviewId;
    }
}
