<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<html>
    <head>
        <title>Hubble Organization Portal :: Error Information</title>
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
         <sx:head cache="true"/>
        
    </head>
    
    <body class="bodyGeneral" oncontextmenu="return false;">
        <%! List errorMsgList=null;%>
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>                    
                </td>
            </tr>
            <tr>
                <td>
                    
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="150px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/>                    
                            </td>
                            <td width="850px" class="cellBorder" valign="top">
                                
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <tr>
                                        <td class="borderBottom">
                                            <img alt="Error Header" src="<s:url value="/includes/images/errorHeader_847x150.jpg"/>" width="847" height="150" border="0">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <sx:tabbedpanel id="ErrorDisplayPannel" cssStyle="width: 845px; height: 300px;padding-left:10px;padding-top:5px;" doLayout="true" >
                                                <sx:div id="Error" label="Error" > 
                                                    <table cellpadding="0" width="100%" cellspacing="0" border="0">  
                                                        
                                                        <tr>
                                                            <td align="center" style="padding-top:100px;"> 
                                                                <span class="fieldLabel">Please select your choice from left menu.</span>
                                                                
                                                                Exception is: <%=session.getAttribute("errorMessage")%>
                                                                <%
                                                                session.removeAttribute("errorMessage");
                                                                %>
                                                                
                                                                <% 
                                                                if(request.getAttribute("errorMsgList")!=null) {
                                                                errorMsgList=(List)request.getAttribute("errorMsgList");
                                                                request.removeAttribute("errorMsgList");
                                                                %>
                                                                <table bordercolor="#efefef" border="1" class="cellBorder" align="center">
                                                                    <tr>
                                                                        <td ><b> Messgae :</b></td>
                                                                        <td><%=(String)errorMsgList.get(0) %></td>
                                                                    </tr> 
                                                                    <tr>
                                                                        <td><b>Exception Type :</b></td>
                                                                        <td><%=(String)errorMsgList.get(1) %></td>
                                                                    </tr> 
                                                                    <tr>
                                                                        <td><b> Line Number :</b></td>
                                                                        <td><%=(String)errorMsgList.get(2) %></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><b>Exception Occour Location :</b></td>
                                                                        <td><%=(String)errorMsgList.get(3) %></td>
                                                                    </tr>
                                                                    
                                                                </table> 
                                                                <% } %>
                                                                
                                                            </td>      
                                                        </tr>
                                                    </table>
                                                </sx:div>
                                            </sx:tabbedpanel>
                                        </td>
                                    </tr>
                                </table>
                                
                            </td>
                            
                        </tr>
                    </table>
                    
                </td>
            </tr>
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
        </table>
        <%-- End  of the Main Table --%>
        
    </body>
</html>
