<%-- 
    Document   : OppurtunityDetailsList
    Created on : Apr 27, 2016, 5:51:13 PM
    Author     : miracle
--%>

 <%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Opportunity Details Search</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/InvestmentScripts.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script>

        <s:include value="/includes/template/headerScript.html"/> 
    </head>
   <!-- <body class="bodyGeneral" onload="getOppurtunityList();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">


        <%!    /*
             * Declarations
             */
            Connection connection;
            String queryString;
            //StringBuffer queryString;
            String strTmp;
            String strSortCol;
            String strSortOrd;
            String userId;
            String submittedFrom;
            String action;
            //new
            String userRoleName;
            int role;
            int intSortOrd = 0;
            int intCurr;
            boolean blnSortAsc = true;
        %>
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
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/> 
                            </td>
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="requirementList"  >Opportunity List</a></li>
                                    <!-- <li ><a href="#" rel="searchDiv">Requirement Search</a></li>-->
                                </ul>
                                <div  style="border:1px solid gray; width:845px;height: 530px; overflow:auto; margin-bottom: 1em;">
                                   

                                       <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%" >
 <tr>
                                                                <td class="headerText" colspan="11" align="right">
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0"/>
                                                                    
                                                                    <%
                                            if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                            }
                                        %> <a href="<s:url action="leadGen"></s:url>" style="align:center;">
                                                                    <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                    width="66px" 
                                                                    height="19px"
                                                                    border="0" align="bottom"></a>
                                                                </td>
                                                            </tr>
                                            <tr>
                                                <td>
                                                     <s:form name="frmInvSearch"  action="" theme="simple" >
                                                         
                                                    <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                                        
                                                        <tr>
                                                            <td class="fieldLabel" align="left">Investment Name :</td>
                                                            <td style="font-size: 13px;font-style: normal;color:green "><s:label><s:property  value="investmentName" /></s:label></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" class="fieldLabel" >Account Name :</td>
                                                            <td ><s:textfield name="oppurtunityAccountName" id="oppurtunityAccountName" cssClass="inputTextBlue"/>
                                                            <td class="fieldLabel">Created By ID : </td>
                                                            <td><s:select list="salesMap"  name="oppurtunitiesPersonId" id="oppurtunitiesPersonId" value="%{oppurtunitiesPersonId}" headerKey="" headerValue="-- Please Select --" cssClass="inputSelect" /></td>
                                                           
                                                        </tr><tr>
                                                            <td colspan="3"></td>
                                                            <td>
                                                                <br>
                                                                <input type="button" class="buttonBg"  align="right"  value="Search" onclick="getOppurtunityList();"/>

                                                            </td>

                                                        </tr>

                                                        <s:hidden name="investmentId" id="investmentId" value="%{investmentId}"/>
                                                        <s:hidden name="investmentName" id="investmentName" value="%{investmentName}"/>






                                                    </table>  
                                                         </s:form>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <table width="100%" cellpadding="1" cellspacing="1" border="0">
                                                        <tr>
                                                            <td>
                                                                <div id="loadingMessage12" style="color:red;display: none" align="center" ><b><font size="4px" >Loading...Please Wait...</font></b></div>
                                                                <%--<div id="loadingMessage12" style="display:none;" class="error" align="center" ><b><font size="4px" >Loading...Please Wait</font></b></div>--%>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3" >
                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                <table id="tblOppurtunity1" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' align="center">

                                                                    <COLGROUP ALIGN="left" >
                                                                        <COL width="10">
                                                                        <COL width="20"> 
                                                                        <COL width="10">
                                                                        <COL width="10">
                                                                        <COL width="20">
                                                                        <COL width="20">
                                                                        <COL width="10">

                                                                </table>
                                                                <br>
                                                                <center><span id="spnFast" class="activeFile"></span></center> 

                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                   
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
                                                            <script type="text/javascript">
		$(window).load(function(){
	getOppurtunityList();

		});
		</script>
    </body>
</html>