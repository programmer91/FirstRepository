<%-- 
    Document   : MyOpportunitiesDashBoard
    Created on : Dec 3, 2015, 10:33:55 PM
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
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css?version=1.0"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/animatedcollapse.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-1.2.2.pack.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <%--    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/PreSalesDashBoard.js?version=1.2"/>"></script>  --%>
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/projectProtofolioReport.js?version=1.3"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
       
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
            
              #parent {
                height: auto;
                
                width:780px;
               max-height: 400px;
                overflow: visible;
            }

            #tblPresalesRequirementList {
               
                width: 750px !important;
            }
        </style>

    </head>
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
                                    <li><a href="#" rel="accountsListTab" class="selected">&nbsp;Project Protfolio Report</a></li>

                                </ul>
                              <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;"> 
                                    <div id="accountsListTab" class="tabcontent" >  
                                        <!-- Overlay start -->
                                                         <!-- Start Overlay -->

                                        

                                        
                                        <!-- Overlay end -->
                                        
                                        
                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                            <tr>
                                                <td height="20px" >

                                                </td>
                                            </tr>

                                            
                                            
                                           
                                                            <tr>
                                                                <td width="80%" valign="top" align="center">
                                                                    <s:form action="" theme="simple" id="requieremntListForm" name="requieremntListForm" >   

                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">

                                                                          <tr>
                                 <td class="fieldLabel">Customer&nbsp;Name :</td>
                               
                               <%-- <s:textfield name="customerName"  id="customerName" cssClass="inputTextBlue" theme="simple"/>
                                </td> --%>
                               <td><s:select headerKey="All" headerValue="All" list="clientMap" name="clientId" id="clientId" cssClass="inputSelect" theme="simple"/></td>
                                                                        <td class="fieldLabel">Start Date:</td>
                                                                        <td ><s:textfield name="postedDate1" id="postedDate1" cssClass="inputTextBlue" theme="simple" /><a class="underscore" href="javascript:cal5.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                    
                                                                        </tr>
                                                                        <tr>
                                                                            <td  class="fieldLabel">Project Status:</td>
                                                                                        <td><s:select list="{'Active','Completed','Terminated','Initiated'}"
                                                                                                          name="status"
                                                                                                          headerKey="" 
                                                                                                          headerValue="--select--"
                                                                                                          value="%{status}"
                                                                                                      cssClass="inputSelect" id="status"/></td>
                                                                            <td class="fieldLabel">Order By :</td>
                                                    <td>   
                                                        <%-- <s:select headerKey="All" headerValue="All" list="{'Open','InProgress','Closed','Lost'}" name="status" id="status" cssClass="inputSelect"/> --%>
                                                        <s:select headerKey="All" headerValue="All" list="{'CustomerName','ProjectName','CostModel','Sector','Practice','ProjectType','StartDate','EndDate','OverallState','Software','Description','Comments'}" name="orderBy" id="orderBy" cssClass="inputSelect" theme="simple" />
                                                    </td>
                                                                        </tr>
                                                                       
                               
                                
                                                 
                            <tr>
                            <td colspan="3"></td>
                            <td > <br><input type="button" value="Search" class="buttonBg" onclick="getProjectProtfolioReport();"/></td>
                            </tr>


                            <tr>
                            <td height="20px" align="center" colspan="4" >
                            <div id="loadReqMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                            </td>

                            </tr>
                            
                           <s:hidden name="pgNo" id="pgNo" />
                           <s:hidden name="totalRecords" id="totalRecords" />
                           

                          <tr>

                           <td colspan="4" >

                                                                                                                                                                                                                        
                                                                                <div id="parent">
                                                                                    <table id="tblProjectProtfolioReport" class="dashBoardGridTable" align="center">
                                                                                                                                                                                                          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-2.1.3.js"/>"></script>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tableHeadFixer.js"/>"></script>    
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                     
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                      <div id="button_pageNation"></div>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>


                                                                                                                                                                                                        </table>
                                                                    </s:form>
                                                                    <script type="text/JavaScript">
                                                                        var cal5 = new CalendarTime(document.forms['requieremntListForm'].elements['postedDate1']);
                                                                        cal5.year_scroll = true;
                                                                        cal5.time_comp = false;
                                                                        
                                                                    </script>
                                                   

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
                                                                                                                                                                                                       
                                                                                                                                                                                                          
                                                                                                                                                                                                        
                                                                                                                                                                                                            function pagerOption(){

                                                             
                                                                                                                                                                                                          var  recordPage=20;
                                                                                                                                                                                                  
                                                                                                                                                                                                                $('#tblPresalesRequirementList').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                                                            }
                                                                                                                                                                                                        </script>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DynamicPagination.js"/>"></script>

    </body>

</html>
