/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : Templates.Classes
 *
 * Date    :  December 26, 2007, 11:57 AM
 *
 * Author  : MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * File    : GetTeamHierarchy.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.tree.team;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author MrutyumjayaRao Chennu
 */
public class GetTeamHierarchy extends ActionSupport{
    
    private String userId;
    private TeamHierarchy teamHierarchy;
    /** Creates a new instance of GetTeamHierarchy */
    public String execute() throws Exception {
        if("".equals(userId)  || userId==null){
            userId = "plokam";
        }
        
        teamHierarchy = TeamHierarchy.getByUserId(userId);
        
        return SUCCESS;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public TeamHierarchy getTeamHierarchy() {
        return teamHierarchy;
    }
    
    
}
