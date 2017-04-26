/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :com.mss.mirage.recruitment
 *
 * Date    :   Created on November 16, 2007, 7:56 PM
 *
 * Author  : Kondaiah Adapa<kadapa@miraclesoft.com>
 *
 * File    : ConsultantVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.recruitment;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * The <code>ConsultantVTO </code>  class is used for getting  Consultant details from
 * <i>ConsultantAdd.jsp</i>    
 *
 * @author  Kondaiah Adapa<a href="mailto:kadapa@miraclesoft.com">kadapa@miraclesoft.com</a>
 *
 *  
   @version 1.122, 05/05/04
   @since   1.0
  */

public class ConsultantVTO {
    // for updating purpose
    
   /** The consultantRecordId is Useful to get the consultant current record Id. */
    private int consultantRecordId;
    
   /** The homAddressId is Useful to get the consultant home address Id. */ 
    private int homAddressId;
    
  /** The offAddressId is Useful to get the consultant offshore address Id. */  
    private int offAddressId;
    
  /** The curAddressId is Useful to get the consultant current address  Id. */  
    private int curAddressId;
    
  /** The othAddressId is Useful to get the consultant other address Id. */  
    private int othAddressId;
    
    /** The id is Useful to get the Consultant Id. */
    private int   id;
    
    /** The loginId is Useful to get the user login Id. */
    private String loginId ;
    
    /** The password is Useful to get the user password. */
    private String password ;
    
    /** The country is Useful to get the country name. */
    private String country ;
    
    /** The practiceId is Useful to get the Consultant practiceId. */
    private String practiceId ;
    
    /** The titleTypeId is Useful to get the Consultant titleTypeId . */
    private String titleTypeId ;
    
    /** The consultantType is Useful to get the type of Consultant. */
    private String consultantType;
    
    /** The work Authorization Field is useful to get the work Authorization of Consultant**/
    private String workAuthorization;
    
    /** The curStatus is Useful to get the current status of Consultant. */
    private String curStatus;
    
    /** The recContactId is Useful to get the Consultant recContactId. */
    private int recContactId;
    
    /** The deletedFlag is Useful to get the Consultant deletedFlag. */
    private int deletedFlag;
    
    /** The laName is Useful to get the  last name of the Consultant. */
    private String laName;
    
    /** The fiName is Useful to get the first name of the Consultant. */
    private String fiName;
    
    /** The miName is Useful to get the middile Name of  the Consultant. */
    private String miName;
    
    /** The aliasName is Useful to get the aliasName of the Consultant. */
    private String aliasName;
    
    /** The ssn is Useful to get the ssn number of the Consultant. */
    private String ssn;
    
    /** The gender is Useful to get the gender value of the Consultant. */
    private String gender;
    
    /** The maritalStatus is Useful to get the maritalStatus of the Consultant . */
    private String maritalStatus;
    
    /** The email1 is Useful to get the first email of Consultant. */
    private String email1;
    
    /** The email2 is Useful to get the second email of Consultant. */
    private String email2;
    
    /** The email3 is Useful to get the thrid email of Consultant. */
    private String email3;
    
    /** The workPhoneNo is Useful to get the workPhoneNo of the Consultant. */
    private String workPhoneNo;
    
    /** The  homePhoneNo is Useful to get  the homePhoneNo of the Consultant . */
    private String homePhoneNo;
    
    /** The  alterPhoneNo is Useful to get  the alterPhoneNo of the Consultant . */
    private String alterPhoneNo;
    
    /** The  cellPhoneNo is Useful to get  the cellPhoneNo of the Consultant . */
    private String cellPhoneNo;
    
    /** The  hotelPhoneNo is Useful to get   the hotelPhoneNo of the Consultant . */
    private String hotelPhoneNo;
    
    /** The   indiaPhoneNo is Useful to get  the  indiaPhoneNo of the Consultant . */
    private String indiaPhoneNo;
    
    /** The  faxPhoneNo is Useful to get the faxPhoneNo of the Consultant . */
    private String faxPhoneNo;
    
/** The refferedBy  is Useful to get the by whom the consultant reffered*/
    private String refferedBy;
    
   /** The refferalBonus  is Useful to get refferal bonus*/
    private String refferalBonus;
    
    /** The offShoreAddressId is Useful to get the offShoreAddressId. */
    private int offShoreAddressId;
    
    /** The curProjectAddressId is Useful to get the current project address id . */
    private int curProjectAddressId ;
    
    /** The homeAddressId is Useful to get the homeAddressId . */
    private int homeAddressId;
    
    /** The otherAddressId is Useful to get the otherAddressId . */
    private int otherAddressId;
    
    /** The officialDob is Useful to get the official date of birth of the Consultant. */
    private Date officialDob;
    
    /** The dob is Useful to get the date of birth of the Consultant. */
    private Date dob;
    
    /** The hireDate is Useful to get hireDate of the Consultant. */
    private Date hireDate;
    
    /** The anniversaryDate is Useful to get the anniversaryDate of the Consultant. */
    private Date anniversaryDate;
    
    /** The firstContactDate is Useful to get the firstContactDate of the Consultant. */
    private Date firstContactDate;
    
    /** The preferedQuestion is Useful to get the preferedQuestion. */
    private String preferedQuestion;
    
    /** The preferedAnswer is Useful to get the preferedAnswer. */
    private String preferedAnswer;
    
    /** The comments is Useful to get the comments about Consultant. */
    private String comments;
    
    /** The ratePerHour is Useful to get the ratePerHour. */
    private String ratePerHour;
    
    /** The currentEmployer is Useful to get the current Employer . */
    private String currentEmployer;
    
    /** The rateNegotiated is Useful to get the rateNegotiated. */
    private String rateNegotiated;
    
     /** The lastContactDate is Useful to get the last Contact Date of the Consultant. */
    private Date lastContactDate;
    
  /** The lastContactBy is Useful to get the by whome the Consultant last contacted. */
    private int lastContactBy;
    
   /** The createdBy  is Useful to identify by whom it was created. */ 
    private String createdBy;
    
      /** The createdDate  is useful to  identify on which date it was created. */
    private Timestamp createdDate;
    
   /** The modifiedBy  is useful to  identify by whom modified this screen */
    private String modifiedBy;
    
   /** The modifiedDate  is useful to  identify ModifiedDate. */
    private Timestamp modifiedDate;
    
    
  // variables for address table
    
    /** The offAddressLine1 is Useful to get the offshore project address line1. */
    private String offAddressLine1;
    
    /** The offAddressLine2 is Useful to get the offshore project address line2. */
    private String offAddressLine2;
    
    /** The offMailStop is Useful to get  the project address mailstop. */
    private String offMailStop;
    
    /** The offCity is Useful to get the offshore project address city. */
    private String offCity;
    
    /** The offState is Useful to get the offshore project address state. */
    private String offState;
    
    /** The HomeCountry is Useful to get  the home address country. */
    private String HomeCountry;
    
    /** The offZip is Useful to get  the offshore project address  zipcode. */
    private String offZip;
    
    /** The curAddressLine1 is Useful to get the current project address line1. */
    private String curAddressLine1;
    
    /** The curAddressLine2 is Useful to get the current project address line2. */
    private String curAddressLine2;
    
    /** The curMailStop is Useful to get the current project address mail stop. */
    private String curMailStop;
    
    /** The curCity is Useful to get the current project address city. */
    private String curCity;
    
    /** The curState is Useful to get the current project address state. */
    private String curState;
    
    /** The ProjectCountry  is Useful to get current project address country. */
    private String ProjectCountry;
    
    /** The curZip is Useful to get the current project address zip code. */
    private String curZip;
    
    /** The AddressLine1 is Useful to get the home address line1. */
    private String AddressLine1;
    
    /** The AddressLine2 is Useful to get the home address line2. */
    private String AddressLine2;
    
    /** The MailStop is Useful to get the home address mailstop. */
    private String MailStop;
    
    /** The City is Useful to get the home address city. */
    private String City;
    
    /** The State is Useful to get the home address state. */
    private String State;
    
    /** The Country is Useful to get the off shore address country. */
    private String OffShoreCountry;
    
    /** The Zip is Useful to get  home address zip code. */
    private String Zip;
    
    /** The othAddressLine1 is Useful to get the other address line1. */
    private String othAddressLine1;
    
    /** The othAddressLine1 is Useful to get the other address line2. */
    private String othAddressLine2;
    
    /** The othMailStop is Useful to get the other address mail stop. */
    private String othMailStop;
    
    /** The othCity is Useful to get the other address city . */
    private String othCity;
    
    /** The othState is Useful to get the other address state. */
    private String othState;
    
    /** The othCountry is Useful to get the  other address country. */
    private String OtherCountry;
    
    /** The othZip is Useful to get  the other address zip code. */
    private String othZip;
    
    
    private Date availableFrom;
    
    private String description;
    
    /** The dueDate is used for storing dueDate. */
    private String dueDate;
    
    /*   Pre Configurable Varaibles */
    
     /**
     * A List object with an genderList object read from a
     * full List of data.
     */
    private List genderList;
    
   /**
     * A List object with an maritalStatusList object  read from a
     * full List of data.
     */
    private List maritalStatusList;
    
     /**
     * A List object with an countryList object  read from a
     * full List of  avilable Countries data.
     */
    private List countryList;
    
   /**
     * A Map object with an prefferedQuestionsList object   read from a
     * full List of prefferedQuestions.
     */
    private Map prefferedQuestionsList;
    
     /**
     * A List object with an consultantTypesList object  read from a
     * full List of data.
     */
    private List consultantTypesList;
    
     /**
     * A List object with an titleTypeIdList object  read from a
     * full List of data.
     */
    private List titleTypeIdList;
    
     /**
     * A List object with an currStatusList object  read from a
     * full List of data.
     */
    private List currStatusList;
    
     /** The searchName is Useful to search a consultant by name. */
    private String searchName;
    
    /** The searchPhone is Useful to search a consultant by phone. */
    private String searchPhone;
    
 /**
     * The actionName  is Useful to get the Name of an Action and depend on this
     * actionName the navigation of screens happens.
     */  
    private String actionName;
    
 /** The viewDob is Useful to get the birth date of the consultant . */   
    private String viewDob;
    
 /** The viewOfficialDob is Useful to get the official birth date of the consultant . */
    private String viewOfficialDob;
    
  /** The viewHireDate is Useful to get the hire date of the consultant . */  
    private String viewHireDate;
    
  /** The viewAnniversaryDate is Useful to get the Anniversary date of the consultant . */
    private String viewAnniversaryDate;
    
  /** The viewFirstContactDate is Useful to get the FirstContactDate  of the consultant . */
    private String viewFirstContactDate;
    
  /** The viewLastContactDate is Useful to get the LastContactDate  of the consultant . */
    private String viewLastContactDate;
         
    private String skills;
    
    private String industryId;
    
    private String currentAction;
    
    private String objectId;
    
    //Variables For Consultant Acivities.
    
    private String activityType;
    
    private String assignedTo;
    
    private String status;
    
    private Date interviewDate;
    
    private Date reportingDate;
    
    private String comments1;
    
    private String availability;
    
    private int consultantId;
    
    private String source;
    
    
    //NEW 
     private String org;
    
    private String exp;
     private String techEval;
     private String available;
     
     private String preferredState;
     private int isReject;
     
     
     
    /** Creates a new instance of ConsultantVTO */
    public ConsultantVTO() {
    }
    
    public List getMaritalStatusList() {
        return maritalStatusList;
    }
    
    public void setMaritalStatusList(List maritalStatusList) {
        this.maritalStatusList = maritalStatusList;
    }
    
    public List getCountryList() {
        return countryList;
    }
    
    public void setCountryList(List countryList) {
        this.countryList = countryList;
    }
    
    public Map getPrefferedQuestionsList() {
        return prefferedQuestionsList;
    }
    
    public void setPrefferedQuestionsList(Map prefferedQuestionsList) {
        this.prefferedQuestionsList = prefferedQuestionsList;
    }
    
    public List getConsultantTypesList() {
        return consultantTypesList;
    }
    
    public void setConsultantTypesList(List consultantTypesList) {
        this.consultantTypesList = consultantTypesList;
    }
    
    /**
     * setter and getter methods for Adding a new consultant
     */
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLoginId() {
        return loginId;
    }
    
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getPracticeId() {
        return practiceId;
    }
    
    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }
    
    public String getTitleTypeId() {
        return titleTypeId;
    }
    
    public void setTitleTypeId(String titleTypeId) {
        this.titleTypeId = titleTypeId;
    }
    
    public String getConsultantType() {
        return consultantType;
    }
    
    public void setConsultantType(String consultantType) {
        this.consultantType = consultantType;
    }
    
    public String getCurStatus() {
        return curStatus;
    }
    
    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus;
    }
    
    public int getRecContactId() {
        return recContactId;
    }
    
    public void setRecContactId(int recContactId) {
        this.recContactId = recContactId;
    }
    
    public int getDeletedFlag() {
        return deletedFlag;
    }
    
    public void setDeletedFlag(int deletedFlag) {
        this.deletedFlag = deletedFlag;
    }
    
    public String getLaName() {
        return laName;
    }
    
    public void setLaName(String laName) {
        this.laName = laName;
    }
    
    public String getFiName() {
        return fiName;
    }
    
    public void setFiName(String fiName) {
        this.fiName = fiName;
    }
    
    public String getMiName() {
        return miName;
    }
    
    public void setMiName(String miName) {
        this.miName = miName;
    }
    
    public String getAliasName() {
        return aliasName;
    }
    
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
    
    public String getSsn() {
        return ssn;
    }
    
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getMaritalStatus() {
        return maritalStatus;
    }
    
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    
    public String getEmail1() {
        return email1;
    }
    
    public void setEmail1(String email1) {
        this.email1 = email1;
    }
    
    public String getEmail2() {
        return email2;
    }
    
    public void setEmail2(String email2) {
        this.email2 = email2;
    }
    
    public String getEmail3() {
        return email3;
    }
    
    public void setEmail3(String email3) {
        this.email3 = email3;
    }
    
    public String getWorkPhoneNo() {
        return workPhoneNo;
    }
    
    public void setWorkPhoneNo(String workPhoneNo) {
        this.workPhoneNo = workPhoneNo;
    }
    
    public String getHomePhoneNo() {
        return homePhoneNo;
    }
    
    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }
    
    public String getAlterPhoneNo() {
        return alterPhoneNo;
    }
    
    public void setAlterPhoneNo(String alterPhoneNo) {
        this.alterPhoneNo = alterPhoneNo;
    }
    
    public String getCellPhoneNo() {
        return cellPhoneNo;
    }
    
    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }
    
    public String getHotelPhoneNo() {
        return hotelPhoneNo;
    }
    
    public void setHotelPhoneNo(String hotelPhoneNo) {
        this.hotelPhoneNo = hotelPhoneNo;
    }
    
    public String getIndiaPhoneNo() {
        return indiaPhoneNo;
    }
    
    public void setIndiaPhoneNo(String indiaPhoneNo) {
        this.indiaPhoneNo = indiaPhoneNo;
    }
    
    public String getFaxPhoneNo() {
        return faxPhoneNo;
    }
    
    public void setFaxPhoneNo(String faxPhoneNo) {
        this.faxPhoneNo = faxPhoneNo;
    }
    
    public String getRefferedBy() {
        return refferedBy;
    }
    
    public void setRefferedBy(String refferedBy) {
        this.refferedBy = refferedBy;
    }
    
    public String getRefferalBonus() {
        return refferalBonus;
    }
    
    public void setRefferalBonus(String refferalBonus) {
        this.refferalBonus = refferalBonus;
    }
    
    public int getOffShoreAddressId() {
        return offShoreAddressId;
    }
    
    public void setOffShoreAddressId(int offShoreAddressId) {
        this.offShoreAddressId = offShoreAddressId;
    }
    
    public int getCurProjectAddressId() {
        return curProjectAddressId;
    }
    
    public void setCurProjectAddressId(int curProjectAddressId) {
        this.curProjectAddressId = curProjectAddressId;
    }
    
    public int getHomeAddressId() {
        return homeAddressId;
    }
    
    public void setHomeAddressId(int homeAddressId) {
        this.homeAddressId = homeAddressId;
    }
    
    public int getOtherAddressId() {
        return otherAddressId;
    }
    
    public void setOtherAddressId(int otherAddressId) {
        this.otherAddressId = otherAddressId;
    }
    
    public Date getHireDate() {
        return hireDate;
    }
    
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    
    public Date getAnniversaryDate() {
        return anniversaryDate;
    }
    
    public void setAnniversaryDate(Date anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }
    
    public Date getFirstContactDate() {
        return firstContactDate;
    }
    
    public void setFirstContactDate(Date firstContactDate) {
        this.firstContactDate = firstContactDate;
    }
    
    public String getPreferedQuestion() {
        return preferedQuestion;
    }
    
    public void setPreferedQuestion(String preferedQuestion) {
        this.preferedQuestion = preferedQuestion;
    }
    
    public String getPreferedAnswer() {
        return preferedAnswer;
    }
    
    public void setPreferedAnswer(String preferedAnswer) {
        this.preferedAnswer = preferedAnswer;
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public String getRatePerHour() {
        return ratePerHour;
    }
    
    public void setRatePerHour(String ratePerHour) {
        this.ratePerHour = ratePerHour;
    }
    
    public String getCurrentEmployer() {
        return currentEmployer;
    }
    
    public void setCurrentEmployer(String currentEmployer) {
        this.currentEmployer = currentEmployer;
    }
    
    public String getRateNegotiated() {
        return rateNegotiated;
    }
    
    public void setRateNegotiated(String rateNegotiated) {
        this.rateNegotiated = rateNegotiated;
    }
    
    public Date getLastContactDate() {
        return lastContactDate;
    }
    
    public void setLastContactDate(Date lastContactDate) {
        this.lastContactDate = lastContactDate;
    }
    
    public int getLastContactBy() {
        return lastContactBy;
    }
    
    public void setLastContactBy(int lastContactBy) {
        this.lastContactBy = lastContactBy;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {        
        this.createdBy = createdBy;
    }
    
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    
    public String getModifiedBy() {
        return modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }
    
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    } 
    
    public Date getOfficialDob() {
        return officialDob;
    }
    
    public void setOfficialDob(Date officialDob) {
        this.officialDob = officialDob;
    }
    
    public Date getDob() {
        return dob;
    }
    
    public void setDob(Date dob) {
        this.dob = dob;
    }
    /**
     * setter and getter methods for seach a consultant
     */
    
    public String getSearchName() {
        return searchName;
    }
    
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
    
    public String getSearchPhone() {
        return searchPhone;
    }
    
    public void setSearchPhone(String searchPhone) {
        this.searchPhone = searchPhone;
    }
    
    public List getTitleTypeIdList() {
        return titleTypeIdList;
    }
    
    public void setTitleTypeIdList(List titleTypeIdList) {
        this.titleTypeIdList = titleTypeIdList;
    }
    
    public List getCurrStatusList() {
        return currStatusList;
    }
    
    public void setCurrStatusList(List currStatusList) {
        this.currStatusList = currStatusList;
    }
    
    
    
    //setters and getter methods for address table of the consultant
    public String getOffAddressLine1() {
        return offAddressLine1;
    }
    
    public void setOffAddressLine1(String offAddressLine1) {
        this.offAddressLine1 = offAddressLine1;
    }
    
    public String getOffAddressLine2() {
        return offAddressLine2;
    }
    
    public void setOffAddressLine2(String offAddressLine2) {
        this.offAddressLine2 = offAddressLine2;
    }
    
    public String getOffMailStop() {
        return offMailStop;
    }
    
    public void setOffMailStop(String offMailStop) {
        this.offMailStop = offMailStop;
    }
    
    public String getOffCity() {
        return offCity;
    }
    
    public void setOffCity(String offCity) {
        this.offCity = offCity;
    }
    
    public String getOffState() {
        return offState;
    }
    
    public void setOffState(String offState) {
        this.offState = offState;
    }
    
    
    
    public String getOffZip() {
        return offZip;
    }
    
    public void setOffZip(String offZip) {
        this.offZip = offZip;
    }
    
    public String getCurAddressLine1() {
        return curAddressLine1;
    }
    
    public void setCurAddressLine1(String curAddressLine1) {
        this.curAddressLine1 = curAddressLine1;
    }
    
    public String getCurAddressLine2() {
        return curAddressLine2;
    }
    
    public void setCurAddressLine2(String curAddressLine2) {
        this.curAddressLine2 = curAddressLine2;
    }
    
    public String getCurMailStop() {
        return curMailStop;
    }
    
    public void setCurMailStop(String curMailStop) {
        this.curMailStop = curMailStop;
    }
    
    public String getCurCity() {
        return curCity;
    }
    
    public void setCurCity(String curCity) {
        this.curCity = curCity;
    }
    
    public String getCurState() {
        return curState;
    }
    
    public void setCurState(String curState) {
        this.curState = curState;
    }
    
    
    
    public String getCurZip() {
        return curZip;
    }
    
    public void setCurZip(String curZip) {
        this.curZip = curZip;
    }
    
    public String getAddressLine1() {
        return AddressLine1;
    }
    
    public void setAddressLine1(String AddressLine1) {
        this.AddressLine1 = AddressLine1;
    }
    
    public String getAddressLine2() {
        return AddressLine2;
    }
    
    public void setAddressLine2(String AddressLine2) {
        this.AddressLine2 = AddressLine2;
    }
    
    public String getMailStop() {
        return MailStop;
    }
    
    public void setMailStop(String MailStop) {
        this.MailStop = MailStop;
    }
    
    public String getCity() {
        return City;
    }
    
    public void setCity(String City) {
        this.City = City;
    }
    
    public String getState() {
        return State;
    }
    
    public void setState(String State) {
        this.State = State;
    }
    
    public String getZip() {
        return Zip;
    }
    
    public void setZip(String Zip) {
        this.Zip = Zip;
    }
    
    public String getOthAddressLine1() {
        return othAddressLine1;
    }
    
    public void setOthAddressLine1(String othAddressLine1) {
        this.othAddressLine1 = othAddressLine1;
    }
    
    public String getOthAddressLine2() {
        return othAddressLine2;
    }
    
    public void setOthAddressLine2(String othAddressLine2) {
        this.othAddressLine2 = othAddressLine2;
    }
    
    public String getOthMailStop() {
        return othMailStop;
    }
    
    public void setOthMailStop(String othMailStop) {
        this.othMailStop = othMailStop;
    }
    
    public String getOthCity() {
        return othCity;
    }
    
    public void setOthCity(String othCity) {
        this.othCity = othCity;
    }
    
    public String getOthState() {
        return othState;
    }
    
    public void setOthState(String othState) {
        this.othState = othState;
    }
    
    
    public String getOthZip() {
        return othZip;
    }
    
    public void setOthZip(String othZip) {
        this.othZip = othZip;
    } 
    
    public String getHomeCountry() {
        return HomeCountry;
    }
    
    public void setHomeCountry(String HomeCountry) {
        this.HomeCountry = HomeCountry;
    }
    
    public String getProjectCountry() {
        return ProjectCountry;
    }
    
    public void setProjectCountry(String ProjectCountry) {
        this.ProjectCountry = ProjectCountry;
    }
    
    public String getOffShoreCountry() {
        return OffShoreCountry;
    }
    
    public void setOffShoreCountry(String OffShoreCountry) {
        this.OffShoreCountry = OffShoreCountry;
    }
    
    public String getOtherCountry() {
        return OtherCountry;
    }
    
    public void setOtherCountry(String OtherCountry) {
        this.OtherCountry = OtherCountry;
    } 

    public List getGenderList() {
        return genderList;
    }

    public void setGenderList(List genderList) {
        this.genderList = genderList;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getConsultantRecordId() {
        return consultantRecordId;
    }

    public void setConsultantRecordId(int consultantRecordId) {
        this.consultantRecordId = consultantRecordId;
    }

    public int getHomAddressId() {
        return homAddressId;
    }

    public void setHomAddressId(int homAddressId) {
        this.homAddressId = homAddressId;
    }

    public int getOffAddressId() {
        return offAddressId;
    }

    public void setOffAddressId(int offAddressId) {
        this.offAddressId = offAddressId;
    }

    public int getCurAddressId() {
        return curAddressId;
    }

    public void setCurAddressId(int curAddressId) {
        this.curAddressId = curAddressId;
    }

    public int getOthAddressId() {
        return othAddressId;
    }

    public void setOthAddressId(int othAddressId) {
        this.othAddressId = othAddressId;
    }

    public String getViewDob() {
        return viewDob;
    }

    public void setViewDob(String viewDob) {
        this.viewDob = viewDob;
    }

    public String getViewOfficialDob() {
        return viewOfficialDob;
    }

    public void setViewOfficialDob(String viewOfficialDob) {
        this.viewOfficialDob = viewOfficialDob;
    }

    public String getViewHireDate() {
        return viewHireDate;
    }

    public void setViewHireDate(String viewHireDate) {
        this.viewHireDate = viewHireDate;
    }

    public String getViewAnniversaryDate() {
        return viewAnniversaryDate;
    }

    public void setViewAnniversaryDate(String viewAnniversaryDate) {
        this.viewAnniversaryDate = viewAnniversaryDate;
    }

    public String getViewFirstContactDate() {
        return viewFirstContactDate;
    }

    public void setViewFirstContactDate(String viewFirstContactDate) {
        this.viewFirstContactDate = viewFirstContactDate;
    }

    public String getViewLastContactDate() {
        return viewLastContactDate;
    }

    public void setViewLastContactDate(String viewLastContactDate) {
        this.viewLastContactDate = viewLastContactDate;
    }

    public String getWorkAuthorization() {
        return workAuthorization;
    }

    public void setWorkAuthorization(String workAuthorization) {
        this.workAuthorization = workAuthorization;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Date getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(Date reportingDate) {
        this.reportingDate = reportingDate;
    }

    public String getComments1() {
        return comments1;
    }

    public void setComments1(String comments1) {
        this.comments1 = comments1;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }
    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    /**
     * @return the exp
     */
    public String getExp() {
        return exp;
    }

    /**
     * @param exp the exp to set
     */
    public void setExp(String exp) {
        this.exp = exp;
    }

    /**
     * @return the techEval
     */
    public String getTechEval() {
        return techEval;
    }

    /**
     * @param techEval the techEval to set
     */
    public void setTechEval(String techEval) {
        this.techEval = techEval;
    }

    /**
     * @return the available
     */
    public String getAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(String available) {
        this.available = available;
    }

    /**
     * @return the preferredState
     */
    public String getPreferredState() {
        return preferredState;
    }

    /**
     * @param preferredState the preferredState to set
     */
    public void setPreferredState(String preferredState) {
        this.preferredState = preferredState;
    }

    public int getIsReject() {
        return isReject;
    }

    public void setIsReject(int isReject) {
        this.isReject = isReject;
    }
    
}
