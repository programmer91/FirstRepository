<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * $Date: 2009-04-07 13:19:25 $
 *
 * $Author: vnukala $
 *
 * $Name:  $
 *
 * $Revision: 1.3 $
 * 
 * 
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/tml; charset=UTF-8">
        <title>Hubble Organization Portal :: Employee Termination</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>           
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <!-- issues Related JavaScript  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        
    </head>
    <body  class="bodyGeneral" oncontextmenu="return false;">
        
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
            
            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>
                </td>
            </tr>
            
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="general"  >Termination Details</a></li>
                                    
                                </ul>
                                
                                <div  style="border:1px solid gray; width:845px; overflow:auto; margin-bottom: 1em;">
                                    <%-- <sx:tabbedpanel  id="empSelfAppraisal" cssStyle="width: 845px;padding:10px 5px 5px 5px" doLayout="false" useSelectedTabCookie="true"> --%>
                                    <%--  <sx:div id="general" label="Termination Details"> --%>
                                    <div id="general" class="tabcontent"  >
                                        <s:form action="updateTemination" theme="simple" name="terminate">
                                            <s:hidden name="empId" id="empId"></s:hidden>
                                            <table border="0" width="100%" cellpadding="5" cellspacing="0" align="center">
                                                
                                                <tr class="headerText">
                                                    
                                                    <td align="right" colspan="5">
                                                        <% if(request.getAttribute(ApplicationConstants.RESULT_MSG) != null){
                                                            out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                        }%>
                                                        <s:submit label="Submit" value="Save" cssClass="buttonBg" />
                                                    </td>
                                                    <td valign="right">
                                                        <a href="<s:url value="empBackToList.action"/>" style="align=center;">
                                                            <img alt="Back to List"
                                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                 width="66px" 
                                                                 height="19px"
                                                             border="0" align="bottom"></a>
                                                    </td>
                                                    
                                                </tr>
                                                
                                                <tr style="font-family: arial,verdana; font-size:12px;" >
                                                    <td class="fieldLabel" >Name :</td>
                                                    <td align="left" class="fieldLabelLeft">
                                                        <s:property value="%{employeeName}"/> 
                                                    </td> 
                                                </tr>
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    
                                                    <td  class="fieldLabel" >Designation :</td>
                                                    <td><s:textfield name="designation" cssClass="inputTextBlueLarge" value="%{empTerminateVTO.designation}" /></td>
                                                    <td rowspan="5">                                                      
                                                        <s:url id="image" action="renderImage.action?empId=%{empTerminateVTO.empId}" namespace="/employee"/>
                                                        <img alt="Employee Image" align="left" src="<s:property value="#image"/>" style="border-width: 4px 4px 4px 4px;border-spacing: 2px;border-style: double double double double;border-color: black black black black;border-collapse: separate;" height="150" width="150"><br>
                                                    </td>
                                                </tr>
                                                
                                                
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    <td class="fieldLabel">Team Name :</td>
                                                    <td><s:textfield name="teamName" cssClass="inputTextBlueLarge" value="%{empTerminateVTO.teamName}" /></td>
                                                </tr>
                                                
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    <td class="fieldLabel">Date of Join :</td>
                                                    <td> <s:textfield name="dateOfJoin" id="dateOfJoin" cssClass="inputTextBlueLarge" value="%{empTerminateVTO.dateOfJoin}"/>
                                                        <a href="javascript:cal1.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a> 
                                                        
                                                    </td>
                                                </tr>
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    <td class="fieldLabel">Itg Batch :</td>
                                                    <td><s:textfield name="itgBatch" cssClass="inputTextBlueLarge" value="%{empTerminateVTO.itgBatch}" /></td>
                                                </tr>
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    <td class="fieldLabel">Date of Termination :</td>
                                                    <td><s:textfield name="dateOfTermination" id="dateOfTermination" cssClass="inputTextBlueLarge" value="%{empTerminateVTO.dateOfTermination}"/>
                                                        <a href="javascript:cal2.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a> 
                                                        
                                                    </td>
                                                </tr>
                                                
                                                
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    <td  class="fieldLabel">Current Status:</td>
                                                    <td><s:select list="currStatusList" id="currStatus" name="currStatus" headerKey="All"  headerValue="All" cssClass="inputSelect" value="%{currStatus ||  empTerminateVTO.currStatus }"/></td>
                                                </tr>
                                                
                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                    <td class="fieldLabel" >Reasons for Termination :</td>  
                                                    <td colspan="6"><s:textarea name="resonsForTerminate" cols="77" rows="5" cssClass="inputTextArea" value="%{empTerminateVTO.resonsForTerminate}"/></td>
                                                </tr>
                                                
                                                
                                            </table>
                                            
                                        </s:form>
                                        <script type="text/JavaScript">
         var cal1 = new CalendarTime(document.forms['terminate'].elements['dateOfJoin']);
         cal1.year_scroll = true;
         cal1.time_comp = false;
          var cal2 = new CalendarTime(document.forms['terminate'].elements['dateOfTermination']);
         cal2.year_scroll = true;
         cal2.time_comp = false;
                                        </script>  
                                        <%--  </sx:div> --%>
                                    </div>
                                    
                                    <!-- Self Assessment Div  -->                                    
                                    
                                    
                                    <%-- </sx:tabbedpanel> --%>
                                </div>
                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                            </td>
                            <!--//END DATA COLUMN : Coloumn for Screen Content-->
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
        </table>
    </body>
</html>

