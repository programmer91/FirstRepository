/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment.consultantdetails
 *
 * Date    :   Created on October 8, 2007, 8:30 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    :ConsultantDetailsService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.recruitment.consultantdetails;

import com.mss.mirage.util.ServiceLocatorException;


/**
 * <p> This interface describe the Consultant Details Business logic methods
 *  The implementation class must provide Behavior of this methods. <p>
 *
 * @version 2.0, November 01, 2007.
 *
 * @author kondaiah Adapa <kadapa@miraclesoft.com>
 *
 * @See com.mss.mirage.recruitment.consultantdetails.ConsultantDetailsServiceImpl
 */

/**
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 *
 */
public  interface ConsultantDetailsService {
    
    /**  This method can be used for  add a consultant skills.
     *
     *
     *@param consultantDetailsAction A ConsultantDetailsAction reference.
     * @return is a boolean.
     */
    public  boolean addConsultantSkills(ConsultantDetailsAction consultantDetailsAction)throws ServiceLocatorException;
    
    /**  This method can be used for  get a consultant skills from database.
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     *@param id
     * @return ConsultantSkillBean depends upon the id.
     */
   ConsultantSkillBean getConsultantDetailsAction(int id) throws ServiceLocatorException;
    
    /**  This method can be used for  edit  a consultant skills.
     *
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     *@param consultantDetailsAction A ConsultantDetailsAction reference.
     * @return ConsultantSkillBean.
     */
    public  boolean editConsultantSkills(ConsultantDetailsAction consultantDetailsAction)throws ServiceLocatorException;
    
    /**  This method can be used for  delete  a consultant skills.
     *
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     *@param consultantDetailsAction A ConsultantDetailsAction reference.
     * @return boolean.
     */
    public  boolean deleteConsultantSkills(ConsultantDetailsAction consultantDetailsAction)throws ServiceLocatorException;
    
    /**  This method can be used for  get a consultant details from database.
     *
    *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     *@param consultantId
     *
     * @return ConsultantVTO.
     */
    ConsultantVTO getConsultantDetails(int consultantId)throws ServiceLocatorException;
    
    
}
