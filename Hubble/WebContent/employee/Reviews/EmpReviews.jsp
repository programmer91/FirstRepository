<%-- 
    Document   : EmpReviews
    Created on : Mar 26, 2015, 4:40:45 PM
    Author     : miracle
--%>

<!-- 
 /* ******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : IssueDetails.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Employee Leaves Applied</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <!-- issues Related JavaScript  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/AjaxReviewList.js"/>"></script>
       <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script> 
        
        <s:include value="/includes/template/headerScript.html"/>
        
    </head>
   <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getTotalReviews();"> -->
        <body class="bodyGeneral" oncontextmenu="return false;">
        <s:hidden name="userId" id="userId" value="%{userId}"/>
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="empSearchTab"> Reviews Report</a></li>
    					
                                </ul>
                                <%-- <sx:tabbedpanel id="empSearch" cssStyle="width: 845px; height: 500px;padding:10px 5px 5px 5px" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:div id="empSearchTab" label="Employee Search" cssStyle="overflow: auto;"> --%>
                                    <div id="empSearchTab" class="tabcontent"  >
                                        <s:form action="" theme="simple" name="searchForm">
                                            <table cellpadding="0" border="0" cellspacing="0" width="100%" >
                                                <tr>
                                                   <td class="headerText" colspan="3" align="right">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                         <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td class="fieldLabel">Employee&nbsp;Name</td><td colspan="2"><font color="green" size="2px"><s:property value="%{employeeName}"/></font></td>
                                                </tr>
                                                <tr>
                                                            <td class="fieldLabel" >Review Type :<FONT color="red"  ><em>*</em></FONT> </td>
                                                            <td colspan="2"> <s:select id="reviewType"  name="reviewType"  list="searchReviewMap" headerKey="0" headerValue="All"   value="" cssClass="inputSelect" disabled="False"/><input type="button" value="Search" class="buttonBg" onclick="getTotalReviews();"/></td>
                                                           <%--  <td ><input type="button" value="Search" class="buttonBg" onclick="getTotalReviews();"/></td> --%>
                                                </tr>
                                                <tr>
                                                    <td colspan="3">
                                                        <div id="loadingMessage" style="display:none;" class="error" align="center" ><b><font size="4px" >Loading...Please Wait</font></b></div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3">
                                                        <br/>
                                                         <fieldset>
                                                             <legend style="color: green"> Training  & Project  <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Which displays employee working state hsitory',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20"></legend>
                                                        <table cellpadding="2" cellspacing="1" width='745' class="gridTable" align="center">
                                <tr class="gridHeader">
                                <%--    <td width="4%" class="gridHeader" ALIGN="right">Edit</td> --%>
                                    <td class="gridHeader" ALIGN="center">State</td>
                                    <td class="gridHeader">StartDate</td>
                                    <td  class="gridHeader">EndDate</td>
                                  <%--  <td width="8%" class="gridHeader">Int.Rate/Hr</td>
                                    <td width="8%" class="gridHeader">Inv.Rate/Hr</td> --%>
                                    <td  class="gridHeader">Skill Set</td>
                                    <td  class="gridHeader">Project</td>
                                 <%--   <td width="18%" class="gridHeader">Created Date</td> --%>
                                    <%-- <td width="18%" class="gridHeader">Delete</td> --%>
                                </tr>
                                <s:iterator value="#request.currentStateHistoryCollection">
                                    <tr class="gridRowEven">
                                       <%-- <td class="gridColumn" align="left"><a href="getEmployee.action?empId=<s:property value="EmpId"/>&currId=<s:property value="id"/>&navId=1"><img src="../../includes/images/DBGrid/newEdit_17x18.png"></a></td> --%>
                                        <td class="gridColumn" align="left"><s:property value="empState"/></td>
                                        <td class="gridColumn" align="left"><s:property value="stateStartDate"/></td>
                                        <td class="gridColumn" align="left"><s:property value="stateEndDate"/></td>
                                      <%--  <td class="gridColumn" align="left"><s:property value="intRatePerHour"/></td>
                                        <td class="gridColumn" align="left"><s:property value="invRatePerHour"/></td> --%>
                                      
                                           <%-- <s:property value="skillSet"/> --%>
                                         <td class="gridColumn" align="left"><a href="#" onclick="getSkillSet(<s:property value="EmpId"/>,<s:property value="id"/>);">Click To View</a></td>    
                                        
                                        <td class="gridColumn" align="left"><s:property value="prjName"/></td>
                                   <%--     <td class="gridColumn" align="left"><s:property value="createdDate"/></td> --%>
                                      <%--  <td class="gridColumn" align="left"><a href="deleteEmpStatus.action?empId=<s:property value="EmpId"/>&currId=<s:property value="id"/>"><img src="../../includes/images/DBGrid/Delete.png"></a></td> --%>
                                    </tr>
                                </s:iterator>
                            <%--    <tr class="gridPager">
                                    <td colspan="5" class="gridFooter">&nbsp;</td>
                                </tr> --%>
                            </table>
                                                         </fieldset>            </td>
                                                </tr>
                                                <tr><td colspan="3"><br>
                                                         <fieldset>
                                                             <legend style="color: green"> Performance reviews <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" onmouseover="fixedtooltip('Which displays employee performance reviews from last revison date to next revision date',this,event, 200,-20,40)" onmouseout="delayhidetip()" height="18" width="20"></legend>
                                                        <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                        <table id="tblUpdate" align="center"  
                                                               cellpadding='1' cellspacing='1' border='0' class="gridTable" width='700'>
                                                           <COLGROUP ALIGN="left" >
                                                            <COL width="10">
                                                            <COL width="250"> 
                                                            <COL width="200">
                                                            <COL width="100">
                                                            <COL width="100">
                                                            <COL width="100">
                                                            <COL width="220">
                                                            <COL width="200">
                                                            
                                                        </table>
                                                         </fieldset>
                                                        <br>
                                                        <center><span id="spnFast" class="activeFile"></span></center>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                        <%--  </sx:div> --%>
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
          <script type="text/javascript">
		$(window).load(function(){
             getTotalReviews();
		});
		</script>
    </body>
</html>