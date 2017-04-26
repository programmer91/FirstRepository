/*******************************************************************************

 *

 * Project : Mirage V2

 *

 * Package :

 *

 * Date    :  November 29, 2007, 10:57 AM

 *

 * Author  : Suresh Nalla<snalla@miraclesoft.com>

 *

 * File    : GreenSheetVTO.java

 *

 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.

 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.

 * *****************************************************************************

 */



package com.mss.mirage.crm.greensheets;



import java.util.Date;



/**

 * The <code>GreenSheetVTO</code>  class is used for getting new Employee details from

 * <i>GreenSheetAdd.jsp</i> page.

 * <p>

 * Then it invokes setter methods in <code>AddEmployee</code> class and invokes doEDIT() method

 * in <code>AddGreenSheet</code> for inserting employee details in TBLGREENSHEETS table.

 *

 * @author Suresh Nalla <a href="mailto:snalla@miraclesoft.com">snalla@miraclesoft.com</a>

 *

 * @version 1.0 November 29, 2007

 *

 * @see com.mss.mirage.crm.greensheets.GreenSheetVTO

 */



/**

 *

 * @author  Suresh Nalla<snalla@miraclesoft.com>

 *

 * This Class.... ENTER THE DESCRIPTION HERE

 */

public class GreenSheetVTO {

    

    /** Creates a new instance of GreenSheetVTO */

    public GreenSheetVTO() {
    }

    

    

    

    /** The id is used for storing the id of the tblgreenSheets. */

    private int id ;

    

    /** The empId is used for storing the empid of the tblgreenSheets. */

    private int empId;

    

    /** The consultantType is used for storing the consultanttype of the tblgreenSheets. */

    private int consultantType;

    

    /** The consultantId is used for storing the consultantid of the tblgreenSheets. */

    private int consultantId;

    

    /** The userAccountID is used for storing the useraccountID of the tblgreenSheets. */

    private int userAccountID;

    

    /** The firstName is used for storing the firstname of the tblgreenSheets. */

    private String firstName;

    

    /** The lastName is used for storing the lastname of the tblgreenSheets. */

    private String lastName;

    

    /** The middleName is used for storing the middlename of the tblgreenSheets. */

    private String middleName;

    

    /** The phone is used for storing the phone of the tblgreenSheets. */

    private String phone;

    

    /** The duration is used for storing the duration of the tblgreenSheets. */

    private double duration;

    

    /** The reportingAddress is used for storing the reportingaddress of the tblgreenSheets. */

    private String reportingAddress;

    

    /** The reportingManagerName is used for storing the reportingmanagername of the tblgreenSheets. */

    private String reportingManagerName;

    

    /** The reportingMGRPhone is used for storing the reportingMGRphone of the tblgreenSheets. */

    private String reportingMGRPhone;

    

    /** The reportingManageremail is used for storing the reportingmanageremail of the tblgreenSheets. */

    private String reportingManageremail;

    

    /** The billingAddress is used for storing the billingaddress of the tblgreenSheets. */

    private String billingAddress;

    

    /** The billingManagerName is used for storing the billingmanagername of the tblgreenSheets. */

    private String billingManagerName;

    

    /** The billingManagerPhone is used for storing the billingmanagerphone of the tblgreenSheets. */

    private String billingManagerPhone;

    

    /** The billingManageremail is used for storing the billingmanageremail of the tblgreenSheets. */

    private String billingManageremail;

    

    /** The dateStart is used for storing the datestart of the tblgreenSheets. */

    private Date dateStart;

    

    /** The dateEnd is used for storing the dateend of the tblgreenSheets. */

    private Date dateEnd;

    

    /** The dateCreated is used for storing the datecreated of the tblgreenSheets. */

    private Date dateCreated;

    

    /** The units is used for storing the units of the tblgreenSheets. */

    //private int units;

    private String units;

    

    /** The clientBillingRate is used for storing the clientbillingrate of the tblgreenSheets. */

    private double clientBillingRate;

    

    /** The expenses is used for storing the expenses of the tblgreenSheets. */

    private String expenses;

    

    /** The expenseDetail is used for storing the expensedetail of the tblgreenSheets. */

    private String  expenseDetail;

    

    /** The equipmentNeeded is used for storing the equipmentneeded of the tblgreenSheets. */

    private String equipmentNeeded;

    

    /** The otallowed is used for storing the otallowed of the tblgreenSheets. */

    private String otallowed;

    

    /** The priSalesMgrId is used for storing the priSalesmgrid of the tblgreenSheets. */

    private int priSalesMgrId;

    

    /** The priSalesPersonId is used for storing the prisalespersonid of the tblgreenSheets. */

    private int priSalesPersonId;

    

    /** The priBDMId is used for storing the priBDMid of the tblgreenSheets. */

    private int priBDMId;

    

    /** The secSalesPersonId is used for storing the secsalespersonid of the tblgreenSheets. */

    private int secSalesPersonId;

    

    /** The priSalesMgrCommission is used for storing the priSalesMgrcommission of the tblgreenSheets. */

    private double priSalesMgrCommission;

    

    /** The priSalesPersonCommission is used for storing the prisalespersoncommission of the tblgreenSheets. */

    private double priSalesPersonCommission;

    

    /** The secSalesPersonCommission is used for storing the secsalespersoncommission of the tblgreenSheets. */

    private double secSalesPersonCommission;

    

    /** The priBDMCommission is used for storing the priBDMcommission of the tblgreenSheets. */

    private double priBDMCommission;

    

    /** The poStatus is used for storing the postatus of the tblgreenSheets. */

    private String poStatus;

    

    /** The vpApprovalStatus is used for storing the vpapprovalstatus of the tblgreenSheets. */

    private String vpApprovalStatus;

    

    /** The status is used for storing the status of the tblgreenSheets. */

    private String status;

    

    /** The agencyName is used for storing the agencyname of the tblgreenSheets. */

    private String agencyName;

    

    /** The vendorContact is used for storing the vendorcontact of the tblgreenSheets. */

    private String vendorContact;

    

    /** The vendorContactPhone is used for storing the vendorcontactphone of the tblgreenSheets. */

    private String vendorContactPhone;

    

    /** The vendorContactEmail is used for storing the vendorcontactemail of the tblgreenSheets. */

    private String vendorContactEmail;

    

    /** The venUnits is used for storing the venunits of the tblgreenSheets. */

    //private int venUnits;

    private String venUnits;

    

    /** The venUnitRate is used for storing the venunitrate of the tblgreenSheets. */

    private double venUnitRate;

    

    /** The venConsultantName is used for storing the venconsultantname of the tblgreenSheets. */

    private String venConsultantName;

    

    /** The vendorContactPerson is used for storing the vendorcontactperson of the tblgreenSheets. */

    private String vendorContactPerson;

    

    

//private String scopework;

    /** The paymentTerms is used for storing the paymentterms of the tblgreenSheets. */

    //private int paymentTerms;

    private String paymentTerms;

    

    /** The invoicing is used for storing the invoicing of the tblgreenSheets. */

    private String invoicing;

    

    /** The scopeId is used for storing the scopeid of the tblgreenSheets. */

    private int scopeId;

    

    /** The marginid is used for storing the marginid of the tblgreenSheets. */

    private int marginid;

    

    /** The total is used for storing the total of the tblgreenSheets. */

    private double total;

    

    /** The currencyType is used for storing the currencytype of the tblgreenSheets. */

    private int currencyType;

    

    /** The salesManager is used for storing the salesmanager of the tblgreenSheets. */

    private String salesManager;

    

    /** The clientBillingRateType is used for storing the clientbillingratetype of the tblgreenSheets. */

    private int clientBillingRateType;

    

    

    

// for editing

    /** The customerName is used for storing the customername of the tblgreenSheets. */

    private String customerName;

    

    /** The viewStartDate is used for storing the viewstartdate of the tblgreenSheets. */

    private String viewStartDate;

    

    /** The viewEndDate is used for storing the viewenddate of the tblgreenSheets. */

    private String viewEndDate;

    

    /** The viewUnits is used for storing the viewunits of the tblgreenSheets. */

    private String viewUnits;

    

    /** The viewClientBillingRateType is used for storing the viewclientbillingratetype of the tblgreenSheets. */

    private String viewClientBillingRateType;

    

    /** The viewCreatedDate is used for storing the viewcreateddate of the tblgreenSheets. */

    private String viewCreatedDate;

    

    /** The actionType is used for storing the actiontype of the tblgreenSheets. */

    private String actionType;

    

    /** The fileName is used for storing the fileName of the PO associated to GreenSheet  */

    private String fileName;

    

    /**The fileId is used to store the Id of the attachement(PO) associated to GreenSheet*/

    private int fileId;

    

    // added

    

    private String scopeOfWork;

    /** The startDate is used for storing startdate of the tblgreenSheets. */

    private java.sql.Date startDate;

    

    /** The endDate is used for storing enddate of the tblgreenSheets. */

    private java.sql.Date endDate;

    

    

    private String empCode;

    private String renewalEmail;

    private String renewalIntEmail;

    private String poType;

    private String poNumber;

    private String poLineno;

    private String intRefcode;

    

    private String maxQuantity;

    private double totalValue;

    private String location;

    private Date consStartDate;

    private Date consEndDate;

    private String nocValue;

    private Date nocDate;    

    private String comments;

    

    private String primaryVicePresident;

    private double primaryVicePresidentCommission;

    private String secondaryVicePresident;

    private double secondaryVicePresidentCommission;

    private int primaryVicePresidentId;

    private int secondaryVicePresidentId;

    

    private String invoiceNo;    

    private String projectName;

    private String customerLocation;

    private java.sql.Date resaleDate;

    private String softDetails;

    private String customerPrice;

    private String avnetPrice;

    private String ibmIcsf;

    private String addServices;

    private String salesTax;

    private String salesTaxState;

    private String profitAmt;

    private String profitPercent;

    private String mspVendor;

    private String country;

    /**
     *  New columns for green sheet entry 
     * ---------------------
     *  1.invFaxNumber 
     *  2.invAttnComments
     *  3.vendorTaxId
     *  4.vendorFaxNumber
     *  5.vendorPaymentAddress
     *  6.vendorComments
     *  7.typeOfResale
     *  8.partNumber
     *  9.contactFaxNumber
     *  10.rejectesReason
     *deleted 
     *9.contactName
     *  10.contactPhoneNumber
     */
    
    private String invFaxNumber;
    private String invAttnComments;
    private String vendorTaxId;
    private String vendorFaxNumber;
    private String vendorPaymentAddress;
    private String vendorComments;
    private String typeOfResale;
    private String partNumber;
   // private String contactName;
    //private String contactPhoneNumber;
    private String contactFaxNumber;
    private String rejectesReason;
    private String practiceName;
    
private String priSalesPerName;
    private String priSalesPerMgrName;
    private String secSalesPerName;
    private String priSalesPerStatus;
     private String priSalesPerMgrStatus;
    private String secSalesPerStatus;
    public int getId() {

        return id;

    }

    

    public void setId(int id) {

        this.id = id;

    }

    

    public int getEmpId() {

        return empId;

    }

    

    public void setEmpId(int empId) {

        this.empId = empId;

    }

    

    public int getConsultantType() {

        return consultantType;

    }

    

    public void setConsultantType(int consultantType) {

        this.consultantType = consultantType;

    }

    

    public int getConsultantId() {

        return consultantId;

    }

    

    public void setConsultantId(int consultantId) {

        this.consultantId = consultantId;

    }

    

    public int getUserAccountID() {

        return userAccountID;

    }

    

    public void setUserAccountID(int userAccountID) {

        this.userAccountID = userAccountID;

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

    

    public String getPhone() {

        return phone;

    }

    

    public void setPhone(String phone) {

        this.phone = phone;

    }

    

    public double getDuration() {

        return duration;

    }

    

    public void setDuration(double duration) {

        this.duration = duration;

    }

    

    public String getReportingAddress() {

        return reportingAddress;

    }

    

    public void setReportingAddress(String reportingAddress) {

        this.reportingAddress = reportingAddress;

    }

    

    public String getReportingManagerName() {

        return reportingManagerName;

    }

    

    public void setReportingManagerName(String reportingManagerName) {

        this.reportingManagerName = reportingManagerName;

    }

    

    public String getReportingManageremail() {

        return reportingManageremail;

    }

    

    public void setReportingManageremail(String reportingManageremail) {

        this.reportingManageremail = reportingManageremail;

    }

    

    public String getBillingAddress() {

        return billingAddress;

    }

    

    public void setBillingAddress(String billingAddress) {

        this.billingAddress = billingAddress;

    }

    

    public String getBillingManagerName() {

        return billingManagerName;

    }

    

    public void setBillingManagerName(String billingManagerName) {

        this.billingManagerName = billingManagerName;

    }

    

    public String getBillingManagerPhone() {

        return billingManagerPhone;

    }

    

    public void setBillingManagerPhone(String billingManagerPhone) {

        this.billingManagerPhone = billingManagerPhone;

    }

    

    public String getBillingManageremail() {

        return billingManageremail;

    }

    

    public void setBillingManageremail(String billingManageremail) {

        this.billingManageremail = billingManageremail;

    }

    

    public Date getDateStart() {

        return dateStart;

    }

    

    public void setDateStart(Date dateStart) {

        this.dateStart = dateStart;

    }

    

    public Date getDateEnd() {

        return dateEnd;

    }

    

    public void setDateEnd(Date dateEnd) {

        this.dateEnd = dateEnd;

    }

    

    public Date getDateCreated() {

        return dateCreated;

    }

    

    public void setDateCreated(Date dateCreated) {

        this.dateCreated = dateCreated;

    }

    /*

    public int getUnits() {

        return units;

    }

    

    public void setUnits(int units) {

        this.units = units;

    }*/

    

    public String getUnits() {

        return units;

    }

    

    public void setUnits(String units) {

        this.units = units;

    }

    

    public double getClientBillingRate() {

        return clientBillingRate;

    }

    

    public void setClientBillingRate(double clientBillingRate) {

        this.clientBillingRate = clientBillingRate;

    }

    

    public String getExpenses() {

        return expenses;

    }

    

    public void setExpenses(String expenses) {

        this.expenses = expenses;

    }

    

    public String getExpenseDetail() {

        return expenseDetail;

    }

    

    public void setExpenseDetail(String expenseDetail) {

        this.expenseDetail = expenseDetail;

    }

    

    public String getEquipmentNeeded() {

        return equipmentNeeded;

    }

    

    public void setEquipmentNeeded(String equipmentNeeded) {

        this.equipmentNeeded = equipmentNeeded;

    }

    

    public String getOtallowed() {

        return otallowed;

    }

    

    public void setOtallowed(String otallowed) {

        this.otallowed = otallowed;

    }

    

    public int getPriSalesMgrId() {

        return priSalesMgrId;

    }

    

    public void setPriSalesMgrId(int priSalesMgrId) {

        this.priSalesMgrId = priSalesMgrId;

    }

    

    public int getPriSalesPersonId() {

        return priSalesPersonId;

    }

    

    public void setPriSalesPersonId(int priSalesPersonId) {

        this.priSalesPersonId = priSalesPersonId;

    }

    

    public int getPriBDMId() {

        return priBDMId;

    }

    

    public void setPriBDMId(int priBDMId) {

        this.priBDMId = priBDMId;

    }

    

    public int getSecSalesPersonId() {

        return secSalesPersonId;

    }

    

    public void setSecSalesPersonId(int secSalesPersonId) {

        this.secSalesPersonId = secSalesPersonId;

    }

    

    public double getPriSalesMgrCommission() {

        return priSalesMgrCommission;

    }

    

    public void setPriSalesMgrCommission(double priSalesMgrCommission) {

        this.priSalesMgrCommission = priSalesMgrCommission;

    }

    

    public double getPriSalesPersonCommission() {

        return priSalesPersonCommission;

    }

    

    public void setPriSalesPersonCommission(double priSalesPersonCommission) {

        this.priSalesPersonCommission = priSalesPersonCommission;

    }

    

    public double getSecSalesPersonCommission() {

        return secSalesPersonCommission;

    }

    

    public void setSecSalesPersonCommission(double secSalesPersonCommission) {

        this.secSalesPersonCommission = secSalesPersonCommission;

    }

    

    public double getPriBDMCommission() {

        return priBDMCommission;

    }

    

    public void setPriBDMCommission(double priBDMCommission) {

        this.priBDMCommission = priBDMCommission;

    }

    

    public String getPoStatus() {

        return poStatus;

    }

    

    public void setPoStatus(String poStatus) {

        this.poStatus = poStatus;

    }

    

    public String getVpApprovalStatus() {

        return vpApprovalStatus;

    }

    

    public void setVpApprovalStatus(String vpApprovalStatus) {

        this.vpApprovalStatus = vpApprovalStatus;

    }

    

    public String getStatus() {

        return status;

    }

    

    public void setStatus(String status) {

        this.status = status;

    }

    

    public String getAgencyName() {

        return agencyName;

    }

    

    public void setAgencyName(String agencyName) {

        this.agencyName = agencyName;

    }

    

    public String getVendorContact() {

        return vendorContact;

    }

    

    public void setVendorContact(String vendorContact) {

        this.vendorContact = vendorContact;

    }

    

    public String getVendorContactPhone() {

        return vendorContactPhone;

    }

    

    public void setVendorContactPhone(String vendorContactPhone) {

        this.vendorContactPhone = vendorContactPhone;

    }

    

    public String getVendorContactEmail() {

        return vendorContactEmail;

    }

    

    public void setVendorContactEmail(String vendorContactEmail) {

        this.vendorContactEmail = vendorContactEmail;

    }

    /*

    public int getVenUnits() {

        return venUnits;

    }

    

    public void setVenUnits(int venUnits) {

        this.venUnits = venUnits;

    }*/

    

    public String getVenUnits() {

        return venUnits;

    }

    

    public void setVenUnits(String venUnits) {

        this.venUnits = venUnits;

    }

    

    public double getVenUnitRate() {

        return venUnitRate;

    }

    

    public void setVenUnitRate(double venUnitRate) {

        this.venUnitRate = venUnitRate;

    }

    

    public String getVenConsultantName() {

        return venConsultantName;

    }

    

    public void setVenConsultantName(String venConsultantName) {

        this.venConsultantName = venConsultantName;

    }

    

    public String getVendorContactPerson() {

        return vendorContactPerson;

    }

    

    public void setVendorContactPerson(String vendorContactPerson) {

        this.vendorContactPerson = vendorContactPerson;

    }

    

    

    public String getInvoicing() {

        return invoicing;

    }

    

    public void setInvoicing(String invoicing) {

        this.invoicing = invoicing;

    }

    

    public int getScopeId() {

        return scopeId;

    }

    

    public void setScopeId(int scopeId) {

        this.scopeId = scopeId;

    }

    

    public int getMarginid() {

        return marginid;

    }

    

    public void setMarginid(int marginid) {

        this.marginid = marginid;

    }

    

    public double getTotal() {

        return total;

    }

    

    public void setTotal(double total) {

        this.total = total;

    }

    

    public int getCurrencyType() {

        return currencyType;

    }

    

    public void setCurrencyType(int currencyType) {

        this.currencyType = currencyType;

    }

    

    public String getSalesManager() {

        return salesManager;

    }

    

    public void setSalesManager(String salesManager) {

        this.salesManager = salesManager;

    }

    

    public String getCustomerName() {

        return customerName;

    }

    

    public void setCustomerName(String customerName) {

        this.customerName = customerName;

    }

    

    public String getViewStartDate() {

        return viewStartDate;

    }

    

    public void setViewStartDate(String viewStartDate) {

        this.viewStartDate = viewStartDate;

    }

    

    public String getViewEndDate() {

        return viewEndDate;

    }

    

    public void setViewEndDate(String viewEndDate) {

        this.viewEndDate = viewEndDate;

    }

    

    public String getViewUnits() {

        return viewUnits;

    }

    

    public void setViewUnits(String viewUnits) {

        this.viewUnits = viewUnits;

    }

    

    public int getClientBillingRateType() {

        return clientBillingRateType;

    }

    

    public void setClientBillingRateType(int clientBillingRateType) {

        this.clientBillingRateType = clientBillingRateType;

    }

    

    public String getViewClientBillingRateType() {

        return viewClientBillingRateType;

    }

    

    public void setViewClientBillingRateType(String viewClientBillingRateType) {

        this.viewClientBillingRateType = viewClientBillingRateType;

    }

    

    

    

    public String getViewCreatedDate() {

        return viewCreatedDate;

    }

    

    public void setViewCreatedDate(String viewCreatedDate) {

        this.viewCreatedDate = viewCreatedDate;

    }

    

    public String getActionType() {

        return actionType;

    }

    

    public void setActionType(String actionType) {

        this.actionType = actionType;

    }

    

    

    public String getPaymentTerms() {

        return paymentTerms;

    }

    

    public void setPaymentTerms(String paymentTerms) {

        this.paymentTerms = paymentTerms;

    }

    

    public String getReportingMGRPhone() {

        return reportingMGRPhone;

    }

    

    public void setReportingMGRPhone(String reportingMGRPhone) {

        this.reportingMGRPhone = reportingMGRPhone;

    }

    

    public String getFileName() {

        return fileName;

    }

    

    public void setFileName(String fileName) {

        this.fileName = fileName;

    }

    

    public int getFileId() {

        return fileId;

    }

    

    public void setFileId(int fileId) {

        this.fileId = fileId;

    }

    

    // added

    

        public String getEmpCode() {

        return empCode;

    }

    

    public void setEmpCode(String empCode) {

        this.empCode = empCode;

    }

    

    public String getRenewalEmail() {

        return renewalEmail;

    }

    

    public void setRenewalEmail(String renewalEmail) {

        this.renewalEmail = renewalEmail;

    }

    

    public String getRenewalIntEmail() {

        return renewalIntEmail;

    }

    

    public void setRenewalIntEmail(String renewalIntEmail) {

        this.renewalIntEmail = renewalIntEmail;

    }

    

    public String getPoType() {

        return poType;

    }

    

    public void setPoType(String poType) {

        this.poType = poType;

    }

    

    public String getPoNumber() {

        return poNumber;

    }

    

    public void setPoNumber(String poNumber) {

        this.poNumber = poNumber;

    }

    

    public String getPoLineno() {

        return poLineno;

    }

    

    public void setPoLineno(String poLineno) {

        this.poLineno = poLineno;

    }

    

    public String getIntRefcode() {

        return intRefcode;

    }

    

    public void setIntRefcode(String intRefcode) {

        this.intRefcode = intRefcode;

    }

    

    public String getMaxQuantity() {

        return maxQuantity;

    }

    

    public void setMaxQuantity(String maxQuantity) {

        this.maxQuantity = maxQuantity;

    }

    

    public double getTotalValue() {

        return totalValue;

    }

    

    public void setTotalValue(double totalValue) {

        this.totalValue = totalValue;

    }

    

    public String getLocation() {

        return location;

    }

    

    public void setLocation(String location) {

        this.location = location;

    }

    

    

    public Date getConsStartDate() {

        return consStartDate;

    }

    

    public void setConsStartDate(Date consStartDate) {

        this.consStartDate = consStartDate;

    }

    

    public Date getConsEndDate() {

        return consEndDate;

    }

    

    public void setConsEndDate(Date consEndDate) {

        this.consEndDate = consEndDate;

    }

    

    public Date getNocDate() {

        return nocDate;

    }

    

    public void setNocDate(Date nocDate) {

        this.nocDate = nocDate;

    }

    

    public String getNocValue() {

        return nocValue;

    }

    

    public void setNocValue(String nocValue) {

        this.nocValue = nocValue;

    }

    

    public String getComments() {

        return comments;

    }

    

    public void setComments(String comments) {

        this.comments = comments;

    }

    

    public String getScopeOfWork() {

        return scopeOfWork;

    }

    

    public void setScopeOfWork(String scopeOfWork) {

        this.scopeOfWork = scopeOfWork;

    }

    

    public java.sql.Date getStartDate() {

        return startDate;

    }

    

    public void setStartDate(java.sql.Date startDate) {

        this.startDate = startDate;

    }

    

    public java.sql.Date getEndDate() {

        return endDate;

    }

    

    public void setEndDate(java.sql.Date endDate) {

        this.endDate = endDate;

    }    

    

        public String getPrimaryVicePresident() {

        return primaryVicePresident;

    }



    public void setPrimaryVicePresident(String primaryVicePresident) {

        this.primaryVicePresident = primaryVicePresident;

    }



    public double getPrimaryVicePresidentCommission() {

        return primaryVicePresidentCommission;

    }



    public void setPrimaryVicePresidentCommission(double primaryVicePresidentCommission) {

        this.primaryVicePresidentCommission = primaryVicePresidentCommission;

    }



    public String getSecondaryVicePresident() {

        return secondaryVicePresident;

    }



    public void setSecondaryVicePresident(String secondaryVicePresident) {

        this.secondaryVicePresident = secondaryVicePresident;

    }



    public double getSecondaryVicePresidentCommission() {

        return secondaryVicePresidentCommission;

    }



    public void setSecondaryVicePresidentCommission(double secondaryVicePresidentCommission) {

        this.secondaryVicePresidentCommission = secondaryVicePresidentCommission;

    }

    

    public int getPrimaryVicePresidentId() {

        return primaryVicePresidentId;

    }

    

    public void setPrimaryVicePresidentId(int primaryVicePresidentId) {

        this.primaryVicePresidentId = primaryVicePresidentId;

    }

    

    public int getSecondaryVicePresidentId() {

        return secondaryVicePresidentId;

    }

    

    public void setSecondaryVicePresidentId(int secondaryVicePresidentId) {

        this.secondaryVicePresidentId = secondaryVicePresidentId;

    }

    

    

    

    public String getInvoiceNo() {

        return invoiceNo;

    }



    public void setInvoiceNo(String invoiceNo) {

        this.invoiceNo = invoiceNo;

    }



    public String getProjectName() {

        return projectName;

    }



    public void setProjectName(String projectName) {

        this.projectName = projectName;

    }



    public String getCustomerLocation() {

        return customerLocation;

    }



    public void setCustomerLocation(String customerLocation) {

        this.customerLocation = customerLocation;

    }



    public java.sql.Date getResaleDate() {

        return resaleDate;

    }



    public void setResaleDate(java.sql.Date resaleDate) {

        this.resaleDate = resaleDate;

    }



    public String getSoftDetails() {

        return softDetails;

    }



    public void setSoftDetails(String softDetails) {

        this.softDetails = softDetails;

    }



    public String getCustomerPrice() {

        return customerPrice;

    }



    public void setCustomerPrice(String customerPrice) {

        this.customerPrice = customerPrice;

    }



    public String getAvnetPrice() {

        return avnetPrice;

    }



    public void setAvnetPrice(String avnetPrice) {

        this.avnetPrice = avnetPrice;

    }



    public String getIbmIcsf() {

        return ibmIcsf;

    }



    public void setIbmIcsf(String ibmIcsf) {

        this.ibmIcsf = ibmIcsf;

    }



    public String getAddServices() {

        return addServices;

    }



    public void setAddServices(String addServices) {

        this.addServices = addServices;

    }



    public String getSalesTax() {

        return salesTax;

    }



    public void setSalesTax(String salesTax) {

        this.salesTax = salesTax;

    }



    public String getSalesTaxState() {

        return salesTaxState;

    }



    public void setSalesTaxState(String salesTaxState) {

        this.salesTaxState = salesTaxState;

    }



    public String getProfitAmt() {

        return profitAmt;

    }



    public void setProfitAmt(String profitAmt) {

        this.profitAmt = profitAmt;

    }



    public String getProfitPercent() {

        return profitPercent;

    }



    public void setProfitPercent(String profitPercent) {

        this.profitPercent = profitPercent;

    }

    

    public String getMspVendor() {

        return mspVendor;

    }

    

    public void setMspVendor(String mspVendor) {

        this.mspVendor = mspVendor;

    }



    public String getCountry() {

        return country;

    }



    public void setCountry(String country) {

        this.country = country;

    }

    /** new **/
    
    public String getContactFaxNumber() {
		return contactFaxNumber;
	}

	public void setContactFaxNumber(String contactFaxNumber) {
		this.contactFaxNumber = contactFaxNumber;
	}

	/*public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}*/

	public String getInvAttnComments() {
		return invAttnComments;
	}

	public void setInvAttnComments(String invAttnComments) {
		this.invAttnComments = invAttnComments;
	}

	public String getInvFaxNumber() {
		return invFaxNumber;
	}

	public void setInvFaxNumber(String invFaxNumber) {
		this.invFaxNumber = invFaxNumber;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getTypeOfResale() {
		return typeOfResale;
	}

	public void setTypeOfResale(String typeOfResale) {
		this.typeOfResale = typeOfResale;
	}

	public String getVendorComments() {
		return vendorComments;
	}

	public void setVendorComments(String vendorComments) {
		this.vendorComments = vendorComments;
	}

	public String getVendorFaxNumber() {
		return vendorFaxNumber;
	}

	public void setVendorFaxNumber(String vendorFaxNumber) {
		this.vendorFaxNumber = vendorFaxNumber;
	}

	public String getVendorPaymentAddress() {
		return vendorPaymentAddress;
	}

	public void setVendorPaymentAddress(String vendorPaymentAddress) {
		this.vendorPaymentAddress = vendorPaymentAddress;
	}

	public String getVendorTaxId() {
		return vendorTaxId;
	}

	public void setVendorTaxId(String vendorTaxId) {
		this.vendorTaxId = vendorTaxId;
	}
    
        public String getRejectesReason() {
		return rejectesReason;
	}

	public void setRejectesReason(String rejectesReason) {
		this.rejectesReason = rejectesReason;
	}

    /**
     * @return the practiceName
     */
    public String getPracticeName() {
        return practiceName;
    }

    /**
     * @param practiceName the practiceName to set
     */
    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    /**
     * @return the priSalesPerName
     */
    public String getPriSalesPerName() {
        return priSalesPerName;
    }

    /**
     * @param priSalesPerName the priSalesPerName to set
     */
    public void setPriSalesPerName(String priSalesPerName) {
        this.priSalesPerName = priSalesPerName;
    }

    /**
     * @return the priSalesPerMgrName
     */
    public String getPriSalesPerMgrName() {
        return priSalesPerMgrName;
    }

    /**
     * @param priSalesPerMgrName the priSalesPerMgrName to set
     */
    public void setPriSalesPerMgrName(String priSalesPerMgrName) {
        this.priSalesPerMgrName = priSalesPerMgrName;
    }

    /**
     * @return the secSalesPerName
     */
    public String getSecSalesPerName() {
        return secSalesPerName;
    }

    /**
     * @param secSalesPerName the secSalesPerName to set
     */
    public void setSecSalesPerName(String secSalesPerName) {
        this.secSalesPerName = secSalesPerName;
    }

    /**
     * @return the priSalesPerStatus
     */
    public String getPriSalesPerStatus() {
        return priSalesPerStatus;
    }

    /**
     * @param priSalesPerStatus the priSalesPerStatus to set
     */
    public void setPriSalesPerStatus(String priSalesPerStatus) {
        this.priSalesPerStatus = priSalesPerStatus;
    }

    /**
     * @return the priSalesPerMgrStatus
     */
    public String getPriSalesPerMgrStatus() {
        return priSalesPerMgrStatus;
    }

    /**
     * @param priSalesPerMgrStatus the priSalesPerMgrStatus to set
     */
    public void setPriSalesPerMgrStatus(String priSalesPerMgrStatus) {
        this.priSalesPerMgrStatus = priSalesPerMgrStatus;
    }

    /**
     * @return the secSalesPerStatus
     */
    public String getSecSalesPerStatus() {
        return secSalesPerStatus;
    }

    /**
     * @param secSalesPerStatus the secSalesPerStatus to set
     */
    public void setSecSalesPerStatus(String secSalesPerStatus) {
        this.secSalesPerStatus = secSalesPerStatus;
    }
}

