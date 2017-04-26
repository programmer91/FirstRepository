 /*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.projects
 *
 * Date    : January 01, 2008, 12:35 PM
 *
 * Author  : Arjun Sanapathi<asanapathi@miraclesoft.com>
 *           Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : ProjectsService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */


 
package com.mss.mirage.projects;

import com.mss.mirage.util.ServiceLocatorException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * This interface.... Contains more methods from which
 * we can add or edit or list or update projects and the Project details.
 */
public interface ProjectService {
    
      
    public int addOrUpdateProject(ProjectAction projectPojo,String operationMode) throws ServiceLocatorException;    
    public int addOrUpdateSubProject(ProjectAction projectPojo,String operationMode) throws ServiceLocatorException;
    
     public int assignTeamForProject(String[] empId,int accountId) throws ServiceLocatorException;
     //public int upDateTeamForProject(String[] empId,int accountId,int projectId,String projectName) throws ServiceLocatorException;
     // Modified for project association..
     public int  upDateTeamForProject(List assignedEmpIds, int accountId, int projectId,String projectName) throws ServiceLocatorException;
    
    public ProjectVTO getProjectVTO(ProjectAction projectPojo) throws ServiceLocatorException;    
    public ProjectVTO getProject(int id) throws ServiceLocatorException;
    
    public SubProjectVTO getSubProjectVTO(ProjectAction projectPojo) throws ServiceLocatorException;
    public SubProjectVTO getSubProject(int subProjectId) throws ServiceLocatorException;

    public boolean insertAttachment(ProjectAction projectAction) throws ServiceLocatorException;
    public String generatePath(String contextPath, String objectType) throws ServiceLocatorException;

    public String getPath(int attachmentId) throws ServiceLocatorException;

    public int addMap(ProjectAction projectAction) throws ServiceLocatorException;
    public int updateMap(ProjectAction projectAction) throws ServiceLocatorException;

    public MapVTO getMapProject(int mapId) throws ServiceLocatorException;    
    public MapVTO getMapVTO(ProjectAction projectAction) throws ServiceLocatorException;
    
    //public IssueVTO getIssueProject(int issueId,int accountId,int projectId,int mapId) throws ServiceLocatorException;
    //public ProjectVTO getAccountProjectName(int accounId, int projectId) throws ServiceLocatorException;

    public int addIssue(ProjectAction projectPojo) throws ServiceLocatorException;
    public int updateIssue(ProjectAction projectAction) throws ServiceLocatorException;
    
    public IssueVTO getIssues(int issueId) throws ServiceLocatorException;
    public IssueVTO getIssueVTO(ProjectAction projectAction) throws ServiceLocatorException;

    public Map getProjectTeamByProjectId(int projectId) throws ServiceLocatorException;
         /*New Methods For hierarchy
     * 
     * 
     */
        public Map getProjectResourceMap(int projectId) throws ServiceLocatorException;
         public Map getProjectReportsToMap(int projectId) throws ServiceLocatorException;
         public int updateProjectResourceReportsTo(int projectEmpId, int reportsToId,int projectId)  throws ServiceLocatorException;

       //  public int doAddEmployeeProject(ProjectAction projectPojo,String loginId) throws ServiceLocatorException ;
         public String doAddEmployeeProject(ProjectAction projectPojo,String loginId) throws ServiceLocatorException ;
          public void getProjectTeamDetails(int Id,ProjectAction projectAction)    throws ServiceLocatorException, SQLException;
          public int doUpdateProjectTeam(ProjectAction projectPojo,String loginId) throws ServiceLocatorException;
         // public void getProjectTeamDetails(int Id,ProjectAction projectAction)    throws ServiceLocatorException, SQLException;
         public int doAddPMOTeam(String createdBy,String preAssignEmpId,String status,int accountId,int projectId) throws ServiceLocatorException;
          public int doDeletePMOTeamMember(String modifiedBy,String preAssignEmpId,int accountId,int projectId) throws ServiceLocatorException;
          
          public int doInsertProjectContactHistory(ProjectAction projectPojo,String loginId,String flag) throws ServiceLocatorException ;
          
         // project risk
           public int doAddProjectRisk(ProjectAction projectPojo) throws ServiceLocatorException ;
          public int doEditProjectRisk(ProjectAction projectPojo) throws ServiceLocatorException ;
          public void getProjectRiskDetails(ProjectAction projectPojo) throws ServiceLocatorException ;
          // project risk end
          
          // current status management
          public int doAddProjectToCustomer(ProjectAction projectPojo,String flag) throws ServiceLocatorException;
           public void getCustomerProjectDetails(ProjectAction projectPojo,int Id) throws ServiceLocatorException; 
   //public int getInActiveCustomerProject(int  projectId,String createdBy,Timestamp createdDate,String projectEndDate) throws ServiceLocatorException;
           public int getInActiveCustomerProject(int  projectId,String createdBy,Timestamp createdDate,String projectEndDate,String comments) throws ServiceLocatorException;


          // current status managemet end
}
