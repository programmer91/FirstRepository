<%-- 
    Document   : EmeetAttendies
    Created on : Jan 30, 2016, 12:17:12 AM
    Author     : miracle
--%>

<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  January 29, 2016, 3:25 PM
 *
 * Author  : Phanidra Kanuri<pkanuri@miraclesoft.com>
 *
 * File    : EmeetAttendees.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>


<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
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

        <title>Hubble Organization Portal :: Executive Meet Attendees</title>
        <sx:head cache="true"/>
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
        <script type="text/javascript" src="<s:url value="/includes/javascripts/searchIssue.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>

        <script type="text/JavaScript" src='<s:url value="/includes/javascripts/GridNavigation.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  




        <%-- for issue reminder popup --%>


        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/EmeetScripts.js"/>"></script> 
       
        <s:include value="/includes/template/headerScript.html"/>
    </head>
    <body class="bodyGeneral"  oncontextmenu="return false;">
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
            String currentSearch = null;
            boolean blnSortAsc = true;
            HttpServletRequest httpServletRequest;
        %>

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

                                    <li ><a href="#" class="selected" rel="issuesTab" >Executive Meet Attendees</a></li>

                                    <%--   <%}else{%>
                                       <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                        <% }%> --%>

                                </ul>


                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">

                                    <div id="issuesTab" class="tabcontent" >
                                        <div id="exeMeetAttendeesoverlay" ></div> 
                                        <div id="exeMeetAttendeesspecialBox" >
                                            <s:form theme="simple"  align="center" name="frmExeMeet" id="frmExeMeet" >

                                                <s:hidden name="exeMeetingId" id="exeMeetingId"/>

                                                <s:hidden name="id" id="id"/>
                                                  
                                                  <s:hidden name="loginId" id="loginId"/>
                                                    <s:hidden name="previousAttendeeStatus" id="previousAttendeeStatus"/>


                                                <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="toggleOverlayForExeMeetAttendees()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="">
                                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessage"></div>
                                                        </td>
                                                    </tr>    
                                                    <tr><td colspan="">
                                                            <table style="width:100%;" align="center" border="0">
                                                                <tr>
                                                                    <td align="left" class="fieldLabel" >E-Mail:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td >
                                                                        <%--   <sx:autocompleter name="executiveMeetingAttendeeEmail" id="executiveMeetingAttendeeEmail" list="activeEmployeeMap" showDownArrow="true" cssClass="inputSelectExtraLarge"/>--%>


                                                                        <%--  <s:textfield name="jobDepartment" id="jobDepartment" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);"/> --%>
                                                                        <s:select headerKey="-1" headerValue="--Please Select--" list="activeEmployeeMap" name="executiveMeetingAttendeeEmail" id="executiveMeetingAttendeeEmail" cssClass="inputSelect"/>
                                                                        <span id="emailSpanId"></span>
                                                                    </td> 
                                                                    <td  class="fieldLabel" >Access&nbsp;Type:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td >
                                                                        <%-- <s:textfield name="jobHireType" id="jobHireType" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);"/> --%>
                                                                         <s:select headerKey="" headerValue="--Please Select--" id="executiveMeetAccessType"  name="executiveMeetAccessType" list="{'Global Practice Meet','Global Sales Meet','Both'}" cssClass="inputSelect"  disabled="False"/>
                                                                        <%-- <s:select headerKey="-1" headerValue="--Please Select--" list="accessTypeList" name="executiveMeetAccessType" id="executiveMeetAccessType" cssClass="inputSelect"/> --%>



                                                                    </td> 

                                                                </tr>
                                                                <tr>
                                                                    <td align="left" class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td >

                                                                        <%--  <s:textfield name="jobDepartment" id="jobDepartment" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);"/> --%>
                                                                        <s:select list="{'Active','InActive'}" name="executiveMeetingAccessStatus" id="executiveMeetingAccessStatus" cssClass="inputSelect"/>
                                                                    </td> 
                                                                </tr>
                                                                <tr id="createdTr">
                                                                    <td class="fieldLabel" valign="top">CreatedBy:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdBy"></span></FONT></td>
                                                                    <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td><FONT color="green"  ><span id="createdDate"></span></FONT></td>
                                                                </tr>
                                                                <tr id="addTr" style="display: none"> 
                                                                    <td></td><td></td><td></td>
                                                                    <%--   <td class="fieldLabel">Job&nbsp;Logo:</td>
                                                                     <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="attachmentFileNameValidate();"/></td>  --%>
                                                                    <td  align="center" >
                                                                        <input type="button" value="Save" onclick="return doAddExecutiveMeetAttendees();" class="buttonBg" id="addButton"/> 


                                                                        <%-- <s:submit cssClass="buttonBg"  align="right" id="save" value="Save" /> --%>
                                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                                    </td>
                                                                </tr> <tr id="editTr" style="display: none"> 

                                                                    <td  align="center" colspan="4"style="text-align: right;padding-right: 50px;">
                                                                        <input type="button" value="Update" onclick="return doUpdateExeMeetingAttendeesDetails();" class="buttonBg" id="updateButton"/> &nbsp;&nbsp;
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>    
                                        </div>


                                        <s:form action="emeetAttendeesSearch.action" id="frmDBGrid" name="frmDBGrid" theme="simple"> 
                                            <div style="width:840px;"> 

                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">

                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                                                <tr>
                                                                    <td class="headerText" colspan="11">
                                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                    </td>
                                                                </tr>
                                                                <tr>

                                                                    <td class="fieldLabel"> E-Mail:</td>
                                                                    <td><s:textfield name="emeetAttendeesEmail" id="emeetAttendeesEmail" cssClass="inputTextBlue" /></td>






                                                                    <td class="fieldLabel">Access:</td>
                                                                    <td >
                                                                       <%--     <s:select headerKey="" headerValue="--Please Select--" id="emeetAccessType"  name="emeetAccessType" list="accessTypeList" cssClass="inputSelect"  disabled="False"/>--%>
                                                                        <s:select headerKey="" headerValue="--Please Select--" id="emeetAccessType"  name="emeetAccessType" list="{'Global Practice Meet','Global Sales Meet','Both'}" cssClass="inputSelect"  disabled="False"/></td>
                                                                </tr><tr>
                                                                    <td align="left" class="fieldLabel" >Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td >

                                                                        <%--  <s:textfield name="jobDepartment" id="jobDepartment" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);"/> --%>
                                                                        <s:select list="{'Active','InActive'}" name="emeetMeetingAccessStatus" id="emeetMeetingAccessStatus" cssClass="inputSelect"/>
                                                                    </td><td></td>
                                                                    <td>
                                                                        <!-- <input type="button" value="Search" class="buttonBg" onclick="getSearchReqList();"/> -->
                                                                        <%-- <s:if test="#session.jobPostingFlag == 1 || #session.roleId == 12">
                                                                           <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addExecutiveMeet();" /> 
                                                                         </s:if> --%>
                                                                        <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addExecutiveMeetAttendees();" /> 
                                                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                                                        <!--<input type="button" align="right" id="search" value="Search" cssClass="buttonBg" onclick="getSearchReqList();"/>-->
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>

                                                   


                                                    <s:if test="#session.ExecutiveMeetingsAttendeeslist != null"> 

                                                        <tr>
                                                            <td style="padding-top: 30px;">
                                                                <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">

                                                                    <%java.util.List mainList = (java.util.List) session.getAttribute("ExecutiveMeetingsAttendeeslist");
                                                                        if (mainList.size() > 0) {


                                                                    %>

                                                                    <tr class="gridHeader">
                                                                        <th>Employee Name</th>
                                                                        <th>E-Mail</th>
                                                                        <th>Designation</th>
                                                                        <th>Access Type</th>
                                                                        <%--   <s:if test="#session.teamName == 'Sourcing'"> --%>

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
                                                                        <td class="title">
<a style="color:#C00067;" href="javascript:editExeMeetAttendees('<%=subList.get("Id")%>');">
                                                                            <%

                                                                                out.println(subList.get("EmpName"));

                                                                            %>
                                                                            
                                                                                 </td> 
                                                                                 <td>
                                                                                <%
                                                                                    out.println(subList.get("Email"));

                                                                                %>
                                                                        </td>       
                                                                        <td class="title">
                                                                            <%
                                                                                out.println(subList.get("TitleTypeId"));

                                                                            %>
                                                                        </td>         
                                                                        <td class="title">
                                                                            <%
                                                                                out.println(subList.get("AccessType"));

                                                                            %>
                                                                        </td>
                                                                        <%--    <s:if test="#session.teamName == 'Sourcing'"> --%>


                                                                        <%

                                                                            //   }
                                                   %></tr><%

                                                                       }
                                                                   } else {
                                                                        %>
                                                                    <tr><td>
                                                                            <%
                                                                                // String contextPath = request.getContextPath();
                                                                                // out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/><b> No Records Found to Display!</b>");

                                                                                out.println("<img  border='0' align='top'  src='" + contextPath + "/includes/images/alert.gif'/> <font color='white'><b>No records found!</b></font>");
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




                                                    <script type="text/javascript">
                                                        var pager = new Pager('results', 10); 
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





    </body>
</html>

