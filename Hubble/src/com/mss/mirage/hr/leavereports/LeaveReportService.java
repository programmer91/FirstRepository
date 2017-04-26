/*
 * HRActionService.java
 *
 * Created on October 15, 2008, 8:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.hr.leavereports;

import com.mss.mirage.util.ServiceLocatorException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *
 * @author miracle
 */
public interface LeaveReportService {
    
    public String generateLeaveStatusReport(LeaveReportAction leaveReportAction,String empName,String query,HttpServletResponse httpServletResponse) throws ServiceLocatorException;
     public String generateBirthdayReport(LeaveReportAction leaveReportAction,String empName,String query,HttpServletResponse httpServletResponse) throws ServiceLocatorException;
    public void generateAvaliableLeaveReport(String empId,String empName, String sdate, String edate,HttpServletResponse httpServletResponse) throws ServiceLocatorException;
    public String getTimeSheetPDFReport(String empName,String query,String timeSheetWeekStartDate,String timeSheetWeekEndDate,HttpServletResponse httpServletResponse) throws ServiceLocatorException;
}
