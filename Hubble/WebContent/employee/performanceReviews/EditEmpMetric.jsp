<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 08, 2013, 3:25 PM
 *
 * Author  :  Ajay Tummapala
 *
 * File    : TechReviews.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.`
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.mss.mirage.employee.tasks.TasksVTO"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Consultant Review Details</title>
        <%--  <sx:head cache="true"/> 
           <sj:head jqueryui="true"/>--%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   


        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>




        <script type="text/javascript">
  
        </script>    

        <style type="text/css">

            .popupItem:hover {
                background: #F2F5A9;
                font: arial;
                font-size:10px;
                color: black;
            }

            .popupRow {
                background: #3E93D4;
            }

            .popupItem {            
                padding: 2px;
                width: 100%;
                border: black;
                font:normal 9px verdana;
                color: white;
                text-decoration: none;
                line-height:13px;
                z-index:100;
            }

        </style>



    </head>

    <%!
        /* Declarations */
        Connection connection;
        String accountPrimaryTeamMember;
        String userId;
        String userRoleName;
        int isUserManager;
        String queryString;
        String currentAccountId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
    %>
    <%-- <body class="bodyGeneral"  oncontextmenu="return false;" onload="init();">  --%>
    <body class="bodyGeneral"  oncontextmenu="return false;"> 

        <!-//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp" />                    
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">
                                <ul id="metricsTabs" class="shadetabs" >

                                    <li ><a href="#" class="selected" rel="editMetrics"  >Edit Metrics</a></li>
                                </ul>
                                <!--//START TABBED PANNEL : -->
                                <div  style="border:1px solid gray; width:830px;height: 200px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                    <div id="editMetrics" class="tabcontent" style="margin-top: 20px;" >
                                        <s:form name="metricsForm" id="metricsForm" theme="simple" action="doEditMetrics">
                                            <table cellpadding="1" cellspacing="1" border="0" width="60%"  align="center">
                                                   <s:hidden name="id" id="id" value="%{performanceVTO.id}"/>
                                                   <tr align="center">
                                <td class="headerText12" colspan="4" >
                                    <s:property value="#request.resultMessage"/>
                                    <s:property value="%{resM}"/>
                                    <s:property value="#session.resultMsg"/>
                                    <%
                                    if(session.getAttribute("resultMsg") != null) {
                                        session.removeAttribute("resultMsg");
                                    }
                                    %>
                                </td></tr>  
                                                <tr>
                                                    <td class="fieldLabel">Metric Name :<FONT color="red"  ><em>*</em></FONT></td> 
                                                    <td ><s:textfield cssClass="inputTextBlue" name="metricName" id="metricName" value="%{performanceVTO.metricName}"/></td>
                                                    <td colspan="2"><label class="fieldLabel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Min :<FONT color="red"  ><em>*</em></FONT></label>
                                                        <s:textfield cssClass="inputTextBlue3" name="minValue" id="minValue" value="%{performanceVTO.minValue}"/>
                                                        <label class="fieldLabel">Max :<FONT color="red"  ><em>*</em></FONT></label>
                                                        <s:textfield cssClass="inputTextBlue3" name="maxValue" id="maxValue" value="%{performanceVTO.maxValue}"/>
                                                    </td> 
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Status :</td>
                                                    <td><s:select list="{'Active','InActive'}"
                                                              name="statusId" id="statusId"
                                                              value="%{performanceVTO.statusId}"
                                                              cssClass="inputSelect"  /></td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Description:</td>
                                                    <td colspan="3"><s:textarea rows="3" cols="65" name="comments" cssClass="inputTextarea4" id="comments" value="%{performanceVTO.comments}" onchange="fieldLenghtValidator(this);"/></td>
                                                </tr>
                                                <tr><td></td><td></td><td colspan="4" align="center"><br>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <s:submit cssClass="buttonBg" value="Update"/>
                                                    </td></tr> 
                                            </table>
                                        </s:form>
                                    </div>                                       
                                </div>                               
                                <%--  </sx:tabbedpanel> --%>
                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("metricsTabs")
                                    countries.setpersist(false)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()

                                </script>
                                <!-- Attachments Grid Start -->


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
<script type="text/javascript">
		$(window).load(function(){
			init();

		});
		</script>
    </body>
</html>
