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
        <%--<link rel="stylesheet" href="<s:url value="/includes/css/displaytag.css"/>">--%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CurrentDateUtilplus2.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <!-- Enable below option if AJAX usage To Retrieve Regions,Territoris  -->
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CrmAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountSearchClientValidation.js"/>"></script>  --%>
        <!-- Disable below option if AJAX usage To Retrieve Regions,Territoris  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RegionTerritoryUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StringUtility.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>--%>
        <s:include value="/includes/template/headerScript.html"/>
        
        
        <script type="text/JavaScript">
            function parseDate(str) {
    var mdy = str.split('/')
    return new Date(mdy[2], mdy[0]-1, mdy[1]);
}

function daydiff(first, second) {
    return (second-first)/(1000*60*60*24);
}
         function mydiff(date1,date2,interval) {
    var second=1000, minute=second*60, hour=minute*60, day=hour*24, week=day*7;
    date1 = new Date(date1);
    date2 = new Date(date2);
    var timediff = date2 - date1;
    if (isNaN(timediff)) return NaN;
    switch (interval) {
        case "years": return date2.getFullYear() - date1.getFullYear();
        case "months": return (
            ( date2.getFullYear() * 12 + date2.getMonth() )
            -
            ( date1.getFullYear() * 12 + date1.getMonth() )
        );
        case "weeks"  : return Math.floor(timediff / week);
        case "days"   : return Math.floor(timediff / day); 
        case "hours"  : return Math.floor(timediff / hour); 
        case "minutes": return Math.floor(timediff / minute);
        case "seconds": return Math.floor(timediff / second);
        default: return undefined;
    }
}   
            
        function getEmailCampaignExcelSheet(){
           
           var reqDate = document.getElementById('fromDate').value;
           
     

            var campaignId = document.getElementById('campaignId').value;
            //alert("the from date is :"+fromDate);
           
            if(reqDate!=null && reqDate!=""){
                window.location="getCampaignExcelSheet.action?fromDate="+reqDate+"&campaignId="+campaignId;
            }
            else{
                alert("Please Enter the From Date");
                return false;
            }
        }
        </script>
    </head>
    <body  class="bodyGeneral" onload="" oncontextmenu="return false;">
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
            //int intSortOrd = 0;
            int intCurr = 1;

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
                                    <li><a href="#" rel="accountsListTab" class="selected">Email Campaign</a></li>
                                </ul>

                                <div  style="border:1px solid gray; width:840px;height: 550px;overflow:auto; margin-bottom: 1em;">    
                                    <%--<div id="accountsSearchTab" class="tabcontent">--%>
                                    <div id="jobprofilelist" style="background-color:#ffffff">
                                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="100%"> 
                                            <tr>
                                                <td valign="top" align="center">

                                                    <s:form name="frmemailcamp" theme="simple">

                                                        <table border="0" cellpadding="1" cellspacing="0" width="70%">

                                                            <tr>
                                                                <td></td>
                                                            </tr>
                                                            <tr>
                                                                <td></td>
                                                            </tr>

                                                            <tr>                                                    
                                                                <td class="fieldLabel" width="25%">From&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                                <td><s:textfield name="fromDate" id="fromDate" cssClass="inputTextBlueSmall" value="%{fromDate}" /><a href="javascript:cal1.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a></td>
                                                                        
                                                                <td class="fieldLabel">Campaign Name :</td>
                                                                <td><s:select list="campaignIdMap" name="campaignId" id="campaignId" cssClass="inputSelect" value="5"/></td>
                                                                <td colsapn="3" align="right"><input type="button" name="generateExcel" id="generateExcel" class="buttonBg" value="Generate Excel" onclick="return getEmailCampaignExcelSheet();"/></td>
                                                            </tr>
                                                            

                                                            <!--Below Block is for AJAX Based Call to DB-->
                                                            <!-- New Functionality for Account Search based on conatct name -->
                                                            <!-- end -->
                                                            <!-- Account Priority -->
                                                        </table>

                                                    </s:form>
                                                </td>
                                            </tr>
                                        </table>
                                        <script type="text/JavaScript">
                                            var cal1 = new CalendarTime(document.forms['frmemailcamp'].elements['fromDate']);
                                            cal1.year_scroll = true;
                                            //cal1.time_comp = true;
                                        </script>
                                        <%--  </sx:div> --%>
                                    </div>






                                    <%--     </sx:div>
                            </sx:tabbedpanel> --%>
                                    <!--//END TABBED PANNEL : --> 
                                </div>  
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
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>

</html>
