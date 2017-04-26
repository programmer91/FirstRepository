/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.crm.opportunities;
 *
 * Date    :  October 8, 2007, 3:42 PM
 *
 * Author  : Charan Raj Devarakonda Charan<cdevarakonda@miraclesoft.com>
 *
 * File    : OpportunityService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.opportunities;

import com.mss.mirage.util.ServiceLocatorException;

/**
 * The <code>OpportunityService</code>  class is used for createingg for the interfaces  from
 * <i>OpportunityAdd.jsp</i> page.
 * <p>
 * @author Charan Raj Devarakonda <a href="mailto:cdevarakonda@miraclesoft.com">cdevarakonda@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.crm.opportunities
 * @see com.mss.mirage.crm.opportunities.OpportunityServiceImpl
 */
public interface OpportunityService {
    
    
    /**
     *  Handles the HTTP <code>addOpportunity</code> method.
     * @return  the boolean value
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public boolean addOpportunity(OpportunityAction opportunityPojo) throws ServiceLocatorException;
    
    /**
     * /** Handles the HTTP <code>editOpportunity</code> method.
     *
     * @return  the boolean value
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public boolean editOpportunity(OpportunityAction opportunityPojo) throws ServiceLocatorException;
    
    /**
     *  Handles the HTTP <code>getOpportunity</code> method.
     *
     * @return  the OpportunityVTO object
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public OpportunityVTO getOpportunity(int opportunityId) throws ServiceLocatorException;
    
     /**
     *  Handles the HTTP <code>getOpportunityVTO</code> method.
     *
	 * @return  the OpportunityVTO object
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public OpportunityVTO getOpportunityVTO(OpportunityAction opportunityPojo) throws ServiceLocatorException;
    public String doUpdateLeadStaus(int leadSourceid,String status) throws ServiceLocatorException;
    public String doUpdateLeadStaus(int leadSourceid,String status,String CreatedBy,String flag) throws ServiceLocatorException;
    public int addOrUpdateOpportunity(OpportunityAction opportunityPojo) throws ServiceLocatorException;
}
