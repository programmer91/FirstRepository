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
 * File    : ConsultantDetailsServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.recruitment.consultantdetails;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.SQLException;

/**
 * <p> This class implements all the Consultant Details Business logic methods  which are defined
 * in ConsultantService interface. <p>
 *
 * @version 2.0, September 24, 2007.
 *
 * @author kondaiah Adapa <kadapa@miraclesoft.com>
 *
 * @See com.mss.mirage.recruitment.ConsultantService
 */


/**
 * @author kondaiah Adapa <kadapa@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 *
 */
public class ConsultantDetailsServiceImpl implements ConsultantDetailsService{
    
  
    
    /** Creates a new instance of ConsultantDetailsServiceImpl */
    public ConsultantDetailsServiceImpl() {
    }
    
    /**  This method can be used to  add a consultant skills.
     *@param consultantDetailsAction is a ConsultantDetailsAction reference
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     *@return boolean isInsert- its returns isInset when consultant skills are inserted into
     * data base by passing a operation type is Ins.
     */
    public boolean addConsultantSkills(ConsultantDetailsAction consultantDetailsAction)throws ServiceLocatorException{
        boolean isInsert=false;
        Connection connection=null;
        CallableStatement callableStatement=null;
        
        try {
            connection=ConnectionProvider.getInstance().getConnection();
            callableStatement=connection.prepareCall("{call spRecruitment (?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(2,consultantDetailsAction.getEmpId());
            callableStatement.setInt(1,0);
            //callableStatement.setInt(1,consultantDetailsAction.getEmpId());
            callableStatement.setString(3,consultantDetailsAction.getYearsOfExperience().trim());
            callableStatement.setString(4,consultantDetailsAction.getSkillSetUtilized().trim());
            callableStatement.setString(5,consultantDetailsAction.getSkillSet().trim());
            callableStatement.setString(6,consultantDetailsAction.getProjectInfo().trim());
            callableStatement.setString(7,"Ins");
            callableStatement.registerOutParameter(8,java.sql.Types.INTEGER);
            isInsert=callableStatement.execute();
            
            
            
        } catch(SQLException se) {
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
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
    
    
    /**  This method can be used to get a consultant skills.
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     *@param  id
     *
     *@return  ConsultantSkillBean returns depends upon the id.
     *which depends on whether all the values are successfully inserted or not.
     *
     */
    public  ConsultantSkillBean getConsultantDetailsAction(int id) throws ServiceLocatorException{
        ConsultantSkillBean bean=null;
        Connection connection=null;
        PreparedStatement preStmt=null;
        ResultSet rs=null;
        try {
            bean=new ConsultantSkillBean();
            connection=ConnectionProvider.getInstance().getConnection();
            preStmt=connection.prepareStatement("SELECT * FROM tblRecSkills WHERE Id=?");
            preStmt.setInt(1,id);
            rs=preStmt.executeQuery();
            if(rs.next()) {
                bean.setId(rs.getInt(1));
                bean.setEmpId(rs.getInt(2));
                bean.setYearsOfExperience(rs.getString(3));
                bean.setSkillsetUtilized(rs.getString(4));
                bean.setProjectInfo(rs.getString(6));
                bean.setSkillSet(rs.getString(5));
                bean.setActionName("editConsultantSkills");
            }
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(rs !=null){
                    rs.close();
                    rs= null;
                }
                if(preStmt!=null){
                    preStmt.close();
                    preStmt=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
                
            } catch(SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return bean;
    }
    
    
    /**  This method can be used to edit a consultant skills.
     *@ param consultantDetailsAction is a ConsultantDetailsAction reference.
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     * @return  boolean isUpdate-its return isUpdate when the consultant skills are edited and updated into data base.
     */
    public boolean editConsultantSkills(ConsultantDetailsAction consultantDetailsAction)throws ServiceLocatorException {
        boolean isUpdate=false;
        Connection connection=null;
        CallableStatement callableStatement=null;
        
        try {
            
            connection=ConnectionProvider.getInstance().getConnection();
            
            callableStatement=connection.prepareCall("{call spRecruitment (?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(2,12);
            callableStatement.setInt(1,consultantDetailsAction.getId());
            //callableStatement.setInt(1,consultantDetailsAction.getEmpId());
            callableStatement.setString(3,consultantDetailsAction.getYearsOfExperience().trim());
            callableStatement.setString(4,consultantDetailsAction.getSkillSetUtilized().trim());
            callableStatement.setString(5,consultantDetailsAction.getSkillSet().trim());
            callableStatement.setString(6,consultantDetailsAction.getProjectInfo().trim());
            callableStatement.setString(7,"upd");
            callableStatement.registerOutParameter(8,java.sql.Types.INTEGER);
            isUpdate=callableStatement.execute();
            
           
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return isUpdate;
    }
    /**  This method can be used to delete a consultant skills.
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     *@param consultantDetailsAction ConsultantDetailsAction reference.
     *
     *@return boolean isDelete-its return isDelete when consultant skills are deleted from the data base table.
     */
    public boolean deleteConsultantSkills(ConsultantDetailsAction consultantDetailsAction) throws ServiceLocatorException{
        boolean isDelete=false;
        Connection connection=null;
        CallableStatement callableStatement=null;
        try{
            
            connection=ConnectionProvider.getInstance().getConnection();
            
            callableStatement=connection.prepareCall("{call spRecruitment (?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(2,12);
            callableStatement.setInt(1,consultantDetailsAction.getId());
            callableStatement.setString(3,"null");
            callableStatement.setString(4,"null");
            callableStatement.setString(5,"null");
            callableStatement.setString(6,"null");
            callableStatement.setString(7,"del");
            callableStatement.registerOutParameter(8,java.sql.Types.INTEGER);
            isDelete=callableStatement.execute();
            
          
            
        } catch(SQLException se) {
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return isDelete;
    }
    
    /**  This method can be used to  get the consultant details.
     *
     *@param id
     *@throws  ServiceLocatorException
     * If a ServiceLocatorException exists and its <code>{
     *@link    com.mss.mirage.util.ServiceLocatorException
     * }</code>
     *@return  consultantVTO-its return type is consultantVTO when user able to getting the
     * consultant details from the data base.
     *
     *
     */
    
    public ConsultantVTO getConsultantDetails(int id)throws ServiceLocatorException {
        ConsultantVTO bean=null;
        Connection connection=null;
        PreparedStatement preStmt=null;
        ResultSet rs=null;
        try {
            bean=new  ConsultantVTO();
            connection=ConnectionProvider.getInstance().getConnection();
            
            preStmt=connection.prepareStatement("SELECT LName,FName,MName,Email2,CellPhoneNo FROM tblRecConsultant WHERE Id=?");
            preStmt.setInt(1,id);
            rs=preStmt.executeQuery();
            if(rs.next()) {
                bean.setLastName(rs.getString(1));
                bean.setFirstName(rs.getString(2));
                bean.setMiddleName(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setPhoneNo(rs.getString(5));
            }
          
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                    rs = null;
                }
                if( preStmt != null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
                
            } catch(SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return bean;
    }
}
