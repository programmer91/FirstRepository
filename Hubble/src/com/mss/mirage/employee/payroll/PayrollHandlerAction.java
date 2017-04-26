 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.payroll;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.ConnectionProvider;

import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;


import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class PayrollHandlerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private PayrollHandlerService payrolHandlerService;
    private DataSourceDataProvider dataSourceDataProvider;
    private HibernateDataProvider hibernateDataProvider;
    private DefaultDataProvider defaultDataProvider;
    private String resultType;
    private int userRoleId;
    private String queryString;
    private String fromDate;
    private String toDate;
    private int month;
    private String year;
    private String startDate;
    private String endDate;
    private List departmentIdList;
    private Map empnamesList;
    private String departmentId;
    private String empnameById;
    private String noDueStatus;
    private String approverComments;
    private boolean withDueAmt;
    private int payrollAuthCheck;
    private String payrollPassword;
    private int payrollAuthRegister;
    private int roleTypeId;
    private String currentAction;
    private String oldPassword;
    private String submitFrom = null;
    private List currStatusList;
    private Map orgIdMap;
    private String email;
    private String empId;
    private Map reportingPersons;
    private String firstName;
    private String lastName;
    private String middleName;
    private String currStatus = ApplicationConstants.DEFAULT_EMP_STATUS;
    private List titleIdList;
    private String titleId;
    private String ssn;
    private List genderList;
    private String gender;
    private String birthDate;
    private String bankName;
    private String bankAccNo;
    private String UANNo;
    private String pfAccount;
    private String weddingDay;
    private String dateOfJoining;
    private String createdDate;
    private List empCurrentStatus;
    private String empCurrStatus;
    private String locationId;
    private String employerId;
    private String trainingPeriod;
    private String trueBirthday;
    private String passportNo;
    private String dateOfterminating;
    private String resonsForLeaving;
    private boolean isPfFlag;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String homePhone;
    private String mobilePhone;
    private String fatherPhone;
    private String fatherTitle;
    private String fathername;
    private String personalEmail;
    private String corporateEmail;
    private double basic;
    private double da;
    private double hra;
    private double ta;
    private double employerPf;
    private double ra;
    private double employeePf;
    private double entertainment;
    private double life;
    private double kidsEducation;
    private double health;
    private double vehicleAllowance;
    private double professionalTax;
    private double cca;
    private double otherDeductions;
    private double miscPay;
    private double employeeesi;
    private double employeresi;
    private double splAllowance;
    private double longTermBonus;
    private double grossPay;
    private double projectPay;
    private double variablePay;
    private double attendanceAllow;
    private double totalCost;
    private double onProjectIndValue1;
    private double onProjectIndValue2;
    private double onsiteIndV;
    private double prevYtdSalary;
    private double empSaving1;
    private double empSaving2;
    private double empSaving3;
    private double empSaving4;
    private double empSaving5;
    private double empSaving6;
    private String adharNo;
    private String itgBatch;
    private String homeAddressId;
    private String bankAccountNo;
    private String payrollFlag;
    private Map shiftList;
    private Map classificationList;
    private Map regionsList;
    private Map bankNameList;
    private Map locationsList;
    private Map paymentTypeList;
    private String workPhone;
    private String empNo;
    private String dateOfRevision;
    private boolean onProjectInd;
    private String payRollId;
    private String datePayRevised;
    private String lockAmtStartDate;
    private boolean onsiteInd;
    private String lifeInsureanceAmt;
    private String lifeInsureanceTerm;
    private String lifeInsureanceAnnual;
    private String lifeInsureancePolicy;
    private String healthInsuranceAnnual;
    private String healthInsuranceAmt;
    private String wagecomments;
    private String wagecomments1;
    private String generalcomments;
    private String referencecomments;
    private String paymentType;
    private int noOfDays;
    private int noOfWeekendDays;
    private int noOfHolidays;
    private int monthOverlay;
    private int yearOverlay;
    private String resultMessage;
    private int wageId;
//new for wages
    private String employeeName;
    private String classificationId;
    private String payPeriodEndDate;
    private String payPeriodStartDate;
    private String daysInMonth;
    private String daysWorked;
    private boolean freezePayroll;
    private String payrollDate;
    private String payRunId;
    private double netPaid;
    private double dedEmpPf;
    private double tds;
    private double actualTds;
    private double incomeTax_TE;
    private double dedProfessionalTax;
    private String daysProject;
    private double dedIncomeTax;
    private String daysVacation;
    private double dedCorporateLoan;
    private String vactionsAvailable;
    private double dedLife;
    private String daysHolidays;
    private double dedHealth;
    private String daysWeekends;
    private double dedOthers;
    private double maidServices;
    private double earnedBasic;
    private double earnedFood;
    private double earnedDa;
    private double earnedLaundary;
    private double earnedHra;
    private double earnedMaidServices;
    private double earnedTa;
    private double earnedEntertainment;
    private double earnedRa;
    private double earnedKidsEducation;
    private double earnedLife;
    private double earnedVehicleAllowance;
    private double earnedHealth;
    private double earnedLongTermBonus;
    private double earnedCCa;
    private double earnedMiscPay;
    private double earnedProjectPay;
    private double earnedEmployerPf;
    private double earnedattallowance;
    private double earnedsplallowance;
    private double tdsDeduction;
    private double grossPayActualDetails;
    private double grossPayPayRollDetails;
    private double bonusCommission;
    private double netPaidActualDetails;
    private double taxableIncome;
    private double otherAdditions;
    private double ytdGross;
    private double ytdTaxableSalary;
    private double ytdTaxableCommission;
    private double ytdPf;
    private double ytdTDSonSalary;
    private double ytdProffTax;
    private double ytdTDSOnCommm;
    private double ytdLifeInsurance;
    private double ytdTDSCollected;
    private double ytdTa;
    private double ytdTDSLiabilitiesSalary;
    private double ytdRa;
    private double ytdTDSLiabilitiesBonus;
    private double ytdOthersMisc;
    private double diffTDSLiabilitiesSalary;
    private double ytdExpTaxFree;
    private double diffTDSLiabilitiesBonus;
    private double ytdProjectPay;
    private double ytdSavings1Reported;
    private double ytdSavings2Reported;
    private double ytdLongterm;
    private double employeePfActualDetails;
    private double employeePfPayRollDetails;
    private int orgId;
    private boolean isBlock;
    private String shiftId;
    private String tutionfees = "0.0";
    private String hbLoanInterst = "0.0";
    private String ppf = "0.0";
    private String medicalIns = "0.0";
    private String lifeIns = "0.0";
    private String hraLifeInsuranceSavings = "0.0";
    private String premium = "0.0";
    private String eduInterest = "0.0";
    private String fd = "0.0";
    private String hbLoanPrinciple = "0.0";
    private String mutualFunds = "0.0";
    private String nsc = "0.0";
    private List countryList;
    private String country;
    private int yearOverlayForBiometricReportGeneration;
    private int monthForBiometricReportGeneration;
    private String projectEndDate;
    //tds calculations
    private double lifeInsurancePremium;
    private double housingLoanRepay;
    private double nscTds;
    private double ppfContribution;
    private double tutionFee;
    private double elss;
    private double postOfficeTerm;
    private double bankDepositTax;
    private double othersTDS;
    private double contributionToPf;
    private double npsEmployeeContr;
    private double totalTds;
    private double totalTdsDeductable;
    private double interstOnBorrowed;
    private double interstOnBorrowedDeductable;
    private double insuranceForParents;
    private double insuranceForParentsDeduc;
    private double insuranceOthers;
    private double insuranceOthersDeduc;
    private double interstOnEdu;
    private double interstOnHrAssumptions;
    private double interstOnHrAssumptionsInv;
    private double licFromSal;
    private double netPaidPayroll;
    private double earnedGrossPay;
    private double earnedVariablePay;
    private double earnedNetPaid;
    private double expectedYearlyCost;
    private int tdsId;
    private int doRepaymentFlag;
    private boolean doRepaymentFlagVal;
    private String repaymentComments;
    private double repaymentGross;
    private double repaymentNet;
    private double repaymentVariablePay;
    private int noOfWorkingDays;
    private String freezeFlag = "";
    private boolean onSiteIndex;
    private double totalCostCalc;
    private double basicCalc;
    private double prjPayCalc;
    private double attAllowCalc;
    private double longBonusCalc;
    private double employerPfCalc;
    private double healthCalc;
    private double pfTaxCalc;
    private double monthlySalary;
    private double daCalac;
    private double hraCalc;
    private double taCalc;
    private double ccaCalc;
    private double entertainmentCalc;
    private double vehAllCal;
    private double miscCal;
    private double splAlloCalc;
    private double healthDedCalc;
    private double proftaxCalc;
    private double tdsCalc;
    private double employeePfCalc;
    private String leavesApplied;
    private String dateOfEmployement;
    private String dateOfTermination;
    private String payRollComments;
    private double diffPF;
    private boolean esiFlag;
    private double earnedEmployeeesi;
    private double earnedEmployeresi;
    private Map exemptionTypeMap;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String fileName;
    private String exemptionId;
    private String exemptionType;
    private String status;
    private boolean isManager;
    private boolean isTeamLead;
    private boolean release;
    private boolean commissions;
    private boolean settled;
    private double dueAmount;
    private String comments;
    private String createdBy;
    private String tempFlag;
    private boolean isSixMonthsLock;
    private String newPassword;
    private boolean isFixedSalary;
    private String fdTaxSavings = "0.0";
    private String taxStartDate;
    private String taxEndDate;
    private String userId;
    private boolean noSalary;
    private String paymentDate;
     private int wageFlag;
  private List practiceIdList;
   private String practiceId;
 private List practiceList;
   private int bankReportFlag;
private String bankReportsResponse;
private String payslipResponse;
private int payslipFlag;
private double earnedNewGrossPay;
private double newGrossPay;
private String financialYear;
    private String rentStartDate;
    private String rentEndDate;
    private Map rentStartDateMap;
    private Map rentEndDateMap;
private String monthlyAmount;
private String tefType;
private String selectType;
private String validityStartDate;
private String validityEndDate;
private String noOfLeaves;
private String availWeekends;
private String availLeaves;
private String availHolidays;
private String releasedDate;
private String payrollAddOrUpdate;
    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public int getPayrollAuthCheck() {
        return payrollAuthCheck;
    }

    public void setPayrollAuthCheck(int payrollAuthCheck) {
        this.payrollAuthCheck = payrollAuthCheck;
    }

    public String getPayrollPassword() {
        return payrollPassword;
    }

    public void setPayrollPassword(String payrollPassword) {
        this.payrollPassword = payrollPassword;
    }

    public int getPayrollAuthRegister() {
        return payrollAuthRegister;
    }

    public void setPayrollAuthRegister(int payrollAuthRegister) {
        this.payrollAuthRegister = payrollAuthRegister;
    }

    public int getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(int roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public boolean getIsFixedSalary() {
        return isFixedSalary;
    }

    public void setIsFixedSalary(boolean isFixedSalary) {
        this.isFixedSalary = isFixedSalary;
    }

    public String getFdTaxSavings() {
        return fdTaxSavings;
    }

    public void setFdTaxSavings(String fdTaxSavings) {
        this.fdTaxSavings = fdTaxSavings;
    }

    public String getTaxEndDate() {
        return taxEndDate;
    }

    public void setTaxEndDate(String taxEndDate) {
        this.taxEndDate = taxEndDate;
    }

    public boolean getWithDueAmt() {
        return isWithDueAmt();
    }

    public void setWithDueAmt(boolean withDueAmt) {
        this.withDueAmt = withDueAmt;
    }

    public String getApproverComments() {
        return approverComments;
    }

    public void setApproverComments(String approverComments) {
        this.approverComments = approverComments;
    }

    public String getNoDueStatus() {
        return noDueStatus;
    }

    public void setNoDueStatus(String noDueStatus) {
        this.noDueStatus = noDueStatus;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmpnameById() {
        return empnameById;
    }

    public void setEmpnameById(String empnameById) {
        this.empnameById = empnameById;
    }

    public Map getEmpnamesList() {
        return empnamesList;
    }

    public void setEmpnamesList(Map empnamesList) {
        this.empnamesList = empnamesList;
    }

    public List getDepartmentIdList() {
        return departmentIdList;
    }

    public void setDepartmentIdList(List departmentIdList) {
        this.departmentIdList = departmentIdList;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public DataSourceDataProvider getDataSourceDataProvider() {
        return dataSourceDataProvider;
    }

    public void setDataSourceDataProvider(DataSourceDataProvider dataSourceDataProvider) {
        this.dataSourceDataProvider = dataSourceDataProvider;
    }

    public DefaultDataProvider getDefaultDataProvider() {
        return defaultDataProvider;
    }

    public void setDefaultDataProvider(DefaultDataProvider defaultDataProvider) {
        this.defaultDataProvider = defaultDataProvider;
    }

    public HibernateDataProvider getHibernateDataProvider() {
        return hibernateDataProvider;
    }

    public void setHibernateDataProvider(HibernateDataProvider hibernateDataProvider) {
        this.hibernateDataProvider = hibernateDataProvider;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public PayrollHandlerService getPayrolHandlerService() {
        return payrolHandlerService;
    }

    public void setPayrolHandlerService(PayrollHandlerService payrolHandlerService) {
        this.payrolHandlerService = payrolHandlerService;
    }

    public PayrollHandlerAction() {
    }

    public String getEmployeeNoDues() {
        setResultType(LOGIN);

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->" + userRoleId);
            setResultType("accessFailed");

            try {

                //System.out.println("userRoleName----->" + userRoleName);
                //System.out.println("Next Date------>" + DateUtility.getInstance().getNextDate(DateUtility.getInstance().getCurrentMySqlDate()));
                setEndDate(DateUtility.getInstance().getNextDate(DateUtility.getInstance().getCurrentMySqlDate()));
                setStartDate(DateUtility.getInstance().FirstDateOfLastMonth());
                setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                setMonth(Calendar.getInstance().get(Calendar.MONTH));
                //    hibernateDataProvider = HibernateDataProvider.getInstance();
                String pattern = "MM/dd/yyyy";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                Calendar calendar = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), day, 23, 30, 0);
                //System.out.println("date before-->" + DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().FirstDateOfCurrentMonth()));
                calendar.setTime(DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().FirstDateOfCurrentMonth()));
                calendar.add(Calendar.MONTH, -3);
                //calendar.add(Calendar.DAY_OF_MONTH,-1);
                Date previousMonthsDate = calendar.getTime();
                //System.out.format("3 month: %s\n", previousMonthsDate);
                //System.out.println("---------------" + format.format(previousMonthsDate));
                setFromDate(format.format(previousMonthsDate));

                // setToDate(format.format(nextMonth));
                setQueryString("SELECT Id,EmpId,FromDate,ToDate,isAcknoweldged,isSetteledAllDues,isDues,DuesAmount,CreatedDate,CreatedBy,Comments,Status FROM tblEmpDuesDetails where EmpId =" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                setQueryString(getQueryString() + " ORDER BY tblEmpDuesDetails.FromDate DESC LIMIT 0, 150");
                //System.out.println("queryString-->" + queryString);
                httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, getQueryString());
                setResultType(SUCCESS);
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                setResultType(ERROR);
            }

        }//Close Session Checking
        return getResultType();
    }

    public String getEmployeeNoDuesOperations() {
        setResultType(LOGIN);

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->" + userRoleId);
            setResultType("accessFailed");

            try {
                //System.out.println("getNoDueStatus----->" + getNoDueStatus());
                setHibernateDataProvider(HibernateDataProvider.getInstance());
                setDefaultDataProvider(DefaultDataProvider.getInstance());
                setDepartmentIdList(getHibernateDataProvider().getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                setEmpnamesList(getDataSourceDataProvider().getInstance().getAllEmployeesListForPayroll(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));
                Calendar now = Calendar.getInstance();
                setMonth(1);
                setNoDueStatus("Submitted");
                setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                setQueryString("SELECT CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,tblEmpDuesDetails.Id,EmpId,FromDate,ToDate,isAcknoweldged,isSetteledAllDues,isDues,DuesAmount,tblEmpDuesDetails.CreatedDate,tblEmpDuesDetails.CreatedBy,tblEmpDuesDetails.Comments,STATUS FROM tblEmpDuesDetails LEFT OUTER JOIN tblEmployee "
                        + " ON(tblEmpDuesDetails.EmpId=tblEmployee.Id) where tblEmpDuesDetails.Status='" + getNoDueStatus() + "' and month(tblEmpDuesDetails.FromDate) =   " + getMonth());
                // queryString = queryString + " AND tblEmployee.OpsContactId = '" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString() + "' ";
                setQueryString(getQueryString() + " ORDER BY tblEmpDuesDetails.FromDate DESC LIMIT 0, 150");
                //System.out.println("queryString123456-->" + queryString);
                httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, getQueryString());

                String queryString1 = "SELECT Id,CreatedBy,CreatedDate,StartDate,EndDate,Comments,EmpList,DepartmentId,Flag, CASE WHEN flag =0 THEN 'Open' ELSE 'Closed' END AS STATUS FROM tblEmpNoDues WHERE YEAR(StartDate) = " + getYear();
                queryString1 = queryString1 + " ORDER BY tblEmpNoDues.StartDate DESC LIMIT 0, 150";
                //System.out.println("queryString1-->" + queryString1);
                httpServletRequest.setAttribute(ApplicationConstants.QS_ACTIVITY_QUERY, queryString1);
                setResultType(SUCCESS);
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                setResultType(ERROR);
            }

        }//Close Session Checking
        return getResultType();
    }

    public String employeeNoDueFormSearchOperationsRemainder() {
        setResultType(LOGIN);

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->" + userRoleId);
            setResultType("accessFailed");

            try {
                //System.out.println("userRoleName----->" + userRoleName);
                setHibernateDataProvider(HibernateDataProvider.getInstance());
                setDefaultDataProvider(DefaultDataProvider.getInstance());
                setDepartmentIdList(getHibernateDataProvider().getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                setEmpnamesList(getDataSourceDataProvider().getInstance().getAllEmployeesListForPayroll(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));
                //Calendar now = Calendar.getInstance();
                //setMonth(now.get(Calendar.MONTH));
                //setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                setQueryString("SELECT Id,CreatedBy,CreatedDate,StartDate,EndDate,Comments,EmpList,DepartmentId,Flag, CASE WHEN flag =0 THEN 'Open' ELSE 'Closed' END AS STATUS FROM tblEmpNoDues WHERE YEAR(StartDate) = " + getYear());
                setQueryString(getQueryString() + " ORDER BY tblEmpNoDues.StartDate DESC LIMIT 0, 150");
                //System.out.println("queryString-->" + queryString);
                httpServletRequest.setAttribute(ApplicationConstants.QS_ACTIVITY_QUERY, getQueryString());

                String queryString1 = "SELECT CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,tblEmpDuesDetails.Id,EmpId,FromDate,ToDate,isAcknoweldged,isSetteledAllDues,isDues,DuesAmount,tblEmpDuesDetails.CreatedDate,tblEmpDuesDetails.CreatedBy,tblEmpDuesDetails.Comments,STATUS FROM tblEmpDuesDetails LEFT OUTER JOIN tblEmployee "
                        + " ON(tblEmpDuesDetails.EmpId=tblEmployee.Id) where tblEmpDuesDetails.Status='" + getNoDueStatus() + "' ";
                //   queryString1 = queryString1 + " AND tblEmployee.OpsContactId = '" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString() + "' ";
                queryString1 = queryString1 + " ORDER BY tblEmpDuesDetails.FromDate DESC LIMIT 0, 150";
                //System.out.println("queryString1-->" + queryString1);
                httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString1);
                setResultType(SUCCESS);
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                setResultType(ERROR);
            }

        }//Close Session Checking
        return getResultType();
    }

    public String employeeNoDueFormSearch() {
        setResultType(LOGIN);

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->" + userRoleId);
            setResultType("accessFailed");

            try {


                setHibernateDataProvider(HibernateDataProvider.getInstance());

                setQueryString("SELECT Id,EmpId,FromDate,ToDate,isAcknoweldged,isSetteledAllDues,isDues,DuesAmount,CreatedDate,CreatedBy,Comments,Status FROM tblEmpDuesDetails where EmpId =" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                //System.out.println("fromDate-->" + getFromDate() + "---" + getToDate());
                if (!"".equals(getFromDate()) && !"".equals(getToDate()) && getFromDate() != null && getToDate() != null) {
                    setQueryString(getQueryString() + " AND tblEmpDuesDetails.FromDate between '" + DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getFromDate())) + "' AND '" + DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getToDate())) + "'");
                }
                setQueryString(getQueryString() + " ORDER BY tblEmpDuesDetails.FromDate DESC LIMIT 0, 150");
                //System.out.println("queryString-->" + queryString);
                httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, getQueryString());
                setResultType(SUCCESS);





            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                setResultType(ERROR);
            }

        }//Close Session Checking
        return getResultType();
    }

    public String employeeNoDueFormSearchOperations() {
        setResultType(LOGIN);

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->" + userRoleId);
            setResultType("accessFailed");
            try {
                setHibernateDataProvider(HibernateDataProvider.getInstance());
                setDefaultDataProvider(DefaultDataProvider.getInstance());
                setDepartmentIdList(getHibernateDataProvider().getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                setEmpnamesList(getDataSourceDataProvider().getInstance().getAllEmployeesListForPayroll(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));

                setQueryString("SELECT CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,tblEmpDuesDetails.Id,EmpId,FromDate,ToDate,isAcknoweldged,isSetteledAllDues,isDues,DuesAmount,tblEmpDuesDetails.CreatedDate,tblEmpDuesDetails.CreatedBy,tblEmpDuesDetails.Comments,STATUS FROM tblEmpDuesDetails LEFT OUTER JOIN tblEmployee "
                        + " ON(tblEmpDuesDetails.EmpId=tblEmployee.Id) where tblEmpDuesDetails.Status='" + getNoDueStatus() + "' ");
                // //System.out.println("fromDate-->" + getFromDate() + "---" + getToDate());
                if (!"".equals(getYear()) && !"".equals(getMonth())) {
                    int monthActual = getMonth();
                    if (monthActual == 1) {
                        setQueryString(getQueryString() + " AND month(tblEmpDuesDetails.FromDate) =  1  and  year(tblEmpDuesDetails.FromDate) = " + getYear());
                    } else if (monthActual == 2) {
                        setQueryString(getQueryString() + " AND month(tblEmpDuesDetails.FromDate) =  4  and  year(tblEmpDuesDetails.FromDate) = " + getYear());
                    } else if (monthActual == 3) {
                        setQueryString(getQueryString() + " AND month(tblEmpDuesDetails.FromDate) =  7  and  year(tblEmpDuesDetails.FromDate) = " + getYear());
                    } else if (monthActual == 4) {
                        setQueryString(getQueryString() + " AND month(tblEmpDuesDetails.FromDate) =  10 and  year(tblEmpDuesDetails.FromDate) = " + getYear());
                    }
                    //queryString = queryString + " AND month(tblEmpDuesDetails.CreatedDate) = " + monthActual + " and  year(tblEmpDuesDetails.CreatedDate) = " + getYear();
                }
                if (!"".equals(getDepartmentId())) {
                    setQueryString(getQueryString() + " AND tblEmployee.DepartmentId = '" + getDepartmentId() + "' ");
                }
                //System.out.println("empnameById-->"+getEmpnameById());
                if (!"".equals(getEmpnameById()) && !"-1".equals(getEmpnameById())) {
                    setQueryString(getQueryString() + " and  tblEmpDuesDetails.EmpId = " + DataSourceDataProvider.getInstance().getEmpIdByLoginId(getEmpnameById()));
                }
                //  queryString = queryString + " AND tblEmployee.OpsContactId = '" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString() + "' ";
                //System.out.println("WITHDUEAMT-->" + getWithDueAmt());

                if (getWithDueAmt()) {
                    setWithDueAmt(getWithDueAmt());
                    setQueryString(getQueryString() + " and  tblEmpDuesDetails.DuesAmount > 0 ");
                }
                setQueryString(getQueryString() + " ORDER BY tblEmpDuesDetails.FromDate DESC LIMIT 0, 150");


                //System.out.println("queryString-->" + queryString);
                httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, getQueryString());
                String queryString1 = "SELECT Id,CreatedBy,CreatedDate,StartDate,EndDate,Comments,EmpList,DepartmentId,Flag FROM tblEmpNoDues WHERE YEAR(StartDate) = " + getYear();
                queryString1 = queryString1 + " ORDER BY tblEmpNoDues.StartDate DESC LIMIT 0, 150";
                //System.out.println("queryString1-->" + queryString1);
                httpServletRequest.setAttribute(ApplicationConstants.QS_ACTIVITY_QUERY, queryString1);
                setResultType(SUCCESS);





            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                setResultType(ERROR);
            }

        }//Close Session Checking
        return getResultType();
    }

    public String getAllEmployeePayroll() {
        // System.out.println("getAllEmployeePayroll-------------");
        setResultType(LOGIN);
try{
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString()));
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            setResultType("accessFailed");
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", getUserRoleId())) {
                try {

                    Calendar now = Calendar.getInstance();
                    setMonth(now.get(Calendar.MONTH));
                    setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                    if ("Payroll".equals(userRoleName)) {

                        //    queryString = "SELECT  tblEmployee.Id AS Id,tblEmpPayRoll.PayRollId AS PayRollId,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmployeeName,tblEmployee.WorkPhoneNo AS WorkPhoneNo,tblEmployee.Email1 AS Email1,tblEmployee.DepartmentId AS DepartmentId FROM tblEmpPayRoll  LEFT OUTER JOIN tblEmployee   ON (tblEmployee.Id=tblEmpPayRoll.EmpId) WHERE tblEmployee.CurStatus='Active' AND tblEmployee.Country='India' AND MONTH(tblEmployee.RevisedDate) =  " + getMonth() + "  AND YEAR(tblEmployee.RevisedDate) =  " + getYear();
                        // queryString = "SELECT  tblEmployee.Id AS Id,tblEmpPayRoll.PayRollId AS PayRollId,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmployeeName,tblEmployee.WorkPhoneNo AS WorkPhoneNo,tblEmployee.Email1 AS Email1,tblEmployee.DepartmentId AS DepartmentId FROM tblEmpPayRoll  LEFT OUTER JOIN tblEmployee   ON (tblEmployee.Id=tblEmpPayRoll.EmpId) WHERE tblEmployee.CurStatus='Active' AND tblEmployee.Country='India' AND MONTH(tblEmpPayRoll.CreatedDate) =  " + getMonth() + "  AND YEAR(tblEmpPayRoll.CreatedDate) =  " + getYear()+" ORDER BY tblEmpPayRoll.CreatedDate DESC";

                        setQueryString("SELECT  tblEmployee.Id AS Id,tblEmpPayRoll.PayRollId AS PayRollId,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmployeeName,tblEmployee.WorkPhoneNo AS WorkPhoneNo,tblEmployee.Email1 AS Email1,tblEmployee.DepartmentId AS DepartmentId FROM tblEmpPayRoll  LEFT OUTER JOIN tblEmployee   ON (tblEmployee.Id=tblEmpPayRoll.EmpId)  WHERE tblEmployee.CurStatus='Active' ORDER BY tblEmpPayRoll.CreatedDate DESC");
                        setDataSourceDataProvider(DataSourceDataProvider.getInstance());
                        setCurrStatus("Active");
                        //System.err.println("Before");
                        setReportingPersons(getDataSourceDataProvider().getEmployeeNamesByReportingPerson());
                        setEmpnamesList(getDataSourceDataProvider().getInstance().getAllEmployees());
                        setOrgIdMap(dataSourceDataProvider.getPayrollOrganisationMap(ApplicationConstants.LKORGANIZATION_OPTION));
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_LIST, getQueryString());
                        setPayrollAuthCheck(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK).toString()));
                        searchPrepare();
                        if (getPayrollAuthCheck() == 0) {
                            int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                            int count = getDataSourceDataProvider().getPayrollAuthenctication(empId);
                            //  System.out.println("getAllEmployeePayroll count" + count);
                            setRoleTypeId(getDataSourceDataProvider().getPrimaryRoleId(empId));
                            if (count > 0) {
                                setPayrollAuthRegister(1);
                                //setPayrollAuthCheck(1);
                                //     System.out.println("getPayrollAuthCheck------------"+getPayrollAuthCheck());
                                //  System.out.println("getPayrollAuthRegister------------"+getPayrollAuthRegister());
                            }
                        }
                        setResultType(SUCCESS);
                    }
                } catch (Exception ex) {

                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    setResultType(ERROR);
                }
            }//END-Authorization Checking
        }//Close Session Checking
          } catch (Exception ex) {

                  System.out.println("error"); 
                }
        return getResultType();
    }

    public String searchPrepare() throws Exception {
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            setHibernateDataProvider(HibernateDataProvider.getInstance());
            setDefaultDataProvider(DefaultDataProvider.getInstance());
            setDepartmentIdList(getHibernateDataProvider().getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
            setCurrStatusList(getDefaultDataProvider().getCurrentStatus(ApplicationConstants.CURRENT_STATUS_OPTIONS));

            setResultType(SUCCESS);
        }
        return getResultType();
    }

    public String addPayrollRecord() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {

                    setPayrollAuthCheck(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK).toString()));

                    if (getPayrollAuthCheck() == 0) {
                        return "payRoll";
                    }
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    setCurrStatusList(defaultDataProvider.getCurrentStatus(ApplicationConstants.CURRENT_STATUS_OPTIONS));
                    setGenderList(defaultDataProvider.getGender(ApplicationConstants.GENDER_OPTIONS));
                    setExemptionTypeMap(dataSourceDataProvider.getInstance().getExemptionTypes());
                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                    setShiftList(dataSourceDataProvider.getEmpShifts());
                    setClassificationList(dataSourceDataProvider.getEmpClassification());
                    setRegionsList(dataSourceDataProvider.getEmpRegion());
                    setBankNameList(dataSourceDataProvider.getEmpBanks());
                    setLocationsList(dataSourceDataProvider.getEmpLocations());
                    setPaymentTypeList(dataSourceDataProvider.getPaymentType());
                    setPracticeIdList(DataSourceDataProvider.getInstance().getPracticeByDepartment(getDepartmentId()));
                    //setTitleIdList(dataSourceDataProvider.getTitleTypeByDepartment(getDepartmentId()));
                  //  setOrgIdMap(dataSourceDataProvider.getOrganisationMapForPayrolls(ApplicationConstants.LKORGANIZATION_OPTION));
                    setOrgIdMap(dataSourceDataProvider.getPayrollOrganisationMap(ApplicationConstants.LKORGANIZATION_OPTION));
                    //System.out.println("email-->" + getEmail() + "-- emp id--" + getEmpId());
                    setEmpId(getEmpId());
                    setPayrollFlag("0");
                    setPayrollAddOrUpdate("0");
                    setEmployerId(getPayRollId());
                    setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                    setMonth(Calendar.getInstance().get(Calendar.MONTH));
                    setMonthOverlay(getMonth());
                    setYearOverlay(Integer.parseInt(getYear()));
                    payrolHandlerService = ServiceLocator.getPayRollHandlerService();
                    //int empNum = DataSourceDataProvider.getInstance().getEmpMaxNum();
                    String result = payrolHandlerService.addEmployeePayroll(Integer.parseInt(getEmpId()), this);
                    // System.out.println("result---------->" + result);
                      setPracticeList(DataSourceDataProvider.getInstance().getPracticeByDept(getDepartmentId()));
                    setReportingPersons(dataSourceDataProvider.getEmployeeNamesByReportingPerson());
                    
                    searchPrepare();
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

   public String searchPayroll() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    setPayrollAuthCheck(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK).toString()));

                    if (getPayrollAuthCheck() == 0) {
                        return "payRoll";
                    }
                   
//                     System.out.println("queryString Search getEmpnameById-->" + getUserId());
//                     System.out.println("queryString Search getEmployeeName-->" + getEmployeeName());
//                     System.out.println("queryString Search getEmpNo-->" + getEmpNo());
                    if ("Payroll".equals(userRoleName)) {
                        // queryString = "SELECT tblEmployee.Id AS Id,tblEmpPayRoll.PayRollId AS PayRollId,tblPayrollTDS.Id as TdsId,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmployeeName,tblEmployee.WorkPhoneNo AS WorkPhoneNo,tblEmployee.Email1 AS Email1,tblEmployee.DepartmentId AS DepartmentId FROM tblEmpPayRoll  LEFT OUTER JOIN tblEmployee   ON (tblEmployee.Id=tblEmpPayRoll.EmpId) left outer join tblPayrollTDS on (tblPayrollTDS.EmpId = tblEmployee.Id ) WHERE tblEmployee.Country='India'";
                        queryString = "SELECT tblEmployee.Id AS Id,tblEmpPayRoll.PayRollId AS PayRollId,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmployeeName,tblEmployee.WorkPhoneNo AS WorkPhoneNo,tblEmployee.Email1 AS Email1,tblEmployee.DepartmentId AS DepartmentId FROM tblEmpPayRoll  LEFT OUTER JOIN tblEmployee   ON (tblEmployee.Id=tblEmpPayRoll.EmpId)  WHERE 1=1";

                       
                      if (getEmployeeName()!=null && !"".equals(getEmployeeName())) {
                        if (getUserId() != null && !"".equals(getUserId())) {
                            queryString = queryString + " and tblEmployee.LoginId='" + getUserId() + "'";
                        }
                         }
                        if (!"".equals(getCurrStatus()) && !"All".equals(getCurrStatus())) {
                            queryString = queryString + " and tblEmployee.CurStatus='" + getCurrStatus() + "'";
                        }
                        if (!"".equals(getEmpNo())) {
                            queryString = queryString + " and tblEmployee.EmpNo='" + getEmpNo() + "'";
                        }
                         if(!"PleaseSelect".equals(getSelectType())){
                        //if (getIsPfFlag()) {
                            if("PF".equals(getSelectType())){
                            queryString = queryString + "  AND tblEmpPayroll.PF_Flag = 1 ";
                        }
                     //   if (getOnProjectInd()) {
                           if("OnProject".equals(getSelectType())){
                            queryString = queryString + "  AND tblEmpPayRoll.OnProject = 1 ";
                        }
                        if("ESI".equals(getSelectType())){
                            queryString = queryString + "  AND tblEmpPayRoll.esiFlag = 1 ";
                        }
                         if("NoSalary".equals(getSelectType())){
                            queryString = queryString + "  AND tblEmpPayRoll.NoSalary = 1 ";
                        }
                        }
//                        if (getIsPfFlag()) {
//                            queryString = queryString + "  AND tblEmpPayroll.PF_Flag = 1 ";
//                        }
//                        if (getOnProjectInd()) {
//                            queryString = queryString + "  AND tblEmpPayRoll.OnProject = 1 ";
//                        }
//                        if (getOnSiteIndex()) {
//                            queryString = queryString + "  AND tblEmpPayRoll.OnSite = 1 ";
//                        }
                        if (getOrgId() != 0 && !"".equals(getOrgId()) && !"All".equals(getOrgId())) {
                           //  System.out.println("queryString Search getOrgId-->" + getOrgId());
                            //  String organization = DataSourceDataProvider.getInstance().getOrgNameById(getOrgId());
                            // System.out.println("queryString Search organization-->" + organization);
                              queryString = queryString + " and tblEmpPayRoll.OrgAccId=" + getOrgId() ;
                        }

                        queryString = queryString + " ORDER BY tblEmpPayRoll.CreatedDate DESC";

                    //   System.out.println("queryString-->" + queryString);
                        dataSourceDataProvider = DataSourceDataProvider.getInstance();
                        setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                        setReportingPersons(dataSourceDataProvider.getEmployeeNamesByReportingPerson());
                        setOrgIdMap(dataSourceDataProvider.getPayrollOrganisationMap(ApplicationConstants.LKORGANIZATION_OPTION));
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_LIST, queryString);

                        searchPrepare();
                        resultType = SUCCESS;
                    }
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }


    public String editEmployeePayroll() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            String employeeLeavesWeekends = "";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    setPayrollAuthCheck(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK).toString()));

                    if (getPayrollAuthCheck() == 0) {
                        return "payRoll";
                    }
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    defaultDataProvider = DefaultDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    setCurrStatusList(defaultDataProvider.getCurrentStatus(ApplicationConstants.CURRENT_STATUS_OPTIONS));
                    setGenderList(defaultDataProvider.getGender(ApplicationConstants.GENDER_OPTIONS));
                    setExemptionTypeMap(dataSourceDataProvider.getInstance().getExemptionTypes());
                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                    setShiftList(dataSourceDataProvider.getEmpShifts());
                    setClassificationList(dataSourceDataProvider.getEmpClassification());
                    setRegionsList(dataSourceDataProvider.getEmpRegion());
                    setBankNameList(dataSourceDataProvider.getEmpBanks());
                    setLocationsList(dataSourceDataProvider.getEmpLocations());
                    setPaymentTypeList(dataSourceDataProvider.getPaymentType());
                    setPracticeIdList(DataSourceDataProvider.getInstance().getPracticeByDepartment(getDepartmentId()));
                    // System.out.println("-- emp id--" + getPayRollId() + "====" + getEmpId());
                  //  setOrgIdMap(dataSourceDataProvider.getOrganisationMapForPayrolls(ApplicationConstants.LKORGANIZATION_OPTION));
                    setOrgIdMap(dataSourceDataProvider.getPayrollOrganisationMap(ApplicationConstants.LKORGANIZATION_OPTION));
                    setPayRollId(getPayRollId());
                    setPayrollAddOrUpdate("1");
                    // setEmployerId(getPayRollId());
                    setEmpId(getEmpId());
                    setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                    setMonth(Calendar.getInstance().get(Calendar.MONTH));
                    setMonthOverlay(getMonth());
                    setYearOverlay(Integer.parseInt(getYear()));
                  
                    payrolHandlerService = ServiceLocator.getPayRollHandlerService();
                    //int empNum = DataSourceDataProvider.getInstance().getEmpMaxNum();
                    payrolHandlerService.setEmployeePayrollEditDetails(Integer.parseInt(getPayRollId()), this);
                 
                    setPracticeList(DataSourceDataProvider.getInstance().getPracticeByDept(getDepartmentId()));
                    setReportingPersons(dataSourceDataProvider.getEmployeeNamesByReportingPerson());
                    searchPrepare();
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String doGetPayRollReports() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                     setPayrollAuthCheck(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK).toString()));

                    if (getPayrollAuthCheck() == 0) {
                        return "payRoll";
                    }
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    Calendar c = Calendar.getInstance();
                    int cur_year = c.get(Calendar.YEAR);
                    int cur_month = (c.get(Calendar.MONTH) + 1);
                    String cur_month1 = "";
                    if (cur_month < 10) {
                        cur_month1 = "0" + cur_month;
                    } else {
                        cur_month1 = cur_month + "";
                    }
                    String payDate = cur_month1 + "/" + "10" + "/" + cur_year;
                    //  System.out.println("payDate.."+payDate);
                    setPaymentDate(payDate);

                    int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    setNoOfDays(monthMaxDays);
                    setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                    setMonth(Calendar.getInstance().get(Calendar.MONTH));
                    c.set(Integer.parseInt(getYear()), getMonth() - 1, 1);

                    int count = 0;
                    for (int day = 1; day <= monthMaxDays; day++) {
                        c.set(Integer.parseInt(getYear()), getMonth() - 1, day);
                        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
                            count++;

                        }
                    }
                    setMonthForBiometricReportGeneration(getMonth());
                    setYearOverlayForBiometricReportGeneration(Integer.parseInt(getYear()));
                    setOrgIdMap(dataSourceDataProvider.getPayrollOrganisationMap(ApplicationConstants.LKORGANIZATION_OPTION));
                    setBankNameList(dataSourceDataProvider.getEmpBanks());
                    setMonthOverlay(getMonth());
                    setYearOverlay(Integer.parseInt(getYear()));
                    setNoOfWeekendDays(count);
                    setNoOfWorkingDays(getNoOfDays() - getNoOfWeekendDays());
                    setNoOfHolidays(0);

                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));

                    // setSearchReviewMap(dataSourceDataProvider.getInstance().getAllReviewTypes());
                    setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getEmpWageDetails() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    setPayrollAuthCheck(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK).toString()));

                    if (getPayrollAuthCheck() == 0) {
                        return "payRoll";
                    }
                    Calendar now = Calendar.getInstance();
                    setMonth(now.get(Calendar.MONTH));
                    setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                    if ("Payroll".equals(userRoleName)) {

                        // queryString = "SELECT  tblEmployee.Id AS Id,tblEmpWages.Wag_Id as Wag_Id,tblEmpWages.Freeze_Payroll as Freeze_Payroll,tblEmpWages.PayRoll_Id AS PayRollId,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmployeeName,tblEmployee.WorkPhoneNo AS WorkPhoneNo,tblEmployee.Email1 AS Email1,tblEmployee.DepartmentId AS DepartmentId,MONTH(tblEmpWages.PayrollDate) as payRollMonth,YEAR(tblEmpWages.PayrollDate) as payrollYear FROM tblEmpWages  LEFT OUTER JOIN tblEmployee   ON (tblEmployee.Id=tblEmpWages.EmpId) WHERE tblEmployee.CurStatus='Active' AND tblEmployee.Country='India' AND MONTH(tblEmployee.RevisedDate) =  " + getMonth() + "  AND YEAR(tblEmployee.RevisedDate) =  " + getYear();

                        queryString = "SELECT DaysInMonth,DaysWorked,DaysProject,DaysVacation,DaysHolidays,Daysweekends,Earned_Net_Paid,tblEmployee.Id AS Id,tblEmpWages.Wag_Id AS Wag_Id,"
                                + "tblEmpWages.Freeze_Payroll AS Freeze_Payroll,tblEmpWages.PayRoll_Id AS PayRollId,"
                                + "CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmployeeName,tblEmployee.WorkPhoneNo AS WorkPhoneNo,"
                                + "tblEmployee.Email1 AS Email1,tblEmployee.DepartmentId AS DepartmentId,YEAR(tblEmpWages.PayrollDate) as payrollDateYear,MONTH(tblEmpWages.PayrollDate) as payrollDateMonth,"
                                + "CONCAT(MONTHNAME(tblEmpWages.PayrollDate),',',YEAR(tblEmpWages.PayrollDate)) AS PayrollMonthYear,TDS,LeavesAvailable "
                                + " FROM tblEmpWages "
                                + " LEFT OUTER JOIN tblEmployee ON (tblEmployee.Id=tblEmpWages.EmpId) WHERE tblEmployee.CurStatus='Active'"
                                + " AND MONTH(tblEmpWages.PayrollDate) =  " + getMonth() + "  AND YEAR(tblEmpWages.PayrollDate) =  " + getYear() + " ORDER BY tblEmpWages.CreateDate DESC";

                        dataSourceDataProvider = DataSourceDataProvider.getInstance();

                        setCurrStatus("Active");
                        //System.err.println("Before");

                        setReportingPersons(dataSourceDataProvider.getEmployeeNamesByReportingPerson());
                        setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                        setOrgIdMap(dataSourceDataProvider.getPayrollOrganisationMap(ApplicationConstants.LKORGANIZATION_OPTION));
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_LIST, queryString);

                        searchPrepare();
                        resultType = SUCCESS;
                    }
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String searchWages() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    setPayrollAuthCheck(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK).toString()));

                    if (getPayrollAuthCheck() == 0) {
                        return "payRoll";
                    }
                    // System.out.println("queryString Search getEmpNo-->" + userRoleName);
                    // System.out.println("queryString Search getEmpnameById-->" + getEmpnameById());
                    // System.out.println("queryString Search getCurrStatus-->" + getCurrStatus());
                    // System.out.println("queryString Search getEmpNo-->" + getEmpNo());
                    if ("Payroll".equals(userRoleName)) {
                       queryString = "SELECT DaysInMonth,DaysWorked,DaysProject,DaysVacation,DaysHolidays,Daysweekends,Earned_Net_Paid,tblEmployee.Id AS Id,tblEmpWages.Wag_Id AS Wag_Id,"
                                + "tblEmpWages.Freeze_Payroll AS Freeze_Payroll,tblEmpWages.PayRoll_Id AS PayRollId,"
                                + "CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmployeeName,tblEmployee.WorkPhoneNo AS WorkPhoneNo,"
                                + "tblEmployee.Email1 AS Email1,tblEmployee.DepartmentId AS DepartmentId,YEAR(tblEmpWages.PayrollDate) as payrollDateYear,MONTH(tblEmpWages.PayrollDate) as payrollDateMonth,"
                                + "CONCAT(MONTHNAME(tblEmpWages.PayrollDate),',',YEAR(tblEmpWages.PayrollDate)) AS PayrollMonthYear,TDS,LeavesAvailable "
                                + " FROM tblEmpWages  "
                                + " LEFT OUTER JOIN tblEmployee ON (tblEmployee.Id=tblEmpWages.EmpId) LEFT JOIN tblEmpPayroll ON (tblEmpWages.PayRoll_Id = tblEmpPayroll.PayrollId) WHERE 1=1 ";
                                //+ " AND tblEmployee.Country='India'";


                        if (!"".equals(getDepartmentId())) {
                            queryString = queryString + " and tblEmployee.DepartmentId = '" + getDepartmentId() + "'";
                        }
                        
                       if (getEmployeeName()!=null && !"".equals(getEmployeeName())) {
                        if (getUserId() != null && !"".equals(getUserId())) {
                            queryString = queryString + " and tblEmployee.LoginId='" + getUserId() + "'";
                        }
                        }

                        if (!"".equals(getCurrStatus()) && !"All".equalsIgnoreCase(getCurrStatus())) {
                            queryString = queryString + " and tblEmployee.CurStatus='" + getCurrStatus() + "'";
                        }
                        if (!"".equals(getEmpNo())) {
                            queryString = queryString + " and tblEmployee.EmpNo='" + getEmpNo() + "'";
                        }

                        if (getMonth() > 0) {
                            queryString = queryString + " AND MONTH(tblEmpWages.PayrollDate) =  " + getMonth();
                        }
                        if (!"".equals(getYear())) {
                            queryString = queryString + "  AND YEAR(tblEmpWages.PayrollDate) = " + getYear();
                        }
                        if (getIsPfFlag()) {
                            queryString = queryString + "  AND tblEmpPayroll.PF_Flag = 1 ";
                        }

                        if (getOnProjectInd()) {
                            queryString = queryString + "  AND tblEmpWages.OnProject = 1 ";
                        }
                        if (getOnSiteIndex()) {
                            queryString = queryString + "  AND tblEmpWages.OnSite = 1 ";
                        }
                         if (getOrgId() != 0 && !"".equals(getOrgId()) && !"All".equals(getOrgId())) {
                          //   System.out.println("queryString Search getOrgId-->" + getOrgId());
                           //   String organization = DataSourceDataProvider.getInstance().getOrgNameById(getOrgId());
                          //   System.out.println("queryString Search organization-->" + organization);
                              queryString = queryString + " and tblEmpWages.OrgId=" + getOrgId() ;
                        }
                        queryString = queryString + " ORDER BY tblEmpWages.CreateDate DESC";


                        // System.out.println("queryString-->" + queryString);
                        dataSourceDataProvider = DataSourceDataProvider.getInstance();
                        setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                        setReportingPersons(dataSourceDataProvider.getEmployeeNamesByReportingPerson());
                        setOrgIdMap(dataSourceDataProvider.getPayrollOrganisationMap(ApplicationConstants.LKORGANIZATION_OPTION));
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_EMP_LIST, queryString);
                        if (!"".equals(getFreezeFlag())) {
                            if (!"".equals(getResultMessage())) {
                                setResultMessage("<font style='color:green;font-size:15px;'>" + getResultMessage() + "</font>");
                            }
                        }
                        searchPrepare();
                        resultType = SUCCESS;
                    }
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String editEmployeeWages() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    setPayrollAuthCheck(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK).toString()));

                    if (getPayrollAuthCheck() == 0) {
                        return "payRoll";
                    }
                    if ("Payroll".equals(userRoleName)) {
                        dataSourceDataProvider = DataSourceDataProvider.getInstance();
                        setClassificationList(dataSourceDataProvider.getEmpClassification());
                        setPaymentTypeList(dataSourceDataProvider.getPaymentType());
                        setBankNameList(dataSourceDataProvider.getEmpBanks());
                        setPayRollId(getPayRollId());
                        setWageId(getWageId());
                        setEmpId(getEmpId());
                        payrolHandlerService = ServiceLocator.getPayRollHandlerService();
                        //int empNum = DataSourceDataProvider.getInstance().getEmpMaxNum();
                        payrolHandlerService.setEmployeeWageDetailsForUpdate(Integer.parseInt(getPayRollId()), this);
                        resultType = SUCCESS;
                    }
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getDashBoardForEmpReports() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->" + userRoleId);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    // System.out.println("userRoleName----->" + userRoleName);
                    if ("Payroll".equals(userRoleName) || "Operations".equals(userRoleName)) {
                        dataSourceDataProvider = DataSourceDataProvider.getInstance();
                        hibernateDataProvider = HibernateDataProvider.getInstance();
                        defaultDataProvider = DefaultDataProvider.getInstance();
                        setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                        setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                        setCountryList(hibernateDataProvider.getContries(ApplicationConstants.COUNTRY_OPTIONS));
                        setCountry("India");
                        Calendar now = Calendar.getInstance();
                        setMonth(now.get(Calendar.MONTH));
                        setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                        resultType = SUCCESS;
                    }



                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    /*
     * Payroll Check
     * Date : 04/29/2015 
     */

   public String payrollCheck() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //  System.out.println("workingCountry----->" + userRoleId);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    // System.out.println("userRoleName----->" + userRoleName);
                    if ("Payroll".equals(userRoleName) || "Operations".equals(userRoleName)) {

                        Calendar now = Calendar.getInstance();
                        setMonth(now.get(Calendar.MONTH));
                        setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                        //   System.out.println("test");
                        hibernateDataProvider = HibernateDataProvider.getInstance();
                        setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                        setOrgIdMap(DataSourceDataProvider.getInstance().getPayrollOrganisationMap(ApplicationConstants.LKORGANIZATION_OPTION));
                        setPracticeIdList(DataSourceDataProvider.getInstance().getPracticeByDepartment(getDepartmentId()));
                        setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                        //  System.out.println("test3");
                        resultType = SUCCESS;
                    }





                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String getEmployeePayRollDashBoard() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            // System.out.println("workingCountry----->" + userRoleId);
            resultType = "accessFailed";

            try {
                //    System.out.println("userRoleName----->" + userRoleName);
                setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());


                int month = Calendar.getInstance().get(Calendar.MONTH);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                 int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                
                if(day<=15 && month==3){
                    year=year-1;
                }
                String sdate = "";
                String edate = "";
                if (month >= 3) {
                    sdate = "04/01/" + year;
                    edate = "03/31/" + (year + 1);
                    setFinancialYear("April" + year + "-March" + (year + 1));
                    setRentStartDate((year) + "-04-01");
                    setRentEndDate((year + 1) + "-03-31");
                } else {
                    sdate = "04/01/" + (year - 1);
                    edate = "03/31/" + year;
                    setFinancialYear("April" + (year - 1) + "-March" + (year));
                    setRentStartDate((year - 1) + "-04-01");
                    setRentEndDate((year) + "-03-31");
                }


                setRentStartDateMap(DateUtility.getInstance().getMonthsWithSrartDate(month,year));
                setRentEndDateMap(DateUtility.getInstance().getMonthsWithEndDate(month,year));

                setEndDate(edate);

                //  setDateAssigned(DateUtility.getInstance().getCurrentMySqlDate());
                setStartDate(sdate);
                setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                setMonth(Calendar.getInstance().get(Calendar.MONTH));


                //   System.out.println("test");
                hibernateDataProvider = HibernateDataProvider.getInstance();

                setExemptionTypeMap(dataSourceDataProvider.getInstance().getExemptionTypes());
                // System.out.println("test3");
                queryString = "SELECT tblEmpTaxExemptionDetails.Id,tblEmpTaxExemptionDetails.EmpId,tblEmpTaxExemptionDetails.ExemptionId,tblLKTaxExemptions.ExemptionType,tblEmpTaxExemptionDetails.SavingsAmount,tblEmpTaxExemptionDetails.ApprovedAmount,tblEmpTaxExemptionDetails.ApprovedBy,tblEmpTaxExemptionDetails.ApproverComments,tblEmpTaxExemptionDetails.STATUS,tblEmpTaxExemptionDetails.AttachmentName,tblEmpTaxExemptionDetails.AttachmentLocation,tblEmpTaxExemptionDetails.AppliedDate as AppliedDate,tblEmpTaxExemptionDetails.SavingsType,CASE WHEN (tblEmpTaxExemptionDetails.CheckFlag=0 AND tblEmpTaxExemptionDetails.STATUS='Applied') THEN '<img src=\"../../includes/images/DBGrid/Delete.png\">' ELSE '' END AS del FROM tblEmpTaxExemptionDetails LEFT OUTER JOIN tblLKTaxExemptions ON (tblEmpTaxExemptionDetails.ExemptionId=tblLKTaxExemptions.id) where IsActive='Active' AND ";


                queryString = queryString + " tblEmpTaxExemptionDetails.EmpId=" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();

                if (!"".equals(getStartDate()) && !"".equals(getEndDate()) && getStartDate() != null && getEndDate() != null) {
                    queryString = queryString + " AND tblEmpTaxExemptionDetails.AppliedDate between '" + DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate())) + "' AND '" + DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate())) + "'";

                }

                queryString = queryString + " ORDER BY tblEmpTaxExemptionDetails.STATUS='Applied' DESC";
            //    System.out.println("queryString-->" + queryString);
                httpServletRequest.setAttribute(ApplicationConstants.EMP_REVIEWS_LIST, queryString);
                setFromDate(DateUtility.getInstance().FirstDateOfCurrentMonth());
                queryString = "SELECT Id,EmpId,FromDate,ToDate,isAcknoweldged,isSetteledAllDues,isDues,DuesAmount,CreatedDate,CreatedBy,Comments,Status FROM tblEmpDuesDetails where EmpId =" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                queryString = queryString + " ORDER BY tblEmpDuesDetails.CreatedDate DESC";
                // System.out.println("queryString-->" + queryString);
                httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString);
                resultType = SUCCESS;



            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }//Close Session Checking
        return resultType;
    }

    public String taxExemptionDownload() throws Exception {
        // System.out.println("setServletResponse method");
        resultType = SUCCESS;
        try {
            //this.setId(Integer.parseInt(httpServletRequest.getParameter("reviewId").toString()));

            String fileLocation = getAttachmentLocationByExemptionId(Integer.parseInt(httpServletRequest.getParameter("exemptionId").toString()));

            //fileName = this.getAttachmentLocation()
            //.substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getAttachmentLocation().length());
            // fileName = httpServletRequest.getParameter("fileName").toString();
            httpServletResponse.setContentType("application/force-download");
            //  System.out.println("fileLocation--->" + fileLocation);
            if (!"".equals(fileLocation) && !"null".equals(fileLocation) && fileLocation != null && fileLocation.length() != 0) {
                File file = new File(fileLocation);
                if(file.exists()){
                fileName = file.getName();
                //  System.out.println("Filename" + fileName);
                inputStream = new FileInputStream(file);
                outputStream = httpServletResponse.getOutputStream();
                httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                int noOfBytesRead = 0;
                byte[] byteArray = null;
                while (true) {
                    byteArray = new byte[1024];
                    noOfBytesRead = inputStream.read(byteArray);
                    if (noOfBytesRead == -1) {
                        break;
                    }
                    outputStream.write(byteArray, 0, noOfBytesRead);
                }

                inputStream.close();
                outputStream.close();
                }else{
                      setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No Attachment exists !!</font>");
                resultType = INPUT;
                }
            } else {
                //System.out.println("in else");
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No Attachment exists !!</font>");
                resultType = INPUT;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }
        return resultType;
    }


    public String getAttachmentLocationByExemptionId(int exemptionId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String fileLocation = null;
        try {

            queryString = "SELECT AttachmentLocation FROM tblEmpTaxExemptionDetails WHERE Id = " + exemptionId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fileLocation = resultSet.getString("AttachmentLocation");
            }


            // }//Closing Cache Checking
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        //  System.out.println("I am Out of Data Source Provider");
        return fileLocation; // returning the object.
    }

    public String taxExemptionDownloadForRolePayroll() throws Exception {
        //  System.out.println("setServletResponse method");
        resultType = SUCCESS;
        try {
            //this.setId(Integer.parseInt(httpServletRequest.getParameter("reviewId").toString()));

            String fileLocation = getAttachmentLocationByExemptionId(Integer.parseInt(httpServletRequest.getParameter("exemptionId").toString()));

            //fileName = this.getAttachmentLocation()
            //.substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getAttachmentLocation().length());
            // fileName = httpServletRequest.getParameter("fileName").toString();
            httpServletResponse.setContentType("application/force-download");
            // System.out.println("fileLocation--->" + fileLocation);
            if (!"".equals(fileLocation) && !"null".equals(fileLocation) && fileLocation != null && fileLocation.length() != 0) {
                File file = new File(fileLocation);
                fileName = file.getName();
                //  System.out.println("Filename" + fileName);
                inputStream = new FileInputStream(file);
                outputStream = httpServletResponse.getOutputStream();
                httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                int noOfBytesRead = 0;
                byte[] byteArray = null;
                while (true) {
                    byteArray = new byte[1024];
                    noOfBytesRead = inputStream.read(byteArray);
                    if (noOfBytesRead == -1) {
                        break;
                    }
                    outputStream.write(byteArray, 0, noOfBytesRead);
                }

                inputStream.close();
                outputStream.close();
            } else {
                //    System.out.println("in else");
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No Attachment exists !!</font>");
                resultType = INPUT;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }
        return resultType;
    }

    public String getPayRollTaxDashBoard() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            // System.out.println("workingCountry----->" + userRoleId);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                     int month = Calendar.getInstance().get(Calendar.MONTH);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                 int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                
                if(day<=15 && month==3){
                    year=year-1;
                }
                String sdate = "";
                String edate = "";
                if (month >= 3) {
                    sdate = "04/01/" + year;
                    edate = "03/31/" + (year + 1);
                    setFinancialYear("April" + year + "-March" + (year + 1));
                    setRentStartDate((year) + "-04-01");
                    setRentEndDate((year + 1) + "-03-31");
                } else {
                    sdate = "04/01/" + (year - 1);
                    edate = "03/31/" + year;
                    setFinancialYear("April" + (year - 1) + "-March" + (year));
                    setRentStartDate((year - 1) + "-04-01");
                    setRentEndDate((year) + "-03-31");
                }
                    setRentStartDateMap(DateUtility.getInstance().getMonthsWithSrartDate(month,year));
                setRentEndDateMap(DateUtility.getInstance().getMonthsWithEndDate(month,year));
                   // System.out.println("getPayRollTaxDashBoard method");
                    setTaxStartDate(sdate);
                    setTaxEndDate(edate);
                    setStartDate(sdate);
                    setEndDate(edate);
                  //  setValidityStartDate(sdate);
                   // setValidityEndDate(edate);
                    setPayrollAuthCheck(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK).toString()));

                    if (getPayrollAuthCheck() == 0) {
                        return "payRoll";
                    }
                    //    System.out.println("userRoleName----->" + userRoleName);


//                    setEndDate(DateUtility.getInstance().getCurrentMySqlDate());
//
//                    setStartDate(DateUtility.getInstance().FirstDateOfLastMonth());
                    // System.out.println("test");
                    hibernateDataProvider = HibernateDataProvider.getInstance();

                    setExemptionTypeMap(dataSourceDataProvider.getInstance().getExemptionTypes());
                    setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                    setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                    setMonth(Calendar.getInstance().get(Calendar.MONTH));
                    setMonthOverlay(getMonth());
                    setYearOverlay(Integer.parseInt(getYear()));
                    setStatus("Applied");
                    setOrgIdMap(dataSourceDataProvider.getInstance().getPayrollOrganisationMap(ApplicationConstants.LKORGANIZATION_OPTION));
                    // System.out.println("test3");
                    queryString = "SELECT tblEmpTaxExemptionDetails.Id,tblEmpTaxExemptionDetails.EmpId,EmpNo,CONCAT (tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,tblEmpTaxExemptionDetails.ExemptionId,"
                            + "tblLKTaxExemptions.ExemptionType,tblEmpTaxExemptionDetails.SavingsAmount,tblEmpTaxExemptionDetails.ApprovedAmount,"
                            + "tblEmpTaxExemptionDetails.ApprovedBy,tblEmpTaxExemptionDetails.ApproverComments,tblEmpTaxExemptionDetails.STATUS,"
                            + "tblEmployee.DepartmentId,tblEmployee.IsManager,tblEmployee.IsTeamLead,WorkPhoneNo,"
                            + "tblEmpTaxExemptionDetails.AttachmentName,tblEmpTaxExemptionDetails.AttachmentLocation,tblEmpTaxExemptionDetails.AppliedDate as AppliedDate,tblEmpTaxExemptionDetails.SavingsType,CASE WHEN (tblEmpTaxExemptionDetails.CheckFlag=0 AND tblEmpTaxExemptionDetails.STATUS='Applied') THEN '<img src=\"../../includes/images/DBGrid/Delete.png\">' ELSE '' END AS del FROM tblEmpTaxExemptionDetails "
                            + "LEFT OUTER JOIN tblLKTaxExemptions ON (tblEmpTaxExemptionDetails.ExemptionId=tblLKTaxExemptions.id) "
                            + "LEFT OUTER JOIN tblEmployee ON (tblEmpTaxExemptionDetails.EmpId=tblEmployee.Id) where tblEmpTaxExemptionDetails.STATUS='Applied' AND IsActive='Active' order by tblEmpTaxExemptionDetails.STATUS='Applied' DESC";

                    //System.out.println("queryString-->" + queryString);
                    httpServletRequest.setAttribute(ApplicationConstants.EMP_REVIEWS_LIST, queryString);
                    resultType = SUCCESS;



                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }

    public String employeeTaxAssumptionSearch() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            // System.out.println("workingCountry----->" + userRoleId);
            resultType = "accessFailed";

            try {

                int month = Calendar.getInstance().get(Calendar.MONTH);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                 int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                
                if(day<=15 && month==3){
                    year=year-1;
                }
                String sdate = "";
                String edate = "";
                if (month >= 3) {
                    sdate = "04/01/" + year;
                    edate = "03/31/" + (year + 1);
                    setFinancialYear("April" + year + "-March" + (year + 1));
                    setRentStartDate((year) + "-04-01");
                    setRentEndDate((year + 1) + "-03-31");
                } else {
                    sdate = "04/01/" + (year - 1);
                    edate = "03/31/" + year;
                    setFinancialYear("April" + (year - 1) + "-March" + (year));
                    setRentStartDate((year - 1) + "-04-01");
                    setRentEndDate((year) + "-03-31");
                }
                    setRentStartDateMap(DateUtility.getInstance().getMonthsWithSrartDate(month,year));
                setRentEndDateMap(DateUtility.getInstance().getMonthsWithEndDate(month,year));
                hibernateDataProvider = HibernateDataProvider.getInstance();

                setExemptionTypeMap(dataSourceDataProvider.getInstance().getExemptionTypes());
                //  System.out.println("getStatus----------->" + getStatus());
                queryString = "SELECT tblEmpTaxExemptionDetails.Id,tblEmpTaxExemptionDetails.EmpId,tblLKTaxExemptions.ExemptionType,tblEmpTaxExemptionDetails.SavingsAmount,tblEmpTaxExemptionDetails.ApprovedAmount,tblEmpTaxExemptionDetails.ApprovedBy,tblEmpTaxExemptionDetails.ApproverComments,tblEmpTaxExemptionDetails.STATUS,tblEmpTaxExemptionDetails.AttachmentName,tblEmpTaxExemptionDetails.AttachmentLocation,tblEmpTaxExemptionDetails.AppliedDate as AppliedDate,tblEmpTaxExemptionDetails.SavingsType,CASE WHEN (tblEmpTaxExemptionDetails.CheckFlag=0 AND tblEmpTaxExemptionDetails.STATUS='Applied') THEN '<img src=\"../../includes/images/DBGrid/Delete.png\">' ELSE '' END AS del FROM tblEmpTaxExemptionDetails LEFT OUTER JOIN tblLKTaxExemptions ON (tblEmpTaxExemptionDetails.ExemptionId=tblLKTaxExemptions.id) where IsActive='Active' AND";


                queryString = queryString + " tblEmpTaxExemptionDetails.EmpId=" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();

                if (!"".equals(getStartDate()) && !"".equals(getEndDate()) && getStartDate() != null && getEndDate() != null) {
                    queryString = queryString + " AND tblEmpTaxExemptionDetails.AppliedDate between '" + DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate())) + "' AND '" + DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate())) + "'";

                }
                // System.out.println("1");
                if (!"".equals(getExemptionType()) && getExemptionType() != null) {
                    queryString = queryString + " AND tblEmpTaxExemptionDetails.ExemptionId=" + getExemptionType();
                }
                //   System.out.println("2");
                if (!"".equals(getStatus()) && getStatus() != null) {
                    queryString = queryString + " AND tblEmpTaxExemptionDetails.STATUS='" + getStatus() + "'";
                }
                //  System.out.println("3");
                queryString = queryString + " ORDER BY tblEmpTaxExemptionDetails.STATUS='Applied' DESC";
                //    System.out.println("queryString-->" + queryString);
                httpServletRequest.setAttribute(ApplicationConstants.EMP_REVIEWS_LIST, queryString);
                queryString = "SELECT Id,EmpId,FromDate,ToDate,isAcknoweldged,isSetteledAllDues,isDues,DuesAmount,CreatedDate,CreatedBy,Comments,Status FROM tblEmpDuesDetails where EmpId =" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                //    System.out.println("fromDate-->"+getFromDate()+"---"+getToDate());
                if (!"".equals(getFromDate()) && !"".equals(getToDate()) && getFromDate() != null && getToDate() != null) {
                    queryString = queryString + " AND tblEmpDuesDetails.FromDate between '" + DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getFromDate())) + "' AND '" + DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getToDate())) + "'";
                }
                queryString = queryString + " ORDER BY tblEmpDuesDetails.CreatedDate DESC";
                //     System.out.println("queryString-->" + queryString);
                httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString);
                resultType = SUCCESS;





            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }//Close Session Checking
        return resultType;
    }

    public String getPayrollTaxAssumptionSearch() {
        resultType = LOGIN;
        //  System.out.println("payrollTaxAssumptionSearch");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //   System.out.println("userRoleId----->" + userRoleId);
            resultType = "accessFailed";
            int temp = 0;
            int empId = 0;
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {


                setPayrollAuthCheck(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK).toString()));

                if (getPayrollAuthCheck() == 0) {
                    return "payRoll";
                }
                try {
                    int month = Calendar.getInstance().get(Calendar.MONTH);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                 int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                
                if(day<=15 && month==3){
                    year=year-1;
                }

                String sdate = "";
                String edate = "";
                if (month >= 3) {
                    sdate = "04/01/" + year;
                    edate = "03/31/" + (year + 1);
                    setFinancialYear("April" + year + "-March" + (year + 1));
                    setRentStartDate((year) + "-04-01");
                    setRentEndDate((year + 1) + "-03-31");
                } else {
                    sdate = "04/01/" + (year - 1);
                    edate = "03/31/" + year;
                    setFinancialYear("April" + (year - 1) + "-March" + (year));
                    setRentStartDate((year - 1) + "-04-01");
                    setRentEndDate((year) + "-03-31");
                }
                    setRentStartDateMap(DateUtility.getInstance().getMonthsWithSrartDate(month,year));
                setRentEndDateMap(DateUtility.getInstance().getMonthsWithEndDate(month,year));
                    hibernateDataProvider = HibernateDataProvider.getInstance();

                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                    setExemptionTypeMap(dataSourceDataProvider.getInstance().getExemptionTypes());
                    setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                    setOrgIdMap(dataSourceDataProvider.getInstance().getPayrollOrganisationMap(ApplicationConstants.LKORGANIZATION_OPTION));

                    if (getEmployeeName()!=null && !"".equals(getEmployeeName()) && getUserId() != null && !"".equals(getUserId()) && getEmpNo() != null && !"".equals(getEmpNo())) {

                        empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(getUserId());
                        int empIdbyNo = DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(getEmpNo()));
                        //  System.out.println("empIdbyNo----->"+empIdbyNo);
                        if (empIdbyNo != empId) {
                            temp = 1;
                        }
                    } else {

                         if (getEmpNo() != null && !"".equals(getEmpNo())) {
                            empId = DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(getEmpNo()));
                            //   System.out.println("empId----->"+empId);
                        }
                       else if (getEmployeeName()!=null && !"".equals(getEmployeeName()) && getUserId() != null && !"".equals(getUserId())) {

                            empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(getUserId());
                            //System.out.println("emoName empId----->"+empId);
                        }
                    }


                    //  System.out.println("getStatus----------->" + getStatus());
                    queryString = "SELECT tblEmpTaxExemptionDetails.Id,tblEmpTaxExemptionDetails.EmpId,EmpNo,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,tblEmpTaxExemptionDetails.ExemptionId,"
                            + "tblLKTaxExemptions.ExemptionType,tblEmpTaxExemptionDetails.SavingsAmount,tblEmpTaxExemptionDetails.ApprovedAmount,"
                            + "tblEmpTaxExemptionDetails.ApprovedBy,tblEmpTaxExemptionDetails.ApproverComments,tblEmpTaxExemptionDetails.STATUS,"
                            + "tblEmployee.DepartmentId,tblEmployee.IsManager,tblEmployee.IsTeamLead,WorkPhoneNo,"
                            + "tblEmpTaxExemptionDetails.AttachmentName,tblEmpTaxExemptionDetails.AttachmentLocation,tblEmpTaxExemptionDetails.AppliedDate as AppliedDate,tblEmpTaxExemptionDetails.SavingsType,CASE WHEN (tblEmpTaxExemptionDetails.CheckFlag=0 AND tblEmpTaxExemptionDetails.STATUS='Applied') THEN  '<img src=\"../../includes/images/DBGrid/Delete.png\">' ELSE '' END AS del FROM tblEmpTaxExemptionDetails "
                            + "LEFT OUTER JOIN tblLKTaxExemptions ON (tblEmpTaxExemptionDetails.ExemptionId=tblLKTaxExemptions.id)"
                            + "LEFT OUTER JOIN tblEmployee ON (tblEmpTaxExemptionDetails.EmpId=tblEmployee.Id) WHERE 1=1 and IsActive='Active' ";


                    //queryString = queryString + " tblEmpTaxExemptionDetails.EmpId=" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();

                    if (getStartDate() != null && !"".equals(getStartDate()) && getEndDate() != null && !"".equals(getEndDate())) {
                        queryString = queryString + " AND tblEmpTaxExemptionDetails.AppliedDate between '" + DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate())) + "' AND '" + DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate())) + "'";

                    }
                    if (getExemptionType() != null && !"".equals(getExemptionType())) {
                        queryString = queryString + " AND tblEmpTaxExemptionDetails.ExemptionId=" + getExemptionType();
                    }
                    if (getStatus() != null && !"".equals(getStatus())) {
                        queryString = queryString + " AND tblEmpTaxExemptionDetails.STATUS='" + getStatus() + "'";
                    }


//                if (isTeamLead) {
//                    queryString += " AND tblEmployee.IsTeamLead=1";
//                }
//                if (isManager) {
//                    queryString += " AND tblEmployee.IsManager=1";
//                }
//                if (departmentId!=null && !"".equals(departmentId)) {
//                    queryString += " AND tblEmployee.DepartmentId='" + getDepartmentId() + "'";
//                }
                   // if (employeeName != null && !"".equals(employeeName) && empId != 0) {
                    if ((employeeName != null && !"".equals(employeeName)) || (getEmpNo() != null && !"".equals(getEmpNo()))) {

                        queryString += " AND tblEmployee.Id='" + empId + "'";
                    }
                    if (getOrgId() != 0 && !"".equals(getOrgId()) && !"All".equals(getOrgId())) {
                        queryString = queryString + " AND tblEmpTaxExemptionDetails.OrgID=" + getOrgId();
                    }

                    if (getTefType() != null && !"".equals(getTefType()) && !"All".equalsIgnoreCase(getTefType())) {
                        queryString = queryString + " AND tblEmpTaxExemptionDetails.SavingsType='" + getTefType() + "'";
                    }
//                    if (getValidityStartDate() != null && !"".equals(getValidityStartDate()) && getValidityEndDate() != null && !"".equals(getValidityEndDate())) {
//                        queryString = queryString + " AND tblEmpTaxExemptionDetails.ValidityDate between '" + DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getValidityStartDate())) + "' AND '" + DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getValidityEndDate())) + "'";
//
//                    }
                    queryString = queryString + " ORDER BY tblEmpTaxExemptionDetails.STATUS='Applied' DESC";
                   // System.out.println("queryString-->" + queryString);
                    httpServletRequest.setAttribute(ApplicationConstants.EMP_REVIEWS_LIST, queryString);
                    resultType = SUCCESS;



                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }
        }//Close Session Checking
        return resultType;
    }

    public String payrollAuthenticationRegister() {
        resultType = LOGIN;
        //  System.out.println("payrollAuthenticationRegister-----");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                    //  System.out.println("empId---------->" + empId);
                    //  System.out.println("getPayrollPassword()---------->" + getPayrollPassword());
                    payrolHandlerService = ServiceLocator.getPayRollHandlerService();
                    int count = payrolHandlerService.payrollAuthenticationRegister(empId, getPayrollPassword());
                    //    System.out.println("count---------->" + count);

                    if (count > 0) {
                        setPayrollAuthCheck(1);
                        setPayrollAuthRegister(0);
                        //    System.out.println("getPayrollAuthCheck------------"+getPayrollAuthCheck());
                        //    System.out.println("getPayrollAuthRegister------------"+getPayrollAuthRegister());
                        resultType = SUCCESS;
                    }


                    searchPrepare();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

  public String payrollAuthenticationLogin() {
        resultType = LOGIN;
        // System.out.println("payrollAuthenticationLogin-----");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                      int empId=Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                 
                 //  System.out.println("empId---------->" + empId);
                  //  System.out.println("getPayrollPassword()---------->" + getPayrollPassword());
                   
                   // System.out.println("getPayrollPassword---------->"+getPayrollPassword());
                     if(!"".equals(getPayrollPassword())){
                   
                      payrolHandlerService = ServiceLocator.getPayRollHandlerService();
                    int count = payrolHandlerService.payrollAuthenticationLogin(empId,getPayrollPassword());
                    
                 // System.out.println("count---------->" + count);
                  
                     
                    if(count>0){
                        setPayrollAuthCheck(1);
                          httpServletRequest.getSession(false).setAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK,1);
                        setPayrollAuthRegister(1);
                       //System.out.println("getPayrollAuthCheck------------"+getPayrollAuthCheck());
                       //System.out.println("getPayrollAuthRegister------------"+getPayrollAuthRegister());
                        resultType = SUCCESS;
                    }
                    else{
                     httpServletRequest.getSession(false).setAttribute("payrollAuthcheckresultMessage", "<font style='color:red;font-size:15px;'>Invalid Password !!</font>");
                }
                     }
                     else{
                            httpServletRequest.getSession(false).setAttribute("payrollAuthcheckresultMessage", "<font style='color:red;font-size:15px;'>Password Can't be Empty!!!</font>");
                     }
                  
                    searchPrepare();
                   
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }  

    public String payrollAuthenticationCanel() {
        resultType = LOGIN;
        //System.out.println("payrollAuthenticationCanel-----");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                    //  System.out.println("empId---------->" + empId);
                    payrolHandlerService = ServiceLocator.getPayRollHandlerService();
                    setCurrentAction(payrolHandlerService.payrollAuthenticationCanel(empId));
                    //  System.out.println("getCurrentAction "+getCurrentAction());
                    resultType = SUCCESS;



                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

    public String resetPayrollPassword() throws Exception {
//        applicationDataProvider = applicationDataProvider.getInstance();
        try {
            resultType = LOGIN;
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {

                    int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                    try {
                        //boolean isReset = ServiceLocator.getGeneralService().updatePassword(this);
                        payrolHandlerService = ServiceLocator.getPayRollHandlerService();
                        int updatedRows = payrolHandlerService.updatePayrollPassword(empId, getOldPassword(), getNewPassword());
                        if (updatedRows == 1) {//isReset
                            resultMessage = "<font color=\"green\" size=\"1.5\">Congrats! You have changed your password succesfully </font>";
                            resultType = SUCCESS;
                        } else {
                            resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! We are not able to change your password. Please enter valid password! </font>";
                            resultType = INPUT;
                        }
                        httpServletRequest.setAttribute("resultMessage", resultMessage);
                        resultType = SUCCESS;

                    } catch (Exception ex) {
                        //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                        httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                        resultType = ERROR;
                    }

                }//Closing Sessiong Checking
            }
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }
        return resultType;

    }

    public String tdsCalculations() throws Exception {
//        applicationDataProvider = applicationDataProvider.getInstance();
        //System.out.println("tdsCalculations method");
        try {
            resultType = LOGIN;
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

                //  if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {

                int empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                try {
                    //boolean isReset = ServiceLocator.getGeneralService().updatePassword(this);
                    payrolHandlerService = ServiceLocator.getPayRollHandlerService();
                    Calendar c = Calendar.getInstance();

                    setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                    setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
                    httpServletRequest.setAttribute("resultMessage", resultMessage);
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

                //Closing Sessiong Checking
            }
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }
        //  System.out.println("resultType---"+resultType);
        return resultType;

    }

    public String getReleasePayslip() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            //  String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            // System.out.println("workingCountry----->" + userRoleId);
            resultType = "accessFailed";

            try {


                Calendar now = Calendar.getInstance();
                setMonth(now.get(Calendar.MONTH) + 1);
                setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));


                queryString = "SELECT Id,ReleasedFor,ReleasedDate,ReleasedBy,STATUS FROM tblReleasePayslip ORDER BY ReleasedFor  DESC";
                //  System.out.println(getId()+"queryString11111111-->" + queryString);


                //     System.out.println("queryString-->" + queryString);
                httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString);
                resultType = SUCCESS;


            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }//Close Session Checking
        return resultType;
    }
       public String releasePayslipSearch() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
          //  String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
           // System.out.println("workingCountry----->" + userRoleId);
            resultType = "accessFailed";

            try {
                
              
             //   System.out.println("fromDate-->" + getFromDate());
                queryString = "SELECT ReleasedFor,ReleasedDate,ReleasedBy,STATUS FROM tblReleasePayslip WHERE ReleasedFor ";
             
              
                        if (getFromDate() != null&&!"".equals(getFromDate())&&getToDate() != null&&!"".equals(getToDate())  ) {
                            queryString = queryString +" BETWEEN '"+ DateUtility.getInstance().convertStringToMySQLDate(getFromDate())+"'";
                       
                            queryString = queryString + " AND '"+ DateUtility.getInstance().convertStringToMySQLDate(getToDate())+"'" ;
                        }
                  queryString = queryString + "  ORDER BY ReleasedFor  DESC";
                
//                 if(getId() == 0){
//                Calendar now = Calendar.getInstance();
//                    setMonth(now.get(Calendar.MONTH)+1);
//                    setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
//                }else{
//                      DateFormat inputDF  = new SimpleDateFormat("MM/dd/yy");
//            Calendar cal = Calendar.getInstance();
//                    Date ReleasedFor = inputDF.parse(getReleaseFor());
//                    int month = cal.get(Calendar.MONTH);
//                    int year = cal.get(Calendar.YEAR);
//                    setMonth(month);
//                    setYear(String.valueOf(year));
//                }
             //   System.out.println("queryString-->" + queryString);
             
           //     System.out.println("queryString-->" + queryString);
                httpServletRequest.setAttribute(ApplicationConstants.QUERY_STRING, queryString);
                resultType = SUCCESS;





            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }//Close Session Checking
        return resultType;
    }  
  public String doRunWages() {
        resultType = LOGIN;
        
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                 SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                try {
                 //   System.out.println("start time "+format.format( new java.util.Date()));
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    Calendar c = Calendar.getInstance();
                   
                    
                    
                   //  System.out.println("payDate.."+payDate);
                  //   setPaymentDate(payDate);
                     
                     // ajax code
                      setCreatedBy( httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                     String  responseString = ServiceLocator.getPayRollHandlerService().runEmpWagesForCurrentMonth(Integer.parseInt(getYear()), getMonth(), this);
                  //  System.out.println("responseString---"+responseString);
                     httpServletRequest.setAttribute("runWageResponse", responseString);
                     setWageFlag(1);
                     // ajax code end
                //    int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    //setNoOfDays(monthMaxDays);
                    //setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                   // setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
                    c.set(Integer.parseInt(getYear()), getMonth() - 1, 1);
                    
                //    int count = 0;
//                    for (int day = 1; day <= monthMaxDays; day++) {
//                        c.set(Integer.parseInt(getYear()), getMonth() - 1, day);
//                        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//                        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
//                            count++;
//                            
//                        }
//                    }
                    setMonthForBiometricReportGeneration(getMonth());
                    setYearOverlayForBiometricReportGeneration(Integer.parseInt(getYear()));
                    setOrgIdMap(dataSourceDataProvider.getPayrollOrganisationMap(ApplicationConstants.LKORGANIZATION_OPTION));
                    setBankNameList(dataSourceDataProvider.getEmpBanks());
                    setMonthOverlay(getMonth());
                    setYearOverlay(Integer.parseInt(getYear()));
                 //   setNoOfWeekendDays(count);
                //    setNoOfWorkingDays(getNoOfDays() - getNoOfWeekendDays());
                    setNoOfHolidays(0);
                    
                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));

                    // setSearchReviewMap(dataSourceDataProvider.getInstance().getAllReviewTypes());
                    setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }finally{
                   // System.out.println("end time "+format.format( new java.util.Date()));
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
 public String doTdsGeneration() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            resultType = "accessFailed";
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    
                    setPayrollAuthCheck(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PAYROLL_AUTH_CHECK).toString()));

                    if (getPayrollAuthCheck() == 0) {
                        return "payRoll";
                    }
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    Calendar c = Calendar.getInstance();
                     if(getMonthOverlay()!= 0 ){
                         setMonthOverlay(getMonthOverlay());
                    }
                     else{
                        
        setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
        setMonthOverlay(getMonth());
                     }
                    
                    if(getYearOverlay()!= 0 ){
                         setYearOverlay(getYearOverlay());
                         
                    }
                    else{
                         setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                         setYearOverlay(Integer.parseInt(getYear()));
                    }
                    if(getPaymentDate()!=null && !"".equals(getPaymentDate())){
                        setPaymentDate(getPaymentDate());
                    }
                    else{
                    int cur_year = getYearOverlay();
                    int cur_month = getMonthOverlay();
                    String cur_month1 = "";
                    if (cur_month < 10) {
                        cur_month1 = "0" + cur_month;
                    } else {
                        cur_month1 = cur_month + "";
                    }
                    String payDate = cur_month1 + "/" + "10" + "/" + cur_year;
                    //  System.out.println("payDate.."+payDate);
                    setPaymentDate(payDate);
                    }
                     if(getOrgId()!= 0){
                    setOrgId(getOrgId());
                    }

//                    int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
//                    setNoOfDays(monthMaxDays);
//                    setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
//                    setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
//                    c.set(Integer.parseInt(getYear()), getMonth() - 1, 1);
//
//                    int count = 0;
//                    for (int day = 1; day <= monthMaxDays; day++) {
//                        c.set(Integer.parseInt(getYear()), getMonth() - 1, day);
//                        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//                        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
//                            count++;
//
//                        }
//                    }
//                      System.out.println("OrgId ***---- >"+getOrgId());
//                     System.out.println("getYearOverlay ***---- >"+getYearOverlay());
//                      System.out.println("getMonthOverlay *** ---- >"+getMonthOverlay());
//                       System.out.println("getNoOfDays*** ---- >"+getNoOfDays());
                    //  System.out.println("getPaymentDate ***---- >"+getPaymentDate());
                   
                    
                    
                   
                   
//                    setNoOfWeekendDays(count);
//                    setNoOfWorkingDays(getNoOfDays() - getNoOfWeekendDays());
                    setNoOfHolidays(0);

                    setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                    setMonthForBiometricReportGeneration(getMonthOverlay());
                    setYearOverlayForBiometricReportGeneration(getYearOverlay());
                    setOrgIdMap(dataSourceDataProvider.getPayrollOrganisationMap(ApplicationConstants.LKORGANIZATION_OPTION));
                    setBankNameList(dataSourceDataProvider.getEmpBanks());
                
                    // setSearchReviewMap(dataSourceDataProvider.getInstance().getAllReviewTypes());
                    setEmpnamesList(dataSourceDataProvider.getInstance().getAllEmployees());
                    resultType = SUCCESS;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }

  public String getSampleForm12BB() throws Exception {
        // System.out.println("setServletResponse method");
        resultType = SUCCESS;
        try {
            //this.setId(Integer.parseInt(httpServletRequest.getParameter("reviewId").toString()));

           String fileLocation = Properties.getProperty("SAMPLE.ADD.Sample.Form12BB");
           fileLocation = fileLocation.trim();
            //fileName = this.getAttachmentLocation()
            //.substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getAttachmentLocation().length());
            // fileName = httpServletRequest.getParameter("fileName").toString();
            httpServletResponse.setContentType("application/force-download");
             //System.out.println("fileLocation--->" + fileLocation);
            if (!"".equals(fileLocation) && !"null".equals(fileLocation) && fileLocation != null && fileLocation.length() != 0) {
                File file = new File(fileLocation);
               // System.out.println("file--->" + file);
                if(file.exists()){
                fileName = file.getName();
               //  System.out.println("Filename" + fileName);
                inputStream = new FileInputStream(file);
                outputStream = httpServletResponse.getOutputStream();
                httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                int noOfBytesRead = 0;
                byte[] byteArray = null;
                while (true) {
                    byteArray = new byte[1024];
                    noOfBytesRead = inputStream.read(byteArray);
                    if (noOfBytesRead == -1) {
                        break;
                    }
                    outputStream.write(byteArray, 0, noOfBytesRead);
                }

                inputStream.close();
                outputStream.close();
                }else{
                      setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No Attachment exists !!</font>");
                resultType = INPUT;
                }
            } else {
                //System.out.println("in else");
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No Attachment exists !!</font>");
                resultType = INPUT;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
        return resultType;
    }
 public String taxExemptionDownloadForm12BB() throws Exception {
        // System.out.println("setServletResponse method");
        resultType = SUCCESS;
        try {
            //this.setId(Integer.parseInt(httpServletRequest.getParameter("reviewId").toString()));

            String fileLocation = getAttachmentLocation1ByExemptionId(Integer.parseInt(httpServletRequest.getParameter("exemptionId").toString()));

            //fileName = this.getAttachmentLocation()
            //.substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getAttachmentLocation().length());
            // fileName = httpServletRequest.getParameter("fileName").toString();
            httpServletResponse.setContentType("application/force-download");
            //  System.out.println("fileLocation--->" + fileLocation);
            if (!"".equals(fileLocation) && !"null".equals(fileLocation) && fileLocation != null && fileLocation.length() != 0) {
                File file = new File(fileLocation);
                if(file.exists()){
                fileName = file.getName();
                //  System.out.println("Filename" + fileName);
                inputStream = new FileInputStream(file);
                outputStream = httpServletResponse.getOutputStream();
                httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                int noOfBytesRead = 0;
                byte[] byteArray = null;
                while (true) {
                    byteArray = new byte[1024];
                    noOfBytesRead = inputStream.read(byteArray);
                    if (noOfBytesRead == -1) {
                        break;
                    }
                    outputStream.write(byteArray, 0, noOfBytesRead);
                }

                inputStream.close();
                outputStream.close();
                }else{
                      setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No Attachment exists !!</font>");
                resultType = INPUT;
                }
            } else {
                //System.out.println("in else");
                setResultMessage("No records exists !!");
                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No Attachment exists !!</font>");
                resultType = INPUT;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }
        return resultType;
    }
          public String getAttachmentLocation1ByExemptionId(int exemptionId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String fileLocation = null;
        try {

            queryString = "SELECT Form12BBAttachmentPath FROM tblEmpTaxExemptionDetails WHERE Id = " + exemptionId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fileLocation = resultSet.getString("Form12BBAttachmentPath");
            }
// System.out.println("fileLocation"+fileLocation);

            // }//Closing Cache Checking
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        //  System.out.println("I am Out of Data Source Provider");
        return fileLocation; // returning the object.
    }
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * @return the withDueAmt
     */
    public boolean isWithDueAmt() {
        return withDueAmt;
    }

    /**
     * @return the submitFrom
     */
    public String getSubmitFrom() {
        return submitFrom;
    }

    /**
     * @param submitFrom the submitFrom to set
     */
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }

    /**
     * @return the currStatusList
     */
    public List getCurrStatusList() {
        return currStatusList;
    }

    /**
     * @param currStatusList the currStatusList to set
     */
    public void setCurrStatusList(List currStatusList) {
        this.currStatusList = currStatusList;
    }

    /**
     * @return the orgIdMap
     */
    public Map getOrgIdMap() {
        return orgIdMap;
    }

    /**
     * @param orgIdMap the orgIdMap to set
     */
    public void setOrgIdMap(Map orgIdMap) {
        this.orgIdMap = orgIdMap;
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
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * @return the reportingPersons
     */
    public Map getReportingPersons() {
        return reportingPersons;
    }

    /**
     * @param reportingPersons the reportingPersons to set
     */
    public void setReportingPersons(Map reportingPersons) {
        this.reportingPersons = reportingPersons;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the currStatus
     */
    public String getCurrStatus() {
        return currStatus;
    }

    /**
     * @param currStatus the currStatus to set
     */
    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus;
    }

    /**
     * @return the titleIdList
     */
    public List getTitleIdList() {
        return titleIdList;
    }

    /**
     * @param titleIdList the titleIdList to set
     */
    public void setTitleIdList(List titleIdList) {
        this.titleIdList = titleIdList;
    }

    /**
     * @return the titleId
     */
    public String getTitleId() {
        return titleId;
    }

    /**
     * @param titleId the titleId to set
     */
    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    /**
     * @return the ssn
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * @param ssn the ssn to set
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * @return the genderList
     */
    public List getGenderList() {
        return genderList;
    }

    /**
     * @param genderList the genderList to set
     */
    public void setGenderList(List genderList) {
        this.genderList = genderList;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the bankAccNo
     */
    public String getBankAccNo() {
        return bankAccNo;
    }

    /**
     * @param bankAccNo the bankAccNo to set
     */
    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

    /**
     * @return the UANNo
     */
    public String getUANNo() {
        return UANNo;
    }

    /**
     * @param UANNo the UANNo to set
     */
    public void setUANNo(String UANNo) {
        this.UANNo = UANNo;
    }

    /**
     * @return the pfAccount
     */
    public String getPfAccount() {
        return pfAccount;
    }

    /**
     * @param pfAccount the pfAccount to set
     */
    public void setPfAccount(String pfAccount) {
        this.pfAccount = pfAccount;
    }

    /**
     * @return the weddingDay
     */
    public String getWeddingDay() {
        return weddingDay;
    }

    /**
     * @param weddingDay the weddingDay to set
     */
    public void setWeddingDay(String weddingDay) {
        this.weddingDay = weddingDay;
    }

    /**
     * @return the dateOfJoining
     */
    public String getDateOfJoining() {
        return dateOfJoining;
    }

    /**
     * @param dateOfJoining the dateOfJoining to set
     */
    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    /**
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the empCurrentStatus
     */
    public List getEmpCurrentStatus() {
        return empCurrentStatus;
    }

    /**
     * @param empCurrentStatus the empCurrentStatus to set
     */
    public void setEmpCurrentStatus(List empCurrentStatus) {
        this.empCurrentStatus = empCurrentStatus;
    }

    /**
     * @return the empCurrStatus
     */
    public String getEmpCurrStatus() {
        return empCurrStatus;
    }

    /**
     * @param empCurrStatus the empCurrStatus to set
     */
    public void setEmpCurrStatus(String empCurrStatus) {
        this.empCurrStatus = empCurrStatus;
    }

    /**
     * @return the locationId
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the employerId
     */
    public String getEmployerId() {
        return employerId;
    }

    /**
     * @param employerId the employerId to set
     */
    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    /**
     * @return the trainingPeriod
     */
    public String getTrainingPeriod() {
        return trainingPeriod;
    }

    /**
     * @param trainingPeriod the trainingPeriod to set
     */
    public void setTrainingPeriod(String trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }

    /**
     * @return the trueBirthday
     */
    public String getTrueBirthday() {
        return trueBirthday;
    }

    /**
     * @param trueBirthday the trueBirthday to set
     */
    public void setTrueBirthday(String trueBirthday) {
        this.trueBirthday = trueBirthday;
    }

    /**
     * @return the passportNo
     */
    public String getPassportNo() {
        return passportNo;
    }

    /**
     * @param passportNo the passportNo to set
     */
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    /**
     * @return the dateOfterminating
     */
    public String getDateOfterminating() {
        return dateOfterminating;
    }

    /**
     * @param dateOfterminating the dateOfterminating to set
     */
    public void setDateOfterminating(String dateOfterminating) {
        this.dateOfterminating = dateOfterminating;
    }

    /**
     * @return the resonsForLeaving
     */
    public String getResonsForLeaving() {
        return resonsForLeaving;
    }

    /**
     * @param resonsForLeaving the resonsForLeaving to set
     */
    public void setResonsForLeaving(String resonsForLeaving) {
        this.resonsForLeaving = resonsForLeaving;
    }

    /**
     * @return the isPfFlag
     */
    public boolean getIsPfFlag() {
        return isPfFlag;
    }

    /**
     * @param isPfFlag the isPfFlag to set
     */
    public void setIsPfFlag(boolean isPfFlag) {
        this.isPfFlag = isPfFlag;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the homePhone
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * @param homePhone the homePhone to set
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     * @return the mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone the mobilePhone to set
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * @return the fatherPhone
     */
    public String getFatherPhone() {
        return fatherPhone;
    }

    /**
     * @param fatherPhone the fatherPhone to set
     */
    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    /**
     * @return the fatherTitle
     */
    public String getFatherTitle() {
        return fatherTitle;
    }

    /**
     * @param fatherTitle the fatherTitle to set
     */
    public void setFatherTitle(String fatherTitle) {
        this.fatherTitle = fatherTitle;
    }

    /**
     * @return the fathername
     */
    public String getFathername() {
        return fathername;
    }

    /**
     * @param fathername the fathername to set
     */
    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    /**
     * @return the personalEmail
     */
    public String getPersonalEmail() {
        return personalEmail;
    }

    /**
     * @param personalEmail the personalEmail to set
     */
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    /**
     * @return the corporateEmail
     */
    public String getCorporateEmail() {
        return corporateEmail;
    }

    /**
     * @param corporateEmail the corporateEmail to set
     */
    public void setCorporateEmail(String corporateEmail) {
        this.corporateEmail = corporateEmail;
    }

    /**
     * @return the basic
     */
    public double getBasic() {
        return basic;
    }

    /**
     * @param basic the basic to set
     */
    public void setBasic(double basic) {
        this.basic = basic;
    }

    /**
     * @return the da
     */
    public double getDa() {
        return da;
    }

    /**
     * @param da the da to set
     */
    public void setDa(double da) {
        this.da = da;
    }

    /**
     * @return the hra
     */
    public double getHra() {
        return hra;
    }

    /**
     * @param hra the hra to set
     */
    public void setHra(double hra) {
        this.hra = hra;
    }

    /**
     * @return the ta
     */
    public double getTa() {
        return ta;
    }

    /**
     * @param ta the ta to set
     */
    public void setTa(double ta) {
        this.ta = ta;
    }

    /**
     * @return the employerPf
     */
    public double getEmployerPf() {
        return employerPf;
    }

    /**
     * @param employerPf the employerPf to set
     */
    public void setEmployerPf(double employerPf) {
        this.employerPf = employerPf;
    }

    /**
     * @return the ra
     */
    public double getRa() {
        return ra;
    }

    /**
     * @param ra the ra to set
     */
    public void setRa(double ra) {
        this.ra = ra;
    }

    /**
     * @return the employeePf
     */
    public double getEmployeePf() {
        return employeePf;
    }

    /**
     * @param employeePf the employeePf to set
     */
    public void setEmployeePf(double employeePf) {
        this.employeePf = employeePf;
    }

    /**
     * @return the entertainment
     */
    public double getEntertainment() {
        return entertainment;
    }

    /**
     * @param entertainment the entertainment to set
     */
    public void setEntertainment(double entertainment) {
        this.entertainment = entertainment;
    }

    /**
     * @return the life
     */
    public double getLife() {
        return life;
    }

    /**
     * @param life the life to set
     */
    public void setLife(double life) {
        this.life = life;
    }

    /**
     * @return the kidsEducation
     */
    public double getKidsEducation() {
        return kidsEducation;
    }

    /**
     * @param kidsEducation the kidsEducation to set
     */
    public void setKidsEducation(double kidsEducation) {
        this.kidsEducation = kidsEducation;
    }

    /**
     * @return the health
     */
    public double getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * @return the vehicleAllowance
     */
    public double getVehicleAllowance() {
        return vehicleAllowance;
    }

    /**
     * @param vehicleAllowance the vehicleAllowance to set
     */
    public void setVehicleAllowance(double vehicleAllowance) {
        this.vehicleAllowance = vehicleAllowance;
    }

    /**
     * @return the professionalTax
     */
    public double getProfessionalTax() {
        return professionalTax;
    }

    /**
     * @param professionalTax the professionalTax to set
     */
    public void setProfessionalTax(double professionalTax) {
        this.professionalTax = professionalTax;
    }

    /**
     * @return the cca
     */
    public double getCca() {
        return cca;
    }

    /**
     * @param cca the cca to set
     */
    public void setCca(double cca) {
        this.cca = cca;
    }

    /**
     * @return the otherDeductions
     */
    public double getOtherDeductions() {
        return otherDeductions;
    }

    /**
     * @param otherDeductions the otherDeductions to set
     */
    public void setOtherDeductions(double otherDeductions) {
        this.otherDeductions = otherDeductions;
    }

    /**
     * @return the miscPay
     */
    public double getMiscPay() {
        return miscPay;
    }

    /**
     * @param miscPay the miscPay to set
     */
    public void setMiscPay(double miscPay) {
        this.miscPay = miscPay;
    }

    /**
     * @return the employeeesi
     */
    public double getEmployeeesi() {
        return employeeesi;
    }

    /**
     * @param employeeesi the employeeesi to set
     */
    public void setEmployeeesi(double employeeesi) {
        this.employeeesi = employeeesi;
    }

    /**
     * @return the employeresi
     */
    public double getEmployeresi() {
        return employeresi;
    }

    /**
     * @param employeresi the employeresi to set
     */
    public void setEmployeresi(double employeresi) {
        this.employeresi = employeresi;
    }

    /**
     * @return the splAllowance
     */
    public double getSplAllowance() {
        return splAllowance;
    }

    /**
     * @param splAllowance the splAllowance to set
     */
    public void setSplAllowance(double splAllowance) {
        this.splAllowance = splAllowance;
    }

    /**
     * @return the longTermBonus
     */
    public double getLongTermBonus() {
        return longTermBonus;
    }

    /**
     * @param longTermBonus the longTermBonus to set
     */
    public void setLongTermBonus(double longTermBonus) {
        this.longTermBonus = longTermBonus;
    }

    /**
     * @return the grossPay
     */
    public double getGrossPay() {
        return grossPay;
    }

    /**
     * @param grossPay the grossPay to set
     */
    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    /**
     * @return the projectPay
     */
    public double getProjectPay() {
        return projectPay;
    }

    /**
     * @param projectPay the projectPay to set
     */
    public void setProjectPay(double projectPay) {
        this.projectPay = projectPay;
    }

    /**
     * @return the variablePay
     */
    public double getVariablePay() {
        return variablePay;
    }

    /**
     * @param variablePay the variablePay to set
     */
    public void setVariablePay(double variablePay) {
        this.variablePay = variablePay;
    }

    /**
     * @return the attendanceAllow
     */
    public double getAttendanceAllow() {
        return attendanceAllow;
    }

    /**
     * @param attendanceAllow the attendanceAllow to set
     */
    public void setAttendanceAllow(double attendanceAllow) {
        this.attendanceAllow = attendanceAllow;
    }

    /**
     * @return the totalCost
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * @return the onProjectIndValue1
     */
    public double getOnProjectIndValue1() {
        return onProjectIndValue1;
    }

    /**
     * @param onProjectIndValue1 the onProjectIndValue1 to set
     */
    public void setOnProjectIndValue1(double onProjectIndValue1) {
        this.onProjectIndValue1 = onProjectIndValue1;
    }

    /**
     * @return the onProjectIndValue2
     */
    public double getOnProjectIndValue2() {
        return onProjectIndValue2;
    }

    /**
     * @param onProjectIndValue2 the onProjectIndValue2 to set
     */
    public void setOnProjectIndValue2(double onProjectIndValue2) {
        this.onProjectIndValue2 = onProjectIndValue2;
    }

    /**
     * @return the onsiteIndV
     */
    public double getOnsiteIndV() {
        return onsiteIndV;
    }

    /**
     * @param onsiteIndV the onsiteIndV to set
     */
    public void setOnsiteIndV(double onsiteIndV) {
        this.onsiteIndV = onsiteIndV;
    }

    /**
     * @return the prevYtdSalary
     */
    public double getPrevYtdSalary() {
        return prevYtdSalary;
    }

    /**
     * @param prevYtdSalary the prevYtdSalary to set
     */
    public void setPrevYtdSalary(double prevYtdSalary) {
        this.prevYtdSalary = prevYtdSalary;
    }

    /**
     * @return the empSaving1
     */
    public double getEmpSaving1() {
        return empSaving1;
    }

    /**
     * @param empSaving1 the empSaving1 to set
     */
    public void setEmpSaving1(double empSaving1) {
        this.empSaving1 = empSaving1;
    }

    /**
     * @return the empSaving2
     */
    public double getEmpSaving2() {
        return empSaving2;
    }

    /**
     * @param empSaving2 the empSaving2 to set
     */
    public void setEmpSaving2(double empSaving2) {
        this.empSaving2 = empSaving2;
    }

    /**
     * @return the empSaving3
     */
    public double getEmpSaving3() {
        return empSaving3;
    }

    /**
     * @param empSaving3 the empSaving3 to set
     */
    public void setEmpSaving3(double empSaving3) {
        this.empSaving3 = empSaving3;
    }

    /**
     * @return the empSaving4
     */
    public double getEmpSaving4() {
        return empSaving4;
    }

    /**
     * @param empSaving4 the empSaving4 to set
     */
    public void setEmpSaving4(double empSaving4) {
        this.empSaving4 = empSaving4;
    }

    /**
     * @return the empSaving5
     */
    public double getEmpSaving5() {
        return empSaving5;
    }

    /**
     * @param empSaving5 the empSaving5 to set
     */
    public void setEmpSaving5(double empSaving5) {
        this.empSaving5 = empSaving5;
    }

    /**
     * @return the adharNo
     */
    public String getAdharNo() {
        return adharNo;
    }

    /**
     * @param adharNo the adharNo to set
     */
    public void setAdharNo(String adharNo) {
        this.adharNo = adharNo;
    }

    /**
     * @return the itgBatch
     */
    public String getItgBatch() {
        return itgBatch;
    }

    /**
     * @param itgBatch the itgBatch to set
     */
    public void setItgBatch(String itgBatch) {
        this.itgBatch = itgBatch;
    }

    /**
     * @return the homeAddressId
     */
    public String getHomeAddressId() {
        return homeAddressId;
    }

    /**
     * @param homeAddressId the homeAddressId to set
     */
    public void setHomeAddressId(String homeAddressId) {
        this.homeAddressId = homeAddressId;
    }

    /**
     * @return the bankAccountNo
     */
    public String getBankAccountNo() {
        return bankAccountNo;
    }

    /**
     * @param bankAccountNo the bankAccountNo to set
     */
    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    /**
     * @return the payrollFlag
     */
    public String getPayrollFlag() {
        return payrollFlag;
    }

    /**
     * @param payrollFlag the payrollFlag to set
     */
    public void setPayrollFlag(String payrollFlag) {
        this.payrollFlag = payrollFlag;
    }

    /**
     * @return the shiftList
     */
    public Map getShiftList() {
        return shiftList;
    }

    /**
     * @param shiftList the shiftList to set
     */
    public void setShiftList(Map shiftList) {
        this.shiftList = shiftList;
    }

    /**
     * @return the classificationList
     */
    public Map getClassificationList() {
        return classificationList;
    }

    /**
     * @param classificationList the classificationList to set
     */
    public void setClassificationList(Map classificationList) {
        this.classificationList = classificationList;
    }

    /**
     * @return the regionsList
     */
    public Map getRegionsList() {
        return regionsList;
    }

    /**
     * @param regionsList the regionsList to set
     */
    public void setRegionsList(Map regionsList) {
        this.regionsList = regionsList;
    }

    /**
     * @return the bankNameList
     */
    public Map getBankNameList() {
        return bankNameList;
    }

    /**
     * @param bankNameList the bankNameList to set
     */
    public void setBankNameList(Map bankNameList) {
        this.bankNameList = bankNameList;
    }

    /**
     * @return the locationsList
     */
    public Map getLocationsList() {
        return locationsList;
    }

    /**
     * @param locationsList the locationsList to set
     */
    public void setLocationsList(Map locationsList) {
        this.locationsList = locationsList;
    }

    /**
     * @return the paymentTypeList
     */
    public Map getPaymentTypeList() {
        return paymentTypeList;
    }

    /**
     * @param paymentTypeList the paymentTypeList to set
     */
    public void setPaymentTypeList(Map paymentTypeList) {
        this.paymentTypeList = paymentTypeList;
    }

    /**
     * @return the workPhone
     */
    public String getWorkPhone() {
        return workPhone;
    }

    /**
     * @param workPhone the workPhone to set
     */
    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    /**
     * @return the empNo
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     * @param empNo the empNo to set
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * @return the dateOfRevision
     */
    public String getDateOfRevision() {
        return dateOfRevision;
    }

    /**
     * @param dateOfRevision the dateOfRevision to set
     */
    public void setDateOfRevision(String dateOfRevision) {
        this.dateOfRevision = dateOfRevision;
    }

    /**
     * @return the onProjectInd
     */
    public boolean getOnProjectInd() {
        return onProjectInd;
    }

    /**
     * @param onProjectInd the onProjectInd to set
     */
    public void setOnProjectInd(boolean onProjectInd) {
        this.onProjectInd = onProjectInd;
    }

    /**
     * @return the payRollId
     */
    public String getPayRollId() {
        return payRollId;
    }

    /**
     * @param payRollId the payRollId to set
     */
    public void setPayRollId(String payRollId) {
        this.payRollId = payRollId;
    }

    /**
     * @return the datePayRevised
     */
    public String getDatePayRevised() {
        return datePayRevised;
    }

    /**
     * @param datePayRevised the datePayRevised to set
     */
    public void setDatePayRevised(String datePayRevised) {
        this.datePayRevised = datePayRevised;
    }

    /**
     * @return the lockAmtStartDate
     */
    public String getLockAmtStartDate() {
        return lockAmtStartDate;
    }

    /**
     * @param lockAmtStartDate the lockAmtStartDate to set
     */
    public void setLockAmtStartDate(String lockAmtStartDate) {
        this.lockAmtStartDate = lockAmtStartDate;
    }

    /**
     * @return the onsiteInd
     */
    public boolean getOnsiteInd() {
        return onsiteInd;
    }

    /**
     * @param onsiteInd the onsiteInd to set
     */
    public void setOnsiteInd(boolean onsiteInd) {
        this.onsiteInd = onsiteInd;
    }

    /**
     * @return the lifeInsureanceAmt
     */
    public String getLifeInsureanceAmt() {
        return lifeInsureanceAmt;
    }

    /**
     * @param lifeInsureanceAmt the lifeInsureanceAmt to set
     */
    public void setLifeInsureanceAmt(String lifeInsureanceAmt) {
        this.lifeInsureanceAmt = lifeInsureanceAmt;
    }

    /**
     * @return the lifeInsureanceTerm
     */
    public String getLifeInsureanceTerm() {
        return lifeInsureanceTerm;
    }

    /**
     * @param lifeInsureanceTerm the lifeInsureanceTerm to set
     */
    public void setLifeInsureanceTerm(String lifeInsureanceTerm) {
        this.lifeInsureanceTerm = lifeInsureanceTerm;
    }

    /**
     * @return the lifeInsureanceAnnual
     */
    public String getLifeInsureanceAnnual() {
        return lifeInsureanceAnnual;
    }

    /**
     * @param lifeInsureanceAnnual the lifeInsureanceAnnual to set
     */
    public void setLifeInsureanceAnnual(String lifeInsureanceAnnual) {
        this.lifeInsureanceAnnual = lifeInsureanceAnnual;
    }

    /**
     * @return the lifeInsureancePolicy
     */
    public String getLifeInsureancePolicy() {
        return lifeInsureancePolicy;
    }

    /**
     * @param lifeInsureancePolicy the lifeInsureancePolicy to set
     */
    public void setLifeInsureancePolicy(String lifeInsureancePolicy) {
        this.lifeInsureancePolicy = lifeInsureancePolicy;
    }

    /**
     * @return the healthInsuranceAnnual
     */
    public String getHealthInsuranceAnnual() {
        return healthInsuranceAnnual;
    }

    /**
     * @param healthInsuranceAnnual the healthInsuranceAnnual to set
     */
    public void setHealthInsuranceAnnual(String healthInsuranceAnnual) {
        this.healthInsuranceAnnual = healthInsuranceAnnual;
    }

    /**
     * @return the healthInsuranceAmt
     */
    public String getHealthInsuranceAmt() {
        return healthInsuranceAmt;
    }

    /**
     * @param healthInsuranceAmt the healthInsuranceAmt to set
     */
    public void setHealthInsuranceAmt(String healthInsuranceAmt) {
        this.healthInsuranceAmt = healthInsuranceAmt;
    }

    /**
     * @return the wagecomments
     */
    public String getWagecomments() {
        return wagecomments;
    }

    /**
     * @param wagecomments the wagecomments to set
     */
    public void setWagecomments(String wagecomments) {
        this.wagecomments = wagecomments;
    }

    /**
     * @return the wagecomments1
     */
    public String getWagecomments1() {
        return wagecomments1;
    }

    /**
     * @param wagecomments1 the wagecomments1 to set
     */
    public void setWagecomments1(String wagecomments1) {
        this.wagecomments1 = wagecomments1;
    }

    /**
     * @return the generalcomments
     */
    public String getGeneralcomments() {
        return generalcomments;
    }

    /**
     * @param generalcomments the generalcomments to set
     */
    public void setGeneralcomments(String generalcomments) {
        this.generalcomments = generalcomments;
    }

    /**
     * @return the referencecomments
     */
    public String getReferencecomments() {
        return referencecomments;
    }

    /**
     * @param referencecomments the referencecomments to set
     */
    public void setReferencecomments(String referencecomments) {
        this.referencecomments = referencecomments;
    }

    /**
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return the noOfDays
     */
    public int getNoOfDays() {
        return noOfDays;
    }

    /**
     * @param noOfDays the noOfDays to set
     */
    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    /**
     * @return the noOfWeekendDays
     */
    public int getNoOfWeekendDays() {
        return noOfWeekendDays;
    }

    /**
     * @param noOfWeekendDays the noOfWeekendDays to set
     */
    public void setNoOfWeekendDays(int noOfWeekendDays) {
        this.noOfWeekendDays = noOfWeekendDays;
    }

    /**
     * @return the noOfHolidays
     */
    public int getNoOfHolidays() {
        return noOfHolidays;
    }

    /**
     * @param noOfHolidays the noOfHolidays to set
     */
    public void setNoOfHolidays(int noOfHolidays) {
        this.noOfHolidays = noOfHolidays;
    }

    /**
     * @return the monthOverlay
     */
    public int getMonthOverlay() {
        return monthOverlay;
    }

    /**
     * @param monthOverlay the monthOverlay to set
     */
    public void setMonthOverlay(int monthOverlay) {
        this.monthOverlay = monthOverlay;
    }

    /**
     * @return the yearOverlay
     */
    public int getYearOverlay() {
        return yearOverlay;
    }

    /**
     * @param yearOverlay the yearOverlay to set
     */
    public void setYearOverlay(int yearOverlay) {
        this.yearOverlay = yearOverlay;
    }

    /**
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * @return the wageId
     */
    public int getWageId() {
        return wageId;
    }

    /**
     * @param wageId the wageId to set
     */
    public void setWageId(int wageId) {
        this.wageId = wageId;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the classificationId
     */
    public String getClassificationId() {
        return classificationId;
    }

    /**
     * @param classificationId the classificationId to set
     */
    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    /**
     * @return the payPeriodEndDate
     */
    public String getPayPeriodEndDate() {
        return payPeriodEndDate;
    }

    /**
     * @param payPeriodEndDate the payPeriodEndDate to set
     */
    public void setPayPeriodEndDate(String payPeriodEndDate) {
        this.payPeriodEndDate = payPeriodEndDate;
    }

    /**
     * @return the payPeriodStartDate
     */
    public String getPayPeriodStartDate() {
        return payPeriodStartDate;
    }

    /**
     * @param payPeriodStartDate the payPeriodStartDate to set
     */
    public void setPayPeriodStartDate(String payPeriodStartDate) {
        this.payPeriodStartDate = payPeriodStartDate;
    }

    /**
     * @return the daysInMonth
     */
    public String getDaysInMonth() {
        return daysInMonth;
    }

    /**
     * @param daysInMonth the daysInMonth to set
     */
    public void setDaysInMonth(String daysInMonth) {
        this.daysInMonth = daysInMonth;
    }

    /**
     * @return the daysWorked
     */
    public String getDaysWorked() {
        return daysWorked;
    }

    /**
     * @param daysWorked the daysWorked to set
     */
    public void setDaysWorked(String daysWorked) {
        this.daysWorked = daysWorked;
    }

    /**
     * @return the freezePayroll
     */
    public boolean isFreezePayroll() {
        return freezePayroll;
    }

    /**
     * @param freezePayroll the freezePayroll to set
     */
    public void setFreezePayroll(boolean freezePayroll) {
        this.freezePayroll = freezePayroll;
    }

    /**
     * @return the payrollDate
     */
    public String getPayrollDate() {
        return payrollDate;
    }

    /**
     * @param payrollDate the payrollDate to set
     */
    public void setPayrollDate(String payrollDate) {
        this.payrollDate = payrollDate;
    }

    /**
     * @return the payRunId
     */
    public String getPayRunId() {
        return payRunId;
    }

    /**
     * @param payRunId the payRunId to set
     */
    public void setPayRunId(String payRunId) {
        this.payRunId = payRunId;
    }

    /**
     * @return the netPaid
     */
    public double getNetPaid() {
        return netPaid;
    }

    /**
     * @param netPaid the netPaid to set
     */
    public void setNetPaid(double netPaid) {
        this.netPaid = netPaid;
    }

    /**
     * @return the dedEmpPf
     */
    public double getDedEmpPf() {
        return dedEmpPf;
    }

    /**
     * @param dedEmpPf the dedEmpPf to set
     */
    public void setDedEmpPf(double dedEmpPf) {
        this.dedEmpPf = dedEmpPf;
    }

    /**
     * @return the tds
     */
    public double getTds() {
        return tds;
    }

    /**
     * @param tds the tds to set
     */
    public void setTds(double tds) {
        this.tds = tds;
    }

    /**
     * @return the actualTds
     */
    public double getActualTds() {
        return actualTds;
    }

    /**
     * @param actualTds the actualTds to set
     */
    public void setActualTds(double actualTds) {
        this.actualTds = actualTds;
    }

    /**
     * @return the incomeTax_TE
     */
    public double getIncomeTax_TE() {
        return incomeTax_TE;
    }

    /**
     * @param incomeTax_TE the incomeTax_TE to set
     */
    public void setIncomeTax_TE(double incomeTax_TE) {
        this.incomeTax_TE = incomeTax_TE;
    }

    /**
     * @return the dedProfessionalTax
     */
    public double getDedProfessionalTax() {
        return dedProfessionalTax;
    }

    /**
     * @param dedProfessionalTax the dedProfessionalTax to set
     */
    public void setDedProfessionalTax(double dedProfessionalTax) {
        this.dedProfessionalTax = dedProfessionalTax;
    }

    /**
     * @return the daysProject
     */
    public String getDaysProject() {
        return daysProject;
    }

    /**
     * @param daysProject the daysProject to set
     */
    public void setDaysProject(String daysProject) {
        this.daysProject = daysProject;
    }

    /**
     * @return the dedIncomeTax
     */
    public double getDedIncomeTax() {
        return dedIncomeTax;
    }

    /**
     * @param dedIncomeTax the dedIncomeTax to set
     */
    public void setDedIncomeTax(double dedIncomeTax) {
        this.dedIncomeTax = dedIncomeTax;
    }

    /**
     * @return the daysVacation
     */
    public String getDaysVacation() {
        return daysVacation;
    }

    /**
     * @param daysVacation the daysVacation to set
     */
    public void setDaysVacation(String daysVacation) {
        this.daysVacation = daysVacation;
    }

    /**
     * @return the dedCorporateLoan
     */
    public double getDedCorporateLoan() {
        return dedCorporateLoan;
    }

    /**
     * @param dedCorporateLoan the dedCorporateLoan to set
     */
    public void setDedCorporateLoan(double dedCorporateLoan) {
        this.dedCorporateLoan = dedCorporateLoan;
    }

    /**
     * @return the vactionsAvailable
     */
    public String getVactionsAvailable() {
        return vactionsAvailable;
    }

    /**
     * @param vactionsAvailable the vactionsAvailable to set
     */
    public void setVactionsAvailable(String vactionsAvailable) {
        this.vactionsAvailable = vactionsAvailable;
    }

    /**
     * @return the dedLife
     */
    public double getDedLife() {
        return dedLife;
    }

    /**
     * @param dedLife the dedLife to set
     */
    public void setDedLife(double dedLife) {
        this.dedLife = dedLife;
    }

    /**
     * @return the daysHolidays
     */
    public String getDaysHolidays() {
        return daysHolidays;
    }

    /**
     * @param daysHolidays the daysHolidays to set
     */
    public void setDaysHolidays(String daysHolidays) {
        this.daysHolidays = daysHolidays;
    }

    /**
     * @return the dedHealth
     */
    public double getDedHealth() {
        return dedHealth;
    }

    /**
     * @param dedHealth the dedHealth to set
     */
    public void setDedHealth(double dedHealth) {
        this.dedHealth = dedHealth;
    }

    /**
     * @return the daysWeekends
     */
    public String getDaysWeekends() {
        return daysWeekends;
    }

    /**
     * @param daysWeekends the daysWeekends to set
     */
    public void setDaysWeekends(String daysWeekends) {
        this.daysWeekends = daysWeekends;
    }

    /**
     * @return the dedOthers
     */
    public double getDedOthers() {
        return dedOthers;
    }

    /**
     * @param dedOthers the dedOthers to set
     */
    public void setDedOthers(double dedOthers) {
        this.dedOthers = dedOthers;
    }

    /**
     * @return the maidServices
     */
    public double getMaidServices() {
        return maidServices;
    }

    /**
     * @param maidServices the maidServices to set
     */
    public void setMaidServices(double maidServices) {
        this.maidServices = maidServices;
    }

    /**
     * @return the earnedBasic
     */
    public double getEarnedBasic() {
        return earnedBasic;
    }

    /**
     * @param earnedBasic the earnedBasic to set
     */
    public void setEarnedBasic(double earnedBasic) {
        this.earnedBasic = earnedBasic;
    }

    /**
     * @return the earnedFood
     */
    public double getEarnedFood() {
        return earnedFood;
    }

    /**
     * @param earnedFood the earnedFood to set
     */
    public void setEarnedFood(double earnedFood) {
        this.earnedFood = earnedFood;
    }

    /**
     * @return the earnedDa
     */
    public double getEarnedDa() {
        return earnedDa;
    }

    /**
     * @param earnedDa the earnedDa to set
     */
    public void setEarnedDa(double earnedDa) {
        this.earnedDa = earnedDa;
    }

    /**
     * @return the earnedLaundary
     */
    public double getEarnedLaundary() {
        return earnedLaundary;
    }

    /**
     * @param earnedLaundary the earnedLaundary to set
     */
    public void setEarnedLaundary(double earnedLaundary) {
        this.earnedLaundary = earnedLaundary;
    }

    /**
     * @return the earnedHra
     */
    public double getEarnedHra() {
        return earnedHra;
    }

    /**
     * @param earnedHra the earnedHra to set
     */
    public void setEarnedHra(double earnedHra) {
        this.earnedHra = earnedHra;
    }

    /**
     * @return the earnedMaidServices
     */
    public double getEarnedMaidServices() {
        return earnedMaidServices;
    }

    /**
     * @param earnedMaidServices the earnedMaidServices to set
     */
    public void setEarnedMaidServices(double earnedMaidServices) {
        this.earnedMaidServices = earnedMaidServices;
    }

    /**
     * @return the earnedTa
     */
    public double getEarnedTa() {
        return earnedTa;
    }

    /**
     * @param earnedTa the earnedTa to set
     */
    public void setEarnedTa(double earnedTa) {
        this.earnedTa = earnedTa;
    }

    /**
     * @return the earnedEntertainment
     */
    public double getEarnedEntertainment() {
        return earnedEntertainment;
    }

    /**
     * @param earnedEntertainment the earnedEntertainment to set
     */
    public void setEarnedEntertainment(double earnedEntertainment) {
        this.earnedEntertainment = earnedEntertainment;
    }

    /**
     * @return the earnedRa
     */
    public double getEarnedRa() {
        return earnedRa;
    }

    /**
     * @param earnedRa the earnedRa to set
     */
    public void setEarnedRa(double earnedRa) {
        this.earnedRa = earnedRa;
    }

    /**
     * @return the earnedKidsEducation
     */
    public double getEarnedKidsEducation() {
        return earnedKidsEducation;
    }

    /**
     * @param earnedKidsEducation the earnedKidsEducation to set
     */
    public void setEarnedKidsEducation(double earnedKidsEducation) {
        this.earnedKidsEducation = earnedKidsEducation;
    }

    /**
     * @return the earnedLife
     */
    public double getEarnedLife() {
        return earnedLife;
    }

    /**
     * @param earnedLife the earnedLife to set
     */
    public void setEarnedLife(double earnedLife) {
        this.earnedLife = earnedLife;
    }

    /**
     * @return the earnedVehicleAllowance
     */
    public double getEarnedVehicleAllowance() {
        return earnedVehicleAllowance;
    }

    /**
     * @param earnedVehicleAllowance the earnedVehicleAllowance to set
     */
    public void setEarnedVehicleAllowance(double earnedVehicleAllowance) {
        this.earnedVehicleAllowance = earnedVehicleAllowance;
    }

    /**
     * @return the earnedHealth
     */
    public double getEarnedHealth() {
        return earnedHealth;
    }

    /**
     * @param earnedHealth the earnedHealth to set
     */
    public void setEarnedHealth(double earnedHealth) {
        this.earnedHealth = earnedHealth;
    }

    /**
     * @return the earnedLongTermBonus
     */
    public double getEarnedLongTermBonus() {
        return earnedLongTermBonus;
    }

    /**
     * @param earnedLongTermBonus the earnedLongTermBonus to set
     */
    public void setEarnedLongTermBonus(double earnedLongTermBonus) {
        this.earnedLongTermBonus = earnedLongTermBonus;
    }

    /**
     * @return the earnedCCa
     */
    public double getEarnedCCa() {
        return earnedCCa;
    }

    /**
     * @param earnedCCa the earnedCCa to set
     */
    public void setEarnedCCa(double earnedCCa) {
        this.earnedCCa = earnedCCa;
    }

    /**
     * @return the earnedMiscPay
     */
    public double getEarnedMiscPay() {
        return earnedMiscPay;
    }

    /**
     * @param earnedMiscPay the earnedMiscPay to set
     */
    public void setEarnedMiscPay(double earnedMiscPay) {
        this.earnedMiscPay = earnedMiscPay;
    }

    /**
     * @return the earnedProjectPay
     */
    public double getEarnedProjectPay() {
        return earnedProjectPay;
    }

    /**
     * @param earnedProjectPay the earnedProjectPay to set
     */
    public void setEarnedProjectPay(double earnedProjectPay) {
        this.earnedProjectPay = earnedProjectPay;
    }

    /**
     * @return the earnedEmployerPf
     */
    public double getEarnedEmployerPf() {
        return earnedEmployerPf;
    }

    /**
     * @param earnedEmployerPf the earnedEmployerPf to set
     */
    public void setEarnedEmployerPf(double earnedEmployerPf) {
        this.earnedEmployerPf = earnedEmployerPf;
    }

    /**
     * @return the earnedattallowance
     */
    public double getEarnedattallowance() {
        return earnedattallowance;
    }

    /**
     * @param earnedattallowance the earnedattallowance to set
     */
    public void setEarnedattallowance(double earnedattallowance) {
        this.earnedattallowance = earnedattallowance;
    }

    /**
     * @return the earnedsplallowance
     */
    public double getEarnedsplallowance() {
        return earnedsplallowance;
    }

    /**
     * @param earnedsplallowance the earnedsplallowance to set
     */
    public void setEarnedsplallowance(double earnedsplallowance) {
        this.earnedsplallowance = earnedsplallowance;
    }

    /**
     * @return the tdsDeduction
     */
    public double getTdsDeduction() {
        return tdsDeduction;
    }

    /**
     * @param tdsDeduction the tdsDeduction to set
     */
    public void setTdsDeduction(double tdsDeduction) {
        this.tdsDeduction = tdsDeduction;
    }

    /**
     * @return the grossPayActualDetails
     */
    public double getGrossPayActualDetails() {
        return grossPayActualDetails;
    }

    /**
     * @param grossPayActualDetails the grossPayActualDetails to set
     */
    public void setGrossPayActualDetails(double grossPayActualDetails) {
        this.grossPayActualDetails = grossPayActualDetails;
    }

    /**
     * @return the grossPayPayRollDetails
     */
    public double getGrossPayPayRollDetails() {
        return grossPayPayRollDetails;
    }

    /**
     * @param grossPayPayRollDetails the grossPayPayRollDetails to set
     */
    public void setGrossPayPayRollDetails(double grossPayPayRollDetails) {
        this.grossPayPayRollDetails = grossPayPayRollDetails;
    }

    /**
     * @return the bonusCommission
     */
    public double getBonusCommission() {
        return bonusCommission;
    }

    /**
     * @param bonusCommission the bonusCommission to set
     */
    public void setBonusCommission(double bonusCommission) {
        this.bonusCommission = bonusCommission;
    }

    /**
     * @return the netPaidActualDetails
     */
    public double getNetPaidActualDetails() {
        return netPaidActualDetails;
    }

    /**
     * @param netPaidActualDetails the netPaidActualDetails to set
     */
    public void setNetPaidActualDetails(double netPaidActualDetails) {
        this.netPaidActualDetails = netPaidActualDetails;
    }

    /**
     * @return the taxableIncome
     */
    public double getTaxableIncome() {
        return taxableIncome;
    }

    /**
     * @param taxableIncome the taxableIncome to set
     */
    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    /**
     * @return the otherAdditions
     */
    public double getOtherAdditions() {
        return otherAdditions;
    }

    /**
     * @param otherAdditions the otherAdditions to set
     */
    public void setOtherAdditions(double otherAdditions) {
        this.otherAdditions = otherAdditions;
    }

    /**
     * @return the ytdGross
     */
    public double getYtdGross() {
        return ytdGross;
    }

    /**
     * @param ytdGross the ytdGross to set
     */
    public void setYtdGross(double ytdGross) {
        this.ytdGross = ytdGross;
    }

    /**
     * @return the ytdTaxableSalary
     */
    public double getYtdTaxableSalary() {
        return ytdTaxableSalary;
    }

    /**
     * @param ytdTaxableSalary the ytdTaxableSalary to set
     */
    public void setYtdTaxableSalary(double ytdTaxableSalary) {
        this.ytdTaxableSalary = ytdTaxableSalary;
    }

    /**
     * @return the ytdTaxableCommission
     */
    public double getYtdTaxableCommission() {
        return ytdTaxableCommission;
    }

    /**
     * @param ytdTaxableCommission the ytdTaxableCommission to set
     */
    public void setYtdTaxableCommission(double ytdTaxableCommission) {
        this.ytdTaxableCommission = ytdTaxableCommission;
    }

    /**
     * @return the ytdPf
     */
    public double getYtdPf() {
        return ytdPf;
    }

    /**
     * @param ytdPf the ytdPf to set
     */
    public void setYtdPf(double ytdPf) {
        this.ytdPf = ytdPf;
    }

    /**
     * @return the ytdTDSonSalary
     */
    public double getYtdTDSonSalary() {
        return ytdTDSonSalary;
    }

    /**
     * @param ytdTDSonSalary the ytdTDSonSalary to set
     */
    public void setYtdTDSonSalary(double ytdTDSonSalary) {
        this.ytdTDSonSalary = ytdTDSonSalary;
    }

    /**
     * @return the ytdProffTax
     */
    public double getYtdProffTax() {
        return ytdProffTax;
    }

    /**
     * @param ytdProffTax the ytdProffTax to set
     */
    public void setYtdProffTax(double ytdProffTax) {
        this.ytdProffTax = ytdProffTax;
    }

    /**
     * @return the ytdTDSOnCommm
     */
    public double getYtdTDSOnCommm() {
        return ytdTDSOnCommm;
    }

    /**
     * @param ytdTDSOnCommm the ytdTDSOnCommm to set
     */
    public void setYtdTDSOnCommm(double ytdTDSOnCommm) {
        this.ytdTDSOnCommm = ytdTDSOnCommm;
    }

    /**
     * @return the ytdLifeInsurance
     */
    public double getYtdLifeInsurance() {
        return ytdLifeInsurance;
    }

    /**
     * @param ytdLifeInsurance the ytdLifeInsurance to set
     */
    public void setYtdLifeInsurance(double ytdLifeInsurance) {
        this.ytdLifeInsurance = ytdLifeInsurance;
    }

    /**
     * @return the ytdTDSCollected
     */
    public double getYtdTDSCollected() {
        return ytdTDSCollected;
    }

    /**
     * @param ytdTDSCollected the ytdTDSCollected to set
     */
    public void setYtdTDSCollected(double ytdTDSCollected) {
        this.ytdTDSCollected = ytdTDSCollected;
    }

    /**
     * @return the ytdTa
     */
    public double getYtdTa() {
        return ytdTa;
    }

    /**
     * @param ytdTa the ytdTa to set
     */
    public void setYtdTa(double ytdTa) {
        this.ytdTa = ytdTa;
    }

    /**
     * @return the ytdTDSLiabilitiesSalary
     */
    public double getYtdTDSLiabilitiesSalary() {
        return ytdTDSLiabilitiesSalary;
    }

    /**
     * @param ytdTDSLiabilitiesSalary the ytdTDSLiabilitiesSalary to set
     */
    public void setYtdTDSLiabilitiesSalary(double ytdTDSLiabilitiesSalary) {
        this.ytdTDSLiabilitiesSalary = ytdTDSLiabilitiesSalary;
    }

    /**
     * @return the ytdRa
     */
    public double getYtdRa() {
        return ytdRa;
    }

    /**
     * @param ytdRa the ytdRa to set
     */
    public void setYtdRa(double ytdRa) {
        this.ytdRa = ytdRa;
    }

    /**
     * @return the ytdTDSLiabilitiesBonus
     */
    public double getYtdTDSLiabilitiesBonus() {
        return ytdTDSLiabilitiesBonus;
    }

    /**
     * @param ytdTDSLiabilitiesBonus the ytdTDSLiabilitiesBonus to set
     */
    public void setYtdTDSLiabilitiesBonus(double ytdTDSLiabilitiesBonus) {
        this.ytdTDSLiabilitiesBonus = ytdTDSLiabilitiesBonus;
    }

    /**
     * @return the ytdOthersMisc
     */
    public double getYtdOthersMisc() {
        return ytdOthersMisc;
    }

    /**
     * @param ytdOthersMisc the ytdOthersMisc to set
     */
    public void setYtdOthersMisc(double ytdOthersMisc) {
        this.ytdOthersMisc = ytdOthersMisc;
    }

    /**
     * @return the diffTDSLiabilitiesSalary
     */
    public double getDiffTDSLiabilitiesSalary() {
        return diffTDSLiabilitiesSalary;
    }

    /**
     * @param diffTDSLiabilitiesSalary the diffTDSLiabilitiesSalary to set
     */
    public void setDiffTDSLiabilitiesSalary(double diffTDSLiabilitiesSalary) {
        this.diffTDSLiabilitiesSalary = diffTDSLiabilitiesSalary;
    }

    /**
     * @return the ytdExpTaxFree
     */
    public double getYtdExpTaxFree() {
        return ytdExpTaxFree;
    }

    /**
     * @param ytdExpTaxFree the ytdExpTaxFree to set
     */
    public void setYtdExpTaxFree(double ytdExpTaxFree) {
        this.ytdExpTaxFree = ytdExpTaxFree;
    }

    /**
     * @return the diffTDSLiabilitiesBonus
     */
    public double getDiffTDSLiabilitiesBonus() {
        return diffTDSLiabilitiesBonus;
    }

    /**
     * @param diffTDSLiabilitiesBonus the diffTDSLiabilitiesBonus to set
     */
    public void setDiffTDSLiabilitiesBonus(double diffTDSLiabilitiesBonus) {
        this.diffTDSLiabilitiesBonus = diffTDSLiabilitiesBonus;
    }

    /**
     * @return the ytdProjectPay
     */
    public double getYtdProjectPay() {
        return ytdProjectPay;
    }

    /**
     * @param ytdProjectPay the ytdProjectPay to set
     */
    public void setYtdProjectPay(double ytdProjectPay) {
        this.ytdProjectPay = ytdProjectPay;
    }

    /**
     * @return the ytdSavings1Reported
     */
    public double getYtdSavings1Reported() {
        return ytdSavings1Reported;
    }

    /**
     * @param ytdSavings1Reported the ytdSavings1Reported to set
     */
    public void setYtdSavings1Reported(double ytdSavings1Reported) {
        this.ytdSavings1Reported = ytdSavings1Reported;
    }

    /**
     * @return the ytdSavings2Reported
     */
    public double getYtdSavings2Reported() {
        return ytdSavings2Reported;
    }

    /**
     * @param ytdSavings2Reported the ytdSavings2Reported to set
     */
    public void setYtdSavings2Reported(double ytdSavings2Reported) {
        this.ytdSavings2Reported = ytdSavings2Reported;
    }

    /**
     * @return the ytdLongterm
     */
    public double getYtdLongterm() {
        return ytdLongterm;
    }

    /**
     * @param ytdLongterm the ytdLongterm to set
     */
    public void setYtdLongterm(double ytdLongterm) {
        this.ytdLongterm = ytdLongterm;
    }

    /**
     * @return the employeePfActualDetails
     */
    public double getEmployeePfActualDetails() {
        return employeePfActualDetails;
    }

    /**
     * @param employeePfActualDetails the employeePfActualDetails to set
     */
    public void setEmployeePfActualDetails(double employeePfActualDetails) {
        this.employeePfActualDetails = employeePfActualDetails;
    }

    /**
     * @return the employeePfPayRollDetails
     */
    public double getEmployeePfPayRollDetails() {
        return employeePfPayRollDetails;
    }

    /**
     * @param employeePfPayRollDetails the employeePfPayRollDetails to set
     */
    public void setEmployeePfPayRollDetails(double employeePfPayRollDetails) {
        this.employeePfPayRollDetails = employeePfPayRollDetails;
    }

    /**
     * @return the orgId
     */
    public int getOrgId() {
        return orgId;
    }

    /**
     * @param orgId the orgId to set
     */
    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    /**
     * @return the isBlock
     */
    public boolean isIsBlock() {
        return isBlock;
    }

    /**
     * @param isBlock the isBlock to set
     */
    public void setIsBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    /**
     * @return the shiftId
     */
    public String getShiftId() {
        return shiftId;
    }

    /**
     * @param shiftId the shiftId to set
     */
    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    /**
     * @return the tutionfees
     */
    public String getTutionfees() {
        return tutionfees;
    }

    /**
     * @param tutionfees the tutionfees to set
     */
    public void setTutionfees(String tutionfees) {
        this.tutionfees = tutionfees;
    }

    /**
     * @return the hbLoanInterst
     */
    public String getHbLoanInterst() {
        return hbLoanInterst;
    }

    /**
     * @param hbLoanInterst the hbLoanInterst to set
     */
    public void setHbLoanInterst(String hbLoanInterst) {
        this.hbLoanInterst = hbLoanInterst;
    }

    /**
     * @return the ppf
     */
    public String getPpf() {
        return ppf;
    }

    /**
     * @param ppf the ppf to set
     */
    public void setPpf(String ppf) {
        this.ppf = ppf;
    }

    /**
     * @return the medicalIns
     */
    public String getMedicalIns() {
        return medicalIns;
    }

    /**
     * @param medicalIns the medicalIns to set
     */
    public void setMedicalIns(String medicalIns) {
        this.medicalIns = medicalIns;
    }

    /**
     * @return the lifeIns
     */
    public String getLifeIns() {
        return lifeIns;
    }

    /**
     * @param lifeIns the lifeIns to set
     */
    public void setLifeIns(String lifeIns) {
        this.lifeIns = lifeIns;
    }

    /**
     * @return the hraLifeInsuranceSavings
     */
    public String getHraLifeInsuranceSavings() {
        return hraLifeInsuranceSavings;
    }

    /**
     * @param hraLifeInsuranceSavings the hraLifeInsuranceSavings to set
     */
    public void setHraLifeInsuranceSavings(String hraLifeInsuranceSavings) {
        this.hraLifeInsuranceSavings = hraLifeInsuranceSavings;
    }

    /**
     * @return the premium
     */
    public String getPremium() {
        return premium;
    }

    /**
     * @param premium the premium to set
     */
    public void setPremium(String premium) {
        this.premium = premium;
    }

    /**
     * @return the eduInterest
     */
    public String getEduInterest() {
        return eduInterest;
    }

    /**
     * @param eduInterest the eduInterest to set
     */
    public void setEduInterest(String eduInterest) {
        this.eduInterest = eduInterest;
    }

    /**
     * @return the fd
     */
    public String getFd() {
        return fd;
    }

    /**
     * @param fd the fd to set
     */
    public void setFd(String fd) {
        this.fd = fd;
    }

    /**
     * @return the hbLoanPrinciple
     */
    public String getHbLoanPrinciple() {
        return hbLoanPrinciple;
    }

    /**
     * @param hbLoanPrinciple the hbLoanPrinciple to set
     */
    public void setHbLoanPrinciple(String hbLoanPrinciple) {
        this.hbLoanPrinciple = hbLoanPrinciple;
    }

    /**
     * @return the mutualFunds
     */
    public String getMutualFunds() {
        return mutualFunds;
    }

    /**
     * @param mutualFunds the mutualFunds to set
     */
    public void setMutualFunds(String mutualFunds) {
        this.mutualFunds = mutualFunds;
    }

    /**
     * @return the nsc
     */
    public String getNsc() {
        return nsc;
    }

    /**
     * @param nsc the nsc to set
     */
    public void setNsc(String nsc) {
        this.nsc = nsc;
    }

    /**
     * @return the countryList
     */
    public List getCountryList() {
        return countryList;
    }

    /**
     * @param countryList the countryList to set
     */
    public void setCountryList(List countryList) {
        this.countryList = countryList;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the yearOverlayForBiometricReportGeneration
     */
    public int getYearOverlayForBiometricReportGeneration() {
        return yearOverlayForBiometricReportGeneration;
    }

    /**
     * @param yearOverlayForBiometricReportGeneration the
     * yearOverlayForBiometricReportGeneration to set
     */
    public void setYearOverlayForBiometricReportGeneration(int yearOverlayForBiometricReportGeneration) {
        this.yearOverlayForBiometricReportGeneration = yearOverlayForBiometricReportGeneration;
    }

    /**
     * @return the monthForBiometricReportGeneration
     */
    public int getMonthForBiometricReportGeneration() {
        return monthForBiometricReportGeneration;
    }

    /**
     * @param monthForBiometricReportGeneration the
     * monthForBiometricReportGeneration to set
     */
    public void setMonthForBiometricReportGeneration(int monthForBiometricReportGeneration) {
        this.monthForBiometricReportGeneration = monthForBiometricReportGeneration;
    }

    /**
     * @return the projectEndDate
     */
    public String getProjectEndDate() {
        return projectEndDate;
    }

    /**
     * @param projectEndDate the projectEndDate to set
     */
    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    /**
     * @return the lifeInsurancePremium
     */
    public double getLifeInsurancePremium() {
        return lifeInsurancePremium;
    }

    /**
     * @param lifeInsurancePremium the lifeInsurancePremium to set
     */
    public void setLifeInsurancePremium(double lifeInsurancePremium) {
        this.lifeInsurancePremium = lifeInsurancePremium;
    }

    /**
     * @return the housingLoanRepay
     */
    public double getHousingLoanRepay() {
        return housingLoanRepay;
    }

    /**
     * @param housingLoanRepay the housingLoanRepay to set
     */
    public void setHousingLoanRepay(double housingLoanRepay) {
        this.housingLoanRepay = housingLoanRepay;
    }

    /**
     * @return the nscTds
     */
    public double getNscTds() {
        return nscTds;
    }

    /**
     * @param nscTds the nscTds to set
     */
    public void setNscTds(double nscTds) {
        this.nscTds = nscTds;
    }

    /**
     * @return the ppfContribution
     */
    public double getPpfContribution() {
        return ppfContribution;
    }

    /**
     * @param ppfContribution the ppfContribution to set
     */
    public void setPpfContribution(double ppfContribution) {
        this.ppfContribution = ppfContribution;
    }

    /**
     * @return the tutionFee
     */
    public double getTutionFee() {
        return tutionFee;
    }

    /**
     * @param tutionFee the tutionFee to set
     */
    public void setTutionFee(double tutionFee) {
        this.tutionFee = tutionFee;
    }

    /**
     * @return the elss
     */
    public double getElss() {
        return elss;
    }

    /**
     * @param elss the elss to set
     */
    public void setElss(double elss) {
        this.elss = elss;
    }

    /**
     * @return the postOfficeTerm
     */
    public double getPostOfficeTerm() {
        return postOfficeTerm;
    }

    /**
     * @param postOfficeTerm the postOfficeTerm to set
     */
    public void setPostOfficeTerm(double postOfficeTerm) {
        this.postOfficeTerm = postOfficeTerm;
    }

    /**
     * @return the bankDepositTax
     */
    public double getBankDepositTax() {
        return bankDepositTax;
    }

    /**
     * @param bankDepositTax the bankDepositTax to set
     */
    public void setBankDepositTax(double bankDepositTax) {
        this.bankDepositTax = bankDepositTax;
    }

    /**
     * @return the othersTDS
     */
    public double getOthersTDS() {
        return othersTDS;
    }

    /**
     * @param othersTDS the othersTDS to set
     */
    public void setOthersTDS(double othersTDS) {
        this.othersTDS = othersTDS;
    }

    /**
     * @return the contributionToPf
     */
    public double getContributionToPf() {
        return contributionToPf;
    }

    /**
     * @param contributionToPf the contributionToPf to set
     */
    public void setContributionToPf(double contributionToPf) {
        this.contributionToPf = contributionToPf;
    }

    /**
     * @return the npsEmployeeContr
     */
    public double getNpsEmployeeContr() {
        return npsEmployeeContr;
    }

    /**
     * @param npsEmployeeContr the npsEmployeeContr to set
     */
    public void setNpsEmployeeContr(double npsEmployeeContr) {
        this.npsEmployeeContr = npsEmployeeContr;
    }

    /**
     * @return the totalTds
     */
    public double getTotalTds() {
        return totalTds;
    }

    /**
     * @param totalTds the totalTds to set
     */
    public void setTotalTds(double totalTds) {
        this.totalTds = totalTds;
    }

    /**
     * @return the totalTdsDeductable
     */
    public double getTotalTdsDeductable() {
        return totalTdsDeductable;
    }

    /**
     * @param totalTdsDeductable the totalTdsDeductable to set
     */
    public void setTotalTdsDeductable(double totalTdsDeductable) {
        this.totalTdsDeductable = totalTdsDeductable;
    }

    /**
     * @return the interstOnBorrowed
     */
    public double getInterstOnBorrowed() {
        return interstOnBorrowed;
    }

    /**
     * @param interstOnBorrowed the interstOnBorrowed to set
     */
    public void setInterstOnBorrowed(double interstOnBorrowed) {
        this.interstOnBorrowed = interstOnBorrowed;
    }

    /**
     * @return the interstOnBorrowedDeductable
     */
    public double getInterstOnBorrowedDeductable() {
        return interstOnBorrowedDeductable;
    }

    /**
     * @param interstOnBorrowedDeductable the interstOnBorrowedDeductable to set
     */
    public void setInterstOnBorrowedDeductable(double interstOnBorrowedDeductable) {
        this.interstOnBorrowedDeductable = interstOnBorrowedDeductable;
    }

    /**
     * @return the insuranceForParents
     */
    public double getInsuranceForParents() {
        return insuranceForParents;
    }

    /**
     * @param insuranceForParents the insuranceForParents to set
     */
    public void setInsuranceForParents(double insuranceForParents) {
        this.insuranceForParents = insuranceForParents;
    }

    /**
     * @return the insuranceForParentsDeduc
     */
    public double getInsuranceForParentsDeduc() {
        return insuranceForParentsDeduc;
    }

    /**
     * @param insuranceForParentsDeduc the insuranceForParentsDeduc to set
     */
    public void setInsuranceForParentsDeduc(double insuranceForParentsDeduc) {
        this.insuranceForParentsDeduc = insuranceForParentsDeduc;
    }

    /**
     * @return the insuranceOthers
     */
    public double getInsuranceOthers() {
        return insuranceOthers;
    }

    /**
     * @param insuranceOthers the insuranceOthers to set
     */
    public void setInsuranceOthers(double insuranceOthers) {
        this.insuranceOthers = insuranceOthers;
    }

    /**
     * @return the insuranceOthersDeduc
     */
    public double getInsuranceOthersDeduc() {
        return insuranceOthersDeduc;
    }

    /**
     * @param insuranceOthersDeduc the insuranceOthersDeduc to set
     */
    public void setInsuranceOthersDeduc(double insuranceOthersDeduc) {
        this.insuranceOthersDeduc = insuranceOthersDeduc;
    }

    /**
     * @return the interstOnEdu
     */
    public double getInterstOnEdu() {
        return interstOnEdu;
    }

    /**
     * @param interstOnEdu the interstOnEdu to set
     */
    public void setInterstOnEdu(double interstOnEdu) {
        this.interstOnEdu = interstOnEdu;
    }

    /**
     * @return the interstOnHrAssumptions
     */
    public double getInterstOnHrAssumptions() {
        return interstOnHrAssumptions;
    }

    /**
     * @param interstOnHrAssumptions the interstOnHrAssumptions to set
     */
    public void setInterstOnHrAssumptions(double interstOnHrAssumptions) {
        this.interstOnHrAssumptions = interstOnHrAssumptions;
    }

    /**
     * @return the interstOnHrAssumptionsInv
     */
    public double getInterstOnHrAssumptionsInv() {
        return interstOnHrAssumptionsInv;
    }

    /**
     * @param interstOnHrAssumptionsInv the interstOnHrAssumptionsInv to set
     */
    public void setInterstOnHrAssumptionsInv(double interstOnHrAssumptionsInv) {
        this.interstOnHrAssumptionsInv = interstOnHrAssumptionsInv;
    }

    /**
     * @return the licFromSal
     */
    public double getLicFromSal() {
        return licFromSal;
    }

    /**
     * @param licFromSal the licFromSal to set
     */
    public void setLicFromSal(double licFromSal) {
        this.licFromSal = licFromSal;
    }

    /**
     * @return the netPaidPayroll
     */
    public double getNetPaidPayroll() {
        return netPaidPayroll;
    }

    /**
     * @param netPaidPayroll the netPaidPayroll to set
     */
    public void setNetPaidPayroll(double netPaidPayroll) {
        this.netPaidPayroll = netPaidPayroll;
    }

    /**
     * @return the earnedGrossPay
     */
    public double getEarnedGrossPay() {
        return earnedGrossPay;
    }

    /**
     * @param earnedGrossPay the earnedGrossPay to set
     */
    public void setEarnedGrossPay(double earnedGrossPay) {
        this.earnedGrossPay = earnedGrossPay;
    }

    /**
     * @return the earnedVariablePay
     */
    public double getEarnedVariablePay() {
        return earnedVariablePay;
    }

    /**
     * @param earnedVariablePay the earnedVariablePay to set
     */
    public void setEarnedVariablePay(double earnedVariablePay) {
        this.earnedVariablePay = earnedVariablePay;
    }

    /**
     * @return the earnedNetPaid
     */
    public double getEarnedNetPaid() {
        return earnedNetPaid;
    }

    /**
     * @param earnedNetPaid the earnedNetPaid to set
     */
    public void setEarnedNetPaid(double earnedNetPaid) {
        this.earnedNetPaid = earnedNetPaid;
    }

    /**
     * @return the expectedYearlyCost
     */
    public double getExpectedYearlyCost() {
        return expectedYearlyCost;
    }

    /**
     * @param expectedYearlyCost the expectedYearlyCost to set
     */
    public void setExpectedYearlyCost(double expectedYearlyCost) {
        this.expectedYearlyCost = expectedYearlyCost;
    }

    /**
     * @return the tdsId
     */
    public int getTdsId() {
        return tdsId;
    }

    /**
     * @param tdsId the tdsId to set
     */
    public void setTdsId(int tdsId) {
        this.tdsId = tdsId;
    }

    /**
     * @return the doRepaymentFlag
     */
    public int getDoRepaymentFlag() {
        return doRepaymentFlag;
    }

    /**
     * @param doRepaymentFlag the doRepaymentFlag to set
     */
    public void setDoRepaymentFlag(int doRepaymentFlag) {
        this.doRepaymentFlag = doRepaymentFlag;
    }

    /**
     * @return the doRepaymentFlagVal
     */
    public boolean isDoRepaymentFlagVal() {
        return doRepaymentFlagVal;
    }

    /**
     * @param doRepaymentFlagVal the doRepaymentFlagVal to set
     */
    public void setDoRepaymentFlagVal(boolean doRepaymentFlagVal) {
        this.doRepaymentFlagVal = doRepaymentFlagVal;
    }

    /**
     * @return the repaymentComments
     */
    public String getRepaymentComments() {
        return repaymentComments;
    }

    /**
     * @param repaymentComments the repaymentComments to set
     */
    public void setRepaymentComments(String repaymentComments) {
        this.repaymentComments = repaymentComments;
    }

    /**
     * @return the repaymentGross
     */
    public double getRepaymentGross() {
        return repaymentGross;
    }

    /**
     * @param repaymentGross the repaymentGross to set
     */
    public void setRepaymentGross(double repaymentGross) {
        this.repaymentGross = repaymentGross;
    }

    /**
     * @return the repaymentNet
     */
    public double getRepaymentNet() {
        return repaymentNet;
    }

    /**
     * @param repaymentNet the repaymentNet to set
     */
    public void setRepaymentNet(double repaymentNet) {
        this.repaymentNet = repaymentNet;
    }

    /**
     * @return the repaymentVariablePay
     */
    public double getRepaymentVariablePay() {
        return repaymentVariablePay;
    }

    /**
     * @param repaymentVariablePay the repaymentVariablePay to set
     */
    public void setRepaymentVariablePay(double repaymentVariablePay) {
        this.repaymentVariablePay = repaymentVariablePay;
    }

    /**
     * @return the noOfWorkingDays
     */
    public int getNoOfWorkingDays() {
        return noOfWorkingDays;
    }

    /**
     * @param noOfWorkingDays the noOfWorkingDays to set
     */
    public void setNoOfWorkingDays(int noOfWorkingDays) {
        this.noOfWorkingDays = noOfWorkingDays;
    }

    /**
     * @return the freezeFlag
     */
    public String getFreezeFlag() {
        return freezeFlag;
    }

    /**
     * @param freezeFlag the freezeFlag to set
     */
    public void setFreezeFlag(String freezeFlag) {
        this.freezeFlag = freezeFlag;
    }

    /**
     * @return the onSiteIndex
     */
    public boolean getOnSiteIndex() {
        return onSiteIndex;
    }

    /**
     * @param onSiteIndex the onSiteIndex to set
     */
    public void setOnSiteIndex(boolean onSiteIndex) {
        this.onSiteIndex = onSiteIndex;
    }

    /**
     * @return the totalCostCalc
     */
    public double getTotalCostCalc() {
        return totalCostCalc;
    }

    /**
     * @param totalCostCalc the totalCostCalc to set
     */
    public void setTotalCostCalc(double totalCostCalc) {
        this.totalCostCalc = totalCostCalc;
    }

    /**
     * @return the basicCalc
     */
    public double getBasicCalc() {
        return basicCalc;
    }

    /**
     * @param basicCalc the basicCalc to set
     */
    public void setBasicCalc(double basicCalc) {
        this.basicCalc = basicCalc;
    }

    /**
     * @return the prjPayCalc
     */
    public double getPrjPayCalc() {
        return prjPayCalc;
    }

    /**
     * @param prjPayCalc the prjPayCalc to set
     */
    public void setPrjPayCalc(double prjPayCalc) {
        this.prjPayCalc = prjPayCalc;
    }

    /**
     * @return the attAllowCalc
     */
    public double getAttAllowCalc() {
        return attAllowCalc;
    }

    /**
     * @param attAllowCalc the attAllowCalc to set
     */
    public void setAttAllowCalc(double attAllowCalc) {
        this.attAllowCalc = attAllowCalc;
    }

    /**
     * @return the longBonusCalc
     */
    public double getLongBonusCalc() {
        return longBonusCalc;
    }

    /**
     * @param longBonusCalc the longBonusCalc to set
     */
    public void setLongBonusCalc(double longBonusCalc) {
        this.longBonusCalc = longBonusCalc;
    }

    /**
     * @return the employerPfCalc
     */
    public double getEmployerPfCalc() {
        return employerPfCalc;
    }

    /**
     * @param employerPfCalc the employerPfCalc to set
     */
    public void setEmployerPfCalc(double employerPfCalc) {
        this.employerPfCalc = employerPfCalc;
    }

    /**
     * @return the healthCalc
     */
    public double getHealthCalc() {
        return healthCalc;
    }

    /**
     * @param healthCalc the healthCalc to set
     */
    public void setHealthCalc(double healthCalc) {
        this.healthCalc = healthCalc;
    }

    /**
     * @return the pfTaxCalc
     */
    public double getPfTaxCalc() {
        return pfTaxCalc;
    }

    /**
     * @param pfTaxCalc the pfTaxCalc to set
     */
    public void setPfTaxCalc(double pfTaxCalc) {
        this.pfTaxCalc = pfTaxCalc;
    }

    /**
     * @return the monthlySalary
     */
    public double getMonthlySalary() {
        return monthlySalary;
    }

    /**
     * @param monthlySalary the monthlySalary to set
     */
    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    /**
     * @return the daCalac
     */
    public double getDaCalac() {
        return daCalac;
    }

    /**
     * @param daCalac the daCalac to set
     */
    public void setDaCalac(double daCalac) {
        this.daCalac = daCalac;
    }

    /**
     * @return the hraCalc
     */
    public double getHraCalc() {
        return hraCalc;
    }

    /**
     * @param hraCalc the hraCalc to set
     */
    public void setHraCalc(double hraCalc) {
        this.hraCalc = hraCalc;
    }

    /**
     * @return the taCalc
     */
    public double getTaCalc() {
        return taCalc;
    }

    /**
     * @param taCalc the taCalc to set
     */
    public void setTaCalc(double taCalc) {
        this.taCalc = taCalc;
    }

    /**
     * @return the ccaCalc
     */
    public double getCcaCalc() {
        return ccaCalc;
    }

    /**
     * @param ccaCalc the ccaCalc to set
     */
    public void setCcaCalc(double ccaCalc) {
        this.ccaCalc = ccaCalc;
    }

    /**
     * @return the entertainmentCalc
     */
    public double getEntertainmentCalc() {
        return entertainmentCalc;
    }

    /**
     * @param entertainmentCalc the entertainmentCalc to set
     */
    public void setEntertainmentCalc(double entertainmentCalc) {
        this.entertainmentCalc = entertainmentCalc;
    }

    /**
     * @return the vehAllCal
     */
    public double getVehAllCal() {
        return vehAllCal;
    }

    /**
     * @param vehAllCal the vehAllCal to set
     */
    public void setVehAllCal(double vehAllCal) {
        this.vehAllCal = vehAllCal;
    }

    /**
     * @return the miscCal
     */
    public double getMiscCal() {
        return miscCal;
    }

    /**
     * @param miscCal the miscCal to set
     */
    public void setMiscCal(double miscCal) {
        this.miscCal = miscCal;
    }

    /**
     * @return the splAlloCalc
     */
    public double getSplAlloCalc() {
        return splAlloCalc;
    }

    /**
     * @param splAlloCalc the splAlloCalc to set
     */
    public void setSplAlloCalc(double splAlloCalc) {
        this.splAlloCalc = splAlloCalc;
    }

    /**
     * @return the healthDedCalc
     */
    public double getHealthDedCalc() {
        return healthDedCalc;
    }

    /**
     * @param healthDedCalc the healthDedCalc to set
     */
    public void setHealthDedCalc(double healthDedCalc) {
        this.healthDedCalc = healthDedCalc;
    }

    /**
     * @return the proftaxCalc
     */
    public double getProftaxCalc() {
        return proftaxCalc;
    }

    /**
     * @param proftaxCalc the proftaxCalc to set
     */
    public void setProftaxCalc(double proftaxCalc) {
        this.proftaxCalc = proftaxCalc;
    }

    /**
     * @return the tdsCalc
     */
    public double getTdsCalc() {
        return tdsCalc;
    }

    /**
     * @param tdsCalc the tdsCalc to set
     */
    public void setTdsCalc(double tdsCalc) {
        this.tdsCalc = tdsCalc;
    }

    /**
     * @return the employeePfCalc
     */
    public double getEmployeePfCalc() {
        return employeePfCalc;
    }

    /**
     * @param employeePfCalc the employeePfCalc to set
     */
    public void setEmployeePfCalc(double employeePfCalc) {
        this.employeePfCalc = employeePfCalc;
    }

    /**
     * @return the leavesApplied
     */
    public String getLeavesApplied() {
        return leavesApplied;
    }

    /**
     * @param leavesApplied the leavesApplied to set
     */
    public void setLeavesApplied(String leavesApplied) {
        this.leavesApplied = leavesApplied;
    }

    /**
     * @return the dateOfEmployement
     */
    public String getDateOfEmployement() {
        return dateOfEmployement;
    }

    /**
     * @param dateOfEmployement the dateOfEmployement to set
     */
    public void setDateOfEmployement(String dateOfEmployement) {
        this.dateOfEmployement = dateOfEmployement;
    }

    /**
     * @return the dateOfTermination
     */
    public String getDateOfTermination() {
        return dateOfTermination;
    }

    /**
     * @param dateOfTermination the dateOfTermination to set
     */
    public void setDateOfTermination(String dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

    /**
     * @return the payRollComments
     */
    public String getPayRollComments() {
        return payRollComments;
    }

    /**
     * @param payRollComments the payRollComments to set
     */
    public void setPayRollComments(String payRollComments) {
        this.payRollComments = payRollComments;
    }

    /**
     * @return the diffPF
     */
    public double getDiffPF() {
        return diffPF;
    }

    /**
     * @param diffPF the diffPF to set
     */
    public void setDiffPF(double diffPF) {
        this.diffPF = diffPF;
    }

    /**
     * @return the esiFlag
     */
    public boolean isEsiFlag() {
        return esiFlag;
    }

    /**
     * @param esiFlag the esiFlag to set
     */
    public void setEsiFlag(boolean esiFlag) {
        this.esiFlag = esiFlag;
    }

    /**
     * @return the earnedEmployeeesi
     */
    public double getEarnedEmployeeesi() {
        return earnedEmployeeesi;
    }

    /**
     * @param earnedEmployeeesi the earnedEmployeeesi to set
     */
    public void setEarnedEmployeeesi(double earnedEmployeeesi) {
        this.earnedEmployeeesi = earnedEmployeeesi;
    }

    /**
     * @return the earnedEmployeresi
     */
    public double getEarnedEmployeresi() {
        return earnedEmployeresi;
    }

    /**
     * @param earnedEmployeresi the earnedEmployeresi to set
     */
    public void setEarnedEmployeresi(double earnedEmployeresi) {
        this.earnedEmployeresi = earnedEmployeresi;
    }

    /**
     * @return the exemptionTypeMap
     */
    public Map getExemptionTypeMap() {
        return exemptionTypeMap;
    }

    /**
     * @param exemptionTypeMap the exemptionTypeMap to set
     */
    public void setExemptionTypeMap(Map exemptionTypeMap) {
        this.exemptionTypeMap = exemptionTypeMap;
    }

    /**
     * @return the inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * @param inputStream the inputStream to set
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * @return the outputStream
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * @param outputStream the outputStream to set
     */
    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the exemptionId
     */
    public String getExemptionId() {
        return exemptionId;
    }

    /**
     * @param exemptionId the exemptionId to set
     */
    public void setExemptionId(String exemptionId) {
        this.exemptionId = exemptionId;
    }

    /**
     * @return the exemptionType
     */
    public String getExemptionType() {
        return exemptionType;
    }

    /**
     * @param exemptionType the exemptionType to set
     */
    public void setExemptionType(String exemptionType) {
        this.exemptionType = exemptionType;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the isManager
     */
    public boolean isIsManager() {
        return isManager;
    }

    /**
     * @param isManager the isManager to set
     */
    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    /**
     * @return the isTeamLead
     */
    public boolean isIsTeamLead() {
        return isTeamLead;
    }

    /**
     * @param isTeamLead the isTeamLead to set
     */
    public void setIsTeamLead(boolean isTeamLead) {
        this.isTeamLead = isTeamLead;
    }

    /**
     * @return the release
     */
    public boolean isRelease() {
        return release;
    }

    /**
     * @param release the release to set
     */
    public void setRelease(boolean release) {
        this.release = release;
    }

    /**
     * @return the commissions
     */
    public boolean isCommissions() {
        return commissions;
    }

    /**
     * @param commissions the commissions to set
     */
    public void setCommissions(boolean commissions) {
        this.commissions = commissions;
    }

    /**
     * @return the settled
     */
    public boolean isSettled() {
        return settled;
    }

    /**
     * @param settled the settled to set
     */
    public void setSettled(boolean settled) {
        this.settled = settled;
    }

    /**
     * @return the dueAmount
     */
    public double getDueAmount() {
        return dueAmount;
    }

    /**
     * @param dueAmount the dueAmount to set
     */
    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the tempFlag
     */
    public String getTempFlag() {
        return tempFlag;
    }

    /**
     * @param tempFlag the tempFlag to set
     */
    public void setTempFlag(String tempFlag) {
        this.tempFlag = tempFlag;
    }

    /**
     * @return the isSixMonthsLock
     */
    public boolean getIsSixMonthsLock() {
        return isSixMonthsLock;
    }

    /**
     * @param isSixMonthsLock the isSixMonthsLock to set
     */
    public void setIsSixMonthsLock(boolean isSixMonthsLock) {
        this.isSixMonthsLock = isSixMonthsLock;
    }

    /**
     * @return the taxStartDate
     */
    public String getTaxStartDate() {
        return taxStartDate;
    }

    /**
     * @param taxStartDate the taxStartDate to set
     */
    public void setTaxStartDate(String taxStartDate) {
        this.taxStartDate = taxStartDate;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the noSalary
     */
    public boolean getNoSalary() {
        return noSalary;
    }

    /**
     * @param noSalary the noSalary to set
     */
    public void setNoSalary(boolean noSalary) {
        this.noSalary = noSalary;
    }

    /**
     * @return the paymentDate
     */
    public String getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * @return the empSaving6
     */
    public double getEmpSaving6() {
        return empSaving6;
    }

    /**
     * @param empSaving6 the empSaving6 to set
     */
    public void setEmpSaving6(double empSaving6) {
        this.empSaving6 = empSaving6;
    }

    /**
     * @return the practiceIdList
     */
    public List getPracticeIdList() {
        return practiceIdList;
    }

    /**
     * @param practiceIdList the practiceIdList to set
     */
    public void setPracticeIdList(List practiceIdList) {
        this.practiceIdList = practiceIdList;
    }

    /**
     * @return the wageFlag
     */
    public int getWageFlag() {
        return wageFlag;
    }

    /**
     * @param wageFlag the wageFlag to set
     */
    public void setWageFlag(int wageFlag) {
        this.wageFlag = wageFlag;
    }

    /**
     * @return the practiceId
     */
    public String getPracticeId() {
        return practiceId;
    }

    /**
     * @param practiceId the practiceId to set
     */
    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }

    /**
     * @return the practiceList
     */
    public List getPracticeList() {
        return practiceList;
    }

    /**
     * @param practiceList the practiceList to set
     */
    public void setPracticeList(List practiceList) {
        this.practiceList = practiceList;
    }

    /**
     * @return the bankReportFlag
     */
    public int getBankReportFlag() {
        return bankReportFlag;
    }

    /**
     * @param bankReportFlag the bankReportFlag to set
     */
    public void setBankReportFlag(int bankReportFlag) {
        this.bankReportFlag = bankReportFlag;
    }

    /**
     * @return the bankReportsResponse
     */
    public String getBankReportsResponse() {
        return bankReportsResponse;
    }

    /**
     * @param bankReportsResponse the bankReportsResponse to set
     */
    public void setBankReportsResponse(String bankReportsResponse) {
        this.bankReportsResponse = bankReportsResponse;
    }

    /**
     * @return the payslipResponse
     */
    public String getPayslipResponse() {
        return payslipResponse;
    }

    /**
     * @param payslipResponse the payslipResponse to set
     */
    public void setPayslipResponse(String payslipResponse) {
        this.payslipResponse = payslipResponse;
    }

    /**
     * @return the payslipFlag
     */
    public int getPayslipFlag() {
        return payslipFlag;
    }

    /**
     * @param payslipFlag the payslipFlag to set
     */
    public void setPayslipFlag(int payslipFlag) {
        this.payslipFlag = payslipFlag;
    }

    /**
     * @return the earnedNewGrossPay
     */
    public double getEarnedNewGrossPay() {
        return earnedNewGrossPay;
    }

    /**
     * @param earnedNewGrossPay the earnedNewGrossPay to set
     */
    public void setEarnedNewGrossPay(double earnedNewGrossPay) {
        this.earnedNewGrossPay = earnedNewGrossPay;
    }

    /**
     * @return the newGrossPay
     */
    public double getNewGrossPay() {
        return newGrossPay;
    }

    /**
     * @param newGrossPay the newGrossPay to set
     */
    public void setNewGrossPay(double newGrossPay) {
        this.newGrossPay = newGrossPay;
    }

    /**
     * @return the financialYear
     */
    public String getFinancialYear() {
        return financialYear;
    }

    /**
     * @param financialYear the financialYear to set
     */
    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    /**
     * @return the rentStartDate
     */
    public String getRentStartDate() {
        return rentStartDate;
    }

    /**
     * @param rentStartDate the rentStartDate to set
     */
    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    /**
     * @return the rentEndDate
     */
    public String getRentEndDate() {
        return rentEndDate;
    }

    /**
     * @param rentEndDate the rentEndDate to set
     */
    public void setRentEndDate(String rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    /**
     * @return the rentStartDateMap
     */
    public Map getRentStartDateMap() {
        return rentStartDateMap;
    }

    /**
     * @param rentStartDateMap the rentStartDateMap to set
     */
    public void setRentStartDateMap(Map rentStartDateMap) {
        this.rentStartDateMap = rentStartDateMap;
    }

    /**
     * @return the rentEndDateMap
     */
    public Map getRentEndDateMap() {
        return rentEndDateMap;
    }

    /**
     * @param rentEndDateMap the rentEndDateMap to set
     */
    public void setRentEndDateMap(Map rentEndDateMap) {
        this.rentEndDateMap = rentEndDateMap;
    }

    /**
     * @return the monthlyAmount
     */
    public String getMonthlyAmount() {
        return monthlyAmount;
    }

    /**
     * @param monthlyAmount the monthlyAmount to set
     */
    public void setMonthlyAmount(String monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    /**
     * @return the tefType
     */
    public String getTefType() {
        return tefType;
    }

    /**
     * @param tefType the tefType to set
     */
    public void setTefType(String tefType) {
        this.tefType = tefType;
    }

    /**
     * @return the selectType
     */
    public String getSelectType() {
        return selectType;
    }

    /**
     * @param selectType the selectType to set
     */
    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    /**
     * @return the validityStartDate
     */
    public String getValidityStartDate() {
        return validityStartDate;
    }

    /**
     * @param validityStartDate the validityStartDate to set
     */
    public void setValidityStartDate(String validityStartDate) {
        this.validityStartDate = validityStartDate;
    }

    /**
     * @return the validityEndDate
     */
    public String getValidityEndDate() {
        return validityEndDate;
    }

    /**
     * @param validityEndDate the validityEndDate to set
     */
    public void setValidityEndDate(String validityEndDate) {
        this.validityEndDate = validityEndDate;
    }

    /**
     * @return the noOfLeaves
     */
    public String getNoOfLeaves() {
        return noOfLeaves;
    }

    /**
     * @param noOfLeaves the noOfLeaves to set
     */
    public void setNoOfLeaves(String noOfLeaves) {
        this.noOfLeaves = noOfLeaves;
    }

    /**
     * @return the availWeekends
     */
    public String getAvailWeekends() {
        return availWeekends;
    }

    /**
     * @param availWeekends the availWeekends to set
     */
    public void setAvailWeekends(String availWeekends) {
        this.availWeekends = availWeekends;
    }

    /**
     * @return the availLeaves
     */
    public String getAvailLeaves() {
        return availLeaves;
    }

    /**
     * @param availLeaves the availLeaves to set
     */
    public void setAvailLeaves(String availLeaves) {
        this.availLeaves = availLeaves;
    }

    /**
     * @return the availHolidays
     */
    public String getAvailHolidays() {
        return availHolidays;
    }

    /**
     * @param availHolidays the availHolidays to set
     */
    public void setAvailHolidays(String availHolidays) {
        this.availHolidays = availHolidays;
    }

    /**
     * @return the releasedDate
     */
    public String getReleasedDate() {
        return releasedDate;
    }

    /**
     * @param releasedDate the releasedDate to set
     */
    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    /**
     * @return the payrollAddOrUpdate
     */
    public String getPayrollAddOrUpdate() {
        return payrollAddOrUpdate;
    }

    /**
     * @param payrollAddOrUpdate the payrollAddOrUpdate to set
     */
    public void setPayrollAddOrUpdate(String payrollAddOrUpdate) {
        this.payrollAddOrUpdate = payrollAddOrUpdate;
    }
}
