 <%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<html>
    <head>
        <title>Hubble Organization Portal :: Updating Employee Details</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpDetailsValidation.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeLocation.js"/>"></script>
<%--  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollajaxscript.js?version=2.8"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollclientvalidations.js?version=1.1"/>"></script>  --%>
       <s:include value="PayrollCalculations.jsp"/>
    <script type="text/JavaScript">
        
        
          
       
    </script>
    <s:include value="/includes/template/headerScript.html"/>
</head>
<%-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="validateCtc();getLocation();"> --%>
<%-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="ifFreezedShowUpdate();changeRepayComments();changeDorepayment();"> --%>
<body  class="bodyGeneral" oncontextmenu="return false;">

    <!--//START MAIN TABLE : Table for template Structure-->
    <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

        <!--//START HEADER : Record for Header Background and Mirage Logo-->
        <tr class="headerBg">
            <td valign="top">
                <s:include value="/includes/template/Header.jsp"/>
            </td>
        </tr>
        <!--//END HEADER : Record for Header Background and Mirage Logo-->

        <!--//START DATA RECORD : Record for LeftMenu and Screen Content-->
        <tr>
            <td>
                <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
                    
                    <tr>

                        <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                        <td width="150px;" class="leftMenuBgColor" valign="top">
                            <s:include value="/includes/template/LeftMenu.jsp"/>
                        </td>
                        <!--//START DATA COLUMN : Coloumn for LeftMenu-->

                        <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            
                        <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px 5px 5px;">

      <ul id="accountTabs1" class="shadetabs" >
                                <li align="center"><a href="#" class="selected" rel="employeeWagesEdit"  >Wage Edit</a></li>
      </ul>
                               <div id="employeeWagesEdit" class="tabcontent"  style="border:1px solid gray; width:850px;height: 610px; overflow:hidden; margin-bottom: 1em;">
                                   <br>
                            <ul id="accountTabs" class="shadetabs" >
                                <li ><a href="#" class="selected" rel="employeeDetails" tabindex="1" >Employee Details</a></li>

                                <li ><a href="#" rel="payrollDetails" tabindex="25">Payroll Details</a></li>
                                <li ><a href="#" rel="actualDetailsTab" tabindex="47" >Actual Details</a></li>
                                <li ><a href="#" rel="yearAndDateTab" tabindex="80">Year and Date</a></li>
                           <%--     <li ><a href="#" rel="tdsTab" tabindex="103">Tax Assumptions</a></li> --%>
                            </ul>

                            <div  style="border:1px solid gray; width:850px;height: 510px; overflow:auto; margin-bottom: 1em;">
                                <s:hidden name="payRollId" id="payRollId"  value="%{payRollId}" />
                                <s:hidden name="wageId" id="wageId" value="%{wageId}" />
                                  <s:hidden name="tdsId" id="tdsId"  value="%{tdsId}" />
                                   <s:hidden name="empId" id="empId"  value="%{empId}" />
                                <!-- employee Details end -->
                                <br>
                                  <div id="wagesResultMessage" style="font-size: 15px;"></div>
                                            <div id="loadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                            <div id="loadingMessageForFreeze" style="color: red;font-size: 15px;display: none;"></div>
                                 <%-- <table cellpadding="1" cellspacing="1" border="0" width="100%">

                                  <tr> 
                                        
                      
                  
                                        <td>
                                            <div id="wagesResultMessage" style="font-size: 15px;"></div>
                                            <div id="loadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                            <div id="loadingMessageForFreeze" style="color: red;font-size: 15px;display: none;"></div>
                                        </td>
                                        <td  align="right">
                                            <table>
                                                <tr>

                                                    <td>
                                                        <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                                    </td>  

                                                    <td>

                                                        <div id="updateAllForEditEmpWages" style="">  <input type="button" class="buttonBg" value="Update All" onclick="updateAllEmpWageDetails();"/></div>

                                                    </td>

                                                </tr>
                                            </table>
                                        </td>
                                    </tr> 
                                </table>--%>
                                <!-- Employee details tab -->
                                <div id="employeeDetails" class="tabcontent" >
                                    <s:form action="" name="employeeWageDetails" theme="simple" id="employeeWageDetails">

 <div align="right" style=" border-radius: 25px;
                                                         border: 2px solid #3e93d4;
                                                          padding: 20px;
                                                         width: 85%;
                                                         height: 100%;
                                                        
                                                        position: relative;background-color:#c5def2; ">

                                        <table cellpadding="1" cellspacing="1" border="0" width="100%" align="left" bgcolor="">
                                              <tr> 
                                        
                      <td colspan="4" align="right">
                           <div style="margin-right: 1%;">
                          <table>
                              <tr>
                                        <td colspan="1">
                                           
                                        </td>
                                        <td  align="right" colspan="2">
                                           
                                            <INPUT type="button" CLASS="btnPayrollWage" value="Back to List" onClick="history.go(-1)">
                                        </td>
                                       <td>
                                            <input type="button" class="btnPayrollWage" value="Update All" onclick="updateAllEmpWageDetails();" id="updateAllForEditEmpWages1" tabindex="24"/>
                                        </td>
                                        <td>
                                            <input type="button" class="btnPayrollWage" value="ReCalculateWage" onclick="CalculateActualDetails();" id="updateAllForEditEmpWages1" tabindex="24"/>
                                        </td>
                                           <td>
                                            <input type="button" class="btnPayrollWage" value="Cancel" onclick="window.location.href=window.location.href"  tabindex="24"/>
    
                                        </td>
                                    </tr> 
                          </table>
                           </div>
                      </td>
                                              </tr>
                                            <tr>
                                                <td class="fieldLabelWages" align="right">Employee&nbsp;Name:</td>    
                                                <td><s:textfield name="employeeName" id="employeeName" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{employeeName}" onchange="payRollFieldLengthValidator(this);"  tabindex="" readonly="true"/></td>
                                                <td class="fieldLabelWages" align="right">Title&nbsp;:</td>    
                                                <td><s:textfield name="titleId" id="titleId" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{titleId}"  readonly="true"/></td>
                                            </tr>  
                                            <tr>
                                                <td class="fieldLabelWages"  align="right" >Classification&nbsp;:</td>
                                                <td ><s:select 
                                                        name="classificationId" 
                                                        id="classificationId"
                                                        headerKey="All"
                                                        headerValue="All"
                                                        list="classificationList" cssClass="inputTextReadOnlyColoredTextBoxes" tabindex="" contenteditable="true"/></td>
                                                <td class="fieldLabelWages"  align="right">Payment&nbsp;Type:</td>    
                                                <td><s:select 
                                                        name="paymentType" 
                                                        id="paymentType"
                                                        headerKey="All"
                                                        headerValue="All"
                                                        list="paymentTypeList" cssClass="inputTextReadOnlyColoredTextBoxes" tabindex="" /></td>
                                            </tr>
                                            <tr>
                                                <td class="fieldLabelWages"  align="right">PayPeriodStartDate:</td>

                                                <td><s:textfield name="payPeriodStartDate" id="payPeriodStartDate" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{payPeriodStartDate}" onchange="isValidDate(this)" tabindex="2"/><a href="javascript:cal1.popup();" readonly="true" >
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20" border="0"></td>
                                                        <td class="fieldLabelWages" align="right">Bank&nbsp;Name:</td>    
                                                        <td><s:select 
                                                                name="bankName" 
                                                                id="bankName"
                                                                headerKey="All"
                                                                headerValue="All"
                                                                list="bankNameList" cssClass="inputTextReadOnlyColoredTextBoxes" tabindex="" contenteditable="true"/></td>
                                            </tr>
                                            <tr>
                                               <%-- <td class="fieldLabelWages" align="right">NetPaid:</td>     --%>
                                                <td><s:hidden name="netPaid" id="netPaid" cssClass="inputTextBlueWagesEarnedGrossandNetPaid" value="%{netPaid}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="3"/></td>
                                               
                                                <td class="fieldLabelWages" align="right" colspan="2">Bank&nbsp;Account&nbsp;No:</td>    
                                                <td><s:textfield name="bankAccountNo" id="bankAccountNo" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{bankAccountNo}" onchange="payRollFieldLengthValidator(this);" onkeypress="" tabindex="" readonly="true"/></td>
                                            </tr>  
                                            <tr>
                                                 <%--  <td class="fieldLabelWages" align="right">GrossPay:</td>  --%>   
                                                <td><s:hidden name="grossPay" id="grossPay" cssClass="inputTextBlueWagesEarnedGrossandNetPaid" value="%{grossPay}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="4"/></td>
                                                
                                                <%--  <td class="fieldLabel"  align="right" >Freeze&nbsp;Payroll:</td>
                                                  <td > <s:checkbox name="freezePayroll" id="freezePayroll"
                                                              value="%{freezePayroll}" 
                                                              theme="simple" tabindex="11" /> </td> --%>

                                            </tr>
                                            <tr>
                                                <td class="fieldLabelWages"  align="right">PayrollDate:</td>

                                                <td colspan="3"><s:textfield name="payrollDate" id="payrollDate" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{payrollDate}" onchange="isValidDate(this)" tabindex="5"/><a href="javascript:cal2.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20" border="0"></td>
                                                    <%--    <td class="fieldLabelWages" align="right">PayRunId:</td>    
                                                        <td></td> --%>
                                                    <s:hidden name="payRunId" id="payRunId" cssClass="inputTextBlueWages" value="%{payRunId}" onchange="" tabindex="13"/>
                                            </tr>   
                                            <%--<tr>
                                                <td class="fieldLabelWages"  align="right">Actual&nbsp;TDS:</td>

                                                <td colspan="3"><s:textfield name="actualTds" id="actualTds" cssClass="inputTextBlueWages" value="%{actualTds}" onchange="" tabindex="" readOnly="true"/></td>
                                                        
                                            </tr>   
                                            <tr>
                                                <td class="fieldLabelWages"  align="right">Tax&nbsp;Exemption:</td>

                                                <td colspan="3"><s:textfield name="incomeTax_TE" id="incomeTax_TE" cssClass="inputTextBlueWages" value="%{incomeTax_TE}" onchange="" tabindex="" readOnly="true"/></td>
                                                        
                                            </tr> --%>   
                                            <tr>
                                                  <td class="fieldLabelWages" align="right">TDS:</td>    
                                                <td><s:textfield name="tds" id="tds" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{tds}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="6" readOnly="true"/></td>
                                                 
                                                <td class="fieldLabelWages" align="right">PayrollId:</td>    
                                                <td><s:textfield name="employerId" id="employerId" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{employerId}" onchange="" onkeyup="isNumberKeyWage(this);" readonly="true" tabindex="7"/></td>
                                            </tr> 
                                            <tr>
                                                <td class="fieldLabelWages" align="right">Days&nbsp;In&nbsp;Month:</td>    
                                                <td><s:textfield name="daysInMonth" id="daysInMonth" cssClass="inputTextBlueWages" value="%{daysInMonth}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="8"/></td>
                                              
                                                <td class="fieldLabelWages" align="right">Ded_Employee_Pf:</td>    
                                                <td><s:textfield name="dedEmpPf" id="dedEmpPf" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{dedEmpPf}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="9"/></td>
                                            </tr> 
                                            <tr>
                                               <td class="fieldLabelWages" align="right">Days&nbsp;Worked:</td>    
                                                <td colspan=""><s:textfield name="daysWorked" id="daysWorked" cssClass="inputTextBlueWages" value="%{daysWorked}" onchange="payRollFieldLengthValidator(this);changeEarnedValuesBasedOnDaysWorked(this);" onkeyup="isNumberKeyWage(this);" tabindex="10"/>
                                                <s:hidden name="daysWorkedHidden" id="daysWorkedHidden" cssClass="inputTextBlueWages" value="%{daysWorked}" onchange="" onkeyup="" tabindex=""/>
                                                </td>
                                                <td class="fieldLabelWages" align="right">Ded_Professional_Tax:</td>    
                                                <td><s:textfield name="dedProfessionalTax" id="dedProfessionalTax" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{dedProfessionalTax}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="11"/></td>
                                            </tr> 
                                            <tr>
                                                <td class="fieldLabelWages" align="right">Days&nbsp;Project:</td>    
                                                <td><s:textfield name="daysProject" id="daysProject" cssClass="inputTextBlueWages" value="%{daysProject}" onchange="payRollFieldLengthValidator(this);checkForSpace(this);checkWithDaysInMonth(this);" onkeyup="isNumberKeyWage(this);" tabindex="12"/>
                                                
                                                <s:hidden name="daysProjectHiddenValue" id="daysProjectHiddenValue" cssClass="inputTextBlueWages" value="%{daysProject}" onchange="" onkeyup="" tabindex=""/>
                                                </td>
                                                <td class="fieldLabelWages" align="right">Ded_Income_Tax:</td>    
                                                <td><s:textfield name="dedIncomeTax" id="dedIncomeTax" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{dedIncomeTax}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="13"/></td>
                                            </tr> 
                                            <tr>
                                                  <td class="fieldLabelWages" align="right">Vacations&nbsp;Available</td>    
                                                  <td><s:textfield name="vactionsAvailable" id="vactionsAvailable" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{vactionsAvailable}" onchange="checkForSpace(this);" readonly="true" onkeyup="isNumberKeyWage(this);" tabindex="16" />
                                               
                                                <td class="fieldLabelWages" align="right">Ded_Corporate_Loan:</td>    
                                                <td><s:textfield name="dedCorporateLoan" id="dedCorporateLoan" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{dedCorporateLoan}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="14"/></td>
                                            </tr> 
                                            <tr>
                                               <td class="fieldLabelWages" align="right">Days&nbsp;Holidays:</td>    
                                                <td><s:textfield name="daysHolidays" id="daysHolidays" cssClass="inputTextBlueWages"  value="%{daysHolidays}" onchange="payRollFieldLengthValidator(this);checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="18"/></td>
                                                
                                                <s:hidden name="vactionsAvailableHidden" id="vactionsAvailableHidden" cssClass="inputTextBlueWages" value="%{vactionsAvailable}"  /></td>
                                                <td class="fieldLabelWages" align="right">Ded_Life:</td>    
                                                <td><s:textfield name="dedLife" id="dedLife" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{dedLife}" onchange="checkForSpace(this);"  onkeyup="isNumberKeyWage(this);" tabindex="17"/></td>
                                            </tr> 

                                            <tr>
                                                <td class="fieldLabelWages" align="right">Leaves&nbsp;Applied:</td>    
                                                <td><s:textfield name="leavesApplied" id="leavesApplied" cssClass="inputTextBlueWages"  value="%{leavesApplied}" />
                                                <s:hidden name="leavesAppliedHidden" id="leavesAppliedHidden" cssClass="inputTextBlueWages" value="%{leavesApplied}" onchange="changeDaysVacationValues(this);"  />
                                                </td>
                                                <td class="fieldLabelWages" align="right">Ded_Health:</td>    
                                                <td><s:textfield name="dedHealth" id="dedHealth" cssClass="inputTextBlueWages" value="%{dedHealth}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="19"/></td>
                                            </tr> 
                                            <tr>
                                                 
                                              <td class="fieldLabelWages" align="right">Days&nbsp;Vacation:</td>    
                                                <td><s:textfield name="daysVacation" id="daysVacation" cssClass="inputTextBlueWages"  value="%{daysVacation}" onchange="payRollFieldLengthValidator(this);checkForSpace(this);checkTotalDays(this);" onkeyup="isNumberKeyWage(this);" tabindex="14" />
                                                <s:hidden name="daysVacationHidden" id="daysVacationHidden" cssClass="inputTextBlueWages" value="%{daysVacation}" onchange="" onkeyup="" tabindex="" />
                                                </td>
                                                <td class="fieldLabelWages" align="right">Ded_Others:</td>    
                                                <td><s:textfield name="dedOthers" id="dedOthers" cssClass="inputTextBlueWages" value="%{dedOthers}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="20"/></td>
                                            </tr> 
                                            <tr>
                                                  <td class="fieldLabelWages" align="right">Days&nbsp;Weekends:</td>    
                                                  <td ><s:textfield name="daysWeekends" id="daysWeekends" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{daysWeekends}" onchange="payRollFieldLengthValidator(this);checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="21"/></td>
                                                  
                                                   <td class="fieldLabelWages" align="right">Bonus/Commision:</td>    
                                                <td><s:textfield name="bonusCommission" id="bonusCommission1"   cssClass="inputTextBlueWages" value="%{bonusCommission}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="74"/></td>
                                           
                                            </tr>
                                             <tr>
                                                 <td class="fieldLabelWages" align="right"></td>    
                                                <td></td>
                                                
                                               <td class="fieldLabelWages" align="right">Other&nbsp;Addtions:</td>    
                                                <td><s:textfield name="otherAdditions" id="otherAdditions1"   cssClass="inputTextBlueWages" value="%{otherAdditions}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="76"/></td>
                                            </tr>
                                            <tr>
                                                <td class="fieldLabelWages"  align="right" >Freeze&nbsp;Payroll:</td>
                                                <td > <s:checkbox name="freezePayroll" id="freezePayroll"
                                                            value="%{freezePayroll}" 
                                                            theme="simple" tabindex="22" onchange="changeDorepayment();"/> </td>
                                                <td class="fieldLabelWages"  align="right" >Do&nbsp;Repayment:</td>
                                                <td > <s:checkbox name="doRepaymentFlag" id="doRepaymentFlag"
                                                            value="%{doRepaymentFlagVal}" 
                                                            theme="simple" tabindex="23" onchange="changeRepayComments();"/> </td>


                                            </tr>
                                            <tr id ="repaymentRow" style="display: none">
                                                <td class="fieldLabelWages" align="right">RepayComments<font color="red" size="2px">*</font>:</td>  
                                                <td colspan="3"><s:textarea name="repaymentComments" id="repaymentComments" cols="75" rows="3" cssClass="inputTextArea" value="%{repaymentComments}" onchange="payRollFieldLengthValidator(this);" tabindex=""/></td>
                                            </tr>
                                        </table>
                                  </div>
                                    </s:form>
                                    <script>
                                                                                          
                                        var cal1 = new CalendarTime(document.forms['employeeWageDetails'].elements['payPeriodStartDate']);
                                        cal1.year_scroll = true;
                                        cal1.time_comp = false;
                                                                                          
                                        var cal2 = new CalendarTime(document.forms['employeeWageDetails'].elements['payrollDate']);
                                        cal2.year_scroll = true;
                                        cal2.time_comp = false;
                                                                                           
                                    </script>
                                </div>
                                <!-- Employee details tab end-->             
                                <!-- payroll Details START -->

                                <div id="payrollDetails" class="tabcontent"  >   

                                    <s:form action="" name="payrollDetails" theme="simple">
 <div align="right" style=" border-radius: 25px;
                                                         border: 2px solid #3e93d4;
                                                         padding: 20px;
                                                         width: 62%;
                                                         height: 400px;
                                                         margin-left: 10%;position: relative;background-color:#c5def2; ">
                                        <table cellpadding="1" cellspacing="1" border="0" width="70%" align="left" bgcolor="">
                                             <tr> 
                                        
                      
                  
                                        <td>
                                           
                                        </td>
                                        <td  align="right" colspan="3">
                                            <table>
                                                <tr>

                                                    <td>
                                                        <INPUT type="button" CLASS="btnPayrollWage" value="Back to List" onClick="history.go(-1)">
                                                    </td>  

                                                    <td>

                                                        <div id="updateAllForEditEmpWages2" style="">  <input type="button" class="btnPayrollWage" value="Update All" onclick="updateAllEmpWageDetails();" tabindex="46"/></div>

                                                    </td>
                                                    <td>
                                            <input type="button" class="btnPayrollWage" value="ReCalculateWage" onclick="CalculateActualDetails();" id="updateAllForEditEmpWages1" tabindex="24"/>
     
                                        </td>
                                                  <td>
                                            <input type="button" class="btnPayrollWage" value="Cancel" onclick="window.location.href=window.location.href"  tabindex="24"/>
    
                                        </td>

                                                </tr>
                                            </table>
                                        </td>
                                    </tr> 
                                            <tr>
                                                <td class="fieldLabelWages"  align="right">Employee&nbsp;Name:</td>                           
                                                <td colspan="4"><font class="navigationTextWages"><s:property value="%{employeeName}"/></font> </td>  
                                            </tr>

                                            <tr>
                                                <td class="fieldLabelWages" align="right">Basic:</td>    
                                                <td><s:textfield name="basic" id="basic" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{basic}"  onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="26"/></td>
                                                <td class="fieldLabelWages" align="right">Maid Services:</td>    
                                                <td><s:textfield name="maidServices" id="maidServices" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{maidServices}"  onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="34"/></td>
                                            </tr>
                                            <tr><td class="fieldLabelWages" align="right">DA:</td>    
                                                <td><s:textfield name="da" id="da" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{da}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" readonly="true"/></td>
                                                <td class="fieldLabelWages"  align="right">Entertainment:</td>    
                                                <td><s:textfield name="entertainment" id="entertainment" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{entertainment}"  onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="35"/></td>
                                            </tr><tr>
                                                <td class="fieldLabelWages"  align="right">HRA:</td>    
                                                <td><s:textfield name="hra" id="hra" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{hra}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" readonly="true"/></td>
                                                <td class="fieldLabelWages"  align="right">Kids&nbsp;Education:</td>    
                                                <td><s:textfield name="kidsEducation" id="kidsEducation" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{kidsEducation}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="36"/></td>
                                            </tr> <tr>
                                                <td class="fieldLabelWages"  align="right">TA:</td>    
                                                <td><s:textfield name="ta" id="ta" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{ta}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="27"/></td> 
                                                <td class="fieldLabelWages"  align="right">Vehicle&nbsp;Allowance:</td>    
                                                <td><s:textfield name="vehicleAllowance" id="vehicleAllowance" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{vehicleAllowance}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="37"/></td>
                                            </tr>
                                            <tr>
                                                <td class="fieldLabelWages"  align="right">RA:</td>    
                                                <td><s:textfield name="ra" id="ra" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{ra}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="28"/></td>
                                                <td class="fieldLabelWages"  align="right">LongTermBonus:</td>    
                                                <td><s:textfield name="longTermBonus" id="longTermBonus" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true"  value="%{longTermBonus}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="38"/></td>
                                            </tr>
                                            <tr>
                                                <td class="fieldLabelWages"  align="right">Life:</td>    
                                                <td><s:textfield name="life" id="life" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{life}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="29"/></td>
                                                <td class="fieldLabelWages"  align="right">Employer PF:</td>    
                                                <td><s:textfield name="employerPf" id="employerPf" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{employerPf}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="39"/></td>    
                                            </tr>
                                            <tr>
                                                <td class="fieldLabelWages"  align="right">Health:</td>    
                                                <td><s:textfield name="health" id="health" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{health}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="30"/></td>
                                                <td class="fieldLabelWages" align="right">Spl.&nbsp;Allowance:</td>    
                                                <td><s:textfield name="splAllowance" id="splAllowance" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{splAllowance}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="40" /></td>
                                            <tr>
                                                <td class="fieldLabelWages"  align="right"></td>   
                                                <td><s:textfield name="health1" id="health1" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{health}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="31"/></td>

                                                <td class="fieldLabelWages"  align="right">CCA:</td>    
                                                <td><s:textfield name="cca" id="cca" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{cca}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="41"/></td>
                                            </tr>
                                            <tr>

                                                <td class="fieldLabelWages"  align="right">Attendance Allow:</td>    
                                                <td><s:textfield name="attendanceAllow" id="attendanceAllow" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{attendanceAllow}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="32"/></td>
                                                <td class="fieldLabelWages"  align="right">Misc&nbsp;Pay:</td>    
                                                <td><s:textfield name="miscPay" id="miscPay" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{miscPay}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="42"/></td>

                                            </tr>

                                            <tr>

                                                <td class="fieldLabelWages"  align="right">Project Pay:</td>    
                                                <td><s:textfield name="projectPay" id="projectPay" cssClass="inputTextBlueWagesColoredTextBoxes" value="%{projectPay}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="33"/></td>
                                                <td class="fieldLabelWages"  align="right">Employee&nbsp;PF:</td>    
                                                <td><s:textfield name="employeePfPayRollDetails" id="employeePfPayRollDetails" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{employeePfPayRollDetails}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="43"/></td>
                                            </tr>

                                            <tr>
                                                <td class="fieldLabelWages"  align="right" >Classification&nbsp;:</td>
                                                <td><s:select 
                                                        name="classificationId" 
                                                        id="classificationId"
                                                        headerKey="All"
                                                        headerValue="All"
                                                        list="classificationList" cssClass="inputTextReadOnlyColoredTextBoxes"  tabindex="" contenteditable="true"/></td>

                                                <td class="fieldLabelWages"  align="right">Gross&nbsp;Pay:</td>    
                                                <td><s:textfield name="grossPayPayRollDetails" id="grossPayPayRollDetails" cssClass="inputTextBlueWagesColoredTextBoxes" value="%{grossPay}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="44"/></td>
                                            </tr>
                                            <tr>
                                               <td class="fieldLabelWages" align="right">Employer&nbsp;ESI&nbsp;:</td>    
                                                                    <td><s:textfield name="employeresi" id="employeresi" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{employeresi}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="58"  /></td>
                                                                     <td class="fieldLabelWages" align="right">Employee&nbsp;ESI&nbsp;:</td>    
                                                                    <td><s:textfield name="employeeesi" id="employeeesi" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{employeeesi}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="59" /></td>
                                            </tr>
                                            <tr>
                                                
                                                <td colspan="3" class="fieldLabelWages"  align="right">Variable&nbsp;Pay:</td>                                            
                                                <td colspan="1"><s:textfield name="variablePay" id="variablePay" cssClass="inputTextReadOnlyColoredTextBoxes" readonly="true" value="%{variablePay}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="45"/></td>
                                            </tr>

                                        </table>
 </div>
                                    </s:form>
                                </div>

                                <!-- payroll Details END -->
                                <!-- Actual Details Tab -->
                                <div id="actualDetailsTab" class="tabcontent" > 
                                    <s:form action="" name="employeeActualWageDetails" theme="simple">
                                         <div align="right" style=" border-radius: 25px;
                                                         border: 2px solid #3e93d4;
                                                         padding: 20px;
                                                         width: 630px;
                                                         height: 550px;
                                                         margin-left: 10%;position: relative;background-color:#c5def2; ">
                                        <table bgcolor="">
                                             <tr> 
                                        
                      
                  
                                        <td>
                                          
                                        </td>
                                        <td  align="right" colspan="3">
                                             <div style="margin-right: 10%;">
                                            <table>
                                                <tr>

                                                    <td>
                                                        <INPUT type="button" CLASS="btnPayrollWage" value="Back to List" onClick="history.go(-1)">
                                                    </td>  

                                                    <td>

                                                        <div id="updateAllForEditEmpWages3" style="">  <input type="button" class="btnPayrollWage" value="Update All" onclick="updateAllEmpWageDetails();" tabindex="79"/></div>

                                                    </td>
                                                    <td colspan="" align="right">
                                                    <input type="button" class="btnPayrollWage" value="Refresh" onclick="computeProjectPay();" tabindex="78"/>
                                                </td>
     <td>
                                            <input type="button" class="btnPayrollWage" value="Cancel" onclick="window.location.href=window.location.href"  tabindex="24"/>
    
                                        </td>
                                                </tr>
                                            </table>
                                             </div>
                                        </td>
                                    </tr> 
                                            <tr>
                                                <td class="fieldLabelWages"  align="right">Employee&nbsp;Name:</td>                           

                                                <td colspan="4"><font class="navigationTextWages"><s:property value="%{employeeName}"/></font> </td>  
                                               


                                            </tr>

                                            <tr>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;Basic:</td>    
                                                <td><s:textfield name="earnedBasic" id="earnedBasic" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedBasic}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="48"/></td>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;Food:</td>    
                                                <td><s:textfield name="earnedFood" id="earnedFood" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedFood}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="63"/></td>
                                            </tr>

                                            <tr>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;Da:</td>    
                                                <td><s:textfield name="earnedDa" id="earnedDa" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedDa}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="49"/></td>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;Laundary:</td>    
                                                <td><s:textfield name="earnedLaundary" id="earnedLaundary" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedLaundary}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="64"/></td>
                                            </tr>


                                            <tr>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;Hra:</td>    
                                                <td><s:textfield name="earnedHra" id="earnedHra" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedHra}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="50"/></td>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;MaidServices:</td>    
                                                <td><s:textfield name="earnedMaidServices" readonly="true" id="earnedMaidServices" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedMaidServices}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);"  tabindex="65"/></td>
                                            </tr>


                                            <tr>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;Ta:</td>    
                                                <td><s:textfield name="earnedTa" id="earnedTa" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedTa}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="51"/></td>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;Entertainment:</td>    
                                                <td><s:textfield name="earnedEntertainment" id="earnedEntertainment" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedEntertainment}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="66"/></td>
                                            </tr>

                                            <tr>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;Ra:</td>    
                                                <td><s:textfield name="earnedRa" id="earnedRa" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedRa}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="52"/></td>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;KidsEducation:</td>    
                                                <td><s:textfield name="earnedKidsEducation" id="earnedKidsEducation" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedKidsEducation}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="67"/></td>
                                            </tr>


                                            <tr>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;Life:</td>    
                                                <td><s:textfield name="earnedLife" id="earnedLife" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedLife}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="53"/></td>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;VehicleAllowance:</td>    
                                                <td><s:textfield name="earnedVehicleAllowance" id="earnedVehicleAllowance" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedVehicleAllowance}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="68"/></td>
                                            </tr>

                                            <tr>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;Health:</td>    
                                                <td><s:textfield name="earnedHealth" id="earnedHealth" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedHealth}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="54"/></td>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;LongTermBonus:</td>    
                                                <td><s:textfield name="earnedLongTermBonus" id="earnedLongTermBonus" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedLongTermBonus}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="69"/></td>
                                            </tr>


                                            <tr>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;CCa:</td>    
                                                <td><s:textfield name="earnedCCa" id="earnedCCa" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedCCa}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="55"/></td>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;MiscPay:</td>    
                                                <td><s:textfield name="earnedMiscPay" id="earnedMiscPay" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedMiscPay}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="70"/></td>
                                            </tr>


                                            <tr>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;ProjectPay:</td>    
                                                <td><s:textfield name="earnedProjectPay" id="earnedProjectPay" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedProjectPay}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="56"/>
                                                <s:hidden name="earnedProjectPayHidden" id="earnedProjectPayHidden"   value="%{earnedProjectPay}" />
                                                </td>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;Employer&nbsp;Pf:</td>    
                                                <td><s:textfield name="earnedEmployerPf" id="earnedEmployerPf" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedEmployerPf}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="71"/></td>
                                            </tr>

                                            <tr>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;attallowance:</td>    
                                                <td><s:textfield name="earnedattallowance" id="earnedattallowance" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedattallowance}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="57"/></td>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;splallowance:</td>    
                                                <td><s:textfield name="earnedsplallowance" id="earnedsplallowance" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedsplallowance}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="72"/></td>
                                            </tr>


                                            <tr>
                                                <td class="fieldLabelWages" align="right">TDS&nbsp;Deduction:</td>    
                                                <td><s:textfield name="tdsDeduction" id="tdsDeduction"  readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{tdsDeduction}" onchange="checkForSpace(this);calculateNetPaid(this.value);" onkeyup="isNumberKeyWage(this);" tabindex="58"/>
                                                <s:hidden name="tdsDeduction" id="tdsDeductionHidden" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{tdsDeduction}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="58"/>
                                                </td>
                                                <td class="fieldLabelWages" align="right">Employee&nbsp;Pf:</td>    
                                                <td><s:textfield name="employeePfActualDetails" id="employeePfActualDetails" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{employeePfActualDetails}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="73"/></td>
                                            </tr>

                                            <tr>
                                                <td class="fieldLabelWages" align="right">Base&nbsp;Pay:</td>    
                                                <td><s:textfield name="grossPayActualDetails" id="grossPayActualDetails" readonly="true" cssClass="inputTextBlueWagesColoredTextBoxes" value="%{earnedGrossPay}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="59"/></td>
                                                <td class="fieldLabelWages" align="right">Bonus/Commision:</td>    
                                                <td><s:textfield name="bonusCommission" id="bonusCommission"  readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{bonusCommission}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="74"/></td>
                                            </tr>


                                            <tr>
                                                <td class="fieldLabelWages" align="right">Gross&nbsp;Pay:</td>    
                                                <td><s:textfield name="newgrossPayActualDetails" id="newgrossPayActualDetails" readonly="true" cssClass="inputTextBlueWagesColoredTextBoxes"  value="%{earnedNewGrossPay}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="59"/></td>
                                               
                                               <td class="fieldLabelWages" align="right">Other&nbsp;Deductions:</td>    
                                                <td><s:textfield name="otherDeductions" id="otherDeductions"  readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{otherDeductions}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="75"/></td>
                                            </tr>

                                            <tr>
                                                 <td class="fieldLabelWages" align="right">Net&nbsp;Paid:</td>    
                                                <td><s:textfield name="netPaidActualDetails" id="netPaidActualDetails"  readonly="true" cssClass="inputTextBlueWagesColoredTextBoxes" value="%{earnedNetPaid}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="60"/></td>
                                                
                                               <td class="fieldLabelWages" align="right">Other&nbsp;Addtions:</td>    
                                                <td><s:textfield name="otherAdditions" id="otherAdditions"  readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{otherAdditions}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="76"/></td>
                                            </tr>
                                            <tr>
                                                 <td class="fieldLabelWages" align="right">Taxable&nbsp;Income:</td>    
                                                <td><s:textfield name="taxableIncome" id="taxableIncome"  readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{taxableIncome}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="61"/></td>
                                                
                                               <td class="fieldLabelWages" align="right">Employee&nbsp;ESI&nbsp;:</td>    
                                               <td><s:textfield name="earnedEmployeeesi" id="earnedEmployeeesi" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedEmployeeesi}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="59" /></td>
                                            </tr>
                                            <tr>
                                                <td class="fieldLabelWages" align="right">Earned&nbsp;Employer&nbsp;ESI&nbsp;:</td>    
                                               <td><s:textfield name="earnedEmployeresi" id="earnedEmployeresi" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{earnedEmployeresi}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="58"  /></td>
                                               
                                                
                                                <td class="fieldLabelWages"  align="right">Project&nbsp;End&nbsp;Date&nbsp;:</td>

                                                <td width="30%"><s:textfield name="projectEndDate" id="projectEndDate" cssClass="inputTextBlueWages" value="%{projectEndDate}" onchange="isValidDate(this)" tabindex="77"/><a href="javascript:cal3.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20" border="0"></td> 
                                                        </tr>
                                                         <tr>
                                                            <td class="fieldLabelWages"  align="right"  >Hold&nbsp;Payroll:</td>
                                                <td colspan=""> <s:checkbox name="isBlock" id="isBlock"
                                                            value="%{isBlock}" 
                                                            theme="simple" tabindex="62" onchange="getReleaseDate();" /> </td>
                                               
                                                 <td style="display: none;" class="fieldLabelWages" id="releaseDateName">Release&nbsp;Date&nbsp;:</td>

                                                 <td  colspan="2" style="display: none;margin-left: 101%; margin-top: -11%;" id="releaseDateCol"><s:textfield name="releasedDate"   id="releasedDate" cssClass="inputTextBlueWages"  value="%{releasedDate}" onchange="isValidDate(this)" tabindex="77"/><a href="javascript:cal4.popup();">
                                                        <img id="dateImg"  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20"  border="0"></td> 
                                                      
                                                        </tr>
                                                        <tr id ="repaymentGrossAndNetRow" style="display: none">
                                                            <td class="fieldLabelWages" align="right">Repayment&nbsp;Gross:</td>    
                                                            <td><s:textfield name="repaymentGross" id="repaymentGross" cssClass="inputTextBlueWages" value="%{repaymentGross}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="79"/></td>
                                                            <td class="fieldLabelWages" align="right">Repayment&nbsp;Net:</td>    
                                                            <td><s:textfield name="repaymentNet" id="repaymentNet" cssClass="inputTextBlueWages" value="%{repaymentNet}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="80"/></td>
                                                        </tr>
                                                        <tr id ="repaymentVariableRow" style="display: none">
                                                            <td class="fieldLabelWages" align="right">Repayment&nbsp;Variable&nbsp;Pay:</td>    
                                                            <td><s:textfield name="repaymentVariablePay" id="repaymentVariablePay" cssClass="inputTextBlueWages" value="%{repaymentVariablePay}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="80"/></td>
                                                        </tr>
                                        </table>
                                         </div>

                                    </s:form>
                                    <script>
                                                                                          
                                        var cal3 = new CalendarTime(document.forms['employeeActualWageDetails'].elements['projectEndDate']);
                                        cal3.year_scroll = true;
                                        cal3.time_comp = false;
                                            var cal4 = new CalendarTime(document.forms['employeeActualWageDetails'].elements['releasedDate']);
                                        cal4.year_scroll = true;
                                        cal4.time_comp = false;                                                  
                                    </script>  
                                </div>
                                <!--Actual Details Tab  End-->
                                <!-- other Details  Tab  Start -->
                                <div id="yearAndDateTab" class="tabcontent" > 
                                    <s:form action="" name="yearAndDateDetails" theme="simple">


                                       
 <div align="right" style=" border-radius: 25px;
                                                         border: 2px solid #3e93d4;
                                                         padding: 20px;
                                                         width: 630px;
                                                         height: 84%;
                                                         margin-left: 10%;position: relative;background-color:#c5def2; ">
                                        <table cellpadding="1" cellspacing="1" border="0" width="70%" align="left" bgcolor="">
                                             <tr> 
                                        
                      
                  
                                        <td>
                                          
                                        </td>
                                        <td  align="right" colspan="3">
                                            <table>
                                                <tr>

                                                    <td>
                                                        <INPUT type="button" CLASS="btnPayrollWage" value="Back to List" onClick="history.go(-1)">
                                                    </td>  

                                                    <td>

                                                        <div id="updateAllForEditEmpWages4" style="">  <input type="button" class="btnPayrollWage" value="Update All" onclick="updateAllEmpWageDetails();" tabindex="102"/></div>
                                                         <div id="updateForyearAndDateTab"  >  <input type="button" class="btnPayrollWage" value="Update" onclick="updateYearAndDate();"/></div>    

                                                    </td>
     <td>
                                            <input type="button" class="btnPayrollWage" value="Cancel" onclick="window.location.href=window.location.href"  tabindex="24"/>
    
                                        </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr> 
                                            <tr>
                                                <td class="fieldLabelWages"  align="right">Employee&nbsp;Name:</td>                           

                                                  <td><font class="navigationTextWages"><s:property value="%{employeeName}"/></font> </td>  



                                            </tr>

                                            <tr>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;Gross:</td>    
                                                <td><s:textfield name="ytdGross" id="ytdGross" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdGross}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="81"/></td>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;Taxable&nbsp;Salary:</td>    
                                                <td><s:textfield name="ytdTaxableSalary" readonly="true" id="ytdTaxableSalary" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdTaxableSalary}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="93"/></td>
                                            </tr>  
                                            <tr>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;LongTerm:</td>    
                                                <td><s:textfield name="ytdLongterm" readonly="true" id="ytdLongterm" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdLongterm}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="82"/></td>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;Taxable&nbsp;Commission:</td>    
                                                <td><s:textfield name="ytdTaxableCommission" readonly="true" id="ytdTaxableCommission" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdTaxableCommission}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="94"/></td>
                                            </tr>  
                                            <tr>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;PF:</td>    
                                                <td><s:textfield name="ytdPf" id="ytdPf" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdPf}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="83"/></td>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;TDS&nbsp;On&nbsp;Salary:</td>    
                                                <td><s:textfield name="ytdTDSonSalary" readonly="true" id="ytdTDSonSalary" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdTDSonSalary}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="95"/></td>
                                            </tr>  
                                            <tr>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;Profession&nbsp;Tax:</td>    
                                                <td><s:textfield name="ytdProffTax" readonly="true" id="ytdProffTax" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdProffTax}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="84"/></td>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;TDS&nbsp;On&nbsp;Commission:</td>    
                                                <td><s:textfield name="ytdTDSOnCommm" id="ytdTDSOnCommm" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdTDSOnCommm}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="96"/></td>
                                            </tr>  
                                            <tr>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;Life&nbsp;Insurance:</td>    
                                                <td><s:textfield name="ytdLifeInsurance" id="ytdLifeInsurance" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdLifeInsurance}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="85"/></td>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;TDS&nbsp;Collected:</td>    
                                                <td><s:textfield name="ytdTDSCollected" readonly="true" id="ytdTDSCollected" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdTDSCollected}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="97"/></td>
                                            </tr>  
                                            <tr>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;TA:</td>    
                                                <td><s:textfield name="ytdTa" id="ytdTa" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdTa}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="86"/></td>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;TDS&nbsp;Liabilities&nbsp;Salary:</td>    
                                                <td><s:textfield name="ytdTDSLiabilitiesSalary" readonly="true" id="ytdTDSLiabilitiesSalary" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdTDSLiabilitiesSalary}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="98"/></td>
                                            </tr>  
                                            <tr>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;RA:</td>    
                                                <td><s:textfield name="ytdRa" id="ytdRa" readonly="true" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdRa}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="87"/></td>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;TDS&nbsp;Liabilities&nbsp;Bonus:</td>    
                                                <td><s:textfield name="ytdTDSLiabilitiesBonus" readonly="true" id="ytdTDSLiabilitiesBonus" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdTDSLiabilitiesBonus}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="99"/></td>
                                            </tr>  
                                            <tr>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;Others(MISC&nbsp;Pay):</td>    
                                                <td><s:textfield name="ytdOthersMisc" readonly="true" id="ytdOthersMisc" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdOthersMisc}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="88"/></td>
                                                <td class="fieldLabelWages" align="right">Diff&nbsp;TDS&nbsp;Liabilities&nbsp;Salary:</td>    
                                                <td><s:textfield name="diffTDSLiabilitiesSalary" readonly="true" id="diffTDSLiabilitiesSalary" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{diffTDSLiabilitiesSalary}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="100"/></td>
                                            </tr>  
                                            <tr>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;Expenses(TAX&nbsp;Free):</td>    
                                                <td><s:textfield name="ytdExpTaxFree" readonly="true" id="ytdExpTaxFree" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdExpTaxFree}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="89"/></td>
                                                <td class="fieldLabelWages" align="right">Diff&nbsp;TDS&nbsp;Liabilities&nbsp;Bonus:</td>    
                                                <td><s:textfield name="diffTDSLiabilitiesBonus" readonly="true" id="diffTDSLiabilitiesBonus" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{diffTDSLiabilitiesBonus}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="101"/></td>
                                            </tr>  
                                            <tr>
                                                <td class="fieldLabelWages" align="right">YTD&nbsp;Project&nbsp;Pay:</td>    
                                                <td colspan="3"><s:textfield name="ytdProjectPay" readonly="true" id="ytdProjectPay" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdProjectPay}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="90"/></td>
                                            </tr>
                                            <tr>
                                                <td class="fieldLabelWages" align="right" >YTD&nbsp;Savings1&nbsp;Reported:</td>    
                                                <td colspan="3"><s:textfield name="ytdSavings1Reported" readonly="true" id="ytdSavings1Reported" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdSavings1Reported}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="91"/></td>
                                            </tr>
                                            <tr>
                                                <td class="fieldLabelWages" align="right" colspan="">YTD&nbsp;Savings2&nbsp;Reported:</td>    
                                                <td colspan="3"><s:textfield name="ytdSavings2Reported" readonly="true" id="ytdSavings2Reported" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{ytdSavings2Reported}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex="92"/></td>
                                            </tr>
                                        </table>
 </div>
                                    </s:form>
                                </div>
                                <div id="tdsTab" class="tabcontent" > 
                                    <s:form action="" name="tdsDetails" theme="simple">


                                        <table cellpadding="1" cellspacing="1" border="0" width="100%" align="center" bgcolor="">
                                            <tr>
                                                <td>
                                                    <div align="right" style=" border-radius: 25px;
                                                         border: 2px solid #3e93d4;
                                                         padding: 20px;
                                                         width: 630px;
                                                         height: 84%;
                                                         margin-right: 30%;position: relative;background-color:#c5def2; ">
                                                        <table width="80%" border="0" align="center" bgcolor="">
                                                             <tr> 
                                        
                      
                  
                                        <td>
                                           
                                        </td>
                                        <td  align="right" colspan="5">
                                            <table>
                                                <tr>

                                                    <td>
                                                        <INPUT type="button" CLASS="btnPayrollWage" value="Back to List" onClick="history.go(-1)">
                                                    </td>  

                                                    <td>

                                                        <div id="updateAllForEditEmpWages5" style="">  <input type="button" class="btnPayrollWage" value="Update All" onclick="updateAllEmpWageDetails();" tabindex="129"/></div>

                                                    </td>

                                                </tr>
                                            </table>
                                        </td>
                                    </tr> 
                                                            <tr>
                                                                <td class="fieldLabelWages"  align="right">Employee&nbsp;Name:</td>                           

                                                                <td><font class="navigationTextWages"><s:property value="%{employeeName}"/></font> </td>  
                                                                <td class="fieldLabelWages"  align="right">Expected&nbsp;Yearly&nbsp;Total&nbsp;Gross: </td>                           

                                                                <td colspan="3" align="right"><s:textfield name="expectedYearlyCost" id="expectedYearlyCost" cssClass="inputTextBlueWages" value="%{expectedYearlyCost}" onkeyup="isNumberKeyWage(this);" tabindex="128"/></td>     
                                                            </tr>

                                                            <tr>

                                                                <td class="fieldLabelWages" align=" " colspan="2"></td>
                                                                <td class="fieldLabelWages" align="left" colspan="2"><h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Investment</h2></td>    

                                                                <td class="fieldLabelWages" align="left" colspan="2"><h2>Deductible</h2></td>    

                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align=" " colspan="2"><h2>Investment&nbsp;U/S&nbsp;80C</h2></td>    
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabelWages" align="right" colspan="2">Life&nbsp;Insurance&nbsp;Premium:</td>    


                                                                <td colspan="2" align="right"><s:textfield name="lifeInsurancePremium" id="lifeInsurancePremium" cssClass="inputTextBlueWages" value="%{lifeInsurancePremium}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="104"/></td>
                                                                <td colspan="2" align=""></td>

                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="right" colspan="2">Housing&nbsp;Loan&nbsp;Repayment:</td>    


                                                                <td colspan="2" align="right"><s:textfield name="housingLoanRepay" id="housingLoanRepay" cssClass="inputTextBlueWages" value="%{housingLoanRepay}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="105"/></td>
                                                                <td colspan="2" align=""></td>

                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="right" colspan="2">NSC:</td>    


                                                                <td colspan="2" align="right"><s:textfield name="nscTds" id="nscTds" cssClass="inputTextBlueWages" value="%{nscTds}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="106"/></td>

                                                                <td colspan="2" align=""></td>
                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="right" colspan="2">PPF&nbsp;Contribution:</td>    


                                                                <td colspan="2" align="right"><s:textfield name="ppfContribution" id="ppfContribution" cssClass="inputTextBlueWages" value="%{ppfContribution}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="107"/></td>
                                                                <td colspan="2" align=""></td>

                                                            </tr> 
                                                            <tr>
                                                                <td class="fieldLabelWages" align="right" colspan="2">Tution&nbsp;Fee:</td>    


                                                                <td colspan="2" align="right"><s:textfield name="tutionFee" id="tutionFee" cssClass="inputTextBlueWages" value="%{tutionFee}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="108"/></td>

                                                                <td colspan="2" align=""></td>
                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="right" colspan="2">ELSS:</td>    


                                                                <td colspan="2" align="right"><s:textfield name="elss" id="elss" cssClass="inputTextBlueWages" value="%{elss}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="109"/></td>

                                                                <td colspan="2" align=""></td>
                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="right" colspan="2">Post&nbsp;Office&nbsp;Term&nbsp;Deposit:</td>    


                                                                <td colspan="2" align="right"><s:textfield name="postOfficeTerm" id="postOfficeTerm" cssClass="inputTextBlueWages" value="%{postOfficeTerm}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="110"/></td>
                                                                <td colspan="2" align=""></td>

                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="right" colspan="2">Bank&nbsp;Deposit&nbsp;(Tax&nbsp;Saving):</td>    


                                                                <td colspan="2" align="right"><s:textfield name="bankDepositTax" id="bankDepositTax" cssClass="inputTextBlueWages" value="%{bankDepositTax}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="111"/></td>
                                                                <td colspan="2" align=""></td>

                                                            </tr>  
                                                            <tr>
                                                                                    <td class="fieldLabelWages" align="right" colspan="2">LIC from Salary:</td>    


                                                                                    <td colspan="2" align="right"><s:textfield name="licFromSal" id="licFromSal" cssClass="inputTextBlueWages" value="%{licFromSal}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="112"/></td>

                                                                                    <td colspan="2" align=""></td>
                                                                                </tr> 
                                                            <tr>
                                                                <td class="fieldLabelWages" align="right" colspan="2">Others:</td>    


                                                                <td colspan="2" align="right"><s:textfield name="othersTDS" id="othersTDS" cssClass="inputTextBlueWages" value="%{othersTDS}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="113"/></td>

                                                                <td colspan="2" align=""></td>
                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="" colspan="2"><h2>Investment U/S 80CCC</h2></td>    

                                                            </tr>   

                                                            <tr>
                                                                <td class="fieldLabelWages" align="right" colspan="2">Contribution&nbsp;to&nbsp;PF:</td>    


                                                                <td colspan="2" align="right"><s:textfield name="contributionToPf" id="contributionToPf" cssClass="inputTextBlueWages" value="%{contributionToPf}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="114"/></td>
                                                                <td colspan="2" align=""></td>

                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="" colspan="2">80CCD-NPS-Employees&nbsp;contribution:</td>    


                                                                <td colspan="2" align="right"><s:textfield name="npsEmployeeContr" id="npsEmployeeContr" cssClass="inputTextBlueWages" value="%{npsEmployeeContr}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="115"/></td>
                                                                <td colspan="2" align=""></td>

                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="" colspan="2"> Total(max exemptions-1,50,000):</td>    


                                                                <td colspan="2" align="right"><s:textfield name="totalTds" id="totalTds" cssClass="inputTextBlueWages" value="%{totalTds}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="116"/></td>
                                                                <td colspan="2" align="right"><s:textfield name="totalTdsDeductable" id="totalTdsDeductable" cssClass="inputTextBlueWages" value="%{totalTdsDeductable}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="117"/></td>


                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="" colspan="2">Interest&nbsp;on&nbsp;borrowed&nbsp;capital-self&nbsp;<br>occupied&nbsp;prop(max exemptions-2,00,000):</td>    


                                                                <td colspan="2" align="right"><s:textfield name="interstOnBorrowed" id="interstOnBorrowed" cssClass="inputTextBlueWages" value="%{interstOnBorrowed}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="118"/></td>
                                                                <td colspan="2" align="right"><s:textfield name="interstOnBorrowedDeductable" id="interstOnBorrowedDeductable" cssClass="inputTextBlueWages" value="%{interstOnBorrowedDeductable}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="119"/></td>


                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align=" " colspan="2"><h2>Medical&nbsp;Insurance&nbsp;U/S80d</h2></td>    

                                                            </tr>   

                                                            <tr>
                                                                <td class="fieldLabelWages" align="right" colspan="2">Insurance&nbsp;for&nbsp;parents(Paid&nbsp;for&nbsp;senior&nbsp;citizens)<br>(max exemptions-20,000):</td>    


                                                                <td colspan="2" align="right"><s:textfield name="insuranceForParents" id="insuranceForParents" cssClass="inputTextBlueWages" value="%{insuranceForParents}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="120"/></td>
                                                                <td colspan="2" align="right"><s:textfield name="insuranceForParentsDeduc" id="insuranceForParentsDeduc" cssClass="inputTextBlueWages" value="%{insuranceForParentsDeduc}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="121"/></td>


                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="" colspan="2">Insurance&nbsp;other(self,spouse and children)<br>(max exemptions-15,000):</td>    


                                                                <td colspan="2" align="right"><s:textfield name="insuranceOthers" id="insuranceOthers" cssClass="inputTextBlueWages" value="%{insuranceOthers}" onchange="tdsCalculation();" onkeyup="isNumberKeyWage(this);" tabindex="122"/></td>
                                                                <td colspan="2" align="right"><s:textfield name="insuranceOthersDeduc" id="insuranceOthersDeduc" cssClass="inputTextBlueWages" value="%{insuranceOthersDeduc}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="123"/></td>


                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="" colspan="2">Interest&nbsp;on&nbsp;education&nbsp;loan&nbsp;repayment(80E):</td>    

                                                                <td colspan="2" align=""></td>
                                                                <td colspan="2" align="right"><s:textfield name="interstOnEdu" id="interstOnEdu" cssClass="inputTextBlueWages" value="%{interstOnEdu}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="124"/></td>



                                                            </tr>  
                                                            <tr>
                                                                <td class="fieldLabelWages" align="" colspan="2">HR&nbsp;Exemptions:</td>    

                                                                <td colspan="2" align="right"><s:textfield name="interstOnHrAssumptionsInv" id="interstOnHrAssumptionsInv" cssClass="inputTextBlueWages" value="%{interstOnHrAssumptionsInv}" onchange="payRollFieldLengthValidator(this);computeHrExemtionsDed();" onkeyup="isNumberKeyWage(this);" tabindex="125"/></td>

                                                                <td colspan="2" align="right"><s:textfield name="interstOnHrAssumptions" id="interstOnHrAssumptions" cssClass="inputTextBlueWages" value="%{interstOnHrAssumptions}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="126"/></td>


                                                            </tr>  
                                                            <tr>
                                                                <td colspan="6" align="right"><input type="button" class="btnPayrollWage" value="Refresh" onclick="computeExpectedYearlyValues1();" tabindex="127"/></td>
                                                            </tr>

                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </s:form>
                                </div>

                             
                                <!--//END TABBED PANNEL : -->

                                <!--//END TABBED PANNEL : -->
                            </div>
                                
                                   <script type="text/javascript">

                                    var countries=new ddtabcontent("accountTabs")
                                    countries.setpersist(true)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()
                                        var countries=new ddtabcontent("accountTabs1")
                                    countries.setpersist(true)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()

                                </script>
                               </div>
                        </td>
                        <!--//END DATA COLUMN : Coloumn for Screen Content-->
                    </tr>
                </table>
            </td>
        </tr>
        <!--//END DATA RECORD : Record for LeftMenu and Body Content-->

        <!--//START FOOTER : Record for Footer Background and Content-->
        <tr class="footerBg">
            <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
        </tr>
        <!--//END FOOTER : Record for Footer Background and Content-->
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>
    </table>
    <!--//END MAIN TABLE : Table for template Structure-->
    
<script type="text/javascript">
		$(window).load(function(){
      ifFreezedShowUpdate();
	  changeRepayComments();
	  changeDorepayment();
	 <%--  initDate('<%=check%>'); --%>
		});
		</script>
                
</body>
</html>

