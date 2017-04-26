/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 29, 2007, 7:50 PM
 *
 * Author  : Jyothi Nimmagadda<jnimmagadda@miraclesoft.com>
 *
 * File    : ContactAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.contacts;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.AuthorizationManager;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.sql.ResultSet;
//new
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.Properties;
/**
 *
 * @author Jyothi Nimmagadda<jnimmagadda@miraclesoft.com>
 *
 *
 */
public class ContactAction extends ActionSupport implements ServletRequestAware{
    
    
    private StringBuffer queryStringBuffer;
    
    /**
     * The resultType  is Useful to get the ResultType of an Action and depend on this
     * resultType the navigation of screens happens.
     */
    private String resultType;
    
    /**
     * The resultMessage  capture the resut and it is stored  as the REQUEST ATTRIBUTE.
     */
    private String resultMessage;
    
    /** The operationType is used for storing the operation type. */
    private String operationType;
    
    /** The addressType is used for storing the address type. */
    private String addressType;
    
    /** The accountName is used for storing the account name. */
    private String accountName;
    
    /** The contactVTO is used for storing the current contact values. */
    private ContactVTO currentContact;
    
    /**
     *  The httpServletRequest
     * object representing the  HttpServletRequest
     */
    private HttpServletRequest httpServletRequest;
    
       
    /**
     *  The dataProvider
     * object provides  the data to Screens
     */
    private HibernateDataProvider hibernateDataProvider;
    
    private DefaultDataProvider defaultDataProvider;
    
    private DataSourceDataProvider dataSourceDataProvider;
    //Pre Configurable Varaibles
    
    /**
     * Create a list for Salutation.
     * The list is dynamically created. If there are no entries for an
     * Salutation, no list object will be created for that salutation.
     * @param salutationlist to be displayed.
     */
    private List salutaionList = new ArrayList();
    
    /**
     * Create a list for Gender.
     * The list is dynamically created. If there are no entries for an
     * Gender, no list object will be created for that gender.
     * @param genderlist  to be displayed.
     */
    private List genderList = new ArrayList();
    
    /**
     * Create a list for ContactStatus.
     * The list is dynamically created. If there are no entries for an
     * contactStatus, no list object will be created for that contactStatus.
     * @param  contactStatusList to be displayed.
     */
    private List contactStatusList = new ArrayList();
    
    /**
     * Create a list for ContactType.
     * The list is dynamically created. If there are no entries for an
     * contactType, no list object will be created for that contactType.
     * @param  contactTypeList to be displayed.
     */
    private List contactTypeList = new ArrayList();
    
    /**
     * Create a list for Country.
     * The list is dynamically created. If there are no entries for an
     * countryList, no list object will be created for that countryList.
     * @param  countryList to be displayed.
     */
    private List countryList = new ArrayList();
    
    /**
     * Create a list for states.
     * The list is dynamically created. If there are no entries for an
     * statesList, no list object will be created for that statesList.
     * @param  statesList to be displayed.
     */
    private List statesList = new ArrayList();
    
    /**
     * Create a list for residentialStates.
     * The list is dynamically created. If there are no entries for an
     * resStatesList, no list object will be created for that statesList.
     * @param  resStatesList to be displayed.
     */
    private List resStatesList = new ArrayList();
    
    /*
     * Variables for storing
     * ContactAdd.jsp-- contactForm parameters
     *and inserting to tblCrmContact.Id,tblCrmAddress.Id
     */
    
    
    /** The id is used for storing the id of Contact. */
    private int id;
    
    /** The accountId is used for storing the id of Account. */
    private int accountId;
    
    /** The salutation is used for storing the salutation of particular Contact. */
    private String salutation;
    
    /** The firstName is used for storing the first name of particular Contact. */
    private String firstName;
    
    /** The middleName is used for storing the middle name of particular Contact. */
    private String middleName;
    
    /** The lastName is used for storing the last name of particular Contact. */
    private String lastName;
    
    /** The aliasName is used for storing the alias name of particular Contact. */
    private String aliasName;
    
    
    /** The title is used for storing the title of particular Contact. */
    private String title;
    
    /** The gender is used for storing the gender of particular Contact. */
    private String gender;
    
    
    /** The dob is used for storing the date of birth of the particular Contact. */
    private Date DOB;
    
    /** The doa is used for storing the date of anniversary of the particular Contact. */
    private Date DOA;
    
    
    /** The contactStatus is used for storing the Status of particular Contact. */
    private String contactStatus;
    
    
    /** The leadSource is used for storing the Source of particular Contact. */
    private String leadSource;
    
    /** The specialization is used for storing the specialization of particular Contact. */
    private String specialization;
    
    
    
    /** The homephone is used for storing the home Phone number  of particular Contact. */
    private String homePhone;
    
    /** The officephone is used for storing the office Phone number  of particular Contact. */
    private String officePhone;
    
    /** The cellphone is used for storing the cell Phone number  of particular Contact. */
    private String cellPhone;
    
    /** The fax is used for storing the fax number  of particular Contact. */
    private String fax;
    
    /** The officeEmail is used for storing the office email  of particular Contact. */
    private String officeEmail;
    
    /** The personalEmail is used for storing the personal email  of particular Contact. */
    private String personalEmail;
    
    
    /** The referredBy is used for storing the referrence of  of particular Contact. */
    private String referredBy;
    
    /** The comments is used for storing the comments  of particular Contact. */
    private String comments;
    
    /** The addressLine1 is used for storing the address line1  of particular Contact. */
    private String addressLine1;
    
    /** The addressLine2 is used for storing the address line2  of particular Contact. */
    private String addressLine2;
    
    /** The mailStop is used for storing the mail stop  of particular Contact. */
    private String mailStop;
    
    /** The city is used for storing the city  of particular Contact. */
    private String city;
    
    /** The state is used for storing the state  of particular Contact. */
    private String state;
    
    /** The country is used for storing the country  of particular Contact. */
    private String country;
    
    /** The zip is used for storing the zip  of particular Contact. */
    private String zip;
    
    
    /** The resAddressLine1 is used for storing the  residential address line1  of particular Contact. */
    private String resAddressLine1;
    
    /** The resAddressLine2 is used for storing the  residential address line2  of particular Contact. */
    private String resAddressLine2;
    
    /** The resMailStop is used for storing the  residential mail stop  of particular Contact. */
    private String resMailStop;
    
    /** The resCity is used for storing the  residential city  of particular Contact. */
    private String resCity;
    
    /** The resState is used for storing the  residential state  of particular Contact. */
    private String resState;
    
    /** The resCountry is used for storing the  residential country  of particular Contact. */
    private String resCountry;
    
    /** The resZip is used for storing the  residential zip  of particular Contact. */
    private String resZip;
    
    
    /** The createdBy is used for storing the  createdBy name  of particular Contact. */
    private String createdBy;
    
    /** The createdDate is used for storing the  date of creation  of particular Contact. */
    private Timestamp createdDate;
    
    
    /** The modifiedBy is used for storing the  modifier name  of particular Contact. */
    private String modifiedBy;
    
    /** The modifiedDate is used for storing the  date of modification  of particular Contact. */
    private Timestamp modifiedDate;
    
    
    /** The primaryAddressId is used for storing the  primary address id of particular Contact. */
    public int primaryAddressId;
    
    /** The secondaryAddressId is used for storing the  secondary address id of particular Contact. */
    public int secondaryAddressId;
    
    private String name;
    
    private String submitFrom;
    
    private int userRoleId;
    
    private boolean dontSend;
    
    private String actionName;
    //new parameters for designation
    
    private List designationList;
    private String designationName;
    /** Creates a new instance of ContactAction */
    public ContactAction() {
    }
    
    
    /** returns navigation */
    public String execute() throws Exception {
        return SUCCESS;
    }
    
    
    
    
     /**
     *   Populates user required options to the Screens depending on the  options.
     *   
     *    
     *   @return   The  ContactEmailService.jsp returned  depends on the  Success  value
     *
     *
     */
    
    
    public String emailService() throws Exception{
    return SUCCESS;    
    }
    
    /**
     *  Populates user required options to the Screens depending on the  options.
     *   @see com.mss.mirage.contacts.ApplicationConstants
     *
     * @return  The  Result type is returned after complete the code of prepare
     * Method.
     *
     *
     */
    public String prepare() throws Exception{
        
        resultType = LOGIN;
       
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            hibernateDataProvider = HibernateDataProvider.getInstance();
            defaultDataProvider = DefaultDataProvider.getInstance();
            dataSourceDataProvider = DataSourceDataProvider.getInstance();
            
            /**
             *   Populates user required options to the Screens depending on the  options.
             *
             *   @see com.mss.mirage.ApplicationConstants
             *
             *   @param   Taking Salutation_OPTIONS key from  ApplicationConstants
             *   @return   The salutation list  returned  depends on the SALUTATION_OPTIONS
             *
             *
             */
            setSalutaionList(defaultDataProvider
                    .getSalutationList(ApplicationConstants.SALUTATION_OPTIONS));
            
            /**
             *   Populates user required options to the Screens depending on the  options.
             *
             *   @see com.mss.mirage.ApplicationConstants
             *
             *   @param   Taking GENDER_OPTIONS key from  ApplicationConstants
             *   @return   The gender list  returned  depends on the GENDER_OPTIONS
             *
             *
             */
            setGenderList(defaultDataProvider
                    .getGender(ApplicationConstants.GENDER_OPTIONS));
            
            /**
             *   Populates user required options to the Screens depending on the  options.
             *
             *   @see com.mss.mirage.ApplicationConstants
             *
             *   @param   Taking CONTACTSTATUS_OPTIONS key from  ApplicationConstants
             *   @return   The contactstatus list  returned  depends on the CONTACTSTATUS_OPTIONS
             *
             *
             */
            setContactStatusList(hibernateDataProvider
                    .getCrmContactStatus(ApplicationConstants.CRM_CONTACT_STATUS_OPTIONS));
            
            /**
             *   Populates user required options to the Screens depending on the  options.
             *
             *   @see com.mss.mirage.ApplicationConstants
             *
             *   @param   Taking CONTACTTYPE_OPTIONS key from  ApplicationConstants
             *   @return   The contactType list  returned  depends on the CONTACTTYPE_OPTIONS
             *
             *
             */
            setContactTypeList(hibernateDataProvider
                    .getCrmContactType(ApplicationConstants.CRM_CONTACT_TYPE_OPTIONS));
            
            /**
             *  Populates user required options to the Screens depending on the  options.
             *   @see com.mss.mirage.ApplicationConstants
             *    @param   Taking COUNTRY_OPTIONS  key from  ApplicationConstants
             *   @return   The CountryList  returned  depends on the  COUNTRY_OPTIONS
             *
             *
             */
            setCountryList(hibernateDataProvider
                    .getContries(ApplicationConstants.COUNTRY_OPTIONS));
            
            setAccountName(HibernateDataProvider.getInstance().getAccountName(getAccountId()));
            httpServletRequest.setAttribute("currentAccountId",String.valueOf(getAccountId()));
            if(httpServletRequest.getSession(false).getAttribute("accountName") != null){
                httpServletRequest.getSession(false).removeAttribute("accountName");
            }
            httpServletRequest.getSession(false).setAttribute("accountName",getAccountName());
            
            if(getCurrentContact() != null){
                
                if(getCurrentContact().getCountry() != null){
                    setStatesList(dataSourceDataProvider
                            .getStates(getCurrentContact().getCountry()));
                }
                
                if(getCurrentContact().getResCountry() != null){
                    setResStatesList(dataSourceDataProvider
                            .getStates(getCurrentContact().getResCountry()));
                }
            }
            resultType = SUCCESS;
            
        }//Close Session Checking
        return resultType;
    }
    
    /**
     *   Populates user required options to the Screens depending on the  options.
     *   @see com.mss.mirage.ApplicationConstants
     *   @param   Taking  getAccountId() method  from  ApplicationConstants
     *   @return   The OfficeAddress returned  depends on the getAccountId() method
     *
     *
     */
    
    public String prepareAddress(){
      
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("PREPARE_ADDRESS_CONTACT",userRoleId)){
                try{
                    setDesignationList((List)httpServletRequest.getSession(false).getAttribute("designationList"));
                    hibernateDataProvider = HibernateDataProvider.getInstance();
                    setCurrentContact(ServiceLocator.getContactService().getOfficeAddress(getAccountId()));
                    
                    setAddressLine1(getCurrentContact().getAddressLine1());
                    setAddressLine2(getCurrentContact().getAddressLine2());
                    setCity(getCurrentContact().getCity());
                    setMailStop(getCurrentContact().getMailStop());
                    setState(getCurrentContact().getState());
                    setCountry(getCurrentContact().getCountry());
                    setZip(getCurrentContact().getZip());
                    //System.err.println("----"+getCountry());
                    //Calling prepare() method to populate select components
                    setActionName("addContact");
                    prepare();
                    httpServletRequest.setAttribute("currentAccountId",String.valueOf(getAccountId()));
                    resultType= SUCCESS;
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
        
    }
    
    
    /**
     *   Populates user required options to the Grid depends on the Contact.
     *   @see com.mss.mirage.ApplicationConstants
     *
     *   @return   The Result Type.
     *
     *   @throws  NullPointerException
     *          If a NullPointerException exists and its <code>{@link
     *          java.lang.NullPointerException}</code>
     *
     *
     *
     */
    public String getContact(){
       
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("GET_CONTACT",userRoleId)){
                try{
                    setCurrentContact(ServiceLocator.getContactService().getContact(getId()));
                    setDesignationList((List)httpServletRequest.getSession(false).getAttribute("designationList"));
                    setDesignationName(httpServletRequest.getParameter("designationName"));
                    
                    setAddressLine1(getCurrentContact().getAddressLine1());
                    setAddressLine2(getCurrentContact().getAddressLine2());
                    setCity(getCurrentContact().getCity());
                    setMailStop(getCurrentContact().getMailStop());
                    setState(getCurrentContact().getState());
                    setCountry(getCurrentContact().getCountry());
                    setZip(getCurrentContact().getZip());
                    
                    setAccountId(getCurrentContact().getAccountId());
                    
                    httpServletRequest.setAttribute("currentContactId",String.valueOf(getCurrentContact().getId()));
                    
                    setAccountId(getCurrentContact().getAccountId());
                    
                    //Calling prepare() method to populate select components
                    prepare();
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
    
    
    /**
     * The <code>doEdit</code>  class is used for getting  Contact details from
     * <i>ContactDetails.jsp</i> page.
     * <p>
     * Then it invokes setter methods in <code>doEdit</code> class and invokes doEdit() method
     * in <code>doEdit</code> for editing contact details in mirage.tblCrmContact table.
     *
     * @author Jyothi.Nimmagadda <a href="mailto:jnimmagadda@miraclesoft.com">jnimmagadda@miraclesoft.com</a>
     *
     * @version 1.0 November 01, 2007
     *
     * @see com.mss.mirage.crm.contacts.ContactAction
     * @see com.mss.mirage.crm.contacts.ContactService
     * @see com.mss.mirage.crm.contacts.ContactServiceImplementation
     * @see com.mss.mirage.crm.contacts.ContactVTO */
    
    
    
public String doEdit(){
       
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EDIT_CONTACT",userRoleId)){
                try{
                    int updatedRows = 0;
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        setOperationType(ApplicationConstants.SP_EDIT);
                        setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                         setDesignationList((List)httpServletRequest.getSession(false).getAttribute("designationList"));
                        setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                        
                        setAddressType("Residence Address");
                         String offEmail ="";
                        if(getOfficeEmail()!=null&&!"".equals(getOfficeEmail()))
                        offEmail = getOfficeEmail().trim();
                        String personalEmail="";
                          if(getPersonalEmail()!=null&&!"".equals(getPersonalEmail()))
                        personalEmail = getPersonalEmail().trim();
                        // Calling addOrUpdateContact(ContactAction contactPojo) method to edit contact
                           boolean isConatctExist=false;
                           if(!"".equals(offEmail)||!"".equals(personalEmail))
                           isConatctExist=DataSourceDataProvider.getInstance().isConatctExsit(getAccountId(),offEmail,personalEmail,getId());
                        // Calling addOrUpdateContact(ContactAction contactPojo) method to edit contact
                           if(!isConatctExist){
                               updatedRows = ServiceLocator.getContactService().addOrUpdateContact(this);
                           }
                        
                        
                        setCurrentContact(ServiceLocator.getContactService().getContactVTO(this));
                        setAccountId(getCurrentContact().getAccountId());
                        setId(getId());
                        if(updatedRows == 1){
                            resultType = SUCCESS;
                            setResultMessage("<font color=\"green\" size=\"1.5\">Contact has been successfully updated!</font>");
                        }else{
                             if(isConatctExist){
                                 resultType = INPUT;
                                setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Contact existed with given email!</font>");
                            }else {
                                 resultType = INPUT;
                                setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>");
                            }
                        }
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                        
                        httpServletRequest.setAttribute("currentContactId",String.valueOf(getCurrentContact().getId()));
                        //new
                        //setCurrentContact(ServiceLocator.getContactService().getContact(getId()));
                        
                    }//Clsoe checking Submit From
                    //Calling prepare() method to populate select components
                   // prepare();
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Clsoe Session Checking
        return resultType;
    }
    
    /**
     * The <code>doAdd</code>  class is used for getting new Contact details from
     * <i>ContactAdd.jsp</i> page.
     * <p>
     * Then it invokes setter methods in <code>doAdd</code> class and invokes doAdd() method
     * in <code>doAdd</code> for inserting contact details in mirage.tblCrmContact table.
     *
     * @author Jyothi.Nimmagadda <a href="mailto:jnimmagadda@miraclesoft.com">jnimmagadda@miraclesoft.com</a>
     *
     * @version 1.0 November 01, 2007
     *
     * @see com.mss.mirage.crm.contacts.ContactAction
     * @see com.mss.mirage.crm.contacts.ContactService
     * @see com.mss.mirage.crm.contacts.ContactServiceImplementation
     * @see com.mss.mirage.crm.contacts.ContactVTO */
    
    public String doAdd(){
        int insertedRows = 0;
      
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("ADD_CONTACT",userRoleId)){
                try{
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        setOperationType(ApplicationConstants.SP_ADD);
                        setCreatedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        setDesignationList((List)httpServletRequest.getSession(false).getAttribute("designationList"));
                        setCreatedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                        
                        setAddressType("Alternate Address");
                        
                        // Calling addOrUpdateContact(ContactAction contactPojo) method to add account
                        
                        String offEmail ="";
                        if(getOfficeEmail()!=null&&!"".equals(getOfficeEmail()))
                        offEmail = getOfficeEmail().trim();
                        String personalEmail="";
                          if(getPersonalEmail()!=null&&!"".equals(getPersonalEmail()))
                        personalEmail = getPersonalEmail().trim();
                        // Calling addOrUpdateContact(ContactAction contactPojo) method to edit contact
                           boolean isConatctExist=false;
                            if(!"".equals(offEmail)||!"".equals(personalEmail))
                           isConatctExist=DataSourceDataProvider.getInstance().isConatctExsit(getAccountId(),offEmail,personalEmail,0);
                          
                          
                          //System.out.println("isConatctExist-->"+isConatctExist);
                        if(!isConatctExist){
                        insertedRows = ServiceLocator.getContactService().addOrUpdateContact(this);
                        }
                        setAccountId(getAccountId());
                         setId(getId());
                       // String primaryManager=DataSourceDataProvider.getInstance().getPrimaryTeamMember(getAccountId());
                      //   System.out.println("insertedRows-->"+insertedRows);
                         
                        if(insertedRows != 0){
                            if(Properties.getProperty("Mail.Flag").equals("1")) {
                           // MailManager.sendReminderForContacts(getCreatedBy(),getFirstName(),getLastName(),getAccountId(),getCreatedDate().toString(),getTitle(),getOfficeEmail());
                            }
                            resultType = SUCCESS;
                            setResultMessage("<font color=\"green\" size=\"1.5\">Contact has been successfully inserted!</font>");
                            
                          setActionName("UpdateContact");
                        }else{
                            setActionName("addContact");
                            if(isConatctExist){
                                 resultType = INPUT;
                                setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Contact existed with given email!</font>");
                            }else {
                                 resultType = INPUT;
                                setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>");
                            }
                           
                        }
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                    }//Clsoe checking Submit From
                    //Calling prepare() method to populate select components
                    //setActionName("addContact");
                   
                    //new
                      httpServletRequest.setAttribute("ActionName",String.valueOf(getActionName()));
                    //getPrimaryAddressId();
                    //getSecondaryAddressId();
                     
                    // setId(ServiceLocator.getContactService().getLastcontact());
//                     setPrimaryAddressId(getPrimaryAddressId());
//                     setSecondaryAddressId(getSecondaryAddressId());
//                     
//                     setCurrentContact(ServiceLocator.getContactService().getContactVTO(this));
//                     
//                     prepare();
                    
                     
                    
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }// Close Session Checking
        return resultType;
    }
	
    
    /**
     * This is for updating contact when add once again in add contact process.
     *
     */
     public String doUpdate(){
       
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("EDIT_CONTACT",userRoleId)){
                try{
                    int updatedRows = 0;
                    if(!("dbGrid".equalsIgnoreCase(getSubmitFrom()))){
                        setOperationType(ApplicationConstants.SP_EDIT);
                        setModifiedBy(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                        setDesignationList((List)httpServletRequest.getSession(false).getAttribute("designationList"));
                        setModifiedDate(DateUtility.getInstance().getCurrentMySqlDateTime());
                        
                        setAddressType("Residence Address");
                        
                        // Calling addOrUpdateContact(ContactAction contactPojo) method to edit contact
                        updatedRows = ServiceLocator.getContactService().addOrUpdateContact(this);
                        
                        setCurrentContact(ServiceLocator.getContactService().getContactVTO(this));
                        
                        if(updatedRows == 1){
                            resultType = SUCCESS;
                            setResultMessage("<font color=\"green\" size=\"1.5\">Contact has been successfully updated!</font>");
                        }else{
                            resultType = INPUT;
                            setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>");
                        }
                        httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, getResultMessage());
                        
                        httpServletRequest.setAttribute("currentContactId",String.valueOf(getCurrentContact().getId()));
                        //new
                        //setCurrentContact(ServiceLocator.getContactService().getContact(getId()));
                        setAccountId(getCurrentContact().getAccountId());
                    }//Clsoe checking Submit From
                    //Calling prepare() method to populate select components
                     prepare();
                     setActionName("UpdateContact");
                    
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
            }//END-Authorization Checking
        }//Clsoe Session Checking
        return resultType;
    }
    
    
    
    public String searchContact(){
        resultType=LOGIN;
      
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
            
            userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_ROLE_ID).toString());
            resultType = "accessFailed";
            if(AuthorizationManager.getInstance().isAuthorizedUser("SEARCH_CONTACT",userRoleId)){
                try{
                    queryStringBuffer =new StringBuffer();
                    
                    queryStringBuffer.append("SELECT Id,Salutation,Gender,LastName,FirstName,AliasName,OfficePhone,Email1,Title FROM tblCrmContact");
                    
                    if(null == getName()) setName("");
//            if(null == getAliasName()) setAliasName("");
//            if(null == getPersonalEmail()) setPersonalEmail("");
                    
                    queryStringBuffer.append(" WHERE ");
                    
                    queryStringBuffer.append(" AccountId ="+getAccountId());
                    
                    if(!"".equals(getName())){
                        if((getName().indexOf("*") == -1)&&(getName().indexOf("%") == -1)) setName(getName()+"*");
                        setName(getName().replace("*","%"));
                        queryStringBuffer.append(" AND (FirstName LIKE '" + getName()+"' OR LastName LIKE '"+getName()+"')");
                    }
                    
//            if(!"".equals(getAliasName()) ){
//                if(getAliasName().indexOf("*") == -1) setAliasName(getAliasName()+"*");
//                setAliasName(getAliasName().replace("*","%"));
//                queryStringBuffer.append(" AND AliasName LIKE '"+ getAliasName()+"'");
//            }
//
//            if(!"".equals(getPersonalEmail())){
//                if(getPersonalEmail().indexOf("*") == -1) setPersonalEmail(getPersonalEmail()+"*");
//                setPersonalEmail(getPersonalEmail().replace("*","%"));
//
//                queryStringBuffer.append(" AND Email1 LIKE '"+ getPersonalEmail()+"'");
//
//            }
                    queryStringBuffer.append(" ORDER BY FirstName");
                    if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST) != null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST);
                    }
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST,queryStringBuffer.toString());
                    httpServletRequest.getSession(false).setAttribute(ApplicationConstants.QS_CRM_CONTACT_LIST_FROM,"contactSearch");
                    //queryStringBuffer.delete(0,queryStringBuffer.capacity());
                    
                    resultType = SUCCESS;
                }catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType =  ERROR;
                }
                
            }//END-Authorization Checking
        }//Close Session Checking
        return resultType;
    }
    
 public String getContactSearch() {
          resultType = LOGIN;
        String queryString="";
        if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null){
        try{
        

                    resultType = SUCCESS;
        }
        catch (Exception ex){
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    ex.printStackTrace();
                    httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
                    resultType = ERROR;
                }
        
    
   }
        return resultType;
          
      
      }
    
    //START:Pre Setters and Getters
    
    
    /**
     * Set the SalutationList.
     * @param SalutationList  which will make up the Salutation List.
     */
    public List getSalutaionList() {
        return salutaionList;
    }
    
    public void setSalutaionList(List salutaionList) {
        this.salutaionList = salutaionList;
    }
    
    
    /**
     * Set the genderList.
     * @param genderList which will make up the Gender List.
     */
    public List getGenderList() {
        return genderList;
    }
    
    public void setGenderList(List genderList) {
        this.genderList = genderList;
    }
    
    
    /**
     * Set the ContactStatusList.
     * @param ContactStatusList which will make up the Contact Status List.
     */
    public List getContactStatusList() {
        return contactStatusList;
    }
    
    public void setContactStatusList(List contactStatusList) {
        this.contactStatusList = contactStatusList;
    }
    
    
    /**
     * Set the ContactTypeList.
     * @param ContactTypeList which will make up the Contact Type List.
     */
    public List getContactTypeList() {
        return contactTypeList;
    }
    
    public void setContactTypeList(List contactTypeList) {
        this.contactTypeList = contactTypeList;
    }
    //END:Pre Setters and Getters
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    //Getter and setters for Form varaibles
    
    /**
     * Set the id of Contact.
     * @param id get id of Contact.
     */
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Set the AccountId of Contact.
     * @param AccountId get accountId of Contact.
     */
    public int getAccountId() {
        return accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    /**
     * Set the Salutation of Contact.
     * @param salutation get salutation of Contact.
     */
    public String getSalutation() {
        return salutation;
    }
    
    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }
    
    /**
     * Set the firstname of Contact.
     * @param firstname get firstname of Contact.
     */
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Set the middlename of Contact.
     * @param middlename get middlename of Contact.
     */
    public String getMiddleName() {
        return middleName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    /**
     * Set the lastname of Contact.
     * @param lastname get lastname of Contact.
     */
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Set the aliasname of Contact.
     * @param aliasname get aliasname of Contact.
     */
    public String getAliasName() {
        return aliasName;
    }
    
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
    
    /**
     * Set the title of Contact.
     * @param title get title of Contact.
     */
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Set the gender of Contact.
     * @param gender get gender of Contact.
     */
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * Set the date of birth of Contact.
     * @param dob get date of birth of Contact.
     */
    public Date getDOB() {
        return DOB;
    }
    
    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }
    
    /**
     * Set the date of anniversary of Contact.
     * @param doa get date of anniversary of Contact.
     */
    public Date getDOA() {
        return DOA;
    }
    
    public void setDOA(Date DOA) {
        this.DOA = DOA;
    }
    
    /**
     * Set the Contact Status of Contact.
     * @param contactStatus get contactStatus of Contact.
     */
    public String getContactStatus() {
        return contactStatus;
    }
    
    public void setContactStatus(String contactStatus) {
        this.contactStatus = contactStatus;
    }
    
    /**
     * Set the source of Contact.
     * @param source get source of Contact.
     */
    public String getLeadSource() {
        return leadSource;
    }
    
    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }
    
    /**
     * Set the specialization of Contact.
     * @param specialization get specialization of Contact.
     */
    public String getSpecialization() {
        return specialization;
    }
    
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    /**
     * Set the home phone of Contact.
     * @param homePhone get homePhone of Contact.
     */
    public String getHomePhone() {
        return homePhone;
    }
    
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
    
    /**
     * Set the office phone of Contact.
     * @param officePhone get officePhone of Contact.
     */
    public String getOfficePhone() {
        return officePhone;
    }
    
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }
    
    /**
     * Set the cell phone of Contact.
     * @param cellPhone get cellPhone of Contact.
     */
    public String getCellPhone() {
        return cellPhone;
    }
    
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
    
    /**
     * Set the fax of Contact.
     * @param fax get fax of Contact.
     */
    public String getFax() {
        return fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    /**
     * Set the office email of Contact.
     * @param officeEmail get officeemail of Contact.
     */
    public String getOfficeEmail() {
        return officeEmail;
    }
    
    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }
    
    /**
     * Set the personal email of Contact.
     * @param personalEmail get officePhone of Contact.
     */
    public String getPersonalEmail() {
        return personalEmail;
    }
    
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }
    
    /**
     * Set the referredBy of Contact.
     * @param referredBy get referredBy of Contact.
     */
    public String getReferredBy() {
        return referredBy;
    }
    
    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }
    
    /**
     * Set the comments of Contact.
     * @param comments get comments of Contact.
     */
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        if(comments == null) comments = "";
        this.comments = comments;
    }
    
    /**
     * Set the addressLine1 of Contact.
     * @param addressLine1 get address Line1 of Contact.
     */
    public String getAddressLine1() {
        return addressLine1;
    }
    
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    
    /**
     * Set the addressLine2 of Contact.
     * @param addressLine2 get address Line2 of Contact.
     */
    public String getAddressLine2() {
        return addressLine2;
    }
    
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    
    /**
     * Set the mailStop of Contact.
     * @param mailStop get Mail Stop of Contact.
     */
    public String getMailStop() {
        return mailStop;
    }
    
    public void setMailStop(String mailStop) {
        this.mailStop = mailStop;
    }
    
    /**
     * Set the city of Contact.
     * @param city get city of Contact.
     */
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * Set the state of Contact.
     * @param state get state of Contact.
     */
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    /**
     * Set the country of Contact.
     * @param country get country of Contact.
     */
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * Set the zip of Contact.
     * @param zip get zip of Contact.
     */
    public String getZip() {
        return zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    /**
     * Set the ResaddressLine1 of Contact.
     * @param resAddressLine1 get  Res Address Line1 of Contact.
     */
    public String getResAddressLine1() {
        return resAddressLine1;
    }
    
    public void setResAddressLine1(String resAddressLine1) {
        this.resAddressLine1 = resAddressLine1;
    }
    
    /**
     * Set the ResaddressLine2 of Contact.
     * @param resAddressLine2 get  Res Address Line2 of Contact.
     */
    public String getResAddressLine2() {
        return resAddressLine2;
    }
    
    public void setResAddressLine2(String resAddressLine2) {
        this.resAddressLine2 = resAddressLine2;
    }
    
    /**
     * Set the ResMailStop of Contact.
     * @param resMailStop get  Res MailStop of Contact.
     */
    public String getResMailStop() {
        return resMailStop;
    }
    
    public void setResMailStop(String resMailStop) {
        this.resMailStop = resMailStop;
    }
    
    /**
     * Set the ResCity of Contact.
     * @param resCity get  Res City of Contact.
     */
    public String getResCity() {
        return resCity;
    }
    
    public void setResCity(String resCity) {
        this.resCity = resCity;
    }
    
    /**
     * Set the ResState of Contact.
     * @param resState get  Res state of Contact.
     */
    public String getResState() {
        return resState;
    }
    
    public void setResState(String resState) {
        this.resState = resState;
    }
    
    /**
     * Set the ResCountry of Contact.
     * @param resCountry get  Res country of Contact.
     */
    public String getResCountry() {
        return resCountry;
    }
    
    public void setResCountry(String resCountry) {
        this.resCountry = resCountry;
    }
    
    /**
     * Set the ResZip of Contact.
     * @param resZip get  Res Zip of Contact.
     */
    public String getResZip() {
        return resZip;
    }
    
    public void setResZip(String resZip) {
        this.resZip = resZip;
    }
    
    /**
     * Set the CreatedBy of Contact.
     * @param CreatedBy get  CreatedBy of Contact.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    /**
     * Set the CreatedDate of Contact.
     * @param createdDate get  createdDate of Contact.
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    
    /**
     * Set the ModifiedBy of Contact.
     * @param ModifiedBy get  modifier name of Contact.
     */
    public String getModifiedBy() {
        return modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    /**
     * Set the ModifiedDate of Contact.
     * @param modifiedDate get  modifiedDate of Contact.
     */
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }
    
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    /**
     * Set the PrimaryAddressId of Contact.
     * @param PrimaryAddressId get primaryaddress id of Contact.
     */
    public int getPrimaryAddressId() {
        return primaryAddressId;
    }
    
    public void setPrimaryAddressId(int primaryAddressId) {
        this.primaryAddressId = primaryAddressId;
    }
    
    /**
     * Set the secondaryAddressId of Contact.
     * @param secondaryAddressId get secondaryaddress id of Contact.
     */
    public int getSecondaryAddressId() {
        return secondaryAddressId;
    }
    
    public void setSecondaryAddressId(int secondaryAddressId) {
        this.secondaryAddressId = secondaryAddressId;
    }
    
    /**
     * Set the OperationType of Contact.
     * @param OperationType get Operation Type of Contact.
     */
    public String getOperationType() {
        return operationType;
    }
    
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    
    /**
     * Set the AddressType of Contact.
     * @param addressType get Address Type of Contact.
     */
    public String getAddressType() {
        return addressType;
    }
    
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
    
    /**
     * Set the currentContact of Contact.
     * @param currentContact get currentContact of Contact.
     */
    public ContactVTO getCurrentContact() {
        return currentContact;
    }
    
    public void setCurrentContact(ContactVTO currentContact) {
        this.currentContact = currentContact;
    }
    
    /**
     * Set the accountName of Contact.
     * @param accountName get accountName of Contact.
     */
    public String getAccountName() {
        return accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    
    /**
     * Set the CountryList which starts the lists.
     * @param countrylist  which will make up the counrtry list.
     */
    public List getCountryList() {
        return countryList;
    }
    
    public void setCountryList(List countryList) {
        this.countryList = countryList;
    }
    
    /**
     * Set the StatesList which starts the lists.
     * @param stateslist  which will make up the states list.
     */
    public List getStatesList() {
        return statesList;
    }
    
    public void setStatesList(List statesList) {
        this.statesList = statesList;
    }
    
    /**
     * Set the ResStatesList which starts the lists.
     * @param ResStateslist  which will make up the states list.
     */
    public List getResStatesList() {
        return resStatesList;
    }
    
    public void setResStatesList(List resStatesList) {
        this.resStatesList = resStatesList;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSubmitFrom() {
        return submitFrom;
    }
    
    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }

    public boolean isDontSend() {
        return dontSend;
    }

    public void setDontSend(boolean dontSend) {
        this.dontSend = dontSend;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public List getDesignationList() {
        return designationList;
    }

    public void setDesignationList(List designationList) {
        this.designationList = designationList;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    /**
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    
    
    
}
