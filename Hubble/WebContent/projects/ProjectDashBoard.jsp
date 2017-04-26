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
        <title>Hubble Organization Portal :: Project DashBoard</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/animatedcollapse.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-1.2.2.pack.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountDetailsClientValidation.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/Activity.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/oppdashboardAjaxUtil.js?version=2.0"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GreensheetListSearch.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GreensheetListResultSearch.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ReusableContainer.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <!-- new js -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectsValidations/ProjectTeamDashBoard.js?version=2.0"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
                
            animatedcollapse.addDiv('accountsummrepDiv', 'fade=1;speed=400;persist=1;group=app');
            animatedcollapse.addDiv('activitiesDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('accountDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('opportDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('greenSheetDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('accountListByPriorityDiv', 'fade=1;persist=1;group=app');

            //animatedcollapse.addDiv('mirageDiv', 'fade=1;speed=400;persist=1;group=app');
            //animatedcollapse.addDiv('hubbleDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.init();
            function hideSelect(){
                //document.getElementById("priorityId").style.display = 'none';
                
            }
        </script>
    </head>
   <!-- <body  class="bodyGeneral" onload="hideSelect();javascript:animatedcollapse.show('accountListByPriorityDiv')" oncontextmenu="return false;"> -->
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
                                    <li><a href="#" rel="accountsListTab" class="selected"> Projects DashBoard</a></li>

                                </ul>
                                <div  style="border:1px solid gray; width:840px;height: 550px;overflow:auto; margin-bottom: 1em;">    
                                    <div id="accountsListTab" class="tabcontent" >  
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">


                                            <!-- new div for account list by priority start -->
                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Project Team</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('accountListByPriorityDiv')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('accountListByPriorityDiv')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="accountListByPriorityDiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="" theme="simple" name="loadAccountListByPriority">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="1" cellspacing="1">
                                                                                        <tr>
                                                                                            <td class="fieldLabel">Customers: </td>

                                                                                            <td width="25%">
                                                                                                <%--   <s:select list="myAccounts" name="accountId" id="customerId" onchange="getProjectsForAccountId();hidePriority();" headerKey="-1" headerValue="--Select Project--" cssClass="inputLargeSelect" value="" disabled="false"/>  --%>
                                                                                                   <s:select list="myAccounts" name="accountId" id="customerId" onchange="getMyProjectsByAccountId();hidePriority();" headerKey="-1" headerValue="--Select Customer--" cssClass="inputSelect1" value="" disabled="false"/> 
                                                                                            </td>
                                                                                            
                                                                                            <td class="fieldLabel">Projects: </td>

                                                                                            <td width="25%">
                                                                                                   <s:select list="myProjects" name="projectId" id="projectId" onchange="hidePriority();" headerKey="-1" headerValue="--Select Project--" cssClass="inputSelect1" value="" disabled="false"/> 
                                                                                            </td>
                                                                                           
                                                                                           
                                                                                            <%-- <td style="padding-left:0px;"><input type="button" value="Search"  class="buttonBg"  onclick="getAccountsByPriority();"/></td> --%>
                                                                                             <td style="padding-left:25px;"><input id="normalUserButton" type="button" value="Search"  class="buttonBg"  onclick="getProjectTeam();"/></td>

                                                                                        </tr>

                            

                                                                                    </table>
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td height="20px" align="center" colspan="9">
                                                                                    <div id="loadActMessagePriority" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                </td>
                                                                            </tr>



                                                                            <!-- end of message row -->

                                                                            <tr>
                                                                                <td>
                                                                                    <br>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                        <tr>
                                                                                            <td >
                                                                                                <br>
                                                                                                <div id="AccountSummaryByPriorityList" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblUpdateForAccountsListByPriority" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="5%">
                                                                                                            <COL width="25%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="13%">
                                                                                                            <COL width="2%">
                                                                                                            <COL width="2%">
                                                                                                            
       

                                                                                                    </table>
                                                                                                    <br>
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>

                                                                        </table>
                                                                    </s:form>
                                                                </td>
                                                            </tr>
                                                        </table>

                                                    </div>
                                                </td>
                                            </tr>

                                            <!-- new div for account list by priority end -->

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
	hideSelect();
	javascript:animatedcollapse.show('accountListByPriorityDiv');
		});
		</script>
    </body>

</html>
