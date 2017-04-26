<%-- 
    Document   : LeadDashboard
    Created on : Apr 19, 2016, 10:31:13 PM
    Author     : miracle
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Lead&nbsp;Dashboard</title>
        
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/marketing/LeadDashboard.js?version=1.1"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ajax/AjaxPopup.js"/>"></script>
        <s:include value="/includes/template/headerScript.html"/> 
        
        <script type="text/javascript">
            
function enableLeadEnter(e) {
    var keynum;
    var keychar;
    var numcheck;
    
    if(window.event) {
        keynum = e.keyCode;
    }
    else if(e.which) // Netscape/Firefox/Opera
    {
        keynum = e.which;
    }
    try{
        if(keynum==13){
            getLeadList();
            return false;
        }
    }catch(e){
        alert("Error"+e);
    }
};
        </script>
    </head>
    <%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getRequirementsList1();"> --%>
  
   <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="getLeadList();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;" >
    


        <!-- Start oif the table -->
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <!--//START TABBED PANNEL : -->
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="requirementList">Lead&nbsp;Dashboard</a></li>
                                    <!-- <li ><a href="#" rel="searchDiv">Requirement Search</a></li>-->
                                </ul>
                                <div  style="border:1px solid gray; width:845px;height: 530px; overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:tabbedpanel id="attachmentPannel2" cssStyle="width: 845px; height: 530px;padding:10px 5px 5px 5px" doLayout="true"> --%>

                                    <!--//START TAB : -->
                                    <%--  <sx:div id="List" label="" cssStyle="overflow:auto;"> --%>
                                    <div id="requirementList" class="tabcontent"  >

                                       


                                        <s:form action="" theme="simple" name="frmLeadDashboard">
                                            
                                            <table cellpadding="0" cellspacing="0" align="left" width="100%">

                                                <tr>
                                                    <td>
                                                        <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                                            <tr>
                                                                <td class="headerText" colspan="11" align="right">
                                                                    <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                                     <%
                                            if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                            }
                                        %>
                                                                </td>
                                                            </tr>
                                                            <%--  <tr>
                                                                
                                                                  <td class="fieldLabel">Created By :</td>
                                                                  <td>
                                                                      <s:select headerKey="All" headerValue="All" name="createdBy" id="createdBy" list="createdMemebers" cssClass="inputSelect" theme="simple" />
                                                                  </td>
                                                                  <td class="fieldLabel">Assigned To :</td>
                                                                  <td>
                                                                      <s:select headerKey="All" headerValue="All" list="assignedMembers" name="assignedTo" id="assignedTo" cssClass="inputSelect" theme="simple"/>
                                                                  </td>
                                                              </tr> --%>
                                                            <tr>
                                                                <td 
                                                                    class="fieldLabel">Title :</td>
                                                                <td><s:textfield name="leadTitle" id="leadTitle" cssClass="inputTextBlue" value="" onkeydown="return enableLeadEnter(event);"/></td>
                                                                <td class="fieldLabel">Status:</td>
                                                                <td><s:select headerKey="" headerValue="All" name="leadStatus" id="leadStatus" list="#@java.util.LinkedHashMap@{'Active':'Active','InActive':'InActive','Linked':'Linked'}"  cssClass="inputSelect" value="%{status}" onkeydown="return enableLeadEnter(event);"/>
                                                            </tr>
                                                              <tr>
                                                                <td   class="fieldLabel">Created Date From:</td>
                                                                <td><s:textfield name="createdDateFrom" id="createdDateFrom" cssClass="inputTextBlue" onchange="validateTimestamp(this);" onkeydown="return enableLeadEnter(event);"/>
                                                      <a href="javascript:cal1.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                             width="20" height="20" border="0"></a></td>
                                                                <td  class="fieldLabel">Created Date To :</td>
                                                                <td><s:textfield name="createdDateTo" id="createdDateTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);" onkeydown="return enableLeadEnter(event);"/>
                                                      <a href="javascript:cal2.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                             width="20" height="20" border="0"></a></td>
                                                             </tr>
                                                             <tr>
                                                                <td   class="fieldLabel">Inactive Date From:</td>
                                                                <td><s:textfield name="inactiveDateFrom" id="inactiveDateFrom" cssClass="inputTextBlue" onchange="validateTimestamp(this);" onkeydown="return enableLeadEnter(event);"/>
                                                      <a href="javascript:cal3.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                             width="20" height="20" border="0"></a></td>
                                                                <td  class="fieldLabel">Inactive Date To :</td>
                                                                <td><s:textfield name="inactiveDateTo" id="inactiveDateTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);" onkeydown="return enableLeadEnter(event);"/>
                                                      <a href="javascript:cal4.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                             width="20" height="20" border="0"></a></td>
                                                             </tr>
                                                             <tr>
                                                                <td   class="fieldLabel">Investment:</td>
                                                                <td> <s:select headerKey="0" headerValue="All" name="investmentId" id="investmentId" list="investmentList" cssClass="inputSelect" theme="simple" onkeydown="return enableLeadEnter(event);"/></td>
                                                                <td  class="fieldLabel">Analyst Name:</td>
                                                                <td><s:select headerKey="" headerValue="All" name="analystId" id="analystId" list="analystMap" cssClass="inputSelect" theme="simple" onkeydown="return enableLeadEnter(event);"/></td>
                                                             </tr>
                                                             
<tr>
                                                                <td   class="fieldLabel">State:</td>
                                                                <td>
                                                                    
                                                                    <%-- <s:select headerKey="0" headerValue="All" name="accountId" id="accountId" list="linkedLeadAccountsMap" cssClass="inputSelect" theme="simple" onkeydown="return enableLeadEnter(event);"/>
                                                                    --%>
                                                                     <s:select 
                                                                                    list="leadStatesList"   
                                                                                    name="state" 
                                                                                    id="state"
                                                                                    cssClass="inputSelect" 
                                                                                    headerKey=""
                                                                                    headerValue="--Please Select--"
                                                                                   
                                                                                />
                                                                        </td>
                                                                        
                                                                   <td class="fieldLabel">Priority:</td>         
                                                                                                                                        
                                                                    <td class="inputOptionText"><s:select name="priority" id="priority" list="#@java.util.LinkedHashMap@{'Hot':'Hot','Warm':'Warm','Neutral':'Neutral','Cold':'Cold'}"  headerKey=""
                                                                                    headerValue="--Please Select--"  cssClass="inputSelect" /></td> 
                                                                          
                                                               
                                                             </tr>
                                                             <tr>
                                                                  <td></td>
                                                                    <td></td>
                                                                      <td></td>
                                                                <td align="left">
                                                                    
                                                                    <input type="button" value="Add" class="buttonBg" onclick="addLeadFromDashboard();"/>
                                                                    
                                                                   
                                                                    <input type="button" value="Search" class="buttonBg" onclick="getLeadList();"/></td>
                                                             </tr>
						
                                                
                                            </table>  
                                            </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <table width="100%" cellpadding="1" cellspacing="1" border="0">
                                                        <tr>
                                                            <td>
                                                                <div id="loadingMessage12" style="color:red;display: none" align="center" ><b><font size="4px" >Loading...Please Wait...</font></b></div>
                                                                <%--<div id="loadingMessage12" style="display:none;" class="error" align="center" ><b><font size="4px" >Loading...Please Wait</font></b></div>--%>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3" >
                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                <table id="tblUpdate" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                                                                    
                                                                    <COLGROUP ALIGN="left" >
                                                                        <COL width="5%">
                                                                        <COL width="28%"> 
                                                                        <COL width="10%">
                                                                        <COL width="7%">
                                                                        <COL width="20%">
                                                                        <COL width="23%">
                                                                        <COL width="7%">
                                                                         <COL width="7%">
                                                                         <COL width="10%">
                                                                         
                                                                </table>
                                                                <br>
                                                                <center><span id="spnFast" class="activeFile"></span></center> 

                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            </table>
                                        </div>
                                    </s:form>


                                    <%--   </sx:div> --%>


                                    <script type="text/javascript">

                                    
                                        var countries=new ddtabcontent("accountTabs")
                                        countries.setpersist(false)
                                        countries.setselectedClassTarget("link") //"link" or "linkparent"
                                        countries.init()

                                             var cal1 = new CalendarTime(document.forms['frmLeadDashboard'].elements['createdDateFrom']);
				                 cal1.year_scroll = true;
				                 cal1.time_comp = false;

                                             var cal2 = new CalendarTime(document.forms['frmLeadDashboard'].elements['createdDateTo']);
				                 cal2.year_scroll = true;
				                 cal2.time_comp = false;
                                                 
                                                  var cal3 = new CalendarTime(document.forms['frmLeadDashboard'].elements['inactiveDateFrom']);
				                 cal3.year_scroll = true;
				                 cal3.time_comp = false;
                                                 
                                                  var cal4 = new CalendarTime(document.forms['frmLeadDashboard'].elements['inactiveDateTo']);
				                 cal4.year_scroll = true;
				                 cal4.time_comp = false;

						

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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->

        </table>
        <!--//END MAIN TABLE : Table for template Structure-->

        <!--  End of the main table -->        

 <script type="text/javascript">
		$(window).load(function(){
		getLeadList();
		});
		</script>
    </body>
</html>
 