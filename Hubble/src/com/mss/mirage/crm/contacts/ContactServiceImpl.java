/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 29, 2007, 7:51 PM
 *
 * Author  : Jyothi Nimmagadda<jnimmagadda@miraclesoft.com>
 *
 * File    : ContactServiceImpl.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.contacts;

import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 *  The common base class for all contact classes; provides default implementations
 *  of the <code>com.mss.mirage.crm.contacts</code> methods. All method implementations are
 *  forwarded to a <code>addOrUpdateContact</code> object stored in the <code>ContactServiceImpl</code>
 *  instance.
 *
 *  @author Jyothi Nimmagadda<jnimmagadda@miraclesoft.com>
 */

public class ContactServiceImpl implements ContactService{
    
    
    /** Creates a new instance of ContactServiceImpl */
    public ContactServiceImpl() {
    }
    
    /**
     * The <code>addorUpdateContact</code>  class is used for getting new Contact details from
     * <i>ContactAdd.jsp,ContactDetails.jsp</i> page.
     * <p>
     * Then it invokes setter methods in <code>doAdd,doEdit</code> class and invokes update() method
     * in <code>doAdd,doEdit</code> for inserting contact details in mirage.tblCrmContact table.
     *
     * @author Jyothi.Nimmagadda <a href="mailto:jnimmagadda@miraclesoft.com">jnimmagadda@miraclesoft.com</a>
     *
     * @version 1.0 November 01, 2007
     *
     * @see com.mss.mirage.crm.contacts.ContactAction
     * @see com.mss.mirage.crm.contacts.ContactService
     * @see com.mss.mirage.crm.contacts.ContactServiceImplementation
     * @see com.mss.mirage.crm.contacts.ContactVTO */
    public int addOrUpdateContact(ContactAction contactPojo) throws ServiceLocatorException {
        
        Connection connection = null;
        CallableStatement callableStatement = null;
        connection = ConnectionProvider.getInstance().getConnection();
        int updatedRows = 0;
        try {
            callableStatement = connection.prepareCall("{ call spContact(?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
                    ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            callableStatement.setInt(1,contactPojo.getAccountId());
            callableStatement.setInt(2,contactPojo.getId());
            callableStatement.setString(3,contactPojo.getSalutation());
            callableStatement.setString(4,contactPojo.getFirstName());
            callableStatement.setString(5,contactPojo.getLastName());
            callableStatement.setString(6,contactPojo.getMiddleName());
            callableStatement.setString(7,contactPojo.getAliasName());
            callableStatement.setString(8,contactPojo.getTitle());
            callableStatement.setString(9,contactPojo.getGender());
            callableStatement.setDate(10,contactPojo.getDOB());
            callableStatement.setDate(11,contactPojo.getDOA());
            callableStatement.setString(12,contactPojo.getReferredBy());
            callableStatement.setString(13,contactPojo.getContactStatus());
            callableStatement.setString(14,contactPojo.getLeadSource());
            callableStatement.setString(15,contactPojo.getSpecialization());
            callableStatement.setString(16,contactPojo.getOfficePhone());
            callableStatement.setString(17,contactPojo.getCellPhone());
            callableStatement.setString(18,contactPojo.getHomePhone());
            callableStatement.setString(19,contactPojo.getOfficeEmail());
            callableStatement.setString(20,contactPojo.getFax());
            callableStatement.setString(21,contactPojo.getPersonalEmail());
            callableStatement.setString(22,contactPojo.getAddressLine1());
            callableStatement.setString(23,contactPojo.getAddressLine2());
            callableStatement.setString(24,contactPojo.getCity());
            callableStatement.setString(25,contactPojo.getMailStop());
            callableStatement.setString(26,contactPojo.getState());
            callableStatement.setString(27,contactPojo.getCountry());
            callableStatement.setString(28,contactPojo.getZip());
            callableStatement.setString(29,contactPojo.getResAddressLine1());

            callableStatement.setString(30,contactPojo.getResAddressLine2());
            callableStatement.setString(31,contactPojo.getResCity());
            callableStatement.setString(32,contactPojo.getResMailStop());
            callableStatement.setString(33,contactPojo.getResState());
            callableStatement.setString(34,contactPojo.getResCountry());
            callableStatement.setString(35,contactPojo.getResZip());
            callableStatement.setString(36,contactPojo.getComments());
            callableStatement.setString(37,contactPojo.getCreatedBy());
            callableStatement.setTimestamp(38,contactPojo.getCreatedDate());
            callableStatement.setString(39,contactPojo.getModifiedBy());
            callableStatement.setTimestamp(40,contactPojo.getModifiedDate());
            callableStatement.setString(41,contactPojo.getOperationType());
            callableStatement.setInt(42,contactPojo.getPrimaryAddressId());
            callableStatement.setInt(43,contactPojo.getSecondaryAddressId());
            callableStatement.setString(44,contactPojo.getAddressType());
          //  String designationName=contactPojo.getDesignationName();
           
         /* if(designationName.equals("Customer"))
          {

              callableStatement.setString(45,"CU");
          }
          if(designationName.equals("Consultant"))
          {

              callableStatement.setString(45,"CN");
          }
          if(designationName.equals("TeamLead"))
          {

              callableStatement.setString(45,"TL");
          }
          if(designationName.equals("Manager"))
          {

              callableStatement.setString(45,"MN");
          }
            if(designationName.equals("Director"))
          {

              callableStatement.setString(45,"DR");
          }
             if(designationName.equals("Operations"))
          {

              callableStatement.setString(45,"OR");
          }*/
            //for getting AddressId,AddressId2,ContactId
            callableStatement.registerOutParameter(45, Types.INTEGER);
            callableStatement.registerOutParameter(46, Types.INTEGER);
            callableStatement.registerOutParameter(47, Types.INTEGER);
            
            
            updatedRows = callableStatement.executeUpdate();
            
            DataSourceDataProvider dataSourceDataProvider  = DataSourceDataProvider.getInstance();
            
            int count = dataSourceDataProvider.touchAccount(contactPojo.getAccountId());
            
            contactPojo.setPrimaryAddressId(callableStatement.getInt(45));
            contactPojo.setSecondaryAddressId(callableStatement.getInt(46));
            contactPojo.setId(callableStatement.getInt(47));
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ServiceLocatorException(ex);
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
            
        }
        
        return updatedRows;
    }
    
    
    /**
     *
     *
     *   @param   Taking contactId as int .
     *   @return   The ContactVTO returned  depends on the contactId.
     *   @throws  ServiceLocatorException
     *          If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
     * @see com.mss.mirage.util.ServiceLocatorException.
     * @ com.mss.mirage.crm.ContactService
     * {@link
     *          com.mss.mirage.crm.ContactService
     */
    public ContactVTO getContact(int contactId) throws ServiceLocatorException {
        
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        
        String queryString = "SELECT * FROM tblCrmContact WHERE Id="+contactId;
        ContactVTO contactVTO = new ContactVTO();
        try{
            
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            contactVTO.setId(contactId);
            
            // Sets  the  value to corresponding setter methods of  contactVTO
            while(resultSet.next()){
                
                contactVTO.setId(resultSet.getInt("Id"));
                contactVTO.setAccountId(resultSet.getInt("AccountId"));
                contactVTO.setContactStatus(resultSet.getString("ContactStatus"));
                contactVTO.setFirstName(resultSet.getString("FirstName"));
                contactVTO.setLastName(resultSet.getString("LastName"));
                contactVTO.setMiddleName(resultSet.getString("MiddleName"));
                contactVTO.setAliasName(resultSet.getString("AliasName"));
                contactVTO.setSalutation(resultSet.getString("Salutation"));
                contactVTO.setTitle(resultSet.getString("Title"));
                contactVTO.setPrimaryAddressId(resultSet.getInt("PrimaryAddressId"));
                contactVTO.setSecondaryAddressId(resultSet.getInt("SecondaryAddressId"));
                contactVTO.setGender(resultSet.getString("Gender"));
                contactVTO.setDOB(resultSet.getDate("DOB"));
                contactVTO.setDOA(resultSet.getDate("DOA"));
                contactVTO.setLeadSource(resultSet.getString("LeadSource"));
                contactVTO.setSpecialization(resultSet.getString("AreaSpecialization"));
                contactVTO.setHomePhone(resultSet.getString("HomePhone"));
                contactVTO.setOfficePhone(resultSet.getString("OfficePhone"));
                contactVTO.setCellPhone(resultSet.getString("CellPhone"));
                contactVTO.setFax(resultSet.getString("Fax"));
                contactVTO.setOfficeEmail(resultSet.getString("Email1"));
                contactVTO.setPersonalEmail(resultSet.getString("Email2"));
                 if(resultSet.getString("ContactStatus")!=null && !"".equals(resultSet.getString("ContactStatus"))&& "Unsubscribe".equals(resultSet.getString("ContactStatus"))){
                     contactVTO.setOfficeEmail("");
                contactVTO.setPersonalEmail("");
                 }
                contactVTO.setReferredBy(resultSet.getString("ReferredBy"));
                contactVTO.setComments(resultSet.getString("Comments"));
                contactVTO.setCreatedBy(resultSet.getString("CreatedBy"));
                contactVTO.setCreatedDate(resultSet.getTimestamp("CreatedDate"));
                contactVTO.setModifiedBy(resultSet.getString("ModifiedBy"));
                contactVTO.setModifiedDate(resultSet.getTimestamp("ModifiedDate"));
                contactVTO.setDontSend(resultSet.getBoolean("DontSendEmail"));
            }
            
            if(contactVTO.getPrimaryAddressId() != 0){
                queryString = "SELECT * FROM tblCrmAddress where Id="+contactVTO.getPrimaryAddressId();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                
                
                // Sets  the  value to corresponding setter methods of  contactVTO
                while(resultSet.next()){
                    contactVTO.setAddressLine1(resultSet.getString("AddressLine1"));
                    contactVTO.setAddressLine2(resultSet.getString("Addressline2"));
                    contactVTO.setCity(resultSet.getString("City"));
                    contactVTO.setMailStop(resultSet.getString("MailStop"));
                    contactVTO.setState(resultSet.getString("State"));
                    contactVTO.setCountry(resultSet.getString("Country"));
                    contactVTO.setZip(resultSet.getString("Zip"));
                }//closing while loop
            }
            
            if(contactVTO.getSecondaryAddressId() != 0){
                queryString = "SELECT * FROM tblCrmAddress where Id="+contactVTO.getSecondaryAddressId();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                
                // Sets  the  value to corresponding setter methods of  ContactVTO
                while(resultSet.next()){
                    contactVTO.setResAddressLine1(resultSet.getString("AddressLine1"));
                    contactVTO.setResAddressLine2(resultSet.getString("Addressline2"));
                    contactVTO.setResCity(resultSet.getString("City"));
                    contactVTO.setResMailStop(resultSet.getString("MailStop"));
                    contactVTO.setResState(resultSet.getString("State"));
                    contactVTO.setResCountry(resultSet.getString("Country"));
                    contactVTO.setResZip(resultSet.getString("Zip"));
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
        
        return contactVTO;
    }
    /**
     * The method ContactVTO getContactVTO(ContactAction contactPojo) is used for getting ContactVTO object
     * which contains accessors and mutators methods
     * @return ContactVTO object
     * {@link com.mss.mirage.crm.contacts.ContactVTO}*/
    
    public ContactVTO getContactVTO(ContactAction contactPojo) throws ServiceLocatorException {
        
        ContactVTO contactVTO = new ContactVTO();
        
        contactVTO.setId(contactPojo.getId());
        contactVTO.setAccountId(contactPojo.getAccountId());
        contactVTO.setContactStatus(contactPojo.getContactStatus());
        contactVTO.setFirstName(contactPojo.getFirstName());
        contactVTO.setLastName(contactPojo.getLastName());
        contactVTO.setMiddleName(contactPojo.getMiddleName());
        contactVTO.setAliasName(contactPojo.getAliasName());
        contactVTO.setSalutation(contactPojo.getSalutation());
        contactVTO.setTitle(contactPojo.getTitle());
        contactVTO.setPrimaryAddressId(contactPojo.getPrimaryAddressId());
        contactVTO.setSecondaryAddressId(contactPojo.getSecondaryAddressId());
        contactVTO.setGender(contactPojo.getGender());
        contactVTO.setDOB(contactPojo.getDOB());
        contactVTO.setDOA(contactPojo.getDOA());
        contactVTO.setLeadSource(contactPojo.getLeadSource());
        contactVTO.setSpecialization(contactPojo.getSpecialization());
        contactVTO.setHomePhone(contactPojo.getHomePhone());
        contactVTO.setOfficePhone(contactPojo.getOfficePhone());
        contactVTO.setCellPhone(contactPojo.getCellPhone());
        contactVTO.setFax(contactPojo.getFax());
        contactVTO.setOfficeEmail(contactPojo.getOfficeEmail());
        contactVTO.setPersonalEmail(contactPojo.getPersonalEmail());
        contactVTO.setReferredBy(contactPojo.getReferredBy());
        contactVTO.setComments(contactPojo.getComments());
        contactVTO.setCreatedBy(contactPojo.getCreatedBy());
        contactVTO.setCreatedDate(contactPojo.getCreatedDate());
        contactVTO.setModifiedBy(contactPojo.getModifiedBy());
        contactVTO.setModifiedDate(contactPojo.getModifiedDate());
        contactVTO.setAddressLine1(contactPojo.getAddressLine1());
        contactVTO.setAddressLine2(contactPojo.getAddressLine2());
        contactVTO.setCity(contactPojo.getCity());
        contactVTO.setMailStop(contactPojo.getMailStop());
        contactVTO.setState(contactPojo.getState());
        contactVTO.setCountry(contactPojo.getCountry());
        contactVTO.setZip(contactPojo.getZip());
        contactVTO.setResAddressLine1(contactPojo.getResAddressLine1());
        contactVTO.setResAddressLine2(contactPojo.getResAddressLine2());
        contactVTO.setResCity(contactPojo.getResCity());
        contactVTO.setResMailStop(contactPojo.getResMailStop());
        contactVTO.setResState(contactPojo.getResState());
        contactVTO.setResCountry(contactPojo.getResCountry());
        contactVTO.setResZip(contactPojo.getResZip());
        return contactVTO;
    }
    
    /**
     *
     *
     *   @param   Taking accountId as int .
     *   @return   The ContactVTO returned  depends on the accountId.
     *   @throws  ServiceLocatorException
     *          If a ServiceLocatorException exists and its <code>{@link
     *          com.mss.mirage.util.ServiceLocatorException}</code>
     * @see com.mss.mirage.util.ServiceLocatorException.
     * @ com.mss.mirage.crm.ContactService
     * {@link
     *          com.mss.mirage.crm.ContactService
     */
    public ContactVTO getOfficeAddress(int accountId) throws ServiceLocatorException {
        
        
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        
        ContactVTO contactVTO =  new ContactVTO();
        
        String queryString = "SELECT * FROM tblCrmAddress WHERE Id  IN (SELECT PrimaryAddressId FROM tblCrmAccount WHERE Id=" + accountId + ")";
        try{
            
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            
            // Sets  the  value to corresponding setter methods of  contactVTO
            while(resultSet.next()){
                contactVTO.setAddressLine1(resultSet.getString("AddressLine1"));
                contactVTO.setAddressLine2(resultSet.getString("AddressLine2"));
                contactVTO.setCity(resultSet.getString("City"));
                contactVTO.setMailStop(resultSet.getString("MailStop"));
                contactVTO.setState(resultSet.getString("State"));
                contactVTO.setCountry(resultSet.getString("Country"));
                contactVTO.setZip(resultSet.getString("Zip"));
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
        return contactVTO;
    }
    
 
 /* public int getLastcontact()throws ServiceLocatorException{
         int id=0;
         ResultSet resultSet = null;
         Connection connection = null;
         Statement statement = null;
         connection = ConnectionProvider.getInstance().getConnection();
         String queryString = "SELECT MAX(Id) as id FROM tblCrmContact";
        try{
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                resultSet.next();
                id=resultSet.getInt("id");
                
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
        
         return id;
  }*/
    
}
