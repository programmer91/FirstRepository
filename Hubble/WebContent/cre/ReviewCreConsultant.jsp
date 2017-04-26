<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.mss.mirage.util.DataSourceDataProvider"%>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<html>
<head>
    <title>Hubble Organization Portal :: Updating Employee Details</title>
    <sx:head cache="true"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/cre/CreAjax.js"/>"></script>
    <s:include value="/includes/template/headerScript.html"/>
    
     <script type="text/JavaScript">
            
            //For Adding & Editing Employee Status
            function setTempVar1() {
                document.techReviewForm.tempVar.value = 1;
            }
            function setTempVar2() {
                document.techReviewForm.tempVar.value = 2;
            }
           
            function checkManadatoryFields() {
            var techComments = document.getElementById('techLeadComments').value;
            if(techComments == null || techComments == "") {
            alert("please Enter Comments");
            return false;
            }else if (techComments.length >500) {
             alert("Comments length must be less than 500 Chanracters!");
            return false;
            }
            else {
            return true;
            }
            }
            
               function setTempHrVar1() {
                document.hrReviewForm.tempHrVar.value = 1;
            }
            function setTempHrVar2() {
                document.hrReviewForm.tempHrVar.value = 2;
            }
              function checkHrFields() {
            var hrComments = document.getElementById('hrComments').value;
            if(hrComments == null || hrComments == "") {
            alert("please Enter Comments");
            return false;
            }else if (hrComments.length >1000) {
             alert("Comments length must be less than 1000 Chanracters!");
            return false;
            }
            
            else {
            return true;
            }
            }
            
                function setTempVpVar1() {
                document.vpReviewForm.tempVpVar.value = 1;
            }
            function setTempVpVar2() {
                document.vpReviewForm.tempVpVar.value = 2;
            }
            
              function checkVpFields() {
            var vpComments = document.getElementById('vpComments').value;
            if(vpComments == null || vpComments == "") {
            alert("please Enter Comments");
            return false;
            }else if (vpComments.length >500) {
             alert("Comments length must be less than 500 Chanracters!");
            return false;
            }
            
            else {
            return true;
            }
            }
            
            
            /*Onboard Validations
             * Date : 08/15/2014
             * Author : Santosh Kola
             */
              function setTempOnboardVar1() {
                document.onBoardReviewForm.tempOnBoardVar.value = 1;
            }
            function setTempOnBoardVar2() {
                document.onBoardReviewForm.tempOnBoardVar.value = 2;
            }
            
              function checkOnBoardFields() {
            var onboardComments = document.getElementById('onboardComments').value;
            if(onboardComments == null || onboardComments == "") {
            alert("please Enter Comments");
            return false;
            }else if (onboardComments.length >1000) {
             alert("Comments length must be less than 1000 Chanracters!");
            return false;
            }
            
            else {
            return true;
            }
            }
			
            
    </script>
    
    
</head>
<body  class="bodyGeneral" oncontextmenu="return false;" >

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
                   
            <%  
           Map rolesMap =  (Map)session.getAttribute(ApplicationConstants.SESSION_MY_ROLES);
            
            %>
            <!--//START DATA COLUMN : Coloumn for Screen Content-->
            <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px 5px 5px;">
                <ul id="accountTabs" class="shadetabs" >
                    <li ><a href="#" class="selected" rel="examReview"  >Exam Review</a></li>
                    <li ><a href="#"  rel="techReview"  >Technical Review</a></li>
                    <%if(rolesMap.containsKey("7")) {%>
                    <li ><a href="#" rel="hrReview">Hr Review</a></li>
                    <%} 
                    if(rolesMap.containsKey("1")) {%>
                    <li ><a href="#" rel="vpReview">Vice Precident Review</a></li>
                     <%} if(rolesMap.containsKey("7")) {%>
                     <li ><a href="#" rel="onboardReview">OnBoard Review</a></li>
                     <%}%>
                </ul>
                <!--//START TABBED PANNEL : -->
                                
                <!--//START TABBED PANNEL : -->
                <%--    <sx:tabbedpanel id="employeeUpdatePannel" cssStyle="width:850px; height:510px;padding: 5px 5px 5px 5px;" doLayout="true" useSelectedTabCookie="true">--%>
                <div  style="border:1px solid gray; width:850px;height: 480px; overflow:auto; margin-bottom: 1em;">
                <!--//START TAB : -->
                <div id="examReview" class="tabcontent"  >
                
                
           <table cellpadding="1" cellspacing="1" border="0" width="100%">
                
                <tr>
                    <td class="headerText" colspan="6">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tr align="right">
                        <td valign="middle">
                       <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)"/>
                        </td>
                         </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                   <td class="fieldLabel" width="200px" align="right">Consultant Name:</td>
                    <td><font color="green" style="font-family: lucida-sans;font-size: 12px;"><s:property value="%{consultantName}"/></font></td>
                </tr>
                
                <tr>
                    <tr>
                        <td colspan="6">
                            <table cellpadding="2" cellspacing="1" width="98%" class="gridTable">
                                <tr class="gridHeader">
                                    <td width="4%" class="gridHeader" >ConsultantId</td>
                                 <%--   <td width="6%" class="gridHeader" >Marks</td>
                                    <td width="8%" class="gridHeader">Total Questions</td>
                                    <td width="8%" class="gridHeader">Attempted Questions</td> --%>
                                    <td width="8%" class="gridHeader">DateSubmitted</td>
                                    <td width="8%" class="gridHeader">ExamStatus</td>
                                   <%-- <td width="8%" class="gridHeader">Delete</td>--%>
                                </tr>
                                <s:iterator value="#request.currentExamReviewCollection">
                                    <tr class="gridRowEven">
                                        
                                          <%
                                        String examKeyId = request.getAttribute("examKeyId").toString();
                                        %>
                                      <td class="gridColumn" align="left" onclick=""><a href="javascript:showDetailResult('<%=examKeyId%>');" ><s:property value="creLoginId"/></a></td>                
                                      <%--  <td class="gridColumn" align="left"><s:property value="marks"/></td>
                                        <td class="gridColumn" align="left"><s:property value="totalQuestions"/></td>
                                        <td class="gridColumn" align="left"><s:property value="attemptedQuestions"/></td> --%>
                                        <td class="gridColumn" align="left"><s:property value="dateSubmitted"/></td>
                                       <td class="gridColumn" align="left"><s:property value="examStatus"/></td>
                                      <%--  <td class="gridColumn" align="left"><a href="javascript:doDeleteTechLeadReview(<s:property value="creId"/>,<s:property value="techReviewId"/>);" ><img src="../includes/images/DBGrid/Delete.png"></a></td>--%>
                                    </tr>
                                </s:iterator>
                                <tr class="gridPager">
                                    <td colspan="8">&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                
                
            </table> 
     
        
                 
   
    <%-- </sx:div > --%>
    </div>
    

                <%-- <sx:div id="personalDetailsTab" label="Employee Details" cssStyle="overflow:auto;" > --%>
                <div id="techReview" class="tabcontent"  >
                <s:form name="techReviewForm" id="techReviewForm" action="creTechReviewUpdate" method="post" theme="simple" onsubmit="return checkManadatoryFields();">
                 <s:hidden name="id" value="%{id}"/>
                 
                 <s:hidden name="tempVar" id="tempVar" value="%{tempVar}"/>
                <s:hidden name="navId" id="navId" value="%{navId}"/>
           <table cellpadding="1" cellspacing="1" border="0" width="100%">
                
                <tr>
                    <td class="headerText" colspan="6">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tr align="right">
                                <td align="left" width="670px">
                                    <% if(request.getAttribute("resultMessage") != null){
                                                    out.println(request.getAttribute("resultMessage"));
                                    }%>
                                </td>
                                
                               <%--  <s:if test="#request.resultMessage!=null">
                                   
                                                <td valign="middle">
                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-2)">  
                                                </td>
                                            </s:if>
                                            <s:else>
                                                <td valign="middle">
                                            
                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                                </td> 
                                            </s:else> --%>
                                            <td valign="middle">
                                            <a href="<s:url value="crmBackToList.action"/>" style="align:center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="bottom"></a></td>
                              
                                <td valign="top">
                                    <s:if test="%{navId == 1}">
                                        <s:submit name="submit" value="Update" cssClass="buttonBg" onclick="setTempVar1()"/>
                                         <s:hidden name="techReviewId" value="%{creReviewVTO.techReviewId}"/>
                                    </s:if>
                                  
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                   <td class="fieldLabel" width="200px" align="right">Consultant Name:</td>
                    <td><font color="green" style="font-family: lucida-sans;font-size: 12px;"><s:property value="%{consultantName}"/></font></td>
                </tr>
                <tr>
               <%--     <td class="fieldLabel" width="200px" align="right">Consultant Name:</td>                           
                    
                    <td><s:textfield name="consultantName"  id="consultantName" cssClass="inputTextBlue" value="" readonly="true"/></td>                                                
                    
                    <td class="fieldLabel" width="200px" align="right">Consultant Id:</td>
                    
                    <td><s:textfield name="consultantId"  id="consultantId" cssClass="inputTextBlue" value="" readonly="true"/></td> --%>
                    
                   <td class="fieldLabel" width="200px" align="right">Status :</td>
                     <td><s:select 
                            name="techReviewStatus" 
                        list="{'Selected', 'Hold' , 'Rejected'}" id="techReviewStatus" cssClass="inputSelect" value="%{creReviewVTO.techReviewStatus}" /></td> 
                </tr>
               
               
                
                <tr>
                        <td class="fieldLabel" width="200px" align="right">Comments :<font color="red" >*</font></td>                           
                        <td colspan="4">
                            <s:textarea name="techLeadComments" id="techLeadComments" value="%{creReviewVTO.techLeadComments}"   cssClass="inputTextareaDesc" />
                        </td>
                       <td>
                        <!--<input type="button" name="addNew" id="addNew" class="buttonBg" value="Add Status" onclick="addStatus();">-->
                        <s:if test="%{navId == 0}">
                            <s:submit id="addStatus" name="addStatus" value="Add Status" cssClass="buttonBg" onclick="setTempVar2()"/>
                         </s:if >
                        <!--<div id="loadMessage" style="display: none" align="center" class="error" >
                            <b>Loading.... Please Wait....</b>
                        </div>-->
                    </td>
                </tr>
                <tr>
                    <tr>
                        <td colspan="6">
                            <table cellpadding="2" cellspacing="1" width="98%" class="gridTable">
                                <tr class="gridHeader">
                                    <td width="4%" class="gridHeader" >Edit</td>
                                    <td width="6%" class="gridHeader" >Status</td>
                                    <td width="8%" class="gridHeader">CreatedDate</td>
                                    <td width="8%" class="gridHeader">CreatedBy</td>
                                    <td width="8%" class="gridHeader">Comments</td>
                                   <%-- <td width="8%" class="gridHeader">Delete</td>--%>
                                </tr>
                                <s:iterator value="#request.currentTechReviewCollection">
                                    <tr class="gridRowEven">
                                        <td class="gridColumn" align="left"><a href="getTechLeadReview.action?id=<s:property value="creId"/>&techReviewId=<s:property value="techReviewId"/>&navId=1"><img src="../includes/images/DBGrid/newEdit_17x18.png"></a></td>
                                        <td class="gridColumn" align="left"><s:property value="techReviewStatus"/></td>
                                        <td class="gridColumn" align="left"><s:property value="techCreatedDate"/></td>
                                        <td class="gridColumn" align="left"><s:property value="techLead"/></td>
                                        <td class="gridColumn" align="left"><a href="javascript:getTechLeadComments('<s:property value="techReviewId"/>');" >ClickToView</a></td>
                                       
                                      <%--  <td class="gridColumn" align="left"><a href="javascript:doDeleteTechLeadReview(<s:property value="creId"/>,<s:property value="techReviewId"/>);" ><img src="../includes/images/DBGrid/Delete.png"></a></td>--%>
                                    </tr>
                                </s:iterator>
                                <tr class="gridPager">
                                    <td colspan="8">&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </tr>
                
            </table> 
     
        
                 </s:form>
   
    <%-- </sx:div > --%>
    </div>
    
    <!--//END TAB : -->
    
    <%--   <sx:div id="empCurrentState" label="Current Status"  > --%>
    <div id="hrReview" class="tabcontent"  >
        <s:form action="creHrReviewUpdate" theme="simple" method="post" id="hrReviewForm" name="hrReviewForm" onsubmit="return checkHrFields();">
       <s:hidden name="id" value="%{id}"/>
                 
                 <s:hidden name="tempHrVar" id="tempHrVar" value="%{tempHrVar}"/>
                <s:hidden name="hrNavId" id="hrNavId" value="%{hrNavId}"/>
           <table cellpadding="1" cellspacing="1" border="0" width="100%">
                
                <tr>
                    <td class="headerText" colspan="6">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tr align="right">
                                <td align="left" width="670px">
                                    <% if(request.getAttribute("hrResultMessage") != null){
                                                    out.println(request.getAttribute("hrResultMessage"));
                                    }%>
                                </td>
                                 <td valign="middle">
                                            <a href="<s:url value="crmBackToList.action"/>" style="align:center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="bottom"></a></td>
                                
                              <%--   <s:if test="#request.hrResultMessage!=null">
                                                <td valign="middle">
                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-2)">  
                                                </td>
                                            </s:if>
                                            <s:else>
                                               
                                                <td valign="middle">
                                                   
                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                                </td>
                                            </s:else> --%>
                                            
                                            
                              
                                <td valign="top">
                                    <s:if test="%{hrNavId == 1}">
                                        <s:submit name="submit" value="Update" cssClass="buttonBg" onclick="setTempHrVar1()"/>
                                         <s:hidden name="hrReviewId" value="%{creReviewVTO.hrReviewId}"/>
                                    </s:if>
                                  
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                 <tr>
                   <td class="fieldLabel" width="200px" align="right">Consultant Name:</td>
                    <td><font color="green" style="font-family: lucida-sans;font-size: 12px;"><s:property value="%{consultantName}"/></font></td>
                </tr>
                <tr>
              
                    
                   <td class="fieldLabel" width="200px" align="right">Status :</td>
                     <td><s:select 
                            name="hrReviewStatus" 
                        list="{'Selected', 'Hold' , 'Rejected'}" id="hrReviewStatus" cssClass="inputSelect" value="%{creReviewVTO.hrReviewStatus}" /></td> 
                </tr>
               
               
                
                <tr>
                        <td class="fieldLabel" width="200px" align="right">Comments :<font color="red" >*</font></td>                           
                        <td colspan="4">
                            <s:textarea name="hrComments" id="hrComments" value="%{creReviewVTO.hrComments}"   cssClass="inputTextareaDesc" />
                        </td>
                       <td>
                        <!--<input type="button" name="addNew" id="addNew" class="buttonBg" value="Add Status" onclick="addStatus();">-->
                      
                       
                        <s:if test="%{hrNavId == 0}">
                            <s:submit id="addHrStatus" name="addHrStatus" value="Add Status" cssClass="buttonBg" onclick="setTempHrVar2()"/>
                         </s:if >
                        <!--<div id="loadMessage" style="display: none" align="center" class="error" >
                            <b>Loading.... Please Wait....</b>
                        </div>-->
                    </td>
                </tr>
                <tr>
                    <tr>
                        <td colspan="6">
                            <table cellpadding="2" cellspacing="1" width="98%" class="gridTable">
                                <tr class="gridHeader">
                                    <td width="4%" class="gridHeader" >Edit</td>
                                    <td width="6%" class="gridHeader" >Status</td>
                                    <td width="8%" class="gridHeader">CreatedDate</td>
                                    <td width="8%" class="gridHeader">CreatedBy</td>
                                    <td width="8%" class="gridHeader">Comments</td>
                                  <%--  <td width="8%" class="gridHeader">Delete</td>  --%>
                                </tr>
                                <s:iterator value="#request.currentHrReviewCollection">
                                    <tr class="gridRowEven">
                                        <td class="gridColumn" align="left"><a href="getHrReview.action?id=<s:property value="creId"/>&hrReviewId=<s:property value="hrReviewId"/>&hrNavId=1"><img src="../includes/images/DBGrid/newEdit_17x18.png"></a></td>
                                        <td class="gridColumn" align="left"><s:property value="hrReviewStatus"/></td>
                                        <td class="gridColumn" align="left"><s:property value="hrCreatedDate"/></td>
                                        <td class="gridColumn" align="left"><s:property value="hrLoginId"/></td>
                                        <td class="gridColumn" align="left"><a href="javascript:getHrComments('<s:property value="hrReviewId"/>');" >ClickToView</a></td>
                                       
                                        <%-- <td class="gridColumn" align="left"><a href="javascript:doDeleteHrReview(<s:property value="creId"/>,<s:property value="hrReviewId"/>);" ><img src="../includes/images/DBGrid/Delete.png"></a></td>--%>
                                    </tr>
                                </s:iterator>
                                <tr class="gridPager">
                                    <td colspan="8">&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </tr>
                
            </table> 
     
        </s:form>
       
        
        <%-- </sx:div> --%>
    </div>
    
    <!-- Other Details START -->
    <%-- <sx:div id="otherDetailsTab" label="Other Details"> --%>
    <div id="vpReview" class="tabcontent"  >   
       <s:form action="creVpReviewUpdate" theme="simple" method="post" id="vpReviewForm" name="vpReviewForm" onsubmit="return checkVpFields();">
       <s:hidden name="id" value="%{id}"/>
                 
                 <s:hidden name="tempVpVar" id="tempVpVar" value="%{tempVpVar}"/>
                <s:hidden name="hrNavId" id="hrNavId" value="%{hrNavId}"/>
           <table cellpadding="1" cellspacing="1" border="0" width="100%">
                
                <tr>
                    <td class="headerText" colspan="6">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tr align="right">
                                <td align="left" width="670px">
                                    <% if(request.getAttribute("vpResultMessage") != null){
                                                    out.println(request.getAttribute("vpResultMessage"));
                                    }%>
                                </td>
                                
                         <%--        <s:if test="#request.vpResultMessage!=null">
                                                <td valign="middle">
                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-2)">  
                                                </td>
                                            </s:if>
                                            <s:else>
                                                <td valign="middle">
                                                    
                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                                </td>
                                            </s:else> --%>
                          <td valign="middle">
                                            <a href="<s:url value="crmBackToList.action"/>" style="align:center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="bottom"></a></td>
                                            
                                            
                              
                                <td valign="top">
                                    <s:if test="%{vpNavId == 1}">
                                        <s:submit name="submit" value="Update" cssClass="buttonBg" onclick="setTempVpVar1()"/>
                                         <s:hidden name="vpReviewId" value="%{creReviewVTO.vpReviewId}"/>
                                    </s:if>
                                  
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                 <tr>
                   <td class="fieldLabel" width="200px" align="right">Consultant Name:</td>
                    <td><font color="green" style="font-family: lucida-sans;font-size: 12px;"><s:property value="%{consultantName}"/></font></td>
                </tr>
                <tr>
              
                    
                   <td class="fieldLabel" width="200px" align="right">Status :</td>
                     <td><s:select 
                            name="vpReviewStatus" 
                        list="{'Selected', 'Hold' , 'Rejected'}" id="vpReviewStatus" cssClass="inputSelect" value="%{creReviewVTO.vpReviewStatus}" /></td> 
                </tr>
               
               
                
                <tr>
                        <td class="fieldLabel" width="200px" align="right">Comments :<font color="red" >*</font></td>                           
                        <td colspan="4">
                            <s:textarea name="vpComments" id="vpComments" value="%{creReviewVTO.vpComments}"   cssClass="inputTextareaDesc" />
                        </td>
                       <td>
                        <!--<input type="button" name="addNew" id="addNew" class="buttonBg" value="Add Status" onclick="addStatus();">-->
                      
                       
                        <s:if test="%{vpNavId == 0}">
                            <s:submit id="addVpStatus" name="addVpStatus" value="Add Status" cssClass="buttonBg" onclick="setTempVpVar2()"/>
                         </s:if >
                        <!--<div id="loadMessage" style="display: none" align="center" class="error" >
                            <b>Loading.... Please Wait....</b>
                        </div>-->
                    </td>
                </tr>
                <tr>
                    <tr>
                        <td colspan="6">
                            <table cellpadding="2" cellspacing="1" width="98%" class="gridTable">
                                <tr class="gridHeader">
                                    <td width="4%" class="gridHeader" >Edit</td>
                                    <td width="6%" class="gridHeader" >Status</td>
                                    <td width="8%" class="gridHeader">CreatedDate</td>
                                    <td width="8%" class="gridHeader">CreatedBy</td>
                                    <td width="8%" class="gridHeader">Comments</td>
                                   <%-- <td width="8%" class="gridHeader">Delete</td>--%>
                                </tr>
                                <s:iterator value="#request.currentVpReviewCollection">
                                    <tr class="gridRowEven">
                                        <td class="gridColumn" align="left"><a href="getVpReview.action?id=<s:property value="creId"/>&vpReviewId=<s:property value="vpReviewId"/>&vpNavId=1"><img src="../includes/images/DBGrid/newEdit_17x18.png"></a></td>
                                        <td class="gridColumn" align="left"><s:property value="vpReviewStatus"/></td>
                                        <td class="gridColumn" align="left"><s:property value="vpCreatedDate"/></td>
                                        <td class="gridColumn" align="left"><s:property value="vpLoginId"/></td>
                                        <td class="gridColumn" align="left"><a href="javascript:getVpComments('<s:property value="vpReviewId"/>');" >ClickToView</a></td>
                                       
                                       <%--  <td class="gridColumn" align="left"><a href="javascript:doDeleteVpReview(<s:property value="creId"/>,<s:property value="vpReviewId"/>);" ><img src="../includes/images/DBGrid/Delete.png"></a></td>--%>
                                    </tr>
                                </s:iterator>
                                <tr class="gridPager">
                                    <td colspan="8">&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </tr>
                
            </table> 
     
        </s:form>
        <%--   </sx:div> --%>
    </div>
    
    <!-- Onboard review start -->
     <div id="onboardReview" class="tabcontent"  >   
       <s:form action="creOnboardReviewUpdate" theme="simple" method="post" id="onBoardReviewForm" name="onBoardReviewForm" onsubmit="return checkOnBoardFields();">
       <s:hidden name="id" value="%{id}"/>
                 
                 <s:hidden name="tempOnBoardVar" id="tempOnBoardVar" value="%{tempOnBoardVar}"/>
                <s:hidden name="onBoardNavId" id="onBoardNavId" value="%{onBoardNavId}"/>
           <table cellpadding="1" cellspacing="1" border="0" width="100%">
                
                <tr>
                    <td class="headerText" colspan="6">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tr align="right">
                                <td align="left" width="670px">
                                    <% if(request.getAttribute("onboardResultMessage") != null){
                                                    out.println(request.getAttribute("onboardResultMessage"));
                                    }%>
                                </td>
                                
                         <%--        <s:if test="#request.vpResultMessage!=null">
                                                <td valign="middle">
                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-2)">  
                                                </td>
                                            </s:if>
                                            <s:else>
                                                <td valign="middle">
                                                    
                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                                </td>
                                            </s:else> --%>
                          <td valign="middle">
                                            <a href="<s:url value="crmBackToList.action"/>" style="align:center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="bottom"></a></td>
                                            
                                            
                              
                                <td valign="top">
                                    <s:if test="%{onBoardNavId == 1}">
                                        <s:submit name="submit" value="Update" cssClass="buttonBg" onclick="setTempOnboardVar1()"/>
                                         <s:hidden name="onboardReviewId" value="%{creReviewVTO.onboardReviewId}"/>
                                    </s:if>
                                  
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                 <tr>
                   <td class="fieldLabel" width="200px" align="right">Consultant Name:</td>
                    <td><font color="green" style="font-family: lucida-sans;font-size: 12px;"><s:property value="%{consultantName}"/></font></td>
                </tr>
             <%--   <tr>
               <td class="fieldLabel" width="200px" align="right">Status :</td>
                     <td><s:select 
                            name="vpReviewStatus" 
                        list="{'Selected', 'Hold' , 'Rejected'}" id="vpReviewStatus" cssClass="inputSelect" value="%{creReviewVTO.vpReviewStatus}" /></td> 
                </tr> --%>
               
               
                
                <tr>
                        <td class="fieldLabel" width="200px" align="right">Comments :<font color="red" >*</font></td>                           
                        <td colspan="4">
                            <s:textarea name="onboardComments" id="onboardComments" value="%{creReviewVTO.onboardComments}"   cssClass="inputTextareaDesc" />
                        </td>
                       <td>
                        <!--<input type="button" name="addNew" id="addNew" class="buttonBg" value="Add Status" onclick="addStatus();">-->
                      
                       
                        <s:if test="%{onBoardNavId == 0}">
                            <s:submit id="addOnboardStatus" name="addOnboardStatus" value="Add Status" cssClass="buttonBg" onclick="setTempOnBoardVar2()"/>
                         </s:if >
                        <!--<div id="loadMessage" style="display: none" align="center" class="error" >
                            <b>Loading.... Please Wait....</b>
                        </div>-->
                    </td>
                </tr>
                <tr>
                    <tr>
                        <td colspan="6">
                            <table cellpadding="2" cellspacing="1" width="98%" class="gridTable">
                                <tr class="gridHeader">
                                    <td width="4%" class="gridHeader" >Edit</td>
                                  <%--  <td width="6%" class="gridHeader" >Status</td> --%>
                                    <td width="8%" class="gridHeader">CreatedDate</td>
                                    <td width="8%" class="gridHeader">CreatedBy</td>
                                    <td width="8%" class="gridHeader">Comments</td>
                                   <%-- <td width="8%" class="gridHeader">Delete</td>--%>
                                </tr>
                                <s:iterator value="#request.currentOnboardReviewCollection">
                                    <tr class="gridRowEven">
                                        <td class="gridColumn" align="left"><a href="getOnboardReview.action?id=<s:property value="creId"/>&onboardReviewId=<s:property value="onboardReviewId"/>&onBoardNavId=1"><img src="../includes/images/DBGrid/newEdit_17x18.png"></a></td>
                                      <%--  <td class="gridColumn" align="left"><s:property value="vpReviewStatus"/></td> --%>
                                        <td class="gridColumn" align="left"><s:property value="onboardCreatedDate"/></td>
                                        <td class="gridColumn" align="left"><s:property value="onboardLoginId"/></td>
                                        <td class="gridColumn" align="left"><a href="javascript:getOnboardComments('<s:property value="onboardReviewId"/>');" >ClickToView</a></td>
                                       
                                       <%--  <td class="gridColumn" align="left"><a href="javascript:doDeleteVpReview(<s:property value="creId"/>,<s:property value="vpReviewId"/>);" ><img src="../includes/images/DBGrid/Delete.png"></a></td>--%>
                                    </tr>
                                </s:iterator>
                                <tr class="gridPager">
                                    <td colspan="8">&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </tr>
                
            </table> 
     
        </s:form>
        <%--   </sx:div> --%>
    </div>
    <!-- Other Details END -->
    
    <!-- Onboard Review End -->
    <!-- Other Details END -->
    
    <%--  </sx:tabbedpanel> --%>
    </div>
    <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(true)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

    </script>
    <!--//END TABBED PANNEL : -->
    
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
    <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
</tr>
<!--//END FOOTER : Record for Footer Background and Content-->

</table>
<!--//END MAIN TABLE : Table for template Structure-->
</body>
</html>
