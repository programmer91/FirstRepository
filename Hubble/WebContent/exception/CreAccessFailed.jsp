<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<html>
    <head>
        <title>Hubble Organization Portal :: Authorization Failed</title>
        
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
                    <s:include value="/includes/template/CreHeader.jsp"/>                    
                </td>
            </tr>
            <tr>
                <td>
                    
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
                        <tr>
                          <%--  <td width="150px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/>                    
                            </td>  --%>
                            <td width="850px" class="cellBorder" valign="top">
                                
                            <sx:tabbedpanel id="AccessFailedPannel" cssStyle="width: 845px; height: 500px;padding-left:10px;padding-top:5px;" doLayout="true" >
                                <sx:div id="AccessFailedTab" label="Access Failed"> 
                                    <table cellpadding="0" cellspacing="0" border="0" align="center">
                                        <tr>
                                            <td style="padding-top:100px;">
                                                <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" width="462" height="284" id="header" align="middle">
                                                    <param name="allowScriptAccess" value="sameDomain" />
                                                    <param name="movie" value="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/flash/accessFailed_462x284.swf" />
                                                    <param name="quality" value="high" />
                                                    <param name="bgcolor" value="#000000" />
                                                    <embed src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/flash/accessFailed_462x284.swf" quality="high" bgcolor="#000000" width="462" height="284" name="header" align="middle" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
                                                </object>                
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
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
        </table>
        <%-- End  of the Main Table --%>
        
    </body>
</html>
