<%-- 
    Document   : InsideSalesStatus
    Created on : Jan 9, 2017, 3:43:14 PM
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
        <title>Hubble Organization Portal :: Inside Sales Status</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/searchIssue.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/IssueFill.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/InsideSalesStatus.js"/>"></script>

     <%--   <script type="text/javascript" src="<s:url value="/includes/javascripts/GreenSheetUtil.js"/>"></script>
  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script> --%>
      
        <s:include value="/includes/template/headerScript.html"/> 
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
        <script>
            function checkName()

            {

                var accountId = document.getElementById("consultantId").value; 

                if(accountId=="" || accountId==null || accountId==0){

                    //  alert(" Please select customer from suggestion list.");

                    document.getElementById("customerName").value = "";

                }

            }
        </script>
    </head>
    <%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getRequirementsList1();"> --%>

   <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="init1();getInsideSalesStatusAccountDetailsList();"> -->

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


        <!-- Start oif the table -->
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
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="requirementList"> <s:property value="employeeName"/> Account Details</a></li>
                                    <!-- <li ><a href="#" rel="searchDiv">Requirement Search</a></li>-->
                                </ul>
                                <div  style="border:1px solid gray; width:845px;height: 530px; overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:tabbedpanel id="attachmentPannel2" cssStyle="width: 845px; height: 530px;padding:10px 5px 5px 5px" doLayout="true"> --%>

                                    <!--//START TAB : -->
                                    <%--  <sx:div id="List" label="" cssStyle="overflow:auto;"> --%>
                                    <div id="requirementList">

                                        <%
                                            if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                            }
                                        %>


                                        <s:form name="frmInsideSalesAccountSearch" id="frmInsideSalesAccountSearch"  theme="simple" >  
                                            <s:hidden name="bdeLoginId" id="bdeLoginId" value="%{bdeLoginId}"/>
                                            <s:hidden name="employeeName" id="employeeName" value="%{employeeName}"/>
                                            <table cellpadding="0" cellspacing="0" align="left" width="100%">
                                                <tr><td colspan="4"><div id="resultMessage"></div></td></tr>  
                                                <tr>
                                                    <td>
                                                        <table border="0" cellpadding="0" cellspacing="2" align="center" width="100%">
                                                            <tr>
                                                                <td class="headerText" colspan="6">
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                            </tr>


                                                            <tr>
                                                                <td class="fieldLabel">Account Name :</td>
                                                                <td colspan="2">  
                                                                    <s:textfield name="customerName" id="customerName" onchange="checkName();" cssClass="inputTextBlueLarge"  style="width:83%"  value="%{customerName}"  theme="simple" onkeyup="fillCustomerForBde();"/>
                                                                    <div id="validationMessage"></div>
                                                                    <s:hidden name="consultantId" id="consultantId" value="%{accountId}" /> 

                                                                </td>


                                                               
                                                            </tr>
                                                             <tr>
                                                                 <td class="fieldLabel">Touched :</td>
                                                                  <td>  
                                                                   <s:select name="touched" id="touched" list="{'Yes','No'}" cssClass="inputSelect" headerKey="All" headerValue="All"/></td>
                                                                  
                                                                 <td class="fieldLabel">Opportunities :</td>
                                                                  <td>  
                                                                   <s:select name="opportunity" id="opportunity" list="{'Yes','No'}" cssClass="inputSelect" headerKey="All" headerValue="All"/></td>
                                                                 
                                                              
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Last Activity From:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td><s:textfield name="lastActivityFrom" id="lastActivityFrom" cssClass="inputTextBlue" onchange="checkDates(this);"/>                                                        
                                                                    <a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                             width="20" height="20" border="0"></a>
                                                                </td>
                                                                <td class="fieldLabel">To:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                <td>
                                                                    <s:textfield name="lastActivityTo"  id="lastActivityTo" cssClass="inputTextBlue" onchange="checkDates(this);"/>
                                                                    <a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                             width="20" height="20" border="0"></a>
                                                                </td>                    

                                                                <td >
                                                                    <input type="button" class="buttonBg"  align="right"   value="Search" onclick="getInsideSalesStatusAccountDetailsList();"/>
                                                                </td>
                                                            </tr>
                                                           

                                                        </table>  
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>

                                                        <table cellpadding="0" cellspacing="0" width="100%">
                                                            <tr>
                                                                <td>
                                                                    <table width="100%" cellpadding="1" cellspacing="1" border="0">
                                                                        <tr>
                                                                            <td>
                                                                                <div id="loadingMessage" style="color:red; display: none;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>

                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <%-- <DIV id="loadingMessage"> </DIV> --%>

                                                                                <TABLE id="tblInsideSalesAccounts" align="left"  
                                                                                       cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%'>
                                                                                    <COLGROUP ALIGN="left" >
                                                                                        <COL width="5%"> 
                                                                                        <COL width="25%"> 
                                                                                        <COL width="20%">
                                                                                        <COL width="15%">
                                                                                        <COL width="20%">
                                                                                </TABLE>

                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>

                                                        </table>


                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </s:form>
                                    <script type="text/JavaScript">
                                        var cal1 = new CalendarTime(document.forms['frmInsideSalesAccountSearch'].elements['lastActivityFrom']);
                                        cal1.year_scroll = true;
                                        cal1.time_comp = false;
                                                   
                                        var cal2 = new CalendarTime(document.forms['frmInsideSalesAccountSearch'].elements['lastActivityTo']);
                                        cal2.year_scroll = true;
                                        cal2.time_comp = false;     

                                    </script>

                                    <%--   </sx:div> --%>


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
            <tr>
                <td>

                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>

                </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->

        </table>
        <!--//END MAIN TABLE : Table for template Structure-->

        <!--  End of the main table -->        

<script type="text/javascript">
		$(window).load(function(){
		init1();
		getInsideSalesStatusAccountDetailsList();
		});
		</script>
    </body>
</html>

