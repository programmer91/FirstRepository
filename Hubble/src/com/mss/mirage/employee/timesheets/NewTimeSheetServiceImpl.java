/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.timesheets;

import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.Properties;
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

/**
 *
 * @author miracle
 */
public class NewTimeSheetServiceImpl implements NewTimeSheetService{
    
    
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
        zeroForMon = (cal.get(Calendar.MONTH) + 1);
        zeroForDay = cal.get(Calendar.DAY_OF_MONTH);
        if (zeroForMon < 10 && zeroForDay < 10) {
            curntDate = "0" +(currDay.get(Calendar.MONTH) + 1) + "/0" + currDay.get(Calendar.DAY_OF_MONTH) + "/" + currDay.get(Calendar.YEAR);

        } else if (zeroForMon > 9 && zeroForDay < 10) {
             curntDate = (currDay.get(Calendar.MONTH) + 1) + "/0" + currDay.get(Calendar.DAY_OF_MONTH) + "/" + currDay.get(Calendar.YEAR);
        } else if (zeroForMon < 10 && zeroForDay > 9) {
             curntDate =  "0" + (currDay.get(Calendar.MONTH) + 1) + "/" + currDay.get(Calendar.DAY_OF_MONTH) + "/" + currDay.get(Calendar.YEAR);
        } else {
             curntDate = (currDay.get(Calendar.MONTH) + 1) + "/" + currDay.get(Calendar.DAY_OF_MONTH) + "/" + currDay.get(Calendar.YEAR);

        }
      //  curntDate = (currDay.get(Calendar.MONTH)+1) + "/" + currDay.get(Calendar.DAY_OF_MONTH)+"/"+currDay.get(Calendar.YEAR) ;
        daysList.add(wstDate);
        daysList.add(wenDate);
        daysList.add(curntDate);
        daysList.add(weekDays);
        
        return daysList;
    } // @ getweekStartAndEndDays(Calendar cal)
    
     
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
    
      
       public NewTimeSheetVTO getWeekDaysBean(List li) {
        NewTimeSheetVTO timeSheetVTO = new  NewTimeSheetVTO();
        
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
        //timeSheetVTO.setModeType("AddTimeSheet");
        timeSheetVTO.setModeType("newAddTimeSheet");
        
        return timeSheetVTO; //  return the bean reference
    } // @ getWeekDaysBean(List)
      
       
       
public int newAddTimeSheet(NewTimeSheetAction timeSheetAction) throws ServiceLocatorException {
        
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
                callableStatement = connection.prepareCall("{call spNewTimeSheetAdd(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
                
                  callableStatement.setDouble(23,timeSheetAction.getProj3Sun());
                callableStatement.setDouble(24,timeSheetAction.getProj3Mon());
                callableStatement.setDouble(25,timeSheetAction.getProj3Tus());
                callableStatement.setDouble(26,timeSheetAction.getProj3Wed());
                callableStatement.setDouble(27,timeSheetAction.getProj3Thur());
                callableStatement.setDouble(28,timeSheetAction.getProj3Fri());
                callableStatement.setDouble(29,timeSheetAction.getProj3Sat());
                
                 callableStatement.setDouble(30,timeSheetAction.getProj4Sun());
                callableStatement.setDouble(31,timeSheetAction.getProj4Mon());
                callableStatement.setDouble(32,timeSheetAction.getProj4Tus());
                callableStatement.setDouble(33,timeSheetAction.getProj4Wed());
                callableStatement.setDouble(34,timeSheetAction.getProj4Thur());
                callableStatement.setDouble(35,timeSheetAction.getProj4Fri());
                callableStatement.setDouble(36,timeSheetAction.getProj4Sat());
                
                callableStatement.setDouble(37,timeSheetAction.getProj5Sun());
                callableStatement.setDouble(38,timeSheetAction.getProj5Mon());
                callableStatement.setDouble(39,timeSheetAction.getProj5Tus());
                callableStatement.setDouble(40,timeSheetAction.getProj5Wed());
                callableStatement.setDouble(41,timeSheetAction.getProj5Thur());
                callableStatement.setDouble(42,timeSheetAction.getProj5Fri());
                callableStatement.setDouble(43,timeSheetAction.getProj5Sat());
                
                
                
                callableStatement.setDouble(44,timeSheetAction.getInternalSun());
                callableStatement.setDouble(45,timeSheetAction.getInternalMon());
                callableStatement.setDouble(46,timeSheetAction.getInternalTus());
                callableStatement.setDouble(47,timeSheetAction.getInternalWed());
                callableStatement.setDouble(48,timeSheetAction.getInternalThur());
                callableStatement.setDouble(49,timeSheetAction.getInternalFri());
                callableStatement.setDouble(50,timeSheetAction.getInternalSat());
                callableStatement.setDouble(51,timeSheetAction.getVacationSun());
                callableStatement.setDouble(52,timeSheetAction.getVacationMon());
                callableStatement.setDouble(53,timeSheetAction.getVacationTus());
                callableStatement.setDouble(54,timeSheetAction.getVacationWed());
                callableStatement.setDouble(55,timeSheetAction.getVacationThur());
                callableStatement.setDouble(56,timeSheetAction.getVacationFri());
                callableStatement.setDouble(57,timeSheetAction.getVacationSat());
                callableStatement.setDouble(58,timeSheetAction.getHoliSun());
                callableStatement.setDouble(59,timeSheetAction.getHoliMon());
                callableStatement.setDouble(60,timeSheetAction.getHoliTus());
                callableStatement.setDouble(61,timeSheetAction.getHoliWed());
                callableStatement.setDouble(62,timeSheetAction.getHoliThur());
                callableStatement.setDouble(63,timeSheetAction.getHoliFri());
                callableStatement.setDouble(64,timeSheetAction.getHoliSat());
                
                 callableStatement.setDouble(65,timeSheetAction.getCompSun());
                callableStatement.setDouble(66,timeSheetAction.getCompMon());
                callableStatement.setDouble(67,timeSheetAction.getCompTus());
                callableStatement.setDouble(68,timeSheetAction.getCompWed());
                callableStatement.setDouble(69,timeSheetAction.getCompThur());
                callableStatement.setDouble(70,timeSheetAction.getCompFri());
                callableStatement.setDouble(71,timeSheetAction.getCompSat());
                
                // converting the String to sql Date object.
                callableStatement.setDate(72,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate1()));
                callableStatement.setDate(73,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate2()));
                callableStatement.setDate(74,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate3()));
                callableStatement.setDate(75,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate4()));
                callableStatement.setDate(76,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate5()));
                callableStatement.setDate(77,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate6()));
                callableStatement.setDate(78,DateUtility.getInstance().getMysqlDate(timeSheetAction.getWeekDate7()));
                callableStatement.setString(79,timeSheetAction.getTxtNotes());
                callableStatement.setString(80,"Ins");
                callableStatement.setInt(81,timeSheetAction.getPriProjId());//inserting project id as 0
                callableStatement.setInt(82,timeSheetAction.getProj1Id());
                callableStatement.setInt(83,timeSheetAction.getProj2Id());
                callableStatement.setInt(84,timeSheetAction.getProj3Id());
                callableStatement.setInt(85,timeSheetAction.getProj4Id());
                callableStatement.setInt(86,timeSheetAction.getProj5Id());
                
                
                callableStatement.setDouble(87,timeSheetAction.getBiometricSun());
                callableStatement.setDouble(88,timeSheetAction.getBiometricMon());
                callableStatement.setDouble(89,timeSheetAction.getBiometricTus());
                callableStatement.setDouble(90,timeSheetAction.getBiometricWed());
                callableStatement.setDouble(91,timeSheetAction.getBiometricThur());
                callableStatement.setDouble(92,timeSheetAction.getBiometricFri());
                callableStatement.setDouble(93,timeSheetAction.getBiometricSat());
                callableStatement.setDouble(94,timeSheetAction.getBiometricTotalHrs());
                
                callableStatement.registerOutParameter(95,java.sql.Types.INTEGER);
                callableStatement.execute() ;
                int timeSheetId = callableStatement.getInt(95);
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
           // ex.printStackTrace();
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


public NewTimeSheetVTO newGetTimeSheet(int empId, int timeSheetID,String empType,String resourceType) {
        NewTimeSheetVTO timeSheetVTO = new NewTimeSheetVTO();
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
            //   resultSet = statement.executeQuery("SELECT EmpId,DateStart,DateEnd,SubmittedDate,ApprovedDate,TotalHrs,Notes,ProjectId  FROM tblTimeSheets WHERE EmpId="+empId+" AND TimeSheetId="+timeSheetID+"");
                 resultSet = statement.executeQuery("SELECT EmpId,DateStart,DateEnd,SubmittedDate,ApprovedDate,TotalHrs,Notes,ProjectId,TimeSheetStatusTypeId,SecondReportsToStatusTypeId,FileFlag,BmFlag FROM tblTimeSheets WHERE EmpId="+empId+" AND TimeSheetId="+timeSheetID+"");
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
                    //timeSheetVTO.setProjectId(resultSet.getInt(8));
                    timeSheetVTO.setPriProjId(resultSet.getInt(8));
                      timeSheetVTO.setTimeSheetStatusTypeId(resultSet.getInt(9));
                    timeSheetVTO.setSecondReportsToStatusTypeId(resultSet.getInt(10));
                     timeSheetVTO.setFileFlagValue(resultSet.getInt(11));
                     timeSheetVTO.setBiometricFlag(resultSet.getInt(12));

                } // while
                resultSet.close(); // clsoing connection
                resultSet = null;
                /* from tblTimeSheetLines getting full week of the TimeSheet */
               // resultSet = statement.executeQuery("SELECT WorkDate,Prj1Hrs,Prj2Hrs,InternalHrs,VacationHrs,HolidayHrs  FROM tblTimeSheetLines WHERE EmpId="+empId+" AND TimeSheetId="+timeSheetID+"");
                // resultSet = statement.executeQuery("SELECT WorkDate,Prj1Hrs,Prj2Hrs,InternalHrs,VacationHrs,HolidayHrs,CmtHrs,Prj3Hrs,Prj4Hrs,Prj5Hrs,PrjId1,PrjId2,PrjId3,PrjId4,PrjId5 FROM tblTimeSheetLines WHERE EmpId="+empId+" AND TimeSheetId="+timeSheetID+"");
                 resultSet = statement.executeQuery("SELECT WorkDate,Prj1Hrs,Prj2Hrs,InternalHrs,VacationHrs,HolidayHrs,CmtHrs,Prj3Hrs,Prj4Hrs,Prj5Hrs,PrjId1,PrjId2,PrjId3,PrjId4,PrjId5,BmHrs,BmStatus FROM tblTimeSheetLines WHERE EmpId="+empId+" AND TimeSheetId="+timeSheetID+"");
                
                while(resultSet.next()) {
                    
                    /* Work days of sunday */
                    if(rowCount==1) {
                        timeSheetVTO.setWeekDate1(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Sun(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Sun(resultSet.getDouble(3));
                        timeSheetVTO.setInternalSun(resultSet.getDouble(4));
                        timeSheetVTO.setVacationSun(resultSet.getDouble(5));
                        timeSheetVTO.setHoliSun(resultSet.getDouble(6));
                        timeSheetVTO.setCompSun(resultSet.getDouble(7));
                        timeSheetVTO.setProj3Sun(resultSet.getDouble(8));
                        timeSheetVTO.setProj4Sun(resultSet.getDouble(9));
                        timeSheetVTO.setProj5Sun(resultSet.getDouble(10));
                        timeSheetVTO.setProj1Id(resultSet.getInt(11));
                        timeSheetVTO.setProj2Id(resultSet.getInt(12));
                        timeSheetVTO.setProj3Id(resultSet.getInt(13));
                        timeSheetVTO.setProj4Id(resultSet.getInt(14));
                        timeSheetVTO.setProj5Id(resultSet.getInt(15));
                        timeSheetVTO.setBiometricSun(resultSet.getDouble(16));
                        timeSheetVTO.setBmSunStatus(resultSet.getString(17));
                    } else if(rowCount==2) {
                        
                        /* Work days of monday */
                        timeSheetVTO.setWeekDate2(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Mon(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Mon(resultSet.getDouble(3));
                        timeSheetVTO.setInternalMon(resultSet.getDouble(4));
                        timeSheetVTO.setVacationMon(resultSet.getDouble(5));
                        timeSheetVTO.setHoliMon(resultSet.getDouble(6));
                        timeSheetVTO.setCompMon(resultSet.getDouble(7));
                         timeSheetVTO.setProj3Mon(resultSet.getDouble(8));
                        timeSheetVTO.setProj4Mon(resultSet.getDouble(9));
                        timeSheetVTO.setProj5Mon(resultSet.getDouble(10));
                        timeSheetVTO.setProj1Id(resultSet.getInt(11));
                        timeSheetVTO.setProj2Id(resultSet.getInt(12));
                        timeSheetVTO.setProj3Id(resultSet.getInt(13));
                        timeSheetVTO.setProj4Id(resultSet.getInt(14));
                        timeSheetVTO.setProj5Id(resultSet.getInt(15));
                        timeSheetVTO.setBiometricMon(resultSet.getDouble(16));
                        timeSheetVTO.setBmMonStatus(resultSet.getString(17));
                        
                    } else if(rowCount==3) {
                        
                        /* Work days of Tuesday */
                        timeSheetVTO.setWeekDate3(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Tus(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Tus(resultSet.getDouble(3));
                        timeSheetVTO.setInternalTus(resultSet.getDouble(4));
                        timeSheetVTO.setVacationTus(resultSet.getDouble(5));
                        timeSheetVTO.setHoliTus(resultSet.getDouble(6));
                        timeSheetVTO.setCompTus(resultSet.getDouble(7));
                        
                        timeSheetVTO.setProj3Tus(resultSet.getDouble(8));
                        timeSheetVTO.setProj4Tus(resultSet.getDouble(9));
                        timeSheetVTO.setProj5Tus(resultSet.getDouble(10));
                        timeSheetVTO.setProj1Id(resultSet.getInt(11));
                        timeSheetVTO.setProj2Id(resultSet.getInt(12));
                        timeSheetVTO.setProj3Id(resultSet.getInt(13));
                        timeSheetVTO.setProj4Id(resultSet.getInt(14));
                        timeSheetVTO.setProj5Id(resultSet.getInt(15));
                        timeSheetVTO.setBiometricTus(resultSet.getDouble(16));
                        timeSheetVTO.setBmTusStatus(resultSet.getString(17));
                    } else if(rowCount==4) {
                        
                        /* Work days of wednessDay */
                        timeSheetVTO.setWeekDate4(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Wed(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Wed(resultSet.getDouble(3));
                        timeSheetVTO.setInternalWed(resultSet.getDouble(4));
                        timeSheetVTO.setVacationWed(resultSet.getDouble(5));
                        timeSheetVTO.setHoliWed(resultSet.getDouble(6));
                        timeSheetVTO.setCompWed(resultSet.getDouble(7));
                        
                        timeSheetVTO.setProj3Wed(resultSet.getDouble(8));
                        timeSheetVTO.setProj4Wed(resultSet.getDouble(9));
                        timeSheetVTO.setProj5Wed(resultSet.getDouble(10));
                        timeSheetVTO.setProj1Id(resultSet.getInt(11));
                        timeSheetVTO.setProj2Id(resultSet.getInt(12));
                        timeSheetVTO.setProj3Id(resultSet.getInt(13));
                        timeSheetVTO.setProj4Id(resultSet.getInt(14));
                        timeSheetVTO.setProj5Id(resultSet.getInt(15));
                        timeSheetVTO.setBiometricWed(resultSet.getDouble(16));
                        timeSheetVTO.setBmWedStatus(resultSet.getString(17));
                        
                    } else if(rowCount==5) {
                        
                        /* Work days of ThursDay */
                        timeSheetVTO.setWeekDate5(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Thur(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Thur(resultSet.getDouble(3));
                        timeSheetVTO.setInternalThur(resultSet.getDouble(4));
                        timeSheetVTO.setVacationThur(resultSet.getDouble(5));
                        timeSheetVTO.setHoliThur(resultSet.getDouble(6));
                        timeSheetVTO.setCompThur(resultSet.getDouble(7));
                        
                         timeSheetVTO.setProj3Thur(resultSet.getDouble(8));
                        timeSheetVTO.setProj4Thur(resultSet.getDouble(9));
                        timeSheetVTO.setProj5Thur(resultSet.getDouble(10));
                        timeSheetVTO.setProj1Id(resultSet.getInt(11));
                        timeSheetVTO.setProj2Id(resultSet.getInt(12));
                        timeSheetVTO.setProj3Id(resultSet.getInt(13));
                        timeSheetVTO.setProj4Id(resultSet.getInt(14));
                        timeSheetVTO.setProj5Id(resultSet.getInt(15));
                        timeSheetVTO.setBiometricThur(resultSet.getDouble(16));
                        timeSheetVTO.setBmThurStatus(resultSet.getString(17));
                    } else if(rowCount==6) {
                        
                        /* Work days of Friday */
                        timeSheetVTO.setWeekDate6(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Fri(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Fri(resultSet.getDouble(3));
                        timeSheetVTO.setInternalFri(resultSet.getDouble(4));
                        timeSheetVTO.setVacationFri(resultSet.getDouble(5));
                        timeSheetVTO.setHoliFri(resultSet.getDouble(6));
                        timeSheetVTO.setCompFri(resultSet.getDouble(7));
                        
                         timeSheetVTO.setProj3Fri(resultSet.getDouble(8));
                        timeSheetVTO.setProj4Fri(resultSet.getDouble(9));
                        timeSheetVTO.setProj5Fri(resultSet.getDouble(10));
                        timeSheetVTO.setProj1Id(resultSet.getInt(11));
                        timeSheetVTO.setProj2Id(resultSet.getInt(12));
                        timeSheetVTO.setProj3Id(resultSet.getInt(13));
                        timeSheetVTO.setProj4Id(resultSet.getInt(14));
                        timeSheetVTO.setProj5Id(resultSet.getInt(15));
                        timeSheetVTO.setBiometricFri(resultSet.getDouble(16));
                        timeSheetVTO.setBmFriStatus(resultSet.getString(17));
                    } else if(rowCount==7) {
                        
                        /* Work days of Saturday */
                        timeSheetVTO.setWeekDate7(DateUtility.getInstance().convertDateToView(resultSet.getDate(1)));
                        timeSheetVTO.setProj1Sat(resultSet.getDouble(2));
                        timeSheetVTO.setProj2Sat(resultSet.getDouble(3));
                        timeSheetVTO.setInternalSat(resultSet.getDouble(4));
                        timeSheetVTO.setVacationSat(resultSet.getDouble(5));
                        timeSheetVTO.setHoliSat(resultSet.getDouble(6));
                        timeSheetVTO.setCompSat(resultSet.getDouble(7));
                        
                        timeSheetVTO.setProj3Sat(resultSet.getDouble(8));
                        timeSheetVTO.setProj4Sat(resultSet.getDouble(9));
                        timeSheetVTO.setProj5Sat(resultSet.getDouble(10));
                        timeSheetVTO.setProj1Id(resultSet.getInt(11));
                        timeSheetVTO.setProj2Id(resultSet.getInt(12));
                        timeSheetVTO.setProj3Id(resultSet.getInt(13));
                        timeSheetVTO.setProj4Id(resultSet.getInt(14));
                        timeSheetVTO.setProj5Id(resultSet.getInt(15));
                        timeSheetVTO.setBiometricSat(resultSet.getDouble(16));
                        timeSheetVTO.setBmSatStatus(resultSet.getString(17));
                    }
                    rowCount++;
                } // while
                
                
                timeSheetVTO.setProj1Name(DataSourceDataProvider.getInstance().getProjectName(timeSheetVTO.getProj1Id()));
                timeSheetVTO.setProj2Name(DataSourceDataProvider.getInstance().getProjectName(timeSheetVTO.getProj2Id()));
                timeSheetVTO.setProj3Name(DataSourceDataProvider.getInstance().getProjectName(timeSheetVTO.getProj3Id()));
                timeSheetVTO.setProj4Name(DataSourceDataProvider.getInstance().getProjectName(timeSheetVTO.getProj4Id()));
                timeSheetVTO.setProj5Name(DataSourceDataProvider.getInstance().getProjectName(timeSheetVTO.getProj5Id()));
                
                resultSet.close(); // clsoing connection
                resultSet = null;
                // for setting the Action Type name
                timeSheetVTO.setModeType("NewEditTimesheet");
                
                /* calculation of the individual day of total working , billing , vaction and holidays sums*/
                timeSheetVTO.setTotalSun((timeSheetVTO.getProj1Sun()+timeSheetVTO.getProj2Sun()+timeSheetVTO.getProj3Sun()+timeSheetVTO.getProj4Sun()+timeSheetVTO.getProj5Sun()+timeSheetVTO.getInternalSun()+timeSheetVTO.getVacationSun()+timeSheetVTO.getHoliSun()+timeSheetVTO.getCompSun()));
                timeSheetVTO.setTotalMon((timeSheetVTO.getProj1Mon()+timeSheetVTO.getProj2Mon()+timeSheetVTO.getProj3Mon()+timeSheetVTO.getProj4Mon()+timeSheetVTO.getProj5Mon()+timeSheetVTO.getInternalMon()+timeSheetVTO.getVacationMon()+timeSheetVTO.getHoliMon()+timeSheetVTO.getCompMon()));
                timeSheetVTO.setTotalTus((timeSheetVTO.getProj1Tus()+timeSheetVTO.getProj2Tus()+timeSheetVTO.getProj3Tus()+timeSheetVTO.getProj4Tus()+timeSheetVTO.getProj5Tus()+timeSheetVTO.getInternalTus()+timeSheetVTO.getVacationTus()+timeSheetVTO.getHoliTus()+timeSheetVTO.getCompTus()));
                timeSheetVTO.setTotalWed((timeSheetVTO.getProj1Wed()+timeSheetVTO.getProj2Wed()+timeSheetVTO.getProj3Wed()+timeSheetVTO.getProj4Wed()+timeSheetVTO.getProj5Wed()+timeSheetVTO.getInternalWed()+timeSheetVTO.getVacationWed()+timeSheetVTO.getHoliWed()+timeSheetVTO.getCompWed()));
                timeSheetVTO.setTotalThur((timeSheetVTO.getProj1Thur()+timeSheetVTO.getProj2Thur()+timeSheetVTO.getProj3Thur()+timeSheetVTO.getProj4Thur()+timeSheetVTO.getProj5Thur()+timeSheetVTO.getInternalThur()+timeSheetVTO.getVacationThur()+timeSheetVTO.getHoliThur()+timeSheetVTO.getCompThur()));
                timeSheetVTO.setTotalFri((timeSheetVTO.getProj1Fri()+timeSheetVTO.getProj2Fri()+timeSheetVTO.getProj3Fri()+timeSheetVTO.getProj4Fri()+timeSheetVTO.getProj5Fri()+timeSheetVTO.getInternalFri()+timeSheetVTO.getVacationFri()+timeSheetVTO.getHoliFri()+timeSheetVTO.getCompFri()));
                timeSheetVTO.setTotalSat((timeSheetVTO.getProj1Sat()+timeSheetVTO.getProj2Sat()+timeSheetVTO.getProj3Sat()+timeSheetVTO.getProj4Sat()+timeSheetVTO.getProj5Sat()+timeSheetVTO.getInternalSat()+timeSheetVTO.getVacationSat()+timeSheetVTO.getHoliSat()+timeSheetVTO.getCompSat()));
                timeSheetVTO.setProj1TotalHrs((timeSheetVTO.getProj1Tus()+timeSheetVTO.getProj1Sun()+timeSheetVTO.getProj1Mon()+timeSheetVTO.getProj1Wed()+timeSheetVTO.getProj1Thur()+timeSheetVTO.getProj1Fri()+timeSheetVTO.getProj1Sat()));
                timeSheetVTO.setProj2TotalHrs((timeSheetVTO.getProj2Tus()+timeSheetVTO.getProj2Sun()+timeSheetVTO.getProj2Mon()+timeSheetVTO.getProj2Wed()+timeSheetVTO.getProj2Thur()+timeSheetVTO.getProj2Fri()+timeSheetVTO.getProj2Sat()));
                
                 timeSheetVTO.setProj3TotalHrs((timeSheetVTO.getProj3Tus()+timeSheetVTO.getProj3Sun()+timeSheetVTO.getProj3Mon()+timeSheetVTO.getProj3Wed()+timeSheetVTO.getProj3Thur()+timeSheetVTO.getProj3Fri()+timeSheetVTO.getProj3Sat()));
                  timeSheetVTO.setProj4TotalHrs((timeSheetVTO.getProj4Tus()+timeSheetVTO.getProj4Sun()+timeSheetVTO.getProj4Mon()+timeSheetVTO.getProj4Wed()+timeSheetVTO.getProj4Thur()+timeSheetVTO.getProj4Fri()+timeSheetVTO.getProj4Sat()));
                   timeSheetVTO.setProj5TotalHrs((timeSheetVTO.getProj5Tus()+timeSheetVTO.getProj5Sun()+timeSheetVTO.getProj5Mon()+timeSheetVTO.getProj5Wed()+timeSheetVTO.getProj5Thur()+timeSheetVTO.getProj5Fri()+timeSheetVTO.getProj5Sat()));
                
                
                timeSheetVTO.setInternalTotalHrs((timeSheetVTO.getInternalSun()+timeSheetVTO.getInternalMon()+timeSheetVTO.getInternalTus()+timeSheetVTO.getInternalWed()+timeSheetVTO.getInternalThur()+timeSheetVTO.getInternalFri()+timeSheetVTO.getInternalSat()));
                timeSheetVTO.setVacationTotalHrs((timeSheetVTO.getVacationSun()+timeSheetVTO.getVacationMon()+timeSheetVTO.getVacationTus()+timeSheetVTO.getVacationWed()+timeSheetVTO.getVacationThur()+timeSheetVTO.getVacationFri()+timeSheetVTO.getVacationSat()));
                timeSheetVTO.setHoliTotalHrs((timeSheetVTO.getHoliSun()+timeSheetVTO.getHoliMon()+timeSheetVTO.getHoliTus()+timeSheetVTO.getHoliWed()+timeSheetVTO.getHoliThur()+timeSheetVTO.getHoliFri()+timeSheetVTO.getHoliSat()));
                timeSheetVTO.setCompTotalHrs((timeSheetVTO.getCompSun()+timeSheetVTO.getCompMon()+timeSheetVTO.getCompTus()+timeSheetVTO.getCompWed()+timeSheetVTO.getCompThur()+timeSheetVTO.getCompFri()+timeSheetVTO.getCompSat()));
                timeSheetVTO.setAllWeekendTotalHors((timeSheetVTO.getProj1TotalHrs()+timeSheetVTO.getProj2TotalHrs()+timeSheetVTO.getProj3TotalHrs()+timeSheetVTO.getProj4TotalHrs()+timeSheetVTO.getProj5TotalHrs()+timeSheetVTO.getInternalTotalHrs()+timeSheetVTO.getVacationTotalHrs()+timeSheetVTO.getHoliTotalHrs()+timeSheetVTO.getCompTotalHrs()));
                timeSheetVTO.setTimeSheetID(String.valueOf(timeSheetID));
                timeSheetVTO.setBiometricTotalHrs(timeSheetVTO.getBiometricSun()+timeSheetVTO.getBiometricMon()+timeSheetVTO.getBiometricTus()+timeSheetVTO.getBiometricWed()+timeSheetVTO.getBiometricThur()+timeSheetVTO.getBiometricFri()+timeSheetVTO.getBiometricSat());
                // System.out.println("empType-->"+empType);
                // System.out.println("resourceType-->"+resourceType);
                if(empType.equalsIgnoreCase("e")){
                if(!(resourceType!=null)){
                     //queryString = "SELECT EmpName from vwTimeSheetList where EmpId='"+empId+"'";
                    queryString = "SELECT EmpName from vwNewTimeSheetList where EmpId='"+empId+"'";
                }
                else if(resourceType.equalsIgnoreCase("e"))
                    //queryString = "SELECT EmpName from vwTimeSheetList where EmpId='"+empId+"'";
                    queryString = "SELECT EmpName from vwNewTimeSheetList where EmpId='"+empId+"'";
                    else 
                        //queryString = "SELECT EmpName from vwCustTimeSheetList where EmpId='"+empId+"'";
                    queryString = "SELECT EmpName from vwNewCustTimeSheetList where EmpId='"+empId+"'";
                
                }else{
                //queryString = "SELECT EmpName from vwCustTimeSheetList where EmpId='"+empId+"'";
                    queryString = "SELECT EmpName from vwNewCustTimeSheetList where EmpId='"+empId+"'";
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
                // Dual change
                 boolean isDualReq = false;
               if(timeSheetVTO.getPriProjId()!=0){
                     resultSet = statement.executeQuery("SELECT Dualreporting FROM tblProjects WHERE Id ="+timeSheetVTO.getPriProjId());
                resultSet.next();
                isDualReq = resultSet.getBoolean("Dualreporting");
                //timeSheetVTO.setIsDualReportingRequired(resultSet.getBoolean("Dualreporting"));
                 resultSet.close(); // clsoing connection
                resultSet = null;
               }
               timeSheetVTO.setIsDualReportingRequired(isDualReq);
                
                
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
     public boolean newEditTimeSheet(NewTimeSheetAction timeSheetAction) throws ServiceLocatorException {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean isTimesheetAddOrEdit = false;
        try {
            
            connection = ConnectionProvider.getInstance().getConnection();
            if(connection != null) {
                callableStatement = connection.prepareCall("{call spNewTimeSheetEdit(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
                
                  callableStatement.setDouble(19,timeSheetAction.getProj3Sun());
                callableStatement.setDouble(20,timeSheetAction.getProj3Mon());
                callableStatement.setDouble(21,timeSheetAction.getProj3Tus());
                callableStatement.setDouble(22,timeSheetAction.getProj3Wed());
                callableStatement.setDouble(23,timeSheetAction.getProj3Thur());
                callableStatement.setDouble(24,timeSheetAction.getProj3Fri());
                callableStatement.setDouble(25,timeSheetAction.getProj3Sat());
                
                  callableStatement.setDouble(26,timeSheetAction.getProj4Sun());
                callableStatement.setDouble(27,timeSheetAction.getProj4Mon());
                callableStatement.setDouble(28,timeSheetAction.getProj4Tus());
                callableStatement.setDouble(29,timeSheetAction.getProj4Wed());
                callableStatement.setDouble(30,timeSheetAction.getProj4Thur());
                callableStatement.setDouble(31,timeSheetAction.getProj4Fri());
                callableStatement.setDouble(32,timeSheetAction.getProj4Sat());
                
                  callableStatement.setDouble(33,timeSheetAction.getProj5Sun());
                callableStatement.setDouble(34,timeSheetAction.getProj5Mon());
                callableStatement.setDouble(35,timeSheetAction.getProj5Tus());
                callableStatement.setDouble(36,timeSheetAction.getProj5Wed());
                callableStatement.setDouble(37,timeSheetAction.getProj5Thur());
                callableStatement.setDouble(38,timeSheetAction.getProj5Fri());
                callableStatement.setDouble(39,timeSheetAction.getProj5Sat());
                
                
                callableStatement.setDouble(40,timeSheetAction.getInternalSun());
                callableStatement.setDouble(41,timeSheetAction.getInternalMon());
                callableStatement.setDouble(42,timeSheetAction.getInternalTus());
                callableStatement.setDouble(43,timeSheetAction.getInternalWed());
                callableStatement.setDouble(44,timeSheetAction.getInternalThur());
                callableStatement.setDouble(45,timeSheetAction.getInternalFri());
                callableStatement.setDouble(46,timeSheetAction.getInternalSat());
                callableStatement.setDouble(47,timeSheetAction.getVacationSun());
                callableStatement.setDouble(48,timeSheetAction.getVacationMon());
                callableStatement.setDouble(49,timeSheetAction.getVacationTus());
                callableStatement.setDouble(50,timeSheetAction.getVacationWed());
                callableStatement.setDouble(51,timeSheetAction.getVacationThur());
                callableStatement.setDouble(52,timeSheetAction.getVacationFri());
                callableStatement.setDouble(53,timeSheetAction.getVacationSat());
                callableStatement.setDouble(54,timeSheetAction.getHoliSun());
                callableStatement.setDouble(55,timeSheetAction.getHoliMon());
                callableStatement.setDouble(56,timeSheetAction.getHoliTus());
                callableStatement.setDouble(57,timeSheetAction.getHoliWed());
                callableStatement.setDouble(58,timeSheetAction.getHoliThur());
                callableStatement.setDouble(59,timeSheetAction.getHoliFri());
                callableStatement.setDouble(60,timeSheetAction.getHoliSat());
                
                 callableStatement.setDouble(61,timeSheetAction.getCompSun());
                callableStatement.setDouble(62,timeSheetAction.getCompMon());
                callableStatement.setDouble(63,timeSheetAction.getCompTus());
                callableStatement.setDouble(64,timeSheetAction.getCompWed());
                callableStatement.setDouble(65,timeSheetAction.getCompThur());
                callableStatement.setDouble(66,timeSheetAction.getCompFri());
                callableStatement.setDouble(67,timeSheetAction.getCompSat());
                
                // converting the String to sql Date object.
                callableStatement.setString(68,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate1().trim())));
                callableStatement.setString(69,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate2().trim())));
                callableStatement.setString(70,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate3().trim())));
                callableStatement.setString(71,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate4().trim())));
                callableStatement.setString(72,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate5().trim())));
                callableStatement.setString(73,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate6().trim())));
                callableStatement.setString(74,DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(timeSheetAction.getWeekDate7().trim())));
                callableStatement.setString(75,timeSheetAction.getTxtNotes().trim());
                callableStatement.setInt(76,timeSheetAction.getTempVar());
                callableStatement.setString(77,timeSheetAction.getEmpType());
                
                callableStatement.setInt(78,timeSheetAction.getProj1Id());
                callableStatement.setInt(79,timeSheetAction.getProj2Id());
                callableStatement.setInt(80,timeSheetAction.getProj3Id());
                callableStatement.setInt(81,timeSheetAction.getProj4Id());
                callableStatement.setInt(82,timeSheetAction.getProj5Id());
                
                callableStatement.setDouble(83,timeSheetAction.getBiometricSun());
                callableStatement.setDouble(84,timeSheetAction.getBiometricMon());
                callableStatement.setDouble(85,timeSheetAction.getBiometricTus());
                callableStatement.setDouble(86,timeSheetAction.getBiometricWed());
                callableStatement.setDouble(87,timeSheetAction.getBiometricThur());
                callableStatement.setDouble(88,timeSheetAction.getBiometricFri());
                callableStatement.setDouble(89,timeSheetAction.getBiometricSat());
                callableStatement.setDouble(90,timeSheetAction.getBiometricTotalHrs());
                
                callableStatement.registerOutParameter(91,java.sql.Types.INTEGER);
                callableStatement.execute() ;
                
                /* get the registerout param to know the successfull insertion. */
                if(callableStatement.getInt(91)<1) {
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
    
   /*   public String getSubmitTimeSheetEmailLogBody(String empName,String wstDate,String wenDate,String empId,String reportsToType,int timeSheetID,String resourceType,String description) throws ServiceLocatorException{
        
       // System.out.println("in service impl");
        int deletedRows = 0;
      //  Connection connection = null;
      //  Statement statement = null;
       // String queryString = "Delete from tblTimeSheets  WHERE id="+id;
      StringBuffer htmlText = new StringBuffer();
       
        try{
            
            
            
             htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>A TimeSheet has been submitted by "+empName+" <br>");
                htmlText.append("<p><u>Time Sheet Details:</u><br>");
                htmlText.append("Week Start Date: "+wstDate+"<br>");
                htmlText.append("Week End Date: "+wenDate+"<br>");
                // linkUrl="newgetEmpTimeSheet.action?empID={EmpID}&timeSheetID={TimeSheetId}&resourceType={ResourceType}&statusValue={Description}" imageBorder="0" 
                if(!(reportsToType!=null)){
                htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/newGetTeamTimeSheet.action?" +
                        "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=e&resourceType="+resourceType+"&statusValue="+description+"'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("e"))
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/newGetTeamTimeSheet.action?" +
                        "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=e&resourceType="+resourceType+"&statusValue="+description+"'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("v"))
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/newGetTeamTimeSheet.action?" +
                        "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=v&resourceType="+resourceType+"&statusValue="+description+"'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("c"))
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/newGetTeamTimeSheet.action?" +
                        "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=c&resourceType="+resourceType+"&statusValue="+description+"'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }
                htmlText.append("<br><br> Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
                htmlText.append("</body></html>");
                
                
            
        }catch (Exception se){
            throw new ServiceLocatorException(se);
        }finally{
            
        }
        return htmlText.toString();
    }
    

*/


 public String getApproveTimeSheetEmailLogBody(String empName,String wstDate,String wenDate) throws ServiceLocatorException{
        
       // System.out.println("in service impl");
        int deletedRows = 0;
      //  Connection connection = null;
      //  Statement statement = null;
       // String queryString = "Delete from tblTimeSheets  WHERE id="+id;
      StringBuffer htmlText = new StringBuffer();
       
        try{
            
            
            
//              htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
//                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
//                htmlText.append("<p>Hello "+empName+"</p>");
//                htmlText.append("<p>Your Time Sheet has been Approved.</p>");
//                htmlText.append("<p><u><b>Time Sheet Details:</b></u><br>");
//                htmlText.append("Week Start Date: "+wstDate+"<br>");
//                htmlText.append("Week End Date: "+wenDate+"<br>");
//                htmlText.append("Thank you.</p></font>");
//                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
//                htmlText.append("</body></html>");
            
            htmlText.append("<!DOCTYPE html><html><head><meta charset='utf-8'><meta name='viewport' content='width=device-width, initial-scale=1'><meta http-equiv='X-UA-Compatible' content='IE=edge' />");
            htmlText.append("<style type='text/css'>body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;}");
            htmlText.append("table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;}");
            htmlText.append("img{-ms-interpolation-mode: bicubic;}");
            htmlText.append("img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}");
            htmlText.append("table{border-collapse: collapse !important;}");
            htmlText.append("body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}");
            htmlText.append("a[x-apple-data-detectors] {color: inherit !important;text-decoration: none !important;font-size: inherit !important;font-family: inherit !important;font-weight: inherit !important;line-height: inherit !important;}");
            htmlText.append("@media screen and (max-width: 525px) { .wrapper {width: 100% !important;max-width: 100% !important;}");
            htmlText.append(".logo img {margin: 0 auto !important;} .mobile-hide {display: none !important;} .img-max {max-width: 100% !important;width: 100% !important; height: auto !important; }");
            htmlText.append(".responsive-table {width: 100% !important;}");
            htmlText.append(".padding {padding: 10px 5% 15px 5% !important;}");
            htmlText.append(".padding-meta {padding: 30px 5% 0px 5% !important;text-align: center;}");
            htmlText.append(".padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}");
            htmlText.append(".no-padding {padding: 0 !important;}");
            htmlText.append(".section-padding {padding: 50px 15px 50px 15px !important;}");
            htmlText.append(".mobile-button-container {margin: 0 auto;width: 100% !important;}");
            htmlText.append(".mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important;display: block !important;} }");
            htmlText.append("</style></head><body style='margin: 0 !important; padding: 0 !important;'>");
            htmlText.append("<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>Hello <b>"+empName+",</b><br>Your Time Sheet has been Approved.</div>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr><td bgcolor='#ffffff' align='center'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper'><tr>");
            htmlText.append("<td align='center' valign='top' style='padding: 15px 0;' class='logo'>");
            htmlText.append("<a href='http://www.miraclesoft.com/' target='_blank'><img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'></a>");
            htmlText.append("</td></tr></table></td></tr>");
            htmlText.append("<tr><td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'><tr><td>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>Time Sheet Approval</b></td></tr>");
            htmlText.append("</table></td></tr></table></td></tr>");
            htmlText.append("<tr><td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'><tr><td>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>");
            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>"+empName+",</b><br>Your Time Sheet has been Approved.     </td></tr>");
            htmlText.append("<tr><td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b style='font-size: 14px; color: #ef4048;'>From Date: </b> "+wstDate+"<br><b style='font-size: 14px; color: #ef4048;'>To Date: </b> "+wenDate+"<br>");
            htmlText.append("</td></tr></tr></table></td></tr>");
            htmlText.append("<tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>");
            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>");
            htmlText.append("Thanks & Regards,<br>Corporate Application Support Team, <br>Miracle Software Systems, Inc. <br>Email: hubble@miraclesoft.com <br>Phone: (+1)248-233-1814");
            htmlText.append("</td></tr></table></td></tr>");
            htmlText.append("<tr><td> <table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>");
            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td>");
            htmlText.append("</tr></table></td></tr></table></td></tr>");
            htmlText.append("<tr><td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("<tr><td width='200' align='center' style='text-align: center;'><table width='200' cellpadding='0' cellspacing='0' align='center'>");
            htmlText.append("<tr><td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("<td width='10'><a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("<td width='10'><a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("<td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("<td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("</tr></table></td></tr><tr><td height='10'></td></tr>");
            htmlText.append("<tr><td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems<br></td></tr>");
            htmlText.append("</table></td></tr></table></body></html>");

            
        }catch (Exception se){
            throw new ServiceLocatorException(se);
        }finally{
            
        }
        return htmlText.toString();
    }
    

public String getRejectTimeSheetEmailLogBody(String empName,String wstDate,String wenDate) throws ServiceLocatorException{
        
       // System.out.println("in service impl");
        int deletedRows = 0;
      //  Connection connection = null;
      //  Statement statement = null;
       // String queryString = "Delete from tblTimeSheets  WHERE id="+id;
      StringBuffer htmlText = new StringBuffer();
       
        try{
                
//                 htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
//                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
//                htmlText.append("<p>Hello "+empName+"</p>");
//                htmlText.append("<p>Your Time Sheet has been Rejected.</p>");
//                htmlText.append("<p><u><b>Time Sheet Details:</b></u><br>");
//                htmlText.append("Week Start Date: "+wstDate+"<br>");
//                htmlText.append("Week End Date: "+wenDate+"<br>");
//                htmlText.append("Thank you.</p></font>");
//                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
//                htmlText.append("</body></html>");
            
            
            htmlText.append("<!DOCTYPE html><html><head><meta charset='utf-8'><meta name='viewport' content='width=device-width, initial-scale=1'><meta http-equiv='X-UA-Compatible' content='IE=edge' />");
            htmlText.append("<style type='text/css'>body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;}");
            htmlText.append("table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;}");
            htmlText.append("img{-ms-interpolation-mode: bicubic;}");
            htmlText.append("img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}");
            htmlText.append("table{border-collapse: collapse !important;}");
            htmlText.append("body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}");
            htmlText.append("a[x-apple-data-detectors] {color: inherit !important;text-decoration: none !important;font-size: inherit !important;font-family: inherit !important;font-weight: inherit !important;line-height: inherit !important;}");
            htmlText.append("@media screen and (max-width: 525px) { .wrapper {width: 100% !important;max-width: 100% !important;}");
            htmlText.append(".logo img {margin: 0 auto !important;} .mobile-hide {display: none !important;} .img-max {max-width: 100% !important;width: 100% !important; height: auto !important; }");
            htmlText.append(".responsive-table {width: 100% !important;}");
            htmlText.append(".padding {padding: 10px 5% 15px 5% !important;}");
            htmlText.append(".padding-meta {padding: 30px 5% 0px 5% !important;text-align: center;}");
            htmlText.append(".padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}");
            htmlText.append(".no-padding {padding: 0 !important;}");
            htmlText.append(".section-padding {padding: 50px 15px 50px 15px !important;}");
            htmlText.append(".mobile-button-container {margin: 0 auto;width: 100% !important;}");
            htmlText.append(".mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important;display: block !important;} }");
            htmlText.append("</style></head><body style='margin: 0 !important; padding: 0 !important;'>");
            htmlText.append("<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>Hello <b>"+empName+",</b><br>Your Time Sheet has been Rejected.</div>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr><td bgcolor='#ffffff' align='center'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper'><tr>");
            htmlText.append("<td align='center' valign='top' style='padding: 15px 0;' class='logo'>");
            htmlText.append("<a href='http://www.miraclesoft.com/' target='_blank'><img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'></a>");
            htmlText.append("</td></tr></table></td></tr>");
            htmlText.append("<tr><td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'><tr><td>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            htmlText.append("<tr><td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>Time Sheet Rejection</b></td></tr>");
            htmlText.append("</table></td></tr></table></td></tr>");
            htmlText.append("<tr><td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");
            htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'><tr><td>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>");
            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>"+empName+",</b><br>Your Time Sheet has been Rejected.     </td></tr>");
            htmlText.append("<tr><td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b style='font-size: 14px; color: #ef4048;'>From Date: </b> "+wstDate+"<br><b style='font-size: 14px; color: #ef4048;'>To Date: </b> "+wenDate+"<br>");
            htmlText.append("</td></tr></tr></table></td></tr>");
            htmlText.append("<tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>");
            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>");
            htmlText.append("Thanks & Regards,<br>Corporate Application Support Team, <br>Miracle Software Systems, Inc. <br>Email: hubble@miraclesoft.com <br>Phone: (+1)248-233-1814");
            htmlText.append("</td></tr></table></td></tr>");
            htmlText.append("<tr><td> <table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>");
            htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td>");
            htmlText.append("</tr></table></td></tr></table></td></tr>");
            htmlText.append("<tr><td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");
            htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' class='responsive-table'>");
            htmlText.append("<tr><td width='200' align='center' style='text-align: center;'><table width='200' cellpadding='0' cellspacing='0' align='center'>");
            htmlText.append("<tr><td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("<td width='10'><a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("<td width='10'><a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("<td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("<td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
            htmlText.append("</tr></table></td></tr><tr><td height='10'></td></tr>");
            htmlText.append("<tr><td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems<br></td></tr>");
            htmlText.append("</table></td></tr></table></body></html>");
            
        }catch (Exception se){
            throw new ServiceLocatorException(se);
        }finally{
            
        }
        return htmlText.toString();
    }
    
public String getEnteredTimeSheetEmailLogBody(String empName,String wstDate,String wenDate) throws ServiceLocatorException{
        
       // System.out.println("in service impl");
        int deletedRows = 0;
      //  Connection connection = null;
      //  Statement statement = null;
       // String queryString = "Delete from tblTimeSheets  WHERE id="+id;
      StringBuffer htmlText = new StringBuffer();
       
        try{
                
                 htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>Hello "+empName+"</p>");
                htmlText.append("<p>Please submit your enterd timesheet in below dates.</p>");
                htmlText.append("<p><u><b>Time Sheet Details:</b></u><br>");
                htmlText.append("Week Start Date: "+wstDate+"<br>");
                htmlText.append("Week End Date: "+wenDate+"<br>");
                htmlText.append("Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
                htmlText.append("</body></html>");
            
        }catch (Exception se){
            throw new ServiceLocatorException(se);
        }finally{
            
        }
        return htmlText.toString();
    }
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
    
     
     // Dual changes
/*     
public String getSubmitTimeSheetEmailLogBody(String empName,String wstDate,String wenDate,String empId,String reportsToType,int timeSheetID,String resourceType,String description,String secDescription) throws ServiceLocatorException{
        
       // System.out.println("in service impl");
        int deletedRows = 0;
      //  Connection connection = null;
      //  Statement statement = null;
       // String queryString = "Delete from tblTimeSheets  WHERE id="+id;
      StringBuffer htmlText = new StringBuffer();
       
        try{
            
            
            
             htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
                htmlText.append("<p>A TimeSheet has been submitted by "+empName+" <br>");
                htmlText.append("<p><u>Time Sheet Details:</u><br>");
                htmlText.append("Week Start Date: "+wstDate+"<br>");
                htmlText.append("Week End Date: "+wenDate+"<br>");
                // linkUrl="newgetEmpTimeSheet.action?empID={EmpID}&timeSheetID={TimeSheetId}&resourceType={ResourceType}&statusValue={Description}" imageBorder="0" 
                if(!(reportsToType!=null)){
                htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/newGetTeamTimeSheet.action?" +
                       // "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=e&resourceType="+resourceType+"&statusValue="+description+"&secStatusValue="+secDescription+"&teamType="+teamType+"'> " +
                         "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=e&resourceType="+resourceType+"&statusValue="+description+"&secStatusValue="+secDescription+"'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("e"))
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/newGetTeamTimeSheet.action?" +
                        "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=e&resourceType="+resourceType+"&statusValue="+description+"&secStatusValue="+secDescription+"'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("v"))
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/newGetTeamTimeSheet.action?" +
                        "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=v&resourceType="+resourceType+"&statusValue="+description+"&secStatusValue="+secDescription+"'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("c"))
                {
                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://w3.miraclesoft.com/Hubble/employee/timesheets/newGetTeamTimeSheet.action?" +
                        "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=c&resourceType="+resourceType+"&statusValue="+description+"&secStatusValue="+secDescription+"'> " +
                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }
                htmlText.append("<br><br> Thank you.</p></font>");
                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
                htmlText.append("</body></html>");
                
                
            
        }catch (Exception se){
            throw new ServiceLocatorException(se);
        }finally{
            
        }
        return htmlText.toString();
    }
    */
      public String getSubmitTimeSheetEmailLogBody(String empName,String wstDate,String wenDate,String empId,String reportsToType,int timeSheetID,String resourceType) throws ServiceLocatorException{
        
      
        int deletedRows = 0;
    
      StringBuffer htmlText = new StringBuffer();
       
        try{
            
            String url = Properties.getProperty("PROD.URL");
            
//             htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
//                htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
//                htmlText.append("<p>A TimeSheet has been submitted by "+empName+" <br>");
//                htmlText.append("<p><u>Time Sheet Details:</u><br>");
//                htmlText.append("Week Start Date: "+wstDate+"<br>");
//                htmlText.append("Week End Date: "+wenDate+"<br>");
//               
//                if(!(reportsToType!=null)){
//              
//                     htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='"+url+"employee/timesheets/newGetTeamTimeSheet.action?" +
//                     
//                         "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=e&resourceType="+resourceType+"'> " +
//                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
//                }else if(reportsToType.equalsIgnoreCase("e"))
//                {
//                   htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='"+url+"employee/timesheets/newGetTeamTimeSheet.action?" +
//                
//                    "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=e&resourceType="+resourceType+"'> " +
//                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
//                }else if(reportsToType.equalsIgnoreCase("v"))
//                {
//                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='"+url+"employee/timesheets/newGetTeamTimeSheet.action?" +
//                        "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=v&resourceType="+resourceType+"'> " +
//                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
//                }else if(reportsToType.equalsIgnoreCase("c"))
//                {
//                    htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='"+url+"employee/timesheets/newGetTeamTimeSheet.action?" +
//                        "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=c&resourceType="+resourceType+"'> " +
//                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
//                }
//                htmlText.append("<br><br> Thank you.</p></font>");
//                htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
//                htmlText.append("</body></html>");
                
                htmlText.append("<!DOCTYPE html><html><head><title>Mail From Hubble Portal</title>");
             htmlText.append("<meta charset='utf-8'><meta name='viewport' content='width=device-width, initial-scale=1'><meta http-equiv='X-UA-Compatible' content='IE=edge' />");
             htmlText.append("<style type='text/css'>body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;}");
             htmlText.append("table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;}");
             htmlText.append("img{-ms-interpolation-mode: bicubic;}");
             htmlText.append("img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}");
             htmlText.append("table{border-collapse: collapse !important;} body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}");
             htmlText.append("a[x-apple-data-detectors] {color: inherit !important; text-decoration: none !important;font-size: inherit !important;font-family: inherit !important; font-weight: inherit !important; line-height: inherit !important;}");
             htmlText.append("@media screen and (max-width: 525px) {");
             htmlText.append(".wrapper {width: 100% !important; max-width: 100% !important;}");
             htmlText.append(".logo img {margin: 0 auto !important;}");
             htmlText.append(".mobile-hide {display: none !important;}");
             htmlText.append(".img-max {max-width: 100% !important; width: 100% !important; height: auto !important;}");
             htmlText.append(".responsive-table {width: 100% !important;}");
             htmlText.append(".padding {padding: 10px 5% 15px 5% !important;}");
             htmlText.append(".padding-meta {padding: 30px 5% 0px 5% !important;text-align: center;}");
             htmlText.append(".padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}");
             htmlText.append(".no-padding {padding: 0 !important;}");
             htmlText.append(".section-padding {padding: 50px 15px 50px 15px !important;}");
             htmlText.append(".mobile-button-container {margin: 0 auto;width: 100% !important;}");
             htmlText.append(".mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important;display: block !important;}");
             htmlText.append("} div[style*='margin: 16px 0;'] { margin: 0 !important; }");
                htmlText.append("</style></head><body style='margin: 0 !important; padding: 0 !important;'>");
                htmlText.append("<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>Timesheet has been submitted by <b>"+empName+"</b></div>");
                htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr><td bgcolor='#ffffff' align='center'>");
                htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper'><tr><td align='center' valign='top' style='padding: 15px 0;' class='logo'>");
                htmlText.append("<a href='http://www.miraclesoft.com/' target='_blank'><img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'></a>");
                htmlText.append("</td></tr></table></td></tr>");
                htmlText.append("<tr><td bgcolor='#ffffff' align='center' style='padding: 5px;'><table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'><tr><td>");
                htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>Time Sheet</b></td></tr></table>");
                htmlText.append("</td></tr></table></td></tr>");
                htmlText.append("<tr><td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'> <table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'><tr><td>");
                htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>Team,</b><br>Timesheet has been submitted by <b>"+empName+"</b></td></tr>");
                htmlText.append("<tr><td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b style='font-size: 14px; color: #ef4048;'>From Date: </b> "+wstDate+"<br><b style='font-size: 14px; color: #ef4048;'>To Date: </b> "+wenDate+"<br></td></tr>");
//                htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b> Click <a href='http://www.miraclesoft.com/Hubble/employee/timesheets/newGetTeamTimeSheet.action?employeeID=3202&emptimeSheetID=4&type=e&resourceType=e' target='_blank' style='font-size: 14px; color: #2368a0;'>here</a> to Approve/Reject the TimeSheet.</b></td>");
//                htmlText.append("<p>A TimeSheet has been submitted by "+empName+" <br>");
//                htmlText.append("<p><u>Time Sheet Details:</u><br>");
//                htmlText.append("Week Start Date: "+wstDate+"<br>");
//                htmlText.append("Week End Date: "+wenDate+"<br>");
                // linkUrl="newgetEmpTimeSheet.action?empID={EmpID}&timeSheetID={TimeSheetId}&resourceType={ResourceType}&statusValue={Description}" imageBorder="0" 
                if(!(reportsToType!=null)){
               // htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://www.miraclesoft.com/Hubble/employee/timesheets/newGetTeamTimeSheet.action?" +
                    htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b> Click <a href='"+url+"employee/timesheets/newGetTeamTimeSheet.action?" +
                            "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=e&resourceType="+resourceType+"'> " +
                            "<target='_blank' style='font-size: 14px; color: #2368a0;'>here</a> to Approve/Reject the TimeSheet.</b></td>");
//                            href='http://www.miraclesoft.com/Hubble/employee/timesheets/newGetTeamTimeSheet.action?employeeID=3202&emptimeSheetID=4&type=e&resourceType=e'");
//                     htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='"+url+"employee/timesheets/newGetTeamTimeSheet.action?" +
//                       // "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=e&resourceType="+resourceType+"&statusValue="+description+"&secStatusValue="+secDescription+"&teamType="+teamType+"'> " +
//                        // "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=e&resourceType="+resourceType+"&statusValue="+description+"&secStatusValue="+secDescription+"'> " +
//                         "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=e&resourceType="+resourceType+"'> " +
//                        " Click here </a> </font>  to Approve/Reject the TimeSheet.");
                }else if(reportsToType.equalsIgnoreCase("e"))
                {
                   htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b> Click <a href='"+url+"employee/timesheets/newGetTeamTimeSheet.action?" +
                  //   htmlText.append(" <br><br>\n\n\n <font color=\"red\">  <a href='http://172.17.11.251:8084/Hubble/employee/timesheets/newGetTeamTimeSheet.action?" + 
                    "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=e&resourceType="+resourceType+"'> " +
                        "<target='_blank' style='font-size: 14px; color: #2368a0;'>here</a> to Approve/Reject the TimeSheet.</b></td>");
                }else if(reportsToType.equalsIgnoreCase("v"))
                {
                    htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b> Click <a href='"+url+"employee/timesheets/newGetTeamTimeSheet.action?" +
                        "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=v&resourceType="+resourceType+"'> " +
                        "<target='_blank' style='font-size: 14px; color: #2368a0;'>here</a> to Approve/Reject the TimeSheet.</b></td>");
                }else if(reportsToType.equalsIgnoreCase("c"))
                {
                    htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b> Click <a href='"+url+"employee/timesheets/newGetTeamTimeSheet.action?" +
                        "employeeID="+empId+"&emptimeSheetID="+timeSheetID+"&type=c&resourceType="+resourceType+"'> " +
                        "<target='_blank' style='font-size: 14px; color: #2368a0;'>here</a> to Approve/Reject the TimeSheet.</b></td>");
                }
                htmlText.append("</tr></table></td></tr>");
                htmlText.append("<tr><td> <table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>Thanks & Regards,<br>Corporate Application Support Team, <br>Miracle Software Systems, Inc. <br>Email: hubble@miraclesoft.com <br>Phone: (+1)248-233-1814</td>");
                htmlText.append("</tr></table></td></tr>");
                htmlText.append("<tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td>");
                htmlText.append("</tr></table></td></tr></table> </td></tr>");
                htmlText.append("<tr><td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'> <table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' class='responsive-table'><tr><td width='200' align='center' style='text-align: center;'><table width='200' cellpadding='0' cellspacing='0' align='center'><tr>");
                htmlText.append("<td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                htmlText.append("<td width='10'><a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                htmlText.append("<td width='10'><a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                htmlText.append("<td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                htmlText.append("<td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                htmlText.append("</tr></table></td></tr><tr><td height='10'></td></tr>");
                htmlText.append("<tr><td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems<br></td></tr>");
                htmlText.append("</table></td></tr></table></body></html>");
            
        }catch (Exception se){
            throw new ServiceLocatorException(se);
        }finally{
            
        }
        return htmlText.toString();
    }
    

	public void getBiometricHours(NewTimeSheetAction newTimeSheetAction) throws ServiceLocatorException{
        
       // System.out.println("in service impl");
        int deletedRows = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String weekDate = null;
        String queryString = "";
      
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
           
            
             weekDate = DateUtility.getInstance().convertStringToMySQLDate(newTimeSheetAction.getTimeSheetVTO().getWeekDate1());
            queryString = "select  EmpNo,AttendanceDate,TIMESTAMPDIFF(hour,InTime,OutTime)hrs,statusCode from tblBIOAttendanceLogs where EmpNo="+newTimeSheetAction.getEmpNo()+" AND AttendanceDate = '"+weekDate+"'";
           // System.out.println("queryString1-->"+queryString) ;
            resultSet = statement.executeQuery(queryString);
             if(resultSet.next()){
                 newTimeSheetAction.getTimeSheetVTO().setBiometricSun(resultSet.getDouble("hrs"));
                 newTimeSheetAction.getTimeSheetVTO().setBmSunStatus(resultSet.getString("statusCode"));
             }
             
             weekDate = DateUtility.getInstance().convertStringToMySQLDate(newTimeSheetAction.getTimeSheetVTO().getWeekDate2());
            queryString = "select  EmpNo,AttendanceDate,TIMESTAMPDIFF(hour,InTime,OutTime)hrs,statusCode from tblBIOAttendanceLogs where EmpNo="+newTimeSheetAction.getEmpNo()+" AND AttendanceDate = '"+weekDate+"'";
         //   System.out.println("queryString2-->"+queryString) ;
            resultSet = statement.executeQuery(queryString);
             if(resultSet.next()){
                 newTimeSheetAction.getTimeSheetVTO().setBiometricMon(resultSet.getDouble("hrs"));
                 newTimeSheetAction.getTimeSheetVTO().setBmMonStatus(resultSet.getString("statusCode"));
             }
             
              weekDate = DateUtility.getInstance().convertStringToMySQLDate(newTimeSheetAction.getTimeSheetVTO().getWeekDate3());
            queryString = "select  EmpNo,AttendanceDate,TIMESTAMPDIFF(hour,InTime,OutTime)hrs,statusCode from tblBIOAttendanceLogs where EmpNo="+newTimeSheetAction.getEmpNo()+" AND AttendanceDate = '"+weekDate+"'";
           // System.out.println("queryString3-->"+queryString) ;
            resultSet = statement.executeQuery(queryString);
             if(resultSet.next()){
                 newTimeSheetAction.getTimeSheetVTO().setBiometricTus(resultSet.getDouble("hrs"));
                 newTimeSheetAction.getTimeSheetVTO().setBmTusStatus(resultSet.getString("statusCode"));
             }
             
             
              weekDate = DateUtility.getInstance().convertStringToMySQLDate(newTimeSheetAction.getTimeSheetVTO().getWeekDate4());
            queryString = "select  EmpNo,AttendanceDate,TIMESTAMPDIFF(hour,InTime,OutTime)hrs,statusCode from tblBIOAttendanceLogs where EmpNo="+newTimeSheetAction.getEmpNo()+" AND AttendanceDate = '"+weekDate+"'";
           // System.out.println("queryString4-->"+queryString) ;
            resultSet = statement.executeQuery(queryString);
             if(resultSet.next()){
                 newTimeSheetAction.getTimeSheetVTO().setBiometricWed(resultSet.getDouble("hrs"));
                 newTimeSheetAction.getTimeSheetVTO().setBmWedStatus(resultSet.getString("statusCode"));
             }
             
             
              weekDate = DateUtility.getInstance().convertStringToMySQLDate(newTimeSheetAction.getTimeSheetVTO().getWeekDate5());
            queryString = "select  EmpNo,AttendanceDate,TIMESTAMPDIFF(hour,InTime,OutTime)hrs,statusCode from tblBIOAttendanceLogs where EmpNo="+newTimeSheetAction.getEmpNo()+" AND AttendanceDate = '"+weekDate+"'";
           // System.out.println("queryString5-->"+queryString) ; 
            resultSet = statement.executeQuery(queryString);
             if(resultSet.next()){
                 newTimeSheetAction.getTimeSheetVTO().setBiometricThur(resultSet.getDouble("hrs"));
                 newTimeSheetAction.getTimeSheetVTO().setBmThurStatus(resultSet.getString("statusCode"));
             }
             
              weekDate = DateUtility.getInstance().convertStringToMySQLDate(newTimeSheetAction.getTimeSheetVTO().getWeekDate6());
            queryString = "select  EmpNo,AttendanceDate,TIMESTAMPDIFF(hour,InTime,OutTime)hrs,statusCode from tblBIOAttendanceLogs where EmpNo="+newTimeSheetAction.getEmpNo()+" AND AttendanceDate = '"+weekDate+"'";
           // System.out.println("queryString6-->"+queryString) ;
            resultSet = statement.executeQuery(queryString);
             if(resultSet.next()){
                 newTimeSheetAction.getTimeSheetVTO().setBiometricFri(resultSet.getDouble("hrs"));
                 newTimeSheetAction.getTimeSheetVTO().setBmFriStatus(resultSet.getString("statusCode"));
             }
             
              weekDate = DateUtility.getInstance().convertStringToMySQLDate(newTimeSheetAction.getTimeSheetVTO().getWeekDate7());
            queryString = "select  EmpNo,AttendanceDate,TIMESTAMPDIFF(hour,InTime,OutTime)hrs,statusCode from tblBIOAttendanceLogs where EmpNo="+newTimeSheetAction.getEmpNo()+" AND AttendanceDate = '"+weekDate+"'";
           // System.out.println("queryString7-->"+queryString) ; 
            resultSet = statement.executeQuery(queryString);
             if(resultSet.next()){
                 newTimeSheetAction.getTimeSheetVTO().setBiometricSat(resultSet.getDouble("hrs"));
                 newTimeSheetAction.getTimeSheetVTO().setBmSatStatus(resultSet.getString("statusCode"));
             }
            newTimeSheetAction.getTimeSheetVTO().setBiometricTotalHrs(newTimeSheetAction.getTimeSheetVTO().getBiometricSun()+newTimeSheetAction.getTimeSheetVTO().getBiometricMon()+newTimeSheetAction.getTimeSheetVTO().getBiometricTus()+newTimeSheetAction.getTimeSheetVTO().getBiometricWed()+newTimeSheetAction.getTimeSheetVTO().getBiometricThur()+newTimeSheetAction.getTimeSheetVTO().getBiometricFri()+newTimeSheetAction.getTimeSheetVTO().getBiometricSat());
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
       
    }
    
}
