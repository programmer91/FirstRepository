<%-- 
    Document   : BDMDashBoard
    Created on : Oct 26, 2015, 11:59:51 PM
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GreensheetListSearch.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/GreensheetListResultSearch.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ReusableContainer.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script> 
        <s:include value="/includes/template/headerScript.html"/>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/crm/BDMDashBoard.js"/>"></script>

        <script language="JavaScript">
            google.load("visualization", "1", {packages:["corechart"]});
            animatedcollapse.addDiv('bdmPiechart', 'fade=1;speed=400;persist=1;group=app');
            animatedcollapse.addDiv('opportunityDiv', 'fade=1;speed=400;persist=1;group=app');
            animatedcollapse.init();
      
        </script>
        <style>
            .rcorners2 {
                border-radius: 25px;
                border: 2px solid #8AC007;
                padding: 20px; 
                width: auto;
                height: auto;    
            }
            .btn {
                background: #3498db;
                background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
                background-image: -moz-linear-gradient(top, #3498db, #2980b9);
                background-image: -ms-linear-gradient(top, #3498db, #2980b9);
                background-image: -o-linear-gradient(top, #3498db, #2980b9);
                background-image: linear-gradient(to bottom, #3498db, #2980b9);
                -webkit-border-radius: 9;
                -moz-border-radius: 9;
                border-radius: 9px;
                font-family: Arial;
                color: #ffffff;
                font-size: 12px;
                padding: 6px 20px 6px 20px;
                text-decoration: none;
            }

            .btn:hover {
                background: #3cb0fd;
                background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
                background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
                background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
                background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
                background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
                text-decoration: none;
            }
        </style>
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

        </style>


        <!-- <script type="text/javascript" src="https://www.google.com/jsapi"></script>-->


    </head>
  <!--  <body  class="bodyGeneral" onload="init();" oncontextmenu="return false;"> -->
    <body  class="bodyGeneral" oncontextmenu="return false;">
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
                                        <div id="overlay"></div>              
                                        <div id="specialBox" style="width: auto;top:50px;">
                                            <s:form theme="simple"  align="center" name="ProjectResources" action="%{currentAction}" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"   >
                                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel" align="center"></span>

                                                                <td colspan="2" style="background-color: #288AD1" align="right">
                                                                    <a href="javascript:BDMOverlay('0');" >
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 
                                                                    </a>  
                                                                </td>
                                                            </h3>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <div id="barchart_material" style="width: auto; height: 400px;"></div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form> 
                                        </div>

                                        <!-- Overlay end -->


                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                            <tr>
                                                <td height="20px" >

                                                </td>
                                            </tr>                                            



                                            <!-- Reports Div start -->                        


                                            <tr>
                                                <td class="homePortlet" valign="top">
                                                    <div class="portletTitleBar">
                                                        <div class="portletTitleLeft">Sales Statistics</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('bdmPiechart')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('bdmPiechart')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="bdmPiechart" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="#" theme="simple" name="bdmDashboard" >   

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
                                                                                            <td class="fieldLabel">From&nbsp;:<font style="color:red;">*</font></td>
                                                                                            <td><s:select cssClass="inputTextBlueSmall" list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="fromMonth" id="fromMonth" onchange="" headerValue="select" headerKey="" value="%{fromMonth}"/></td>
                                                                                            <td class="fieldLabel"><font style="color:red;"></font></td>
                                                                                            <td><s:textfield name="fromYear" maxlength="4" cssClass="inputTextBlueSmall" id="fromYear" value="%{fromYear}"/></td>
                                                                                            <td class="fieldLabel">To&nbsp;:<font style="color:red;">*</font></td>
                                                                                            <td><s:select cssClass="inputTextBlueSmall" list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="toMonth" id="toMonth" onchange="" headerValue="select" headerKey="" value="%{toMonth}"/></td>
                                                                                            <td class="fieldLabel"><font style="color:red;"></font></td>
                                                                                            <td><s:textfield name="toYear" maxlength="4" cssClass="inputTextBlueSmall" id="toYear" value="%{toYear}"/></td>
                                                                                            <td class="fieldLabel">Account Name:</td>

                                                                                            <td>

                                                                                                <s:textfield name="accountName" id="accountName" value="" autocomplete="off" cssClass="inputTextBlue" onkeyup="getAccountNames();"/><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessage"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popup">
                                                                                                    <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                                                                                <s:hidden name="accountId" id="accountId" value=""/>
                                                                                                <s:hidden name="roleName" id="roleName" value="%{#session.roleName}"/>

                                                                                            </td>


                                                                                            <td class="fieldLabel">
                                                                                                <input type="button" value="Search" class="buttonBg" onclick="getBDMpiecharts();"/>
                                                                                            </td> 
                                                                                        </tr>



                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            
                                                                            
                                                                            

                                                                            <tr>
                                                                                <td>

                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                <%--<tr align='center'>
                                                                                <td>
                                                                                    <br>
                                                                                    <a class="btn" href="javascript:BDMOverlay();">Revenue</a>
                                                                                </td>


                                                                                <td><br>
                                                                                    <a class="btn" href="javascript:BDMOppertunitiesOverlay();">Opportunities</a>
                                                                                </td>
                                                                            </tr>--%>
                                                                            
                                                                            <tr>
                                                                                <td height="20px" align="center" colspan="9">
                                                                                    <div id="loadActMessageASh" style="display:none" class="error" ><br></br><b>Loading Please Wait.....</b></div>
                                                                                </td>
                                                                            </tr>
                                                                                        <tr>
                                                                                            <td>
                                                                                               
                                                                                                <div id="resultMessage" align="center" style=" color: red;
                                                                                                     font-family: lucida-sans;
                                                                                                     font-size: 15px;
                                                                                                     font-style: normal;
                                                                                                     font-variant: normal;
                                                                                                     font-weight: bold; display: none;"></div>
                                                                                                <div class="rcorners2" id="staatusGraph" style="display:none;">

                                                                                                    <div id="statuspiechart" style="width: 300px; height: 300px;"></div>

                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->



                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>

                                                                                            </td>
                                                                                            <td>


                                                                                                <div class="rcorners2" id="requirementGraph" style="display:none;">

                                                                                                    <div id="requirementpiechart" style="width: 300px; height: 300px;"></div>

                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->



                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>    
                                                                                </td>
                                                                            </tr>



                                                                            <tr>
                                                                                <td style="display:none">

                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >

                                                                                        <tr>
                                                                                            <td>
                                                                                                
                                                                                                <div class="rcorners2" id="oppertunitiesGraph" style="display:none;">

                                                                                                    <div id="opertunitypiechart" style="width: 300px; height: 300px;"></div>

                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->



                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                            
                                                                                            <td>
                                                                                                <div class="rcorners2" id="greensheetGraph" style="display:none;">

                                                                                                    <div id="greensheetpiechart" style="width: 300px; height: 300px;"></div>

                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->



                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td></td>
                                                                                        </tr>


                                                                                    </table>    
                                                                                </td>
                                                                            </tr>
                                                                                <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="tblloadMessage1" style="display: none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="3">
                                                                                                <div id="tableHeading1"  align='center'  style="display: none;color: green; font-size:14px;font-weight: bold;">Opportunities</div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="3">


<lable id="noteLable1" style="display: none;color:red;font-size: 80%; font-style: italic;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note :The details are order by CreatedBy,Account Name and Created Date.</lable>

                                                                                                <table id="tblrequirements1" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                        <COL width="7%">
                                                                                                        <COL width="10%">
                                                                                                        <COL width="15%">
                                                                                                        <COL width="15%">
                                                                                                        <COL width="7%">
                                                                                                        <COL width="7%">
                                                                                                        <COL width="7%">

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
                                                        <div class="portletTitleLeft">Opportunites -Requirements -GreenSheets Details</div>
                                                        <div class="portletIcons">
                                                            <a href="javascript:animatedcollapse.hide('opportunityDiv')" title="Minimize">
                                                                <img src="../../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                            <a href="javascript:animatedcollapse.show('opportunityDiv')" title="Maximize">
                                                                <img src="../../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                            </a>                                                           
                                                        </div>
                                                        <div class="clear"></div>
                                                    </div>
                                                    <div id="opportunityDiv" style="background-color:#ffffff">
                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                                            <tr>
                                                                <td width="40%" valign="top" align="center">
                                                                    <s:form action="#" theme="simple" name="bdmDashboard" >   

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
                                                                                            <td class="fieldLabel">From&nbsp;:<font style="color:red;">*</font></td>
                                                                                            <td><s:select cssClass="inputTextBlueSmall" list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="fromMonth1" id="fromMonth1" onchange="" headerValue="select" headerKey="" value="%{fromMonth}"/></td>
                                                                                            <td class="fieldLabel"><font style="color:red;"></font></td>
                                                                                            <td><s:textfield name="fromYear1" maxlength="4" cssClass="inputTextBlueSmall" id="fromYear1" value="%{fromYear}"/></td>
                                                                                            <td class="fieldLabel">To&nbsp;:<font style="color:red;">*</font></td>
                                                                                            <td><s:select cssClass="inputTextBlueSmall" list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="toMonth1" id="toMonth1" onchange="" headerValue="select" headerKey="" value="%{toMonth}"/></td>
                                                                                            <td class="fieldLabel"><font style="color:red;"></font></td>
                                                                                            <td><s:textfield name="toYear1" maxlength="4" cssClass="inputTextBlueSmall" id="toYear1" value="%{toYear}"/></td>

                                                                                            </td>


                                                                                            <td class="fieldLabel">
                                                                                                <input type="button" value="Search" class="buttonBg" onclick="opportunitiesPieChartDetails();"/>
                                                                                            </td> 
                                                                                        </tr>



                                                                                    </table>
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td height="20px" align="center" colspan="9">
                                                                                    <div id="loadMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                </td>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>

                                                                                    <table align="center" cellpadding="2" border="0" cellspacing="1" width="50%" >
                                                                                        <tr><td colspan="3">


                                                                                                <div id="resultMessage1" align="center" style=" color: red;
                                                                                                     font-family: lucida-sans;
                                                                                                     font-size: 15px;
                                                                                                     font-style: normal;
                                                                                                     font-variant: normal;
                                                                                                     font-weight: bold; display: none;"></div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td>


                                                                                                <div class="rcorners2" id="opportunitiesGraph" style="display:none;">

                                                                                                    <div id="opportunitieschart" style="width: 220px; height: 220px;"></div>

                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->



                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>

                                                                                            </td>
                                                                                            <td >


                                                                                                <div class="rcorners2" id="requirementByPracticeGraph" style="display:none;">

                                                                                                    <div id="requirementByPracticePiechart" style="width: 220px; height: 220px;"></div>

                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->



                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>

                                                                                            <td>
                                                                                                <div class="rcorners2" id="greensheetByPracticeGraph" style="display:none;">

                                                                                                    <div id="greensheetByPracticeChart" style="width: 220px; height: 220px;"></div>

                                                                                                    <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->



                                                                                                    <!--<center><span id="spnFast" class="activeFile" style="display: none;"></span></center>-->
                                                                                                </div>
                                                                                            </td>


                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td height="20px" align="center" colspan="9">
                                                                                                <div id="tblloadMessage" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="3">
                                                                                                <div id="tableHeading"  align='center'  style="display: none;color: green; font-size:14px;font-weight: bold;">Opportunities</div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="3">


<lable id="noteLable" style="display: none;color:red;font-size: 80%; font-style: italic;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note :The details are order by CreatedBy,Account Name and Created Date.</lable>

                                                                                                <table id="tblopportunities" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                        <COL width="7%">
                                                                                                        <COL width="10%">
                                                                                                        <COL width="20%">
                                                                                                        <COL width="15%">
                                                                                                        <COL width="7%">
                                                                                                        <COL width="7%">
                                                                                                        <COL width="7%">
                                                                                                        <COL width="7%">

                                                                                                </table>  



                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="3">




                                                                                                <table id="tblrequirements" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                        <COL width="7%">
                                                                                                        <COL width="10%">
                                                                                                        <COL width="15%">
                                                                                                        <COL width="15%">
                                                                                                        <COL width="7%">
                                                                                                        <COL width="7%">
                                                                                                        <COL width="7%">

                                                                                                </table>  



                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="3">




                                                                                                <table id="tblgreenSheet" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                                                                   <COLGROUP ALIGN="left" >
                                                                                                        <COL width="7%">
                                                                                                        <COL width="10%">
                                                                                                        <COL width="7%">
                                                                                                        <COL width="7%">
                                                                                                        <COL width="20%">
                                                                                                        <COL width="15%">
                                                                                                        <COL width="7%">



                                                                                                </table>  



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
		init();
		});
		</script>
    </body>

</html>

