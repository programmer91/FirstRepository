<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  july 21, 2013, 3:25 PM
 *
 * Author  : Prasad Kandregula<vkandregula@miraclesoft.com>
 *
 * File    : QuestionsList.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>--%>
<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hubble Organization Portal :: Ecertification AddQuestions</title>
  <%--  <sx:head cache="true"/> --%>
   
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
     <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ecertification/EcertStandardClientValidation.js"/>"></script>
    <script type="text/javascript">

function stopRKey(evt) {
  var evt = (evt) ? evt : ((event) ? event : null);
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;}
}

document.onkeypress = stopRKey;


function changeVisibility()
{
   // alert("hello");
    
    if(document.getElementById("checkAlert").checked)
    {
    document.getElementById("days").disabled=false ;
     
    }else
    {
    document.getElementById("days").disabled=true ;
    document.getElementById("days").value="" ;
    }
    
}
function checkedAlert()
{
    document.getElementById("checkAlert").checked = true;
   
}
function fieldLengthValidatorForQuestions()
{
//alert(document.getElementById("question").value.length);
if(document.getElementById("question").value.length > 10)
{
alert("Question length shuold be less than 1000");
return false;
}
else{
return true;
}
alert("Hi");
}
    </script> 

    
    
     <style type="text/css">
            
            .popupItem:hover {
            background: #F2F5A9;
            font: arial;
            font-size:10px;
            color: black;
            }
            
            .popupRow {
            background: #3E93D4;
            }
            
            
            .popupItem {
            padding: 2px;
            width: 100%;
            border: black;
            font:normal 9px verdana;
            color: white;
            text-decoration: none;
            line-height:13px;
            z-index:100;
            }
            
        </style>
    
    
</head>
<body class="bodyGeneral" oncontextmenu="return false;"> 

<!--//START MAIN TABLE : Table for template Structure-->
<table class="templateTable1000x580" align="center" cellpadding="0" cellspacing="0">

<!--//START HEADER : Record for Header Background and Mirage Logo-->
<tr class="headerBg">
    <td valign="top">
        <s:include value="/includes/template/Header.jsp" />                    
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
            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">
                <ul id="accountTabs" class="shadetabs" >
                     <s:if test="%{currentAction == 'updateQuestion'}">
                        <li ><a href="#" class="selected" rel="issueTab"  >Update Question </a></li>
                    </s:if>
                    <s:if test="%{currentAction == 'createQuestion'}">
                        <li ><a href="#" class="selected" rel="issueTab"  >Create Question </a></li>
                    </s:if>
                    

                </ul>
                <!--//START TABBED PANNEL : -->
                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                <!--//START TAB : -->
                <div id="issueTab" class="tabcontent"  >
                   
                    <s:form name="createQuestionForm" id="createQuestionForm" action="%{currentAction}" method="POST" theme="simple" onsubmit="return checkMandatoryFields();">
                       <s:hidden value="%{topicId}" name="topicId" /> 
                        <s:hidden value="%{id}" name = "id"/>
                        <table width="100%" cellpadding="2" cellspacing="2" border="0" >
                         <tr align="right">
                                    <td class="headerText" colspan="2">
                                       <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">
                                        <% if(request.getAttribute("resultMessage") != null){
                                            out.println(request.getAttribute("resultMessage"));
                                            session.removeAttribute("resultMessage");
                                        }%>
                                      
                                    </td>
                                  <%--   <s:if test="%{currentAction == 'updateQuestion'}">
                                      <td valign="middle">
                                         <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                                </td>
                                    </s:if>
                                                 <s:else> --%>
                                         <td valign="middle">
                                             <a href="<s:url value="questionsBackToList.action"/>?topicId=<s:property value="%{topicId}" />  " style="align:center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="bottom"></a>
                                                </td>
                                 <%--   </s:else>      --%>
                                </tr> 
                                <tr>
                                    <td>
                                        <table style="padding-right: 15%" width="100%" cellpadding="2" cellspacing="2" border="0" >
                                             <tr>
                                      
                                <td  class="fieldLabel" width="35%">Subtopic :</td>                                                                  
                                <td  width="40%"><s:select label="Select Subtopic" id="subTopicId" 
                                                                       name="subTopicId" headerKey=""            
                                                                       headerValue="-- Please Select --"
                                                                       list="subTopicMap" cssClass="inputSelect" value="%{currentQuestion.subtopicId}" /></td>
                                </tr>
                                 
                                 <tr>
                                      
                                <td  class="fieldLabel">Question:</td>                                                                  
                                <td  ><s:textarea name="question" id="question" value="%{currentQuestion.question}"
                                                                  onchange="fieldLenghtValidator(this);escapeHTML(this);" 
                                                             cssClass="inputTextareaDesc" onkeyup="autoresize(this);"/></td>
                                </tr>
                                
                               
                                    <tr>
                                    <td  class="fieldLabel">Option&nbsp;1&nbsp;: </td>
                                    <td >  
                                        <s:textfield name="option1" id="option1" value="%{currentQuestion.option1}" cssClass="inputTextBlueCustomer"  onchange="fieldLenghtValidator(this);escapeHTML(this);" theme="simple" style="width :450px;"/>
                                     </tr>  
                                     <tr>
                                    </td>
                                    
                                    <td  class="fieldLabel">Option&nbsp;2&nbsp;: </td>
                                    <td >  
                                        <s:textfield name="option2" id="option2" value="%{currentQuestion.option2}" cssClass="inputTextBlueCustomer"  onchange="fieldLenghtValidator(this);escapeHTML(this);"  theme="simple" style="width :450px;"/>
                                        
                                    </td>
                                     </tr> 
                                     <tr>
                                    <td  class="fieldLabel">Option&nbsp;3&nbsp;: </td>
                                    <td >  
                                        <s:textfield name="option3" id="option3" cssClass="inputTextBlueCustomer" value="%{currentQuestion.option3}" onchange="fieldLenghtValidator(this);escapeHTML(this);" theme="simple" style="width :450px;"/>
                                        
                                    </td>
                                     </tr> 
                                     <tr>
                                    <td  class="fieldLabel">Option&nbsp;4&nbsp;: </td>
                                    <td >  
                                        <s:textfield name="option4" id="option4" value="%{currentQuestion.option4}" cssClass="inputTextBlueCustomer"  onchange="fieldLenghtValidator(this);escapeHTML(this);" theme="simple" style="width :450px;"/>
                                        
                                    </td>
                                     </tr> 
                                      <tr>
                                    <td  class="fieldLabel">Answer(1-4)&nbsp;: </td>
                                    <td >  
                                        <s:textfield name="answer" id="answer" value="%{currentQuestion.answer}"  cssClass="inputTextBlueCustomer"  onchange="return allowAnswer(event)"  theme="simple" style="width :25px;"/>
                                        
                                    </td>
                                     </tr> 
                            
                            <tr>
                                <td  class="fieldLabel">Description :</td>                                                                  
                                <td  ><s:textarea name="description" id="description" value="%{currentQuestion.description}" 
                                                                onchange="fieldLenghtValidator(this);escapeHTML(this);"
                                                             cssClass="inputTextareaDesc" onkeyup="autoresize(this);"/></td>
                            </tr>
                       
                           
                         <s:hidden value="%{currentAction}" name="currentAction" /> 
                         
                        
                            <s:if test="%{currentAction == 'createQuestion'}">
                                <tr align="right">
                                    <s:if test="%{QuesCreateSuccess == 2 || QuesCreateSuccess == 0}">
                                    <td colspan="2" >
                                    <s:submit label="Submit" value="Add" cssClass="buttonBg" /></td>
                                    </s:if>
                                   
                                     
                                </tr>
                            </s:if> 
                            
                            <s:if test="%{currentAction == 'updateQuestion'}">
                                  <tr align="right">
                                    
                                    <td colspan="2" >
                                    <s:submit label="Submit" value="Update" cssClass="buttonBg" /></td>
                                     
                                </tr>
                            </s:if> 
                                        </table>
                                    </td>
                                </tr>
                          
                            
                        </table>
                      </s:form>
                    
                    <!-- this script for After loading Form it will instantiate Calender Objects as you require -->
    
                </div>                                       
    </div>                               
    <%--  </sx:tabbedpanel> --%>
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
    <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
</tr>
<!--//END FOOTER : Record for Footer Background and Content-->
 <tr>
                <td>
                    
                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>
                    
                </td>
            </tr>
</table>
<!--//END MAIN TABLE : Table for template Structure-->

</body>
</html>


