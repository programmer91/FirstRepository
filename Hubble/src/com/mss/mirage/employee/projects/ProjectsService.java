/*
 * ProjectsService.java
 *
 * Created on December 18, 2008, 10:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.projects;

import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.text.ParseException;

/**
 *
 * @author miracle
 */
public interface ProjectsService {
    
    /** Creates a new instance of ProjectService */
    public boolean addProjectDetails(ProjectAction projAction) throws ServiceLocatorException,ParseException;
   public ProjectVTO getProjectDetails(int Id) throws ServiceLocatorException; 
   
   public void getProjStatusCode(ProjectAction projAction) throws ServiceLocatorException;
}
