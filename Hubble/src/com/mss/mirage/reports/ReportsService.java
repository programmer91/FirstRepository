 /*
  * ReportsService.java
  *
  * Created on November 22, 2007, 8:35 PM
  *
  * To change this template, choose Tools | Template Manager
  * and open the template in the editor.
  */

package com.mss.mirage.reports;

import com.mss.mirage.util.ServiceLocatorException;

/**
 *
 * @author miracle
 */
public interface ReportsService {
    
    public boolean generateReportToPdfFile(String sourceJrxml,String destDirectiory) throws ServiceLocatorException;
    public boolean generateReportToHtmlFile(String sourceJrxml, String destDirectiory) throws ServiceLocatorException;
    public boolean generateReportToXmlFile(String sourceJrxml, String destDirectiory) throws ServiceLocatorException;
    public boolean addEmpReportToDatabase(EmployeeAvailabilityAction employeeAvailabilityAction) throws ServiceLocatorException;
    public boolean deleteReport(EmployeeAvailabilityAction employeeAvailabilityAction) throws ServiceLocatorException;
    public String getReportName(int reportId) throws ServiceLocatorException;
    public String generatePFPortalReport(int contactId,String docType) throws ServiceLocatorException;
}

