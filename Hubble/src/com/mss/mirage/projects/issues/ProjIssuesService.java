/*
 * ProjIssuesService.java
 *
 * Created on April 23, 2008, 8:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.projects.issues;

import com.mss.mirage.util.ServiceLocatorException;
import java.sql.SQLException;
/**
 *
 * @author miracle
 */
public interface ProjIssuesService {
    
    public int addOrUpdateIssue(ProjIssuesAction issuesaction)
    throws ServiceLocatorException, SQLException;
    
    public ProjIssuesVTO getIssue(String s, int i)
    throws ServiceLocatorException, SQLException;
    
    public String getAttachmentLocation(int attachmentId)
    throws ServiceLocatorException;

    public ProjIssuesVTO getProject(ProjIssuesAction projIssuesAction)
    throws ServiceLocatorException;
}
