<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  July 30, 2013, 3:25 PM
 *
 * Author  : Ajay Tummapala<atummapala@miraclesoft.com>
 *
 * File    : KeysList.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Exam Keys List</title>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script> 
        <script type="text/javascript" src="<s:url value="/includes/javascripts/searchIssue.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/ecertificationAjax.js?version=1.0"/>"></script>
        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>
       
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
         <script type="text/javascript">
            function init1() {
 // alert("hello");
 var insflag =  document.getElementById("insSerachFlag").value;
// alert("insflag---->"+insflag);
 
 
 if(insflag==0){
 var today = new Date();
var endDay   = new Date();
var day = today.getDay() || 7; // Get current day number, converting Sun. to 7
if( day !== 1 ) {               // Only manipulate the date if it isn't Mon.
    today.setHours(-24 * (day - 1)); 
endDay.setHours(-24*(day -7)); 


} // Set the hours to day number minus 1
var cDate = today.getDate();
var cMonth = today.getMonth()+1;
var cYear = today.getFullYear();

var sDate = endDay.getDate();
var sMonth = endDay.getMonth()+1;
var sYear = endDay.getFullYear();
 
var datefrom =cMonth+"/"+cDate+"/"+cYear;
var dateto = sMonth+"/"+sDate+"/"+sYear;

document.getElementById("startDate").value = datefrom;
document.getElementById("endDate").value = dateto;
}
 
}    
            
        </script>
    </head>
    	

    
 <!--   <body class="bodyGeneral" oncontextmenu="return false;" onload="init1();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">
        <%!
        /* Declarations */
        Connection connection;
        String queryString;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        String action;
        int role;
        int intSortOrd = 0;
        int intCurr;
        String currentSearch=null;
        boolean blnSortAsc = true;
        HttpServletRequest httpServletRequest;
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
                            <td width="150px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/> 
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <%
                                    //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                 /// session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
                                %>
                                
                                <ul id="accountTabs" class="shadetabs" >
                                  <%--  <li ><a href="#" class="selected" rel="issuesTab"  > IssuesList </a></li>
                                    <li ><a href="#" rel="IssuesSearchTab">Issues Search</a></li> --%>
                                   
                                    <li ><a href="#" class="selected" rel="issuesTab"  > Exam Key List </a></li>
                                     <li ><a href="#" rel="IssuesSearchTab">Exam Key Search</a></li>
                                     <li ><a href="#" rel="ReportSearchTab">Generate Keys Report</a></li>
                                </ul>
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->
                                    <%-- <sx:div id="issuesTab" label="IssuesList" cssStyle="overflow:auto;" > --%>
                                     
                                    <div id="issuesTab" class="tabcontent"  >
                                        
                                        <%
                                        
                                        try{
                                            queryString="";
                                            /* Getting DataSource using Service Locator */
                                            connection = ConnectionProvider.getInstance().getConnection();
                                            
                                            
                                            /* String Variable for storing current position of records in dbgrid*/
                                            strTmp = request.getParameter("txtCurr");
                                            intCurr = 1;
                                            if (strTmp != null)
                                                intCurr = Integer.parseInt(strTmp);
                                            
                                            /* Specifing Shorting Column */
                                            strSortCol = request.getParameter("txtSortCol");
                                            
                                            if (strSortCol == null) strSortCol = "DateCreated";
                                            
                                            strSortOrd = request.getParameter("txtSortAsc");
                                            if (strSortOrd == null) strSortOrd = "ASC";
                                            
                                            queryString = session.getAttribute(ApplicationConstants.ECERT_MY_EXAM_KEYS_QUERY).toString();
                                            /* Sql query for retrieving resultset from DataBase */
                                         //  out.println(queryString);
                                            
                                      
                                        %>
                                        <s:form action="" name="frmDBGrid" theme="simple"> 
                                            <div style="width:840px;"> 
                                                <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                    <tr>
                                                        <td class="headerText">
                                                            
                                                            <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                          
                                                                <grd:dbgrid id="tblCrmAttachments" name="tblCrmAttachments" width="100" pageSize="17" 
                                                                            currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                            dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">
                                                                    <grd:gridpager imgFirst="../includes/images/DBGrid/First.gif" imgPrevious="../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../includes/images/DBGrid/Next.gif" imgLast="../includes/images/DBGrid/Last.gif"
                                                                                   />
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>                 
                                                                  <%-- <grd:numbercolumn dataField="ExamKeyId"  headerText="ExamKeyId" width="10"   dataFormat=""/>--%>
                                                                    <grd:textcolumn dataField="VKey"  headerText="Exam Key"   width="10" sortable="true"/> 
                                                                    <grd:textcolumn dataField="TopicName"  headerText="Exam Name"   width="10" sortable="true"/> 
                                                                    <grd:datecolumn dataField="CreatedDate"  headerText="Date Created"  dataFormat="MM-dd-yyyy HH:mm:SS" width="15" sortable="true"/>
                                                                    <grd:numbercolumn dataField="CreatedBy"  headerText="CreatedBy" width="10"   /> 
                                                                     
                                                                </grd:dbgrid>
                                                            
                                                           
                                                        
                                                            <!-- these components are DBGrid Specific  -->
                                                            <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                            <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                            <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            <s:hidden name="domainId" value="%{domainId}"/>
                                                            <s:hidden name="topicId" value="%{topicId}"/>
                                                            <s:hidden name="startDate" value="%{startDate}"/>
                                                            <s:hidden name="endDate" value="%{endDate}"/>
                                                          
                                                        </td>
                                                    </tr>
                                                </table>    
                                            </div>
                                        </s:form>  
                                        <%
                                        connection.close();
                                        connection = null;
                                        /* For Exception handling mechanism */
                                        }catch(Exception se){
                                            System.out.println("Exception in IssuesList "+se);
                                        }finally{
                                            if(connection!= null){
                                                connection.close();
                                                connection = null;
                                            }
                                            if(session!=null){
                                                session.removeAttribute("resultMessage");
                                            }
                                        }
                                        %>
                                        <%--  </sx:div > --%>
                                    </div>
                                   
                                    
                                    <div id="IssuesSearchTab" class="tabcontent"  >   
                                        <s:form name="frmSearch" action="searchExamKeyList" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                        
                                            <!--The components of searching issues -->
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%" align="center">
                                                     <tr>
                                                    <td class="headerText" align="right">
                                                        <s:hidden name="insSerachFlag" id="insSerachFlag"  cssClass="inputTextBlue"  value="%{insSerachFlag}"  theme="simple"/>
                                                        <s:hidden name="submitFrom" value="Search"/>
                                                        <s:submit cssClass="buttonBg" value="Search" />
                                                    </td>
                                                </tr>
                                              
                                                <tr><td align="center"> <table>
                                                     
                                                            <tr>
                                                                <td class="fieldLabel">Practice :</td>
                                                                <td> <s:select label="Select Domain" id="domainId" 
                                                                       name="domainId" headerKey=""            
                                                                       headerValue="-- Please Select --"
                                                                       list="domainMap" cssClass="inputSelect" value="%{domainId}" onchange="getTopicData();"/></td>
                                                                <td class="fieldLabel">Topic :</td>
                                                                <td>  <s:select label="Select Domain" id="topicId" 
                                                                       name="topicId" headerKey="-1"            
                                                                       headerValue="-- Please Select --"
                                                                       list="topicsMap" cssClass="inputSelect" value="%{topicId}" /></td>
                                                                
                                                            </tr>
                                                            
                                                            <tr>
                                                                
                                                                <td  class="fieldLabel">Start Date:</td>
                                                                   
                                                                <td width="40%"><s:textfield name="startDate" value="%{startDate}" id="startDate" cssClass="inputTextBlue"/><a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td>
                                                                <td class="fieldLabel">End Date:</td>
                                                                <td><s:textfield name="endDate" id="endDate" value="%{endDate}" cssClass="inputTextBlue"/><a href="javascript:cal2.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td>
                                                               
                                                            </tr>
                                                            <tr></tr>
                                                               <%--   <tr>
                                                                       <td></td><td></td><td></td>
                                                   <td align="">                                                                   
                                                     
                                                                    
                                                                    <s:submit cssClass="buttonBg" value="Search" />
                            
                                                    </td> 
                                                </tr>  --%>
                                                        </table>  
                                                    </td>
                                                </tr> 
                                                
                                            </table>
                                            
                                        </s:form>
                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                        <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['frmSearch'].elements['startDate']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                            
                                                           var cal2 = new CalendarTime(document.forms['frmSearch'].elements['endDate']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                        </script>
                                        
                                       
                                    </div> 
                                    
                                    <div id="ReportSearchTab" class="tabcontent"  >   
                                        <s:form name="frmKeyDownloadSearch" action="searchExamKeyList" theme="simple" method="post" onsubmit="return (validateDates() && compareDates(document.getElementById('startDate').value,document.getElementById('endDate').value));">
                                        
                                            
                                            <!--The components of searching issues -->
                                            <table border="0" cellpadding="1" cellspacing="1" width="100%" align="center">
                                                   <tr>
                                                    <td class="headerText" align="right">
                                                        <s:hidden name="insSerachFlag" id="insSerachFlag"  cssClass="inputTextBlue"  value="%{insSerachFlag}"  theme="simple"/>
                                                         <s:hidden name="submitFrom" value="Search"/>
                                                         <input type="button" class="buttonBg" value="Generate Excel" onclick="return getKeysReport();"/>
                                                    </td>
                                                </tr>
                                              
                                                <tr><td align="center"> <table>
                                                     
                                                            <tr>
                                                                <td class="fieldLabel">Practice :</td>
                                                                <td> <s:select label="Select Domain" id="domainIdReport" 
                                                                       name="domainId" headerKey="-1"            
                                                                       headerValue="-- Please Select --"
                                                                       list="domainMap" cssClass="inputSelect" value="%{domainId}" onchange="getTopicDataReport();"/></td>
                                                                <td class="fieldLabel">Topic :</td>
                                                                <td>  <s:select label="Select Domain" id="topicIdReport" 
                                                                       name="topicId" headerKey="-1"            
                                                                       headerValue="-- Please Select --"
                                                                       list="topicsMap" cssClass="inputSelect" value="%{topicId}" /></td>
                                                                
                                                            </tr>
                                                             <tr>
                                                                <td class="fieldLabel">Created Date:</td>
                                                                   
                                                                <td width="40%"><s:textfield name="createdDate" value="%{createdDate}" id="createdDate" cssClass="inputTextBlue"/><a href="javascript:cal9.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></a></td>
                                                            </tr>
                                                         
                                                            <tr></tr>
                                                                 
                                                        </table>  
                                                    </td>
                                                </tr> 
                                                
                                            </table>
                                            
                                        </s:form>
                                        <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
                                        <script type="text/JavaScript">
                                                          
                                      
                                                         
                                                           
                                                            var cal9 = new CalendarTime(document.forms['frmKeyDownloadSearch'].elements['createdDate']);
                                                           cal9.year_scroll = true;
                                                           cal9.time_comp = false;
                                        </script>
                                    </div> 
                                    
                                    <!--//END TAB : -->
                                  
                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>
                                   
                                    
                                    <!--//END TAB : -->
                                    <%--  </sx:tabbedpanel> --%>
                                </div>
                                <!--//END TABBED PANNEL : -->
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
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
        
        
        
        <script>
$(document).ready(function() {
   init1();
     });
</script>	
        
    </body>
</html>

