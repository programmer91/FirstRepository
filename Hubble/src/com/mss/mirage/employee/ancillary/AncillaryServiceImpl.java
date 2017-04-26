/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 26, 2007, 12:35 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : AncillaryServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.ancillary;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.ServiceLocatorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rajanikanth Teppala <rteppala@miraclesoft.com>
 *  This CLASS.... ENTER THE DESCRIPTION HERE
 */
public class AncillaryServiceImpl implements AncillaryService{
    
    
    
    /** Creates a new instance of AncillaryServiceImpl */
    public AncillaryServiceImpl() {
    }
    
    /**
     * The addorUpdateAncillary(AncillaryAction ancillaryAction) is used for adding Ancillary.
     * @throws ServiceLocatorException.
     * @return int variable.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@link com.mss.mirage.employee.ancillary.AncillaryVTO}
     * {@ling com.mss.mirage.employee.ancillary.AncillaryAction}
     */
    public int addorUpdateAncillary(AncillaryAction ancillaryPojo) throws ServiceLocatorException{
        
        int updatedRows = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            
            String queryString ="insert into tblEmpAncillary(EmpId,FatherName,FatherTitle," +
                    "FatherPhone,FatherAddress,LaptopType,Memory,HardDisk,Model," +
                    "SerialNo,PurchaseDate,WarrantyExpiryDate,ReferalName," +
                    "ReferalRelation,ReferalPhone,ReferalEmail,ReferalAltPhone," +
                    "ReferalComments,ContractOnFileId,ContractSignDt,ConExpDt," +
                    "ConPeriod,TrainPeriod,TrainStartDt,BondProvidedBy," +
                    "RelationtoEmp,PrevJobTitle,PrevJobCompany,PrevJobPhone," +
                    "PrevJobAddress,Comments,BachDegree,DegreeCollege,DegreeMarks," +
                    "DegreeSpecialization,DegreePassedYear,DegreeUniversity,PGDegree," +
                    "PGSpecialization,PGCollege,PGUniversity,PGMarks,PGPassYear) values(?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            preparedStatement =connection.prepareStatement(queryString);
            
            preparedStatement.setInt(1,ancillaryPojo.getId());
            
            preparedStatement.setString(2,ancillaryPojo.getFatherName());
            
            preparedStatement.setString(3,ancillaryPojo.getFatherTitle());
            
            preparedStatement.setString(4,ancillaryPojo.getFatherPhone());
            
            preparedStatement.setString(5,ancillaryPojo.getFatherAddress());
            
            preparedStatement.setString(6,ancillaryPojo.getLaptopType());
            
            preparedStatement.setString(7,ancillaryPojo.getMemory());
            
            preparedStatement.setString(8,ancillaryPojo.getHardDisk());
            
            preparedStatement.setString(9,ancillaryPojo.getModel());
            
            preparedStatement.setString(10,ancillaryPojo.getSerialNo());
            
            preparedStatement.setDate(11,ancillaryPojo.getPurchaseDate());
            
            preparedStatement.setDate(12,ancillaryPojo.getWarrantyExp());
            
            preparedStatement.setString(13,ancillaryPojo.getReferalName());
            
            preparedStatement.setString(14,ancillaryPojo.getReferalRelation());
            
            preparedStatement.setString(15,ancillaryPojo.getReferalPhone());
            
            preparedStatement.setString(16,ancillaryPojo.getReferalEmail());
            
            preparedStatement.setString(17,ancillaryPojo.getReferalAltPhone());
            
            preparedStatement.setString(18,ancillaryPojo.getReferalComments());
            
            preparedStatement.setInt(19,ancillaryPojo.getContractOnField());
            
            preparedStatement.setDate(20,ancillaryPojo.getContractSignDate());
            
            preparedStatement.setDate(21,ancillaryPojo.getContractExpDate());
            
            preparedStatement.setString(22,ancillaryPojo.getContractPeriod());
            
            preparedStatement.setInt(23,ancillaryPojo.getTrainPeriod());
            
            preparedStatement.setDate(24,ancillaryPojo.getTrainStartDate());
            
            preparedStatement.setString(25,ancillaryPojo.getBondProvidedBy());
            
            preparedStatement.setString(26,ancillaryPojo.getRelationToEmployee());
            
            preparedStatement.setString(27,ancillaryPojo.getJobTitle());
            
            preparedStatement.setString(28,ancillaryPojo.getJobCompany());
            
            preparedStatement.setString(29,ancillaryPojo.getJobPhone());
            
            preparedStatement.setString(30,ancillaryPojo.getJobAddress());
            
            preparedStatement.setString(31,ancillaryPojo.getComments());
            
            preparedStatement.setString(32,ancillaryPojo.getDegree());
            
            preparedStatement.setString(33,ancillaryPojo.getCollege());
            
            preparedStatement.setDouble(34,ancillaryPojo.getMarks());
            
            preparedStatement.setString(35,ancillaryPojo.getSpecialization());
            
            preparedStatement.setString(36,ancillaryPojo.getPassOut());
            
            preparedStatement.setString(37,ancillaryPojo.getUniversity());
            
            preparedStatement.setString(38,ancillaryPojo.getPg());
            
            preparedStatement.setString(39,ancillaryPojo.getPgSpecialization());
            
            preparedStatement.setString(40,ancillaryPojo.getPgCollege());
            
            preparedStatement.setString(41,ancillaryPojo.getPgUniversity());
            
            preparedStatement.setDouble(42,ancillaryPojo.getPgMarks());
            
            preparedStatement.setString(43,ancillaryPojo.getPgPassOut());
            
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
    
    /*
     *UPDATE tblCrmAccountDetails SET Applications=?,Hardware=?,Softwares=? WHERE AccountId="+rowUpdate;
     */
    
    /**
     * The UpdateAncillary(AncillaryAction ancillaryAction) is used for updating ancillary.
     * @throws ServiceLocatorException.
     * @return int variable.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@ling com.mss.mirage.employee.ancillary.AncillaryAction}
     */
    public int UpdateAncillary(AncillaryAction ancillaryPojo) throws ServiceLocatorException{
        int  updatedRows = 0;
        int rowUpdate=ancillaryPojo.getId();
        Connection connection = null;
        
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            
            String queryString ="UPDATE tblEmpAncillary SET FatherName=?, FatherTitle=?," +
                    " FatherPhone=?, FatherAddress=?, LaptopType=?, Memory=?," +
                    " HardDisk=?, Model=?, SerialNo=?, PurchaseDate=?," +
                    " WarrantyExpiryDate=?, ReferalName=?, ReferalRelation=?," +
                    " ReferalPhone=?, ReferalEmail=?, ReferalAltPhone=?," +
                    " ReferalComments=?, ContractOnFileId=?, ContractSignDt=?," +
                    " ConExpDt=?, ConPeriod=?, TrainPeriod=?, TrainStartDt=?," +
                    " BondProvidedBy=?, RelationtoEmp=?, PrevJobTitle=?," +
                    " PrevJobCompany=?, PrevJobPhone=?, PrevJobAddress=?," +
                    " Comments=?,ModifiedBy=?,ModifiedDate=?,BachDegree=?,DegreeCollege=?," +
                    " DegreeMarks=?,DegreeSpecialization=?,DegreePassedYear=?,DegreeUniversity=?," +
                    " PGDegree=?,PGSpecialization=?,PGCollege=?,PGUniversity=?,PGMarks=?,PGPassYear=? WHERE EmpId="+rowUpdate;
            
            preparedStatement =connection.prepareStatement(queryString);
            
            //preparedStatement.setInt(1,ancillaryPojo.getEmpId());
            
            preparedStatement.setString(1,ancillaryPojo.getFatherName());
            
            preparedStatement.setString(2,ancillaryPojo.getFatherTitle());
            
            preparedStatement.setString(3,ancillaryPojo.getFatherPhone());
            
            preparedStatement.setString(4,ancillaryPojo.getFatherAddress());
            
            preparedStatement.setString(5,ancillaryPojo.getLaptopType());
            
            preparedStatement.setString(6,ancillaryPojo.getMemory());
            
            preparedStatement.setString(7,ancillaryPojo.getHardDisk());
            
            preparedStatement.setString(8,ancillaryPojo.getModel());
            
            preparedStatement.setString(9,ancillaryPojo.getSerialNo());
            
            preparedStatement.setDate(10,ancillaryPojo.getPurchaseDate());
            
            preparedStatement.setDate(11,ancillaryPojo.getWarrantyExp());
            
            preparedStatement.setString(12,ancillaryPojo.getReferalName());
            
            preparedStatement.setString(13,ancillaryPojo.getReferalRelation());
            
            preparedStatement.setString(14,ancillaryPojo.getReferalPhone());
            
            preparedStatement.setString(15,ancillaryPojo.getReferalEmail());
            
            preparedStatement.setString(16,ancillaryPojo.getReferalAltPhone());
            
            preparedStatement.setString(17,ancillaryPojo.getReferalComments());
            
            preparedStatement.setInt(18,ancillaryPojo.getContractOnField());
            
            preparedStatement.setDate(19,ancillaryPojo.getContractSignDate());
            
            preparedStatement.setDate(20,ancillaryPojo.getContractExpDate());
            
            preparedStatement.setString(21,ancillaryPojo.getContractPeriod());
            
            preparedStatement.setInt(22,ancillaryPojo.getTrainPeriod());
            
            preparedStatement.setDate(23,ancillaryPojo.getTrainStartDate());
            
            preparedStatement.setString(24,ancillaryPojo.getBondProvidedBy());
            
            preparedStatement.setString(25,ancillaryPojo.getRelationToEmployee());
            
            preparedStatement.setString(26,ancillaryPojo.getJobTitle());
            
            preparedStatement.setString(27,ancillaryPojo.getJobCompany());
            
            preparedStatement.setString(28,ancillaryPojo.getJobPhone());
            
            preparedStatement.setString(29,ancillaryPojo.getJobAddress());
            
            preparedStatement.setString(30,ancillaryPojo.getComments());
            
            preparedStatement.setString(31,ancillaryPojo.getModifiedBy());
            
            preparedStatement.setTimestamp(32,ancillaryPojo.getModifiedDate());
            
            preparedStatement.setString(33,ancillaryPojo.getDegree());
            
            preparedStatement.setString(34,ancillaryPojo.getCollege());
            
            preparedStatement.setDouble(35,ancillaryPojo.getMarks());
            
            preparedStatement.setString(36,ancillaryPojo.getSpecialization());
            
            preparedStatement.setString(37,ancillaryPojo.getPassOut());
            
            preparedStatement.setString(38,ancillaryPojo.getUniversity());
            
            preparedStatement.setString(39,ancillaryPojo.getPg());
            
            preparedStatement.setString(40,ancillaryPojo.getPgSpecialization());
            
            preparedStatement.setString(41,ancillaryPojo.getPgCollege());
            
            preparedStatement.setString(42,ancillaryPojo.getPgUniversity());
            
            preparedStatement.setDouble(43,ancillaryPojo.getPgMarks());
            
            preparedStatement.setString(44,ancillaryPojo.getPgPassOut());
            
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
    
    /**
     * The getAncillary(int empId) is used for getting AncillaryVTO.
     * @throws ServiceLocatorException.
     * @return AncillaryVTO.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@link com.mss.mirage.employee.ancillary.AncillaryVTO}
     * {@ling com.mss.mirage.employee.ancillary.AncillaryAction}
     */
    public AncillaryVTO getAncillary(int empId) throws ServiceLocatorException{
        String queryString ="SELECT * from tblEmpAncillary where EmpId="+empId;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        AncillaryVTO ancillaryVTO = new AncillaryVTO();
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet =statement.executeQuery(queryString);
            ancillaryVTO.setEmpId(empId);
            while(resultSet.next()){
                ancillaryVTO.setFatherName(resultSet.getString("FatherName"));
                ancillaryVTO.setFatherTitle(resultSet.getString("FatherTitle"));
                ancillaryVTO.setFatherPhone(resultSet.getString("FatherPhone"));
                ancillaryVTO.setFatherAddress(resultSet.getString("FatherAddress"));
                ancillaryVTO.setLaptopType(resultSet.getString("LaptopType"));
                ancillaryVTO.setMemory(resultSet.getString("Memory"));
                
                ancillaryVTO.setHardDisk(resultSet.getString("HardDisk"));
                ancillaryVTO.setModel(resultSet.getString("Model"));
                ancillaryVTO.setSerialNo(resultSet.getString("SerialNo"));
                ancillaryVTO.setPurchaseDate(resultSet.getDate("PurchaseDate"));
                ancillaryVTO.setWarrantyExp(resultSet.getDate("WarrantyExpiryDate"));
                ancillaryVTO.setReferalName(resultSet.getString("ReferalName"));
                
                ancillaryVTO.setReferalRelation(resultSet.getString("ReferalRelation"));
                ancillaryVTO.setReferalPhone(resultSet.getString("ReferalPhone"));
                ancillaryVTO.setReferalEmail(resultSet.getString("ReferalEmail"));
                ancillaryVTO.setReferalAltPhone(resultSet.getString("ReferalAltPhone"));
                ancillaryVTO.setReferalComments(resultSet.getString("ReferalComments"));
                ancillaryVTO.setContractOnField(resultSet.getInt("ContractOnFileId"));
                
                ancillaryVTO.setContractSignDate(resultSet.getDate("ContractSignDt"));
                ancillaryVTO.setContractExpDate(resultSet.getDate("ConExpDt"));
                ancillaryVTO.setContractPeriod(resultSet.getString("ConPeriod"));
                ancillaryVTO.setTrainPeriod(resultSet.getInt("TrainPeriod"));
                ancillaryVTO.setTrainStartDate(resultSet.getDate("TrainStartDt"));
                ancillaryVTO.setBondProvidedBy(resultSet.getString("BondProvidedBy"));
                
                ancillaryVTO.setRelationToEmployee(resultSet.getString("RelationtoEmp"));
                ancillaryVTO.setJobTitle(resultSet.getString("PrevJobTitle"));
                ancillaryVTO.setJobCompany(resultSet.getString("PrevJobCompany"));
                ancillaryVTO.setJobPhone(resultSet.getString("PrevJobPhone"));
                ancillaryVTO.setJobAddress(resultSet.getString("PrevJobAddress"));
                ancillaryVTO.setComments(resultSet.getString("Comments"));
                
                ancillaryVTO.setDegree(resultSet.getString("BachDegree"));
                ancillaryVTO.setCollege(resultSet.getString("DegreeCollege"));
                ancillaryVTO.setMarks(resultSet.getDouble("DegreeMarks"));
                ancillaryVTO.setSpecialization(resultSet.getString("DegreeSpecialization"));
                ancillaryVTO.setPassOut(resultSet.getString("DegreePassedYear"));
                ancillaryVTO.setUniversity(resultSet.getString("DegreeUniversity"));
                
                ancillaryVTO.setPg(resultSet.getString("PGDegree"));
                ancillaryVTO.setPgCollege(resultSet.getString("PGCollege"));
                ancillaryVTO.setPgMarks(resultSet.getDouble("PGMarks"));
                ancillaryVTO.setPgSpecialization(resultSet.getString("PGSpecialization"));
                ancillaryVTO.setPgPassOut(resultSet.getString("PGPassYear"));
                ancillaryVTO.setPgUniversity(resultSet.getString("PGUniversity"));
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
        return ancillaryVTO;
    }
    
    public int checkAction(int empId) throws ServiceLocatorException {
        int count=0;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        connection =ConnectionProvider.getInstance().getConnection();
        String query="select * from tblEmpAncillary where EmpId="+empId;
        try{
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
    
}
