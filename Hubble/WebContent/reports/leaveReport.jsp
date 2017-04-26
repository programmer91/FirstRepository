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
        <title>Hubble Organization Portal :: Leave Report </title>
        <%-- <sx:head cache="true"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link REL="StyleSheet" HREF="<s:url value="/includes/css/leftMenu.css"/>" type="text/css">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        
        <script type="text/JavaScript" language="JavaScript">
            function validateFields() {
                var checkApplied = document.employeeUpdate.futureLeaves;
                //alert(checkApplied.checked);
                if(checkApplied.checked == true) {
                    return true;
                }else {                
                var departmentName = document.employeeUpdate.departmentId.value;
                var practiceName = document.employeeUpdate.practiceId.value;            
                var subPracticeName = document.employeeUpdate.subPractice.value; 
                var teamName = document.employeeUpdate.teamId.value; 
                var teamMemberName = document.employeeUpdate.teamMemberId.value;
                var status = document.employeeUpdate.status.value;                     
                if(departmentName == 0 || practiceName.length == 0 || subPracticeName == 0 || teamName == 0 || teamMemberName == 0 || status == 0) {
                    alert(" Please Provide Relevent Information! ");                 
                    return false;
                }else{
                    return true;
                }//  return true;
                }
            };
            function validateDates() {    
    
                var startDate = document.getElementById('startDate').value;             
                var endDate = document.getElementById('endDate').value;
                var checkApplied = document.employeeUpdate.futureLeaves;
                
                if(checkApplied.checked == true) {
                            return true;
                }else {           
                if(startDate.length==0 || endDate.length==0){
                alert("Start-date and End-date are required.");
                return false;
                }else{
                return true;
                }
           }
    
        };


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

        </script>
        
        <s:include value="/includes/template/headerScript.html"/>
    </head>
    
   <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getDate()"> -->
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
                                    <li ><a href="#" class="selected" rel="report">Employee Leave Report</a></li>
                                </ul>
                                
                                <!--//START TABBED PANNEL : -->
                                <%-- <sx:tabbedpanel id="reportSelPannel" cssStyle="width: 840px; height: 300px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                <div style="border:1px solid gray; width: 840px; height: 300px; overflow:auto; margin-bottom: 1em;">    
                                    <!--//START TAB : -->
                                    <div id="report" class="tabcontent"> 
                                    <%-- <sx:div id="report" label="Employee Leave Report"> --%>
                                        <s:form name="employeeUpdate" id="employeeUpdate" action="generateLeavereport" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                            <!-- for displaying action errors and field errors -->
                                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                <tr>
                                                    <td class="headerText" align="right">
                                                        <s:property value="#request.reportResult"/>
                                                        <s:submit name="submit" value="Generate Report" cssClass="buttonBg"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table width="100%" cellpadding="2" cellspacing="2" border="0">
                                                            <tr>
                                                                
                                                                <td class="fieldLabel" width="200px" align="right">Department :</td>
                                                                <td>
                                                                    <s:select label="Select Department" 
                                                                              name="departmentId"
                                                                              id="departmentId"    
                                                                              headerKey="All"
                                                                              headerValue="All"
                                                                              list="departmentIdList" cssClass="inputSelect"  onchange="getPracticeDataV1();"/>
                                                                </td>    
                                                                
                                                                <td class="fieldLabel" width="200px" align="right">Practice Name :</td>
                                                                <script type="text/javascript" >
                                                                    getPracticeDataV1();
                                                                </script>
                                                                <td><s:select label="Select Practice Name" 
                                                                                  name="practiceId" 
                                                                                  id="practiceId"     
                                                                                  headerKey="All"
                                                                                  headerValue="All"
                                                                                  list="{}" cssClass="inputSelect"  onchange="getSubPracticeData();"/> <%--onchange="getTeamData();" --%>
                                                                </td>                                                                  
                                                                
                                                            </tr>
                                                            
                                                            <tr>                                                                
                                                                <td class="fieldLabel" width="200px" align="right">SubPractice Name :</td>
                                                                <script type="javascript" >
                                                                    getSubPracticeData();
                                                                </script>

                                                                <td><s:select name="subPractice" 
                                                                                  id="subPractice"                                                                                  
                                                                                  headerKey="All"
                                                                                  headerValue="All"
                                                                                  list="{}" cssClass="inputSelect"  onchange="getTeamData();"/></td>  
                                                                
                                                                <td class="fieldLabel" width="200px" align="right">Team Name :</td>
                                                                <script type="javascript" >
                                                                    getSubPracticeData();
                                                                </script>
                                                                
                                                                <td><s:select label="Select Team Name" 
                                                                                  name="teamId"  
                                                                                  id="teamId"   
                                                                                  headerKey="All"
                                                                                  headerValue="All"
                                                                                  list="{}" cssClass="inputSelect"  onchange="getTeamMemberData();"/>
                                                                </td>
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="fieldLabel" width="200px" align="right">TeamMember Name :</td>
                                                                
                                                                <td><s:select label="Select TeamMember Name" 
                                                                                  name="teamMemberId" 
                                                                                  id="teamMemberId"      
                                                                                  headerKey="All"
                                                                                  headerValue="All"
                                                                                  
                                                                                  list="{}" cssClass="inputSelect" />
                                                                </td>
                                                                
                                                                <td align="right" class="fieldLabel">Status:</td>
                                                                <td>
                                                                    <s:select list="{'Applied','postApproved','cancel'}" 
                                                                              label="Select Status"
                                                                              name="status" 
                                                                              id="status"    
                                                                              headerKey="approved"
                                                                              headerValue="approved"
                                                                              theme="simple" cssClass="inputSelect" />
                                                                </td>  
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
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel" height="40px" ><s:checkbox  id="futureLeaves" name="futureLeaves" value="futureLeaves" ></s:checkbox></td>                                                                           
                                                                <td colspan="3" class="fieldLabelLeft" >Select Check Box (If You Need Applied/Approved Consolidated Scheduled LeaveReport)</td>                                                                
                                                            </tr>
                                                            
                                                        </table>            
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                        
                                        <script type="text/JavaScript">
                                         var cal1 = new CalendarTime(document.forms['employeeUpdate'].elements['startDate']);
	                                cal1.year_scroll = true;
	                                cal1.time_comp = false;
                                        var cal2 = new CalendarTime(document.forms['employeeUpdate'].elements['endDate']);
	                                cal2.year_scroll = true;
	                                cal2.time_comp = false;                                            
                                        </script>
                                     </div>   
                                    <%-- </sx:div > --%>
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
