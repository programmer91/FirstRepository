/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   April 7, 2008, 3:56 PM
 *
 * Author  :  Hari Krishna Kondala <hkondala@miraclesoft.com>
 *
 * File    : EmpProfileServiceImpl .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.profile;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;

/**
 *
 * @author miracle
 */
public class EmpProfileServiceImpl implements EmpProfileService{
    
    DateUtility date = new DateUtility();
    
    /** Creates a new instance of EmpProfileServiceImpl */
    public EmpProfileServiceImpl() {
    }
    
    public EmpProfileVTO getSkills(int userId) throws ServiceLocatorException {
        
        EmpProfileVTO empProfileVTO = new EmpProfileVTO();
        String queryString = "SELECT * from tblEmployee left outer join tblEmpImmigration on(tblEmployee.Id = tblEmpImmigration.EmpId) where tblEmployee.Id="+userId;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()) {
                empProfileVTO.setSkillSet(resultSet.getString("SkillSet"));
                empProfileVTO.setFirstName(resultSet.getString("FName"));
                empProfileVTO.setLastName(resultSet.getString("LName"));
                empProfileVTO.setMiddleName(resultSet.getString("MName"));
                empProfileVTO.setGender(resultSet.getString("Gender"));
                empProfileVTO.setAliasName(resultSet.getString("AliasName"));
                empProfileVTO.setMaritalStatus(resultSet.getString("MaritalStatus"));
                empProfileVTO.setCountry(resultSet.getString("Country"));
                empProfileVTO.setSsn(resultSet.getString("SSN"));
                empProfileVTO.setEmpno(resultSet.getString("EmpNo"));
                empProfileVTO.setNsrno(resultSet.getString("NSR"));
                empProfileVTO.setBirthDate(resultSet.getDate("BirthDate"));
                empProfileVTO.setOffBirthDate(resultSet.getDate("OffBirthDate"));
                empProfileVTO.setHireDate(resultSet.getDate("HireDate"));
                empProfileVTO.setAnniversaryDate(resultSet.getDate("AnniversaryDate"));
                empProfileVTO.setWorkPhoneNo(resultSet.getString("WorkPhoneNo"));
                empProfileVTO.setAltPhoneNo(resultSet.getString("AlterPhoneNo"));
                empProfileVTO.setHomePhoneNo(resultSet.getString("HomePhoneNo"));
                empProfileVTO.setCellPhoneNo(resultSet.getString("CellPhoneNo"));
                empProfileVTO.setOfficeEmail(resultSet.getString("Email1"));
                empProfileVTO.setHotelPhoneNo(resultSet.getString("HotelPhoneNo"));
                empProfileVTO.setPersonalEmail(resultSet.getString("Email2"));
                empProfileVTO.setIndiaPhoneNo(resultSet.getString("IndiaPhoneNo"));
                empProfileVTO.setOtherEmail(resultSet.getString("Email3"));
                empProfileVTO.setFaxNo(resultSet.getString("FaxPhoneNo"));
                empProfileVTO.setWorkingCountry(resultSet.getString("WorkingCountry"));
                
                empProfileVTO.setBankName(resultSet.getString("BankName"));
                 empProfileVTO.setAccNum(resultSet.getString("AccNum"));
                 empProfileVTO.setNameAsPerAcc(resultSet.getString("NameAsPerAcc"));
                 empProfileVTO.setIfscCode(resultSet.getString("IfscCode"));
                 empProfileVTO.setPhyChallenged(resultSet.getString("PhyChallenged"));
                 empProfileVTO.setPhyCategory(resultSet.getString("PhyCategory"));
                 empProfileVTO.setAadharNum(resultSet.getString("AadharNum"));
                 empProfileVTO.setAadharName(resultSet.getString("AadharName"));
                 empProfileVTO.setNameAsPerPan(resultSet.getString("NameAsPerPan"));
                 
                 empProfileVTO.setItgBatch(resultSet.getString("Itgbatch"));
                 
                  empProfileVTO.setUanNo(resultSet.getString("UANNo"));
                 empProfileVTO.setPfno(resultSet.getString("PFNo"));
                  empProfileVTO.setPassportExp(resultSet.getDate("PassportExpiryDate"));
                 empProfileVTO.setPassportNo(resultSet.getString("PassportNo"));
                 if(resultSet.getString("ReportsTo")!=null && !"".equals(resultSet.getString("ReportsTo"))){
                 empProfileVTO.setReportsTo(DataSourceDataProvider.getInstance().getemployeenamebyloginId(resultSet.getString("ReportsTo")));
                 }
                 else{
                      empProfileVTO.setReportsTo("");
                 }
                 if(resultSet.getString("OpsContactId")!=null && !"".equals(resultSet.getString("OpsContactId"))){
                 empProfileVTO.setOperationContact(DataSourceDataProvider.getInstance().getEmpNameByEmpId(resultSet.getInt("OpsContactId")));
                 }else{
                      empProfileVTO.setOperationContact("");
                 }
                 empProfileVTO.setAboutMe(resultSet.getString("AboutMe"));
            }
            
        } catch(SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
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
        
        return empProfileVTO;
    }
    
    public int updateSkills(EmpProfileAction empProfileAction, int userId) throws ServiceLocatorException {
        
        int updatedRows = 0;
        String resultMessage="";
        String queryString = "UPDATE tblEmployee set SkillSet=? where Id="+userId;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,empProfileAction.getSkillSet());
            updatedRows = preparedStatement.executeUpdate();
            
        } catch(SQLException se) {
            throw new ServiceLocatorException(se);
        } finally{
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
    
    public int updateEmpProfile(EmpProfileAction empProfileaction, int userId) throws ServiceLocatorException {
        
        int updatedRows = 0;
        int updatedRowsForImmegration=0;
        String resultMessage="";
        /*String queryString = "UPDATE tblEmployee set FName=?,MName=?,LName=?,AliasName=?,Gender=?,MaritalStatus=?,Country=?,BirthDate=?," +
                            "OffBirthDate=?,AnniversaryDate=?,WorkPhoneNo=?,AlterPhoneNo=?,HomePhoneNo=?,CellPhoneNo=?,HotelPhoneNo=?," +
                            "Email2=?,IndiaPhoneNo=?,Email3=?,FaxPhoneNo=? where Id="+userId;*/
     /*   String queryString = "UPDATE tblEmployee set AliasName=?,Gender=?,MaritalStatus=?,BirthDate=?," +
                            "OffBirthDate=?,AnniversaryDate=?,AlterPhoneNo=?,HomePhoneNo=?,CellPhoneNo=?,HotelPhoneNo=?," +
                            "Email2=?,IndiaPhoneNo=?,Email3=?,FaxPhoneNo=?,WorkPhoneNo=? where Id="+userId;*/
        
        String queryString ="";
        
     String queryString1 ="";
	 
	 int check= ServiceLocator.getImmigrationService().checkAction(userId);
        if(check==1)
        {
        queryString1 = "UPDATE tblEmpImmigration SET  PassportNo = ?, PassportExpiryDate= ? WHERE EmpId=?";
        } 
        else
        {
        queryString1 = "insert into tblEmpImmigration(PassportNo,PassportExpiryDate,EmpId) values(?,?,?)";
        }
         if(empProfileaction.getCountry().equalsIgnoreCase("India")){
            queryString = "UPDATE tblEmployee set AliasName=?,Gender=?,MaritalStatus=?,BirthDate=?," +
                            "OffBirthDate=?,AnniversaryDate=?,AlterPhoneNo=?,HomePhoneNo=?,CellPhoneNo=?,HotelPhoneNo=?," +
                            "Email2=?,IndiaPhoneNo=?,Email3=?,FaxPhoneNo=?,WorkPhoneNo=?,NameAsPerPan=?,SSN=?,EmpNo=? ,NSR=?,Itgbatch=?,AboutMe=?,PhyChallenged=?,PhyCategory=?,BankName=?,AccNum=?,NameAsPerAcc=?,IfscCode=?,AadharNum=?,AadharName=?,uanNo=?,pfno=? where Id="+userId;
        }else {
            queryString = "UPDATE tblEmployee set AliasName=?,Gender=?,MaritalStatus=?,BirthDate=?," +
                            "OffBirthDate=?,AnniversaryDate=?,AlterPhoneNo=?,HomePhoneNo=?,CellPhoneNo=?,HotelPhoneNo=?," +
                            "Email2=?,IndiaPhoneNo=?,Email3=?,FaxPhoneNo=?,WorkPhoneNo=?,NameAsPerPan=?,SSN=?,EmpNo=?,NSR=?,Itgbatch=?,AboutMe=? where Id="+userId;
        }
		
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Connection connection1 = null;
        PreparedStatement preparedStatement1 = null;
        try {
          connection1 = ConnectionProvider.getInstance().getConnection();
            preparedStatement1 = connection1.prepareStatement(queryString1);
	    preparedStatement1.setString(1,empProfileaction.getPassportNo());
            preparedStatement1.setDate(2,empProfileaction.getPassportExp());
            preparedStatement1.setInt(3,userId);
            updatedRowsForImmegration = preparedStatement1.executeUpdate();
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement.setString(1,empProfileaction.getFirstName());
            //preparedStatement.setString(2,empProfileaction.getMiddleName());
            //preparedStatement.setString(3,empProfileaction.getLastName());
            preparedStatement.setString(1,empProfileaction.getAliasName());
            preparedStatement.setString(2,empProfileaction.getGender());
            preparedStatement.setString(3,empProfileaction.getMaritalStatus());
            //preparedStatement.setString(7,empProfileaction.getCountry());
            preparedStatement.setDate(4,empProfileaction.getBirthDate());
            preparedStatement.setDate(5,empProfileaction.getOffBirthDate());
            preparedStatement.setDate(6,empProfileaction.getAnniversaryDate());
            //preparedStatement.setString(11,empProfileaction.getWorkPhoneNo());
            preparedStatement.setString(7,empProfileaction.getAltPhoneNo());
            preparedStatement.setString(8,empProfileaction.getHomePhoneNo());
            preparedStatement.setString(9,empProfileaction.getCellPhoneNo());
            preparedStatement.setString(10,empProfileaction.getHotelPhoneNo());
            preparedStatement.setString(11,empProfileaction.getPersonalEmail());
            preparedStatement.setString(12,empProfileaction.getIndiaPhoneNo());
            preparedStatement.setString(13,empProfileaction.getOtherEmail());
            preparedStatement.setString(14,empProfileaction.getFaxNo());
            preparedStatement.setString(15,empProfileaction.getWorkPhoneNo());
             preparedStatement.setString(16,empProfileaction.getNameAsPerPan());
            preparedStatement.setString(17,empProfileaction.getSsn());
            preparedStatement.setString(18,empProfileaction.getEmpno());
            preparedStatement.setString(19,empProfileaction.getNsrno());
            preparedStatement.setString(20,empProfileaction.getItgBatch());
            preparedStatement.setString(21,empProfileaction.getAboutMe());
            //NameAsPerPan=?,PhyChallenged=?,PhyCategory=?,BankName=?,AccNum=?,NameAsPerAcc=?,IfscCode=?,AadharNum=?,AadharName=?
             if(empProfileaction.getCountry().equalsIgnoreCase("India")){
                  
                  preparedStatement.setString(22,empProfileaction.getPhyChallenged());
                  preparedStatement.setString(23,empProfileaction.getPhyCategory());
                  preparedStatement.setString(24,empProfileaction.getBankName());
                  preparedStatement.setString(25,empProfileaction.getAccNum());
                  preparedStatement.setString(26,empProfileaction.getNameAsPerAcc());
                  preparedStatement.setString(27,empProfileaction.getIfscCode());
                  preparedStatement.setString(28,empProfileaction.getAadharNum());
                  preparedStatement.setString(29,empProfileaction.getAadharName());
                  preparedStatement.setString(30,empProfileaction.getUanNo());
                  preparedStatement.setString(31,empProfileaction.getPfno());
             }
            updatedRows = preparedStatement.executeUpdate();
            
        }catch(SQLException se) {
            throw new ServiceLocatorException(se);
        } finally{
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
                 if(preparedStatement1 != null){
                    preparedStatement1.close();
                    preparedStatement1 = null;
                }
                if(connection1 != null){
                    connection1.close();
                    connection1 = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return updatedRows;
    }
    
}
