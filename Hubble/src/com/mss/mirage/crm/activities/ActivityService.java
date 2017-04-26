/*
 * @(#)ActivityService.java 1.0 November 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 *
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 30, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : ActivityService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.activities;

import com.mss.mirage.util.ServiceLocatorException;

/**
 *
 * @author Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public interface ActivityService {
    
	/**
	* The getActivityVTO(ActivityAction activityAction) is used for getting activityVTO.
	* @throws ServiceLocatorException.
	* @return ActivityVTO.
	* {@link com.mss.mirage.util.ServiceLocatorException}
	* {@link com.mss.mirage.crm.activities.ActivityVTO}
	* {@ling com.mss.mirage.crm.activities.ActivityAction}
	*/
    public ActivityVTO getActivityVTO(ActivityAction activityAction) throws ServiceLocatorException;

	/**
	* The addActivity(ActivityAction activityPojo) is used for adding activity.
	* @throws ServiceLocatorException.
	* @return boolean variable.
	* {@link com.mss.mirage.util.ServiceLocatorException}
	* {@link com.mss.mirage.crm.activities.ActivityAction}
	*/
     public boolean addActivity(ActivityAction activityPojo,String loginId) throws ServiceLocatorException;

	/**
	* The updateActivity(ActivityAction activityPojo) is used for updating activity.
	* @throws ServiceLocatorException.
	* @return boolean variable.
	* {@link com.mss.mirage.util.ServiceLocatorException}
	* {@link com.mss.mirage.crm.activities.ActivityAction}
	*/
    public boolean updateActivity(ActivityAction activityPojo) throws ServiceLocatorException;

	/**
	* The getActivity(int activityId) is used for retrieving activity.
	* @throws ServiceLocatorException.
	* @return ActivityVTO variable.
	* {@link com.mss.mirage.util.ServiceLocatorException}
	* {@link com.mss.mirage.crm.activities.ActivityVTO}
	*/
    public ActivityVTO getActivity(int activityId) throws ServiceLocatorException;
    
   
    public boolean getMailStatus(int contId) throws ServiceLocatorException;
    
     //new
    // public int getMaxActivityId() throws ServiceLocatorException;
   
    
}
