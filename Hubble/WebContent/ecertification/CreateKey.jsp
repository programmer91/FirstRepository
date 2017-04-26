<!-- 
 /* ******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package : ecertification
 *
 * Date    :  JULY 30, 2013, 3:25 PM
 *
 * Author  : 
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
        <title>Hubble Organization Portal :: Create Exam Keys</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/ecertificationAjax.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/EcertStandardClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/ResetEmployeePwd.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/ResetEmployeePwd.js"/>"></script> 

        
        
        
        <s:include value="/includes/template/headerScript.html"/>
    </head>
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
                                    <li ><a href="#" class="selected" rel="empSearchTab"  > Create Keys</a></li>
    					 
                                </ul>
                                <%-- <sx:tabbedpanel id="empSearch" cssStyle="width: 845px; height: 500px;padding:10px 5px 5px 5px" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                    <%--  <sx:div id="empSearchTab" label="Employee Search" cssStyle="overflow: auto;"> --%>
                                    <div id="empSearchTab" class="tabcontent"  >
                                        <s:form action="doAddExamKey" theme="simple" name="searchForm" onsubmit="return validateExamKeyInputs();">
                                            <table cellpadding="0" border="0" cellspacing="0" width="100%">
                                                <tr>
                                                    <td class="headerText">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td>
                                                        <table align="center" border="0" width="100%" cellpadding="2" cellspacing="2" >
                                                             <tr>  
                                                             <%
                                                             if(session.getAttribute("resultMessage")!=null)
                                                              out.println(session.getAttribute("resultMessage").toString());
                                                             session.removeAttribute("resultMessage1");
                                                             %>
                                                             
                                                             <td><br></td></tr>
                                                            <tr>
                                                                <td class="fieldLabel" >Practice :</td>
                                                                <td width="15%"> <s:select label="Select Domain" id="domainId" 
                                                                       name="domainId" headerKey=""            
                                                                       headerValue="-- Please Select --"
                                                                       list="domainMap" cssClass="inputSelect" value="%{domainId}" onchange="getTopicData();"/></td>
                                                                <td class="fieldLabel" width="15%">Topic :</td>
                                                                <td width="15%">  <s:select label="Select Domain" id="topicId" 
                                                                       name="topicId" headerKey="-1"            
                                                                       headerValue="-- Please Select --"
                                                                       list="topicsMap" cssClass="inputSelect" value="%{topicId}" onchange="getAvailableQuestions();"/></td>
                                                                       <td width="25%">   <b class="hiddenLabel" id="availQuest" ></b>
                                                                       <s:hidden name="availableQues"   value=""  id="availableQues" /></td>
                                                                
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel">No Of Questions:</td>
                                                        <td><s:textfield name="totalQues" cssClass="inputTextHours"  value=""  id="totalQues" onkeypress="return isNumberKeyExam(event)" size="1"/></td>
                                                                
                                                                 <td class="fieldLabel">Duration :</td>
                                                        <td><s:textfield name="totDuration" cssClass="inputTextHours"  value=""  id="totDuration" onkeypress="return isNumberKeyExam(event)" size="1"/><b class="fieldLabel">(Minutes)</b></td> 
                                                            </tr>
                                                            <tr>
                                                               
                                                        <td class="fieldLabel">Min Marks :</td>
                                                        <td><s:textfield name="minMarks" cssClass="inputTextHours"  value=""  id="minMarks" onkeypress="return isNumberKeyExam(event)" size="1"/></td> 
                                                        
                                                        <td class="fieldLabel">No Of Keys:</td>
                                                        <td><s:textfield name="noOfKeys" cssClass="inputTextHours"  value=""  id="noOfKeys" onkeypress="return isNumberKeyExam(event)" size="1"/></td>
                                                            </tr>
                                                            <tr>
                                                            
                                                                <td align="right" colspan="4"><s:submit  value="Generate Keys" cssClass="buttonBg" /></td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            
                                            </table>
                                        </s:form>
                                        <%--  </sx:div> --%>
                                    </div>
                                    
                                    
                                     <div id="instructionDiv" class="tabcontent" style="display:none;" > 
                                         <s:form name="instructionForm" id="instructionForm" action="startExam" method="POST" theme="simple">
                                              <%-- <h3 align="center" style="color:darkblue;">Start Exam</h3>  --%>
                                              <table align="center" width="100%" height="100%" cellpadding="0" cellspacing="0" border="0" >
                                                 <tr>
                                                    <td class="headerText" ">
                                                        <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                                    </td>
                                                </tr>
                                                <tr>
                                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="750px" height="481px" class="cellBorder" valign="top" background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
                                <!--//START TABBED PANNEL : -->
                              <%--  <sx:tabbedpanel id="resetPasswordPannel" cssStyle="width: 750px; height: 410px;padding-left:5px;padding-right:5px;padding-bottom:8px;" doLayout="true" > --%>
                                    
                                    <!--//START TAB : -->
                                   <%-- <sx:div id="resetPasswordTab" label="Reset Password">--%>
                                       <%-- <s:form action="resetPasswordSubmit" name="resetForm" theme="simple" onsubmit="return resetEmpPwdSubmit();">--%>
                                            <div>
                                                <table border="0" cellpadding="1" cellspacing="1" align="center" width="500px" height="150px" class="cellBorder" background="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/miragewatermark_850X600.gif">
                                                    <%-- Start of Exam details  --%>
                                                    
                                                    <tr>
                                                      <td>
                                                          <table align="center">
                                                           
                                                              <tr>
                                                                    <td colspan="2"><h3 class="bgColor" align="center">
                                                                        <%--<script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/InstructionsAnimation.js"/>"></script>--%>
                                                                        <font color="white">Exam Details</font>
                                                                        
                                                                        </h3>
                                                                    </td>
                                                                </tr>
                                                                
                                                              <tr>
                                                                  <td class="fieldLabeforecert">
                                                                      <font size="2px" color="#1B425E"><span>Practice :</span>&nbsp;</font>
                                                                  </td>
                                                                  <td><font size="2px" color="#1B425E">
                                                                          <label id="insdomainName"> </label>
                                                                          <s:hidden name="insdomainId" id="insdomainId"/>
                                                                      <s:hidden name="domainName" id="domainName" value=""/></font>
                                                                  </td>
                                                             
                                                              </tr>
                                                              <tr>
                                                                  <td class="fieldLabeforecert">
                                                                      <font size="2px" color="#1B425E"><span>Topic :</span>&nbsp;</font>
                                                                  </td>
                                                                  <td><font size="2px" color="#1B425E">
                                                                          <label id="insTopicName"> </label>
                                                                          <s:hidden name="insTopicId" id="insTopicId"/>
                                                                      <s:hidden name="topicName" id="topicName" /></font>
                                                                  </td>
                                                              </tr>
                                                            
                                                              
                                                              
                                                              
                                                              <tr>
                                                                  <td><font size="2px" color="#1B425E">
                                                                      <span>Total Questions :</span>&nbsp;</font>
                                                                  </td>
                                                                  <td><font size="2px" color="#1B425E">
                                                                      <label id="totalQuestions" name="totalQuestions"></label>
                                                                      <s:hidden name="totalQuest" id="totalQuest" /></font>
                                                                  </td>
                                                              </tr>
                                                              <tr>
                                                                  <td><font size="2px" color="#1B425E">
                                                                      <span>Duration :</span>&nbsp;</font>
                                                                  </td>
                                                                  <td ><font size="2px" color="#1B425E">
                                                                          <label id="duration" name="duration"></label>&nbsp;&nbsp;Mins
                                                                      <s:hidden name="durationTime" id="durationTime" /></font>
                                                                  </td>
                                                              </tr>
                                                          </table>
                                                  </td></tr>
                                                    <%-- Exam details --%>
                                                    <tr>
                                                        <td valign="top" colspan="2">
                                                            <table width="500px" cellpadding="1" cellspacing="1" border="0" align="center">
                                                                <!--START ANIMATED TEXT ROW -->
                                                                <tr>
                                                                    <td colspan="2"><h3 class="bgColor" align="center"><script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/InstructionsAnimation.js"/>"></script>
                                                                            
                                                                        </h3>
                                                                    </td>
                                                                </tr>
                                                                <!--END ANIMATED TEXT ROW -->
                                                                <tr>
                                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                                    <td ><font size="2px" color="#1B425E">Don't click left menu links in middle of the exam. ! </font></td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                                    <td ><font size="2px" color="#1B425E"> Don't change your role in middle of the exam. ! </font></td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                                    <td ><font size="2px" color="#1B425E">Don't refresh the page. ! </font></td>
                                                                </tr>
                                                                 <tr>
                                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                                    <td ><font size="2px" color="#1B425E">Each question carry 1 mark, no negative marks. ! </font></td>
                                                                </tr>
                                                                 <tr>
                                                                    <td align="right"><img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/rightArrow_16x6.gif"></td>
                                                                    <td ><font size="2px" color="#1B425E">Test will be submitted automatically if the time expired. ! </font></td>
                                                                </tr>
                                                            </table>
                                                        <br> </td>
                                                    </tr>
                                                   
                                                    <tr>
                                                        <td colspan="2" style="padding-left:218px;">
                                                            <s:submit cssClass="buttonBg" value="Start Exam"/>
                                                        </td>
                                                    </tr>
                                                   
                                                </table>
                                            </div>
                                       
                               <%--     </sx:div >  --%>
                                    <!--//END TAB : -->
                                    
                               <%-- </sx:tabbedpanel>--%>
                                <!--//END TABBED PANNEL : -->
                              
                            </td>
                            
                            
                            
                            <!--//END DATA COLUMN : Coloumn for Screen Content-->
                            
                            
                            
                            
                                                </tr>

                                               </table>
                                          </s:form>
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
