<%-- 
    Document   : filenotfounderrordisplay
    Created on : Sep 30, 2014, 9:28:49 AM
    Author     : miracle
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%--Start The Page --%>
<html>

<%--Start The Head Section --%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Hubble Organization Portal :: Release Notes</title>
<style type="text/css">
    
</style>

<script src="../includes/javascripts/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="../includes/javascripts/HelpLeftTextAnimation.js" type="text/javascript"></script>
<script src="../includes/javascripts/HelpRightTextAnimation.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
<link href="../includes/css/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link href="../includes/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
</head>
<%--End The Head Section --%>

<%--Start The Body Section --%>
<!-- <body class="bodyGeneral" onLoad="initVars()" oncontextmenu="return false;"> -->
<body class="bodyGeneral" oncontextmenu="return false;">
<%! List errorMsgList=null;%>


<%--//START MAIN TABLE : Table for template Structure--%>

<table class="templateTableReleaseNotes" align="center" cellpadding="0" cellspacing="0" border="1">

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
    <table class="innerTableReleaseNotes" cellpadding="0" cellspacing="0" border="0" background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
    <tr>
        <!--START LEFT MENU -->
        <td width="150px" valign="top" align="center">
            <br><br><br><br><br><br><br><br>
            <div id="hi" style="color: #7E2217">
                <!--   Mirage Help -->
            </div>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            <div id="welcome" style="color: #7E2217">
                <!--   Mirage Help -->
            </div> 
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            <div id="message" style="color: #7E2217">
                <!--   Mirage Help -->
            </div>
            
            
        </td>   
        <!--END LEFt MENU -->
        
        <!--//START DATA COLUMN : Coloumn for Screen Content-->
        <td width="700px" class="" valign="top">
        <!--Start data table -->    
        <table border="0" width="700px" align="center">
            <tr>
            <td valign="top">
                <table width="1000px" align="center" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td valign="top">
                        <div>
                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/releaseNotesHeader_700x150.jpg" width="100%" height="150%" />
                        </div>
                    </td>
                </tr>
                
                                    <tr>
                                        <td>
                                            <sx:tabbedpanel id="ErrorDisplayPannel" cssStyle="width: 845px; height: 300px;padding-left:10px;padding-top:5px;" doLayout="true" >
                                                <sx:div id="Error" label="Error" > 
                                                    <table cellpadding="0" width="100%" cellspacing="0" border="0">  
                                                        
                                                        <tr>
                                                            <td align="center" style="padding-top:100px;"> 
                                                                <span class="fieldLabel">Please click on Release Notes link from Header menu.</span>
                                                                
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
                              
                
                                                                             
    <%--IF you want to add extra rows you enable following rows --%>
    <%-- <tr>
        <td valign="top">&nbsp;</td>
    </tr>
    <tr>
        <td valign="top">&nbsp;</td>
    </tr>
    <tr>
        <td valign="top">&nbsp;</td>
    </tr>--%>
</table></td>
</tr>
</table>   
<!--End data table -->
</td> 
<!--//END DATA COLUMN : Coloumn for Screen Content-->

<!--START RIGHT MENU -->
<td width="150px" align="center" valign="top" background="">
    <br><br><br><br><br><br><br><br>
    <div id="msg2" style="color: #7E2217">
        <!--   Mirage Help -->
    </div>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <div id="msg3" style="color: #7E2217">
        <!--   Mirage Help -->
    </div>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <div id="msg4" style="color: #7E2217">
        <!--   Mirage Help -->
    </div>
</td>   
<!--END RIGHT MENU -->
</tr>
</table>
</td>

</tr>
<%--//END DATA RECORD : Record for LeftMenu and Screen Content--%>

<%--//START FOOTER : Record for Footer Background and Content--%>
<tr class="footerBg">
    <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
</tr>
<%--//END FOOTER : Record for Footer Background and Content--%>

</table> 
<%--//End MAIN TABLE : Table for template Structure--%>
<script type="text/javascript">
<!--
var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel1", {contentIsOpen:false});
var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel2", {contentIsOpen:false});
var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel3", {contentIsOpen:false});
var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel4", {contentIsOpen:false});
var CollapsiblePanel5 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel5", {contentIsOpen:false});
var CollapsiblePanel6 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel6", {contentIsOpen:false});
var CollapsiblePanel7 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel7", {contentIsOpen:false});
//-->
</script>        
<script type="text/javascript">
		$(window).load(function(){
		initVars();

		});
		</script>
</body>
<%--End The Body Section --%>

</html>
<%--End The Page --%>