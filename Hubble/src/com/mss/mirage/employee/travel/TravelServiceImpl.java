/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  May 30, 2008, 9:55 PM
 *
 * Author  : Hari Krishna Kondala<hkondala@miraclesoft.com>
 *
 * File    : TravelServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.employee.travel;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author miracle
 */
public class TravelServiceImpl implements TravelService{
    
    /** Creates a new instance of TravelServiceImpl */
    public TravelServiceImpl() {
    }

    public int checkAction(int empid) throws ServiceLocatorException {
        int count = 0;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String query = "SELECT * FROM tblEmpTravelProfile WHERE EmpId="+empid;
        
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                count++;
            }
        }catch(Exception e){
            throw new ServiceLocatorException(e);
        }finally{
            try {
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        
        return count;
    }
    
    public TravelVTO getTravel(int empid) throws ServiceLocatorException {
        String query = "SELECT * from tblEmpTravelProfile WHERE EmpId="+empid;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        TravelVTO travelVTO = new TravelVTO();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            travelVTO.setEmpId(empid);
            while(resultSet.next()) {
                travelVTO.setCorpCardType(resultSet.getString("CorpCardType"));
                travelVTO.setCorpCardNo(resultSet.getString("CorpCardNo"));
                travelVTO.setCorpExpDate(resultSet.getDate("CorpExpDate"));
                travelVTO.setCorpNameOnCard(resultSet.getString("CorpNameOnCard"));
                travelVTO.setCorpCardCode(resultSet.getString("CorpCardCode"));
                travelVTO.setPerCardType(resultSet.getString("PerCardType"));
                travelVTO.setPerCardNo(resultSet.getString("PerCardNo"));
                travelVTO.setPerExpDate(resultSet.getDate("PerExpDate"));
                travelVTO.setPerNameOnCard(resultSet.getString("PerNameOnCard"));
                travelVTO.setPerCardCode(resultSet.getString("PerCardCode"));
                travelVTO.setFQProg1(resultSet.getString("FQProg1"));
                travelVTO.setFQNo1(resultSet.getString("FQNo1"));
                travelVTO.setFQProg2(resultSet.getString("FQProg2"));
                travelVTO.setFQNo2(resultSet.getString("FQNo2"));
                travelVTO.setFQProg3(resultSet.getString("FQProg3"));
                travelVTO.setFQNo3(resultSet.getString("FQNo3"));
                travelVTO.setFQProg4(resultSet.getString("FQProg4"));
                travelVTO.setFQNo4(resultSet.getString("FQNo4"));
                travelVTO.setFQProg5(resultSet.getString("FQProg5"));
                travelVTO.setFQNo5(resultSet.getString("FQNo5"));
                travelVTO.setFQProg6(resultSet.getString("FQProg6"));
                travelVTO.setFQNo6(resultSet.getString("FQNo6"));
                travelVTO.setPrefSeat(resultSet.getString("PrefSeat"));
                travelVTO.setPrefMeals(resultSet.getString("PrefMeals"));
                travelVTO.setPrefCarCo(resultSet.getString("PrefCarCo"));
                travelVTO.setPrefAirLine(resultSet.getString("PrefAirLine"));
                travelVTO.setPrefHotel(resultSet.getString("PrefHotel"));
                travelVTO.setPrefOther(resultSet.getString("PrefOther"));
                travelVTO.setComments(resultSet.getString("Comments"));
                travelVTO.setModifiedBy(resultSet.getString("ModifiedBy"));
                travelVTO.setModifiedDate(resultSet.getTimestamp("ModifiedDate"));
            }
        }catch (SQLException se){
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return travelVTO;
    }
    
    public TravelVTO getEmpDetails(int empid) throws ServiceLocatorException {
        String queryString = "SELECT * FROM tblEmployee WHERE Id="+empid;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        TravelVTO travelVTO = new TravelVTO();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()) {
                travelVTO.setFName(resultSet.getString("FName"));
                travelVTO.setMName(resultSet.getString("MName"));
                travelVTO.setLName(resultSet.getString("LName"));
                travelVTO.setOrganization(resultSet.getString("OrgId"));
                travelVTO.setDepartment(resultSet.getString("DepartmentId"));
                travelVTO.setTitle(resultSet.getString("TitleTypeId"));
                travelVTO.setStatus(resultSet.getString("State"));
                travelVTO.setEmployeeType(resultSet.getString("EmployeeTypeId"));
            }
        }catch (SQLException se){
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return travelVTO;
    }
    
    public int addTravel(TravelAction travelAction) throws ServiceLocatorException {
        
        int updatedRows = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
//            String queryString = "insert into tblEmpTravelProfile(EmpId,CorpCardType,CorpCardNo,CorpExpDate," +
//                    "CorpNameOnCard,CorpCardCode,PerCardType,PerCardNo,PerExpDate,PerNameOnCard,PerCardCode" +
//                    "FQProg1,FQNo1,FQProg2,FQNo2,FQProg3,FQNo3,FQProg4,FQNo4,FQProg5,FQNo5,FQProg6,FQNo6,PrefSeat" +
//                    "PrefMeals,PrefCarCo,PrefAirLine,PrefHotel,PrefOther,Comments,ModifiedBy,ModifiedDate)" +
//                    " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
//                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String queryString = "insert into tblEmpTravelProfile values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            preparedStatement =connection.prepareStatement(queryString);
            
            preparedStatement.setInt(1,travelAction.getId());
            preparedStatement.setString(2,travelAction.getCorpCardType());
            preparedStatement.setString(3,travelAction.getCorpCardNo());
            preparedStatement.setDate(4,travelAction.getCorpExpDate());
            preparedStatement.setString(5,travelAction.getCorpNameOnCard());
            preparedStatement.setString(6,travelAction.getCorpCardCode());
            preparedStatement.setString(7,travelAction.getPerCardType());
            preparedStatement.setString(8,travelAction.getPerCardNo());
            preparedStatement.setDate(9,travelAction.getPerExpDate());
            preparedStatement.setString(10,travelAction.getPerNameOnCard());
            preparedStatement.setString(11,travelAction.getPerCardCode());
            preparedStatement.setString(12,travelAction.getFQProg1());
            preparedStatement.setString(13,travelAction.getFQNo1());
            preparedStatement.setString(14,travelAction.getFQProg2());
            preparedStatement.setString(15,travelAction.getFQNo2());
            preparedStatement.setString(16,travelAction.getFQProg3());
            preparedStatement.setString(17,travelAction.getFQNo3());
            preparedStatement.setString(18,travelAction.getFQProg4());
            preparedStatement.setString(19,travelAction.getFQNo4());
            preparedStatement.setString(20,travelAction.getFQProg5());
            preparedStatement.setString(21,travelAction.getFQNo5());
            preparedStatement.setString(22,travelAction.getFQProg6());
            preparedStatement.setString(23,travelAction.getFQNo6());
            preparedStatement.setString(24,travelAction.getPrefSeat());
            preparedStatement.setString(25,travelAction.getPrefMeals());
            preparedStatement.setString(26,travelAction.getPrefCarCo());
            preparedStatement.setString(27,travelAction.getPrefAirLine());
            preparedStatement.setString(28,travelAction.getPrefHotel());
            preparedStatement.setString(29,travelAction.getPrefOther());
            preparedStatement.setString(30,travelAction.getComments());
            preparedStatement.setString(31,travelAction.getModifiedBy());
            preparedStatement.setTimestamp(32,travelAction.getModifiedDate());
            
            updatedRows = preparedStatement.executeUpdate();
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement != null){
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
        return updatedRows;
    }
    
    public int UpdateTravel(TravelAction travelAction) throws ServiceLocatorException {
        int updatedRows = 0;
        int rowUpdate=travelAction.getId();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            
            String queryString = "UPDATE tblEmpTravelProfile SET CorpCardType=?, CorpCardNo=?, CorpExpDate=?, CorpNameOnCard=?, " +
                    "CorpCardCode=?, PerCardType=?, PerCardNo=?, PerExpDate=?, PerNameOnCard=?, PerCardCode=?, FQProg1=?, FQNo1=?, " +
                    "FQProg2=?, FQNo2=?, FQProg3=?, FQNo3=?, FQProg4=?, FQNo4=?, FQProg5=?, FQNo5=?, FQProg6=?, FQNo6=?, PrefSeat=?, " +
                    "PrefMeals=?, PrefCarCo=?, PrefAirLine=?, PrefHotel=?, PrefOther=?, Comments=?, ModifiedBy=?, ModifiedDate=? " +
                    "WHERE EmpId="+rowUpdate;
            
            preparedStatement =connection.prepareStatement(queryString);
            
            //preparedStatement.setInt(1,travelAction.getId());
            preparedStatement.setString(1,travelAction.getCorpCardType());
            preparedStatement.setString(2,travelAction.getCorpCardNo());
            preparedStatement.setDate(3,travelAction.getCorpExpDate());
            preparedStatement.setString(4,travelAction.getCorpNameOnCard());
            preparedStatement.setString(5,travelAction.getCorpCardCode());
            preparedStatement.setString(6,travelAction.getPerCardType());
            preparedStatement.setString(7,travelAction.getPerCardNo());
            preparedStatement.setDate(8,travelAction.getPerExpDate());
            preparedStatement.setString(9,travelAction.getPerNameOnCard());
            preparedStatement.setString(10,travelAction.getPerCardCode());
            preparedStatement.setString(11,travelAction.getFQProg1());
            preparedStatement.setString(12,travelAction.getFQNo1());
            preparedStatement.setString(13,travelAction.getFQProg2());
            preparedStatement.setString(14,travelAction.getFQNo2());
            preparedStatement.setString(15,travelAction.getFQProg3());
            preparedStatement.setString(16,travelAction.getFQNo3());
            preparedStatement.setString(17,travelAction.getFQProg4());
            preparedStatement.setString(18,travelAction.getFQNo4());
            preparedStatement.setString(19,travelAction.getFQProg5());
            preparedStatement.setString(20,travelAction.getFQNo5());
            preparedStatement.setString(21,travelAction.getFQProg6());
            preparedStatement.setString(22,travelAction.getFQNo6());
            preparedStatement.setString(23,travelAction.getPrefSeat());
            preparedStatement.setString(24,travelAction.getPrefMeals());
            preparedStatement.setString(25,travelAction.getPrefCarCo());
            preparedStatement.setString(26,travelAction.getPrefAirLine());
            preparedStatement.setString(27,travelAction.getPrefHotel());
            preparedStatement.setString(28,travelAction.getPrefOther());
            preparedStatement.setString(29,travelAction.getComments());
            preparedStatement.setString(30,travelAction.getModifiedBy());
            preparedStatement.setTimestamp(31,travelAction.getModifiedDate());
            
            updatedRows = preparedStatement.executeUpdate();
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement != null){
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
        return updatedRows;
    }
    
}
