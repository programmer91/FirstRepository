 <%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.mss.mirage.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal ::Add Consultant For Requirement</title>
        <%-- <sx:head cache="true"/> --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css?version=1"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/RequirementUtil.js?version=1.2"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/recruitment/ConsultantAddClientValidation.js"/>"></script>

        <!--<script type="text/JavaScript">
            function checkResume() {
                var res = document.consultantRequirementForm.resumes.value;
                if(res != '') {
                    return true;
                }else {
                    alert('Plese Enter Resume');
                    document.consultantRequirementForm.resumes.focus();
                    return false;
                }
            }
        </script>-->
      <SCRIPT language=Javascript>
      
      function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
         {
            alert("Please enter numeric value");
            return false;
           } 

         return true;
      }
     
      
   function numberValid(year){
         if (isNaN(year) || year.length!=4 ){
              alert("Enter valid Year");
              document.getElementById("yearOfCompletion").value="";
         }
   }
     
       function fieldLengthValidator(element) {
       var i=0;
        if(element.id=="targetRate") { 
            i=15;
        }
        if(element.id=="comments") { 
            i=255;
        }
        if(element.id=="changeReason") { 
            i=200;
        }
        if(element.id=="reference") { 
            i=250;
        }
         if(element.id=="educationDetails") { 
            i=500;
        }
      
       
        
        if(element.value.replace(/^\s+|\s+$/g,"").length>i) {
            str=new String(element.value);
            element.value=str.substring(0,i);
            alert("The "+element.id+" must be less than "+i+" characters");
            element.focus();
            return false;
        }
        return true;
       
      }
      
      
           

      
   </SCRIPT>
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
     <%-- <script>
            function checkConsultantEmail()
            {
            var cmail=document.getElementById('email2').value;
           
            if(cmail=="" || cmail==null){
            alert("Please enter consultant email");
            return false;
            }
            else
            return true;
            }
            
        </script>--%>
    </head>
    <body class="bodyGeneral" oncontextmenu="return false;">

         <%!
            /* Declarations */
            Connection connection;
            String accountPrimaryTeamMember;
            String userId;
            String userRoleName;
            int isUserManager;
            String queryString;
            String consultantId;
            String strTmp;
            String strSortCol;
            String strSortOrd;
            int intSortOrd = 0;
            int intCurr;
            String strSQL = null;
            String custId = null;
            boolean blnSortAsc = true;
            String reqList = null;
            String isSearch = null;
            String url = null;

        %>

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
                            <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px;">
                                
                                <ul id="accountTabs" class="shadetabs" >
                                    <li><a href="#" class="selected" rel="consultantRequirementDiv"  >Consultant Details</a></li>
 <s:if test="%{currentRequirement.actionType == 'editConsultantRequirement' && ((#session.roleName =='Admin') ||(#session.roleName =='Recruitment') ||(#session.roleName =='Sales'))}">
                                 
                                        <li><a href="#" rel="reviewsTab">Activities</a></li>
                                 
 </s:if>


                                </ul>
                                <%-- <sx:tabbedpanel id="consultantRequirementPanel" cssStyle="width: 830px; height: 500px;padding: 5px 5px;" doLayout="true"> --%>
                                
                                <div  style="border:1px solid gray;  width:857px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                                    
                                    <div id="consultantRequirementDiv" class="tabcontent"  >
                                        
                                        <div id="overlayRecruitment"></div>              <!-- Start Overlay -->
                                            
                                    <div id="specialBoxRecruitment" style="left:auto;width:410px;">
                                       
                                        <div   id="addedConultantDiv" style="display: none;" >
                                             <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                <tr>                               
                                                    <td colspan="1" style="background-color: #288AD1" >
                                                        <lable style="color:darkblue;font: bold" align="left">
                                                            <span id="headerLabel"></span>
                                                        </lable>
                                                   
                                                        <a style="float:right;" href="#" onmousedown="closeConsultantList()" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  

                                                    </td></tr>
                                                <tr>
                                                    <td colspan="4">
                                                        <div id="load" style="color: green;display: none;">Loading..</div>
                                                        <div id="resultMessage"></div>
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td>
                                                        <table id="tblConsultantStatus" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width="404">
                                <%--   <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script> --%>
                                    <COLGROUP ALIGN="left" >
                                       
                                        <COL width="7%">
                                        <COL width="7%">
                                        <COL width="7%">
                                        

                               </table> 
                                                    </td>
                                                </tr>
                                                
                                             </table>    
                               
                                </div>
                                         </div>
<div id="overlayUpload"></div>              <!-- Start Overlay -->
                                            
                                    <div id="specialBoxUpload" style="left:auto;">
                                       
                                       <!-- <div id="uploadDiv" style="display: none;" > -->
                                             <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                 
                                                <tr>                               
                                                    <td colspan="4" style="background-color: #288AD1" >
                                                        <lable style="color:white;font: bold" align="left">
                                                           Work&nbsp;Authoriztion &nbsp;Copy&nbsp;Attached
                                                        </lable>
                                                   
                                                        <a style="float:right;" href="#" onmousedown="uploadWorkAuthorizationCopy();" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  

                                                    </td></tr>
                                                <tr>
                                                    <td >
                                                        
                                                        <div id="resultMessage2"></div>
                                                    </td>
                                                </tr>
                                                 <tr id="uploadTr" > 

                                                    <td class="fieldLabel">Upload File&nbsp;:</td>
                                                    <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="return fileValidation2();"/></td> 
                                                    <td  align="center" >
                                                        <input type="button" value="Upload" onclick="return fileUploadWorkAuthorizationCopy();" class="buttonBg" id="addButton" style="margin-left: 28px;"/> 


                                                        <%-- <s:submit cssClass="buttonBg"  align="right" id="save" value="Save" /> --%>
                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                    </td>
                                                </tr>
                                                
                                                
                                             </table>    
                               
                              <!--  </div> -->
                                         </div>
                                        
                                                        
                                                        <div id="overlayUpload1"></div>              <!-- Start Overlay -->
                                            
                                    <div id="specialBoxUpload1" style="left:auto;">
                                       
                                       <!-- <div id="uploadDiv" style="display: none;" > -->
                                             <table align="center" border="0" cellspacing="0" style="width:100%;" >
                                                 
                                                <tr>                               
                                                    <td colspan="4" style="background-color: #288AD1" >
                                                        <lable style="color:white;font: bold" align="left">
                                                           D/L&nbsp;&nbsp;Copy&nbsp;Attached
                                                        </lable>
                                                   
                                                        <a style="float:right;" href="#" onmousedown="uploadDlCopyAttached();" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  

                                                    </td></tr>
                                                <tr>
                                                    <td >
                                                     
                                                        <div id="resultMessage1"></div>
                                                    </td>
                                                </tr>
                                                 <tr id="uploadTr" > 

                                                    <td class="fieldLabel">Upload File&nbsp;:</td>
                                                    <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file1" onchange="return fileValidation1();" /></td> 
                                                    <td  align="center" >
                                                        <input type="button" value="Upload" onclick="return fileUploadDlCopyAttached();" class="buttonBg" id="addButton" style="margin-left: 28px;"/> 


                                                        <%-- <s:submit cssClass="buttonBg"  align="right" id="save" value="Save" /> --%>
                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                    </td>
                                                </tr>
                                                
                                                
                                             </table>    
                               
                              <!--  </div> -->
                                         </div>
                                                        
                                                        
                                        <%--  <sx:div id="consultantRequirementDiv" name="Consultant Requirements" label="Consultant Details" cssStyle="overflow:auto;"> --%>
                                        <s:form name="consultantRequirementForm" action="%{currentRequirement.actionType}" method="post" theme="simple" onsubmit="return consultantValidate();"><!-- onsubmit="return checkResume();">-->
                                            <table border="0" cellpadding="2" cellspacing="0" width="100%">
                                                <tr>
                                                    
                                                

                                                    <td colspan="6" class="headerText" align="right">
                                                        <%
                                                        if(request.getAttribute(ApplicationConstants.RESULT_MSG)!=null){
                                                            out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                        }
                                                        %>
                                                         <a href="<s:url action="../../crm/requirement/getRequirement.action"><s:param name="objectId" value="%{objectId}"/><s:param name="requirementAdminFlag" value="%{requirementAdminFlag}"/></s:url>" style="align:center;">
                                                                        <img alt="Back to List"
                                                                             src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                             width="66px" 
                                                                             height="19px"
                                                                             border="0" align="bottom"></a>
                                            
                                                        <s:hidden name="consultId" id="consultId"/>
                                                            <s:if test="%{currentRequirement.actionType == 'editConsultantRequirement' && ((#session.roleName =='Admin') ||(#session.roleName =='Recruitment') ||(#session.roleName =='Sales'))}">

                                                            <s:submit name="submit" value="Update" cssClass="buttonBg"/>
                                                        </s:if>
                                                        <s:elseif test="%{(#session.roleName =='Recruitment') || (#session.roleName =='Admin')}">
                                                            <s:submit name="submit" value="Save" cssClass="buttonBg"/>
                                                        </s:elseif>
                                                        <s:else>
                                                            
                                                        </s:else>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    
                                                     <td class="fieldLabel">Type Consultant Name :</td>
                                                      <s:if test="%{currentRequirement.actionType != 'editConsultantRequirement'}">
                                                            <td><s:textfield name="consultantName" id="consultantName" value="%{currentRequirement.consultantName}" autocomplete="off" cssClass="inputTextBlue" onkeyup="getRequirement();"/><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessage"></div>

                                                        <div style="display: none; position: absolute; overflow:auto;" id="menu-popup">
                                                            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                        </div>
                                                                
                                                          </s:if>
                                                                <s:else>
                                                                    <td>
                                                                         <s:hidden name="consultantName" id="consultantName" value="%{currentRequirement.consultantName}"/>
                                                                        <font color="green" size="2.5"><s:property value="%{currentRequirement.consultantName}"  /></font></td>
                                                                </s:else>
                                                                
                                                                
                                                   <td  class="fieldLabel" >

                                                        Job Title :        

                                                        </td>
                                                        <td><a href="<s:url action="../../crm/requirement/getRequirement.action"><s:param name="objectId" value="%{objectId}"/><s:param name="requirementAdminFlag" value="%{requirementAdminFlag}"/><s:param name="accId" value="%{accId}"/></s:url>" class="navigationText"> <s:property value="%{title}"  /></a></td>
                                                        
                                                </tr>
                                                <tr>



                                                    <td class="fieldLabel">Requirement Title :</td>
                                                    <td>

                                                        <s:if test="%{currentRequirement.actionType == 'editConsultantRequirement'}">
                                                            <s:textfield name="title" id="title" value="%{currentRequirement.title}" cssClass="inputTextBlue" readonly="true"/>
                                                        </s:if>
                                                        <s:else>
                                                            <input type="text" name="title" id="title" value="<%=request.getAttribute("title")%>" class="inputTextBlue" readonly="true"/>
                                                        </s:else>
                                                        <input type="hidden" name="requirementId" id="requirementId" value="<%=request.getAttribute("objectId")%>"/>
                                                        <s:hidden name="accId" value="%{accId}"/>
                                                    </td>

                                                    <s:if test="%{(currentRequirement.actionType == 'editConsultantRequirement')}">
                                                        <td  class="fieldLabel" >

                                                        Consultant Email:&nbsp;        

                                                        </td>
                                                        <s:if test="%{(#session.roleName =='Recruitment')}">
                                                        <td colspan="3"><a href="<s:url action="../../recruitment/consultant/getConsultant.action"></s:url>?empId=<s:property  value="currentRequirement.consultantId"/>&requirement=-1&consultId=<s:property value="consultId"/>&requirementId=<s:property value="objectId"/>&requirementAdminFlag=<s:property value="requirementAdminFlag"/>" class="navigationText"> <s:property value="%{currentRequirement.email2}"  /></a>
                                                        <s:hidden name="email2" id="email2" value="%{currentRequirement.email2}"/>
                                                        </td>
                                                </s:if>
                                                <s:else>
                                                    <td class="userInfoLeft"><s:property value="%{currentRequirement.email2}"  />
                                                        <s:hidden name="email2" id="email2" value="%{currentRequirement.email2}"/>
                                                    </td>
                                                </s:else>
                                                        </s:if>
                                                <s:else>
                                                      <td class="fieldLabel">Consultant Email:&nbsp;<font color="red">* </font></td>
                                                      <td>
                                                           <s:textfield name="email2" id="email2" value="%{currentRequirement.email2}" autocomplete="off" cssClass="inputTextBlue" />
                                                      </td>
                                                  
                                                        </s:else> 
                                                        <s:hidden name="consultantId" id="consultantId" value="%{currentRequirement.consultantId}"/>
                                                        <s:hidden name="roleName" id="roleName" value="%{#session.roleName}"/>

                                                   

                                                </tr>
                                                
                                                <tr>
                                                   <td class="fieldLabel">Resumes Attached :&nbsp;<font color="red">* </font></td>
                                                    <td>
                                                        <s:textfield name="resumes" id="resumes" value="%{currentRequirement.resumes}" autocomplete="off" cssClass="inputTextBlue" readonly="true" onclick="getResumeId();"/><div class="fieldLabelLeft" id="validationMessage1"></div>
                                                        <div style="display: none; position: absolute; overflow:auto;" id="menu-popup1">
                                                            <table id="completeTable1" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                        </div>
                                                        <s:hidden name="resumeId" id="resumeId" value="%{currentRequirement.resumeId}"/>
                                                        <s:hidden name="objectId" id="objectId" value="%{objectId}"/>
                                                        
                                                        <s:if test="%{currentRequirement.actionType == 'editConsultantRequirement'}">
                                                            <s:if test="%{currentRequirement.resumeId == 0}">
                                                                <input type="button" style="display: none;" class="buttonBg" value="Download" name="download" id="download" onclick="getDownload(document.consultantRequirementForm.resumeId.value);">
                                                            </s:if>
                                                            <s:else>
                                                                <input type="button" style="display: block;" class="buttonBg" value="Download" name="download" id="download" onclick="getDownload(document.consultantRequirementForm.resumeId.value);">
                                                            </s:else>
                                                        </s:if>
                                                        <s:else>
                                                            <input type="button" style="display: none;" class="buttonBg" value="Download" name="download" id="download" onclick="getDownload(document.consultantRequirementForm.resumeId.value);">
                                                        </s:else>
                                                    </td>

                                                    <td class="fieldLabel">Rate/Hr :<font color="red">* </font></td>
                                                    <td>
                                                        <s:textfield name="targetRate" id="targetRate" value="%{currentRequirement.targetRate}" onchange="fieldLengthValidator(this);" cssClass="inputTextBlue"/>
                                                    </td>

                                                </tr>

<tr>
                                                    <td colspan="3"  class="fieldLabel"><%--Skype Id&nbsp;:--%></td>
                                                    <td>
                                                                <s:hidden name="skypeId" id="skypeId" value="%{currentRequirement.skypeId}" cssClass="inputTextBlue" />
                                                   
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" >Available From:<font color="red">* </font></td>
                                                    <td>
     <s:textfield id="AvailableFrom" name="availableFrom" cssClass="inputTextBlueWithDatePicker" value="%{currentRequirement.availableFrom}"  onchange="checkDates(this);"/>
                                                        <a href="javascript:cal2.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                 width="20" height="20" border="0"></a>
                                                    </td>
                                                    <td class="fieldLabel">Mobile :<font color="red">* </td>
                                                    <td>
                                                        <s:textfield id="cellPhoneNo" name="cellPhoneNo" value="%{currentRequirement.cellPhoneNo}" cssClass="inputTextBlue" onchange="return formatPhone(this);"/>
                                                    </td>

                                                </tr>

<tr>
                                                    <td class="fieldLabel">Work Authoriztion :<font color="red">* </font></td>
                                                    <td>

                                                        <s:select headerKey="1" headerValue="--Please Select--" value="%{currentRequirement.workAuthorization}" list="{'US Citizen','Green Card','EAD','H1','B1','TN Permit Visa','Need H1B','Student Visa'}" 
                                                                  name="workAuthorization" id="workAuthorization" cssClass="inputSelect"/>
                                                    </td>

    

        <td class="fieldLabel">Current Employer  :<font color="red">* </font></td>
                                                    <td>
                                                        <s:textfield id="currentEmployer" name="currentEmployer" value="%{currentRequirement.currentEmployer}" cssClass="inputTextBlue"/>
                                                    </td>
 </tr>

                                                 <tr>
                                                    <td>
                                                        <s:hidden name="startDate" id="startDate" value="%{currentRequirement.startDate}" cssClass="inputTextBlue"/><a href="javascript:cal1.popup();">
                                                    </td>
                                                    <td>
                                                        <s:hidden name="techRate" id="techRate" value="%{currentRequirement.techRate}" onkeypress="return isNumberKey(event)" cssClass="inputTextBlue"/>
                                                    </td>
                                                </tr>

                                                 <tr>
                                                 <td class="fieldLabel">Status :<font color="red">* </font> </td>
                                                    <td>
                                                     <s:if test="%{(currentRequirement.actionType == 'editConsultantRequirement')}">
                                                       <s:select name="status" id="status" value="%{currentRequirement.status}" headerKey="-1" headerValue="--Please Select--" list="consultantStatusList" cssClass="inputSelect" onchange="checkStatus();"/><div id="forwardLink" style="display: none;"><a HREF="javaScript:doConsultantForward();" ><img SRC="../../includes/images/DBGrid/forward.png" WIDTH=20 HEIGHT=20 BORDER=0 ALTER="Forward"></a><label id="updatealertId"  style="color: red;display: none;" >Click on arrow to forward to tech review .</div>
                                                     </s:if>
                                                     <s:else>
                                                         <s:select name="status" id="status" contentEditable='true' value="%{currentRequirement.status}" headerKey="-1" headerValue="--Please Select--" list="consultantStatusList" cssClass="inputSelect" onchange="checkStatus();"/><div id="forwardLink" style="display: none;"><a HREF="javaScript:doConsultantForward();" ><img SRC="../../includes/images/DBGrid/forward.png" WIDTH=20 HEIGHT=20 BORDER=0 ALTER="Forward"></a><label id="updatealertId"  style="color: red;display: none;" >Click on arrow to forward to tech review .</div>
                                                     </s:else>
                                                    <s:hidden name="preStatus" id="preStatus" value="%{currentRequirement.status}"/>
                                                    </td>
                                                    <td class="fieldLabel">Current Location  :<font color="red">* </font></td>
                                                    <td>
                                                        <s:textfield id="currentLocation" name="currentLocation" value="%{currentRequirement.currentLocation}" cssClass="inputTextBlue"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">Work Authoriztion Copy Attached&nbsp;:</td>
                                                      <s:hidden name="workAuthorizationCopyAttachment" id="workAuthorizationCopyAttachment" value="%{currentRequirement.workAuthorizationCopyAttachment}"  />
                                                  <s:hidden id="actionName" value="%{currentRequirement.actionType}" />
                                                     
                                                         <s:if test="%{currentRequirement.actionType == 'editConsultantRequirement' && (currentRequirement.workAuthorizationCopy != '' && currentRequirement.workAuthorizationCopy != null)}">
           <%--  <input type="button"  class="buttonBg" value="Download" name="download" id="download" onclick="getDownloadAuthCopy('%{currentRequirement.consultantId}','%{currentRequirement.requirementId}');" />
                                                                <a href=javascript:getDownloadAuthCopy("");>"+AttachmentName+"</a> --%>
           
                                                                <td><span id="downloadTd"><a href="<s:url action="../../crm/requirement/getDownloadAuthCopy.action"><s:param name="requirementId" value="%{objectId}"/><s:param name="consultantId" value="%{currentRequirement.consultantId}"/></s:url>" class="navigationText"> <s:property value="%{currentRequirement.workAuthorizationCopy}"  /></a></span>
                                                                    <span id="downloadTr"></span>
                                                                    <input type="button" class="uploadButton" value="Upload" onclick="uploadWorkAuthorizationCopy();"/></td>
                                                              <s:hidden name="workAuthorizationCopy" id="workAuthorizationCopy"  value="%{currentRequirement.workAuthorizationCopy}"  />
                                                   
                                                         </s:if>
                                                               
                                                          <s:if test="%{currentRequirement.workAuthorizationCopy == '' || currentRequirement.workAuthorizationCopy == null}">
                                                                     <td>  <s:textfield name="workAuthorizationCopy" id="workAuthorizationCopy" value="%{currentRequirement.workAuthorizationCopy}" autocomplete="off" cssClass="inputTextBlue"  readonly="true" />
                                                        <input type="button" class="uploadButton" value="Upload" onclick="uploadWorkAuthorizationCopy();"/></td>
                                                           
   </s:if>                                                   
                          <td class="fieldLabel">Currently OnProject&nbsp;:</td>
                                                     <td>
                                                          <s:select name="onProject" id="onProject" value="%{currentRequirement.onProject}" headerKey="-1" headerValue="--Please Select--" list="#@java.util.LinkedHashMap@{'1':'Yes','2':'No'}" cssClass="inputSelect" />
                                                     </td> 
                                                </tr> 
                                                <tr>

                                                   
                                                    <td class="fieldLabel">D/L Copy Attached :</td>
                                                    <s:hidden name="dlCopyAttachedAttachment" id="dlCopyAttachedAttachment" value="%{currentRequirement.dlCopyAttachedAttachment}"  />
                                                   
                                                     
                                                    
                                                      <s:if test="%{currentRequirement.actionType == 'editConsultantRequirement' && (currentRequirement.dlCopyAttached != '' && currentRequirement.dlCopyAttached != null)}">
           <%--  <input type="button"  class="buttonBg" value="Download" name="download" id="download" onclick="getDownloadAuthCopy('%{currentRequirement.consultantId}','%{currentRequirement.requirementId}');" />
                                                                <a href=javascript:getDownloadAuthCopy("");>"+AttachmentName+"</a> --%>
             <s:hidden name="dlCopyAttached" id="dlCopyAttached" value="%{currentRequirement.dlCopyAttached}"  />
                                                                <td><span id="dldownloadTd"><a href="<s:url action="../../crm/requirement/getDownloadDLCopy.action"><s:param name="requirementId" value="%{objectId}"/><s:param name="consultantId" value="%{currentRequirement.consultantId}"/></s:url>" class="navigationText"> <s:property value="%{currentRequirement.dlCopyAttached}"  /></a></span>&nbsp;
                                                                 <span id="dldownloadTr" ></span><input type="button" class="uploadButton" value="Upload" onclick="uploadDlCopyAttached();"/></td>
                                                        
                                                      </s:if>
                                                      <s:if test="%{currentRequirement.dlCopyAttached == '' || currentRequirement.dlCopyAttached == null}">
                                                        
                                                    
                                                    
                                                    <td>
                                                        <s:textfield name="dlCopyAttached" id="dlCopyAttached" value="%{currentRequirement.dlCopyAttached}" autocomplete="off" cssClass="inputTextBlue" onchange = "return fileValidation();" readonly="true" />
                                                    <input type="button" class="uploadButton" value="Upload" onclick="uploadDlCopyAttached();"/>
                                                                    

                                                    </td>
                           
                                                                 
        </s:if>
                                                          
                                                       
             
                                                     <td class="fieldLabel">Current Project Ending&nbsp;: </td>
                                                    <td>
                                                        <s:textfield id="projectEnd" name="projectEnd" cssClass="inputTextBlueWithDatePicker" value="%{currentRequirement.projectEnd}" />
                                                        <a href="javascript:cal4.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                            width="20" height="20" border="0"></a>
                                                    </td>
                                                   
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel">ReLocation&nbsp;:<font color="red">* </font></td>
                                                     <td>
                                                          <s:select name="relocation" id="relocation" value="%{currentRequirement.relocation}" headerKey="-1" headerValue="--Please Select--" list="{'Yes','No'}" cssClass="inputSelect" />
                                                     </td>
                                                     
                                                     <td class="fieldLabel">Arrived To USA&nbsp;:</td>
                                                    <td>
                                                     <s:textfield id="startDatetoUs" name="startDatetoUs" cssClass="inputTextBlueWithDatePicker" value="%{currentRequirement.startDatetoUs}"  onchange="checkDates(this);"/>
                                                     <a href="javascript:cal3.popup();">
                                                     <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                            width="20" height="20" border="0"></a>
                                                   </td> 
                                                     
                                                </tr>

                                                
                                                
                                             <tr>
                                                    <td class="fieldLabel">Bachlor&nbsp;of&nbsp;Degree&nbsp;completion&nbsp;year&nbsp;:</td>
                                                    <td> <s:textfield name="yearOfCompletion" id="yearOfCompletion" value="%{currentRequirement.yearOfCompletion}" cssClass="inputTextBlue" onchange="numberValid(this.value);" readonly="false"/>
                                                    </td>
                                                
                                                   <td class="fieldLabel">OnSite&nbsp;Availabilty(M-F)/No&nbsp;WFH:</td>
                                                    <td>
                                                          <s:select name="availability" id="availability" value="%{currentRequirement.availability}" headerKey="-1" headerValue="--Please Select--" list="{'Yes','No'}" cssClass="inputSelect" />
                                                    </td> 
                                                    
                                                    
                                                 </tr>
                                                 <tr>
                                                     <td class="fieldLabel">Reason For Change&nbsp;: </td>
                                                     <td  colspan="4">  <s:textarea rows="1" cols="90" id="changeReason" name="changeReason" cssClass="inputTextarea" value="%{currentRequirement.changeReason}" onchange="fieldLengthValidator(this);" /> </td>
                                                      
                                                 </tr>
                                                <tr>
                                                   <td class="fieldLabel">Education&nbsp;Details&nbsp;:&nbsp;</td>
                                                     <td  colspan="4">
                                                          <s:textarea rows="1" cols="90" name="educationDetails" id="educationDetails" value="%{currentRequirement.educationDetails}" onchange="fieldLengthValidator(this);"  cssClass="inputTextarea"/>
                                                    </td> 
                                                </tr>
                                            
                                                  
                                              <tr>
                                                    <td class="fieldLabel">References :</td>
                                                    <td colspan="4">
                                                        <s:textarea rows="3" cols="90" name="reference" id="reference" value="%{currentRequirement.reference}" onchange="fieldLengthValidator(this);"  cssClass="inputTextarea"/>
                                                    </td>
                                                </tr> 
                                                <tr>

                                                    <td class="fieldLabel">Comments :</td>
                                                    <td colspan="4">
                                                        <s:textarea rows="5" cols="90" name="comments" id="comments" value="%{currentRequirement.comments}" onchange="fieldLengthValidator(this);" cssClass="inputTextarea"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                        <script type="text/JavaScript">
                                            var cal1 = new CalendarTime(document.forms['consultantRequirementForm'].elements['startDate']);
                                            cal1.year_scroll = true;
                                            cal1.time_comp = true;
                                             var cal2 = new CalendarTime(document.forms['consultantRequirementForm'].elements['availableFrom']);
                                            cal2.year_scroll = true;
                                            cal2.time_comp = false;
 var cal3 = new CalendarTime(document.forms['consultantRequirementForm'].elements['startDatetoUs']);
                                            cal3.year_scroll = true;
                                            cal3.time_comp = false;
 var cal4 = new CalendarTime(document.forms['consultantRequirementForm'].elements['projectEnd']);
                                            cal4.year_scroll = true;
                                            cal4.time_comp = false;






                                        </script>
                                        <%--  </sx:div> --%>
                                    </div>
                                    <%--    </sx:tabbedpanel> --%>

                              
                                <script type="text/javascript">

var countries=new ddtabcontent("accountTabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

                                </script>
                                
                                

                               


                                  

                                        <!-- reviews tab start -->
                                        <div id="reviewsTab" class="tabcontent"  >
                                            <%
                                                /* String Variable for storing current position of records in dbgrid*/
                                                strTmp = request.getParameter("txtCurr");

                                                intCurr = 1;

                                                if (strTmp != null) {
                                                    intCurr = Integer.parseInt(strTmp);
                                                }

                                                /* Specifing Shorting Column */
                                                strSortCol = request.getParameter("Colname");

                                                if (strSortCol == null) {
                                                    strSortCol = "ForwardedDate";
                                                }

                                                strSortOrd = request.getParameter("txtSortAsc");
                                                if (strSortOrd == null) {
                                                    strSortOrd = "ASC";
                                                }





                                                try {

                                                    /* Getting DataSource using Service Locator */
                                                    //  System.out.println("consultant Id -----------"+request.getParameter("consultantId"));;
                                                    //  out.println("hii-->"+request.getAttribute("currentRequirement.consultantId"));


                                                    connection = ConnectionProvider.getInstance().getConnection();
                                                    custId = request.getAttribute("currentRequirement.consultantId").toString();
                                                    String recConsultId = request.getParameter("consultId");
                                                    String requirementId = request.getParameter("objectId");
                                                    String requirementAdminFlag = request.getAttribute("requirementAdminFlag").toString();
                                                    //Retrieving Users Rolename from Session Attributes.


                                                    /* Sql query for retrieving resultset from DataBase */
                                                    queryString = null;

                                                    //int authorTopicId = (Integer)request.getAttribute("topicId");
                                                    //  int objectId = Integer.parseInt(request.getAttribute("taskObjectId").toString());
                                                    // queryString = "SELECT Id, ObjectId, ObjectType, AttachmentName, AttachmentLocation, AttachmentFileName , DateUploaded,UploadedBy FROM tblTaskAttachments WHERE  ObjectId = "+objectId;
                                                    String addAttachmentAction = "";
                                                    String reviewAction = "../../recruitment/consultant/reviews.action?consultantId=" + custId + "&recConsultantId=" + recConsultId + "&requirementId=" + requirementId + "&requirementAdminFlag=" + requirementAdminFlag;
                                                    //../consultant/reviews.action?consultantId={Id}
                                                    // queryString = "SELECT Id,ConsultantId,ForwardedBy,ForwardedTo,ForwardedDate,ForwardToName,LastModifiedDate,Comments,Rateing,STATUS FROM tblRecConsultantActivity WHERE ConsultantId="+custId+" AND RequirementId="+requirementId+" ORDER BY LastModifiedDate DESC";
                                                    queryString = "SELECT Id,ConsultantId,ForwardedBy,ForwardedTo,ForwardedDate,ForwardToName,LastModifiedDate,Comments,TechnicalSkills,DomainSkills,CommunicationSkills,STATUS,CASE WHEN (STATUS='Tech Screen Reject' OR STATUS='Tech Screen shotlisted') THEN  'view' ELSE ' ' END AS Rating,CASE WHEN (STATUS='Tech Screen Reject' OR STATUS='Tech Screen shotlisted') THEN  'view' ELSE ' ' END AS TechComments,CASE WHEN (STATUS='Tech Screen Reject' OR STATUS='Tech Screen shotlisted') THEN  InterveiwType ELSE ' ' END AS InterveiwType FROM tblRecConsultantActivity WHERE ConsultantId=" + custId + " AND RequirementId=" + requirementId + " ORDER BY ForwardedDate DESC";

                                                  //  System.out.println("activities list" + queryString);
                                            %>

                                            <table cellpadding="0" cellspacing="0" width="100%" border="0" >


                                                <%-- <tr>
                                                                   
                                                                    <td class="headerText"> <a href="<%=reviewAction%>" style="align:left;">
                                                                               <img alt="Add Activity"
                                                                                    src="<s:url value="/includes/images/add.gif"/>" 
                                                                                    width="33px" 
                                                                                    height="19px"
                                                                                border="0" align="left"></a>&nbsp;&nbsp;
                                                                           
                                                                       </td>
                                                                   </tr> --%>

                                                <!-- BEGIN:: DBGrid Specific -->  

                                                <tr>
                                                    <td>
                                                        <div>
                                                            <s:form action="" theme="simple" name="frmDBGrid" method="post">   
                                                                <grd:dbgrid id="tblTaskAttachments" name="tblTaskAttachments" width="100" pageSize="10" 
                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2" 
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif" scriptFunction="doNavigate" />

                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                    imageAscending="../includes/images/DBGrid/ImgAsc.gif" 
                                                                                    imageDescending="../includes/images/DBGrid/ImgDesc.gif"/>  
                                                                    <grd:rownumcolumn headerText="SNo" width="2" HAlign="center"/>   
                                                                    <grd:textcolumn dataField="STATUS" headerText="Consultant Status" width="10"/>
                                                                    <grd:textcolumn dataField="InterveiwType" headerText="Interveiw Type" width="10"/>
                                                                    <grd:textcolumn dataField="ForwardedBy" headerText="Done By" width="10"/>
                                                                    <%--<grd:textcolumn dataField="ForwardedTo" headerText="AssignedTo" width="10"/>
                                                                    <grd:textcolumn dataField="ForwardToName" headerText="AssignedToName" width="15"/> --%>
                                                                    <%--  <grd:textcolumn dataField="Comments" headerText="Comments" width="10"/>--%>
                                                                    <grd:anchorcolumn dataField="Comments" linkUrl="javascript:getTechComments('{Id}')" headerText="Comments"
                                                                                      linkText="View"  width="6" HAlign="center"/>
                                                                    
                                                                    <grd:anchorcolumn dataField="TechComments" headerText="Tech Comments" width="6" HAlign="center" linkUrl="javascript:getTechReviewComments('{Id}')" linkText="{TechComments}"/>
                                                                    <grd:anchorcolumn dataField="Rating" headerText="Rating" width="6" HAlign="center" linkUrl="javascript:getRatings('{Id}')" linkText="{Rating}"/>
                                                                    <%--<grd:textcolumn dataField="Rateing" headerText="Rateing" width="10"/> --%>


                                                                    <grd:datecolumn dataField="ForwardedDate" headerText="CreatedDate" dataFormat="MM-dd-yyyy hh:mm" width="8"/> 
                                                                     <grd:datecolumn dataField="LastModifiedDate" headerText="ModifiedDate" dataFormat="MM-dd-yyyy hh:mm" width="8"/> 
                                                                    <%-- <grd:anchorcolumn dataField="Comments" linkUrl="javascript:sendMailToTech('{Id}','{ConsultantId}','{ForwardedTo}','{ForwardToName}')" headerText="Alert"
                                                                                      linkText="View"  width="6" HAlign="center"/> --%>
                                                                    <%--  <grd:imagecolumn headerText="Alert" width="4" HAlign="center" 
                                                                                          imageSrc="../../includes/images/alert_icon_small.png"
                                                                                          linkUrl="javascript:sendMailToTech('{Id}','{ConsultantId}','{ForwardedTo}','{ForwardToName}')" imageBorder="0"
                                                                                          imageWidth="11" imageHeight="10" alterText="Alert"></grd:imagecolumn>--%>

                                                                </grd:dbgrid>



                                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>                                

                                            </s:form>
                                            <%
                                                    connection.close();
                                                    connection = null;
                                                } catch (Exception ex) {
                                                    out.println(ex.toString());
                                                } finally {
                                                    if (connection != null) {
                                                        connection.close();
                                                        connection = null;
                                                    }
                                                }
                                            %>
                                        </div>
                                        <!-- end -->

                                    </div> 
                                    <script type="text/javascript">

                                        var countries=new ddtabcontent("accountTabs1")
                                        countries.setpersist(true)
                                        countries.setselectedClassTarget("link") //"link" or "linkparent"
                                        countries.init()

                                    </script>

                              

                            </td>
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

 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
<script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
<script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/FileUpload.js"/>"></script>

<script type="text/javascript">
		$(window).load(function(){
		init();
               
		});
		</script>

    </body>
</html>

