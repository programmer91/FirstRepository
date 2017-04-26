/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  May 30, 2008, 9:00 PM
 *
 * Author  : Hari Krishna Kondala<hkondala@miraclesoft.com>
 *
 * File    : TravelService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.travel;

import com.mss.mirage.util.ServiceLocatorException;

/**
 *
 * @author miracle
 */
public interface TravelService {
    
    public int checkAction(int empid)throws ServiceLocatorException;
    
    /**
     * The getTravel(int empid) is used for getting TravelVTO.
     * @throws ServiceLocatorException.
     * @return int variable.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@link com.mss.mirage.employee.travel.TravelVTO}
     * {@ling com.mss.mirage.employee.travel.TravelAction}
     */
    public TravelVTO getTravel(int empid) throws ServiceLocatorException;
    
    /**
     * The addorUpdateTravel(TravelAction travelAction) is used for adding Travel Details.
     * @throws ServiceLocatorException.
     * @return int variable.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@link com.mss.mirage.employee.travel.TravelVTO}
     * {@ling com.mss.mirage.employee.travel.TravelAction}
     */
    public int addTravel(TravelAction travelAction) throws ServiceLocatorException;
    
    /**
     * The UpdateTravel(TravelAction travelAction) is used for updating Travel Details.
     * @throws ServiceLocatorException.
     * @return int variable.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@ling com.mss.mirage.employee.travel.TravelAction}
     */
    public int UpdateTravel(TravelAction travelAction) throws ServiceLocatorException;
    
    public TravelVTO getEmpDetails(int empid) throws ServiceLocatorException;
    
}
