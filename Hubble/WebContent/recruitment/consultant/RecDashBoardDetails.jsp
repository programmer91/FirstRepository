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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/oppdashboardAjaxUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GreensheetListSearch.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GreensheetListResultSearch.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ReusableContainer.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
       

        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
                
            animatedcollapse.addDiv('recactivitysummrepDiv', 'fade=1;speed=400;persist=1;group=app');
          

            //animatedcollapse.addDiv('mirageDiv', 'fade=1;speed=400;persist=1;group=app');
            //animatedcollapse.addDiv('hubbleDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.init();
            function hideSelect(){
                document.getElementById("priorityId").style.display = 'none';
                
            }
        </script>
    </head>
    <!-- <body  class="bodyGeneral" onload="hideSelect();" oncontextmenu="return false;"> -->
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
                                <ul id="recDashBoardActivity" class="shadetabs" >
                                    <li><a href="#" rel="recDashBoardActivityListTab" class="selected"> DashBoard Details </a></li>

                                </ul>
                                <div  style="border:1px solid gray; width:840px;height: 550px;overflow:auto; margin-bottom: 1em;">    
                                    <div id="recDashBoardActivityListTab" class="tabcontent" >  
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
                                                                    <s:form action="dashBoard" theme="simple" name="dashboardRep" >   

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

                                                                                            <td><s:textfield name="startdate" id="startDate" cssClass="inputTextBlue" onchange="checkDates(this);"/> <a href="javascript:calRep.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                         width="20" height="20" border="0"></td>    

                                                                                            <td class="fieldLabel">EndDate:<FONT color="red"  ><em>*</em></FONT></td>
                                                                                            <td><s:textfield name="enddate" id="endDate" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:calRep1.popup();">
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
                                                                                <div id="loadActMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
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
                                                                        var calRep = new CalendarTime(document.forms['dashboardRep'].elements['startdate']);
                                                                        calRep.year_scroll = true;
                                                                        calRep.time_comp = true;
                                                                        calRep1 = new CalendarTime(document.forms['dashboardRep'].elements['enddate']);
                                                                        calRep1.year_scroll = true;
                                                                        calRep1.time_comp = true;
                                                                    </script>
                                                                    </div>
                                                                </td>
                                                            </tr>

                                                                                            
                                                                                            <!-- new div for account list by priority start -->
                                                                                           
                                                                                            <!-- new div for account list by priority end -->

                                                                                        </table>

                                                                                        <%--     </sx:div>
                                                                                </sx:tabbedpanel> --%>
                                                                                        <!--//END TABBED PANNEL : --> 
                                                                                    </div>
                                                                                    </div>
                                                                                    <script type="text/javascript">

                                                                                        var countries=new ddtabcontent("recDashBoardActivity")
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
	hideSelect();
		});
		</script>
                                                        </html>
