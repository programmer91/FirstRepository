<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<html>
    <head>
        <title>Hubble Organization Portal ::ContactEmailService</title>
       <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AccountDetailsClientValidation.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DashBoardAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        
         <s:include value="/includes/template/headerScript.html"/>
         
         <script type="text/JavaScript">
            function emailOpen()
            {
            var offmail = document.mailServices.officeEmail.value;
            var accountId = document.mailServices.accountId.value;
            
            var contactId = document.mailServices.contactId.value;
             
           var testwindow = window.open(CONTENXT_PATH+"/services/mail/ContactsMailWindow.jsp?to="+offmail+"&accountId="+accountId+"&contactId="+contactId,"testwindow","location=1,status=1,scrollbars=1,width=550,height=500");
            testwindow.moveTo(650,400);      
            }                     
                              
             
           
             </script>
         
    </head>
    <!-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="emailOpen()"> -->
        
        
        <body  class="bodyGeneral" oncontextmenu="return false;" >
        
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
                            <td width="850px;" class="cellBorder" valign="top"> 
                                <s:include value="/includes/template/LeftMenu.jsp"/>                    
                            </td>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            <td>
                             
                            </td>    
                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td>
                             <s:form name="mailServices">
                               <s:hidden name="officeEmail" value="%{officeEmail}" />
                               <s:hidden name="contactId" value="%{id}" />
                               <s:hidden name="accountId" value="%{accountId}" />
                                
                          <!--  <input type="button" name="submit" value="Back" Class="buttonBg" onclick="javascript:history.go(-1)"/>  -->
                            </s:form>  
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
        <script type="text/javascript">
		$(window).load(function(){
		
		emailOpen();
		
                
		});
		</script>
    </body>
</html>