<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>


<%--
    author: Ajay Kumar Tummapala
    email :atummapala@miraclesoft.com
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    
    <%-- START HEAD SECTION --%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <title>Hubble Organization Portal :: Forgot Password</title>
        
        <script type="text/JavaScript">
            function checkFields() {
                
                var loginId = document.getElementById("loginId").value;
                var answer = document.getElementById("prefAnswer").value;
                var question = document.getElementById("prefQuestionId").value;
                if(question == '-1' || answer == '' || loginId == '') {
                    alert('Enter Mandatory Fields');
                    return false;
                }else return true;
            }
        </script>
    </head>
    <%-- END HEAD SECTION --%>
    
    <%-- START BODY SECTION --%>
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <%--//START MAIN TABLE : Table for template Structure--%>
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0" border="0">
            
            <%--//START HEADER : Record for Header Background and Mirage Logo--%>
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/CreHeader.jsp"/>
                </td>
            </tr>
            <%--//END HEADER : Record for Header Background and Mirage Logo--%>
         
            <%--//START DATA RECORD : Record for LeftMenu and Screen Content--%>
            <tr>
                <td>
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0" border="0">
                       
                        <tr>
                            <td class="topBorder" height="400px" valign="top" background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
                                <br><br>
                                <table cellpadding="0" cellspacing="0" border="0" width="500px" align="center"  class="cellBorder">
                                    <tr>
                                        <td valign="top">
                                            <table width="500px" cellpadding="2" cellspacing="2" border="0" align="center">
                                                <!--START ANIMATED TEXT ROW -->
                                                <tr>
                                                    <td colspan="2"><h3 class="bgColor" align="center"><script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/SuccessMessageAnimation.js"/>"></script>
                                                            
                                                        </h3>
                                                    </td>
                                                </tr>
                                                <!--END ANIMATED TEXT ROW -->
                                                <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Please copy your login Id.</font></td>
                                                </tr>
                                                
                                                <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Your Login ID :  <% if(request.getAttribute("resultMessage") != null){
                                                        out.println(request.getAttribute("resultMessage").toString().trim());
                                                        }%> </font></td>
                                                </tr>
                                                <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Click Here to <a class="navigationText" href="<s:url value="../general/crelogin.action"/>">Login</a> </font></td>
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <%--//END FOOTER : Record for Footer Background and Content--%>

        </table>
        <%--//START MAIN TABLE : Table for template Structure--%>

    </body>
    <%-- END BODY SECTION --%>
    
</html>