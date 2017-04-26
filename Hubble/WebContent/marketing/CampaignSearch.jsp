
<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    : December 19, 2015, 3:25 PM
 *
 * Author  : Miracle
 *
 * File    : CampaignSearch.jsp 
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
        <title>Hubble Organization Portal :: Campaign Search</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>       
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/CampaignScript.js"/>"></script>

        <s:include value="/includes/template/headerScript.html"/> 
    </head>
    <%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getRequirementsList1();"> --%>
  
      <%--  <body class="bodyGeneral" oncontextmenu="return false;" onload="getCampaignSearch();"> --%>
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
                                    <li ><a href="#" class="selected" rel="requirementList"> Campaign List</a></li>
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


                                        <s:form name="campaignSearch"  action="campaignSearchAction.action" theme="simple" >                                            
                                            <table cellpadding="0" cellspacing="0" align="left" width="100%">
 <tr><td colspan="4"><div id="resultMessage"></div></td></tr>  
                                                <tr>
                                                    <td>
                                                        <table border="0" cellpadding="0" cellspacing="2" align="center" width="100%">
                                                            <tr>
                                                                <td class="headerText" colspan="4">
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                            </tr>
                                                            <%--  <tr>
                                                                
                                                                  <td class="fieldLabel">Created By :</td>
                                                                  <td>
                                                                      <s:select headerKey="All" headerValue="All" name="createdBy" id="createdBy" list="createdMemebers" cssClass="inputSelect" theme="simple" />
                                                                  </td>
                                                                  <td class="fieldLabel">Assigned To :</td>
                                                                  <td>
                                                                      <s:select headerKey="All" headerValue="All" list="assignedMembers" name="assignedTo" id="assignedTo" cssClass="inputSelect" theme="simple"/>
                                                                  </td>
                                                              </tr> --%>
                                                            
                                                                <tr>
                                                <td align="left" class="fieldLabel" >Title:</td>
                                                <td ><s:textfield name="campaignTitle" id="campaignTitle" cssClass="inputTextBlue"/>
                                                <td align="left" class="fieldLabel" >Status:</td>
                                                <s:if test="#session.roleId==4">
                                                    <%--    <td class="inputOptionText"><s:select name="campaignStatus" id="campaignStatus" list="#@java.util.LinkedHashMap@{'Active':'Active','Completed':'Completed'}" value="%{campaignStatus}" cssClass="inputSelect" /></td>   --%>
                                                    <td> <s:select list="{'Active','Completed'}"  name="campaignStatus" headerKey="-1" headerValue="All" id="campaignStatus" style="width: 100px;" cssClass="inputSelectInvestement" /></td>
                                                </s:if>
                                                <s:else>
                                                    <td> <s:select list="{'Active','Inactive','Completed'}"  name="campaignStatus" headerKey="-1" headerValue="All" id="campaignStatus" style="width: 100px;" cssClass="inputSelectInvestement" /></td>
                                                    <%--  <td class="inputOptionText"><s:select name="campaignStatus" id="campaignStatus" list="#@java.util.LinkedHashMap@{'Active':'Active','InActive':'InActive','Completed':'Completed'}" value="%{campaignStatus}" cssClass="inputSelect" /></td> --%>
                                                </s:else>
                                            </tr>
                                                          
                                                              <tr>
                                                <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                <td><s:textfield name="campaignStartDate" id="campaignStartDate" cssClass="inputTextBlueSmall" value="%{campaignStartDate}"/><a href="javascript:cal4.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                </td>

                                                <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                <td>  <s:textfield name="campaignEndDate" id="campaignEndDate" cssClass="inputTextBlueSmall" value="%{campaignEndDate}"/><a href="javascript:cal5.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                </td>
                                            </tr>
                                              
                                           <tr>
                                               
                                               <td colspan="3" align="right">
                                                    <s:if test="#session.roleId!=4 && #session.roleId!=12">
                                                        <input type="button" class="buttonBg"  align="right"  value="Add" onclick="campaignAdd();" />   
                                                    </s:if>

                                                <%--    <s:submit cssClass="buttonBg"  align="right"  value="Search" onclick="return compareDates()"/> --%>
                                                 <input type="button" class="buttonBg"  align="right"   value="Search" onclick="getCampaignSearch();"/>
                                                </td>    

                                                <td></td>
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
                                 
                                    <TABLE id="CampaignSearchTbl" align="left"  
                                       cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%'>
                                         <COLGROUP ALIGN="left" >
                                          <COL width="5%">
                                          <COL width="25%"> 
                                           <COL width="20%">
                                           <COL width="15%">
                                           <COL width="10%">
                                             <COL width="15%">
                                          
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


                                    <%--   </sx:div> --%>


                                    <script type="text/javascript">
                                         
                                              
                                                var cal4 = new CalendarTime(document.forms['campaignSearch'].elements['campaignStartDate']);
                                                cal4.year_scroll = true;
                                                cal4.time_comp = false;
                                                var cal5 = new CalendarTime(document.forms['campaignSearch'].elements['campaignEndDate']);
                                                cal5.year_scroll = true;
                                                cal5.time_comp = false;
                                                                                 
                                          
                                    
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

        <!--  End of the main table -->        
<script type="text/javascript">
		$(window).load(function(){
	getCampaignSearch();
		});
		</script>

    </body>
</html>
 
