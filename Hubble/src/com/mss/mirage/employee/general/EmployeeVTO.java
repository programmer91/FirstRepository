/*
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : com.mss.mirage.employee.genaral
 *
 * Date    :  September 24, 2007, 10:18 PM
 *
 * Author  : Praveen Kumar Ralla<pralla@miraclesoft.com>
 *
 * File    : EmployeeVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.employee.general;

import java.sql.Date;
import java.sql.Timestamp;
/*
 * @(#)EmployeeService.java 1.0 November 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 * @since 1.0
 */
public class EmployeeVTO {
    
    /** The id is used for storing id of employee. */
    private int id;
    
    /** The loginId is used for storing loginId of employee. */
    private String loginId;
    
    /** The password is used for storing password of employee. */
    private String password;
    
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
    
    /** The currStatus is used for storing currentStatus of employee. */
    private String currStatus;
    
    /** The empTypeId is used for storing employeeTypeId of employee. */
    private String empTypeId;
    
    /** The orgId is used for storing OraganizationId  of employee. */
    private String orgId;
    
    /** The opsContactId is used for storing ContactId */
    private String opsContactId;
    
    /** The teamId is used for storing teamId. */
    private String teamId;
    
    /** The practiceId is used for storing PracticeId.  */
    private String practiceId;
    
    private String subPractice;
    
    /** The titleId is used for storing titleId. */
    private String titleId;
    
    /** The industryId is used for storing industryId */
    private String industryId;
    
    /** The departmentId is used for storing DepartmentId. */
    private String departmentId;
    
    /** The birthDate is used for storing dateOfBirth of employee. */
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
    
    /** The lastContactBy is used for storing lastContact PerosonId. */
    private String lastContactBy;
    
    /** The modifiedBy is used for storing modified Person Id. */
    private String modifiedBy;
    
    /** The modifiedDate is used for storing modifiedDate. */
    private Timestamp modifiedDate;
    
    /** The createdBy is used for storing created Person id. */
    private String createdBy;
    
    /**  The createdDate is used for storing created Date. */
    private Timestamp createdDate;
    
    /** isManager is used to store the flag to identify user has manager role*/
    private boolean isManager;
    
    private boolean isTeamLead;
    
    /**reportsTo is used to store the User Id of reporting person*/
    private String reportsTo;
    
    
    private String empState;
    private Date stateStartDate;
    private Date stateEndDate;
  //  private float intRatePerHour;
   // private float invRatePerHour;
    private String skillSet;
    private String prjName;
  //  private double ctcPerYear;
    private String itgBatch;
    private String isOnsite;
    
    private String previousEmpState;
    
    private String workingCountry;
    
      //new
    private boolean isCreTeam;
    private String location;
    
    // For oprations team
    
    private boolean isOperationTeam;
     private boolean isPMO;
     
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
    private String uanNo;
private String pfno;

// New ReportstoFlag
 private boolean reportsToFlag;
     
 private Date lastRevisedDate;
    private Date revisedDate;
     private int utilization;
     private String stateStartDate1;
    private String stateEndDate1;
    private String projectStartDate;
    private String projectEndDate;
    private String comments;
    private boolean isInternationalWorker;
    
    private Date dateOfTermination;
     private String reasonsForTerminate;
     
     
     
      private String prvexpMnths;
     private String prvexpYears;
     private String prevExp;
     private String resumeName;
     private String resumePath;
     
     private String projectStatus;
     private int projectContactId;
     private boolean lateralFlag;

     
     
       public boolean getReportsToFlag() {
        return reportsToFlag;
    }

    public void setReportsToFlag(boolean reportsToFlag) {
        this.reportsToFlag = reportsToFlag;
    }	
    /** Creates a new instance of EmployeeVTO */
    public EmployeeVTO() {
    }
    
    /**
     * The getId() is used for id of employee.
     * @ return int variable
     */
    public int getId() {
        return id;
    }
    
    /**
     * The setId(int id) is used for id of employee.
     *
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * The getFirstName() is used for firstName of employee.
     * @ return String variable
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * The setFirstName(String firstName) is used for setting firstName of employee.
     *
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * The getLastName() is used for lastName of employee.
     * @ return String variable
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * The setLastName(String lastName) is used for setting lastName of employee.
     *
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * The getMiddleName() is used for middleName of employee.
     * @ return String variable
     */
    public String getMiddleName() {
        return middleName;
    }
    
    /**
     * The setMiddleName(String middleName) is used for setting middleName of employee.
     *
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    /**
     * The getAliasName() is used for aliasName of employee.
     * @ return String variable
     */
    public String getAliasName() {
        return aliasName;
    }
    
    /**
     * The setAliasName(String aliasName) is used for setting middleName of employee.
     *
     */
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
    
    /**
     * The getGender() is used for gender of employee.
     * @ return String variable
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * The setGender(String gender) is used for setting gender of employee.
     *
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * The getMaritalStatus() is used for maritalStatus of employee.
     * @ return String variable
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }
    
    /**
     * The setMaritalStatus(String maritalStatus) is used for setting maritalStatus of employee.
     *
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    
    /**
     * The getCountry() is used for country of employee.
     * @ return String variable
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * The setCountry(String country) is used for setting country of employee.
     *
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * The getSsn() is used for ssn of employee.
     * @ return String variable
     */
    public String getSsn() {
        return ssn;
    }
    
    /**
     * The setSsn(String ssn) is used for setting ssn of employee.
     *
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    
    /**
     * The getCurrStatus() is used for currStatus of employee.
     * @ return String variable
     */
    public String getCurrStatus() {
        return currStatus;
    }
    
    /**
     * The setCurrStatus(String currStatus) is used for setting currStatus of employee.
     *
     */
    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus;
    }
    
    /**
     * The getEmpTypeId() is used for empTypeId of employee.
     * @ return String variable
     */
    public String getEmpTypeId() {
        return empTypeId;
    }
    
    /**
     * The setEmpTypeId(String empTypeId) is used for setting empTypeId of employee.
     *
     */
    public void setEmpTypeId(String empTypeId) {
        this.empTypeId = empTypeId;
    }
    
    /**
     * The getOrgId() is used for orgId of employee.
     * @ return String variable
     */
    public String getOrgId() {
        return orgId;
    }
    
    /**
     * The setOrgId(String orgId) is used for setting orgId of employee.
     *
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    
    /**
     * The getOpsContactId() is used for opsContactId of employee.
     * @ return int variable
     */
    public String getOpsContactId() {
        return opsContactId;
    }
    
    /**
     * The setOpsContactId(int opsContactId) is used for setting opsContactId of employee.
     *
     */
    public void setOpsContactId(String opsContactId) {
        this.opsContactId = opsContactId;
    }
    
    /**
     * The getTeamId() is used for teamId of employee.
     * @ return String variable
     */
    public String getTeamId() {
        return teamId;
    }
    
    /**
     * The setTeamId(String teamId) is used for setting teamId of employee.
     *
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
    
    /**
     * The getPracticeId() is used for practiceId of employee.
     * @ return String variable
     */
    public String getPracticeId() {
        return practiceId;
    }
    
    /**
     * The setPracticeId(String practiceId) is used for setting practiceId of employee.
     *
     */
    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }
    
    /**
     * The getTitleId() is used for titleId of employee.
     * @ return String variable
     */
    public String getTitleId() {
        return titleId;
    }
    
    /**
     * The setTitleId(String titleId) is used for setting titleId of employee.
     *
     */
    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }
    
    /**
     * The getIndustryId() is used for industryId of employee.
     * @ return String variable
     */
    public String getIndustryId() {
        return industryId;
    }
    
    /**
     * The getIndustryId() is used for industryId of employee.
     * @ return String variable
     */
    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }
    
    /**
     * The getDepartmentId() is used for departmentId of employee.
     * @ return String variable
     */
    public String getDepartmentId() {
        return departmentId;
    }
    
    /**
     * The setDepartmentId(String departmentId) is used for setting departmentId of employee.
     *
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    
    /**
     * The getBirthDate() is used for birthDate of employee.
     * @ return Date variable
     */
    public Date getBirthDate() {
        return birthDate;
    }
    
    /**
     * The setBirthDate(Date birthDate) is used for setting birthDate of employee.
     *
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    /**
     * The getOffBirthDate() is used for offBirthDate of employee.
     * @ return Date variable
     */
    public Date getOffBirthDate() {
        return offBirthDate;
    }
    
    /**
     * The setOffBirthDate(Date offBirthDate) is used for setting offBirthDate of employee.
     *
     */
    public void setOffBirthDate(Date offBirthDate) {
        this.offBirthDate = offBirthDate;
    }
    
    /**
     * The getHireDate() is used for hireDate of employee.
     * @ return Date variable
     */
    public Date getHireDate() {
        return hireDate;
    }
    
    /**
     * The setHireDate(Date hireDate)  is used for setting hireDate of employee.
     *
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    
    /**
     * The getAnniversaryDate() is used for anniversaryDate of employee.
     * @ return Date variable
     */
    public Date getAnniversaryDate() {
        return anniversaryDate;
    }
    
    /**
     * The setAnniversaryDate(Date anniversaryDate)  is used for setting anniversaryDate of employee.
     *
     */
    public void setAnniversaryDate(Date anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }
    
    /**
     * The getWorkPhoneNo() is used for workPhoneNo of employee.
     * @ return String variable
     */
    public String getWorkPhoneNo() {
        return workPhoneNo;
    }
    
    /**
     * The setWorkPhoneNo(String workPhoneNo) is used for setting workPhoneNo of employee.
     *
     */
    public void setWorkPhoneNo(String workPhoneNo) {
        this.workPhoneNo = workPhoneNo;
    }
    
    /**
     * The getAltPhoneNo() is used for altPhoneNo of employee.
     * @ return String variable
     */
    public String getAltPhoneNo() {
        return altPhoneNo;
    }
    
    /**
     * The setAltPhoneNo(String altPhoneNo) is used for setting altPhoneNo of employee.
     *
     */
    public void setAltPhoneNo(String altPhoneNo) {
        this.altPhoneNo = altPhoneNo;
    }
    
    /**
     * The getHomePhoneNo() is used for homePhoneNo of employee.
     * @ return String variable
     */
    public String getHomePhoneNo() {
        return homePhoneNo;
    }
    
    /**
     * The setHomePhoneNo(String homePhoneNo) is used for setting homePhoneNo of employee.
     *
     */
    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }
    
    /**
     * The getCellPhoneNo() is used for cellPhoneNo of employee.
     * @ return String variable
     */
    public String getCellPhoneNo() {
        return cellPhoneNo;
    }
    
    /**
     * The setCellPhoneNo(String cellPhoneNo) is used for setting cellPhoneNo of employee.
     *
     */
    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }
    
    /**
     * The getOfficeEmail() is used for officeEmail of employee.
     * @ return String variable
     */
    public String getOfficeEmail() {
        return officeEmail;
    }
    
    /**
     * The setOfficeEmail(String officeEmail) is used for setting officeEmail of employee.
     *
     */
    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }
    
    /**
     * The getHotelPhoneNo() is used for hotelPhoneNo of employee.
     * @ return String variable
     */
    public String getHotelPhoneNo() {
        return hotelPhoneNo;
    }
    
    /**
     * The setHotelPhoneNo(String hotelPhoneNo) is used for setting hotelPhoneNo of employee.
     *
     */
    public void setHotelPhoneNo(String hotelPhoneNo) {
        this.hotelPhoneNo = hotelPhoneNo;
    }
    
    /**
     * The getPersonalEmail() is used for personalEmail of employee.
     * @ return String variable
     */
    public String getPersonalEmail() {
        return personalEmail;
    }
    
    /**
     * The setPersonalEmail(String personalEmail) is used for setting personalEmail of employee.
     *
     */
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }
    
    /**
     * The getIndiaPhoneNo() is used for indiaPhoneNo of employee.
     * @ return String variable
     */
    public String getIndiaPhoneNo() {
        return indiaPhoneNo;
    }
    
    /**
     * The setIndiaPhoneNo(String indiaPhoneNo) is used for setting indiaPhoneNo of employee.
     *
     */
    public void setIndiaPhoneNo(String indiaPhoneNo) {
        this.indiaPhoneNo = indiaPhoneNo;
    }
    
    /**
     * The getOtherEmail() is used for otherEmail of employee.
     * @ return String variable
     */
    public String getOtherEmail() {
        return otherEmail;
    }
    
    /**
     * The setOtherEmail(String otherEmail) is used for setting otherEmail of employee.
     *
     */
    public void setOtherEmail(String otherEmail) {
        this.otherEmail = otherEmail;
    }
    
    /**
     * The getFaxNo() is used for otherEmail of employee.
     * @ return String variable
     */
    public String getFaxNo() {
        return faxNo;
    }
    
    /**
     * The setFaxNo(String faxNo) is used for setting faxNo of employee.
     *
     */
    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }
    
    /**
     * The getLastContactBy() is used for lastContactBy of employee.
     * @ return int variable
     */
    public String getLastContactBy() {
        return lastContactBy;
    }
    
    /**
     * The setLastContactBy(int lastContactBy) is used for lastContactBy of employee.
     *
     */
    public void setLastContactBy(String lastContactBy) {
        this.lastContactBy = lastContactBy;
    }
    
    /**
     * The getModifiedBy() is used for modifiedBy of employee.
     * @ return String variable
     */
    public String getModifiedBy() {
        return modifiedBy;
    }
    
    /**
     * The setModifiedBy(String modifiedBy) is used for modifiedBy of employee.
     *
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    /**
     * The getModifiedDate() is used for modifiedDate of employee.
     * @ return Timestamp variable
     */
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }
    
    /**
     * The setModifiedDate(Timestamp modifiedDate) is used for modifiedDate of employee.
     *
     */
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    /**
     * The getCreatedBy() is used for createdBy of employee.
     * @ return String variable
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * The setCreatedBy(String createdBy) is used for createdBy of employee.
     *
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    /**
     * The getCreatedDate() is used for createdDate of employee.
     * @ return Timestamp variable
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    
    /**
     * The setCreatedDate(Timestamp createdDate) is used for createdDate of employee.
     *
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    
    /**
     * The getLoginId() is used for storing loginId of employee.
     * @return String variable
     */
    public String getLoginId() {
        return loginId;
    }
    
    /**
     * The setLoginId(String loginId) is used for setting loginId of employee.
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
    /**
     * The getPassword() is used for storing loginId of employee.
     * @return String variable
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * The setPassword(String password) is used for setting password of employee.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean getIsManager() {
        return isManager;
    }
    
    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }
    
    public String getReportsTo() {
        return reportsTo;
    }
    
    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }
    
    public String getEmpState() {
        return empState;
    }
    
    public void setEmpState(String empState) {
        this.empState = empState;
    }
    
    public Date getStateStartDate() {
        return stateStartDate;
    }
    
    public void setStateStartDate(Date stateStartDate) {
        this.stateStartDate = stateStartDate;
    }
    
    public Date getStateEndDate() {
        return stateEndDate;
    }
    
    public void setStateEndDate(Date stateEndDate) {
        this.stateEndDate = stateEndDate;
    }
    
//    public float getIntRatePerHour() {
//        return intRatePerHour;
//    }
//    
//    public void setIntRatePerHour(float intRatePerHour) {
//        this.intRatePerHour = intRatePerHour;
//    }
//    
//    public float getInvRatePerHour() {
//        return invRatePerHour;
//    }
//    
//    public void setInvRatePerHour(float invRatePerHour) {
//        this.invRatePerHour = invRatePerHour;
//    }
    
    public String getSkillSet() {
        return skillSet;
    }
    
    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }
    
    public String getPreviousEmpState() {
        return previousEmpState;
    }
    
    public void setPreviousEmpState(String previousEmpState) {
        this.previousEmpState = previousEmpState;
    }
    
    public String getSubPractice() {
        return subPractice;
    }
    
    public void setSubPractice(String subPractice) {
        this.subPractice = subPractice;
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
    
    public boolean isIsTeamLead() {
        return isTeamLead;
    }
    
    public void setIsTeamLead(boolean isTeamLead) {
        this.isTeamLead = isTeamLead;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

//    public double getCtcPerYear() {
//        return ctcPerYear;
//    }
//
//    public void setCtcPerYear(double ctcPerYear) {
//        this.ctcPerYear = ctcPerYear;
//    }

    public String getItgBatch() {
        return itgBatch;
    }

    public void setItgBatch(String itgBatch) {
        this.itgBatch = itgBatch;
    }

    public String getWorkingCountry() {
        return workingCountry;
    }

    public void setWorkingCountry(String workingCountry) {
        this.workingCountry = workingCountry;
    }

    public String getIsOnsite() {
        return isOnsite;
    }

    public void setIsOnsite(String isOnsite) {
        this.isOnsite = isOnsite;
    }

    public boolean isIsCreTeam() {
        return isCreTeam;
    }

    public void setIsCreTeam(boolean isCreTeam) {
        this.isCreTeam = isCreTeam;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the isOperationTeam
     */
    public boolean getIsOperationTeam() {
        return isOperationTeam;
    }

    /**
     * @param isOperationTeam the isOperationTeam to set
     */
    public void setIsOperationTeam(boolean isOperationTeam) {
        this.isOperationTeam = isOperationTeam;
    }

    public boolean getIsPMO() {
        return isPMO;
    }

    public void setIsPMO(boolean isPMO) {
        this.isPMO = isPMO;
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

    /**
     * @return the lastRevisedDate
     */
    public Date getLastRevisedDate() {
        return lastRevisedDate;
    }

    /**
     * @param lastRevisedDate the lastRevisedDate to set
     */
    public void setLastRevisedDate(Date lastRevisedDate) {
        this.lastRevisedDate = lastRevisedDate;
    }

    /**
     * @return the revisedDate
     */
    public Date getRevisedDate() {
        return revisedDate;
    }

    /**
     * @param revisedDate the revisedDate to set
     */
    public void setRevisedDate(Date revisedDate) {
        this.revisedDate = revisedDate;
    }

    /**
     * @return the utilization
     */
    public int getUtilization() {
        return utilization;
    }

    /**
     * @param utilization the utilization to set
     */
    public void setUtilization(int utilization) {
        this.utilization = utilization;
    }

    /**
     * @return the stateStartDate1
     */
    public String getStateStartDate1() {
        return stateStartDate1;
    }

    /**
     * @param stateStartDate1 the stateStartDate1 to set
     */
    public void setStateStartDate1(String stateStartDate1) {
        this.stateStartDate1 = stateStartDate1;
    }

    /**
     * @return the stateEndDate1
     */
    public String getStateEndDate1() {
        return stateEndDate1;
    }

    /**
     * @param stateEndDate1 the stateEndDate1 to set
     */
    public void setStateEndDate1(String stateEndDate1) {
        this.stateEndDate1 = stateEndDate1;
    }

    /**
     * @return the projectStartDate
     */
    public String getProjectStartDate() {
        return projectStartDate;
    }

    /**
     * @param projectStartDate the projectStartDate to set
     */
    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    /**
     * @return the projectEndDate
     */
    public String getProjectEndDate() {
        return projectEndDate;
    }

    /**
     * @param projectEndDate the projectEndDate to set
     */
    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the isInternationalWorker
     */
    public boolean getIsInternationalWorker() {
        return isInternationalWorker;
    }

    /**
     * @param isInternationalWorker the isInternationalWorker to set
     */
    public void setIsInternationalWorker(boolean isInternationalWorker) {
        this.isInternationalWorker = isInternationalWorker;
    }

    /**
     * @return the dateOfTermination
     */
    public Date getDateOfTermination() {
        return dateOfTermination;
    }

    /**
     * @param dateOfTermination the dateOfTermination to set
     */
    public void setDateOfTermination(Date dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

    /**
     * @return the reasonsForTerminate
     */
    public String getReasonsForTerminate() {
        return reasonsForTerminate;
    }

    /**
     * @param reasonsForTerminate the reasonsForTerminate to set
     */
    public void setReasonsForTerminate(String reasonsForTerminate) {
        this.reasonsForTerminate = reasonsForTerminate;
    }

    /**
     * @return the prvexpMnths
     */
    public String getPrvexpMnths() {
        return prvexpMnths;
    }

    /**
     * @param prvexpMnths the prvexpMnths to set
     */
    public void setPrvexpMnths(String prvexpMnths) {
        this.prvexpMnths = prvexpMnths;
    }

    /**
     * @return the prvexpYears
     */
    public String getPrvexpYears() {
        return prvexpYears;
    }

    /**
     * @param prvexpYears the prvexpYears to set
     */
    public void setPrvexpYears(String prvexpYears) {
        this.prvexpYears = prvexpYears;
    }

    /**
     * @return the prevExp
     */
    public String getPrevExp() {
        return prevExp;
    }

    /**
     * @param prevExp the prevExp to set
     */
    public void setPrevExp(String prevExp) {
        this.prevExp = prevExp;
    }

    /**
     * @return the resumeName
     */
    public String getResumeName() {
        return resumeName;
    }

    /**
     * @param resumeName the resumeName to set
     */
    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    /**
     * @return the resumePath
     */
    public String getResumePath() {
        return resumePath;
    }

    /**
     * @param resumePath the resumePath to set
     */
    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    /**
     * @return the projectStatus
     */
    public String getProjectStatus() {
        return projectStatus;
    }

    /**
     * @param projectStatus the projectStatus to set
     */
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    /**
     * @return the projectContactId
     */
    public int getProjectContactId() {
        return projectContactId;
    }

    /**
     * @param projectContactId the projectContactId to set
     */
    public void setProjectContactId(int projectContactId) {
        this.projectContactId = projectContactId;
    }

    /**
     * @return the lateralFlag
     */
    public boolean isLateralFlag() {
        return lateralFlag;
    }

    /**
     * @param lateralFlag the lateralFlag to set
     */
    public void setLateralFlag(boolean lateralFlag) {
        this.lateralFlag = lateralFlag;
    }

  
    
}
