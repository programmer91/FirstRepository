/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 26, 2007, 12:35 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : AncillaryService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.ancillary;

import com.mss.mirage.util.ServiceLocatorException;

/**
 *
 * @author Rajanikanth Teppala <rteppala@miraclesoft.com>
 *  This INTERFACE.... ENTER THE DESCRIPTION HERE
 */

public interface AncillaryService {
    
    public int checkAction(int empid)throws ServiceLocatorException;
    /**
     * The addorUpdateAncillary(AncillaryAction ancillaryAction) is used for adding Ancillary.
     * @throws ServiceLocatorException.
     * @return int variable.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@link com.mss.mirage.employee.ancillary.AncillaryVTO}
     * {@ling com.mss.mirage.employee.ancillary.AncillaryAction}
     */
    public int addorUpdateAncillary(AncillaryAction ancillaryAction) throws ServiceLocatorException;
    
    /**
     * The getAncillary(int empId) is used for getting AncillaryVTO.
     * @throws ServiceLocatorException.
     * @return AncillaryVTO.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@link com.mss.mirage.employee.ancillary.AncillaryVTO}
     * {@ling com.mss.mirage.employee.ancillary.AncillaryAction}
     */
    public AncillaryVTO getAncillary(int empId) throws ServiceLocatorException;
    
    /**
     * The UpdateAncillary(AncillaryAction ancillaryAction) is used for updating ancillary.
     * @throws ServiceLocatorException.
     * @return int variable.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@ling com.mss.mirage.employee.ancillary.AncillaryAction}
     */
    public int UpdateAncillary(AncillaryAction ancillaryAction) throws ServiceLocatorException;
}
