/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   August 4, 2008, 3:24 PM
 *
 * Author  :  Rajasekhar Yenduva <ryenduva@miraclesoft.com>
 *
 * File    : CalendarHandlerServiceImpl .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.calendar;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Rajasekhar Yenduva <ryenduva@miraclesoft.com>
 */
public class CalendarHandlerServiceImpl implements CalendarHandlerService {
    
    /** Creates a new instance of CalendarHandlerServiceImpl */
    public CalendarHandlerServiceImpl() {
    }
    
    
    public Map getAllSalesTeamExceptAccountTeam(String empId) throws ServiceLocatorException {
        Map salesTeamExceptAccountTeamMap = new TreeMap();
        salesTeamExceptAccountTeamMap.clear();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            //System.out.println("Entered the GetAllSales Team MAp");
            //select EmpId,AccessType from tblCrmCalAccessList where otherEmpId
            preparedStatement = connection.prepareStatement("select EmpId from tblCrmCalAccessList where otherEmpId='"+empId+"'");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
//                if(!(accountTeam.containsKey(resultSet.getString("LoginId")))){
                salesTeamExceptAccountTeamMap.put(resultSet.getString("EmpId"),DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(resultSet.getString("EmpId"))));
//                }
            }
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            }catch (SQLException ex) {
                ex.printStackTrace();
                throw new ServiceLocatorException(ex);
            }
        }
        
        return salesTeamExceptAccountTeamMap;
    }
    
    public Map getTeamCalendar(String empId) throws ServiceLocatorException {
        ArrayList calMemberList=new ArrayList();
        Map TeamCalendar=new TreeMap();
        TeamCalendar.clear();
        HashMap teamCalendarMap=new HashMap();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int num2=0;
       connection = ConnectionProvider.getInstance().getConnection();
        try {
            teamCalendarMap=DataSourceDataProvider.getInstance().getTeamcalendar(empId);
           
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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
            }catch (SQLException ex) {
                ex.printStackTrace();
                throw new ServiceLocatorException(ex);
            }
        }
       return  teamCalendarMap;
    }
    
}
