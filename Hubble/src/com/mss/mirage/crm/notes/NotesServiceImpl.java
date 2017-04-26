/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  October 9, 2007, 12:22 PM
 *
 * Author  : Charan Raj Devarakonda <cdevarakonda@miraclesoft.com>
 *
 * File    : NotesServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.notes;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.TrueFalseType;


/**
 * The <code>NotesServiceImpl</code>  class is used for getting new Note details and  overridding the methods from the
 * <i>NotesService.class</i>
 * <p>
 * Then it overrides  methods in <code>NotesService</code> class and performs doEdit() method and doAdd()
 * in <code>NotesAction</code> for inserting employee details in TBLCRMNOTES table.
 *
 * @author Charan Raj Devarakonda <a href="mailto:cdevarakonda@miraclesoft.com">cdevarakonda@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.crm.notes.NotesAction
 * @see com.mss.mirage.crm.notes.NotesService
 * @see com.mss.mirage.crm.notes.NotesServiceImpl
 * @see com.mss.mirage.crm.notes.NotesVTO
 */
public class NotesServiceImpl implements NotesService{
 
    /** Creates a new instance of NotesServiceImpl */
    public NotesServiceImpl() {
    }
    
    /**
     * The addNote() is used for insert the data  from  the NotesAdd.jsp page into the databasetable.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public boolean addNote(NotesAction notesPojo) throws ServiceLocatorException {
        boolean isInserted = false;
        try {
            
            Session hibernateSession = HibernateServiceLocator.getInstance().getSession();
            
            Transaction transaction = hibernateSession.beginTransaction();
            
            /* updating pojo Object */
            hibernateSession.save(notesPojo);
            
            /* Pushing Pojo Object into DataBase */
            hibernateSession.flush();
            
            /* Commit a Tranasaction */
            transaction.commit();
            
            /* Closing Session */
            hibernateSession.close();
            
            isInserted = true;
            
        } catch(Exception e) {
            isInserted = false;
            throw new ServiceLocatorException(e);
        }
        return isInserted;
    }
    
    /**
     * The editNote() is used for update the data  from  the NotesAdd.jsp page and save update data into the databasetable.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public boolean editNote(NotesAction notesPojo) throws ServiceLocatorException {
        boolean  isUpdated = false;
        try {
            
            Session hibernateSession = HibernateServiceLocator.getInstance().getSession();
            
            Transaction transaction = hibernateSession.beginTransaction();
            
            /* updating pojo Object */
            hibernateSession.update(notesPojo);
            
            /* Pushing Pojo Object into DataBase */
            hibernateSession.flush();
            
            /* Commit a Tranasaction */
            transaction.commit();
            
            /* Closing Session */
            hibernateSession.close();
            
            isUpdated = true;
            
        } catch(Exception e) {
            isUpdated = false;
            throw new ServiceLocatorException(e);
        }
        return isUpdated;
    }
    
    /**
     * The getNotes() is used for getting th value of the Id from  the NotesAdd.jsp page.
     *  @return  The name of the reference value of NotesVTO  by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     * @throws  SQLException
     *          If a required system property value cannot be accessed.
     */
    public NotesVTO getNote(int noteId) throws ServiceLocatorException {
        NotesVTO notesVTO = new NotesVTO();
        
        Connection connection = null;
        ResultSet resultSet = null;
           Statement statement=null;  
        
        String queryString = "SELECT * FROM tblCrmNotes WHERE Id="+noteId;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                notesVTO.setId(resultSet.getInt("Id"));
                notesVTO.setObjectId(resultSet.getInt("ObjectId"));
                notesVTO.setObjectType(resultSet.getString("ObjectType"));
                notesVTO.setNoteType(resultSet.getString("NoteType"));
                notesVTO.setNote(resultSet.getString("Note"));
                notesVTO.setCreatedDate(resultSet.getTimestamp("CreatedDate"));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
            return notesVTO;
        }
    }
    
    /**
     * The getNotes() is used for getting th value of the Id from  the NotesAdd.jsp page.
     *  @return  The name of the reference value of NotesVTO by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public NotesVTO getNoteVTO(NotesAction notesPojo) throws ServiceLocatorException {
        NotesVTO notesVTO = new NotesVTO();
        
        notesVTO.setId(notesPojo.getId());
        notesVTO.setObjectId(notesPojo.getObjectId());
        notesVTO.setObjectType(notesPojo.getObjectType());
        notesVTO.setNoteType(notesPojo.getNoteType());
        notesVTO.setNote(notesPojo.getNote());
        notesVTO.setCreatedDate(notesPojo.getCreatedDate());
        
        return notesVTO;
    }
    
}
