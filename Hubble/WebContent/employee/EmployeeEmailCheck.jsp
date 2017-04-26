<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
<head>
    <title>Hubble Organization Portal :: Employee Addresses</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAddAjax.js"/>"></script>
    <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/AddressClientValidation.js"/>"></script>--%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    
</head>

<body class="bodyGeneral" oncontextmenu="return false;">

<!--//START MAIN TABLE : Table for template Structure-->
<table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

<!--//START HEADER : Record for Header Background and Mirage Logo-->
<tr class="headerBg">
    <td valign="top">
        <s:include value="/includes/template/Header.jsp"/>                    
    </td>
</tr>
<tr>
<td>
    <table class="innerTable1000x515" cellpadding="0" cellspacing="0">
    <tr>
        <td width="150px;" class="leftMenuBgColor" valign="top">
            <s:include value="/includes/template/LeftMenu.jsp"/>
        </td>
        <td width="850px" class="cellBorder" valign="top" >
        <table cellpadding="0" cellspacing="0" width="100%" border="0">
            <tr>
            <td valign="top" style="padding:5px 5px 5px 5px;"> 
                <!--//START TABBED PANNEL : -->
                <ul id="accountTabs" class="shadetabs" >
                    <li ><a href="#" class="selected" rel="empEmailCheck"  > Employee Email Check </a></li>
                </ul>
                  <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                <div id="empEmailCheck" align="center" >
                    <br>
                    <s:form name="employeeAddForm" theme="simple">
                        <table cellpadding="0" cellspacing="0" width="50%" border="0">                           
                            <tr>
                                            <td class="fieldLabel">Email:</td>
                                            <td colspan="3"><s:textfield name="email" id="email" onchange="return checkEmail(this);" cssClass="inputTextBlueLarge" /></td>
                                            <td>
                                                <input type="button" value="Search" class="buttonBg"  onclick="checkEmailExistsOrNot();"/>
                                            </td>
                                            <td height="20px" align="center" colspan="9">
                                                                                    <div id="resultMessage" style="color:green;"></div>
                                                                               </td>
                            </tr>
                              <tr>
                                                                                <td height="20px" align="center">
                                                                                <div id="loadActMessageAS" style="display:none" class="error" ><b>Loading Please Wait.....</b></div>
                                                                                </td>
                                                                           </tr>
                                                                         
                        </table>
                        
                    </s:form>
                    <%--  </sx:div> --%>
                </div>
                <!--//START TAB : -->
                                    
              <%--  </sx:tabbedpanel> --%>
              </div>
               <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                <!--//END TABBED PANNEL : -->
            </td>
        </tr>
    </table>
</td>
<!--//END DATA COLUMN : Coloumn for Screen Content-->
</tr>

</table>
</td>
</tr>
<!--//END DATA RECORD : Record for LeftMenu and Screen Content-->

<!--//START FOOTER : Record for Footer Background and Content-->
<tr class="footerBg">
    <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
</tr>
<!--//END FOOTER : Record for Footer Background and Content-->
</body>
</html>