/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   February 18, 2008, 3:02 PM
 *
 * Author  :  Rajasekhar Yenduva <ryenduva@miraclesoft.com>
 *
 * File    : OperationsTeamHirarchy .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.tree.team;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
//import org.apache.poi.hssf.record.formula.functions.True;

/**
 *
 * @author miracle
 */
public class OperationsTeamHirarchy {
    
    private static Map<String, OperationsTeamHirarchy> teamMap = new HashMap<String, OperationsTeamHirarchy>();
    private static Boolean populateFlag = false;
    private String userId;
    private String userName;
    private List<OperationsTeamHirarchy> children;
    private boolean toggle;
    private static HttpServletRequest httpServletRequest;
    private static String userFullName;
    
    /* This is Structure of TreeView Component */
/* static {
        new TeamHierarchy("plokam", "Prasad Lokam",
            new TeamHierarchy("mlokam","Madhavi Lokam",new TeamHierarchy("sbarnala","Santosh Barnal"),
                                                       new TeamHierarchy("skodati","Sumera Kodati"),
                                                       new TeamHierarchy("schamana","Sai Chamana")),
            new TeamHierarchy("rsanku","Ram Sanku",new TeamHierarchy("stirumareddy","Santosh Tirumareddy"),
                                                   new TeamHierarchy("ralla","Ram Alla"),
                                                   new TeamHierarchy("vbajpe","Vick Bajpe")));
    }*/
    
    /** Creates a new instance of TeamHierarchy */
    public OperationsTeamHirarchy(String userId,String userName,OperationsTeamHirarchy... children) {
        //  Check if the UserId already exists in the Map
        this.userId = userId;
        this.userName = userName;
        
        this.children = new ArrayList<OperationsTeamHirarchy>();
        for(OperationsTeamHirarchy child : children){
            this.children.add(child);
        }
        teamMap.put(userId,this);
    }
    
    // For Retreiving User From TeamMap for given UserId
    public static OperationsTeamHirarchy getByUserId(String userId){
        return teamMap.get(userId);
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public List<OperationsTeamHirarchy> getChildren() {
        return children;
    }
    
    public void setChildren(List<OperationsTeamHirarchy> children) {
        this.children = children;
    }
    
    public boolean isToggle() {
        return toggle;
    }
    
    public void toggle() {
        this.toggle = !toggle;
    }
    
    public static void add(String reportsTo,OperationsTeamHirarchy child) {
        // check if the Reportsto Key already exists
        
        OperationsTeamHirarchy temp = teamMap.get(reportsTo);
        if (temp == null) {
            
            //Initial Invocation of TeamHierarchy Constructor
            OperationsTeamHirarchy temp1 = new OperationsTeamHirarchy(reportsTo,OperationsTeamHirarchy.getUserFullName());
            temp = teamMap.get(reportsTo);
        }
        
        // If it exists then add this as a Child of that Node
        OperationsTeamHirarchy temp2 = getByUserId(reportsTo);
        temp2.children.add(child);
    }
    
    /**
     *
     *@ throws ServiceLocatorException
     */
    public static void getMyTeamManagersHierarchyOfOperations(String reportsTo) throws ServiceLocatorException {
        
        Connection  connection = ConnectionProvider.getInstance().getConnection();
        
        try {
            String queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE  DepartmentId='Operations' and CurStatus='Active' and ReportsTo= ?  ORDER BY FName";
            java.sql.PreparedStatement theStatement = connection.prepareStatement(queryString);
            
            if(!populateFlag)
            {
                getMyTeamMembersUpToHierarchy(reportsTo,theStatement);
                populateFlag = true;
            }
            
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
        }finally {
            if(connection!=null)
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException sqlex) {
                    throw new ServiceLocatorException(sqlex);
                }
            
        }
    }
    
   
    
    public static void getMyTeamMembersUpToHierarchy(String reportsTo, java.sql.PreparedStatement theStatement)  throws ServiceLocatorException{
        
        String[] keys = new String[100];
        
        String key = null;
        String value = null;
        ResultSet resultSet = null;
        
        try {
            
            //  Pass the Reportsto Parameter to the Prepared Statement Query
            theStatement.setString(1,reportsTo);
            
            //  Execute the Prepared Statement to Extract the List of People reporting to
            //  The Specified Person.
            resultSet = theStatement.executeQuery();
            int keyCnt = 0;
            while(resultSet.next()) {
                key     = resultSet.getString("LoginId");
                value   = resultSet.getString("FName")+" "+resultSet.getString("MName")+"."+resultSet.getString("LName");
                
                OperationsTeamHirarchy child = new OperationsTeamHirarchy(key,value);
                
                //adding Child To the CurrentNode
                OperationsTeamHirarchy.add(reportsTo, child);
                
                // If the Team Member is a Manager then Get his Team Members List
                if((resultSet.getBoolean("IsManager")) || (resultSet.getBoolean("IsTeamLead"))) {
                    keys[keyCnt] = key;
                    keyCnt++;
                }
            }
            
            //Processing Recursive Invocation for Managers
            for(int i=0;i<keyCnt;i++) {
                key = keys[i];
                getMyTeamMembersUpToHierarchy(key,theStatement);
            }
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        } finally {
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
    } //closing the method

    public static String getUserFullName() {
        return OperationsTeamHirarchy.userFullName;
    }

    public static void setUserFullName(String userFullName) {
        OperationsTeamHirarchy.userFullName = userFullName;
    }
    
    
}

