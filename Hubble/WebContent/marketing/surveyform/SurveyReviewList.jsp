<%-- 
    Document   : SurveyReviewList
    Created on : Aug 31, 2015, 5:02:17 PM
    Author     : miracle
--%>



<%@page import="java.io.File"%>
<%@page import="java.util.Map"%>
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
        <title>Hubble Organization Portal :: Survey&nbsp;Form&nbsp;Review&nbsp;List</title>
       
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
       
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
     
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
 
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
   
  
      <script type="text/JavaScript" src='<s:url value="/includes/javascripts/GridNavigation.js"/>'></script>
   
      <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
    <script type="text/javascript" src="<s:url value="/includes/javascripts/marketing/SurveyFormScripts.js"/>"></script>   
       
  
     
        <s:include value="/includes/template/headerScript.html"/>
        <%-- for issue reminder popup --%>
    </head>
    <%-- <body class="bodyGeneral" oncontextmenu="return false;" onload="checkAdvSearch();"> --%>
    <body class="bodyGeneral" oncontextmenu="return false;" >
        <s:hidden name="searchQuestionValue" id="searchQuestionValue" value="%{searchQuestion}"/>
        <s:hidden name="searchQuestionValue2" id="searchQuestionValue2" value="%{searchQuestion2}"/>
        
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
                                       
                                        <li ><a href="#" class="selected" rel="issuesTab">Survey&nbsp;Form&nbsp;Review&nbsp;List</a></li>
                                       
                                 <%--   <%}else{%>
                                    <li ><a href="#" class="selected" rel="IssuesSearchTab">Tasks Search</a></li>
                                     <% }%> --%>
                                    
                                </ul>
                                 <s:hidden name="attachmentAvailable" id="attachmentAvailable" value="%{attachmentAvailable}"/>
                                
                                <%-- <sx:tabbedpanel id="IssuesPanel" cssStyle="width: 845px; height: 500px;padding:5px 5px;" doLayout="true"> --%>
                                <div  style="border:1px solid gray; width:845px;height: 500px; overflow:auto; margin-bottom: 1em;">
                                
                            <div id="issuesTab" class="tabcontent" >
                                                  
                                     <div id="overlay" ></div> 
                                                  <div id="specialBox" >
                                                       <s:form theme="simple"  align="center" >
                                                           
                                                           
                                                       
                                                           
                                                           <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                                  <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="headerLabel"></span>


                                                        </h3></td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="javascript:showServeyReviewDetails(0)" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  

                                                    </td></tr>
                                                               <tr>
                                                    <td colspan="4">
                                                        <div id="load" style="color: green;display: none;">Loading..</div>
                                                        <div id="resultMessage"></div>
                                                    </td>
                                                </tr>    
                                                        <tr><td colspan="4">
                                                         <table style="width:80%;" align="center">
                                                                       <tr>


                                                     <td class="fieldLabel" >Name: </td>
                                                     <td><s:textfield name="sfName" id="sfName" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);" /></td>
                                                      <td class="fieldLabel" valign="top">Phone:</td>
                                                        <td><s:textfield name="sfPhone" id="sfPhone" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);" /></td>
                                                  
                                                       </tr>
                                                      <%--  <s:if test="%{surveyType=='I'}"> --%>
                                                           <%if(request.getAttribute("surveyType").toString().equals("I")){%>
                                                           <tr>
                                                        <td class="fieldLabel" >E-Mail:</td>
                                                     <td><s:textfield name="sfEmail" id="sfEmail" cssClass="inputTextBlue" onchange="return fieldLengthValidator2(this);" /></td>
                                                     <td class="fieldLabel" valign="top">Empno:</td> 
                                                      <td><s:textfield name="sfEmpno" id="sfEmpno" cssClass="inputTextBlue"  /></td>
                                                           </tr>
                                                   <%--    </s:if> --%>
                                                           <%}else {%>
                                                     <%--  <s:else> --%>
                                                           <tr>
                                                        <td class="fieldLabel" >E-Mail:</td>
                                                     <td valign="top"><s:textfield name="sfEmail" id="sfEmail" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" /></td>
                                                         </tr>
                                                      <%-- </s:else> --%>
                                                         
                                                       <%}%>  


                                                      <%--     <tr>
                                                        <td class="fieldLabel" valign="top">E-Mail:</td>
                                                     <td colspan="3" valign="top"><s:textfield name="sfEmail" id="sfEmail" cssClass="inputTextBlueLargeAccount" onchange="return fieldLengthValidator2(this);" /></td>
                                                            </tr> --%>
                                                   <%
                                                     
                                                       java.util.List questionaryList = (java.util.List) session.getAttribute("surveyFormQuestionnaireList");
                                                       for(int qes=0;qes<questionaryList.size();qes++){
                                                             Map firstMap = (Map)questionaryList.get(qes);
                                                     
                                                       %>                       
                                                       <tr>
                                                           <td class="fieldLabel"><%
                                                       out.println(firstMap.get("Query"));
                                                       %>:</td>
                                                           
                                                           
                                                               <%
                                                      // out.println(firstMap.get("Query"));
                                                       if(firstMap.get("OptionType").toString().equals("Textbox")){
                                                           %>
                                                           <td colspan="3" ><input type="text" class="inputTextBlue" id="questionId<%=firstMap.get("Id")%>" name="questionId<%=firstMap.get("Id")%>"/></td>
                                                               <%
                                                       }else if(firstMap.get("OptionType").toString().equals("TextArea")){
                                                         %><td colspan="3"><textarea class="inputTextareaOverlay1" cols="60" rows="5" id="questionId<%=firstMap.get("Id")%>" name="questionId<%=firstMap.get("Id")%>"></textarea></td>
                                                           
                                                           
                                                         
                                                      <% }else if(firstMap.get("OptionType").toString().equals("Checkbox")){
                                                         int OptionCount = Integer.parseInt(firstMap.get("OptionCount").toString());
                                                                                                        ;
                                                         %>
                                                         <td colspan="3" class="fieldLabelLeft">  <input type="checkbox" name="questionId<%=firstMap.get("Id")%>[]" id="questionId<%=firstMap.get("Id")%>" value="<%=firstMap.get("OptionLabel1").toString()%>"/>&nbsp;&nbsp;
                                                         <%out.println(firstMap.get("OptionLabel1"));
                                                         if(OptionCount>1){
                                                         %>
                                                         <input type="checkbox" name="questionId<%=firstMap.get("Id")%>[]" id="questionId<%=firstMap.get("Id")%>" value="<%=firstMap.get("OptionLabel2").toString()%>"/>&nbsp;&nbsp;
                                                         <%out.println(firstMap.get("OptionLabel2")); 
                                                         }if(OptionCount>2){%>
                                                         <input type="checkbox" name="questionId<%=firstMap.get("Id")%>[]" id="questionId<%=firstMap.get("Id")%>" value="<%=firstMap.get("OptionLabel3").toString()%>"/>&nbsp;&nbsp;
                                                           <%
                                                         out.println(firstMap.get("OptionLabel3")); 
                                                                }if(OptionCount>3){%>
                                                         <input type="checkbox" name="questionId<%=firstMap.get("Id")%>[]" id="questionId<%=firstMap.get("Id")%>" value="<%=firstMap.get("OptionLabel4").toString()%>"/>&nbsp;&nbsp;
                                                           <%
                                                         out.println(firstMap.get("OptionLabel4")); 
                                                                }if(OptionCount>4){%>
                                                         <input type="checkbox" name="questionId<%=firstMap.get("Id")%>[]" id="questionId<%=firstMap.get("Id")%>" value="<%=firstMap.get("OptionLabel5").toString()%>"/>&nbsp;&nbsp;
                                                           <%
                                                         out.println(firstMap.get("OptionLabel5")); 
                                                                }if(OptionCount>5){%>
                                                         <input type="checkbox" name="questionId<%=firstMap.get("Id")%>[]" id="questionId<%=firstMap.get("Id")%>" value="<%=firstMap.get("OptionLabel6").toString()%>"/>&nbsp;&nbsp;
                                                           <%
                                                         out.println(firstMap.get("OptionLabel6")); 
                                                                }%>
                                                           </td>  
                                                      <% }else if(firstMap.get("OptionType").toString().equals("RadioButton")){
                                                         int OptionCount = Integer.parseInt(firstMap.get("OptionCount").toString());
                                                                                                        ;
                                                         %>
                                                        <td colspan="3" class="fieldLabelLeft">  <input type="radio" name="questionId<%=firstMap.get("Id")%>" id="questionId<%=firstMap.get("Id")%>" value="<%=firstMap.get("OptionLabel1").toString()%>"/>&nbsp;&nbsp;
                                                         <%out.println(firstMap.get("OptionLabel1"));
                                                         if(OptionCount>1){
                                                         %>
                                                         <input type="radio" name="questionId<%=firstMap.get("Id")%>" id="questionId<%=firstMap.get("Id")%>" value="<%=firstMap.get("OptionLabel2").toString()%>"/>&nbsp;&nbsp;
                                                         <%out.println(firstMap.get("OptionLabel2")); 
                                                         }if(OptionCount>2){%>
                                                         <input type="radio" name="questionId<%=firstMap.get("Id")%>" id="questionId<%=firstMap.get("Id")%>" value="<%=firstMap.get("OptionLabel3").toString()%>"/>&nbsp;&nbsp;
                                                           <%
                                                         out.println(firstMap.get("OptionLabel3")); 
                                                                }if(OptionCount>3){%>
                                                         <input type="radio" name="questionId<%=firstMap.get("Id")%>" id="questionId<%=firstMap.get("Id")%>" value="<%=firstMap.get("OptionLabel4").toString()%>"/>&nbsp;&nbsp;
                                                           <%
                                                         out.println(firstMap.get("OptionLabel4")); 
                                                                }if(OptionCount>4){%>
                                                         <input type="radio" name="questionId<%=firstMap.get("Id")%>" id="questionId<%=firstMap.get("Id")%>" value="<%=firstMap.get("OptionLabel5").toString()%>"/>&nbsp;&nbsp;
                                                           <%
                                                         out.println(firstMap.get("OptionLabel5")); 
                                                                }if(OptionCount>5){%>
                                                         <input type="radio" name="questionId<%=firstMap.get("Id")%>" id="questionId<%=firstMap.get("Id")%>" value="<%=firstMap.get("OptionLabel6").toString()%>"/>&nbsp;&nbsp;
                                                           <%
                                                         out.println(firstMap.get("OptionLabel6")); 
                                                                }%>
                                                           </td>  
                                                      <% } else if(firstMap.get("OptionType").toString().equals("Attachment")){%>
                                                      <td colspan="3" ><span id="attachmentLink<%=firstMap.get("Id")%>"></span></td>
                                                    <%  }else if(firstMap.get("OptionType").toString().equals("Slider")){%>
                                                      <td colspan="3" >
                                                         
                                                      <b>0</b> <input type = "range" min="0" max="100"  name="questionId<%=firstMap.get("Id")%>" id="questionId<%=firstMap.get("Id")%>" onchange="setRangeValue(this,'rangevalue<%=firstMap.get("Id")%>');"/><b>100</b>
                                                      <center> <span id="rangevalue<%=firstMap.get("Id")%>"></span></center>
                                                      </td>
                                                      <% }else if(firstMap.get("OptionType").toString().equals("DropDown")){
                                                        String optionName = firstMap.get("dropdownOptions").toString();
                                                                                                       ;
                                                        %>
                                                       <td colspan="3" class="fieldLabelLeft"> 
                                                           <select class="inputTextBlue" id="questionId<%=firstMap.get("Id")%>" name="questionId<%=firstMap.get("Id")%>">
                                                            <option value="">Select option</option>
                                                               <%   String[]  optionNames=optionName.split(",");
                                                           for(int i=0;i<optionNames.length;i++){
                                                               %>
                                                      <option  value="<%=optionNames[i]%>"><%out.println(optionNames[i]);%></option>
                                                      <% }%></select>
                                                          </td>
                                                    <%  } else if(firstMap.get("OptionType").toString().equals("ANONYMOUS")){
                                                        %>
                                                        <td colspan="3" >
                                                            <span id="anonymous<%=firstMap.get("Id")%>"></span>
                                                        </td>
                                                      
                                                      <%
                                                    }
                                                                                      }
                                                        %> 
                                                                                      
                                                        
                                                  </tr>
                                                  <tr id="createdTr">
                                                     
                                                       <td class="fieldLabel" valign="top">CreatedDate:<FONT color="red"  ><em>*</em></FONT> </td>
                                                       <td colspan="3"><FONT color="green"  ><span id="createdDate"></span></FONT></td>
                                                  </tr>
                                                       
                                                               </table>
                                                           </td>
                                                       </tr>
                                                           </table>
                                                       </s:form>    
                                                         </div>

                                     
                                                           <s:form action="surveyFormReviewListSearch" name="frmDBGrid" id="frmDBGrid" theme="simple" onsubmit="return checkMandatory();"> 
                                          
                                               
                                                <table cellpadding="0" cellspacing="0" align="left" width="100%">
                    
                 <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="2" align="left" width="100%">
                                <tr align="right">
                                    <td class="headerText" colspan="11">
                                     <%--   <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0"> --%>
                                         <a href="<s:url value="surveyFormListSearch.action"/>" style="align:center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="bottom"></a>
                                        &nbsp;&nbsp;
                                    </td>
                                </tr>
                                 <s:hidden name="surveyId" id="surveyId" value="%{surveyId}"/>
                              <tr>
                                  <td class="fieldLabel">Survey&nbsp;Title<FONT color="red"  ><em>*</em></FONT> </td>
                                                                 <td colspan="3">
                                                                       <a href="<s:url value="editSurveyForm.action?surveyId=%{surveyId}"/>" class="navigationText">
                                                                     <font color="green" size="2px"><s:property value="%{surveyTitle}"/></font></a>
                                                                     
                                                                 </td>
                                                                 
                                 </tr> 
                                 <tr>
                                    
                                  
                                                     <td class="fieldLabel">Created&nbsp;Date&nbsp;From:</td>
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
                                    <td class="fieldLabel">FullName:</td>
                                    <td > <s:textfield name="fullName"  id="fullName" cssClass="inputTextBlue" /></td>
                                    
                                    <td class="fieldLabel">PhoneNumber:</td>
                                    <td > <s:textfield name="phoneNumber"  id="phoneNumber" cssClass="inputTextBlue" /></td>
                                    
                                   
                                </tr>
                              <tr>
                                    <td class="fieldLabel">Email:</td>
                                    <td > <s:textfield name="emailId"  id="emailId" cssClass="inputTextBlue" /></td>
                               <%--     <td class="fieldLabel">
                                        <!-- <div id="fsCollImgSpan"></div> -->
                                        <img src="../../includes/images/dtp/cal_minus.gif" alt="nag" width="20" height="20" border="0" onclick="javascript:hideSearch()" id="fsCollImg" />
                                      
                                    </td>
                                    <td class="fieldLabelLeft"><font color="red">*</font>Advanced Search</td> --%>
                               <td></td>
                               <td >
                                       
                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                    </td>
                              </tr>
                              
                              <tr id="SearchByTr" style="display: none">
                                                 <td class="fieldLabel">Field1:</td>
                                                 <td >   <s:select id="searchQuestionId"  name="searchQuestionId"  list="questionMap" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" onchange="getSearchQuestionInfo(this,'');"/></td>
                               <td></td>
                                <td></td>
                              </tr>
                         
                            <tr id="searchdivTr1" style="display: none" align="right">
                             <td class="fieldLabel"><span id="searchLabelId"></span></td><td class="fieldLabelLeft"><span id="searchOptionId"></span></td><td></td><td></td>
                                
                            </tr>
                                 
                               <tr id="SearchByTr2" style="display: none">
                                                 <td class="fieldLabel">Field2:</td>
                                                 <td >   <s:select id="searchQuestionId2"  name="searchQuestionId2"  list="questionMap" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" onchange="getSearchQuestionInfo2(this,'');"/></td>
                               <td></td>
                                <td></td>
                              </tr>
                             <tr id="searchdivTr2" style="display: none" align="right">
                                <td class="fieldLabel"><span id="searchLabelId2"></span></td><td class="fieldLabelLeft"><span id="searchOptionId2"></span></td><td></td><td></td>
                            </tr>
                              <%--  <tr>
                                    <td></td> 
                                    <td></td>
                                    <td></td>
                                   
                                    <td>
                                       
                                        <s:submit name="search" value="Search" cssClass="buttonBg"/>
                                    </td>
                                </tr>
                                 --%>

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
                                       <!-- <input type="button" class="buttonBg"  align="right"  value="Add" onclick="addQuestionnaire();" /> -->
                                   <%--     <s:submit name="search" value="Search" cssClass="buttonBg"/> --%>
                                    </td>
                                </tr>
                                <s:hidden name="tableName" id="tableName" value="tblSurveyInfoDetails"/>
                                <s:if test="#session.surveyFormReviewList != null"> 
                                    <%java.util.List mainListt = (java.util.List) session.getAttribute("surveyFormReviewList");
                                                                        if (mainListt.size() > 0) {


                                                                    %>
                                                                 <tr>
                                                                       
                                                                        <td></td>
                                                                         <td></td>
                                                                        <td align="right">
                                                                            <br>
                                                                            <input align="right" type="button" name="generateExcel" id="generateExcel" class="buttonBg" value="Generate Excel"  onclick="return getEmployeeServeyExcelSheet();"/></td>
                                                                          <% int count=0;
        java.util.List questionaryListt = (java.util.List) session.getAttribute("surveyFormQuestionnaireList");

for (int qes = 0; qes < questionaryListt.size(); qes++) {
    
                        Map firstMap = (Map) questionaryListt.get(qes);
                        if (firstMap.get("OptionType").toString().equals("Attachment")) {
                            count+=1;
                                                    }
                                               }
                                                                        
                                
                        if(count>0){
                     %>
                                                                        <td align="left"><br><input align="right" type="button" name="downloadAttachments" id="downloadAttachments" class="buttonBg" value="Download Attachments"  onclick="getEmployeeServeyAttachments();"/></td>
                                                                   
                                                                    <%   } %>

           
            
                                                                    </tr>
                                                                    <% } %>

                    <tr>
                        <td colspan="4"><br>
                          <table id="results" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center">
                               
                              <%java.util.List mainList = (java.util.List) session.getAttribute("surveyFormReviewList");
                              
                                if(mainList.size()>0){


%>
                              
                             <tr class="gridHeader">
                                                       <th>Details</th> 
                                                       <th>Name</th>
                                                       <th>Email</th>
                                                       <th>Phone</th>
                                                       <th>CreatedDate</th>
                                                       <%
                            if(request.getAttribute("surveyType").toString().equals("I")){%>
                                                       <th>EmpNo</th>
                                                        <%}%>
                                                      <%-- <%
                                                     
                                                       java.util.List questionaryList = (java.util.List) session.getAttribute("surveyFormQuestionnaireList");
                                                       for(int qes=0;qes<questionaryList.size();qes++){
                                                             Map firstMap = (Map)questionaryList.get(qes);
                                                     
                                                       %>                       
                                                       <th><%
                                                       out.println(firstMap.get("Query"));
                                                       %></th>
                                                      <% }
                                
                                                        %> --%>
                                                       
                                                      <%-- <th>Attachment</th> --%>
                                                        
                                                    </tr>
                          <%
             
             for (int i = 0; i < mainList.size(); i++) {
                 %>
                 <tr CLASS="gridRowEven">
                     
                            <%
                 
                            java.util.Map subList = (java.util.Map)mainList.get(i);
               
                          //System.out.println("subList-->"+subList);
                           
                           %>
                           <%-- <td> <a style="color:#C00067;" href="getSurveyFormInfoDetails.action?surveyInfoId=<%=subList.get("sinfo_id")%>&surveyId=<%=request.getAttribute("surveyId")%>"> --%>
                           <td> <a style="color:#C00067;" href="javascript:showServeyReviewDetails('<%=subList.get("sinfo_id")%>','<%=request.getAttribute("surveyId")%>')" >
                         <img src="../../includes/images/DBGrid/Edit.gif"/>
                        </a> </td>
                          <td class="title">
                           <%
                           
                          out.println(subList.get("Name"));
                         %>
                     </td>
                       <td class="title">
                           <%
                           
                          out.println(subList.get("Email").toString());
                         %>
                     </td><td class="title">
                           <%
                           
                          out.println(subList.get("Phone").toString());
                         %>
                     </td><td class="title">
                           <%
                           
                          out.println(subList.get("CreatedDate").toString());
                         %>
                     </td>
                        <%
                            if(request.getAttribute("surveyType").toString().equals("I")){%>
                     <td class="title">
                          <%
                          out.println(subList.get("EmpNo").toString());
                                                   %>
                         
                     </td>
                       <% }%>

                          <%-- <%
                          
                           for(int qes=0;qes<questionaryList.size();qes++){
                                                             Map firstMap = (Map)questionaryList.get(qes);
                          %><td class="title">
                              <%                         out.println(subList.get(firstMap.get("Query")));
                            %>
                          </td>
                                            
                     
                   
                     
                     <%}%> --%>
                        
                       <%--   <td class="title">
                             <%
                             
                             File file= new File(subList.get("AttachmentPath").toString());
                             
                             
                             
                        if(file.exists()){%>
                           <a style="color:#C00067;" href="javascript:doSurveyAttachmentDownload('<%=subList.get("sinfo_id")%>');">
                             <img src="../../includes/images/download_11x10.jpg"/>
                        </a>
                    
                             <% }%> </td> --%>
                         
                         
                       
                     
                   </tr>
                    <% 
                                                             }         
             
                          }else {
             %>
                     <tr><td>
                 <%
                // String contextPath = request.getContextPath();
           // out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/><b> No Records Found to Display!</b>");
                 
                  out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/> <font color='white'><b>No records found!</b></font>");
           // }

            %>
                     </td>
           </tr>
           <%}%>
               
                        </table>
                        </td>
                    </tr>
                    
                    
                    
                     <%
                                         
             if(mainList.size()!=0){
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
                    
                 
           
                                               
                                                                                               
 <script type="text/JavaScript">
                                             var cal1 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateFrom']);
				                 cal1.year_scroll = true;
				                 cal1.time_comp = false;

                                             var cal2 = new CalendarTime(document.forms['frmDBGrid'].elements['createdDateTo']);
				                 cal2.year_scroll = true;
				                 cal2.time_comp = false;

						
                                        </script>                                                           
                                        
                          
       <script type="text/javascript">
        var pager = new ReviewPager('results', 10); 
        pager.init(); 
        pager.showPageNav('pager', 'pageNavPosition'); 
        pager.showPage(1);
    	</script>      
            
                                                </table>
                                             
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
                     <script>
       function getEmployeeServeyExcelSheet(){
           
          var tableName=document.getElementById('tableName').value;
          
                window.location="../websiteDataDownload.action?tableName="+tableName+"&surveyTitle=<s:property value="%{surveyTitle}"/>";
           
        }
        // <img src='../../includes/images/dtp/cal_minus.gif' alt="nag"width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg" onmouseover="Tip('Advanced Search')" onmouseout="UnTip()"/>
        function hideSearch(){
    
	//if(document.getElementById("searchdiv").style.display == 'none') {
        if(document.getElementById("searchdivTr1").style.display == 'none') {
           // alert("In if");
            showRow('SearchByTr');
            showRow('SearchByTr2');
             showRow('searchdivTr1');
             showRow('searchdivTr2');
            //$("#fsCollImgSpan").empty();
		//document.getElementById("searchdivTr1").style.display="block";
             //   document.getElementById("searchdivTr2").style.display="block";
                // $('#fsCollImgSpan').append("<img src='../../includes/images/dtp/cal_plus.gif' alt='nag'width='13' height='9' border='0' onclick='javascript:hideSearch()' id='fsCollImg' onmouseover='Tip('Hide Advanced Search')' onmouseout='UnTip()'/>");
		document.getElementById("fsCollImg").src = "../../includes/images/dtp/cal_plus.gif";
               // document.getElementById('fsCollImg').onmouseover= "Tip('Hide Advanced Search')";
                //document.getElementById('fsCollImg').onmouseout= "UnTip()";
	}else{
          //  alert("In else");
            hideRow('SearchByTr');
             hideRow('SearchByTr2');
            document.getElementById("searchQuestionId").value = "";
            document.getElementById("searchLabelId").innerHTML = "";
            document.getElementById("searchOptionId").innerHTML = "";
            
             document.getElementById("searchQuestionId2").value = "";
            document.getElementById("searchLabelId2").innerHTML = "";
            document.getElementById("searchOptionId2").innerHTML = "";
            

//document.getElementById("searchQLabelDiv").style.display="none";
//document.getElementById("searchQOptionDiv").style.display="none";
		//document.getElementById("searchdivTr1").style.display="none";
               // document.getElementById("searchdivTr2").style.display="none";
                 hideRow('searchdivTr1');
             hideRow('searchdivTr2');
               // $("#fsCollImgSpan").empty();
                // $('#fsCollImgSpan').append("<img src='../../includes/images/dtp/cal_minus.gif' alt='nag'width='13' height='9' border='0' onclick='javascript:hideSearch()' id='fsCollImg' onmouseover='Tip('Show Advanced Search')' onmouseout='UnTip()'/>");
		document.getElementById("fsCollImg").src = "../../includes/images/dtp/cal_minus.gif";
              //   document.getElementById('fsCollImg').onmouseover= Tip('Show Advanced Search');
               // document.getElementById('fsCollImg').onmouseout= UnTip();
	}
}

function showonlyGird(){
	document.getElementById("searchdiv").style.display="none";
	document.getElementById("fsCollImg").src = "../../includes/images/dtp/cal_minus.gif";
}


function checkAdvSearch() {
    var searchQId = document.getElementById("searchQuestionId").value;
    var searchQuestion = document.getElementById("searchQuestionValue").value;
     var searchQId2 = document.getElementById("searchQuestionId2").value;
    var searchQuestion2 = document.getElementById("searchQuestionValue2").value;
   // alert(searchQId+"--"+searchQuestion+" "+searchQId2+" "+searchQuestion2);
    
    if(searchQId!='' || searchQId2!=''){
         showRow('SearchByTr');
        showRow('SearchByTr2');
        //SearchByTr
        //document.getElementById("searchdiv").style.display="block";
        //document.getElementById("searchdivTr1").style.display="block";
       // document.getElementById("searchdivTr2").style.display="block";
         showRow('searchdivTr1');
             showRow('searchdivTr2');
		document.getElementById("fsCollImg").src = "../../includes/images/dtp/cal_plus.gif";
    }
    if(searchQId!=''){
       
              //  document.getElementById('fsCollImg').onmouseover= Tip('Hide Advanced Search');
               // document.getElementById('fsCollImg').onmouseout= UnTip();
                //$("#fsCollImgSpan").empty();
               // $('#fsCollImgSpan').append("<img src='../../includes/images/dtp/cal_plus.gif' alt='nag'width='13' height='9' border='0' onclick='javascript:hideSearch()' id='fsCollImg' onmouseover='Tip('Hide Advanced Search')' onmouseout='UnTip()'/>");
                getSearchQuestionInfo(document.getElementById("searchQuestionId"),searchQuestion);
    }
    if(searchQId2!=''){
      
              //  document.getElementById('fsCollImg').onmouseover= Tip('Hide Advanced Search');
               // document.getElementById('fsCollImg').onmouseout= UnTip();
                //$("#fsCollImgSpan").empty();
               // $('#fsCollImgSpan').append("<img src='../../includes/images/dtp/cal_plus.gif' alt='nag'width='13' height='9' border='0' onclick='javascript:hideSearch()' id='fsCollImg' onmouseover='Tip('Hide Advanced Search')' onmouseout='UnTip()'/>");
                getSearchQuestionInfo2(document.getElementById("searchQuestionId2"),searchQuestion2);
    }
}
function getEmployeeServeyAttachments(){
             var attachmentAvailable = document.getElementById("attachmentAvailable").value;
             if(parseInt(attachmentAvailable,10)>0) {
                window.location="../websiteDataDownload.action?tableName=downloadSurveyAttachments&surveyTitle=<s:property value="%{surveyTitle}"/>";
                }else {
                    alert("Attachments are not available!");
                }
           
            }
   </script>

                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>
                    
                </td>
            </tr>

            <!--//END FOOTER : Record for Footer Background and Content-->
            
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
        
        
        
        
        
    </body>
</html>



