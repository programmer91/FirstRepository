/*
 * @(#)GeneralServiceImpl.java 1.0 Nov 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */

package com.mss.mirage.general;

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
public class GeneralServiceImpl implements GeneralService{
    /** Creates a new instance of GeneralServiceIml */
    public GeneralServiceImpl() {
    }
    
    /**
     *addUser(RegistrationAction registrationAction) method is used to add user into MirageV2.
     *@param registrationAction is sending as parameter in this method.
     * in this method user loginId and password are generating
     */
    public boolean  addUser(RegistrationAction registrationAction) throws ServiceLocatorException {
        
        /* @param isAddUser is used to store boolean value*/
        boolean isAddUser = false;
        
        /* @param mailId of whom to send mail regarding registered candidate details*/
        String sendMailId=null;
        
        /*PasswordUtility is class it is used for encrypt and dycrypt a password */
        PasswordUtility passwordUtil = new PasswordUtility();
        
        
        
        /**generatedPassword is a method in GeneralService.java class
         * this is used to generate automated password of length 8 chars
         */
        String  generatedPassword = generatePassword(8);
        
        /*Here that 8 char password encrypted and that is set to registrationAction */
        registrationAction.setPassword(passwordUtil.encryptPwd(generatedPassword));
        
        /* here userId is generated according to office email*/
        registrationAction.setLoginId(generateUserId(registrationAction.getOfficeEmail()));
        
        Session session = HibernateServiceLocator.getInstance().getSession();
        
      //  System.out.println("registrationAction.getCountry()"+registrationAction.getCountry());
        
        if(registrationAction.getCountry().equals("India")) {
            registrationAction.setOrgId("Miracle Software Systems(India), Pvt. Ltd");
            registrationAction.setWorkingCountry("India");
            sendMailId=Properties.getProperty("Registered.India");
        }
        if(registrationAction.getCountry().equals("USA")) {
            registrationAction.setOrgId("Miracle Software Systems(USA).Inc");
            registrationAction.setWorkingCountry("USA");
            sendMailId=Properties.getProperty("Registered.Usa");
        }
        Transaction tran=session.beginTransaction();
        session.save(registrationAction);
        tran.commit();
        
        
        try{
            // Closing hibernate session
            session.close();
            session = null;
             if(sendMailId != null && !sendMailId.equals("")){
            
            //System.out.println("sendMailId---"+sendMailId);
                  if(Properties.getProperty("Mail.Flag").equals("1")) {
            MailManager sendMail = new MailManager();        
            sendMail.sendRegisteredCandidateDetails(registrationAction.getLoginId(),registrationAction.getFirstName(),registrationAction.getLastName(),registrationAction.getCountry(),sendMailId);    
                  }
        }
        }catch(HibernateException he){
            throw new ServiceLocatorException(he);
        }catch(NullPointerException nex){
            throw new ServiceLocatorException(nex);
        }catch(Exception ex){
            throw new ServiceLocatorException(ex);
        }
        finally{
            if(session!=null){
                session.close();
                session = null;
            }
        }
       
        
        isAddUser = true;
        
        return isAddUser;
        
    }
    
    /**
     * generateUserId(String mailId) method is mainly for generating userId,
     * @param mailId sending as String into this mehtod
     */
    public String generateUserId(String mailId) {
        
        /*@param atOccurance is used to store index of mailId upto @ char*/
        int atOccurance = mailId.indexOf("@");
        
        /*finally those string return here*/
        return mailId.substring(0,atOccurance).toLowerCase();
    }
    
    /**
     *generatePassword(int noOfCharacters) method is used for generate automated String
     *@param noOfCharacters used to int value
     */
    public String generatePassword(int noOfCharacters) {
        
        /*@param generatedPwd used to store String*/
        String generatedPwd = "";
        
        int randInt;
        
        /**
         *Random() method Creates a new random number generator
         *currentTimeMillis() method Returns the current value of the most precise available system
         *timer, in nanoseconds.
         */
        Random random = new Random(System.currentTimeMillis());
        
                /*
                 *the next pseudorandom, uniformly distributed value from this random number generator's sequence.
                 *and that is stored in randomOne and randomTwo
                 */
        long randomOne = random.nextLong();
        long randomTwo = random.nextLong();
        
        /**
         *toHexString() method return the string representation of the unsigned
         *value represented by the argument in hexadecimal
         *that String stored in hashCodeOne and hashCodeTwo
         */
        String hashCodeOne = Long.toHexString(randomOne);
        String hashCodeTwo = Long.toHexString(randomTwo);
        
        /*@param generatedPwd used to store concatinate string of hashCodeOne and hashCodeTwo*/
        generatedPwd = hashCodeOne + hashCodeTwo;
        
        if(generatedPwd.length()>noOfCharacters) {
            generatedPwd = generatedPwd.substring(0,noOfCharacters);
        }
        
        return generatedPwd.toUpperCase();
    }
    
    
    
    public boolean userCheckExist(RegistrationAction registrationAction) throws ServiceLocatorException {
        boolean isUserExist= false;
        
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction tran=session.beginTransaction();
        String SQL_STR="select ra.loginId from RegistrationAction ra";
        Query query= session.createQuery(SQL_STR);
        String userId= generateUserId(registrationAction.getOfficeEmail());
        for(Iterator it=query.iterate();it.hasNext();){
            String loginId = (String) it.next();
            if(loginId.toString().equalsIgnoreCase(userId)){
                isUserExist= true;
                break;
            }
        }
        session.close();
        session = null;
        
        return isUserExist;
    }
    
    /**
     *getPrimaryAction(int roleId) method is used to get actions according to
     *roleId ,for generating leftmenu.
     *@param roleId is sending as int into the method
     * it returns primary actions
     */
    public String getPrimaryAction(int roleId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        /*@param primaryAction used to store actions*/
        String primaryAction= null;
        
        String queryString = "SELECT Action FROM vwLeftMenu WHERE RoleId="+roleId+" AND primaryFlag=1";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                primaryAction = resultSet.getString("Action");
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
        
        return primaryAction;
    }
    
    /**
     *regetPassword(PasswordAction passwordAction) this method is used for getting password
     *user fill the details in forgotPasswor.jsp and click on submit button then this method
     *will be called.
     */
    public boolean regetPassword(PasswordAction passwordAction) throws ServiceLocatorException {
        
        /*@param isReget is used to store boolean value false*/
        boolean isReget=false;
        
        /*@param dbPassword is used to store password of the user*/
        Object dbPassword=null;
        
        /*@param userName is used to store username of the user*/
        Object userName=null;
        
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction tran=session.beginTransaction();
        String SQL_QUERY ="SELECT p.oldPassword,p.userName FROM RegetPasswordData as p" +
                " WHERE p.loginId=:loginId AND p.prefQuestionId=:prefQuestion AND p.prefAnswer=:prefAnswer";
        
        Query query = session.createQuery(SQL_QUERY).setString("loginId",passwordAction.getLoginId().trim()).setInteger("prefQuestion",passwordAction.getPrefQuestionId()).setString("prefAnswer",passwordAction.getPrefAnswer().trim());
        
        /* getting data from database  */
        for(Iterator it=query.iterate();it.hasNext();){
            Object[] row = (Object[]) it.next();
            dbPassword=(Object)row[0];
            userName=(Object)row[1];
            
        }
        /*checking the condition and deycrypting the password to send user*/
        if(dbPassword !=null && userName != null){
            PasswordUtility passwordUtility=new PasswordUtility();
            passwordAction.setNewPassword(passwordUtility.decryptPwd(dbPassword.toString()));
            passwordAction.setUserName(userName.toString());
             if(Properties.getProperty("Mail.Flag").equals("1")) {
            MailManager.sendUserIdPwd(passwordAction.getLoginId(),passwordAction.getUserName(),passwordAction.getNewPassword());
             }
            isReget=true;
            
        }
        
        session.close();
        session = null;
        return isReget;
    }
    
    
    public String getPassword(String loginId) throws ServiceLocatorException {
        String password = null;
        PasswordUtility passwordUtility = new PasswordUtility();
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select h.oldPassword from GetPasswordData as h where h.loginId=:loginId";
        
        Query query=session.createQuery(SQL_QUERY).setString("loginId",loginId);
        for(Iterator it=query.iterate();it.hasNext();){
            password = (String) it.next();
        }//end of the for loop
        
        if(password != null){
            password = passwordUtility.decryptPwd(password);
        }
        session.close();
        session = null;
        return password;
    }
    
//    /*updatePassword(PasswordAction passwordAction) this method is used to update password*/
//    public boolean updatePassword(PasswordAction passwordAction) throws ServiceLocatorException {
//
//        /*@param isUpdate is used to store boolean value false*/
//        boolean isUpdate= false;
//
//        /*@param password is used to store password of the user*/
//        String password=null;
//
//        PasswordUtility passwordUtility=new PasswordUtility();
//        Session session=HibernateServiceLocator.getInstance().getSession();
//        Transaction tran=session.beginTransaction();
//
//        //sql query
//
//        String SQL_QUERY ="select p.oldPassword,p.loginId from GetPasswordData as p where p.loginId=:loginId";
//        Query query=session.createQuery(SQL_QUERY).setString("loginId",passwordAction.getLoginId());
//
//        for(Iterator it=query.iterate();it.hasNext();){
//            Object row[]=(Object[])it.next();
//            // String temp=(String)row[0];
//            password=passwordUtility.decryptPwd(row[0].toString());
//
//
//        }//end of the for loop
//
//        /*here checking weather passwor exist or not ,if it exit the update will be done.*/
//        if(passwordAction.getOldPassword().equalsIgnoreCase(password)){
//
//            String encryptPass=passwordUtility.encryptPwd(passwordAction.getNewPassword());
//            String QUERY="update GetPasswordData p set p.oldPassword=:npwd where  p.loginId=:loginId";
//            int query1=session.createQuery(QUERY).setString("npwd",encryptPass).setString("loginId",passwordAction.getLoginId()).executeUpdate();
//            tran.commit();
//
//            isUpdate = true;
//
//        }
//        return isUpdate;
    
//    }
    
    
    public int updatePassword(PasswordAction passwordAction) throws ServiceLocatorException {
        /*@param isUpdate is used to store boolean value false*/
        int updatedRows = 0;
        /*@param password is used to store password of the user*/
        String password=null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        PasswordUtility passwordUtility=new PasswordUtility();
        String queryString = "SELECT LoginId,Password FROM tblEmployee WHERE LoginId='"+passwordAction.getLoginId()+"'";
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                password = resultSet.getString("Password");
            }
            
            
            password=passwordUtility.decryptPwd(password);
            
            if(statement != null){
                    statement.close();
                    statement = null;
            }
            /*here checking weather passwor exist or not ,if it exit the update will be done.*/
            if(passwordAction.getOldPassword().equalsIgnoreCase(password)){
                String encryptPass=passwordUtility.encryptPwd(passwordAction.getNewPassword());
                queryString = "UPDATE tblEmployee SET Password='"+encryptPass+"' WHERE LoginId='"+passwordAction.getLoginId()+"'";
                statement = connection.createStatement();
                updatedRows = statement.executeUpdate(queryString);
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
        return  updatedRows;
        
    }
  /*  public int updateCustPassword(PasswordAction passwordAction) throws ServiceLocatorException {
        
        int updatedRows = 0;
        
        String password=null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        PasswordUtility passwordUtility=new PasswordUtility();
        String queryString = "SELECT LoginId,Password FROM tblCrmContact WHERE LoginId='"+passwordAction.getCustLoginId()+"'";
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                password = resultSet.getString("Password");
            }
            
            
            password=passwordUtility.decryptPwd(password);
            
            
            if(passwordAction.getOldCustPassword().equalsIgnoreCase(password)){
                String encryptPass=passwordUtility.encryptPwd(passwordAction.getNewCustPassword());
                queryString = "UPDATE tblCrmContact SET Password='"+encryptPass+"' WHERE LoginId='"+passwordAction.getCustLoginId()+"'";
                statement = connection.createStatement();
                updatedRows = statement.executeUpdate(queryString);
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
        return  updatedRows;
        
    }*/
    public int updateCustPassword(PasswordAction passwordAction) throws ServiceLocatorException {
        /*@param isUpdate is used to store boolean value false*/
        int updatedRows = 0;
        /*@param password is used to store password of the user*/
        String password=null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        PasswordUtility passwordUtility=new PasswordUtility();
        String queryString = "SELECT LoginId,Password FROM tblCrmContact WHERE LoginId='"+passwordAction.getCustLoginId()+"'";
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                password = resultSet.getString("Password");
            }
            
            
            password=passwordUtility.decryptPwd(password);
            
            /*here checking weather passwor exist or not ,if it exit the update will be done.*/
            if(passwordAction.getOldCustPassword().equalsIgnoreCase(password)){
                String encryptPass=passwordUtility.encryptPwd(passwordAction.getNewCustPassword());
                queryString = "UPDATE tblCrmContact SET Password='"+encryptPass+"' WHERE LoginId='"+passwordAction.getCustLoginId()+"'";
                statement = connection.createStatement();
                updatedRows = statement.executeUpdate(queryString);
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
        return  updatedRows;
        
    }
    /*String getRoleName(int roleId) this method is used to get role names,and displaying in home page*/
    public String getRoleName(int roleId) throws ServiceLocatorException {
        
        /*@param roleName is used to store names of the roles*/
        String roleName = null;
        
        Session session = HibernateServiceLocator.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        
        String SQL_QUERY ="Select h.description from HomeAction as h where h.roleTypeId=:roleId";
        
        Query query=session.createQuery(SQL_QUERY).setInteger("roleId",roleId);
        for(Iterator it=query.iterate();it.hasNext();){
            roleName = (String) it.next();
        }//end of the for loop
        
        session.close();
        session = null;
        return roleName;
    }
    
    public int getUsableTeamHours(String usableDays, int holidays, int teamStreangth) throws  ServiceLocatorException {
        int updatedRows = 0;
        /*@param password is used to store password of the user*/
        String password=null;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("call spOne(?,?,?,?)");
            callableStatement.setInt(1,Integer.parseInt(usableDays));
            callableStatement.setInt(2,holidays);
            callableStatement.setInt(3,teamStreangth);
            callableStatement.registerOutParameter(4,Types.INTEGER);
            callableStatement.executeUpdate();
            
            updatedRows = callableStatement.getInt(4);
            
        }catch (SQLException se){
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return  updatedRows;
        
    }
    
    public int getUsedTeamHours() throws  ServiceLocatorException {
        int updatedRows = 0;
        /*@param password is used to store password of the user*/
        String password=null;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("call spTwo(?,?)");
            callableStatement.setInt(1,1);
            callableStatement.registerOutParameter(2,Types.INTEGER);
            callableStatement.executeUpdate();
            
            updatedRows = callableStatement.getInt(2);
            
        }catch (SQLException se){
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return  updatedRows;
    }
    
    public int getNaturalHolidayHours() throws  ServiceLocatorException {
        int updatedRows = 0;
        /*@param password is used to store password of the user*/
        String password=null;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("call spThree(?,?)");
            callableStatement.setInt(1,1);
            callableStatement.registerOutParameter(2,Types.INTEGER);
            callableStatement.executeUpdate();
            
            updatedRows = callableStatement.getInt(2);
            
        }catch (SQLException se){
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return  updatedRows;
    }
    
    public int getTeamLeaveHours() throws  ServiceLocatorException {
        int updatedRows = 0;
        /*@param password is used to store password of the user*/
        String password=null;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("call spFour(?,?)");
            callableStatement.setInt(1,1);
            callableStatement.registerOutParameter(2,Types.INTEGER);
            callableStatement.executeUpdate();
            
            updatedRows = callableStatement.getInt(2);
            
        }catch (SQLException se){
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
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return  updatedRows;
    }
    
  //new impl for forgot password
   
 public String regetPwd(String emailId,String eFlag) throws ServiceLocatorException{
        /*@param isReget is used to store boolean value false*/
        boolean isReget=false;
        int count = 0; 
        String queryString = null;        
        Connection connection = null;
        Statement statement = null;       
        ResultSet resultSet = null;
        String result=null;  
        DataSourceDataProvider dataSourceDataProvider = null;           
      // System.out.println("111--->"+eFlag);             
        
        String resultMessage = "";
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            
            
            
            String status = DataSourceDataProvider.getInstance().getStatusByEmailId(emailId, eFlag);
             if(!"nodata".equals(status))
            {
            if("Active".equalsIgnoreCase(status)){
            result = dataSourceDataProvider.getInstance().getEmpNameAndLoginIdByEmailId(emailId, eFlag);  
            if(!"nodata".equals(result))
            {
            String[] temp;             
            String delimiter="-";             
            temp=result.split(delimiter);
            String psw=generatePassword(4);
//            System.out.println("Password======>"+psw); 
//            System.out.println("LoginId======>"+temp[0]);
//            System.out.println("Email======>"+emailId);            
            PasswordUtility passwordUtility=new PasswordUtility();
            String psaw1= passwordUtility.encryptPwd(psw);            
            //System.out.println("Password======>"+psaw); 
            if(eFlag.equalsIgnoreCase("e")){
             queryString = "UPDATE tblEmployee SET Password='"+psaw1+"' WHERE Email1='"+emailId+"'";
            }else{
                queryString = "UPDATE tblCrmContact SET Password='"+psaw1+"' WHERE Email1='"+emailId+"'AND LoginId='"+temp[0]+"' AND iFlag=1";
               //queryString = "UPDATE queryString = "UPDATE tblCrmContactAlias SET Password='"+psaw1+"' WHERE Email='"+emailId+"'"; SET Password='"+psaw1+"' WHERE Email='"+emailId+"'";
                // queryString = "UPDATE tblCrmContact SET Password='"+psaw1+"' WHERE Email1='"+emailId+"'";                
            }            
           // System.out.println("queryString1======>"+queryString);  
            statement = connection.createStatement();
            int  updatedRows = statement.executeUpdate(queryString);            
            if(updatedRows>=1){            
             //System.out.println("result from dsdp-->"+result);         
             //System.out.println(temp[0]+"===temp[10-->"+temp[1]);                        
             MailManager sendMail = new MailManager();             
             sendMail.sendResetPwd(emailId,temp[0],temp[1],psw);
             isReget = true;
              resultMessage = "<font color=\"green\" size=\"1.5\">Congrats! We have sent the password to your Official EmailId. </font>";
            } else {
                 resultMessage = "<font color=\"red\" size=\"1.5\">Oops! Please try again. </font>";  
            }                        
    }else {
                 resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! The given email id is not associated with any account in Hubble.Please enter valid email id. </font>"; 
            }
            }else if("InActive".equalsIgnoreCase(status)){
               resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Your account is Inactive. </font>";  
            }else if("Registered".equalsIgnoreCase(status)){
               resultMessage = "<font color=\"red\" size=\"1.5\">Activation process will take 24hrs after Registration, So Please try to login after the stipulated time.</font>";  
            } else if("Long-Term Leave".equalsIgnoreCase(status)){
               resultMessage = "<font color=\"red\" size=\"1.5\">Oops! Your account is in Long-Term Leave.</font>";  
            }else {
               resultMessage = "<font color=\"red\" size=\"1.5\">Oops! Your account is not in Active So please contact Hr team for proper updation.</font>";  
            }  
            }else {
                 resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! The given email id is not associated with any account in Hubble.Please enter valid email id. </font>"; 
             }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                   // System.out.println("IN finally resultSet ");
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                   // System.out.println("IN finally statement ");
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                   // System.out.println("IN finally connection ");
                }                              
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
         return resultMessage;
    }
	



    
}
