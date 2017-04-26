/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :  com.mss.mirage.immigration;
 *
 * Date    :  October 16, 2007, 9:09 PM
 *
 * Author  : Jyothi Nimmagadda <jnimmagadda@miraclesoft.com>
 *
 * File    : ImmigrationService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.immigration;

import com.mss.mirage.util.ServiceLocatorException;


/**
 * The <code>ImmigrationService</code>  class is used for createingg for the interfaces  from
 * <i>Immigration.jsp</i> page.
 * <p>
 * @author Jyothi Nimmagadda <a href="mailto:jnimmagadda@miraclesoft.com">jnimmagadda@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.immigration
 * @see com.mss.mirage.immigration.ImmigrationServiceImpl
 */
public interface ImmigrationService {
    
    public int checkAction(int empid)throws ServiceLocatorException;
    /**
     *  Handles the HTTP <code>addImmigration</code> method.
     *
     * @return  the boolean value
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public boolean addImmigration(ImmigrationAction immigrationPojo) throws ServiceLocatorException;
    
    /**
     *  Handles the HTTP <code>getImmigrationVTO</code> method.
     *
     * @return  the ImmigrationVTO object
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public ImmigrationVTO getImmigrationVTO(ImmigrationAction immigrationAction) throws ServiceLocatorException;
    
    /**
     *  Handles the HTTP <code>editImmigration</code> method.
     *
     * @return  the boolean value
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public boolean editImmigration(ImmigrationAction immigrationPojo) throws ServiceLocatorException;
    
    /**
     *  Handles the HTTP <code>getImmigration</code> method.
     *
     * @return  the ImmigrationVTO object
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public ImmigrationVTO getImmigration(int empId)  throws ServiceLocatorException;
    
    
    
}
