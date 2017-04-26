/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : Templates.Classes
 *
 * Date    :  December 26, 2007, 11:41 AM
 *
 * Author  : MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * File    : TeamSelectAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.tree.team;

import com.opensymphony.xwork2.ActionSupport;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
/**
 *
 * @author MrutyumjayaRao Chennu
 */
public class TeamSelectAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
     private String resultType;
    private String nodeId;
    private String name;
    private String id;
    private String departmentId;
    private String workingCountry;
    private String phoneNo;
    private String email;
    private int opsContactId;
    private int empNo;
    private String opsContactName;
    private TeamHierarchy currentTeamHierarchy;
    private String practiceName;
    private String subPraticename;
    private String currentStatus;
    private String startDate;
    /** Creates a new instance of TeamSelectAction */
    public TeamSelectAction() {
    }
    
public String execute() throws Exception {
         Connection connection = null;
        ResultSet resultSet = null;
        Statement preparedStatement = null;
        
        resultType = LOGIN;
        String queryString = "";
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
           
               // queryString = "SELECT LoginId,DepartmentId,WorkingCountry,CellPhoneNo,Email1 FROM tblEmployee WHERE LoginId ='"+getId()+"'";
                //queryString = "SELECT e1.LoginId,e1.DepartmentId AS DepartmentId,e1.WorkingCountry AS WorkingCountry,e1.CellPhoneNo AS CellPhoneNo,e1.Email1 AS Email1,CONCAT(TRIM(e2.FName),'.',TRIM(e2.LName)) AS OpsContactId FROM tblEmployee AS e1 INNER JOIN  tblEmployee AS e2 ON (e2.Id=e1.OpsContactId) WHERE e1.LoginId ='"+getId()+"'";
                queryString = "SELECT Id,EmpNo,LoginId,DepartmentId AS DepartmentId,WorkingCountry AS WorkingCountry,CellPhoneNo AS CellPhoneNo,Email1 AS Email1,OpsContactId,PracticeId,SubPractice FROM tblEmployee WHERE LoginId ='"+getId()+"'";
                
                // System.out.println("query string is----->"+queryString);
                try {
                    
                     connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.createStatement();
            resultSet = preparedStatement.executeQuery(queryString);
                    int empId=0;
              if(resultSet.next()) {
                 
                  empNo =  resultSet.getInt("EmpNo");
               departmentId = resultSet.getString("DepartmentId");
               workingCountry = resultSet.getString("WorkingCountry");
               phoneNo = resultSet.getString("CellPhoneNo");
               email = resultSet.getString("Email1");
               opsContactId=resultSet.getInt("opsContactId");
               practiceName=resultSet.getString("PracticeId");
               subPraticename=resultSet.getString("SubPractice");
               
              // startDate=resultSet.getString("StartDate");
               empId = resultSet.getInt("Id");
                setOpsContactName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(opsContactId));
                //setCurrentStatus(DataSourceDataProvider.getInstance().getCurrentStatusByMaxStartDate(resultSet.getInt("Id")));
              }
              resultSet.close();
              String TABLE_EMP_STATE_HISTORY = Properties.getProperty("TABLE_EMP_STATE_HISTORY");
              queryString = "SELECT StartDate,State FROM   "+TABLE_EMP_STATE_HISTORY+" WHERE EmpId="+empId+" ORDER BY StartDate DESC LIMIT 1";
                resultSet = preparedStatement.executeQuery(queryString);
                if(resultSet.next()) {
                     startDate=resultSet.getString("StartDate");
                     setCurrentStatus(resultSet.getString("State"));
                }
              
              
                    
        currentTeamHierarchy = TeamHierarchy.getByUserId(getNodeId());
        
    }catch (SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        
           
        } 
}
        return SUCCESS;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getNodeName(){
        return currentTeamHierarchy.getUserName();
    }
    
    public String getNodeId() {
        return nodeId;
    }
    
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getWorkingCountry() {
        return workingCountry;
    }

    public void setWorkingCountry(String workingCountry) {
        this.workingCountry = workingCountry;
    }
    
    
    @Override
 
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }
@Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the opsContactId
     */
    public int getOpsContactId() {
        return opsContactId;
    }

    /**
     * @param opsContactId the opsContactId to set
     */
    public void setOpsContactId(int opsContactId) {
        this.opsContactId = opsContactId;
    }

    /**
     * @return the opsContactName
     */
    public String getOpsContactName() {
        return opsContactName;
    }

    /**
     * @param opsContactName the opsContactName to set
     */
    public void setOpsContactName(String opsContactName) {
        this.opsContactName = opsContactName;
    }

    /**
     * @return the empNo
     */
    public int getEmpNo() {
        return empNo;
    }

    /**
     * @param empNo the empNo to set
     */
    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    /**
     * @return the practiceName
     */
    public String getPracticeName() {
        return practiceName;
    }

    /**
     * @param practiceName the practiceName to set
     */
    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    /**
     * @return the subPraticename
     */
    public String getSubPraticename() {
        return subPraticename;
    }

    /**
     * @param subPraticename the subPraticename to set
     */
    public void setSubPraticename(String subPraticename) {
        this.subPraticename = subPraticename;
    }

    /**
     * @return the currentStatus
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * @param currentStatus the currentStatus to set
     */
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

}

