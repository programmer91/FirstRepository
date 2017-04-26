<%-- 
    Document   : SurveyQuestionnaireList
    Created on : Aug 26, 2015, 7:29:16 PM
    Author     : miracle
--%>



<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
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
        <title>Hubble Organization Portal :: Survey&nbsp;Form&nbsp;Questionnaire&nbsp;List</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/helpText.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   


        <script type="text/JavaScript" src='<s:url value="/includes/javascripts/GridNavigation.js"/>'></script>


        <script type="text/javascript" src="<s:url value="/includes/javascripts/marketing/SurveyFormScripts.js?version=2.4"/>"></script>   
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script> 
       
<script type="text/javascript">
        var specialKeys = new Array();
        specialKeys.push(8); 
        specialKeys.push(44);//,
        specialKeys.push(32);// Space
        specialKeys.push(40); //(
        specialKeys.push(41);//)
        specialKeys.push(43);//+
       // specialKeys.push(47);// slash/
        specialKeys.push(45);// _Under score
        
        function dropdowncheck(e) {
            var keyCode = e.which ? e.which : e.keyCode
            var ret = ((keyCode >= 48 && keyCode <= 57) ||((keyCode >=65 && keyCode <= 90))||((keyCode >=97 && keyCode <= 122))||specialKeys.indexOf(keyCode) != -1)
            document.getElementById("error").style.display = ret ? "none" : "inline";
            return ret;
        }
    </script>
<!-- <script type="text/javascript">
        var specialKeys = new Array();
        specialKeys.push(8); 
        specialKeys.push(44);
        specialKeys.push(32);
        function dropdowncheck(e) {
            var keyCode = e.which ? e.which : e.keyCode
            var ret = ((keyCode >= 48 && keyCode <= 57) ||((keyCode >=65 && keyCode <= 90))||((keyCode >=97 && keyCode <= 122))||specialKeys.indexOf(keyCode) != -1)
            document.getElementById("error").style.display = ret ? "none" : "inline";
            return ret;
        }
    </script> -->
        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>

        <%String contextPath = request.getContextPath();
        %>
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
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px ;">
                                <!--//START TABBED PANNEL : -->
                                <%
                                    //out.print("naga::"+ request.getAttribute("subnavigateTo").toString());
                                    /// session.setAttribute(ApplicationConstants.ISSUE_TO_TASK,request.getAttribute("subnavigateTo").toString());
%>

                                <ul id="accountTabs" class="shadetabs" >

                                    <%--    <% if(request.getParameter("issueList")==null)
                                           {%> --%>

                                    <li ><a href="#" class="selected" rel="issuesTab">Survey&nbsp;Form&nbsp;Questions&nbsp;List</a></li>

                                    <%--   <%}else{%>
                                       <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                        <% }%> --%>

                                </ul>


                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">

                                    <div id="issuesTab" class="tabcontent" >
                                        <!-- Questinarrie details overlay start -->
                                        <div id="overlay"></div> 
                                        <div id="specialBox">
                                            <s:form theme="simple"  align="center" name="frmResource">

                                                <s:hidden name="surveyId" id="surveyId" value="%{surveyId}"/>
                                                <s:hidden name="questionId" id="questionId"/>

                                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="headerLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="toggleCloseQuestionOverlay();" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="error" style="color: green;display: none;">Enter comma separated values</div>
                                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessage"></div>
                                                        </td>
                                                    </tr>    
                                                    <tr><td colspan="4">
                                                            <table style="width:80%;" align="center" border="0">

                                                                 <tr>

                                                                    <td class="fieldLabel" >Option&nbsp;type:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td >   <s:select name="optionType"  id="optionType" headerKey="" headerValue="--Please Select--"   list="optionTypeList" cssClass="inputSelect" onchange="checkOptionType(this);"/> 
                                                                     <a  id="Question_type"  href = "javaScript:OptionPopup('QuestionType.jsp');">
                                                                     <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" height="18" width="20" border="0">
                                                                     </a>
                                                                    </td>
                                                                      
                                                                    <td class="fieldLabel" >Sequence:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td >   <s:select id="querySequence"  name="querySequence"  list="{'1','2','3','4','5','6','7','8','9','10','11','12'}" cssClass="inputSelectSmall" /></td>

                                                                </tr> 
                                                                <tr>

                                                                    <td class="fieldLabel" >Question:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td colspan="3"><s:textfield name="questionTitle" id="questionTitle" cssClass="inputTextBlueLargeAccount" cssStyle="width: 589px; height: 20px" onchange="surveyFormFieldsValidator(this);"/></td>

                                                                </tr> 
                                                               <tr id="placeholderTr">

                                                                    <td class="fieldLabel" >Place Holder:</td>
                                                                    <td colspan="3"><s:textfield name="placeHolder" id="placeHolder" cssClass="inputTextBlueLargeAccount" cssStyle="width: 589px; height: 20px" /></td>

                                                                </tr> 

                                                                <s:hidden id="optionCount" name="optionCount"/>


                                                                <tr id="optionRow1" style="display: none">
                                                                    <td class="fieldLabel">Label1</td><td><s:textfield name="optionLabel1" id="optionLabel1" value="%{optionLabel1}" cssClass="inputTextBlue"/>   <s:select id="optionSequence1"  name="optionSequence1"  list="{'1','2','3','4','5','6'}" cssClass="inputSelectSmall" value="%{optionSequence1}"/></td>
                                                                </tr><tr id="optionRow2" style="display: none">
                                                                    <td class="fieldLabel">Label2</td><td><s:textfield name="optionLabel2" id="optionLabel2" value="%{optionLabel2}" cssClass="inputTextBlue"/>   <s:select id="optionSequence2"  name="optionSequence2"  list="{'1','2','3','4','5','6'}" cssClass="inputSelectSmall" value="%{optionSequence2}"/></td>
                                                                </tr><tr id="optionRow3" style="display: none">
                                                                    <td class="fieldLabel">Label3</td><td><s:textfield name="optionLabel3" id="optionLabel3" value="%{optionLabel3}" cssClass="inputTextBlue"/>   <s:select id="optionSequence3"  name="optionSequence3"  list="{'1','2','3','4','5','6'}" cssClass="inputSelectSmall" value="%{optionSequence3}"/></td>
                                                                </tr><tr id="optionRow4" style="display: none">
                                                                    <td class="fieldLabel">Label4</td><td><s:textfield name="optionLabel4" id="optionLabel4" value="%{optionLabel4}" cssClass="inputTextBlue"/>   <s:select id="optionSequence4"  name="optionSequence4"  list="{'1','2','3','4','5','6'}" cssClass="inputSelectSmall" value="%{optionSequence4}"/></td>
                                                                </tr><tr id="optionRow5" style="display: none">
                                                                    <td class="fieldLabel">Label5</td><td><s:textfield name="optionLabel5" id="optionLabel5" value="%{optionLabel5}" cssClass="inputTextBlue"/>   <s:select id="optionSequence5"  name="optionSequence5"  list="{'1','2','3','4','5','6'}" cssClass="inputSelectSmall" value="%{optionSequence5}"/></td>
                                                                </tr><tr id="optionRow6" style="display: none">
                                                                    <td class="fieldLabel">Label6</td><td><s:textfield name="optionLabel6" id="optionLabel6" value="%{optionLabel6}" cssClass="inputTextBlue"/>   <s:select id="optionSequence6"  name="optionSequence6"  list="{'1','2','3','4','5','6'}" cssClass="inputSelectSmall" value="%{optionSequence6}"/></td>
                                                                </tr>
                                                                <s:hidden id="dropdown" name="dropdown"/>
                                                                <tr id="dropdownoptionsRow" style="display: none">
                                                                    <td class="fieldLabel">Dropdownoptions:</td>
                                                                   
                                                                    <td><s:textarea rows="5" cols="50" name="dropdownOptions" id="dropdownOptions"  title="Please enter comma separated values" cssClass="inputTextareaOverlay1"  onkeyup="commaValidator(this);" ondrop="return false;" onpaste="return false;" onkeypress="return dropdowncheck(event);"/>
                                                                            
                                                                            <img  title="Please enter comma separated values in this field." src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/info.jpg" height="18" width="20" border="0">
                                                                        </td></tr>


                                                                <tr id="addButtonTr" style="display: none">

                                                                    <td align="right">
                                                                        <div id="addbuttonDiv" style="display: none"><button type="button" name="addButton" id="addButton" onclick="addOption();" >+</button></div>
                                                                    </td>
                                                                    <td align="left">
                                                                        <div id="removeDiv" style="display: none"><button type="button" name="removeButton" id="removeButton" onclick="removeOption();" >-</button></div>
                                                                    </td>
                                                                </tr>
                                                                
                                                              
                                                                <tr>
                                                                    <td align="right">
                                                                        <s:checkbox name="isRequired" id="isRequired" />
                                                                        
                                                                    </td><td class="fieldLabelLeft" >:Requred</td>
                                                               
                                                                    <td class="fieldLabel">Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td style="padding-right: 15px;">
                                                        
                                                            <s:select id="QuestionnaireStatus" name="QuestionnaireStatus" list="{'Active','InActive'}" cssClass="inputSelect"  />
                                                       
                                                        
                                                    
                                                    </td>
                                                                </tr>
                                                                   <tr id="attachmentRow" style="display: none">
                                                                    <td class="fieldLabel">Attachment&nbsp;Types:</td>
                                                                   <td align="left">
                                                                       <s:checkbox name="allowDocuments" id="allowDocuments" value="true"/><span class="fieldLabelLeft">Documents</span> 
                                                                     <s:checkbox name="allowPictures" id="allowPictures" value="true"/><span class="fieldLabelLeft">Pictures</span> 
                                                                     </td>
                                                                </tr>
                                                                <tr id="addTr" style="display: none"> 
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td  align="center" colspan="2" style="padding-left: 150px;">
                                                                        <input type="button" value="Add" onclick="return doAddSurveyFormQuestion();" class="buttonBg" id="addButton"/> 
                                                                    </td>
                                                                </tr><tr id="editTr" style="display: none"> 
                                                                    <td  align="center" colspan="4" style="padding-left: 448px;">
                                                                        <input type="button" value="Update" onclick="return doUpdateSurveyFormQuestion();" class="buttonBg" id="updateButton"/> 
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>    
                                        </div>

                                        <!-- Questonarrie details overlay end -->

                                        <%-- <s:form action="surveyFormListSearch" name="frmDBGrid" id="frmDBGrid" theme="simple" onsubmit="return checkMandatory();">  --%>
                                        <s:form action="updateQuestionSequence" name="frmDBGrid" id="frmDBGrid" theme="simple" > 
                                            <div style="width:840px;"> 

                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">

                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                                                <tr align="right">
                                                                    <td class="headerText" colspan="11">
                                                                        <%
                                                                        if(request.getAttribute("resultMessage")!=null){
                                                                            out.println(request.getAttribute("resultMessage").toString());
                                                                        }
                                                                        %>
                                                                        <a href="<s:url value="surveyFormListSearch.action"/>" style="align:center;">
                                                                            <img alt="Back to List"
                                                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                                 width="66px" 
                                                                                 height="19px"
                                                                                 border="0" align="bottom"></a>
                                                                        &nbsp;&nbsp;
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="fieldLabel" >Survey&nbsp;Title<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td>
                                                                        <a href="<s:url value="editSurveyForm.action?surveyId=%{surveyId}"/>" class="navigationText">
                                                                            <font color="green" size="2px"><s:property value="%{surveyTitle}"/></font></a>

                                                                    </td>
                                                                </tr> 
                                                                <s:hidden name="surveyId" id="surveyId" value="%{surveyId}"/>
                                                                <s:hidden name="currStatus" id="currStatus" value="%{currStatus}"/>
                                                                <s:hidden name="totalQuestions" id="totalQuestions" value="%{totalQuestions}"/>
                                                                <%-- <tr>
                                                                     
                                                                                        <td class="fieldLabel">Date From:</td>
                                                                                       <td><s:textfield name="createdDateFrom" id="createdDateFrom" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                                         <a href="javascript:cal1.popup();">
                                                                                               <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                                width="20" height="20" border="0"></a>
                                                                                           </td>
                                                                                            <td class="fieldLabel">To:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                                                       <td>
                                                                                           <s:textfield name="createdDateTo"  id="createdDateTo" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                                                           <a href="javascript:cal2.popup();">
                                                                                               <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                                width="20" height="20" border="0"></a>
                                                                                       </td>
                                                                                       
                                                                   </tr> 
                                                                   <tr>
                                                                       <td class="fieldLabel">Title:</td>
                                                                       <td > <s:textfield name="surveyTitle"  id="surveyTitle" cssClass="inputTextBlue" /></td>
                                                                       
                                                                      
                                                                       
                                                                      
                                                                   </tr> --%>


                                                                <tr>
                                                                     
                                                                    <td></td>
                                                                    <td colspan="2"></td>
                                                                    <td>
                                                                        <s:if test="%{currStatus=='Active'}">
                                                                             <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addSurveyFormQuestion();" />
                                                                        </s:if>
                                                                        <%-- <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addQuestionnaire();" /> --%>
                                                                       

                                                                        <%--     <s:submit name="search" value="Search" cssClass="buttonBg"/> --%>
                                                                    </td>
                                                                </tr>

                                                                <s:if test="#session.surveyFormQuestionnaireList != null"> 
                                                                    <tr><td colspan="4" align="center"><span id="loadMsg" style="display: none"><font color="red" size="2px">Processing please wait..</font></span></td></tr>
                                                                    <tr>
                                                                        <td colspan="4">
                                                                            <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                                                                                
                                                                                <%java.util.List mainList = (java.util.List) session.getAttribute("surveyFormQuestionnaireList");
                                                                                    if (mainList.size() > 0) {


                                                                                %>
                                                                                <input type="hidden" name="questionCount" id="questionCount" value="<%=mainList.size()%>"/>
                                                                                <%--<tr align="right"><td colspan="6" style="background-color:white; "><s:submit value="Update" name="update" id="update" cssClass="buttonBg"/>
                                                                                    <input type="button" class="buttonBg"  align="right"  value="Add" onclick="getQuestionIds();" />
                                                                                    
                                                                                    </td></tr> --%>
                                                                                <tr class="gridHeader">
                                                                                    <th>Edit</th>
                                                                                    <th>Title</th>
                                                                                    <th>Type</th>
                                                                                   
                                                                                    <th>Required</th>
                                                                                    <th>Status</th>
                                                                                    <%-- <th>Sequence</th> --%>
                                                                                    <th>Sequence&nbsp;
                                                                                        <s:if test="%{currStatus=='Active'}">
                                                                                            
                                                                                            <a href="#" onclick="doUpdateSequance();">
                                                                                              <!--  <img src="../../includes/images/whiteArrow.png" width="20px" height="20px"/> -->
                                                                                                <!-- <img src="../../includes/images/arrow_down.png" width="20px" height="20px"/> -->
                                                                                                
                                                                                               <!-- <img src="../../includes/images/kdevelop_down.png" width="20px" height="20px" onmouseover="Tip('Click here to update sequance.')" onmouseout="UnTip()"/> -->
                                                                                                <img src="../../includes/images/down_grey.png" width="20px" height="20px" onmouseover="Tip('Click here to update sequance.')" onmouseout="UnTip()"/>
                                                                                                
                                                                                            </a>
                                                                                           <!-- <input type="button" class="buttonBg"  align="right"  value="Update" onclick="doUpdateSequance();" />-->
                                                                                        </s:if>
                                                                                        
                                                                                    </th>
                                                                                <%--    <s:if test="%{currStatus=='Active'}">
                                                                                        <th>Delete</th>
                                                                                    </s:if> --%>
                                                                                    
                                                                                </tr>
                                                                                <%

                                                                                    for (int i = 0; i < mainList.size(); i++) {
                                                                                %>
                                                                                <tr CLASS="gridRowEven">
                                                                                    <%
                                                                                        //java.util.List subList = (java.util.List)mainList.get(i);
                                                                                        java.util.Map subList = (java.util.Map) mainList.get(i);
                                                                                        //  for (int j = 0; j < subList.size(); j++) {

                                                                                    %>
                                                                                    <td>

                                                                                        <%--   <a style="color:#C00067;" href="editQuestionnaireDetails.action?questionId=<%=subList.get("Id")%>&surveyId=%{surveyId}"> --%>
                                                                                        <a style="color:#C00067;" href="javascript:getQuestionnaireDetails('<%=subList.get("Id")%>')">
                                                                                            <img src="../../includes/images/DBGrid/Edit.gif"/>
                                                                                        </a>

                                                                                    </td>
                                                                                    <td class="title">
                                                                                        <%
                                                                                            out.println(subList.get("Query").toString());
                                                                                        %>
                                                                                    </td>

                                                                                    <td class="title">
                                                                                        <%
                                                                                            out.println(subList.get("OptionType").toString());
                                                                                        %>
                                                                                    </td>
                                                                                    <%-- <td class="title"> --%>
                                                                                         
                                                                                           <%--  <s:set name="tabName" ><%=subList.get("Sequence").toString()%></s:set>
                                                                                             <s:set name="seq" ><%=i%></s:set>
                                                                                              <s:set name="questId" ><%=subList.get("Id").toString()%></s:set>
                                                                                           
                                                                                             <s:if test="#seq==0">
                                                                                                 
                                                                                                 <s:hidden name="questionId1" id="questionId1" value="#questId"/>
                                                                                                 <s:select id="querySequence1"  name="querySequence1"  list="{'1','2','3','4','5','6','7','8','9','10','11','12'}" cssClass="inputSelectSmall" value="#tabName"/>
                                                                                             </s:if><s:elseif test="#seq==1">
                                                                                                   
                                                                                                 <s:hidden name="questionId2" id="questionId2" value="#questId"/>
                                                                                                 <s:select id="querySequence2"  name="querySequence2"  list="{'1','2','3','4','5','6','7','8','9','10','11','12'}" cssClass="inputSelectSmall" value="#tabName"/>
                                                                                             </s:elseif><s:elseif test="#seq==2">
                                                                                                 <s:hidden name="questionId3" id="questionId3" value="#questId"/>
                                                                                                 <s:select id="querySequence3"  name="querySequence3"  list="{'1','2','3','4','5','6','7','8','9','10','11','12'}" cssClass="inputSelectSmall" value="#tabName"/>
                                                                                             </s:elseif><s:elseif test="#seq==3">
                                                                                                 <s:hidden name="questionId4" id="questionId4" value="#questId"/>
                                                                                                 <s:select id="querySequence4"  name="querySequence4"  list="{'1','2','3','4','5','6','7','8','9','10','11','12'}" cssClass="inputSelectSmall" value="#tabName"/>
                                                                                             </s:elseif><s:elseif test="#seq==4">
                                                                                                 <s:hidden name="questionId5" id="questionId5" value="#questId"/>
                                                                                                 <s:select id="querySequence5"  name="querySequence5"  list="{'1','2','3','4','5','6','7','8','9','10','11','12'}" cssClass="inputSelectSmall" value="#tabName"/>
                                                                                             </s:elseif><s:elseif test="#seq==5">
                                                                                                 <s:hidden name="questionId6" id="questionId6" value="#questId"/>
                                                                                                 <s:select id="querySequence6"  name="querySequence6"  list="{'1','2','3','4','5','6','7','8','9','10','11','12'}" cssClass="inputSelectSmall" value="#tabName"/>
                                                                                             </s:elseif><s:elseif test="#seq==6">
                                                                                                 <s:hidden name="questionId7" id="questionId7" value="#questId"/>
                                                                                                 <s:select id="querySequence7"  name="querySequence7"  list="{'1','2','3','4','5','6','7','8','9','10','11','12'}" cssClass="inputSelectSmall" value="#tabName"/>
                                                                                             </s:elseif><s:elseif test="#seq==7">
                                                                                                 <s:hidden name="questionId8" id="questionId8" value="#questId"/>
                                                                                                 <s:select id="querySequence8"  name="querySequence8"  list="{'1','2','3','4','5','6','7','8','9','10','11','12'}" cssClass="inputSelectSmall" value="#tabName"/>
                                                                                             </s:elseif><s:elseif test="#seq==8">
                                                                                                 <s:hidden name="questionId9" id="questionId9" value="#questId"/>
                                                                                                 <s:select id="querySequence9"  name="querySequence9"  list="{'1','2','3','4','5','6','7','8','9','10','11','12'}" cssClass="inputSelectSmall" value="#tabName"/>
                                                                                             </s:elseif> --%>
                                                                                             
                                                                                             
                                                                                    <%--     <%
                                                                                            out.println(subList.get("Sequence").toString());
                                                                                        %> 
                                                                                    </td> --%>
                                                                                    
                                                                                    <td class="title">
                                                                                        <%
                                                                                        if(subList.get("RequiredFlag").toString().equals("1"))
                                                                                            out.println("Yes");
                                                                                            else
                                                                                              out.println("No");  
                                                                                        %>
                                                                                    </td><td class="title">
                                                                                        <%
                                                                                            out.println(subList.get("CurrStatus").toString());
                                                                                        %>
                                                                                    </td>
                                                                                    
                                                                                   <td width="150px;">
                                                                                       <s:if test="%{currStatus=='Active'}">
                                                                                           <input type="text" name="questSeq_<%=(i+1)%>" id="questSeq_<%=(i+1)%>" value="<%=subList.get("Sequence").toString()%>" class="gridCol" onkeyup="checkSequance(this);" style="width: 30px;"/>
                                                                                        <input type="hidden" name="qestId_<%=(i+1)%>" id="qestId_<%=(i+1)%>" value="<%=subList.get("Id")%>"/>
                                                                                    
                                                                                       </s:if><s:else>
                                                                                           <%out.println(subList.get("Sequence").toString());%>
                                                                                       </s:else>
                                                                                      
                                                                                    </td> 
                                                                                 <%--   <s:if test="%{currStatus=='Active'}">
                                                                                         <td class="gridColumn" align="left"><a href="javascript:doDeleteQuestion('<%=subList.get("Id")%>')" ><img src="../../includes/images/DBGrid/Delete.png"></a></td>
                                                                                    </s:if> --%>
                                                                                   

                                                                                </tr>
                                                                                <% }%>


                                                                                <%


                                                                                } else {
                                                                                %>
                                                                                <tr><td>
                                                                                        <%
                                                                                            // String contextPath = request.getContextPath();
                                                                                            // out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/><b> No Records Found to Display!</b>");

                                                                                            out.println("<img  border='0' align='top'  src='" + contextPath + "/includes/images/alert.gif'/> <font color='white'><b>No records found!</b></font>");
                                                                                            // }

                                                                                        %>
                                                                                    </td>
                                                                                </tr>
                                                                                <%}%>

                                                                            </table>
                                                                        </td>
                                                                    </tr>



                                                                    <%

                                                                        if (mainList.size() != 0) {
                                                                    %>

                                                                    <tr>

                                                                        <td align="right" colspan="4" style="background-color:white;" >
                                                                            <div align="right" id="pageNavPosition">hello</div>
                                                                        </td>
                                                                    </tr> 

                                                                    <%}
                                                                    %>
                                                                </s:if>


                                                            </table>
                                                        </td>
                                                    </tr>





                                                    <%-- <script type="text/JavaScript">
                                                                                                 var cal1 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateFrom']);
                                                                                                     cal1.year_scroll = true;
                                                                                                     cal1.time_comp = false;

                                             var cal2 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateTo']);
                                                 cal2.year_scroll = true;
                                                 cal2.time_comp = false;

                                                
                                        </script>   --%>                                                          


                                                    <script type="text/javascript">
                                                        var pager = new ReviewPager('results', 10); 
                                                        pager.init(); 
                                                        pager.showPageNav('pager', 'pageNavPosition'); 
                                                        pager.showPage(1);
                                                    </script>      

                                                </table>
                                            </div>    
                                        </s:form>  


                                        <%--  </sx:div > --%>
                                    </div> 
                                    <%--   <%}%> --%>
                                    <!--//END TAB : -->

                                    <%--  <sx:div id="IssuesSearchTab" label="Issues Search" > --%>

                                </div>
                                <!--//END TAB : -->
                                <%--  </sx:tabbedpanel> --%>

                                <!--//END TABBED PANNEL : -->
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
                <!--//END DATA COLUMN : Coloumn for Screen Content-->
            </tr>



            <!--//END DATA RECORD : Record for LeftMenu and Body Content-->

            <!--//START FOOTER : Record for Footer Background and Content-->
            <tr class="footerBg">
                <td align="center"><s:include value="/includes/template/Footer.jsp"/>   </td>
            </tr>
            <tr>
                <td>

                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>

                </td>
            </tr>

            <!--//END FOOTER : Record for Footer Background and Content-->

        </table>
        <!--//END MAIN TABLE : Table for template Structure-->



 <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script>
            $(function() {
                $( document ).tooltip();
            });
        </script>
 <script type="text/JavaScript">
              function OptionPopup(url) {
                newwindow=window.open(url,'name','scrollbars=1,height=600,width=500,top=200,left=250');
                if (window.focus) {newwindow.focus()}
           }
           
        </script>

    </body>
</html>



