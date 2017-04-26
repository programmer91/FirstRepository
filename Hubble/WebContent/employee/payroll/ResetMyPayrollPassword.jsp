<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
    <head>
        <title>Hubble Organization Portal :: Reset Password</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/ResetEmployeePwd.js"/>"></script>  
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css"/>">
        <s:include value="/includes/template/headerScript.html"/>
    </head>
    <body  class="bodyGeneral" oncontextmenu="return false;">
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
            <td valign="top">
                <s:include value="/includes/template/Header.jsp"/>                    
            </td>
        </tr>
        <!--//END HEADER : Record for Header Background and Mirage Logo-->

        <!--//START DATA RECORD : Record for LeftMenu and Screen Content-->
        <tr>
        <td>
            <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
                <tr>

                    <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                <td width="150px;" class="leftMenuBgColor" valign="top"> 
                    <s:include value="/includes/template/LeftMenu.jsp"/>                    
                </td>
                <!--//START DATA COLUMN : Coloumn for LeftMenu-->

                <!--//START DATA COLUMN : Coloumn for Screen Content-->
                <td width="850px" class="cellBorder" valign="center" background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
                    <!--//START TABBED PANNEL : -->
                    <sx:tabbedpanel id="resetPasswordPannel" cssStyle="width: 850px; height: 510px;padding-left:5px;padding-right:5px;padding-bottom:8px;" doLayout="true" >

                        <!--//START TAB : -->
                        <sx:div id="resetPasswordTab" label="Reset Password">
                            <s:form action="resetMyPayrollPasswordSubmit" name="resetForm" theme="simple" onsubmit="return resetEmpPwdSubmit();">
                                <div style="padding-top:80px;">
                                    <table border="0" cellpadding="1" cellspacing="1" align="center" width="500px" height="150px" class="cellBorder" background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
                                        <tr>
                                        <td valign="top" colspan="2">
                                            <table width="500px" cellpadding="1" cellspacing="1" border="0" align="center">
                                                <!--START ANIMATED TEXT ROW -->
                                                <tr>
                                                <td colspan="2"><h3 class="bgColor" align="center"><script type="text/JavaScript" src="<s:url value="/includes/javascripts/ResetPasswordAnimation.js"/>"></script>

                                                    </h3>
                                                </td>
                                                </tr>
                                                <!--END ANIMATED TEXT ROW -->
                                                <tr>
                                                <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                <td ><font size="2px" color="#1B425E">Please Enter Your Old Password In First Box ! </font></td>
                                                </tr>
                                                <tr>
                                                <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                <td ><font size="2px" color="#1B425E">Enter Your New Password In Second Box ! </font></td>
                                                </tr>
                                                <tr>
                                                <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                <td ><font size="2px" color="#1B425E">Enter Your New Password Once Again In Third Box And Click Submit Button ! </font></td>
                                                </tr>
                                            </table>
                                            <br> </td>
                                        </tr>
                                        <tr>
                                        <td colspan="2" class="headerText">Enter following details</td>
                                        </tr>
                                        <tr> <td align="center" colspan="2" > <font size="0" id="payRollAuthenticationMsg" /></td></tr><br><br> 

                                        <tr>
                                        <td class="fieldLabel" align="right">Old Password<font color="red" style="font-style: bold">*</font> :</td>
                                        <td><s:password name="oldPassword" size="30" cssClass="inputTextBlue"/></td>
                                        </tr>
                                        <tr>
                                        <td class="fieldLabel" align="right">New Password<font color="red" style="font-style: bold">*</font> :</td>
                                        <td><s:password  name="newPassword" size="30" cssClass="inputTextBlue" onchange="NewpasswordValidate(document.resetForm.newPassword.value);"/></td>
                                        </tr>
                                        <tr>
                                        <td class="fieldLabel" align="right">Confirm Password<font color="red" style="font-style: bold">*</font> :</td>
                                        <td><s:password name="confirmPassword" size="30" cssClass="inputTextBlue" onchange="CnfpasswordValidate(document.resetForm.confirmPassword.value);"/></td>
                                        </tr>
                                        <tr>
                                        <td colspan="2" style="padding-left:218px;">
                                            <s:submit cssClass="button_payroll" value="Submit"/>
                                        </td>
                                        </tr>
                                        <tr>
                                        <td colspan="2" align="center">
                                            <% if (request.getAttribute("resultMessage") != null) {
                                                                    out.println(request.getAttribute("resultMessage"));
                                                                }%>
                                        </td>
                                        </tr>
                                    </table>
                                </div>
                            </s:form>
                        </sx:div >
                        <!--//END TAB : -->

                    </sx:tabbedpanel>
                    <!--//END TABBED PANNEL : -->

                </td>
                <!--//END DATA COLUMN : Coloumn for Screen Content-->
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
 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>
</table>
<!--//END MAIN TABLE : Table for template Structure-->
</body>
</html>
