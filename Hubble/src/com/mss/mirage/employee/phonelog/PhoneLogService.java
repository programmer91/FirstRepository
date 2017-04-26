/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.employee.phonelog
 *
 * Date    : February 7, 2008, 7:44 PM
 *
 * Author  : Venkateswara Rao Nukala<vnukala@miraclesoft.com>
 *
 * File    : PhoneLogService.java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.employee.phonelog;

import com.mss.mirage.util.ServiceLocatorException;

/**
 *
 * @author miracle
 */
public interface PhoneLogService {
 
    /**
     * this method is for adding phonelog
     * @param action 
     * @throws com.mss.mirage.util.ServiceLocatorException 
     * @return 
     */    
public boolean addPhoneLog(PhoneLogAction action)throws ServiceLocatorException;    

    /**
     * this method is for editing the phonelog
     * @param action 
     * @param PhoneLogId 
     * @throws com.mss.mirage.util.ServiceLocatorException 
     * @return 
     */
public boolean editPhoneLog(PhoneLogAction action,int PhoneLogId)throws ServiceLocatorException;

    /**
     * this method is for deleting the phonelog record
     * @param action 
     * @param PhoneLogId 
     * @throws com.mss.mirage.util.ServiceLocatorException 
     * @return 
     */
public boolean deletePhoneLog(PhoneLogAction action,int PhoneLogId) throws ServiceLocatorException;

    /**
     * this method is for editing the particular phonelog record
     * @param PhoneLogId 
     * @throws com.mss.mirage.util.ServiceLocatorException 
     * @return 
     */
public PhoneLogVTO getPhoneLog(int PhoneLogId)throws ServiceLocatorException;

}
