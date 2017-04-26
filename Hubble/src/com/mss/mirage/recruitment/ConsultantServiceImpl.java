/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment
 *
 * Date    :   Created on September 27, 2007, 8:01 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.recruitment;

import com.mss.mirage.util.*;
//import com.sun.org.apache.bcel.internal.generic.Type;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.Timestamp;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.*;
import java.util.regex.Pattern;
/**
 * <p> This class implements all the Consultant Business logic methods  which are defined
 * in ConsultantService interface. <p>
 *
 * @version 2.0, September 24, 2007.
 *
 * @author kondaiah Adapa <kadapa@miraclesoft.com>
 *
 * @See com.mss.mirage.recruitment.ConsultantService
 */

/**
 *
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 *
 */
public class ConsultantServiceImpl implements ConsultantService {
    
    /** Creates a new instance of ConsultantServiceImpl */
    public ConsultantServiceImpl() {
    }
    
    /**  This method can be useful  to add a new consultant details.
     *
     * @param  consultantAction is A ConsultantAction Reference
     *
     * @return boolean  isSuccess -  if all the values are successfully inserted.
     *
     *
     */
    public boolean addConsultant(ConsultantAction consultantAction) throws ServiceLocatorException{
        boolean isSuccess=false;
        try{
            isSuccess= executeQuery(consultantAction,"Ins");
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return isSuccess;
    }
    
    /**  This method can be used to  get the consultant skills.
     *
     *  @param  consultantId
     *
     *  @throws  ServiceLocatorException
     *    If a ServiceLocatorException exists and its <code>{
     * @link     com.mss.mirage.util.ServiceLocatorException}</code>
     *@see com.mss.mirage.util.ServiceLocatorException.
     *
     *@return  consultantVTO-its return type is consultantVTO when getting the all the login details of the
     *consultant from the data base.
     *
     *
     */
    public ConsultantVTO getConsultant(int consultantId) throws ServiceLocatorException{
        /** connection is a reference variable for Connection . */
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        queryString = "SELECT * FROM tblRecConsultant WHERE Id="+consultantId;
        ConsultantVTO consultantVTO = new ConsultantVTO();
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        ResultSet resultSetTemp=null;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            consultantVTO.setId(consultantId);
            consultantVTO.setActionName("saveEditDetails");
            while(resultSet.next()){
                consultantVTO.setCountry(resultSet.getString(4));
                consultantVTO.setPracticeId(resultSet.getString(5));
                consultantVTO.setTitleTypeId(resultSet.getString(6));
                consultantVTO.setConsultantType(resultSet.getString(7));
                consultantVTO.setCurStatus(resultSet.getString(8));
                consultantVTO.setRecContactId(resultSet.getInt(9));
                consultantVTO.setLaName(resultSet.getString(11));
                consultantVTO.setFiName(resultSet.getString(12));
                consultantVTO.setMiName(resultSet.getString(13));
                consultantVTO.setAliasName(resultSet.getString(14));
                consultantVTO.setSsn(resultSet.getString(15));
                consultantVTO.setGender(resultSet.getString(16));
                consultantVTO.setMaritalStatus(resultSet.getString(17));
                consultantVTO.setEmail1(resultSet.getString(18));
                consultantVTO.setEmail2(resultSet.getString(19));
                consultantVTO.setEmail3(resultSet.getString(20));
                consultantVTO.setWorkPhoneNo(resultSet.getString(21));
                consultantVTO.setHomePhoneNo(resultSet.getString(22));
                consultantVTO.setAlterPhoneNo(resultSet.getString(23));
                consultantVTO.setCellPhoneNo(resultSet.getString(24));
                consultantVTO.setHotelPhoneNo(resultSet.getString(25));
                consultantVTO.setIndiaPhoneNo(resultSet.getString(26));
                consultantVTO.setFaxPhoneNo(resultSet.getString(27));
                consultantVTO.setRefferedBy(resultSet.getString(28));
                consultantVTO.setRefferalBonus(resultSet.getString(29));
               
                if(resultSet.getString("AvailableFrom") == null){
                    consultantVTO.setAvailableFrom(null);
                }else {
                    consultantVTO.setAvailableFrom(resultSet.getDate("AvailableFrom"));
                }
                
                 if(resultSet.getString("Description") == null){
                    consultantVTO.setAvailableFrom(null);
                }else {
                    consultantVTO.setDescription(resultSet.getString("Description"));
                }
                
                if(resultSet.getDate(34) == null) {
                    consultantVTO.setViewOfficialDob(null);
                } else {
                    consultantVTO.setViewOfficialDob(resultSet.getDate(34).toString());
                }
                
                if(resultSet.getDate(35) == null) {
                    consultantVTO.setViewOfficialDob(null);
                } else {
                    consultantVTO.setViewDob(resultSet.getDate(35).toString());
                }
                
                if(resultSet.getDate(36) == null) {
                    consultantVTO.setViewOfficialDob(null);
                } else {
                    consultantVTO.setViewHireDate(resultSet.getDate(36).toString());
                }
                
                if(resultSet.getDate(37) == null) {
                    consultantVTO.setViewOfficialDob(null);
                } else {
                    consultantVTO.setViewAnniversaryDate(resultSet.getDate(37).toString());
                }
                
                if(resultSet.getDate(38) == null) {
                    consultantVTO.setViewOfficialDob(null);
                } else {
                    consultantVTO.setViewFirstContactDate(resultSet.getDate(38).toString());
                }
                consultantVTO.setPreferedQuestion(resultSet.getString(39));
                consultantVTO.setPreferedAnswer(resultSet.getString(40));
                consultantVTO.setComments(resultSet.getString(41));
                consultantVTO.setRatePerHour(resultSet.getString(42));
                consultantVTO.setCurrentEmployer(resultSet.getString(43));
                consultantVTO.setRateNegotiated(resultSet.getString(44));
                
                if(resultSet.getDate(45) == null) {
                    consultantVTO.setViewOfficialDob(null);
                } else {
                    consultantVTO.setViewLastContactDate(resultSet.getDate(45).toString());
                }
                consultantVTO.setLastContactBy(resultSet.getInt(46));
                consultantVTO.setWorkAuthorization(resultSet.getString(51));
                int addressId=resultSet.getInt(30);
                
                consultantVTO.setOffAddressId(addressId);
                if(addressId!=0) {
                    preStmtTemp=connection.prepareStatement("SELECT * FROM tblRecAddress WHERE Id=?");
                    preStmtTemp.setInt(1,addressId);
                    resultSetTemp=preStmtTemp.executeQuery();
                    while(resultSetTemp.next()) {
                        consultantVTO.setOffAddressLine1(resultSetTemp.getString(3));
                        consultantVTO.setOffAddressLine2(resultSetTemp.getString(4));
                        consultantVTO.setOffCity(resultSetTemp.getString(5));
                        consultantVTO.setOffState(resultSetTemp.getString(6));
                        consultantVTO.setOffMailStop(resultSetTemp.getString(7));
                        consultantVTO.setOffZip(resultSetTemp.getString(8));
                        consultantVTO.setOffShoreCountry(resultSetTemp.getString(9));
                    }
                    
                    resultSetTemp.close();
                    resultSetTemp=null;
                    
                    preStmtTemp.close();
                    preStmtTemp=null;
                    
                }
                addressId=resultSet.getInt(31);
                consultantVTO.setCurAddressId(addressId);
                if(addressId!=0) {
                    preStmtTemp=connection.prepareStatement("SELECT * FROM tblRecAddress WHERE Id=?");
                    preStmtTemp.setInt(1,addressId);
                    resultSetTemp=preStmtTemp.executeQuery();
                    while(resultSetTemp.next()) {
                        consultantVTO.setCurAddressLine1(resultSetTemp.getString(3));
                        consultantVTO.setCurAddressLine2(resultSetTemp.getString(4));
                        consultantVTO.setCurCity(resultSetTemp.getString(5));
                        consultantVTO.setCurState(resultSetTemp.getString(6));
                        consultantVTO.setCurMailStop(resultSetTemp.getString(7));
                        consultantVTO.setCurZip(resultSetTemp.getString(8));
                        consultantVTO.setProjectCountry(resultSetTemp.getString(9));
                    }
                    
                    resultSetTemp.close();
                    resultSetTemp=null;
                    
                    preStmtTemp.close();
                    preStmtTemp=null;
                    
                }
                addressId=resultSet.getInt(32);
                consultantVTO.setHomAddressId(addressId);
                if(addressId!=0) {
                    preStmtTemp=connection.prepareStatement("SELECT * FROM tblRecAddress WHERE Id=?");
                    preStmtTemp.setInt(1,addressId);
                    resultSetTemp=preStmtTemp.executeQuery();
                    while(resultSetTemp.next()) {
                        consultantVTO.setAddressLine1(resultSetTemp.getString(3));
                        consultantVTO.setAddressLine2(resultSetTemp.getString(4));
                        consultantVTO.setCity(resultSetTemp.getString(5));
                        consultantVTO.setState(resultSetTemp.getString(6));
                        consultantVTO.setMailStop(resultSetTemp.getString(7));
                        consultantVTO.setZip(resultSetTemp.getString(8));
                        consultantVTO.setHomeCountry(resultSetTemp.getString(9));
                    }
                    
                    resultSetTemp.close();
                    resultSetTemp=null;
                    
                    preStmtTemp.close();
                    preStmtTemp=null;
                    
                }
                addressId=resultSet.getInt(33);
                consultantVTO.setOthAddressId(addressId);
                if(addressId!=0) {
                    preStmtTemp=connection.prepareStatement("SELECT * FROM tblRecAddress WHERE Id=?");
                    preStmtTemp.setInt(1,addressId);
                    resultSetTemp=preStmtTemp.executeQuery();
                    while(resultSetTemp.next()) {
                        consultantVTO.setOthAddressLine1(resultSetTemp.getString(3));
                        consultantVTO.setOthAddressLine2(resultSetTemp.getString(4));
                        consultantVTO.setOthCity(resultSetTemp.getString(5));
                        consultantVTO.setOthState(resultSetTemp.getString(6));
                        consultantVTO.setOthMailStop(resultSetTemp.getString(7));
                        consultantVTO.setOthZip(resultSetTemp.getString(8));
                        consultantVTO.setOtherCountry(resultSetTemp.getString(9));
                    }
                    resultSetTemp.close();
                    resultSetTemp=null;
                    
                    
                    preStmtTemp.close();
                    preStmtTemp=null;
                    
                }
                consultantVTO.setConsultantRecordId(consultantId);
            }
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSetTemp!=null){
                    resultSetTemp.close();
                    resultSetTemp=null;
                }
                if( preStmtTemp!=null){
                    preStmtTemp.close();
                    preStmtTemp=null;
                }
                if(resultSet!=null){
                    resultSet.close();
                    resultSet=null;
                }
                  if( statement!=null){
                    statement.close();
                    statement=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
                
                
                
                
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return consultantVTO;
    }
    
    /**  This method can be used to  edit the consultant all details.
     *
     *  @param  consultantAction ConsultantAction reference,operationType.
     *
     *  @throws  ServiceLocatorException
     *
     *    If a ServiceLocatorException exists and its <code>{
     * @link    com.mss.mirage.util.ServiceLocatorException}</code>
     * @see    com.mss.mirage.util.ServiceLocatorException.
     *
     *
     * @return  boolean  isSuccess-
     */
    public boolean executeQuery(ConsultantAction consultantAction,String operationType) throws Exception {
        boolean isSuccess=false;
        /** connection is a reference variable for Connection . */
        Connection connection= null;
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement=null;
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        try {
            
            connection=ConnectionProvider.getInstance().getConnection();
            callableStatement=connection.prepareCall("{call spConsultant(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");//,?
            
            callableStatement.setString(1,consultantAction.getFiName());
            callableStatement.setString(2,consultantAction.getLaName());
            callableStatement.setString(3,consultantAction.getMiName());
            callableStatement.setString(4,consultantAction.getAliasName());
            callableStatement.setString(5,consultantAction.getSsn());
            
            callableStatement.setString(6,consultantAction.getGender());
            callableStatement.setString(7,consultantAction.getMaritalStatus());
            callableStatement.setString(8,consultantAction.getEmail1());
            callableStatement.setString(9,consultantAction.getEmail2());
            callableStatement.setString(10,consultantAction.getEmail3());
            
            callableStatement.setString(11,consultantAction.getCountry());
            callableStatement.setString(12,consultantAction.getWorkPhoneNo());
            callableStatement.setString(13,consultantAction.getHomePhoneNo());
            callableStatement.setString(14,consultantAction.getAlterPhoneNo());
            callableStatement.setString(15,consultantAction.getCellPhoneNo());
            
            callableStatement.setString(16,consultantAction.getHotelPhoneNo());
            callableStatement.setString(17,consultantAction.getIndiaPhoneNo());
            callableStatement.setString(18,consultantAction.getFaxPhoneNo());
            callableStatement.setString(19,consultantAction.getRefferedBy());
            callableStatement.setString(20,consultantAction.getRefferalBonus());
            
            if(consultantAction.getOfficialDob() == null) {
                callableStatement.setDate(21,null);
            } else {
                callableStatement.setDate(21,new java.sql.Date(consultantAction.getOfficialDob().getTime()));
            }
            
            if(consultantAction.getDob() == null) {
                callableStatement.setDate(22,null);
            } else {
                callableStatement.setDate(22,new java.sql.Date(consultantAction.getDob().getTime()));
            }
            
            if(consultantAction.getHireDate() == null) {
                callableStatement.setDate(23,null);
            } else {
                callableStatement.setDate(23,new java.sql.Date(consultantAction.getHireDate().getTime()));
            }
            
            if(consultantAction.getAnniversaryDate() == null) {
                callableStatement.setDate(24,null);
            } else {
                callableStatement.setDate(24,new java.sql.Date(consultantAction.getAnniversaryDate().getTime()));
            }
            
            if(consultantAction.getFirstContactDate() == null) {
                callableStatement.setDate(25,null);
            } else {
                callableStatement.setDate(25,new java.sql.Date(consultantAction.getFirstContactDate().getTime()));
            }
            
            callableStatement.setString(26,consultantAction.getPreferedQuestion());
            callableStatement.setString(27,consultantAction.getPreferedAnswer());
            callableStatement.setString(28,consultantAction.getLoginId());
            callableStatement.setString(29,consultantAction.getPassword());
            callableStatement.setString(30,consultantAction.getPracticeId());
            
            callableStatement.setString(31,consultantAction.getTitleTypeId());
            callableStatement.setString(32,consultantAction.getConsultantType());
            callableStatement.setString(33,consultantAction.getCurStatus());
            callableStatement.setInt(34,consultantAction.getRecContactId());
            callableStatement.setInt(35,consultantAction.getDeletedFlag());
            callableStatement.setString(36,consultantAction.getComments());
            callableStatement.setString(37,consultantAction.getRatePerHour());
            callableStatement.setString(38,consultantAction.getCurrentEmployer());
            callableStatement.setString(39,consultantAction.getRateNegotiated());
            
            if(consultantAction.getLastContactDate() == null) {
                callableStatement.setDate(40,null);
            } else {
                callableStatement.setDate(40,new java.sql.Date(consultantAction.getLastContactDate().getTime()));
            }
            callableStatement.setInt(41,consultantAction.getLastContactBy());
            
            callableStatement.setString(42,consultantAction.getCreatedBy());
            callableStatement.setTimestamp(43,new java.sql.Timestamp(new java.util.Date().getTime()));
            callableStatement.setString(44,consultantAction.getModifiedBy());
            callableStatement.setTimestamp(45,new java.sql.Timestamp(new java.util.Date().getTime()));
            
            if(consultantAction.getAddressLine1()==null || consultantAction.getAddressLine1().equals("") )
                callableStatement.setString(46,null);
            else
                callableStatement.setString(46,consultantAction.getAddressLine1());
            callableStatement.setString(47,consultantAction.getAddressLine2());
            callableStatement.setString(48,consultantAction.getCity());
            callableStatement.setString(49,consultantAction.getMailStop());
            callableStatement.setString(50,consultantAction.getState());
            callableStatement.setString(51,consultantAction.getHomeCountry());
            callableStatement.setString(52,consultantAction.getZip());
            if( consultantAction.getCurAddressLine1()==null ||  consultantAction.getCurAddressLine1().equals(""))
                callableStatement.setString(53,null);
            else
                callableStatement.setString(53,consultantAction.getCurAddressLine1());
            callableStatement.setString(54,consultantAction.getCurAddressLine2());
            callableStatement.setString(55,consultantAction.getCurCity());
            callableStatement.setString(56,consultantAction.getCurMailStop());
            callableStatement.setString(57,consultantAction.getCurState());
            callableStatement.setString(58,consultantAction.getProjectCountry());
            callableStatement.setString(59,consultantAction.getCurZip());
            if(consultantAction.getOffAddressLine1()==null || consultantAction.getOffAddressLine1().equals(""))
                callableStatement.setString(60,null);
            else
                callableStatement.setString(60,consultantAction.getOffAddressLine1());
            callableStatement.setString(61,consultantAction.getOffAddressLine2());
            callableStatement.setString(62,consultantAction.getOffCity());
            callableStatement.setString(63,consultantAction.getOffMailStop());
            callableStatement.setString(64,consultantAction.getOffState());
            callableStatement.setString(65,consultantAction.getOffShoreCountry());
            callableStatement.setString(66,consultantAction.getOffZip());
            if(consultantAction.getOthAddressLine1()==null || consultantAction.getOthAddressLine1().equals(""))
                callableStatement.setString(67,null);
            else
                callableStatement.setString(67,consultantAction.getOthAddressLine1());
            callableStatement.setString(68,consultantAction.getOthAddressLine2());
            callableStatement.setString(69,consultantAction.getOthCity());
            callableStatement.setString(70,consultantAction.getOthMailStop());
            callableStatement.setString(71,consultantAction.getOthState());
            callableStatement.setString(72,consultantAction.getOtherCountry());
            callableStatement.setString(73,consultantAction.getOthZip());
            callableStatement.setInt(74,consultantAction.getConsultantId());
            callableStatement.setInt(75,consultantAction.getHomAddressId());
            callableStatement.setInt(76,consultantAction.getOthAddressId());
            callableStatement.setInt(77,consultantAction.getOffAddressId());
            callableStatement.setInt(78,consultantAction.getCurAddressId());
            if(operationType.equals("Ins")) {
                callableStatement.setString(79,"Ins");
            } else if(operationType.equals("Edit")) {
                callableStatement.setString(79,"Edit");
            }
            //callableStatement.registerOutParameter(80,Types.INTEGER);
            callableStatement.setString(80,consultantAction.getWorkAuthorization());
            callableStatement.registerOutParameter(81,Types.INTEGER);
            callableStatement.executeUpdate();
            
            callableStatement.close();
            callableStatement=null;
            connection.close();
            connection=null;
            
        } catch(SQLException se) {
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
                
                
                
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return isSuccess;
    }
    
    /**  This method can be used to  edit the consultant skills and those will be updated in database.
     *
     * @param  consultantAction ConsultantAction reference
     *
     *  @throws  ServiceLocatorException
     *    If a ServiceLocatorException exists and its <code>{
     * @link com.mss.mirage.util.ServiceLocatorException}</code>
     * @see com.mss.mirage.util.ServiceLocatorException.
     *
     * @return boolean isSuccess-its returns successs when the consultant details are editing by passing a operion type Edit.
     */
    
    public boolean editConsultant(ConsultantAction consultantAction) throws ServiceLocatorException {
        boolean isSuccess=false;
        try{
            isSuccess= executeQuery(consultantAction,"Edit");
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return isSuccess;
    }
    
    /**  This method can be used to  delete the particular consultant.
     *
     * @param  consultantId
     *
     *  @throws  ServiceLocatorException
     *    If a ServiceLocatorException exists and its <code>{
     * @linkcom.mss.mirage.util.ServiceLocatorException
     * }</code>
     *
     * @see com.mss.mirage.util.ServiceLocatorException.
     *
     *@return boolean isSuccess-its return success when consultant deleting by using a consultant id.
     */
    
    
    public boolean deleteConsultant(int consultantId) throws ServiceLocatorException {
        boolean isSuccess;
        /** connection is a reference variable for Connection . */
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            
            connection=ConnectionProvider.getInstance().getConnection();
            preStmt=connection.prepareStatement("SELECT OffShoreAddressId,CurProjectAddressId,HomeAddressId,OtherAddressId FROM tblRecConsultant WHERE Id=?");
            preStmt.setInt(1,consultantId);
            resultSet=preStmt.executeQuery();
            while(resultSet.next()) {
                for(int index=1;index<5;index++) {
                    
                    // Calling a doDelete() method to delete a address of the consultant
                    doDelete(resultSet.getInt( index),connection);
                    
                }
                
            }
            resultSet.close();
            preStmt.close();
            preStmt=connection.prepareStatement("DELETE  FROM tblRecConsultant WHERE Id=?");
            preStmt.setInt(1,consultantId);
            if(preStmt.execute())
                isSuccess=true;
            else
                isSuccess=false;
            preStmt.close();
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }finally{
            try{
                if(preStmt!=null){
                    preStmt.close();
                    preStmt=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
                     
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return isSuccess;
    }
    
    /**  This method can be used to  delete the different addresses of particular consultant.
     *
     * @param  addressId,connection
     *
     * @throws NullPointerException
     *         If a NullPointerException exists and its <code>
     * {
     * @link java.lang.NullPointerException
     * }
     * </code>
     *
     */
    public void doDelete(int addressId,Connection connection) throws Exception {
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString;
        if(addressId!=0) {
            preStmtTemp=connection.prepareStatement("DELETE  FROM tblRecAddress WHERE Id=?");
            preStmtTemp.setInt(1,addressId);
            preStmtTemp.execute();
            preStmtTemp.close();
        }
    }
    
    /**  This method can be used to  add the different consultants.
     *
     * @param  consultantAction,connection
     *
     * @throws NullPointerException
     *         If a NullPointerException exists and its <code>
     * {
     * @link java.lang.NullPointerException
     * }
     * </code>
     *
     */
  public  int doAddConsultant(ConsultantAction consultantAction) throws ServiceLocatorException {
          
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            connection=ConnectionProvider.getInstance().getConnection();
           // preStmt = connection.prepareStatement("SELECT  ifnull(max(Id),0)+1 as tempId FROM tblRecConsultant");
         //   resultSet = preStmt.executeQuery();
        //    resultSet.next();
         //   String objId = resultSet.getString("tempId");
         //   int id = Integer.parseInt(objId);
         //   preStmt.close();
         //   resultSet.close();
            preStmt = connection.prepareStatement("INSERT INTO tblRecConsultant(FName,LName,MName,Email2,TitleTypeId,Gender," +
                    "WorkPhoneNo,HomePhoneNo,CellPhoneNo,WorkAuthorization,SkillSet,PracticeId,Country,RefferedBy," +
                    "RatePerHour,Comments,Password,CreatedBy,Industry,Source,CreatedDate,AvailableFrom,Description,OrgId,"
                    + "Experience,TechnicallyEvaluated,Available,PreferredState) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            /*preStmt = connection.prepareStatement("INSERT INTO tblRecConsultant(Id,FName,LName,MName,Email2,TitleTypeId,Gender," +
                    "WorkPhoneNo,HomePhoneNo,CellPhoneNo,WorkAuthorization,SkillSet,PracticeId,Country,RefferedBy," +
                    "RatePerHour,Comments,Password,CreatedBy,Industry,Source,CreatedDate,AvailableFrom,Description,OrgId,"
                    + "LastContactDate,Experience,TechnicallyEvaluated,Available,PreferredState) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); */
           // preStmt.setInt(1,id);
            preStmt.setString(1,consultantAction.getFiName());
            preStmt.setString(2,consultantAction.getLaName());
            preStmt.setString(3,consultantAction.getMiName());
            preStmt.setString(4,consultantAction.getEmail2());
           preStmt.setString(5,consultantAction.getTitleTypeId());
            preStmt.setString(6,consultantAction.getGender());
            preStmt.setString(7,consultantAction.getWorkPhoneNo());
            preStmt.setString(8,consultantAction.getHomePhoneNo());
            preStmt.setString(9,consultantAction.getCellPhoneNo());
            preStmt.setString(10,consultantAction.getWorkAuthorization());
            preStmt.setString(11,consultantAction.getSkills());
            preStmt.setString(12,consultantAction.getPracticeId());
            preStmt.setString(13,consultantAction.getCountry());
            preStmt.setString(14,consultantAction.getRefferedBy());
            preStmt.setString(15,consultantAction.getRatePerHour());
            preStmt.setString(16,consultantAction.getComments());
            preStmt.setString(17,"Attachment");
            preStmt.setString(18,consultantAction.getCreatedBy());
            preStmt.setString(19,consultantAction.getIndustryId());
         
            
            
            if(consultantAction.getSource().equals("")) {
                preStmt.setString(20,null);
            }else {
                preStmt.setString(20,consultantAction.getSource());
            }
            //consultantAction.setDate(new Timestamp(new java.util.Date().getTime()));
            consultantAction.setDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            //System.err.println("Upload File name is.."+consultantAction.getUploadFileName());
            preStmt.setTimestamp(21,consultantAction.getDate());
            preStmt.setDate(22,consultantAction.getAvailableFrom());
            preStmt.setString(23,consultantAction.getDescription());
            
               String resorg = consultantAction.getOrg();
            if(resorg.equals(""))
            {
                preStmt.setString(24,"1USA");
            }
            else
            {
             preStmt.setString(24,consultantAction.getOrg());
            }
            DateUtility dateUtility = DateUtility.getInstance();
            
            //preStmt.setDate(26,dateUtility.getMysqlDate(dateUtility.getToDayDateToView()));
            preStmt.setString(25,consultantAction.getExp());
            preStmt.setString(26,consultantAction.getTechEval());
            preStmt.setString(27,consultantAction.getAvailable());
            preStmt.setString(28,consultantAction.getPreferredState());
            isSuccess = preStmt.executeUpdate();
            
             resultSet = preStmt.getGeneratedKeys();
           if (resultSet.next()) {
                //user.setId(generatedKeys.getLong(1));
                consultantAction.setObjectId(String.valueOf(resultSet.getInt(1)));
            }
            
           // preStmt.close();
           // preStmt=null;
           // connection.close();
           // connection=null;
        } catch(SQLException se) {
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet=null;
                }
                if(preStmt!=null){
                    preStmt.close();
                    preStmt=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                se.printStackTrace();
                //throw new ServiceLocatorException(se);
            }
        }
        return isSuccess;
    }
    
    /**  This method can be used to edit the different consultants.
     *
     * @param  consultantAction,connection
     *
     * @throws NullPointerException
     *         If a NullPointerException exists and its <code>
     * {
     * @link java.lang.NullPointerException
     * }
     * </code>
     *
     */
    public int doEdit(ConsultantAction consultantAction) throws ServiceLocatorException {
        
        int isSuccess = 0;
        
        
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
           // System.out.println("in do edit--->>>");
            connection=ConnectionProvider.getInstance().getConnection();
            //preStmt = connection.prepareStatement("SELECT  ifnull(max(Id),0)+1 as tempId FROM tblRecConsultant");
            //resultSet = preStmt.executeQuery();
            //resultSet.next();
            //String objId = resultSet.getString("tempId");
            //int id = Integer.parseInt(objId);
            //preStmt.close();
            //resultSet.close();
            //consultantAction.setDate(new Timestamp(new java.util.Date().getTime()));
            consultantAction.setDate(DateUtility.getInstance().getCurrentMySqlDateTime());
           // System.out.println("date---------------->"+consultantAction.getDate());
            if(consultantAction.getSource().equals("")) {
                consultantAction.setSource("null");
            }
            
           preStmt = connection.prepareStatement("UPDATE tblRecConsultant set FName='"+consultantAction.getFiName()+"',LName='"+consultantAction.getLaName()+"'," +
                    "MName='"+consultantAction.getMiName()+"',Email2='"+consultantAction.getEmail2()+"',TitleTypeId='"+consultantAction.getTitleTypeId()+"'," +
                    "Gender='"+consultantAction.getGender()+"',WorkPhoneNo='"+consultantAction.getWorkPhoneNo()+"',HomePhoneNo='"+consultantAction.getHomePhoneNo()+"'," +
                    "CellPhoneNo='"+consultantAction.getCellPhoneNo()+"',SkillSet='"+consultantAction.getSkills()+"',PracticeId='"+consultantAction.getPracticeId()+"'," +
                    "RatePerHour='"+consultantAction.getRatePerHour()+"',Comments='"+consultantAction.getComments()+"',ModifiedBy='"+consultantAction.getModifiedBy()+"'," +
                    "WorkAuthorization='"+consultantAction.getWorkAuthorization()+"',RefferedBy='"+consultantAction.getRefferedBy()+"',Country='"+consultantAction.getCountry()+"'," +
                    "Industry='"+consultantAction.getIndustryId()+"',ModifiedDate='"+consultantAction.getDate()+"',Source='"+consultantAction.getSource()+"'" + 
                   
                   
                    ",AvailableFrom=?,Description='"+consultantAction.getDescription()+"', OrgId = '"+consultantAction.getOrg()+"' "
                   // " WHERE Id='"+consultantAction.getObjectId()+"'");
           
           + " , TechnicallyEvaluated ='"+consultantAction.getTechEval()+"' , Experience ='"+consultantAction.getExp()+"'"+
                    "  ,Available='"+consultantAction.getAvailable()+"' ,PreferredState='"+consultantAction.getPreferredState()+"'  WHERE Id='"+consultantAction.getObjectId()+"'");
           
           preStmt.setDate(1,consultantAction.getAvailableFrom());
            
           // System.out.println("after preStmt setDate()");
            isSuccess = preStmt.executeUpdate();
           // System.out.println("after executeUpdate()");
           // preStmt.close();
            //preStmt=null;
            //connection.close();
            //connection=null;
        } catch(SQLException se) {
            //se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preStmt!=null){
                    preStmt.close();
                    preStmt=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
//                se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return isSuccess;
    }
    
    
    public boolean attachResume(ConsultantAction consultantAction) throws ServiceLocatorException {
        boolean isInsert=false;
        try {
           //for get session factory instance from hibernate service locator
            Session session=HibernateServiceLocator.getInstance().getSession(); 
            Transaction trns=session.beginTransaction();
            trns.begin();
            
           // consultantAction.setDate(new Timestamp(new java.util.Date().getTime()));
             consultantAction.setDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            consultantAction.setObjectIdinInt(Integer.parseInt(consultantAction.getObjectId().trim()));
            //Save values in DataBase
            session.save(consultantAction);
            session.flush();
            trns.commit();
            isInsert=true;
            session.clear();
            session.close();
        } catch (Exception ex) {
            //System.err.println("Impl Catch  "+ex.getMessage());
            throw new ServiceLocatorException(ex);
        }
        return isInsert;
    }
    
   public int newAttachResume(ConsultantAction consultantAction) throws ServiceLocatorException{
        int isSuccess = 0;
        Connection connection = null;
        PreparedStatement preStmt=null;
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            
             
            // consultantAction.setDate(new Timestamp(new java.util.Date().getTime()));
             consultantAction.setDate(DateUtility.getInstance().getCurrentMySqlDateTime());
             consultantAction.setObjectIdinInt(Integer.parseInt(consultantAction.getObjectId().trim()));
//             int Id = DataSourceDataProvider.getInstance().getMaxRecAttachMentId();
//             Id = Id + 1;
             //System.out.print("Id-->"+Id);
            
             preStmt = connection.prepareStatement("INSERT INTO tblRecAttachments(ObjectId,ObjectType,AttachmentName,AttachmentFileName,AttachmentLocation,DateUploaded) values(?,?,?,?,?,?)");
           //  preStmt.setInt(1,Id);
             preStmt.setInt(1,consultantAction.getObjectIdinInt());
             preStmt.setString(2,consultantAction.getRequestType());
             preStmt.setString(3,consultantAction.getAttachmentName());
             preStmt.setString(4,consultantAction.getUploadFileName());
             preStmt.setString(5,consultantAction.getFilepath());
             preStmt.setTimestamp(6,consultantAction.getDate());
             
             isSuccess = preStmt.executeUpdate();
             
        }catch (Exception ex){
            throw new ServiceLocatorException(ex);
        }finally{
            try{
                if(preStmt!=null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        
        return isSuccess;
    }
    
    public ConsultantVTO getConsultantDetails(String Id) throws ServiceLocatorException {
        
        ConsultantVTO consultantVTO = new ConsultantVTO();
        Connection connection= null;
        
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Email2,FName,LName,MName,Gender,TitleTypeId,WorkPhoneNo,HomePhoneNo,CellPhoneNo,WorkAuthorization,SkillSet," +
                                                "RatePerHour,Industry,PracticeId,Country,RefferedBy,Comments,Source,AvailableFrom,Description,OrgId,Experience,TechnicallyEvaluated,Available,PreferredState,IsReject FROM tblRecConsultant WHERE Id="+Id);
            if(resultSet.next()) {
                consultantVTO.setEmail2(resultSet.getString("Email2"));
                consultantVTO.setFiName(resultSet.getString("FName"));
                consultantVTO.setMiName(resultSet.getString("MName"));
                consultantVTO.setLaName(resultSet.getString("LName"));
                consultantVTO.setGender(resultSet.getString("Gender"));
                consultantVTO.setTitleTypeId(resultSet.getString("TitleTypeId"));
                consultantVTO.setWorkPhoneNo(resultSet.getString("WorkPhoneNo"));
                consultantVTO.setHomePhoneNo(resultSet.getString("HomePhoneNo"));
                consultantVTO.setCellPhoneNo(resultSet.getString("CellPhoneNo"));
                consultantVTO.setWorkAuthorization(resultSet.getString("WorkAuthorization"));
                consultantVTO.setSkills(resultSet.getString("SkillSet"));
                consultantVTO.setRatePerHour(resultSet.getString("RatePerHour"));
                consultantVTO.setIndustryId(resultSet.getString("Industry"));
                consultantVTO.setPracticeId(resultSet.getString("PracticeId"));
                consultantVTO.setCountry(resultSet.getString("Country"));
                consultantVTO.setRefferedBy(resultSet.getString("RefferedBy"));
                consultantVTO.setComments(resultSet.getString("Comments"));
               // System.err.println("Org map Key"+resultSet.getString("OrgId"));
                consultantVTO.setOrg(resultSet.getString("OrgId"));
                if(resultSet.getString("Source") == null || resultSet.getString("Source").equals("null")) {
                    consultantVTO.setSource("");
                }else {
                    consultantVTO.setSource(resultSet.getString("Source"));
                }
                consultantVTO.setCurrentAction("editConsultant");
                consultantVTO.setAvailableFrom(resultSet.getDate("AvailableFrom"));
                consultantVTO.setDescription(resultSet.getString("Description"));
                consultantVTO.setExp(resultSet.getString("Experience"));
consultantVTO.setTechEval(resultSet.getString("TechnicallyEvaluated"));
                consultantVTO.setAvailable(resultSet.getString("Available"));
                consultantVTO.setPreferredState(resultSet.getString("PreferredState"));
                consultantVTO.setIsReject(resultSet.getInt("IsReject"));
                consultantVTO.setObjectId(Id);
                //ConsultantAction consultantAction = new ConsultantAction();
                //consultantAction.setObjectId(Id);
            }
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
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
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return consultantVTO;
    }
    
   public int doAddConsultantActivity(ConsultantAction consultantAction,String loginId) throws ServiceLocatorException {
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null;
                
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            connection=ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("INSERT INTO tblRecActivity(ConsultantId,ActivityType,AssignedTo,Status," +
                    "InterviewDate,ReportingDate,Comments,Availability,CreatedBy,CreatedDate,DueDate) values(?,?,?,?,?,?,?,?,?,?,?)");
            //preStmt.setInt(1,);
            preStmt.setInt(1,consultantAction.getConsultantId());
            preStmt.setString(2,consultantAction.getActivityType());
            preStmt.setString(3,consultantAction.getAssignedTo());
            preStmt.setString(4,consultantAction.getStatus());
            preStmt.setDate(5,consultantAction.getInterviewDate());
            preStmt.setDate(6,consultantAction.getReportingDate());
            preStmt.setString(7,consultantAction.getComments1());
            preStmt.setString(8,consultantAction.getAvailability());
            preStmt.setString(9,consultantAction.getCreatedBy());
            //consultantAction.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
            consultantAction.setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            preStmt.setTimestamp(10,consultantAction.getCreatedDate());
            preStmt.setTimestamp(11,consultantAction.getDueDateTimeStamp());
            
            isSuccess = preStmt.executeUpdate();
            
            int ActivityId = DataSourceDataProvider.getInstance().getMaxRecActivityId();
            
           /* if(isSuccess == 1) {
                //consultantAction.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
                consultantAction.setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
               // String assignedto_name = DataSourceDataProvider.getInstance().getFname_Lname(consultantAction.getAssignedTo());

                preStmt = connection.prepareStatement("INSERT INTO tblEmpIssues(Category,SubCategory,IssueType,CreatedBy,DateCreated,AssignedTo,DateAssigned,Status," +
                        "Comments,Description,TaskId,Severity,IssueTitle,Iflag,ActivityId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                preStmt.setString(1,"Recruiting");
                preStmt.setString(2,"Consultant");
                 preStmt.setString(3,consultantAction.getActivityType());
                preStmt.setString(4,loginId);
                preStmt.setTimestamp(5,consultantAction.getCreatedDate());
                 preStmt.setString(6,consultantAction.getAssignedTo());
                 preStmt.setTimestamp(7,consultantAction.getCreatedDate());
                 preStmt.setString(8,consultantAction.getStatus());
                 preStmt.setString(9,consultantAction.getComments1());
                 preStmt.setString(10,"Requirement Activity Description");
                 preStmt.setInt(11,consultantAction.getConsultantId());
                 preStmt.setString(12,"Medium");
                 preStmt.setString(13,"Requirement Activity");
                 preStmt.setInt(14,2);
                 preStmt.setInt(15,ActivityId);
//                 preStmt.setInt(13,taskId);
//                 preStmt.setTimestamp(6,consultantAction.getAssignedTo());
//                 preStmt.setTimestamp(6,consultantAction.getAssignedTo());
//                preStmt.setString(4,assignedto_name);
//                preStmt.setString(5,consultantAction.getStatus());
//                preStmt.setString(6,consultantAction.getComments1());
//                preStmt.setString(7,"Activity");
//                preStmt.setString(8,consultantAction.getActivityType());
//                preStmt.setTimestamp(9,consultantAction.getCreatedDate());
//                preStmt.setString(10,consultantAction.getDescription());
//                preStmt.setInt(11,taskId);
                preStmt.executeUpdate();
            }*/
        }catch(SQLException se) {
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preStmt!=null){
                    preStmt.close();
                    preStmt=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                se.printStackTrace();
                //throw new ServiceLocatorException(se);
            }
        }
        return isSuccess;
    }

    public int doEditConsultantActivity(ConsultantAction consultantAction) throws ServiceLocatorException {
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            connection=ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("UPDATE tblRecActivity set ActivityType='"+consultantAction.getActivityType()+"',AssignedTo='"+consultantAction.getAssignedTo()+"'," +
                                                  "Status='"+consultantAction.getStatus()+"',Comments='"+consultantAction.getComments1()+"',Availability='"+consultantAction.getAvailability()+"',ModifiedBy='"+consultantAction.getModifiedBy()+"'," +
                                                  "ModifiedDate='"+consultantAction.getModifiedDate()+"',DueDate='"+consultantAction.getDueDateTimeStamp()+"' WHERE Id="+consultantAction.getId()+" AND ConsultantId="+consultantAction.getConsultantId());
            isSuccess = preStmt.executeUpdate();
            
         /*   if(isSuccess==1){
                
                 preStmt = connection.prepareStatement("UPDATE tblEmpIssues set AssignedTo = '" +consultantAction.getAssignedTo()
                 +"', Status = '"+consultantAction.getStatus()+"' ,Comments = '"+consultantAction.getComments1()
                 +"', IssueType='"+consultantAction.getActivityType()+"'  WHERE TaskId="+consultantAction.getId());
                 
                isSuccess = preStmt.executeUpdate();
           
            }*/
            
        }catch(SQLException se) {
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preStmt!=null){
                    preStmt.close();
                    preStmt=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                se.printStackTrace();
                //throw new ServiceLocatorException(se);
            }
        }
        return isSuccess;
    }
    
    public ConsultantVTO getConsultantActivity(int consultantId,int id)  throws ServiceLocatorException {
        
        ConsultantVTO consultantVTO = new ConsultantVTO();
        Connection connection= null;
        
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Id,ConsultantId,ActivityType,AssignedTo,Status,InterviewDate,ReportingDate,Comments,Availability,DueDate from tblRecActivity where Id='"+id+"' AND ConsultantId='"+consultantId+"'");
            
            consultantVTO.setCurrentAction("addActivity");
            
            if(resultSet.next()) {
                consultantVTO.setId(resultSet.getInt("Id"));
                consultantVTO.setObjectId(String.valueOf(resultSet.getInt("ConsultantId")));
                consultantVTO.setActivityType(resultSet.getString("ActivityType"));
                consultantVTO.setAssignedTo(resultSet.getString("AssignedTo"));
                consultantVTO.setStatus(resultSet.getString("Status"));
                //System.out.println("Status-->"+resultSet.getString("Status"));
                consultantVTO.setInterviewDate(resultSet.getDate("InterviewDate"));
                consultantVTO.setReportingDate(resultSet.getDate("ReportingDate"));
                consultantVTO.setComments1(resultSet.getString("Comments"));
                consultantVTO.setAvailability(resultSet.getString("Availability"));
                if(resultSet.getString("DueDate") == null){
                     consultantVTO.setDueDate(null);
                } else{
                consultantVTO.setDueDate(DateUtility.getInstance().sqlTimeStampTousTimeStamp(resultSet.getString("DueDate")));    
                }             
                
                consultantVTO.setCurrentAction("editActivity");
                consultantVTO.setObjectId(String.valueOf(id));
                consultantVTO.setConsultantId(consultantId);
            }
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
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
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return consultantVTO;
    }
    //Added By Ajay Tummapala By Tech Reviews
   public String getReviewConsultantDetails(ConsultantAction consultantAction,int consultantId) throws ServiceLocatorException{
        /** connection is a reference variable for Connection . */
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
       queryString = "SELECT CONCAT(FName,' ' ,MName,' ',LName) AS NAME,Email2 AS Email,CellPhoneNo AS Mobile,"
                + " TitleTypeId AS Title,SkypeId FROM tblRecConsultant WHERE Id ="+consultantId;
     
        ConsultantVTO consultantVTO = new ConsultantVTO();
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
       // ResultSet resultSetTemp=null;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
           // consultantVTO.setId(consultantId);
           // consultantVTO.setActionName("saveEditDetails");
            while(resultSet.next()){
               consultantAction.setFiName(resultSet.getString("Name"));
               consultantAction.setEmail2(resultSet.getString("Email"));
               consultantAction.setCellPhoneNo(resultSet.getString("Mobile"));
               consultantAction.setTitleTypeId(resultSet.getString("Title"));
            consultantAction.setSkypeId(resultSet.getString("SkypeId"));

            }
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                
                if( resultSet!=null){
                    resultSet.close();
                    resultSet=null;
                }
                if( statement!=null){
                    statement.close();
                    statement=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
              
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return "success";
    }  

    
//public int doReferToReview(ConsultantAction consultantAction,String loginId,StringTokenizer st) throws ServiceLocatorException
//{
//    int maxId = 0;
//   int isSuccess = 0;
//   String toEmailId="";
//        Connection connection= null;
//        
//        /** callableStatement is a reference variable for CallableStatement . */
//        CallableStatement callableStatement = null;
//        
//        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
//        PreparedStatement preStmt=null,preStmtTemp=null;
//                
//        /** The statement is useful  to execute the above queryString */
//        ResultSet resultSet=null;
//        try{
//             
//              while(st.hasMoreTokens())
//             {    
//            connection=ConnectionProvider.getInstance().getConnection();
//            preStmt = connection.prepareStatement("INSERT INTO tblRecConsultantActivity(ConsultantId,ForwardedBy,ForwardedTo,ForwardedDate," +
//                    "Comments,LastModifiedDate,Rateing,LinkStatus,ForwardedLink,ForwardToName,Status) values(?,?,?,?,?,?,?,?,?,?,?)");
//            //preStmt.setInt(1,);
//            preStmt.setInt(1,consultantAction.getConsultantId());
//            preStmt.setString(2,loginId);
//              
//                  //System.out.println("to234--->"+st.nextToken());
//             preStmt.setString(3, st.nextToken()+"@miraclesoft.com");
//            
//             
//            //preStmt.setString(3,finalEmailIds);
//            preStmt.setTimestamp(4,DateUtility.getInstance().getCurrentMySqlDateTime());
//            preStmt.setString(5,"Yet to be Reviewed");
//            preStmt.setTimestamp(6,DateUtility.getInstance().getCurrentMySqlDateTime());
//            preStmt.setString(7,"Not Yet Reviewed");
//            preStmt.setString(8,"Active");
//             String link="http://w3.miraclesoft.com/Hubble/recruitment/consultant/reviewTechnical.action?name=jheDudcD&lee=djhgd343D&id="+consultantAction.getId()+"&consultantId="+consultantAction.getConsultantId()+"";
//            preStmt.setString(9,link);
//            preStmt.setString(10,consultantAction.getTechName());
//            preStmt.setString(11,"A");
//            consultantAction.setConsultantId(consultantAction.getConsultantId());
//        
//            isSuccess = preStmt.executeUpdate();
//             }
//            if(isSuccess>0){
//                preStmtTemp= connection.prepareStatement("SELECT MAX(Id) AS count from tblRecConsultantActivity");
//                resultSet = preStmtTemp.executeQuery();
//                while(resultSet.next())
//                {
//                    maxId = resultSet.getInt("count");
//                }
//                
//            }
//            
//            
//          
//       
//        }catch(SQLException se) {
//            se.printStackTrace();
//            //throw new ServiceLocatorException(se);
//        }finally{
//            try{
//                 if(resultSet!=null){
//                    resultSet.close();
//                    resultSet=null;
//                }
//                if(preStmtTemp!=null){
//                    preStmtTemp.close();
//                    preStmtTemp=null;
//                }
//                 if(preStmt!=null){
//                    preStmt.close();
//                    preStmt=null;
//                }
//                if(connection != null){
//                    connection.close();
//                    connection = null;
//                }
//            }catch (SQLException se){
//                se.printStackTrace();
//                //throw new ServiceLocatorException(se);
//            }
//        }
//        return maxId; 
//}
public int doReferToReview(ConsultantAction consultantAction, String loginId) throws ServiceLocatorException {
        int maxId = 0;
        int isSuccess = 0;
        String toEmailId = "";
        Connection connection = null;

        /**
         * callableStatement is a reference variable for CallableStatement .
         */
        CallableStatement callableStatement = null;

        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null, preStmtTemp = null;

        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        try {

//              while(st.hasMoreTokens())
//             {    
            connection = ConnectionProvider.getInstance().getConnection();
//            preStmt = connection.prepareStatement("INSERT INTO tblRecConsultantActivity(ConsultantId,ForwardedBy,ForwardedTo,ForwardedDate," +
//                    "Comments,LastModifiedDate,Rateing,LinkStatus,ForwardedLink,ForwardToName,Status,RequirementId,RecConsultantId,ScheduledDate,ScheduledTime,TimeFormat,Zone,TechieTitle) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//            preStmt = connection.prepareStatement("INSERT INTO tblRecConsultantActivity(ConsultantId,ForwardedBy,Presales1,ForwardedDate,"
//                    + "Comments,LastModifiedDate,Rateing,LinkStatus,ForwardedLink,Presales2,Status,RequirementId,RecConsultantId,ScheduledDate,ScheduledTime,TimeFormat,Zone,TechieTitle,Skill1,Skill2,Skill3,Skill4,Skill5,Skill6,Skill7,Skill8,Skill9,Skill10,InterveiwType) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
  preStmt = connection.prepareStatement("INSERT INTO tblRecConsultantActivity(ConsultantId,ForwardedBy,Presales1,ForwardedDate,"
                    + "Comments,Rateing,LinkStatus,ForwardedLink,Presales2,Status,RequirementId,RecConsultantId,ScheduledDate,ScheduledTime,TimeFormat,Zone,TechieTitle,Skill1,Skill2,Skill3,Skill4,Skill5,Skill6,Skill7,Skill8,Skill9,Skill10,InterveiwType,SkypeId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            String[] skills = DataSourceDataProvider.getInstance().getSkillSet(consultantAction.getRequirementId());
            //preStmt.setInt(1,);
            preStmt.setInt(1, consultantAction.getConsultantId());
            preStmt.setString(2, loginId);

            //System.out.println("to234--->"+st.nextToken());
            preStmt.setInt(3, consultantAction.getPreSales1());


            //preStmt.setString(3,finalEmailIds);
            preStmt.setTimestamp(4, DateUtility.getInstance().getCurrentMySqlDateTime());
            // preStmt.setString(5,"Yet to be Reviewed");
            preStmt.setString(5, consultantAction.getComments());
           // preStmt.setTimestamp(6, DateUtility.getInstance().getCurrentMySqlDateTime());
            preStmt.setString(6, "Not Yet Reviewed");
            preStmt.setString(7, "Active");
           // String link = "http://www.miraclesoft.com/Hubble/recruitment/consultant/reviewTechnical.action?name=jheDudcD&lee=djhgd343D&id=" + consultantAction.getId() + "&consultantId=" + consultantAction.getConsultantId() + "";
            String link = "";
            preStmt.setString(8, link);
            // preStmt.setString(10,consultantAction.getTechName());
            preStmt.setInt(9, consultantAction.getPreSales2());
            preStmt.setString(10, consultantAction.getStatus());
            preStmt.setInt(11, consultantAction.getRequirementId());
            preStmt.setInt(12, consultantAction.getRecConsultantId());
            consultantAction.setConsultantId(consultantAction.getConsultantId());
            preStmt.setDate(13, DateUtility.getInstance().getMysqlDate(consultantAction.getScheduledDate()));
            preStmt.setTime(14, DateUtility.getInstance().strToSqlTimeObj(consultantAction.getScheduledTime() + ":00"));
            preStmt.setString(15, consultantAction.getTimeFormat());
            preStmt.setString(16, consultantAction.getZone());

            preStmt.setString(17, consultantAction.getTitle());
            for (int i = 0; i < skills.length; i++) {
                preStmt.setString((i + 18), skills[i]);
            }
            preStmt.setString(28, consultantAction.getInterveiwType());
             if(consultantAction.getSkypeId() != null && !"".equals(consultantAction.getSkypeId())){
            preStmt.setString(29,consultantAction.getSkypeId());
            }else{
                preStmt.setString(29,"");
            }

            isSuccess = preStmt.executeUpdate();

            doUpdateRecStatus(consultantAction);
            // }
            if (isSuccess > 0) {
                preStmtTemp = connection.prepareStatement("SELECT MAX(Id) AS count from tblRecConsultantActivity");
                resultSet = preStmtTemp.executeQuery();
                while (resultSet.next()) {
                    maxId = resultSet.getInt("count");
                }

            }




        } catch (SQLException se) {
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmtTemp != null) {
                    preStmtTemp.close();
                    preStmtTemp = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                se.printStackTrace();
                //throw new ServiceLocatorException(se);
            }
        }
        //System.out.println("maxId" + maxId);
        return maxId;
    }

   
//public String doReviewTechnical(ConsultantAction consultantAction) throws ServiceLocatorException{
//
//    Connection connection= null;
//        
//        /** callableStatement is a reference variable for CallableStatement . */
//      //  CallableStatement callableStatement;
//        
//        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
//     //   PreparedStatement preStmt=null,preStmtTemp=null;
//       
//        /** The queryString is useful to get  queryString result to the particular jsp page */
//        String queryString="";
//        queryString = "SELECT CONCAT(FName,' ' ,MName,' ',LName) AS NAME,Email2 AS Email,CellPhoneNo AS Mobile,"
//                + " TitleTypeId AS Title FROM tblRecConsultant WHERE Id ="+consultantAction.getConsultantId();
//      //  ConsultantVTO consultantVTO = new ConsultantVTO();
//        Statement statement=null;
//        
//        /** The statement is useful  to execute the above queryString */
//        ResultSet resultSet=null;
//       // ResultSet resultSetTemp=null;
//        try{
//            connection = ConnectionProvider.getInstance().getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(queryString);
//           // consultantVTO.setId(consultantId);
//           // consultantVTO.setActionName("saveEditDetails");
//            while(resultSet.next()){
//               consultantAction.setFiName(resultSet.getString("Name"));
//               consultantAction.setEmail2(resultSet.getString("Email"));
//               consultantAction.setCellPhoneNo(resultSet.getString("Mobile"));
//               consultantAction.setTitleTypeId(resultSet.getString("Title"));
//
//            }
//        }catch (SQLException se){
//            throw new ServiceLocatorException(se);
//        }finally{
//            try{
//                 if( resultSet!=null){
//                    resultSet.close();
//                    resultSet=null;
//                }
//                 if( statement!=null){
//                    statement.close();
//                    statement=null;
//                }
//                if(connection != null){
//                    connection.close();
//                    connection = null;
//                }
//                
//              
//                
//              
//            }catch (SQLException se){
//                throw new ServiceLocatorException(se);
//            }
//        }
//        return "success"; 
//    
//}
public String doReviewTechnical(ConsultantAction consultantAction) throws ServiceLocatorException {

        Connection connection = null;

        /**
         * callableStatement is a reference variable for CallableStatement .
         */
        //  CallableStatement callableStatement;
        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        //   PreparedStatement preStmt=null,preStmtTemp=null;
        /**
         * The queryString is useful to get queryString result to the particular
         * jsp page
         */
        String queryString = "";
//        queryString = "SELECT CONCAT(FName,' ' ,MName,' ',LName) AS NAME,Email2 AS Email,CellPhoneNo AS Mobile,"
//                + " TitleTypeId AS Title FROM tblRecConsultant WHERE Id =" + consultantAction.getConsultantId();
//        queryString = "SELECT CONCAT(FName,' ' ,MName,' ',LName) AS NAME,Email2 AS Email,CellPhoneNo AS Mobile,SkillSet AS skill,TitleTypeId AS Title,"
//                + "ScheduledDate AS scdate,ScheduledTime AS sctime,TimeFormat AS timefomat,"
//                + "Zone AS zone, Rateing AS rating,tblRecConsultantActivity.Comments AS comments,TechnicalSkills AS techskills,"
//                + "DomainSkills AS domainskills,CommunicationSkills AS commskills,TechieTitle AS techieJobTitle"
//                + " FROM tblRecConsultant  LEFT OUTER JOIN tblRecConsultantActivity ON (tblRecConsultant.Id=tblRecConsultantActivity.ConsultantId)"
//                + "WHERE tblRecConsultantActivity.Id =" + consultantAction.getId();

consultantAction.setStatus("denied");
        queryString = "SELECT CONCAT(FName,' ' ,MName,' ',LName) AS NAME,Email2 AS Email,MobileNo AS Mobile,SkillSet AS skill,tblRecRequirement.JobTitle AS Title,"
                + "ScheduledDate AS scdate,ScheduledTime AS sctime,TimeFormat AS timefomat,"
                + "Zone AS zone, Rateing AS rating,tblRecConsultantActivity.TechReviewComments AS comments,TechnicalSkills AS techskills,"
                + "DomainSkills AS domainskills,CommunicationSkills AS commskills,TechieTitle AS techieJobTitle,tblRec.ResumeAttachmentId AS ResumeAttachmentId,tblRecConsultantActivity.RecConsultantId AS RecConsultantId,tblRec.AvailableFrom,tblRec.WorkAuthorization,tblRec.CurrentEmployer,Skill1,Skill2,Skill3,Skill4,Skill5,Skill6,Skill7,Skill8,Skill9,Skill10,Rating1,Rating2,Rating3,Rating4,Rating5,Rating6,Rating7,Rating8,Rating9,Rating10,tblRecConsultantActivity.InterveiwType,tblRecConsultantActivity.Status "
                + " FROM tblRecConsultantActivity  INNER JOIN tblRecConsultant ON (tblRecConsultant.Id=tblRecConsultantActivity.ConsultantId)"
                + " INNER JOIN tblRec ON (tblRecConsultantActivity.RecConsultantId = tblRec.Id) JOIN tblRecRequirement ON (tblRecConsultantActivity.RequirementId=tblRecRequirement.Id)"
                + "WHERE tblRecConsultantActivity.Id =" + consultantAction.getId()+" AND (tblRecConsultantActivity.Presales1="+consultantAction.getEmpId()+" OR tblRecConsultantActivity.Presales2="+consultantAction.getEmpId()+")";

        //System.out.println("Query-->" + queryString);
        //  ConsultantVTO consultantVTO = new ConsultantVTO();
        Statement statement = null;

        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        // ResultSet resultSetTemp=null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            // consultantVTO.setId(consultantId);
            // consultantVTO.setActionName("saveEditDetails");
            HashMap<Integer, List> skillSetMap = new LinkedHashMap<Integer, List>();
            if (resultSet.next()) {
                consultantAction.setFiName(resultSet.getString("Name"));
                consultantAction.setEmail2(resultSet.getString("Email"));
                consultantAction.setCellPhoneNo(resultSet.getString("Mobile"));
                consultantAction.setTitleTypeId(resultSet.getString("Title"));
                //----------------------

                consultantAction.setSkills(resultSet.getString("skill"));
                String srcDate = DateUtility.getInstance().dispDate(resultSet.getString("scdate"));
                consultantAction.setScheduledDate(srcDate);
                String srcTime = DateUtility.getInstance().dispTime(resultSet.getString("sctime"));
                consultantAction.setScheduledTime(srcTime);
                consultantAction.setTimeFormat(resultSet.getString("timefomat"));
                consultantAction.setZone(resultSet.getString("zone"));
                consultantAction.setRateing(resultSet.getString("rating"));
                consultantAction.setComments(resultSet.getString("comments"));
                consultantAction.setTechSkills(resultSet.getInt("techskills"));
                consultantAction.setDomainskills(resultSet.getInt("domainskills"));
                consultantAction.setCommskills(resultSet.getInt("commskills"));
                consultantAction.setTitle(resultSet.getString("techieJobTitle"));
                consultantAction.setAttachmentId(resultSet.getInt("ResumeAttachmentId"));
                consultantAction.setRecConsultantId(resultSet.getInt("RecConsultantId"));
                if (resultSet.getDate("AvailableFrom") != null) {
                    consultantAction.setAvailableFrom(resultSet.getDate("AvailableFrom"));
                }

                consultantAction.setWorkAuthorization(resultSet.getString("WorkAuthorization"));
                consultantAction.setCurrentEmployer(resultSet.getString("CurrentEmployer"));

                consultantAction.setSkill1(resultSet.getString("Skill1"));
                consultantAction.setSkill2(resultSet.getString("Skill2"));
                consultantAction.setSkill3(resultSet.getString("Skill3"));
                consultantAction.setSkill4(resultSet.getString("Skill4"));
                consultantAction.setSkill5(resultSet.getString("Skill5"));
                consultantAction.setSkill6(resultSet.getString("Skill6"));
                consultantAction.setSkill7(resultSet.getString("Skill7"));
                consultantAction.setSkill8(resultSet.getString("Skill8"));
                consultantAction.setSkill9(resultSet.getString("Skill9"));
                consultantAction.setSkill10(resultSet.getString("Skill10"));
                consultantAction.setRating1(resultSet.getInt("Rating1"));
                consultantAction.setRating2(resultSet.getInt("Rating2"));
                consultantAction.setRating3(resultSet.getInt("Rating3"));
                consultantAction.setRating4(resultSet.getInt("Rating4"));
                consultantAction.setRating5(resultSet.getInt("Rating5"));
                consultantAction.setRating6(resultSet.getInt("Rating6"));
                consultantAction.setRating7(resultSet.getInt("Rating7"));
                consultantAction.setRating8(resultSet.getInt("Rating8"));
                consultantAction.setRating9(resultSet.getInt("Rating9"));
                consultantAction.setRating10(resultSet.getInt("Rating10"));
                consultantAction.setInterveiwType(resultSet.getString("InterveiwType"));
                consultantAction.setStatus(resultSet.getString("Status"));
                for (int i = 1; i <= 10; i++) {
                    String skill = "Skill" + i;
                    String rating = "Rating" + i;
                    if (!"".equals(resultSet.getString(skill))) {
                        List skil = new ArrayList();

                        skil.add(resultSet.getString(skill));
                        skil.add(rating);
                        skil.add(resultSet.getInt(rating));
                        skillSetMap.put(i, skil);
                    }
                }
              
            }
            consultantAction.setSkillSetMap(skillSetMap);
            consultantAction.setId(consultantAction.getId());
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }




            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return "success";

    }
//public int doAddTechincalReview(ConsultantAction consultantAction,StringTokenizer st) throws ServiceLocatorException
//{
//     int maxIdOrCount[] = null ;
//     int maxId = 0;
//   int isSuccess = 0;
//        Connection connection= null;
//         Connection connection1= null;
//          Connection connection2= null;
//           Connection connection3= null;
//        String queryString1 = null;
//         String queryString2 = null;
//        /** callableStatement is a reference variable for CallableStatement . */
//        CallableStatement callableStatement = null;
//        
//        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
//        PreparedStatement preStmt=null,preStmtTemp=null,preStmtTemp1=null;
//         PreparedStatement preStmt1=null,preStmt1Temp=null,preStmt1Temp1=null;
//          PreparedStatement preStmt2=null,preStmt2Temp=null,preStmt2Temp1=null;
//             PreparedStatement preStmt3=null,preStmt3Temp=null,preStmt3Temp1=null;
//        String queryString = null;
//                
//        /** The statement is useful  to execute the above queryString */
//        ResultSet resultSet=null;
//        String forwardedBy = null;
//        try{
//            if(consultantAction.getOperationType().equalsIgnoreCase("Review"))
//            {
//              //  System.out.println("In review");
//                queryString="UPDATE tblRecConsultantActivity SET LastModifiedDate=?, Comments=?,Rateing=?, LinkStatus=?,Status=? WHERE Id="+consultantAction.getId();
//                
//            }
//            else if(consultantAction.getOperationType().equalsIgnoreCase("Review & Forward")){
//                // System.out.println("In review and forward");
//                queryString="UPDATE tblRecConsultantActivity SET LastModifiedDate=?, Comments=?,Rateing=?, LinkStatus=?,Status=? WHERE Id="+consultantAction.getId();
//                queryString1="INSERT INTO tblRecConsultantActivity(ConsultantId,ForwardedBy,ForwardedTo,ForwardedDate," +
//                    "Comments,LastModifiedDate,Rateing,LinkStatus,ForwardedLink,ForwardToName,Status) values(?,?,?,?,?,?,?,?,?,?,?)";
//            forwardedBy=DataSourceDataProvider.getInstance().getForwarderByName(consultantAction.getId());
//            }
//            else{
//                 //System.out.println("In forward");
//                 queryString2="UPDATE tblRecConsultantActivity SET LastModifiedDate=?,Comments=?, LinkStatus=?,Status=? WHERE Id="+consultantAction.getId();
//                queryString="INSERT INTO tblRecConsultantActivity(ConsultantId,ForwardedBy,ForwardedTo,ForwardedDate," +
//                    "Comments,LastModifiedDate,Rateing,LinkStatus,ForwardedLink,ForwardToName,Status) values(?,?,?,?,?,?,?,?,?,?,?)";
//            forwardedBy=DataSourceDataProvider.getInstance().getForwarderByName(consultantAction.getId());
//            }
//           
//            connection=ConnectionProvider.getInstance().getConnection();
//          //  preStmt = connection.prepareStatement(queryString);
//            //preStmt.setInt(1,);
//            if(consultantAction.getOperationType().equalsIgnoreCase("Review")){
//                 preStmt = connection.prepareStatement(queryString);
//                preStmt.setTimestamp(1,DateUtility.getInstance().getCurrentMySqlDateTime());
//                preStmt.setString(2,consultantAction.getComments());
//                preStmt.setString(3,consultantAction.getRateing());
//                preStmt.setString(4,"InActive");
//                 preStmt.setString(5,"R");
//                 maxId= preStmt.executeUpdate();;
//            }else if(consultantAction.getOperationType().equalsIgnoreCase("Forward")){
//                preStmt = connection.prepareStatement(queryString2);
//                preStmt.setTimestamp(1,DateUtility.getInstance().getCurrentMySqlDateTime());
//                  preStmt.setString(2,consultantAction.getComments());
//                preStmt.setString(3,"InActive");
//                 preStmt.setString(4,"F");
//                 maxId= preStmt.executeUpdate();;
//               connection1=ConnectionProvider.getInstance().getConnection();  
//                while(st.hasMoreTokens())
//                {
//                    
//            preStmt1 = connection1.prepareStatement(queryString);
//            preStmt1.setInt(1,consultantAction.getConsultantId());
//            preStmt1.setString(2,forwardedBy);
//          //  preStmt1.setString(3,consultantAction.getReferTo());
//             preStmt1.setString(3, st.nextToken()+"@miraclesoft.com");
//            preStmt1.setTimestamp(4,DateUtility.getInstance().getCurrentMySqlDateTime());
//            preStmt1.setString(5,consultantAction.getComments());
//            preStmt1.setTimestamp(6,DateUtility.getInstance().getCurrentMySqlDateTime());
//            preStmt1.setString(7,"Not Yet Reviewed");
//            String link="http://w3.miraclesoft.com/Hubble/recruitment/consultant/reviewTechnical.action?name=jheDudcD&lee=djhgd343D&id="+consultantAction.getId()+"&consultantId="+consultantAction.getConsultantId()+"";
//            preStmt1.setString(8,"Active");
//            preStmt1.setString(9,link);
//            preStmt1.setString(10,consultantAction.getTechName());
//            preStmt1.setString(11,"F");
//            maxId= preStmt1.executeUpdate();;
//            }}else{
//                StringTokenizer st1 =  DataSourceDataProvider.getInstance().getReferredLoginIdsForGroups(consultantAction.getReferTo());
//                 connection2=ConnectionProvider.getInstance().getConnection();
//                preStmt2 = connection2.prepareStatement(queryString);
//                preStmt2.setTimestamp(1,DateUtility.getInstance().getCurrentMySqlDateTime());
//                preStmt2.setString(2,consultantAction.getComments());
//                preStmt2.setString(3,consultantAction.getRateing());
//                preStmt2.setString(4,"InActive");
//                preStmt2.setString(5,"RF");
//                //preStmt.addBatch();
//                preStmt2.executeUpdate();
//                 connection3=ConnectionProvider.getInstance().getConnection();
//                preStmt3 = connection3.prepareStatement(queryString1);
//                 while(st1.hasMoreTokens())
//                {
//                preStmt3.setInt(1,consultantAction.getConsultantId());
//            preStmt3.setString(2,forwardedBy);
//           // preStmt3.setString(3,consultantAction.getReferTo());
//             preStmt3.setString(3, st1.nextToken()+"@miraclesoft.com");
//            preStmt3.setTimestamp(4,DateUtility.getInstance().getCurrentMySqlDateTime());
//            preStmt3.setString(5,"Not Yet Reviewed");
//            preStmt3.setTimestamp(6,DateUtility.getInstance().getCurrentMySqlDateTime());
//            preStmt3.setString(7,"Not Yet Reviewed");
//            String link="http://w3.miraclesoft.com/Hubble/recruitment/consultant/reviewTechnical.action?name=jheDudcD&lee=djhgd343D&id="+consultantAction.getId()+"&consultantId="+consultantAction.getConsultantId()+"";
//            preStmt3.setString(8,"Active");
//            preStmt3.setString(9,link);
//            preStmt3.setString(10,consultantAction.getTechName());
//            preStmt3.setString(11,"RF");
//            //preStmt.addBatch();
//            maxId= preStmt3.executeUpdate();;
//            }}
//            consultantAction.setConsultantId(consultantAction.getConsultantId());
//        if(consultantAction.getOperationType().equalsIgnoreCase("Review & Forward")){
//            maxIdOrCount = preStmt3.executeBatch();
//        }else{
//            maxId = preStmt.executeUpdate();
//        }
//            
//           if(!(consultantAction.getOperationType().equalsIgnoreCase("Review")) && maxId>0 || (consultantAction.getOperationType().equalsIgnoreCase("Review & Forward")) && maxId>0){
//                preStmtTemp= connection.prepareStatement("SELECT MAX(Id) AS count from tblRecConsultantActivity");
//                resultSet = preStmtTemp.executeQuery();
//                while(resultSet.next())
//                {
//                    maxId = resultSet.getInt("count");
//                }   
//            }
//        }catch(SQLException se) {
//            se.printStackTrace();
//            //throw new ServiceLocatorException(se);
//        }catch(Exception se) {
//            se.printStackTrace();
//            //throw new ServiceLocatorException(se);
//        }
//        finally{
//            try{
//                 if(resultSet!=null){
//                    resultSet.close();
//                    resultSet=null; 
//                }
//                if(preStmtTemp!=null){
//                    preStmtTemp.close();
//                    preStmtTemp=null;
//                }
//                 if(preStmt!=null){
//                    preStmt.close();
//                    preStmt=null;
//                }
//                if(connection != null){
//                    connection.close();
//                    connection = null;
//                }
//                  if(preStmt1Temp!=null){
//                    preStmt1Temp.close();
//                    preStmt1Temp=null;
//                }
//                 if(preStmt1!=null){
//                    preStmt1.close();
//                    preStmt1=null;
//                }
//                if(connection1 != null){
//                    connection1.close();
//                    connection1 = null;
//                }
//                  if(preStmt2Temp!=null){
//                    preStmt2Temp.close();
//                    preStmt2Temp=null;
//                }
//                 if(preStmt2!=null){
//                    preStmt2.close();
//                    preStmt2=null;
//                }
//                if(connection2 != null){
//                    connection2.close();
//                    connection2= null;
//                }
//                  if(preStmt3Temp!=null){
//                    preStmt3Temp.close();
//                    preStmt3Temp=null;
//                }
//                 if(preStmt3!=null){
//                    preStmt3.close();
//                    preStmt3=null;
//                }
//                if(connection3 != null){
//                    connection3.close();
//                    connection3 = null;
//                } 
//                
//            }catch (SQLException se){
//                se.printStackTrace();
//                //throw new ServiceLocatorException(se);
//            }
//        }
//        return maxId; 
//    
//}

public int doAddTechincalReview(ConsultantAction consultantAction, StringTokenizer st) throws ServiceLocatorException {
        int maxId = 0;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();


            //       queryString = "UPDATE tblRecConsultantActivity SET LastModifiedDate=?, Comments=?,Rateing=?, LinkStatus=?,Status=?,TechnicalSkills=?,DomainSkills=?,CommunicationSkills=? WHERE Id=" + consultantAction.getId();
            queryString = "UPDATE tblRecConsultantActivity SET Rateing='Reviewed', LastModifiedDate=?, TechReviewComments=?, LinkStatus=?,"
                    + "Status=?,TechnicalSkills=?,DomainSkills=?,CommunicationSkills=?,Rating1=?,Rating2=?,Rating3=?,Rating4=?,Rating5=?,Rating6=?,Rating7=?,Rating8=?,Rating9=?,Rating10=?,IsReject=?,ForwardedBy=? WHERE Id=" + consultantAction.getId();

            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setTimestamp(1, DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(2, consultantAction.getComments());
            preparedStatement.setString(3, "InActive");
            preparedStatement.setString(4, consultantAction.getStatus());
            preparedStatement.setInt(5, consultantAction.getTechSkills());
            preparedStatement.setInt(6, consultantAction.getDomainskills());
            preparedStatement.setInt(7, consultantAction.getCommskills());
            preparedStatement.setInt(8, consultantAction.getRating1());
            preparedStatement.setInt(9, consultantAction.getRating2());
            preparedStatement.setInt(10, consultantAction.getRating3());
            preparedStatement.setInt(11, consultantAction.getRating4());
            preparedStatement.setInt(12, consultantAction.getRating5());
            preparedStatement.setInt(13, consultantAction.getRating6());
            preparedStatement.setInt(14, consultantAction.getRating7());
            preparedStatement.setInt(15, consultantAction.getRating8());
            preparedStatement.setInt(16, consultantAction.getRating9());
            preparedStatement.setInt(17, consultantAction.getRating10());

            if (consultantAction.getStatus().equals("Tech Screen Reject")) {
                consultantAction.setIsReject(1);
                doUpdateCosultant(consultantAction);
            } else {
                consultantAction.setIsReject(0);
            }
            preparedStatement.setInt(18, consultantAction.getIsReject());
             preparedStatement.setString(19, consultantAction.getModifiedBy());
            maxId = preparedStatement.executeUpdate();

            doUpdateRecStatus(consultantAction);
            String title = DataSourceDataProvider.getInstance().getRequirementTitle(String.valueOf(consultantAction.getRequirementId()));
            String subject = "";
            String bodyPart = "";
            if (consultantAction.getStatus().equals("Tech Screen shotlisted")) {
                subject = consultantAction.getFiName()+" selected for "+title+" in Tech Screen";
                bodyPart = "has been selected/shortlisted to submit to client";
            }
            if (consultantAction.getStatus().equals("Tech Screen Reject")) {
                subject = consultantAction.getFiName()+" rejected for "+title+" in Tech Screen";
                bodyPart = "has been rejected/put on hold";
            }

            //sendUpdatedReviewDetails(String requirementId,String consultantId,String status,String comments,String modifiedBy,String modifiedDate,String techSkills,String domainSkills,String commSkills)   String eMail,String cellNo
            sendUpdatedReviewDetails(subject, bodyPart, consultantAction.getId(), String.valueOf(consultantAction.getRequirementId()), consultantAction.getFiName(), consultantAction.getStatus(), consultantAction.getComments(), consultantAction.getModifiedBy(), DateUtility.getInstance().getCurrentMySqlDateTime().toString(), consultantAction.getTechSkills(), consultantAction.getDomainskills(), consultantAction.getCommskills(), consultantAction.getEmail2(), consultantAction.getCellPhoneNo(), consultantAction.getRecConsultantId(), consultantAction.getInterveiwType());
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }



        return maxId;

    }

public int doUpdateRecStatus(ConsultantAction consultantAction) throws ServiceLocatorException {
        int maxId = 0;
        int isSuccess = 0;
        String toEmailId = "";
        Connection connection = null;

        /**
         * callableStatement is a reference variable for CallableStatement .
         */
        CallableStatement callableStatement = null;

        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null, preStmtTemp = null;

        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        try {


            connection = ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("UPDATE tblRec SET STATUS = ? ,Comments = ? ,ModifiedDate=?,ModifiedBy=? WHERE Id = ?");
            preStmt.setString(1, consultantAction.getStatus());
            preStmt.setString(2, consultantAction.getComments());
            preStmt.setTimestamp(3, DateUtility.getInstance().getCurrentMySqlDateTime());
            preStmt.setString(4, consultantAction.getModifiedBy());
            preStmt.setInt(5, consultantAction.getRecConsultantId());
            isSuccess = preStmt.executeUpdate();

          //  System.out.println("Updated");
        } catch (SQLException se) {
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        } finally {
            try {

                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                se.printStackTrace();
                //throw new ServiceLocatorException(se);
            }
        }
        return isSuccess;
    }

public int doUpdateCosultant(ConsultantAction consultantAction) throws ServiceLocatorException {
        int maxId = 0;
        int isSuccess = 0;
        String toEmailId = "";
        Connection connection = null;

        /**
         * callableStatement is a reference variable for CallableStatement .
         */
        CallableStatement callableStatement = null;

        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null, preStmtTemp = null;

        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        try {


            connection = ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("UPDATE tblRecConsultant SET IsReject = ?,ModifiedDate=?,ModifiedBy=? WHERE Id = ?");
            preStmt.setInt(1, consultantAction.getIsReject());
            preStmt.setTimestamp(2, DateUtility.getInstance().getCurrentMySqlDateTime());
            preStmt.setString(3, consultantAction.getModifiedBy());
            preStmt.setInt(4, consultantAction.getConsultantId());
            isSuccess = preStmt.executeUpdate();

            //System.out.println("Updated");
        } catch (SQLException se) {
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        } finally {
            try {

                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                se.printStackTrace();
                //throw new ServiceLocatorException(se);
            }
        }
        return isSuccess;
    }
    
public static void sendUpdatedReviewDetails(String subject, String bodyPart, int Id, String requirementId, String consultantName, String status, String comments, String modifiedBy, String modifiedDate, int techSkills, int domainSkills, int commSkills, String eMail, String cellNo, int consultId, String interviewType) throws ServiceLocatorException {
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
//public static void sendConsultantUpdatedDetailsForRequirement(String title,String requirementId,String consultantId,String rate,String startDate,String status,String comments,String modifiedBy,String modifiedDate,String eMail,String cellNo) throws ServiceLocatorException {
        try {
            String Managers = com.mss.mirage.util.Properties.getProperty("RecruitManager");
            String mgrs[] = Managers.split(Pattern.quote(","));
            HashSet CC2 = new HashSet();
            for (int i = 0; i < mgrs.length; i++) {
                CC2.add(mgrs[i]);
            }
            String recruiterPresales = DataSourceDataProvider.getInstance().getRecruitersPreSalesByReviewId(Id);
            String url = com.mss.mirage.util.Properties.getProperty("PROD.URL");

            String persons[] = recruiterPresales.split("#");
            String recruiter1 = persons[0];
            String recruiter2 = persons[1];
            String presales1 = persons[2];
            String presales2 = persons[3];

            HashSet<String> reportsToTopLevel = new HashSet<String>();
            if (!recruiter1.equals("") && recruiter1 != null) {
                String recruiter1loginId = DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(recruiter1);
                HashSet reportsToTopLevel1 = DataSourceDataProvider.getInstance().getTopLevelReportsLoginIds(recruiter1loginId);
                reportsToTopLevel.add(recruiter1loginId);
                CC2.addAll(reportsToTopLevel1);
            }
            if (!recruiter2.equals("") && recruiter2 != null) {
                String recruiter2loginId = DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(recruiter2);
                HashSet reportsToTopLevel2 = DataSourceDataProvider.getInstance().getTopLevelReportsLoginIds(recruiter2loginId);
                reportsToTopLevel.add(recruiter2loginId);
                CC2.addAll(reportsToTopLevel2);
            }

            String email = "";
            String email1 = "";
            if (!presales1.equals("0") && presales1 != null) {
                email = DataSourceDataProvider.getInstance().getEmailId(Integer.parseInt(presales1));
                if (email != null && !email.equals("")) {
                    reportsToTopLevel.add(email);
                }
            }
            if (!presales2.equals("0") && presales2 != null) {
                email1 = DataSourceDataProvider.getInstance().getEmailId(Integer.parseInt(presales2));
                if (email1 != null && !email1.equals("")) {
                    reportsToTopLevel.add(email1);
                }
            }
            String errormsg = "success";
            String to1 = "";
            String from = "hubbleapp@miraclesoft.com";
            String to = "";

// Iterator<String> itr=reportsToTopLevel.iterator();  
//  while(itr.hasNext()){  
//   //message.addRecipient(Message.RecipientType.TO,new InternetAddress(itr.next().toString()+"@miraclesoft.com"));
//      to+=itr.next().toString()+"@miraclesoft.com,";
//  }  
            // to=to.substring(0, to.length()-1);


           // System.out.println("to-->" + to);

            String title = DataSourceDataProvider.getInstance().getRequirementTitle(requirementId);

            String details = DataSourceDataProvider.getInstance().getDetailsByRequirementId(requirementId);

            String createdBy = details.substring(0, details.indexOf("#"));
           
            String practice = details.substring(details.indexOf("#") + 1, details.length());

            String createdByName = DataSourceDataProvider.getInstance().getFname_Lname(createdBy);

            to += createdBy + "@miraclesoft.com";
            //String cc = leaveCc;

            List practiseManagers = DefaultDataProvider.getInstance().getPracticeManager();

           

 CC2.add(createdBy);
            if (practice.equals("SAP")) {
                for (Iterator it = practiseManagers.iterator(); it.hasNext();) {
                    //  message.addRecipient(Message.RecipientType.TO,new InternetAddress(it.next().toString()+"@miraclesoft.com"));
                    // tomail+=it.next().toString()+"@miraclesoft.com";
                    CC2.add(it.next().toString());
                }
            }

            Iterator<String> itr = reportsToTopLevel.iterator();
            String tomail = "";
            //System.out.println("while loop");
            while (itr.hasNext()) {
                tomail += itr.next().toString() + "@miraclesoft.com,";


            }
            tomail = tomail.substring(0, tomail.length() - 1);
            itr = CC2.iterator();
            String cc = "";
            while (itr.hasNext()) {
                cc += itr.next().toString() + "@miraclesoft.com,";


            }

            StringBuilder htmlText = new StringBuilder();

/*
            htmlText.append("<html><head><title>Mail From Hubble Portal</title></head>");
            htmlText.append("<body><table align='center'><tr style='background:#07BBD7;height:40px;'><td><font color='white' size='4' face='Arial'>");
            htmlText.append("<p>" + subject + "</p></font></td></tr><tr><td><table style='background:#CCDBDE;width:100%;'><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<p>Hello Team,</p></font> </td></tr><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("</font>  </td></tr><tr><td><font color='#111728' size='2' face='Arial' style='font-weight:600;'><p>Based on the Tech Screen <font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>" + consultantName + "</font> submitted for  <font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>" + title + "</font> " + bodyPart + ".Please see the comments below.</p></font></td></tr>");

            htmlText.append("<tr><td><font color='#111728' size='2' face='Arial' style='font-weight:600;'><p><u>Interview Type:</u></p></font></td></tr>");
            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>" + interviewType + "</font></td></tr>");
            htmlText.append("<tr><td><font color='#111728' size='2' face='Arial' style='font-weight:600;'><p><u>Comments:</u></p></font></td></tr>");
            htmlText.append("<tr><td><font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + comments + "</font></td></tr>");


            HashMap skillSetMap = DataSourceDataProvider.getInstance().getSkillsAndRatings(Id);
            if (skillSetMap.size() > 0 && skillSetMap != null) {
                htmlText.append("<tr><td><font color='#111728' size='2' face='Arial' style='font-weight:600;'><p><u>Ratings:</u></p></font></td></tr>");
                htmlText.append("<tr><td>");
                htmlText.append("<table style='border-collapse: collapse;'>");
             
                htmlText.append("<tr style=' border: 1px  black;'><th style='border: 1px solid green;'>Skill</th><th style='border: 1px solid green;'>Rating</th></tr>");
                Iterator it = skillSetMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    // System.out.println( + " = " + );
                    htmlText.append("<tr style='border: 1px solid black;'><td style='border: 1px solid black;'><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>" + pair.getKey() + " :</font>");
                    htmlText.append("</td><td width='80' style='border: 1px solid black;'><font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + pair.getValue() + "</font></td></tr>");
                }
                   htmlText.append("</table></td></tr>");
            }
            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>For further support please contact:</font></td></tr>");
            if (!presales1.equals("0") && presales1 != null) {
                String name = DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(presales1));
                htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Name :</font>");
                htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + name + "</font></td></tr>");
                htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Email :</font>");
                htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + email + "@miraclesoft.com</font></td></tr>");
            }
            if (!presales2.equals("0") && presales2 != null) {
                email1 = DataSourceDataProvider.getInstance().getEmailId(Integer.parseInt(presales2));
                String name = DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(presales2));
                htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Name :</font>");
                htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + name + "</font></td></tr>");
                htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Email :</font>");
                htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + email1 + "@miraclesoft.com</font></td></tr>");
            }
            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'><br>For More Details about this Requirement/candidate <a href='" + url + "crm/requirement/getConsultantRequirement.action?consultId=" + consultId + "&objectId=" + requirementId + "&requirementAdminFlag=YES'>click here</a></font></td></tr>");
            htmlText.append("</table></font></td></tr><tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font> </td></tr></table></body></html>");
*/

htmlText.append("<!DOCTYPE html>");
htmlText.append("<html>");
   htmlText.append("<head>");
     htmlText.append("<meta charset='utf-8'>");
      htmlText.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
      htmlText.append("<meta http-equiv='X-UA-Compatible' content='IE=edge' />");
      htmlText.append("<style type='text/css'>");
         
         htmlText.append("body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;} ");
         htmlText.append("table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;} ");
         htmlText.append("img{-ms-interpolation-mode: bicubic;} ");
         htmlText.append("img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}");
         htmlText.append("table{border-collapse: collapse !important;}");
         htmlText.append("body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}");
         
         htmlText.append("a[x-apple-data-detectors] {color: inherit !important;text-decoration: none !important;font-size: inherit !important;font-family: inherit !important;font-weight: inherit !important;line-height: inherit !important;}");
         
         htmlText.append("@media screen and (max-width: 525px) {");
         
         htmlText.append(".wrapper {width: 100% !important;max-width: 100% !important;}");
         
         htmlText.append(".logo img {margin: 0 auto !important;}");
         
         htmlText.append(".mobile-hide {display: none !important;}");
         htmlText.append(".img-max {max-width: 100% !important;width: 100% !important;height: auto !important;}");
         
         htmlText.append(".responsive-table {width: 100% !important;}");
         
         htmlText.append(".padding {padding: 10px 5% 15px 5% !important;}");
         htmlText.append(".padding-meta {padding: 30px 5% 0px 5% !important;text-align: center;}");
         htmlText.append(".padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}");
         htmlText.append(".no-padding {padding: 0 !important;}");
         htmlText.append(".section-padding {padding: 50px 15px 50px 15px !important;}");
         
         htmlText.append(".mobile-button-container {margin: 0 auto;width: 100% !important;}");
         htmlText.append(".mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important;display: block !important;}}");
         
         htmlText.append("div[style*='margin: 16px 0;'] { margin: 0 !important; }");
      htmlText.append("</style>");
   htmlText.append("</head>");
   htmlText.append("<body style='margin: 0 !important; padding: 0 !important;'>");
      
      htmlText.append("<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>Hello <b>Team,</b><br>Based on the Tech Screen  <b>" + consultantName + "</b> submitted for <b>" + title + "</b> " + bodyPart + ".Please see the comments below.</div>");
     
      htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
         htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center'>");
               
                        htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper'>");
                           htmlText.append("<tr>");
                              htmlText.append("<td align='center' valign='top' style='padding: 15px 0;' class='logo'><a href='http://www.miraclesoft.com/' target='_blank'><img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'></a></td>");
                           htmlText.append("</tr>");
                        htmlText.append("</table>");
                        
            htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
               
                        htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
                           htmlText.append("<tr>");
                              htmlText.append("<td>");
                                 
                                 htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>Tech Screen Selection</b></td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                        htmlText.append("</table>");
                        
            htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");
             
                        htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
                           htmlText.append("<tr>");
                              htmlText.append("<td>");
                                 htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>Team,</b><br>Based on the Tech Screen  <b>" + consultantName + "</b> submitted for <b>" + title + "</b> " + bodyPart + ".Please see the comments below.      </td>");
                                    htmlText.append("</tr>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Interview Type:</b> " + interviewType + "<br>");
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Comments:</b> " + comments + "</b><br>");
            
                                       htmlText.append("</td>");
                                    htmlText.append("</tr>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
                                        htmlText.append("<b style='font-size: 14px; color: #2368a0;'>Ratings:</b><br> ");
                                                                       HashMap skillSetMap = DataSourceDataProvider.getInstance().getSkillsAndRatings(Id);
            if (skillSetMap.size() > 0 && skillSetMap != null) {
                
                 Iterator it = skillSetMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    
                            htmlText.append("<b style='font-size: 14px; color: #ef4048;'>" + pair.getKey() + ":</b>  " + pair.getValue() + "<br>");
//                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>" + pair.getValue() + "</b>  <br>");
                }
            }
                                         
                                  
                                          htmlText.append("<b style='font-size: 14px; color: #232527;'>For further support please contact:</b> <br>");
                                           if (!presales1.equals("0") && presales1 != null) {
                String name = DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(presales1));
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Name:</b>  " + name + "<br>");
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Email:</b> " + email + "@miraclesoft.com<br>");
                                           }
                                            if (!presales2.equals("0") && presales2 != null) {
                email1 = DataSourceDataProvider.getInstance().getEmailId(Integer.parseInt(presales2));
                String name = DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(presales2));
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Name:</b> " + name + "<br>");
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Email:</b> " + email1 + "@miraclesoft.com<br>");
                                            }
                                       htmlText.append("</td>");
                                    htmlText.append("</tr>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
                                          htmlText.append("<b>For More Details about this Requirement, Click <a href='" + url + "crm/requirement/getConsultantRequirement.action?consultId=" + consultId + "&objectId=" + requirementId + "&requirementAdminFlag=YES' target='_blank' style='font-size: 14px; color: #2368a0;'>here</a></b>");
                                       htmlText.append("</td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                           htmlText.append("<tr>");
                              htmlText.append("<td>");
                                
                                 htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>Thanks & Regards,<br>Corporate Application Support Team, <br>Miracle Software Systems, Inc. <br>Email: hubble@miraclesoft.com <br>Phone: (+1)248-233-1814</td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                           htmlText.append("<tr>");
                              htmlText.append("<td>");
                                
                                 htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                        htmlText.append("</table>");
                       
            htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");
              
                        htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' class='responsive-table'>");
                           htmlText.append("<tr>");
                              htmlText.append("<td width='200' align='center' style='text-align: center;'>");
                                 htmlText.append("<table width='200' cellpadding='0' cellspacing='0' align='center'>");
                                    htmlText.append("<tr>");
                                      htmlText.append("<td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                       htmlText.append("<td width='10'><a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                       htmlText.append("<td width='10'><a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                       htmlText.append("<td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                       htmlText.append("<td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                           htmlText.append("<tr>");
                              htmlText.append("<td height='10'>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                           htmlText.append("<tr>");
                              htmlText.append("<td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems<br></td>");
                           htmlText.append("</tr>");
                        htmlText.append("</table>");
                        
            htmlText.append("</td>");
         htmlText.append("</tr>");
      htmlText.append("</table>");
   htmlText.append("</body>");
htmlText.append("</html>");
            ServiceLocator.getMailServices().doAddEmailLogNew(tomail, cc, subject, htmlText.toString(), "", "","Requirement Notification");
            //  doAddEmailLog(String toAddress, String cCAddress, String subject,String bodyContent,String createdDate,String bCCAddress)
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
