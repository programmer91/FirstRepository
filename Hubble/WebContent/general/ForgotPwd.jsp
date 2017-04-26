<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    
    <%-- START HEAD SECTION --%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <title>Hubble Organization Portal :: Forgot Password</title>
        
        <script type="text/JavaScript">
            
            
    function checkFields() {             
         var emailId = document.getElementById('emailId').value;
         var re = /^\s*[\w\-\+_]+(\.[\w\-\+_]+)*\@[\w\-\+_]+\.[\w\-\+_]+(\.[\w\-\+_]+)*\s*$/;
         if(emailId == '') {
                    alert('Enter email id Field');
                    return false;
                    }
        else
    
    if (re.test(emailId)) {
       
       /* if (emailId.indexOf('@miraclesoft.com', emailId.length - '@miraclesoft.com'.length) !== -1) 
        {
          //alert('Submission was successful.');
            return true;            
        } else {
            alert('Email must be a miraclesoft e-mail address (emailId@miraclesoft.com).');
           
        }*/
                return true;
        } else {
                 alert('Not a valid e-mail address.');       
        }
      emailId.focus;
      return false;
    }               
        
        
    </script>
  
    </head>
    <%-- END HEAD SECTION --%>
    
    <%-- START BODY SECTION --%>
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <%--//START MAIN TABLE : Table for template Structure--%>
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0" border="0">
            
            <%--//START HEADER : Record for Header Background and Mirage Logo--%>
            <tr class="headerBg">
                <td valign="top">
                    
                    <% if(request.getParameter("eflag").equalsIgnoreCase("e")){%>
                    <s:include value="/includes/template/Header.jsp"/>
                    <%}
                    else 
                     {%>
                     <s:include value="/includes/template/customerLoginHeader.jsp"/>
                    <%}%>
                </td>
            </tr>
            <%--//END HEADER : Record for Header Background and Mirage Logo--%>
         
            <%--//START DATA RECORD : Record for LeftMenu and Screen Content--%>
            <tr>
                <td>
                    <table class="innerTable1000x515" cellpadding="0" cellspacing="0" border="0">
                      
                        
                        <tr>
                            <!-- START Horizonatla Bar Below the Animated Header -->
                            <td class="footerBg" colspan="2">
                                <img alt="border" 
                                     src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                     width="1000px" 
                                     height="13px" border="0">
                            </td>
                            <!-- END Horizonatla Bar Below the Animated Header -->
                        </tr>
                        <tr>
                            <td class="topBorder" height="350px" valign="top" background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
                                <br><br>
                                <table cellpadding="0" cellspacing="0" border="0" width="500px" align="center"  class="cellBorder">
                                    <tr>
                                        <td valign="top">
                                            <table width="500px" cellpadding="2" cellspacing="2" border="0" align="center">
                                                <!--START ANIMATED TEXT ROW -->
                                                <tr>
                                                    <td colspan="2"><h3 class="bgColor" align="center"><script type="text/JavaScript" src="<s:url value="/includes/javascripts/ForgotPassworAnimation.js"/>"></script>
                                                            
                                                        </h3>
                                                    </td>
                                                </tr>
                                                <!--END ANIMATED TEXT ROW -->                                                                                       
                                                <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">Enter valid Miracle Web Mail Email Id and Click Submit Button! </font></td>
                                                </tr>
                                                <tr>
                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                    <td ><font size="2px" color="#1B425E">We will send the Password to Your Email! </font></td>
                                                </tr>
                                            </table>
                                        </td>
                                        
                                    </tr>
                                    <tr>
                                        <td valign="top">
                                            
                                            <s:form action="forgotPasswordSubmit" theme="simple" onsubmit="return checkFields();">
                                                <table border="0" cellpadding="1" cellspacing="1" align="center" width="500px">
                                                    <head>
                                                        <th class="headerText" colspan="2" align="left">
                                                            <%
                                                                String empType=request.getParameter("eflag");
                                                            %>
                                                        </th>
                                                     </head>
                                                    <tr>
                                                        <td class="fieldLabel" align="right" width="200px">Email Id* :</td>
                                                       <td><s:textfield size="35" cssClass="inputBox" name="emailId" id="emailId"/>
                                                           <input type="hidden" value="<%=request.getParameter("eflag") %>" name="eflag" id="emailId"/>
                                                        
                                                        </td>
                                                         
                                                  
                                                    </tr>   
                                                    
                                                    <tr>
                                                        <td colspan="2" align="center">
                                                            <s:submit cssClass="buttonBg" value="Submit"/>
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
                                                        
                                            </s:form>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        
                    </table>
                </td>  
            </tr>
            <%--//START FOOTER : Record for Footer Background and Content--%>
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <%--//END FOOTER : Record for Footer Background and Content--%>

        </table>
        <%--//START MAIN TABLE : Table for template Structure--%>

    </body>
    <%-- END BODY SECTION --%>
    
   </html>