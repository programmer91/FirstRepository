/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.ajax;

import com.mss.mirage.util.ServiceLocatorException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface PayrollAjaxHandlerService {

    public String addNoDuesSettlement(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String getNoDueDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String getEmployeesByDeptPayroll(String deptName, String status, String roleName, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String approveOrRejectNoDues(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String addRemainderValuesNoDues(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String showEmpListNoDuesRemainder(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String checkForEmpNoDueRecordExistsOrNot(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String noDuesRemainderClose(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String getEmpNumAndStatus(String email, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String setPayRollDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String setPayRollEmployeeDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String setEmployeePayRollDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String setInsuranceSavingsPayrollDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String setOtherPayrollDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String setPayrollContactDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String doAddPayRollDetails(String empId, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String getEmployeesByDept(String deptName) throws ServiceLocatorException;

    public String runEmpWagesForCurrentMonth(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String doFreezeWages(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String doUpdateYearAndDate(String payRollId, int wageId, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String freezeEmpWageDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String unfreezeEmpWageDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String getDaysCountDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String updateDaysCount(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public int insertIntoWages(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String setEmpWageDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String updateAllEmpWageDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String doBackUpPayRollDetails(String empId, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String getPayrollCheck(String year, int month, String difference , int OrgId) throws ServiceLocatorException;

    public String cleanPayroll(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String doUnFreezeWages(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String rerunEmpWagesForCurrentMonth(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String doUploadLeavesExcelSheet(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String getPayrollHistory(int empId) throws ServiceLocatorException;

    public String getWagesHistory(int empId) throws ServiceLocatorException;

    public String getPayRollHistoryEmployeeDetails(int empId, String modifiedDate) throws ServiceLocatorException;

    public String getWagesHistoryEmployeeDetails(int empId, String modifiedDate) throws ServiceLocatorException;

    public String getTEFDetails(int empId) throws ServiceLocatorException;

    public String getNamesByDesignation(int isManager, int isTeamLead, String departmentId) throws ServiceLocatorException;

    public String getEmpTefDetailsForOverlay(int tefId) throws ServiceLocatorException;

    public boolean addTaxAssumption(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public boolean upadteTaxExemption(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public boolean upadtePayrollTaxExemption(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public boolean checkCategoryWiseSavingsAmt(PayrollAjaxHandlerAction payrollAjaxHandlerAction, int category) throws ServiceLocatorException;

    public String getSingleEmpLockAmtDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String getAllEmpsLockAmtDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String generatePayRollPassword(int empId, String email) throws ServiceLocatorException;

    public String getRepaymentDetails(String year, int month) throws ServiceLocatorException;

    public String getRepaymentReason(int wagId) throws ServiceLocatorException;

    public String tdsCalculation(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;
    //public String getEmpLockAmtDetailsForBothtwelveandSixMonths(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException;

    public String getEmployeeNumberByLoginId(String loginId) throws ServiceLocatorException;

    public boolean addTaxAssumptionFromPayroll(PayrollAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getTaxAssumptionFromPayroll(int tefId) throws ServiceLocatorException;

    public boolean updateTaxAssumptionFromPayroll(PayrollAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException;

    public String getBlockedSalDetails(int year, int month);

    public String doAddPayslipReleases(String releasedFor, PayrollAjaxHandlerAction aThis);

    public String doEditPayslipReleases(String releasedFor, PayrollAjaxHandlerAction aThis);

    public String calculateActualDetails(String requestString) throws ServiceLocatorException;
    
     public String getSumOfHRA(int empId, double overlaySavingAmount, String paymentDateEmp,String financialYear)  throws ServiceLocatorException;

     public String getRevisedSalDetails(String year, int month);
     
     public String getTdsCalculation(String year, int month);
     public String doGetEmployeeNames(String query) throws ServiceLocatorException;
     public String deleteTefEmpDetails(int id) throws ServiceLocatorException;

   


    public String getEmpSavingsValidate(Double empSavings, int tefId, double taxableIncome, int categoryId, int exemptionId,String empId) throws ServiceLocatorException;
}
