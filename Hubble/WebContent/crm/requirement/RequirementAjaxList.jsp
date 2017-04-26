<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    : Sep 09, 2007, 3:25 PM
 *
 * Author  : Venkateswara Rao Nukala<vnukala@miraclesoft.com>
 *
 * File    : LeaveList.jsp 
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
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Requirement List</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RequirementUtil.js?version=1.0"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxPopup.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxRequirement.js?version=2.0"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/LoadConsultantAjax.js"/>"></script> 

        <s:include value="/includes/template/headerScript.html"/> 
    </head>
    <%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getRequirementsList1();"> --%>
   <%-- <s:if test="%{requirementAdminFlag == 'YES'}">
        <body class="bodyGeneral" oncontextmenu="return false;" onload="getRequirementsAdminList1();">
        </s:if><s:else>
        <body class="bodyGeneral" oncontextmenu="return false;" onload="getRequirementsList1();">
        </s:else> --%>
        <body class="bodyGeneral" oncontextmenu="return false;">
        <%!    /*
             * Declarations
             */
            Connection connection;
            String queryString;
            //StringBuffer queryString;
            String strTmp;
            String strSortCol;
            String strSortOrd;
            String userId;
            String submittedFrom;
            String action;
            //new
            String userRoleName;
            int role;
            int intSortOrd = 0;
            int intCurr;
            boolean blnSortAsc = true;
        %>


        <!-- Start oif the table -->
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="requirementList"  > Requirement List</a></li>
                                    <!-- <li ><a href="#" rel="searchDiv">Requirement Search</a></li>-->
                                </ul>
                                <div  style="border:1px solid gray; width:845px;height: 530px; overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:tabbedpanel id="attachmentPannel2" cssStyle="width: 845px; height: 530px;padding:10px 5px 5px 5px" doLayout="true"> --%>

                                    <!--//START TAB : -->
                                    <%--  <sx:div id="List" label="" cssStyle="overflow:auto;"> --%>
                                    <div id="requirementList">

                                        <%
                                            if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                            }
                                        %>


                                        <s:form action="" theme="simple" name="frmDBAccActGrid">
                                            <s:hidden name="requirementAdminFlag" id="requirementAdminFlag" value="%{requirementAdminFlag}"/>
                                            <table cellpadding="0" cellspacing="0" align="left" width="100%">

                                                <tr>
                                                    <td>
                                                        <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                                            <tr>
                                                                <td class="headerText" colspan="11">
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                </td>
                                                            </tr>
                                                            <%--  <tr>
                                                                
                                                                  <td class="fieldLabel">Created By :</td>
                                                                  <td>
                                                                      <s:select headerKey="All" headerValue="All" name="createdBy" id="createdBy" list="createdMemebers" cssClass="inputSelect" theme="simple" />
                                                                  </td>
                                                                  <td class="fieldLabel">Assigned To :</td>
                                                                  <td>
                                                                      <s:select headerKey="All" headerValue="All" list="assignedMembers" name="assignedTo" id="assignedTo" cssClass="inputSelect" theme="simple"/>
                                                                  </td>
                                                              </tr> --%>
                                                            <tr>
 
                                                                <td class="fieldLabel">Requirement&nbsp;Id :</td>
                                                                <td>
                                                                    <s:textfield name="requirementId"  id="requirementId" cssClass="inputTextBlue" theme="simple"/>
                                                                </td>
                                                                <td class="fieldLabel">Status Of Requirement :</td>
                                                    <td>   
                                                        <%-- <s:select headerKey="All" headerValue="All" list="{'Open','InProgress','Closed','Lost'}" name="status" id="status" cssClass="inputSelect"/> --%>
                                                        <s:select headerKey="All" headerValue="All" list="{'Forecast','Open','InProgress','Hold','Withdrawn','Won','Lost','Closed'}" name="status" id="status" cssClass="inputSelect" theme="simple" />
                                                    </td>
                                                            </tr>
                                                            <tr>
                                                    
                                                    <td class="fieldLabel">Job Title :</td>
                                                    <td>
                                                        <s:textfield name="title" id="title" cssClass="inputTextBlue" theme="simple"/>
                                                    </td>
                                                    <td class="fieldLabel">Practice :&nbsp;</td>
                                                    <td>
                                                        <s:select headerKey="-1" headerValue="--Please Select--" list="{'SAP','J2EE','Integration','Portals','Commerce','B2B','QA','BI','Microsoft','Others'}" name="practiceId" id="practiceId" cssClass="inputSelect" value="%{currentRequirement.practiceId}"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Assigned By :</td>
                                                    <td>
                                                        <s:select headerKey="All" headerValue="All" list="AssignedByMap" name="assignedBy" id="assignedBy" cssClass="inputSelect" theme="simple"/>
                                                    </td>
                                                    <td class="fieldLabel">Assigned To :</td>
                                                                <td>
                                                                    <s:select headerKey="All" headerValue="All" list="assignedMembers" name="assignedTo" id="assignedTo" cssClass="inputSelect" theme="simple"/>
                                                                </td>
                                                </tr>
                                                            <tr>
                                                                <td class="fieldLabel">PreSales Person :</td>
                                                                <td>
                                                                    <s:select headerKey="" headerValue="All" list="techLeadList" name="preSalesPerson" id="preSalesPerson"  cssClass="inputSelect" theme="simple" />
                                                                </td>
                                                                <%--<td><s:select list="#session.myTeamMap" name="newloginId" id="newloginId"  cssClass="inputSelect" headerValue="select" headerKey="newloginId" theme="simple" /> </td> --%>   
                                                               <%
                                                                    userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                                                                    if (("Sales".equalsIgnoreCase(userRoleName))) {
                                                                %>
                                                                 <td class="fieldLabel"></td>
                                                                <td>
                                                                    <s:hidden value="All" name="createdBy" id="createdBy" />
                                                                </td>
                                                                <%                                    } else {
                                                                %>
                                                               
                                                                <td class="fieldLabel">Created By :</td>
                                                                <td>
                                                                    <s:select headerKey="All" headerValue="All" name="createdBy" id="createdBy" list="createdMemebers" cssClass="inputSelect" theme="simple" />
                                                                </td>
                                                                <%                                        }
                                                                %> 

                                                            </tr>
                                                </tr>
                                                
                                                <tr>




                                                    <td class="fieldLabel">Country :&nbsp;</td>
                                                    <td>
                                                        <s:select headerKey="-1" headerValue="--Please Select--" name="country" id="country" list="{'USA','India','UK','Australia'}" cssClass="inputSelect" value="%{currentRequirement.country}" onchange="getStates(this.form, this.value);"/>
                                                    </td>
                                                    <td class="fieldLabel">State :</td>
    <td>
       <%-- <select name="state" id="state" class="inputSelect"><option value="<s:property value="%{currentRequirement.state}"/>"><s:property value="%{currentRequirement.state}"/></option></select> --%>
       <select name="state" id="state" class="inputSelect"><option value="">--Please Select--</option></select>
       
       
    </td>

                                                </tr>
                                               

                                                <tr>
                                                    <td class="fieldLabel">Date Posted (Start Date):</td>
                                                    <td><s:textfield name="postedDate1" id="postedDate1" cssClass="inputTextBlue" theme="simple" /><a href="javascript:cal1.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                    <td class="fieldLabel">Date Posted (End Date):</td>
                                                    <td><s:textfield name="postedDate2" id="postedDate2" cssClass="inputTextBlue" theme="simple" /><a href="javascript:cal2.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                </tr>
                                                
                                                <tr>

                                                   
	  <s:if test ="#session.requirementByCustomerAccess">
                                                        <td class="fieldLabel">Client:</td>
                                                     <td><s:select headerKey="" headerValue="All" list="clientMap" name="clientId" id="clientId" cssClass="inputSelect" /></td>

                                                    </s:if><s:else>
                                                        <td colspan="2"><s:hidden name="clientId" id="clientId" value=""/></td>
                                                    </s:else>
                                                    
                                                     <td></td>
                                                    <td>
                                                        <%--   <input type="button" value="Search" class="buttonBg" onclick="getSearchReqList();"/> --%>
                                                        <s:if test="%{requirementAdminFlag == 'YES'}">
                                                            <input type="button" value="Search" class="buttonBg" onclick="getAdminSearchReqList();"/>
                                                        </s:if><s:else>
                                                            <input type="button" value="Search" class="buttonBg" onclick="getSearchReqList();"/>
                                                        </s:else>

                                                    </td>
                                                </tr>
                                            </table>  
                                            </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <table width="100%" cellpadding="1" cellspacing="1" border="0">
                                                        <tr>
                                                            <td>
                                                                <div id="loadingMessage12" style="color:red;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>
                                                                <%--<div id="loadingMessage12" style="display:none;" class="error" align="center" ><b><font size="4px" >Loading...Please Wait</font></b></div>--%>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3" >
                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                <table id="tblUpdate1" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='550' align="center">
                                                                    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
                                                                    <COLGROUP ALIGN="left" >
                                                                        <COL width="10">
                                                                        <COL width="200"> 
                                                                        <COL width="200">
                                                                        <COL width="220">
                                                                        <COL width="200">
                                                                        <COL width="10">
                                                                        <COL width="250"> 
                                                                        <COL width="200">
                                                                        <COL width="220">
                                                                        <COL width="200">
                                                                        <COL width="10">
                                                                        <COL width="10">
                                                                </table>
                                                                <br>
                                                                <center><span id="spnFast" class="activeFile"></span></center> 

                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            </table>
                                        </div>
                                    </s:form>


                                    <%--   </sx:div> --%>


                                    <script type="text/JavaScript">
                                        var cal1 = new CalendarTime(document.forms['frmDBAccActGrid'].elements['postedDate1']);
                                        cal1.year_scroll = true;
                                        cal1.time_comp = false;
                                        var cal2 = new CalendarTime(document.forms['frmDBAccActGrid'].elements['postedDate2']);
                                        cal2.year_scroll = true;
                                        cal2.time_comp = false;
                                    </script>

                                    <!-- End search tab -->  
                                    <!-- //END TAB : -->
                                    <%--     </sx:tabbedpanel> --%>

                                    <!-- //END TABBED PANNEL : -->
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
            <!--//END FOOTER : Record for Footer Background and Content-->

        </table>
        <!--//END MAIN TABLE : Table for template Structure-->

        <!--  End of the main table -->        

 <s:if test="%{requirementAdminFlag == 'YES'}">
    <script type="text/javascript">
		$(window).load(function(){
		getRequirementsAdminList1();
		});
		</script>
        </s:if><s:else>
            <script type="text/javascript">
		$(window).load(function(){
		getRequirementsList1();
		});
		</script>
        </s:else>
    </body>
</html>