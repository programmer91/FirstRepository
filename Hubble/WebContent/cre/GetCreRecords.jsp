<!-- 
 /* ******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  : Rajasekhar Yenduva<ryenduva@miraclesoft.com>
 *
 * File    : IssueDetails.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Cre Records</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/cre/CreAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/EcertStandardClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/cre/EmpStandardClientValidations.js"/>"></script>
        
      <!--  <script>
            function examDetailsPopup(url) {
                newwindow=window.open(url,'name','height=600,width=500,top=200,left=250');
                if (window.focus) {newwindow.focus()}
           }
            
        </script> -->
        <s:include value="/includes/template/headerScript.html"/>
    </head>
   <!-- <body class="bodyGeneral" oncontextmenu="return false;"  onload="init();"> -->
    <body class="bodyGeneral" oncontextmenu="return false;" >
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:10px 5px 5px 5px;">
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" class="selected" rel="getCreRecords"  > Get Cre Records</a></li>
    					 
                                </ul>
                                <%-- <sx:tabbedpanel id="empSearch" cssStyle="width: 845px; height: 500px;padding:10px 5px 5px 5px" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:div id="empSearchTab" label="Employee Search" cssStyle="overflow: auto;"> --%>
                                    <div id="getCreRecords" class="tabcontent"  >
                                        <s:form action="" theme="simple" name="creSearchForm" >
                                            <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                
                                                                <tr>
                                                                    <td>
                                                                    <table align="center" border="0" width="90%" cellpadding="2" cellspacing="2" id="getCreSearch">
                                                                        <tr>
                                                                            <td class="fieldLabel">Consultant&nbsp;Id&nbsp;:</td>
                                                                        </tr>
                                                                    <tr>
                                                                         <td class="fieldLabel">From:</td>
                                                                   <td> 
                                                                   <s:textfield name="creConsultantId" id="creConsultantId"  cssClass="inputTextBlue"  value=""  theme="simple"/>
                                                                 </td>
                                                                   <td class="fieldLabel">To:</td>
                                                                   <td> 
                                                                   <s:textfield name="creConsultantId1" id="creConsultantId1"  cssClass="inputTextBlue"  value=""  theme="simple"/>
                                                                 </td>
                                                                 <td class="fieldLabel">Status&nbsp;:<font color="red">*</font></td>
                                                                   <td> 
                                                                   <s:select name="creConsultantStatus" list="{'Registered','Active','Process','Hold','Selected','Rejected','PASS','FAIL'}" id="creConsultantStatus" cssClass="inputSelect" value="" headerKey="" headerValue="--Select Status--"/>
                                                                 </td>
                                                                 
                                                                       <%-- <td class="fieldLabel">Consultant&nbsp;Name&nbsp;: </td>
                                                                        <td > 
                                                                            <s:textfield name="creConsultantName" id="creConsultantName"  cssClass="inputTextBlue"  value=""  theme="simple"/>
                                                                        </td> --%>
                                                              
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">Start&nbsp;Date&nbsp;: </td>
                                                        <td > <s:textfield name="startDate" cssClass="inputTextBlueSmall" id="startDate" />
                                                            <a href="javascript:cal1.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20" border="0"></a></td>
                                                             
                                                             <td class="fieldLabel">To&nbsp;Date&nbsp;: </td>
                                                        <td > <s:textfield name="toDate" cssClass="inputTextBlueSmall" id="toDate" />
                                                            <a href="javascript:cal.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20" border="0"></a></td>
                                                             
                                                              <script type="text/javascript">
                                                          var cal1 = new CalendarTime(document.forms['creSearchForm'].elements['startDate']);
                                                             cal1.year_scroll = true;
                                                             cal1.time_comp = false;
                                                             
                                                             var cal = new CalendarTime(document.forms['creSearchForm'].elements['toDate']);
                                                             cal.year_scroll = true;
                                                             cal.time_comp = false;
                                                         </script>
                                                   <%--  <td class="fieldLabel"  align="right">Category :</td>
                                                    <td>
                                                       <s:select name="category"  id="category"  cssClass="inputSelect" headerKey="0" headerValue="Select Category" list="#@java.util.LinkedHashMap@{'1':'Software Trainee','2':'Sr.Developer','3':'Sales','4':'IT Recruitment','5':'Operations','6':'Networking','7':'Trainer','8':'Civil','9':'MES','10':'Other'}" theme="simple"></s:select> 
                                                    </td> --%>
                                                          
                                                               <td class="fieldLabel">Interview&nbsp;at</td>
                                                        <td > 
                                                            <%--<s:textfield name="status" id="status"  cssClass="inputTextBlue"  value="%{status}"  theme="simple"/> --%>
                                                            <s:select headerKey="" headerValue="--Please Select--" list="#@java.util.LinkedHashMap@{'1':'MiracleCampus','2':'CampusRecruitment','3':'JobFair'}" name="interviewAt" id="interviewAt" cssClass="inputSelect"  value="%{interviewAt}"  theme="simple">
                                                            </s:select>
                                                        </td> 
                                                                
                                                              
                                                            </tr>
                                                            <tr><td class="fieldLabel">College&nbsp;Name</td>
                                                              <td>   
                                                               <s:select  name="recLocation" headerKey="" headerValue="--Please Select--" tabindex="2" id="recLocation"  cssClass="inputSelect" list="recLocationList"  theme="simple" onchange="inputField()"></s:select>  
                                                               </td>
                                                               <td class="fieldLabel">Course:</td>
                                                               <td>
                                                                   <s:select name="course" id="course" cssClass="inputSelect" headerKey="-1" headerValue="--Select Course--" list="{'B.Tech','B.E','B.Sc','B.Com','B.A','BBM','MCA','MBA','M.Tech','M.Com','M.Sc','MS','Other'}" onchange="getStream(this.form, this.value);"></s:select>
                                                               </td>
                                                               <td class="fieldLabel">Branch</td>
                                                               <td><select name="creStream" id="creStream" class="inputSelect" ><option value="">--Please Select--</option></select></td>
                                                           </tr>

                                                            <tr>
                                                       
                                                       
                                                    <%--   <td class="fieldLabel">Level</td> 
                                                        <td >
                                                            <%--<s:textfield name="level" id="level"  cssClass="inputTextBlue"  value="%{level}"  theme="simple"/> 
                                                            <s:select headerKey="" headerValue="--Please Select--" list="{'Registration','Exam','Tech Level','HR level','VP level'}" name="level" id="level" cssClass="inputSelect"  value="%{level}"  theme="simple"></s:select>
                                                        </td>--%>
                                                       <%--  <td class="fieldLabel">Status&nbsp;:</td>
                                                                   <td> 
                                                                   <s:select name="creConsultantStatus" list="{'Registered','Active','Process','Hold','Selected','Rejected'}" id="creConsultantStatus" cssClass="inputSelect" value="" headerKey="" headerValue="--Select Status--"/>
                                                                 </td>--%>
                                                                   <%--  <td class="fieldLabel">Interview&nbsp;at</td>
                                                        <td > 
                                                            <%--<s:textfield name="status" id="status"  cssClass="inputTextBlue"  value="%{status}"  theme="simple"/> 
                                                            <s:select headerKey="" headerValue="--Please Select--" list="#@java.util.LinkedHashMap@{'1':'MiracleCampus','2':'CampusRecruitment','3':'JobFair'}" name="interviewAt" id="interviewAt" cssClass="inputSelect"  value="%{interviewAt}"  theme="simple">
                                                            </s:select>
                                                        </td> --%>
                                                                
                                                        </tr>
                                                        <tr>
                                                            <td></td> <td></td> <td></td> <td></td> <td></td>
                                                                <td align="left" ><input type="button" value="Get Cre Records" class="buttonBg" onclick="getCreRecords();" id="btnGetRecords"/></td>
                                                        </tr>
                                                            
                                                         <%--   <tr>
                                                                
                                                               
                                                               <td align="left" ><input type="button" value="Acivate Consultants" class="buttonBg" onclick="setStatus('Active');" id="btnActive"/></td>
                                                               <td align="left" ><input type="button" value="Reject Consultants" class="buttonBg" onclick="setStatus('Rejected');" id="btnReject"/></td>
                                                            </tr> --%>
                                                        </table>
                                                    </td>
                                                </tr>
                                               
                                              
                                            </table>
                                        
                                        <%--  </sx:div> --%>
                                    </div>
                                     <div id="loadingMessage" style="display:none;" class="error" align="center" ><b><font size="4px" >Loading...Please Wait</font></b></div>
                                     <br>
                                      <table align="center" border="0" cellpadding="1" cellspacing="1">
                                          <tr><td> 
                                            <div style="height:500px;  overflow:auto; border:3px; border-right-width: 15px;border-bottom-width: 15px; border-left-width: 15px;display: none; "  id="creConsultantList">
                                              <table cellpadding="1" cellspacing="1" width="100%" class="gridTable" id="RevenueSummery">
                                                <tr class="gridHeader">
                                                  <td width="5%" class="gridHeader" ><input type="checkbox" name="text" id="text">Select&nbsp;All</td> <%-- onclick="checkAll(document.searchForm.check_List);" --%>
                                                  <td width="5%" class="gridHeader">ConsultantId </td>
                                                  <td width="5%" class="gridHeader" >ConsultantName</td>
                                                  <td width="5%" class="gridHeader" >Post&nbsp;Applied</td>
                                                  <td width="5%" class="gridHeader" >Status</td>
                                                  <td width="5%" class="gridHeader" >Course</td>
                                                  <td width="5%" class="gridHeader" >Stream</td>
                                                   
                                                </tr> 
                                                <tr class="gridRowEven">
                                                    <td  align="center"><div id="gridRow_1"> <input type="checkbox" name="check_List" id="check_List">
                                                    <input type="hidden" name="text1"  id="text1" class="gridCol" align="left" value="" readonly></div></td>
                                                    <td> <input type="text" name="texti1"  id="texti1" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta1"  id="texta1" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                   <td> <input type="text" name="textc1"  id="textc1" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts1"  id="texts1" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse1"  id="textcourse1" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream1"  id="textstream1" class="gridCol" align="left" value="" readonly></td>
                                                    
                                                    
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_2"> <input type="checkbox" name="check_List" id="check_List">
                                                    <input type="hidden" name="text2" id="text2" value="" class="gridCol" align="left" readonly></div></td>
                                                     <td> <input type="text" name="texti2"  id="texti2" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta2"  id="texta2" value="" class="gridColumnMediumLarge" align="left" readonly></td><%--  onclick="getAccountDetails('6')" --%>
                                                    <td> <input type="text" name="textc2"  id="textc2" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts2"  id="texts2" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse2"  id="textcourse2" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream2"  id="textstream2" class="gridCol" align="left" value="" readonly></td>
                                                    </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"> <div id="gridRow_3"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('9')"--%>
                                                    <input type="hidden" name="text3"  id="text3" value="" class="gridCol" align="left" readonly></div></td>
                                                     <td> <input type="text" name="texti3"  id="texti3" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta3"  id="texta3" value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc3"  id="textc3" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts3"  id="texts3" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse3"  id="textcourse3" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream3"  id="textstream3" class="gridCol" align="left" value="" readonly></td>
                                                   
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_4"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('12')"--%>
                                                     <input type="hidden" name="text4"  id="text4" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti4"  id="texti4" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta4"  id="texta4"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc4"  id="textc4" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts4"  id="texts4" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse4"  id="textcourse4" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream4"  id="textstream4" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_5"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('15')"--%>
                                                    <input type="hidden" name="text5"  id="text5" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti5"  id="texti5" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta5"  id="texta5"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc5"  id="textc5" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts5"  id="texts5" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse5"  id="textcourse5" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream5"  id="textstream5" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_6"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('18')"--%>
                                                    <input type="hidden" name="text6"  id="text6" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti6"  id="texti6" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta6"  id="texta6"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc6"  id="textc6" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts6"  id="texts6" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse6"  id="textcourse6" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream6"  id="textstream6" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"> <div id="gridRow_7"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('21')"--%>
                                                     <input type="hidden" name="text7"  id="text7" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti7"  id="texti7" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta7"  id="texta7"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc7"  id="textc7" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts7"  id="texts7" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse7"  id="textcourse7" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream7"  id="textstream7" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_8"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('24')"--%>
                                                    <input type="hidden" name="text8"  id="text8" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti8"  id="texti8" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta8"  id="texta8"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc8"  id="textc8" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts8"  id="texts8" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse8"  id="textcourse8" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream8"  id="textstream8" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                              <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_9"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('27')"--%>
                                                    <input type="hidden" name="text9"  id="text9" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti9"  id="texti9" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta9"  id="texta9"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc9"  id="textc9" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts9"  id="texts9" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse9"  id="textcourse9" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream9"  id="textstream9" class="gridCol" align="left" value="" readonly></td>
                                              </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_10"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                     <input type="hidden" name="text10"  id="text10" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti10"  id="texti10" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta10"  id="texta10"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc10"  id="textc10" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts10"  id="texts10" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse10"  id="textcourse10" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream10"  id="textstream10" class="gridCol" align="left" value="" readonly></td>
                                                 </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_11"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                    <input type="hidden" name="text11"  id="text11" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti11"  id="texti11" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta11"  id="texta11"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc11"  id="textc11" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts11"  id="texts11" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse11"  id="textcourse11" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream11"  id="textstream11" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                                    <td align="center"> <div id="gridRow_12"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                                    <input type="hidden" name="text12"  id="text12" value="" class="gridCol" align="left" readonly ></div></td>
                                                                     <td> <input type="text" name="texti12"  id="texti12" class="gridCol" align="left" value="" readonly></td>
                                                                    <td> <input type="text" name="texta12"  id="texta12"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                    
                                                                    <td> <input type="text" name="textc12"  id="textc12" class="gridCol" align="left" value="" readonly></td>
                                                                    <td> <input type="text" name="texts12"  id="texts12" class="gridCol" align="left" value="" readonly></td>
                                                                    <td> <input type="text" name="textcourse12"  id="textcourse12" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream12"  id="textstream12" class="gridCol" align="left" value="" readonly></td>
                                                   </tr>
                                                   <tr class="gridRowEven">
                                                       <td align="center"><div id="gridRow_13"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                         <input type="hidden" name="text13"  id="text13" value="" class="gridCol" align="left" readonly ></div></td>
                                                          <td> <input type="text" name="texti13"  id="texti13" class="gridCol" align="left" value="" readonly></td>
                                                        <td> <input type="text" name="texta13"  id="texta13"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                        
                                                        <td> <input type="text" name="textc13"  id="textc13" class="gridCol" align="left" value="" readonly></td>
                                                        <td> <input type="text" name="texts13"  id="texts13" class="gridCol" align="left" value="" readonly></td>
                                                        <td> <input type="text" name="textcourse13"  id="textcourse13" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream13"  id="textstream13" class="gridCol" align="left" value="" readonly></td>
                                                    </tr>
                                                    <tr class="gridRowEven">
                                                     <td align="center"> <div id="gridRow_14"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                     <input type="hidden" name="text14"  id="text14" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti14"  id="texti14" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texta14"  id="texta14"  value="" class="gridColumnMediumLarge" align="left" readonly></td>  
                                                     
                                                     <td> <input type="text" name="textc14"  id="textc14" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texts14"  id="texts14" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="textcourse14"  id="textcourse14" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream14"  id="textstream14" class="gridCol" align="left" value="" readonly></td>
                                                    </tr>
                                                    <tr class="gridRowEven">
                                                                    <td align="center"> <div id="gridRow_15"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                                     <input type="hidden" name="text15"  id="text15" value="" class="gridCol" align="left" readonly ></div></td>
                                                                      <td> <input type="text" name="texti15"  id="texti15" class="gridCol" align="left" value="" readonly></td>
                                                                     <td> <input type="text" name="texta15"  id="texta15"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                     
                                                                     <td> <input type="text" name="textc15"  id="textc15" class="gridCol" align="left" value="" readonly></td>
                                                                     <td> <input type="text" name="texts15"  id="texts15" class="gridCol" align="left" value="" readonly></td>
                                                                     <td> <input type="text" name="textcourse15"  id="textcourse15" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream15"  id="textstream15" class="gridCol" align="left" value="" readonly></td>
                                                      </tr>
                                                      <tr class="gridRowEven">
                                                                  <td align="center"><div id="gridRow_16"> <input type="checkbox" name="check_List" id="check_List">
                                                                <input type="hidden" name="text16"  id="text16" value="" class="gridCol" align="left" readonly ></div></td>
                                                                 <td> <input type="text" name="texti16"  id="texti16" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texta16"  id="texta16"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                <td> <input type="text" name="textc16"  id="textc16" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texts16"  id="texts16" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="textcourse16"  id="textcourse16" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream16"  id="textstream16" class="gridCol" align="left" value="" readonly></td>
                                                       </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                                <td align="center"> <div id="gridRow_17"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                                <input type="hidden" name="text17"  id="text17" value="" class="gridCol" align="left" readonly ></div></td>
                                                                 <td> <input type="text" name="texti17"  id="texti17" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texta17"  id="texta17"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                
                                                                <td> <input type="text" name="textc17"  id="textc17" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texts17"  id="texts17" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse17"  id="textcourse17" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream17"  id="textstream17" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_18"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                            <input type="hidden" name="text18"  id="text18" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti18"  id="texti18" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta18"  id="texta18"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc18"  id="textc18" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts18"  id="texts18" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse18"  id="textcourse18" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream18"  id="textstream18" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                        
                                                         <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_19"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text19"  id="text19" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti19"  id="texti19" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta19"  id="texta19"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc19"  id="textc19" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts19"  id="texts19" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse19"  id="textcourse19" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream19"  id="textstream19" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                       
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_20"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text20"  id="text20" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti20"  id="texti20" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta20"  id="texta20"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                           <td> <input type="text" name="textc20"  id="textc20" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="texts20"  id="texts20" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="textcourse20"  id="textcourse20" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream20"  id="textstream20" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_21"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text21"  id="text21" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti21"  id="texti21" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta21"  id="texta21"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc21"  id="textc21" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts21"  id="texts21" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse21"  id="textcourse21" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream21"  id="textstream21" class="gridCol" align="left" value="" readonly></td>
                                                            
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_22"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text22"  id="text22" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti22"  id="texti22" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta22"  id="texta22"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc22"  id="textc22" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts22"  id="texts22" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse22"  id="textcourse22" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream22"  id="textstream22" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_23"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text23"  id="text23" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti23"  id="texti23" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta23"  id="texta23"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc23"  id="textc23" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts23"  id="texts23" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse23"  id="textcourse23" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream23"  id="textstream23" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_24"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text24"  id="text24" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti24"  id="texti24" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta24"  id="texta24"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc24"  id="textc24" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts24"  id="texts24" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse24"  id="textcourse24" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream24"  id="textstream24" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_25"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text25"  id="text25" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti25"  id="texti25" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta25"  id="texta25"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc25"  id="textc25" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts25"  id="texts25" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse25"  id="textcourse25" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream25"  id="textstream25" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_26"> <input type="checkbox" name="check_List" id="check_List">
                                                                    <input type="hidden" name="text26"  id="text26" value="" class="gridCol" align="left" readonly ></div></td>
                                                                     <td> <input type="text" name="texti26"  id="texti26" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta26"  id="texta26"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc26"  id="textc26" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts26"  id="texts26" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse26"  id="textcourse26" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream26"  id="textstream26" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_27"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text27"  id="text27" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti27"  id="texti27" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta27"  id="texta27"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc27"  id="textc27" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts27"  id="texts27" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse27"  id="textcourse27" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream27"  id="textstream27" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_28"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text28"  id="text28" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti28"  id="texti28" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta28"  id="texta28"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc28"  id="textc28" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts28"  id="texts28" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse28"  id="textcourse28" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream28"  id="textstream28" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_29"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text29"  id="text29" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti29"  id="texti29" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta29"  id="texta29"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc29"  id="textc29" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts29"  id="texts29" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse29"  id="textcourse29" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream29"  id="textstream29" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_30"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text30"  id="text30" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti30"  id="texti30" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta30"  id="texta30"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc30"  id="textc30" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts30"  id="texts30" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse30"  id="textcourse30" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream30"  id="textstream30" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>

                                              <!-- satrt next 30 -->
                                                 <tr class="gridRowEven">
                                                    <td  align="center"><div id="gridRow_31"> <input type="checkbox" name="check_List" id="check_List">
                                                    <input type="hidden" name="text31"  id="text31" class="gridCol" align="left" value="" readonly></div></td>
                                                    <td> <input type="text" name="texti31"  id="texti31" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta31"  id="texta31" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                    
                                                     <td> <input type="text" name="textc31"  id="textc31" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texts31"  id="texts31" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="textcourse31"  id="textcourse31" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream31"  id="textstream31" class="gridCol" align="left" value="" readonly></td>
                                                    <%-- <td> <input type="checkbox" name="text3" id="text3" class="gridCol" align="left" value="Assign" onclick="getAccountDetails('3')"></td>--%>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_32"> <input type="checkbox" name="check_List" id="check_List">
                                                    <input type="hidden" name="text32" id="text32" value="" class="gridCol" align="left" readonly></div></td>
                                                     <td> <input type="text" name="texti32"  id="texti32" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta32"  id="texta32" value="" class="gridColumnMediumLarge" align="left" readonly></td><%--  onclick="getAccountDetails('6')" --%>
                                                    
                                                    <td> <input type="text" name="textc32"  id="textc32" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts32"  id="texts32" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse32"  id="textcourse32" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream32"  id="textstream32" class="gridCol" align="left" value="" readonly></td>
                                                    </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"> <div id="gridRow_33"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('9')"--%>
                                                    <input type="hidden" name="text33"  id="text33" value="" class="gridCol" align="left" readonly></div></td>
                                                     <td> <input type="text" name="texti33"  id="texti33" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta33"  id="texta33" value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc33"  id="textc33" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts33"  id="texts33" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse33"  id="textcourse33" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream33"  id="textstream33" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_34"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('12')"--%>
                                                     <input type="hidden" name="text34"  id="text34" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti34"  id="texti34" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta34"  id="texta34"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc34"  id="textc34" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts34"  id="texts34" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse34"  id="textcourse34" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream34"  id="textstream34" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_35"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('15')"--%>
                                                    <input type="hidden" name="text35"  id="text35" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti35"  id="texti35" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta35"  id="texta35"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc35"  id="textc35" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts35"  id="texts35" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse35"  id="textcourse35" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream35"  id="textstream35" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_36"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('18')"--%>
                                                    <input type="hidden" name="text36"  id="text36" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti36"  id="texti36" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta36"  id="texta36"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc36"  id="textc36" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts36"  id="texts36" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse36"  id="textcourse36" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream36"  id="textstream36" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"> <div id="gridRow_37"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('21')"--%>
                                                     <input type="hidden" name="text37"  id="text37" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti37"  id="texti37" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta37"  id="texta37"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc37"  id="textc37" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts37"  id="texts37" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse37"  id="textcourse37" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream37"  id="textstream37" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_38"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('24')"--%>
                                                    <input type="hidden" name="text38"  id="text38" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti38"  id="texti38" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta38"  id="texta38"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc38"  id="textc38" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts38"  id="texts38" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse38"  id="textcourse38" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream38"  id="textstream38" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                              <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_39"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('27')"--%>
                                                    <input type="hidden" name="text39"  id="text39" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti39"  id="texti39" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta39"  id="texta39"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc39"  id="textc39" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts39"  id="texts39" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse39"  id="textcourse39" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream39"  id="textstream39" class="gridCol" align="left" value="" readonly></td>
                                              </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_40"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                     <input type="hidden" name="text40"  id="text40" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti40"  id="texti40" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta40"  id="texta40"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc40"  id="textc40" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts40"  id="texts40" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse40"  id="textcourse40" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream40"  id="textstream40" class="gridCol" align="left" value="" readonly></td>
                                                 </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_41"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                    <input type="hidden" name="text41"  id="text41" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti41"  id="texti41" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta41"  id="texta41"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc41"  id="textc41" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts41"  id="texts41" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse41"  id="textcourse41" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream41"  id="textstream41" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                                    <td align="center"> <div id="gridRow_42"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                                    <input type="hidden" name="text42"  id="text42" value="" class="gridCol" align="left" readonly ></div></td>
                                                                     <td> <input type="text" name="texti42"  id="texti42" class="gridCol" align="left" value="" readonly></td>
                                                                    <td> <input type="text" name="texta42"  id="texta42"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                    
                                                                    <td> <input type="text" name="textc42"  id="textc42" class="gridCol" align="left" value="" readonly></td>
                                                                    <td> <input type="text" name="texts42"  id="texts42" class="gridCol" align="left" value="" readonly></td>
                                                                    <td> <input type="text" name="textcourse42"  id="textcourse42" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream42"  id="textstream42" class="gridCol" align="left" value="" readonly></td>
                                                   </tr>
                                                   <tr class="gridRowEven">
                                                       <td align="center"><div id="gridRow_43"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                         <input type="hidden" name="text43"  id="text43" value="" class="gridCol" align="left" readonly ></div></td>
                                                          <td> <input type="text" name="texti43"  id="texti43" class="gridCol" align="left" value="" readonly></td>
                                                        <td> <input type="text" name="texta43"  id="texta43"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                        
                                                        <td> <input type="text" name="textc43"  id="textc43" class="gridCol" align="left" value="" readonly></td>
                                                        <td> <input type="text" name="texts43"  id="texts43" class="gridCol" align="left" value="" readonly></td>
                                                        <td> <input type="text" name="textcourse43"  id="textcourse43" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream43"  id="textstream43" class="gridCol" align="left" value="" readonly></td>
                                                    </tr>
                                                    <tr class="gridRowEven">
                                                     <td align="center"> <div id="gridRow_44"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                     <input type="hidden" name="text44"  id="text44" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti44"  id="texti44" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texta44"  id="texta44"  value="" class="gridColumnMediumLarge" align="left" readonly></td>  
                                                     
                                                     <td> <input type="text" name="textc44"  id="textc44" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texts44"  id="texts44" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="textcourse44"  id="textcourse44" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream44"  id="textstream44" class="gridCol" align="left" value="" readonly></td>
                                                    </tr>
                                                    <tr class="gridRowEven">
                                                                    <td align="center"> <div id="gridRow_45"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                                     <input type="hidden" name="text45"  id="text45" value="" class="gridCol" align="left" readonly ></div></td>
                                                                      <td> <input type="text" name="texti45"  id="texti45" class="gridCol" align="left" value="" readonly></td>
                                                                     <td> <input type="text" name="texta45"  id="texta45"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                     
                                                                     <td> <input type="text" name="textc45"  id="textc45" class="gridCol" align="left" value="" readonly></td>
                                                                     <td> <input type="text" name="texts45"  id="texts45" class="gridCol" align="left" value="" readonly></td>
                                                                     <td> <input type="text" name="textcourse45"  id="textcourse45" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream45"  id="textstream45" class="gridCol" align="left" value="" readonly></td>
                                                      </tr>
                                                      <tr class="gridRowEven">
                                                                  <td align="center"><div id="gridRow_46"> <input type="checkbox" name="check_List" id="check_List">
                                                                <input type="hidden" name="text46"  id="text46" value="" class="gridCol" align="left" readonly ></div></td>
                                                                 <td> <input type="text" name="texti46"  id="texti46" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texta46"  id="texta46"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                
                                                                <td> <input type="text" name="textc46"  id="textc46" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texts46"  id="texts46" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="textcourse46"  id="textcourse46" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream46"  id="textstream46" class="gridCol" align="left" value="" readonly></td>
                                                       </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                                <td align="center"> <div id="gridRow_47"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                                <input type="hidden" name="text47"  id="text47" value="" class="gridCol" align="left" readonly ></div></td>
                                                                 <td> <input type="text" name="texti47"  id="texti47" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texta47"  id="texta47"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                
                                                               <td> <input type="text" name="textc47"  id="textc47" class="gridCol" align="left" value="" readonly></td>
                                                               <td> <input type="text" name="texts47"  id="texts47" class="gridCol" align="left" value="" readonly></td>
                                                               <td> <input type="text" name="textcourse47"  id="textcourse47" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream47"  id="textstream47" class="gridCol" align="left" value="" readonly></td>
                                                            
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_48"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                            <input type="hidden" name="text48"  id="text48" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti48"  id="texti48" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta48"  id="texta48"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc48"  id="textc48" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts48"  id="texts48" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse48"  id="textcourse48" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream48"  id="textstream48" class="gridCol" align="left" value="" readonly></td>
                                                            
                                                        </tr>
                                                        
                                                         <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_49"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text49"  id="text49" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti49"  id="texti49" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta49"  id="texta49"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc49"  id="textc49" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts49"  id="texts49" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse49"  id="textcourse49" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream49"  id="textstream49" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                       
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_50"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text50"  id="text50" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti50"  id="texti50" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta50"  id="texta50"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc50"  id="textc50" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts50"  id="texts50" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse50"  id="textcourse50" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream50"  id="textstream50" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_51"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text51"  id="text51" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti51"  id="texti51" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta51"  id="texta51"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc51"  id="textc51" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts51"  id="texts51" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse51"  id="textcourse51" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream51"  id="textstream51" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_52"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text52"  id="text52" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti52"  id="texti52" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta52"  id="texta52"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc52"  id="textc52" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts52"  id="texts52" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse52"  id="textcourse52" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream52"  id="textstream52" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_53"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text53"  id="text53" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti53"  id="texti53" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta53"  id="texta53"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc53"  id="textc53" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts53"  id="texts53" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse53"  id="textcourse53" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream53"  id="textstream53" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_54"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text54"  id="text54" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti54"  id="texti54" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta54"  id="texta54"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc54"  id="textc54" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts54"  id="texts54" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse54"  id="textcourse54" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream54"  id="textstream54" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_55"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text55"  id="text55" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti55"  id="texti55" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta55"  id="texta55"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc55"  id="textc55" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts55"  id="texts55" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse55"  id="textcourse55" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream55"  id="textstream55" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_56"> <input type="checkbox" name="check_List" id="check_List">
                                                                    <input type="hidden" name="text56"  id="text56" value="" class="gridCol" align="left" readonly ></div></td>
                                                                     <td> <input type="text" name="texti56"  id="texti56" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta56"  id="texta56"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc56"  id="textc56" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts56"  id="texts56" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse56"  id="textcourse56" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream56"  id="textstream56" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_57"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text57"  id="text57" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti57"  id="texti57" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta57"  id="texta57"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc57"  id="textc57" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts57"  id="texts57" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse57"  id="textcourse57" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream57"  id="textstream57" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_58"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text58"  id="text58" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti58"  id="texti58" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta58"  id="texta58"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc58"  id="textc58" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts58"  id="texts58" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse58"  id="textcourse58" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream58"  id="textstream58" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_59"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text59"  id="text59" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti59"  id="texti59" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta59"  id="texta59"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc59"  id="textc59" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts59"  id="texts59" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse59"  id="textcourse59" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream59"  id="textstream59" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_60"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text60"  id="text60" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti60"  id="texti60" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta60"  id="texta60"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc60"  id="textc60" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts60"  id="texts60" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse60"  id="textcourse60" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream60"  id="textstream60" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                              <!-- end next 30 -->
                                              
                                              <!-- 61 to 100 -->
                                              <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_61"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text61"  id="text61" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti61"  id="texti61" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta61"  id="texta61"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc61"  id="textc61" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts61"  id="texts61" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse61"  id="textcourse61" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream61"  id="textstream61" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_62"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text62"  id="text62" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti62"  id="texti62" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta62"  id="texta62"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                           <td> <input type="text" name="textc62"  id="textc62" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="texts62"  id="texts62" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="textcourse62"  id="textcourse62" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream62"  id="textstream62" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_63"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text63"  id="text63" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti63"  id="texti63" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta63"  id="texta63"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc63"  id="textc63" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts63"  id="texts63" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse63"  id="textcourse63" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream63"  id="textstream63" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_64"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text64"  id="text64" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti64"  id="texti64" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta64"  id="texta64"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc64"  id="textc64" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts64"  id="texts64" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse64"  id="textcourse64" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream64"  id="textstream64" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_65"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text65"  id="text65" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti65"  id="texti65" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta65"  id="texta65"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc65"  id="textc65" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts65"  id="texts65" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse65"  id="textcourse65" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream65"  id="textstream65" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_66"> <input type="checkbox" name="check_List" id="check_List">
                                                                    <input type="hidden" name="text66"  id="text66" value="" class="gridCol" align="left" readonly ></div></td>
                                                                     <td> <input type="text" name="texti66"  id="texti66" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta66"  id="texta66"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc66"  id="textc66" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts66"  id="texts66" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse66"  id="textcourse66" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream66"  id="textstream66" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_67"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text67"  id="text67" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti67"  id="texti67" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta67"  id="texta67"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc67"  id="textc67" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts67"  id="texts67" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse67"  id="textcourse67" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream67"  id="textstream67" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_68"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text68"  id="text68" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti68"  id="texti68" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta68"  id="texta68"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc68"  id="textc68" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts68"  id="texts68" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse68"  id="textcourse68" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream68"  id="textstream68" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_69"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text69"  id="text69" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti69"  id="texti69" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta69"  id="texta69"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc69"  id="textc69" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts69"  id="texts69" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse69"  id="textcourse69" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream69"  id="textstream69" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_70"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text70"  id="text70" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti70"  id="texti70" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta70"  id="texta70"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                           
                                                            <td> <input type="text" name="textc70"  id="textc70" class="gridCol" align="left" value="" readonly></td>
                                                             <td> <input type="text" name="texts70"  id="texts70" class="gridCol" align="left" value="" readonly></td>
                                                             <td> <input type="text" name="textcourse70"  id="textcourse70" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream70"  id="textstream70" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>

                                              
                                                 <tr class="gridRowEven">
                                                    <td  align="center"><div id="gridRow_71"> <input type="checkbox" name="check_List" id="check_List">
                                                    <input type="hidden" name="text71"  id="text71" class="gridCol" align="left" value="" readonly></div></td>
                                                    <td> <input type="text" name="texti71"  id="texti71" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta71"  id="texta71" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                    
                                                     <td> <input type="text" name="textc71"  id="textc71" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texts71"  id="texts71" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="textcourse71"  id="textcourse71" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream71"  id="textstream71" class="gridCol" align="left" value="" readonly></td>
                                                    <%-- <td> <input type="checkbox" name="text3" id="text3" class="gridCol" align="left" value="Assign" onclick="getAccountDetails('3')"></td>--%>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_72"> <input type="checkbox" name="check_List" id="check_List">
                                                    <input type="hidden" name="text72" id="text72" value="" class="gridCol" align="left" readonly></div></td>
                                                     <td> <input type="text" name="texti72"  id="texti72" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta72"  id="texta72" value="" class="gridColumnMediumLarge" align="left" readonly></td><%--  onclick="getAccountDetails('6')" --%>
                                                    
                                                    <td> <input type="text" name="textc72"  id="textc72" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts72"  id="texts72" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse72"  id="textcourse72" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream72"  id="textstream72" class="gridCol" align="left" value="" readonly></td>
                                                    </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"> <div id="gridRow_73"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('9')"--%>
                                                    <input type="hidden" name="text73"  id="text73" value="" class="gridCol" align="left" readonly></div></td>
                                                     <td> <input type="text" name="texti73"  id="texti73" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta73"  id="texta73" value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc73"  id="textc73" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts73"  id="texts73" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse73"  id="textcourse73" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream73"  id="textstream73" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_74"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('12')"--%>
                                                     <input type="hidden" name="text74"  id="text74" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti74"  id="texti74" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta74"  id="texta74"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc74"  id="textc74" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts74"  id="texts74" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse74"  id="textcourse74" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream74"  id="textstream74" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_75"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('15')"--%>
                                                    <input type="hidden" name="text75"  id="text75" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti75"  id="texti75" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta75"  id="texta75"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc75"  id="textc75" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts75"  id="texts75" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse75"  id="textcourse75" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream75"  id="textstream75" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_76"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('18')"--%>
                                                    <input type="hidden" name="text76"  id="text76" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti76"  id="texti76" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta76"  id="texta76"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc76"  id="textc76" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts76"  id="texts76" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse76"  id="textcourse76" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream76"  id="textstream76" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"> <div id="gridRow_77"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('21')"--%>
                                                     <input type="hidden" name="text77"  id="text77" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti77"  id="texti77" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta77"  id="texta77"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc77"  id="textc77" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts77"  id="texts77" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse77"  id="textcourse77" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream77"  id="textstream77" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_78"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('24')"--%>
                                                    <input type="hidden" name="text38"  id="text78" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti78"  id="texti78" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta78"  id="texta78"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc78"  id="textc78" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts78"  id="texts78" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse78"  id="textcourse78" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream78"  id="textstream78" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                              <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_79"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('27')"--%>
                                                    <input type="hidden" name="text79"  id="text79" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti79"  id="texti79" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta79"  id="texta79"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                   <td> <input type="text" name="textc79"  id="textc79" class="gridCol" align="left" value="" readonly></td>
                                                   <td> <input type="text" name="texts79"  id="texts79" class="gridCol" align="left" value="" readonly></td>
                                                   <td> <input type="text" name="textcourse79"  id="textcourse79" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream79"  id="textstream79" class="gridCol" align="left" value="" readonly></td>
                                              </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_80"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                     <input type="hidden" name="text80"  id="text80" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti80"  id="texti80" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta80"  id="texta80"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc80"  id="textc80" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts80"  id="texts80" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textcourse80"  id="textcourse80" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream80"  id="textstream80" class="gridCol" align="left" value="" readonly></td>
                                                 </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_81"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                    <input type="hidden" name="text81"  id="text81" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti81"  id="texti81" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta81"  id="texta81"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                   <td> <input type="text" name="textc81"  id="textc81" class="gridCol" align="left" value="" readonly></td>
                                                   <td> <input type="text" name="texts81"  id="texts81" class="gridCol" align="left" value="" readonly></td>
                                                   <td> <input type="text" name="textcourse81"  id="textcourse81" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream81"  id="textstream81" class="gridCol" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                                    <td align="center"> <div id="gridRow_82"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                                    <input type="hidden" name="text82"  id="text82" value="" class="gridCol" align="left" readonly ></div></td>
                                                                     <td> <input type="text" name="texti82"  id="texti82" class="gridCol" align="left" value="" readonly></td>
                                                                    <td> <input type="text" name="texta82"  id="texta82"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                    
                                                                    <td> <input type="text" name="textc82"  id="textc82" class="gridCol" align="left" value="" readonly></td>
                                                                    <td> <input type="text" name="texts82"  id="texts82" class="gridCol" align="left" value="" readonly></td>
                                                                    <td> <input type="text" name="textcourse82"  id="textcourse82" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream82"  id="textstream82" class="gridCol" align="left" value="" readonly></td>
                                                   </tr>
                                                   <tr class="gridRowEven">
                                                       <td align="center"><div id="gridRow_83"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                         <input type="hidden" name="text83"  id="text83" value="" class="gridCol" align="left" readonly ></div></td>
                                                          <td> <input type="text" name="texti83"  id="texti83" class="gridCol" align="left" value="" readonly></td>
                                                        <td> <input type="text" name="texta83"  id="texta83"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                        
                                                        <td> <input type="text" name="textc83"  id="textc83" class="gridCol" align="left" value="" readonly></td>
                                                        <td> <input type="text" name="texts83"  id="texts83" class="gridCol" align="left" value="" readonly></td>
                                                        <td> <input type="text" name="textcourse83"  id="textcourse83" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream83"  id="textstream83" class="gridCol" align="left" value="" readonly></td>
                                                    </tr>
                                                    <tr class="gridRowEven">
                                                     <td align="center"> <div id="gridRow_84"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                     <input type="hidden" name="text84"  id="text84" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti84"  id="texti84" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texta84"  id="texta84"  value="" class="gridColumnMediumLarge" align="left" readonly></td>  
                                                     
                                                     <td> <input type="text" name="textc84"  id="textc84" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texts84"  id="texts84" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="textcourse84"  id="textcourse84" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream84"  id="textstream84" class="gridCol" align="left" value="" readonly></td>
                                                    </tr>
                                                    <tr class="gridRowEven">
                                                                    <td align="center"> <div id="gridRow_85"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                                     <input type="hidden" name="text85"  id="text85" value="" class="gridCol" align="left" readonly ></div></td>
                                                                      <td> <input type="text" name="texti85"  id="texti85" class="gridCol" align="left" value="" readonly></td>
                                                                     <td> <input type="text" name="texta85"  id="texta85"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                    
                                                                     <td> <input type="text" name="textc85"  id="textc85" class="gridCol" align="left" value="" readonly></td>
                                                                      <td> <input type="text" name="texts85"  id="texts85" class="gridCol" align="left" value="" readonly></td>
                                                                      <td> <input type="text" name="textcourse85"  id="textcourse85" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream85"  id="textstream85" class="gridCol" align="left" value="" readonly></td>
                                                      </tr>
                                                      <tr class="gridRowEven">
                                                                  <td align="center"><div id="gridRow_86"> <input type="checkbox" name="check_List" id="check_List">
                                                                <input type="hidden" name="text86"  id="text86" value="" class="gridCol" align="left" readonly ></div></td>
                                                                 <td> <input type="text" name="texti86"  id="texti86" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texta86"  id="texta86"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                
                                                                <td> <input type="text" name="textc86"  id="textc86" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texts86"  id="texts86" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="textcourse86"  id="textcourse86" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream86"  id="textstream86" class="gridCol" align="left" value="" readonly></td>
                                                       </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                                <td align="center"> <div id="gridRow_87"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                                <input type="hidden" name="text87"  id="text87" value="" class="gridCol" align="left" readonly ></div></td>
                                                                 <td> <input type="text" name="texti87"  id="texti87" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texta87"  id="texta87"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                
                                                                <td> <input type="text" name="textc87"  id="textc87" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texts87"  id="texts87" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="textcourse87"  id="textcourse87" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream87"  id="textstream87" class="gridCol" align="left" value="" readonly></td>
                                                            
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_88"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                            <input type="hidden" name="text88"  id="text88" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti88"  id="texti88" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta88"  id="texta88"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc88"  id="textc88" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts88"  id="texts88" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse88"  id="textcourse88" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream88"  id="textstream88" class="gridCol" align="left" value="" readonly></td>
                                                            
                                                        </tr>
                                                        
                                                         <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_89"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text89"  id="text89" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti89"  id="texti89" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta89"  id="texta89"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc89"  id="textc89" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts89"  id="texts89" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse89"  id="textcourse89" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream89"  id="textstream89" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                       
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_90"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text90"  id="text90" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti90"  id="texti90" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta90"  id="texta90"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc90"  id="textc90" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts90"  id="texts90" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse90"  id="textcourse90" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream90"  id="textstream90" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_91"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text91"  id="text91" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti91"  id="texti91" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta91"  id="texta91"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc91"  id="textc91" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts91"  id="texts91" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse91"  id="textcourse91" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream91"  id="textstream91" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_92"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text92"  id="text92" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti92"  id="texti92" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta92"  id="texta92"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc92"  id="textc92" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts92"  id="texts92" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse92"  id="textcourse92" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream92"  id="textstream92" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_93"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text93"  id="text93" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti93"  id="texti93" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta93"  id="texta93"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc93"  id="textc93" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts93"  id="texts93" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse93"  id="textcourse93" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream93"  id="textstream93" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_94"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text94"  id="text94" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti94"  id="texti94" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta94"  id="texta94"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                           <td> <input type="text" name="textc94"  id="textc94" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="texts94"  id="texts94" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="textcourse94"  id="textcourse94" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream94"  id="textstream94" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_95"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text95"  id="text95" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti95"  id="texti95" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta95"  id="texta95"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                           <td> <input type="text" name="textc95"  id="textc95" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="texts95"  id="texts95" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="textcourse95"  id="textcourse95" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream95"  id="textstream95" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_96"> <input type="checkbox" name="check_List" id="check_List">
                                                                    <input type="hidden" name="text96"  id="text96" value="" class="gridCol" align="left" readonly ></div></td>
                                                                     <td> <input type="text" name="texti96"  id="texti96" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta96"  id="texta96"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc96"  id="textc96" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts96"  id="texts96" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse96"  id="textcourse96" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream96"  id="textstream96" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_97"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text97"  id="text97" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti97"  id="texti97" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta97"  id="texta97"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                           <td> <input type="text" name="textc97"  id="textc97" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="texts97"  id="texts97" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="textcourse97"  id="textcourse97" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream97"  id="textstream97" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_98"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text98"  id="text98" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti98"  id="texti98" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta98"  id="texta98"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                           <td> <input type="text" name="textc98"  id="textc98" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="texts98"  id="texts98" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="textcourse98"  id="textcourse98" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream98"  id="textstream98" class="gridCol" align="left" value="" readonly></td>
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_99"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text99"  id="text99" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti99"  id="texti99" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta99"  id="texta99"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                           <td> <input type="text" name="textc99"  id="textc99" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="texts99"  id="texts99" class="gridCol" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="textcourse99"  id="textcourse99" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream99"  id="textstream99" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_100"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text100"  id="text100" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti100"  id="texti100" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta100"  id="texta100"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc100"  id="textc100" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts100"  id="texts100" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="textcourse100"  id="textcourse100" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="textstream100"  id="textstream100" class="gridCol" align="left" value="" readonly></td>
                                                        </tr>
                                              
                                              <!-- 61 to 100 -->
                                                     
                                                        <tr class="gridPager">
                                                            <td colspan="5" align="left" class="gridFooter" ></td>
                                                        </tr>
                                                      </table>
                                                      
                                                  </div>
                                              </td>
                                                <td align="top">
                                                  <div id="examDetails" style="display:none;">
                                                      <label class="fieldLabel" rowspan="4">Exam Name:<font color="red">*</font></label>
                                                      <a HREF="#" onclick="return examDetailsPopup();">
                                          <img SRC="../includes/images/help.gif" WIDTH=16 HEIGHT=16 BORDER=0 ALTER="Territory">
                                      </a>
                                                      <table border="0">
                                                         <tr><td rowspan="4"><s:select name="subtopics" id="subtopics" cssClass="inputSelect" list="examTypeMap" size="6" multiple="true"></s:select></td>
                                                          <%--<td><input type="button" class="buttonBg" onclick="return insertExamDetails();" value="Proceed"/></td>--%><td>  </td></tr>
                                                      </table>
                                                       
                                                     <%-- <input type="button" class="buttonBg" onclick="return insertExamDetails();" value="Proceed"/>--%>
                                                  </div>
                                                      
                                            
                                                    <div id="activeButton" style="display:none;"> 
                                                      <br><br>
                                                   
                                                     <input type="button" class="buttonBg" onclick="return insertExamDetails();" value="Click To Active"/> <br><br> 
                                                 </div>
                                              
                                               
                                                <div id="rejectButton" style="display:none;">
                                                    
                                                    <%--  <span class="fieldLabel">SubTopics:</span>:<br>
                                                    <select name="subtopics" id="subtopics" size="5" multiple="multiple">
                                                    <option value="18">English</option>
                                                    <option value="19">Aptitude</option>
                                                    <option value="20">Technical</option>
                                                    </select> 
                                                   <s:select name="subtopics" id="subtopics" cssClass="inputSelect" list="subTopicsMap" size="5" multiple="true"></s:select>--%>
                                                
                                                   
                                                   
                                                  <%--  <input type="button" class="buttonBg" onclick="getCreData(document.creSearchForm.check_List , 'Active');" value="Click To Active"/> <br><br> --%>
                                                 <input type="button" class="buttonBg" onclick="getCreData(document.creSearchForm.check_List , 'Rejected');" value="Click To Reject"/>
                                                    <!--<input type="button" class="buttonBg" onclick="popupWindow();" value="Click To Active"/><br><br>
                                                 <input type="button" class="buttonBg" onclick="popupWindow();" value="Click To Reject"/>   -->
                                             
                                                  </div>
                                               
                                              </td>
                                          </tr>
                                          
                                         <%-- <tr>
                                              <td>
                                                  <div id="examDetails" style="display:none;">
                                                      <table border="0">
                                                          <tr><td class="fieldLabel" rowspan="4">SubTopics:<font color="red">*</font></td><td rowspan="4"><s:select name="subtopics" id="subtopics" cssClass="inputSelect" list="examTypeMap" size="6" multiple="true" value="%{subtopics}" onchange="resetNoOfQuest();"></s:select></td>
                                                              <td class="fieldLabel">Number of questions from each subtopic:<font color="red">*</font></td>
                                                              <td><s:textfield name="totalQues" cssClass="inputTextHours" value="" id="totalQues" onkeypress="return isNumberKeyExam(event)" size="1"  onfocus="getCreAvailableQuestionCount();" onchange="getTotalQuestions();"/>   <b class="hiddenLabel" id="availQuest" ></b>
                                                              <s:hidden name="availableQues" value="" id="availableQues"/></td></tr>
                                                           <tr> <td class="fieldLabel">Total Number of Questions :<font color="red">*</font></td>
                                                               <td ><s:textfield name="totalNoOfQuestions" cssClass="inputTextHours"  value=""  id="totalNoOfQuestions" readonly="true"/></td>
                                                          <tr> <td class="fieldLabel">Duration :<font color="red">*</font></td>
                                                        <td ><s:textfield name="totDuration" cssClass="inputTextHours"  value=""  id="totDuration" onkeypress="return isNumberKeyExam(event)" size="1"/><b class="fieldLabel">(Minutes)</b></td> </tr>
                                                          <tr> <td class="fieldLabel">Min Marks :<font color="red">*</font></td>
                                                        <td ><s:textfield name="minMarks" cssClass="inputTextHours"  value=""  id="minMarks" onkeypress="return isNumberKeyExam(event)" size="1"/></td>
                                                          <tr>
                                                          <tr><td></td><td><td ><input type="button" class="buttonBg" onclick="displayBack();" value="Back"/> </td><td><input type="button" class="buttonBg" onclick="return insertExamDetails();" value="Proceed"/></td></tr>
                                                      </table>
                                                      
                                                  </div>
                                              </td>
                                          </tr> --%>
                                          
                                      </table>
                               </s:form>
                                    
                                    
                                    <%--  </sx:tabbedpanel> --%>
 
                                </div>
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
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
          <script>
$(document).ready(function() {
   init();
     });
</script>	
    </body>
</html>