<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Map,java.util.TreeMap" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>
        <title>Hubble Organization Portal :: Venus Report </title>
        <%-- <sx:head cache="true"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/VenusReport.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/VenusReportAjax.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        
        <s:include value="/includes/template/headerScript.html"/>        
        <script type="text/JavaScript">
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
                document.getElementById('absStartDate').value=firstDayOfMonth;
                document.getElementById('absEndDate').value=firstDayOfMonth;
            }
        </script>
    </head>
    
   <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getDate();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String submittedFrom;
        String searchSubmitValue;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
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
                                    <li><a href="#" class="selected" rel="report">Employee Attendance Report</a></li>
                                    <li><a href="#" rel="Absreport">Employee Absentees Report</a></li>
                                </ul>
                                
                                <!--//START TABBED PANNEL : -->
                                <%-- <sx:tabbedpanel id="reportSelPannel" cssStyle="width: 840px; height: 480px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                    <div style="border:1px solid gray; width: 840px; height: 480px; overflow:auto; margin-bottom: 1em;">    
                                        
                                        <!--//START TAB : -->
                                        <%-- <sx:div id="report" label="Employee Attendance Report" cssStyle="overflow:auto;"> --%>
                                        <div id="report" class="tabcontent"> 
                                        <s:form name="forMycop" action="getVenusReport" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                            <!-- for displaying action errors and field errors -->
                                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                <tr>
                                                    <td class="headerText" align="right">
                                                        <s:property value="#request.resultMessage"/>
                                                        
                                                        <input type="button" value="Search" class="buttonBg" onclick="getVenusReportList();"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table width="80%" cellpadding="2" cellspacing="2" border="0">
                                                            <tr>
                                                                <td align="right" class="fieldLabel">Name:</td>
                                                                <td>
                                                                    <%--// <s:select list="MyTeamMembers" name="username" id="username" headerKey="" headerValue="--Select Team--" theme="simple" cssClass="inputSelect" /> --%>
                                                                    <s:textfield name="venusEmpName" id="venusEmpName" cssClass="inputTextBlue"/>
                                                                </td>
                                                                <%--  <td align="center"><font class="fieldLabel"></font></td> --%>
                                                                <td class="fieldLabel">Department :</td>
                                                                
                                                                
                                                                <td><s:select label="Select Department" 
                                                                                  name="departmentId" 
                                                                                  id="departmentId"
                                                                                  
                                                                              list="{'Executive Board','GDC','Marketing','Operations','Recruiting','Sales','SSG'}" cssClass="inputSelect" value="%{'GDC'}"/></td>
                                                                
                                                            </tr>
                                                            
                                                            
                                                            <tr>
                                                                <td align="right" class="fieldLabel">Start Date:</td>
                                                                <td><s:textfield id="startDate" name="startDate" cssClass="inputTextBlue" value="%{}"  required="true"/>
                                                                    <a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                <td align="right" class="fieldLabel">End Date:</td>
                                                                <td><s:textfield name="endDate" cssClass="inputTextBlue" id="endDate" value="%{}"  required="true"/>
                                                                    <a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                         width="20" height="20" border="0" ></a>
                                                                </td>
                                                                
                                                                
                                                            </tr>
                                                            
                                                        </table>            
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <tr>
                                                        <td>
                                                            
                                                            <br>
                                                            <div id="consultantList" style="display: block">
                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                <table id="tblUpdate" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                                                                    <COLGROUP ALIGN="left" >
                                                                    <COL width="3%">
                                                                    <COL width="25%">
                                                                    <COL width="22%">
                                                                    <COL width="23%">
                                                                    <COL width="15%">
                                                                    <COL width="2%">
                                                                </table>
                                                                <br>
                                                                <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                            </div>
                                                        </td>
                                                    </tr>
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
                                        
                                        </div>       
                                    <%-- </sx:div> --%>
                                    <!--//END TAB : -->
                                    
                                        <div id="Absreport" class="tabcontent"> 
                                            <%-- <sx:div id="Absreport" label="Employee Absentees Report" cssStyle="overflow:auto;"> --%>
                                            <s:form name="absReport" action="getVenusReport" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                                <!-- for displaying action errors and field errors -->
                                                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                    <tr>
                                                        <td class="headerText" align="right">
                                                            <s:property value="#request.resultMessage"/>
                                                            
                                                            <input type="button" value="Search" class="buttonBg" onclick="getAbsentees();"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <div id="loadMessage" style="display: none" align="center" class="error" >
                                                                <b>Loading.... Please Wait....</b>
                                                            </div>
                                                            <table width="80%" cellpadding="2" cellspacing="2" border="0">
                                                                <tr>
                                                                    <td align="right" class="fieldLabel">Name:</td>
                                                                    <td>
                                                                        <%--// <s:select list="MyTeamMembers" name="username" id="username" headerKey="" headerValue="--Select Team--" theme="simple" cssClass="inputSelect" /> --%>
                                                                        <s:textfield name="absEmpName" id="absEmpName" cssClass="inputTextBlue" />
                                                                    </td>
                                                                    <%--  <td align="center"><font class="fieldLabel"></font></td> --%>
                                                                    <td class="fieldLabel">Department :</td>
                                                                    
                                                                    
                                                                    <td><s:select label="Select Department" 
                                                                                      name="departmentId1" 
                                                                                      id="departmentId1"
                                                                                      
                                                                                  list="{'Executive Board','GDC','Marketing','Operations','Recruiting','Sales','SSG'}" cssClass="inputSelect" value="%{'GDC'}"/></td>
                                                                    
                                                                    
                                                                </tr>
                                                                
                                                                
                                                                <tr>
                                                                    <td align="right" class="fieldLabel">Start Date:</td>
                                                                    <td><s:textfield id="absStartDate" name="absStartDate" cssClass="inputTextBlue" value="%{}"  required="true"/>
                                                                        <a href="javascript:cal3.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                             width="20" height="20" border="0" ></a>
                                                                    </td>
                                                                    <td align="right" class="fieldLabel">End Date:</td>
                                                                    <td><s:textfield id="absEndDate" name="absEndDate" cssClass="inputTextBlue"  value="%{}"  required="true"/>
                                                                        <a href="javascript:cal4.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                             width="20" height="20" border="0" ></a>
                                                                    </td>
                                                                    
                                                                    
                                                                </tr>
                                                              <%--  <tr>
                                                                    <td>
                                                                        <td>
                                                                             <s:textfield cssStyle="display:none;" name="inputRowData" id="inputRowData" cssClass="inputTextBlue" readonly="true"/> 

                                                                        </td>
                                                                    </td>
                                                                    
                                                                </tr>  --%>
                                                                
                                                            </table>            
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <tr>
                                                            <td>
                                                                
                                                                <br>
                                                                <div id="consultantList" style="display: block">
                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                    <table id="tblAbsantee" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='80%' align="center">
                                                                        <COLGROUP ALIGN="left" >
                                                                        <COL width="3%">
                                                                        <COL width="20%">
                                                                        <COL width="22%">
                                                                        <COL width="20%">
                                                                        <COL width="10%">
                                                                        
                                                                    </table>
                                                                    <br>
                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tr>
                                                </table>
                                            </s:form>
                                            
                                            <script type="text/JavaScript">
                                         var cal3 = new CalendarTime(document.forms['absReport'].elements['absStartDate']);
	                                cal3.year_scroll = true;
	                                cal3.time_comp = false;
                                        var cal4 = new CalendarTime(document.forms['absReport'].elements['absEndDate']);
	                                cal4.year_scroll = true;
	                                cal4.time_comp = false;
                                            
                                            </script>
                                        </div>
                                        <%-- </sx:div> --%>
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
