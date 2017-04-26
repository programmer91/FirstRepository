<%-- 
   Document   : TasksDashBoard
   Created on : Feb 15, 2017, 10:49:49 PM
   Author     : Phanindra Kanuri
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
        <title>Hubble Organization Portal :: Tasks DashBoard</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css?version=2.1"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/animatedcollapse.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-1.2.2.pack.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ReusableContainer.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js?version=1.0"/>"></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script> 
        <s:include value="/includes/template/headerScript.html"/>
        <script language="JavaScript">
            google.load("visualization", "1", {packages:["corechart"]});   
            animatedcollapse.init();
        </script>
         <script language="JavaScript">
                
            animatedcollapse.addDiv('TasksDashBoardDiv', 'fade=1;speed=400;persist=1;group=app');
           

           
            animatedcollapse.init();
            function hideSelect(){
                //document.getElementById("priorityId").style.display = 'none';
                
            }
        </script>
        <style type="text/css">
            .rcorners2 {
                border-radius: 25px;
                border: 2px solid #3e93d4;
                padding: 20px; 
                width: auto;
                height: auto;    
            }   
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

        </style>
    </head>
   <!--  <body  class="bodyGeneral" onload="javascript:animatedcollapse.show('TasksDashBoardDiv');" oncontextmenu="return false;"> -->
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
                        <li><a href="#" rel="TasksDashBoardTab" class="selected">Tasks&nbsp;DashBoard</a></li>

                    </ul>
                    <div  style="border:1px solid gray; width:840px;height:675px;overflow:auto; margin-bottom: 1em;">    
                        <br>
                        <div id="TasksDashBoardTab" class="tabcontent" >  

                            <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                <%-- Tasks DashBoard start --%>

                                <tr>
                                <td class="homePortlet" valign="top">
                                    <div class="portletTitleBar">
                                        <div class="portletTitleLeft">Tasks</div>
                                        <div class="portletIcons">
                                            <a href="javascript:animatedcollapse.hide('TasksDashBoardDiv')" title="Minimize">
                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                            <a href="javascript:animatedcollapse.show('TasksDashBoardDiv')" title="Maximize">
                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                            </a>
                                        </div>
                                        <div class="clear"></div>
                                    </div>

                                    <div id="TasksDashBoardDiv" style="background-color:#ffffff">
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                            <tr>
                                            <td width="80%" valign="top" align="center">
                                                <s:form action="" theme="simple" name="TasksDashBoard" id="TasksDashBoard">  

                                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                        <tr>
                                                        <td>
                                                            <table border="0" align="center" cellpadding="0" cellspacing="0" width="800;">
                                                                <br>
                                                                <tr>
                                                                <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                <td><s:textfield name="taskStartDate" id="taskStartDate" cssClass="inputTextBlueSmall" onchange="isValidDate(this)"/><a class="underscore" href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>

                                                                <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                <td>  <s:textfield name="taskEndDate" id="taskEndDate" cssClass="inputTextBlueSmall" onchange="isValidDate(this)"/><a class="underscore" href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td>
                                                                </tr>

                                                                <tr>
                                                                <td  class="fieldLabel" width="200px" align="right">Reports&nbsp;To :</td>
                                                                <td><s:select cssClass="inputSelect" list="reportsToHierarchyMap" name="reportsTo" id="reportsTo" headerValue="--Please Select--" headerKey="-1" /></td>
                                                               <td></td><td align="">
                                                              
                                                                    <input style="margin-left: 14px;" type="button" value="Search" class="buttonBg" onclick="getIssuesDashBoardByStatus();"/>
                                                                </td>

                                                                </tr>

                                                            </table>
                                                        </td>
                                                        </tr>
                                                        <tr>
                                                        <td>
                                                            <table border="0" align="center" cellpadding="0" cellspacing="0" >

                                                                <tr>
                                                                <td>

                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                        <tr>
                                                                        <td height="20px" align="center" colspan="9">
                                                                            <div id="projectReport" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                        </td>
                                                                        </tr>

                                                                        <tr>
                                                                        <td>
                                                                            <div class="rcorners2" id="IssuesDashBoardByStatusPie" style="display:none;width: 355px;margin-left:10px;">
                                                                                <div id="IssuesDashBoardByStatusPieChart" style="width: 350px; height: 250px;"></div>
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <div class="rcorners2" id="IssuesDashBoardByPriorityPie" style="display:none;width: 355px;">
                                                                                <div id="IssuesDashBoardByPriorityPieChart" style="width: 350px; height: 250px;"></div>
                                                                            </div>
                                                                        </td>
                                                                        </tr>



                                                                        <tr>
                                                                        <td>
                                                                            <div class="rcorners2" id="IssuesDashBoardByAssignmentPie" style="display:none;width: 355px;margin-left:10px;">
                                                                                <div id="IssuesDashBoardByAssignmentPieChart" style="width: 350px; height: 250px;"></div>
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <div class="rcorners2" id="IssuesDashBoardByCategoryPie" style="display:none;width: 355px;">
                                                                                <div id="IssuesDashBoardByCategoryPieChart" style="width: 350px; height: 250px;"></div>
                                                                            </div>
                                                                        </td>
                                                                        </tr>
                                                                    </table>    
                                                                </td>
                                                                </tr>


                                                                <!-- new graph details start -->
                                                                <tr>
                                                                <td height="20px" align="center" colspan="9">
                                                                    <div id="projectReporssst" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                </td>
                                                                </tr>
                                                                <tr>
                                                                <td colspan="4">
                                                                    <table id="tblTasksDashBoard" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='800'>
                                                                        <COLGROUP ALIGN="left" >
                                                                            <COL width="7%">
                                                                                <COL width="25%">
                                                                                    <COL width="7%">
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
                                                                                                                var cal1 = new CalendarTime(document.forms['TasksDashBoard'].elements['taskStartDate']);
                                                                                                                cal1.year_scroll = true;
                                                                                                                cal1.time_comp = false;
                                                                                                                var cal2 = new CalendarTime(document.forms['TasksDashBoard'].elements['taskEndDate']);
                                                                                                                cal2.year_scroll = true;
                                                                                                                cal2.time_comp = false;
                                                                        
                                                                                                            </script>
                                                                                                            </td>
                                                                                                            </tr>
                                                                                                            </table>

                                                                                                            </div>

                                                                                                            </td>
                                                                                                            </tr>


                                                                                                            <%-- Tasks DashBoard end --%> 		   



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
                                                                                                            <script type="text/javascript">
                                                                                                                var recordPage=10;
                                                                                                                function pagerOption(){

                                                                                                                    var paginationSize =10;
                                                                                                                    if(isNaN(paginationSize))
                                                                                                                    {
                       
                                                                                                                    }
                                                                                                                    recordPage=paginationSize;
                                                                                                                    // alert(recordPage)
                                                                                                                    $('#tblTasksDashBoard').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                };
                                                                                                              
                                                                                                                                                                                                      
                                                                                                                                                                                                      
                                                                                                            </script>
                                                                                                            <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajaxPaginationForTaskDashboard.js"/>"></script>

                                                                                                            <!--//END MAIN TABLE : Table for template Structure-->
    <script type="text/javascript">
		$(window).load(function(){
		
		animatedcollapse.show('TasksDashBoardDiv');

		});
		</script>                                                                                                        
    </body>

                                                                                                            </html>