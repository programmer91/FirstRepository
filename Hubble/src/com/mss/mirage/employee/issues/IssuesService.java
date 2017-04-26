package com.mss.mirage.employee.issues;

import com.mss.mirage.util.ServiceLocatorException;
import java.sql.SQLException;


public interface IssuesService{
    
    public  int addOrUpdateIssue(IssuesAction issuesaction)
    throws ServiceLocatorException, SQLException;
    
    public IssuesVTO getIssue(String s, int i)
    throws ServiceLocatorException, SQLException;
    
    public String getAttachmentLocation(int attachmentId)
    throws ServiceLocatorException;
    
      public String getEmpTaskAttachmentLocation(int attachmentId)
    throws ServiceLocatorException;
    
}