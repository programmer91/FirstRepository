<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<%--
 author:raja reddy andra
 email:randra@miraclesoft.com
--%>
<html>
    <head>
        <title>Hubble Organization Portal :: Employee Login </title>
        <%--This link for ToolTip css --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <%--This is End for ToolTip css --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>         
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/LoginClientValidation.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <%--This link for ToolTip js --%>
        
        <%--This End for ToolTip js --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/clock.css"/>">
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
        
        <SCRIPT LANGUAGE="JavaScript">


function popUp() {
//URL="../crm/activities/activity.action?accountId=107481&contactId=0";

URL="../crm/Listactivities/loginactivity.action";
alert("URL"+URL);
day = new Date();
id = day.getTime();
eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=400,height=250');");

//eval("page" + id + " = window.open(URL, '" + id + "', 'channelmode=0,width=300,height=150,top=200,left=350,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no');");
}

</SCRIPT>
        
    </head>
    <%--<body class="bodyGeneral"  oncontextmenu="return false;" onload="document.userLoginForm.userId.focus();"> --%> <!-- onload="clock();" -->
        <!--//START MAIN TABLE : Table for template Structure-->
      <!--  <body class="bodyGeneral"  oncontextmenu="return false;" onload="document.userLoginForm.userId.focus();"> -->
        <body class="bodyGeneral"  oncontextmenu="return false;">
        <table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0">
            
            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/Header.jsp"/>
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
                                <s:form action="/general/loginCheck.action" method="post" name="userLoginForm" id="userLoginForm" theme="simple" onsubmit="return checkUserLoginForm();"> 
                                   <%--  <s:form action="/general/loginCheck.action" method="post" name="userLoginForm" id="userLoginForm" theme="simple" onsubmit="return popUp();"> --%>
                                   
                                        <table id="login" bgcolor="#ffffff" border="0" height="150px" width="340px;" align="left" cellpadding="0" cellspacing="2" class="border">
                                            <head >
                                                <th class="tableHeaderBg" align="left" width="100%" colspan="2">
                                                <font color="#336699" size="2px"><b>Employee Login</b></font></th>
                                            </head>
                                            <tr> 
                                                <td colspan="2">
                                                    <s:actionerror/>
                                                    <s:actionmessage/>
                                                    <s:fielderror/> 
                                                    <%! String userdetails=null; %>
                                                    <%  
                                                    if(userdetails != null) {
                                                        if("invaliduser".equals(userdetails)) {
                                                            out.println("<center><font face=\"Arial\" color=red size=4pt>");
                                                            out.println("Either Username/Password was Invalid</center>");
                                                        }
                                                    }
                                                    request.removeAttribute("issueId");
                                                    request.removeAttribute("resM");
                                                    if(request.getParameter("issueId") != null){
                                                        String issueId = request.getParameter("issueId");
                                                        session.setAttribute("issueId",issueId);
                                                        String resM = request.getParameter("resM");
                                                        session.setAttribute("resM",resM);
                                                    }
                                                       
                                                    request.removeAttribute("taskId");
                                                    request.removeAttribute("resM");
                                                    if(request.getParameter("taskId") != null){
                                                        String taskId = request.getParameter("taskId");
                                                        session.setAttribute("taskId",taskId);
                                                        String resM = request.getParameter("resM");
                                                        session.setAttribute("resM",resM);
                                                    }
                                                    request.removeAttribute("leaveId");
                                                    if(request.getParameter("leaveId") != null){
                                                        String leaveId = request.getParameter("leaveId");
                                                        session.setAttribute("leaveId",leaveId);
                                                    }
                                                     request.removeAttribute("emptimeSheetID");
                                                     request.removeAttribute("employeeID");
                                                      request.removeAttribute("resourceType");
                                                    // request.removeAttribute("statusValue");
                                                    // request.removeAttribute("secStatusValue");
                                                     if(request.getParameter("emptimeSheetID") != null)
                                                     {
                                                     String timeSheetID = request.getParameter("emptimeSheetID");
                                                     String empID = request.getParameter("employeeID");
                                                     String resourceType = request.getParameter("resourceType");
                                                    // String statusValue = request.getParameter("statusValue");
                                                    //  String secStatusValue = request.getParameter("secStatusValue");
                                                     session.setAttribute("emptimeSheetID",timeSheetID);
                                                     session.setAttribute("employeeID",empID);
                                                     session.setAttribute("resourceType",resourceType);
                                                   //  session.setAttribute("statusValue",statusValue);
                                                   // session.setAttribute("secStatusValue",secStatusValue);
                                                     }
                                                     request.removeAttribute("id");
                                                     request.removeAttribute("consultantId");
                                                     
                                                      if (request.getParameter("consultantId") != null) {
                                                        String techId = request.getParameter("id");
                                                        String conId = request.getParameter("consultantId");
                                                        String status = request.getParameter("status");
                                                        int requirementId = Integer.parseInt(request.getParameter("requirementId"));
                                                        int recConsultantId = Integer.parseInt(request.getParameter("recConsultantId"));

                                                        session.setAttribute("id", techId);
                                                        session.setAttribute("consultantId", conId);
                                                        session.setAttribute("status", status);
                                                        session.setAttribute("requirementId", requirementId);
                                                        session.setAttribute("recConsultantId", recConsultantId);
                                                    }

                                                    if (request.getParameter("objectId") != null && request.getParameter("recruitmentRoleType") != null) {
                                                        String objectId = request.getParameter("objectId");
                                                        String requirementAdminFlag = request.getParameter("requirementAdminFlag");
                                                        String recruitmentRoleType = request.getParameter("recruitmentRoleType");
                                                        session.setAttribute("objectId", objectId);
                                                        session.setAttribute("requirementAdminFlag", requirementAdminFlag);
                                                        session.setAttribute("recruitmentRoleType", recruitmentRoleType);
                                                        //out.print("roletype");
                                                    }

                                                    if (request.getParameter("objectId") != null && request.getParameter("consultId") != null) {
                                                        String objectId = request.getParameter("objectId");
                                                        String requirementAdminFlag = request.getParameter("requirementAdminFlag");
                                                        String consultId = request.getParameter("consultId");
                                                        session.setAttribute("objectId", objectId);
                                                        session.setAttribute("requirementAdminFlag", requirementAdminFlag);
                                                        session.setAttribute("consultId", consultId);
                                                         //out.print("flag");
                                                    }
                                                    %>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="30%" class="fieldLabel" align="right" >Login Id :</td>
                                                <td width="70%"><s:textfield name="userId" id="userId" cssClass="inputBox" onkeypress="return handleEnter(this,event);" onchange="userIdValidate(document.userLoginForm.userId.value);" tabindex="1"/> </td>
                                            </tr>
                                            <tr>
                                                <td width="30%"></td>
                                                <td width="70%" align="left" class="error" id="nameError">Required:UserName</td>
                                            </tr><!--username error display -->
                                                    
                                            <tr>
                                                <td width="30%" class="fieldLabel" align="right">Password :</td>
                                                <td width="70%" ><s:password name="password" id="password" cssClass="inputBox" onchange="passwordValidate(document.userLoginForm.password.value);" tabindex="2"/></td>
                                                
                                            </tr>
                                            <tr>
                                                <td width="30%"></td>
                                                <td width="70%" align="left" class="error" id="pwdError">Required:Password</td>
                                            </tr><!--password error display -->
                                                    
                                            <tr>
                                                <td></td><td></td><td></td>
                                            </tr>
                                            <tr>
                                                
                                                <td colspan="2" align="center">
                                                    <s:submit cssClass="buttonBg" value="Login" tabindex="3"/>
                                                    <s:reset cssClass="buttonBg" value="Reset" tabindex="4"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" align="center">
                                                    <% if(request.getAttribute("errorMessage") != null){
                                                        out.println(request.getAttribute("errorMessage"));
                                                    }%>
                                                    
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center" colspan="2">
                                                    <a href="<s:url value="/general/registration.action"/>" cssClass="noUnderLine" onmouseover="fixedtooltip('<b>Get Registered Here To Access Hubble .</b>',this,event, 150,2,-60)" onmouseout="delayhidetip()" style="text-decoration: none;">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/selfRegistration_96x16.gif" border="0">
                                                    </a>
                                                     &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <a href="<s:url value="/general/forgotPassword.action?eflag=e"/>" cssClass="noUnderLine" onmouseover="fixedtooltip('<b>Have you forgot your password ?</b><br>No problem enter here.',this,event, 180,2,-43)" onmouseout="delayhidetip()">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/forgotPassword_96x16.gif" border="0">
                                                    </a>
                                                    
                                                </td>
                                                
                                                
                                            </tr>
                                            
                                            
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
                
                <td align="center">
                    <script language="javascript">
var msgs = new Array(
"Note: We recommend you to use IE7,FireFox2.x above versions!",
"Please send us your feed back to hubble@miraclesoft.com",
"You can reach us at 248-233-1814.",
"Please signOff properly !",
"© 2011 HubbleV1.0 - All Rights Reserved." ); // No comma after last ticker msg

var msg_url = new Array(
"http://www.firefox2.com/",
"mailto:mirage@miraclesoft.com",
"#",
"http://<%=request.getLocalName()%>/"+CONTENXT_PATH+"/general/logout.action",
"http://www.miraclesoft.com" ); // No comma after last ticker url

var barwidth='390px' //Enter main bar width in px or %
var setdelay=3000 //Enter delay between msgs, in mili-seconds
var mouseover_color='#E1FFE1' //Specify highlight color
var mouseout_color='#FFFFFF' //Specify default color
/////////////////////////////////////////////////////////////////////

var count=0;
var ns6=document.getElementById&&!document.all
var ie4=document.all&&navigator.userAgent.indexOf("Opera")==-1

if (ie4||ns6){
    document.write('<form name="news_bar"><input type="button" value="<-" onclick="moveit(0)" class="scrollerstyle" style="width:10px; height:22px; border-right-width:0px;" name="prev" title="Previous News"><input type="button" name="news_bar_but" onclick="goURL();" style="color:#000000; width:'+barwidth+'; height:22px; border-width:1; border-color:#000000; cursor:hand" onmouseover="this.style.background=mouseover_color" onmouseout="this.style.background=mouseout_color"><input type="button" value="->" onclick="moveit(1)" class="scrollerstyle" style="width:10px; height:22px; border-left-width:0px;" name="next" title="Next News"></form>');
}
else{
    document.write('<form name="news_bar"><input type="button" value="Previous" onclick="moveit(0)">')
    if (navigator.userAgent.indexOf("Opera")!=-1)
        document.write('<input type="button" name="news_bar_but" onclick="goURL();" style="width:'+barwidth+'" border="0">')
    else
        document.write('<input type="button" name="news_bar_but" onclick="goURL();" width="'+barwidth+'" border="0">')
        document.write('<input type="button" value="Next" onclick="moveit(1)"></form>')
}

function init_news_bar(){
    document.news_bar.news_bar_but.value=msgs[count];
}
//moveit function by Dynamicdrive.com
function moveit(how){
    if (how==1){ //cycle foward
        if (count<msgs.length-1)
            count++
        else
            count=0
    }
    else{ //cycle backward
        if (count==0)
            count=msgs.length-1
        else
            count--
    }
    document.news_bar.news_bar_but.value=msgs[count];
}

setInterval("moveit(1)",setdelay)

function goURL(){
    location.href=msg_url[count];
}

init_news_bar();                        
                                           </script>
                </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        <script type="text/javascript">
		$(window).load(function(){
		document.userLoginForm.userId.focus();

		});
		</script>
    </body>
</html>
