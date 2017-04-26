/*
 * HRActionClass.java
 *
 * Created on October 15, 2008, 8:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.hr.leavereports;

import com.mss.mirage.employee.general.EmployeeService;
import com.mss.mirage.general.LoginAction;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class LeaveReportAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
    
    /** Creates a new instance of HRActionClass */
    public LeaveReportAction() {
        
    }
    private String resultType;
    private String empName;
    private String status;
    private Date startDate;
    private Date endDate;
    
    private List leaveList;
    private List leaveList1;
    private List departmentIdList;
    private List notApprovedDepartmentList;
    private String departmentId;
    private String practiceId;
    private String subPractice;
    private String teamId;
    private String teamMemberId;
    private String futureLeaves;
    private StringBuffer currentDate;
    private String reportResult;
    private String department;
    
    private String timeSheetWeekStartDate;
    private String timeSheetWeekEndDate;
    
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    /** The hibernateDataProvider is used for storing reference of ApplicationDataProvider methods. */
    private HibernateDataProvider hibernateDataProvider;
    
    private DefaultDataProvider defaultDataProvider;
    
    private DataSourceDataProvider dataSourceDataProvider;
    
    private EmployeeService employeeService;
    
    private String projectName;
    private Date projectDate;
    private String  listby;
    //LeaveReportServiceImpl hrservice = new LeaveReportServiceImpl();
    
    
    public String prepare() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            resultType = "accessFailed";
            try {
                hibernateDataProvider = HibernateDataProvider.getInstance();
                defaultDataProvider = DefaultDataProvider.getInstance();
                employeeService = ServiceLocator.getEmployeeService();
                dataSourceDataProvider = dataSourceDataProvider.getInstance();
                setDepartmentIdList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                setNotApprovedDepartmentList(hibernateDataProvider.getDepartment(ApplicationConstants.DEPARTMENT_OPTION));
                resultType = SUCCESS;
            } catch(Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }
        return resultType;
    }
    
    
    public String execute() {
        prepare();
        String query ="";
        if(futureLeaves.equals("true")){
            resultType = "login";
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                StringBuffer currentDate = getCurrentDate();
                try {
                    empName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                    LeaveReportService hrService = ServiceLocator.getLeaveReportService();
                    //hrService.getFutureLeavesReport(httpServletResponse,empName,futureLeaves);
                    query = "SELECT  concat(tblEmployee.FName,'',tblEmployee.MName,'.',tblEmployee.LName) as Name,tblEmpLeaves.StartDate,tblEmpLeaves.EndDate,"+
                            "tblEmpLeaves.LeaveType,tblEmpLeaves.status,tblEmpLeaves.reportsTO,tblEmpLeaves.DepartmentId FROM tblEmpLeaves join tblEmployee on(tblEmployee.Id=tblEmpLeaves.EmpId  and tblEmployee.CurStatus  = 'active')"+
                            " where tblEmpLeaves.StartDate > '"+currentDate.toString()+"%'  order by DepartmentId, Name";
                    reportResult = hrService.generateLeaveStatusReport(this,empName,query,httpServletResponse);
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,reportResult);
                    resultType = SUCCESS;
                }catch(Exception ex) {
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        } else {
            //System.err.println("---"+departmentId);
            if(teamMemberId.equalsIgnoreCase("All")){
                teamMemberId = "%";
            } else {
                teamMemberId = teamMemberId;
            }
            
            if(teamId.equalsIgnoreCase("All")){
                teamId = "%";
            } else {
                teamId = teamId;
            }
            
            if(subPractice.equalsIgnoreCase("All")){
                subPractice = "%";
            } else {
                subPractice = subPractice;
            }
            
            if(practiceId.equalsIgnoreCase("All")){
                practiceId = "%";
            } else {
                practiceId = practiceId;
            }
            if(departmentId.equalsIgnoreCase("All")){
                departmentId = "%";
            } else {
                departmentId = departmentId;
            }
            resultType = "login";
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
                try {
                    empName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                    LeaveReportService hrService = ServiceLocator.getLeaveReportService();
                    query = "SELECT  concat(tblEmployee.FName,'',tblEmployee.MName,'.',tblEmployee.LName) as Name,tblEmpLeaves.StartDate,tblEmpLeaves.EndDate,"+
                            "tblEmpLeaves.LeaveType,tblEmpLeaves.status,tblEmpLeaves.reportsTO,tblEmpLeaves.DepartmentId FROM tblEmpLeaves join tblEmployee on(tblEmployee.Id=tblEmpLeaves.EmpId and tblEmployee.PracticeId like '"+getPracticeId()+"' and tblEmployee.SubPractice like '"+getSubPractice()+"' and tblEmployee.CurStatus  = 'active' and tblEmployee.TeamId like '"+getTeamId()+"')"+
                            " where Date(tblEmpLeaves.StartDate) >= '"+getStartDate()+"' and Date(tblEmpLeaves.EndDate) <= '"+getEndDate()+"' and tblEmpLeaves.status='"+getStatus()+"' and tblEmpLeaves.DepartmentId like '"+getDepartmentId()+"' and tblEmpLeaves.EmpId like '"+getTeamMemberId()+"' order by DepartmentId,Name,tblEmpLeaves.EndDate";
                    //System.out.println("Query------->"+query);
                    reportResult = hrService.generateLeaveStatusReport(this,empName,query,httpServletResponse);
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,reportResult);
                    resultType = SUCCESS;
                }catch(Exception ex) {
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }
        }
        return resultType;
    }
    
    
    public String avalLeaveReport() {
        resultType = LOGIN;
        String month1 =null;
        String day1 =null;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            try {
                String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                empName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                StringBuffer edate1 = getCurrentDate();
                String sdate = year+"-"+"01"+"-"+"01";
                String edate = edate1.toString();
                //System.out.println("Current Date is...."+empId+"....."+startDate+"....."+endDate);
                LeaveReportService hrservice = ServiceLocator.getLeaveReportService();
                hrservice.generateAvaliableLeaveReport(empId,empName,sdate,edate,httpServletResponse);
                resultType = SUCCESS;
            } catch (Exception ex){
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }
        return resultType;
    }
    
    
    public String birthdayReport(){
        prepare();
        String query ="";
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            try {
                empName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                LeaveReportService hrService = ServiceLocator.getLeaveReportService();
                if(getListby().equals("1")) {
                    query ="SELECT CONCAT(fname,' ',mname,' ',lname) AS EmpName ,departmentid,orgid,BirthDate," +
                            "AnniversaryDate,Email1 FROM tblEmployee WHERE curstatus='Active' AND" +
                            " WEEK(CURRENT_DATE)=WEEK(birthdate) AND DAY(CURRENT_DATE)=DAY(birthdate) order by birthdate" ;
                    
                }
                if(getListby().equals("2")) {
                    query="SELECT CONCAT(fname,' ',mname,' ',lname) AS EmpName ,departmentid,orgid,BirthDate," +
                            "AnniversaryDate,Email1 FROM tblEmployee WHERE curstatus='Active' AND" +
                            " WEEK(CURRENT_DATE)=WEEK(birthdate) order by birthdate";
                }
                if(getListby().equals("3")) {
                    query="SELECT CONCAT(fname,' ',mname,' ',lname) AS EmpName ,departmentid,orgid," +
                            "BirthDate,AnniversaryDate,Email1 FROM tblEmployee WHERE  " +
                            "curstatus='Active' AND MONTH(CURRENT_DATE)=MONTH(birthdate) order by birthdate";
                    
                }
                if(getListby().equals("4")) {
                    query="SELECT CONCAT(fname,' ',mname,' ',lname) AS EmpName ,departmentid,orgid,BirthDate," +
                            "AnniversaryDate,Email1 FROM tblEmployee WHERE curstatus='Active' AND" +
                            " birthdate>='"+getStartDate()+"' AND birthdate<='"+getEndDate()+"' ORDER BY birthdate";
                    
                }
                
                //  System.out.println("Query------->"+query);
                reportResult = hrService.generateBirthdayReport(this,empName,query,httpServletResponse);
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,reportResult);
                resultType = SUCCESS;
            }catch(Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }
        
        return resultType;
    }
    public String getTimeSheetReport() {
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            try {
                empName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                
                LeaveReportService hrService = ServiceLocator.getLeaveReportService();
                
                String tsheetStartDate[] = getTimeSheetWeekStartDate().split("/");
                
                timeSheetWeekStartDate = tsheetStartDate[2]+"-"+tsheetStartDate[1]+"-"+tsheetStartDate[0];
                
                String tsheetEndDate [] = getTimeSheetWeekEndDate().split("/");
                
                timeSheetWeekEndDate = tsheetEndDate[2]+"-"+tsheetEndDate[1]+"-"+tsheetEndDate[0];
                
                String query = "select concat(tblEmployee.FName ,' ',tblEmployee.MName,' . ', tblEmployee.LName) as empName,tblEmployee.DepartmentId,tblEmployee.ReportsTo from tblEmployee where tblEmployee.CurStatus like 'Active%' and DepartmentId like '"+getDepartment()+"' and tblEmployee.ID not in (Select EmpId from tblTimeSheetLines where Date(tblTimeSheetLines.WorkDate) between '"+timeSheetWeekStartDate+"' and '"+timeSheetWeekEndDate+"') ";
                
                //System.out.println("Query----->"+query);
                
                reportResult = hrService.getTimeSheetPDFReport(empName,query,timeSheetWeekStartDate,timeSheetWeekEndDate,httpServletResponse);
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,reportResult);
                resultType = SUCCESS;
            }catch(Exception ex){
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }
        
        return resultType;
    }
    
    
    public String getWeekStartDateAndWeekEndDate(Calendar cal) {
        /** Creates a new instance of Test */
        /* Used for Storing the Week Start Date */
        String wstDate = null;
        
        /* Used for Storing the Week End Date */
        String wenDate = null;
        
        /* used for the current date */
        String curntDate=null;
        
        /* for stroring the weekdays */
        String [] weekDays = new String[7];
        
        /* to get the day of week */
        int w = cal.get(Calendar.DAY_OF_WEEK);
        
        /* if its sunday then the index is 0 other then sunday then minus the index */
        if(w==1) {
            cal.add(Calendar.DATE,0);
            
        } else if (w==2) {
            cal.add(Calendar.DATE,-1);
            
        } else if (w==3) {
            cal.add(Calendar.DATE,-2);
            
        } else if(w==4) {
            cal.add(Calendar.DATE,-3);
            
        } else if(w==5) {
            cal.add(Calendar.DATE,-4);
            
        } else if(w==6) {
            cal.add(Calendar.DATE,-5);
            
        } else if(w==7) {
            cal.add(Calendar.DATE,-6);
            
        }
        
        /* for generating the month/day sequence of the week */
        int zeroForMon=0; // if month is single digit then Zero is append to left of that digit
        int zeroForDay=0; // if day is single digit then Zero is append to left of that digit
        for(int index=0;index<7;index++) {
            
            /* for the purpose of concatinating 0 before the day and month */
            zeroForMon=(cal.get(Calendar.MONTH)+1);
            zeroForDay=cal.get(Calendar.DAY_OF_MONTH);
            
            if(zeroForMon<10 && zeroForDay <10) {
                weekDays[index]="0"+(cal.get(Calendar.MONTH)+1) +"/0" + cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.YEAR));
                
            } else
                if(zeroForMon >9 && zeroForDay <10) {
                weekDays[index]=(cal.get(Calendar.MONTH)+1) +"/0" + cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.YEAR));
                
                } else
                    if(zeroForMon <10 && zeroForDay >9) {
                weekDays[index]="0"+(cal.get(Calendar.MONTH)+1) +"/" + cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.YEAR));
                
                
                    } else {
                weekDays[index]=(cal.get(Calendar.MONTH)+1) +"/" + cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.YEAR));
                
                    }
            cal.add(Calendar.DAY_OF_MONTH,1);
        }// End for loop
        
        wstDate =weekDays[0];  // storing the week startday
        wenDate =  weekDays[6]; // storing the week endday
        String startEndDates = wstDate +","+ wenDate;
        return startEndDates;
    }
    public StringBuffer getCurrentDate() {
        
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        currentDate = new StringBuffer();
        
        currentDate.append(year+"-");
        
        if(String.valueOf(month).length() == 1){
            currentDate.append("0"+String.valueOf(month));
        }else {
            currentDate.append(month);
        }
        
        currentDate.append("-");//delimiter b/w Month and Day
        
        if(String.valueOf(day).length() == 1){
            currentDate.append("0"+String.valueOf(day));
        }else {
            currentDate.append(day);
        }
        
        return currentDate;
    }
    
    public String getProjectsSearchList() {
        resultType = LOGIN;
        String month1 =null;
        String day1 =null;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            try {
                String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                empName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                String queryString = "";
                DateUtility.getInstance().convertDateToMySql(projectDate);
                String TABLE_EMP_STATE_HISTORY = Properties.getProperty("TABLE_EMP_STATE_HISTORY");
                if(projectName == null || projectName.equals("")) {
                    queryString = "select * from "+TABLE_EMP_STATE_HISTORY+" where (PrjName like '%' or PrjName is null) and StartDate >= '"+DateUtility.getInstance().convertDateToMySql(projectDate)+"' and (EndDate <= '"+DateUtility.getInstance().convertDateToMySql(projectDate)+"' or EndDate is null )";
                }else {
                    queryString = "select * from "+TABLE_EMP_STATE_HISTORY+" where PrjName like '"+projectName+"'  and StartDate >= '"+DateUtility.getInstance().convertDateToMySql(projectDate)+"' and (EndDate <= '"+DateUtility.getInstance().convertDateToMySql(projectDate)+"' or EndDate is null )";
                }
                //System.err.println("query--"+queryString);
                httpServletRequest.setAttribute(ApplicationConstants.QS_USER_LIST,queryString);
                resultType = SUCCESS;
                prepare();
            } catch (Exception ex){
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }
        return resultType;
    }
    
    
    
    public String getStatus() {
        
        return status;
    }
    
    public void setStatus(String status) {
        //System.out.println(status);
        this.status = status;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    public List getDepartmentIdList() {
        return departmentIdList;
    }
    
    public void setDepartmentIdList(List departmentIdList) {
        this.departmentIdList = departmentIdList;
    }
    
    public String getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getPracticeId() {
        return practiceId;
    }
    
    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }
    
    public String getSubPractice() {
        return subPractice;
    }
    
    public void setSubPractice(String subPractice) {
        this.subPractice = subPractice;
    }
    
    public String getTeamId() {
        return teamId;
    }
    
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
    
    public String getTeamMemberId() {
        return teamMemberId;
    }
    
    public void setTeamMemberId(String teamMemberId) {
        this.teamMemberId = teamMemberId;
    }
    
    public List getLeaveList() {
        return leaveList;
    }
    
    public void setLeaveList(List leaveList) {
        this.leaveList = leaveList;
    }
    
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }
    
    public String getFutureLeaves() {
        return futureLeaves;
    }
    
    public void setFutureLeaves(String futureLeaves) {
        this.futureLeaves = futureLeaves;
    }
    
    public String getReportResult() {
        return reportResult;
    }
    
    public void setReportResult(String reportResult) {
        this.reportResult = reportResult;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getTimeSheetWeekStartDate() {
        return timeSheetWeekStartDate;
    }
    
    public void setTimeSheetWeekStartDate(String timeSheetWeekStartDate) {
        this.timeSheetWeekStartDate = timeSheetWeekStartDate;
    }
    
    public String getTimeSheetWeekEndDate() {
        return timeSheetWeekEndDate;
    }
    
    public void setTimeSheetWeekEndDate(String timeSheetWeekEndDate) {
        this.timeSheetWeekEndDate = timeSheetWeekEndDate;
    }
    
    public List getNotApprovedDepartmentList() {
        return notApprovedDepartmentList;
    }
    
    public void setNotApprovedDepartmentList(List notApprovedDepartmentList) {
        this.notApprovedDepartmentList = notApprovedDepartmentList;
    }
    
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public Date getProjectDate() {
        return projectDate;
    }
    
    public void setProjectDate(Date projectDate) {
        this.projectDate = projectDate;
    }
    
    public String getListby() {
        return listby;
    }
    
    public void setListby(String listby) {
        this.listby = listby;
    }
    
    
    
}
