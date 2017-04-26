 /*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.projects
 *
 * Date    : January 01, 2008, 12:35 PM
 *
 * Author  : Arjun Sanapathi<asanapathi@miraclesoft.com>
 *           Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : ProjectsServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.projects;

import com.mss.mirage.util.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

/**
 *
 * @author miracle
 */
public class ProjectServiceImpl implements ProjectService{
    
    /*
    private int insertedRows;
    private int isInserted;
    private int updatedRows;
    private Session hibernateSession;
    private Transaction transaction;
    private String queryString;
    private ProjectVTO projectVTO;
    private SubProjectVTO subProjectVTO;
    private MapVTO mapVTO;
    private IssueVTO issueVTO;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private CallableStatement callableStatement;
    private  Statement statement;
     
    private String generatedPath;
    private String Path;
     **/
    
    /** Creates a new instance of ProjectsServiceImpl */
    public ProjectServiceImpl() {
    }
    
    public int addOrUpdateProject(ProjectAction projectPojo, String operationMode) throws ServiceLocatorException {
        int insertedRows = 0;
        int isInserted;
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = ConnectionProvider.getInstance().getConnection();
            // callableStatement = connection.prepareCall("{call spProject(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            //  callableStatement = connection.prepareCall("{call spProject(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement = connection.prepareCall("{call spProjectNew(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            callableStatement.setString(1, projectPojo.getPrjName());
            callableStatement.setString(2, projectPojo.getDescription());
            if (projectPojo.getStartDateActual() == null || "".equals(projectPojo.getStartDateActual())) {
                //callableStatement.setDate(3, java.sql.Date.valueOf(DateUtility.getInstance().getCurrentSQLDate()));
                callableStatement.setDate(3, null);
            } else {
                // System.out.println("projectPojo.getStartDate().getTime()---->"+projectPojo.getStartDate().getTime());
                callableStatement.setDate(3, DateUtility.getInstance().getMysqlDate(projectPojo.getStartDateActual()));
            }
            if (projectPojo.getEndDateActual() == null || "".equals(projectPojo.getEndDateActual())) {
               // callableStatement.setDate(4, java.sql.Date.valueOf(DateUtility.getInstance().getCurrentSQLDate()));
                callableStatement.setDate(4, null);
            } else {
                callableStatement.setDate(4, DateUtility.getInstance().getMysqlDate(projectPojo.getEndDateActual()));
            }

            callableStatement.setInt(5, projectPojo.getTotalResources());
            callableStatement.setString(6, projectPojo.getPrjManagerUID());
            callableStatement.setString(7, projectPojo.getProjectType());
            callableStatement.setString(8, operationMode);
            callableStatement.setInt(9, projectPojo.getId());
            callableStatement.setInt(10, projectPojo.getCustomerId());
            callableStatement.setString(11, projectPojo.getStatus());
            if (operationMode.equals("add")) {
                callableStatement.setString(12, projectPojo.getCreatedBy());
                callableStatement.setTimestamp(13, projectPojo.getCreatedDate());

            } else if (operationMode.equals("update")) {
                callableStatement.setString(12, projectPojo.getModifiedBy());
                callableStatement.setTimestamp(13, projectPojo.getModifiedDate());
            }
            if (projectPojo.getIsDualReportingRequired()) {
                callableStatement.setInt(14, 1);
            } else {
                callableStatement.setInt(14, 0);
            }

            callableStatement.setString(15, projectPojo.getPreSalesMgrId());
            callableStatement.setString(16, projectPojo.getPmo());
            if (projectPojo.getStartDatePlan() == null || "".equals(projectPojo.getStartDatePlan())) {
               // callableStatement.setDate(17, java.sql.Date.valueOf(DateUtility.getInstance().getCurrentSQLDate()));
                callableStatement.setDate(17, null);
            } else {
                // System.out.println("projectPojo.getStartDate().getTime()---->"+projectPojo.getStartDate().getTime());
                 callableStatement.setDate(17, DateUtility.getInstance().getMysqlDate(projectPojo.getStartDatePlan()));
            }
            if (projectPojo.getEndDatePlan() == null || "".equals(projectPojo.getEndDatePlan())) {
              //  callableStatement.setDate(18, java.sql.Date.valueOf(DateUtility.getInstance().getCurrentSQLDate()));
                callableStatement.setDate(18, null);
            } else {
                callableStatement.setDate(18, DateUtility.getInstance().getMysqlDate(projectPojo.getEndDatePlan()));
            }

            callableStatement.setString(19, projectPojo.getPractice());
            callableStatement.setInt(20, projectPojo.getOnSitePlan());
            callableStatement.setInt(21, projectPojo.getOnSiteActual());
            callableStatement.setInt(22, projectPojo.getOffShorePlan());
            callableStatement.setInt(23, projectPojo.getOffShoreActual());
            callableStatement.setInt(24, projectPojo.getNearShorePlan());
            callableStatement.setInt(25, projectPojo.getNearShoreActual());
            callableStatement.setString(26, projectPojo.getCostModel());
            callableStatement.setString(27, projectPojo.getSector());
            callableStatement.setString(28, projectPojo.getComplexity());
            callableStatement.setString(29, projectPojo.getPriority());
            callableStatement.setString(30, projectPojo.getComments());
            callableStatement.setString(31, projectPojo.getSoftware());
            callableStatement.setString(32, projectPojo.getState());
              callableStatement.setString(33,projectPojo.getOffshoreDelLead());
            callableStatement.setString(34,projectPojo.getOffshoreTechLead());
            callableStatement.setString(35,projectPojo.getOnsiteLead());
            callableStatement.setString(36,projectPojo.getSchedule());
            callableStatement.setString(37,projectPojo.getRisk());
            callableStatement.setString(38,projectPojo.getResources());
            isInserted = callableStatement.executeUpdate();
            System.out.println("isInserted--->" + isInserted);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;

    }
    
    
    
    
    public int  assignTeamForProject(String[] assignedEmpIds, int accountId) throws ServiceLocatorException {
        
        int insertedRows = 0;
        int updateRows=0;
        int deletedRows = 0;
        Connection connection = null;
        Statement statement=null;
        String queryString = null;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            
            statement = connection.createStatement();
            
            
            /**
             *   it loops the roles.length and inserts the data into database for each addedVals
             *
             *   @throws  NullPointerException
             *          If a NullPointerException exists and its <code>{@link
             *          java.lang.NullPointerException}</code>
             */
            for(int counter=0; counter<assignedEmpIds.length; counter++) {
                 
           queryString = "Insert into tblProjectTeam(AccountId,EmpId) values(" + accountId +  ", " + assignedEmpIds[counter] +")";
             
                insertedRows = statement.executeUpdate(queryString);
            }
            
            
        }catch (Exception e){
            throw new ServiceLocatorException(e);
        }finally{
            try{
                if(statement != null){ statement.close();
                statement = null;
                }
                if(connection != null){connection.close();
                connection = null;
                }
                
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return insertedRows;
    }
    
    
    
    
    /* public int  upDateTeamForProject(String[] assignedEmpIds, int accountId, int projectId,String projectName) throws ServiceLocatorException {
        
        int insertedRows = 0;
        int updateRows=0;
        int deletedRows = 0;
        Connection connection = null;
        Statement statement=null;
        String queryString = null;
        try{
           connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            // The queryString is created  depends on the employeeId 
            queryString = "DELETE FROM tblProjectTeam WHERE ProjectId="+projectId;
            deletedRows = statement.executeUpdate(queryString);
            statement.close();
            statement = null;
            
            statement = connection.createStatement();
            
            
            //**
             *   it loops the roles.length and inserts the data into database for each addedVals
             *
             *   @throws  NullPointerException
             *          If a NullPointerException exists and its <code>{@link
             *          java.lang.NullPointerException}</code>
            //
            for(int counter=0; counter<assignedEmpIds.length; counter++) {
                 
           queryString = "Insert into tblProjectTeam(AccountId,EmpId,ProjectId,ProjectName)" +
                   " values(" + accountId +  ", " + assignedEmpIds[counter] +", "+ projectId +", '"+ projectName +"')";
                
                insertedRows = statement.executeUpdate(queryString);
            }
            
            
        }catch (Exception e){
            throw new ServiceLocatorException(e);
        }finally{
            try{
                if(statement != null){ statement.close();
                statement = null;
                }
                if(connection != null){connection.close();
                connection = null;
                }
                
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return insertedRows;
    }
    */
    
    public int  upDateTeamForProject(List assignedEmpIds, int accountId, int projectId,String projectName) throws ServiceLocatorException {
        
        int insertedRows = 0;
        int updateRows=0;
        int deletedRows = 0;
        Connection connection = null;
        Statement statement=null;
        String queryString = null;
        String queryString1 = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection1 = null;
        try{
           connection = ConnectionProvider.getInstance().getConnection();
            //statement = connection.createStatement();
            /** The queryString is created  depends on the employeeId */
           // queryString = "DELETE FROM tblProjectTeam WHERE ProjectId="+projectId;
            //deletedRows = statement.executeUpdate(queryString);
            //statement.close();
            //statement = null;
            connection1 = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection1.prepareStatement("DELETE FROM tblProjectTeam WHERE ProjectId="+projectId+" AND EmpId=?");
            statement = connection.createStatement();
            
            
            /**
             *   it loops the roles.length and inserts the data into database for each addedVals
             *
             *   @throws  NullPointerException
             *          If a NullPointerException exists and its <code>{@link
             *          java.lang.NullPointerException}</code>
             */
           // int countDeleted = 0;
          
                queryString1="SELECT EmpId FROM tblProjectTeam WHERE ProjectId="+projectId;
                deletedRows=0;
               // System.out.println("queryString1-->"+queryString1);
                resultSet=statement.executeQuery(queryString1);
                while(resultSet.next()){
                    int existedId=resultSet.getInt("EmpId");
                    boolean isAssigned=false;
                    for(int i=0;i<assignedEmpIds.size();i++){
                    String temp=assignedEmpIds.get(i).toString();
                  //  System.out.println("temp-->"+temp);
                    String temp1[]=temp.trim().split("\\|");
                   // System.out.println("assigned id before-->"+temp1[0]);
                   // System.out.println("EmpId id before-->"+existedId);
                    
                    if(Integer.parseInt(temp1[0]) == existedId) {
                       // System.out.println("assigned id-->"+temp1[0]);
                        isAssigned = true;
                        break;
                    }
                    }
                    if(isAssigned==false) {
                       // System.out.println("deleted123-->"+isAssigned);
                        preparedStatement.setInt(1, existedId);
                        preparedStatement.executeUpdate();
                        deletedRows++;
                    }
                }
            
            if(resultSet!=null){
            resultSet.close();
            }
            resultSet=null;
            //System.out.println("deletedRows-->"+deletedRows);
            if(!assignedEmpIds.isEmpty()){
                //System.out.println("in assignedEmpIds");
                for(int i=0;i<assignedEmpIds.size();i++){
                    statement = connection.createStatement();
                     // System.out.println("in for loop");
                    String temp=assignedEmpIds.get(i).toString();
                
                  
                     queryString1="SELECT Id FROM tblProjectTeam WHERE EmpId="+temp+" AND ProjectId="+projectId;
                   //  System.out.println("In queryString1-->"+queryString1);
                     resultSet=statement.executeQuery(queryString1);
                       int id = 0;
                     if(resultSet.next()) {
                     id=resultSet.getInt("Id");
                     }
                   //  System.out.println("Id----->"+id);
                     statement.close();
                     statement=null;
                     
                     if(id==0){
                         statement = connection.createStatement();
                     queryString = "Insert into tblProjectTeam(AccountId,EmpId,ProjectId,ProjectName)" +
                   " values(" + accountId +  ", " + temp +", "+ projectId +", '"+ projectName +"')";
                    // System.out.println("queryString-->"+queryString);
                     insertedRows = statement.executeUpdate(queryString);
                     statement.close();
                     statement=null;
                     
                     }
                }
            }
            
         
       }catch (Exception e){
            throw new ServiceLocatorException(e);
        }finally{
            try{
                 if(preparedStatement != null){ preparedStatement.close();
                preparedStatement = null;
                }
                 if(connection1 != null){connection1.close();
                connection1 = null;
                }
                 if(resultSet != null){ resultSet.close();
                resultSet = null;
                }
                if(statement != null){ statement.close();
                statement = null;
                }
                
                if(connection != null){connection.close();
                connection = null;
                }
                
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return insertedRows;
    }
    
    public String generatePath(String contextPath, String objectType) throws ServiceLocatorException {
        
        String generatedPath;
        
        Date date = new Date();
        String monthName = null;
        String weekName = null;
        
        /*The path which is created in the drive and used as a home directroy*/
        generatedPath = contextPath;
        
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
        short week = (short)(Math.round(date.getDate()/7));
        
        if(week == 0) weekName="WeekFirst";
        else if(week == 1) weekName="WeekSecond";
        else if(week == 2) weekName="WeekThird";
        else if(week == 3) weekName="WeekFourth";
        else if(week == 4) weekName="WeekFifth";
        
        /*getrequestType is used to create a directory of the object type specified in the jsp page*/
//        createPath=new File(createPath.getAbsolutePath()+"//MirageV2//"+getRequestType()+"//"+String.valueOf(date.getYear()+1900)+"//"+monthName+"//"+weekName);
        generatedPath = generatedPath+"/MirageV2/"+objectType+"/"
                +String.valueOf(date.getYear()+1900)
                +"/"+monthName+"/"+weekName;
        return generatedPath;
    }
    
    public ProjectVTO getProjectVTO(ProjectAction projectPojo) throws ServiceLocatorException {
        
        ProjectVTO projectVTO;
        projectVTO = new ProjectVTO();
        
         projectVTO.setId(projectPojo.getId());
        projectVTO.setCustomerId(projectPojo.getCustomerId());
        projectVTO.setPrjName(projectPojo.getPrjName());
        projectVTO.setProjectType(projectPojo.getProjectType());
        projectVTO.setStartDate(projectPojo.getStartDate());
        projectVTO.setEndDate(projectPojo.getEndDate());
        projectVTO.setPrjManagerUID(projectPojo.getPrjManagerUID());
        projectVTO.setDescription(projectPojo.getDescription());
        projectVTO.setTotalResources(projectPojo.getTotalResources());
        projectVTO.setStatus(projectPojo.getStatus());
        projectVTO.setIsDualReportingRequired(projectPojo.getIsDualReportingRequired());
        projectVTO.setPreSalesMgrId(projectPojo.getPreSalesMgrId());
        projectVTO.setPmo(projectPojo.getPmo());

        projectVTO.setStartDateActual(projectPojo.getStartDateActual());
        projectVTO.setStartDatePlan(projectPojo.getStartDatePlan());
        projectVTO.setEndDateActual(projectPojo.getEndDateActual());
        projectVTO.setEndDatePlan(projectPojo.getEndDatePlan());
        projectVTO.setPractice(projectPojo.getPractice());
        projectVTO.setOnSitePlan(projectPojo.getOnSitePlan());
        projectVTO.setOnSiteActual(projectPojo.getOnSiteActual());
        projectVTO.setOffShorePlan(projectPojo.getOffShorePlan());
        projectVTO.setOffShoreActual(projectPojo.getOffShoreActual());
        projectVTO.setNearShorePlan(projectPojo.getNearShorePlan());
        projectVTO.setNearShoreActual(projectPojo.getNearShoreActual());
        projectVTO.setCostModel(projectPojo.getCostModel());
        projectVTO.setSector(projectPojo.getSector());
        projectVTO.setComplexity(projectPojo.getComplexity());
        projectVTO.setPriority(projectPojo.getPriority());
        projectVTO.setComments(projectPojo.getComments());
        projectVTO.setSoftware(projectPojo.getSoftware());
        projectVTO.setState(projectPojo.getState());
         projectVTO.setOffshoreDelLead(projectPojo.getOffshoreDelLead());
        projectVTO.setOffshoreTechLead(projectPojo.getOffshoreTechLead());
        projectVTO.setOnsiteLead(projectPojo.getOnsiteLead());
        projectVTO.setSchedule(projectPojo.getSchedule());
        projectVTO.setRisk(projectPojo.getRisk());
        projectVTO.setResources(projectPojo.getResources());
        return projectVTO;
    }
    
    public ProjectVTO getProject(int id) throws ServiceLocatorException {
        
        ProjectVTO projectVTO;
        projectVTO = new ProjectVTO();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        if(id!=0) {
            try{
                connection = ConnectionProvider.getInstance().getConnection();
                preparedStatement = connection.prepareStatement("SELECT * FROM tblProjects WHERE Id=?");
                preparedStatement.setInt(1,id);
                resultSet = preparedStatement.executeQuery();
                projectVTO.setId(id);
                
                 while (resultSet.next()) {
                    projectVTO.setId(resultSet.getInt("Id"));
                    projectVTO.setDescription(resultSet.getString("ProjectDescription"));
                    projectVTO.setPrjManagerUID(resultSet.getString("ProjectManagerUID"));
                    // System.out.println("ProjectStartDate----->"+resultSet.getDate("ProjectStartDate"));
                    if (resultSet.getString("ProjectStartDate") != null && !"".equals(resultSet.getString("ProjectStartDate"))) {
                        projectVTO.setStartDateActual(DateUtility.getInstance().convertToviewFormat(resultSet.getString("ProjectStartDate")));
                    }
                    if (resultSet.getString("ProjectEndDate") != null && !"".equals(resultSet.getString("ProjectEndDate"))) {
                        projectVTO.setEndDateActual(DateUtility.getInstance().convertToviewFormat(resultSet.getString("ProjectEndDate")));
                    }


                    projectVTO.setPrjName(resultSet.getString("ProjectName"));
                    projectVTO.setTotalResources(resultSet.getInt("TotalResources"));
                    projectVTO.setProjectType(resultSet.getString("ProjectType"));
                    projectVTO.setCustomerId(resultSet.getInt("CustomerId"));
                    projectVTO.setStatus(resultSet.getString("Status"));
                    projectVTO.setIsDualReportingRequired(resultSet.getBoolean("Dualreporting"));
                    projectVTO.setPreSalesMgrId(resultSet.getString("PreSalesMgrId"));
                    projectVTO.setPmo(resultSet.getString("PMO"));

                    if (resultSet.getString("StartDatePlan") != null && !"".equals(resultSet.getString("StartDatePlan"))) {
                        projectVTO.setStartDatePlan(DateUtility.getInstance().convertToviewFormat(resultSet.getString("StartDatePlan")));
                    }
                    if (resultSet.getString("EndDatePlan") != null && !"".equals(resultSet.getString("EndDatePlan"))) {
                        projectVTO.setEndDatePlan(DateUtility.getInstance().convertToviewFormat(resultSet.getString("EndDatePlan")));
                    }
                    projectVTO.setPractice(resultSet.getString("Practice"));
                    projectVTO.setOnSitePlan(resultSet.getInt("OnSitePlan"));
                    projectVTO.setOnSiteActual(resultSet.getInt("OnSiteActual"));
                    projectVTO.setOffShorePlan(resultSet.getInt("OffShorePlan"));
                    projectVTO.setOffShoreActual(resultSet.getInt("OffShoreActual"));
                    projectVTO.setNearShorePlan(resultSet.getInt("NearShorePlan"));
                    projectVTO.setNearShoreActual(resultSet.getInt("NearShoreActual"));
                    projectVTO.setCostModel(resultSet.getString("CostModel"));
                    projectVTO.setSector(resultSet.getString("Sector"));
                    projectVTO.setComplexity(resultSet.getString("Complexity"));
                    projectVTO.setPriority(resultSet.getString("Priority"));
                    projectVTO.setComments(resultSet.getString("Comments"));
                    projectVTO.setSoftware(resultSet.getString("Software"));
                    projectVTO.setState(resultSet.getString("State"));
                    projectVTO.setOffshoreDelLead(resultSet.getString("OffshoreDelLead"));
        projectVTO.setOffshoreTechLead(resultSet.getString("OffshoreTechLead"));
        projectVTO.setOnsiteLead(resultSet.getString("OnsiteTechLead"));
        projectVTO.setSchedule(resultSet.getString("SCHEDULE"));
        projectVTO.setRisk(resultSet.getString("Risk"));
        projectVTO.setResources(resultSet.getString("Resources"));

                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }finally{
                try{
                    if(preparedStatement!=null){
                        preparedStatement.close();
                        preparedStatement = null;
                    }
                    if(resultSet!=null){
                        resultSet.close();
                        resultSet = null;
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
        return projectVTO;
    }
    
    public int addOrUpdateSubProject(ProjectAction projectPojo, String operationMode) throws ServiceLocatorException {
        
        Connection connection = null;
        CallableStatement callableStatement = null;
        int insertedRows;
        
        try {
            
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spSubProject(?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            callableStatement.setInt(1,projectPojo.getProjectId());
            callableStatement.setString(2,projectPojo.getTeamLeadUID());
            callableStatement.setString(3,projectPojo.getSubPrjName());
            callableStatement.setString(4,projectPojo.getCurrentState());
            if(projectPojo.getStartDate()==null){
                callableStatement.setDate(5,java.sql.Date.valueOf(DateUtility.getInstance().getCurrentSQLDate()));
            }else{
                callableStatement.setDate(5,new java.sql.Date(projectPojo.getStartDate().getTime()));
            }
            if(projectPojo.getEndDate()==null){
                callableStatement.setDate(6,java.sql.Date.valueOf(DateUtility.getInstance().getCurrentSQLDate()));
            }else{
                callableStatement.setDate(6,new java.sql.Date(projectPojo.getEndDate().getTime()));
            }
            callableStatement.setInt(7,projectPojo.getTotalResources());
            callableStatement.setString(8,projectPojo.getProjectType());
            callableStatement.setString(9,projectPojo.getDescription());
            callableStatement.setString(10,operationMode);
            callableStatement.setInt(11,projectPojo.getSubProjectId());
            callableStatement.setInt(12,projectPojo.getAccountId());
            
            insertedRows = callableStatement.executeUpdate();
            
        }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
        }finally{
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
        return insertedRows;
    }
    
    public SubProjectVTO getSubProjectVTO(ProjectAction projectPojo) throws ServiceLocatorException {
        
        SubProjectVTO subProjectVTO;
        subProjectVTO = new SubProjectVTO();
        
        subProjectVTO.setSubProjectId(projectPojo.getSubProjectId());
        subProjectVTO.setProjectId(projectPojo.getProjectId());
        subProjectVTO.setCurrentState(projectPojo.getCurrentState());
        subProjectVTO.setDescription(projectPojo.getDescription());
        subProjectVTO.setEndDate(projectPojo.getEndDate());
        subProjectVTO.setStartDate(projectPojo.getStartDate());
        subProjectVTO.setSubPrjName(projectPojo.getSubPrjName());
        subProjectVTO.setTotalResources(projectPojo.getTotalResources());
        subProjectVTO.setTeamLeadUID(projectPojo.getTeamLeadUID());
        subProjectVTO.setProjectType(projectPojo.getProjectType());
        
        return subProjectVTO;
        
    }
    
    public MapVTO getMapVTO(ProjectAction projectAction) throws ServiceLocatorException{
        MapVTO mapVTO;
        mapVTO = new MapVTO();
        
        mapVTO.setProjectNames(projectAction.getProjectNames());
        mapVTO.setSubProjectNames(projectAction.getSubProjectNames());
        mapVTO.setMapName(projectAction.getMapName());
        mapVTO.setCurrentState(projectAction.getCurrentState());
        mapVTO.setStartDate(projectAction.getStartDate());
        mapVTO.setEndDate(projectAction.getEndDate());
        mapVTO.setBussinessDomain(projectAction.getBussinessDomain());
        mapVTO.setMapTools(projectAction.getMapTools());
        mapVTO.setProjectManager(projectAction.getProjectManager());
        mapVTO.setTechLead(projectAction.getTechLead());
        mapVTO.setMapper(projectAction.getMapper());
        mapVTO.setTester(projectAction.getTester());
        mapVTO.setDescription(projectAction.getDescription());
        
        return mapVTO;
    }
    
    public IssueVTO getIssueVTO(ProjectAction projectAction) throws ServiceLocatorException{
        IssueVTO issueVTO;
        issueVTO = new IssueVTO();
        
        //issueVTO.setMapId();
        issueVTO.setProjectId(projectAction.getProjectId());
        issueVTO.setSubProjectId(projectAction.getSubProjectId());
        issueVTO.setAccountId(projectAction.getAccountId());
        issueVTO.setIssueTypes(projectAction.getIssueTypes());
        issueVTO.setDatesCreated(projectAction.getDatesCreated());
        issueVTO.setDescriptions(projectAction.getDescriptions());
        issueVTO.setIssueStates(projectAction.getIssueStates());
        issueVTO.setAssignedToUIDs(projectAction.getAssignedToUIDs());
        issueVTO.setIssueNames(projectAction.getIssueNames());
        issueVTO.setCreatedBy(projectAction.getCreatedBy());
        
        return issueVTO;
    }
    
    public SubProjectVTO getSubProject(int subProjectId) throws ServiceLocatorException {
        SubProjectVTO subProjectVTO;
        subProjectVTO = new SubProjectVTO();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM tblSubProjects WHERE Id=?");
            preparedStatement.setInt(1,subProjectId);
            resultSet = preparedStatement.executeQuery();
            
            subProjectVTO.setSubProjectId(subProjectId);
            while(resultSet.next()){
                subProjectVTO.setProjectId(resultSet.getInt("ProjectId"));
                subProjectVTO.setCurrentState(resultSet.getString("CurStatus"));
                subProjectVTO.setDescription(resultSet.getString("Description"));
                subProjectVTO.setStartDate(resultSet.getDate("StartDate"));
                subProjectVTO.setEndDate(resultSet.getDate("EndDate"));
                subProjectVTO.setSubPrjName(resultSet.getString("SubProjectName"));
                subProjectVTO.setTotalResources(resultSet.getInt("TeamSize"));
                subProjectVTO.setTeamLeadUID(resultSet.getString("TeamLeadUID"));
                subProjectVTO.setProjectType(resultSet.getString("ProjectType"));
            }
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return  subProjectVTO;
        
    }
    
    public MapVTO getMapProject(int mapId) throws ServiceLocatorException{
        MapVTO mapVTO;
        mapVTO = new MapVTO();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM tblMaps WHERE Id=?");
            preparedStatement.setInt(1,mapId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                mapVTO.setProjectNames(resultSet.getString("ProjectId"));
                mapVTO.setSubProjectNames(resultSet.getString("SubProjectId"));
                
                mapVTO.setMapName(resultSet.getString("MapName"));
                mapVTO.setCurrentState(resultSet.getString("CurrentState"));
                
                mapVTO.setStartDate(resultSet.getDate("MapStartDate"));
                mapVTO.setEndDate(resultSet.getDate("MapEndDate"));
                
                mapVTO.setBussinessDomain(resultSet.getString("BusinessDomain"));
                mapVTO.setMapTools(resultSet.getString("MappingToolName"));
                
                mapVTO.setProjectManager(resultSet.getString("ProjectManagerUID"));
                mapVTO.setTechLead(resultSet.getString("TechLeadUID"));
                mapVTO.setMapper(resultSet.getString("MapperUID"));
                
                mapVTO.setTester(resultSet.getString("TesterUID"));
                mapVTO.setDescription(resultSet.getString("Description"));
            }
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return mapVTO;
    }
    
    public boolean insertAttachment(ProjectAction projectAction) throws ServiceLocatorException{
        
        boolean isInserted = false;
        try {
            
            Session session = HibernateServiceLocator.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            transaction.begin();
            session.save(projectAction);
            session.flush();
            transaction.commit();
            isInserted = true;
            
            session.close();
            
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return isInserted;
    }
    
    public String getPath(int attachmentId) throws ServiceLocatorException{
        String Path = null;
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select h.Path from DownloadAction as h where h.id=:attachmentId";
        
        Query query = session.createQuery(SQL_QUERY).setInteger("attachmentId",attachmentId);
        for(Iterator it=query.iterate();it.hasNext();){
            Path = (String) it.next();
        }//end of the for loop
        
        
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        throw new ServiceLocatorException(he);
                    }
                }
          
        
        return Path;
    }
    
    
    public int addMap(ProjectAction projectAction) throws ServiceLocatorException{
        int isInserted = 0;
        String queryString;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "INSERT INTO tblMaps (CustomerId,MapName,TesterUID,MapperUID,ProjectId,SubProjectId,MappingToolName,ProjectManagerUID,TechLeadUID,CurrentState,Description,MapStartDate,MapEndDate,BusinessDomain) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryString);
            
            preparedStatement.setInt(1,projectAction.getAccountId());
            preparedStatement.setString(2,projectAction.getMapName());
            preparedStatement.setString(3,projectAction.getTester());
            preparedStatement.setString(4,projectAction.getMapper());
            preparedStatement.setInt(5,projectAction.getId());
            preparedStatement.setInt(6,projectAction.getSubProjectId());
            preparedStatement.setString(7,projectAction.getMapTools());
            preparedStatement.setString(8,projectAction.getProjectManager());
            preparedStatement.setString(9,projectAction.getTechLead());
            preparedStatement.setString(10,projectAction.getCurrentState());
            preparedStatement.setString(11,projectAction.getDescription());
            if(projectAction.getStartDate()==null){
                preparedStatement.setDate(12,java.sql.Date.valueOf(DateUtility.getInstance().getCurrentSQLDate()));
            }else{
                preparedStatement.setDate(12,projectAction.getStartDate());
            }
            if(projectAction.getEndDate()==null){
                preparedStatement.setDate(13,java.sql.Date.valueOf(DateUtility.getInstance().getCurrentSQLDate()));
            }else{
                preparedStatement.setDate(13,projectAction.getEndDate());
            }
            preparedStatement.setString(14,projectAction.getBussinessDomain());
            
            isInserted = preparedStatement.executeUpdate();
            
        } catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;
    }
    
    public int updateMap(ProjectAction projectAction) throws ServiceLocatorException{
        
        String queryString;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        int isInserted = 0;
        int rowUpdate = projectAction.getId();
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "UPDATE tblMaps SET CustomerId=?,MapName=?,TesterUID=?,MapperUID=?,ProjectId=?,SubProjectId=?,MappingToolName=?,ProjectManagerUID=?,TechLeadUID=?,CurrentState=?,Description=?,MapStartDate=?,MapEndDate=?,BusinessDomain=? WHERE Id="+rowUpdate;
            
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1,projectAction.getAccountId());
            preparedStatement.setString(2,projectAction.getMapName());
            preparedStatement.setString(3,projectAction.getTester());
            preparedStatement.setString(4,projectAction.getMapper());
            preparedStatement.setInt(5,projectAction.getProjectId());
            preparedStatement.setInt(6,projectAction.getSubProjectId());
            preparedStatement.setString(7,projectAction.getMapTools());
            preparedStatement.setString(8,projectAction.getProjectManager());
            preparedStatement.setString(9,projectAction.getTechLead());
            preparedStatement.setString(10,projectAction.getCurrentState());
            preparedStatement.setString(11,projectAction.getDescription());
            preparedStatement.setDate(12,new java.sql.Date(projectAction.getStartDate().getTime()));
            preparedStatement.setDate(13,new java.sql.Date(projectAction.getEndDate().getTime()));
            preparedStatement.setString(14,projectAction.getBussinessDomain());
            
            isInserted = preparedStatement.executeUpdate();
            
        } catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;
    }
    
    public int addIssue(ProjectAction projectPojo) throws ServiceLocatorException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString;
        int isInserted = 0;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            
            queryString = "INSERT INTO tblMapIssues(MapId,ProjectId,IssueType,DateCreated,Description,IssueState,AssignedToUID,CreatedBy,SubProjectId,IssueName,CustomerId) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryString);
            
            preparedStatement.setInt(1,projectPojo.getMapId());
            preparedStatement.setInt(2,projectPojo.getProjectId());
            preparedStatement.setString(3,projectPojo.getIssueTypes());
            preparedStatement.setDate(4,projectPojo.getDatesCreated());
            preparedStatement.setString(5,projectPojo.getDescriptions());
            
            preparedStatement.setString(6,projectPojo.getIssueStates());
            preparedStatement.setString(7,projectPojo.getAssignedToUIDs());
            preparedStatement.setString(8,projectPojo.getCreatedBy());
            preparedStatement.setInt(9,projectPojo.getSubProjectId());
            
            preparedStatement.setString(10,projectPojo.getIssueNames());
            preparedStatement.setInt(11,projectPojo.getAccountId());
            
            isInserted = preparedStatement.executeUpdate();
            
        } catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;
    }
    
    
    public IssueVTO getIssues(int issueId) throws ServiceLocatorException{
        
        IssueVTO issueVTO;
        issueVTO = new IssueVTO();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM tblMapIssues WHERE Id=?");
            preparedStatement.setInt(1,issueId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                issueVTO.setMapId(resultSet.getInt("MapId"));
                issueVTO.setProjectId(resultSet.getInt("ProjectId"));
                issueVTO.setSubProjectId(resultSet.getInt("SubProjectId"));
                issueVTO.setAccountId(resultSet.getInt("CustomerId"));
                issueVTO.setIssueTypes(resultSet.getString("IssueType"));
                //issueVTO.setDatesCreatedOne(resultSet.getDate("DateCreated").toString());
                
                if(resultSet.getDate("DateCreated") != null){
                    issueVTO.setDatesCreatedOne(resultSet.getDate("DateCreated").toString());
                } 
                issueVTO.setDescriptions(resultSet.getString("Description"));
                issueVTO.setIssueStates(resultSet.getString("IssueState"));
                issueVTO.setAssignedToUIDs(resultSet.getString("AssignedToUID"));
                issueVTO.setIssueNames(resultSet.getString("IssueName"));
                issueVTO.setCreatedBy(resultSet.getString("CreatedBy"));
            }
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return issueVTO;
        
    }
    
    
    public int updateIssue(ProjectAction projectAction) throws ServiceLocatorException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString;
        
        int isInserted = 0;
        int rowUpdate = projectAction.getIssueId();
        
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            
            queryString = "UPDATE tblMapIssues SET MapId=?,ProjectId=?,IssueType=?,DateCreated=?,Description=?,IssueState=?,AssignedToUID=?,CreatedBy=?,SubProjectId=?,IssueName=?,CustomerId=? WHERE Id="+rowUpdate;
            
            preparedStatement = connection.prepareStatement(queryString);
            
            preparedStatement.setInt(1,projectAction.getMapId());
            preparedStatement.setInt(2,projectAction.getProjectId());
            preparedStatement.setString(3,projectAction.getIssueTypes());
            
            if(projectAction.getDatesCreated() != null){
            preparedStatement.setDate(4,new java.sql.Date(projectAction.getDatesCreated().getTime()));
            }else if(projectAction.getDatesCreated() == null){
            preparedStatement.setDate(4,projectAction.getDatesCreated());
            }
            
            preparedStatement.setString(5,projectAction.getDescriptions());
            
            preparedStatement.setString(6,projectAction.getIssueStates());
            preparedStatement.setString(7,projectAction.getAssignedToUIDs());
            preparedStatement.setString(8,projectAction.getCreatedBy());
            preparedStatement.setInt(9,projectAction.getSubProjectId());
            
            preparedStatement.setString(10,projectAction.getIssueNames());
            preparedStatement.setInt(11,projectAction.getId());
            
            isInserted = preparedStatement.executeUpdate();
            
        } catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;
    }
    
    public Map getProjectTeamByProjectId(int projectId) throws ServiceLocatorException{
        Map projectTeamMap = new HashMap();
        projectTeamMap.clear();
        
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        
        try {
            statement = connection.createStatement();
            
            //if(!("".equals(primaryTeamMember))){
                if(projectId >0){
                resultSet = statement.executeQuery("select * from vwProjectTeamEmployees where ProjectId="+projectId );
                        
                while(resultSet.next()) {
                    projectTeamMap.put(resultSet.getString("ProjectId"),resultSet.getString("EmpName"));
                }
                resultSet.close();
                resultSet = null;
            }
            
            
            
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            }catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return projectTeamMap;
    }
    
    /*Method For Get Project resource available list
     * Author : Santosh Kola
     * Date : 14/03/2014
     */
      public Map getProjectResourceMap(int projectId) throws ServiceLocatorException
    {
    Map resourceMap = new TreeMap();
     ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        
        try {
            statement = connection.createStatement();
            
            //if(!("".equals(primaryTeamMember))){
                if(projectId >0){
                //resultSet = statement.executeQuery("select EmpId,EmpType from tblProjectTeam where ProjectId="+projectId );
                    
                    //System.out.println("Query-->select EmpId,Concat(FirstName,'.',LastName) as Name,ReportsTo from tblCrmContact left outer join tblProjectTeam on (tblCrmContact.Id = tblProjectTeam.EmpId) where ProjectId="+projectId+" AND Designation != 'DR' AND ReportsTo = 0");
                    resultSet = statement.executeQuery("select EmpId,Concat(FirstName,'.',LastName) as Name,ReportsTo from tblCrmContact left outer join tblProjectTeam on (tblCrmContact.Id = tblProjectTeam.EmpId) where ProjectId="+projectId+" AND Designation != 'DR' " );
                        while(resultSet.next()) {
                            if(resultSet.getInt("ReportsTo") ==0)
                            resourceMap.put(resultSet.getInt("EmpId"),resultSet.getString("Name"));
                        }
               
                 
             
            }
            
            
            
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            }catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
    
    return resourceMap;
    }
     public Map getProjectReportsToMap(int projectId) throws ServiceLocatorException
    {
    Map resourceReportsToMap = new TreeMap();
     ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        
        try {
            statement = connection.createStatement();
            
            //if(!("".equals(primaryTeamMember))){
                if(projectId >0){
                //resultSet = statement.executeQuery("select EmpId,EmpType from tblProjectTeam where ProjectId="+projectId );
                   // System.out.println("reports to quesry-->select EmpId,Concat(FirstName,'.',LastName) as Name from tblCrmContact left outer join tblProjectTeam on (tblCrmContact.Id = tblProjectTeam.EmpId) where ProjectId="+projectId+" AND Designation != 'CU' AND Designation != 'CN'");
                    resultSet = statement.executeQuery("select EmpId,Concat(FirstName,'.',LastName) as Name from tblCrmContact left outer join tblProjectTeam on (tblCrmContact.Id = tblProjectTeam.EmpId) where ProjectId="+projectId+" AND Designation != 'CU' AND Designation != 'CN'" );
                        while(resultSet.next()) {
                            resourceReportsToMap.put(resultSet.getInt("EmpId"),resultSet.getString("Name"));
                        }
               
                 
             
            }
            
            
            
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            }catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
    
    return resourceReportsToMap;
    }
     
      public int updateProjectResourceReportsTo(int projectEmpId, int reportsToId,int projectId)  throws ServiceLocatorException
 {
   
   
   PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        String designation=null;
        int isUpdated=0;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "update tblProjectTeam set ReportsTo="+reportsToId+" where EmpId="+projectEmpId+" and ProjectId="+projectId;
          //  System.out.println("query1------->"+queryString);
            //queryString ="SELECT tblEmployee.id,FName,LName,IsTeamLead,IsManager FROM tblEmployee LEFT JOIN tblProjectTeam ON(tblEmployee.Id = tblProjectTeam.EmpId) WHERE tblProjectTeam.ProjectId = 187";
            preparedStatement = connection.prepareStatement(queryString);
            isUpdated=preparedStatement.executeUpdate();
            
            }
        catch (SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally{
            try {
           
                
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
            
        }
     return isUpdated;   

 }
 //New by Ajay Tummapala for Project Team adding 
      // Modified by Teja Kadamanti Date : 09/23/2016
   public String doAddEmployeeProject(ProjectAction projectPojo, String loginId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;

        int isInserted = 0;
        int result=0;
        int responseId;
         String response="";
        /*    String queryString = "INSERT INTO tblProjectContacts(ObjectId,ObjectType,AccountId,ProjectId,"
         + "ResourceName,Email,Billable,StartDate,EndDate,STATUS,ResourceType,ResourceTitle,CreatedBy,"
         + "CreatedDate,Utilization,RateType,Rate,ReportsTo,WorkPhone,MobilePhone,IsPMO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
         */

        /*String queryString = "INSERT INTO tblProjectContacts(ObjectId,ObjectType,AccountId,ProjectId,"
         + "ResourceName,Email,Billable,StartDate,EndDate,STATUS,ResourceType,ResourceTitle,CreatedBy,"
         + "CreatedDate,Utilization,RateType,Rate,ReportsTo,WorkPhone,MobilePhone) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
         */
//        String queryString = "INSERT INTO tblProjectContacts(ObjectId,ObjectType,AccountId,ProjectId,"
//                + "ResourceName,Email,Billable,StartDate,EndDate,STATUS,ResourceType,ResourceTitle,CreatedBy,"
//                + "CreatedDate,Utilization,RateType,Rate,ReportsTo,WorkPhone,MobilePhone,SecondReportTo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
          //  System.out.println("projectPojo.getDateClosed()--->" + projectPojo.getDateClosed());
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spAddEmployeeToProject(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            //preparedStatement = connection.prepareStatement(queryString);
           // System.out.println("getPreAssignEmpId-->"+projectPojo.getPreAssignEmpId());
            callableStatement.setInt(1, Integer.parseInt(projectPojo.getPreAssignEmpId()));
            if (projectPojo.getResType().equalsIgnoreCase("e")) {
                callableStatement.setString(2, projectPojo.getResType());
            } else {
                String accountType = DataSourceDataProvider.getInstance().getAccountTypeById(projectPojo.getAccountId());
                if (accountType.equalsIgnoreCase("customer")) {
                    callableStatement.setString(2, "C");
                } else if (accountType.equalsIgnoreCase("Vendor")) {
                    callableStatement.setString(2, "V");
                } else {
                    callableStatement.setString(2, "O");
                }
            }
            callableStatement.setInt(3, projectPojo.getAccountId());
            callableStatement.setInt(4, projectPojo.getProjectId());
            callableStatement.setString(5, projectPojo.getAssignedToUID());
            callableStatement.setString(6, projectPojo.getEmail());
            callableStatement.setBoolean(7, projectPojo.getIsBillable());
            //System.out.println("projectPojo.getDateAssigned()-->"+projectPojo.getDateAssigned());
            if (projectPojo.getDateAssigned() != null && !"".equals(projectPojo.getDateAssigned())) {
                callableStatement.setDate(8, DateUtility.getInstance().getMysqlDate(projectPojo.getDateAssigned()));
                // preparedStatement.setDate(8, new java.sql.Date(projectPojo.getDateAssigned().getTime()));

            } else {
                callableStatement.setDate(8, null);
            }
            if (projectPojo.getDateClosed() != null && !"".equals(projectPojo.getDateClosed())) {
                callableStatement.setDate(9, DateUtility.getInstance().getMysqlDate(projectPojo.getDateClosed()));
                //          preparedStatement.setDate(9, new java.sql.Date(projectPojo.getDateClosed().getTime()));
            } else {
                callableStatement.setDate(9, null);
            }
            //preparedStatement.setString(8,dateUtil.strToTimeStampObj(employeePojo.getDateAssigned()));

            callableStatement.setString(10, projectPojo.getStatus());
            callableStatement.setBoolean(11, projectPojo.getIsPrimary());
            callableStatement.setString(12, projectPojo.getResTitle());
            callableStatement.setString(13, loginId);
            callableStatement.setTimestamp(14, DateUtility.getInstance().getCurrentMySqlDateTime());
            callableStatement.setInt(15, Integer.parseInt(projectPojo.getUtilisation()));
            callableStatement.setString(16, projectPojo.getRateType());
            callableStatement.setString(17, projectPojo.getCurrency());
            callableStatement.setInt(18, projectPojo.getProjectReportsTo());
            callableStatement.setString(19, projectPojo.getWorkPhone());
            callableStatement.setString(20, projectPojo.getMobilePhone());
            callableStatement.setInt(21, projectPojo.getSecProjectReportsTo());
            callableStatement.setInt(22, projectPojo.getId());
            callableStatement.setString(23, projectPojo.getTester());
            callableStatement.setString(24, projectPojo.getComments());
            callableStatement.setString(25, projectPojo.getSkillSet());
               if (projectPojo.getBillableStartDate() != null && !"".equals(projectPojo.getBillableStartDate())) {
                callableStatement.setDate(26, DateUtility.getInstance().getMysqlDate(projectPojo.getBillableStartDate()));
                } else {
                callableStatement.setDate(26, null);
               }
              if (projectPojo.getBillableEndDate() != null && !"".equals(projectPojo.getBillableEndDate())) {
                callableStatement.setDate(27, DateUtility.getInstance().getMysqlDate(projectPojo.getBillableEndDate()));
                } else {
                callableStatement.setDate(27, null);
               }
             callableStatement.setString(28, projectPojo.getEmpProjectStatus());
            callableStatement.registerOutParameter(29, Types.VARCHAR);
              callableStatement.registerOutParameter(30, Types.INTEGER);
            isInserted = callableStatement.executeUpdate();
           response=callableStatement.getString(29);
           responseId=callableStatement.getInt(30);

           
           //System.out.println("responseId-->"+responseId);
            //projectPojo.setId(DataSourceDataProvider.getInstance().getProjectContactsIdFromAccoundId(projectPojo.getAccountId(), projectPojo.getProjectId(), projectPojo.getPreAssignEmpId()));
             projectPojo.setId(responseId);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return response;

    }    

  public void getProjectTeamDetails(int Id,ProjectAction projectAction) throws ServiceLocatorException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DateUtility dateUtil;
       // TasksVTO tasksVTO = new TasksVTO();
        String attachmentId="";
        String sqlQuery = "SELECT * FROM tblProjectContacts where Id=" + Id;
        dateUtil = DateUtility.getInstance();
        String uploadFileName = null;
        String category = null;
        int taskId=0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
           // tasksVTO.setId(Integer.parseInt(TaskId));
            while(resultSet.next()){
                projectAction.setId(resultSet.getInt("Id"));
                projectAction.setCustomerId(resultSet.getInt("AccountId"));
                //employeeAction.setCustomer(String.valueOf(resultSet.getInt("ObjectId")));
                projectAction.setProjectId(resultSet.getInt("ProjectId"));
                projectAction.setResType(resultSet.getString("ObjectType"));
                projectAction.setAssignedToUID(resultSet.getString("ResourceName"));
                projectAction.setPreAssignEmpId(String.valueOf(resultSet.getInt("ObjectId")));
                projectAction.setResTitle(resultSet.getString("ResourceTitle"));
                projectAction.setStatus(resultSet.getString("STATUS"));
                if(resultSet.getString("StartDate")!=null){
              //projectAction.setDateAssigned(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("StartDate")));
                    String startDate=dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("StartDate"));
                   // System.out.println("startDate-->"+startDate);
                    StringTokenizer st = new StringTokenizer(startDate, " ");
                    String actStartDate = st.nextToken();
                   //System.out.println("actStartDate-->"+actStartDate);
                   
                    projectAction.setDateAssigned(actStartDate);
                //     projectAction.setDateAssigned(resultSet.getDate("StartDate"));
                }else{
                   projectAction.setDateAssigned(null);
                }
                if(resultSet.getString("EndDate")!=null){
                  // projectAction.setDateClosed(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("EndDate")));
                     String endDate=dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("EndDate"));
                     StringTokenizer st = new StringTokenizer(endDate, " ");
                    String actEndDate = st.nextToken();
                  //  System.out.println("actStartDate-->"+actEndDate);
                    projectAction.setDateClosed(actEndDate);
             //   projectAction.setDateClosed(resultSet.getDate("EndDate"));
                }else
                {
                    projectAction.setDateClosed(null);
                }
                projectAction.setIsPrimary(resultSet.getBoolean("ResourceType"));
                projectAction.setIsBillable(resultSet.getBoolean("Billable"));
                projectAction.setEmail(resultSet.getString("Email"));
                projectAction.setCustomerName(DataSourceDataProvider.getInstance().getAccountName(projectAction.getCustomerId()));
                projectAction.setProjectName(DataSourceDataProvider.getInstance().getProjectName(projectAction.getProjectId()));
                projectAction.setUtilisation(String.valueOf(resultSet.getInt("Utilization")));
                projectAction.setRateType(resultSet.getString("RateType"));
                projectAction.setCurrency(resultSet.getString("Rate"));
                projectAction.setProjectReportsTo(resultSet.getInt("ReportsTo"));
                projectAction.setWorkPhone(resultSet.getString("WorkPhone"));
                projectAction.setMobilePhone(resultSet.getString("MobilePhone"));
                projectAction.setSecProjectReportsTo(resultSet.getInt("SecondReportTo"));
                projectAction.setComments(resultSet.getString("Comments"));
                projectAction.setSkillSet(resultSet.getString("SkillSet"));
                  if(resultSet.getDate("BillableStartDate")!=null){
                  projectAction.setBillableStartDate(DateUtility.getInstance().convertDateToView(resultSet.getDate("BillableStartDate")));
                  }else{
                     projectAction.setBillableStartDate(null);  
                  }
                   if(resultSet.getDate("BillableEndDate")!=null){
                  projectAction.setBillableEndDate(DateUtility.getInstance().convertDateToView(resultSet.getDate("BillableEndDate")));
                  }else{
                     projectAction.setBillableEndDate(null);  
                  }
                    projectAction.setEmpProjectStatus(resultSet.getString("EmpProjStatus"));
                    
             //   projectAction.setIsPMO(resultSet.getBoolean("IsPMO"));
            }
       

        } catch(SQLException se){
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null){
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
        }    

      
    public int doUpdateProjectTeam(ProjectAction projectPojo,String loginId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        int isInserted = 0;
    /*    String queryString = "UPDATE tblProjectContacts SET ObjectId=?,ObjectType=?,AccountId=?,ProjectId=?,"
                + "ResourceName=?,Email=?,Billable=?,StartDate=?,EndDate=?,STATUS=?,ResourceType=?,ResourceTitle=?,ModifiedBy=?,"
                + "ModifiedDate=?,Utilization=?,RateType=?,Rate=?,ReportsTo=?,WorkPhone=?,MobilePhone=?,IsPMO=? WHERE Id=?";*/
     /*  String queryString = "UPDATE tblProjectContacts SET ObjectId=?,ObjectType=?,AccountId=?,ProjectId=?,"
                + "ResourceName=?,Email=?,Billable=?,StartDate=?,EndDate=?,STATUS=?,ResourceType=?,ResourceTitle=?,ModifiedBy=?,"
                + "ModifiedDate=?,Utilization=?,RateType=?,Rate=?,ReportsTo=?,WorkPhone=?,MobilePhone=? WHERE Id=?";
         * 
         */
        String queryString = "UPDATE tblProjectContacts SET ObjectId=?,ObjectType=?,AccountId=?,ProjectId=?,"
                + "ResourceName=?,Email=?,Billable=?,StartDate=?,EndDate=?,STATUS=?,ResourceType=?,ResourceTitle=?,ModifiedBy=?,"
                + "ModifiedDate=?,Utilization=?,RateType=?,Rate=?,ReportsTo=?,WorkPhone=?,MobilePhone=?,SecondReportTo=? WHERE Id=?";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1,Integer.parseInt(projectPojo.getPreAssignEmpId()));
            //preparedStatement.setString(2,projectPojo.getResType());
            if(projectPojo.getResType().equalsIgnoreCase("e")){
                preparedStatement.setString(2,projectPojo.getResType());
            }else{
            String accountType = DataSourceDataProvider.getInstance().getAccountTypeById(projectPojo.getAccountId());
            if (accountType.equalsIgnoreCase("customer")){
            preparedStatement.setString(2,"C");
            }
            else if(accountType.equalsIgnoreCase("Vendor"))
            {
            preparedStatement.setString(2,"V");
            }else{
                  preparedStatement.setString(2,"O");
            }
            }
            preparedStatement.setInt(3,projectPojo.getAccountId());
            preparedStatement.setInt(4,projectPojo.getProjectId());
            preparedStatement.setString(5,projectPojo.getAssignedToUID());
            preparedStatement.setString(6,projectPojo.getEmail());
            preparedStatement.setBoolean(7,projectPojo.getIsBillable());
            //preparedStatement.setTimestamp(8,DateUtility.getInstance().strToTimeStampObj(projectPojo.getDateAssigned()));
            //preparedStatement.setString(8,dateUtil.strToTimeStampObj(employeePojo.getDateAssigned()));
           // preparedStatement.setTimestamp(9,DateUtility.getInstance().strToTimeStampObj(projectPojo.getDateClosed()));
              if(projectPojo.getDateAssigned()!=null){
         preparedStatement.setDate(8,DateUtility.getInstance().strToTimeStampObj1(projectPojo.getDateAssigned()));
                 //  preparedStatement.setDate(8, new java.sql.Date(projectPojo.getDateAssigned().getTime()));
            }else{
                preparedStatement.setDate(8,null);
            }
             if(projectPojo.getDateClosed()!=null){
       preparedStatement.setDate(9,DateUtility.getInstance().strToTimeStampObj1(projectPojo.getDateClosed()));
               //   preparedStatement.setDate(9, new java.sql.Date(projectPojo.getDateClosed().getTime()));
            }else{
                preparedStatement.setDate(9,null);
            }
            preparedStatement.setString(10,projectPojo.getStatus());
            preparedStatement.setBoolean(11,projectPojo.getIsPrimary());
             preparedStatement.setString(12,projectPojo.getResTitle());
               preparedStatement.setString(13,loginId);
                 preparedStatement.setTimestamp(14,DateUtility.getInstance().getCurrentMySqlDateTime());
                  
                  preparedStatement.setInt(15,Integer.parseInt(projectPojo.getUtilisation()));
                  preparedStatement.setString(16,projectPojo.getRateType());
                  preparedStatement.setString(17,projectPojo.getCurrency());
                  preparedStatement.setInt(18,projectPojo.getProjectReportsTo());
                  preparedStatement.setString(19,projectPojo.getWorkPhone());
                  preparedStatement.setString(20,projectPojo.getMobilePhone());
                 
                  //preparedStatement.setBoolean(21,projectPojo.getIsPMO());
                  //preparedStatement.setInt(21,projectPojo.getId());
                   preparedStatement.setInt(21,projectPojo.getSecProjectReportsTo());
                  //preparedStatement.setBoolean(21,projectPojo.getIsPMO());
                  preparedStatement.setInt(22,projectPojo.getId());
				  
            isInserted = preparedStatement.executeUpdate();
            
        }catch (SQLException se){
           // se.printStackTrace();
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return isInserted;
        
    }  
    //new
    public int doAddPMOTeam(String createdBy,String PMOId,String status,int accountId, int projectId) throws ServiceLocatorException{
       int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
          //String PMOLoginID=DataSourceDataProvider.getInstance().getLoginIdByEmpId(Integer.parseInt(PMOId));
          int isExists=DataSourceDataProvider.getInstance().checkForPMOLoginId(PMOId, projectId);
          if(isExists==1)
          {
          isSuccess = 0;
          }
          else
          {
            connection=ConnectionProvider.getInstance().getConnection();
          
            preStmt = connection.prepareStatement("insert into tblPmoAuthors(AccountId,ProjectId,AuthorId,CreatedBy,CreatedDate,Status) values(?,?,?,?,?,?)");
            //preStmt.setInt(1,id);
            preStmt.setInt(1,accountId);
            preStmt.setInt(2,projectId);
            preStmt.setString(3,PMOId);
            preStmt.setString(4,createdBy);
           preStmt.setTimestamp(5,DateUtility.getInstance().getCurrentMySqlDateTime());
            preStmt.setString(6,status);
            isSuccess = preStmt.executeUpdate();
     }}catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
              
                if(preStmt != null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
     
     }
     
         public int doDeletePMOTeamMember(String modifiedby,String PMOId,int accountId, int projectId) throws ServiceLocatorException
     {
         
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
             //String PMOLoginID=DataSourceDataProvider.getInstance().getLoginIdByEmpId(Integer.parseInt(PMOId));
            connection=ConnectionProvider.getInstance().getConnection();
            //preStmt = connection.prepareStatement("delete from tblEcertTopicAuthors where ID=?");
            preStmt = connection.prepareStatement("UPDATE tblPmoAuthors SET Status = 'InActive' , ModifiedBy = ?, ModifiedDate = ?  WHERE AuthorId = ? and AccountId=? and ProjectId=?");
            //preStmt.setInt(1,id);
           preStmt.setString(1,modifiedby);
                preStmt.setTimestamp(2,DateUtility.getInstance().getCurrentMySqlDateTime());
            
            preStmt.setString(3,PMOId);
             preStmt.setInt(4,accountId);
             preStmt.setInt(5,projectId);
           
            isSuccess = preStmt.executeUpdate();
           
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
               
                if(preStmt != null){
                    preStmt.close();
                    preStmt = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
    }
public int doInsertProjectContactHistory(ProjectAction projectPojo, String loginId, String flag) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int isInserted = 0;
        int Id = 0;
        String queryString = "";
        String CreatedBy = "";
        String CreatedDate = "";
        if (flag.equals("I")) {
            Id = DataSourceDataProvider.getInstance().getMaxProjectContactsID();
            projectPojo.setId(Id);
            System.out.println("Id..."+Id+"Comments..."+projectPojo.getComments()+"BillableStartDate....."+projectPojo.getBillableStartDate());
            queryString = "INSERT INTO tblProjectContactsHistory(ProjectContactId,ObjectId,ObjectType,AccountId,ProjectId,"
                    + "ResourceName,Email,Billable,StartDate,EndDate,STATUS,ResourceType,ResourceTitle,CreatedBy,"
                    + "Utilization,RateType,Rate,ReportsTo,WorkPhone,MobilePhone,SecondReportTo,Flag,SkillSet,Comments,BillableStartDate,BillableEndDate,EmpProjStatus) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        } else {

            queryString = "INSERT INTO tblProjectContactsHistory(ProjectContactId,ObjectId,ObjectType,AccountId,ProjectId,"
                    + "ResourceName,Email,Billable,StartDate,EndDate,STATUS,ResourceType,ResourceTitle,CreatedBy,"
                    + "Utilization,RateType,Rate,ReportsTo,WorkPhone,MobilePhone,SecondReportTo,Flag,SkillSet,Comments,BillableStartDate,BillableEndDate,EmpProjStatus)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, projectPojo.getId());
            preparedStatement.setInt(2, Integer.parseInt(projectPojo.getPreAssignEmpId()));
            if (projectPojo.getResType().equalsIgnoreCase("e")) {
                preparedStatement.setString(3, projectPojo.getResType());
            } else {
                String accountType = DataSourceDataProvider.getInstance().getAccountTypeById(projectPojo.getAccountId());
                if (accountType.equalsIgnoreCase("customer")) {
                    preparedStatement.setString(3, "C");
                } else if (accountType.equalsIgnoreCase("Vendor")) {
                    preparedStatement.setString(3, "V");
                } else {
                    preparedStatement.setString(3, "O");
                }
            }
            preparedStatement.setInt(4, projectPojo.getAccountId());
            preparedStatement.setInt(5, projectPojo.getProjectId());
            preparedStatement.setString(6, projectPojo.getAssignedToUID());
            preparedStatement.setString(7, projectPojo.getEmail());
            preparedStatement.setBoolean(8, projectPojo.getIsBillable());
            //System.out.println("projectPojo.getDateAssigned()-->"+projectPojo.getDateAssigned());
            if (projectPojo.getDateAssigned() != null) {
                preparedStatement.setDate(9, DateUtility.getInstance().strToTimeStampObj1(projectPojo.getDateAssigned()));
                // preparedStatement.setDate(8, new java.sql.Date(projectPojo.getDateAssigned().getTime()));

            } else {
                preparedStatement.setDate(9, null);
            }
            if (projectPojo.getDateClosed() != null) {
                preparedStatement.setDate(10, DateUtility.getInstance().strToTimeStampObj1(projectPojo.getDateClosed()));
                //          preparedStatement.setDate(9, new java.sql.Date(projectPojo.getDateClosed().getTime()));
            } else {
                preparedStatement.setDate(10, null);
            }
            //preparedStatement.setString(8,dateUtil.strToTimeStampObj(employeePojo.getDateAssigned()));

            preparedStatement.setString(11, projectPojo.getStatus());
            preparedStatement.setBoolean(12, projectPojo.getIsPrimary());
            preparedStatement.setString(13, projectPojo.getResTitle());
            //System.out.println("======================="+loginId);
            //  System.out.println(""+projectPojo.getCreatedDate());

            preparedStatement.setString(14, loginId);
            //preparedStatement.setTimestamp(15,projectPojo.getCreatedDate());

//            preparedStatement.setString(14, loginId);
//            preparedStatement.setTimestamp(15, DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setInt(15, Integer.parseInt(projectPojo.getUtilisation()));
            preparedStatement.setString(16, projectPojo.getRateType());
            preparedStatement.setString(17, projectPojo.getCurrency());
            preparedStatement.setInt(18, projectPojo.getProjectReportsTo());
            preparedStatement.setString(19, projectPojo.getWorkPhone());
            preparedStatement.setString(20, projectPojo.getMobilePhone());
            preparedStatement.setInt(21, projectPojo.getSecProjectReportsTo());
            preparedStatement.setString(22, flag);
            preparedStatement.setString(23, projectPojo.getSkillSet());
            preparedStatement.setString(24, projectPojo.getComments());
            if (projectPojo.getBillableStartDate() != null && !"".equals(projectPojo.getBillableStartDate())) {
                preparedStatement.setDate(25, DateUtility.getInstance().getMysqlDate(projectPojo.getBillableStartDate()));
            } else {
                preparedStatement.setDate(25, null);
            }
            if (projectPojo.getBillableEndDate() != null && !"".equals(projectPojo.getBillableEndDate())) {
                preparedStatement.setDate(26, DateUtility.getInstance().getMysqlDate(projectPojo.getBillableEndDate()));
            } else {
                preparedStatement.setDate(26, null);
            }
              preparedStatement.setString(27, projectPojo.getEmpProjectStatus());
            isInserted = preparedStatement.executeUpdate();
            // projectPojo.setId(DataSourceDataProvider.getInstance().getProjectContactsIdFromAccoundId(projectPojo.getAccountId(), projectPojo.getProjectId(), projectPojo.getPreAssignEmpId()));
            //System.out.println("queryString :: " + queryString);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                //se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;

    }

public int doAddProjectRisk(ProjectAction projectPojo) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int isInserted = 0;
        int Id = 0;
        String queryString = "";
        String CreatedBy = "";
        String CreatedDate = "";
     

            queryString = "INSERT INTO mirage.tblProjectRisks (ProjectId,AccountId, Description,AssignedTo, STATUS,RiskImpact,Resolution, DateClosed,CreatedBy,CreatedDate)"
                    + " VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, projectPojo.getProjectId());
            preparedStatement.setInt(2, projectPojo.getAccountId());
           
            
            preparedStatement.setString(3, projectPojo.getDescription());
            preparedStatement.setString(4, projectPojo.getAssignedTo());
            preparedStatement.setString(5, projectPojo.getStatus());
            
            preparedStatement.setString(6, projectPojo.getImpact());
            preparedStatement.setString(7, projectPojo.getResolution());

           // preparedStatement.setDate(8,DateUtility.getInstance().getMysqlDate(projectPojo.getClosedDate()));
            if (projectPojo.getClosedDate() != null && !"".equals(projectPojo.getClosedDate())) {
                preparedStatement.setDate(8, DateUtility.getInstance().getMysqlDate(projectPojo.getClosedDate()));
            } else {
                preparedStatement.setDate(8, null);
            }

            preparedStatement.setString(9, projectPojo.getCreatedBy());
            preparedStatement.setTimestamp(10, DateUtility.getInstance().getCurrentMySqlDateTime());
            
            isInserted = preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                //se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;

    }
    public void getProjectRiskDetails(ProjectAction projectPojo) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int isInserted = 0;
        int Id = 0;
        String queryString = "";
        String CreatedBy = "";
        String CreatedDate = "";
     

            queryString = "SELECT * FROM tblProjectRisks WHERE ID="+projectPojo.getId();
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
                       
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                projectPojo.setDescription(resultSet.getString("Description"));
                projectPojo.setAssignedTo(resultSet.getString("AssignedTo"));
                projectPojo.setStatus(resultSet.getString("STATUS"));
             //   projectPojo.setClosedDate(DateUtility.getInstance().convertToviewFormat(resultSet.getString("DateClosed")));
                if(resultSet.getString("DateClosed")!=null && !"".equals(resultSet.getString("DateClosed"))){
                projectPojo.setClosedDate(DateUtility.getInstance().convertToviewFormat(resultSet.getString("DateClosed")));
                }

                   projectPojo.setResolution(resultSet.getString("Resolution"));
                   projectPojo.setImpact(resultSet.getString("RiskImpact"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                //se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        

    }
    public int doEditProjectRisk(ProjectAction projectPojo) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int isInserted = 0;
        int Id = 0;
        String queryString = "";
        String CreatedBy = "";
        String CreatedDate = "";
     

            queryString = "UPDATE tblProjectRisks set Description=?,AssignedTo=?, STATUS=?,RiskImpact=?,Resolution=?, DateClosed=?,ModifiedBy=?,ModifiedDate=?"
                    + " where Id="+projectPojo.getId();
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
           
            
            preparedStatement.setString(1, projectPojo.getDescription());
            preparedStatement.setString(2, projectPojo.getAssignedTo());
            preparedStatement.setString(3, projectPojo.getStatus());
            
            preparedStatement.setString(4, projectPojo.getImpact());
            preparedStatement.setString(5, projectPojo.getResolution());

          //  preparedStatement.setDate(6,DateUtility.getInstance().getMysqlDate(projectPojo.getClosedDate()));
             if (projectPojo.getClosedDate() != null && !"".equals(projectPojo.getClosedDate())) {
                preparedStatement.setDate(6, DateUtility.getInstance().getMysqlDate(projectPojo.getClosedDate()));
            } else {
                preparedStatement.setDate(6, null);
            }

            preparedStatement.setString(7, projectPojo.getCreatedBy());
            preparedStatement.setTimestamp(8, DateUtility.getInstance().getCurrentMySqlDateTime());
            
            isInserted = preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                //se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;

    }
/*
 * current status management start
 */
     public int doAddProjectToCustomer(ProjectAction projectPojo, String operationMode) throws ServiceLocatorException {
       // int insertedRows = 0;
        int isInserted;
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spProjectNew(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            callableStatement.setString(1, projectPojo.getPrjName());
            callableStatement.setString(2, projectPojo.getDescription());
            if (projectPojo.getStartDateActual() == null || "".equals(projectPojo.getStartDateActual())) {
                callableStatement.setDate(3, null);
            } else {
                // System.out.println("projectPojo.getStartDate().getTime()---->"+projectPojo.getStartDate().getTime());
                callableStatement.setDate(3, DateUtility.getInstance().getMysqlDate(projectPojo.getStartDateActual()));
            }
            if (projectPojo.getEndDateActual() == null || "".equals(projectPojo.getEndDateActual())) {
                callableStatement.setDate(4, null);
            } else {
                callableStatement.setDate(4, DateUtility.getInstance().getMysqlDate(projectPojo.getEndDateActual()));
            }

            callableStatement.setInt(5, projectPojo.getTotalResources());
            callableStatement.setString(6, projectPojo.getPrjManagerUID());
            callableStatement.setString(7, projectPojo.getProjectType());
            callableStatement.setString(8, operationMode);
            callableStatement.setInt(9, projectPojo.getProjectId());
            callableStatement.setInt(10, projectPojo.getCustomerId());
            callableStatement.setString(11, projectPojo.getStatus());
            if (operationMode.equals("add")) {
                callableStatement.setString(12, projectPojo.getCreatedBy());
                callableStatement.setTimestamp(13, projectPojo.getCreatedDate());

            } else if (operationMode.equals("update")) {
                callableStatement.setString(12, projectPojo.getModifiedBy());
                callableStatement.setTimestamp(13, projectPojo.getModifiedDate());
            }
            if (projectPojo.getIsDualReportingRequired()) {
                callableStatement.setInt(14, 1);
            } else {
                callableStatement.setInt(14, 0);
            }

            callableStatement.setString(15, projectPojo.getPreSalesMgrId());
            callableStatement.setString(16, projectPojo.getPmo());
            if (projectPojo.getStartDatePlan() == null || "".equals(projectPojo.getStartDatePlan())) {
                callableStatement.setDate(17, null);
            } else {
                // System.out.println("projectPojo.getStartDate().getTime()---->"+projectPojo.getStartDate().getTime());
                callableStatement.setDate(17, DateUtility.getInstance().getMysqlDate(projectPojo.getStartDatePlan()));
            }
            if (projectPojo.getEndDatePlan() == null || "".equals(projectPojo.getEndDatePlan())) {
                callableStatement.setDate(18, null);
            } else {
                callableStatement.setDate(18, DateUtility.getInstance().getMysqlDate(projectPojo.getEndDatePlan()));
            }

            callableStatement.setString(19, projectPojo.getPractice());
            callableStatement.setInt(20, projectPojo.getOnSitePlan());
            callableStatement.setInt(21, projectPojo.getOnSiteActual());
            callableStatement.setInt(22, projectPojo.getOffShorePlan());
            callableStatement.setInt(23, projectPojo.getOffShoreActual());
            callableStatement.setInt(24, projectPojo.getNearShorePlan());
            callableStatement.setInt(25, projectPojo.getNearShoreActual());
            callableStatement.setString(26, projectPojo.getCostModel());
            callableStatement.setString(27, projectPojo.getSector());
            callableStatement.setString(28, projectPojo.getComplexity());
            callableStatement.setString(29, projectPojo.getPriority());
            callableStatement.setString(30, projectPojo.getComments());
            callableStatement.setString(31, projectPojo.getSoftware());
            callableStatement.setString(32, projectPojo.getState());
            callableStatement.setString(33, projectPojo.getOffshoreDelLead());
            callableStatement.setString(34, projectPojo.getOffshoreTechLead());
            callableStatement.setString(35, projectPojo.getOnsiteLead());
           // System.out.println("projectPojo.getOffshoreDelLead()..."+projectPojo.getOffshoreDelLead()+"projectPojo.getOffshoreTechLead().."+projectPojo.getOffshoreTechLead()+"projectPojo.getOnsiteLead()..."+projectPojo.getOnsiteLead());
            callableStatement.setString(36, projectPojo.getSchedule());
            callableStatement.setString(37, projectPojo.getRisk());
            callableStatement.setString(38, projectPojo.getResources());
            isInserted = callableStatement.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return isInserted;

    }
    
     public void getCustomerProjectDetails(ProjectAction projectPojo, int Id) throws ServiceLocatorException {

       // System.out.println("getCustomerProjectDetails impl...");
        Connection connection = null;
        // PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        String queryString = "SELECT tblProjects.Id,tblProjects.ProjectName,tblProjects.ProjectDescription,tblProjects.ProjectStartDate,tblProjects.ProjectEndDate,tblProjects.TotalResources,tblProjects.ProjectManagerUID,tblProjects.CustomerId,"
                + "tblProjects.ProjectType,tblProjects.PreSalesMgrId,tblProjects.PMO,tblProjects.STATUS,tblProjects.CreatedBy,tblProjects.Dualreporting,tblProjects.Practice,tblProjects.OnSitePlan,"
                + " tblProjects.OnSiteActual,tblProjects.OffShorePlan,tblProjects.OffShoreActual,tblProjects.NearShorePlan,tblProjects.NearShoreActual,tblProjects.CostModel, "
                + " tblProjects.Sector,tblProjects.StartDatePlan,tblProjects.EndDatePlan,tblProjects.Complexity,tblProjects.Priority,tblProjects.Comments,tblProjects.Software,tblProjects.State,tblProjects.OffshoreDelLead,tblProjects.OffshoreTechLead,tblProjects.OnsiteTechLead,tblProjects.SCHEDULE," + " tblProjects.Risk,tblProjects.Resources ,tblCrmAccount.NAME,tblProjects.Dualreporting FROM tblCrmAccount JOIN tblProjects WHERE tblCrmAccount.Id=tblProjects.CustomerId AND tblProjects.Id=" + Id;
       // System.out.println("queryString.." + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                projectPojo.setAccountId(resultSet.getInt("CustomerId"));
             //   System.out.println("CustomerId...." + resultSet.getInt("CustomerId") + "resultSet.getString(\"NAME\")" + resultSet.getString("NAME"));
                projectPojo.setProjectId(resultSet.getInt("Id"));
                projectPojo.setAccountName(resultSet.getString("NAME"));
                projectPojo.setPrjName(resultSet.getString("ProjectName"));
                projectPojo.setDescription(resultSet.getString("ProjectDescription"));
                if (resultSet.getString("ProjectEndDate") != null && !"".equals(resultSet.getString("ProjectEndDate"))) {
                    projectPojo.setEndDateActual(com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(resultSet.getString("ProjectEndDate")));
                }
                if (resultSet.getString("ProjectStartDate") != null && !"".equals(resultSet.getString("ProjectStartDate"))) {
                    projectPojo.setStartDateActual(com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(resultSet.getString("ProjectStartDate")));
                }
                projectPojo.setResources(resultSet.getString("TotalResources"));
                projectPojo.setPrjManagerUID(resultSet.getString("ProjectManagerUID"));
                projectPojo.setProjectType(resultSet.getString("ProjectType"));
                projectPojo.setPreSalesMgrId(resultSet.getString("PreSalesMgrId"));
                projectPojo.setPmo(resultSet.getString("PMO"));
                projectPojo.setStatus(resultSet.getString("STATUS"));
                projectPojo.setPractice(resultSet.getString("Practice"));
                projectPojo.setOnSitePlan(resultSet.getInt("OnSitePlan"));
                projectPojo.setOnSiteActual(resultSet.getInt("OnSiteActual"));
                projectPojo.setOffShorePlan(resultSet.getInt("OffShorePlan"));
                projectPojo.setOffShoreActual(resultSet.getInt("OffShoreActual"));
                projectPojo.setNearShorePlan(resultSet.getInt("NearShorePlan"));
                projectPojo.setNearShoreActual(resultSet.getInt("NearShoreActual"));
                projectPojo.setCostModel(resultSet.getString("CostModel"));
                projectPojo.setSector(resultSet.getString("Sector"));
                if (resultSet.getString("StartDatePlan") != null && !"".equals(resultSet.getString("StartDatePlan"))) {
                    projectPojo.setStartDatePlan(com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(resultSet.getString("StartDatePlan")));
                }
                if (resultSet.getString("EndDatePlan") != null && !"".equals(resultSet.getString("EndDatePlan"))) {
                    projectPojo.setEndDatePlan(com.mss.mirage.util.DateUtility.getInstance().convertToviewFormat(resultSet.getString("EndDatePlan")));

                }
                projectPojo.setComplexity(resultSet.getString("Complexity"));
                projectPojo.setPriority(resultSet.getString("Priority"));
                projectPojo.setComments(resultSet.getString("Comments"));
                projectPojo.setSoftware(resultSet.getString("Software"));
                projectPojo.setState(resultSet.getString("State"));
                projectPojo.setOffshoreDelLead(resultSet.getString("OffshoreDelLead"));
                projectPojo.setOffshoreTechLead(resultSet.getString("OffshoreTechLead"));
                projectPojo.setOnsiteLead(resultSet.getString("OnsiteTechLead"));
                projectPojo.setSchedule(resultSet.getString("SCHEDULE"));
                projectPojo.setRisk(resultSet.getString("Risk"));
                projectPojo.setResources(resultSet.getString("Resources"));
                projectPojo.setIsDualReportingRequired(resultSet.getBoolean("Dualreporting"));

            }
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

    }

     public int getInActiveCustomerProject(int projectId, String createdBy, Timestamp createdDate,String projectEndDate,String comments) throws ServiceLocatorException {
        CallableStatement callableStatement = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
      
        int updatedRows = 0;
        try {
            //System.out.println("spInActiveProjectMembers");
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spInActiveProjectMembers(?,?,?,?,?)}");

            callableStatement.setInt(1, projectId);
            callableStatement.setString(2, createdBy);
            callableStatement.setTimestamp(3, createdDate);
              callableStatement.setString(4, projectEndDate);
              if(comments != null && !"".equalsIgnoreCase(comments))
              {
              callableStatement.setString(5, comments);
              }else{
               callableStatement.setString(5, "Project is completed ");
                
              }
              
            updatedRows = callableStatement.executeUpdate();
            //System.out.println("updatedRows..." + updatedRows + "projectId.." + projectId + "createdBy.." + createdBy + "createdDate.." + createdDate);
            // }//Closing Cache Checking
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
       // System.out.println("I am Out of Data Source Provider");
        return updatedRows; // returning the object.

    }


    /*
 * current status management end
 */
}
