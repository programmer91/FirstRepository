<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<html>
<head>
    <title>Hubble Organization Portal :: Employee Leave Request</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CurrentDateUtilplus2.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/employee/DateValidator.js"/>"></script>
    <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/LeaveRequestValidation.js"/>"></script>  --%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <s:include value="/includes/template/headerScript.html"/>
</head>
<!-- onload="doTime()" -->
    
<body class="bodyGeneral" oncontextmenu="return false;">
<!--//START MAIN TABLE : Table for template Structure-->
<table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0">

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
    <table class="innerTableLogin" cellpadding="0" cellspacing="0">
        <tr>
            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
            <td width="150px;" class="leftMenuBgColor" valign="top"> 
                <s:include value="/includes/template/LeftMenu.jsp"/> 
            </td>
            <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                            
            <!--//START DATA COLUMN : Coloumn for Screen Content-->
            <td width="850px" class="cellBorder" valign="top" style="padding-left:5px;padding-top:5px;">
            <!--//START TABBED PANNEL : --> 
            <ul id="accountTabs" class="shadetabs" >
                <li ><a href="#" class="selected" rel="personalDetailsTab"  >LeaveRequestDetails</a></li>
                
            </ul>
            <div  style="border:1px solid gray; width:830px;height: 250px; overflow:auto; margin-bottom: 1em;">
            <%--<sx:tabbedpanel id="leavePannel" cssStyle="width:830px; height:250px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>
                                    
            <!--//START TAB : -->
            <%--  <sx:div id="personalDetailsTab" label="LeaveRequestDetails"  > --%>
            <div id="personalDetailsTab" class="tabcontent"  >
            <s:form name="leaveForm" action="%{currentAction}" theme="simple" onsubmit="return checkLeaveForm();">
            <table>
                <tr><td> <font class="fieldLabel" style="margin-left: 53px;">Employee Name :</font><font size="2px" color="green" > <s:property value="#request.empName"/></font></td></tr>
                <tr>
            </table>
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr class="headerText" align="right">
                <td colspan="4">
                    <s:property value="#request.resultMessage"/>
                    <!--<input type="button" name="submit" value="Back" Class="buttonBg" onclick="javascript:history.go(-2)"/>-->
                    </td>
                
            </tr>
            
            <s:if test="%{currentLeave.empId!=''}">  
                <s:hidden name="empId" value="%{currentLeave.empId}" />
            </s:if>
            <s:else>  
                <s:hidden name="empId" value="%{empId}" />
            </s:else>
            
            <s:hidden name="action" value="%{currentAction}"/>  
            <s:hidden name="id" value="%{currentLeave.Id}"/> 
            
            
            <td class="fieldLabel">Leave Required From&nbsp;:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
            <td>
                
                <s:textfield name="leaveRequiredFrom" id="leaveRequiredFrom" value="%{currentLeave.leaveStartDate}"  cssClass="inputTextBlue"/>
                <a href="javascript:cal1.popup();">
                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                     width="20" height="20" border="0"></a>
                
            </td>  
            <td class="fieldLabel" >Required To&nbsp;:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
            <td>
                <!-- value="%{leaveRequiredTo}"  value="%{leaveRequiredFrom}"  -->
                <s:textfield name="leaveRequiredTo" value="%{currentLeave.leaveEndDate}" id="leaveRequiredTo" cssClass="inputTextBlue"/>
                <a href="javascript:cal2.popup();">
                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                     width="20" height="20" border="0"></a>
            </td>                    
            
        </tr>
        
        
        <tr>
            <td class="fieldLabel">Leave Type&nbsp;:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
            <td>
                <s:select
                    name="leaveType"
                    id="leaveType"
                    headerKey="-1" 
                    headerValue="--Please Select--"
                list="{'Post Approval','Comptime','Vacation','Timeoff','Cancel-Leave'}" cssClass="inputSelect"  value="%{currentLeave.leaveType}" />  </td> 
            <td class="fieldLabel">Reports To&nbsp;:</td>
            <td>
                
                <s:if test="%{currentLeave.reportsTo!=''}">  
                    <s:textfield name="oneLevelreportsToId" readonly="true"  cssClass="inputTextBlue"  value="%{currentLeave.reportsTo}" /> 
                </s:if>
                <s:else>  
                    <s:textfield name="oneLevelreportsToId" readonly="true"  cssClass="inputTextBlue" value="%{oneLevelReportsTo}"/>     
                </s:else>
            </td>
        </tr>
        <tr>
            <td class="fieldLabel" > </td>
            <td><s:hidden name="reportsTo"  cssClass="inputTextBlue" value="%{levelReportsTo}"/></td> 
        </tr>                                                
        <tr>
            <td class="fieldLabel">Reason&nbsp;:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
            <td colspan="3"><s:textarea name="reason" id="reason" cssClass="inputTextarea"  value="%{currentLeave.reason}" onchange="fieldLengthValidator(this);" cols="103" rows="5" /> </td>
        </tr>
        <tr>
            <s:if test="%{operationType!='request'}">  
                <td class="fieldLabel">Status&nbsp;:</td>
                <td><s:select
                        name="status" 
                        headerKey="-1" 
                        headerValue="--Please Select--"
                    list="{'Applied','Approved','Cancelled'}" cssClass="inputSelect"  value="%{currentLeave.Status}" />  </td>                          
            </s:if>
            <s:else>
                <td><s:hidden name="status" value="Applied" cssClass="inputSelect"/></td> 
            </s:else>
        </tr>
        <tr>
        <td colspan="4" align="right" ><s:if test="#request.currentAction != 'editLeave'">
                <s:submit style="margin-right: 137px;margin-top: 5px;" name="submit" value="submit" cssClass="buttonBg"/></s:if></td>
        </tr>
     
    </table> 
            <s:if test="%{currentLeave.departmentId != ''}">  
            <s:hidden name="departmentId"  cssClass="inputTextBlue" value="%{currentLeave.departmentId}"/> 
        </s:if>
        <s:else>
            <s:hidden name="departmentId"  cssClass="inputTextBlue" value="%{departmentId}"/> 
        </s:else>
        
        <s:if test="%{currentLeave.orgId!=''}">  
            <s:hidden name="orgId"  cssClass="inputTextBlue" value="%{currentLeave.orgId}"/>
        </s:if>
        <s:else>
            <s:hidden name="orgId"  cssClass="inputTextBlue" value="%{orgId}"/>
        </s:else> 
    </s:form>
    <script type="text/JavaScript">
                                            var cal1 = new CalendarTime(document.forms['leaveForm'].elements['leaveRequiredFrom']);
				                 cal1.year_scroll = true;
				                   cal1.time_comp = true;
                                                   
                                           var cal2 = new CalendarTime(document.forms['leaveForm'].elements['leaveRequiredTo']);
				                 cal2.year_scroll = true;
				                   cal2.time_comp = true;      
                                            
    </script>
    
    <%-- </sx:div > --%>
    </div>
    <!--//END TAB : -->
                                    
    <%-- </sx:tabbedpanel> --%>
    </div>
    <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

    </script>
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
    <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
</tr>
<!--//END FOOTER : Record for Footer Background and Content-->
            
</table>
<!--//END MAIN TABLE : Table for template Structure-->
        
        
        
        
        
</body>
</html>


