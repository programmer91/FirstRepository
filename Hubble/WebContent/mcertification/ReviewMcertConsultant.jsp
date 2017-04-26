<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.mss.mirage.util.DataSourceDataProvider"%>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<html>
<head>
    <title>Hubble Organization Portal :: Updating Employee Details</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
       <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script> 
  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/mcertification/mcertificationAjax.js"/>"></script>
    <s:include value="/includes/template/headerScript.html"/>
    
     
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
    
</head>
<body  class="bodyGeneral" oncontextmenu="return false;" >

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
                   
            <%  
           Map rolesMap =  (Map)session.getAttribute(ApplicationConstants.SESSION_MY_ROLES);
            
            %>
            <!--//START DATA COLUMN : Coloumn for Screen Content-->
            <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px 5px 5px;">
                <ul id="accountTabs" class="shadetabs" >
                    <li ><a href="#" class="selected" rel="examReview"  >Exam Review</a></li>
                 </ul>
                <!--//START TABBED PANNEL : -->
                                
                <!--//START TABBED PANNEL : -->
                <%--    <sx:tabbedpanel id="employeeUpdatePannel" cssStyle="width:850px; height:510px;padding: 5px 5px 5px 5px;" doLayout="true" useSelectedTabCookie="true">--%>
                <div  style="border:1px solid gray; width:850px;height: 480px; overflow:auto; margin-bottom: 1em;">
                <!--//START TAB : -->
                <div id="examReview" class="tabcontent"  >
                
                
           <table cellpadding="1" cellspacing="1" border="0" width="100%">
                
                <tr>
                    <td class="headerText" colspan="6">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tr align="right">
                        <td valign="middle">
                       <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)"/>
                        </td>
                         </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                   <td class="fieldLabel" width="200px" align="right">Consultant Name:</td>
                    <td><font color="green" style="font-family: lucida-sans;font-size: 12px;"><s:property value="%{consultantName}"/></font></td>
                </tr>
                
                <tr>
                    <tr>
                        <td colspan="6">
                            <table cellpadding="2" cellspacing="1" width="98%" class="gridTable">
                                <tr class="gridHeader">
                                    <td width="4%" class="gridHeader" >ConsultantId</td>
                                 <%--   <td width="6%" class="gridHeader" >Marks</td>
                                    <td width="8%" class="gridHeader">Total Questions</td>
                                    <td width="8%" class="gridHeader">Attempted Questions</td> --%>
                                 <td width="8%" class="gridHeader">ExamName</td>
                                    <td width="8%" class="gridHeader">DateSubmitted</td>
                                    
                                    <td width="8%" class="gridHeader">ExamStatus</td>
                                   <%-- <td width="8%" class="gridHeader">Delete</td>--%>
                                </tr>
                                <s:iterator value="#request.currentExamReviewCollection">
                                    <tr class="gridRowEven">
                                        
                                          <%
                                        String examKeyId = request.getAttribute("examKeyId").toString();
                                        %>
                                      <td class="gridColumn" align="left" onclick=""><a href="javascript:showDetailResult('<%=examKeyId%>');" ><s:property value="creLoginId"/></a></td>                
                                      <%--  <td class="gridColumn" align="left"><s:property value="marks"/></td>
                                        <td class="gridColumn" align="left"><s:property value="totalQuestions"/></td>
                                        <td class="gridColumn" align="left"><s:property value="attemptedQuestions"/></td> --%>
                                       <td class="gridColumn" align="left"><s:property value="examTypeName"/></td>
                                        <td class="gridColumn" align="left"><s:property value="dateSubmitted"/></td>
                                       <td class="gridColumn" align="left"><s:property value="examStatus"/></td>
                                      <%--  <td class="gridColumn" align="left"><a href="javascript:doDeleteTechLeadReview(<s:property value="creId"/>,<s:property value="techReviewId"/>);" ><img src="../includes/images/DBGrid/Delete.png"></a></td>--%>
                                    </tr>
                                </s:iterator>
                                <tr class="gridPager">
                                    <td colspan="8">&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                
                
            </table> 
     
        
                 
   
    <%-- </sx:div > --%>
    </div>
    

                <%-- <sx:div id="personalDetailsTab" label="Employee Details" cssStyle="overflow:auto;" > --%>
                
    
    <!--//END TAB : -->
    
    <%--   <sx:div id="empCurrentState" label="Current Status"  > --%>
    
    
    <!-- Other Details START -->
    <%-- <sx:div id="otherDetailsTab" label="Other Details"> --%>
    
    
    <!-- Onboard review start -->
     
    <!-- Other Details END -->
    
    <!-- Onboard Review End -->
    <!-- Other Details END -->
    
    <%--  </sx:tabbedpanel> --%>
    </div>
    <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(true)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

    </script>
    <!--//END TABBED PANNEL : -->
    
    <!--//END TABBED PANNEL : -->
    
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
</body>
</html>
