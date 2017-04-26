<%-- 
    Document   : ClientEngagementRequestSearch
    Created on : May 24, 2016, 4:57:52 PM
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
        <title>Hubble Organization Portal :: Client Engagement Request</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/includes/javascripts/crm/ClientEngagementRequest.js?version=1.0"/>"></script>   

        <s:include value="/includes/template/headerScript.html"/> 
        <style>
            
.gridRowEvenPSCER
{
	
	height: 20px;
        width: 100px;
}
        </style>
    </head>
 <!--   <body class="bodyGeneral"   oncontextmenu="return false;" onload="getClientrequestDetailsForEmployee('1','onload');"> -->
    <body class="bodyGeneral"   oncontextmenu="return false;" >


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
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <%
                                    //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                    /// session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
                                %>

                                <ul id="accountTabs" class="shadetabs" >

                                    <%--    <% if(request.getParameter("issueList")==null)
                                           {%> --%>

                                    <li ><a href="#" class="selected" rel="issuesTab"  > PSCER </a></li>
                                    <%-- <li ><a href="#"  rel="IssuesSearchTab"  > Project Search</a></li> --%>


                                    <%--   <%}else{%>
                                       <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                        <% }%> --%>

                                </ul>

                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:850px;height: 675px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->

                                    <%--  <% if(request.getParameter("issueList")==null)
                                             {
                                                 System.out.println("list");
                                             %> --%>
                                    <div id="issuesTab" class="tabcontent"  >






                                        <s:form action="" theme="simple" name="frmClientEngagementRequestSearch">


                                            <div style="width:840px;" > 

                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">

                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                                                <tr>
                                                                    <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                    <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlueSmall" value="%{startDate}"/><a href="javascript:cal2.popup();" style="text-decoration: none;">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </td>

                                                                    <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                    <td>  <s:textfield name="endDate" id="endDate" cssClass="inputTextBlueSmall" value="%{endDate}"/><a href="javascript:cal3.popup();" style="text-decoration: none;">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <td class="fieldLabel">State :</td>
                                                                    <td>
                                                                        <s:select id="state" name="state" value="%{state}" list="{'Creation','Submitted','Reviewed','Approved','Rejected'}" cssClass="inputSelect" headerKey="" headerValue="All" />
                                                                    </td>  
                                                                    <td class="fieldLabel">Request&nbsp;type:</td>
                                                                    <td>
                                                                        <s:select id="requestType" name="requestType" value="%{requestType}" list="{'PSCER','RFP'}" cssClass="inputSelect" headerKey="" headerValue="All" />
                                                                    </td>  
                                                                   
                                                                </tr>
                                                                <tr>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                        
                                                                     <td>
                                                                        <!--  <input type="button" class="buttonBg"  align="right"  value="Add" onclick="investmentToggleOverlay();" />                      -->
                                                                        <input type="button" value="Search" class="buttonBg" onclick="getClientrequestDetailsForEmployee('1','search');"/>

                                                                    </td>
                                                                </tr>
                                                                



                                                            </table>  
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td>
                                                            <table width="100%" cellpadding="1" cellspacing="1" border="0">
                                                                <tr>
                                                                    <td>
                                                                        <div id="loadingMessage" style="color:red; display: none;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>

                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td height="20px" >
                                                                        <s:hidden name="pgNo" id="pgNo" cssClass="inputTextBlue" value="%{pgNo}" theme="simple"/>
                                                                        
                                                                        <s:hidden name="totalRecords" id="totalRecords" cssClass="inputTextBlue" theme="simple"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <%-- <DIV id="loadingMessage"> </DIV> --%>

                                                                        <TABLE id="tblPSCERDetailsEmployee" align="left"  
                                                                               cellpadding='1' cellspacing='1' border='0' class="gridTable" width='100%'>
                                                                            <COLGROUP ALIGN="left" >
                                                                                 <COL width="7%">
                                                                                <COL width="7%">
                                                                                <COL width="20%"> 
                                                                                <COL width="20%">
                                                                                <COL width="20%">
                                                                                <COL width="7%">
                                                                                <COL width="7%">
                                                                                <COL width="7%">
                                                                                  <COL width="15%">
                                                                                <COL width="15%">
                                                                                  <COL width="15%">

                                                                        </TABLE>
                                                                        <div id="button_pageNation"></div>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>


                                                </table>   
                                                <script type="text/javascript">
                                               
                                                    var cal2 = new CalendarTime(document.forms['frmClientEngagementRequestSearch'].elements['startDate']);
                                                    cal2.year_scroll = true;
                                                    cal2.time_comp = false;
                                                    var cal3 = new CalendarTime(document.forms['frmClientEngagementRequestSearch'].elements['endDate']);
                                                    cal3.year_scroll = true;
                                                    cal3.time_comp = false;
                                               
                                                                                 
                                            
                                    
                                                    var countries=new ddtabcontent("accountTabs")
                                                    countries.setpersist(false)
                                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                    countries.init()

                                                </script>
                                            </div>
                                        </s:form>  



                                        <%--  </sx:div > --%>
                                    </div>


                                    <!--//END TABBED PANNEL : -->
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
<script type="text/javascript">
		$(window).load(function(){
		getClientrequestDetailsForEmployee('1','onload');
		});
		</script>

        <script type="text/javascript">
                                                                                                                                                                                                       
                                                                                                                                                                                                          
                                                                                                                                                                                                        
            function pagerOption(){

                                                             
                var  recordPage=10;
                                                                                                                                                                                                  
                $('#tblPSCERDetailsEmployee').tablePaginate({navigateType:'navigator'},recordPage,tblPSCERDetailsEmployee);

            }
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DynamicPagination.js?version=1.0"/>"></script>


    </body>
</html>
