/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment
 *
 * Date    :  September 27, 2007, 8:00 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.recruitment;
import com.mss.mirage.util.ServiceLocatorException;
import java.util.StringTokenizer;
/**
 * <p> This interface describe the Consultant Business logic methods
 *  The implementation class must provide Behavior of this methods. <p>
 *
 * @version 2.0, November 01, 2007.
 *
 * @author kondaiah Adapa <kadapa@miraclesoft.com>
 *
 * @See com.mss.mirage.recruitment.ConsultantServiceImpl
 */
/**
 * @author kondaiah Adapa <kadapa@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 *
 */
public interface ConsultantService {
    
    
    /**  This method can be used to add the new consultant.
     *
     *  @param   consultantAction A ConsultantAction reference.
     *
     *  @return is a boolean
     *
     *  @throws  ServiceLocatorException
     *    If a ServiceLocatorException exists and its <code>{
     * @link    com.mss.mirage.util.ServiceLocatorException}
     * </code>
     * @see com.mss.mirage.util.ServiceLocatorException.
     */
    public boolean addConsultant(ConsultantAction consultantAction) throws ServiceLocatorException;
    
    /**  This method can be used to delete the  consultant.
     *
     *  @param consultantId
     *
     *@throws  ServiceLocatorException
     *    If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException}
     * </code>
     *@see com.mss.mirage.util.ServiceLocatorException.
     *
     *@return is a boolean
     */
    public boolean deleteConsultant(int consultantId) throws ServiceLocatorException;
    
    /**  This method can be used to edit the consultant details.
     *
     *@param consultantAction
     * 
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     *@see com.mss.mirage.util.ServiceLocatorException.
     *
     *@return is a boolean
     */
    public boolean editConsultant(ConsultantAction consultantAction) throws ServiceLocatorException;
    
    /**  This method can be used to get the consultant demographics.
     *
     *@param consultantAction
     *
     *@throws  ServiceLocatorException
     *    If a ServiceLocatorException exists and its <code>
     * {
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }
     * </code>
     *@see com.mss.mirage.util.ServiceLocatorException.
     *
     *@return ConsultantVTO returned depends on the consultantId
     */
    public ConsultantVTO getConsultant(int consultantId) throws ServiceLocatorException;
    
    public int doAddConsultant(ConsultantAction consultantAction) throws ServiceLocatorException;
    
    public boolean attachResume(ConsultantAction consultantAction) throws ServiceLocatorException;
    
    public int doEdit(ConsultantAction consultantAction) throws ServiceLocatorException;
    
    public ConsultantVTO getConsultantDetails(String Id) throws ServiceLocatorException;
    
    public int doAddConsultantActivity(ConsultantAction consultantAction,String loginID) throws ServiceLocatorException;
    
    public int doEditConsultantActivity(ConsultantAction consultantAction) throws ServiceLocatorException;
    
    public ConsultantVTO getConsultantActivity(int consultantId,int id)  throws ServiceLocatorException;
 
    //New SErvice to add resume
    
    public int newAttachResume(ConsultantAction consultantAction) throws ServiceLocatorException;
    public String getReviewConsultantDetails(ConsultantAction consultantAction,int consultantId) throws ServiceLocatorException;
  //  public int doReferToReview(ConsultantAction consultantAction,String loginId,StringTokenizer st) throws ServiceLocatorException;
    public int doReferToReview(ConsultantAction consultantAction,String loginId) throws ServiceLocatorException;

    public String doReviewTechnical(ConsultantAction consultantAction) throws ServiceLocatorException;
    public int doAddTechincalReview(ConsultantAction consultantAction,StringTokenizer st) throws ServiceLocatorException;
	
    
}
