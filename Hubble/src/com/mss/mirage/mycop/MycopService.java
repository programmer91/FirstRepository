/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   March 12, 2008, 4:01 PM
 *
 * Author  :  Rajasekhar Yenduva <ryenduva@miraclesoft.com>
 *
 * File    : MycopService .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.mycop;

import com.mss.mirage.util.ServiceLocatorException;
import java.util.List;

/**
 *
 * @author Rajasekhar Yenduva <ryenduva@miraclesoft.com>
 */
public interface MycopService {
    public List getUserData(String user,String startDate, String endDate,String dbLoginId)throws ServiceLocatorException;
    
   // public List getAbcentiesData(String startDate, String endDate,String departmentId)throws ServiceLocatorException;
}
