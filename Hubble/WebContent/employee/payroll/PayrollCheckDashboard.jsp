<%-- 
    Document   : PayrollCheckDashboard
    Created on : Apr 29, 2015, 3:24:50 PM
    Author     : miracle
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<html>
    <head>
        <title>Hubble Organization Portal :: Payroll Check</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/animatedcollapse.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-1.2.2.pack.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/Activity.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ReusableContainer.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollajaxscript.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollclientvalidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollCheckAjaxUtill.js?version=1.1"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/jquery.min.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/pagination.css"/>">
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
                
           
            animatedcollapse.addDiv('PayrollCheckExcelReportDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('PayRollHistoryDiv', 'fade=0;persist=1;group=app');
            animatedcollapse.addDiv('WagesHistoryDiv', 'fade=0;persist=1;group=app');
            animatedcollapse.addDiv('LockAmountCheckDiv', 'fade=0;persist=1;group=app');
            animatedcollapse.addDiv('pfProfessionalTaxReportsDiv', 'fade=0;persist=1;group=app');
            animatedcollapse.addDiv('repaymentDetailsDiv', 'fade=0;persist=1;group=app');
            animatedcollapse.addDiv('tdsCalculationDiv', 'fade=0;persist=1;group=app');
            animatedcollapse.addDiv('blockedSalariesDiv', 'fade=0;persist=1;group=app');
            animatedcollapse.addDiv('revisedSalariesDiv', 'fade=0;persist=1;group=app');
             animatedcollapse.addDiv('employeeTaxSavings', 'fade=0;persist=1;group=app');
            animatedcollapse.init();
            function hideSelect(){
                //document.getElementById("priorityId").style.display = 'none';
                
            }
        </script>
    </head>
        
            
          <body  class="bodyGeneral" oncontextmenu="return false;">

        <%!
            /* Declarations */
            String queryString = null;
            Connection connection;
            Statement stmt;
            ResultSet rs;
            int userCounter = 0;
            int activityCounter = 0;
            int accountCounter = 0;
            /* Declarations */
            String strTmp;
            String strSortCol;
            String strSortOrd;
            String submittedFrom;
            String searchSubmitValue;
            int intSortOrd = 0;
            int intCurr;
            boolean blnSortAsc = true;

        %>


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
                <td width="850px;" class="leftMenuBgColor" valign="top"> 
                    <s:include value="/includes/template/LeftMenu.jsp"/>
                </td>
                <!--//START DATA COLUMN : Coloumn for LeftMenu-->

                <!--//START DATA COLUMN : Coloumn for Screen Content-->
                <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                    <!--//START TABBED PANNEL : -->
                    <%--      <sx:tabbedpanel id="resetPasswordPannel" cssStyle="width: 845px; height: 550px;padding-left:10px;padding-top:5px;" doLayout="true" useSelectedTabCookie="true" > 
                        
                        <!--//START TAB : -->
                        <sx:div id="dashBoardTab" label="DashBoard Details" cssStyle="overflow:auto;"> --%>
                    <ul id="reportsTab" class="shadetabs" >
                        <li><a href="#" rel="EmpReportsTab" class="selected"> Employee Reports DashBoard</a></li>

                    </ul>
                    <div  style="border:1px solid gray; width:840px;height: 550px;overflow:auto; margin-bottom: 1em;">    
                        <br><br>
                        <div id="EmpReportsTab" class="tabcontent" >  
                            <table cellpadding="0" cellspacing="0" border="0" width="100%">


                                <!-- new div for account list by priority start -->
                                <tr>
                                <td class="homePortlet" valign="top">
                                    <div class="portletTitleBar">
                                        <div class="portletTitleLeft">Payroll Check Report</div>
                                        <div class="portletIcons">
                                            <a href="javascript:animatedcollapse.hide('PayrollCheckExcelReportDiv')" title="Minimize">
                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                            <a href="javascript:animatedcollapse.show('PayrollCheckExcelReportDiv')" title="Maximize">
                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                            </a>
                                        </div>
                                        <div class="clear"></div>
                                    </div>
                                    <div id="PayrollCheckExcelReportDiv" style="background-color:#ffffff">
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                            <tr>
                                            <td width="40%" valign="top" align="center">
                                                <s:form action="" theme="simple" name="leaveReports" id="leaveReports">  

                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                        <tr align="right">
                                                        <td class="headerText" colspan="9">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                        </td>
                                                        </tr>

                                                        <tr>
                                                        <td>
                                                            <%
                                                                if (session.getAttribute("resultMessage") != null) {
                                                                    out.println(session.getAttribute("resultMessage"));
                                                                    session.removeAttribute("resultMessage");
                                                                }

                                                            %>
                                                        </td>
                                                        </tr>
                                                        <tr>
                                                        <td>
                                                            <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                <tr>
                                                                <td class="fieldLabel">Year(YYYY):</td>
                                                                <td>

                                                                    <s:textfield name="year" id="year" maxlength="4" cssClass="inputTextBlue" value="%{year}" onblur="yearValidation(this.value,event)" onkeypress="yearValidation(this.value,event)"/>
                                                                </td>
                                                                <td class="fieldLabel">Month:</td>
                                                                <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="month" onchange="" headerValue="select" headerKey="" value="%{month}"/></td>
                                                                 </tr>
                                                                 <tr>
                                                                   <td  class="fieldLabel">Organization:</td>
                                                            <td><s:select list="orgIdMap" id="organizationId" name="organizationId" headerKey="" headerValue="All" cssClass="inputSelect" value="%{organizationId}" /></td>

                                                                <td class="fieldLabel">Difference :</td>
                                                                <td><s:select list="#@java.util.LinkedHashMap@{'0-5':'0-5','5-10':'5-10','10-15':'10-15','15-20':'15-20','greater than 20':'> 20','negative difference':'< 0'}" name="difference" id="difference" onchange="" headerValue="select" headerKey="" /></td>
                                                              
                                                                <td colspan="2" width="200px" align="right">
                                                                  
                                                                    <input type="button"  value="Search" Class="button_payroll" onClick="load();"/>
                                                                </td>

                                                                </tr>
                                                                <br>

                                                            </table>
                                                        </td>
                                                        </tr>
                                                    </table>
                                                </s:form>
                                            </td>
                                            </tr>
                                            <tr>
                                            <td align="center"><span id="resultmsg" style="display: none"><font color="red" size="2px">no records found..</font></span></td>
                                            </tr>
                                            <tr>
                                            <td align="center"><span id="loadingMessage" style="display: none"><font color="red" size="2px">loading please wait...</font></span></td>
                                            </tr>

                                            <tr>
                                            <td>

                                                <br>
                                                <div id="payrollCheckList" style="display: inline">
                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                    <div id="pagCheck" style=" display: none;">
                                                        <label style="margin-left:5%; position: relative;color:blue"> Display <select id="paginationOptionCheck" class="disPlayRecordsCss" onchange="pagerOptionCheck();" style="width: auto">

                                                                <option>15</option>
                                                                <option>20</option>
                                                                <option>25</option>
                                                            </select> Rows</label></div>
                                                     
                                                    <table id="tblUpdate" cellpadding='0' cellspacing='0' border='0' class="gridTable" width='90%' align="center">
                                                        <!--COLGROUP ALIGN="left" >
                                                            <COL width="10%">
                                                                <COL width="20%">
                                                                    <COL width="10%">
                                                                        <COL width="10%">

                                                                            <COL width="10%">
                                                                                <COL width="10%">
                                                                                    <COL width="10%"-->

                                                    </table>
                                                 
                                            <span id="colorDiff" style="display : none">
                                               
                                                <table cellpadding="0" style="margin:4px 5%;" cellspacing="0" border="0" width="61%">
                                                    <tr>
                                                    <td><font style="color:#e3170d;font-family:Arial,Verdana,Geneva,Tahoma,Trebuchet MS,sans-serif;font-size:11px;">Difference above 20%</font></td><td><div id="rectangleRed"></div></td>
                                                      <td>&nbsp;</td>
                                                  <td><font style="color:#003f87;font-family:Arial,Verdana,Geneva,Tahoma,Trebuchet MS,sans-serif;font-size:11px;">Difference between 10%-19%</font></td><td><div id="rectangleBlue"></div></td>
                                                   <td>&nbsp;</td>
                                                   <td><font style="color:#008000;font-family:Arial,Verdana,Geneva,Tahoma,Trebuchet MS,sans-serif;font-size:11px;">Negative Difference</font></td><td><div id="rectangleGreen"></div></td>
                                                    <td>&nbsp;&nbsp;&nbsp;</td>  
                                                   <td> <div align="right" ><font style="margin:3px;color:#e3170d;font-family:Arial,Verdana,Geneva,Tahoma,Trebuchet MS,sans-serif;font-size:15px;">Total:</font><span id="total"></span></div>    </td>
                                                </tr>
                                                </table>
                                              
                                            </span>
                                                    
                                                    
                                           
                                                    <br>
                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                </div>
                                                <div id="menuDiv" align="center" style="display:none;position: absolute;top: 120px; left: 180px;">
                                                    <table border="1" bgcolor="#FBFBB1" width="100">
                                                        <tr>
                                                        <td>
                                                            <a id="detailsLink"><font class="fieldLabel">Details</font></a>
                                                            <br>
                                                            <!--<a><font class="fieldLabel">Send Alert</font></a>
                                                            <br>-->
                                                            <a id="cancelLink"><font class="fieldLabel">Cancel</font></a>
                                                        </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </td>
                                            </tr>

                                        </table>

                                    </div>
                                </td>
                                </tr>

                                <!-- new div for account list by priority end -->

                            </table>

                            <%--     </sx:div>
                    </sx:tabbedpanel> --%>
                            <!--//END TABBED PANNEL : --> 
                        </div>

                        <div id="PayRollHistory" class="tabcontent" style="display: block;" >  
                            <table cellpadding="0" cellspacing="0" border="0" width="100%">


                                <!-- new div for account list by priority start -->
                                <tr>
                                <td class="homePortlet" valign="top">
                                    <div class="portletTitleBar">
                                        <div class="portletTitleLeft">PayRoll History</div>
                                        <div class="portletIcons">
                                            <a href="javascript:animatedcollapse.hide('PayRollHistoryDiv')" title="Minimize">
                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                            <a href="javascript:animatedcollapse.show('PayRollHistoryDiv')" title="Maximize">
                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                            </a>
                                        </div>
                                        <div class="clear"></div>
                                    </div>
                                    <div id="PayRollHistoryDiv" style="background-color:#ffffff">
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                            <tr>
                                            <td width="40%" valign="top" align="center">
                                                <s:form action="getPayrollHistory" theme="simple" name="leaveReports" id="leaveReports">  

                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                        <tr align="right">
                                                        <td class="headerText" colspan="9">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                        </td>
                                                        </tr>

                                                        <tr>
                                                        <td>
                                                            <%
                                                                if (session.getAttribute("payRollHistoryResultMessage") != null) {
                                                                    out.println(session.getAttribute("payRollHistoryResultMessage"));
                                                                    session.removeAttribute("payRollHistoryResultMessage");
                                                                }

                                                            %>
                                                        </td>
                                                        </tr>
                                                        <tr>
                                                        <td>
                                                            <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                <tr>
                                                                 <td class="fieldLabel">Employee Name:</td>
                                                <td >
                                                 <s:textfield name="employeeName" id="empNameTef"  autocomplete="off" cssClass="inputTextBlue" onkeyup="getEmployeeNamesTef();"/><span id="empNoSpan"></span><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessageTef"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popupTef">
                                                                                                    <table id="completeTableTef" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                            <s:hidden name="empNameById" id="empNameByIdTef" value="%{empNameByIdTef}"/>
                                                    </td> 
                                                               
                                                                <td class="fieldLabel">Employee No.</td>
                                                                <td ><s:textfield name="empNo" id="payRollHistoryempNo" cssClass="inputTextBlue" value="%{empNo}" onchange="firstNameValidate(document.frmEmpSearch.firstName.value);"/></td>





                                                                <td colspan="2" width="200px" align="right">

                                                                    <input type="button"  value="Search" Class="button_payroll" onClick="getPayRollHistory();"/>

                                                                </td>

                                                                </tr>



                                                            </table>
                                                        </td>
                                                        </tr>
                                                    </table>
                                                </s:form>
                                            </td>
                                            </tr>
                                            <tr>
                                            <td align="center"><span id="payrollHistoryresultmsg" style="display: none"><font color="red" size="2px">no records found..</font></span></td>
                                            </tr>
                                            <tr>
                                            <td align="center"><span id="payrollHistoryloadingMessage" style="display: none"><font color="red" size="2px">loading please wait...</font></span></td>
                                            </tr>
                                            <tr>
                                            <td>

                                                <br>
                                                <div id="payrollHistoryList" style="display: inline">
                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                    <div id="pagHistory" style=" display: none;">
                                                        <label style="margin-left:5%; position: relative;color:blue"> Display <select id="paginationOptionHistory" class="disPlayRecordsCss" onchange="pagerOptionHistory();" style="width: auto">

                                                                <option>15</option>
                                                                <option>20</option>
                                                                <option>25</option>
                                                            </select> Rows</label></div>
                                                    <table id="tblpayrollHistoryUpdate" cellpadding='0' cellspacing='0' border='0' class="gridTable" width='90%' align="center">
                                                        <!--COLGROUP ALIGN="left" >
                                                            <COL width="10%">
                                                                <COL width="10%">
                                                                    <COL width="20%">
                                                                        <COL width="10%"-->
                                                    </table>
                                                                            <br>
                                                                            <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                            </div>
                                                                            <div id="menuDiv" align="center" style="display:none;position: absolute;top: 120px; left: 180px;">
                                                                                <table border="1" bgcolor="#FBFBB1" width="100">
                                                                                    <tr>
                                                                                    <td>
                                                                                        <a id="detailsLink"><font class="fieldLabel">Details</font></a>
                                                                                        <br>
                                                                                        <!--<a><font class="fieldLabel">Send Alert</font></a>
                                                                                        <br>-->
                                                                                        <a id="cancelLink"><font class="fieldLabel">Cancel</font></a>
                                                                                    </td>
                                                                                    </tr>
                                                                                </table>
                                                                            </div>
                                                                            </td>
                                                                            </tr>

                                                                            </table>

                                                                            </div>
                                                                            </td>
                                                                            </tr>

                                                                            <!-- new div for account list by priority end -->

                                                                            </table>

                                                                            <%--     </sx:div>
                                                                    </sx:tabbedpanel> --%>
                                                                            <!--//END TABBED PANNEL : --> 

                                                                            <div id="payRollHistoryGridOverlay" Style="display: none;"></div>
                                                                            <div id="payRollHistoryGridOverlayMain" style="width: 900px;top :-40px; left :15%;display: none;"  align="center">
                                                                                <s:form theme="simple" >
                                                                                    <table style="width: 100%;">

                                                                                        <tr class="overlayHeader">
                                                                                        <td colspan="2">

                                                                                            <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                                                                Details</font> 
                                                                                            <div style="float: right" class="closeButton" align="right">
                                                                                                <a href="#" onclick="togglePayRollHistoryRequirement('0','0');" style="width: 50px;background: red;color:white;font-family: myHeaderFonts;text-decoration: none; "><b>X</b></a>
                                                                                            </div> 
                                                                                        </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                        <td>


                                                                                            <ul id="accountTabs" class="shadetabs" >
                                                                                                <li ><a href="#" class="selected" rel="employeeDetails"  tabindex="1">Employee Details</a></li>
                                                                                                <li ><a href="#" rel="contactDetails" tabindex="23">Contact Details</a></li>
                                                                                                <li ><a href="#" rel="payrollDetails" tabindex="37">Payroll Details</a></li>
                                                                                                <li ><a href="#" rel="insuranceSavingsTab" tabindex="66">Tax Exemptions</a></li>
                                                                                                <%--  <li ><a href="#" rel="otherDetailsTab" tabindex="83">Other Details</a></li>
                                                                                                  <li ><a href="#" rel="tdsTab" tabindex="89">TDS</a></li> --%>
                                                                                            </ul>
                                                                                            <div  style="border:1px solid gray; width:850px;height: 510px; overflow:auto; margin-bottom: 1em;">
                                                                                                <div id="employeeDetails" class="tabcontent" style="display: block;">

                                                                                                    <br>

                                                                                                    <s:form name="employeeDetails" id="employeeDetails" action="employeeDetailsUpdate" method="post" theme="simple" >




                                                                                                        <div align="center" style=" border-radius: 25px;
                                                                                                             border: 2px solid #3e93d4;
                                                                                                             padding: 20px;
                                                                                                             width: 800px;
                                                                                                             height: 400px;
                                                                                                             margin-right: 30%;
                                                                                                             position: relative;background-color:#c0c0c0; display: block;">

                                                                                                            <table cellpadding="1" cellspacing="1" border="0" width="80%">


                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">Emp&nbsp;Name&nbsp;:</td>                           

                                                                                                                <td colspan="3"><font class="navigationTextWages"><lable id="employeeName"></lable></font> </td>    

                                                                                                                </tr>

                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" colspan="">Organization :</td>

                                                                                                                <td colspan="5">


                                                                                                                    <s:textfield 
                                                                                                                        name="orgId" 
                                                                                                                        id="orgId"
                                                                                                                        cssClass="inputSelectWagesEmpUpdate" 
                                                                                                                        value="%{orgId}" contenteditable="true" tabindex="2"/>


                                                                                                                </td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">Department&nbsp;:</td>
                                                                                                                <td ><s:textfield 
                                                                                                                        name="departmentId" 
                                                                                                                        id="departmentId"
                                                                                                                        cssClass="inputSelectWages" value="%{departmentId}" onchange="getEmpTitleData();" tabindex="3"/></td>
                                                                                                                <td class="fieldLabelWages"  align="right" >Job&nbsp;Title&nbsp;:</td>
                                                                                                                <td ><s:textfield
                                                                                                                        name="titleId" 
                                                                                                                        id="titleId" cssClass="inputSelectWages" value="%{titleId}" onchange="" tabindex="4"/></td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">Shift&nbsp;:</td>
                                                                                                                <td ><s:textfield 
                                                                                                                        name="shiftId" 
                                                                                                                        id="shiftId"
                                                                                                                        cssClass="inputSelectWages" tabindex="5"/></td>
                                                                                                                <td class="fieldLabelWages"  align="right" >Classification&nbsp;:</td>
                                                                                                                <td ><s:textfield
                                                                                                                        name="classificationId" 
                                                                                                                        id="classificationId"
                                                                                                                        cssClass="inputSelectWages" tabindex="6"/></td>
                                                                                                                </tr>

                                                                                                                <tr>


                                                                                                                <td class="fieldLabelWages" align="right">Location&nbsp;:</td>
                                                                                                                <td ><s:textfield 
                                                                                                                        name="locationId" 
                                                                                                                        id="locationId"
                                                                                                                        cssClass="inputSelectWages" value="%{locationId}" tabindex="7"/></td>
                                                                                                               <td class="fieldLabelWages" align="right" >Practice Name :</td>
                                                                                                               <td><s:textfield cssClass="inputSelectWages"
                                                                                                       name="practiceId"  id="practiceId"
                                                value="%{practiceId}" /> <%--onchange="getTeamData();" --%></td>  
                              
                                                                                                                </tr>
                                                                                                                <tr>

                                                                                                                <td class="fieldLabelWages" align="right">Gender&nbsp;:</td>                           

                                                                                                                <td><s:textfield 
                                                                                                                        name="gender"  id="gender"
                                                                                                                        cssClass="inputSelectWages" value="%{gender}" tabindex="9"/>
                                                                                                                </td>  
                                                                                                                <td class="fieldLabelWages"  align="right">Current&nbsp;Status:</td>                           

                                                                                                                <td><s:textfield 
                                                                                                                        name="currStatus"  id="currStatus"
                                                                                                                        cssClass="inputSelectWages" value="%{currStatus}" tabindex="10" contenteditable="true"/></td>         
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right" >PF&nbsp;Flag&nbsp;:</td>
                                                                                                                <td > <s:checkbox name="isPfFlag" id="isPfFlag"
                                                                                                                            value="%{isPfFlag}" 
                                                                                                                            theme="simple" tabindex="11" onchange=""/> </td>
                                                                                                                <td class="fieldLabelWages"  align="right">Employer&nbsp;Id&nbsp;:</td>                           

                                                                                                                <td ><s:textfield name="employerId" id="employerId" cssClass="inputTextBlueWages" value="%{employerId}" onchange="payRollFieldLengthValidator(this);" readonly="true" tabindex="12"/></td>      
                                                                                                                </tr>
                                                                                                                <tr>


                                                                                                                <td class="fieldLabelWages"  align="right">PAN&nbsp;No.&nbsp;:</td>

                                                                                                                <td><s:textfield name="ssn" id="ssn" cssClass="inputTextBlueWages" value="%{ssn}" onchange="payRollFieldLengthValidator(this);" tabindex="13"/></td>

                                                                                                                <td class="fieldLabelWages"  align="left">Passport No.&nbsp;:</td>                           

                                                                                                                <td ><s:textfield name="passportNo" id="passportNo" cssClass="inputTextBlueWages" value="%{passportNo}" onchange="payRollFieldLengthValidator(this);" tabindex="14"/></td>               
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" width="200px" align="right">PF&nbsp;Account&nbsp;No.&nbsp;:</td>                           

                                                                                                                <td ><s:textfield name="pfAccount" id="pfAccount" cssClass="inputTextBlueWages" value="%{pfAccount}" onchange="payRollFieldLengthValidator(this);" tabindex="15"/></td>      


                                                                                                                <td class="fieldLabelWages"  align="left">Training&nbsp;Period&nbsp;:</td>                           

                                                                                                                <td ><s:textfield name="trainingPeriod" id="trainingPeriod" cssClass="inputTextBlueWages" value="%{trainingPeriod}" onchange="payRollFieldLengthValidator(this);" tabindex="16"/></td> 
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="left" >Contract&nbsp;Period&nbsp;:</td>                           

                                                                                                                <td ><s:textfield name="contractPeriod" id="contractPeriod" cssClass="inputTextBlueWages" value="" onchange="payRollFieldLengthValidator(this);" tabindex="17"/></td> 

                                                                                                                <td class="fieldLabelWages"  align="right">Date&nbsp;Of&nbsp;Joining&nbsp;:</td>

                                                                                                                <td width="30%"><s:textfield name="dateOfJoining" id="dateOfJoining" cssClass="inputTextBlueWages" value="%{dateOfJoining}" onchange="isValidDate(this)" tabindex="18"/><a href="javascript:cal2.popup();">
                                                                                                                </td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">UAN&nbsp;No&nbsp;:</td>                           

                                                                                                                <td ><s:textfield name="UANNo" id="UANNo" cssClass="inputTextBlueWages" value="%{UANNo}" onchange="payRollFieldLengthValidator(this);" tabindex="19"/></td>      

                                                                                                                <td class="fieldLabelWages"  align="right">Adhar&nbsp;No:</td>

                                                                                                                <td><s:textfield name="adharNo" id="adharNo" cssClass="inputTextBlueWages" value="%{adharNo}" onchange="payRollFieldLengthValidator(this);" tabindex="20"/></td>


                                                                                                                </tr>                             

                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages">Reason&nbsp;for&nbsp;Leaving&nbsp;:</td>  
                                                                                                                <td colspan="5"><s:textarea name="resonsForLeaving" id="resonsForLeaving" cols="85" rows="3" cssClass="inputTextArea" value="%{resonsForLeaving}" onchange="payRollFieldLengthValidator(this);" tabindex="21"/></td>
                                                                                                                </tr>

                                                                                                            </table>
                                                                                                        </div>
                                                                                                    </s:form>

                                                                                                    <script>
                                                                                          
                                                                                                        var cal2 = new CalendarTime(document.forms['employeeDetails'].elements['dateOfJoining']);
                                                                                                        cal2.year_scroll = true;
                                                                                                        cal2.time_comp = false;
                                                                                           
                                                                                                    </script>

                                                                                                </div>
                                                                                                <!-- contact Details START -->
                                                                                                <div id="contactDetails" class="tabcontent" style="display: block;" >
                                                                                                    <br>
                                                                                                    <s:form action="contactDetailsUpdate" theme="simple" method="post" id="contactDetails" name="contactDetails" onsubmit="">
                                                                                                        <div align="center" style=" border-radius: 25px;
                                                                                                             border: 2px solid #3e93d4;
                                                                                                             padding: 20px;
                                                                                                             width: 800px;
                                                                                                             height: 400px;
                                                                                                             margin-right: 30%;
                                                                                                             position: relative;background-color:#c0c0c0; ">
                                                                                                            <table cellpadding="1" cellspacing="1" border="0" width="80%">



                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages">Address&nbsp;:</td>  
                                                                                                                <td colspan="5"><s:textarea name="address" id="address" cols="85" rows="3" cssClass="inputTextArea" value="%{address}" onchange="payRollFieldLengthValidator(this);" tabindex="24"/></td>
                                                                                                                </tr>

                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">Corporate&nbsp;Email:</td>                           

                                                                                                                <td colspan=""><s:textfield name="corporateEmail" id="corporateEmail" cssClass="inputTextBlueWages" value="%{corporateEmail}" onchange="payRollFieldLengthValidator(this);" tabindex="25"/></td>      

                                                                                                                <td class="fieldLabelWages"  align="right" colspan="3">Personal&nbsp;Email:</td>

                                                                                                                <td><s:textfield name="personalEmail" id="personalEmail" cssClass="inputTextBlueWages" value="%{personalEmail}" onchange="payRollFieldLengthValidator(this);" tabindex="26"/></td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">Father&nbsp;Name:</td>

                                                                                                                <td colspan=""><s:textfield name="fathername" id="fathername" cssClass="inputTextBlueWages" value="%{fathername}" onchange="payRollFieldLengthValidator(this);" tabindex="27"/></td>


                                                                                                                <td class="fieldLabelWages"  align="right" colspan="3">Father&nbsp;Title:</td>

                                                                                                                <td><s:textfield name="fatherTitle" id="fatherTitle" cssClass="inputTextBlueWages" value="%{fatherTitle}" onchange="payRollFieldLengthValidator(this);" tabindex="28"/></td>


                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">City&nbsp;:</td>                           

                                                                                                                <td ><s:textfield name="city" id="city" cssClass="inputTextBlueWages" value="%{city}" onchange="payRollFieldLengthValidator(this);" tabindex="29"/></td>      

                                                                                                                <td class="fieldLabelWages"  align="right" colspan="3">State&nbsp;:</td>

                                                                                                                <td><s:textfield name="state" id="state" cssClass="inputTextBlueWages" value="%{state}" onchange="payRollFieldLengthValidator(this);" tabindex="30"/></td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="left">Zip&nbsp;:</td>                           

                                                                                                                <td ><s:textfield name="zip" id="zip" cssClass="inputTextBlueWages" value="%{zip}" onchange="payRollFieldLengthValidator(this);" tabindex="31"/></td>               


                                                                                                                <td class="fieldLabelWages"  align="right" colspan="3">Father&nbsp;Phone:</td>                           

                                                                                                                <td ><s:textfield name="fatherPhone" id="fatherPhone" cssClass="inputTextBlueWages" value="%{fatherPhone}" onchange="payRollFieldLengthValidator(this);" tabindex="32"/></td>      
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">Home&nbsp;Phone:</td>

                                                                                                                <td><s:textfield name="homePhone" id="homePhone" cssClass="inputTextBlueWages" value="%{homePhone}" onchange="payRollFieldLengthValidator(this);" tabindex="33"/></td>

                                                                                                                <td class="fieldLabelWages"  align="left" colspan="3">Mobile&nbsp;Phone:</td>                           

                                                                                                                <td ><s:textfield name="mobilePhone" id="mobilePhone" cssClass="inputTextBlueWages" value="%{mobilePhone}" onchange="payRollFieldLengthValidator(this);" tabindex="34"/></td>               
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">Work&nbsp;Phone:</td>

                                                                                                                <td><s:textfield name="workPhone" id="workPhone" cssClass="inputTextBlueWages" value="%{workPhone}" onchange="payRollFieldLengthValidator(this);" tabindex="35"/></td>
                                                                                                                </tr>
                                                                                                            </table>
                                                                                                        </div>
                                                                                                    </s:form>
                                                                                                </div>
                                                                                                <!-- payroll Details START -->

                                                                                                <div id="payrollDetails" class="tabcontent"  >   

                                                                                                    <s:form action="" name="payrollDetails" theme="simple">
                                                                                                        <div align="center" style=" border-radius: 25px;
                                                                                                             border: 2px solid #3e93d4;
                                                                                                             padding: 20px;
                                                                                                             width: 700px;
                                                                                                             height: 480px;
                                                                                                             margin-right: 30%;
                                                                                                             margin-top: 0.2%;
                                                                                                             position: relative;background-color:#c0c0c0;">
                                                                                                            <table cellpadding="1" cellspacing="1" border="0"  align="left"  width="80%">



                                                                                                                <!-- start by nag -->

                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">Basic:</td>    
                                                                                                                <td><s:textfield name="basic" id="basic" cssClass="inputTextBlueWages" value="%{basic}"  onchange="payRollFieldLengthValidator(this);BasicChangeCheck();" onkeyup="isNumberKey(this);" tabindex="38"/></td>
                                                                                                                <td class="fieldLabelWages"  align="right">Payment&nbsp;Type:</td>    
                                                                                                                <td><s:textfield
                                                                                                                        name="paymentType" 
                                                                                                                        id="paymentType"
                                                                                                                        cssClass="inputSelectWages" tabindex="54"/></td>

                                                                                                                </tr>

                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">DA:</td>    
                                                                                                                <td><s:textfield name="da" id="da" cssClass="inputTextBlueWages" value="%{da}" onchange="payRollFieldLengthValidator(this);"  readonly="true" /></td>
                                                                                                                <td class="fieldLabelWages" align="right">Bank&nbsp;Name:</td>    
                                                                                                                <td><s:textfield
                                                                                                                        name="bankName" 
                                                                                                                        id="bankName"
                                                                                                                        cssClass="inputSelectWages" tabindex="55"/></td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">HRA:</td>    
                                                                                                                <td><s:textfield name="hra" id="hra" cssClass="inputTextBlueWages" value="%{hra}" onchange="payRollFieldLengthValidator(this);" readonly="true"/></td>
                                                                                                                <td class="fieldLabelWages" align="right">Bank&nbsp;Account&nbsp;No:</td>    
                                                                                                                <td><s:textfield name="bankAccountNo" id="bankAccountNo" cssClass="inputTextBlueWages" value="%{bankAccountNo}" onkeyup="isNumberKey(this);" onchange="payRollFieldLengthValidator(this);" tabindex="56"/></td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right">TA:</td>    
                                                                                                                <td><s:textfield name="ta" id="ta" cssClass="inputTextBlueWages" value="%{ta}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="39"/></td>
                                                                                                                <td class="fieldLabelWages" align="right">Employer&nbsp;PF&nbsp;:</td>    
                                                                                                                <td><s:textfield name="employerPf" id="employerPf" cssClass="inputTextBlueWages" value="%{employerPf}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" readonly="true"/></td>
                                                                                                            </tr><tr>  
                                                                                                        <td class="fieldLabelWages"  align="right">RA:</td>    
                                                                                                        <td><s:textfield name="ra" id="ra" cssClass="inputTextBlueWages" value="%{ra}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="40"/></td>
                                                                                                        <td class="fieldLabelWages" align="right">Employee&nbsp;PF&nbsp;:</td>    
                                                                                                        <td><s:textfield name="employeePf" id="employeePf" cssClass="inputTextBlueWages" value="%{employeePf}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="57" /></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">Entertainment:</td>    
                                                                                                        <td><s:textfield name="entertainment" id="entertainment" cssClass="inputTextBlueWages" value="%{entertainment}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="41"/></td>
                                                                                                        <td class="fieldLabelWages" align="right">Employer&nbsp;ESI&nbsp;:</td>    
                                                                                                        <td><s:textfield name="employeresi" id="employeresi" cssClass="inputTextBlueWages" value="%{employeresi}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="58"  /></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">Kids&nbsp;Education:</td>    
                                                                                                        <td><s:textfield name="kidsEducation" id="kidsEducation" cssClass="inputTextBlueWages" value="%{kidsEducation}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="42"/></td>
                                                                                                        <td class="fieldLabelWages" align="right">Employee&nbsp;ESI&nbsp;:</td>    
                                                                                                        <td><s:textfield name="employeeesi" id="employeeesi" cssClass="inputTextBlueWages" value="%{employeeesi}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="59" /></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">Vehicle&nbsp;Allowance:</td>    
                                                                                                        <td><s:textfield name="vehicleAllowance" id="vehicleAllowance" cssClass="inputTextBlueWages" value="%{vehicleAllowance}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="43"/></td>

                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">CCA:</td>    
                                                                                                        <td><s:textfield name="cca" id="cca" cssClass="inputTextBlueWages" value="%{cca}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="44"/></td>
                                                                                                        <td class="fieldLabelWages" align="right">Life:</td>    
                                                                                                        <td><s:textfield name="life" id="life" cssClass="inputTextBlueWages" value="%{life}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="60"/></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">Misc&nbsp;Pay:</td>    
                                                                                                        <td><s:textfield name="miscPay" id="miscPay" cssClass="inputTextBlueWages" value="%{miscPay}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="45"/></td>
                                                                                                        <%-- <td class="fieldLabelWages"  align="right">Project&nbsp;End&nbsp;Date&nbsp;:</td>

                                                <td width="30%"><s:textfield name="projectEndDate" id="projectEndDate" cssClass="inputTextBlueWages" value="%{projectEndDate}" onchange="" tabindex="58"/><a href="javascript:cal3.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20" border="0"></td>  --%>
                                                                                                        <td class="fieldLabelWages" align="right">Health:</td>    
                                                                                                        <td><s:textfield name="health" id="health" cssClass="inputTextBlueWages" value="%{health}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="61"/></td>

                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages" align="right">Spl.&nbsp;Allowance:</td>    
                                                                                                        <td><s:textfield name="splAllowance" id="splAllowance" cssClass="inputTextBlueWages" value="%{splAllowance}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="46"/></td>
                                                                                                        <td class="fieldLabelWages" align="right">Professional Tax:</td>    
                                                                                                        <td><s:textfield name="professionalTax" id="professionalTax" cssClass="inputTextBlueWages" value="%{professionalTax}" onchange="payRollFieldLengthValidator(this);" readonly="true"/></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">LongTermBonus:</td>    
                                                                                                        <td><s:textfield name="longTermBonus" id="longTermBonus" cssClass="inputTextBlueWages"  value="%{longTermBonus}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="47"/></td>
                                                                                                        <td class="fieldLabelWages" align="right">Other&nbsp;Deductions:</td>    
                                                                                                        <td><s:textfield name="otherDeductions" id="otherDeductions" cssClass="inputTextBlueWages" value="%{otherDeductions}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="62"/></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">Project&nbsp;Pay:</td>    
                                                                                                        <td><s:textfield name="projectPay" id="projectPay" cssClass="inputTextBlueWages"  value="%{projectPay}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="48"/></td>
                                                                                                        <%-- <td class="fieldLabelWages" align="right">Net&nbsp;Paid:</td>    --%>
                                                                                                        <td class="fieldLabelWages" align="right">Gross&nbsp;Pay:</td>    
                                                                                                        <td><s:textfield name="grossPay" id="grossPay" cssClass="inputTextBlueWages" value="%{grossPay}" onchange="payRollFieldLengthValidator(this);" readonly="true"/></td>
                                                                                                        <td><s:hidden name="netPaidPayroll" id="netPaidPayroll" cssClass="inputTextBlueWages" value="%{netPaidPayroll}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" readonly="true"/></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">Attendance&nbsp;Allow.:</td>    
                                                                                                        <td><s:textfield name="attendanceAllow" id="attendanceAllow" cssClass="inputTextBlueWages" value="%{attendanceAllow}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="49"/></td>
                                                                                                        <td class="fieldLabelWages" align="right">Variable&nbsp;Pay:</td>    
                                                                                                        <td><s:textfield name="variablePay" id="variablePay" cssClass="inputTextBlueWages" value="%{variablePay}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" readonly="true"/></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">OnProjectInd.:</td>    

                                                                                                        <td><s:checkbox name="onProjectInd" id="onProjectInd"
                                                                                                                    value="%{onProjectInd}" 
                                                                                                                    theme="simple" onchange=""  tabindex="49"/> <s:textfield name="onProjectIndValue1" id="onProjectIndValue1" cssClass="inputTextBlueWagesSmall" value="%{onProjectIndValue1}" onkeyup="isNumberKey(this);" onchange="payRollFieldLengthValidator(this);" readonly=" " tabindex="50"/>
                                                                                                            <s:textfield name="onProjectIndValue2" id="onProjectIndValue2" cssClass="inputTextBlueWagesSmall" value="%{onProjectIndValue2}" onchange="payRollFieldLengthValidator(this);"  onkeyup="isNumberKey(this);" readonly=" "  tabindex="51"/></td>

                                                                                                        <td class="fieldLabelWages" align="right">Total&nbsp;Cost:</td>    
                                                                                                        <td><s:textfield name="totalCost" id="totalCost" cssClass="inputTextBlueWages" value="%{totalCost}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="63"/>
                                                                                                            <div id="totalCostMatchDv"></div></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">OnsiteInd.:</td>    

                                                                                                        <td >  <s:checkbox name="onsiteInd" id="onsiteInd"
                                                                                                                    value="" 
                                                                                                                    theme="simple" tabindex="52"/> 
                                                                                                            <s:textfield name="onsiteIndV" id="onsiteIndV" cssClass="inputTextBlueWagesMedium" value="%{onsiteIndV}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" readonly=" " tabindex="53"/></td>
                                                                                                        <td class="fieldLabelWages" align="right">DatePay&nbsp;Revised:</td>    
                                                                                                        <td><s:textfield name="datePayRevised" id="datePayRevised" cssClass="inputTextBlueWages" onchange="isValidDate(this)" value="%{datePayRevised}" tabindex="64"/>


                                                                                                        </td>
                                                                                                        </tr>
                                                                                                    </table>
                                                                                                </div>
                                                                                            </s:form>

                                                                                        </div>
                                                                                        <div id="insuranceSavingsTab" class="tabcontent" > 
                                                                                            <%
                                                                                                if (session.getAttribute("resultMessage") != null) {
                                                                                                    out.println(session.getAttribute("resultMessage"));
                                                                                                    session.removeAttribute("resultMessage");
                                                                                                }

                                                                                            %>
                                                                                            <br>
                                                                                            <s:form name="insuranceSavingsTab" action="insuranceSavingsUpdate" theme="simple" >
                                                                                                <div align="center" style=" border-radius: 25px;
                                                                                                     border: 2px solid #3e93d4;
                                                                                                     padding: 20px;
                                                                                                     width: 780px;
                                                                                                     height: auto;
                                                                                                     margin-right: 30%;
                                                                                                     position: relative;background-color:#c0c0c0; ">
                                                                                                    <table cellpadding="1" cellspacing="1" border="0" width="80%">
                                                                                                        <%-- <tr>
                                                                                                             <td colspan="6"align="right">
                                                                                                                 <div style="margin-right: -13%;">
                                                                                                                     <table>
                                                                                                                         <tr>
                                                                                                                             <td  >
                                                                                                                                 <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)" tabindex="">
                                                                                                                             </td>  
                                                                                                                             <td >
                                                                                                                                 <div id="new3">
                                                                                                                                     <input type="button" class="buttonBg" value="New" id="new33" onclick="confirmForNewPayrollAdd();" tabindex=""/>
                                                                                                                                 </div>
                                                                                                                             </td>
                                                                                                                            <td >
                                                                                                                                 <div id="save3" >
                                                                                                                                     <input type="button" class="buttonBg" id="save33" value="Save All" onclick="addPayrollDetails();" tabindex="82"/>
                                                                                                                                 </div>
                                                                                                                                 <div id="update3" >
                                                                                                                                     <input type="button" class="buttonBg" id="update33" value="Update All" onclick="addPayrollDetails();" tabindex="82"/>
                                                                                                                                 </div>
                                                                                                                             </td> 

                                                                                                                    </tr>
                                                                                                                </table>
                                                                                                            </div>
                                                                                                        </td>
                                                                                                    </tr>--%>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right"></td>                           

                                                                                                        <td colspan="2"><font class="navigationTextWages"><s:property value="%{employeeName}"/></font> </td>        
                                                                                                        <td class="fieldLabelWages"  align="left" colspan="">Yearly&nbsp;Gross&nbsp;:</td>                           

                                                                                                        <td colspan="2"><s:textfield name="expectedYearlyCost" id="expectedYearlyCost" cssClass="inputTextBlueWagesReg" value="%{expectedYearlyCost}" onchange="" onkeyup="isNumberKey(this);" tabindex="" readonly="true"/></td>               
                                                                                                        </tr>
                                                                                                        <%--</tr>
                                                                                                      <tr>
                                                                                                           <td class="fieldLabelWages"  align="right" >Prev&nbsp;YTD&nbsp;Salary:</td>                           

                                                                            <td colspan="5"><s:textfield name="prevYtdSalary" id="prevYtdSalary" cssClass="inputTextBlueWagesReg" value="%{prevYtdSalary}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="67"/></td>    
                                                                        </tr> 
                                                                        <tr>--%>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right" >Emp&nbsp;Savings1&nbsp;:</td>

                                                                                                        <td ><s:textfield name="empSaving1" id="empSaving1" cssClass="inputTextBlueWagesReg" value="%{empSaving1}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="68" readonly="true"/></td>
                                                                                                        <td class="fieldLabelWages"  align="left" colspan="3">Emp&nbsp;Savings2&nbsp;:</td>                           

                                                                                                        <td ><s:textfield name="empSaving2" id="empSaving2" cssClass="inputTextBlueWagesReg" value="%{empSaving2}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="77" readonly="true"/></td>               


                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right" >Health&nbsp;Insurance(80d)&nbsp;:</td>

                                                                                                        <td ><s:textfield name="empSaving3" id="empSaving3" cssClass="inputTextBlueWagesReg" value="%{empSaving3}" onchange="" onkeyup="isNumberKey(this);" tabindex="68" readonly="true"/></td>
                                                                                                        <td class="fieldLabelWages"  align="left" colspan="3">Interest&nbsp;on&nbsp;Education&nbsp;Loan(80E):</td>                           

                                                                                                        <td ><s:textfield name="empSaving4" id="empSaving4" cssClass="inputTextBlueWagesReg" value="%{empSaving4}" onchange="" onkeyup="isNumberKey(this);" tabindex="77" readonly="true"/></td>               


                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages"  align="right" >HR&nbsp;Exemption:</td>

                                                                                                        <td ><s:textfield name="empSaving5" id="empSaving5" cssClass="inputTextBlueWagesReg" value="%{empSaving5}" onchange="" onkeyup="isNumberKey(this);" tabindex="68" readonly="true"/></td>
                                                                                                        </tr>
                                                                                                    </table>
                                                                                                </div>
                                                                                            </s:form>
                                                                                        </div>
                                                                                        <!-- insurance Savings Tab  End-->
                                                                                        <!-- other Details  Tab  Start -->
                                                                                        <div id="otherDetailsTab" class="tabcontent" > 
                                                                                            <br>
                                                                                            <s:form name="otherDetailsTab" action="otherDetailsUpdate" theme="simple" >
                                                                                                <div align="center" style=" border-radius: 25px;
                                                                                                     border: 2px solid #3e93d4;
                                                                                                     padding: 20px;
                                                                                                     width: 700px;
                                                                                                     height: 400px;
                                                                                                     margin-right: 30%;
                                                                                                     position: relative;background-color:#c0c0c0; ">
                                                                                                    <table cellpadding="1" cellspacing="1" border="0" width="100%">


                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages">Wage&nbsp;&nbsp;Comments&nbsp;:</td>  
                                                                                                        <td colspan="5"><s:textarea name="wagecomments" id="wagecomments" cols="105" rows="3" cssClass="inputTextArea" value="%{wagecomments}" onchange="payRollFieldLengthValidator(this);"
                                                                                                                    tabindex="84"/></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages">Wage&nbsp;&nbsp;Comments1&nbsp;:</td>  
                                                                                                        <td colspan="5"><s:textarea name="wagecomments1" id="wagecomments1" cols="105" rows="3" cssClass="inputTextArea" value="%{wagecomments1}" onchange="payRollFieldLengthValidator(this);"
                                                                                                                    tabindex="85" /></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages">General&nbsp;&nbsp;Comments&nbsp;:</td>  
                                                                                                        <td colspan="5"><s:textarea name="generalcomments" id="generalcomments" cols="105" rows="3" cssClass="inputTextArea" value="%{generalcomments}" onchange="payRollFieldLengthValidator(this);"
                                                                                                                    tabindex="86"/></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                        <td class="fieldLabelWages">Reference&nbsp;&nbsp;Details&nbsp;:</td>  
                                                                                                        <td colspan="5"><s:textarea name="referencecomments" id="referencecomments" cols="105" rows="1" cssClass="inputTextArea" value="%{referencecomments}" onchange="payRollFieldLengthValidator(this);"
                                                                                                                    tabindex="87" /></td>
                                                                                                        </tr>

                                                                                                    </table>
                                                                                                </div>
                                                                                            </s:form>
                                                                                        </div>
                                                                                        <div id="tdsTab" class="tabcontent" > 
                                                                                            <br>
                                                                                            <s:form action="" name="tdsDetails" theme="simple">

                                                                                                <div align="center" style=" border-radius: 25px;
                                                                                                     border: 2px solid #3e93d4;
                                                                                                     padding: 20px;
                                                                                                     width: 700px;
                                                                                                     height: 850px;
                                                                                                     margin-right: 30%;
                                                                                                     position: relative;background-color:#c0c0c0; ">
                                                                                                    <table cellpadding="1" cellspacing="1" border="0" width="100%" align="center">
                                                                                                        <tr>
                                                                                                        <td>

                                                                                                            <table width="80%" border="0" align="center">


                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages"  align="right"></td>                           

                                                                                                                <td colspan=""></td>  
                                                                                                                <td class="fieldLabelWages"  align="right">Expected&nbsp;yearly&nbsp;total&nbsp;cost: </td>                           

                                                                                                                <td colspan="3" align="right"><s:textfield name="expectedYearlyCost" id="expectedYearlyCost" cssClass="inputTextBlueWages" value="%{expectedYearlyCost}" onkeyup="isNumberKey(this);" tabindex=""/></td>     
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="left" colspan="2"></td>    

                                                                                                                <td class="fieldLabelWages" align="left" colspan="2"><h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Investment</h2></td>    

                                                                                                                <td class="fieldLabelWages" align="left" colspan="2"><h2>Deductible</h2></td>    

                                                                                                                </tr> 
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="left" colspan="2"><h2>Investment&nbsp;U/S&nbsp;80C</h2></td>   
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">Life&nbsp;Insurance&nbsp;Premium:</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="lifeInsurancePremium" id="lifeInsurancePremium" cssClass="inputTextBlueWages" value="%{lifeInsurancePremium}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="90" /></td>
                                                                                                                <td colspan="2" align=""></td>

                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">Housing&nbsp;Loan&nbsp;Repayment:</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="housingLoanRepay" id="housingLoanRepay" cssClass="inputTextBlueWages" value="%{housingLoanRepay}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="92"/></td>
                                                                                                                <td colspan="2" align=""></td>

                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">NSC:</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="nscTds" id="nscTds" cssClass="inputTextBlueWages" value="%{nscTds}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="93"/></td>

                                                                                                                <td colspan="2" align=""></td>
                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">PPF&nbsp;Contribution:</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="ppfContribution" id="ppfContribution" cssClass="inputTextBlueWages" value="%{ppfContribution}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="94"/></td>
                                                                                                                <td colspan="2" align=""></td>

                                                                                                                </tr> 
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">Tution&nbsp;Fee:</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="tutionFee" id="tutionFee" cssClass="inputTextBlueWages" value="%{tutionFee}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="95"/></td>

                                                                                                                <td colspan="2" align=""></td>
                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">ELSS:</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="elss" id="elss" cssClass="inputTextBlueWages" value="%{elss}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="96"/></td>

                                                                                                                <td colspan="2" align=""></td>
                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">Post&nbsp;Office&nbsp;Term&nbsp;Deposit:</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="postOfficeTerm" id="postOfficeTerm" cssClass="inputTextBlueWages" value="%{postOfficeTerm}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="97"/></td>
                                                                                                                <td colspan="2" align=""></td>

                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">Bank&nbsp;Deposit&nbsp;(Tax&nbsp;Saving):</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="bankDepositTax" id="bankDepositTax" cssClass="inputTextBlueWages" value="%{bankDepositTax}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="98"/></td>
                                                                                                                <td colspan="2" align=""></td>

                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">LIC from Salary:</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="licFromSal" id="licFromSal" cssClass="inputTextBlueWages" value="%{licFromSal}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="99"/></td>

                                                                                                                <td colspan="2" align=""></td>
                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">Others:</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="othersTDS" id="othersTDS" cssClass="inputTextBlueWages" value="%{othersTDS}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="100"/></td>

                                                                                                                <td colspan="2" align=""></td>
                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="" colspan="2"><h2>Investment U/S 80CCC</h2></td>    

                                                                                                                </tr>   

                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">Contribution&nbsp;to&nbsp;PF:</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="contributionToPf" id="contributionToPf" cssClass="inputTextBlueWages" value="%{contributionToPf}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="101"/></td>
                                                                                                                <td colspan="2" align=""></td>

                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="" colspan="2">80CCD-NPS-Employees&nbsp;contribution:</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="npsEmployeeContr" id="npsEmployeeContr" cssClass="inputTextBlueWages" value="%{npsEmployeeContr}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="102"/></td>
                                                                                                                <td colspan="2" align=""></td>

                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="" colspan="2"> Total(max exemptions-1,50,000):</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="totalTds" id="totalTds" cssClass="inputTextBlueWages" value="%{totalTds}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="103"/></td>
                                                                                                                <td colspan="2" align="right"><s:textfield name="totalTdsDeductable" id="totalTdsDeductable" cssClass="inputTextBlueWages" value="%{totalTdsDeductable}" onchange="" onkeyup="isNumberKey(this);" tabindex="104"/></td>


                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="" colspan="2">Interest&nbsp;on&nbsp;borrowed&nbsp;capital-self&nbsp;<br>occupied&nbsp;prop(max exemptions-2,00,000):</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="interstOnBorrowed" id="interstOnBorrowed" cssClass="inputTextBlueWages" value="%{interstOnBorrowed}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="105"/></td>
                                                                                                                <td colspan="2" align="right"><s:textfield name="interstOnBorrowedDeductable" id="interstOnBorrowedDeductable" cssClass="inputTextBlueWages" value="%{interstOnBorrowedDeductable}" onchange="" onkeyup="isNumberKey(this);" tabindex="106"/></td>


                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="" colspan="2"><h2>Medical&nbsp;Insurance&nbsp;U/S80d</h2></td>    

                                                                                                                </tr>   

                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">Insurance&nbsp;for&nbsp;parents(Paid&nbsp;for&nbsp;senior&nbsp;citizens)<br>(max exemptions-20,000):</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="insuranceForParents" id="insuranceForParents" cssClass="inputTextBlueWages" value="%{insuranceForParents}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="107"/></td>
                                                                                                                <td colspan="2" align="right"><s:textfield name="insuranceForParentsDeduc" id="insuranceForParentsDeduc" cssClass="inputTextBlueWages" value="%{insuranceForParentsDeduc}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="108"/></td>


                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="" colspan="2">Insurance&nbsp;other(self,spouse and children)<br>(max exemptions-15,000):</td>    


                                                                                                                <td colspan="2" align="right"><s:textfield name="insuranceOthers" id="insuranceOthers" cssClass="inputTextBlueWages" value="%{insuranceOthers}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="109"/></td>
                                                                                                                <td colspan="2" align="right"><s:textfield name="insuranceOthersDeduc" id="insuranceOthersDeduc" cssClass="inputTextBlueWages" value="%{insuranceOthersDeduc}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="110"/></td>


                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="" colspan="2">Interest&nbsp;on&nbsp;education&nbsp;loan&nbsp;repayment(80E):</td>    

                                                                                                                <td colspan="2" align=""></td>
                                                                                                                <td colspan="2" align="right"><s:textfield name="interstOnEdu" id="interstOnEdu" cssClass="inputTextBlueWages" value="%{interstOnEdu}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="111"/></td>



                                                                                                                </tr>  
                                                                                                                <tr>
                                                                                                                <td class="fieldLabelWages" align="" colspan="2">HR&nbsp;Exemptions:</td>    

                                                                                                                <td colspan="2" align="right"><s:textfield name="interstOnHrAssumptionsInv" id="interstOnHrAssumptionsInv" cssClass="inputTextBlueWages" value="%{interstOnHrAssumptionsInv}" onchange="payRollFieldLengthValidator(this);showHouseRentDetails();computeHrExemtionsDed();" onkeyup="isNumberKey(this);" tabindex="112"/></td>

                                                                                                                <td colspan="2" align="right"><s:textfield name="interstOnHrAssumptions" id="interstOnHrAssumptions" cssClass="inputTextBlueWages" value="%{interstOnHrAssumptions}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="113"/></td>


                                                                                                                </tr>  

                                                                                                                <tr  id ="houseOwnerDetails1" style="display: none">
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">House&nbsp;Owner&nbsp;Name:</td>    
                                                                                                                <td colspan="2" align="right"><s:textfield name="houseOwnerName" id="houseOwnerName" cssClass="inputTextBlueWages" value="%{houseOwnerName}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex=""/></td>
                                                                                                                <td colspan="2" align=""></td>
                                                                                                                </tr>
                                                                                                                <tr  id ="houseOwnerDetails2" style="display: none">          
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">House&nbsp;Owner&nbsp;PAN&nbsp;No.:</td>    
                                                                                                                <td colspan="2" align="right"><s:textfield name="houseOwnerPAN" id="houseOwnerPAN" cssClass="inputTextBlueWages" value="%{houseOwnerPAN}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex=""/></td>
                                                                                                                <td colspan="2" align=""></td>
                                                                                                                </tr>
                                                                                                                <tr  id ="houseOwnerMobileNumber" style="display: none">
                                                                                                                <td class="fieldLabelWages" align="right" colspan="2">House&nbsp;Owner&nbsp;Mobile&nbsp;No:</td>    
                                                                                                                <td colspan="2" align="right"><s:textfield name="houseOwnerMobile" id="houseOwnerMobile" cssClass="inputTextBlueWages" value="%{houseOwnerMobile}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex=""/></td>
                                                                                                                <td colspan="2" align=""></td>
                                                                                                                </tr>



                                                                                                            </table>

                                                                                                        </td>
                                                                                                        </tr>
                                                                                                    </table>
                                                                                                </div>
                                                                                            </s:form>
                                                                                        </div>

                                                                                    </div>
                                                                                </td>
                                                                                </tr>

                                                                            </table>

                                                                        </s:form>
                                                                    </div>
                                                                    </div>


                                                                    <div id="WagesHistory" class="tabcontent" style="display: block;" >  
                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">


                                                                            <!-- new div for account list by priority start -->
                                                                            <tr>
                                                                            <td class="homePortlet" valign="top">
                                                                                <div class="portletTitleBar">
                                                                                    <div class="portletTitleLeft">Wages History</div>
                                                                                    <div class="portletIcons">
                                                                                        <a href="javascript:animatedcollapse.hide('WagesHistoryDiv')" title="Minimize">
                                                                                            <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                        <a href="javascript:animatedcollapse.show('WagesHistoryDiv')" title="Maximize">
                                                                                            <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                        </a>
                                                                                    </div>
                                                                                    <div class="clear"></div>
                                                                                </div>
                                                                                <div id="WagesHistoryDiv" style="background-color:#ffffff">
                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                        <tr>
                                                                                        <td width="40%" valign="top" align="center">
                                                                                            <s:form action="getPayrollHistory" theme="simple" name="leaveReports" id="leaveReports">  

                                                                                                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                    <tr align="right">
                                                                                                    <td class="headerText" colspan="9">
                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                    </td>
                                                                                                    </tr>

                                                                                                    <tr>
                                                                                                    <td>
                                                                                                        <%
                                                                                                            if (session.getAttribute("wagesHistoryResultMessage") != null) {
                                                                                                                out.println(session.getAttribute("wagesHistoryResultMessage"));
                                                                                                                session.removeAttribute("wagesHistoryResultMessage");
                                                                                                            }

                                                                                                        %>
                                                                                                    </td>
                                                                                                    </tr>
                                                                                                    <tr>
                                                                                                    <td>
                                                                                                        <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                                                            <tr>
                                                                                                            <td class="fieldLabel">Emp&nbsp;Name:</td>
                                                           
                                                                <td >
                                                                      <s:textfield name="employeeName" id="empNameWage"  autocomplete="off" cssClass="inputTextBlue" onkeyup="getEmployeeNames1();"/><span id="empNoSpan"></span><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessageWage"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popupWage">
                                                                                                    <table id="completeTableWage" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                            <s:hidden name="empNameById" id="empNameById" value="%{empNameById}"/>
                                                    </td>  
                                                                                                            
                                                                                                            <td class="fieldLabel">Employee No.</td>
                                                                                                            <td><s:textfield name="empNo" id="wagesHistoryempNo" cssClass="inputTextBlue" value="%{empNo}" onchange="firstNameValidate(document.frmEmpSearch.firstName.value);"/></td>





                                                                                                            <td colspan="2" width="200px" align="right">

                                                                                                                <input type="button"  value="Search" Class="button_payroll" onClick="getWagesHistory();"/>
                                                                                                            </td>

                                                                                                            </tr>



                                                                                                        </table>
                                                                                                    </td>
                                                                                                    </tr>
                                                                                                </table>
                                                                                            </s:form>
                                                                                        </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                        <td align="center"><span id="wagesHistoryresultmsg" style="display: none"><font color="red" size="2px">no records found..</font></span></td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                        <td align="center"><span id="wagesHistoryloadingMessage" style="display: none"><font color="red" size="2px">loading please wait...</font></span></td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                        <td>

                                                                                            <br>
                                                                                            <div id="WagesHistoryList" style="display: inline">
                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                <div id="pagWages" style=" display: none;">
                                                                                                <label style="margin-left:5%; position: relative;color:blue"> Display <select id="paginationOptionWages" class="disPlayRecordsCss" onchange="pagerOptionWages();" style="width: auto">

                                                                <option>15</option>
                                                                <option>20</option>
                                                                <option>25</option>
                                                            </select> Rows</label></div>
                                                                                                <table id="tblWagesHistoryUpdate" cellpadding='0' cellspacing='0' border='0' class="gridTable" width='90%' align="center">
                                                                                                    <!--COLGROUP ALIGN="left" >
                                                                                                        <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                                <COL width="20%">
                                                                                                                    <COL width="10%"-->

                                                                                                                        </table>
                                                                                                                        <br>
                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                        </div>
                                                                                                                        <div id="menuDiv" align="center" style="display:none;position: absolute;top: 120px; left: 180px;">
                                                                                                                            <table border="1" bgcolor="#FBFBB1" width="100">
                                                                                                                                <tr>
                                                                                                                                <td>
                                                                                                                                    <a id="detailsLink"><font class="fieldLabel">Details</font></a>
                                                                                                                                    <br>
                                                                                                                                    <!--<a><font class="fieldLabel">Send Alert</font></a>
                                                                                                                                    <br>-->
                                                                                                                                    <a id="cancelLink"><font class="fieldLabel">Cancel</font></a>
                                                                                                                                </td>
                                                                                                                                </tr>
                                                                                                                            </table>
                                                                                                                        </div>
                                                                                                                        </td>
                                                                                                                        </tr>

                                                                                                                        </table>

                                                                                                                        </div>
                                                                                                                        </td>
                                                                                                                        </tr>

                                                                                                                        <!-- new div for account list by priority end -->

                                                                                                                        </table>
                                                                                                                        <div id="wagesHistoryGridOverlay" ></div>
                                                                                                                        <div id="wagesHistoryGridOverlayMain" style="width: 900px;top :-40px; left :15%;"  align="center">
                                                                                                                            <s:form theme="simple" >
                                                                                                                                <table style="width: 100%;">

                                                                                                                                    <tr class="overlayHeader">
                                                                                                                                    <td colspan="2">

                                                                                                                                        <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                                                                                                            Details</font> 
                                                                                                                                        <div style="float: right" class="closeButton" align="right">
                                                                                                                                            <a href="#" onclick="toggleWagesHistoryRequirement('0','0');" style="width: 50px;background: red;color:white;font-family: myHeaderFonts;text-decoration: none; "><b>X</b></a>
                                                                                                                                        </div> 
                                                                                                                                    </td>
                                                                                                                                    </tr>
                                                                                                                                    <tr>
                                                                                                                                    <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px 5px 5px;">
                                                                                                                                        <ul id="accountTabs1" class="shadetabs" >
                                                                                                                                            <li ><a href="#" class="selected" rel="employeeDetailss" tabindex="1" >Employee Details</a></li>

                                                                                                                                            <li ><a href="#" rel="payrollDetailss" tabindex="30">Payroll Details</a></li>
                                                                                                                                            <li ><a href="#" rel="actualDetailssTab" tabindex="52" >Actual Details</a></li>
                                                                                                                                            <li ><a href="#" rel="yearAndDateTabb" tabindex="81">Year and Date</a></li>
                                                                                                                                            <%-- <li ><a href="#" rel="tdsTabb" tabindex="81">TDS</a></li> --%>
                                                                                                                                        </ul>
                                                                                                                                        <div  style="border:1px solid gray; width:850px;height: 510px; overflow:auto; margin-bottom: 1em;">

                                                                                                                                            <!-- employee Details end -->
                                                                                                                                            <br>
                                                                                                                                            <div id="wagesResultMessage" style="font-size: 15px;"></div>
                                                                                                                                            <div id="loadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                                                                                                                            <div id="loadingMessageForFreeze" style="color: red;font-size: 15px;display: none;"></div>

                                                                                                                                            <!-- Employee details tab -->
                                                                                                                                            <div id="employeeDetailss" class="tabcontent" >
                                                                                                                                                <s:form action="" name="employeeWageDetails" theme="simple" id="employeeWageDetails">

                                                                                                                                                    <div align="right" style=" border-radius: 25px;
                                                                                                                                                         border: 2px solid #3e93d4;
                                                                                                                                                         padding: 20px;
                                                                                                                                                         width: 750px;
                                                                                                                                                         height: 550px;
                                                                                                                                                         margin-right: 30%;
                                                                                                                                                         position: relative;background-color:#c0c0c0; ">

                                                                                                                                                        <table cellpadding="1" cellspacing="1" border="0" width="80%" align="left" bgcolor="">

                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Employee&nbsp;Name:</td>    
                                                                                                                                                            <td><s:textfield name="employeeName" id="whemployeeName" cssClass="inputTextBlueWages" value="%{employeeName}" onchange="payRollFieldLengthValidator(this);"  tabindex="" readonly="true"/></td>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Title&nbsp;:</td>    
                                                                                                                                                            <td><s:textfield name="titleId" id="whtitleId" cssClass="inputTextBlueWages" value="%{titleId}" onchange="" tabindex="" readonly="true"/></td>
                                                                                                                                                            </tr>  
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages"  align="right" >Classification&nbsp;:</td>
                                                                                                                                                            <td ><s:textfield
                                                                                                                                                                    name="classificationId" 
                                                                                                                                                                    id="whclassificationId1"

                                                                                                                                                                    cssClass="inputSelectWages" tabindex="" contenteditable="true"/></td>
                                                                                                                                                            <td class="fieldLabelWages"  align="right">Payment&nbsp;Type:</td>    
                                                                                                                                                            <td><s:textfield
                                                                                                                                                                    name="paymentType" 
                                                                                                                                                                    id="whpaymentType"
                                                                                                                                                                    cssClass="inputSelectWages" tabindex="" contenteditable="true"/></td>
                                                                                                                                                            </tr>
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages"  align="right">PayPeriodStartDate:</td>

                                                                                                                                                            <td><s:textfield name="payPeriodStartDate" id="whpayPeriodStartDate" cssClass="inputTextBlueWages" value="%{payPeriodStartDate}" onchange="isValidDate(this)" tabindex="2"/>
                                                                                                                                                            </td>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Bank&nbsp;Name:</td>    
                                                                                                                                                            <td><s:textfield
                                                                                                                                                                    name="bankName" 
                                                                                                                                                                    id="whbankName"
                                                                                                                                                                    cssClass="inputSelectWages" tabindex="" contenteditable="true"/></td>
                                                                                                                                                            </tr>
                                                                                                                                                            <tr>
                                                                                                                                                                <%-- <td class="fieldLabelWages" align="right">NetPaid:</td>     --%>
                                                                                                                                                            <td><s:hidden name="netPaid" id="whnetPaid" cssClass="inputTextBlueWagesColoredTextBoxes" value="%{netPaid}" onchange="" onkeyup="isNumberKey(this);" tabindex="3"/></td>

                                                                                                                                                            <td class="fieldLabelWages" align="right" colspan="2">Bank&nbsp;Account&nbsp;No:</td>    
                                                                                                                                                            <td><s:textfield name="bankAccountNo" id="whbankAccountNo" cssClass="inputTextBlueWages" value="%{bankAccountNo}" onchange="payRollFieldLengthValidator(this);" onkeypress="" tabindex="" readonly="true"/></td>
                                                                                                                                                            </tr>  
                                                                                                                                                            <tr>
                                                                                                                                                                <%-- <td class="fieldLabelWages" align="right">GrossPay:</td>    --%>
                                                                                                                                                            <td><s:hidden name="grossPay" id="whgrossPay" cssClass="inputTextBlueWagesColoredTextBoxes" value="%{grossPay}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="4"/></td>

                                                                                                                                                            <%--  <td class="fieldLabel"  align="right" >Freeze&nbsp;Payroll:</td>
                                                                                                                                                            <td > <s:checkbox name="freezePayroll" id="freezePayroll"
                                                                                                                                                            value="%{freezePayroll}" 
                                                                                                                                                            theme="simple" tabindex="11" /> </td> --%>

                                                                                                                                                            </tr>
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages"  align="right">PayrollDate:</td>

                                                                                                                                                            <td colspan="3"><s:textfield name="payrollDate" id="whpayrollDate" cssClass="inputTextBlueWages" value="%{payrollDate}" onchange="isValidDate(this)" tabindex="5"/></td>
                                                                                                                                                            <%--    <td class="fieldLabelWages" align="right">PayRunId:</td>    
                                                                                                                                                            <td></td> --%>
                                                                                                                                                            <s:hidden name="payRunId" id="whpayRunId" cssClass="inputTextBlueWages" value="%{payRunId}" onchange="" tabindex="13"/>
                                                                                                                                                            </tr>   
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages"  align="right">Actual&nbsp;TDS:</td>

                                                                                                                                                            <td colspan="3"><s:textfield name="actualTds" id="whactualTds" cssClass="inputTextBlueWages" value="%{actualTds}" onchange="" tabindex="" readOnly="true"/></td>

                                                                                                                                                            </tr>   
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages"  align="right">Tax&nbsp;Exemption:</td>

                                                                                                                                                            <td colspan="3"><s:textfield name="incomeTax_TE" id="whincomeTax_TE" cssClass="inputTextBlueWages" value="%{incomeTax_TE}" onchange="" tabindex="" readOnly="true"/></td>

                                                                                                                                                            </tr>   
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages" align="right">TDS:</td>    
                                                                                                                                                            <td><s:textfield name="tds" id="whtds" cssClass="inputTextBlueWages" value="%{tds}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="6" readOnly="true"/></td>

                                                                                                                                                            <td class="fieldLabelWages" align="right">PayrollId:</td>    
                                                                                                                                                            <td><s:textfield name="employerId" id="whemployerId" cssClass="inputTextBlueWages" value="%{employerId}" onchange="" onkeyup="isNumberKey(this);" tabindex="7"/></td>
                                                                                                                                                            </tr> 
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Days&nbsp;In&nbsp;Month:</td>    
                                                                                                                                                            <td><s:textfield name="daysInMonth" id="whdaysInMonth" cssClass="inputTextBlueWages" value="%{daysInMonth}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="8"/></td>

                                                                                                                                                            <td class="fieldLabelWages" align="right">Ded_Employee_Pf:</td>    
                                                                                                                                                            <td><s:textfield name="dedEmpPf" id="whdedEmpPf" cssClass="inputTextBlueWages" value="%{dedEmpPf}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="9"/></td>
                                                                                                                                                            </tr> 
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Days&nbsp;Worked:</td>    
                                                                                                                                                            <td colspan=""><s:textfield name="daysWorked" id="whdaysWorked" cssClass="inputTextBlueWages" value="%{daysWorked}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="10"/>
                                                                                                                                                                <s:hidden name="daysWorkedHidden" id="daysWorkedHidden" cssClass="inputTextBlueWages" value="%{daysWorked}" onchange="" onkeyup="" tabindex=""/>
                                                                                                                                                            </td>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Ded_Professional_Tax:</td>    
                                                                                                                                                            <td><s:textfield name="dedProfessionalTax" id="whdedProfessionalTax" cssClass="inputTextBlueWages" value="%{dedProfessionalTax}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="11"/></td>
                                                                                                                                                            </tr> 
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Days&nbsp;Project:</td>    
                                                                                                                                                            <td><s:textfield name="daysProject" id="whdaysProject" cssClass="inputTextBlueWages" value="%{daysProject}" onchange="payRollFieldLengthValidator(this);checkForSpace(this);checkWithDaysInMonth(this);" onkeyup="isNumberKey(this);" tabindex="12"/>

                                                                                                                                                                <s:hidden name="daysProjectHiddenValue" id="daysProjectHiddenValue" cssClass="inputTextBlueWages" value="%{daysProject}" onchange="" onkeyup="" tabindex=""/>
                                                                                                                                                            </td>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Ded_Income_Tax:</td>    
                                                                                                                                                            <td><s:textfield name="dedIncomeTax" id="whdedIncomeTax" cssClass="inputTextBlueWages" value="%{dedIncomeTax}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="13"/></td>
                                                                                                                                                            </tr> 
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Days&nbsp;Vacation:</td>    
                                                                                                                                                            <td><s:textfield name="daysVacation" id="whdaysVacation" cssClass="inputTextBlueWages" value="%{daysVacation}" onchange="payRollFieldLengthValidator(this);checkForSpace(this);checkTotalDays(this);" onkeyup="isNumberKey(this);" tabindex="14" />
                                                                                                                                                                <s:hidden name="daysVacationHidden" id="whdaysVacationHidden" cssClass="inputTextBlueWages" value="%{daysVacation}" onchange="" onkeyup="" tabindex="" />
                                                                                                                                                            </td>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Ded_Corporate_Loan:</td>    
                                                                                                                                                            <td><s:textfield name="dedCorporateLoan" id="whdedCorporateLoan" cssClass="inputTextBlueWages" value="%{dedCorporateLoan}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="14"/></td>
                                                                                                                                                            </tr> 
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Vacations&nbsp;Available</td>    
                                                                                                                                                            <td><s:textfield name="vactionsAvailable" id="whvactionsAvailable" cssClass="inputTextBlueWages" value="%{vactionsAvailable}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="16" readonly="true"/>

                                                                                                                                                                <s:hidden name="vactionsAvailableHidden" id="vactionsAvailableHidden" cssClass="inputTextBlueWages" value="%{vactionsAvailable}"  /></td>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Ded_Life:</td>    
                                                                                                                                                            <td><s:textfield name="dedLife" id="whdedLife" cssClass="inputTextBlueWages" value="%{dedLife}" onchange="checkForSpace(this);"  onkeyup="isNumberKey(this);" tabindex="17"/></td>
                                                                                                                                                            </tr> 

                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Days&nbsp;Holidays:</td>    
                                                                                                                                                            <td><s:textfield name="daysHolidays" id="whdaysHolidays" cssClass="inputTextBlueWages" value="%{daysHolidays}" onchange="payRollFieldLengthValidator(this);checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="18"/></td>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Ded_Health:</td>    
                                                                                                                                                            <td><s:textfield name="dedHealth" id="whdedHealth" cssClass="inputTextBlueWages" value="%{dedHealth}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="19"/></td>
                                                                                                                                                            </tr> 
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Leaves&nbsp;Applied:</td>    
                                                                                                                                                            <td><s:textfield name="leavesApplied" id="whleavesApplied" cssClass="inputTextBlueWages" value="%{leavesApplied}" onchange=""  onkeyup="" tabindex="" readOnly="true"/>
                                                                                                                                                                <s:hidden name="leavesAppliedHidden" id="whleavesAppliedHidden" cssClass="inputTextBlueWages" value="%{leavesApplied}" onchange="changeDaysVacationValues(this);"  onkeyup="" tabindex=""/>
                                                                                                                                                            </td>

                                                                                                                                                            <td class="fieldLabelWages" align="right">Ded_Others:</td>    
                                                                                                                                                            <td><s:textfield name="dedOthers" id="whdedOthers" cssClass="inputTextBlueWages" value="%{dedOthers}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="20"/></td>
                                                                                                                                                            </tr> 
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Days&nbsp;Weekends:</td>    
                                                                                                                                                            <td colspan="3"><s:textfield name="daysWeekends" id="whdaysWeekends" cssClass="inputTextBlueWages" value="%{daysWeekends}" onchange="payRollFieldLengthValidator(this);checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="21"/></td>
                                                                                                                                                            </tr>
                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages"  align="right" >Freeze&nbsp;Payroll:</td>
                                                                                                                                                            <td > <s:checkbox name="freezePayroll" id="whfreezePayroll"
                                                                                                                                                                        value="%{freezePayroll}" 
                                                                                                                                                                        theme="simple" tabindex="22" onchange="changeDorepayment();"/> </td>
                                                                                                                                                            <td class="fieldLabelWages"  align="right" >Do&nbsp;Repayment:</td>
                                                                                                                                                            <td > <s:checkbox name="doRepaymentFlag" id="whdoRepaymentFlag"
                                                                                                                                                                        value="%{doRepaymentFlagVal}" 
                                                                                                                                                                        theme="simple" tabindex="23" onchange="changeRepayComments();"/> </td>


                                                                                                                                                            </tr>
                                                                                                                                                            <tr id ="repaymentRow" style="display: none">
                                                                                                                                                            <td class="fieldLabelWages" align="right">Repayment&nbsp;Comments&nbsp;<font color="red" size="2px">*</font>:</td>  
                                                                                                                                                            <td colspan="3"><s:textarea name="repaymentComments" id="whrepaymentComments" cols="75" rows="3" cssClass="inputTextArea" value="%{repaymentComments}" onchange="payRollFieldLengthValidator(this);" tabindex=""/></td>
                                                                                                                                                            </tr>
                                                                                                                                                        </table>
                                                                                                                                                    </div>
                                                                                                                                                </s:form>

                                                                                                                                            </div>
                                                                                                                                            <!-- Employee details tab end-->             
                                                                                                                                            <!-- payroll Details START -->

                                                                                                                                            <div id="payrollDetailss" class="tabcontent"  >   

                                                                                                                                                <s:form action="" name="payrollDetails" theme="simple">
                                                                                                                                                    <div align="right" style=" border-radius: 25px;
                                                                                                                                                         border: 2px solid #3e93d4;
                                                                                                                                                         padding: 20px;
                                                                                                                                                         width: 530px;
                                                                                                                                                         height: 400px;
                                                                                                                                                         margin-right: 30%;position: relative;background-color:#c0c0c0; ">
                                                                                                                                                        <table cellpadding="1" cellspacing="1" border="0" width="70%" align="left" bgcolor="#c0c0c0">



                                                                                                                                                            <tr>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Basic:</td>    
                                                                                                                                                            <td><s:textfield name="basic" id="whbasic" cssClass="inputTextBlueWages" value="%{basic}"  onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="31"/></td>
                                                                                                                                                            <td class="fieldLabelWages" align="right">Maid Services:</td>    
                                                                                                                                                            <td><s:textfield name="maidServices" id="whmaidServices" cssClass="inputTextBlueWages" value="%{maidServices}"  onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="40"/></td>
                                                                                                                                                            </tr>
                                                                                                                                                            <tr><td class="fieldLabelWages" align="right">DA:</td>    
                                                                                                                                                            <td><s:textfield name="da" id="whda" cssClass="inputTextBlueWages" value="%{da}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" readonly="true"/></td>
                                                                                                                                                            <td class="fieldLabelWages"  align="right">Entertainment:</td>    
                                                                                                                                                            <td><s:textfield name="entertainment" id="whentertainment" cssClass="inputTextBlueWages" value="%{entertainment}"  onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="41"/></td>
                                                                                                                                                        </tr><tr>
                                                                                                                                                    <td class="fieldLabelWages"  align="right">HRA:</td>    
                                                                                                                                                    <td><s:textfield name="hra" id="whhra" cssClass="inputTextBlueWages" value="%{hra}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" readonly="true"/></td>
                                                                                                                                                    <td class="fieldLabelWages"  align="right">Kids&nbsp;Education:</td>    
                                                                                                                                                    <td><s:textfield name="kidsEducation" id="whkidsEducation" cssClass="inputTextBlueWages" value="%{kidsEducation}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="42"/></td>
                                                                                                                                                </tr> <tr>
                                                                                                                                            <td class="fieldLabelWages"  align="right">TA:</td>    
                                                                                                                                            <td><s:textfield name="ta" id="whta" cssClass="inputTextBlueWages" value="%{ta}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="32"/></td> 
                                                                                                                                            <td class="fieldLabelWages"  align="right">Vehicle&nbsp;Allowance:</td>    
                                                                                                                                            <td><s:textfield name="vehicleAllowance" id="whvehicleAllowance" cssClass="inputTextBlueWages" value="%{vehicleAllowance}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="43"/></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages"  align="right">RA:</td>    
                                                                                                                                            <td><s:textfield name="ra" id="whra" cssClass="inputTextBlueWages" value="%{ra}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="33"/></td>
                                                                                                                                            <td class="fieldLabelWages"  align="right">LongTermBonus:</td>    
                                                                                                                                            <td><s:textfield name="longTermBonus" id="whlongTermBonus" cssClass="inputTextBlueWages"  value="%{longTermBonus}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="44"/></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages"  align="right">Life:</td>    
                                                                                                                                            <td><s:textfield name="life" id="whlife" cssClass="inputTextBlueWages" value="%{life}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="34"/></td>
                                                                                                                                            <td class="fieldLabelWages"  align="right">Employer PF:</td>    
                                                                                                                                            <td><s:textfield name="employerPf" id="whemployerPf" cssClass="inputTextBlueWages" value="%{employerPf}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="45"/></td>    
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages"  align="right">Health:</td>    
                                                                                                                                            <td><s:textfield name="health" id="whhealth" cssClass="inputTextBlueWages" value="%{health}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="35"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Spl.&nbsp;Allowance:</td>    
                                                                                                                                            <td><s:textfield name="splAllowance" id="whsplAllowance" cssClass="inputTextBlueWages" value="%{splAllowance}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="46" /></td>
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages"  align="right"></td>   
                                                                                                                                            <td><s:textfield name="health1" id="whhealth1" cssClass="inputTextBlueWages" value="%{health}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="36"/></td>

                                                                                                                                            <td class="fieldLabelWages"  align="right">CCA:</td>    
                                                                                                                                            <td><s:textfield name="cca" id="whcca" cssClass="inputTextBlueWages" value="%{cca}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="47"/></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>

                                                                                                                                            <td class="fieldLabelWages"  align="right">Attendance Allow:</td>    
                                                                                                                                            <td><s:textfield name="attendanceAllow" id="whattendanceAllow" cssClass="inputTextBlueWages" value="%{attendanceAllow}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="37"/></td>
                                                                                                                                            <td class="fieldLabelWages"  align="right">Misc&nbsp;Pay:</td>    
                                                                                                                                            <td><s:textfield name="miscPay" id="whmiscPay" cssClass="inputTextBlueWages" value="%{miscPay}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="48"/></td>

                                                                                                                                            </tr>

                                                                                                                                            <tr>

                                                                                                                                            <td class="fieldLabelWages"  align="right">Project Pay:</td>    
                                                                                                                                            <td><s:textfield name="projectPay" id="whprojectPay" cssClass="inputTextBlueWagesColoredTextBoxes" value="%{projectPay}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="38"/></td>
                                                                                                                                            <td class="fieldLabelWages"  align="right">Employee&nbsp;PF:</td>    
                                                                                                                                            <td><s:textfield name="employeePfPayRollDetails" id="whemployeePfPayRollDetails" cssClass="inputTextBlueWages" value="%{employeePfPayRollDetails}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="49"/></td>
                                                                                                                                            </tr>

                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages"  align="right" >Classification&nbsp;:</td>
                                                                                                                                            <td><s:textfield
                                                                                                                                                    name="classificationId" 
                                                                                                                                                    id="whclassificationId"
                                                                                                                                                    cssClass="inputSelectWages" tabindex="39" readonly="true"/></td>

                                                                                                                                            <td class="fieldLabelWages"  align="right">Gross&nbsp;Pay:</td>    
                                                                                                                                            <td><s:textfield name="grossPayPayRollDetails" id="whgrossPayPayRollDetails" cssClass="inputTextBlueWagesColoredTextBoxes" value="%{grossPay}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="50"/></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Employer&nbsp;ESI&nbsp;:</td>    
                                                                                                                                            <td><s:textfield name="employeresi" id="whhemployeresi" cssClass="inputTextBlueWages" value="%{employeresi}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="58"  /></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Employee&nbsp;ESI&nbsp;:</td>    
                                                                                                                                            <td><s:textfield name="employeeesi" id="whhemployeeesi" cssClass="inputTextBlueWages" value="%{employeeesi}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="59" /></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                            <td colspan="3" class="fieldLabelWages"  align="right">Variable&nbsp;Pay:</td>                                            
                                                                                                                                            <td colspan="1"><s:textfield name="variablePay" id="whvariablePay" cssClass="inputTextBlueWages" value="%{variablePay}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="51"/></td>
                                                                                                                                            </tr>

                                                                                                                                        </table>
                                                                                                                                    </div>
                                                                                                                                </s:form>
                                                                                                                            </div>

                                                                                                                            <!-- payroll Details END -->
                                                                                                                            <!-- Actual Details Tab -->
                                                                                                                            <div id="actualDetailssTab" class="tabcontent" > 
                                                                                                                                <s:form action="" name="employeeActualWageDetails" theme="simple">
                                                                                                                                    <div align="right" style=" border-radius: 25px;
                                                                                                                                         border: 2px solid #3e93d4;
                                                                                                                                         padding: 20px;
                                                                                                                                         width: 630px;
                                                                                                                                         height: 550px;
                                                                                                                                         margin-right: 30%;position: relative;background-color:#c0c0c0; ">
                                                                                                                                        <table bgcolor="#c0c0c0">


                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;Basic:</td>    
                                                                                                                                            <td><s:textfield name="earnedBasic" id="whearnedBasic" cssClass="inputTextBlueWages" value="%{earnedBasic}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="53"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;Food:</td>    
                                                                                                                                            <td><s:textfield name="earnedFood" id="whearnedFood" cssClass="inputTextBlueWages" value="%{earnedFood}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="54"/></td>
                                                                                                                                            </tr>

                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;Da:</td>    
                                                                                                                                            <td><s:textfield name="earnedDa" id="whearnedDa" cssClass="inputTextBlueWages" value="%{earnedDa}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="55"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;Laundary:</td>    
                                                                                                                                            <td><s:textfield name="earnedLaundary" id="whearnedLaundary" cssClass="inputTextBlueWages" value="%{earnedLaundary}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="56"/></td>
                                                                                                                                            </tr>


                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;Hra:</td>    
                                                                                                                                            <td><s:textfield name="earnedHra" id="whearnedHra" cssClass="inputTextBlueWages" value="%{earnedHra}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="57"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;MaidServices:</td>    
                                                                                                                                            <td><s:textfield name="earnedMaidServices" id="whearnedMaidServices" cssClass="inputTextBlueWages" value="%{earnedMaidServices}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);"  tabindex="58"/></td>
                                                                                                                                            </tr>


                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;Ta:</td>    
                                                                                                                                            <td><s:textfield name="earnedTa" id="whearnedTa" cssClass="inputTextBlueWages" value="%{earnedTa}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="59"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;Entertainment:</td>    
                                                                                                                                            <td><s:textfield name="earnedEntertainment" id="whearnedEntertainment" cssClass="inputTextBlueWages" value="%{earnedEntertainment}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="60"/></td>
                                                                                                                                            </tr>

                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;Ra:</td>    
                                                                                                                                            <td><s:textfield name="earnedRa" id="whearnedRa" cssClass="inputTextBlueWages" value="%{earnedRa}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="61"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;KidsEducation:</td>    
                                                                                                                                            <td><s:textfield name="earnedKidsEducation" id="whearnedKidsEducation" cssClass="inputTextBlueWages" value="%{earnedKidsEducation}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="62"/></td>
                                                                                                                                            </tr>


                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;Life:</td>    
                                                                                                                                            <td><s:textfield name="earnedLife" id="whearnedLife" cssClass="inputTextBlueWages" value="%{earnedLife}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="63"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;VehicleAllowance:</td>    
                                                                                                                                            <td><s:textfield name="earnedVehicleAllowance" id="whearnedVehicleAllowance" cssClass="inputTextBlueWages" value="%{earnedVehicleAllowance}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="64"/></td>
                                                                                                                                            </tr>

                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;Health:</td>    
                                                                                                                                            <td><s:textfield name="earnedHealth" id="whearnedHealth" cssClass="inputTextBlueWages" value="%{earnedHealth}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="65"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;LongTermBonus:</td>    
                                                                                                                                            <td><s:textfield name="earnedLongTermBonus" id="whearnedLongTermBonus" cssClass="inputTextBlueWages" value="%{earnedLongTermBonus}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="66"/></td>
                                                                                                                                            </tr>


                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;CCa:</td>    
                                                                                                                                            <td><s:textfield name="earnedCCa" id="whearnedCCa" cssClass="inputTextBlueWages" value="%{earnedCCa}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="67"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;MiscPay:</td>    
                                                                                                                                            <td><s:textfield name="earnedMiscPay" id="whearnedMiscPay" cssClass="inputTextBlueWages" value="%{earnedMiscPay}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="68"/></td>
                                                                                                                                            </tr>


                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;ProjectPay:</td>    
                                                                                                                                            <td><s:textfield name="earnedProjectPay" id="whearnedProjectPay" cssClass="inputTextBlueWages" value="%{earnedProjectPay}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="69"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;Employer&nbsp;Pf:</td>    
                                                                                                                                            <td><s:textfield name="earnedEmployerPf" id="whearnedEmployerPf" cssClass="inputTextBlueWages" value="%{earnedEmployerPf}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="70"/></td>
                                                                                                                                            </tr>

                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;attallowance:</td>    
                                                                                                                                            <td><s:textfield name="earnedattallowance" id="whearnedattallowance" cssClass="inputTextBlueWages" value="%{earnedattallowance}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="71"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;splallowance:</td>    
                                                                                                                                            <td><s:textfield name="earnedsplallowance" id="whearnedsplallowance" cssClass="inputTextBlueWages" value="%{earnedsplallowance}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="72"/></td>
                                                                                                                                            </tr>


                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">TDS&nbsp;Deduction:</td>    
                                                                                                                                            <td><s:textfield name="tdsDeduction" id="whtdsDeduction" cssClass="inputTextBlueWages" value="%{tdsDeduction}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="73"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Employee&nbsp;Pf:</td>    
                                                                                                                                            <td><s:textfield name="employeePfActualDetails" id="whemployeePfActualDetails" cssClass="inputTextBlueWages" value="%{employeePfActualDetails}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="74"/></td>
                                                                                                                                            </tr>

                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Gross&nbsp;Pay:</td>    
                                                                                                                                            <td><s:textfield name="grossPayActualDetails" id="whgrossPayActualDetails" cssClass="inputTextBlueWagesColoredTextBoxes" value="%{earnedGrossPay}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="75"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Bonus/Commision:</td>    
                                                                                                                                            <td><s:textfield name="bonusCommission" id="whbonusCommission" cssClass="inputTextBlueWages" value="%{bonusCommission}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="76"/></td>
                                                                                                                                            </tr>


                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Net&nbsp;Paid:</td>    
                                                                                                                                            <td><s:textfield name="netPaidActualDetails" id="whnetPaidActualDetails" cssClass="inputTextBlueWagesColoredTextBoxes" value="%{earnedNetPaid}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="77"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Other&nbsp;Deductions:</td>    
                                                                                                                                            <td><s:textfield name="otherDeductions" id="whotherDeductions" cssClass="inputTextBlueWages" value="%{otherDeductions}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="78"/></td>
                                                                                                                                            </tr>

                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Taxable&nbsp;Income:</td>    
                                                                                                                                            <td><s:textfield name="taxableIncome" id="whtaxableIncome" cssClass="inputTextBlueWages" value="%{taxableIncome}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="79"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Other&nbsp;Addtions:</td>    
                                                                                                                                            <td><s:textfield name="otherAdditions" id="whotherAdditions" cssClass="inputTextBlueWages" value="%{otherAdditions}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="80"/></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">Earned&nbsp;Employer&nbsp;ESI&nbsp;:</td>    
                                                                                                                                            <td><s:textfield name="earnedEmployeresi" id="whearnedEmployeresi" cssClass="inputTextBlueWages" value="%{earnedEmployeresi}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="58"  /></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Employee&nbsp;ESI&nbsp;:</td>    
                                                                                                                                            <td><s:textfield name="earnedEmployeeesi" id="whearnedEmployeeesi" cssClass="inputTextBlueWages" value="%{earnedEmployeeesi}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="59" /></td>
                                                                                                                                            </tr>

                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages"  align="right"  >Is&nbsp;Block:</td>
                                                                                                                                            <td  colspan=""> <s:checkbox name="isBlock" id="whisBlock"
                                                                                                                                                        value="%{isBlock}" 
                                                                                                                                                        theme="simple" tabindex="" /> </td>
                                                                                                                                            <td class="fieldLabelWages"  align="right">Project&nbsp;End&nbsp;Date&nbsp;:</td>

                                                                                                                                            <td width="30%"><s:textfield name="projectEndDate" id="whprojectEndDate" cssClass="inputTextBlueWages" value="%{projectEndDate}" onchange="isValidDate(this)" tabindex="58"/></td> 
                                                                                                                                            </tr>
                                                                                                                                            <tr  id ="repaymentGrossAndNetRow" style="display: none">
                                                                                                                                            <td class="fieldLabelWages" align="right">Repayment&nbsp;Gross:</td>    
                                                                                                                                            <td><s:textfield name="repaymentGross" id="repaymentGross" cssClass="inputTextBlueWages" value="%{repaymentGross}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="79"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Repayment&nbsp;Net:</td>    
                                                                                                                                            <td><s:textfield name="repaymentNet" id="repaymentNet" cssClass="inputTextBlueWages" value="%{repaymentNet}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="80"/></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr  id ="repaymentVariableRow" style="display: none">
                                                                                                                                            <td class="fieldLabelWages" align="right">Repayment&nbsp;Variable&nbsp;Pay:</td>    
                                                                                                                                            <td><s:textfield name="repaymentVariablePay" id="repaymentVariablePay" cssClass="inputTextBlueWages" value="%{repaymentVariablePay}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="80"/></td>
                                                                                                                                            </tr>
                                                                                                                                        </table>
                                                                                                                                    </div>

                                                                                                                                </s:form>
                                                                                                                                <script>
                                                                                          
                                                                                                                                    var cal3 = new CalendarTime(document.forms['employeeActualWageDetails'].elements['projectEndDate']);
                                                                                                                                    cal3.year_scroll = true;
                                                                                                                                    cal3.time_comp = false;
                                                                                           
                                                                                                                                </script>  
                                                                                                                            </div>
                                                                                                                            <!--Actual Details Tab  End-->
                                                                                                                            <!-- other Details  Tab  Start -->
                                                                                                                            <div id="yearAndDateTabb" class="tabcontent" > 
                                                                                                                                <s:form action="" name="yearAndDateDetails" theme="simple">



                                                                                                                                    <div align="right" style=" border-radius: 25px;
                                                                                                                                         border: 2px solid #3e93d4;
                                                                                                                                         padding: 20px;
                                                                                                                                         width: 630px;
                                                                                                                                         height: 450px;
                                                                                                                                         margin-right: 30%;position: relative;background-color:#c0c0c0; ">
                                                                                                                                        <table cellpadding="1" cellspacing="1" border="0" width="70%" align="left" bgcolor="#c0c0c0">



                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;Gross:</td>    
                                                                                                                                            <td><s:textfield name="ytdGross" id="whytdGross" cssClass="inputTextBlueWages" value="%{ytdGross}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="82"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;Taxable&nbsp;Salary:</td>    
                                                                                                                                            <td><s:textfield name="ytdTaxableSalary" id="whytdTaxableSalary" cssClass="inputTextBlueWages" value="%{ytdTaxableSalary}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="83"/></td>
                                                                                                                                            </tr>  
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;LongTerm:</td>    
                                                                                                                                            <td><s:textfield name="ytdLongterm" id="whytdLongterm" cssClass="inputTextBlueWages" value="%{ytdLongterm}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="84"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;Taxable&nbsp;Commission:</td>    
                                                                                                                                            <td><s:textfield name="ytdTaxableCommission" id="whytdTaxableCommission" cssClass="inputTextBlueWages" value="%{ytdTaxableCommission}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="85"/></td>
                                                                                                                                            </tr>  
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;PF:</td>    
                                                                                                                                            <td><s:textfield name="ytdPf" id="whytdPf" cssClass="inputTextBlueWages" value="%{ytdPf}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="86"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;TDS&nbsp;On&nbsp;Salary:</td>    
                                                                                                                                            <td><s:textfield name="ytdTDSonSalary" id="whytdTDSonSalary" cssClass="inputTextBlueWages" value="%{ytdTDSonSalary}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="87"/></td>
                                                                                                                                            </tr>  
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;Profession&nbsp;Tax:</td>    
                                                                                                                                            <td><s:textfield name="ytdProffTax" id="whytdProffTax" cssClass="inputTextBlueWages" value="%{ytdProffTax}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="88"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;TDS&nbsp;On&nbsp;Commission:</td>    
                                                                                                                                            <td><s:textfield name="ytdTDSOnCommm" id="whytdTDSOnCommm" cssClass="inputTextBlueWages" value="%{ytdTDSOnCommm}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="89"/></td>
                                                                                                                                            </tr>  
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;Life&nbsp;Insurance:</td>    
                                                                                                                                            <td><s:textfield name="ytdLifeInsurance" id="whytdLifeInsurance" cssClass="inputTextBlueWages" value="%{ytdLifeInsurance}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="90"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;TDS&nbsp;Collected:</td>    
                                                                                                                                            <td><s:textfield name="ytdTDSCollected" id="whytdTDSCollected" cssClass="inputTextBlueWages" value="%{ytdTDSCollected}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="91"/></td>
                                                                                                                                            </tr>  
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;TA:</td>    
                                                                                                                                            <td><s:textfield name="ytdTa" id="whytdTa" cssClass="inputTextBlueWages" value="%{ytdTa}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="92"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;TDS&nbsp;Liabilities&nbsp;Salary:</td>    
                                                                                                                                            <td><s:textfield name="ytdTDSLiabilitiesSalary" id="whytdTDSLiabilitiesSalary" cssClass="inputTextBlueWages" value="%{ytdTDSLiabilitiesSalary}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="93"/></td>
                                                                                                                                            </tr>  
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;RA:</td>    
                                                                                                                                            <td><s:textfield name="ytdRa" id="whytdRa" cssClass="inputTextBlueWages" value="%{ytdRa}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="94"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;TDS&nbsp;Liabilities&nbsp;Bonus:</td>    
                                                                                                                                            <td><s:textfield name="ytdTDSLiabilitiesBonus" id="whytdTDSLiabilitiesBonus" cssClass="inputTextBlueWages" value="%{ytdTDSLiabilitiesBonus}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="95"/></td>
                                                                                                                                            </tr>  
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;Others(MISC&nbsp;Pay):</td>    
                                                                                                                                            <td><s:textfield name="ytdOthersMisc" id="whytdOthersMisc" cssClass="inputTextBlueWages" value="%{ytdOthersMisc}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="96"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Diff&nbsp;TDS&nbsp;Liabilities&nbsp;Salary:</td>    
                                                                                                                                            <td><s:textfield name="diffTDSLiabilitiesSalary" id="whdiffTDSLiabilitiesSalary" cssClass="inputTextBlueWages" value="%{diffTDSLiabilitiesSalary}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="97"/></td>
                                                                                                                                            </tr>  
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;Expenses(TAX&nbsp;Free):</td>    
                                                                                                                                            <td><s:textfield name="ytdExpTaxFree" id="whytdExpTaxFree" cssClass="inputTextBlueWages" value="%{ytdExpTaxFree}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="98"/></td>
                                                                                                                                            <td class="fieldLabelWages" align="right">Diff&nbsp;TDS&nbsp;Liabilities&nbsp;Bonus:</td>    
                                                                                                                                            <td><s:textfield name="diffTDSLiabilitiesBonus" id="whdiffTDSLiabilitiesBonus" cssClass="inputTextBlueWages" value="%{diffTDSLiabilitiesBonus}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="99"/></td>
                                                                                                                                            </tr>  
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right">YTD&nbsp;Project&nbsp;Pay:</td>    
                                                                                                                                            <td colspan="3"><s:textfield name="ytdProjectPay" id="whytdProjectPay" cssClass="inputTextBlueWages" value="%{ytdProjectPay}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="100"/></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right" >YTD&nbsp;Savings1&nbsp;Reported:</td>    
                                                                                                                                            <td colspan="3"><s:textfield name="ytdSavings1Reported" id="whytdSavings1Reported" cssClass="inputTextBlueWages" value="%{ytdSavings1Reported}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="101"/></td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                            <td class="fieldLabelWages" align="right" colspan="">YTD&nbsp;Savings2&nbsp;Reported:</td>    
                                                                                                                                            <td colspan="3"><s:textfield name="ytdSavings2Reported" id="whytdSavings2Reported" cssClass="inputTextBlueWages" value="%{ytdSavings2Reported}" onchange="checkForSpace(this);" onkeyup="isNumberKey(this);" tabindex="102"/></td>
                                                                                                                                            </tr>
                                                                                                                                        </table>
                                                                                                                                    </div>
                                                                                                                                </s:form>
                                                                                                                            </div>

                                                                                                                            <div id="tdsTabb" class="tabcontent" > 
                                                                                                                                <s:form action="" name="tdsDetails" theme="simple">


                                                                                                                                    <table cellpadding="1" cellspacing="1" border="0" width="100%" align="center" bgcolor="">
                                                                                                                                        <tr>
                                                                                                                                        <td>
                                                                                                                                            <div align="right" style=" border-radius: 25px;
                                                                                                                                                 border: 2px solid #3e93d4;
                                                                                                                                                 padding: 20px;
                                                                                                                                                 width: 630px;
                                                                                                                                                 height: 800px;
                                                                                                                                                 margin-right: 30%;position: relative;background-color:#c0c0c0; ">
                                                                                                                                                <table width="80%" border="0" align="center" bgcolor="#c0c0c0">

                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages"  align="right"></td>                           

                                                                                                                                                    <td></td>  
                                                                                                                                                    <td class="fieldLabelWages"  align="right">Expected&nbsp;yearly&nbsp;total&nbsp;Gross: </td>                           

                                                                                                                                                    <td colspan="3" align="right"><s:textfield name="expectedYearlyCost" id="whexpectedYearlyCost" cssClass="inputTextBlueWages" value="%{expectedYearlyCost}" onkeyup="isNumberKey(this);" tabindex=""/></td>     
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


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="lifeInsurancePremium" id="whlifeInsurancePremium" cssClass="inputTextBlueWages" value="%{lifeInsurancePremium}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="103"/></td>
                                                                                                                                                    <td colspan="2" align=""></td>

                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="right" colspan="2">Housing&nbsp;Loan&nbsp;Repayment:</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="housingLoanRepay" id="whhousingLoanRepay" cssClass="inputTextBlueWages" value="%{housingLoanRepay}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="104"/></td>
                                                                                                                                                    <td colspan="2" align=""></td>

                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="right" colspan="2">NSC:</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="nscTds" id="whnscTds" cssClass="inputTextBlueWages" value="%{nscTds}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="105"/></td>

                                                                                                                                                    <td colspan="2" align=""></td>
                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="right" colspan="2">PPF&nbsp;Contribution:</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="ppfContribution" id="whppfContribution" cssClass="inputTextBlueWages" value="%{ppfContribution}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="106"/></td>
                                                                                                                                                    <td colspan="2" align=""></td>

                                                                                                                                                    </tr> 
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="right" colspan="2">Tution&nbsp;Fee:</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="tutionFee" id="whtutionFee" cssClass="inputTextBlueWages" value="%{tutionFee}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="107"/></td>

                                                                                                                                                    <td colspan="2" align=""></td>
                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="right" colspan="2">ELSS:</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="elss" id="whelss" cssClass="inputTextBlueWages" value="%{elss}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="108"/></td>

                                                                                                                                                    <td colspan="2" align=""></td>
                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="right" colspan="2">Post&nbsp;Office&nbsp;Term&nbsp;Deposit:</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="postOfficeTerm" id="whpostOfficeTerm" cssClass="inputTextBlueWages" value="%{postOfficeTerm}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="109"/></td>
                                                                                                                                                    <td colspan="2" align=""></td>

                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="right" colspan="2">Bank&nbsp;Deposit&nbsp;(Tax&nbsp;Saving):</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="bankDepositTax" id="whbankDepositTax" cssClass="inputTextBlueWages" value="%{bankDepositTax}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="110"/></td>
                                                                                                                                                    <td colspan="2" align=""></td>

                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="right" colspan="2">LIC from Salary:</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="licFromSal" id="whlicFromSal" cssClass="inputTextBlueWages" value="%{licFromSal}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex=""/></td>

                                                                                                                                                    <td colspan="2" align=""></td>
                                                                                                                                                    </tr> 
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="right" colspan="2">Others:</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="othersTDS" id="whothersTDS" cssClass="inputTextBlueWages" value="%{othersTDS}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="111"/></td>

                                                                                                                                                    <td colspan="2" align=""></td>
                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="" colspan="2"><h2>Investment U/S 80CCC</h2></td>    

                                                                                                                                                    </tr>   

                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="right" colspan="2">Contribution&nbsp;to&nbsp;PF:</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="contributionToPf" id="whcontributionToPf" cssClass="inputTextBlueWages" value="%{contributionToPf}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="112"/></td>
                                                                                                                                                    <td colspan="2" align=""></td>

                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="" colspan="2">80CCD-NPS-Employees&nbsp;contribution:</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="npsEmployeeContr" id="whnpsEmployeeContr" cssClass="inputTextBlueWages" value="%{npsEmployeeContr}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="113"/></td>
                                                                                                                                                    <td colspan="2" align=""></td>

                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="" colspan="2"> Total(max exemptions-1,50,000):</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="totalTds" id="whtotalTds" cssClass="inputTextBlueWages" value="%{totalTds}" onchange="" onkeyup="isNumberKey(this);" tabindex="114"/></td>
                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="totalTdsDeductable" id="whtotalTdsDeductable" cssClass="inputTextBlueWages" value="%{totalTdsDeductable}" onchange="" onkeyup="isNumberKey(this);" tabindex="115"/></td>


                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="" colspan="2">Interest&nbsp;on&nbsp;borrowed&nbsp;capital-self&nbsp;<br>occupied&nbsp;prop(max exemptions-2,00,000):</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="interstOnBorrowed" id="whinterstOnBorrowed" cssClass="inputTextBlueWages" value="%{interstOnBorrowed}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="116"/></td>
                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="interstOnBorrowedDeductable" id="whinterstOnBorrowedDeductable" cssClass="inputTextBlueWages" value="%{interstOnBorrowedDeductable}" onchange="" onkeyup="isNumberKey(this);" tabindex="117"/></td>


                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align=" " colspan="2"><h2>Medical&nbsp;Insurance&nbsp;U/S80d</h2></td>    

                                                                                                                                                    </tr>   

                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="right" colspan="2">Insurance&nbsp;for&nbsp;parents(Paid&nbsp;for&nbsp;senior&nbsp;citizens)<br>(max exemptions-20,000):</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="insuranceForParents" id="whinsuranceForParents" cssClass="inputTextBlueWages" value="%{insuranceForParents}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="118"/></td>
                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="insuranceForParentsDeduc" id="whinsuranceForParentsDeduc" cssClass="inputTextBlueWages" value="%{insuranceForParentsDeduc}" onchange="" onkeyup="isNumberKey(this);" tabindex="119"/></td>


                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="" colspan="2">Insurance&nbsp;other(self,spouse and children)<br>(max exemptions-15,000):</td>    


                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="insuranceOthers" id="whinsuranceOthers" cssClass="inputTextBlueWages" value="%{insuranceOthers}" onchange="tdsCalculation();" onkeyup="isNumberKey(this);" tabindex="120"/></td>
                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="insuranceOthersDeduc" id="whinsuranceOthersDeduc" cssClass="inputTextBlueWages" value="%{insuranceOthersDeduc}" onchange="" onkeyup="isNumberKey(this);" tabindex="121"/></td>


                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="" colspan="2">Interest&nbsp;on&nbsp;education&nbsp;loan&nbsp;repayment(80E):</td>    

                                                                                                                                                    <td colspan="2" align=""></td>
                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="interstOnEdu" id="whinterstOnEdu" cssClass="inputTextBlueWages" value="%{interstOnEdu}" onchange="" onkeyup="isNumberKey(this);" tabindex="122"/></td>



                                                                                                                                                    </tr>  
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabelWages" align="" colspan="2">HR&nbsp;Exemptions:</td>    

                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="interstOnHrAssumptionsInv" id="whinterstOnHrAssumptionsInv" cssClass="inputTextBlueWages" value="%{interstOnHrAssumptionsInv}" onchange="payRollFieldLengthValidator(this);computeHrExemtionsDed();" onkeyup="isNumberKey(this);" tabindex="123"/></td>

                                                                                                                                                    <td colspan="2" align="right"><s:textfield name="interstOnHrAssumptions" id="whinterstOnHrAssumptions" cssClass="inputTextBlueWages" value="%{interstOnHrAssumptions}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKey(this);" tabindex="124"/></td>


                                                                                                                                                    </tr>  


                                                                                                                                                </table>
                                                                                                                                            </div>
                                                                                                                                        </td>
                                                                                                                                        </tr>
                                                                                                                                    </table>
                                                                                                                                </s:form>
                                                                                                                            </div>


                                                                                                                        </div>
                                                                                                                    </td>           </tr>

                                                                                                                </table>

                                                                                                            </s:form>
                                                                                                        </div>

                                                                                                        </div>

                                                                                                        <div id="LockAmtCheckTab" class="tabcontent" style="display: block;">  
                                                                                                            <table cellpadding="0" cellspacing="0" border="0" width="100%">


                                                                                                                <!-- new div for account list by priority start -->
                                                                                                                <tr>
                                                                                                                <td class="homePortlet" valign="top">
                                                                                                                    <div class="portletTitleBar">
                                                                                                                        <div class="portletTitleLeft">Lock&nbsp;Amt.&nbsp;Check</div>
                                                                                                                        <div class="portletIcons">
                                                                                                                            <a href="javascript:animatedcollapse.hide('LockAmountCheckDiv')" title="Minimize">
                                                                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                            <a href="javascript:animatedcollapse.show('LockAmountCheckDiv')" title="Maximize">
                                                                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                            </a>
                                                                                                                        </div>
                                                                                                                        <div class="clear"></div>
                                                                                                                    </div>
                                                                                                                    <div id="LockAmountCheckDiv" style="background-color:#ffffff">
                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                            <tr>
                                                                                                                            <td width="40%" valign="top" align="center">
                                                                                                                                <s:form action="getLockAmtReport" theme="simple" name="lockAmtReport" id="lockAmtReport">  

                                                                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                        <tr align="right">
                                                                                                                                        <td class="headerText" colspan="9">
                                                                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                        </td>
                                                                                                                                        </tr>

                                                                                                                                        <tr>
                                                                                                                                        <td>
                                                                                                                                            <%
                                                                                                                                                if (session.getAttribute("resultMessageForLOck") != null) {
                                                                                                                                                    out.println(session.getAttribute("resultMessageForLOck"));
                                                                                                                                                    session.removeAttribute("resultMessageForLOck");
                                                                                                                                                }

                                                                                                                                            %>
                                                                                                                                        </td>
                                                                                                                                        </tr>
                                                                                                                                        <tr>
                                                                                                                                        <td>
                                                                                                                                            <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                                                                                                <tr>
                                                                                                                                                <td class="fieldLabel">Year(YYYY):</td>
                                                                                                                                                <td>

                                                                                                                                                    <s:textfield name="year" id="yearlockAmt" maxlength="4" cssClass="inputTextBlue" value="%{year}" onblur="yearValidation(this.value,event)" onkeypress="yearValidation(this.value,event)"/>
                                                                                                                                                </td>
                                                                                                                                                <td class="fieldLabel">Month:</td>
                                                                                                                                                <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="monthlockAmt" onchange="" headerValue="select" headerKey="" value="%{month}"/></td>

                                                                                                                                                </tr>
                                                                                                                                                <tr>
                                                                                                                                                <td class="fieldLabel">EmpName :</td>
                                                            <td><s:textfield name="employeeName" id="employeeName1" value="" autocomplete="off" cssClass="inputTextBlue" onkeyup="getEmplyeeNamesForTefSearch();"/><span id="empNoSpan"></span><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessage1"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popup1">
                                                                                                    <table id="completeTable1" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                                                                                <s:hidden id="userId" name="userId"/>
                                                                </td>  
                                                                                                                                               
                                                                                                                                                <td class="fieldLabel">Lock&sAmount&Period:</td>
                                                                                                                                                <td>
                                                                                                                                                    <s:select name="lockAmtPeriod"  id="lockAmtPeriod" 
                                                                                                                                                              cssClass="inputSelect" 
                                                                                                                                                              list="{'12','6','All'}" value=""></s:select> 
                                                                                                                                                </td>
                                                                                                                                                </tr>
                                                                                                                                                <tr>
                                                                                                                                                <td colspan="4" width="200px" align="right">
                                                                                                                                                    <input type="button"  value="Generate" Class="button_payroll" onClick="generateLockAmtExcel();"/> 
                                                                                                                                                    <input type="button"  value="Search" Class="button_payroll" onClick="serachForLockAmt();"/>
                                                                                                                                                </td>

                                                                                                                                                </tr>



                                                                                                                                            </table>
                                                                                                                                        </td>
                                                                                                                                        </tr>
                                                                                                                                    </table>
                                                                                                                                </s:form>
                                                                                                                            </td>
                                                                                                                            </tr>
                                                                                                                            <tr>
                                                                                                                            <td align="center"><span id="resultmsglockAmt" style="display: none"><font color="red" size="2px">no records found..</font></span></td>
                                                                                                                            </tr>
                                                                                                                            <tr>
                                                                                                                            <td align="center"><span id="loadingMessagelockAmt" style="display: none"><font color="red" size="2px">loading please wait...</font></span></td>
                                                                                                                            </tr>
                                                                                                                            <tr>
                                                                                                                            <td>

                                                                                                                                <br>
                                                                                                                                <div id="lockAmtAllEmpsList" style="display: none">
                                                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                   <div id="pagLock" style=" display: none;">
                                                                                                <label style="margin-left:5%; position: relative;color:blue"> Display <select id="paginationOptionLock" class="disPlayRecordsCss" onchange="pagerOptionLock();" style="width: auto">

                                                                <option>15</option>
                                                                <option>20</option>
                                                                <option>25</option>
                                                            </select> Rows</label></div>
                                                                                                                                    <table id="tbllockAmtAllEmpsList" cellpadding='0' cellspacing='0' border='0' class="gridTable" width='50%' align="center">
                                                                                                                                        <!--COLGROUP ALIGN="left" >
                                                                                                                                            <COL width="10%">
                                                                                                                                                <COL width="20%">
                                                                                                                                                    <COL width="10%">
                                                                                                                                                        <COL width="10%"-->

                                                                                                                                                            </table>
                                                                                                                                                            <br>
                                                                                                                                                            <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                            </div>
                                                                                                                                                            <div id="lockAmtAllSingleEmpList" style="display: none">
                                                                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                                <table width="100%">
                                                                                                                                                                    <tr>
                                                                                                                                                                    <td class="fieldLabel" width="200px" align="right">Employee&nbsp;Name :</td>
                                                                                                                                                                    <td>
                                                                                                                                                                        <div id="empNameForLockAmtSearch" style="color:green;font-size: 12px;font-family: sans-serif;"></div>
                                                                                                                                                                    </td>
                                                                                                                                                                    </tr>
                                                                                                                                                                </table>

                                                                                                                                                                <div style="margin-right: 15%;">
                                                                                                                                                                    <table id="tbllockAmtSingleEmpList" cellpadding='0' cellspacing='0' border='0' class="gridTable" width='40%' align="center">
                                                                                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                                                                                            <COL width="5%">
                                                                                                                                                                                <COL width="5%">
                                                                                                                                                                                    <COL width="5%">
                                                                                                                                                                                        </table>
                                                                                                                                                                                        </div>

                                                                                                                                                                                        <br>
                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                        </div>
                                                                                                                                                                                        <div id="menuDiv" align="center" style="display:none;position: absolute;top: 120px; left: 180px;">
                                                                                                                                                                                            <table border="1" bgcolor="#FBFBB1" width="100">
                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td>
                                                                                                                                                                                                    <a id="detailsLink"><font class="fieldLabel">Details</font></a>
                                                                                                                                                                                                    <br>
                                                                                                                                                                                                    <!--<a><font class="fieldLabel">Send Alert</font></a>
                                                                                                                                                                                                    <br>-->
                                                                                                                                                                                                    <a id="cancelLink"><font class="fieldLabel">Cancel</font></a>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                </tr>
                                                                                                                                                                                            </table>
                                                                                                                                                                                        </div>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>

                                                                                                                                                                                        </table>

                                                                                                                                                                                        </div>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>

                                                                                                                                                                                        <!-- new div for account list by priority end -->

                                                                                                                                                                                        </table>

                                                                                                                                                                                        <%--     </sx:div>
                                                                                                                                                                                        </sx:tabbedpanel> --%>
                                                                                                                                                                                        <!--//END TABBED PANNEL : --> 
                                                                                                                                                                                        </div>


                                                                                                                                                                                        <div id="pfProfessionalTaxReportsTab" class="tabcontent" style="display: block;">  
                                                                                                                                                                                            <table cellpadding="0" cellspacing="0" border="0" width="100%">


                                                                                                                                                                                                <!-- new div for account list by priority start -->
                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td class="homePortlet" valign="top">
                                                                                                                                                                                                    <div class="portletTitleBar">
                                                                                                                                                                                                        <!-- div class="portletTitleLeft">Reports&nbsp;Generation</div -->
                                                                                                                                                                                                         <div class="portletTitleLeft">Statutary&nbsp;Payment&nbsp;Reports</div>
                                                                                                                                                                                                        <div class="portletIcons">
                                                                                                                                                                                                        <a href="javascript:animatedcollapse.hide('pfProfessionalTaxReportsDiv')" title="Minimize">
                                                                                                                                                                                                        <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                                                        <a href="javascript:animatedcollapse.show('pfProfessionalTaxReportsDiv')" title="Maximize">
                                                                                                                                                                                                        <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="clear"></div>
                                                                                                                                                                                                    </div>
                                                                                                                                                                                                    <div id="pfProfessionalTaxReportsDiv" style="background-color:#ffffff">
                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td width="40%" valign="top" align="center">
                                                                                                                                                                                                        <s:form action="getReportsPfOrPT" theme="simple" name="getReportsPfOrPT" id="getReportsPfOrPT">  

                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                                                                        <tr align="right">
                                                                                                                                                                                                        <td class="headerText" colspan="9">
                                                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <%
                                                                                                                                                                                                        if (session.getAttribute("resultMessageForPForPTReports") != null) {
                                                                                                                                                                                                        out.println(session.getAttribute("resultMessageForPForPTReports"));
                                                                                                                                                                                                        session.removeAttribute("resultMessageForPForPTReports");
                                                                                                                                                                                                        }

                                                                                                                                                                                                        %>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Year(YYYY):</td>
                                                                                                                                                                                                        <td>

                                                                                                                                                                                                        <s:textfield name="year" id="yearPForPTAmt" maxlength="4" cssClass="inputTextBlue" value="%{year}" onblur="yearValidation(this.value,event)" onkeypress="yearValidation(this.value,event)"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Month:</td>
                                                                                                                                                                                                        <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="monthPForPTAmt" onchange="" headerValue="select" headerKey="" value="%{month}"/></td>

                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Report&nbsp;For:</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <s:select name="reportFor"  id="reportFor" 
                                                                                                                                                                                                        cssClass="inputSelect" 
                                                                                                                                                                                                        list="{'PF','Professional Tax','TDS'}" value=""></s:select> 
                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        <td colspan="2" width="" align="right">
                                                                                                                                                                                                        <input type="button"  value="Generate" Class="button_payroll" onClick="generatePfOrPTExcel();"/> 

                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        </tr>



                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </s:form>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>



                                                                                                                                                                                                        </table>

                                                                                                                                                                                                    </div>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                </tr>
                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td class="homePortlet" valign="top">
                                                                                                                                                                                                    <div class="portletTitleBar">
                                                                                                                                                                                                        <div class="portletTitleLeft">Repayment&nbsp;Details</div>
                                                                                                                                                                                                        <div class="portletIcons">
                                                                                                                                                                                                        <a href="javascript:animatedcollapse.hide('repaymentDetailsDiv')" title="Minimize">
                                                                                                                                                                                                        <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                                                        <a href="javascript:animatedcollapse.show('repaymentDetailsDiv')" title="Maximize">
                                                                                                                                                                                                        <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="clear"></div>
                                                                                                                                                                                                    </div>
                                                                                                                                                                                                    <div id="repaymentDetailsDiv" style="background-color:#ffffff">
                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td width="40%" valign="top" align="center">
                                                                                                                                                                                                        <s:form action="getReportsPfOrPT" theme="simple" name="getReportsPfOrPT" id="getReportsPfOrPT">  

                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                                                                        <tr align="right">
                                                                                                                                                                                                        <td class="headerText" colspan="9">
                                                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <%
                                                                                                                                                                                                        if (session.getAttribute("resultMessageForRepayment") != null) {
                                                                                                                                                                                                        out.println(session.getAttribute("resultMessageForRepayment"));
                                                                                                                                                                                                        session.removeAttribute("resultMessageForRepayment");
                                                                                                                                                                                                        }

                                                                                                                                                                                                        %>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Year(YYYY):</td>
                                                                                                                                                                                                        <td>

                                                                                                                                                                                                        <s:textfield name="year" id="yearRepayment" maxlength="4" cssClass="inputTextBlue" value="%{year}" onblur="yearValidation(this.value,event)" onkeypress="yearValidation(this.value,event)"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Month:</td>
                                                                                                                                                                                                        <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="monthRepayment" onchange="" headerValue="select" headerKey="" value="%{month}"/></td>






                                                                                                                                                                                                        <td class="fieldLabel" width="" align="right">
                                                                                                                                                                                                        <input type="button"  value="Search" Class="button_payroll" onClick="repaymentDetails();"/> 

                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5" align="center"><span id="resultmsgRepayment" style="display: none"><font color="red" size="2px">no records found..</font></span></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5"  align="center"><span id="loadingMessageRepament" style="display: none"><font color="red" size="2px">loading please wait...</font></span></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        <table>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td >

                                                                                                                                                                                                        <br>
                                                                                                                                                                                                        <div id="payrollCheckList" style="display: inline">
                                                                                                                                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                                                                        <div id="pagRepay" style=" display: none;">
                                                                                                                                                                                                        <label style="top: 10%; position: relative;color:blue"> Display <select id="paginationOptionRepay" class="disPlayRecordsCss" onchange="pagerOptionRepay();" style="width: auto">

                                                                                                                                                                                                        <option>15</option>
                                                                                                                                                                                                        <option>20</option>
                                                                                                                                                                                                        <option>25</option>
                                                                                                                                                                                                        </select> Rows</label></div>
                                                                                                                                                                                                        <table id="tblUpdateRepayment" cellpadding='0' cellspacing='0' border='0' class="gridTable" width='90%' align="center">
                                                                                                                                                                                                        <!--COLGROUP ALIGN="left" >
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="20%">
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="10%"-->

                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        <br>
                                                                                                                                                                                                         <span id="totalDivRepay" style="display:none">
                                                                                                                                                                                                        <div align="left"><font style="margin-left:30px;color:#e3170d;font-family:Arial,Verdana,Geneva,Tahoma,Trebuchet MS,sans-serif;font-size:15px;">Total:</font><span id="totalRepay"></span> </div>
                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                        </span>
                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                        </div>

                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        </table>
                                                                                                                                                                                                    </td>
                                                                                                                                                                                                    </tr>
                                                                                                                                                                                                </table>
                                                                                                                                                                                            </s:form>
                                                                                                                                                                                            </td>
                                                                                                                                                                                            </tr>



                                                                                                                                                                                            </table>

                                                                                                                                                                                        </div>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>
                                                                                                                                                                                        <!-- new div for account list by priority end -->
                                                                                                                                                                                        <!-- tds calculations start -->

                                                                                                                                                                                        <!-- block salary tr start -->
                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td class="homePortlet" valign="top">
                                                                                                                                                                                            <div class="portletTitleBar" onload="">
                                                                                                                                                                                                <div class="portletTitleLeft">Blocked&nbsp;Salaries</div>
                                                                                                                                                                                                <div class="portletIcons">
                                                                                                                                                                                                    <a href="javascript:animatedcollapse.hide('blockedSalariesDiv')" title="Minimize">
                                                                                                                                                                                                        <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                                                    <a href="javascript:animatedcollapse.show('blockedSalariesDiv');" title="Maximize">
                                                                                                                                                                                                        <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                                                    </a>
                                                                                                                                                                                                </div>
                                                                                                                                                                                                <div class="clear"></div>
                                                                                                                                                                                            </div>
                                                                                                                                                                                            <div id="blockedSalariesDiv" style="background-color:#ffffff" >
                                                                                                                                                                                                <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                                                    <tr>
                                                                                                                                                                                                    <td width="40%" valign="top" align="center">
                                                                                                                                                                                                        <s:form action="getBlockedSalaries" theme="simple" name="getBlockedSalaries" id="getBlockedSalaries">  

                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                                                                        <tr align="right">
                                                                                                                                                                                                        <td class="headerText" colspan="9">
                                                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <%
                                                                                                                                                                                                        if (session.getAttribute("resultMessageForBlockedSal") != null) {
                                                                                                                                                                                                        out.println(session.getAttribute("resultMessageForBlockedSal"));
                                                                                                                                                                                                        session.removeAttribute("resultMessageForBlockedSal");
                                                                                                                                                                                                        }

                                                                                                                                                                                                        %>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr><td> <span id="MessageBlockedSal" style="display: none"><font color="red" size="2px">No Records Found!!</font></span>
                                                                                                                                                                                                       </td></tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                                                                                                                                        
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Year(YYYY):</td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        
                                                                                                                                                                                                        <s:textfield name="year" id="yearBlockedSal" maxlength="4" cssClass="inputTextBlue" value="%{year}" onblur="yearValidation(this.value,event)" onkeypress="yearValidation(this.value,event)"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Month:</td>
                                                                                                                                                                                                        <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="monthBlockedSal" onchange="" headerValue="select" headerKey="" value="%{month}"/></td>
                                                                                                                                                                                                        
                                                                                                                                                                                                        <td class="fieldLabel" width="" align="right">
                                                                                                                                                                                                        <input type="button"  value="Search" Class="button_payroll" onClick="blockedSalaryDetails();"/> 
                                                                                                                                                                                                        
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5" align="center"><span id="resultmsgBlockedSal" style="display: none"><font color="red" size="2px">no records found..</font></span></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5"  align="center"><span id="loadingMessageBlockedSal" style="display: none"><font color="red" size="2px">loading please wait...</font></span></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        

                                                                                                                                                                                                        </table>
                                                                                                                                                                                                         <table align="center">
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td >
                                                                                                                                                                                                        <br>
                                                                                                                                                                                                        <div id="payrollCheckList" style="display: inline">
                                                                                                                                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                                                                        <div id="pagBlock" style=" display: none;">
                                                                                                                                                                                                        <label style="top: 10%; position: relative;color:blue"> Display <select id="paginationOptionBlock" class="disPlayRecordsCss" onchange="pagerOptionBlock();" style="width: auto">

                                                                                                                                                                                                        <option>15</option>
                                                                                                                                                                                                        <option>20</option>
                                                                                                                                                                                                        <option>25</option>
                                                                                                                                                                                                        </select> Rows</label></div>
                                                                                                                                                                                                        <table id="tblUpdateBlockedSal" cellpadding='0' cellspacing='0' border='0' class="gridTable" width='90%' align="center">
                                                                                                                                                                                                        <!--COLGROUP ALIGN="left" >
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="20%">
                                                                                                                                                                                                        <COL width="30%">
                                                                                                                                                                                                        <COL width="20%">
                            
                                                                                                                                                                                                        <COL width="20%"-->


                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        
                                                                                                                                                                                                        <br>
                                                                                                                                                                                                        <span id="totalDivBlock" style="display:none">
                                                                                                                                                                                                        <div align="left"><font style="margin-left:5px;color:#e3170d;font-family:Arial,Verdana,Geneva,Tahoma,Trebuchet MS,sans-serif;font-size:15px;">Total:</font><span id="totalBlock"></span> </div>
                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                        </span>
                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                        </div>

                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </s:form>
                                                                                                                                                                                                    </td>
                                                                                                                                                                                                    </tr>
</table></td></tr>

                                                                                                                                                                                        <!-- block salary tr end -->
                                                                                                                                                                                        <!-- Sal revisions block start -->
                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td class="homePortlet" valign="top">
                                                                                                                                                                                            <div class="portletTitleBar" >
                                                                                                                                                                                                <div class="portletTitleLeft" >Revised&nbsp;Salaries</div>
                                                                                                                                                                                                <div class="portletIcons">
                                                                                                                                                                                                    <a href="javascript:animatedcollapse.hide('revisedSalariesDiv')" title="Minimize">
                                                                                                                                                                                                        <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                                                    <a href="javascript:animatedcollapse.show('revisedSalariesDiv');" title="Maximize">
                                                                                                                                                                                                        <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                                                    </a>
                                                                                                                                                                                                </div>
                                                                                                                                                                                                <div class="clear"></div>
                                                                                                                                                                                            </div>
                                                                                                                                                                                            <div id="revisedSalariesDiv" style="background-color:#ffffff" >
                                                                                                                                                                                                <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                                                    <tr>
                                                                                                                                                                                                    <td width="100%" valign="top" align="center">
                                                                                                                                                                                                        <s:form action="getRevisedSalaries" theme="simple" name="getRevisedSalaries" id="getRevisedSalaries">  

                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="50%">
                                                                                                                                                                                                        <tr align="right">

                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <%
                                                                                                                                                                                                        if (session.getAttribute("resultMessageForRevisedSal") != null) {
                                                                                                                                                                                                        out.println(session.getAttribute("resultMessageForRevisedSal"));
                                                                                                                                                                                                        session.removeAttribute("resultMessageForRevisedSal");
                                                                                                                                                                                                        }

                                                                                                                                                                                                        %>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Year(YYYY):</td>
                                                                                                                                                                                                        <td>

                                                                                                                                                                                                        <s:textfield name="year" id="revisedYear" maxlength="4" cssClass="inputTextBlue" value="%{year}" onblur="yearValidation(this.value,event)" onkeypress="yearValidation(this.value,event)"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Month:</td>
                                                                                                                                                                                                        <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="revisedMonth" onchange="" headerValue="select" headerKey="" value="%{month}"/></td>






                                                                                                                                                                                                        <td class="fieldLabel" width="" align="right">
                                                                                                                                                                                                        <input type="button"  value="Search" Class="button_payroll" onClick="revisedSalaryDetails();"/> 

                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5" align="center"><span id="resultmsgRevisedSal" style="display: none"><font color="red" size="2px">no records found..</font></span></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5"  align="center"><span id="loadingMessageRevisedSal" style="display: none"><font color="red" size="2px">loading please wait...</font></span></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                    </table>
                                                                                                                                                                                                    <table>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>

                                                                                                                                                                                                        <br>
                                                                                                                                                                                                        <div id="payrollCheckList" style="display: inline">
                                                                                                                                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                                                                        <!-- table id="tblUpdateRevisedSal" cellpadding='0' cellspacing='0' border='0' class="gridTable" width='90%' align="center">
                                                                                                                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="20%">
                                                                                                                                                                                                        <COL width="30%">
                                                                                                                                                                                                        <COL width="20%">
                                                                                                                                                                                                        <COL width="20%">
                                                                                                                                                                                                        </table -->
                                                                                                                                                                                                        <div id="pag" style=" display: none;">
                                                                                                                                                                                                        <label style="top: 10%; position: relative;color:blue"> Display <select id="paginationOptionRev" class="disPlayRecordsCss" onchange="pagerOptionRev();" style="width: auto">

                                                                                                                                                                                                        <option>15</option>
                                                                                                                                                                                                        <option>20</option>
                                                                                                                                                                                                        <option>25</option>
                                                                                                                                                                                                        </select> Rows</label></div>
                                                                                                                                                                                                        <table id="tblUpdateRevisedSal" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' align="center">

                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        <br>
                                                                                                                                                                                                           <span id="totalDivRev" style="display:none">
                                                                                                                                                                                                        <div align="left"><font style="color:#e3170d;font-family:Arial,Verdana,Geneva,Tahoma,Trebuchet MS,sans-serif;font-size:15px;">Total:</font><span id="totalRev"></span> </div>
                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                        </span>
                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                        </div>

                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                    </table>
                                                                                                                                                                                            </td>
                                                                                                                                                                                            </tr>
                                                                                                                                                                                            </table>
                                                                                                                                                                                        </s:form>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>



                                                                                                                                                                                        </table>

                                                                                                                                                                                        </div>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>

                                                                                                                                                                                        <!-- Sal revisions block end -->


                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td class="homePortlet" valign="top">
                                                                                                                                                                                            <div class="portletTitleBar">
                                                                                                                                                                                                <div class="portletTitleLeft">TDS&nbsp;Calculations</div>
                                                                                                                                                                                                <div class="portletIcons">
                                                                                                                                                                                                    <a href="javascript:animatedcollapse.hide('tdsCalculationDiv')" title="Minimize">
                                                                                                                                                                                                        <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                                                    <a href="javascript:animatedcollapse.show('tdsCalculationDiv')" title="Maximize">
                                                                                                                                                                                                        <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                                                    </a>
                                                                                                                                                                                                </div>
                                                                                                                                                                                                <div class="clear"></div>
                                                                                                                                                                                            </div>
                                                                                                                                                                                            <div id="tdsCalculationDiv" style="background-color:#ffffff">
                                                                                                                                                                                                <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                                                    <tr>
                                                                                                                                                                                                    <td width="40%" valign="top" align="center">
                                                                                                                                                                                                        <s:form action="getReportsPfOrPT" theme="simple" name="getReportsPfOrPT" id="getReportsPfOrPT">  

                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                                                                        <tr align="right">
                                                                                                                                                                                                        <td class="headerText" colspan="9">
                                                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <%
                                                                                                                                                                                                        if (session.getAttribute("resultMessageForTdsCalculation") != null) {
                                                                                                                                                                                                        out.println(session.getAttribute("resultMessageForTdsCalculation"));
                                                                                                                                                                                                        session.removeAttribute("resultMessageForTdsCalculation");
                                                                                                                                                                                                        }

                                                                                                                                                                                                        %>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                        <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="fieldLabel">Year(YYYY):</td>
                                                                                                                                                                                                        <td>

                                                                                                                                                                                                        <s:textfield name="year" id="yearTdsCalculations" maxlength="4" cssClass="inputTextBlue" value="%{year}" onblur="yearValidation(this.value,event)" onkeypress="yearValidation(this.value,event)"/>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        <td class="fieldLabel">Month:</td>
                                                                                                                                                                                                        <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="monthTdsCalculations" onchange="" headerValue="select" headerKey="" value="%{month}"/></td>






                                                                                                                                                                                                        <td class="fieldLabel" width="" align="right">
                                                                                                                                                                                                        <input type="button"  value="Search" Class="button_payroll" onClick="getTdsCalculation();"/> 

                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5" align="center"><span id="resultmsgTdsCalculation" style="display: none"><font color="red" size="2px">no records found..</font></span></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5"  align="center"><span id="loadingMessageTdsCalculation" style="display: none"><font color="red" size="2px">loading please wait...</font></span></td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                    </table>
                                                                                                                                                                                                    <table align="center">
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td >

                                                                                                                                                                                                        <br>
                                                                                                                                                                                                        <div id="payrollCheckList" style="display: inline">
                                                                                                                                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                                                                        <div id="pagTdsCalculation" style=" display: none;">
                                                                                                                                                                                                        <label style="top: 10%; position: relative;color:blue"> Display <select id="paginationOptionTdsCalculation" class="disPlayRecordsCss" onchange="pagerOptionTdsCalculation();" style="width: auto">

                                                                                                                                                                                                        <option>15</option>
                                                                                                                                                                                                        <option>20</option>
                                                                                                                                                                                                        <option>25</option>
                                                                                                                                                                                                        </select> Rows</label></div>
                                                                                                                                                                                                        <table id="tblUpdateTdsCalculation" cellpadding='0' cellspacing='0' border='0' class="gridTable" width='90%' align="center">
                                                                                                                                                                                                        <!--COLGROUP ALIGN="left" >
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="20%">
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                                        <COL width="10%"-->

                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        <br>
                                                                                                                                                                                                          <span id="totalDiv" style="display:none">
                                                                                                                                                                                                        <div align="left" ><font style="margin-left:30px;color:#e3170d;font-family:Arial,Verdana,Geneva,Tahoma,Trebuchet MS,sans-serif;font-size:15px;">Total:</font><span id="totalTDS"></span> </div>
                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                        </span>
                                                                                                                                                                                                        </div>

                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                    </table>
                                                                                                                                                                                            </td>
                                                                                                                                                                                            </tr>
                                                                                                                                                                                            </table>
                                                                                                                                                                                        </s:form>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>



                                                                                                                                                                                        </table>

                                                                                                                                                                                        </div>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>
                                                                                                                                                                                        <!-- tds calculations end -->
                                                                                                                                                                                        <tr>
                                        <td class="homePortlet" valign="top">
                                            <div class="portletTitleBar">
                                                <div class="portletTitleLeft">Cosilidated&nbsp;Tax&nbsp;Savings</div>
                                                <div class="portletIcons">
                                                    <a href="javascript:animatedcollapse.hide('employeeTaxSavings')" title="Minimize">
                                                        <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                    <a href="javascript:animatedcollapse.show('employeeTaxSavings')" title="Maximize">
                                                        <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                    </a>
                                                </div>
                                                <div class="clear"></div>
                                            </div>
                                            <div id="employeeTaxSavings" style="background-color:#ffffff">
                                                <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                    <tr>
                                                    <td width="40%" valign="top" align="center">
                                                        <s:form action="getReportsPfOrPT" theme="simple" name="getReportsPfOrPT" id="getReportsPfOrPT">  

                                                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                <tr align="right">
                                                                <td class="headerText" colspan="9">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                </td>
                                                                </tr>

                                                                <tr>
                                                                <td>
                                                                    <%
                                                                        if (session.getAttribute("taxSavingsResultMessage") != null) {
                                                                            out.println(session.getAttribute("taxSavingsResultMessage"));
                                                                            session.removeAttribute("taxSavingsResultMessage");
                                                                        }

                                                                    %>
                                                                </td>
                                                                </tr>
                                                                <tr>
                                                                <td>
                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                        <tr>
                                                                        <td class="fieldLabel" >Organization:</td>

                                                                        <td colspan="">
                                                                            <s:select  id="orgIdForCosilidatedTax" name="orgId"  list="orgIdMap" style="width:150%" headerKey=""   headerValue="-- Please Select --" class="inputSelectSmall"  value="%{orgId}" />
                                                                        </td>
                                                                        </tr>
                                                                        <tr>
                                                                        <td class="fieldLabel">Year(YYYY):</td>
                                                                        <td>

                                                                            <s:textfield name="year" id="yearCosilidatedTax" maxlength="4" cssClass="inputTextBlue" value="%{year}" onblur="yearValidation(this.value,event)" onkeypress="yearValidation(this.value,event)"/>
                                                                        </td>
                                                                        <td class="fieldLabel">Month:</td>
                                                                        <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="monthCosilidatedTax" onchange="" headerValue="select" headerKey="" value="%{month}"/></td>

                                                                    </tr>
                                                                    <tr>

                                                                    <td colspan="4" class="fieldLabel" width="" align="right">
                                                                            <input type="button"  value="Generate" Class="button_payroll" onClick="getCosilidatedTaxSavings();"/> 

                                                                        </td>

                                                                        </tr>

                                                                        <tr>
                                                                        
                                                                </tr>
                                                                
                                                    </table>
                                                    
                                            </td>
                                            </tr>
                                            </table>
                                        </s:form>
                                        </td>
                                        </tr>



                                        </table>

                                        </div>
                                        </td>
                                        </tr>
                                        <!-- consolated tax savings end -->
                                                                                                                                                                                        </table>

                                                                                                                                                                                        <%--     </sx:div>
                                                                                                                                                                                        </sx:tabbedpanel> --%>
                                                                                                                                                                                        <!--//END TABBED PANNEL : --> 
                                                                                                                                                                                        </div>
                                                                                                                                                                                        </div>
                                                                                                                                                                                        <script type="text/javascript">

                                                                                                                                                                                            var countries=new ddtabcontent("reportsTab")
                                                                                                                                                                                            countries.setpersist(false)
                                                                                                                                                                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                                                                                                                                                            countries.init()
                                    
                                                                                                                                                                                            var countries=new ddtabcontent("accountTabs")
                                                                                                                                                                                            countries.setpersist(false)
                                                                                                                                                                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                                                                                                                                                            countries.init()
                                                                                                                                                                                            var countries=new ddtabcontent("accountTabs1")
                                                                                                                                                                                            countries.setpersist(false)
                                                                                                                                                                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                                                                                                                                                            countries.init()

                                                                                                                                                                                        </script>
                                                                                                                                                                                        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
                                                                                                                                                                                        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/paging.js"/>"></script>

                                                                                                                                                                                        <script type="text/javascript">
                                                                                                                                                                                                       
                                                                                                                                                                                            function pagerOptionRev(){
                                                                                                                                                                                                var paginationSize = document.getElementById("paginationOptionRev").value;
                                                                                                                                                                                                if(isNaN(paginationSize))
                                                                                                                                                                                                {
                       
                                                                                                                                                                                                }
                                                                                                                                                                                                recordPage=paginationSize;
                                                                                                                                                                                                  
                                                                                                                                                                                                $('#tblUpdateRevisedSal').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                                            }
                                                                                                                                                                                                         
                                                                                                                                                                                            function pagerOptionCheck(){
                                                                                                                                                                                                var paginationSize = document.getElementById("paginationOptionCheck").value;
                                                                                                                                                                                                if(isNaN(paginationSize))
                                                                                                                                                                                                {
                       
                                                                                                                                                                                                }
                                                                                                                                                                                                recordPage=paginationSize;
                                                                                                                                                                                                  
                                                                                                                                                                                                $('#tblUpdate').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                                            }
                                                                                                                                                                                            function pagerOptionHistory(){
                                                                                                                                                                                                var paginationSize = document.getElementById("paginationOptionHistory").value;
                                                                                                                                                                                                if(isNaN(paginationSize))
                                                                                                                                                                                                {
                       
                                                                                                                                                                                                }
                                                                                                                                                                                                recordPage=paginationSize;
                                                                                                                                                                                                  
                                                                                                                                                                                                $('#tblpayrollHistoryUpdate').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                                            }
                                                                                                                                                                                            
                                                                                                                                                                                            function pagerOptionWages(){
                                                                                                                                                                                                var paginationSize = document.getElementById("paginationOptionWages").value;
                                                                                                                                                                                                if(isNaN(paginationSize))
                                                                                                                                                                                                {
                       
                                                                                                                                                                                                }
                                                                                                                                                                                                recordPage=paginationSize;
                                                                                                                                                                                                  
                                                                                                                                                                                                $('#tblWagesHistoryUpdate').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                                            }
                                                                                                                                                                                            function pagerOptionLock(){
                                                                                                                                                                                                var paginationSize = document.getElementById("paginationOptionLock").value;
                                                                                                                                                                                                if(isNaN(paginationSize))
                                                                                                                                                                                                {
                       
                                                                                                                                                                                                }
                                                                                                                                                                                                recordPage=paginationSize;
                                                                                                                                                                                                  
                                                                                                                                                                                                $('#tbllockAmtAllEmpsList').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                                            }
                                                                                                                                                                                            function pagerOptionTdsCalculation(){
                                                                                                                                                                                                var paginationSize = document.getElementById("paginationOptionTdsCalculation").value;
                                                                                                                                                                                                if(isNaN(paginationSize))
                                                                                                                                                                                                {
                       
                                                                                                                                                                                                }
                                                                                                                                                                                                recordPage=paginationSize;
                                                                                                                                                                                                  
                                                                                                                                                                                                $('#tblUpdateTdsCalculation').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                                            }
                                                                                                                                                                                            
                                                                                                                                                                                             function pagerOptionBlock(){
                                                                                                                                                                                                var paginationSize = document.getElementById("paginationOptionBlock").value;
                                                                                                                                                                                                if(isNaN(paginationSize))
                                                                                                                                                                                                {
                       
                                                                                                                                                                                                }
                                                                                                                                                                                                recordPage=paginationSize;
                                                                                                                                                                                                  
                                                                                                                                                                                                $('#tblUpdateBlockedSal').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                                            }
                                              
                                                                    
                                                                                                                                                                                            
                                                                                                                                                                                        </script>
           <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>                                                                                                                                                                              <script type="text/JavaScript" src="<s:url value="/includes/javascripts/pagination.js"/>"></script>
     <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/nameSuggestion.js"/>"></script>


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
                                                                                                                                                                                        </table>
                                                                                                                                                                                        <!--//END MAIN TABLE : Table for template Structure-->
                                                                                                                                                                                        </body>
 <s:if test="%{tempFlag == 1}">
 <script type="text/javascript">
		$(window).load(function(){
			animatedcollapse.show('LockAmountCheckDiv');
			init();
		});
		</script>
       
        </s:if>
        <s:elseif test="%{tempFlag == 2}">
		<script type="text/javascript">
		$(window).load(function(){
		animatedcollapse.show('pfProfessionalTaxReportsDiv');

		});
		</script>
       
        </s:elseif>
        <s:else>
		<script type="text/javascript">
		$(window).load(function(){
			animatedcollapse.show('PayrollCheckExcelReportDiv');
			init();
			initWage();
			initTef();

		});
		</script>
      
            
        </s:else>
                                                                                                                                                                                        </html>