
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment.consultantresume
 *
 * Date    :   October 17, 2007, 10:58 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    :  ConsultantResumeService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */


package com.mss.mirage.recruitment.attachments;

import com.mss.mirage.util.ServiceLocatorException;


/**
 * <p> This interface describe the ConsultantResume Business logic methods 
 *  The implementation class must provide Behavior of this methods. <p>
 *
 * @version 2.0, November 01, 2007.
 *
 * @author kondaiah Adapa <kadapa@miraclesoft.com>
 *
 * @See com.mss.mirage.recruitment.consultantresume.ConsultantResumeServiceImpl
 */

/**
 *
* @author
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public interface ConsultantAttachmentService {
    
    /**
     *  This method can be use to attach new resume for consultant.
     * 
     * 
     * 
     * @param consultantResumeAction A ConsultantAttachmentAction reference.
     * @return is a boolean.
     */    
      public boolean insert(ConsultantAttachmentAction consultantAttachmentAction)throws ServiceLocatorException;   
      
       /**
     *  This method can be use to delete a  attached resume of the consultant.
     * 
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     * 
     * @param consultantResumeAction A ConsultantAttachmentAction reference.
     * @return is a boolean.
     */    
    //  public boolean deleteConsultantResume(ConsultantAttachmentAction consultantResumeAction );
      
    public boolean deleteConsultantAttachmentById(int id) throws ServiceLocatorException;
      
    
   /**
     *  This method can be use to download a  attachment file of the  consultant.
     * 
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     * @param  attachmentId.
     * @return String attachmentLocation - its returns attachmentLocation when consultant attachment attachment file downloaded successfully .
     */      
    public String getAttachmentLocation(int attachmentId) throws ServiceLocatorException;
    
    /**
     *  This method can be use to submitt a attach resume of the consultant.
     * 
    *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     * 
     * @param consultantResumeAction A ConsultantAttachmentAction reference.
     * @return is a boolean.
     */    
     public boolean submitt(ConsultantAttachmentAction consultantAttachmentAction)throws ServiceLocatorException;   
     
     /**
     *  This method can be use to submitt a techreview of the consultant.
     * 
    *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     * 
     * @param consultantResumeAction A ConsultantAttachmentAction reference.
     * @return is a boolean.
     */    
     
     public boolean techreview(ConsultantAttachmentAction consultantAttachmentAction) throws ServiceLocatorException;
    public String getConsultantAttachmentLocation(int id)throws ServiceLocatorException;
}

