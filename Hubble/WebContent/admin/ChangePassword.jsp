<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
    <head>
        <title>Hubble Organization Portal :: Change Password</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        
    </head>
    <body  class="bodyGeneral" oncontextmenu="return false;">
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable" align="center" cellpadding="0" cellspacing="0">
            
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
                    <table class="innerTable" cellpadding="0" cellspacing="0">
                        <tr>
                            
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td width="150px;" class="leftMenuBgColor" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/>                    
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top">
                                <!--//START TABBED PANNEL : -->
                                <sx:tabbedpanel id="resetPasswordPannel" cssStyle="width: 845px; height: 470px;padding-left:10px;padding-top:5px;" doLayout="true" >
                                    
                                    <!--//START TAB : -->
                                    <sx:div id="changePasswordTab" label="Reset Password"  >
                                        <s:form action="changePasswordSubmit" theme="simple">
                                            <div style="padding-top=100px;">
                                                <table border="0" cellpadding="0" cellspacing="0" align="center" width="500px" height="150px" class="cellBorder">
                                                    <tr>
                                                        <td colspan="2" class="headerText">Enter Details to Change Password</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel" align="right">User Id :</td>
                                                        <td><s:password name="oldPassword" size="30" cssClass="inputTextBlue"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel" align="right">New Password<font color="red" style="bold">*</font> :</td>
                                                        <td><s:password  name="newPassword" size="30" cssClass="inputTextBlue"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2" style="padding-left:218px;">
                                                            <s:submit cssClass="buttonBg" value="Submit"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2" align="center">
                                                            <% if(request.getAttribute("resultMessage") != null){
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
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>
</html>
