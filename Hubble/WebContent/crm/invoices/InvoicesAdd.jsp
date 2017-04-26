<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>

<html>
    <head>
        <title>Hubble Organization Portal :: Template</title>
        <sx:head cache="false"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
        
        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTable" align="center" cellpadding="0" cellspacing="0">
            
            <!--//START HEADER : Record for Header Background and Mirage Logo-->
            <tr class="headerBg">
                <td valign="top">
                    <s:include value="/includes/template/header.jsp"/>                    
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
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <!--//START TABBED PANNEL : -->
                              <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="contactTab"  > Invoices </a></li>
                                </ul>
                                <div  style="border:1px solid gray; width:845px;height: 600px; overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:tabbedpanel id="contactPannel" cssStyle="width: 845px; height: 600px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                    
                                    <!--//START TAB : -->
                                    <%--  <sx:div id="contactTab" label="Invoices" theme="ajax" > --%>
                                    <div id="contactTab" class="tabcontent"  >
                                        <s:form  name="myform" theme="simple" action="poSearch">
                                            
                                            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                                <tr align="right"> 
                                                    <td  colspan="6"  class="headerText">
                                                        <s:submit   name="Submit" value="Submit" cssClass="buttonBg"  />
                                                        
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="6">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Date printed</td>
                                                    <td><s:textfield name="dateSubmit" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal1.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                    <td class="fieldLabel">Date paid</td>
                                                    <td><s:textfield name="dateResolve" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal2.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                    <td class="fieldLabel">Duedate </td>
                                                    <td><s:textfield name="dateResolve1" cssClass="inputTextBlueSmall" value=""/><a href="javascript:cal3.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                    </td>
                                                    
                                                    
                                                </tr>
                                                
                                                <tr>
                                                    <td colspan="6">&nbsp;</td>
                                                </tr>
                                                
                                                
                                                <tr>
                                                    <td colspan="3" class="headerText">Bill To Address</td>
                                                    
                                                    <td colspan="3" class="headerText">Ship To Address</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3">
                                                        <s:include value="/includes/template/GeneralAddress.jsp"/>
                                                    </td>
                                                    <td colspan="3">
                                                        <s:include value="/includes/template/GeneralAddress.jsp"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">NetTerm</td>
                                                    <td>   <s:select name="stage" cssClass="inputSelect" list="{'30 Days','60 Days',' 90 Days','Active'}" emptyOption="false" headerKey="-1" headerValue="---Select NetTerm--"/></td>
                                                    <td class="fieldLabel">SalesPerson</td>
                                                    <td>  <s:select name="stage" cssClass="inputSelect" list="{'30 Days','60 Days',' 90 Days','Active'}" emptyOption="false" headerKey="-1" headerValue="---Select Sales--"/></td>
                                                    <td class="fieldLabel">Department </td>
                                                    <td>     <s:select name="stage" cssClass="inputSelect" list="{'30 Days','60 Days',' 90 Days','Active'}" emptyOption="false" headerKey="-1" headerValue="---Select Department--"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Invoices Status</td>
                                                    <td>   <s:select name="stage" cssClass="inputSelect" list="{'Description'}" emptyOption="false" headerKey="-1" headerValue="---Select  Invoices Status--"/></td>
                                                    <td class="fieldLabel">Net Value ($)</td>
                                                    <td><s:textfield name="mainPhone" cssClass="inputTextBlue" size="15"/></td>
                                                    
                                                </tr>
                                                
                                            </table>
                                            <script type="text/JavaScript">
                                                           var cal1 = new CalendarTime(document.forms['myform'].elements['dateSubmit']);
                                                           cal1.year_scroll = true;
                                                           cal1.time_comp = false;
                                                           
                                                           var cal2 = new CalendarTime(document.forms['myform'].elements['dateResolve']);
                                                           cal2.year_scroll = true;
                                                           cal2.time_comp = false;
                                                           
                                                           var cal3 = new CalendarTime(document.forms['myform'].elements['dateResolve1']);
                                                           cal3.year_scroll = true;
                                                           cal3.time_comp = false;
                                            </script>
                                        </s:form>
                                        <%--  </sx:div > --%>
                                    </div>
                                    <!--//END TAB :   -->
                                    
                                    <%--  </sx:tabbedpanel> --%>
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
                <td align="center">&reg; 2007  Mirage - All Rights Reserved.</td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>
</html>
