/*
 * ProjIssuesVTO.java
 *
 * Created on April 23, 2008, 10:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.projects.issues;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author miracle
 */
public class ProjIssuesVTO {
    
     private int id;
    private String title;
    private String categoryId;
    private int projectId;
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
    
    private int subProjectId;
    private int projectMapId;
//    private String mapId;
//    private String issueName;
    private int accountId;
    
    private String mapNameId;
    
    /* The String projectName is used to store the name for partcular Project in the database*/
    private String projectName;
    /* The String projectName is used to store the name for partcular SubProject in the database*/
    private String subProjectName;
    /* The String mapName is used for storing the MapName of Map */
    private String mapName;
    
    /** Creates a new instance of ProjIssuesVTO */
    public ProjIssuesVTO() {
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(int subProjectId) {
        this.subProjectId = subProjectId;
    }

    public int getProjectMapId() {
        return projectMapId;
    }

    public void setProjectMapId(int projectMapId) {
        this.projectMapId = projectMapId;
    }

    public String getMapNameId() {
        return mapNameId;
    }

    public void setMapNameId(String mapNameId) {
        this.mapNameId = mapNameId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
}
