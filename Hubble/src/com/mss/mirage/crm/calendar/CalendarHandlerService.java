/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   August 4, 2008, 3:24 PM
 *
 * Author  :  Rajasekhar Yenduva <ryenduva@miraclesoft.com>
 *
 * File    : CalendarHandlerService .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.calendar;

import com.mss.mirage.util.ServiceLocatorException;
import java.util.Map;

/**
 *
 * @author Rajasekhar Yenduva <ryenduva@miraclesoft.com>
 */
public interface CalendarHandlerService {
    
    /** Creates a new instance of CalendarHandlerService */
    
    public Map getAllSalesTeamExceptAccountTeam(String empId) throws ServiceLocatorException;
    public Map getTeamCalendar(String empId) throws ServiceLocatorException;
    
}