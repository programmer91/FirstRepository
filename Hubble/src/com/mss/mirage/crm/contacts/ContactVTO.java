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
 * File    : ContactVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.mirage.crm.contacts;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Jyothi Nimmagadda<jnimmagadda@miraclesoft.com>
 *
 * 
 */
public class ContactVTO {
    
    /** Creates a new instance of ContactVTO */
    public ContactVTO() {
    }
    
	/** The operationType is used for storing the operation type. */
    private String operationType;

	/** The addressType is used for storing the address type. */
    private String addressType;
    

	/** The id is used for storing the id of Particular Contact. */
    private int id;

	/** The accountId is used for storing the id of  Particular Account. */
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
    private int primaryAddressId;

	/** The secondaryAddressId is used for storing the  secondary address id of particular Contact. */
    private int secondaryAddressId;
    
   private boolean dontSend;
    


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

    public boolean isDontSend() {
        return dontSend;
    }

    public void setDontSend(boolean dontSend) {
        this.dontSend = dontSend;
    }
    
}
