/*
 * BridgeConferenceImpl.java
 *
 * Created on April 3, 2008, 7:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.bridgeconference;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.*;
/**
 *
 * @author miracle
 */
public class BridgeConferenceServiceImpl implements  BridgeConferenceService {
    
    /** Creates a new instance of BridgeConferenceImpl */
    public BridgeConferenceServiceImpl() {
    }
    
    public boolean addBridgeSchedule(BridgeConferenceAction conferenceAction) throws ServiceLocatorException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        
        boolean isInserted=false;
        try{
            
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement=connection.prepareStatement("insert into tblbridgeshedule (contactno,"+
                    " bridgenumber ,engagedby ,date ,starttime," +
                    "endtime ,title,listofemailadd ," +
                    "status   ) values(?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,conferenceAction.getContactNo());
            preparedStatement.setString(2,conferenceAction.getBridgeNumber());
            preparedStatement.setString(3,conferenceAction.getEngagedBy());
            preparedStatement.setDate(4, (Date) conferenceAction.getDate());
            preparedStatement.setTimestamp(5,DateUtility.getInstance().strToTimeStampObj(conferenceAction.getStartTime()));
            preparedStatement.setTimestamp(6,DateUtility.getInstance().strToTimeStampObj(conferenceAction.getEndTime()));
            preparedStatement.setString(7,conferenceAction.getTitle());
            preparedStatement.setString(8,conferenceAction.getListOfEmailAdd());
            preparedStatement.setString(9,conferenceAction.getStatus());
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            if(x>0){
                isInserted=true;
            }
            
        }catch(SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
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
    
    public BridgeConferenceVTO getBridge(int bridgeId) throws ServiceLocatorException {
        
        BridgeConferenceVTO bridgeConferenceVTO=new BridgeConferenceVTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        
        try {
            
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("select contactno,bridgenumber,engagedby,date,starttime,endtime,title,listofemailadd,status from tblbridgeshedule where bridgeid='"+bridgeId+"'");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                
                bridgeConferenceVTO.setContactNo(resultSet.getString(1));
                bridgeConferenceVTO.setBridgeNumber(resultSet.getString(2));
                bridgeConferenceVTO.setEngagedBy(resultSet.getString(3));
                bridgeConferenceVTO.setDate(resultSet.getDate(4));
                bridgeConferenceVTO.setStartTime(resultSet.getString(5));
                bridgeConferenceVTO.setEndTime(resultSet.getString(6));
                bridgeConferenceVTO.setTitle(resultSet.getString(7));
                bridgeConferenceVTO.setListOfEmailAdd(resultSet.getString(8));
                bridgeConferenceVTO.setStatus(resultSet.getString(9));
                
            }
            
        } catch(SQLException sqle){
            throw new ServiceLocatorException(sqle);
        } finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                
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
        return bridgeConferenceVTO;
    }
    
    public boolean editBridgeSchedule(BridgeConferenceAction conferenceAction, int bridgeId) throws ServiceLocatorException {
        BridgeConferenceVTO bridgeConferenceVTO=new BridgeConferenceVTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        boolean isUpdated=false;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement=connection.prepareStatement("update tblbridgeshedule set contactno=?,bridgenumber=?,engagedby=?," +
                    "date=?,starttime=?,endtime=?,title=?,listofemailadd=?,status=? where bridgeid='"+bridgeId+"'" );
            
            preparedStatement.setString(1,conferenceAction.getContactNo());
            preparedStatement.setString(2,conferenceAction.getBridgeNumber());
            preparedStatement.setString(3,conferenceAction.getEngagedBy());
            preparedStatement.setDate(4,conferenceAction.getDate());
            preparedStatement.setTimestamp(5,DateUtility.getInstance().strToTimeStampObj(conferenceAction.getStartTime()));
            preparedStatement.setTimestamp(6,DateUtility.getInstance().strToTimeStampObj(conferenceAction.getEndTime()));
            preparedStatement.setString(7,conferenceAction.getTitle());
            preparedStatement.setString(8,conferenceAction.getListOfEmailAdd());
            preparedStatement.setString(9,conferenceAction.getStatus());
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
           
            if(x>0){
                isUpdated=true;
            }
            
        }catch(SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally{
            try{
                
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                
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
    
    public boolean delete(int bridgeid1) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        boolean isDelete=false;
        int bridgeId = bridgeid1;
        try {
            
            connection=ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("delete from tblbridgeshedule where bridgeid="+bridgeId);
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            
            if(x>0) {
                isDelete = true;
            }
            
        }catch(SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally {
            try {
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
        return isDelete;
    }
    
}



