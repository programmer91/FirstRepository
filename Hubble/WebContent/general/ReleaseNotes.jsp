<%-- 
    Document   : ReleaseNotes
    Created on : Sep 17, 2014, 9:40:19 AM
    Author     : miracle
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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

<style> 
#myDIV {   
    -webkit-animation: mymove 5s infinite; /* Chrome, Safari, Opera */
    animation: mymove 2s infinite;
}

/* Chrome, Safari, Opera */
@-webkit-keyframes mymove {
    50% {font-weight: bold;}
}

/* Standard syntax */
@keyframes mymove {
    50% {font-weight: bold;}
}
</style>

</head>
<%--End The Head Section --%>

<%--Start The Body Section --%>
<!-- <body class="bodyGeneral" onLoad="initVars()" oncontextmenu="return false;"> -->
<body class="bodyGeneral" oncontextmenu="return false;">



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
                    <td valign="top">
                        <div id="CollapsiblePanel1" class="CollapsiblePanel">
                            <div class="CollapsiblePanelTab" tabindex="0">
                                <table width="1000" align="center" border="0" cellspacing="0" cellpadding="6">
                                    <tr> 
                                        <td width="50" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ReleaseVer_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactReleaseV">&nbsp;&nbsp;Hubble V2014.3  <font color="#FFFFFF">:</font> <font face="Sans-serif" color="#228B22">(09-17-2014)</font></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="CollapsiblePanelContent">
                                <table width="1000" align="center" border="0" cellspacing="0" cellpadding="6">
                                    
                                    <tr>
                                        <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ReleaseDesc_20x19.jpg" width="20" height="19" /></td>
                                        <td width="642" valign="top" class="fieldLabelContactReleaseD">
                                          <table border="0" cellpadding="0" cellspacing="6">
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactReleaseD"><font size="4px" color="#800080"><b>Forgot Password :</b></font> 
                                                      
                                                    </td>                                                    
                                                </tr>
                                                <tr>                                                   
                                                    <td valign="top" class="fieldLabelContactReleaseD"><font size="3px" color="#4E7D42"><b>Purpose :</b></font>
                                                    To Reset the password for both Employee and Customer.                                                   
                                                    <%-- <s:url id="fileDownload" action="download?path=/usr/local/ProjectFiles/ReleaseFiles/ForgotPassword.doc" ></s:url> --%>
                                                    <s:url id="fileDownload" action="download?path=C:\\usr\\local\\ProjectFiles\\ReleaseFiles\\ForgotPassword.doc" ></s:url>                                                     
                                                    <s:a href="%{fileDownload}" style="text-decoration:none;"><font id="myDIV" size="2px" color="#6863F3">read more...</font></s:a>
                                                    </td>
                                                    
                                                </tr>                                                                                                
                                        </table>
                                        </td>
                                    </tr>
                                     <tr>
                                        <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ReleaseDesc_20x19.jpg" width="20" height="19" /></td>
                                        <td width="642" valign="top" class="fieldLabelContactReleaseD">
                                          <table border="0" cellpadding="0" cellspacing="6">
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactReleaseD"><font size="4px" color="#800080"><b>Tasks Functionality :</b></font> 
                                                      
                                                    </td>                                                    
                                                </tr>
                                                <tr>                                                   
                                                    <td valign="top" class="fieldLabelContactReleaseD"><font size="3px" color="#4E7D42"><b>Purpose :</b></font>
                                                    This functionality is used for assigning tasks to an employee.
                                                    <%-- <s:url id="fileDownload" action="download?path=/usr/local/ProjectFiles/ReleaseFiles/TasksFunctionality.doc" ></s:url> --%>
                                                    <s:url id="fileDownload" action="download?path=C:\\usr\\local\\ProjectFiles\\ReleaseFiles\\TasksFunctionality.doc" ></s:url>                                                     
                                                    <s:a href="%{fileDownload}" style="text-decoration:none;"><font id="myDIV" size="2px" color="#6863F3">read more...</font></s:a>
                                                    </td>
                                                </tr>                                                                                                 
                                        </table></td>
                                    </tr>
                                    
                                     <tr>
                                        <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ReleaseDesc_20x19.jpg" width="20" height="19" /></td>
                                        <td width="642" valign="top" class="fieldLabelContactReleaseD">
                                          <table border="0" cellpadding="0" cellspacing="6">
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactReleaseD"><font size="4px" color="#800080"><b>Tech Reviews Functionality :</b></font> 
                                                      
                                                    </td>                                                    
                                                </tr>
                                                <tr>                                                   
                                                    <td valign="top" class="fieldLabelContactReleaseD"><font size="3px" color="#4E7D42"><b>Purpose :</b></font>
                                                    This is used for giving reviews to consultant by the respective techie.
                                                    <%-- <s:url id="fileDownload" action="download?path=/usr/local/ProjectFiles/ReleaseFiles/TechReviewsFlow.doc" ></s:url> --%>
                                                    <s:url id="fileDownload" action="download?path=C:\\usr\\local\\ProjectFiles\\ReleaseFiles\\TechReviewsFlow.doc" ></s:url>                                                     
                                                    <s:a href="%{fileDownload}" style="text-decoration:none;"><font id="myDIV" size="2px" color="#6863F3">read more...</font></s:a>
                                                    </td>
                                                </tr>                                                                                                 
                                        </table></td>
                                    </tr>
                                    
                                    
                                </table>
                            </div>
                                                    
                      </div>
                    </td>
                </tr>
                <tr>
                    <td valign="top"><div id="CollapsiblePanel2" class="CollapsiblePanel">
                            <div class="CollapsiblePanelTab" tabindex="0">
                                <table width="1000"  border="0" cellspacing="0" cellpadding="6">
                                    <tr> 
                                        <td width="50" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ReleaseVer_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactReleaseV">&nbsp;&nbsp;Hubble V2014.2 <font color="#FFFFFF">:</font> <font face="Sans-serif" color="#228B22">(08-17-2014)</font></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="CollapsiblePanelContent">
                                
                                <table width="1000" align="center" border="0" cellspacing="0" cellpadding="6">
                                   
                                     
                                    <tr>
                                        <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ReleaseDesc_20x19.jpg" width="20" height="19" /></td>
                                        <td width="642" valign="top" class="fieldLabelContactReleaseD">
                                          <table border="0" cellpadding="0" cellspacing="6">
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactReleaseD"><font size="4px" color="#800080"><b>Time sheets functionality changes:</b></font> 
                                                      
                                                    </td>                                                    
                                                </tr>
                                                <tr>                                                   
                                                    <td valign="top" class="fieldLabelContactReleaseD"><font size="3px" color="#4E7D42"><b>Purpose :</b></font>
                                                    This functionality provides project based time sheets adding.
                                                    <%-- <s:url id="fileDownload" action="download?path=/usr/local/ProjectFiles/ReleaseFiles/TimeSheets.doc" ></s:url> --%>
                                                    <s:url id="fileDownload" action="download?path=C:\\usr\\local\\ProjectFiles\\ReleaseFiles\\TimeSheets.doc" ></s:url>                                                     
                                                    <s:a href="%{fileDownload}" style="text-decoration:none;"><font id="myDIV" size="2px" color="#6863F3">read more...</font></s:a>
                                                    </td>
                                                </tr>                                                                                            
                                        </table></td>
                                    </tr>                                   
                                    
                                    
                                </table>
                                
                                
                                
                            </div>
                    </div></td>
                </tr>
                <tr>
                    <td valign="top"><div id="CollapsiblePanel3" class="CollapsiblePanel">
                            <div class="CollapsiblePanelTab" tabindex="0">
                                <table width="1000" align="center" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="50" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ReleaseVer_20x19.jpg" width="20" height="19" /></td>
                                        <td valign="top" class="fieldLabelContactReleaseV">&nbsp;&nbsp;Hubble V2014.1  <font color="#FFFFFF">:</font> <font face="Sans-serif" color="#228B22">(07-17-2014)</font></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="CollapsiblePanelContent">
                                <table width="1000" align="center" border="0" cellspacing="0" cellpadding="6">
                                    <tr>
                                        <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ReleaseDesc_20x19.jpg" width="20" height="19" /></td>
                                        <td width="642" valign="top" class="fieldLabelContactReleaseD">
                                          <table border="0" cellpadding="0" cellspacing="6">
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactReleaseD"><font size="4px" color="#800080"><b>Activity Manager DashBoard Functionality :</b></font> 
                                                      
                                                    </td>                                                    
                                                </tr>
                                                <tr>                                                   
                                                    <td valign="top" class="fieldLabelContactReleaseD"><font size="3px" color="#4E7D42"><b>Purpose :</b></font>
                                                    This functionality provides list of accounts with activity details.
                                                    <%-- <s:url id="fileDownload" action="download?path=/usr/local/ProjectFiles/ReleaseFiles/ActivityManagerDashBoard.doc" ></s:url> --%>
                                                    <s:url id="fileDownload" action="download?path=C:\\usr\\local\\ProjectFiles\\ReleaseFiles\\ActivityManagerDashBoard.doc" ></s:url>                                                     
                                                    <s:a href="%{fileDownload}" style="text-decoration:none;"><font id="myDIV" size="2px" color="#6863F3">read more...</font></s:a>
                                                    </td>
                                                </tr>                                                                                                 
                                        </table></td>
                                    </tr>
                                    
                                     <tr>
                                        <td width="32" align="center" valign="top"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ReleaseDesc_20x19.jpg" width="20" height="19" /></td>
                                        <td width="642" valign="top" class="fieldLabelContactReleaseD">
                                          <table border="0" cellpadding="0" cellspacing="6">
                                                <tr>
                                                    <td valign="top" class="fieldLabelContactReleaseD"><font size="4px" color="#800080"><b>Requirement DashBoard Functionality :</b></font> 
                                                      
                                                    </td>                                                    
                                                </tr>
                                                <tr>                                                   
                                                    <td valign="top" class="fieldLabelContactReleaseD"><font size="3px" color="#4E7D42"><b>Purpose :</b></font>
                                                    This functionality provides required information to Requirement. 
                                                    <%-- <s:url id="fileDownload" action="download?path=/usr/local/ProjectFiles/ReleaseFiles/RequirementDashBoard.doc" ></s:url> --%>
                                                     <s:url id="fileDownload" action="download?path=C:\\usr\\local\\ProjectFiles\\ReleaseFiles\\RequirementDashBoard.doc" ></s:url>                                                     
                                                     <s:a href="%{fileDownload}" style="text-decoration:none;"><font id="myDIV" size="2px" color="#6863F3">read more...</font></s:a>
                                                    </td>
                                                </tr>                                                                                                
                                        </table></td>
                                    </tr>
                                </table>
                            </div>
                    </div></td>
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