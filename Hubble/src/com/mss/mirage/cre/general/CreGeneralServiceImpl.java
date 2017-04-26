/*
 * @(#)GeneralServiceImpl.java 1.0 Nov 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */

package com.mss.mirage.cre.general;

import com.mss.mirage.cre.CreAction;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.Iterator;
import java.util.Random;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import com.mss.mirage.util.Properties;

/**
 *
 * @author RajaReddy.Andra <a href="mailto:randra@miraclesoft.com">randra@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 *
 * @see com.mss.mirage.util.ServiceLocatorException
 * @see com.mss.mirage.util.ConnectionProvider
 * @see com.mss.mirage.util.HibernateServiceLocator
 * @see com.mss.mirage.util.PasswordUtility
 * @see com.mss.mirage.util.MailManager
 *
 */
public class CreGeneralServiceImpl implements CreGeneralService{
    /** Creates a new instance of GeneralServiceIml */
    public CreGeneralServiceImpl() {
    }
  /*
    public boolean creUserCheckExist(CreRegistrationAction creAction) throws ServiceLocatorException{
        boolean isUserExist = false;
        
        Connection connection = null;
        PreparedStatement prepstatement = null;
        ResultSet resultSet = null;
        
       try{
           connection = ConnectionProvider.getInstance().getConnection();
            prepstatement = connection.prepareStatement("SELECT Email1,CellPhoneNo FROM tblCreConsultentDetails WHERE Email1=? OR CellPhoneNo=?");
            prepstatement.setString(1,creAction.getPersonalEmail());
            prepstatement.setString(2,creAction.getCellPhoneNo());
            
             resultSet = prepstatement.executeQuery();
            if(resultSet.next()){
                isUserExist = true;
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
        return isUserExist;
        
    }
    */
    public boolean creUserCheckExist(CreRegistrationAction creAction) throws ServiceLocatorException{
        boolean isUserExist = false;
        
        Connection connection = null;
        PreparedStatement prepstatement = null;
        ResultSet resultSet = null;
        /*@param primaryAction used to store actions*/
       try{
           connection = ConnectionProvider.getInstance().getConnection();
           prepstatement = connection.prepareStatement("SELECT Email1,CellPhoneNo FROM tblCreConsultentDetails WHERE Email1=? OR CellPhoneNo=?");
            //prepstatement = connection.prepareStatement("SELECT Email1 FROM tblCreConsultentDetails WHERE Email1=? ");
            prepstatement.setString(1,creAction.getPersonalEmail());
            prepstatement.setString(2,creAction.getCellPhoneNo());
            
             resultSet = prepstatement.executeQuery();
            if(resultSet.next()){
                if(creAction.getPersonalEmail().equalsIgnoreCase(resultSet.getString("Email1"))&&creAction.getCellPhoneNo().equalsIgnoreCase(resultSet.getString("CellPhoneNo")))
                     creAction.setResultMessage("<font color=\"red\" size=\"1.5\">Oops! your email&mobile number existed in our Database.Please contact HR Team!</font>");
                else if(creAction.getPersonalEmail().equalsIgnoreCase(resultSet.getString("Email1")))
                    creAction.setResultMessage("<font color=\"red\" size=\"1.5\">Oops! your personal email existed in our Database.Please contact HR Team!</font>");
                else if(creAction.getCellPhoneNo().equalsIgnoreCase(resultSet.getString("CellPhoneNo")))
                    creAction.setResultMessage("<font color=\"red\" size=\"1.5\">Oops! your personal mobile number existed in our Database.Please contact HR Team!</font>");
               // setResultMessage("<font color=\"red\" size=\"1.5\">Oops! you have been registered.</font>");
             //resultMessage = resultMessage+ "<font color=\"red\" size=\"1.5\">Please use the Registered ID to login.</font>";
                isUserExist = true;
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
        return isUserExist;
        
    }
    
            
      public int addCreUser(CreRegistrationAction creAction) throws ServiceLocatorException{
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
                            cstatement.setString(76,"Insert");
                            cstatement.setInt(77,0);
                            cstatement.setString(78,"");
                            
                            cstatement.setInt(79,creAction.getCampusLocation());
                            cstatement.setString(80,creAction.getRecLocation());
                            cstatement.setString(81,creAction.getJfairLocation());
                           // System.out.println("creAction.getCampusLocation()-->"+creAction.getCampusLocation());
                          //  System.out.println("creAction.getRecLocation()-->"+creAction.getRecLocation());
                          //  System.out.println("creAction.getjFairLocation()-->"+creAction.getJfairLocation());
                            
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

 
      
               
    
    
}
