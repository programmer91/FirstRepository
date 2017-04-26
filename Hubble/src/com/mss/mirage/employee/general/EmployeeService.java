/*
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.employee.genaral
 *
 * Date    :  September 24, 2007, 10:18 PM
 *
 * Author  : Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * File    : EmployeeService.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.employee.general;

import com.mss.mirage.employee.general.StateVTO;
import com.mss.mirage.util.ServiceLocatorException;
import java.util.Collection;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;

/*
 * @(#)EmployeeService.java 1.0 November 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */
public interface EmployeeService {
    
    /**
     * the upadateEmployee(EmployeeAction employeeAction) is used for updating employee information.
     * @throws ServiceLocatorException
     * @see com.mss.mirage.util.ServiceLocatorException
     * @see com.mss.mirage.employee.general.EmployeeAction
     *
     * @return boolean variable
     */
    public boolean updateEmployee(EmployeeAction employeeAction) throws ServiceLocatorException;
    
    /**
     * The getEmployeeVTO(EmployeeAction employeePojo) is used for retrieving employeeVTO Object.
     * @throws ServiceLocatorException
     * @see com.mss.mirage.util.ServiceLocatorException
     * @see com.mss.mirage.employee.general.EmployeeVTO
     * @return EmployeeVTO variable
     */
    public EmployeeVTO getEmployeeVTO(EmployeeAction employeePojo) throws ServiceLocatorException;
    
    /**
     * The EmployeeVTO getEmployee(int employeeId) is used for retrieving employee Details.
     * @throws ServiceLocatorException
     * @see com.mss.mirage.util.ServiceLocatorException
     * @see com.mss.mirage.employee.general.EmployeeVTO
     * @return EmployeeVTO variable
     */
    public EmployeeVTO getEmployee(int employeeId,int currId) throws ServiceLocatorException;
    
    /**
     * The deleteEmployee(int employeeId) is used for deleting employee Details.
     * @throws ServiceLocatorException
     * @see com.mss.mirage.util.ServiceLocatorException
     *
     * @return int variable
     */
    public int deleteEmployee(int employeeId) throws ServiceLocatorException;
    
    /**
     * The sendMail(int employeeId) is used for sending e-mail to specified employee.
     * @throws ServiceLocatorException
     * @see com.mss.mirage.util.ServiceLocatorException
     *
     * @return boolean variable
     */
    public boolean sendMail(int employeeId) throws ServiceLocatorException;
    
    /**
     * The insetDefaultRoles(int employeeId) is used for inserting default roles to
     * the employee depending up on his department in tblEmpRoles table.
     * @throws ServiceLocatorException
     * @see com.mss.mirage.util.ServiceLocatorException
     *
     * @return int inserted rows
     */
    public int insertDefaultRoles(int employeeId,String departmentName) throws ServiceLocatorException;
    
    public boolean insertStateHistory(EmployeeAction employeePojo) throws ServiceLocatorException;
    public int updateStateHistory(EmployeeAction employeePojo,int recordId) throws ServiceLocatorException;
    public int getRecentStateHistoryId(String loginId) throws ServiceLocatorException;
    
    public Collection getStateHistoryCollection(String loginId,int noOfRecords) throws ServiceLocatorException;
    
    public StateVTO getStateVTO(EmployeeAction employeePojo) throws ServiceLocatorException;
    
    public boolean updateEmployeeState(StateVTO stateVTO) throws ServiceLocatorException;

    public int deleteEmpStatus(int empId, int currId) throws ServiceLocatorException;
    public String generateEmployeeList(String loginId);
    public String getEmployeeResumeLocation(int fileId) throws ServiceLocatorException ;
     public int isExistedAppraisal(int empId,int year,String Quarterly) throws ServiceLocatorException ;
     public String getQuarterAppraisalDetails(int managerFlag,int empId) throws ServiceLocatorException ;
    public String quarterlyAppraisalEdit(int empId,int appraisalId,int lineId) throws ServiceLocatorException ;
    public String empQuarterlyAppraisalSave(HttpServletRequest httpServletRequest,int rowcount,String curretRole,int appraisalId,String status,int empId,int lineId,String shortTeamGoal,String shortTeamGoalComments,String longTeamGoal,String  longTeamGoalComments,String strength,String strengthsComments,String improvements,String  improvementsComments,String quarterly,String rejectedComments) throws ServiceLocatorException ;
    
}
