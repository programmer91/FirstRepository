<!-- 
 /* ******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : IssueDetails.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Employee Leaves Applied</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <!-- issues Related JavaScript  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/totalLeaves.js?ver=1.0"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="empSearchTab"  > Leaves Search</a></li>
                                </ul>
                                <%-- <sx:tabbedpanel id="empSearch" cssStyle="width: 845px; height: 500px;padding:10px 5px 5px 5px" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:div id="empSearchTab" label="Employee Search" cssStyle="overflow: auto;"> --%>
                                    <div id="empSearchTab" class="tabcontent"  >
                                        <s:form action="" theme="simple" name="searchForm">
                                            <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td>
                                                        <table align="center" border="0" width="80%" cellpadding="0" cellspacing="0">
                                                            <tr>
                                                                <%--<td class="fieldLabel">Name:</td>
                                                                <td><s:select list="#session.myTeamMap" name="employee" id="employee" onchange="load(event);" headerValue="select" headerKey="--select--" /></td> --%>
                                                                <td class="fieldLabel">Name&nbsp;:</td>
                                                                <td><s:select list="#session.MyTeamMapForLeaveSearch" name="employee" id="employee"  headerValue="--Please Select--" headerKey="-1" cssClass="inputSelect"/></td>
                                                                <td class="fieldLabel">Year(YYYY)&nbsp;:</td>
                                                                <td><input type="text" name="year" autocomplete="off" maxlength="4"  id="year" class="inputTextBlue" value="2017"/></td>
                                                                <td style="padding-left:15px;"><input type="button" value="Search" class="buttonBg" onclick="load(event);"/></td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <br>
                                                        <div align="center" ><b><font size="2px" color="green" >Note: Total leaves displayed excludes "Saturday" and "Sunday". It has no information about Holidays. So Total Leaves includes Holidays between the Leave days.</font></b></div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <div id="load" style="display:none;" class="error" align="center" ><b><font size="4px" >Loading...Please Wait</font></b></div>
                                                    </td>
                                                </tr>
                                                
                                                <tr><td><br>
                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                        <table id="tblUpdate" align="center"  
                                                               cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                            <COLGROUP ALIGN="left" >
                                                            <COL width="10">
                                                            <COL width="250">
                                                            <COL width="200">
                                                            <COL width="220">
                                                            <COL width="200">
                                                            <COL width="10">
                                                        </table>
                                                        <br>
                                                        
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                        <%--  </sx:div> --%>
                                    </div>
                                    <%--  </sx:tabbedpanel> --%>
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