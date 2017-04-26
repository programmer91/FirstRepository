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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/cre/CreClientLoginValidation.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <%--This link for ToolTip js --%>
        
        <%--This End for ToolTip js --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/clock.css"/>">
        
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
        <body class="bodyGeneral"  oncontextmenu="return false;" onload="document.consultantLoginForm.consultantId.focus();">
        <table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0">
            
            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/CreHeader.jsp"/>
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
                                <s:form action="mcertLoginCheck.action" method="post" name="consultantLoginForm" id="consultantLoginForm" theme="simple" onsubmit="return checkCreLoginForm();"> 
                                   <%--  <s:form action="/general/loginCheck.action" method="post" name="userLoginForm" id="userLoginForm" theme="simple" onsubmit="return popUp();"> --%>
                                   
                                        <table id="login" bgcolor="#ffffff" border="0" height="150px" width="340px;" align="left" cellpadding="0" cellspacing="2" class="border">
                                            <head >
                                                <th class="tableHeaderBg" align="left" width="100%" colspan="2">
                                                <font color="#336699" size="2px"><b>Ecert&nbsp;Consultant Login</b></font></th>
                                            </head>
                                            <tr> 
                                                <td colspan="2">
                                                    <s:actionerror/>
                                                    <s:actionmessage/>
                                                    <s:fielderror/> 
                                                    <%! String userdetails=null; %>
                                                    <%  
                                                    if(userdetails != null) {
                                                        if("invalidconsultant".equals(userdetails)) {
                                                            out.println("<center><font face=\"Arial\" color=red size=4pt>");
                                                            out.println("ConsultantId was Invalid</center>");
                                                        }
                                                    }
                                                    
                                                    %>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="40%" class="fieldLabel" align="right" >Consultant&nbsp;Id :</td>
                                                <td width="60%"><s:textfield name="consultantId" id="consultantId" cssClass="inputBox" onkeypress="return handleEnter(this,event);" tabindex="1"/> </td>
                                            </tr>
                                             <tr>
                                                <td width="40%"></td>
                                                <td width="60%" align="left" class="error" id="idError">Required:Consultant Id</td>
                                            </tr>
                                              <tr>
                                                <td width="40%" class="fieldLabel" align="right" >Consultant&nbsp;Email :</td>
                                                <td width="60%"><s:textfield name="consultantEmail" id="consultantEmail" cssClass="inputBox"  tabindex="2" onchange="return checkEmail();"/> </td>
                                            </tr>
                                            <tr>
                                                <td width="40%"></td>
                                                <td width="60%" align="left" class="error" id="emailError">Required:Consultant Email</td>
                                            </tr>
                                            <tr>
                                                <td width="40%"></td>
                                                <td width="60%" align="left" class="error" id="invalidEmail">Invalid:Consultant Email</td>
                                            </tr>
                                            <!--username error display -->
                                            <tr>
                                                
                                                <td colspan="2" align="center">
                                                    <s:submit cssClass="buttonBg" value="Enter" tabindex="3"/>
                                                    <s:reset cssClass="buttonBg" value="Reset" tabindex="4"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" align="center">
                                                    <% if(request.getAttribute("reqErrorMessage") != null){
                                                        out.println(request.getAttribute("reqErrorMessage"));
                                                    }%>
                                                    
                                                </td>
                                            </tr>
                                               <tr>
                                                <td align="center" colspan="2">
                                                    <a href="<s:url value="/cre/general/creregistration.action"/>" cssClass="noUnderLine" onmouseover="fixedtooltip('<b>Get Registered Here To Access CRE .</b>',this,event, 150,2,-60)" onmouseout="delayhidetip()">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/selfRegistration_96x16.gif" border="0">
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
"Note: We recommend you to use Latest FireFox version (Recommended 24 and above) versions!",
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
    </body>
</html>
