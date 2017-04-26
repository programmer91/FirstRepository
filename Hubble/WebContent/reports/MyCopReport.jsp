<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Map,java.util.TreeMap" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        <title>Hubble Organization Portal :: MyCop Report </title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        
        <script>
            function getDate() {
                //var temp = 0;
                var currentYear = new Date().getFullYear();    
                var currentMonth = new Date().getMonth() +1;    
                var currentDay = new Date().getDate();
                if(currentDay <10 ){
                    currentDay = '0'+ currentDay;
                }
                if(currentMonth <10 ){
                    currentMonth = '0'+ currentMonth;
                }
                // month-date-year
                var firstDayOfMonth = (currentMonth) + '/' + (currentDay) + '/' + currentYear;
                document.getElementById('startDate').value = firstDayOfMonth;
                document.getElementById('endDate').value = firstDayOfMonth;
            }
            
            function changeAction() {
                var formId = document.getElementById("forMycop");
                formId.action = "reportExcel.action";
                //alert(document.getElementById("forMycop").action);
            }

        </script>
        <s:include value="/includes/template/headerScript.html"/>
    </head>
    
  <!--  <body class="bodyGeneral" oncontextmenu="return false;" onload="getDate()"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">
        
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
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top">  
                                <s:include value="/includes/template/LeftMenu.jsp"/>  
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->                            
                            <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px;">    
                                
                                <ul id="reportSelPannel" class="shadetabs">
                                    <li ><a href="#" class="selected" rel="report">Employee Attendance Report</a></li>
                                </ul>
                                
                                <!--//START TABBED PANNEL : -->
                                <div  style="border:1px solid gray; width: 845px; height: 300px; overflow:auto; margin-bottom: 1em;">
                                    <%-- <sx:tabbedpanel id="reportSelPannel" cssStyle="width: 840px; height: 300px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                    
                                    <!--//START TAB : -->
                                    <div id="report" class="tabcontent">      
                                        <%-- <sx:div id="report" label="Employee Attendance Report"> --%>
                                        <s:form name="forMycop" id="forMycop" action="report" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                            <!-- for displaying action errors and field errors -->
                                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                <tr>
                                                    <td class="headerText" align="right">
                                                        <s:property value="#request.resultMessage"/>
                                                        
                                                        <s:submit value="Generate Report" cssClass="buttonBg"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table width="80%" cellpadding="2" cellspacing="2" border="0">
                                                            <tr>
                                                                <td align="right" class="fieldLabel">LoginId:</td>
                                                                <td>
                                                                    <s:select list="MyTeamMembers" name="username" id="username" headerKey="" headerValue="--Select Team--" theme="simple" cssClass="inputSelect" />
                                                                </td>
                                                                <td align="center"><font class="fieldLabel">(OR)</font></td>
                                                                <td><s:checkbox id="all_users"  name="all" value="false" fieldValue="true" onchange="checkOption();"><font class="fieldLabel">Team Report</font></s:checkbox></td>
                                                                <s:if test="#session.roleId == 7">
                                                                    <td align="center"><font class="fieldLabel">(OR)</font></td>
                                                                    <td><s:checkbox id="all_emp" label="All Users" name="all_emp" fieldValue="true" checked="true" onchange="checkOption();"><font class="fieldLabel">All Users Report</font></s:checkbox></td>
                                                                </s:if>
                                                            </tr>
                                                            
                                                            
                                                            <tr>
                                                                <td align="right" class="fieldLabel">Start Date:</td>
                                                                <td><s:textfield id="startDate" cssClass="inputTextBlue"  name="startDate"  required="true"/>
                                                                    <a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                <td align="right" class="fieldLabel">End Date:</td>
                                                                <td><s:textfield id="endDate" cssClass="inputTextBlue" name="endDate"  required="true"/>
                                                                    <a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                    
                                                                </td>
                                                                
                                                                <s:if test="#session.roleId == 7">
                                                                    <td></td>
                                                                    <td></td>
                                                                </s:if>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="4" >
                                                                    <s:checkbox id="excelReport"  name="excelReport" fieldValue="true" onclick="changeAction();"><font class="fieldLabel">For Excel Report</font></s:checkbox>
                                                                </td>
                                                            </tr>
                                                        </table>            
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                        
                                        <script type="text/JavaScript">
                                        var cal1 = new CalendarTime(document.forms['forMycop'].elements['startDate']);
	                                cal1.year_scroll = true;
	                                cal1.time_comp = false;
                                        var cal2 = new CalendarTime(document.forms['forMycop'].elements['endDate']);
	                                cal2.year_scroll = true;
	                                cal2.time_comp = false;                                            
                                        </script>                                        

                                    
                                   <%-- <sx:div id="report1" label="HR Permission Report">
                                        <s:form name="permissionReport" id="permissionReport" action="permReport" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate1').value,document.getElementById('endDate1').value));">
                                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                <tr>
                                                    <td class="headerText" align="right">
                                                        <s:property value="#request.resultMessage" />
                                                        
                                                        <s:submit label="Generate Report" cssStyle="buttonBg" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table width="80%" cellpadding="2" cellspacing="2" border="0">
                                                            <tr>
                                                                <td align="right" class="fieldLabel">Dept</td>
                                                                <td>
                                                                    <s:select list="departmentList" name="departmentList" id="departmentList" headerKey="" headerValue="--Select Team--" theme="simple" cssClass="inputSelect" />
                                                                </td>                                                                        
                                                                
                                                                <td align="right" class="fieldLabel">Start Date:</td>
                                                                <td><s:textfield id="startDate1" cssClass="inputTextBlue"  name="startDate1"  required="true"/>
                                                                    <a href="javascript:cal3.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                <td align="right" class="fieldLabel">End Date:</td>
                                                                <td><s:textfield id="endDate1" cssClass="inputTextBlue" name="endDate1"  required="true"/>
                                                                    <a href="javascript:cal4.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                    
                                                                </td>
                                                                
                                                                <s:if test="#session.roleId == 7">
                                                                    <td></td>
                                                                    <td></td>
                                                                </s:if>
                                                            </tr>
                                                            
                                                        </table>
                                                    </td>
                                                    
                                                </tr>
                                                
                                            </table>
                                            
                                        </s:form>
                                        <script type="text/JavaScript">
                                         var cal3 = new CalendarTime(document.forms['permissionReport'].elements['startDate1']);
	                                cal3.year_scroll = true;
	                                cal3.time_comp = false;
                                        var cal4 = new CalendarTime(document.forms['permissionReport'].elements['endDate1']);
	                                cal4.year_scroll = true;
	                                cal4.time_comp = false;
                                            
                                        </script>
                                    </sx:div> --%>

                                    </div>    
                                    <%-- </sx:div> --%>                                    

                                    <!--//END TAB : -->
                                </div>    
                                <%-- </sx:tabbedpanel> --%>
                                <!--//END TABBED PANNEL : -->
                              
                                <script type="text/javascript">

var countries=new ddtabcontent("reportSelPannel")
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
	getDate();
		});
		</script>
    </body>
</html>
