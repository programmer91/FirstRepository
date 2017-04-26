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
 *$Source: /Hubble/Hubble/src/java/com/mss/mirage/employee/timesheets/TimeSheetVTO.java,v $
 *
 * @(#)TimeSheetVTO.java	September 24, 2007, 12:13 AM
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */


package com.mss.mirage.employee.timesheets;

/**
 * <p> This class can be used to store the Timesheet values and also
 * used for displaying values on the jsp . <p>
 *
 * @version 2.0, September 24, 2007.
 *
 * @author  RangaRao Panda<rpanda@miraclesoft.com>
 *
 */
public class TimeSheetVTO {
    
    /** Used for Storing the Week Start Date */
    private String wstDate;
    
    /** Used for Storing the Week End Date */
    private String wenDate;
    
    /** Used for Storing the submitted Date */
    private String submittedDate;
    
    /** Used for strong apporove date */
    private String approveDate;
    
    /**
     * This seven variable weekDate1,weekDate2,weekDate3,weekDate4
     * weekDate5,weekDate6,weekDate7 can be used to stroing the one full week values of the timesheet ,
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
    
    /** This variable POId used to store the project ID */
    private int POId;
    
    /** This variable notes used to store the timesheet notes */
    private String notes;
    
    /** This variable modeType used to store the action name of the jsp page. */
    private String modeType;
    
    /** This variable txtNotes used to store the timesheet notes */
    private String txtNotes;
    
    /** This variable timeSheetID used to store the timesheet id */
    private String timeSheetID;
    
    /**
     * This variable empName is to store the value of Employee Name
     */
    private String empName;
    
    /**
     * This variable comment is to store the value of Comment
     */
    private String comment;
    
    //new
    private int projectId;
    /** Creates a new instance of TimeSheetVTO */
    public TimeSheetVTO() {
        
    }
    
    
    /**
     * 
     * @return String
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
     * @return String
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
     * @return int
     */
    public int getPOId() {
        return POId;
    }
    
    /**
     * 
     * @param POId 
     */
    public void setPOId(int POId) {
        this.POId = POId;
    }
    
    /**
     * 
     * @return String
     */
    public String getNotes() {
        return notes;
    }
    
    /**
     * 
     * @param notes 
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    /**
     * 
     * @return String
     */
    public String getModeType() {
        return modeType;
    }
    
    /**
     * 
     * @param modeType 
     */
    public void setModeType(String modeType) {
        this.modeType = modeType;
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
     * @return String
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    
}
