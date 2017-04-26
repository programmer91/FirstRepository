<%-- 
    Document   : ClientRequestForm
    Created on : May 24, 2016, 5:35:59 PM
    Author     : miracle
--%>


<%@page contentType="text/html;charset=UTF-8" errorPage="../../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mss.mirage.util.*"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ page import="java.sql.*,java.lang.*,java.util.*" %>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>

<html>
    <head>

        <title>Hubble Organization Portal :: Client Engagement Request</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/selectivity-full.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script> 
        <script type="text/javascript" src="<s:url value="/includes/javascripts/crm/ClientEngagementRequest.js?version=1.3"/>"></script>   
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

   <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="loadAddressRow();requestSelect();"> -->
   <body class="bodyGeneral" oncontextmenu="return false;">
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
                            <td width="850px" class="cellBorder" valign="top" style="padding-left:10px;padding-top:5px;">
                                <!--//START TABBED PANNEL : -->

                                <%--<s:property value="%{consultantName}"/>--%>
                                <ul id="surveyDetailsTabs" class="shadetabs" >
                                  <%--  <s:if test="%{currentAction=='doAddSurveyForm'}"> --%>
                                        <li ><a href="#" class="selected" rel="libraryAdd"> 
                                                <s:if test="%{currentAction=='doClientReqEngagementAdd'}">
                                                    Client Engagement Request Add
                                                </s:if><s:else>
                                                    Client Engagement Request Edit
                                                </s:else>
                                                
                                            
                                            </a></li> 

                                  <%--  </s:if>
                                    <s:else>
                                        <li ><a href="#" class="selected" rel="libraryAdd">Survey&nbsp;Form&nbsp;Edit </a></li>
                                    </s:else> --%>



                                </ul>

                                <%-- <sx:tabbedpanel id="resumeAttachmentDetails" cssStyle="width: 840px; height: 310px;padding-left:10px;padding-top:5px;" doLayout="true"> --%>

                                <!--//START TAB : -->
                                <%-- <sx:div id="resumeAttachmentTab"  label="Resume Attachment Details"  > --%>

                                <div  style="border:1px solid gray; width:830px;height: 500px; overflow:auto; margin-bottom: 1em;">



                                    <div id="libraryAdd" class="tabcontent"  >
                                         <div id="overlayEmailCampaign"></div>              
                        <div id="specialBoxEmailCampaign" >
                            <s:form theme="simple"  align="center" name="ProjResour" action="" method="post"   >
                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                    <tr>                               
                                    <td colspan="2" style="background-color: #288AD1" >
                                        <h3 style="color:darkblue;" align="left">
                                            <span id="headerLabel" align="center"></span>

                                            <td colspan="2" style="background-color: #288AD1" align="right">
                                                <a href="javascript:clientStatusStages('0');" >
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 
                                                </a>  
                                            </td>
                                        </h3>
                                    </td>
                                    </tr>

                                    <tr>
                                    <td class="fieldLabel" valign="top">Comments:-</td>
                                    <td colspan="3" valign="top"><s:textarea rows="2" cols="105" name="stageComments" value="%{stageComments}" cssClass="inputTextarea" id="stageComments"/>
                                    </td>
                                    </tr>




                                </table>
                            </s:form> 
                        </div>
                                        <s:form name="frmClientRequestForm" action="%{currentAction}" theme="simple" method="POST" enctype="multipart/form-data" onsubmit="return engagementFieldsValidate();">
                                            <table cellpadding="2" cellspacing="0" border="0" width="100%">                                                
                                                <tr align="right">
                                                    <s:hidden name="requestId" id="requestId" value="%{requestId}"/>
                                                          <s:hidden name="re" id="re" value="%{currentAction}" />
                                                    <td class="headerText" colspan="4">
                                                        <%
                                                            if (request.getAttribute("resultMessage") != null) {
                                                                out.println(request.getAttribute("resultMessage").toString());
                                                            }

                                                        %>
                                                         <a href="<s:url value="clientReqEngagementApprovalsSearch.action?backToFlag=Yes"/>" style="align:center;">
                                            <img alt="Back to List"
                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                 width="66px" 
                                                 height="19px"
                                             border="0" align="bottom"></a>
                                        &nbsp;&nbsp;
                                                    <s:if test="%{currentAction=='doClientReqEngagementAdd'}"> 
                                                            <s:submit cssClass="buttonBg" value="Save"/>
                                                    </s:if>
                                                       


                                                    </td>
                                                </tr>    
 <tr>
                                        <s:hidden name="stageNum" id="stageNum" value="%{stageNum}"/>
                                        <s:hidden name="requestStage" id="requestStage" value="%{requestStage}"/>
                                                        <s:hidden name="preRequestStage" id="preRequestStage" value="%{requestStage}"/>
                                                        <td class="fieldLabel">Stage:<FONT color="red"  ><em>*</em></FONT></td>
                                    <td colspan="3" >
                                        <div class="progress">
                                            <div class="circle" id="test">
                                                <span class="label" onclick="eventClick(1);" >1</span>
                                                <span class="title">Created</span>
                                            </div>
                                            <span class="bar"></span>
                                            <div class="circle">
                                                <span class="label" onclick="eventClick(2);">2</span>
                                                <span class="title">Submitted</span>
                                            </div>
                                            <span class="bar"></span>
                                            <div class="circle">
                                                <span class="label" onclick="eventClick(3);">3</span>
                                                <span class="title">Reviewed</span>
                                            </div>
                                            <span class="bar"></span>
                                            <div class="circle">
                                                <span class="label" onclick="eventClick(4);">4</span>
                                                <span class="title" id="finalStatus">Approved/Rejected</span>
                                            </div>

                                        </div>
                                    </td>
                                    </tr>
                                               
                                    <tr>
                                        <td class="fieldLabel">Request type:<FONT color="red"  ><em>*</em></FONT></td>
                                                      <s:if test="%{requestStage!='Approved' && requestStage!='Submitted'&& requestStage!='Reviewed'}"> 
                                                   
                                                    <td>
                                                            <s:select id="requestType" name="requestType" value="%{requestType}" list="{'PSCER','RFP'}" cssClass="inputSelect"  onchange="requestSelect(this);"/>
                                                    </td>
                                            </s:if><s:else>
                                                <td>
                                                       <font color="green" size="2px"><s:property value="%{requestType}"/></font>
                                                        <s:hidden name="requestType" id="requestType" value="%{requestType}"/>
                                                     </td>
                                            </s:else>
                                    </tr>
                                    <tr>
                                                    <td class="fieldLabel">Requestor&nbsp;Name:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td>
                                                            <%-- <s:select id="requestorId" name="requestorId" value="%{requestorId}" list="preSalesMap" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/> --%>
                                                            
                                                            <font color="green" size="2px"><s:property value="%{requestorName}"/></font>
                                                            
                                                            <s:hidden name="requestorId" id="requestorId" value="%{requestorId}"/>
                                                    </td>
                                               <td class="fieldLabel" >RVP Region/ RVP Name:<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td><s:textfield name="rvpName" id="rvpName" value="%{rvpName}" cssClass="inputTextBlue" onchange="surveyFormFieldsValidator(this);" readOnly="true"/></td>
                                                    
                                                </tr>
                                                 <tr>
                                                    <td class="fieldLabel" >Client Type:<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td>
                                                       
                                                        
                                                           <%-- <s:if test="%{clientType=='E'}"> --%>
                                                                <%if(request.getAttribute("clientType").toString().equals("E")){%>
                                                            <font color="green" size="2px">Not Existed</font>
                                                        <%-- </s:if><s:else> --%>
                                                        <%} else {%>
                                                             <font color="green" size="2px">Existed</font>
                                                             <%}%>
                                                       <%-- </s:else> --%>
                                                       
                                                        <s:hidden id="clientType" name="clientType" value="%{clientType}"/>
                                                      <%--  <s:select id="clientType" name="clientType" value="%{clientType}" list="#@java.util.LinkedHashMap@{'I':'Existed','E':'Not Existed'}" cssClass="inputSelect" onchange="checkClientType(this);"/> --%>
                                                    
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" >Client Name:<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td > <s:hidden name="customerName" id="customerName" value="%{customerName}"/>
                                                        <font color="green" size="2px"><s:property value="%{customerName}"/></font>
                                                       <%-- <s:textfield name="customerName" id="customerName" value="%{customerName}" cssClass="inputTextBlue"  theme="simple" onkeyup="fillCustomer();"/>
                                                                    <div id="validationMessage"></div> --%>
                                                                    <s:hidden name="consultantId" id="consultantId" value="%{consultantId}"/> </td>
                                                     <td class="fieldLabel">Opportunities:</td>
                                                    <td>
                                                        <%--    <s:select id="opportunityId" name="opportunityId" value="%{opportunityId}" list="opportunitiesMap" cssClass="inputSelect" headerKey="-1" headerValue="--Please Select--"/> --%>
                                                          <s:select id="opportunityId" name="opportunityId" value="%{opportunityId}" list="opportunitiesMap" cssClass="inputSelect" headerKey="-1" headerValue="--Please Select--" style="display:none"/>
                                                            <s:textfield name="opportunityName" id="opportunityName" value="%{opportunityName}" cssClass="inputTextBlue"  theme="simple" style="display:none"/>
                                                    </td>
                                            </tr>
                                            
                                             <tr>
                                                 <td class="fieldLabel">Company Revenue:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td align="left">
                                                          <s:textfield name="revenues" id="revenues" value="%{revenues}" cssClass="inputTextBlue" readOnly="true" theme="simple" onblur="return validatenumber(this);"/>
                                                           </td>
                                                 <td class="fieldLabel">Size:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td align="left">
                                                          <s:textfield name="noOfEmployees" id="noOfEmployees" value="%{noOfEmployees}" cssClass="inputTextBlue" readOnly="true" theme="simple" onblur="return validatenumber(this);"/>
                                                           </td>
                                            </tr>
                                            
                                            
                                            <tr>
                                                 <%--    <td class="fieldLabel">Stage:<FONT color="red"  ><em>*</em></FONT></td>
                                                   <td>
                                                        
                                                        <font color="green" size="2px"><s:property value="%{requestStage}"/></font>
                                                        <s:hidden name="requestStage" id="requestStage" value="%{requestStage}"/>
                                                        <s:hidden name="preRequestStage" id="preRequestStage" value="%{requestStage}"/>
                                                     <s:select id="requestStage" name="requestStage" value="%{requestStage}" list="{'Creation','Submitted','Reviewed','Approved','Rejected'}" cssClass="inputSelect"/>
                                                    </td> --%>
                                                 
                                                   
                                                     <td class="fieldLabel">Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td>
                                                            <s:select id="requestStatus" name="requestStatus" value="%{requestStatus}" list="{'Active','Closed'}" cssClass="inputSelect" />
                                                    </td>
                                                    
                                                    
                                                       <% if(request.getAttribute("bdmId") != null && !"0".equals(request.getAttribute("bdmId").toString().trim())) {%>
                                                
                                                 
                                                     <td class="fieldLabel">BDM :</td>
                                                    <td colspan="3" align="left">
                                                            <s:select list="bdmMap" headerKey="" headerValue="--Please Select--" name="bdmId" id="bdmId" value="%{bdmId}" cssClass="inputSelect"/> </td>
                                              <% } else { %>
                                            
                                              <s:hidden name="bdmId" id="bdmId" value="0"/>
                                         <% } %>
                                                    
                                                </tr>  
                                           
                                                  <tr>
                                                            <td class="fieldLabel">Practice Sales:&nbsp;<font color="red">*</font> </td>
                                                            <td > 
                                                                <s:select name="regionalMgrId" id="regionalMgrId" cssClass="inputTextBlue" list="practiceSalesMap" headerKey="-1" headerValue="--Please Select--" value="%{regionalMgrId}"/>
                                                            </td> 
                                                     </tr> 
                                            <tr id="meetingTypeTr">
                                                  <%--   <td class="fieldLabel">Meeting Status:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td>
                                                            <s:select id="meetingStatus" name="meetingStatus" value="%{meetingStatus}" list="#@java.util.LinkedHashMap@{'N':'Needs to be Arranged','P':'Previously Arranged'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--"/>
                                                    </td> --%>
                                                     <td class="fieldLabel">Meeting type:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td>
                                                            <s:select id="meetingType" name="meetingType" value="%{meetingType}" list="{'Client Call','Client F2F Meeting'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" onchange="getAddressRow(this);"/>
                                                    </td>
                                                     <td class="fieldLabel">Level of Engagement:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td>
                                                            <s:select id="levelEngagement" name="levelEngagement" value="%{levelEngagement}" list="{'First','Second','Other'}" cssClass="inputSelect" headerKey="" headerValue="--Please Select--" />
                                                    </td>
                                                </tr>   
                                                 <s:hidden name="title" id="title" value="%{#session.title}"/>
                                                     <s:hidden name="roleName" id="roleName" value="%{#session.roleName}"/>
                                              
                                                 
                                                <tr id="addressRow" style="display: none">
                                                     <td class="fieldLabel">State:<FONT color="red"  ><em>*</em></FONT></td>
                                                       <td>
            <s:select 
                list="statesList"   
                name="state" 
                id="state" 
                cssClass="inputSelect" 
                headerKey=""
                headerValue="--Please Select--"
                theme="simple"
                vlaue="%{state}"
            />
        </td>
                                                     <td class="fieldLabel">City:<FONT color="red"  ><em>*</em></FONT></td>
                                                  
                                                         <td><s:textfield name="city" id="city" value="%{city}" cssClass="inputTextBlue" onchange="cerFieldLengthValidator(this);" theme="simple"/> </td>
                                                  
                                                </tr>  
                                              
                                                <tr>
                                                    <td class="fieldLabel" valign="top">Engagement Details/Summary:</td>
                                                    <td colspan="3" valign="top"><s:textarea rows="2" cols="105" name="engagementDetails" value="%{engagementDetails}" cssClass="inputTextarea" id="engagementDetails"  onchange="cerFieldLengthValidator(this);"/>
                                                </td>
                                                </tr>
                                                 <tr>
                                                    <td class="fieldLabel" valign="top">Specific Technology/Tool insights:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3" valign="top"><s:textarea rows="2" cols="105" name="insightDetails" value="%{insightDetails}" cssClass="inputTextarea" id="insightDetails"  onchange="cerFieldLengthValidator(this);"/>
                                                </td>
                                                </tr>
                                                
                                                  <tr id="meetingDateTr">

                                                    <td class="fieldLabel">Meeting Date/Time:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3"><s:textfield name="meetingDate" id="meetingDate" value="%{meetingDate}" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                        <a href="javascript:cal3.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                 width="20" height="20" border="0"></a>
                                                   
                                                                <s:textfield name="startTime" id="startTime" value="%{startTime}" maxLength="5" placeholder="HH:MM" Class="inputSelectSmall" onchange="timeValidator(this);" onkeyup="enterdTime(this);"/>
                                                                <s:select id="midDayFrom" name="midDayFrom" value="%{midDayFrom}"  list="{'AM','PM'}" cssClass="inputSelectSmall" />
                                                                <s:select name="timeZone" id="timeZone" value="%{timeZone}" list="{'EST','IST','MST','CST','PST'}" cssClass="inputSelectSmall"/>
                                                                
                                                            </td>

                                                           
                                                           
                                                </tr> 
                                                
                                                 <tr id="otherMeetingDateTr">
                                                    <td class="fieldLabel" valign="top">Other Meeting slots available for Client:</td>
                                                  <%--  <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="otherMeetingSlots" value="%{otherMeetingSlots}" cssClass="inputTextareaReq" id="otherMeetingSlots" onchange="surveyFormFieldsValidator(this);"/> --%>
                                                    <td colspan="3"><s:textfield name="otherMeetingDate" id="otherMeetingDate" value="%{otherMeetingDate}" cssClass="inputTextBlue" onchange="validateTimestamp(this);"/>
                                                        <a href="javascript:cal4.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                 width="20" height="20" border="0"></a>
                                                   
                                                                <s:textfield name="otherStartTime" id="otherStartTime" value="%{otherStartTime}" maxLength="5" placeholder="HH:MM" Class="inputSelectSmall" onchange="timeValidator(this);" onkeyup="enterdTime(this);"/>
                                                                <s:select id="otherMidDayFrom" name="otherMidDayFrom" value="%{otherMidDayFrom}"  list="{'AM','PM'}" cssClass="inputSelectSmall" />
                                                                <s:select name="otherTimeZone" id="otherTimeZone" value="%{otherTimeZone}" list="{'EST','IST','MST','CST','PST'}" cssClass="inputSelectSmall"/>
                                                                
                                                            </td>
                                                </td>
                                                </tr>
                                                <s:if test="%{requestStage=='Reviewed'}"><tr> 
                                                     <td class="fieldLabel forRemove">Resources :<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3">
                    
                       <s:select  name="requestResources"  id="requestResources"  list="preSalesMap" cssClass="inputTextBlueLargeAccount" style="width:430px; height: 100px" multiple="true" value="%{requestResourcesList}"/> 
                     

                    </td>  </tr>
                                                </s:if><s:elseif test="%{resourceMap.size!=0}">
                                             
                                                 <td class="fieldLabel forRemove">Resources :<FONT color="red"  ><em>*</em></FONT></td>
                                                 <td colspan="3">
                                                  <s:iterator status="status" value="%{#request.resourceMap}">
<%-- <s:property value="#status.index"></s:property>
<s:property value="key"></s:property> --%>
<font color="green" size="2px"><b><s:property value="value"></s:property></b></font><br>
</s:iterator>
                                                 </td>
                                                </s:elseif>
                                                
                                                   <s:hidden name="resourcesHidden" id="resourcesHidden" />
                                               
                                                  <s:hidden name="PreviousResourceIds" id="PreviousResourceIds" value="%{PreviousResourceIds}" />
                                              
                                                
                                                <tr>
                                                    <td class="fieldLabel" valign="top">Additional Comment(s):</td>
                                                    <td colspan="3" valign="top"><s:textarea rows="2" cols="105" name="additionalComments" value="%{additionalComments}" cssClass="inputTextarea" id="additionalComments"  onchange="cerFieldLengthValidator(this);"/>
                                                </td>
                                                </tr>
                                                <tr>
                                                <td colspan="4" align="center">
                                                        <s:if test="%{requestStage=='Submitted'}"> 
                                                            <s:submit cssClass="buttonBg" value="Save Changes"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <input type="button" class="buttonBg" value="Review" onclick="doRequestSubmit('Reviewed');">
                                                        </s:if><s:elseif test="%{requestStage=='Reviewed'}">
                                                            <s:submit cssClass="buttonBg" value="Save Changes"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <input type="button" class="buttonBg" value="Approve" onclick="doRequestSubmit('Approved');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="buttonBg" value="Reject" onclick="doRequestSubmit('Rejected');">
                                                        </s:elseif>
                                                        
                                                        </td>
                                                     
                                                </tr>
                                            </table>
                                        </s:form>
                                    </div>
                                </div>

                                <script type="text/JavaScript">
                                    var cal3 = new CalendarTime(document.forms['frmClientRequestForm'].elements['meetingDate']);
                                    cal3.year_scroll = true;
                                    cal3.time_comp = false;
                                          var cal4 = new CalendarTime(document.forms['frmClientRequestForm'].elements['otherMeetingDate']);
                                    cal4.year_scroll = true;
                                    cal4.time_comp = false;
                                    
                                </script> 
                                <script type="text/javascript">

                                    var countries=new ddtabcontent("surveyDetailsTabs")
                                    countries.setpersist(false)
                                    countries.setselectedClassTarget("link") //"link" or "linkparent"
                                    countries.init()

                                </script>
                                <%-- </sx:div > --%>

                                <%-- </sx:tabbedpanel> --%>

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
             <tr>
                <td>
                    
                    <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto;" id="menu-popup">
                        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
                    </div>
                    
                </td>
            </tr>
            <!--//END FOOTER : Record for Footer Background and Content-->
   <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
     
         <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
           <script type="text/JavaScript" src="<s:url value="/includes/javascripts/selectivity-full.min.js"/>"></script>
             <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/StatusBar.css?version=2.0"/>">
		
		  
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/StatusBar.js?version=2.0"/>"></script> 
            <script>
            $(document).ready(function() {
             //  if(parseInt($("#accountId").val(),10)>0) {
            loadAddressRow();
            requestSelect();
            
          $('#requestResources').selectivity({
                    
                    multiple: true,
                    minimumInputLength: 2,
                    placeholder: 'Type to search Contact Name'
                });
                
                
                  $('#requestResources').change(function(e){
                    var emailArry = []; 
                    $("#requestResources :selected").each(function(){
                        emailArry.push($(this).val());
                        var len= emailArry.length;
                        if(len>5){
                                  
                            alert("We Can Selecte Max of 5 Members");
                            //$('#appreciationCC').selectivity('clear');
                            $('#requestResources').selectivity('remove', $(this).val());
                            return false;
                        }
                    });
                });
            //   }
                   });

        </script>
        </table>
        <!--//END MAIN TABLE : Table for template Structure-->
    </body>
</html>
