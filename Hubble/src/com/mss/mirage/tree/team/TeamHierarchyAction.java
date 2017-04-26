/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.tree.team
 *
 * Date    :  December 25, 2007, 9:14 PM
 *
 * Author  : MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * File    : TeamHierarchyAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.tree.team;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle
 */
public class TeamHierarchyAction extends ActionSupport implements ServletRequestAware{
    private HttpServletRequest httpServletRequest;
    public TeamHierarchy getTeamTreeRootNode() throws ServiceLocatorException{
        HttpSession session = httpServletRequest.getSession(false);
        String userId = null;
        String userFullName = null;
        
        // if session is existed or not
        if(session!=null){
            userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userFullName = session.getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            TeamHierarchy.setUserFullName(userFullName);
            TeamHierarchy.getMyTeamManagersHierarchy(Properties.getProperty("Managers.ReportingTo"));
        }
        return TeamHierarchy.getByUserId(userId);
    }
    
     public OperationsTeamHirarchy getTeamTreeRootNodeOfOperations() throws ServiceLocatorException{
        HttpSession session = httpServletRequest.getSession(false);
        String userId = null;
        String userFullName = null;
        
        // if session is existed or not
        if(session!=null){
            userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userFullName = session.getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            OperationsTeamHirarchy.setUserFullName(userFullName);
            OperationsTeamHirarchy.getMyTeamManagersHierarchyOfOperations(Properties.getProperty("Operations.ReportingTo"));
        }
        return OperationsTeamHirarchy.getByUserId(userId);
    }
     
     public GDCHierarchy getTeamTreeOfGDC() throws ServiceLocatorException{
        HttpSession session = httpServletRequest.getSession(false);
        String userId = null;
        String userFullName = null;
        
        // if session is existed or not
        if(session!=null){
            userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userFullName = session.getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            GDCHierarchy.setUserFullName(userFullName);
            GDCHierarchy.getMyTeamHierarchyOfGDC(Properties.getProperty("GDC.ReportingTo"));
        }
        return GDCHierarchy.getByUserId(userId);
    } 
     
    public RecruitmentHierarchy getTeamTreeOfRecruitment() throws ServiceLocatorException{
        HttpSession session = httpServletRequest.getSession(false);
        String userId = null;
        String userFullName = null;
        
        // if session is existed or not
        if(session!=null){
            userId = session.getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            userFullName = session.getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
            RecruitmentHierarchy.setUserFullName(userFullName);
            RecruitmentHierarchy.getMyTeamHierarchyOfRecruitment(Properties.getProperty("Recruitment.ReportingTo"));
        }
        return RecruitmentHierarchy.getByUserId(userId);
    }
     
     
    public String execute()throws Exception{
        
        //System.out.println("I am in Execute");
        
        return SUCCESS;
    }
     
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        
        //Binding Request Object to This Class Variable httpServletRequest
        this.httpServletRequest = httpServletRequest;
        
    }
    
}
