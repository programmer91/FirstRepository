package com.mss.mirage.util;

import com.mss.mirage.admin.AdminService;
import com.mss.mirage.admin.AdminServiceImpl;
import com.mss.mirage.ajax.AjaxHandlerService;
import com.mss.mirage.ajax.AjaxHandlerServiceImpl;
import com.mss.mirage.ajax.AjaxHandlerWebService;
import com.mss.mirage.ajax.AjaxHandlerWebServiceImpl;
import com.mss.mirage.ajax.PayrollAjaxHandlerService;
import com.mss.mirage.ajax.PayrollAjaxHandlerServiceImpl;
import com.mss.mirage.ajaxnew.NewAjaxHandlerService;
import com.mss.mirage.ajaxnew.NewAjaxHandlerServiceImpl;
import com.mss.mirage.crm.accounts.AccountService;
import com.mss.mirage.crm.accounts.AccountServiceImpl;
import com.mss.mirage.crm.activities.ActivityService;
import com.mss.mirage.crm.activities.ActivityServiceImpl;
import com.mss.mirage.crm.attachments.AttachmentService;
import com.mss.mirage.crm.attachments.AttachmentServiceImpl;
import com.mss.mirage.crm.calendar.CalendarHandlerService;
import com.mss.mirage.crm.calendar.CalendarHandlerServiceImpl;
import com.mss.mirage.crm.contacts.ContactService;
import com.mss.mirage.crm.contacts.ContactServiceImpl;
import com.mss.mirage.crm.greensheets.GreenSheetService;
import com.mss.mirage.crm.greensheets.GreenSheetServiceImpl;
import com.mss.mirage.crm.notes.NotesService;
import com.mss.mirage.crm.notes.NotesServiceImpl;
import com.mss.mirage.crm.opportunities.OpportunityService;
import com.mss.mirage.crm.opportunities.OpportunityServiceImpl;
import com.mss.mirage.crm.requirement.RequirementService;
import com.mss.mirage.crm.requirement.RequirementServiceImpl;
import com.mss.mirage.employee.address.AddressService;
import com.mss.mirage.employee.address.AddressServiceImpl;
import com.mss.mirage.employee.ancillary.AncillaryService;
import com.mss.mirage.employee.ancillary.AncillaryServiceImpl;
import com.mss.mirage.employee.appraisal.EmployeeAppraisalService;
import com.mss.mirage.employee.appraisal.EmployeeAppraisalServiceImpl;
import com.mss.mirage.employee.bridgeconference.BridgeConferenceService;
import com.mss.mirage.employee.bridgeconference.BridgeConferenceServiceImpl;
import com.mss.mirage.employee.general.EmployeeService;
import com.mss.mirage.employee.general.EmployeeServiceImpl;
import com.mss.mirage.employee.immigration.ImmigrationService;
import com.mss.mirage.employee.immigration.ImmigrationServiceImpl;
import com.mss.mirage.employee.insurance.EmpInsuranceService;
import com.mss.mirage.employee.insurance.EmpInsuranceServiceImpl;
import com.mss.mirage.employee.issues.IssuesService;
import com.mss.mirage.employee.issues.IssuesServiceImpl;
import com.mss.mirage.employee.leaverequest.LeaveService;
import com.mss.mirage.employee.leaverequest.LeaveServiceImpl;
import com.mss.mirage.employee.phonelog.PhoneLogService;
import com.mss.mirage.employee.phonelog.PhoneLogServiceImpl;
import com.mss.mirage.employee.profile.EmpProfileService;
import com.mss.mirage.employee.profile.EmpProfileServiceImpl;
import com.mss.mirage.employee.projects.ProjectsService;
import com.mss.mirage.employee.projects.ProjectsServiceImpl;
import com.mss.mirage.employee.termination.EmpTerminateService;
import com.mss.mirage.employee.termination.EmpTerminateServiceImpl;
import com.mss.mirage.employee.timesheets.TimeSheetService;
import com.mss.mirage.employee.timesheets.TimeSheetServiceImpl;
import com.mss.mirage.employee.travel.TravelService;
import com.mss.mirage.employee.travel.TravelServiceImpl;
import com.mss.mirage.general.GeneralService;
import com.mss.mirage.general.GeneralServiceImpl;
import com.mss.mirage.hr.leavereports.LeaveReportService;
import com.mss.mirage.hr.leavereports.LeaveReportServiceImpl;
import com.mss.mirage.marketing.MarketingService;
import com.mss.mirage.marketing.MarketingServiceImpl;
import com.mss.mirage.mycop.MycopService;
import com.mss.mirage.mycop.MycopServiceImpl;
import com.mss.mirage.projects.ProjectService;
import com.mss.mirage.projects.ProjectServiceImpl;
import com.mss.mirage.projects.issues.ProjIssuesService;
import com.mss.mirage.projects.issues.ProjIssuesServiceImpl;
import com.mss.mirage.recruitment.ConsultantService;
import com.mss.mirage.recruitment.ConsultantServiceImpl;
import com.mss.mirage.recruitment.attachments.ConsultantAttachmentService;
import com.mss.mirage.recruitment.attachments.ConsultantAttachmentServiceImpl;
import com.mss.mirage.recruitment.consultantdetails.ConsultantDetailsService;
import com.mss.mirage.recruitment.consultantdetails.ConsultantDetailsServiceImpl;
import com.mss.mirage.reports.ReportsService;
import com.mss.mirage.reports.ReportsServiceImpl;
import com.mss.mirage.employee.tasks.TasksService;
import com.mss.mirage.employee.tasks.TasksServiceImpl;
import com.mss.mirage.ecertification.EcertificationService;
import com.mss.mirage.ecertification.EcertificationServiceImpl;
import com.mss.mirage.cre.general.CreLoginService;
import com.mss.mirage.cre.general.CreLoginServiceImpl;
import com.mss.mirage.cre.CreService;
import com.mss.mirage.cre.CreServiceImpl;
import com.mss.mirage.cre.general.CreGeneralService;
import com.mss.mirage.cre.general.CreGeneralServiceImpl;
import com.mss.mirage.employee.Reviews.ReviewsService;
import com.mss.mirage.employee.Reviews.ReviewsServiceImpl;
import com.mss.mirage.employee.appreciation.AppreciationService;
import com.mss.mirage.employee.appreciation.AppreciationServiceImpl;
import com.mss.mirage.employee.expenses.ExpensesService;
import com.mss.mirage.employee.expenses.ExpensesServiceImpl;
import com.mss.mirage.employee.payroll.PayrollHandlerService;
import com.mss.mirage.employee.payroll.PayrollHandlerServiceImpl;
import com.mss.mirage.employee.performanceReviews.PerformanceService;
import com.mss.mirage.employee.performanceReviews.PerformanceServiceImpl;
import com.mss.mirage.employee.timesheets.NewTimeSheetService;
import com.mss.mirage.employee.timesheets.NewTimeSheetServiceImpl;
import com.mss.mirage.marketing.events.EventService;
import com.mss.mirage.marketing.events.EventServiceImpl;
import com.mss.mirage.marketing.library.LibraryService;
import com.mss.mirage.marketing.library.LibraryServiceImpl;
import com.mss.mirage.marketing.surveyform.SurveyFormService;
import com.mss.mirage.marketing.surveyform.SurveyFormServiceImpl;
import com.mss.mirage.mcertification.McertificationService;
import com.mss.mirage.mcertification.McertificationServiceImpl;



public class ServiceLocator
{
  public static TimeSheetService getTimeSheetService()
  {
    TimeSheetService timeSheetservice = new TimeSheetServiceImpl();
    return timeSheetservice;
  }

  public static GeneralService getGeneralService() {
    GeneralService generalService = new GeneralServiceImpl();
    return generalService;
  }

  public static AccountService getAccountService() {
    AccountService accountService = new AccountServiceImpl();
    return accountService;
  }

  public static ContactService getContactService() {
    ContactService contactService = new ContactServiceImpl();
    return contactService;
  }

  public static ActivityService getActivityService() {
    ActivityService activityService = new ActivityServiceImpl();
    return activityService;
  }

  public static EmployeeService getEmployeeService() {
    EmployeeService employeeService = new EmployeeServiceImpl();
    return employeeService;
  }

  public static OpportunityService getOpportunityService() {
    OpportunityService opportunityService = new OpportunityServiceImpl();
    return opportunityService;
  }

  public static AttachmentService getAttachmentService() {
    AttachmentService attachmentService = new AttachmentServiceImpl();
    return attachmentService;
  }

  public static NotesService getNotesService() {
    NotesService notesService = new NotesServiceImpl();
    return notesService;
  }

  public static GreenSheetService getGreenSheetService() {
    GreenSheetService greenSheetService = new GreenSheetServiceImpl();
    return greenSheetService;
  }

  public static AdminService getAdminService() {
    AdminService adminService = new AdminServiceImpl();
    return adminService;
  }

  public static ReportsService getReportsService() {
    ReportsService reportService = new ReportsServiceImpl();
    return reportService;
  }

  public static ProjectService getProjectService() {
    ProjectService projectService = new ProjectServiceImpl();
    return projectService;
  }

  public static IssuesService getIssuesService() {
    IssuesService issueService = new IssuesServiceImpl();
    return issueService; }

  public static AncillaryService getAncillaryService() {
    AncillaryService ancillaryService = new AncillaryServiceImpl();
    return ancillaryService;
  }

  public static ImmigrationService getImmigrationService() {
    ImmigrationService ImmigrationService = new ImmigrationServiceImpl();
    return ImmigrationService;
  }

  public static EmpInsuranceService getEmpInsuranceService() {
    EmpInsuranceService empInsuranceService = new EmpInsuranceServiceImpl();
    return empInsuranceService; }

  public static AddressService getAddressService() {
    AddressService addressService = new AddressServiceImpl();
    return addressService;
  }

  public static ConsultantService getConsultantService()
  {
    return new ConsultantServiceImpl();
  }

  public static ConsultantDetailsService getConsultantDetailsService() {
    return new ConsultantDetailsServiceImpl();
  }

  public static ConsultantAttachmentService getConsultantAttachmentService()
  {
    return new ConsultantAttachmentServiceImpl();
  }

  public static LeaveService getLeaveService() {
    return new LeaveServiceImpl(); }

  public static MarketingService getMarketingService() {
    return new MarketingServiceImpl();
  }

  public static MycopService getMyCopService()
  {
    return new MycopServiceImpl();
  }

  public static PhoneLogService getPhoneLogService() {
    PhoneLogService logService = new PhoneLogServiceImpl();
    return logService;
  }

  public static EmpProfileService getEmpProfileService() {
    EmpProfileService empProfileService = new EmpProfileServiceImpl();
    return empProfileService;
  }

  public static BridgeConferenceService getBridgeConferenceService() {
    BridgeConferenceService bridgeService = new BridgeConferenceServiceImpl();
    return bridgeService;
  }

  public static ProjIssuesService getProjIssuesService() {
    ProjIssuesService projIssuesService = new ProjIssuesServiceImpl();
    return projIssuesService; }

  public static EmployeeAppraisalService getEmpAppraisalService() {
    EmployeeAppraisalService service = new EmployeeAppraisalServiceImpl();
    return service;
  }

  public static AjaxHandlerService getAjaxHandlerService() {
    AjaxHandlerService service = new AjaxHandlerServiceImpl();
    return service;
  }

  public static TravelService getTravelService() {
    TravelService travel = new TravelServiceImpl();
    return travel; }

  public static EmpTerminateService getTerminateService() {
    EmpTerminateService terminate = new EmpTerminateServiceImpl();
    return terminate;
  }

  public static RequirementService getRequirementService() {
    RequirementService requirement = new RequirementServiceImpl();
    return requirement;
  }

  public static CalendarHandlerService getCalendarService() {
    CalendarHandlerService calendarService = new CalendarHandlerServiceImpl();
    return calendarService;
  }

  public static LeaveReportService getLeaveReportService() {
    LeaveReportService leaveReportService = new LeaveReportServiceImpl();
    return leaveReportService;
  }

  public static ProjectsService getProjectDetailService() {
    ProjectsService projsService = new ProjectsServiceImpl();
    return projsService;
  }

  public static LeaveReportService getTimeSheetPDFReport() {
    LeaveReportService leaveReportService = new LeaveReportServiceImpl();
    return leaveReportService;
  }
  
  
  public static TasksService getTasksService(){
    TasksService tasksService = new TasksServiceImpl();
    return tasksService;
  }
  
   public static EcertificationService getEcertificationService(){
    EcertificationService ecertificationService = new EcertificationServiceImpl();
    return ecertificationService;
  }
  
   public static CreLoginService getCreLoginService(){
    CreLoginService creLoginService = new CreLoginServiceImpl();
    return creLoginService;
  }
    public static CreService getCreService(){
    CreService creService = new CreServiceImpl();
    return creService;
  }
    public static CreGeneralService getCreGeneralService(){
       CreGeneralService creGeneralService = new CreGeneralServiceImpl();
       return creGeneralService;   
   }
    public static MailServices getMailServices() {
    MailServices service = new MailServiceImpl();
    return service;
  }
    public static NewTimeSheetService getNewTimeSheetService()
  {
    NewTimeSheetService newTimeSheetservice = new NewTimeSheetServiceImpl();
    return newTimeSheetservice;
  }
       public static PerformanceService getPerformanceService(){
    PerformanceService performanceService = new PerformanceServiceImpl();
    return performanceService;
  }
   public static ReviewsService getReviewsService() {
    ReviewsService reviewsService = new ReviewsServiceImpl();
    return reviewsService;
  }
  
  public static AjaxHandlerWebService getAjaxHandlerWebService() {
    AjaxHandlerWebService webservice = new AjaxHandlerWebServiceImpl();
    return webservice;
  }
  public static PayrollAjaxHandlerService getPayrollAjaxHandlerService() {
    PayrollAjaxHandlerService service = new PayrollAjaxHandlerServiceImpl();
    return service;
  }

  public static LibraryService getLiraryService() {
      LibraryService libraryService = new LibraryServiceImpl();
      return libraryService;
  }
   public static SurveyFormService getSurveyFormService() {
      SurveyFormService surveyFormService = new SurveyFormServiceImpl();
      return surveyFormService;
  }
   
    public static AppreciationService getAppreciationService() {
      AppreciationService appreciationService = new AppreciationServiceImpl();
      return appreciationService;
  }
     public static ExpensesService getExpensesService() {
      ExpensesService expensesService = new ExpensesServiceImpl();
      return expensesService;
  }
      public static PayrollHandlerService getPayRollHandlerService() {
    PayrollHandlerService payrollHandlerService = new PayrollHandlerServiceImpl();
    return payrollHandlerService;
  }
public static NewAjaxHandlerService getNewAjaxHandlerService() {
    NewAjaxHandlerService mcertificationService = new NewAjaxHandlerServiceImpl();
    return mcertificationService;
  }
 public static McertificationService getMcertificationService() {
    McertificationService mcertificationService = new McertificationServiceImpl();
    return mcertificationService;
  }
  public static EventService getEventService() {
    EventService eventService = new EventServiceImpl();
    return eventService;
  }
}