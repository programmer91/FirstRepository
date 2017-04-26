/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   April 7, 2008, 3:56 PM
 *
 * Author  :  Hari Krishna Kondala <hkondala@miraclesoft.com>
 *
 * File    : EmpProfileService .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.profile;

import com.mss.mirage.util.ServiceLocatorException;

/**
 *
 * @author miracle
 */
public interface EmpProfileService {
    
    public EmpProfileVTO getSkills(int userId) throws ServiceLocatorException;
    
    public int updateSkills(EmpProfileAction empProfileAction, int userId) throws ServiceLocatorException;
    
    public int updateEmpProfile(EmpProfileAction empProfileAction, int userId) throws ServiceLocatorException;
}
