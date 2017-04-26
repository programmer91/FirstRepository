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
        <title>Hubble Organization Portal :: Employee Leaves Applied</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/cre/CreAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/EcertStandardClientValidation.js"/>"></script>
        <script type="text/JavaScript">
            function bodyOnload()
            {
   
                var totalQues=document.getElementById("totalQues").value;
                
                var totalQuest=document.getElementById("totalNoOfQuestions").value;
                
                var totDuration=document.getElementById("totDuration").value;
                
                var minMarks=document.getElementById("minMarks").value;
                
                if(totalQues == '0' && totalQuest== '0' && totDuration== '0' && minMarks== '0')
                    {
                      
                        document.getElementById("totalQues").value="";
                        document.getElementById("totalNoOfQuestions").value="";
                        document.getElementById("totDuration").value="";
                        document.getElementById("minMarks").value="";
                        
                    }
               
            }
        </script>
        <s:include value="/includes/template/headerScript.html"/>
    </head>
  
       <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="return bodyOnload();"> -->
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
                                    <li><a href="#" class="selected" rel="getCreRecords">Set Cre Subtopics</a></li>
    					 
                                </ul>
                                
                                 <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                
                                     <s:form action="%{currentAction}" theme="simple" name="creSearchForm" id="creSearchForm" onsubmit="return validateMandatoryFields();">
                                     <%
                         if(request.getAttribute("resultMessage")!=null) {
                                                out.println(request.getAttribute("resultMessage").toString());
                                        }                                     


%>   
                                        
                                        
                                     <s:hidden name="subtopicId1" value="%{subtopicId1}"/>
                                   
                                      <table align="center" border="0" cellpadding="1" cellspacing="1">
                                          
                                          <tr>
                                              <td>
                                                  <div id="examDetails">
                                                      <table border="0">
                                                          
                                                          <tr> <td class="fieldLabel">ExamType :<font color="red">*</font></td>
                                                              
                                                              <td> <s:if test="%{currentAction == 'doAddSubTopics'}">
                                                                      <s:textfield name="examType" value="" cssClass="inputSelect" id="examType" size="1" onblur="return validateExamType();"/>
                                                                  </s:if>
                                                                  <s:else>
                                                                      <s:textfield name="examType" value="%{examType}" cssClass="inputSelect" id="examType" size="1" readonly="true"/>
                                                                  </s:else>
                                                        
                                                        </td> </tr>
                                                          <tr><td class="fieldLabel" rowspan="4">SubTopics:<font color="red">*</font></td>
                                                             <td rowspan="4"><s:select name="subtopics1" id="subtopics" value="subtopics1" cssClass="inputSelect" list="subTopicsMap" size="6" multiple="true" onchange="resetNoOfQuest();"></s:select></td> 
                                                              <td class="fieldLabel">Number of questions from each subtopic:<font color="red">*</font></td>
                                                              <td><s:textfield name="totalQues"  value="%{totalQues}" cssClass="inputTextHours" id="totalQues" onkeypress="return isNumberKeyExam(event)" size="1" onfocus="getCreAvailableQuestionCount();" onchange="getTotalQuestions();"/>   <b class="hiddenLabel" id="availQuest" ></b>
                                                              <s:hidden name="availableQues" id="availableQues"/></td></tr>
                                                           <tr> <td class="fieldLabel">Total Number of Questions :<font color="red">*</font></td>
                                                               <td ><s:textfield name="totalQuest"  value="%{totalQuest}" cssClass="inputTextHours" id="totalNoOfQuestions" readonly="true"/></td>
                                                          <tr> <td class="fieldLabel">Duration :<font color="red">*</font></td>
                                                        <td ><s:textfield name="totDuration"  value="%{totDuration}"  cssClass="inputTextHours" id="totDuration" onkeypress="return isNumberKeyExam(event)" size="1" onfocus="checkSubtopics();"/><b class="fieldLabel">(Minutes)</b></td> </tr>
                                                          <tr> <td class="fieldLabel">Min Marks :<font color="red">*</font></td>
                                                        <td><s:textfield name="minMarks" value="%{minMarks}" cssClass="inputTextHours" id="minMarks" onkeypress="return isNumberKeyExam(event)" size="1" onfocus="checkSubtopics();"/></td></tr>
                                              
                                               
                                                          <tr>
                                                              <td valign="top" colspan="4">
                                                <s:if test="%{currentAction == 'doAddSubTopics'}">
                                                    <s:submit  style="margin-left: 435px;" name="submit" id="Add" value="Add" cssClass="buttonBg"  />
                                                 
                                                </s:if>
                                                <s:else>
                                                    <s:submit  style="margin-left: 435px;" name="submit" value="Update" cssClass="buttonBg" />
                                                </s:else>
                                              
                                                
                                                
                                              
                                            </td></tr>
                                                      </table>
                                                      
                                                  </div>
                                              </td>
                                          </tr>
                                          
                                      </table>
                               </s:form>
                                   
                                    
                                    <%--  </sx:tabbedpanel> --%>
 
                                 </div>
                                
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
          <script type="text/javascript">
		$(window).load(function(){
	bodyOnload();
		});
		</script>
    </body>
</html>