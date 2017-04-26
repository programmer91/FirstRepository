<%@page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants" %>
<%@ taglib uri="/WEB-INF/tlds/dataselect.tld" prefix="sql"%>
<%--
    author: raja reddy andra
    email :randra@miraclesoft.com
--%>
<html>
    
    <%-- START HEAD SECTION --%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <title>Hubble Organization Portal :: RoleSelect</title>
    </head>
    <%-- END HEAD SECTION --%>
    
    <%-- START BODY SECTION --%>
    <body class="bodyGeneral" oncontextmenu="return false;">
        
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
                                        <td colspan="3">
                                            <table border="0" height="90px" cellpadding="0" cellspacing="1" width="100%">
                                                <tr>
                                                    <td align="center" width="240px" height="90px" class="borderBlue">
                                                        <font color="#FF9900" size="2px"><u>Operations Executive</u><br></font>
                                                        <font color="#FF9900">Edit Expenses,<br>TravelProfile,Immigration,<br> Records of an Employee.</font>
                                                        
                                                    </td>
                                                    <td align="center" width="300px" height="90px" class="borderHome">
                                                        <font color="#6698FF" size="2px"><u>HR Admin</u><br></font>
                                                        <font color="#6698FF">Create Employee Record and Activities.</font>
                                                    </td>
                                                    <td align="center" width="240px" height="90px" class="borderBlue">
                                                        <font color="#FF9900" size="2px"><u>PayRoll Admin</u><br></font>
                                                        <font color="#FF9900">Edit and Run <br>Payroll Records.</font>
                                                    </td>
                                                    
                                                </tr>
                                            </table> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> 
                                            <table border="0" cellpadding="0" cellspacing="1" align="left" width="250px" height="303px">
                                                <tr>
                                                    <td align="center" width="250px" height="100px" class="borderHome">
                                                        <font color="#6698FF" size="2px"><u>Sales VP</u><br></font><br>
                                                        <font color="#6698FF">Search All Accounts.</font>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center" width="250px" height="100px" class="borderHome">
                                                        <font color="#6698FF" size="2px"><u>Manager</u><br></font><br>
                                                        <font color="#6698FF">Edit Team Appraisals,<br>Expenses,Tasks<br>and Projects.</font> 
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td align="center">
                                             <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/logo.jpg" width="418px" height="299px" border="0">
                                            
                                            <%--
                                            <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" width="418" height="299" id="header" align="middle">
                                                <param name="allowScriptAccess" value="sameDomain" />
                                                <param name="movie" value="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/flash/mirageHome.swf" />
                                                <param name="quality" value="high" />
                                                <param name="bgcolor" value="#000000" />
                                                <embed src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/flash/mirageHome.swf" quality="high" bgcolor="#000000" width="418" height="299" name="header" align="middle" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
                                            </object>
                                            --%>
                                        </td>
                                        <td align="leftt">
                                            <table border="0" align="right" cellpadding="0" cellspacing="1" width="250px" height="303px">
                                                <tr>
                                                    <td align="center" width="250px" height="100px" class="borderHome">
                                                        <font color="#6698FF" size="2px"><u>Sales Admin</u><br></font><br>
                                                        <font color="#6698FF">Edit GreenSheets, <br>PO's and Invoices.</font>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center" width="250px" height="100px" class="borderHome">
                                                        <font color="#6698FF" size="2px"><u>Sales Manager</u><br></font><br>
                                                        <font color="#6698FF">Search his Team Accounts,<br>BDM Revenues,GreenSheets,<br>and Team Activities.</font> 
                                                    </td>
                                                </tr>
                                        </table></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <table border="0" width="999px" height="90px">
                                                <tr>
                                                    <td align="center" width="240px" height="90px" class="borderBlue">
                                                        <font color="#FF9900" size="2px"><u>Sales BDM</u><br></font>
                                                        <font color="#FF9900">Search his Team Accounts,<br>BDM Revenues,GreenSheets<br>Tasks.</font> 
                                                    </td>
                                                    <td align="center" width="300px" height="90px" class="borderHome">
                                                        <font color="#6698FF" size="2px"><u>Sales Inside</u><br></font>
                                                        <font color="#6698FF">Search his Team Accounts,<br>BDM Revenues,GreenSheets<br>Tasks.</font> 
                                                    </td>
                                                    <td align="center" width="240px" height="90px" class="borderBlue">
                                                        <font color="#FF9900" size="2px"><u>Employee:</u><br></font>
                                                        <font color="#FF9900">ResetPassword,SparkPassword,<br>Edit Profile,Projects,Tasks<br>and Raise Trouble Tickets.</font> 
                                                    </td>
                                                </tr>
                                            </table> 
                                        </td>
                                    </tr>
                                </table>
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

    </body>
    <%-- END BODY SECTION --%>
    
</html>