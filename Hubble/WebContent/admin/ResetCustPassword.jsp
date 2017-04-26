<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
    <head>
        <title>Hubble Organization Portal :: Reset User Password</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/ResetPasswordClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
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
                            <td width="850px" class="cellBorder" valign="top">
                                <!--//START TABBED PANNEL : -->
                                <sx:tabbedpanel id="resetPasswordPannel" cssStyle="width: 845px; height: 470px;padding:5px 5px 5px 5px;" doLayout="true">
                                    
                                    <!--//START TAB : -->
                                    <sx:div id="resetPasswordTab" label="Reset Password" >
                                        <s:form  name="resetUserPwdForm" action="updateCustPassword" theme="simple" onsubmit="return checkValues();">
                                            <div style="padding-top=100px;">
                                                <table border="0" cellpadding="0" cellspacing="0" align="center" width="500px" height="150px" class="cellBorder">
                                                    <tr>
                                                        <td colspan="2" class="headerText">Enter following details to reset Password</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel" align="right">Customer Id<font color="red" style="bold">*</font> :</td>
                                                        <td><s:textfield name="loginId" id="loginId" size="30" cssClass="inputTextBlue" onchange="loginIdValidate(this);"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel" align="right">New Password<font color="red" style="bold">*</font> :</td>
                                                        <td><s:password  name="newPassword" id="newPassword" size="30" cssClass="inputTextBlue" onchange="newUserPasswordValidate(this);"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel" align="right">Confirm Password<font color="red" style="bold">*</font> :</td>
                                                        <td><s:password name="cnfPassword" id="cnfPassword" size="30" cssClass="inputTextBlue" onchange="confirmUserPasswordValidate(this);"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2" style="padding-left:218px;">
                                                            <s:submit cssClass="buttonBg" value="Submit"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2" align="center" class="headerText">
                                                            <s:property value="#request.resultMessage"/>
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
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>
</html>