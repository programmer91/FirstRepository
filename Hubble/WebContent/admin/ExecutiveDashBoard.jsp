 <%-- 
    Document   : ExecutiveDashBoard
    Created on : Sep 18, 2015, 10:49:49 PM
    Author     : miracle
--%>



<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
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
        <title>Hubble Organization Portal :: Executive DashBoard</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css?version=2.1"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/animatedcollapse.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-1.2.2.pack.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/Activity.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ReusableContainer.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ExecutiveDashBoard.js?version=2.2"/>"></script>
          <script type="text/javascript" src="https://www.google.com/jsapi"></script> 
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
                  google.load("visualization", "1", {packages:["corechart"]});   


           
            //            animatedcollapse.addDiv('LeavesCountExcelReportDiv', 'fade=1;persist=1;group=app');
            //            animatedcollapse.addDiv('GenerateTimeSeetReport', 'fade=1;persist=1;group=app');
            //            animatedcollapse.addDiv('GenerateHierarchyReport', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('EmplyeeCountDiv', 'fade=1;persist=1;group=app');
              animatedcollapse.addDiv('BDMStatisticsDiv', 'fade=1;persist=1;group=app');
            // animatedcollapse.addDiv('RequirementCountDiv', 'fade=1;persist=1;group=app');
             
            animatedcollapse.init();
            function hideSelect(){
                //document.getElementById("priorityId").style.display = 'none';
                
            }
            function getSalesClosuresReport(){
  
                var year = document.getElementById('yearForSalesClosuresReport').value;
                var clouserFlag = document.getElementById('isClouserFlag').checked;
                //alert(clouserFlag);
    
                // var practiceId = document.getElementById('practiceId').value;
                //var teamId = document.getElementById('teamId').value;
                if(year.length==''){
                    alert("Please Enter year");
                    return false;
                }
                //var empnameById = document.getElementById('empnameById').value;
                // window.location ="generateSalesClosuresReport.action?teamId="+teamId+"&practiceId="+practiceId+"&year="+year;  
                window.location ="generateSalesClosuresReport.action?year="+year+"&isClouserFlag="+clouserFlag;  
                return true;
            }
        </script>
    </head>
  <!--  <body  class="bodyGeneral" onload="hideSelect();javascript:animatedcollapse.show('EmplyeeCountDiv');getEmpolyeeCount();" oncontextmenu="return false;"> -->
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
                                <ul id="reportsTab" class="shadetabs" >
                                    <li><a href="#" rel="EmpReportsTab" class="selected">Executive DashBoard</a></li>

                                </ul>
                                <div  style="border:1px solid gray; width:840px;height:675px;overflow:auto; margin-bottom: 1em;">    
                                    <br>
                                    <div id="EmpReportsTab" class="tabcontent" >  
                                        
                                        
  <div id="overlayExecutiveDashBoard"></div>              <!-- Start Overlay -->

                            <div id="specialBoxExecutiveDashBoard">

                                <div id="employeeTypeDetails"  >
                                    <table align="center" border="0" cellspacing="0" style="width:100%;">
                                        <tr>                               
                                        <td colspan="2" style="background-color: #288AD1" >
                                            <h3 style="color:darkblue;" align="left">
                                                <span id="headerLabel1"></span>


                                            </h3></td>
                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                            <a href="#" onmousedown="toggleCloseUploadOverlay1()" >
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
                                            <table id="tblEmployeeTypeDetails"  class="gridTable" width="400" cellspacing="1" cellpadding="7" border="0" align="center" style="margin-left:37px;">
                                                <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script> --%>
                                                <COLGROUP ALIGN="left" >
                                                    <COL width="15%">
                                                        <COL width="15%">


                                                            </table> 
                                              
                                                            </td>
                                                          
                                                            </tr>
                                                            <%-- <tr><td>  <s:label  class="fieldLabel">Total:</s:label></td>
                                                            <td><s:label id="total" class="fieldLabel"></s:label></td><td></td></tr> --%>

                                                            </table>    

                                                            </div>
                                                            </div>	
											
                                        
                                        
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">



                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Executive DashBoard</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('EmplyeeCountDiv')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('EmplyeeCountDiv')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="EmplyeeCountDiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="employeeCount" theme="simple" name="employeeCount" id="employeeCount">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                            <tr align="right">
                                                                                <td class="headerText" colspan="9">
                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <%
                                                                                        if (session.getAttribute("resultMessageForSalesClosure") != null) {
                                                                                            out.println(session.getAttribute("resultMessageForSalesClosure"));
                                                                                            session.removeAttribute("resultMessageForSalesClosure");
                                                                                        }

                                                                                    %>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">

                                                                                        <tr>                                                    
                                                                                            <td class="fieldLabel">Past:</td> <!--value="%{dateWithOutTime}" -->

                                                                                            <td>
                                                                                                <s:select label="Select Month" 
                                                                                                          name="pastMonths"
                                                                                                          id="pastMonths"


                                                                                                          list="#@java.util.LinkedHashMap@{'3':'3 Months','6':'6 Months','9':'9 Months','12':'12 Months'}" cssClass="inputSelect" onChange="getEmpolyeeCount();"/>
                                                                                            </td>    


                                                                                        </tr>




                                                                                    </table>
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>

                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td height="20px" align="center" colspan="9">
                                                                                    <div id="loadActMessageAS" style="display:none" class="error" ><b></b></div>
                                                                                    <div id="loadEmployeeCount" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                        <tr>
                                                                                            <td class="portletTitleLeft"  style="color: green; font-size:14px">Employee Details</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td >
                                                                                                <div id="topPerfReviewsList" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblEmployeeCount" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="5%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="13%">
                                                                                                    </table>
                                                                                                    
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>
                                                                             <tr>
                                                                                <td height="20px" align="center" colspan="9">
                                                                                    <div id="loadRequirment" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                        <tr>
                                                                                            <td class="portletTitleLeft"  style="color: green; font-size:14px">Requirements Details</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td>
                                                                                                <div id="topPerfReviewsList" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblRequirement" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="5%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="10%">
                                                                                                            <COL width="13%">
                                                                                                    </table>
                                                                                                    
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td height="20px" align="center" colspan="9">
                                                                                    <div id="loadGreenSheetsCount" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                        <tr>
                                                                                            <td class="portletTitleLeft"  style="color: green; font-size:14px">GreenSheet Details</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            
                                                                                            <td align="center">
                                                                                                <div style="display: block;">
                                                                                                    <spam class="fieldLabel">Consultant Type</spam>
                                                                                                     <s:select label="Select Month" 
                                                                                                          name="external"
                                                                                                          id="external"


                                                                                                          list="#@java.util.LinkedHashMap@{'true':'External','false':'Internal'}" cssClass="inputSelect"  onChange="getGreenSheetCount(0);"/>
                                                                                           
                                                                                                   <s:hidden name="opertunitiesClear" id="opertunitiesClear"/>
                                                                                                    
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td >
                                                                                                <div style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblGreenSheets" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="5%">
                                                                                                            <COL width="20%">
                                                                                                            <COL width="20%">
                                                                                                           
                                                                                                    </table>
                                                                                                   
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td height="20px" align="center" colspan="9">
                                                                                    <div id="loadOpertunitiesCounts" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                        <tr>
                                                                                            <td class="portletTitleLeft"  style="color: green; font-size:14px">Opportunities Details</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td >
                                                                                                <div id="topPerfReviewsList" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblOpertunitiesCount" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
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
                                                                            <tr>
                                                                                <td height="20px" align="center" colspan="9">
                                                                                    <div id="loadOpertunitiesCount" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                        <tr>
                                                                                        <td class="portletTitleLeft"  style="color: green; font-size:14px"><spam id="statusLabel"></spam> Opportunities Details by Account</td>
                                                                                        </tr>
                                                                                              <tr>
                                                                                            
                                                                                            <td align="center">
                                                                                                <div style="display: block;">
                                                                                                    <spam class="fieldLabel">Status :</spam>
                                                                                                     <s:select label="Select State" 
                                                                                                          name="opportunityState"
                                                                                                          id="opportunityState"


                                                                                                          list="opportunityStateList" cssClass="inputSelect"  onChange="getOpportunitiesCount(0);"/>
                                                                                           
                                                                                                 <s:hidden name="opertunitiesStateClear" id="opertunitiesStateClear"/>
                                                                                                    
                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td >
                                                                                                <div id="topPerfReviewsList" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblOpertunities" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                           <COL width="5%">
                                                                                                            <COL width="20%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="15%"> 
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
                                                                             <tr>
                                                                                <td height="20px" align="center" colspan="9">
                                                                                    <div id="loadLostClosedOpertunitiesCount" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                        <tr>
                                                                                            <td class="portletTitleLeft"  style="color: green; font-size:14px">Lost/Closed Opportunities Details by Account</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td >
                                                                                                <div id="topPerfReviewsList" style="display: block">
                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                    <table id="tblLostClosedOpertunities" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                           <COL width="5%">
                                                                                                            <COL width="20%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="15%">
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
                                            
    <%-- BDM statistics start --%>
                                            
                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">BDM Statistics</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('BDMStatisticsDiv')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('BDMStatisticsDiv')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    
                                                     <div id="BDMStatisticsDiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="80%" valign="top" align="center">
                                                                    <s:form action="BDMStatistics" theme="simple" name="BDMStatistics" id="BDMStatistics">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                              <tr>
                                                                                <td>
                                                                                   <table border="0" align="center" cellpadding="0" cellspacing="0" >
                                                                                       
                                                                                       <tr><td><table border="0" align="center" cellpadding="0" cellspacing="0"><tr> <td class="fieldLabel">StartDate:<FONT color="red"  ><em>*</em></FONT></td> <!--value="%{dateWithOutTime}" -->


                                                                                                            <td><s:textfield name="startDateSummaryGraph" id="startDateSummaryGraph" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:cal1.popup();">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                            </td>
                                                                                                            <td class="fieldLabel">EndDate:<FONT color="red"  ><em>*</em></FONT></td>

                                                                                                            <td><s:textfield name="endDateSummaryGraph" id="endDateSummaryGraph" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:cal2.popup();">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                            </td></tr>
                                                                                           <tr>
                                                                                            
                                                                                                 
                                                                                            <td class="fieldLabel">BDM&nbsp;:</td>
                                                                                            <td>
                                                                                            <s:select list="bdmMap" headerKey="All" headerValue="All" name="bdmId" id="bdmId"  cssClass="inputSelect"/> </td>
                                                                                                   <td ></td><td>
                                                                                                                    <input type="button"  class="buttonBg" onclick="getBDMStatistics()" value="Search"/>

                                                                                                                </td>

                                                                                        </tr>
                                                                                           </table></td></tr>
                                                                                       
                                                                                         
                                                                                            
                                                                                            <tr>
                                                                                                <td height="20px" align="center" colspan="4">
                                                                                                    <div id="resultDisplay" style="display:none" class="error"></div>
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
                                                                                                                <div id="bdmDashBoardActivitygraph" style="">

                                                                                                                    <div id="piechart" style="width: 400px; height: 400px;"></div>

                                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->



                                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                </div>
                                                                                                            </td>
                                                                                                        </tr>
                                                                                                    </table>    
                                                                                                </td>
                                                                                            </tr>
                                                                                            
                                                                                            
                                                                                             <!-- new graph details start -->
                                                                                           <tr>
                                                                                                <td height="20px" align="center" colspan="4">
                                                                                                    <div id="loadActMessageASh" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td colspan="3">
                                                                                                    <div id="tableHeading" style="display: none;" align='center'>Activities</div>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td colspan="4">




                                                                                                    <table id="tblBdmActivitySummaryByLoginId" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="7%">
                                                                                                            <COL width="15%">
                                                                                                            <COL width="20%">
                                                                                                            <COL width="7%">
                                                                                                            <COL width="7%">
                                                                                                            <COL width="7%">
                                                                                                            <COL width="7%"> 
                                                                                                            <COL width="7%">
                                                                                                            <COL width="7%">

                                                                                                    </table>  



                                                                                                </td>
                                                                                            </tr>
                                                                                            <!-- new graph details end -->
                                                                                            
                                                                                    </table>    
                                                                                </td>
                                                                            </tr> 
                                                                        </table>
                                                                    </s:form>
                                                                    <script type="text/JavaScript">
                                                                            var cal1 = new CalendarTime(document.forms['BDMStatistics'].elements['startDateSummaryGraph']);
                                                                            cal1.year_scroll = true;
                                                                            cal1.time_comp = false;
                                                                            var cal2 = new CalendarTime(document.forms['BDMStatistics'].elements['endDateSummaryGraph']);
                                                                            cal2.year_scroll = true;
                                                                            cal2.time_comp = false;
                                                                        
                                                                        </script>
                                                                </td>
                                                        </tr>
                                                        </table>
                                                     </div>
                                                                            
                                                </td>
                                </tr>
                                               
                                                                        
     <%-- BDM statistics end --%> 		   
	 
 <%-- customer contacts --%>
                                       
                                       
                                          <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Customer Contact Activities Summary By Rep</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('CustomerContactsDiv')" title="Minimize">
                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('CustomerContactsDiv')" title="Maximize">
                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    
                                                     <div id="CustomerContactsDiv" style="background-color:#ffffff">
                                                           <div id="overlayContactsActivites"></div>              
                        <div id="specialBoxContactsActivites" >
                          
                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                    <tr>                               
                                    <td colspan="2" style="background-color: #288AD1" >
                                        <h3 style="color:darkblue;" align="left">
                                            <span id="headerLabel" align="center"></span>

                                            <td colspan="2" style="background-color: #288AD1" align="right">
                                                <a href="javascript:getContactActivities('0');" >
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 
                                                </a>  
                                            </td>
                                        </h3>
                                    </td>
                                    </tr>

                                    <tr>                                                    
                                    <td colspan="5">

                                        <table cellpadding="0" cellspacing="0" width="100%">
                                            <tr>
                                            <td colspan="5">
                                                <table width="100%" cellpadding="1" cellspacing="1" border="0">
                                                    <tr>
                                                    <td colspan="5">
                                                        <div id="loadingMsgContactActivities" style="color:red; display: none;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>
                                                        <div id="resMessage"> </div>
                                                    </td>
                                                    </tr>
                                                    <tr>
                                                    <td colspan="5">
                                                        <%-- <DIV id="loadingMessage"> </DIV> --%>

                                                        <div style="overflow: auto;max-height:400px;max-width:1000px;"> 
                                                            <table id="ContactActivitiesTbl" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' align="center">
                                                                <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
                                                                <COLGROUP ALIGN="left" >
                                                                    <COL width="10">
                                                                        <COL width="20"> 
                                                                            <COL width="20">
                                                                                <COL width="25">
                                                                                    <COL width="20">
                                                                                        <COL width="20">

                                                                                            </table>
                                                                                            </div>
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
                                        
                                                         
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="80%" valign="top" align="center">
                                                                    <s:form action="" theme="simple" name="CustomerContacts" id="CustomerContacts">  

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                              <tr>
                                                                                <td>
                                                                                   <table border="0" align="center" cellpadding="0" cellspacing="0" >
                                                                                       <tr><td><table border="0" align="center" cellpadding="0" cellspacing="0" >
                                                                                               <tr>
                                                                                               <td>
                                                                                                   <br>
                                                                                               </td>
                                                                                               </tr>
                                                                                        <tr>
                                                                                          <td class="fieldLabel">Customer Name :<font color="red" style="font-weight: bold;"></font></td>
                                                                                          <td colspan="1">
                                                     
                                                                                               <s:textfield name="customerName" id="customerName" cssClass="inputTextBlueLargeAccount" style="width:130px;"  /> 
                                                                                         </td>
                                                            <td class="fieldLabel">Team Member:</td>
                                                            <td colspan="1">
                                                                                            <s:select list="teamMemberList" headerKey="All" style="float:left" headerValue="All" name="teamMemberId" id="teamMemberId" onchange="titleTypeCheck(this);showTeamCheck(this);" cssClass="inputSelect"/> 
                                                                                            <s:hidden name="titleType" id="titleType" cssClass="inputTextBlue" value=""/>
                                                                                                         <div id="displayTitleType"></div>
                                                                                            </td>
                                                                                            
                                                        
                                                                                   </tr> 
                                                                                          
                                                                                       <tr> 
                                                                                       <td class="fieldLabel">StartDate:</td> <!--value="%{dateWithOutTime}" -->


                                                                                                            <td><s:textfield name="startDateContacts" id="startDateContacts" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:cal3.popup();">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                            </td>
                                                                                                            <td class="fieldLabel">EndDate:</td>

                                                                                                            <td><s:textfield name="endDateContacts" id="endDateContacts" cssClass="inputTextBlue" onchange="checkDates(this);"/><a href="javascript:cal4.popup();">
                                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                                                            </td>
                                                                                   </tr>
                                                                                   </td>
                                                                                   <tr><td colspan="3" class="fieldLabel" align="right" style="padding: 20px"> <div id="checkDiv" style="display: none"><s:checkbox name="teamMemberCheck" id="teamMemberCheck" theme="simple" tabindex="11" />: Include Team Members&nbsp;</div></td> 
                                                                                       <td  align="center">
                                                                                                                    <input type="button"  class="buttonBg" onclick="getCustomerContacts()" value="Search"/>

                                                                                                                </td>

                                                                                        </tr>     
                                                                                   
                                                                                        
                                                                                          <tr>
                                                                                                <td height="20px" align="center" colspan="4">
                                                                                                    <div id="customerContactsloadMsg" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                </td>
                                                                                            </tr>
                                                                                         
                                                                                            <tr>
                                                                                                <td colspan="4">




                                                                                                    <table id="tblCustomerContacts" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                            <COL width="10%">
                                                                                                            <COL width="60%">
                                                                                                            <COL width="40%">
                                                                                                            

                                                                                                    </table>  



                                                                                                </td>
                                                                                            </tr>
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                       </table></td></tr>    
                                                                                           
                                                                                           
                                                                                          
                                                                                       
                                                                                         
                                                                                            
                                                                                          
                                                                                        
                                                                                            
                                                                                          
                                                                                            
                                                                                    </table>    
                                                                                </td>
                                                                            </tr> 
                                                                        </table>
                                                                    </s:form>
                                                                    <script type="text/JavaScript">
                                                                            var cal3 = new CalendarTime(document.forms['CustomerContacts'].elements['startDateContacts']);
                                                                            cal3.year_scroll = true;
                                                                            cal3.time_comp = false;
                                                                            var cal4 = new CalendarTime(document.forms['CustomerContacts'].elements['endDateContacts']);
                                                                            cal4.year_scroll = true;
                                                                            cal4.time_comp = false;
                                                                        
                                                                        </script>
                                                                </td>
                                                        </tr>
                                                        </table>
                                                     </div>
                                                                            
                                                </td>
                                </tr>
                                       
                                       
                                       
                                       
                                       <%-- customer contacts --%>
	 
                                        </table>

                                        <%--     </sx:div>
                                </sx:tabbedpanel> --%>
                                        <!--//END TABBED PANNEL : --> 
                                    </div>
                                </div>
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("reportsTab")
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
        <script>
 $(document).ready(function() {
     hideSelect();
     javascript:animatedcollapse.show('EmplyeeCountDiv');
      getEmpolyeeCount();
      });
</script>
    </body>

</html>

