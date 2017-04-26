<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  May 30, 2008, 4:25 PM
 *
 * Author  : Harikrishna Kondala<hkondala@miraclesoft.com>
 *
 * File    : EmpTravel.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/tml; charset=UTF-8">
        <title>Hubble Organization Portal :: Travel Information</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <!-- issues Related JavaScript  -->
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AjaxUtil.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpSearch.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EnableEnter.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
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
                            
                            <td width="850px" class="cellBorder" valign="top">
                                <table cellpadding="0" cellspacing="0" width="100%" border="0">
                                    <tr style="font-family: arial,verdana; font-size:12px;">
                                        <td>
                                            <span class="fieldLabel">Employee Name :</span>&nbsp;
                                            <s:if test="currentTravel == null">
                                                <a class="navigationText" href="<s:url action="getEmployee"><s:param name="empId" value="%{id}"/></s:url>"><s:property value="%{employeeName}"/></a>
                                            </s:if>
                                            <s:else>
                                                <a class="navigationText" href="<s:url action="getEmployee"><s:param name="empId" value="%{currentTravel.empId}"/></s:url>"><s:property value="%{employeeName}"/></a>
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" style="padding: 5px 5px;">
                                            <ul id="accountTabs" class="shadetabs" >
                                                <li ><a href="#" class="selected" rel="empTravelTab"  > EmpTravel Information </a></li>
                                            </ul>
                                          <%--  <sx:tabbedpanel id="empTravel" cssStyle="width: 845px; height: 500px;padding: 5px 5px" doLayout="true"> --%>
                                            <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                                <%--  <sx:div id="empTravelTab" label="EmpTravel Information" cssStyle="overflow:auto;"> --%>
                                                <div id="empTravelTab" class="tabcontent"  >
                                                    <s:form name="travelForm" action="%{formAction}" theme="simple" method="post">
                                                        <table width="100%" border="0" cellspacing=0>
                                                            <tr class="headerText"> 
                                                                <td align="left"> 
                                                                    <b>Travel Information</b>
                                                                </td>
                                                                <td colspan="5" align="right">
                                                                    <s:property value="#request.resultMessage"/>
                                                                    <s:if test="currentTravel == null">
                                                                        <s:hidden name="id" value="%{id}"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="id" value="%{currentTravel.empId}"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">Last Name</td>
                                                                <td class="fieldLabelLeft"><b><s:property value="%{currentEmployee.lName}"/></b>
                                                                </td>
                                                                <td class="fieldLabelLeft">First Name</td>
                                                                <td class="fieldLabelLeft"><b><s:property value="%{currentEmployee.fName}"/></b>
                                                                </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">Middle Name</td>
                                                                <td class="fieldLabelLeft"><b><s:property value="%{currentEmployee.mName}"/></b>
                                                                </td>
                                                                <td class="fieldLabelLeft">Organization</td>
                                                                <td class="fieldLabelLeft"><b><s:property value="%{currentEmployee.organization}"/></b>
                                                                </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">Department</td>                                        
                                                                <td class="fieldLabelLeft"><b><s:property value="%{currentEmployee.department}"/></b>
                                                                </td>
                                                                <td class="fieldLabelLeft">Title</td>                                        
                                                                <td class="fieldLabelLeft"><b><s:property value="%{currentEmployee.title}"/></b>
                                                                </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">Status</td>                                        
                                                                <td class="fieldLabelLeft"><b><s:property value="%{currentEmployee.status}"/></b>
                                                                </td>
                                                                <td class="fieldLabelLeft">Employee Type</td>                                       
                                                                <td class="fieldLabelLeft"><b><s:property value="%{currentEmployee.employeeType}"/></b>
                                                                </td>
                                                            </tr>
                                                            <tr> 
                                                                <tr> 
                                                                    <td colspan="2" class="headerText" align="left" height="20"> 
                                                                    <b>Corporate Card Information</b> </td>
                                                                    <td colspan="2" class="headerText" align="left" height="20"> 
                                                                    <b>Personal Card Information</b> </td>
                                                                </tr>
                                                                <tr style="font-family: arial,verdana; font-size:12px;">
                                                                    <td class="fieldLabelLeft">Card Type</td>
                                                                    <td> 
                                                                        <s:textfield name="CorpCardType" cssClass="inputTextBlue" value="%{currentTravel.CorpCardType}"/>
                                                                    </td>
                                                                    <td class="fieldLabelLeft">Card Type</td>
                                                                    <td> 
                                                                        <s:textfield name="PerCardType" cssClass="inputTextBlue" value="%{currentTravel.PerCardType}"/>
                                                                    </td>
                                                                </tr>
                                                            </tr>
                                                            
                                                            <tr style="font-family: arial,verdana; font-size:12px;">
                                                                <td class="fieldLabelLeft">Card No</td>
                                                                <td>
                                                                    <s:textfield name="CorpCardNo" cssClass="inputTextBlue" value="%{currentTravel.CorpCardNo}"/>
                                                                </td>
                                                                <td class="fieldLabelLeft">Card No</td>
                                                                <td>
                                                                    <s:textfield name="PerCardNo" cssClass="inputTextBlue" value="%{currentTravel.PerCardNo}"/>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr style="font-family: arial,verdana; font-size:12px;">
                                                                <td class="fieldLabelLeft">Expiry Date</td>
                                                                <td>
                                                                    <s:textfield name="CorpExpDate" id="CorpExpDate" cssClass="inputTextBlue" value="%{currentTravel.CorpExpDate}"/>
                                                                    <a href="javascript:cal1.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a> 
                                                                </td>
                                                                <td class="fieldLabelLeft">Expiry Date</td>  <td>
                                                                    <s:textfield name="PerExpDate" id="PerExpDate"
                                                                                 cssClass="inputTextBlue" value="%{currentTravel.PerExpDate}"/>
                                                                    <a href="javascript:cal2.popup();">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a> 
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr style="font-family: arial,verdana; font-size:12px;">
                                                                <td class="fieldLabelLeft">Name On Card</td>
                                                                <td>
                                                                    <s:textfield name="CorpNameOnCard" cssClass="inputTextBlueLarge" value="%{currentTravel.CorpNameOnCard}"/>
                                                                </td>
                                                                <td class="fieldLabelLeft">Name On Card</td>  <td>
                                                                    <s:textfield name="PerNameOnCard" cssClass="inputTextBlueLarge" value="%{currentTravel.PerNameOnCard}"/>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr style="font-family: arial,verdana; font-size:12px;">
                                                                <td class="fieldLabelLeft">Card Code</td>
                                                                <td>
                                                                    <s:textfield name="CorpCardCode" cssClass="inputTextBlue" value="%{currentTravel.CorpCardCode}"/>
                                                                </td>
                                                                <td class="fieldLabelLeft">Card Code</td>
                                                                <td>
                                                                    <s:textfield name="PerCardCode" cssClass="inputTextBlue" value="%{currentTravel.PerCardCode}"/>
                                                                </td>
                                                            </tr>
                                                            
                                                            
                                                            <tr> 
                                                                <td colspan="4" class="headerText" align="left" height="20"> 
                                                                <b>Frequently Flyer Related Information</b></td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">FQ Program 1</td>
                                                                <td> 
                                                                    <s:textfield name="FQProg1" cssClass="inputTextBlue" value="%{currentTravel.FQProg1}"/>
                                                                </td>
                                                                <td class="fieldLabelLeft">FQ No. 1</td>
                                                                <td> 
                                                                    <s:textfield name="FQNo1" cssClass="inputTextBlue" value="%{currentTravel.FQNo1}"/>
                                                                </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">FQ Program 2 </td>
                                                                <td> 
                                                                    <s:textfield name="FQProg2" cssClass="inputTextBlue" value="%{currentTravel.FQProg2}"/>
                                                                </td>
                                                                <td class="fieldLabelLeft">FQ No. 2 </td>
                                                                <td> 
                                                                    <s:textfield name="FQNo2" cssClass="inputTextBlue" value="%{currentTravel.FQNo2}"/>
                                                                </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">FQ Program 3</td>
                                                                <td> 
                                                                    <s:textfield name="FQProg3" cssClass="inputTextBlue" value="%{currentTravel.FQProg3}"/>
                                                                </td>
                                                                <td class="fieldLabelLeft">FQ No. 3</td>
                                                                <td> 
                                                                    <s:textfield name="FQNo3" cssClass="inputTextBlue" value="%{currentTravel.FQNo3}"/>
                                                                </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">FQ Program 4 </td>
                                                                <td> 
                                                                    <s:textfield name="FQProg4" cssClass="inputTextBlue" value="%{currentTravel.FQProg4}"/>
                                                                </td>
                                                                <td class="fieldLabelLeft">FQ No. 4 </td>
                                                                <td> 
                                                                    <s:textfield name="FQNo4" cssClass="inputTextBlue" value="%{currentTravel.FQNo4}"/>
                                                                </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">FQ Program 5</td>
                                                                <td> 
                                                                    <s:textfield name="FQProg5" cssClass="inputTextBlue" value="%{currentTravel.FQProg5}"/>
                                                                </td>
                                                                <td class="fieldLabelLeft">FQ No. 5</td>
                                                                <td> 
                                                                    <s:textfield name="FQNo5" cssClass="inputTextBlue" value="%{currentTravel.FQNo5}"/>
                                                                </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">FQ Program 6</td>
                                                                <td> 
                                                                    <s:textfield name="FQProg6" cssClass="inputTextBlue" value="%{currentTravel.FQProg6}"/>
                                                                </td>
                                                                <td class="fieldLabelLeft">FQ No. 6</td>
                                                                <td> 
                                                                    <s:textfield name="FQNo6" cssClass="inputTextBlue" value="%{currentTravel.FQNo6}"/>
                                                                </td>
                                                            </tr>
                                                            <tr> 
                                                                <td colspan="4" class="headerText" align="left" height="20"> 
                                                                <b>Preferences</b></td>
                                                            </tr>
                                                            
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">Seat</td>
                                                                <td> 
                                                                    <s:select name="PrefSeat" cssClass="inputTextBlue" list="{'Aile','Window','Dont Care'}" value="%{currentTravel.PrefSeat}"/>
                                                                </td>
                                                                <td class="fieldLabelLeft">Meals</td>
                                                                <td> 
                                                                    <s:select name="PrefMeals" cssClass="inputTextBlue" list="{'Veg.','Non-Veg.','Dont Care'}" value="%{currentTravel.PrefMeals}"/>
                                                                </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">Car Co.Preference</td>
                                                                <td> 
                                                                    <s:textfield name="PrefCarCo" cssClass="inputTextBlue" value="%{currentTravel.PrefCarCo}"/>
                                                                </td>
                                                                <td class="fieldLabelLeft">Air-Line Preference</td>
                                                                <td> 
                                                                    <s:textfield name="PrefAirLine" cssClass="inputTextBlue" value="%{currentTravel.PrefAirLine}"/>
                                                                </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabelLeft">Hotel Preference</td>
                                                                <td> 
                                                                    <s:textfield name="PrefHotel" cssClass="inputTextBlue" value="%{currentTravel.PrefHotel}"/>
                                                                </td>
                                                                <td class="fieldLabelLeft">Other</td>
                                                                <td> 
                                                                    <s:textfield name="PrefOther" cssClass="inputTextBlue" value="%{currentTravel.PrefOther}"/>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <table width="100%" border="0">
                                                            <tr> 
                                                                <td colspan="4" class="headerText" align="left" height="20"> 
                                                                <b>Comments</b> </td>
                                                            </tr>
                                                            <tr style="font-family: arial,verdana; font-size:12px;"> 
                                                                <td class="fieldLabel"> Travel Comments </td>		
                                                                <td> 
                                                                    <s:textarea name="Comments" rows="4" wrap="physical" cols="79" cssClass="inputTextarea" value="%{currentTravel.Comments}"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="4" align="center">
                                                                    <s:submit name="submit" value="Save" cssClass="buttonBg"/>&nbsp;
                                                                    <s:submit name="cancel" value="Cancel" cssClass="buttonBg" onclick='document.travelForm.action="./getEmployee.action?empId=%{id}"'/>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </s:form>
                                                    <script type="text/JavaScript">
         var cal1 = new CalendarTime(document.forms['travelForm'].elements['CorpExpDate']);
         cal1.year_scroll = true;
         cal1.time_comp = false;
          var cal2 = new CalendarTime(document.forms['travelForm'].elements['PerExpDate']);
         cal2.year_scroll = true;
         cal2.time_comp = false;
                                                    </script>  
                                                    <%-- </sx:div> --%>
                                                </div>
                                                <%--  </sx:tabbedpanel> --%>
                                            </div>
                                             <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
        </table>
    </body>
</html>
