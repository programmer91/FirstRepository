/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  December 25, 2007, 9:02 PM
 *
 * Author  : MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * File    : TeamCacheManager.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author miracle
 */
public class TeamCacheManager {
    
    /** Creates a new instance of TeamCacheManager */
    public TeamCacheManager() {
    }
    
    /* Cache to store the datasource objects
     * for improving performance by avoided repetative
     * calls to the JNDI registry
     */
    private static Map teamCache;
    
    /**
     * @return An instance of the Cache Map
     * @throws ServiceLocatorException
     */
    public static Map getTeamCache() throws ServiceLocatorException {
        try {
            if(teamCache == null) {
                teamCache = Collections.synchronizedMap(new HashMap());
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return teamCache;
    }
    
}
