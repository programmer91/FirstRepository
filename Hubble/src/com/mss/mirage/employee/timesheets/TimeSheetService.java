/*******************************************************************************
 * /*
 *
 *Project: MirajeV2
 *
 *$Author: rdadi $
 *
 *$Date: 2011-03-01 09:47:21 $
 *
 *$Revision: 1.2 $
 *
 *$Source: /Hubble/Hubble/src/java/com/mss/mirage/employee/timesheets/TimeSheetService.java,v $
 *
 * @(#)TimeSheetService.java	September 24, 2007, 12:13 AM
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.mss.mirage.employee.timesheets;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import com.mss.mirage.util.ServiceLocatorException;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> This interface describe the TimeSheet Business logic methods
 *  The implementation class must provide Behavior of this methods. <p>
 *
 * @version 2.0, September 24, 2007.
 *
 * @author  RangaRao Panda<rpanda@miraclesoft.com>
 *
 * @See com.mss.mirage.employee.timesheets.TimeSheetServiceImpl
 */
public interface TimeSheetService {
    
    /**  This method can be used to storing list data to TimeSheetVTO bean class.
     *
     * @param li A java.util.List
     *
     * @return TimeSheetVTO.
     */
    public TimeSheetVTO getWeekDaysBean(List li);
    
    /**  This method can be used to generating the starting , ending and weekdays of given java.util.Date.
     *
     * @param cal A java.util.Calendar.
     *
     * @return java.util.List.
     */
    public List getweekStartAndEndDays(Calendar cal);
    
    /**  This method can be used to seacrh the TimeSheet based on the employee Id and TimeSheet ID
     *  return the TimeSheetVTO object.
     *
     * @param empId A Int.
     * @param timeSheetID A Int.
     *
     * @return TimeSheetVTO.
     */
    public TimeSheetVTO getTimeSheet(int empId, int timeSheetID,String empType,String resourceType);
    
    /**
     *  This method can be used for adding the new TimeSheet.
     * @return boolean.
     * @param action 
     * @throws com.mss.mirage.util.ServiceLocatorException 
     */
    public int addTimeSheet(TimeSheetAction action) throws ServiceLocatorException;
    
    /**  This method can be used for editing the TimeSheet.
     *
     * @param action A TimeSheetAction reference.
     *
     * @return boolean.
     *
     * @throws ServiceLocatorException.
     */
    public boolean editTimeSheet(TimeSheetAction action) throws ServiceLocatorException;
    
    /**
     *  This method can be used for checking timesheet exists based on the empId and
     * starting ,ending dates of the Timesheet.
     * 
     * 
     * @return boolean.
     * @param empID 
     * @param li A java.util.List
     */
    public String checkTimeSheetExists(List li,String empID,String empType);
    
    /**
     * 
     * This method can be used for Delete the timesheet
     * @param id 
     * @param empId 
     * @param timeSheetId 
     * @return int
     * @throws com.mss.mirage.util.ServiceLocatorException 
     */
    public int deleteTimeSheet(int id, int empId, int timeSheetId) throws ServiceLocatorException;
    
    /**
     * 
     *  This method can be used for upload the timesheet
     * @param timeSheetAction 
     * @return int
     * @throws com.mss.mirage.util.ServiceLocatorException 
     * @throws java.sql.SQLException 
     */
    public int uploadTimeSheet(TimeSheetAction timeSheetAction) throws ServiceLocatorException, SQLException;
    
    /**
     * 
     *  This method can be used for delete the Onsite Timesheet
     * @param id 
     * @return int
     * @throws com.mss.mirage.util.ServiceLocatorException 
     */
    public int deleteOnsiteTimeSheet(int id) throws ServiceLocatorException;
    public String getTeamMembersList(String loginId) throws ServiceLocatorException; 
    public String generateEmpTimeSheetsReport(TimeSheetAction timeSheetAction,String empName,String query,HttpServletResponse httpServletResponse) throws ServiceLocatorException;
    /*Report for Customer Timesheet
     * Date : 03/18/2014
     * Author : Santosh Kola
     */
   // public String generateCustTimeSheetsReport(TimeSheetAction timeSheetAction,String empName,String query,HttpServletResponse httpServletResponse,String reportsToName) throws ServiceLocatorException;
   // public List getCustTimesheetsStatusData(String query,String status,String reportsTo) throws ServiceLocatorException;
}
