/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.crm.notes;
 *
 * Date    :  October 9, 2007, 12:21 PM
 *
 * Author  : Charan Raj Devarakonda <cdevarakonda@miraclesoft.com>
 *
 * File    : NotesService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.notes;

import com.mss.mirage.util.ServiceLocatorException;

/**
 * The <code>NotesService</code>  class is used for createingg for the interfaces  from
 * <i>NotesAdd.jsp</i> page.
 * <p>
 * @author Charan Raj Devarakonda <a href="mailto:cdevarakonda@miraclesoft.com">cdevarakonda@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.crm.notes
 * @see com.mss.mirage.crm.notes.NotesServiceImpl
 */
public interface NotesService {
    /**
     * /** Handles the HTTP <code>addNote</code> method.
     * @return  the boolean value
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public boolean addNote(NotesAction notesPojo) throws ServiceLocatorException;
    
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
    public boolean editNote(NotesAction notesPojo) throws ServiceLocatorException;
    
    /**
     * /** Handles the HTTP <code>getNote</code> method.
     *
     * @return  the NoteVTO object
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public NotesVTO getNote(int noteId) throws ServiceLocatorException;
    
    /**
     * /** Handles the HTTP <code>getNoteVTO</code> method.
     *
     * @return  the NoteVTO object
     *
     * @throws  ServiceLocatorException
     *          If a required system property value cannot be accessed.
     *
     * @see     com.mss.mirage.util.ServiceLocatorException
     */
    public NotesVTO getNoteVTO(NotesAction notesPojo) throws ServiceLocatorException;
    
}
