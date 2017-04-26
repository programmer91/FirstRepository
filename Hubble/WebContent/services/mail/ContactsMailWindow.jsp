<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<html>
    <head>
        <title>Hubble Organization Portal :: Mail Service</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/javascript">
          function createActivity()
          {
          document.mailService.submit();
          }
            
            
        </script>
        
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable705x440" align="center" cellpadding="0" cellspacing="0">
            
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
                                
                                    
                                <!--//START TAB : -->
                                <%!String toValue=null;
                                String toaddress =null;
                                %>
                                <%
                                if(request.getParameter("name") != null){
                                    
                                    toValue =  request.getParameter("name");
                                    if("MailService".equalsIgnoreCase(toValue)){
                                        toValue = "";
                                    }
                                }
                                
                                
                                %>
                                
                                <%--<s:form action="mailAction" theme="simple" enctype="multipart/form-data">--%>
                                <s:form action="contactmailAction" name="mailService" id="mailService" theme="simple" method="post">
                                    <h3 align="center" style="color=#659EC7;font-style=italic;">
                                        HubbleV1 Mail
                                    </h3>
                                    <% if(request.getAttribute("mailMessage") != null){
                                    out.println(request.getAttribute("mailMessage"));
                                    }%> 
                                    
                                    <table width="400px" align="center" border="0" bgcolor="#659EC7" class="cellBorder">
                                        
                                        <tr>
                                            <td class="fieldLabelWhite" width="100px">TO :</td>
                                            <td width="300px">
                                                <%
                                                if(request.getParameter("to") != null){
                                    
                                    toaddress = request.getParameter("to"); 
                                                
                                                %>
                                                <input type="text" name="to"  class="inputTextBlueAddress" value="<%=toaddress%>"/>
                                                <%}else{%>
                                                <input type="text" name="to"  class="inputTextBlueAddress" value=""/>
                                                <% } %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabelWhite">CC :</td>
                                            <td><s:textfield name="cc" size="60" cssClass="inputTextBlueAddress"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabelWhite">BCC :</td>
                                            <td><s:textfield name="bcc" size="60" cssClass="inputTextBlueAddress"/></td>
                                        </tr>
                                        <tr>
                                            <td class="fieldLabelWhite">Subject :</td>
                                            <td><s:textfield name="subject" size="60" cssClass="inputTextBlueAddress"/></td>
                                        </tr>
                                        
                                        <tr>
                                            <td></td>
                                            <td> </td>
                                        </tr>
                                        
                                        <tr>
                                            <td class="fieldLabelWhite" valign="top">Message :</td>
                                            <td><s:textarea name="message" cssClass="inputTextarea" cols="70" rows="4"/></td>
                                        </tr>
                                        
                                        <tr>
                                            <td>
                                                &nbsp;&nbsp;
                                            </td>
                                            <td align="left">
                                                
                                                <a href="mailto:<%=toaddress%>" onclick="createActivity()"  > Send </a>
                                            </td>
                                        </tr>
                                        <%--
                                        <tr>
                                            <td class="fieldLabelWhite">Attachments</td>
                                            <td colspan="0" align="">
                                                <s:file name="upload" label="File" cssClass="inputTextBlueLargeAccount" theme="simple"/>
                                                &nbsp;&nbsp;<s:submit cssClass="buttonBg" value="Send"/>
                                            </td>
                                        </tr>
                                        --%>
                                        <%
                                        if(request.getParameter("accountId") != null){
                                    
                                    String accountId = request.getParameter("accountId");
                                    String contactId = request.getParameter("contactId");      
                                        
                                        %>
                                        <input type="hidden" name="accountId" value="<%=accountId%>"/>
                                        <input type="hidden" name="contactId" value="<%=contactId%>"/>
                                        <%} %>
                                        
                                        
                                        
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
