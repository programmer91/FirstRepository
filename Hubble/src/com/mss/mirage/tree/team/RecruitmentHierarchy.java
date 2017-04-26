/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.tree.team
 *
 * Date    : March 18, 2008, 6:44 PM
 *
 * Author  : Chandra Sekhar Bodireddy <cbodireddy@miraclesoft.com>
 *
 * File    : RecruitmentHierarchy .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.tree.team;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Chandra Sekhar Bodireddy <cbodireddy@miraclesoft.com>
 */
public class RecruitmentHierarchy {
    
    private static Map<String, RecruitmentHierarchy> teamMap = new HashMap<String, RecruitmentHierarchy>();
    private static Boolean populateFlag = false;
    private String userId;
    private String userName;
    private List<RecruitmentHierarchy> children;
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
    
    /** Creates a new instance of RecruitmentHierarchy */
    public RecruitmentHierarchy(String userId,String userName,RecruitmentHierarchy... children) {
        //  Check if the UserId already exists in the Map
        this.userId = userId;
        this.userName = userName;
        
        this.children = new ArrayList<RecruitmentHierarchy>();
        for(RecruitmentHierarchy child : children){
            this.children.add(child);
        }
        teamMap.put(userId,this);
    }
    
    // For Retreiving User From TeamMap for given UserId
    public static RecruitmentHierarchy getByUserId(String userId){
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
    
    public List<RecruitmentHierarchy> getChildren() {
        return children;
    }
    
    public void setChildren(List<RecruitmentHierarchy> children) {
        this.children = children;
    }
    
    public boolean isToggle() {
        return toggle;
    }
    
    public void toggle() {
        this.toggle = !toggle;
    }
    
    public static void add(String reportsTo, RecruitmentHierarchy child) {
        // check if the Reportsto Key already exists
        
        RecruitmentHierarchy temp = teamMap.get(reportsTo);
        if (temp == null) {
            
            //Initial Invocation of TeamHierarchy Constructor
            RecruitmentHierarchy temp1 = new RecruitmentHierarchy(reportsTo,RecruitmentHierarchy.getUserFullName());
            temp = teamMap.get(reportsTo);
        }
        
        // If it exists then add this as a Child of that Node
        RecruitmentHierarchy temp2 = getByUserId(reportsTo);
        temp2.children.add(child);
    }
    
    /**
     *
     *@ throws ServiceLocatorException
     */
    public static void getMyTeamHierarchyOfRecruitment(String reportsTo) throws ServiceLocatorException {
        
        Connection  connection = ConnectionProvider.getInstance().getConnection();
        
        try {
            String queryString = "SELECT LoginId,FName,MName,LName,IsManager,IsTeamLead FROM tblEmployee WHERE DepartmentId='Recruiting' and CurStatus='Active' and ReportsTo= ?   ORDER BY FName";
            java.sql.PreparedStatement theStatement = connection.prepareStatement(queryString);
            
            if(!populateFlag) {
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
                
                RecruitmentHierarchy child = new RecruitmentHierarchy(key,value);
                
                //adding Child To the CurrentNode
                RecruitmentHierarchy.add(reportsTo, child);
                
                // If the Team Member is a Manager then Get his Team Members List
                if((resultSet.getBoolean("IsManager")) || (resultSet.getBoolean("IsTeamLead"))){
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
            
        }
    } //closing the method
    
    public static String getUserFullName() {
        return RecruitmentHierarchy.userFullName;
    }
    
    public static void setUserFullName(String userFullName) {
        RecruitmentHierarchy.userFullName = userFullName;
    }
    
}