 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.payroll;

import com.mss.mirage.util.ServiceLocatorException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface PayrollHandlerService {
  //   public boolean addDueSettlement(PayrollHandlerAction payrollHandlerAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
     public String addEmployeePayroll(int empId,PayrollHandlerAction payrollHandlerAction) throws ServiceLocatorException;
     public String setEmployeePayrollEditDetails(int payrollId,PayrollHandlerAction payrollHandlerAction) throws ServiceLocatorException;
     public int insertIntoWages(PayrollHandlerAction payrollHandlerAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
     public String setEmployeeWageDetailsForUpdate(int payrollId,PayrollHandlerAction payrollHandlerAction) throws ServiceLocatorException;
  //   public boolean addDueSettlement(PayrollHandlerAction payrollHandlerAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
     
      public int payrollAuthenticationRegister(int empId,String password) throws ServiceLocatorException;
      public int payrollAuthenticationLogin(int empId,String password) throws ServiceLocatorException;
      public String payrollAuthenticationCanel(int empId) throws ServiceLocatorException;
      public int updatePayrollPassword(int empId,String oldPassword,String newPassword) throws ServiceLocatorException;
      public String runEmpWagesForCurrentMonth(int year, int month, PayrollHandlerAction payrollHandlerAction) throws ServiceLocatorException;

}
