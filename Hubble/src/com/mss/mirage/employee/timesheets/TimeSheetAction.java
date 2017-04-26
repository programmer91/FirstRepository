/*******************************************************************************
 * /*
 *Project: MirajeV2
 *
 *$Author: rdadi $
 *
 *$Date: 2011-03-01 09:47:21 $
 *
 *$Revision: 1.2 $
 *
 *$Source: /Hubble/Hubble/src/java/com/mss/mirage/employee/timesheets/TimeSheetAction.java,v $
 *
 * @(#)TimeSheetAction.java	September 24, 2007, 12:13 AM
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
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
import java.util.StringTokenizer;
import org.apache.struts2.interceptor.ServletResponseAware;


/**
 * <p> This class TimeSheetAction can be used for control all CURD operation of Timesheet. <p>
 *
 * @version 2.0, September 24, 2007.
 *
 * @author  RangaRao Panda<rpanda@miraclesoft.com>
 *
 * @See com.mss.mirage.util.ServiceLocator
 */
public class TimeSheetAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
    
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
    private Date previousWeek;
    
    /**
     * for stroing the all values of the timesheet ,
     * the object can be passed to jsp page to display the date.
     *
     *{@Link com.mss.mirage.employee.timesheets.TimeSheetVTO}
     */
    private TimeSheetVTO timeSheetVTO;
    
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
    
    private int emptimesheetid;
    
    private int employeeid;
    
    
    private Map empnamesList;
    
    private DataSourceDataProvider dataSourceDataProvider;
    
    private String empnameById;
    private String empnameById1;
    //new
    
     private String sheetType;
     
     private String empType;
     private String description;
     // For generating report
     private Map projectReportsToMap;
     
     private Map custnamesList;
     private String custnameById;
     private String empCusType;
     private String resourceType;
     
     
 //new varible map by prasad k
     private Map myProjects;
     private int projectId;
     
     
     // Madhavi mam changes 07/31/2014
       private Map projectMap;
     private String projectName;
     private String customerName;
     private int customerId;
     private Map printEmpnamesList;
     private String printStartDate;
     private String printEndDate;
     private Map reportsEmpnamesList;
     private String timesheetAction;
     private int val;
     
//  ===================================================================================
//  This method is used to loop through all the keys present in a Map Data Structure
//  and formulate them into a String of Keys seperated by a delimiter and return that
//  string of delimited values back to the caller
//=====================================================================================
    /**
     *
     * @param map
     * @param delimiter
     * @return String
     */
    private String getKeys(Map map, String delimiter) {
        Iterator tempIterator = map.entrySet().iterator();
        String keys = "";
        String key="";
        int cnt = 0;
        
        while(tempIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) tempIterator.next();
            key = entry.getKey().toString();
            entry = null;
            
            //  Add the Delimiter to the Keys Field for the Second Key onwards
            if (cnt > 0) keys = keys + delimiter;
            
            keys = keys + "'" + key +"'";
            cnt++;
        }
        tempIterator = null;
        return(keys);
    }
    
    /**
     *
     * @return Date
     */
    public Date getPreviousWeek() {
        return previousWeek;
    }
    
    /**
     *
     * @param previousWeek
     */
    public void setPreviousWeek(Date previousWeek) {
        this.previousWeek = previousWeek;
    }
    
    /**
     *
     * @return Date
     */
    public String getWstDate() {
        return wstDate;
    }
    
    /**
     *
     * @param wstDate
     */
    public void setWstDate(String wstDate) {
        this.wstDate = wstDate;
    }
    
    /**
     *
     * @return String
     */
    public String getWenDate() {
        return wenDate;
    }
    
    /**
     *
     * @param wenDate
     */
    public void setWenDate(String wenDate) {
        this.wenDate = wenDate;
    }
    
    /**
     *
     * @return String
     */
    public String getSubmittedDate() {
        return submittedDate;
    }
    
    /**
     *
     * @param submittedDate
     */
    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }
    
    /**
     *
     * @return String
     */
    public String getApproveDate() {
        return approveDate;
    }
    
    /**
     *
     * @param approveDate
     */
    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }
    
    /**
     *
     * @return String
     */
    public String getCheckDate() {
        return checkDate;
    }
    
    /**
     *
     * @param checkDate
     */
    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }
    
    /**
     *
     * @return String
     */
    public String getWeekDate1() {
        return weekDate1;
    }
    
    /**
     *
     * @param weekDate1
     */
    public void setWeekDate1(String weekDate1) {
        this.weekDate1 = weekDate1;
    }
    
    /**
     *
     * @return String
     */
    public String getWeekDate2() {
        return weekDate2;
    }
    
    /**
     *
     * @param weekDate2
     */
    public void setWeekDate2(String weekDate2) {
        this.weekDate2 = weekDate2;
    }
    
    /**
     *
     * @return String
     */
    public String getWeekDate3() {
        return weekDate3;
    }
    
    /**
     *
     * @param weekDate3
     */
    public void setWeekDate3(String weekDate3) {
        this.weekDate3 = weekDate3;
    }
    
    /**
     *
     * @return String
     */
    public String getWeekDate4() {
        return weekDate4;
    }
    
    /**
     *
     * @param weekDate4
     */
    public void setWeekDate4(String weekDate4) {
        this.weekDate4 = weekDate4;
    }
    
    /**
     *
     * @return String
     */
    public String getWeekDate5() {
        return weekDate5;
    }
    
    /**
     *
     * @param weekDate5
     */
    public void setWeekDate5(String weekDate5) {
        this.weekDate5 = weekDate5;
    }
    
    /**
     *
     * @return String
     */
    public String getWeekDate6() {
        return weekDate6;
    }
    
    /**
     *
     * @param weekDate6
     */
    public void setWeekDate6(String weekDate6) {
        this.weekDate6 = weekDate6;
    }
    
    /**
     *
     * @return String
     */
    public String getWeekDate7() {
        return weekDate7;
    }
    
    /**
     *
     * @param weekDate7
     */
    public void setWeekDate7(String weekDate7) {
        this.weekDate7 = weekDate7;
    }
    
    /**
     *
     * @return double
     */
    public double getProj1Sun() {
        return proj1Sun;
    }
    
    /**
     *
     * @param proj1Sun
     */
    public void setProj1Sun(double proj1Sun) {
        this.proj1Sun = proj1Sun;
    }
    
    /**
     *
     * @return double
     */
    public double getProj1Mon() {
        return proj1Mon;
    }
    
    /**
     *
     * @param proj1Mon
     */
    public void setProj1Mon(double proj1Mon) {
        this.proj1Mon = proj1Mon;
    }
    
    /**
     *
     * @return double
     */
    public double getProj1Tus() {
        return proj1Tus;
    }
    
    /**
     *
     * @param proj1Tus
     */
    public void setProj1Tus(double proj1Tus) {
        this.proj1Tus = proj1Tus;
    }
    
    /**
     *
     * @return double
     */
    public double getProj1Wed() {
        return proj1Wed;
    }
    
    /**
     *
     * @param proj1Wed
     */
    public void setProj1Wed(double proj1Wed) {
        this.proj1Wed = proj1Wed;
    }
    
    /**
     *
     * @return double
     */
    public double getProj1Thur() {
        return proj1Thur;
    }
    
    /**
     *
     * @param proj1Thur
     */
    public void setProj1Thur(double proj1Thur) {
        this.proj1Thur = proj1Thur;
    }
    
    /**
     *
     * @return double
     */
    public double getProj1Fri() {
        return proj1Fri;
    }
    
    /**
     *
     * @param proj1Fri
     */
    public void setProj1Fri(double proj1Fri) {
        this.proj1Fri = proj1Fri;
    }
    
    /**
     *
     * @return double
     */
    public double getProj1Sat() {
        return proj1Sat;
    }
    
    /**
     *
     * @param proj1Sat
     */
    public void setProj1Sat(double proj1Sat) {
        this.proj1Sat = proj1Sat;
    }
    
    /**
     *
     * @return double
     */
    public double getProj1TotalHrs() {
        return proj1TotalHrs;
    }
    
    /**
     *
     * @param proj1TotalHrs
     */
    public void setProj1TotalHrs(double proj1TotalHrs) {
        this.proj1TotalHrs = proj1TotalHrs;
    }
    
    /**
     *
     * @return double
     */
    public double getProj2Sun() {
        return proj2Sun;
    }
    
    /**
     *
     * @param proj2Sun
     */
    public void setProj2Sun(double proj2Sun) {
        this.proj2Sun = proj2Sun;
    }
    
    /**
     *
     * @return double
     */
    public double getProj2Mon() {
        return proj2Mon;
    }
    
    /**
     *
     * @param proj2Mon
     */
    public void setProj2Mon(double proj2Mon) {
        this.proj2Mon = proj2Mon;
    }
    
    /**
     *
     * @return double
     */
    public double getProj2Tus() {
        return proj2Tus;
    }
    
    /**
     *
     * @param proj2Tus
     */
    public void setProj2Tus(double proj2Tus) {
        this.proj2Tus = proj2Tus;
    }
    
    /**
     *
     * @return double
     */
    public double getProj2Wed() {
        return proj2Wed;
    }
    
    /**
     *
     * @param proj2Wed
     */
    public void setProj2Wed(double proj2Wed) {
        this.proj2Wed = proj2Wed;
    }
    
    /**
     *
     * @return double
     */
    public double getProj2Thur() {
        return proj2Thur;
    }
    
    /**
     *
     * @param proj2Thur
     */
    public void setProj2Thur(double proj2Thur) {
        this.proj2Thur = proj2Thur;
    }
    
    /**
     *
     * @return double
     */
    public double getProj2Fri() {
        return proj2Fri;
    }
    
    /**
     *
     * @param proj2Fri
     */
    public void setProj2Fri(double proj2Fri) {
        this.proj2Fri = proj2Fri;
    }
    
    /**
     *
     * @return double
     */
    public double getProj2Sat() {
        return proj2Sat;
    }
    
    /**
     *
     * @param proj2Sat
     */
    public void setProj2Sat(double proj2Sat) {
        this.proj2Sat = proj2Sat;
    }
    
    /**
     *
     * @return double
     */
    public double getProj2TotalHrs() {
        return proj2TotalHrs;
    }
    
    /**
     *
     * @param proj2TotalHrs
     */
    public void setProj2TotalHrs(double proj2TotalHrs) {
        this.proj2TotalHrs = proj2TotalHrs;
    }
    
    /**
     *
     * @return double
     */
    public double getInternalSun() {
        return internalSun;
    }
    
    /**
     *
     * @param internalSun
     */
    public void setInternalSun(double internalSun) {
        this.internalSun = internalSun;
    }
    
    /**
     *
     * @return double
     */
    public double getInternalMon() {
        return internalMon;
    }
    
    /**
     *
     * @param internalMon
     */
    public void setInternalMon(double internalMon) {
        this.internalMon = internalMon;
    }
    
    /**
     *
     * @return double
     */
    public double getInternalTus() {
        return internalTus;
    }
    
    /**
     *
     * @param internalTus
     */
    public void setInternalTus(double internalTus) {
        this.internalTus = internalTus;
    }
    
    /**
     *
     * @return double
     */
    public double getInternalWed() {
        return internalWed;
    }
    
    /**
     *
     * @param internalWed
     */
    public void setInternalWed(double internalWed) {
        this.internalWed = internalWed;
    }
    
    /**
     *
     * @return double
     */
    public double getInternalThur() {
        return internalThur;
    }
    
    /**
     *
     * @param internalThur
     */
    public void setInternalThur(double internalThur) {
        this.internalThur = internalThur;
    }
    
    /**
     *
     * @return double
     */
    public double getInternalFri() {
        return internalFri;
    }
    
    /**
     *
     * @param internalFri
     */
    public void setInternalFri(double internalFri) {
        this.internalFri = internalFri;
    }
    
    /**
     *
     * @return double
     */
    public double getInternalSat() {
        return internalSat;
    }
    
    /**
     *
     * @param internalSat
     */
    public void setInternalSat(double internalSat) {
        this.internalSat = internalSat;
    }
    
    /**
     *
     * @return double
     */
    public double getInternalTotalHrs() {
        return internalTotalHrs;
    }
    
    /**
     *
     * @param internalTotalHrs
     */
    public void setInternalTotalHrs(double internalTotalHrs) {
        this.internalTotalHrs = internalTotalHrs;
    }
    
    /**
     *
     * @return double
     */
    public double getVacationSun() {
        return vacationSun;
    }
    
    /**
     *
     * @param vacationSun
     */
    public void setVacationSun(double vacationSun) {
        this.vacationSun = vacationSun;
    }
    
    /**
     *
     * @return double
     */
    public double getVacationMon() {
        return vacationMon;
    }
    
    /**
     *
     * @param vacationMon
     */
    public void setVacationMon(double vacationMon) {
        this.vacationMon = vacationMon;
    }
    
    /**
     *
     * @return double
     */
    public double getVacationTus() {
        return vacationTus;
    }
    
    /**
     *
     * @param vacationTus
     */
    public void setVacationTus(double vacationTus) {
        this.vacationTus = vacationTus;
    }
    
    /**
     *
     * @return double
     */
    public double getVacationWed() {
        return vacationWed;
    }
    
    /**
     *
     * @param vacationWed
     */
    public void setVacationWed(double vacationWed) {
        this.vacationWed = vacationWed;
    }
    
    /**
     *
     * @return double
     */
    public double getVacationThur() {
        return vacationThur;
    }
    
    /**
     *
     * @param vacationThur
     */
    public void setVacationThur(double vacationThur) {
        this.vacationThur = vacationThur;
    }
    
    /**
     *
     * @return double
     */
    public double getVacationFri() {
        return vacationFri;
    }
    
    /**
     *
     * @param vacationFri
     */
    public void setVacationFri(double vacationFri) {
        this.vacationFri = vacationFri;
    }
    
    /**
     *
     * @return double
     */
    public double getVacationSat() {
        return vacationSat;
    }
    
    /**
     *
     * @param vacationSat
     */
    public void setVacationSat(double vacationSat) {
        this.vacationSat = vacationSat;
    }
    
    /**
     *
     * @return double
     */
    public double getVacationTotalHrs() {
        return vacationTotalHrs;
    }
    
    /**
     *
     * @param vacationTotalHrs
     */
    public void setVacationTotalHrs(double vacationTotalHrs) {
        this.vacationTotalHrs = vacationTotalHrs;
    }
    
    /**
     *
     * @return double
     */
    public double getHoliSun() {
        return holiSun;
    }
    
    /**
     *
     * @param holiSun
     */
    public void setHoliSun(double holiSun) {
        this.holiSun = holiSun;
    }
    
    /**
     *
     * @return double
     */
    public double getHoliMon() {
        return holiMon;
    }
    
    /**
     *
     * @param holiMon
     */
    public void setHoliMon(double holiMon) {
        this.holiMon = holiMon;
    }
    
    /**
     *
     * @return double
     */
    public double getHoliTus() {
        return holiTus;
    }
    
    /**
     *
     * @param holiTus
     */
    public void setHoliTus(double holiTus) {
        this.holiTus = holiTus;
    }
    
    /**
     *
     * @return double
     */
    public double getHoliWed() {
        return holiWed;
    }
    
    /**
     *
     * @param holiWed
     */
    public void setHoliWed(double holiWed) {
        this.holiWed = holiWed;
    }
    
    /**
     *
     * @return double
     */
    public double getHoliThur() {
        return holiThur;
    }
    
    /**
     *
     * @param holiThur
     */
    public void setHoliThur(double holiThur) {
        this.holiThur = holiThur;
    }
    
    /**
     *
     * @return double
     */
    public double getHoliFri() {
        return holiFri;
    }
    
    /**
     *
     * @param holiFri
     */
    public void setHoliFri(double holiFri) {
        this.holiFri = holiFri;
    }
    
    /**
     *
     * @return double
     */
    public double getHoliSat() {
        return holiSat;
    }
    
    /**
     *
     * @param holiSat
     */
    public void setHoliSat(double holiSat) {
        this.holiSat = holiSat;
    }
    
    /**
     *
     * @return double
     */
    public double getHoliTotalHrs() {
        return holiTotalHrs;
    }
    
    /**
     *
     * @param holiTotalHrs
     */
    public void setHoliTotalHrs(double holiTotalHrs) {
        this.holiTotalHrs = holiTotalHrs;
    }
    
    /**
     *
     * @return double
     */
    public double getTotalSun() {
        return totalSun;
    }
    
    /**
     *
     * @param totalSun
     */
    public void setTotalSun(double totalSun) {
        this.totalSun = totalSun;
    }
    
    /**
     *
     * @return double
     */
    public double getTotalMon() {
        return totalMon;
    }
    
    /**
     *
     * @param totalMon
     */
    public void setTotalMon(double totalMon) {
        this.totalMon = totalMon;
    }
    
    /**
     *
     * @return double
     */
    public double getTotalTus() {
        return totalTus;
    }
    
    /**
     *
     * @param totalTus
     */
    public void setTotalTus(double totalTus) {
        this.totalTus = totalTus;
    }
    
    /**
     *
     * @return double
     */
    public double getTotalWed() {
        return totalWed;
    }
    
    /**
     *
     * @param totalWed
     */
    public void setTotalWed(double totalWed) {
        this.totalWed = totalWed;
    }
    
    /**
     *
     * @return double
     */
    public double getTotalThur() {
        return totalThur;
    }
    
    /**
     *
     * @param totalThur
     */
    public void setTotalThur(double totalThur) {
        this.totalThur = totalThur;
    }
    
    /**
     *
     * @return double
     */
    public double getTotalFri() {
        return totalFri;
    }
    
    /**
     *
     * @param totalFri
     */
    public void setTotalFri(double totalFri) {
        this.totalFri = totalFri;
    }
    
    /**
     *
     * @return double
     */
    public double getTotalSat() {
        return totalSat;
    }
    
    /**
     *
     * @param totalSat
     */
    public void setTotalSat(double totalSat) {
        this.totalSat = totalSat;
    }
    
    /**
     *
     * @return double
     */
    public double getAllWeekendTotalHors() {
        return allWeekendTotalHors;
    }
    
    /**
     *
     * @param allWeekendTotalHors
     */
    public void setAllWeekendTotalHors(double allWeekendTotalHors) {
        this.allWeekendTotalHors = allWeekendTotalHors;
    }
    
    /**
     *
     * @return double
     */
    public double getTotalVacationHrs() {
        return totalVacationHrs;
    }
    
    /**
     *
     * @param totalVacationHrs
     */
    public void setTotalVacationHrs(double totalVacationHrs) {
        this.totalVacationHrs = totalVacationHrs;
    }
    
    /**
     *
     * @return double
     */
    public double getTotalHoliHrs() {
        return totalHoliHrs;
    }
    
    /**
     *
     * @param totalHoliHrs
     */
    public void setTotalHoliHrs(double totalHoliHrs) {
        this.totalHoliHrs = totalHoliHrs;
    }
    
    /**
     *
     * @return double
     */
    public double getTotalBillHrs() {
        return totalBillHrs;
    }
    
    /**
     *
     * @param totalBillHrs
     */
    public void setTotalBillHrs(double totalBillHrs) {
        this.totalBillHrs = totalBillHrs;
    }
    
    /**
     *
     * @return String
     */
    public String getTxtNotes() {
        return txtNotes;
    }
    
    /**
     *
     * @param txtNotes
     */
    public void setTxtNotes(String txtNotes) {
        this.txtNotes = txtNotes;
    }
    
    /**
     *
     * @return String
     */
    public String getTimeSheetID() {
        return timeSheetID;
    }
    
    /**
     *
     * @param timeSheetID
     */
    public void setTimeSheetID(String timeSheetID) {
        this.timeSheetID = timeSheetID;
    }
    
    /**
     *
     * @return String
     */
    public String getEmpID() {
        return empID;
    }
    
    /**
     *
     * @param empID
     */
    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpCusType() {
        return empCusType;
    }

    public void setEmpCusType(String empCusType) {
        this.empCusType = empCusType;
    }
    
    
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
    public String myTimesheet(){
        
        
      //  System.out.println("in project team");
        
        
        resultType = LOGIN;
        if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            String empType = "";
             String resourceId="";
                String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
              
             
            // SESSION_EMPTYPE from session 
            
           // empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            
      //      System.out.println("emptype---->"+empType);
            
            try {
              
               // String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                connection = ConnectionProvider.getInstance().getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT EmployeeTypeId from tblEmployee WHERE loginId='"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID)+"'");
                resultSet.next();
                String empTye = resultSet.getString("EmployeeTypeId");
                if(empTye.equals("Onsite")) {
                    setIsOnsite("Onsite");
                }
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
               //change 
                /*    if(empType1.equalsIgnoreCase("c") || empType1.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                 
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                
                }
                 setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));   
                if(empType1.equalsIgnoreCase("c") || empType1.equalsIgnoreCase("v")){
                    String objectId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                            
                 boolean flag = DataSourceDataProvider.getInstance().checkAssociatedProject(objectId);
          //       System.out.println(flag);
                 if(!flag){
                     httpServletRequest.getSession(false).setAttribute("errorMessage","You are not associated to any project please contact concerned team.");
                     resultType = "error";
                 }else{
                     resultType = SUCCESS;
                 }
                    
                }else{
                
                resultType = SUCCESS;
                }*/  //change end
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
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                   resultType = SUCCESS;
                }
                 setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));   
               
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
    public String fillMyTimesheet()throws ServiceLocatorException {
     //   System.out.println("in fillMyTimesheet");
        resultType = LOGIN;
        
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
             
             String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
             userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("FILL_TIMESHEET",userRoleId)){
                /* geeting the employee id from the session .*/
                //change
                /*
                 if(empType.equalsIgnoreCase("e")){
                 setEmpID(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString());
                }else{
                 setEmpID(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString());
               
                }*/ //change end
                
                resultType = INPUT;
                 String resourceId="";
             //   String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if(empType.equalsIgnoreCase("c") || empType.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                 setEmpID(resourceId);
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                 setEmpID(resourceId);
                }
                setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));   
                /* for checking Timesheet exists */
                String isTimeSheetExist = "";
                if(httpServletRequest.getParameter("checkDate")!=null){
                    if(getPreviousWeek()!=null && !getPreviousWeek().toString().trim().equals("") && httpServletRequest.getParameter("checkDate").equals("PreviousWeek")) {
                        
                        String stortingDate=new java.text.SimpleDateFormat("MM/dd/yyyy").format(getPreviousWeek());
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
                        List previousWeekDaysList = ServiceLocator.getTimeSheetService().getweekStartAndEndDays(previouseCalender);
                        
                        /**
                         * To check wethere the timesheet exists or not
                         * {@Link com.mss.mirage.employee.timesheets.TimeSheetService#checkTimeSheetExists(List,String)}
                         */
                        //isTimeSheetExist  = ServiceLocator.getTimeSheetService().checkTimeSheetExists(previousWeekDaysList,getEmpID());
                        isTimeSheetExist  = ServiceLocator.getTimeSheetService().checkTimeSheetExists(previousWeekDaysList,getEmpID(),empType);
                        
                        
                        if(isTimeSheetExist.equals("exist")) {
                            resultMessage = "<font color=\"red\" size=\"1.5\">The TimeSheet already Filled for  Week Starting Date :"+previousWeekDaysList.get(0).toString()+" And EndDate :"+previousWeekDaysList.get(1).toString()+"</font>";
                        }else if(isTimeSheetExist.equals("notallow")) {
                            resultMessage = "<font color=\"red\" size=\"1.5\">The TimeSheet is not allowed for Week Starting Date :"+previousWeekDaysList.get(0).toString()+" And EndDate :"+previousWeekDaysList.get(1).toString()+"</font>";
                        } else {
                            /**
                             *{@Link com.mss.mirage.employee.timesheets.TimeSheetService#getWeekDaysBean(List)}
                             */
                            TimeSheetVTO timeSheetVTO = ServiceLocator.getTimeSheetService().getWeekDaysBean(previousWeekDaysList);
                            setTimeSheetVTO(timeSheetVTO);
                            resultType = SUCCESS;
                        } // else
                        
                    } else if(httpServletRequest.getParameter("checkDate").equals("ThisWeek")) {
                        Calendar currentCalender = Calendar.getInstance();
                  /**
                         * For generating the weekdays
                         *{@Link com.mss.mirage.employee.timesheets.TimeSheetService#getweekStartAndEndDays(cal)}
                         */
                        List currentWeekDaysList = ServiceLocator.getTimeSheetService().getweekStartAndEndDays(currentCalender);
                        
                        /**
                         * To check wethere the timesheet exists or not
                         * {@Link com.mss.mirage.employee.timesheets.TimeSheetService#checkTimeSheetExists(List,String)}
                         */
                        isTimeSheetExist = ServiceLocator.getTimeSheetService().checkTimeSheetExists(currentWeekDaysList,getEmpID(),empType);
                        if(isTimeSheetExist.equals("exist")) {
                            resultMessage = "<font color=\"red\" size=\"1.5\">The TimeSheet already Filled for  Week Starting Date :"+currentWeekDaysList.get(0).toString()+" And EndDate : "+currentWeekDaysList.get(1).toString()+"</font>";
                        }else if(isTimeSheetExist.equals("exist")) {
                            resultMessage = "<font color=\"red\" size=\"1.5\">The TimeSheet is not allowed for  Week Starting Date :"+currentWeekDaysList.get(0).toString()+" And EndDate : "+currentWeekDaysList.get(1).toString()+"</font>";
                        } else {
                            /**
                             *{@Link com.mss.mirage.employee.timesheets.TimeSheetService#getWeekDaysBean(List)}
                             */
                            TimeSheetVTO timeSheetVTO = ServiceLocator.getTimeSheetService().getWeekDaysBean(currentWeekDaysList);
                            setTimeSheetVTO(timeSheetVTO);
                            resultType = SUCCESS;
                        } // if
                    } // if
                } // if
                
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    } //  @ execute
    
    /**
     * This method can be used to add the new timesheet
     *
     *Modified By Ajay Tummapala
     * @return resultType a String.
     *
     * {@Link com.mss.mirage.employee.timesheets.TimeSheetService#addTimeSheet(TimeSheetAction)}
     */
    public String doAdd() {
        resultType = LOGIN;
       // System.out.println("in do add project id-->"+getProjectId());
       // if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_ADD_TIMESHEET",userRoleId)){
                try{
                    int isTimeSheetAdd = ServiceLocator.getTimeSheetService().addTimeSheet(this);
                    
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
                setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                         
                        resultMessage="<font color=\"green\" size=\"1.5\">The TimeSheet Successfully Added for WeekStartDate: "+getWstDate()+" And WeekEndDate:"+getWenDate()+"</font>";
                    }
                    else
                        resultMessage="<font color=\"red\" size=\"1.5\">Error occour Adding for WeekStartDate: "+getWstDate()+" And WeekEndDate:"+getWenDate()+"</font>";
                    
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    } // @ doAdd
    /**
     * This method is invoke when the user click the edid image on grid
     * and get the date from the datebase ,populate it on jsp page
     *
     * @return SUCCESS a String.
     *
     * @throws Exception.
     */
    public String prepare() throws Exception {
        resultType = LOGIN;
        //System.out.println("in prepare");
       // if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            //System.out.println("after session  prepare");
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            String resourceId="";
                //String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if(empType.equalsIgnoreCase("c") || empType.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                
                }
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_TIMESHEET",userRoleId)){
              //  System.out.println("after authorization  prepare");
                try{
                    /**
                     * for setting the Timesheet object
                     * {@Link com.mss.mirage.employee.timesheets.TimeSheetService#getTimeSheet(int,int)}
                     */
                    if(getEmpID() == null || "".equals(getEmpID())) {
                    String employeeID = httpServletRequest.getParameter("employeeID").toString();
                    setEmpID(employeeID);
                    
                }
                  if(getVal()==1)
                  {
                  httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_CUST_DESIG,"OR");
                  }
                setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                    if(getTimeSheetID() == null || "".equals(getTimeSheetID())) {
                        String employeeTimesheetId = httpServletRequest.getParameter("emptimeSheetID").toString();
                    setTimeSheetID(employeeTimesheetId);
                    }
                    if(getResourceType()==null){
                        setResourceType(dataSourceDataProvider.getInstance().getTimeSheetResourceTypeByEmpId(Integer.parseInt(getEmpID()),Integer.parseInt(getTimeSheetID())));
                    }
                    setTimeSheetVTO(ServiceLocator.getTimeSheetService().getTimeSheet(Integer.parseInt(getEmpID()),Integer.parseInt(getTimeSheetID()),empType,getResourceType()));
                   // System.out.println("after session setTimeSheetVTO prepare");
                    //Invoking getTimeSheetStatus() method to get the status of the TimeSheet.
                    
                    statusValue = getTimeSheetStatus(empType);
                  //  System.out.println("--- prepare-->"+statusValue);
                    if(statusValue.equals("Entered")) {
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
        //Close Session Checking
       
       //if(httpServletRequest.getParameter("emptimeSheetID") != null && httpServletRequest.getParameter("type")!=null){
         if(httpServletRequest.getParameter("emptimeSheetID") != null ){
           //System.out.println("in emptimeSheetID");
            String employeeID = httpServletRequest.getParameter("employeeID");
            String emptimeSheetID = httpServletRequest.getParameter("emptimeSheetID");
            httpServletRequest.setAttribute("employeeID",employeeID);
            httpServletRequest.setAttribute("emptimeSheetID",emptimeSheetID);
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

    
  
    public String doEdit(){
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
               // String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if(eType.equalsIgnoreCase("c") || eType.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                }
              //  System.out.println("resourceId --> "+resourceId);
               // System.out.println("empId --> "+getEmpID());
                setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                
                    boolean isTimeSheetEdit = ServiceLocator.getTimeSheetService().editTimeSheet(this);
                    if(getTempVar() == 2) {
                       // System.out.println("in emp Name");
                       // System.out.println("empName---->"+getEmpName());
                        //change
                    /*    if(eType.equalsIgnoreCase("E")){
                            //System.out.println("---------empName--->"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                            setResourceType("e");
                            setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                        }else{
                            setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                           // System.out.println("---custName----"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                        }*/
                        //change end
                  //  System.out.println("projectId in Action "+getProjectId());
                         String reportsTo = "";
                          /*  if(eType.equalsIgnoreCase("e")){
                            reportsTo = DataSourceDataProvider.getInstance().reportsTo(getEmpID());

                            }else{
                                String resourceType= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_RESOURCETYPE).toString();
                              int reportsToId= DataSourceDataProvider.getInstance().getReportsToIdByContactId(Integer.parseInt(getEmpID()),getProjectId()); 
                              String reportsToType=DataSourceDataProvider.getInstance().getReportsToTypeByReportsToId(reportsToId,getProjectId()); 
                            reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(reportsToId,reportsToType);

                            } */
                         String reportsToType = "";
                          if(empType.equalsIgnoreCase("e")){
                 setResourceType("e");
                 setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString());
                 if(getResourceType().equalsIgnoreCase("e")){
                     if(projectId!=0){
                         //String checkPMO="";
                         //String pmoReportsTO="";
                         //String finalResult = dataSourceDataProvider.getInstance().checkForIsPMO(Integer.parseInt(resourceId),projectId);
                         //StringTokenizer st = new StringTokenizer(finalResult, "^");
                         //checkPMO = st.nextToken();
                         //pmoReportsTO= st.nextToken();
                         //if(checkPMO.equals("1")&&pmoReportsTO.equals("-1"))
                        // {
                        // reportsTo = DataSourceDataProvider.getInstance().reportsTo(getEmpID())+"@miraclesoft.com";
                         //}
                         //else{
                               int reportsToId= DataSourceDataProvider.getInstance().getReportsToIdByContactId(Integer.parseInt(getEmpID()),projectId); 
                               reportsToType=DataSourceDataProvider.getInstance().getReportsToTypeByReportsToId(reportsToId,projectId); 
                               reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(reportsToId,reportsToType);
                           //  }
                     }
                     else{
                reportsTo = DataSourceDataProvider.getInstance().reportsTo(getEmpID())+"@miraclesoft.com";
                     }
                }else{
                     int reportsToId= DataSourceDataProvider.getInstance().getReportsToIdByContactId(Integer.parseInt(getEmpID()),projectId); 
                               reportsToType=DataSourceDataProvider.getInstance().getReportsToTypeByReportsToId(reportsToId,projectId); 
                            reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(reportsToId,reportsToType);
                    //reportsTo = DataSourceDataProvider.getInstance().getContactOfficeMail(Integer.parseInt(EmpId));
                }
            }else{
                 setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                 String resourceType= httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_RESOURCETYPE).toString();
               // reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(Integer.parseInt(EmpId),resourceType);
                  int reportsToId= DataSourceDataProvider.getInstance().getReportsToIdByContactId(Integer.parseInt(getEmpID()),projectId); 
                               reportsToType=DataSourceDataProvider.getInstance().getReportsToTypeByReportsToId(reportsToId,projectId); 
                            reportsTo = DataSourceDataProvider.getInstance().getReportsEmailIdByContactId(reportsToId,reportsToType);
            }
                           // System.out.println("reportsTo---->"+reportsTo);
                        if(reportsTo == null)
                    reportsTo = "";
                      if(Properties.getProperty("Mail.Flag").equals("1")&&!"".equals(reportsTo)) {
                                    MailManager mail = new MailManager();
                                    mail.sendReminders(getEmpName(),getTimeSheetID(),getEmpID(),eType,httpServletRequest,getResourceType(),getProjectId());
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
    
    /**
     *
     * This method is used to search the Timesheets
     * @return String
     */
    public String doSearch() {
        
        resultType = LOGIN;
       // if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
           // String loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
            resultType = "accessFailed";
            String queryString = "";
            String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            if(AuthorizationManager.getInstance().isAuthorizedUser("DO_SEARCH_TIMESHEET",userRoleId)){
                try{
                    String resourceId="";
                    if(empType.equalsIgnoreCase("E")){
                        queryString = "SELECT * from vwTimeSheetList";
                         resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                    }else{
                        queryString = "SELECT * from vwTimeSheetList";
                        resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                    }
                    StringBuffer   strSQL=new  StringBuffer(queryString);
                    
                    if(!getStartDate().equals("") && getEndDate().equals("")) {
                        strSQL= strSQL.append(" WHERE  DateStart='"+DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"'");
                        setSqlQuery(strSQL.toString());
                    } else
                        if(getStartDate().equals("") && !getEndDate().equals("")) {
                        strSQL= strSQL.append(" WHERE DateEnd='"+DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"'");
                        setSqlQuery(strSQL.toString());
                        
                        } else
                            if(! getStartDate().equals("") && !getEndDate().equals("") )   {
                        strSQL= strSQL.append(" WHERE SubmittedDate between '"+DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"' AND '"+DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"'");
                        setSqlQuery(strSQL.toString());
                        
                            } else{
                        //  if(getStartDate().equals("") && getEndDate().trim().equals(""))   {
                        strSQL= strSQL.append(" WHERE EmpId=115");
                        setSqlQuery(strSQL.toString());
                        
                            }
                    //change
                    /*
                    String resourceId="";
                String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if(empType1.equalsIgnoreCase("c")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                
                }*/
                setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                    httpServletRequest.setAttribute("strSQL",strSQL.toString());
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        
        return resultType;
    } // @ doSearch
    
    /**
     * The deleteTimeSheet() is used for deleting purticular TimeSheet .
     *
     * @return String variable for navigation.
     */
    public String deleteTimeSheet() {
        resultType = LOGIN;
       // System.out.println("in delete  action");
        //if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
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
                setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                    //System.out.println("in action");
                    int deletedRows = ServiceLocator.getTimeSheetService().deleteTimeSheet(Integer.parseInt(getId()),Integer.parseInt(getEmpID()),Integer.parseInt(getTimeSheetID()));
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
    
    /**
     * Author:Hari Krishna Kondala
     * For Manager Role
     * The getTimeSheetStatus() is to get the Status of Team TimeSheet.
     * @return String variable for navigation.
     * @throws Exception
     
    public String getTimeSheetStatus(String empType) throws SQLException {
        String status = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         Statement statement1 = null;
        ResultSet resultSet1 = null;
         Connection connection1 = null;
        String queryString = "";
        try {
            System.out.println("empType--->"+empType);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            if(empType.equalsIgnoreCase("e")){
                if(!(getResourceType()!=null)){
                    queryString = "select Description from vwTimeSheetList where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'";
                }
                else if(getResourceType().equalsIgnoreCase("e")){
                queryString = "select Description from vwTimeSheetList where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'";
                }else{
                    
                queryString = "select Description from vwCustTimeSheetList where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'";              
                }
            }else{
               queryString = "select Description from vwCustTimeSheetList where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'";
            }
            System.out.println("queryString--->"+queryString);
            resultSet = statement.executeQuery(queryString);
            resultSet.next();
            status = resultSet.getString("Description");
                System.out.println("status--->"+status);
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
        return status;
    }*/
     public String getTimeSheetStatus(String empType) throws SQLException {
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
        queryString ="SELECT Description FROM vwTimeSheetList WHERE EmpId = '"+getEmpID()+"' AND TimeSheetId = '"+getTimeSheetID()+"'";
            }else
            {
               queryString ="SELECT Description FROM vwCustTimeSheetList WHERE EmpId = '"+getEmpID()+"' AND TimeSheetId = '"+getTimeSheetID()+"'";  
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
    
    
    /**
     * Author:Hari Krishna Kondala
     * For Manager Role
     * The teamTimeSheet() is used for display Team TimeSheets .
     *
     * @return String variable for navigation.
     */
    public String teamTimeSheet() {
        resultType = LOGIN;
        String list = "";
        String queryString = "";
        String loginId = "";
       // System.out.println("team tiomesheets");
        
       // if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            String empType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            
          try{
               dataSourceDataProvider = dataSourceDataProvider.getInstance();
              String resourceId="";
              //  String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
          //change
             /* if(empType.equalsIgnoreCase("c") || empType.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                 
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                
                }*/ //change end
             
                setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
           //change     setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId))); change end
           dataSourceDataProvider = dataSourceDataProvider.getInstance();
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
                     if(roleId.equalsIgnoreCase("8") )  {
                          httpServletRequest.getSession(false).setAttribute(ApplicationConstants.SESSION_CUST_DESIG,"");
               setEmpnamesList(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
             }}
             
            // setMyProjects(DataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
               setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
               setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
              
              // setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
                //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
        //change     
        /*   if(empType.equalsIgnoreCase("E")){
                  // String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();   
                   
                   loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           }else{
              
               loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
           
           } */ //change end
           
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
            /*
            try {
                if(empType.equalsIgnoreCase("E")){
                     list=ServiceLocator.getTimeSheetService().getTeamMembersList(loginId);
                }else{
                  // list = " SELECT empId FROM tblProjectTeam LEFT OUTER JOIN tblCrmContact ON (tblProjectTeam.empId=tblCrmContact.Id) WHERE ReportsTo='"+loginId+"'"; 
                    
                    list = loginId;
                }               
                
            } catch (ServiceLocatorException ex) {
                list="";
            }*/ //change end
            //For Single Level
           //System.out.println(getEmployeeid()+"In Team timeSheets.");
           queryStringBuffer = new StringBuffer();
           
           
            if(getSubmitFrom()==null){
                
                if(empType.equalsIgnoreCase("E")){
                    if(getEmpCusType() == null){
                        queryString = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) AND ProjectId=0";
                    }
                  else  if(getEmpCusType().equalsIgnoreCase("Internal")){
                    
                    queryString = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) AND ProjectId=0";
                    }
                    else
                    {
                        String empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                   // queryString = "SELECT * FROM vwCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where ReportsTo in ("+empId+") AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") AND ProjectTeamId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+")) AND ProjectId!=0 AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") AND ProjectTeamId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+")";
                         queryString = "SELECT * FROM vwCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where ReportsTo in ("+empId+") AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") ) AND ProjectId!=0 AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") ";
                    }
                }else{
                 //  queryString = "SELECT * FROM vwCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where ReportsTo in ("+list+") AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") ) AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") ";
                     queryString = "SELECT * FROM vwCustTimeSheetList WHERE EmpId IN ( SELECT ObjectId FROM tblProjectContacts WHERE ReportsTo IN("+list+") AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") ) AND ProjectId IN (SELECT ProjectId FROM tblProjectContacts WHERE ReportsTo IN("+list+") AND ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+")) ";
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
    
    /**
     * Author:Hari Krishna Kondala
     * For Operation Role
     * The empTimeSheets() is used for display Employees TimeSheets .
     *
     * @return String variable for navigation.
     */
    public String empTimeSheets() throws Exception {
        resultType = LOGIN;
         String loginId = "";
      //  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
        // if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
          if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null){
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            dataSourceDataProvider = dataSourceDataProvider.getInstance();
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
                   dataSourceDataProvider = dataSourceDataProvider.getInstance();
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
                         queryStringBuffer.append("SELECT * FROM vwCustTimeSheetList where Description not in ('Approved') AND  ProjectId IN ("+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ASSOCIATED_PROJECTSIDS).toString()+") ");
                    //}
                    if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        queryStringBuffer.append(" AND date(DateStart) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        queryStringBuffer.append(" AND date(DateEnd) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
                    String empIds ="";
                    if(getEmpnameById()!=null && !"".equals(getEmpnameById()))   {
                        if("All".equals(getEmpnameById())){
                           // queryStringBuffer.append(" AND (EmployeeId like '%')");
                           //  queryStringBuffer.append(" AND (EmpId like '%')");
                            //change
                              /*if(empType.equalsIgnoreCase("E")){
                                queryStringBuffer.append(" AND (LoginId like '%')");
                            }else{*/
                              //  queryStringBuffer.append(" AND (EmpId like '%')");
                                  empIds = DataSourceDataProvider.getInstance().getCustomerList((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS));
                                 // System.out.println("empIds in all--->"+empIds);
                               // queryStringBuffer.append(" AND (EmpId like '%')");
                                   queryStringBuffer.append(" AND EmpId IN ("+empIds+")");
                            //}
                        }
                        else{
                      //  queryStringBuffer.append(" AND (EmployeeId like '"+getEmpnameById()+"')");
                          //  queryStringBuffer.append(" AND (EmpId like '"+getEmpnameById()+"')");
                            //change
                            /*if(empType.equalsIgnoreCase("E")){
                               // queryStringBuffer.append(" AND (LoginId like '"+getEmpnameById()+"')");
                                   queryStringBuffer.append(" AND (EmpId like '"+getEmpnameById()+"')");
                            }else{ */
                                queryStringBuffer.append(" AND (EmpId like '"+getEmpnameById()+"')");
                           // }
                        }
                        
                    }else if(getEmpnameById() == null || "".equals(getEmpnameById())) {
                        //change
                        //   if(!empType.equalsIgnoreCase("E")){
                               empIds = DataSourceDataProvider.getInstance().getCustomerList((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS));
                              // String empIds = DataSourceDataProvider.getInstance().getCustomerList((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS));
                               //System.out.println("empIds--->"+empIds);
                               queryStringBuffer.append(" AND EmpId IN ("+empIds+") ");
                         //  } 
                        }
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

 /**
 * Author:Hari Krishna Kondala
 * Updated By : Ajay Tummapala
 * For Manager Role
 * The approveTimeSheet() is used to approve TimeSheet.
 * Updated By : Ajay Tummapala
 * @return String variable for navigation.
 */
    
    
/*
     
      public String approveTimeSheet() {
        resultType = LOGIN;
        int updatedRows = 0;
        String timeSheetQuery = "";
        String list;
        
        String loginId = "";
        String eType = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            eType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
            
            if(AuthorizationManager.getInstance().isAuthorizedUser("APPROVE_OR_REJECT_TIMESHEET",userRoleId)){
                try {
                    dataSourceDataProvider = dataSourceDataProvider.getInstance();
                    String resourceId="";
                String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                if(empType1.equalsIgnoreCase("c") || empType1.equalsIgnoreCase("v")){
                 resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();              
                }
                else{
                resourceId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();    
                
                }
                setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                    connection = ConnectionProvider.getInstance().getConnection();
                    preparedStatement = connection.prepareStatement("UPDATE tblTimeSheets set TimeSheetStatusTypeId=3,Comments=null,ApprovedDate='"+DateUtility.getInstance().getMysqlDate(getApproveDate().trim())+"' where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'");
                    updatedRows = preparedStatement.executeUpdate();
                    if(updatedRows == 1) {
                        
                        if(eType.equalsIgnoreCase("E")){
                          //  setResourceType("e");
                         loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                         list=ServiceLocator.getTimeSheetService().getTeamMembersList(loginId);
                         String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                         setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                         timeSheetQuery = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) AND Description='Submitted'";
                           setEmpName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Integer.parseInt(getEmpID())));
                           String  empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(empId)));
                        }else{
                            loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                            list=loginId;
                            setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                            timeSheetQuery = "SELECT * FROM vwCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where ReportsTo in ("+list+")) AND Description='Submitted'";
                         // setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                             setEmpName(DataSourceDataProvider.getInstance().getCustomerNameById(Integer.parseInt(getEmpID())));
                        }
                        
                       
                        //For Two Level
                        //String timeSheetQuery = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo IN ("+getKeys(getMyTeamMembers(),",")+")) AND  Description='Submitted' ";
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,timeSheetQuery);
                        //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
                        //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
                        setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                        if(Properties.getProperty("Mail.Flag").equals("1")) { 
                        MailManager mail = new MailManager();
                        mail.sendReminders(getEmpName(),getTimeSheetID(),getEmpID(),eType,httpServletRequest,getResourceType(),getProjectId());
                        }
                        if(eType.equalsIgnoreCase("e")){
                            setResourceType("e");
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
    
 public String approveTimeSheet() {
        resultType = LOGIN;
        int updatedRows = 0;
        String timeSheetQuery = "";
        String list;
        
        String loginId = "";
        String eType = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
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
                    dataSourceDataProvider = dataSourceDataProvider.getInstance();
                    
             //   String empType1 = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
         
                setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                    connection = ConnectionProvider.getInstance().getConnection();
                    preparedStatement = connection.prepareStatement("UPDATE tblTimeSheets set TimeSheetStatusTypeId=3,Comments=null,ApprovedDate='"+DateUtility.getInstance().getMysqlDate(getApproveDate().trim())+"' where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'");
                    updatedRows = preparedStatement.executeUpdate();
                    if(updatedRows == 1) {
                        
                        if(eType.equalsIgnoreCase("E")){
                            //change
                         setResourceType("e");
                         loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                         list=ServiceLocator.getTimeSheetService().getTeamMembersList(loginId);
                         String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                        //setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                         setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                         timeSheetQuery = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) AND Description='Submitted'";
                           setEmpName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Integer.parseInt(getEmpID())));
                           String  empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                           setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(empId)));
                           setProjectMap(DataSourceDataProvider.getInstance().getProjectsMapByEmpId(Integer.parseInt(resourceId)));
                        }else{
                         //   setResourceType("c");
                            loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                            list=loginId;
                            setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                            timeSheetQuery = "SELECT * FROM vwCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where ReportsTo in ("+list+")) AND Description='Submitted'";
                         // setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                            setEmpName(DataSourceDataProvider.getInstance().getCustomerNameById(Integer.parseInt(getEmpID())));
                        }
                        
                      
                        //For Two Level
                        //String timeSheetQuery = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo IN ("+getKeys(getMyTeamMembers(),",")+")) AND  Description='Submitted' ";
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,timeSheetQuery);
                        //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
                        //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
                        setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                        setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                        /*if(eType.equalsIgnoreCase("e")){
                            setResourceType("e");
                        }*/
                         //change
                        if(eType.equalsIgnoreCase("c"))
                         {
                         setResourceType("c");
                         }
                         else if(eType.equalsIgnoreCase("v"))
                         {
                         setResourceType("v");
                         }
                        if(Properties.getProperty("Mail.Flag").equals("1")) { 
                        MailManager mail = new MailManager();
                        mail.sendReminders(getEmpName(),getTimeSheetID(),getEmpID(),eType,httpServletRequest,getResourceType(),getProjectId());
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

/**
 * Author:Hari Krishna Kondala
 * For Manager Role
 * The rejectTimeSheet() is used to reject TimeSheet.
 *
 * @return String variable for navigation.
 */
public String rejectTimeSheet() {
    resultType = LOGIN;
    int updatedRows = 0;
     String list;
        
        String loginId = "";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
     String timeSheetQuery = "";
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
                dataSourceDataProvider = dataSourceDataProvider.getInstance();
                connection = ConnectionProvider.getInstance().getConnection();
                preparedStatement = connection.prepareStatement("UPDATE tblTimeSheets set TimeSheetStatusTypeId=10,Comments='"+getComment()+"' where EmpId='"+getEmpID()+"' AND TimeSheetId='"+getTimeSheetID()+"'");
                updatedRows = preparedStatement.executeUpdate();
                //setEmpName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Integer.parseInt(getEmpID())));
                setMyProjects(dataSourceDataProvider.getInstance().getMyProjectList(Integer.parseInt(resourceId)));
                if(empType1.equalsIgnoreCase("E")){
                    setResourceType("e");
                    loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
                         list=ServiceLocator.getTimeSheetService().getTeamMembersList(loginId);
                             setEmpName(DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(Integer.parseInt(getEmpID())));
                             String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                             //setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                             setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                             String  empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                             setCustnamesList(dataSourceDataProvider.getCustomerTeamMap(Integer.parseInt(empId)));
                             timeSheetQuery = "SELECT * FROM vwTimeSheetList where EmpId IN ( SELECT Id FROM tblEmployee where ReportsTo in ("+list+")) AND Description='Submitted'";
                             setProjectMap(DataSourceDataProvider.getInstance().getProjectsMapByEmpId(Integer.parseInt(resourceId)));
                }else{
                    // setResourceType("c");
                           // setEmpName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString());
                    loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID).toString();
                            list=loginId;
                            timeSheetQuery = "SELECT * FROM vwCustTimeSheetList where EmpId IN ( SELECT ObjectId FROM tblProjectContacts where ReportsTo in ("+list+")) AND Description='Submitted'";
                    setEmpName(DataSourceDataProvider.getInstance().getCustomerNameById(Integer.parseInt(getEmpID())));
                            setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
               }
                //change
               /* if(empType1.equalsIgnoreCase("e")){
                            setResourceType("e");
                        }*/
                  if(empType1.equalsIgnoreCase("c")){
                            setResourceType("c");
                        }
                  else if(empType1.equalsIgnoreCase("v")){
                            setResourceType("v");
                        }
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_MY_TEAMTIMESHEETS_LIST,timeSheetQuery);
                setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                if(Properties.getProperty("Mail.Flag").equals("1")) {
                MailManager mail = new MailManager();
                mail.sendReminders(getEmpName(),getTimeSheetID(),getEmpID(),empType1,httpServletRequest,getResourceType(),getProjectId());
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
}

/**
 * Author:Hari Krishna Kondala
 * For Operation Role
 * The sendRemainders() is used to send Reminders to employee as well as Team Manager.
 * @throws Exception
 * @return String variable for navigation.
 */

/*
 * Modified Date : 07/31/2014
 * Reason : Need to redirect Emp operations timesheet search(new jsp)
 */
/*
public String sendReminders() throws ServiceLocatorException {
    resultType = LOGIN;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null)||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
        userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
        resultType = "accessFailed";
        String eType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
        
        
        String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
         if(eType.equalsIgnoreCase("E")){
        setEmpnamesList(DataSourceDataProvider.getInstance().getEmployeeNamesByID(workingCountry));
         String  empId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                setCustnamesList(dataSourceDataProvider.getInstance().getCustomerTeamMap(Integer.parseInt(empId)));
                //new
                
                
         }else {
             setEmpnamesList(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
         }
          
        if(AuthorizationManager.getInstance().isAuthorizedUser("SEND_REMINDERS",userRoleId)){
            try {
                 if(Properties.getProperty("Mail.Flag").equals("1")) {
                    MailManager mail = new MailManager();
                    
                    mail.sendReminders(getEmpName(),getTimeSheetID(),getEmpID(),eType,httpServletRequest,getResourceType(),getProjectId());
                 }
                // setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
                  //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
                 setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                resultMessage = "<font color=\"green\" size=\"1.5\">Reminder has been sent successfully!</font>";
                resultType = SUCCESS;
            }catch(Exception ex) {
                ex.printStackTrace();
                resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                resultType = INPUT;
            }
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
        }
    }
    return resultType;
}
*/




public String sendReminders() throws ServiceLocatorException {
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
                empTimeSheetSearch(); 
                
         }else {
             setEmpnamesList(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
         } 
             /*     if(eType.equalsIgnoreCase("E")){
                      empTimeSheetSearch(); 
                  } */
                 if(Properties.getProperty("Mail.Flag").equals("1")) {
                    MailManager mail = new MailManager();
                    
                    mail.sendReminders(getEmpName(),getTimeSheetID(),getEmpID(),eType,httpServletRequest,getResourceType(),getProjectId());
                 }
               
                 setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
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



/**
 * Author:Hari Krishna Kondala
 * For HR Role
 * The onSiteTimeSheetSearch() is used to Search Onsite and Offshore Employees TimeSheets.
 *
 * @return String variable for navigation.
 */
public String onSiteTimeSheetSearch() {
    resultType = LOGIN;
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
        if(httpServletRequest.getSession(false).getAttribute("sDate") != null) {
            httpServletRequest.getSession(false).setAttribute("sDate",null);
        }
        if(httpServletRequest.getSession(false).getAttribute("eDate") != null) {
            httpServletRequest.getSession(false).setAttribute("eDate",null);
        }
        if(httpServletRequest.getSession(false).getAttribute("isFirstOnsite") != null) {
            httpServletRequest.getSession(false).setAttribute("isFirstOnsite","yes");
        }
        if(httpServletRequest.getSession(false).getAttribute("isFirstOffshore") != null) {
            httpServletRequest.getSession(false).setAttribute("isFirstOffshore","yes");
        }
        resultType = SUCCESS;
    }
    return resultType;
}

/**
 * Author:Hari Krishna Kondala
 * For Employee(Onsite) Role
 * The doUploadTimeSheet() is used to Upload TimeSheet Document.
 *
 * @return String variable for navigation.
 */
public String doUploadTimeSheet() {
    resultType = LOGIN;
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        try {
            attachmentService = ServiceLocator.getAttachmentService();
            
            generatedPath = null;
            setCreatedBy(httpServletRequest.getSession(false).getAttribute("userId").toString());
            setObjectId(Integer.parseInt((String)httpServletRequest.getSession(false).getAttribute("empId")));
            setObjectType("Emp TimeSheets");
            if(getUploadFileName()!= null) {
                generatedPath = attachmentService.generatePath(Properties.getProperty("Attachments.Path"), "TimeSheets");
                File targetDirectory = new File(generatedPath + "/" + getUploadFileName());
                setFileLocation(targetDirectory.toString());
                FileUtils.copyFile(getUpload(), targetDirectory);
            }else {
                this.fileLocation = "";
                this.filePath = "";
                this.attachmentName = "";
            }
            myTimesheet();
            if(ServiceLocator.getTimeSheetService().uploadTimeSheet(this) > 0) {
                resultMessage = "<font color=\"green\" size=\"1.5\">TimeSheet has been uploaded successfully!</font>";
                
                resultType = SUCCESS;
            } else {
                resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                resultType = INPUT;
            }
            httpServletRequest.setAttribute("resultMessage", resultMessage);
            
        }catch (Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
    }
    return resultType;
}

/**
 *
 * This method is used to delete the Onsite Timesheet
 * @return String
 */
public String deleteOnsiteTimeSheet() {
    resultType = LOGIN;
    
    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
        resultType = "accessFailed";
        if(AuthorizationManager.getInstance().isAuthorizedUser("DELETE_TIMESHEET",userRoleId)){
            try{
                int deletedRows = ServiceLocator.getTimeSheetService().deleteOnsiteTimeSheet(Integer.parseInt(getId()));
                
                if(deletedRows == 1){
                    
                    resultType = SUCCESS;
                    resultMessage = "<font color=\"green\" size=\"1.5\">TimeSheetDocument has been successfully Deleted!</font>";
                }else{
                    resultType = INPUT;
                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                }
                myTimesheet();
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            }catch (Exception ex){
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }//END-Authorization Checking
    }//Close Session Checking
    return resultType;
}

/*Modified Date : 07/31/2014
 * Reason : Existed Query not correct
 * 
 */
public String getEmpTimeSheetsReport(){
        //prepare();
        String query ="";
        
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            try {
                // StringBuffer   strSQL=new  StringBuffer("SELECT EmpName,Description,DepartmentId,DateStart,DateEnd,TotalHrs,SubmittedDate,ApprovedDate FROM vwTimeSheetList");
                empName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
                TimeSheetService timeSheetService = ServiceLocator.getTimeSheetService();
                // System.out.println("DateStart"+DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(getStartDate())));
                //  strSQL.append(" WHERE curstatus='Active' AND  date(SubmittedDate) between date('"+DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"')" +
                //        " AND date('"+DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"" +
                //      "')");// ORDER BY DateStart";
                // System.out.println("getEmpName"+getEmpName());
                //  System.out.println("getEmpLName"+getEmpLName());
                //System.out.println("StartDt-->"+getStartDate());
                //System.out.println("EndDt-->"+getEndDate());
                java.util.Date sdate = DateUtility.getInstance().convertStringToMySql(getPrintStartDate());
                java.util.Date edate = DateUtility.getInstance().convertStringToMySql(getPrintEndDate());
               // System.out.println("sdate-->"+sdate);
                 String StartDt=DateUtility.getInstance().convertDateToMySql1(sdate);
               //  System.out.println("StartDt-->"+StartDt);
                String EndDt=DateUtility.getInstance().convertDateToMySql1(edate);
              //  System.out.println("EndDt-->"+EndDt);
               // String StartDt=DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()));
                //String EndDt=DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()));
               // System.out.println("getEmpnameById1-->"+getEmpnameById1());
              /*  StringBuffer   strSQL=new  StringBuffer(" SELECT  tblTimeSheets.EmpId,CONCAT(TRIM(FName),' ',TRIM(MName)," +
                        "' ',TRIM(LName)) AS EmpName,tblEmployee.DepartmentId,tblTimeSheets.DateStart,tblTimeSheets.DateEnd," +
                        "tblLKTimeSheetStatusType.Description  AS STATUS,tblTimeSheets.TotalHrs,tblTimeSheetLines.WorkDate," +
                        "tblTimeSheetLines.Prj1Hrs,tblTimeSheetLines.Prj2Hrs,tblTimeSheetLines.InternalHrs," +
                        "tblTimeSheetLines.VacationHrs,tblTimeSheetLines.HolidayHrs,tblTimeSheets.ApprovedDate" +
                        " FROM tblTimeSheets,tblTimeSheetLines,tblLKTimeSheetStatusType,tblEmployee WHERE tblEmployee.Curstatus='Active' " +
                        " and tblEmployee.Id=tblTimeSheets.EmpId AND tblTimeSheetLines.EmpId="+getEmpnameById1()+"  AND " +
                        "DATE(workDate) BETWEEN DATE('"+StartDt+"') AND DATE('"+EndDt+"') AND " +
                        "tblTimeSheets.TimeSheetStatusTypeId=tblLKTimeSheetStatusType.Id AND " +
                        "tblTimeSheets.EmpId = "+getEmpnameById1()+" AND DateStart>='"+StartDt+"' AND DateEnd<='"+EndDt+"'");
                
           if(!getEmpnameById1().equals("") && getEmpFName()!=null) {
                    strSQL.append(" and CONCAT(TRIM(FName),'.',TRIM(LName)) like '"+getEmpFName()+"%'");
                        
                }
                
                
                strSQL.append(" Group BY WorkDate ");
                
               */ 
             
             query = "SELECT tblTimeSheets.EmpId,CONCAT(TRIM(FName),' ',TRIM(MName),' ',TRIM(LName)) AS EmpName,tblEmployee.DepartmentId,tblTimeSheets.DateStart,tblTimeSheets.DateEnd,tblLKTimeSheetStatusType.Description  AS STATUS,tblTimeSheets.TotalHrs,tblTimeSheetLines.WorkDate,tblTimeSheetLines.Prj1Hrs,tblTimeSheetLines.Prj2Hrs,tblTimeSheetLines.InternalHrs,tblTimeSheetLines.VacationHrs,tblTimeSheetLines.HolidayHrs,tblTimeSheets.ApprovedDate FROM tblTimeSheetLines LEFT OUTER JOIN tblTimeSheets ON (tblTimeSheetLines.empId=tblTimeSheets.EmpId)  LEFT OUTER JOIN tblEmployee ON(tblTimeSheets.EmpId=tblEmployee.Id) LEFT OUTER JOIN tblLKTimeSheetStatusType ON (tblTimeSheets.TimeSheetStatusTypeId=tblLKTimeSheetStatusType.Id) ";   
                
              query = query + "  WHERE tblTimeSheetLines.EmpId= "+getEmpnameById1() ;
              
                 query = query + " AND workdate>='"+StartDt+"' AND workdate<='"+EndDt+"' ";
                 query = query + " AND tblTimeSheetLines.timesheetId=tblTimeSheets.timesheetId AND projectId=0 ";
                 //if(!status.equals("-1")){
                //  query = query + "  AND tblLKTimeSheetStatusType.Description = ? ";
                // }
               query = query +  " GROUP BY workdate";
                
                
                
                
                
                
                
                
                
                
                
                
              //System.out.println("Query123------->"+query);
               // timeSheetService.generateEmpTimeSheetsReport(this,empName,strSQL.toString(), httpServletResponse);
               timeSheetService.generateEmpTimeSheetsReport(this,empName,query, httpServletResponse);
                setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                resultType = SUCCESS;
            }catch(Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }
        
        return resultType;
    }
	
/*	public String getCustTimeSheetsReport(){
     String query ="";
        System.out.println("In getCustTimeSheetsReport-->");
        String eType = "";
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME) != null){
            try {
                eType = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_EMPTYPE).toString();
                // StringBuffer   strSQL=new  StringBuffer("SELECT EmpName,Description,DepartmentId,DateStart,DateEnd,TotalHrs,SubmittedDate,ApprovedDate FROM vwTimeSheetList");
                empName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_NAME).toString();
                 String StartDt=DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()));
               String EndDt=DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()));
                List teamList = DataSourceDataProvider.getInstance().getCustomerTeamList(Integer.parseInt(getEmpnameById1()));
                String teamIds = getEmpnameById1();
                if(teamList.size()>0) {
                    teamIds = teamList.toString().replace("[", "");
                    teamIds = teamIds.replace("]", "");
                }
                
                
                String reportToName = DataSourceDataProvider.getInstance().getCustomerNameById(Integer.parseInt(getEmpnameById1()));
                StringBuffer   strSQL=new  StringBuffer("select concat(tblCrmContact.FirstName,'.',tblCrmContact.LastName) as EMPNAME,tblTimeSheetLines.WorkDate,tblTimeSheetLines.Prj1Hrs,"
                        + " tblTimeSheetLines.Prj2Hrs,tblTimeSheets.TimeSheetStatusTypeId from tblTimeSheetLines LEFT OUTER JOIN tblCrmContact ON (tblCrmContact.Id= tblTimeSheetLines.EmpId) "
                        + " LEFT OUTER JOIN tblTimeSheets ON (tblTimeSheets.Id = tblTimeSheetLines.TimeSheetId) "
                        + " where tblTimeSheetLines.EmpId IN ("+teamIds+") and tblTimeSheets.TimeSheetStatusTypeId = "+getDescription());
                
                 if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        strSQL.append(" AND date(tblTimeSheetLines.WorkDate) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        strSQL.append(" AND date(tblTimeSheetLines.WorkDate) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
                    
                
                System.out.println("Customer Query-->"+strSQL.toString());
                ServiceLocator.getTimeSheetService().generateCustTimeSheetsReport(this, empName, strSQL.toString(), httpServletResponse, reportToName);
                // public String generateCustTimeSheetsReport(TimeSheetAction timeSheetAction,String empName,String query,HttpServletResponse httpServletResponse,String reportsToName) throws ServiceLocatorException
               resultType = SUCCESS;
               // teamTimeSheet();
                  
            }catch(Exception ex) {
                System.out.println("ex-->"+ex.getMessage());
                httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                resultType =  ERROR;
            }
        }
    return resultType;
}
*/




/*New Method For Madhavi Mam Req.
 * Date : 07/22/2014
 * Author : Santosh Kola
 * 
 */
public String empTimeSheetSearch() throws Exception {
        resultType = LOGIN;
         String loginId = "";
      //  if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
         if((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) ||(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUST_ID) != null)){
            httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG,resultMessage);
            dataSourceDataProvider = dataSourceDataProvider.getInstance();
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
                String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();
               // setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                
                //setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(198));
               // setProjectReportsToMap(ServiceLocator.getProjectService().getProjectReportsToMap(Integer.parseInt(Properties.getProperty("Cust.ProjectId"))));
               // setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustomerMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
               //setProjectReportsToMap(DataSourceDataProvider.getInstance().getCustProjectReportsToMap((List)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_CUSTOMER_PROJECTIDS)));
                   dataSourceDataProvider = DataSourceDataProvider.getInstance();
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
                setReportsEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
                  // String  workingCountry = (String)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.WORKING_COUNTRY).toString();   
                  // setEmpnamesList((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                     //setEmpnamesList(dataSourceDataProvider.getEmployeeNamesByID(workingCountry));
               // setMyTeamMembers((Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_MY_TEAM_MAP));
                   loginId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString();
           
                if(getSubmitFrom()==null){
                    queryStringBuffer = new StringBuffer();
                   
                    //queryStringBuffer.append("SELECT * FROM vwTimeSheetList where Description not in ('Approved') AND WorkingCountry like '"+workingCountry+"'");
                   //  queryStringBuffer.append("SELECT * FROM vwTimeSheetList where  WorkingCountry like '"+workingCountry+"'");
                     if( getProjectId()==-1 || getProjectId()==0) {
                         queryStringBuffer.append("SELECT * FROM vwTimeSheetList where  WorkingCountry like '"+workingCountry+"'");
                     }else {
                         queryStringBuffer.append("SELECT * FROM vwCustTimeSheetList where  1=1"); 
                     }
                    
                    if(getStartDate()!=null && !"".equals(getStartDate()))   {
                        queryStringBuffer.append(" AND date(DateStart) >= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getStartDate()))+"') ");
                        
                    }if(getEndDate()!=null && !"".equals(getEndDate())) {
                        queryStringBuffer.append(" AND date(DateEnd) <= date('"+DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql(getEndDate()))+"')");
                        
                    }
                    String empIds ="";
                    if(getEmpnameById()!=null && !"".equals(getEmpnameById()))   {
                        if(!"-1".equals(getEmpnameById())){
                           // queryStringBuffer.append(" AND (EmployeeId like '%')");
                           //  queryStringBuffer.append(" AND (EmpId like '%')");
                             
                                //queryStringBuffer.append(" AND (LoginId like '%')");
                            queryStringBuffer.append(" AND (EmpId like '"+getEmpnameById()+"')");
                        }
                      
                        
                    }
                    /*
                        if(getProjectName()!=null && !"".equals(getProjectName()))   {
                        if(!"All".equals(getProjectName())){
                         
                               queryStringBuffer.append(" AND ProjectName like '"+getProjectName()+"'");
                            
                        }
                        
                        
                    }
                    */
                    
                   // if(getProjectId()!=0 && getProjectId()!=-1) {
                     if( getProjectId()!=-1) {
                      setProjectName(DataSourceDataProvider.getInstance().getProjectName(getProjectId()));
                       queryStringBuffer.append(" AND ProjectName like '"+getProjectName()+"'");
                    }
                    
                    
                    
                    
                    
                    
                    
                    
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










/**
 *
 * @return
 */
public String getId() {
    return Id;
}

/**
 *
 * @param Id
 */
public void setId(String Id) {
    this.Id = Id;
}

/**
 *
 * @param httpServletRequest
 */
public void setServletRequest(HttpServletRequest httpServletRequest) {
    this.httpServletRequest = httpServletRequest;
}

/**
 *
 * @return
 */
public TimeSheetVTO getTimeSheetVTO() {
    return timeSheetVTO;
}

/**
 *
 * @param timeSheetVTO
 */
public void setTimeSheetVTO(TimeSheetVTO timeSheetVTO) {
    this.timeSheetVTO = timeSheetVTO;
}

/**
 *
 * @return
 */
public String getStartDate() {
    return startDate;
}

/**
 *
 * @param startDate
 */
public void setStartDate(String startDate) {
    this.startDate = startDate;
}

/**
 *
 * @return
 */
public String getEndDate() {
    return endDate;
}

/**
 *
 * @param endDate
 */
public void setEndDate(String endDate) {
    this.endDate = endDate;
}

/**
 *
 * @return
 */
public String getSqlQuery() {
    return sqlQuery;
}

/**
 *
 * @param sqlQuery
 */
public void setSqlQuery(String sqlQuery) {
    this.sqlQuery = sqlQuery;
}

/**
 *
 * @return
 */
public String getTimeSheetStat() {
    return timeSheetStat;
}

/**
 *
 * @param timeSheetStat
 */
public void setTimeSheetStat(String timeSheetStat) {
    this.timeSheetStat = timeSheetStat;
}

/**
 *
 * @return
 */
public String getComment() {
    return comment;
}

/**
 *
 * @param comment
 */
public void setComment(String comment) {
    this.comment = comment;
}

/**
 *
 * @return
 */
public String getEmpName() {
    return empName;
}

/**
 *
 * @param empName
 */
public void setEmpName(String empName) {
    this.empName = empName;
}

/**
 *
 * @return
 */
public Map getMyTeamMembers() {
    return MyTeamMembers;
}

/**
 *
 * @param MyTeamMembers
 */
public void setMyTeamMembers(Map MyTeamMembers) {
    this.MyTeamMembers = MyTeamMembers;
}

/**
 *
 * @return
 */
public String getIsOnsite() {
    return isOnsite;
}

/**
 *
 * @param isOnsite
 */
public void setIsOnsite(String isOnsite) {
    this.isOnsite = isOnsite;
}

/**
 *
 * @return
 */
public File getUpload() {
    return upload;
}

/**
 *
 * @param upload
 */
public void setUpload(File upload) {
    this.upload = upload;
}

/**
 *
 * @return
 */
public String getCreatedBy() {
    return createdBy;
}

/**
 *
 * @param createdBy
 */
public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
}

/**
 *
 * @return
 */
public int getObjectId() {
    return objectId;
}

/**
 *
 * @param objectId
 */
public void setObjectId(int objectId) {
    this.objectId = objectId;
}

/**
 *
 * @return
 */
public String getUploadContentType() {
    return uploadContentType;
}

/**
 *
 * @param uploadContentType
 */
public void setUploadContentType(String uploadContentType) {
    this.uploadContentType = uploadContentType;
}

/**
 *
 * @return
 */
public String getUploadFileName() {
    return uploadFileName;
}

/**
 *
 * @param uploadFileName
 */
public void setUploadFileName(String uploadFileName) {
    this.uploadFileName = uploadFileName;
}

/**
 *
 * @return
 */
public String getFileLocation() {
    return fileLocation;
}

/**
 *
 * @param fileLocation
 */
public void setFileLocation(String fileLocation) {
    this.fileLocation = fileLocation;
}

/**
 *
 * @return
 */
public String getFilePath() {
    return filePath;
}

/**
 *
 * @param filePath
 */
public void setFilePath(String filePath) {
    this.filePath = filePath;
}

/**
 *
 * @return
 */
public String getAttachmentName() {
    return attachmentName;
}

/**
 *
 * @param attachmentName
 */
public void setAttachmentName(String attachmentName) {
    this.attachmentName = attachmentName;
}

/**
 *
 * @return
 */
public String getObjectType() {
    return objectType;
}

/**
 *
 * @param objectType
 */
public void setObjectType(String objectType) {
    this.objectType = objectType;
}
/**
 *
 * @return
 */
public String getResultMessage() {
    return resultMessage;
}

/**
 *
 * @param resultMessage
 */
public void setResultMessage(String resultMessage) {
    this.resultMessage = resultMessage;
}

public int getTempVar() {
    return tempVar;
}

public void setTempVar(int tempVar) {
    this.tempVar = tempVar;
}


public void setServletResponse(HttpServletResponse httpServletResponse) {
    this.httpServletResponse = httpServletResponse;
}

public String getEmpLName() {
    return empLName;
}

public void setEmpLName(String empLName) {
    this.empLName = empLName;
}

public String getEmpFName() {
    return empFName;
}

public void setEmpFName(String empFName) {
    this.empFName = empFName;
}

public String getSubmitFrom() {
    return submitFrom;
}

public void setSubmitFrom(String submitFrom) {
    this.submitFrom = submitFrom;
}

    public int getEmptimesheetid() {
        return emptimesheetid;
    }

    public void setEmptimesheetid(int emptimesheetid) {
        this.emptimesheetid = emptimesheetid;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public Map getEmpnamesList() {
        return empnamesList;
    }

    public void setEmpnamesList(Map empnamesList) {
        this.empnamesList = empnamesList;
    }

    public String getEmpnameById() {
        return empnameById;
    }

    public void setEmpnameById(String empnameById) {
        this.empnameById = empnameById;
    }

    public String getEmpnameById1() {
        return empnameById1;
    }

    public void setEmpnameById1(String empnameById1) {
        this.empnameById1 = empnameById1;
    }

    public String getSheetType() {
        return sheetType;
    }

    public void setSheetType(String sheetType) {
        this.sheetType = sheetType;
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
     * @return the custnamesList
     */
    public Map getCustnamesList() {
        return custnamesList;
    }

    /**
     * @return the custnameById
     */
    public String getCustnameById() {
        return custnameById;
    }

    /**
     * @param custnamesList the custnamesList to set
     */
    public void setCustnamesList(Map custnamesList) {
        this.custnamesList = custnamesList;
    }

    /**
     * @param custnameById the custnameById to set
     */
    public void setCustnameById(String custnameById) {
        this.custnameById = custnameById;
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
     * @return the myProjects
     */
    public Map getMyProjects() {
        return myProjects;
    }

    /**
     * @param myProjects the myProjects to set
     */
    public void setMyProjects(Map myProjects) {
        this.myProjects = myProjects;
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
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
     * @return the printStartDate
     */
    public String getPrintStartDate() {
        return printStartDate;
    }

    /**
     * @param printStartDate the printStartDate to set
     */
    public void setPrintStartDate(String printStartDate) {
        this.printStartDate = printStartDate;
    }

    /**
     * @return the printEndDate
     */
    public String getPrintEndDate() {
        return printEndDate;
    }

    /**
     * @param printEndDate the printEndDate to set
     */
    public void setPrintEndDate(String printEndDate) {
        this.printEndDate = printEndDate;
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

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }


    
    
}
