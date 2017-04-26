/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   September 12, 2008, 8:55 PM
 *
 * Author  :  Hari Krishna Kondala <hkondala@miraclesoft.com>
 *
 * File    : RequirementVTO .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.requirement;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author miracle
 */
public class RequirementVTO {
    
    /** Creates a new instance of RequirementVTO */
    public RequirementVTO() {
    }
    
    private String objectId;
    
    private String state;
    
    private String city;
    
    private String country;
    
    private String comments;
    
    private String title;
    
    private String practiceId;
    
    private String startDate;
    
    private String endDate;
    
    private String contactNo;
    
    private String assignedTo;

    /*added Fields for recruitment */
     private String secondaryRecruiter;

    private String assignToTechLead;

    private String secondaryTechLead;
    /* ends */
    private int noResumes;
    
    private String functions;
    
    private String responsibilities;
    
    private String education;
    
    private String experience;
    
    private String taxTerm;
    
    private String targetRate;
    
    private String targetSalary;
    
    private String status;
    
    private String skills;
    
    private List assignedMembers;
    
    private String actionType;
    
    private String requirementId;
    
    private String consultantId;
    
    private int techRate;
    
    private String email2;
    
    private String resumeId;
    
    private String resumes;
    
    private String createdBy;
    
    private String duration;
    
    private String location;
    
    private String rejectReason;
    
    private String createdDate;
    
    private String modifiedDate;
    
    private String assignedDate;
    
    private String approvedDate;
    
    private String releasedDate;
    
    private String closedDate;
    private String region;
     private String recruiterComments;
      private String secondarySkills;
      private String availableFrom;
    private String cellPhoneNo;
    private String workAuthorization;
  private String skypeId;
private String currentLocation;
private String workAuthorizationCopyId;
private String workAuthorizationCopy;
private String onProject;
private String dlCopyAttachedId;
private String dlCopyAttached;
private String projectEnd;
private String relocation;
private String changeReason;
private String yearOfCompletion;
private String availability;
private String startDatetoUs;
private String educationDetails;
private String reference;
private String workAuthorizationCopyAttachment;
private String dlCopyAttachedAttachment;
private String consultantName;
    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getCurrentEmployer() {
        return currentEmployer;
    }

    public void setCurrentEmployer(String currentEmployer) {
        this.currentEmployer = currentEmployer;
    }

    public String getWorkAuthorization() {
        return workAuthorization;
    }

    public void setWorkAuthorization(String workAuthorization) {
        this.workAuthorization = workAuthorization;
    }
    
    private String currentEmployer;

      

    public String getRecruiterComments() {
        return recruiterComments;
    }

    public void setRecruiterComments(String recruiterComments) {
        this.recruiterComments = recruiterComments;
    }

    public String getSecondarySkills() {
        return secondarySkills;
    }

    public void setSecondarySkills(String secondarySkills) {
        this.secondarySkills = secondarySkills;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
   
    

    public String getAssignToTechLead() {
        return assignToTechLead;
    }

    public void setAssignToTechLead(String assignToTechLead) {
        this.assignToTechLead = assignToTechLead;
    }

    public String getSecondaryRecruiter() {
        return secondaryRecruiter;
    }

    public void setSecondaryRecruiter(String secondaryRecruiter) {
        this.secondaryRecruiter = secondaryRecruiter;
    }

    public String getSecondaryTechLead() {
        return secondaryTechLead;
    }

    public void setSecondaryTechLead(String secondaryTechLead) {
        this.secondaryTechLead = secondaryTechLead;
    }



    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public int getNoResumes() {
        return noResumes;
    }

    public void setNoResumes(int noResumes) {
        this.noResumes = noResumes;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getTaxTerm() {
        return taxTerm;
    }

    public void setTaxTerm(String taxTerm) {
        this.taxTerm = taxTerm;
    }

    public String getTargetRate() {
        return targetRate;
    }

    public void setTargetRate(String targetRate) {
        this.targetRate = targetRate;
    }

    public String getTargetSalary() {
        return targetSalary;
    }

    public void setTargetSalary(String targetSalary) {
        this.targetSalary = targetSalary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public List getAssignedMembers() {
        return assignedMembers;
    }

    public void setAssignedMembers(List assignedMembers) {
        this.assignedMembers = assignedMembers;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public int getTechRate() {
        return techRate;
    }

    public void setTechRate(int techRate) {
        this.techRate = techRate;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getResumes() {
        return resumes;
    }

    public void setResumes(String resumes) {
        this.resumes = resumes;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    /**
     * @return the skypeId
     */
    public String getSkypeId() {
        return skypeId;
    }

    /**
     * @param skypeId the skypeId to set
     */
    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }

    /**
     * @return the currentLocation
     */
    public String getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @param currentLocation the currentLocation to set
     */
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * @return the workAuthorizationCopyId
     */
    public String getWorkAuthorizationCopyId() {
        return workAuthorizationCopyId;
    }

    /**
     * @param workAuthorizationCopyId the workAuthorizationCopyId to set
     */
    public void setWorkAuthorizationCopyId(String workAuthorizationCopyId) {
        this.workAuthorizationCopyId = workAuthorizationCopyId;
    }

    /**
     * @return the workAuthorizationCopy
     */
    public String getWorkAuthorizationCopy() {
        return workAuthorizationCopy;
    }

    /**
     * @param workAuthorizationCopy the workAuthorizationCopy to set
     */
    public void setWorkAuthorizationCopy(String workAuthorizationCopy) {
        this.workAuthorizationCopy = workAuthorizationCopy;
    }

    /**
     * @return the onProject
     */
    public String getOnProject() {
        return onProject;
    }

    /**
     * @param onProject the onProject to set
     */
    public void setOnProject(String onProject) {
        this.onProject = onProject;
    }

    /**
     * @return the dlCopyAttachedId
     */
    public String getDlCopyAttachedId() {
        return dlCopyAttachedId;
    }

    /**
     * @param dlCopyAttachedId the dlCopyAttachedId to set
     */
    public void setDlCopyAttachedId(String dlCopyAttachedId) {
        this.dlCopyAttachedId = dlCopyAttachedId;
    }

    /**
     * @return the dlCopyAttached
     */
    public String getDlCopyAttached() {
        return dlCopyAttached;
    }

    /**
     * @param dlCopyAttached the dlCopyAttached to set
     */
    public void setDlCopyAttached(String dlCopyAttached) {
        this.dlCopyAttached = dlCopyAttached;
    }

    /**
     * @return the projectEnd
     */
    public String getProjectEnd() {
        return projectEnd;
    }

    /**
     * @param projectEnd the projectEnd to set
     */
    public void setProjectEnd(String projectEnd) {
        this.projectEnd = projectEnd;
    }

    /**
     * @return the relocation
     */
    public String getRelocation() {
        return relocation;
    }

    /**
     * @param relocation the relocation to set
     */
    public void setRelocation(String relocation) {
        this.relocation = relocation;
    }

    /**
     * @return the changeReason
     */
    public String getChangeReason() {
        return changeReason;
    }

    /**
     * @param changeReason the changeReason to set
     */
    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    /**
     * @return the yearOfCompletion
     */
    public String getYearOfCompletion() {
        return yearOfCompletion;
    }

    /**
     * @param yearOfCompletion the yearOfCompletion to set
     */
    public void setYearOfCompletion(String yearOfCompletion) {
        this.yearOfCompletion = yearOfCompletion;
    }

    /**
     * @return the availability
     */
    public String getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    /**
     * @return the startDatetoUs
     */
    public String getStartDatetoUs() {
        return startDatetoUs;
    }

    /**
     * @param startDatetoUs the startDatetoUs to set
     */
    public void setStartDatetoUs(String startDatetoUs) {
        this.startDatetoUs = startDatetoUs;
    }

    /**
     * @return the educationDetails
     */
    public String getEducationDetails() {
        return educationDetails;
    }

    /**
     * @param educationDetails the educationDetails to set
     */
    public void setEducationDetails(String educationDetails) {
        this.educationDetails = educationDetails;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return the workAuthorizationCopyAttachment
     */
    public String getWorkAuthorizationCopyAttachment() {
        return workAuthorizationCopyAttachment;
    }

    /**
     * @param workAuthorizationCopyAttachment the workAuthorizationCopyAttachment to set
     */
    public void setWorkAuthorizationCopyAttachment(String workAuthorizationCopyAttachment) {
        this.workAuthorizationCopyAttachment = workAuthorizationCopyAttachment;
    }

    /**
     * @return the dlCopyAttachedAttachment
     */
    public String getDlCopyAttachedAttachment() {
        return dlCopyAttachedAttachment;
    }

    /**
     * @param dlCopyAttachedAttachment the dlCopyAttachedAttachment to set
     */
    public void setDlCopyAttachedAttachment(String dlCopyAttachedAttachment) {
        this.dlCopyAttachedAttachment = dlCopyAttachedAttachment;
    }

    /**
     * @return the consultantName
     */
    public String getConsultantName() {
        return consultantName;
    }

    /**
     * @param consultantName the consultantName to set
     */
    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

   
        
}
