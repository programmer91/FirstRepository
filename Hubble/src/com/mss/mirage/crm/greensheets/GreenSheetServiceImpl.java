/*******************************************************************************
 * /*
 * @(#)GreenSheetServiceImpl.java	September 26, 2007, 12:13 AM
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.mss.mirage.crm.greensheets;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.HibernateServiceLocator;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import javax.servlet.http.HttpSession;
/**
 * The <code>GreenSheetServiceImpl</code>  class is used for getting new Employee details from
 * <i>GreenSheetAdd.jsp</i> page.
 * <p>
 * Then it invokes setter methods in <code>AddEmployee</code> class and invokes doEdit() method
 * in <code>GreenSheetAction</code> for inserting employee details in TBLGREENSHEETS table.
 *
 * @author Suresh Nalla <a href="mailto:snalla@miraclesoft.com">snalla@miraclesoft.com</a>
 *
 * @version 1.0 November 29, 2007
 *
 * @see com.mss.mirage.crm.greensheets.GreenSheetServiceImpl
 */

/**
 *
 * @author  Suresh Nalla<snalla@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class GreenSheetServiceImpl implements GreenSheetService{
    
    
    /** Creates a new instance of GreenSheetServiceImpl */
    public GreenSheetServiceImpl() {
    }
    
    /**
     *This @editGreenSheet can be used update the Record
     *@ return type is boolean for know the status
     */
    
    public boolean editGreenSheet(GreenSheetAction greenSheetAction) throws ServiceLocatorException {
        
        boolean isEdit=false;
        int count = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            
            connection= ConnectionProvider.getInstance().getConnection();
            preparedStatement=connection.prepareStatement("update tblCrmAttachments set AttachmentLocation=?, AttachmentName=?  where ObjectId=? and ObjectType=?");
            preparedStatement.setString(1,greenSheetAction.getFilepath());
            preparedStatement.setString(2,greenSheetAction.getUploadFileName());
            preparedStatement.setInt(3,greenSheetAction.getId());
            preparedStatement.setString(4,"GreenSheet");
            
            count = preparedStatement.executeUpdate();
            
            if(count > 0)
                isEdit=true;
            
            
        } catch (Exception ex) {
            
            throw new ServiceLocatorException(ex);
        }finally{
            try {
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return isEdit;
    }
    
    public int getPoAttached(Connection connection,int greensheetId) throws ServiceLocatorException{
        int projectId = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        
        String queryString = "SELECT * from tblCrmAttachments where ObjectType='GreenSheet' and ObjectID="+greensheetId;
        try{
            statement =connection.createStatement();
            resultSet =statement.executeQuery(queryString);
            if(resultSet.next()){
                projectId = resultSet.getInt("ObjectID");
            }else{
                projectId = 0;
            }
            
        }catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }finally {
            
            try{
                if(resultSet != null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement != null){
                    statement.close();
                    statement = null;
                }
                
                
            }catch(SQLException sqle){
                throw new ServiceLocatorException(sqle);
            }
        }
        return projectId;
    }
    
    public boolean insertGreenSheet(GreenSheetAction action) throws ServiceLocatorException{
        boolean isInsert=false;
        int isPoAttached = 0;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int updatedRows = 0;
        try {
            
            connection = ConnectionProvider.getInstance().getConnection();
            
            if(action.getOperationType().equalsIgnoreCase("Ins")){
                // if(getUploadFileName() != null && !getUploadFileName().equals(null)){
                // if(action.getUploadFileName() != null && action.getUploadFileName().length()>1){
                if(action.getUploadFileName() != null && !action.getUploadFileName().equals(null) ){
                    isInsert = insertGreenSheetAttach(action,action.getId());
                }else{
                    isInsert = true;
                }
            }else if(action.getOperationType().equalsIgnoreCase("Upd")){
                
                isPoAttached = getPoAttached(connection,action.getId());
                
                // if(action.getUploadFileName() != null && action.getUploadFileName().length()>1 && (isPoAttached > 0)){
                
                if(action.getUploadFileName() != null && !action.getUploadFileName().equals(null) && (isPoAttached > 0) && action.getUploadFileName().length()>1){                
                    isInsert = editGreenSheet(action);
                }else if(action.getUploadFileName() != null && !action.getUploadFileName().equals(null) && (isPoAttached == 0) && action.getUploadFileName().length()>1){
                    isInsert = insertGreenSheetAttach(action,action.getId());
                } else{
                    isInsert = true;
                }
            }
            
            if(isInsert) {
                
                
               callableStatement = connection.prepareCall("{call spGreenSheet(?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                
                callableStatement.setInt(1,Integer.parseInt(action.getEmpId()));
                /*if(action.getConsultantTypeId()==4) {
                    callableStatement.setInt(2,4);
                    // ("Internal") ==4
                }else{
                    callableStatement.setInt(2,5);
                }*/
                //System.out.println("Consultant Type ------->"+action.getConsultantType());
                if(action.getConsultantType().equalsIgnoreCase("Internal")) {
                    callableStatement.setInt(2,4);
                }
                /*if(action.getConsultantType()==null || action.getConsultantType().equalsIgnoreCase("Internal")) {
                    callableStatement.setInt(2,4);
                }*/
                else{
                    callableStatement.setInt(2,5);
                }
                callableStatement.setInt(3,Integer.parseInt(action.getConsultantId()));
                callableStatement.setInt(4,Integer.parseInt(action.getConsultantId())); //setting accountId
                callableStatement.setString(5,action.getCustomerName());
                
                callableStatement.setString(6,action.getFname());
                callableStatement.setString(7,action.getLastName());
                callableStatement.setString(8,action.getMiddleName());
                callableStatement.setString(9,action.getPhone());
                callableStatement.setDouble(10,action.getDuration());
                
                callableStatement.setString(11,action.getReportingAddress());
                callableStatement.setString(12,action.getReportingManager());
                callableStatement.setString(13,action.getReportingMGRPhone());
                callableStatement.setString(14,action.getReportingManagerEmail());
                callableStatement.setString(15,action.getBillingAddress());
                
                callableStatement.setString(16,action.getBillingManager());
                callableStatement.setString(17,action.getBillingphone());
                callableStatement.setString(18,action.getBillingManagerEmail());
                
                /*if(action.getStartDate()==null){
                    //callableStatement.setDate(20,DateUtility.getInstance().convertStringToMySql(DateUtility.getInstance().getToDayDate()));
                    callableStatement.setDate(19, (Date) DateUtility.getInstance().convertStringToMySql(DateUtility.getInstance().getToDayDate()));
                    //greensheetVTO.setDateEnd(new java.text.SimpleDateFormat("MM/dd/yyyy").parse(new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date())));
                }else{
                    callableStatement.setDate(19,action.getStartDate());
                }
                 
                if(action.getEndDate()==null){
                    //callableStatement.setDate(20,DateUtility.getInstance().convertStringToMySql(DateUtility.getInstance().getToDayDate()));
                    callableStatement.setDate(20, (Date) DateUtility.getInstance().convertStringToMySql(DateUtility.getInstance().getToDayDate()));
                    //greensheetVTO.setDateEnd(new java.text.SimpleDateFormat("MM/dd/yyyy").parse(new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date())));
                }else{
                    callableStatement.setDate(20,action.getEndDate());
                }*/
                
                
                callableStatement.setDate(19,action.getStartDate());
               // System.out.println("dates1212"+action.getStartDate());
                
                callableStatement.setDate(20,action.getEndDate());
                
                //callableStatement.setTimestamp(21,DateUtility.getInstance().getCurrentMySqlDateTime());
                callableStatement.setString(21,action.getUnits());
                callableStatement.setDouble(22,Double.parseDouble(action.getClientBillingRate()));
                callableStatement.setString(23,action.getExpenses());
                callableStatement.setString(24,action.getClientBillingRateType());
                callableStatement.setString(25,action.getExpensesDetails());
                
                callableStatement.setString(26,action.getEquipmentNeeded());
                callableStatement.setString(27,action.getOtAllowed());
                callableStatement.setInt(28,Integer.parseInt(action.getPrimarySalesPerson()));
                callableStatement.setInt(29,Integer.parseInt(action.getPrimarySalesManager()));
                callableStatement.setInt(30,Integer.parseInt(action.getSecondarySalesPerson()));
                
                callableStatement.setDouble(31,action.getPriSalesMngCommission());
                callableStatement.setDouble(32,action.getPriSalesPersonCommission());
                callableStatement.setDouble(33,action.getSecondarySalesPersonCommission());
                callableStatement.setString(34,action.getPoStatus());
                callableStatement.setString(35,action.getVpSalesApproved());
                
                callableStatement.setString(36,action.getStatus());
                callableStatement.setString(37,action.getAgencyName());
                callableStatement.setString(38,action.getVendorContactPerson());
                callableStatement.setString(39,action.getVendorPhone());
                callableStatement.setString(40,action.getVendorEmail());
                
                callableStatement.setString(41,action.getVendorUnits());
                callableStatement.setString(42,action.getVendorRate());
                callableStatement.setString(43,action.getInvoicing());
                callableStatement.setString(44,action.getScopeOfWork());
                callableStatement.setString(45,action.getPayementTerms());
                
                callableStatement.setDouble(46,action.getTotalValue());
                callableStatement.setString(47,action.getRenewalEmail());
                callableStatement.setString(48,action.getRenewalIntEmail());
                callableStatement.setString(49,action.getPoType());
                callableStatement.setString(50,action.getPoNumber());
                
                callableStatement.setString(51,action.getPoLineno());
                callableStatement.setString(52,action.getIntRefcode());
                callableStatement.setString(53,action.getMaxQuantity());
                callableStatement.setString(54,action.getLocation());
                callableStatement.setDate(55,action.getConsStartDate());
                
                callableStatement.setDate(56,action.getConsEndDate());
                callableStatement.setString(57,action.getNocValue());
                callableStatement.setDate(58,action.getNocDate());
                callableStatement.setString(59,action.getComments());
                callableStatement.setString(60,action.getOperationType());
                
                callableStatement.setInt(61,action.getId());
                callableStatement.setString(62,action.getEmpCode());
                
                callableStatement.setTimestamp(63,action.getDateCreatedOn());
                callableStatement.setTimestamp(64,action.getDateModifiedOn());
                callableStatement.setString(65,action.getCreatedBy());
                callableStatement.setString(66,action.getModifedBy());
                callableStatement.setInt(67,Integer.parseInt(action.getPrimaryVicePresident()));
                callableStatement.setInt(68,Integer.parseInt(action.getSecondaryVicePresident()));
                callableStatement.setDouble(69,action.getPrimaryVicePresidentCommission());
                callableStatement.setDouble(70,action.getSecondaryVicePresidentCommission());
                
                callableStatement.setString(71,action.getInvoiceNo());
                callableStatement.setString(72,action.getProjectName());
                callableStatement.setString(73,action.getCustomerLocation());
                callableStatement.setDate(74,action.getResaleDate());
                callableStatement.setString(75,action.getSoftDetails());
                callableStatement.setString(76,action.getCustomerPrice());
                callableStatement.setString(77,action.getAvnetPrice());
                callableStatement.setString(78,action.getIbmIcsf());
                callableStatement.setString(79,action.getAddServices());
                callableStatement.setString(80,action.getSalesTax());
                callableStatement.setString(81,action.getSalesTaxState());
                callableStatement.setString(82,action.getProfitAmt());
                callableStatement.setString(83,action.getProfitPercent());
                callableStatement.setString(84,action.getMspVendor());
                callableStatement.setString(85,action.getCountry());
                
                /** New **/
             /**   IN   invFaxNumber              VARCHAR(30),
  IN   invAttnComments           VARCHAR(1500),
  IN   vendorTaxId               VARCHAR(30),
  IN   vendorFaxNumber           VARCHAR(30),
  IN   vendorPaymentAddress      VARCHAR(800),
  IN   vendorComments            VARCHAR(1500),
  IN   typeOfResale              VARCHAR(1500),
  IN   softwarePartNumber        VARCHAR(50),
  IN   contactName               VARCHAR(50),
  IN   contactPhoneNumber        VARCHAR(25),
  IN   contactFaxNumber          VARCHAR(30),**/
                
                
                
                callableStatement.setString(86,action.getInvFaxNumber());
                callableStatement.setString(87,action.getInvAttnComments());
                callableStatement.setString(88,action.getVendorTaxId());
                callableStatement.setString(89,action.getVendorFaxNumber());
                callableStatement.setString(90,action.getVendorPaymentAddress());
                callableStatement.setString(91,action.getVendorComments());
                callableStatement.setString(92,action.getTypeOfResale());
                callableStatement.setString(93,action.getPartNumber());
                //callableStatement.setString(94,action.getContactName());
                //callableStatement.setString(95,action.getContactPhoneNumber());
                callableStatement.setString(94,action.getContactFaxNumber());
                callableStatement.setString(95,action.getRejectesReason());
                callableStatement.setString(96,action.getPracticeName());
                callableStatement.registerOutParameter(97,Types.INTEGER);
                
                updatedRows = callableStatement.executeUpdate();
                
                //System.err.println("greensheet id-------"+callableStatement.getInt(96));
                int greenSheetMaxId = callableStatement.getInt(97);
                if(greenSheetMaxId > 1){
                action.setId(greenSheetMaxId);
                }
               /* if(updatedRows == 1) {
                    isInsert=true;
                }else{
                    isInsert=false;
                }*/
                if(greenSheetMaxId >= 1) {
                    isInsert=true;
                }else{
                    isInsert=false;
                }
                
                
            }
            
        }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
        }finally{
            try{
                if(callableStatement!=null){
                    callableStatement.close();
                    callableStatement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        return isInsert;
    }
    
    /**
     *This @insertGreenSheet can be used save the values to DataBase
     *@ return type is Boolean
     */
    public boolean insertGreenSheetAttach(GreenSheetAction action, int greensheetId) throws ServiceLocatorException{
        
        String Str_SQL=null;
        String Str_SQL_Gsheet=null;
        boolean isInsert=false;
        int iDGsheetCount=0;
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement=connection.createStatement();
            connection.setAutoCommit(false);
            
            Str_SQL="SELECT ifnull(max(Id),0)+1 FROM tblCrmAttachments";
            int iDCount=0;
            resultSet = statement.executeQuery(Str_SQL);
            while(resultSet.next()) {
                iDCount=resultSet.getInt(1);
            }
            
            if(greensheetId == 0){
                Str_SQL_Gsheet="SELECT ifnull(max(Id),0)+1 FROM tblGreensheets";
                
                resultSet = statement.executeQuery(Str_SQL_Gsheet);
                while(resultSet.next()) {
                    iDGsheetCount=resultSet.getInt(1);
                }
            }else{
                iDGsheetCount=greensheetId;
            }
            
            
            Str_SQL = "insert into tblCrmAttachments values(?,?,?,?,?,?,?)";
            
            preparedStatement=connection.prepareStatement(Str_SQL);
            preparedStatement.setInt(1,iDCount);
            preparedStatement.setInt(2,iDGsheetCount);
            //preparedStatement.setInt(2,Integer.parseInt(action.getConsultantId()));
            preparedStatement.setString(3,"GreenSheet");
            preparedStatement.setString(4,action.getUploadFileName());
            preparedStatement.setString(5,action.getFilepath());
            preparedStatement.setString(6,action.getUploadFileName());
           // preparedStatement.setTimestamp(7,new java.sql.Timestamp(new java.util.Date().getTime()));
             preparedStatement.setTimestamp(7,DateUtility.getInstance().getCurrentMySqlDateTime());
            if(preparedStatement.executeUpdate()>0) isInsert=true;
            
            
            connection.commit();
            
            
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }finally {
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                    statement.close();
                    statement = null;
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (Exception ex) {
                throw new ServiceLocatorException(ex);
            }
            
        }
        
        return isInsert;
    }
    
    
    /**
     *This @GreenSheetVTO can be used to set the action class values to the bean Class
     *@ return type is GreenSheetVTO Object
     */
    
    public GreenSheetVTO getGreenSheetByID(int greensheetId,HttpSession httpsession) throws ServiceLocatorException {
        
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        String queryString = null;
        GreenSheetVTO greenSheetVTO = new GreenSheetVTO();
        PreparedStatement preparedStatement = null;
        String poTypeTemp = null;
        
        queryString = "SELECT * FROM tblGreensheets WHERE Id="+greensheetId;
        
        try{
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            greenSheetVTO.setId(greensheetId);
            
            while(resultSet.next()){
                greenSheetVTO.setCountry(resultSet.getString("Country"));
                greenSheetVTO.setConsultantId(resultSet.getInt("ConsultantId"));
                
                greenSheetVTO.setConsultantType(resultSet.getInt("ConsultantType"));
                greenSheetVTO.setCustomerName(resultSet.getString("CustomerName"));
                greenSheetVTO.setStatus(resultSet.getString("Status"));
                greenSheetVTO.setPoStatus(resultSet.getString("POStatus"));
                greenSheetVTO.setEmpCode(resultSet.getString("EmployeeCode"));
                greenSheetVTO.setRenewalEmail(resultSet.getString("RenExternalEmail"));
                greenSheetVTO.setRenewalIntEmail(resultSet.getString("RenInternalEmail"));
                
                poTypeTemp = resultSet.getString("PoType");
                greenSheetVTO.setPoType(poTypeTemp);
                
                greenSheetVTO.setPoNumber(resultSet.getString("PoNumber"));
                greenSheetVTO.setPoLineno(resultSet.getString("PolineNumber"));
                greenSheetVTO.setIntRefcode(resultSet.getString("ReferanceCode"));
                
                greenSheetVTO.setFirstName(resultSet.getString("FirstName"));
                greenSheetVTO.setLastName(resultSet.getString("LastName"));
                greenSheetVTO.setMiddleName(resultSet.getString("MiddleName"));
                greenSheetVTO.setPhone(resultSet.getString("Phone"));
                
                greenSheetVTO.setStartDate(resultSet.getDate("DateStart"));
                
                
                greenSheetVTO.setEndDate(resultSet.getDate("DateEnd"));
                greenSheetVTO.setDuration(resultSet.getDouble("Duration"));
                greenSheetVTO.setUnits(resultSet.getString("Units"));
                greenSheetVTO.setMaxQuantity(resultSet.getString("MaxQuantity"));
                greenSheetVTO.setTotalValue(resultSet.getDouble("Total")); // ???
                
                greenSheetVTO.setLocation(resultSet.getString("Location"));
                greenSheetVTO.setClientBillingRate(resultSet.getDouble("UnitsRate"));
                greenSheetVTO.setClientBillingRateType(resultSet.getInt("CurrencyType"));
                greenSheetVTO.setOtallowed(resultSet.getString("otallowed"));
                greenSheetVTO.setReportingManagerName(resultSet.getString("ReportingManagerName"));
                
                greenSheetVTO.setReportingMGRPhone(resultSet.getString("ReportingManagerPhone"));
                greenSheetVTO.setExpenses(resultSet.getString("Expenses"));
                greenSheetVTO.setReportingManageremail(resultSet.getString("ReportingManageremail"));
                greenSheetVTO.setExpenseDetail(resultSet.getString("ExpenseDetail"));
                greenSheetVTO.setEquipmentNeeded(resultSet.getString("EquipmentDetail"));
                
                greenSheetVTO.setReportingAddress(resultSet.getString("ReportingAddress"));
                greenSheetVTO.setConsStartDate(resultSet.getDate("ConsulStartDate"));
                greenSheetVTO.setConsEndDate(resultSet.getDate("ConsulEndDate"));
                greenSheetVTO.setNocValue(resultSet.getString("NOC"));
                greenSheetVTO.setNocDate(resultSet.getDate("NOCDate"));
                
                greenSheetVTO.setComments(resultSet.getString("Comments"));
                greenSheetVTO.setAgencyName(resultSet.getString("VendorName"));
                greenSheetVTO.setVendorContactPhone(resultSet.getString("VendorContactPhone"));
                greenSheetVTO.setVendorContactEmail(resultSet.getString("VendorContactEmail"));
                greenSheetVTO.setVendorContactPerson(resultSet.getString("VendorContact"));
                
                greenSheetVTO.setVenUnits(resultSet.getString("VenUnits"));
                greenSheetVTO.setVenUnitRate(resultSet.getDouble("VenUnitRate"));
                greenSheetVTO.setBillingManagerName(resultSet.getString("BillingManagerName"));
                greenSheetVTO.setBillingManagerPhone(resultSet.getString("BillingManagerPhone"));
                greenSheetVTO.setBillingManageremail(resultSet.getString("BillingManageremail"));
                
                greenSheetVTO.setScopeOfWork(resultSet.getString("Scopework"));
                greenSheetVTO.setBillingAddress(resultSet.getString("BillingAddress"));
                greenSheetVTO.setInvoicing(resultSet.getString("Invoicing"));
                greenSheetVTO.setVpApprovalStatus(resultSet.getString("VPApprovalStatus"));
                greenSheetVTO.setPaymentTerms(resultSet.getString("PaymentId"));
                
                greenSheetVTO.setPriSalesPersonId(resultSet.getInt("PriSalesPersonId"));
                greenSheetVTO.setPriSalesPersonCommission(resultSet.getDouble("PriSalesPersonCommission"));
                greenSheetVTO.setPriSalesMgrId(resultSet.getInt("PriSalesMgrId"));
                greenSheetVTO.setPriSalesMgrCommission(resultSet.getDouble("PriSalesMgrCommission"));
                greenSheetVTO.setSecSalesPersonId(resultSet.getInt("SecSalesPersonId"));
                greenSheetVTO.setSecSalesPersonCommission(resultSet.getDouble("SecSalesPersonCommission"));
                
                
              DataSourceDataProvider dataSourceDataProvider = DataSourceDataProvider.getInstance();
                
                List Prisaleslist = new ArrayList();
                Prisaleslist = dataSourceDataProvider.getEmpNameCurStatusByEmpId(resultSet.getInt("PriSalesPersonId"));
                greenSheetVTO.setPriSalesPerStatus(Prisaleslist.get(1).toString());
                if (!Prisaleslist.get(1).toString().equals("Active")) {
                     greenSheetVTO.setPriSalesPerName(Prisaleslist.get(0).toString());
                }
                  List PrisalesMgrlist = new ArrayList();
                PrisalesMgrlist = dataSourceDataProvider.getEmpNameCurStatusByEmpId(resultSet.getInt("PriSalesMgrId"));
                
                greenSheetVTO.setPriSalesPerMgrStatus(PrisalesMgrlist.get(1).toString());
                if (!PrisalesMgrlist.get(1).toString().equals("Active")) {
                    greenSheetVTO.setPriSalesPerMgrName(PrisalesMgrlist.get(0).toString());
                } 
                
                List secSaleslist = new ArrayList();
                secSaleslist = dataSourceDataProvider.getEmpNameCurStatusByEmpId(resultSet.getInt("SecSalesPersonId"));
                greenSheetVTO.setSecSalesPerStatus(secSaleslist.get(1).toString());
                if (!secSaleslist.get(1).toString().equals("Active")) {
                    greenSheetVTO.setSecSalesPerName(secSaleslist.get(0).toString()); 
                }


                
                greenSheetVTO.setPrimaryVicePresidentId(resultSet.getInt("PrimaryVPId"));
                greenSheetVTO.setPrimaryVicePresidentCommission(resultSet.getDouble("PrimaryVPIdCommission"));
                greenSheetVTO.setSecondaryVicePresidentId(resultSet.getInt("SecondaryVPId"));
                greenSheetVTO.setSecondaryVicePresidentCommission(resultSet.getDouble("SecondaryVPIdCommission"));
                
                
                
                
                
                
                
                
                greenSheetVTO.setInvoiceNo(resultSet.getString("InvoiceNo"));
                greenSheetVTO.setProjectName(resultSet.getString("ProjectName"));
                greenSheetVTO.setMspVendor(resultSet.getString("MspVendor"));
                
                /**
                 *New fields to set which avialble commenly for all po types.
                 */
                greenSheetVTO.setInvFaxNumber(resultSet.getString("invFaxNumber"));
                greenSheetVTO.setInvAttnComments(resultSet.getString("invAttnComments"));
                greenSheetVTO.setVendorFaxNumber(resultSet.getString("vendorFaxNumber"));
                greenSheetVTO.setVendorPaymentAddress(resultSet.getString("vendorPaymentAddress"));
                greenSheetVTO.setVendorComments(resultSet.getString("vendorComments"));
                greenSheetVTO.setVendorTaxId(resultSet.getString("vendorTaxId"));
                greenSheetVTO.setTypeOfResale(resultSet.getString("typeOfResale"));
                greenSheetVTO.setPartNumber(resultSet.getString("softwarePartNumber"));
                //greenSheetVTO.setContactName(resultSet.getString("contactName"));
                //greenSheetVTO.setContactPhoneNumber(resultSet.getString("contactPhoneNumber"));
                greenSheetVTO.setContactFaxNumber(resultSet.getString("contactFaxNumber"));
                greenSheetVTO.setRejectesReason(resultSet.getString("StatusComments"));
                 greenSheetVTO.setPracticeName(resultSet.getString("Practice"));
                
                //public static String GREENSHEETSTATUS_BEFORE = "greensheetstatusbefore";
                httpsession.setAttribute(ApplicationConstants.GREENSHEETSTATUS_BEFORE,greenSheetVTO.getStatus());
                /** New Setters **/
                
                if(poTypeTemp.equalsIgnoreCase("Software")){
                    greenSheetVTO.setCustomerLocation(resultSet.getString("CustomerLocation"));
                    greenSheetVTO.setResaleDate(resultSet.getDate("ResaleDate"));
                    greenSheetVTO.setSoftDetails(resultSet.getString("SoftwareDet"));
                    greenSheetVTO.setCustomerPrice(resultSet.getString("CustomerPrice"));
                    greenSheetVTO.setAvnetPrice(resultSet.getString("AvnetPrice"));
                    greenSheetVTO.setIbmIcsf(resultSet.getString("IbmIcsf"));
                    greenSheetVTO.setAddServices(resultSet.getString("AddServices"));
                    greenSheetVTO.setSalesTax(resultSet.getString("SalesTax"));
                    greenSheetVTO.setSalesTaxState(resultSet.getString("SalesTaxState"));
                    greenSheetVTO.setProfitAmt(resultSet.getString("ProfitAmt"));
                    greenSheetVTO.setProfitPercent(resultSet.getString("ProfitPercent"));
                } else if(poTypeTemp.equalsIgnoreCase("FixedBid")){
                    greenSheetVTO.setCustomerLocation(resultSet.getString("CustomerLocation"));
                    greenSheetVTO.setResaleDate(resultSet.getDate("ResaleDate"));
                    greenSheetVTO.setSoftDetails(resultSet.getString("SoftwareDet"));
                    greenSheetVTO.setCustomerPrice(resultSet.getString("CustomerPrice"));
                    greenSheetVTO.setAvnetPrice(resultSet.getString("AvnetPrice"));
                    greenSheetVTO.setIbmIcsf(resultSet.getString("IbmIcsf"));
                    greenSheetVTO.setAddServices(resultSet.getString("AddServices"));
                }
            }
            
            if(greenSheetVTO.getConsultantType() != 0){
                
                // for getting the file location
                preparedStatement=connection.prepareStatement("select Id,AttachmentName from tblCrmAttachments where ObjectId=? and ObjectType=?");
                preparedStatement.setInt(1,greenSheetVTO.getId());
                preparedStatement.setString(2,"GreenSheet");
                
                resultSet=preparedStatement.executeQuery();
                
                while(resultSet.next()) {
                    greenSheetVTO.setFileId(resultSet.getInt("Id"));
                    greenSheetVTO.setFileName(resultSet.getString("AttachmentName"));
                }
                
                greenSheetVTO.setActionType("editGeensheet");
                
            }
            
            
        }catch (SQLException sqle){
            throw new ServiceLocatorException(sqle);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                    statement.close();
                    statement = null;
                }
                if(preparedStatement!=null){
                    preparedStatement.close();
                    preparedStatement = null;
                }
                
                if(connection != null){
                    connection.close();
                    connection = null;
                }
            }catch (SQLException se){
                throw new ServiceLocatorException(se);
            }
        }
        
        return greenSheetVTO;
    }
}


