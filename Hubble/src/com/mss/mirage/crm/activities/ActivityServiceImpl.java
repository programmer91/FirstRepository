/*
 * @(#)ActivityServiceImpl.java 1.0 November 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 *
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.crm.activities
 *
 * Date    :  September 30, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : ActivityServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.activities;

import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 *
 * @author Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class ActivityServiceImpl implements ActivityService{
    
    
    /** Creates a new instance of ActivityServiceImpl */
    public ActivityServiceImpl() {
    }
    
    /**
     * The getActivityVTO(ActivityAction activityAction) is used for getting activityVTO.
     * @throws ServiceLocatorException.
     * @return ActivityVTO.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@link com.mss.mirage.crm.activities.ActivityVTO}
     * {@ling com.mss.mirage.crm.activities.ActivityAction}
     */
    public ActivityVTO getActivityVTO(ActivityAction activityAction) throws ServiceLocatorException {
        
        ActivityVTO activityVTO = new ActivityVTO();
        activityVTO.setId(activityAction.getId());
        activityVTO.setAccountId(activityAction.getAccountId());
        activityVTO.setActivityType(activityAction.getActivityType());
        activityVTO.setContactId(activityAction.getContactId());
        activityVTO.setCampaignId(activityAction.getCampaignId());
        activityVTO.setAssignedToId(activityAction.getAssignedToId());
        activityVTO.setAssignedById(activityAction.getAssignedById());
        activityVTO.setPriority(activityAction.getPriority());
        activityVTO.setDueDate(activityAction.getDueDate());
        activityVTO.setStatus(activityAction.getStatus());
        activityVTO.setAlarm(activityAction.getAlarm());
        activityVTO.setDescription(activityAction.getDescription());
        activityVTO.setComments(activityAction.getComments());
        activityVTO.setCreatedById(activityAction.getCreatedById());
        activityVTO.setCreatedDate(activityAction.getCreatedDate());
        activityVTO.setModifiedById(activityAction.getModifiedById());
        activityVTO.setModifiedDate(activityAction.getModifiedDate());
        activityVTO.setContactType(activityAction.getContactType());
activityVTO.setPracticeName(activityAction.getPracticeName());
        return activityVTO;
    }
    
    /**
     * The addActivity(ActivityAction activityPojo) is used for adding activity.
     * @throws ServiceLocatorException.
     * @return boolean variable.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@link com.mss.mirage.crm.activities.ActivityAction}
     */
   public boolean addActivity(ActivityAction activityPojo,String loginId) throws ServiceLocatorException {        
        boolean isInserted = false;
        Connection connection = null;
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
       // PreparedStatement preStmt=null;      
        try {
            connection = ConnectionProvider.getInstance().getConnection();
           // System.out.println("Campaign Id-->"+activityPojo.getCampaignId());
            callableStatement = connection.prepareCall("{call spActivity(?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1,activityPojo.getAccountId());
            callableStatement.setString(2,activityPojo.getContactsHidden());
            callableStatement.setInt(3,activityPojo.getCampaignId());
            callableStatement.setString(4,activityPojo.getActivityType());
            callableStatement.setString(5,activityPojo.getStatus());
            callableStatement.setString(6,activityPojo.getPriority());
            //callableStatement.setBoolean(7,activityPojo.getAlarm());
          //  callableStatement.setTimestamp(8,activityPojo.getDueDateTimeStamp());
            callableStatement.setString(7,activityPojo.getAssignedById());
        //    callableStatement.setString(9,activityPojo.getContactsHidden());
          //  callableStatement.setString(8,activityPojo.getDescription());
            callableStatement.setString(8,activityPojo.getComments());
            callableStatement.setTimestamp(9,activityPojo.getCreatedDate());
            callableStatement.setString(10,activityPojo.getCreatedById());
            callableStatement.setTimestamp(11,activityPojo.getModifiedDate());
            callableStatement.setString(12,activityPojo.getModifiedById());
            callableStatement.setInt(13,activityPojo.getId());            
            callableStatement.setInt(14, activityPojo.getOppurtunityId());
            callableStatement.setString(15,DateUtility.getInstance().convertStringToMySQLDate(activityPojo.getDueDate())); 
            if("Conference Call".equals(activityPojo.getActivityType())){
                callableStatement.setString(16, activityPojo.getPracticeName());
             callableStatement.setString(17, activityPojo.getContactType());
            }else {
                 callableStatement.setString(16, "");
             callableStatement.setString(17, "");
            }
             
 callableStatement.registerOutParameter(18, Types.INTEGER);
 
            callableStatement.executeUpdate();      
             
            activityPojo.setId(callableStatement.getInt(18));
             isInserted = true;
            
           /* DataSourceDataProvider dataSourceDataProvider = DataSourceDataProvider.getInstance();
            int count = dataSourceDataProvider.touchAccount(activityPojo.getAccountId());
            
            int ActivityId = dataSourceDataProvider.getMaxCrmActivityId();
           // System.err.print("activities rows count:"+count);    
            if(count == 1){
              
                isInserted = true;
            }else{
                isInserted = false;
            }*/
            
        } catch(Exception e) {
            isInserted = false;
            throw new ServiceLocatorException(e);
        }finally {
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;
    }


    
    /**
     * The updateActivity(ActivityAction activityPojo) is used for updating activity.
     * @throws ServiceLocatorException.
     * @return boolean variable.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@link com.mss.mirage.crm.activities.ActivityAction}
     */
     public boolean updateActivity(ActivityAction activityPojo) throws ServiceLocatorException {
        
        boolean isUpdated = false;
        Connection connection = null;
        CallableStatement callableStatement = null;
        
       // PreparedStatement preStmt=null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
          //   System.out.println("Campaign Id123-->"+activityPojo.getCampaignId());
            callableStatement = connection.prepareCall("{call spActivity(?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1,activityPojo.getAccountId());
            callableStatement.setString(2,activityPojo.getContactsHidden());
            callableStatement.setInt(3,activityPojo.getCampaignId());
            callableStatement.setString(4,activityPojo.getActivityType());
            callableStatement.setString(5,activityPojo.getStatus());
            callableStatement.setString(6,activityPojo.getPriority());
         //   callableStatement.setBoolean(7,activityPojo.getAlarm());
           // callableStatement.setTimestamp(8,activityPojo.getDueDateTimeStamp());
            callableStatement.setString(7,activityPojo.getAssignedById());
           // callableStatement.setString(10,activityPojo.getAssignedToId());
            //callableStatement.setString(8,activityPojo.getDescription());
            callableStatement.setString(8,activityPojo.getComments());
            callableStatement.setTimestamp(9,activityPojo.getCreatedDate());
            callableStatement.setString(10,activityPojo.getCreatedById());
            callableStatement.setTimestamp(11,activityPojo.getModifiedDate());
            callableStatement.setString(12,activityPojo.getModifiedById());
            callableStatement.setInt(13,activityPojo.getId());
            callableStatement.setInt(14, activityPojo.getOppurtunityId());
            if(!"".equalsIgnoreCase(activityPojo.getDueDate()))
            {
                callableStatement.setString(15,DateUtility.getInstance().convertStringToMySQLDate(activityPojo.getDueDate()));   
            }
             else
            {               
                callableStatement.setString(15,DateUtility.getInstance().convertStringToMySQLDate(DateUtility.getInstance().getCurrentMySqlDate()));   
            }
           if("Conference Call".equals(activityPojo.getActivityType())){
                callableStatement.setString(16, activityPojo.getPracticeName());
             callableStatement.setString(17, activityPojo.getContactType());
            }else {
                 callableStatement.setString(16, "");
             callableStatement.setString(17, "");
            }
           callableStatement.registerOutParameter(18, Types.INTEGER);
            callableStatement.executeUpdate();
             activityPojo.setId(callableStatement.getInt(18));
             isUpdated = true; 
          /*  DataSourceDataProvider dataSourceDataProvider =  DataSourceDataProvider.getInstance();
            //Number of Touched Accounts.
            
            int count = dataSourceDataProvider.touchAccount(activityPojo.getAccountId());
           //CreatedBy = '"+activityPojo.getCreatedById()+"' ,
            if(count == 1){                          
            isUpdated = true;  
            }*/
            
        }catch(Exception e) {
            isUpdated = false;
            throw new ServiceLocatorException(e);
        }finally {
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return isUpdated;
    }

    
    /**
     * The getActivity(int activityId) is used for retrieving activity.
     * @throws ServiceLocatorException.
     * @return ActivityVTO variable.
     * {@link com.mss.mirage.util.ServiceLocatorException}
     * {@link com.mss.mirage.crm.activities.ActivityVTO}
     */
    public ActivityVTO getActivity(int activityId) throws ServiceLocatorException {
        
        ActivityVTO activityVTO = new ActivityVTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
         List<String> list = new ArrayList<String>();
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM tblCrmActivity WHERE Id=?");
            preparedStatement.setInt(1,activityId);
            resultSet = preparedStatement.executeQuery();
            activityVTO.setId(activityId);
            while(resultSet.next()){
                activityVTO.setAccountId(resultSet.getInt("AccountId"));
                activityVTO.setActivityType(resultSet.getString("ActivityType"));
               // activityVTO.setContactId(resultSet.getInt("ContactId"));
               // System.out.println("Before...");
                 StringTokenizer str = new StringTokenizer(resultSet.getString("ContactId"), ",");
                while (str.hasMoreTokens()) {
                    list.add(str.nextToken());
                }
               // System.out.println("Before11...");
                activityVTO.setContactsIdList(list);
               // System.out.println("Before.cwdcc..");
                activityVTO.setCampaignId(resultSet.getInt("CampaignId"));
                activityVTO.setAssignedToId(resultSet.getString("AssignedToId"));
                activityVTO.setAssignedById(resultSet.getString("AssignedById"));
                activityVTO.setPriority(resultSet.getString("Priority"));
                activityVTO.setDueDate(DateUtility.getInstance().sqlTimeStampTousTimeStamp(resultSet.getString("DateDue")));
                activityVTO.setStatus(resultSet.getString("Status"));
                activityVTO.setAlarm(resultSet.getBoolean("Alarm"));
                //activityVTO.setDescription(resultSet.getString("Description"));
               // System.out.println("eqdwqdw.cwdcc..");
                if(resultSet.getString("Description")!=null &&!"".equalsIgnoreCase(resultSet.getString("Description").trim()))
                activityVTO.setDescription(resultSet.getString("Description"));
               else
                     activityVTO.setDescription("");
                 // System.out.println("commmm..");
                activityVTO.setComments(resultSet.getString("Comments"));
                activityVTO.setCreatedById(resultSet.getString("CreatedById"));
                activityVTO.setCreatedDate(resultSet.getTimestamp("CreatedDate"));
                activityVTO.setModifiedById(resultSet.getString("ModifiedById"));
                activityVTO.setModifiedDate(resultSet.getTimestamp("ModifiedDate"));
                activityVTO.setOppurtunityId(resultSet.getInt("OpportunityId"));
                activityVTO.setDueDate(DateUtility.getInstance().convertToviewFormat(resultSet.getString("DateDue")));
               // System.out.println("Afterrgbkjhlj..");
                if("Conference Call".equals(resultSet.getString("ActivityType"))){
                activityVTO.setContactType(resultSet.getString("ContactType"));
                activityVTO.setPracticeName(resultSet.getString("Pratice"));
            }else {
                 activityVTO.setContactType("");
                  activityVTO.setPracticeName("");
            }
                
                
            }
            
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return activityVTO;
    }

    
    public boolean getMailStatus(int contId) throws ServiceLocatorException {
       boolean mailStatus = false;  
       Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
         try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("select DontSendEmail  from tblCrmContact where Id=?");
            preparedStatement.setInt(1,contId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
              mailStatus=resultSet.getBoolean("DontSendEmail");  
            }
         }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
       return mailStatus;
    }
  
}
