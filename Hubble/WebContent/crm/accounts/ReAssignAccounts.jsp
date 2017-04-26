<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
    <head>
        <title>Hubble Organization Portal :: ReAssign Screens</title>
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
         <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/ResetPasswordClientValidation.js"/>"></script>--%>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
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
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                 <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="accountsListTab">ReAssign Accounts</a></li>
                                  
                                </ul>
                                <div  style="border:1px solid gray; width:840px;height: 470px; overflow:auto; margin-bottom: 1em;">
                                    
                                    <!--//START TABBED PANNEL : -->
                                    <%--         <sx:tabbedpanel id="ReAssignPannel" cssStyle="width: 845px; height: 470px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                    
                                    <!--//START TAB : -->
                                    <%--   <sx:div id="ReAssignTab" label="ReAssign Accounts"> --%>
                                    <div id="accountsListTab" class="tabcontent"   >
                                        <s:form name="reAssignAccounts" action="reAssignAccountsSubmit" theme="simple">
                                            <div style="padding-top=100px;">
                                                <table border="0" cellpadding="0" cellspacing="0" align="center" width="500px" height="150px" class="cellBorder">
                                                    <tr>
                                                        <td colspan="4" class="headerText">Enter following details to  ReAssign Screens</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel" align="right">From* :</td>
                                                        <td>
                                                          <s:select list="salesTeamExceptAccountTeamMap" name="frmLoginId" id="frmLoginId" headerKey="" headerValue="--Select Team--" cssClass="inputSelect" />
                                                         
                                                        </td>
                                                        
                                                        
                                                        <td class="fieldLabel" align="right">To* :</td>
                                                        <td>
                                                            <s:select list="salesTeamExceptAccountTeamMap" name="toLoginId" id="toLoginId" headerKey="" headerValue="--Select Team--" cssClass="inputSelect" />
                                                            
                                                        </td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td colspan="2" style="padding-left:218px;">
                                                            <s:submit cssClass="buttonBg" value="Submit"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4" align="center">
                                                            <% if(request.getAttribute("resultMessage") != null){
                                                            out.println(request.getAttribute("resultMessage"));
                                                            }%>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </s:form>
                                        <%--   </sx:div > --%>
                                    </div>
                                    <!--//END TAB : -->
                                    
                                    <%-- </sx:tabbedpanel> --%>
                                </div>
                                <!--//END TABBED PANNEL : -->
                               <script type="text/javascript">

                    var countries=new ddtabcontent("accountTabs")
                    countries.setpersist(false)
                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                    countries.init()

                                </script>
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
