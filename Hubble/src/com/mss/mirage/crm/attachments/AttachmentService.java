/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  October 9, 2007, 3:11 PM
 *
 * Author  : Pardha Saradhi<pkoganti@miraclesoft.com>
 * File    : AttachmentService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.attachments;

import com.mss.mirage.util.ServiceLocatorException;
/**
 * The <code>NotesService</code>  class is used for createingg for the interfaces  from
 * <i>NotesAdd.jsp</i> page.
 * <p>
 * @author  Pardha Saradhi<pkoganti@miraclesoft.com>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.crm.notes
 * @see com.mss.mirage.crm.notes.NotesServiceImpl
 */
public interface AttachmentService {
    /**
     * /** Handles the HTTP <code>addNote</code> method.
     * @return  the boolean value
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    
    public boolean insertAttachment(AttachmentAction attachmentPojo) throws ServiceLocatorException;
    
    /**
     * /** Handles the HTTP <code>editNote</code> method.
     *
     * @return  the String value
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public String generatePath(String contextPath, String objectType) throws ServiceLocatorException;
    
    /**
     * /** Handles the HTTP <code>editNote</code> method.
     *
     * @return  the boolean value
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public boolean deleteAttachment(AttachmentAction attachmentPojo) throws ServiceLocatorException;
    
    /**
     * /** Handles the HTTP <code>editNote</code> method.
     *
     * @return  the String value
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public String getAttachmentLocation(int attachmentId) throws ServiceLocatorException;
}
