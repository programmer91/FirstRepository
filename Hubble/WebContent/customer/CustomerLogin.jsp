<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<%--
 author:raja reddy andra
 email:randra@miraclesoft.com
--%>
<html>
    <head>
        <title>Hubble Organization Portal :: Customer Login </title>
        <sx:head cache="true"/>
        <%--This link for ToolTip css --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
        <%--This is End for ToolTip css --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>         
        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/LoginClientValidation.js"/>"></script>         --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clock.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StandardClientValidations.js"/>"></script>
        
        <%--This link for ToolTip js --%>
          
        <%--This End for ToolTip js --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/clock.css"/>">
        
    </head>
    <!-- <body class="bodyGeneral"  oncontextmenu="return false;" onload="document.custLoginForm.customerId.focus();"> --><!-- onload="clock();" -->
    <body class="bodyGeneral"  oncontextmenu="return false;">
        <%--This is END for toopTip --%>
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0">
            
            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                   <%-- <s:include value="/includes/template/Header.jsp"/>                    --%>
                   <s:include value="/includes/template/customerLoginHeader.jsp"/>
                </td>
            </tr>
            
            <!--//END HEADER : Record for Header Background and Mirage Logo-->
            
            <!--//START DATA RECORD : Record for LeftMenu and Screen Content-->
            <tr>
                <td align="center">
                    <table border="0" width="350px;" cellpadding="2" cellspacing="2">
                    <tr>
            <td>
                            <s:form action="/customer/loginCustCheck.action" method="post" name="custLoginForm" theme="simple" onsubmit="return checkCustLoginForm();">
                                <table  border="0" height="150px" width="340px;" align="left" cellpadding="0" cellspacing="2" class="border">
                                    <head >
                                        <th class="tableHeaderBg" align="left" width="100%" colspan="2">
                                        <font color="#336699" size="2px"><b>Customer Login</b></font></th>
                                    </head>
                                    <tr> 
                                        <s:hidden name="empType" value="Customer" />
                                        <td colspan="2">
                                            <s:actionerror/>
                                            <s:actionmessage/>
                                            <s:fielderror/> 
                                            
                                            <!-- **********************userdetails  1****-->                                                                    
                                            <%! String userdetails1=null; %>
                                            <%  
                                            if(userdetails1 != null) {
                                                if("invaliduser".equals(userdetails1)) {
                                                    out.println("<center><font face=\"Arial\" color=red size=4pt>");
                                                    out.println("Either Username/Password was Invalid</center>");
                                                }
                                            }
                                            
                                            request.removeAttribute("emptimeSheetID");
                                                     request.removeAttribute("employeeID");
                                                      request.removeAttribute("resourceType");
                                                	//request.removeAttribute("statusValue");
                                                     //   request.removeAttribute("secStatusValue");
                                                     if(request.getParameter("emptimeSheetID") != null)
                                                     {
                                                     String timeSheetID = request.getParameter("emptimeSheetID");
                                                     String empID = request.getParameter("employeeID");
                                                     String resourceType = request.getParameter("resourceType");
                                                     //String statusValue = request.getParameter("statusValue");
                                                    // String secStatusValue = request.getParameter("secStatusValue");
                                                     session.setAttribute("emptimeSheetID",timeSheetID);
                                                     session.setAttribute("employeeID",empID);
                                                    session.setAttribute("resourceType",resourceType);
                                                    // session.setAttribute("statusValue",statusValue);
                                                    // session.setAttribute("secStatusValue",secStatusValue);
                                                     }
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="30%" class="fieldLabel" align="right" >Login Id :</td>
                                        <td width="70%"><s:textfield name="customerId" id="customerId" cssClass="inputBox" onkeypress="return handleEnter(this,event);" onchange="fieldLengthValidator(this);" tabindex="5"/> </td>
                                        
                                    </tr>
                                    <tr>
                                        <td width="30%"></td>
                                        <td width="70%" align="left" class="error" id="custNameError">Required:UserName</td>
                                    </tr><!--username error display -->
                                                    
                                    <tr>
                                        <td width="30%" class="fieldLabel" align="right">Password :</td>
                                        <td width="70%" ><s:password name="customerPwd" id="customerPwd" cssClass="inputBox" onchange="fieldLengthValidator(this);" tabindex="6"/></td>
                                        
                                    </tr>
                                    <tr>
                                        <td width="30%"></td>
                                        <td width="70%" align="left" class="error" id="custPwdError">Required:Password</td>
                                    </tr><!--password error display -->
                                                    
                                    <tr>
                                        <td></td><td></td><td></td>
                                    </tr>
                                    <tr>
                                        
                                        <td colspan="2" align="center">
                                            
                                            <s:submit cssClass="buttonBg" value="Login" tabindex="7"/>
                                            <s:reset cssClass="buttonBg" value="Reset" tabindex="8"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" align="center">
                                            <% if(request.getAttribute("custErrorMessage") != null){
                                                out.println(request.getAttribute("custErrorMessage"));
                                            }%>
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center" colspan="2">
                                                   
                                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <a href="<s:url value="/general/forgotPassword.action?eflag=c"/>" cssClass="noUnderLine" onmouseover="fixedtooltip('<b>Have you forgot your password ?</b><br>No problem enter here.',this,event, 180,2,-43)" onmouseout="delayhidetip()">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/forgotPassword_96x16.gif" border="0">
                                                    </a>
                                                    
                                                </td>
                                    </tr>
                                </table>
                                
                            </s:form>
                        </td>
       </tr>
                   
                        <tr>
                            
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
                
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        <script>
$(document).ready(function() {
   document.custLoginForm.customerId.focus();
     });
</script>	
    </body>
</html>
