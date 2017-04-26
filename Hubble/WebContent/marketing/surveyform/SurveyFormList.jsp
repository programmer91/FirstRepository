<%--
    Document   : SurveyFormList
    Created on : Aug 26, 2015, 3:02:47 PM
    Author     : miracle
--%>


<%@page import="com.mss.mirage.util.Properties"%>
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
        <title>Hubble Organization Portal :: Survey&nbsp;Form&nbsp;List</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/helpText.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>


        <script type="text/JavaScript" src='<s:url value="/includes/javascripts/GridNavigation.js"/>'></script>


        <script type="text/javascript" src="<s:url value="/includes/javascripts/marketing/SurveyFormScripts.js?version=1.0"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>
       

        <%-- for issue reminder popup --%>
        <style>
            .round-button {
                display:inline-block;
                width:20px;
                height:20px;
                line-height:20px;
                border: 0px solid #f5f5f5;
                border-radius: 50%;
                color:#f5f5f5;
                text-align:center;
                text-decoration:none;
                background: #4679BD;
                box-shadow: 0 0 3px gray;
                font-size:8px;
                font-weight:bold;
                cursor: pointer;
            }
            .round-button:hover {
                background:#30588e;
            }
        </style>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">


        <%String contextPath = request.getContextPath();
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px ;">
                                <!--//START TABBED PANNEL : -->
                                <%
                                    //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                    /// session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
%>

                                <ul id="accountTabs" class="shadetabs" >

                                    <%--    <% if(request.getParameter("issueList")==null)
                                           {%> --%>

                                    <li ><a href="#" class="selected" rel="issuesTab">Survey&nbsp;Form&nbsp;List</a></li>

                                    <%--   <%}else{%>
                                       <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                        <% }%> --%>

                                </ul>


                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">

                                    <div id="issuesTab" class="tabcontent" >
                                        <div id="overlay" ></div>
                                        <div id="specialBox1" >
                                            <s:form theme="simple" align="center" name="eventForm" id="eventForm">


                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>
                                                        <td colspan="2" class="cornerleft" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right" class="corneright">

                                                            <a href="#" onmousedown="toggleOverlay()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" />

                                                            </a>

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessage"></div>
                                                        </td>


                                                    </tr>
                                                    <tr><td colspan="4">
                                                            <table style="width:80%;" align="center">
                                                                 <div id="resultMessage123"></div>
                                                                <tr>
                                                                    <s:hidden name="tempEventDate" id="tempEventDate"/>

                                                                    <td class="fieldLabel" id="fromDateLabelId">Expiry&nbsp;Date&nbsp;:</td>
                                                                    <td><s:textfield name="selectDateFrom" id="selectDateFrom" cssClass="inputTextBlue" onchange="checkEventDate();validateTimestamp(this);" />
                                                                        <a href="javascript:cal3.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>
                                                                </tr>
                                                                   <tr>
                                                                       <td class="fieldLabel" id="existedLabelId">Existed&nbspExpiry&nbsp;Date&nbsp;:</td> <td><span id="existedLabelId1" style="color: red;"></span></td>
                                                                   
                                                                   <%-- <td> <s:textfield name="existedDateFrom" id="existedDateFrom" cssClass="inputTextBlue" readonly="true"/></td>--%>
                                                                    
                                                                </tr>

                                                                <tr id="addTr" style="display: none" >
                                                                 <td colspan="2" align="right">
                                                                        <!-- <input type="button" value="Update" class="buttonBg" id="addButton"/> </td></tr>-->
                                                                        
                                                                       <!-- <input type="button"  value="Update"  class="buttonBg" id="addButton" onclick="updateExp()"/> </td></tr> -->
                                                                       <input type="button"  value="Update"  class="buttonBg" id="addButton" onclick="updateSurveyFormExpiryDate()"/> </td></tr>


                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </div>


                                        <s:form action="surveyFormListSearch" name="frmDBGrid" id="frmDBGrid" theme="simple" onsubmit="return checkMandatory();">
                                            <div style="width:840px;">

                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">

                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                                                <tr align="right">
                                                                    <td class="headerText" colspan="11">
                                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                        <%
                                                                            if (request.getAttribute("resultMessage") != null) {
                                                                                out.println(request.getAttribute("resultMessage"));
                                                                            }

                                                                        %>
                                                                    </td>
                                                                </tr>
                                                                <%-- <tr>
                                <td class="fieldLabel" >Search Type<FONT color="red" ><em>*</em></FONT> </td>
                                <td ><s:select id="tableName"  name="tableName" list="#@java.util.LinkedHashMap@{'tblContactus':'ContactUs','tblEmpVerfication':'Employee Verification','tblEventAttendies':'QuarterlyMeet','tblResourceDepotDetails':'Resource Depot','tblSuggestions':'Suggestion Box'}" cssClass="inputSelect" headerKey="" headerValue="--Select Type--"/></td>
                                                                 </tr> --%>

                                                                <tr>

                                                                    <td class="fieldLabel">Date From:</td>
                                                                    <td><s:textfield name="createdDateFrom" id="createdDateFrom" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:cal1.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>
                                                                    <td class="fieldLabel">To:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield name="createdDateTo"  id="createdDateTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                        <a href="javascript:cal2.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                 width="20" height="20" border="0"></a>
                                                                    </td>

                                                                </tr>
                                                                <tr>
                                                                    <td class="fieldLabel">Title:</td>
                                                                    <td > <s:textfield name="surveyTitle"  id="surveyTitle" cssClass="inputTextBlue" /></td>




                                                                </tr>


                                                                <tr>

                                                                    <td></td>
                                                                    <td colspan="2"></td>
                                                                    <td>
                                                                        <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addSurveyForm();" />
                                                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                                                    </td>
                                                                </tr>

                                                                <s:if test="#session.surveyFormSearchList != null">
                                                                    <tr>
                                                                        <td colspan="4">
                                                                            <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">

                                                                                <%java.util.List mainList = (java.util.List) session.getAttribute("surveyFormSearchList");
                                                                                    if (mainList.size() > 0) {

                                                                                %>

                                                                                <tr class="gridHeader">
                                                                                    <th>Edit</th>
                                                                                    <th>Title</th>
                                                                                    <th>Type</th>
                                                                                    <th>Status</th>
                                                                                    <th>CreatedBy</th>
                                                                                    <th>CreatedDate</th>
                                                                                    <th>ExpiryDate</th>
                                                                                    <th>Questions</th>
                                                                                    <th>Responses</th>
                                                                                    <th>Preview</th>
                                                                                    <th>Live</th>
                                                                                    <!--<th>Publish</th>-->
                                                                                    <th>Service</th>
                                                                                </tr>
                                                                                <%

                                                                                    for (int i = 0; i < mainList.size(); i++) {
                                                                                %>
                                                                                <tr CLASS="gridRowEven">
                                                                                    <%
                                                                                        //java.util.List subList = (java.util.List)mainList.get(i);
                                                                                        java.util.Map subList = (java.util.Map) mainList.get(i);
                                                                                        //  for (int j = 0; j < subList.size(); j++) {

                                                                                    %>
                                                                                    <td>
                                                                                        <input type="hidden" id="survyIdCol" name="survyIdCol" value="<%=subList.get("Id")%>"/>
                                                                                      <input type="hidden" id="currentDateCol" name="currentDateCol" value="<%=subList.get("currentDate")%>"/> 
                                                                                        <a style="color:#C00067;" href="editSurveyForm.action?surveyId=<%=subList.get("Id")%>">
                                                                                            <img src="../../includes/images/DBGrid/Edit.gif"/>
                                                                                        </a>

                                                                                    </td>
                                                                                    <td class="title">

                                                                                        <%
                                                                                            //String fullTitle=subList.get("wholeTitle").toString();
                                                                                            //   String title=subList.get("Title").toString().substring(0,8);
                                                                                            //  System.out.println("title in jsp-->"+title);
                                                  //System.out.println("fullTitle in jsp-->"+fullTitle+"i-->"+i);
                                                                                            String table = "<a href='#' value='' title='" + subList.get("Title").toString() + "'>?</a>";
                                                                                            // out.print(title);
                                                                                            //  out.print(table);
                                                                                            //  out.println(title+table);
                                                                                            String tempTitle = subList.get("Title").toString();
                                                                                            if (tempTitle.length() > 20) {
                                                                                                tempTitle = tempTitle.substring(0, 19);
                                                                                            }
                                                                                            out.println(tempTitle + table);

                                                                                        %>


                                                                                    </td>

                                                                                    <td class="title">
                                                                                        <%
                                                                                            if ("I".equals(subList.get("SurveyType").toString())) {
                                                                                                out.println("Internal");
                                                                                            } else {
                                                                                                out.println("External");
                                                                                            }

                                                                                        %>
                                                                                    </td>
                                                                                    <td class="title">
                                                                                        <%
                                                                                            out.println(subList.get("CurrStatus").toString());
                                                                                        %>
                                                                                    </td>
                                                                                    <td class="title">
                                                                                        <%
                                                                                            out.println(subList.get("CreatedBy").toString());
                                                                                        %>
                                                                                    </td>
                                                                                    <td class="title">
                                                                                        <%
                                                                                            out.println(subList.get("CreatedDate").toString().split(" ")[0]);
                                                                                        %>
                                                                                    </td><td class="title">
                                                                                       
                                                                                        <a href="javascript:serviceRPFunctionality(this,'<%=subList.get("Id")%>','<%=subList.get("ExpiryDate")%>','ExpireDate');" ><% out.println(subList.get("ExpiryDate").toString().split(" ")[0]);%></a> 

                                                                                    </td>

                                                                                    <td class="title" align="center">

                                                                                        <a style="color:#C00067;" href="javascript:getQuestionnaire('<%=subList.get("Id")%>');">
                                                                                            <img src="../../includes/images/go_21x21.gif"/>
                                                                                        </a>

                                                                                    </td>

                                                                                    <td class="title" align="center">

                                                                                        <a style="color:#C00067;" href="javascript:getSurveyReviewList('<%=subList.get("Id")%>');">
                                                                                            <img src="../../includes/images/go_21x21.gif"/>
                                                                                        </a>

                                                                                    </td><td class="title">

                                                                                        <a style="color:#C00067;" href="http://www.miraclesoft.com/survey/survey-form.php?flag=stage&surveyId=<%=subList.get("Id")%>" target="_blank">
                                                                                            View
                                                                                        </a>

                                                                                    </td>
                                                                                    <td class="title">
                                                                                        <%if (subList.get("CurrStatus").toString().equals("Published")) {%>
                                                                                        <a style="color:#C00067;" href="http://www.miraclesoft.com/survey/survey-form.php?surveyId=<%=subList.get("Id")%>" target="_blank">
                                                                                            View
                                                                                        </a>
                                                                                        <% }%>
                                                                                    </td> <%--<td class="title">

                                                                                    <%if(Integer.parseInt(subList.get("QuesCount").toString())>3){%>
                                                                                    <a style="color:#C00067;" href="javascript:doPublishSurveyForm('<%=subList.get("Id")%>');">
                                                                                        <img src="../../includes/images/publish-button.jpg" height="30" width="50"/>
                                                                                   </a>
                                                                                        <% } %>
                                                                                   </td> --%>

                                                                                    <td  width="30%" class="" >

                                                                                        <% String status = subList.get("CurrStatus").toString();%>
                                                                                        <!--<div class="round-button"><div class="round-button-circle"><a>R</a></div></div>
                                                                                        <div class="round-button"><div class="round-button-circle"><a>RP</a></div></div>
                                                                                        <div class="round-button"><div class="round-button-circle"><a>RA</a></div></div>-->

                                                                                        <%--<span style="display: inline" > <a herf="#" class="round-button" onclick="serviceFunctionality(this,'<%=subList.get("Id")%>')">R</a><a herf="#" class="round-button" onclick="serviceRPFunctionality(this,'<%=subList.get("Id")%>','<%=subList.get("ExpiryDate")%>')">RP</a><a herf="#" class="round-button">RA</a></span>--%>
                                                                                        <%--   <span style="display: inline" > <a herf="#" class="round-button" onclick="serviceFunctionality(this,'<%=subList.get("Id")%>')">R</a>&nbsp;&nbsp;<a herf="#" class="round-button" onclick="serviceRPFunctionality(this,'<%=subList.get("Id")%>','<%=subList.get("ExpiryDate")%>')">RP</a>&nbsp;&nbsp;<a herf="#" class="round-button">RA</a>&nbsp;&nbsp;<a herf="#" class="round-button" onclick="doPublishSurveyForm(this,'<%=subList.get("Id")%>')">P</a></span> --%>
                                                                                        <%--   <span style="display: inline" > <a herf="#" class="round-button" onclick="serviceFunctionality(this,'<%=subList.get("Id")%>','<%=subList.get("CurrStatus")%>')">R</a><a herf="#" class="round-button" onclick="serviceRPFunctionality(this,'<%=subList.get("Id")%>','<%=subList.get("ExpiryDate")%>'">RP</a> <a herf="#" class="round-button" onclick="serviceRAFunctionality(this,'<%=subList.get("Id")%>','<%=subList.get("CurrStatus")%>')">RA</a>&nbsp;&nbsp;<a herf="#" class="round-button" onclick="doPublishSurveyForm(this,'<%=subList.get("Id")%>','<%=subList.get("CurrStatus")%>')">P</a></span> --%>
                                                                                        <span style="display: inline" > 
                                                                                            <%if(!"InActive".equalsIgnoreCase(subList.get("CurrStatus").toString())){%>
                                                                                          <a herf="#" class="round-button" title="In-Active" onclick="serviceFunctionality(this,'<%=subList.get("Id")%>','<%=subList.get("CurrStatus")%>')">I</a>
                                                                                           <% } %>  <%if(subList.get("isExpired").toString().equals("True")){%>
                                                                                            <a herf="#" class="round-button" title="Re-Publish"   onclick="serviceRPFunctionality(this,'<%=subList.get("Id")%>','<%=subList.get("ExpiryDate")%>','Republish')">RP</a>
                                                                                              <% } %><%if(!"Active".equals(subList.get("CurrStatus").toString())){%><a herf="#" title="Re-Active" class="round-button" onclick="serviceRAFunctionality(this,'<%=subList.get("Id")%>','<%=subList.get("CurrStatus")%>')">RA</a><% } %>
                                                                                              <%if(Integer.parseInt(subList.get("QuesCount").toString())>3 && subList.get("CurrStatus").toString().equals("Active")){%>
                                                                                                     <a herf="#" title="Publish" class="round-button" onclick="doPublishSurveyForm(this,'<%=subList.get("Id")%>','<%=subList.get("CurrStatus")%>')">P</a>
                                                                                             <% } %></span>
                                                                                        <%--      <%  if(status.equals("Active"))
                                                                                                                           {%>
                                                                                                                           <div class="round-button"><div class="round-button-circle"><a onclick="serviceFunctionality(this,'<%=subList.get("Id")%>')">R</a></div></div>
                                                                                                                         <div class="round-button"><div class="round-button-circle"><a><input type="button" name="b3" id="b3" value="R" onclick="serviceFunctionality(this,'<%=subList.get("Id")%>')"></a></div></div>

                                                                                            <%}
                                                                                          if(status.equals("Published"))
                                                                                          {
                                                                                          %>
                                                                                          <div class="round-button"><div class="round-button-circle"><a onclick="serviceRPFunctionality(this,'<%=subList.get("Id")%>','<%=subList.get("ExpiryDate")%>')">RP</a></div></div>
                                                                                         <input type="button" name="b1" id="b1" value="RP" onclick="serviceRPFunctionality(this,'<%=subList.get("Id")%>','<%=subList.get("ExpiryDate")%>')">

                                                                                                      <%} %>


                                                                                        --%>

                                                                                    </td>

                                                                                </tr>
                                                                                <% }%>


                                                                                <%


                                                                                } else {
                                                                                %>
                                                                                <tr><td>
                                                                                        <%
                                                                                            // String contextPath = request.getContextPath();
                                                                                            // out.println("<img  border='0' align='top' src='"+contextPath+"/includes/images/alert.gif'/><b> No Records Found to Display!</b>");

                                                                                            out.println("<img  border='0' align='top' src='" + contextPath + "/includes/images/alert.gif'/> <font color='white'><b>No records found!</b></font>");
                                                                                            // }

                                                                                        %>
                                                                                    </td>
                                                                                </tr>
                                                                                <%}%>

                                                                            </table>
                                                                        </td>
                                                                    </tr>



                                                                    <%

                                                                        if (mainList.size() != 0) {
                                                                    %>

                                                                    <tr>

                                                                        <td align="right" colspan="4" style="background-color:white;" >
                                                                            <div align="right" id="pageNavPosition">hello</div>
                                                                        </td>
                                                                    </tr>

                                                                    <%}
                                                                    %>
                                                                </s:if>


                                                            </table>
                                                        </td>
                                                    </tr>





                                                    <script type="text/JavaScript">
                                                        var cal1 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateFrom']);
                                                        cal1.year_scroll = true;
                                                        cal1.time_comp = false;

                                                        var cal2 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateTo']);
                                                        cal2.year_scroll = true;
                                                        cal2.time_comp = false;
                                                        var cal3 = new CalendarTime(document.forms['eventForm'].elements['selectDateFrom']);
                                                        cal3.year_scroll = true;
                                                        cal3.time_comp = true;


                                                    </script>


                                                    <script type="text/javascript">
                                                        var pager = new ReviewPager('results', 10);
                                                        pager.init();
                                                        pager.showPageNav('pager', 'pageNavPosition');
                                                        pager.showPage(1);
                                                    </script>

                                                </table>
                                            </div>
                                        </s:form>


                                        <%--  </sx:div > --%>
                                    </div>
                                    <%--   <%}%> --%>
                                    <!--//END TAB : -->

                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>

                                </div>
                                <!--//END TAB : -->
                                <%--  </sx:tabbedpanel> --%>

                                <!--//END TABBED PANNEL : -->
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("accountTabs")
                                    countries.setpersist(false)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()

                                </script>
                            </td>
                        </tr>
                    </table>
                </td>
                <!--//END DATA COLUMN : Coloumn for Screen Content-->
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


 <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script>
            $(function() {
                $( document ).tooltip();
            });
        </script>
        <s:include value="/includes/template/headerScript.html"/>


    </body>
</html>
