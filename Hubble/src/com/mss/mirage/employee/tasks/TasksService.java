package com.mss.mirage.employee.tasks;

import com.mss.mirage.util.ServiceLocatorException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;


public interface TasksService{
    
   /* public  int addOrUpdateTask(TasksAction tasksaction,HttpServletRequest httpServletRequest)
    throws ServiceLocatorException, SQLException;*/
    
   public  int addOrUpdateTask1(TasksAction tasksaction,HttpServletRequest httpServletRequest,String issueTitle,String issueType,String priority,String primaryAssignTo,String secondaryAssignTo,String comments,String secondaryAssignToLoginId,String secondaryAssignToLoginIdForHubble,String secondaryAssignToLoginIdForProject,String secondaryAssignToLoginIdForNetwork,String secondaryAssignToLoginIdForInfra,String secondaryAssignToLoginIdForOthers)throws ServiceLocatorException, SQLException;
  public  int  addOrUpdateTask(TasksAction tasksaction,HttpServletRequest httpServletRequest,String issueTitle,String issueType,String priority,String primaryAssignTo,String secondaryAssignTo,String comments,String secondaryAssignToLoginId)throws ServiceLocatorException,SQLException ;
    public String getAttachmentLocation(int attachmentId)
    throws ServiceLocatorException;
     public TasksVTO getTask(String IssueId, int objectId,String currentResourceType,String type)
    throws ServiceLocatorException, SQLException ;
    
    public String addAttachmentLocation(TasksAction tasksAction) throws ServiceLocatorException ;
    public String getTaskAttachmentLocation(int fileId) throws ServiceLocatorException;
      public String addNotes(TasksAction tasksAction) throws ServiceLocatorException;
       public String getTaskNotes(int notesId) throws ServiceLocatorException;
     public String updateNotes(TasksAction tasksAction) throws ServiceLocatorException;
}