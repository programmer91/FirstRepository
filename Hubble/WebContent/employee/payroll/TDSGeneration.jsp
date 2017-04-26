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
        <title>Hubble Organization Portal :: PayRoll TDS Generation</title>
    <sx:head cache="true"/>
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
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css"/>">
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
   
</head>
<%-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="validateCtc();getLocation();"> --%>
<%-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="getDaysForTheSelectedMonth()"> --%>
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
                                <li ><a href="#" class="selected" rel="payRollReportTab"  >TDS&nbsp;Generation</a></li>

                            </ul>

                            <%--    <sx:tabbedpanel id="employeeUpdatePannel" cssStyle="width:850px; height:510px;padding: 5px 5px 5px 5px;" doLayout="true" useSelectedTabCookie="true">--%>
                            <div  style="border:1px solid gray; width:850px;height: 510px; overflow:auto; margin-bottom: 1em;">

                                
                                
                                <!-- Re run overlay start -->
                                 
                                <!-- Re run overlay end -->
                                
 <!-- Leaves upload start -->
                                
                                                                    
                                                                    
                                     <!-- Leaves upload end -->                               
                                  <!-- TEF overlay-->
                                  
                                  <!-- TEF overlay end -->                                  
                                <div id="payRollReportTab" class="tabcontent"  >   
                                  
                                    <s:form action="" name="generatepayrollform" id="generatepayrollform" theme="simple">
   <div id="resultMessageForFreeze" style="font-size: 15px;"></div>
 <div id="loadingMessageForFreeze" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                      <%
                                                                                                                                            if (session.getAttribute("resultMessage") != null) {
                                                                                                                                                out.println(session.getAttribute("resultMessage"));
                                                                                                                                                session.removeAttribute("resultMessage");
                                                                                                                                            }

                                                                                                                                        %>
   
                                                                                                                                        <table cellpadding="0" cellspacing="3" border="0" align="center" width="100%" style="padding-top: 3%">
                                            
                                            
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

                                                            <td class="fieldLabel">Year(YYYY):</td>
                                                            <td>

                                                                <s:textfield name="yearOverlay" id="yearOverlay" maxlength="4" cssClass="" value="%{yearOverlay}" onchange="getDaysForTheSelectedMonth();" onkeypress="return isNumber(event)" />
                                                            </td>
                                                            <td class="fieldLabel">Month:</td>
                                                            <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="monthOverlay" id="monthOverlay"  headerValue="select" headerKey="0" value="%{monthOverlay}" cssClass="inputSelectSmall" onchange="getDaysForTheSelectedMonth();"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="fieldLabel">No.&nbsp;of&nbsp;Days:</td>
                                                            <td colspan="">
                                                                <s:textfield  id="noOfDays" name="noOfDays" value="%{noOfDays}" onkeypress="return isNumber(event)" />
                                                            </td>
                                                            <td class="fieldLabel">No.&nbsp;of&nbsp;Weekend&nbsp;Days:</td>
                                                            <td colspan="">
                                                                <s:textfield  id="noOfWeekendDays" name="noOfWeekendDays" value="%{noOfWeekendDays}" onkeypress="return isNumber(event)" />
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <%--<td class="overlayFieldLabels">No.&nbsp;of&nbsp;Holidays:</td>
                                                            <td colspan="">
                                                                <s:textfield  id="noOfHolidays" name="noOfHolidays" value="%{noOfHolidays}" onchange="fillNoOfDaysWorked();" />
                                                            </td> --%>
                                                            <td class="fieldLabel">No.&nbsp;of&nbsp;Working&nbsp;Days:</td>
                                                            <td colspan="">
                                                                <s:textfield  id="noOfWorkingDays" name="noOfWorkingDays" value="%{noOfWorkingDays}" onkeypress="return isNumber(event)" />
                                                            </td>
                                                            <td class="fieldLabel">Payment&nbsp;Date:</td>
                                        <td colspan="">
                                            <s:textfield id="paymentDateEmp" name="paymentDateEmp" readonly="true" cssClass="inputTextBlue" value="%{paymentDate}" onchange="isValidDate(this)" style="width:122px"/>
                                            <a href="javascript:cal4.popup();">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0" style="margin-bottom: -4px"></a>
                                        </td> </tr>
                                           <tr>
                                                        <td class="fieldLabel" >Organization:</td>
                                                                
                                                                <td colspan="">
                                                                    <s:select  id="orgId" name="orgId"  list="orgIdMap"  headerKey=""
                                                        headerValue="-- Please Select --" cssClass="inputSelectLarge"  value="%{orgId}" />
                                                                </td>
                                                    </tr>
        
                                                    <tr>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                            <td colspan="2">
                                                                <input type="button" class="btnPayroll" value="Next" onclick="calculatetds();"/>
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
                           <script type="text/JavaScript">
                                                                    var cal4 = new CalendarTime(document.forms['generatepayrollform'].elements['paymentDateEmp']);
                                                                         cal4.year_scroll = true;
                                                                         cal4.time_comp = false;
                                                                         </script> 
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
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>
    </table>
    <!--//END MAIN TABLE : Table for template Structure-->
    
    <script type="text/javascript">
		$(window).load(function(){
			getDaysForTheSelectedMonth();

		});
		</script>
</body>
</html>

