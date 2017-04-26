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
        <title>Hubble Organization Portal :: Mcert Records</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/mcertification/mcertificationAjax.js?version=2.3"/>"></script>
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
    <body class="bodyGeneral" oncontextmenu="return false;"  onload="init();">
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
                                    <li ><a href="#" class="selected" rel="getCreRecords"  > Get Mcert Records</a></li>
    					 
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
                                                             <td class="fieldLabel">Status&nbsp;:<font color="red">*</font></td>
                                                                   <td> 
                                                                   <s:select name="mcertConsultantStatus" list="{'Registered','Active','Rejected','PASS','FAIL'}" id="mcertConsultantStatus" cssClass="inputSelect" value="" headerKey="" headerValue="--Select Status--"/>
                                                                 </td>
                                                              <script type="text/javascript">
                                                          var cal1 = new CalendarTime(document.forms['creSearchForm'].elements['startDate']);
                                                             cal1.year_scroll = true;
                                                             cal1.time_comp = false;
                                                             
                                                             var cal = new CalendarTime(document.forms['creSearchForm'].elements['toDate']);
                                                             cal.year_scroll = true;
                                                             cal.time_comp = false;
                                                         </script>
                                                 
                                                          
                                                            
                                                              
                                                            </tr>
                                                         

                                                   
                                                        <tr>
                                                            <td></td> <td></td> <td></td> <td></td> <td></td>
                                                                <td align="left" ><input type="button" value="Get Cre Records" class="buttonBg" onclick="getMcertRecords();" id="btnGetRecords"/></td>
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
                                              <table cellpadding="1" cellspacing="1" width="100%" class="gridTable" id="RevenueSummery" border="0">
                                                <tr class="gridHeader">
                                                  <td width="5%"  ><input type="checkbox" name="text" id="text">Select&nbsp;All</td>
                                                  <td width="5%" >ConsultantId </td>
                                                  <td width="5%" >ConsultantName</td>
                                                  <td width="5%"  >Email</td>
                                                  <td width="5%"  >Status</td>
                                                  
                                                 
                                                   
                                                </tr> 
                                                <tr class="gridRowEven">
                                                    <td  align="center"><div id="gridRow_1"> <input type="checkbox" name="check_List" id="check_List">
                                                    <input type="hidden" name="text1"  id="text1" class="gridCol" align="left" value="" readonly></div></td>
                                                    <td> <input type="text" name="texti1"  id="texti1" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta1"  id="texta1" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                   <td> <input type="text" name="textc1"  id="textc1" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                   <td> <input type="text" name="texts1"  id="texts1" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                    
                                                    
                                                    
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_2"> <input type="checkbox" name="check_List" id="check_List">
                                                    <input type="hidden" name="text2" id="text2" value="" class="gridCol" align="left" readonly></div></td>
                                                     <td> <input type="text" name="texti2"  id="texti2" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta2"  id="texta2" value="" class="gridColumnMediumLarge" align="left" readonly></td><%--  onclick="getAccountDetails('6')" --%>
                                                    <td> <input type="text" name="textc2"  id="textc2" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                   <td> <input type="text" name="texts2"  id="texts2" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                    </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"> <div id="gridRow_3"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('9')"--%>
                                                    <input type="hidden" name="text3"  id="text3" value="" class="gridCol" align="left" readonly></div></td>
                                                     <td> <input type="text" name="texti3"  id="texti3" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta3"  id="texta3" value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc3"  id="textc3" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texts3"  id="texts3" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_4"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('12')"--%>
                                                     <input type="hidden" name="text4"  id="text4" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti4"  id="texti4" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta4"  id="texta4"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc4"  id="textc4" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texts4"  id="texts4" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_5"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('15')"--%>
                                                    <input type="hidden" name="text5"  id="text5" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti5"  id="texti5" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta5"  id="texta5"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc5"  id="textc5" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texts5"  id="texts5" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_6"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('18')"--%>
                                                    <input type="hidden" name="text6"  id="text6" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti6"  id="texti6" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta6"  id="texta6"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc6"  id="textc6" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                      <td> <input type="text" name="texts6"  id="texts6" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"> <div id="gridRow_7"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('21')"--%>
                                                     <input type="hidden" name="text7"  id="text7" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti7"  id="texti7" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta7"  id="texta7"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc7"  id="textc7" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texts7"  id="texts7" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_8"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('24')"--%>
                                                    <input type="hidden" name="text8"  id="text8" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti8"  id="texti8" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta8"  id="texta8"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc8"  id="textc8" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                      <td> <input type="text" name="texts8"  id="texts8" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                </tr>
                                              <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_9"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('27')"--%>
                                                    <input type="hidden" name="text9"  id="text9" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti9"  id="texti9" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta9"  id="texta9"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc9"  id="textc9" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                      <td> <input type="text" name="texts9"  id="texts9" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_10"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                     <input type="hidden" name="text10"  id="text10" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti10"  id="texti10" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta10"  id="texta10"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc10"  id="textc10" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texts10"  id="texts10" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                 </tr>
                                                <tr class="gridRowEven">
                                                    <td align="center"><div id="gridRow_11"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                    <input type="hidden" name="text11"  id="text11" value="" class="gridCol" align="left" readonly ></div></td>
                                                     <td> <input type="text" name="texti11"  id="texti11" class="gridCol" align="left" value="" readonly></td>
                                                    <td> <input type="text" name="texta11"  id="texta11"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                    
                                                    <td> <input type="text" name="textc11"  id="textc11" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                   <td> <input type="text" name="texts11"  id="texts11" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                </tr>
                                                <tr class="gridRowEven">
                                                                    <td align="center"> <div id="gridRow_12"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                                    <input type="hidden" name="text12"  id="text12" value="" class="gridCol" align="left" readonly ></div></td>
                                                                     <td> <input type="text" name="texti12"  id="texti12" class="gridCol" align="left" value="" readonly></td>
                                                                    <td> <input type="text" name="texta12"  id="texta12"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                    
                                                                    <td> <input type="text" name="textc12"  id="textc12" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                                  <td> <input type="text" name="texts12"  id="texts12" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                               
                                                   </tr>
                                                   <tr class="gridRowEven">
                                                       <td align="center"><div id="gridRow_13"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                         <input type="hidden" name="text13"  id="text13" value="" class="gridCol" align="left" readonly ></div></td>
                                                          <td> <input type="text" name="texti13"  id="texti13" class="gridCol" align="left" value="" readonly></td>
                                                        <td> <input type="text" name="texta13"  id="texta13"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                        
                                                        <td> <input type="text" name="textc13"  id="textc13" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                      <td> <input type="text" name="texts13"  id="texts13" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                    </tr>
                                                    <tr class="gridRowEven">
                                                     <td align="center"> <div id="gridRow_14"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                     <input type="hidden" name="text14"  id="text14" value="" class="gridCol" align="left" readonly ></div></td>
                                                      <td> <input type="text" name="texti14"  id="texti14" class="gridCol" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texta14"  id="texta14"  value="" class="gridColumnMediumLarge" align="left" readonly></td>  
                                                     
                                                     <td> <input type="text" name="textc14"  id="textc14" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                               <td> <input type="text" name="texts14"  id="texts14" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                    
                                                    </tr>
                                                    <tr class="gridRowEven">
                                                                    <td align="center"> <div id="gridRow_15"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                                     <input type="hidden" name="text15"  id="text15" value="" class="gridCol" align="left" readonly ></div></td>
                                                                      <td> <input type="text" name="texti15"  id="texti15" class="gridCol" align="left" value="" readonly></td>
                                                                     <td> <input type="text" name="texta15"  id="texta15"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                     
                                                                     <td> <input type="text" name="textc15"  id="textc15" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                     <td> <input type="text" name="texts15"  id="texts15" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                              
                                                      </tr>
                                                      <tr class="gridRowEven">
                                                                  <td align="center"><div id="gridRow_16"> <input type="checkbox" name="check_List" id="check_List">
                                                                <input type="hidden" name="text16"  id="text16" value="" class="gridCol" align="left" readonly ></div></td>
                                                                 <td> <input type="text" name="texti16"  id="texti16" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texta16"  id="texta16"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                <td> <input type="text" name="textc16"  id="textc16" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                               <td> <input type="text" name="texts16"  id="texts16" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                       </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                                <td align="center"> <div id="gridRow_17"><input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                                <input type="hidden" name="text17"  id="text17" value="" class="gridCol" align="left" readonly ></div></td>
                                                                 <td> <input type="text" name="texti17"  id="texti17" class="gridCol" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texta17"  id="texta17"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                                
                                                                <td> <input type="text" name="textc17"  id="textc17" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                                <td> <input type="text" name="texts17"  id="texts17" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_18"> <input type="checkbox" name="check_List" id="check_List"><%--  onclick="getAccountDetails('30')"--%>
                                                            <input type="hidden" name="text18"  id="text18" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti18"  id="texti18" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta18"  id="texta18"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc18"  id="textc18" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts18"  id="texts18" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr>
                                                        
                                                         <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_19"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text19"  id="text19" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti19"  id="texti19" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta19"  id="texta19"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc19"  id="textc19" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts19"  id="texts19" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr>
                                                       
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_20"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text20"  id="text20" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti20"  id="texti20" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta20"  id="texta20"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                           <td> <input type="text" name="textc20"  id="textc20" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                           <td> <input type="text" name="texts20"  id="texts20" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_21"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text21"  id="text21" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti21"  id="texti21" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta21"  id="texta21"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc21"  id="textc21" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts21"  id="texts21" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_22"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text22"  id="text22" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti22"  id="texti22" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta22"  id="texta22"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc22"  id="textc22" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                             <td> <input type="text" name="texts22"  id="texts22" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_23"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text23"  id="text23" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti23"  id="texti23" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta23"  id="texta23"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc23"  id="textc23" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts23"  id="texts23" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_24"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text24"  id="text24" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti24"  id="texti24" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta24"  id="texta24"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc24"  id="textc24" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                             <td> <input type="text" name="texts24"  id="texts24" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_25"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text25"  id="text25" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti25"  id="texti25" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta25"  id="texta25"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc25"  id="textc25" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texts25"  id="texts25" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_26"> <input type="checkbox" name="check_List" id="check_List">
                                                                    <input type="hidden" name="text26"  id="text26" value="" class="gridCol" align="left" readonly ></div></td>
                                                                     <td> <input type="text" name="texti26"  id="texti26" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta26"  id="texta26"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc26"  id="textc26" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                              <td> <input type="text" name="texts26"  id="texts26" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_27"> <input type="checkbox" name="check_List" id="check_List">
                                                             <input type="hidden" name="text27"  id="text27" value="" class="gridCol" align="left" readonly ></div></td>
                                                              <td> <input type="text" name="texti27"  id="texti27" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta27"  id="texta27"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc27"  id="textc27" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                             <td> <input type="text" name="texts27"  id="texts27" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_28"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text28"  id="text28" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti28"  id="texti28" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta28"  id="texta28"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc28"  id="textc28" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                             <td> <input type="text" name="texts28"  id="texts28" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr><tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_29"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text29"  id="text29" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti29"  id="texti29" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta29"  id="texta29"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc29"  id="textc29" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                             <td> <input type="text" name="texts29"  id="texts29" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr>
                                                        <tr class="gridRowEven">
                                                            
                                                            <td align="center"><div id="gridRow_30"> <input type="checkbox" name="check_List" id="check_List">
                                                            <input type="hidden" name="text30"  id="text30" value="" class="gridCol" align="left" readonly ></div></td>
                                                             <td> <input type="text" name="texti30"  id="texti30" class="gridCol" align="left" value="" readonly></td>
                                                            <td> <input type="text" name="texta30"  id="texta30"  value="" class="gridColumnMediumLarge" align="left" readonly></td>
                                                            
                                                            <td> <input type="text" name="textc30"  id="textc30" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                                             <td> <input type="text" name="texts30"  id="texts30" class="gridColumnMediumLarge" align="left" value="" readonly></td>
                                              
                                                        </tr>

                                              <!-- satrt next 30 -->
                                                 
                                                     
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
                                                         <td>  </td></tr>
                                                      </table>
                                                       
                                                     
                                                  </div>
                                                      
                                            
                                                    <div id="activeButton" style="display:none;"> 
                                                      <br><br>
                                                   
                                                     <input type="button" class="buttonBg" onclick="return insertMcertExamDetails();" value="Click To Active"/> <br><br> 
                                                 </div>
                                              
                                               
                                                <div id="rejectButton" style="display:none;">
                                                    
                                                 
                                                 <input type="button" class="buttonBg" onclick="getMcertData(document.creSearchForm.check_List , 'Rejected');" value="Click To Reject"/>
                                                   
                                                  </div>
                                               
                                              </td> 
                                          </tr>
                                          
                                       
                                          
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
          
    </body>
</html>