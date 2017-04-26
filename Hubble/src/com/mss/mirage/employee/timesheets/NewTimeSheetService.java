/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.timesheets;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import com.mss.mirage.util.ServiceLocatorException;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author miracle
 */
public interface NewTimeSheetService {
    public List getweekStartAndEndDays(Calendar cal);
    public String checkTimeSheetExists(List li,String empID,String empType);
    public int deleteTimeSheet(int id, int empId, int timeSheetId) throws ServiceLocatorException;
    public NewTimeSheetVTO getWeekDaysBean(List li);
    public int newAddTimeSheet(NewTimeSheetAction action) throws ServiceLocatorException;
    public NewTimeSheetVTO newGetTimeSheet(int empId, int timeSheetID,String empType,String resourceType);
    public boolean newEditTimeSheet(NewTimeSheetAction action) throws ServiceLocatorException;
   // public String getSubmitTimeSheetEmailLogBody(String empName,String wstDate,String wenDate,String empId,String reportsToType,int timeSheetID) throws ServiceLocatorException;
   //  public String getSubmitTimeSheetEmailLogBody(String empName,String wstDate,String wenDate,String empId,String reportsToType,int timeSheetID,String resourceType,String description) throws ServiceLocatorException;
    public String getApproveTimeSheetEmailLogBody(String empName,String wstDate,String wenDate) throws ServiceLocatorException;
    public String getRejectTimeSheetEmailLogBody(String empName,String wstDate,String wenDate) throws ServiceLocatorException;
    public String getEnteredTimeSheetEmailLogBody(String empName,String wstDate,String wenDate) throws ServiceLocatorException;
    // Dual changes
    public String getTeamMembersList(String LoginId) throws ServiceLocatorException;
    // public String getSubmitTimeSheetEmailLogBody(String empName,String wstDate,String wenDate,String empId,String reportsToType,int timeSheetID,String resourceType,String description,String secDescription) throws ServiceLocatorException;
    public String getSubmitTimeSheetEmailLogBody(String empName,String wstDate,String wenDate,String empId,String reportsToType,int timeSheetID,String resourceType) throws ServiceLocatorException;
public void getBiometricHours(NewTimeSheetAction newTimeSheetAction) throws ServiceLocatorException;
     
}
