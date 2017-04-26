<%--*******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  September 29, 2007, 7:50 PM
 *
 * Author  : Jyothi Nimmagadda<jnimmagadda@miraclesoft.com>
 *
 * File    : ImmigrationAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
*/--%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.employee.immigration.ImmigrationVTO" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<html>
<head>
    <title>Hubble Organization Portal :: Employee Immigration Details</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
    <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/ImmigrationClientValidation.js"/>"></script>    --%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/JavaScript">
            function checkDate() {
                var passportIssue = document.getElementById('passportIssue').value;
                var passportExp = document.getElementById('passportExp').value;
                var mm = passportIssue.substring(0,2);
                var dd = passportIssue.substring(3,5);
                var yyyy = passportIssue.substring(6,10);
                var mm1 = passportExp.substring(0,2);
                var dd1 = passportExp.substring(3,5);
                var yyyy1 = passportExp.substring(6,10);
                if(yyyy1 < yyyy) {
                    alert('Exp. Date is older than Issue Date');
                    return false;
                }
                else if((yyyy1 == yyyy) && (mm1 < mm)) {
                    alert('Exp. Date is older than Issue Date');
                    return false;
                }
                else if((yyyy1 == yyyy) && (mm1 == mm) && (dd1 < dd)) {
                    alert('Exp. Date is older than Issue Date');
                    return false;
                }
            }
    </script>
    
</head>
<body class="bodyGeneral" oncontextmenu="return false;">

<!--//START MAIN TABLE : Table for template Structure-->
<table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

<!--//START HEADER : Record for Header Background and Mirage Logo-->
<tr class="headerBg">
    <td valign="top"> <s:include value="/includes/template/Header.jsp"/></td>
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
<td width="850px" class="cellBorder" valign="top">
<!--//START TABBED PANNEL : -->
<table cellpadding="0" cellspacing="0" width="100%" border="0">
<tr>
    <td>
        <span class="fieldLabel">Employee Name :</span>&nbsp;
        <s:if test="currentImmigration == null ">
            <a class="navigationText" href="<s:url action="getEmployee"><s:param name="empId" value="%{empId}"/></s:url>"><s:property value="%{employeeName}"/></a>
        </s:if>
        <s:else>
            <a class="navigationText" href="<s:url action="getEmployee"><s:param name="empId" value="%{currentImmigration.empId}"/></s:url>"><s:property value="%{employeeName}"/></a>
        </s:else>
    </td>
</tr>
<tr>
<td valign="top" style="padding: 5px 5px 5px 5px;"> 
<!--//START TABBED PANNEL : -->
<ul id="accountTabs" class="shadetabs" >
    <li ><a href="#" class="selected" rel="personalDetailsTab"  > Employee Immigration Details </a></li>
</ul>


<%-- <sx:tabbedpanel id="activityPannel" cssStyle="width: 850px; height: 600px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>
<div  style="border:1px solid gray; width:850px;height: 600px; overflow:auto; margin-bottom: 1em;">
    
    <!--//START TAB : -->
    <%--  <sx:div id="personalDetailsTab" label="Employee Immigration Details"  > --%>
    <div id="personalDetailsTab" class="tabcontent"  >
        <!--onsubmit="return immigrationValidate();"-->
        <s:form name="immigrationForm" action="%{actionType}"  theme="simple" onsubmit="return checkDate();">
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                <tr class="headerText">
                    <td >Passport Details </td>
                    <td colspan="5">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tr align="right" class="headerText">
                                <td>
                                    <s:property value="#request.resultMessage"/>
                                    
                                    <s:if test="currentImmigration == null ">
                                        <s:hidden name="empId" id="empId"  value="%{empId}"/> 
                                    </s:if>
                                    <s:else>
                                        <s:hidden name="empId" id="empId"  value="%{currentImmigration.empId}"/>          
                                    </s:else>
                                    
                                    <s:submit value="save" cssClass="buttonBg"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6"><s:actionerror/></td>
                            </tr>
                            <tr>
                                <td colspan="6"><s:fielderror/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    
                    <td class="fieldLabel"  width="13%">Cur Immig Status :</td>                           
                    
                    <td><s:select name="currentImmigStatus" list="immigrationStatusList" cssClass="inputSelect" headerKey="" headerValue="--Select Status--" value="%{currentImmigration.currentImmigStatus}"/>  </td>
                    
                    <td class="fieldLabel"  width="13%">Passport No :</td>                           
                    
                    <td><s:textfield name="passportNo" cssClass="inputTextBlue" value="%{currentImmigration.passportNo}" onchange="fieldLengthValidator(this);" id="passportNo" size="20"/></td>  
                    
                    
                    <td class="fieldLabel"  width="14%">Passport Issued At:</td>                           
                    
                    <td><s:textfield name="passportIssuedAt" cssClass="inputTextBlue" value="%{currentImmigration.passportIssuedAt}" onchange="fieldLengthValidator(this);" id="passportIssuedAt" size="50"/></td>   
                    
                </tr>
                <tr>
                    
                    <td class="fieldLabel"  width="200px">Passport Issue :</td>  
                    
                    <td><s:textfield name="passportIssue" id="passportIssue" cssClass="inputTextBlueSmall"  value="%{currentImmigration.passportIssue}"/><a href="javascript:cal1.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    
                    
                    <td class="fieldLabel"  width="13%">Passport Exp :</td>
                    
                    <td><s:textfield name="passportExp" id="passportExp" cssClass="inputTextBlueSmall"  value="%{currentImmigration.passportExp}" /><a href="javascript:cal2.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    
                    <td class="fieldLabel"  width="14%">Passport Country :</td>
                    
                    <td><s:select list="passportCountryList" name="passportCountry" cssClass="inputSelect"  headerKey="" headerValue="--Select Country--"
                                  value="%{currentImmigration.passportCountry}" /></td>
                    
                    <%--  
                            
                            <s:select list="healthInsuranceTypeList" name="healthInsuranceType" cssClass="inputSelect" 
                            headerKey="-1" headerValue="--Select Insurance--"  value="%{currentEmpInsurance.healthInsuranceType}" 
                            <td class="fieldLabel" width="200px" align="right">Country :</td>      --%>                     
                            
                      
                            
                </tr>
            </table>
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                <thead>
                    <th colspan="6" class="headerText" align="left">
                        Entry Details:
                    </th>
                </thead>
                <tr>
                    
                    <td class="fieldLabel"  width="13%">Date of Arrival :</td>   
                    
                    <td><s:textfield name="dateOfArrival" cssClass="inputTextBlueSmall"  value="%{currentImmigration.dateOfArrival}" /><a href="javascript:cal3.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    
                    
                    <td class="fieldLabel"  width="13%">Port Of Entry :</td>                           
                    
                    <td><s:textfield name="portOfEntry" cssClass="inputTextBlue"  value="%{currentImmigration.portOfEntry}" onchange="fieldLengthValidator(this);"  id="portOfEntry" size="30"/></td>    
                    
                    
                    <td class="fieldLabel" width="13%" >I94 No :</td>                           
                    
                    <td><s:textfield name="i94No" cssClass="inputTextBlue"  value="%{currentImmigration.i94No}" onchange="fieldLengthValidator(this);" id="i94No" size="30"/></td>  
                    
                </tr>
                <tr>
                    
                    <td class="fieldLabel"  width="13%">PT University:</td>                           
                    
                    <td><s:textfield name="ptUniversity" cssClass="inputTextBlue"  value="%{currentImmigration.ptUniversity}" onchange="fieldLengthValidator(this);" id="ptUniversity" size="40"/></td>      
                    <td class="fieldLabel"  width="13%">PT Start :</td>
                    
                    <td><s:textfield name="ptStart" cssClass="inputTextBlueSmall"  value="%{currentImmigration.ptStart}"/><a href="javascript:cal4.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    <td class="fieldLabel"  width="13%">PT End :</td>
                    
                    <td><s:textfield name="ptEnd" cssClass="inputTextBlueSmall"  value="%{currentImmigration.ptEnd}" /><a href="javascript:cal5.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    
                    
                </tr>
                
            </table>
            
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                <thead>
                    <th colspan="6" class="headerText" align="left">
                        H1 Details:
                    </th>
                </thead>
                <tr>
                    
                    <td class="fieldLabel"  width="13%" >H1 Filed :</td>
                    
                    <td><s:textfield name="h1Filed" cssClass="inputTextBlueSmall"  value="%{currentImmigration.h1Filed}"/><a href="javascript:cal6.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    
                    
                    
                    <td class="fieldLabel"  width="13%">H1 Lin Receive :</td>
                    
                    <td><s:textfield name="h1LinReceive" cssClass="inputTextBlueSmall"  value="%{currentImmigration.h1LinReceive}" /><a href="javascript:cal7.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    
                    
                    <td class="fieldLabel"  width="13%"  >H1Lin  :</td>                           
                    
                    <td><s:textfield name="h1Lin" cssClass="inputTextBlue"  value="%{currentImmigration.h1Lin}" onchange="fieldLengthValidator(this);" id="h1Lin" size="50"/></td>  
                    
                </tr>
                
                <tr>
                    
                    <td class="fieldLabel"  width="13%">H1 Lca Eta No:</td>                           
                    
                    <td><s:textfield name="h1LcaEtaNo" cssClass="inputTextBlue"  value="%{currentImmigration.h1LcaEtaNo}" onchange="fieldLengthValidator(this);" id="h1LcaEtaNo" size="15"/></td>     
                    
                    <td class="fieldLabel"  width="13%" >H1 Title :</td>                           
                    
                    <td><s:textfield name="h1Title" cssClass="inputTextBlue"  value="%{currentImmigration.h1Title}" onchange="fieldLengthValidator(this);" id="h1Title" size="50"/></td>  
                    
                    <td class="fieldLabel"  width="13%">H1 Start :</td>
                    
                    <td><s:textfield name="h1Start" cssClass="inputTextBlueSmall"  value="%{currentImmigration.h1Start}"/><a href="javascript:cal8.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                </tr>
                <tr>
                    <td class="fieldLabel"  width="13%">H1 Termination :</td>
                    <td><s:textfield name="h1Termination" cssClass="inputTextBlueSmall"  value="%{currentImmigration.h1Termination}" /><a href="javascript:cal9.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    <td class="fieldLabel"  width="13%">H1 End :</td>                           
                    <td><s:textfield name="h1End" cssClass="inputTextBlueSmall"  value="%{currentImmigration.h1End}" /><a href="javascript:cal10.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    <td class="fieldLabel"  width="13%" >H1 Status :</td>
                    <td><s:select
                            name="h1Status" 
                        list="{'value','value1'}" cssClass="inputSelect"  value="%{currentImmigration.h1Status}"/>  </td>                          
                </tr>
                <tr>
                    <td class="fieldLabel"  width="13%" >Query Received :</td>
                    <td><s:textfield name="queryReceived" cssClass="inputTextBlueSmall"  value="%{currentImmigration.queryReceived}" /><a href="javascript:cal11.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    <td class="fieldLabel"  width="13%">Query Due :</td>
                    <td><s:textfield name="queryDue" cssClass="inputTextBlueSmall"  value="%{currentImmigration.queryDue}" /><a href="javascript:cal12.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    <td class="fieldLabel"  width="13%">Query Responded :</td>
                    <td><s:textfield name="queryResponded" cssClass="inputTextBlueSmall"  value="%{currentImmigration.queryResponded}"/><a href="javascript:cal13.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                </tr>
                <tr>
                    <td class="fieldLabel"  width="13%" >Query Comments :</td>
                    <td colspan="6"><s:textarea label="Comments" name="queryComments" cssClass="inputTextarea" cols="80" rows="1"  value="%{currentImmigration.queryComments}" onchange="fieldLengthValidator(this);" id="queryComments"/></td>
                </tr>
            </table>
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                <thead>
                    <th colspan="6" class="headerText" align="left">
                        B1 Details:
                    </th>
                </thead>
                <tr>
                    
                    <td class="fieldLabel"  width="10%" >B1 Filed :</td>                           
                    
                    <td><s:textfield name="b1Filed" cssClass="inputTextBlueSmall"  value="%{currentImmigration.b1Filed}"/><a href="javascript:cal14.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>                              
                    
                    <td class="fieldLabel"  width="100px" >B1 Interview :</td>
                    
                    <td><s:textfield name="b1Interview" cssClass="inputTextBlueSmall"  value="%{currentImmigration.b1Interview}" /><a href="javascript:cal15.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    
                    <td class="fieldLabel"  width="100px">B1 Start :</td>
                    
                    <td><s:textfield name="b1Start" cssClass="inputTextBlueSmall"  value="%{currentImmigration.b1Start}" /><a href="javascript:cal16.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>
                    
                </tr>
                
                <tr>
                <td class="fieldLabel"  width="10%" >B1 End :</td>                           
                
                <td><s:textfield name="b1End" cssClass="inputTextBlueSmall"   value="%{currentImmigration.b1End}"/><a href="javascript:cal17.popup();">
                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                     width="20" height="20" border="0"></td>                    
                
                <td class="fieldLabel"  width="100px">B1 Status   :</td>  
                
                <td><s:select
                        name="b1Status" 
                        
                    list="{'value','value1'}" cssClass="inputSelect"  value="%{currentImmigration.b1Status}"/>  </td>                           
                
                
                
                
            </table>
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                <thead>
                    <th colspan="6" class="headerText" align="left">
                        L1 Details:
                    </th>
                </thead>
                <tr>
                    <td class="fieldLabel"   width="10%">L1 Filed :</td>                           
                    
                    <td><s:textfield name="l1Filed" cssClass="inputTextBlueSmall"  value="%{currentImmigration.l1Filed}"/><a href="javascript:cal18.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>                                        
                    
                    <td class="fieldLabel"  width="100px">L1 Interview :</td>                           
                    
                    <td><s:textfield name="l1Interview" cssClass="inputTextBlueSmall"  value="%{currentImmigration.l1Interview}" /><a href="javascript:cal19.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>                                                                    
                    
                    <td class="fieldLabel"  width="100px" >L1 Start :</td>
                    
                    <td><s:textfield name="l1Start" cssClass="inputTextBlueSmall"  value="%{currentImmigration.l1Start}"/><a href="javascript:cal20.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>              
                    
                    
                </tr>
                
                <tr>
                    <td class="fieldLabel"  width="10%" >L1 End :</td>
                    
                    <td><s:textfield name="l1End" cssClass="inputTextBlueSmall"  value="%{currentImmigration.l1End}" /><a href="javascript:cal21.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>              
                    
                    <td class="fieldLabel"  width="100px" >L1 Status :</td>
                    
                    <td><s:select 
                            name="l1Status" 
                            
                        list="{'value','value1'}" cssClass="inputSelect"  value="%{currentImmigration.l1Status}"/>  </td>
                    
                </tr>
            </table>
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                <thead>
                    <th colspan="6" class="headerText" align="left">
                        Green Card Details:
                    </th>
                    </thead>
                <tr>
                    
                    
                    <td class="fieldLabel"  width="10%" >GC Applied :</td>
                    
                    <td><s:textfield name="gcApplied" cssClass="inputTextBlueSmall"  value="%{currentImmigration.gcApplied}" /><a href="javascript:cal22.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>              
                    
                    <td class="fieldLabel"   width="13%">GC Approved :</td>
                    
                    <td><s:textfield name="gcApproved" cssClass="inputTextBlueSmall" value="%{currentImmigration.gcApproved}"/><a href="javascript:cal23.popup();">
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                         width="20" height="20" border="0"></td>              
                    
                    <td class="fieldLabel"  width="13%">GC Ref  :</td>                           
                    
                    <td><s:textfield name="gcRef" cssClass="inputTextBlue" value="%{currentImmigration.gcRef}" onchange="fieldLengthValidator(this);"   id="gcRef" size="50"/></td>  
                    
                </tr>
                
                <tr>
                    
                    <td class="fieldLabel"  width="10%">GC Title  :</td>                           
                    
                    <td><s:textfield name="gcTitle" cssClass="inputTextBlue" value="%{currentImmigration.gcTitle}" onchange="fieldLengthValidator(this);" id="gcTitle" size="50"/></td>  
                    
                    <td class="fieldLabel"  width="13%">GC Status  :</td>                           
                    
                    <td><s:select 
                            name="gcStatus" 
                            
                        list="{'value','value1'}" cssClass="inputSelect" value="%{currentImmigration.gcStatus}"/>  </td>
                    
                    <td class="fieldLabel"  width="13%" >Other VISAS :</td>                           
                    
                    <td><s:select 
                            name="otherVisas" 
                            
                        list="{'value','value1'}" cssClass="inputSelect" value="%{currentImmigration.otherVisas}"/>  </td>  
                    
                    
                </tr>
                
                <tr>
                    
                    <td class="fieldLabel"   width="10%">Comments  :</td>                           
                    
                    <td colspan="6"><s:textarea name="comments" cssClass="inputTextarea" cols="80" rows="1" value="%{currentImmigration.comments}" onchange="fieldLengthValidator(this);" id="comment"/></td>  
                    
                </tr>
            </table>
            
            
        </s:form> 
        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['immigrationForm'].elements['passportIssue']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['immigrationForm'].elements['passportExp']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                           
                                                           var cal3 = new CalendarTime(document.forms['immigrationForm'].elements['dateOfArrival']);
                                                           cal3.year_scroll = true;
                                                           cal3.time_comp = false;
                                                           
                                                            var cal4 = new CalendarTime(document.forms['immigrationForm'].elements['ptStart']);
                                                           cal4.year_scroll = true;
                                                           cal4.time_comp = false;
                                                           
                                                            var cal5 = new CalendarTime(document.forms['immigrationForm'].elements['ptEnd']);
                                                           cal5.year_scroll = true;
                                                           cal5.time_comp = false;
                                                           
                                                            var cal6 = new CalendarTime(document.forms['immigrationForm'].elements['h1Filed']);
                                                           cal6.year_scroll = true;
                                                           cal6.time_comp = false;
                                                           
                                                            var cal7 = new CalendarTime(document.forms['immigrationForm'].elements['h1LinReceive']);
                                                           cal7.year_scroll = true;
                                                           cal7.time_comp = false;
                                                           
                                                            var cal8 = new CalendarTime(document.forms['immigrationForm'].elements['h1Start']);
                                                           cal8.year_scroll = true;
                                                           cal8.time_comp = false;
                                                           
                                                            var cal9 = new CalendarTime(document.forms['immigrationForm'].elements['h1Termination']);
                                                           cal9.year_scroll = true;
                                                           cal9.time_comp = false;
                                                           
                                                            var cal10 = new CalendarTime(document.forms['immigrationForm'].elements['h1End']);
                                                           cal10.year_scroll = true;
                                                           cal10.time_comp = false;
                                                           
                                                            var cal11 = new CalendarTime(document.forms['immigrationForm'].elements['queryReceived']);
                                                           cal11.year_scroll = true;
                                                           cal11.time_comp = false;
                                                           
                                                            var cal12 = new CalendarTime(document.forms['immigrationForm'].elements['queryDue']);
                                                           cal12.year_scroll = true;
                                                           cal12.time_comp = false;
                                                           
                                                            var cal13 = new CalendarTime(document.forms['immigrationForm'].elements['queryResponded']);
                                                           cal13.year_scroll = true;
                                                           cal13.time_comp = false;
                                                           
                                                            var cal14 = new CalendarTime(document.forms['immigrationForm'].elements['b1Filed']);
                                                           cal14.year_scroll = true;
                                                           cal14.time_comp = false;
                                                           
                                                            var cal15 = new CalendarTime(document.forms['immigrationForm'].elements['b1Interview']);
                                                           cal15.year_scroll = true;
                                                           cal15.time_comp = false;
                                                           
                                                            var cal16 = new CalendarTime(document.forms['immigrationForm'].elements['b1Start']);
                                                           cal16.year_scroll = true;
                                                           cal16.time_comp = false;
                                                           
                                                            var cal17 = new CalendarTime(document.forms['immigrationForm'].elements['b1End']);
                                                           cal17.year_scroll = true;
                                                           cal17.time_comp = false;
                                                           
                                                            var cal18 = new CalendarTime(document.forms['immigrationForm'].elements['l1Filed']);
                                                           cal18.year_scroll = true;
                                                           cal18.time_comp = false;
                                                           
                                                            var cal19 = new CalendarTime(document.forms['immigrationForm'].elements['l1Interview']);
                                                           cal19.year_scroll = true;
                                                           cal19.time_comp = false;
                                                           
                                                            var cal20 = new CalendarTime(document.forms['immigrationForm'].elements['l1Start']);
                                                           cal20.year_scroll = true;
                                                           cal20.time_comp = false;
                                                           
                                                            var cal21 = new CalendarTime(document.forms['immigrationForm'].elements['l1End']);
                                                           cal21.year_scroll = true;
                                                           cal21.time_comp = false;
                                                           
                                                            var cal22 = new CalendarTime(document.forms['immigrationForm'].elements['gcApplied']);
                                                           cal22.year_scroll = true;
                                                           cal22.time_comp = false;
                                                           
                                                            var cal23 = new CalendarTime(document.forms['immigrationForm'].elements['gcApproved']);
                                                           cal23.year_scroll = true;
                                                           cal23.time_comp = false;
        </script>
       <%-- </sx:div > --%> 
        <!--//END TAB : -->
        <%--       <s:div id="attachmentTab" label="Immigration Add"  >
                                                    
                                                    <%!
                                                    /* Declarations */
                                                    Connection connection;
                                                    String queryString;
                                                    String currentAccountId;
                                                    String strTmp;
                                                    String strSortCol;
                                                    String strSortOrd;
                                                    
                                                    int intSortOrd = 0;
                                                    int intCurr;
                                                    boolean blnSortAsc = true;
                                                    %>
                                                    
                                                    <%
                                                    
                                                    
                                                    /* String Variable for storing current position of records in dbgrid*/
                                                    strTmp = request.getParameter("txtCurr");
                                                    
                                                    intCurr = 1;
                                                    
                                                    if (strTmp != null)
                                                        intCurr = Integer.parseInt(strTmp);
                                                    
                                                    /* Specifing Shorting Column */
                                                    strSortCol = request.getParameter("Colname");
                                                    
                                                    if (strSortCol == null) strSortCol = "AttachmentName";
                                                    
                                                    strSortOrd = request.getParameter("txtSortAsc");
                                                    if (strSortOrd == null) strSortOrd = "ASC";
                                                    
                                                    try{
                                                        
                                                        /* Getting DataSource using Service Locator */
                                                        connection=ConnectionProvider.getInstance().getConnection();
                                                        
                                                        System.out.println("connection is*******"+connection);
                                                        /* Sql query for retrieving resultset from DataBase */
                                                        queryString  =null;
                                                        //  queryString = "SELECT * from tblEmpTraining";
                                                        String immigrationAction = "../employee/immigration.action";
                                                        
                                                        queryString="SELECT EmpId,PassportNo,I94Number,CurrentImmigStatus,Comments from tblEmpImmigration";
                                                    %>
                                                    <form method="post" id="frmDBGrid" name="frmDBGrid" action=""> 
                                                        
                                                        <!-- DataGrid for list all activities -->

                                                        <grd:dbgrid  id="tblEmpImmigration" name="tblEmpImmigration" width="100" pageSize="8" 
                                                                     currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                     dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable"
                                                        >
                                                            
                                                            <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                           imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                           addImage="../includes/images/DBGrid/Add.png" 
                                                                           addAction="<%=immigrationAction%>"/>
                                                            <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" />
                                                            
                                                            <grd:imagecolumn  headerText="Edit" width="5"  HAlign="center" 
                                                                              imageSrc="../includes/images/DBGrid/Edit.gif" 
                                                                              linkUrl="getImmigration.action?EmpId={EmpId}" 
                                                                              imageBorder="0" imageWidth="16" imageHeight="16" 
                                                                              alterText="Click to edit" /> 
                                                            
                                                            <grd:textcolumn dataField="PassportNo"       headerText="PassportNo" width="25" sortable="true"/>
                                                            <grd:textcolumn dataField="I94Number"  headerText="I94Number"   width="25" sortable="true"/>
                                                            <grd:textcolumn dataField="CurrentImmigStatus"  headerText="CurrentImmigStatus"   width="25" sortable="true"/>
                                                            <grd:textcolumn dataField="Comments"       headerText="Comments" width="30" sortable="true" dataFormat=""/> 
                                                            
                                                        </grd:dbgrid>
                                                        
                                                        <!-- these components are DBGrid Specific  -->
                                                        <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                        <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                        <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                        <input id="txtDBId" TYPE="hidden" NAME="txtDBId" VALUE="0">
                                                        <input id="txtTRId"TYPE="hidden" NAME="txtTRId" VALUE="1">       
                                                    </form>                                        
                                                    <%
                                                    }catch(Exception ex){
                                                        out.println(ex.toString());
                                                    }finally{
                                                        if(connection!= null){
                                                            connection.close();
                                                        }
                                                    }
                                                    
                                                    %>
                                                </s:div>--%>
    </div>
    <!--//END TAB : -->                              
    <%-- </sx:tabbedpanel> --%>
</div>
<!--//END TABBED PANNEL : -->

<!--//END TABBED PANNEL : -->
<script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>
</td>
</tr>
</table>
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
</html>

