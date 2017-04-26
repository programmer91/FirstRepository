/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   September 9, 2008, 7:28 PM
 *
 * Author  :  Hari Krishna Kondala <hkondala@miraclesoft.com>
 *
 * File    : RequirementService .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.requirement;

import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Timestamp;

/**
 *
 * @author miracle
 */
public interface RequirementService {
    
    public RequirementVTO getRequirement(int objId,String country) throws ServiceLocatorException;
    
    public int doAddRequirement(RequirementAction requirementAction) throws ServiceLocatorException;
    
    public int doEdit(RequirementAction requirementAction ,String transType) throws ServiceLocatorException;
    
    public int addConsultantForRequirement(RequirementAction requirementAction) throws ServiceLocatorException;
    
    public RequirementVTO getConsultantRequuirement(int objId) throws ServiceLocatorException;
    
    public int editConsultantRequirement(RequirementAction requirementAction) throws ServiceLocatorException;
    
    //new service for resume submission details on 07162013
    public int addResumeSubmissionDetails(String custEmail,String cc,String bcc,String subject,String message,String resumeRecId,String resumeRequirementId,String resumeConsultantId,String resumeAttachmentId,String submittedBy) throws ServiceLocatorException;
    
    public int insertRequirementAssignedToTrack(String transType,String createdBy,String recruiter1,String recruiter2,String requirementId,String assignedBy,Timestamp assignedDate) throws ServiceLocatorException;
    
     public void doAddMailsForRequirement(String subjectinfo,String title, String user,String rate, String id,String country,String state,String primarySkills,String secondarySkills) throws ServiceLocatorException;
     public String addReqAttachmentLocation(RequirementAction requirementAction) throws ServiceLocatorException ;
 public String getPath(int Id) throws ServiceLocatorException ;
 
 public String getDLCopyLocation(RequirementAction requirementAction) throws ServiceLocatorException;

    public String getAuthCopyLocation(RequirementAction requirementAction) throws ServiceLocatorException;

    
}
