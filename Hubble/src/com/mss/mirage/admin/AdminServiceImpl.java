/*
 * @(#)AdminServiceImpl.java	1.0 04/11/2007
 *
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.mss.mirage.admin;


import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.ServiceLocatorException;
import java.util.Iterator;
import java.util.Map;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.admin.AdminService;
import com.mss.mirage.util.ConnectionProvider;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import com.mss.mirage.admin.AdminRolesVTO;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
/**
 *
 * @author Arjun Sanapathi
 * @version 1.122, 05/05/04
 * @since   1.0
 */
public class AdminServiceImpl implements AdminService {
    
    /** Creates a new instance of AdminServiceImpl */
    
    /**
     *  The adminrole
     * object is useful to data persistance
     * @see com.mss.mirage.admin.AdminRolesVTO
     */
    
    private AdminRolesVTO adminroleVTO;
    
    /** The queryString is useful to get  queryString result to the particular jsp page */
    private String queryString;
    
    /** The statement is useful  to execute the resultSet object */
    private Statement statement;
    
    /** The statement is useful  to execute the above queryString */
    private ResultSet resultSet;
    
    /** The connection is object to  useful  to create the statement object */
    private Connection connection;
    
    /**
     * 
     * This method is useful to Get the employee Details
     * @param employeeId 
     * @return The AdminRolesVTO returned  depends on the employeeId.
     * @see com.mss.mirage.util.ServiceLocatorException.
     * @ com.mss.mirage.admin.AdminService#employeeDetails
     * {@link
     *          com.mss.mirage.admin.AdminService#empolyeeDetails()}
     * @throws ServiceLocatorException If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
     */
    public AdminRolesVTO employeeDetails(int employeeId) throws ServiceLocatorException {
        adminroleVTO = new AdminRolesVTO();
        
        /** The queryString is created  depends on the employeeId */
        
        queryString = "SELECT * FROM vwEmpRoleFlag WHERE EmpId="+employeeId;
        try{
            
            /**
             *   @return   connection Object
             *   @throws  SQLException
             *          If a SQLExceptionexists and its <code>{@link
             *          com.mss.mirage.util.ServiceLocatorException}</code>
             * @see com.mss.mirage.util.ServiceLocatorException.
             * {@link
             *          com.mss.mirage.util.ConnectionProvider#getConnection()}
             */
            
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            // set the Id in adminroleVTO
            adminroleVTO.setId(employeeId);
            // Loop Starts
            while(resultSet.next()){
                
                // Sets  the  value to corresponding setter methods of  adminroleVTO
                adminroleVTO.setLoginId(resultSet.getString("LoginId"));
                adminroleVTO.setPrimaryRole(resultSet.getString("RoleId"));
                
                adminroleVTO.setUserName(resultSet.getString("EmployeeName"));
                
                
            }
            
            
            
        }catch (SQLException se){
            throw new ServiceLocatorException(se);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!= null){
                    statement.close();
                    /*Releasing Statement Object*/
                    statement=null;
                }
                if(connection != null){
                    // If null Closes the Connection
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return  adminroleVTO;
    }
    
    
    /**
     * 
     * This method is useful to assign the roles to user
     * @param assignedRoleIds 
     * @param employeeId 
     * @param primaryRoleId 
     * @return the result of inserted rows in the database
     * @see com.mss.mirage.util.ServiceLocatorException.
     * 
     * {@link
     *          com.mss.mirage.admin.AdminService#insertRoles()}
     * @throws ServiceLocatorException If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
     */
    public int  insertRoles(String[] assignedRoleIds, int employeeId, int primaryRoleId) throws ServiceLocatorException {
        
        int insertedRows = 0;
        int updateRows=0;
        int deletedRows = 0;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            /** The queryString is created  depends on the employeeId */
            queryString = "DELETE FROM tblEmpRoles WHERE EmpId="+employeeId;
            deletedRows = statement.executeUpdate(queryString);
            statement.close();
            statement = null;
            statement = connection.createStatement();
            
            
            /**
             *   it loops the roles.length and inserts the data into database for each addedVals
             *
             *   @throws  NullPointerException
             *          If a NullPointerException exists and its <code>{@link
             *          java.lang.NullPointerException}</code>
             */
            for(int counter=0; counter<assignedRoleIds.length; counter++) {
                if(Integer.parseInt(assignedRoleIds[counter]) == primaryRoleId){
                    queryString = "Insert into tblEmpRoles(PrimaryFlag,EmpId,RoleId) values(1,"  + employeeId +  ", " + assignedRoleIds[counter] +")";
                }else{
                    queryString = "Insert into tblEmpRoles(primaryflag,empid,roleid) values(0,"  + employeeId +  ", " + assignedRoleIds[counter] +")";
                }
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
    
    
    /**
     * 
     * This method is useful to get The assigned roles Screen with user values
     * @param roles 
     * @param roleId 
     * @return the result of inserted rows in the database
     * @see com.mss.mirage.util.ServiceLocatorException.
     * @throws ServiceLocatorException If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
     */
    public int  insertRoleScreens(String[] roles,int roleId) throws ServiceLocatorException {
       // System.out.println("In IMPL-->"+roleId);
        int insertedRows = 0;
        int updateRows=0;
        int deletedRows = 0;
//        Connection twoConnection;
//        Statement twoStatement;
//        ResultSet  twoResultSet;
        int screenOrder=0;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            
            queryString = "DELETE FROM tblRoleScreens WHERE RoleId ="+roleId;
            deletedRows = statement.executeUpdate(queryString);
            
            /*Releasing Statement Object*/
            statement.close();
            statement = null;
            
            statement = connection.createStatement();
            
            /**
             *   it loops the addedVals.length and inserts the data into database for each addedVals
             *
             *   @throws  NullPointerException
             *          If a NullPointerException exists and its <code>{@link
             *          java.lang.NullPointerException}</code>
             */
            
            for(int addedVals=0;addedVals<roles.length;addedVals++) {
                
                /** If addedVals is equals to 0 then it inserts the PrimaryRole with 1 into the database **/
                if(addedVals==0) {
                    screenOrder = addedVals+1;
                    queryString = "insert into tblRoleScreens(primaryflag,roleid,ScreenId,ScreenOrder) values(1,"  + roleId +  ", " + roles[addedVals] +  "," + screenOrder + ")";
                    insertedRows = statement.executeUpdate(queryString);
                }
                
                /** If addedVals is not equals to 0 then it inserts the PrimaryRole with 0 into the database **/
                else {
                    screenOrder = addedVals+1;
                    queryString = "insert into tblRoleScreens(primaryflag,roleid,ScreenId,ScreenOrder) values(0,"  + roleId +  ", " + roles[addedVals] +  "," + screenOrder + ")";
                    insertedRows = statement.executeUpdate(queryString);
                }
            }
            
            
        }catch (Exception e){
            throw new ServiceLocatorException(e);
        }finally{
            try{
                if(statement!= null){
                    statement.close();
                    statement = null;
                }
                if(connection != null){
                    // Closes the Connection if connection is not null //
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
            
        }
        return insertedRows;
    }
    
    
    
    /**
     *  
     * This method is useful to get The assigned roles Screen
     * @param moduleId 
     * @param screenName 
     * @param screenAction 
     * @param screenTitle 
     * @return the result of insscreenId rows in the database
     * @see com.mss.mirage.util.ServiceLocatorException.
     * @throws ServiceLocatorException If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
     */
    
    public int  insertNewScreen(String moduleId,String screenName,String screenAction,String screenTitle) throws ServiceLocatorException {
        int iModduleId=0;
        iModduleId = Integer.parseInt(moduleId);
        int  insscreenId=0;
        CallableStatement cstmt = null;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            cstmt = connection.prepareCall("{ call   spNewScreenAdd(?,?,?,?,?)}");
            cstmt.setInt(1,iModduleId);
            cstmt.setString(2,screenName);
            cstmt.setString(3,screenAction);
            cstmt.setString(4,screenTitle);
            cstmt.registerOutParameter(5, Types.INTEGER);
            insscreenId = cstmt.executeUpdate();
            
            
        }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
        }finally{
            try{
                if(cstmt!=null){
                    cstmt.close();
                    cstmt = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
            
        }
        
        return  insscreenId;
        
    }
    
    
    
    /**
     * This method is useful to get the role names of a user
     * @param roleId 
     * @return The AdminRolesVTO returned  depends on the roleId.
     * @see com.mss.mirage.util.ServiceLocatorException.
     * @ com.mss.mirage.admin.AdminService#employeeDetails
     * {@link
     *          com.mss.mirage.admin.AdminService#getRoleName()}
     * @throws ServiceLocatorException If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
     */
    
    
    public AdminRolesVTO getRoleName(int roleId) throws ServiceLocatorException {
        adminroleVTO = new AdminRolesVTO();
        
        // queryString created depend on the roleId
        
        queryString = "SELECT * FROM tblLKRoles WHERE Id="+roleId;
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            /** Setting RoleId in setter method of adminroleVTO
             *		{@link
             * com.mss.mirage.admin.AdminRoleVTOe#setRoleId()}
             **/
            adminroleVTO.setRoleId(roleId);
            
            // Loop Starts
            while(resultSet.next()){
                // Setting Description in setter method of adminroleVTO
                adminroleVTO.setRoleName(resultSet.getString("Description"));
            }
            
        }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!= null){
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
        // Returns adminroleVTO
        return  adminroleVTO;
        
        
        
    } // Method ended of getRoleName
    
    /**
     * 
     * This method is useful to update the password of user
     * @param passwordAction 
     * @return the result of updated rows in the database
     * @see com.mss.mirage.util.ServiceLocatorException.
     * 
     * {@link
     *          com.mss.mirage.admin.AdminService#updatePassword()}
     * @throws ServiceLocatorException If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
     */
    
    
    public int updatePassword(AdminAction passwordAction) throws ServiceLocatorException {
        /*@param isUpdate is used to store boolean value false*/
        int updatedRows = 0;
        /*@param password is used to store password of the user*/
        String password=null;
        PasswordUtility passwordUtility=new PasswordUtility();
        connection = ConnectionProvider.getInstance().getConnection();
        try{
            
            /*here checking weather passwor equal or not ,if it equal the update will be done.*/
            if(passwordAction.getNewPassword().equalsIgnoreCase(passwordAction.getCnfPassword())){
                String encryptPass=passwordUtility.encryptPwd(passwordAction.getNewPassword());
                //System.out.println("Working Country------>"+passwordAction.getWorkingCountry());                               
                String userRoleName = passwordAction.getUserRoleName();
                //System.out.println("Working Country------>"+passwordAction.getUserRoleName());
                
                if("Admin".equals(userRoleName)) {
                                
                            queryString = "UPDATE tblEmployee SET Password='"+encryptPass+"' WHERE LoginId='"+passwordAction.getLoginId()+"'";
                            
                        }else {
                            queryString = "UPDATE tblEmployee SET Password='"+encryptPass+"' WHERE LoginId='"+passwordAction.getLoginId()+"' AND Country = '"+passwordAction.getWorkingCountry()+"'";
                        }
                //queryString = "UPDATE tblEmployee SET Password='"+encryptPass+"' WHERE LoginId='"+passwordAction.getLoginId()+"' AND Country = '"+passwordAction.getWorkingCountry()+"'";
                //System.out.println("queryString ------>"+queryString );
                statement = connection.createStatement();
                updatedRows = statement.executeUpdate(queryString);
            }
            
            
            
        }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
        }finally{
            try{
                if(statement!= null){
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
    
    
    public int updateCustPassword(AdminAction passwordAction) throws ServiceLocatorException
    {
     /*@param isUpdate is used to store boolean value false*/
        int updatedRows = 0;
        /*@param password is used to store password of the user*/
        String password=null;
        PasswordUtility passwordUtility=new PasswordUtility();
        connection = ConnectionProvider.getInstance().getConnection();
        try{
            
            /*here checking weather passwor equal or not ,if it equal the update will be done.*/
            if(passwordAction.getNewPassword().equalsIgnoreCase(passwordAction.getCnfPassword())){
                String encryptPass=passwordUtility.encryptPwd(passwordAction.getNewPassword());
                //System.out.println("Working Country------>"+passwordAction.getWorkingCountry());                               
                String userRoleName = passwordAction.getUserRoleName();
                //System.out.println("Working Country------>"+passwordAction.getUserRoleName());
                
                if("Admin".equalsIgnoreCase(userRoleName)||"Operations".equalsIgnoreCase(userRoleName)) {
                                
                            queryString = "UPDATE tblCrmContact SET Password='"+encryptPass+"' WHERE LoginId='"+passwordAction.getLoginId()+"'";
                            
                        }
                
                statement = connection.createStatement();
                updatedRows = statement.executeUpdate(queryString);
            }
            
            
            
        }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
        }finally{
            try{
                if(statement!= null){
                    statement.close();
                    statement = null;
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
        return  updatedRows;
    }

    public boolean checkSalesAgainstBdm(AdminAction adminAction) throws ServiceLocatorException {
    System.out.println("in impl of resourcename");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
 String strConsId = "";
 String responseString="";
          
         
     //       String strCategory = "";
        boolean isInserted = true;
        String teamMemberId = null;
        // INSERT INTO `mirage`.`tblStarPerformers` (`Date`,`Status`,`CreatedDate`,`CreatedBy`, `ModifiedBy`, `ModifiedDate`) VALUES(?,?,?,?,?,?)
      //  String queryString = "insert into tblStarPerformers(Date,Status,CreatedDate,CreatedBy,ModifiedBy,ModifiedDate) values(?,?,?,?,?,?)";
       // String queryString = "SELECT TeamMemberId FROM tblBDMAssociates";
      //  System.out.println("queryString----->"+queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
    //    preparedStatement.setString(1, adminAction.getBdmId());
            resultSet = preparedStatement.executeQuery();
     
       
                     
                  
                 //    System.out.println("getResource Id isss---->"+adminAction.getBdmId());
        
            
            while (resultSet.next()) {
              
                
//                empName = resultSet.getString("EmpName");
//                     System.out.println("empName in while loop is---->"+empName); 
//                      if(empName == ajaxHandlerAction.getResourceName()){
//                  responseString = "sorry! the star performer for particular month has been added";
//                  isInserted = false;
//                break;
//              }
                
                 teamMemberId=resultSet.getString("TeamMemberId");
              //  System.out.println("empName in while loop is---->"+teamMemberId); 
                
              //  System.out.println("final member is--->"+teamMemberId);
                //System.out.println("adminAction.getPreAssignSalesId() is--->"+adminAction.getPreAssignSalesId());
                      if(teamMemberId.equals(adminAction.getPreAssignSalesId())){
                         // System.out.println("in equals case");
                  responseString = "sorry! the Sales has been already associated";
                  isInserted = false;
                break;
              }
            }
          
             

          //  System.out.println("empName is---->"+teamMemberId);
 
        preparedStatement.execute();
            //System.out.println("isInserted for resourcename is---->"+isInserted);
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
                throw new ServiceLocatorException(se);
            }
        }

        return isInserted;

    }

}
