/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 27, 2007, 12:35 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : EmpInsuranceServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.insurance;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;

import com.mss.mirage.util.ConnectionProvider;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Rajanikanth Teppala<rteppala@miraclesoft.com>
 * This CLASS.... ENTER THE DESCRIPTION HERE
 */

public class EmpInsuranceServiceImpl implements EmpInsuranceService{
    
    
    
    
   
    
    /** Creates a new instance of EmpInsuranceServiceImpl */
    public EmpInsuranceServiceImpl() {
    }
    
    /**  This method can be used to add a new insurance.
     *
     *
     * @return isDoInsurance.
     */
    public boolean doInsuranceAdd(EmpInsuranceAction empInsuranceAction) {
        Session session=null;
        SessionFactory sessionFactory=null;
        Transaction transaction=null;
        EmpInsuranceVTO empInsuranceVTO = new EmpInsuranceVTO();
        boolean isDoInsurance = false;
        try {
            empInsuranceVTO.setEmpId(empInsuranceAction.getEmpId());
            empInsuranceVTO.setLicPolicyNumber(empInsuranceAction.getLicPolicyNumber());
            empInsuranceVTO.setLicPolicyDate(empInsuranceAction.getLicPolicyDate());
            empInsuranceVTO.setLicPolicyValues(empInsuranceAction.getLicPolicyValues());
            empInsuranceVTO.setLicPolicyComNumber(empInsuranceAction.getLicPolicyComNumber());
            empInsuranceVTO.setHealthInsuranceType(empInsuranceAction.getHealthInsuranceType());
            empInsuranceVTO.setHealthInsEffecDate(empInsuranceAction.getHealthInsEffecDate());
            empInsuranceVTO.setHealthInsuranceCoverage(empInsuranceAction.getHealthInsuranceCoverage());
            empInsuranceVTO.setHealthInsComName(empInsuranceAction.getHealthInsComName());
            empInsuranceVTO.setHealthInsurePolicyNum(empInsuranceAction.getHealthInsurePolicyNum());
            empInsuranceVTO.setHealthInsPolicyDate(empInsuranceAction.getHealthInsPolicyDate());
            empInsuranceVTO.setHealthInsureNumOfDep(empInsuranceAction.getHealthInsureNumOfDep());
            empInsuranceVTO.setHealthInsureDedAmt(empInsuranceAction.getHealthInsureDedAmt());
            empInsuranceVTO.setCobraNotif(empInsuranceAction.getCobraNotif());
            empInsuranceVTO.setCobraNotifDate(empInsuranceAction.getCobraNotifDate());
            empInsuranceVTO.setCobraStartDate(empInsuranceAction.getCobraStartDate());
            empInsuranceVTO.setCobraPayment(empInsuranceAction.getCobraPayment());
            empInsuranceVTO.setDentalInsureType(empInsuranceAction.getDentalInsureType());
            empInsuranceVTO.setDentalInsEffecDate(empInsuranceAction.getDentalInsEffecDate());
            empInsuranceVTO.setDentalInsureCoverage(empInsuranceAction.getDentalInsureCoverage());
            empInsuranceVTO.setMedicalLeave(empInsuranceAction.getMedicalLeave());
            empInsuranceVTO.setMedicalLeaveEffecDate(empInsuranceAction.getMedicalLeaveEffecDate());
            empInsuranceVTO.setMedicalHours(empInsuranceAction.getMedicalHours());
            empInsuranceVTO.setShortTermDisability(empInsuranceAction.getShortTermDisability());
            empInsuranceVTO.setLongTermDisability(empInsuranceAction.getLongTermDisability());
            empInsuranceVTO.setComments(empInsuranceAction.getComments());
            empInsuranceVTO.setEffecChangeDate(empInsuranceAction.getEffecChangeDate());
            // Session session=(Session) HibernateServiceLocator.getInstance().getSession();
            //Create a seesion for connectionFactory
            sessionFactory=new Configuration().configure().buildSessionFactory();//for get session factory instance from hibernate service locator
            session=sessionFactory.openSession();
            //Transaction Started
            transaction=session.beginTransaction();
            //save values in Database
            session.save(empInsuranceVTO);
            session.flush();
            transaction.commit();
            isDoInsurance =true;
            
        }
        
        catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            session.close();
        }
        return isDoInsurance;
    }
    
    /**  This method can be used to edit a new Insurance.
     *
     *
     * @return isdoEdit.
     */
    /*
    public boolean doInsuranceEdit(EmpInsuranceAction empInsuranceAction) throws ServiceLocatorException {
        //public boolean updateActivity(ActivityAction activityPojo) throws ServiceLocatorException {
        boolean isUpdated = false;
        try {
     
            // Session session=(Session) HibernateServiceLocator.getInstance().getSession();
            //Create a seesion for connectionFactory
            SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();//for get session factory instance from hibernate service locator
            Session session=sessionFactory.openSession();
            //Transaction Started
            Transaction tra=session.beginTransaction();
            //save values in Database
            session.update(empInsuranceAction);
     
            isDoInsurance =true;
     
     
            session.flush();
            tra.commit();
            session.close();
     
     
        } catch(Exception e) {
            isUpdated = false;
            e.printStackTrace();
        }
        return isUpdated;
     
    }
     */
    
    public boolean doInsuranceEdit(EmpInsuranceAction empInsuranceAction) throws ServiceLocatorException{
        Session session=null;
        SessionFactory sessionFactory=null;
        Transaction transaction=null;
        EmpInsuranceVTO empInsuranceVTO = new EmpInsuranceVTO();
        boolean isdoEdit = false;
        empInsuranceVTO.setEmpId(empInsuranceAction.getEmpId());
        empInsuranceVTO.setLicPolicyNumber(empInsuranceAction.getLicPolicyNumber());
        empInsuranceVTO.setLicPolicyDate(empInsuranceAction.getLicPolicyDate());
        empInsuranceVTO.setLicPolicyValues(empInsuranceAction.getLicPolicyValues());
        empInsuranceVTO.setLicPolicyComNumber(empInsuranceAction.getLicPolicyComNumber());
        empInsuranceVTO.setHealthInsuranceType(empInsuranceAction.getHealthInsuranceType());
        empInsuranceVTO.setHealthInsEffecDate(empInsuranceAction.getHealthInsEffecDate());
        empInsuranceVTO.setHealthInsuranceCoverage(empInsuranceAction.getHealthInsuranceCoverage());
        empInsuranceVTO.setHealthInsComName(empInsuranceAction.getHealthInsComName());
        empInsuranceVTO.setHealthInsurePolicyNum(empInsuranceAction.getHealthInsurePolicyNum());
        empInsuranceVTO.setHealthInsPolicyDate(empInsuranceAction.getHealthInsPolicyDate());
        empInsuranceVTO.setHealthInsureNumOfDep(empInsuranceAction.getHealthInsureNumOfDep());
        empInsuranceVTO.setHealthInsureDedAmt(empInsuranceAction.getHealthInsureDedAmt());
        empInsuranceVTO.setCobraNotif(empInsuranceAction.getCobraNotif());
        empInsuranceVTO.setCobraNotifDate(empInsuranceAction.getCobraNotifDate());
        empInsuranceVTO.setCobraStartDate(empInsuranceAction.getCobraStartDate());
        empInsuranceVTO.setCobraPayment(empInsuranceAction.getCobraPayment());
        empInsuranceVTO.setDentalInsureType(empInsuranceAction.getDentalInsureType());
        empInsuranceVTO.setDentalInsEffecDate(empInsuranceAction.getDentalInsEffecDate());
        empInsuranceVTO.setDentalInsureCoverage(empInsuranceAction.getDentalInsureCoverage());
        empInsuranceVTO.setMedicalLeave(empInsuranceAction.getMedicalLeave());
        empInsuranceVTO.setMedicalLeaveEffecDate(empInsuranceAction.getMedicalLeaveEffecDate());
        empInsuranceVTO.setMedicalHours(empInsuranceAction.getMedicalHours());
        empInsuranceVTO.setShortTermDisability(empInsuranceAction.getShortTermDisability());
        empInsuranceVTO.setLongTermDisability(empInsuranceAction.getLongTermDisability());
        empInsuranceVTO.setComments(empInsuranceAction.getComments());
        empInsuranceVTO.setEffecChangeDate(empInsuranceAction.getEffecChangeDate());
        empInsuranceVTO.setModifiedBy(empInsuranceAction.getModifiedBy());
        empInsuranceVTO.setModifiedDate(empInsuranceAction.getModifiedDate());
        try{
            //Create a seesion for connectionFactory
            session=(Session) HibernateServiceLocator.getInstance().getSession();//for get session factory instance from hibernate service locator
            //begin Transaction
            transaction=session.beginTransaction();
            transaction.begin();
            //save the values in Database
            session.update(empInsuranceVTO);
            isdoEdit = true;
            session.flush();
            transaction.commit();
            
        }catch(Exception ex){
            throw new ServiceLocatorException(ex);
        }finally{
            session.close();
        }
        return isdoEdit;
        
    }
    
    /**  This method used to get Insurance Details.
     *
     *
     * @return isdoEdit.
     */
    public EmpInsuranceVTO getInsurance(int empId) throws ServiceLocatorException {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet = null;
        EmpInsuranceVTO empInsuranceVTO = new EmpInsuranceVTO();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String sql_string = "select * from tblEmpInsurance where EmpId="+empId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql_string);
            empInsuranceVTO.setEmpId(empId);
            while(resultSet.next()){
                //empInsuranceVTO.setId(resultSet.getInt("Id"));
                empInsuranceVTO.setEmpId(resultSet.getInt("EmpId"));
                empInsuranceVTO.setLicPolicyNumber(resultSet.getString("LicPolicyNumber"));
                empInsuranceVTO.setLicPolicyDate(resultSet.getDate("LicPolicyDate"));
                empInsuranceVTO.setLicPolicyValues(resultSet.getString("LicPolicyValues"));
                empInsuranceVTO.setLicPolicyComNumber(resultSet.getString("LicPolicyComNumber"));
                empInsuranceVTO.setHealthInsuranceType(resultSet.getString("HealthInsType"));
                empInsuranceVTO.setHealthInsEffecDate(resultSet.getDate("HealthInsEffecDate"));
                empInsuranceVTO.setHealthInsuranceCoverage(resultSet.getInt("HealthInsCoverage"));
                empInsuranceVTO.setHealthInsComName(resultSet.getString("HealthInsComName"));
                empInsuranceVTO.setHealthInsurePolicyNum(resultSet.getString("HealthInsPolicyNumber"));
                empInsuranceVTO.setHealthInsPolicyDate(resultSet.getDate("HealthInsPolicyDate"));
                empInsuranceVTO.setHealthInsureNumOfDep(resultSet.getInt("HealthInsNumOfDep"));
                empInsuranceVTO.setHealthInsureDedAmt(resultSet.getString("HealthInsDedAmt"));
                empInsuranceVTO.setCobraNotif(resultSet.getDouble("CobraNotif"));
                empInsuranceVTO.setCobraNotifDate(resultSet.getDate("CobraNotifDate"));
                empInsuranceVTO.setCobraStartDate(resultSet.getDate("CobraStartDate"));
                empInsuranceVTO.setCobraPayment(resultSet.getInt("CobraPayment"));
                empInsuranceVTO.setDentalInsureType(resultSet.getInt("DentalInsType"));
                empInsuranceVTO.setDentalInsEffecDate(resultSet.getDate("DentalInsEffecDate"));
                empInsuranceVTO.setDentalInsureCoverage(resultSet.getInt("DentalInsCoverage"));
                empInsuranceVTO.setMedicalLeave(resultSet.getString("MedicalLeave"));
                empInsuranceVTO.setMedicalLeaveEffecDate(resultSet.getDate("MedLeaveEffecDate"));
                empInsuranceVTO.setMedicalHours(resultSet.getDouble("MedicalHours"));
                empInsuranceVTO.setShortTermDisability(resultSet.getString("ShortTermDisability"));
                empInsuranceVTO.setLongTermDisability(resultSet.getString("LongTermDisability"));
                empInsuranceVTO.setComments(resultSet.getString("Comments"));
                empInsuranceVTO.setEffecChangeDate(resultSet.getDate("EffecChangeDate"));
            }
            
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        
        return empInsuranceVTO;
    }
    
    public int checkAction(int empId) throws ServiceLocatorException {
        int count=0;
        Connection connection=null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement=null;
        connection =ConnectionProvider.getInstance().getConnection();
        String query="select * from tblEmpInsurance where EmpId="+empId;
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
