<%@page import="com.mss.mirage.util.DataSourceDataProvider"%>
<%@page import="java.util.Map"%>
<%@page import="com.mss.mirage.util.Properties"%>
<%@page import="com.constantcontact.components.contacts.EmailAddress"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.constantcontact.components.contacts.Contact"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>



<html>
    <head>

        <title>Hubble Organization Portal :: ${title} ListAll</title>
        <%--<sx:head cache="true"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/animatedcollapse.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/jquery-1.2.2.pack.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CurrentDateUtilplus2.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RegionTerritoryUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/constantContact.js"/>"></script>
        <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
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
          
            animatedcollapse.addDiv('contactListDiv', 'fade=1;persist=1;group=app');
            
            animatedcollapse.addDiv('SynchronizationDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.addDiv('campaignListDiv', 'fade=1;persist=1;group=app');
            animatedcollapse.init();
        </script>

    </head>
    <!-- <body  class="bodyGeneral" onload="javascript:animatedcollapse.show('SalesClosuresExcelReportDiv');pagerOption();" oncontextmenu="return false;"> -->
    <body  class="bodyGeneral" oncontextmenu="return false;">
        <%!
            /* Declarations */
            Connection connection;
            String queryString;
            String currentAccountId;
            String strTmp;
            String userId;
            String strSortCol;
            String strSortOrd = "ASC";
            String submittedFrom;
            String roleName;
            boolean blnSortAsc = true;
            String viewType;
            int intCurr = 1;
            List l = new ArrayList();
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
                    <ul id="accountTabs" class="shadetabs" >
                        <li><a href="#" rel="jobprofilelist" class="selected">Constant Contact Email List</a></li>
                        <li><a href="#" rel="dashBoradContact" >DashBoard</a></li>
                    </ul>

                    <div  style="border:1px solid gray; width:840px;height: 550px;overflow:auto; margin-bottom: 1em;">    
                        <%--<div id="accountsSearchTab" class="tabcontent">--%>
                        <div id="jobprofilelist" style="background-color:#ffffff">

                            <table align="center" cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                <tr>
                                <td valign="top" align="center">

                                    <s:form name="SearchConstantContact" action="" theme="simple">

                                        <table border="0" cellpadding="1" cellspacing="0" width="70%">



                                            <tr>                                                    
                                            <td class="fieldLabel">Constant&nbsp;Contact&nbsp;List:</td>
                                            <td><s:select headerKey="All" headerValue="--All--"  list="constantContactList" name="constantContactId" id="constantContactId" cssClass="inputSelect" value="%{constantContactId}"/></td>
                                            <td class="fieldLabel">Contact&nbsp;EmailId:</td>
                                            <td><s:textfield name="contactEmailID" id="contactEmailID" cssClass="inputTextBlue" 
                                                         value=""/></td>
                                            </tr>
                                            <tr>
                                            <td>
                                                <br>
                                            </td>
                                            </tr>
                                            <tr>

                                            <td colspan="4" align="right"><input type="button" name="search" id="btnEmailList" value="Search" class="buttonBg" onclick="constantContactEmailList();"/>
                                  
                                            </td>
                                            </tr>
                                            <tr>
                                            <td colspan="5">
                                                <div id="loadingMessageCon" style="color:red; display: none;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>
                                                <div id="resultMessage1" align="center"></div>
                                            </td>
                                            </tr>
                                            <tr>
                                            <td colspan="5">

                                                <div id="pag" style=" display: none;">
                                                    <label style="top: 10%; position: relative;"> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption();" style="width: auto">

                                                            <option>15</option>
                                                            <option>20</option>
                                                            <option>25</option>
                                                        </select> Rows</label></div>
                                                <table id="ContactEmailListTbl" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' align="center">

                                                </table>



                                            </td>
                                            </tr>

                                        </table>

                                    </s:form>

                                </td>
                                </tr>
                            </table>

                            <%--  </sx:div> --%>
                        </div>
                        <div id="displyID"></div>
                        <div id="blockDiv" style="display: none"align="center;position:relative;top:30%">
                            <font style="font-weight:bold;position: absolute;color: #fff;font-size: 23px;top:-53px;left:32px ">  Syncing <font id="fontId">...</font> </font>
                            <img src='../includes/images/ajax-loader.gif' WIDTH='35%' HEIGHT='5%'  alt='block'>
                        </div>
                        <%-- codeeeeeeeee for over lay--%>

                        <!-- Overlay open -->
                        <div id="overlayEmailCampaign"></div>              
                        <div id="specialBoxEmailCampaign" >
                            <s:form theme="simple"  align="center" name="ProjResour" action="" method="post"   >
                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                    <tr>                               
                                    <td colspan="2" style="background-color: #288AD1" >
                                        <h3 style="color:darkblue;" align="left">
                                            <span id="headerLabel" align="center"></span>

                                            <td colspan="2" style="background-color: #288AD1" align="right">
                                                <a href="javascript:trackingEmailCampaign('0');" >
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
                                                        <div id="loadingMessageCam" style="color:red; display: none;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>
                                                        <div id="restMessage"></div>
                                                    </td>
                                                    </tr>
                                                    <tr>
                                                    <td colspan="5">
                                                        <%-- <DIV id="loadingMessage"> </DIV> --%>

                                                        <div style="overflow: auto;max-height:400px;max-width:1000px;"> 
                                                            <table id="TrackingTbl" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' align="center">
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
                                                                                        </s:form> 
                                                                                        </div>

                                                                                        <%--end ******************************************************--%>
                                                                                        <div id="dashBoradContact" style="background-color:#ffffff">


                                                                                            <table align="center" cellpadding="0" cellspacing="0" border="0" width="100%"> 

                                                                                                <tr>
                                                                                                <td class="homePortlet" valign="top">
                                                                                                    <div class="portletTitleBar">
                                                                                                        <div class="portletTitleLeft">Contact List</div>
                                                                                                        <div class="portletIcons">
                                                                                                            <a href="javascript:animatedcollapse.hide('contactListDiv')" title="Minimize">
                                                                                                                <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                            <a href="javascript:animatedcollapse.show('contactListDiv')" title="Maximize">
                                                                                                                <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                            </a>
                                                                                                        </div>
                                                                                                        <div class="clear"></div>
                                                                                                    </div>
                                                                                                    <div id="contactListDiv" style="background-color:#ffffff">

                                                                                                        <s:form name="frmConstantContact" action="" theme="simple">

                                                                                                            <table border="0" cellpadding="1" cellspacing="0" width="70%">


                                                                                                                <tr>
                                                                                                                <td 
                                                                                                                    class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                                                                <td><s:textfield name="startDate" id="startDate" 
                                                                                                                             cssClass="inputTextBlueSmall" value="%{startDate}"/><a 
                                                                                                                        href="javascript:cal1.popup();">
                                                                                                                        <img 
                                                                                                                            src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                            width="20" height="20" border="0"></a>
                                                                                                                </td>

                                                                                                                <td 
                                                                                                                    class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                                                                <td>  <s:textfield name="endDate" id="endDate" 
                                                                                                                             cssClass="inputTextBlueSmall" value="%{endDate}"/><a 
                                                                                                                        href="javascript:cal2.popup();">
                                                                                                                        <img 
                                                                                                                            src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                            width="20" height="20" border="0"></a>
                                                                                                                </td>
                                                                                                                </tr>
                                                                                                                <tr><td>
                                                                                                                    <br>
                                                                                                                </td></tr>
                                                                                                                <tr>

                                                                                                                <td colspan="4" align="right">
                                                                                                                    <input type="button" class="buttonBg"  align="right"  id="btnContactList" value="Search" onclick="getContactListSearch();"/>

                                                                                                                </td>
                                                                                                                <td></td>

                                                                                                                </tr>           
                                                                                                                <tr>
                                                                                                                <td colspan="5">

                                                                                                                    <table cellpadding="0" cellspacing="0" width="100%">
                                                                                                                        <tr>
                                                                                                                        <td colspan="5">
                                                                                                                            <table width="100%" cellpadding="1" cellspacing="1" border="0">
                                                                                                                                <tr>
                                                                                                                                <td colspan="5">
                                                                                                                                    <div id="loadingMessage1" style="color:red; display: none;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>
                                                                                                                                    <div id="resMesg"></div>                                  
                                                                                                                                </td>
                                                                                                                                </tr>
                                                                                                                                <tr>
                                                                                                                                <td colspan="5">
                                                                                                                                    <%-- <DIV id="loadingMessage"> </DIV> --%>

                                                                                                                                    <div style="overflow: auto;max-height:400px;max-width:1000px;"> 
                                                                                                                                        <table id="ContactListSearchTbl" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' align="center">
                                                                                                                                            <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
                                                                                                                                            <COLGROUP ALIGN="left" >
                                                                                                                                                <COL width="10">
                                                                                                                                                    <COL width="20"> 
                                                                                                                                                        <COL width="20">
                                                                                                                                                            <COL width="25">
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


                                                                                                                                                                        <!--Below Block is for AJAX Based Call to DB-->
                                                                                                                                                                        <!-- New Functionality for Account Search based on conatct name -->
                                                                                                                                                                        <!-- end -->
                                                                                                                                                                        <!-- Account Priority -->
                                                                                                                                                                        </table>

                                                                                                                                                                    </s:form>
                                                                                                                                                                    </div>
                                                                                                                                                                    </td>
                                                                                                                                                                    </tr>
                                                                                                                                                                    <!--  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
                            


                                                                                                                                                                    <tr>
                                                                                                                                                                    <td class="homePortlet" valign="top">
                                                                                                                                                                        <div class="portletTitleBar">
                                                                                                                                                                            <div class="portletTitleLeft">Syncing Hubble Contacts with Constant Contacts CC</div>
                                                                                                                                                                            <div class="portletIcons">
                                                                                                                                                                                <a href="javascript:animatedcollapse.hide('SynchronizationDiv')" title="Minimize">
                                                                                                                                                                                    <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                                <a href="javascript:animatedcollapse.show('SynchronizationDiv')" title="Maximize">
                                                                                                                                                                                    <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                                </a>
                                                                                                                                                                            </div>
                                                                                                                                                                            <div class="clear"></div>
                                                                                                                                                                        </div>
                                                                                                                                                                        <div id="SynchronizationDiv" style="background-color:#ffffff">
                                                                                                                                                                            <s:form name="frmSynchronization" action="" theme="simple">

                                                                                                                                                                                <table border="0" cellpadding="1" cellspacing="0" width="70%">


                                                                                                                                                                                    <tr>
                                                                                                                                                                                    <td class="fieldLabel">Constant&nbsp;Contact&nbsp;Location&nbsp;List:</td>
                                                                                                                                                                                    <td>
                                                                                                                                                                                        <s:select name="loactionName"  id="loactionName" 
                                                                                                                                                                                                  cssClass="inputSelect" headerKey="All" headerValue="-- All --" 
                                                                                                                                                                                                  list="locationsMap" value="%{loactionName}"></s:select> 
                                                                                                                                                                                        </td>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <input type="button" class="buttonBg"  align="right" id="btnContactSync"  value="Search" onclick="getSyncListSearch();"/></td>
                                                                                                                                                                                        </tr>
                                                                                                                                                                                        <tr>


                                                                                                                                                                                        </tr>           
                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td colspan="5">

                                                                                                                                                                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                                                                                                                                                                <tr>
                                                                                                                                                                                                <td colspan="5">
                                                                                                                                                                                                    <table width="100%" cellpadding="1" cellspacing="1" border="0">
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5">
                                                                                                                                                                                                        <div id="loadingMessage3" style="color:red; display: none;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>
                                                                                                                                                                                                        <div id="resultMessage2" align="center"></div>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5">
                                                                                                                                                                                                        <%-- <DIV id="loadingMessage"> </DIV> --%>

                                                                                                                                                                                                        <div style="overflow: auto;max-height:400px;max-width:1000px;"> 
                                                                                                                                                                                                        <table id="SynchronizeListTbl" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' align="center">
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
                                                                                                                                                                                                        <COLGROUP ALIGN="left" >
                                                                                                                                                                                                        <COL width="10">
                                                                                                                                                                                                        <COL width="25"> 
                                                                                                                                                                                                        <COL width="20">
                                                                                                                                                                                                        <COL width="20">
                                                                                                                                                                                                        <COL width="25">


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


                                                                                                                                                                                                        <!--Below Block is for AJAX Based Call to DB-->
                                                                                                                                                                                                        <!-- New Functionality for Account Search based on conatct name -->
                                                                                                                                                                                                        <!-- end -->
                                                                                                                                                                                                        <!-- Account Priority -->
                                                                                                                                                                                                        </table>

                                                                                                                                                                                                        </s:form>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                                                      
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td class="homePortlet" valign="top">
                                                                                                                                                                                                        <div class="portletTitleBar">
                                                                                                                                                                                                        <div class="portletTitleLeft">Campaign list</div>
                                                                                                                                                                                                        <div class="portletIcons">
                                                                                                                                                                                                        <a href="javascript:animatedcollapse.hide('campaignListDiv')" title="Minimize">
                                                                                                                                                                                                        <img src="../includes/images/portal/title_minimize.gif" alt="Minimize"/></a>
                                                                                                                                                                                                        <a href="javascript:animatedcollapse.show('campaignListDiv')" title="Maximize">
                                                                                                                                                                                                        <img src="../includes/images/portal/title_maximize.gif" alt="Maximize"/>
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div class="clear"></div>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <div id="campaignListDiv" style="background-color:#ffffff">
                                                                                                                                                                                                        <s:form name="frmConstantContactCampaign" action="" theme="simple">

                                                                                                                                                                                                        <table border="0" cellpadding="1" cellspacing="0" width="70%">


                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td 
                                                                                                                                                                                                        class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                                                                                                                                                        <td><s:textfield name="campaignStartDate" id="campaignStartDate" 
                                                                                                                                                                                                        cssClass="inputTextBlueSmall" value="%{startDate}"/><a 
                                                                                                                                                                                                        href="javascript:cal3.popup();">
                                                                                                                                                                                                        <img 
                                                                                                                                                                                                        src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                                                        width="20" height="20" border="0"></a>
                                                                                                                                                                                                        </td>

                                                                                                                                                                                                        <td 
                                                                                                                                                                                                        class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                                                                                                                                                        <td>  <s:textfield name="campaignEndDate" id="campaignEndDate" 
                                                                                                                                                                                                        cssClass="inputTextBlueSmall" value="%{endDate}"/><a 
                                                                                                                                                                                                        href="javascript:cal4.popup();">
                                                                                                                                                                                                        <img 
                                                                                                                                                                                                        src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                                                        width="20" height="20" border="0"></a>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="4" align="right">
                                                                                                                                                                                                        <input type="button" class="buttonBg"  align="right" id="btnCampaignList"  value="Search" onclick="getCampaignListSearch();"/></td>

                                                                                                                                                                                                        </tr>           
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5">

                                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" width="100%">
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5">
                                                                                                                                                                                                        <table width="100%" cellpadding="1" cellspacing="1" border="0">
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5">
                                                                                                                                                                                                        <div id="loadingMessage2" style="color:red; display: none;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>
                                                                                                                                                                                                        <div id="resMessage" ></div>                                               
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <tr>
                                                                                                                                                                                                        <td colspan="5">
                                                                                                                                                                                                        <%-- <DIV id="loadingMessage"> </DIV> --%>


                                                                                                                                                                                                        <div id="pag1" style=" display: none;">
                                                                                                                                                                                                        <label style="top: 10%; position: relative;"> Display <select id="paginationOption1" class="disPlayRecordsCss" onchange="pagerOption1();" style="width: auto">

                                                                                                                                                                                                        <option>10</option>
                                                                                                                                                                                                        <option>15</option>
                                                                                                                                                                                                        <option>20</option>
                                                                                                                                                                                                        </select> Rows</label></div>
                                                                                                                                                                                                        <table id="campSearchTbl" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750' align="center">


                                                                                                                                                                                                        </table>

                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>

                                                                                                                                                                                                        </table>


                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>


                                                                                                                                                                                                        <!--Below Block is for AJAX Based Call to DB-->
                                                                                                                                                                                                        <!-- New Functionality for Account Search based on conatct name -->
                                                                                                                                                                                                        <!-- end -->
                                                                                                                                                                                                        <!-- Account Priority -->
                                                                                                                                                                                                        </table>

                                                                                                                                                                                                        </s:form>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        </td>
                                                                                                                                                                                                        </tr>




                                                                                                                                                                                                        </table>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        <%--  </sx:div> --%>
                                                                                                                                                                                                        </div>



                                                                                                                                                                                       



                                                                                                                                                                                                        <!--//END TABBED PANNEL : --> 
                                                                                                                                                                                                        </div>  
                                                                                                                                                                                                        <script 
                                                                                                                                                                                                        type="text/javaScript">
                                                                                                                                                                                                            var cal1 = 
                                                                                                                                                                                                                new CalendarTime(document.forms['frmConstantContact'].elements['startDate']);
                                                                                                                                                                                                            cal1.year_scroll = true;
                                                                                                                                                                                                            cal1.time_comp = false;
                                                                                                                                                                                                            var cal2 = 
                                                                                                                                                                                                                new CalendarTime(document.forms['frmConstantContact'].elements['endDate']);
                                                                                                                                                                                                            cal2.year_scroll = true;
                                                                                                                                                                                                            cal2.time_comp = false;
                                                                                                                                                                                                            var cal3 = 
                                                                                                                                                                                                                new CalendarTime(document.forms['frmConstantContactCampaign'].elements['campaignStartDate']);
                                                                                                                                                                                                            cal3.year_scroll = true;
                                                                                                                                                                                                            cal3.time_comp = false;
                                                                                                                                                                                                            var cal4 = 
                                                                                                                                                                                                                new CalendarTime(document.forms['frmConstantContactCampaign'].elements['campaignEndDate']);
                                                                                                                                                                                                            cal4.year_scroll = true;
                                                                                                                                                                                                            cal4.time_comp = false;

                                                                                                                                                                                                        </script>
                                                                                                                                                                                                        <script type="text/javascript">

                                                                                                                                                                                                            var countries=new ddtabcontent("accountTabs")
                                                                                                                                                                                                            countries.setpersist(false)
                                                                                                                                                                                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                                                                                                                                                                            countries.init()

                                                                                                                                                                                                        </script>

                                                                                                                                                                                                        </td>                                          
                                                                                                                                                                                                        </tr>                                   <!--//END DATA COLUMN : Coloumn for Screen Content-->

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
                                                                                                                                                                                                       
                                                                                                                                                                                                            function pagerOption(){

                                                                                                                                                                                                                var paginationSize = document.getElementById("paginationOption").value;
                                                                                                                                                                                                                if(isNaN(paginationSize))
                                                                                                                                                                                                                {
                       
                                                                                                                                                                                                                }
                                                                                                                                                                                                                recordPage=paginationSize;
                                                                                                                                                                                                  
                                                                                                                                                                                                                $('#ContactEmailListTbl').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                                                            }
                                                                                                                                                                                                        
                                                                                                                                                                                                            function pagerOption1(){

                                                                                                                                                                                                                var paginationSize = document.getElementById("paginationOption1").value;
                                                                                                                                                                                                                if(isNaN(paginationSize))
                                                                                                                                                                                                                {
                       
                                                                                                                                                                                                                }
                                                                                                                                                                                                                recordPage=paginationSize;
                                                                                                                                                                                                  
                                                                                                                                                                                                                $('#campSearchTbl').tablePaginate({navigateType:'navigator'},recordPage);

                                                                                                                                                                                                            }
                                                                                                                                                                                                        </script>
                                                                                                                                                                                                        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/pagination.js"/>"></script>
                                                                                                                                                                                                                                                                            <script type="text/javascript">
		$(window).load(function(){
	animatedcollapse.show('SalesClosuresExcelReportDiv');
	pagerOption();
		});
		</script>

                                                                                                                                                                                                        <!--//END MAIN TABLE : Table for template Structure-->
                                                                                                                                                                                                        </body>

                                                                                                                                                                                                        </html>
