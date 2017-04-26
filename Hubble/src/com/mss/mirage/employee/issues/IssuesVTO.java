// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 12/3/2007 10:52:16 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   IssuesVTO.java

package com.mss.mirage.employee.issues;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

public class IssuesVTO
{
     private int id;
    private String title;
    private String categoryId;
    private String department;
    private String organization;
    private String createdBy;
    private Timestamp dateCreated;
    private Timestamp dateAssigned;
    private Timestamp dateClosed;
    private String assignedToUID;
    private String status;
    private String comments;
    private int attachmentId;
    private String description;
    private String created;
    private String assigned;
    private String closed;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String requestType;
    private String attachmentName;
    private String objectid;
    private String objectType;
    private Timestamp date;
    private String filepath;
    private String fileLocation;
    private List attachedFiles;
    
    // Issue Modified
    
    private String subCategoryId;
    private String typeId;
    private String statusId;
    private String resolution;
    private String severityId;
    
    
     //Issues New Activties
    private int taskId;
    //private int ActivityId;
    private int ContactId;
    private String refName;
    private String contactName;
    private String postAssignedToUID;
    private String issueName;
    private String customerName;
    private String customerId;
    private String projectName;
    private String preAssignEmpId;
    private String perCompleted;
    private String postAssignEmpId;
    private int iFlag;
    private int activityId;
   /* public void setActivityId(int ActivityId) {
        this.ActivityId = ActivityId;
    }

    public int getActivityId() {
        return ActivityId;
    }*/
    

    public void setContactId(int ContactId) {
        this.ContactId = ContactId;
    }

    public int getContactId() {
        return ContactId;
    }
 
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }
 
    public void setRefName(String refName) {
        this.refName = refName;
    }

    public String getrefName() {
        return refName;
    }
    
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactName() {
        return contactName;
    }
   
    
    public IssuesVTO()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization(String organization)
    {
        this.organization = organization;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    public Timestamp getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateAssigned()
    {
        return dateAssigned;
    }

    public void setDateAssigned(Timestamp dateAssigned)
    {
        this.dateAssigned = dateAssigned;
    }

    public Timestamp getDateClosed()
    {
        return dateClosed;
    }

    public void setDateClosed(Timestamp dateClosed)
    {
        this.dateClosed = dateClosed;
    }

    public String getAssignedToUID()
    {
        return assignedToUID;
    }

    public void setAssignedToUID(String assignedToUID)
    {
        this.assignedToUID = assignedToUID;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public int getAttachmentId()
    {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId)
    {
        this.attachmentId = attachmentId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public File getUpload()
    {
        return upload;
    }

    public void setUpload(File upload)
    {
        this.upload = upload;
    }

    public String getUploadContentType()
    {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType)
    {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName()
    {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName)
    {
        this.uploadFileName = uploadFileName;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public void setRequestType(String requestType)
    {
        this.requestType = requestType;
    }

    public String getAttachmentName()
    {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName)
    {
        this.attachmentName = attachmentName;
    }

    public String getObjectid()
    {
        return objectid;
    }

    public void setObjectid(String objectid)
    {
        this.objectid = objectid;
    }

    public String getObjectType()
    {
        return objectType;
    }

    public void setObjectType(String objectType)
    {
        this.objectType = objectType;
    }

    public Timestamp getDate()
    {
        return date;
    }

    public void setDate(Timestamp date)
    {
        this.date = date;
    }

    public String getFilepath()
    {
        return filepath;
    }

    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }

    public String getFileLocation()
    {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation)
    {
        this.fileLocation = fileLocation;
    }

    public String getCreated()
    {
        return created;
    }

    public void setCreated(String created)
    {
        this.created = created;
    }

    public String getAssigned()
    {
        return assigned;
    }

    public void setAssigned(String assigned)
    {
        this.assigned = assigned;
    }

    public String getClosed()
    {
        return closed;
    }

    public void setClosed(String closed)
    {
        this.closed = closed;
    }

    public List getAttachedFiles()
    {
        return attachedFiles;
    }

    public void setAttachedFiles(List attachedFiles)
    {
        this.attachedFiles = attachedFiles;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getSeverityId() {
        return severityId;
    }

    public void setSeverityId(String severityId) {
        this.severityId = severityId;
    }

    public String getPostAssignedToUID() {
        return postAssignedToUID;
    }

    public void setPostAssignedToUID(String postAssignedToUID) {
        this.postAssignedToUID = postAssignedToUID;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPreAssignEmpId() {
        return preAssignEmpId;
    }

    public void setPreAssignEmpId(String preAssignEmpId) {
        this.preAssignEmpId = preAssignEmpId;
    }

    public String getPerCompleted() {
        return perCompleted;
    }

    public void setPerCompleted(String perCompleted) {
        this.perCompleted = perCompleted;
    }

    public String getPostAssignEmpId() {
        return postAssignEmpId;
    }

    public void setPostAssignEmpId(String postAssignEmpId) {
        this.postAssignEmpId = postAssignEmpId;
    }

    public int getIFlag() {
        return iFlag;
    }

    public void setIFlag(int iFlag) {
        this.iFlag = iFlag;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

   
}