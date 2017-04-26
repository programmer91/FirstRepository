/*
 * CreService.java
 *
 * Created on August 29, 2013, 4:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.cre;

import com.mss.mirage.ecertification.ExamDetailInfoVTO;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import com.mss.mirage.ecertification.QuestionsVTO;
import java.util.TreeMap;
import java.util.LinkedList;
import java.sql.Types;
import java.util.ArrayList;
import com.mss.mirage.util.Properties;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author miracle
 */
public class CreServiceImpl implements CreService {
    
    private List<QuestionsVTO> questionsVtoList = null;
    /** Creates a new instance of CreService */
    public CreServiceImpl() {
    }
    

      
      /***
       *
       *  ExamResult 
       *
       *
       */
      
      /*
       public void getCreDetailResult(int examKeyId, CreAction creAction) throws ServiceLocatorException {
       
        Connection connection= null;
        
        
        CallableStatement callableStatement = null;
        List subTopicDetailsList = null;
        try{
            
            connection=ConnectionProvider.getInstance().getConnection();
           callableStatement = connection.prepareCall("{call spEcertResult(?,?,?,?,?,?,?,?)}");
           callableStatement.setInt(1,examKeyId);
            callableStatement.registerOutParameter(2,Types.VARCHAR);
            callableStatement.registerOutParameter(3,Types.VARCHAR);
            callableStatement.registerOutParameter(4,Types.INTEGER);
            callableStatement.registerOutParameter(5,Types.INTEGER);
            callableStatement.registerOutParameter(6,Types.INTEGER);
            callableStatement.registerOutParameter(7,Types.VARCHAR);
            callableStatement.registerOutParameter(8,Types.VARCHAR);
            callableStatement.execute();
            
            creAction.setDomainName(callableStatement.getString(2));
            creAction.setTopicName(callableStatement.getString(3));
            creAction.setTotalQuest(callableStatement.getInt(4));
            creAction.setAttemptedQuestions(callableStatement.getInt(5));
            creAction.setExamMarks(callableStatement.getInt(6));
            creAction.setExamStatus(callableStatement.getString(7));
            
            String subTopicDetailResult = "";
           subTopicDetailResult = callableStatement.getString(8);
            
            String subTopics [] = subTopicDetailResult.split("@");
            
            subTopicDetailsList = new ArrayList();
            ExamDetailInfoVTO examDetailInfoVTO = null;
            for(int i=0;i<subTopics.length;i++) {
                
                String subTopicInfo[] = subTopics[i].split("!");
               // for(int j=0;j<subTopicInfo.length;j++) {
                
                 examDetailInfoVTO = new ExamDetailInfoVTO();
               //  System.out.println("=============Sub Topic Info Start=============");
                examDetailInfoVTO.setSubtopicName(subTopicInfo[0]);
               // System.out.println("=============Sub Topic Info Start=============");
                examDetailInfoVTO.setSubtopictotalQuestions(Integer.parseInt(subTopicInfo[1]));
               // System.out.println("=============Sub Topic Info Start=============");
                examDetailInfoVTO.setAttemptedQuestion(Integer.parseInt(subTopicInfo[3]));
               // System.out.println("=============Sub Topic Info Start=============");
                examDetailInfoVTO.setSubtopicMarks(Integer.parseInt(subTopicInfo[2]));
               // System.out.println("=============Sub Topic Info Start=============");
                subTopicDetailsList.add(examDetailInfoVTO);
             
           // }
            }
            creAction.setExamDetailInfoList(subTopicDetailsList);
                    
//                    setDomainName(getDomainName());
//            setTopicName(getTopicName());
//            setTotalQuest(getTotalQuest());
//            setAttemptedQuestions(attemptedQuestions);
//            setExamStatus(examStatus);
//            setExamMarks(result);
            
            
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
        
       
       
   }
       */
       /***
        *  Final exam result
        *
        *
        */ 
       /*
        public int insertEmpExamResult(int examKeyId,String EmpLoginId,int totalQuest, int attemptedQuest, int marks,String examStatus)  throws ServiceLocatorException {
            ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;
        int result=0;
            try{
            connection = ConnectionProvider.getInstance().getConnection();
            
            preparedStatement=connection.prepareStatement("INSERT INTO tblEcertResult (EmpId,ExamKeyId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus,EmpType) " +
                    "VALUES(?,?,?,?,?,?,?,?)");
        
           preparedStatement.setString(1,EmpLoginId);
           preparedStatement.setInt(2,examKeyId);
           preparedStatement.setInt(3,marks);
           preparedStatement.setInt(4,totalQuest);
           preparedStatement.setInt(5,attemptedQuest);
           preparedStatement.setTimestamp(6,DateUtility.getInstance().getCurrentMySqlDateTime());
           preparedStatement.setString(7,examStatus);
           preparedStatement.setInt(8,2);
           
           result =  preparedStatement.executeUpdate();
            }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
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
        return result;
      }
        
        */
        /*
         * Consutant add edit Functions
         *
         *
         *
         *
         */
        
        
         public void getConsultantDetails(CreAction creAction) throws ServiceLocatorException
    {
         boolean isUserExist = false;
        
        Connection connection = null;
        PreparedStatement prepstatement = null;
        ResultSet resultSet = null;
               
      //  System.out.println("1.Edit Consultant id---->"+creAction.getId());
        
        
       try{
           connection = ConnectionProvider.getInstance().getConnection();
            prepstatement = connection.prepareStatement("SELECT * FROM tblCreConsultentDetails WHERE Id = "+creAction.getId());
            
            
             resultSet = prepstatement.executeQuery();
            //ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
           //  int noOfRecords = resultSetMetaData.get
             int i = 1;
            while(resultSet.next()){
                 
                    creAction.setAttendingInterviewAt(resultSet.getInt("AttendedAt"));
                    creAction.setCategory(resultSet.getInt("Category"));
                    creAction.setFirstName(resultSet.getString("FName"));
                    creAction.setLastName(resultSet.getString("LName"));
                    creAction.setMiddleName(resultSet.getString("MName"));
                    creAction.setGender(resultSet.getString("Gender"));
                    creAction.setMaritalStatus(resultSet.getString("MaritalStatus"));
                    creAction.setPan(resultSet.getString("PAN"));
                    creAction.setBirthDate(resultSet.getDate("DOB"));
                    creAction.setFatherName(resultSet.getString("FatherName"));  
                    creAction.setFatherOccupation(resultSet.getString("FatherOccupation"));
                    creAction.setFatherIncome(resultSet.getString("AnualIncome"));
                    creAction.setAddress(resultSet.getString("Address"));
                    creAction.setCity(resultSet.getString("City"));
                    creAction.setPin(resultSet.getString("pin"));
                    creAction.setState(resultSet.getString("State"));
                    creAction.setCellPhoneNo(resultSet.getString("CellPhoneNo"));
                    creAction.setAltPhoneNo(resultSet.getString("AlterPhoneNo"));
                    creAction.setPersonalEmail(resultSet.getString("Email1"));
                    creAction.setOtherEmail(resultSet.getString("Email2"));
                    creAction.setAddInfo(resultSet.getString("Addinfo"));
                    creAction.setRefName(resultSet.getString("REFByName"));
                    creAction.setRefEmail(resultSet.getString("RefferedByMailID"));
                    creAction.setRefMobile(resultSet.getString("RefByPhone")); 
                    
                    
             
    
                    //creAction.setCampusLocation(resultSet.getInt("campusLocation")); 
                    
                   // creAction.setRecLocation(resultSet.getString("recLocation")); 
                 //   creAction.setjFairLocation(resultSet.getString("jFairLocation")); 
                    creAction.setCampusLocation(resultSet.getInt("campusLocation")); 
                    creAction.setRecLocation(resultSet.getString("recLocation")); 
                    creAction.setJfairLocation(resultSet.getString("jFairLocation"));
                    creAction.setConsultentId(resultSet.getString("ConsultentId"));
                   // System.out.println("in getCrecondetails----->"+creAction.getJfairLocation()+"from database"+resultSet.getString("jFairLocation"));
            }
             getConsultantAcademics(creAction);
             getConsultantSkillSet(creAction);  
             getConsultantExperience(creAction);
           
       }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(prepstatement != null){
                    prepstatement.close();
                    prepstatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
       // return isUserExist;
        
    }

        
         /**
     * for using with in the class
    *for getting acadmics
     *
     */
    public void getConsultantAcademics(CreAction creAction) throws ServiceLocatorException
    {
         boolean isUserExist = false;
        
        Connection connection = null;
        PreparedStatement prepstatement = null;
        ResultSet resultSet = null;
        
       // System.out.println("2.Edit Consultant id---->"+creAction.getId());
        
       try{
           connection = ConnectionProvider.getInstance().getConnection();
            prepstatement = connection.prepareStatement("SELECT * FROM tblCreAcademics WHERE CreId = '"+creAction.getId()+"' ORDER BY Id");
            
            
             resultSet = prepstatement.executeQuery();
            //ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
           //  int noOfRecords = resultSetMetaData.get
             int i = 1;
            while(resultSet.next()){
                 
                    if(i==1){
                    creAction.setQualification(resultSet.getString("cource"));
                    creAction.setCollege(resultSet.getString("university"));
                    creAction.setMedium(resultSet.getString("MEDIUM"));
                    creAction.setYearOfPass(resultSet.getString("passyear"));
                    creAction.setPercentage(resultSet.getString("percent"));
                    }
                    else if(i==2){
                         creAction.setIpeCategory(resultSet.getString("cource"));
                    creAction.setIpeCollege(resultSet.getString("university"));
                    creAction.setIpeMedium(resultSet.getString("MEDIUM"));
                    creAction.setIpeYearOfPass(resultSet.getString("passyear"));
                    creAction.setIpePercentage(resultSet.getString("percent"));
                    creAction.setIpeStream(resultSet.getString("stream"));
                    }
                    else if(i==3)
                    {
                        creAction.setDegreeQual(resultSet.getString("cource"));
                    creAction.setDegreeCollege(resultSet.getString("university"));
                    creAction.setDegreeMedium(resultSet.getString("MEDIUM"));
                    creAction.setDegreeYearOfPass(resultSet.getString("passyear"));
                    creAction.setDegreePercentage(resultSet.getString("percent"));
                    creAction.setDegreeBranch(resultSet.getString("stream"));
                    }
                    else if(i==4)
                    {
                        creAction.setPgQual(resultSet.getString("cource"));
                    creAction.setPgCollege(resultSet.getString("university"));
                    creAction.setPgMedium(resultSet.getString("MEDIUM"));
                    creAction.setPgYearOfPass(resultSet.getString("passyear"));
                    creAction.setPgPercentage(resultSet.getString("percent"));
                    creAction.setPgStream(resultSet.getString("stream"));
                    }
                    i++;

            }
           
       }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(prepstatement != null){
                    prepstatement.close();
                    prepstatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
       // return isUserExist;
        
    }
    
    /**
     * for using with in the class
    *for getting creconsultant skill set
     *
     */
    public void getConsultantSkillSet(CreAction creAction) throws ServiceLocatorException
    {
         boolean isUserExist = false;
        
        Connection connection = null;
        PreparedStatement prepstatement = null;
        ResultSet resultSet = null;
        
        
        
       try{
           connection = ConnectionProvider.getInstance().getConnection();
            prepstatement = connection.prepareStatement("SELECT * FROM tblCreSkillset WHERE CreId = '"+creAction.getId()+"' ORDER BY Id");
            
            
             resultSet = prepstatement.executeQuery();
            //ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
           //  int noOfRecords = resultSetMetaData.get
             int i = 1;
            while(resultSet.next()){
                 
                    if(i==1){
                    creAction.setSkill1(resultSet.getString("skill"));
                    creAction.setScale1(resultSet.getString("scale"));
                    
                    }
                    else if(i==2){
                    creAction.setSkill2(resultSet.getString("skill"));
                    creAction.setScale2(resultSet.getString("scale"));
                    
                    }
                    else if(i==3)
                    {
                   creAction.setSkill3(resultSet.getString("skill"));
                    creAction.setScale3(resultSet.getString("scale"));
                 
                    }
                    else if(i==4)
                    {
                    creAction.setSkill4(resultSet.getString("skill"));
                    creAction.setScale4(resultSet.getString("scale"));
                   
                    }
                    else if(i==5)
                    {
                    creAction.setSkill5(resultSet.getString("skill"));
                    creAction.setScale5(resultSet.getString("scale"));
                 
                    }
                    else if(i==6)
                    {
                    creAction.setSkill6(resultSet.getString("skill"));
                    creAction.setScale6(resultSet.getString("scale"));
                  
                    }
                    i++;

            }
           
       }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(prepstatement != null){
                    prepstatement.close();
                    prepstatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
       // return isUserExist;
        
    }
    
     /**
     * for using with in the class
    *for getting cre consultant experience
     *
     */
    public void getConsultantExperience(CreAction creAction) throws ServiceLocatorException
    {
         boolean isUserExist = false;
        
        Connection connection = null;
        PreparedStatement prepstatement = null;
        ResultSet resultSet = null;
        
        
        
       try{
           connection = ConnectionProvider.getInstance().getConnection();
            prepstatement = connection.prepareStatement("SELECT * FROM tblCreExperience WHERE CreId = '"+creAction.getId()+"' ORDER BY Id");
            
            
             resultSet = prepstatement.executeQuery();
            //ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
           //  int noOfRecords = resultSetMetaData.get
             int i = 1;
            while(resultSet.next()){
                 
                    if(i==1){
                    creAction.setCompany(resultSet.getString("CompanyName"));
                    creAction.setDesignation(resultSet.getString("Designation"));
                    creAction.setFromDate(resultSet.getDate("Fromdate"));
                    creAction.setToDate(resultSet.getDate("ToDate"));
                    creAction.setLocation(resultSet.getString("Location"));
                    }
                    else if(i==2){
                     creAction.setCompany1(resultSet.getString("CompanyName"));
                    creAction.setDesignation1(resultSet.getString("Designation"));
                    creAction.setFromDate1(resultSet.getDate("Fromdate"));
                    creAction.setToDate1(resultSet.getDate("ToDate"));
                    creAction.setLocation1(resultSet.getString("Location"));
                    }
                    else if(i==3)
                    {
                     creAction.setCompany2(resultSet.getString("CompanyName"));
                    creAction.setDesignation2(resultSet.getString("Designation"));
                    creAction.setFromDate2(resultSet.getDate("Fromdate"));
                    creAction.setToDate2(resultSet.getDate("ToDate"));
                    creAction.setLocation2(resultSet.getString("Location"));
                    }
                  
                    i++;

            }
           
       }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(prepstatement != null){
                    prepstatement.close();
                    prepstatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
       // return isUserExist;
        
    }

    /**
     * Update Consultent
     *  
     */
    
    public int updateConsultantDetails(CreAction creAction,String loginId) throws ServiceLocatorException{
        int updatedRows = 0; 
        Connection connection = null;
        CallableStatement cstatement = null;
        ResultSet resultSet = null;
        int maxCreId = 0;
        
        
        /*@param primaryAction used to store actions*/
       try{
           
          // DataSourceDataProvider.getInstance().   spCreConsultantDetails
           connection = ConnectionProvider.getInstance().getConnection();
            cstatement = connection.prepareCall("call spCreConsultantDetails(?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?,?,?,?)");
                            cstatement.setInt(1,creAction.getCategory());
                            cstatement.setString(2,creAction.getLastName());
                            cstatement.setString(3,creAction.getFirstName());
                            cstatement.setString(4,creAction.getMiddleName());
                            cstatement.setDate(5,creAction.getBirthDate());
                            cstatement.setString(6,creAction.getGender());
                            cstatement.setString(7,creAction.getMaritalStatus());
                            cstatement.setString(8,creAction.getPersonalEmail());
                            cstatement.setString(9,creAction.getOtherEmail());
                            cstatement.setString(10,creAction.getCellPhoneNo());
                            cstatement.setString(11,creAction.getAltPhoneNo());
                            cstatement.setString(12,creAction.getPan());
                            cstatement.setString(13,creAction.getRefName());
                            cstatement.setString(14,creAction.getRefEmail());
                            cstatement.setString(15,creAction.getRefMobile());
                            cstatement.setString(16,creAction.getFatherName());
                            cstatement.setString(17,creAction.getFatherOccupation());
                            cstatement.setString(18,creAction.getFatherIncome());
                            cstatement.setString(19,creAction.getAddress());
                            cstatement.setString(20,creAction.getCity());
                            cstatement.setString(21,creAction.getState());
                            cstatement.setString(22,creAction.getPin());
                            cstatement.setString(23,creAction.getAddInfo());
                            cstatement.setInt(24,creAction.getAttendingInterviewAt());
                            cstatement.setString(25,creAction.getQualification());
                            cstatement.setString(26,creAction.getEducation());
                            cstatement.setString(27,creAction.getCollege());
                            cstatement.setString(28,creAction.getMedium());
                            cstatement.setString(29,creAction.getYearOfPass());
                            cstatement.setString(30,creAction.getPercentage());
                            cstatement.setString(31,creAction.getIpeCategory());
                            cstatement.setString(32,creAction.getIpeStream());
                            cstatement.setString(33,creAction.getIpeCollege());
                            cstatement.setString(34,creAction.getIpeMedium());
                            cstatement.setString(35,creAction.getIpeYearOfPass());
                            cstatement.setString(36,creAction.getIpePercentage());
                            cstatement.setString(37,creAction.getDegreeQual());
                            cstatement.setString(38,creAction.getDegreeBranch());
                            cstatement.setString(39,creAction.getDegreeCollege());
                            cstatement.setString(40,creAction.getDegreeMedium());
                            cstatement.setString(41,creAction.getDegreeYearOfPass());
                            cstatement.setString(42,creAction.getDegreePercentage());
                            cstatement.setString(43,creAction.getPgQual());
                            cstatement.setString(44,creAction.getPgStream());
                            cstatement.setString(45,creAction.getPgCollege());
                            cstatement.setString(46,creAction.getPgMedium());
                            cstatement.setString(47,creAction.getPgYearOfPass());
                            cstatement.setString(48,creAction.getPgPercentage());
                            cstatement.setString(49,creAction.getSkill1());
                            cstatement.setString(50,creAction.getSkill2());
                            cstatement.setString(51,creAction.getSkill3());
                            cstatement.setString(52,creAction.getSkill4());
                            cstatement.setString(53,creAction.getSkill5());
                            cstatement.setString(54,creAction.getSkill6());
                            cstatement.setString(55,creAction.getScale1());
                            cstatement.setString(56,creAction.getScale2());
                            cstatement.setString(57,creAction.getScale3());
                            cstatement.setString(58,creAction.getScale4());
                            cstatement.setString(59,creAction.getScale5());
                            cstatement.setString(60,creAction.getScale6());
                            
                            cstatement.setString(61,creAction.getCompany());
                            cstatement.setString(62,creAction.getDesignation());
                            cstatement.setDate(63,creAction.getFromDate());
                            cstatement.setDate(64,creAction.getToDate());
                            cstatement.setString(65,creAction.getLocation());
                            cstatement.setString(66,creAction.getCompany1());
                            cstatement.setString(67,creAction.getDesignation1());
                            cstatement.setDate(68,creAction.getFromDate1());
                            cstatement.setDate(69,creAction.getToDate1());
                            cstatement.setString(70,creAction.getLocation1());
                            cstatement.setString(71,creAction.getCompany2());
                            cstatement.setString(72,creAction.getDesignation2());
                            cstatement.setDate(73,creAction.getFromDate2());
                            cstatement.setDate(74,creAction.getToDate2());
                            cstatement.setString(75,creAction.getLocation2());
                            cstatement.setString(76,"Update");
                            cstatement.setInt(77,creAction.getId());
                            cstatement.setString(78,loginId);
                            
                             cstatement.setInt(79,creAction.getCampusLocation());
                            cstatement.setString(80,creAction.getRecLocation());
                            cstatement.setString(81,creAction.getJfairLocation());
                            
                            cstatement.registerOutParameter(82,Types.INTEGER);  
                            updatedRows = cstatement.executeUpdate();
                            maxCreId = cstatement.getInt(82);  
                            
                          //  addCreAcademics(creAction,maxCreId);
         
           
       }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(cstatement != null){
                    cstatement.close();
                    cstatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return maxCreId;
        
    }       

    
    /*** cre collection changes ****/
    
    
    public Collection getCreTechReviewCollection(int creId,int noOfRecords) throws ServiceLocatorException {
        Map creReviewMap = new TreeMap();
        int counter = 1;
        String tempCreatedDate ="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            String queryString = "SELECT * FROM tblCreTchComments WHERE CreId="+creId+" AND  DeletedFlag != 1 ORDER BY CretedDate DESC";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                if(counter <= noOfRecords){
                    CreReviewVTO creReviewVTO = new CreReviewVTO();
                    creReviewVTO.setTechReviewId(resultSet.getInt("id"));
                    creReviewVTO.setTechLead(resultSet.getString("TchLoginId"));
                    creReviewVTO.setTechCreatedDate(resultSet.getString("CretedDate"));
                    creReviewVTO.setTechModifiedDate(resultSet.getString("ModifiedDate"));
                    creReviewVTO.setTechModifiedLead(resultSet.getString("ModifiedBy"));
                    creReviewVTO.setTechReviewStatus(resultSet.getString("Status"));
                    creReviewVTO.setCreId(resultSet.getInt("CreId"));
                    creReviewMap.put("stateVTO"+counter,creReviewVTO);
                    counter++;
                }
            }
            
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
        return creReviewMap.values();
    }
    
    public boolean creTechReviewAdd(CreAction creAction) throws ServiceLocatorException {
         Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        
        boolean isInserted = false;
        String queryString = "INSERT INTO tblCreTchComments(TchLoginId,CretedDate,Comments,Status,CreId) VALUES(?,?,?,?,?)";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            
            preparedStatement.setString(1,creAction.getCreatedBy());
            preparedStatement.setTimestamp(2,DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(3,creAction.getTechLeadComments());
            preparedStatement.setString(4,creAction.getTechReviewStatus());
            preparedStatement.setInt(5,creAction.getId());
            
            isInserted = preparedStatement.execute();
            
        }catch (SQLException se){
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
    
      public boolean updateTechReview(CreAction creAction) throws ServiceLocatorException {
         Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        
        boolean isInserted = false;
        String queryString = "UPDATE tblCreTchComments SET ModifiedDate = ?, ModifiedBy = ?, Comments = ?, Status = ? WHERE id = ? AND CreId = ?";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            
            
            preparedStatement.setTimestamp(1,DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(2,creAction.getCreatedBy());
            preparedStatement.setString(3,creAction.getTechLeadComments());
            preparedStatement.setString(4,creAction.getTechReviewStatus());
            preparedStatement.setInt(5,creAction.getTechReviewId());
            preparedStatement.setInt(6,creAction.getId());
            
            isInserted = preparedStatement.execute();
            
        }catch (SQLException se){
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
    
     public CreReviewVTO getTechLeadReview(int techReviewId, int creId) throws ServiceLocatorException {
        Map creReviewMap = new TreeMap();
        int counter = 1;
        String tempCreatedDate ="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         CreReviewVTO creReviewVTO = null;
        try{
            String queryString = "SELECT * FROM tblCreTchComments WHERE CreId="+creId+" AND id = "+techReviewId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            creReviewVTO = new CreReviewVTO();
            while(resultSet.next()){
                
                    
                    creReviewVTO.setTechReviewId(resultSet.getInt("id"));
                    creReviewVTO.setTechLead(resultSet.getString("TchLoginId"));
                    creReviewVTO.setTechCreatedDate(resultSet.getString("CretedDate"));
                    creReviewVTO.setTechModifiedDate(resultSet.getString("ModifiedDate"));
                    creReviewVTO.setTechModifiedLead(resultSet.getString("ModifiedBy"));
                    creReviewVTO.setTechReviewStatus(resultSet.getString("Status"));
                    creReviewVTO.setCreId(resultSet.getInt("CreId"));
                    creReviewVTO.setTechLeadComments(resultSet.getString("Comments"));
                  
            }
            
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
        return creReviewVTO;
    }
   
     
     public int deleteTechLeadReview(int techReviewId) throws ServiceLocatorException{
        int deletedRows = 0;
        Connection connection = null;
        Statement statement = null;
        String queryString = "UPDATE tblCreTchComments set DeletedFlag = 1 WHERE Id="+techReviewId;
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
    public Collection getCreHrReviewCollection(int creId,int noOfRecords) throws ServiceLocatorException {
        Map creHrReviewMap = new TreeMap();
        int counter = 1;
        String tempCreatedDate ="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            String queryString = "SELECT * FROM tblCreHrComments WHERE CreId="+creId+" AND  DeletedFlag != 1 ORDER BY CretedDate DESC";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                if(counter <= noOfRecords){
                    CreReviewVTO creHrReviewVTO = new CreReviewVTO();
                    creHrReviewVTO.setHrReviewId(resultSet.getInt("id"));
                    creHrReviewVTO.setHrLoginId(resultSet.getString("HrLoginId"));
                    creHrReviewVTO.setHrCreatedDate(resultSet.getString("CretedDate"));
                    
                    
                    creHrReviewVTO.setHrReviewStatus(resultSet.getString("Status"));
                    creHrReviewVTO.setCreId(resultSet.getInt("CreId"));
                    creHrReviewMap.put("stateVTO"+counter,creHrReviewVTO);
                    counter++;
                }
            }
            
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
        return creHrReviewMap.values();
    }
    
    public boolean creHrReviewAdd(CreAction creAction) throws ServiceLocatorException {
         Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        
        boolean isInserted = false;
        String queryString = "INSERT INTO tblCreHrComments(HrLoginId,CretedDate,Comments,Status,CreId) VALUES(?,?,?,?,?)";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            
            preparedStatement.setString(1,creAction.getCreatedBy());
            preparedStatement.setTimestamp(2,DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(3,creAction.getHrComments());
            preparedStatement.setString(4,creAction.getHrReviewStatus());
            preparedStatement.setInt(5,creAction.getId());
            
            isInserted = preparedStatement.execute();
            
        }catch (SQLException se){
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
 
    public CreReviewVTO getHrReview(int hrReviewId, int creId) throws ServiceLocatorException {
        
        
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         CreReviewVTO creReviewVTO = null;
        try{
            String queryString = "SELECT * FROM tblCreHrComments WHERE CreId="+creId+" AND id = "+hrReviewId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            creReviewVTO = new CreReviewVTO();
            while(resultSet.next()){
                
                    
                    creReviewVTO.setHrReviewId(resultSet.getInt("id"));
                    creReviewVTO.setHrLoginId(resultSet.getString("HrLoginId"));
                    creReviewVTO.setTechCreatedDate(resultSet.getString("CretedDate"));
                    //creReviewVTO.setTechModifiedDate(resultSet.getString("ModifiedDate"));
                    //creReviewVTO.setTechModifiedLead(resultSet.getString("ModifiedBy"));
                    creReviewVTO.setHrReviewStatus(resultSet.getString("Status"));
                    creReviewVTO.setCreId(resultSet.getInt("CreId"));
                    creReviewVTO.setHrComments(resultSet.getString("Comments"));
                  
            }
            
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
        return creReviewVTO;
    }
   
    
    public boolean updateHrReview(CreAction creAction) throws ServiceLocatorException {
         Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        
        boolean isInserted = false;
        String queryString = "UPDATE tblCreHrComments SET ModifiedDate = ?, ModifiedBy = ?, Comments = ?, Status = ? WHERE id = ? AND CreId = ?";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            
            
            preparedStatement.setTimestamp(1,DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(2,creAction.getCreatedBy());
            preparedStatement.setString(3,creAction.getHrComments());
            preparedStatement.setString(4,creAction.getHrReviewStatus());
            preparedStatement.setInt(5,creAction.getHrReviewId());
            preparedStatement.setInt(6,creAction.getId());
            
            isInserted = preparedStatement.execute();
            
        }catch (SQLException se){
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
    
    
    public int deleteHrReview(int hrReviewId) throws ServiceLocatorException{
        int deletedRows = 0;
        Connection connection = null;
        Statement statement = null;
        String queryString = "UPDATE tblCreHrComments set DeletedFlag = 1 WHERE Id="+hrReviewId;
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
     public Collection getCreVpReviewCollection(int creId,int noOfRecords) throws ServiceLocatorException {
        Map creVpReviewMap = new TreeMap();
        int counter = 1;
        String tempCreatedDate ="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            String queryString = "SELECT * FROM tblCreVPComments WHERE CreId="+creId+" AND  DeletedFlag != 1 ORDER BY CretedDate DESC";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                if(counter <= noOfRecords){
                    CreReviewVTO creVpReviewVTO = new CreReviewVTO();
                    creVpReviewVTO.setVpReviewId(resultSet.getInt("id"));
                    creVpReviewVTO.setVpLoginId(resultSet.getString("VPLoginId"));
                    creVpReviewVTO.setVpCreatedDate(resultSet.getString("CretedDate"));
                    
                    
                    creVpReviewVTO.setVpReviewStatus(resultSet.getString("Status"));
                    creVpReviewVTO.setCreId(resultSet.getInt("CreId"));
                    creVpReviewMap.put("stateVTO"+counter,creVpReviewVTO);
                    counter++;
                }
            }
            
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
        return creVpReviewMap.values();
    }
     public boolean creVpReviewAdd(CreAction creAction) throws ServiceLocatorException {
         Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        
        boolean isInserted = false;
        String queryString = "INSERT INTO tblCreVPComments(VPLoginId,CretedDate,Comments,Status,CreId) VALUES(?,?,?,?,?)";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            
            preparedStatement.setString(1,creAction.getCreatedBy());
            preparedStatement.setTimestamp(2,DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(3,creAction.getVpComments());
            preparedStatement.setString(4,creAction.getVpReviewStatus());
            preparedStatement.setInt(5,creAction.getId());
            
            isInserted = preparedStatement.execute();
            
        }catch (SQLException se){
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
 public CreReviewVTO getVpReview(int vpReviewId, int creId) throws ServiceLocatorException {
        
        
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         CreReviewVTO creReviewVTO = null;
        try{
            String queryString = "SELECT * FROM tblCreVPComments WHERE CreId="+creId+" AND id = "+vpReviewId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            creReviewVTO = new CreReviewVTO();
            while(resultSet.next()){
                
                    
                    creReviewVTO.setVpReviewId(resultSet.getInt("id"));
                    creReviewVTO.setVpLoginId(resultSet.getString("VPLoginId"));
                    creReviewVTO.setVpCreatedDate(resultSet.getString("CretedDate"));
                    //creReviewVTO.setTechModifiedDate(resultSet.getString("ModifiedDate"));
                    //creReviewVTO.setTechModifiedLead(resultSet.getString("ModifiedBy"));
                    creReviewVTO.setVpReviewStatus(resultSet.getString("Status"));
                    creReviewVTO.setCreId(resultSet.getInt("CreId"));
                    creReviewVTO.setVpComments(resultSet.getString("Comments"));
                  
            }
            
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
        return creReviewVTO;
    }
 
 public boolean updateVpReview(CreAction creAction) throws ServiceLocatorException {
         Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        
        boolean isInserted = false;
        String queryString = "UPDATE tblCreVPComments SET ModifiedDate = ?, ModifiedBy = ?, Comments = ?, Status = ? WHERE id = ? AND CreId = ?";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            
            
            preparedStatement.setTimestamp(1,DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(2,creAction.getCreatedBy());
            preparedStatement.setString(3,creAction.getVpComments());
            preparedStatement.setString(4,creAction.getVpReviewStatus());
            preparedStatement.setInt(5,creAction.getVpReviewId());
            preparedStatement.setInt(6,creAction.getId());
            
            isInserted = preparedStatement.execute();
            
        }catch (SQLException se){
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
    
     public int deleteVpReview(int vpReviewId) throws ServiceLocatorException{
        int deletedRows = 0;
        Connection connection = null;
        Statement statement = null;
        String queryString = "UPDATE tblCreVPComments set DeletedFlag = 1 WHERE id="+vpReviewId;
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
        
        
        
          /**
     * To update Consultant status
     */
    
    /**
   *  Update Consultant status based on consultant id in tblCreConsultentDetails 
   *
   *  Stauts,modifiedby,modified date,level
   *
   */
  
   public void updateCREConsultantStatus(int creConsultantId,String status,String level,String loginId) throws ServiceLocatorException {
          Connection connection = null;
          PreparedStatement preparedStatement = null;
          int i=0;
          try {
              connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("UPDATE tblCreConsultentDetails SET Status=?,Level=?,ModifiedBy=?,ModifiedDate=?  WHERE Id = ?");
              preparedStatement.setString(1,status);
              preparedStatement.setString(2,level);
              preparedStatement.setString(3,loginId);
              preparedStatement.setTimestamp(4,DateUtility.getInstance().getCurrentMySqlDateTime());
              preparedStatement.setInt(5,creConsultantId);
              
              i = preparedStatement.executeUpdate();
          }catch (Exception e){
              System.err.println("Exception is-->"+e.getMessage());
          }finally {
              try {
               if(preparedStatement!=null){
                  preparedStatement.close();
                  preparedStatement = null;
              }if(connection!=null){
                  connection.close();
                  connection = null;
              } 
              }catch(Exception sqle){
                  System.err.println("SQL Exception is-->"+sqle.getMessage());
              }
          }
          
      }
   
   
    public Collection getCreExamReviewCollection(int creId,int noOfRecords) throws ServiceLocatorException {
        Map creExamReviewMap = new TreeMap();
        int counter = 1;
        String tempCreatedDate ="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            //String queryString = "SELECT * FROM tblCreVPComments WHERE CreId="+creId+" AND  DeletedFlag != 1 ORDER BY CretedDate DESC";
            String queryString = "SELECT tblEcertResult.ExamKeyId as examKeyId,tblEcertResult.EmpId AS empId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus FROM tblEcertResult where tblEcertResult.EmpId = 'MSSCRE"+creId+"'";
           // System.out.println("Cre Exam Review Query-->"+queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                if(counter <= noOfRecords){
                    CreReviewVTO creExamReviewVTO = new CreReviewVTO();
                   creExamReviewVTO.setCreLoginId(resultSet.getString("empId"));
                   creExamReviewVTO.setMarks(resultSet.getInt("Marks"));
                   creExamReviewVTO.setTotalQuestions(resultSet.getInt("TotalQuestions"));
                   creExamReviewVTO.setAttemptedQuestions(resultSet.getInt("AttemptedQuestions"));
                   creExamReviewVTO.setDateSubmitted(resultSet.getString("DateSubmitted"));
                   creExamReviewVTO.setExamStatus(resultSet.getString("ExamStatus"));
                   creExamReviewVTO.setExamKeyId(String.valueOf(resultSet.getInt("examKeyId")));
                    creExamReviewMap.put("stateVTO"+counter,creExamReviewVTO);
                    counter++;
                }
            }
            
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
        return creExamReviewMap.values();
    }

   /* public void updateCREExamStatus(int creConsultantId,String status,String level) throws ServiceLocatorException {
          Connection connection = null;
          PreparedStatement preparedStatement = null;
          int i=0;
          try {
              connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("UPDATE tblCreConsultentDetails SET Status=?,Level=? WHERE Id = ?");
              preparedStatement.setString(1,status);
              preparedStatement.setString(2,level);
             
              preparedStatement.setInt(3,creConsultantId);
              
              i = preparedStatement.executeUpdate();
          }catch (Exception e){
              System.err.println("Exception is-->"+e.getMessage());
          }finally {
              try {
               if(preparedStatement!=null){
                  preparedStatement.close();
                  preparedStatement = null;
              }if(connection!=null){
                  connection.close();
                  connection = null;
              } 
              }catch(Exception sqle){
                  System.err.println("SQL Exception is-->"+sqle.getMessage());
              }
          }
          
      }*/
    /*
     * New methods for Cre Start Exam
     * 
     */
    
  /*  
public CreReviewVTO doExamProcess(int creId, String creLoginId, int domainId, int topicId) throws ServiceLocatorException {
         Connection connection = null;
        
        CallableStatement callableStatement= null;
        CreReviewVTO creReviewVTO = new CreReviewVTO();
        
        
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spCreStartExam(" +
                    "?,?,?,?,"
                    + "?,?,?,?)" +
                    
                    " }"); 
            
            callableStatement.setInt(1, creId); 
            callableStatement.setString(2, creLoginId);
            callableStatement.setInt(3, domainId);
            callableStatement.setInt(4, topicId);
            callableStatement.registerOutParameter(5,Types.INTEGER);
            callableStatement.registerOutParameter(6,Types.INTEGER);
            callableStatement.registerOutParameter(7,Types.INTEGER);
            callableStatement.registerOutParameter(8,Types.INTEGER);
            
            callableStatement.executeUpdate();
             
             creReviewVTO.setTotalQues(callableStatement.getInt(5));
             creReviewVTO.setMinMarks(callableStatement.getInt(6));
             creReviewVTO.setTotDuration(callableStatement.getInt(7));
             creReviewVTO.setExamKey(callableStatement.getInt(8));
             
             
             
             
        }
        catch(SQLException se) {
            System.out.println("Error-->"+se.getMessage());
            se.printStackTrace();
            throw new ServiceLocatorException(se.getMessage());
        }finally{
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        
  return creReviewVTO;
   }
   */
   /*
   public Map getCreQuestions(int examKey) throws ServiceLocatorException{
        
          QuestionsVTO questionVTO = null;
            Map  questionMap = null;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        
        queryString = "SELECT Id,Question,option1,option2,option3,option4,subtopicId,SubtopicName FROM tblCretemp_EQ WHERE ExmKeyId = "+examKey+" ORDER BY subtopicId ";
     
     
        connection = ConnectionProvider.getInstance().getConnection();
        
        try {
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            questionMap = new TreeMap();
             int i=1;
            while(resultSet.next()){
                questionVTO = new QuestionsVTO();
                questionVTO.setId(resultSet.getInt("Id"));
                questionVTO.setQuestion(resultSet.getString("Question"));
                questionVTO.setOption1(resultSet.getString("option1"));
                questionVTO.setOption2(resultSet.getString("option2"));
                questionVTO.setOption3(resultSet.getString("option3"));
                questionVTO.setOption4(resultSet.getString("option4"));
                questionVTO.setSubtopicId(resultSet.getInt("SubtopicId"));
                questionVTO.setSubTopicName(resultSet.getString("SubtopicName"));
                 questionMap.put(i,questionVTO);
                i++;
            }
            deleteCreQuestions(examKey);
        } catch (SQLException sqlex) {
            throw new ServiceLocatorException(sqlex);
        }finally {
            
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
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
        return questionMap;
    }//closing the method.
 */
    public int deleteCreQuestions(int examKey) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionProvider.getInstance().getConnection();
        ResultSet resultSet = null;
        String subtopicslist = null;
        int i=0;
        try{
              preparedStatement = connection.prepareStatement("DELETE FROM tblCretemp_EQ WHERE ExmKeyId = "+examKey);
              i = preparedStatement.executeUpdate();
           
           
        }catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }finally {     
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null){
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
        return i;
  }
   

//New Cre
    public int doAddSubTopics(List subTopicId,String examType,int minMarks,int noOfQuestions,int time,int totalQuestionsFromEachSub) throws ServiceLocatorException{
       // System.out.println("into doAddOrUpdateSubTopics ");
     Connection connection= null;
        
        /** callableStatement is a reference variable for CallableStatement . */
       PreparedStatement preparedStatement = null;
       int result=0;
        try{
          connection = ConnectionProvider.getInstance().getConnection();
            
            preparedStatement=connection.prepareStatement("INSERT INTO tblCreSetExam (ExamType,MinMarks,Time,NoOfQuestions,SubTopicIds,NoOfSubTopicQues) " +
                    "VALUES(?,?,?,?,?,?)");
        
           preparedStatement.setString(1,examType);
           preparedStatement.setInt(2,minMarks);
           preparedStatement.setInt(3,time);
           preparedStatement.setInt(4,noOfQuestions);
           //subTopicId
           
                //   StringTokenizer st = new StringTokenizer(examType, examType)
           //String subTopicLists = (String)subTopicId.get(0)+(String)subTopicId.get(1)+""+(String)subTopicId.get(2);
       /*    String subTopicLists ="";
           for(int i =0;i<subTopicId.size();i++)
           {
               subTopicLists = subTopicLists+(String)subTopicId.get(i)+",";
             
           }
             System.out.println("subTopicsList----------->"+subTopicLists);
             
           String subTopicLists1=subTopicLists.substring(0,subTopicLists.length()-1);*/
           String subTopicLists1=DataSourceDataProvider.getInstance().getSubTopicIdString(subTopicId);
           
           preparedStatement.setString(5,subTopicLists1);
           preparedStatement.setInt(6,totalQuestionsFromEachSub);
           
           result =  preparedStatement.executeUpdate();
       
            
        }catch(SQLException se){
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
            } catch(SQLException se){
                se.printStackTrace();
            }
        }   
        
        return result;
        
    }
 public String getCreExamDetails(CreAction creAction,int examId1) throws ServiceLocatorException
 {   Map idsMap = new TreeMap();
    // System.out.println("into creexam details impl----------->"+examId1);
 Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
      
        String sqlQuery = "SELECT tblCreSetExam.Id,tblCreSetExam.ExamType,tblCreSetExam.MinMarks,tblCreSetExam.Time,tblCreSetExam.NoOfQuestions,tblCreSetExam.SubTopicIds,tblCreSetExam.NoOfSubTopicQues from tblCreSetExam where Id="+examId1;
        
       
        String uploadFileName = null;
        String category = null;
        int taskId=0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
           // questionsVTO.setId(questionId);
            resultSet.next();
            creAction.setExamType(resultSet.getString("ExamType"));
            creAction.setMinMarks(resultSet.getInt("MinMarks"));
            creAction.setTotDuration(resultSet.getInt("Time"));
            creAction.setTotalQuest(resultSet.getInt("NoOfQuestions"));
           String subtopicsID=resultSet.getString("SubTopicIds");
          
        /* String replace = subtopicsID.replace("[","");
         System.out.println(replace);
         String subtopicsID1 = replace.replace("]","");
         idsMap  = DataSourceDataProvider.getInstance().getSubTopicIds(subtopicsID1);
         System.out.println(idsMap);*/
   StringTokenizer stk = new StringTokenizer(subtopicsID,",");  
   List list = new ArrayList();  
       
    while ( stk.hasMoreTokens() ) {  
    list.add(stk.nextToken());  
    }   
   // System.out.println("list----------->"+list);
       //  List list = new ArrayList(idsMap.keySet());
         
         creAction.setSubtopics1(list);
         creAction.setTotalQues(resultSet.getInt("NoOfSubTopicQues"));
        } catch(SQLException se){
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
       // return questionsVTO;   
        
        
        return null;
 }
 public boolean doUpdateSubTopics(int examId,List subTopicId,String examType,int minMarks,int noOfQuestions,int time,int totalQuestionsFromEachSub) throws ServiceLocatorException{
       // System.out.println("into doUpdateSubTopics ");
       // System.out.println("id------------>"+examId);
     Connection connection= null;
        boolean isUpdated = false;
        /** callableStatement is a reference variable for CallableStatement . */
       PreparedStatement preparedStatement = null;
       //int result=0;
        try{
          connection = ConnectionProvider.getInstance().getConnection();
            
            preparedStatement=connection.prepareStatement("update tblCreSetExam set ExamType=?,MinMarks=?,Time=?,NoOfQuestions=?,SubTopicIds=?,NoOfSubTopicQues=? where Id=?");
        
           preparedStatement.setString(1,examType);
           preparedStatement.setInt(2,minMarks);
           preparedStatement.setInt(3,time);
           preparedStatement.setInt(4,noOfQuestions);
          /* String subTopicLists ="";
           for(int i =0;i<subTopicId.size();i++)
           {
               subTopicLists = subTopicLists+(String)subTopicId.get(i)+",";
             
           }
             System.out.println("subTopicsList----------->"+subTopicLists);
             
           String subTopicLists1=subTopicLists.substring(0,subTopicLists.length()-1);*/
           String subTopicLists1=DataSourceDataProvider.getInstance().getSubTopicIdString(subTopicId);
           preparedStatement.setString(5,subTopicLists1);
           preparedStatement.setInt(6,totalQuestionsFromEachSub);
           preparedStatement.setInt(7,examId);
           
           
           isUpdated =  preparedStatement.execute();
      // System.out.println("booolean------>"+isUpdated);
            
        }catch(SQLException se){
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
            } catch(SQLException se){
                se.printStackTrace();
            }
        }   
        
        return isUpdated;
        
    }
public boolean inActiveCreExam(CreAction creAction,int examId1) throws ServiceLocatorException 
 {  
        Connection connection = null;
        PreparedStatement preparedStatement = null;
       // ResultSet resultSet = null;
        String sqlQuery = "update tblCreSetExam set Status='InActive' where Id="+examId1;
        boolean isUpdated = false;
       
        String uploadFileName = null;
        String category = null;
        int taskId=0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            isUpdated = preparedStatement.execute();
           // questionsVTO.setId(questionId);
           
        } catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
               
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
       // return questionsVTO;   
        
        
        return isUpdated;
}

/*For Single procedure for startExam .
 * Author : santosh Kola
 * Date : 02/27/2014
 * Method : doStartExam()
 */

public CreReviewVTO doStartExam(int creId,int examTypeId) throws ServiceLocatorException  {
     CreReviewVTO creReviewVTO = new CreReviewVTO();
     Connection connection = null;
     CallableStatement callableStatement = null;
     ResultSet resultSet = null;
     QuestionsVTO questionVTO = null;
            Map  questionMap = null;
      try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spCreDoStartExam(?,?,?,?,?,?)}");
            callableStatement.setInt(1,creId);
            callableStatement.setInt(2,examTypeId);
            callableStatement.registerOutParameter(3,Types.INTEGER);
            callableStatement.registerOutParameter(4,Types.INTEGER);
            callableStatement.registerOutParameter(5,Types.INTEGER);
            callableStatement.registerOutParameter(6,Types.INTEGER);
            
            resultSet = callableStatement.executeQuery();

            creReviewVTO.setTotalQues(callableStatement.getInt(3));
            creReviewVTO.setMinMarks(callableStatement.getInt(4));
            creReviewVTO.setTotDuration(callableStatement.getInt(5));
            creReviewVTO.setExamKey(callableStatement.getInt(6));
             questionMap = new TreeMap();
              int i=1;
            while(resultSet.next()){
             questionVTO = new QuestionsVTO();
                questionVTO.setId(resultSet.getInt("Id"));
                questionVTO.setQuestion(resultSet.getString("Question"));
                questionVTO.setOption1(resultSet.getString("option1"));
                questionVTO.setOption2(resultSet.getString("option2"));
                questionVTO.setOption3(resultSet.getString("option3"));
                questionVTO.setOption4(resultSet.getString("option4"));
                questionVTO.setSubtopicId(resultSet.getInt("SubtopicId"));
                questionVTO.setSubTopicName(resultSet.getString("SubtopicName"));
                questionMap.put(i,questionVTO);
                
                i++;
            }
            creReviewVTO.setQuestionVtoMap(questionMap);
            
            deleteCreQuestions(creReviewVTO.getExamKey());
            
     } catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                 if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(callableStatement != null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
           
     return creReviewVTO;
}
/*For Submit Exam
 * Author : Santosh Kola
 * Date : 03/03/2014
 * 
 */
public void doSubmitExam(int examKey,int creId,int totalQuest,int attemptedQuest,int minMarks,String questionVtoList,CreAction creAction) throws ServiceLocatorException  {
     CreReviewVTO creReviewVTO = new CreReviewVTO();
     Connection connection = null;
     CallableStatement callableStatement = null;
     ResultSet resultSet = null;
     QuestionsVTO questionVTO = null;
            Map  questionMap = null;
            List subTopicDetailsList = null;
      try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spCreSubmitExam(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1,examKey);
            callableStatement.setInt(2,creId);
            callableStatement.setInt(3,totalQuest);
            callableStatement.setInt(4,attemptedQuest);
            callableStatement.setInt(5,minMarks);
            callableStatement.setString(6,questionVtoList);
            callableStatement.registerOutParameter(7,Types.VARCHAR);
            callableStatement.registerOutParameter(8,Types.VARCHAR);
            callableStatement.registerOutParameter(9,Types.INTEGER);
            callableStatement.registerOutParameter(10,Types.INTEGER);
            callableStatement.registerOutParameter(11,Types.INTEGER);
            callableStatement.registerOutParameter(12,Types.VARCHAR);
            callableStatement.registerOutParameter(13,Types.VARCHAR);
            callableStatement.execute();
            
            creAction.setDomainName(callableStatement.getString(7));
            creAction.setTopicName(callableStatement.getString(8));
            creAction.setTotalQuest(callableStatement.getInt(9));
            creAction.setAttemptedQuestions(callableStatement.getInt(10));
            creAction.setExamMarks(callableStatement.getInt(11));
            creAction.setExamStatus(callableStatement.getString(12));
            
            String subTopicDetailResult = "";
           subTopicDetailResult = callableStatement.getString(13);
            
            String subTopics [] = subTopicDetailResult.split("@");
            
            subTopicDetailsList = new ArrayList();
            ExamDetailInfoVTO examDetailInfoVTO = null;
            for(int i=0;i<subTopics.length;i++) {
                
                String subTopicInfo[] = subTopics[i].split("!");
               
                
                 examDetailInfoVTO = new ExamDetailInfoVTO();
            
                examDetailInfoVTO.setSubtopicName(subTopicInfo[0]);
          
                examDetailInfoVTO.setSubtopictotalQuestions(Integer.parseInt(subTopicInfo[1]));
               
                examDetailInfoVTO.setAttemptedQuestion(Integer.parseInt(subTopicInfo[3]));
               
                examDetailInfoVTO.setSubtopicMarks(Integer.parseInt(subTopicInfo[2]));
               
                subTopicDetailsList.add(examDetailInfoVTO);
             
           
            }
            creAction.setExamDetailInfoList(subTopicDetailsList);
            
//            callableStatement.registerOutParameter(3,Types.INTEGER);
//            callableStatement.registerOutParameter(4,Types.INTEGER);
//            callableStatement.registerOutParameter(5,Types.INTEGER);
//            callableStatement.registerOutParameter(6,Types.INTEGER);
            
            //resultSet = callableStatement.executeQuery();

//            creReviewVTO.setTotalQues(callableStatement.getInt(3));
//            creReviewVTO.setMinMarks(callableStatement.getInt(4));
//            creReviewVTO.setTotDuration(callableStatement.getInt(5));
//            creReviewVTO.setExamKey(callableStatement.getInt(6));
          /*   questionMap = new TreeMap();
              int i=1;
            while(resultSet.next()){
             questionVTO = new QuestionsVTO();
                questionVTO.setId(resultSet.getInt("Id"));
                questionVTO.setQuestion(resultSet.getString("Question"));
                questionVTO.setOption1(resultSet.getString("option1"));
                questionVTO.setOption2(resultSet.getString("option2"));
                questionVTO.setOption3(resultSet.getString("option3"));
                questionVTO.setOption4(resultSet.getString("option4"));
                questionVTO.setSubtopicId(resultSet.getInt("SubtopicId"));
                questionVTO.setSubTopicName(resultSet.getString("SubtopicName"));
                questionMap.put(i,questionVTO);
                
                i++;
            }
            creReviewVTO.setQuestionVtoMap(questionMap);
            
            deleteCreQuestions(creReviewVTO.getExamKey());
            */
     } catch(SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                 if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(callableStatement != null){
                    callableStatement.close();
                    callableStatement = null;
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


/*Methods For Onboard commnets
 * Date : 08/15/2014
 * Author : Santosh Kola
 */
 public boolean creOnboardReviewAdd(CreAction creAction) throws ServiceLocatorException {
         Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        
        boolean isInserted = false;
        String queryString = "INSERT INTO tblCreOnBoardComments(CreatedBy,CreatedDate,Comments,CreId) VALUES(?,?,?,?)";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            
            preparedStatement.setString(1,creAction.getCreatedBy());
            preparedStatement.setTimestamp(2,DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(3,creAction.getOnboardComments());
            //preparedStatement.setString(4,creAction.getTechReviewStatus());
            preparedStatement.setInt(4,creAction.getId());
            
            isInserted = preparedStatement.execute();
            
        }catch (SQLException se){
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
 
 public boolean updateOnboardReview(CreAction creAction) throws ServiceLocatorException {
         Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        
        boolean isInserted = false;
        String queryString = "UPDATE tblCreOnBoardComments SET ModifiedDate = ?, ModifiedBy = ?, Comments = ? WHERE Id = ? AND CreId = ?";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            
            
            preparedStatement.setTimestamp(1,DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(2,creAction.getCreatedBy());
            preparedStatement.setString(3,creAction.getOnboardComments());
            //preparedStatement.setString(4,creAction.getVpReviewStatus());
            preparedStatement.setInt(4,creAction.getOnboardReviewId());
            preparedStatement.setInt(5,creAction.getId());
            
            isInserted = preparedStatement.execute();
            
        }catch (SQLException se){
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
     public CreReviewVTO getOnboardReview(int onboardReviewId, int creId) throws ServiceLocatorException {
        
        
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         CreReviewVTO creReviewVTO = null;
        try{
            String queryString = "SELECT * FROM tblCreOnBoardComments WHERE CreId="+creId+" AND Id = "+onboardReviewId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            creReviewVTO = new CreReviewVTO();
            while(resultSet.next()){
                
                    
                    creReviewVTO.setOnboardReviewId(resultSet.getInt("Id"));
                    creReviewVTO.setOnboardLoginId(resultSet.getString("CreatedBy"));
                    creReviewVTO.setOnboardCreatedDate(resultSet.getString("CreatedDate"));
                    //creReviewVTO.setTechModifiedDate(resultSet.getString("ModifiedDate"));
                    //creReviewVTO.setTechModifiedLead(resultSet.getString("ModifiedBy"));
                   // creReviewVTO.setVpReviewStatus(resultSet.getString("Status"));
                    creReviewVTO.setCreId(resultSet.getInt("CreId"));
                    creReviewVTO.setOnboardComments(resultSet.getString("Comments"));
                  
            }
            
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
        return creReviewVTO;
    }
 public Collection getCreOnboardReviewCollection(int creId,int noOfRecords) throws ServiceLocatorException {
        Map creOnboardReviewMap = new TreeMap();
        int counter = 1;
        String tempCreatedDate ="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            String queryString = "SELECT * FROM tblCreOnBoardComments WHERE CreId="+creId+" ORDER BY CreatedDate DESC";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                if(counter <= noOfRecords){
                    CreReviewVTO creOnboardReviewVTO = new CreReviewVTO();
                    creOnboardReviewVTO.setOnboardReviewId(resultSet.getInt("Id"));
                    creOnboardReviewVTO.setOnboardLoginId(resultSet.getString("CreatedBy"));
                    creOnboardReviewVTO.setOnboardCreatedDate(resultSet.getString("CreatedDate"));
                    
                    
                    //creVpReviewVTO.setVpReviewStatus(resultSet.getString("Status"));
                    creOnboardReviewVTO.setCreId(resultSet.getInt("CreId"));
                    creOnboardReviewMap.put("stateVTO"+counter,creOnboardReviewVTO);
                    counter++;
                }
            }
            
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
        return creOnboardReviewMap.values();
    }
 
 public void updateCREOnboardLevel(int creConsultantId,String loginId) throws ServiceLocatorException {
          Connection connection = null;
          PreparedStatement preparedStatement = null;
          int i=0;
          try {
              connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement("UPDATE tblCreConsultentDetails SET Level=?,ModifiedBy=?,ModifiedDate=?  WHERE Id = ?");
             // preparedStatement.setString(1,status);
             // preparedStatement.setString(2,level);
              preparedStatement.setString(1,"Onboard");
              preparedStatement.setString(2,loginId);
              preparedStatement.setTimestamp(3,DateUtility.getInstance().getCurrentMySqlDateTime());
              preparedStatement.setInt(4,creConsultantId);
              
              i = preparedStatement.executeUpdate();
          }catch (Exception e){
              System.err.println("Exception is-->"+e.getMessage());
          }finally {
              try {
               if(preparedStatement!=null){
                  preparedStatement.close();
                  preparedStatement = null;
              }if(connection!=null){
                  connection.close();
                  connection = null;
              } 
              }catch(Exception sqle){
                  System.err.println("SQL Exception is-->"+sqle.getMessage());
              }
          }
          
      }
 
 /*
public List getConsultantListForExcel(CreAction creAction) throws ServiceLocatorException {
          Connection connection = null;
          PreparedStatement preparedStatement = null;
          ResultSet resultSet = null;
          int i=0;
          List creList = new LinkedList();
          Map creDetails =null;
          StringBuffer queryString = new StringBuffer();
          try {
              
              
               queryString.append("SELECT Id,ConsultentId,CONCAT(LName,'.',FName) AS ConsultentName,STATUS,Level FROM tblCreConsultentDetails WHERE STATUS!='Joined' ");
        
       
         
           if(creAction.getRecLocation()!=null && !"".equals(creAction.getRecLocation())) {
            queryString.append(" And recLocation = '"+creAction.getRecLocation()+"' ");
         }
            if(creAction.getStatus()!=null && !"".equals(creAction.getStatus())&&(creAction.getExamType()==null ||"".equals(creAction.getExamType()))) {
            queryString.append(" And STATUS = '"+creAction.getStatus()+"' ");
         }
              
            if(creAction.getStartDate()!=null && !"".equals(creAction.getStartDate().trim())) {
            queryString.append(" And CreatedDate >= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getStartDate())+"') ");
         }if(creAction.getEndDate()!=null && !"".equals(creAction.getEndDate().trim())) {
            queryString.append(" And CreatedDate <= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getEndDate())+"') ");
         }
         
          // System.out.println("ExamType-->"+creAction.getExamType());  
              
              connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement(queryString.toString());
              resultSet = preparedStatement.executeQuery();
              int j=1;
              if(creAction.getExamType()!=null && !"".equals(creAction.getExamType())) {
              while(resultSet.next()){
                   String consultentId = resultSet.getString("ConsultentId");     
                   if(consultentId==null||"".equals(consultentId))
                       consultentId = "";
                String consultentName = resultSet.getString("ConsultentName");
                if(consultentName==null||"".equals(consultentName))
                       consultentName = "";
                String status = resultSet.getString("STATUS");    
                if(status==null||"".equals(status))
                       status = "";
                String level = resultSet.getString("LEVEL");  
                if(level==null||"".equals(level))
                       level = "";
                
                if(creAction.getStatus()!=null&&creAction.getStatus().equals(getCreExamStatus(resultSet.getInt("Id"),creAction))){
                creDetails = new HashMap();
                creDetails.put("SNO", String.valueOf(j));
                creDetails.put("consultentId", consultentId);
                creDetails.put("consultentName", consultentName);
                creDetails.put("status", creAction.getStatus());
                creDetails.put("level", level);               
                creList.add(creDetails);
                j++;
                }
//                }else if(!"".equals(getCreExamStatus(resultSet.getInt("Id"),creAction))){
//                     creDetails = new HashMap();
//                creDetails.put("SNO", String.valueOf(j));
//                creDetails.put("consultentId", consultentId);
//                creDetails.put("consultentName", consultentName);
//                creDetails.put("status", creAction.getStatus());
//                creDetails.put("level", level);               
//                creList.add(creDetails);
//                j++;
//                }
                  
              }
              }else {
                   while(resultSet.next()){
                   String consultentId = resultSet.getString("ConsultentId");  
                    if(consultentId==null||"".equals(consultentId))
                       consultentId = "";
                String consultentName = resultSet.getString("ConsultentName");
                    if(consultentName==null||"".equals(consultentName))
                       consultentName = "";
                String status = resultSet.getString("STATUS");         
                 if(status==null||"".equals(status))
                       status = "";
                String level = resultSet.getString("LEVEL");  
                 if(level==null||"".equals(level))
                       level = "";
                creDetails = new HashMap();
                creDetails.put("SNO", String.valueOf(j));
                creDetails.put("consultentId", consultentId);
                creDetails.put("consultentName", consultentName);
                creDetails.put("status", status);
                creDetails.put("level", level);               
                creList.add(creDetails);
                j++;
                  
              }
              }
             // preparedStatement.setString(1,status);
             // preparedStatement.setString(2,level);
             
          }catch (Exception e){
              System.err.println("Exception is-->"+e.getMessage());
          }finally {
              try {
                  if(resultSet!=null){
                  resultSet.close();
                  resultSet = null;
              }
               if(preparedStatement!=null){
                  preparedStatement.close();
                  preparedStatement = null;
              }if(connection!=null){
                  connection.close();
                  connection = null;
              } 
              }catch(Exception sqle){
                  System.err.println("SQL Exception is-->"+sqle.getMessage());
              }
          }
          return creList;
      }
 
 
 public String getCreExamStatus(int creId,CreAction creAction) throws ServiceLocatorException {
        Map creExamReviewMap = new TreeMap();
        int counter = 1;
        String tempCreatedDate ="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String examStatus = "";
         StringBuffer queryString = new StringBuffer();
        try{
            //String queryString = "SELECT * FROM tblCreVPComments WHERE CreId="+creId+" AND  DeletedFlag != 1 ORDER BY CretedDate DESC";
            // queryString.append("SELECT tblEcertResult.ExamKeyId as examKeyId,tblEcertResult.EmpId AS empId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus FROM tblEcertResult where tblEcertResult.EmpId = 'MSSCRE"+creId+"' ");
            queryString.append("SELECT ExamStatus,DateSubmitted FROM tblEcertResult LEFT JOIN tblEcertKey ON tblEcertResult.ExamKeyId = tblEcertKey.ID WHERE tblEcertKey.ExamTypeId ="+creAction.getExamType()+"  AND tblEcertKey.EmpId="+creId+" AND DomainId =5 AND TopicId=25");
            //SELECT ExamStatus,DateSubmitted FROM tblEcertResult LEFT JOIN tblEcertKey ON tblEcertResult.ExamKeyId = tblEcertKey.ID WHERE tblEcertKey.ExamTypeId =9  AND tblEcertKey.EmpId=9812
            
//            if(creAction.getStartDate()!=null && !"".equals(creAction.getStartDate().trim())) {
//            queryString.append("And DateSubmitted >= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getStartDate())+"') ");
//         }if(creAction.getEndDate()!=null && !"".equals(creAction.getEndDate().trim())) {
//            queryString.append("And DateSubmitted <= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getEndDate())+"') ");
//         }
           // System.out.println("Cre Exam Review Query-->"+queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString.toString());
            while(resultSet.next()){
            examStatus = resultSet.getString("ExamStatus");
//                    CreReviewVTO creExamReviewVTO = new CreReviewVTO();
//                   creExamReviewVTO.setCreLoginId(resultSet.getString("empId"));
//                   creExamReviewVTO.setMarks(resultSet.getInt("Marks"));
//                   creExamReviewVTO.setTotalQuestions(resultSet.getInt("TotalQuestions"));
//                   creExamReviewVTO.setAttemptedQuestions(resultSet.getInt("AttemptedQuestions"));
//                   creExamReviewVTO.setDateSubmitted(resultSet.getString("DateSubmitted"));
//                   creExamReviewVTO.setExamStatus(resultSet.getString("ExamStatus"));
//                   creExamReviewVTO.setExamKeyId(String.valueOf(resultSet.getInt("examKeyId")));
//                    creExamReviewMap.put("stateVTO"+counter,creExamReviewVTO);
                    
            }
            
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
        return examStatus;
    }

    public String generateExcelForCreConsultantSearch(CreAction creAction) throws ServiceLocatorException{
        String filePath = "";
        StringBuffer sb = null;

        Connection connection = null;
       
        PreparedStatement preStmt = null;

     
        ResultSet resultSet = null;
        HashMap map = null;
        double totalAmount = 0.0;
        double floortotalsum = 0.0;
        String generatedPath = "";
        List finalList = new ArrayList();
         StringBuffer queryString = new StringBuffer();
        try {         
           finalList = getConsultantListForExcel(creAction);
            
          
          //  System.out.println(" finalList.size;-->"+ finalList.size());
           
          
            File file = new File(Properties.getProperty("Marketing.Investment.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath()+"/Details.xls");
            filePath = file.getAbsolutePath() + File.separator + "Details.xls";
            HSSFRow row1;
            HSSFWorkbook workbook = new HSSFWorkbook();
            System.out.println("filePath " + filePath);
            HSSFSheet worksheet = workbook.createSheet("Details");
            for (int i = 0; i < 5; i++) {
                worksheet.setColumnWidth(i, 10 * 256);

            }

            HSSFFont timesBoldFont = workbook.createFont();
            timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            timesBoldFont.setColor(HSSFColor.WHITE.index);

            timesBoldFont.setFontName("Calibri");
            HSSFCellStyle headercs = workbook.createCellStyle();
            headercs.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
            headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            headercs.setBorderTop((short) 1); // single line border
            headercs.setBorderBottom((short) 1); // single line border
            headercs.setFont(timesBoldFont);

            HSSFFont footerFont = workbook.createFont();
            footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            footerFont.setFontName("Calibri");





            // HSSFRow row1;
            // LogisticsDocBean logisticsDocBean = null;
            // index from 0,0... cell A1 is cell(0,0)

            // if(list.size()!=0){//
            //System.out.println("list size-->"+list.size());




            HSSFFont font4 = workbook.createFont();
            font4.setColor(HSSFColor.WHITE.index);
            font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font4.setFontHeightInPoints((short) 14);
            font4.setFontName("Calibri");

            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setFont(font4);


            HSSFFont font1 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
            font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font1.setFontHeightInPoints((short) 14);
            font1.setFontName("Calibri");
            HSSFCellStyle cs = workbook.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs.setBorderTop((short) 1); // single line border
            cs.setBorderBottom((short) 1); // single line border
            cs.setFont(font1);


            HSSFCellStyle cs1 = workbook.createCellStyle();
            cs1.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
            cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs1.setFont(font4);
            cs1.setBorderTop((short) 1); // single line border
            cs1.setBorderBottom((short) 1); // single line border



            HSSFCellStyle cs2 = workbook.createCellStyle();

            cs2.setFillForegroundColor(HSSFColor.WHITE.index);
            cs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs2.setBorderTop((short) 1); // single line border
            cs2.setBorderBottom((short) 1); // single line border
            cs2.setFont(font1);


            HSSFFont font3 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
            //  font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font3.setFontHeightInPoints((short) 14);
            font3.setFontName("Calibri");

            HSSFCellStyle cs3 = workbook.createCellStyle();
            cs3.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs3.setFont(font3);
            cs3.setBorderTop((short) 1); // single line border
            cs3.setBorderBottom((short) 1); // single line border



            HSSFCellStyle cs4 = workbook.createCellStyle();

            cs4.setFillForegroundColor(HSSFColor.WHITE.index);
            cs4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs4.setBorderTop((short) 1); // single line border
            cs4.setBorderBottom((short) 1); // single line border
            cs4.setFont(font3);



            //start	


            row1 = worksheet.createRow((short) 0);

            HSSFCell cellpo0 = row1.createCell((short) 0);
            // cellpo0.setCellValue("Purchase Order");
            HSSFCell cellpo1 = row1.createCell((short) 1);
            HSSFCell cellpo2 = row1.createCell((short) 2);
            // cellpo2.setCellValue("Created Date");
            HSSFCell cellpo3 = row1.createCell((short) 3);
            //cellpo3.setCellValue((date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());

            HSSFCell cellpo4 = row1.createCell((short) 4);
            HSSFCell cellpo5 = row1.createCell((short) 5);
            HSSFCell cellpo6 = row1.createCell((short) 6);
            HSSFCell cellpo7 = row1.createCell((short) 7);
            HSSFCell cellpo8 = row1.createCell((short) 8);
            HSSFCell cellpo9 = row1.createCell((short) 9);
            HSSFCell cellpo10 = row1.createCell((short) 10);
            HSSFCell cellpo11 = row1.createCell((short) 11);
            row1 = worksheet.createRow((short) 0);
            Cell cell[] = new Cell[5];
            for (int i = 0; i < 5; i++) {
                cell[i] = row1.createCell((short) i);
            }

            // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
            cell[0].setCellValue("Details");
            HSSFCellStyle cellStyleHead = workbook.createCellStyle();
            cellStyleHead.setFont(timesBoldFont);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cell[0].setCellStyle(cellStyle);


            worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:" + "E2"));

            //sno
            row1 = worksheet.createRow((short) 2);
            cell[0] = row1.createCell((short) 0);
            cell[0].setCellValue("SNo");
           
            // cellStyleHead.setFont(font4);
            cellStyleHead.setFont(timesBoldFont);
            //  cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //cellStyleHead.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
            // cellStyleHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);

            worksheet.addMergedRegion(CellRangeAddress.valueOf("A3:A4"));

            cell[0] = row1.createCell((short) 1);
            cell[0].setCellValue("ConsultentId");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("B3:B4"));

            cell[0] = row1.createCell((short) 2);
            cell[0].setCellValue("Consultent Name");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("C3:C4"));

            cell[0] = row1.createCell((short) 3);
            cell[0].setCellValue("Status");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("D3:D4"));
           cell[0] = row1.createCell((short) 4);
            cell[0].setCellValue("Level");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));
//            cell[0] = row1.createCell((short) 4);
//            cell[0].setCellValue("Phone");
//            cellStyleHead.setFont(timesBoldFont);
//            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//            //  cell[0].setCellStyle(cellStyleHead);
//            cell[0].setCellStyle(cs1);
//            worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));

            int count = 4;

            int len = finalList.size();
            if (finalList.size() > 0) {
                
                for (int k = 0; k < finalList.size(); k++) {

                    java.util.Map subList = (java.util.Map) finalList.get(k);
                    row1 = worksheet.createRow((short) count++);
                    for (int m = 0; m < 5; m++) {
                        cell[m] = row1.createCell((short) m);
                    }
                    //System.out.println("subList-->"+subList);
                    cell[0].setCellValue((String) subList.get("SNO"));
                   // System.out.println("subList.get(\"SNO\")-->"+subList.get("SNO"));
                    cell[1].setCellValue((String) subList.get("consultentId").toString());
                  //   System.out.println("subList.get(\"SNO\")-->"+subList.get("consultentId"));
                    cell[2].setCellValue((String) subList.get("consultentName").toString());
                   // System.out.println("subList.get(\"SNO\")-->"+subList.get("consultentName"));
                    cell[3].setCellValue((String) subList.get("status").toString());
                    // System.out.println("subList.get(\"SNO\")-->"+subList.get("status"));
                   cell[4].setCellValue((String) subList.get("level").toString());
                   // System.out.println("subList.get(\"SNO\")-->"+subList.get("level"));
                   // System.out.println("in iffff-->");

                    for (int h = 0; h < 5; h++) {
                        if (count % 2 == 0) {
                            cell[h].setCellStyle(cs1);
                        } else {
                            cell[h].setCellStyle(cs);
                        }

                    }
                }
                // 
             
               



            }
 for (int i = 1; i < 5; i++) {
                worksheet.setColumnWidth(i, 40 * 256);

            }
 workbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
        } catch (FileNotFoundException fne) {

            fne.printStackTrace();
        } catch (IOException ioe) {

            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();

        } 

        return filePath;


    }
	*/
 public List getConsultantListForExcel(CreAction creAction) throws ServiceLocatorException {
          Connection connection = null;
          PreparedStatement preparedStatement = null;
          ResultSet resultSet = null;
          int i=0;
          List creList = new LinkedList();
          Map creDetails =null;
          StringBuffer queryString = new StringBuffer();
          try {
              
              
               queryString.append("SELECT Id,ConsultentId,CONCAT(LName,'.',FName) AS ConsultentName,STATUS,Level FROM tblCreConsultentDetails WHERE STATUS!='Joined' ");
        
       
         
           if(creAction.getRecLocation()!=null && !"".equals(creAction.getRecLocation())) {
            queryString.append(" And recLocation = '"+creAction.getRecLocation()+"' ");
         }
//            if(creAction.getStatus()!=null && !"".equals(creAction.getStatus())&&(creAction.getExamType()==null ||"".equals(creAction.getExamType()))) {
//            queryString.append(" And STATUS = '"+creAction.getStatus()+"' ");
//         }
              
//            if(creAction.getStartDate()!=null && !"".equals(creAction.getStartDate().trim())) {
//                
//                
//                //Id IN (SELECT EMPID FROM tblEcertKey WHERE DATE(CreatedDate) >= DATE('2016-04-11'))
//                
//                queryString.append(" AND Id IN (SELECT EMPID FROM tblEcertKey WHERE DATE(CreatedDate) >= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getStartDate())+"') AND ExamTypeId>0) ");
//           // queryString.append(" And CreatedDate >= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getStartDate())+"') ");
//         }if(creAction.getEndDate()!=null && !"".equals(creAction.getEndDate().trim())) {
//          //  queryString.append(" And CreatedDate <= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getEndDate())+"') ");
//               queryString.append(" AND Id IN (SELECT EMPID FROM tblEcertKey WHERE DATE(CreatedDate) <= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getEndDate())+"') AND ExamTypeId>0 ) ");
//         }
         if(creAction.getInterviewAtConsultantReport()!=null && !"".equals(creAction.getInterviewAtConsultantReport()))
	{
             queryString.append(" And tblCreConsultentDetails.AttendedAt = "+creAction.getInterviewAtConsultantReport()) ;
	}
         
         
         String subQuery="SELECT EMPID FROM tblEcertKey WHERE ExamTypeId>0 ";
         boolean isDateExist=false;
         if(creAction.getStartDate()!=null && !"".equals(creAction.getStartDate().trim())) {
             isDateExist=true;
             subQuery = subQuery +" AND DATE(CreatedDate) >= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getStartDate())+"') ";
         }if(creAction.getEndDate()!=null && !"".equals(creAction.getEndDate().trim())) {
              isDateExist=true;
             subQuery = subQuery +" AND DATE(CreatedDate) <= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getEndDate())+"') ";
         }
         if(isDateExist){
             queryString.append(" AND Id IN ("+subQuery+") ");
         }
         
//             if(creAction.getStartDate()!=null && !"".equals(creAction.getStartDate().trim())) {
//                
//                
//                //Id IN (SELECT EMPID FROM tblEcertKey WHERE DATE(CreatedDate) >= DATE('2016-04-11'))
//                
//                queryString.append(" AND Id IN (SELECT EMPID FROM tblEcertKey WHERE DATE(CreatedDate) >= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getStartDate())+"') AND ExamTypeId>0) ");
//           // queryString.append(" And CreatedDate >= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getStartDate())+"') ");
//         }if(creAction.getEndDate()!=null && !"".equals(creAction.getEndDate().trim())) {
//          //  queryString.append(" And CreatedDate <= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getEndDate())+"') ");
//               queryString.append(" AND Id IN (SELECT EMPID FROM tblEcertKey WHERE DATE(CreatedDate) <= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getEndDate())+"') AND ExamTypeId>0 ) ");
//         }
         
          queryString.append(" ORDER BY Id ");
           //System.out.println("queryString-->"+queryString.toString());  
              
              connection = ConnectionProvider.getInstance().getConnection();
              preparedStatement = connection.prepareStatement(queryString.toString());
              resultSet = preparedStatement.executeQuery();
              int j=1;
              String creId="";
              if(creAction.getExamType()!=null && !"".equals(creAction.getExamType())) {
              while(resultSet.next()){                  
                   String consultentId = resultSet.getString("ConsultentId");     
                   if(consultentId==null||"".equals(consultentId))
                       consultentId = "";
                String consultentName = resultSet.getString("ConsultentName");
                if(consultentName==null||"".equals(consultentName))
                       consultentName = "";
                String status = resultSet.getString("STATUS");    
                if(status==null||"".equals(status))
                       status = "";
                String level = resultSet.getString("LEVEL");  
                if(level==null||"".equals(level))
                       level = "";
                 String examStatus = getCreExamStatus(resultSet.getInt("Id"),creAction.getExamType());
               // if(creAction.getStatus()!=null&&!"".equals(creAction.getStatus())&&creAction.getStatus().equals(examStatus)){
                   
                creDetails = new HashMap();
                creDetails.put("SNO", String.valueOf(j));
                creDetails.put("consultentId", consultentId);
                creDetails.put("consultentName", consultentName);
               // System.out.println("stauts--->"+getCreExamStatus(resultSet.getInt("Id"),creAction.getExamType()));
               
                
                creDetails.put("status", examStatus);
                creDetails.put("level", level);
              //  System.out.println("examStatus-->"+examStatus);
              //  System.out.println("Status-->"+creAction.getStatus());
                creDetails.put(creAction.getExamType(), examStatus);    
                 if(creAction.getStatus()!=null && !"".equals(creAction.getStatus())){
                       if(examStatus.equals(creAction.getStatus())) 
                            creList.add(creDetails);
                    }else{
                     creList.add(creDetails);
                 }
               
                j++;
               // }                  
              }
              
              } else {
                  //Map subtopicsMap = new TreeMap();
                 Map subtopicsMap=DataSourceDataProvider.getInstance().getExamTypeMap();
                //  System.out.println("----in else-----");
                  //  List examsList = new ArrayList(); 

                   // examsList.add(subtopicsMap);                     
                   while(resultSet.next()){
                   String consultentId = resultSet.getString("ConsultentId");  
                    if(consultentId==null||"".equals(consultentId))
                       consultentId = "";
                String consultentName = resultSet.getString("ConsultentName");
                    if(consultentName==null||"".equals(consultentName))
                       consultentName = "";
                String status = resultSet.getString("STATUS");         
                 if(status==null||"".equals(status))
                       status = "";
                String level = resultSet.getString("LEVEL");  
                 if(level==null||"".equals(level))
                       level = "";
                creDetails = new HashMap();
                creDetails.put("SNO", String.valueOf(j));
                creDetails.put("consultentId", consultentId);
                creDetails.put("consultentName", consultentName);
                creDetails.put("status", status);
                creDetails.put("level", level);
                
                if (subtopicsMap.size() > 0) {
             //   System.out.println("--subtopicsMap--");
                Iterator tempIterator = subtopicsMap.entrySet().iterator();
                    while(tempIterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) tempIterator.next();
                        int   key = (Integer)entry.getKey();
                        String   value = entry.getValue().toString();
                       // System.out.println("key--.............>"+key+"value-->"+getCreExamStatus(resultSet.getInt("Id"),String.valueOf(key))); 
                       // creDetails.put(key,getCreExamStatus(resultSet.getInt("Id"),String.valueOf(key)));
                         creDetails.put(value,getCreExamStatus(resultSet.getInt("Id"),String.valueOf(key)));
                    }
                                       //    System.out.println("creDetails-->"+creDetails.get(9));   

                }
                 
                                               
                creList.add(creDetails);
                j++;
              }
              }        
          }catch (Exception e){
              System.err.println("Exception is-->"+e.getMessage());
          }finally {
              try {
                  if(resultSet!=null){
                  resultSet.close();
                  resultSet = null;
              }
               if(preparedStatement!=null){
                  preparedStatement.close();
                  preparedStatement = null;
              }if(connection!=null){
                  connection.close();
                  connection = null;
              } 
              }catch(Exception sqle){
                  System.err.println("SQL Exception is-->"+sqle.getMessage());
              }
          }
          return creList;
      }
 
 public String getCreExamStatus(int creId,String examType) throws ServiceLocatorException {
        Map creExamReviewMap = new TreeMap();
        int counter = 1;
        String tempCreatedDate ="";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String examStatus = "";
         StringBuffer queryString = new StringBuffer();
        try{
            //String queryString = "SELECT * FROM tblCreVPComments WHERE CreId="+creId+" AND  DeletedFlag != 1 ORDER BY CretedDate DESC";
            // queryString.append("SELECT tblEcertResult.ExamKeyId as examKeyId,tblEcertResult.EmpId AS empId,Marks,TotalQuestions,AttemptedQuestions,DateSubmitted,ExamStatus FROM tblEcertResult where tblEcertResult.EmpId = 'MSSCRE"+creId+"' ");
            queryString.append("SELECT ExamStatus,DateSubmitted FROM tblEcertResult LEFT JOIN tblEcertKey ON tblEcertResult.ExamKeyId = tblEcertKey.ID WHERE tblEcertKey.ExamTypeId ='"+examType+"'  AND tblEcertKey.EmpId="+creId+" AND DomainId =5 AND TopicId=25 ORDER BY DateSubmitted DESC LIMIT 1");
            //SELECT ExamStatus,DateSubmitted FROM tblEcertResult LEFT JOIN tblEcertKey ON tblEcertResult.ExamKeyId = tblEcertKey.ID WHERE tblEcertKey.ExamTypeId =9  AND tblEcertKey.EmpId=9812
            
//            if(creAction.getStartDate()!=null && !"".equals(creAction.getStartDate().trim())) {
//            queryString.append("And DateSubmitted >= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getStartDate())+"') ");
//         }if(creAction.getEndDate()!=null && !"".equals(creAction.getEndDate().trim())) {
//            queryString.append("And DateSubmitted <= DATE('"+DateUtility.getInstance().convertStringToMySQLDate(creAction.getEndDate())+"') ");
//         }
           // System.out.println("Cre Exam Review Query-->"+queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString.toString());
            if(resultSet.next()){
            examStatus = resultSet.getString("ExamStatus");
//                    CreReviewVTO creExamReviewVTO = new CreReviewVTO();
//                   creExamReviewVTO.setCreLoginId(resultSet.getString("empId"));
//                   creExamReviewVTO.setMarks(resultSet.getInt("Marks"));
//                   creExamReviewVTO.setTotalQuestions(resultSet.getInt("TotalQuestions"));
//                   creExamReviewVTO.setAttemptedQuestions(resultSet.getInt("AttemptedQuestions"));
//                   creExamReviewVTO.setDateSubmitted(resultSet.getString("DateSubmitted"));
//                   creExamReviewVTO.setExamStatus(resultSet.getString("ExamStatus"));
//                   creExamReviewVTO.setExamKeyId(String.valueOf(resultSet.getInt("examKeyId")));
//                    creExamReviewMap.put("stateVTO"+counter,creExamReviewVTO);
                    
            }
            
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
        return examStatus;
    }

	
	public String generateExcelForCreConsultantSearchV2(CreAction creAction) throws ServiceLocatorException{
        String filePath = "";
        StringBuffer sb = null;

        Connection connection = null;
        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null;

        /**
         * The queryString is useful to get queryString result to the particular
         * jsp page
         */
      
        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        HashMap map = null;
        double totalAmount = 0.0;
        double floortotalsum = 0.0;
        String generatedPath = "";
        List finalList = new ArrayList();
         StringBuffer queryString = new StringBuffer();
        try {         
           finalList = getConsultantListForExcel(creAction);
            
          
            //System.out.println(" finalList.size;-->"+ finalList.size());
           // System.out.println(" finalList.size;-->"+ creAction.getExamType());
           
          
            File file = new File(Properties.getProperty("Marketing.Investment.Path"));

            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath()+"/Details.xls");
            filePath = file.getAbsolutePath() + File.separator + "Details.xls";
            HSSFRow row1;
            HSSFWorkbook workbook = new HSSFWorkbook();
            //System.out.println("filePath " + filePath);
            HSSFSheet worksheet = workbook.createSheet("Details");
            for (int i = 0; i < 5; i++) {
                worksheet.setColumnWidth(i, 10 * 256);

            }

            HSSFFont timesBoldFont = workbook.createFont();
            timesBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            timesBoldFont.setColor(HSSFColor.WHITE.index);

            timesBoldFont.setFontName("Calibri");
            HSSFCellStyle headercs = workbook.createCellStyle();
            headercs.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
            headercs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            headercs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            headercs.setBorderTop((short) 1); // single line border
            headercs.setBorderBottom((short) 1); // single line border
            headercs.setFont(timesBoldFont);

            HSSFFont footerFont = workbook.createFont();
            footerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            footerFont.setFontName("Calibri");





            // HSSFRow row1;
            // LogisticsDocBean logisticsDocBean = null;
            // index from 0,0... cell A1 is cell(0,0)

            // if(list.size()!=0){//
            //System.out.println("list size-->"+list.size());




            HSSFFont font4 = workbook.createFont();
            font4.setColor(HSSFColor.WHITE.index);
            font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font4.setFontHeightInPoints((short) 14);
            font4.setFontName("Calibri");

            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setFont(font4);


            HSSFFont font1 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
            font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font1.setFontHeightInPoints((short) 14);
            font1.setFontName("Calibri");
            HSSFCellStyle cs = workbook.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs.setBorderTop((short) 1); // single line border
            cs.setBorderBottom((short) 1); // single line border
            cs.setFont(font1);


            HSSFCellStyle cs1 = workbook.createCellStyle();
            cs1.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
            cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs1.setFont(font4);
            cs1.setBorderTop((short) 1); // single line border
            cs1.setBorderBottom((short) 1); // single line border



            HSSFCellStyle cs2 = workbook.createCellStyle();

            cs2.setFillForegroundColor(HSSFColor.WHITE.index);
            cs2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs2.setBorderTop((short) 1); // single line border
            cs2.setBorderBottom((short) 1); // single line border
            cs2.setFont(font1);


            HSSFFont font3 = workbook.createFont();
            //font1.setColor(HSSFColor.WHITE.index);
            //  font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font3.setFontHeightInPoints((short) 14);
            font3.setFontName("Calibri");

            HSSFCellStyle cs3 = workbook.createCellStyle();
            cs3.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cs3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs3.setFont(font3);
            cs3.setBorderTop((short) 1); // single line border
            cs3.setBorderBottom((short) 1); // single line border



            HSSFCellStyle cs4 = workbook.createCellStyle();

            cs4.setFillForegroundColor(HSSFColor.WHITE.index);
            cs4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cs4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs4.setBorderTop((short) 1); // single line border
            cs4.setBorderBottom((short) 1); // single line border
            cs4.setFont(font3);



            //start	


//            row1 = worksheet.createRow((short) 0);
//
//            HSSFCell cellpo0 = row1.createCell((short) 0);
//            // cellpo0.setCellValue("Purchase Order");
//            HSSFCell cellpo1 = row1.createCell((short) 1);
//            HSSFCell cellpo2 = row1.createCell((short) 2);
//            // cellpo2.setCellValue("Created Date");
//            HSSFCell cellpo3 = row1.createCell((short) 3);
//            //cellpo3.setCellValue((date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
//
//            HSSFCell cellpo4 = row1.createCell((short) 4);
//            HSSFCell cellpo5 = row1.createCell((short) 5);
//            HSSFCell cellpo6 = row1.createCell((short) 6);
//            HSSFCell cellpo7 = row1.createCell((short) 7);
//            HSSFCell cellpo8 = row1.createCell((short) 8);
//            HSSFCell cellpo9 = row1.createCell((short) 9);
//            HSSFCell cellpo10 = row1.createCell((short) 10);
//            HSSFCell cellpo11 = row1.createCell((short) 11);
            
            row1 = worksheet.createRow((short) 0);
            Map subtopicsMap = new HashMap();
            subtopicsMap=DataSourceDataProvider.getInstance().getExamTypeMap();
           ArrayList examList = new ArrayList();   
            Set examSet = new HashSet();   
            
        //    System.out.println("Map Size-->"+subtopicsMap.size
                   examSet=subtopicsMap.keySet(); 
                   
                   Iterator iterator = examSet.iterator(); 
      
   // check values
                while (iterator.hasNext()){
               //  System.out.println("Value: "+iterator.next() + " ");  
                  examList.add ((String) subtopicsMap.get(iterator.next()));
                } 
                   
//               for (int i= 0; i <examSet.size(); i++) {                   
//                    System.out.println("in iff");
//                  //  System.out.println("subtopicsMap.get(i)-->"+i+""+subtopicsMap.get(i));
//                //  examList.add ((String) subtopicsMap.get(examSet.));
//                   
//               // System.out.println("header-->" + firstMap.get("Query"));
//
//            }
               
            int examListLen=examList.size();
            Cell cell[];
             int len1;
            if(creAction.getExamType()==null || "".equalsIgnoreCase(creAction.getExamType())){
             cell= new Cell[subtopicsMap.size()+5];
            for (int i = 0; i < subtopicsMap.size()+5; i++) {
                cell[i] = row1.createCell((short) i);
            }
                      len1 =subtopicsMap.size();

            }
            else{
              cell= new Cell[6];
            for (int i = 0; i < 6; i++) {
                cell[i] = row1.createCell((short) i);
            }
            len1=6;
            }

            // cell.setCellValue("Logistics Document :-Created Date : "+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
            cell[0].setCellValue("Details");
            HSSFCellStyle cellStyleHead = workbook.createCellStyle();
            cellStyleHead.setFont(timesBoldFont);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cell[0].setCellStyle(cellStyle);
               ///////
            String mergingInterval = "";
                    switch (len1+2) {
                    case 1:
                        mergingInterval = "D";
                        break;
                    case 2:
                        mergingInterval = "E";
                        break;
                    case 3:
                        mergingInterval = "F";
                        break;
                    case 4:
                        mergingInterval = "G";
                        break;
                    case 5:
                        mergingInterval = "H";
                        break;
                    case 6:
                        mergingInterval = "I";
                        break;
                    case 7:
                        mergingInterval = "J";
                        break;
                    case 8:
                        mergingInterval = "K";
                        break;
                    case 9:
                        mergingInterval = "L";
                        break;
                    case 10:
                        mergingInterval = "M";
                        break;
                    case 11:
                        mergingInterval = "N";
                        break;
                    case 12:
                        mergingInterval = "O";
                        break;
                    case 13:
                        mergingInterval = "P";
                        break;
                    case 14:
                        mergingInterval = "Q";
                        break;
                    case 15:
                        mergingInterval = "R";
                        break;
                    case 16:
                        mergingInterval = "S";
                        break;
                    case 17:
                        mergingInterval = "T";
                        break;
                    case 18:
                        mergingInterval = "U";
                        break;
                    case 19:
                        mergingInterval = "V";
                        break;
                }

             worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:" + mergingInterval + "2"));
            /////

          //  worksheet.addMergedRegion(CellRangeAddress.valueOf("A1:" + "E2"));

            //sno
            row1 = worksheet.createRow((short) 2);
            cell[0] = row1.createCell((short) 0);
            cell[0].setCellValue("SNo");
           
            // cellStyleHead.setFont(font4);
            cellStyleHead.setFont(timesBoldFont);
            //  cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //cellStyleHead.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
            // cellStyleHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);

            worksheet.addMergedRegion(CellRangeAddress.valueOf("A3:A4"));

            cell[0] = row1.createCell((short) 1);
            cell[0].setCellValue("ConsultentId");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("B3:B4"));

            cell[0] = row1.createCell((short) 2);
            cell[0].setCellValue("Consultent Name");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("C3:C4"));

            cell[0] = row1.createCell((short) 3);
            cell[0].setCellValue("Status");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("D3:D4"));
           cell[0] = row1.createCell((short) 4);
            cell[0].setCellValue("Level");
            cellStyleHead.setFont(timesBoldFont);
            cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // cell[0].setCellStyle(cellStyleHead);
            cell[0].setCellStyle(cs1);
            worksheet.addMergedRegion(CellRangeAddress.valueOf("E3:E4"));
           // System.out.println("examList[0]-->"+examList[0]);
             if(creAction.getExamType()==null || "".equalsIgnoreCase(creAction.getExamType())){
                 
                 
                    Iterator entries = subtopicsMap.entrySet().iterator();
                       int k=5;
while (entries.hasNext()) {
    Map.Entry entry = (Map.Entry) entries.next();
    Integer key = (Integer)entry.getKey();
    String value = (String)entry.getValue();
     cell[0] = row1.createCell((short) k);
                cell[0].setCellValue(value);
                cell[0].setCellStyle(cs1);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //   cell[0].setCellStyle(cellStyleHead);
                if(k==5)
                worksheet.addMergedRegion(CellRangeAddress.valueOf("F3:F4")); 
                else if(k==6)
                    worksheet.addMergedRegion(CellRangeAddress.valueOf("G3:G4"));
                    else if(k==7)
                        worksheet.addMergedRegion(CellRangeAddress.valueOf("H3:H4"));
                        else if(k==8)
                            worksheet.addMergedRegion(CellRangeAddress.valueOf("I3:I4"));
                            else if(k==9)
                                worksheet.addMergedRegion(CellRangeAddress.valueOf("J3:J4"));
                                else if(k==10)
                                    worksheet.addMergedRegion(CellRangeAddress.valueOf("K3:K4"));
                                    else if(k==11)
                                        worksheet.addMergedRegion(CellRangeAddress.valueOf("L3:L4"));
                                        else if(k==12)
                                            worksheet.addMergedRegion(CellRangeAddress.valueOf("M3:M4"));
                                            else if(k==13)
                                                worksheet.addMergedRegion(CellRangeAddress.valueOf("N3:N4"));
                 else if(k==14)
                     worksheet.addMergedRegion(CellRangeAddress.valueOf("O3:O4"));
                      else if(k==15)
                          worksheet.addMergedRegion(CellRangeAddress.valueOf("P3:P4"));
                 else if(k==16)
                          worksheet.addMergedRegion(CellRangeAddress.valueOf("Q3:Q4"));
                 else if(k==17)
                          worksheet.addMergedRegion(CellRangeAddress.valueOf("R3:R4"));
                 else if(k==18)
                          worksheet.addMergedRegion(CellRangeAddress.valueOf("S3:S4"));
                 else if(k==19)
                          worksheet.addMergedRegion(CellRangeAddress.valueOf("T3:T4"));
                 else if(k==20)
                          worksheet.addMergedRegion(CellRangeAddress.valueOf("U3:U4"));
    k++;
  //  System.out.println("Key = " + key + ", Value = " + value);
}
                 
                 //System.out.println("examList.get(0).toString()..."+examList.get(8).toString());
                // System.out.println("examList.get(0).toString()..."+examList.get(9).toString());
//            if(examListLen>0)
//            {
//          
//                System.out.println("");
//                cell[0] = row1.createCell((short) 5);
//                cell[0].setCellValue(examList.get(0).toString());
//                cell[0].setCellStyle(cs1);
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //   cell[0].setCellStyle(cellStyleHead);                
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("F3:F4"));   
//           // }
//            }
//            if(examListLen>1) {
//                cell[0] = row1.createCell((short) 6);
//                cell[0].setCellValue(examList.get(1).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("G3:G4"));
//            }
//             if(examListLen>2) {
//                cell[0] = row1.createCell((short) 7);
//                cell[0].setCellValue(examList.get(2).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("H3:H4"));
//            }
//            if(examListLen>3) {
//                cell[0] = row1.createCell((short) 8);
//                cell[0].setCellValue(examList.get(3).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("I3:I4"));
//            }
//             if(examListLen>4) {
//                cell[0] = row1.createCell((short) 9);
//                cell[0].setCellValue(examList.get(4).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("J3:J4"));
//            }
//             if(examListLen>5) {
//                cell[0] = row1.createCell((short) 10);
//                cell[0].setCellValue(examList.get(5).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("K3:K4"));
//            }
//             if(examListLen>6) {
//                cell[0] = row1.createCell((short) 11);
//                cell[0].setCellValue(examList.get(6).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("L3:L4"));
//            }
//            if(examListLen>7) {
//                cell[0] = row1.createCell((short) 12);
//                cell[0].setCellValue(examList.get(7).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("M3:M4"));
//            }
//         //   System.out.println("examList.get(8).toString()-->"+examList.get(8).toString());
//            if(examListLen>8) {
//                cell[0] = row1.createCell((short) 13);
//                cell[0].setCellValue(examList.get(8).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("N3:N4"));
//            }
//            
//         //   System.out.println("examList.get(9).toString()-->"+examList.get(9).toString());
//            if(examListLen>9)  {
//                cell[0] = row1.createCell((short) 14);
//                cell[0].setCellValue(examList.get(9).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("O3:O4"));
//            }
//           if(examListLen>10) {
//                cell[0] = row1.createCell((short) 15);
//                cell[0].setCellValue(examList.get(10).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("P3:P4"));
//            }
//            if(examListLen>11) {
//                cell[0] = row1.createCell((short) 16);
//                cell[0].setCellValue(examList.get(11).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("Q3:Q4"));
//            }
//             if(examListLen>12) {
//                cell[0] = row1.createCell((short) 17);
//                cell[0].setCellValue(examList.get(12).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("R3:R4"));
//            }
//             if(examListLen>13) {
//                cell[0] = row1.createCell((short) 18);
//                cell[0].setCellValue(examList.get(13).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("S3:S4"));
//            }
//            
//            if(examListLen>14) {
//                cell[0] = row1.createCell((short) 19);
//                cell[0].setCellValue(examList.get(14).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("T3:T4"));
//            }
//            if(examListLen>15) {
//                cell[0] = row1.createCell((short) 20);
//                cell[0].setCellValue(examList.get(15).toString());
//                cellStyleHead.setFont(timesBoldFont);
//                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//                //  cell[0].setCellStyle(cellStyleHead);
//                cell[0].setCellStyle(cs1);
//                worksheet.addMergedRegion(CellRangeAddress.valueOf("U3:U4"));
//            }
             }else{ 
                String name=DataSourceDataProvider.getInstance().getExamName(creAction.getExamType());
              //  System.out.println("name-->"+name);
                cell[0] = row1.createCell((short) 5);
                cell[0].setCellValue(name);
                cell[0].setCellStyle(cs1);
                cellStyleHead.setFont(timesBoldFont);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                //   cell[0].setCellStyle(cellStyleHead);                
                worksheet.addMergedRegion(CellRangeAddress.valueOf("F3:F4"));
             }
            
           

            int count = 4;

            int len = finalList.size();
            if (finalList.size() > 0) {
                
                for (int k = 0; k < finalList.size(); k++) {

                    java.util.Map subList = (java.util.Map) finalList.get(k);
                    {
                       // System.out.println("subList...."+subList.size());
                    row1 = worksheet.createRow((short) count++);
                    for (int m = 0; m < subList.size(); m++) {
                        cell[m] = row1.createCell((short) m);
                    }
                    //System.out.println("subList-->"+subList);
                    cell[0].setCellValue((String) subList.get("SNO"));
                   // System.out.println("subList.get(\"SNO\")-->"+subList.get("SNO"));
                    cell[1].setCellValue((String) subList.get("consultentId"));
                  //   System.out.println("subList.get(\"SNO\")-->"+subList.get("consultentId"));
                    cell[2].setCellValue((String) subList.get("consultentName"));
                   // System.out.println("subList.get(\"SNO\")-->"+subList.get("consultentName"));
                    cell[3].setCellValue((String) subList.get("status"));
                    // System.out.println("subList.get(\"SNO\")-->"+subList.get("status"));
                   cell[4].setCellValue((String) subList.get("level"));
                   
                   //cell[5].setCellValue((String) subList.get("level").toString());
//                    System.out.println("subList-->"+subList.get(1).toString());
//                    System.out.println("subList-->"+subList.get(1).toString());
//                    System.out.println("subList-->"+subList.get(1).toString());
                   if(creAction.getExamType()==null || "".equalsIgnoreCase(creAction.getExamType())){
                       
                       
                       Iterator entries = subtopicsMap.entrySet().iterator();
                       int j=5;
while (entries.hasNext()) {
    Map.Entry entry = (Map.Entry) entries.next();
    Integer key = (Integer)entry.getKey();
    String value = (String)entry.getValue();
    cell[j].setCellValue(subList.get(value).toString());
    
    j++;
  //  System.out.println("Key = " + key + ", Value = " + value);
}
                       
                       
                       
                       
                       
                       
                       
                       
//                   for(int j = 5, q = 1; j < subList.size(); j++, q++){
//                 //  for(int j=5;int k=1;j<subList.size();j++){
//                       System.out.println("here-->");
//                     //  String h=""+q;
//                      System.out.println("subList.get(q).toString()-->"+"j-->"+j+"q-->"+q+" "+subList.get(q).toString());
////                       if(!"".equalsIgnoreCase(subList.get(h).toString()))
////                       {
//                         //  System.out.println("subList.get(h).toString--->"+q+"   "+j+"h  "+h+" "+"  "+subList.get(h).toString());
//                           if(subList.containsKey(q))
//                           {
//                           cell[j].setCellValue(subList.get(q).toString());
//                           }
//                           else
//                           {
//                               cell[j].setCellValue("");
//                           }
////                       }
////                           else
////                       {
////                           cell[j].setCellValue("--");
////                       }
//                   } 
                   
                   
                   }                                                    
                   else{
                      // System.out.println("---in else---");
                       String n=DataSourceDataProvider.getInstance().getExamName(creAction.getExamType());     
                     //  System.out.println("n value-->"+n);
                     //  System.out.println("(String) subList.get(creAction.getExamType()).toString()-->"+(String) subList.get(creAction.getExamType()));
                       cell[5].setCellValue((String) subList.get(creAction.getExamType()));
                       
                      // System.out.println("suceesssss");
                  // cell[5].setCellValue(creAction.getExamType());
                   }
//                   cell[6].setCellValue((String) subList.get("Exam2").toString());
//                   cell[7].setCellValue((String) subList.get("Exam3").toString());
//                   
                   
                   // System.out.println("subList.get(\"SNO\")-->"+subList.get("level"));
                   // System.out.println("in iffff-->");

         
            
                    for (int footer = 0; footer < subList.size(); footer++) {
                        if (count % 2 == 0) {
                            cell[footer].setCellStyle(cs1);
                        } else {
                            cell[footer].setCellStyle(cs);
                        }

                    }
              }
            } 
            } 
            
 for (int i = 1; i < 15; i++) {
                worksheet.setColumnWidth(i, 40 * 256);

            }
 workbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
        }
                catch (FileNotFoundException fne) {

            fne.printStackTrace();
        } catch (IOException ioe) {

            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();

        } 

        return filePath;


    }
	
}
