/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.ajax;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.mss.mirage.crm.accounts.AccountService;
import com.mss.mirage.crm.attachments.AttachmentService;
import com.mss.mirage.util.MailManager;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.Properties;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author miracle
 */
public class PayrollAjaxHandlerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    /**
     * Creating a reference variable for HttpServletRequest.
     */
    private HttpServletRequest httpServletRequest;
    /**
     * Creating a reference variable for HttpServletResponse.
     */
    private HttpServletResponse httpServletResponse;
    private String resultString;
    private String email;
    private String responseString;
    private String firstName;
    private String lastName;
    private String middleName;
    private String departmentId;
    private String titleId;
    private String shiftId;
    private String classificationId;
    private String locationId;
    private String regionId;
    private String gender;
    private String currStatus;
    private String isPfFlag;
    private String employerId;
    private String ssn;
    private String passportNo;
    private String pfAccount;
    private String birthDate;
    private String trainingPeriod;
    private String contractPeriod;
    private String dateOfJoining;
    private String dateOfEmployeement;
    private String trueBirthday;
    private String dateOfterminating;
    private String weddingDay;
    private String UANNo;
    private String adharNo;
    private String itgBatch;
    private String resonsForLeaving;
    private String address;
    private String corporateEmail;
    private String personalEmail;
    private String fathername;
    private String fatherTitle;
    private String city;
    private String state;
    private String zip;
    private String fatherPhone;
    private String homePhone;
    private String mobilePhone;
    private String basic;
    private String paymentType;
    private String da;
    private String bankAccountNo;
    private String hra;
    private String bankName;
    private String ta;
    private String employerPf;
    private String ra;
    private String employeePf;
    private String entertainment;
    private String life;
    private String kidsEducation;
    private String health;
    private String vehicleAllowance;
    private String professionalTax;
    private String cca;
    private String otherDeductions;
    private String miscPay;
    private String splAllowance;
    private String longTermBonus;
    private String grossPay;
    private String projectPay;
    private String variablePay;
    private String attendanceAllow;
    private String totalCost;
    private String onProjectInd;
    private String onProjectIndValue1;
    private String onProjectIndValue2;
    private String datePayRevised;
    private String lockAmtStartDate;
    private String onsiteInd;
    private String onsiteIndV;
    private String prevYtdSalary;
    private String empSaving1;
    private String empSaving2;
    private String empSaving3;
    private String empSaving4;
    private String empSaving5;
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
    private String empId;
    private String homeAddressId;
    private String payrollFlag;
    private String workPhone;
    private String userId;
    private String passwordForPdf;
    private int year;
    private int month;
    private int noOfDays;
    private String payRollId;
    private int wageId;
    private String employeeName;
    private String payPeriodEndDate;
    private String daysInMonth;
    private String daysWorked;
    private int workingDays;
    // private boolean freezePayroll;
    private String payrollDate;
    private String payRunId;
    private double netPaid;
    private double dedEmpPf;
    private double tds;
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
    private int noOfWeekedDays;
    private int noOfHolidays;
    private int freezePayroll;
    private boolean freezePayrollVal;
    private int leavesCount;
    private int daysVactaionFromBiometric;
    private String orgName;
    private int orgId;
    private double employeePfPayRollDetails;
    private double employeePfActualDetails;
    private int isBlock;
    private String tutionfees;
    private String hbLoanInterst;
    private String ppf;
    private String medicalIns;
    private String lifeIns;
    private String hraLifeInsuranceSavings;
    private String premium;
    private String eduInterest;
    private String fd;
    private String hbLoanPrinciple;
    private String mutualFunds;
    private String nsc;
    private String payPeriodStartDate;
    private String projectEndDate;
    //tds calcuations
    private String lifeInsurancePremium;
    private String housingLoanRepay;
    private String nscTds;
    private String ppfContribution;
    private String tutionFee;
    private String elss;
    private String postOfficeTerm;
    private String bankDepositTax;
    private String othersTDS;
    private String contributionToPf;
    private String npsEmployeeContr;
    private String totalTds;
    private String totalTdsDeductable;
    private String interstOnBorrowed;
    private String interstOnBorrowedDeductable;
    private String insuranceForParents;
    private String insuranceForParentsDeduc;
    private String insuranceOthers;
    private String insuranceOthersDeduc;
    private String interstOnEdu;
    private String interstOnHrAssumptions;
    private String interstOnHrAssumptionsInv;
    private double netPaidPayroll;
    private String repaymentComments;
    private double repaymentGross;
    private double repaymentNet;
    private double repaymentVariablePay;
    private double employeeesi;
    private double employeresi;
    private String expectedYearlyCost;
    private String licFromSal;
    private int tdsId;
    private int doRepaymentFlag;
    private boolean doRepaymentFlagVal;
    private String createdBy;
    private File file;
    private String fileFileName;
    private String empnameById;
    private String empNo;
    private String leavesApplied;
    private String dateOfEmployement;
    private String dateOfTermination;
    private String payRollComments;
    private double diffPF;
    private boolean esiFlag;
    private String esiFlagVal;
    private double earnedEmployeeesi;
    private double earnedEmployeresi;
    private int isManager;
    private int tefId;
    private boolean release;
    private boolean commissions;
    private boolean settled;
    private String startDate;
    private String endDate;
    private String comments;
   private int noDueId;
   private String selectedEmployess;
   private String apprComments;
  private int tempVar;
  private String noDueTableID;

   
    private int isTeamLead;
    
    private String overLayExemptionType;
    private String overLayStatus;
    private double overlaySavingAmount;
    private double overlayApprovedAmount;
    private int exemptionId;
    private String generatedPath;
    private AttachmentService attachmentService;
    private String attachmentLocation;
    private String objectType;
    private String paymentDateEmp;
    private double dueAmount;
    private String fromDate;
    private String toDate;
    private int isSixMonthsLock;
    
    
    private String lockAmtPeriod;
   private String modifiedBy;
   private String country;
   private int  isFixedSalary;
    private String loginId;
   
   private String validityDate;
   private String tefType;

   private String noSalary;
   private String empSaving6;
   private String releaseFor;
    private String releasedDate;
    private String releasedBy;
    private int Id;
     private String panNumber;
   private String ownerName;
   private int revisedMonth;
   private String revisedYear;
  private String practiceId;
  private String difference;
  private int organizationId;
  private List practiceList;
  private Double newGrossPay;
  private String exemptionName;
private String financialYear;
    private String rentStartDate;
    private String rentEndDate;
    private double monthlyAmount;
   private String updateFlag;
   private String policyNumber;
   private String licPremium;
   private File file1;
   private String file1FileName;
  private int isExists;
  private String attachmentLocation1;
  private Double empSavings;
  private int categoryId;
    private String houseAddress;
 public String getNoDueTableID() {
        return noDueTableID;
    }

    public void setNoDueTableID(String noDueTableID) {
        this.noDueTableID = noDueTableID;
    }

  
    public int getTempVar() {
        return tempVar;
    }

    public void setTempVar(int tempVar) {
        this.tempVar = tempVar;
    }
  
  
    public String getApprComments() {
        return apprComments;
    }

    public void setApprComments(String apprComments) {
        this.apprComments = apprComments;
    }

   
   
    public int getNoDueId() {
        return noDueId;
    }

    public void setNoDueId(int noDueId) {
        this.noDueId = noDueId;
    }

    public String getSelectedEmployess() {
        return selectedEmployess;
    }

    public void setSelectedEmployess(String selectedEmployess) {
        this.selectedEmployess = selectedEmployess;
    }
   
   
   
   
    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public int getIsTeamLead() {
        return isTeamLead;
    }

    public void setIsTeamLead(int isTeamLead) {
        this.isTeamLead = isTeamLead;
    }
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
   
   
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private String status;
    
    
    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public int getExemptionId() {
        return exemptionId;
    }

    public void setExemptionId(int exemptionId) {
        this.exemptionId = exemptionId;
    }

    public double getOverlayApprovedAmount() {
        return overlayApprovedAmount;
    }

    public void setOverlayApprovedAmount(double overlayApprovedAmount) {
        this.overlayApprovedAmount = overlayApprovedAmount;
    }

    public String getOverLayExemptionType() {
        return overLayExemptionType;
    }

    public void setOverLayExemptionType(String overLayExemptionType) {
        this.overLayExemptionType = overLayExemptionType;
    }

    public String getOverLayStatus() {
        return overLayStatus;
    }

    public void setOverLayStatus(String overLayStatus) {
        this.overLayStatus = overLayStatus;
    }

    public double getOverlaySavingAmount() {
        return overlaySavingAmount;
    }

    public void setOverlaySavingAmount(double overlaySavingAmount) {
        this.overlaySavingAmount = overlaySavingAmount;
    }

    public PayrollAjaxHandlerAction() {
    }

     public String getNoDueEndDate() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                //System.out.println("getEndDate()--->"+getEndDate());
                if (!getStartDate().equals("")) {
                    String pattern = "MM/dd/yyyy";
                    SimpleDateFormat format = new SimpleDateFormat(pattern);
                    java.util.Date date = format.parse(getStartDate()); // your date
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                   // System.out.println(month);
                    Calendar calendar = new GregorianCalendar(year, month, day, 23, 30, 0);
                    java.util.Date currentDate = calendar.getTime();
                    calendar.setTime(currentDate);
                    calendar.add(Calendar.MONTH, 3);
                    calendar.add(Calendar.DAY_OF_MONTH, -1);
                    java.util.Date nextMonth = calendar.getTime();
                    String empSavingsValues = " ";

                    //    System.out.println("getComments()--->"+getComments());




                    responseString = format.format(nextMonth);
                } else {
                    responseString = "";
                }
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ParseException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;

    }

      public String addNoDuesSettlement() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
               // System.out.println("getOverLayStatus()--->" + getOverLayStatus());
                String empSavingsValues = " ";

               // System.out.println("getComments()--->" + getComments());
                setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
               // System.out.println("EmpId==>" + getEmpId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().addNoDuesSettlement(this);

                


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
     
//    public String addNoDuesSettlement() {
//        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
//            try {
//               // System.out.println("getOverLayStatus()--->" + getOverLayStatus());
//                String empSavingsValues = " ";
//
//               // System.out.println("getComments()--->" + getComments());
//                setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
//               // System.out.println("EmpId==>" + getEmpId());
//                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
//                responseString = ServiceLocator.getPayrollAjaxHandlerService().addNoDuesSettlement(this);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.getWriter().write(responseString);
//            } catch (ServiceLocatorException ex) {
//                System.err.println(ex);
//            } catch (IOException ioe) {
//                System.err.println(ioe);
//            }
//        }
//        return null;
//    }
    
        public String addRemainderValuesNoDues() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
               // System.out.println("getOverLayStatus()--->" + getOverLayStatus());
                String empSavingsValues = " ";

               // System.out.println("getComments()--->" + getComments());
                setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
               // System.out.println("EmpId==>" + getEmpId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().addRemainderValuesNoDues(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
    
        public String getNoDueDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
               // System.out.println("id---->"+getNoDueId());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getNoDueDetails(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
   
        public String getEmployeesByDeptPayroll() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                  String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getEmployeesByDeptPayroll(getDepartmentId(),getStatus(),userRoleName,this);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
        
                
                public String approveOrRejectNoDues() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  System.out.println("getOverLayStatus()--->" + getOverLayStatus());
                String empSavingsValues = " ";
               // System.out.println("getComments()--->" + getComments());
                setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                //System.out.println("EmpId==>" + getEmpId());
                setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().approveOrRejectNoDues(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
                
     public String showEmpListNoDuesRemainder() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
               
                responseString = ServiceLocator.getPayrollAjaxHandlerService().showEmpListNoDuesRemainder(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
     
         public String checkForEmpNoDueRecordExistsOrNot() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
               setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
               setDepartmentId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString());
               //System.out.println("empid-->"+getEmpId());
              // System.out.println("DepartmentId-->"+getDepartmentId());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().checkForEmpNoDueRecordExistsOrNot(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
     
      public String noDuesRemainderClose() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
//               setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
//               setDepartmentId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_DEPT_ID).toString());
//                responseString = ServiceLocator.getPayrollAjaxHandlerService().checkForEmpNoDueRecordExistsOrNot(this);
                
              //  System.out.println("fromDate=="+getFromDate());
               // System.out.println("toDate=="+getToDate());
               // System.out.println("selectedEmployess=="+getSelectedEmployess());
                 responseString = ServiceLocator.getPayrollAjaxHandlerService().noDuesRemainderClose(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
    public String checkEmpExitsForPayroll() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

                responseString = "Success";
               // System.out.println(getEmail());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getEmpNumAndStatus(getEmail(), httpServletRequest);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String payrollAddDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
               // System.out.println("resultString-->" + getResultString());
                ServiceLocator.getPayrollAjaxHandlerService().setPayRollDetails(getResultString(), this);
//                System.out.println("get Id-->" + getEmpId());
//                System.out.println("getFirstName-->" + getFirstName());
//                System.out.println("getCorporateEmail-->" + getCorporateEmail());
//                System.out.println("getCorporateEmail-->" + getEmployerId());
//                System.out.println("getCorporateEmail-->" + getLifeInsureanceAmt());
//                System.out.println("getCorporateEmail-->" + getWagecomments());
//                System.out.println("getCorporateEmail-->" + getReferencecomments());
//                System.out.println("getCorporateEmail-->" + getAddress());
//                System.out.println("birthdate-->" + getBirthDate());
//                System.out.println("getOrgId-->" + getOrgId());

                setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setOrgName(DataSourceDataProvider.getInstance().getOrgNameById(getOrgId()));
            // System.out.println("getNewGrossPay-->" + getNewGrossPay());
                String payRollInsertionUpdate = ServiceLocator.getPayrollAjaxHandlerService().doAddPayRollDetails(getEmpId(), this);

               // System.out.println("payRollInsertionUpdate-->" + payRollInsertionUpdate);
                if (!"".equals(payRollInsertionUpdate)) {
                    responseString = payRollInsertionUpdate;
                }
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {

                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String updatePayrollEmployeeDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
               // System.out.println("resultString-->" + getPracticeId());
                ServiceLocator.getPayrollAjaxHandlerService().setPayRollEmployeeDetails(getResultString(), this);
               // System.out.println("getNames-->" + getFirstName() + getMiddleName() + getPassportNo() + getDateOfEmployeement());

                responseString = "Success";
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String updatePayrollDetailsForPayroll() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
               // System.out.println("resultString-->" + getResultString());
                ServiceLocator.getPayrollAjaxHandlerService().setEmployeePayRollDetails(getResultString(), this);
               // System.out.println("getNames-->" + getBasic() + getTa() + getBankName() + getRa());

                responseString = "Success";
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String updateInsuranceSavingsForPayroll() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
             //   System.out.println("resultString-->" + getResultString());
                ServiceLocator.getPayrollAjaxHandlerService().setInsuranceSavingsPayrollDetails(getResultString(), this);
              //  System.out.println("getNames-->" + getPrevYtdSalary() + getEmpSaving1() + getEmpSaving2());

                responseString = "Success";
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String updateOtherDetailsForPayroll() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
             //   System.out.println("resultString-->" + getResultString());
                ServiceLocator.getPayrollAjaxHandlerService().setOtherPayrollDetails(getResultString(), this);
            //    System.out.println("getNames-->" + getWagecomments() + getReferencecomments() + getWagecomments1());

                responseString = "Success";
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String updateContactDetailsForPayroll() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  System.out.println("resultString-->" + getResultString());
                ServiceLocator.getPayrollAjaxHandlerService().setPayrollContactDetails(getResultString(), this);
              //  System.out.println("getNames-->" + getAddress() + getMobilePhone() + getFatherTitle());

                responseString = "Success";
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String getEmployeesByDept() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getEmployeesByDept(getDepartmentId());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String doRunWages() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);

            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    if ("Payroll".equals(userRoleName)) {
                      //  System.out.println(getYear());
                     //   System.out.println(getMonth());
                     //   System.out.println(getNoOfDays());
                        //System.out.println(getNoOfHolidays());
                     //   System.out.println(getNoOfWeekedDays());
                     //   System.out.println(getWorkingDays());
                        
                        setCreatedBy( httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        Calendar now = Calendar.getInstance();
                        int currentMon = now.get(Calendar.MONTH) + 1;
                     //   System.out.println("currentMon-->"+currentMon);
                        //if(getMonth()<=currentMon){
                        responseString = ServiceLocator.getPayrollAjaxHandlerService().runEmpWagesForCurrentMonth(getYear(), getMonth(), this);
                       // }
                      //  else{
                      //  responseString = "<font style='color:red;'>Month selected for running of wages should not exceed current month</font>";
                      //  }
                       //  System.out.println("responseString------------>"+responseString);
                        httpServletResponse.setContentType("text/xml");
                        httpServletResponse.getWriter().write(responseString);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return null;
    }

    public String doFreezeWages() {


        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);

            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    if ("Payroll".equals(userRoleName)) {
                     //   System.out.println(getYear());
                      //  System.out.println(getMonth());
                        setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        responseString = ServiceLocator.getPayrollAjaxHandlerService().doFreezeWages(getYear(), getMonth(), this);
                      //  System.out.println("responseString------------>" + responseString);
                        httpServletResponse.setContentType("text/xml");
                        httpServletResponse.getWriter().write(responseString);
                    }

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());

                }
            }//END-Authorization Checking
        }//Close Session Checking
        return null;
    }

    public String updateYearAndDate() {


        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);

            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    if ("Payroll".equals(userRoleName)) {
                       // System.out.println(getPayRollId());
                       // System.out.println(getWageId());

                        setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        responseString = ServiceLocator.getPayrollAjaxHandlerService().doUpdateYearAndDate(getPayRollId(), getWageId(), this);


                        httpServletResponse.setContentType("text/xml");
                        httpServletResponse.getWriter().write(responseString);
                    }

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());

                }
            }//END-Authorization Checking
        }//Close Session Checking
        return null;
    }

    public String unfreezeEmpWageDetails() {


        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);

            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    if ("Payroll".equals(userRoleName)) {

                        setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        responseString = ServiceLocator.getPayrollAjaxHandlerService().unfreezeEmpWageDetails(this);
//                        System.out.println("responseString------------>" + responseString);
                        httpServletResponse.setContentType("text/xml");
                        httpServletResponse.getWriter().write(responseString);
                    }

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());

                }
            }//END-Authorization Checking
        }//Close Session Checking
        return null;
    }

    public String freezeEmpWageDetails() {


        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);

            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    if ("Payroll".equals(userRoleName)) {

                        setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        responseString = ServiceLocator.getPayrollAjaxHandlerService().freezeEmpWageDetails(this);
                       // System.out.println("responseString------------>" + responseString);
                        httpServletResponse.setContentType("text/xml");
                        httpServletResponse.getWriter().write(responseString);
                    }

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());

                }
            }//END-Authorization Checking
        }//Close Session Checking
        return null;
    }

    public String getDaysCountDetails() {


        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);

            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    if ("Payroll".equals(userRoleName)) {


                        responseString = ServiceLocator.getPayrollAjaxHandlerService().getDaysCountDetails(this);
                    //    System.out.println("responseString------------>" + responseString);
                        httpServletResponse.setContentType("text/xml");
                        httpServletResponse.getWriter().write(responseString);
                    }

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());

                }
            }//END-Authorization Checking
        }//Close Session Checking
        return null;
    }

    public String updateDaysCount() {


        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);

            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    if ("Payroll".equals(userRoleName)) {


                        responseString = ServiceLocator.getPayrollAjaxHandlerService().updateDaysCount(this);
                        //System.out.println("responseString------------>" + responseString);
                        httpServletResponse.setContentType("text/xml");
                        httpServletResponse.getWriter().write(responseString);
                    }

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());

                }
            }//END-Authorization Checking
        }//Close Session Checking
        return null;
    }

    public String updateAllEmpWageDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
               // System.out.println("resultString-->" + getResultString());
                ServiceLocator.getPayrollAjaxHandlerService().setEmpWageDetails(getResultString(), this);
                setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setOrgName(DataSourceDataProvider.getInstance().getOrgNameById(getOrgId()));
               // System.out.println("getOrgName-->" + getOrgName());
                String payRollInsertionUpdate = ServiceLocator.getPayrollAjaxHandlerService().updateAllEmpWageDetails(this);

              //  System.out.println("payRollInsertionUpdate-->" + payRollInsertionUpdate);
                if (!"".equals(payRollInsertionUpdate)) {
                    responseString = payRollInsertionUpdate;
                }
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String doGetDaysFortheSelectedMonthRunWages() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                Calendar mycal = new GregorianCalendar(getYear(), getMonth() - 1, 1);

// Get the number of days in that month

                Calendar c = Calendar.getInstance();
                c.set(Calendar.MONTH, getMonth() - 1);

                c.set(Calendar.YEAR, getYear());
                int monthMaxDays = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

              //  System.out.println("Calendar.DAY_OF_MONTH-->" + Calendar.DAY_OF_MONTH);
             //   System.out.println("monthMaxDays-->" + monthMaxDays);
             //   System.out.println("month-->" + (getMonth() - 1));
              //  System.out.println("year-->" + getYear());
                int noOfWeekEndDays = 0;
                for (int day = 1; day <= monthMaxDays; day++) {
                    c.set(getYear(), getMonth() - 1, day);
                    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                    if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
                        noOfWeekEndDays++;

                    }
                }


                responseString = monthMaxDays + "^" + noOfWeekEndDays;

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }
    //---------------------------------------------------

    public String getPayrollCheck() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
//                System.out.println("getYear-->" + getYear());
//                System.out.println("getMonth-->" + getMonth());
//                 System.out.println("getDifference()-->" + getDifference());
               
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getPayrollCheck(String.valueOf(getYear()), getMonth(),getDifference(),getOrganizationId());

// System.out.println("responseString"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    //clean payroll
    public String cleanPayroll() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  System.out.println("getYear-->" + getYear());
              //  System.out.println("getMonth-->" + getMonth());
                setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().cleanPayroll(getYear(), getMonth(),this);


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }
    //-----------------------------------------------------------------------------------------------------

    public String doUnFreezeWages() {


        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);

            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    if ("Payroll".equals(userRoleName)) {
                     //   System.out.println(getYear());
                     //   System.out.println(getMonth());
                        setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        boolean limitExceed = DataSourceDataProvider.getInstance().checkPayRollDateLimit(getYear(), getMonth());
                        if (!limitExceed) {
                            responseString = ServiceLocator.getPayrollAjaxHandlerService().doUnFreezeWages(getYear(), getMonth(), this);
                        } else {
                            responseString = "LimitExceeded";

                        }
                      //  System.out.println("responseString------------>" + responseString);
                        httpServletResponse.setContentType("text/xml");
                        httpServletResponse.getWriter().write(responseString);
                    }

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());

                }
            }//END-Authorization Checking
        }//Close Session Checking
        return null;
    }

   public String doReRunWages() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
int temp = 0;
            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    if ("Payroll".equals(userRoleName)) {
//                        System.out.println(getYear());
//                        System.out.println(getMonth());
//                        System.out.println(getNoOfDays());
//                        System.out.println(getNoOfHolidays());
//                        System.out.println(getNoOfWeekedDays());
//                        System.out.println(getWorkingDays());
//                        System.out.println("EmpID-->" + getEmpId());
                        setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        if(!"".equals(getEmpnameById()) && !"".equals(getEmpId()) ){
                  int empID = DataSourceDataProvider.getInstance().getEmpIdByLoginId(getEmpnameById());
                 int empIdbyNo = DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(getEmpId()));
                    
               if(empID != empIdbyNo){
                   temp = 1; 
               } 
               }
                     if(temp == 1){
                        responseString = "<font style='color:red;'>Employee No and name Doesn't match</font>";
                     }
                     else{
                         responseString = ServiceLocator.getPayrollAjaxHandlerService().rerunEmpWagesForCurrentMonth(getYear(), getMonth(), this);
                     }
                        
                      //                          System.out.println("responseString------------>"+responseString);
                        httpServletResponse.setContentType("text/xml");
                        httpServletResponse.getWriter().write(responseString);
                    }

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return null;
    }


    public String uploadLeavesExcel() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);

            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {


                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    //  responseString = "uploaded";
                    responseString = ServiceLocator.getPayrollAjaxHandlerService().doUploadLeavesExcelSheet(getYear(), getMonth(), this);
                    //  FileUtils.copyFile(getFile(), targetDirectory);


//                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
//                setRoleName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString());
//                if("Team".equalsIgnoreCase(getAddType())){
//                     setUserId(getUserId());
//                     setEmpTitle(DataSourceDataProvider.getInstance().getEmpTitleByLoginId(getUserId()));
//                     
//                     //setEmpTitle(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString());
//                    
//                }
//                else{
//                    setEmpTitle(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_TITLE).toString());
//               setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
//                }
//              
//                 //setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
//                
//                          // System.out.println("filename"+getFileFileName());
//                           // System.out.println("file location"+getAttachmentLocation());
//                           // System.out.println("filename"+getUploadFileName());
//                         /* if( !ServiceLocator.getReviewsService().getInsertReview(this)){
//                              setResultMessage("<font color=green size=2px>Review added successfully.</font>");
//                          }else {
//                              setResultMessage("<font color=red size=2px>Please try again.</font>");
//                          }*/
                    //  if(!ServiceLocator.getAjaxHandlerService().addReview(this)){
                    // responseString = "uploaded";
                    //}else {
                    //  responseString ="Error";
                    // }
                  //  System.out.println(responseString);
                    httpServletResponse.setContentType("text/html");
                    httpServletResponse.getWriter().write(responseString);
                    //}
//        }catch(ServiceLocatorException ex){
//            System.err.println(ex);
                } catch (IOException ioe) {
                    System.err.println(ioe);
                } catch (Exception ioe) {
                    System.err.println(ioe);
                }
            }
        }
        return null;
    }

    public String getTEFDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);

            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    if ("Payroll".equals(userRoleName)) {

                        responseString = ServiceLocator.getPayrollAjaxHandlerService().getTEFDetails(Integer.parseInt(getEmpId()));
//                          //System.out.println("responseString------------>"+responseString);
                        httpServletResponse.setContentType("text/xml");
                        httpServletResponse.getWriter().write(responseString);
                    }

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return null;
    }

    public String getNamesByDesignation() {

        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
//                System.out.println("getIsManager-->" + getIsManager());
//                System.out.println("getIsTeamLead-->" + getIsTeamLead());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getNamesByDesignation(getIsManager(), getIsTeamLead(), getDepartmentId());
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getEmpTefDetailsForOverlay() {

        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getEmpTefDetailsForOverlay(getTefId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

   

   public String addTaxAssumption() {
if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
try {
int temp=0;
// System.out.println("getOverLayExemptionType==>"+getOverLayExemptionType());
// System.out.println("getOverlaySavingAmount==>" + getOverlaySavingAmount());
// setCurrentTask(tasksVTO);
// String generatedPath = null;
String empId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
// String empId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SES_EMP_NO).toString();
// File zipName = DataSourceDataProvider.getInstance().getZipFiles(files,getFileFileName(),getFile1FileName(),empId);

empId=DataSourceDataProvider.getInstance().getLoginIdByEmpId(Integer.parseInt(empId));


setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
// System.out.println("EmpId==>" + getEmpId());
setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
// System.out.println("createdby==>" + getCreatedBy());

setOrgId(DataSourceDataProvider.getInstance().getOrgAccountIdByEmpID(Integer.parseInt(getEmpId())));
attachmentService = ServiceLocator.getAttachmentService();
String path=com.mss.mirage.util.Properties.getProperty("Attachments.Path")+"/"+"EmpPayroll/TaxSavingsDocs/"+getFinancialYear()+ "/"+empId;
java.util.Date date = new java.util.Date();

String monthName = null;

if(date.getMonth()==0) monthName="January";
else if(date.getMonth()==1) monthName="February";
else if(date.getMonth()==2) monthName="March";
else if(date.getMonth()==3) monthName="April";
else if(date.getMonth()==4) monthName="May";
else if(date.getMonth()==5) monthName="June";
else if(date.getMonth()==6) monthName="July";
else if(date.getMonth()==7) monthName="August";
else if(date.getMonth()==8) monthName="September";
else if(date.getMonth()==9) monthName="October";
else if(date.getMonth()==10) monthName="November";
else if(date.getMonth()==11) monthName="December";
String datetime=new SimpleDateFormat("ddMMMyyyy_hh_mm_ssa").format(new java.util.Date());
// setFileFileName(empId+"_"+datetime+"_"+ getFileFileName());
String currentDate= DateUtility.getInstance().getCurrentMySqlDateTime()+"";
//System.out.println("");
generatedPath = path+"/"+monthName;

if(Integer.parseInt(getOverLayExemptionType()) == 18){
if (getRentEndDate() != null && !"".equals(getRentEndDate())) {
int count=DataSourceDataProvider.getInstance().isExistedTaxExemtion(Integer.parseInt(getEmpId()),getRentStartDate(), getRentEndDate(), getOverLayExemptionType(),getFinancialYear());
if(count>0){
responseString="existed";
temp=1;
}
}
}
if(temp==0){
    if (getFileFileName() != null) {
File targetDirectory = new File(generatedPath+ "/" +empId+"_"+datetime+"_"+ getFileFileName());
 setAttachmentLocation(targetDirectory.toString());
FileUtils.copyFile(getFile(), targetDirectory);
    } else {
objectType = "NoFile";
setAttachmentLocation("");
//setFilepath("");
//attachmentName = "";
}
if (getFile1FileName() != null){
    File targetDirectory1 = new File(generatedPath+ "/" + "HRA_FORM12BB"+"-"+datetime+"_"+ getFile1FileName());
    FileUtils.copyFile(getFile1(), targetDirectory1);
//    List<String> files = new ArrayList<String>();
//             files.add(targetDirectory.getAbsolutePath());
//              files.add(targetDirectory1.getAbsolutePath());
 //System.out.println("getOverLayExemptionType==>" + targetDirectory1.toString());
// String zipName = getZipFiles(files,empId,generatedPath);
 setAttachmentLocation1(targetDirectory1.toString());
  setFile1FileName("HRA_FORM12BB" + "-" + getFile1FileName());
//System.out.println("getOverLayExemptionType==>" + getFile1FileName());
//setFileFileName("HRA_FORM12BB"+"-"+datetime+".zip");


} else {
objectType = "NoFile";
setAttachmentLocation1("");
//setFilepath("");
//attachmentName = "";
}


// setObjectType("Emp Reviews");

if (!ServiceLocator.getPayrollAjaxHandlerService().addTaxAssumption(this)) {
responseString = "uploaded";
} else {
responseString = "Error";
}
}
httpServletResponse.setContentType("text");
httpServletResponse.getWriter().write(responseString);
} catch (ServiceLocatorException ex) {
System.err.println(ex);
} catch (IOException ioe) {
System.err.println(ioe);
}
}
return null;
}


//   public String getZipFiles(List<String> files, String loginId,String generatedPath) throws ServiceLocatorException {
//        
//        FileOutputStream fos = null;
//        ZipOutputStream zipOut = null;
//        FileInputStream fis = null;
//        String timeStamp = DateUtility.getInstance().getCurrentDate();
//        String path = Properties.getProperty("Attachments.Path");
//       // String targetDirectory = generatedPath+ "/"+loginId +'-' + timeStamp + ".zip";
//       String targetDirectory = generatedPath+ "/" +"HRA_FORM12BB"+"-"+timeStamp+".zip";
//       
//        System.out.println("targetDirectory....."+targetDirectory);
//        try {
//           //  FileUtils.copyFile(getFile(), targetDirectory);
//          fos = new FileOutputStream(targetDirectory);
//            zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
//            for(String filePath:files){
//             System.out.println("filePath......."+filePath);
//                File input = new File(filePath);
//                fis = new FileInputStream(input);
//                ZipEntry ze = new ZipEntry(input.getName());
//               System.out.println("Zipping the file: "+input.getName());
//                zipOut.putNextEntry(ze);
//                byte[] tmp = new byte[4*1024];
//                int size = 0;
//                while((size = fis.read(tmp)) != -1){
//                    zipOut.write(tmp, 0, size);
//                }
//                zipOut.flush();
//                fis.close();
//            }
//              //System.out.println("Zipping the zipOut file: "+zipOut);
//            zipOut.close();
//          //  System.out.println("Done... Zipped the files...");
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }  finally{
//            try{
//                if(fos != null) fos.close();
//            } catch(Exception ex){
//                 
//            }
//        }
//        return targetDirectory;
//        
//    }

   public String upadteTaxExemption() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                File targetDirectory = null;
                 empId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                  empId=DataSourceDataProvider.getInstance().getLoginIdByEmpId(Integer.parseInt(empId));
                    String path=com.mss.mirage.util.Properties.getProperty("Attachments.Path")+"/"+"EmpPayroll/TaxSavingsDocs/"+getFinancialYear()+ "/"+empId;
                     java.util.Date date = new java.util.Date();
  
    String monthName = null;
	
	   if(date.getMonth()==0) monthName="January";
        else if(date.getMonth()==1) monthName="February";
        else if(date.getMonth()==2) monthName="March";
        else if(date.getMonth()==3) monthName="April";
        else if(date.getMonth()==4) monthName="May";
        else if(date.getMonth()==5) monthName="June";
        else if(date.getMonth()==6) monthName="July";
        else if(date.getMonth()==7) monthName="August";
        else if(date.getMonth()==8) monthName="September";
        else if(date.getMonth()==9) monthName="October";
        else if(date.getMonth()==10) monthName="November";
        else if(date.getMonth()==11) monthName="December";
          String datetime=new SimpleDateFormat("ddMMMyyyy_hh_mm_ssa").format(new java.util.Date());
           // setFileFileName(empId+"_"+datetime+"_"+ getFileFileName());
                    generatedPath = path+"/"+monthName;
                    
               
                 if(getFileFileName() != null ) {
                    attachmentService = ServiceLocator.getAttachmentService();
                  
                     targetDirectory = new File(generatedPath+ "/" + empId+"_"+datetime+"_"+ getFileFileName());
                  
                //  System.out.println("targetDirectory--->"+targetDirectory.toString());
                    setAttachmentLocation(targetDirectory.toString());
                    FileUtils.copyFile(getFile(), targetDirectory);
                 
                    //  setObjectType("Emp Reviews");
                    
                } else {
                    objectType = "NoFile";
                    setAttachmentLocation("");
                    //setFilepath("");
                    //attachmentName = "";
                }
                //  System.out.println("targetDirectory--->"+getFile1FileName());
                  if (getFile1FileName() != null){
//                            String fileLocation = DataSourceDataProvider.getInstance().getActualsFilePath(getId());
//                     
//                  if (!"".equals(fileLocation) && !"null".equals(fileLocation) && fileLocation != null && fileLocation.length() != 0) {
//                    File file = new File(fileLocation);
//                   // File imageFile = new File(file.getAbsolutePath());
//                   
//                   
//             System.out.println("File"+file);
////                    String filePath = file.getName();
////                     System.out.println("filePath"+filePath);
//                  
//                    if(getIsExists() == 1){
//                        System.out.println("filePath"+fileLocation);
//                        targetDirectory = new File(generatedPath+ "/" + empId+"_"+datetime+"_"+ getFileFileName());
//                        FileUtils.copyFile(getFile(), targetDirectory);
//                    }else{
//                         targetDirectory = file;
//                    }
//                  }
    File targetDirectory1 = new File(generatedPath+ "/" + "HRA_FORM12BB" + "-"+datetime+"_"+ getFile1FileName());
//    System.out.println("targetDirectory1"+targetDirectory1);
//    FileUtils.copyFile(getFile1(), targetDirectory1);
//    List<String> files = new ArrayList<String>();
//  
//    files.add(targetDirectory.getAbsolutePath());
//   
//              files.add(targetDirectory1.getAbsolutePath());
 //System.out.println("getOverLayExemptionType==>" + targetDirectory1.toString());
 //String zipName = getZipFiles(files,empId,generatedPath);
 setAttachmentLocation1(targetDirectory1.toString());
    FileUtils.copyFile(getFile1(), targetDirectory1);
  //setFileFileName("Form12BB.zip");
   setFile1FileName("HRA_FORM12BB" + "-"+ getFile1FileName());

}
              //  System.out.println("getOverLayStatus()--->" + getOverLayStatus());
                if (!ServiceLocator.getPayrollAjaxHandlerService().upadteTaxExemption(this)) {
                    responseString = "updated";
                } else {
                    responseString = "Error";
                }
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }



    public String upadtePayrollTaxExemption() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  System.out.println("getOverLayStatus()--->" + getOverLayStatus());
                String empSavingsValues = " ";

               // System.out.println("getComments()--->" + getComments());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                if (!ServiceLocator.getPayrollAjaxHandlerService().upadtePayrollTaxExemption(this)) {
                   responseString = "updated";
                  
                } else {
                    responseString = "Error";
                }
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }

    public String upadtePayrollTaxExemptionPayRollList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                //System.out.println("getOverLayStatus()--->" + getOverLayStatus());
                String empSavingsValues = " ";

               // System.out.println("getComments()--->" + getComments());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                if (!ServiceLocator.getPayrollAjaxHandlerService().upadtePayrollTaxExemption(this)) {

                    //empSavingsValues = DataSourceDataProvider.getInstance().getEmpSavings1234and5Values(Integer.parseInt(getEmpId()));
                    // System.out.println("updated$"+empSavingsValues);
                    responseString = "updated$" + getEmpSaving1() + "#" + getEmpSaving2() + "#" + getEmpSaving3() + "#" + getEmpSaving4() + "#" + getEmpSaving5();
                } else {
                    responseString = "Error$" + getEmpSaving1() + "#" + getEmpSaving2() + "#" + getEmpSaving3() + "#" + getEmpSaving4() + "#" + getEmpSaving5();
                }
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }

    public String checkCategoryWiseSavingsAmt() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
               // System.out.println("gettef--->" + getTefId());
                String empSavingsValues = " ";

//                System.out.println("approvedamt--->" + getOverlayApprovedAmount());
//                System.out.println("approvedamt--->" + getOverLayExemptionType());

                int category = DataSourceDataProvider.getInstance().getCategoryIdBasedOnExemptionId(Integer.parseInt(getOverLayExemptionType()));
              //  System.out.println("category--->" + category);
                if (!ServiceLocator.getPayrollAjaxHandlerService().checkCategoryWiseSavingsAmt(this, category)) {
                    responseString = "Success#" + category;
                } else {
                    responseString = "Fail#" + category;
                }

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }



    public String lockAmtListForAllEmps() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {

            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);

            if (AuthorizationManager.getInstance().isAuthorizedUser("PAYROLL_ACTIVITY", userRoleId)) {
                try {
                    if ("Payroll".equals(userRoleName)) {
//                        System.out.println("empNameById -->" + getEmpnameById());
//                        System.out.println("departy -->" + getDepartmentId());
//                        System.out.println("year -->" + getYear());
//                        System.out.println("month -->" + getMonth());
                      //   System.out.println("period -->" + getLockAmtPeriod());
                        String lockDate = getMonth() + "/01/" + getYear();
// System.out.println("lockDate -->"+lockDate);
                        java.util.Date referenceDate = DateUtility.getInstance().convertStringToMySql(lockDate);
                //  System.out.println("referenceDate -->"+referenceDate); 
                  
                        Calendar c = Calendar.getInstance();
                        c.setTime(referenceDate);
                        if(getLockAmtPeriod().equals("12"))
                        {
                            c.add(Calendar.MONTH, -12);
                        }
                        else if(getLockAmtPeriod().equals("6")){
                        c.add(Calendar.MONTH, -6);
                        }
                       String previousYearDate =DateUtility.getInstance().convertDateToMySql1(c.getTime());
                     //  System.out.println("previousYearDate -->"+previousYearDate);
                       setYear(Integer.parseInt(previousYearDate.split("-")[0]));
                       setMonth(Integer.parseInt(previousYearDate.split("-")[1]));
                      // System.out.println("--->setYear()"+getYear());
                        if (!"".equals(getEmpnameById()) && !"All".equals(getLockAmtPeriod())) {
                            responseString  = ServiceLocator.getPayrollAjaxHandlerService().getSingleEmpLockAmtDetails(this);
                            
                        } else {
                            responseString  = ServiceLocator.getPayrollAjaxHandlerService().getAllEmpsLockAmtDetails(this);
                            
                        }


                        httpServletResponse.setContentType("text");
                        httpServletResponse.getWriter().write(responseString);
                    }
                } catch (Exception ex) {

                    System.err.println(ex);
                    ex.printStackTrace();
                }

            }
        }
        return null;
    }
 public String generatePayRollPassword() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  System.out.println("gettef--->" + getTefId());
                String empSavingsValues = " ";

                //System.out.println("email--->" + getEmail());
               if(getEmail().equals(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMAIL).toString())){
int empId=Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                responseString=ServiceLocator.getPayrollAjaxHandlerService().generatePayRollPassword(empId,getEmail());
              
               }
               else{
                   responseString="<font color='red' size='2'>This is not yours mail id.</font>";
               }

                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
  /*
  * Get Employee number by LoginId
  * Date : 05/03/2016
  * Author : Santosh Kola
  */
 
  public String getEmployeeNumberByLoginId() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              
                responseString=ServiceLocator.getPayrollAjaxHandlerService().getEmployeeNumberByLoginId(getLoginId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
 
 //addTaxAssumptionFromPayroll
   public String addTaxAssumptionFromPayroll() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {

              //  System.out.println("getOverLayExemptionType==>" + getOverLayExemptionType());
                // System.out.println("getOverLayExemptionType==>"+getOverLayExemptionType());
               // System.out.println("getOverlaySavingAmount==>" + getOverlaySavingAmount());
                //  setCurrentTask(tasksVTO);
                // String  generatedPath = null;
                 attachmentService = ServiceLocator.getAttachmentService();
                     setEmpId(String.valueOf(DataSourceDataProvider.getInstance().getEmpIdByLoginId(getLoginId())));
                     empId=DataSourceDataProvider.getInstance().getLoginIdByEmpId(Integer.parseInt(getEmpId()));
                     String path=com.mss.mirage.util.Properties.getProperty("Attachments.Path")+"/"+"EmpPayroll/TaxSavingsDocs/"+getFinancialYear()+ "/"+empId;
                     java.util.Date date = new java.util.Date();
  
    String monthName = null;
	
	   if(date.getMonth()==0) monthName="January";
        else if(date.getMonth()==1) monthName="February";
        else if(date.getMonth()==2) monthName="March";
        else if(date.getMonth()==3) monthName="April";
        else if(date.getMonth()==4) monthName="May";
        else if(date.getMonth()==5) monthName="June";
        else if(date.getMonth()==6) monthName="July";
        else if(date.getMonth()==7) monthName="August";
        else if(date.getMonth()==8) monthName="September";
        else if(date.getMonth()==9) monthName="October";
        else if(date.getMonth()==10) monthName="November";
        else if(date.getMonth()==11) monthName="December";
          String datetime=new SimpleDateFormat("ddMMMyyyy_hh_mm_ssa").format(new java.util.Date());
           // setFileFileName(empId+"_"+datetime+"_"+ getFileFileName());
                    generatedPath = path+"/"+monthName;
                if (getFileFileName() != null) {
                   
                    File targetDirectory = new File(generatedPath + "/"+ empId+"_"+datetime+"_"+ getFileFileName());
                    setAttachmentLocation(targetDirectory.toString());
                    FileUtils.copyFile(getFile(), targetDirectory);
                    //  setObjectType("Emp Reviews");
                } else {
                    objectType = "NoFile";
                    setAttachmentLocation("");
                    //setFilepath("");
                    //attachmentName = "";
                }
                if (getFile1FileName() != null){
    File targetDirectory1 = new File(generatedPath+ "/" + "HRA_FORM12BB"+"-"+datetime+"_"+ getFile1FileName());
    FileUtils.copyFile(getFile1(), targetDirectory1);
//    List<String> files = new ArrayList<String>();
//             files.add(targetDirectory.getAbsolutePath());
//              files.add(targetDirectory1.getAbsolutePath());
 //System.out.println("getOverLayExemptionType==>" + targetDirectory1.toString());
// String zipName = getZipFiles(files,empId,generatedPath);
 setAttachmentLocation1(targetDirectory1.toString());
  setFile1FileName("HRA_FORM12BB" + "-" + getFile1FileName());
//System.out.println("getOverLayExemptionType==>" + getFile1FileName());
//setFileFileName("HRA_FORM12BB"+"-"+datetime+".zip");


} else {
objectType = "NoFile";
setAttachmentLocation1("");
//setFilepath("");
//attachmentName = "";
}
              // setEmpId(String.valueOf(DataSourceDataProvider.getInstance().getEmpNoByLoginId(getLoginId())));
                 setEmpId(String.valueOf(DataSourceDataProvider.getInstance().getEmpIdByLoginId(getLoginId())));
                //setEmpId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
               // System.out.println("EmpId==>" + getEmpId());
                setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
              //  System.out.println("createdby==>" + getCreatedBy());
setOrgId(DataSourceDataProvider.getInstance().getOrgAccountIdByEmpID(Integer.parseInt(getEmpId())));
 int temp=0;
if(getExemptionId() == 18){
             if (getRentEndDate() != null && !"".equals(getRentEndDate())) {
             int count=DataSourceDataProvider.getInstance().isExistedTaxExemtion(Integer.parseInt(getEmpId()),getRentStartDate(), getRentEndDate(), getExemptionId()+"",getFinancialYear());
                 if(count>0){
                     responseString="existed";
                     temp=1;
                 }
             }
        }    
             if(temp==0){
                if (!ServiceLocator.getPayrollAjaxHandlerService().addTaxAssumptionFromPayroll(this)) {
                    responseString = "uploaded";
                } else {
                    responseString = "Error";
                }
             }
                System.out.println("responseString---"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
  
    public String getCategoryByExemptionTypeId() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              
                responseString=String.valueOf(DataSourceDataProvider.getInstance().getCategoryIdBasedOnExemptionId(getExemptionId()));
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
    //getTaxAssumptionFromPayroll
        public String getTaxAssumptionFromPayroll() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              
                
                responseString=ServiceLocator.getPayrollAjaxHandlerService().getTaxAssumptionFromPayroll(getTefId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }
    public String updateTaxAssumptionFromPayroll() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
            //  System.out.println(getFileFileName()+"getFileName"+(getOverlayApprovedAmount() > 100000));
                 File targetDirectory = null;
                 empId=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                  loginId=DataSourceDataProvider.getInstance().getLoginIdByEmpId(Integer.parseInt(empId));
                    String path=com.mss.mirage.util.Properties.getProperty("Attachments.Path")+"/"+"EmpPayroll/TaxSavingsDocs/"+getFinancialYear()+ "/"+loginId;
                     java.util.Date date = new java.util.Date();
  
    String monthName = null;
	
	   if(date.getMonth()==0) monthName="January";
        else if(date.getMonth()==1) monthName="February";
        else if(date.getMonth()==2) monthName="March";
        else if(date.getMonth()==3) monthName="April";
        else if(date.getMonth()==4) monthName="May";
        else if(date.getMonth()==5) monthName="June";
        else if(date.getMonth()==6) monthName="July";
        else if(date.getMonth()==7) monthName="August";
        else if(date.getMonth()==8) monthName="September";
        else if(date.getMonth()==9) monthName="October";
        else if(date.getMonth()==10) monthName="November";
        else if(date.getMonth()==11) monthName="December";
          String datetime=new SimpleDateFormat("ddMMMyyyy_hh_mm_ssa").format(new java.util.Date());
           // setFileFileName(empId+"_"+datetime+"_"+ getFileFileName());
                    generatedPath = path+"/"+monthName;
                    
               
                 if(getFileFileName() != null ) {
                    attachmentService = ServiceLocator.getAttachmentService();
                  
                     targetDirectory = new File(generatedPath+ "/" + loginId+"_"+datetime+"_"+ getFileFileName());
                  
                 // System.out.println("targetDirectory--->"+targetDirectory.toString());
                    setAttachmentLocation(targetDirectory.toString());
                    FileUtils.copyFile(getFile(), targetDirectory);
                 
                    //  setObjectType("Emp Reviews");
                    
                } 
             //     System.out.println("targetDirectory---getFile1FileName>"+getFile1FileName());
                  if (getFile1FileName() != null){

    File targetDirectory1 = new File(generatedPath+ "/" + "HRA_FORM12BB" + "-"+datetime+"_"+ getFile1FileName());

 setAttachmentLocation1(targetDirectory1.toString());
    FileUtils.copyFile(getFile1(), targetDirectory1);
  //setFileFileName("Form12BB.zip");
   setFile1FileName("HRA_FORM12BB" + "-"+ getFile1FileName());

}
     
              
      //  System.out.println("........"+getLicPremium());      
              
              
                if (!ServiceLocator.getPayrollAjaxHandlerService().updateTaxAssumptionFromPayroll(this)) {
                 //  if(!"Applied".equals(getOverLayStatus())){
//                      if(Properties.getProperty("Mail.Flag").equals("1")){
//                              MailManager sendMail = new MailManager();
//                            //  System.out.println("--- sendMail");
//                        sendMail.sendTEFStatusMailToEmp(getOverLayStatus(),getEmpId(),getOverlayApprovedAmount(),"updated",getExemptionName(),getOverlaySavingAmount(),getTefType(),getComments());
//                        
//                        }
                      
//            }
                    responseString = "uploaded";
                } else {
                    responseString = "Error";
                }
               // System.out.println("resposeString"+responseString);
              //  responseString=ServiceLocator.getPayrollAjaxHandlerService().getTaxAssumptionFromPayroll(getTefId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return null;
    }    
        public String getBlockedSalDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
          // System.out.println("in action method-->");
               // System.out.println("getMonth-->" + getMonth());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getBlockedSalDetails(getYear(),getMonth());


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

      public String doAddPayslipReleases() {
    
       
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            try {
                    
                String userName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString(); 
                setReleasedBy(userName);
                setReleasedDate(DateUtility.getInstance().getCurrentMySqlDate());
               // System.out.println("getYear----------->"+getYear());
                String releasedFor = getYear()+"-"+getMonth()+"-01";
//                System.out.println("userRoleName----------->"+userRoleName);   
//                System.out.println("releasedFor----------->"+releasedFor);  
//                System.out.println("getReleasedBy----------->"+getReleasedBy());  
//                System.out.println("getReleasedDate----------->"+getReleasedDate());  
//                System.out.println("getStatus----------->"+getStatus());  
                
                 boolean doHaveWages = DataSourceDataProvider.getInstance().checkForPayrollDateForReleases(getMonth(), getYear());
                 boolean isMonthExists =DataSourceDataProvider.getInstance().checkIfSameMonthsRecordExists(getMonth(), getYear(),getStatus());
                   
//                System.out.println("doHaveWages----------->"+doHaveWages);  
//                    System.out.println("doHaveWages == true----------->"+(doHaveWages == true));  
                if(doHaveWages == true && isMonthExists == false){
                
                responseString = ServiceLocator.getPayrollAjaxHandlerService().doAddPayslipReleases(releasedFor,this);
                }
                else if(isMonthExists == true){
                     responseString = "exists";
                }
                else{
                    responseString = "Nowage";
                }
                     
                //  responseString=ServiceLocator.getPayrollAjaxHandlerService().getTaxAssumptionFromPayroll(getTefId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString); 
            
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    
                }
            }//END-Authorization Checking
        return null;
        }//Close Session Checking
        
 
    
           public String doEditPayslipReleases() {
    
       
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            try {
                    
                String userName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString(); 
                setReleasedBy(userName);
                setReleasedDate(DateUtility.getInstance().getCurrentMySqlDate());
//                System.out.println("editgetYear----------->"+getYear());
               String releasedFor = getYear()+"-"+getMonth()+"-01";
//                System.out.println("edituserRoleName----------->"+userRoleName);   
//                System.out.println("editreleasedFor----------->"+releasedFor);  
//                System.out.println("editgetReleasedBy----------->"+getReleasedBy());  
//                System.out.println("editgetReleasedDate----------->"+getReleasedDate());  
//                System.out.println("editgetStatus----------->"+getStatus());  
                 boolean doHaveWages = DataSourceDataProvider.getInstance().checkForPayrollDateForReleases(getMonth(), getYear());
                 boolean isMonthExists =DataSourceDataProvider.getInstance().checkIfSameMonthsRecordExists(getMonth(), getYear(),getStatus());
                 
                 
                if(doHaveWages == true && isMonthExists == false ){
                    //System.out.println("doHaveWages----------->"+doHaveWages);  
                responseString = ServiceLocator.getPayrollAjaxHandlerService().doEditPayslipReleases(releasedFor,this);
                }
                else if(isMonthExists == true){
                     responseString = "exists";
                }
                  else{
                     responseString = "Nowage";
                }    
                //  responseString=ServiceLocator.getPayrollAjaxHandlerService().getTaxAssumptionFromPayroll(getTefId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString); 
            
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    
                }
            }//END-Authorization Checking
        return null;
        }//Close Session Checking
        
 public String calculateActualDetails() {
    
       
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            String workingCountry = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
            String userRoleName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
            //System.out.println("workingCountry----->"+workingCountry);
            try {
                
                responseString = ServiceLocator.getPayrollAjaxHandlerService().calculateActualDetails(getResultString());
                   
                //  responseString=ServiceLocator.getPayrollAjaxHandlerService().getTaxAssumptionFromPayroll(getTefId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString); 
            
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    
                }
            }//END-Authorization Checking
        return null;
        }
  
public String getSumOfHRA() throws ServiceLocatorException{
        int  sum = 0;
          if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
           //  String employee = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
             //   System.out.println("employee--"+employee);  
          //   int empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(employee);
              //    System.out.println("empId--"+empId);     
                int empId =Integer.parseInt(getEmpId());
                  responseString = ServiceLocator.getPayrollAjaxHandlerService().getSumOfHRA(empId,getOverlaySavingAmount(),getPaymentDateEmp(),getFinancialYear()); 
                
              //  responseString=ServiceLocator.getPayrollAjaxHandlerService().getTaxAssumptionFromPayroll(getTefId());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (ServiceLocatorException ex) {
                System.err.println(ex);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        
        return null;
        
    }


        public String getRevisedSalDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
        //  System.out.println("in action method-->");
            //   System.out.println(getRevisedYear()+"getMonth-->" + getRevisedMonth());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getRevisedSalDetails(getRevisedYear(),getRevisedMonth());
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }
         public String getTdsCalculation() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  System.out.println("getYear-->" + getYear());
              //  System.out.println("getMonth-->" + getMonth());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getTdsCalculation(String.valueOf(getYear()), getMonth());


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }
         
        public String doGetEmployeeNames() {
        try {
            // System.out.println("email--"+getEmail());
        //   System.out.println("SELECT CONCAT(TRIM(FName),'.',TRIM(LName)) AS FullName,LoginId FROM tblEmployee WHERE (LName LIKE '" + employeeName + "%' OR FName LIKE '" + employeeName + "%')");
            // responseString = ServiceLocator.getAjaxHandlerService().getConsultantList("SELECT Id,Email2,CellPhoneNo,AvailableFrom,IsReject,RatePerHour,WorkAuthorization FROM tblRecConsultant where Email2 LIKE '" + getEmail() + "%'");
          //  responseString = ServiceLocator.getPayrollAjaxHandlerService().doGetEmployeeNames("SELECT CONCAT(TRIM(FName),'.',TRIM(LName)) AS FullName,LoginId FROM tblEmployee WHERE (LName LIKE '" + employeeName + "%' OR FName LIKE '" + employeeName + "%')");
              responseString = ServiceLocator.getPayrollAjaxHandlerService().doGetEmployeeNames("SELECT CONCAT(TRIM(FName),'.',TRIM(LName)) AS FullName,LoginId FROM tblEmployee WHERE (LName LIKE '" + employeeName + "%' OR FName LIKE '" + employeeName + "%' OR EmpNo LIKE '" + employeeName + "%') AND Country='India'");
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

public String deleteTefEmpDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  System.out.println("getYear-->" + getYear());
              //  System.out.println("getMonth-->" + getMonth());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().deleteTefEmpDetails(getId());

             //   System.out.println("responseString---"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

public String getEmpSavingsValidate(){
     if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
               
             //   System.out.println("getEmpSavings-->" + getTaxableIncome());
              //  System.out.println("getMonth-->" + getMonth());
             
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getEmpSavingsValidate(getEmpSavings(),getTefId(),getTaxableIncome(),getCategoryId(),getExemptionId(),getEmpId());


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null; 
}
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     *
     * This method is used to set the Servlet Response
     * @param httpServletResponse
     */
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public String getUANNo() {
        return UANNo;
    }

    public void setUANNo(String UANNo) {
        this.UANNo = UANNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdharNo() {
        return adharNo;
    }

    public void setAdharNo(String adharNo) {
        this.adharNo = adharNo;
    }

    public String getAttendanceAllow() {
        return attendanceAllow;
    }

    public void setAttendanceAllow(String attendanceAllow) {
        this.attendanceAllow = attendanceAllow;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCca() {
        return cca;
    }

    public void setCca(String cca) {
        this.cca = cca;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public String getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(String contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    public String getCorporateEmail() {
        return corporateEmail;
    }

    public void setCorporateEmail(String corporateEmail) {
        this.corporateEmail = corporateEmail;
    }

    public String getCurrStatus() {
        return currStatus;
    }

    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus;
    }

    public String getDa() {
        return da;
    }

    public void setDa(String da) {
        this.da = da;
    }

    public String getDateOfEmployeement() {
        return dateOfEmployeement;
    }

    public void setDateOfEmployeement(String dateOfEmployeement) {
        this.dateOfEmployeement = dateOfEmployeement;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getDateOfterminating() {
        return dateOfterminating;
    }

    public void setDateOfterminating(String dateOfterminating) {
        this.dateOfterminating = dateOfterminating;
    }

    public String getDatePayRevised() {
        return datePayRevised;
    }

    public void setDatePayRevised(String datePayRevised) {
        this.datePayRevised = datePayRevised;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmpSaving1() {
        return empSaving1;
    }

    public void setEmpSaving1(String empSaving1) {
        this.empSaving1 = empSaving1;
    }

    public String getEmpSaving2() {
        return empSaving2;
    }

    public void setEmpSaving2(String empSaving2) {
        this.empSaving2 = empSaving2;
    }

    public String getEmployeePf() {
        return employeePf;
    }

    public void setEmployeePf(String employeePf) {
        this.employeePf = employeePf;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public String getEmployerPf() {
        return employerPf;
    }

    public void setEmployerPf(String employerPf) {
        this.employerPf = employerPf;
    }

    public String getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment;
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public String getFatherTitle() {
        return fatherTitle;
    }

    public void setFatherTitle(String fatherTitle) {
        this.fatherTitle = fatherTitle;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGeneralcomments() {
        return generalcomments;
    }

    public void setGeneralcomments(String generalcomments) {
        this.generalcomments = generalcomments;
    }

    public String getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(String grossPay) {
        this.grossPay = grossPay;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getHealthInsuranceAmt() {
        return healthInsuranceAmt;
    }

    public void setHealthInsuranceAmt(String healthInsuranceAmt) {
        this.healthInsuranceAmt = healthInsuranceAmt;
    }

    public String getHealthInsuranceAnnual() {
        return healthInsuranceAnnual;
    }

    public void setHealthInsuranceAnnual(String healthInsuranceAnnual) {
        this.healthInsuranceAnnual = healthInsuranceAnnual;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getHra() {
        return hra;
    }

    public void setHra(String hra) {
        this.hra = hra;
    }

    public String getIsPfFlag() {
        return isPfFlag;
    }

    public void setIsPfFlag(String isPfFlag) {
        this.isPfFlag = isPfFlag;
    }

    public String getItgBatch() {
        return itgBatch;
    }

    public void setItgBatch(String itgBatch) {
        this.itgBatch = itgBatch;
    }

    public String getKidsEducation() {
        return kidsEducation;
    }

    public void setKidsEducation(String kidsEducation) {
        this.kidsEducation = kidsEducation;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getLifeInsureanceAmt() {
        return lifeInsureanceAmt;
    }

    public void setLifeInsureanceAmt(String lifeInsureanceAmt) {
        this.lifeInsureanceAmt = lifeInsureanceAmt;
    }

    public String getLifeInsureanceAnnual() {
        return lifeInsureanceAnnual;
    }

    public void setLifeInsureanceAnnual(String lifeInsureanceAnnual) {
        this.lifeInsureanceAnnual = lifeInsureanceAnnual;
    }

    public String getLifeInsureancePolicy() {
        return lifeInsureancePolicy;
    }

    public void setLifeInsureancePolicy(String lifeInsureancePolicy) {
        this.lifeInsureancePolicy = lifeInsureancePolicy;
    }

    public String getLifeInsureanceTerm() {
        return lifeInsureanceTerm;
    }

    public void setLifeInsureanceTerm(String lifeInsureanceTerm) {
        this.lifeInsureanceTerm = lifeInsureanceTerm;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLongTermBonus() {
        return longTermBonus;
    }

    public void setLongTermBonus(String longTermBonus) {
        this.longTermBonus = longTermBonus;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiscPay() {
        return miscPay;
    }

    public void setMiscPay(String miscPay) {
        this.miscPay = miscPay;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getOnProjectInd() {
        return onProjectInd;
    }

    public void setOnProjectInd(String onProjectInd) {
        this.onProjectInd = onProjectInd;
    }

    public String getOnProjectIndValue1() {
        return onProjectIndValue1;
    }

    public void setOnProjectIndValue1(String onProjectIndValue1) {
        this.onProjectIndValue1 = onProjectIndValue1;
    }

    public String getOnProjectIndValue2() {
        return onProjectIndValue2;
    }

    public void setOnProjectIndValue2(String onProjectIndValue2) {
        this.onProjectIndValue2 = onProjectIndValue2;
    }

    public String getOnsiteInd() {
        return onsiteInd;
    }

    public void setOnsiteInd(String onsiteInd) {
        this.onsiteInd = onsiteInd;
    }

    public String getOnsiteIndV() {
        return onsiteIndV;
    }

    public void setOnsiteIndV(String onsiteIndV) {
        this.onsiteIndV = onsiteIndV;
    }

    public String getOtherDeductions() {
        return otherDeductions;
    }

    public void setOtherDeductions(String otherDeductions) {
        this.otherDeductions = otherDeductions;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPfAccount() {
        return pfAccount;
    }

    public void setPfAccount(String pfAccount) {
        this.pfAccount = pfAccount;
    }

    public String getPrevYtdSalary() {
        return prevYtdSalary;
    }

    public void setPrevYtdSalary(String prevYtdSalary) {
        this.prevYtdSalary = prevYtdSalary;
    }

    public String getProfessionalTax() {
        return professionalTax;
    }

    public void setProfessionalTax(String professionalTax) {
        this.professionalTax = professionalTax;
    }

    public String getProjectPay() {
        return projectPay;
    }

    public void setProjectPay(String projectPay) {
        this.projectPay = projectPay;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getReferencecomments() {
        return referencecomments;
    }

    public void setReferencecomments(String referencecomments) {
        this.referencecomments = referencecomments;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getResonsForLeaving() {
        return resonsForLeaving;
    }

    public void setResonsForLeaving(String resonsForLeaving) {
        this.resonsForLeaving = resonsForLeaving;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getSplAllowance() {
        return splAllowance;
    }

    public void setSplAllowance(String splAllowance) {
        this.splAllowance = splAllowance;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getTrainingPeriod() {
        return trainingPeriod;
    }

    public void setTrainingPeriod(String trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }

    public String getTrueBirthday() {
        return trueBirthday;
    }

    public void setTrueBirthday(String trueBirthday) {
        this.trueBirthday = trueBirthday;
    }

    public String getVariablePay() {
        return variablePay;
    }

    public void setVariablePay(String variablePay) {
        this.variablePay = variablePay;
    }

    public String getVehicleAllowance() {
        return vehicleAllowance;
    }

    public void setVehicleAllowance(String vehicleAllowance) {
        this.vehicleAllowance = vehicleAllowance;
    }

    public String getWagecomments() {
        return wagecomments;
    }

    public void setWagecomments(String wagecomments) {
        this.wagecomments = wagecomments;
    }

    public String getWagecomments1() {
        return wagecomments1;
    }

    public void setWagecomments1(String wagecomments1) {
        this.wagecomments1 = wagecomments1;
    }

    public String getWeddingDay() {
        return weddingDay;
    }

    public void setWeddingDay(String weddingDay) {
        this.weddingDay = weddingDay;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getHomeAddressId() {
        return homeAddressId;
    }

    public void setHomeAddressId(String homeAddressId) {
        this.homeAddressId = homeAddressId;
    }

    public String getPayrollFlag() {
        return payrollFlag;
    }

    public void setPayrollFlag(String payrollFlag) {
        this.payrollFlag = payrollFlag;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswordForPdf() {
        return passwordForPdf;
    }

    public void setPasswordForPdf(String passwordForPdf) {
        this.passwordForPdf = passwordForPdf;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public int getNoOfHolidays() {
        return noOfHolidays;
    }

    public void setNoOfHolidays(int noOfHolidays) {
        this.noOfHolidays = noOfHolidays;
    }

    public int getNoOfWeekedDays() {
        return noOfWeekedDays;
    }

    public void setNoOfWeekedDays(int noOfWeekedDays) {
        this.noOfWeekedDays = noOfWeekedDays;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPayRollId() {
        return payRollId;
    }

    public void setPayRollId(String payRollId) {
        this.payRollId = payRollId;
    }

    public int getWageId() {
        return wageId;
    }

    public void setWageId(int wageId) {
        this.wageId = wageId;
    }

    public double getDiffTDSLiabilitiesBonus() {
        return diffTDSLiabilitiesBonus;
    }

    public void setDiffTDSLiabilitiesBonus(double diffTDSLiabilitiesBonus) {
        this.diffTDSLiabilitiesBonus = diffTDSLiabilitiesBonus;
    }

    public double getDiffTDSLiabilitiesSalary() {
        return diffTDSLiabilitiesSalary;
    }

    public void setDiffTDSLiabilitiesSalary(double diffTDSLiabilitiesSalary) {
        this.diffTDSLiabilitiesSalary = diffTDSLiabilitiesSalary;
    }

    public double getYtdExpTaxFree() {
        return ytdExpTaxFree;
    }

    public void setYtdExpTaxFree(double ytdExpTaxFree) {
        this.ytdExpTaxFree = ytdExpTaxFree;
    }

    public double getYtdGross() {
        return ytdGross;
    }

    public void setYtdGross(double ytdGross) {
        this.ytdGross = ytdGross;
    }

    public double getYtdLifeInsurance() {
        return ytdLifeInsurance;
    }

    public void setYtdLifeInsurance(double ytdLifeInsurance) {
        this.ytdLifeInsurance = ytdLifeInsurance;
    }

    public double getYtdLongterm() {
        return ytdLongterm;
    }

    public void setYtdLongterm(double ytdLongterm) {
        this.ytdLongterm = ytdLongterm;
    }

    public double getYtdOthersMisc() {
        return ytdOthersMisc;
    }

    public void setYtdOthersMisc(double ytdOthersMisc) {
        this.ytdOthersMisc = ytdOthersMisc;
    }

    public double getYtdPf() {
        return ytdPf;
    }

    public void setYtdPf(double ytdPf) {
        this.ytdPf = ytdPf;
    }

    public double getYtdProffTax() {
        return ytdProffTax;
    }

    public void setYtdProffTax(double ytdProffTax) {
        this.ytdProffTax = ytdProffTax;
    }

    public double getYtdProjectPay() {
        return ytdProjectPay;
    }

    public void setYtdProjectPay(double ytdProjectPay) {
        this.ytdProjectPay = ytdProjectPay;
    }

    public double getYtdRa() {
        return ytdRa;
    }

    public void setYtdRa(double ytdRa) {
        this.ytdRa = ytdRa;
    }

    public double getYtdSavings1Reported() {
        return ytdSavings1Reported;
    }

    public void setYtdSavings1Reported(double ytdSavings1Reported) {
        this.ytdSavings1Reported = ytdSavings1Reported;
    }

    public double getYtdSavings2Reported() {
        return ytdSavings2Reported;
    }

    public void setYtdSavings2Reported(double ytdSavings2Reported) {
        this.ytdSavings2Reported = ytdSavings2Reported;
    }

    public double getYtdTDSCollected() {
        return ytdTDSCollected;
    }

    public void setYtdTDSCollected(double ytdTDSCollected) {
        this.ytdTDSCollected = ytdTDSCollected;
    }

    public double getYtdTDSLiabilitiesBonus() {
        return ytdTDSLiabilitiesBonus;
    }

    public void setYtdTDSLiabilitiesBonus(double ytdTDSLiabilitiesBonus) {
        this.ytdTDSLiabilitiesBonus = ytdTDSLiabilitiesBonus;
    }

    public double getYtdTDSLiabilitiesSalary() {
        return ytdTDSLiabilitiesSalary;
    }

    public void setYtdTDSLiabilitiesSalary(double ytdTDSLiabilitiesSalary) {
        this.ytdTDSLiabilitiesSalary = ytdTDSLiabilitiesSalary;
    }

    public double getYtdTDSOnCommm() {
        return ytdTDSOnCommm;
    }

    public void setYtdTDSOnCommm(double ytdTDSOnCommm) {
        this.ytdTDSOnCommm = ytdTDSOnCommm;
    }

    public double getYtdTDSonSalary() {
        return ytdTDSonSalary;
    }

    public void setYtdTDSonSalary(double ytdTDSonSalary) {
        this.ytdTDSonSalary = ytdTDSonSalary;
    }

    public double getYtdTa() {
        return ytdTa;
    }

    public void setYtdTa(double ytdTa) {
        this.ytdTa = ytdTa;
    }

    public double getYtdTaxableCommission() {
        return ytdTaxableCommission;
    }

    public void setYtdTaxableCommission(double ytdTaxableCommission) {
        this.ytdTaxableCommission = ytdTaxableCommission;
    }

    public double getYtdTaxableSalary() {
        return ytdTaxableSalary;
    }

    public void setYtdTaxableSalary(double ytdTaxableSalary) {
        this.ytdTaxableSalary = ytdTaxableSalary;
    }

    public int getFreezePayroll() {
        return freezePayroll;
    }

    public void setFreezePayroll(int freezePayroll) {
        this.freezePayroll = freezePayroll;
    }

    public int getLeavesCount() {
        return leavesCount;
    }

    public void setLeavesCount(int leavesCount) {
        this.leavesCount = leavesCount;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public double getBonusCommission() {
        return bonusCommission;
    }

    public void setBonusCommission(double bonusCommission) {
        this.bonusCommission = bonusCommission;
    }

    public String getDaysHolidays() {
        return daysHolidays;
    }

    public void setDaysHolidays(String daysHolidays) {
        this.daysHolidays = daysHolidays;
    }

    public String getDaysInMonth() {
        return daysInMonth;
    }

    public void setDaysInMonth(String daysInMonth) {
        this.daysInMonth = daysInMonth;
    }

    public String getDaysProject() {
        return daysProject;
    }

    public void setDaysProject(String daysProject) {
        this.daysProject = daysProject;
    }

    public String getDaysVacation() {
        return daysVacation;
    }

    public void setDaysVacation(String daysVacation) {
        this.daysVacation = daysVacation;
    }

    public String getDaysWeekends() {
        return daysWeekends;
    }

    public void setDaysWeekends(String daysWeekends) {
        this.daysWeekends = daysWeekends;
    }

    public String getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(String daysWorked) {
        this.daysWorked = daysWorked;
    }

    public double getDedCorporateLoan() {
        return dedCorporateLoan;
    }

    public void setDedCorporateLoan(double dedCorporateLoan) {
        this.dedCorporateLoan = dedCorporateLoan;
    }

    public double getDedEmpPf() {
        return dedEmpPf;
    }

    public void setDedEmpPf(double dedEmpPf) {
        this.dedEmpPf = dedEmpPf;
    }

    public double getDedHealth() {
        return dedHealth;
    }

    public void setDedHealth(double dedHealth) {
        this.dedHealth = dedHealth;
    }

    public double getDedIncomeTax() {
        return dedIncomeTax;
    }

    public void setDedIncomeTax(double dedIncomeTax) {
        this.dedIncomeTax = dedIncomeTax;
    }

    public double getDedLife() {
        return dedLife;
    }

    public void setDedLife(double dedLife) {
        this.dedLife = dedLife;
    }

    public double getDedOthers() {
        return dedOthers;
    }

    public void setDedOthers(double dedOthers) {
        this.dedOthers = dedOthers;
    }

    public double getDedProfessionalTax() {
        return dedProfessionalTax;
    }

    public void setDedProfessionalTax(double dedProfessionalTax) {
        this.dedProfessionalTax = dedProfessionalTax;
    }

    public double getEarnedBasic() {
        return earnedBasic;
    }

    public void setEarnedBasic(double earnedBasic) {
        this.earnedBasic = earnedBasic;
    }

    public double getEarnedCCa() {
        return earnedCCa;
    }

    public void setEarnedCCa(double earnedCCa) {
        this.earnedCCa = earnedCCa;
    }

    public double getEarnedDa() {
        return earnedDa;
    }

    public void setEarnedDa(double earnedDa) {
        this.earnedDa = earnedDa;
    }

    public double getEarnedEmployerPf() {
        return earnedEmployerPf;
    }

    public void setEarnedEmployerPf(double earnedEmployerPf) {
        this.earnedEmployerPf = earnedEmployerPf;
    }

    public double getEarnedEntertainment() {
        return earnedEntertainment;
    }

    public void setEarnedEntertainment(double earnedEntertainment) {
        this.earnedEntertainment = earnedEntertainment;
    }

    public double getEarnedFood() {
        return earnedFood;
    }

    public void setEarnedFood(double earnedFood) {
        this.earnedFood = earnedFood;
    }

    public double getEarnedHealth() {
        return earnedHealth;
    }

    public void setEarnedHealth(double earnedHealth) {
        this.earnedHealth = earnedHealth;
    }

    public double getEarnedHra() {
        return earnedHra;
    }

    public void setEarnedHra(double earnedHra) {
        this.earnedHra = earnedHra;
    }

    public double getEarnedKidsEducation() {
        return earnedKidsEducation;
    }

    public void setEarnedKidsEducation(double earnedKidsEducation) {
        this.earnedKidsEducation = earnedKidsEducation;
    }

    public double getEarnedLaundary() {
        return earnedLaundary;
    }

    public void setEarnedLaundary(double earnedLaundary) {
        this.earnedLaundary = earnedLaundary;
    }

    public double getEarnedLife() {
        return earnedLife;
    }

    public void setEarnedLife(double earnedLife) {
        this.earnedLife = earnedLife;
    }

    public double getEarnedLongTermBonus() {
        return earnedLongTermBonus;
    }

    public void setEarnedLongTermBonus(double earnedLongTermBonus) {
        this.earnedLongTermBonus = earnedLongTermBonus;
    }

    public double getEarnedMaidServices() {
        return earnedMaidServices;
    }

    public void setEarnedMaidServices(double earnedMaidServices) {
        this.earnedMaidServices = earnedMaidServices;
    }

    public double getEarnedMiscPay() {
        return earnedMiscPay;
    }

    public void setEarnedMiscPay(double earnedMiscPay) {
        this.earnedMiscPay = earnedMiscPay;
    }

    public double getEarnedProjectPay() {
        return earnedProjectPay;
    }

    public void setEarnedProjectPay(double earnedProjectPay) {
        this.earnedProjectPay = earnedProjectPay;
    }

    public double getEarnedRa() {
        return earnedRa;
    }

    public void setEarnedRa(double earnedRa) {
        this.earnedRa = earnedRa;
    }

    public double getEarnedTa() {
        return earnedTa;
    }

    public void setEarnedTa(double earnedTa) {
        this.earnedTa = earnedTa;
    }

    public double getEarnedVehicleAllowance() {
        return earnedVehicleAllowance;
    }

    public void setEarnedVehicleAllowance(double earnedVehicleAllowance) {
        this.earnedVehicleAllowance = earnedVehicleAllowance;
    }

    public double getEarnedattallowance() {
        return earnedattallowance;
    }

    public void setEarnedattallowance(double earnedattallowance) {
        this.earnedattallowance = earnedattallowance;
    }

    public double getEarnedsplallowance() {
        return earnedsplallowance;
    }

    public void setEarnedsplallowance(double earnedsplallowance) {
        this.earnedsplallowance = earnedsplallowance;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public boolean isFreezePayrollVal() {
        return freezePayrollVal;
    }

    public void setFreezePayrollVal(boolean freezePayrollVal) {
        this.freezePayrollVal = freezePayrollVal;
    }

    public double getGrossPayActualDetails() {
        return grossPayActualDetails;
    }

    public void setGrossPayActualDetails(double grossPayActualDetails) {
        this.grossPayActualDetails = grossPayActualDetails;
    }

    public double getGrossPayPayRollDetails() {
        return grossPayPayRollDetails;
    }

    public void setGrossPayPayRollDetails(double grossPayPayRollDetails) {
        this.grossPayPayRollDetails = grossPayPayRollDetails;
    }

    public double getMaidServices() {
        return maidServices;
    }

    public void setMaidServices(double maidServices) {
        this.maidServices = maidServices;
    }

    public double getNetPaid() {
        return netPaid;
    }

    public void setNetPaid(double netPaid) {
        this.netPaid = netPaid;
    }

    public double getNetPaidActualDetails() {
        return netPaidActualDetails;
    }

    public void setNetPaidActualDetails(double netPaidActualDetails) {
        this.netPaidActualDetails = netPaidActualDetails;
    }

    public double getOtherAdditions() {
        return otherAdditions;
    }

    public void setOtherAdditions(double otherAdditions) {
        this.otherAdditions = otherAdditions;
    }

    public String getPayPeriodEndDate() {
        return payPeriodEndDate;
    }

    public void setPayPeriodEndDate(String payPeriodEndDate) {
        this.payPeriodEndDate = payPeriodEndDate;
    }

    public String getPayRunId() {
        return payRunId;
    }

    public void setPayRunId(String payRunId) {
        this.payRunId = payRunId;
    }

    public String getPayrollDate() {
        return payrollDate;
    }

    public void setPayrollDate(String payrollDate) {
        this.payrollDate = payrollDate;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getTds() {
        return tds;
    }

    public void setTds(double tds) {
        this.tds = tds;
    }

    public double getTdsDeduction() {
        return tdsDeduction;
    }

    public void setTdsDeduction(double tdsDeduction) {
        this.tdsDeduction = tdsDeduction;
    }

    public String getVactionsAvailable() {
        return vactionsAvailable;
    }

    public void setVactionsAvailable(String vactionsAvailable) {
        this.vactionsAvailable = vactionsAvailable;
    }

    public double getEmployeePfActualDetails() {
        return employeePfActualDetails;
    }

    public void setEmployeePfActualDetails(double employeePfActualDetails) {
        this.employeePfActualDetails = employeePfActualDetails;
    }

    public double getEmployeePfPayRollDetails() {
        return employeePfPayRollDetails;
    }

    public void setEmployeePfPayRollDetails(double employeePfPayRollDetails) {
        this.employeePfPayRollDetails = employeePfPayRollDetails;
    }

    public int getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(int isBlock) {
        this.isBlock = isBlock;
    }

    public String getEduInterest() {
        return eduInterest;
    }

    public void setEduInterest(String eduInterest) {
        this.eduInterest = eduInterest;
    }

    public String getFd() {
        return fd;
    }

    public void setFd(String fd) {
        this.fd = fd;
    }

    public String getHbLoanInterst() {
        return hbLoanInterst;
    }

    public void setHbLoanInterst(String hbLoanInterst) {
        this.hbLoanInterst = hbLoanInterst;
    }

    public String getHbLoanPrinciple() {
        return hbLoanPrinciple;
    }

    public void setHbLoanPrinciple(String hbLoanPrinciple) {
        this.hbLoanPrinciple = hbLoanPrinciple;
    }

    public String getHraLifeInsuranceSavings() {
        return hraLifeInsuranceSavings;
    }

    public void setHraLifeInsuranceSavings(String hraLifeInsuranceSavings) {
        this.hraLifeInsuranceSavings = hraLifeInsuranceSavings;
    }

    public String getLifeIns() {
        return lifeIns;
    }

    public void setLifeIns(String lifeIns) {
        this.lifeIns = lifeIns;
    }

    public String getMedicalIns() {
        return medicalIns;
    }

    public void setMedicalIns(String medicalIns) {
        this.medicalIns = medicalIns;
    }

    public String getMutualFunds() {
        return mutualFunds;
    }

    public void setMutualFunds(String mutualFunds) {
        this.mutualFunds = mutualFunds;
    }

    public String getNsc() {
        return nsc;
    }

    public void setNsc(String nsc) {
        this.nsc = nsc;
    }

    public String getPpf() {
        return ppf;
    }

    public void setPpf(String ppf) {
        this.ppf = ppf;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getTutionfees() {
        return tutionfees;
    }

    public void setTutionfees(String tutionfees) {
        this.tutionfees = tutionfees;
    }

    public int getDaysVactaionFromBiometric() {
        return daysVactaionFromBiometric;
    }

    public void setDaysVactaionFromBiometric(int daysVactaionFromBiometric) {
        this.daysVactaionFromBiometric = daysVactaionFromBiometric;
    }

    public String getPayPeriodStartDate() {
        return payPeriodStartDate;
    }

    public void setPayPeriodStartDate(String payPeriodStartDate) {
        this.payPeriodStartDate = payPeriodStartDate;
    }

    public String getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public String getBankDepositTax() {
        return bankDepositTax;
    }

    public void setBankDepositTax(String bankDepositTax) {
        this.bankDepositTax = bankDepositTax;
    }

    public String getContributionToPf() {
        return contributionToPf;
    }

    public void setContributionToPf(String contributionToPf) {
        this.contributionToPf = contributionToPf;
    }

    public String getElss() {
        return elss;
    }

    public void setElss(String elss) {
        this.elss = elss;
    }

    public String getHousingLoanRepay() {
        return housingLoanRepay;
    }

    public void setHousingLoanRepay(String housingLoanRepay) {
        this.housingLoanRepay = housingLoanRepay;
    }

    public String getInsuranceForParents() {
        return insuranceForParents;
    }

    public void setInsuranceForParents(String insuranceForParents) {
        this.insuranceForParents = insuranceForParents;
    }

    public String getInsuranceForParentsDeduc() {
        return insuranceForParentsDeduc;
    }

    public void setInsuranceForParentsDeduc(String insuranceForParentsDeduc) {
        this.insuranceForParentsDeduc = insuranceForParentsDeduc;
    }

    public String getInsuranceOthers() {
        return insuranceOthers;
    }

    public void setInsuranceOthers(String insuranceOthers) {
        this.insuranceOthers = insuranceOthers;
    }

    public String getInsuranceOthersDeduc() {
        return insuranceOthersDeduc;
    }

    public void setInsuranceOthersDeduc(String insuranceOthersDeduc) {
        this.insuranceOthersDeduc = insuranceOthersDeduc;
    }

    public String getInterstOnBorrowed() {
        return interstOnBorrowed;
    }

    public void setInterstOnBorrowed(String interstOnBorrowed) {
        this.interstOnBorrowed = interstOnBorrowed;
    }

    public String getInterstOnBorrowedDeductable() {
        return interstOnBorrowedDeductable;
    }

    public void setInterstOnBorrowedDeductable(String interstOnBorrowedDeductable) {
        this.interstOnBorrowedDeductable = interstOnBorrowedDeductable;
    }

    public String getInterstOnEdu() {
        return interstOnEdu;
    }

    public void setInterstOnEdu(String interstOnEdu) {
        this.interstOnEdu = interstOnEdu;
    }

    public String getInterstOnHrAssumptions() {
        return interstOnHrAssumptions;
    }

    public void setInterstOnHrAssumptions(String interstOnHrAssumptions) {
        this.interstOnHrAssumptions = interstOnHrAssumptions;
    }

    public String getLifeInsurancePremium() {
        return lifeInsurancePremium;
    }

    public void setLifeInsurancePremium(String lifeInsurancePremium) {
        this.lifeInsurancePremium = lifeInsurancePremium;
    }

    public String getNpsEmployeeContr() {
        return npsEmployeeContr;
    }

    public void setNpsEmployeeContr(String npsEmployeeContr) {
        this.npsEmployeeContr = npsEmployeeContr;
    }

    public String getNscTds() {
        return nscTds;
    }

    public void setNscTds(String nscTds) {
        this.nscTds = nscTds;
    }

    public String getOthersTDS() {
        return othersTDS;
    }

    public void setOthersTDS(String othersTDS) {
        this.othersTDS = othersTDS;
    }

    public String getPostOfficeTerm() {
        return postOfficeTerm;
    }

    public void setPostOfficeTerm(String postOfficeTerm) {
        this.postOfficeTerm = postOfficeTerm;
    }

    public String getPpfContribution() {
        return ppfContribution;
    }

    public void setPpfContribution(String ppfContribution) {
        this.ppfContribution = ppfContribution;
    }

    public String getTotalTds() {
        return totalTds;
    }

    public void setTotalTds(String totalTds) {
        this.totalTds = totalTds;
    }

    public String getTotalTdsDeductable() {
        return totalTdsDeductable;
    }

    public void setTotalTdsDeductable(String totalTdsDeductable) {
        this.totalTdsDeductable = totalTdsDeductable;
    }

    public String getTutionFee() {
        return tutionFee;
    }

    public void setTutionFee(String tutionFee) {
        this.tutionFee = tutionFee;
    }

    public double getNetPaidPayroll() {
        return netPaidPayroll;
    }

    public void setNetPaidPayroll(double netPaidPayroll) {
        this.netPaidPayroll = netPaidPayroll;
    }

    public String payrollBackUpDetails() {
      //  System.out.println("payrollBackUpDetails-------------");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
              //  System.out.println("resultString-->" + getResultString());
                ServiceLocator.getPayrollAjaxHandlerService().setPayRollDetails(getResultString(), this);
//                System.out.println("get Id-->" + getEmpId());
//                System.out.println("getFirstName-->" + getFirstName());
//                System.out.println("getCorporateEmail-->" + getCorporateEmail());
//                System.out.println("getCorporateEmail-->" + getEmployerId());
//                System.out.println("getCorporateEmail-->" + getLifeInsureanceAmt());
//                System.out.println("getCorporateEmail-->" + getWagecomments());
//                System.out.println("getCorporateEmail-->" + getReferencecomments());
//                System.out.println("birthdate-->" + getBirthDate());
//                System.out.println("getOrgId-->" + getOrgId());

                setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                setOrgName(DataSourceDataProvider.getInstance().getOrgNameById(getOrgId()));
               // System.out.println("getOrgName-->" + getOrgName());
                String payRollBackUpInsertion = ServiceLocator.getPayrollAjaxHandlerService().doBackUpPayRollDetails(getEmpId(), this);

              //  System.out.println("payRollBackUpInsertion-->" + payRollBackUpInsertion);
                if (!"".equals(payRollBackUpInsertion)) {
                    responseString = payRollBackUpInsertion;
                }
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String getPayrollHistory() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
//                System.out.println("getDepartmentId-->" + getDepartmentId());
//                System.out.println("getEmpnameById-->" + getEmpnameById());
            //    System.out.println("getEmpNo-->" + getEmpNo());
                int empId = 0;
                if ("".equals(getEmpNo())) {
                    empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(getEmpnameById());
                } else {
                    empId = DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(getEmpNo()));
                }
             //   System.out.println("empId--->"+empId);
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getPayrollHistory(empId);
               // System.out.println("responseString---->" + responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String getWagesHistory() {
      //  System.out.println("getWagesHistory 1");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
//                System.out.println("getDepartmentId-->" + getDepartmentId());
//                System.out.println("getEmpnameById-->" + getEmpnameById());
//                System.out.println("getEmpNo-->" + getEmpNo());
                int empId = 0;
                if ("".equals(getEmpNo())) {
                    empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(getEmpnameById());
                } else {
                    empId = DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(getEmpNo()));
                }
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getWagesHistory(empId);


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String getPayRollHistoryEmployeeDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
               // System.out.println("emptId-->" + getEmpId());
              //  System.out.println("modifiedDate-->" + httpServletRequest.getParameter("modifiedDate"));
                String modifiedDate = httpServletRequest.getParameter("modifiedDate");
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getPayRollHistoryEmployeeDetails(Integer.parseInt(getEmpId()), modifiedDate);


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }

    public String getWagesHistoryEmployeeDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
//                System.out.println("emptId-->" + getEmpId());
//                System.out.println("modifiedDate-->" + httpServletRequest.getParameter("modifiedDate"));
                String modifiedDate = httpServletRequest.getParameter("modifiedDate");
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getWagesHistoryEmployeeDetails(Integer.parseInt(getEmpId()), modifiedDate);


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }
     public String getRepaymentDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
//                System.out.println("getYear-->" + getYear());
//                System.out.println("getMonth-->" + getMonth());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getRepaymentDetails(String.valueOf(getYear()), getMonth());


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }
     public String getRepaymentReason() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
//                System.out.println("getYear-->" + getYear());
//                System.out.println("getMonth-->" + getMonth());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().getRepaymentReason(getWageId());


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
    }
     public String tdsCalculation() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
//                System.out.println("getYear-->" + getYear());
//                System.out.println("getMonth-->" + getMonth());
                responseString = ServiceLocator.getPayrollAjaxHandlerService().tdsCalculation(this);


                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                System.err.println(ex);
                ex.printStackTrace();
            }

        }
        return null;
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

    public String getInterstOnHrAssumptionsInv() {
        return interstOnHrAssumptionsInv;
    }

    public void setInterstOnHrAssumptionsInv(String interstOnHrAssumptionsInv) {
        this.interstOnHrAssumptionsInv = interstOnHrAssumptionsInv;
    }

    public double getEmployeeesi() {
        return employeeesi;
    }

    public void setEmployeeesi(double employeeesi) {
        this.employeeesi = employeeesi;
    }

    public double getEmployeresi() {
        return employeresi;
    }

    public void setEmployeresi(double employeresi) {
        this.employeresi = employeresi;
    }

    public String getExpectedYearlyCost() {
        return expectedYearlyCost;
    }

    public void setExpectedYearlyCost(String expectedYearlyCost) {
        this.expectedYearlyCost = expectedYearlyCost;
    }

    public String getLicFromSal() {
        return licFromSal;
    }

    public void setLicFromSal(String licFromSal) {
        this.licFromSal = licFromSal;
    }

    public int getTdsId() {
        return tdsId;
    }

    public void setTdsId(int tdsId) {
        this.tdsId = tdsId;
    }

    public int getDoRepaymentFlag() {
        return doRepaymentFlag;
    }

    public void setDoRepaymentFlag(int doRepaymentFlag) {
        this.doRepaymentFlag = doRepaymentFlag;
    }

    public boolean isDoRepaymentFlagVal() {
        return doRepaymentFlagVal;
    }

    public void setDoRepaymentFlagVal(boolean doRepaymentFlagVal) {
        this.doRepaymentFlagVal = doRepaymentFlagVal;
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
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the fileFileName
     */
    public String getFileFileName() {
        return fileFileName;
    }

    /**
     * @param fileFileName the fileFileName to set
     */
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    /**
     * @return the empnameById
     */
    public String getEmpnameById() {
        return empnameById;
    }

    /**
     * @param empnameById the empnameById to set
     */
    public void setEmpnameById(String empnameById) {
        this.empnameById = empnameById;
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

    public String getLeavesApplied() {
        return leavesApplied;
    }

    public void setLeavesApplied(String leavesApplied) {
        this.leavesApplied = leavesApplied;
    }

    public String getDateOfEmployement() {
        return dateOfEmployement;
    }

    public void setDateOfEmployement(String dateOfEmployement) {
        this.dateOfEmployement = dateOfEmployement;
    }

    public String getDateOfTermination() {
        return dateOfTermination;
    }

    public void setDateOfTermination(String dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

    public double getDiffPF() {
        return diffPF;
    }

    public void setDiffPF(double diffPF) {
        this.diffPF = diffPF;
    }

    public boolean isEsiFlag() {
        return esiFlag;
    }

    public void setEsiFlag(boolean esiFlag) {
        this.esiFlag = esiFlag;
    }

    public String getPayRollComments() {
        return payRollComments;
    }

    public void setPayRollComments(String payRollComments) {
        this.payRollComments = payRollComments;
    }

    public String getEsiFlagVal() {
        return esiFlagVal;
    }

    public void setEsiFlagVal(String esiFlagVal) {
        this.esiFlagVal = esiFlagVal;
    }

    public double getEarnedEmployeeesi() {
        return earnedEmployeeesi;
    }

    public void setEarnedEmployeeesi(double earnedEmployeeesi) {
        this.earnedEmployeeesi = earnedEmployeeesi;
    }

    public double getEarnedEmployeresi() {
        return earnedEmployeresi;
    }

    public void setEarnedEmployeresi(double earnedEmployeresi) {
        this.earnedEmployeresi = earnedEmployeresi;
    }

    public int getTefId() {
        return tefId;
    }

    public void setTefId(int tefId) {
        this.tefId = tefId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getAttachmentLocation() {
        return attachmentLocation;
    }

    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }

    public String getGeneratedPath() {
        return generatedPath;
    }

    public void setGeneratedPath(String generatedPath) {
        this.generatedPath = generatedPath;
    }

    public String getPaymentDateEmp() {
        return paymentDateEmp;
    }

    public void setPaymentDateEmp(String paymentDateEmp) {
        this.paymentDateEmp = paymentDateEmp;
    }

    public String getEmpSaving3() {
        return empSaving3;
    }

    public void setEmpSaving3(String empSaving3) {
        this.empSaving3 = empSaving3;
    }

    public String getEmpSaving4() {
        return empSaving4;
    }

    public void setEmpSaving4(String empSaving4) {
        this.empSaving4 = empSaving4;
    }

    public String getEmpSaving5() {
        return empSaving5;
    }

    public void setEmpSaving5(String empSaving5) {
        this.empSaving5 = empSaving5;
    }

    public double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
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

    public boolean isCommissions() {
        return commissions;
    }

    public void setCommissions(boolean commissions) {
        this.commissions = commissions;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }

    public boolean isSettled() {
        return settled;
    }

    public void setSettled(boolean settled) {
        this.settled = settled;
    }

    public String getLockAmtStartDate() {
        return lockAmtStartDate;
    }

    public void setLockAmtStartDate(String lockAmtStartDate) {
        this.lockAmtStartDate = lockAmtStartDate;
    }

    public int getIsSixMonthsLock() {
        return isSixMonthsLock;
    }

    public void setIsSixMonthsLock(int isSixMonthsLock) {
        this.isSixMonthsLock = isSixMonthsLock;
    }

    public String getLockAmtPeriod() {
        return lockAmtPeriod;
    }

    public void setLockAmtPeriod(String lockAmtPeriod) {
        this.lockAmtPeriod = lockAmtPeriod;
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
     * @return the isFixedSalary
     */
    public int getIsFixedSalary() {
        return isFixedSalary;
    }

    /**
     * @param isFixedSalary the isFixedSalary to set
     */
    public void setIsFixedSalary(int isFixedSalary) {
        this.isFixedSalary = isFixedSalary;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the validityDate
     */
    public String getValidityDate() {
        return validityDate;
    }

    /**
     * @param validityDate the validityDate to set
     */
    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
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
     * @return the noSalary
     */
    public String getNoSalary() {
        return noSalary;
    }

    /**
     * @param noSalary the noSalary to set
     */
    public void setNoSalary(String noSalary) {
        this.noSalary = noSalary;
    }

    /**
     * @return the empSaving6
     */
    public String getEmpSaving6() {
        return empSaving6;
    }

    /**
     * @param empSaving6 the empSaving6 to set
     */
    public void setEmpSaving6(String empSaving6) {
        this.empSaving6 = empSaving6;
    }

    /**
     * @return the releaseFor
     */
    public String getReleaseFor() {
        return releaseFor;
    }

    /**
     * @param releaseFor the releaseFor to set
     */
    public void setReleaseFor(String releaseFor) {
        this.releaseFor = releaseFor;
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
     * @return the releasedBy
     */
    public String getReleasedBy() {
        return releasedBy;
    }

    /**
     * @param releasedBy the releasedBy to set
     */
    public void setReleasedBy(String releasedBy) {
        this.releasedBy = releasedBy;
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the panNumber
     */
    public String getPanNumber() {
        return panNumber;
    }

    /**
     * @param panNumber the panNumber to set
     */
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    /**
     * @return the ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName the ownerName to set
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @return the revisedMonth
     */
    public int getRevisedMonth() {
        return revisedMonth;
    }

    /**
     * @param revisedMonth the revisedMonth to set
     */
    public void setRevisedMonth(int revisedMonth) {
        this.revisedMonth = revisedMonth;
    }

    /**
     * @return the revisedYear
     */
    public String getRevisedYear() {
        return revisedYear;
    }

    /**
     * @param revisedYear the revisedYear to set
     */
    public void setRevisedYear(String revisedYear) {
        this.revisedYear = revisedYear;
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
     * @return the difference
     */
    public String getDifference() {
        return difference;
    }

    /**
     * @param difference the difference to set
     */
    public void setDifference(String difference) {
        this.difference = difference;
    }

    /**
     * @return the organizationId
     */
    public int getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId the organizationId to set
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
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
     * @return the newGrossPay
     */
    public Double getNewGrossPay() {
        return newGrossPay;
    }

    /**
     * @param newGrossPay the newGrossPay to set
     */
    public void setNewGrossPay(Double newGrossPay) {
        this.newGrossPay = newGrossPay;
    }

    /**
     * @return the exemptionName
     */
    public String getExemptionName() {
        return exemptionName;
    }

    /**
     * @param exemptionName the exemptionName to set
     */
    public void setExemptionName(String exemptionName) {
        this.exemptionName = exemptionName;
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
     * @return the monthlyAmount
     */
    public double getMonthlyAmount() {
        return monthlyAmount;
    }

    /**
     * @param monthlyAmount the monthlyAmount to set
     */
    public void setMonthlyAmount(double monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    /**
     * @return the updateFlag
     */
    public String getUpdateFlag() {
        return updateFlag;
    }

    /**
     * @param updateFlag the updateFlag to set
     */
    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    /**
     * @return the policyNumber
     */
    public String getPolicyNumber() {
        return policyNumber;
    }

    /**
     * @param policyNumber the policyNumber to set
     */
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    /**
     * @return the licPremium
     */
    public String getLicPremium() {
        return licPremium;
    }

    /**
     * @param licPremium the licPremium to set
     */
    public void setLicPremium(String licPremium) {
        this.licPremium = licPremium;
    }

    /**
     * @return the file1
     */
    public File getFile1() {
        return file1;
    }

    /**
     * @param file1 the file1 to set
     */
    public void setFile1(File file1) {
        this.file1 = file1;
    }

    /**
     * @return the file1FileName
     */
    public String getFile1FileName() {
        return file1FileName;
    }

    /**
     * @param file1FileName the file1FileName to set
     */
    public void setFile1FileName(String file1FileName) {
        this.file1FileName = file1FileName;
    }

    /**
     * @return the isExists
     */
    public int getIsExists() {
        return isExists;
    }

    /**
     * @param isExists the isExists to set
     */
    public void setIsExists(int isExists) {
        this.isExists = isExists;
    }

    /**
     * @return the attachmentLocation1
     */
    public String getAttachmentLocation1() {
        return attachmentLocation1;
    }

    /**
     * @param attachmentLocation1 the attachmentLocation1 to set
     */
    public void setAttachmentLocation1(String attachmentLocation1) {
        this.attachmentLocation1 = attachmentLocation1;
    }


    /**
     * @return the empSavings
     */
    public Double getEmpSavings() {
        return empSavings;
    }

    /**
     * @param empSavings the empSavings to set
     */
    public void setEmpSavings(Double empSavings) {
        this.empSavings = empSavings;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the houseAddress
     */
    public String getHouseAddress() {
        return houseAddress;
    }

    /**
     * @param houseAddress the houseAddress to set
     */
    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

 

    
    
    
}
