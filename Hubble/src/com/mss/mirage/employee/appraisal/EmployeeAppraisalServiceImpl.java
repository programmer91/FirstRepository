/*
 * EmployeeApraisalImpl.java
 *
 * Created on May 15, 2008, 3:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.employee.appraisal;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author miracle
 */
public class EmployeeAppraisalServiceImpl implements EmployeeAppraisalService {
    
    /** Creates a new instance of EmployeeApraisalImpl */
    public EmployeeAppraisalServiceImpl() {
    }
    
    
    public EmployeeAppraisalVTO getPersonalDetails(int empId,int currentEmpId) throws ServiceLocatorException{
        EmployeeAppraisalVTO empAppraisalVTO=new EmployeeAppraisalVTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement=connection.prepareStatement("select * from vwEmployeeAppraisal where EmpId='"+empId +"'");
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                empAppraisalVTO.setDepartment(resultSet.getString("DepartmentId"));
                empAppraisalVTO.setDesignation(resultSet.getString("TitleTypeId"));
                empAppraisalVTO.setDateOfJoin(resultSet.getDate("HireDate"));
                empAppraisalVTO.setAppraisalDate(resultSet.getDate("AppraisalDate"));
                empAppraisalVTO.setTeamName(resultSet.getString("TeamId"));
                empAppraisalVTO.setTechMangName(resultSet.getString("ReportsTo"));
              
            }
            resultSet.close();
            resultSet = null;
            resultSet=preparedStatement.executeQuery("select Id,Prj1Name,Prj1ModeofWork,Prj1ResourceType,Prj1StartDate,Prj1EndDate,Prj1Comm," +
                    "Prj2Name,Prj2ModeofWork,Prj2ResourceType,Prj2StartDate,Prj2EndDate,Prj2Comm," +
                    "Prj3Name,Prj3ModeofWork,Prj3ResourceType,Prj3StartDate,Prj3EndDate,Prj3Comm," +
                    "Prj4Name,Prj4ModeofWork,Prj4ResourceType,Prj4StartDate,Prj4EndDate,Prj4Comm," +
                    "Prj5Name,Prj5ModeofWork,Prj5ResourceType,Prj5StartDate,Prj5EndDate,Prj5Comm,PrjHrComm,PrjRating" +
                    " from tblEmpAppraisal where Id='"+currentEmpId+"'");
            while(resultSet.next()){
                empAppraisalVTO.setNameOfProject1(resultSet.getString("Prj1Name"));
                empAppraisalVTO.setModeOfWork1(resultSet.getString("Prj1ModeofWork"));
                empAppraisalVTO.setResourceType1(resultSet.getString("Prj1ResourceType"));
                empAppraisalVTO.setStartDate1(resultSet.getDate("Prj1StartDate"));
                empAppraisalVTO.setEndDate1(resultSet.getDate("Prj1EndDate"));
                
                empAppraisalVTO.setComments1(resultSet.getString("Prj1Comm"));
                
                empAppraisalVTO.setNameOfProject2(resultSet.getString("Prj2Name"));
                empAppraisalVTO.setModeOfWork2(resultSet.getString("Prj2ModeofWork"));
                empAppraisalVTO.setResourceType2(resultSet.getString("Prj2ResourceType"));
                empAppraisalVTO.setStartDate2(resultSet.getDate("Prj2StartDate"));
                empAppraisalVTO.setEndDate2(resultSet.getDate("Prj2EndDate"));
                empAppraisalVTO.setComments2(resultSet.getString("Prj2Comm"));
                empAppraisalVTO.setNameOfProject3(resultSet.getString("Prj3Name"));
                empAppraisalVTO.setModeOfWork3(resultSet.getString("Prj3ModeofWork"));
                empAppraisalVTO.setResourceType3(resultSet.getString("Prj3ResourceType"));
                empAppraisalVTO.setStartDate3(resultSet.getDate("Prj3StartDate"));
                empAppraisalVTO.setEndDate3(resultSet.getDate("Prj3EndDate"));
                empAppraisalVTO.setComments3(resultSet.getString("Prj3Comm"));
                empAppraisalVTO.setNameOfProject4(resultSet.getString("Prj4Name"));
                empAppraisalVTO.setModeOfWork4(resultSet.getString("Prj4ModeofWork"));
                empAppraisalVTO.setResourceType4(resultSet.getString("Prj4ResourceType"));
                empAppraisalVTO.setStartDate4(resultSet.getDate("Prj4StartDate"));
                empAppraisalVTO.setEndDate4(resultSet.getDate("Prj4EndDate"));
                empAppraisalVTO.setComments4(resultSet.getString("Prj4Comm"));
                empAppraisalVTO.setNameOfProject5(resultSet.getString("Prj5Name"));
                empAppraisalVTO.setModeOfWork5(resultSet.getString("Prj5ModeofWork"));
                empAppraisalVTO.setResourceType5(resultSet.getString("Prj5ResourceType"));
                empAppraisalVTO.setStartDate5(resultSet.getDate("Prj5StartDate"));
                empAppraisalVTO.setEndDate5(resultSet.getDate("Prj5EndDate"));
                empAppraisalVTO.setComments5(resultSet.getString("Prj5Comm"));
                empAppraisalVTO.setPrjHrOrTm(resultSet.getString("PrjHrComm"));
                empAppraisalVTO.setPrjHrRating(resultSet.getString("PrjRating"));
            }
            /** for Details of individuals **/
            resultSet.close();
            resultSet = null;
            resultSet=preparedStatement.executeQuery("select PayrollMonths,LeaveHistory," +
                    "OnSiteDuration,OffShoreDuration,MonthsinBench," +
                    "CorporateTraining,Interviews,CurrnetSalary," +
                    "PreviousHike,VisaAvailable,SkillSet,HRComm" +
                    " from tblEmpAppraisal where Id="+currentEmpId);
            while(resultSet.next()) {
                
                empAppraisalVTO.setMonthsInPayroll(resultSet.getString(1));
                empAppraisalVTO.setLeaveHistory(resultSet.getString(2));
                empAppraisalVTO.setOnsiteProjectDuration(resultSet.getString(3));
                empAppraisalVTO.setOffshoreProjectDuration(resultSet.getString(4));
                empAppraisalVTO.setBenchDuration(resultSet.getString(5));
                empAppraisalVTO.setCorporateTrainingDuration(resultSet.getString(6));
                empAppraisalVTO.setInterviewsAttended(resultSet.getString(7));
                empAppraisalVTO.setCurrentSal(resultSet.getString(8));
                empAppraisalVTO.setPreviousHike(resultSet.getString(9));
                empAppraisalVTO.setVisaAvailable(resultSet.getString(10));
                empAppraisalVTO.setSkills(resultSet.getString(11));
                empAppraisalVTO.setHrComments(resultSet.getString(12));
            }
            resultSet.close();
            resultSet = null;
            /** for self assessment Details **/
            resultSet=preparedStatement.executeQuery("select * from tblEmpAppraisal where Id="+currentEmpId);
            
            while(resultSet.next()) {
                empAppraisalVTO.setPAttendence(resultSet.getString("PAttendance"));
                empAppraisalVTO.setPAttendHrComments(resultSet.getString("PAHrComm"));
                empAppraisalVTO.setPAttendHrRating(resultSet.getString("PARating"));
                empAppraisalVTO.setWorkAttitude(resultSet.getString("WorkAttitude"));
                empAppraisalVTO.setWAttHrComment(resultSet.getString("WAHrComm"));
                empAppraisalVTO.setWAttHrRating(resultSet.getString("WARating"));
                empAppraisalVTO.setCommunication(resultSet.getString("Communication"));
                empAppraisalVTO.setCommHrComments(resultSet.getString("CHrComm"));
                empAppraisalVTO.setCommHrRating(resultSet.getString("CRating"));
                empAppraisalVTO.setWorkObjectives(resultSet.getString("WorkObjectives"));
                empAppraisalVTO.setWorkObjHrComm(resultSet.getString("WOHrComm"));
                empAppraisalVTO.setWorkObjHrRating(resultSet.getString("WORating"));
                empAppraisalVTO.setCollaborationTW(resultSet.getString("TeamWork"));
                empAppraisalVTO.setCollTWHrComm(resultSet.getString("TWHrComm"));
                empAppraisalVTO.setCollTWHrRating(resultSet.getString("TWRating"));
                empAppraisalVTO.setPlanOrganization(resultSet.getString("Planning"));
                empAppraisalVTO.setPlanOrgHrComm(resultSet.getString("POHrComm"));
                empAppraisalVTO.setPlanOrgHrRating(resultSet.getString("PORating"));
                empAppraisalVTO.setAdaptVersatility(resultSet.getString("Adaptability"));
                empAppraisalVTO.setAdaptVersatilityHrComm(resultSet.getString("AVHrComm"));
                empAppraisalVTO.setAdaptVersatilityHrRating(resultSet.getString("AVRating"));
                empAppraisalVTO.setAchievAppreciations(resultSet.getString("Achievements"));
                empAppraisalVTO.setAchievApprecHrComm(resultSet.getString("AAHrComm"));
                empAppraisalVTO.setAchievApprecHrRating(resultSet.getString("AARating"));
            }
            resultSet.close();
            resultSet = null;
            resultSet=preparedStatement.executeQuery("SELECT TMComm,TMName,TMRating,TMCommDate,PMComm,PMName,PMRating,PMCommDate," +
                    "HrFhComm,HrName,HrRating,HrCommDate,VPComm,VPName,VPRating,VPCommDate " +
                    "from tblEmpAppraisal where Id="+currentEmpId);
            while(resultSet.next()) {
                empAppraisalVTO.setTlcomments(resultSet.getString("TMComm"));
                empAppraisalVTO.setTlRating(resultSet.getString("TMRating"));
                empAppraisalVTO.setCurrentTMDate(resultSet.getDate("TMCommDate"));
                empAppraisalVTO.setPmComments(resultSet.getString("PMComm"));
                empAppraisalVTO.setPName(resultSet.getString("PMName"));
                empAppraisalVTO.setPRating(resultSet.getString("PMRating"));
                empAppraisalVTO.setCurrentPMDate(resultSet.getDate("PMCommDate"));
                empAppraisalVTO.setHrComment(resultSet.getString("HrFhComm"));
                empAppraisalVTO.setHName(resultSet.getString("HrName"));
                empAppraisalVTO.setHrRating(resultSet.getString("HrRating"));
                empAppraisalVTO.setCurrentHRDate(resultSet.getDate("HrCommDate"));
                empAppraisalVTO.setVpComments(resultSet.getString("VPComm"));
                empAppraisalVTO.setVName(resultSet.getString("VPName"));
                empAppraisalVTO.setVpRating(resultSet.getString("VPRating"));
               empAppraisalVTO.setCurrentVPDate(resultSet.getDate("VPCommDate"));
                
                
            }
            
            resultSet.close();
            resultSet = null;
            
            
            String empName=DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(empId);
            
            empAppraisalVTO.setEmpName(empName);
            
        }catch(SQLException sqle){
            throw new ServiceLocatorException(sqle);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        //getProjectDetails();
        return empAppraisalVTO;
    }
    
    public  boolean addOrUpdateProjectDetails(EmployeeAppraisalAction empAppraisalAction, int empId,String operationalType)throws ServiceLocatorException {
        
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean flag=true;
        int updatedRows = 0;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spProjectDetails(?,?,?,?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            callableStatement.setInt(1,empId);
            callableStatement.setString(2,empAppraisalAction.getNameOfProject1());
            callableStatement.setString(3,empAppraisalAction.getModeOfWork1());
            callableStatement.setString(4,empAppraisalAction.getResourceType1());
            callableStatement.setDate(5,empAppraisalAction.getStartDate1());
            callableStatement.setDate(6,empAppraisalAction.getEndDate1());
            callableStatement.setString(7,empAppraisalAction.getComments1());
            callableStatement.setString(8,empAppraisalAction.getNameOfProject2());
            callableStatement.setString(9,empAppraisalAction.getModeOfWork2());
            callableStatement.setString(10,empAppraisalAction.getResourceType2());
            callableStatement.setDate(11,empAppraisalAction.getStartDate2());
            callableStatement.setDate(12,empAppraisalAction.getEndDate2());
            callableStatement.setString(13,empAppraisalAction.getComments2());
            callableStatement.setString(14,empAppraisalAction.getNameOfProject3());
            callableStatement.setString(15,empAppraisalAction.getModeOfWork3());
            callableStatement.setString(16,empAppraisalAction.getResourceType3());
            callableStatement.setDate(17,empAppraisalAction.getStartDate3());
            callableStatement.setDate(18,empAppraisalAction.getEndDate3());
            callableStatement.setString(19,empAppraisalAction.getComments3());
            callableStatement.setString(20,empAppraisalAction.getNameOfProject4());
            callableStatement.setString(21,empAppraisalAction.getModeOfWork4());
            callableStatement.setString(22,empAppraisalAction.getResourceType4());
            callableStatement.setDate(23,empAppraisalAction.getStartDate4());
            callableStatement.setDate(24,empAppraisalAction.getEndDate4());
            callableStatement.setString(25,empAppraisalAction.getComments4());
            callableStatement.setString(26,empAppraisalAction.getNameOfProject5());
            callableStatement.setString(27,empAppraisalAction.getModeOfWork5());
            callableStatement.setString(28,empAppraisalAction.getResourceType5());
            callableStatement.setDate(29,empAppraisalAction.getStartDate5());
            callableStatement.setDate(30,empAppraisalAction.getEndDate5());
            callableStatement.setString(31,empAppraisalAction.getComments5());
            callableStatement.setString(32,empAppraisalAction.getPrjHrOrTm());
            callableStatement.setString(33,empAppraisalAction.getPrjHrRating());
            if(operationalType=="Ins") {
                callableStatement.setString(34,"Ins");
                callableStatement.setInt(35,0);
                callableStatement.registerOutParameter(36,Types.INTEGER);
                updatedRows = callableStatement.executeUpdate();
                empAppraisalAction.setCurrentEmployeeId(callableStatement.getInt(36));
                
            } else {
                callableStatement.setString(34,"Upd");
                callableStatement.setInt(35,empAppraisalAction.getCurrentEmployeeId());
                callableStatement.registerOutParameter(36,Types.INTEGER);
                updatedRows = callableStatement.executeUpdate();
            }
            
            // updatedRows = callableStatement.executeUpdate();
            // empAppraisalAction.setCurrentEmployeeId(callableStatement.getInt(35));
            
            
            
        }catch(SQLException sqle){
            throw new ServiceLocatorException(sqle);}
        
        finally{
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        if(updatedRows==1){
            flag=false;
        }
        return flag;
    }
    
    public boolean addOrUpdateDetailsOfIndividuals(EmployeeAppraisalAction appraisalAction,String operationType) throws ServiceLocatorException {
        
        // this.appraisalAction = appraisalAction;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean isInserted=false;
        int x;
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{ call spEmpDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            callableStatement.setString(1,appraisalAction.getMonthsInPayroll());
            callableStatement.setString(2,appraisalAction.getLeaveHistory());
            callableStatement.setString(3,appraisalAction.getOnsiteProjectDuration());
            callableStatement.setString(4,appraisalAction.getOffshoreProjectDuration());
            callableStatement.setString(5,appraisalAction.getBenchDuration());
            callableStatement.setString(6,appraisalAction.getCorporateTrainingDuration());
            callableStatement.setString(7,appraisalAction.getInterviewsAttended());
            callableStatement.setString(8,appraisalAction.getCurrentSal());
            callableStatement.setString(9,appraisalAction.getPreviousHike());
            callableStatement.setString(10,appraisalAction.getVisaAvailable());
            callableStatement.setString(11,appraisalAction.getSkills());
            callableStatement.setString(12,appraisalAction.getHrComments());
            if(operationType.equalsIgnoreCase("ins")){
                callableStatement.setString(13,operationType);
                callableStatement.setInt(14,appraisalAction.getCurrentEmployeeId());
                callableStatement.registerOutParameter(15,Types.INTEGER);
                x = callableStatement.executeUpdate();
                appraisalAction.setCurrentEmployeeId(callableStatement.getInt(15));
                
            } else {
                callableStatement.setString(13,operationType);
                callableStatement.setInt(14,appraisalAction.getCurrentEmployeeId());
                callableStatement.registerOutParameter(15,Types.INTEGER);
                x = callableStatement.executeUpdate();
            }
            
            
            if(x==0){
                isInserted=false;
            }else{
                isInserted=true;
            }
            
        } catch(SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally {
            
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
            }
            return isInserted;
        }
    }
    
    /** self Assesment **/
    
    public int addOrUpdateEmployeeAssessment(EmployeeAppraisalAction empAppraisalAction,int userId,String operationType) throws ServiceLocatorException {
        int addRows =0;
//        String queryString = "insert into tblEmpAppraisal (PAttendance,PAHrComm,PARating,WorkAttitude,WAHrComm,WARating,Communication,CHrComm,CRating,WorkObjectives,WOHrComm,WORating,TeamWork,TWHrComm,TWRating,Planning,POHrComm,PORating,Adaptability,AVHrComm,AVRating,Achievements,AAHrComm,AARating,EmpId)" +
//                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("call spSelfAssessment(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            callableStatement.setInt(1,userId);
            callableStatement.setString(2,empAppraisalAction.getPAttendence());
            callableStatement.setString(3,empAppraisalAction.getPAttendHrComments());
            callableStatement.setString(4,empAppraisalAction.getPAttendHrRating());
            callableStatement.setString(5,empAppraisalAction.getWorkAttitude());
            callableStatement.setString(6,empAppraisalAction.getWAttHrComment());
            callableStatement.setString(7,empAppraisalAction.getWAttHrRating());
            callableStatement.setString(8,empAppraisalAction.getCommunication());
            callableStatement.setString(9,empAppraisalAction.getCommHrComments());
            callableStatement.setString(10,empAppraisalAction.getCommHrRating());
            callableStatement.setString(11,empAppraisalAction.getWorkObjectives());
            callableStatement.setString(12,empAppraisalAction.getWorkObjHrComm());
            callableStatement.setString(13,empAppraisalAction.getWorkObjHrRating());
            callableStatement.setString(14,empAppraisalAction.getCollaborationTW());
            callableStatement.setString(15,empAppraisalAction.getCollTWHrComm());
            callableStatement.setString(16,empAppraisalAction.getCollTWHrRating());
            callableStatement.setString(17,empAppraisalAction.getPlanOrganization());
            callableStatement.setString(18,empAppraisalAction.getPlanOrgHrComm());
            callableStatement.setString(19,empAppraisalAction.getPlanOrgHrRating());
            callableStatement.setString(20,empAppraisalAction.getAdaptVersatility());
            callableStatement.setString(21,empAppraisalAction.getAdaptVersatilityHrComm());
            callableStatement.setString(22,empAppraisalAction.getAdaptVersatilityHrRating());
            callableStatement.setString(23,empAppraisalAction.getAchievAppreciations());
            callableStatement.setString(24,empAppraisalAction.getAchievApprecHrComm());
            callableStatement.setString(25,empAppraisalAction.getAchievApprecHrRating());
            if(operationType == "Ins") {
                callableStatement.setString(26,"Ins");
                callableStatement.setInt(27,0);
                callableStatement.registerOutParameter(28,Types.INTEGER);
                addRows = callableStatement.executeUpdate();
                empAppraisalAction.setCurrentEmployeeId(callableStatement.getInt(28));
                
            } else if(operationType == "Upd") {
                callableStatement.setString(26,"Upd");
                callableStatement.setInt(27,empAppraisalAction.getCurrentEmployeeId());
                callableStatement.registerOutParameter(28,Types.INTEGER);
                addRows = callableStatement.executeUpdate();
            }
            int outPara = callableStatement.getInt(28);
            callableStatement.close();
            callableStatement = null;
        } catch (Exception sel) {
            System.err.println("Exception:["+sel);
        }finally{
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                System.err.println("Exception:["+se);
                // throw new ServiceLocatorException(se);
            }
        }
        
        return addRows;
    }
    /** team lead or hr or pm or  Vp  **/
    
    
    public boolean addOrUpdateTLComments(EmployeeAppraisalAction action,String operationType) throws ServiceLocatorException {
        Connection connection=null;
        CallableStatement  callableStatement =null;
        PreparedStatement preparedStatement=null;
        boolean isInserted = false;
        int returnRows = 0;
        String message =null;
        int appraisalId;
        if("TM".equalsIgnoreCase(action.getTM())) {
            
            try {
                connection = ConnectionProvider.getInstance().getConnection();
                callableStatement = connection.prepareCall("{call spAppraisalComments(?,?,?,?,?,?,?,?,?)}");
                callableStatement.setString(1,action.getTlcomments());
                callableStatement.setString(2,action.getEName());
                callableStatement.setString(3,action.getTlRating());
                //callableStatement.setDate(4,DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().usDateToSqldate(action.getCurrentDate())));
                callableStatement.setDate(4,action.getCurrentDate());
                callableStatement.setInt(5,action.getCurrentEmployeeId());
                callableStatement.setString(6,action.getTM());
                if(operationType == "Ins"){
                    callableStatement.setString(7,operationType);
                    callableStatement.registerOutParameter(8,Types.VARCHAR);
                    callableStatement.registerOutParameter(9,Types.INTEGER);
                    returnRows = callableStatement.executeUpdate();
                    message = callableStatement.getString(8);
                    appraisalId = callableStatement.getInt(9);
                    action.setCurrentEmployeeId(appraisalId);
                    action.setMessage(message);
                    if(appraisalId == 0){
                        isInserted = true;
                    }else if(appraisalId != 0){
                        isInserted = false;
                    }
                    //   isInserted = true;
                } else if(operationType == "Upd"){
                    callableStatement.setString(7,operationType);
                    callableStatement.registerOutParameter(8,Types.VARCHAR);
                    callableStatement.registerOutParameter(9,Types.INTEGER);
                    returnRows = callableStatement.executeUpdate();
                    message = callableStatement.getString(8);
                    action.setMessage(message);
                    if(returnRows > 0){
                        isInserted = true;
                    }else if(returnRows <= 0){
                        isInserted = false;
                    }
                }
                
            } catch(SQLException se) {
                throw new ServiceLocatorException(se);
            }finally {
                try{
                    if(callableStatement != null) {
                        callableStatement.close();
                        callableStatement = null;
                    }
                    if(connection != null) {
                        connection.close();
                        connection = null;
                    }
                }catch(SQLException se) {
                    throw new ServiceLocatorException(se);
                }
                
                
            }
        } else if("PM".equalsIgnoreCase(action.getPM())) {
            
            try {
                connection = ConnectionProvider.getInstance().getConnection();
                callableStatement = connection.prepareCall("{call spAppraisalComments(?,?,?,?,?,?,?,?,?)}");
                //callableStatement = connection.prepareCall("{call spUpdateAccountPrimary(?,?,?,?)}");
                // preparedStatement = connection.prepareStatement("insert into tblEmpAppraisal(TMComm,TMRating,TMCommDate) values('"+action.getTlcomments()+"','"+action.getTlRating()+"','"+action.getCurrentDate()+"') ");
                
                callableStatement.setString(1,action.getPmComments());
                callableStatement.setString(2,action.getPName());
                callableStatement.setString(3,action.getPRating());
                // callableStatement.setDate(4,DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().usDateToSqldate(action.getCurrentDate())));
                callableStatement.setDate(4,action.getCurrentDate());
                callableStatement.setInt(5,action.getCurrentEmployeeId());
                callableStatement.setString(6,action.getPM());
                if(operationType == "Ins"){
                    callableStatement.setString(7,operationType);
                    callableStatement.registerOutParameter(8,Types.VARCHAR);
                    callableStatement.registerOutParameter(9,Types.INTEGER);
                    returnRows = callableStatement.executeUpdate();
                    appraisalId = callableStatement.getInt(9);
                    message = callableStatement.getString(8);
                    action.setMessage(message);
                    action.setCurrentEmployeeId(appraisalId);
                    if(appraisalId == 0){
                        isInserted = true;
                    }else if(appraisalId != 0){
                        isInserted = false;
                    }
                } else if(operationType == "Upd"){
                    callableStatement.setString(7,operationType);
                    callableStatement.registerOutParameter(8,Types.VARCHAR);
                    callableStatement.registerOutParameter(9,Types.INTEGER);
                    returnRows = callableStatement.executeUpdate();
                    message = callableStatement.getString(8);
                    action.setMessage(message);
                    if(returnRows > 0){
                        isInserted = true;
                    }else if(returnRows <= 0){
                        isInserted = false;
                    }
                }
                
            } catch(SQLException se) {
                throw new ServiceLocatorException(se);
            }finally {
                try{
                    if(callableStatement != null) {
                        callableStatement.close();
                        callableStatement = null;
                    }
                    if(connection != null) {
                        connection.close();
                        connection = null;
                    }
                }catch(SQLException se) {
                    throw new ServiceLocatorException(se);
                }
                
            }
        }
        
        else if("HR".equalsIgnoreCase(action.getHR())) {
            
            try {
                connection = ConnectionProvider.getInstance().getConnection();
                callableStatement = connection.prepareCall("{call spAppraisalComments(?,?,?,?,?,?,?,?,?)}");
                //callableStatement = connection.prepareCall("{call spUpdateAccountPrimary(?,?,?,?)}");
                // preparedStatement = connection.prepareStatement("insert into tblEmpAppraisal(TMComm,TMRating,TMCommDate) values('"+action.getTlcomments()+"','"+action.getTlRating()+"','"+action.getCurrentDate()+"') ");
                
                callableStatement.setString(1,action.getHrComment());
                callableStatement.setString(2,action.getHName());
                callableStatement.setString(3,action.getHrRating());
                
                callableStatement.setDate(4,action.getCurrentDate());
                //callableStatement.setDate(4,DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().usDateToSqldate(action.getCurrentDate())));
                callableStatement.setInt(5,action.getCurrentEmployeeId());
                callableStatement.setString(6,action.getHR());
                
                if(operationType == "Ins"){
                    callableStatement.setString(7,operationType);
                    callableStatement.registerOutParameter(8,Types.VARCHAR);
                    callableStatement.registerOutParameter(9,Types.INTEGER);
                    returnRows = callableStatement.executeUpdate();
                    appraisalId = callableStatement.getInt(9);
                    message = callableStatement.getString(8);
                    action.setMessage(message);
                    action.setCurrentEmployeeId(appraisalId);
                    if(appraisalId == 0){
                        isInserted = true;
                    }else if(appraisalId != 0){
                        isInserted = false;
                    }
                    
                } else if(operationType == "Upd"){
                    callableStatement.setString(7,operationType);
                    callableStatement.registerOutParameter(8,Types.VARCHAR);
                    callableStatement.registerOutParameter(9,Types.INTEGER);
                    returnRows = callableStatement.executeUpdate();
                    message = callableStatement.getString(8);
                    action.setMessage(message);
                    if(returnRows > 0){
                        isInserted = true;
                    }else if(returnRows <= 0){
                        isInserted = false;
                    }
                }
            } catch(SQLException se) {
                throw new ServiceLocatorException(se);
            }finally {
                try{
                    if(callableStatement != null) {
                        callableStatement.close();
                        callableStatement = null;
                    }
                    if(connection != null) {
                        connection.close();
                        connection = null;
                    }
                }catch(SQLException se) {
                    throw new ServiceLocatorException(se);
                }
                
            }
        }
        
        else if("VP".equalsIgnoreCase(action.getVP())) {
            
            try {
                connection = ConnectionProvider.getInstance().getConnection();
                callableStatement = connection.prepareCall("{call spAppraisalComments(?,?,?,?,?,?,?,?,?)}");
                callableStatement.setString(1,action.getVpComments());
                callableStatement.setString(2,action.getVName());
                callableStatement.setString(3,action.getVpRating());
                callableStatement.setDate(4,action.getCurrentDate());
// callableStatement.setDate(4,DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().usDateToSqldate(action.getCurrentDate())));
                callableStatement.setInt(5,action.getCurrentEmployeeId());
                callableStatement.setString(6,action.getVP());
                
                if(operationType == "Ins"){
                    callableStatement.setString(7,operationType);
                    callableStatement.registerOutParameter(8,Types.VARCHAR);
                    callableStatement.registerOutParameter(9,Types.INTEGER);
                    returnRows = callableStatement.executeUpdate();
                    appraisalId = callableStatement.getInt(9);
                    message = callableStatement.getString(8);
                    action.setMessage(message);
                    action.setCurrentEmployeeId(appraisalId);
                    if(appraisalId == 0){
                        isInserted = true;
                    }else if(appraisalId != 0){
                        isInserted = false;
                    }
                    //     isInserted = true;
                } else if(operationType == "Upd"){
                    callableStatement.setString(7,operationType);
                    callableStatement.registerOutParameter(8,Types.VARCHAR);
                    callableStatement.registerOutParameter(9,Types.INTEGER);
                    returnRows = callableStatement.executeUpdate();
                    message = callableStatement.getString(8);
                    action.setMessage(message);
                    if(returnRows > 0){
                        isInserted = true;
                    }else if(returnRows <= 0){
                        isInserted = false;
                    }
                }
                if(returnRows > 0){
                    isInserted = true;
                }else if(returnRows <= 0){
                    isInserted = false;
                }
            } catch(SQLException se) {
                throw new ServiceLocatorException(se);
            }finally {
                try{
                    if(callableStatement != null) {
                        callableStatement.close();
                        callableStatement = null;
                    }
                    if(connection != null) {
                        connection.close();
                        connection = null;
                    }
                }catch(SQLException se) {
                    throw new ServiceLocatorException(se);
                }
                
            }
        }
        return isInserted;
    }
    
}



