<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  Feb 6, 2008, 4:25 PM
 *
 * Author  : Venkateswara Rao<vnukala@miraclesoft.com>
 *
 * File    : PhoneLog.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib  prefix="grd" uri="/WEB-INF/tlds/datagrid.tld" %>

<html>
    <head>
        <title>Hubble Organization Portal :: Employee PhoneLog</title>
         <sx:head cache="true"/>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/timepicker.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ActivityClientValidation.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/RegistrationClientValidation.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/PhoneLog.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
               
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
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
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                    
                                    <tr>
                                        <td valign="top">
                                            <!--//START TABBED PANNEL : -->
                                            <sx:tabbedpanel id="phoneLogPanel" cssStyle="width: 845px; height: 350px;padding-left:10px;padding-top:5px;" doLayout="true">
                                                
                                                <!--//START TAB : -->
                                                <sx:div id="phoneTab" label="Phone Log" >
                                                    <s:form name="phoneLogForm" id="phoneLogForm" action="%{currentAction}" theme="simple" method="post" validate="true" onsubmit="return validation();">
                                                        <table width="100%" cellpadding="0" cellspacing="4" border="0">
                                                            <tr align="right">
                                                                <td class="headerText" colspan="6">
                                                                    <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                    }%>
                                                                    <s:submit label="Submit" value="Save" cssClass="buttonBg" />
                                                                    <!--HiddenFields START-->
                                                                    <s:hidden name="reconcilled" id="reconcilled" value="false"/>   
                                                                    <s:hidden name="start" id="start" value="start"  />  
                                                                    <s:hidden name="end" id="end" value=""  />  
                                                                    <s:hidden name="phoneLogId" id="phoneLogId" />
                                                                    
                                                                    <!--HiddenFields END-->
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="5">                                                        
                                                                    <!-- for displaying validation Errors and Action Messages -->
                                                                    <s:actionerror/>
                                                                    <s:actionmessage/>
                                                                    <s:fielderror/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td width="130px"class="fieldLabel">Employee Id :</td>
                                                                <td><s:textfield name="employeeLoginId" id="employeeLoginId" cssClass="inputTextBlue" value="%{employeeLoginId || currentPhoneLogVTO.employeeLoginId}" readonly="true"/></td> 
                                                                <td class="fieldLabel">Date :</td>
                                                                <td colspan="2"><s:textfield name="date" id="date" readonly="true" cssClass="inputTextBlue" value="%{date||currentPhoneLogVTO.date}" /> </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">From :</td>
                                                                <td><s:textfield name="fromPhoneNo" id="fromPhoneNo" cssClass="inputTextBlue" value="%{fromPhoneNo||currentPhoneLogVTO.fromPhoneNo}" readonly="true" /></td>
                                                                <td class="fieldLabel">To :</td>                                                          
                                                                 <td><s:textfield name="toPhoneNo" id="toPhoneNo" cssClass="inputTextBlue" value="%{currentPhoneLogVTO.toPhoneNo}"   onchange="return formatPhone(this);" /></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                
                                                                <td class="fieldLabel">Time :</td>
                                                                 <td>
                                                                    <s:textfield name="startTime" value="" id="startTime" cssClass="inputTextBlueSmall" value="%{currentPhoneLogVTO.startTime}"  />
                                                                   
                                                                   <IMG SRC="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/timepicker/timepicker.gif" BORDER="0" ALT="Pick a start Time!" ONCLICK="selectTime(this,startTime)" >
                                                                
                                                                 <s:textfield name="endTime" id="endTime" cssClass="inputTextBlueSmall"  value="%{currentPhoneLogVTO.endTime}" onblur="validation();"  />
                                                                   <IMG SRC="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/timepicker/timepicker.gif" BORDER="0" ALT="Pick a end Time!" ONCLICK="selectTime(this,endTime)" > </td>
                                                               
                                                                <td class="fieldLabel">Call Duration :</td>
                                                                <td colspan="2"><s:textfield name="callDuration" id="callDuration" cssClass="inputTextBlue" value="%{currentPhoneLogVTO.callDuration}"  /></td>
                                                                
                                                            </tr>
                                                                                                                     
                                                            <tr>
                                                                <td class="fieldLabel">Type :</td>
                                                                <td class="fieldLabelLeft" colspan="4"><s:radio name="callType" id="callType" list="{'Personal','Official'}"   value="%{currentPhoneLogVTO.callType}" onclick="getCallDuration();" /></td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                
                                                                <td  class="fieldLabel" >Description :</td>                                                                  
                                                                <td  colspan="4"><s:textarea name="description" id="description" cols="77" rows="5" cssClass="inputTextArea"value="%{currentPhoneLogVTO.description}"/></td>
                                                            </tr>
                                                            
                                                        </table>
                                                    </s:form>
                                                    <script type="text/JavaScript">
                                                 var cal1 = new CalendarTime(document.forms['phoneLogForm'].elements['startTime']);
				                 cal1.year_scroll = false;
				                 cal1.time_comp = true;
                                                 var cal11 = new CalendarTime(document.forms['phoneLogForm'].elements['endTime']);
				                 cal11.year_scroll = false;
                                                 cal11.time_comp = true;
                                                                                         
                                                  </script>
                                                    
                                                </sx:div >
                                                <!--//END TAB : -->
                                    
                                            </sx:tabbedpanel>
                                            <!--//END TABBED PANNEL : -->
                                
                                            <!--//START TABBED PANNEL : -->
                                            
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>
</html>
