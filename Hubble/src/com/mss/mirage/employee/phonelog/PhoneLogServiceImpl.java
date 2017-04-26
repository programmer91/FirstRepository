/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.employee.phonelog
 *
 * Date    : February 7, 2008, 7:44 PM
 *
 * Author  : Venkateswara Rao Nukala<vnukala@miraclesoft.com>
 *
 * File    : PhoneLogServiceImpl.java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.employee.phonelog;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;

/**
 *
 * @author miracle
 */
public class PhoneLogServiceImpl implements PhoneLogService{
    
    /** Creates a new instance of PhoneLogServiceImpl */
    public PhoneLogServiceImpl() {
    }
    
    /**
     *
     * @param action
     * @throws com.mss.mirage.util.ServiceLocatorException
     * @return
     */
    public boolean addPhoneLog(PhoneLogAction action) throws ServiceLocatorException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        boolean isInserted=false;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement=connection.prepareStatement("insert into tblEmpPhoneLog (LoginId,"+
                    " FromNo ,ToNo, CallDate,CallStartTime," +
                    "CallEndTime,Duration,Description," +
                    "Type,Reconcilled) values(?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,action.getEmployeeLoginId());
            preparedStatement.setString(2,action.getFromPhoneNo());
            preparedStatement.setString(3,action.getToPhoneNo());
            preparedStatement.setDate(4,action.getDate());
            preparedStatement.setTime(5,DateUtility.getInstance().strToSqlTimeObj(action.getStartTime()));
            preparedStatement.setTime(6,DateUtility.getInstance().strToSqlTimeObj(action.getEndTime()));
            preparedStatement.setString(7,action.getCallDuration());
            preparedStatement.setString(8,action.getDescription());
            if(action.getCallType().equals("Personal")){
                preparedStatement.setBoolean(9,true);
            }else{
                preparedStatement.setBoolean(9,false);
            }
            
            preparedStatement.setBoolean(10,action.isReconcilled());
            
            
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            if(x>0){
                isInserted=true;
            }
            
        }catch(SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                    
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        
        return isInserted;
    }
    
    /**
     * this is for editing employee phonelog
     * @param action
     * @param PhoneLogId
     * @throws com.mss.mirage.util.ServiceLocatorException
     * @return
     */
    public boolean editPhoneLog(PhoneLogAction action,int PhoneLogId) throws ServiceLocatorException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        boolean isUpdated=false;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement=connection.prepareStatement("update tblEmpPhoneLog set LoginId=?,FromNo=?,ToNo=? ,CallDate=? ,CallStartTime=?," +
                    "CallEndTime=? ,Duration=?  ,Description=? ,Type =?,Reconcilled =? where Id='"+PhoneLogId+"'" );
            
            preparedStatement.setString(1,action.getEmployeeLoginId());
            preparedStatement.setString(2,action.getFromPhoneNo());
            preparedStatement.setString(3,action.getToPhoneNo());
            preparedStatement.setDate(4,action.getDate());
            preparedStatement.setTime(5,DateUtility.getInstance().strToSqlTimeObj(action.getStartTime()));
            preparedStatement.setTime(6,DateUtility.getInstance().strToSqlTimeObj(action.getEndTime()));
            preparedStatement.setString(7,action.getCallDuration());
            preparedStatement.setString(8,action.getDescription());
            if(action.getCallType().equals("Personal")){
                preparedStatement.setBoolean(9,true);
            }else{
                preparedStatement.setBoolean(9,false);
            }
            preparedStatement.setBoolean(10,action.isReconcilled());
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            if(x>0){
                isUpdated=true;
            }
            
        }catch(SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        
        return isUpdated;
    }
    
    /**
     * this is for deleting the employee phonelog
     * @param action
     * @param PhoneLogId
     * @throws com.mss.mirage.util.ServiceLocatorException
     * @return
     */
    public boolean deletePhoneLog(PhoneLogAction action,int PhoneLogId)  throws ServiceLocatorException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        boolean isDeleted=false;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement=connection.prepareStatement("delete from tblEmpPhoneLog where Id='"+PhoneLogId+"' ");
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            if(x>0){
                isDeleted=true;
            }
        } catch(SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally{
            try{
                
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
            
        }
        return isDeleted;
    }
    
    /**
     * this is for editing the particular record of phonelog
     * @param PhoneLogId
     * @throws com.mss.mirage.util.ServiceLocatorException
     * @return
     */
    public PhoneLogVTO getPhoneLog(int PhoneLogId) throws ServiceLocatorException{
        
        PhoneLogVTO phoneLogVTO=new PhoneLogVTO();
        Connection connection = null;
       // PreparedStatement preparedStatement = null;
        Statement statement=null;
        ResultSet resultSet= null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery("select LoginId,FromNo, ToNo,CallDate,CallStartTime,CallEndTime,Duration,Description,Type from tblEmpPhoneLog where Id='"+PhoneLogId+"'");
            while(resultSet.next()){
                phoneLogVTO.setEmployeeLoginId(resultSet.getString(1));
                phoneLogVTO.setFromPhoneNo(resultSet.getString(2));
                phoneLogVTO.setToPhoneNo(resultSet.getString(3));
                phoneLogVTO.setDate(resultSet.getDate(4));
                phoneLogVTO.setStartTime(resultSet.getString(5).substring(0,5));
                phoneLogVTO.setEndTime(resultSet.getString(6).substring(0,5));
                phoneLogVTO.setCallDuration(resultSet.getString(7));
                phoneLogVTO.setDescription(resultSet.getString(8));
                if(resultSet.getBoolean(9))
                    phoneLogVTO.setCallType("Personal");
                else
                    phoneLogVTO.setCallType("Official");
            }
        } catch(SQLException sqle){
            throw new ServiceLocatorException(sqle);
        } finally{
            try{
                  if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                    statement.close();
                    statement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        return phoneLogVTO;
    }
}
