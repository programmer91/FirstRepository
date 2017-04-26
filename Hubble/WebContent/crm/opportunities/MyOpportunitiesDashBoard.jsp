<%-- 
    Document   : MyOpportunitiesDashBoard
    Created on : Dec 3, 2015, 10:33:55 PM
    Author     : miracle
--%>

<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<html>
    <head>
        <title>Hubble Organization Portal :: DashBoard</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/animatedcollapse.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-1.2.2.pack.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/crm/MyOpportunityDashScript.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
            animatedcollapse.addDiv('opportDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.init();
        </script>
    </head>
  <!--  <body  class="bodyGeneral" onload="setDefaultMyOppDates();" oncontextmenu="return false;"> -->
    <body  class="bodyGeneral" oncontextmenu="return false;">

        <%!
            /* Declarations */
            String queryString = null;
            Connection connection;
            Statement stmt;
            ResultSet rs;
            int userCounter = 0;
            int activityCounter = 0;
            int accountCounter = 0;
        %>


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
                            <td width="850px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/>
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->

                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <!--//START TABBED PANNEL : -->
                                <%--      <sx:tabbedpanel id="resetPasswordPannel" cssStyle="width: 845px; height: 550px;padding-left:10px;padding-top:5px;" doLayout="true" useSelectedTabCookie="true" > 
                                    
                                    <!--//START TAB : -->
                                    <sx:div id="dashBoardTab" label="DashBoard Details" cssStyle="overflow:auto;"> --%>
                                <ul id="accountTabs" class="shadetabs" >
                                    <li><a href="#" rel="accountsListTab" class="selected">&nbsp;My&nbsp;Opportunities</a></li>

                                </ul>
                                <div  style="border:1px solid gray; width:840px;height: 550px;overflow:auto; margin-bottom: 1em;">    
                                    <div id="accountsListTab" class="tabcontent" >  
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                            <tr>
                                                <td height="20px" >

                                                </td>
                                            </tr>



                                                            <tr>
                                                                <td class="homePortlet" valign="top">
                                                                    <div class="portletTitleBar">
                                                                        <div class="portletTitleLeft">My Opportunities Summary</div>
                                                                        <div class="portletIcons">
                                                                            <a href="javascript:animatedcollapse.hide('opportDiv')" title="Minimize">
                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                            <a href="javascript:animatedcollapse.show('opportDiv')" title="Maximize">
                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                            </a>
                                                                        </div>
                                                                        <div class="clear"></div>
                                                                    </div>
                                                                    <div id="opportDiv" style="background-color:#ffffff">


                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                            <tr>
                                                                                <td width="80%" valign="top" align="center">
                                                                                    <s:form action="" theme="simple" id="oppDashboard" name="oppDashboard" onsubmit="return compareDates(document.getElementById('dueStartDate').value,document.getElementById('dueEndDate').value);">   

                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                           
                                                                                            <tr>
                                                                                                <td>
                                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0" >
                                                                                                        <tr><td colspan="4"><br></td></tr>
                                                                                                        <tr>
                                                                                                            <td class="fieldLabel">Opportunity Type:</td>
                                                                                                            <td>
                                                                                                                <s:select name="type" id="type" cssClass="inputSelect" list="typeList" headerKey="All" headerValue="All"/>
                                                                                                            </td>
                                                                                                              <td  class="fieldLabel">State:&nbsp;</td>
                                                                                                                <td> 
                                                                                                                    <s:select name="state" id="state" cssClass="inputSelect" list="opportunityStateList" headerKey="" headerValue="All"/>
                                                                                                                </td>

                                                                                                        </tr>
                                                                                                       
                                                                                                    <%--    <tr>
                                                                                                            <td class="fieldLabel">
                                                                                                                Specify Created Date Range:
                                                                                                            </td>
                                                                                                            <td colspan="3"  >
                                                                                                                <input id="dateClearbtn" type="button" class="buttonBg" value="clear Dates" name="dateClearbtn" onclick="clearDatesOppDash(this);" >
                                                                                                            </td>
                                                                                                        </tr>--%>
                                                                                                        <tr>          
                                                                                                            <td class="fieldLabel">Start Date:</td> <!--value="%{dateWithOutTime}" -->
                                                                                                            <td><s:textfield id="dueStartDate" name="dueStartDate" cssClass="inputTextBlue" value="" onchange="checkDates(this);"/> <a href="javascript:cal1.popup();">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                         width="20" height="20" border="0"></td>    
                                                                                                                    <td class="fieldLabel">End Date</td>
                                                                                                                    <td><s:textfield name="dueEndDate" id="dueEndDate" cssClass="inputTextBlue" value="%{dateWithOutTime}" onchange="checkDates(this);"/><a href="javascript:cal2.popup();">
                                                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                 width="20" height="20" border="0">  </td>
                                                                                                                            </tr>
                                                                                                                             <tr>
                                                                                                                              <td class="fieldLabel" style="width: 10px;">Practice :</td>
                                                                                                                                     <td style="width: 156px;">
                                                                                                                              <s:select list="practiceIdList" name="practice" id="practice" cssClass="inputSelect" value="All" headerKey="All" headerValue="All" />
                                                                                                                                </td>
                                                                                                                                 <td > 
                                                                                                                               <input type="button" id="opportunitySearchBUtton" class="buttonBg" onclick="getMyOpportunities()" value="Search"/>
                                                                                                                                 </td>
                                                                                                                             </tr>
                                                                                                                         

                                                                                                                            <tr>
                                                                                                                                <td height="20px" align="center" colspan="4" >
                                                                                                                                    <div id="loadOppMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                </td>
                                                                                                                            </tr>

                                                                                                                            <tr>
                                                                                                                               <td class="fieldLabel" >
                                                                                                                                   Total&nbsp;Records&nbsp;found&nbsp:
                                                                                                                                 </td>
                                                                                                                                <td class="fieldLabelLeft" id="totalOppRec" >

                                                                                                                                </td>
                                                                                                                                <td class="fieldLabel"  >
                                                                                                                                    Total Value($): 
                                                                                                                                </td>
                                                                                                                                <td class="fieldLabelLeft" id="totalOppSum" >

                                                                                                                                </td>
                                                                                                                            </tr>
                                                                                                                            <tr>
                                                                                                                                <td height="20px" >

                                                                                                                                </td>
                                                                                                                            </tr>
                                                                                                                            <tr>

                                                                                                                                <td colspan="4" >
                                                                                                                            <lable id="noteLableForOpportunity" style="display:none;color:red;font-size: 80%; font-style: italic;">Note :The details are order by  Due date ascending.</lable>
                                                                                                                            <table id="tblOppUpdate" align="center"  
                                                                                                                                   cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                <COLGROUP ALIGN="left" >
                                                                                                                                    <COL width="5%">
                                                                                                                                    <COL width="20%">
                                                                                                                                    <COL width="25%">
                                                                                                                                    <COL width="7%">
                                                                                                                                    <COL width="7%">
                                                                                                                                    <COL width="7%">
                                                                                                                                    <COL width="7%">
                                                                                                                                    <COL width="7%">
                                                                                                                            </table>
                                                                                                                    </td>
                                                                                                        </tr>
                                                                                                    </table>
                                                                                                </td>
                                                                                            </tr>


                                                                                        </table>
                                                                                    </s:form>
                                                                                    <script type="text/JavaScript">
                                                                                        var cal1 = new CalendarTime(document.forms['oppDashboard'].elements['dueStartDate']);
                                                                                        cal1.year_scroll = true;
                                                                                        cal1.time_comp = false;
                                                                                        cal2 = new CalendarTime(document.forms['oppDashboard'].elements['dueEndDate']);
                                                                                        cal2.year_scroll = true;
                                                                                        cal2.time_comp = false;
                                                                                    </script>
                                                                                </td>
                                                                            </tr>
                                                                            <!--2nd td in First table -->
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr>

                                                           
                                                            <!-- Account renewal dashbord changes end -->

                                                        </table>

                                                        <%--     </sx:div>
                                                </sx:tabbedpanel> --%>
                                                        <!--//END TABBED PANNEL : --> 
                                                    </div>
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
                                            <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
                                        </tr>
                                        <!--//END FOOTER : Record for Footer Background and Content-->
                                        </table>
                                        <!--//END MAIN TABLE : Table for template Structure-->
                                        </body>
<script type="text/javascript">
		$(window).load(function(){
		
		setDefaultMyOppDates();
                
		});
		</script>
                                        </html>

