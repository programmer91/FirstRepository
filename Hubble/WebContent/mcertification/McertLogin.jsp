<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>



<%--
 author:raja reddy andra
 email:randra@miraclesoft.com
--%>
<html>
    <head>
        <title>Hubble Organization Portal :: Consultant Login </title>
        <%--This link for ToolTip css --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <%--This is End for ToolTip css --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/mcertification/McertClientLoginValidation.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <%--This link for ToolTip js --%>
        
        <%--This End for ToolTip js --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/clock.css"/>">
        
       
        
    </head>
    <%--<body class="bodyGeneral"  oncontextmenu="return false;" onload="document.userLoginForm.userId.focus();"> --%> <!-- onload="clock();" -->
        <!--//START MAIN TABLE : Table for template Structure-->
        <body class="bodyGeneral"  oncontextmenu="return false;" onload="document.consultantLoginForm.loginId.focus();">
        <table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0">
            
            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/McertHeader.jsp"/>
                </td>
            </tr>
            
            <!--//END HEADER : Record for Header Background and Mirage Logo-->
            
            <!--//START DATA RECORD : Record for LeftMenu and Screen Content-->
            <tr>
                <td align="center">
                    <table border="0" width="350px;" cellpadding="2" cellspacing="2">
                        <tr>
                            <tr>
                                <td>
                                <s:form action="mcertLoginCheck.action" method="post" name="consultantLoginForm" id="consultantLoginForm" theme="simple" onsubmit="return checkMcertLoginForm();"> 
                                   <%--  <s:form action="/general/loginCheck.action" method="post" name="userLoginForm" id="userLoginForm" theme="simple" onsubmit="return popUp();"> --%>
                                   
                                        <table id="login" bgcolor="#ffffff" border="0" height="150px" width="340px;" align="left" cellpadding="0" cellspacing="2" class="border">
                                           
                                           
                                          
                                            <tr>
                                                <td colspan="2" align="center">
                                                    <% 
                                                   
                                                    if(request.getAttribute("mcertReqErrorMessage") != null){
                                                        
                                                        out.println(request.getAttribute("mcertReqErrorMessage"));
                                                    }%>
                                                    
                                                </td>
                                            </tr>
                                            <%--   <tr>
                                                <td align="center" colspan="2">
                                                    <a href="<s:url value="/cre/general/creregistration.action"/>" cssClass="noUnderLine" onmouseover="fixedtooltip('<b>Get Registered Here To Access CRE .</b>',this,event, 150,2,-60)" onmouseout="delayhidetip()">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/selfRegistration_96x16.gif" border="0">
                                                    </a>
                                                
                                                 </td>
                                            </tr> --%>
                                            
                                            
                                        </table>
                                        
                                    </s:form>
                                    
                                </td>
                            </tr>
                            
                            
                            <td align="center" colspan="2">
                                <% if(request.getAttribute("resultMessage") != null){
                                                        out.println(request.getAttribute("resultMessage"));
                                }%>
                                
                            </td>
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
    </body>
</html>
