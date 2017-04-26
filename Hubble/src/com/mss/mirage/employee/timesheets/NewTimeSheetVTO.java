/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.timesheets;

/**
 *
 * @author miracle
 */
public class NewTimeSheetVTO {
        
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
     
    private String proj1Name;
    private String proj2Name;
    private String proj3Name;
    private String proj4Name;
    private String proj5Name;
    
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
    
    
    //Dual change
     private boolean isDualReportingRequired;
    private int priReportsToId;
    private int secReportsToId;
    
     private int timeSheetStatusTypeId;
    private int secondReportsToStatusTypeId;
    private int fileFlagValue;
     private int biometricFlag;
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
     * @return the POId
     */
    public int getPOId() {
        return POId;
    }

    /**
     * @param POId the POId to set
     */
    public void setPOId(int POId) {
        this.POId = POId;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the modeType
     */
    public String getModeType() {
        return modeType;
    }

    /**
     * @param modeType the modeType to set
     */
    public void setModeType(String modeType) {
        this.modeType = modeType;
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
     * @return the proj1Name
     */
    public String getProj1Name() {
        return proj1Name;
    }

    /**
     * @param proj1Name the proj1Name to set
     */
    public void setProj1Name(String proj1Name) {
        this.proj1Name = proj1Name;
    }

    /**
     * @return the proj2Name
     */
    public String getProj2Name() {
        return proj2Name;
    }

    /**
     * @param proj2Name the proj2Name to set
     */
    public void setProj2Name(String proj2Name) {
        this.proj2Name = proj2Name;
    }

    /**
     * @return the proj3Name
     */
    public String getProj3Name() {
        return proj3Name;
    }

    /**
     * @param proj3Name the proj3Name to set
     */
    public void setProj3Name(String proj3Name) {
        this.proj3Name = proj3Name;
    }

    /**
     * @return the proj4Name
     */
    public String getProj4Name() {
        return proj4Name;
    }

    /**
     * @param proj4Name the proj4Name to set
     */
    public void setProj4Name(String proj4Name) {
        this.proj4Name = proj4Name;
    }

    /**
     * @return the proj5Name
     */
    public String getProj5Name() {
        return proj5Name;
    }

    /**
     * @param proj5Name the proj5Name to set
     */
    public void setProj5Name(String proj5Name) {
        this.proj5Name = proj5Name;
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
     * @return the timeSheetStatusTypeId
     */
    public int getTimeSheetStatusTypeId() {
        return timeSheetStatusTypeId;
    }

    /**
     * @param timeSheetStatusTypeId the timeSheetStatusTypeId to set
     */
    public void setTimeSheetStatusTypeId(int timeSheetStatusTypeId) {
        this.timeSheetStatusTypeId = timeSheetStatusTypeId;
    }

    /**
     * @return the secondReportsToStatusTypeId
     */
    public int getSecondReportsToStatusTypeId() {
        return secondReportsToStatusTypeId;
    }

    /**
     * @param secondReportsToStatusTypeId the secondReportsToStatusTypeId to set
     */
    public void setSecondReportsToStatusTypeId(int secondReportsToStatusTypeId) {
        this.secondReportsToStatusTypeId = secondReportsToStatusTypeId;
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
     * @return the biometricFlag
     */
    public int getBiometricFlag() {
        return biometricFlag;
    }

    /**
     * @param biometricFlag the biometricFlag to set
     */
    public void setBiometricFlag(int biometricFlag) {
        this.biometricFlag = biometricFlag;
    }
}
