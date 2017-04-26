/*
 * @(#)RegistrationAction.java 1.0 Nov 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */

package com.mss.mirage.general;

import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.HibernateDataProvider;
import com.mss.mirage.util.DefaultDataProvider;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Random;
import com.mss.mirage.util.HibernateServiceLocator;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * The <code>RegistrationAction</code>  class is used for to register in MirageV2 from
 * <i>Registration.jsp</i> page.
 *
 * @author RajaReddy.Andra<a href="mailto:randra@miraclesoft.com">randra@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 *
 * @see com.mss.mirage.util.MailManager
 * @see com.mss.mirage.util.ApplicationConstants
 * @see com.mss.mirage.util.ApplicationDataProvider
 * @see com.mss.mirage.util.PasswordUtility
 * @see com.mss.mirage.util.ServiceLocator
 */
public class RegistrationAction extends ActionSupport implements ServletRequestAware{
    
    /*@param resultMessage to store message and that will display in login page*/
    String resultMessage;
    
    /*@param httpServletRequest to set resultMessage and that will get in login page*/
    private HttpServletRequest httpServletRequest;
    
    /*@param resultType to store type of results means login or success or failure etc...*/
    private String resultType;
    
    /*These are all persional details of the employee*/
    
    /*@param firstName to store firstname of the user*/
    private String firstName;
    
    /*@param lastName to store lastname of the user*/
    private String lastName;
    
    /*@param middleName to store middlename of the user*/
    private String middleName;
    
    /*@param gender to store gender of the user*/
    private String gender;
    
    /*@param maritalStatus to store  maritalstatus of the user*/
    private String maritalStatus;
    
    /*@param ssn to store the ssn of the user*/
    private String ssn;
    
    /*@param country to store the country of the user*/
    private String country;
    
    /*@param birthDate to store the date of birth of user*/
    private Date birthDate;
    
    /*@param offBirthDate to store the official date of birth*/
    private Date offBirthDate;
    
    /*@param refferedBy to store the name of the refferenc persion*/
    private String refferedBy;
    
    /*these all are contact details*/
    
    /*@param workPhoneNo to store work phone no of the user*/
    private String workPhoneNo;
    
    /*@param cellPhoneNo to store mobile number of the user*/
    private String cellPhoneNo;
    
    /*@param homePhoneNo to store home phone no of the user*/
    private String homePhoneNo;
    
    /*@param altPhoneNo to store alternative phone number of the user*/
    private String altPhoneNo;
    
    /*@param hotelPhoneNo to store hotel phone no of the user*/
    private String hotelPhoneNo;
    
    /*@param indiaPhoneNo to store india phone no of the user*/
    private String indiaPhoneNo;
    
    /*@param faxNo to store faxno of the user*/
    private String faxNo;
    
    /*@param officeEmail to store office email of the user*/
    private String officeEmail;
    
    /*@param otherEmail to store other email of the user*/
    private String otherEmail;
    
    /*@param personalEmail to store personal email of the user*/
    private String personalEmail;
    
    /*@param prefQuestion to store prefered question of the user*/
    private String prefQuestion;
    
    /*@param prefAnswer to store prefered answer  of the user*/
    private String prefAnswer;
    
    /*@param loginId to store the login id of the user*/
    private String loginId;
    
    /*@param password to store the password of the user*/
    private String password;
    
    /*@param curStatus to store the current status of the  user*/
    private String curStatus;
    private int deletedFlag;
    
    /*@param id t store id of the row*/
    private int id;
    
    
    /*@param genderList is used to store applocation level constants of gender*/
    private List genderList = new ArrayList();
    
    /*@param maritalStatusList is used to store applicationn level constants of maritalstatus valus*/
    private List maritalStatusList = new ArrayList();
    
    /*@param countryList is used to store application level constants of Country*/
    private List coutryList = new ArrayList();
    
    /*@param prefferedQuestionMap is used to store application level constatns of prefferedQuestions */
    private Map prefferedQuestionsMap;
    
    private DefaultDataProvider defaultDataProvider;
    
    private String orgId;
    
    private String workingCountry;
    
    
    
    
    /** Creates a new instance of UserRegistration */
    public RegistrationAction() {
    }
    
    /**
     *This method prepare() is used to get application leval constant values
     *return type is String and it returns success
     */
    public String prepare()throws Exception{
        
        defaultDataProvider = DefaultDataProvider.getInstance();
        
        setGenderList(defaultDataProvider.getGender(ApplicationConstants.GENDER_OPTIONS));
        setMaritalStatusList(defaultDataProvider.getMaritalStatus(ApplicationConstants.MARITAL_STATUS_OPTIONS));
        setCoutryList(HibernateDataProvider.getInstance().getContries(ApplicationConstants.COUNTRY_OPTIONS));
        setPrefferedQuestionsMap(defaultDataProvider.getPrefferedQuestions(ApplicationConstants.PREFERED_QUESTION_OPTIONS));
        
        return SUCCESS;
    }
    
    /**
     *execute() method is type String in this method
     *checking condition like if user alredy exist in data base it won't accept
     * other wise it will accept .finally i wil display a message to user on login page.
     */
    public String execute() throws Exception {
        resultMessage = null;
        boolean isUserExist= ServiceLocator.getGeneralService().userCheckExist(this);
        if(isUserExist){
          //  resultMessage = "<font color=\"red\" size=\"1.5\">Oops! you have been registered with Mirage already!</font>";
              resultMessage = "<font color=\"red\" size=\"1.5\">Oops! you have been registered with Hubble already!</font>";
            resultMessage = resultMessage+ "<font color=\"red\" size=\"1.5\">Activation process will take 24hrs after Registration, So Please try to login after the stipulated time.</font>";
            resultType = LOGIN;
        }else{
            setCurStatus("Registered");
            
            boolean isAddUser =  ServiceLocator.getGeneralService().addUser(this);
            
            
            if(isAddUser){
                resultMessage = "<font color=\"green\" size=\"1.5\">Congrats! You have registered yourself Successfully! <br> You will get your Login Id and Password with in 24 hours.</font>";
                resultType = LOGIN;
            }else{
                resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Registration Failed, Please Try Again! </font>";
                resultType = INPUT;
            }
        }
        
        httpServletRequest.setAttribute("resultMessage",resultMessage);
        return resultType;
    }
    
    /*This is bean section all are setter and getter methods*/
    
    /*getFirstName() used to get first name of the employee*/
    public String getFirstName() {
        return firstName;
    }
    
    /*setFirstName(String firstName) used to set first name of the employee*/
    public void setFirstName(String firstName) {
        if(firstName == null) firstName = " ";
        this.firstName = firstName.trim();
    }
    
    /*getLastName() used to get last name of the employee*/
    public String getLastName() {
        return lastName;
    }
    
    /*setLastName(String lastName) used to set last name of the employee*/
    public void setLastName(String lastName) {
        if(lastName == null) lastName = " ";
        this.lastName = lastName.trim();
    }
    
    /*getMiddleName() used to get middle name of the employee*/
    public String getMiddleName() {
        return middleName;
    }
    
    /*setMiddleName(String middleName) used to set middle name of the employee*/
    public void setMiddleName(String middleName) {
        if(middleName == null) middleName = " ";
        this.middleName = middleName.trim();
    }
    
    /*getGender() used to get gender of the employee*/
    public String getGender() {
        return gender;
    }
    
    /*setGender(String gender) used to set gender of the employee*/
    public void setGender(String gender) {
        this.gender = gender.trim();
    }
    
    /*getMaritalStatus() used to get marital status of the employee*/
    public String getMaritalStatus() {
        return maritalStatus;
    }
    
    /*setMaritalStatus(String maritalStatus) used to set marital status of the employee*/
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus.trim();
    }
    
    /*getSsn() used to get ssn of the employee*/
    public String getSsn() {
        return ssn;
    }
    
    /*setSsn(String ssn) used to set ssn of the employee*/
    public void setSsn(String ssn) {
        this.ssn = ssn.trim();
    }
    
    /*getCountry() used to get country of the employee*/
    public String getCountry() {
        return country;
    }
    
    /*setCountry(String country) used to set country of the employee*/
    public void setCountry(String country) {
        this.country = country.trim();
    }
    
    /*getBirthDate() used to get birth date of the employee*/
    public Date getBirthDate() {
        return birthDate;
    }
    
    /*setBirthDate(Date birthDate) used to set birth date of the employee*/
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    /*getOffBirthDate() used to get official birth date of the employee*/
    public Date getOffBirthDate() {
        return offBirthDate;
    }
    
    /*setOffBirthDate(Date offBirthDate) used to set official birth date of the employee*/
    public void setOffBirthDate(Date offBirthDate) {
        this.offBirthDate = offBirthDate;
    }
    
    /*getRefferedBy() used to get reffered persion name*/
    public String getRefferedBy() {
        return refferedBy;
    }
    
    /*setRefferedBy(String refferedBy) used to set reffered persion name*/
    public void setRefferedBy(String refferedBy) {
        this.refferedBy = refferedBy.trim();
    }
    
    /*getWorkPhoneNo() used to get work phone number*/
    public String getWorkPhoneNo() {
        return workPhoneNo;
    }
    
    /*setWorkPhoneNo(String workPhoneNo) used to set work phone number*/
    public void setWorkPhoneNo(String workPhoneNo) {
        this.workPhoneNo = workPhoneNo.trim();
    }
    
    /*getCellPhoneNo() used to get cell phone number*/
    public String getCellPhoneNo() {
        return cellPhoneNo;
    }
    
    /*setCellPhoneNo(String cellPhoneNo) used to set cell phone number*/
    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo.trim();
    }
    
    /*getHomePhoneNo() used to get home phone number*/
    public String getHomePhoneNo() {
        return homePhoneNo;
    }
    
    /*setHomePhoneNo(String homePhoneNo) used to set home phone number*/
    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo.trim();
    }
    
    /*getAltPhoneNo() used to get alternate phone number*/
    public String getAltPhoneNo() {
        return altPhoneNo;
    }
    
    /*setAltPhoneNo(String altPhoneNo) used to set alternate phone number*/
    public void setAltPhoneNo(String altPhoneNo) {
        this.altPhoneNo = altPhoneNo.trim();
    }
    
    /*getHotelPhoneNo() used to get hotel phone number*/
    public String getHotelPhoneNo() {
        return hotelPhoneNo;
    }
    
    /*setHotelPhoneNo(String hotelPhoneNo) used to set hotel phone number*/
    public void setHotelPhoneNo(String hotelPhoneNo) {
        this.hotelPhoneNo = hotelPhoneNo.trim();
    }
    
    /*getIndiaPhoneNo() used to get india phone number*/
    public String getIndiaPhoneNo() {
        return indiaPhoneNo;
    }
    
    /*setIndiaPhoneNo(String indiaPhoneNo) used to set india phone number*/
    public void setIndiaPhoneNo(String indiaPhoneNo) {
        this.indiaPhoneNo = indiaPhoneNo.trim();
    }
    
    /*getFaxNo() used to get fax number*/
    public String getFaxNo() {
        return faxNo;
    }
    
    /*setFaxNo(String faxNo) used to set fax number*/
    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo.trim();
    }
    
    /* getOfficeEmail() used to get office  email*/
    public String getOfficeEmail() {
        return officeEmail;
    }
    
    /* setOfficeEmail(String officeEmail) used to set office  email*/
    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail.trim();
    }
    
    /*getOtherEmail() used to get other email*/
    public String getOtherEmail() {
        return otherEmail;
    }
    
    /*setOtherEmail(String otherEmail) used to set other email*/
    public void setOtherEmail(String otherEmail) {
        this.otherEmail = otherEmail.trim();
    }
    
    /*getPersonalEmail() used to get personal email*/
    public String getPersonalEmail() {
        return personalEmail;
    }
    
    /*setPersonalEmail(String personalEmail) used to set personal email*/
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail.trim();
    }
    
    /*getPrefQuestion() used to get preffered question*/
    public String getPrefQuestion() {
        return prefQuestion;
    }
    
    /*setPrefQuestion(String prefQuestion) used to set preffered question*/
    public void setPrefQuestion(String prefQuestion) {
        this.prefQuestion = prefQuestion.trim();
    }
    
    /*getPrefAnswer() used to get preffered answer*/
    public String getPrefAnswer() {
        return prefAnswer;
    }
    
    /*setPrefAnswer() used to set preffered answer*/
    public void setPrefAnswer(String prefAnswer) {
        this.prefAnswer = prefAnswer.trim();
    }
    
    /*getLoginId() used to get login id*/
    public String getLoginId() {
        return loginId;
    }
    
    /*setLoginId(String loginId) used to set login id*/
    public void setLoginId(String loginId) {
        this.loginId = loginId.trim();
    }
    
    /*getPassword() used to get password*/
    public String getPassword() {
        return password;
    }
    
    /*setPassword(String password) used to set password*/
    public void setPassword(String password) {
        this.password = password.trim();
    }
    
    /*getGenderList() used to get gender list*/
    public List getGenderList() {
        return genderList;
    }
    
    /*setGenderList(List genderList) used to set gender list*/
    public void setGenderList(List genderList) {
        this.genderList = genderList;
    }
    
    /*getMaritalStatusList() used to get marital status*/
    public List getMaritalStatusList() {
        return maritalStatusList;
    }
    
    /*setMaritalStatusList(List maritalStatusList) used to set marital status*/
    public void setMaritalStatusList(List maritalStatusList) {
        this.maritalStatusList = maritalStatusList;
    }
    
    /*getCoutryList() used to get country list*/
    public List getCoutryList() {
        return coutryList;
    }
    
    /*setCoutryList(List coutryList) used to set country list*/
    public void setCoutryList(List coutryList) {
        this.coutryList = coutryList;
    }
    
    /*getPrefferedQuestionsMap() used to get preffered questions map*/
    public Map getPrefferedQuestionsMap() {
        return prefferedQuestionsMap;
    }
    
    /*setPrefferedQuestionsMap(Map prefferedQuestionsMap) used to set preffered questions map*/
    public void setPrefferedQuestionsMap(Map prefferedQuestionsMap) {
        this.prefferedQuestionsMap = prefferedQuestionsMap;
    }
    
    /*getCurStatus() used to get current status*/
    public String getCurStatus() {
        return curStatus;
    }
    
    /*setCurStatus(String curStatus) used to set current status*/
    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus.trim();
    }
    
    /*getDeletedFlag() used to get delete flag*/
    public int getDeletedFlag() {
        return deletedFlag;
    }
    
    /*setDeletedFlag(int deletedFlag) used to set delete flag*/
    public void setDeletedFlag(int deletedFlag) {
        this.deletedFlag = 0;
    }
    
    /*getId() used to get id*/
    public int getId() {
        return id;
    }
    
    /*setId(int id) used to set id*/
    public void setId(int id) {
        this.id = id;
    }
    
    /*this is abstract method*/
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getWorkingCountry() {
        return workingCountry;
    }

    public void setWorkingCountry(String workingCountry) {
        this.workingCountry = workingCountry;
    }

   
    
    
}
