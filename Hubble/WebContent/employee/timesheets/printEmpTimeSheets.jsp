<!--/*******************************************************************************
/*
 *Project: MirageV2
 *
 *$Author: vnukala $
 *
 *$Date: 2011-04-07 13:09:16 $
 *
 *$Revision: 1.4 $
 *
 *$Source: /Hubble/Hubble_CVS/Hubble/web/employee/timesheets/TeamTimeSheetSearch.jsp,v $
 *
 * @(#)TeamTimeSheetSearch.jsp	May 03, 2008, 2:17 AM 
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/ -->

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ page import="com.mss.mirage.util.*,java.util.Date" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        
        <title>Hubble Organization Portal :: Team TimeSheet Search</title>
        
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/struts/tabs.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/GridStyle.css"/>" type="text/css">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        
        <script language="javascript" src="<s:url value="/includes/javascripts/TimeSheetWeekDays.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        
        <s:include value="/includes/template/headerScript.html"/>
        
        <script type="text/JavaScript">
            
            function checkDates()
            {
            
            var fromDate = document.getElementById('startDate').value;
            var toDate = document.getElementById('endDate').value;
            
            if(fromDate!=null && fromDate!="")
            {
 
                if(toDate!=null && toDate!="")
                {
                return true;
                }
                else{
                alert("Please Enter End Date ");
                return false;
                }
            }
            else
            {
            alert("Please Start Date ");
            return false;
            }
            return true;
           
           
            
            }
            
        </script>
    </head>
    
    <body class="bodyGeneral"  oncontextmenu="return false;">
        
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
            
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>                    
                </td>
            </tr>
            <tr>
                <td>
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="150px;" class="leftMenuBgColor" valign="top">
                                <s:include value="/includes/template/LeftMenu.jsp"/>                    
                            </td>
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">  
                                    <tr>
                                        <td style="padding: 5px 5px;">
                                            <ul id="accountTabs" class="shadetabs" >
                                                <li ><a href="#" class="selected" rel="teamTimeSheetsListTab"  >TimeSheet</a></li>
                                            </ul> 
                                            <%--  <sx:tabbedpanel  id="teamTimeSheetsListPannel" cssStyle="width: 845px; height: 400px;padding: 5px 5px;" doLayout="true">--%>
                                            <div  style="border:1px solid gray; width:845px;height: 400px; overflow:auto; margin-bottom: 1em;">
                                                <div id="teamTimeSheetsListTab" class="tabcontent"  >
                                                    <%-- <sx:div id="teamTimeSheetsListTab" label="TimeSheet" theme="ajax" > --%>
                                                    <table cellpadding="0" width="100%" cellspacing="0" bordercolor="#efefef">  
                                                        <tr class="headerText">
                                                            <td align="right">
                                                                <%
                                                                if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null){
                                                                    out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                }
                                                                %>   
                                                            </td>
                                                            <td align="right">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                    
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <table cellpadding="1" cellspacing="2" border="0" width="100%">
                                                                    <tr>
                                                                        <td>
                                                                            <!-- We have to implement Search login in backend-->
                                                                            <s:form name="teamSearch" action="getEmpTimeSheetsReport" onsubmit="return checkDates();" theme="simple" target="_blank">
                                                                                
                                                                                <tr>
                                                                                    <td class="fieldLabel">Start Date(mm/dd/yyyy):</td>
                                                                                    <td  ><s:textfield name="startDate" id="startDate" cssClass="inputTextBlue" /><a style="text-decoration: none" href="javascript:cal1.popup();">
                                                                                        <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                    </td>
                                                                                    
                                                                                    <td  class="fieldLabel">End Date(mm/dd/yyyy):</td>
                                                                                    <td > <s:textfield name="endDate"  id="endDate" cssClass="inputTextBlue"/><a style="text-decoration: none" href="javascript:cal2.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                    </td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td   class="fieldLabel">EmpFirstName :</td>
                                                                                    <td >
                                                                                        <s:textfield name="empFName" id="empFName" cssClass="inputTextBlue"/>
                                                                                    </td>
                                                                                    <td  class="fieldLabel">EmpLastName :</td>
                                                                                    <td >
                                                                                        <s:textfield name="empLName" id="empLName" cssClass="inputTextBlue"/>
                                                                                    </td>
                                                                                    <td> <s:submit name="submit" value="Generate Report"  cssClass="buttonBg"/>
                                                                                    </td>
                                                                                </tr>
                                                                            </s:form>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        
                                                    </table>
                                                    <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['teamSearch'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['teamSearch'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                    </script>
                                                    <%-- </sx:div> --%>
                                                </div>
                                                <%-- </sx:tabbedpanel> --%>
                                            </div>
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
                        </tr>
                    </table>
                </td>
            </tr>
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
        </table>
    </body>
</html>
