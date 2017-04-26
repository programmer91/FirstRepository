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
        <title>Hubble Organization Portal :: PayRoll Reports</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpDetailsValidation.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeLocation.js"/>"></script>
        <s:include value="PayrollCalculations.jsp"/>
    <s:include value="/includes/template/headerScript.html"/>
    <style> 
            #fontId {
                position: relative;
                -webkit-animation: mymove 5s infinite; /* Chrome, Safari, Opera */
                animation: mymove 1s infinite;
                font-size:49px;
            }

            /* Chrome, Safari, Opera */
            @-webkit-keyframes mymove {
                from {opacity:0.2;}
            to {opacity:1;}
            }

            @keyframes mymove {
                from {opacity:0.2;}
            to {opacity:1;}
            }
        </style>
    <script>
        function runWageFlagCheck(){
       var wageFlag= document.getElementById('wageFlag').value;
      
               if(wageFlag=='1'){
            toggleRunPayrollReportOverlay();
        }
        var bankReportFlag = document.getElementById('bankReportFlag').value;
       
          if(bankReportFlag=='1'){
            toggleBankReportForWagesRequirement();
        }
        if(bankReportFlag == '2'){
            togglegetPayrollReportOverlay();
        }
         
        var payslipFlag = document.getElementById('payslipFlag').value;
        //alert(payslipFlag)
         if(payslipFlag=='1'){
            toggleGetPayslipOverlay();
        }
        if(payslipFlag == '2'){
            toogleTef();
        }
          
        }
    </script>
</head>
<%-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="validateCtc();getLocation();"> --%>
<%-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="init();initForTefSearch();initWage();initTef();runWageFlagCheck();"> --%>
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
                            <ul id="accountTabs" class="shadetabs" >
                                <li ><a href="#" class="selected" rel="payRollReportTab"  >PayRoll Report</a></li>

                            </ul>

                            <%--    <sx:tabbedpanel id="employeeUpdatePannel" cssStyle="width:850px; height:510px;padding: 5px 5px 5px 5px;" doLayout="true" useSelectedTabCookie="true">--%>
                            <div  style="border:1px solid gray; width:850px;height: 510px; overflow:auto; margin-bottom: 1em;">

                                <div id="payrollReportOverlay"></div>
                                <div id="hubblePayrollReportOverlay" align="center">
                                    <div id="blockDiv" style="display: none;align:center;position:fixed;top:50%;left:34%;">
                            <font style="font-weight:bold;position: absolute;color: #fff;font-size: 23px;top:-53px;left:34% ">  Run Payroll <font id="fontId">...</font> </font>
                            <img src='../../includes/images/ajax-loader.gif' WIDTH='35%' HEIGHT='5%'  alt='block'>
                        </div>
                                    <div id="displyID"></div>

                                    <s:form theme="simple" action="doRunWages" name="wagesOverlay" id="wagesOverlay">
                                        <s:hidden id="wageFlag" name="wageFlag" value="%{wageFlag}"/>
                                        <table style="width: 100%;">

                                            <tr class="overlayHeader">
                                                <td>
                                                   <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                    Run Payroll</font> 
                                                   
                                                        <a href="#" onclick="toggleRunPayrollReportOverlay();"><img  align="right" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> </a>
                                                   
                                                </td>
                                           </tr>
                                            
                                      
                                            
                                            <tr>
                                                <td>
                                                    <div id="resultMessage" style="font-size: 15px;">
                                                         
                                                         <%
                                                         if(request.getAttribute("runWageResponse")!=null){
                                                             out.print(request.getAttribute("runWageResponse"));
                                                         }
                                                         %>
                                                     </div>
                                                </td>
                                                    
                                            </tr>
                                            <tr>
                                                <td>
                                                    <br><br>
                                                    <table style="width:70%;margin-top:-25px;">
                                                          <tr>
                                                            <td colspan="4">
                                                                <div id="loadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                                            </td>   
                                                            
                                                        </tr>
                                                        <tr>
                                                            <td colspan="4">
                                                                <div id="resultMessage"></div>
                                                            </td>
                                                        </tr>
                                                        <tr>

                                                            <td class="overlayFieldLabels">Year(YYYY):</td>
                                                            <td>

                                                                <s:textfield name="yearOverlay" id="yearOverlay" maxlength="4" cssClass="" value="%{yearOverlay}" onchange="getDaysForTheSelectedMonth();"/>
                                                            </td>
                                                            <td class="overlayFieldLabels">Month:</td>
                                                            <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="monthOverlay" id="monthOverlay"  headerValue="select" headerKey="0" value="%{monthOverlay}" cssClass="inputSelectSmall" onchange="getDaysForTheSelectedMonth();"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="overlayFieldLabels">No.&nbsp;of&nbsp;Days:</td>
                                                            <td colspan="">
                                                                <s:textfield  id="noOfDays" name="noOfDays" value="%{noOfDays}"/>
                                                            </td>
                                                            <td class="overlayFieldLabels">No.&nbsp;of&nbsp;Weekend&nbsp;Days:</td>
                                                            <td colspan="">
                                                                <s:textfield  id="noOfWeekendDays" name="noOfWeekendDays" value="%{noOfWeekendDays}"/>
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <%--<td class="overlayFieldLabels">No.&nbsp;of&nbsp;Holidays:</td>
                                                            <td colspan="">
                                                                <s:textfield  id="noOfHolidays" name="noOfHolidays" value="%{noOfHolidays}" onchange="fillNoOfDaysWorked();" />
                                                            </td> --%>
                                                            <td class="overlayFieldLabels">No.&nbsp;of&nbsp;Working&nbsp;Days:</td>
                                                            <td colspan="">
                                                                <s:textfield  id="noOfWorkingDays" name="noOfWorkingDays" value="%{noOfWorkingDays}" />
                                                            </td>
                                                            <td class="overlayFieldLabels">Payment&nbsp;Date:</td>
                                        <td colspan="">
                                            <s:textfield id="paymentDateEmp" name="paymentDateEmp" cssClass="inputTextBlue" value="%{paymentDate}" onchange="isValidDate(this)" style="width:122px"/>
                                            <a href="javascript:cal4.popup();">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0" style="margin-bottom: -4px"></a>
                                        </td> </tr>
                                           <tr>
                                                        <td class="overlayFieldLabels" >Organization:</td>
                                                                
                                                                <td colspan="">
                                                                    <s:select  id="orgId" name="orgId"  list="orgIdMap" style="width:150%" headerKey=""
                                                        headerValue="-- Please Select --" class="inputSelectSmall"  value="%{orgId}" />
                                               </td>
                                                    </tr>
        
                                                    <tr>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                            <td align="right" colspan="2">
                                                                <input type="button" class="button_payroll" value="Next" onclick="doRunWagesScript();"/>
                                                            </td>
                                                        </tr>
                                                      

                                                    </table>

                                                </td>
                                            </tr>

                                        </table>
<script type="text/JavaScript">
                                     
                                                var cal4 = new CalendarTime(document.forms['wagesOverlay'].elements['paymentDateEmp']);
                                                cal4.year_scroll = true;
                                                cal4.time_comp = false;
                                                                                 
                                            </script>
                                    </s:form>
                                </div>
                                
                                <!-- Re run overlay start -->
                                 <div id="rerunWagesOverlay"></div>
                                <div id="rerunReportOverlay" align="center">
                                    <s:form theme="simple" action="doRunWages" name="wagesOverlay" id="wagesOverlay">
                                        <table style="width: 100%;">

                                            <tr class="overlayHeader">
                                                <td colspan="2">

                                                    <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                    ReRun Wages</font> 
                                                   
                                                        <a href="#" onclick="tooglereRunWages();" style="color:#869fa3;font-family: myHeaderFonts;text-decoration: none; "><img  align="right" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> </a>
                                                   
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                     <div id="rerunresultMessage" style="font-size: 15px;"></div>
                                                </td>
                                                    
                                            </tr>
                                            <tr>
                                                <td>
                                                    <br><br>
                                                    <table style="width:70%;margin-top:-25px;">
                                                        
                                                        <tr>
                                                             <td class="overlayFieldLabels">Emp No:</td>
                                                            <td>
                                                               <s:textfield name="rerunEmpNoOverlay" id="rerunEmpNoOverlay" maxlength="5" cssClass="" value="%{rerunEmpNoOverlay}" onclick="disableReRunempnameById()" />
                                                            </td>
                                                            <td class="overlayFieldLabels">Emp&nbsp;Name:</td>
                                                           
                                                                <td >
                                                                      <s:textfield name="employeeName" id="empNameWage"  autocomplete="off" cssClass="inputTextBlue" onkeyup="getEmployeeNames1();"/><span id="empNoSpan"></span><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessageWage"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popupWage">
                                                                                                    <table id="completeTableWage" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                            <s:hidden name="empNameById" id="empNameById" value="%{empNameById}"/>
                                                    </td> 
                                                            
                                                        </tr>

                                                        <tr>
                                                            <td class="overlayFieldLabels">Year(YYYY):</td>
                                                            <td>

                                                                <s:textfield name="rerunyearOverlay" id="rerunyearOverlay" maxlength="4" cssClass="" value="%{yearOverlay}" onchange="getDaysForTheSelectedMonthReRunWages();"/>
                                                            </td>
                                                            <td class="overlayFieldLabels">Month:</td>
                                                            <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="rerunmonthOverlay" id="rerunmonthOverlay"  headerValue="select" headerKey="0" value="%{monthOverlay}" cssClass="inputSelectSmall" onchange="getDaysForTheSelectedMonthReRunWages();"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="overlayFieldLabels">No.&nbsp;of&nbsp;Days:</td>
                                                            <td colspan="">
                                                                <s:textfield  id="rerunnoOfDays" name="rerunnoOfDays" value="%{noOfDays}"/>
                                                            </td>
                                                            <td class="overlayFieldLabels">No.&nbsp;of&nbsp;Weekend&nbsp;Days:</td>
                                                            <td colspan="">
                                                                <s:textfield  id="rerunnoOfWeekendDays" name="rerunnoOfWeekendDays" value="%{noOfWeekendDays}"/>
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <td class="overlayFieldLabels">No.&nbsp;of&nbsp;Holidays:</td>
                                                            <td colspan="">
                                                                <s:textfield  id="rerunnoOfHolidays" name="rerunnoOfHolidays" value="%{noOfHolidays}" onchange="fillNoOfDaysWorkedForReRunWages();" />
                                                            </td>
                                                            <td class="overlayFieldLabels">No.&nbsp;of&nbsp;Working&nbsp;Days:</td>
                                                            <td colspan="">
                                                                <s:textfield  id="rerunnoOfWorkingDays" name="rerunnoOfWorkingDays" value="%{noOfWorkingDays}" />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2">
                                                                <div id="rerunloadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                                            </td>   
                                                            <td align="right" colspan="2">
                                                                <input type="button" class="button_payroll" value="Next" onclick="confirmForReRunWagesHolidaysUpdation();"/>
                                                            </td>
                                                        </tr>
                                                       

                                                    </table>

                                                </td>
                                            </tr>

                                        </table>

                                    </s:form>
                                </div>
                                <!-- Re run overlay end -->
                                <div id="bankReportsGridOverlay"></div>
                                    <div id="bankReportsGridOverlayMain" style="width: 502px;"  align="center">
                                        <s:form theme="simple" >
                                            <table style="width: 100%;">

                                                <tr class="overlayHeader">
                                                    <td colspan="2">

                                                        <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                        Details</font> 
                                                       
                                                            <a href="#" onclick="toggleBankReportForWagesRequirement();" style="color:#869fa3;font-family: myHeaderFonts;text-decoration: none; "><img  align="right" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> </a>
                                                      
                                                    </td>
                                                </tr>
                                                <tr>
                                                <td>
                                                    <div id="bankReportsresultMessage" style="font-size: 15px;">
                                                         <%
                                                         if(request.getAttribute("bankReportsResponse")!=null){
                                                             out.print(request.getAttribute("bankReportsResponse"));
                                                         }
                                                         %>
                                                     </div>
                                                </td>
                                              </tr>
                                                <tr>
                                                    <td>
                                                        <br><br>
                                                        <table style="width:70%;margin-top:-25px;">
                                                              <s:hidden id="bankReportFlag" name="bankReportFlag" value="%{bankReportFlag}"/>
                                                            <tr>

                                                                <td class="overlayFieldLabels">Year(YYYY):</td>
                                                                <td>

                                                                    <s:textfield name="yearOverlayForBiometricReportGeneration" id="yearOverlayForBiometricReportGeneration" maxlength="4" cssClass="" value="%{yearOverlayForBiometricReportGeneration}" readonly="false"/>
                                                                </td>
                                                                <td class="overlayFieldLabels">Month:</td>
                                                                <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="monthForBiometricReportGeneration" id="monthForBiometricReportGeneration"  headerValue="select" headerKey="" value="%{monthForBiometricReportGeneration}" cssClass="inputSelectSmall" contenteditable="false"/></td>
                                                                <td><s:checkbox name="doRepaymentFlag" id="doRepaymentFlag"/></td>
                                                                <td class="overlayFieldLabels">Repayment</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="overlayFieldLabels" >Organization:</td>
                                                                
                                                                <td colspan="3">
                                                                   <s:select  id="orgIdForBank" name="orgId"  list="#@java.util.LinkedHashMap@{'116866':'Chikiniki Enterprises(India) Pvt. Ltd','117009':'IT Lokam Services(India), Pvt. Ltd','104124':'Miracle Software Systems(India), Pvt. Ltd'}" headerKey=""
                                                        headerValue="-- Please Select --"  value="%{orgId}" />

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="overlayFieldLabels">Bank_Name:</td>
                                                                <td colspan="">
                                                                  <s:select 
                                                        name="bankName" 
                                                        id="bankName"
                                                        list="bankNameList" cssClass="inputSelect" tabindex="49"/>
                                                                </td>
                                                                <td colspan="2">
                                                                    <div id="buttonsForExcel"> <input style="float: right" type="button" class="button_payroll" value="Generate Excel" onclick="getBankReport();"/>
                                                             </td>
                                                             <td colspan="2">
                                                                    <input style="float: right" type="button" class="button_payroll" value="Generate PDF" onclick="getBankReportAsPDF();"/>
                                                             </td>
                                                             </tr>
                                                            
                                                                
                                                            <tr>
                                                                <td colspan="4">
                                                                    <div id="resultMessage"></div>
                                                                </td>
                                                            </tr>

                                                        </table>

                                                    </td>
                                                </tr>

                                            </table>

                                        </s:form>
                                    </div>
 <!-- Leaves upload start -->
                                      <div id="leavesUplaodSpecailbox"></div>
                                <div id="leavesUploadOverlay" align="center">
                                    <s:form theme="simple" action="doRunWages" name="wagesOverlay" id="wagesOverlay">
                                        <table style="width: 100%;">

                                            <tr class="overlayHeader">
                                                <td colspan="2">

                                                    <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                    Load Attendance</font> 
                                                    
                                                        <a href="#" onclick="toggleUploadLeavesOverlay();" style="color:#869fa3;font-family: myHeaderFonts;text-decoration: none; "><img  align="right" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> </a>
                                                   
                                                </td>
                                            </tr>
                                           
                                            <tr>
                                                <td>
                                                    <br><br>
                                                    <table style="width:70%;margin-top:-25px;">
                                                        <tr><td colspan="4">
                                                         <lable id="leavesUploadResultMessage" style="font-size: 15px;">
                                                         
                                                     </lable>
                                                   </td></tr>
                                                        <tr>
                                                                <td class="overlayFieldLabels" >Organization:</td>
                                                                
                                                                <td colspan="3">
                                                                    <s:select  id="orgIdForLeavesUpload" name="orgId"  list="#@java.util.LinkedHashMap@{'116866':'Chikiniki Enterprises(India) Pvt. Ltd','117009':'IT Lokam Services(India), Pvt. Ltd','104124':'Miracle Software Systems(India), Pvt. Ltd'}" headerKey=""
                                                        headerValue="-- Please Select --"  value="%{orgId}" />
                                                                </td>
                                                            </tr>

                                                        <tr>

                                                            <td class="overlayFieldLabels">Year(YYYY):</td>
                                                            <td>

                                                                <s:textfield name="leaveYear" id="leaveYear" maxlength="4" cssClass="" value="%{yearOverlay}" />
                                                            </td>
                                                            <td class="overlayFieldLabels">Month:</td>
                                                            <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="leaveMonth" id="leaveMonth"  headerValue="select" headerKey="0" value="%{monthOverlay}" cssClass="inputSelectSmall" /></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="overlayFieldLabels">Excel&nbsp;Sheet:</td>
                                                            <td colspan="">
                                                                <input type="file" id="file" name="file"  accept=".xls,.xlsx" class="browseButtonStyle" onchange="putFileName();"/> 
                                                            </td>
                                                            
                                                        </tr>

                                                       
                                                        <tr>
                                                            <td colspan="3">
                                                                <div id="leavesUploadLoadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                                            </td>   
                                                            <td align="right" colspan="3">
                                                                <input type="button" class="button_payroll" value="Upload" onclick="return doUploadLeavesExcel();"/>
                                                            </td>
                                                        </tr>
                                                      

                                                    </table>

                                                </td>
                                            </tr>

                                        </table>

                                    </s:form>
                                </div>
                                                                    
                                                                    
                                     <!-- Leaves upload end -->                               
                                  <!-- TEF overlay-->
                                    <div id="tefOverlay"></div>
                                  <div id="rerunTefOverlay" align="center">
                                    <s:form theme="simple" action="doRunWages" name="wagesOverlay" id="wagesOverlay">
                                        <table style="width: 100%;">

                                            <tr class="overlayHeader">
                                                <td colspan="2">

                                                    <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                    TEF Report</font> 
                                                  
                                                        <a href="#" onclick="toogleTef();" style="color:#869fa3;font-family: myHeaderFonts;text-decoration: none; "><img  align="right" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> </a>
                                                   
                                                </td>
                                            </tr>
                                          
                                            <tr>
                                                <td>
                                                    <br><br>
                                                    <table style="width:70%;margin-top:-25px;">
                                                          <tr>
                                               
                                                     <div id="tefresultMessage" style="font-size: 15px;">
                                                         <%
                                                         if(request.getAttribute("payslipResponse")!=null){
                                                             out.print(request.getAttribute("payslipResponse"));
                                                         }
                                                         %>
                                                     </div>
                                              
                                                    
                                                      </tr>
                                                        <s:hidden id="payslipFlag" name="payslipFlag" value="%{payslipFlag}"/>
                                                         <tr>
                                                            <td class="overlayFieldLabels">Emp No:</td>
                                                            <td>

                                                                <s:textfield name="tefEmpNoOverlay" id="tefEmpNoOverlay" maxlength="5" cssClass="" value="%{tefEmpNoOverlay}" onclick="" />
                                                            </td>
                                                              <td class="overlayFieldLabels">Select&nbsp;EmpName:</td>
                                                <td >
                                                 <s:textfield name="employeeName" id="empNameTef"  autocomplete="off" cssClass="inputTextBlue" onkeyup="getEmployeeNamesTef();"/><span id="empNoSpan"></span><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessageTef"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popupTef">
                                                                                                    <table id="completeTableTef" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                            <s:hidden name="empNameById" id="empNameByIdTef" value="%{empNameByIdTef}"/>
                                                    </td> 

                                            </tr>
                                                        
                                                   <tr>

                                                            <td class="overlayFieldLabels">Year(YYYY):</td>
                                                            <td>

                                                                <s:textfield name="yearOverlay" id="tefyearOverlay" maxlength="4" cssClass="" value="%{yearOverlay}" />
                                                            </td>
                                                            <td class="overlayFieldLabels">Month:</td>
                                                            <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="monthOverlay" id="tefmonthOverlay"  headerValue="select" headerKey="0" value="%{monthOverlay}" cssClass="inputSelectSmall"/></td>
                                                        </tr>         

                                                        
                                                        <tr>
                                                            <td colspan="2">
                                                                <div id="tefloadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                                            </td>   
                                                            <td align="right" colspan="2">
                                                                <input type="button" class="button_payroll" value="Generate" onclick="getTefReport();"/>
                                                            </td>
                                                        </tr>
                                                       

                                                    </table>

                                                </td>
                                            </tr>

                                        </table>

                                    </s:form>
                                </div>
                                  <!-- TEF overlay end -->   
                                  
                                  
                                  
                                    <!-- freeze overlay start -->
                                    <div id="freezeOverlay"></div>
                                <div id="freezePayrollOverlay" align="center">
                                    <s:form theme="simple" action="" name="" id="">
                                        <table style="width: 100%;">

                                            <tr class="overlayHeader">
                                                <td colspan="2">

                                                    <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                    Freeze Wages</font> 
                                                   
                                                        <a href="#" onclick="toggleFreezeForWagesOverlay();" style="color:#869fa3;font-family: myHeaderFonts;text-decoration: none; "><img  align="right" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div id="freezeresultMessage" style="font-size: 15px;">
                                                       
                                                    </div>
 <div id="freezeloadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
 </td>
                                                         
                                            </tr>
                                            <tr>
                                                <td>
                                                    <br><br>
                                                    <table style="width:100%;">
                                                       
                                                         <tr>
                                                            <td class="overlayFieldLabels">Year(YYYY):</td>
                                                            <td>
                                                                <s:textfield name="freezeyearOverlay" id="freezeyearOverlay" maxlength="4" cssClass="" value="%{yearOverlay}" />
                                                            </td>
                                                            <td class="overlayFieldLabels">Month:</td>
                                                            <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="freezemonthOverlay" id="freezemonthOverlay"  headerValue="select" headerKey="0" value="%{monthOverlay}" cssClass="inputSelectSmall" /></td>
                                                        </tr>
                                                        
                                                        <tr>    
                                                          
                                                  <td class="overlayFieldLabels">Emp&nbsp;Name:</td>
                                                           
                                                                <td>
                                                                      <s:textfield name="employeeName" id="empNameFreeze"  autocomplete="off" cssClass="inputTextBlue" onkeyup="getEmployeeNamesFreeze();"/><span id="empNoSpan"></span><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessageFreeze"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popupFreeze">
                                                                                                    <table id="completeTableFreeze" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                                                      <s:hidden name="empNameById" id="empNameByIdFreeze" />
                                                    </td>      
                                                       
                                                             <td class="overlayFieldLabels">Emp No:</td>
                                                            <td>
                                                               <s:textfield name="freezeEmpNoOverlay" id="freezeEmpNoOverlay" maxlength="5" cssClass="" value="%{freezeEmpNoOverlay}"  />
                                                            </td>
                                                            </tr>
                                                            <tr>
                                                             <td class="overlayFieldLabels" >Organization:</td>
                                                                
                                                                <td colspan="">
                                                                    <s:select  id="freezeorgId" name="orgId"  list="orgIdMap" style="width:56%" headerKey=""
                                                        headerValue="-- Please Select --" class="inputSelectSmall"  value="%{orgId}" />
                                                                </td>
                                                           
                                                            <td align="right" colspan="2">
                                                             <input type="button" class="button_payroll" value="Freeze" onclick="setFreezeForWages();"/>
                                                             </td>
                                                    </tr>

                                                    </table>

                                                </td>
                                            </tr>

                                        </table>

                                    </s:form>
                                </div>
                                  <!-- freeze overlay end -->
                                  <!-- unfreeze overlay start -->
                                    <div id="unfreezeOverlay"></div>
                                <div id="unfreezePayrollOverlay" align="center">
                                    <s:form theme="simple" action="" name="" id="">
                                        <table style="width: 100%;">

                                            <tr class="overlayHeader">
                                                <td colspan="2">

                                                    <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                    UnFreeze Wages</font> 
                                                   
                                                        <a href="#" onclick="toggleUnFreezeForWagesOverlay();" style="color:#869fa3;font-family: myHeaderFonts;text-decoration: none; "><img  align="right" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> </a>
                                                  
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div id="unfreezeresultMessage" style="font-size: 15px;"></div>
 <div id="unfreezeloadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
 </td>
                                                         
                                            </tr>
                                            <tr>
                                                <td>
                                                    <br><br>
                                                    <table style="width:100%;">
                                                       
                                                         <tr>
                                                            <td class="overlayFieldLabels">Year(YYYY):</td>
                                                            <td>

                                                                <s:textfield name="unfreezeyearOverlay" id="unfreezeyearOverlay" maxlength="4" cssClass="" value="%{yearOverlay}" />
                                                            </td>
                                                            <td class="overlayFieldLabels">Month:</td>
                                                            <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="unfreezemonthOverlay" id="unfreezemonthOverlay"  headerValue="select" headerKey="0" value="%{monthOverlay}" cssClass="inputSelectSmall" /></td>
                                                        </tr>
                                                        
                                                        <tr>   
                                                       <td class="overlayFieldLabels">Emp&nbsp;Name:</td>
                                                           
                                                                <td>
                                                                      <s:textfield name="employeeName" id="empNameUnFreeze"  autocomplete="off" cssClass="inputTextBlue" onkeyup="getEmployeeNamesUnFreeze();"/><span id="empNoSpan"></span><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessageUnFreeze"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popupUnFreeze">
                                                                                                    <table id="completeTableUnFreeze" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                                                      <s:hidden name="empNameById" id="empNameByIdUnFreeze" />
                                                    </td>
                                           
                                                             <td class="overlayFieldLabels">Emp No:</td>
                                                            <td>
                                                               <s:textfield name="unfreezeEmpNoOverlay" id="unfreezeEmpNoOverlay" maxlength="5" cssClass="" value="%{unfreezeEmpNoOverlay}"  />
                                                            </td>
                                                            </tr>
                                                            <tr>
                                                            <td class="overlayFieldLabels" >Organization:</td>
                                                                
                                                                <td colspan="">
                                                                    <s:select  id="unfreezeorgId" name="orgId"  list="orgIdMap" style="width:56%" headerKey=""
                                                        headerValue="-- Please Select --" class="inputSelectSmall"  value="%{orgId}" />
                                                                </td>
                                                           
                                                                
                                                            <td align="right" colspan="2">
                                                             <input type="button" class="button_payroll" value="UnFreeze" onclick="setUnFreezeForWages();"/>
                                                             </td>
                                                    </tr>

                                                    </table>

                                                </td>
                                            </tr>

                                        </table>

                                    </s:form>
                                </div>
                                  <!-- unfreeze overlay end -->
                                   <!-- cleanPayroll overlay start -->
                                    <div id="cleanPayrollOverlay"></div>
                                <div id="cleanPayrollHubbleOverlay" align="center">
                                    <s:form theme="simple" action="" name="" id="">
                                        <table style="width: 100%;">

                                            <tr class="overlayHeader">
                                                <td colspan="2">

                                                    <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                    Clean Payroll</font> 
                                                   
                                                        <a href="#" onclick="togglecleanPayrollOverlay();" style="color:#869fa3;font-family: myHeaderFonts;text-decoration: none; "><img  align="right" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> </a>
                                                   
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div id="cleanPayrollresultMessage" style="font-size: 15px;"></div>
 <div id="cleanPayrollloadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
 </td>
                                                         
                                            </tr>
                                            <tr>
                                                <td>
                                                    <br><br>
                                                    <table style="width:70%;margin-top:-25px;">
                                                       
                                                         <tr>
                                                            <td class="overlayFieldLabels">Year(YYYY):</td>
                                                            <td>
                                                               <s:textfield name="year" id="cleanYear" maxlength="4" cssClass="" value="%{yearOverlay}" />
                                                            </td>
                                                            <td class="overlayFieldLabels">Month:</td>
                                                            <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="cleanMonth"  headerValue="select" headerKey="0" value="%{monthOverlay}" cssClass="inputSelectSmall" /></td>
                                                        </tr>
                                                        
                                                        <tr>
                                                            <td class="overlayFieldLabels" >Organization:</td>
                                                                
                                                                <td colspan="">
                                                                    <s:select  id="cleanorgId" name="orgId"  list="orgIdMap" style="width:73%" headerKey=""
                                                        headerValue="-- Please Select --" class="inputSelectSmall"  value="%{orgId}" />
                                                                </td>
                                                                <td align="right" colspan="2">
                                                             <input type="button" class="button_payroll" value="Clean" onclick="cleanPayroll();"/>
                                                             </td>
                                                        </tr>

                                                    </table>

                                                </td>
                                            </tr>

                                        </table>

                                    </s:form>
                                </div>
                                  <!-- cleanPayroll overlay end -->
                                  
                                  
                                    <!-- payslip overlay start -->
                                    <div id="getPayslipOverlay"></div>
                                <div id="getPayslipHubbleOverlay" align="center">
                                    <s:form theme="simple" action="" name="" id="">
                                        <table style="width: 100%;">

                                            <tr class="overlayHeader">
                                                <td colspan="2">

                                                    <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                    Generate Payslips</font> 
                                                   
                                                        <a href="#" onclick="toggleGetPayslipOverlay();" style="color:#869fa3;font-family: myHeaderFonts;text-decoration: none; "><img  align="right" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> </a>
                                                  
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div id="payslipResultMessage" style="font-size: 15px;">
                                                         <%
                                                         if(request.getAttribute("payslipResponse")!=null){
                                                             out.print(request.getAttribute("payslipResponse"));
                                                           
                                                         }
                                                         %>
                                                    </div>
 <div id="paysliploadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
 
 </td>
                                                         
                                            </tr>
                                            <tr>
                                                <td>
                                                    <br><br>
                                                    <table style="width:90%;">
                                                       
                                                  <s:hidden id="payslipFlag" name="payslipFlag" value="%{payslipFlag}"/>     
                                              <tr>

                                                <td class="overlayFieldLabels">Year(YYYY):</td>
                                                <td>

                                                    <s:textfield name="year" id="year" maxlength="4" cssClass="inputTextBlue" value="%{year}"/>
                                                </td>
                                                <td class="overlayFieldLabels">Month:</td>
                                                <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="month" onchange="load(event);" headerValue="select" headerKey="0" value="%{month}" cssClass="inputSelectSmall"/></td>
                                            </tr> 
                                            <tr>
                                                <td class="overlayFieldLabels">Emp No:</td>
                                                <td>

                                                    <s:textfield name="empNo" id="empNo" maxlength="4" cssClass="inputTextBlue" value="%{empNo}"/>
                                                </td>
                                                
                                            <td class="overlayFieldLabels">EmpName:</td>
                                                <td >
                                                   <s:textfield name="employeeName" id="employeeName1" value="" autocomplete="off" cssClass="inputTextBlue" onkeyup="getEmplyeeNamesForTefSearch();"/><span id="empNoSpan"></span><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessage1"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popup1">
                                                                                                    <table id="completeTable1" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                                                                                <s:hidden id="userId" name="userId"/>
                                                </td> 

                                            </tr>                                 
                                            
                                                        
                                                        <tr>
                                                             <td></td>
                                                              <td></td>
                                                            <td align ="right" colspan="2">
                                                             <input type="button" class="button_payroll" value="Generate" onclick="getPaySlip();"/>
                                                             </td>
                                                        </tr>

                                                    </table>

                                                </td>
                                            </tr>

                                        </table>

                                    </s:form>
                                </div>
                                  <!-- payslip overlay end -->
                                   <!-- payrollReport overlay start -->
                                    <div id="getPayrollReportOverlay"></div>
                                <div id="getPayrollReportHubbleOverlay" align="center">
                                    <s:form theme="simple" action="" name="" id="">
                                        <table style="width: 100%;">

                                            <tr class="overlayHeader">
                                                <td colspan="2">

                                                    <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                    Generate Wage</font> 
                                                  
                                                        <a href="#" onclick="togglegetPayrollReportOverlay();" style="color:#869fa3;font-family: myHeaderFonts;text-decoration: none; "><img  align="right" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> </a>
                                                  
                                                </td>
                                            </tr>
                                            
                                            <tr>
                                                <td>
                                                    <br><br>
                                                    <table style="width:100%;">
                                                    <tr>   
                                                    
                                                    <div id="payrollResultMessage" style="font-size: 15px;">
                                                         <%
                                                         if(request.getAttribute("bankReportsResponse")!=null){
                                                             out.print(request.getAttribute("bankReportsResponse"));
                                                         }
                                                         %>
                                                    </div>
 <div id="payrollloadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
     <s:hidden id="bankReportFlag" name="bankReportFlag" value="%{bankReportFlag}"/>
 </tr>
                                              <tr>

                                                <td class="overlayFieldLabels">Year(YYYY):</td>
                                                <td>

                                                    <s:textfield name="year" id="payrollYear" maxlength="4" cssClass="inputTextBlue" value="%{year}"/>
                                                </td>
                                                <td class="overlayFieldLabels">Month:</td>
                                                <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="payrollMonth" onchange="load(event);" headerValue="select" headerKey="0" value="%{month}" cssClass="inputSelectSmall"/></td>
                                            </tr>
                                            <tr>
                                                 <td class="overlayFieldLabels">Emp No:</td>
                                                <td>

                                                    <s:textfield name="empNo" id="payrollEmpNo" maxlength="4" cssClass="inputTextBlue" value="%{empNo}"/>
                                                </td>
                                            <td class="overlayFieldLabels">EmpName:</td>
                                                <td>
                                                     <s:textfield name="employeeName" id="employeeName" value="" autocomplete="off" cssClass="inputTextBlue" onkeyup="getEmplyeeNames();"/> <span id="empNoSpan"></span><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessage"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popup">
                                                                                                    <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                                                                                <s:hidden id="empLoginId" name="empLoginId"/></td> 

                                            </tr>                                 
                                            <tr>
                                                <td></td>
                                                  <td></td>
                                                     <td align ="right" colspan="2">
                                                             <input type="button" class="button_payroll" value="generate" onclick="getPayrollReport();"/>
                                                             </td>
                                                        </tr>

                                                    </table>

                                                </td>
                                            </tr>

                                        </table>

                                    </s:form>
                                </div>
                                  <!-- payrollReport overlay end -->
                                  
                                  
                                  
                                  
                                <div id="payRollReportTab" class="tabcontent"  >   
                                
                                    <s:form action="" name="generatepayrollform" id="generatepayrollform" theme="simple">
   <div id="resultMessageForFreeze" style="font-size: 15px;"></div>
 <div id="loadingMessageForFreeze" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>

                                        <table cellpadding="0" cellspacing="3" border="0" align="center" width="100%" style="padding-top: 3%">
                                         
                                            <tr>
                                                <td colspan="4"><br></br></td>
                                            </tr>
                                            <tr valign="middle">

                                                <td valign="middle" colspan="4">
                                                    <table cellpadding="0" class="tableOfButtons" cellspacing="0" border="0" align="center">
                                                        <tr>
                                                            
                                                            <td>
                                                                  <input type="button" class="btnPayrollReports" value="  Load Attendance  " onclick="toggleUploadLeavesOverlay();"/>

                                                            </td>
                                                            <td>
                                                                  <input type="button" class="btnPayrollReports" value="    Run Payroll     " onclick="toggleRunPayrollReportOverlay();"/>
                                                            </td>
                                                            <td colspan="2">
                                                                  <input type="button" class="btnPayrollReports" value="     Wage register  " onclick="goToWageRegistery();"/>

                                                            </td>
                                                            
                                                            
                                                        </tr>
                                                        <tr>
                                                             <td>
                                                                  <input type="button" class="btnPayrollReports" value="       Freeze          " onclick="toggleFreezeForWagesOverlay();"/>

                                                            </td>
                                                           <td>
                                                                 <input type="button" class="btnPayrollReports" value="       UnFreeze       " onclick="toggleUnFreezeForWagesOverlay();"/>
                                                            </td>
                                                            <td colspan="2">
                                                                 <input type="button" class="btnPayrollReports" value="    Clean Payroll     " onclick="togglecleanPayrollOverlay();"/>
                                                            </td>
                                                             
                                                        </tr>
                                                        <tr>
                                                           <td>
                                                                  <input type="button" class="btnPayrollReports" value="     Bank Reports    " onclick="toggleBankReportForWagesRequirement();"/>

                                                            </td>
                                                            <td>
                                                                 <input type="button" class="btnPayrollReports" value="  Generate Wage   " onclick="togglegetPayrollReportOverlay();"/>
                                                            </td>
                                                             <td colspan="2">
                                                                  <input type="button" class="btnPayrollReports" value="Generate Payslips" onclick="toggleGetPayslipOverlay();"/>

                                                            </td>
                                                            
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                 <input type="button" class="btnPayrollReports" value="   Run Single Wage " onclick="tooglereRunWages();"/>
                                                            </td>
                                                            <td colspan="">
                                                                 <input type="button" class="btnPayrollReports" value="          TEF            " onclick="toogleTef();"/>
                                                            </td>
                                                           
   <td colspan="">
                                                                  <input type="button" class="btnPayrollReports" value="   Release Payslip   " onclick="goToReleasePayslip();"/>
                                                            </td>


                                                        </tr> 
                                                        
                                                            
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>


                                    </s:form>
                                    <%--   </sx:div> --%>
                                </div>

                            </div>
                            <script type="text/javascript">

                                var countries=new ddtabcontent("accountTabs")
                                countries.setpersist(true)
                                countries.setselectedClassTarget("link") //"link" or "linkparent"
                                countries.init()

                            </script>
                            <%-- <script type="text/JavaScript">
                                                                    var cal1 = new CalendarTime(document.forms['generatepayrollform'].elements['hireDate']);
                                                                         cal1.year_scroll = true;
                                                                         cal1.time_comp = false;
                                                                         </script> --%>
                            <!--//END TABBED PANNEL : -->

                            <!--//END TABBED PANNEL : -->

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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script> 
     <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/nameSuggestion.js"/>"></script>
       <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>
     
    </table>
    <!--//END MAIN TABLE : Table for template Structure-->
    
<script type="text/javascript">
		$(window).load(function(){
 init();
 initForTefSearch();
 initWage();
 initTef();
 runWageFlagCheck();
		});
		</script>
</body>
</html>

