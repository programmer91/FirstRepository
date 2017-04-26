/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment.consultantresume
 *
 * Date    :   October 17, 2007, 10:59 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantResumeServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.recruitment.attachments;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.io.File;
import java.sql.Timestamp;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Iterator;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.hibernate.Query;
import java.sql.SQLException;
import org.hibernate.HibernateException;
/**
 * <p> This class implements all the Consultant Resume Business logic methods  which are defined
 * in ConsultantResumeService interface. <p>
 *
 * @version 2.0, November 01, 2007.
 *
 * @author kondaiah Adapa <kadapa@miraclesoft.com>
 *
 * @See com.mss.mirage.recruitment.ConsultantResumeService
 */



/**
 *
 * @author
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class ConsultantAttachmentServiceImpl implements ConsultantAttachmentService{
   
    /**
     * Creates a new instance of ConsultantAttachmentServiceImpl
     */
    public ConsultantAttachmentServiceImpl() {
    }
    
    /**
     *  This method can be use to attach new resume for consultant.
     *
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     * @param consultantAttachmentAction ConsultantAttachmentAction reference.
     * @return boolean isInsert-its return isInsert when resume attached to the consultant.
     */
  /*  public boolean insert(ConsultantAttachmentAction consultantAttachmentAction) throws ServiceLocatorException{
        
        boolean isInsert=false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        Statement Statement=null;
        
        int updatedRows=0;
        
        try {
            //objectIdinInt,
           String queryString = "INSERT INTO tblRecAttachments (Id,ObjectId,ObjectType,AttachmentName,AttachmentLocation,AttachmentFileName,DateUploaded) values (?,?,?,?,?,?,?)";
       
       
            
           // consultantAttachmentAction.setDate(new Timestamp(new java.util.Date().getTime()));
            consultantAttachmentAction.setDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            consultantAttachmentAction.setObjectIdinInt(Integer.parseInt(consultantAttachmentAction.getObjectId().trim()));
            //Save values in DataBase
            
           try{
                //connection provider for inserting values.
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            
            int attachId=0;
            Statement = connection.createStatement();
            resultSet=Statement.executeQuery("select max(Id) from tblRecAttachments");
             if (resultSet.next()) {
                   attachId=resultSet.getInt(1);
                  //  System.out.print("max row id:"+attachId);
                               attachId=attachId+1;
            
            preparedStatement.setInt(1,attachId);
            preparedStatement.setInt(2,consultantAttachmentAction.getObjectIdinInt());
            preparedStatement.setString(3,consultantAttachmentAction.getRequestType());
            preparedStatement.setString(4,consultantAttachmentAction.getAttachmentName());
            preparedStatement.setString(5,consultantAttachmentAction.getFilepath());
            preparedStatement.setString(6,consultantAttachmentAction.getUploadFileName());
            preparedStatement.setString(7,consultantAttachmentAction.getDate().toString());
        
            updatedRows = preparedStatement.executeUpdate();
                   
              } else {
                           // throw an exception from here
              } 
            
            
            }catch (SQLException se){
            throw new ServiceLocatorException(se);
            }finally{
             try{
                  if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(Statement!=null){
                    Statement.close();
                    Statement = null;
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
           }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
          
           if(updatedRows>0){
             // System.out.println("After saving the resume into DB");
             isInsert=true;
            }
            
         
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return isInsert;
    }
    */
    public boolean insert(ConsultantAttachmentAction consultantAttachmentAction) throws ServiceLocatorException{
        
        boolean isInsert=false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        Statement Statement=null;
        
        int updatedRows=0;
        
        try {
            //objectIdinInt,
           String queryString = "INSERT INTO tblRecAttachments (ObjectId,ObjectType,AttachmentName,AttachmentLocation,AttachmentFileName,DateUploaded) values (?,?,?,?,?,?)";
          consultantAttachmentAction.setDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            consultantAttachmentAction.setObjectIdinInt(Integer.parseInt(consultantAttachmentAction.getObjectId().trim()));
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1,consultantAttachmentAction.getObjectIdinInt());
            preparedStatement.setString(2,consultantAttachmentAction.getRequestType());
            preparedStatement.setString(3,consultantAttachmentAction.getAttachmentName());
            preparedStatement.setString(4,consultantAttachmentAction.getFilepath());
            preparedStatement.setString(5,consultantAttachmentAction.getUploadFileName());
            preparedStatement.setString(6,consultantAttachmentAction.getDate().toString()); 
            updatedRows = preparedStatement.executeUpdate();                  
               if(updatedRows>0){
             // System.out.println("After saving the resume into DB");
             isInsert=true;
            }
            }
 
         catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
               throw new ServiceLocatorException(se);
            }
        }
        return isInsert;
    }
    /**
     *  This method can be use to submitt a resume of  consultant.
     *
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     * @param consultantAttachmentAction ConsultantAttachmentAction reference.
     * @return boolean isSubmitt-its return isSubmitt when resume submitted to the consultant.
     */
    
   /* public boolean submitt(ConsultantAttachmentAction consultantAttachmentAction)throws ServiceLocatorException {
        
        boolean isSubmitt=false;
        try {
            
            
            //Create a seesion for connectionFactory
            Session session=HibernateServiceLocator.getInstance().getSession(); //for get session factory instance from hibernate service locator
            //Transaction Started
            Transaction trns=session.beginTransaction();
            trns.begin();
            
            //consultantAttachmentAction.setDate(new Timestamp(new java.util.Date().getTime()));
            consultantAttachmentAction.setDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            consultantAttachmentAction.setObjectIdinInt(Integer.parseInt(consultantAttachmentAction.getObjectId().trim()));
            //Save values in DataBase
            session.save(consultantAttachmentAction);
            session.flush();
            trns.commit();
            isSubmitt=true;
            session.clear();
            session.close();
            session = null;
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
            //ex.printStackTrace();
        }
        return isSubmitt;
        
        
    }
    */
    
    
    public boolean submitt(ConsultantAttachmentAction consultantAttachmentAction) throws ServiceLocatorException{
        
        boolean isInsert=false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        Statement Statement=null;
        
        int updatedRows=0;
        
        try {
            //objectIdinInt,
           String queryString = "INSERT INTO tblRecAttachments (ObjectId,ObjectType,AttachmentName,AttachmentLocation,AttachmentFileName,DateUploaded) values (?,?,?,?,?,?)";
          consultantAttachmentAction.setDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            consultantAttachmentAction.setObjectIdinInt(Integer.parseInt(consultantAttachmentAction.getObjectId().trim()));
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1,consultantAttachmentAction.getObjectIdinInt());
            preparedStatement.setString(2,consultantAttachmentAction.getRequestType());
            preparedStatement.setString(3,consultantAttachmentAction.getAttachmentName());
            preparedStatement.setString(4,consultantAttachmentAction.getFilepath());
            preparedStatement.setString(5,consultantAttachmentAction.getUploadFileName());
            preparedStatement.setString(6,consultantAttachmentAction.getDate().toString()); 
            updatedRows = preparedStatement.executeUpdate();                  
               if(updatedRows>0){
             // System.out.println("After saving the resume into DB");
             isInsert=true;
            }
            }
 
         catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
               throw new ServiceLocatorException(se);
            }
        }
        return isInsert;
    }
    
    
    /**
     *  This method can be use to submitt a techreview of  consultant.
     *
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     * @param consultantAttachmentAction ConsultantAttachmentAction reference.
     * @return boolean isTechreview - its return isTechreview when techreview file submitted to the consultant.
     */
    
    /*public boolean techreview(ConsultantAttachmentAction consultantAttachmentAction)throws ServiceLocatorException {
        
        boolean isTechreview=false;
        try {
            
            
            //Create a seesion for connectionFactory
            Session session=HibernateServiceLocator.getInstance().getSession(); //for get session factory instance from hibernate service locator
            //Transaction Started
            Transaction trns=session.beginTransaction();
            trns.begin();
            
           // consultantAttachmentAction.setDate(new Timestamp(new java.util.Date().getTime()));
            consultantAttachmentAction.setDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            consultantAttachmentAction.setObjectIdinInt(Integer.parseInt(consultantAttachmentAction.getObjectId().trim()));
            //Save values in DataBase
            session.save(consultantAttachmentAction);
            session.flush();
            trns.commit();
            isTechreview=true;
            session.clear();
            session.close();
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return isTechreview;
    }
    */
    
     public boolean techreview(ConsultantAttachmentAction consultantAttachmentAction) throws ServiceLocatorException{
        
        boolean isInsert=false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        Statement Statement=null;
        
        int updatedRows=0;
        
        try {
            //objectIdinInt,
           String queryString = "INSERT INTO tblRecAttachments (ObjectId,ObjectType,AttachmentName,AttachmentLocation,AttachmentFileName,DateUploaded) values (?,?,?,?,?,?)";
          consultantAttachmentAction.setDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            consultantAttachmentAction.setObjectIdinInt(Integer.parseInt(consultantAttachmentAction.getObjectId().trim()));
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1,consultantAttachmentAction.getObjectIdinInt());
            preparedStatement.setString(2,consultantAttachmentAction.getRequestType());
            preparedStatement.setString(3,consultantAttachmentAction.getAttachmentName());
            preparedStatement.setString(4,consultantAttachmentAction.getFilepath());
            preparedStatement.setString(5,consultantAttachmentAction.getUploadFileName());
            preparedStatement.setString(6,consultantAttachmentAction.getDate().toString()); 
            updatedRows = preparedStatement.executeUpdate();                  
               if(updatedRows>0){
             // System.out.println("After saving the resume into DB");
             isInsert=true;
            }
            }
 
         catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
               throw new ServiceLocatorException(se);
            }
        }
        return isInsert;
    }
    
    /**
     *  This method can be use to delete a attachment of the  consultant.
     *
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     * @param  id.
     * @return true - its returns true when consultant attachment deleted successfully .
     */
    
    public boolean deleteConsultantAttachmentById(int id)throws ServiceLocatorException{
        Connection connection=null;
        Statement stmt=null;
        ResultSet rs=null;
        String location ="";
        
        try {
            connection=ConnectionProvider.getInstance().getConnection();
            stmt = connection.createStatement();
              
            rs = stmt.executeQuery("select * from tblRecAttachments where Id="+id);
            while(rs.next()) {
                
                location = rs.getString("AttachmentLocation");
                
            }
            
            File oldFiles = new File(location);
            if(oldFiles.exists()){
                
                oldFiles.delete();
            }
            PreparedStatement  psmt = connection.prepareStatement("delete from tblRecAttachments where Id=?");
            psmt.setInt(1,id);
            int count = psmt.executeUpdate();
            psmt.close();
            psmt=null;
        }catch(SQLException se) {
            throw new ServiceLocatorException(se);
          }finally{
            try{
                if(rs!=null){
                    rs.close();
                    rs = null;
                }
                if(stmt!=null){
                    stmt.close();
                    stmt=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
               throw new ServiceLocatorException(se);
            }
        }
        
        return true;
    }
        
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
    
    public String getAttachmentLocation(int attachmentId) throws ServiceLocatorException {
        String attachmentLocation="";
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select h.filepath from ConsultantAttachmentAction as h where h.id=:attachmentId";
        
        Query query = session.createQuery(SQL_QUERY).setInteger("attachmentId",attachmentId);
        for(Iterator it=query.iterate();it.hasNext();){
            attachmentLocation = (String) it.next();
        }//end of the for loop
        
         try{
                // Closing hibernate session
                session.close();
                session=null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
            }
         
        return attachmentLocation;
    }
    
    
    /*
     * For geting the path based on consultant id from tblRecAttachments
     * 
     * Ajay Tummapla
     */
    
     public String getConsultantAttachmentLocation(int id)throws ServiceLocatorException{
        Connection connection=null;
        Statement stmt=null;
        ResultSet rs=null;
        String location ="";
        
        try {
            connection=ConnectionProvider.getInstance().getConnection();
            stmt = connection.createStatement();
              
            //rs = stmt.executeQuery("SELECT MAX(Id),AttachmentLocation FROM tblRecAttachments WHERE ObjectId ="+id);
            
            rs = stmt.executeQuery("SELECT Id,AttachmentLocation FROM tblRecAttachments "
                    + " WHERE ObjectId = "+id+" AND Id IN (SELECT MAX(Id) FROM tblRecAttachments WHERE ObjectId = "+id+" )");
            while(rs.next()) {
                
                location = rs.getString("AttachmentLocation");
                
            }
          
        }catch(SQLException se) {
            throw new ServiceLocatorException(se);
          }finally{
            try{
                if(rs!=null){
                    rs.close();
                    rs = null;
                }
                if(stmt!=null){
                    stmt.close();
                    stmt=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
               throw new ServiceLocatorException(se);
            }
        }
        
        return location;
    }
    
    
}
