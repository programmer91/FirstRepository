<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<html>
    <head>
        <title>Hubble Organization Portal :: Mail Service</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script language="javascript" type="text/javascript">
//window.alert("hello");

var mytext = document.getElementById("cc");
mytext.focus();

</script>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable705x440" align="center" cellpadding="0" cellspacing="0" border="0">
            
            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <%-- <tr class="headerBg">
                <td width="135px">
                    <img alt="Mirage" 
                         src="/Struts2Example/includes/images/mirageLogo_135x32.gif" 
                         width="135px" 
                         height="30px">
                </td>
                
            </tr>--%>
            <!--//END HEADER : Record for Header Background and Mirage Logo-->
            
            <!--//START DATA RECORD : Record for LeftMenu and Screen Content-->
            <tr class="mailBackground">
                <td>
                    <table class="innerTable700x440" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <%--<td width="150px;" class="cellBorder" valign="top"> <b><font color="blue" size="2px" >Menu Comes Here</font></b> </td>--%>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td>
                                <!--//START TABBED PANNEL : -->
                                
                                <% 
                                   session.setAttribute(ApplicationConstants.RESUME_REC_ID,request.getParameter("resumeRecId"));
                                   session.setAttribute(ApplicationConstants.RESUME_REQUIREMENT_ID,request.getParameter("resumeRequirementId"));
                                   session.setAttribute(ApplicationConstants.RESUME_CONSULTANT_ID,request.getParameter("resumeConsultantId"));
                                   session.setAttribute(ApplicationConstants.RESUME_ATTACHMENT_ID,request.getParameter("resumeAttachmentId"));
                                %> 
                                
                                <%--<s:form action="mailAction" theme="simple" enctype="multipart/form-data">--%>
                                <s:form action="resumeSubmissionPopup" theme="simple" method="post">
                                    <h3 align="center" style="color=#659EC7;font-style=italic;">
                                        Resume Submission
                                    </h3>

                                    <table width="400px" align="center" border="0" bgcolor="#659EC7" class="cellBorder">
                                        
                                        <tr>
                                            <td class="fieldLabelWhite" width="100px">TO :</td>
                                            <td width="300px"><s:textfield name="customerEmail" id="customerEmail" size="60" cssClass="inputTextBlueAddress"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabelWhite">CC :</td>
                                            <td><s:textfield name="cc" id="cc" size="60" cssClass="inputTextBlueAddress "/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabelWhite">BCC :</td>
                                            <td><s:textfield name="bcc" id="bcc" size="60" cssClass="inputTextBlueAddress"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabelWhite">Subject :</td>
                                            <td><s:textfield name="subject" id="subject" size="60" cssClass="inputTextBlueAddress"/></td>
                                        </tr>
                                        
                                        <tr>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        
                                        <tr>
                                            <td class="fieldLabelWhite" valign="top">Message&nbsp;:</td>
                                            <td><s:textarea name="message" id="messge" cssClass="inputTextarea" cols="70" rows="6"/></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <s:if test="%{isSuccess != 1}">
                                            <tr>
                                                <td>
                                                    &nbsp;&nbsp;
                                                </td>
                                                <td align="left">
                                                    <s:submit cssClass="buttonBg" value="Send"/>
                                                </td>
                                            </tr>
                                        </s:if>
                                          <s:if test="%{isSuccess ==1}">
                                            <tr>
                                                <td>
                                                    &nbsp;&nbsp;
                                                </td>
                                                <td align="left">
                                                    
                                                    <%
                                                    out.println("<font color=\"green\" size=\"1.5\">Consultant Details Sent Successfully ..........!</font>");
                                                    %>
                                                </td>
                                            </tr>
                                        </s:if>
                                        
                                        <s:if test="%{isSuccess == 2}">
                                            <tr>
                                                <td>
                                                    &nbsp;&nbsp;
                                                </td>
                                                <td align="left">
                                                   
                                                    <%
                                                    out.println("<font color=\"red\" size=\"1.5\">Your operation Failed..Please Try again later..!</font>");
                                                    %>
                                                    
                                                </td>
                                            </tr>
                                        </s:if>
                                        <%--
                                        <tr>
                                            <td class="fieldLabelWhite">Attachments</td>
                                            <td colspan="0" align="">
                                                <s:file name="upload" label="File" cssClass="inputTextBlueLargeAccount" theme="simple"/>
                                                &nbsp;&nbsp;<s:submit cssClass="buttonBg" value="Send"/>
                                            </td>
                                        </tr>
                                        --%>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                        </tr>
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
