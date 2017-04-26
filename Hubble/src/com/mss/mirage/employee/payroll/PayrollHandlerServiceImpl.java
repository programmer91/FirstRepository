/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.payroll;

import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.ServiceLocatorException;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public class PayrollHandlerServiceImpl implements PayrollHandlerService {

    public String addEmployeePayroll(int empId, PayrollHandlerAction payrollHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        int updatedRows = 0;
        int isSuccess = 0;

        String result;
        try {
            //     System.out.println("empId---------->" + empId);
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spPayrollAdd(?,?)}");
            callableStatement.setInt(1, empId);
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            updatedRows = callableStatement.executeUpdate();

            result = callableStatement.getString(2);
            // System.out.println("result---------->" + result);
            //System.out.println(result.trim().split("\\|").length);
            payrollHandlerAction.setFirstName(result.trim().split("\\|")[0]);
            payrollHandlerAction.setMiddleName(result.trim().split("\\|")[1]);
            payrollHandlerAction.setLastName(result.trim().split("\\|")[2]);
            payrollHandlerAction.setEmployeeName(result.trim().split("\\|")[0] + " " + result.trim().split("\\|")[1] + " " + result.trim().split("\\|")[2]);
            payrollHandlerAction.setDepartmentId(result.trim().split("\\|")[3]);
            payrollHandlerAction.setTitleIdList(DataSourceDataProvider.getInstance().getTitleTypeByDepartment(result.trim().split("\\|")[3]));
            payrollHandlerAction.setTitleId(result.trim().split("\\|")[4]);
            payrollHandlerAction.setSsn(result.trim().split("\\|")[5]);
            //  System.out.println("setGender---------->" + result.trim().split("\\|")[6]);
            payrollHandlerAction.setGender(result.trim().split("\\|")[6]);
            payrollHandlerAction.setBankName(result.trim().split("\\|")[7]);
            payrollHandlerAction.setBankAccountNo(result.trim().split("\\|")[8]);
            payrollHandlerAction.setUANNo(result.trim().split("\\|")[10]);
            payrollHandlerAction.setPfAccount(result.trim().split("\\|")[9]);

            if (!"".equals(result.trim().split("\\|")[11])) {
                payrollHandlerAction.setDateOfJoining(DateUtility.getInstance().convertToviewFormat(result.trim().split("\\|")[11]));
            } else {
                payrollHandlerAction.setDateOfJoining("");
            }
            payrollHandlerAction.setCurrStatus(result.trim().split("\\|")[12]);
            // System.out.println("location---------->" + result.trim().split("\\|")[13]);
            payrollHandlerAction.setLocationId(result.trim().split("\\|")[13]);


            payrollHandlerAction.setTrainingPeriod(result.trim().split("\\|")[14]);

            payrollHandlerAction.setPassportNo(result.trim().split("\\|")[15]);

            String resonsForTerminating = result.trim().split("\\|")[16].replace(',', ' ');
            resonsForTerminating = resonsForTerminating.replace("\"", "  ");
            payrollHandlerAction.setResonsForLeaving(resonsForTerminating);
            /*if (result.trim().split("\\|")[21].equals("0")) {
             payrollHandlerAction.setIsPfFlag(false);
             } else {
             payrollHandlerAction.setIsPfFlag(true);
             }*/
            String address = result.trim().split("\\|")[17].replace(',', ' ');
            address = address.replace("\"", "  ");
            payrollHandlerAction.setAddress(address);
            payrollHandlerAction.setCity(result.trim().split("\\|")[18]);
            payrollHandlerAction.setState(result.trim().split("\\|")[19]);
            payrollHandlerAction.setZip(result.trim().split("\\|")[20]);

            payrollHandlerAction.setCorporateEmail(result.trim().split("\\|")[21]);
            payrollHandlerAction.setPersonalEmail(result.trim().split("\\|")[22]);
            payrollHandlerAction.setFathername(result.trim().split("\\|")[23]);
            payrollHandlerAction.setFatherTitle(result.trim().split("\\|")[24]);
            payrollHandlerAction.setHomePhone(result.trim().split("\\|")[25]);
            payrollHandlerAction.setMobilePhone(result.trim().split("\\|")[26]);
            payrollHandlerAction.setFatherPhone(result.trim().split("\\|")[27]);
            payrollHandlerAction.setAdharNo(result.trim().split("\\|")[28]);
            payrollHandlerAction.setItgBatch(result.trim().split("\\|")[29]);
            payrollHandlerAction.setHomeAddressId(result.trim().split("\\|")[30]);
            payrollHandlerAction.setWorkPhone(result.trim().split("\\|")[31]);
            payrollHandlerAction.setClassificationId(result.trim().split("\\|")[32]);
            payrollHandlerAction.setShiftId(result.trim().split("\\|")[33]);
            int orgId = DataSourceDataProvider.getInstance().getOrgIdByName(result.trim().split("\\|")[34]);
            //   System.out.println("orgId--------->" + orgId);
            payrollHandlerAction.setOrgId(orgId);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se.getMessage());
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        //System.out.println("taskMaxId-->"+taskMaxId);
        return result;
    }

    public String setEmployeePayrollEditDetails(int payrollId, PayrollHandlerAction payrollHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        ResultSet resultSet = null;
       String queryString = "SELECT tblEmployee.FName AS FName,tblEmployee.MName AS MName,tblEmployee.LName AS LName,tblEmployee.DepartmentId AS DepartmentId,tblEmployee.TitleTypeId AS TitleTypeId,tblEmployee.SSN AS SSN,tblEmployee.Gender AS Gender,tblEmpPayRoll.Bank_Name AS Bank_Name, "
                + "tblEmpPayRoll.BankAccountNumber AS BankAccountNumber,tblEmployee.UANNo AS UANNo,tblEmployee.PFNo AS PFNo,tblEmployee.CurStatus AS CurStatus, "
                + "tblEmployee.HireDate AS HireDate,tblEmployee.Location AS Location,tblEmployee.TrainingPeriod AS TrainingPeriod,tblEmpImmigration.PassportNo AS PassportNo,tblEmployee.Termination AS Termination,tblEmpPayRoll.PF_Flag AS PF_Flag,tblEmpPayRoll.NoSalary AS NoSalary,tblEmpAddress.AddressLine1 AS AddressLine1,tblEmpAddress.City AS City,tblEmpAddress.State AS State,tblEmpAddress.Zip AS Zip,tblEmployee.Email1 AS Email1,tblEmployee.Email2 AS Email2,tblEmpAncillary.FatherName AS FatherName,tblEmpAncillary.FatherTitle AS FatherTitle,tblEmpAncillary.FatherPhone AS FatherPhone,tblEmployee.HomePhoneNo AS HomePhoneNo,tblEmployee.CellPhoneNo AS CellPhoneNo,tblEmployee.AadharNum AS AadharNum,tblEmployee.Itgbatch AS Itgbatch,tblEmployee.HomeAddressId AS HomeAddressId,tblEmployee.WorkPhoneNo AS WorkPhoneNo,tblEmpPayRoll.PayRollId , WageComments , WageComments1 , ReferenceDetails , Prev_Employer_YTD_Gross , EmpSavings1 , EmpSavings2 ,tutionfees,hbLoanInterst,ppf,medicalIns,lifeIns,hraLifeInsuranceSavings,premium,eduInterest,fd,hbLoanPrinciple,mutualFunds,nsc ,BasicPay , DA , HRA , TA , RA , Entertainment , KidsEducation , VehcleAllowance , CCA , MiscPay , SplAllowances , LongTermbonus , ProjectPay , AttAllowance , PaymentTypeId , Employer_PF , Employee_PF , Life , Health , Ded_Professional_Tax , "
                + " Ded_Others , GrossPay , VariablePay , TotalCost,GeneralComments,tblEmployee.AadharNum,tblEmpPayRoll.OnProject AS OnProject, "
                + " tblEmpPayRoll.OnSite AS OnSite,tblEmpPayRoll.OrgAccId AS OrgAccId, tblEmployee.EmpNo AS EmpNo,tblEmployee.ClassificationId,NewGrossPay, "
                + " tblEmployee.ShiftId,NetPaid,Employer_ESI,Employee_ESI,tblEmpPayRoll.esiFlag AS esiFlag,tblEmpPayRoll.DateOfEmployement AS DateOfEmployement,tblEmployee.TerminationDate AS DateOfTermination,tblEmpPayRoll.PayRollComments AS PayRollComments,DiffPf,tblEmpPayRoll.CreatedDate,DatePayRevised,LockAmtStartDate,IsSixMonthsLock,tblEmpPayRoll.FixedSalaryFlag,tblEmployee.PracticeId FROM tblEmployee LEFT OUTER JOIN tblEmpPayRoll  ON (tblEmployee.Id=tblEmpPayRoll.EmpId) LEFT OUTER JOIN tblEmpImmigration ON(tblEmpImmigration.EmpId=tblEmployee.Id)LEFT OUTER JOIN tblEmpAddress ON(tblEmpAddress.Id=tblEmployee.HomeAddressId)  "
                + " LEFT OUTER JOIN tblEmpAncillary ON(tblEmpAncillary.EmpId=tblEmployee.Id)   WHERE tblEmpPayRoll.PayRollId= " + payrollId;




        //  System.out.println("setEmployeePayrollEditDetails queryString--->" + queryString);
        Statement statement = null;
        int updatedRows = 0;
        int isSuccess = 0;

        String result = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                payrollHandlerAction.setFirstName(resultSet.getString("FName"));
                payrollHandlerAction.setMiddleName(resultSet.getString("MName"));
                payrollHandlerAction.setLastName(resultSet.getString("LName"));
                payrollHandlerAction.setEmployeeName(resultSet.getString("FName") + resultSet.getString("MName") + resultSet.getString("LName"));
                payrollHandlerAction.setDepartmentId(resultSet.getString("DepartmentId"));
                payrollHandlerAction.setTitleIdList(DataSourceDataProvider.getInstance().getTitleTypeByDepartment(resultSet.getString("DepartmentId")));
                payrollHandlerAction.setTitleId(resultSet.getString("TitleTypeId"));
                payrollHandlerAction.setSsn(resultSet.getString("SSN"));
                // System.out.println("setGender---------->" + result.trim().split("\\|")[6]);
                payrollHandlerAction.setGender(resultSet.getString("Gender"));
                payrollHandlerAction.setBankName(resultSet.getString("Bank_Name"));
                payrollHandlerAction.setBankAccountNo(resultSet.getString("BankAccountNumber"));
                payrollHandlerAction.setUANNo(resultSet.getString("UANNo"));
                payrollHandlerAction.setPfAccount(resultSet.getString("PFNo"));
                if (resultSet.getDate("HireDate") != null && !"01/01/1951".equals(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("HireDate"))))) {
                    payrollHandlerAction.setDateOfJoining(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("HireDate"))));
                } else {
                    payrollHandlerAction.setDateOfJoining(" ");
                }
                if (resultSet.getDate("CreatedDate") != null && !"01/01/1951".equals(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("CreatedDate"))))) {
                    payrollHandlerAction.setCreatedDate(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("CreatedDate"))));
                } else {
                    payrollHandlerAction.setCreatedDate(" ");
                }
                payrollHandlerAction.setCurrStatus(resultSet.getString("CurStatus"));
                payrollHandlerAction.setLocationId(resultSet.getString("Location"));
                payrollHandlerAction.setTrainingPeriod(String.valueOf(resultSet.getInt("TrainingPeriod")));
                payrollHandlerAction.setPassportNo(resultSet.getString("PassportNo"));
                payrollHandlerAction.setResonsForLeaving(resultSet.getString("Termination"));
                if (resultSet.getInt("PF_Flag") == 1) {
                    payrollHandlerAction.setIsPfFlag(true);
                } else {
                    payrollHandlerAction.setIsPfFlag(false);
                }
                String address = "";
                //   System.out.println("address---------" + resultSet.getString("AddressLine1"));
                if (resultSet.getString("AddressLine1") != null) {
                    //    System.out.println("in if");
                    address = resultSet.getString("AddressLine1").replace(',', ' ');
                    address = address.replace("\"", "  ");
                    address = address.replace(":", "  ");
                }
                payrollHandlerAction.setAddress(address);
                payrollHandlerAction.setCity(resultSet.getString("City"));
                payrollHandlerAction.setState(resultSet.getString("State"));
                payrollHandlerAction.setZip(resultSet.getString("Zip"));

                payrollHandlerAction.setCorporateEmail(resultSet.getString("Email1"));
                payrollHandlerAction.setPersonalEmail(resultSet.getString("Email2"));

                payrollHandlerAction.setFathername(resultSet.getString("FatherName"));
                payrollHandlerAction.setFatherTitle(resultSet.getString("FatherTitle"));
                payrollHandlerAction.setHomePhone(resultSet.getString("FatherPhone"));
                payrollHandlerAction.setMobilePhone(resultSet.getString("CellPhoneNo"));
                payrollHandlerAction.setFatherPhone(resultSet.getString("HomePhoneNo"));
                payrollHandlerAction.setAdharNo(resultSet.getString("AadharNum"));
                payrollHandlerAction.setItgBatch(resultSet.getString("Itgbatch"));
                payrollHandlerAction.setHomeAddressId(String.valueOf(resultSet.getInt("HomeAddressId")));
                payrollHandlerAction.setWorkPhone(resultSet.getString("WorkPhoneNo"));
                
                
                
                /* payrollHandlerAction.setWagecomments(resultSet.getString("WageComments"));
                 payrollHandlerAction.setWagecomments1(resultSet.getString("WageComments1"));
                 payrollHandlerAction.setReferencecomments(resultSet.getString("ReferenceDetails"));*/
                payrollHandlerAction.setPrevYtdSalary(resultSet.getDouble("Prev_Employer_YTD_Gross"));

                String empSavingsValues = DataSourceDataProvider.getInstance().getEmpSavings1234and5Values(Integer.parseInt(payrollHandlerAction.getEmpId()));
               if (!"".equals(empSavingsValues)) {
                    payrollHandlerAction.setEmpSaving1(Double.parseDouble(empSavingsValues.split("#")[0]));
                    payrollHandlerAction.setEmpSaving2(Double.parseDouble(empSavingsValues.split("#")[1]));
                  //  payrollHandlerAction.setEmpSaving3(Double.parseDouble(empSavingsValues.split("#")[2]));
                   payrollHandlerAction.setEmpSaving3(Double.parseDouble(empSavingsValues.split("#")[2]) + Double.parseDouble(empSavingsValues.split("#")[5]) );
                 
                    payrollHandlerAction.setEmpSaving4(Double.parseDouble(empSavingsValues.split("#")[3]));
                    payrollHandlerAction.setEmpSaving5(Double.parseDouble(empSavingsValues.split("#")[4]));
                      payrollHandlerAction.setEmpSaving6(Double.parseDouble(empSavingsValues.split("#")[7]));
                } else {
                    payrollHandlerAction.setEmpSaving1(0.00);
                    payrollHandlerAction.setEmpSaving2(0.00);
                    payrollHandlerAction.setEmpSaving3(0.00);
                    payrollHandlerAction.setEmpSaving4(0.00);
                    payrollHandlerAction.setEmpSaving5(0.00);
                     payrollHandlerAction.setEmpSaving6(0.00);

                }

                payrollHandlerAction.setTutionfees(String.valueOf(resultSet.getDouble("tutionfees")));
                payrollHandlerAction.setHbLoanInterst(String.valueOf(resultSet.getDouble("hbLoanInterst")));
                payrollHandlerAction.setPpf(String.valueOf(resultSet.getDouble("ppf")));
                payrollHandlerAction.setMedicalIns(String.valueOf(resultSet.getDouble("medicalIns")));
                payrollHandlerAction.setLifeIns(String.valueOf(resultSet.getDouble("lifeIns")));
                payrollHandlerAction.setHraLifeInsuranceSavings(String.valueOf(resultSet.getDouble("hraLifeInsuranceSavings")));
                payrollHandlerAction.setPremium(String.valueOf(resultSet.getDouble("premium")));
                payrollHandlerAction.setEduInterest(String.valueOf(resultSet.getDouble("eduInterest")));
                payrollHandlerAction.setFd(String.valueOf(resultSet.getDouble("fd")));
                payrollHandlerAction.setHbLoanPrinciple(String.valueOf(resultSet.getDouble("hbLoanPrinciple")));
                payrollHandlerAction.setMutualFunds(String.valueOf(resultSet.getDouble("mutualFunds")));
                payrollHandlerAction.setNsc(String.valueOf(resultSet.getDouble("nsc")));
                // System.out.println("basic-->" + resultSet.getDouble("BasicPay"));
                payrollHandlerAction.setBasic(resultSet.getDouble("BasicPay"));
                payrollHandlerAction.setDa(resultSet.getDouble("DA"));
                payrollHandlerAction.setHra(resultSet.getDouble("HRA"));
                payrollHandlerAction.setTa(resultSet.getDouble("TA"));
                payrollHandlerAction.setRa(resultSet.getDouble("RA"));
                payrollHandlerAction.setEntertainment(resultSet.getDouble("Entertainment"));
                payrollHandlerAction.setKidsEducation(resultSet.getDouble("KidsEducation"));
                payrollHandlerAction.setVehicleAllowance(resultSet.getDouble("VehcleAllowance"));
                payrollHandlerAction.setCca(resultSet.getDouble("CCA"));
                payrollHandlerAction.setMiscPay(resultSet.getDouble("MiscPay"));
                payrollHandlerAction.setSplAllowance(resultSet.getDouble("SplAllowances"));
                payrollHandlerAction.setLongTermBonus(resultSet.getDouble("LongTermbonus"));
                payrollHandlerAction.setProjectPay(resultSet.getDouble("ProjectPay"));
                payrollHandlerAction.setAttendanceAllow(resultSet.getDouble("AttAllowance"));
                payrollHandlerAction.setPaymentType(String.valueOf(resultSet.getInt("PaymentTypeId")));
                payrollHandlerAction.setEmployerPf(resultSet.getDouble("Employer_PF"));
                payrollHandlerAction.setEmployeePf(resultSet.getDouble("Employee_PF"));
                payrollHandlerAction.setLife(resultSet.getDouble("Life"));
                payrollHandlerAction.setHealth(resultSet.getDouble("Health"));
                payrollHandlerAction.setProfessionalTax(resultSet.getDouble("Ded_Professional_Tax"));
                payrollHandlerAction.setOtherDeductions(resultSet.getDouble("Ded_Others"));
                payrollHandlerAction.setGrossPay(resultSet.getDouble("GrossPay"));

                String monthOfCreatedDate = payrollHandlerAction.getCreatedDate().split("/")[0];
                //  System.out.println("monthOfCreatedDate-->"+monthOfCreatedDate);
                double yearlyGross = (payrollHandlerAction.getGrossPay() * 12) - (payrollHandlerAction.getGrossPay() * (Integer.parseInt(monthOfCreatedDate) - 4));
                payrollHandlerAction.setExpectedYearlyCost(yearlyGross);
                payrollHandlerAction.setVariablePay(resultSet.getDouble("VariablePay"));
                payrollHandlerAction.setTotalCost(resultSet.getDouble("TotalCost"));
                // payrollHandlerAction.setGeneralcomments(resultSet.getString("GeneralComments"));
                payrollHandlerAction.setAdharNo(resultSet.getString("AadharNum"));
                if (resultSet.getInt("OnProject") == 1) {
                    payrollHandlerAction.setOnProjectInd(true);
                } else {
                    payrollHandlerAction.setOnProjectInd(false);
                }
                payrollHandlerAction.setOrgId(resultSet.getInt("OrgAccId"));
                payrollHandlerAction.setEmployerId(String.valueOf(resultSet.getInt("EmpNo")));
                payrollHandlerAction.setClassificationId(String.valueOf(resultSet.getInt("ClassificationId")));
                payrollHandlerAction.setShiftId(String.valueOf(resultSet.getInt("ShiftId")));
                /* if(!"01/01/1951".equals(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("ProjectEndDate"))))){
                 payrollHandlerAction.setProjectEndDate(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("ProjectEndDate"))));
                 }
                 else{
                 payrollHandlerAction.setProjectEndDate("");
                 }*/
                payrollHandlerAction.setNetPaidPayroll(resultSet.getDouble("NetPaid"));

                payrollHandlerAction.setEmployeresi(resultSet.getDouble("Employer_ESI"));
                payrollHandlerAction.setEmployeeesi(resultSet.getDouble("Employee_ESI"));
                
                /* payrollHandlerAction.setLifeInsurancePremium(resultSet.getDouble("InvLifePremium_80C"));
                 payrollHandlerAction.setHousingLoanRepay(resultSet.getDouble("InvHouseLoanRepayment_80C"));
                 payrollHandlerAction.setNscTds(resultSet.getDouble("InvNSC_80C"));
                 payrollHandlerAction.setElss(resultSet.getDouble("InvELSS_80C"));
                 payrollHandlerAction.setPostOfficeTerm(resultSet.getDouble("InvPostOfficeTermDeposit_80C"));
                 payrollHandlerAction.setBankDepositTax(resultSet.getDouble("InvBankDeposits_80C"));
                 payrollHandlerAction.setOthersTDS(resultSet.getDouble("InvOthers_80C"));
                 payrollHandlerAction.setContributionToPf(resultSet.getDouble("InvContrToPF_80CCC"));
                 payrollHandlerAction.setNpsEmployeeContr(resultSet.getDouble("Inv80CCDNPSEmpCont_80CCC"));
                 payrollHandlerAction.setTotalTds(resultSet.getDouble("InvTotalFrm80CAnd80CCC"));
                 payrollHandlerAction.setTotalTdsDeductable(resultSet.getDouble("DedFrom80CAnd80CCC"));
                 payrollHandlerAction.setInterstOnBorrowed(resultSet.getDouble("InvIntrOnBorrCap_80CCC"));
                 payrollHandlerAction.setInterstOnBorrowedDeductable(resultSet.getDouble("DedIntrOnBorrCap_80CCC"));
                 payrollHandlerAction.setInsuranceForParents(resultSet.getDouble("InvIntrOnParentsLIC_80D"));
                 payrollHandlerAction.setInsuranceForParentsDeduc(resultSet.getDouble("DedIntrOnParentsLIC_80D"));
                 payrollHandlerAction.setInsuranceOthers(resultSet.getDouble("InvIntrOnSpouseLIC_80D"));
                 payrollHandlerAction.setInsuranceOthersDeduc(resultSet.getDouble("DedIntrOnSpouseLIC_80D"));
                 payrollHandlerAction.setInterstOnEdu(resultSet.getDouble("DedIntrOnEdu_80E"));
                 payrollHandlerAction.setInterstOnHrAssumptionsInv(resultSet.getDouble("InvHRExemptions_80E"));
                 payrollHandlerAction.setInterstOnHrAssumptions(resultSet.getDouble("DedHRExemptions_80E"));
                 payrollHandlerAction.setExpectedYearlyCost(resultSet.getDouble("TotalExpGross"));
                 payrollHandlerAction.setLicFromSal(resultSet.getDouble("InvLICFromSal_80C"));
                 payrollHandlerAction.setTutionFee(resultSet.getDouble("InvTutionFee_80C"));
                 //   System.out.println("ppf------>" + resultSet.getDouble("InvPPFContribution_80C"));
                 payrollHandlerAction.setPpfContribution(resultSet.getDouble("InvPPFContribution_80C"));*/
                if (resultSet.getInt("OnSite") == 1) {
                    payrollHandlerAction.setOnsiteInd(true);
                } else {
                    payrollHandlerAction.setOnsiteInd(false);
                }

                if (resultSet.getInt("esiFlag") == 1) {
                    payrollHandlerAction.setEsiFlag(true);
                } else {
                    payrollHandlerAction.setEsiFlag(false);
                }

                if (resultSet.getDate("DateOfEmployement") != null && !"01/01/1951".equals(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("DateOfEmployement"))))) {
                    payrollHandlerAction.setDateOfEmployement(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("DateOfEmployement"))));
                } else {
                    payrollHandlerAction.setDateOfEmployement("");
                }

                if (resultSet.getDate("DateOfTermination")!= null && !"01/01/1951".equals(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("DateOfTermination"))))) {
                    payrollHandlerAction.setDateOfTermination(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("DateOfTermination"))));
                } else {
                    payrollHandlerAction.setDateOfTermination("");
                }

                if (resultSet.getDate("DatePayRevised")!= null && !"01/01/1951".equals(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("DatePayRevised"))))) {
                    payrollHandlerAction.setDatePayRevised(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("DatePayRevised"))));
                } else {
                    payrollHandlerAction.setDatePayRevised("");
                }

                if (resultSet.getDate("LockAmtStartDate") != null && !"01/01/1951".equals(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("LockAmtStartDate"))))) {
                    payrollHandlerAction.setLockAmtStartDate(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("LockAmtStartDate"))));
                } else {
                    payrollHandlerAction.setLockAmtStartDate("");
                }
                payrollHandlerAction.setPayRollComments(resultSet.getString("PayRollComments"));
                payrollHandlerAction.setDiffPF(resultSet.getDouble("DiffPf"));
//IsSixMonthsLock
                if (resultSet.getInt("IsSixMonthsLock") == 1) {
                    payrollHandlerAction.setIsSixMonthsLock(true);
                } else {
                    payrollHandlerAction.setIsSixMonthsLock(false);
                }

                if (resultSet.getInt("FixedSalaryFlag") == 1) {
                    payrollHandlerAction.setIsFixedSalary(true);
                } else {
                    payrollHandlerAction.setIsFixedSalary(false);
                }
                if (resultSet.getInt("NoSalary") == 1) {
                    payrollHandlerAction.setNoSalary(true);
                } else {
                    payrollHandlerAction.setNoSalary(false);
                }
                if(resultSet.getString("PracticeId")!=null && !"".equals(resultSet.getString("PracticeId"))){
                payrollHandlerAction.setPracticeId(resultSet.getString("PracticeId"));
                }else{
                     payrollHandlerAction.setPracticeId("");
                }
                if(resultSet.getString("NewGrossPay")!=null && !"".equals(resultSet.getString("NewGrossPay"))){
                payrollHandlerAction.setNewGrossPay(resultSet.getDouble("NewGrossPay"));
                }else{
                     payrollHandlerAction.setNewGrossPay(0.00);
                }
 String employeeLeavesWeekends = DataSourceDataProvider.getInstance().getEmployeeLeavesWeekends(payrollHandlerAction.getPayRollId(),payrollHandlerAction.getYear());
                int count=0; 
        int month = 0;
        Calendar calendar=Calendar.getInstance(); 
        for(month=1;month <= 12;month++){
        calendar.set(Integer.parseInt(payrollHandlerAction.getYear()), month,1); 
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
 
        for(int i=1;i<=days;i++) 
        { 
         
                calendar.set(Integer.parseInt(payrollHandlerAction.getYear()), month, i); 
                int day=calendar.get(Calendar.DAY_OF_WEEK); 
              //   System.out.println("day"+day);
                if(day==1 || day==7) 
                count++; 
         
        }
        }
        payrollHandlerAction.setNoOfWeekendDays(count);
                    payrollHandlerAction.setNoOfLeaves(employeeLeavesWeekends.split("#")[0]);
                    payrollHandlerAction.setAvailWeekends(String.valueOf(count - Integer.parseInt(employeeLeavesWeekends.split("#")[1])));
                    payrollHandlerAction.setAvailLeaves(employeeLeavesWeekends.split("#")[2]);
                    payrollHandlerAction.setAvailHolidays(String.valueOf(9-Integer.parseInt(employeeLeavesWeekends.split("#")[3])));
                    
          
       
          
            }  
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        //System.out.println("taskMaxId-->"+taskMaxId);
        return null;
    }

    public int insertIntoWages(PayrollHandlerAction payrollHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {

        Connection connection = null;
        Connection connection1 = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int updatedRows = 0;
        String queryString = "";
        String queryString1 = "SELECT EmpNo FROM tblEmployee WHERE Country = 'India' AND CurStatus='Active'";
        try {
            connection1 = ConnectionProvider.getInstance().getConnection();
            statement = connection1.createStatement();
            resultSet = statement.executeQuery(queryString1);
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt("EmpNo"));
                connection = ConnectionProvider.getInstance().getConnection();
                queryString = "INSERT INTO `mirage`.`tblEmpWages` "
                        + "(`PayRoll_Id`, `PaymentTypeId`, `GrossPay`, `BasicPay1`,`DA`, `HRA`, `TA`, `RA`, `Life`, `Health`, `AttAllowance`, "
                        + "`Entertainment`, `KidsEducation`,`VehcleAllowance`, `LongTermBonus`, `Employer_PF`, `Employee_PF`,`CCA1`, `SplAllowance`, "
                        + "`MiscPay`, `VariablePay`, Ded_Professional_Tax,Ded_Others,YTD_Gross,YTD_Savings1,YTD_Savings2,EmpId) "
                        + "SELECT `PayRollId`, `PaymentTypeId`,`GrossPay`, `BasicPay`, `DA`, `HRA`, `TA`, `RA`, `Life`, `Health`,`AttAllowance`, "
                        + "`Entertainment`, `KidsEducation`, `VehcleAllowance`, `LongTermbonus`, `Employer_PF`, `Employee_PF`, `CCA`, `SplAllowances`, "
                        + "`MiscPay`, `VariablePay`, `Ded_Professional_Tax`, `Ded_Others`, `Prev_Employer_YTD_Gross`, `EmpSavings1`, `EmpSavings2`,EmpId	FROM tblEmpPayRoll WHERE PayRollId = " + resultSet.getInt("EmpNo");
                preparedStatement = connection.prepareStatement(queryString);
                updatedRows = preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return updatedRows;


    }

    public int insertIntoWagesReference(PayrollHandlerAction payrollHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int updatedRows = 0;
        String queryString = "";

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "INSERT INTO `mirage`.`tblWagesReference` (`Month`, `year`,CreatedDate, `CreatedBy`) VALUES(?,?,,?,?);";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, payrollHandlerAction.getMonthOverlay());
            preparedStatement.setInt(2, payrollHandlerAction.getYearOverlay());
            preparedStatement.setTimestamp(3, DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(4, httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());

            updatedRows = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return updatedRows;
    }

    public String setEmployeeWageDetailsForUpdate(int payrollId, PayrollHandlerAction payrollHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = "	SELECT 	tblEmpWages.PaymentTypeId,tblEmpWages. PayPeriodStartDate,tblEmpWages. Freeze_Payroll,tblEmpWages. PayrollDate,tblEmpWages.TDS,tblEmpWages."
                + "NetPaid,tblEmpWages. GrossPay,tblEmpWages. DaysInMonth,tblEmpWages. DaysWorked,tblEmpWages. DaysProject,tblEmpWages. DaysVacation,tblEmpWages. LeavesAvailable,tblEmpWages."
                + "DaysHolidays,tblEmpWages. Daysweekends,tblEmpWages. Ded_Employee_PF,tblEmpWages. Ded_Professional_Tax,tblEmpWages. Ded_Income_Tax,tblEmpWages. Ded_Corporate_Loan,tblEmpWages. Ded_Life,tblEmpWages. Ded_Health,tblEmpWages. "
                + "Ded_Others,tblEmpWages. BasicPay1,tblEmpWages. DA,tblEmpWages. HRA,tblEmpWages. TA,tblEmpWages. RA,tblEmpWages. Life,tblEmpWages. Health,tblEmpWages. AttAllowance,tblEmpWages. MaidServices,tblEmpWages. Entertainment,tblEmpWages. KidsEducation,tblEmpWages. VehcleAllowance,tblEmpWages. LongTermBonus,tblEmpWages."
                + "Employer_PF,tblEmpWages. 	Employee_PF,tblEmpWages. 	CCA1,tblEmpWages. SplAllowance,tblEmpWages. MiscPay,tblEmpWages. VariablePay,tblEmpWages. Earned_Basic,tblEmpWages. Earned_DA,tblEmpWages. Earned_HRA,tblEmpWages. Earned_TA,tblEmpWages. Earned_RA,tblEmpWages. Earned_Life,tblEmpWages."
                + "Earned_Health,tblEmpWages. Earned_CCA,tblEmpWages. Earned_ProjectPay,tblEmpWages. Earned_Allowance,tblEmpWages. Ded_TDS_Bonus,tblEmpWages."
                + "TaxableIncome,tblEmpWages. Earned_Food,tblEmpWages. Earned_Laundry,tblEmpWages. Earned_MaidServices,tblEmpWages. Earned_Entertainment,tblEmpWages. Earned_KidsEducation,tblEmpWages. Earned_VehicleAllowance,tblEmpWages."
                + "Earned_LongTermBonus,tblEmpWages. Earned_MiscPay,tblEmpWages. Earned_Employeer_Pf,tblEmpWages. Earned_SplAllowance,tblEmpWages. Earned_EmployeePF,tblEmpWages."
                + "YTD_Gross,tblEmpWages. YTD_LongTerm,tblEmpWages. YTD_PF,tblEmpWages. YTD_PTax,tblEmpWages. YTD_Life,tblEmpWages. YTD_TA,tblEmpWages. YTD_RA,tblEmpWages. Ytd_ExpTaxFree,tblEmpWages. YTD_Others,tblEmpWages. YTD_Projectpay,tblEmpWages."
                + "YTD_Savings1,tblEmpWages. YTD_Savings2,tblEmpWages. YTD_TaxableIncome,tblEmpWages. YTD_Comissions,tblEmpWages. YTD_TDS_Salary,tblEmpWages. YTD_TDS_Comissions,tblEmpWages. YTD_TDS_Collected,tblEmpWages."
                + "Tax_Liability,tblEmpWages. Tax_Liability_Bonus,tblEmpWages. Diff_tax_salary,tblEmpWages. Diff_tax_bonus,tblEmpWages. FreezeAppliedBy,tblEmpWages. FreezeAppliedDate,tblEmpWages. FreezeModifiedBy,tblEmpWages. FreezeModifiedDate,tblEmpWages."
                + "CreatedBy,tblEmpWages. CreateDate,tblEmpWages. ModifiedBy,tblEmpWages. ModifiedDate,tblEmpWages. EmpId,tblEmpWages. DaysVacationfromHubble,tblEmpWages. DaysVacationFromBiometric,tblEmpWages.Bank_Name,tblEmpWages.BankAccount,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS empName,tblEmpWages.TitleId AS Title,tblEmpWages.ProjectPay,tblEmpWages.IsBlock,tblEmployee.ClassificationId,tblEmployee.ShiftId, "
                + "tblEmpWages.Earned_Gross_Pay,tblEmpWages.Earned_NewGrossPay,tblEmpWages.Earned_Net_Paid,tblEmpWages.Earned_Variable_Pay,tblEmpWages.ProjectEndDate as ProjectEndDate,tblEmpWages.TotalCost as TotalCost, "
                + " DoRepaymentFlag,RepaymentGross,RepaymentComments,RepaymentNetPaid,RepaymentVarriablePay,Earned_EmpOthers,Earned_Bonus,tblEmpWages.EmployeeESI as EmployeeESI,tblEmpWages.EmployerESI as  EmployerESI,Earned_Employee_ESI,Earned_Employer_ESI,ActualTDS,IncomeTax,tblEmpWages.NewGrossPay1,tblEmpWages.ReleaseDate "
                + " FROM mirage.tblEmpWages LEFT OUTER JOIN tblEmployee ON(tblEmployee.Id =  tblEmpWages.EmpId)"
                + "  WHERE tblEmpWages.PayRoll_Id = " + payrollId + " and tblEmpWages.Wag_Id=" + payrollHandlerAction.getWageId();
        Statement statement = null;
        int updatedRows = 0;
        int isSuccess = 0;
        //System.out.println("queryString-->" + queryString);
        String result = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                payrollHandlerAction.setEmployeeName(resultSet.getString("empName"));
                payrollHandlerAction.setTitleId(resultSet.getString("Title"));
                payrollHandlerAction.setBankName(resultSet.getString("Bank_Name"));
                payrollHandlerAction.setBankAccountNo(resultSet.getString("BankAccount"));

                if (resultSet.getInt("Freeze_Payroll") == 1) {
                    payrollHandlerAction.setFreezePayroll(true);
                } else {
                    payrollHandlerAction.setFreezePayroll(false);
                }

                payrollHandlerAction.setBasic(resultSet.getDouble("BasicPay1"));
                payrollHandlerAction.setDa(resultSet.getDouble("DA"));
                payrollHandlerAction.setHra(resultSet.getDouble("HRA"));
                payrollHandlerAction.setTa(resultSet.getDouble("TA"));
                payrollHandlerAction.setRa(resultSet.getDouble("RA"));
                payrollHandlerAction.setEntertainment(resultSet.getDouble("Entertainment"));
                payrollHandlerAction.setKidsEducation(resultSet.getDouble("KidsEducation"));
                payrollHandlerAction.setVehicleAllowance(resultSet.getDouble("VehcleAllowance"));
                payrollHandlerAction.setCca(resultSet.getDouble("CCA1"));
                payrollHandlerAction.setMiscPay(resultSet.getDouble("MiscPay"));
                payrollHandlerAction.setSplAllowance(resultSet.getDouble("SplAllowance"));
                payrollHandlerAction.setLongTermBonus(resultSet.getDouble("LongTermBonus"));
                payrollHandlerAction.setProjectPay(resultSet.getDouble("ProjectPay"));
                payrollHandlerAction.setAttendanceAllow(resultSet.getDouble("AttAllowance"));
                payrollHandlerAction.setPaymentType(String.valueOf(resultSet.getInt("PaymentTypeId")));
                payrollHandlerAction.setEmployerPf(resultSet.getDouble("Employer_PF"));
                payrollHandlerAction.setEmployeePfPayRollDetails(resultSet.getDouble("Employee_PF"));
                payrollHandlerAction.setLife(resultSet.getDouble("Life"));
                payrollHandlerAction.setHealth(resultSet.getDouble("Health"));
                payrollHandlerAction.setProfessionalTax(resultSet.getDouble("Ded_Professional_Tax"));
                payrollHandlerAction.setOtherDeductions(resultSet.getDouble("Ded_Others"));
                payrollHandlerAction.setGrossPay(resultSet.getDouble("GrossPay"));
                payrollHandlerAction.setVariablePay(resultSet.getDouble("VariablePay"));
                payrollHandlerAction.setDaysInMonth(String.valueOf(resultSet.getInt("DaysInMonth")));
                payrollHandlerAction.setDaysWorked(String.valueOf(resultSet.getInt("DaysWorked")));
                payrollHandlerAction.setDaysProject(String.valueOf(resultSet.getInt("DaysProject")));
                payrollHandlerAction.setDaysVacation(String.valueOf(resultSet.getInt("DaysVacation")));
                payrollHandlerAction.setDaysWeekends(String.valueOf(resultSet.getInt("Daysweekends")));
                payrollHandlerAction.setDaysHolidays(String.valueOf(resultSet.getInt("DaysHolidays")));
                payrollHandlerAction.setPayrollDate(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("PayrollDate"))));
                payrollHandlerAction.setPayPeriodStartDate(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("PayPeriodStartDate"))));
                payrollHandlerAction.setEmployerId(String.valueOf(payrollId));
                payrollHandlerAction.setDedEmpPf(resultSet.getDouble("Ded_Employee_PF"));
                payrollHandlerAction.setTds(resultSet.getDouble("TDS"));
                payrollHandlerAction.setDedProfessionalTax(resultSet.getDouble("Ded_Professional_Tax"));
                payrollHandlerAction.setDedIncomeTax(resultSet.getDouble("Ded_Income_Tax"));
                payrollHandlerAction.setDedCorporateLoan(resultSet.getDouble("Ded_Corporate_Loan"));
                payrollHandlerAction.setDedLife(resultSet.getDouble("Ded_Life"));
                payrollHandlerAction.setDedHealth(resultSet.getDouble("Ded_Health"));
                payrollHandlerAction.setDedOthers(resultSet.getDouble("Ded_Others"));
                payrollHandlerAction.setEarnedBasic(resultSet.getDouble("Earned_Basic"));
                payrollHandlerAction.setEarnedDa(resultSet.getDouble("Earned_DA"));
                payrollHandlerAction.setEarnedHra(resultSet.getDouble("Earned_HRA"));
                payrollHandlerAction.setEarnedTa(resultSet.getDouble("Earned_TA"));
                payrollHandlerAction.setEarnedRa(resultSet.getDouble("Earned_RA"));
                payrollHandlerAction.setEarnedLife(resultSet.getDouble("Earned_Life"));
                payrollHandlerAction.setEarnedHealth(resultSet.getDouble("Earned_Health"));
                payrollHandlerAction.setEarnedCCa(resultSet.getDouble("Earned_CCA"));
                payrollHandlerAction.setEarnedProjectPay(resultSet.getDouble("Earned_ProjectPay"));
                payrollHandlerAction.setEarnedattallowance(resultSet.getDouble("Earned_Allowance"));
                payrollHandlerAction.setEarnedFood(resultSet.getDouble("Earned_Food"));
                payrollHandlerAction.setEarnedLaundary(resultSet.getDouble("Earned_Laundry"));
                payrollHandlerAction.setMaidServices(resultSet.getDouble("MaidServices"));
                payrollHandlerAction.setEarnedMaidServices(resultSet.getDouble("Earned_MaidServices"));
                payrollHandlerAction.setEarnedEntertainment(resultSet.getDouble("Earned_Entertainment"));
                payrollHandlerAction.setEarnedKidsEducation(resultSet.getDouble("Earned_KidsEducation"));
                payrollHandlerAction.setEarnedVehicleAllowance(resultSet.getDouble("Earned_VehicleAllowance"));
                payrollHandlerAction.setEarnedLongTermBonus(resultSet.getDouble("Earned_LongTermBonus"));
                payrollHandlerAction.setEarnedMiscPay(resultSet.getDouble("Earned_MiscPay"));
                payrollHandlerAction.setEarnedEmployerPf(resultSet.getDouble("Earned_Employeer_Pf"));
                payrollHandlerAction.setEarnedsplallowance(resultSet.getDouble("Earned_SplAllowance"));
                payrollHandlerAction.setTdsDeduction(resultSet.getDouble("Ded_TDS_Bonus"));
                //    System.out.println("tds deduction-->"+payrollHandlerAction.getTdsDeduction());
                payrollHandlerAction.setTaxableIncome(resultSet.getDouble("TaxableIncome"));
                payrollHandlerAction.setEmployeePf(resultSet.getDouble("Earned_EmployeePF"));
                payrollHandlerAction.setYtdGross(resultSet.getDouble("YTD_Gross"));
                payrollHandlerAction.setYtdLongterm(resultSet.getDouble("YTD_LongTerm"));
                payrollHandlerAction.setYtdPf(resultSet.getDouble("YTD_PF"));
                payrollHandlerAction.setYtdProffTax(resultSet.getDouble("YTD_PTax"));
                payrollHandlerAction.setYtdLifeInsurance(resultSet.getDouble("YTD_Life"));
                payrollHandlerAction.setYtdTa(resultSet.getDouble("YTD_TA"));
                payrollHandlerAction.setYtdRa(resultSet.getDouble("YTD_RA"));
                payrollHandlerAction.setYtdExpTaxFree(resultSet.getDouble("Ytd_ExpTaxFree"));
                payrollHandlerAction.setYtdOthersMisc(resultSet.getDouble("YTD_Others"));
                payrollHandlerAction.setYtdProjectPay(resultSet.getDouble("YTD_Projectpay"));
                payrollHandlerAction.setYtdSavings1Reported(resultSet.getDouble("YTD_Savings1"));
                payrollHandlerAction.setYtdSavings2Reported(resultSet.getDouble("YTD_Savings2"));
                payrollHandlerAction.setYtdTaxableSalary(resultSet.getDouble("YTD_TaxableIncome"));
                payrollHandlerAction.setYtdTaxableCommission(resultSet.getDouble("YTD_Comissions"));
                payrollHandlerAction.setYtdTDSonSalary(resultSet.getDouble("YTD_TDS_Salary"));
                payrollHandlerAction.setYtdTDSOnCommm(resultSet.getDouble("YTD_TDS_Comissions"));
                payrollHandlerAction.setYtdTDSCollected(resultSet.getDouble("YTD_TDS_Collected"));
                payrollHandlerAction.setYtdTDSLiabilitiesSalary(resultSet.getDouble("Tax_Liability"));
                payrollHandlerAction.setYtdTDSLiabilitiesBonus(resultSet.getDouble("Tax_Liability_Bonus"));
                payrollHandlerAction.setDiffTDSLiabilitiesSalary(resultSet.getDouble("Diff_tax_salary"));
                payrollHandlerAction.setDiffTDSLiabilitiesBonus(resultSet.getDouble("Diff_tax_bonus"));
                payrollHandlerAction.setVactionsAvailable(String.valueOf(resultSet.getInt("LeavesAvailable")));
                payrollHandlerAction.setEmployeePfActualDetails(resultSet.getDouble("Earned_EmployeePF"));
                payrollHandlerAction.setNetPaid(resultSet.getDouble("NetPaid"));
                payrollHandlerAction.setClassificationId(String.valueOf(resultSet.getInt("ClassificationId")));
                payrollHandlerAction.setShiftId(String.valueOf(resultSet.getInt("ShiftId")));
                // System.out.println("payrollHandlerAction.getEmpId()->"+payrollHandlerAction.getEmpId());
                payrollHandlerAction.setPayRunId(DataSourceDataProvider.getInstance().getEmpNoByEmpId(Integer.parseInt(payrollHandlerAction.getEmpId())).split("\\^")[0]);

                //  System.out.println("payrollHandlerAction.setPayRunId()->"+payrollHandlerAction.getPayRunId());
                if (resultSet.getInt("IsBlock") == 1) {
                    payrollHandlerAction.setIsBlock(true);
                } else {
                    payrollHandlerAction.setIsBlock(false);
                }
                payrollHandlerAction.setEarnedGrossPay(resultSet.getDouble("Earned_Gross_Pay"));
                 payrollHandlerAction.setEarnedNewGrossPay(resultSet.getDouble("Earned_NewGrossPay"));
               
                payrollHandlerAction.setEarnedVariablePay(resultSet.getDouble("Earned_Variable_Pay"));
                payrollHandlerAction.setEarnedNetPaid(resultSet.getDouble("Earned_Net_Paid"));
                payrollHandlerAction.setActualTds(resultSet.getDouble("ActualTDS"));
                payrollHandlerAction.setIncomeTax_TE(resultSet.getDouble("IncomeTax"));
                if (!"01/01/1951".equals(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("ProjectEndDate"))))) {
                    payrollHandlerAction.setProjectEndDate(DateUtility.getInstance().convertToviewFormat(DateUtility.getInstance().convertDateToStringPayroll(resultSet.getDate("ProjectEndDate"))));
                } else {
                    payrollHandlerAction.setProjectEndDate("");
                }
                /*  payrollHandlerAction.setLifeInsurancePremium(resultSet.getDouble("InvLifePremium_80C"));
                 payrollHandlerAction.setHousingLoanRepay(resultSet.getDouble("InvHouseLoanRepayment_80C"));
                 payrollHandlerAction.setNscTds(resultSet.getDouble("InvNSC_80C"));
                 payrollHandlerAction.setElss(resultSet.getDouble("InvELSS_80C"));
                 payrollHandlerAction.setPostOfficeTerm(resultSet.getDouble("InvPostOfficeTermDeposit_80C"));
                 payrollHandlerAction.setBankDepositTax(resultSet.getDouble("InvBankDeposits_80C"));
                 payrollHandlerAction.setOthersTDS(resultSet.getDouble("InvOthers_80C"));
                 payrollHandlerAction.setContributionToPf(resultSet.getDouble("InvContrToPF_80CCC"));
                 payrollHandlerAction.setNpsEmployeeContr(resultSet.getDouble("Inv80CCDNPSEmpCont_80CCC"));
                 payrollHandlerAction.setTotalTds(resultSet.getDouble("InvTotalFrm80CAnd80CCC"));
                 payrollHandlerAction.setTotalTdsDeductable(resultSet.getDouble("DedFrom80CAnd80CCC"));
                 payrollHandlerAction.setInterstOnBorrowed(resultSet.getDouble("InvIntrOnBorrCap_80CCC"));
                 payrollHandlerAction.setInterstOnBorrowedDeductable(resultSet.getDouble("DedIntrOnBorrCap_80CCC"));
                 payrollHandlerAction.setInsuranceForParents(resultSet.getDouble("InvIntrOnParentsLIC_80D"));
                 payrollHandlerAction.setInsuranceForParentsDeduc(resultSet.getDouble("DedIntrOnParentsLIC_80D"));
                 payrollHandlerAction.setInsuranceOthers(resultSet.getDouble("InvIntrOnSpouseLIC_80D"));
                 payrollHandlerAction.setInsuranceOthersDeduc(resultSet.getDouble("DedIntrOnSpouseLIC_80D"));
                 payrollHandlerAction.setInterstOnEdu(resultSet.getDouble("DedIntrOnEdu_80E"));
                 payrollHandlerAction.setInterstOnHrAssumptionsInv(resultSet.getDouble("InvHRExemptions_80E"));
                 payrollHandlerAction.setInterstOnHrAssumptions(resultSet.getDouble("DedHRExemptions_80E"));
                 payrollHandlerAction.setExpectedYearlyCost(resultSet.getDouble("TotalExpGross"));
                 payrollHandlerAction.setLicFromSal(resultSet.getDouble("InvLICFromSal_80C"));
                 payrollHandlerAction.setTutionFee(resultSet.getDouble("InvTutionFee_80C"));
                 //  System.out.println("ppf------>" + resultSet.getDouble("InvPPFContribution_80C"));
                 payrollHandlerAction.setPpfContribution(resultSet.getDouble("InvPPFContribution_80C"));*/
                if (resultSet.getInt("DoRepaymentFlag") == 1) {
                    payrollHandlerAction.setDoRepaymentFlagVal(true);
                } else {
                    payrollHandlerAction.setDoRepaymentFlagVal(false);
                }
                if (resultSet.getString("RepaymentComments") == null) {
                    payrollHandlerAction.setRepaymentComments(" ");
                } else {
                    payrollHandlerAction.setRepaymentComments(resultSet.getString("RepaymentComments"));
                }
                payrollHandlerAction.setRepaymentGross(resultSet.getDouble("RepaymentGross"));
                payrollHandlerAction.setRepaymentNet(resultSet.getDouble("RepaymentNetPaid"));
                payrollHandlerAction.setRepaymentVariablePay(resultSet.getDouble("RepaymentVarriablePay"));
                payrollHandlerAction.setBonusCommission(resultSet.getDouble("Earned_Bonus"));
                payrollHandlerAction.setOtherAdditions(resultSet.getDouble("Earned_EmpOthers"));
                payrollHandlerAction.setLeavesApplied(String.valueOf(resultSet.getInt("DaysVacationfromHubble")));
                payrollHandlerAction.setEmployeresi(resultSet.getDouble("EmployerESI"));
                payrollHandlerAction.setEmployeeesi(resultSet.getDouble("EmployeeESI"));
                //,
                payrollHandlerAction.setEarnedEmployeeesi(resultSet.getDouble("Earned_Employee_ESI"));
                payrollHandlerAction.setEarnedEmployeresi(resultSet.getDouble("Earned_Employer_ESI"));
                payrollHandlerAction.setNewGrossPay(resultSet.getDouble("NewGrossPay1"));
                if(resultSet.getString("ReleaseDate")!=null && !"".equals(resultSet.getString("ReleaseDate"))){
                payrollHandlerAction.setReleasedDate(DateUtility.getInstance().convertToviewFormat(resultSet.getString("ReleaseDate")));
                }else{
                    payrollHandlerAction.setReleasedDate("");
                }
                // payrollHandlerAction.setOtherDeductions(resultSet.getDouble("Earned_EmpOthers"));
                //   payrollHandlerAction.setExpectedYearlyCost(resultSet.getDouble("TotalCost")*12);
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        //System.out.println("taskMaxId-->"+taskMaxId);
        return null;
    }

//    public boolean addDueSettlement(PayrollHandlerAction payrollHandlerAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException {
//     System.out.println("addDuesSettlement method1");
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        Statement statement = null;
//
//         boolean isUpdated = false;
//        String queryString = "";
//        String query = "";
//        String maxDate= "";
//          try {
//              
//              connection = ConnectionProvider.getInstance().getConnection();
//          String pattern = "MM/dd/yyyy";
//        SimpleDateFormat format = new SimpleDateFormat(pattern);
//        java.util.Date maxiDate =  DataSourceDataProvider.getInstance().getMaxDateForNoDuesSettlement(payrollHandlerAction.getEmpId());
//        java.util.Date fromDate = format.parse(payrollHandlerAction.getFromDate());
//    System.out.println("dates--------->"+maxiDate.compareTo(format.parse("01/01/1951")));
// //if(maxiDate.compareTo(format.parse("01/01/1951"))!=0){
//        
//         if(fromDate.compareTo(maxiDate)>0){
//             System.out.println("Today Date is Greater than my date"); 
//        
//
//        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
//        //if(roleName.equalsIgnoreCase("Employee"))
//             
//                 queryString = "INSERT INTO tblEmpDuesDetails (EmpId,FromDate,ToDate,isAcknoweldged,isSetteledAllDues,isDues,DuesAmount,CreatedBy,Comments) values (?,?,?,?,?,?,?,?,?)";
//                 preparedStatement = connection.prepareStatement(queryString);
//            //  preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());
//           
//            preparedStatement.setInt(1, Integer.parseInt(payrollHandlerAction.getEmpId()));
//            preparedStatement.setDate(2, DateUtility.getInstance().getMysqlDate(payrollHandlerAction.getFromDate()));
//            preparedStatement.setDate(3, DateUtility.getInstance().getMysqlDate(payrollHandlerAction.getToDate()));
//            if(payrollHandlerAction.isRelease()){
//                preparedStatement.setInt(4, 1);
//            }
//            else{
//                preparedStatement.setInt(4, 0);
//            }
//            if(payrollHandlerAction.isCommissions()){
//                preparedStatement.setInt(5, 1);
//            }
//            else{
//                preparedStatement.setInt(5, 0);
//            }
//            if(payrollHandlerAction.isSettled()){
//                preparedStatement.setInt(6, 1);
//            }
//            else{
//                preparedStatement.setInt(6, 0);
//            }
//           
//            preparedStatement.setDouble(7, payrollHandlerAction.getDueAmount());
//            preparedStatement.setString(8, payrollHandlerAction.getCreatedBy());
//            preparedStatement.setString(9, payrollHandlerAction.getComments());
//          
//            
//             
//            isUpdated = preparedStatement.execute();
//            System.out.println("isUpdated==="+isUpdated);
//            httpServletRequest.getSession(false).setAttribute("resultMessageforNoDue","<font style='color:green;font-size:15px;'>Record inserted Successfully</font>");
//             }
//         else 
//              httpServletRequest.getSession(false).setAttribute("resultMessageforNoDue","<font style='color:red;font-size:15px;'>you can insert new record after "+ DateUtility.getInstance().convertDateToMySql(maxiDate)+" only.</font>");
// 
///* else
// {
//       httpServletRequest.getSession(false).setAttribute("resultMessageforNoDue","<font style='color:red;font-size:15px;'>No record exist.</font>");
// }*/
// 
//        } catch (ParseException ex) {
//          ex.printStackTrace();
//        } catch (SQLException se) {
//            se.printStackTrace();
//            throw new ServiceLocatorException(se);
//        } finally {
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                    preparedStatement = null;
//                }
//                if (connection != null) {
//                    connection.close();
//                    connection = null;
//                }
//            } catch (SQLException se) {
//                throw new ServiceLocatorException(se);
//            }
//        }
//
//        return isUpdated;
//
//    }
    public int payrollAuthenticationLogin(int empId, String password) throws ServiceLocatorException {
     //  System.out.println("payrollAuthenticationRegister"+empId);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        String queryString = "";
        String dbPassword = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT Password FROM tblPayrollAuthCheck WHERE empId=? and  Status='Active'";
           
            password = PasswordUtility.md5EncriptionPassword(password);
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, empId);
            //preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                dbPassword = resultSet.getString("Password");
            }
            if (password.equals(dbPassword)) {
                count = 1;
            }
          //   System.out.println(count+"query"+queryString);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }



            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return count;


    }

    public String payrollAuthenticationCanel(int empId) throws ServiceLocatorException {
        // System.out.println("payrollAuthenticationRegister");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String dbPassword = "";
        String result = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT tblScreens.ScreenAction as Action FROM tblScreens LEFT OUTER JOIN tblRoleScreens  ON (tblRoleScreens.ScreenId = tblScreens.Id) "
                    + "LEFT OUTER JOIN tblEmpRoles ON (tblEmpRoles.RoleId=tblRoleScreens.RoleId) WHERE tblEmpRoles.EmpId=" + empId + " AND tblEmpRoles.PrimaryFlag=1 AND tblRoleScreens.PrimaryFlag = 1";
            preparedStatement = connection.prepareStatement(queryString);

            //  System.out.println("queryString--->"+queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString("Action");
            }
            //  System.out.println("result Action--"+result);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }



            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;


    }

    public int updatePayrollPassword(int empId, String oldPassword, String newPassword) throws ServiceLocatorException {
        int updatedRows = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String dbpassword = "";
        queryString = "SELECT Password FROM tblPayrollAuthCheck WHERE empId=" + empId + " and  Status='Active'";
        // String queryString = "SELECT LoginId,Password FROM tblEmployee WHERE LoginId='"+passwordAction.getLoginId()+"'";

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                dbpassword = resultSet.getString("Password");
            }


            oldPassword = PasswordUtility.md5EncriptionPassword(oldPassword);

            if (statement != null) {
                statement.close();
                statement = null;
            }
            /*here checking weather passwor exist or not ,if it exit the update will be done.*/
            if (oldPassword.equalsIgnoreCase(dbpassword)) {
                String encryptPass = PasswordUtility.md5EncriptionPassword(newPassword);
                //queryString = "UPDATE tblEmployee SET Password='"+encryptPass+"' WHERE LoginId='"+passwordAction.getLoginId()+"'";
                queryString = "UPDATE tblPayrollAuthCheck SET PASSWORD ='" + encryptPass + "'  WHERE EmpId = " + empId;
                statement = connection.createStatement();
                updatedRows = statement.executeUpdate(queryString);
            }


        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return updatedRows;

    }

    @Override
    public int payrollAuthenticationRegister(int empId, String password) throws ServiceLocatorException {
        //  System.out.println("payrollAuthenticationRegister");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int insertRows = 0;
        String queryString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            password = PasswordUtility.md5EncriptionPassword(password);
            queryString = "insert into tblPayrollAuthCheck(EmpId,Password,Status) values(?,?,?)";

            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, empId);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, "Active");
            insertRows = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }



            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return insertRows;


    }
    public String runEmpWagesForCurrentMonth(int year, int month, PayrollHandlerAction payrollHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;

        CallableStatement callableStatement = null;

        String resultMessage = "";
        String queryString1 = "";
        String queryString2 = "";
        int insertedRows = 0;
        int updatedrows = 0;
        int count = 0;

        try {

            boolean exists = DataSourceDataProvider.getInstance().checkForPayrollDateForRunWages(month, year,payrollHandlerAction.getOrgId());
            boolean checkLoadLeavesExists = DataSourceDataProvider.getInstance().checkLoadLeaves(month, year,payrollHandlerAction.getOrgId());
          //  System.out.println("checkLoadLeavesExists" + checkLoadLeavesExists);
           // System.out.println("exists" + exists);
            if (checkLoadLeavesExists) {
                if (!exists) {
                    connection = ConnectionProvider.getInstance().getConnection();
                    callableStatement = connection.prepareCall("{call spAddEmpWages1(?,?,?,?,?,?,?,?,?,?,?,?) }");

                    callableStatement.setInt(1, month);
                    callableStatement.setInt(2, year);
                    callableStatement.setInt(3, payrollHandlerAction.getNoOfDays());
                    callableStatement.setInt(4, payrollHandlerAction.getNoOfWeekendDays());
                    //  callableStatement.setInt(5, payrollAjaxHandlerAction.getNoOfHolidays());
                    callableStatement.setInt(5, payrollHandlerAction.getNoOfWorkingDays());
                    callableStatement.setString(6, payrollHandlerAction.getCreatedBy());
                    

                //    System.out.println("1month----" + month);
               //     System.out.println("2year----" + year);
               //     System.out.println("3payrollAjaxHandlerAction.getNoOfDays()----" + payrollAjaxHandlerAction.getNoOfDays());
                //    System.out.println("4payrollAjaxHandlerAction.getNoOfWeekedDays()----" + payrollAjaxHandlerAction.getNoOfWeekedDays());
                //    System.out.println("5payrollAjaxHandlerAction.getNoOfWeekedDays()----" + payrollAjaxHandlerAction.getWorkingDays());
                //    System.out.println("6payrollAjaxHandlerAction.getCreatedBy()----" + payrollAjaxHandlerAction.getCreatedBy());
                    int yearForWageRun = year;
                    int monthForWageRun = month;
                    String sdate = "";
                    String edate = "";

                    if (monthForWageRun < 4) {
                        sdate = (yearForWageRun - 1) + "-04-01";
                    //    System.out.println("sdate" + sdate);
                        edate = yearForWageRun + "-0" + (monthForWageRun + 1) + "-01";
                     //   System.out.println("edate" + edate);
                    } else {
                        sdate = (yearForWageRun) + "-04-01";
                     //   System.out.println("sdate" + sdate);
                        if (monthForWageRun <= 9) {
                            edate = (yearForWageRun) + "-0" + (monthForWageRun + 1) + "-01";
                       //     System.out.println("edate" + edate);
                        } else {

                            if (monthForWageRun == 12) {
                                edate = (yearForWageRun + 1) + "-" + (monthForWageRun - 11) + "-01";
                            } else {
                                edate = (yearForWageRun) + "-" + (monthForWageRun + 1) + "-01";
                            }
                        }
                    }

                    int earned_monthDiff = DataSourceDataProvider.getInstance().getMonthsBetweenDates(sdate, edate);
                    if (monthForWageRun <= 9) {
                        sdate = yearForWageRun + "-0" + (monthForWageRun) + "-01";// 2015-02-01
                      //  System.out.println("wagerunsdate" + sdate);
                    } else {
                        sdate = yearForWageRun + "-" + (monthForWageRun) + "-01";// 
                      //  System.out.println("wagerunsdate" + sdate);
                    }
                    if (monthForWageRun <= 03) {
                        edate = yearForWageRun + "-03-31"; //2015-03-01
                     //   System.out.println("wagerunsdate" + edate);
                    } else {
                        edate = (yearForWageRun + 1) + "-03-31";// 2016-03-01
                     //   System.out.println("wagerunsdate" + edate);
                    }
                    int projected_monthDiff = DataSourceDataProvider.getInstance().getMonthsBetweenDates(sdate, edate);
                   // System.out.println("EarnedMonths -->" + earned_monthDiff);
                   // System.out.println("ProjectedMonths -->" + projected_monthDiff);
                    callableStatement.setInt(7, projected_monthDiff);
                    callableStatement.setInt(8, earned_monthDiff);
                    callableStatement.setString(9, com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(payrollHandlerAction.getPaymentDate()));
                     callableStatement.setInt(10,payrollHandlerAction.getOrgId());
                    callableStatement.registerOutParameter(11, Types.VARCHAR);

                    insertedRows = callableStatement.executeUpdate();

                    resultMessage = "<font style='color:green;'>" + callableStatement.getString(11) + "<font>";
                    //resultMessage = "<font style='color:red;'>Can Proceed</font>";
                } else {
                    resultMessage = "<font style='color:red;'>Running of wages already done for this month</font>";
                }


            } else {
                resultMessage = "<font style='color:red;'>Loading of leaves is not done for this month</font>";
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            resultMessage = "<font style='color:red;'>Please try again later</font>";
            DataSourceDataProvider.getInstance().deleteWagesIfExceptionOccurs();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (prestatement != null) {
                    prestatement.close();
                    prestatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultMessage;

    }
    
    
    
    
}
