/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.ajax;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;
import com.mss.mirage.util.MailManager;
import com.mss.mirage.util.PasswordUtility;
import com.mss.mirage.util.Properties;
import com.mss.mirage.util.RandomPasswordGenerator;
import com.mss.mirage.util.ReportProperties;
import com.mss.mirage.util.ServiceLocatorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import jxl.Sheet;
import jxl.Workbook;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.hibernate.connection.DatasourceConnectionProvider;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author miracle
 */
public class PayrollAjaxHandlerServiceImpl implements PayrollAjaxHandlerService {

    private Connection connection;
    private StringBuffer stringBuffer;
    /**
     *
     * Creating a reference variable for preparedStatement
     */
    private PreparedStatement preparedStatement;
    /**
     *
     * Creating a reference variable for Resultset
     */
    private ResultSet resultSet;
    private Statement statement;
    private String queryString;

     public String getEmpNumAndStatus(String email, HttpServletRequest httpServletRequest) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        String result = "";
       // queryString = "Select Id,EmpNo,CurStatus from tblEmployee WHERE Email1 ='" + email + "' and CurStatus = 'Active' and Country='India' ";


        queryString = "SELECT tblEmployee.Id,tblEmployee.EmpNo,tblEmployee.CurStatus,tblEmpPayRoll.PayRollId FROM tblEmployee "
                + "LEFT JOIN tblEmpPayRoll ON (tblEmployee.EmpNo=tblEmpPayRoll.PayRollId)  WHERE EmpNo =" + email ;
        try {
            //  System.out.println(queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            prestatement = connection.prepareStatement(queryString);
            resultSet = prestatement.executeQuery();

            while (resultSet.next()) {
                result = resultSet.getInt("Id") + "|" + resultSet.getString("EmpNo") + "|" + resultSet.getString("CurStatus") + "|" + resultSet.getString("PayRollId");
            }
            //  System.out.println("result" + result);
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
                if (prestatement != null) {
                    prestatement.close();
                    prestatement = null;
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


    public String setPayRollDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {


        try {
            JSONObject expensesdata = new JSONObject(resultString);
//                System.out.println("expensesdata.firstName   "+expensesdata.getString("firstName"));
//                System.out.println("expensesdata.payRollComments   "+expensesdata.getString("payRollComments"));
            payrollAjaxHandlerAction.setFirstName(expensesdata.getString("firstName"));
            payrollAjaxHandlerAction.setLastName(expensesdata.getString("lastName"));
            payrollAjaxHandlerAction.setMiddleName(expensesdata.getString("middleName"));
            payrollAjaxHandlerAction.setDepartmentId(expensesdata.getString("departmentId"));
            payrollAjaxHandlerAction.setTitleId(expensesdata.getString("titleId"));
            payrollAjaxHandlerAction.setShiftId(expensesdata.getString("shiftId"));
            payrollAjaxHandlerAction.setClassificationId(expensesdata.getString("classificationId"));
            payrollAjaxHandlerAction.setLocationId(expensesdata.getString("locationId"));
            // payrollAjaxHandlerAction.setRegionId(expensesdata.getString("regionId"));
            payrollAjaxHandlerAction.setGender(expensesdata.getString("gender"));
            payrollAjaxHandlerAction.setCurrStatus(expensesdata.getString("currStatus"));
            payrollAjaxHandlerAction.setIsPfFlag(expensesdata.getString("isPfFlag"));
            payrollAjaxHandlerAction.setEmployerId(expensesdata.getString("employerId"));
            payrollAjaxHandlerAction.setSsn(expensesdata.getString("ssn"));
            payrollAjaxHandlerAction.setPassportNo(expensesdata.getString("passportNo"));
            payrollAjaxHandlerAction.setPfAccount(expensesdata.getString("pfAccount"));

            payrollAjaxHandlerAction.setTrainingPeriod(expensesdata.getString("trainingPeriod"));
            payrollAjaxHandlerAction.setContractPeriod(expensesdata.getString("contractPeriod"));
            payrollAjaxHandlerAction.setDateOfJoining(expensesdata.getString("dateOfJoining"));

            payrollAjaxHandlerAction.setUANNo(expensesdata.getString("UANNo"));
            payrollAjaxHandlerAction.setAdharNo(expensesdata.getString("adharNo"));

            payrollAjaxHandlerAction.setResonsForLeaving(expensesdata.getString("resonsForLeaving"));

            String address = expensesdata.getString("address");
            address = address.replace("\"", "  ");

            payrollAjaxHandlerAction.setAddress(address);
            payrollAjaxHandlerAction.setCorporateEmail(expensesdata.getString("corporateEmail"));
            payrollAjaxHandlerAction.setPersonalEmail(expensesdata.getString("personalEmail"));
            payrollAjaxHandlerAction.setFathername(expensesdata.getString("fathername"));
            payrollAjaxHandlerAction.setFatherTitle(expensesdata.getString("fatherTitle"));
            payrollAjaxHandlerAction.setCity(expensesdata.getString("city"));
            payrollAjaxHandlerAction.setState(expensesdata.getString("state"));
            payrollAjaxHandlerAction.setZip(expensesdata.getString("zip"));
            payrollAjaxHandlerAction.setFatherPhone(expensesdata.getString("fatherPhone"));
            payrollAjaxHandlerAction.setHomePhone(expensesdata.getString("homePhone"));
            payrollAjaxHandlerAction.setMobilePhone(expensesdata.getString("mobilePhone"));
            payrollAjaxHandlerAction.setBasic(expensesdata.getString("basic"));
            payrollAjaxHandlerAction.setPaymentType(expensesdata.getString("paymentType"));
            payrollAjaxHandlerAction.setDa(expensesdata.getString("da"));
            payrollAjaxHandlerAction.setBankAccountNo(expensesdata.getString("bankAccountNo"));
            payrollAjaxHandlerAction.setHra(expensesdata.getString("hra"));
            payrollAjaxHandlerAction.setBankName(expensesdata.getString("bankName"));
            payrollAjaxHandlerAction.setTa(expensesdata.getString("ta"));
            payrollAjaxHandlerAction.setEmployerPf(expensesdata.getString("employerPf"));
            payrollAjaxHandlerAction.setRa(expensesdata.getString("ra"));
            payrollAjaxHandlerAction.setEmployeePf(expensesdata.getString("employeePf"));
            payrollAjaxHandlerAction.setEntertainment(expensesdata.getString("entertainment"));
            payrollAjaxHandlerAction.setLife(expensesdata.getString("life"));
            payrollAjaxHandlerAction.setKidsEducation(expensesdata.getString("kidsEducation"));
            payrollAjaxHandlerAction.setHealth(expensesdata.getString("health"));
            payrollAjaxHandlerAction.setVehicleAllowance(expensesdata.getString("vehicleAllowance"));
            payrollAjaxHandlerAction.setProfessionalTax(expensesdata.getString("professionalTax"));
            payrollAjaxHandlerAction.setCca(expensesdata.getString("cca"));
            payrollAjaxHandlerAction.setOtherDeductions(expensesdata.getString("otherDeductions"));
            payrollAjaxHandlerAction.setMiscPay(expensesdata.getString("miscPay"));
            payrollAjaxHandlerAction.setSplAllowance(expensesdata.getString("splAllowance"));
            payrollAjaxHandlerAction.setLongTermBonus(expensesdata.getString("longTermBonus"));
            payrollAjaxHandlerAction.setGrossPay(expensesdata.getString("grossPay"));
            payrollAjaxHandlerAction.setProjectPay(expensesdata.getString("projectPay"));
            payrollAjaxHandlerAction.setVariablePay(expensesdata.getString("variablePay"));
            payrollAjaxHandlerAction.setAttendanceAllow(expensesdata.getString("attendanceAllow"));
            payrollAjaxHandlerAction.setTotalCost(expensesdata.getString("totalCost"));
            payrollAjaxHandlerAction.setOnProjectInd(expensesdata.getString("onProjectInd"));
            payrollAjaxHandlerAction.setOnProjectIndValue1(expensesdata.getString("onProjectIndValue1"));
            payrollAjaxHandlerAction.setOnProjectIndValue2(expensesdata.getString("onProjectIndValue2"));
            payrollAjaxHandlerAction.setDatePayRevised(expensesdata.getString("datePayRevised"));
            payrollAjaxHandlerAction.setOnsiteInd(expensesdata.getString("onsiteInd"));
            payrollAjaxHandlerAction.setOnsiteIndV(expensesdata.getString("onsiteIndV"));
            // payrollAjaxHandlerAction.setPrevYtdSalary(resultString.split(Pattern.quote("*@!"))[65].split(Pattern.quote("#^$"))[1]);
            payrollAjaxHandlerAction.setEmpSaving1(expensesdata.getString("empSaving1"));
            payrollAjaxHandlerAction.setEmpSaving2(expensesdata.getString("empSaving2"));
            /*payrollAjaxHandlerAction.setLifeInsureanceAmt(resultString.split(Pattern.quote("*@!"))[68].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setLifeInsureanceTerm(resultString.split(Pattern.quote("*@!"))[69].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setLifeInsureanceAnnual(resultString.split(Pattern.quote("*@!"))[70].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setLifeInsureancePolicy(resultString.split(Pattern.quote("*@!"))[71].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setHealthInsuranceAnnual(resultString.split(Pattern.quote("*@!"))[72].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setHealthInsuranceAmt(resultString.split(Pattern.quote("*@!"))[73].split(Pattern.quote("#^$"))[1]);
             */
            /*   payrollAjaxHandlerAction.setTutionfees(resultString.split(Pattern.quote("*@!"))[68].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setHbLoanInterst(resultString.split(Pattern.quote("*@!"))[69].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setPpf(resultString.split(Pattern.quote("*@!"))[70].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setMedicalIns(resultString.split(Pattern.quote("*@!"))[71].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setLifeIns(resultString.split(Pattern.quote("*@!"))[72].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setHraLifeInsuranceSavings(resultString.split(Pattern.quote("*@!"))[73].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setPremium(resultString.split(Pattern.quote("*@!"))[74].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setEduInterest(resultString.split(Pattern.quote("*@!"))[75].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setFd(resultString.split(Pattern.quote("*@!"))[76].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setHbLoanPrinciple(resultString.split(Pattern.quote("*@!"))[77].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setMutualFunds(resultString.split(Pattern.quote("*@!"))[78].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setNsc(resultString.split(Pattern.quote("*@!"))[79].split(Pattern.quote("#^$"))[1]);
             */
            /* String wageComments = resultString.split(Pattern.quote("*@!"))[80].split(Pattern.quote("#^$"))[1];
             wageComments = wageComments.replace("\""*@! "  ");
             wageComments = wageComments.replace(Pattern.quote("#^$")*@! "  ");
             String wageComments1 = resultString.split(Pattern.quote("*@!"))[81].split(Pattern.quote("#^$"))[1];
             wageComments1 = wageComments1.replace("\""*@! "  ");
             wageComments1 = wageComments1.replace(Pattern.quote("#^$")*@! "  ");
             String generalComments = resultString.split(Pattern.quote("*@!"))[82].split(Pattern.quote("#^$"))[1];
             generalComments = generalComments.replace("\""*@! "  ");
             generalComments = generalComments.replace(Pattern.quote("#^$")*@! "  ");
             String refComments = resultString.split(Pattern.quote("*@!"))[83].split(Pattern.quote("#^$"))[1];
             refComments = refComments.replace("\""*@! "  ");
             refComments = refComments.replace(Pattern.quote("#^$")*@! "  ");
            
            
            
             payrollAjaxHandlerAction.setWagecomments(wageComments);
             payrollAjaxHandlerAction.setWagecomments1(wageComments1);
             payrollAjaxHandlerAction.setGeneralcomments(generalComments);
             payrollAjaxHandlerAction.setReferencecomments(refComments);
             */
            payrollAjaxHandlerAction.setHomeAddressId(expensesdata.getString("homeAddressId"));
            payrollAjaxHandlerAction.setWorkPhone(expensesdata.getString("workPhone"));
            //payrollAjaxHandlerAction.setProjectEndDate(resultString.split(Pattern.quote("*@!"))[86].split(Pattern.quote("#^$"))[1]);


            /*
            
             payrollAjaxHandlerAction.setLifeInsurancePremium(resultString.split(Pattern.quote("*@!"))[86].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setHousingLoanRepay(resultString.split(Pattern.quote("*@!"))[87].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setNscTds(resultString.split(Pattern.quote("*@!"))[88].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setPpfContribution(resultString.split(Pattern.quote("*@!"))[89].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setTutionFee(resultString.split(Pattern.quote("*@!"))[90].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setElss(resultString.split(Pattern.quote("*@!"))[91].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setPostOfficeTerm(resultString.split(Pattern.quote("*@!"))[92].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setBankDepositTax(resultString.split(Pattern.quote("*@!"))[93].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setOthersTDS(resultString.split(Pattern.quote("*@!"))[94].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setContributionToPf(resultString.split(Pattern.quote("*@!"))[95].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setNpsEmployeeContr(resultString.split(Pattern.quote("*@!"))[96].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setTotalTds(resultString.split(Pattern.quote("*@!"))[97].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setTotalTdsDeductable(resultString.split(Pattern.quote("*@!"))[98].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setInterstOnBorrowed(resultString.split(Pattern.quote("*@!"))[99].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setInterstOnBorrowedDeductable(resultString.split(Pattern.quote("*@!"))[100].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setInsuranceForParents(resultString.split(Pattern.quote("*@!"))[101].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setInsuranceForParentsDeduc(resultString.split(Pattern.quote("*@!"))[102].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setInsuranceOthers(resultString.split(Pattern.quote("*@!"))[103].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setInsuranceOthersDeduc(resultString.split(Pattern.quote("*@!"))[104].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setInterstOnEdu(resultString.split(Pattern.quote("*@!"))[105].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setInterstOnHrAssumptions(resultString.split(Pattern.quote("*@!"))[106].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setInterstOnHrAssumptionsInv(resultString.split(Pattern.quote("*@!"))[107].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setExpectedYearlyCost(resultString.split(Pattern.quote("*@!"))[108].split(Pattern.quote("#^$"))[1]);
             payrollAjaxHandlerAction.setLicFromSal(resultString.split(Pattern.quote("*@!"))[109].split(Pattern.quote("#^$"))[1]);*/
            payrollAjaxHandlerAction.setNetPaidPayroll(Double.parseDouble(expensesdata.getString("netPaidPayroll")));


            payrollAjaxHandlerAction.setEmployeeesi(Double.parseDouble(expensesdata.getString("employeeEsi")));
            payrollAjaxHandlerAction.setEmployeresi(Double.parseDouble(expensesdata.getString("employerEsi")));

            payrollAjaxHandlerAction.setItgBatch(expensesdata.getString("itgBatch"));

            payrollAjaxHandlerAction.setEsiFlagVal(expensesdata.getString("esiFlag"));
            payrollAjaxHandlerAction.setDiffPF(Double.parseDouble(expensesdata.getString("diffPF")));
            payrollAjaxHandlerAction.setDateOfTermination(expensesdata.getString("dateOfTermination"));
            payrollAjaxHandlerAction.setDateOfEmployeement(expensesdata.getString("dateOfEmployement"));
            payrollAjaxHandlerAction.setPayRollComments(expensesdata.getString("payRollComments"));
            payrollAjaxHandlerAction.setEmpSaving3(expensesdata.getString("empSaving3"));
            payrollAjaxHandlerAction.setEmpSaving4(expensesdata.getString("empSaving4"));
            payrollAjaxHandlerAction.setEmpSaving5(expensesdata.getString("empSaving5"));
            payrollAjaxHandlerAction.setExpectedYearlyCost(expensesdata.getString("expectedYearlyCost"));
            payrollAjaxHandlerAction.setLockAmtStartDate(expensesdata.getString("lockAmtStartDate"));
            payrollAjaxHandlerAction.setIsSixMonthsLock(Integer.parseInt(expensesdata.getString("isSixMonthsLock")));
            payrollAjaxHandlerAction.setOrgId(Integer.parseInt(expensesdata.getString("orgId")));
            payrollAjaxHandlerAction.setPayRollId(expensesdata.getString("payRollId"));
            //payrollAjaxHandlerAction.setTdsId(Integer.parseInt(resultString.split(Pattern.quote("*@!"))[115].split(Pattern.quote("#^$"))[1]));
            payrollAjaxHandlerAction.setEmpId(expensesdata.getString("empId"));
            payrollAjaxHandlerAction.setIsFixedSalary(Integer.parseInt(expensesdata.getString("isFixedSalary")));
            payrollAjaxHandlerAction.setNoSalary(expensesdata.getString("noSalary"));
            payrollAjaxHandlerAction.setEmpSaving6(expensesdata.getString("empSaving6"));
            payrollAjaxHandlerAction.setPracticeId(expensesdata.getString("practiceId"));
              payrollAjaxHandlerAction.setNewGrossPay(expensesdata.getDouble("newGrossPay"));
              payrollAjaxHandlerAction.setUpdateFlag(expensesdata.getString("updateFlag"));


        } catch (Exception e) {
            e.printStackTrace();
        }


        //System.out.println("the campaign name from dsdp=" +campaignName);
        return null;
    }

    public String setPayRollEmployeeDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {


        try {

            payrollAjaxHandlerAction.setFirstName(resultString.split(",")[0].split(":")[1]);
            payrollAjaxHandlerAction.setLastName(resultString.split(",")[1].split(":")[1]);
            payrollAjaxHandlerAction.setMiddleName(resultString.split(",")[2].split(":")[1]);
            payrollAjaxHandlerAction.setDepartmentId(resultString.split(",")[3].split(":")[1]);
            payrollAjaxHandlerAction.setTitleId(resultString.split(",")[4].split(":")[1]);
            payrollAjaxHandlerAction.setShiftId(resultString.split(",")[5].split(":")[1]);
            payrollAjaxHandlerAction.setClassificationId(resultString.split(",")[6].split(":")[1]);
            payrollAjaxHandlerAction.setLocationId(resultString.split(",")[7].split(":")[1]);
            // payrollAjaxHandlerAction.setRegionId(resultString.split(",")[8].split(":")[1]);
            payrollAjaxHandlerAction.setGender(resultString.split(",")[9].split(":")[1]);
            payrollAjaxHandlerAction.setCurrStatus(resultString.split(",")[10].split(":")[1]);
            payrollAjaxHandlerAction.setIsPfFlag(resultString.split(",")[11].split(":")[1]);
            payrollAjaxHandlerAction.setEmployerId(resultString.split(",")[12].split(":")[1]);
            payrollAjaxHandlerAction.setSsn(resultString.split(",")[13].split(":")[1]);
            payrollAjaxHandlerAction.setPassportNo(resultString.split(",")[14].split(":")[1]);
            payrollAjaxHandlerAction.setPfAccount(resultString.split(",")[15].split(":")[1]);
            payrollAjaxHandlerAction.setBirthDate(resultString.split(",")[16].split(":")[1]);
            payrollAjaxHandlerAction.setTrainingPeriod(resultString.split(",")[17].split(":")[1]);
            payrollAjaxHandlerAction.setContractPeriod(resultString.split(",")[18].split(":")[1]);
            payrollAjaxHandlerAction.setDateOfJoining(resultString.split(",")[19].split(":")[1]);
            payrollAjaxHandlerAction.setDateOfEmployeement(resultString.split(",")[20].split(":")[1]);
            payrollAjaxHandlerAction.setTrueBirthday(resultString.split(",")[21].split(":")[1]);
            payrollAjaxHandlerAction.setDateOfterminating(resultString.split(",")[22].split(":")[1]);
            payrollAjaxHandlerAction.setWeddingDay(resultString.split(",")[23].split(":")[1]);
            payrollAjaxHandlerAction.setUANNo(resultString.split(",")[24].split(":")[1]);
            payrollAjaxHandlerAction.setAdharNo(resultString.split(",")[25].split(":")[1]);
            payrollAjaxHandlerAction.setItgBatch(resultString.split(",")[26].split(":")[1]);
            payrollAjaxHandlerAction.setResonsForLeaving(resultString.split(",")[27].split(":")[1]);
            payrollAjaxHandlerAction.setEmpId(resultString.split(",")[28].split(":")[1]);


        } catch (Exception e) {
            e.printStackTrace();
        }


        //System.out.println("the campaign name from dsdp=" +campaignName);
        return null;
    }

    public String setEmployeePayRollDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {


        try {
            payrollAjaxHandlerAction.setBasic(resultString.split(",")[0].split(":")[1]);
            payrollAjaxHandlerAction.setPaymentType(resultString.split(",")[1].split(":")[1]);
            payrollAjaxHandlerAction.setDa(resultString.split(",")[2].split(":")[1]);
            payrollAjaxHandlerAction.setBankAccountNo(resultString.split(",")[3].split(":")[1]);
            payrollAjaxHandlerAction.setHra(resultString.split(",")[4].split(":")[1]);
            payrollAjaxHandlerAction.setBankName(resultString.split(",")[5].split(":")[1]);
            payrollAjaxHandlerAction.setTa(resultString.split(",")[6].split(":")[1]);
            payrollAjaxHandlerAction.setEmployerPf(resultString.split(",")[7].split(":")[1]);
            payrollAjaxHandlerAction.setRa(resultString.split(",")[8].split(":")[1]);
            payrollAjaxHandlerAction.setEmployeePf(resultString.split(",")[9].split(":")[1]);
            payrollAjaxHandlerAction.setEntertainment(resultString.split(",")[10].split(":")[1]);
            payrollAjaxHandlerAction.setLife(resultString.split(",")[11].split(":")[1]);
            payrollAjaxHandlerAction.setKidsEducation(resultString.split(",")[12].split(":")[1]);
            payrollAjaxHandlerAction.setHealth(resultString.split(",")[13].split(":")[1]);
            payrollAjaxHandlerAction.setVehicleAllowance(resultString.split(",")[14].split(":")[1]);
            payrollAjaxHandlerAction.setProfessionalTax(resultString.split(",")[15].split(":")[1]);
            payrollAjaxHandlerAction.setCca(resultString.split(",")[16].split(":")[1]);
            payrollAjaxHandlerAction.setOtherDeductions(resultString.split(",")[17].split(":")[1]);
            payrollAjaxHandlerAction.setMiscPay(resultString.split(",")[18].split(":")[1]);
            payrollAjaxHandlerAction.setSplAllowance(resultString.split(",")[19].split(":")[1]);
            payrollAjaxHandlerAction.setLongTermBonus(resultString.split(",")[20].split(":")[1]);
            payrollAjaxHandlerAction.setGrossPay(resultString.split(",")[21].split(":")[1]);
            payrollAjaxHandlerAction.setProjectPay(resultString.split(",")[22].split(":")[1]);
            payrollAjaxHandlerAction.setVariablePay(resultString.split(",")[23].split(":")[1]);
            payrollAjaxHandlerAction.setAttendanceAllow(resultString.split(",")[24].split(":")[1]);
            payrollAjaxHandlerAction.setTotalCost(resultString.split(",")[25].split(":")[1]);
            payrollAjaxHandlerAction.setOnProjectInd(resultString.split(",")[26].split(":")[1]);
            payrollAjaxHandlerAction.setOnProjectIndValue1(resultString.split(",")[27].split(":")[1]);
            payrollAjaxHandlerAction.setOnProjectIndValue2(resultString.split(",")[28].split(":")[1]);
            payrollAjaxHandlerAction.setDatePayRevised(resultString.split(",")[29].split(":")[1]);
            payrollAjaxHandlerAction.setOnsiteInd(resultString.split(",")[30].split(":")[1]);
            payrollAjaxHandlerAction.setOnsiteIndV(resultString.split(",")[31].split(":")[1]);
            payrollAjaxHandlerAction.setEmpId(resultString.split(",")[32].split(":")[1]);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public String setInsuranceSavingsPayrollDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {


        try {




            payrollAjaxHandlerAction.setPrevYtdSalary(resultString.split(",")[0].split(":")[1]);
            payrollAjaxHandlerAction.setEmpSaving1(resultString.split(",")[1].split(":")[1]);
            payrollAjaxHandlerAction.setEmpSaving2(resultString.split(",")[2].split(":")[1]);
            payrollAjaxHandlerAction.setLifeInsureanceAmt(resultString.split(",")[3].split(":")[1]);
            payrollAjaxHandlerAction.setLifeInsureanceTerm(resultString.split(",")[4].split(":")[1]);
            payrollAjaxHandlerAction.setLifeInsureanceAnnual(resultString.split(",")[5].split(":")[1]);
            payrollAjaxHandlerAction.setLifeInsureancePolicy(resultString.split(",")[6].split(":")[1]);
            payrollAjaxHandlerAction.setHealthInsuranceAnnual(resultString.split(",")[7].split(":")[1]);
            payrollAjaxHandlerAction.setHealthInsuranceAmt(resultString.split(",")[8].split(":")[1]);
            payrollAjaxHandlerAction.setEmpId(resultString.split(",")[9].split(":")[1]);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public String setOtherPayrollDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {


        try {




            payrollAjaxHandlerAction.setWagecomments(resultString.split(",")[0].split(":")[1]);
            payrollAjaxHandlerAction.setWagecomments1(resultString.split(",")[1].split(":")[1]);
            payrollAjaxHandlerAction.setGeneralcomments(resultString.split(",")[2].split(":")[1]);
            payrollAjaxHandlerAction.setReferencecomments(resultString.split(",")[3].split(":")[1]);
            payrollAjaxHandlerAction.setEmpId(resultString.split(",")[4].split(":")[1]);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public String setPayrollContactDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {


        try {




            payrollAjaxHandlerAction.setAddress(resultString.split(",")[0].split(":")[1]);
            payrollAjaxHandlerAction.setCorporateEmail(resultString.split(",")[1].split(":")[1]);
            payrollAjaxHandlerAction.setPersonalEmail(resultString.split(",")[2].split(":")[1]);
            payrollAjaxHandlerAction.setFathername(resultString.split(",")[3].split(":")[1]);
            payrollAjaxHandlerAction.setFatherTitle(resultString.split(",")[4].split(":")[1]);
            payrollAjaxHandlerAction.setCity(resultString.split(",")[5].split(":")[1]);
            payrollAjaxHandlerAction.setState(resultString.split(",")[6].split(":")[1]);
            payrollAjaxHandlerAction.setZip(resultString.split(",")[7].split(":")[1]);
            payrollAjaxHandlerAction.setFatherPhone(resultString.split(",")[8].split(":")[1]);
            payrollAjaxHandlerAction.setHomePhone(resultString.split(",")[9].split(":")[1]);
            payrollAjaxHandlerAction.setMobilePhone(resultString.split(",")[10].split(":")[1]);
            payrollAjaxHandlerAction.setEmpId(resultString.split(",")[11].split(":")[1]);
            payrollAjaxHandlerAction.setHomeAddressId(resultString.split(",")[12].split(":")[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public String doAddPayRollDetails(String empId, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        int updatedRows = 0;
        int isSuccess = 0;
        int payRollInsertionUpdate = 0;
        String result;
        try {

            DateUtility dateUtil = DateUtility.getInstance();
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spPayRollDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            callableStatement.setInt(1, Integer.parseInt(payrollAjaxHandlerAction.getEmpId()));
            if (!"nun".equals(payrollAjaxHandlerAction.getLastName())) {
                callableStatement.setString(2, payrollAjaxHandlerAction.getLastName());
            } else {
                callableStatement.setString(2, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getFirstName())) {
                callableStatement.setString(3, payrollAjaxHandlerAction.getFirstName());
            } else {
                callableStatement.setString(3, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getMiddleName())) {
                callableStatement.setString(4, payrollAjaxHandlerAction.getMiddleName());
            } else {
                callableStatement.setString(4, " ");
            }

            if (!"nun".equals(payrollAjaxHandlerAction.getDepartmentId())) {
                callableStatement.setString(5, payrollAjaxHandlerAction.getDepartmentId());
            } else {
                callableStatement.setString(5, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getTitleId())) {
                callableStatement.setString(6, payrollAjaxHandlerAction.getTitleId());
            } else {
                callableStatement.setString(6, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getLocationId())) {
                callableStatement.setString(7, payrollAjaxHandlerAction.getLocationId());
            } else {
                callableStatement.setString(7, "-1");
            }

            callableStatement.setString(8, payrollAjaxHandlerAction.getGender());
            if (!"nun".equals(payrollAjaxHandlerAction.getCurrStatus())) {
                callableStatement.setString(9, payrollAjaxHandlerAction.getCurrStatus());
            } else {
                callableStatement.setString(9, " ");
            }
            callableStatement.setString(10, payrollAjaxHandlerAction.getEmployerId());
            if (!"nun".equals(payrollAjaxHandlerAction.getSsn())) {
                callableStatement.setString(11, payrollAjaxHandlerAction.getSsn());
            } else {
                callableStatement.setString(11, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getPfAccount())) {
                callableStatement.setString(12, payrollAjaxHandlerAction.getPfAccount());
            } else {
                callableStatement.setString(12, " ");
            }
            callableStatement.setString(13, payrollAjaxHandlerAction.getIsPfFlag());
            if (!"nun".equals(payrollAjaxHandlerAction.getDateOfJoining())) {
                callableStatement.setDate(14, dateUtil.getMysqlDate(payrollAjaxHandlerAction.getDateOfJoining()));
            } else {
                callableStatement.setDate(14, dateUtil.getMysqlDate("01/01/1951"));
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getResonsForLeaving())) {
                callableStatement.setString(15, payrollAjaxHandlerAction.getResonsForLeaving());
            } else {
                callableStatement.setString(15, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getTrainingPeriod())) {
                callableStatement.setInt(16, Integer.parseInt(payrollAjaxHandlerAction.getTrainingPeriod()));
            } else {
                callableStatement.setInt(16, 0);
            }
            callableStatement.setInt(17, Integer.parseInt(payrollAjaxHandlerAction.getOnProjectInd()));
            if (!"nun".equals(payrollAjaxHandlerAction.getHomeAddressId())) {
                callableStatement.setInt(18, Integer.parseInt(payrollAjaxHandlerAction.getHomeAddressId()));
            } else {
                callableStatement.setInt(18, 0);
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getCorporateEmail())) {
                callableStatement.setString(19, payrollAjaxHandlerAction.getCorporateEmail());
            } else {
                callableStatement.setString(19, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getPersonalEmail())) {
                callableStatement.setString(20, payrollAjaxHandlerAction.getPersonalEmail());
            } else {
                callableStatement.setString(20, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getWorkPhone())) {
                callableStatement.setString(21, payrollAjaxHandlerAction.getWorkPhone());
            } else {
                callableStatement.setString(21, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getHomePhone())) {
                callableStatement.setString(22, payrollAjaxHandlerAction.getHomePhone());
            } else {
                callableStatement.setString(22, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getMobilePhone())) {
                callableStatement.setString(23, payrollAjaxHandlerAction.getMobilePhone());
            } else {
                callableStatement.setString(23, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getAddress())) {
                callableStatement.setString(24, payrollAjaxHandlerAction.getAddress());
            } else {
                callableStatement.setString(24, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getCity())) {
                callableStatement.setString(25, payrollAjaxHandlerAction.getCity());
            } else {
                callableStatement.setString(25, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getState())) {
                callableStatement.setString(26, payrollAjaxHandlerAction.getState());
            } else {
                callableStatement.setString(26, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getZip())) {
                callableStatement.setInt(27, Integer.parseInt(payrollAjaxHandlerAction.getZip()));
            } else {
                callableStatement.setInt(27, 0);
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getFathername())) {
                callableStatement.setString(28, payrollAjaxHandlerAction.getFathername());
            } else {
                callableStatement.setString(28, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getFatherTitle())) {
                callableStatement.setString(29, payrollAjaxHandlerAction.getFatherTitle());
            } else {
                callableStatement.setString(29, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getFatherPhone())) {
                callableStatement.setString(30, payrollAjaxHandlerAction.getFatherPhone());
            } else {
                callableStatement.setString(30, " ");
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getPassportNo())) {
                callableStatement.setString(31, payrollAjaxHandlerAction.getPassportNo());
            } else {
                callableStatement.setString(31, " ");
            }
            /*  if (!"nun".equals(payrollAjaxHandlerAction.getWagecomments())) {
             callableStatement.setString(32, payrollAjaxHandlerAction.getWagecomments());
             } else {
             callableStatement.setString(32, "");
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getWagecomments1())) {
             callableStatement.setString(33, payrollAjaxHandlerAction.getWagecomments1());
             } else {
             callableStatement.setString(33, "");
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getReferencecomments())) {
             callableStatement.setString(34, payrollAjaxHandlerAction.getReferencecomments());
             } else {
             callableStatement.setString(34, "");
             }*/
            //   callableStatement.setDouble(32, Double.parseDouble(payrollAjaxHandlerAction.getPrevYtdSalary()));
            callableStatement.setDouble(32, Double.parseDouble(payrollAjaxHandlerAction.getEmpSaving1()));
            callableStatement.setDouble(33, Double.parseDouble(payrollAjaxHandlerAction.getEmpSaving2()));
            /*  if (!"nun".equals(payrollAjaxHandlerAction.getTutionfees())) {
             callableStatement.setDouble(35, Double.parseDouble(payrollAjaxHandlerAction.getTutionfees()));
             } else {
             callableStatement.setDouble(35, 0.0);
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getHbLoanInterst())) {
             callableStatement.setDouble(36, Double.parseDouble(payrollAjaxHandlerAction.getHbLoanInterst()));
             } else {
             callableStatement.setDouble(36, 0.0);
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getPpf())) {
             callableStatement.setDouble(37, Double.parseDouble(payrollAjaxHandlerAction.getPpf()));
             } else {
             callableStatement.setDouble(37, 0.0);
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getMedicalIns())) {
             callableStatement.setDouble(38, Double.parseDouble(payrollAjaxHandlerAction.getMedicalIns()));
             } else {
             callableStatement.setDouble(38, 0.0);
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getLifeIns())) {
             callableStatement.setDouble(39, Double.parseDouble(payrollAjaxHandlerAction.getLifeIns()));
             } else {
             callableStatement.setDouble(39, 0.0);
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getHraLifeInsuranceSavings())) {
             callableStatement.setDouble(40, Double.parseDouble(payrollAjaxHandlerAction.getHraLifeInsuranceSavings()));
             } else {
             callableStatement.setDouble(40, 0.0);
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getPremium())) {
             callableStatement.setDouble(41, Double.parseDouble(payrollAjaxHandlerAction.getPremium()));
             } else {
             callableStatement.setDouble(41, 0.0);
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getEduInterest())) {
             callableStatement.setDouble(42, Double.parseDouble(payrollAjaxHandlerAction.getEduInterest()));
             } else {
             callableStatement.setDouble(42, 0.0);
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getFd())) {
             callableStatement.setDouble(43, Double.parseDouble(payrollAjaxHandlerAction.getFd()));
             } else {
             callableStatement.setDouble(43, 0.0);
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getHbLoanPrinciple())) {
             callableStatement.setDouble(44, Double.parseDouble(payrollAjaxHandlerAction.getHbLoanPrinciple()));
             } else {
             callableStatement.setDouble(44, 0.0);
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getMutualFunds())) {
             callableStatement.setDouble(45, Double.parseDouble(payrollAjaxHandlerAction.getMutualFunds()));
             } else {
             callableStatement.setDouble(45, 0.0);
             }
             if (!"nun".equals(payrollAjaxHandlerAction.getNsc())) {
             callableStatement.setDouble(46, Double.parseDouble(payrollAjaxHandlerAction.getNsc()));
             } else {
             callableStatement.setDouble(46, 0.0);
             }*/
            callableStatement.setDouble(34, Double.parseDouble(payrollAjaxHandlerAction.getBasic()));
            callableStatement.setDouble(35, Double.parseDouble(payrollAjaxHandlerAction.getDa()));
            callableStatement.setDouble(36, Double.parseDouble(payrollAjaxHandlerAction.getHra()));
            callableStatement.setDouble(37, Double.parseDouble(payrollAjaxHandlerAction.getTa()));
            callableStatement.setDouble(38, Double.parseDouble(payrollAjaxHandlerAction.getRa()));
            callableStatement.setDouble(39, Double.parseDouble(payrollAjaxHandlerAction.getEntertainment()));
            callableStatement.setDouble(40, Double.parseDouble(payrollAjaxHandlerAction.getKidsEducation()));
            callableStatement.setDouble(41, Double.parseDouble(payrollAjaxHandlerAction.getVehicleAllowance()));
            callableStatement.setDouble(42, Double.parseDouble(payrollAjaxHandlerAction.getCca()));
            callableStatement.setDouble(43, Double.parseDouble(payrollAjaxHandlerAction.getMiscPay()));
            callableStatement.setDouble(44, Double.parseDouble(payrollAjaxHandlerAction.getSplAllowance()));
            callableStatement.setDouble(45, Double.parseDouble(payrollAjaxHandlerAction.getLongTermBonus()));
            callableStatement.setDouble(46, Double.parseDouble(payrollAjaxHandlerAction.getProjectPay()));
            callableStatement.setDouble(47, Double.parseDouble(payrollAjaxHandlerAction.getAttendanceAllow()));
            if (!"All".equals(payrollAjaxHandlerAction.getPaymentType())) {
                callableStatement.setInt(48, Integer.parseInt(payrollAjaxHandlerAction.getPaymentType()));
            } else {
                callableStatement.setInt(48, 0);
            }
            callableStatement.setDouble(49, Double.parseDouble(payrollAjaxHandlerAction.getEmployerPf()));
            callableStatement.setDouble(50, Double.parseDouble(payrollAjaxHandlerAction.getEmployeePf()));
            callableStatement.setDouble(51, Double.parseDouble(payrollAjaxHandlerAction.getLife()));
            callableStatement.setDouble(52, Double.parseDouble(payrollAjaxHandlerAction.getHealth()));
            callableStatement.setDouble(53, Double.parseDouble(payrollAjaxHandlerAction.getProfessionalTax()));
            callableStatement.setDouble(54, Double.parseDouble(payrollAjaxHandlerAction.getOtherDeductions()));
            callableStatement.setDouble(55, Double.parseDouble(payrollAjaxHandlerAction.getGrossPay()));
            callableStatement.setDouble(56, Double.parseDouble(payrollAjaxHandlerAction.getVariablePay()));
            callableStatement.setDouble(57, Double.parseDouble(payrollAjaxHandlerAction.getTotalCost()));
            callableStatement.setString(58, payrollAjaxHandlerAction.getBankName());
            callableStatement.setString(59, payrollAjaxHandlerAction.getBankAccountNo());
            if (Integer.parseInt(payrollAjaxHandlerAction.getEmployerId()) == 0) {
                callableStatement.setString(60, payrollAjaxHandlerAction.getUserId());
                callableStatement.setString(61, "");
            } else {
                callableStatement.setString(60, "");
                callableStatement.setString(61, payrollAjaxHandlerAction.getUserId());
            }
            /*   if (!"nun".equals(payrollAjaxHandlerAction.getGeneralcomments())) {
             callableStatement.setString(78, payrollAjaxHandlerAction.getGeneralcomments());
             } else {
             callableStatement.setString(78, " ");
             }*/

            if (!"nun".equals(payrollAjaxHandlerAction.getAdharNo())) {

                callableStatement.setString(62, payrollAjaxHandlerAction.getAdharNo());
            } else {
                callableStatement.setString(62, " ");
            }



            callableStatement.setString(63, DataSourceDataProvider.getInstance().getBankNameByBankId(payrollAjaxHandlerAction.getBankName()));
            callableStatement.setInt(64, payrollAjaxHandlerAction.getOrgId());
            callableStatement.setString(65, payrollAjaxHandlerAction.getOrgName());

            callableStatement.setInt(66, Integer.parseInt(payrollAjaxHandlerAction.getClassificationId()));
            callableStatement.setInt(67, Integer.parseInt(payrollAjaxHandlerAction.getShiftId()));
            /*  if (!"nun".equals(payrollAjaxHandlerAction.getProjectEndDate())) {
             callableStatement.setDate(85, dateUtil.getMysqlDate(payrollAjaxHandlerAction.getProjectEndDate()));
             } else {
             callableStatement.setDate(85, dateUtil.getMysqlDate("01/01/1951"));
             }*/




            /*  callableStatement.setDouble(85, Double.parseDouble(payrollAjaxHandlerAction.getLifeInsurancePremium()));
             callableStatement.setDouble(86, Double.parseDouble(payrollAjaxHandlerAction.getHousingLoanRepay()));
             callableStatement.setDouble(87, Double.parseDouble(payrollAjaxHandlerAction.getNscTds()));
             callableStatement.setDouble(88, Double.parseDouble(payrollAjaxHandlerAction.getPpfContribution()));
             callableStatement.setDouble(89, Double.parseDouble(payrollAjaxHandlerAction.getTutionFee()));
             callableStatement.setDouble(90, Double.parseDouble(payrollAjaxHandlerAction.getElss()));
             callableStatement.setDouble(91, Double.parseDouble(payrollAjaxHandlerAction.getPostOfficeTerm()));
             callableStatement.setDouble(92, Double.parseDouble(payrollAjaxHandlerAction.getBankDepositTax()));
             callableStatement.setDouble(93, Double.parseDouble(payrollAjaxHandlerAction.getOthersTDS()));
             callableStatement.setDouble(94, Double.parseDouble(payrollAjaxHandlerAction.getContributionToPf()));
             callableStatement.setDouble(95, Double.parseDouble(payrollAjaxHandlerAction.getNpsEmployeeContr()));
             callableStatement.setDouble(96, Double.parseDouble(payrollAjaxHandlerAction.getTotalTds()));
             callableStatement.setDouble(97, Double.parseDouble(payrollAjaxHandlerAction.getTotalTdsDeductable()));
             callableStatement.setDouble(98, Double.parseDouble(payrollAjaxHandlerAction.getInterstOnBorrowed()));
             callableStatement.setDouble(99, Double.parseDouble(payrollAjaxHandlerAction.getInterstOnBorrowedDeductable()));
             callableStatement.setDouble(100, Double.parseDouble(payrollAjaxHandlerAction.getInsuranceForParents()));
             callableStatement.setDouble(101, Double.parseDouble(payrollAjaxHandlerAction.getInsuranceForParentsDeduc()));
             callableStatement.setDouble(102, Double.parseDouble(payrollAjaxHandlerAction.getInsuranceOthers()));
             callableStatement.setDouble(103, Double.parseDouble(payrollAjaxHandlerAction.getInsuranceOthersDeduc()));
             callableStatement.setDouble(104, Double.parseDouble(payrollAjaxHandlerAction.getInterstOnEdu()));
             callableStatement.setDouble(105, Double.parseDouble(payrollAjaxHandlerAction.getInterstOnHrAssumptions()));*/

            callableStatement.setDouble(68, payrollAjaxHandlerAction.getNetPaidPayroll());
            callableStatement.setDouble(69, payrollAjaxHandlerAction.getEmployeresi());


            callableStatement.setDouble(70, payrollAjaxHandlerAction.getEmployeeesi());
            /*   callableStatement.setDouble(109, Double.parseDouble(payrollAjaxHandlerAction.getInterstOnHrAssumptionsInv()));
             callableStatement.setDouble(110, Double.parseDouble(payrollAjaxHandlerAction.getExpectedYearlyCost()));
             callableStatement.setDouble(111, Double.parseDouble(payrollAjaxHandlerAction.getLicFromSal()));*/
            callableStatement.setInt(71, Integer.parseInt(payrollAjaxHandlerAction.getPayRollId()));
            //  callableStatement.setInt(113, payrollAjaxHandlerAction.getTdsId());


            if (!"nun".equals(payrollAjaxHandlerAction.getUANNo())) {

                callableStatement.setString(72, payrollAjaxHandlerAction.getUANNo());
            } else {
                callableStatement.setString(72, " ");
            }
            callableStatement.setInt(73, Integer.parseInt(payrollAjaxHandlerAction.getOnsiteInd()));
            if (!"nun".equals(payrollAjaxHandlerAction.getItgBatch())) {

                callableStatement.setString(74, payrollAjaxHandlerAction.getItgBatch());
            } else {
                callableStatement.setString(74, " ");
            }
            callableStatement.setString(75, payrollAjaxHandlerAction.getEsiFlagVal());
            if (!"nun".equals(payrollAjaxHandlerAction.getDateOfEmployeement())) {
                callableStatement.setDate(76, dateUtil.getMysqlDate(payrollAjaxHandlerAction.getDateOfEmployeement()));
            } else {
                callableStatement.setDate(76, dateUtil.getMysqlDate("01/01/1951"));
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getDateOfTermination())) {
                callableStatement.setDate(77, dateUtil.getMysqlDate(payrollAjaxHandlerAction.getDateOfTermination()));
            } else {
                callableStatement.setDate(77, dateUtil.getMysqlDate("01/01/1951"));
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getPayRollComments())) {

                callableStatement.setString(78, payrollAjaxHandlerAction.getPayRollComments());
            } else {
                callableStatement.setString(78, " ");
            }


            callableStatement.setDouble(79, payrollAjaxHandlerAction.getDiffPF());

            callableStatement.setDouble(80, Double.parseDouble(payrollAjaxHandlerAction.getEmpSaving3()));
            callableStatement.setDouble(81, Double.parseDouble(payrollAjaxHandlerAction.getEmpSaving4()));
            callableStatement.setDouble(82, Double.parseDouble(payrollAjaxHandlerAction.getEmpSaving5()));
            callableStatement.setDouble(83, Double.parseDouble(payrollAjaxHandlerAction.getExpectedYearlyCost()));
            if (!"nun".equals(payrollAjaxHandlerAction.getDatePayRevised())) {
                callableStatement.setDate(84, dateUtil.getMysqlDate(payrollAjaxHandlerAction.getDatePayRevised()));
            } else {
                callableStatement.setDate(84, dateUtil.getMysqlDate("01/01/1951"));
            }
            if (!"nun".equals(payrollAjaxHandlerAction.getLockAmtStartDate())) {
                callableStatement.setDate(85, dateUtil.getMysqlDate(payrollAjaxHandlerAction.getLockAmtStartDate()));
            } else {
                callableStatement.setDate(85, dateUtil.getMysqlDate("01/01/1951"));
            }
            callableStatement.setInt(86, payrollAjaxHandlerAction.getIsSixMonthsLock());
            callableStatement.setInt(87, payrollAjaxHandlerAction.getIsFixedSalary());
            callableStatement.setString(88, payrollAjaxHandlerAction.getNoSalary());
            callableStatement.setDouble(89, Double.parseDouble(payrollAjaxHandlerAction.getEmpSaving6()));
              if (!"nun".equals(payrollAjaxHandlerAction.getPracticeId())) {
            callableStatement.setString(90, payrollAjaxHandlerAction.getPracticeId());
              }
              else{
                  callableStatement.setString(90, "");
              }
                 
            callableStatement.setDouble(91, payrollAjaxHandlerAction.getNewGrossPay());
          //  System.out.println("payrollAjaxHandlerAction.getUpdateFlag()---"+payrollAjaxHandlerAction.getUpdateFlag());
             callableStatement.setString(92,payrollAjaxHandlerAction.getUpdateFlag()); 
            callableStatement.registerOutParameter(93, Types.VARCHAR);

            updatedRows = callableStatement.executeUpdate();

            result = callableStatement.getString(93);
        } catch (SQLException se) {
            se.printStackTrace();
            // System.out.println(se.getMessage());
            throw new ServiceLocatorException(se.getMessage());
        } finally {
            try {
                  if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
              

            } catch (SQLException sqle) {
                throw new ServiceLocatorException(sqle);
            }
        }
        //System.out.println("taskMaxId-->"+taskMaxId);
        return result;
    }

    public String getEmployeesByDept(String deptName) throws ServiceLocatorException {
        StringBuffer reportsToBuffer = new StringBuffer();
        String loginId = null;
        String topManagementPeople[] = null;
        boolean isTopManager = false;



        try {
            if (deptName == null || "".equals(deptName)) {
                deptName = "%";
            }
            queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee WHERE DepartmentId LIKE '" + deptName + "' AND CurStatus='Active' AND Country='India' ORDER BY EmployeeName";
            //  System.out.println("emp list query-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            reportsToBuffer.append("<xml version=\"1.0\">");
            reportsToBuffer.append("<TEAM Description=\"" + deptName + "\">");
            reportsToBuffer.append("<USER userId=\"\">All</USER>");


            while (resultSet.next()) {
                loginId = resultSet.getString("LoginId");

                reportsToBuffer.append("<USER userId=\"" + loginId + "\">");
                reportsToBuffer.append(resultSet.getString("EmployeeName"));
                reportsToBuffer.append("</USER>");

            }
            reportsToBuffer.append("</TEAM>");
            reportsToBuffer.append("</xml>");
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
        //System.out.println("Team List: "+reportsToBuffer.toString());
        return reportsToBuffer.toString();
    }

    public int insertIntoWages(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        Connection connection1 = null;
        Statement statement = null;
        Statement statement1 = null;
        // PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int updatedRows = 0;
        String queryString = "";
        String queryString1 = "SELECT Id FROM tblEmployee WHERE Country = 'India' AND CurStatus='Active'";
        try {
            connection1 = ConnectionProvider.getInstance().getConnection();
            statement = connection1.createStatement();
            resultSet = statement.executeQuery(queryString1);
            while (resultSet.next()) {
                //   System.out.println(resultSet.getInt("Id"));
                connection = ConnectionProvider.getInstance().getConnection();
                statement1 = connection.createStatement();
                queryString = "INSERT INTO `mirage`.`tblEmpWages` "
                        + "(`PayRoll_Id`, `PaymentTypeId`, `GrossPay`, `BasicPay1`,`DA`, `HRA`, `TA`, `RA`, `Life`, `Health`, `AttAllowance`, "
                        + "`Entertainment`, `KidsEducation`,`VehcleAllowance`, `LongTermBonus`, `Employer_PF`, `Employee_PF`,`CCA1`, `SplAllowance`, "
                        + "`MiscPay`, `VariablePay`, Ded_Professional_Tax,Ded_Others,YTD_Gross,YTD_Savings1,YTD_Savings2,EmpId) "
                        + "SELECT `PayRollId`, `PaymentTypeId`,`GrossPay`, `BasicPay`, `DA`, `HRA`, `TA`, `RA`, `Life`, `Health`,`AttAllowance`, "
                        + "`Entertainment`, `KidsEducation`, `VehcleAllowance`, `LongTermbonus`, `Employer_PF`, `Employee_PF`, `CCA`, `SplAllowances`, "
                        + "`MiscPay`, `VariablePay`, `Ded_Professional_Tax`, `Ded_Others`, `Prev_Employer_YTD_Gross`, `EmpSavings1`, `EmpSavings2`,EmpId	FROM tblEmpPayRoll WHERE PayRollId = " + resultSet.getInt("Id");
                //      System.out.println("queryString->" + queryString);
                statement1.addBatch(queryString);
            }
            int rowsInserted[] = statement1.executeBatch();
            updatedRows = rowsInserted.length;
            //  System.out.println("updatedRows->" + updatedRows);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
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

    /* public String updateEmpWagesForCurrentMonth(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {
    
     Connection connection = null;
     PreparedStatement prestatement = null;
     ResultSet resultSet = null;
     Connection connection1 = null;
     PreparedStatement prestatement1 = null;
     ResultSet resultSet1 = null;
     Connection connection2 = null;
     PreparedStatement prestatement2 = null;
     ResultSet resultSet2 = null;
     String resultMessage = "";
     String queryString1 = "";
     String queryString2 = "";
     int insertedRows = 0;
     int updatedrows = 0;
     int count = 0;
     String queryString = "SELECT * FROM tblEmpWages WHERE MONTH(CreateDate) = " + month + " AND YEAR(CreateDate)=" + year;
     System.out.println("query-->" + queryString);
     try {
     connection = ConnectionProvider.getInstance().getConnection();
     prestatement = connection.prepareStatement(queryString);
     resultSet = prestatement.executeQuery();
     if (resultSet.next()) {
     System.out.println("in forst if");
     //further statements
     queryString1 = "SELECT Freeze_Payroll FROM tblEmpWages WHERE MONTH(CreateDate) = " + month + " AND YEAR(CreateDate)=" + year;
     connection1 = ConnectionProvider.getInstance().getConnection();
     prestatement1 = connection1.prepareStatement(queryString1);
     resultSet1 = prestatement1.executeQuery();
     while (resultSet1.next()) {
     System.out.println("in second if--->" + resultSet1.getInt("Freeze_Payroll"));
     if (resultSet1.getInt("Freeze_Payroll") == 0) {
    
     queryString2 = "UPDATE `mirage`.`tblEmpWages` SET `DaysInMonth` = " + payrollAjaxHandlerAction.getNoOfDays() + " , `DaysHolidays` = " + payrollAjaxHandlerAction.getNoOfHolidays() + " , `Daysweekends` = " + payrollAjaxHandlerAction.getNoOfWeekedDays() + " WHERE MONTH(CreateDate) = " + month + " AND YEAR(CreateDate)=" + year;
     connection2 = ConnectionProvider.getInstance().getConnection();
     prestatement2 = connection2.prepareStatement(queryString2);
     updatedrows = prestatement2.executeUpdate();
     System.out.println("in second if updatedrows--->" + updatedrows);
     if (updatedrows > 0) {
     resultMessage = "<font style='color:green;'>Updated Succesfully</font>";
     }
     //further statements
     } else {
     resultMessage = "<font style='color:red;'>Records already freezed</font>";
     }
    
     }
     } else {
     resultMessage = "<font style='color:red;'>Records doesnt exists for the given month and year..</font>";
     }
    
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
     if (prestatement != null) {
     prestatement.close();
     prestatement = null;
     }
     if (connection != null) {
     connection.close();
     connection = null;
     }
     if (resultSet1 != null) {
     resultSet1.close();
     resultSet1 = null;
     }
     if (prestatement1 != null) {
     prestatement1.close();
     prestatement1 = null;
     }
     if (connection1 != null) {
     connection1.close();
     connection1 = null;
     }
     if (resultSet2 != null) {
     resultSet2.close();
     resultSet2 = null;
     }
     if (prestatement2 != null) {
     prestatement2.close();
     prestatement2 = null;
     }
     if (connection2 != null) {
     connection2.close();
     connection2 = null;
     }
    
     } catch (SQLException ex) {
     ex.printStackTrace();
     }
     }
     return resultMessage;
    
     }
     */
    public String runEmpWagesForCurrentMonth(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        
        ResultSet resultSet = null;

        CallableStatement callableStatement = null;

        String resultMessage = "";
        String queryString1 = "";
        String queryString2 = "";
        int insertedRows = 0;
        int updatedrows = 0;
        int count = 0;

        try {

            boolean exists = DataSourceDataProvider.getInstance().checkForPayrollDateForRunWages(month, year, payrollAjaxHandlerAction.getOrgId());
            boolean checkLoadLeavesExists = DataSourceDataProvider.getInstance().checkLoadLeaves(month, year, payrollAjaxHandlerAction.getOrgId());
            //  System.out.println("checkLoadLeavesExists" + checkLoadLeavesExists);
            // System.out.println("exists" + exists);
            if (checkLoadLeavesExists) {
                if (!exists) {
                    connection = ConnectionProvider.getInstance().getConnection();
                    callableStatement = connection.prepareCall("{call spAddEmpWages1(?,?,?,?,?,?,?,?,?,?,?,?) }");

                    callableStatement.setInt(1, month);
                    callableStatement.setInt(2, year);
                    callableStatement.setInt(3, payrollAjaxHandlerAction.getNoOfDays());
                    callableStatement.setInt(4, payrollAjaxHandlerAction.getNoOfWeekedDays());
                    //  callableStatement.setInt(5, payrollAjaxHandlerAction.getNoOfHolidays());
                    callableStatement.setInt(5, payrollAjaxHandlerAction.getWorkingDays());
                    callableStatement.setString(6, payrollAjaxHandlerAction.getCreatedBy());


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
                    callableStatement.setString(9, com.mss.mirage.util.DateUtility.getInstance().convertStringToMySQLDate(payrollAjaxHandlerAction.getPaymentDateEmp()));
                    callableStatement.setInt(10, payrollAjaxHandlerAction.getOrgId());
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

    public String doFreezeWages(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        Connection connection1 = null;
        PreparedStatement prestatement1 = null;
        ResultSet resultSet1 = null;

        String resultMessage = "";
         String queryString = "";
      
        String queryString2 = "";
         boolean flag = false;
        int updatedrows = 0;
        int empId = 0;
        int curMonth = 0;
        int curYear = 0;
      
        if(payrollAjaxHandlerAction.getEmpnameById() !=null && !"nun".equals(payrollAjaxHandlerAction.getEmpnameById())){
                  
                empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(payrollAjaxHandlerAction.getEmpnameById());
               //   System.out.println("emoName empId----->"+empId);
                }
           else if(payrollAjaxHandlerAction.getEmpNo()!=null && !"nun".equals(payrollAjaxHandlerAction.getEmpNo()) ){
                empId =  DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(payrollAjaxHandlerAction.getEmpNo()));
             //   System.out.println("empId----->"+empId);
            }
       
      
       
       // System.out.println("empId 0----->"+empId);
        
         queryString = "SELECT Freeze_Payroll FROM tblEmpWages WHERE MONTH(PayrollDate) = " + month + " AND YEAR(PayrollDate)='" + year+ "'";;
         if(empId != 0){
           queryString = queryString + " AND EmpId='" + empId+ "'";
       }
        if(payrollAjaxHandlerAction.getOrgId() != 0){
           queryString = queryString +" AND OrgId='"+payrollAjaxHandlerAction.getOrgId()+ "'";
         }       
        
                 
       
       
     //  System.out.println("query-->" + queryString);
        try {
            
            resultMessage = "<font style='color:red;'>Records doesnt exists for the given month and year..</font>";
       
            connection = ConnectionProvider.getInstance().getConnection();
            prestatement = connection.prepareStatement(queryString);
            resultSet = prestatement.executeQuery();
  
    
            if (resultSet.next()) {
                //  System.out.println("in forst if");

                queryString2 = "UPDATE `mirage`.`tblEmpWages` SET `Freeze_Payroll` = 1,FreezeAppliedBy='" + payrollAjaxHandlerAction.getUserId() + "',FreezeAppliedDate='" + DateUtility.getInstance().getCurrentMySqlDateTime() + "'  WHERE MONTH(PayrollDate) = " + month + " AND YEAR(PayrollDate)=" + year + " AND Freeze_Payroll != 1";
                   if(payrollAjaxHandlerAction.getOrgId() != 0){
           queryString2 = queryString2 +" AND OrgId='"+payrollAjaxHandlerAction.getOrgId()+ "'";
         }  
             if(empId != 0){
           queryString2 = queryString2 + " AND EmpId='" + empId+ "'";; 
       }
            
             //   System.out.println("query2-->" + queryString2); 
                connection1 = ConnectionProvider.getInstance().getConnection();
                prestatement1 = connection1.prepareStatement(queryString2);
                updatedrows = prestatement1.executeUpdate();
                // System.out.println("in second if updatedrows--->" + updatedrows);
                if (updatedrows > 0) {
                    resultMessage = "<font style='color:green;'>Freezed Succesfully</font>";
                } else {
                    resultMessage = "<font style='color:red;'>Records already freezed</font>";
                }
            }



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
                if (prestatement != null) {
                    prestatement.close();
                    prestatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (prestatement1 != null) {
                    prestatement1.close();
                    prestatement1 = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultMessage;

    }

    public String doUpdateYearAndDate(String payrollid, int wageId, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        String resultMessage = "";
        int updatedrows = 0;

        try {

            String queryString = "UPDATE `mirage`.`tblEmpWages` "
                    + "SET "
                    + " `YTD_Gross` = " + payrollAjaxHandlerAction.getYtdGross() + " , `YTD_LongTerm` = " + payrollAjaxHandlerAction.getYtdLongterm() + " , `YTD_PF` = " + payrollAjaxHandlerAction.getYtdPf() + " , `YTD_PTax` = " + payrollAjaxHandlerAction.getYtdProffTax() + " , `YTD_Life` = " + payrollAjaxHandlerAction.getYtdLifeInsurance() + " , "
                    + " `YTD_TA` =" + payrollAjaxHandlerAction.getYtdTa() + ", `YTD_RA` = " + payrollAjaxHandlerAction.getYtdRa() + " , `Ytd_ExpTaxFree` =" + payrollAjaxHandlerAction.getYtdExpTaxFree() + " , `YTD_Others` = " + payrollAjaxHandlerAction.getYtdOthersMisc() + " , `YTD_Projectpay` = " + payrollAjaxHandlerAction.getYtdProjectPay() + " , "
                    + " `YTD_Savings1` =" + payrollAjaxHandlerAction.getYtdSavings1Reported() + " , `YTD_Savings2` = " + payrollAjaxHandlerAction.getYtdSavings2Reported() + " , `YTD_TaxableIncome` = " + payrollAjaxHandlerAction.getYtdTaxableSalary() + " , `YTD_Comissions` = " + payrollAjaxHandlerAction.getYtdTaxableCommission() + " , `YTD_TDS_Salary` = " + payrollAjaxHandlerAction.getYtdTDSonSalary() + " , "
                    + " `YTD_TDS_Comissions` = " + payrollAjaxHandlerAction.getYtdTDSOnCommm() + " , `YTD_TDS_Collected` = " + payrollAjaxHandlerAction.getYtdTDSCollected() + " , `Tax_Liability` = " + payrollAjaxHandlerAction.getYtdTDSLiabilitiesSalary() + " , `Tax_Liability_Bonus` = " + payrollAjaxHandlerAction.getYtdTDSLiabilitiesBonus() + " , "
                    + " `Diff_tax_salary` = " + payrollAjaxHandlerAction.getDiffTDSLiabilitiesSalary() + " , `Diff_tax_bonus` = " + payrollAjaxHandlerAction.getDiffTDSLiabilitiesBonus() + " , `ModifiedBy` = '" + payrollAjaxHandlerAction.getUserId() + "' , `ModifiedDate` = '" + DateUtility.getInstance().getCurrentMySqlDateTime() + "'  "
                    + " WHERE `Wag_Id` =  " + wageId;
            //  System.out.println(queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            prestatement = connection.prepareStatement(queryString);
            updatedrows = prestatement.executeUpdate();
            // System.out.println("in second if updatedrows--->" + updatedrows);
            if (updatedrows > 0) {
                resultMessage = "<font style='color:green;'>Year and Date details updated Succesfully</font>";
            } //further statements
            else {
                resultMessage = "<font style='color:red;'>Please try again later</font>";
            }





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
                if (prestatement != null) {
                    prestatement.close();
                    prestatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultMessage;

    }

    public String freezeEmpWageDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        String resultMessage = "";
        int updatedrows = 0;

        try {

            String queryString = "UPDATE `mirage`.`tblEmpWages` set Freeze_Payroll = 1,FreezeModifiedBy='" + payrollAjaxHandlerAction.getUserId() + "',FreezeModifiedDate='" + DateUtility.getInstance().getCurrentMySqlDateTime() + "' WHERE `Wag_Id` =  " + payrollAjaxHandlerAction.getWageId();
            //   System.out.println(queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            prestatement = connection.prepareStatement(queryString);
            updatedrows = prestatement.executeUpdate();
            //  System.out.println("in second if updatedrows--->" + updatedrows);
            if (updatedrows > 0) {
                resultMessage = "Freezed Succesfully";
            } //further statements
            else {
                resultMessage = "Please try again later";
            }

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
                if (prestatement != null) {
                    prestatement.close();
                    prestatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultMessage;

    }

    public String unfreezeEmpWageDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        String resultMessage = "";
        int updatedrows = 0;

        try {

            String queryString = "UPDATE `mirage`.`tblEmpWages` set Freeze_Payroll = 0,FreezeModifiedBy='" + payrollAjaxHandlerAction.getUserId() + "',FreezeModifiedDate='" + DateUtility.getInstance().getCurrentMySqlDateTime() + "' WHERE `Wag_Id` =  " + payrollAjaxHandlerAction.getWageId();
            //  System.out.println(queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            prestatement = connection.prepareStatement(queryString);
            updatedrows = prestatement.executeUpdate();
            // System.out.println("in second if updatedrows--->" + updatedrows);
            if (updatedrows > 0) {
                resultMessage = "Unfreezed Succesfully";
            } //further statements
            else {
                resultMessage = "Please try again later";
            }





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
                if (prestatement != null) {
                    prestatement.close();
                    prestatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultMessage;

    }

    public String getDaysCountDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        String result = "";
        /* queryString = "SELECT 	`Wag_Id`, `PayRoll_Id`, `DaysInMonth`, `DaysHolidays`, `Daysweekends`, "
         + "(CASE WHEN (MONTH(`CreateDate`) = '1') THEN '12' ELSE MONTH(`CreateDate`)-1 END) AS `MONTH`, "
         + "(CASE WHEN (MONTH(`CreateDate`) = '1') THEN YEAR(`CreateDate`)-1 ELSE YEAR(`CreateDate`) END) AS `YEAR`, "
         + "`EmpId`, `DaysVacationfromHubble`,DaysVacationFromBiometric FROM `mirage`.`tblEmpWages` WHERE Wag_Id =" + payrollAjaxHandlerAction.getWageId() + " AND PayRoll_Id =" + payrollAjaxHandlerAction.getPayRollId();*/
        queryString = "SELECT 	`Wag_Id`, `PayRoll_Id`, `DaysInMonth`, `DaysHolidays`, `Daysweekends`, "
                + " MONTH(`PayrollDate`) AS `MONTH`, "
                + "YEAR(`PayrollDate`) AS `YEAR`, "
                + "`EmpId`, `DaysVacationfromHubble`,DaysVacationFromBiometric FROM `mirage`.`tblEmpWages` WHERE Wag_Id =" + payrollAjaxHandlerAction.getWageId() + " AND PayRoll_Id =" + payrollAjaxHandlerAction.getPayRollId();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            prestatement = connection.prepareStatement(queryString);
            resultSet = prestatement.executeQuery();

            while (resultSet.next()) {
                result = resultSet.getInt("Wag_Id") + "|" + resultSet.getInt("PayRoll_Id") + "|" + resultSet.getInt("DaysInMonth") + "|" + resultSet.getInt("DaysHolidays") + "|" + resultSet.getInt("Daysweekends") + "|" + resultSet.getInt("MONTH") + "|" + resultSet.getInt("YEAR") + "|" + resultSet.getInt("EmpId") + "|" + resultSet.getInt("DaysVacationfromHubble") + "|" + resultSet.getInt("DaysVacationFromBiometric");
            }

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
                if (prestatement != null) {
                    prestatement.close();
                    prestatement = null;
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

    public String updateDaysCount(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        String resultMessage = "";
        int updatedrows = 0;

        try {

            String queryString = "UPDATE `mirage`.`tblEmpWages` set DaysInMonth = " + payrollAjaxHandlerAction.getNoOfDays() + ",DaysHolidays=" + payrollAjaxHandlerAction.getNoOfHolidays() + ",Daysweekends=" + payrollAjaxHandlerAction.getNoOfWeekedDays() + ",DaysVacationfromHubble=" + payrollAjaxHandlerAction.getLeavesCount() + ", DaysVacationFromBiometric=" + payrollAjaxHandlerAction.getDaysVactaionFromBiometric() + "  WHERE `Wag_Id` =  " + payrollAjaxHandlerAction.getWageId();
            //System.out.println(queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            prestatement = connection.prepareStatement(queryString);
            updatedrows = prestatement.executeUpdate();
            // System.out.println("in second if updatedrows--->" + updatedrows);
            if (updatedrows > 0) {
                resultMessage = "<font style='color:green;font-size:15px;'>Days count Updated Succesfully</font>";
            } else {
                resultMessage = "<font style='color:red;font-size:15px;'>Please try again later</font>";
            }

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
                if (prestatement != null) {
                    prestatement.close();
                    prestatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultMessage;

    }

    public String setEmpWageDetails(String resultString, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {


        try {
            
 JSONObject wageData = new JSONObject(resultString);
 
            payrollAjaxHandlerAction.setPaymentType(wageData.getString("paymentType"));
            payrollAjaxHandlerAction.setPayPeriodStartDate(wageData.getString("payPeriodStartDate"));
            payrollAjaxHandlerAction.setBankName(wageData.getString("bankName"));
            payrollAjaxHandlerAction.setDaysInMonth(wageData.getString("daysInMonth"));
            payrollAjaxHandlerAction.setBankAccountNo(wageData.getString("bankAccountNo"));
            payrollAjaxHandlerAction.setDaysWorked(wageData.getString("daysWorked"));
            payrollAjaxHandlerAction.setFreezePayroll(Integer.parseInt(wageData.getString("freezePayroll")));
            payrollAjaxHandlerAction.setPayrollDate(wageData.getString("payrollDate"));
            payrollAjaxHandlerAction.setPayRunId(wageData.getString("payRunId"));
            payrollAjaxHandlerAction.setNetPaid(Double.parseDouble(wageData.getString("netPaid")));
            payrollAjaxHandlerAction.setEmployerId(wageData.getString("employerId"));
            payrollAjaxHandlerAction.setGrossPay(wageData.getString("grossPay"));
            payrollAjaxHandlerAction.setDedEmpPf(Double.parseDouble(wageData.getString("dedEmpPf")));
            payrollAjaxHandlerAction.setTds(Double.parseDouble(wageData.getString("tds")));
            payrollAjaxHandlerAction.setDedProfessionalTax(Double.parseDouble(wageData.getString("dedProfessionalTax")));
            payrollAjaxHandlerAction.setDaysProject(wageData.getString("daysProject"));
            payrollAjaxHandlerAction.setDedIncomeTax(Double.parseDouble(wageData.getString("dedIncomeTax")));
            payrollAjaxHandlerAction.setDaysVacation(wageData.getString("daysVacation"));
            payrollAjaxHandlerAction.setDedCorporateLoan(Double.parseDouble(wageData.getString("dedCorporateLoan")));
            payrollAjaxHandlerAction.setVactionsAvailable(wageData.getString("vactionsAvailable"));
            payrollAjaxHandlerAction.setDedLife(Double.parseDouble(wageData.getString("dedLife")));
            payrollAjaxHandlerAction.setDaysHolidays(wageData.getString("daysHolidays"));
            payrollAjaxHandlerAction.setDedHealth(Double.parseDouble(wageData.getString("dedHealth")));
            payrollAjaxHandlerAction.setDaysWeekends(wageData.getString("daysWeekends"));
            payrollAjaxHandlerAction.setDedOthers(Double.parseDouble(wageData.getString("dedOthers")));
            payrollAjaxHandlerAction.setBasic(wageData.getString("basic"));
            payrollAjaxHandlerAction.setMaidServices(Double.parseDouble(wageData.getString("maidServices")));
            payrollAjaxHandlerAction.setDa(wageData.getString("da"));
            payrollAjaxHandlerAction.setEntertainment(wageData.getString("entertainment"));
            payrollAjaxHandlerAction.setHra(wageData.getString("hra"));
            payrollAjaxHandlerAction.setKidsEducation(wageData.getString("kidsEducation"));
            payrollAjaxHandlerAction.setTa(wageData.getString("ta"));
            payrollAjaxHandlerAction.setVehicleAllowance(wageData.getString("vehicleAllowance"));
            payrollAjaxHandlerAction.setRa(wageData.getString("ra"));
            payrollAjaxHandlerAction.setLongTermBonus(wageData.getString("longTermBonus"));
            payrollAjaxHandlerAction.setLife(wageData.getString("life"));
            payrollAjaxHandlerAction.setEmployerPf(wageData.getString("employerPf"));
            payrollAjaxHandlerAction.setHealth(wageData.getString("health"));
            payrollAjaxHandlerAction.setSplAllowance(wageData.getString("splAllowance"));
            payrollAjaxHandlerAction.setCca(wageData.getString("cca"));
            payrollAjaxHandlerAction.setAttendanceAllow(wageData.getString("attendanceAllow"));
            payrollAjaxHandlerAction.setMiscPay(wageData.getString("miscPay"));
            payrollAjaxHandlerAction.setProjectPay(wageData.getString("projectPay"));
            payrollAjaxHandlerAction.setEmployeePfPayRollDetails(Double.parseDouble(wageData.getString("employeePfPayRollDetails")));
            payrollAjaxHandlerAction.setClassificationId(wageData.getString("classificationId"));
            payrollAjaxHandlerAction.setGrossPayPayRollDetails(Double.parseDouble(wageData.getString("grossPayPayRollDetails")));
            payrollAjaxHandlerAction.setVariablePay(wageData.getString("variablePay"));
            payrollAjaxHandlerAction.setEarnedBasic(Double.parseDouble(wageData.getString("earnedBasic")));
            payrollAjaxHandlerAction.setEarnedFood(Double.parseDouble(wageData.getString("earnedFood")));
            payrollAjaxHandlerAction.setEarnedDa(Double.parseDouble(wageData.getString("earnedDa")));
            payrollAjaxHandlerAction.setEarnedLaundary(Double.parseDouble(wageData.getString("earnedLaundary")));
            payrollAjaxHandlerAction.setEarnedHra(Double.parseDouble(wageData.getString("earnedHra")));
            payrollAjaxHandlerAction.setEarnedMaidServices(Double.parseDouble(wageData.getString("earnedMaidServices")));
            payrollAjaxHandlerAction.setEarnedTa(Double.parseDouble(wageData.getString("earnedTa")));
            payrollAjaxHandlerAction.setEarnedEntertainment(Double.parseDouble(wageData.getString("earnedEntertainment")));
            payrollAjaxHandlerAction.setEarnedRa(Double.parseDouble(wageData.getString("earnedRa")));
            payrollAjaxHandlerAction.setEarnedKidsEducation(Double.parseDouble(wageData.getString("earnedKidsEducation")));
            payrollAjaxHandlerAction.setEarnedLife(Double.parseDouble(wageData.getString("earnedLife")));
            payrollAjaxHandlerAction.setEarnedVehicleAllowance(Double.parseDouble(wageData.getString("earnedVehicleAllowance")));
            payrollAjaxHandlerAction.setEarnedHealth(Double.parseDouble(wageData.getString("earnedHealth")));
            payrollAjaxHandlerAction.setEarnedLongTermBonus(Double.parseDouble(wageData.getString("earnedLongTermBonus")));
            payrollAjaxHandlerAction.setEarnedCCa(Double.parseDouble(wageData.getString("earnedCCa")));
            payrollAjaxHandlerAction.setEarnedMiscPay(Double.parseDouble(wageData.getString("earnedMiscPay")));
            payrollAjaxHandlerAction.setEarnedProjectPay(Double.parseDouble(wageData.getString("earnedProjectPay")));
            payrollAjaxHandlerAction.setEarnedEmployerPf(Double.parseDouble(wageData.getString("earnedEmployerPf")));
            payrollAjaxHandlerAction.setEarnedattallowance(Double.parseDouble(wageData.getString("earnedattallowance")));
            payrollAjaxHandlerAction.setEarnedsplallowance(Double.parseDouble(wageData.getString("earnedsplallowance")));
            payrollAjaxHandlerAction.setTdsDeduction(Double.parseDouble(wageData.getString("tdsDeduction")));
            payrollAjaxHandlerAction.setEmployeePfActualDetails(Double.parseDouble(wageData.getString("employeePfActualDetails")));
            payrollAjaxHandlerAction.setGrossPayActualDetails(Double.parseDouble(wageData.getString("grossPayActualDetails")));
            payrollAjaxHandlerAction.setBonusCommission(Double.parseDouble(wageData.getString("bonusCommission")));
            payrollAjaxHandlerAction.setNetPaidActualDetails(Double.parseDouble(wageData.getString("netPaidActualDetails")));
            payrollAjaxHandlerAction.setOtherDeductions(wageData.getString("otherDeductions"));
            payrollAjaxHandlerAction.setTaxableIncome(Double.parseDouble(wageData.getString("taxableIncome")));
            payrollAjaxHandlerAction.setOtherAdditions(Double.parseDouble(wageData.getString("otherAdditions")));
            payrollAjaxHandlerAction.setYtdGross(Double.parseDouble(wageData.getString("ytdGross")));
            payrollAjaxHandlerAction.setYtdTaxableSalary(Double.parseDouble(wageData.getString("ytdTaxableSalary")));
            payrollAjaxHandlerAction.setYtdLongterm(Double.parseDouble(wageData.getString("ytdLongterm")));
            payrollAjaxHandlerAction.setYtdTaxableCommission(Double.parseDouble(wageData.getString("ytdTaxableCommission")));
            payrollAjaxHandlerAction.setYtdPf(Double.parseDouble(wageData.getString("ytdPf")));
            payrollAjaxHandlerAction.setYtdTDSonSalary(Double.parseDouble(wageData.getString("ytdTDSonSalary")));
            payrollAjaxHandlerAction.setYtdProffTax(Double.parseDouble(wageData.getString("ytdProffTax")));
            payrollAjaxHandlerAction.setYtdTDSOnCommm(Double.parseDouble(wageData.getString("ytdTDSOnCommm")));
            payrollAjaxHandlerAction.setYtdLifeInsurance(Double.parseDouble(wageData.getString("ytdLifeInsurance")));
            payrollAjaxHandlerAction.setYtdTDSCollected(Double.parseDouble(wageData.getString("ytdTDSCollected")));
            payrollAjaxHandlerAction.setYtdTa(Double.parseDouble(wageData.getString("ytdTa")));
            payrollAjaxHandlerAction.setYtdTDSLiabilitiesSalary(Double.parseDouble(wageData.getString("ytdTDSLiabilitiesSalary")));
            payrollAjaxHandlerAction.setYtdRa(Double.parseDouble(wageData.getString("ytdRa")));
            payrollAjaxHandlerAction.setYtdTDSLiabilitiesBonus(Double.parseDouble(wageData.getString("ytdTDSLiabilitiesBonus")));
            payrollAjaxHandlerAction.setYtdOthersMisc(Double.parseDouble(wageData.getString("ytdOthersMisc")));
            payrollAjaxHandlerAction.setDiffTDSLiabilitiesSalary(Double.parseDouble(wageData.getString("diffTDSLiabilitiesSalary")));
            payrollAjaxHandlerAction.setYtdExpTaxFree(Double.parseDouble(wageData.getString("ytdExpTaxFree")));
            payrollAjaxHandlerAction.setDiffTDSLiabilitiesBonus(Double.parseDouble(wageData.getString("diffTDSLiabilitiesBonus")));
            payrollAjaxHandlerAction.setYtdProjectPay(Double.parseDouble(wageData.getString("ytdProjectPay")));
            payrollAjaxHandlerAction.setYtdSavings1Reported(Double.parseDouble(wageData.getString("ytdSavings1Reported")));
            payrollAjaxHandlerAction.setYtdSavings2Reported(Double.parseDouble(wageData.getString("ytdSavings2Reported")));
            payrollAjaxHandlerAction.setIsBlock(Integer.parseInt(wageData.getString("isBlockVal")));
            payrollAjaxHandlerAction.setProjectEndDate(wageData.getString("projectEndDate"));
            if (!"nun".equals(wageData.getString("repaymentComments"))) {
                payrollAjaxHandlerAction.setRepaymentComments(wageData.getString("repaymentComments"));
            } else {
                payrollAjaxHandlerAction.setRepaymentComments("");
            }
            payrollAjaxHandlerAction.setRepaymentGross(Double.parseDouble(wageData.getString("repaymentGross")));
            payrollAjaxHandlerAction.setRepaymentNet(Double.parseDouble(wageData.getString("repaymentNet")));
            payrollAjaxHandlerAction.setRepaymentVariablePay(Double.parseDouble(wageData.getString("repaymentVariablePay")));
            payrollAjaxHandlerAction.setDoRepaymentFlag(Integer.parseInt(wageData.getString("doRepaymentFlagValue")));



            /* payrollAjaxHandlerAction.setLifeInsurancePremium(resultString.split(",")[103].split(":")[1]);
             payrollAjaxHandlerAction.setHousingLoanRepay(resultString.split(",")[104].split(":")[1]);
             payrollAjaxHandlerAction.setNscTds(resultString.split(",")[105].split(":")[1]);
             payrollAjaxHandlerAction.setPpfContribution(resultString.split(",")[106].split(":")[1]);
             payrollAjaxHandlerAction.setTutionFee(resultString.split(",")[107].split(":")[1]);
             payrollAjaxHandlerAction.setElss(resultString.split(",")[108].split(":")[1]);
             payrollAjaxHandlerAction.setPostOfficeTerm(resultString.split(",")[109].split(":")[1]);
             payrollAjaxHandlerAction.setBankDepositTax(resultString.split(",")[110].split(":")[1]);
             payrollAjaxHandlerAction.setOthersTDS(resultString.split(",")[111].split(":")[1]);
             payrollAjaxHandlerAction.setContributionToPf(resultString.split(",")[112].split(":")[1]);
             payrollAjaxHandlerAction.setNpsEmployeeContr(resultString.split(",")[113].split(":")[1]);
             payrollAjaxHandlerAction.setTotalTds(resultString.split(",")[114].split(":")[1]);
             payrollAjaxHandlerAction.setTotalTdsDeductable(resultString.split(",")[115].split(":")[1]);
             payrollAjaxHandlerAction.setInterstOnBorrowed(resultString.split(",")[116].split(":")[1]);
             payrollAjaxHandlerAction.setInterstOnBorrowedDeductable(resultString.split(",")[117].split(":")[1]);
             payrollAjaxHandlerAction.setInsuranceForParents(resultString.split(",")[118].split(":")[1]);
             payrollAjaxHandlerAction.setInsuranceForParentsDeduc(resultString.split(",")[119].split(":")[1]);
             payrollAjaxHandlerAction.setInsuranceOthers(resultString.split(",")[120].split(":")[1]);
             payrollAjaxHandlerAction.setInsuranceOthersDeduc(resultString.split(",")[121].split(":")[1]);
             payrollAjaxHandlerAction.setInterstOnEdu(resultString.split(",")[122].split(":")[1]);
             payrollAjaxHandlerAction.setInterstOnHrAssumptions(resultString.split(",")[123].split(":")[1]);
             payrollAjaxHandlerAction.setInterstOnHrAssumptionsInv(resultString.split(",")[124].split(":")[1]);
             payrollAjaxHandlerAction.setExpectedYearlyCost(resultString.split(",")[125].split(":")[1]);
             payrollAjaxHandlerAction.setLicFromSal(resultString.split(",")[126].split(":")[1]);*/
            payrollAjaxHandlerAction.setLeavesApplied(wageData.getString("leavesApplied"));
            payrollAjaxHandlerAction.setWageId(Integer.parseInt(wageData.getString("wageId")));
            payrollAjaxHandlerAction.setPayRollId(wageData.getString("payRollId"));
            payrollAjaxHandlerAction.setTdsId(Integer.parseInt(wageData.getString("tdsId")));
            payrollAjaxHandlerAction.setEmpId(wageData.getString("empId"));
            payrollAjaxHandlerAction.setNewGrossPay(Double.parseDouble(wageData.getString("newGrossPay")));
            payrollAjaxHandlerAction.setReleasedDate((wageData.getString("releasedDate")));



        } catch (Exception e) {
            e.printStackTrace();
        }


        //System.out.println("the campaign name from dsdp=" +campaignName);
        return null;

    }

    /*  public String updateAllEmpWageDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {
    
     Connection connection = null;
     PreparedStatement prestatement = null;
     ResultSet resultSet = null;
     String resultMessage = "";
     int updatedrows = 0;
    
     try {
    
     String queryString = "UPDATE `mirage`.`tblEmpWages` SET "
     + " `PaymentTypeId` = " + payrollAjaxHandlerAction.getPaymentType() + " ,  "
     + " `Freeze_Payroll` = " + payrollAjaxHandlerAction.getFreezePayroll() + " , `PayrollDate` = '" + DateUtility.getInstance().strToTimeStampObj(payrollAjaxHandlerAction.getPayrollDate()) + "' , 	`NetPaid` = " + payrollAjaxHandlerAction.getNetPaid() + " , 	`GrossPay` = " + payrollAjaxHandlerAction.getGrossPay() + " ,  "
     + " `TDS` = " + payrollAjaxHandlerAction.getTds() + " , 	`DaysInMonth` = " + payrollAjaxHandlerAction.getDaysInMonth() + " , 	`DaysWorked` = " + payrollAjaxHandlerAction.getDaysWorked() + " , 	`DaysProject` = " + payrollAjaxHandlerAction.getDaysProject() + " ,  "
     + " `DaysVacation` = " + payrollAjaxHandlerAction.getDaysVacation() + " , 	`LeavesAvailable` = " + payrollAjaxHandlerAction.getVactionsAvailable() + " , 	`DaysHolidays` = " + payrollAjaxHandlerAction.getDaysHolidays() + " , 	`Daysweekends` = " + payrollAjaxHandlerAction.getDaysWeekends() + " ,  "
     + " `Ded_Employee_PF` = " + payrollAjaxHandlerAction.getDedEmpPf() + " , 	`Ded_Professional_Tax` = " + payrollAjaxHandlerAction.getDedProfessionalTax() + " , `Ded_Income_Tax` = " + payrollAjaxHandlerAction.getDedIncomeTax() + " , 	`Ded_Corporate_Loan` = " + payrollAjaxHandlerAction.getDedCorporateLoan() + " ,  "
     + " `Ded_Life` = " + payrollAjaxHandlerAction.getDedLife() + " , 	`Ded_Health` = " + payrollAjaxHandlerAction.getDedHealth() + " , 	`Ded_Others` = " + payrollAjaxHandlerAction.getDedOthers() + " , 	`BasicPay1` = " + payrollAjaxHandlerAction.getBasic() + " , 	`DA` = " + payrollAjaxHandlerAction.getDa() + " ,  "
     + " `HRA` = " + payrollAjaxHandlerAction.getHra() + " , 	`TA` = " + payrollAjaxHandlerAction.getTa() + " , 	`RA` = " + payrollAjaxHandlerAction.getRa() + " , 	`Life` = " + payrollAjaxHandlerAction.getLife() + " , 	`Health` = " + payrollAjaxHandlerAction.getHealth() + " , 	`AttAllowance` = " + payrollAjaxHandlerAction.getAttendanceAllow() + " ,  "
     + " `MaidServices` = " + payrollAjaxHandlerAction.getMaidServices() + " , 	`Entertainment` = " + payrollAjaxHandlerAction.getEntertainment() + " , 	`KidsEducation` = " + payrollAjaxHandlerAction.getKidsEducation() + " , 	`VehcleAllowance` = " + payrollAjaxHandlerAction.getVehicleAllowance() + " ,  "
     + " `LongTermBonus` =" + payrollAjaxHandlerAction.getLongTermBonus() + ", 	`Employer_PF` =" + payrollAjaxHandlerAction.getEmployerPf() + " , 	`Employee_PF` = " + payrollAjaxHandlerAction.getEmployeePfPayRollDetails() + " , 	`CCA1` = " + payrollAjaxHandlerAction.getCca() + " , 	`SplAllowance` = " + payrollAjaxHandlerAction.getSplAllowance() + ",  "
     + " `MiscPay` = " + payrollAjaxHandlerAction.getMiscPay() + " , 	`VariablePay` = " + payrollAjaxHandlerAction.getVariablePay() + " , 	`Earned_Basic` = " + payrollAjaxHandlerAction.getEarnedBasic() + " , 	`Earned_DA` = " + payrollAjaxHandlerAction.getEarnedDa() + " , 	`Earned_HRA` = " + payrollAjaxHandlerAction.getEarnedHra() + " ,  "
     + " `Earned_TA` = " + payrollAjaxHandlerAction.getEarnedTa() + " , 	`Earned_RA` = " + payrollAjaxHandlerAction.getEarnedRa() + " , 	`Earned_Life` = " + payrollAjaxHandlerAction.getEarnedLife() + " , 	`Earned_Health` = " + payrollAjaxHandlerAction.getEarnedHealth() + " , 	`Earned_CCA` = " + payrollAjaxHandlerAction.getEarnedCCa() + " ,  "
     + " `Earned_ProjectPay` = " + payrollAjaxHandlerAction.getEarnedProjectPay() + " , 	`Earned_Allowance` = " + payrollAjaxHandlerAction.getEarnedattallowance() + " , 	`Ded_TDS_Bonus` = " + payrollAjaxHandlerAction.getTdsDeduction() + " , 	`TaxableIncome` = " + payrollAjaxHandlerAction.getTaxableIncome() + " ,  "
     + " `Earned_Food` = " + payrollAjaxHandlerAction.getEarnedFood() + " , 	`Earned_Laundry` = " + payrollAjaxHandlerAction.getEarnedLaundary() + " , 	`Earned_MaidServices` = " + payrollAjaxHandlerAction.getEarnedMaidServices() + " , 	`Earned_Entertainment` = " + payrollAjaxHandlerAction.getEarnedEntertainment() + " ,  "
     + " `Earned_KidsEducation` = " + payrollAjaxHandlerAction.getEarnedKidsEducation() + " , 	`Earned_VehicleAllowance` = " + payrollAjaxHandlerAction.getEarnedVehicleAllowance() + " , 	`Earned_LongTermBonus` = " + payrollAjaxHandlerAction.getEarnedLongTermBonus() + " , 	`Earned_MiscPay` = " + payrollAjaxHandlerAction.getEarnedMiscPay() + " ,  "
     + " `Earned_SplAllowance` = " + payrollAjaxHandlerAction.getEarnedsplallowance() + " , 	`Earned_Employeer_Pf` = " + payrollAjaxHandlerAction.getEarnedEmployerPf() + " , 	`YTD_Gross` = " + payrollAjaxHandlerAction.getYtdGross() + " ,  "
     + " `YTD_LongTerm` = " + payrollAjaxHandlerAction.getYtdLongterm() + " , 	`YTD_PF` = " + payrollAjaxHandlerAction.getYtdPf() + " , 	`YTD_PTax` = " + payrollAjaxHandlerAction.getYtdProffTax() + " , 	`YTD_Life` = " + payrollAjaxHandlerAction.getYtdLifeInsurance() + " , 	`YTD_TA` = " + payrollAjaxHandlerAction.getYtdTa() + " , 	`YTD_RA` = " + payrollAjaxHandlerAction.getYtdRa() + " ,  "
     + " `YTD_EmpOthers` = " + payrollAjaxHandlerAction.getYtdOthersMisc() + " , 	`YTD_Others` = " + payrollAjaxHandlerAction.getOtherDeductions() + " , 	`YTD_Projectpay` = " + payrollAjaxHandlerAction.getYtdProjectPay() + " , 	`YTD_Savings1` = " + payrollAjaxHandlerAction.getYtdSavings1Reported() + " , 	`YTD_Savings2` = " + payrollAjaxHandlerAction.getYtdSavings2Reported() + " ,  "
     + " `YTD_TaxableIncome` = " + payrollAjaxHandlerAction.getYtdTaxableSalary() + " , 	`YTD_Comissions` = " + payrollAjaxHandlerAction.getYtdTaxableCommission() + " , 	`YTD_TDS_Salary` = " + payrollAjaxHandlerAction.getYtdTDSonSalary() + " , 	`YTD_TDS_Comissions` = " + payrollAjaxHandlerAction.getYtdTDSOnCommm() + " , 	`YTD_TDS_Collected` = " + payrollAjaxHandlerAction.getYtdTDSCollected() + " ,  "
     + " `Tax_Liability` = " + payrollAjaxHandlerAction.getYtdTDSLiabilitiesSalary() + " , 	`Tax_Liability_Bonus` = " + payrollAjaxHandlerAction.getYtdTDSLiabilitiesBonus() + " , 	`Diff_tax_salary` = " + payrollAjaxHandlerAction.getDiffTDSLiabilitiesSalary() + " , 	`Diff_tax_bonus` = " + payrollAjaxHandlerAction.getDiffTDSLiabilitiesBonus() + " ,  "
     + " `ModifiedBy` = '" + payrollAjaxHandlerAction.getUserId() + "' , 	`ModifiedDate` = '" + DateUtility.getInstance().getCurrentMySqlDateTime() + "',`ProjectPay` = " + payrollAjaxHandlerAction.getProjectPay() + ",`IsBlock` =" + payrollAjaxHandlerAction.getIsBlock() + ",Earned_Gross_Pay =  " + payrollAjaxHandlerAction.getGrossPayActualDetails() + ", Earned_Net_Paid=" + payrollAjaxHandlerAction.getNetPaidActualDetails() + ",ProjectEndDate='" + DateUtility.getInstance().getMysqlDate(payrollAjaxHandlerAction.getProjectEndDate()) + "'  "
     + " WHERE	`Wag_Id` = " + payrollAjaxHandlerAction.getWageId() + " ";
     System.out.println(queryString);
     connection = ConnectionProvider.getInstance().getConnection();
     prestatement = connection.prepareStatement(queryString);
     updatedrows = prestatement.executeUpdate();
     System.out.println("in second if updatedrows--->" + updatedrows);
     if (updatedrows > 0) {
     resultMessage = "<font style='color:green;font-size:15px;'>Wages Updated Succesfully</font>";
     } else {
     resultMessage = "<font style='color:red;font-size:15px;'>Please try again later</font>";
     }
    
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
     if (prestatement != null) {
     prestatement.close();
     prestatement = null;
     }
     if (connection != null) {
     connection.close();
     connection = null;
     }
    
     } catch (SQLException ex) {
     ex.printStackTrace();
     }
     }
     return resultMessage;
    
     }
     */
    public String updateAllEmpWageDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement prestatement = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String resultMessage = "";
        int updatedrows = 0;

        try {
            // System.out.println("test");
            DateUtility dateUtil = DateUtility.getInstance();
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spUpdateEmpWagesBKP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            //  System.out.println(queryString);
            //  connection = ConnectionProvider.getInstance().getConnection();
            //  prestatement = connection.prepareStatement(queryString);
            callableStatement.setInt(1, Integer.parseInt(payrollAjaxHandlerAction.getPaymentType()));
            callableStatement.setInt(2, payrollAjaxHandlerAction.getFreezePayroll());
            callableStatement.setTimestamp(3, DateUtility.getInstance().strToTimeStampObj(payrollAjaxHandlerAction.getPayrollDate()));
            callableStatement.setDouble(4, payrollAjaxHandlerAction.getNetPaid());
            callableStatement.setDouble(5, Double.parseDouble(payrollAjaxHandlerAction.getGrossPay()));
            callableStatement.setDouble(6, payrollAjaxHandlerAction.getTds());
            callableStatement.setInt(7, Integer.parseInt(payrollAjaxHandlerAction.getDaysInMonth()));
            callableStatement.setInt(8, Integer.parseInt(payrollAjaxHandlerAction.getDaysWorked()));
            callableStatement.setInt(9, Integer.parseInt(payrollAjaxHandlerAction.getDaysProject()));
            callableStatement.setInt(10, Integer.parseInt(payrollAjaxHandlerAction.getDaysVacation()));
            callableStatement.setInt(11, Integer.parseInt(payrollAjaxHandlerAction.getVactionsAvailable()));
            callableStatement.setInt(12, Integer.parseInt(payrollAjaxHandlerAction.getDaysHolidays()));
            callableStatement.setInt(13, Integer.parseInt(payrollAjaxHandlerAction.getDaysWeekends()));
            callableStatement.setDouble(14, payrollAjaxHandlerAction.getDedEmpPf());
            callableStatement.setDouble(15, payrollAjaxHandlerAction.getDedProfessionalTax());
            callableStatement.setDouble(16, payrollAjaxHandlerAction.getDedIncomeTax());
            callableStatement.setDouble(17, payrollAjaxHandlerAction.getDedCorporateLoan());
            callableStatement.setDouble(18, payrollAjaxHandlerAction.getDedLife());
            callableStatement.setDouble(19, payrollAjaxHandlerAction.getDedHealth());
            callableStatement.setDouble(20, payrollAjaxHandlerAction.getDedOthers());
            callableStatement.setDouble(21, Double.parseDouble(payrollAjaxHandlerAction.getBasic()));
            callableStatement.setDouble(22, Double.parseDouble(payrollAjaxHandlerAction.getDa()));
            callableStatement.setDouble(23, Double.parseDouble(payrollAjaxHandlerAction.getHra()));
            callableStatement.setDouble(24, Double.parseDouble(payrollAjaxHandlerAction.getTa()));
            callableStatement.setDouble(25, Double.parseDouble(payrollAjaxHandlerAction.getRa()));
            callableStatement.setDouble(26, Double.parseDouble(payrollAjaxHandlerAction.getLife()));
            callableStatement.setDouble(27, Double.parseDouble(payrollAjaxHandlerAction.getHealth()));
            callableStatement.setDouble(28, Double.parseDouble(payrollAjaxHandlerAction.getAttendanceAllow()));
            callableStatement.setDouble(29, payrollAjaxHandlerAction.getMaidServices());
            callableStatement.setDouble(30, Double.parseDouble(payrollAjaxHandlerAction.getEntertainment()));
            callableStatement.setDouble(31, Double.parseDouble(payrollAjaxHandlerAction.getKidsEducation()));
            callableStatement.setDouble(32, Double.parseDouble(payrollAjaxHandlerAction.getVehicleAllowance()));
            callableStatement.setDouble(33, Double.parseDouble(payrollAjaxHandlerAction.getLongTermBonus()));
            callableStatement.setDouble(34, Double.parseDouble(payrollAjaxHandlerAction.getEmployerPf()));
            callableStatement.setDouble(35, payrollAjaxHandlerAction.getEmployeePfPayRollDetails());
            callableStatement.setDouble(36, Double.parseDouble(payrollAjaxHandlerAction.getCca()));
            callableStatement.setDouble(37, Double.parseDouble(payrollAjaxHandlerAction.getSplAllowance()));
            callableStatement.setDouble(38, Double.parseDouble(payrollAjaxHandlerAction.getMiscPay()));
            callableStatement.setDouble(39, Double.parseDouble(payrollAjaxHandlerAction.getVariablePay()));
            callableStatement.setDouble(40, payrollAjaxHandlerAction.getEarnedBasic());
            callableStatement.setDouble(41, payrollAjaxHandlerAction.getEarnedDa());
            callableStatement.setDouble(42, payrollAjaxHandlerAction.getEarnedHra());
            callableStatement.setDouble(43, payrollAjaxHandlerAction.getEarnedTa());
            callableStatement.setDouble(44, payrollAjaxHandlerAction.getEarnedRa());
            callableStatement.setDouble(45, payrollAjaxHandlerAction.getEarnedLife());
            callableStatement.setDouble(46, payrollAjaxHandlerAction.getEarnedHealth());
            callableStatement.setDouble(47, payrollAjaxHandlerAction.getEarnedCCa());
            callableStatement.setDouble(48, payrollAjaxHandlerAction.getEarnedProjectPay());
            callableStatement.setDouble(49, payrollAjaxHandlerAction.getEarnedattallowance());
            callableStatement.setDouble(50, payrollAjaxHandlerAction.getTdsDeduction());
            callableStatement.setDouble(51, payrollAjaxHandlerAction.getTaxableIncome());
            callableStatement.setDouble(52, payrollAjaxHandlerAction.getEarnedFood());
            callableStatement.setDouble(53, payrollAjaxHandlerAction.getEarnedLaundary());
            callableStatement.setDouble(54, payrollAjaxHandlerAction.getEarnedMaidServices());
            callableStatement.setDouble(55, payrollAjaxHandlerAction.getEarnedEntertainment());
            callableStatement.setDouble(56, payrollAjaxHandlerAction.getEarnedKidsEducation());
            callableStatement.setDouble(57, payrollAjaxHandlerAction.getEarnedVehicleAllowance());
            callableStatement.setDouble(58, payrollAjaxHandlerAction.getEarnedLongTermBonus());
            callableStatement.setDouble(59, payrollAjaxHandlerAction.getEarnedMiscPay());
            callableStatement.setDouble(60, payrollAjaxHandlerAction.getEarnedsplallowance());
            callableStatement.setDouble(61, payrollAjaxHandlerAction.getEarnedEmployerPf());
            callableStatement.setDouble(62, payrollAjaxHandlerAction.getYtdGross());
            callableStatement.setDouble(63, payrollAjaxHandlerAction.getYtdLongterm());
            callableStatement.setDouble(64, payrollAjaxHandlerAction.getYtdPf());
            callableStatement.setDouble(65, payrollAjaxHandlerAction.getYtdProffTax());
            callableStatement.setDouble(66, payrollAjaxHandlerAction.getYtdLifeInsurance());
            callableStatement.setDouble(67, payrollAjaxHandlerAction.getYtdTa());
            callableStatement.setDouble(68, payrollAjaxHandlerAction.getYtdRa());
            callableStatement.setDouble(69, payrollAjaxHandlerAction.getYtdExpTaxFree());
            callableStatement.setDouble(70, payrollAjaxHandlerAction.getYtdOthersMisc());
            callableStatement.setDouble(71, payrollAjaxHandlerAction.getYtdProjectPay());
            callableStatement.setDouble(72, payrollAjaxHandlerAction.getYtdSavings1Reported());
            callableStatement.setDouble(73, payrollAjaxHandlerAction.getYtdSavings2Reported());
            callableStatement.setDouble(74, payrollAjaxHandlerAction.getYtdTaxableSalary());
            callableStatement.setDouble(75, payrollAjaxHandlerAction.getYtdTaxableCommission());
            callableStatement.setDouble(76, payrollAjaxHandlerAction.getYtdTDSonSalary());
            callableStatement.setDouble(77, payrollAjaxHandlerAction.getYtdTDSOnCommm());
            callableStatement.setDouble(78, payrollAjaxHandlerAction.getYtdTDSCollected());
            callableStatement.setDouble(79, payrollAjaxHandlerAction.getYtdTDSLiabilitiesSalary());
            callableStatement.setDouble(80, payrollAjaxHandlerAction.getYtdTDSLiabilitiesBonus());
            callableStatement.setDouble(81, payrollAjaxHandlerAction.getDiffTDSLiabilitiesSalary());
            callableStatement.setDouble(82, payrollAjaxHandlerAction.getDiffTDSLiabilitiesBonus());
            callableStatement.setString(83, payrollAjaxHandlerAction.getUserId());
            callableStatement.setTimestamp(84, DateUtility.getInstance().getCurrentMySqlDateTime());
            callableStatement.setDouble(85, Double.parseDouble(payrollAjaxHandlerAction.getProjectPay()));
            callableStatement.setInt(86, payrollAjaxHandlerAction.getIsBlock());
            callableStatement.setDouble(87, payrollAjaxHandlerAction.getGrossPayActualDetails());
            callableStatement.setDouble(88, payrollAjaxHandlerAction.getNetPaidActualDetails());
            callableStatement.setInt(89, payrollAjaxHandlerAction.getWageId());
            callableStatement.setDate(90, DateUtility.getInstance().getMysqlDate(payrollAjaxHandlerAction.getProjectEndDate()));
            callableStatement.setInt(91, payrollAjaxHandlerAction.getDoRepaymentFlag());
            callableStatement.setDouble(92, payrollAjaxHandlerAction.getRepaymentGross());
            callableStatement.setString(93, payrollAjaxHandlerAction.getRepaymentComments());
            callableStatement.setDouble(94, payrollAjaxHandlerAction.getRepaymentNet());
            // System.out.println("payrollAjaxHandlerAction.getLifeInsurancePremium()----->" + payrollAjaxHandlerAction.getLifeInsurancePremium());
         /*   callableStatement.setDouble(95, Double.parseDouble(payrollAjaxHandlerAction.getLifeInsurancePremium()));
             callableStatement.setDouble(96, Double.parseDouble(payrollAjaxHandlerAction.getHousingLoanRepay()));
             callableStatement.setDouble(97, Double.parseDouble(payrollAjaxHandlerAction.getNscTds()));
             callableStatement.setDouble(98, Double.parseDouble(payrollAjaxHandlerAction.getPpfContribution()));
             callableStatement.setDouble(99, Double.parseDouble(payrollAjaxHandlerAction.getTutionFee()));
             callableStatement.setDouble(100, Double.parseDouble(payrollAjaxHandlerAction.getElss()));
             callableStatement.setDouble(101, Double.parseDouble(payrollAjaxHandlerAction.getPostOfficeTerm()));
             callableStatement.setDouble(102, Double.parseDouble(payrollAjaxHandlerAction.getBankDepositTax()));
             callableStatement.setDouble(103, Double.parseDouble(payrollAjaxHandlerAction.getOthersTDS()));
             callableStatement.setDouble(104, Double.parseDouble(payrollAjaxHandlerAction.getContributionToPf()));
             callableStatement.setDouble(105, Double.parseDouble(payrollAjaxHandlerAction.getNpsEmployeeContr()));
             callableStatement.setDouble(106, Double.parseDouble(payrollAjaxHandlerAction.getTotalTds()));
             callableStatement.setDouble(107, Double.parseDouble(payrollAjaxHandlerAction.getTotalTdsDeductable()));
             callableStatement.setDouble(108, Double.parseDouble(payrollAjaxHandlerAction.getInterstOnBorrowed()));
             callableStatement.setDouble(109, Double.parseDouble(payrollAjaxHandlerAction.getInterstOnBorrowedDeductable()));
             callableStatement.setDouble(110, Double.parseDouble(payrollAjaxHandlerAction.getInsuranceForParents()));
             callableStatement.setDouble(111, Double.parseDouble(payrollAjaxHandlerAction.getInsuranceForParentsDeduc()));
             callableStatement.setDouble(112, Double.parseDouble(payrollAjaxHandlerAction.getInsuranceOthers()));
             callableStatement.setDouble(113, Double.parseDouble(payrollAjaxHandlerAction.getInsuranceOthersDeduc()));
             callableStatement.setDouble(114, Double.parseDouble(payrollAjaxHandlerAction.getInterstOnEdu()));
             callableStatement.setDouble(115, Double.parseDouble(payrollAjaxHandlerAction.getInterstOnHrAssumptions()));
             callableStatement.setDouble(116, Double.parseDouble(payrollAjaxHandlerAction.getInterstOnHrAssumptionsInv()));
             callableStatement.setDouble(117, Double.parseDouble(payrollAjaxHandlerAction.getExpectedYearlyCost()));
             callableStatement.setDouble(118, Double.parseDouble(payrollAjaxHandlerAction.getLicFromSal()));*/
            callableStatement.setInt(95, Integer.parseInt(payrollAjaxHandlerAction.getPayRollId()));
            callableStatement.setInt(96, payrollAjaxHandlerAction.getTdsId());
            callableStatement.setInt(97, Integer.parseInt(payrollAjaxHandlerAction.getEmpId()));
            callableStatement.setDouble(98, payrollAjaxHandlerAction.getRepaymentVariablePay());
            callableStatement.setDouble(99, payrollAjaxHandlerAction.getBonusCommission());
            callableStatement.setDouble(100, payrollAjaxHandlerAction.getOtherAdditions());
            callableStatement.setInt(101, Integer.parseInt(payrollAjaxHandlerAction.getLeavesApplied()));
            callableStatement.setDouble(102, payrollAjaxHandlerAction.getEmployeeesi());
            callableStatement.setDouble(103, payrollAjaxHandlerAction.getEmployeresi());
            callableStatement.setDouble(104, payrollAjaxHandlerAction.getEarnedEmployeeesi());
            callableStatement.setDouble(105, payrollAjaxHandlerAction.getEarnedEmployeresi());
            callableStatement.setDouble(106, payrollAjaxHandlerAction.getNewGrossPay());
          //  System.out.println("payrollAjaxHandlerAction.getReleasedDate()---"+payrollAjaxHandlerAction.getReleasedDate());
            if(payrollAjaxHandlerAction.getReleasedDate()!=null && !"".equals(payrollAjaxHandlerAction.getReleasedDate())){
             //   System.out.println("iff");
           callableStatement.setDate(107, DateUtility.getInstance().getMysqlDate(payrollAjaxHandlerAction.getReleasedDate()));
            }else{
               //  System.out.println("else");
                 callableStatement.setDate(107,null);
            }
            callableStatement.registerOutParameter(108, Types.VARCHAR);

            updatedrows = callableStatement.executeUpdate();

            resultMessage = "<font style='color:green;'>" + callableStatement.getString(108) + "<font>";


           

            //   System.out.println("in second if updatedrows--->" + updatedrows);

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
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultMessage;

    }

    public String doBackUpPayRollDetails(String empId, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {
        //  System.out.println("doBackUpPayRollDetails-------------");
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        int insertedRows = 0;
        int isSuccess = 0;
        int payRollInsertionUpdate = 0;
        String result;
        String query = "";
        try {

            DateUtility dateUtil = DateUtility.getInstance();
            connection = ConnectionProvider.getInstance().getConnection();
            query = "INSERT INTO `tblEmpPayRollHistory`(`PayRollId`,`BasicPay`,`DA`,`HRA`,`TA`,`RA`,`Entertainment`,`KidsEducation`,`VehcleAllowance`,`CCA`,"
                    + "`MiscPay`,`SplAllowances`,`LongTermbonus`,`ProjectPay`,`AttAllowance`,`PaymentTypeId`,`Employer_PF`,`Employee_PF`,`Life`,`Health`,"
                    + "`Ded_Professional_Tax`,`Ded_Others`,`GrossPay`,`VariablePay`,`TotalCost`,`Bank_Name`,`BankAccountNumber`,`CreatedBy`,`EmpId`,`OrgAccId`,`ProjectEndDate`,`NetPaid`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, Integer.parseInt(payrollAjaxHandlerAction.getPayRollId()));
            preparedStatement.setDouble(2, Double.parseDouble(payrollAjaxHandlerAction.getBasic()));
            preparedStatement.setDouble(3, Double.parseDouble(payrollAjaxHandlerAction.getDa()));
            preparedStatement.setDouble(4, Double.parseDouble(payrollAjaxHandlerAction.getHra()));
            preparedStatement.setDouble(5, Double.parseDouble(payrollAjaxHandlerAction.getTa()));
            preparedStatement.setDouble(6, Double.parseDouble(payrollAjaxHandlerAction.getRa()));
            preparedStatement.setDouble(7, Double.parseDouble(payrollAjaxHandlerAction.getEntertainment()));
            preparedStatement.setDouble(8, Double.parseDouble(payrollAjaxHandlerAction.getKidsEducation()));
            preparedStatement.setDouble(9, Double.parseDouble(payrollAjaxHandlerAction.getVehicleAllowance()));
            preparedStatement.setDouble(10, Double.parseDouble(payrollAjaxHandlerAction.getCca()));
            preparedStatement.setDouble(11, Double.parseDouble(payrollAjaxHandlerAction.getMiscPay()));
            preparedStatement.setDouble(12, Double.parseDouble(payrollAjaxHandlerAction.getSplAllowance()));
            preparedStatement.setDouble(13, Double.parseDouble(payrollAjaxHandlerAction.getLongTermBonus()));
            preparedStatement.setDouble(14, Double.parseDouble(payrollAjaxHandlerAction.getProjectPay()));
            preparedStatement.setDouble(15, Double.parseDouble(payrollAjaxHandlerAction.getAttendanceAllow()));
            if (!"All".equals(payrollAjaxHandlerAction.getPaymentType())) {
                preparedStatement.setInt(16, Integer.parseInt(payrollAjaxHandlerAction.getPaymentType()));
            } else {
                preparedStatement.setInt(16, 0);
            }
            preparedStatement.setDouble(17, Double.parseDouble(payrollAjaxHandlerAction.getEmployerPf()));
            preparedStatement.setDouble(18, Double.parseDouble(payrollAjaxHandlerAction.getEmployeePf()));
            preparedStatement.setDouble(19, Double.parseDouble(payrollAjaxHandlerAction.getLife()));
            preparedStatement.setDouble(20, Double.parseDouble(payrollAjaxHandlerAction.getHealth()));
            preparedStatement.setDouble(21, Double.parseDouble(payrollAjaxHandlerAction.getProfessionalTax()));
            preparedStatement.setDouble(22, Double.parseDouble(payrollAjaxHandlerAction.getOtherDeductions()));
            preparedStatement.setDouble(23, Double.parseDouble(payrollAjaxHandlerAction.getGrossPay()));
            preparedStatement.setDouble(24, Double.parseDouble(payrollAjaxHandlerAction.getVariablePay()));
            preparedStatement.setDouble(25, Double.parseDouble(payrollAjaxHandlerAction.getTotalCost()));
            preparedStatement.setString(26, payrollAjaxHandlerAction.getBankName());
            preparedStatement.setString(27, payrollAjaxHandlerAction.getBankAccountNo());
            preparedStatement.setString(28, payrollAjaxHandlerAction.getUserId());
            preparedStatement.setInt(29, Integer.parseInt(payrollAjaxHandlerAction.getEmpId()));

            preparedStatement.setInt(30, payrollAjaxHandlerAction.getOrgId());

            if (!"nun".equals(payrollAjaxHandlerAction.getProjectEndDate())) {
                preparedStatement.setDate(31, dateUtil.getMysqlDate(payrollAjaxHandlerAction.getProjectEndDate()));
            } else {
                preparedStatement.setDate(31, dateUtil.getMysqlDate("01/01/1951"));
            }
            preparedStatement.setDouble(32, payrollAjaxHandlerAction.getNetPaidPayroll());

            insertedRows = preparedStatement.executeUpdate();

            if (insertedRows > 0) {
                result = "success";
            } else {
                result = "faild";
            }

        } catch (SQLException se) {
            se.printStackTrace();
          //  System.out.println(se.getMessage());
            result = "faild";
            throw new ServiceLocatorException(se.getMessage());
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

    public String getPayrollCheck(String year, int month, String difference, int OrgId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preStmt = null;
        // String diffString = "";
        String result = "";
        ResultSet resultSet = null;
        int preYear = Integer.parseInt(year);
        int preMonth = (month - 1);
        int sno = 0;
        try {
       //     System.out.println("year-->" + year);
        //    System.out.println("difference-->" + difference);
            if (month == 1) {
                preMonth = 12;
                preYear = (Integer.parseInt(year) - 1);
            }

            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
            query = "SELECT cur.Earned_Net_Paid AS NetPaid,pre.Earned_Net_Paid AS PreNetPaid,cur.Earned_Net_Paid-pre.Earned_Net_Paid AS Difference,tblEmployee.EmpNo AS EmpNo,"
                    + "CONCAT(FName,'.',LName) AS EmpName,cur.PayrollDate AS PayrollDate,pre.PayrollDate AS PrePayrollDate,tblEmpPayRoll.EmpId,((cur.Earned_Net_Paid - pre.Earned_Net_Paid) / cur.Earned_Net_Paid) * 100 AS DiffPercent FROM "
                    + "tblEmpWages cur LEFT JOIN tblEmpWages pre ON pre.Payroll_Id = cur.Payroll_Id"
                    + " JOIN tblEmployee ON (cur.PayRoll_Id = tblEmployee.EmpNo)"
                    + "LEFT OUTER JOIN tblEmpPayRoll ON cur.PayRoll_Id=tblEmpPayRoll.PayRollId"
                    + " WHERE MONTH(cur.PayPeriodStartDate) = " + month + " AND YEAR(cur.PayPeriodStartDate)= " + (Integer.parseInt(year)) + ""
                    + " AND MONTH(pre.PayPeriodStartDate) = " + preMonth + " AND YEAR(pre.PayPeriodStartDate)=" + preYear + " AND cur.IsBlock=0 AND cur.Earned_Net_Paid != pre.Earned_Net_Paid";

             if (OrgId != 0 && !"".equals(OrgId) && !"All".equals(OrgId)) {
                      queryString = queryString + " and tblEmpPayRoll.OrgAccId='" + OrgId + "'";
                        }
            
            if (!"".equals(difference)) {
                query = query + " AND ((cur.Earned_Net_Paid - pre.Earned_Net_Paid) / cur.Earned_Net_Paid) * 100";
                if ("0-5".equals(difference)) {
                    query = query + " BETWEEN 0 AND 5 ";
                } else if ("5-10".equals(difference)) {
                    query = query + " BETWEEN 5 AND 10 ";
                } else if ("10-15".equals(difference)) {
                    query = query + " BETWEEN 10 AND 15 ";
                } else if ("15-20".equals(difference)) {
                    query = query + "  BETWEEN 15 AND 20 ";
                } else if ("greater than 20".equals(difference)) {
                    query = query + " > 20 ";
                } else if ("negative difference".equals(difference)) {
                    query = query + " < 0 ";
                }
}
            query = query + " ORDER BY cur.Payroll_Id";
            preStmt = connection.prepareStatement(query);
         //   System.out.println("query" + query);
            resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                sno++;
                int empNo = resultSet.getInt("EmpNo");
                String empName = resultSet.getString("EmpName");
                String payrollDate = resultSet.getString("PayrollDate");
                double netPaid = resultSet.getInt("NetPaid");
                String lastPayrollDate = resultSet.getString("PrePayrollDate");
                double lastNetPaid = resultSet.getInt("PreNetPaid");
                double diff = resultSet.getInt("Difference");
                double percent = resultSet.getInt("DiffPercent");
                String diffPercent = diff + "(" + String.format("%.2f", (Double) percent) + "%)";
                result = result + sno + "#^$" + empNo + "#^$" + empName + "#^$" + payrollDate + "#^$" + netPaid + "#^$" + lastPayrollDate + "#^$" + lastNetPaid + "#^$" + diffPercent + "#^$" + percent + "*@!";

            }

// System.out.println("queryString"+query);
        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        //  System.out.println("sno-->" + sno);
        return result;
    }

    //---------------------------------------------------
    /* public String getPayrollCheck(String year, int month) throws ServiceLocatorException {
     String payrollCheckList = "";
     DateUtility dateutility = new DateUtility();
       
     Connection connection = null;
     int sno = 0;
        
     CallableStatement callableStatement = null;

       
     PreparedStatement preStmt = null;

       
     String queryString = "";
     Statement statement = null;

        
     ResultSet resultSet = null;
       
       
     try {
     //    System.out.println("year-->" + year);
     //  System.out.println("month-->" + month);


     connection = ConnectionProvider.getInstance().getConnection();
     String query = null;
     Map employeeMap = new HashMap();
     //query="SELECT Id,CONCAT(FName,'.',LName) AS Name FROM tblEmployee INNER JOIN tblEmpPayRoll WHERE CurStatus = 'Active'";
     query = "SELECT Id,CONCAT(FName,'.',LName) AS NAME FROM tblEmployee INNER JOIN tblEmpPayRoll ON tblEmployee.Id = tblEmpPayRoll.EmpId  WHERE CurStatus = 'Active'";
     preStmt = connection.prepareStatement(query);

     resultSet = preStmt.executeQuery();
     while (resultSet.next()) {
     employeeMap.put(resultSet.getInt("Id"), resultSet.getString("Name"));
     }
     resultSet.close();
     preStmt.close();
     Iterator entries = employeeMap.entrySet().iterator();
     //------------------
     DateFormat inputDF = new SimpleDateFormat("MM/dd/yyyy");
     Date date1 = inputDF.parse(month + "/1/" + year);
     Calendar cal = Calendar.getInstance();
     cal.setTime(date1);
     int currmonth = cal.get(Calendar.MONTH);
     int curryear = cal.get(Calendar.YEAR);


     Calendar aCalendar = Calendar.getInstance();
     aCalendar.setTime(date1);
     // add -1 month to current month
     aCalendar.add(Calendar.MONTH, -1);
     // set DATE to 1, so first date of previous month
     aCalendar.set(Calendar.DATE, 1);



     int lastMonth = aCalendar.get(Calendar.MONTH);
     int lastYesr = aCalendar.get(Calendar.YEAR);

     //    System.out.println("currmonth-->" + currmonth);
     //    System.out.println("curryear-->" + curryear);
     //    System.out.println("lastMonth-->" + lastMonth);
     //    System.out.println("lastYesr-->" + lastYesr);
     Map wagesMap = null;
     List empWagesList = new ArrayList();
     query = "SELECT tblEmpWages.Earned_Net_Paid as NetPaid,PayrollDate,tblEmpPayRoll.EmpId FROM tblEmpWages  LEFT OUTER JOIN tblEmpPayRoll ON tblEmpWages.PayRoll_Id=tblEmpPayRoll.PayRollId WHERE MONTH(PayPeriodStartDate) = ? AND YEAR(PayPeriodStartDate)=? AND IsBlock=0 AND tblEmpPayRoll.EmpId = ?";
     preStmt = connection.prepareStatement(query);

     double currNetPaid = 0.00;
     double lastNetPaid = 0.00;
     boolean isExist = false;
     boolean lastExist = false;
     while (entries.hasNext()) {
     Entry thisEntry = (Entry) entries.next();
     int key = Integer.parseInt(thisEntry.getKey().toString());
     String employeeName = thisEntry.getValue().toString();
     //------------------

     preStmt.setInt(1, (currmonth + 1));
     preStmt.setInt(2, curryear);
     preStmt.setInt(3, key);
     resultSet = preStmt.executeQuery();

     if (resultSet.next()) {
     isExist = true;
     sno++;
     wagesMap = new HashMap();
     wagesMap.put("SNO", sno);
     wagesMap.put("EmpName", employeeName);
     wagesMap.put("PayrollDate", resultSet.getString("PayrollDate"));
     wagesMap.put("NetPaid", resultSet.getString("NetPaid"));
     currNetPaid = resultSet.getDouble("NetPaid");

     payrollCheckList = payrollCheckList + sno + "|" + employeeName + "|" + resultSet.getString("PayrollDate") + "|" + resultSet.getString("NetPaid") + "|";
     resultSet.close();
     }
     if (isExist) {
     preStmt.setInt(1, (lastMonth + 1));
     preStmt.setInt(2, lastYesr);
     preStmt.setInt(3, key);
     resultSet = preStmt.executeQuery();
     if (resultSet.next()) {
     lastExist = true;
     wagesMap.put("LastPayrollDate", resultSet.getString("PayrollDate"));
     wagesMap.put("LastNetPaid", resultSet.getString("NetPaid"));
     lastNetPaid = resultSet.getDouble("NetPaid");
     double netDiff = (currNetPaid - lastNetPaid);
     double diff = ((currNetPaid - lastNetPaid) / currNetPaid) * 100;
     wagesMap.put("Difference", diff);

     String Difference = netDiff +"("+String.format("%.2f", (Double) diff)+"%)";

     payrollCheckList = payrollCheckList + resultSet.getString("PayrollDate") + "|" + resultSet.getString("NetPaid") + "|" + Difference + "|" + String.format("%.2f", (Double) diff )+"^";
     resultSet.close();
     }
     if (!lastExist) {
     payrollCheckList = payrollCheckList + "-|-|-^";
     //payrollCheckList = payrollCheckList + "^";
     }
     // ...
     }
     //empWagesList.add(wagesMap);
     isExist = false;
     lastExist = false;
     currNetPaid = 0.00;
     lastNetPaid = 0.00;
     } 

     //    System.out.println("payrollCheckList-->" + payrollCheckList);



     } catch (Exception ex) {
     ex.printStackTrace();
     //System.out.println("Exception-->"+ex.getMessage());
     //e.printStackTrace();
     } finally {

     try {
     if (resultSet != null) {
     resultSet.close();
     resultSet = null;
     }
     if (preStmt != null) {
     preStmt.close();
     preStmt = null;
     }
     if (connection != null) {
     connection.close();
     connection = null;
     }
     } catch (Exception se) {
     se.printStackTrace();
     }
     }
     //  System.out.println("sno-->" + sno);
     return payrollCheckList + "addto" + sno;
     } */
//---------------------------------------------------
    public String cleanPayroll(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;

        /**
         * callableStatement is a reference variable for CallableStatement .
         */
        CallableStatement callableStatement = null;

        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null, preStmtTemp = null;

        /**
         * The queryString is useful to get queryString result to the particular
         * jsp page
         */
        String queryString = "";
        String resultMesage = "";
        Statement statement = null;
        int deleted = 0;
        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;

        try {
            //System.out.println("year-->" + year);
            //System.out.println("month-->" + month);


            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;

            query = "SELECT * FROM tblEmpWages  WHERE Freeze_Payroll=1 and MONTH(PayrollDate)=" + month + " and YEAR(PayrollDate) = " + year +"";
            
            if(payrollAjaxHandlerAction.getOrgId() != 0){
                query = query + " AND OrgId = "+payrollAjaxHandlerAction.getOrgId();
            }
            
            preStmt = connection.prepareStatement(query);

            resultSet = preStmt.executeQuery();
            if (resultSet.next()) {
                resultMesage = "Failure";

            } else {
//                queryString = "delete from tblEmpWages where MONTH(PayrollDate)=" + month + " and YEAR(PayrollDate) = " + year;
//                statement = connection.createStatement();
//                statement.execute(queryString);
//                resultMesage = "Success";
                connection = ConnectionProvider.getInstance().getConnection();
                callableStatement = connection.prepareCall("{call spCleanWagesAndBKP(?,?,?,?,?,?) }");
                callableStatement.setInt(1, month);
                callableStatement.setInt(2, year);
                callableStatement.setString(3, payrollAjaxHandlerAction.getUserId());
                java.util.Date date = new java.util.Date();
                callableStatement.setTimestamp(4, new Timestamp(date.getTime()));
                callableStatement.setInt(5, payrollAjaxHandlerAction.getOrgId());
                callableStatement.registerOutParameter(6, Types.VARCHAR);
                deleted = callableStatement.executeUpdate();
                resultMesage = callableStatement.getString(6);
            }
            resultSet.close();
            preStmt.close();


        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }

        return resultMesage;
    }

    //----------------------------------------------------------------------------------------------
    public String doUnFreezeWages(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        Connection connection1 = null;
        PreparedStatement prestatement1 = null;
        ResultSet resultSet1 = null;

        String resultMessage = "";
        String queryString1 = "";
        String queryString2 = "";
        int updatedrows = 0;
        boolean flag = false;
         int empId = 0;
         
          if(payrollAjaxHandlerAction.getEmpnameById() !=null && !"nun".equals(payrollAjaxHandlerAction.getEmpnameById())){
                  
                empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(payrollAjaxHandlerAction.getEmpnameById());
               //  System.out.println("emoName empId----->"+empId);
                }
           else if(payrollAjaxHandlerAction.getEmpNo()!=null && !"nun".equals(payrollAjaxHandlerAction.getEmpNo()) ){
                empId =  DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(payrollAjaxHandlerAction.getEmpNo()));
              // System.out.println("empId----->"+empId);
            }
      //  System.out.println("empId0----->"+empId);
        
        
          
        queryString1 = "SELECT Freeze_Payroll FROM tblEmpWages WHERE MONTH(PayrollDate) = " + month + " AND YEAR(PayrollDate)='" +year+ "'";
        
        if(empId != 0){
           queryString1 = queryString1 +" AND EmpId='"+empId+ "'"; 
       }
         if(payrollAjaxHandlerAction.getOrgId() != 0){
           queryString1 = queryString1 +" AND OrgId='"+payrollAjaxHandlerAction.getOrgId()+ "'";
         }
     //  System.out.println("query-->" + queryString1);
        try {
         
            resultMessage = "<font style='color:red;'>Records doesnt exists for the given month and year..</font>";
       
            connection = ConnectionProvider.getInstance().getConnection();
            prestatement = connection.prepareStatement(queryString1);
            resultSet = prestatement.executeQuery();

            if (resultSet.next()) {

                // if (resultSet.getInt("Freeze_Payroll") == 0) {
                queryString2 = "UPDATE `mirage`.`tblEmpWages` SET `Freeze_Payroll` = 0,FreezeAppliedBy='" + payrollAjaxHandlerAction.getUserId() + "',FreezeAppliedDate='" + DateUtility.getInstance().getCurrentMySqlDateTime() + "'  WHERE MONTH(PayrollDate) = " + month + " AND YEAR(PayrollDate)=" + year + " AND Freeze_Payroll != 0";
               if(flag = true){
             queryString2 = queryString2 + " AND EmpId='" + empId+ "' AND OrgId='"+payrollAjaxHandlerAction.getOrgId()+ "'";
         }
         else{
         if(empId != 0){
           queryString2 = queryString2 + " AND EmpId='" + empId+ "'";
       }
        if(payrollAjaxHandlerAction.getOrgId() != 0){
           queryString2 = queryString2 +" AND OrgId='"+payrollAjaxHandlerAction.getOrgId()+ "'";
         }       
         }
     //  System.out.println("query-->" + queryString2);
                
                connection1 = ConnectionProvider.getInstance().getConnection();
                prestatement1 = connection1.prepareStatement(queryString2);
                updatedrows = prestatement1.executeUpdate();
               //    System.out.println("in second if updatedrows--->" + updatedrows);
                if (updatedrows > 0) {
                    resultMessage = "<font style='color:green;'>UnFreezed Succesfully</font>";
                } else {
                    resultMessage = "<font style='color:red;'>Records already Unfreezed</font>";
                }
                //further statements
                // } else {
                //   resultMessage = "<font style='color:red;'>Records already freezed</font>";
                //}

            }



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
                if (prestatement != null) {
                    prestatement.close();
                    prestatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (prestatement1 != null) {
                    prestatement1.close();
                    prestatement1 = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultMessage;

    }

    public String rerunEmpWagesForCurrentMonth(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

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
        int empID = 0;
        try {
            // System.out.println("payrollAjaxHandlerAction.getEmpnameById()==="+payrollAjaxHandlerAction.getEmpnameById());
            //  System.out.println(" payrollAjaxHandlerAction.getEmpId()==="+ payrollAjaxHandlerAction.getEmpId());

            if (!"".equals(payrollAjaxHandlerAction.getEmpnameById()) && payrollAjaxHandlerAction.getEmpnameById() != null) {
                empID = DataSourceDataProvider.getInstance().getEmpIdByLoginId(payrollAjaxHandlerAction.getEmpnameById());
                // System.out.println("emoName EmpId----->"+empID);
            } else if (!"".equals(payrollAjaxHandlerAction.getEmpId()) && payrollAjaxHandlerAction.getEmpId() != null) {
                empID = DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(payrollAjaxHandlerAction.getEmpId()));
                // System.out.println("EmpId----->"+empID);
            }

            boolean exists = DataSourceDataProvider.getInstance().checkForWageExisteByEmpId(month, year, empID);
            //  System.out.println(exists);
            boolean isFreeze = false;

            if (exists) {

                isFreeze = DataSourceDataProvider.getInstance().isFreezedByEmpId(month, year, empID);
                if (isFreeze) {

                    connection = ConnectionProvider.getInstance().getConnection();
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
                    callableStatement = connection.prepareCall("{call spReRunEmpWages(?,?,?,?,?,?,?,?,?,?,?,?) }");

                    callableStatement.setInt(1, month);
                    callableStatement.setInt(2, year);
                    callableStatement.setInt(3, payrollAjaxHandlerAction.getNoOfDays());
                    callableStatement.setInt(4, payrollAjaxHandlerAction.getNoOfWeekedDays());
                    callableStatement.setInt(5, payrollAjaxHandlerAction.getNoOfHolidays());
                    callableStatement.setInt(6, payrollAjaxHandlerAction.getWorkingDays());

                    callableStatement.setInt(7, empID);
                    callableStatement.setString(8, payrollAjaxHandlerAction.getCreatedBy());
                    callableStatement.setTimestamp(9, DateUtility.getInstance().getCurrentMySqlDateTime());
                    callableStatement.setInt(10, projected_monthDiff);
                    callableStatement.setInt(11, earned_monthDiff);
                    callableStatement.registerOutParameter(12, Types.VARCHAR);

                    insertedRows = callableStatement.executeUpdate();

                    resultMessage = "<font style='color:green;'>" + callableStatement.getString(12) + "<font>";
                } else {
                    resultMessage = "<font style='color:red;'>Please Freeze the selected employee wage of given month!</font>";
                }
                //resultMessage = "<font style='color:red;'>Can Proceed</font>";
            } else {
                resultMessage = "<font style='color:red;'>Running of wages is not done for given month</font>";
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

    public String doUploadLeavesExcelSheet(int year, int month, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;


        String resultMessage = "";
        java.sql.Date payRollDate = null;
        int rowsInserted = 0;
        try {
            if (month < 10) {

                payRollDate = DateUtility.getInstance().getMysqlDate("0" + month + "/01/" + year);
            } else {
                payRollDate = DateUtility.getInstance().getMysqlDate(month + "/01/" + year);
            }
            
            boolean isExist = DataSourceDataProvider.getInstance().checkLeavesExcelUploadByMonth(month, year, payrollAjaxHandlerAction.getOrgId());
            if (!isExist) {
                connection = ConnectionProvider.getInstance().getConnection();
                prestatement = connection.prepareStatement("INSERT INTO tblEmpAttendanceLaod(PayRollId,OrgId,FirstName,LastName,PayrollDate,DaysInMonth,DaysWorked,DaysVacation,DaysHolidays,DaysWeekends,DaysProject,Ded_Others,Bonus,Earned_others,UplaodedBy) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                FileInputStream fs = new FileInputStream(payrollAjaxHandlerAction.getFile());
                Workbook wb = Workbook.getWorkbook(fs);
                int sheet1Rows = 0;
                int sheet2Rows = 0;
                //for (int i = 0; i < 2; i++) {
                Sheet sh = wb.getSheet(0);
                sheet1Rows = sh.getRows();
                // To get the number of rows present in sheet

//                    if (i == 0) {
//                        sheet1Rows = sh.getRows();
//                    } else {
//                        sheet2Rows = sh.getRows();
//                    }

                //}
                int totalRowsInTheUploadedExcel = sheet1Rows + sheet2Rows - 1;

                // System.out.println("total rows--->" + totalRowsInTheUploadedExcel);
                int payrollCount = DataSourceDataProvider.getInstance().getPayrollCount(payrollAjaxHandlerAction.getOrgId());
                // System.out.println("payrollCount--->" + payrollCount);
                // if (payrollCount == totalRowsInTheUploadedExcel) {
                //   for (int i = 0; i < 2; i++) {
                // TO get the access to the sheet
                sh = wb.getSheet(0);

                // To get the number of rows present in sheet
                int totalNoOfRows = sh.getRows();
                //  System.out.println("totalNoofRows in excel-->" + totalNoOfRows);
                // System.out.println("row=="+totalNoOfRows);
                // To get the number of columns present in sheet
                int totalNoOfCols = sh.getColumns();
                //  setCurrentTask(tasksVTO);
                // String  generatedPath = null;
                if (payrollAjaxHandlerAction.getFileFileName() != null) {
                    // attachmentService = ServiceLocator.getAttachmentService();
                    // generatedPath = attachmentService.generatePath(com.mss.mirage.util.Properties.getProperty("Attachments.Path"), "Emp Reviews");
                    //  File targetDirectory = new File(generatedPath + File.separator + getFileFileName());
                    // setAttachmentLocation(targetDirectory.toString()); 

                    for (int row = 1; row < totalNoOfRows; row++) {
                        String sno = sh.getCell(0, row).getContents();
                        String code = sh.getCell(1, row).getContents();

                        String empId = sh.getCell(2, row).getContents();
                        String lastName = sh.getCell(3, row).getContents();
                        String FirstName = sh.getCell(4, row).getContents();
                        String Daysinmonth = sh.getCell(5, row).getContents();
                        String Daysworked = sh.getCell(6, row).getContents();
                        String Daysvacation = sh.getCell(7, row).getContents();
                        String Daysholidays = sh.getCell(8, row).getContents();
                        String daysweekends = sh.getCell(9, row).getContents();
                        String Daysproject = sh.getCell(10, row).getContents();
                        String ded_others = sh.getCell(11, row).getContents();
                        //   System.out.println("sh.getCell(12, row).getContents()--->" + sh.getCell(12, row).getContents());
                        String Bonus = sh.getCell(12, row).getContents();
                        String Earned_others = sh.getCell(13, row).getContents();

                        if(empId.equals("") && code.equals("")){
                            break;
                        }
                     //   System.out.println("---->payrollAjaxHandlerAction.getOrgId()"+payrollAjaxHandlerAction.getOrgId());
                     //        System.out.println("---->Integer.parseInt(\"5\"+empId)"+Integer.parseInt("5"+empId));
                        if(payrollAjaxHandlerAction.getOrgId() == 116866){
                        prestatement.setInt(1, Integer.parseInt("5"+empId));
                        }
                        else{
                             prestatement.setInt(1, Integer.parseInt(empId));
                        }
                        prestatement.setString(2, code);
                        prestatement.setString(3, FirstName);
                        prestatement.setString(4, lastName);
                        prestatement.setDate(5, payRollDate);
                        prestatement.setInt(6, Integer.parseInt(Daysinmonth));
                        prestatement.setInt(7, Integer.parseInt(Daysworked));
                        prestatement.setInt(8, Integer.parseInt(Daysvacation));
                        prestatement.setInt(9, Integer.parseInt(Daysholidays));
                        prestatement.setInt(10, Integer.parseInt(daysweekends));
                        prestatement.setInt(11, Integer.parseInt(Daysproject));
                        prestatement.setDouble(12, Double.parseDouble(ded_others));
                        prestatement.setDouble(13, Double.parseDouble(Bonus));
                        prestatement.setDouble(14, Double.parseDouble(Earned_others));
                        prestatement.setString(15, payrollAjaxHandlerAction.getCreatedBy());
                        prestatement.executeUpdate();
                        rowsInserted++;
                        /*for (int col = 0; col < totalNoOfCols; col++) {
                         System.out.print(sh.getCell(col, row).getContents() + "\t");
                         }*/

                        //   statement.addBatch("UPDATE tblEmpWages SET DaysVacationFromBiometric ="+vacations+" WHERE PayRoll_Id ="+payrollId+" AND MONTH(PayPeriodStartDate) = 4 AND YEAR(PayPeriodStartDate)=2015 AND IsBlock=0");



                    }
                    resultMessage = "uploaded^" + rowsInserted;

                    //  FileUtils.copyFile(getFile(), targetDirectory);
                    //  setObjectType("Emp Reviews");
                } else {

                    resultMessage = "Error";
                }

                //}
//                } else {
//
//                    resultMessage = "<p>No of rows count in selected excel is not matching with existed</p><p>payroll count..Please check again</p>";
//                }
            } else {
                resultMessage = "Attendance sheet for this month is already loaded..";
            }
            //    resultMessage = "<font style='color:red;'>Running of wages is not done for given month</font>";



        }  catch (Exception ioe) {
            resultMessage = "<font style='color:red;'>Please try again later</font>";
            ioe.printStackTrace();
            System.err.println(ioe);
        } finally {
            try {


                if (prestatement != null) {
                    prestatement.close();
                    prestatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultMessage;
    }

    public String getPayrollHistory(int empId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;


        String resultMessage = "";

        java.sql.Date payRollDate = null;
        int rowsInserted = 0;
        try {



            connection = ConnectionProvider.getInstance().getConnection();
            prestatement = connection.prepareStatement("SELECT EmpNo,EmpId,CONCAT(FName,'.',LName) AS NAME ,Updatebay AS Updateby, UpdatedDate FROM tblPayRollHistory WHERE EmpId=? ORDER BY UpdatedDate DESC");



            prestatement.setInt(1, empId);

            resultSet = prestatement.executeQuery();

            /*for (int col = 0; col < totalNoOfCols; col++) {
             System.out.print(sh.getCell(col, row).getContents() + "\t");
             }*/

            //   statement.addBatch("UPDATE tblEmpWages SET DaysVacationFromBiometric ="+vacations+" WHERE PayRoll_Id ="+payrollId+" AND MONTH(PayPeriodStartDate) = 4 AND YEAR(PayPeriodStartDate)=2015 AND IsBlock=0");

            while (resultSet.next()) {
                resultMessage += resultSet.getString("NAME") + "#^$" + resultSet.getString("Updateby") + "#^$" + resultSet.getString("UpdatedDate") + "#^$" + resultSet.getString("EmpId") + "*@!";
            }

            if (resultMessage.equals("")) {

                resultMessage = "";

            }




        } catch (SQLException ex) {
            ex.printStackTrace();
            resultMessage = "<font style='color:red;'>Please try again later</font>";
            DataSourceDataProvider.getInstance().deleteWagesIfExceptionOccurs();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } catch (Exception ioe) {
            System.err.println(ioe);
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

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultMessage;
    }

    public String getPayRollHistoryEmployeeDetails(int empId, String modifiedDate) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;


        String resultMessage = "";

        java.sql.Date payRollDate = null;
        int rowsInserted = 0;
        try {



            connection = ConnectionProvider.getInstance().getConnection();
            String query = "SELECT CONCAT(FName,' ',MName,' ',LName) AS NAME,OrgName,DepartmentId,TitleTypeId,ShiftId,ClassificationId,Location,Gender,CurStatus,PF_Flag,EmpNo,SSN ,PassportNo,PFNo,TrainingPeriod,HireDate,UANNo,AadharNum,Termination,AddressLine1,Email1,Email2,FatherName,FatherTitle,City ,State,Zip,FatherPhone,HomePhoneNo,CellPhoneNo,WorkPhoneNo,BasicPay ,PaymentTypeId , DA , BankName,HRA ,AccNum AS BankAccountNumber, TA , Employer_PF ,RA , Employee_PF , Entertainment , Employer_ESI,KidsEducation ,Employee_ESI, VehcleAllowance , CCA ,   Life ,MiscPay ,  Health , SplAllowances ,Ded_Professional_Tax , LongTermbonus , Ded_Others ,  ProjectPay , GrossPay , AttAllowance , VariablePay ,OnProject, TotalCost,DATE(doPay) AS doPay,Prev_Employer_YTD_Gross , EmpSavings1 , EmpSavings2 ,tutionfees,hbLoanInterst,ppf,medicalIns,lifeIns,hraLifeInsuranceSavings,premium,eduInterest,fd,hbLoanPrinciple,mutualFunds,nsc ,WageComments , WageComments1 ,GeneralComments, ReferenceDetails ,TotalExpGross,   `InvLifePremium_80C`, `InvHouseLoanRepayment_80C`, `InvNSC_80C`,`InvPPFContribution_80C`,  `InvTutionFee_80C`, `InvELSS_80C`, `InvPostOfficeTermDeposit_80C`, `InvBankDeposits_80C`, `InvLICFromSal_80C`, `InvOthers_80C`, `InvContrToPF_80CCC`, `Inv80CCDNPSEmpCont_80CCC`, `InvTotalFrm80CAnd80CCC`, `DedFrom80CAnd80CCC`, `InvIntrOnBorrCap_80CCC`, `DedIntrOnBorrCap_80CCC`, `InvIntrOnParentsLIC_80D`, `DedIntrOnParentsLIC_80D`, `InvIntrOnSpouseLIC_80D`, `DedIntrOnSpouseLIC_80D`,DedIntrOnEdu_80E, `InvHRExemptions_80E`, `DedHRExemptions_80E`,EmpSavings3,EmpSavings4,EmpSavings5,ExpectedGrossYearly,PracticeId  FROM tblPayRollHistory WHERE EmpId=? AND updatedDate=?";
            prestatement = connection.prepareStatement(query);


            prestatement.setInt(1, empId);
            prestatement.setString(2, modifiedDate);

            resultSet = prestatement.executeQuery();

            /*for (int col = 0; col < totalNoOfCols; col++) {
             System.out.print(sh.getCell(col, row).getContents() + "\t");
             }*/

            //   statement.addBatch("UPDATE tblEmpWages SET DaysVacationFromBiometric ="+vacations+" WHERE PayRoll_Id ="+payrollId+" AND MONTH(PayPeriodStartDate) = 4 AND YEAR(PayPeriodStartDate)=2015 AND IsBlock=0");

            while (resultSet.next()) {

                resultMessage += resultSet.getString("NAME") + "#^$";
                resultMessage += resultSet.getString("OrgName") + "#^$";
                resultMessage += resultSet.getString("DepartmentId") + "#^$";
                resultMessage += resultSet.getString("TitleTypeId") + "#^$";
                resultMessage += DataSourceDataProvider.getInstance().getShiftByShiftId(resultSet.getInt("ShiftId")) + "#^$";
                resultMessage += DataSourceDataProvider.getInstance().getClassificationById(resultSet.getInt("ClassificationId")) + "#^$";
                //   resultMessage += DataSourceDataProvider.getInstance().getLocationByLocationId(resultSet.getInt("Location")) + "#^$";

                if (!"".equals(resultSet.getString("Location"))) {
                    
                    String location = DataSourceDataProvider.getInstance().getLocationNameById(resultSet.getString("Location"));
                    resultMessage += location + "#^$";
                } else {
                    resultMessage += "No Location Added#^$";
                }
                resultMessage += "" + "#^$";
                resultMessage += resultSet.getString("Gender") + "#^$";
                resultMessage += resultSet.getString("CurStatus") + "#^$";
                resultMessage += resultSet.getString("PF_Flag") + "#^$";
                resultMessage += resultSet.getString("EmpNo") + "#^$";
                resultMessage += resultSet.getString("SSN") + "#^$";
                resultMessage += resultSet.getString("PassportNo") + "#^$";
                resultMessage += resultSet.getString("PFNo") + "#^$";
                resultMessage += resultSet.getString("TrainingPeriod") + "#^$";
                resultMessage += "" + "#^$";
                resultMessage += resultSet.getString("HireDate") + "#^$";
                resultMessage += resultSet.getString("UANNo") + "#^$";

                resultMessage += resultSet.getString("AadharNum") + "#^$";
                resultMessage += resultSet.getString("Termination") + "#^$";
                //personal details

                resultMessage += resultSet.getString("AddressLine1") + "#^$";
                resultMessage += resultSet.getString("Email1") + "#^$";
                resultMessage += resultSet.getString("Email2") + "#^$";
                resultMessage += resultSet.getString("FatherName") + "#^$";
                resultMessage += resultSet.getString("FatherTitle") + "#^$";
                resultMessage += resultSet.getString("City") + "#^$";
                resultMessage += resultSet.getString("State") + "#^$";
                resultMessage += resultSet.getString("Zip") + "#^$";
                resultMessage += resultSet.getString("FatherPhone") + "#^$";
                resultMessage += resultSet.getString("HomePhoneNo") + "#^$";
                resultMessage += resultSet.getString("CellPhoneNo") + "#^$";
                resultMessage += resultSet.getString("WorkPhoneNo") + "#^$";

                //Payroll details


                resultMessage += resultSet.getString("BasicPay") + "#^$";

                resultMessage += DataSourceDataProvider.getInstance().getPaymentTypeById(resultSet.getInt("PaymentTypeId")) + "#^$";

                resultMessage += resultSet.getString("DA") + "#^$";
                resultMessage += resultSet.getString("BankName") + "#^$";
                resultMessage += resultSet.getString("HRA") + "#^$";
                resultMessage += resultSet.getString("BankAccountNumber") + "#^$";
                resultMessage += resultSet.getString("TA") + "#^$";
                resultMessage += resultSet.getString("Employer_PF") + "#^$";
                resultMessage += resultSet.getString("RA") + "#^$";
                resultMessage += resultSet.getString("Employee_PF") + "#^$";
                resultMessage += resultSet.getString("Entertainment") + "#^$";
                resultMessage += resultSet.getString("Employer_ESI") + "#^$";
                resultMessage += resultSet.getString("KidsEducation") + "#^$";
                resultMessage += resultSet.getString("Employee_ESI") + "#^$";
                resultMessage += resultSet.getString("VehcleAllowance") + "#^$";
                resultMessage += resultSet.getString("CCA") + "#^$";
                resultMessage += resultSet.getString("Life") + "#^$";
                resultMessage += resultSet.getString("MiscPay") + "#^$";
                resultMessage += resultSet.getString("Health") + "#^$";
                resultMessage += resultSet.getString("SplAllowances") + "#^$";
                resultMessage += resultSet.getString("Ded_Professional_Tax") + "#^$";
                resultMessage += resultSet.getString("LongTermbonus") + "#^$";
                resultMessage += resultSet.getString("Ded_Others") + "#^$";
                resultMessage += resultSet.getString("ProjectPay") + "#^$";
                resultMessage += resultSet.getString("GrossPay") + "#^$";
                resultMessage += resultSet.getString("AttAllowance") + "#^$";
                resultMessage += resultSet.getString("VariablePay") + "#^$";
                resultMessage += resultSet.getString("OnProject") + "#^$";

                resultMessage += "" + "#^$";
                resultMessage += "" + "#^$";

                resultMessage += resultSet.getString("TotalCost") + "#^$";
                resultMessage += "" + "#^$";
                resultMessage += "" + "#^$";
                resultMessage += resultSet.getString("doPay") + "#^$";

                //Insurence Details

                resultMessage += resultSet.getString("Prev_Employer_YTD_Gross") + "#^$";
                resultMessage += resultSet.getString("EmpSavings1") + "#^$";
                resultMessage += resultSet.getString("EmpSavings2") + "#^$";
                resultMessage += resultSet.getString("tutionfees") + "#^$";
                resultMessage += resultSet.getString("hbLoanInterst") + "#^$";
                resultMessage += resultSet.getString("ppf") + "#^$";
                resultMessage += resultSet.getString("medicalIns") + "#^$";
                resultMessage += resultSet.getString("lifeIns") + "#^$";
                resultMessage += resultSet.getString("hraLifeInsuranceSavings") + "#^$";
                resultMessage += resultSet.getString("premium") + "#^$";
                resultMessage += resultSet.getString("eduInterest") + "#^$";
                resultMessage += resultSet.getString("fd") + "#^$";
                resultMessage += resultSet.getString("hbLoanPrinciple") + "#^$";
                resultMessage += resultSet.getString("mutualFunds") + "#^$";
                resultMessage += resultSet.getString("nsc") + "#^$";

                //other Details


                resultMessage += resultSet.getString("WageComments") + "#^$";
                resultMessage += resultSet.getString("WageComments1") + "#^$";
                resultMessage += resultSet.getString("GeneralComments") + "#^$";
                resultMessage += resultSet.getString("ReferenceDetails") + "#^$";

                //TDS

                resultMessage += resultSet.getString("TotalExpGross") + "#^$";
                resultMessage += resultSet.getString("InvLifePremium_80C") + "#^$";
                resultMessage += resultSet.getString("InvHouseLoanRepayment_80C") + "#^$";
                resultMessage += resultSet.getString("InvNSC_80C") + "#^$";
                resultMessage += resultSet.getString("InvPPFContribution_80C") + "#^$";
                resultMessage += resultSet.getString("InvTutionFee_80C") + "#^$";
                resultMessage += resultSet.getString("InvELSS_80C") + "#^$";
                resultMessage += resultSet.getString("InvPostOfficeTermDeposit_80C") + "#^$";
                resultMessage += resultSet.getString("InvBankDeposits_80C") + "#^$";
                resultMessage += resultSet.getString("InvLICFromSal_80C") + "#^$";
                resultMessage += resultSet.getString("InvOthers_80C") + "#^$";
                resultMessage += resultSet.getString("InvContrToPF_80CCC") + "#^$";
                resultMessage += resultSet.getString("Inv80CCDNPSEmpCont_80CCC") + "#^$";
                resultMessage += resultSet.getString("InvTotalFrm80CAnd80CCC") + "#^$";
                resultMessage += resultSet.getString("DedFrom80CAnd80CCC") + "#^$";
                resultMessage += resultSet.getString("InvIntrOnBorrCap_80CCC") + "#^$";
                resultMessage += resultSet.getString("DedIntrOnBorrCap_80CCC") + "#^$";
                resultMessage += resultSet.getString("InvIntrOnParentsLIC_80D") + "#^$";
                resultMessage += resultSet.getString("DedIntrOnParentsLIC_80D") + "#^$";
                resultMessage += resultSet.getString("InvIntrOnSpouseLIC_80D") + "#^$";
                resultMessage += resultSet.getString("DedIntrOnSpouseLIC_80D") + "#^$";
                resultMessage += resultSet.getString("DedIntrOnEdu_80E") + "#^$";
                resultMessage += resultSet.getString("InvHRExemptions_80E") + "#^$";
                resultMessage += resultSet.getString("DedHRExemptions_80E") + "#^$";
                resultMessage += resultSet.getString("ExpectedGrossYearly") + "#^$";
                resultMessage += resultSet.getString("EmpSavings3") + "#^$";
                resultMessage += resultSet.getString("EmpSavings4") + "#^$";
                resultMessage += resultSet.getString("EmpSavings5") + "#^$";
                resultMessage += resultSet.getString("PracticeId");


            }

            if (resultMessage.equals("")) {

                resultMessage = "";

            }




        } catch (SQLException ex) {
            ex.printStackTrace();
            resultMessage = "<font style='color:red;'>Please try again later</font>";
            DataSourceDataProvider.getInstance().deleteWagesIfExceptionOccurs();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } catch (Exception ioe) {
            System.err.println(ioe);
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

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultMessage;
    }

    public String getWagesHistory(int empId) throws ServiceLocatorException {
        //  System.out.println("getWagesHistory method");
        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;


        String resultMessage = "";

        java.sql.Date payRollDate = null;
        int rowsInserted = 0;
        try {



            connection = ConnectionProvider.getInstance().getConnection();
            prestatement = connection.prepareStatement("SELECT EmpId ,Updatedby, UpdatedDate FROM tblWagesHistory WHERE EmpId=? ORDER BY UpdatedDate DESC");



            prestatement.setInt(1, empId);

            resultSet = prestatement.executeQuery();

            /*for (int col = 0; col < totalNoOfCols; col++) {
             System.out.print(sh.getCell(col, row).getContents() + "\t");
             }*/

            //   statement.addBatch("UPDATE tblEmpWages SET DaysVacationFromBiometric ="+vacations+" WHERE PayRoll_Id ="+payrollId+" AND MONTH(PayPeriodStartDate) = 4 AND YEAR(PayPeriodStartDate)=2015 AND IsBlock=0");

            while (resultSet.next()) {

                resultMessage += DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(resultSet.getInt("EmpId")) + "#^$" + resultSet.getString("Updatedby") + "#^$" + resultSet.getString("UpdatedDate") + "#^$" + resultSet.getString("EmpId") + "*@!";
            }

            if (resultMessage.equals("")) {

                resultMessage = "";

            }




        } catch (SQLException ex) {
            ex.printStackTrace();
            resultMessage = "<font style='color:red;'>Please try again later</font>";
            DataSourceDataProvider.getInstance().deleteWagesIfExceptionOccurs();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } catch (Exception ioe) {
            System.err.println(ioe);
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

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        // System.out.println("resultMessage-->" + resultMessage);
        return resultMessage;
    }

    public String getWagesHistoryEmployeeDetails(int empId, String modifiedDate) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement prestatement = null, prestatement1 = null;
        ResultSet resultSet = null, resultSet1 = null;


        String resultMessage = "";

        java.sql.Date payRollDate = null;
        int rowsInserted = 0;
        try {



            connection = ConnectionProvider.getInstance().getConnection();
            String query = "SELECT  PaymentTypeId,PayPeriodStartDate,DaysInMonth,DaysWorked,PayrollDate,NetPaid,PayRoll_id,GrossPay,Ded_Employee_PF,TDS,Ded_Professional_Tax,DaysProject,Ded_Income_Tax,DaysVacation,Ded_Corporate_Loan,LeavesAvailable,Ded_Life,DaysHolidays,Ded_Health,Daysweekends,Ded_Others, Freeze_Payroll,DoRepaymentFlag,"
                    + "BasicPay1,MaidServices,DA,Entertainment,HRA,KidsEducation,TA,VehcleAllowance,RA,LongTermBonus,Life,Employer_PF,Health,SplAllowance,CCA1,AttAllowance,MiscPay,ProjectPay,Employee_PF,VariablePay,"
                    + "Earned_Basic,Earned_Food,Earned_DA,Earned_Laundry,Earned_HRA,Earned_MaidServices,Earned_TA,Earned_Entertainment,Earned_RA,Earned_KidsEducation,Earned_Life,Earned_vehicleAllowance,Earned_Health,Earned_LongTermBonus,Earned_CCA,Earned_MiscPay,Earned_ProjectPay,Earned_Employeer_Pf,Earned_Allowance,Earned_SplAllowance,Ded_TDS_Bonus,Earned_EmployeePF,Earned_Gross_Pay,Earned_Bonus,Earned_Net_Paid,Ded_Others,TaxableIncome,Earned_EmpOthers,IsBlock,ProjectEndDate,"
                    + "YTD_Gross,YTD_TaxableIncome,YTD_LongTerm,YTD_Comissions,YTD_PF,YTD_TDS_Salary,YTD_PTax,YTD_TDS_Comissions,YTD_Life,YTD_TDS_Collected,YTD_TA,Tax_Liability,YTD_RA,Tax_Liability_Bonus,YTD_Others,Diff_tax_salary,Ytd_ExpTaxFree,Diff_tax_bonus,YTD_Projectpay,YTD_Savings1,YTD_Savings2,"
                    + "InvLifePremium_80C,InvHouseLoanRepayment_80C,InvNSC_80C,InvPPFContribution_80C,InvTutionFee_80C,InvELSS_80C,InvPostOfficeTermDeposit_80C,InvBankDeposits_80C,InvLICFromSal_80C,InvOthers_80C,InvContrToPF_80CCC,Inv80CCDNPSEmpCont_80CCC, InvTotalFrm80CAnd80CCC, `DedFrom80CAnd80CCC`,InvIntrOnBorrCap_80CCC,`DedIntrOnBorrCap_80CCC`,InvIntrOnParentsLIC_80D,`DedIntrOnParentsLIC_80D`, `InvIntrOnSpouseLIC_80D`, `DedIntrOnSpouseLIC_80D`,`DedIntrOnEdu_80E`,`InvHRExemptions_80E`, `DedHRExemptions_80E`,TotalExpGross,EmployeeESI,EmployerESI,Earned_Employee_ESI,Earned_Employer_ESI,DaysVacationfromHubble FROM tblWagesHistory WHERE EmpId=? AND updatedDate=?";
            prestatement = connection.prepareStatement(query);

            //   System.out.println("query--->" + query);

            prestatement.setInt(1, empId);
            prestatement.setString(2, modifiedDate);

            resultSet = prestatement.executeQuery();

            /*for (int col = 0; col < totalNoOfCols; col++) {
             System.out.print(sh.getCell(col, row).getContents() + "\t");
             }*/

            //   statement.addBatch("UPDATE tblEmpWages SET DaysVacationFromBiometric ="+vacations+" WHERE PayRoll_Id ="+payrollId+" AND MONTH(PayPeriodStartDate) = 4 AND YEAR(PayPeriodStartDate)=2015 AND IsBlock=0");

            while (resultSet.next()) {

                query = "select CONCAT(FName,' ',MName,' ',LName) AS NAME,classificationId,BankName,TitleTypeId,AccNum FROM tblEmployee WHERE id=?";
                prestatement1 = connection.prepareStatement(query);
                prestatement1.setInt(1, empId);
                resultSet1 = prestatement1.executeQuery();
                String name = "";
                String classification = "";
                String title = "";
                String bankName = "";
                String accountNum = "";
                if (resultSet1.next()) {
                    name = resultSet1.getString("NAME");
                    title = resultSet1.getString("TitleTypeId");
                    classification = DataSourceDataProvider.getInstance().getClassificationById(resultSet1.getInt("classificationId"));
                    bankName = resultSet1.getString("BankName");
                    accountNum = resultSet1.getString("AccNum");

                }
                resultMessage += name + "#^$";//employee name
                resultMessage += title + "#^$";//title
                resultMessage += classification + "#^$";//classification
                resultMessage += DataSourceDataProvider.getInstance().getPaymentTypeById(resultSet.getInt("PaymentTypeId")) + "#^$";
                resultMessage += resultSet.getString("PayPeriodStartDate") + "#^$";
                resultMessage += bankName + "#^$";//Bank Name
                resultMessage += resultSet.getString("DaysInMonth") + "#^$";
                resultMessage += accountNum + "#^$";//account number
                resultMessage += resultSet.getString("DaysWorked") + "#^$";
                resultMessage += resultSet.getString("PayrollDate") + "#^$";
                resultMessage += resultSet.getString("PayRoll_id") + "#^$";
                resultMessage += resultSet.getString("NetPaid") + "#^$";
                resultMessage += resultSet.getString("PayRoll_id") + "#^$";
                resultMessage += resultSet.getString("GrossPay") + "#^$";
                resultMessage += resultSet.getString("Ded_Employee_PF") + "#^$";
                resultMessage += resultSet.getString("TDS") + "#^$";
                resultMessage += resultSet.getString("Ded_Professional_Tax") + "#^$";
                resultMessage += resultSet.getString("DaysProject") + "#^$";
                resultMessage += resultSet.getString("Ded_Income_Tax") + "#^$";
                resultMessage += resultSet.getString("DaysVacation") + "#^$";
                resultMessage += resultSet.getString("Ded_Corporate_Loan") + "#^$";
                resultMessage += resultSet.getString("LeavesAvailable") + "#^$";
                resultMessage += resultSet.getString("Ded_Life") + "#^$";
                resultMessage += resultSet.getString("DaysHolidays") + "#^$";
                resultMessage += resultSet.getString("Ded_Health") + "#^$";
                resultMessage += resultSet.getString("Daysweekends") + "#^$";
                resultMessage += resultSet.getString("Ded_Others") + "#^$";
                resultMessage += resultSet.getString("Freeze_Payroll") + "#^$";
                resultMessage += resultSet.getString("DoRepaymentFlag") + "#^$";

//                         
                //Payroll details


                resultMessage += resultSet.getString("BasicPay1") + "#^$";
                resultMessage += resultSet.getString("MaidServices") + "#^$";
                resultMessage += resultSet.getString("DA") + "#^$";
                resultMessage += resultSet.getString("Entertainment") + "#^$";
                resultMessage += resultSet.getString("HRA") + "#^$";
                resultMessage += resultSet.getString("KidsEducation") + "#^$";
                resultMessage += resultSet.getString("TA") + "#^$";
                resultMessage += resultSet.getString("VehcleAllowance") + "#^$";
                resultMessage += resultSet.getString("RA") + "#^$";
                resultMessage += resultSet.getString("LongTermbonus") + "#^$";
                resultMessage += resultSet.getString("Life") + "#^$";
                resultMessage += resultSet.getString("Employer_PF") + "#^$";
                resultMessage += resultSet.getString("Health") + "#^$";
                resultMessage += "" + "#^$";//another health
                resultMessage += resultSet.getString("SplAllowance") + "#^$";
                resultMessage += resultSet.getString("CCA1") + "#^$";
                resultMessage += resultSet.getString("AttAllowance") + "#^$";
                resultMessage += resultSet.getString("MiscPay") + "#^$";
                resultMessage += resultSet.getString("ProjectPay") + "#^$";
                resultMessage += resultSet.getString("Employee_PF") + "#^$";
                resultMessage += classification + "#^$";//classification
                resultMessage += resultSet.getString("GrossPay") + "#^$";
                resultMessage += resultSet.getString("VariablePay") + "#^$";

                // Actual details

                resultMessage += resultSet.getString("Earned_Basic") + "#^$";
                resultMessage += resultSet.getString("Earned_Food") + "#^$";
                resultMessage += resultSet.getString("Earned_DA") + "#^$";
                resultMessage += resultSet.getString("Earned_Laundry") + "#^$";
                resultMessage += resultSet.getString("Earned_HRA") + "#^$";
                resultMessage += resultSet.getString("Earned_MaidServices") + "#^$";
                resultMessage += resultSet.getString("Earned_TA") + "#^$";
                resultMessage += resultSet.getString("Earned_Entertainment") + "#^$";
                resultMessage += resultSet.getString("Earned_RA") + "#^$";
                resultMessage += resultSet.getString("Earned_KidsEducation") + "#^$";
                resultMessage += resultSet.getString("Earned_Life") + "#^$";
                resultMessage += resultSet.getString("Earned_vehicleAllowance") + "#^$";
                resultMessage += resultSet.getString("Earned_Health") + "#^$";
                resultMessage += resultSet.getString("Earned_LongTermBonus") + "#^$";
                resultMessage += resultSet.getString("Earned_CCA") + "#^$";
                resultMessage += resultSet.getString("Earned_MiscPay") + "#^$";
                resultMessage += resultSet.getString("Earned_ProjectPay") + "#^$";
                resultMessage += resultSet.getString("Earned_Employeer_Pf") + "#^$";
                resultMessage += resultSet.getString("Earned_Allowance") + "#^$";
                resultMessage += resultSet.getString("Earned_SplAllowance") + "#^$";
                resultMessage += resultSet.getString("Ded_TDS_Bonus") + "#^$";
                resultMessage += resultSet.getString("Earned_EmployeePF") + "#^$";
                resultMessage += resultSet.getString("Earned_Gross_Pay") + "#^$";
                resultMessage += resultSet.getString("Earned_Bonus") + "#^$";
                resultMessage += resultSet.getString("Earned_Net_Paid") + "#^$";
                resultMessage += resultSet.getString("Ded_Others") + "#^$";
                resultMessage += resultSet.getString("TaxableIncome") + "#^$";
                resultMessage += resultSet.getString("Earned_EmpOthers") + "#^$";
                resultMessage += resultSet.getString("IsBlock") + "#^$";
                resultMessage += resultSet.getString("ProjectEndDate") + "#^$";

//YTD
                resultMessage += resultSet.getString("YTD_Gross") + "#^$";
                resultMessage += resultSet.getString("YTD_TaxableIncome") + "#^$";
                resultMessage += resultSet.getString("YTD_LongTerm") + "#^$";
                resultMessage += resultSet.getString("YTD_Comissions") + "#^$";
                resultMessage += resultSet.getString("YTD_PF") + "#^$";
                resultMessage += resultSet.getString("YTD_TDS_Salary") + "#^$";
                resultMessage += resultSet.getString("YTD_PTax") + "#^$";
                resultMessage += resultSet.getString("YTD_TDS_Comissions") + "#^$";
                resultMessage += resultSet.getString("YTD_Life") + "#^$";
                resultMessage += resultSet.getString("YTD_TDS_Collected") + "#^$";
                resultMessage += resultSet.getString("YTD_TA") + "#^$";
                resultMessage += resultSet.getString("Tax_Liability") + "#^$";
                resultMessage += resultSet.getString("YTD_RA") + "#^$";
                resultMessage += resultSet.getString("Tax_Liability_Bonus") + "#^$";
                resultMessage += resultSet.getString("YTD_Others") + "#^$";
                resultMessage += resultSet.getString("Diff_tax_salary") + "#^$";
                resultMessage += resultSet.getString("Ytd_ExpTaxFree") + "#^$";
                resultMessage += resultSet.getString("Diff_tax_bonus") + "#^$";
                resultMessage += resultSet.getString("YTD_Projectpay") + "#^$";
                resultMessage += resultSet.getString("YTD_Savings1") + "#^$";
                resultMessage += resultSet.getString("YTD_Savings2") + "#^$";


//Investment U/S 80C

                resultMessage += resultSet.getString("InvLifePremium_80C") + "#^$";
                resultMessage += resultSet.getString("InvHouseLoanRepayment_80C") + "#^$";
                resultMessage += resultSet.getString("InvNSC_80C") + "#^$";
                resultMessage += resultSet.getString("InvPPFContribution_80C") + "#^$";
                resultMessage += resultSet.getString("InvTutionFee_80C") + "#^$";
                resultMessage += resultSet.getString("InvELSS_80C") + "#^$";
                resultMessage += resultSet.getString("InvPostOfficeTermDeposit_80C") + "#^$";
                resultMessage += resultSet.getString("InvBankDeposits_80C") + "#^$";
                resultMessage += resultSet.getString("InvLICFromSal_80C") + "#^$";
                resultMessage += resultSet.getString("InvOthers_80C") + "#^$";

//Investment U/S 80CCC

                resultMessage += resultSet.getString("InvContrToPF_80CCC") + "#^$";
                resultMessage += resultSet.getString("Inv80CCDNPSEmpCont_80CCC") + "#^$";
                resultMessage += resultSet.getString("InvTotalFrm80CAnd80CCC") + "#^$";
                resultMessage += resultSet.getString("DedFrom80CAnd80CCC") + "#^$";
                resultMessage += resultSet.getString("InvIntrOnBorrCap_80CCC") + "#^$";
                resultMessage += resultSet.getString("DedIntrOnBorrCap_80CCC") + "#^$";

//Medical Insurance U/S80d

                resultMessage += resultSet.getString("InvIntrOnParentsLIC_80D") + "#^$";
                resultMessage += resultSet.getString("DedIntrOnParentsLIC_80D") + "#^$";
                resultMessage += resultSet.getString("InvIntrOnSpouseLIC_80D") + "#^$";
                resultMessage += resultSet.getString("DedIntrOnSpouseLIC_80D") + "#^$";
                resultMessage += resultSet.getString("DedIntrOnEdu_80E") + "#^$";
                resultMessage += resultSet.getString("InvHRExemptions_80E") + "#^$";
                resultMessage += resultSet.getString("DedHRExemptions_80E") + "#^$";
                resultMessage += resultSet.getString("TotalExpGross") + "#^$";
                resultMessage += resultSet.getString("EmployerESI") + "#^$";
                resultMessage += resultSet.getString("EmployeeESI") + "#^$";
                resultMessage += resultSet.getString("Earned_Employer_ESI") + "#^$";
                resultMessage += resultSet.getString("Earned_Employee_ESI") + "#^$";
                resultMessage += resultSet.getString("DaysVacationfromHubble") + "#^$";


            }

            if (resultMessage.equals("")) {

                resultMessage = "";

            }




        } catch (SQLException ex) {
            ex.printStackTrace();
            resultMessage = "<font style='color:red;'>Please try again later</font>";
            DataSourceDataProvider.getInstance().deleteWagesIfExceptionOccurs();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } catch (Exception ioe) {
            System.err.println(ioe);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (prestatement != null) {
                    prestatement.close();
                    prestatement = null;
                }
                if (prestatement1 != null) {
                    prestatement1.close();
                    prestatement1 = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        // System.out.println("resultMessage-->" + resultMessage);
        return resultMessage;
    }

    public String getTEFDetails(int empId) throws ServiceLocatorException {
        // System.out.println("getTEFDetails method");
        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        stringBuffer = new StringBuffer();

        String resultMessage = "";

        java.sql.Date payRollDate = null;
        int rowsInserted = 0;
        int i = 0;
        try {


            //    System.out.println("empid-->" + empId);
            connection = ConnectionProvider.getInstance().getConnection();
            prestatement = connection.prepareStatement("SELECT tblEmpTaxExemptionDetails.Id,EmpId,tblEmpTaxExemptionDetails.STATUS,AppliedBy,SavingsAmount,AttachmentLocation,AttachmentName,PaymentDate,ApprovedBy,ApprovedAmount,ApproverComments,ExemptionId,tblLKTaxExemptions.ExemptionType FROM tblEmpTaxExemptionDetails left outer join tblLKTaxExemptions  ON (tblEmpTaxExemptionDetails.ExemptionId=tblLKTaxExemptions.id) WHERE EmpId=? and CheckFlag=0 ORDER BY tblEmpTaxExemptionDetails.STATUS = 'Applied'  DESC ");



            prestatement.setInt(1, empId);

            resultSet = prestatement.executeQuery();

            while (resultSet.next()) {

                //  System.out.println(i);
                i++;
                String attachmentName = "";
                if (resultSet.getString("AttachmentName") == " " || resultSet.getString("AttachmentName") == null || resultSet.getString("AttachmentName") == "null" || resultSet.getString("AttachmentName").length() == 0) {
                    attachmentName = "--";
                } else {
                    attachmentName = resultSet.getString("AttachmentName");
                }

                String attachmentLoc = "";
                if (resultSet.getString("AttachmentLocation") == " " || resultSet.getString("AttachmentLocation") == null || resultSet.getString("AttachmentLocation") == "null" || resultSet.getString("AttachmentLocation").length() == 0) {
                    attachmentLoc = "--";
                } else {
                    attachmentLoc = resultSet.getString("AttachmentLocation");
                }
                String approvedBy = "";
                if (resultSet.getString("ApprovedBy") == " " || resultSet.getString("ApprovedBy") == null || resultSet.getString("ApprovedBy") == "null" || resultSet.getString("ApprovedBy").length() == 0) {
                    approvedBy = "--";
                } else {
                    approvedBy = resultSet.getString("ApprovedBy");
                }

                String approvedCom = "";
                if (resultSet.getString("ApproverComments") == " " || resultSet.getString("ApproverComments") == null || resultSet.getString("ApproverComments") == "null" || resultSet.getString("ApproverComments").length() == 0) {
                    approvedCom = "--";
                } else {
                    approvedCom = resultSet.getString("ApproverComments");
                }



                resultMessage = resultMessage + i + "|" + resultSet.getInt("EmpId") + "|" + resultSet.getString("STATUS") + "|" + resultSet.getString("AppliedBy") + "|" + resultSet.getDouble("SavingsAmount") + "|" + attachmentName + "|" + attachmentLoc + "|" + resultSet.getInt("Id") + "|" + resultSet.getDate("PaymentDate") + "|" + approvedBy + "|" + resultSet.getDouble("ApprovedAmount") + "|" + approvedCom + "|" + resultSet.getInt("ExemptionId") + "|" + resultSet.getString("ExemptionType") + "^";
            }
            // System.out.println("resultMessage-->" + resultMessage);
            if (resultMessage.equals("")) {

                resultMessage = "";

            }
            // System.out.println("1-->" + resultMessage);
            stringBuffer.append(resultMessage);
            stringBuffer.append("addto");

            stringBuffer.append(i);


        } catch (SQLException ex) {
            ex.printStackTrace();
            resultMessage = "<font style='color:red;'>Please try again later</font>";
            DataSourceDataProvider.getInstance().deleteWagesIfExceptionOccurs();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } catch (Exception ioe) {
            ioe.printStackTrace();
            System.err.println(ioe);
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

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        // System.out.println("resultMessage-->" + stringBuffer.toString());
        return stringBuffer.toString();
    }

    public String getNamesByDesignation(int isManager, int isTeamLead, String departmentId) throws ServiceLocatorException {
        StringBuffer reportsToBuffer = new StringBuffer();
        String loginId = null;
        String topManagementPeople[] = null;
        boolean isTopManager = false;

        queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee where 1=1 and Country = 'India' ";

        try {
            if (isManager == 1 && isTeamLead == 1) {
                queryString += " and  (IsManager=1 OR IsTeamLead=1)";
            } else {
                if (isManager == 1) {
                    queryString += " and  IsManager=1";
                }
                if (isTeamLead == 1) {
                    queryString += " and IsTeamLead=1";
                }
            }
            if (!departmentId.equals("")) {
                queryString += " and departmentId='" + departmentId + "'";
            }

            //  System.out.println("queryString-->" + queryString);
            //queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee WHERE DepartmentId LIKE '" + deptName + "' AND CurStatus='Active' ORDER BY EmployeeName";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            reportsToBuffer.append("<xml version=\"1.0\">");
            reportsToBuffer.append("<TEAM>");
            reportsToBuffer.append("<USER userId=\"\">All</USER>");


            while (resultSet.next()) {
                loginId = resultSet.getString("LoginId");

                reportsToBuffer.append("<USER userId=\"" + loginId + "\">");
                reportsToBuffer.append(resultSet.getString("EmployeeName"));
                reportsToBuffer.append("</USER>");

            }
            reportsToBuffer.append("</TEAM>");
            reportsToBuffer.append("</xml>");
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
        // System.out.println("Team List: " + reportsToBuffer.toString());
        return reportsToBuffer.toString();
    }

    public String getEmpTefDetailsForOverlay(int tefId) throws ServiceLocatorException {
     //  System.out.println("getWagesHistory method"+tefId);
        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        JSONObject taxJson = null;

        String resultMessage = "";

        java.sql.Date payRollDate = null;
        int rowsInserted = 0;
        try {



            connection = ConnectionProvider.getInstance().getConnection();
            //  prestatement = connection.prepareStatement("SELECT tblEmpTaxExemptionDetails.Id,ExemptionId,tblLKTaxExemptions.Category,tblLKTaxExemptions.ExemptionType,SavingsAmount,tblEmpTaxExemptionDetails.STATUS,ApprovedAmount,PaymentDate,ApproverComments,AttachmentName,tblEmpTaxExemptionDetails.EmpId from tblEmpTaxExemptionDetails left outer join tblLKTaxExemptions  ON (tblEmpTaxExemptionDetails.ExemptionId=tblLKTaxExemptions.id) where tblEmpTaxExemptionDetails.Id = ?");
           prestatement = connection.prepareStatement("SELECT tblEmpTaxExemptionDetails.Id,ExemptionId,tblLKTaxExemptions.Category,tblLKTaxExemptions.ExemptionType,SavingsAmount,tblEmpTaxExemptionDetails.STATUS,ApprovedAmount,PaymentDate,ApproverComments,AttachmentName,tblEmpTaxExemptionDetails.EmpId,ValidityDate,PAN_No,Owner_Name,Comments,SavingsType,FinancialYear,RentStartDate,RentEndDate,MonthlyAmount,PolicyNumber,LICPremium,Form12BBAttachmentName,HouseAddress from tblEmpTaxExemptionDetails left outer join tblLKTaxExemptions  ON (tblEmpTaxExemptionDetails.ExemptionId=tblLKTaxExemptions.id) where tblEmpTaxExemptionDetails.Id = ?");



            prestatement.setInt(1, tefId);

            resultSet = prestatement.executeQuery();

            /*for (int col = 0; col < totalNoOfCols; col++) {
             System.out.print(sh.getCell(col, row).getContents() + "\t");
             }*/

            //   statement.addBatch("UPDATE tblEmpWages SET DaysVacationFromBiometric ="+vacations+" WHERE PayRoll_Id ="+payrollId+" AND MONTH(PayPeriodStartDate) = 4 AND YEAR(PayPeriodStartDate)=2015 AND IsBlock=0");

            while (resultSet.next()) {
                String comments = "";
                String attachment = "";
                String Form12BBAttachmentName = "";
                if (resultSet.getString("ApproverComments") == null || resultSet.getString("ApproverComments").length() == 0) {
                    comments = "--";
                } else {
                    comments = resultSet.getString("ApproverComments");
                }

                if (resultSet.getString("AttachmentName") == null || resultSet.getString("AttachmentName").length() == 0) {
                    attachment = "--";
                } else {
                    attachment = resultSet.getString("AttachmentName");
                }
                if (resultSet.getString("Form12BBAttachmentName") == null || resultSet.getString("Form12BBAttachmentName").length() == 0) {
                    Form12BBAttachmentName = "--";
                } else {
                    Form12BBAttachmentName = resultSet.getString("Form12BBAttachmentName");
                }


                //  resultMessage += resultSet.getString("Id") + "#" + resultSet.getString("ExemptionId") + "#" + resultSet.getString("ExemptionType") + "#" + resultSet.getString("SavingsAmount") + "#" + resultSet.getString("STATUS") + "#" + resultSet.getString("ApprovedAmount") + "#" + resultSet.getString("PaymentDate") + "#" + comments + "#" + attachment + "#" + resultSet.getInt("EmpId") + "#" + resultSet.getInt("Category");
//                  resultMessage += resultSet.getString("Id") + "#" + resultSet.getString("ExemptionId") + "#" + resultSet.getString("ExemptionType") 
//                          + "#" + resultSet.getString("SavingsAmount") + "#" + resultSet.getString("STATUS") + "#" + resultSet.getString("ApprovedAmount") + "#" + resultSet.getString("PaymentDate") 
//                          + "#" + comments + "#" + attachment + "#" + resultSet.getInt("EmpId") + "#" + resultSet.getInt("Category")
//                          + '#' + resultSet.getString("Comments") + '#' + resultSet.getString("SavingsType") 
//                          + '#' + resultSet.getString("PAN_No") + '#' + resultSet.getString("Owner_Name") + '#' + resultSet.getDate("ValidityDate");
                taxJson = new JSONObject();
                taxJson.put("Id", resultSet.getString("Id"));
                taxJson.put("ExemptionId", resultSet.getString("ExemptionId"));
                taxJson.put("ExemptionType", resultSet.getString("ExemptionType"));
                taxJson.put("SavingsAmount", resultSet.getString("SavingsAmount"));
                taxJson.put("STATUS", resultSet.getString("STATUS"));
                taxJson.put("ApprovedAmount", resultSet.getString("ApprovedAmount"));
                taxJson.put("ApproverComments", comments);
                taxJson.put("attachment", attachment);
                taxJson.put("EmpId", resultSet.getInt("EmpId"));
                taxJson.put("Category", resultSet.getInt("Category"));
                taxJson.put("Comments", resultSet.getString("Comments"));
                taxJson.put("SavingsType", resultSet.getString("SavingsType"));
                taxJson.put("PAN_No", resultSet.getString("PAN_No"));
                taxJson.put("Owner_Name", resultSet.getString("Owner_Name"));
                 
                  taxJson.put("Form12BBAttachmentName",Form12BBAttachmentName);
                if (resultSet.getString("ValidityDate") != null && !"".equals(resultSet.getString("ValidityDate"))) {
                    taxJson.put("ValidityDate", DateUtility.getInstance().convertToviewFormat(resultSet.getString("ValidityDate")));

                } else {
                    taxJson.put("ValidityDate", "");
                }
                if (resultSet.getString("PaymentDate") != null && !"".equals(resultSet.getString("PaymentDate"))) {
                    taxJson.put("PaymentDate", DateUtility.getInstance().convertToviewFormat(resultSet.getString("PaymentDate")));

                } else {
                    taxJson.put("PaymentDate", "");
                }

                taxJson.put("FinancialYear", resultSet.getString("FinancialYear"));
                taxJson.put("MonthlyAmount", resultSet.getString("MonthlyAmount"));

                if (resultSet.getString("RentStartDate") != null && !"".equals(resultSet.getString("RentStartDate"))) {
                    taxJson.put("RentStartDate", resultSet.getString("RentStartDate"));
                } else {
                    taxJson.put("RentStartDate", "");
                }
                if (resultSet.getString("RentEndDate") != null && !"".equals(resultSet.getString("RentEndDate"))) {
                    taxJson.put("RentEndDate", resultSet.getString("RentEndDate"));
                } else {
                    taxJson.put("RentEndDate", "");
                }
 if (resultSet.getString("PolicyNumber") != null && !"".equals(resultSet.getString("PolicyNumber"))) {
                    taxJson.put("PolicyNumber", resultSet.getString("PolicyNumber"));
                } else {
                    taxJson.put("PolicyNumber", "");
                }
  if (resultSet.getString("LICPremium") != null && !"".equals(resultSet.getString("LICPremium"))) {
                    taxJson.put("licPremium", resultSet.getString("LICPremium"));
                } else {
                    taxJson.put("licPremium", "");
                }
     taxJson.put("PAN_No", resultSet.getString("PAN_No"));
                taxJson.put("Owner_Name", resultSet.getString("Owner_Name"));
                 if (resultSet.getString("HouseAddress") != null && !"".equals(resultSet.getString("HouseAddress"))) {
                    taxJson.put("HouseAddress", resultSet.getString("HouseAddress"));
                } else {
                    taxJson.put("HouseAddress", "");
                }
            }
           

//            if (resultMessage.equals("")) {
//
//                resultMessage = "";
//
//            }




        } catch (SQLException ex) {
            ex.printStackTrace();
            resultMessage = "<font style='color:red;'>Please try again later</font>";
            DataSourceDataProvider.getInstance().deleteWagesIfExceptionOccurs();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } catch (Exception ioe) {
            System.err.println(ioe);
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

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //System.out.println("resultMessage-->" + resultMessage);
        return taxJson.toString();
    }

   public boolean addTaxAssumption(PayrollAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
       // System.out.println("start addTaxAssumption");
        boolean isInserted = false;
        // String queryString = "INSERT INTO tblEmpTaxExemptionDetails(EmpId,ExemptionId,AppliedBy,SavingsAmount,AttachmentName,AttachmentLocation,PaymentDate) values(?,?,?,?,?,?,?)";
        queryString = "INSERT INTO tblEmpTaxExemptionDetails(EmpId,ExemptionId,AppliedBy,SavingsAmount,AttachmentName,AttachmentLocation,PaymentDate,ValidityDate,SavingsType,Comments,PAN_No,Owner_Name,OrgId,FinancialYear,RentStartDate,RentEndDate,MonthlyAmount,AppliedDate,PolicyNumber,LICPremium,Form12BBAttachmentName,Form12BBAttachmentPath,HouseAddress) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, Integer.parseInt(ajaxHandlerAction.getEmpId()));
            preparedStatement.setInt(2, Integer.parseInt(ajaxHandlerAction.getOverLayExemptionType()));
            // preparedStatement.setString(3, ajaxHandlerAction.getOverLayStatus());
            preparedStatement.setString(3, ajaxHandlerAction.getCreatedBy());

            preparedStatement.setDouble(4, ajaxHandlerAction.getOverlaySavingAmount());
            
            preparedStatement.setString(5, ajaxHandlerAction.getFileFileName());
            preparedStatement.setString(6, ajaxHandlerAction.getAttachmentLocation());
            if (ajaxHandlerAction.getPaymentDateEmp() != null && !"".equals(ajaxHandlerAction.getPaymentDateEmp())) {
                preparedStatement.setDate(7, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getPaymentDateEmp()));
            } else {
                preparedStatement.setDate(7, null);
            }
            if (ajaxHandlerAction.getValidityDate() != null && !"".equals(ajaxHandlerAction.getValidityDate())) {
                preparedStatement.setDate(8, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getValidityDate()));
            } else {
                preparedStatement.setDate(8, null);
            }
            preparedStatement.setString(9, ajaxHandlerAction.getTefType());
            preparedStatement.setString(10, ajaxHandlerAction.getComments());
            if (Integer.parseInt(ajaxHandlerAction.getOverLayExemptionType()) == 18) {
                preparedStatement.setString(11, ajaxHandlerAction.getPanNumber());
                preparedStatement.setString(12, ajaxHandlerAction.getOwnerName());
            } else {
                preparedStatement.setString(11, " ");
                preparedStatement.setString(12, " ");
            }
            preparedStatement.setInt(13, ajaxHandlerAction.getOrgId());
              preparedStatement.setString(14, ajaxHandlerAction.getFinancialYear());
             if (Integer.parseInt(ajaxHandlerAction.getOverLayExemptionType()) == 18) {
          
            if (ajaxHandlerAction.getRentStartDate() != null && !"".equals(ajaxHandlerAction.getRentStartDate())) {
                preparedStatement.setDate(15, DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(ajaxHandlerAction.getRentStartDate())));
            } else {
                preparedStatement.setDate(15, null);
            }

            if (ajaxHandlerAction.getRentEndDate() != null && !"".equals(ajaxHandlerAction.getRentEndDate())) {
                preparedStatement.setDate(16, DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(ajaxHandlerAction.getRentEndDate())));
            } else {
                preparedStatement.setDate(16, null);
            }

            preparedStatement.setDouble(17, ajaxHandlerAction.getMonthlyAmount());
         
             }else{
                   preparedStatement.setDate(15, null);
                     preparedStatement.setDate(16, null);
                      preparedStatement.setDouble(17, 0.00);
             }
                preparedStatement.setTimestamp(18, DateUtility.getInstance().getCurrentMySqlDateTime());
                preparedStatement.setString(19, ajaxHandlerAction.getPolicyNumber());
                 if (Integer.parseInt(ajaxHandlerAction.getOverLayExemptionType()) == 1) {
                  preparedStatement.setString(20, ajaxHandlerAction.getLicPremium());
                 }
                 else{
                      preparedStatement.setString(20, "");
                 }
                   if(Integer.parseInt(ajaxHandlerAction.getOverLayExemptionType()) == 18 && ajaxHandlerAction.getOverlaySavingAmount() > 100000){
                  
                   preparedStatement.setString(21, ajaxHandlerAction.getFile1FileName());
                   preparedStatement.setString(22, ajaxHandlerAction.getAttachmentLocation1());
                  }else{
                        preparedStatement.setString(21, "");
                        preparedStatement.setString(22, "");
                  }
                   preparedStatement.setString(23, ajaxHandlerAction.getHouseAddress());
            isInserted = preparedStatement.execute();
           // System.out.println("end addTaxAssumption");
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isInserted;

    }

    public boolean upadteTaxExemption(PayrollAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {
        // System.out.println("upadteTaxExemption method");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean isUpdated = false;
        String queryString = "";


        //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
        //if(roleName.equalsIgnoreCase("Employee"))
        //queryString = "update tblEmpTaxExemptionDetails set ExemptionId=?,SavingsAmount=?,Status=?,PaymentDate=? where Id=?";
        // System.out.println("queryString-->" + queryString);
// System.out.println("ajaxHandlerAction.getAttachmentLocation()-->"+ajaxHandlerAction.getAttachmentLocation());
// System.out.println("ajaxHandlerAction.getFileFileName()-->"+ajaxHandlerAction.getFileFileName());
          queryString = "update tblEmpTaxExemptionDetails set ExemptionId=?,SavingsAmount=?,Status=?,PaymentDate=?,ValidityDate=?,SavingsType=?,Comments=?,PAN_No=?,Owner_Name=?,RentStartDate=?,RentEndDate=?,MonthlyAmount=?,FinancialYear=?,PolicyNumber=?,LICPremium=?,AttachmentName=?,AttachmentLocation=?,Form12BBAttachmentName=?,Form12BBAttachmentPath=?,HouseAddress=? ";

       
     
           
       
       queryString = queryString + " WHERE Id=" + ajaxHandlerAction.getExemptionId();
      // System.out.println("queryString-->" + queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            //  preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());
            preparedStatement.setString(1, ajaxHandlerAction.getOverLayExemptionType());



            preparedStatement.setDouble(2, ajaxHandlerAction.getOverlaySavingAmount());
            preparedStatement.setString(3, ajaxHandlerAction.getOverLayStatus());
            if (ajaxHandlerAction.getPaymentDateEmp() != null && !"".equals(ajaxHandlerAction.getPaymentDateEmp())) {
                preparedStatement.setDate(4, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getPaymentDateEmp()));
            } else {
                preparedStatement.setDate(4, null);
            }
            if (ajaxHandlerAction.getValidityDate() != null && !"".equals(ajaxHandlerAction.getValidityDate())) {
                preparedStatement.setDate(5, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getValidityDate()));
            } else {
                preparedStatement.setDate(5, null);
            }
            preparedStatement.setString(6, ajaxHandlerAction.getTefType());
            preparedStatement.setString(7, ajaxHandlerAction.getComments());
            if (Integer.parseInt(ajaxHandlerAction.getOverLayExemptionType()) == 18) {
                preparedStatement.setString(8, ajaxHandlerAction.getPanNumber());
                preparedStatement.setString(9, ajaxHandlerAction.getOwnerName());

                if (ajaxHandlerAction.getRentStartDate() != null && !"".equals(ajaxHandlerAction.getRentStartDate())) {
                    preparedStatement.setDate(10, DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(ajaxHandlerAction.getRentStartDate())));
                } else {
                    preparedStatement.setDate(10, null);
                }

                if (ajaxHandlerAction.getRentEndDate() != null && !"".equals(ajaxHandlerAction.getRentEndDate())) {
                    preparedStatement.setDate(11, DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(ajaxHandlerAction.getRentEndDate())));
                } else {
                    preparedStatement.setDate(11, null);
                }

                preparedStatement.setDouble(12, ajaxHandlerAction.getMonthlyAmount());

            } else {
                preparedStatement.setString(8, " ");
                preparedStatement.setString(9, " ");
                preparedStatement.setDouble(12, 0.00);
                preparedStatement.setDate(10, null);
                preparedStatement.setDate(11, null);
            }
            preparedStatement.setString(13, ajaxHandlerAction.getFinancialYear());
             preparedStatement.setString(14, ajaxHandlerAction.getPolicyNumber());
              if (Integer.parseInt(ajaxHandlerAction.getOverLayExemptionType()) == 1) {
             preparedStatement.setString(15, ajaxHandlerAction.getLicPremium());
              }
              else{
                  preparedStatement.setString(15, "");
              }
            if (ajaxHandlerAction.getFileFileName() != null && !"".equals(ajaxHandlerAction.getFileFileName()) && !"--".equals(ajaxHandlerAction.getFileFileName())) {
                preparedStatement.setString(16, ajaxHandlerAction.getFileFileName());
                preparedStatement.setString(17, ajaxHandlerAction.getAttachmentLocation());
            }
            else{
                String fileName = DataSourceDataProvider.getInstance().getAttachmentDetails(ajaxHandlerAction.getExemptionId());
               if(fileName != null){
                String[] attachment =  fileName.split("#");
               
                String attachmentName = attachment[0];
               //    System.out.println("fileName.split(\"#^$\")[0]--->" + attachmentName);
                String attachmentPath = attachment[1];
             
                preparedStatement.setString(16, attachmentName);
                preparedStatement.setString(17, attachmentPath);
               }else{
                   preparedStatement.setString(16, "");
                preparedStatement.setString(17, "");
               }
                     }
           if(Integer.parseInt(ajaxHandlerAction.getOverLayExemptionType()) == 18 && ajaxHandlerAction.getOverlaySavingAmount() > 100000){
                  
            if (ajaxHandlerAction.getFile1FileName() != null && !"".equals(ajaxHandlerAction.getFile1FileName()) && !"--".equals(ajaxHandlerAction.getFile1FileName())) {
                preparedStatement.setString(18, ajaxHandlerAction.getFile1FileName());
                preparedStatement.setString(19, ajaxHandlerAction.getAttachmentLocation1());
            }else{
               String fileName = DataSourceDataProvider.getInstance().getAttachmentDetailsForm12BB(ajaxHandlerAction.getExemptionId());
                if(fileName != null){
                    String[] attachment =  fileName.split("#");
              
               
                String attachmentName = attachment[0];
                 //  System.out.println("fileName.split(\"#^$\")[0]--->" + attachmentName);
                String attachmentPath = attachment[1];
             
                preparedStatement.setString(18, attachmentName);
                preparedStatement.setString(19, attachmentPath);
                  }else{
                    preparedStatement.setString(18, "");
                preparedStatement.setString(19, "");
                }
            }
            }else{
                 preparedStatement.setString(18, "");
                preparedStatement.setString(19, "");
            }
            preparedStatement.setString(20, ajaxHandlerAction.getHouseAddress());
            isUpdated = preparedStatement.execute();
 

        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isUpdated;

    }

    public boolean upadtePayrollTaxExemption(PayrollAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {
        //  System.out.println("upadteTaxExemption method");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean isUpdated = false;
        String queryString = "";


        queryString = "update tblEmpTaxExemptionDetails set ExemptionId=?,ApprovedAmount=?,Status=?,ApprovedBy=?,ApprovedDate=?,ApproverComments=?,PaymentDate=? where Id=?";
        // System.out.println("queryString-->" + queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            //  preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());
            preparedStatement.setString(1, ajaxHandlerAction.getOverLayExemptionType());
            if (ajaxHandlerAction.getOverLayStatus().equals("Approved")) {
                preparedStatement.setDouble(2, ajaxHandlerAction.getOverlayApprovedAmount());
            } else {
                preparedStatement.setDouble(2, 0.0);
            }
            preparedStatement.setString(3, ajaxHandlerAction.getOverLayStatus());

            //preparedStatement.setInt(4, Integer.parseInt(ajaxHandlerAction.getMonth()));
            // preparedStatement.setInt(5, Integer.parseInt(ajaxHandlerAction.getYear()));
            preparedStatement.setString(4, ajaxHandlerAction.getCreatedBy());
            preparedStatement.setTimestamp(5, DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(6, ajaxHandlerAction.getComments());
            preparedStatement.setDate(7, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getPaymentDateEmp()));
            preparedStatement.setInt(8, ajaxHandlerAction.getExemptionId());


            isUpdated = preparedStatement.execute();

        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isUpdated;

    }

    public boolean checkCategoryWiseSavingsAmt(PayrollAjaxHandlerAction ajaxHandlerAction, int category) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        double approvedAmt = 0.0;
        boolean isApplicable = false;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
        if (category == 1) {
            queryString = "SELECT EmpSavings1 as approvedAmt FROM tblEmpPayRoll WHERE EmpId=" + ajaxHandlerAction.getEmpId() + " and PayRollId=" + ajaxHandlerAction.getPayRollId();
        }
        if (category == 2) {
            queryString = "SELECT EmpSavings2 as approvedAmt FROM tblEmpPayRoll WHERE EmpId=" + ajaxHandlerAction.getEmpId() + " and PayRollId=" + ajaxHandlerAction.getPayRollId();
        }
        if (category == 3) {
            queryString = "SELECT EmpSavings3 as approvedAmt FROM tblEmpPayRoll WHERE EmpId=" + ajaxHandlerAction.getEmpId() + " and PayRollId=" + ajaxHandlerAction.getPayRollId();
        }

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                approvedAmt = resultSet.getDouble("approvedAmt") + ajaxHandlerAction.getOverlayApprovedAmount();
            }
            //  System.out.println("approved amt-->" + approvedAmt);

            if (category == 1 && approvedAmt > Integer.parseInt(Properties.getProperty("Payroll.EmpSavings1.Amt"))) {
                isApplicable = true;
            }
            if (category == 2 && approvedAmt > Integer.parseInt(Properties.getProperty("Payroll.EmpSavings2.Amt"))) {
                isApplicable = true;
            }
            if (Integer.parseInt(ajaxHandlerAction.getOverLayExemptionType()) == 15 && category == 3 && approvedAmt > Integer.parseInt(Properties.getProperty("Payroll.EmpSavings3.cat15.Amt"))) {
                isApplicable = true;
            }
            if (Integer.parseInt(ajaxHandlerAction.getOverLayExemptionType()) == 16 && category == 3 && approvedAmt > Integer.parseInt(Properties.getProperty("Payroll.EmpSavings3.cat15.Amt"))) {
                isApplicable = true;
            }


        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isApplicable;

    }

    public String addNoDuesSettlement(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {
        // System.out.println("addDuesSettlement method1");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String result = "";
        boolean isUpdated = false;
        String queryString = "";
        String query = "";
        String maxDate = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String pattern = "MM/dd/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            java.util.Date maxiDate = null;
            // System.out.println("payrollAjaxHandlerAction.getNoDueId()---->" + payrollAjaxHandlerAction.getNoDueId());

            if (payrollAjaxHandlerAction.getNoDueId() == 0) {
                String maxiDateString = DataSourceDataProvider.getInstance().getMaxDateForNoDuesSettlement(payrollAjaxHandlerAction.getEmpId());
                if (!maxiDateString.equals("")) {
                    //  System.out.println("maxiDateString--------->" + maxiDateString);
                    maxiDate = format.parse(maxiDateString);
                    java.util.Date fromDate = format.parse(payrollAjaxHandlerAction.getFromDate());

                    //    System.out.println("maxiDate" + maxiDate);
                    String datemax = maxiDate + "";
                    //   System.out.println("datemax" + datemax);
                    java.util.Date date = maxiDate; // your date
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    // System.out.println(month);
                    Calendar calendar = new GregorianCalendar(year, month, day, 23, 30, 0);
                    java.util.Date currentDate = calendar.getTime();
                    calendar.setTime(currentDate);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    java.util.Date nextMonth = calendar.getTime();
                    cal.setTime(maxiDate);
                    int maxiDateMonth = cal.get(Calendar.MONTH);
                    // System.out.println("moth of maxi date" + maxiDateMonth);
                    cal.setTime(fromDate);
                    int fromDateMonth = cal.get(Calendar.MONTH);
                    //  System.out.println("moth of from date" + fromDateMonth);
                    if (fromDate.compareTo(maxiDate) < 0 || fromDate.compareTo(maxiDate) == 0) {
                        result = "<font style='color:red;font-size:15px;'>you can insert new record after  " + DateUtility.getInstance().convertDateToMySql(maxiDate) + " only.</font>";
                        return result;
                    } else {
                        if (maxiDateMonth == 11) {
                            maxiDateMonth = -1;
                        }
                        if (fromDateMonth != maxiDateMonth + 1) {
                            result = "<font style='color:red;font-size:15px;'>you have to apply from this " + format.format(nextMonth) + ".</font>";
                            return result;
                        }
                    }

                }
            }
            // System.out.println("Today Date is Greater than my date");
            if (payrollAjaxHandlerAction.getNoDueId() == 0) {
                queryString = "INSERT INTO tblEmpDuesDetails (EmpId,FromDate,ToDate,isAcknoweldged,isSetteledAllDues,isDues,DuesAmount,CreatedBy,Comments,Status,NoDueId) values (?,?,?,?,?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setInt(1, Integer.parseInt(payrollAjaxHandlerAction.getEmpId()));
                preparedStatement.setDate(2, DateUtility.getInstance().getMysqlDate(payrollAjaxHandlerAction.getFromDate()));
                preparedStatement.setDate(3, DateUtility.getInstance().getMysqlDate(payrollAjaxHandlerAction.getToDate()));
                preparedStatement.setBoolean(4, payrollAjaxHandlerAction.isRelease());
                preparedStatement.setBoolean(6, payrollAjaxHandlerAction.isCommissions());
                preparedStatement.setBoolean(5, payrollAjaxHandlerAction.isSettled());
                preparedStatement.setDouble(7, payrollAjaxHandlerAction.getDueAmount());
                preparedStatement.setString(8, payrollAjaxHandlerAction.getCreatedBy());
                preparedStatement.setString(9, payrollAjaxHandlerAction.getComments());
                // System.out.println("payrollAjaxHandlerAction.getTempVar()--> in impl -->" + payrollAjaxHandlerAction.getTempVar());
                if (payrollAjaxHandlerAction.getTempVar() == 0) {
                    preparedStatement.setString(10, "Entered");
                    result = "<font style='color:green;font-size:15px;'>Record inserted Successfully</font>";
                } else if (payrollAjaxHandlerAction.getTempVar() == 2) {
                    preparedStatement.setString(10, "Entered");
                    result = "<font style='color:green;font-size:15px;'>Record Updated Successfully</font>";
                } else {
                    preparedStatement.setString(10, "Submitted");
                    result = "<font style='color:green;font-size:15px;'>Record Submitted Successfully</font>";
                }
                preparedStatement.setString(11, payrollAjaxHandlerAction.getNoDueTableID());
                isUpdated = preparedStatement.execute();
                //  System.out.println("isUpdated===" + isUpdated);
                // result = "<font style='color:green;font-size:15px;'>Record inserted Successfully</font>";
            } else {
                queryString = "update tblEmpDuesDetails set isAcknoweldged=?,isSetteledAllDues=?,isDues=?,DuesAmount=?,Comments=?,Status=?,ApproverComments=? where Id=" + payrollAjaxHandlerAction.getNoDueId();
                preparedStatement = connection.prepareStatement(queryString);
                //  preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());


                preparedStatement.setBoolean(1, payrollAjaxHandlerAction.isRelease());
                preparedStatement.setBoolean(3, payrollAjaxHandlerAction.isCommissions());
                preparedStatement.setBoolean(2, payrollAjaxHandlerAction.isSettled());

                preparedStatement.setDouble(4, payrollAjaxHandlerAction.getDueAmount());
                preparedStatement.setString(5, payrollAjaxHandlerAction.getComments());

                if (payrollAjaxHandlerAction.getTempVar() == 0) {
                    preparedStatement.setString(6, "Entered");
                    result = "<font style='color:green;font-size:15px;'>Record inserted Successfully</font>";
                } else if (payrollAjaxHandlerAction.getTempVar() == 2) {
                    preparedStatement.setString(6, "Entered");
                    result = "<font style='color:green;font-size:15px;'>Record Updated Successfully</font>";
                } else {
                    preparedStatement.setString(6, "Submitted");
                    result = "<font style='color:green;font-size:15px;'>Record Submitted Successfully</font>";
                }
                preparedStatement.setString(7, payrollAjaxHandlerAction.getApprComments());
                isUpdated = preparedStatement.execute();


            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return result;

    }

//    public String addNoDuesSettlement(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {
//        System.out.println("addDuesSettlement method1");
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        Statement statement = null;
//        String result = "";
//        boolean isUpdated = false;
//        String queryString = "";
//        String query = "";
//        String maxDate = "";
//        try {
//
//            connection = ConnectionProvider.getInstance().getConnection();
//            String pattern = "MM/dd/yyyy";
//            SimpleDateFormat format = new SimpleDateFormat(pattern);
//            java.util.Date maxiDate = null;
//            String maxiDateString = DataSourceDataProvider.getInstance().getMaxDateForNoDuesSettlement(payrollAjaxHandlerAction.getEmpId());
//            if (!maxiDateString.equals("")) {
//                maxiDate = format.parse(maxiDateString);
//                java.util.Date fromDate = format.parse(payrollAjaxHandlerAction.getFromDate());
//                System.out.println("dates--------->" + maxiDate.compareTo(format.parse("05/01/2015")));
//                System.out.println("maxiDate" + maxiDate);
//                String datemax = maxiDate + "";
//                System.out.println("datemax" + datemax);
//                java.util.Date date = maxiDate; // your date
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(date);
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//                System.out.println(month);
//                Calendar calendar = new GregorianCalendar(year, month, day, 23, 30, 0);
//                java.util.Date currentDate = calendar.getTime();
//                calendar.setTime(currentDate);
//                calendar.add(Calendar.DAY_OF_MONTH, 1);
//                java.util.Date nextMonth = calendar.getTime();
//
//                cal.setTime(maxiDate);
//
//
//                int maxiDateMonth = cal.get(Calendar.MONTH);
//                System.out.println("moth of maxi date" + maxiDateMonth);
//
//                cal.setTime(fromDate);
//
//
//                int fromDateMonth = cal.get(Calendar.MONTH);
//                System.out.println("moth of from date" + fromDateMonth);
//
//                if (fromDate.compareTo(maxiDate) < 0 || fromDate.compareTo(maxiDate) == 0) {
//                    result = "<font style='color:red;font-size:15px;'>you can insert new record after  " + DateUtility.getInstance().convertDateToMySql(maxiDate) + " only.</font>";
//                    return result;
//                } else {
//                    if (fromDateMonth != maxiDateMonth + 1) {
//                        result = "<font style='color:red;font-size:15px;'>you have to apply from this " + format.format(nextMonth) + ".</font>";
//                        return result;
//                    }
//                }
//
//            }
//
//            //if(maxiDate.compareTo(format.parse("01/01/1951"))!=0){
//
//
//            System.out.println("Today Date is Greater than my date");
//
//
//
//            //queryString = "update tblEmpReview set ReviewTypeId=?,EmpComments=?,ModifiedBy=?,ModifiedDate=?,ReviewName=?,ReviewDate=?,Status=?,TLComments=?,TLRating=?,HRRating=?,HrComments=? where Id=?";
//            //if(roleName.equalsIgnoreCase("Employee"))
//
//            queryString = "INSERT INTO tblEmpDuesDetails (EmpId,FromDate,ToDate,isAcknoweldged,isSetteledAllDues,isDues,DuesAmount,CreatedBy,Comments) values (?,?,?,?,?,?,?,?,?)";
//            preparedStatement = connection.prepareStatement(queryString);
//            //  preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());
//
//            preparedStatement.setInt(1, Integer.parseInt(payrollAjaxHandlerAction.getEmpId()));
//            preparedStatement.setDate(2, DateUtility.getInstance().getMysqlDate(payrollAjaxHandlerAction.getFromDate()));
//            preparedStatement.setDate(3, DateUtility.getInstance().getMysqlDate(payrollAjaxHandlerAction.getToDate()));
//            if (payrollAjaxHandlerAction.isRelease()) {
//                preparedStatement.setInt(4, 1);
//            } else {
//                preparedStatement.setInt(4, 0);
//            }
//            if (payrollAjaxHandlerAction.isCommissions()) {
//                preparedStatement.setInt(5, 1);
//            } else {
//                preparedStatement.setInt(5, 0);
//            }
//            if (payrollAjaxHandlerAction.isSettled()) {
//                preparedStatement.setInt(6, 1);
//            } else {
//                preparedStatement.setInt(6, 0);
//            }
//
//            preparedStatement.setDouble(7, payrollAjaxHandlerAction.getDueAmount());
//            preparedStatement.setString(8, payrollAjaxHandlerAction.getCreatedBy());
//            preparedStatement.setString(9, payrollAjaxHandlerAction.getComments());
//            isUpdated = preparedStatement.execute();
//            System.out.println("isUpdated===" + isUpdated);
//            result = "<font style='color:green;font-size:15px;'>Record inserted Successfully</font>";
//
//            /* else
//            {
//            httpServletRequest.getSession(false).setAttribute("resultMessageforNoDue","<font style='color:red;font-size:15px;'>No record exist.</font>");
//            }*/
//
//        } catch (ParseException ex) {
//            ex.printStackTrace();
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
//        return result;
//
//    }
    public String getSingleEmpLockAmtDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {
        //System.out.println("getWagesHistory method");
        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        String resultMessage = "";
        String queryString = "";
        java.sql.Date payRollDate = null;
        int rowsInserted = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT tblEmpWages.Earned_LongTermBonus,CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,MONTHNAME(PayrollDate) AS MONTH,YEAR(PayrollDate) AS YEAR FROM tblEmpWages "
                    + "LEFT OUTER JOIN tblEmpPayRoll ON (tblEmpWages.PayRoll_Id = tblEmpPayRoll.PayRollId) "
                    + "LEFT OUTER JOIN tblEmployee ON(tblEmpPayRoll.EmpId = tblEmployee.Id) "
                    + "WHERE  tblEmployee.DepartmentId = ? AND tblEmployee.Id = ? AND "
                    + "MONTH(tblEmpPayRoll.LockAmtStartDate) = ? AND YEAR(tblEmpPayRoll.LockAmtStartDate) = ? and tblEmpWages.Earned_LongTermBonus > 0 ";
           
            prestatement = connection.prepareStatement(queryString);
            //  System.out.println("department in impl-->" + payrollAjaxHandlerAction.getDepartmentId());
            //  System.out.println("empId-->" + DataSourceDataProvider.getInstance().getEmpIdByLoginId(payrollAjaxHandlerAction.getEmpnameById()));
            //  System.out.println("month-->" + payrollAjaxHandlerAction.getMonth());
            //  System.out.println("year-->" + payrollAjaxHandlerAction.getYear());
            prestatement.setString(1, payrollAjaxHandlerAction.getDepartmentId());
            prestatement.setInt(2, DataSourceDataProvider.getInstance().getEmpIdByLoginId(payrollAjaxHandlerAction.getEmpnameById()));
            prestatement.setInt(3, payrollAjaxHandlerAction.getMonth());
            prestatement.setInt(4, payrollAjaxHandlerAction.getYear());
            resultSet = prestatement.executeQuery();
            String empName = "";
            while (resultSet.next()) {
                empName = resultSet.getString("EmpName");
                resultMessage += resultSet.getDouble("Earned_LongTermBonus") + "#" + resultSet.getString("MONTH") + "#" + resultSet.getInt("YEAR") + "&";
            }
            if (resultMessage.length() != 0) {
                resultMessage = "Single@" + empName + "$" + resultMessage;
            }
            
         //   System.out.println("---->query"+queryString);
        } catch (SQLException ex) {
            ex.printStackTrace();
            resultMessage = "<font style='color:red;'>Please try again later</font>";
            DataSourceDataProvider.getInstance().deleteWagesIfExceptionOccurs();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } catch (Exception ioe) {
            System.err.println(ioe);
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

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //   System.out.println("resultMessage-->" + resultMessage);
        return resultMessage;
    }

    public String getAllEmpsLockAmtDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {
        //   System.out.println("getWagesHistory method");
        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        Connection connection1 = null;
        PreparedStatement prestatement1 = null;
        ResultSet resultSet1 = null;
        Connection connection2 = null;
        PreparedStatement prestatement2 = null;
        ResultSet resultSet2 = null;
        String resultMessage = "";
        java.sql.Date payRollDate = null;
        int rowsInserted = 0;
        try {

            if (!"All".equals(payrollAjaxHandlerAction.getLockAmtPeriod())) {
                connection = ConnectionProvider.getInstance().getConnection();
                String query = "SELECT DISTINCT tblEmpPayRoll.EmpId as EmpId,COUNT(tblEmpPayRoll.EmpId) AS TotalMonths,SUM(tblEmpWages.Earned_LongTermBonus) as totalLongTermBonus, CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,MONTHNAME(PayrollDate) AS MONTH,YEAR(PayrollDate) AS YEAR FROM tblEmpWages "
                        + "LEFT OUTER JOIN tblEmpPayRoll ON (tblEmpWages.PayRoll_Id = tblEmpPayRoll.PayRollId) "
                        + "LEFT OUTER JOIN tblEmployee ON(tblEmpPayRoll.EmpId = tblEmployee.Id) "
                        + "WHERE  tblEmpWages.Earned_LongTermBonus>0 and "
                        + "MONTH(tblEmpPayRoll.LockAmtStartDate) = " + payrollAjaxHandlerAction.getMonth() + "  AND YEAR(tblEmpPayRoll.LockAmtStartDate) = " + payrollAjaxHandlerAction.getYear();
                if (payrollAjaxHandlerAction.getDepartmentId().length() != 0) {
                    query = query + " and tblEmployee.DepartmentId = '" + payrollAjaxHandlerAction.getDepartmentId() + "'";
                }
                query = query + " GROUP BY EmpId HAVING (TotalMonths=12 OR TotalMonths=6)";
                // System.out.println("Query -- >" + query);
                prestatement = connection.prepareStatement(query);
                resultSet = prestatement.executeQuery();
                String empName = "";
                while (resultSet.next()) {
                    empName = resultSet.getString("EmpName");
                    resultMessage += resultSet.getInt("EmpId") + "#" + empName + "#" + resultSet.getDouble("totalLongTermBonus") + "#" + resultSet.getInt("TotalMonths") + "&";
                }
                
              //   System.out.println("-->query"+query);
                
            } else {
                connection1 = ConnectionProvider.getInstance().getConnection();
                connection2 = ConnectionProvider.getInstance().getConnection();
                String lockDate = payrollAjaxHandlerAction.getMonth() + "/01/" + payrollAjaxHandlerAction.getYear();
                java.util.Date referenceDate = DateUtility.getInstance().convertStringToMySql(lockDate);
                Calendar c = Calendar.getInstance();
                int monthBeforeTwelveMonths = 0;

                int yearBeforeTwelveMonths = 0;
                c.setTime(referenceDate);
                c.add(Calendar.MONTH, -12);
                String previousYearDate = DateUtility.getInstance().convertDateToMySql1(c.getTime());
                // System.out.println("previousYearDate -->" + previousYearDate);
                yearBeforeTwelveMonths = Integer.parseInt(previousYearDate.split("-")[0]);
                monthBeforeTwelveMonths = Integer.parseInt(previousYearDate.split("-")[1]);

                String query1 = "SELECT DISTINCT tblEmpPayRoll.EmpId as EmpId,COUNT(tblEmpPayRoll.EmpId) AS TotalMonths,SUM(tblEmpWages.Earned_LongTermBonus) as totalLongTermBonus, CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,MONTHNAME(PayrollDate) AS MONTH,YEAR(PayrollDate) AS YEAR FROM tblEmpWages "
                        + "LEFT OUTER JOIN tblEmpPayRoll ON (tblEmpWages.PayRoll_Id = tblEmpPayRoll.PayRollId) "
                        + "LEFT OUTER JOIN tblEmployee ON(tblEmpPayRoll.EmpId = tblEmployee.Id) "
                        + "WHERE  tblEmpWages.Earned_LongTermBonus>0 and "
                        + "MONTH(tblEmpPayRoll.LockAmtStartDate) = " + monthBeforeTwelveMonths + "  AND YEAR(tblEmpPayRoll.LockAmtStartDate) = " + yearBeforeTwelveMonths;
                if (payrollAjaxHandlerAction.getDepartmentId().length() != 0) {
                    query1 = query1 + " and tblEmployee.DepartmentId = '" + payrollAjaxHandlerAction.getDepartmentId() + "'";
                }
                query1 = query1 + " GROUP BY EmpId HAVING (TotalMonths=12)";
                prestatement1 = connection1.prepareStatement(query1);
                resultSet1 = prestatement1.executeQuery();
                String empName = "";
                while (resultSet1.next()) {
                    empName = resultSet1.getString("EmpName");
                    resultMessage += resultSet1.getInt("EmpId") + "#" + empName + "#" + resultSet1.getDouble("totalLongTermBonus") + "#" + resultSet1.getInt("TotalMonths") + "&";
                }
                int monthBeforeSixMonths = 0;
                int yearBeforeSixMonths = 0;
                c.setTime(referenceDate);
                c.add(Calendar.MONTH, -6);
                String previousSixMonthsDate = DateUtility.getInstance().convertDateToMySql1(c.getTime());
                // System.out.println("previousYearDate -->" + previousSixMonthsDate);
                yearBeforeSixMonths = Integer.parseInt(previousSixMonthsDate.split("-")[0]);
                monthBeforeSixMonths = Integer.parseInt(previousSixMonthsDate.split("-")[1]);
                String query2 = "SELECT DISTINCT tblEmpPayRoll.EmpId as EmpId,COUNT(tblEmpPayRoll.EmpId) AS TotalMonths,SUM(tblEmpWages.Earned_LongTermBonus) as totalLongTermBonus, CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,MONTHNAME(PayrollDate) AS MONTH,YEAR(PayrollDate) AS YEAR FROM tblEmpWages "
                        + "LEFT OUTER JOIN tblEmpPayRoll ON (tblEmpWages.PayRoll_Id = tblEmpPayRoll.PayRollId) "
                        + "LEFT OUTER JOIN tblEmployee ON(tblEmpPayRoll.EmpId = tblEmployee.Id) "
                        + "WHERE  tblEmpWages.Earned_LongTermBonus>0 and "
                        + "MONTH(tblEmpPayRoll.LockAmtStartDate) = " + monthBeforeSixMonths + "  AND YEAR(tblEmpPayRoll.LockAmtStartDate) = " + yearBeforeSixMonths;
                if (payrollAjaxHandlerAction.getDepartmentId().length() != 0) {
                    query2 = query2 + " and tblEmployee.DepartmentId = '" + payrollAjaxHandlerAction.getDepartmentId() + "'";
                }
                query2 = query2 + " GROUP BY EmpId HAVING (TotalMonths=6)";
                prestatement2 = connection2.prepareStatement(query2);
                resultSet2 = prestatement2.executeQuery();
                String empName1 = "";
                while (resultSet2.next()) {
                    empName1 = resultSet2.getString("EmpName");
                    resultMessage += resultSet2.getInt("EmpId") + "#" + empName1 + "#" + resultSet2.getDouble("totalLongTermBonus") + "#" + resultSet2.getInt("TotalMonths") + "&";
                }

              //   System.out.println("-->query1"+query1);
              //    System.out.println("-->query2"+query2);
                
            }
            if (resultMessage.length() != 0) {
                resultMessage = "ALl@" + resultMessage;
            //    System.out.println("-->resultMessage"+resultMessage);
            }
            
           
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            resultMessage = "<font style='color:red;'>Please try again later</font>";
            DataSourceDataProvider.getInstance().deleteWagesIfExceptionOccurs();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } catch (Exception ioe) {
            System.err.println(ioe);
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }

                if (prestatement1 != null) {
                    prestatement1.close();
                    prestatement1 = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
                if (resultSet2 != null) {
                    resultSet2.close();
                    resultSet2 = null;
                }

                if (prestatement2 != null) {
                    prestatement2.close();
                    prestatement2 = null;
                }
                if (connection2 != null) {
                    connection2.close();
                    connection2 = null;
                }
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

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        // System.out.println("resultMessage-->" + resultMessage);
        return resultMessage;
    }

    public String getNoDueDetails(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String details = "NO";
        queryString = "SELECT Id,EmpId,FromDate,ToDate,isAcknoweldged,isSetteledAllDues,isDues,Comments,DuesAmount,Status,ApproverComments,NoDueId FROM tblEmpDuesDetails WHERE Id =" + payrollAjaxHandlerAction.getNoDueId();

        connection = ConnectionProvider.getInstance().getConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                details = resultSet.getInt("Id") + "|@#" + resultSet.getString("ApproverComments") + "|@#" + resultSet.getString("FromDate") + "|@#" + resultSet.getInt("isAcknoweldged") + "|@#" + resultSet.getInt("isSetteledAllDues") + "|@#" + resultSet.getInt("isDues") + "|@#" + resultSet.getString("Comments") + "|@#" + resultSet.getString("DuesAmount") + "|@#" + resultSet.getString("Status") + "|@#" + resultSet.getString("ToDate") + "|@#" + resultSet.getInt("EmpId") + "|@#" + resultSet.getInt("NoDueId");
            }


        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new ServiceLocatorException(ex);
            }
        }
        return details; // returning the object.
    }

    public String getEmployeesByDeptPayroll(String deptName, String status, String roleName, PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {
        StringBuffer reportsToBuffer = new StringBuffer();
        String loginId = null;
        String topManagementPeople[] = null;
        boolean isTopManager = false;



        try {
            if (deptName == null || "".equals(deptName) || "All".equals(deptName)) {
                deptName = "%";
            }
            int isOperationContact = DataSourceDataProvider.getInstance().getRoleIdByEmpId(Integer.parseInt(payrollAjaxHandlerAction.getEmpId()));
            if (isOperationContact == 1 && roleName.equals("Operations")) {
                queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee WHERE DepartmentId LIKE '" + deptName + "' AND CurStatus='" + status + "' and Country='" + payrollAjaxHandlerAction.getCountry() + "' ORDER BY EmployeeName";
            } else {
                queryString = "SELECT LoginId,CONCAT(TRIM(FName),'.',TRIM(LName)) AS EmployeeName FROM tblEmployee WHERE DepartmentId LIKE '" + deptName + "' AND CurStatus='" + status + "' and Country='" + payrollAjaxHandlerAction.getCountry() + "' ORDER BY EmployeeName";
            }
            //    System.out.println("queryString---" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            reportsToBuffer.append("<xml version=\"1.0\">");
            reportsToBuffer.append("<TEAM Description=\"" + deptName + "\">");
            reportsToBuffer.append("<USER userId=\"-1\">All</USER>");


            while (resultSet.next()) {
                loginId = resultSet.getString("LoginId");

                reportsToBuffer.append("<USER userId=\"" + loginId + "\">");
                reportsToBuffer.append(resultSet.getString("EmployeeName"));
                reportsToBuffer.append("</USER>");

            }
            reportsToBuffer.append("</TEAM>");
            reportsToBuffer.append("</xml>");
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
        //System.out.println("Team List: "+reportsToBuffer.toString());
        return reportsToBuffer.toString();
    }

    public String approveOrRejectNoDues(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String result = "";
        boolean isUpdated = false;
        String queryString = "";
        String query = "";
        String maxDate = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String pattern = "MM/dd/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            java.util.Date maxiDate = null;


            queryString = "update tblEmpDuesDetails set ApproverComments=?,ModifiedBy=?,ModifiedDate=?,Status=?,DuesAmount=? where Id=" + payrollAjaxHandlerAction.getNoDueId();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, payrollAjaxHandlerAction.getApprComments());
            preparedStatement.setString(2, payrollAjaxHandlerAction.getModifiedBy());
            preparedStatement.setTimestamp(3, DateUtility.getInstance().getCurrentMySqlDateTime());
            if (payrollAjaxHandlerAction.getTempVar() == 0) {
                preparedStatement.setString(4, "Approved");
            } else {
                preparedStatement.setString(4, "Rejected");
            }
            preparedStatement.setDouble(5, payrollAjaxHandlerAction.getDueAmount());
            isUpdated = preparedStatement.execute();

            if (payrollAjaxHandlerAction.getTempVar() == 0) {
                result = "<font style='color:green;font-size:15px;'>Record Approved Successfully</font>";
            } else {
                result = "<font style='color:green;font-size:15px;'>Record Rejected Successfully</font>";
            }

        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return result;

    }

    public String addRemainderValuesNoDues(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String result = "";
        boolean isUpdated = false;
        String queryString = "";
        String query = "";
        String maxDate = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
//            boolean checkRemainderTable = DataSourceDataProvider.getInstance().checkRemainderTablesFlag();
//            if(!checkRemainderTable){
            String employees[] = payrollAjaxHandlerAction.getSelectedEmployess().split(Pattern.quote("$$"));
            String resultantEmpNames = "$$";
            String checkEmpNames = "";

            for (int i = 0; i <= employees.length - 1; i++) {

                String loginId = employees[i];
                int empIds = DataSourceDataProvider.getInstance().getEmpIdByLoginId(loginId);
                checkEmpNames = checkEmpNames + empIds + "!@#";
                resultantEmpNames = resultantEmpNames + empIds + "$$";
            }

            String resultEndDate = DataSourceDataProvider.getInstance().checkRemainderDatesForNoDues(payrollAjaxHandlerAction.getFromDate(), checkEmpNames, payrollAjaxHandlerAction.getDepartmentId());
//            if(!"".equals(resultEndDate)&&(resultEndDate.split(Pattern.quote("$%$"))[0]).equals("formNotSubmitted")){
//                //DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(resultEndDate.split(Pattern.quote("@@"))[1]))
//             String resultNames[] =    resultEndDate.split(Pattern.quote("$%$"))[1].split(Pattern.quote("@@"));
//              String namesEmp = "";
//             for(int i=0;i<=resultNames.length-1;i++){
//             namesEmp = namesEmp + DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(resultNames[i]))+",";
//             }
//             result = "<font style='color:red;font-size:15px;'><font style='font-weight:800'>'"+namesEmp.substring(0,namesEmp.length()-1) +"'</font> in your selected list has not submitted no dues for previous remainder.. Please check that and try again</font>";
//            }
            /*else*/ if (!"".equals(resultEndDate) && (resultEndDate.split(Pattern.quote("$%$"))[0]).equals("formSubmitted")) {
                String endDate = resultEndDate.split(Pattern.quote("$%$"))[1].split(Pattern.quote("#@#"))[0];
                String resultNames[] = resultEndDate.split(Pattern.quote("$%$"))[1].split(Pattern.quote("#@#"))[1].split(Pattern.quote("@@"));
                String namesEmp = "";
                for (int i = 0; i <= resultNames.length - 1; i++) {
                    namesEmp = namesEmp + DataSourceDataProvider.getInstance().getFname_Lname1(Integer.parseInt(resultNames[i])) + ",";
                }

                result = "<font style='color:red;font-size:15px;'>Already Remainder added for employee <font style='font-weight:800'>" + namesEmp.substring(0, namesEmp.length() - 1) + "</font>.. Please add after " + DateUtility.getInstance().convertToviewFormat(endDate) + "</font>";
            } else if (!"".equals(resultEndDate) && (resultEndDate.split(Pattern.quote("$%$"))[0]).equals("All") && (resultEndDate.split(Pattern.quote("$%$"))[1]).equals("Nodept")) {
                result = "<font style='color:red;font-size:15px;'>Already Remainder added for all employees of all depafrtments..for this month</font>";
            } else if (!"".equals(resultEndDate) && (resultEndDate.split(Pattern.quote("$%$"))[0]).equals("All") && !("Nodept").equals(resultEndDate.split(Pattern.quote("$%$"))[1])) {
                result = "<font style='color:red;font-size:15px;'>Already Remainder added for all employees of  depafrtment " + resultEndDate.split(Pattern.quote("$%$"))[1] + "..for this month</font>";
            } else {
                queryString = "INSERT INTO tblEmpNoDues (`CreatedBy`, `StartDate`, `EndDate`, `Comments`, `EmpList`,DepartmentId,Country) values (?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setString(1, payrollAjaxHandlerAction.getCreatedBy());
                preparedStatement.setDate(2, DateUtility.getInstance().getMysqlDate(payrollAjaxHandlerAction.getFromDate()));
                preparedStatement.setDate(3, DateUtility.getInstance().getMysqlDate(payrollAjaxHandlerAction.getToDate()));
                preparedStatement.setString(4, payrollAjaxHandlerAction.getComments());
                String checkForALl = payrollAjaxHandlerAction.getSelectedEmployess().split(Pattern.quote("$$"))[0];

                if (!"-1".equals(checkForALl) && !"All".equals(checkForALl)) {
                    preparedStatement.setString(5, resultantEmpNames);
                } else {
                    preparedStatement.setString(5, "All");
                }
                preparedStatement.setString(6, payrollAjaxHandlerAction.getDepartmentId());
                preparedStatement.setString(7, payrollAjaxHandlerAction.getCountry());
                isUpdated = preparedStatement.execute();

                result = "<font style='color:green;font-size:15px;'>Record inserted Successfully</font>";

            }
//            }else{
//                    result = "<font style='color:red;font-size:15px;'>Please make sure that everyone has submitted no dues for previous remainder</font>";
//            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return result;

    }

    public String showEmpListNoDuesRemainder(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String result = "";
        boolean isUpdated = false;
        String queryString = "";
        String query = "";
        String maxDate = "";
        String resultantEmpNames = "";
        try {

            String employees[] = payrollAjaxHandlerAction.getSelectedEmployess().split(Pattern.quote("$$"));


            for (int i = 1; i <= employees.length - 1; i++) {

                String nameAndDep = DataSourceDataProvider.getInstance().getemployeenameAndDepbyEmpId(employees[i]);
                resultantEmpNames = resultantEmpNames + nameAndDep + "$$";
            }


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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return resultantEmpNames;

    }

    public String checkForEmpNoDueRecordExistsOrNot(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String result = "";
        try {


            String resultDates = DataSourceDataProvider.getInstance().checkForEmpRecordInNoDueTable(payrollAjaxHandlerAction.getEmpId(), payrollAjaxHandlerAction.getDepartmentId());
            if (!"".equals(resultDates)) {
                result = resultDates;
            } else {

                result = "fail";
            }

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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return result;

    }

    public String noDuesRemainderClose(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {
        CallableStatement callableStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spCloseNoDuesRem1(?,?,?,?,?,?)}");
            callableStatement.setInt(1, payrollAjaxHandlerAction.getNoDueId());
            callableStatement.setString(2, payrollAjaxHandlerAction.getFromDate());
            callableStatement.setString(3, payrollAjaxHandlerAction.getToDate());
            callableStatement.setString(4, payrollAjaxHandlerAction.getSelectedEmployess());
            callableStatement.setString(5, payrollAjaxHandlerAction.getDepartmentId());
            callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
            // callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
            resultSet = callableStatement.executeQuery();
            queryString = callableStatement.getString(6);

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return queryString;
    }

    public String generatePayRollPassword(int empId, String email) throws ServiceLocatorException {
        // System.out.println("generatePayRollPassword method");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String result = "";
        int rowCount = 0;
        String queryString = "";
        //  String originalPassword = RandomPasswordGenerator.generatePswd(4, 4, 2, 2, 0);
        String originalPassword = RandomPasswordGenerator.generatePswd(4, 8, 1, 1, 1);

        String encryptedPassword = PasswordUtility.md5EncriptionPassword(originalPassword);
        //  System.out.println("originalPassword---" + originalPassword);
        //  System.out.println("encryptedPassword---" + encryptedPassword);
        queryString = "update tblPayrollAuthCheck set PASSWORD=? where EmpId=?";
        //System.out.println("queryString-->" + queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            //  preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());
            preparedStatement.setString(1, encryptedPassword);

            preparedStatement.setInt(2, empId);




            rowCount = preparedStatement.executeUpdate();
            if (rowCount > 0) {
                result = "<font color='green' size='2'>Password sent to your email Id!</font>";
                if (Properties.getProperty("Mail.Flag").equals("1")) {
                    String userName = DataSourceDataProvider.getInstance().getEmployeeNameByEmpNo(empId);
                    MailManager.sendPayrollPassword(email, userName, originalPassword);
                }
            } else {
                result = "<font color='red' size='2'>Password generationFailed!</font>";
            }

        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return result;

    }

    public String getRepaymentDetails(String year, int month) throws ServiceLocatorException {
        String result = "";
        Connection connection = null;
        int sno = 0;
        /**
         * callableStatement is a reference variable for CallableStatement .
         */
        CallableStatement callableStatement = null;

        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null;

        /**
         * The queryString is useful to get queryString result to the particular
         * jsp page
         */
        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        String timeSheetStatus = "";
        try {
            //  System.out.println("year-->" + year);
            //  System.out.println("month-->" + month);


            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;

            query = "SELECT Wag_Id,CONCAT(FName,'.',LName) AS NAME,RepaymentGross,RepaymentVarriablePay,RepaymentNetPaid,PayrollDate FROM tblEmployee LEFT JOIN tblEmpWages ON (tblEmployee.Id=tblEmpWages.EmpId) WHERE  DoRepaymentFlag=1 AND MONTH(PayPeriodStartDate) = " + month + " AND YEAR(PayPeriodStartDate)=" + year;
            preStmt = connection.prepareStatement(query);
            resultSet = preStmt.executeQuery();

            while (resultSet.next()) {
                sno++;
                result = result + sno + "#^$" + resultSet.getString("NAME") + "#^$" + resultSet.getString("PayrollDate") + "#^$" + resultSet.getString("RepaymentVarriablePay") + "#^$" + resultSet.getString("RepaymentGross") + "#^$" + resultSet.getString("RepaymentNetPaid") + "#^$" + resultSet.getString("Wag_Id") + "*@!";

            }

            if (sno == 0) {
                result = "noData";
            }


            //   System.out.println("result-->" + result);



        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }

        return result;
    }

    public String getRepaymentReason(int wagId) throws ServiceLocatorException {
        String result = "";
        Connection connection = null;
        int sno = 0;
        /**
         * callableStatement is a reference variable for CallableStatement .
         */
        CallableStatement callableStatement = null;

        /**
         * preStmt,preStmtTemp are reference variable for PreparedStatement .
         */
        PreparedStatement preStmt = null;

        /**
         * The queryString is useful to get queryString result to the particular
         * jsp page
         */
        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet = null;
        String timeSheetStatus = "";
        try {



            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;

            query = "SELECT RepaymentComments FROM tblEmpWages WHERE Wag_Id=" + wagId;
            preStmt = connection.prepareStatement(query);



            resultSet = preStmt.executeQuery();

            while (resultSet.next()) {
                sno++;

                if (resultSet.getString("RepaymentComments") != null && !"".equals(resultSet.getString("RepaymentComments").trim())) {
                    result = resultSet.getString("RepaymentComments");
                }

            }

            if (sno == 0) {
                result = "No Comments";
            }


            // System.out.println("result-->" + result);



        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }

        return result;
    }

    public String tdsCalculation(PayrollAjaxHandlerAction payrollAjaxHandlerAction) throws ServiceLocatorException {
        String reportResult = "";
        String empBasicDetails = "";
        String empPayrollDetails = "";
        String totalEarnings = " ";
        String totaldetailsForPayslip = "";
        Statement statement = null;
        Statement statement1 = null;
        Statement statement2 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        Connection connection = null;
        Map DetailsMap = new HashMap();

        Map RBMap = new HashMap();
        RBMap.put("generatedby", "");
        try {
            // System.out.println("payrollAjaxHandlerAction.getEmpNo()==" + payrollAjaxHandlerAction.getEmpNo());
            int empId = DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(payrollAjaxHandlerAction.getEmpNo()));
            //  System.out.println("empId===" + empId);
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT CONCAT(tblEmployee.FName,' ',tblEmployee.MName,' ',tblEmployee.LName) AS EmpName,tblEmployee.EmpNo AS EmpId,"
                    + "SUM(tblEmpWages.Earned_Basic) AS EarnedBasicPay,SUM(tblEmpWages.Earned_DA) AS EarnedDA,SUM(tblEmpWages.Earned_HRA) AS EarnedHRA,"
                    + "SUM(tblEmpWages.Earned_TA) AS EarnedTA,SUM(tblEmpWages.Earned_RA) AS EarnedRA,SUM(tblEmpWages.Earned_Entertainment) AS EarnedEntertainment,"
                    + "SUM(tblEmpWages.Earned_KidsEducation) AS EarnedKidsEducation,SUM(tblEmpWages.Earned_VehicleAllowance) AS EarnedVehcleAllowance,"
                    + "SUM(tblEmpWages.Earned_CCA) AS EarnedCCA,SUM(tblEmpWages.Earned_SplAllowance) AS EarnedSplAllowance,SUM(tblEmpWages.Earned_MiscPay) AS EarnedMiscPay,"
                    + "SUM(tblEmpWages.Earned_Bonus) AS EarnedBonus,SUM(tblEmpWages.Earned_Allowance) AS EarnedAttAllowance,"
                    + "SUM(tblEmpWages.Earned_ProjectPay) AS EarnedProjectPay,SUM(tblEmpWages.Ded_Professional_Tax) AS ProfessionalTax,SUM(tblEmpWages.Ded_Others) AS DedOthers,SUM(tblEmpWages.Earned_Gross_Pay) AS EarnedGrossPay,SUM(tblEmpWages.Earned_MiscPay) AS EmpOthers, "
                    + " SUM(tblEmpWages.Employee_PF) AS EmployeePF, SUM(tblEmpWages.Earned_Health) AS earnedHelth ,SUM(tblEmpWages.TDS) AS TDS "
                    + "FROM tblEmpWages  "
                    + "LEFT OUTER JOIN tblEmployee ON (tblEmployee.Id = tblEmpWages.EmpId)  WHERE tblEmpWages.EmpId= " + empId;
            Calendar cal = Calendar.getInstance();
            //cal.setTime(date);
            //  int year = cal.get(Calendar.YEAR);
            int year = payrollAjaxHandlerAction.getYear();
            // int month = cal.get(Calendar.MONTH);
            int month = payrollAjaxHandlerAction.getMonth() - 1;
            String monthVal = "";
            switch (month) {
                case 0:
                    monthVal = "Jan";
                    break;
                case 1:
                    monthVal = "Feb";
                    break;
                case 2:
                    monthVal = "Mar";
                    break;
                case 3:
                    monthVal = "Apr";
                    break;
                case 4:
                    monthVal = "May";
                    break;
                case 5:
                    monthVal = "June";
                    break;
                case 6:
                    monthVal = "July";
                    break;
                case 7:
                    monthVal = "Aug";
                    break;
                case 8:
                    monthVal = "Sept";
                    break;
                case 9:
                    monthVal = "Oct";
                    break;
                case 10:
                    monthVal = "Nov";
                    break;
                case 11:
                    monthVal = "Dec";
                    break;

            }
            String sdate = "";
            String edate = "";
            if (month < 4) {
                sdate = (year - 1) + "-04-01";    // 2015-04-01
                edate = year + "-0" + (month + 1) + "-01";   //2016-03-01
            } else {
                sdate = (year) + "-04-01";

                if (month < 9) {
                    edate = (year) + "-0" + (month + 1) + "-01";
                } else {
                    edate = (year) + "-" + (month + 1) + "-01";
                }
            }
            queryString = queryString + " AND (tblEmpWages.PayPeriodStartDate>= '" + sdate + "' AND tblEmpWages.PayPeriodStartDate<='" + edate + "')";
            queryString += "  GROUP BY tblEmployee.Id";

            double TDS_uptoPreviousMonths = DataSourceDataProvider.getInstance().getPreviousMonthsTDS(empId, sdate, year + "-" + (month) + "-01");
            String queryString1 = "SELECT tblEmpWages.BasicPay1 AS BasicPay,tblEmpWages.Earned_Basic AS EarnedBasicPay,tblEmpWages.Earned_DA AS EarnedDA,tblEmpWages.DA,tblEmpWages.HRA,tblEmpWages.Earned_HRA AS EarnedHRA,tblEmpWages.TA,tblEmpWages.RA,tblEmpWages.Entertainment,tblEmpWages.KidsEducation,"
                    + "tblEmpWages.VehcleAllowance,tblEmpWages.CCA1 AS CCA,tblEmpWages.SplAllowance,"
                    + "tblEmpWages.ProjectPay,tblEmpWages.LongTermBonus,tblEmpWages.AttAllowance,tblEmpWages.Ded_Professional_Tax AS ProfessionalTax,tblEmpWages.GrossPay AS GrossPay,tblEmpWages.Earned_Gross_Pay AS EarnedGrossPay,tblEmpWages.MiscPay AS EmpOthers,"
                    + " tblEmpWages.Employee_PF AS EmployeePF, tblEmpWages.Health,SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 9 AND tblEmpTaxExemptionDetails.STATUS='Approved') THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS LICFROMSalary ,"
                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 5 AND tblEmpTaxExemptionDetails.STATUS='Approved') THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS TutionFee,SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 4 AND tblEmpTaxExemptionDetails.STATUS='Approved') THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS ppf,"
                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 1 AND tblEmpTaxExemptionDetails.STATUS='Approved') THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS lic, "
                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 14 AND tblEmpTaxExemptionDetails.STATUS='Approved') "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS HBLoan, "
                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 17 AND tblEmpTaxExemptionDetails.STATUS='Approved') "
                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS eduloan "
                    + "FROM tblEmpWages LEFT OUTER JOIN tblEmpPayRoll ON (tblEmpPayRoll.PayRollId = tblEmpWages.PayRoll_Id) LEFT OUTER JOIN tblEmployee ON (tblEmployee.Id = tblEmpPayRoll.EmpId) LEFT OUTER JOIN tblEmpTaxExemptionDetails ON (tblEmployee.Id = tblEmpTaxExemptionDetails.EmpId) WHERE tblEmpWages.EmpId= " + empId;
            //setStartdate(DateUtility.getInstance().FirstDateOfCurrentMonth());
            queryString1 += " AND tblEmpWages.PayPeriodStartDate='" + year + "-" + (month + 1) + "-01'";

            queryString1 += "  GROUP BY tblEmployee.Id";

            String queryString2 = "SELECT `PayRollId`,`PaymentTypeId`,`GrossPay`,`BasicPay`,`DA`,`HRA`,`TA`,	   `RA` ,`Life`,`Health`,`AttAllowance`,`Entertainment`,`KidsEducation`,"
                    + "  `VehcleAllowance`,`LongTermbonus`,`Employer_PF`,`Employee_PF`,`CCA`,ProjectPay, `SplAllowances`, `MiscPay`, `VariablePay`,`Ded_Professional_Tax`,`Ded_Others`,"
                    + " `Prev_Employer_YTD_Gross`,`EmpSavings1`,`EmpSavings2`,`EmpSavings3`,`EmpSavings4`,`EmpSavings5`,`EmpId`,ProjectEndDate,NetPaid,Employer_ESI,Employee_ESI ,OnProject,OnSite,TotalCost "
                    + "FROM tblEmpPayRoll WHERE EmpId =" + empId;
            // System.out.println("queryString0-->" + queryString);
            //  System.out.println("queryString1-->" + queryString1);
            //  System.out.println("queryString2-->" + queryString2);

            statement = connection.createStatement();
            statement1 = connection.createStatement();
            statement2 = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            resultSet1 = statement1.executeQuery(queryString1);
            resultSet2 = statement2.executeQuery(queryString2);

            DateFormatSymbols dfs = new DateFormatSymbols();
            String monthName = dfs.getMonths()[month];

            if (month < 9) {
                sdate = year + "-0" + (month + 1) + "-01";// 2015-02-01
            } else {
                sdate = year + "-" + (month + 1) + "-01";// 
            }
            if (month <= 02) {
                edate = year + "-03-31"; //2015-03-01
            } else {
                edate = (year + 1) + "-03-31";// 2016-03-01
            }
            //  System.out.println("month=" + month);
            //  System.out.println("monthName=" + monthName);
            //String sdate=year+"-"+
            int monthDiff = DataSourceDataProvider.getInstance().getMonthsBetweenDates(sdate, edate);

            //System.out.println("monthDiff--" + monthDiff);
            while (resultSet.next() && resultSet1.next() && resultSet2.next()) {

                NumberFormat formatter = new DecimalFormat("#0.00");
                //    System.out.println("while block");


                DetailsMap.put("title", "Tax Projections of " + resultSet.getString("EmpName"));
                DetailsMap.put("empId", resultSet.getString("EmpId"));
                DetailsMap.put("empName", resultSet.getString("EmpName"));
                DetailsMap.put("monthYear", monthName + " " + year);
                DetailsMap.put("month", "April & " + monthVal);

                DetailsMap.put("basic", resultSet.getString("EarnedBasicPay"));
                DetailsMap.put("pbasic", formatter.format(resultSet1.getDouble("BasicPay") * monthDiff));
                double basic = resultSet.getDouble("EarnedBasicPay") + (resultSet1.getDouble("BasicPay") * monthDiff);
                DetailsMap.put("tbasic", formatter.format(basic));

                DetailsMap.put("da", resultSet.getString("EarnedDA"));
                DetailsMap.put("pda", formatter.format(resultSet1.getDouble("DA") * monthDiff));
                double da = resultSet.getDouble("EarnedDA") + (resultSet1.getDouble("DA") * monthDiff);
                DetailsMap.put("tda", formatter.format(da));

                DetailsMap.put("hra", resultSet.getString("EarnedHRA"));
                DetailsMap.put("phra", formatter.format(resultSet1.getDouble("HRA") * monthDiff));
                double hra = resultSet.getDouble("EarnedHRA") + (resultSet1.getDouble("HRA") * monthDiff);
                DetailsMap.put("thra", formatter.format(hra));


                DetailsMap.put("ta", resultSet.getString("EarnedTA"));
                DetailsMap.put("pta", formatter.format(resultSet1.getDouble("TA") * monthDiff));
                double TA = resultSet.getDouble("EarnedTA") + (resultSet1.getDouble("TA") * monthDiff);
                DetailsMap.put("tta", formatter.format(TA));


                DetailsMap.put("ra", resultSet.getString("EarnedRA"));
                DetailsMap.put("pra", formatter.format(resultSet1.getDouble("RA") * monthDiff));
                double EarnedRA = resultSet.getDouble("EarnedRA") + (resultSet1.getDouble("RA") * monthDiff);
                DetailsMap.put("tra", formatter.format(EarnedRA));

                DetailsMap.put("entertainment", resultSet.getString("EarnedEntertainment"));
                DetailsMap.put("pEntertainment", formatter.format(resultSet1.getDouble("Entertainment") * monthDiff));
                double Entertainment = resultSet.getDouble("EarnedEntertainment") + (resultSet1.getDouble("Entertainment") * monthDiff);
                DetailsMap.put("tEntertainment", formatter.format(Entertainment));

                DetailsMap.put("kidsEdu", resultSet.getString("EarnedKidsEducation"));
                DetailsMap.put("pKidsEdu", formatter.format(resultSet1.getDouble("KidsEducation") * monthDiff));
                double EarnedKidsEducation = resultSet.getDouble("EarnedKidsEducation") + (resultSet1.getDouble("KidsEducation") * monthDiff);
                DetailsMap.put("tKidsEdu", formatter.format(EarnedKidsEducation));

                DetailsMap.put("vAllowance", resultSet.getString("EarnedVehcleAllowance"));
                DetailsMap.put("pvAllowance", formatter.format(resultSet1.getDouble("VehcleAllowance") * monthDiff));
                double EarnedVehcleAllowance = resultSet.getDouble("EarnedVehcleAllowance") + (resultSet1.getDouble("VehcleAllowance") * monthDiff);
                DetailsMap.put("tvAllowance", formatter.format(EarnedVehcleAllowance));

                DetailsMap.put("cca", resultSet.getString("EarnedCCA"));
                DetailsMap.put("pcca", formatter.format(resultSet1.getDouble("CCA") * monthDiff));
                double CCA = resultSet.getDouble("EarnedCCA") + (resultSet1.getDouble("CCA") * monthDiff);
                DetailsMap.put("tcca", formatter.format(CCA));

                DetailsMap.put("empOthers", resultSet.getString("EmpOthers"));
                DetailsMap.put("pEmpOthers", formatter.format(resultSet1.getDouble("EmpOthers") * monthDiff));
                double EmpOthers = resultSet.getDouble("EmpOthers") + (resultSet1.getDouble("EmpOthers") * monthDiff);
                DetailsMap.put("tEmpOthers", formatter.format(EmpOthers));

                DetailsMap.put("projectpay", resultSet.getString("EarnedProjectPay"));
                DetailsMap.put("pProjectpay", formatter.format(resultSet1.getDouble("ProjectPay") * monthDiff));
                double ProjectPay = resultSet.getDouble("EarnedProjectPay") + (resultSet1.getDouble("ProjectPay") * monthDiff);
                DetailsMap.put("tProjectpay", formatter.format(ProjectPay));

                DetailsMap.put("bonus", resultSet.getString("EarnedBonus"));
                //  DetailsMap.put("pBonus", formatter.format(resultSet1.getDouble("LongTermBonus") * monthDiff));
                DetailsMap.put("pBonus", "");
                // double EarnedLongTermBonus = resultSet.getDouble("EarnedLongTermBonus") + (resultSet1.getDouble("LongTermBonus") * monthDiff);
                double Bonus = resultSet.getDouble("EarnedBonus");
                DetailsMap.put("tBonus", formatter.format(Bonus));

                DetailsMap.put("attAllow", resultSet.getString("EarnedAttAllowance"));
                DetailsMap.put("pAttAllow", formatter.format(resultSet1.getDouble("AttAllowance") * monthDiff));
                double AttAllowance = resultSet.getDouble("EarnedAttAllowance") + (resultSet1.getDouble("AttAllowance") * monthDiff);
                DetailsMap.put("tAttAllow", formatter.format(AttAllowance));

                DetailsMap.put("nsAllow", resultSet.getString("EarnedSplAllowance"));
                DetailsMap.put("pNsAllow", formatter.format(resultSet1.getDouble("SplAllowance") * monthDiff));
                double nsSplAllowance = resultSet.getDouble("EarnedSplAllowance") + (resultSet1.getDouble("SplAllowance") * monthDiff);
                DetailsMap.put("tNsAllow", formatter.format(nsSplAllowance));

                double earnedGrossPay = resultSet.getDouble("EarnedBasicPay") + resultSet.getDouble("EarnedDA") + resultSet.getDouble("EarnedHRA") + resultSet.getDouble("EarnedTA") + resultSet.getDouble("EarnedRA") + resultSet.getDouble("EarnedEntertainment") + resultSet.getDouble("EarnedKidsEducation") + resultSet.getDouble("EarnedVehcleAllowance") + resultSet.getDouble("EarnedCCA") + resultSet.getDouble("EmpOthers") + resultSet.getDouble("EarnedProjectPay") + resultSet.getDouble("EarnedBonus") + resultSet.getDouble("EarnedAttAllowance") + resultSet.getDouble("EarnedSplAllowance");

                double projectedGrossPay = (resultSet1.getDouble("BasicPay") + resultSet1.getDouble("DA") + resultSet1.getDouble("HRA") + resultSet1.getDouble("TA") + resultSet1.getDouble("RA") + resultSet1.getDouble("Entertainment") + resultSet1.getDouble("KidsEducation") + resultSet1.getDouble("VehcleAllowance") + resultSet1.getDouble("CCA") + resultSet1.getDouble("EmpOthers") + resultSet1.getDouble("ProjectPay") + resultSet1.getDouble("AttAllowance") + resultSet1.getDouble("SplAllowance")) * monthDiff;
                double completeGross = earnedGrossPay + projectedGrossPay;
                //DetailsMap.put("gross", resultSet.getString("EarnedGrossPay"));
                DetailsMap.put("gross", formatter.format(earnedGrossPay));
                //DetailsMap.put("pGross", formatter.format(resultSet1.getDouble("GrossPay") * monthDiff));
                DetailsMap.put("pGross", formatter.format(projectedGrossPay));
                double EarnedGrossPay = resultSet.getDouble("EarnedGrossPay") + (resultSet1.getDouble("GrossPay") * monthDiff) - AttAllowance;
                //DetailsMap.put("tGross", formatter.format(EarnedGrossPay));
                DetailsMap.put("tGross", formatter.format(completeGross));
                DetailsMap.put("TAE", formatter.format(TA));
                DetailsMap.put("RentReceipts", resultSet2.getString("EmpSavings5"));
                // DetailsMap.put("HRE", formatter.format(hra));


                DetailsMap.put("PFTax", resultSet.getString("ProfessionalTax"));
                DetailsMap.put("pPFTax", formatter.format(resultSet1.getDouble("ProfessionalTax") * monthDiff));
                double ProfessionalTax = resultSet.getDouble("ProfessionalTax") + (resultSet1.getDouble("ProfessionalTax") * monthDiff);
                DetailsMap.put("tPFTax", formatter.format(ProfessionalTax));

                DetailsMap.put("otherGross", "0.00");

                //  double sal = completeGross - (TA + hra + ProfessionalTax) + AttAllowance;


                DetailsMap.put("pf", resultSet.getString("EmployeePF"));
                DetailsMap.put("ppf", formatter.format(resultSet1.getDouble("EmployeePF") * monthDiff));
                double EmployeePF = resultSet.getDouble("EmployeePF") + (resultSet1.getDouble("EmployeePF") * monthDiff);
                DetailsMap.put("tpf", formatter.format(EmployeePF));




                DetailsMap.put("plic", formatter.format(resultSet1.getDouble("LICFROMSalary") * monthDiff));







                DetailsMap.put("MutualFunds", "0.00");

                DetailsMap.put("HBLPrinciple", "0.00");
                //double totalSavings = EmployeePF + TutionFee + ppf + lic + taxSavingFD;
                double totalSavings = EmployeePF + Double.parseDouble(payrollAjaxHandlerAction.getTutionfees()) + Double.parseDouble(payrollAjaxHandlerAction.getPpf()) + Double.parseDouble(payrollAjaxHandlerAction.getLifeIns()) + Double.parseDouble(payrollAjaxHandlerAction.getFd());
                DetailsMap.put("totalSavings", formatter.format(totalSavings));
                double maxSavings = totalSavings;
                if (totalSavings > 150000) {
                    maxSavings = 150000;
                }
                DetailsMap.put("maxSavings", formatter.format(maxSavings));

                //double HBLoan = Double.parseDouble((String) TaxExemptionMap.get("HBLoan")) + (resultSet1.getDouble("HBLoan") * monthDiff);







                DetailsMap.put("healthInsFrom", resultSet.getString("earnedHelth"));
                DetailsMap.put("phealthInsFrom", formatter.format(resultSet1.getDouble("Health") * monthDiff));
                double Health = resultSet.getDouble("earnedHelth") + (resultSet1.getDouble("Health") * monthDiff);
                DetailsMap.put("thealthInsFrom", formatter.format(Health));

                double healthInsDirect = 0.00;
                DetailsMap.put("healthInsDirect", formatter.format(healthInsDirect));
                double v_Earned_TaxableSalary = 0.00;//taxablesal
                double taxablesal = v_Earned_TaxableSalary;//taxablesal
                double temptax = 0.00;
                double rebate = 0.00;
                double v_Edu_Cess = 0.00;
                double totalTax = 0.00;
                double taxRealised = 0.00;
                double balanceTax = 0.00;
                double taxdeductable = 0.00;
                double v_IncomeTax_Calc_TE = 0.00;// tempTax
                double taxOnAbove = 0.00;// tempTax
                // v_Earned_TaxableSalary = basic + da + hra + TA + Entertainment + CCA + EmpOthers + ProjectPay + nsSplAllowance + AttAllowance - EmployeePF - Health - ProfessionalTax;
                // v_Earned_TaxableSalary+=Bonus-TA;
                // System.out.println("v_Earned_TaxableSalary----first--" + v_Earned_TaxableSalary);
                double v_TempEmpSavings5ForTaxCalc1 = (basic + da) * 0.4;
                /*Second calculation based on 10% of Basic and DA and Subtracting it from HRE(EmpSavings5) given*/
                //double v_TempEmpSavings5ForTaxCalc2 = resultSet2.getDouble("EmpSavings5") - (resultSet1.getDouble("EarnedBasicPay") + resultSet1.getDouble("EarnedDA")) * 0.1;
                double v_TempEmpSavings5ForTaxCalc2 = resultSet2.getDouble("EmpSavings5") - (basic + da) * 0.1;
                if (v_TempEmpSavings5ForTaxCalc2 < 0) {
                    v_TempEmpSavings5ForTaxCalc2 = 0.00;
                }
                double v_Earned_HRA = hra;

                double v_FinalEmpSavings5ForTaxCalc = 0.00;
                //case0: 
                if ((v_TempEmpSavings5ForTaxCalc1 == v_Earned_HRA)) {
                    v_FinalEmpSavings5ForTaxCalc = v_TempEmpSavings5ForTaxCalc1;
                }

                /*case1:*/
                if ((v_TempEmpSavings5ForTaxCalc1 < v_Earned_HRA) && (v_TempEmpSavings5ForTaxCalc1 < v_TempEmpSavings5ForTaxCalc2)) {
                    v_FinalEmpSavings5ForTaxCalc = v_TempEmpSavings5ForTaxCalc1;
                }

                /*case2:*/
                if ((v_TempEmpSavings5ForTaxCalc2 < v_Earned_HRA) && (v_TempEmpSavings5ForTaxCalc2 < v_TempEmpSavings5ForTaxCalc1)) {
                    v_FinalEmpSavings5ForTaxCalc = v_TempEmpSavings5ForTaxCalc2;
                }

                /*case3:*/
                if ((v_Earned_HRA < v_TempEmpSavings5ForTaxCalc1) && (v_Earned_HRA < v_TempEmpSavings5ForTaxCalc2)) {
                    v_FinalEmpSavings5ForTaxCalc = v_Earned_HRA;
                }
                double finalSavingsExemption = resultSet2.getDouble("EmpSavings5");
                if (resultSet2.getDouble("EmpSavings5") > v_FinalEmpSavings5ForTaxCalc) {
                    finalSavingsExemption = v_FinalEmpSavings5ForTaxCalc;
                }

                double sal = completeGross - (TA + finalSavingsExemption + ProfessionalTax);
                // ============================ for cal pending
                //v_Earned_TaxableSalary = sal - (maxSavings + Health + eduloan + totalHBLoan + healthInsDirect);
                //============================== 
                taxablesal = v_Earned_TaxableSalary;
                //  System.out.println("first v_Earned_TaxableSalary" + v_Earned_TaxableSalary);
                //double v_TempEmpSavings5ForTaxCalc1 = (resultSet1.getDouble("EarnedBasicPay") + resultSet1.getDouble("EarnedDA")) * 0.4;

                DetailsMap.put("miracleSal", formatter.format(sal));
                DetailsMap.put("totalSal", formatter.format(sal));
//                v_Earned_TaxableSalary = v_Earned_TaxableSalary - (resultSet2.getDouble("EmpSavings1") + resultSet2.getDouble("EmpSavings2") + resultSet2.getDouble("EmpSavings3") + resultSet2.getDouble("EmpSavings4") + v_FinalEmpSavings5ForTaxCalc);
                // double v_TaxableIncomeForTaxCalc = v_Earned_TaxableSalary - 250000.00;//Taxonabove --- v_TaxableIncomeForTaxCalc
                //  double temp_v_TaxableIncomeForTaxCalc=v_TaxableIncomeForTaxCalc;
                //System.out.println("v_TaxableIncomeForTaxCalc temp_v_TaxableIncomeForTaxCalc--"+temp_v_TaxableIncomeForTaxCalc);
                //v_Earned_TaxableSalary-=v_FinalEmpSavings5ForTaxCalc;
                double temp_v_TaxableIncomeForTaxCalc = v_Earned_TaxableSalary;
                double v_TaxableIncomeForTaxCalc = v_Earned_TaxableSalary;
                if (v_TaxableIncomeForTaxCalc > 250000) {

                    /*Removal of Non Taxable income ie., 2.5L from the gross calculated*/
                    v_TaxableIncomeForTaxCalc = v_Earned_TaxableSalary - 250000.00;
                    double v_TaxableIncomeForTaxCalc1 = v_TaxableIncomeForTaxCalc;
                    //   System.out.println("v_TaxableIncomeForTaxCalc( v_Earned_TaxableSalary - 250000.00)---" + v_TaxableIncomeForTaxCalc);
                    if (v_TaxableIncomeForTaxCalc > 0) {
                        // temptax = (double) Taxonabove / 10;
                        if (v_TaxableIncomeForTaxCalc <= 250000) {
                            v_IncomeTax_Calc_TE = v_TaxableIncomeForTaxCalc * 0.1;// -- 20000

                        } else {
                            v_IncomeTax_Calc_TE = 250000 * 0.1; // -- 25000
                            v_TaxableIncomeForTaxCalc = v_TaxableIncomeForTaxCalc - 250000; // -- 3500000
                        }
//                        if (v_TaxableIncomeForTaxCalc > 250000) {
//                            //Taxonabove = Taxonabove - 250000.00;
///*Calculation of 10% of the Taxable Income calculated after the removal of non taxable income for incomeTax(TE) calculation*/
//                            v_IncomeTax_Calc_TE = v_TaxableIncomeForTaxCalc * 0.1;
//                            System.out.println("v_TaxableIncomeForTaxCalc > 250000 v_IncomeTax_Calc_TE==" + v_IncomeTax_Calc_TE);
//                            v_TaxableIncomeForTaxCalc = v_TaxableIncomeForTaxCalc - 250000;
//                        }
//                        if (v_TaxableIncomeForTaxCalc > 0) {
//
//                            if ((v_TaxableIncomeForTaxCalc > 250000) && (v_TaxableIncomeForTaxCalc <= 500000)) {
//                                v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + (v_TaxableIncomeForTaxCalc * 0.2);
//                            }
//                            /*Calculation of 20% of the Taxable Income calculated after the removal of non taxable income for incomeTax(TE) calculation*/
//                            System.out.println("v_IncomeTax_Calc_TE==" + v_IncomeTax_Calc_TE);
//
//                            // temptax = temptax + (double) (Taxonabove * 2) / 10;
//                            if (v_TaxableIncomeForTaxCalc > 500000) {
//                                //Taxonabove = Taxonabove - 500000.00;
//                                v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + (500000 * 0.2);
//                                v_TaxableIncomeForTaxCalc = v_TaxableIncomeForTaxCalc - 500000;
//                                if (v_TaxableIncomeForTaxCalc > 0) {
//                                    // temptax = temptax + (double) (Taxonabove * 3) / 10;
//                                    v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + (v_TaxableIncomeForTaxCalc * 0.3);
//                                }
//                            }
//                            System.out.println("v_IncomeTax_Calc_TE==" + v_IncomeTax_Calc_TE);
//                        }
//                        if (temp_v_TaxableIncomeForTaxCalc < 500000) {
//                            v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE - 2000;
//                        }
//                        EDucess = v_IncomeTax_Calc_TE * 0.03;
//                        v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + EDucess;
//                        System.out.println("v_IncomeTax_Calc_TE==" + v_IncomeTax_Calc_TE);
//                        rebate = 0.00;
//                        if (v_Earned_TaxableSalary < 500000) {
//                            rebate = 2000;
//                        }
//                        totalTax = (v_IncomeTax_Calc_TE - rebate) + EDucess;
//                        
                        if (v_TaxableIncomeForTaxCalc > 0) { //-- 3500000 ----------------
                            // if ((v_TaxableIncomeForTaxCalc > 250000) && (v_TaxableIncomeForTaxCalc <= 500000)) {
                            if ((v_TaxableIncomeForTaxCalc1 > 250000) && (v_TaxableIncomeForTaxCalc <= 500000)) {
                                v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + (v_TaxableIncomeForTaxCalc * 0.2);
                            }
                            if (v_TaxableIncomeForTaxCalc > 500000) {
                                v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + (500000 * 0.2);
                                v_TaxableIncomeForTaxCalc = v_TaxableIncomeForTaxCalc - 500000;
                                if (v_TaxableIncomeForTaxCalc > 0) {
                                    v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + (v_TaxableIncomeForTaxCalc * 0.3);
                                }
                            }
                        }
                        taxOnAbove = v_IncomeTax_Calc_TE;
                        if (temp_v_TaxableIncomeForTaxCalc < 500000) {
                            v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE - 2000;
                        }
                        v_Edu_Cess = v_IncomeTax_Calc_TE * 0.03;
                        v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + v_Edu_Cess;
                        totalTax = v_IncomeTax_Calc_TE;
                    }
                    taxRealised = TDS_uptoPreviousMonths;
                    if (month == 3) {
                        taxRealised = 0;
                    }
                    balanceTax = v_IncomeTax_Calc_TE - taxRealised;
                    taxdeductable = Math.ceil((v_IncomeTax_Calc_TE - taxRealised) / (monthDiff + 1));
                    // System.out.println("v_IncomeTax_Calc_TE==" + v_IncomeTax_Calc_TE);
                } else {
                    //  System.out.println("Not Aplicable!!");
                }
                //  DetailsMap.put("taxOnAbove", formatter.format(temptax));
                if (v_Earned_TaxableSalary < 500000) {
                    rebate = 2000;
                }
                DetailsMap.put("HRE", formatter.format(v_FinalEmpSavings5ForTaxCalc));

                DetailsMap.put("taxableSal", formatter.format(taxablesal));
                DetailsMap.put("taxOnAbove", formatter.format(Math.round(taxOnAbove)));
                DetailsMap.put("rebate", formatter.format(rebate));
                DetailsMap.put("eduCess", formatter.format(Math.round(v_Edu_Cess)));
                DetailsMap.put("totalTax", formatter.format(Math.round(totalTax)));
                DetailsMap.put("taxRealised", formatter.format(Math.round(taxRealised)));
                DetailsMap.put("taxRealisedByOthers", "-");
                //balanceTax = totalTax - taxRealised;
                DetailsMap.put("balanceTax", formatter.format(Math.round(balanceTax)));
                // taxdeductable = (double) balanceTax / (monthDiff - 1);
                DetailsMap.put("taxdeductable", formatter.format(taxdeductable));
                reportResult = formatter.format(taxdeductable) + "";
            }
            // System.out.println("HashMap-----------------" + DetailsMap.size());
            if (DetailsMap.size() > 0) {
            } else {

                reportResult = "No records are available between selected dates";
                //   httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();


        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
                if (resultSet2 != null) {
                    resultSet2.close();
                    resultSet2 = null;
                }
                if (statement2 != null) {
                    statement2.close();
                    statement2 = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException sqle) {
                sqle.printStackTrace();

            }
        }

        return reportResult;
    }

    public String getEmployeeNumberByLoginId(String loginId) throws ServiceLocatorException {
        String empNo = "";
        int empId = 0;
        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet resultSet = null;
        String result = "";
        String response = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;

            query = "SELECT EmpNo,Id FROM tblEmployee WHERE LoginId='" + loginId + "'";
            // System.out.println("Query-->" + query);
            preStmt = connection.prepareStatement(query);
            resultSet = preStmt.executeQuery();
            if (resultSet.next()) {
                empNo = resultSet.getString("EmpNo");
                empId = resultSet.getInt("Id");
//               getEmpSavings1234and5Values()
            }

            if (empNo != null && !"".equals(empNo)) {
                result = DataSourceDataProvider.getInstance().getEmpSavings1234and5Values(DataSourceDataProvider.getInstance().getEmpIdByLoginId(loginId));
            }
            response = empNo + "$" + result + "$" + empId;


        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }

        return response;
    }

    public boolean addTaxAssumptionFromPayroll(PayrollAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean isInserted = false;
        //   String queryString = "INSERT INTO tblEmpTaxExemptionDetails(EmpId,ExemptionId,STATUS,AppliedBy,SavingsAmount,ApprovedBy,ApprovedDate,ApproverComments,AttachmentName,AttachmentLocation,ApprovedAmount,PaymentDate,ValidityDate,SavingsType) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

         queryString = "INSERT INTO tblEmpTaxExemptionDetails(EmpId,ExemptionId,STATUS,AppliedBy,SavingsAmount,ApprovedBy,ApprovedDate,ApproverComments,AttachmentName,AttachmentLocation,ApprovedAmount,PaymentDate,ValidityDate,SavingsType,PAN_No,Owner_Name,OrgId,FinancialYear,RentStartDate,RentEndDate,MonthlyAmount,PolicyNumber,LICPremium,Form12BBAttachmentName,Form12BBAttachmentPath,HouseAddress) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, Integer.parseInt(ajaxHandlerAction.getEmpId()));
            preparedStatement.setInt(2, ajaxHandlerAction.getExemptionId());
            preparedStatement.setString(3, ajaxHandlerAction.getOverLayStatus());
            preparedStatement.setString(4, ajaxHandlerAction.getCreatedBy());
            preparedStatement.setDouble(5, ajaxHandlerAction.getOverlaySavingAmount());
            preparedStatement.setString(6, ajaxHandlerAction.getCreatedBy());
            preparedStatement.setTimestamp(7, DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(8, ajaxHandlerAction.getComments());
            preparedStatement.setString(9, ajaxHandlerAction.getFileFileName());
            preparedStatement.setString(10, ajaxHandlerAction.getAttachmentLocation());
            preparedStatement.setDouble(11, ajaxHandlerAction.getOverlayApprovedAmount());
            if(ajaxHandlerAction.getPaymentDateEmp() != null && !"".equals(ajaxHandlerAction.getPaymentDateEmp())){
            preparedStatement.setDate(12, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getPaymentDateEmp()));
            }else{
                  preparedStatement.setDate(12,null);
            }
           // System.out.println("ajaxHandlerAction.getValidityDate()==="+ajaxHandlerAction.getValidityDate());
             if(ajaxHandlerAction.getValidityDate() != null && !"".equals(ajaxHandlerAction.getValidityDate())){
            preparedStatement.setDate(13, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getValidityDate()));
             }else{
                 preparedStatement.setDate(13, null);
             }
            preparedStatement.setString(14, ajaxHandlerAction.getTefType());
            if (ajaxHandlerAction.getExemptionId() == 18) {
                preparedStatement.setString(15, ajaxHandlerAction.getPanNumber());
                preparedStatement.setString(16, ajaxHandlerAction.getOwnerName());
            } else {
                preparedStatement.setString(15, " ");
                preparedStatement.setString(16, " ");
            }
            preparedStatement.setInt(17, ajaxHandlerAction.getOrgId());
              preparedStatement.setString(18, ajaxHandlerAction.getFinancialYear());
             if (ajaxHandlerAction.getExemptionId() == 18) {
          
            if (ajaxHandlerAction.getRentStartDate() != null && !"".equals(ajaxHandlerAction.getRentStartDate())) {
                preparedStatement.setDate(19, DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(ajaxHandlerAction.getRentStartDate())));
            } else {
                preparedStatement.setDate(19, null);
            }

            if (ajaxHandlerAction.getRentEndDate() != null && !"".equals(ajaxHandlerAction.getRentEndDate())) {
                preparedStatement.setDate(20, DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(ajaxHandlerAction.getRentEndDate())));
            } else {
                preparedStatement.setDate(20, null);
            }

            preparedStatement.setDouble(21, ajaxHandlerAction.getMonthlyAmount());
             }else{
                   preparedStatement.setDate(19, null);
                     preparedStatement.setDate(20, null);
                      preparedStatement.setDouble(21, 0.00);
             }
             preparedStatement.setString(22, ajaxHandlerAction.getPolicyNumber());
             if(ajaxHandlerAction.getExemptionId() == 1){
                  preparedStatement.setString(23, ajaxHandlerAction.getLicPremium());
             }
             else{
                  preparedStatement.setString(23, ajaxHandlerAction.getLicPremium());
             }
                if((ajaxHandlerAction.getExemptionId()) == 18 && ajaxHandlerAction.getOverlayApprovedAmount() > 100000){
                   
                  preparedStatement.setString(24, ajaxHandlerAction.getFile1FileName());
            preparedStatement.setString(25, ajaxHandlerAction.getAttachmentLocation1());
                }
                else{
                     preparedStatement.setString(24, "");
            preparedStatement.setString(25, "");
                }
           preparedStatement.setString(26, ajaxHandlerAction.getHouseAddress());
            isInserted = preparedStatement.execute();
          //   System.out.println("isInserted-->" + isInserted);
            //  System.out.println("getOverLayStatus-->" + ajaxHandlerAction.getOverLayStatus());

            if (!isInserted && ajaxHandlerAction.getOverLayStatus().equals("Approved")) {
                //      System.out.println("in ifff-->" + isInserted);
                String savingsResult = DataSourceDataProvider.getInstance().getEmpSavings1234and5Values(Integer.parseInt(ajaxHandlerAction.getEmpId()));
                //  System.out.println("in savingsResult-->" + savingsResult);
                //result = empSavings1 + "#" + empSavings2 + "#" + empSavings3_parents + "#" + empSavings4 + "#" + empSavings5+"#"+empSavings3_self+"#"+(heathAmt*12);

                double savings1 = Double.parseDouble(savingsResult.split("#")[0]);
                double savings2 = Double.parseDouble(savingsResult.split("#")[1]);
                double savings3 = Double.parseDouble(savingsResult.split("#")[2]) + Double.parseDouble(savingsResult.split("#")[5]) + Double.parseDouble(savingsResult.split("#")[6]);
                double savings4 = Double.parseDouble(savingsResult.split("#")[3]);
                double savings5 = Double.parseDouble(savingsResult.split("#")[4]);
                double savings6 = Double.parseDouble(savingsResult.split("#")[7]);
                updateSavings1234And5FromPayroll(savings1, savings2, savings3, savings4, savings5, savings6, Integer.parseInt(ajaxHandlerAction.getEmpId()));

            }

        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isInserted;

    }


    public String getTaxAssumptionFromPayroll(int tefId) throws ServiceLocatorException {
        String empNo = "";
        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet resultSet = null;
        String result = "";
        String response = "";
        JSONObject taxJson = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
//SELECT tblEmpTaxExemptionDetails.Id,ExemptionId,tblLKTaxExemptions.Category,tblLKTaxExemptions.ExemptionType,SavingsAmount,tblEmpTaxExemptionDetails.STATUS,ApprovedAmount,PaymentDate,ApproverComments,AttachmentName,tblEmpTaxExemptionDetails.EmpId from tblEmpTaxExemptionDetails left outer join tblLKTaxExemptions  ON (tblEmpTaxExemptionDetails.ExemptionId=tblLKTaxExemptions.id) where tblEmpTaxExemptionDetails.Id = ?
            //   query = "SELECT tblEmpTaxExemptionDetails.Id,ExemptionId,tblLKTaxExemptions.Category,tblLKTaxExemptions.ExemptionType,SavingsAmount,tblEmpTaxExemptionDetails.STATUS,ApprovedAmount,PaymentDate,ApproverComments,AttachmentName,tblEmpTaxExemptionDetails.EmpId,ValidityDate,SavingsType from tblEmpTaxExemptionDetails left outer join tblLKTaxExemptions  ON (tblEmpTaxExemptionDetails.ExemptionId=tblLKTaxExemptions.id) where tblEmpTaxExemptionDetails.Id = " + tefId;
            query = "SELECT tblEmpTaxExemptionDetails.Id,ExemptionId,tblLKTaxExemptions.Category,tblLKTaxExemptions.ExemptionType,SavingsAmount,tblEmpTaxExemptionDetails.STATUS,ApprovedAmount,PaymentDate,ApproverComments,AttachmentName,tblEmpTaxExemptionDetails.EmpId,ValidityDate,SavingsType,PAN_No,Owner_Name, FinancialYear,RentStartDate,RentEndDate,MonthlyAmount,tblEmpTaxExemptionDetails.Comments,tblEmpTaxExemptionDetails.PolicyNumber,LICPremium,Form12BBAttachmentName,HouseAddress from tblEmpTaxExemptionDetails left outer join tblLKTaxExemptions  ON (tblEmpTaxExemptionDetails.ExemptionId=tblLKTaxExemptions.id) where tblEmpTaxExemptionDetails.Id = " + tefId;

           // System.out.println("query==="+query);
            preStmt = connection.prepareStatement(query);
            resultSet = preStmt.executeQuery();
            taxJson = new JSONObject();
            if (resultSet.next()) {
                taxJson.put("TefId", resultSet.getString("Id"));
                taxJson.put("ExemptionId", resultSet.getString("ExemptionId"));
                taxJson.put("Category", resultSet.getString("Category"));
                taxJson.put("ExemptionType", resultSet.getString("ExemptionType"));
                taxJson.put("SavingsAmount", resultSet.getString("SavingsAmount"));
                taxJson.put("STATUS", resultSet.getString("STATUS"));
                taxJson.put("ApprovedAmount", resultSet.getString("ApprovedAmount"));
                //taxJson.put("PaymentDate", DateUtility.getInstance().convertToviewFormat(resultSet.getString("PaymentDate")));
                  if (resultSet.getString("PaymentDate") != null && !"".equals(resultSet.getString("PaymentDate"))) {
                    taxJson.put("PaymentDate", DateUtility.getInstance().convertToviewFormat(resultSet.getString("PaymentDate")));

                } else {
                    taxJson.put("PaymentDate", "");
                }
                taxJson.put("ApproverComments", resultSet.getString("ApproverComments"));
                taxJson.put("Comments", resultSet.getString("Comments"));
                if (resultSet.getString("AttachmentName") != null) {
                    taxJson.put("AttachmentName", resultSet.getString("AttachmentName"));
                } else {
                    taxJson.put("AttachmentName", "-");
                }
                
                taxJson.put("EmpId", resultSet.getString("EmpId"));
                
                
			    if (resultSet.getString("ValidityDate") != null && !"".equals(resultSet.getString("ValidityDate"))) {
                    taxJson.put("ValidityDate", DateUtility.getInstance().convertToviewFormat(resultSet.getString("ValidityDate")));

                } else {
                    taxJson.put("ValidityDate", "");
                }
                             taxJson.put("FinancialYear", resultSet.getString("FinancialYear"));
                taxJson.put("MonthlyAmount", resultSet.getString("MonthlyAmount"));

                if (resultSet.getString("RentStartDate") != null && !"".equals(resultSet.getString("RentStartDate"))) {
                    taxJson.put("RentStartDate", resultSet.getString("RentStartDate"));
                } else {
                    taxJson.put("RentStartDate", "");
                }
                if (resultSet.getString("RentEndDate") != null && !"".equals(resultSet.getString("RentEndDate"))) {
                    taxJson.put("RentEndDate", resultSet.getString("RentEndDate"));
                } else {
                    taxJson.put("RentEndDate", "");
                }
              //  taxJson.put("ValidityDate", DateUtility.getInstance().convertToviewFormat(resultSet.getString("ValidityDate")));
                 if (resultSet.getString("PolicyNumber") != null && !"".equals(resultSet.getString("PolicyNumber"))) {
                    taxJson.put("PolicyNumber", resultSet.getString("PolicyNumber"));
                } else {
                    taxJson.put("PolicyNumber", "");
                }
                 if (resultSet.getString("LICPremium") != null && !"".equals(resultSet.getString("LICPremium"))) {
                    taxJson.put("licPremium", resultSet.getString("LICPremium"));
                } else {
                    taxJson.put("licPremium", "0");
                }
                if (resultSet.getString("Form12BBAttachmentName") != null) {
                    taxJson.put("Form12BBAttachmentName", resultSet.getString("Form12BBAttachmentName"));
                } else {
                    taxJson.put("Form12BBAttachmentName", "-");
                }
                if (resultSet.getString("HouseAddress") != null && !"".equals(resultSet.getString("HouseAddress"))) {
                    taxJson.put("HouseAddress", resultSet.getString("HouseAddress"));
                } else {
                    taxJson.put("HouseAddress", "");
                }
                
                taxJson.put("EmpName", DataSourceDataProvider.getInstance().getEmpNameByEmpId(resultSet.getInt("EmpId")));
                result = DataSourceDataProvider.getInstance().getEmpSavings1234and5Values(resultSet.getInt("EmpId"));
                taxJson.put("empSaving1", result.split("#")[0]);
                taxJson.put("empSaving2", result.split("#")[1]);
                taxJson.put("empSaving3_parens", result.split("#")[2]);
                taxJson.put("empSaving4", result.split("#")[3]);
                taxJson.put("empSaving5", result.split("#")[4]);
                taxJson.put("empSaving3_self", result.split("#")[5]);
                taxJson.put("healthAmt", result.split("#")[6]);
                taxJson.put("empSaving6", result.split("#")[7]);
                taxJson.put("tefType", resultSet.getString("SavingsType"));
                String PAN_No = resultSet.getString("PAN_No");
                String Owner_Name = resultSet.getString("Owner_Name");
                if (PAN_No == null) {
                    PAN_No = "";
                }
                if (Owner_Name == null) {
                    Owner_Name = "";
                }
                taxJson.put("PANNumber", PAN_No);
                taxJson.put("ownerName", Owner_Name);
                
            }



        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
//System.out.println("taxJson-->"+taxJson.toString());
        return taxJson.toString();
    }

    public boolean updateTaxAssumptionFromPayroll(PayrollAjaxHandlerAction ajaxHandlerAction) throws ServiceLocatorException {
        //System.out.println("upadteTaxExemption method");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean isUpdated = false;
        String queryString = "";


        //   queryString = "UPDATE tblEmpTaxExemptionDetails SET SavingsAmount=?,ApprovedAmount=?,STATUS=?,ApprovedBy=?,ApprovedDate=?,ApproverComments=?,PaymentDate=?,ValidityDate=?,SavingsType=? WHERE Id=?";
        // System.out.println("queryString-->" + queryString);

        queryString = "UPDATE tblEmpTaxExemptionDetails SET SavingsAmount=?,ApprovedAmount=?,STATUS=?,ApprovedBy=?,ApprovedDate=?,ApproverComments=?,PaymentDate=?,ValidityDate=?,SavingsType=?,PAN_No=?,Owner_Name=? ,RentStartDate=?,RentEndDate=?,MonthlyAmount=?,FinancialYear=?,PolicyNumber=?,LICPremium=?,AttachmentName=?,AttachmentLocation=?,Form12BBAttachmentName=?,Form12BBAttachmentPath=?,HouseAddress=? WHERE Id="+ajaxHandlerAction.getTefId();

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            //  preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());
            preparedStatement.setDouble(1, ajaxHandlerAction.getOverlaySavingAmount());

            preparedStatement.setDouble(2, ajaxHandlerAction.getOverlayApprovedAmount());

            preparedStatement.setString(3, ajaxHandlerAction.getOverLayStatus());

            //preparedStatement.setInt(4, Integer.parseInt(ajaxHandlerAction.getMonth()));
            // preparedStatement.setInt(5, Integer.parseInt(ajaxHandlerAction.getYear()));
            preparedStatement.setString(4, ajaxHandlerAction.getCreatedBy());
            preparedStatement.setTimestamp(5, DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(6, ajaxHandlerAction.getComments());
              if (ajaxHandlerAction.getPaymentDateEmp() != null && !"".equals(ajaxHandlerAction.getPaymentDateEmp())) {
            preparedStatement.setDate(7, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getPaymentDateEmp()));
              }else{
             preparedStatement.setDate(7, null);
        }
                if (ajaxHandlerAction.getValidityDate() != null && !"".equals(ajaxHandlerAction.getValidityDate())) {
            preparedStatement.setDate(8, DateUtility.getInstance().getMysqlDate(ajaxHandlerAction.getValidityDate()));
                }else{
                     preparedStatement.setDate(8, null);
                }
            preparedStatement.setString(9, ajaxHandlerAction.getTefType());
            if (ajaxHandlerAction.getExemptionId() == 18) {
                preparedStatement.setString(10, ajaxHandlerAction.getPanNumber());
                preparedStatement.setString(11, ajaxHandlerAction.getOwnerName());
                    if (ajaxHandlerAction.getRentStartDate() != null && !"".equals(ajaxHandlerAction.getRentStartDate())) {
                    preparedStatement.setDate(12, DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(ajaxHandlerAction.getRentStartDate())));
                } else {
                    preparedStatement.setDate(12, null);
                }

                if (ajaxHandlerAction.getRentEndDate() != null && !"".equals(ajaxHandlerAction.getRentEndDate())) {
                    preparedStatement.setDate(13, DateUtility.getInstance().getMysqlDate(DateUtility.getInstance().convertToviewFormat(ajaxHandlerAction.getRentEndDate())));
                } else {
                    preparedStatement.setDate(13, null);
                }

                preparedStatement.setDouble(14, ajaxHandlerAction.getMonthlyAmount());
            } else {
                preparedStatement.setString(10, " ");
                preparedStatement.setString(11, " ");
                 
                preparedStatement.setDate(12, null);
                preparedStatement.setDate(13, null);
                 preparedStatement.setDouble(14, 0.00);
            }
              preparedStatement.setString(15, ajaxHandlerAction.getFinancialYear());
              preparedStatement.setString(16, ajaxHandlerAction.getPolicyNumber());
              if(ajaxHandlerAction.getExemptionId() == 1){
             preparedStatement.setString(17, ajaxHandlerAction.getLicPremium());
        }
              else{
                   preparedStatement.setString(17, "");
              }
             
                  if (ajaxHandlerAction.getFileFileName() != null && !"".equals(ajaxHandlerAction.getFileFileName()) && !"--".equals(ajaxHandlerAction.getFileFileName())) {
                preparedStatement.setString(18, ajaxHandlerAction.getFileFileName());
                preparedStatement.setString(19, ajaxHandlerAction.getAttachmentLocation());
            }
            else{
                String fileName = DataSourceDataProvider.getInstance().getAttachmentDetails(ajaxHandlerAction.getTefId());
               if(fileName != null){
                String[] attachment =  fileName.split("#");
              
                String attachmentName = attachment[0];
                 //  System.out.println("fileName.split(\"#^$\")[0]--->" + attachmentName);
                String attachmentPath = attachment[1];
             
                preparedStatement.setString(18, attachmentName);
                preparedStatement.setString(19, attachmentPath);
               }else{
                      preparedStatement.setString(18, "");
                preparedStatement.setString(19, "");
               }
                     }
                   if(((ajaxHandlerAction.getExemptionId()) == 18) && (ajaxHandlerAction.getOverlayApprovedAmount() > 100000)){
                      
                           
                    
            if (ajaxHandlerAction.getFile1FileName() != null && !"".equals(ajaxHandlerAction.getFile1FileName()) && !"--".equals(ajaxHandlerAction.getFile1FileName())) {
                preparedStatement.setString(20, ajaxHandlerAction.getFile1FileName());
                preparedStatement.setString(21, ajaxHandlerAction.getAttachmentLocation1());
            }else{
               String fileName = DataSourceDataProvider.getInstance().getAttachmentDetailsForm12BB(ajaxHandlerAction.getTefId());
                if(fileName != null){
               String[] attachment =  fileName.split("#");
               
                String attachmentName = attachment[0];
               //    System.out.println("fileName.split(\"#^$\")[0]--->" + attachmentName);
                String attachmentPath = attachment[1];
             
                preparedStatement.setString(20, attachmentName);
                preparedStatement.setString(21, attachmentPath);
                }else{
                     preparedStatement.setString(20, "");
                preparedStatement.setString(21, "");
                }
            }
             
                   }
                   else{
                        preparedStatement.setString(20, "");
                preparedStatement.setString(21, "");
                   }
                   
                  preparedStatement.setString(22, ajaxHandlerAction.getHouseAddress());  
            isUpdated = preparedStatement.execute();
         //  System.out.println("isUpdated-->" + isUpdated);
            if (!isUpdated && ajaxHandlerAction.getOverLayStatus().equals("Approved")) {
                String savingsResult = DataSourceDataProvider.getInstance().getEmpSavings1234and5Values(Integer.parseInt(ajaxHandlerAction.getEmpId()));

                //result = empSavings1 + "#" + empSavings2 + "#" + empSavings3_parents + "#" + empSavings4 + "#" + empSavings5+"#"+empSavings3_self+"#"+(heathAmt*12);

                double savings1 = Double.parseDouble(savingsResult.split("#")[0]);
                double savings2 = Double.parseDouble(savingsResult.split("#")[1]);
                //  double savings3 = Double.parseDouble(savingsResult.split("#")[2]) + Double.parseDouble(savingsResult.split("#")[5]) + Double.parseDouble(savingsResult.split("#")[6]);
                double savings3 = Double.parseDouble(savingsResult.split("#")[2]) + Double.parseDouble(savingsResult.split("#")[5]);
                double savings4 = Double.parseDouble(savingsResult.split("#")[3]);
                double savings5 = Double.parseDouble(savingsResult.split("#")[4]);
                double savings6 = Double.parseDouble(savingsResult.split("#")[7]);
                updateSavings1234And5FromPayroll(savings1, savings2, savings3, savings4, savings5, savings6, Integer.parseInt(ajaxHandlerAction.getEmpId()));

            }



        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isUpdated;

    }

    public boolean updateSavings1234And5FromPayroll(double savings1, double savings2, double savings3, double savings4, double savings5, double savings6, int empId) throws ServiceLocatorException {
        //  System.out.println("upadteTaxExemption method");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean isUpdated = false;
        String queryString = "";


        queryString = "UPDATE tblEmpPayRoll SET EmpSavings1=?,EmpSavings2=?,EmpSavings3=?,EmpSavings4=?,EmpSavings5=?,EmpSavings6=? WHERE EmpId=?";
        // System.out.println("queryString-->" + queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            //  preparedStatement.setString(1,ajaxHandlerAction.getOverlayReviewType());
            preparedStatement.setDouble(1, savings1);
            preparedStatement.setDouble(2, savings2);
            preparedStatement.setDouble(3, savings3);
            preparedStatement.setDouble(4, savings4);
            preparedStatement.setDouble(5, savings5);
            preparedStatement.setDouble(6, savings6);
            preparedStatement.setInt(7, empId);
            isUpdated = preparedStatement.execute();

        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return isUpdated;

    }

    @Override
    public String getBlockedSalDetails(int year, int month) {
        String result = "";
        Connection connection = null;
        int sno = 0;
        PreparedStatement preStmt = null;


        ResultSet resultSet = null;

        try {



            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;

            query = "SELECT EmpId,MONTH(PayrollDate) AS PayRollMonth,Earned_Net_Paid AS Salary,CONCAT(FName,'.',LName) AS NAME FROM tblEmpWages JOIN tblEmployee ON (tblEmpWages.PayRoll_Id = tblEmployee.EmpNo) WHERE IsBlock = 1 AND MONTH(PayrollDate) ="+ month +" AND YEAR(PayrollDate) = "+year;
            preStmt = connection.prepareStatement(query);
//System.out.println("----> query"+query);


            resultSet = preStmt.executeQuery();

            while (resultSet.next()) {
                sno++;
                int empId = resultSet.getInt("EmpId");
                String payMonth = DateUtility.getInstance().getMonthNamebyMonthNumber(resultSet.getString("PayRollMonth"));
                int salary = resultSet.getInt("Salary");
                String name = resultSet.getString("NAME");

                result = result + sno + "#^$" + empId + "#^$" + name + "#^$" + salary + "*@!";
             //    System.out.println("----> result"+result);

            }

            if (sno == 0) {
                result = "No Records Found";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }

        return result;
    }

    public String doAddPayslipReleases(String releasedFor, PayrollAjaxHandlerAction ajaxHandlerAction) {
        //  System.out.println("payrollAuthenticationRegister");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isUpdated = false;
        String queryString = "";
        String result = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "insert into tblReleasePayslip(ReleasedFor,ReleasedDate,ReleasedBy,STATUS) values(?,?,?,?)";

            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, releasedFor);
            preparedStatement.setString(2, DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getReleasedDate()));
            preparedStatement.setString(3, ajaxHandlerAction.getReleasedBy());
            preparedStatement.setString(4, ajaxHandlerAction.getStatus());
            isUpdated = preparedStatement.execute();

            if (!isUpdated) {
                result = "success";
            } else {
                result = "error";
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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;


    }

    public String doEditPayslipReleases(String releasedFor, PayrollAjaxHandlerAction ajaxHandlerAction) {
        // System.out.println("doEditPayslipReleases");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isUpdated = false;
        String queryString = "";
        String result = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "UPDATE tblReleasePayslip SET ReleasedFor=?,ReleasedDate=?,ReleasedBy=?,STATUS=? WHERE Id=?";

            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setString(1, releasedFor);
            preparedStatement.setString(2, DateUtility.getInstance().convertStringToMySQLDate(ajaxHandlerAction.getReleasedDate()));
            preparedStatement.setString(3, ajaxHandlerAction.getReleasedBy());
            preparedStatement.setString(4, ajaxHandlerAction.getStatus());
            preparedStatement.setInt(5, ajaxHandlerAction.getId());
            isUpdated = preparedStatement.execute();
            if (!isUpdated) {
                result = "success";
            } else {
                result = "error";
            }
            //System.out.println("doEditPayslipReleasesqueryString"+queryString);
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

        return result;

    }

    public String calculateActualDetails(String requestString) throws ServiceLocatorException {

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
        JSONObject taxJson = null;
        try {




            JSONObject inputJson = new JSONObject(requestString);

            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call spGetEarnedPayRollDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            callableStatement.setInt(1, inputJson.getInt("payRollId"));
            callableStatement.setDouble(2, inputJson.getDouble("grossPayPayRollDetails"));
            callableStatement.setDouble(3, inputJson.getDouble("basic"));
            callableStatement.setDouble(4, inputJson.getDouble("da"));
            callableStatement.setDouble(5, inputJson.getDouble("hra"));
            callableStatement.setDouble(6, inputJson.getDouble("ta"));
            callableStatement.setDouble(7, inputJson.getDouble("ra"));
            callableStatement.setDouble(8, inputJson.getDouble("life"));
            callableStatement.setDouble(9, inputJson.getDouble("health"));
            callableStatement.setDouble(10, inputJson.getDouble("attendanceAllow"));
            callableStatement.setDouble(11, inputJson.getDouble("entertainment"));
            callableStatement.setDouble(12, inputJson.getDouble("kidsEducation"));
            callableStatement.setDouble(13, inputJson.getDouble("vehicleAllowance"));
            callableStatement.setDouble(14, inputJson.getDouble("longTermBonus"));
            callableStatement.setDouble(15, inputJson.getDouble("employerPf"));
            callableStatement.setDouble(16, inputJson.getDouble("employeePfPayRollDetails"));


            callableStatement.setDouble(17, inputJson.getDouble("cca"));
            callableStatement.setDouble(18, inputJson.getDouble("projectPay"));
            callableStatement.setDouble(19, inputJson.getDouble("splAllowance"));
            callableStatement.setDouble(20, inputJson.getDouble("miscPay"));
            callableStatement.setDouble(21, inputJson.getDouble("variablePay"));
            callableStatement.setDouble(22, inputJson.getDouble("dedProfessionalTax"));
            callableStatement.setDouble(23, inputJson.getDouble("dedOthers"));
            callableStatement.setDouble(24, inputJson.getDouble("employeresi"));
            callableStatement.setDouble(25, inputJson.getDouble("employeeesi"));


            callableStatement.setInt(26, inputJson.getInt("daysInMonth"));
            callableStatement.setInt(27, inputJson.getInt("leavesApplied"));
            callableStatement.setInt(28, inputJson.getInt("daysHolidays"));
            callableStatement.setInt(29, inputJson.getInt("daysVacation"));
            callableStatement.setInt(30, inputJson.getInt("vactionsAvailable"));
            callableStatement.setInt(31, inputJson.getInt("daysWeekends"));
            callableStatement.setInt(32, inputJson.getInt("daysWorked"));
            callableStatement.setDate(33, DateUtility.getInstance().getMysqlDate(inputJson.getString("payPeriodStartDate")));
callableStatement.setDouble(34, inputJson.getDouble("bonusCommission"));
callableStatement.setDouble(35, inputJson.getDouble("otherAdditions"));
            callableStatement.registerOutParameter(36, Types.VARCHAR);

            insertedRows = callableStatement.executeUpdate();

            resultMessage = callableStatement.getString(36);
            // System.out.println("resultMessage---"+resultMessage);
            String resultData[] = resultMessage.split(Pattern.quote("#^$"));
//                    for(int i=0;i<resultData.length;i++){
//                        System.out.println("resultData["+i+"]=="+resultData[i]);
//                    }

            taxJson = new JSONObject();
            taxJson.put("earnedBasic", resultData[0]);
            taxJson.put("earnedDa", resultData[1]);
            taxJson.put("earnedHra", resultData[2]);
            taxJson.put("earnedTa", resultData[3]);
            taxJson.put("earnedRa", resultData[4]);
            taxJson.put("earnedLife", resultData[5]);
            taxJson.put("earnedHealth", resultData[6]);
            taxJson.put("earnedCCa", resultData[7]);
            taxJson.put("earnedProjectPay", resultData[8]);
            taxJson.put("earnedattallowance", resultData[9]);
            taxJson.put("earnedEntertainment", resultData[10]);
            taxJson.put("earnedKidsEducation", resultData[11]);
            taxJson.put("earnedVehicleAllowance", resultData[12]);
            taxJson.put("earnedLongTermBonus", resultData[13]);
            taxJson.put("earnedMiscPay", resultData[14]);
            taxJson.put("earnedEmployerPf", resultData[15]);
            taxJson.put("earnedsplallowance", resultData[16]);
            taxJson.put("tdsDeduction", resultData[17]);
            taxJson.put("employeePfActualDetails", resultData[18]);
            taxJson.put("grossPayActualDetails", resultData[19]);
            taxJson.put("bonusCommission", resultData[20]);
            taxJson.put("netPaidActualDetails", resultData[21]);
            taxJson.put("otherDeductions", resultData[22]);
            taxJson.put("taxableIncome", resultData[23]);
            taxJson.put("otherAdditions", resultData[24]);
            taxJson.put("earnedEmployeresi", resultData[25]);
            taxJson.put("earnedEmployeeesi", resultData[26]);
             taxJson.put("newgrossPayActualDetails", resultData[27]);
            //resultMessage = "<font style='color:red;'>Can Proceed</font>";

          //  System.out.println("taxJson---"+taxJson.toString());



        } catch (JSONException ex) {
            Logger.getLogger(PayrollAjaxHandlerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
            resultMessage = "<font style='color:red;'>Please try again later</font>";

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
        return taxJson.toString();

    }

    public String getSumOfHRA(int empId, double overlaySavingAmount, String paymentDateEmp,String financialYear) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String startDate = "";
        String endDate = "";
        double sum = 0;
        String totalAmount = "";
//        System.out.println(empId + "savingAmount--" + overlaySavingAmount);
//        Calendar cal = Calendar.getInstance(); 
//        int month = cal.get(Calendar.MONTH);
//        int year = cal.get(Calendar.YEAR);
//        System.out.println(month+"...........month");


        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(DateUtility.getInstance().convertStringToMySql(DateUtility.getInstance().convertToviewFormat(paymentDateEmp)));
        int payMonth = cal1.get(Calendar.MONTH) + 1;
        int payYear = cal1.get(Calendar.YEAR);
//        System.out.println(payMonth + "...........payMonth");

        if (payMonth >= 4) {
            startDate = payYear + "-04-" + "01";
            endDate = (payYear + 1) + "-03-" + "31";
        } else if (payMonth < 4) {
            startDate = (payYear - 1) + "-04-" + "01";
            endDate = (payYear) + "-03-" + "31";
        }

       // queryString = "SELECT case when SUM(SavingsAmount) is null then 0 else SUM(SavingsAmount) end AS Total FROM tblEmpTaxExemptionDetails WHERE ExemptionId = 18 AND STATUS = 'Approved' AND EmpId =" + empId + " AND AppliedDate BETWEEN '" + startDate + "' AND '" + endDate + "'";
      //  queryString = "SELECT case when SUM(SavingsAmount) is null then 0 else SUM(SavingsAmount) end AS Total FROM tblEmpTaxExemptionDetails WHERE ExemptionId = 18 AND STATUS = 'Approved' AND EmpId =" + empId + " AND FinancialYear = '" + financialYear + "'";
      queryString = "SELECT case when SUM(SavingsAmount) is null then 0 else SUM(SavingsAmount) end AS Total FROM tblEmpTaxExemptionDetails WHERE ExemptionId = 18 AND STATUS = 'Approved' AND IsActive='Active'  AND EmpId =" + empId + " AND FinancialYear = '" + financialYear + "'";
//        System.out.println("qery--" + queryString);

        try {
            connection = ConnectionProvider.getInstance().getConnection();


            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                sum = resultSet.getDouble("Total");
//                System.out.println("sum--" + sum);

            }
            totalAmount = new Double(sum + overlaySavingAmount).toString();

//            System.out.println("totalAmount--" + totalAmount);

        } catch (SQLException se) {
            se.printStackTrace();
            throw new ServiceLocatorException(se);
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
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
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new ServiceLocatorException(ex);
            }
        }
        return totalAmount; // returning the object.
    }

    public String getRevisedSalDetails(String year, int month) {

        Connection connection = null;
        PreparedStatement preStmt = null;
        String queryString = "";
        String result = "";
        ResultSet resultSet = null;
        int preYear = Integer.parseInt(year);
        int preMonth = (month - 1);
        int sno = 0;
        try {
        //    System.out.println("year-->" + year);
         //   System.out.println("month-->" + month);
            if (month == 1) {
                preMonth = 12;
                preYear = (Integer.parseInt(year) - 1);
            }
            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
            query = "SELECT cur.Payroll_Id AS payrollId,CONCAT(FName,'.',LName) AS EmpName,cur.TotalCost AS curTotalCost,pre.TotalCost AS preTotalCost,cur.TotalCost-pre.TotalCost AS Difference FROM tblEmpWages cur LEFT JOIN tblEmpWages pre ON pre.Payroll_Id = cur.Payroll_Id JOIN tblEmployee ON (cur.PayRoll_Id = tblEmployee.EmpNo) WHERE MONTH(cur.PayPeriodStartDate) =" + month + " AND YEAR(cur.PayPeriodStartDate) =" + (Integer.parseInt(year)) + " AND YEAR(pre.PayPeriodStartDate) =" + preYear + " AND MONTH(pre.PayPeriodStartDate) =" + preMonth + " AND cur.TotalCost != pre.TotalCost ORDER BY cur.Payroll_Id";
            preStmt = connection.prepareStatement(query);
        //    System.out.println("query" + query);
            resultSet = preStmt.executeQuery();
            while (resultSet.next()) {

                float difference = resultSet.getFloat("Difference");
                if (difference > 0) {
                    sno++;
                    int payrollId = resultSet.getInt("payrollId");
                    String empName = resultSet.getString("EmpName");
                    int curTotalCost = resultSet.getInt("curTotalCost");
                    int prevTotalCost = resultSet.getInt("preTotalCost");
                    result = result + sno + "#^$" + payrollId + "#^$" + empName + "#^$" + curTotalCost + "#^$" + prevTotalCost + "#^$" + difference + "*@!";
               //     System.out.println("result" + result);
                }

            }


        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        //  System.out.println("sno-->" + sno);
        return result;
    }

    public String getTdsCalculation(String year, int month) {

        Connection connection = null;
        PreparedStatement preStmt = null;
        String queryString = "";
        String result = "";
        ResultSet resultSet = null;

        int sno = 0;
        try {
         //   System.out.println("year-->" + year);
         //   System.out.println("month-->" + month);

            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
            query = "SELECT Payroll_Id,CONCAT(FName,'.',LName) AS EmpName,TaxableIncome,TDS FROM tblEmpWages LEFT JOIN tblEmployee ON (tblEmployee.EmpNo = tblEmpWages.Payroll_Id) WHERE MONTH(PayrollDate) = " + month + " AND YEAR(PayrollDate) =" + year + " AND TDS > 0 ORDER BY TDS DESC";
            preStmt = connection.prepareStatement(query);
         //   System.out.println("query" + query);
            resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                sno++;
                int payrollId = resultSet.getInt("Payroll_Id");
                String empName = resultSet.getString("EmpName");
                int taxableIncome = resultSet.getInt("TaxableIncome");
                int tds = resultSet.getInt("TDS");
                result = result + sno + "#^$" + payrollId + "#^$" + empName + "#^$" + taxableIncome + "#^$" + tds + "*@!";
           //     System.out.println("result" + result);


            }


        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        //  System.out.println("sno-->" + sno);
        return result;
    }
    
    
    public String doGetEmployeeNames(String query) throws ServiceLocatorException {
        boolean isGetting = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuffer sb = new StringBuffer();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            int count = 0;
            sb.append("<xml version=\"1.0\">");
            sb.append("<EMPLOYEES>");
            while (resultSet.next()) {
                sb.append("<EMPLOYEE><VALID>true</VALID>");

                if (resultSet.getString(1) == null || resultSet.getString(1).equals("")) {
                    sb.append("<NAME>NoRecord</NAME>");
                } else {
                    String title = resultSet.getString(1);
                    if (title.contains("&")) {
                        title = title.replace("&", "&amp;");
                    }
                    sb.append("<NAME>" + title + "</NAME>");
                }
                //sb.append("<NAME>" +resultSet.getString(1) + "</NAME>");
                sb.append("<EMPLOGINID>" + resultSet.getString(2) + "</EMPLOGINID>");
                sb.append("</EMPLOYEE>");
                isGetting = true;
                count++;
            }

            if (!isGetting) {
                //sb.append("<EMPLOYEES>" + sb.toString() + "</EMPLOYEES>");
                //} else {
                isGetting = false;
                //nothing to show
                //  response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                sb.append("<EMPLOYEE><VALID>false</VALID></EMPLOYEE>");
            }
            sb.append("</EMPLOYEES>");
            sb.append("</xml>");
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
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
            } catch (SQLException sql) {
                //System.err.print("Error :"+sql);
            }

        }
        // System.out.println(sb.toString());
        return sb.toString();
    }

public String deleteTefEmpDetails(int id) {

        Connection connection = null;
        PreparedStatement preStmt = null;
        String queryString = "";
        String result = "";
        int count = 0;

        int sno = 0;
        try {
            //   System.out.println("year-->" + year);
            //   System.out.println("month-->" + month);

            connection = ConnectionProvider.getInstance().getConnection();
            String query = null;
            query = "UPDATE tblEmpTaxExemptionDetails set IsActive='InActive' where Id=" + id;
            preStmt = connection.prepareStatement(query);
            //   System.out.println("query" + query);
            count = preStmt.executeUpdate();
            if (count > 0) {
                result = "success";
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {

                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        //  System.out.println("sno-->" + sno);
        return result;
    }
    

    @Override
    public String getEmpSavingsValidate(Double empSavings, int tefId, double taxableIncome , int categoryId, int exemptionId,String empId) {
        Connection connection = null;
        PreparedStatement preStmt = null;
        String queryString = "";
        ResultSet resultSet = null;
        String amount = "";
     //   String result = "";

        int sno = 0;
        try {
            //   System.out.println("year-->" + year);
            //   System.out.println("month-->" + month);

            connection = ConnectionProvider.getInstance().getConnection();
            
             int month = Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String sdate = "";
        String edate = "";
        String financialYear="";
        if (month >= 3) {
            sdate = year + "-04-01";
            edate = (year + 1) + "-03-31";
             financialYear="April" + year + "-March" + (year + 1);
             
        } else {
            sdate = (year - 1) + "-04-01";
            edate = year + "-03-31";
              financialYear="April" + (year - 1) + "-March" + (year);
        }

            
            String query = null;
            query = "SELECT SUM(ApprovedAmount) AS ApprovedAmount FROM tblEmpTaxExemptionDetails LEFT JOIN tblLKTaxExemptions ON (tblLKTaxExemptions.Id = tblEmpTaxExemptionDetails.ExemptionId) WHERE Category = "+categoryId+" AND EmpId = "+empId+" AND tblEmpTaxExemptionDetails.STATUS = 'Approved' AND IsActive = 'Active' AND FinancialYear='" + financialYear + "'";

if(!"".equals(tefId)){
    query = query + "AND tblEmpTaxExemptionDetails.Id != "+tefId;
}
if(categoryId == 3 ){
     query = query + "AND tblEmpTaxExemptionDetails.ExemptionId = "+exemptionId;
}

            preStmt = connection.prepareStatement(query);
       // System.out.println("query" + query);
            resultSet = preStmt.executeQuery();
            while(resultSet.next()){
               amount =  resultSet.getString("ApprovedAmount");
            }
         //   System.out.println("get amount..."+amount);
            if(amount == null || "".equals(amount)){
                amount = "0";
            }
// result = "the amount is"+amount;

        } catch(Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception-->"+ex.getMessage());
            //e.printStackTrace();
        } finally {

            try {

                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        //  System.out.println("sno-->" + sno);
        return amount;
    }

    
}
