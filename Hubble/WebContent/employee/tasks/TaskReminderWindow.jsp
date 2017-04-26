<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<html>
    <head>
        <title>Hubble Organization Portal :: Mail Service</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript">
            function validateREMform(){
           // alert("hiiii");
            var msg = document.getElementById("message").value;
            
             if(msg.length <= 2 ){
               alert("Please enter Commetnts!!");
               return false;
             }
             return true;
            }

        </script>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="innerTable0x400" align="center" cellpadding="0" cellspacing="0" border="0">
            
            <!--//START HEADER : Record for Header Background and Mirage Logo-->
       
            <!--//END HEADER : Record for Header Background and Mirage Logo-->
            
            <!--//START DATA RECORD : Record for LeftMenu and Screen Content-->
            <tr class="mailBackgroundForIssueReminder">
                <td>
                    <table class="innerTable0x400" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                   <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td>
                                <!--//START TABBED PANNEL : -->
                                <!--//START TAB : -->
                                
                                <% session.setAttribute(ApplicationConstants.TASK_REMINDER_Id, request.getParameter("issueid"));
                                %>
                                
                                <s:form action="taskReminderPopup" theme="simple" method="post" onsubmit="return validateREMform();">
                                    <h3 align="center" style="color=#659EC7;font-style=italic;">
                                        Task Reminder 
                                    </h3>
                                    
                                    <table width="0px" align="top" border="0" bgcolor="#659EC7" class="cellBorder">

                                        <tr>
                                            <td class="fieldLabelWhite" valign="top">Comments&nbsp;:&nbsp;</td>
                                            <td><s:textarea name="message" id="message" cssClass="inputTextarea" cols="70" rows="4"/></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <s:if test="%{mailsent != 1}">
                                            <tr>
                                                <td>
                                                    &nbsp;&nbsp;
                                                </td>
                                                <td align="left">
                                                    <s:submit cssClass="buttonBg" value="Send"/>
                                                </td>
                                            </tr>
                                        </s:if>
                                        <s:if test="%{mailsent == 1}">
                                            <tr>
                                                <td>
                                                    &nbsp;&nbsp;
                                                </td>
                                                <td align="left">
                                                    
                                                    <%
                                                    out.println("<font color=\"green\" size=\"1.5\">Your Reminder Sent Successfully..........!</font>");
                                                    %>
                                                </td>
                                            </tr>
                                        </s:if>
                                        
                                        <s:if test="%{mailsent == 2}">
                                            <tr>
                                                <td>
                                                    &nbsp;&nbsp;
                                                </td>
                                                <td align="left">
                                                   
                                                    <%
                                                    out.println("<font color=\"red\" size=\"1.5\">Your Message Mail operation Failed..Please Try again later..!</font>");
                                                    %>
                                                    
                                                </td>
                                            </tr>
                                        </s:if>

                                        <tr>
                                            <td colspan="2" align="center"></td>
                                        </tr>
                                    </table>
                                </s:form>
                                <!--//END TAB : -->
                                    
                                
                                <!--//END TABBED PANNEL : -->
                                
                            </td>
                            <!--//END DATA COLUMN : Coloumn for Screen Content-->
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//END DATA RECORD : Record for LeftMenu and Body Content-->
            
            <!--//START FOOTER : Record for Footer Background and Content-->
            <%--<tr class="footerBg">
                <td align="center">&reg; 2007  Mirage - All Rights Reserved.</td>
            </tr>--%>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>
</html>