<%-- 
    Document   : ManagerDashboard
    Created on : Jan 21, 2016, 4:36:51 PM
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeProject.js"/>"></script>


        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
             
            
            animatedcollapse.addDiv('dmProjectsDashboardDiv', 'fade=1;persist=1;group=app');
           
            animatedcollapse.init();
           
        </script>
           <style>

            div#overlayProjects {
                display: none;
                z-index: 2;
                background: #000;
                position: fixed;
                width: 100%;
                height: 100%;
                top: 0px;
                left: 0px;
                overflow: auto;
                text-align: center;
            }
            div#specialBoxProject {
                display: none;

                position: absolute;
                z-index: 3;
                margin: 100px auto 0px auto;
                width: 800px; 
                height: auto;
                background: #FFF;
                overflow: auto;
                overflow-y: auto;
                color: #000;
            }
        </style>
    </head>
   <%-- <body  class="bodyGeneral"  oncontextmenu="return false;" onload="defaultDates();"> --%>
    <body  class="bodyGeneral"  oncontextmenu="return false;">

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
                                    <li><a href="#" rel="accountsListTab" class="selected">&nbsp;DashBoards</a></li>

                                </ul>
                                <div  style="border:1px solid gray; width:840px;height: 550px;overflow:auto; margin-bottom: 1em;">    
                                    <div id="accountsListTab" class="tabcontent" >  
                                      
                                           <!-- Over lay start -->

                                        <div id="overlayProjects"></div>              <!-- Start Overlay -->

                                        <div id="specialBoxProject" style="align: center" >

                                            <div id="addedProjects" style="display: none;align: center">
                                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                    <tr>                               
                                                        <td colspan="1" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="closeProjectMembers()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessage"></div>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td>
                                                            <table id="tblProjectMembers" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script> --%>
                                                                <COLGROUP ALIGN="left" >
                                                                    <COL width="20%">
                                                                    <COL width="20%">
                                                                    <COL width="20%">
                                                                    <COL width="20%">
                                                                    <COL width="20%">
                                                            </table> 
                                                        </td>
                                                    </tr>

                                                </table>    

                                            </div>
                                        </div>

                                        <!-- Overlay end -->
                                        
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                            <tr>
                                                <td height="20px" >

                                                </td>
                                            </tr>


                                          

                                            <!-- PreSAles Projects start start -->

                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Projects</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('dmProjectsDashboardDiv')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('dmProjectsDashboardDiv')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="dmProjectsDashboardDiv" style="background-color:#ffffff">


                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="80%" valign="top" align="center">
                                                                    <s:form action="" theme="simple" id="projectDashboard" name="projectDashboard" onsubmit="return compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value);">   

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0" >
                                                                                        <tr><td colspan="4"><br></td></tr>

                                                                                        <tr>   

                                                                                            <td class="fieldLabel">Start Date:</td> <!--value="%{dateWithOutTime}" -->
                                                                                            <td><s:textfield id="startDate" name="startDate" cssClass="inputTextBlue" value="" onchange="checkDates(this);"/> <a href="javascript:cal3.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                         width="20" height="20" border="0"></td>    
                                                                                                    <td class="fieldLabel">End Date</td>
                                                                                                    <td><s:textfield name="endDate" id="endDate" cssClass="inputTextBlue" value="%{dateWithOutTime}" onchange="checkDates(this);"/><a href="javascript:cal4.popup();">
                                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                 width="20" height="20" border="0">  </td>
                                                                                                            </tr>
                                                                                                          <tr>
                                                                                                                <td class="fieldLabel" width="200px" align="right">Team&nbsp;Members: </td>                           
                                                                                                                <td>

                                                                                                                    <s:select name="managerTeamMember" id="managerTeamMember" headerKey="-1" headerValue="--please select--" list="managerTeamMembersList" cssClass="inputSelect" value="%{managerTeamMember}"  />    

                                                                                                                </td>
                                                                                                                <td class="fieldLabel" width="200px" align="right">State: </td>     
                                                                                                                <td>
                                                                                                                    <s:select  list="{'Active','Completed','Terminated','Initiated'}" name="status" id="status" cssClass="inputSelect"  value="%{state}"/>
                                                                                                                </td>
                                                                                                            </tr> 
                                                                                                            <tr>
                                                                                                                <td colspan="3"></td><td>
                                                                                                                    <input type="button" id="ProjectSearchBUtton" class="buttonBg" onclick="getDeliverManagerProjects()" value="Search"/>

                                                                                                                </td>
                                                                                                            </tr>

                                                                                                            <tr>
                                                                                                                <td height="20px" align="center" colspan="4" >
                                                                                                                    <div id="loadProjectMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            <tr>
                                                                                                                <td height="20px" >

                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            <tr>

                                                                                                                <td colspan="4" >
                                                                                                            <lable id="noteLableForProject" style="display:none;color:red;font-size: 80%; font-style: italic;">Note :The details are order by  Due date ascending.</lable>
                                                                                                            <table id="tblDMProjects" align="center"  
                                                                                                                   cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                <COLGROUP ALIGN="left" >
                                                                                                                    <COL width="5%">
                                                                                                                    <COL width="15%">
                                                                                                                    <COL width="5%">
                                                                                                                    <COL width="10%">
                                                                                                                    
                                                                                                                <COL width="15%">                                                                                                            </table>
                                                                                                    </td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>


                                                                        </table>
                                                                    </s:form>
                                                                    <script type="text/JavaScript">
                                                                        var cal3 = new CalendarTime(document.forms['projectDashboard'].elements['startDate']);
                                                                        cal3.year_scroll = true;
                                                                        cal3.time_comp = false;
                                                                        cal4 = new CalendarTime(document.forms['projectDashboard'].elements['endDate']);
                                                                        cal4.year_scroll = true;
                                                                        cal4.time_comp = false;
                                                                    </script>
                                                                </td>
                                                            </tr>
                                                            <!--2nd td in First table -->
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>

                                            <!-- PreSAles Projects start end -->
                                            
                                           
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
        
		<script type="text/javascript">
		$(window).load(function(){
		defaultDates();
		});
		</script>
    </body>

</html>

