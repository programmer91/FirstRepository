<%-- 
    Document   : pmoDashBoard
    Created on : Dec 18, 2015, 2:46:51 PM
    Author     : miracle
--%>

<%@page import="java.util.Map"%>
<%-- 
    Document   : pmoDashBoard
    Created on : Dec 17, 2015, 8:50:01 PM
    Author     : miracle
--%>


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
        <title>Hubble Organization Portal :: PMO Dashboards</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css?ver=1.1"/>">
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/pmoDashBoardAjax.js?version=3.8"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/IssueFill.js"/>"></script>

        <link rel="stylesheet" type="text/css" href="<s:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css"/>">
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollajaxscript.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/payrollclientvalidations.js"/>"></script> --%>
        <s:include value="/includes/template/headerScript.html"/>

        <style> 
            #fontId {
                position: relative;
                -webkit-animation: mymove 5s infinite; /* Chrome, Safari, Opera */
                animation: mymove 1s infinite;
                font-size:49px;
            }

            /* Chrome, Safari, Opera */
            @-webkit-keyframes mymove {
                from {opacity:0.2;}
            to {opacity:1;}
            }

            @keyframes mymove {
                from {opacity:0.2;}
            to {opacity:1;}
            }
        </style>
        <script language="JavaScript">
                
           
            //            animatedcollapse.addDiv('LeavesCountExcelReportDiv', 'fade=1;persist=1;group=app');
            //            animatedcollapse.addDiv('GenerateTimeSeetReport', 'fade=1;persist=1;group=app');
            //            animatedcollapse.addDiv('GenerateHierarchyReport', 'fade=1;persist=1;group=app');
             
            animatedcollapse.addDiv('AvailableEmpList', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('ProjectList', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('CustomerProjectList', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('EmployeeMultipleProjectList', 'fade=1;persist=1;group=app');
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
            
            function checkResourceName()
            {
                var empId = document.getElementById("preAssignEmpId1").value=""; 
                if(empId=="" || empId==null){
                    //  alert("Select from suggestion list.");
                    document.getElementById("assignedToUID1").value = "";
                }
            }
            
          
            
            function showResourceTypeDiv(){
    
                var state=document.getElementById('state').value;
                if(state=='OnProject'){
                    document.getElementById('resTypeTr').style.display='';
                }else{
                    document.getElementById('resTypeTr').style.display='none';
                }
    
            }


            function checkResourceNameForMultipleProjects()
            {
                var empId = document.getElementById("preAssignEmpId2").value=""; 
                if(empId=="" || empId==null){
                    //  alert("Select from suggestion list.");
                    document.getElementById("assignedToUID2").value = "";
                }
            }	
        </script>

        <style type="text/css">

            .popupItem:hover {
                background: #F2F5A9;
                font: arial;
                font-size:10px;
                color: black;
            }

            .popupRow {
                background: #3E93D4;
            }

            .popupItem {
                padding: 2px;
                width: 100%;
                border: black;
                font:normal 9px verdana;
                color: white;
                text-decoration: none;
                line-height:13px;
                z-index:100;
            }
            #parent {
                height: auto;

                width:780px;
                max-height: 400px;
                overflow: visible;
            }

            #tblPmoProjectList {

                width: 750px !important;
            }
        </style>
        <style>
            div#overlayPmoDashBoard {
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
            div#specialBoxPmoDashBoard {
                display: none;
                top: 200px;

                position: absolute;
                z-index: 3;
                margin: 10px auto 0px auto;
                width: auto; 
                height: auto;
                background: #FFF;
                overflow: auto;
                overflow-y: auto;
                color: #000;
            }
        </style> 

    </head>

    <body  class="bodyGeneral" oncontextmenu="return false;" onload="showResourceTypeDiv();"> 
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
                        <li><a href="#" rel="EmpReportsTab" class="selected">PMO&nbsp;Dashboards</a></li>

                    </ul>
                    <div  style="border:1px solid gray; width:840px;height:675px;overflow:auto; margin-bottom: 1em;">    
                        <br>
                        <div id="EmpReportsTab" class="tabcontent" > 


                            <!-- Start Overlay -->                                      
                            <div id="overlayPmoDashBoard"></div>             

                            <div id="specialBoxPmoDashBoard">

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
                                            <table id="tblProjectEmployeeDetails"  class="gridTable" width="700" cellspacing="1" cellpadding="7" border="0" align="center" style="margin-left:37px;">
                                                <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script> --%>
                                                <COLGROUP ALIGN="left" >
                                                    <COL width="15%">
                                                        <COL width="15%">
                                                            </table> 

                                                            </td>

                                                            </tr>

                                                            </table>    

                                                            </div>
                                                            </div>	


                                                            <!-- End Overlay -->




                                                            <!-- Customer Overlay start -->       
                                                            <div id="overlayResourceType"></div>             

                                                            <div id="specialBoxResourceType">

                                                                <div id="resourceTypeDetails"  >
                                                                    <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                                        <tr>                               
                                                                        <td colspan="2" style="background-color: #288AD1" >
                                                                            <h3 style="color:darkblue;" align="left">
                                                                                <span id="headerLabel3"></span>


                                                                            </h3></td>
                                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                                            <a href="#" onmousedown="toggleCloseUploadOverlay2()" >
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
                                                                            <table id="tblResourceTypeDetails"  class="gridTable" width="400" cellspacing="1" cellpadding="7" border="0" align="center" style="margin-left:37px;">
                                                                                <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script> --%>
                                                                                <COLGROUP ALIGN="left" >



                                                                            </table> 
                                                                        </td>

                                                                        </tr>

                                                                    </table>    

                                                                </div>
                                                            </div>  

                                                            <div id="overlayProjectList"></div>             

                                                            <div id="specialBoxProjectList" style="width :670px;">

                                                                <div id="projectListDetails"  >
                                                                    <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                                        <tr>                               
                                                                        <td colspan="2" style="background-color: #288AD1" >
                                                                            <h3 style="color:darkblue;" align="left">
                                                                                <span id="headerLabel2"></span>


                                                                            </h3></td>
                                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                                            <a href="#" onmousedown="toggleCloseUploadOverlay3()" >
                                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                                            </a>  

                                                                        </td></tr>
                                                                        <tr>
                                                                        <td colspan="4">
                                                                            <div id="load2" style="color: green;display: none;">Loading..</div>
                                                                            <div id="resultMessage2"></div>
                                                                        </td>
                                                                        </tr>

                                                                        <tr>
                                                                        <td>
                                                                            <table id="tblProjectListDetails"  class="gridTable" width="600" cellspacing="1" cellpadding="7" border="0" align="center" style="margin-left:37px;">
                                                                                <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script> --%>
                                                                                <COLGROUP ALIGN="left" >
                                                                                    <COL width="15%">
                                                                                        <COL width="15%">
                                                                                            <COL width="15%">
                                                                                                <COL width="15%">
                                                                                                    </table> 
                                                                                                    </td>

                                                                                                    </tr>

                                                                                                    </table>    

                                                                                                    </div>
                                                                                                    </div>


                                                                                                    <!-- Customer Overlay end -->     



                                                                                                    <!--- MultipleProjectList overlay start   -->



                                                                                                    <div id="overlayMultipleProjectList"></div>             

                                                                                                    <div id="specialBoxMultipleProjectList" style="width :670px;">

                                                                                                        <div id="multipleProjectListDetails"  >
                                                                                                            <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                                                                                <tr>                               
                                                                                                                <td colspan="2" style="background-color: #288AD1" >
                                                                                                                    <h3 style="color:darkblue;" align="left">
                                                                                                                        <span id="headerLabel4"></span>


                                                                                                                    </h3></td>
                                                                                                                <td colspan="2" style="background-color: #288AD1" align="right">

                                                                                                                    <a href="#" onmousedown="toggleCloseUploadOverlayMultipleProjects()" >
                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                                                                                    </a>  

                                                                                                                </td></tr>
                                                                                                                <tr>
                                                                                                                <td colspan="4">
                                                                                                                    <div id="load3" style="color: green;display: none;">Loading..</div>
                                                                                                                    <div id="resultMessage3"></div>
                                                                                                                </td>
                                                                                                                </tr>

                                                                                                                <tr>
                                                                                                                <td>
                                                                                                                    <table id="tblMultipleProjectListDetails"  class="gridTable" width="600" cellspacing="1" cellpadding="7" border="0" align="center" style="margin-left:37px;">
                                                                                                                        <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script> --%>
                                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                                            <COL width="5%">
                                                                                                                                <COL width="25%">
                                                                                                                                    <COL width="20%">
                                                                                                                                        <COL width="10%">
                                                                                                                                            <COL width="10%">
                                                                                                                                                <COL width="10%">
                                                                                                                                                    <COL width="10%">
                                                                                                                                                        <COL width="10%">

                                                                                                                                                            </table> 
                                                                                                                                                            </td>

                                                                                                                                                            </tr>

                                                                                                                                                            </table>    

                                                                                                                                                            </div>
                                                                                                                                                            </div>


                                                                                                                                                            <!--- MultipleProjectList overlay end   --> 



                                                                                                                                                            <table cellpadding="0" cellspacing="0" border="0" width="100%">


                                                                                                                                                                <%-- Available Employees  List Report  --%>

                                                                                                                                                                <tr>
                                                                                                                                                                <td class="homePortlet" valign="top">
                                                                                                                                                                    <div class="portletTitleBar">
                                                                                                                                                                        <div class="portletTitleLeft">Available Employee List</div>
                                                                                                                                                                        <div class="portletIcons">
                                                                                                                                                                            <a href="javascript:animatedcollapse.hide('AvailableEmpList')" title="Minimize">
                                                                                                                                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                            <a href="javascript:animatedcollapse.show('AvailableEmpList')" title="Maximize">
                                                                                                                                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                            </a>
                                                                                                                                                                        </div>
                                                                                                                                                                        <div class="clear"></div>
                                                                                                                                                                    </div>
                                                                                                                                                                    <div id="AvailableEmpList" style="background-color:#ffffff">
                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                            <tr>
                                                                                                                                                                            <td width="40%" valign="top" align="center">
                                                                                                                                                                                <s:form theme="simple" name="availableEmpList" id="availableEmpList">  

                                                                                                                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                                                        <tr align="right">
                                                                                                                                                                                        <td class="headerText" colspan="9">
                                                                                                                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>

                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <%
                                                                                                                                                                                                if (session.getAttribute("resultMessageForUtility") != null) {
                                                                                                                                                                                                    out.println(session.getAttribute("resultMessageForUtility"));
                                                                                                                                                                                                    session.removeAttribute("resultMessageForUtility");
                                                                                                                                                                                                }

                                                                                                                                                                                            %>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>
                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <table border="0" align="center" cellpadding="0" cellspacing="0">


                                                                                                                                                                                                <%
                                                                                                                                                                                                    Map sesrolesMap0 = (Map) session.getAttribute("myRoles");
                                                                                                                                                                                                    if ((!sesrolesMap0.containsValue("Admin") && (!((String) session.getAttribute("sessionEmpPractice")).equals("PMO") && ((Integer) session.getAttribute("isUserManager")) == 1 && !session.getAttribute("roleName").toString().equals("Recruitment") && !session.getAttribute("roleName").toString().equals("Pre-Sales"))) && ((Integer) session.getAttribute("empPmoActivityAccess") != 1)) {

                                                                                                                                                                                                %>  
                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td class="fieldLabel" width="200px" align="right">Department :</td>

                                                                                                                                                                                                <td><s:textfield readonly="true"  name="departmentId" id="departmentId" cssClass="inputSelect"  value="%{#session.myDeptId}"/></td>
                                                                                                                                                                                                <td class="fieldLabel" width="200px" align="right">Practice Name :</td>

                                                                                                                                                                                                <td><s:textfield readonly="true" name="practiceId"  id="practiceId" cssClass="inputSelect" value="%{#session.sessionEmpPractice}"/></td>

                                                                                                                                                                                                </tr>

                                                                                                                                                                                                <%} else {%>
                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td class="fieldLabel" width="200px" align="right">Department :</td>

                                                                                                                                                                                                <td><s:select headerKey="-1" headerValue="--Please Select--" list="{'SSG','GDC'}" name="departmentId" id="departmentId" cssClass="inputSelect" onchange="getPracticeDataV2();"/></td>
                                                                                                                                                                                                <td class="fieldLabel" width="200px" align="right">Practice Name :</td>

                                                                                                                                                                                                <td><s:select name="practiceId"  id="practiceId" headerKey="-1" headerValue="--Please Select--" list="practiceIdList" cssClass="inputSelect"  onchange="getSubPracticeData3();"/></td>

                                                                                                                                                                                                </tr>
                                                                                                                                                                                                <%}%>
                                                                                                                                                                                                <tr>

                                                                                                                                                                                                <td class="fieldLabel" width="200px" align="right">Country :</td>

                                                                                                                                                                                                <td><s:select label="Select Countr" name="country" id="country" headerKey="-1" headerValue="--Please Select--" list="countryList" cssClass="inputSelect" /></td>        

                                                                                                                                                                                                <td class="fieldLabel" width="200px" align="right">Status :<FONT color="red"  ><em>*</em></FONT> </td>
                                                                                                                                                                                                <%
                                                                                                                                                                                                    Map sesrolesMap = (Map) session.getAttribute("myRoles");
                                                                                                                                                                                                    if ((!sesrolesMap0.containsValue("Admin") && (!((String) session.getAttribute("sessionEmpPractice")).equals("PMO") && ((Integer) session.getAttribute("isUserManager")) == 1 && !session.getAttribute("roleName").toString().equals("Recruitment") && !session.getAttribute("roleName").toString().equals("Pre-Sales"))) && ((Integer) session.getAttribute("empPmoActivityAccess") != 1)) {

                                                                                                                                                                                                %> 
                                                                                                                                                                                                <td><s:select name="state"  id="state" list="{'Available'}" cssClass="inputSelect" /></td>
                                                                                                                                                                                                <%} else {%>
                                                                                                                                                                                                <td><s:select name="state"  id="state" headerKey="-1" headerValue="--Please Select--" list="stateList" cssClass="inputSelect" onchange="showResourceTypeDiv()"/></td>
                                                                                                                                                                                                <%}%>
                                                                                                                                                                                                </tr>
                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td class="fieldLabel" width="200px" align="right">SubPractice&nbsp;Name&nbsp;:</td>
                                                                                                                                                                                                <td><s:select name="subPractice2"  id="subPractice2" headerKey="-1" headerValue="--Please Select--" list="subPracticeList" cssClass="inputSelect" /></td>
                                                                                                                                                                                                <td class="fieldLabel" width="200px" align="right">Sorted&nbsp;Based&nbspOn&nbsp;:</td>
                                                                                                                                                                                                <td><s:select name="sortedBy"  id="sortedBy" list="{'Practice','EmployeeName'}" cssClass="inputSelect" /></td>

                                                                                                                                                                                                </tr>
                                                                                                                                                                                               <tr id="resTypeTr" style="display:none;">
                                                                                                                                                                                                <td class="fieldLabel" width="200px" align="right">Resource&nbsp;Type:</td>
                                                                                                                                                                                                <td><s:select name="resourceType"  id="resourceType" headerKey="-1" headerValue="--Please Select--" list="{'Main(Billable)','Main','Shadow','Training','Overhead'}" cssClass="inputSelect" /></td>
                                                                                                                                                                                                </tr>
                                                                                                                                                                                                <tr>

                                                                                                                                                                                                <td colspan="3"></td>
                                                                                                                                                                                                <td width="200px" align="center">
                                                                                                                                                                                                    <input type="button" value="Search" class="buttonBg" onclick="getPMOAvailableList()"/>
                                                                                                                                                                                                </td> 
                                                                                                                                                                                                </tr>	
                                                                                                                                                                                                <tr>

                                                                                                                                                                                                </tr>
                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td class="fieldLabel" >Total&nbsp;Records&nbsp;:</td>
                                                                                                                                                                                                <td class="userInfoLeft" id="totalState1" ></td>   
                                                                                                                                                                                                </tr>
                                                                                                                                                                                            </table>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>

                                                                                                                                                                                        <%-- table grid --%>
                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <br>
                                                                                                                                                                                            <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td height="20px" align="center" colspan="9">
                                                                                                                                                                                                    <div id="pmoAvailableReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                </tr>


                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td ><br>
                                                                                                                                                                                                    <div id="pmoAvailableReport" style="display: block">
                                                                                                                                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                                                                        <table id="tblPmoReport" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>

                                                                                                                                                                                                        </table> <br>
                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                    </div>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                </tr>
                                                                                                                                                                                            </table>    
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>
                                                                                                                                                                                        <%-- table grid  end--%>
                                                                                                                                                                                    </table>
                                                                                                                                                                                </s:form>
                                                                                                                                                                            </td>
                                                                                                                                                                            </tr>
                                                                                                                                                                        </table>

                                                                                                                                                                    </div>
                                                                                                                                                                </td>
                                                                                                                                                                </tr>

                                                                                                                                                                <%-- Available Employees List End --%>  




                                                                                                                                                                <!-- Project  List Dashboard Start  -->

                                                                                                                                                                <%
                                                                                                                                                                    Map sesrolesMap1 = (Map) session.getAttribute("myRoles");
                                                                                                                                                                    String sessionEmpPractice = (String) session.getAttribute("sessionEmpPractice");
                                                                                                                                                                    if (sesrolesMap1.containsValue("Admin") || sessionEmpPractice.equals("PMO") || ((Integer) session.getAttribute("empPmoActivityAccess") == 1)) {
                                                                                                                                                                %>  
                                                                                                                                                                <tr>
                                                                                                                                                                <td class="homePortlet" valign="top">
                                                                                                                                                                    <div class="portletTitleBar">
                                                                                                                                                                        <div class="portletTitleLeft">Projects Overview</div>
                                                                                                                                                                        <div class="portletIcons">
                                                                                                                                                                            <a href="javascript:animatedcollapse.hide('ProjectList')" title="Minimize">
                                                                                                                                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                            <a href="javascript:animatedcollapse.show('ProjectList')" title="Maximize">
                                                                                                                                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                            </a>
                                                                                                                                                                        </div>
                                                                                                                                                                        <div class="clear"></div>
                                                                                                                                                                    </div>
                                                                                                                                                                    <div id="ProjectList" style="background-color:#ffffff">
                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                            <tr>
                                                                                                                                                                            <td width="40%" valign="top" align="center">
                                                                                                                                                                                <s:form theme="simple" name="projectList" id="projectList">  

                                                                                                                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                                                        <tr align="right">
                                                                                                                                                                                        <td class="headerText" colspan="9">
                                                                                                                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>

                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <%
                                                                                                                                                                                                if (session.getAttribute("resultMessageForUtility") != null) {
                                                                                                                                                                                                    out.println(session.getAttribute("resultMessageForUtility"));
                                                                                                                                                                                                    session.removeAttribute("resultMessageForUtility");
                                                                                                                                                                                                }

                                                                                                                                                                                            %>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>
                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <table border="0" align="center" cellpadding="0" cellspacing="0">


                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td class="fieldLabel" >Customer Name :</td>
                                                                                                                                                                                                <td>

                                                                                                                                                                                                    <s:select headerKey="" headerValue="All" list="clientMap" name="customerName" id="customerName" cssClass="inputSelect" theme="simple" onchange="getProjectsByAccountId();"/>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                <td class="fieldLabel">ProjectName :</td>
                                                                                                                                                                                                <td>

                                                                                                                                                                                                    <s:select headerKey="" headerValue="All"  list="{}" name="projectName" id="projectName" cssClass="inputSelect" theme="simple"/>
                                                                                                                                                                                                </td>

                                                                                                                                                                                                <td  class="fieldLabel">Project Status&nbsp;:</td>
                                                                                                                                                                                                <td><s:select list="{'Active','Completed','Terminated','Initiated'}"
                                                                                                                                                                                                        name="status"
                                                                                                                                                                                                        headerKey="" 
                                                                                                                                                                                                        headerValue="--Please Select--"
                                                                                                                                                                                                        value="%{status}"
                                                                                                                                                                                                        cssClass="inputSelect" id="status"/></td>
                                                                                                                                                                                                </tr>
                                                                                                                                                                                                <tr>

                                                                                                                                                                                                <td class="fieldLabel">ProjectStartDate :</td>
                                                                                                                                                                                                <td><s:textfield name="projectStartDate" id="projectStartDate" cssClass="inputTextBlue" theme="simple" /><a href="javascript:cal1.popup();">

                                                                                                                                                                                                        <a href="javascript:cal4.popup();">
                                                                                                                                                                                                        <img style="margin-bottom:-6px;margin-left:-4px" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                                                        width="20" height="20" border="0" ></a>
                                                                                                                                                                                                </td>

                                                                                                                                                                                                <td class="fieldLabel" >Practice :</td>
                                                                                                                                                                                                <td >
                                                                                                                                                                                                    <s:select list="practiceList" name="practiceId1" id="practiceId1" value="%{practiceId}" cssClass="inputSelect"  headerKey="" headerValue="--Please Select--"/>
                                                                                                                                                                                                </td>





                                                                                                                                                                                                <td class="fieldLabel">Resource&nbsp;Name&nbsp;:</td>
                                                                                                                                                                                                <td ><s:textfield name="assignedToUID" id="assignedToUID" value="%{assignedToUID}"   onchange="checkName();"  onkeyup="EmployeeForProjectDetails();"  cssClass="inputTextBlue"  theme="simple" readonly="false"/>
                                                                                                                                                                                                    <div id="authorEmpValidationMessage" style="position: absolute; overflow:hidden;"></div>  
                                                                                                                                                                                                    <s:hidden name="preAssignEmpId" value="%{preAssignEmpId}" id="preAssignEmpId"/>  
                                                                                                                                                                                                </td> 

                                                                                                                                                                                                </tr>
                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td  class="fieldLabel">Employee Status&nbsp;:</td>
                                                                                                                                                                                                <td><s:select list="{'Active','InActive'}"
                                                                                                                                                                                                        name="empStatus"
                                                                                                                                                                                                        id="empStatus"
                                                                                                                                                                                                        headerKey="-1" 
                                                                                                                                                                                                        headerValue="--Please Select--"
                                                                                                                                                                                                        value="%{empStatus}"
                                                                                                                                                                                                        cssClass="inputSelect" /></td>
                                                                                                                                                                                                    <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.empPmoActivityAccess==1 || #session.isAdminAccess==1" >
                                                                                                                                                                                                    <td  class="fieldLabel">PMO&nbsp;:</td>
                                                                                                                                                                                                    <td><s:select list="myPMOTeamMembers"
                                                                                                                                                                                                        name="pmoLoginId"
                                                                                                                                                                                                        headerKey="" 
                                                                                                                                                                                                        headerValue="--Please Select--"

                                                                                                                                                                                                        cssClass="inputSelect" id="pmoLoginId"/></td>
                                                                                                                                                                                                    </s:if><s:else>
                                                                                                                                                                                                    <td colspan="2">
                                                                                                                                                                                                        <s:hidden name="pmoLoginId" id="pmoLoginId" value=""/>
                                                                                                                                                                                                    </td>
                                                                                                                                                                                                </s:else>

                                                                                                                                                                                                <td></td>
                                                                                                                                                                                                <td>
                                                                                                                                                                                                    <input type="button" value="Search" class="buttonBg" onclick="geProjectListDetails();" style="margin-left: 76px;"/>
                                                                                                                                                                                                </td> 
                                                                                                                                                                                                </tr>
                                                                                                                                                                                            </table>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>

                                                                                                                                                                                        <%-- table grid --%>
                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <br>
                                                                                                                                                                                            <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td height="20px" align="center" colspan="9">
                                                                                                                                                                                                    <div id="pmoProjectDetailsList" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                </tr>


                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td ><br>
                                                                                                                                                                                                    <div id="pmoProjectDetailsReport" style="display: block">
                                                                                                                                                                                                        <div id="parent">
                                                                                                                                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                                                                        <table id="tblPmoProjectList" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-2.1.3.js"/>"></script>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tableHeadFixer.js"/>"></script>    

                                                                                                                                                                                                        </table> 
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div id="button_pageNation1"></div>
                                                                                                                                                                                                        <br>
                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                    </div>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                </tr>
                                                                                                                                                                                            </table>    
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>
                                                                                                                                                                                        <%-- table grid  end--%>
                                                                                                                                                                                    </table>
                                                                                                                                                                                </s:form>
                                                                                                                                                                            </td>
                                                                                                                                                                            </tr>
                                                                                                                                                                        </table>

                                                                                                                                                                    </div>
                                                                                                                                                                    <script type="text/JavaScript">
                                          
                                                                                                                                                                        var cal4 = new CalendarTime(document.forms['projectList'].elements['projectStartDate']);
                                                                                                                                                                        cal4.year_scroll = true;
                                                                                                                                                                        cal4.time_comp = false;
                                            
                                                                                                                                                                    </script>
                                                                                                                                                                </td>
                                                                                                                                                                </tr>
                                                                                                                                                                <%}%>
                                                                                                                                                                <%-- project list dashboard ENd --%>  




                                                                                                                                                                <%-- Customer Project Details start --%>

                                                                                                                                                                <%
                                                                                                                                                                    Map sesrolesMap2 = (Map) session.getAttribute("myRoles");
                                                                                                                                                                    String sessionEmpPractice2 = (String) session.getAttribute("sessionEmpPractice");
                                                                                                                                                                    if (sesrolesMap2.containsValue("Admin") || sessionEmpPractice2.equals("PMO") || ((Integer) session.getAttribute("empPmoActivityAccess") == 1)) {
                                                                                                                                                                %>  
                                                                                                                                                                <tr>
                                                                                                                                                                <td class="homePortlet" valign="top">
                                                                                                                                                                    <div class="portletTitleBar">
                                                                                                                                                                        <div class="portletTitleLeft">Resource&nbsp;Count&nbsp;By&nbsp;Customer</div>
                                                                                                                                                                        <div class="portletIcons">
                                                                                                                                                                            <a href="javascript:animatedcollapse.hide('CustomerProjectList')" title="Minimize">
                                                                                                                                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                            <a href="javascript:animatedcollapse.show('CustomerProjectList')" title="Maximize">
                                                                                                                                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                            </a>
                                                                                                                                                                        </div>
                                                                                                                                                                        <div class="clear"></div>
                                                                                                                                                                    </div>
                                                                                                                                                                    <div id="CustomerProjectList" style="background-color:#ffffff">
                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                            <tr>
                                                                                                                                                                            <td width="100%" valign="top" align="center">
                                                                                                                                                                                <s:form theme="simple" name="frmSearch" id="frmSearch">  

                                                                                                                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                                                        <tr align="right">
                                                                                                                                                                                        <td class="headerText" colspan="9">
                                                                                                                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>

                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <%
                                                                                                                                                                                                if (session.getAttribute("resultMessageForUtility") != null) {
                                                                                                                                                                                                    out.println(session.getAttribute("resultMessageForUtility"));
                                                                                                                                                                                                    session.removeAttribute("resultMessageForUtility");
                                                                                                                                                                                                }

                                                                                                                                                                                            %>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>
                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <table border="0" align="center" cellpadding="0" cellspacing="0">


                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td class="fieldLabel" >Customer Name :</td>
                                                                                                                                                                                                <td colspan="2">
                                                                                                                                                                                                    <s:select headerKey="" headerValue="All" list="clientMap" name="customerName" id="customerName1" cssClass="inputSelect" theme="simple" onchange="getProjectsByAccountIdForResourceCount();"/>
                                                                                                                                                                                                </td>

                                                                                                                                                                                                <td class="fieldLabel">ProjectName :</td>
                                                                                                                                                                                                <td>

                                                                                                                                                                                                    <s:select headerKey="" headerValue="All"  list="{}" name="projectName" id="projectName1" cssClass="inputSelect" theme="simple"/>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                </tr>
                                                                                                                                                                                                <tr>

                                                                                                                                                                                                <td  class="fieldLabel">Project Status :</td>
                                                                                                                                                                                                <td colspan="2"><s:select list="{'Active','Completed','Terminated','Initiated'}"
                                                                                                                                                                                                        name="status"
                                                                                                                                                                                                        headerKey="" 
                                                                                                                                                                                                        headerValue="--Please Select--"
                                                                                                                                                                                                        value="%{status}"
                                                                                                                                                                                                        cssClass="inputSelect" id="status1"/></td>

                                                                                                                                                                                                <td class="fieldLabel">ProjectStartDate :</td>
                                                                                                                                                                                                <td><s:textfield name="projectStartDate" id="projectStartDate1" cssClass="inputTextBlue" theme="simple" /><a href="javascript:cal1.popup();">

                                                                                                                                                                                                        <a href="javascript:cal5.popup();">
                                                                                                                                                                                                        <img style="margin-bottom:-6px;margin-left:-4px" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                                                        width="20" height="20" border="0" ></a>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                </tr>
                                                                                                                                                                                                <tr>

                                                                                                                                                                                                <td class="fieldLabel" >Practice :</td>
                                                                                                                                                                                                <td colspan="2">
                                                                                                                                                                                                    <s:select list="practiceList" name="practiceId1" id="practiceId2" value="%{practiceId}" cssClass="inputSelect"  headerKey="" headerValue="--Please Select--"/>
                                                                                                                                                                                                </td>


                                                                                                                                                                                                <td class="fieldLabel">Resource&nbsp;Name&nbsp;:</td>
                                                                                                                                                                                                <td ><s:textfield name="assignedToUID" id="assignedToUID1" value="%{assignedToUID}"   onchange="checkName1();"  onkeyup="EmployeeForProjectDetails1();"  cssClass="inputTextBlue"  theme="simple" readonly="false"/>
                                                                                                                                                                                                    <div id="authorEmpValidationMessage1" style="position: absolute; overflow:hidden;"></div>  
                                                                                                                                                                                                    <s:hidden name="preAssignEmpId" value="%{preAssignEmpId}" id="preAssignEmpId1"/>  
                                                                                                                                                                                                </td>  

                                                                                                                                                                                                </tr>
                                                                                                                                                                                                <tr>


                                                                                                                                                                                                    <s:if test="#session.isUserManager == 1 || #session.isUserTeamLead==1 || #session.empPmoActivityAccess==1">
                                                                                                                                                                                                    <td  class="fieldLabel" >PMO&nbsp;:</td>
                                                                                                                                                                                                    <td colspan="3"> <s:select list="myPMOTeamMembers"
                                                                                                                                                                                                        name="pmoLoginId"
                                                                                                                                                                                                        headerKey="" 
                                                                                                                                                                                                        headerValue="--Please Select--"

                                                                                                                                                                                                        cssClass="inputSelect" id="pmoLoginId1"/></td>
                                                                                                                                                                                                    </s:if><s:else>
                                                                                                                                                                                                    <td colspan="2">
                                                                                                                                                                                                        <s:hidden name="pmoLoginId" id="pmoLoginId1" value=""/>
                                                                                                                                                                                                    </td>
                                                                                                                                                                                                </s:else>



                                                                                                                                                                                                <td>
                                                                                                                                                                                                    <input type="button" value="Search" class="buttonBg" onclick="geCustomerProjectListDetails();" style="margin-left: 76px;"/>
                                                                                                                                                                                                </td> 
                                                                                                                                                                                                </tr>
                                                                                                                                                                                            </table>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>

                                                                                                                                                                                        <%-- table grid --%>
                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <br>
                                                                                                                                                                                            <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td height="20px" align="center" colspan="9">
                                                                                                                                                                                                    <div id="pmoCutomerProjectDetailsList" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                </tr>


                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td ><br>
                                                                                                                                                                                                    <div id="pmoCutomerProjectDetailsReport" style="display: block">
                                                                                                                                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                                                                        <table id="tblPmoCutomerProjectList" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>


                                                                                                                                                                                                        </table> <br>
                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                    </div>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                </tr>
                                                                                                                                                                                            </table>    
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>
                                                                                                                                                                                        <%-- table grid  end--%>
                                                                                                                                                                                    </table>
                                                                                                                                                                                </s:form>
                                                                                                                                                                            </td>
                                                                                                                                                                            </tr>
                                                                                                                                                                        </table>

                                                                                                                                                                    </div>
                                                                                                                                                                    <script type="text/JavaScript">
                                          
                                                                                                                                                                        var cal5 = new CalendarTime(document.forms['frmSearch'].elements['projectStartDate1']);
                                                                                                                                                                        cal5.year_scroll = true;
                                                                                                                                                                        cal5.time_comp = false;
                                            
                                                                                                                                                                    </script>
                                                                                                                                                                </td>
                                                                                                                                                                </tr>
                                                                                                                                                                <%}%>
                                                                                                                                                                <%-- customer project details end--%>		 


                                                                                                                                                                <%-- employee multiple Project Details start --%>

                                                                                                                                                                <%
                                                                                                                                                                    if (sesrolesMap2.containsValue("Admin") || sessionEmpPractice2.equals("PMO") || ((Integer) session.getAttribute("empPmoActivityAccess") == 1)) {
                                                                                                                                                                %>  
                                                                                                                                                                <tr>
                                                                                                                                                                <td class="homePortlet" valign="top">
                                                                                                                                                                    <div class="portletTitleBar">
                                                                                                                                                                        <div class="portletTitleLeft">Multiple&nbsp;Projects&nbsp;Employee&nbsp;List</div>
                                                                                                                                                                        <div class="portletIcons">
                                                                                                                                                                            <a href="javascript:animatedcollapse.hide('EmployeeMultipleProjectList')" title="Minimize">
                                                                                                                                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                            <a href="javascript:animatedcollapse.show('EmployeeMultipleProjectList')" title="Maximize">
                                                                                                                                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                            </a>
                                                                                                                                                                        </div>
                                                                                                                                                                        <div class="clear"></div>
                                                                                                                                                                    </div>
                                                                                                                                                                    <div id="EmployeeMultipleProjectList" style="background-color:#ffffff">
                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                                                                                                                                            <tr>
                                                                                                                                                                            <td width="100%" valign="top" align="center">
                                                                                                                                                                                <s:form theme="simple" name="empSearch" id="empSearch">  

                                                                                                                                                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                                                        <tr align="right">
                                                                                                                                                                                        <td class="headerText" colspan="9">
                                                                                                                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                                        
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>

                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <%
                                                                                                                                                                                                if (session.getAttribute("resultMessageForUtility") != null) {
                                                                                                                                                                                                    out.println(session.getAttribute("resultMessageForUtility"));
                                                                                                                                                                                                    session.removeAttribute("resultMessageForUtility");
                                                                                                                                                                                                }

                                                                                                                                                                                            %>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>
                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                          <table border="0" align="center" cellpadding="0" cellspacing="0">




                                                                                                                                                                                                <tr>


                                                                                                                                                                                                <td class="fieldLabel">Resource&nbsp;Name&nbsp;:</td>
                                                                                                                                                                                                <td ><s:textfield name="assignedToUID" id="assignedToUID2" value="%{assignedToUID}"   onchange="checkResourceNameForMultipleProjects();"  onkeyup="EmployeeMultipleProjectDetails();"  cssClass="inputTextBlue"  theme="simple" readonly="false"/>
                                                                                                                                                                                                    <div id="authorEmpValidationMessage2" style="position: absolute; overflow:hidden;"></div>  
                                                                                                                                                                                                    <s:hidden name="preAssignEmpId" value="%{preAssignEmpId}" id="preAssignEmpId2"/>  
                                                                                                                                                                                                </td> 
                                                                                                                                                                                                
                                                                                                                                                                                                
                                                                                                                                                                                                 <td class="fieldLabel" width="200px" align="right">Department :</td>

                                                                                                                                                                                                <td><s:select headerKey="-1" headerValue="--Please Select--" list="{'SSG','GDC'}" name="departmentIdEmpPrj" id="departmentIdEmpPrj" cssClass="inputSelect" onchange="getPracticeDataForMultipleProjects();"/></td>
      
                                                                                                                                                                                                </tr>
                                                                                                                                                                                                
                                                                                                                                                                                                <tr>
                                                                                                                                                                                                
                                                                                                                                                                                                 <td class="fieldLabel" width="200px" align="right">Practice Name :</td>

                                                                                                                                                                                                <td><s:select name="practiceIdEmpPrj"  id="practiceIdEmpPrj" headerKey="-1" headerValue="--Please Select--" list="practiceIdList" cssClass="inputSelect"  onchange="getSubPracticeDataForMultipleProjects();"/></td>
                                                                                                                                                                                               
                                                                                                                                                                                                    
                                                                                                                                                                                                    <td class="fieldLabel" width="200px" align="right">SubPractice&nbsp;Name&nbsp;:</td>
                                                                                                                                                                                                <td><s:select name="subPracticeEmpPrj"  id="subPracticeEmpPrj" headerKey="-1" headerValue="--Please Select--" list="subPracticeList" cssClass="inputSelect" /></td>
                                                                                                                                                                                               </tr>
                                                                                                                                                                                               
                                                                                                                                                                                               <tr>
                                                                                                                                                                                               <td colspan="3"></td>
                                                                                                                                                                                                    <td>
                                                                                                                                                                                                    <input type="button" value="Search" class="buttonBg" onclick="getMultipleProjectsEmployeeList();" style="margin-left: 76px;"/>
                                                                                                                                                                                                </td> 
                                                                                                                                                                                                
                                                                                                                                                                                               </tr>

                                                                                                                                                                                            </table>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>

                                                                                                                                                                                        <%-- table grid --%>
                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <br>
                                                                                                                                                                                            <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td height="20px" align="center" colspan="9">
                                                                                                                                                                                                    <div id="multipleProjectEmployeeDetailsLoad" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                </tr>


                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td ><br>
                                                                                                                                                                                                    <div id="multipleProjectEmployeeDetailsReport" style="display: block">
                                                                                                                                                                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                                                                                        <table id="tblMultipleProjectEmployeeList" align='center' i cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>


                                                                                                                                                                                                        </table> <br>
                                                                                                                                                                                                        <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                                                                                                                    </div>
                                                                                                                                                                                                </td>
                                                                                                                                                                                                </tr>
                                                                                                                                                                                            </table>    
                                                                                                                                                                                        </td>
                                                                                                                                                                                        </tr>
                                                                                                                                                                                        <%-- table grid  end--%>
                                                                                                                                                                                    </table>
                                                                                                                                                                                </s:form>
                                                                                                                                                                            </td>
                                                                                                                                                                            </tr>
                                                                                                                                                                        </table>

                                                                                                                                                                    </div>

                                                                                                                                                                </td>
                                                                                                                                                                </tr>
                                                                                                                                                                <%}%>
                                                                                                                                                                <%-- employee multiple project details end--%>	





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
                                                                                                                                                                                                       
                                                                                                                                                                function pagerOption(){

                                                                                                                                                                    // var paginationSize = document.getElementById("paginationOption").value;
                                                                                                                                                                                                      
                                                                                                                                                                    var  recordPage=15;
                                                                                                                                                                                                  
                                                                                                                                                                    $('#tblPmoReport').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                }
                                                                                                                                                                function pagerOption1(){

                                                                                                                                                                    // var paginationSize = document.getElementById("paginationOption").value;
                                                                                                                                                                                                      
                                                                                                                                                                    var  recordPage=15;
                                                                                                                                                                    $('#tblPmoProjectList').tablePaginate({navigateType:'navigator'},recordPage,tblPmoProjectList);
                                                                                                                                                                    // $('#tblPmoProjectList').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                }	
                                                                                                                                                                function pagerOption2(){

                                                                                                                                                                    // var paginationSize = document.getElementById("paginationOption").value;
                                                                                                                                                                                                      
                                                                                                                                                                    var  recordPage=15;
                                                                                                                                                                                                  
                                                                                                                                                                    $('#tblPmoCutomerProjectList').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                }
                                                                                                                                                                                                        
                                                                                                            
                                                                                                                                                                function pagerOption3(){

                                                                                                                                                                    // var paginationSize = document.getElementById("paginationOption").value;
                                                                                                                                                                                                      
                                                                                                                                                                    var  recordPage=15;
                                                                                                                                                                                                  
                                                                                                                                                                    $('#tblMultipleProjectEmployeeList').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                } 	                                                                                               
                                                                                                                                                            </script>
                                                                                                                                                            <script type="text/JavaScript" src="<s:url value="/includes/javascripts/paginationAll.js?ver=1.0"/>"></script>

                                                                                                                                                            <%
                                                                                                                                                                Map sesrolesMap01 = (Map) session.getAttribute("myRoles");
                                                                                                                                                                if ((!sesrolesMap01.containsValue("Admin") && (!((String) session.getAttribute("sessionEmpPractice")).equals("PMO") && ((Integer) session.getAttribute("isUserManager")) == 1)) && ((Integer) session.getAttribute("empPmoActivityAccess") != 1)) {

                                                                                                                                                            %>  
                                                                                                                                                            <script type="text/javascript">
                                                                                                                                                                $(window).load(function(){
                                                                                                                                                                    getSubPracticeData3();
                                                                                                                                                                    hideSelect();
                                                                                                                                                                    animatedcollapse.show('SalesClosuresExcelReportDiv');
                                                                                                                                                                    defaultDates();

                                                                                                                                                                });
                                                                                                                                                            </script>


                                                                                                                                                            <%} else {%>
                                                                                                                                                            <script type="text/javascript">
                                                                                                                                                                $(window).load(function(){
                                                                                                                                                                    hideSelect();
                                                                                                                                                                    animatedcollapse.show('SalesClosuresExcelReportDiv');
                                                                                                                                                                    defaultDates();

                                                                                                                                                                });
                                                                                                                                                            </script>

                                                                                                                                                            <%}%>


                                                                                                                                                            </body>

                                                                                                                                                            </html>

