/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 26, 2007, 12:36 PM
 *
 * Author  : Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * File    : AccountServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.accounts;

import com.mss.mirage.general.GeneralService;
import com.mss.mirage.general.GeneralServiceImpl;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.CacheManager;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//new
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.Properties;
import java.util.StringTokenizer;
import javax.servlet.http.HttpSession;
/**
 * This Class AccountServiceImpl is the implementation of an interface AccountService
 * @author Rajanikanth Teppala<rteppala@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 * @see com.mss.mirage.crm.accounts.AccountVTO
 */
public class AccountServiceImpl implements AccountService{
    
    /** Creates a new instance of AccountServiceImpl */
    public AccountServiceImpl() {
    }
    public String getContactEmailId(int contatId) throws ServiceLocatorException {
        
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;
        String emailId=null;
        queryString = "SELECT Email1 FROM tblCrmContact WHERE Id="+contatId;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            
            
            while(resultSet.next()){
                emailId =  resultSet.getString("Email1");
                if( emailId==null) {
                    emailId= "None";
                }
            }//Closing while loop
            
        }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        
        
        
        return emailId;
    }
    
    
    
    public int  createContactLogin(int contactId,String accountId,String emailId,String loginId) throws ServiceLocatorException {
        
        int upatedRows=0;
        int insertedRows=0;
        Connection connection = null;
        Statement statement = null;
       // Connection connection1 = null;
      //  PreparedStatement preparedStatement = null;
        String queryString = null;
       // String queryString1 = null;
       
      //  System.out.println("emailId-->"+emailId);
        String encryptPwd=null;
        GeneralService generalService = new GeneralServiceImpl();
        String  generatedPassword = generalService.generatePassword(8);
        PasswordUtility passwordUtility = new PasswordUtility();
        encryptPwd = passwordUtility.encryptPwd(generatedPassword);
       
        queryString = "update  tblCrmContact set LoginId='" + loginId + "'"  +   "," + "Pas"
                + "sword='" + encryptPwd + "'" + ", iFlag=1 WHERE Id="+contactId;
       // queryString1= "insert into tblCrmContactProjects(CrmContactId,ProjectId) values(?,?)";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            upatedRows = statement.executeUpdate(queryString);
           // connection1 = ConnectionProvider.getInstance().getConnection();
           //preparedStatement = connection1.prepareStatement(queryString1);
           // String[] projectsA = projects.split(",");
            //System.out.println("projectsA---------"+projectsA);
           // for(int i=0;i<projectsA.length;i++)
     //  {
          //  System.out.println("projectsA---------"+projectsA[i]);
          // preparedStatement.setInt(1, contatId);
          // preparedStatement.setInt(2, Integer.valueOf(projectsA[i]));
          // insertedRows=preparedStatement.executeUpdate();
       
      // }
          //  System.out.println("");
          }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
        }finally{
            try{
                
                if(statement!=null){
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
        
      /*  if(Properties.getProperty("Mail.Flag").equals("1")) {
        MailManager sendMail = new MailManager();
        sendMail.sendContactEmail(loginId,generatedPassword,emailId);
        }
        */
         String contactName = DataSourceDataProvider.getInstance().getCustomerNameById(contactId);
         String accountType = DataSourceDataProvider.getInstance().getAccountTypeById(Integer.parseInt(accountId));
        if(Properties.getProperty("Mail.Flag").equals("1")) {
        MailManager sendMail = new MailManager();
        
        sendMail.sendContactEmail(loginId,generatedPassword,emailId,contactName,accountType);
        }
        
        return upatedRows;
    }
    
    /**
     * The addOrUpdateAccount method is  used to add the new Account Details and update
     * the Account already existed.
     *
     * @param  accountPojo
     *         AccountAction Reference
     *
     * @throws ServiceLocatorException
     *         If a required Account value cannot be added or updated in the database.
     *
     * @return The return type of this method is int
     *         which depends on whether all the values are successfully inserted or not.
     *
     */
    public int addOrUpdateAccount(AccountAction accountPojo) throws ServiceLocatorException {
        
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        
        int updatedRows = 0;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
           callableStatement = connection.prepareCall("{call spAccount(?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            callableStatement.setInt(1,accountPojo.getId());
            callableStatement.setString(2,accountPojo.getAccountName());
            callableStatement.setString(3,accountPojo.getAccountType());
            callableStatement.setString(4,accountPojo.getUrl());
            callableStatement.setString(5,accountPojo.getSynonyms());
            callableStatement.setString(6,accountPojo.getPhone());
            callableStatement.setString(7,accountPojo.getFax());
            callableStatement.setString(8,accountPojo.getStatus());
            callableStatement.setString(9,accountPojo.getIndustry());
            callableStatement.setString(10,accountPojo.getRegion());
            callableStatement.setString(11,accountPojo.getTerritory());
            callableStatement.setString(12,accountPojo.getLeadSource());
            callableStatement.setString(13,accountPojo.getAccountTeam());
            callableStatement.setInt(14,accountPojo.getNoOfEmployees());
            callableStatement.setDouble(15,accountPojo.getRevenues());
            callableStatement.setString(16,accountPojo.getStockSymbol1());
            callableStatement.setString(17,accountPojo.getStockSymbol2());
            callableStatement.setDouble(18,accountPojo.getItBudget());
            callableStatement.setString(19,accountPojo.getTaxId());
            callableStatement.setString(20,accountPojo.getDescription());
            callableStatement.setString(21,accountPojo.getAddressType());
            callableStatement.setString(22,accountPojo.getAddressLine1());
            callableStatement.setString(23,accountPojo.getAddressLine2());
            callableStatement.setString(24,accountPojo.getCity());
            callableStatement.setString(25,accountPojo.getMailStop());
            callableStatement.setString(26,accountPojo.getState());
            callableStatement.setString(27,accountPojo.getCountry());
            callableStatement.setString(28,accountPojo.getZip());
            callableStatement.setString(29,accountPojo.getCreatedBy());
            callableStatement.setTimestamp(30,accountPojo.getDateCreated());
            callableStatement.setString(31,accountPojo.getModifiedBy());
            callableStatement.setTimestamp(32,accountPojo.getDateModified());
            callableStatement.setString(33,accountPojo.getActivityBY());
            callableStatement.setTimestamp(34,accountPojo.getDateLastActivity());
            callableStatement.setString(35,accountPojo.getOperationType());
            callableStatement.setInt(36,accountPojo.getPrimaryAddressId());
            callableStatement.setString(37,accountPojo.getIbmPPANo());
            callableStatement.setString(38,accountPojo.getIbmSiteNo());
            callableStatement.setDate(39,accountPojo.getDateOfPPARenewal());
            //callableStatement.setString(40,accountPojo.getPractice());
              //callableStatement.setInt(41,accountPojo.getPriority());
              callableStatement.setInt(40,accountPojo.getB2bPriority());
              callableStatement.setInt(41,accountPojo.getBpmPriority());
              callableStatement.setInt(42,accountPojo.getSapPriority());
              callableStatement.setInt(43,accountPojo.getEcomPriority());
              callableStatement.setInt(44,accountPojo.getQaPriority());//42,43,44
              callableStatement.setInt(45,accountPojo.getMainPriority());
//            callableStatement.registerOutParameter(42,Types.INTEGER);
//            callableStatement.registerOutParameter(43,Types.INTEGER);
//            callableStatement.registerOutParameter(44,Types.VARCHAR);
            callableStatement.registerOutParameter(46,Types.INTEGER);
            callableStatement.registerOutParameter(47,Types.INTEGER);
            callableStatement.registerOutParameter(48,Types.VARCHAR);
            updatedRows = callableStatement.executeUpdate();
            
            accountPojo.setPrimaryAddressId(callableStatement.getInt(46));
            //int maxAddressId = callableStatement.getInt(37);
            
            
            
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
        
        return updatedRows;
    }
    
    
    /**
     * The getAccount method is  used to edit the Complete Account Details.
     * Account Details are edited basing on the Account selected on the screen from the grid.
     *
     * @param  accountId
     *
     * @throws ServiceLocatorException
     *         If a required Account value cannot be added or updated in the database.
     *
     * @return The return type of this method is AccountVTO.
     *
     */
    public AccountVTO getAccount(int accountId, String userWorkCountry, String userRoleName) throws ServiceLocatorException {
        
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        String queryString = null;
        AccountVTO accountVTO = new AccountVTO();
        
       // if("Admin".equalsIgnoreCase(userRoleName)) {
        if("Admin".equalsIgnoreCase(userRoleName) || "Marketing".equalsIgnoreCase(userRoleName)){
            queryString = "SELECT * FROM tblCrmAccount WHERE Id="+accountId;
        }else{
            queryString = "SELECT * FROM tblCrmAccount join tblCrmAddress on (tblCrmAddress.Id = tblCrmAccount.PrimaryAddressId) where tblCrmAddress.Country like '"+userWorkCountry+"' AND tblCrmAccount.Id ="+accountId;
        }
        
        // accountVTO.setAccountName(
        //queryString = "SELECT * FROM tblCrmAccount WHERE Id="+accountId;
        //SELECT * FROM tblCrmAccount join tblCrmAddress on (tblCrmAddress.Id = tblCrmAccount.PrimaryAddressId) where tblCrmAccount.Id = 106053
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            accountVTO.setId(accountId);
            
            while(resultSet.next()){
                accountVTO.setAccountName(resultSet.getString("Name"));
                accountVTO.setAccountType(resultSet.getString("Type"));
                accountVTO.setUrl(resultSet.getString("WebAddress"));
                accountVTO.setSynonyms(resultSet.getString("Alias"));
                accountVTO.setStatus(resultSet.getString("Status"));
                accountVTO.setIndustry(resultSet.getString("Industry"));
                accountVTO.setLeadSource(resultSet.getString("LeadSource"));
                accountVTO.setRegion(resultSet.getString("Region"));
                accountVTO.setNoOfEmployees(resultSet.getInt("NoOfEmployees"));
                accountVTO.setRevenues(resultSet.getDouble("Revenues"));
                accountVTO.setTerritory(resultSet.getString("Teritory"));
                accountVTO.setStockSymbol1(resultSet.getString("StockSymbol1"));
                accountVTO.setStockSymbol2(resultSet.getString("StockSymbol2"));
                accountVTO.setPhone(resultSet.getString("Phone"));
                accountVTO.setItBudget(resultSet.getDouble("ItBudget"));
                accountVTO.setTaxId(resultSet.getString("TaxId"));
                accountVTO.setFax(resultSet.getString("Fax"));
                accountVTO.setDescription(resultSet.getString("Description"));
                accountVTO.setPrimaryAddressId(resultSet.getInt("PrimaryAddressId"));
                accountVTO.setCreatedBy(resultSet.getString("CreatedBy"));
                accountVTO.setDateCreated(resultSet.getTimestamp("DateCreated"));
                accountVTO.setModifiedBy(resultSet.getString("ModifiedBy"));
                accountVTO.setDateModified(resultSet.getTimestamp("DateModified"));
                accountVTO.setActivityBY(resultSet.getString("ActivityBy"));
                accountVTO.setDateLastActivity(resultSet.getTimestamp("DateLastActivity"));
               accountVTO.setIbmPPANo(resultSet.getString("IBMPPANo"));
                accountVTO.setIbmSiteNo(resultSet.getString("IBMSITENo"));
                accountVTO.setDateOfPPARenewal(resultSet.getDate("DateOfPPARenewal"));
                //accountVTO.setPractice(resultSet.getString("Practice"));
                //accountVTO.setPriority(resultSet.getInt("Priority"));
                accountVTO.setB2bPriority(resultSet.getInt("B2BPriority"));
                accountVTO.setBpmPriority(resultSet.getInt("BPMPriority"));
                accountVTO.setSapPriority(resultSet.getInt("SAPPriority"));
                accountVTO.setEcomPriority(resultSet.getInt("ECOMPriority"));
                accountVTO.setQaPriority(resultSet.getInt("QAPriority"));
                accountVTO.setMainPriority(resultSet.getInt("MainPriority"));
               // accountVTO.setDateOfPPARenewal(DateUtility.getInstance().convertDateToView(resultSet.getDate("DateOfPPARenewal")));
            }//Closing while loop
            
            
            if(accountVTO.getPrimaryAddressId() != 0){
                
                if("Admin".equalsIgnoreCase(userRoleName) || "Marketing".equalsIgnoreCase(userRoleName)) {
                    queryString = "SELECT * FROM tblCrmAddress where Id="+accountVTO.getPrimaryAddressId();
                }else{
                    queryString = "SELECT * FROM tblCrmAddress where Country like '"+userWorkCountry+"' AND Id="+accountVTO.getPrimaryAddressId();
                }
                
                
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                while(resultSet.next()){
                    accountVTO.setAddressType(resultSet.getString("AddressType"));
                    accountVTO.setAddressLine1(resultSet.getString("AddressLine1"));
                    accountVTO.setAddressLine2(resultSet.getString("Addressline2"));
                    accountVTO.setCity(resultSet.getString("City"));
                    accountVTO.setMailStop(resultSet.getString("MailStop"));
                    accountVTO.setState(resultSet.getString("State"));
                    accountVTO.setCountry(resultSet.getString("Country"));
                    accountVTO.setZip(resultSet.getString("Zip"));
                }
            }//Close If statement for PrimaryAddressId
            
            
        }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
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
        return accountVTO;
    }
    
    /**
     * The updateAccountTeamDetails method is  used to set the TeamMemberId basing on the accountId
     *
     * @param  accountId,teamId
     *
     * @throws ServiceLocatorException
     *         If a required Account value cannot be added in the database.
     *
     * @return The return type of this method is int
     *         which depends on whether all the values are successfully inserted or not.
     *
     */
    public int updateAccountTeamDetails(int accountId, String teamId) throws ServiceLocatorException {
        
        Connection connection = null;
        Statement statement = null;
        String queryString = null;
        int updatedRows = 0;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "UPDATE tblCrmAccountTeam SET TeamMemberId="+teamId+" WHERE AccountId="+accountId;
            statement = connection.createStatement();
            updatedRows = statement.executeUpdate(queryString);
            
        }catch(SQLException sql){
            throw new ServiceLocatorException(sql);
        }finally{
            try{
                if(statement!=null){
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
        return updatedRows;
    }
    
    /**
     * The getAccountVTO method is used to set the variables using the AccountAction class.
     *
     * @param  accountPojo
     *         AccountAction Reference
     *
     * @throws ServiceLocatorException
     *         If a required Account value cannot be added in the database.
     *
     * @return The return type of this method is AccountVTO.
     *
     */
    public AccountVTO getAccountVTO(AccountAction accountPojo) throws ServiceLocatorException {
        
        AccountVTO accountVTO = new AccountVTO();
        accountVTO.setId(accountPojo.getId());
        accountVTO.setAccountName(accountPojo.getAccountName());
        accountVTO.setAccountType(accountPojo.getAccountType());
        accountVTO.setUrl(accountPojo.getUrl());
        accountVTO.setSynonyms(accountPojo.getSynonyms());
        accountVTO.setStatus(accountPojo.getStatus());
        accountVTO.setIndustry(accountPojo.getIndustry());
        accountVTO.setLeadSource(accountPojo.getLeadSource());
        accountVTO.setAccountTeam(accountPojo.getAccountTeam());
        accountVTO.setRegion(accountPojo.getRegion());
        accountVTO.setNoOfEmployees(accountPojo.getNoOfEmployees());
        accountVTO.setRevenues(accountPojo.getRevenues());
        accountVTO.setTerritory(accountPojo.getTerritory());
        accountVTO.setStockSymbol1(accountPojo.getStockSymbol1());
        accountVTO.setStockSymbol2(accountPojo.getStockSymbol2());
        accountVTO.setPhone(accountPojo.getPhone());
        accountVTO.setItBudget(accountPojo.getItBudget());
        accountVTO.setTaxId(accountPojo.getTaxId());
        accountVTO.setFax(accountPojo.getFax());
        accountVTO.setDescription(accountPojo.getDescription());
        accountVTO.setPrimaryAddressId(accountPojo.getPrimaryAddressId());
        accountVTO.setCreatedBy(accountPojo.getCreatedBy());
        accountVTO.setDateCreated(accountPojo.getDateCreated());
        accountVTO.setModifiedBy(accountPojo.getModifiedBy());
        accountVTO.setDateModified(accountPojo.getDateModified());
        accountVTO.setActivityBY(accountPojo.getActivityBY());
        accountVTO.setDateLastActivity(accountPojo.getDateLastActivity());
        accountVTO.setAddressType(accountPojo.getAddressType());
        accountVTO.setAddressLine1(accountPojo.getAddressLine1());
        accountVTO.setAddressLine2(accountPojo.getAddressLine2());
        accountVTO.setCity(accountPojo.getCity());
        accountVTO.setMailStop(accountPojo.getMailStop());
        accountVTO.setState(accountPojo.getState());
        accountVTO.setCountry(accountPojo.getCountry());
        accountVTO.setZip(accountPojo.getZip());
          accountVTO.setIbmPPANo(accountPojo.getIbmPPANo());
        accountVTO.setIbmSiteNo(accountPojo.getIbmSiteNo());
        accountVTO.setDateOfPPARenewal(accountPojo.getDateOfPPARenewal());
        //accountVTO.setPractice(accountPojo.getPractice());
        //accountVTO.setPriority(accountPojo.getPriority());
        
        accountVTO.setB2bPriority(accountPojo.getB2bPriority());
        accountVTO.setBpmPriority(accountPojo.getBpmPriority());
        accountVTO.setSapPriority(accountPojo.getSapPriority());
        accountVTO.setEcomPriority(accountPojo.getEcomPriority());
        accountVTO.setQaPriority(accountPojo.getQaPriority());
        accountVTO.setMainPriority(accountPojo.getMainPriority());
        //accountVTO.setDateOfPPARenewal(DateUtility.getInstance().convertDateToView(accountPojo.getDateOfPPARenewal()));
        return accountVTO;
        
    }
    
    /**
     * The getAccountsList method is used see the variables using the AccountAction class.
     *
     * @param  queryString
     *
     * @throws ServiceLocatorException
     *         If a required Account value cannot be added in the database.
     *
     * @return The return type of this method is List which is new instance for accountsList.
     *
     */
    public List getAccountsList(String queryString) throws ServiceLocatorException {
        
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        AccountVTO accountVTO = null;
        List accountsList = new ArrayList();
        try{
            
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()){
                accountVTO = new AccountVTO();
                accountVTO.setId(resultSet.getInt("AccountId"));
                accountVTO.setAccountName(resultSet.getString("AccountName"));
                accountVTO.setStatus(resultSet.getString("Status"));
                accountVTO.setIndustry(resultSet.getString("Industry"));
                accountVTO.setUrl(resultSet.getString("URL"));
                accountVTO.setAccountTeamName(resultSet.getString("AccountTeam"));
                accountVTO.setRegion(resultSet.getString("Region"));
                accountVTO.setTerritory(resultSet.getString("Teritory"));
                accountVTO.setDateLastActivity(resultSet.getTimestamp("DateLastActivity"));
                accountsList.add(accountVTO);
            }
            
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement!=null){
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
        
        return accountsList;
    }
    
    /**
     * The addSoftware method is used used to add the new Software Details for the
     * particular Account
     *
     * @param  softwarePojo
     *	       SoftwareAction reference
     *
     * @throws ServiceLocatorException
     *         If a required Account value cannot be added in the database.
     *
     * @return The return type of this method is int
     *         which depends on whether all the values are successfully inserted or not.
     */
    
    public int addSoftware(SoftwareAction softwarePojo) throws ServiceLocatorException {
        
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;
        
        int updatedRows = 0;
        int deleteRows=0;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            queryString ="INSERT INTO tblCrmAccountDetails (AccountId,Applications,Hardware,Softwares,DatabaseDetails,Sap,Siebel,OracleApps,PeopleSoft,JDEdwards,Baan,Sapxi,IConnect,OracleFusion,BeaWebLogic,MessageBroker,Ics,Wps,Fabric,WebMethods,SeeBeyond,Tibco,Gentran,Gxs,Cyclone,Harbinger,SeeBurger,Xtol,Mercator,Oracle,Db2,MsSql,MySql,Informatica,MicroStrategy,Cognos,Hyperion,BusinessObjects,Cobol,Mq,Hats,MainFrames,As400,Aix,HpUx,Solaris,RedHat,Windows,DotNet,Jsp,Coldfusion,Manuguistics,ITwo,Vignette,Broadvision,Neon,Vitria,Selectica,WebSphere,Ariba,Commerce,DataPower,IbmPortals,WTX,JCAPS,CI,BPM,WODM,Rational,LOTUS,HP_QualityCenter,Sterling_B2b,Hybris) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryString);
            
            preparedStatement.setInt(1,softwarePojo.getId());
            
            preparedStatement.setString(2,softwarePojo.getApplications());
            
            preparedStatement.setString(3,softwarePojo.getHardware());
            
            preparedStatement.setString(4,softwarePojo.getSoftwares());
            
            preparedStatement.setString(5,softwarePojo.getDatabases());
            
            preparedStatement.setBoolean(6,softwarePojo.isSap());
            
            preparedStatement.setBoolean(7,softwarePojo.isSiebel());
            
            preparedStatement.setBoolean(8,softwarePojo.isOracleApps());
            
            preparedStatement.setBoolean(9,softwarePojo.isPeopleSoft());
            
            preparedStatement.setBoolean(10,softwarePojo.isJdEdwards());
            
            preparedStatement.setBoolean(11,softwarePojo.isBaan());
            
            preparedStatement.setBoolean(12,softwarePojo.isSapxi()) ;
            
            preparedStatement.setBoolean(13,softwarePojo.isIConnect());
            
            preparedStatement.setBoolean(14,softwarePojo.isOracleFusion());
            
            preparedStatement.setBoolean(15,softwarePojo.isBeaWeblogic());
            
            preparedStatement.setBoolean(16,softwarePojo.isMessageBroker());
            
            preparedStatement.setBoolean(17,softwarePojo.isIcs());
            
            preparedStatement.setBoolean(18,softwarePojo.isWps());
            
            preparedStatement.setBoolean(19,softwarePojo.isFabric());
            
            preparedStatement.setBoolean(20,softwarePojo.isWebMethods());
            
            preparedStatement.setBoolean(21,softwarePojo.isSeeBeyond());
            
            preparedStatement.setBoolean(22,softwarePojo.isTibco());
            
            preparedStatement.setBoolean(23,softwarePojo.isGentran());
            
            preparedStatement.setBoolean(24,softwarePojo.isGxs());
            
            preparedStatement.setBoolean(25,softwarePojo.isCyclone());
            
            preparedStatement.setBoolean(26,softwarePojo.isHarbinger());
            
            preparedStatement.setBoolean(27,softwarePojo.isSeeBurger());
            
            preparedStatement.setBoolean(28,softwarePojo.isXtol());
            
            preparedStatement.setBoolean(29,softwarePojo.isMercator());
            
            preparedStatement.setBoolean(30,softwarePojo.isOracle());
            
            preparedStatement.setBoolean(31,softwarePojo.isDbTwo());
            
            preparedStatement.setBoolean(32,softwarePojo.isMsSql());
            
            preparedStatement.setBoolean(33,softwarePojo.isMySql());
            
            preparedStatement.setBoolean(34,softwarePojo.isInformatica());
            
            preparedStatement.setBoolean(35,softwarePojo.isMicroStrategy());
            
            preparedStatement.setBoolean(36,softwarePojo.isCognos());
            
            preparedStatement.setBoolean(37,softwarePojo.isHyperion());
            
            preparedStatement.setBoolean(38,softwarePojo.isBusinessObjects());
            
            preparedStatement.setBoolean(39,softwarePojo.isCobol());
            
            preparedStatement.setBoolean(40,softwarePojo.isMq());
            
            preparedStatement.setBoolean(41,softwarePojo.isHats());
            
            preparedStatement.setBoolean(42,softwarePojo.isMainFrames());
            
            preparedStatement.setBoolean(43,softwarePojo.isAsFour());
            
            preparedStatement.setBoolean(44,softwarePojo.isAix());
            
            preparedStatement.setBoolean(45,softwarePojo.isHpUx());
            
            preparedStatement.setBoolean(46,softwarePojo.isSolaris());
            
            preparedStatement.setBoolean(47,softwarePojo.isRedHat());
            
            preparedStatement.setBoolean(48,softwarePojo.isWindows());
            
            preparedStatement.setBoolean(49,softwarePojo.isDotNet());
            
            preparedStatement.setBoolean(50,softwarePojo.isJsp());
            
            preparedStatement.setBoolean(51,softwarePojo.isColdFusion());
            
            preparedStatement.setBoolean(52,softwarePojo.isManuguistics());
            
            preparedStatement.setBoolean(53,softwarePojo.isITwo());
            
            preparedStatement.setBoolean(54,softwarePojo.isVignette());
            
            preparedStatement.setBoolean(55,softwarePojo.isBroadvision());
            
            preparedStatement.setBoolean(56,softwarePojo.isNeon());
            
            preparedStatement.setBoolean(57,softwarePojo.isVitria());
            
            preparedStatement.setBoolean(58,softwarePojo.isSelectica());
            
            preparedStatement.setBoolean(59,softwarePojo.isWebSphere());
            
            preparedStatement.setBoolean(60,softwarePojo.isAriba());
            
            preparedStatement.setBoolean(61,softwarePojo.isCommerce());
            
            preparedStatement.setBoolean(62,softwarePojo.isDataPower());
            
            preparedStatement.setBoolean(63,softwarePojo.isIbmPortals());
            
			preparedStatement.setBoolean(64,softwarePojo.isWtx());
            
            preparedStatement.setBoolean(65,softwarePojo.isJcaps());
            
            preparedStatement.setBoolean(66,softwarePojo.isCastIron());
            
            preparedStatement.setBoolean(67,softwarePojo.isBpm());
            
            preparedStatement.setBoolean(68,softwarePojo.isWodm());
            
            preparedStatement.setBoolean(69,softwarePojo.isRational());
            
            preparedStatement.setBoolean(70,softwarePojo.isLotus());
            
            preparedStatement.setBoolean(71,softwarePojo.isHp());
			
			preparedStatement.setBoolean(72,softwarePojo.isB2b());
			preparedStatement.setBoolean(73,softwarePojo.isHybris());
            
            updatedRows = preparedStatement.executeUpdate();
            
            
        }catch (SQLException se){
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
        return updatedRows;
    }
    
    
    public int editSoftware(SoftwareAction softwarePojo) throws ServiceLocatorException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = null;
        int updatedRows = 0;
        int rowUpdate=softwarePojo.getId();
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            
            queryString ="UPDATE tblCrmAccountDetails SET Applications=?,Hardware=?,Softwares=?,DatabaseDetails=?,Sap=?,Siebel=?,OracleApps=?,PeopleSoft=?,JDEdwards=?,Baan=?,Sapxi=?,IConnect=?,OracleFusion=?,BeaWebLogic=?,MessageBroker=?,Ics=?,Wps=?,Fabric=?,WebMethods=?,SeeBeyond=?,Tibco=?,Gentran=?,Gxs=?,Cyclone=?,Harbinger=?,SeeBurger=?,Xtol=?,Mercator=?,Oracle=?,Db2=?,MsSql=?,MySql=?,Informatica=?,MicroStrategy=?,Cognos=?,Hyperion=?,BusinessObjects=?,Cobol=?,Mq=?,Hats=?,MainFrames=?,As400=?,Aix=?,HpUx=?,Solaris=?,RedHat=?,Windows=?,DotNet=?,Jsp=?,Coldfusion=?,Manuguistics=?,ITwo=?,Vignette=?,Broadvision=?,Neon=?,Vitria=?,Selectica=?,WebSphere=?,Ariba=?,Commerce=?,DataPower=?,IbmPortals=?,WTX=?,JCAPS=?,CI=?,BPM=?,WODM=?,Rational=?,LOTUS=?,HP_QualityCenter=?,Sterling_B2b=?,Hybris=? WHERE AccountId="+rowUpdate;
            
            preparedStatement = connection.prepareStatement(queryString);
            
            //preparedStatement.setInt(1,softwarePojo.getId());
            
            preparedStatement.setString(1,softwarePojo.getApplications());
            
            preparedStatement.setString(2,softwarePojo.getHardware());
            
            preparedStatement.setString(3,softwarePojo.getSoftwares());
            
            preparedStatement.setString(4,softwarePojo.getDatabases());
            
            preparedStatement.setBoolean(5,softwarePojo.isSap());
            
            preparedStatement.setBoolean(6,softwarePojo.isSiebel());
            
            preparedStatement.setBoolean(7,softwarePojo.isOracleApps());
            
            preparedStatement.setBoolean(8,softwarePojo.isPeopleSoft());
            
            preparedStatement.setBoolean(9,softwarePojo.isJdEdwards());
            
            preparedStatement.setBoolean(10,softwarePojo.isBaan());
            
            preparedStatement.setBoolean(11,softwarePojo.isSapxi()) ;
            
            preparedStatement.setBoolean(12,softwarePojo.isIConnect());
            
            preparedStatement.setBoolean(13,softwarePojo.isOracleFusion());
            
            preparedStatement.setBoolean(14,softwarePojo.isBeaWeblogic());
            
            preparedStatement.setBoolean(15,softwarePojo.isMessageBroker());
            
            preparedStatement.setBoolean(16,softwarePojo.isIcs());
            
            preparedStatement.setBoolean(17,softwarePojo.isWps());
            
            preparedStatement.setBoolean(18,softwarePojo.isFabric());
            
            preparedStatement.setBoolean(19,softwarePojo.isWebMethods());
            
            preparedStatement.setBoolean(20,softwarePojo.isSeeBeyond());
            
            preparedStatement.setBoolean(21,softwarePojo.isTibco());
            
            preparedStatement.setBoolean(22,softwarePojo.isGentran());
            
            preparedStatement.setBoolean(23,softwarePojo.isGxs());
            
            preparedStatement.setBoolean(24,softwarePojo.isCyclone());
            
            preparedStatement.setBoolean(25,softwarePojo.isHarbinger());
            
            preparedStatement.setBoolean(26,softwarePojo.isSeeBurger());
            
            preparedStatement.setBoolean(27,softwarePojo.isXtol());
            
            preparedStatement.setBoolean(28,softwarePojo.isMercator());
            
            preparedStatement.setBoolean(29,softwarePojo.isOracle());
            
            preparedStatement.setBoolean(30,softwarePojo.isDbTwo());
            
            preparedStatement.setBoolean(31,softwarePojo.isMsSql());
            
            preparedStatement.setBoolean(32,softwarePojo.isMySql());
            
            preparedStatement.setBoolean(33,softwarePojo.isInformatica());
            
            preparedStatement.setBoolean(34,softwarePojo.isMicroStrategy());
            
            preparedStatement.setBoolean(35,softwarePojo.isCognos());
            
            preparedStatement.setBoolean(36,softwarePojo.isHyperion());
            
            preparedStatement.setBoolean(37,softwarePojo.isBusinessObjects());
            
            preparedStatement.setBoolean(38,softwarePojo.isCobol());
            
            preparedStatement.setBoolean(39,softwarePojo.isMq());
            
            preparedStatement.setBoolean(40,softwarePojo.isHats());
            
            preparedStatement.setBoolean(41,softwarePojo.isMainFrames());
            
            preparedStatement.setBoolean(42,softwarePojo.isAsFour());
            
            preparedStatement.setBoolean(43,softwarePojo.isAix());
            
            preparedStatement.setBoolean(44,softwarePojo.isHpUx());
            
            preparedStatement.setBoolean(45,softwarePojo.isSolaris());
            
            preparedStatement.setBoolean(46,softwarePojo.isRedHat());
            
            preparedStatement.setBoolean(47,softwarePojo.isWindows());
            
            preparedStatement.setBoolean(48,softwarePojo.isDotNet());
            
            preparedStatement.setBoolean(49,softwarePojo.isJsp());
            
            preparedStatement.setBoolean(50,softwarePojo.isColdFusion());
            
            preparedStatement.setBoolean(51,softwarePojo.isManuguistics());
            
            preparedStatement.setBoolean(52,softwarePojo.isITwo());
            
            preparedStatement.setBoolean(53,softwarePojo.isVignette());
            
            preparedStatement.setBoolean(54,softwarePojo.isBroadvision());
            
            preparedStatement.setBoolean(55,softwarePojo.isNeon());
            
            preparedStatement.setBoolean(56,softwarePojo.isVitria());
            
            preparedStatement.setBoolean(57,softwarePojo.isSelectica());
            
            preparedStatement.setBoolean(58,softwarePojo.isWebSphere());
            
            preparedStatement.setBoolean(59,softwarePojo.isAriba());
            
            preparedStatement.setBoolean(60,softwarePojo.isCommerce());
            
            preparedStatement.setBoolean(61,softwarePojo.isDataPower());
            
            preparedStatement.setBoolean(62,softwarePojo.isIbmPortals());
            
			preparedStatement.setBoolean(63,softwarePojo.isWtx());
            
            preparedStatement.setBoolean(64,softwarePojo.isJcaps());
            
            preparedStatement.setBoolean(65,softwarePojo.isCastIron());
            
            preparedStatement.setBoolean(66,softwarePojo.isBpm());
            
            preparedStatement.setBoolean(67,softwarePojo.isWodm());
            
            preparedStatement.setBoolean(68,softwarePojo.isRational());
            
            preparedStatement.setBoolean(69,softwarePojo.isLotus());
            
            preparedStatement.setBoolean(70,softwarePojo.isHp());
			
			preparedStatement.setBoolean(71,softwarePojo.isB2b());
                        preparedStatement.setBoolean(72, softwarePojo.isHybris());
           // System.out.println("iscommerce"+softwarePojo.isCommerce());
            
            updatedRows = preparedStatement.executeUpdate();
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if( preparedStatement!=null ){
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
        return updatedRows;
    }
    
    public SoftwareVTO getSoftwareDetails(int accountId) throws ServiceLocatorException {
        
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        String queryString = null;
        boolean flag=false;
        
        queryString = "SELECT Applications,Hardware,Softwares,DatabaseDetails,Manuguistics,SUM(ITwo) AS ITWO,SUM(Vignette) AS Vignette,SUM(Broadvision) AS Broadvision,SUM(Neon) AS Neon,SUM(Vitria) AS Vitria,SUM(Selectica) AS Selectica,SUM(WebSphere) AS WebSphere,SUM(Ariba) AS Ariba,SUM(Sap) AS Sap,SUM(Siebel) AS Siebel,SUM(OracleApps) AS OracleApps,SUM(PeopleSoft) AS PeopleSoft,SUM(JDEdwards) AS JDEdwards,SUM(Baan) AS Baan,SUM(Sapxi) AS Sapxi,SUM(IConnect) AS IConnect,SUM(OracleFusion) AS OracleFusion,SUM(BeaWebLogic) AS BeaWebLogic,SUM(MessageBroker) AS MessageBroker,SUM(Ics) AS Ics,SUM(Wps) AS Wps,SUM(Fabric) AS Fabric,SUM(WebMethods) AS WebMethods,SUM(SeeBeyond) AS SeeBeyond,SUM(Tibco) AS Tibco,SUM(Gentran) AS Gentran,SUM(Gxs) AS Gxs,SUM(Cyclone) AS Cyclone,SUM(Harbinger) AS Harbinger,SUM(SeeBurger) AS SeeBurger,SUM(Xtol) AS Xtol,SUM(Mercator) AS Mercator,SUM(Oracle) AS Oracle,SUM(Db2) AS Db2,SUM(MsSql) AS MsSql,SUM(MySql) AS MySql,SUM(Informatica) AS Informatica,SUM(MicroStrategy) AS MicroStrategy,SUM(Cognos) AS Cognos,SUM(Hyperion) AS Hyperion,SUM(BusinessObjects) AS BusinessObjects,SUM(Cobol) AS Cobol,SUM(Mq) AS Mq,SUM(Hats) AS Hats,SUM(MainFrames) AS MainFrames,SUM(As400) AS As400,SUM(Aix) AS Aix,SUM(HpUx) AS HpUx,SUM(Solaris) AS Solaris,SUM(RedHat) AS RedHat,SUM(Windows) AS Windows,SUM(DotNet) AS DotNet,SUM(Jsp) AS Jsp,SUM(Coldfusion) AS Coldfusion,SUM(Wdi) AS Wdi,SUM(BiztalkServer) AS BiztalkServer,SUM(BeaPortals) AS BeaPortals,SUM(OraclePortals) AS OraclePortals,SUM(IbmPortals) AS IbmPortals,SUM(SharePoint) AS SharePoint,SUM(SapPortals) AS SapPortals,SUM(Microsoft) AS Microsoft,SUM(Commerce) AS Commerce,SUM(DataPower) AS DataPower,SUM(IbmPortals) AS IbmPortals,SUM(WTX) AS WTX,SUM(JCAPS) AS JCAPS,SUM(CI) AS CI,SUM(BPM) AS BPM,SUM(WODM) AS WODM,SUM(Rational) AS Rational,SUM(LOTUS) AS LOTUS,SUM(HP_QualityCenter) AS HP_QualityCenter,SUM(Sterling_B2b) AS Sterling_B2b,SUM(Hybris) AS Hybris FROM tblCrmAccountDetails WHERE AccountId="+ accountId +" GROUP BY AccountId";
        int deleteRows=0;
        SoftwareVTO softwareVTO =new SoftwareVTO();
        
        try{
            connection= ConnectionProvider.getInstance().getConnection();
            statement =connection.createStatement();
            resultSet =statement.executeQuery(queryString);
            
            while(resultSet.next()){
                flag=true;
                if(flag){
                    // In this Account already check boxes are used.
                    //detailsAdd(accountPojo);
                    
                    
                    softwareVTO.setId(accountId);
                    softwareVTO.setApplications(resultSet.getString("Applications"));
                    softwareVTO.setHardware(resultSet.getString("Hardware"));
                    softwareVTO.setSoftwares(resultSet.getString("Softwares"));
                    softwareVTO.setDatabases(resultSet.getString("DatabaseDetails"));
                    softwareVTO.setSap(resultSet.getBoolean("Sap"));
                    softwareVTO.setSiebel(resultSet.getBoolean("Siebel"));
                    softwareVTO.setOracleApps(resultSet.getBoolean("OracleApps"));
                    softwareVTO.setPeopleSoft(resultSet.getBoolean("PeopleSoft"));
                    softwareVTO.setJdEdwards(resultSet.getBoolean("JDEdwards"));
                    softwareVTO.setBaan(resultSet.getBoolean("Baan"));
                    softwareVTO.setSapxi(resultSet.getBoolean("Sapxi"));
                    softwareVTO.setIConnect(resultSet.getBoolean("IConnect"));
                    softwareVTO.setOracleFusion(resultSet.getBoolean("OracleFusion"));
                    softwareVTO.setBeaWeblogic(resultSet.getBoolean("BeaWebLogic"));
                    softwareVTO.setMessageBroker(resultSet.getBoolean("MessageBroker"));
                    softwareVTO.setIcs(resultSet.getBoolean("Ics"));
                    softwareVTO.setWps(resultSet.getBoolean("Wps"));
                    softwareVTO.setFabric(resultSet.getBoolean("Fabric"));
                    softwareVTO.setWebMethods(resultSet.getBoolean("WebMethods"));
                    softwareVTO.setSeeBeyond(resultSet.getBoolean("SeeBeyond"));
                    softwareVTO.setTibco(resultSet.getBoolean("Tibco"));
                    softwareVTO.setGentran(resultSet.getBoolean("Gentran"));
                    softwareVTO.setGxs(resultSet.getBoolean("Gxs"));
                    softwareVTO.setCyclone(resultSet.getBoolean("Cyclone"));
                    softwareVTO.setHarbinger(resultSet.getBoolean("Harbinger"));
                    softwareVTO.setSeeBurger(resultSet.getBoolean("SeeBurger"));
                    softwareVTO.setXtol(resultSet.getBoolean("Xtol"));
                    softwareVTO.setMercator(resultSet.getBoolean("Mercator"));
                    softwareVTO.setOracle(resultSet.getBoolean("Oracle"));
                    softwareVTO.setDbTwo(resultSet.getBoolean("Db2"));
                    softwareVTO.setMsSql(resultSet.getBoolean("MsSql"));
                    softwareVTO.setMySql(resultSet.getBoolean("MySql"));
                    softwareVTO.setInformatica(resultSet.getBoolean("Informatica"));
                    softwareVTO.setMicroStrategy(resultSet.getBoolean("MicroStrategy"));
                    softwareVTO.setCognos(resultSet.getBoolean("Cognos"));
                    softwareVTO.setHyperion(resultSet.getBoolean("Hyperion"));
                    softwareVTO.setBusinessObjects(resultSet.getBoolean("BusinessObjects"));
                    softwareVTO.setCobol(resultSet.getBoolean("Cobol"));
                    softwareVTO.setMq(resultSet.getBoolean("Mq"));
                    softwareVTO.setHats(resultSet.getBoolean("Hats"));
                    softwareVTO.setMainFrames(resultSet.getBoolean("MainFrames"));
                    softwareVTO.setAsFour(resultSet.getBoolean("As400"));
                    softwareVTO.setAix(resultSet.getBoolean("Aix"));
                    softwareVTO.setHpUx(resultSet.getBoolean("HpUx"));
                    softwareVTO.setSolaris(resultSet.getBoolean("Solaris"));
                    softwareVTO.setRedHat(resultSet.getBoolean("RedHat"));
                    softwareVTO.setWindows(resultSet.getBoolean("Windows"));
                    softwareVTO.setDotNet(resultSet.getBoolean("DotNet"));
                    softwareVTO.setJsp(resultSet.getBoolean("Jsp"));
                    softwareVTO.setColdFusion(resultSet.getBoolean("Coldfusion"));
                    softwareVTO.setManuguistics(resultSet.getBoolean("Manuguistics"));
                    softwareVTO.setITwo(resultSet.getBoolean("ITwo"));
                    softwareVTO.setVignette(resultSet.getBoolean("Vignette"));
                    softwareVTO.setBroadvision(resultSet.getBoolean("Broadvision"));
                    softwareVTO.setNeon(resultSet.getBoolean("Neon"));
                    softwareVTO.setVitria(resultSet.getBoolean("Vitria"));
                    softwareVTO.setSelectica(resultSet.getBoolean("Selectica"));
                    softwareVTO.setWebSphere(resultSet.getBoolean("WebSphere"));
                    softwareVTO.setAriba(resultSet.getBoolean("Ariba"));
                    softwareVTO.setCommerce(resultSet.getBoolean("Commerce"));
                    softwareVTO.setDataPower(resultSet.getBoolean("DataPower"));
                    softwareVTO.setIbmPortals(resultSet.getBoolean("IbmPortals"));
					softwareVTO.setWtx(resultSet.getBoolean("WTX"));
                    softwareVTO.setJcaps(resultSet.getBoolean("JCAPS"));
                    softwareVTO.setCastIron(resultSet.getBoolean("CI"));
                    softwareVTO.setWodm(resultSet.getBoolean("WODM"));
                    softwareVTO.setBpm(resultSet.getBoolean("BPM"));
		            softwareVTO.setB2b(resultSet.getBoolean("Sterling_B2b"));
                    softwareVTO.setRational(resultSet.getBoolean("Rational"));
                    softwareVTO.setLotus(resultSet.getBoolean("LOTUS"));
                    softwareVTO.setHp(resultSet.getBoolean("HP_QualityCenter"));
                    softwareVTO.setHybris(resultSet.getBoolean("Hybris"));
                    softwareVTO.setFormAction("editSoftware");
                } //if loop
            } // while loop
            
            if(!flag){
                softwareVTO.setFormAction("addSoftware");
            }
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                    statement.close();
                    statement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch(SQLException sql){
                throw new ServiceLocatorException(sql);
            }
        }
        return softwareVTO;
        
    }
    
    public Map getAccountTeamByAccountId(int accountId,String primaryTeamMember) throws ServiceLocatorException {
        
        Map accountTeamMap = new HashMap();
        accountTeamMap.clear();
        
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        
        try {
            statement = connection.createStatement();
            
            if(!("".equals(primaryTeamMember))){
                resultSet = statement.executeQuery("SELECT DISTINCT LoginId,EmployeeName FROM vwAccountTeamList WHERE AccountId="+accountId+" AND LoginId='"+primaryTeamMember+"'");
                while(resultSet.next()) {
                    accountTeamMap.put(resultSet.getString("LoginId"),resultSet.getString("EmployeeName"));
                }
                resultSet.close();
                resultSet = null;
            }
            
            resultSet = statement.executeQuery("SELECT DISTINCT LoginId,EmployeeName FROM vwAccountTeamList WHERE AccountId="+accountId);
            while(resultSet.next()) {
                if(!(accountTeamMap.containsKey(resultSet.getString("LoginId")))){
                    accountTeamMap.put(resultSet.getString("LoginId"),resultSet.getString("EmployeeName"));
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
        return accountTeamMap;
    }
    
  public Map getAllSalesTeamExceptAccountTeam(Map accountTeam) throws ServiceLocatorException {
       
           
        Map salesTeamExceptAccountTeamMap =new LinkedHashMap();
        salesTeamExceptAccountTeamMap.clear();
        //Map sortedMap = new LinkedHashMap();
        //sortedMap.clear();
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        
         
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmpRoles,tblLKRoles,tblEmployee WHERE tblEmployee.Id=tblEmpRoles.EmpId AND tblLKRoles.Id=tblEmpRoles.RoleId AND RoleId=4 AND CurStatus ='Active' AND DeletedFlag != 1 AND DepartmentId='Sales' ORDER BY FName ");
            while(resultSet.next()) {
                if(!(accountTeam.containsKey(resultSet.getString("LoginId")))){
                   salesTeamExceptAccountTeamMap.put(resultSet.getString("LoginId"),resultSet.getString("EmployeeName"));
                     List list = new LinkedList( salesTeamExceptAccountTeamMap.entrySet());
                     
                      Collections.sort(list, new Comparator() {
                       public int compare(Object o1, Object o2) {
	           return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
             }
	});
 
           
	for (Iterator it = list.iterator(); it.hasNext();) {
	     Map.Entry entry = (Map.Entry)it.next();
             salesTeamExceptAccountTeamMap.put(entry.getKey(), entry.getValue());         
                }
              }  
        }
        
        // CacheManager.getCache().put("allSalesTeamExceptAccountTeam",salesTeamExceptAccountTeamMap);
        
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
        } finally {
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
         
        
        return salesTeamExceptAccountTeamMap;
    }
    
    
    

    
    
    
    
    public Map getAllVendorTeam(Map vendorTeam) throws ServiceLocatorException {
        // System.out.println("vendorTeam  "+vendorTeam);
        Map vendorTeamMap = new TreeMap();
        vendorTeamMap.clear();
        
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee,tblEmpRoles WHERE CurStatus='Active' AND tblEmployee.id=tblEmpRoles.EmpId AND tblEmpRoles.RoleId ='8' ORDER BY FName");
            while(resultSet.next()) {
                if(!(vendorTeam.containsKey(resultSet.getString("LoginId")))){
                    vendorTeamMap.put(resultSet.getString("LoginId"),resultSet.getString("EmployeeName"));
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
        
        return vendorTeamMap;
    }
    //prevoius functionality for account team assigning
   /* public int updateAccountTeamMembers(String[] accountTeamMembers,int accountId) throws ServiceLocatorException {
        int insertedRows = 0;
        
        
        Connection connection = null;
        Statement statement = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            boolean isDelete = statement.execute("DELETE FROM tblCrmAccountTeam WHERE AccountId="+accountId);
            statement.close();
            statement = null;
            if(!isDelete){
                statement=connection.createStatement();
                
                for(int i=0;i<accountTeamMembers.length;i++){
                    if(i==0){
                        statement.addBatch("INSERT INTO tblCrmAccountTeam(AccountId, TeamMemberId, SalesFlag, PrimaryFlag) values("
                                +accountId+",'"+accountTeamMembers[i]+"',0,1)");
                     }else{
                        statement.addBatch("INSERT INTO tblCrmAccountTeam(AccountId, TeamMemberId, SalesFlag, PrimaryFlag) values("
                                +accountId+",'"+accountTeamMembers[i]+"',0,0)");
                     }
                }
                int[] insertedRowsArray = statement.executeBatch();
                insertedRows = insertedRowsArray.length;
                
                
                
            }
            
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            }catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return insertedRows;
    }*/
    //new functionality for account team after emailnotification
     /*modified by:prasad kandregula
     *email:vkandregula@miraclesoft.com
     *method:updateAccountTeamMembers
     ***/
      public int updateAccountTeamMembers(String[] accountTeamMembers,int accountId,String primaryId ,int rid,HttpSession httpsession) throws ServiceLocatorException {
        int insertedRows = 0;
          
        Connection connection = null;
        Statement statement = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
           
            boolean isDelete = statement.execute("DELETE FROM tblCrmAccountTeam WHERE AccountId="+accountId);
            statement.close();
            statement = null;
            if(!isDelete){
                statement=connection.createStatement();
                if(accountTeamMembers!=null)
                {
                for(int i=0;i<accountTeamMembers.length;i++ ){
                 
                    if(i==0)
                    {
                        statement.addBatch("INSERT INTO tblCrmAccountTeam(AccountId, TeamMemberId, SalesFlag, PrimaryFlag) values("
                                +accountId+",'"+accountTeamMembers[i]+"',0,1)");
                         //ApplicationConstants.AFTER_UPDATEPRIMARYTEAM = accountTeamMembers[i];
                         httpsession.setAttribute(ApplicationConstants.AFTER_UPDATEPRIMARYTEAM,accountTeamMembers[i]);
                     }else{
                        statement.addBatch("INSERT INTO tblCrmAccountTeam(AccountId, TeamMemberId, SalesFlag, PrimaryFlag) values("
                                +accountId+",'"+accountTeamMembers[i]+"',0,0)");
                     }
                  
                }
                }
                int[] insertedRowsArray = statement.executeBatch();
                insertedRows = insertedRowsArray.length;
                
                
                
            }
            
        } catch (SQLException ex) {
            throw new ServiceLocatorException(ex);
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
            }catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return insertedRows;
    }
    
    public String getPrimaryTeamMember(int accountId) throws ServiceLocatorException {
        
        String primaryTeamMemberUserId = "";
        
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT TeamMemberId FROM tblCrmAccountTeam WHERE AccountId="+accountId+" AND PrimaryFlag=1");
            while(resultSet.next()){
                primaryTeamMemberUserId = resultSet.getString("TeamMemberId");
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
        
        return primaryTeamMemberUserId;
    }
    
    public int updateAssignAccounts(String fromUserId, String toUserId) throws ServiceLocatorException {
        
        int updatedRows = 0;
        
        Connection connection = null;
        CallableStatement callableStatement = null;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spTransfterTeamMembers(?,?)}");
            callableStatement.setString(1,fromUserId);
            callableStatement.setString(2,toUserId);
            
            updatedRows = callableStatement.executeUpdate();
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
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
        
        return updatedRows;
    }
   
public int doClientReqEngagementAdd(AccountAction accountAction) throws ServiceLocatorException{
         int updatedRows = 0;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CallableStatement callbleStatement=null;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
           // preparedStatement = connection.prepareStatement("INSERT INTO tblPSCER(RequestorId,AccountId,OpportunityId,RvpName,MeetingType,LevelOfEngagement,State,City,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,Comments,CreatedBy,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,Stage,ReqStatus,AccountType,AccountName,OppurtunityName) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
         //   callbleStatement = connection.prepareCall("{call spClientReqEngagement(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
           // callbleStatement = connection.prepareCall("{call spClientReqEngagement(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            //   callbleStatement = connection.prepareCall("{call spClientReqEngagement(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                 callbleStatement = connection.prepareCall("{call spClientReqEngagement(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
          callbleStatement.setString(1,accountAction.getRequestorId());
          callbleStatement.setInt(2,accountAction.getConsultantId());
          callbleStatement.setInt(3,accountAction.getOpportunityId());
          callbleStatement.setString(4,accountAction.getRvpName());
          callbleStatement.setString(5,accountAction.getMeetingType());
         // preparedStatement.setString(6,accountAction.getMeetingStatus());
          callbleStatement.setString(6,accountAction.getLevelEngagement());
          callbleStatement.setString(7,accountAction.getState());
          callbleStatement.setString(8,accountAction.getCity());
          callbleStatement.setString(9,accountAction.getEngagementDetails());
          callbleStatement.setString(10,accountAction.getInsightDetails());
          //callbleStatement.setDate(11,DateUtility.getInstance().getMysqlDate(accountAction.getMeetingDate()));
          if(accountAction.getMeetingDate()!=null&&!"".equals(accountAction.getMeetingDate()))
          callbleStatement.setDate(11,DateUtility.getInstance().getMysqlDate(accountAction.getMeetingDate()));
                else
                callbleStatement.setDate(11,null);   
          callbleStatement.setString(12,accountAction.getStartTime());
          callbleStatement.setString(13,accountAction.getMidDayFrom());
          callbleStatement.setString(14,accountAction.getTimeZone());
          callbleStatement.setString(15,accountAction.getAdditionalComments());
          callbleStatement.setString(16,accountAction.getCreatedBy());
          
          if(accountAction.getOtherMeetingDate()!=null&&!"".equals(accountAction.getOtherMeetingDate()))
            callbleStatement.setDate(17,DateUtility.getInstance().getMysqlDate(accountAction.getOtherMeetingDate()));
          else
                 callbleStatement.setDate(17,null);  
          callbleStatement.setString(18,accountAction.getOtherStartTime());
          callbleStatement.setString(19,accountAction.getOtherMidDayFrom());
          callbleStatement.setString(20,accountAction.getOtherTimeZone());
          callbleStatement.setString(21,accountAction.getRequestStage());
          callbleStatement.setString(22,accountAction.getRequestStatus());
          callbleStatement.setString(23,accountAction.getClientType());
          callbleStatement.setString(24,accountAction.getCustomerName());
          callbleStatement.setString(25,accountAction.getOpportunityName());
           callbleStatement.setInt(26,accountAction.getBdmId());
             callbleStatement.setDouble(27,accountAction.getRevenues());
           callbleStatement.setInt(28,accountAction.getNoOfEmployees());
           
					 callbleStatement.setString(29,accountAction.getRequestType());
            callbleStatement.setInt(30,accountAction.getRegionalMgrId());
          callbleStatement.registerOutParameter(31,Types.INTEGER);
            
          updatedRows=  callbleStatement.executeUpdate();
            
         
                accountAction.setRequestId(callbleStatement.getInt(31));
       //   callbleStatement.registerOutParameter(29,Types.INTEGER);
            
        //  updatedRows=  callbleStatement.executeUpdate();
            
         
            //    accountAction.setRequestId(callbleStatement.getInt(29));
				
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(callbleStatement!=null){
                    callbleStatement.close();
                    callbleStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return updatedRows;
    }
    
    
    
    public void getClientEngagementDetails(AccountAction accountAction) throws ServiceLocatorException{
         int updatedRows = 0;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<String>();
        try{
            connection = ConnectionProvider.getInstance().getConnection();
          //  preparedStatement = connection.prepareStatement("SELECT tblPSCER.Id AS Id,RequestorId,NAME AS CustomerName,tblPSCER.AccountId AS AccountId,OpportunityId,RvpName,Stage,ReqStatus,MeetingType, tblPSCER.State AS State,tblPSCER.City,LevelOfEngagement,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,Comments,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,Stage,ReqStatus,ResourceIds,AccountType,AccountName,OppurtunityName FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id WHERE tblPSCER.Id="+accountAction.getRequestId());
            // preparedStatement = connection.prepareStatement("SELECT tblPSCER.Id AS Id,RequestorId,NAME AS CustomerName,tblPSCER.AccountId AS AccountId,OpportunityId,RvpName,Stage,ReqStatus,MeetingType, tblPSCER.State AS State,tblPSCER.City,LevelOfEngagement,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,Comments,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,Stage,ReqStatus,ResourceIds,AccountType,AccountName,OppurtunityName,BDMId FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id WHERE tblPSCER.Id="+accountAction.getRequestId());
            //  preparedStatement = connection.prepareStatement("SELECT tblPSCER.Id AS Id,RequestorId,NAME AS CustomerName,tblPSCER.AccountId AS AccountId,OpportunityId,RvpName,Stage,ReqStatus,MeetingType, tblPSCER.State AS State,tblPSCER.City,LevelOfEngagement,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,Comments,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,Stage,ReqStatus,ResourceIds,AccountType,AccountName,OppurtunityName,BDMId,tblPSCER.Revenues,tblPSCER.NoOfEmployees FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id WHERE tblPSCER.Id="+accountAction.getRequestId());
             preparedStatement = connection.prepareStatement("SELECT tblPSCER.Id AS Id,RequestorId,NAME AS CustomerName,tblPSCER.AccountId AS AccountId,OpportunityId,RvpName,Stage,ReqStatus,MeetingType, tblPSCER.State AS State,tblPSCER.City,LevelOfEngagement,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,Comments,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,Stage,ReqStatus,ResourceIds,AccountType,AccountName,OppurtunityName,BDMId,tblPSCER.Revenues,tblPSCER.NoOfEmployees,tblPSCER.RequestType,tblPSCER.RegionalMgrId FROM tblPSCER LEFT OUTER JOIN tblCrmAccount ON tblPSCER.AccountId = tblCrmAccount.Id WHERE tblPSCER.Id="+accountAction.getRequestId());
            resultSet=  preparedStatement.executeQuery();
          if(resultSet.next()){
              
              
            //   accountAction.setRequestorId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                 accountAction.setRequestorName(DataSourceDataProvider.getInstance().getFullNameBYLoginId(resultSet.getString("RequestorId")));
                 
              accountAction.setRequestId(resultSet.getInt("Id"));
              accountAction.setRequestorId(resultSet.getString("RequestorId"));
              if("I".equals( resultSet.getString("AccountType")))
              accountAction.setCustomerName(resultSet.getString("CustomerName"));
              else
                accountAction.setCustomerName(resultSet.getString("AccountName"));
              
              accountAction.setConsultantId(resultSet.getInt("AccountId"));
              accountAction.setOpportunityId(resultSet.getInt("OpportunityId"));
              accountAction.setRvpName(resultSet.getString("RvpName"));
              //accountAction.setMeetingStatus(resultSet.getString("MeetingStatus"));
              accountAction.setMeetingType(resultSet.getString("MeetingType"));
              accountAction.setState(resultSet.getString("State"));
              accountAction.setCity(resultSet.getString("City"));
              accountAction.setLevelEngagement(resultSet.getString("LevelOfEngagement"));
              accountAction.setEngagementDetails(resultSet.getString("EngagementDetails"));
              accountAction.setInsightDetails(resultSet.getString("InSightDetails"));
               if(resultSet.getDate("MeetingDate")!=null)
              accountAction.setMeetingDate(DateUtility.getInstance().convertDateToView(resultSet.getDate("MeetingDate")));
               
              accountAction.setStartTime(resultSet.getString("MeetingTime"));
              accountAction.setMidDayFrom(resultSet.getString("MidDay"));
              //accountAction.setOtherMeetingSlots(resultSet.getString("AltMeetingSlot"));
              accountAction.setTimeZone(resultSet.getString("TimeZone"));
              accountAction.setAdditionalComments(resultSet.getString("Comments"));
//              /OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone
              if(resultSet.getDate("OtherMeetingDate")!=null)
              accountAction.setOtherMeetingDate(DateUtility.getInstance().convertDateToView(resultSet.getDate("OtherMeetingDate")));
              accountAction.setOtherStartTime(resultSet.getString("OtherStartTime"));
              accountAction.setOtherMidDayFrom(resultSet.getString("OtherMidDayFrom"));
              //accountAction.setOtherMeetingSlots(resultSet.getString("AltMeetingSlot"));
              accountAction.setOtherTimeZone(resultSet.getString("OtherTimeZone"));
              accountAction.setRequestStage(resultSet.getString("Stage"));
              accountAction.setRequestStatus(resultSet.getString("ReqStatus"));
                StringTokenizer str = new StringTokenizer(resultSet.getString("ResourceIds"), ",");
                while (str.hasMoreTokens()) {
                    list.add(str.nextToken());
                }
                accountAction.setRequestResourcesList(list);
                accountAction.setResourcesHidden(resultSet.getString("ResourceIds"));
                accountAction.setClientType(resultSet.getString("AccountType"));
                accountAction.setOpportunityName(resultSet.getString("OppurtunityName"));
                 accountAction.setBdmId(resultSet.getInt("BDMId"));	
//              /MeetingTime,MidDay,AltMeetingSlot,TimeZone,Comments 
                 
                   accountAction.setRevenues(resultSet.getDouble("Revenues"));	
                 accountAction.setNoOfEmployees(resultSet.getInt("NoOfEmployees"));
                 
                  
  accountAction.setRequestType(resultSet.getString("RequestType"));
                 accountAction.setPreviousResourceIds(resultSet.getString("ResourceIds"));
                 
                 accountAction.setRegionalMgrId(resultSet.getInt("RegionalMgrId"));
                 
          }
          
          
            
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        
    }
   /* public int doClientReqEngagementUpdate(AccountAction accountAction) throws ServiceLocatorException{
         int updatedRows = 0;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
          //  preparedStatement = connection.prepareStatement("UPDATE tblPSCER SET RequestorId=?,AccountId=?,OpportunityId=?,RvpName=?,MeetingType=?,LevelOfEngagement=?,State=?,City=?,EngagementDetails=?,InSightDetails=?,MeetingDate=?,MeetingTime=?,MidDay=?,TimeZone=?,Comments=?,ModifiedBy=?,ModifiedDate=?,OtherMeetingDate=?,OtherStartTime=?,OtherMidDayFrom=?,OtherTimeZone=?,Stage=?,ReqStatus=?,ResourceIds=?,AccountType=?,AccountName=?,OppurtunityName=?,BDMId=? WHERE Id=?");
            
   preparedStatement = connection.prepareStatement("UPDATE tblPSCER SET RequestorId=?,AccountId=?,OpportunityId=?,RvpName=?,MeetingType=?,LevelOfEngagement=?,State=?,City=?,EngagementDetails=?,InSightDetails=?,MeetingDate=?,MeetingTime=?,MidDay=?,TimeZone=?,Comments=?,ModifiedBy=?,ModifiedDate=?,OtherMeetingDate=?,OtherStartTime=?,OtherMidDayFrom=?,OtherTimeZone=?,Stage=?,ReqStatus=?,ResourceIds=?,AccountType=?,AccountName=?,OppurtunityName=?,BDMId=?,Revenues=?,NoOfEmployees=? WHERE Id=?");
          preparedStatement.setString(1,accountAction.getRequestorId());
          preparedStatement.setInt(2,accountAction.getConsultantId());
          preparedStatement.setInt(3,accountAction.getOpportunityId());
          preparedStatement.setString(4,accountAction.getRvpName());
          preparedStatement.setString(5,accountAction.getMeetingType());
          //preparedStatement.setString(6,accountAction.getMeetingStatus());
          preparedStatement.setString(6,accountAction.getLevelEngagement());
          preparedStatement.setString(7,accountAction.getState());
          preparedStatement.setString(8,accountAction.getCity());
          preparedStatement.setString(9,accountAction.getEngagementDetails());
          preparedStatement.setString(10,accountAction.getInsightDetails());
          preparedStatement.setDate(11,DateUtility.getInstance().getMysqlDate(accountAction.getMeetingDate()));
          preparedStatement.setString(12,accountAction.getStartTime());
          preparedStatement.setString(13,accountAction.getMidDayFrom());
        //  preparedStatement.setString(15,accountAction.getOtherMeetingSlots());
          preparedStatement.setString(14,accountAction.getTimeZone());
          preparedStatement.setString(15,accountAction.getAdditionalComments());
          preparedStatement.setString(16,accountAction.getCreatedBy());
          preparedStatement.setTimestamp(17,DateUtility.getInstance().getCurrentMySqlDateTime());
          if(accountAction.getOtherMeetingDate()!=null&&!"".equals(accountAction.getOtherMeetingDate()))
            preparedStatement.setDate(18,DateUtility.getInstance().getMysqlDate(accountAction.getOtherMeetingDate()));
          else
                 preparedStatement.setDate(18,null);  
            //preparedStatement.setDate(19,DateUtility.getInstance().getMysqlDate(accountAction.getOtherMeetingDate()));
          preparedStatement.setString(19,accountAction.getOtherStartTime());
          preparedStatement.setString(20,accountAction.getOtherMidDayFrom());
          preparedStatement.setString(21,accountAction.getOtherTimeZone());
           preparedStatement.setString(22,accountAction.getRequestStage());
          preparedStatement.setString(23,accountAction.getRequestStatus());
          preparedStatement.setString(24,accountAction.getResourcesHidden());
           preparedStatement.setString(25,accountAction.getClientType());
          preparedStatement.setString(26,accountAction.getCustomerName());
          preparedStatement.setString(27,accountAction.getOpportunityName());
          preparedStatement.setInt(28,accountAction.getBdmId());
        			 
	    preparedStatement.setDouble(29,accountAction.getRevenues());
          preparedStatement.setInt(30,accountAction.getNoOfEmployees());
          
          preparedStatement.setInt(31,accountAction.getRequestId());
          updatedRows=  preparedStatement.executeUpdate();
            
              if(updatedRows>0){
             if(Properties.getProperty("Mail.Flag").equals("1")) {
                
                 if(!accountAction.getRequestStage().equals("Creation")){
                           //   MailManager sendMail = new MailManager();
                   
                     String comments="--";
                     if(accountAction.getAdditionalComments()!=null && !"".equals(accountAction.getAdditionalComments().trim())){
                     comments=accountAction.getAdditionalComments();
                     }
                     String loginId="";
                     if(!accountAction.getPreRequestStage().equals(accountAction.getRequestStage())){
                     accountAction.setUserName("");
                     }
                        MailManager.sendPSCERStatusMailsToSalesAndPreSales(accountAction.getPreRequestStage(),accountAction.getRequestStage(),accountAction.getRequestorId(),comments,accountAction.getUserName());
                 }
                        }
               }


        }catch (SQLException se){
            throw new ServiceLocatorException(se);
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return updatedRows;
    }
    
    */
     public int doClientReqEngagementUpdate(AccountAction accountAction) throws ServiceLocatorException{
         int updatedRows = 0;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("UPDATE tblPSCER SET RequestorId=?,AccountId=?,OpportunityId=?,RvpName=?,MeetingType=?,LevelOfEngagement=?,State=?,City=?,EngagementDetails=?,InSightDetails=?,MeetingDate=?,MeetingTime=?,MidDay=?,TimeZone=?,Comments=?,ModifiedBy=?,ModifiedDate=?,OtherMeetingDate=?,OtherStartTime=?,OtherMidDayFrom=?,OtherTimeZone=?,Stage=?,ReqStatus=?,ResourceIds=?,AccountType=?,AccountName=?,OppurtunityName=?,BDMId=?,Revenues=?,NoOfEmployees=?,RequestType=?,RegionalMgrId=? WHERE Id=?");
          preparedStatement.setString(1,accountAction.getRequestorId());
          preparedStatement.setInt(2,accountAction.getConsultantId());
          preparedStatement.setInt(3,accountAction.getOpportunityId());
          preparedStatement.setString(4,accountAction.getRvpName());
          preparedStatement.setString(5,accountAction.getMeetingType());
          //preparedStatement.setString(6,accountAction.getMeetingStatus());
          preparedStatement.setString(6,accountAction.getLevelEngagement());
          preparedStatement.setString(7,accountAction.getState());
          preparedStatement.setString(8,accountAction.getCity());
          preparedStatement.setString(9,accountAction.getEngagementDetails());
          preparedStatement.setString(10,accountAction.getInsightDetails());
                if(accountAction.getMeetingDate()!=null&&!"".equals(accountAction.getMeetingDate()))
          preparedStatement.setDate(11,DateUtility.getInstance().getMysqlDate(accountAction.getMeetingDate()));
                else
                preparedStatement.setDate(11,null);    
          preparedStatement.setString(12,accountAction.getStartTime());
          preparedStatement.setString(13,accountAction.getMidDayFrom());
        //  preparedStatement.setString(15,accountAction.getOtherMeetingSlots());
          preparedStatement.setString(14,accountAction.getTimeZone());
          preparedStatement.setString(15,accountAction.getAdditionalComments());
          preparedStatement.setString(16,accountAction.getCreatedBy());
          preparedStatement.setTimestamp(17,DateUtility.getInstance().getCurrentMySqlDateTime());
          if(accountAction.getOtherMeetingDate()!=null&&!"".equals(accountAction.getOtherMeetingDate()))
            preparedStatement.setDate(18,DateUtility.getInstance().getMysqlDate(accountAction.getOtherMeetingDate()));
          else
                 preparedStatement.setDate(18,null);  
            //preparedStatement.setDate(19,DateUtility.getInstance().getMysqlDate(accountAction.getOtherMeetingDate()));
          preparedStatement.setString(19,accountAction.getOtherStartTime());
          preparedStatement.setString(20,accountAction.getOtherMidDayFrom());
          preparedStatement.setString(21,accountAction.getOtherTimeZone());
          
          //  System.out.println("accountAction.getRequestStage()"+accountAction.getRequestStage());
           preparedStatement.setString(22,accountAction.getRequestStage());
          preparedStatement.setString(23,accountAction.getRequestStatus());
          //  System.out.println("accountAction.getResourcesHidden()"+accountAction.getResourcesHidden());
          preparedStatement.setString(24,accountAction.getResourcesHidden());
           preparedStatement.setString(25,accountAction.getClientType());
          preparedStatement.setString(26,accountAction.getCustomerName());
          preparedStatement.setString(27,accountAction.getOpportunityName());
          preparedStatement.setInt(28,accountAction.getBdmId());
          preparedStatement.setDouble(29,accountAction.getRevenues());
          preparedStatement.setInt(30,accountAction.getNoOfEmployees());
          preparedStatement.setString(31,accountAction.getRequestType());
          preparedStatement.setInt(32,accountAction.getRegionalMgrId());
          preparedStatement.setInt(33,accountAction.getRequestId());
          updatedRows=  preparedStatement.executeUpdate();
            
              if(updatedRows>0){
             if(Properties.getProperty("Mail.Flag").equals("1")) {
                
                 if(!accountAction.getRequestStage().equals("Creation")){
                           //   MailManager sendMail = new MailManager();
                   
                     String comments="--";
                     if(accountAction.getAdditionalComments()!=null && !"".equals(accountAction.getAdditionalComments().trim())){
                     comments=accountAction.getAdditionalComments();
                     }
                     String loginId="";
                     if(!accountAction.getPreRequestStage().equals(accountAction.getRequestStage())){
                     accountAction.setUserName("");
                     }
                     
                     String preSalesEmails="";
                     Map preSalesEmpDetailsMap=new TreeMap();
                     Map preSalesEmpDetailsMapForOnetimeEmail=new TreeMap();
                     if(accountAction.getResourcesHidden()!=null && !"".equals(accountAction.getResourcesHidden().trim())){
                     
                         preSalesEmails=DataSourceDataProvider.getInstance().getEmailIdsForLoginIds(accountAction.getResourcesHidden().trim());
                         preSalesEmpDetailsMap=DataSourceDataProvider.getInstance().getEmpDetailsLoginIds(accountAction.getResourcesHidden().trim());
                         
                         String oneTimeLoginIds = "";
                         if(!accountAction.getPreviousResourceIds().equalsIgnoreCase(accountAction.getResourcesHidden())){
                        String[] resourceArr = accountAction.getResourcesHidden().split(",");
                        String[] previousResourceIdsArr = accountAction.getPreviousResourceIds().split(",");
                            
                        for(int i=0;i<resourceArr.length;i++){
                          int k=0;
                           for(int j=0;j<previousResourceIdsArr.length;j++){
                                if(resourceArr[i].equalsIgnoreCase(previousResourceIdsArr[j]))
                                           k++;
                                        }
                                                 if(k==0){
                                           oneTimeLoginIds= oneTimeLoginIds+resourceArr[i]+",";
                                                   }

                                           }


                           if(!"".equals(oneTimeLoginIds)){
preSalesEmpDetailsMapForOnetimeEmail=DataSourceDataProvider.getInstance().getEmpDetailsLoginIds(oneTimeLoginIds.trim().substring(0, oneTimeLoginIds.length()-1));
                                  }
                        
                        
                         }
                         
                     }
                     
                     String practiceSalesEmailId = DataSourceDataProvider.getInstance().getEmailIdForEmployee(accountAction.getRegionalMgrId());
                     if(!"".equals(preSalesEmails))
                     preSalesEmails = preSalesEmails+","+practiceSalesEmailId;
                     else
                         preSalesEmails = practiceSalesEmailId;
                     
                     if(accountAction.getRequestType().equalsIgnoreCase("PSCER")){
                         MailManager.sendPSCERStatusMailsToSalesAndPreSales(accountAction.getPreRequestStage(),accountAction.getRequestStage(),accountAction.getRequestorId(),comments,accountAction.getUserName(),preSalesEmails);
                     
                     }
                     else if(accountAction.getRequestType().equalsIgnoreCase("RFP")){ 
                         MailManager.sendRFPStatusMailsToSalesAndPreSales(accountAction.getPreRequestStage(),accountAction.getRequestStage(),accountAction.getRequestorId(),comments,accountAction.getUserName(),preSalesEmails);
                 }
//                 String RequestorName=DataSourceDataProvider.getInstance().getFname_Lname(accountAction.getRequestorId().trim());
                 if(preSalesEmpDetailsMap.size()>0){
                     if(preSalesEmpDetailsMapForOnetimeEmail.size()>0){
                         Iterator iterator= preSalesEmpDetailsMap.entrySet().iterator();
                         String presalesNames="";
                while(iterator.hasNext()) {
                    Map.Entry entry =(Map.Entry)iterator.next();
              //      System.out.println(" entries= "+entry.getKey().toString());
              //      System.out.println(" entries= "+entry.getValue().toString());
                   presalesNames=presalesNames+entry.getValue().toString()+",";
                    
                }
                      
                     
                     Iterator iterator1= preSalesEmpDetailsMapForOnetimeEmail.entrySet().iterator();
                while(iterator1.hasNext()) {
                    Map.Entry entry =(Map.Entry)iterator1.next();
                    String toEmail=entry.getKey().toString();
                    String toName=entry.getValue().toString();
                      
                      MailManager.sendPSCER_RFP_StatusMailsToPreSales(toEmail,toName,accountAction.getRequestorId().trim(),accountAction.getCustomerName(),accountAction.getRequestStage(),comments,accountAction.getUserName(),accountAction.getMeetingType(),presalesNames.substring(0, presalesNames.length()-1),accountAction.getRequestType(),accountAction.getMeetingDate(), accountAction.getStartTime()+" "+accountAction.getMidDayFrom()+" "+accountAction.getOtherTimeZone());
                 
                    
                }
                
                     
                   
                 }
                 }
                 }
                        }
               }


        }catch (SQLException se){
            throw new ServiceLocatorException(se);
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return updatedRows;
    }
    
    
    public int doClientReqEngagementUpdateByStatus(int requestId,String previousStage,String currentStage) throws ServiceLocatorException{
         int updatedRows = 0;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
//            if("Rejected".equals(previousStage)&&"Submitted".equals(currentStage)){
//                  preparedStatement = connection.prepareStatement("INSERT INTO tblPSCER (RequestorId,AccountType,AccountId,AccountName,OppurtunityName,OpportunityId,RvpName,Stage,ReqStatus,MeetingStatus,MeetingType,State,City,LevelOfEngagement,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,ResourceIds,Comments,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate,RecordType,RequestId,BDMId) SELECT RequestorId,AccountType,AccountId,AccountName,OppurtunityName,OpportunityId,RvpName,'Creation',ReqStatus,MeetingStatus,MeetingType,State,City,LevelOfEngagement,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,ResourceIds,Comments,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate,0,Id,BDMId FROM tblPSCER WHERE Id = "+requestId);
//            }else {
//                  preparedStatement = connection.prepareStatement("INSERT INTO tblPSCER (RequestorId,AccountType,AccountId,AccountName,OppurtunityName,OpportunityId,RvpName,Stage,ReqStatus,MeetingStatus,MeetingType,State,City,LevelOfEngagement,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,ResourceIds,Comments,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate,RecordType,RequestId,BDMId) SELECT RequestorId,AccountType,AccountId,AccountName,OppurtunityName,OpportunityId,RvpName,Stage,ReqStatus,MeetingStatus,MeetingType,State,City,LevelOfEngagement,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,ResourceIds,Comments,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate,0,Id,BDMId FROM tblPSCER WHERE Id = "+requestId);
//            }
            if("Rejected".equals(previousStage)&&"Submitted".equals(currentStage)){
                  preparedStatement = connection.prepareStatement("INSERT INTO tblPSCER (RequestorId,AccountType,AccountId,AccountName,OppurtunityName,OpportunityId,RvpName,Stage,ReqStatus,MeetingStatus,MeetingType,State,City,LevelOfEngagement,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,ResourceIds,Comments,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate,RecordType,RequestId,BDMId,Revenues,NoOfEmployees,RequestType,RegionalMgrId) SELECT RequestorId,AccountType,AccountId,AccountName,OppurtunityName,OpportunityId,RvpName,'Creation',ReqStatus,MeetingStatus,MeetingType,State,City,LevelOfEngagement,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,ResourceIds,Comments,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate,0,Id,BDMId,Revenues,NoOfEmployees,RequestType,RegionalMgrId FROM tblPSCER WHERE Id = "+requestId);
            }else {
                  preparedStatement = connection.prepareStatement("INSERT INTO tblPSCER (RequestorId,AccountType,AccountId,AccountName,OppurtunityName,OpportunityId,RvpName,Stage,ReqStatus,MeetingStatus,MeetingType,State,City,LevelOfEngagement,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,ResourceIds,Comments,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate,RecordType,RequestId,BDMId,Revenues,NoOfEmployees,RequestType,RegionalMgrId) SELECT RequestorId,AccountType,AccountId,AccountName,OppurtunityName,OpportunityId,RvpName,Stage,ReqStatus,MeetingStatus,MeetingType,State,City,LevelOfEngagement,EngagementDetails,InSightDetails,MeetingDate,MeetingTime,MidDay,TimeZone,OtherMeetingDate,OtherStartTime,OtherMidDayFrom,OtherTimeZone,ResourceIds,Comments,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate,0,Id,BDMId,Revenues,NoOfEmployees,RequestType,RegionalMgrId FROM tblPSCER WHERE Id = "+requestId);
            }
        
          updatedRows=  preparedStatement.executeUpdate();
            
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return updatedRows;
    }
      public int doClientReqEngagementDeleteByStatus(int requestId) throws ServiceLocatorException{
         int updatedRows = 0;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
          preparedStatement = connection.prepareStatement("DELETE FROM  tblPSCER WHERE RequestId="+requestId+" AND Id!="+requestId);
        
          updatedRows=  preparedStatement.executeUpdate();
            
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return updatedRows;
    }
	
	
    
}
