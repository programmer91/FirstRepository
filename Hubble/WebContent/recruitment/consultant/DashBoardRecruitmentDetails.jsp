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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountDetailsClientValidation.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/Activity.js"/>"></script>
        <%--  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/oppdashboardAjaxUtil.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GreensheetListSearch.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GreensheetListResultSearch.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ReusableContainer.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/RecruitmentDashBoard.js"/>"></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script> 
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
            google.load("visualization", "1", {packages:["corechart"]});
            animatedcollapse.addDiv('recactivitysummrepDiv', 'fade=1;speed=400;persist=1;group=app');
            animatedcollapse.addDiv('totalProfiles', 'fade=1;speed=400;persist=1;group=app');
            animatedcollapse.addDiv('profilesSubmitted', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('requirementsClosed', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('requirementsStatuswindow', 'fade=1;persist=1;group=app');
            // animatedcollapse.addDiv('inActiveProfiles', 'fade=1;persist=1;group=app');
            // animatedcollapse.addDiv('ActiveProfiles', 'fade=1;persist=1;group=app');
            // animatedcollapse.addDiv('requirementsInfo', 'fade=1;persist=1;group=app');
            // animatedcollapse.addDiv('mirageDiv', 'fade=1;speed=400;persist=1;group=app');
            //animatedcollapse.addDiv('hubbleDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('recactivitysummrepDivGraph', 'fade=1;speed=400;persist=1;group=app');
            animatedcollapse.addDiv('recReports', 'fade=1;speed=400;persist=1;group=app');
            animatedcollapse.init();
      
        </script>
    </head>
    <body  class="bodyGeneral" onload="" oncontextmenu="return false;">
        <%!
            /*
             * Declarations
             */
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
                                    <li><a href="#" rel="accountsListTab" class="selected"> DashBoard Details </a></li>
                                </ul>

                                <div  style="border:1px solid gray; width:840px;height: 550px;overflow:auto; margin-bottom: 1em;">    
                                    <div id="accountsListTab" class="tabcontent" >  

                                        <!-- Over lay start -->

                                        <div id="overlayRecruitment"></div>              <!-- Start Overlay -->

                                        <div id="specialBoxRecruitment">

                                            <div id="addedConultantDiv" style="display: none;" >
                                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="closeConsultantList()" >
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
                                                            <table id="tblAddedConsultant" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script> --%>
                                                                <COLGROUP ALIGN="left" >
                                                                    <COL width="15%">
                                                                    <COL width="15%">
                                                                    <COL width="10%">
                                                                    <COL width="5%">
                                                                    <COL width="15%">
                                                                    <COL width="15%">
                                                                    <COL width="10%">
                                                                    <COL width="5%">
                                                                    <COL width="15%">
                                                                    <COL width="15%">
                                                                    <COL width="10%">
                                                                    <COL width="5%">
                                                                    <COL width="5%">

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

                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Activities Summary</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('recactivitysummrepDiv')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('recactivitysummrepDiv')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="recactivitysummrepDiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="recruitmentDashboard" theme="simple" name="dashboardSummaryRep" >   

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                        <tr>                                                    
                                                                                            <td class="fieldLabel">StartDate:<FONT color="red"  ><em>*</em></FONT></td> <!--value="%{dateWithOutTime}" -->

                                                                                            <td><s:textfield name="startdateSummary" id="startDateSummary" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calRep01.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                         width="20" height="20" border="0">
                                                                                                    </td>
                                                                                                    <td class="fieldLabel">EndDate:<FONT color="red"  ><em>*</em></FONT></td>

                                                                                                    <td><s:textfield name="enddateSummary" id="endDateSummary" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calRep02.popup();">
                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                  width="20" height="20" border="0">  </td>
                                                                                                            </tr>                                                                                                          

                                                                                                            <tr>  
                                                                                                                <td class="fieldLabel">Activity Type : </td>
                                                                                                                <td><s:select name="activityType" id="activityType" value="%{currentConsultant.activityType}" headerKey="-1" headerValue="--Please Select--" list="{'Email','Call-Inbound','Call-Outbound'}" cssClass="inputSelect"/></td>
                                                                                                                <td class="fieldLabel">Recruiter Name :</td>
                                                                                                                <td>
                                                                                                                    <s:select headerKey="-1" headerValue="--Please Select--" name="createdBy" id="createdBy" list="assignedMembers" cssClass="inputSelect" theme="simple" />
                                                                                                                </td>
                                                                                                                <td colspan="4" align="center">
                                                                                                                    <input type="button" value="Search" class="buttonBg" onclick="getRecActivitiesByRep();"/>
                                                                                                                </td> 
                                                                                                            </tr>

                                                                                                            </table>
                                                                                                    </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="loadActMessageAS" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td>
                                                                                                <br>
                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                    <tr>
                                                                                                        <td >
                                                                                                            <br>
                                                                                                            <div id="recDashBoardActivity" style="display: block">
                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                <table id="tblRecDashBoardSummRep" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='550'>
                                                                                                                    <COLGROUP ALIGN="center" >
                                                                                                                        <COL width="3%">
                                                                                                                        <COL width="8%">
                                                                                                                        <COL width="7%">
                                                                                                                        <COL width="5%">
                                                                                                                        <COL width="13%">
                                                                                                                        <COL width="4%">
                                                                                                                    </colgroup>

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
                                                                    <script type="text/JavaScript">
                                                                        var calRep01 = new CalendarTime(document.forms['dashboardSummaryRep'].elements['startdateSummary']);
                                                                        calRep01.year_scroll = true;
                                                                        calRep01.time_comp = true;
                                                                        var calRep02 = new CalendarTime(document.forms['dashboardSummaryRep'].elements['enddateSummary']);
                                                                        calRep02.year_scroll = true;
                                                                        calRep02.time_comp = true;
                                                                    </script>
                                                                    </div>
                                                                </td>
                                                            </tr>


                                                            <tr>
                                                                <td class="homePortlet" valign="top">
                                                                    <div class="portletTitleBar">
                                                                        <div class="portletTitleLeft">Sourcing Agent Profiles count by Practice</div>                                                       
                                                                        <div class="portletIcons">
                                                                            <a href="javascript:animatedcollapse.hide('totalProfiles')" title="Minimize">
                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                            <a href="javascript:animatedcollapse.show('totalProfiles')" title="Maximize">
                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                            </a>                                                           
                                                                        </div>
                                                                        <div class="clear"></div>
                                                                    </div>
                                                                    <div id="totalProfiles" style="background-color:#ffffff">
                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                            <tr>
                                                                                <td width="40%" valign="top" align="center">
                                                                                    <s:form action="recruitmentDashboard" theme="simple" name="dashboardRep">   

                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                            <tr align="right">
                                                                                                <td class="headerText" colspan="9">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td>
                                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                                        <tr>                                                    
                                                                                                            <td class="fieldLabel">StartDate:</td> <!--value="%{dateWithOutTime}" -->

                                                                                                            <td><s:textfield name="dashBoardStartDate" id="dashBoardStartDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calRep03.popup();">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                         width="20" height="20" border="0"></td>    

                                                                                                                    <td class="fieldLabel">EndDate</td>
                                                                                                                    <td><s:textfield name="dashBoardEndDate" id="dashBoardEndDate" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calRep04.popup();">
                                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                  width="20" height="20" border="0">  </td>

                                                                                                                            <td colspan="4" align="center">


                                                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getTotalProfiles();"/>
                                                                                                                            </td> 
                                                                                                                            </tr>

                                                                                                                            </table>
                                                                                                                    </td>
                                                                                                        </tr>

                                                                                                        <tr>
                                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                                <div id="loadActMessageSAPC" style='display:none;' class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                            </td>
                                                                                                        </tr>

                                                                                                        <tr>
                                                                                                            <td>
                                                                                                                <br>
                                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                                    <tr>
                                                                                                                        <td >
                                                                                                                            <br>
                                                                                                                            <div id="toatlProfilesList" style="display: block">
                                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                <table id="tblToatlProfiles" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                                        <COL width="5%">
                                                                                                                                        <COL width="5%">
                                                                                                                                        <COL width="5%">
                                                                                                                                        <COL width="5%">
                                                                                                                                        <COL width="5%">
                                                                                                                                        <COL width="5%">

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
                                                                                    <script type="text/JavaScript">
                                                                                        var calRep03 = new CalendarTime(document.forms['dashboardRep'].elements['dashBoardStartDate']);
                                                                                        calRep03.year_scroll = true;
                                                                                        calRep03.time_comp = true;
                                                                                        var calRep04 = new CalendarTime(document.forms['dashboardRep'].elements['dashBoardEndDate']);
                                                                                        calRep04.year_scroll = true;
                                                                                        calRep04.time_comp = true;
                                                                                    </script>
                                                                                    </div>
                                                                                </td>
                                                                            </tr>



                                                                            <%--                            
                                                                                                        
                                                                                        <tr>
                                                                                            <td class="homePortlet" valign="top">
                                                                                                <div class="portletTitleBar">
                                                                                                    <div class="portletTitleLeft">Inactive Profiles by Practice</div>
                                                                                                    <div class="portletIcons">
                                                                                                        <a href="javascript:animatedcollapse.hide('inActiveProfiles')" title="Minimize">
                                                                                                            <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                        <a href="javascript:animatedcollapse.show('inActiveProfiles')" title="Maximize">
                                                                                                            <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                        </a>                                                           
                                                                                                    </div>
                                                                                                    <div class="clear"></div>
                                                                                                </div>
                                                                                                <div id="inActiveProfiles" style="background-color:#ffffff">
                                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                        <tr>
                                                                                                            <td width="40%" valign="top" align="center">
                                                                                                                <s:form action="dashBoard" theme="simple" name="dashboard">   

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>
                                                                            --%>
                                                                            <%--  <tr>
                                                                                  <td>
                                                                                      <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                          <tr>                                                    
                                                                                              <td class="fieldLabel">StartDate:</td> <!--value="%{dateWithOutTime}" -->

                                                                                            <td><s:textfield name="dashBoardStartDate" id="dashBoardStartDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calRep.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                         width="20" height="20" border="0"></td>    

                                                                                                    <td class="fieldLabel">EndDate</td>
                                                                                                    <td><s:textfield name="dashBoardEndDate" id="dashBoardEndDate" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calRep1.popup();">
                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                  width="20" height="20" border="0">  </td>

                                                                                                            <td colspan="4" align="center">
                                                                                                             

                                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getInactiveProfileByPractice();"/>
                                                                                                            </td> 
                                                                                                            </tr>

                                                                                                            </table>
                                                                                                    </td>
                                                                                        </tr>  --%>
                                                                            <%--
                                                                                                                                       <tr>
                                                                                                                                           <td height="20px" align="center" colspan="9">
                                                                                                                                               <div id="loadActMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                           </td>
                                                                                                                                       </tr>

                                                                                        <tr>
                                                                                            <td>
                                                                                                <br>
                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
<tr>
                                                                                                                        <td class="fieldLabelLeft" >
                                                                                                                            No of Inactive Profiles(Not Touched for last 30 Days) by Practice
                                                                                                                        </td>
                                                                                                                        <td class="fieldLabelLeft" id="totalProfilesFound">

                                                                                                                        </td>
                                                                                                                    </tr>

                                                                                                    <tr>
                                                                                                        <td >
                                                                                                            <br>
                                                                                                            <div id="accountsByRepList" style="display: block">
                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                <table id="tblInactiveProfiles" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                        <COL width="5%">
                                                                                                                        <COL width="15%">
                                                                                                                        <COL width="10%">
                                                                                                                       
                                                                                                                        
                                                                                                                       
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
                                                            
                                        
                                      
                                                            

 <tr>                                 
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Active Profiles by Practice</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('ActiveProfiles')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('ActiveProfiles')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="ActiveProfiles" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="dashBoard" theme="simple" name="dashboard">   

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>
                                                                            --%> 
                                                                            <%--  <tr>
                                                                                  <td>
                                                                                      <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                          <tr>                                                    
                                                                                              <td class="fieldLabel">StartDate:</td> <!--value="%{dateWithOutTime}" -->

                                                                                            <td><s:textfield name="dashBoardStartDate" id="dashBoardStartDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calRep.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                         width="20" height="20" border="0"></td>    

                                                                                                    <td class="fieldLabel">EndDate</td>
                                                                                                    <td><s:textfield name="dashBoardEndDate" id="dashBoardEndDate" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calRep1.popup();">
                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                  width="20" height="20" border="0">  </td>

                                                                                                            <td colspan="4" align="center">
                                                                                                             

                                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getActiveProfileByPractice();"/>
                                                                                                            </td> 
                                                                                                            </tr>

                                                                                                            </table>
                                                                                                    </td>
                                                                                        </tr>  --%>

                                                                            <%--                                                  <tr>
                                                                                                                                  <td height="20px" align="center" colspan="9">
                                                                                                                                      <div id="loadActMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                  </td>
                                                                                                                              </tr>

                                                                                        <tr>
                                                                                            <td>
                                                                                                <br>
                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
<tr>
                                                                                                                        <td class="fieldLabelLeft" >
                                                                                                                            No of Active Profiles(Touched for last 30 Days) by Practice
                                                                                                                        </td>
                                                                                                                        <td class="fieldLabelLeft" id="totalProfilesFound">

                                                                                                                        </td>
                                                                                                                    </tr>

                                                                                                    <tr>
                                                                                                        <td >
                                                                                                            <br>
                                                                                                            <div id="accountsByRepList" style="display: block">
                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                <table id="tblActiveProfiles" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                        <COL width="5%">
                                                                                                                        <COL width="15%">
                                                                                                                        <COL width="10%">
                                                                                                                       
                                                                                                                        
                                                                                                                       
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
                                                         

                                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Requirement by Practice</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('requirementsInfo')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('requirementsInfo')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="requirementsInfo" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="dashBoard" theme="simple" name="dashboard">   

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>
                                                                            --%>
                                                                            <%--  <tr>
                                                                                  <td>
                                                                                      <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                          <tr>                                                    
                                                                                              <td class="fieldLabel">StartDate:</td> <!--value="%{dateWithOutTime}" -->

                                                                                            <td><s:textfield name="dashBoardStartDate" id="dashBoardStartDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calRep.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                         width="20" height="20" border="0"></td>    

                                                                                                    <td class="fieldLabel">EndDate</td>
                                                                                                    <td><s:textfield name="dashBoardEndDate" id="dashBoardEndDate" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calRep1.popup();">
                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                  width="20" height="20" border="0">  </td>

                                                                                                            <td colspan="4" align="center">
                                                                                                             

                                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getRequirementInfo();"/>
                                                                                                            </td> 
                                                                                                            </tr>

                                                                                                            </table>
                                                                                                    </td>
                                                                                        </tr>  --%>
                                                                            <%--
                                                                                                                          <tr>
                                                                                                                              <td height="20px" align="center" colspan="9">
                                                                                                                                  <div id="loadActMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                              </td>
                                                                                                                          </tr>

                                                                                        <tr>
                                                                                            <td>
                                                                                                <br>
                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
<tr>
                                                                                                                        <td class="fieldLabelLeft" width="200px">
                                                                                                                            Total Requirements in System :
                                                                                                                        </td>
                                                                                                                        <td class="fieldLabelLeft" id="totalRequirementsFound">

                                                                                                                        </td>
                                                                                                                    </tr>

                                                                                                    <tr>
                                                                                                        <td colspan="2">
                                                                                                            <br>
                                                                                                            <div id="accountsByRepList" style="display: block">
                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                <table id="tblRequirementInfo" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                        <COL width="5%">
                                                                                                                        <COL width="15%">
                                                                                                                        
                                                                                                                       
                                                                                                                        
                                                                                                                       
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
                                                            
                                                            
                                                                            --%>                         


                                                                            <tr>
                                                                                <td class="homePortlet" valign="top">
                                                                                    <div class="portletTitleBar">
                                                                                        <div class="portletTitleLeft">No. of Profiles Submitted Per Requirement by Practice</div>
                                                                                        <div class="portletIcons">
                                                                                            <a href="javascript:animatedcollapse.hide('profilesSubmitted')" title="Minimize">
                                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                            <a href="javascript:animatedcollapse.show('profilesSubmitted')" title="Maximize">
                                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                            </a>                                                           
                                                                                        </div>
                                                                                        <div class="clear"></div>
                                                                                    </div>
                                                                                    <div id="profilesSubmitted" style="background-color:#ffffff">
                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                            <tr>
                                                                                                <td width="40%" valign="top" align="center">
                                                                                                    <s:form action="recruitmentDashboard" theme="simple" name="dashboardProfiles">   

                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                            <tr align="right">
                                                                                                                <td class="headerText" colspan="9">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            <tr>
                                                                                                                <td>
                                                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                                                        <tr>                                                    
                                                                                                                            <td class="fieldLabel">Practice:</td> <!--value="%{dateWithOutTime}" -->

                                                                                                                            <td><s:select headerKey="-1" headerValue="--Please Select--" list="{'SAP','J2EE','Integration','Portals','Commerce','B2B','Others'}" name="practiceId" id="practiceId" cssClass="inputSelect" /></td>    

                                                                                                                            <td class="fieldLabel">StartDate:<FONT color="red"  ><em>*</em></FONT></td> <!--value="%{dateWithOutTime}" -->

                                                                                                                            <td><s:textfield name="startDateSub" id="startDateSub" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calRep05.popup();">
                                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                         width="20" height="20" border="0"></td>    

                                                                                                                                    <td class="fieldLabel">EndDate:<FONT color="red"  ><em>*</em></FONT></td>
                                                                                                                                    <td><s:textfield name="endDateSub" id="endDateSub" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calRep06.popup();">
                                                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                  width="20" height="20" border="0">  </td>
                                                                                                                                            <td>

                                                                                                                                            </td>
                                                                                                                                            <td colspan="2" align="center">


                                                                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getReqProfileSubmittedInfo();"/>
                                                                                                                                            </td> 
                                                                                                                                            </tr>

                                                                                                                                            </table>
                                                                                                                                    </td>



                                                                                                                        </tr>  


                                                                                                                        <tr>
                                                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                                                <div id="loadActMessageNPSR" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                            </td>
                                                                                                                        </tr>

                                                                                                                        <tr>
                                                                                                                            <td>
                                                                                                                                <br>
                                                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >                                                                                                          

                                                                                                                                    <tr>
                                                                                                                                        <td colspan="2">
                                                                                                                                            <br>
                                                                                                                                            <div id="ProfilesSubmittedList" style="display: block">
                                                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                <table id="tblProfilesSubmitted" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                                                        <COL width="10%">
                                                                                                                                                        <COL width="10%">
                                                                                                                                                        <COL width="5%">                                                                                                                       


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
                                                                                                    <script type="text/JavaScript">
                                                                                                        var calRep05 = new CalendarTime(document.forms['dashboardProfiles'].elements['startDateSub']);
                                                                                                        calRep05.year_scroll = true;
                                                                                                        calRep05.time_comp = true;
                                                                                                        var calRep06 = new CalendarTime(document.forms['dashboardProfiles'].elements['endDateSub']);
                                                                                                        calRep06.year_scroll = true;
                                                                                                        calRep06.time_comp = true;
                                                                                                    </script>  
                                                                                                    </div>
                                                                                                </td>
                                                                                            </tr>



                                                                                            <tr>
                                                                                                <td class="homePortlet" valign="top">
                                                                                                    <div class="portletTitleBar">
                                                                                                        <div class="portletTitleLeft">No. of Requirements by Status</div>
                                                                                                        <div class="portletIcons">
                                                                                                            <a href="javascript:animatedcollapse.hide('requirementsStatuswindow')" title="Minimize">
                                                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                            <a href="javascript:animatedcollapse.show('requirementsStatuswindow')" title="Maximize">
                                                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                            </a>                                                           
                                                                                                        </div>
                                                                                                        <div class="clear"></div>
                                                                                                    </div>
                                                                                                    <div id="requirementsStatuswindow" style="background-color:#ffffff">
                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                            <tr>
                                                                                                                <td width="40%" valign="top" align="center">
                                                                                                                    <s:form action="recruitmentDashboard" theme="simple" name="requirementsStatus">   

                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                            <tr align="right">
                                                                                                                                <td class="headerText" colspan="9">
                                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                </td>
                                                                                                                            </tr>
                                                                                                                            <tr>
                                                                                                                                <td>
                                                                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                                                                        <tr>                                                    
                                                                                                                                            <td class="fieldLabel">StartDate:<FONT color="red"  ><em>*</em></FONT></td> <!--value="%{dateWithOutTime}" -->

                                                                                                                                            <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calRep07.popup();">
                                                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                         width="20" height="20" border="0"></td>    

                                                                                                                                                    <td class="fieldLabel">EndDate:<FONT color="red"  ><em>*</em></FONT></td>
                                                                                                                                                    <td><s:textfield name="endDate" id="endDate" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calRep08.popup();">
                                                                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                  width="20" height="20" border="0">  </td>

                                                                                                                                                            <td style="padding-left:0px;"><input type="button" value="Search" class="buttonBg" onclick="getReqStatusInfo();"/></td>         
                                                                                                                                                            </tr>                                                                        
                                                                                                                                                            </table>
                                                                                                                                                    </td>
                                                                                                                                        </tr>  

                                                                                                                                        <tr>
                                                                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                                                                <div id="loadActMessageNRS" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                            </td>
                                                                                                                                        </tr>

                                                                                                                                        <tr>
                                                                                                                                            <td>
                                                                                                                                                <br>
                                                                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                                                                    <tr>
                                                                                                                                                        <td colspan="2">
                                                                                                                                                            <br>
                                                                                                                                                            <div id="requirementsStatusList" style="display: block">
                                                                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                                <table id="tblRequirementsStatus" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                                                                        <COL width="1%">
                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                        <COL width="5%">                                                                                                                                                                                                                                             


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
                                                                                                                    <script type="text/JavaScript">
                                                                                                                        var calRep07 = new CalendarTime(document.forms['requirementsStatus'].elements['startDate']);
                                                                                                                        calRep07.year_scroll = true;
                                                                                                                        calRep07.time_comp = true;
                                                                                                                        var calRep08 = new CalendarTime(document.forms['requirementsStatus'].elements['endDate']);
                                                                                                                        calRep08.year_scroll = true;
                                                                                                                        calRep08.time_comp = true;
                                                                                                                    </script>                                                                    
                                                                                                                    </div>
                                                                                                                </td>
                                                                                                            </tr>





                                                                                                            <tr>
                                                                                                                <td class="homePortlet" valign="top">
                                                                                                                    <div class="portletTitleBar">
                                                                                                                        <div class="portletTitleLeft">No. of Requirements Closed</div>
                                                                                                                        <div class="portletIcons">
                                                                                                                            <a href="javascript:animatedcollapse.hide('requirementsClosed')" title="Minimize">
                                                                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                            <a href="javascript:animatedcollapse.show('requirementsClosed')" title="Maximize">
                                                                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                            </a>                                                           
                                                                                                                        </div>
                                                                                                                        <div class="clear"></div>
                                                                                                                    </div>
                                                                                                                    <div id="requirementsClosed" style="background-color:#ffffff">
                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                            <tr>
                                                                                                                                <td width="40%" valign="top" align="center">
                                                                                                                                    <s:form action="recruitmentDashboard" theme="simple" name="dashboardRequirements">   

                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                            <tr align="right">
                                                                                                                                                <td class="headerText" colspan="9">
                                                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                                </td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                                <td>
                                                                                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                                                                                        <tr>  
                                                                                                                                                            <%--   
                                                                                                                                                               <td class="fieldLabel">Year:</td> <!--value="%{dateWithOutTime}" -->

                                                                                            <td><s:select headerKey="-1" headerValue="--Please Select--" list="{'1980','2008','2009','2010','2011','2012','2013','2014','2015','2016','2017','2018','2019'}" name="year" id="year" cssClass="inputSelect" /></td>                                                                                                                                                                                                                                                                                    
                                                                                            
                                                                                            <td class="fieldLabel">Month:</td> <!--value="%{dateWithOutTime}" -->

                                                                                            <td><s:select headerKey="-1" headerValue="--Please Select--" list="#@java.util.LinkedHashMap@{'01':'Jan','02':'Feb','03':'Mar','04':'Apr','05':'May','06':'Jun','07':'Jul','08':'Aug','09':'Sep','10':'Oct','11':'Nov','12':'Dec'}" name="month" id="month" cssClass="inputSelect" /></td>    

                                                                                                                                                            --%>    

                                                                                                                                                            <td class="fieldLabel">StartDate:<FONT color="red"  ><em>*</em></FONT></td> <!--value="%{dateWithOutTime}" -->

                                                                                                                                                            <td><s:textfield name="startDateClose" id="startDateClose" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calRep09.popup();">
                                                                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                         width="20" height="20" border="0"></td>    

                                                                                                                                                                    <td class="fieldLabel">EndDate:<FONT color="red"  ><em>*</em></FONT></td>
                                                                                                                                                                    <td><s:textfield name="endDateClose" id="endDateClose" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calRep10.popup();">
                                                                                                                                                                            <img  src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                                  width="20" height="20" border="0">  </td>                                                                                                       

                                                                                                                                                                            <td colspan="2" align="center">


                                                                                                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getReqClosedInfo();"/>
                                                                                                                                                                            </td> 
                                                                                                                                                                            </tr>

                                                                                                                                                                            </table>
                                                                                                                                                                    </td>
                                                                                                                                                        </tr>  

                                                                                                                                                        <tr>
                                                                                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                                                                                <div id="loadActMessageNRC" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                                            </td>
                                                                                                                                                        </tr>

                                                                                                                                                        <tr>
                                                                                                                                                            <td>
                                                                                                                                                                <br>
                                                                                                                                                                <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                                                                                                    <%--      <tr>
                                                                                                                                                                                  <td class="fieldLabelLeft" width="200px">
                                                                                                                                                                                      Total Requirements in System :
                                                                                                                                                                                  </td>
                                                                                                                                                                                  <td class="fieldLabelLeft" id="totalRequirementsFound">

                                                                                                                        </td>
                                                                                                                    </tr> --%>

                                                                                                                                                                    <tr>
                                                                                                                                                                        <td colspan="2">
                                                                                                                                                                            <br>
                                                                                                                                                                            <div id="requirementsClosedList" style="display: block">
                                                                                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                                                <table id="tblRequirementsClosed" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                                                                                        <COL width="15%">
                                                                                                                                                                                        <COL width="10%">
                                                                                                                                                                                        <COL width="5%">



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
                                                                                                                                    <script type="text/JavaScript">
                                                                                                                                        var calRep09 = new CalendarTime(document.forms['dashboardRequirements'].elements['startDateClose']);
                                                                                                                                        calRep09.year_scroll = true;
                                                                                                                                        calRep09.time_comp = true;
                                                                                                                                        var calRep10 = new CalendarTime(document.forms['dashboardRequirements'].elements['endDateClose']);
                                                                                                                                        calRep10.year_scroll = true;
                                                                                                                                        calRep10.time_comp = true;
                                                                                                                                    </script>
                                                                                                                                    </div>
                                                                                                                                </td>
                                                                                                                            </tr>


                                                                                                                            <%--Recruitment activitty graph changes--%>

                                                                                                                            <tr>
                                                                                                                                <td class="homePortlet" valign="top">
                                                                                                                                    <div class="portletTitleBar">
                                                                                                                                        <div class="portletTitleLeft">Activities Summary Graph</div>
                                                                                                                                        <div class="portletIcons">
                                                                                                                                            <a href="javascript:animatedcollapse.hide('recactivitysummrepDivGraph')" title="Minimize">
                                                                                                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                            <a href="javascript:animatedcollapse.show('recactivitysummrepDivGraph')" title="Maximize">
                                                                                                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                            </a>                                                           
                                                                                                                                        </div>
                                                                                                                                        <div class="clear"></div>
                                                                                                                                    </div>
                                                                                                                                    <div id="recactivitysummrepDivGraph" style="background-color:#ffffff">
                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                            <tr>
                                                                                                                                                <td width="40%" valign="top" align="center">
                                                                                                                                                    <s:form action="recruitmentDashboard" theme="simple" name="dashboardSummaryRepGraph" >   

                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                            <tr align="right">
                                                                                                                                                                <td class="headerText" colspan="9">
                                                                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                                                </td>
                                                                                                                                                            </tr>
                                                                                                                                                            <tr>
                                                                                                                                                                <td>
                                                                                                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                                                                                                                                        <tr>                                                    
                                                                                                                                                                            <td class="fieldLabel">StartDate:<FONT color="red"  ><em>*</em></FONT></td> <!--value="%{dateWithOutTime}" -->


                                                                                                                                                                            <td><s:textfield name="startDateSummaryGraph" id="startDateSummaryGraph" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:cal1.popup();">
                                                                                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                                                            </td>
                                                                                                                                                                            <td class="fieldLabel">EndDate:<FONT color="red"  ><em>*</em></FONT></td>

                                                                                                                                                                            <td><s:textfield name="endDateSummaryGraph" id="endDateSummaryGraph" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:cal2.popup();">
                                                                                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                                                                                            </td>
                                                                                                                                                                        </tr>                                                                                                          

                                                                                                                                                                        <tr>  
                                                                                                                                                                            <td class="fieldLabel">Activity Type : </td>
                                                                                                                                                                            <td><s:select name="activityTypeGraph" id="activityTypeGraph" value="%{currentConsultant.activityType}"  list="{'Email','Call-Inbound','Call-Outbound'}" cssClass="inputSelect" headerKey="-1" headerValue="All"/></td>
                                                                                                                                                                            <td class="fieldLabel">Recruiter Name :</td>
                                                                                                                                                                            <td>
                                                                                                                                                                                <s:select headerKey="-1" headerValue="--Please Select--" name="createdByGraph" id="createdByGraph" list="assignedMembers" cssClass="inputSelect" />
                                                                                                                                                                            </td>
                                                                                                                                                                            <td colspan="4" align="center">
                                                                                                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getRecActivitiesAsGraph();"/>
                                                                                                                                                                            </td> 
                                                                                                                                                                        </tr>

                                                                                                                                                                    </table>
                                                                                                                                                                </td>
                                                                                                                                                            </tr>

                                                                                                                                                            <tr>
                                                                                                                                                                <td height="20px" align="center" colspan="9">
                                                                                                                                                                    <div id="loadActMessageASh" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                                                </td>
                                                                                                                                                            </tr>

                                                                                                                                                            <tr>
                                                                                                                                                                <td>

                                                                                                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                                                                                        <tr>
                                                                                                                                                                            <td >

                                                                                                                                                                                <div id="resultMessage" align="center" style=" color: red;
                                                                                                                                                                                     font-family: lucida-sans;
                                                                                                                                                                                     font-size: 15px;
                                                                                                                                                                                     font-style: normal;
                                                                                                                                                                                     font-variant: normal;
                                                                                                                                                                                     font-weight: bold; display: none;"></div>
                                                                                                                                                                                <div id="recDashBoardActivitygraph" style="">

                                                                                                                                                                                    <div id="piechart" style="width: 400px; height: 400px;"></div>

                                                                                                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->



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

                                                                                                                                        <script type="text/JavaScript">
                                                                                                                                            var cal1 = new CalendarTime(document.forms['dashboardSummaryRepGraph'].elements['startDateSummaryGraph']);
                                                                                                                                            cal1.year_scroll = true;
                                                                                                                                            cal1.time_comp = false;
                                                                                                                                            var cal2 = new CalendarTime(document.forms['dashboardSummaryRepGraph'].elements['endDateSummaryGraph']);
                                                                                                                                            cal2.year_scroll = true;
                                                                                                                                            cal2.time_comp = false;
                                                                        
                                                                                                                                        </script>
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
                                                                                        </body>

                                                                                        </html>
