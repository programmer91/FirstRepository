/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :   April 4, 2008, 11:14 PM
 *
 * Author  :  Hari Krishna Kondala <hkondala@miraclesoft.com>
 *
 * File    : EmpVTO .java
 *
 * Copyright 2008 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.profile;

import java.sql.Date;

/**
 *
 * @author miracle
 */
public class EmpProfileVTO {
    
     /** The skillSet is used for storing skillSet of employee. */
    private String skillSet;
    
     /** The firstName is used for storing firstname of employee. */
    private String firstName;
    
    /** The lastName is used for storing lastName of employee. */
    private String lastName;
    
    /** The middleName is used for storing middleName of employee. */
    private String middleName;
    
    /** The aliasName is used for storing aliasName of employee. */
    private String aliasName;
    
    /** The gender is used for storing gender of employee. */
    private String gender;
    
    /** The maritalStatus is used for storing maritalStatus of employee. */
    private String maritalStatus;
    
    /** The country is used for storing contry of employee. */
    private String country;
    
    /** The ssn is used for storing ssn of employee. */
    private String ssn;
    
    private String empno;
    
    private String nsrno;
    
    private Date birthDate;
    
    /** The offBirthDate is used for storing official date of birth of employee. */
    private Date offBirthDate;
    
    /** The  hireDate is used for storing hiredate of employee. */
    private Date hireDate;
    
    /** The anniversaryDate is used for storing anniversaryDate of employee.*/
    private Date anniversaryDate;
    
    /** The workPhoneNo is used for storing wormPhoneNo of employee. */
    private String workPhoneNo;
    
    /** The altPhoneNo is used for storing alternatePhoneNo of employee. */
    private String altPhoneNo;
    
    /** The homePhoneNo is used for storing homePhoneNo of employee. */
    private String homePhoneNo;
    
    /** The cellPhoneNo is used for storing cellPhoneNo of employee. */
    private String cellPhoneNo;
    
    /** The officeEmail is used for storing officeEmail of employee. */
    private String officeEmail;
    
    /** The hotelPhoneNo is used for storing hotelPhoneNo of employee. */
    private String hotelPhoneNo;
    
    /** The personalEmail is used for storing personal Email Of employee. */
    private String personalEmail;
    
    /** The indiaPhoneNo is used for storing indiaPhoneNo of employee. */
    private String indiaPhoneNo;
    
    /** The otherEmail is used for storing other Email of employee. */
    private String otherEmail;
    
    /** The faxNo is used for storing Fax of employee. */
    private String faxNo;
    
    private String workingCountry;
    
          //New Fileds For Confedential Info Date : 08/19/2014
     private String bankName;
private String accNum;
private String nameAsPerAcc;
private String ifscCode;
private String phyChallenged;
private String phyCategory;
private String aadharNum;
private String aadharName;
private String nameAsPerPan;

    private String itgBatch;
    
    private String uanNo;
private String pfno;

private Date passportExp;
private String passportNo;
private String reportsTo;
private String operationContact;
private String aboutMe;
    /**
     * Creates a new instance of EmpProfileVTO
     */
    public EmpProfileVTO() {
    }

    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getNsrno() {
        return nsrno;
    }

    public void setNsrno(String nsrno) {
        this.nsrno = nsrno;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getOffBirthDate() {
        return offBirthDate;
    }

    public void setOffBirthDate(Date offBirthDate) {
        this.offBirthDate = offBirthDate;
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

    public String getWorkPhoneNo() {
        return workPhoneNo;
    }

    public void setWorkPhoneNo(String workPhoneNo) {
        this.workPhoneNo = workPhoneNo;
    }

    public String getAltPhoneNo() {
        return altPhoneNo;
    }

    public void setAltPhoneNo(String altPhoneNo) {
        this.altPhoneNo = altPhoneNo;
    }

    public String getHomePhoneNo() {
        return homePhoneNo;
    }

    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }

    public String getHotelPhoneNo() {
        return hotelPhoneNo;
    }

    public void setHotelPhoneNo(String hotelPhoneNo) {
        this.hotelPhoneNo = hotelPhoneNo;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getIndiaPhoneNo() {
        return indiaPhoneNo;
    }

    public void setIndiaPhoneNo(String indiaPhoneNo) {
        this.indiaPhoneNo = indiaPhoneNo;
    }

    public String getOtherEmail() {
        return otherEmail;
    }

    public void setOtherEmail(String otherEmail) {
        this.otherEmail = otherEmail;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getWorkingCountry() {
        return workingCountry;
    }

    public void setWorkingCountry(String workingCountry) {
        this.workingCountry = workingCountry;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the accNum
     */
    public String getAccNum() {
        return accNum;
    }

    /**
     * @param accNum the accNum to set
     */
    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    /**
     * @return the nameAsPerAcc
     */
    public String getNameAsPerAcc() {
        return nameAsPerAcc;
    }

    /**
     * @param nameAsPerAcc the nameAsPerAcc to set
     */
    public void setNameAsPerAcc(String nameAsPerAcc) {
        this.nameAsPerAcc = nameAsPerAcc;
    }

    /**
     * @return the ifscCode
     */
    public String getIfscCode() {
        return ifscCode;
    }

    /**
     * @param ifscCode the ifscCode to set
     */
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    /**
     * @return the phyChallenged
     */
    public String getPhyChallenged() {
        return phyChallenged;
    }

    /**
     * @param phyChallenged the phyChallenged to set
     */
    public void setPhyChallenged(String phyChallenged) {
        this.phyChallenged = phyChallenged;
    }

    /**
     * @return the phyCategory
     */
    public String getPhyCategory() {
        return phyCategory;
    }

    /**
     * @param phyCategory the phyCategory to set
     */
    public void setPhyCategory(String phyCategory) {
        this.phyCategory = phyCategory;
    }

    /**
     * @return the aadharNum
     */
    public String getAadharNum() {
        return aadharNum;
    }

    /**
     * @param aadharNum the aadharNum to set
     */
    public void setAadharNum(String aadharNum) {
        this.aadharNum = aadharNum;
    }

    /**
     * @return the aadharName
     */
    public String getAadharName() {
        return aadharName;
    }

    /**
     * @param aadharName the aadharName to set
     */
    public void setAadharName(String aadharName) {
        this.aadharName = aadharName;
    }

    /**
     * @return the nameAsPerPan
     */
    public String getNameAsPerPan() {
        return nameAsPerPan;
    }

    /**
     * @param nameAsPerPan the nameAsPerPan to set
     */
    public void setNameAsPerPan(String nameAsPerPan) {
        this.nameAsPerPan = nameAsPerPan;
    }

    /**
     * @return the itgBatch
     */
    public String getItgBatch() {
        return itgBatch;
    }

    /**
     * @param itgBatch the itgBatch to set
     */
    public void setItgBatch(String itgBatch) {
        this.itgBatch = itgBatch;
    }

    /**
     * @return the uanNo
     */
    public String getUanNo() {
        return uanNo;
    }

    /**
     * @param uanNo the uanNo to set
     */
    public void setUanNo(String uanNo) {
        this.uanNo = uanNo;
    }

    /**
     * @return the pfno
     */
    public String getPfno() {
        return pfno;
    }

    /**
     * @param pfno the pfno to set
     */
    public void setPfno(String pfno) {
        this.pfno = pfno;
    }

    public Date getPassportExp() {
        return passportExp;
    }

    public void setPassportExp(Date passportExp) {
        this.passportExp = passportExp;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    /**
     * @return the reportsTo
     */
    public String getReportsTo() {
        return reportsTo;
    }

    /**
     * @param reportsTo the reportsTo to set
     */
    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    /**
     * @return the operationContact
     */
    public String getOperationContact() {
        return operationContact;
    }

    /**
     * @param operationContact the operationContact to set
     */
    public void setOperationContact(String operationContact) {
        this.operationContact = operationContact;
    }

    /**
     * @return the aboutMe
     */
    public String getAboutMe() {
        return aboutMe;
    }

    /**
     * @param aboutMe the aboutMe to set
     */
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
    
}
