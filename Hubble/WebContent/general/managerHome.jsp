<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants" %>
<%@ taglib uri="/WEB-INF/tlds/dataselect.tld" prefix="sql"%>
<%--
    author: Venkateswara  rao Nukala
    email vnukala@miraclesoft.com
--%>
<html>
    
    <%-- START HEAD SECTION --%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/HomeAjaxUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ProjectsUtil.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
        <title>Hubble Organization Portal :: RoleSelect</title>
    </head>
    <%-- END HEAD SECTION --%>
    
    <%-- START BODY SECTION --%>
    <body class="bodyGeneral" onload="getTotalUsableTeamHours(),getCurrentEvents()" oncontextmenu="return false;">
        
        <%--//START MAIN TABLE : Table for template Structure--%>
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0" border="0">
            
            <%--//START HEADER : Record for Header Background and Mirage Logo--%>
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>                    
                </td>
            </tr>
            <%--//END HEADER : Record for Header Background and Mirage Logo--%>

            <%--//START DATA RECORD : Record for LeftMenu and Screen Content--%>
            <tr>
                <td>
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <%--START DATA --%>
                            <td width="1000px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                
                                <tr>
                                    <td colspan="3">
                                        <s:form action="roleSubmit" theme="simple">
                                            <table border="0"cellpadding="0" cellspacing="0" align="right">
                                                <tr>
                                                    <td align="right" width="450px" class="fieldLabel">Role :</td>
                                                    <td align="left">
                                                        <% /* Retrieving Id from request Object  */
                                                        String empId = session.getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                                                        %>
                                                        <sql:conn>
                                                            <sql:select table="tblEmpRoles" id="<%=empId%>">
                                                                <sql:field  name="RoleId" />
                                                                
                                                            </sql:select>
                                                        </sql:conn>
                                                        &nbsp;&nbsp; <s:submit value="Continue" cssClass="buttonBg"/>
                                                    </td>
                                                    
                                                </tr>
                                            </table>
                                        </s:form> 
                                    </td>
                                </tr>
                                
                                <tr>
                                    <table cellpadding="0" cellspacing="0" width="100%" border="0" align="center" style="padding-top=50px;">
                                        <tr>
                                            <td>
                                                <div class="portletTitleBar">
                                                    <div class="portletTitleLeft">Dear MANAGER welcome to HubbleV1</div>								
						</div>
                                                <br><br>
                                            </td>
                                        </tr>
                                        <tr valign="middle">
                                            <td valign="middle">
                                               <table align="center" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='300'>
                                                    <tr class="gridHeader"><td colspan=3 align=center><b>Team Utilization Values</b></td></tr>
                                                    <tr><td class="gridRowEven">Total Usable Team hours </td><td id="totalHours" class="gridRowEven"></td><td class="gridRowEven">YTD</td></tr>
                                                    <tr><td class="gridRowEven">Total Used Team hours</td><td class="gridRowEven">${usedTeamHours}</td><td class="gridRowEven">YTD</td></tr>
                                                    <tr><td class="gridRowEven">Total Ntaural Holiday hours </td><td class="gridRowEven">${naturalHolidayHours}</td><td class="gridRowEven">YTD</td></tr>
                                                    <tr><td class="gridRowEven">Total Team Leave hours </td><td class="gridRowEven">${teamLeaveHours}</td><td class="gridRowEven">YTD</td></tr>
                                                </table>
                                               <br>
                                               <br>
                                                <table align="center">
                                                    <tr>
                                                        <td><b>Today Activities</b></td>
                                                    </tr>
                                                </table>
                                                <!--<table border=1 align="center" style="border-width: 4px 4px 4px 4px;border-spacing: 2px;border-style: double double double double;border-color: black black black black;border-collapse: separate;">
                                                    <tr><th>Project Name</th><th>Completion Percentage</th><th>Completion Due Date</th><th>Other Info</th></tr>
                                                    <tr><td align=center>Project Alpha</td><td align=center>70%</td><td align=center>3-March-2009</td><td>Caution 1</td></tr>
                                                    <tr><td align=center>Project Xeta</td><td align=center>40%</td><td align=center>30-March-2009</td><td>Caution 3</td></tr>
                                                    <tr><td align=center>Project Pilabus</td><td align=center>50%</td><td align=center>17-Feb-2009</td><td>Caution 2</td></tr>
                                                </table>-->
                                                <s:hidden id="uName" name="uName" value="%{userId}"/>
                                                <table id="eventsList1" align="center" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='750'>
                                                    <tbody id="eventsList"></tbody>
                                                    <!--<tr><th bgcolor=#a0a00a>Date</th><th bgcolor=#a0a00a>Start Time</th><th bgcolor=#a0a00a>End Time</th><th bgcolor=#a0a00a>Activity Type</th><th bgcolor=#a0a00a>Description</th><th bgcolor=#a0a00a>Requsted by</th><th bgcolor=#a0a00a>Status</th></tr>-->
                                                    
                                                </table>
                                                
                                            </td>
                                        </tr>
                                    </table>
                                </tr>
                            </td>
                        </tr>
                    </table>
                </td>  
            </tr>
            <%--//START FOOTER : Record for Footer Background and Content--%>
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <%--//END FOOTER : Record for Footer Background and Content--%>

        </table>
        <%--//START MAIN TABLE : Table for template Structure--%>
        <script type="text/javascript">
		$(window).load(function(){
		getTotalUsableTeamHours();
		getCurrentEvents();

		});
		</script>
    </body>
    <%-- END BODY SECTION --%>
    
</html>