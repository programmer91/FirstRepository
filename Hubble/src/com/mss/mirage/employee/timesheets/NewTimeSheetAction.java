/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.timesheets;

import com.mss.mirage.crm.attachments.AttachmentService;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ExceptionToListUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.mss.mirage.util.ServiceLocatorException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.apache.struts2.interceptor.ServletResponseAware;
/**
 *
 * @author miracle
 */
public class NewTimeSheetAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
      /**
     * This variable httpServletRequest store the HttpServletRequest object reference
     * <code>{@link #HttpServletRequest}</code>.
     */
    private HttpServletRequest httpServletRequest;
    
    private HttpServletResponse httpServletResponse;
    /**
     *  This variable resultType store the result type of the methods
     *  this can be used for view navigation purpose
     */
    private String resultType;
    
    /* this variable previousWeek store the previousWeek date value */
    /**
     *
     * This variable previousWeek is to get/store the value of Previous Week
     */
    private String previousWeek;
    
    /**
     * for stroing the all values of the timesheet ,
     * the object can be passed to jsp page to display the date.
     *
     *{@Link com.mss.mirage.employee.timesheets.TimeSheetVTO}
     */
    private NewTimeSheetVTO timeSheetVTO;
    
    /** for storing the success or error message of the CURD operation. */
    private String resultMessage;
    
    /* this variable wstDate store the Week Start Date value */
    /**
     *
     * This variable wstDate is to get/store the value of Week Start Date
     */
    private String wstDate;
    
    /* this variable wenDate store the the Week End Date value */
    /**
     *
     * This variable wenDate is to get/store the value of Week End Date
     */
    private String wenDate;
    
    /* this variable submittedDate store the the submitted Date value */
    /**
     *
     * This variable submittedDate is to get/store the value of Submitted Date
     */
    private String submittedDate;
    
    /* this variable approveDate store the the apporove date value */
    /**
     *
     * This variable approveDate is to get/store the value of Approve Date
     */
    private String approveDate;
    
    /* use for radio buttion when user click raido button of this or previous week of TimeSheet*/
    /**
     *
     * This variable checkDate is to get/store the value of Check Date
     */
    private String checkDate;
    
    /**
     * This seven variable weekDate1,weekDate2,weekDate3,weekDate4
     * weekDate5,weekDate6,weekDate7 can be used to stroing the one full week values of the timesheet
     */
    private String weekDate1;
    private String weekDate2;
    private String weekDate3;
    private String weekDate4;
    private String weekDate5;
    private String weekDate6;
    private String weekDate7;
    
    /** This variable proj1Sun used to store the project 1 sunday value */
    private double proj1Sun;
    
    /** This variable proj1Mon used to store the project 1 Monday value */
    private double proj1Mon;
    
    /** This variable proj1Tus used to store the project 1 Tuesday value */
    private double proj1Tus;
    
    /** This variable proj1Wed used to store the project 1 wednesday value */
    private double proj1Wed;
    
    /** This variable proj1Thur used to store the project 1 Thursday value */
    private double proj1Thur;
    
    /** This variable proj1Fri used to store the project 1 Friday value */
    private double proj1Fri;
    
    /** This variable proj1Sat used to store the project 1 saturday value */
    private double proj1Sat;
    
    /** This variable proj1TotalHrs used to store the project 1 total hours value */
    private double proj1TotalHrs;
    
    /** This variable proj2Sun used to store the project 2 sunday value */
    private double proj2Sun;
    
    /** This variable proj2Mon used to store the project 2 Monday value */
    private double proj2Mon;
    
    /** This variable proj2Tus used to store the project 2 tuesday value */
    private double proj2Tus;
    
    /** This variable proj2Wed used to store the project 2 wednesday value */
    private double proj2Wed;
    
    /** This variable proj2Thur used to store the project 2 Thursday value */
    private double proj2Thur;
    
    /** This variable proj2Fri used to store the project 2 Friday value */
    private double proj2Fri;
    
    /** This variable proj2Sat used to store the project 2 Saturday value */
    private double proj2Sat;
    
    /** This variable proj2TotalHrs used to store the project 2 total hours value */
    private double proj2TotalHrs;
    
    /** This variable internalSun used to store the internal project of sunday value */
    private double internalSun;
    
    /** This variable internalMon used to store the internal project of Monday value */
    private double internalMon;
    
    /** This variable internalTus used to store the internal project of tuesday value */
    private double internalTus;
    
    /** This variable internalWed used to store the internal project of Wednesday value */
    private double internalWed;
    
    /** This variable internalThur used to store the internal project of Thursday value */
    private double internalThur;
    
    /** This variable internalFri used to store the internal project of Friday value */
    private double internalFri;
    
    /** This variable internalSat used to store the internal project of saturday value */
    private double internalSat;
    
    /** This variable internalTotalHrs used to store the internal total hours value */
    private double internalTotalHrs;
    
    /** This variable vacationSun used to store the vacation of sunday value */
    private double vacationSun;
    
    /** This variable vacationMon used to store the vacation of Monday value */
    private double vacationMon;
    
    /** This variable vacationTus used to store the vacation of Tuesday value */
    private double vacationTus;
    
    /** This variable vacationWed used to store the vacation of Wednesday value */
    private double vacationWed;
    
    /** This variable vacationThur used to store the vacation of Thursday value */
    private double vacationThur;
    
    /** This variable vacationFri used to store the vacation of Friday value */
    private double vacationFri;
    
    /** This variable vacationSat used to store the vacation of Saturday value */
    private double vacationSat;
    
    /** This variable vacationTotalHrs used to store the total vacations value */
    private double vacationTotalHrs;
    
    /** This variable holiSun used to store the sunday value of holiday */
    private double holiSun;
    
    /** This variable holiMon used to store the Monday value of holiday */
    private double holiMon;
    
    /** This variable holiTus used to store the Tusday value of holiday */
    private double holiTus;
    
    /** This variable holiWed used to store the Wednesday value of holiday */
    private double holiWed;
    
    /** This variable holiThur used to store the Thursday value of holiday */
    private double holiThur;
    
    /** This variable holiFri used to store the Friday value of holiday */
    private double holiFri;
    
    /** This variable holiSat used to store the Saturday value of holiday */
    private double holiSat;
    
    /** This variable holiTotalHrs used to store the total holidays value  */
    private double holiTotalHrs;
    
    /** This variable totalSun used to store the total sum of project 1,2,vaction,and holiday value of sunday  */
    private double totalSun;
    
    /** This variable totalMon used to store the total sum of project 1,2,vaction,and holiday value of Monday  */
    private double totalMon;
    
    /** This variable totalTus used to store the total sum of project 1,2,vaction,and holiday value of Tuesday  */
    private double totalTus;
    
    /** This variable totalWed used to store the total sum of project 1,2,vaction,and holiday value of Wednesday  */
    private double totalWed;
    
    /** This variable totalThur used to store the total sum of project 1,2,vaction,and holiday value of Thursday  */
    private double totalThur;
    
    /** This variable totalFri used to store the total sum of project 1,2,vaction,and holiday value of Friday  */
    private double totalFri;
    
    /** This variable totalSat used to store the total sum of project 1,2,vaction,and holiday value of Saturday  */
    private double totalSat;
    
    /** This variable allWeekendTotalHors used to store the total sum of project 1,2,vaction,and holiday value of Weekend  */
    private double allWeekendTotalHors;
    
    /** This variable totalVacationHrs used to store the total vacation weekend value  */
    private double totalVacationHrs;
    
    /** This variable totalHoliHrs used to store the total holidays of weekend  */
    private double totalHoliHrs;
    
    /** This variable totalBillHrs used to store the total billing hours */
    private double totalBillHrs;
    
    /** This variable txtNotes used to store the notes of the timesheet */
    private String txtNotes;
    
    /** This variable timeSheetID used to store the id of the timesheet */
    private String timeSheetID;
    
    /** This variable empID used to store the employee ID */
    private String empID;
    
    /** This variable startDate used to store the starting day of timesheet */
    private String startDate;
    
    /** This variable endDate used to store the ending day of timesheet */
    private String endDate;
    
    /** This variable sqlQuery used to store the Query of searching timesheet */
    private String sqlQuery;
    
    /**
     *
     * This variable userRoleId is to get/store the value of User Role Id
     */
    private int userRoleId;
    
    /**
     *
     * This variable empName is to get/store the value of Employee Name
     */
    private String empName;
    
    /**
     *
     * This variable Id is to get/store the value of Id
     */
    private String Id;
    
    /**
     *
     * This variable timeSheetStat is to get/store the value of TimeSheet Status
     */
    private String timeSheetStat;
    
    /**
     *
     * This variable statusValue is to get/store the value of Status Value
     */
    private String statusValue;
    
    /**
     *
     * This variable comment is to get/store the value of Comment
     */
    private String comment;
    
    /**
     *
     * This variable isOnsite is to get/store the value of is Onsite or not
     */
    private String isOnsite;
    
    /**
     *
     * This Map MyTeamMembers is to get/store the My Team Members
     */
    private Map MyTeamMembers;
    
    //Upload TimeSheet Variables
    /**
     *
     * This Object attachmentService provides the reference variable for AttachmentService
     */
    private AttachmentService attachmentService;
    /**
     *
     * This variable generatedPath is to get/store the value of Generated Path
     */
    private String generatedPath;
    /**
     *
     * This File upload is to get/upload the File
     */
    private File upload;
    /**
     *
     * This variable uploadContentType is to get/store the value of Uploaded Content Type
     */
    private String uploadContentType;
    /**
     *
     * This variable uploadFileName is to get/store the value of Upload File Name
     */
    private String uploadFileName;
    /**
     *
     * This variable createdBy is to get/store the value of CreatedBy
     */
    private String createdBy;
    /**
     *
     * This variable objectId is to get/store the value of ObjectId
     */
    private int objectId;
    /**
     *
     * This variable objectType is to get/store the value of Object Type
     */
    private String objectType;
    /**
     *
     * This variable fileLocation is to get/store the value of File Location
     */
    private String fileLocation;
    /**
     *
     * This variable filePath is to get/store the value of File Path
     */
    private String filePath;
    /**
     *
     * This variable attachmentName is to get/store the value of Attachment Name
     */
    private String attachmentName;
    
    private String empLName;
    private String empFName;
    
    private int tempVar;
    private String submitFrom;
    private StringBuffer queryStringBuffer;
    // newly added
    private int associatedProjectsCount;
    
    
     
     /*Newly Added properties for Timesheet enhancement
      * Date : 09/09/2014
      * Author : Santosh Kola
      * 
      */
         /** This variable compSun used to store the sunday value of comptime */
    private double compSun;
    
    /** This variable compMon used to store the Monday value of comptime */
    private double compMon;
    
    /** This variable compTus used to store the Tusday value of comptime */
    private double compTus;
    
    /** This variable compWed used to store the Wednesday value of comptime */
    private double compWed;
    
    /** This variable compThur used to store the Thursday value of comptime */
    private double compThur;
    
    /** This variable compFri used to store the Friday value of comptime */
    private double compFri;
    
    /** This variable compSat used to store the Saturday value of comptime */
    private double compSat;
    
    /** This variable compTotalHrs used to store the total comptimes value  */
    private double compTotalHrs;
     
     private double totalComptimeHrs;
     
     /** This variable proj3Sun used to store the project 3 sunday value */
    private double proj3Sun;
    
    /** This variable proj3Mon used to store the project 3 Monday value */
    private double proj3Mon;
    
    /** This variable proj3Tus used to store the project 3 tuesday value */
    private double proj3Tus;
    
    /** This variable proj3Wed used to store the project 3 wednesday value */
    private double proj3Wed;
    
    /** This variable proj3Thur used to store the project 3 Thursday value */
    private double proj3Thur;
    
    /** This variable proj3Fri used to store the project 3 Friday value */
    private double proj3Fri;
    
    /** This variable proj3Sat used to store the project 3 Saturday value */
    private double proj3Sat;
    
    /** This variable proj3TotalHrs used to store the project 3 total hours value */
    private double proj3TotalHrs;
     
          /** This variable proj4Sun used to store the project 4 sunday value */
    private double proj4Sun;
    
    /** This variable proj4Mon used to store the project 4 Monday value */
    private double proj4Mon;
    
    /** This variable proj4Tus used to store the project 4 tuesday value */
    private double proj4Tus;
    
    /** This variable proj4Wed used to store the project 4 wednesday value */
    private double proj4Wed;
    
    /** This variable proj4Thur used to store the project 4 Thursday value */
    private double proj4Thur;
    
    /** This variable proj4Fri used to store the project 4 Friday value */
    private double proj4Fri;
    
    /** This variable proj4Sat used to store the project 4 Saturday value */
    private double proj4Sat;
    
    /** This variable proj4TotalHrs used to store the project 4 total hours value */
    private double proj4TotalHrs;
     
          /** This variable proj5Sun used to store the project 5 sunday value */
    private double proj5Sun;
    
    /** This variable proj5Mon used to store the project 5 Monday value */
    private double proj5Mon;
    
    /** This variable proj5Tus used to store the project 5 tuesday value */
    private double proj5Tus;
    
    /** This variable proj5Wed used to store the project 5 wednesday value */
    private double proj5Wed;
    
    /** This variable proj5Thur used to store the project 5 Thursday value */
    private double proj5Thur;
    
    /** This variable proj5Fri used to store the project 5 Friday value */
    private double proj5Fri;
    
    /** This variable proj5Sat used to store the project 5 Saturday value */
    private double proj5Sat;
    
    /** This variable proj5TotalHrs used to store the project 5 total hours value */
    private double proj5TotalHrs;
     
    // private int totalProjects;
     private int proj1Id;
     private int proj2Id;
     private int proj3Id;
     private int proj4Id;
     private int proj5Id;
     
    private int iflag;
    private String resourceType;
    
     private int emptimesheetid;
     private int employeeid;
    private String empType;
    private int projectId;
    private String empCusType;
    private Map empnamesList;
    private Map custnamesList;
    private String custnameById;
    private String empnameById;
    private String description;
    private Map projectMap;
    private Map projectReportsToMap;
    
    private Map printEmpnamesList;
    private Map reportsEmpnamesList;
    
    private int priProjId;
    
    
    
        // Fields For Biometric Hrs
    /** This variable compSun used to store the sunday value of comptime */
    private double biometricSun;
     private String bmSunStatus;
    /** This variable compMon used to store the Monday value of comptime */
    private double biometricMon;
    private String bmMonStatus;
    /** This variable compTus used to store the Tusday value of comptime */
    private double biometricTus;
    private String bmTusStatus;
    /** This variable compWed used to store the Wednesday value of comptime */
    private double biometricWed;
    private String bmWedStatus;
    /** This variable compThur used to store the Thursday value of comptime */
    private double biometricThur;
    private String bmThurStatus;
    /** This variable compFri used to store the Friday value of comptime */
    private double biometricFri;
    private String bmFriStatus;
    /** This variable compSat used to store the Saturday value of comptime */
    private double biometricSat;
    private String bmSatStatus;
    /** This variable compTotalHrs used to store the total comptimes value  */
    private double biometricTotalHrs;
     
     private double totalBiometricHrs;
     
     // Dual reports properties start
        private Map secCustnamesList;
        private int teamType;
        private boolean isDualReportingRequired;
        private String secStatusValue;
        private String timeSheetSecStat;
        private int priReportsToId;
        private int secReportsToId;

        private String secDescription;
    
    // Dual reports to properties end
     
     private String employeeLocation; 
     private int fileFlagValue;
     private String empNo;
     
     
     private Map operationContactMapByCountry= new HashMap();
private Map locationsMapByLivingCountry= new HashMap();
 private int opsContactId;
    private String locationId;
     
    
    private String leaveDates;
     
    /**
     *
     * @throws java.lang.Exception
     * @return String
     */
    public String execute()throws Exception{
        return SUCCESS;
    }
    
    /**
     *
     * This method is used to check the user is either Onsite or Offshore
     * @return String
     */
    public String newMyTimesheet(){
        
        
      
        
        
        resultType = LOGIN;
        if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            String empType = "";
             String resourceId="";
                String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            try {
              
              
               
                if(httpServletRequest.getSession(false).getAttribute("sDate") != null) {
                    httpServletRequest.getSession(false).setAttribute("sDate",null);
                }
                if(httpServletRequest.getSession(false).getAttribute("eDate") != null) {
                    httpServletRequest.getSession(false).setAttribute("eDate",null);
                }
                if(httpServletRequest.getSession(false).getAttribute("isFirst") != null) {
                    httpServletRequest.getSession(false).setAttribute("isFirst","yes");
                }
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
              
                 if(empType1.equalsIgnoreCase("c") || empType1.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                   boolean flag = DataSourceDataProvider.getInstance().checkAssociatedProject(resourceId);
                    if(!flag){
                     httpServletRequest.getSession(false).setAttribute("errorMessage","You are not associated to any project please contact concerned team.");
                     resultType = "error";
                 }else{
                     resultType = SUCCESS;
                 }
                }
                else{
                      connection = ConnectionProvider.getInstance().getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT EmployeeTypeId from tblEmployee WHERE loginId='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"'");
                resultSet.next();
                String empTye = resultSet.getString("EmployeeTypeId");
                if(empTye.equals("Onsite")) {
                    setIsOnsite("Onsite");
                }
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                   resultType = SUCCESS;
                }
              //--  setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));   
               
            //    System.out.println(resultType);
            }catch(Exception ex) {
                
                ex.printStackTrace();
            }finally {
                try {
                    if(resultSet!=null) {
                        resultSet.close();
                        resultSet = null;
                    }
                    if(statement!=null) {
                        statement.close();
                        statement = null;
                    }
                    if(connection!=null) {
                        connection.close();
                        connection = null;
                    }
                    
                }catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return resultType;
    }
    
       
    
    /**
     * This method can be used to seacrh the TimeSheet based on the employee Id
     *  start date and end date of the timesheet.
     *
     * @return resultType a String.
     *
     * @throws Exception.
     */
   /* public String newFillMyTimesheet()throws ServiceLocatorException {
     //   System.out.println("in fillMyTimesheet");
        resultType = LOGIN;
        
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
             
             String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if(empType.equalsIgnoreCase("c") || empType.equalsIgnoreCase("v")){
                resultType = "custaccessFailed";
            }else {
             resultType = "accessFailed";
            }
            if(AuthorizationManager.getInstance().isAuthorizedUser("FILL_TIMESHEET",userRoleId)){
               
                
                resultType = INPUT;
                 String resourceId="";
             //   String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if(empType.equalsIgnoreCase("c") || empType.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                 setEmpID(resourceId);
                 setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                 setEmpID(resourceId);
                 setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                }
                //-- setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));   
                
                setAssociatedProjectsCount(DataSourceDataProvider.getInstance().getEmpAssociatedProjectsCount(Integer.parseInt(resourceId)));
                // for checking Timesheet exists 
                String isTimeSheetExist = "";
                
                
                // Reports checking start
                
                  boolean isReportsToExist = false;
                  //setEmpType(empType);
  if(empType.equalsIgnoreCase("e")){
        
                 if(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_REPORTSTOFLAG).toString())==0) {
					String reportsToId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_REPORTSTO_ID).toString();
						if(!"".equals(reportsToId)) {
						isReportsToExist = true;
						}
                    
                 }  else {
                   String  reportsToDetails = DataSourceDataProvider.getInstance().getReportsToDetails(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                  
                   if(reportsToDetails!=null && !"".equals(reportsToDetails)) {
                  
				  isReportsToExist = true;
                 }
                 } 
                 
            }else{
       
              String  reportsToDetails = DataSourceDataProvider.getInstance().getReportsToDetails(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
             if(reportsToDetails!=null && !"".equals(reportsToDetails)) {  
			 isReportsToExist = true;
             
                       }
            }
                
                
                // Reports checking end
                
                if(isReportsToExist) {
                if(httpServletRequest.getParameter("checkDate")!=null){
                    if(getPreviousWeek()!=null && !getPreviousWeek().toString().trim().equals("") && httpServletRequest.getParameter("checkDate").equals("PreviousWeek")) {
                        
                        String stortingDate=new java.text.SimpleDateFormat("MM/dd/yyyy").format(getPreviousWeek());
                        String splitDate[]=stortingDate.split("/");
                        int mon=Integer.parseInt(splitDate[0]);
                        int  day=Integer.parseInt(splitDate[1]);
                        int  year=Integer.parseInt(splitDate[2]);
                        
                        // for setting the date for the spliting filed 
                        Calendar previouseCalender = Calendar.getInstance();
                        
                        previouseCalender.set(Calendar.YEAR,year);
                        previouseCalender.set(Calendar.MONTH,mon-1); // becoz the index is starting 0
                        previouseCalender.set(Calendar.DAY_OF_MONTH,day);
                        
                        
                        List previousWeekDaysList = ServiceLocator.getNewTimeSheetService().getweekStartAndEndDays(previouseCalender);
                        
                        
                        //isTimeSheetExist  = ServiceLocator.getTimeSheetService().checkTimeSheetExists(previousWeekDaysList,getEmpID());
                        isTimeSheetExist  = ServiceLocator.getNewTimeSheetService().checkTimeSheetExists(previousWeekDaysList,getEmpID(),empType);
                        
                        
                        if(isTimeSheetExist.equals("exist")) {
                            resultMessage = "<font color=\"red\" size=\"1.5\">The TimeSheet already Filled for  Week Starting Date :"+previousWeekDaysList.get(0).toString()+" And EndDate :"+previousWeekDaysList.get(1).toString()+"</font>";
                        }else if(isTimeSheetExist.equals("notallow")) {
                            resultMessage = "<font color=\"red\" size=\"1.5\">The TimeSheet is not allowed for Week Starting Date :"+previousWeekDaysList.get(0).toString()+" And EndDate :"+previousWeekDaysList.get(1).toString()+"</font>";
                        } else {
                           
                            setIflag(0);
                            NewTimeSheetVTO timeSheetVTO = ServiceLocator.getNewTimeSheetService().getWeekDaysBean(previousWeekDaysList);
                            setTimeSheetVTO(timeSheetVTO);
                            resultType = SUCCESS;
                        } // else
                        
                    } else if(httpServletRequest.getParameter("checkDate").equals("ThisWeek")) {
                        Calendar currentCalender = Calendar.getInstance();
                
                        List currentWeekDaysList = ServiceLocator.getNewTimeSheetService().getweekStartAndEndDays(currentCalender);
                        
                       
                        isTimeSheetExist = ServiceLocator.getNewTimeSheetService().checkTimeSheetExists(currentWeekDaysList,getEmpID(),empType);
                        if(isTimeSheetExist.equals("exist")) {
                            resultMessage = "<font color=\"red\" size=\"1.5\">The TimeSheet already Filled for  Week Starting Date :"+currentWeekDaysList.get(0).toString()+" And EndDate : "+currentWeekDaysList.get(1).toString()+"</font>";
                        }else if(isTimeSheetExist.equals("exist")) {
                            resultMessage = "<font color=\"red\" size=\"1.5\">The TimeSheet is not allowed for  Week Starting Date :"+currentWeekDaysList.get(0).toString()+" And EndDate : "+currentWeekDaysList.get(1).toString()+"</font>";
                        } else {
                           
                            NewTimeSheetVTO timeSheetVTO = ServiceLocator.getNewTimeSheetService().getWeekDaysBean(currentWeekDaysList);
                            setTimeSheetVTO(timeSheetVTO);
                            resultType = SUCCESS;
                        } // if
                    } // if
                } // if
                
                }else {
                    resultMessage = "<font color=\"red\" size=\"1.5\">Please contact HR/PMO team for reporting person updation! </font>";
                }				
				
				
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    } //  @ execute
    */
    
    
 public String newFillMyTimesheet()throws ServiceLocatorException {
     //   System.out.println("in fillMyTimesheet");
        resultType = LOGIN;
         String empLocation = "";
         try {
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
             
             String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
           //  String empLocation = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SES_EMP_LOCATION).toString();
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            if(empType.equalsIgnoreCase("c") || empType.equalsIgnoreCase("v")){
                resultType = "custaccessFailed";
            }else {
             resultType = "accessFailed";
            }
            if(AuthorizationManager.getInstance().isAuthorizedUser("FILL_TIMESHEET",userRoleId)){
                
                
                resultType = INPUT;
                 String resourceId="";
         //System.out.println("CheckDate-->"+httpServletRequest.getParameter("checkDate"));
         //System.out.println("getPreviousWeek-->"+getPreviousWeek());
         
                if(empType.equalsIgnoreCase("c") || empType.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                 setEmpID(resourceId);
                 setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                 empLocation = "";
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                 setEmpID(resourceId);
                 //System.out.println("EmpNo-->"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SES_EMP_NO));
                 //System.out.println("EmpNo-->"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SES_EMP_NO).toString());
                 setEmpNo(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SES_EMP_NO).toString());
                 setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                 if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SES_EMP_LOCATION)!=null)
                 empLocation = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SES_EMP_LOCATION).toString();
                }
                //-- setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));   
                
                setAssociatedProjectsCount(DataSourceDataProvider.getInstance().getEmpAssociatedProjectsCount(Integer.parseInt(resourceId)));
                /* for checking Timesheet exists */
                String isTimeSheetExist = "";
                
                
                // Reports checking start
                
                  boolean isReportsToExist = false;
                  //setEmpType(empType);
                  boolean isDualReq = false;
                  
  if(empType.equalsIgnoreCase("e")){
        isDualReq = DataSourceDataProvider.getInstance().getDualFlagByObjectId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                //--
        
        if(!isDualReq) {
        if(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_REPORTSTOFLAG).toString())==0) {
					String reportsToId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_REPORTSTO_ID).toString();
						if(!"".equals(reportsToId)) {
						isReportsToExist = true;
						}else {
                                                     isReportsToExist = false;
                   resultMessage = "<font color=\"red\" size=\"1.5\">Please contact HR/PMO team for reporting person updation! </font>";
                                                }
                    
                 }  else {
                   String  reportsToDetails = DataSourceDataProvider.getInstance().getReportsToDetails(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                  
                   if(reportsToDetails!=null && !"".equals(reportsToDetails)) {
                  
				  isReportsToExist = true;
                 }else {
                                                     isReportsToExist = false;
                   resultMessage = "<font color=\"red\" size=\"1.5\">Please contact HR/PMO team for project reporting person updation! </font>";
                                                }
                 } 
        }else {
             if(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_REPORTSTOFLAG).toString())==0) {
                 String reportsToId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_REPORTSTO_ID).toString();
              
                 if(!"".equals(reportsToId)) {
						isReportsToExist = true;
						}else {
                                                     isReportsToExist = false;
                    resultMessage = "<font color=\"red\" size=\"1.5\">Please contact your Hr/Pmo for Reporting person updation!</font>";
                                                }
                 // resultMessage = "<font color=\"red\" size=\"1.5\">Your primary project enabled with dual reporting.So please contact Hr/Pmo team for proper reportsTo updation!</font>";
                
             }else {
                  String  reportsToDetails = DataSourceDataProvider.getInstance().getReportsToDetails(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                //  String  secReportsToDetails = DataSourceDataProvider.getInstance().getSecReportsToDetails(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                  // if(reportsToDetails!=null && !"".equals(reportsToDetails)&&secReportsToDetails!=null && !"".equals(secReportsToDetails)) {
                   if(reportsToDetails!=null && !"".equals(reportsToDetails)) {
                       isReportsToExist = true;
                   }else{
                        resultMessage = "<font color=\"red\" size=\"1.5\">Your primary project enabled with dual reporting.So please contact Hr/Pmo team for proper reporting person updation!</font>";
                       isReportsToExist = false;
                   }
             }
        }
                 //---
            }else{
        isDualReq = DataSourceDataProvider.getInstance().getDualFlagByObjectId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
        
              String  reportsToDetails = DataSourceDataProvider.getInstance().getReportsToDetails(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
            if(!isDualReq) {
              if(reportsToDetails!=null && !"".equals(reportsToDetails)) {  
			 isReportsToExist = true;
             
                       }else {
                   isReportsToExist = false;
                   resultMessage = "<font color=\"red\" size=\"1.5\">Please contact HR/PMO team for reporting person updation! </font>";
              }
            }else {
                //String  secReportsToDetails = DataSourceDataProvider.getInstance().getSecReportsToDetails(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                // if(reportsToDetails!=null && !"".equals(reportsToDetails)&&secReportsToDetails!=null && !"".equals(secReportsToDetails)) {
                 if(reportsToDetails!=null && !"".equals(reportsToDetails)) {
                     isReportsToExist = true;
                 }else {
                      isReportsToExist = false;
                      resultMessage = "<font color=\"red\" size=\"1.5\">Your primary project enabled with dual reporting.So please contact Hr/Pmo team for proper reporting person updation!</font>";
                 }
            }
            }
                
                
                // Reports checking end
                
                if(isReportsToExist) {
                if(httpServletRequest.getParameter("checkDate")!=null){
                    if(getPreviousWeek()!=null && !getPreviousWeek().toString().trim().equals("") && httpServletRequest.getParameter("checkDate").equals("PreviousWeek")) {
                        
                        //String stortingDate=new java.text.SimpleDateFormat("MM/dd/yyyy").format(getPreviousWeek());
                        String stortingDate=getPreviousWeek();
                        String splitDate[]=stortingDate.split("/");
                        int mon=Integer.parseInt(splitDate[0]);
                        int  day=Integer.parseInt(splitDate[1]);
                        int  year=Integer.parseInt(splitDate[2]);
                        
                        /* for setting the date for the spliting filed */
                        Calendar previouseCalender = Calendar.getInstance();
                        
                        previouseCalender.set(Calendar.YEAR,year);
                        previouseCalender.set(Calendar.MONTH,mon-1); // becoz the index is starting 0
                        previouseCalender.set(Calendar.DAY_OF_MONTH,day);
                        
                        /**
                         * For generating the weekdays
                         *{@Link com.mss.mirage.employee.timesheets.TimeSheetService#getweekStartAndEndDays(cal)}
                         */
                        List previousWeekDaysList = ServiceLocator.getNewTimeSheetService().getweekStartAndEndDays(previouseCalender);
                        
                        /**
                         * To check wethere the timesheet exists or not
                         * {@Link com.mss.mirage.employee.timesheets.TimeSheetService#checkTimeSheetExists(List,String)}
                         */
                        //isTimeSheetExist  = ServiceLocator.getTimeSheetService().checkTimeSheetExists(previousWeekDaysList,getEmpID());
                        isTimeSheetExist  = ServiceLocator.getNewTimeSheetService().checkTimeSheetExists(previousWeekDaysList,getEmpID(),empType);
                        
                        
                        if(isTimeSheetExist.equals("exist")) {
                            resultMessage = "<font color=\"red\" size=\"1.5\">The TimeSheet already Filled for  Week Starting Date :"+previousWeekDaysList.get(0).toString()+" And EndDate :"+previousWeekDaysList.get(1).toString()+"</font>";
                        }else if(isTimeSheetExist.equals("notallow")) {
                            resultMessage = "<font color=\"red\" size=\"1.5\">The TimeSheet is not allowed for Week Starting Date :"+previousWeekDaysList.get(0).toString()+" And EndDate :"+previousWeekDaysList.get(1).toString()+"</font>";
                        } else {
                            /**
                             *{@Link com.mss.mirage.employee.timesheets.TimeSheetService#getWeekDaysBean(List)}
                             */
                            setIflag(0);
                            NewTimeSheetVTO timeSheetVTO = ServiceLocator.getNewTimeSheetService().getWeekDaysBean(previousWeekDaysList);
                            setTimeSheetVTO(timeSheetVTO);
                            if(empType.equalsIgnoreCase("e")){
      setLeaveDates(com.mss.mirage.util.DataSourceDataProvider.getInstance().getEmpLeavesForTheGivenWeek(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString(), previousWeekDaysList.get(0).toString(), previousWeekDaysList.get(1).toString()));
                            }
                             if(("Miracle City".equalsIgnoreCase(empLocation)||"Miracle Heights".equalsIgnoreCase(empLocation)||"LB Office".equalsIgnoreCase(empLocation))&&!"0".equals(getEmpNo()))
                            ServiceLocator.getNewTimeSheetService().getBiometricHours(this);
                            resultType = SUCCESS;
                        } // else
                        
                    } else if(httpServletRequest.getParameter("checkDate").equals("ThisWeek")) {
                        Calendar currentCalender = Calendar.getInstance();
                  /**
                         * For generating the weekdays
                         *{@Link com.mss.mirage.employee.timesheets.TimeSheetService#getweekStartAndEndDays(cal)}
                         */
                        List currentWeekDaysList = ServiceLocator.getNewTimeSheetService().getweekStartAndEndDays(currentCalender);
                        
                        /**
                         * To check wethere the timesheet exists or not
                         * {@Link com.mss.mirage.employee.timesheets.TimeSheetService#checkTimeSheetExists(List,String)}
                         */
                        isTimeSheetExist = ServiceLocator.getNewTimeSheetService().checkTimeSheetExists(currentWeekDaysList,getEmpID(),empType);
                        if(isTimeSheetExist.equals("exist")) {
                            resultMessage = "<font color=\"red\" size=\"1.5\">The TimeSheet already Filled for  Week Starting Date :"+currentWeekDaysList.get(0).toString()+" And EndDate : "+currentWeekDaysList.get(1).toString()+"</font>";
                        }else if(isTimeSheetExist.equals("exist")) {
                            resultMessage = "<font color=\"red\" size=\"1.5\">The TimeSheet is not allowed for  Week Starting Date :"+currentWeekDaysList.get(0).toString()+" And EndDate : "+currentWeekDaysList.get(1).toString()+"</font>";
                        } else {
                            /**
                             *{@Link com.mss.mirage.employee.timesheets.TimeSheetService#getWeekDaysBean(List)}
                             */
                            NewTimeSheetVTO timeSheetVTO = ServiceLocator.getNewTimeSheetService().getWeekDaysBean(currentWeekDaysList);
                            setTimeSheetVTO(timeSheetVTO);
                             if(empType.equalsIgnoreCase("e")){
      setLeaveDates(com.mss.mirage.util.DataSourceDataProvider.getInstance().getEmpLeavesForTheGivenWeek(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString(), currentWeekDaysList.get(0).toString(), currentWeekDaysList.get(1).toString()));
                            }
                             //if("Miracle City".equalsIgnoreCase(empLocation)||"Miracle Heights".equalsIgnoreCase(empLocation)||"LB Office".equalsIgnoreCase(empLocation))
                            if(("Miracle City".equalsIgnoreCase(empLocation)||"Miracle Heights".equalsIgnoreCase(empLocation)||"LB Office".equalsIgnoreCase(empLocation))&&!"0".equals(getEmpNo()))
                            ServiceLocator.getNewTimeSheetService().getBiometricHours(this);
                            resultType = SUCCESS;
                        } // if
                    } // if
                } // if
                
                }//else {
                   // resultMessage = "<font color=\"red\" size=\"1.5\">Please contact HR/PMO team for reporting person updation! </font>";
               // }				
				
				
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            }//END-Authorization Checking
        }//Close Session Checking
         }catch(Exception exception){
             exception.printStackTrace();
         }
        return resultType;
    } //  @ execute
	
    
    
    
     public String newDeleteTimeSheet() {
        resultType = LOGIN;
       // System.out.println("in delete  action");
        //if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
           // resultType = "accessFailed";
              String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
             if(empType.equalsIgnoreCase("c") || empType.equalsIgnoreCase("v")){
                resultType = "custaccessFailed";
            }else {
             resultType = "accessFailed";
            }
            if(AuthorizationManager.getInstance().isAuthorizedUser("DELETE_TIMESHEET",userRoleId)){
                try{
                    String resourceId="";
                String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if(empType1.equalsIgnoreCase("c") || empType1.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                
                }
               //-- setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                    //System.out.println("in action");
                    int deletedRows = ServiceLocator.getNewTimeSheetService().deleteTimeSheet(Integer.parseInt(getId()),Integer.parseInt(getEmpID()),Integer.parseInt(getTimeSheetID()));
                    //System.out.println("in action after");
                    if(deletedRows > 1){
                        
                        resultType = SUCCESS;
                        resultMessage = "<font color=\"green\" size=\"1.5\">TimeSheet has been successfully Deleted!</font>";
                    }else{
                        resultType = INPUT;
                        resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    resultType=SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    
    
    
     public String newDoAdd() {
        resultType = LOGIN;
       // System.out.println("in do add project id-->"+getProjectId());
       // if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_TIMESHEET",userRoleId)){
                try{
                    int isTimeSheetAdd = ServiceLocator.getNewTimeSheetService().newAddTimeSheet(this);
                   // System.out.println("isTimeSheetAdd-->"+isTimeSheetAdd);
                   /* if(isTimeSheetAdd>0){
                        MailManager mail = new MailManager();
                        setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                         if(Properties.getProperty("Mail.Flag").equals("1")) {
                        mail.sendReminders(getEmpName(),String.valueOf(isTimeSheetAdd),getEmpID());
                         }
                        resultMessage="<font color=\"green\" size=\"1.5\">The TimeSheet Successfully Added for WeekStartDate: "+getWstDate()+" And WeekEndDate:"+getWenDate()+"</font>";
                    }*/
                    if(isTimeSheetAdd>0){
                        //change
                        /*
                        if(empType.equalsIgnoreCase("e")){
                         setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                        }else{
                         setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                        }*/ //change end
                        String resourceId="";
               // String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if(empType.equalsIgnoreCase("c") || empType.equalsIgnoreCase("v")){
                    setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                }
                else{
                      setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                
                }
              //  setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                         
                        resultMessage="<font color=\"green\" size=\"1.5\">The TimeSheet Successfully Added for WeekStartDate: "+getWstDate()+" And WeekEndDate:"+getWenDate()+"</font>";
                    }
                    else
                        resultMessage="<font color=\"red\" size=\"1.5\">Error occour Adding for WeekStartDate: "+getWstDate()+" And WeekEndDate:"+getWenDate()+"</font>";
                    
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    resultType = SUCCESS;
                }catch (Exception ex){
                   // ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
     public String newPrepare() throws Exception {
        resultType = LOGIN;
        //System.out.println("in prepare");
       // if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if(httpServletRequest.getSession(false)!=null){
        if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            //System.out.println("after session  prepare");
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            String resourceId="";
                //String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if(empType.equalsIgnoreCase("c") || empType.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                 setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                }
                else{
                    setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                
                }
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_TIMESHEET",userRoleId)){
              //  System.out.println("after authorization  prepare");
                try{
                    /**
                     * for setting the Timesheet object
                     * {@Link com.mss.mirage.employee.timesheets.TimeSheetService#getTimeSheet(int,int)}
                     */
                    
                    setIflag(1);
                    if(getEmpID() == null || "".equals(getEmpID())) {
                    String employeeID = httpServletRequest.getParameter("employeeID").toString();
                    setEmpID(employeeID);
                    
                }
                /*  if(getVal()==1)
                  {
                  httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_CUST_DESIG,"OR");
                  }*/
               // setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                    if(getTimeSheetID() == null || "".equals(getTimeSheetID())) {
                        String employeeTimesheetId = httpServletRequest.getParameter("emptimeSheetID").toString();
                    setTimeSheetID(employeeTimesheetId);
                    }
                    if(getResourceType()==null){
                        setResourceType(DataSourceDataProvider.getInstance().getTimeSheetResourceTypeByEmpId(Integer.parseInt(getEmpID()),Integer.parseInt(getTimeSheetID())));
                    }
                    setTimeSheetVTO(ServiceLocator.getNewTimeSheetService().newGetTimeSheet(Integer.parseInt(getEmpID()),Integer.parseInt(getTimeSheetID()),empType,getResourceType()));
                   setFileFlagValue(getTimeSheetVTO().getFileFlagValue());
                    // System.out.println("after session setTimeSheetVTO prepare");
                    //Invoking getTimeSheetStatus() method to get the status of the TimeSheet.
                    
                    //statusValue = newGetTimeSheetStatus(empType);
                  //  System.out.println("--- prepare-->"+statusValue);
             /*       if(statusValue.equals("Entered")) {
                        setTimeSheetStat("Enter");
                    }
                    if(statusValue.equals("Disapproved")) {
                        setTimeSheetStat("Reject");
                    }
                    if(statusValue.equals("Submitted")) {
                        setTimeSheetStat("Submit");
                    }
                    if(statusValue.equals("Approved")) {
                        setTimeSheetStat("Approve");
                    }
                    
                    
                    if(secStatusValue!=null && !"".equals(secStatusValue)){
                     if(secStatusValue.equals("Entered")) {
                        setTimeSheetSecStat("Enter");
                    }
                    if(secStatusValue.equals("Disapproved")) {
                        setTimeSheetSecStat("Reject");
                    }
                    if(secStatusValue.equals("Submitted")) {
                        setTimeSheetSecStat("Submit");
                    }
                    if(secStatusValue.equals("Approved")) {
                        setTimeSheetSecStat("Approve");
                    }
                    }*/
                    

if(getTimeSheetVTO().getTimeSheetStatusTypeId()==1) {
                        setTimeSheetStat("Enter");
                    }
                    if(getTimeSheetVTO().getTimeSheetStatusTypeId()==10) {
                        setTimeSheetStat("Reject");
                    }
                     if(getTimeSheetVTO().getTimeSheetStatusTypeId()==2) {
                        setTimeSheetStat("Submit");
                    }
                   if(getTimeSheetVTO().getTimeSheetStatusTypeId()==3) {
                        setTimeSheetStat("Approve");
                    }
                    if(getTimeSheetVTO().getSecondReportsToStatusTypeId()==1) {
                        setTimeSheetSecStat("Enter");
                    }
                     if(getTimeSheetVTO().getSecondReportsToStatusTypeId()==10) {
                        setTimeSheetSecStat("Reject");
                    }
                    if(getTimeSheetVTO().getSecondReportsToStatusTypeId()==2) {
                        setTimeSheetSecStat("Submit");
                    }
                    if(getTimeSheetVTO().getSecondReportsToStatusTypeId()==3) {
                        setTimeSheetSecStat("Approve");
                    }
					
					
                   // setTeamType(getTeamType());
                    
                   //  setTeamType(DataSourceDataProvider.getInstance().getReportsTypeByTeamMemberId(Integer.parseInt(getEmpID()),Integer.parseInt(resourceId)));
                      if("E".equalsIgnoreCase(getResourceType())){
                        int reportsToCheck = DataSourceDataProvider.getInstance().getReportsToCheck(Integer.parseInt(getEmpID()));
                        
                     //   System.out.println("reportsToCheck-->"+reportsToCheck);
                        if(reportsToCheck==0){
                            setTeamType(1);
                        }else {
                          setTeamType(DataSourceDataProvider.getInstance().getReportsTypeByTeamMemberId(Integer.parseInt(getEmpID()),Integer.parseInt(resourceId)));  
                        }
                        
                    }else {
                     setTeamType(DataSourceDataProvider.getInstance().getReportsTypeByTeamMemberId(Integer.parseInt(getEmpID()),Integer.parseInt(resourceId)));
                    }
                      
                       if("E".equals(getResourceType())){
                     String empLocation  = DataSourceDataProvider.getInstance().getEmpLocationByEmpId(Integer.parseInt(getEmpID()));
                     setEmployeeLocation(empLocation);
                     setEmpNo(DataSourceDataProvider.getInstance().getEmpNoByEmpId(Integer.parseInt(getEmpID())));
                   if(getTimeSheetVTO().getBiometricFlag()==0&&("Miracle City".equalsIgnoreCase(empLocation)||"Miracle Heights".equalsIgnoreCase(empLocation)||"LB Office".equalsIgnoreCase(empLocation))){
                        ServiceLocator.getNewTimeSheetService().getBiometricHours(this);
                    }   
                 }
                   //  System.out.println("getTeamType-->"+getTeamType());
                    //System.out.println("SUCCESS");
                    //resultType = SUCCESS;
                    // System.out.println("emptimesheetId-->"+httpServletRequest.getParameter("emptimeSheetID")+"--->"+ httpServletRequest.getParameter("type"));
                    return SUCCESS;
                }catch (Exception ex){
                    ex.printStackTrace();
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    //resultType =  ERROR;
                    return ERROR;
                }
            }//END-Authorization Checking
        }
        }
        //Close Session Checking
       
       //if(httpServletRequest.getParameter("emptimeSheetID") != null && httpServletRequest.getParameter("type")!=null){
         if(httpServletRequest.getParameter("emptimeSheetID") != null ){
           //System.out.println("in emptimeSheetID");
            String employeeID = httpServletRequest.getParameter("employeeID");
            String emptimeSheetID = httpServletRequest.getParameter("emptimeSheetID");
            String resourceType = httpServletRequest.getParameter("resourceType");
            //String description = httpServletRequest.getParameter("statusValue");
           // String secStatusValue = httpServletRequest.getParameter("secStatusValue");
           // String teamType = httpServletRequest.getParameter("teamType");
         //   String priProjId = httpServletRequest.getParameter("priProjId");
            httpServletRequest.setAttribute("employeeID",employeeID);
            httpServletRequest.setAttribute("emptimeSheetID",emptimeSheetID);
            httpServletRequest.setAttribute("resourceType",resourceType);
           // httpServletRequest.setAttribute("statusValue",description);
           // httpServletRequest.setAttribute("secStatusValue",secStatusValue);
           // httpServletRequest.setAttribute("teamType",teamType);
            //httpServletRequest.setAttribute("priProjId",priProjId);
            setEmptimesheetid(Integer.parseInt(emptimeSheetID));
            setEmployeeid(Integer.parseInt(employeeID));
            //String etype = httpServletRequest.getParameter("type");
            //setType(etype);
            //setEmpType(etype);
            //System.out.println("emptimsheetId-->"+emptimeSheetID);
            //System.out.println("empId-->"+employeeID);
            //System.out.println("type-->"+httpServletRequest.getParameter("type").toString());
            // New parameter by nag
            
            String empTypeForTimesheet = httpServletRequest.getParameter("type").toString();
            
            if(empTypeForTimesheet.equalsIgnoreCase("C"))
            resultType = INPUT;
            else if(empTypeForTimesheet.equalsIgnoreCase("V"))
                resultType = "INPUT1";
            else
                resultType = LOGIN;
            //return resultType;
        }

         
        return resultType;
    }



    /*
    
   public String newGetTimeSheetStatus(String empType) throws SQLException {
        String status = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         Statement statement1 = null;
        ResultSet resultSet1 = null;
         Connection connection1 = null;
        String queryString = "";
        String queryString1 = "";
        try {
           // System.out.println("empType--->"+empType);
            connection = ConnectionProvider.getInstance().getConnection();
             connection1 = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            
              queryString1="select * from tblEmployee where Id="+getEmpID();
                  
                statement1 =connection1.createStatement();
            resultSet1 =statement1.executeQuery(queryString1); 
            if(resultSet1.next())
            {
        queryString ="SELECT Description FROM vwNewTimeSheetList WHERE EmpId = '"+getEmpID()+"' AND TimeSheetId = '"+getTimeSheetID()+"'";
            }else
            {
               queryString ="SELECT Description FROM vwNewCustTimeSheetList WHERE EmpId = '"+getEmpID()+"' AND TimeSheetId = '"+getTimeSheetID()+"'";  
            }  
            
           // System.out.println("queryString--->"+queryString);
            resultSet = statement.executeQuery(queryString);
            resultSet.next();
            status = resultSet.getString("Description");
            //    System.out.println("status--->"+status);
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                if(resultSet!=null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null) {
                    statement.close();
                    statement = null;
                }
                if(connection!=null) {
                    connection.close();
                    connection = null;
                }
                       if(resultSet1!=null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if(statement1!=null) {
                    statement1.close();
                    statement1 = null;
                }
                if(connection1!=null) {
                    connection1.close();
                    connection1 = null;
                }
            }catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        return status;
    }
     
    */
    public String newDoEdit(){
        resultType = LOGIN;
        String empName = "";
        //if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            
            String eType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_EDIT_TIMESHEET",userRoleId)){
                try{
                    /**
                     * Calling the business logic method for saving the info
                     * {@Link com.mss.mirage.employee.timesheets.TimeSheetService#editTimeSheet(TimeSheetAction)}
                     */
                    setEmpType(eType);
                    String resourceId="";
              
                if(eType.equalsIgnoreCase("c") || eType.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                }
             
                    boolean isTimeSheetEdit = ServiceLocator.getNewTimeSheetService().newEditTimeSheet(this);
                    if(getTempVar() == 2) {
                       
                         String reportsTo = "";
                        
                         String reportsToType = null;
                         String reportsToEmail = null;
                         
                          if(getEmpType().equalsIgnoreCase("e")){
                 setResourceType("e");
                 setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                
        
                 if(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_REPORTSTOFLAG).toString())==0) {
                    // reportsToEmail = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_REPORTSTO_ID).toString()+"@miraclesoft.com";
                       reportsToEmail = DataSourceDataProvider.getInstance().getReportsToEmailByEmployeeId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString()));
                     reportsToType = "e";
                 }  else {
                   String  reportsToDetails = DataSourceDataProvider.getInstance().getReportsToDetails(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                  // System.out.println("reportsToDetails-->"+reportsToDetails);
                   if(!"".equals(reportsToDetails)) {
                   reportsToEmail = reportsToDetails.split("\\|")[0];
                   reportsToType = reportsToDetails.split("\\|")[1];
                 }
                 } 
                 
            }else{
                 setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                 String resourceType= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
               setResourceType(resourceType);
              String  reportsToDetails = DataSourceDataProvider.getInstance().getReportsToDetails(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
             // System.out.println("reportsToDetails-->"+reportsToDetails);
              
             // if(!"".equals(reportsToDetails)) {  
               if(reportsToDetails!=null) {  
              reportsToEmail = reportsToDetails.split("\\|")[0];
                   reportsToType = reportsToDetails.split("\\|")[1];
                       }
            }
                    
            
                      if(Properties.getProperty("Mail.Flag").equals("1")&&!"".equals(reportsToEmail)&&!"".equals(reportsToType)) {
                       //  String bodyConetent = ServiceLocator.getNewTimeSheetService().getSubmitTimeSheetEmailLogBody(getEmpName(),getWstDate(),getWenDate(),resourceId,reportsToType,Integer.parseInt(getTimeSheetID()));        
                          //  String bodyConetent = ServiceLocator.getNewTimeSheetService().getSubmitTimeSheetEmailLogBody(getEmpName(),getWstDate(),getWenDate(),resourceId,reportsToType,Integer.parseInt(getTimeSheetID()),getResourceType(),"Submitted");        
                          // String bodyConetent = ServiceLocator.getNewTimeSheetService().getSubmitTimeSheetEmailLogBody(getEmpName(),getWstDate(),getWenDate(),resourceId,reportsToType,Integer.parseInt(getTimeSheetID()),getResourceType(),"Submitted","Entered");        
                           String bodyConetent = ServiceLocator.getNewTimeSheetService().getSubmitTimeSheetEmailLogBody(getEmpName(),getWstDate(),getWenDate(),resourceId,reportsToType,Integer.parseInt(getTimeSheetID()),getResourceType());        
                          String subject = "TimeSheet Reminder";
                        //  ServiceLocator.getMailServices().doAddEmailLog(reportsToEmail, "nseerapu@miraclesoft.com", subject, bodyConetent, "nseerapu@miraclesoft.com");
                          String timesheetCC = Properties.getProperty("Timesheet.CC");
                          String timesheetBCC = Properties.getProperty("Timesheet.BCC");
                            //ServiceLocator.getMailServices().doAddEmailLog(reportsToEmail, timesheetCC, subject, bodyConetent,"", timesheetBCC);
                          ServiceLocator.getMailServices().doAddEmailLogNew(reportsToEmail, timesheetCC, subject, bodyConetent,"", timesheetBCC,"Timesheets");
                        }
                    }        
                    
                    
                    if(isTimeSheetEdit)
                        resultMessage="<font color=\"green\" size=\"1.5\">The TimeSheet Successfully Edited for WeekStartDate: "+getWstDate()+" And WeekEndDate:"+getWenDate()+"</font>";
                    else
                        resultMessage="<font color=\"red\" size=\"1.5\">Error occour Editing for WeekStartDate: "+getWstDate()+" And WeekEndDate:"+getWenDate()+"</font>";
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    
  /*   public String newTeamTimeSheet() {
        resultType = LOGIN;
        String list = "";
        String queryString = "";
        String loginId = "";
       // System.out.println("team tiomesheets");
        
       // if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            
          try{
           DataSourceDataProvider dataSourceDataProvider = DataSourceDataProvider.getInstance();
              String resourceId="";
              //  String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
          //change
            
             
                setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
           //change     setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId))); change end
           
             if(empType.equalsIgnoreCase("e")){
           //change String  empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(resourceId)));
                 loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                  list=ServiceLocator.getTimeSheetService().getTeamMembersList(loginId);
                  setProjectMap(DataSourceDataProvider.getInstance().getProjectsMapByEmpId(Integer.parseInt(resourceId)));
            }else{
                  resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();               
                  loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                   list = loginId;
               String roleId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_ROLE).toString();    
                  
               setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
             }
             
            // setMyProjects(DataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
               
               setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
              
              // setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
                //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
        //change     
       
           
          // System.out.println("getEmpnamesList---->"+getEmpnamesList());
          // System.out.println("setMyTeamMembers---->"+getMyTeamMembers());
          // System.out.println("loginId---->"+loginId);
           
            if(httpServletRequest.getSession(false).getAttribute("sDate") != null) {
                httpServletRequest.getSession(false).setAttribute("sDate",null);
            }
            if(httpServletRequest.getSession(false).getAttribute("eDate") != null) {
                httpServletRequest.getSession(false).setAttribute("eDate",null);
            }
            if(httpServletRequest.getSession(false).getAttribute("isFirstTeam") != null) {
                httpServletRequest.getSession(false).setAttribute("isFirstTeam","yes");
            }
            //change
           
            //For Single Level
           //System.out.println(getEmployeeid()+"In Team timeSheets.");
           queryStringBuffer = new StringBuffer();
           
           
            if(getSubmitFrom()==null){
                
                if(empType.equalsIgnoreCase("E")){
                    if(getEmpCusType() == null){
                        queryString = "SELECT * FROM vwNewTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) ";
                    }
                  else  if(getEmpCusType().equalsIgnoreCase("Internal")){
                    
                    queryString = "SELECT * FROM vwNewTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) ";
                    }
                    else
                    {
                        String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                   // queryString = "SELECT * FROM vwCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where ReportsTo in ("+empId+") AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") AND ProjectTeamId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+")) AND ProjectId!=0 AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") AND ProjectTeamId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+")";
                         queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where ReportsTo in ("+empId+") AND STATUS = 'Active') ";
                    }
                }else{
                 //  queryString = "SELECT * FROM vwCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where ReportsTo in ("+list+") AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") ) AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") ";
                     queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE ReportsTo IN("+list+") AND STATUS = 'Active' )  ";
                }
                
                
                    queryStringBuffer.append(queryString);
                    if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        queryStringBuffer.append(" AND date(DateStart) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        queryStringBuffer.append(" AND date(DateEnd) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
                    if((getEmpnameById()!=null && !"".equals(getEmpnameById()))  ||    (getCustnameById()!=null && !"".equals(getCustnameById())) )   {
                        
                     
                        if("All".equals(getEmpnameById()) && "All".equals(getCustnameById())){
                            if(empType.equalsIgnoreCase("E")){
                                if(getEmpCusType().equalsIgnoreCase("Internal")){
                                queryStringBuffer.append(" AND (LoginId like '%')");
                                }else{
                                queryStringBuffer.append(" AND (EmpId like '%')");
                                }
                            }else{
                                queryStringBuffer.append(" AND (EmpId like '%')");
                            }
                        //queryStringBuffer.append(" AND (LoginId like '%')");
                            
                        }
                        else{
                            if(empType.equalsIgnoreCase("E")){
                                if(getEmpCusType().equalsIgnoreCase("Internal")){
                                queryStringBuffer.append(" AND (LoginId like '"+getEmpnameById()+"')");
                                }else{
                                queryStringBuffer.append(" AND (EmpId like '"+getCustnameById()+"')");    
                                }
                            }else{
                                if("All".equals(getEmpnameById()))
                                {
                                    queryStringBuffer.append(" AND (EmpId like '%')");
                                }else{
                                queryStringBuffer.append(" AND (EmpId like '"+getEmpnameById()+"')");
                                }
                            }
                        
                            
                        }
                        
                    }
                    
                    
                    //  Description based search
                    if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (Description like '"+getDescription()+"')");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND Description='Submitted'");
                    }
                    queryStringBuffer.append(" Order by DateStart desc Limit 250");
                   
                    
                    
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,queryStringBuffer.toString());
                }
            

               //     System.out.print(queryStringBuffer.toString());

            //For Two Level
            //String timeSheetQuery = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo IN ("+getKeys(getMyTeamMembers(),",")+")) AND  Description='Submitted' ";
            
            
            resultType = SUCCESS;
        } catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        } 
        return resultType;
    }
    */
    
    
     public String newTeamTimeSheet() {
        resultType = LOGIN;
        String list = "";
        String queryString = "";
        String loginId = "";
     
        if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            
          try{
           DataSourceDataProvider dataSourceDataProvider = DataSourceDataProvider.getInstance();
              String resourceId="";
            
           
             if(empType.equalsIgnoreCase("e")){
        
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                  setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
             
                   if(getTeamType()==0)
                setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(resourceId)));
             else if(getTeamType()==1)
                 setCustnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
             else 
                  setCustnamesList(DataSourceDataProvider.getInstance().getCustomerAllTeamMap(Integer.parseInt(resourceId)));
          
             
                 
                 
                 
                // setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(resourceId)));
                
                
                
                 loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
               
                   list=ServiceLocator.getNewTimeSheetService().getTeamMembersList(loginId);
                  setProjectMap(DataSourceDataProvider.getInstance().getProjectsMapByEmpId(Integer.parseInt(resourceId)));
            }else{
                  resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();               
                  loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                   list = loginId;
               String roleId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_ROLE).toString();    
                 if(getTeamType()==0)
                setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(resourceId)));
                  else if(getTeamType()==1)
                       setCustnamesList(dataSourceDataProvider.getCustomerSecondaryTeamMap(Integer.parseInt(resourceId)));
                 else
                       setCustnamesList(dataSourceDataProvider.getCustomerAllTeamMap(Integer.parseInt(resourceId)));
               //  System.out.println("custnameslist-->"+getCustnamesList());
               setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
             }
               
               
               setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
              
            
           
            if(httpServletRequest.getSession(false).getAttribute("sDate") != null) {
                httpServletRequest.getSession(false).setAttribute("sDate",null);
            }
            if(httpServletRequest.getSession(false).getAttribute("eDate") != null) {
                httpServletRequest.getSession(false).setAttribute("eDate",null);
            }
            if(httpServletRequest.getSession(false).getAttribute("isFirstTeam") != null) {
                httpServletRequest.getSession(false).setAttribute("isFirstTeam","yes");
            }
          
           queryStringBuffer = new StringBuffer();
           
           
            if(getSubmitFrom()==null){
                
                if(empType.equalsIgnoreCase("E")){
               /*     if(getEmpCusType() == null){
                        queryString = "SELECT * FROM vwNewTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) ";
                       
                    }
                  else  if(getEmpCusType().equalsIgnoreCase("Internal")){
                    
                    queryString = "SELECT * FROM vwNewTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) ";
                   
                    }*/
                     if(getEmpCusType() == null || "Internal".equalsIgnoreCase(getEmpCusType())){
                        queryString = "SELECT * FROM vwNewTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) ";
                       
                    }
                
                    else
                    {
                        String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                         
                 
                        if(getTeamType()==0) {  
                            List teamList = DataSourceDataProvider.getInstance().getListFromMap(getCustnamesList());
                     
                          if(teamList.size()>0) {
                              
                          queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ("+teamList.toString().substring(1, teamList.toString().length()-1) +" ) ";
                          
                        }else {
                             queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where STATUS='Active' AND ResourceType = 1 AND ReportsTo in ("+empId+")) ";
                        }
                        }else if(getTeamType()==1){
                        List secTeamList = DataSourceDataProvider.getInstance().getListFromMap(DataSourceDataProvider.getInstance().getCustomerSecondaryTeamMap(Integer.parseInt(empId)));     
                             if(secTeamList.size()>0) {
                                  queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +") AND Description = 'Approved' ";
                             }
                             else {
                                queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where STATUS='Active' AND ResourceType = 1 AND SecondReportTo in ("+empId+")) AND Description = 'Approved' ";  
                             }
                        }
                        else {
                        List secTeamList = DataSourceDataProvider.getInstance().getListFromMap(DataSourceDataProvider.getInstance().getCustomerSecondaryTeamMap(Integer.parseInt(empId)));     
                        List teamList = DataSourceDataProvider.getInstance().getListFromMap(getCustnamesList());
                       
                        
                        if(teamList.size()>0) {
                            
                         //   queryString = "SELECT * FROM vwNewCustTimeSheetList where 1=1 ";
                            
                          queryString = "SELECT * FROM vwNewCustTimeSheetList where (EmpId IN ("+teamList.toString().substring(1, teamList.toString().length()-1) +" ) ";
                          if(secTeamList.size()>0) {
                                  queryString = queryString+" OR (EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +") AND Description = 'Approved' ))";
                             }
                             else {
                                queryString = queryString+" OR (EmpId IN ( SELECT ObjectId FROM tblProjectContacts where STATUS='Active' AND ResourceType = 1 AND SecondReportTo in ("+empId+")) AND Description = 'Approved' ))";  
                             }
                        }
                        else  if(secTeamList.size()>0) {
                                  queryString = "SELECT * FROM vwNewCustTimeSheetList where ( (EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +") AND Description = 'Approved' ))";
                             }
                        else {
                             queryString = "SELECT * FROM vwNewCustTimeSheetList where (EmpId IN ( SELECT ObjectId FROM tblProjectContacts where STATUS='Active' AND ResourceType = 1 AND ReportsTo in ("+empId+")) )";
                        }
                        }
                    }
                    
                    
                }else{
                 int customerId =Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                
                    if(getTeamType()==0) {
                  
                        List teamList = DataSourceDataProvider.getInstance().getListFromMap(getMyTeamMembers());
                       if(teamList.size()>0) {
                      queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( "+teamList.toString().substring(1, teamList.toString().length()-1) +"   )  ";
                       }else{
                          queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE STATUS='Active' AND ResourceType = 1 AND ReportsTo IN("+list+")  )  ";
                       }
                    }  else if(getTeamType()==1){
                        List secTeamList = DataSourceDataProvider.getInstance().getListFromMap(DataSourceDataProvider.getInstance().getCustomerSecondaryTeamMap(customerId));     
                        if(secTeamList.size()>0) {
                            queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +"  ) AND Description = 'Approved'"; 
                        }else {
                             queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE STATUS='Active' AND ResourceType = 1 AND SecondReportTo IN("+list+")  ) AND Description = 'Approved'";
                        }
                    }else {
                          List teamList = DataSourceDataProvider.getInstance().getListFromMap(getMyTeamMembers());
                         List secTeamList = DataSourceDataProvider.getInstance().getListFromMap(DataSourceDataProvider.getInstance().getCustomerSecondaryTeamMap(customerId));         
                      if(teamList.size()>0) {
                           queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE (EmpId IN ( "+teamList.toString().substring(1, teamList.toString().length()-1) +"   )  ";
                           
                            if(secTeamList.size()>0) {
                            queryString = queryString+" OR (EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +"  ) AND Description = 'Approved'))"; 
                        }else {
                             queryString = queryString+" OR( EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE STATUS='Active' AND ResourceType = 1 AND SecondReportTo IN("+list+")  ) AND Description = 'Approved'))";
                        } 
                        }else if(secTeamList.size()>0) {
                            queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE (EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +"  ) AND Description = 'Approved')"; 
                        }else {
                             queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE (EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE STATUS='Active' AND ResourceType = 1 AND SecondReportTo IN("+list+")  ) AND Description = 'Approved')";
                        }
                         
                    
                        
                    }
                    }
                
                
                    queryStringBuffer.append(queryString);
                    
                    if((getEmpnameById()!=null && !"".equals(getEmpnameById()))  ||    (getCustnameById()!=null && !"".equals(getCustnameById())) )   {
                        
                     
                        if("All".equals(getEmpnameById()) && "All".equals(getCustnameById())){
                       /*     if(empType.equalsIgnoreCase("E")){
                                if(getEmpCusType().equalsIgnoreCase("Internal")){
                                queryStringBuffer.append(" AND (LoginId like '%')");
                                }else{
                                queryStringBuffer.append(" AND (EmpId like '%')");
                                }
                            }else{
                                queryStringBuffer.append(" AND (EmpId like '%')");
                            }
                       */
                            
                        }
                        else{
                            if(empType.equalsIgnoreCase("E")){
                                if(getEmpCusType().equalsIgnoreCase("Internal")){
                                    queryStringBuffer.delete(0, queryStringBuffer.length());
                                     queryStringBuffer.append(" SELECT * FROM vwNewTimeSheetList WHERE (LoginId like '"+getEmpnameById()+"') ");
                                //queryStringBuffer.append(" AND (LoginId like '"+getEmpnameById()+"')");
                                }else{
                                    //if(getTeamType()==2) {
                                        queryStringBuffer.delete(0, queryStringBuffer.length());
                                       queryStringBuffer.append(" SELECT * FROM vwNewCustTimeSheetList WHERE (EmpId like '"+getCustnameById()+"') ");
                                   // }else {
                                //queryStringBuffer.append(" AND (EmpId like '"+getCustnameById()+"')");   
                                  //  }
                                }
                            }else{
                                if("All".equals(getCustnameById()))
                                {
                                  //  queryStringBuffer.append(" AND (EmpId like '%') ");
                                }else{
                                   // if(getTeamType()==2) {
                                         queryStringBuffer.delete(0, queryStringBuffer.length());
                                       queryStringBuffer.append(" SELECT * FROM vwNewCustTimeSheetList WHERE (EmpId like '"+getCustnameById()+"') ");
                                   // }else {
                                   // queryStringBuffer.append(" AND (EmpId like '"+getCustnameById()+"') ");
                                   // }
                                }
                            }
                        
                            
                        }
                        
                    }
                    
                    if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        queryStringBuffer.append(" AND date(DateStart) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        queryStringBuffer.append(" AND date(DateEnd) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
                 
                   if(empType.equalsIgnoreCase("c") || "External".equalsIgnoreCase(getEmpCusType())){
                      if(getTeamType()==1) {
                    if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (SecDescription like '"+getDescription()+"')");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND SecDescription='Submitted'");
                    }
                     }else if (getTeamType()==0){
                           if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (Description like '"+getDescription()+"')");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND Description='Submitted'");
                    }
                      }else {
                           if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND ((Description like '"+getDescription()+"') OR  (SecDescription like '"+getDescription()+"'))");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND Description='Submitted'");
                    }
                      }
                   }else {
                           if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (Description like '"+getDescription()+"')");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND Description='Submitted'");
                    }
                       
                   }
                      
                      
                      
                    queryStringBuffer.append(" Order by DateStart desc Limit 250");
                   
                    
                    
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,queryStringBuffer.toString());
                }
            

               //     System.out.print(queryStringBuffer.toString());

            //For Two Level
            //String timeSheetQuery = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo IN ("+getKeys(getMyTeamMembers(),",")+")) AND  Description='Submitted' ";
            
            
            resultType = SUCCESS;
        } catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        } 
        return resultType;
    }
    
	
/*	
    
    public String newApproveTimeSheet() {
        
        System.out.println("Resource tYpe-->"+getResourceType());
        resultType = LOGIN;
        int updatedRows = 0;
        String timeSheetQuery = "";
        String list;
        
        String loginId = "";
        String eType = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DataSourceDataProvider dataSourceDataProvider;
        //if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            String resourceId="";
            eType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                   if(eType.equalsIgnoreCase("c") || eType.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                
                }
            if(AuthorizationManager.getInstance().isAuthorizedUser("APPROVE_OR_REJECT_TIMESHEET",userRoleId)){
                try {
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    
             //   String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
         
            //--    setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                    connection = ConnectionProvider.getInstance().getConnection();
                    preparedStatement = connection.prepareStatement("UPDATE tblTimeSheets set TimeSheetStatusTypeId=3,Comments=null,ApprovedDate='"+DateUtility.getInstance().getMysqlDate(getApproveDate().trim())+"' where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'");
                    updatedRows = preparedStatement.executeUpdate();
                    if(updatedRows == 1) {
                        
                        if(eType.equalsIgnoreCase("E")){
                            //change
                        // setResourceType("e");
                         loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                         list=ServiceLocator.getTimeSheetService().getTeamMembersList(loginId);
                         String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                        //setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                         setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                         timeSheetQuery = "SELECT * FROM vwNewTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) AND Description='Submitted'";
                         //  setEmpName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Integer.parseInt(getEmpID())));
                           String  empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                           setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(empId)));
                           setProjectMap(DataSourceDataProvider.getInstance().getProjectsMapByEmpId(Integer.parseInt(resourceId)));
                        }else{
                         //   setResourceType("c");
                            loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                            list=loginId;
                            setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                            timeSheetQuery = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where ReportsTo in ("+list+") AND STATUS = 'Active') AND Description='Submitted'";
                         // setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                           // setEmpName(DataSourceDataProvider.getInstance().getCustomerNameById(Integer.parseInt(getEmpID())));
                             setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                        }
                        
                      
                        //For Two Level
                        //String timeSheetQuery = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo IN ("+getKeys(getMyTeamMembers(),",")+")) AND  Description='Submitted' ";
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,timeSheetQuery);
                        //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
                        //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
                       
                        setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                       
                        
                        
                        
                        //String toEmail = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(Integer.parseInt(getEmpID()), getResourceType());
                                String toEmail = DataSourceDataProvider.getInstance().getEmailIdByObjectId(Integer.parseInt(getEmpID()), getResourceType());
                        System.out.println("ToEmail-->"+toEmail);
                        
                        
                        
                       if(Properties.getProperty("Mail.Flag").equals("1")&&!"".equals(toEmail)) { 
                           String subject = "Timesheet Reminder";
                           String bodyContent = ServiceLocator.getNewTimeSheetService().getApproveTimeSheetEmailLogBody(getEmpName(), getWstDate(), getWenDate());
                          //String toAddress, String cCAddress, String subject,String bodyContent,String bCCAddress
                          // ServiceLocator.getMailServices().doAddEmailLog(toEmail, "nseerapu@miraclesoft.com", subject, bodyContent, "nseerapu@miraclesoft.com");
                            
                            String timesheetCC = Properties.getProperty("Timesheet.CC");
                          String timesheetBCC = Properties.getProperty("Timesheet.BCC");
                           ServiceLocator.getMailServices().doAddEmailLog(toEmail, timesheetCC, subject, bodyContent,"", timesheetBCC);
                        //MailManager mail = new MailManager();
                        //mail.sendReminders(getEmpName(),getTimeSheetID(),getEmpID(),eType,httpServletRequest,getResourceType(),getProjectId());
                        }
                       
                        resultMessage = "<font color=\"green\" size=\"1.5\">TimeSheet has been Approved successfully!</font>";
                        
                        resultType = SUCCESS;
                    }else {
                        resultType = INPUT;
                        resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                }catch(Exception ex) {
                    ex.printStackTrace();
                }finally {
                    try {
                        if(preparedStatement!=null) {
                            preparedStatement.close();
                            preparedStatement = null;
                        }
                        if(connection!=null) {
                            connection.close();
                            connection = null;
                        }
                        
                    }catch(SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } 
        }
        return resultType;
    }
    */
     
    public String newApproveTimeSheet() {
        
       // System.out.println("Resource tYpe-->"+getResourceType());
       // System.out.println("Team tYpe-->"+getTeamType());
        resultType = LOGIN;
        int updatedRows = 0;
        String timeSheetQuery = "";
        String list;
        
        String loginId = "";
        String eType = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DataSourceDataProvider dataSourceDataProvider;
        //if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            String resourceId="";
            eType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                   if(eType.equalsIgnoreCase("c") || eType.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                
                }
            if(AuthorizationManager.getInstance().isAuthorizedUser("APPROVE_OR_REJECT_TIMESHEET",userRoleId)){
                try {
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();
                    
             //   String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
         
            //--    setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                    connection = ConnectionProvider.getInstance().getConnection();
                   // System.out.println("getTeamType-->"+getTeamType());
                  //  System.out.println("getPriProjId-->"+getPriProjId());
                    String queryString = null;
                    
                  //  if((getTeamType()==1 && getPriProjId()!=0) ) {
                      if((getTeamType()==2 && getPriProjId()!=0) ) {
                         queryString = "UPDATE tblTimeSheets set SecondReportsToStatusTypeId=3,TimeSheetStatusTypeId=3,Comments=null,ApprovedDate='"+DateUtility.getInstance().getMysqlDate(getApproveDate().trim())+"', Approver2Id="+resourceId+" where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'";
                       
                       
                        //preparedStatement = connection.prepareStatement("UPDATE tblTimeSheets set SecondReportsToStatusTypeId=3,Comments=null,ApprovedDate='"+DateUtility.getInstance().getMysqlDate(getApproveDate().trim())+"' where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'");
                    }else {
                        if(getIsDualReportingRequired()){
                             String  reportsToDetails = DataSourceDataProvider.getInstance().getSecReportsToDetails(getEmpID());
                               if(!"".equals(reportsToDetails)&&reportsToDetails!=null ) {
                         queryString = "UPDATE tblTimeSheets set Approver1Id="+resourceId+", TimeSheetStatusTypeId=3,SecondReportsToStatusTypeId=2,Comments=null,ApprovedDate='"+DateUtility.getInstance().getMysqlDate(getApproveDate().trim())+"' where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'";
                               } else {
                               queryString = "UPDATE tblTimeSheets set Approver1Id="+resourceId+", TimeSheetStatusTypeId=3,Comments=null,ApprovedDate='"+DateUtility.getInstance().getMysqlDate(getApproveDate().trim())+"' where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'";     
                               }
                        }  else
                          queryString = "UPDATE tblTimeSheets set Approver1Id="+resourceId+", TimeSheetStatusTypeId=3,Comments=null,ApprovedDate='"+DateUtility.getInstance().getMysqlDate(getApproveDate().trim())+"' where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'";  
                   // preparedStatement = connection.prepareStatement("UPDATE tblTimeSheets set TimeSheetStatusTypeId=3,SecondReportsToStatusTypeId=2,Comments=null,ApprovedDate='"+DateUtility.getInstance().getMysqlDate(getApproveDate().trim())+"' where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'");
                    }
                 //   System.out.println("Update Query-->"+queryString);
                    preparedStatement = connection.prepareStatement(queryString);
                    updatedRows = preparedStatement.executeUpdate();
                    if(updatedRows == 1) {
                        
                        if(eType.equalsIgnoreCase("E")){
                     /*       //change
                        // setResourceType("e");
                         loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                         list=ServiceLocator.getTimeSheetService().getTeamMembersList(loginId);
                         String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                        //setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                         setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                         timeSheetQuery = "SELECT * FROM vwNewTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) AND Description='Submitted'";
                         //  setEmpName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Integer.parseInt(getEmpID())));
                           String  empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                           setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(empId)));
                           setProjectMap(DataSourceDataProvider.getInstance().getProjectsMapByEmpId(Integer.parseInt(resourceId)));
                           
                           */
                            newEmployeeteamTimeSheets();
                         
                           
                           
                        }else{
                         //   setResourceType("c");
                         /*   loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                            list=loginId;
                         if(getTeamType()==0)
                            setCustnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                            else
                                setCustnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
                            
                            
                            
                        
                           
                        
                             setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                             
                              if(getTeamType()==0) {
                  //  queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE ReportsTo IN("+list+")  )  ";
                       timeSheetQuery = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE STATUS='Active' AND ResourceType = 1 AND ReportsTo IN("+list+")  )   ";   
                    }  else {
                          timeSheetQuery = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE STATUS='Active' AND ResourceType = 1 AND SecondReportTo IN("+list+")  ) AND Description = 'Approved'";
                    
                    }*/
                            newCustomerTeamTimeSheets();
                            
                        }
                        
                      
                        //For Two Level
                        //String timeSheetQuery = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo IN ("+getKeys(getMyTeamMembers(),",")+")) AND  Description='Submitted' ";
                    //    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,timeSheetQuery);
                        //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
                        //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
                       
                        setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                         setSecCustnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
                        /*if(eType.equalsIgnoreCase("e")){
                            setResourceType("e");
                        }*/
                         //change
                      /*  if(eType.equalsIgnoreCase("c"))
                         {
                         setResourceType("c");
                         }
                         else if(eType.equalsIgnoreCase("v"))
                         {
                         setResourceType("v");
                         }*/
                        
                        
                        
                        //String toEmail = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(Integer.parseInt(getEmpID()), getResourceType());
                   
                         
                         //if((getTeamType()==1 && getPriProjId()!=0 )|| !getIsDualReportingRequired()) {
                         if((getTeamType()==2 && getPriProjId()!=0 )|| !getIsDualReportingRequired()) {
                        //--
                              String toEmail = DataSourceDataProvider.getInstance().getEmailIdByObjectId(Integer.parseInt(getEmpID()), getResourceType());
                        if(Properties.getProperty("Mail.Flag").equals("1")&&!"".equals(toEmail)) { 
                           String subject = "Timesheet Reminder";
                           String bodyContent = ServiceLocator.getNewTimeSheetService().getApproveTimeSheetEmailLogBody(getEmpName(), getWstDate(), getWenDate());
                          //String toAddress, String cCAddress, String subject,String bodyContent,String bCCAddress
                          // ServiceLocator.getMailServices().doAddEmailLog(toEmail, "nseerapu@miraclesoft.com", subject, bodyContent, "nseerapu@miraclesoft.com");
                            
                            String timesheetCC = Properties.getProperty("Timesheet.CC");
                          String timesheetBCC = Properties.getProperty("Timesheet.BCC");
                         //  ServiceLocator.getMailServices().doAddEmailLog(toEmail, timesheetCC, subject, bodyContent,"", timesheetBCC);
                            ServiceLocator.getMailServices().doAddEmailLogNew(toEmail, timesheetCC, subject, bodyContent,"", timesheetBCC,"Timesheets");
                        //MailManager mail = new MailManager();
                        //mail.sendReminders(getEmpName(),getTimeSheetID(),getEmpID(),eType,httpServletRequest,getResourceType(),getProjectId());
                        }
                             
                             
                             //--
                       
                        
                        //-----
                        
                      }else {
                            String reportsToType = null;
                         String reportsToEmail = null;
                            String  reportsToDetails = DataSourceDataProvider.getInstance().getSecReportsToDetails(getEmpID());
                  // System.out.println("reportsToDetails-->"+reportsToDetails+"<--");
                   if(!"".equals(reportsToDetails)&&reportsToDetails!=null ) {
                   reportsToEmail = reportsToDetails.split("\\|")[0];
                   reportsToType = reportsToDetails.split("\\|")[1];
                 
                          
                          
                          
                        if(Properties.getProperty("Mail.Flag").equals("1")&&!"".equals(reportsToEmail)&&!"".equals(reportsToType)&&reportsToDetails!=null) {
                       //  String bodyConetent = ServiceLocator.getNewTimeSheetService().getSubmitTimeSheetEmailLogBody(getEmpName(),getWstDate(),getWenDate(),resourceId,reportsToType,Integer.parseInt(getTimeSheetID()));        
                         //   String bodyConetent = ServiceLocator.getNewTimeSheetService().getSubmitTimeSheetEmailLogBody(getEmpName(),getWstDate(),getWenDate(),getEmpID(),reportsToType,Integer.parseInt(getTimeSheetID()),getResourceType(),"Approved","Submitted",1);        //1=getTEamType(Secondary)
                             //  String bodyConetent = ServiceLocator.getNewTimeSheetService().getSubmitTimeSheetEmailLogBody(getEmpName(),getWstDate(),getWenDate(),getEmpID(),reportsToType,Integer.parseInt(getTimeSheetID()),getResourceType(),"Approved","Submitted");        //1=getTEamType(Secondary)
                              String bodyConetent = ServiceLocator.getNewTimeSheetService().getSubmitTimeSheetEmailLogBody(getEmpName(),getWstDate(),getWenDate(),getEmpID(),reportsToType,Integer.parseInt(getTimeSheetID()),getResourceType());        //1=getTEamType(Secondary)
                          String subject = "TimeSheet Reminder";
                        //  ServiceLocator.getMailServices().doAddEmailLog(reportsToEmail, "nseerapu@miraclesoft.com", subject, bodyConetent, "nseerapu@miraclesoft.com");
                          String timesheetCC = Properties.getProperty("Timesheet.CC");
                          String timesheetBCC = Properties.getProperty("Timesheet.BCC");
                          //  ServiceLocator.getMailServices().doAddEmailLog(reportsToEmail, timesheetCC, subject, bodyConetent,"", timesheetBCC);
                            ServiceLocator.getMailServices().doAddEmailLogNew(reportsToEmail, timesheetCC, subject, bodyConetent,"", timesheetBCC,"Timesheets");
                        }
                        }else {// without sec reports to
                       
 String toEmail = DataSourceDataProvider.getInstance().getEmailIdByObjectId(Integer.parseInt(getEmpID()), getResourceType());
                        if(Properties.getProperty("Mail.Flag").equals("1")&&!"".equals(toEmail)) { 
                           String subject = "Timesheet Reminder";
                           String bodyContent = ServiceLocator.getNewTimeSheetService().getApproveTimeSheetEmailLogBody(getEmpName(), getWstDate(), getWenDate());
                          //String toAddress, String cCAddress, String subject,String bodyContent,String bCCAddress
                          // ServiceLocator.getMailServices().doAddEmailLog(toEmail, "nseerapu@miraclesoft.com", subject, bodyContent, "nseerapu@miraclesoft.com");
                            
                            String timesheetCC = Properties.getProperty("Timesheet.CC");
                          String timesheetBCC = Properties.getProperty("Timesheet.BCC");
                         //  ServiceLocator.getMailServices().doAddEmailLog(toEmail, timesheetCC, subject, bodyContent,"", timesheetBCC);
                            ServiceLocator.getMailServices().doAddEmailLogNew(toEmail, timesheetCC, subject, bodyContent,"", timesheetBCC,"Timesheets");
                        //MailManager mail = new MailManager();
                        //mail.sendReminders(getEmpName(),getTimeSheetID(),getEmpID(),eType,httpServletRequest,getResourceType(),getProjectId());
                        }
                   }
                       
                      }
                        resultMessage = "<font color=\"green\" size=\"1.5\">TimeSheet has been Approved successfully!</font>";
                          if(eType.equalsIgnoreCase("E")){
                        resultType = SUCCESS;
                          } else {
                                resultType = "csuccess";
                          }
                    }else {
                        resultType = INPUT;
                        resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                    }
                    setTeamType(0);
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                }catch(Exception ex) {
                    ex.printStackTrace();
                }finally {
                    try {
                        if(preparedStatement!=null) {
                            preparedStatement.close();
                            preparedStatement = null;
                        }
                        if(connection!=null) {
                            connection.close();
                            connection = null;
                        }
                        
                    }catch(SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } 
        }
        return resultType;
    }
	
    /*
    public String newRejectTimeSheet() {
    resultType = LOGIN;
    int updatedRows = 0;
     String list;
        
        String loginId = "";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
     String timeSheetQuery = "";
     DataSourceDataProvider dataSourceDataProvider;
    //if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
     if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
        userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
       //String eType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
        String resourceId="";   
        String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if(empType1.equalsIgnoreCase("c") || empType1.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                
                }
        resultType = "accessFailed";
        if(AuthorizationManager.getInstance().isAuthorizedUser("APPROVE_OR_REJECT_TIMESHEET",userRoleId)){
            try {
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                connection = ConnectionProvider.getInstance().getConnection();
                preparedStatement = connection.prepareStatement("UPDATE tblTimeSheets set TimeSheetStatusTypeId=10,Comments='"+getComment()+"' where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'");
                updatedRows = preparedStatement.executeUpdate();
                //setEmpName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Integer.parseInt(getEmpID())));
                //setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                if(empType1.equalsIgnoreCase("E")){
                   // setResourceType("e");
                    loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                         list=ServiceLocator.getTimeSheetService().getTeamMembersList(loginId);
                             //setEmpName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Integer.parseInt(getEmpID())));
                             String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                             //setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                             setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                             String  empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                             setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(empId)));
                             timeSheetQuery = "SELECT * FROM vwNewTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) AND Description='Submitted'";
                             setProjectMap(DataSourceDataProvider.getInstance().getProjectsMapByEmpId(Integer.parseInt(resourceId)));
                }else{
                    // setResourceType("c");
                           // setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                    loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                            list=loginId;
                            timeSheetQuery = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where ReportsTo in ("+list+") AND STATUS = 'Active') AND Description='Submitted'";
                    //setEmpName(DataSourceDataProvider.getInstance().getCustomerNameById(Integer.parseInt(getEmpID())));
                            setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
               setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                }
                //change
               
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,timeSheetQuery);
                
                setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
               
                 // String toEmail = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(Integer.parseInt(getEmpID()), getResourceType());
                       String toEmail = DataSourceDataProvider.getInstance().getEmailIdByObjectId(Integer.parseInt(getEmpID()), getResourceType()); 
                  System.out.println("ToEmail-->"+toEmail);
                        
                
               
                if(Properties.getProperty("Mail.Flag").equals("1")) {
                     String subject = "Timesheet Reminder";
                           String bodyContent = ServiceLocator.getNewTimeSheetService().getRejectTimeSheetEmailLogBody(getEmpName(), getWstDate(), getWenDate());
                          //String toAddress, String cCAddress, String subject,String bodyContent,String bCCAddress
                            String timesheetCC = Properties.getProperty("Timesheet.CC");
                          String timesheetBCC = Properties.getProperty("Timesheet.BCC");
                             ServiceLocator.getMailServices().doAddEmailLog(toEmail, timesheetCC, subject, bodyContent,"", timesheetBCC);
                         //  ServiceLocator.getMailServices().doAddEmailLog(toEmail, "nseerapu@miraclesoft.com", subject, bodyContent, "nseerapu@miraclesoft.com");
              //  MailManager mail = new MailManager();
               // mail.sendReminders(getEmpName(),getTimeSheetID(),getEmpID(),empType1,httpServletRequest,getResourceType(),getProjectId());
                }
                
                if(updatedRows == 1) {
                    resultType = SUCCESS;
                    resultMessage = "<font color=\"green\" size=\"1.5\">TimeSheet has been Rejected successfully!</font>";
                }else {
                    resultType = INPUT;
                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                }
               // setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
                 //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
                
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            }catch(Exception ex) {
                ex.printStackTrace();
            }finally {
                try {
                    if(preparedStatement!=null) {
                        preparedStatement.close();
                        preparedStatement = null;
                    }
                    if(connection!=null) {
                        connection.close();
                        connection = null;
                    }
                    
                }catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    return resultType;
}*/
   
   public String newRejectTimeSheet() {
    resultType = LOGIN;
    int updatedRows = 0;
     String list;
        
        String loginId = "";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
     String timeSheetQuery = "";
     DataSourceDataProvider dataSourceDataProvider;
    //if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
     if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
        userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
       //String eType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
        String resourceId="";   
        String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if(empType1.equalsIgnoreCase("c") || empType1.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                
                }
        resultType = "accessFailed";
        if(AuthorizationManager.getInstance().isAuthorizedUser("APPROVE_OR_REJECT_TIMESHEET",userRoleId)){
            try {
                dataSourceDataProvider = DataSourceDataProvider.getInstance();
                connection = ConnectionProvider.getInstance().getConnection();
                if((getTeamType()==2&& getPriProjId()!=0)) {
                    preparedStatement = connection.prepareStatement("UPDATE tblTimeSheets set SecondReportsToStatusTypeId=10,Comments='"+getComment()+"', Approver2Id="+resourceId+" where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'");
                  
                 
                }else {
                      preparedStatement = connection.prepareStatement("UPDATE tblTimeSheets set Approver1Id="+resourceId+", TimeSheetStatusTypeId=10,Comments='"+getComment()+"' where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'");   
                }
                
                updatedRows = preparedStatement.executeUpdate();
                //setEmpName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Integer.parseInt(getEmpID())));
                //setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                if(empType1.equalsIgnoreCase("E")){
                   // setResourceType("e");
                   /* loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                         list=ServiceLocator.getTimeSheetService().getTeamMembersList(loginId);
                             //setEmpName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Integer.parseInt(getEmpID())));
                             String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                             //setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                             setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                             String  empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                             setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(empId)));
                             timeSheetQuery = "SELECT * FROM vwNewTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) AND Description='Submitted'";
                             setProjectMap(DataSourceDataProvider.getInstance().getProjectsMapByEmpId(Integer.parseInt(resourceId)));
                      */
                    newEmployeeteamTimeSheets();
                             
                }else{
              /*      // setResourceType("c");
                           // setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                    loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                            list=loginId;
                           // timeSheetQuery = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where ReportsTo in ("+list+")) AND Description='Submitted'";
                             if(getTeamType()==0) {
                  //  queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE ReportsTo IN("+list+")  )  ";
                       timeSheetQuery = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE STATUS='Active' AND ResourceType = 1 AND ReportsTo IN("+list+")  )   ";   
                    }  else {
                          timeSheetQuery = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE STATUS='Active' AND ResourceType = 1 AND SecondReportTo IN("+list+")  ) AND Description = 'Approved'";
                    
                    }
                    //setEmpName(DataSourceDataProvider.getInstance().getCustomerNameById(Integer.parseInt(getEmpID())));
                            if(getTeamType()==0)
                            setCustnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                            else
                                setCustnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
               setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
               
               */
                 newCustomerTeamTimeSheets();
                            
               
                }
                //change
               /* if(empType1.equalsIgnoreCase("e")){
                            setResourceType("e");
                        }*/
                /*  if(empType1.equalsIgnoreCase("c")){
                            setResourceType("c");
                        }
                  else if(empType1.equalsIgnoreCase("v")){
                            setResourceType("v");
                        }*/
               // httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,timeSheetQuery);
                
                setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                setSecCustnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
                 // String toEmail = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(Integer.parseInt(getEmpID()), getResourceType());
                       String toEmail = DataSourceDataProvider.getInstance().getEmailIdByObjectId(Integer.parseInt(getEmpID()), getResourceType()); 
               //   System.out.println("ToEmail-->"+toEmail);
                        
                
               
                if(Properties.getProperty("Mail.Flag").equals("1")) {
                     String subject = "Timesheet Reminder";
                           String bodyContent = ServiceLocator.getNewTimeSheetService().getRejectTimeSheetEmailLogBody(getEmpName(), getWstDate(), getWenDate());
                          //String toAddress, String cCAddress, String subject,String bodyContent,String bCCAddress
                            String timesheetCC = Properties.getProperty("Timesheet.CC");
                          String timesheetBCC = Properties.getProperty("Timesheet.BCC");
                             //ServiceLocator.getMailServices().doAddEmailLog(toEmail, timesheetCC, subject, bodyContent,"", timesheetBCC);
                          ServiceLocator.getMailServices().doAddEmailLogNew(toEmail, timesheetCC, subject, bodyContent,"", timesheetBCC,"Timesheets");
                         //  ServiceLocator.getMailServices().doAddEmailLog(toEmail, "nseerapu@miraclesoft.com", subject, bodyContent, "nseerapu@miraclesoft.com");
              //  MailManager mail = new MailManager();
               // mail.sendReminders(getEmpName(),getTimeSheetID(),getEmpID(),empType1,httpServletRequest,getResourceType(),getProjectId());
                }
                
                if(updatedRows == 1) {
                    if(empType1.equalsIgnoreCase("E")){
                    resultType = SUCCESS;
                    }else{
                         resultType = "csuccess";
                    }
                    System.out.println("Teamtype-->"+getTeamType());
                    System.out.println("resultType-->"+resultType);
                    resultMessage = "<font color=\"green\" size=\"1.5\">TimeSheet has been Rejected successfully!</font>";
                }else {
                    resultType = INPUT;
                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                }
                setTeamType(0);
               // setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
                 //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
                
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            }catch(Exception ex) {
                ex.printStackTrace();
            }finally {
                try {
                    if(preparedStatement!=null) {
                        preparedStatement.close();
                        preparedStatement = null;
                    }
                    if(connection!=null) {
                        connection.close();
                        connection = null;
                    }
                    
                }catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    return resultType;
}
    


  /*  
   public String newEmpTimeSheetSearch() throws Exception {
        resultType = LOGIN;
         String loginId = "";
         DataSourceDataProvider dataSourceDataProvider;
      //  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            try{
                if(httpServletRequest.getSession(false).getAttribute("sDate") != null) {
                    httpServletRequest.getSession(false).setAttribute("sDate",null);
                }
                if(httpServletRequest.getSession(false).getAttribute("eDate") != null) {
                    httpServletRequest.getSession(false).setAttribute("eDate",null);
                }
              
                 if(httpServletRequest.getSession(false).getAttribute("isFirstTeam") != null) {
                httpServletRequest.getSession(false).setAttribute("isFirstTeam","yes");
            }
               // String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            //    String  userId = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
                String roleId = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID);
                String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
               // setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                
                //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
               // setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
               // setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
               //setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                   //dataSourceDataProvider = DataSourceDataProvider.getInstance();
          setProjectMap(DataSourceDataProvider.getInstance().getProjectsMap());
         // setProjectMap(DataSourceDataProvider.getInstance().getProjectsByAccountId(String.valueOf(getCustomerId())));
          
                setEmpnameById(getEmpnameById());
                
               // System.out.println("ProjectId-->"+getProjectId());
                if(getProjectId()!=0 && getProjectId()!=-1) {
                   // System.out.println("in if");
                    setEmpnamesList(dataSourceDataProvider.getEmployeesByProjectId(getProjectId()));
                }
                else {
                   // System.out.println("in else");
                    setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                }
                setPrintEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
              //  setReportsEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                  setReportsEmpnamesList(getPrintEmpnamesList());
                  // String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();   
                  // setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                     //setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
               // setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                   loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                if(getSubmitFrom()==null){
                    queryStringBuffer = new StringBuffer();
                   
                    //queryStringBuffer.append("SELECT * FROM vwTimeSheetList where Description not in ('Approved') AND WorkingCountry like '"+workingCountry+"'");
                   //  queryStringBuffer.append("SELECT * FROM vwTimeSheetList where  WorkingCountry like '"+workingCountry+"'");
                    
                    System.out.println("getProjectId-->"+getProjectId());
                     if( getProjectId()==-1 || getProjectId()==0) {
                       //  queryStringBuffer.append("SELECT * FROM vwTimeSheetList where  WorkingCountry like '"+workingCountry+"'");
                           queryStringBuffer.append("SELECT * FROM vwNewTimeSheetList where  WorkingCountry like '"+workingCountry+"'");
                           
                            if(getEmpnameById()!=null && !"".equals(getEmpnameById()))   {
                             if(!"-1".equals(getEmpnameById())){
                                 queryStringBuffer.append(" AND (EmpId like '"+getEmpnameById()+"')");
                             }else {
                                 List empList = dataSourceDataProvider.getListFromMap(getEmpnamesList());
                         if(empList.size()>0)
                          queryStringBuffer.append(" AND EmpId IN ("+empList.toString().substring(1,empList.toString().length()-1) +")"); 
                         else
                           queryStringBuffer.append(" AND EmpId IN ("+0 +")");   
                             }
                         }
                           
                     }else {
                         queryStringBuffer.append("SELECT * FROM vwNewCustTimeSheetList where  1=1"); 
                         System.out.println("getEmpnameById-->"+getEmpnameById());
                         if(getEmpnameById()!=null && !"".equals(getEmpnameById()))   {
                             if(!"-1".equals(getEmpnameById())){
                                 queryStringBuffer.append(" AND (EmpId like '"+getEmpnameById()+"')");
                             }else {
                                 List empList = dataSourceDataProvider.getListFromMap(getEmpnamesList());
                         if(empList.size()>0)
                          queryStringBuffer.append(" AND EmpId IN ("+empList.toString().substring(1,empList.toString().length()-1) +")"); 
                         else
                           queryStringBuffer.append(" AND EmpId IN ("+0 +")");   
                             }
                         }
                         
                     }
                    
                    if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        queryStringBuffer.append(" AND date(DateStart) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        queryStringBuffer.append(" AND date(DateEnd) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
                  
                    
                   // if(getProjectId()!=0 && getProjectId()!=-1) {
                    // if( getProjectId()!=-1) {
                      //setProjectName(DataSourceDataProvider.getInstance().getProjectName(getProjectId()));
                      // queryStringBuffer.append(" AND ProjectName like '"+getProjectName()+"'");
                    //}
                    
                    
                    
                    
                    
                    
                    
                    
                     if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (Description like '"+getDescription()+"')");
                    }
                  //  else{
                        
                     //   queryStringBuffer.append(" AND Description='Submitted'");
                   // }
                    queryStringBuffer.append(" order by DateStart desc Limit 250");
                    httpServletRequest.getSession().setAttribute("queryTeam",queryStringBuffer.toString());
                  //  System.out.println(queryStringBuffer.toString());
                }
                
               // System.out.print(queryStringBuffer.toString());
              
               
                resultType = SUCCESS;
            } catch (Exception ex){
                ex.printStackTrace();
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
            
            
        }
        
        return resultType;
    }
*/

   public String newEmpTimeSheetSearch() throws Exception {
        resultType = LOGIN;
         String loginId = "";
         DataSourceDataProvider dataSourceDataProvider;
      //  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            try{
                if(httpServletRequest.getSession(false).getAttribute("sDate") != null) {
                    httpServletRequest.getSession(false).setAttribute("sDate",null);
                }
                if(httpServletRequest.getSession(false).getAttribute("eDate") != null) {
                    httpServletRequest.getSession(false).setAttribute("eDate",null);
                }
               /* if(httpServletRequest.getSession(false).getAttribute("isFirstOp") != null) {
                    httpServletRequest.getSession(false).setAttribute("isFirstOp","yes");
                }*/
                 if(httpServletRequest.getSession(false).getAttribute("isFirstTeam") != null) {
                httpServletRequest.getSession(false).setAttribute("isFirstTeam","yes");
            }
                 
               // String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            //    String  userId = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
                String roleId = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID);
               String  workingFor = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                 String  livingCountry =     httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Living_COUNTRY).toString();
                 
                 System.out.println("livingCountry-->"+livingCountry);
                 setOperationContactMapByCountry(DataSourceDataProvider.getInstance().getOpsContactIdByCountry(livingCountry));
                setLocationsMapByLivingCountry(DataSourceDataProvider.getInstance().getEmployeeLocationsList(livingCountry));
                 
               // setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                
                //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
               // setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
               // setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
               //setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                   //dataSourceDataProvider = DataSourceDataProvider.getInstance();
          setProjectMap(DataSourceDataProvider.getInstance().getProjectsMap());
         // setProjectMap(DataSourceDataProvider.getInstance().getProjectsByAccountId(String.valueOf(getCustomerId())));
          setIsDualReportingRequired(getIsDualReportingRequired());
                setEmpnameById(getEmpnameById());
                
               // System.out.println("ProjectId-->"+getProjectId());
                if(getProjectId()!=0 && getProjectId()!=-1) {
                   // System.out.println("in if");
                    setEmpnamesList(dataSourceDataProvider.getEmployeesByProjectId(getProjectId()));
                }
                else {
                   // System.out.println("in else");
                    setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByCountry(workingFor));
                }
                setPrintEmpnamesList(dataSourceDataProvider.getEmployeeNamesByCountry(workingFor));
              //  setReportsEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                  setReportsEmpnamesList(getPrintEmpnamesList());
                  // String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();   
                  // setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                     //setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
               // setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                   loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                if(getSubmitFrom()==null){
                    queryStringBuffer = new StringBuffer();
                   
                    //queryStringBuffer.append("SELECT * FROM vwTimeSheetList where Description not in ('Approved') AND WorkingCountry like '"+workingCountry+"'");
                   //  queryStringBuffer.append("SELECT * FROM vwTimeSheetList where  WorkingCountry like '"+workingCountry+"'");
                    
                  //  System.out.println("getProjectId-->"+getProjectId());
                     if( getProjectId()==-1 || getProjectId()==0) {
                       //  queryStringBuffer.append("SELECT * FROM vwTimeSheetList where  WorkingCountry like '"+workingCountry+"'");
                           queryStringBuffer.append("SELECT * FROM vwNewTimeSheetList where  Country  like '"+workingFor+"'");
                           
                            if(getEmpnameById()!=null && !"".equals(getEmpnameById()))   {
                             if(!"-1".equals(getEmpnameById())){
                                 queryStringBuffer.append(" AND (EmpId like '"+getEmpnameById()+"')");
                             }
                           /*  else {
                                 List empList = dataSourceDataProvider.getListFromMap(getEmpnamesList());
                         if(empList.size()>0)
                          queryStringBuffer.append(" AND EmpId IN ("+empList.toString().substring(1,empList.toString().length()-1) +")"); 
                         else
                           queryStringBuffer.append(" AND EmpId IN ("+0 +")");   
                             }*/
                         }
                           
                     }else {
                         queryStringBuffer.append("SELECT * FROM vwNewCustTimeSheetList where  1=1"); 
                      //   System.out.println("getEmpnameById-->"+getEmpnameById());
                         if(getEmpnameById()!=null && !"".equals(getEmpnameById()))   {
                             if(!"-1".equals(getEmpnameById())){
                                 queryStringBuffer.append(" AND (EmpId like '"+getEmpnameById()+"')");
                             }else {
                                 List empList = dataSourceDataProvider.getListFromMap(getEmpnamesList());
                         if(empList.size()>0)
                          queryStringBuffer.append(" AND EmpId IN ("+empList.toString().substring(1,empList.toString().length()-1) +")"); 
                         else
                           queryStringBuffer.append(" AND EmpId IN ("+0 +")");   
                             }
                         }/*else {
                             List empList = dataSourceDataProvider.getListFromMap(getEmpnamesList());
                         if(empList.size()>0)
                          queryStringBuffer.append(" AND EmpId IN ("+empList.toString().substring(1,empList.toString().length()-1) +")"); 
                         else
                           queryStringBuffer.append(" AND EmpId IN ("+0 +")");   
                         }*/
                         
                     }
                    
                    if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        queryStringBuffer.append(" AND date(DateStart) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        queryStringBuffer.append(" AND date(DateEnd) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
                   /* String empIds ="";
                    if(getEmpnameById()!=null && !"".equals(getEmpnameById()))   {
                        if(!"-1".equals(getEmpnameById())){
                          
                            queryStringBuffer.append(" AND (EmpId like '"+getEmpnameById()+"')");
                        }
                      
                        
                    }*/
                    /*
                        if(getProjectName()!=null && !"".equals(getProjectName()))   {
                        if(!"All".equals(getProjectName())){
                         
                               queryStringBuffer.append(" AND ProjectName like '"+getProjectName()+"'");
                            
                        }
                        
                        
                    }
                    */
                    
                   // if(getProjectId()!=0 && getProjectId()!=-1) {
                    // if( getProjectId()!=-1) {
                      //setProjectName(DataSourceDataProvider.getInstance().getProjectName(getProjectId()));
                      // queryStringBuffer.append(" AND ProjectName like '"+getProjectName()+"'");
                    //}
                    
                    
                    
                    

if(getOpsContactId()!=0 && getOpsContactId()!=-1){
                        queryStringBuffer.append(" AND OpsContactId="+getOpsContactId());
                    }
                    if(getLocationId() != null && !"-1".equals(getLocationId())){
                        queryStringBuffer.append(" AND Location='"+getLocationId()+"'");
                    }
                    
                    
                    
                    
                     if(getDescription()!=null && !"".equals(getDescription())){
                         
                         if(getIsDualReportingRequired()){
                              if(getTeamType()==1)
                         queryStringBuffer.append(" AND (Description like '"+getDescription()+"')");
                         else if(getTeamType()==2)
                             queryStringBuffer.append(" AND (SecDescription like '"+getDescription()+"')");
                              else
                             queryStringBuffer.append(" AND ((Description like '"+getDescription()+"') OR (SecDescription like '"+getDescription()+"'))" );
                         }else {
                             queryStringBuffer.append(" AND (Description like '"+getDescription()+"')");
                         }
                        
                    }
                  //  else{
                        
                     //   queryStringBuffer.append(" AND Description='Submitted'");
                   // }
                    //queryStringBuffer.append(" order by DateStart desc Limit 250");
                     queryStringBuffer.append(" order by DateStart DESC");
                    httpServletRequest.getSession().setAttribute("queryTeam",queryStringBuffer.toString());
                   // System.out.println(queryStringBuffer.toString());
                }
                
               // System.out.print(queryStringBuffer.toString());
               /* else{
                    queryStringBuffer = new StringBuffer();
                    if(httpServletRequest.getSession().getAttribute("queryTeam")==null){
                       queryStringBuffer.append("SELECT * FROM vwTimeSheetList where WorkingCountry like '"+workingCountry+"'");
                    }else{
                    httpServletRequest.getSession().setAttribute("queryTeam",queryStringBuffer);
                    }
                }*/
               
                resultType = SUCCESS;
            } catch (Exception ex){
                ex.printStackTrace();
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
            
            
        }
        
        return resultType;
    }
 
  /*  public String newEmpTimeSheets() throws Exception {
        resultType = LOGIN;
         String loginId = "";
         DataSourceDataProvider dataSourceDataProvider;
      //  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
        // if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
          if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null){
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            try{
                if(httpServletRequest.getSession(false).getAttribute("sDate") != null) {
                    httpServletRequest.getSession(false).setAttribute("sDate",null);
                }
                if(httpServletRequest.getSession(false).getAttribute("eDate") != null) {
                    httpServletRequest.getSession(false).setAttribute("eDate",null);
                }
              
                 if(httpServletRequest.getSession(false).getAttribute("isFirstTeam") != null) {
                httpServletRequest.getSession(false).setAttribute("isFirstTeam","yes");
            }
                String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            //    String  userId = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
                String roleId = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID);
                String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
               // setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                
                //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
               // setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
               // setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
               setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                 //  dataSourceDataProvider = dataSourceDataProvider.getInstance();
            //change 
                   
                   
                setEmpnameById(getEmpnameById());
                //change
               
               setEmpnamesList(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
               // setMyTeamMembers(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
               loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                 //}
                if(getSubmitFrom()==null){
                    queryStringBuffer = new StringBuffer();
                    //change
                  
                        // queryStringBuffer.append("SELECT * FROM vwCustTimeSheetList where Description not in ('Approved') AND  ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") ");
                     queryStringBuffer.append("SELECT * FROM vwCustTimeSheetList where Description not in ('Approved')  ");
                    //}
                    if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        queryStringBuffer.append(" AND date(DateStart) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        queryStringBuffer.append(" AND date(DateEnd) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
                    
                    
                    
                    
                    String empIds ="";
                    if(getEmpnameById()!=null && !"".equals(getEmpnameById()))   {
                        if("All".equals(getEmpnameById())){
                          List empList = DataSourceDataProvider.getInstance().getListFromMap(getEmpnamesList());
                                 // empIds = DataSourceDataProvider.getInstance().getCustomerList((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS));
                                 
                                   //queryStringBuffer.append(" AND EmpId IN ("+empIds+")");
                            queryStringBuffer.append(" AND EmpId IN ("+empList.toString().substring(1, empList.toString().length()-1) +")");
                           
                        }
                        else{
                     
                                queryStringBuffer.append(" AND (EmpId like '"+getEmpnameById()+"')");
                           
                        }
                        
                   // }else if(getEmpnameById() == null || "".equals(getEmpnameById())) {
                         }else  {
                     
                              // empIds = DataSourceDataProvider.getInstance().getCustomerList((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS));
                              
                              // queryStringBuffer.append(" AND EmpId IN ("+empIds+") ");
                         List empList = DataSourceDataProvider.getInstance().getListFromMap(getEmpnamesList());
                                 // empIds = DataSourceDataProvider.getInstance().getCustomerList((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS));
                                 
                                   //queryStringBuffer.append(" AND EmpId IN ("+empIds+")");
                            queryStringBuffer.append(" AND EmpId IN ("+empList.toString().substring(1, empList.toString().length()-1) +")");
                         
                        }
                     if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (Description like '"+getDescription()+"')");
                    }
                 
                    queryStringBuffer.append(" order by DateStart desc Limit 250");
                    httpServletRequest.getSession().setAttribute("queryTeam",queryStringBuffer.toString());
                  //  System.out.println(queryStringBuffer.toString());
                }
                
               // System.out.print(queryStringBuffer.toString());
              
               
                resultType = SUCCESS;
            } catch (Exception ex){
                ex.printStackTrace();
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
            
            
        }
        
        return resultType;
    }

    */
   
    public String newEmpTimeSheets() throws Exception {
        resultType = LOGIN;
         String loginId = "";
         DataSourceDataProvider dataSourceDataProvider;
      //  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
        // if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
          if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null){
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            try{
                if(httpServletRequest.getSession(false).getAttribute("sDate") != null) {
                    httpServletRequest.getSession(false).setAttribute("sDate",null);
                }
                if(httpServletRequest.getSession(false).getAttribute("eDate") != null) {
                    httpServletRequest.getSession(false).setAttribute("eDate",null);
                }
               /* if(httpServletRequest.getSession(false).getAttribute("isFirstOp") != null) {
                    httpServletRequest.getSession(false).setAttribute("isFirstOp","yes");
                }*/
                 if(httpServletRequest.getSession(false).getAttribute("isFirstTeam") != null) {
                httpServletRequest.getSession(false).setAttribute("isFirstTeam","yes");
            }
                String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            //    String  userId = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID);
                String roleId = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID);
                String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
               // setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                
                //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
               // setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
               // setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
               setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                 //  dataSourceDataProvider = dataSourceDataProvider.getInstance();
            //change 
                   /* if(empType.equalsIgnoreCase("e")){
            String  empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(empId)));
            }*/
                   
                setEmpnameById(getEmpnameById());
                //change
               /*  if(empType.equalsIgnoreCase("E")){
                  // String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();   
                  // setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                     setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
               // setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                   loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           }else{ */
               setEmpnamesList(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
               // setMyTeamMembers(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
               loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                 //}
                if(getSubmitFrom()==null){
                    queryStringBuffer = new StringBuffer();
                    //change
                   /* if(empType.equalsIgnoreCase("E")){
                    queryStringBuffer.append("SELECT * FROM vwTimeSheetList where Description not in ('Approved') AND WorkingCountry like '"+workingCountry+"'");
                    }else {*/
                        // queryStringBuffer.append("SELECT * FROM vwCustTimeSheetList where Description not in ('Approved') AND  ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") ");
                    // queryStringBuffer.append("SELECT * FROM vwCustTimeSheetList where Description not in ('Approved')  ");
                   //  queryStringBuffer.append("SELECT * FROM vwNewCustTimeSheetList where Description not in ('Approved')  ");
                      queryStringBuffer.append("SELECT * FROM vwNewCustTimeSheetList where 1=1  ");
                    //}
                    if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        queryStringBuffer.append(" AND date(DateStart) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        queryStringBuffer.append(" AND date(DateEnd) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
                    
                    
                    
                    
                    String empIds ="";
                    if(getEmpnameById()!=null && !"".equals(getEmpnameById()))   {
                        if("All".equals(getEmpnameById())){
                          List empList = DataSourceDataProvider.getInstance().getListFromMap(getEmpnamesList());
                                 // empIds = DataSourceDataProvider.getInstance().getCustomerList((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS));
                                 
                                   //queryStringBuffer.append(" AND EmpId IN ("+empIds+")");
                            queryStringBuffer.append(" AND EmpId IN ("+empList.toString().substring(1, empList.toString().length()-1) +")");
                           
                        }
                        else{
                     
                                queryStringBuffer.append(" AND (EmpId like '"+getEmpnameById()+"')");
                           
                        }
                        
                   // }else if(getEmpnameById() == null || "".equals(getEmpnameById())) {
                         }else  {
                     
                              // empIds = DataSourceDataProvider.getInstance().getCustomerList((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS));
                              
                              // queryStringBuffer.append(" AND EmpId IN ("+empIds+") ");
                         List empList = DataSourceDataProvider.getInstance().getListFromMap(getEmpnamesList());
                                 // empIds = DataSourceDataProvider.getInstance().getCustomerList((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS));
                                 
                                   //queryStringBuffer.append(" AND EmpId IN ("+empIds+")");
                            queryStringBuffer.append(" AND EmpId IN ("+empList.toString().substring(1, empList.toString().length()-1) +")");
                         
                        }
                     if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (Description like '"+getDescription()+"')");
                    }
                  if(getSecDescription()!=null && !"".equals(getSecDescription())){
                         queryStringBuffer.append(" AND (SecDescription like '"+getSecDescription()+"')");
                    }
                    queryStringBuffer.append(" order by DateStart desc Limit 250");
                    httpServletRequest.getSession().setAttribute("queryTeam",queryStringBuffer.toString());
                  //  System.out.println(queryStringBuffer.toString());
                }
                
               // System.out.print(queryStringBuffer.toString());
               /* else{
                    queryStringBuffer = new StringBuffer();
                    if(httpServletRequest.getSession().getAttribute("queryTeam")==null){
                       queryStringBuffer.append("SELECT * FROM vwTimeSheetList where WorkingCountry like '"+workingCountry+"'");
                    }else{
                    httpServletRequest.getSession().setAttribute("queryTeam",queryStringBuffer);
                    }
                }*/
               
                resultType = SUCCESS;
            } catch (Exception ex){
                ex.printStackTrace();
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
            
            
        }
        
        return resultType;
    }

    
    
public String newSendReminders() throws ServiceLocatorException {
    resultType = LOGIN;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
        userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
        resultType = "accessFailed";
        String eType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
        
        
        String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
     //change
       
          
        if(AuthorizationManager.getInstance().isAuthorizedUser("SEND_REMINDERS",userRoleId)){
            try {
                 if(eType.equalsIgnoreCase("E")){
      //  setEmpnamesList(DataSourceDataProvider.getInstance().getEmployeeNamesByID(workingCountry));
        // String  empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
         //       setCustnamesList(dataSourceDataProvider.getInstance().getCustomerTeamMap(Integer.parseInt(empId)));
                //new
                newEmpTimeSheetSearch(); 
                
         }else {
             setEmpnamesList(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
        setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                 } 
             /*     if(eType.equalsIgnoreCase("E")){
                      empTimeSheetSearch(); 
                  } */
                 
            String toEmail = null;
            String reportsToType = null;
            String bodyContent = null;
            if(getTimeSheetStat().equalsIgnoreCase("Submit")) {
           //     if(getEmpType().equalsIgnoreCase("e")){
                     if(getResourceType().equalsIgnoreCase("e")){
                // setResourceType("e");
                // setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                
        
               //  if(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_REPORTSTOFLAG).toString())==0) {
                      if(DataSourceDataProvider.getInstance().getReportsToCheck(Integer.parseInt(getEmpID())) ==0) {
                    // toEmail = DataSourceDataProvider.getInstance().getReportsToById(Integer.parseInt(getEmpID())) +"@miraclesoft.com";
                          toEmail = DataSourceDataProvider.getInstance().getReportsToEmailByEmployeeId(Integer.parseInt(getEmpID())) ;
                     reportsToType = "e";
                 }  else {
                   String  reportsToDetails = DataSourceDataProvider.getInstance().getReportsToDetails(getEmpID());
                   //System.out.println("reportsToDetails-->"+reportsToDetails);
                   if(!"".equals(reportsToDetails)) {
                   toEmail = reportsToDetails.split("\\|")[0];
                   reportsToType = reportsToDetails.split("\\|")[1];
                 }
                 } 
                 
            }else{
                 //setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
               //  String resourceType= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_RESOURCETYPE).toString();
               //setResourceType(resourceType);
              String  reportsToDetails = DataSourceDataProvider.getInstance().getReportsToDetails(getEmpID());
                       if(!"".equals(reportsToDetails)) {  
              toEmail = reportsToDetails.split("\\|")[0];
                   reportsToType = reportsToDetails.split("\\|")[1];
                       }
                        
            }
                 
             // bodyContent = ServiceLocator.getNewTimeSheetService().getSubmitTimeSheetEmailLogBody(getEmpName(),getWstDate(),getWenDate(),getEmpID(),reportsToType,Integer.parseInt(getTimeSheetID()),getResourceType(),getTimeSheetStat());                   // String subject = "TimeSheet Reminder";
                     //   bodyContent = ServiceLocator.getNewTimeSheetService().getSubmitTimeSheetEmailLogBody(getEmpName(),getWstDate(),getWenDate(),getEmpID(),reportsToType,Integer.parseInt(getTimeSheetID()),getResourceType(),"Approved","Submitted");                   // String subject = "TimeSheet Reminder";
                        bodyContent = ServiceLocator.getNewTimeSheetService().getSubmitTimeSheetEmailLogBody(getEmpName(),getWstDate(),getWenDate(),getEmpID(),reportsToType,Integer.parseInt(getTimeSheetID()),getResourceType());                   // String subject = "TimeSheet Reminder";
                        //  ServiceLocator.getMailServices().doAddEmailLog(reportsToEmail, "nseerapu@miraclesoft.com", subject, bodyConetent, "nseerapu@miraclesoft.com");    
            }else {
                
                // toEmail = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(Integer.parseInt(getEmpID()), getResourceType());
                         toEmail = DataSourceDataProvider.getInstance().getEmailIdByObjectId(Integer.parseInt(getEmpID()), getResourceType());
                 if(getTimeSheetStat().equalsIgnoreCase("Reject")) {
                     bodyContent = ServiceLocator.getNewTimeSheetService().getRejectTimeSheetEmailLogBody(getEmpName(), getWstDate(), getWenDate());
                 }else {
                     bodyContent = ServiceLocator.getNewTimeSheetService().getEnteredTimeSheetEmailLogBody(getEmpName(), getWstDate(), getWenDate());
                 }
                 
                 
            }
                 
                 
                 
                 if(Properties.getProperty("Mail.Flag").equals("1")&& !"".equals(toEmail)) {
                     
                       String subject = "Timesheet Reminder";
                        String timesheetCC = Properties.getProperty("Timesheet.CC");
                          String timesheetBCC = Properties.getProperty("Timesheet.BCC");
                    //  ServiceLocator.getMailServices().doAddEmailLog(toEmail, timesheetCC, subject, bodyContent,"", timesheetBCC);
                            ServiceLocator.getMailServices().doAddEmailLogNew(toEmail, timesheetCC, subject, bodyContent,"", timesheetBCC,"Timesheets");
                    // ServiceLocator.getMailServices().doAddEmailLog(toEmail, "nseerapu@miraclesoft.com", subject, bodyContent, "nseerapu@miraclesoft.com");
                  // MailManager mail = new MailManager();
                 //  mail.sendReminders(getEmpName(),getTimeSheetID(),getEmpID(),eType,httpServletRequest,getResourceType(),getProjectId());
                 }
                    resultMessage = "<font color=\"green\" size=\"1.5\">Reminder has been sent successfully!</font>";
                  if(eType.equalsIgnoreCase("E")){
               
                 resultType = "esuccess";
                  }else {
                      resultType = SUCCESS;
                  }
                
            }catch(Exception ex) {
                ex.printStackTrace();
                resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                
                
                  if(eType.equalsIgnoreCase("E")){
                
                 resultType = "einput";
                  }else {
                      resultType = INPUT;
                  }
                
                
                //resultType = INPUT;
            }
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
        }
    }
    return resultType;
}


    
    

/*
 * New method for customer team timesheets serach
 * DAte : 01/27/2015
 * 
 */

     public String newCustomerTeamTimeSheets() {
        resultType = LOGIN;
        String list = "";
        String queryString = "";
        String loginId = "";
     
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null){
          //  String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            
          try{
           DataSourceDataProvider dataSourceDataProvider = DataSourceDataProvider.getInstance();
              String resourceId="";
            
           
          
                  resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();               
                  loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                   list = loginId;
               String roleId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_ROLE).toString();    
                // if(getTeamType()==0)
                       setCustnamesList(dataSourceDataProvider.getCustomerAllTeamMap(Integer.parseInt(resourceId)));
               
                /*  else if(getTeamType()==1)
                       setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(resourceId)));
                 else
                       setCustnamesList(dataSourceDataProvider.getCustomerSecondaryTeamMap(Integer.parseInt(resourceId)));*/
               //  System.out.println("custnameslist-->"+getCustnamesList());
               setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
             
               
               
               setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
              
            
           
            if(httpServletRequest.getSession(false).getAttribute("sDate") != null) {
                httpServletRequest.getSession(false).setAttribute("sDate",null);
            }
            if(httpServletRequest.getSession(false).getAttribute("eDate") != null) {
                httpServletRequest.getSession(false).setAttribute("eDate",null);
            }
            if(httpServletRequest.getSession(false).getAttribute("isFirstTeam") != null) {
                httpServletRequest.getSession(false).setAttribute("isFirstTeam","yes");
            }
          
           queryStringBuffer = new StringBuffer();
           
           
            if(getSubmitFrom()==null){
                
             /*   if(empType.equalsIgnoreCase("E")){
              
                     if(getEmpCusType() == null || "Internal".equalsIgnoreCase(getEmpCusType())){
                        queryString = "SELECT * FROM vwNewTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) ";
                       
                    }
                
                    else
                    {
                        String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                         
                 
                        if(getTeamType()==0) {  
                            List teamList = DataSourceDataProvider.getInstance().getListFromMap(getCustnamesList());
                     
                          if(teamList.size()>0) {
                              
                          queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ("+teamList.toString().substring(1, teamList.toString().length()-1) +" ) ";
                          
                        }else {
                             queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where STATUS='Active' AND ResourceType = 1 AND ReportsTo in ("+empId+")) ";
                        }
                        }else if(getTeamType()==1){
                        List secTeamList = DataSourceDataProvider.getInstance().getListFromMap(DataSourceDataProvider.getInstance().getCustomerSecondaryTeamMap(Integer.parseInt(empId)));     
                             if(secTeamList.size()>0) {
                                  queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +") AND Description = 'Approved' ";
                             }
                             else {
                                queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where STATUS='Active' AND ResourceType = 1 AND SecondReportTo in ("+empId+")) AND Description = 'Approved' ";  
                             }
                        }
                        else {
                        List secTeamList = DataSourceDataProvider.getInstance().getListFromMap(DataSourceDataProvider.getInstance().getCustomerSecondaryTeamMap(Integer.parseInt(empId)));     
                        List teamList = DataSourceDataProvider.getInstance().getListFromMap(getCustnamesList());
                       
                        
                        if(teamList.size()>0) {
                            
                         //   queryString = "SELECT * FROM vwNewCustTimeSheetList where 1=1 ";
                            
                          queryString = "SELECT * FROM vwNewCustTimeSheetList where (EmpId IN ("+teamList.toString().substring(1, teamList.toString().length()-1) +" ) ";
                          if(secTeamList.size()>0) {
                                  queryString = queryString+" OR (EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +") AND Description = 'Approved' ))";
                             }
                             else {
                                queryString = queryString+" OR (EmpId IN ( SELECT ObjectId FROM tblProjectContacts where STATUS='Active' AND ResourceType = 1 AND SecondReportTo in ("+empId+")) AND Description = 'Approved' ))";  
                             }
                        }
                        else  if(secTeamList.size()>0) {
                                  queryString = "SELECT * FROM vwNewCustTimeSheetList where ( (EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +") AND Description = 'Approved' ))";
                             }
                        else {
                             queryString = "SELECT * FROM vwNewCustTimeSheetList where (EmpId IN ( SELECT ObjectId FROM tblProjectContacts where STATUS='Active' AND ResourceType = 1 AND ReportsTo in ("+empId+")) )";
                        }
                        }
                    }
                    
                    */
                //}else{
                 int customerId =Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                
                List lowerTeamList = DataSourceDataProvider.getInstance().getListFromMap((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                List higherTeamList = DataSourceDataProvider.getInstance().getListFromMap((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
                 queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE 1=1 ";
                 if(lowerTeamList.size()>0|| higherTeamList.size()>0){
                   if(lowerTeamList.size()>0) { 
                      queryString = queryString+" AND (EmpId IN ( "+lowerTeamList.toString().substring(1, lowerTeamList.toString().length()-1) +"   )  AND Description='Submitted') ";
                       }
                 
                  if(higherTeamList.size()>0) {
                      if(lowerTeamList.size()>0)  
                      queryString = queryString+" OR (EmpId IN ( "+higherTeamList.toString().substring(1, higherTeamList.toString().length()-1) +"   )  AND Description='Approved' AND SecDescription='Submitted') ";
                      else 
                           queryString = queryString+" AND (EmpId IN ( "+higherTeamList.toString().substring(1, higherTeamList.toString().length()-1) +"   )  AND Description='Approved' AND SecDescription='Submitted') ";
                       }
                 }else {
                     queryString = queryString+" AND EmpId IN ('-1') ";
                 }
                 queryStringBuffer.append(queryString);
                 
                 
              
                 
                 
                   /* if(getTeamType()==0) {
                  
                      //  List teamList = DataSourceDataProvider.getInstance().getListFromMap(getMyTeamMembers());
                          List teamList = DataSourceDataProvider.getInstance().getListFromMap(getCustnamesList());
                       if(teamList.size()>0) {
                      queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( "+teamList.toString().substring(1, teamList.toString().length()-1) +"   )  ";
                       }else{
                          queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE STATUS='Active' AND ResourceType = 1 AND ReportsTo IN("+list+")  )  ";
                       }
                    }  else if(getTeamType()==1){
                        List secTeamList = DataSourceDataProvider.getInstance().getListFromMap(DataSourceDataProvider.getInstance().getCustomerSecondaryTeamMap(customerId));     
                        if(secTeamList.size()>0) {
                            queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +"  ) AND Description = 'Approved'"; 
                        }else {
                             queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE STATUS='Active' AND ResourceType = 1 AND SecondReportTo IN("+list+")  ) AND Description = 'Approved'";
                        }
                    }else {
                          List teamList = DataSourceDataProvider.getInstance().getListFromMap(getMyTeamMembers());
                         List secTeamList = DataSourceDataProvider.getInstance().getListFromMap(DataSourceDataProvider.getInstance().getCustomerSecondaryTeamMap(customerId));         
                      if(teamList.size()>0) {
                           queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE (EmpId IN ( "+teamList.toString().substring(1, teamList.toString().length()-1) +"   )  ";
                           
                            if(secTeamList.size()>0) {
                            queryString = queryString+" OR (EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +"  ) AND Description = 'Approved'))"; 
                        }else {
                             queryString = queryString+" OR( EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE STATUS='Active' AND ResourceType = 1 AND SecondReportTo IN("+list+")  ) AND Description = 'Approved'))";
                        } 
                        }else if(secTeamList.size()>0) {
                            queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE (EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +"  ) AND Description = 'Approved')"; 
                        }else {
                             queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE (EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE STATUS='Active' AND ResourceType = 1 AND SecondReportTo IN("+list+")  ) AND Description = 'Approved')";
                        }
                         
                    
                        
                    }
                    //}
                
                
                    queryStringBuffer.append(queryString);
                    
                    if((getEmpnameById()!=null && !"".equals(getEmpnameById()))  ||    (getCustnameById()!=null && !"".equals(getCustnameById())) )   {
                        
                     
                        if("All".equals(getEmpnameById()) && "All".equals(getCustnameById())){
                   
                            
                        }
                        else{
                            if(empType.equalsIgnoreCase("E")){
                                if(getEmpCusType().equalsIgnoreCase("Internal")){
                                    queryStringBuffer.delete(0, queryStringBuffer.length());
                                     queryStringBuffer.append(" SELECT * FROM vwNewTimeSheetList WHERE (LoginId like '"+getEmpnameById()+"') ");
                                //queryStringBuffer.append(" AND (LoginId like '"+getEmpnameById()+"')");
                                }else{
                                    //if(getTeamType()==2) {
                                        queryStringBuffer.delete(0, queryStringBuffer.length());
                                       queryStringBuffer.append(" SELECT * FROM vwNewCustTimeSheetList WHERE (EmpId like '"+getCustnameById()+"') ");
                                   // }else {
                                //queryStringBuffer.append(" AND (EmpId like '"+getCustnameById()+"')");   
                                  //  }
                                }
                            }else{
                                if("All".equals(getCustnameById()))
                                {
                                  //  queryStringBuffer.append(" AND (EmpId like '%') ");
                                }else{
                                   // if(getTeamType()==2) {
                                         queryStringBuffer.delete(0, queryStringBuffer.length());
                                       queryStringBuffer.append(" SELECT * FROM vwNewCustTimeSheetList WHERE (EmpId like '"+getCustnameById()+"') ");
                                   // }else {
                                   // queryStringBuffer.append(" AND (EmpId like '"+getCustnameById()+"') ");
                                   // }
                                }
                            }
                        
                            
                        }
                        
                    }
                    
                    if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        queryStringBuffer.append(" AND date(DateStart) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        queryStringBuffer.append(" AND date(DateEnd) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
                 
                   if(empType.equalsIgnoreCase("c") || "External".equalsIgnoreCase(getEmpCusType())){
                      if(getTeamType()==1) {
                    if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (SecDescription like '"+getDescription()+"')");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND SecDescription='Submitted'");
                    }
                     }else if (getTeamType()==0){
                           if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (Description like '"+getDescription()+"')");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND Description='Submitted'");
                    }
                      }else {
                           if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND ((Description like '"+getDescription()+"') OR  (SecDescription like '"+getDescription()+"'))");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND Description='Submitted'");
                    }
                      }
                   }else {
                           if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (Description like '"+getDescription()+"')");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND Description='Submitted'");
                    }
                       
                   }
                      
                      */
                      
                    queryStringBuffer.append(" Order by DateStart desc Limit 250");
                   
                    
                    
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST);
                    }
                   httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,queryStringBuffer.toString());
                    
                }
            

               //     System.out.print(queryStringBuffer.toString());

            //For Two Level
            //String timeSheetQuery = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo IN ("+getKeys(getMyTeamMembers(),",")+")) AND  Description='Submitted' ";
            
            
            resultType = SUCCESS;
        } catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        } 
        return resultType;
    }
    
    
    
     public String newCustomerTeamTimeSheetsSearch() {
        resultType = LOGIN;
        String list = "";
        String queryString = "";
        String loginId = "";
     
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null){
         
            
          try{
           DataSourceDataProvider dataSourceDataProvider = DataSourceDataProvider.getInstance();
              String resourceId="";
            
           
          
                  resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();               
                  loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                   list = loginId;
               String roleId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_ROLE).toString();    
                if(getTeamType()==0)
                       setCustnamesList(dataSourceDataProvider.getCustomerAllTeamMap(Integer.parseInt(resourceId)));
               
                else if(getTeamType()==1)
                       setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(resourceId)));
                 else
                       setCustnamesList(dataSourceDataProvider.getCustomerSecondaryTeamMap(Integer.parseInt(resourceId)));
               
               setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
             
               
               
               setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
              
            
           
            if(httpServletRequest.getSession(false).getAttribute("sDate") != null) {
                httpServletRequest.getSession(false).setAttribute("sDate",null);
            }
            if(httpServletRequest.getSession(false).getAttribute("eDate") != null) {
                httpServletRequest.getSession(false).setAttribute("eDate",null);
            }
            if(httpServletRequest.getSession(false).getAttribute("isFirstTeam") != null) {
                httpServletRequest.getSession(false).setAttribute("isFirstTeam","yes");
            }
          
           queryStringBuffer = new StringBuffer();
           
           
            if(getSubmitFrom()==null){
                
            
                 int customerId =Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
                
               // List lowerTeamList = DataSourceDataProvider.getInstance().getListFromMap((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                //List higherTeamList = DataSourceDataProvider.getInstance().getListFromMap((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
                 queryString = "SELECT * FROM vwNewCustTimeSheetList WHERE 1=1 ";
                 /*
                 
                 if(!"All".equals(getCustnameById())){
                     queryString = queryString+ " AND EmpId="+getCustnameById();
                     
                     if(DataSourceDataProvider.getInstance().getReportsTypeByTeamMemberId(Integer.parseInt(getCustnameById()),customerId)==2){
                          queryString = queryString+" AND SecDescription='"+getDescription()+"'";
                     }else{
                         queryString = queryString+" AND Description='"+getDescription()+"'";
                     }
                 }else {
                      if(lowerTeamList.size()>0|| higherTeamList.size()>0){
                   if(lowerTeamList.size()>0) { 
                      queryString = queryString+" AND (EmpId IN ( "+lowerTeamList.toString().substring(1, lowerTeamList.toString().length()-1) +"   )  AND Description='"+getDescription()+"') ";
                       }
                 
                  if(higherTeamList.size()>0) {
                      if(lowerTeamList.size()>0)  
                      queryString = queryString+" OR (EmpId IN ( "+higherTeamList.toString().substring(1, higherTeamList.toString().length()-1) +"   )  AND Description='Approved' AND SecDescription='"+getDescription()+"') ";
                      else 
                           queryString = queryString+" AND (EmpId IN ( "+higherTeamList.toString().substring(1, higherTeamList.toString().length()-1) +"   )  AND Description='Approved' AND SecDescription='"+getDescription()+"') ";
                       }
                 }else {
                     queryString = queryString+" AND EmpId IN ('-1') ";
                 }
                 }*/
                  
                     if(getTeamType()==0){
                        
                     if(!"All".equals(getCustnameById())){
                         queryString = queryString + " AND EmpId="+getCustnameById()+" ";
                         if(DataSourceDataProvider.getInstance().getReportsTypeByTeamMemberId(Integer.parseInt(getCustnameById()),customerId)==2){
                          queryString = queryString+" AND SecDescription='"+getDescription()+"'";
                     }else{
                         queryString = queryString+" AND Description='"+getDescription()+"'";
                     }
                     }else {
                           List lowerTeamList = DataSourceDataProvider.getInstance().getListFromMap((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                List higherTeamList = DataSourceDataProvider.getInstance().getListFromMap((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
                
                         if(lowerTeamList.size()>0|| higherTeamList.size()>0){
                   if(lowerTeamList.size()>0) { 
                      queryString = queryString+" AND (EmpId IN ( "+lowerTeamList.toString().substring(1, lowerTeamList.toString().length()-1) +"   )  AND Description='"+getDescription()+"') ";
                       }
                 
                  if(higherTeamList.size()>0) {
                      if(lowerTeamList.size()>0)  
                      queryString = queryString+" OR (EmpId IN ( "+higherTeamList.toString().substring(1, higherTeamList.toString().length()-1) +"   )  AND Description='Approved' AND SecDescription='"+getDescription()+"') ";
                      else 
                           queryString = queryString+" AND (EmpId IN ( "+higherTeamList.toString().substring(1, higherTeamList.toString().length()-1) +"   )  AND Description='Approved' AND SecDescription='"+getDescription()+"') ";
                       }
                 }else {
                     queryString = queryString+" AND EmpId IN ('-1') ";
                 }
                     }
               }else if(getTeamType()==1){
                   if(!"All".equals(getCustnameById())){
                        queryString = queryString + " AND EmpId="+getCustnameById()+" AND Description='"+getDescription()+"'";
                   }else {
                       List lowerTeamList = DataSourceDataProvider.getInstance().getListFromMap((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                        if(lowerTeamList.size()>0) { 
                      queryString = queryString+" AND (EmpId IN ( "+lowerTeamList.toString().substring(1, lowerTeamList.toString().length()-1) +"   )  AND Description='"+getDescription()+"') ";
                       }else {
                            queryString = queryString+" AND EmpId IN ('-1') ";
                        }
                   }
               }else if(getTeamType()==2){
                     if(!"All".equals(getCustnameById())){
                        queryString = queryString + " AND EmpId="+getCustnameById()+" AND SecDescription='"+getDescription()+"'";
                   }else {
                        List higherTeamList = DataSourceDataProvider.getInstance().getListFromMap((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
                        if(higherTeamList.size()>0) { 
                      queryString = queryString+" AND (EmpId IN ( "+higherTeamList.toString().substring(1, higherTeamList.toString().length()-1) +"   )  AND Description='Approved' AND SecDescription='"+getDescription()+"') ";
                       }else {
                            queryString = queryString+" AND EmpId IN ('-1') ";
                        }
                   }
               }
                    
               queryStringBuffer.append(queryString);
                  if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        queryStringBuffer.append(" AND date(DateStart) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        queryStringBuffer.append(" AND date(DateEnd) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
              
                    queryStringBuffer.append(" Order by DateStart desc Limit 250");
                   
                    
                    
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST);
                    }
                   httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,queryStringBuffer.toString());
                    
                }
            

            resultType = SUCCESS;
        } catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        } 
        return resultType;
    }
    
    
    
    
     public String newEmployeeteamTimeSheets() {
        resultType = LOGIN;
        String list = "";
        String queryString = "";
        String loginId = "";
     
        if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            
          try{
           DataSourceDataProvider dataSourceDataProvider = DataSourceDataProvider.getInstance();
              String resourceId="";
            
           
            // if(empType.equalsIgnoreCase("e")){
        
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                  setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
             
                /*   if(getTeamType()==0)
                setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(resourceId)));
             else if(getTeamType()==1)
                 setCustnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
             else */
                  setCustnamesList(DataSourceDataProvider.getInstance().getCustomerAllTeamMap(Integer.parseInt(resourceId)));
          
             
                 
                 
                 
                // setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(resourceId)));
                
                
                
                 loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
               
                   list=ServiceLocator.getNewTimeSheetService().getTeamMembersList(loginId);
                  setProjectMap(DataSourceDataProvider.getInstance().getProjectsMapByEmpId(Integer.parseInt(resourceId)));
           // }
               
               
               setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
              
            
           
            if(httpServletRequest.getSession(false).getAttribute("sDate") != null) {
                httpServletRequest.getSession(false).setAttribute("sDate",null);
            }
            if(httpServletRequest.getSession(false).getAttribute("eDate") != null) {
                httpServletRequest.getSession(false).setAttribute("eDate",null);
            }
            if(httpServletRequest.getSession(false).getAttribute("isFirstTeam") != null) {
                httpServletRequest.getSession(false).setAttribute("isFirstTeam","yes");
            }
          
           queryStringBuffer = new StringBuffer();
           
           
            if(getSubmitFrom()==null){
                queryString = "SELECT * FROM vwNewTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) AND Description='Submitted' ";
                 queryStringBuffer.append(queryString);
                 
                
                
                
                
                
                /*
                if(empType.equalsIgnoreCase("E")){
              
                     if(getEmpCusType() == null || "Internal".equalsIgnoreCase(getEmpCusType())){
                        queryString = "SELECT * FROM vwNewTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) ";
                       
                    }
                
                    else
                    {
                        String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                         
                 
                        if(getTeamType()==0) {  
                            List teamList = DataSourceDataProvider.getInstance().getListFromMap(getCustnamesList());
                     
                          if(teamList.size()>0) {
                              
                          queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ("+teamList.toString().substring(1, teamList.toString().length()-1) +" ) ";
                          
                        }else {
                             queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where STATUS='Active' AND ResourceType = 1 AND ReportsTo in ("+empId+")) ";
                        }
                        }else if(getTeamType()==1){
                        List secTeamList = DataSourceDataProvider.getInstance().getListFromMap(DataSourceDataProvider.getInstance().getCustomerSecondaryTeamMap(Integer.parseInt(empId)));     
                             if(secTeamList.size()>0) {
                                  queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +") AND Description = 'Approved' ";
                             }
                             else {
                                queryString = "SELECT * FROM vwNewCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where STATUS='Active' AND ResourceType = 1 AND SecondReportTo in ("+empId+")) AND Description = 'Approved' ";  
                             }
                        }
                        else {
                        List secTeamList = DataSourceDataProvider.getInstance().getListFromMap(DataSourceDataProvider.getInstance().getCustomerSecondaryTeamMap(Integer.parseInt(empId)));     
                        List teamList = DataSourceDataProvider.getInstance().getListFromMap(getCustnamesList());
                       
                        
                        if(teamList.size()>0) {
                            
                         //   queryString = "SELECT * FROM vwNewCustTimeSheetList where 1=1 ";
                            
                          queryString = "SELECT * FROM vwNewCustTimeSheetList where (EmpId IN ("+teamList.toString().substring(1, teamList.toString().length()-1) +" ) ";
                          if(secTeamList.size()>0) {
                                  queryString = queryString+" OR (EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +") AND Description = 'Approved' ))";
                             }
                             else {
                                queryString = queryString+" OR (EmpId IN ( SELECT ObjectId FROM tblProjectContacts where STATUS='Active' AND ResourceType = 1 AND SecondReportTo in ("+empId+")) AND Description = 'Approved' ))";  
                             }
                        }
                        else  if(secTeamList.size()>0) {
                                  queryString = "SELECT * FROM vwNewCustTimeSheetList where ( (EmpId IN ( "+secTeamList.toString().substring(1, secTeamList.toString().length()-1) +") AND Description = 'Approved' ))";
                             }
                        else {
                             queryString = "SELECT * FROM vwNewCustTimeSheetList where (EmpId IN ( SELECT ObjectId FROM tblProjectContacts where STATUS='Active' AND ResourceType = 1 AND ReportsTo in ("+empId+")) )";
                        }
                        }
                    }
                    
                    
                }
                
                
                    queryStringBuffer.append(queryString);
                    
                    if((getEmpnameById()!=null && !"".equals(getEmpnameById()))  ||    (getCustnameById()!=null && !"".equals(getCustnameById())) )   {
                        
                     
                        if("All".equals(getEmpnameById()) && "All".equals(getCustnameById())){
                       
                            
                        }
                        else{
                            if(empType.equalsIgnoreCase("E")){
                                if(getEmpCusType().equalsIgnoreCase("Internal")){
                                    queryStringBuffer.delete(0, queryStringBuffer.length());
                                     queryStringBuffer.append(" SELECT * FROM vwNewTimeSheetList WHERE (LoginId like '"+getEmpnameById()+"') ");
                                //queryStringBuffer.append(" AND (LoginId like '"+getEmpnameById()+"')");
                                }else{
                                    //if(getTeamType()==2) {
                                        queryStringBuffer.delete(0, queryStringBuffer.length());
                                       queryStringBuffer.append(" SELECT * FROM vwNewCustTimeSheetList WHERE (EmpId like '"+getCustnameById()+"') ");
                                   // }else {
                                //queryStringBuffer.append(" AND (EmpId like '"+getCustnameById()+"')");   
                                  //  }
                                }
                            }else{
                                if("All".equals(getCustnameById()))
                                {
                                  //  queryStringBuffer.append(" AND (EmpId like '%') ");
                                }else{
                                   // if(getTeamType()==2) {
                                         queryStringBuffer.delete(0, queryStringBuffer.length());
                                       queryStringBuffer.append(" SELECT * FROM vwNewCustTimeSheetList WHERE (EmpId like '"+getCustnameById()+"') ");
                                   // }else {
                                   // queryStringBuffer.append(" AND (EmpId like '"+getCustnameById()+"') ");
                                   // }
                                }
                            }
                        
                            
                        }
                        
                    }
                    
                    if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        queryStringBuffer.append(" AND date(DateStart) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        queryStringBuffer.append(" AND date(DateEnd) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
                 
                   if(empType.equalsIgnoreCase("c") || "External".equalsIgnoreCase(getEmpCusType())){
                      if(getTeamType()==1) {
                    if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (SecDescription like '"+getDescription()+"')");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND SecDescription='Submitted'");
                    }
                     }else if (getTeamType()==0){
                           if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (Description like '"+getDescription()+"')");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND Description='Submitted'");
                    }
                      }else {
                           if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND ((Description like '"+getDescription()+"') OR  (SecDescription like '"+getDescription()+"'))");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND Description='Submitted'");
                    }
                      }
                   }else {
                           if(getDescription()!=null && !"".equals(getDescription())){
                         queryStringBuffer.append(" AND (Description like '"+getDescription()+"')");
                    }
                    else{
                        
                        queryStringBuffer.append(" AND Description='Submitted'");
                    }
                       
                   }
                      
                      */
                      
                    queryStringBuffer.append(" Order by DateStart desc Limit 250");
                   
                    
                    
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,queryStringBuffer.toString());
                }
            

               //     System.out.print(queryStringBuffer.toString());

            //For Two Level
            //String timeSheetQuery = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo IN ("+getKeys(getMyTeamMembers(),",")+")) AND  Description='Submitted' ";
            
            
            resultType = SUCCESS;
        } catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        } 
        return resultType;
    }
    
    
     
     
    
     public String newEmployeeteamTimeSheetsSearch() {
        resultType = LOGIN;
        String list = "";
        String queryString = "";
        String loginId = "";
     
        if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            
          try{
           DataSourceDataProvider dataSourceDataProvider = DataSourceDataProvider.getInstance();
              String resourceId="";
            
           
            // if(empType.equalsIgnoreCase("e")){
        
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                  setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
             
                 if(getTeamType()==0)
                      setCustnamesList(DataSourceDataProvider.getInstance().getCustomerAllTeamMap(Integer.parseInt(resourceId)));
                
             else if(getTeamType()==1)
                 setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(resourceId)));
             else 
                 setCustnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
          
             
                 
                 
                 
                // setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(resourceId)));
                
                
                
                 loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
               
                   list=ServiceLocator.getNewTimeSheetService().getTeamMembersList(loginId);
                  setProjectMap(DataSourceDataProvider.getInstance().getProjectsMapByEmpId(Integer.parseInt(resourceId)));
           // }
               
               
               setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
              
            
           
            if(httpServletRequest.getSession(false).getAttribute("sDate") != null) {
                httpServletRequest.getSession(false).setAttribute("sDate",null);
            }
            if(httpServletRequest.getSession(false).getAttribute("eDate") != null) {
                httpServletRequest.getSession(false).setAttribute("eDate",null);
            }
            if(httpServletRequest.getSession(false).getAttribute("isFirstTeam") != null) {
                httpServletRequest.getSession(false).setAttribute("isFirstTeam","yes");
            }
          
           queryStringBuffer = new StringBuffer();
           
           
            if(getSubmitFrom()==null){
                
               if( "Internal".equalsIgnoreCase(getEmpCusType())){
                   
                   queryString = "SELECT * FROM vwNewTimeSheetList where 1=1 ";
                   if(!"All".equals(getEmpnameById())){
                       queryString = queryString + " AND LoginId='"+getEmpnameById()+"' AND Description='"+getDescription()+"'";
                   }else {
                        queryString = queryString + " AND EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) AND Description='"+getDescription()+"'";
                   }
                   
               }else {
                    String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                   
                     queryString = "SELECT * FROM vwNewCustTimeSheetList where 1=1 ";
                     
                     
                     if(getTeamType()==0){
                        
                     if(!"All".equals(getCustnameById())){
                         queryString = queryString + " AND EmpId="+getCustnameById()+" ";
                         if(DataSourceDataProvider.getInstance().getReportsTypeByTeamMemberId(Integer.parseInt(getCustnameById()),Integer.parseInt(empId))==2){
                          queryString = queryString+" AND SecDescription='"+getDescription()+"'";
                     }else{
                         queryString = queryString+" AND Description='"+getDescription()+"'";
                     }
                     }else {
                           List lowerTeamList = DataSourceDataProvider.getInstance().getListFromMap(DataSourceDataProvider.getInstance().getCustomerTeamMap(Integer.parseInt(empId)));
                List higherTeamList = DataSourceDataProvider.getInstance().getListFromMap((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
                
                         if(lowerTeamList.size()>0|| higherTeamList.size()>0){
                   if(lowerTeamList.size()>0) { 
                      queryString = queryString+" AND (EmpId IN ( "+lowerTeamList.toString().substring(1, lowerTeamList.toString().length()-1) +"   )  AND Description='"+getDescription()+"') ";
                       }
                 
                  if(higherTeamList.size()>0) {
                      if(lowerTeamList.size()>0)  
                      queryString = queryString+" OR (EmpId IN ( "+higherTeamList.toString().substring(1, higherTeamList.toString().length()-1) +"   )  AND Description='Approved' AND SecDescription='"+getDescription()+"') ";
                      else 
                           queryString = queryString+" AND (EmpId IN ( "+higherTeamList.toString().substring(1, higherTeamList.toString().length()-1) +"   )  AND Description='Approved' AND SecDescription='"+getDescription()+"') ";
                       }
                 }else {
                     queryString = queryString+" AND EmpId IN ('-1') ";
                 }
                     }
               }else if(getTeamType()==1){
                   if(!"All".equals(getCustnameById())){
                        queryString = queryString + " AND EmpId="+getCustnameById()+" AND Description='"+getDescription()+"'";
                   }else {
                        List lowerTeamList = DataSourceDataProvider.getInstance().getListFromMap(DataSourceDataProvider.getInstance().getCustomerTeamMap(Integer.parseInt(empId)));
                        if(lowerTeamList.size()>0) { 
                      queryString = queryString+" AND (EmpId IN ( "+lowerTeamList.toString().substring(1, lowerTeamList.toString().length()-1) +"   )  AND Description='"+getDescription()+"') ";
                       }else {
                            queryString = queryString+" AND EmpId IN ('-1') ";
                        }
                   }
               }else if(getTeamType()==2){
                     if(!"All".equals(getCustnameById())){
                        queryString = queryString + " AND EmpId="+getCustnameById()+" AND SecDescription='"+getDescription()+"'";
                   }else {
                        List higherTeamList = DataSourceDataProvider.getInstance().getListFromMap((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_SEC_TEAM));
                        if(higherTeamList.size()>0) { 
                      queryString = queryString+" AND (EmpId IN ( "+higherTeamList.toString().substring(1, higherTeamList.toString().length()-1) +"   )  AND Description='Approved' AND SecDescription='"+getDescription()+"') ";
                       }else {
                            queryString = queryString+" AND EmpId IN ('-1') ";
                        }
                   }
               }
                    
                     
                    
               }
               
                 queryStringBuffer.append(queryString);
                 
                 if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        queryStringBuffer.append(" AND date(DateStart) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        queryStringBuffer.append(" AND date(DateEnd) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
              
                    queryStringBuffer.append(" Order by DateStart desc Limit 250");
                   
                    
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,queryStringBuffer.toString());
                }
            

               //     System.out.print(queryStringBuffer.toString());

            //For Two Level
            //String timeSheetQuery = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo IN ("+getKeys(getMyTeamMembers(),",")+")) AND  Description='Submitted' ";
            
            
            resultType = SUCCESS;
        } catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        } 
        return resultType;
    }
    
	
    
    public void getTimeSheetAttachementDownload() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String fileLocation = DataSourceDataProvider.getInstance().getAttachmentLocationByEmpId(getEmpID(),getTimeSheetID());
          
            httpServletResponse.setContentType("application/force-download");
            File file = new File(fileLocation);
            String fileName = file.getName();
            System.out.println("Filename" + fileName);
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
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    
    
    
    
    
    public void setServletResponse(HttpServletResponse httpServletResponse) {
    this.httpServletResponse = httpServletResponse;
}
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
    this.httpServletRequest = httpServletRequest;
}

    /**
     * @return the resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * @param resultType the resultType to set
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    /**
     * @return the previousWeek
     */
    public String getPreviousWeek() {
        return previousWeek;
    }

    /**
     * @param previousWeek the previousWeek to set
     */
    public void setPreviousWeek(String previousWeek) {
        this.previousWeek = previousWeek;
    }

    /**
     * @return the timeSheetVTO
     */
    public NewTimeSheetVTO getTimeSheetVTO() {
        return timeSheetVTO;
    }

    /**
     * @param timeSheetVTO the timeSheetVTO to set
     */
    public void setTimeSheetVTO(NewTimeSheetVTO timeSheetVTO) {
        this.timeSheetVTO = timeSheetVTO;
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
     * @return the wstDate
     */
    public String getWstDate() {
        return wstDate;
    }

    /**
     * @param wstDate the wstDate to set
     */
    public void setWstDate(String wstDate) {
        this.wstDate = wstDate;
    }

    /**
     * @return the wenDate
     */
    public String getWenDate() {
        return wenDate;
    }

    /**
     * @param wenDate the wenDate to set
     */
    public void setWenDate(String wenDate) {
        this.wenDate = wenDate;
    }

    /**
     * @return the submittedDate
     */
    public String getSubmittedDate() {
        return submittedDate;
    }

    /**
     * @param submittedDate the submittedDate to set
     */
    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    /**
     * @return the approveDate
     */
    public String getApproveDate() {
        return approveDate;
    }

    /**
     * @param approveDate the approveDate to set
     */
    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    /**
     * @return the checkDate
     */
    public String getCheckDate() {
        return checkDate;
    }

    /**
     * @param checkDate the checkDate to set
     */
    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * @return the weekDate1
     */
    public String getWeekDate1() {
        return weekDate1;
    }

    /**
     * @param weekDate1 the weekDate1 to set
     */
    public void setWeekDate1(String weekDate1) {
        this.weekDate1 = weekDate1;
    }

    /**
     * @return the weekDate2
     */
    public String getWeekDate2() {
        return weekDate2;
    }

    /**
     * @param weekDate2 the weekDate2 to set
     */
    public void setWeekDate2(String weekDate2) {
        this.weekDate2 = weekDate2;
    }

    /**
     * @return the weekDate3
     */
    public String getWeekDate3() {
        return weekDate3;
    }

    /**
     * @param weekDate3 the weekDate3 to set
     */
    public void setWeekDate3(String weekDate3) {
        this.weekDate3 = weekDate3;
    }

    /**
     * @return the weekDate4
     */
    public String getWeekDate4() {
        return weekDate4;
    }

    /**
     * @param weekDate4 the weekDate4 to set
     */
    public void setWeekDate4(String weekDate4) {
        this.weekDate4 = weekDate4;
    }

    /**
     * @return the weekDate5
     */
    public String getWeekDate5() {
        return weekDate5;
    }

    /**
     * @param weekDate5 the weekDate5 to set
     */
    public void setWeekDate5(String weekDate5) {
        this.weekDate5 = weekDate5;
    }

    /**
     * @return the weekDate6
     */
    public String getWeekDate6() {
        return weekDate6;
    }

    /**
     * @param weekDate6 the weekDate6 to set
     */
    public void setWeekDate6(String weekDate6) {
        this.weekDate6 = weekDate6;
    }

    /**
     * @return the weekDate7
     */
    public String getWeekDate7() {
        return weekDate7;
    }

    /**
     * @param weekDate7 the weekDate7 to set
     */
    public void setWeekDate7(String weekDate7) {
        this.weekDate7 = weekDate7;
    }

    /**
     * @return the proj1Sun
     */
    public double getProj1Sun() {
        return proj1Sun;
    }

    /**
     * @param proj1Sun the proj1Sun to set
     */
    public void setProj1Sun(double proj1Sun) {
        this.proj1Sun = proj1Sun;
    }

    /**
     * @return the proj1Mon
     */
    public double getProj1Mon() {
        return proj1Mon;
    }

    /**
     * @param proj1Mon the proj1Mon to set
     */
    public void setProj1Mon(double proj1Mon) {
        this.proj1Mon = proj1Mon;
    }

    /**
     * @return the proj1Tus
     */
    public double getProj1Tus() {
        return proj1Tus;
    }

    /**
     * @param proj1Tus the proj1Tus to set
     */
    public void setProj1Tus(double proj1Tus) {
        this.proj1Tus = proj1Tus;
    }

    /**
     * @return the proj1Wed
     */
    public double getProj1Wed() {
        return proj1Wed;
    }

    /**
     * @param proj1Wed the proj1Wed to set
     */
    public void setProj1Wed(double proj1Wed) {
        this.proj1Wed = proj1Wed;
    }

    /**
     * @return the proj1Thur
     */
    public double getProj1Thur() {
        return proj1Thur;
    }

    /**
     * @param proj1Thur the proj1Thur to set
     */
    public void setProj1Thur(double proj1Thur) {
        this.proj1Thur = proj1Thur;
    }

    /**
     * @return the proj1Fri
     */
    public double getProj1Fri() {
        return proj1Fri;
    }

    /**
     * @param proj1Fri the proj1Fri to set
     */
    public void setProj1Fri(double proj1Fri) {
        this.proj1Fri = proj1Fri;
    }

    /**
     * @return the proj1Sat
     */
    public double getProj1Sat() {
        return proj1Sat;
    }

    /**
     * @param proj1Sat the proj1Sat to set
     */
    public void setProj1Sat(double proj1Sat) {
        this.proj1Sat = proj1Sat;
    }

    /**
     * @return the proj1TotalHrs
     */
    public double getProj1TotalHrs() {
        return proj1TotalHrs;
    }

    /**
     * @param proj1TotalHrs the proj1TotalHrs to set
     */
    public void setProj1TotalHrs(double proj1TotalHrs) {
        this.proj1TotalHrs = proj1TotalHrs;
    }

    /**
     * @return the proj2Sun
     */
    public double getProj2Sun() {
        return proj2Sun;
    }

    /**
     * @param proj2Sun the proj2Sun to set
     */
    public void setProj2Sun(double proj2Sun) {
        this.proj2Sun = proj2Sun;
    }

    /**
     * @return the proj2Mon
     */
    public double getProj2Mon() {
        return proj2Mon;
    }

    /**
     * @param proj2Mon the proj2Mon to set
     */
    public void setProj2Mon(double proj2Mon) {
        this.proj2Mon = proj2Mon;
    }

    /**
     * @return the proj2Tus
     */
    public double getProj2Tus() {
        return proj2Tus;
    }

    /**
     * @param proj2Tus the proj2Tus to set
     */
    public void setProj2Tus(double proj2Tus) {
        this.proj2Tus = proj2Tus;
    }

    /**
     * @return the proj2Wed
     */
    public double getProj2Wed() {
        return proj2Wed;
    }

    /**
     * @param proj2Wed the proj2Wed to set
     */
    public void setProj2Wed(double proj2Wed) {
        this.proj2Wed = proj2Wed;
    }

    /**
     * @return the proj2Thur
     */
    public double getProj2Thur() {
        return proj2Thur;
    }

    /**
     * @param proj2Thur the proj2Thur to set
     */
    public void setProj2Thur(double proj2Thur) {
        this.proj2Thur = proj2Thur;
    }

    /**
     * @return the proj2Fri
     */
    public double getProj2Fri() {
        return proj2Fri;
    }

    /**
     * @param proj2Fri the proj2Fri to set
     */
    public void setProj2Fri(double proj2Fri) {
        this.proj2Fri = proj2Fri;
    }

    /**
     * @return the proj2Sat
     */
    public double getProj2Sat() {
        return proj2Sat;
    }

    /**
     * @param proj2Sat the proj2Sat to set
     */
    public void setProj2Sat(double proj2Sat) {
        this.proj2Sat = proj2Sat;
    }

    /**
     * @return the proj2TotalHrs
     */
    public double getProj2TotalHrs() {
        return proj2TotalHrs;
    }

    /**
     * @param proj2TotalHrs the proj2TotalHrs to set
     */
    public void setProj2TotalHrs(double proj2TotalHrs) {
        this.proj2TotalHrs = proj2TotalHrs;
    }

    /**
     * @return the internalSun
     */
    public double getInternalSun() {
        return internalSun;
    }

    /**
     * @param internalSun the internalSun to set
     */
    public void setInternalSun(double internalSun) {
        this.internalSun = internalSun;
    }

    /**
     * @return the internalMon
     */
    public double getInternalMon() {
        return internalMon;
    }

    /**
     * @param internalMon the internalMon to set
     */
    public void setInternalMon(double internalMon) {
        this.internalMon = internalMon;
    }

    /**
     * @return the internalTus
     */
    public double getInternalTus() {
        return internalTus;
    }

    /**
     * @param internalTus the internalTus to set
     */
    public void setInternalTus(double internalTus) {
        this.internalTus = internalTus;
    }

    /**
     * @return the internalWed
     */
    public double getInternalWed() {
        return internalWed;
    }

    /**
     * @param internalWed the internalWed to set
     */
    public void setInternalWed(double internalWed) {
        this.internalWed = internalWed;
    }

    /**
     * @return the internalThur
     */
    public double getInternalThur() {
        return internalThur;
    }

    /**
     * @param internalThur the internalThur to set
     */
    public void setInternalThur(double internalThur) {
        this.internalThur = internalThur;
    }

    /**
     * @return the internalFri
     */
    public double getInternalFri() {
        return internalFri;
    }

    /**
     * @param internalFri the internalFri to set
     */
    public void setInternalFri(double internalFri) {
        this.internalFri = internalFri;
    }

    /**
     * @return the internalSat
     */
    public double getInternalSat() {
        return internalSat;
    }

    /**
     * @param internalSat the internalSat to set
     */
    public void setInternalSat(double internalSat) {
        this.internalSat = internalSat;
    }

    /**
     * @return the internalTotalHrs
     */
    public double getInternalTotalHrs() {
        return internalTotalHrs;
    }

    /**
     * @param internalTotalHrs the internalTotalHrs to set
     */
    public void setInternalTotalHrs(double internalTotalHrs) {
        this.internalTotalHrs = internalTotalHrs;
    }

    /**
     * @return the vacationSun
     */
    public double getVacationSun() {
        return vacationSun;
    }

    /**
     * @param vacationSun the vacationSun to set
     */
    public void setVacationSun(double vacationSun) {
        this.vacationSun = vacationSun;
    }

    /**
     * @return the vacationMon
     */
    public double getVacationMon() {
        return vacationMon;
    }

    /**
     * @param vacationMon the vacationMon to set
     */
    public void setVacationMon(double vacationMon) {
        this.vacationMon = vacationMon;
    }

    /**
     * @return the vacationTus
     */
    public double getVacationTus() {
        return vacationTus;
    }

    /**
     * @param vacationTus the vacationTus to set
     */
    public void setVacationTus(double vacationTus) {
        this.vacationTus = vacationTus;
    }

    /**
     * @return the vacationWed
     */
    public double getVacationWed() {
        return vacationWed;
    }

    /**
     * @param vacationWed the vacationWed to set
     */
    public void setVacationWed(double vacationWed) {
        this.vacationWed = vacationWed;
    }

    /**
     * @return the vacationThur
     */
    public double getVacationThur() {
        return vacationThur;
    }

    /**
     * @param vacationThur the vacationThur to set
     */
    public void setVacationThur(double vacationThur) {
        this.vacationThur = vacationThur;
    }

    /**
     * @return the vacationFri
     */
    public double getVacationFri() {
        return vacationFri;
    }

    /**
     * @param vacationFri the vacationFri to set
     */
    public void setVacationFri(double vacationFri) {
        this.vacationFri = vacationFri;
    }

    /**
     * @return the vacationSat
     */
    public double getVacationSat() {
        return vacationSat;
    }

    /**
     * @param vacationSat the vacationSat to set
     */
    public void setVacationSat(double vacationSat) {
        this.vacationSat = vacationSat;
    }

    /**
     * @return the vacationTotalHrs
     */
    public double getVacationTotalHrs() {
        return vacationTotalHrs;
    }

    /**
     * @param vacationTotalHrs the vacationTotalHrs to set
     */
    public void setVacationTotalHrs(double vacationTotalHrs) {
        this.vacationTotalHrs = vacationTotalHrs;
    }

    /**
     * @return the holiSun
     */
    public double getHoliSun() {
        return holiSun;
    }

    /**
     * @param holiSun the holiSun to set
     */
    public void setHoliSun(double holiSun) {
        this.holiSun = holiSun;
    }

    /**
     * @return the holiMon
     */
    public double getHoliMon() {
        return holiMon;
    }

    /**
     * @param holiMon the holiMon to set
     */
    public void setHoliMon(double holiMon) {
        this.holiMon = holiMon;
    }

    /**
     * @return the holiTus
     */
    public double getHoliTus() {
        return holiTus;
    }

    /**
     * @param holiTus the holiTus to set
     */
    public void setHoliTus(double holiTus) {
        this.holiTus = holiTus;
    }

    /**
     * @return the holiWed
     */
    public double getHoliWed() {
        return holiWed;
    }

    /**
     * @param holiWed the holiWed to set
     */
    public void setHoliWed(double holiWed) {
        this.holiWed = holiWed;
    }

    /**
     * @return the holiThur
     */
    public double getHoliThur() {
        return holiThur;
    }

    /**
     * @param holiThur the holiThur to set
     */
    public void setHoliThur(double holiThur) {
        this.holiThur = holiThur;
    }

    /**
     * @return the holiFri
     */
    public double getHoliFri() {
        return holiFri;
    }

    /**
     * @param holiFri the holiFri to set
     */
    public void setHoliFri(double holiFri) {
        this.holiFri = holiFri;
    }

    /**
     * @return the holiSat
     */
    public double getHoliSat() {
        return holiSat;
    }

    /**
     * @param holiSat the holiSat to set
     */
    public void setHoliSat(double holiSat) {
        this.holiSat = holiSat;
    }

    /**
     * @return the holiTotalHrs
     */
    public double getHoliTotalHrs() {
        return holiTotalHrs;
    }

    /**
     * @param holiTotalHrs the holiTotalHrs to set
     */
    public void setHoliTotalHrs(double holiTotalHrs) {
        this.holiTotalHrs = holiTotalHrs;
    }

    /**
     * @return the totalSun
     */
    public double getTotalSun() {
        return totalSun;
    }

    /**
     * @param totalSun the totalSun to set
     */
    public void setTotalSun(double totalSun) {
        this.totalSun = totalSun;
    }

    /**
     * @return the totalMon
     */
    public double getTotalMon() {
        return totalMon;
    }

    /**
     * @param totalMon the totalMon to set
     */
    public void setTotalMon(double totalMon) {
        this.totalMon = totalMon;
    }

    /**
     * @return the totalTus
     */
    public double getTotalTus() {
        return totalTus;
    }

    /**
     * @param totalTus the totalTus to set
     */
    public void setTotalTus(double totalTus) {
        this.totalTus = totalTus;
    }

    /**
     * @return the totalWed
     */
    public double getTotalWed() {
        return totalWed;
    }

    /**
     * @param totalWed the totalWed to set
     */
    public void setTotalWed(double totalWed) {
        this.totalWed = totalWed;
    }

    /**
     * @return the totalThur
     */
    public double getTotalThur() {
        return totalThur;
    }

    /**
     * @param totalThur the totalThur to set
     */
    public void setTotalThur(double totalThur) {
        this.totalThur = totalThur;
    }

    /**
     * @return the totalFri
     */
    public double getTotalFri() {
        return totalFri;
    }

    /**
     * @param totalFri the totalFri to set
     */
    public void setTotalFri(double totalFri) {
        this.totalFri = totalFri;
    }

    /**
     * @return the totalSat
     */
    public double getTotalSat() {
        return totalSat;
    }

    /**
     * @param totalSat the totalSat to set
     */
    public void setTotalSat(double totalSat) {
        this.totalSat = totalSat;
    }

    /**
     * @return the allWeekendTotalHors
     */
    public double getAllWeekendTotalHors() {
        return allWeekendTotalHors;
    }

    /**
     * @param allWeekendTotalHors the allWeekendTotalHors to set
     */
    public void setAllWeekendTotalHors(double allWeekendTotalHors) {
        this.allWeekendTotalHors = allWeekendTotalHors;
    }

    /**
     * @return the totalVacationHrs
     */
    public double getTotalVacationHrs() {
        return totalVacationHrs;
    }

    /**
     * @param totalVacationHrs the totalVacationHrs to set
     */
    public void setTotalVacationHrs(double totalVacationHrs) {
        this.totalVacationHrs = totalVacationHrs;
    }

    /**
     * @return the totalHoliHrs
     */
    public double getTotalHoliHrs() {
        return totalHoliHrs;
    }

    /**
     * @param totalHoliHrs the totalHoliHrs to set
     */
    public void setTotalHoliHrs(double totalHoliHrs) {
        this.totalHoliHrs = totalHoliHrs;
    }

    /**
     * @return the totalBillHrs
     */
    public double getTotalBillHrs() {
        return totalBillHrs;
    }

    /**
     * @param totalBillHrs the totalBillHrs to set
     */
    public void setTotalBillHrs(double totalBillHrs) {
        this.totalBillHrs = totalBillHrs;
    }

    /**
     * @return the txtNotes
     */
    public String getTxtNotes() {
        return txtNotes;
    }

    /**
     * @param txtNotes the txtNotes to set
     */
    public void setTxtNotes(String txtNotes) {
        this.txtNotes = txtNotes;
    }

    /**
     * @return the timeSheetID
     */
    public String getTimeSheetID() {
        return timeSheetID;
    }

    /**
     * @param timeSheetID the timeSheetID to set
     */
    public void setTimeSheetID(String timeSheetID) {
        this.timeSheetID = timeSheetID;
    }

    /**
     * @return the empID
     */
    public String getEmpID() {
        return empID;
    }

    /**
     * @param empID the empID to set
     */
    public void setEmpID(String empID) {
        this.empID = empID;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the sqlQuery
     */
    public String getSqlQuery() {
        return sqlQuery;
    }

    /**
     * @param sqlQuery the sqlQuery to set
     */
    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    /**
     * @return the userRoleId
     */
    public int getUserRoleId() {
        return userRoleId;
    }

    /**
     * @param userRoleId the userRoleId to set
     */
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * @return the empName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * @return the Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return the timeSheetStat
     */
    public String getTimeSheetStat() {
        return timeSheetStat;
    }

    /**
     * @param timeSheetStat the timeSheetStat to set
     */
    public void setTimeSheetStat(String timeSheetStat) {
        this.timeSheetStat = timeSheetStat;
    }

    /**
     * @return the statusValue
     */
    public String getStatusValue() {
        return statusValue;
    }

    /**
     * @param statusValue the statusValue to set
     */
    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the isOnsite
     */
    public String getIsOnsite() {
        return isOnsite;
    }

    /**
     * @param isOnsite the isOnsite to set
     */
    public void setIsOnsite(String isOnsite) {
        this.isOnsite = isOnsite;
    }

    /**
     * @return the MyTeamMembers
     */
    public Map getMyTeamMembers() {
        return MyTeamMembers;
    }

    /**
     * @param MyTeamMembers the MyTeamMembers to set
     */
    public void setMyTeamMembers(Map MyTeamMembers) {
        this.MyTeamMembers = MyTeamMembers;
    }

    /**
     * @return the attachmentService
     */
    public AttachmentService getAttachmentService() {
        return attachmentService;
    }

    /**
     * @param attachmentService the attachmentService to set
     */
    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    /**
     * @return the generatedPath
     */
    public String getGeneratedPath() {
        return generatedPath;
    }

    /**
     * @param generatedPath the generatedPath to set
     */
    public void setGeneratedPath(String generatedPath) {
        this.generatedPath = generatedPath;
    }

    /**
     * @return the upload
     */
    public File getUpload() {
        return upload;
    }

    /**
     * @param upload the upload to set
     */
    public void setUpload(File upload) {
        this.upload = upload;
    }

    /**
     * @return the uploadContentType
     */
    public String getUploadContentType() {
        return uploadContentType;
    }

    /**
     * @param uploadContentType the uploadContentType to set
     */
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     * @return the uploadFileName
     */
    public String getUploadFileName() {
        return uploadFileName;
    }

    /**
     * @param uploadFileName the uploadFileName to set
     */
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
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
     * @return the objectId
     */
    public int getObjectId() {
        return objectId;
    }

    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    /**
     * @return the objectType
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * @param objectType the objectType to set
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     * @return the fileLocation
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * @param fileLocation the fileLocation to set
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the attachmentName
     */
    public String getAttachmentName() {
        return attachmentName;
    }

    /**
     * @param attachmentName the attachmentName to set
     */
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    /**
     * @return the empLName
     */
    public String getEmpLName() {
        return empLName;
    }

    /**
     * @param empLName the empLName to set
     */
    public void setEmpLName(String empLName) {
        this.empLName = empLName;
    }

    /**
     * @return the empFName
     */
    public String getEmpFName() {
        return empFName;
    }

    /**
     * @param empFName the empFName to set
     */
    public void setEmpFName(String empFName) {
        this.empFName = empFName;
    }

    /**
     * @return the tempVar
     */
    public int getTempVar() {
        return tempVar;
    }

    /**
     * @param tempVar the tempVar to set
     */
    public void setTempVar(int tempVar) {
        this.tempVar = tempVar;
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
     * @return the queryStringBuffer
     */
    public StringBuffer getQueryStringBuffer() {
        return queryStringBuffer;
    }

    /**
     * @param queryStringBuffer the queryStringBuffer to set
     */
    public void setQueryStringBuffer(StringBuffer queryStringBuffer) {
        this.queryStringBuffer = queryStringBuffer;
    }

    /**
     * @return the associatedProjectsCount
     */
    public int getAssociatedProjectsCount() {
        return associatedProjectsCount;
    }

    /**
     * @param associatedProjectsCount the associatedProjectsCount to set
     */
    public void setAssociatedProjectsCount(int associatedProjectsCount) {
        this.associatedProjectsCount = associatedProjectsCount;
    }

    /**
     * @return the compSun
     */
    public double getCompSun() {
        return compSun;
    }

    /**
     * @param compSun the compSun to set
     */
    public void setCompSun(double compSun) {
        this.compSun = compSun;
    }

    /**
     * @return the compMon
     */
    public double getCompMon() {
        return compMon;
    }

    /**
     * @param compMon the compMon to set
     */
    public void setCompMon(double compMon) {
        this.compMon = compMon;
    }

    /**
     * @return the compTus
     */
    public double getCompTus() {
        return compTus;
    }

    /**
     * @param compTus the compTus to set
     */
    public void setCompTus(double compTus) {
        this.compTus = compTus;
    }

    /**
     * @return the compWed
     */
    public double getCompWed() {
        return compWed;
    }

    /**
     * @param compWed the compWed to set
     */
    public void setCompWed(double compWed) {
        this.compWed = compWed;
    }

    /**
     * @return the compThur
     */
    public double getCompThur() {
        return compThur;
    }

    /**
     * @param compThur the compThur to set
     */
    public void setCompThur(double compThur) {
        this.compThur = compThur;
    }

    /**
     * @return the compFri
     */
    public double getCompFri() {
        return compFri;
    }

    /**
     * @param compFri the compFri to set
     */
    public void setCompFri(double compFri) {
        this.compFri = compFri;
    }

    /**
     * @return the compSat
     */
    public double getCompSat() {
        return compSat;
    }

    /**
     * @param compSat the compSat to set
     */
    public void setCompSat(double compSat) {
        this.compSat = compSat;
    }

    /**
     * @return the compTotalHrs
     */
    public double getCompTotalHrs() {
        return compTotalHrs;
    }

    /**
     * @param compTotalHrs the compTotalHrs to set
     */
    public void setCompTotalHrs(double compTotalHrs) {
        this.compTotalHrs = compTotalHrs;
    }

    /**
     * @return the totalComptimeHrs
     */
    public double getTotalComptimeHrs() {
        return totalComptimeHrs;
    }

    /**
     * @param totalComptimeHrs the totalComptimeHrs to set
     */
    public void setTotalComptimeHrs(double totalComptimeHrs) {
        this.totalComptimeHrs = totalComptimeHrs;
    }

    /**
     * @return the proj3Sun
     */
    public double getProj3Sun() {
        return proj3Sun;
    }

    /**
     * @param proj3Sun the proj3Sun to set
     */
    public void setProj3Sun(double proj3Sun) {
        this.proj3Sun = proj3Sun;
    }

    /**
     * @return the proj3Mon
     */
    public double getProj3Mon() {
        return proj3Mon;
    }

    /**
     * @param proj3Mon the proj3Mon to set
     */
    public void setProj3Mon(double proj3Mon) {
        this.proj3Mon = proj3Mon;
    }

    /**
     * @return the proj3Tus
     */
    public double getProj3Tus() {
        return proj3Tus;
    }

    /**
     * @param proj3Tus the proj3Tus to set
     */
    public void setProj3Tus(double proj3Tus) {
        this.proj3Tus = proj3Tus;
    }

    /**
     * @return the proj3Wed
     */
    public double getProj3Wed() {
        return proj3Wed;
    }

    /**
     * @param proj3Wed the proj3Wed to set
     */
    public void setProj3Wed(double proj3Wed) {
        this.proj3Wed = proj3Wed;
    }

    /**
     * @return the proj3Thur
     */
    public double getProj3Thur() {
        return proj3Thur;
    }

    /**
     * @param proj3Thur the proj3Thur to set
     */
    public void setProj3Thur(double proj3Thur) {
        this.proj3Thur = proj3Thur;
    }

    /**
     * @return the proj3Fri
     */
    public double getProj3Fri() {
        return proj3Fri;
    }

    /**
     * @param proj3Fri the proj3Fri to set
     */
    public void setProj3Fri(double proj3Fri) {
        this.proj3Fri = proj3Fri;
    }

    /**
     * @return the proj3Sat
     */
    public double getProj3Sat() {
        return proj3Sat;
    }

    /**
     * @param proj3Sat the proj3Sat to set
     */
    public void setProj3Sat(double proj3Sat) {
        this.proj3Sat = proj3Sat;
    }

    /**
     * @return the proj3TotalHrs
     */
    public double getProj3TotalHrs() {
        return proj3TotalHrs;
    }

    /**
     * @param proj3TotalHrs the proj3TotalHrs to set
     */
    public void setProj3TotalHrs(double proj3TotalHrs) {
        this.proj3TotalHrs = proj3TotalHrs;
    }

    /**
     * @return the proj4Sun
     */
    public double getProj4Sun() {
        return proj4Sun;
    }

    /**
     * @param proj4Sun the proj4Sun to set
     */
    public void setProj4Sun(double proj4Sun) {
        this.proj4Sun = proj4Sun;
    }

    /**
     * @return the proj4Mon
     */
    public double getProj4Mon() {
        return proj4Mon;
    }

    /**
     * @param proj4Mon the proj4Mon to set
     */
    public void setProj4Mon(double proj4Mon) {
        this.proj4Mon = proj4Mon;
    }

    /**
     * @return the proj4Tus
     */
    public double getProj4Tus() {
        return proj4Tus;
    }

    /**
     * @param proj4Tus the proj4Tus to set
     */
    public void setProj4Tus(double proj4Tus) {
        this.proj4Tus = proj4Tus;
    }

    /**
     * @return the proj4Wed
     */
    public double getProj4Wed() {
        return proj4Wed;
    }

    /**
     * @param proj4Wed the proj4Wed to set
     */
    public void setProj4Wed(double proj4Wed) {
        this.proj4Wed = proj4Wed;
    }

    /**
     * @return the proj4Thur
     */
    public double getProj4Thur() {
        return proj4Thur;
    }

    /**
     * @param proj4Thur the proj4Thur to set
     */
    public void setProj4Thur(double proj4Thur) {
        this.proj4Thur = proj4Thur;
    }

    /**
     * @return the proj4Fri
     */
    public double getProj4Fri() {
        return proj4Fri;
    }

    /**
     * @param proj4Fri the proj4Fri to set
     */
    public void setProj4Fri(double proj4Fri) {
        this.proj4Fri = proj4Fri;
    }

    /**
     * @return the proj4Sat
     */
    public double getProj4Sat() {
        return proj4Sat;
    }

    /**
     * @param proj4Sat the proj4Sat to set
     */
    public void setProj4Sat(double proj4Sat) {
        this.proj4Sat = proj4Sat;
    }

    /**
     * @return the proj4TotalHrs
     */
    public double getProj4TotalHrs() {
        return proj4TotalHrs;
    }

    /**
     * @param proj4TotalHrs the proj4TotalHrs to set
     */
    public void setProj4TotalHrs(double proj4TotalHrs) {
        this.proj4TotalHrs = proj4TotalHrs;
    }

    /**
     * @return the proj5Sun
     */
    public double getProj5Sun() {
        return proj5Sun;
    }

    /**
     * @param proj5Sun the proj5Sun to set
     */
    public void setProj5Sun(double proj5Sun) {
        this.proj5Sun = proj5Sun;
    }

    /**
     * @return the proj5Mon
     */
    public double getProj5Mon() {
        return proj5Mon;
    }

    /**
     * @param proj5Mon the proj5Mon to set
     */
    public void setProj5Mon(double proj5Mon) {
        this.proj5Mon = proj5Mon;
    }

    /**
     * @return the proj5Tus
     */
    public double getProj5Tus() {
        return proj5Tus;
    }

    /**
     * @param proj5Tus the proj5Tus to set
     */
    public void setProj5Tus(double proj5Tus) {
        this.proj5Tus = proj5Tus;
    }

    /**
     * @return the proj5Wed
     */
    public double getProj5Wed() {
        return proj5Wed;
    }

    /**
     * @param proj5Wed the proj5Wed to set
     */
    public void setProj5Wed(double proj5Wed) {
        this.proj5Wed = proj5Wed;
    }

    /**
     * @return the proj5Thur
     */
    public double getProj5Thur() {
        return proj5Thur;
    }

    /**
     * @param proj5Thur the proj5Thur to set
     */
    public void setProj5Thur(double proj5Thur) {
        this.proj5Thur = proj5Thur;
    }

    /**
     * @return the proj5Fri
     */
    public double getProj5Fri() {
        return proj5Fri;
    }

    /**
     * @param proj5Fri the proj5Fri to set
     */
    public void setProj5Fri(double proj5Fri) {
        this.proj5Fri = proj5Fri;
    }

    /**
     * @return the proj5Sat
     */
    public double getProj5Sat() {
        return proj5Sat;
    }

    /**
     * @param proj5Sat the proj5Sat to set
     */
    public void setProj5Sat(double proj5Sat) {
        this.proj5Sat = proj5Sat;
    }

    /**
     * @return the proj5TotalHrs
     */
    public double getProj5TotalHrs() {
        return proj5TotalHrs;
    }

    /**
     * @param proj5TotalHrs the proj5TotalHrs to set
     */
    public void setProj5TotalHrs(double proj5TotalHrs) {
        this.proj5TotalHrs = proj5TotalHrs;
    }

    /**
     * @return the proj1Id
     */
    public int getProj1Id() {
        return proj1Id;
    }

    /**
     * @param proj1Id the proj1Id to set
     */
    public void setProj1Id(int proj1Id) {
        this.proj1Id = proj1Id;
    }

    /**
     * @return the proj2Id
     */
    public int getProj2Id() {
        return proj2Id;
    }

    /**
     * @param proj2Id the proj2Id to set
     */
    public void setProj2Id(int proj2Id) {
        this.proj2Id = proj2Id;
    }

    /**
     * @return the proj3Id
     */
    public int getProj3Id() {
        return proj3Id;
    }

    /**
     * @param proj3Id the proj3Id to set
     */
    public void setProj3Id(int proj3Id) {
        this.proj3Id = proj3Id;
    }

    /**
     * @return the proj4Id
     */
    public int getProj4Id() {
        return proj4Id;
    }

    /**
     * @param proj4Id the proj4Id to set
     */
    public void setProj4Id(int proj4Id) {
        this.proj4Id = proj4Id;
    }

    /**
     * @return the proj5Id
     */
    public int getProj5Id() {
        return proj5Id;
    }

    /**
     * @param proj5Id the proj5Id to set
     */
    public void setProj5Id(int proj5Id) {
        this.proj5Id = proj5Id;
    }

    /**
     * @return the iflag
     */
    public int getIflag() {
        return iflag;
    }

    /**
     * @param iflag the iflag to set
     */
    public void setIflag(int iflag) {
        this.iflag = iflag;
    }

    /**
     * @return the resourceType
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * @param resourceType the resourceType to set
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * @return the emptimesheetid
     */
    public int getEmptimesheetid() {
        return emptimesheetid;
    }

    /**
     * @param emptimesheetid the emptimesheetid to set
     */
    public void setEmptimesheetid(int emptimesheetid) {
        this.emptimesheetid = emptimesheetid;
    }

    /**
     * @return the employeeid
     */
    public int getEmployeeid() {
        return employeeid;
    }

    /**
     * @param employeeid the employeeid to set
     */
    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    /**
     * @return the empType
     */
    public String getEmpType() {
        return empType;
    }

    /**
     * @param empType the empType to set
     */
    public void setEmpType(String empType) {
        this.empType = empType;
    }

    /**
     * @return the empCusType
     */
    public String getEmpCusType() {
        return empCusType;
    }

    /**
     * @param empCusType the empCusType to set
     */
    public void setEmpCusType(String empCusType) {
        this.empCusType = empCusType;
    }

    /**
     * @return the empnamesList
     */
    public Map getEmpnamesList() {
        return empnamesList;
    }

    /**
     * @param empnamesList the empnamesList to set
     */
    public void setEmpnamesList(Map empnamesList) {
        this.empnamesList = empnamesList;
    }

    /**
     * @return the custnamesList
     */
    public Map getCustnamesList() {
        return custnamesList;
    }

    /**
     * @param custnamesList the custnamesList to set
     */
    public void setCustnamesList(Map custnamesList) {
        this.custnamesList = custnamesList;
    }

    /**
     * @return the custnameById
     */
    public String getCustnameById() {
        return custnameById;
    }

    /**
     * @param custnameById the custnameById to set
     */
    public void setCustnameById(String custnameById) {
        this.custnameById = custnameById;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the projectMap
     */
    public Map getProjectMap() {
        return projectMap;
    }

    /**
     * @param projectMap the projectMap to set
     */
    public void setProjectMap(Map projectMap) {
        this.projectMap = projectMap;
    }

    /**
     * @return the projectReportsToMap
     */
    public Map getProjectReportsToMap() {
        return projectReportsToMap;
    }

    /**
     * @param projectReportsToMap the projectReportsToMap to set
     */
    public void setProjectReportsToMap(Map projectReportsToMap) {
        this.projectReportsToMap = projectReportsToMap;
    }

    /**
     * @return the projectId
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the printEmpnamesList
     */
    public Map getPrintEmpnamesList() {
        return printEmpnamesList;
    }

    /**
     * @param printEmpnamesList the printEmpnamesList to set
     */
    public void setPrintEmpnamesList(Map printEmpnamesList) {
        this.printEmpnamesList = printEmpnamesList;
    }

    /**
     * @return the reportsEmpnamesList
     */
    public Map getReportsEmpnamesList() {
        return reportsEmpnamesList;
    }

    /**
     * @param reportsEmpnamesList the reportsEmpnamesList to set
     */
    public void setReportsEmpnamesList(Map reportsEmpnamesList) {
        this.reportsEmpnamesList = reportsEmpnamesList;
    }

    /**
     * @return the priProjId
     */
    public int getPriProjId() {
        return priProjId;
    }

    /**
     * @param priProjId the priProjId to set
     */
    public void setPriProjId(int priProjId) {
        this.priProjId = priProjId;
    }

    /**
     * @return the biometricSun
     */
    public double getBiometricSun() {
        return biometricSun;
    }

    /**
     * @param biometricSun the biometricSun to set
     */
    public void setBiometricSun(double biometricSun) {
        this.biometricSun = biometricSun;
    }

    /**
     * @return the bmSunStatus
     */
    public String getBmSunStatus() {
        return bmSunStatus;
    }

    /**
     * @param bmSunStatus the bmSunStatus to set
     */
    public void setBmSunStatus(String bmSunStatus) {
        this.bmSunStatus = bmSunStatus;
    }

    /**
     * @return the biometricMon
     */
    public double getBiometricMon() {
        return biometricMon;
    }

    /**
     * @param biometricMon the biometricMon to set
     */
    public void setBiometricMon(double biometricMon) {
        this.biometricMon = biometricMon;
    }

    /**
     * @return the bmMonStatus
     */
    public String getBmMonStatus() {
        return bmMonStatus;
    }

    /**
     * @param bmMonStatus the bmMonStatus to set
     */
    public void setBmMonStatus(String bmMonStatus) {
        this.bmMonStatus = bmMonStatus;
    }

    /**
     * @return the biometricTus
     */
    public double getBiometricTus() {
        return biometricTus;
    }

    /**
     * @param biometricTus the biometricTus to set
     */
    public void setBiometricTus(double biometricTus) {
        this.biometricTus = biometricTus;
    }

    /**
     * @return the bmTusStatus
     */
    public String getBmTusStatus() {
        return bmTusStatus;
    }

    /**
     * @param bmTusStatus the bmTusStatus to set
     */
    public void setBmTusStatus(String bmTusStatus) {
        this.bmTusStatus = bmTusStatus;
    }

    /**
     * @return the biometricWed
     */
    public double getBiometricWed() {
        return biometricWed;
    }

    /**
     * @param biometricWed the biometricWed to set
     */
    public void setBiometricWed(double biometricWed) {
        this.biometricWed = biometricWed;
    }

    /**
     * @return the bmWedStatus
     */
    public String getBmWedStatus() {
        return bmWedStatus;
    }

    /**
     * @param bmWedStatus the bmWedStatus to set
     */
    public void setBmWedStatus(String bmWedStatus) {
        this.bmWedStatus = bmWedStatus;
    }

    /**
     * @return the biometricThur
     */
    public double getBiometricThur() {
        return biometricThur;
    }

    /**
     * @param biometricThur the biometricThur to set
     */
    public void setBiometricThur(double biometricThur) {
        this.biometricThur = biometricThur;
    }

    /**
     * @return the bmThurStatus
     */
    public String getBmThurStatus() {
        return bmThurStatus;
    }

    /**
     * @param bmThurStatus the bmThurStatus to set
     */
    public void setBmThurStatus(String bmThurStatus) {
        this.bmThurStatus = bmThurStatus;
    }

    /**
     * @return the biometricFri
     */
    public double getBiometricFri() {
        return biometricFri;
    }

    /**
     * @param biometricFri the biometricFri to set
     */
    public void setBiometricFri(double biometricFri) {
        this.biometricFri = biometricFri;
    }

    /**
     * @return the bmFriStatus
     */
    public String getBmFriStatus() {
        return bmFriStatus;
    }

    /**
     * @param bmFriStatus the bmFriStatus to set
     */
    public void setBmFriStatus(String bmFriStatus) {
        this.bmFriStatus = bmFriStatus;
    }

    /**
     * @return the biometricSat
     */
    public double getBiometricSat() {
        return biometricSat;
    }

    /**
     * @param biometricSat the biometricSat to set
     */
    public void setBiometricSat(double biometricSat) {
        this.biometricSat = biometricSat;
    }

    /**
     * @return the bmSatStatus
     */
    public String getBmSatStatus() {
        return bmSatStatus;
    }

    /**
     * @param bmSatStatus the bmSatStatus to set
     */
    public void setBmSatStatus(String bmSatStatus) {
        this.bmSatStatus = bmSatStatus;
    }

    /**
     * @return the biometricTotalHrs
     */
    public double getBiometricTotalHrs() {
        return biometricTotalHrs;
    }

    /**
     * @param biometricTotalHrs the biometricTotalHrs to set
     */
    public void setBiometricTotalHrs(double biometricTotalHrs) {
        this.biometricTotalHrs = biometricTotalHrs;
    }

    /**
     * @return the totalBiometricHrs
     */
    public double getTotalBiometricHrs() {
        return totalBiometricHrs;
    }

    /**
     * @param totalBiometricHrs the totalBiometricHrs to set
     */
    public void setTotalBiometricHrs(double totalBiometricHrs) {
        this.totalBiometricHrs = totalBiometricHrs;
    }

    /**
     * @return the secCustnamesList
     */
    public Map getSecCustnamesList() {
        return secCustnamesList;
    }

    /**
     * @param secCustnamesList the secCustnamesList to set
     */
    public void setSecCustnamesList(Map secCustnamesList) {
        this.secCustnamesList = secCustnamesList;
    }

    /**
     * @return the teamType
     */
    public int getTeamType() {
        return teamType;
    }

    /**
     * @param teamType the teamType to set
     */
    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    /**
     * @return the isDualReportingRequired
     */
    public boolean getIsDualReportingRequired() {
        return isDualReportingRequired;
    }

    /**
     * @param isDualReportingRequired the isDualReportingRequired to set
     */
    public void setIsDualReportingRequired(boolean isDualReportingRequired) {
        this.isDualReportingRequired = isDualReportingRequired;
    }

    /**
     * @return the secStatusValue
     */
    public String getSecStatusValue() {
        return secStatusValue;
    }

    /**
     * @param secStatusValue the secStatusValue to set
     */
    public void setSecStatusValue(String secStatusValue) {
        this.secStatusValue = secStatusValue;
    }

    /**
     * @return the timeSheetSecStat
     */
    public String getTimeSheetSecStat() {
        return timeSheetSecStat;
    }

    /**
     * @param timeSheetSecStat the timeSheetSecStat to set
     */
    public void setTimeSheetSecStat(String timeSheetSecStat) {
        this.timeSheetSecStat = timeSheetSecStat;
    }

    /**
     * @return the priReportsToId
     */
    public int getPriReportsToId() {
        return priReportsToId;
    }

    /**
     * @param priReportsToId the priReportsToId to set
     */
    public void setPriReportsToId(int priReportsToId) {
        this.priReportsToId = priReportsToId;
    }

    /**
     * @return the secReportsToId
     */
    public int getSecReportsToId() {
        return secReportsToId;
    }

    /**
     * @param secReportsToId the secReportsToId to set
     */
    public void setSecReportsToId(int secReportsToId) {
        this.secReportsToId = secReportsToId;
    }

    /**
     * @return the secDescription
     */
    public String getSecDescription() {
        return secDescription;
    }

    /**
     * @param secDescription the secDescription to set
     */
    public void setSecDescription(String secDescription) {
        this.secDescription = secDescription;
    }

    /**
     * @return the employeeLocation
     */
    public String getEmployeeLocation() {
        return employeeLocation;
    }

    /**
     * @param employeeLocation the employeeLocation to set
     */
    public void setEmployeeLocation(String employeeLocation) {
        this.employeeLocation = employeeLocation;
    }

    /**
     * @return the fileFlagValue
     */
    public int getFileFlagValue() {
        return fileFlagValue;
    }

    /**
     * @param fileFlagValue the fileFlagValue to set
     */
    public void setFileFlagValue(int fileFlagValue) {
        this.fileFlagValue = fileFlagValue;
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
     * @return the operationContactMapByCountry
     */
    public Map getOperationContactMapByCountry() {
        return operationContactMapByCountry;
    }

    /**
     * @param operationContactMapByCountry the operationContactMapByCountry to set
     */
    public void setOperationContactMapByCountry(Map operationContactMapByCountry) {
        this.operationContactMapByCountry = operationContactMapByCountry;
    }

    /**
     * @return the locationsMapByLivingCountry
     */
    public Map getLocationsMapByLivingCountry() {
        return locationsMapByLivingCountry;
    }

    /**
     * @param locationsMapByLivingCountry the locationsMapByLivingCountry to set
     */
    public void setLocationsMapByLivingCountry(Map locationsMapByLivingCountry) {
        this.locationsMapByLivingCountry = locationsMapByLivingCountry;
    }

    /**
     * @return the opsContactId
     */
    public int getOpsContactId() {
        return opsContactId;
    }

    /**
     * @param opsContactId the opsContactId to set
     */
    public void setOpsContactId(int opsContactId) {
        this.opsContactId = opsContactId;
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
     * @return the leaveDates
     */
    public String getLeaveDates() {
        return leaveDates;
    }

    /**
     * @param leaveDates the leaveDates to set
     */
    public void setLeaveDates(String leaveDates) {
        this.leaveDates = leaveDates;
    }
    
    
    
    
    
    
    
}
