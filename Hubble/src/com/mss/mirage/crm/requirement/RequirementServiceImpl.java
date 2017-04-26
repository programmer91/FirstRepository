/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   September 9, 2008, 7:30 PM
 *
 * Author  :  Hari Krishna Kondala <hkondala@miraclesoft.com>
 *
 * File    : RequirementServiceImpl .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.requirement;

import com.mss.mirage.util.*;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
//import org.apache.tools.ant.taskdefs.Java;
import org.hibernate.secure.JACCConfiguration;

/**
 *
 * @author miracle
 */
public class RequirementServiceImpl implements RequirementService{
    
    /** Creates a new instance of RequirementServiceImpl */
    public RequirementServiceImpl() {
    }
    
//    public int doAddRequirement(RequirementAction requirementAction) throws ServiceLocatorException {
//        int isSuccess = 0;
//        Connection connection= null;
//        
//        /** callableStatement is a reference variable for CallableStatement . */
//        CallableStatement callableStatement = null;
//        
//        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
//        PreparedStatement preStmt=null,preStmtTemp=null;
//        
//        Timestamp tstamp = new Timestamp(00-00-00);
//        
//        /** The queryString is useful to get  queryString result to the particular jsp page */
//        String queryString="";
//        Statement statement=null;
//        
//        /** The statement is useful  to execute the above queryString */
//        ResultSet resultSet=null;
//        try{
//            DateUtility dateUtil = DateUtility.getInstance();
//            connection=ConnectionProvider.getInstance().getConnection();
//            preStmt = connection.prepareStatement("SELECT  ifnull(max(Id),0)+1 as tempId FROM tblRecRequirement");
//            resultSet = preStmt.executeQuery();
//            resultSet.next();
//            int objId = resultSet.getInt("tempId");
//            preStmt.close();
//            resultSet.close();
//            String status=requirementAction.getStatus();
//            preStmt = connection.prepareStatement("INSERT INTO tblRecRequirement(Id,JobTitle,Practice,Skills,State,City,StartDate," +
//                    "CreatedBy,ContactNo,AssignedTo,Status,DatePosted,Functions,Responsibilities,Education,Comments,Experience,TaxTerm," +
//                    "TargetRate,TargetSalary,NoResumes,Country,Duration,Location,CustomerId,ClosedDate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//            preStmt.setInt(1,objId);
//            preStmt.setString(2,requirementAction.getTitle());
//            preStmt.setString(3,requirementAction.getPracticeId());
//            preStmt.setString(4,requirementAction.getSkills());
//            preStmt.setString(5,requirementAction.getState());
//            preStmt.setString(6,requirementAction.getCity());
//            preStmt.setTimestamp(7,dateUtil.strToTimeStampObj(requirementAction.getStartDate()));
//            //preStmt.setTimestamp(8,dateUtil.strToTimeStampObj(requirementAction.getEndDate()));
//            preStmt.setString(8,requirementAction.getCreatedBy());
//            preStmt.setString(9,requirementAction.getContactNo());
//            //System.out.println("requirementAction.getAssignedTo() In insert"+requirementAction.getAssignedTo());
//            preStmt.setString(10,requirementAction.getAssignedTo());
//            preStmt.setString(11,requirementAction.getStatus());
//           // requirementAction.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
//             requirementAction.setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
//            preStmt.setTimestamp(12,requirementAction.getCreatedDate());
//            preStmt.setString(13,requirementAction.getFunctions());
//            preStmt.setString(14,requirementAction.getResponsibilities());
//            preStmt.setString(15,requirementAction.getEducation());
//            preStmt.setString(16,requirementAction.getComments());
//            preStmt.setString(17,requirementAction.getExperience());
//            preStmt.setString(18,requirementAction.getTaxTerm());
//            preStmt.setString(19,requirementAction.getTargetRate());
//            preStmt.setString(20,requirementAction.getTargetSalary());
//            preStmt.setInt(21,requirementAction.getNoResumes());
//            preStmt.setString(22,requirementAction.getCountry());
//            preStmt.setString(23,requirementAction.getDuration());
//            preStmt.setString(24, requirementAction.getLocation());
//            preStmt.setInt(25,requirementAction.getAccId());
//            if(status.equals("Hold") || status.equals("Withdrawn") || status.equals("won") || status.equals("lost")){
//                //preStmt.setTimestamp(26, new Timestamp(new java.util.Date().getTime()));
//                preStmt.setTimestamp(26, DateUtility.getInstance().getCurrentMySqlDateTime());
//            }else{
//                preStmt.setTimestamp(26,(Timestamp)null);
//                //preStmt.setTimestamp(26,tstamp.valueOf("1980-01-14 12:50:45.01"));
//                
//            }
//            isSuccess = preStmt.executeUpdate();
//        }catch(SQLException se) {
//            se.printStackTrace();
//            //throw new ServiceLocatorException(se);
//        }finally{
//            try{
//                if(preStmt!=null){
//                    preStmt.close();
//                    preStmt=null;
//                }
//                if(connection != null){
//                    connection.close();
//                    connection = null;
//                }
//            }catch (SQLException se){
//                se.printStackTrace();
//                //throw new ServiceLocatorException(se);
//            }
//        }
//        return isSuccess;
//    }
    
    public int doAddRequirement(RequirementAction requirementAction) throws ServiceLocatorException {
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        Timestamp tstamp = new Timestamp(00-00-00);
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            DateUtility dateUtil = DateUtility.getInstance();
            connection=ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("SELECT  ifnull(max(Id),0)+1 as tempId FROM tblRecRequirement");
            resultSet = preStmt.executeQuery();
            resultSet.next();
            int objId = resultSet.getInt("tempId");
            preStmt.close();
            resultSet.close();
            String status=requirementAction.getStatus();
            preStmt = connection.prepareStatement("INSERT INTO tblRecRequirement(Id,JobTitle,Practice,Skills,State,City,StartDate," +
                    "CreatedBy,ContactNo,AssignedTo,Status,DatePosted,Functions,Responsibilities,Education,Comments,Experience,TaxTerm," +
                    "TargetRate,TargetSalary,NoResumes,Country,Duration,Location,CustomerId,ClosedDate ,SecondarySkills,RecComments,Region) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preStmt.setInt(1,objId);
            preStmt.setString(2,requirementAction.getTitle());
            preStmt.setString(3,requirementAction.getPracticeId());
            preStmt.setString(4,requirementAction.getSkills());
            preStmt.setString(5,requirementAction.getState());
            preStmt.setString(6,requirementAction.getCity());
            preStmt.setTimestamp(7,dateUtil.strToTimeStampObj(requirementAction.getStartDate()));
            //preStmt.setTimestamp(8,dateUtil.strToTimeStampObj(requirementAction.getEndDate()));
            preStmt.setString(8,requirementAction.getCreatedBy());
            preStmt.setString(9,requirementAction.getContactNo());
            //System.out.println("requirementAction.getAssignedTo() In insert"+requirementAction.getAssignedTo());
            preStmt.setString(10,requirementAction.getAssignedTo());
            preStmt.setString(11,requirementAction.getStatus());
           // requirementAction.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
             requirementAction.setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            preStmt.setTimestamp(12,requirementAction.getCreatedDate());
            preStmt.setString(13,requirementAction.getFunctions());
            preStmt.setString(14,requirementAction.getResponsibilities());
            preStmt.setString(15,requirementAction.getEducation());
            preStmt.setString(16,requirementAction.getComments());
            preStmt.setString(17,requirementAction.getExperience());
            preStmt.setString(18,requirementAction.getTaxTerm());
            preStmt.setString(19,requirementAction.getTargetRate());
            preStmt.setString(20,requirementAction.getTargetSalary());
            preStmt.setInt(21,requirementAction.getNoResumes());
            preStmt.setString(22,requirementAction.getCountry());
            preStmt.setString(23,requirementAction.getDuration());
            preStmt.setString(24, requirementAction.getLocation());
            preStmt.setInt(25,requirementAction.getAccId());
          
            if(status.equals("Hold") || status.equals("Withdrawn") || status.equals("won") || status.equals("lost")){
                //preStmt.setTimestamp(26, new Timestamp(new java.util.Date().getTime()));
                preStmt.setTimestamp(26, DateUtility.getInstance().getCurrentMySqlDateTime());
            }else{
                preStmt.setTimestamp(26,(Timestamp)null);
                //preStmt.setTimestamp(26,tstamp.valueOf("1980-01-14 12:50:45.01"));
                
            }
             preStmt.setString(27, requirementAction.getSecondarySkills());
             preStmt.setString(28, requirementAction.getRecruiterComments());
             preStmt.setString(29, requirementAction.getRegion());
            isSuccess = preStmt.executeUpdate();
        }catch(SQLException se) {
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preStmt!=null){
                    preStmt.close();
                    preStmt=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                se.printStackTrace();
                //throw new ServiceLocatorException(se);
            }
        }
        return isSuccess;
    }
    
    public int doEdit(RequirementAction requirementAction ,String transType) throws ServiceLocatorException {
        int isSuccess = 0;
        Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
         /*preStmt = connection.prepareStatement("UPDATE tblRecRequirement set State='"+requirementAction.getState()+"',JobTitle='"+requirementAction.getTitle()+"'," +
                    "Practice='"+requirementAction.getPracticeId()+"',Comments='"+requirementAction.getComments()+"',StartDate='"+requirementAction.getStartDate()+"'," +
                    "EndDate='"+requirementAction.getEndDate()+"',ContactNo='"+requirementAction.getContactNo()+"',AssignedTo='"+requirementAction.getAssignedTo()+"'," +
                    "NoResumes='"+requirementAction.getNoResumes()+"',Functions='"+requirementAction.getFunctions()+"',Responsibilities='"+requirementAction.getResponsibilities()+"'," +
                    "Education='"+requirementAction.getEducation()+"',Experience='"+requirementAction.getExperience()+"',TaxTerm='"+requirementAction.getTaxTerm()+"'," +
                    "TargetRate='"+requirementAction.getTargetRate()+"',TargetSalary='"+requirementAction.getTargetSalary()+"',Status='"+requirementAction.getStatus()+"'," +
                    "Skills='"+requirementAction.getSkills()+"',City='"+requirementAction.getCity()+"' WHERE Id='"+requirementAction.getObjectId()+"'");*/
        
            /* kiran Commented  -- preStmt = connection.prepareStatement("UPDATE tblRecRequirement set State=?,JobTitle=?,Practice=?,Comments=?,StartDate=?," +
                    "EndDate=?,ContactNo=?,AssignedTo=?,NoResumes=?,Functions=?,Responsibilities=?,Education=?," +
                    "Experience=?,TaxTerm=?,TargetRate=?,TargetSalary=?,Status=?,Skills=?,City=?,Country=?,ModifiedBy=?," +
                    "ModifiedDate=?,SecondaryRecruiter=?,AssignToTechLead=?,SecondaryTechLead=?,Duration=?,Location=?  WHERE Id='"+requirementAction.getObjectId()+"'");
             */
        CallableStatement callableStatement = null;
        
        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
        PreparedStatement preStmt=null,preStmtTemp=null;
        
        /** The queryString is useful to get  queryString result to the particular jsp page */
        String queryString="";
        Statement statement=null;
        Timestamp tstamp = new Timestamp(00-00-00);
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        try{
            String status=requirementAction.getStatus();
            connection=ConnectionProvider.getInstance().getConnection();
            DateUtility dateUtil = DateUtility.getInstance();
            DataSourceDataProvider dataSourceDataProvider=DataSourceDataProvider.getInstance();
            
//            String requirementDetailsQuery="UPDATE tblRecRequirement set State=?,JobTitle=?,Practice=?,StartDate=?," +
//                    "ContactNo=?,AssignedTo=?,NoResumes=?,TaxTerm=?,TargetRate=?,TargetSalary=?,Status=?,Skills=?,City=?,Country=?,ModifiedBy=?," +
//                    "ModifiedDate=?,SecondaryRecruiter=?,AssignToTechLead=?,SecondaryTechLead=?,Duration=?,Location=?,RejectReason=?,AssignedDate=?,ClosedDate=?  WHERE Id='"+requirementAction.getObjectId()+"'";
            
            String requirementDetailsQuery="UPDATE tblRecRequirement set State=?,JobTitle=?,Practice=?,StartDate=?," +
                    "ContactNo=?,AssignedTo=?,NoResumes=?,TaxTerm=?,TargetRate=?,TargetSalary=?,Status=?,Skills=?,City=?,Country=?,ModifiedBy=?," +
                    "ModifiedDate=?,SecondaryRecruiter=?,AssignToTechLead=?,SecondaryTechLead=?,Duration=?,Location=?,RejectReason=?,AssignedDate=?,ClosedDate=?,AssignedBy=?,SecondarySkills=?,Region=?  WHERE Id='"+requirementAction.getObjectId()+"'";
            
            
            
         //   String jobDetailsQuery="UPDATE tblRecRequirement set Comments=?,Functions=?,Responsibilities=?  WHERE Id='"+requirementAction.getObjectId()+"'";
             String jobDetailsQuery="UPDATE tblRecRequirement set Comments=?,Functions=?,Responsibilities=?,RecComments=?  WHERE Id='"+requirementAction.getObjectId()+"'";

            if(requirementAction.getDivType().equals("jobDetails")){
                preStmt = connection.prepareStatement(jobDetailsQuery);
                preStmt.setString(1,requirementAction.getComments());
                preStmt.setString(2,requirementAction.getFunctions());
                preStmt.setString(3,requirementAction.getResponsibilities());
                 preStmt.setString(4,requirementAction.getRecruiterComments());

                isSuccess = preStmt.executeUpdate();
                preStmt.close();
                preStmt=null;
                connection.close();
                //preStmt.setString(,requirementAction.getEducation());
                //preStmt.setString(13,requirementAction.getExperience());
                
            }
            
            if(requirementAction.getDivType().equals("requirementDetails")){
                preStmt = connection.prepareStatement(requirementDetailsQuery);
                preStmt.setString(1,requirementAction.getState());
                preStmt.setString(2,requirementAction.getTitle());
                preStmt.setString(3,requirementAction.getPracticeId());
                preStmt.setTimestamp(4,dateUtil.strToTimeStampObj(requirementAction.getStartDate()));
                //preStmt.setTimestamp(6,dateUtil.strToTimeStampObj(requirementAction.getEndDate()));
                preStmt.setString(5,requirementAction.getContactNo());
               // System.out.println("requirementAction.getAssignedTo() In update"+requirementAction.getAssignedTo());
                preStmt.setString(6,requirementAction.getAssignedTo());
                preStmt.setInt(7,requirementAction.getNoResumes());
                preStmt.setString(8,requirementAction.getTaxTerm());
                preStmt.setString(9,requirementAction.getTargetRate());
                preStmt.setString(10,requirementAction.getTargetSalary());
                preStmt.setString(11,requirementAction.getStatus());
                preStmt.setString(12,requirementAction.getSkills());
                preStmt.setString(13,requirementAction.getCity());
                preStmt.setString(14,requirementAction.getCountry());
                preStmt.setString(15,requirementAction.getModifiedBy());
                //preStmt.setTimestamp(16,new Timestamp(new java.util.Date().getTime()));
                preStmt.setTimestamp(16,DateUtility.getInstance().getCurrentMySqlDateTime());
                preStmt.setString(17, requirementAction.getSecondaryRecruiter());
                preStmt.setString(18, requirementAction.getAssignToTechLead());
                preStmt.setString(19, requirementAction.getSecondaryTechLead());
                preStmt.setString(20, requirementAction.getDuration());
                preStmt.setString(21, requirementAction.getLocation());
                preStmt.setString(22,requirementAction.getRejectReason());
                /*if(requirementAction.getAssignedTo()!=null){
                    preStmt.setTimestamp(23,new Timestamp(new java.util.Date().getTime()));
                }else{
                  //  preStmt.setTimestamp(23,tstamp.valueOf("1980-01-14 12:50:45.01"));
                    preStmt.setTimestamp(23,(Timestamp)null);
                }*/
                Timestamp assignedDate=dataSourceDataProvider.getAssignedDate(requirementAction.getObjectId());
                
            
               //(requirementAction.getAssignedTo()!=null || requirementAction.getSecondaryRecruiter()!=null)
             //   if(dataSourceDataProvider.getAssignedDate(requirementAction.getObjectId())==null || "u".equals(transType) ) {
                if(assignedDate==null || "u".equals(transType) ) {
                
                    //preStmt.setTimestamp(23,new Timestamp(new java.util.Date().getTime()));
                    preStmt.setTimestamp(23,DateUtility.getInstance().getCurrentMySqlDateTime());
                }else{
                    
                  //  preStmt.setTimestamp(23,tstamp.valueOf("1980-01-14 12:50:45.01"));
                      preStmt.setTimestamp(23,assignedDate);
                }   
                
               /* if(requirementAction.getAssignedTo()==null && requirementAction.getSecondaryRecruiter()==null)
                {
                 preStmt.setTimestamp(23,assignedDate);    
                }
                else
                {
                 preStmt.setTimestamp(23,new Timestamp(new java.util.Date().getTime()));   
                }*/
                
               
                if(status.equals("Hold") || status.equals("Withdrawn") || status.equals("won") || status.equals("lost")  || status.equals("Closed")){
                   // preStmt.setTimestamp(24, new Timestamp(new java.util.Date().getTime()));
                     preStmt.setTimestamp(24, DateUtility.getInstance().getCurrentMySqlDateTime());
                }else{
                   // preStmt.setTimestamp(24,tstamp.valueOf("1980-01-14 12:50:45.01"));
                    preStmt.setTimestamp(24,(Timestamp)null);
                }
                String assignedBy =dataSourceDataProvider.getRequirementAssignedBy(requirementAction.getObjectId());
                if(assignedBy==null || "".equals(assignedBy) || "u".equals(transType)) {
                
                    //preStmt.setTimestamp(23,new Timestamp(new java.util.Date().getTime()));
                    preStmt.setString(25,requirementAction.getModifiedBy());
                }else{
                    
                  //  preStmt.setTimestamp(23,tstamp.valueOf("1980-01-14 12:50:45.01"));
                      preStmt.setString(25,assignedBy);
                }
                 preStmt.setString(26,requirementAction.getSecondarySkills());
                 preStmt.setString(27,requirementAction.getRegion());

                isSuccess = preStmt.executeUpdate();
                preStmt.close();
                preStmt=null;
                connection.close();
            }
            
            connection=null;
        } catch(SQLException se) {
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preStmt!=null){
                    preStmt.close();
                    preStmt=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return isSuccess;
    }
    
    public RequirementVTO getRequirement(int objId,String country) throws ServiceLocatorException {
        
        RequirementVTO requirementVTO = new RequirementVTO();
        Connection connection= null;
        
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        DateUtility dateUtil;
        dateUtil = DateUtility.getInstance();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
          //  resultSet = statement.executeQuery("SELECT * from tblRecRequirement WHERE Id="+objId+" AND Country like '"+country+"'");
              resultSet = statement.executeQuery("SELECT * from tblRecRequirement WHERE Id="+objId);
            if(resultSet.next()) {
                requirementVTO.setTitle(resultSet.getString("JobTitle"));
                requirementVTO.setPracticeId(resultSet.getString("Practice"));
                requirementVTO.setSkills(resultSet.getString("Skills"));
                requirementVTO.setState(resultSet.getString("State"));
                requirementVTO.setCity(resultSet.getString("City"));
                requirementVTO.setCountry(resultSet.getString("Country"));
                requirementVTO.setSecondarySkills(resultSet.getString("SecondarySkills"));

                if(resultSet.getString("StartDate")!=null){
                    requirementVTO.setStartDate(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("StartDate")));
                }else{
                    requirementVTO.setStartDate(resultSet.getString("StartDate"));
                }
                
                if(resultSet.getString("EndDate")!=null){
                    requirementVTO.setEndDate(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("EndDate")));
                }else{
                    requirementVTO.setEndDate(resultSet.getString("EndDate"));
                }
                requirementVTO.setAssignedTo(resultSet.getString("AssignedTo"));
                // added
                requirementVTO.setSecondaryRecruiter(resultSet.getString("SecondaryRecruiter"));
                requirementVTO.setAssignToTechLead(resultSet.getString("AssignToTechLead"));
                requirementVTO.setSecondaryTechLead(resultSet.getString("SecondaryTechLead"));
                // end
                requirementVTO.setContactNo(resultSet.getString("ContactNo"));
                requirementVTO.setStatus(resultSet.getString("Status"));
                requirementVTO.setFunctions(resultSet.getString("Functions"));
                requirementVTO.setResponsibilities(resultSet.getString("Responsibilities"));
                requirementVTO.setComments(resultSet.getString("Comments"));
                requirementVTO.setEducation(resultSet.getString("Education"));
                requirementVTO.setExperience(resultSet.getString("Experience"));
                requirementVTO.setTaxTerm(resultSet.getString("TaxTerm"));
                requirementVTO.setTargetRate(resultSet.getString("TargetRate"));
                requirementVTO.setTargetSalary(resultSet.getString("TargetSalary"));
                requirementVTO.setNoResumes(resultSet.getInt("NoResumes"));
                requirementVTO.setActionType("requirementEdit");
                requirementVTO.setObjectId(String.valueOf(objId));
                //added K.
                requirementVTO.setCreatedBy(resultSet.getString("CreatedBy"));
                requirementVTO.setDuration(resultSet.getString("Duration"));
                requirementVTO.setLocation(resultSet.getString("Location"));
                requirementVTO.setRejectReason(resultSet.getString("RejectReason"));
                if(resultSet.getString("DatePosted")!=null){
                    requirementVTO.setCreatedDate(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("DatePosted")));
                }else{
                    requirementVTO.setCreatedDate(resultSet.getString("DatePosted"));
                }
                if(resultSet.getString("ModifiedDate")!=null){
                    requirementVTO.setModifiedDate(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("ModifiedDate")));
                }else{
                    requirementVTO.setModifiedDate(resultSet.getString("ModifiedDate"));
                }
                if(resultSet.getString("AssignedDate")!=null){
                    requirementVTO.setAssignedDate(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("AssignedDate")));
                }else{
                    requirementVTO.setAssignedDate(resultSet.getString("AssignedDate"));
                }
                if(resultSet.getString("ClosedDate")!=null){
                    requirementVTO.setClosedDate(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("ClosedDate")));
                }else{
                    requirementVTO.setClosedDate(resultSet.getString("ClosedDate"));
                }
                requirementVTO.setRecruiterComments(resultSet.getString("RecComments"));
                requirementVTO.setRegion(resultSet.getString("Region"));

            }
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }catch(Exception ex){
            throw new ServiceLocatorException(ex);
        } finally{
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
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return requirementVTO;
    }
    
//    public int addConsultantForRequirement(RequirementAction requirementAction) throws ServiceLocatorException {
//        int isSuccess = 0;
//        Connection connection= null;
//        
//        /** callableStatement is a reference variable for CallableStatement . */
//        CallableStatement callableStatement = null;
//        
//        /** preStmt,preStmtTemp are reference variable for PreparedStatement . */
//        PreparedStatement preStmt=null,preStmtTemp=null;
//        
//        /** The queryString is useful to get  queryString result to the particular jsp page */
//        String queryString="";
//        Statement statement=null;
//        
//        /** The statement is useful  to execute the above queryString */
//        ResultSet resultSet=null;
//        try{
//            DateUtility dateUtil=DateUtility.getInstance();
//            connection=ConnectionProvider.getInstance().getConnection();
//            preStmt = connection.prepareStatement("SELECT  ifnull(max(Id),0)+1 as tempId FROM tblRec");
//            resultSet = preStmt.executeQuery();
//            resultSet.next();
//            int id = resultSet.getInt("tempId");
//            preStmt.close();
//            resultSet.close();
//            preStmt = connection.prepareStatement("insert into tblRec(Id,RequirementId,ConsultantId,ResumeAttachmentId,RateNegotiated,StartDate," +
//                    "TechRate,Comments,Status,CreatedBy,CreatedDate) values(?,?,?,?,?,?,?,?,?,?,?)");
//            preStmt.setInt(1,id);
//            preStmt.setInt(2,Integer.parseInt(requirementAction.getRequirementId()));
//            preStmt.setInt(3,Integer.parseInt(requirementAction.getConsultantId()));
//            if(requirementAction.getResumeId().equals("") || requirementAction.getResumeId().equals(null)) {
//                preStmt.setInt(4,0);
//            }else preStmt.setInt(4,Integer.parseInt(requirementAction.getResumeId()));
//            preStmt.setString(5,requirementAction.getTargetRate());
//            preStmt.setTimestamp(6,dateUtil.strToTimeStampObj(requirementAction.getStartDate()));
//            System.err.println("requirementAction.getStartDate()"+requirementAction.getStartDate());
//            preStmt.setInt(7,requirementAction.getTechRate());
//            preStmt.setString(8,requirementAction.getComments());
//            preStmt.setString(9,requirementAction.getStatus());
//            preStmt.setString(10,requirementAction.getCreatedBy());
//           // requirementAction.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
//             requirementAction.setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
//            preStmt.setTimestamp(11,requirementAction.getCreatedDate());
//            isSuccess = preStmt.executeUpdate();
//        }catch(SQLException se){
//            throw new ServiceLocatorException(se);
//        }finally{
//            try{
//                if(resultSet != null){
//                    resultSet.close();
//                    resultSet = null;
//                }
//                if(statement != null){
//                    statement.close();
//                    statement = null;
//                }
//                if(connection != null){
//                    connection.close();
//                    connection = null;
//                }
//            } catch(SQLException se){
//                se.printStackTrace();
//            }
//        }
//        return isSuccess;
//    }
    
//    public RequirementVTO getConsultantRequuirement(int objId) throws ServiceLocatorException {
//        RequirementVTO requirementVTO = new RequirementVTO();
//        Connection connection= null;
//        
//        Statement statement=null;
//        
//        /** The statement is useful  to execute the above queryString */
//        ResultSet resultSet=null;
//        DateUtility dateUtil;
//        dateUtil = DateUtility.getInstance();
//        try {
//            connection = ConnectionProvider.getInstance().getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT * from tblRec WHERE Id="+objId);
//            if(resultSet.next()) {
//                requirementVTO.setRequirementId(String.valueOf(resultSet.getInt("RequirementId")));
//                requirementVTO.setConsultantId(String.valueOf(resultSet.getInt("ConsultantId")));
//                requirementVTO.setResumeId(String.valueOf(resultSet.getInt("ResumeAttachmentId")));
//                requirementVTO.setTargetRate(resultSet.getString("RateNegotiated"));
//                if(resultSet.getString("StartDate")!=null){
//                    requirementVTO.setStartDate(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("StartDate")));
//                }else{
//                    requirementVTO.setStartDate(resultSet.getString("StartDate"));
//                }
//                requirementVTO.setTechRate(resultSet.getInt("TechRate"));
//                requirementVTO.setStatus(resultSet.getString("Status"));
//                requirementVTO.setComments(resultSet.getString("Comments"));
//                requirementVTO.setActionType("editConsultantRequirement");
//            }
//        }catch(SQLException se){
//            throw new ServiceLocatorException(se);
//        }finally{
//            try{
//                if(resultSet != null){
//                    resultSet.close();
//                    resultSet = null;
//                }
//                if(statement != null){
//                    statement.close();
//                    statement = null;
//                }
//                if(connection != null){
//                    connection.close();
//                    connection = null;
//                }
//            } catch(SQLException se){
//                se.printStackTrace();
//            }
//        }
//        return requirementVTO;
//    }
    
    public int addConsultantForRequirement1(RequirementAction requirementAction) throws ServiceLocatorException {
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
            DateUtility dateUtil=DateUtility.getInstance();
            connection=ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("SELECT  ifnull(max(Id),0)+1 as tempId FROM tblRec");
            resultSet = preStmt.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("tempId");
            preStmt.close();
            resultSet.close();
            preStmt = connection.prepareStatement("insert into tblRec(Id,RequirementId,ConsultantId,ResumeAttachmentId,RateNegotiated,StartDate," +
                    "TechRate,Comments,Status,CreatedBy,CreatedDate,AvailableFrom,MobileNo,WorkAuthorization,CurrentEmployer) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preStmt.setInt(1,id);
            preStmt.setInt(2,Integer.parseInt(requirementAction.getRequirementId()));
            preStmt.setInt(3,Integer.parseInt(requirementAction.getConsultantId()));
            if(requirementAction.getResumeId().equals("") || requirementAction.getResumeId().equals(null)) {
                preStmt.setInt(4,0);
            }else preStmt.setInt(4,Integer.parseInt(requirementAction.getResumeId()));
            preStmt.setString(5,requirementAction.getTargetRate());
            preStmt.setTimestamp(6,dateUtil.strToTimeStampObj(requirementAction.getStartDate()));
          //  System.err.println("requirementAction.getStartDate()"+requirementAction.getStartDate());
            preStmt.setInt(7,requirementAction.getTechRate());
            preStmt.setString(8,requirementAction.getComments());
            preStmt.setString(9,requirementAction.getStatus());
            preStmt.setString(10,requirementAction.getCreatedBy());
           // requirementAction.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
             requirementAction.setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            preStmt.setTimestamp(11,requirementAction.getCreatedDate());
            preStmt.setDate(12,dateUtil.getMysqlDate(requirementAction.getAvailableFrom()));
            preStmt.setString(13, requirementAction.getCellPhoneNo());
            preStmt.setString(14, requirementAction.getWorkAuthorization());
            preStmt.setString(15, requirementAction.getCurrentEmployer());
            
            isSuccess = preStmt.executeUpdate();
            addConsultantReview(id,Integer.parseInt(requirementAction.getConsultantId()),Integer.parseInt(requirementAction.getRequirementId()),requirementAction.getComments(),requirementAction.getStatus(),requirementAction.getCreatedBy());
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
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

   public int addConsultantForRequirement(RequirementAction requirementAction) throws ServiceLocatorException {
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
            DateUtility dateUtil=DateUtility.getInstance();
            connection=ConnectionProvider.getInstance().getConnection();
            //==================================================
             callableStatement = connection.prepareCall("{call spConsultantForRequirement(?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            callableStatement.setInt(1,Integer.parseInt(requirementAction.getRequirementId()));
            callableStatement.setInt(2,Integer.parseInt(requirementAction.getConsultantId()));
            if(requirementAction.getResumeId().equals("") || requirementAction.getResumeId().equals(null)) {
                callableStatement.setInt(3,0);
            }else{ 
                callableStatement.setInt(3,Integer.parseInt(requirementAction.getResumeId()));
            }
            callableStatement.setString(4,requirementAction.getTargetRate());
            callableStatement.setTimestamp(5,dateUtil.strToTimeStampObj(requirementAction.getStartDate()));
            callableStatement.setInt(6,requirementAction.getTechRate());
            callableStatement.setString(7,requirementAction.getComments());
            callableStatement.setString(8,requirementAction.getStatus());
            callableStatement.setString(9,requirementAction.getCreatedBy());
            requirementAction.setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            callableStatement.setTimestamp(10,requirementAction.getCreatedDate());
            callableStatement.setDate(11,dateUtil.getMysqlDate(requirementAction.getAvailableFrom()));
            callableStatement.setString(12,requirementAction.getCellPhoneNo());
            callableStatement.setString(13,requirementAction.getWorkAuthorization());
            callableStatement.setString(14, requirementAction.getCurrentEmployer());
         //   System.out.println("---->"+requirementAction.getSkypeId()+"--");
           if(requirementAction.getSkypeId()!= null && !"".equals(requirementAction.getSkypeId())){
             callableStatement.setString(15, requirementAction.getSkypeId());
             }else{
                  callableStatement.setString(15,"");
             }
            callableStatement.setString(16, requirementAction.getCurrentLocation());
            if(requirementAction.getWorkAuthorizationCopyAttachment() != null && !"".equals(requirementAction.getWorkAuthorizationCopyAttachment())){
            callableStatement.setString(17, requirementAction.getWorkAuthorizationCopyAttachment());
            }else{
              callableStatement.setString(17, "");  
            }
             if(requirementAction.getOnProject()!= -1 && !"".equals(requirementAction.getOnProject())){
           callableStatement.setInt(18, requirementAction.getOnProject());
             }
             else{
                   callableStatement.setInt(18, -1);
             }
              if(requirementAction.getDlCopyAttachedAttachment() != null && !"".equals(requirementAction.getDlCopyAttachedAttachment())){
             callableStatement.setString(19, requirementAction.getDlCopyAttachedAttachment());
              }else{
                    callableStatement.setString(19, "");
              }
               if(requirementAction.getProjectEnd() != null && !"".equals(requirementAction.getProjectEnd())){
            callableStatement.setString(20, dateUtil.convertDateToMySql(requirementAction.getProjectEnd()));
               }else{
                    callableStatement.setString(20, null);
               }
                 if(requirementAction.getRelocation() != null && !"".equals(requirementAction.getRelocation())){
            callableStatement.setString(21, requirementAction.getRelocation());
                 }else{
                      callableStatement.setString(21, "");
                 }
                  if(requirementAction.getChangeReason() != null && !"".equals(requirementAction.getChangeReason())){
            callableStatement.setString(22, requirementAction.getChangeReason());
                  }else{
                       callableStatement.setString(22, "");
                  }
              if(requirementAction.getYearOfCompletion() != 0 && !"".equals(requirementAction.getYearOfCompletion())){     
            callableStatement.setInt(23, requirementAction.getYearOfCompletion());
              }else{
                   callableStatement.setInt(23, 0);
              }
             if(requirementAction.getAvailability() != null && !"".equals(requirementAction.getAvailability())){
            callableStatement.setString(24, requirementAction.getAvailability());
             }else{
                  callableStatement.setString(24, "");
             }
              if(requirementAction.getStartDatetoUs() != null && !"".equals(requirementAction.getStartDatetoUs())){
            callableStatement.setString(25, dateUtil.convertDateToMySql(requirementAction.getStartDatetoUs()));
              }else{
                   callableStatement.setString(25, null);
              }
               if(requirementAction.getEducationDetails() != null && !"".equals(requirementAction.getEducationDetails())){
            callableStatement.setString(26, requirementAction.getEducationDetails());
               }else{
                    callableStatement.setString(26,"");
               }
                if(requirementAction.getReference() != null && !"".equals(requirementAction.getReference())){
            callableStatement.setString(27, requirementAction.getReference());
                }else{
                   callableStatement.setString(27, "");  
                }
            
         //   System.out.println(" dateUtil.convertDateToMySql(requirementAction.getReference()........"+dateUtil.convertStringToMySQLDate(requirementAction.getReference()));
              
            callableStatement.registerOutParameter(28,Types.VARCHAR);
          int  updatedRows = callableStatement.executeUpdate();
            
          //  accountPojo.setPrimaryAddressId(callableStatement.getInt(45));
            //=====================================================
           String result=callableStatement.getString(28);
            //System.out.println("result--"+result);
            if(result.equals("success")){
               isSuccess=1; 
            }
            //isSuccess = preStmt.executeUpdate();
           // addConsultantReview(id,Integer.parseInt(requirementAction.getConsultantId()),Integer.parseInt(requirementAction.getRequirementId()),requirementAction.getComments(),requirementAction.getStatus(),requirementAction.getCreatedBy());
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
             
                if(callableStatement != null){
                    callableStatement.close();
                    callableStatement = null;
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
    
   public RequirementVTO getConsultantRequuirement(int objId) throws ServiceLocatorException {
        RequirementVTO requirementVTO = new RequirementVTO();
        Connection connection= null;
        
        Statement statement=null;
        
        /** The statement is useful  to execute the above queryString */
        ResultSet resultSet=null;
        DateUtility dateUtil;
        String query = "";
        dateUtil = DateUtility.getInstance();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            query = "SELECT * from tblRec WHERE Id="+objId;
         //  System.out.println("query------------>"+query);
            resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                requirementVTO.setRequirementId(String.valueOf(resultSet.getInt("RequirementId")));
                requirementVTO.setConsultantId(String.valueOf(resultSet.getInt("ConsultantId")));
                requirementVTO.setResumeId(String.valueOf(resultSet.getInt("ResumeAttachmentId")));
                requirementVTO.setTargetRate(resultSet.getString("RateNegotiated"));
                if(resultSet.getString("StartDate")!=null){
                    requirementVTO.setStartDate(dateUtil.sqlTimeStampTousTimeStamp(resultSet.getString("StartDate")));
                }else{
                    requirementVTO.setStartDate(resultSet.getString("StartDate"));
                }
                requirementVTO.setTechRate(resultSet.getInt("TechRate"));
                requirementVTO.setStatus(resultSet.getString("Status"));
                requirementVTO.setComments(resultSet.getString("Comments"));
                requirementVTO.setActionType("editConsultantRequirement");
                if(!"".equals(resultSet.getString("AvailableFrom")) && resultSet.getString("AvailableFrom")!=null){
                requirementVTO.setAvailableFrom(DateUtility.getInstance().convertToviewFormat(resultSet.getString("AvailableFrom")));
                }
                else{
                     requirementVTO.setAvailableFrom("");
                }
                requirementVTO.setCellPhoneNo(resultSet.getString("MobileNo"));
                requirementVTO.setWorkAuthorization(resultSet.getString("WorkAuthorization"));
                requirementVTO.setCurrentEmployer(resultSet.getString("CurrentEmployer"));
                 if(!"".equals(resultSet.getString("SkypeId")) && resultSet.getString("SkypeId")!=null){
                 requirementVTO.setSkypeId(resultSet.getString("SkypeId"));
                 }
                 else{
                      requirementVTO.setSkypeId("");
                 }
                  if(!"".equals(resultSet.getString("Cur_Location")) && resultSet.getString("Cur_Location")!=null){
             requirementVTO.setCurrentLocation(resultSet.getString("Cur_Location"));
                  }else{
                      requirementVTO.setCurrentLocation("");
                  }
//              if(!"".equals(resultSet.getString("WorkAuthAttachName")) && resultSet.getString("WorkAuthAttachName")!=null){
//               requirementVTO.setWorkAuthorizationCopy(resultSet.getString("WorkAuthAttachName"));
//              }else{
//                  requirementVTO.setWorkAuthorizationCopy("");
//              } 
                  File wrkAuthFile = new File(resultSet.getString("WorkAuthAttachLocation"));
             // if(!"".equals(resultSet.getString("WorkAuthAttachLocation")) && resultSet.getString("WorkAuthAttachLocation")!=null){
            if(wrkAuthFile.exists()){
                  requirementVTO.setWorkAuthorizationCopyAttachment(resultSet.getString("WorkAuthAttachLocation"));
          
       //  String filename =  requirementVTO.getWorkAuthorizationCopyAttachment().substring(requirementVTO.getWorkAuthorizationCopyAttachment().lastIndexOf("\\")+1);
         // String filename = 
                  // System.out.println("-----filename--->"+filename);
            requirementVTO.setWorkAuthorizationCopy(wrkAuthFile.getName());
            //System.out.println("--->"+requirementVTO.getWorkAuthorizationCopyAttachment().split("\\")[2]+"--->"+requirementVTO.getWorkAuthorizationCopyAttachment().split("\\")[1]);
           // requirementVTO.setWorkAuthorizationCopy(requirementVTO.getWorkAuthorizationCopyAttachment().split("\\")[4]);
              }else{
                  requirementVTO.setWorkAuthorizationCopyAttachment("");
              requirementVTO.setWorkAuthorizationCopy("");
              }
             if(!"".equals(resultSet.getString("IsOnProject")) && resultSet.getString("IsOnProject")!=null){
           requirementVTO.setOnProject(resultSet.getString("IsOnProject"));
             }else{
                 requirementVTO.setOnProject("");
             }
             
//          if(!"".equals(resultSet.getString("DlCopyAttachName")) && resultSet.getString("DlCopyAttachName")!=null){
//            requirementVTO.setDlCopyAttached(resultSet.getString("DlCopyAttachName"));
//            }else{
//                requirementVTO.setDlCopyAttached("");
//            } 
             File dlCopyFile = new File(resultSet.getString("DlCopyAttachLocation"));
            // if(!"".equals(resultSet.getString("DlCopyAttachLocation")) && resultSet.getString("DlCopyAttachLocation")!=null){
                     if(dlCopyFile.exists()){
             requirementVTO.setDlCopyAttachedAttachment(resultSet.getString("DlCopyAttachLocation"));
             
               String filename =  dlCopyFile.getName();
             
            //  String filename =  requirementVTO.getDlCopyAttachedAttachment().substring(requirementVTO.getDlCopyAttachedAttachment().lastIndexOf("\\")+1);
          //  System.out.println("-----filename--->"+filename);
            requirementVTO.setDlCopyAttached(filename);
           
           //    requirementVTO.setDlCopyAttached(requirementVTO.getDlCopyAttachedAttachment().split("/")[5]);
             }else{
                  requirementVTO.setDlCopyAttachedAttachment("");
                requirementVTO.setDlCopyAttached("");
             }
              if(!"".equals(resultSet.getString("Cur_Project_EndDate")) && resultSet.getString("Cur_Project_EndDate")!=null){
          requirementVTO.setProjectEnd(DateUtility.getInstance().convertToviewFormat(resultSet.getString("Cur_Project_EndDate")));
              }else{
                   requirementVTO.setProjectEnd("");
              }
           if(!"".equals(resultSet.getString("Relocation")) && resultSet.getString("Relocation")!=null){
             requirementVTO.setRelocation(resultSet.getString("Relocation"));
           }else{
               requirementVTO.setRelocation("");
           }
              if(!"".equals(resultSet.getString("ChangeReason")) && resultSet.getString("ChangeReason")!=null){
            requirementVTO.setChangeReason(resultSet.getString("ChangeReason"));
              }else{
                  requirementVTO.setChangeReason("");
              }
             if(!"".equals(resultSet.getString("Bachlor_of_Degree_comp_year")) && resultSet.getString("Bachlor_of_Degree_comp_year")!=null){
            requirementVTO.setYearOfCompletion(resultSet.getString("Bachlor_of_Degree_comp_year"));
             }else{
                  requirementVTO.setYearOfCompletion("");
             }
             if(!"".equals(resultSet.getString("OnSite_Availabilty")) && resultSet.getString("OnSite_Availabilty")!=null){
           requirementVTO.setAvailability(resultSet.getString("OnSite_Availabilty"));
             }else{
                  requirementVTO.setAvailability("");
             }
            if(!"".equals(resultSet.getString("Arrived_To_USA")) && resultSet.getString("Arrived_To_USA")!=null){
          requirementVTO.setStartDatetoUs(DateUtility.getInstance().convertToviewFormat(resultSet.getString("Arrived_To_USA")));
            }else{
                 requirementVTO.setStartDatetoUs("");
            }
           if(!"".equals(resultSet.getString("Education_Details")) && resultSet.getString("Education_Details")!=null){
             requirementVTO.setEducationDetails(resultSet.getString("Education_Details"));
           }else{
                requirementVTO.setEducationDetails("");
           }
              if(!"".equals(resultSet.getString("Reference")) && resultSet.getString("Reference")!=null){
            requirementVTO.setReference(resultSet.getString("Reference"));
              }else{
                   requirementVTO.setReference("");
              }
          
            requirementVTO.setConsultantName(DataSourceDataProvider.getInstance().getConsultantName(resultSet.getInt("ConsultantId")));
                
            }
        }catch(SQLException se){
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
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return requirementVTO;
    }


    
    public int editConsultantRequirement(RequirementAction requirementAction) throws ServiceLocatorException {
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
            DateUtility dateUtil = DateUtility.getInstance();
             String previousStaus = DataSourceDataProvider.getInstance().getRecConsultantStatus(Integer.parseInt(requirementAction.getConsultId()));

            connection=ConnectionProvider.getInstance().getConnection();
//            preStmt = connection.prepareStatement("UPDATE tblRec set ConsultantId=?,ResumeAttachmentId=?,RateNegotiated=?,StartDate=?,TechRate=?," +
//                    "Comments=?,Status=?,ModifiedBy=?,ModifiedDate=? WHERE Id='"+requirementAction.getConsultId()+"'");
            preStmt = connection.prepareStatement("UPDATE tblRec set ConsultantId=?,ResumeAttachmentId=?,RateNegotiated=?,StartDate=?,TechRate=?," +
                    "Comments=?,Status=?,ModifiedBy=?,ModifiedDate=?,AvailableFrom=?,MobileNo=?,WorkAuthorization=?,CurrentEmployer=?,"+
                    "SkypeId=?,Cur_Location=?,IsOnProject=?,Cur_Project_EndDate=?,Relocation=?,Bachlor_of_Degree_comp_year=?,OnSite_Availabilty=?,"+
                    "Education_Details=?,Arrived_To_USA=?,Reference=?,ChangeReason=?,WorkAuthAttachLocation=?,"+
                    "DlCopyAttachLocation=? "+
                    " WHERE Id='"+requirementAction.getConsultId()+"'");
  
            preStmt.setInt(1,Integer.parseInt(requirementAction.getConsultantId()));
            if(requirementAction.getResumeId().equals("0") || requirementAction.getResumeId().equals(null)) {
                preStmt.setInt(2,0);
            }else preStmt.setInt(2,Integer.parseInt(requirementAction.getResumeId()));
            preStmt.setString(3,requirementAction.getTargetRate());
           // System.err.println(requirementAction.getStartDate()+">---<");
            preStmt.setTimestamp(4,dateUtil.strToTimeStampObj(requirementAction.getStartDate()));
            preStmt.setInt(5,requirementAction.getTechRate());
            preStmt.setString(6,requirementAction.getComments());
            preStmt.setString(7,requirementAction.getStatus());
            preStmt.setString(8,requirementAction.getModifiedBy());
           // requirementAction.setModifiedDate(new Timestamp(new java.util.Date().getTime()));
             requirementAction.setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
            preStmt.setTimestamp(9,requirementAction.getModifiedDate());
            if(!"".equals(requirementAction.getAvailableFrom()) && requirementAction.getAvailableFrom()!=null){
             preStmt.setDate(10,dateUtil.getMysqlDate(requirementAction.getAvailableFrom()));
            }
            preStmt.setString(11, requirementAction.getCellPhoneNo());
            preStmt.setString(12, requirementAction.getWorkAuthorization());
            preStmt.setString(13, requirementAction.getCurrentEmployer());
             if(requirementAction.getSkypeId()!= null && !"".equals(requirementAction.getSkypeId())){
             preStmt.setString(14, requirementAction.getSkypeId());
             }else{
                  preStmt.setString(14,null);
             }
            preStmt.setString(15, requirementAction.getCurrentLocation());
           if(requirementAction.getOnProject()!= -1 && !"".equals(requirementAction.getOnProject())){
           preStmt.setInt(16, requirementAction.getOnProject());
            }else{
                 preStmt.setInt(16, -1);
            }
             if(requirementAction.getProjectEnd()!= null && !"".equals(requirementAction.getProjectEnd())){
           preStmt.setString(17, dateUtil.convertDateToMySql(requirementAction.getProjectEnd()));
             }else{
                  preStmt.setString(17, null);
             }
             if(requirementAction.getRelocation()!= null && !"".equals(requirementAction.getRelocation())){
             preStmt.setString(18, requirementAction.getRelocation());
             }else{
                  preStmt.setString(18, "");
             }
             if(requirementAction.getYearOfCompletion()!= 0 && !"".equals(requirementAction.getYearOfCompletion())){
             preStmt.setInt(19, requirementAction.getYearOfCompletion());
             }else{
                  preStmt.setInt(19, 0);
             }
              if(requirementAction.getAvailability()!= null && !"".equals(requirementAction.getAvailability())){
           preStmt.setString(20, requirementAction.getAvailability());
              }else{
                   preStmt.setString(20, "");
              }
               if(requirementAction.getEducationDetails()!= null && !"".equals(requirementAction.getEducationDetails())){
            preStmt.setString(21, requirementAction.getEducationDetails());
               }else{
                     preStmt.setString(21, "");
               }
                if(requirementAction.getStartDatetoUs()!= null && !"".equals(requirementAction.getStartDatetoUs())){
             preStmt.setString(22, dateUtil.convertDateToMySql(requirementAction.getStartDatetoUs()));
                }else{
                     preStmt.setString(22, null);
                }
                 if(requirementAction.getReference()!= null && !"".equals(requirementAction.getReference())){
              preStmt.setString(23, requirementAction.getReference());
                 }else{
                      preStmt.setString(23, "");
                 }
                  if(requirementAction.getChangeReason()!= null && !"".equals(requirementAction.getChangeReason())){
            preStmt.setString(24, requirementAction.getChangeReason());
                  }else{
                      preStmt.setString(24, "");
                  }
        //  preStmt.setString(25, requirementAction.getWorkAuthorizationCopy());
                   if(requirementAction.getWorkAuthorizationCopyAttachment()!= null && !"".equals(requirementAction.getWorkAuthorizationCopyAttachment())){
            preStmt.setString(25, requirementAction.getWorkAuthorizationCopyAttachment());
                   }else{
                        preStmt.setString(25, "");
                   }
          //    preStmt.setString(27, requirementAction.getDlCopyAttached());
                    if(requirementAction.getDlCopyAttachedAttachment()!= null && !"".equals(requirementAction.getDlCopyAttachedAttachment())){
            preStmt.setString(26, requirementAction.getDlCopyAttachedAttachment());
                    }else{
                         preStmt.setString(26, "");
                    }

            isSuccess = preStmt.executeUpdate();
         //   System.out.println("isSuccess---"+isSuccess);
            if(isSuccess>0 && previousStaus.equals(requirementAction.getStatus())){
                updateConsultantReview(Integer.parseInt(requirementAction.getConsultantId()),Integer.parseInt(requirementAction.getRequirementId()),requirementAction.getComments(),previousStaus);
            }
            preStmt.close();
            preStmt=null;
            connection.close();
            connection=null;
            
             if(!previousStaus.equals(requirementAction.getStatus())){
            addConsultantReview(Integer.parseInt(requirementAction.getConsultId()),Integer.parseInt(requirementAction.getConsultantId()),Integer.parseInt(requirementAction.getRequirementId()),requirementAction.getComments(),requirementAction.getStatus(),requirementAction.getModifiedBy());
            String subject="";
            String bodyPart="";
            if(requirementAction.getStatus().equals("Evaluation Reject")){
                subject="Resume rejected in TL/Manager evaluation";
                bodyPart="has been rejected/put on hold";
            }
             if(requirementAction.getStatus().equals("Submitted to Sales")){
                subject="Resume Submitted to Sales";
                bodyPart="has been submitted to Sales Team";
            }
             if(requirementAction.getStatus().equals("Sales Accepted")){
                subject="Resume Accepted by Sales Team";
                bodyPart="has been accepted by Sales Team";
            }
              if(requirementAction.getStatus().equals("Sales Reject")){
                subject="Resume Rejected by Sales Team";
                bodyPart="has been rejected/put on hold by Sales Team";
            }
              if(requirementAction.getStatus().equals("Client Submission")){
                subject="Resume Shortlisted for Client Submission";
                bodyPart="has been shortlisted for Client Submission";
            }
              if(requirementAction.getStatus().equals("Client Reject")){
                subject="Resume Rejected by CLient";
                bodyPart="has been rejected by Client";
            }
              if(requirementAction.getStatus().equals("Client Interview")){
                subject="Sent for Client Interview";
                bodyPart="has been sent to Client Interview";
            }
              if(requirementAction.getStatus().equals("Client Interview Reject")){
                subject="Resume Rejected in Client Interview";
                bodyPart="has been Rejected in Client Interview";
            }
              if(requirementAction.getStatus().equals("Offered")){
                subject="Resume Offered by Client";
                bodyPart="has been Offered by Client";
            }
              if(requirementAction.getStatus().equals("Not Joined")){
                subject="Candidate Not Joined";
                bodyPart="Not Joined";
            }
              if(requirementAction.getStatus().equals("Joined")){
                subject="Candidate Joined";
                bodyPart="has bean Joined";
            }
              if(requirementAction.getStatus().equals("Dropped/Backout")){
                subject="Resume Dropped/Backout";
                bodyPart="has bean Dropped/Backout";
            }
              if(requirementAction.getStatus().equals("Hold")){
                subject="Resume put on hold";
                bodyPart="has bean put on hold";
            }
            //sendConsultantUpdatedDetailsForRequirement(String title,String requirementId,String consultantId,String rate,String startDate,String status,String comments,String modifiedBy,String modifiedDate)
        //  sendConsultantUpdatedDetailsForRequirement(requirementAction.getTitle(),requirementAction.getRequirementId(),requirementAction.getConsultantId(),requirementAction.getTargetRate(),requirementAction.getStartDate(),requirementAction.getStatus(),requirementAction.getComments(),requirementAction.getModifiedBy(),requirementAction.getModifiedDate().toString());
       //  sendConsultantUpdatedDetailsForRequirement(subject,bodyPart,requirementAction.getTitle(),requirementAction.getRequirementId(),requirementAction.getConsultantId(),requirementAction.getTargetRate(),requirementAction.getAvailableFrom(),requirementAction.getStatus(),requirementAction.getComments(),requirementAction.getModifiedBy(),requirementAction.getModifiedDate().toString(),requirementAction.getEmail2(),requirementAction.getCellPhoneNo(),requirementAction.getConsultId());
 
            }

            
        }catch(SQLException se) {
            se.printStackTrace();
            //throw new ServiceLocatorException(se);
        }finally{
            try{
                if(preStmt!=null){
                    preStmt.close();
                    preStmt=null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                se.printStackTrace();
                throw new ServiceLocatorException(se);
            }
        }
        return isSuccess;
    }
 
    
    //new serviceImpl for resume submission details on 07162013
      public int addResumeSubmissionDetails(String custEmail,String cc,String bcc,String subject,String message,String resumeRecId,String resumeRequirementId,String resumeConsultantId,String resumeAttachmentId,String submittedBy) throws ServiceLocatorException {
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
            DateUtility dateUtil=DateUtility.getInstance();
            connection=ConnectionProvider.getInstance().getConnection();
           
            preStmt = connection.prepareStatement("insert into tblRecResumeSubmission(RecId,RequirementId,ConsultantId,ResumeAttachmentId,CustomerEmail," +
                    "SubmittedBy,SubmittedDate) values(?,?,?,?,?,?,?)");
            //preStmt.setInt(1,id);
           
            preStmt.setInt(1,Integer.parseInt(resumeRecId));
            preStmt.setInt(2,Integer.parseInt(resumeRequirementId));
            preStmt.setInt(3,Integer.parseInt(resumeConsultantId));
            preStmt.setInt(4,Integer.parseInt(resumeAttachmentId));
             preStmt.setString(5,custEmail);
            preStmt.setString(6,submittedBy);
           // preStmt.setTimestamp(7,new Timestamp(new java.util.Date().getTime()));
             preStmt.setTimestamp(7,DateUtility.getInstance().getCurrentMySqlDateTime());
            //preStmt.setTimestamp(8,new Timestamp(new java.util.Date().getTime()));
            isSuccess = preStmt.executeUpdate();
            if(isSuccess>=1)
            {
            preStmt = connection.prepareStatement("SELECT  max(Id) as tempId FROM tblRecResumeSubmission");
            resultSet = preStmt.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("tempId");
             if(Properties.getProperty("Mail.Flag").equals("1")) {
            String s=MailManager.sendResumeDetailsToCustomer(id,resumeRecId,resumeConsultantId,resumeAttachmentId,custEmail,cc,bcc,subject,message,submittedBy);
             }
            preStmt.close();
            resultSet.close();
            }
            else
            {
               isSuccess=-1; 
            }
            
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
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

    public int insertRequirementAssignedToTrack(String transType,String createdBy,String recruiter1,String recruiter2,String requirementId,String assignedBy,Timestamp assignedDate) throws ServiceLocatorException {
        int isSuccess = 0;
        Connection connection= null;
     
        PreparedStatement preStmt=null;
      
        try{
           connection = ConnectionProvider.getInstance().getConnection();
            preStmt = connection.prepareStatement("insert into tblRecTrackRequirement(RequirementId,AssignedById,AssignedDate,Recruiter1,Recruiter2,CreatedBy," +
                    "Trantype) values(?,?,?,?,?,?,?)");
            preStmt.setInt(1,Integer.parseInt(requirementId));
           
           
            preStmt.setString(2,assignedBy);
            preStmt.setTimestamp(3,assignedDate);
            preStmt.setString(4,recruiter1);
            preStmt.setString(5,recruiter2);
           preStmt.setString(6,createdBy);
           preStmt.setString(7,transType);
          
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

 public void doAddMailsForRequirement(String subjectinfo,String title, String user,String rate, String id,String country,String state,String primarySkills,String secondarySkills) throws ServiceLocatorException {
        String createdBy = DataSourceDataProvider.getInstance().getCreatedByRequirementId(id);
        
        String createdByName = DataSourceDataProvider.getInstance().getFname_Lname(createdBy);
        
        String workPhoneNo = DataSourceDataProvider.getInstance().getWorkPhNoByLoginId(createdBy);
        
        
       // String cc = createdBy+"@miraclesoft.com";
        user+="#"+createdByName;
       //  System.out.println("user2--->"+user);
        //String cc = "nseerapu@miraclesoft.com";
        //String bcc = "vkandregula@miraclesoft.com";
        String bcc = "NULL";
        //to send mail to ajay bhat
        
       String Managers = com.mss.mirage.util.Properties.getProperty("RecruitManager");
            String mgrs[] = Managers.split(Pattern.quote(","));
            HashSet CC2 = new HashSet();
            for (int i = 0; i < mgrs.length; i++) {
                CC2.add(mgrs[i]);
            }
            if(!subjectinfo.equals("New requirement assigned")){
            CC2.add(createdBy);
            }
        String url=com.mss.mirage.util.Properties.getProperty("PROD.URL");
        
      //  cc2= cc2+"@miraclesoft.com";
        //System.out.println("abhatt--->"+cc2);
        /** The from is used for storing the from address. */
        String from = "hubbleapp@miraclesoft.com";
        
        String loginId="";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        
        /**The host is used for storing the IP address of mail */
        
        // String host = "mail.miraclesoft.com";
        // String host="mail.miraclesoft.com";
        
        
        try {
            
            String MailToUserList[]=user.split("#");
            //System.out.println("test mail"+MailToUserList.length);
//           for(int i=0;i<MailToUserList.length;i++){
//               System.out.println(MailToUserList[i]);
//           }
           HashSet<String> reportsToTopLevel = new HashSet<String>();
          //  for(int i=0;i<MailToUserList.length;i++){
              /*  
                loginId = DataSourceDataProvider.getInstance().getEmpEmailById(MailToUserList[i]);
                if(loginId!=null && loginId!=""){
                    String toAddr=loginId;
                    if(!MailToUserList[0].equals("") && (i==0 || i==1)){
                        String loginId1=DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(MailToUserList[i]);
                       // String reportsToTopLevel=DataSourceDataProvider.getInstance().getReportsTo(loginId1);
                        HashSet reportsToTopLevel1 = DataSourceDataProvider.getInstance().getTopLevelReportsLoginIds(loginId1);
                          CC2.addAll(reportsToTopLevel1);
                          reportsToTopLevel.add(loginId1);
                        System.out.println("reportsToTopLevel--"+reportsToTopLevel);
                        String recruter1LeadMail=DataSourceDataProvider.getInstance().getLeadMailIdByEmpName(MailToUserList[i]);
                        System.out.println("recruter1LeadMail--"+recruter1LeadMail);
                        if(recruter1LeadMail!=null && recruter1LeadMail!=""){
                          toAddr+=","+recruter1LeadMail;  
                        }
                    }
                    */
                    if (!MailToUserList[0].equals("") && MailToUserList[0] != null) {
                String recruiter1loginId = DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(MailToUserList[0]);
                HashSet reportsToTopLevel1 = DataSourceDataProvider.getInstance().getTopLevelReportsLoginIds(recruiter1loginId);
                reportsToTopLevel.add(recruiter1loginId);
                CC2.addAll(reportsToTopLevel1);
            }
            if (!MailToUserList[1].equals("") && MailToUserList[1] != null) {
                String recruiter2loginId = DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(MailToUserList[1]);
                HashSet reportsToTopLevel2 = DataSourceDataProvider.getInstance().getTopLevelReportsLoginIds(recruiter2loginId);
                reportsToTopLevel.add(recruiter2loginId);
                CC2.addAll(reportsToTopLevel2);
            }
            if (!MailToUserList[2].equals("") && MailToUserList[2] != null) {
                String recruiter1loginId = DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(MailToUserList[2]);
                reportsToTopLevel.add(recruiter1loginId);
            }
              if (!MailToUserList[3].equals("") && MailToUserList[3] != null) {
                String recruiter1loginId = DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(MailToUserList[3]);
                reportsToTopLevel.add(recruiter1loginId);
            }
          //  String ccAddress=cc2+","+cc;
            Iterator<String> itr = reportsToTopLevel.iterator();
            String tomail = "";
            //System.out.println("while loop");
            while (itr.hasNext()) {
                tomail += itr.next().toString() + "@miraclesoft.com,";


            }
              tomail = tomail.substring(0, tomail.length() - 1);
              //System.out.println("tomail----"+tomail);
            itr = CC2.iterator();
            String cc = "";
            while (itr.hasNext()) {
                cc += itr.next().toString() + "@miraclesoft.com,";


            }
             cc = cc.substring(0, cc.length() - 1);
            // System.out.println("cc----"+cc);
            
            // first part  (the html)
            StringBuilder htmlText = new StringBuilder();
       /*     htmlText.append("<html><head><title>Mail From Hubble Portal</title></head><body><table align='center'><tr style='background:#07BBD7;height:40px;'><td><font color='white' size='4' face='Arial'>");
            htmlText.append("<p>"+subjectinfo+"</p>");
            htmlText.append("</font></td></tr><tr><td><table style='background:#CCDBDE;width:100%;'><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<p>Hello Team,</p></font> </td></tr><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<p>"+subjectinfo+"");
            htmlText.append("</font>  </td></tr><tr><td><font color='#111728' size='2' face='Arial' style='font-weight:600;'><p><u>Requirement Details:</u></p></font></td></tr>");
            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'> Job Title</font>:  ");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>"+title+"</font> ");
            htmlText.append("</td></tr><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Location</font> :");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>"+state+","+country+"</font></td></tr>");
            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Target Rate</font>:");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>"+rate+"</font></td></tr>");
            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Primary Skills</font>:");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>"+primarySkills+"</font></td></tr>");
            
             if(secondarySkills!=null  && !"".equals(secondarySkills)){
                 htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Secondary Skills</font>:");
                 htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>"+secondarySkills+"</font></td></tr>");
            }
//            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Secondary Skills</font>:");
//            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>"+secondarySkills+"</font></td></tr>");
            htmlText.append("<tr><td><font color='#111728' size='2' face='Arial' style='font-weight:600;'><p><u>This Requirement has been assigned to:</u></p></font></td></tr>");
            
            if(!MailToUserList[0].equals("")){
            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Recruiter</font>: ");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>"+MailToUserList[0]+"</font></td></tr>");
            }
            if(!MailToUserList[1].equals("")){
            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Recruiter</font>: ");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>"+MailToUserList[1]+"</font></td></tr>");
            }
             if(!MailToUserList[2].equals("")){
            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Pre-Sales Person</font>: ");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>"+MailToUserList[2]+"</font></td></tr>");
            }
              if(!MailToUserList[3].equals("")){
            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Pre-Sales Person</font>: ");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>"+MailToUserList[3]+"</font></td></tr>");
            }
            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'><br>For More Details <a");
           //  if(i<2){
            htmlText.append(" href='"+url+"crm/requirement/getRequirementDetails.action?objectId="+id+"&requirementAdminFlag=YES&recruitmentRoleType=6'>");
//            }
//            else if(i<4){
//                 htmlText.append(" href='"+url+"crm/requirement/getRequirementDetails.action?objectId="+id+"&requirementAdminFlag=YES&recruitmentRoleType=10'>");
//            }
//            else{
//                  htmlText.append(" href='"+url+"crm/requirement/getRequirementDetails.action?objectId="+id+"&requirementAdminFlag=YES&recruitmentRoleType=4'>");
//            }
            htmlText.append("<font color='#111728'> click here</font> </a>.</font>");
            htmlText.append("</td></tr></table></font></td></tr><tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font> </td></tr></table></body></html>");
            */
            
//            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
//            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
//            htmlText.append("<p>Hello "+createdByName+"</p>");
//            htmlText.append("<p>"+subjectinfo+"</p>");
//            htmlText.append("<p><u><b>Requirement Details:</b></u><br>");
//            htmlText.append("Job Title: "+title+" <br>");
//            htmlText.append("Location: "+state+","+country+"<br>");
//            htmlText.append("Target Rate: "+rate+" <br>");
//            htmlText.append("<b>This Requirement has been assigned to "+user+", Work Phone:"+workPhoneNo+"</b><br>");
//            htmlText.append("Thank you.<br><br>");
//            htmlText.append("For More Details about this Requirement ");
//            htmlText.append("<a");
//            if(i<2){
//            htmlText.append(" href='http://localhost:8084/Hubble/crm/requirement/getRequirementDetails.action?objectId="+id+"&requirementAdminFlag=YES&recruitmentRoleType=6'>");
//            }
//            else if(i<4){
//                 htmlText.append(" href='http://localhost:8084/Hubble/crm/requirement/getRequirementDetails.action?objectId="+id+"&requirementAdminFlag=YES&recruitmentRoleType=10'>");
//            }
//            else{
//                  htmlText.append(" href='http://localhost:8084/Hubble/crm/requirement/getRequirementDetails.action?objectId="+id+"&requirementAdminFlag=YES&recruitmentRoleType=4'>");
//            }
//            htmlText.append("Click Here</a></p></font>");
//            htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
//            htmlText.append("</body></html>");
//            
            htmlText.append("<html>");
   htmlText.append("<head>");
     htmlText.append("<meta charset='utf-8'>");
      htmlText.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
      htmlText.append("<meta http-equiv='X-UA-Compatible' content='IE=edge' />");
      htmlText.append("<style type='text/css'>");
      
         htmlText.append("body, table, td, a{-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;}"); 
         htmlText.append("table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;}"); 
         htmlText.append("img{-ms-interpolation-mode: bicubic;}"); 

         htmlText.append("img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none;}");
         htmlText.append("table{border-collapse: collapse !important;}");
         htmlText.append("body{height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important;}");
        
         htmlText.append("a[x-apple-data-detectors] {color: inherit !important;text-decoration: none !important;font-size: inherit !important;font-family: inherit !important;font-weight: inherit !important;line-height: inherit !important;}");
        
         htmlText.append("@media screen and (max-width: 525px) {");
       
         htmlText.append(".wrapper {width: 100% !important;max-width: 100% !important;}");
         
         htmlText.append(".logo img {margin: 0 auto !important;}");
      
         htmlText.append(".mobile-hide {display: none !important;}");
         htmlText.append(".img-max {max-width: 100% !important;width: 100% !important;height: auto !important;}");
        
         htmlText.append(".responsive-table {width: 100% !important;}");
         
         htmlText.append(".padding {padding: 10px 5% 15px 5% !important;}");
         htmlText.append(".padding-meta {padding: 30px 5% 0px 5% !important;text-align: center;}");
         htmlText.append(".padding-copy {padding: 10px 5% 10px 5% !important;text-align: center;}");
         htmlText.append(".no-padding {padding: 0 !important;}");
         htmlText.append(".section-padding {padding: 50px 15px 50px 15px !important;}");
         
         htmlText.append(".mobile-button-container {margin: 0 auto;width: 100% !important;}");
         htmlText.append(".mobile-button {padding: 15px !important;border: 0 !important;font-size: 16px !important;display: block !important; }}");
         
         htmlText.append("div[style*='margin: 16px 0;'] { margin: 0 !important; }");
      htmlText.append("</style>");
   htmlText.append("</head>");
   htmlText.append("<body style='margin: 0 !important; padding: 0 !important;'>");
  
      htmlText.append("<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'>Job Title:</b> "+title+"</div>");
      
      htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
         htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center'>");
              
                        htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='wrapper'>");
                           htmlText.append("<tr>");
                              htmlText.append("<td align='center' valign='top' style='padding: 15px 0;' class='logo'><a href='http://www.miraclesoft.com/' target='_blank'><img alt='Logo' src='http://www.miraclesoft.com/images/newsletters/miracle-logo-dark.png' width='165' height='auto' style='display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 16px;' border='0'></a></td>");
                           htmlText.append("</tr>");
                        htmlText.append("</table>");
                       
            htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 5px;'>");
               
                        htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
                           htmlText.append("<tr>");
                              htmlText.append("<td>");
                                
                                 htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                                    htmlText.append("<tr>");
                                     if(subjectinfo.equals("New requirement assigned")){
                                        htmlText.append("<td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>New Requirement Assigned</b></td>");
                                     }
                                     if(subjectinfo.equals("A new requirement has been Updated")){
                                         htmlText.append("<td align='center' style='font-size: 26px; font-family: calibri; color: #2368a0; padding-top: 10px;' class='padding-copy'><b>New Requirement Updated</b></td>");
                                     }
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                        htmlText.append("</table>");
                        
            htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px;' class='padding'>");
               
                        htmlText.append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 500px;' class='responsive-table'>");
                           htmlText.append("<tr>");
                              htmlText.append("<td>");
                                 htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                                    htmlText.append("<tr>");
                                     if(subjectinfo.equals("New requirement assigned")){
                                       htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>Team,</b><br>You have been assigned to a new requirment and following are the details of it.</td>");
                                     }
                                     if(subjectinfo.equals("A new requirement has been Updated")){
                                          htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>Hello <b>Team,</b><br>A New Requirement has been Updated and following are the detais of it.</td>");
                                     }
                                    htmlText.append("</tr>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Job Title:</b> "+title+"<br>");
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Location:</b> "+state+","+country+"</b><br>");
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Primary Skills:</b> "+primarySkills+"<br>");
                                          if(secondarySkills!=null  && !"".equals(secondarySkills)){
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Secondary Skills:</b> "+secondarySkills+"<br>");
                                          }
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Target Rate:</b> "+rate+"<br>");
                                       htmlText.append("</td>");
                                    htmlText.append("</tr>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='justify' style='padding: 5px 0 5px 0; border-top: 1px dashed #2368a0; border-bottom: 1px dashed #2368a0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'>");
                                          htmlText.append("<b style='font-size: 14px;'>This Requirement has been assigned to</b><br>");
                                          if(!MailToUserList[0].equals("")){
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Recruiter:</b> "+MailToUserList[0]+"<br>");
                                          }
                                          if(!MailToUserList[1].equals("")){
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Recruiter:</b> "+MailToUserList[1]+"</b><br>");
                                          }
                                          if(!MailToUserList[2].equals("")){
                                          htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Pre-Sales Person:</b> "+MailToUserList[2]+"<br>");
                                          }
                                          if(!MailToUserList[3].equals("")){
                                              htmlText.append("<b style='font-size: 14px; color: #ef4048;'>Pre-Sales Person:</b> "+MailToUserList[3]+"<br>");
                                          }
                                       htmlText.append("</td>");
                                    htmlText.append("</tr>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 25px; font-family: calibri; color: #232527;' class='padding-copy'><b>Click <a href='"+url+"crm/requirement/getRequirementDetails.action?objectId="+id+"&requirementAdminFlag=YES&recruitmentRoleType=6' target='_blank' style='font-size: 14px; color: #2368a0;'>here</a> to know more about this requirement</b></td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                           htmlText.append("<tr>");
                              htmlText.append("<td>");
                                
                                 htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #8c8c8c; font-style: normal;' class='padding-copy'>Thanks & Regards,<br>Corporate Application Support Team, <br>Miracle Software Systems, Inc. <br>Email: hubble@miraclesoft.com <br>Phone: (+1)248-233-1814</td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                           htmlText.append("<tr>");
                              htmlText.append("<td>");
                               
                                 htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
                                    htmlText.append("<tr>");
                                       htmlText.append("<td align='left' style='padding: 5px 0 5px 0; font-size: 14px; line-height: 22px; font-family: calibri; color: #ef4048; font-style: italic;' class='padding-copy'>*Note: Please do not reply to this email as this is an automated notification</td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                        htmlText.append("</table>");
                       
            htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#ffffff' align='center' style='padding: 15px 0px;'>");
               
                        htmlText.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='max-width: 500px;' class='responsive-table'>");
                           htmlText.append("<tr>");
                              htmlText.append("<td width='200' align='center' style='text-align: center;'>");
                                 htmlText.append("<table width='200' cellpadding='0' cellspacing='0' align='center'>");
                                    htmlText.append("<tr>");
                                      htmlText.append("<td width='10'><a href='https://www.facebook.com/miracle45625' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/facebook.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                       htmlText.append("<td width='10'><a href='https://plus.google.com/+Team_MSS/videos' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/googleplus.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                       htmlText.append("<td width='10'><a href='https://www.linkedin.com/company/miracle-software-systems-inc' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/linkedin.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                       htmlText.append("<td width='10'><a href='https://www.youtube.com/c/Team_MSS' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/youtube.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                       htmlText.append("<td width='10'><a href='https://twitter.com/Team_MSSs' target='_blank'><img src='http://www.miraclesoft.com/images/newsletters/twitter.png' alt='facebook' width='26' height='auto' data-max-width='40' data-customIcon='true' ></a></td>");
                                    htmlText.append("</tr>");
                                 htmlText.append("</table>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                           htmlText.append("<tr>");
                              htmlText.append("<td height='10'>");
                              htmlText.append("</td>");
                           htmlText.append("</tr>");
                           htmlText.append("<tr>");
                              htmlText.append("<td align='center' style='font-size: 14px; line-height: 20px; font-family: calibri; color:#666666;'>&copy; "+Calendar.getInstance().get(Calendar.YEAR)+" Miracle Software Systems<br></td>");
                           htmlText.append("</tr>");
                        htmlText.append("</table>");
                       
            htmlText.append("</td>");
         htmlText.append("</tr>");
      htmlText.append("</table>");
   htmlText.append("</body>");
htmlText.append("</html>");
            
            // add it
            
            ServiceLocator.getMailServices().doAddEmailLogNew(tomail, cc, subjectinfo, htmlText.toString(),"", bcc,"Requirement Notification");
                    //System.out.println("added");
            //    }
           // }
            
            
            
      
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
public int addConsultantReview(int recConsultantId,int consultantId,int requirementId,String comments,String status,String assignedBy) throws ServiceLocatorException {
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
            DateUtility dateUtil=DateUtility.getInstance();
          
           connection=ConnectionProvider.getInstance().getConnection();
          //  preStmt = connection.prepareStatement("INSERT INTO tblRecConsultantActivity(ConsultantId,ForwardedBy,ForwardedTo,ForwardedDate," +
               //     "Comments,LastModifiedDate,Rateing,LinkStatus,ForwardedLink,ForwardToName,Status,RequirementId,RecConsultantId) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
//            preStmt = connection.prepareStatement("INSERT INTO tblRecConsultantActivity(ConsultantId,ForwardedBy,ForwardedTo,ForwardedDate," +
//                    "Comments,LastModifiedDate,Status,RequirementId,RecConsultantId) values(?,?,?,?,?,?,?,?,?)");
             preStmt = connection.prepareStatement("INSERT INTO tblRecConsultantActivity(ConsultantId,ForwardedBy,ForwardedTo,ForwardedDate," +
                    "Comments,Status,RequirementId,RecConsultantId) values(?,?,?,?,?,?,?,?)");
            //preStmt.setInt(1,);
            preStmt.setInt(1,consultantId);
            preStmt.setString(2,assignedBy);
              
                  //System.out.println("to234--->"+st.nextToken());
             preStmt.setString(3, assignedBy+"@miraclesoft.com");
            
             
            //preStmt.setString(3,finalEmailIds);
            preStmt.setTimestamp(4,DateUtility.getInstance().getCurrentMySqlDateTime());
           // preStmt.setString(5,"Yet to be Reviewed");
             preStmt.setString(5,comments);
          //  preStmt.setTimestamp(6,DateUtility.getInstance().getCurrentMySqlDateTime());
          
            
            preStmt.setString(6,status);
            preStmt.setInt(7,requirementId);
            preStmt.setInt(8,recConsultantId);
        
        
            
            
            isSuccess = preStmt.executeUpdate();
        }catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
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
public int updateConsultantReview(int recConsultantId,int requirementId,String comments,String previousStaus) throws ServiceLocatorException {
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
            DateUtility dateUtil=DateUtility.getInstance();
          
           connection=ConnectionProvider.getInstance().getConnection();
          //  preStmt = connection.prepareStatement("INSERT INTO tblRecConsultantActivity(ConsultantId,ForwardedBy,ForwardedTo,ForwardedDate," +
               //     "Comments,LastModifiedDate,Rateing,LinkStatus,ForwardedLink,ForwardToName,Status,RequirementId,RecConsultantId) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preStmt = connection.prepareStatement("UPDATE tblRecConsultantActivity set Comments=?,LastModifiedDate=? where ConsultantId="+recConsultantId+" AND RequirementId="+requirementId +" AND Status='"+previousStaus+"'");
            //preStmt.setInt(1,);
            //System.out.println("UPDATE tblRecConsultantActivity set Comments=?,LastModifiedDate=? where ConsultantId="+recConsultantId+" AND RequirementId="+requirementId +" AND Status='"+previousStaus+"'");
            //System.out.println("comments---"+comments);
            preStmt.setString(1,comments);
              
            
             
            //preStmt.setString(3,finalEmailIds);
            preStmt.setTimestamp(2,DateUtility.getInstance().getCurrentMySqlDateTime());
           // preStmt.setString(5,"Yet to be Reviewed");
          
            isSuccess = preStmt.executeUpdate();
        }catch(SQLException se){
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
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return isSuccess;
    }
public static void sendConsultantUpdatedDetailsForRequirement(String subject,String bodyPart,String title,String requirementId,String consultantId,String rate,String startDate,String status,String comments,String modifiedBy,String modifiedDate,String eMail,String cellNo,String consultId) throws ServiceLocatorException {
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        
        try {
            
            
            String details = DataSourceDataProvider.getInstance().getDetailsByRequirementId(requirementId);
            String allDetails[]=details.replaceAll("null", "").split("#");
             String url=com.mss.mirage.util.Properties.getProperty("PROD.URL");
              String Managers = com.mss.mirage.util.Properties.getProperty("RecruitManager");
            String mgrs[] = Managers.split(Pattern.quote(","));
            HashSet CC2 = new HashSet();
            for (int i = 0; i < mgrs.length; i++) {
                CC2.add(mgrs[i]);
            }
            
//            String loginId1=DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(requirementId);
//                        String reportsToTopLevel=DataSourceDataProvider.getInstance().getReportsTo(loginId1);

            //String createdBy = details.substring(0,details.indexOf("#"));
            String createdBy = allDetails[0];
            CC2.add(createdBy);
            //String practice = details.substring(details.indexOf("#")+1,details.length());
            String practice = allDetails[1];
            
            //String assignedByEmail  = DataSourceDataProvider.getInstance().getRequirementAssignedByEmail(requirementId);
           // String recruiter1Email  = DataSourceDataProvider.getInstance().getRequirementRecruiter1Email(requirementId);
            String assignedByEmail  =allDetails[2]+"@miraclesoft.com";
            String recruiter1  =allDetails[3];
            String recruiter2  =allDetails[4].replaceAll("_", "");
             HashSet<String> reportsToTopLevel=new HashSet<String>();  
            if(!recruiter1.equals("") && recruiter1!=null){
            String recruiter1loginId=DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(recruiter1);
                        HashSet reportsToTopLevel1=DataSourceDataProvider.getInstance().getTopLevelReportsLoginIds(recruiter1loginId);
                      reportsToTopLevel.add(recruiter1loginId);
                CC2.addAll(reportsToTopLevel1);
            }
            if(!recruiter2.equals("") && recruiter2!=null){
            String recruiter2loginId=DataSourceDataProvider.getInstance().getLoginIdByEmpFullName(recruiter2);
                        HashSet reportsToTopLevel2=DataSourceDataProvider.getInstance().getTopLevelReportsLoginIds(recruiter2loginId);
                        reportsToTopLevel.add(recruiter2loginId);
                        //reportsToTopLevel.addAll(reportsToTopLevel2);
                         CC2.addAll(reportsToTopLevel2);
            }
            
           // String details = DataSourceDataProvider.getInstance().getDetailsByRequirementId(requirementId);
           // String assignedByEmail  = DataSourceDataProvider.getInstance().getRequirementAssignedByEmail(requirementId);
          //  String recruiter1Email  = DataSourceDataProvider.getInstance().getRequirementRecruiter1Email(requirementId);
            
          //  String createdBy = details.substring(0,details.indexOf("#"));
            
           // String practice = details.substring(details.indexOf("#")+1,details.length());
            
            String createdByName = DataSourceDataProvider.getInstance().getFname_Lname(createdBy);
            
            String consultantName = DataSourceDataProvider.getInstance().getConsultantName(Integer.parseInt(consultantId)) ;
            /** The to is used for storing the user mail id to send details. */
            String to = createdBy+"@miraclesoft.com";
            //String cc = leaveCc;
            
            /** The from is used for storing the from address. */
            
            
            List practiseManagers = DefaultDataProvider.getInstance().getPracticeManager();
            
          // String subject = "Consultant has been updated for A Requirement";
           
            if(assignedByEmail!=null && !"".equals(assignedByEmail)){
                to = to+","+assignedByEmail;
           // message.addRecipient(Message.RecipientType.TO,new InternetAddress(assignedByEmail));
            }
//            if(recruiter1Email!=null && !"".equals(recruiter1Email)){
//                to = to+","+recruiter1Email;
//                
//           // message.addRecipient(Message.RecipientType.TO,new InternetAddress(recruiter1Email));
//            }
            to="";
             Iterator<String> itr = reportsToTopLevel.iterator();
            String tomail = "";
            while (itr.hasNext()) {
                tomail += itr.next().toString() + "@miraclesoft.com,";


            }
             tomail = tomail.substring(0, tomail.length() - 1);
            if(practice.equals("SAP")) {
                for(Iterator it = practiseManagers.iterator(); it.hasNext();) {
                  //  to = to + ","+it.next().toString()+"@miraclesoft.com";
                     CC2.add(it.next().toString());
                    //message.addRecipient(Message.RecipientType.TO,new InternetAddress(it.next().toString()+"@miraclesoft.com"));
                }
            }
             itr = CC2.iterator();
            String cc = "";
            while (itr.hasNext()) {
                cc += itr.next().toString() + "@miraclesoft.com,";


            }
             cc = cc.substring(0, cc.length() - 1);
          
           
            StringBuilder htmlText = new StringBuilder();
            /* old
            htmlText.append("<html><head><title>Mail From Hubble Portal</title>");
            htmlText.append("</head><body><font color='blue' size='2' face='Arial'>");
            htmlText.append("<p>Hello Team </p>");
            htmlText.append("<p>Consultant has been updated for "+title+" Requirement</p>");
            htmlText.append("<p><u><b>Consultant Details:</b></u><br>");
            htmlText.append("Consultant Name: "+consultantName+" <br>");
            htmlText.append("Target Rate:"+rate+" <br>");
            htmlText.append("Date Available:"+startDate+" <br>");
             htmlText.append("Status:"+status+" <br>");
              htmlText.append("ModifiedBy:"+modifiedBy+" <br>");
               htmlText.append("Modified Date:"+modifiedDate+" <br>");
               htmlText.append("Comments:"+comments+" <br>");
            htmlText.append("For More Details ");
            htmlText.append("Visit the following URL :<a");
            htmlText.append(" href='http://www.miraclesoft.com/Hubble/'>");
            htmlText.append("http://www.miraclesoft.com/Hubble/</a></p></font>");
            htmlText.append("<font color='red', size='2' face='Arial'>*Note:Please do not reply to this e-mail.  It was generated by our System.</font>");
            htmlText.append("</body></html>");*/
            
            
             htmlText.append("<html><head><title>Mail From Hubble Portal</title></head>");
            htmlText.append("<body><table align='center'><tr style='background:#07BBD7;height:40px;'><td><font color='white' size='4' face='Arial'>");
            htmlText.append("<p>"+subject+"</p></font></td></tr><tr><td><table style='background:#CCDBDE;width:100%;'><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<p>Hello Team,</p></font> </td></tr><tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("</font>  </td></tr><tr><td><font color='#111728' size='2' face='Arial' style='font-weight:600;'><p><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>"+ consultantName + "</font> submitted for <font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>" + title + "</font> "+bodyPart+".Please see the comments below.</p></font></td></tr>");
//            htmlText.append("<tr><td><font color='#111728' size='2' face='Arial' style='font-weight:600;'><p><u>Consultant Details:</u></p></font></td></tr>");
//            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Consultant Name:</font>");
//            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + consultantName + "</font> </td></tr>");
//            
//            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Consultant E-Mail:</font>");
//            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + eMail + "</font> </td></tr>");
//            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Consultant MobileNo:</font>");
//            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + cellNo + "</font> </td></tr>");
//             htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Target Rate:</font>");
//            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + rate + "</font></td></tr>");
//            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Date Available:</font>");
//            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + startDate + "</font></td></tr>");
//            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Status:</font>");
//            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + status + "</font></td></tr>");
//            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>ModifiedBy:</font>");
//            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + modifiedBy + "</font></td></tr>");
//            htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'>Modified Date:</font>");
//            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + modifiedDate + "</font></td></tr>");
              htmlText.append("<tr><td><font color='#111728' size='2' face='Arial' style='font-weight:600;'><p><u>Comments:</u></p></font></td></tr>");
            htmlText.append("<tr><td>");
            htmlText.append("<font color='#111728' size='2' face='Arial' style='font-weight:600;'>" + comments + "</font></td></tr>");;
          htmlText.append("<tr><td><font color='#3960D2' size='2' face='Arial' style='font-weight:600;'><br>For More Details about this Requirement/candidate <a href='"+url+"crm/requirement/getConsultantRequirement.action?consultId="+consultId+"&objectId="+requirementId+"&requirementAdminFlag=YES'>click here</a></font></td></tr>");
            htmlText.append("</table></font></td></tr><tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font> </td></tr></table></body></html>");
            
        ServiceLocator.getMailServices().doAddEmailLogNew(tomail, cc, subject, htmlText.toString(), "", "","Requirement Notification");
          // old  doAddEmailLog(String toAddress, String cCAddress, String subject,String bodyContent,String createdDate,String bCCAddress)
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public String addReqAttachmentLocation(RequirementAction requirementAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int isInsert = 0;
        String resultMessage = "";
        try {
            
            String queryString = "INSERT INTO tblAttachments ( ObjectId, ObjectType, AttachmentType,AttachmentName, AttachmentLocation, DateUploaded, UploadedBy) VALUES (?,?,?,?,?,?,?)";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, Integer.parseInt(requirementAction.getObjectId()));
           
           if("opp".equals(requirementAction.getOppFlag()))
           {
               preparedStatement.setString(2, "Opportunity");
             
           }
           else
           {
             preparedStatement.setString(2, "Requirtment"); 
         
           }
            preparedStatement.setString(3,requirementAction.getAttachmentType());  
             preparedStatement.setString(4, requirementAction.getUploadFileName());
        //    preparedStatement.setString(3, tasksAction.getTaskFileName());
            preparedStatement.setString(5, requirementAction.getFileLocation());
          
            preparedStatement.setTimestamp(6, DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(7, requirementAction.getCreatedBy());
            isInsert = preparedStatement.executeUpdate();

            //System.out.println("After insert-->"+isInsert);
        } catch (SQLException se) {
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
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return resultMessage;
    }
     //new
    public String getPath(int id) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int isInsert = 0;
        String resultMessage = "";
         ResultSet resultSet=null;
        try {

            String queryString = "SELECT Id,AttachmentLocation FROM tblAttachments WHERE Id= "+id;
            connection = ConnectionProvider.getInstance().getConnection();
           preparedStatement=connection.prepareStatement(queryString);
          resultSet= preparedStatement.executeQuery();

          while(resultSet.next())
          {
              resultMessage=resultSet.getString("AttachmentLocation");
          }
            //System.out.println("After insert-->"+isInsert);
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
 if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return resultMessage;
    }
    
    
    @Override
    public String getDLCopyLocation(RequirementAction requirementAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int isInsert = 0;
        String resultMessage = "";
         ResultSet resultSet=null;
        try {

            String queryString = "SELECT Id,DlCopyAttachLocation FROM tblRec WHERE ConsultantId= "+requirementAction.getConsultantId()+" AND RequirementId ="+requirementAction.getRequirementId();
           connection = ConnectionProvider.getInstance().getConnection();
           preparedStatement=connection.prepareStatement(queryString);
          resultSet= preparedStatement.executeQuery();

          while(resultSet.next())
          {
              resultMessage=resultSet.getString("DlCopyAttachLocation");
          }
         //  System.out.println("After resultMessage-->"+resultMessage);
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
 if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return resultMessage;
    }

    @Override
    public String getAuthCopyLocation(RequirementAction requirementAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int isInsert = 0;
        String resultMessage = "";
         ResultSet resultSet=null;
        try {

            String queryString = "SELECT Id,WorkAuthAttachLocation FROM tblRec WHERE ConsultantId= "+requirementAction.getConsultantId()+" AND RequirementId ="+requirementAction.getRequirementId();
            connection = ConnectionProvider.getInstance().getConnection();
           preparedStatement=connection.prepareStatement(queryString);
          resultSet= preparedStatement.executeQuery();

          while(resultSet.next())
          {
              resultMessage=resultSet.getString("WorkAuthAttachLocation");
          }
          //  System.out.println("After resultMessage-->"+resultMessage);
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
 if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }

        return resultMessage;
    }


}
