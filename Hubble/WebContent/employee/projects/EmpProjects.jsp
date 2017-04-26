<%--
/*******************************************************************************
 *
 * Project : Mirage V2
 *
 * File    : EmpProjectDetails.jsp
 *
 * Package :
 *
 * $Author: hkondala $  
 *
 * $Date: 2009-03-31 10:48:48 $         
 *
 * $Header: /Hubble/Hubble_CVS/Hubble/web/employee/projects/EmpProjects.jsp,v 1.2 2009-03-31 10:48:48 hkondala Exp $
 *
 * $Id: EmpProjects.jsp,v 1.2 2009-03-31 10:48:48 hkondala Exp $
 *
 * $Log: EmpProjects.jsp,v $
 * Revision 1.2  2009-03-31 10:48:48  hkondala
 * cache=true
 *
 * Revision 1.1  2009-01-21 22:04:25  hkondala
 * Hubble Version 1.3
 *
 *
 * $Name:  $
 *
 * $Revision: 1.2 $
 *
 * $Source: /Hubble/Hubble_CVS/Hubble/web/employee/projects/EmpProjects.jsp,v $  
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 
 * *****************************************************************************
 */
 --%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 0
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        
        <title>Hubble Organization Portal :: Project Details</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/MarketingAjaxUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ProjectsUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/datetimepicker.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    </head>
    
   <%-- <body class="bodyGeneral" onload="check()" oncontextmenu="return false;"> --%>
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
                            <!--//END DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <!--//START TABBED PANNEL : -->
                                
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    
                                    <tr>
                                        <td valign="top">
                                            <!--//START TABBED PANNEL : -->
                                            <sx:tabbedpanel id="projectPannel" cssStyle="width: 840px;height: 500px; padding-left:10px; padding-top:5px;" doLayout="true">
                                                
                                                <!--//START TAB : -->
                                                <sx:div id="ProjectDetailsTab" label="Project Details"  >
                                                    <s:form name="frmProjectEdit" action="addProjectDetails" theme="simple">
                                                        
                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                            
                                                            
                                                            <tr align="left">
                                                                <td class="headerText">
                                                                    Project Details:
                                                                </td>
                                                                <td class="headerText" align="right" colspan="3"><s:property value="#request.resultMessage"/>
                                                                    <input type="button" value="Clear" class="buttonBg" onclick="getClearData();"/>
                                                                    <input type="button" value="Cancel" class="buttonBg" onclick="goBack();"/>
                                                                    
                                                                    <input type="submit" value="Save" class="buttonBg"/>
                                                                    <s:hidden name="projId" id="projId" />
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <s:if test="statusCode == 1 || statusCode == 2 || statusCode == 3 || statusCode == 4">
                                                                    <td class="fieldLabel" >Project Name:</td>                                                    
                                                                    <td><s:textfield name="prjName" id="prjName" cssClass="inputTextBlueLarge" value="%{projVTO.prjName}" readonly="true"/></td>  
                                                                </s:if>
                                                                <s:else>
                                                                    <td class="fieldLabel" >Project Name:</td>                                                    
                                                                    <td><s:textfield name="prjName" id="prjName" cssClass="inputTextBlueLarge" /></td>  
                                                                </s:else>
                                                                <td class="fieldLabel">Project Manager :</td>
                                                                <td>
                                                                    <s:if test="statusCode == 3 || statusCode == 4">
                                                                        <s:if test="%{projVTO.prjManagerUID != null}">
                                                                            <s:select list="reportsToDefault" name="prjManagerUID" id="prjManagerUID" headerKey="" headerValue="-- Please Select --" cssClass="inputSelect" value="{projVTO.prjManagerUID}" disabled="true"/>
                                                                        </s:if>
                                                                    </s:if>
                                                                    <s:elseif test="%{projVTO.prjManagerUID != null}">
                                                                        <s:select list="reportsToDefault" name="prjManagerUID" id="prjManagerUID" headerKey="" headerValue="-- Please Select --" cssClass="inputSelect" value="{projVTO.prjManagerUID}"/>
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <s:select list="reportsToDefault" name="prjManagerUID" id="prjManagerUID" headerKey="" headerValue="-- Please Select --" cssClass="inputSelect" value="{#session.userId}"/>
                                                                    </s:else>
                                                                    
                                                                    
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel" align="right">Start Date :</td>
                                                                <s:if test="statusCode == 2 || statusCode == 3 || statusCode == 4">
                                                                    <td align="left"><s:textfield name="startDate" id="startDate" cssClass="inputTextBlue" value="%{projVTO.startDate}" readonly="true"/><a href="javascript:NewCal('startDate','ddmmmyyyy',false)">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td align="left"><s:textfield name="startDate" id="startDate" cssClass="inputTextBlue" value="%{projVTO.startDate}"/><a href="javascript:NewCal('startDate','ddmmmyyyy',false)">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </td>
                                                                </s:else>
                                                                <s:hidden id="startDate1" name="startDate1" value="%{projVTO.startDate}"/>
                                                                <td class="fieldLabel">Duration :</td>
                                                                <td><s:textfield name="projectDuration"  id="projectDuration" cssClass="inputTextBlue" value="" onblur="check()"/> </td>
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel">End Date :</td>
                                                                <s:if test="statusCode == 3 || statusCode == 4">
                                                                    <td><s:textfield name="endDate" id="endDate" cssClass="inputTextBlue" value="%{projVTO.endDate}" readonly="true"/><a href="javascript:NewCal('endDate','ddmmmyyyy',false)">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td><s:textfield name="endDate" id="endDate" cssClass="inputTextBlue" value="%{projVTO.endDate}"/><a href="javascript:NewCal('endDate','ddmmmyyyy',false)">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </td>
                                                                </s:else>
                                                                <td class="fieldLabel">Project Type :</td>
                                                                <td>
                                                                    <s:if test="statusCode == 2 || statusCode == 3 || statusCode == 4">
                                                                        <s:select list="prjTypeMap" name="projectType"  id="projectType" value="%{projVTO.projectType}" headerKey="" headerValue="-- Please Select --" cssClass="inputSelect" disabled="true"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:select list="prjTypeMap" name="projectType"  id="projectType" value="%{projVTO.projectType}" headerKey="" headerValue="-- Please Select --" cssClass="inputSelect"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            
                                                            
                                                            
                                                            <tr align="left">
                                                                <td class="headerText" colspan="4">
                                                                    Customer Details:
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <s:if test="statusCode == 1 || statusCode == 2 || statusCode == 3 || statusCode == 4">
                                                                    <td class="fieldLabel">Cutomer Name :</td>
                                                                    
                                                                    <td> <s:textfield name="accName" id="accName" cssClass="inputTextBlue" value="%{projVTO.accName}" onkeyup="fillAccount(),clearTable();" autocomplete="off" readonly="true" />
                                                                        <div style="z-index:9000; display: none; position: absolute; overflow: auto;" id="menu-popup">
                                                                            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed black;opacity: .8;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                        </div>
                                                                        <div id="validationMessage"></div>
                                                                        <s:hidden name="accountId" id="accountId" value="" cssClass="inputTextBlue"/> 
                                                                    </td>
                                                                </s:if>
                                                                <s:else>
                                                                    <td class="fieldLabel">Cutomer Name :</td>
                                                                    
                                                                    <td> <s:textfield name="accName" id="accName" cssClass="inputTextBlue" onkeyup="fillAccount(),clearTable();" autocomplete="off"  />
                                                                        <div style="z-index:9000; display: none; position: absolute; overflow: auto;" id="menu-popup">
                                                                            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed black;opacity: .8;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                        </div>
                                                                        <div id="validationMessage"></div>
                                                                        <s:hidden name="accountId" id="accountId" value="" cssClass="inputTextBlue"/> 
                                                                    </td>
                                                                </s:else>
                                                                
                                                            </tr>
                                                            
                                                            
                                                            
                                                        </table>
                                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmProjectEdit'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['frmProjectEdit'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                        </script>
                                                    </s:form>
                                                </sx:div>
                                                
                                                <!--//END TAB : -->
                                            </sx:tabbedpanel>
                                            <!--//END TABBED PANNEL : -->
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
        <script type="text/javascript">
		$(window).load(function(){
			check();

		});
		</script>
    </body>
</html>
