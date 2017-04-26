 /*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  October 9, 2007, 3:53 PM
 *
 * Author  : Jyothi Nimmagadda <jnimmagadda@miraclesoft.com>
 *
 * File    : ImmigrationVTO.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */


package com.mss.mirage.employee.immigration;

import java.sql.Date;
import java.sql.Timestamp;


/**
 * The <code>ImmigrationServiceImpl</code>  class is used for getting new Note details and  overridding the methods from the
 * <i>EmployeeTravelService.class</i>
 * <p>
 * Then it overrides  methods in <code>ImmigrationService</code> class and performs doImmigrationEdit() method and doImmigrationAdd()
 * in <code>immigrationAction</code> for inserting employee details in  TBLEMPIMMIGRATION  table.
 *
 * @author Jyothi Nimmagadda <a href="mailto:jnimmagadda@miraclesoft.com">jnimmagadda@miraclesoft.com</a>
 *
 * @version 1.0 November 01, 2007
 *
 * @see com.mss.mirage.immigration.ImmigrationAction
 * @see com.mss.mirage.immigration.ImmigrationService
 * @see com.mss.mirage.immigration.ImmigrationServiceImpl
 * @see com.mss.mirage.immigration.ImmigrationVTO
 */
public class ImmigrationVTO {
    
  /** The empId is used for storing the primary value of the table. */
    private int empId;
    
  /** The currentImmigStatus is used for storing the Current Immigration Status Type value in the table. */
  private String  currentImmigStatus;
  
  /** The passportCountry is used for storing the passportCountry value in the table. */
    private String passportCountry;
  
   /** The passportNo is used for storing the Passport Number value in the table. */
  private String passportNo;
  
   /** The passportIssuedAt is used for storing the Passport Issued At value in the table. */
  private String passportIssuedAt;
  
     /** The passportissue is used for storing the Passport Issuel Date . */
  private Date passportIssue;
  
    /** The passportExp is used for storing the Passport Expiry Date . */
  private Date passportExp;
   
	
   /** The dateOfArrival is used for storing the Arrival Date . */
  private Date dateOfArrival;
  
   /** The portOfEntry  is used for storing the Port Of Entry  value in the table. */
  private String portOfEntry;
  
   /** The i94No is used for storing the I94Number value in the table. */
  private String i94No;
  
   /** The ptUniversity is used for storing the PTUniversity value in the table. */
  private String ptUniversity;
  
   /** The ptStart is used for storing the Entry Start Date . */
  private Date ptStart;
  
   /** The ptEnd is used for storing the Entry End  Date . */
  private Date ptEnd; 
 
	
   /** The h1Field is used for storing the H1Field Date . */
  private Date h1Filed;
  
   /** The h1LinReceive is used for storing the H1LinReceive Date . */
  private Date h1LinReceive;
  
  /** The h1Lin is used for storing the H1Lin value in the table. */
  private String h1Lin;
  
  /** The h1LcaEtaNo is used for storing the H1LcaEtaNo value in the table. */
  private String h1LcaEtaNo;
  
  /** The h1Title is used for storing the H1Title value in the table. */
  private String h1Title;
  
  /** The h1Start is used for storing the H1Start  Date . */
  private Date h1Start; 
  
  /** The h1Termination is used for storing the H1Termination  Date . */
  private Date h1Termination;
  
  /** The h1End is used for storing the H1End  Date . */
  private Date h1End;
  
   /** The h1Status is used for storing the H1Status value in the table. */
  private String h1Status;
  /** The queryReceived  is used for storing the Query Received  Date . */
  private Date queryReceived; 
  
  /** The queryDue is used for storing the QueryDue Date . */
  private Date queryDue;  
  
  /** The queryResponded is used for storing the Query Responded  Date . */
  private Date queryResponded;      
  
   /** The comments is used for storing the Query Comments value in the table. */
  private String queryComments;
  
    
    /** The b1Field is used for storing the B1Field  Date . */
  private Date b1Filed;
  
   /** The b1Interview  is used for storing the B1Interview Date . */
  private Date b1Interview; 
  
   /** The b1Start is used for storing the B1Start  Date . */
  private Date b1Start; 
  
   /** The b1End is used for storing the B1End  Date . */
  private Date b1End; 
  
   /** The b1Status is used for storing the B1Status value in the table. */
  private String b1Status;
    
   /** The l1Field  is used for storing the L1Field  Date . */
  private Date l1Filed;
  
   /** The l1Interview is used for storing the l1Interview  Date . */
  private Date l1Interview; 
  
   /** The l1Start is used for storing the l1Start  Date . */
  private Date l1Start; 
  
   /** The l1End is used for storing the L1End  Date . */
  private Date l1End; 
  
   /** The l1Status is used for storing the L1Status value in the table. */
  private String l1Status;
    
    /** The gcApplied  is used for storing the GreenCard Applied  Date . */
  private Date gcApplied; 
  
  /** The gcApproved  is used for storing the GreenCard Approved   Date . */
  private Date gcApproved; 
  
   /** The gcRef  is used for storing the  GreenCard Reference value in the table. */
  private String gcRef; 
  
   /** The gcTitle  is used for storing the GreenCard Title value in the table. */
  private String gcTitle;     
  
   /** The gcStatus is used for storing the GreenCard Status value in the table. */
  private String gcStatus; 
  
   /** The otherVisas is used for storing the OtherVISAS value in the table. */
  private String otherVisas; 
  
   /** The comments  is used for storing the Comments value in the table. */
  private String comments; 
    
    /** The modifiedBy  is used for storing the ModifiedBy value in the table. */
  private String modifiedBy;
  
  /** The modifiedDate is used for storing the Modified Date  in the database table. */
  private Timestamp modifiedDate;
    
  
  private String actionType;
  
    
    /** Creates a new instance of ImmigrationVTO */
    public ImmigrationVTO() {
     
    }

    /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the empId at the specified URL
 * @see         empId
 */
    public int getEmpId() {
        return empId;
    }

    
      /** Handles the  <code>setEmpId</code> method.
     * @param request setEmpId
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    
  /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the currentImmigStatus at the specified URL
 * @see         currentImmigStatus
 */
    public String getCurrentImmigStatus() {
        return currentImmigStatus;
    }

    
      /** Handles the  <code>setCurrentImmigStatus</code> method.
     * @param request currentImmigStatus
     */
    public void setCurrentImmigStatus(String currentImmigStatus) {
        this.currentImmigStatus = currentImmigStatus;
    }

    
  /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the passportNo at the specified URL
 * @see         passportNo
 */
    public String getPassportNo() {
        return passportNo;
    }

    
     /** Handles the  <code>setPassportNo</code> method.
     * @param request passportNo
     */
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    
 /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the passportIssuedAt at the specified URL
 * @see         passportIssuedAt
 */
    public String getPassportIssuedAt() {
        return passportIssuedAt;
    }

    
      /** Handles the  <code>setPassportIssuedAt</code> method.
     * @param request passportIssuedAt
     */
    public void setPassportIssuedAt(String passportIssuedAt) {
        this.passportIssuedAt = passportIssuedAt;
    }

    
 /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the passportIssueDate at the specified URL
 * @see         passportIssueDate
 */
    public Date getPassportIssue() {
        return passportIssue;
    }

    
     /** Handles the  <code>setPassportIssue</code> method.
     * @param request passportIssueDate
     */
    public void setPassportIssue(Date passportIssue) {
        this.passportIssue = passportIssue;
    }

    
 /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the passportExpDate at the specified URL
 * @see         passportExpDate
 */
    public Date getPassportExp() {
        return passportExp;
    }

    
    /** Handles the  <code>setPassportExp</code> method.
     * @param request passportExpDate
     */ 
    public void setPassportExp(Date passportExp) {
        this.passportExp = passportExp;
    }

    
 /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the dateOfArrival at the specified URL
 * @see          dateOfArrival
 */
    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    
    /** Handles the  <code>setDateOfArrival</code> method.
     * @param request dateOfArrival
     */ 
    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    
 /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the portOfEntry at the specified URL
 * @see          portOfEntry
 */
    public String getPortOfEntry() {
        return portOfEntry;
    }

    /** Handles the  <code>setPortOfEntry</code> method.
     * @param request portOfEntry
     */ 
    public void setPortOfEntry(String portOfEntry) {
        this.portOfEntry = portOfEntry;
    }

    
  /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the i94No at the specified URL
 * @see          i94No
 */
    public String getI94No() {
        return i94No;
    }

    
    /** Handles the  <code>setI94No</code> method.
     * @param request i94No
     */ 
    public void setI94No(String i94No) {
        this.i94No = i94No;
    }

    
   /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the ptUniversity at the specified URL
 * @see          ptUniversity
 */ 
    public String getPtUniversity() {
        return ptUniversity;
    }

    
      /** Handles the  <code>setPtUniversity</code> method.
     * @param request ptUniversity
     */ 
    public void setPtUniversity(String ptUniversity) {
        this.ptUniversity = ptUniversity;
    }

    
 /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the ptStartDate at the specified URL
 * @see          ptStartDate
 */ 
    public Date getPtStart() {
        return ptStart;
    }

    
     /** Handles the  <code>setPtStartDate</code> method.
     * @param request ptStartDate
     */ 
    public void setPtStart(Date ptStart) {
        this.ptStart = ptStart;
    }

    
 /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the ptEndDate at the specified URL
 * @see          ptEndDate
 */ 
    public Date getPtEnd() {
        return ptEnd;
    }

    
      /** Handles the  <code>setPtEndDate</code> method.
     * @param request ptEnd
     */ 
    public void setPtEnd(Date ptEnd) {
        this.ptEnd = ptEnd;
    }

    
    /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the h1FiledDate at the specified URL
 * @see          h1FiledDate
 */ 
    public Date getH1Filed() {
        return h1Filed;
    }

    
      /** Handles the  <code>setH1FiledDate</code> method.
     * @param request h1FiledDate
     */ 
    public void setH1Filed(Date h1Filed) {
        this.h1Filed = h1Filed;
    }

    
 /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the h1LinReceive at the specified URL
 * @see          h1LinReceive
 */ 
    public Date getH1LinReceive() {
        return h1LinReceive;
    }

    
     /** Handles the  <code>setH1LinReceive</code> method.
     * @param request h1LinReceive
     */ 
    public void setH1LinReceive(Date h1LinReceive) {
        this.h1LinReceive = h1LinReceive;
    }

    
 /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the h1Lin at the specified URL
 * @see          h1Lin
 */  
    public String getH1Lin() {
        return h1Lin;
    }

    
     /** Handles the  <code>setH1Lin</code> method.
     * @param request h1Lin
     */ 
    public void setH1Lin(String h1Lin) {
        this.h1Lin = h1Lin;
    }

    
   /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the h1LcaEtaNo at the specified URL
 * @see          h1LcaEtaNo
 */    
    public String getH1LcaEtaNo() {
        return h1LcaEtaNo;
    }

    
       /** Handles the  <code>setH1LcaEtaNo</code> method.
     * @param request h1LcaEtaNo
     */ 
    public void setH1LcaEtaNo(String h1LcaEtaNo) {
        this.h1LcaEtaNo = h1LcaEtaNo;
    }

  /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the h1Title at the specified URL
 * @see         h1Title
 */      
  public String getH1Title() {
      return h1Title;
    }

    /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the h1Lin at the specified URL
 * @see          h1Lin
 */  
  public void setH1Title(String h1Title) {
        this.h1Title = h1Title;
    }

  
   /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the h1StartDate at the specified URL
 * @see          h1StartDate
 */ 
    public Date getH1Start() {
        return h1Start;
    }

      /** Handles the  <code>setH1StartDate</code> method.
     * @param request h1StartDate
     */ 
    public void setH1Start(Date h1Start) {
        this.h1Start = h1Start;
    }

    
      /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the h1TerminationDate at the specified URL
 * @see          h1TerminationDate
 */ 
    public Date getH1Termination() {
        return h1Termination;
    }

    
     /** Handles the  <code>setH1TerminationDate</code> method.
     * @param request h1Termination
     */ 
    public void setH1Termination(Date h1Termination) {
        this.h1Termination = h1Termination;
    }

    
 /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the h1EndDate at the specified URL
 * @see          h1EndDate
 */ 
    public Date getH1End() {
        return h1End;
    }

    
   /** Handles the  <code>setH1EndDate</code> method.
     * @param request h1EndDate
     */ 
    public void setH1End(Date h1End) {
        this.h1End = h1End;
    }

    
  /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the h1Status at the specified URL
 * @see          h1Status
 */  
    public String getH1Status() {
        return h1Status;
    }

     /** Handles the  <code>setH1Status</code> method.
     * @param request h1Status
     */ 
    public void setH1Status(String h1Status) {
        this.h1Status = h1Status;
    }

    
  /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the queryReceivedDate at the specified URL
 * @see          queryReceivedDate
 */  
    public Date getQueryReceived() {
        return queryReceived;
    }

    
    /** Handles the  <code>setQueryReceivedDate</code> method.
     * @param request queryReceivedDate
     */ 
    public void setQueryReceived(Date queryReceived) {
        this.queryReceived = queryReceived;
    }

    
   /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the queryDueDate at the specified URL
 * @see          queryDueDate
 */    
    public Date getQueryDue() {
        return queryDue;
    }

    
     /** Handles the  <code>setQueryDueDate</code> method.
     * @param request queryDueDate
     */ 
    public void setQueryDue(Date queryDue) {
        this.queryDue = queryDue;
    }

    
  /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the queryRespondedDate at the specified URL
 * @see          queryRespondedDate
 */    
    public Date getQueryResponded() {
        return queryResponded;
    }

    
     /** Handles the  <code>setQueryRespondedDate</code> method.
     * @param request queryRespondedDate
     */ 
    public void setQueryResponded(Date queryResponded) {
        this.queryResponded = queryResponded;
    }

    
    /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the queryComments at the specified URL
 * @see          queryComments
 */     
    public String getQueryComments() {
        return queryComments;
    }

    
     /** Handles the  <code>setQueryComments</code> method.
     * @param request queryComments
     */ 
    public void setQueryComments(String queryComments) {
        this.queryComments = queryComments;
    }

    
    /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the b1FiledDate at the specified URL
 * @see          b1FiledDate
 */     
    public Date getB1Filed() {
        return b1Filed;
    }

    
     /** Handles the  <code>setB1FiledDate</code> method.
     * @param request b1FiledDate
     */ 
    public void setB1Filed(Date b1Filed) {
        this.b1Filed = b1Filed;
    }

    
    /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the b1InterviewDate at the specified URL
 * @see          b1InterviewDate
 */     
    public Date getB1Interview() {
        return b1Interview;
    }

    
     /** Handles the  <code>setB1InterviewDate</code> method.
     * @param request b1InterviewDate
     */ 
    public void setB1Interview(Date b1Interview) {
        this.b1Interview = b1Interview;
    }

    /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the b1StartDate at the specified URL
 * @see          b1StartDate
 */     
    public Date getB1Start() {
        return b1Start;
    }

    
     /** Handles the  <code>setB1StartDate</code> method.
     * @param request b1StartDate
     */ 
    public void setB1Start(Date b1Start) {
        this.b1Start = b1Start;
    }

    /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the b1EndDate at the specified URL
 * @see          b1EndDate
 */     
    public Date getB1End() {
        return b1End;
    }

    
     /** Handles the  <code>setB1EndDate</code> method.
     * @param request b1EndDate
     */ 
    public void setB1End(Date b1End) {
        this.b1End = b1End;
    }

  /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the b1Status at the specified URL
 * @see          b1Status
 */     
    public String getB1Status() {
        return b1Status;
    }

    
     /** Handles the  <code>setB1Status</code> method.
     * @param request b1Status
     */ 
    public void setB1Status(String b1Status) {
        this.b1Status = b1Status;
    }

    
 /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the  l1FiledDate at the specified URL
 * @see           l1FiledDate
 */     
    public Date getL1Filed() {
        return l1Filed;
    }

    /** Handles the  <code>set l1FiledDate</code> method.
     * @param request  l1FiledDate
     */ 
    public void setL1Filed(Date l1Filed) {
        this.l1Filed = l1Filed;
    }

    
   /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the l1InterviewDate at the specified URL
 * @see           l1InterviewDate
 */     
    public Date getL1Interview() {
        return l1Interview;
    }

    /** Handles the  <code>setl1InterviewDate</code> method.
     * @param request l1InterviewDate
     */ 
    public void setL1Interview(Date l1Interview) {
        this.l1Interview = l1Interview;
    }

    
   /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the l1StartDate at the specified URL
 * @see           l1StartDate
 */     
    public Date getL1Start() {
        return l1Start;
    }

    /** Handles the  <code>setL1StartDate</code> method.
     * @param request l1StartDate
     */ 
    public void setL1Start(Date l1Start) {
        this.l1Start = l1Start;
    }

    
    /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the l1EndDate at the specified URL
 * @see          l1EndDate
 */     
    public Date getL1End() {
        return l1End;
    }

    
    /** Handles the  <code>setL1EndDate</code> method.
     * @param request l1EndDate
     */ 
    public void setL1End(Date l1End) {
        this.l1End = l1End;
    }

  /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the l1Status at the specified URL
 * @see          l1Status
 */     
    public String getL1Status() {
        return l1Status;
    }

    
   /** Handles the  <code>setL1Status</code> method.
     * @param request l1Status
     */ 
    public void setL1Status(String l1Status) {
        this.l1Status = l1Status;
    }

    
      /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the gcApplied at the specified URL
 * @see         gcApplied
 */     
    public Date getGcApplied() {
        return gcApplied;
    }

       /** Handles the  <code>setGcAppliedDate</code> method.
     * @param request gcAppliedDate
     */ 
    public void setGcApplied(Date gcApplied) {
        this.gcApplied = gcApplied;
    }

   
      /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the gcApproved at the specified URL
 * @see          gcApproved
 */     
    public Date getGcApproved() {
        return gcApproved;
    }

       /** Handles the  <code>setGcApprovedDate</code> method.
     * @param request gcApprovedDate
     */ 
    public void setGcApproved(Date gcApproved) {
        this.gcApproved = gcApproved;
    }

    
      /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the gcRef at the specified URL
 * @see          gcRef
 */     
    public String getGcRef() {
        return gcRef;
    }

    
       /** Handles the  <code>setGcRef</code> method.
     * @param request gcRef
     */ 
    public void setGcRef(String gcRef) {
        this.gcRef = gcRef;
    }

    
      /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the gcTitle at the specified URL
 * @see          gcTitle
 */     
    public String getGcTitle() {
        return gcTitle;
    }

    
       /** Handles the  <code>setGcTitle</code> method.
     * @param request gcTitle
     */ 
    public void setGcTitle(String gcTitle) {
        this.gcTitle = gcTitle;
    }

    
      /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the gcStatus at the specified URL
 * @see          gcStatus
 */     
    public String getGcStatus() {
        return gcStatus;
    }

       /** Handles the  <code>setGcStatus</code> method.
     * @param request gcStatus
     */ 
    public void setGcStatus(String gcStatus) {
        this.gcStatus = gcStatus;
    }

    
      /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the otherVisas at the specified URL
 * @see          otherVisas
 */     
    public String getOtherVisas() {
        return otherVisas;
    }

       /** Handles the  <code>setOtherVisas</code> method.
     * @param request otherVisas
     */ 
    public void setOtherVisas(String otherVisas) {
        this.otherVisas = otherVisas;
    }

    
      /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the comments at the specified URL
 * @see          comments
 */     
    public String getComments() {
        return comments;
    }

       /** Handles the  <code>setComments</code> method.
     * @param request comments
     */ 
    public void setComments(String comments) {
        this.comments = comments;
    }

    
  /* * Returns an String object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the modifiedBy at the specified URL
 * @see         modifiedBy
 */     
    public String getModifiedBy() {
        return modifiedBy;
    }

       /** Handles the  <code>setModifiedBy</code> method.
     * @param request modifiedBy
     */ 
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    
    /* * Returns an Timestamp object that can then be used for the retreiving the data.
 * <p>
 * This method always returns immediately, whether or not the
 * timestamp exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the modifiedDate at the specified URL
 * @see         modifiedDate
 */
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    
     /** Handles the  <code>setModifiedDate</code> method.
     * @param request modifiedDate
     */
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    } 

   
 /* * Returns an String object that can then be used for the retreiving the data.
 *
 * <p>
 * This method always returns immediately, whether or not the
 * string exists. When this "edit button " is clicked to edit the page ,
 * the data will be loaded.
 * @return      the actionType at the specified URL
 * @see         actionType
 */
    public String getActionType() {
        return actionType;
    }

    
     /** Handles the  <code>setActionType</code> method.
     * @param request actionType
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getPassportCountry() {
        return passportCountry;
    }

    public void setPassportCountry(String passportCountry) {
        this.passportCountry = passportCountry;
    }
    
    
 }

 

   
  


    