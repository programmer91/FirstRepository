/*******************************************************************************
 * /*
 *
 *Project: MirajeV2
 *
 *$Author: rdadi $
 *
 *$Date: 2011-03-01 09:47:21 $
 *
 *$Revision: 1.2 $
 *
 *$Source: /Hubble/Hubble/src/java/com/mss/mirage/employee/timesheets/TimeSheetServiceImpl.java,v $
 *
 * @(#)TimeSheetServiceImpl.java	September 24, 2007, 12:13 AM
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */


package com.mss.mirage.employee.timesheets;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ReportProperties;
//import com.mss.mirage.util.LoggerManager;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;

/**
 * <p> This class implements all the TimeSheet Business logic methods  that are defined
 * in TimeSheetService interface. <p>
 *
 * @version 2.0, September 24, 2007.
 *
 * @author  RangaRao Panda<rpanda@miraclesoft.com>
 *
 * @See com.mss.mirage.employee.timesheets.TimeSheetService
 */
public class TimeSheetServiceImpl implements TimeSheetService {
    
    private String reportResult;
    
    String BdayreportsSource = ReportProperties.getProperty("BirthdayReportJRXML");
    String TimesheetreportsSource = ReportProperties.getProperty("PrintTimeSheetReportsJRXML");
   // String customerTimesheetreportsSource = ReportProperties.getProperty("PrintCustomerTimeSheetReportsJRXML");
    JasperPrint jasperPrint = null;
    JasperReport jasperReport = null;
    ServletOutputStream outStream =null;
    Connection connection = null;
    Map RBMap = null;
    List finalList = null;
    String query =  null;
    /** Creates a new instance of TimeSheetService */
    public TimeSheetServiceImpl() {
        
    }
    
    /**  This method can be used to storing list data to TimeSheetVTO bean class.
     *
     * @param li A java.util.List
     *
     *{@Link com.mss.mirage.employee.timesheets.TimeSheetService#getWeekDaysBean(List)}
     *
     * @return timeSheetVTO TimeSheetVTO.
     */
    public TimeSheetVTO getWeekDaysBean(List li) {
        TimeSheetVTO timeSheetVTO = new  TimeSheetVTO();
        
        /* Used for Storing the Week Start Date */
        timeSheetVTO.setWstDate((String)li.get(0));
        
        /* Used for Storing the Week End Date */
        timeSheetVTO.setWenDate((String)li.get(1));
        
        /* Used for Storing the Week Submitted Date */
        timeSheetVTO.setSubmittedDate((String)li.get(2));
        
        /* for storing the seven weekdays */
        String [] weekDaysSequence=(String [])li.get(3);
        
        timeSheetVTO.setWeekDate1(weekDaysSequence[0]);
        timeSheetVTO.setWeekDate2(weekDaysSequence[1]);
        timeSheetVTO.setWeekDate3(weekDaysSequence[2]);
        timeSheetVTO.setWeekDate4(weekDaysSequence[3]);
        timeSheetVTO.setWeekDate5(weekDaysSequence[4]);
        timeSheetVTO.setWeekDate6(weekDaysSequence[5]);
        timeSheetVTO.setWeekDate7(weekDaysSequence[6]);
        
        /* to set the action Type */
        timeSheetVTO.setModeType("AddTimeSheet");
        return timeSheetVTO; //  return the bean reference
    } // @ getWeekDaysBean(List)
    
    /**  This method can be used to generating the starting , ending and weekdays of given java.util.Date.
     *
     * @param cal A java.util.Calendar.
     *
     *{@Link com.mss.mirage.employee.timesheets.TimeSheetService#getweekStartAndEndDays(cal)}
     *
     * @return daysList java.util.List.
     */
    public List getweekStartAndEndDays(Calendar cal) {
        
        /* used to store the totla weekdays. */
        List daysList = new ArrayList();
        
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
        
        /* current date */
        Calendar currDay = Calendar.getInstance();
        curntDate = (currDay.get(Calendar.MONTH)+1) + "/" + currDay.get(Calendar.DAY_OF_MONTH)+"/"+currDay.get(Calendar.YEAR) ;
        daysList.add(wstDate);
        daysList.add(wenDate);
        daysList.add(curntDate);
        daysList.add(weekDays);
        
        return daysList;
    } // @ getweekStartAndEndDays(Calendar cal)
    
    /**  This method can be used to seacrh the TimeSheet based on the employee Id and TimeSheet ID
     *  return the TimeSheetVTO object.
     *
     * @param empId A Int.
     * @param timeSheetID A Int.
     *
     *{@Link com.mss.mirage.employee.timesheets.TimeSheetService#getTimeSheet(int,int)}
     *
     * @return  timeSheetVTO  A TimeSheetVTO object.
     */
    public TimeSheetVTO getTimeSheet(int empId, int timeSheetID,String empType,String resourceType) {
        TimeSheetVTO timeSheetVTO = new TimeSheetVTO();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int rowCount=1;
        try {
            
            /* getting the connection from the ConnectionProvider */
            connection = ConnectionProvider.getInstance().getConnection();
            
            if(connection!=null) {
                
                statement = connection.createStatement();
                
                /* taking the info from the tblTimeSheets based on the empId and timeSheet Id */
               resultSet = statement.executeQuery("SELECT EmpId,DateStart,DateEnd,SubmittedDate,ApprovedDate,TotalHrs,Notes,ProjectId  FROM tblTimeSheets WHERE EmpId="+empId+" AND TimeSheetId="+timeSheetID+"");
               //log=LoggerManager.getLog(TimeSheetServiceImpl.class.getName());
                while(resultSet.next()) {
                    timeSheetVTO.setWstDate(DateUtility.getInstance().convertDateToView(resultSet.getDate(2)));
                    timeSheetVTO.setWenDate(DateUtility.getInstance().convertDateToView(resultSet.getDate(3)));
                    timeSheetVTO.setSubmittedDate(DateUtility.getInstance().convertDateToView(resultSet.getDate(4)));
                  //  System.out.println("setApproveDate--->"+resultSet.getDate(5));
                   
                   // if("")
                    timeSheetVTO.setApproveDate(DateUtility.getInstance().convertDateToView(resultSet.getDate(5)));
                    timeSheetVTO.setTotalBillHrs(resultSet.getDouble(6));
                    timeSheetVTO.setNotes(resultSet.getString(7));
                    timeSheetVTO.setProjectId(resultSet.getInt(8));
                } // while
                resultSet.close(); // clsoing connection
                resultSet = null;
                /* from tblTimeSheetLines getting full week of the TimeSheet */
                resultSet = statement.executeQuery("SELECT WorkDate,Prj1Hrs,Prj2Hrs,InternalHrs,VacationHrs,HolidayHrs  FROM tblTimeSheetLines WHERE EmpId="+empId+" AND TimeSheetId="+timeSheetID+"");
                
                while(resultSet.next()) {
                    
                    /* Work days of sunday */
                    if(rowCount==1) {
                        timeSheetVTO.setWeekDate1(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Sun(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Sun(resultSet.getDouble(3));
                        timeSheetVTO.setInternalSun(resultSet.getDouble(4));
                        timeSheetVTO.setVacationSun(resultSet.getDouble(5));
                        timeSheetVTO.setHoliSun(resultSet.getDouble(6));
                        
                    } else if(rowCount==2) {
                        
                        /* Work days of monday */
                        timeSheetVTO.setWeekDate2(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Mon(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Mon(resultSet.getDouble(3));
                        timeSheetVTO.setInternalMon(resultSet.getDouble(4));
                        timeSheetVTO.setVacationMon(resultSet.getDouble(5));
                        timeSheetVTO.setHoliMon(resultSet.getDouble(6));
                        
                    } else if(rowCount==3) {
                        
                        /* Work days of Tuesday */
                        timeSheetVTO.setWeekDate3(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Tus(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Tus(resultSet.getDouble(3));
                        timeSheetVTO.setInternalTus(resultSet.getDouble(4));
                        timeSheetVTO.setVacationTus(resultSet.getDouble(5));
                        timeSheetVTO.setHoliTus(resultSet.getDouble(6));
                        
                    } else if(rowCount==4) {
                        
                        /* Work days of wednessDay */
                        timeSheetVTO.setWeekDate4(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Wed(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Wed(resultSet.getDouble(3));
                        timeSheetVTO.setInternalWed(resultSet.getDouble(4));
                        timeSheetVTO.setVacationWed(resultSet.getDouble(5));
                        timeSheetVTO.setHoliWed(resultSet.getDouble(6));
                        
                    } else if(rowCount==5) {
                        
                        /* Work days of ThursDay */
                        timeSheetVTO.setWeekDate5(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Thur(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Thur(resultSet.getDouble(3));
                        timeSheetVTO.setInternalThur(resultSet.getDouble(4));
                        timeSheetVTO.setVacationThur(resultSet.getDouble(5));
                        timeSheetVTO.setHoliThur(resultSet.getDouble(6));
                        
                    } else if(rowCount==6) {
                        
                        /* Work days of Friday */
                        timeSheetVTO.setWeekDate6(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Fri(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Fri(resultSet.getDouble(3));
                        timeSheetVTO.setInternalFri(resultSet.getDouble(4));
                        timeSheetVTO.setVacationFri(resultSet.getDouble(5));
                        timeSheetVTO.setHoliFri(resultSet.getDouble(6));
                        
                    } else if(rowCount==7) {
                        
                        /* Work days of Saturday */
                        timeSheetVTO.setWeekDate7(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Sat(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Sat(resultSet.getDouble(3));
                        timeSheetVTO.setInternalSat(resultSet.getDouble(4));
                        timeSheetVTO.setVacationSat(resultSet.getDouble(5));
                        timeSheetVTO.setHoliSat(resultSet.getDouble(6));
                        
                    }
                    rowCount++;
                } // while
                resultSet.close(); // clsoing connection
                resultSet = null;
                // for setting the Action Type name
                timeSheetVTO.setModeType("EditTimesheet");
                
                /* calculation of the individual day of total working , billing , vaction and holidays sums*/
                timeSheetVTO.setTotalSun((timeSheetVTO.getProj1Sun()+timeSheetVTO.getProj2Sun()+timeSheetVTO.getInternalSun()+timeSheetVTO.getVacationSun()+timeSheetVTO.getHoliSun()));
                timeSheetVTO.setTotalMon((timeSheetVTO.getProj1Mon()+timeSheetVTO.getProj2Mon()+timeSheetVTO.getInternalMon()+timeSheetVTO.getVacationMon()+timeSheetVTO.getHoliMon()));
                timeSheetVTO.setTotalTus((timeSheetVTO.getProj1Tus()+timeSheetVTO.getProj2Tus()+timeSheetVTO.getInternalTus()+timeSheetVTO.getVacationTus()+timeSheetVTO.getHoliTus()));
                timeSheetVTO.setTotalWed((timeSheetVTO.getProj1Wed()+timeSheetVTO.getProj2Wed()+timeSheetVTO.getInternalWed()+timeSheetVTO.getVacationWed()+timeSheetVTO.getHoliWed()));
                timeSheetVTO.setTotalThur((timeSheetVTO.getProj1Thur()+timeSheetVTO.getProj2Thur()+timeSheetVTO.getInternalThur()+timeSheetVTO.getVacationThur()+timeSheetVTO.getHoliThur()));
                timeSheetVTO.setTotalFri((timeSheetVTO.getProj1Fri()+timeSheetVTO.getProj2Fri()+timeSheetVTO.getInternalFri()+timeSheetVTO.getVacationFri()+timeSheetVTO.getHoliFri()));
                timeSheetVTO.setTotalSat((timeSheetVTO.getProj1Sat()+timeSheetVTO.getProj2Sat()+timeSheetVTO.getInternalSat()+timeSheetVTO.getVacationSat()+timeSheetVTO.getHoliSat()));
                timeSheetVTO.setProj1TotalHrs((timeSheetVTO.getProj1Tus()+timeSheetVTO.getProj1Sun()+timeSheetVTO.getProj1Mon()+timeSheetVTO.getProj1Wed()+timeSheetVTO.getProj1Thur()+timeSheetVTO.getProj1Fri()+timeSheetVTO.getProj1Sat()));
                timeSheetVTO.setProj2TotalHrs((timeSheetVTO.getProj2Tus()+timeSheetVTO.getProj2Sun()+timeSheetVTO.getProj2Mon()+timeSheetVTO.getProj2Wed()+timeSheetVTO.getProj2Thur()+timeSheetVTO.getProj2Fri()+timeSheetVTO.getProj2Sat()));
                timeSheetVTO.setInternalTotalHrs((timeSheetVTO.getInternalSun()+timeSheetVTO.getInternalMon()+timeSheetVTO.getInternalTus()+timeSheetVTO.getInternalWed()+timeSheetVTO.getInternalThur()+timeSheetVTO.getInternalFri()+timeSheetVTO.getInternalSat()));
                timeSheetVTO.setVacationTotalHrs((timeSheetVTO.getVacationSun()+timeSheetVTO.getVacationMon()+timeSheetVTO.getVacationTus()+timeSheetVTO.getVacationWed()+timeSheetVTO.getVacationThur()+timeSheetVTO.getVacationFri()+timeSheetVTO.getVacationSat()));
                timeSheetVTO.setHoliTotalHrs((timeSheetVTO.getHoliSun()+timeSheetVTO.getHoliMon()+timeSheetVTO.getHoliTus()+timeSheetVTO.getHoliWed()+timeSheetVTO.getHoliThur()+timeSheetVTO.getHoliFri()+timeSheetVTO.getHoliSat()));
                timeSheetVTO.setAllWeekendTotalHors((timeSheetVTO.getProj1TotalHrs()+timeSheetVTO.getProj2TotalHrs()+timeSheetVTO.getInternalTotalHrs()+timeSheetVTO.getVacationTotalHrs()+timeSheetVTO.getHoliTotalHrs()));
                timeSheetVTO.setTimeSheetID(String.valueOf(timeSheetID));
                // System.out.println("empType-->"+empType);
                // System.out.println("resourceType-->"+resourceType);
                if(empType.equalsIgnoreCase("e")){
                if(!(resourceType!=null)){
                     queryString = "SELECT EmpName from vwTimeSheetList where EmpId='"+empId+"'";
                }
                else if(resourceType.equalsIgnoreCase("e"))
                    queryString = "SELECT EmpName from vwTimeSheetList where EmpId='"+empId+"'";
                    else 
                        queryString = "SELECT EmpName from vwCustTimeSheetList where EmpId='"+empId+"'";
                
                }else{
                queryString = "SELECT EmpName from vwCustTimeSheetList where EmpId='"+empId+"'";
                }
                //System.out.println(queryString);
                resultSet = statement.executeQuery(queryString);
                resultSet.next();
                timeSheetVTO.setEmpName(resultSet.getString("EmpName"));
                resultSet.close(); // clsoing connection
                resultSet = null;
                
                resultSet = statement.executeQuery("SELECT Comments from tblTimeSheets where EmpId='"+empId+"' AND TimeSheetId="+timeSheetID+"");
                resultSet.next();
                timeSheetVTO.setComment(resultSet.getString("Comments"));
                resultSet.close(); // clsoing connection
                resultSet = null;
                
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            // log.setLevel((Level)Level.ERROR);
            // log.error("The Error @getTimeSheet()",ex);
        } finally {
            if(connection!=null)
                try {
                     if(resultSet != null){
                        resultSet.close();
                        resultSet = null;
                    }
                    if(statement != null){
                        statement.close();
                        statement = null;
                    }
                    if(connection != null){
                        connection.close();
                        connection = null;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // log.setLevel((Level)Level.ERROR);
                    // log.error("The Error @getTimeSheet()",ex);
                }
        }
        return timeSheetVTO;
    } // @ getTimeSheet(int empId, int timeSheetID)
    
   /**
     *  This method can be used for adding the new TimeSheet.
     *
     * Modified By ajay Tummapala
     * @return isTimesheetAddOrEdit a boolean.
     *
     * {@Link com.mss.mirage.employee.timesheets.TimeSheetService#addTimeSheet(TimeSheetAction)}
     * @param timeSheetAction
     * @throws ServiceLocatorException.
     */
    public int addTimeSheet(TimeSheetAction timeSheetAction) throws ServiceLocatorException {
        
        /* for stroing the approvied date */
        int isTimesheetAddOrEdit = 0;
        java.sql.Date approDate = null;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        
        if(timeSheetAction.getApproveDate()!=null && !("".equalsIgnoreCase(timeSheetAction.getApproveDate()))) {
            
            /* if employee not enter the approved date then we fill default value to table */
            approDate = DateUtility.getInstance().getMysqlDate(timeSheetAction.getApproveDate());
        } else {
            
            approDate = null;
        } //if
        
        try {
            
            /* getting the connection from the ConnectionProvider */
            connection = ConnectionProvider.getInstance().getConnection();
            if(connection != null) {
                callableStatement = connection.prepareCall("{call spTimeSheetAdd(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setInt(1,Integer.parseInt(timeSheetAction.getEmpID()));
                callableStatement.setInt(2,1);
                callableStatement.setInt(3,0);
                
                /* converting the string object to date object*/
                callableStatement.setDate(4,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWstDate()));
                callableStatement.setDate(5,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWenDate()));
                callableStatement.setDate(6,approDate);
                callableStatement.setDate(7,DateUtility.getInstance().getMysqlDate(timeSheetAction.getSubmittedDate()));
                callableStatement.setDouble(8,timeSheetAction.getTotalBillHrs());
                callableStatement.setDouble(9,timeSheetAction.getProj1Sun());
                callableStatement.setDouble(10,timeSheetAction.getProj1Mon());
                callableStatement.setDouble(11,timeSheetAction.getProj1Tus());
                callableStatement.setDouble(12,timeSheetAction.getProj1Wed());
                callableStatement.setDouble(13,timeSheetAction.getProj1Thur());
                callableStatement.setDouble(14,timeSheetAction.getProj1Fri());
                callableStatement.setDouble(15,timeSheetAction.getProj1Sat());
                callableStatement.setDouble(16,timeSheetAction.getProj2Sun());
                callableStatement.setDouble(17,timeSheetAction.getProj2Mon());
                callableStatement.setDouble(18,timeSheetAction.getProj2Tus());
                callableStatement.setDouble(19,timeSheetAction.getProj2Wed());
                callableStatement.setDouble(20,timeSheetAction.getProj2Thur());
                callableStatement.setDouble(21,timeSheetAction.getProj2Fri());
                callableStatement.setDouble(22,timeSheetAction.getProj2Sat());
                callableStatement.setDouble(23,timeSheetAction.getInternalSun());
                callableStatement.setDouble(24,timeSheetAction.getInternalMon());
                callableStatement.setDouble(25,timeSheetAction.getInternalTus());
                callableStatement.setDouble(26,timeSheetAction.getInternalWed());
                callableStatement.setDouble(27,timeSheetAction.getInternalThur());
                callableStatement.setDouble(28,timeSheetAction.getInternalFri());
                callableStatement.setDouble(29,timeSheetAction.getInternalSat());
                callableStatement.setDouble(30,timeSheetAction.getVacationSun());
                callableStatement.setDouble(31,timeSheetAction.getVacationMon());
                callableStatement.setDouble(32,timeSheetAction.getVacationTus());
                callableStatement.setDouble(33,timeSheetAction.getVacationWed());
                callableStatement.setDouble(34,timeSheetAction.getVacationThur());
                callableStatement.setDouble(35,timeSheetAction.getVacationFri());
                callableStatement.setDouble(36,timeSheetAction.getVacationSat());
                callableStatement.setDouble(37,timeSheetAction.getHoliSun());
                callableStatement.setDouble(38,timeSheetAction.getHoliMon());
                callableStatement.setDouble(39,timeSheetAction.getHoliTus());
                callableStatement.setDouble(40,timeSheetAction.getHoliWed());
                callableStatement.setDouble(41,timeSheetAction.getHoliThur());
                callableStatement.setDouble(42,timeSheetAction.getHoliFri());
                callableStatement.setDouble(43,timeSheetAction.getHoliSat());
                
                // converting the String to sql Date object.
                callableStatement.setDate(44,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate1()));
                callableStatement.setDate(45,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate2()));
                callableStatement.setDate(46,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate3()));
                callableStatement.setDate(47,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate4()));
                callableStatement.setDate(48,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate5()));
                callableStatement.setDate(49,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate6()));
                callableStatement.setDate(50,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate7()));
                callableStatement.setString(51,timeSheetAction.getTxtNotes());
                callableStatement.setString(52,"Ins");
                callableStatement.setInt(53,timeSheetAction.getProjectId());
                callableStatement.registerOutParameter(54,java.sql.Types.INTEGER);
                callableStatement.execute() ;
                int timeSheetId = callableStatement.getInt(54);
                if(timeSheetId<1) // for retriving the timesheetID
                { isTimesheetAddOrEdit = timeSheetId;
                  //log.debug("The Error occur while inserting the TimeSheet for EmpId"+timeSheetAction.getEmpID());
                } else {
                    isTimesheetAddOrEdit = timeSheetId;
                    // log.debug("The TimeSheet Successfully Added For EmpId:"+timeSheetAction.getEmpID()+"with TimeSheetId:"+callableStatement.getInt(53));
                }// if
                
            } //if
            
            
        }//try
        catch(Exception ex) {
            throw new ServiceLocatorException(ex);
            //log.debug("The Error @addTimeSheet()",ex);
        } finally {
            
            try {
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
                //log.debug("The Error @addTimeSheet()",ex);
            } //try
        } //finally
        return isTimesheetAddOrEdit;
    }
    /**  This method can be used for editing the TimeSheet.
     *
     * @param timeSheetAction A TimeSheetAction reference.
     *
     * @return isTimesheetAddOrEdit A boolean.
     *
     *{@Link com.mss.mirage.employee.timesheets.TimeSheetService#editTimeSheet(TimeSheetAction)}
     *
     * @throws ServiceLocatorException.
     */
    public boolean editTimeSheet(TimeSheetAction timeSheetAction) throws ServiceLocatorException {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean isTimesheetAddOrEdit = false;
        try {
            
            connection = ConnectionProvider.getInstance().getConnection();
            if(connection != null) {
                callableStatement = connection.prepareCall("{call spTimeSheetEdit(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setInt(1,Integer.parseInt(timeSheetAction.getEmpID().trim()));
                callableStatement.setInt(2,Integer.parseInt(timeSheetAction.getTimeSheetID().trim()));
                
                // converting the String to sql Date object.
                callableStatement.setDate(3,DateUtility.getInstance().getMysqlDate(timeSheetAction.getApproveDate().trim()));
                callableStatement.setDouble(4,timeSheetAction.getTotalBillHrs());
                callableStatement.setDouble(5,timeSheetAction.getProj1Sun());
                callableStatement.setDouble(6,timeSheetAction.getProj1Mon());
                callableStatement.setDouble(7,timeSheetAction.getProj1Tus());
                callableStatement.setDouble(8,timeSheetAction.getProj1Wed());
                callableStatement.setDouble(9,timeSheetAction.getProj1Thur());
                callableStatement.setDouble(10,timeSheetAction.getProj1Fri());
                callableStatement.setDouble(11,timeSheetAction.getProj1Sat());
                callableStatement.setDouble(12,timeSheetAction.getProj2Sun());
                callableStatement.setDouble(13,timeSheetAction.getProj2Mon());
                callableStatement.setDouble(14,timeSheetAction.getProj2Tus());
                callableStatement.setDouble(15,timeSheetAction.getProj2Wed());
                callableStatement.setDouble(16,timeSheetAction.getProj2Thur());
                callableStatement.setDouble(17,timeSheetAction.getProj2Fri());
                callableStatement.setDouble(18,timeSheetAction.getProj2Sat());
                callableStatement.setDouble(19,timeSheetAction.getInternalSun());
                callableStatement.setDouble(20,timeSheetAction.getInternalMon());
                callableStatement.setDouble(21,timeSheetAction.getInternalTus());
                callableStatement.setDouble(22,timeSheetAction.getInternalWed());
                callableStatement.setDouble(23,timeSheetAction.getInternalThur());
                callableStatement.setDouble(24,timeSheetAction.getInternalFri());
                callableStatement.setDouble(25,timeSheetAction.getInternalSat());
                callableStatement.setDouble(26,timeSheetAction.getVacationSun());
                callableStatement.setDouble(27,timeSheetAction.getVacationMon());
                callableStatement.setDouble(28,timeSheetAction.getVacationTus());
                callableStatement.setDouble(29,timeSheetAction.getVacationWed());
                callableStatement.setDouble(30,timeSheetAction.getVacationThur());
                callableStatement.setDouble(31,timeSheetAction.getVacationFri());
                callableStatement.setDouble(32,timeSheetAction.getVacationSat());
                callableStatement.setDouble(33,timeSheetAction.getHoliSun());
                callableStatement.setDouble(34,timeSheetAction.getHoliMon());
                callableStatement.setDouble(35,timeSheetAction.getHoliTus());
                callableStatement.setDouble(36,timeSheetAction.getHoliWed());
                callableStatement.setDouble(37,timeSheetAction.getHoliThur());
                callableStatement.setDouble(38,timeSheetAction.getHoliFri());
                callableStatement.setDouble(39,timeSheetAction.getHoliSat());
                
                // converting the String to sql Date object.
                callableStatement.setString(40,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate1().trim())));
                callableStatement.setString(41,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate2().trim())));
                callableStatement.setString(42,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate3().trim())));
                callableStatement.setString(43,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate4().trim())));
                callableStatement.setString(44,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate5().trim())));
                callableStatement.setString(45,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate6().trim())));
                callableStatement.setString(46,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate7().trim())));
                callableStatement.setString(47,timeSheetAction.getTxtNotes().trim());
                callableStatement.setInt(48,timeSheetAction.getTempVar());
                callableStatement.setString(49,timeSheetAction.getEmpType());
                callableStatement.registerOutParameter(50,java.sql.Types.INTEGER);
                callableStatement.execute() ;
                
                /* get the registerout param to know the successfull insertion. */
                if(callableStatement.getInt(50)<1) {
                    isTimesheetAddOrEdit = false;
                    //log.debug("The Error occur while inserting the TimeSheet for EmpId"+timeSheetAction.getEmpID());
                } else {
                    isTimesheetAddOrEdit = true;
                    //log.debug("The TimeSheet Successfully Updatd For EmpId:"+timeSheetAction.getEmpID()+"with TimeSheetId:"+callableStatement.getInt(52));
                }// if
                
                
            } //if
            
        } //try
        catch(Exception ex) {
            throw new ServiceLocatorException(ex);
            //log.setLevel((Level)Level.ERROR);
            // log.error("The Error @ editTimeSheet()",ex);
            
        } finally {
            try {
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
                // log.setLevel((Level)Level.ERROR);
                // log.error("The Error @ editTimeSheet()",ex);
            } //catch
        } // finally
        return isTimesheetAddOrEdit;
    } // @ editTimeSheet()
    
    
    
    public String getTeamMembersList(String LoginId) throws ServiceLocatorException {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String teamMembersList="'"+LoginId+"',";
        try {
            
            connection = ConnectionProvider.getInstance().getConnection();
            if(connection != null) {
                callableStatement = connection.prepareCall("{call spTeamTimeSheets(?,?)}");
                callableStatement.setString(1,LoginId);
                callableStatement.registerOutParameter(2,java.sql.Types.VARCHAR);
                
                callableStatement.execute() ;
                String str = callableStatement.getString(2);
                String[] temp;
                
                /* delimiter */
                String delimiter = ",";
                /* given string will be split by the argument delimiter provided. */
                temp = str.split(delimiter);
                /* print substrings */
                for(int i =0; i < temp.length ; i++) {
                    if(!"".equals(temp[i]) ) {
                        teamMembersList=teamMembersList+"'"+(temp[i]);
                        teamMembersList=teamMembersList+"'";
                        if(i!=temp.length-1) teamMembersList=teamMembersList+",";
                    }
                }
                // System.out.println("Team Members List IS"+teamMembersList.toString());
            } //if
            
        } //try
        catch(Exception ex) {
            throw new ServiceLocatorException(ex);
            
            
        } finally {
            try {
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
                
            } //catch
        } // finally
        return teamMembersList;
    }
    
    
    
    
    /**
     *  This method can be used for checking timesheet exists based on the empId and
     * starting ,ending dates of the Timesheet.
     *
     *
     * @return isTimeSheetExist A boolean.
     * @param empID
     * @param li A java.util.List
     */
    public String checkTimeSheetExists(List li, String empID,String empType) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String isTimeSheetExist = "";
        try {
            // convert the date to string
            String wstDate = DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql((String)li.get(0)));
            String wenDate = DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql((String)li.get(1)));
            
            Date weekStarDate = DateUtility.getInstance().convertStringToMySql((String)li.get(0));
            Date currDate = DateUtility.getInstance().convertStringToMySql((String)li.get(2));
            
            connection = ConnectionProvider.getInstance().getConnection();
            
            if(connection != null) {
                statement = connection.createStatement();
                
                // select the data from vwTimeSheetList of DataBase.
                if("e".equalsIgnoreCase(empType))
                resultSet =statement.executeQuery("SELECT *  FROM vwTimeSheetList WHERE EmpId="+Integer.parseInt(empID)+" AND DateStart='"+wstDate+"' AND DateEnd='"+wenDate+"'");
                else
                  resultSet =statement.executeQuery("SELECT *  FROM vwCustTimeSheetList WHERE EmpId="+Integer.parseInt(empID)+" AND DateStart='"+wstDate+"' AND DateEnd='"+wenDate+"'");
                
                if(resultSet.next()) {
                    isTimeSheetExist = "exist";
                }else if(currDate.before(weekStarDate)){
                    isTimeSheetExist = "notallow";
                }else{
                    isTimeSheetExist = "allow";
                }
                
            } //if
            
            
        } // try
        catch(Exception ex) {
            ex.printStackTrace();
            //log.setLevel((Level)Level.ERROR);
            //log.error("The Error @ checkTimeSheetExists()",ex);
        } // catch
        finally{
            try {
              if(resultSet != null) {
                 resultSet.close();
                 resultSet = null;
                }
              if(statement != null) {
                 statement.close();
                 statement = null;
                }
            if(connection != null){
               connection.close();
               connection = null;
                } 
                
            }catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }
        
        return isTimeSheetExist;
    } // @checkTimeSheetExists
    /**
     * The deleteTimeSheet(int id) is used for deleting TimeSheet.
     *
     * @return int variable
     * @see com.mss.mirage.util.ServiceLocatorException
     * @param id
     * @param empId
     * @param timeSheetId
     * @throws ServiceLocatorException
     */
    public int deleteTimeSheet(int id, int empId, int timeSheetId) throws ServiceLocatorException{
        
       // System.out.println("in service impl");
        int deletedRows = 0;
        Connection connection = null;
        Statement statement = null;
        String queryString = "Delete from tblTimeSheets  WHERE id="+id;
        String queryString1 = "Delete from tblTimeSheetLines WHERE EmpId='"+empId+"' AND TimeSheetId='"+timeSheetId+"'";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            deletedRows = statement.executeUpdate(queryString);
            deletedRows = statement.executeUpdate(queryString1);
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(statement != null){
                    statement.close();
                    statement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return deletedRows;
    }
    
    /**
     *
     *  This method can be used to upload the timesheet
     * @param timeSheetAction
     * @return int
     * @throws com.mss.mirage.util.ServiceLocatorException
     * @throws java.sql.SQLException
     */
    public int uploadTimeSheet(TimeSheetAction timeSheetAction) throws ServiceLocatorException, SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        int updatedRows = 0;
        
        try {
            DateUtility dateUtil = DateUtility.getInstance();
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO tblEmpAttachments values(?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,0);
            preparedStatement.setInt(2,timeSheetAction.getObjectId());
            preparedStatement.setString(3,timeSheetAction.getObjectType());
            preparedStatement.setString(4,timeSheetAction.getAttachmentName());
            preparedStatement.setString(5,timeSheetAction.getFileLocation());
            preparedStatement.setString(6,timeSheetAction.getUploadFileName());
            preparedStatement.setTimestamp(7,dateUtil.getCurrentMySqlDateTime());
            updatedRows = preparedStatement.executeUpdate();
        } catch(SQLException se) {
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        }finally{
            try{
                 if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        
        return updatedRows;
    }
    
    /**
     *
     *  This method can be used to delete the timesheet
     * @param id
     * @return int
     * @throws com.mss.mirage.util.ServiceLocatorException
     */
    public int deleteOnsiteTimeSheet(int id) throws ServiceLocatorException {
        int deletedRows = 0;
        Connection connection = null;
        Statement statement = null;
        String queryString = "Delete from tblEmpAttachments WHERE Id="+id;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            deletedRows = statement.executeUpdate(queryString);
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(statement != null){
                    statement.close();
                    statement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return deletedRows;
    }
    public String generateEmpTimeSheetsReport(TimeSheetAction timeSheetAction,String empName,String query,HttpServletResponse httpServletResponse) throws ServiceLocatorException{
        
        RBMap=new HashMap();
        // RBMap.put("sdate",leaveReportAction.getStartDate());
        // RBMap.put("edate",leaveReportAction.getEndDate());
        RBMap.put("empName",empName);
        
        try {
            //System.out.println("FutureLeaves ::"+leaveReportAction.getFutureLeaves());
            
            jasperReport = JasperCompileManager.compileReport(TimesheetreportsSource);
            List dataList = getEmpTimesheetsStatusData(query);
            
            //System.out.println(usedLeaves+"%%%%%%%%"+appliedLeaves+"%%%%%%%%%%"+(avalLeaves-appliedLeaves));
            JRBeanCollectionDataSource jrxmlds = new JRBeanCollectionDataSource(getCollection(dataList));
            jasperPrint = JasperFillManager.fillReport(jasperReport, RBMap, jrxmlds);
            //JasperViewer.viewReport(jasperPrint);
            byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
            outStream = httpServletResponse.getOutputStream();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setContentLength(pdf.length);
            httpServletResponse.setHeader("Content-disposition","inline; filename=\"Report.pdf\"");
            outStream.write(pdf);
            outStream.flush();
            outStream.close();
            
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex.getMessage());
        }
        return reportResult;
    }
    
    
    
    public List getEmpTimesheetsStatusData(String query) throws ServiceLocatorException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List lt=null;
        HashMap map = null;
        finalList=new ArrayList();
        
        try {
            /* getting the connection from the ConnectionProvider */
            connection = ConnectionProvider.getInstance().getConnection();
            if(connection!=null) {
                preparedStatement = connection.prepareStatement(query);
                //System.err.println("Query---"+query);
                /* taking the info from the tblTimeSheets based on the empId and timeSheet Id */
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    map = new HashMap();
                    String EmpName      = resultSet.getString("EmpName");
                    String Description      = resultSet.getString("STATUS");
                    String departmentid  = resultSet.getString("departmentid");
                    String datestart     = resultSet.getDate("DateStart").toString();
                    String enddate  = resultSet.getDate("DateEnd").toString();
                    // Date SubmittedDate = resultSet.getDate("SubmittedDate");
                    Date ApprovedDate = resultSet.getDate("ApprovedDate");
                    String WorkDate = resultSet.getDate("WorkDate").toString();
                    String P1Hrs      = resultSet.getString("Prj1Hrs");
                    String P2Hrs      = resultSet.getString("Prj2Hrs");
                    String InHrs      = resultSet.getString("InternalHrs");
                    String VaccHrs    = resultSet.getString("VacationHrs");
                    String HolidHrs   = resultSet.getString("HolidayHrs");
                    
                    map.put("EmpName",EmpName);
                    map.put("Description",Description);
                    map.put("departmentid",departmentid);
                    map.put("datestart",datestart);
                    map.put("enddate",enddate);
                    //  map.put("SubmittedDate",SubmittedDate);
                    map.put("ApprovedDate",ApprovedDate);
                    map.put("WrkDt",WorkDate);
                    map.put("P1Hrs",P1Hrs);
                    map.put("P2Hrs",P2Hrs);
                    map.put("InHrs",InHrs);
                    map.put("VaccHrs",VaccHrs);
                    map.put("HolidHrs",HolidHrs);
                    
                    finalList.add(map);
                }
                
            }
        } catch(Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }
        }
        return finalList;
    }
    
    
    
    public Collection getCollection(Object obj){
        List list=(ArrayList)obj;
        return list;
    }

    
} // TimeSheetServiceImpl
