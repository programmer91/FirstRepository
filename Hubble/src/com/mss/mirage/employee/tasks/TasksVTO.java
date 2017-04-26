// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 12/3/2007 10:52:16 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   IssuesVTO.java
package com.mss.mirage.employee.tasks;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class TasksVTO {

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
    private String days;
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
    private Map projectNamesMap;
    private String projectId;
    private String priority;
    private String type;
    private String issueType;
    private String primaryAssignTo;
    private String secondaryAssignTo;
    private String priorityHubble;
    private String issueTypeHubble;
    private String titleHubble;
    private String primaryAssignToHubble;
    private String secondaryAssignToHubble;
    private String commentsHubble;
    private String priorityProject;
    private String issueTypeProject;
    private String titleProject;
    private String primaryAssignToProject;
    private String secondaryAssignToProject;
    private String commentsProject;
    private String priorityNetwork;
    private String issueTypeNetwork;
    private String titleNetwork;
    private String primaryAssignToNetwork;
    private String secondaryAssignToNetwork;
    private String commentsNetwork;
    private String priorityInfra;
    private String issueTypeInfra;
    private String titleInfra;
    private String primaryAssignToInfra;
    private String secondaryAssignToInfra;
    private String commentsInfra;
    private Map primaryAssignToMap;
    private Map secondaryAssignToMap;
    
    
   
   
    
    
    
    /* public void setActivityId(int ActivityId) {
    this.ActivityId = ActivityId;
    }
    
    public int getActivityId() {
    return ActivityId;
    }*/
    private int repeat;
    private Map primaryAssignToMapForProject;
    private Map secondaryAssignToMapForProject;
    private Map primaryAssignToMapForHubble;
    private Map secondaryAssignToMapForHubble;
    private Map primaryAssignToMapForNetwork;
    private Map secondaryAssignToMapForNetwork;
    private Map primaryAssignToMapForInfra;
    private Map secondaryAssignToMapForInfra;
    private String resourceType;
    private String secondaryAssignToLoginId;
    private String secondaryAssignToLoginIdForHubble;
    private String secondaryAssignToLoginIdForProject;
    private String secondaryAssignToLoginIdForNetwork;
    private String secondaryAssignToLoginIdForInfra;
    private String resolutionInfra;
    private String resolutionNetwork;
    private String resolutionProject;
    private String resolutionHubble;
    private String empType;
    
    
      private String priorityOthers;
    private String issueTypeOthers;
    private String titleOthers;
    private String primaryAssignToforOthers;
    private String primaryAssignToLoginIdforOthers;
     private String resolutionOthers;
     private String commentsOthers;
      private String secondaryAssignToLoginIdForOthers;
       private String secondaryAssignToOthers;
       
       private String durationTotheTask;
       private String bridgeCode;
    public void setContactId(int ContactId) {
        this.ContactId = ContactId;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
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

    public TasksVTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Timestamp dateAssigned) {
        this.setDateAssigned(dateAssigned);
    }

    public Timestamp getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Timestamp dateClosed) {
        this.setDateClosed(dateClosed);
    }

    public String getAssignedToUID() {
        return assignedToUID;
    }

    public void setAssignedToUID(String assignedToUID) {
        this.assignedToUID = assignedToUID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public List getAttachedFiles() {
        return attachedFiles;
    }

    public void setAttachedFiles(List attachedFiles) {
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

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public Map getProjectNamesMap() {
        return projectNamesMap;
    }

    public void setProjectNamesMap(Map projectNamesMap) {
        this.projectNamesMap = projectNamesMap;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getPrimaryAssignTo() {
        return primaryAssignTo;
    }

    public void setPrimaryAssignTo(String primaryAssignTo) {
        this.primaryAssignTo = primaryAssignTo;
    }

    public String getSecondaryAssignTo() {
        return secondaryAssignTo;
    }

    public void setSecondaryAssignTo(String secondaryAssignTo) {
        this.secondaryAssignTo = secondaryAssignTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommentsHubble() {
        return commentsHubble;
    }

    public void setCommentsHubble(String commentsHubble) {
        this.commentsHubble = commentsHubble;
    }

    public String getCommentsInfra() {
        return commentsInfra;
    }

    public void setCommentsInfra(String commentsInfra) {
        this.commentsInfra = commentsInfra;
    }

    public String getCommentsNetwork() {
        return commentsNetwork;
    }

    public void setCommentsNetwork(String commentsNetwork) {
        this.commentsNetwork = commentsNetwork;
    }

    public String getCommentsProject() {
        return commentsProject;
    }

    public void setCommentsProject(String commentsProject) {
        this.commentsProject = commentsProject;
    }

    public String getIssueTypeHubble() {
        return issueTypeHubble;
    }

    public void setIssueTypeHubble(String issueTypeHubble) {
        this.issueTypeHubble = issueTypeHubble;
    }

    public String getIssueTypeInfra() {
        return issueTypeInfra;
    }

    public void setIssueTypeInfra(String issueTypeInfra) {
        this.issueTypeInfra = issueTypeInfra;
    }

    public String getIssueTypeNetwork() {
        return issueTypeNetwork;
    }

    public void setIssueTypeNetwork(String issueTypeNetwork) {
        this.issueTypeNetwork = issueTypeNetwork;
    }

    public String getIssueTypeProject() {
        return issueTypeProject;
    }

    public void setIssueTypeProject(String issueTypeProject) {
        this.issueTypeProject = issueTypeProject;
    }

    public String getPrimaryAssignToHubble() {
        return primaryAssignToHubble;
    }

    public void setPrimaryAssignToHubble(String primaryAssignToHubble) {
        this.primaryAssignToHubble = primaryAssignToHubble;
    }

    public String getPrimaryAssignToInfra() {
        return primaryAssignToInfra;
    }

    public void setPrimaryAssignToInfra(String primaryAssignToInfra) {
        this.primaryAssignToInfra = primaryAssignToInfra;
    }

    public String getPrimaryAssignToNetwork() {
        return primaryAssignToNetwork;
    }

    public void setPrimaryAssignToNetwork(String primaryAssignToNetwork) {
        this.primaryAssignToNetwork = primaryAssignToNetwork;
    }

    public String getPrimaryAssignToProject() {
        return primaryAssignToProject;
    }

    public void setPrimaryAssignToProject(String primaryAssignToProject) {
        this.primaryAssignToProject = primaryAssignToProject;
    }

    public String getPriorityHubble() {
        return priorityHubble;
    }

    public void setPriorityHubble(String priorityHubble) {
        this.priorityHubble = priorityHubble;
    }

    public String getPriorityInfra() {
        return priorityInfra;
    }

    public void setPriorityInfra(String priorityInfra) {
        this.priorityInfra = priorityInfra;
    }

    public String getPriorityNetwork() {
        return priorityNetwork;
    }

    public void setPriorityNetwork(String priorityNetwork) {
        this.priorityNetwork = priorityNetwork;
    }

    public String getPriorityProject() {
        return priorityProject;
    }

    public void setPriorityProject(String priorityProject) {
        this.priorityProject = priorityProject;
    }

    public String getSecondaryAssignToHubble() {
        return secondaryAssignToHubble;
    }

    public void setSecondaryAssignToHubble(String secondaryAssignToHubble) {
        this.secondaryAssignToHubble = secondaryAssignToHubble;
    }

    public String getSecondaryAssignToInfra() {
        return secondaryAssignToInfra;
    }

    public void setSecondaryAssignToInfra(String secondaryAssignToInfra) {
        this.secondaryAssignToInfra = secondaryAssignToInfra;
    }

    public String getSecondaryAssignToNetwork() {
        return secondaryAssignToNetwork;
    }

    public void setSecondaryAssignToNetwork(String secondaryAssignToNetwork) {
        this.secondaryAssignToNetwork = secondaryAssignToNetwork;
    }

    public String getSecondaryAssignToProject() {
        return secondaryAssignToProject;
    }

    public void setSecondaryAssignToProject(String secondaryAssignToProject) {
        this.secondaryAssignToProject = secondaryAssignToProject;
    }

    public String getTitleHubble() {
        return titleHubble;
    }

    public void setTitleHubble(String titleHubble) {
        this.titleHubble = titleHubble;
    }

    public String getTitleInfra() {
        return titleInfra;
    }

    public void setTitleInfra(String titleInfra) {
        this.titleInfra = titleInfra;
    }

    public String getTitleNetwork() {
        return titleNetwork;
    }

    public void setTitleNetwork(String titleNetwork) {
        this.titleNetwork = titleNetwork;
    }

    public String getTitleProject() {
        return titleProject;
    }

    public void setTitleProject(String titleProject) {
        this.titleProject = titleProject;
    }

    public Map getPrimaryAssignToMap() {
        return primaryAssignToMap;
    }

    public void setPrimaryAssignToMap(Map primaryAssignToMap) {
        this.primaryAssignToMap = primaryAssignToMap;
    }

    public Map getSecondaryAssignToMap() {
        return secondaryAssignToMap;
    }

    public void setSecondaryAssignToMap(Map secondaryAssignToMap) {
        this.secondaryAssignToMap = secondaryAssignToMap;
    }

    public Map getPrimaryAssignToMapForProject() {
        return primaryAssignToMapForProject;
    }

    public void setPrimaryAssignToMapForProject(Map primaryAssignToMapForProject) {
        this.primaryAssignToMapForProject = primaryAssignToMapForProject;
    }

    public Map getSecondaryAssignToMapForProject() {
        return secondaryAssignToMapForProject;
    }

    public void setSecondaryAssignToMapForProject(Map secondaryAssignToMapForProject) {
        this.secondaryAssignToMapForProject = secondaryAssignToMapForProject;
    }

    public Map getPrimaryAssignToMapForHubble() {
        return primaryAssignToMapForHubble;
    }

    public void setPrimaryAssignToMapForHubble(Map primaryAssignToMapForHubble) {
        this.primaryAssignToMapForHubble = primaryAssignToMapForHubble;
    }

    public Map getSecondaryAssignToMapForHubble() {
        return secondaryAssignToMapForHubble;
    }

    public void setSecondaryAssignToMapForHubble(Map secondaryAssignToMapForHubble) {
        this.secondaryAssignToMapForHubble = secondaryAssignToMapForHubble;
    }

    public Map getPrimaryAssignToMapForInfra() {
        return primaryAssignToMapForInfra;
    }

    public void setPrimaryAssignToMapForInfra(Map primaryAssignToMapForInfra) {
        this.primaryAssignToMapForInfra = primaryAssignToMapForInfra;
    }

    public Map getPrimaryAssignToMapForNetwork() {
        return primaryAssignToMapForNetwork;
    }

    public void setPrimaryAssignToMapForNetwork(Map primaryAssignToMapForNetwork) {
        this.primaryAssignToMapForNetwork = primaryAssignToMapForNetwork;
    }

    public Map getSecondaryAssignToMapForInfra() {
        return secondaryAssignToMapForInfra;
    }

    public void setSecondaryAssignToMapForInfra(Map secondaryAssignToMapForInfra) {
        this.secondaryAssignToMapForInfra = secondaryAssignToMapForInfra;
    }

    public Map getSecondaryAssignToMapForNetwork() {
        return secondaryAssignToMapForNetwork;
    }

    public void setSecondaryAssignToMapForNetwork(Map secondaryAssignToMapForNetwork) {
        this.secondaryAssignToMapForNetwork = secondaryAssignToMapForNetwork;
    }

    public int getiFlag() {
        return iFlag;
    }

    public void setiFlag(int iFlag) {
        this.iFlag = iFlag;
    }

    public String getSecondaryAssignToLoginId() {
        return secondaryAssignToLoginId;
    }

    public void setSecondaryAssignToLoginId(String secondaryAssignToLoginId) {
        this.secondaryAssignToLoginId = secondaryAssignToLoginId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getSecondaryAssignToLoginIdForHubble() {
        return secondaryAssignToLoginIdForHubble;
    }

    public void setSecondaryAssignToLoginIdForHubble(String secondaryAssignToLoginIdForHubble) {
        this.secondaryAssignToLoginIdForHubble = secondaryAssignToLoginIdForHubble;
    }

    public String getSecondaryAssignToLoginIdForInfra() {
        return secondaryAssignToLoginIdForInfra;
    }

    public void setSecondaryAssignToLoginIdForInfra(String secondaryAssignToLoginIdForInfra) {
        this.secondaryAssignToLoginIdForInfra = secondaryAssignToLoginIdForInfra;
    }

    public String getSecondaryAssignToLoginIdForNetwork() {
        return secondaryAssignToLoginIdForNetwork;
    }

    public void setSecondaryAssignToLoginIdForNetwork(String secondaryAssignToLoginIdForNetwork) {
        this.secondaryAssignToLoginIdForNetwork = secondaryAssignToLoginIdForNetwork;
    }

    public String getSecondaryAssignToLoginIdForProject() {
        return secondaryAssignToLoginIdForProject;
    }

    public void setSecondaryAssignToLoginIdForProject(String secondaryAssignToLoginIdForProject) {
        this.secondaryAssignToLoginIdForProject = secondaryAssignToLoginIdForProject;
    }

    public String getResolutionHubble() {
        return resolutionHubble;
    }

    public void setResolutionHubble(String resolutionHubble) {
        this.resolutionHubble = resolutionHubble;
    }

    public String getResolutionInfra() {
        return resolutionInfra;
    }

    public void setResolutionInfra(String resolutionInfra) {
        this.resolutionInfra = resolutionInfra;
    }

    public String getResolutionNetwork() {
        return resolutionNetwork;
    }

    public void setResolutionNetwork(String resolutionNetwork) {
        this.resolutionNetwork = resolutionNetwork;
    }

    public String getResolutionProject() {
        return resolutionProject;
    }

    public void setResolutionProject(String resolutionProject) {
        this.resolutionProject = resolutionProject;
    }

    /**
     * @return the priorityOthers
     */
    public String getPriorityOthers() {
        return priorityOthers;
    }

    /**
     * @param priorityOthers the priorityOthers to set
     */
    public void setPriorityOthers(String priorityOthers) {
        this.priorityOthers = priorityOthers;
    }

    /**
     * @return the issueTypeOthers
     */
    public String getIssueTypeOthers() {
        return issueTypeOthers;
    }

    /**
     * @param issueTypeOthers the issueTypeOthers to set
     */
    public void setIssueTypeOthers(String issueTypeOthers) {
        this.issueTypeOthers = issueTypeOthers;
    }

    /**
     * @return the titleOthers
     */
    public String getTitleOthers() {
        return titleOthers;
    }

    /**
     * @param titleOthers the titleOthers to set
     */
    public void setTitleOthers(String titleOthers) {
        this.titleOthers = titleOthers;
    }

    /**
     * @return the primaryAssignToforOthers
     */
    public String getPrimaryAssignToforOthers() {
        return primaryAssignToforOthers;
    }

    /**
     * @param primaryAssignToforOthers the primaryAssignToforOthers to set
     */
    public void setPrimaryAssignToforOthers(String primaryAssignToforOthers) {
        this.primaryAssignToforOthers = primaryAssignToforOthers;
    }

    /**
     * @return the primaryAssignToLoginIdforOthers
     */
    public String getPrimaryAssignToLoginIdforOthers() {
        return primaryAssignToLoginIdforOthers;
    }

    /**
     * @param primaryAssignToLoginIdforOthers the primaryAssignToLoginIdforOthers to set
     */
    public void setPrimaryAssignToLoginIdforOthers(String primaryAssignToLoginIdforOthers) {
        this.primaryAssignToLoginIdforOthers = primaryAssignToLoginIdforOthers;
    }

    /**
     * @return the resolutionOthers
     */
    public String getResolutionOthers() {
        return resolutionOthers;
    }

    /**
     * @param resolutionOthers the resolutionOthers to set
     */
    public void setResolutionOthers(String resolutionOthers) {
        this.resolutionOthers = resolutionOthers;
    }

    /**
     * @return the commentsOthers
     */
    public String getCommentsOthers() {
        return commentsOthers;
    }

    /**
     * @param commentsOthers the commentsOthers to set
     */
    public void setCommentsOthers(String commentsOthers) {
        this.commentsOthers = commentsOthers;
    }

    /**
     * @return the secondaryAssignToLoginIdForOthers
     */
    public String getSecondaryAssignToLoginIdForOthers() {
        return secondaryAssignToLoginIdForOthers;
    }

    /**
     * @param secondaryAssignToLoginIdForOthers the secondaryAssignToLoginIdForOthers to set
     */
    public void setSecondaryAssignToLoginIdForOthers(String secondaryAssignToLoginIdForOthers) {
        this.secondaryAssignToLoginIdForOthers = secondaryAssignToLoginIdForOthers;
    }

    /**
     * @return the secondaryAssignToOthers
     */
    public String getSecondaryAssignToOthers() {
        return secondaryAssignToOthers;
    }

    /**
     * @param secondaryAssignToOthers the secondaryAssignToOthers to set
     */
    public void setSecondaryAssignToOthers(String secondaryAssignToOthers) {
        this.secondaryAssignToOthers = secondaryAssignToOthers;
    }

    /**
     * @return the durationTotheTask
     */
    public String getDurationTotheTask() {
        return durationTotheTask;
    }

    /**
     * @param durationTotheTask the durationTotheTask to set
     */
    public void setDurationTotheTask(String durationTotheTask) {
        this.durationTotheTask = durationTotheTask;
    }

    /**
     * @return the bridgeCode
     */
    public String getBridgeCode() {
        return bridgeCode;
    }

    /**
     * @param bridgeCode the bridgeCode to set
     */
    public void setBridgeCode(String bridgeCode) {
        this.bridgeCode = bridgeCode;
    }
    
}