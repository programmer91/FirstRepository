/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.immigration
 *
 * Date    :  October 9, 2007, 3:52 PM
 *
 * Author  :Jyothi Nimmagadda<jnimmagadda@miraclesoft.com>
 *
 * File    :ImmigrationServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */


package com.mss.mirage.employee.immigration;


import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.mss.mirage.util.ConnectionProvider;


/**
 * The <code>ImmigrationServiceImpl</code>  class is used for getting new Note details and  overridding the methods from the
 * <i>EmployeeTravelService.class</i>
 * <p>
 * Then it overrides  methods in <code>ImmigrationService</code> class and performs doImmigrationEdit() method and doImmigrationAdd()
 * in <code>immigrationAction</code> for inserting employee details in  TBLEMPIMMIGRATION  table.
 *
 * @author Jyothi Nimmagadda <a href="mailto:jnimmagadda@miraclesoft.com">jnimmagadda@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.immigration.ImmigrationAction
 * @see com.mss.mirage.immigration.ImmigrationService
 * @see com.mss.mirage.immigration.ImmigrationServiceImpl
 * @see com.mss.mirage.immigration.ImmigrationVTO
 */
public class ImmigrationServiceImpl implements ImmigrationService{
    
    
    /** Creates a new instance of ImmigrationServiceImpl */
    public ImmigrationServiceImpl() {
    }
    
    /**
     * The addImmigration() is used for insert the data  from  the Immigration.jsp page into the databasetable.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public boolean addImmigration(ImmigrationAction immigrationAction) throws ServiceLocatorException {
        boolean isInserted =false;
        //Generating query to inserting activity details into tblCrmActivity
        String queryString = "";
        Session hibernateSession = null;
        Transaction transaction = null;
        // travelVTO = new TravelVTO();
        try {
            hibernateSession = HibernateServiceLocator.getInstance().getSession();
            
            transaction = hibernateSession.beginTransaction();
            
            /* Save Pojo object into Session Object */
            hibernateSession.save(immigrationAction);
            
            /* Pushing Pojo Object into DataBase */
            hibernateSession.flush();
            
            /* Commit a Tranasaction */
            transaction.commit();
            
            isInserted = true;
        } catch(Exception e) {
            isInserted = false;
            throw new ServiceLocatorException(e);
        }finally{
            hibernateSession.close();
        }
        return isInserted;
    }
    
    /**
     * The editImmigration() is used for update the data  from  the Immigration.jsp page and save update data into the databasetable.
     *  @return  The name of the string or resultType by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public boolean editImmigration(ImmigrationAction immigrationPojo) throws ServiceLocatorException {
        Session hibernateSession=null;
        Transaction transaction = null;
        boolean isUpdated = false;
        try {
            
            hibernateSession = HibernateServiceLocator.getInstance().getSession();
            
            transaction = hibernateSession.beginTransaction();
            
            /* updating pojo Object */
            hibernateSession.update(immigrationPojo);
            
            /* Pushing Pojo Object into DataBase */
            
            hibernateSession.flush();
            
            /* Commit a Tranasaction */
            transaction.commit();
            isUpdated = true;
        } catch(Exception e) {
            isUpdated = false;
            throw new ServiceLocatorException(e);
        }finally{
            /* Closing Session */
            hibernateSession.close();
        }
        return isUpdated;
    }
    
    
    /**
     * The getImmigrationVTO() is used for display the values from the database  in corresponding jsp  as  Immigration.jsp page.
     *  @return  The name of the reference value of ImmigrationVTO by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public ImmigrationVTO getImmigrationVTO(ImmigrationAction immigrationAction) throws ServiceLocatorException {
        
        ImmigrationVTO immigrationVTO = new ImmigrationVTO();
        immigrationVTO.setEmpId(immigrationAction.getEmpId());
        immigrationVTO.setCurrentImmigStatus(immigrationAction.getCurrentImmigStatus());
        immigrationVTO.setPassportNo(immigrationAction.getPassportNo());
        immigrationVTO.setPassportIssuedAt(immigrationAction.getPassportIssuedAt());
        immigrationVTO.setPassportIssue(immigrationAction.getPassportIssue());
        immigrationVTO.setPassportExp(immigrationAction.getPassportExp());
        immigrationVTO.setPassportCountry(immigrationAction.getPassportCountry());
        
        immigrationVTO.setDateOfArrival(immigrationAction.getDateOfArrival());
        immigrationVTO.setPortOfEntry(immigrationAction.getPortOfEntry());
        immigrationVTO.setI94No(immigrationAction.getI94No());
        immigrationVTO.setPtUniversity(immigrationAction.getPtUniversity());
        immigrationVTO.setPtStart(immigrationAction.getPtStart());
        immigrationVTO.setPtEnd(immigrationAction.getPtEnd());
        
        immigrationVTO.setH1Filed(immigrationAction.getH1Filed());
        immigrationVTO.setH1LinReceive(immigrationAction.getH1LinReceive());
        immigrationVTO.setH1Lin(immigrationAction.getH1Lin());
        immigrationVTO.setH1LcaEtaNo(immigrationAction.getH1LcaEtaNo());
        immigrationVTO.setH1Title(immigrationAction.getH1Title());
        immigrationVTO.setH1Start(immigrationAction.getH1Start());
        immigrationVTO.setH1Termination(immigrationAction.getH1Termination());
        immigrationVTO.setH1End(immigrationAction.getH1End());
        immigrationVTO.setH1Status(immigrationAction.getH1Status());
        immigrationVTO.setQueryReceived(immigrationAction.getQueryReceived());
        immigrationVTO.setQueryDue(immigrationAction.getQueryDue());
        immigrationVTO.setQueryResponded(immigrationAction.getQueryResponded());
        immigrationVTO.setQueryComments(immigrationAction.getQueryComments());
        
        immigrationVTO.setB1Filed(immigrationAction.getB1Filed());
        immigrationVTO.setB1Interview(immigrationAction.getB1Interview());
        immigrationVTO.setB1Start(immigrationAction.getB1Start());
        immigrationVTO.setB1End(immigrationAction.getB1End());
        immigrationVTO.setB1Status(immigrationAction.getB1Status());
        
        immigrationVTO.setL1Filed(immigrationAction.getL1Filed());
        immigrationVTO.setL1Interview(immigrationAction.getL1Interview());
        immigrationVTO.setL1Start(immigrationAction.getL1Start());
        immigrationVTO.setL1End(immigrationAction.getL1End());
        immigrationVTO.setL1Status(immigrationAction.getL1Status());
        
        immigrationVTO.setGcApplied(immigrationAction.getGcApplied());
        immigrationVTO.setGcApproved(immigrationAction.getGcApproved());
        immigrationVTO.setGcRef(immigrationAction.getGcRef());
        immigrationVTO.setGcTitle(immigrationAction.getGcTitle());
        immigrationVTO.setGcStatus(immigrationAction.getGcStatus());
        immigrationVTO.setOtherVisas(immigrationAction.getOtherVisas());
        immigrationVTO.setComments(immigrationAction.getComments());
      /*
      immigrationVTO.setModifiedBy(immigrationAction.getModifiedBy());
      immigrationVTO.setModifiedDate(immigrationAction.getModifiedDate());
       */
        
        return immigrationVTO;
    }
    
    /**
     * The getImmigration() is used for getting th value of the Id from  the Immigration.jsp page.
     *  @return  The name of the reference value of ImmigrationVTO  by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     * @throws  SQLException
     *          If a required system property value cannot be accessed.
     */
    public ImmigrationVTO getImmigration(int empId) throws ServiceLocatorException {
        
        ImmigrationVTO immigrationVTO = new ImmigrationVTO();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "select * from tblEmpImmigration where EmpId ="+empId;
        
        try{
            
            connection = com.mss.mirage.util.ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            
            while(resultSet.next()){
                immigrationVTO.setEmpId(resultSet.getInt("EmpId"));
                immigrationVTO.setCurrentImmigStatus(resultSet.getString("CurrentImmigStatus"));
                immigrationVTO.setPassportNo(resultSet.getString("PassportNo"));
                immigrationVTO.setPassportIssuedAt(resultSet.getString("PassportIssuedAt"));
                immigrationVTO.setPassportIssue(resultSet.getDate("PassportIssueDate"));
                immigrationVTO.setPassportExp(resultSet.getDate("PassportExpiryDate"));
                immigrationVTO.setPassportCountry(resultSet.getString("PassportCountry"));
                
                immigrationVTO.setDateOfArrival(resultSet.getDate("DateOfArrival"));
                immigrationVTO.setPortOfEntry(resultSet.getString("PortOfEntry"));
                immigrationVTO.setI94No(resultSet.getString("I94Number"));
                immigrationVTO.setPtUniversity(resultSet.getString("PTUniversity"));
                immigrationVTO.setPtStart(resultSet.getDate("PtStartDate"));
                immigrationVTO.setPtEnd(resultSet.getDate("PtEndDate"));
                
                immigrationVTO.setH1Filed(resultSet.getDate("H1FiledDate"));
                immigrationVTO.setH1LinReceive(resultSet.getDate("H1LinRcvDate"));
                immigrationVTO.setH1Lin(resultSet.getString("H1Lin"));
                immigrationVTO.setH1LcaEtaNo(resultSet.getString("H1LcaEtaNo"));
                immigrationVTO.setH1Title(resultSet.getString("H1Title"));
                immigrationVTO.setH1Start(resultSet.getDate("H1StartDate"));
                immigrationVTO.setH1Termination(resultSet.getDate("H1TerminationDate"));
                immigrationVTO.setH1End(resultSet.getDate("H1EndDate"));
                immigrationVTO.setH1Status(resultSet.getString("H1Status"));
                immigrationVTO.setQueryReceived(resultSet.getDate("QueryReceivedDate"));
                immigrationVTO.setQueryDue(resultSet.getDate("QueryDueDate"));
                immigrationVTO.setQueryResponded(resultSet.getDate("QueryResponded"));
                immigrationVTO.setQueryComments(resultSet.getString("QueryComments"));
                
                immigrationVTO.setB1Filed(resultSet.getDate("B1FiledDate"));
                immigrationVTO.setB1Interview(resultSet.getDate("B1InterviewDate"));
                immigrationVTO.setB1Start(resultSet.getDate("B1StartDate"));
                immigrationVTO.setB1End(resultSet.getDate("B1EndDate"));
                immigrationVTO.setB1Status(resultSet.getString("B1Status"));
                
                immigrationVTO.setL1Filed(resultSet.getDate("L1FiledDate"));
                immigrationVTO.setL1Interview(resultSet.getDate("L1InterviewDate"));
                immigrationVTO.setL1Start(resultSet.getDate("L1StartDate"));
                immigrationVTO.setL1End(resultSet.getDate("L1EndDate"));
                immigrationVTO.setL1Status(resultSet.getString("L1Status"));
                
                immigrationVTO.setGcApplied(resultSet.getDate("GcAppliedDate"));
                immigrationVTO.setGcApproved(resultSet.getDate("GcApprovedDate"));
                immigrationVTO.setGcRef(resultSet.getString("GcRef"));
                immigrationVTO.setGcTitle(resultSet.getString("GcTitle"));
                immigrationVTO.setGcStatus(resultSet.getString("GcStatus"));
                immigrationVTO.setOtherVisas(resultSet.getString("OtherVISAS"));
                immigrationVTO.setComments(resultSet.getString("Comments"));
               /*
             immigrationVTO.setModifiedBy(resultSet.getString("ModifiedBy"));
             immigrationVTO.setModifiedDate(resultSet.getTimestamp("ModifiedDate"));
                */
                
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
        
        return immigrationVTO;
    }
    
    public int checkAction(int empId) throws ServiceLocatorException {
        int count=0;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        connection =ConnectionProvider.getInstance().getConnection();
        String query="select * from tblEmpImmigration where EmpId="+empId;
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
