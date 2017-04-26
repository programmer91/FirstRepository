/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : Templates.Classes
 *
 * Date    :  December 26, 2007, 12:01 PM
 *
 * Author  : MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * File    : Toggle.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.tree.team;

/**
 *
 * @author MrutyumjayaRao Chennu
 */
public class Toggle extends GetTeamHierarchy{
    
    
    public String execute() throws Exception {
        super.execute();
        getTeamHierarchy().toggle();
        return SUCCESS;
    }
}
