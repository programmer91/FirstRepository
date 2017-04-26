<!-- 
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 08, 2013, 3:25 PM
 *
 * Author  :  Ajay Tummapala
 *
 * File    : TechReviews.jsp
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved.`
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page import="com.freeware.gridtag.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.mss.mirage.employee.tasks.TasksVTO"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hubble Organization Portal :: Consultant Review Details</title>
  <%--  <sx:head cache="true"/> 
     <sj:head jqueryui="true"/>--%>
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tooltip.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tooltip.js"/>"></script>   
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>   
 
     
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
     
    <script type="text/javascript" src="<s:url value="/includes/javascripts/recruitment/ConsultantTechReviewsClientValidation.js"/>"></script>
    
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>

     
    <script type="text/javascript">
        
        function checkEmail(element)
 {
     if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(element.value)){return(true);
 }element.value="";
 alert("Invalid E-mail Address! Please re-enter.");
 return(false);
 } 
 function checkManadatory()
 {
   //  alert("test");
   //  return false;
     
    // var referTo=document.getElementById("referTo").value;
    // var techName = document.getElementById("techName").value;
     var preSales1 = document.getElementById("preSales1").value;
     var scheduledDate = document.getElementById("scheduledDate").value;
     var scheduledTime = document.getElementById("scheduledTime").value;
     var status = document.getElementById("status").value;
     if(preSales1=="0"){
           alert("Please select Presales1.");
             return false;
     }else if(scheduledDate==""){
           alert("Scheduled Date should not be empty.");
             return false;
     }
     else if(scheduledTime==""){
           alert("Scheduled Time should not be empty.");
             return false;
     }
      else if(status=="-1"){
           alert("Please select status.");
             return false;
     }
     if(referTo == null || referTo == "")
         {
                alert("Please enter Email Id.");
             return false;
         }
     else if(techName == null || techName == "")
         {
                alert("Please enter Techie Name.");
             return false;
         } 
         
         return true;
 }

 function fieldLenghtValidator(element){
  //var totMarks=document.getElementById("totMarks").value;
//var minMarks=document.getElementById("minMarks").value;

    var i = 0;
    if (element.value != null && (element.value != "")) {
        //alert("In if standard client validation");
        //if(element.id == 'comments') i = 1000;
        if(element.id == 'techName') i=100;
            
       // else if(element.id == 'totQuestions' || element.id == 'totDuration' || element.id == 'totMarks' || element.id == 'minMarks') i = 3;
        //else if(element.id == 'question') i = 1000;
       // else if(element.id == 'option1' || element.id == 'option2' || element.id == 'option3' || element.id == 'option4') i = 500;
       // else if(element.id == 'description') i = 1000;
      
        if(element.value.replace(/^\s+|\s+$/g,"").length>i){
            //subStringValue(i,element,"The "+element.id+" must be less than "+i+" characters");
            str = new String(element.value);
            element.value=str.substring(0,i);
            
            alert("The "+element.id+" must be less than "+i+" characters");
            element.focus();
            return false;
        }
      }
};
 function setNameAndEmail()
 {
     var group = document.getElementById("group").value;
     if(group == 1)
         {
             document.getElementById("techName").value = "";
             document.getElementById("referTo").value = "";  
         }
         else if(group == 'J2EE'){
             document.getElementById("techName").value = "J2EE Group";
             document.getElementById("referTo").value = "j2ee@miraclesoft.com";  
         }
          else if(group == 'WPS'){
             document.getElementById("techName").value = "WPS Group";
             document.getElementById("referTo").value = "wps@miraclesoft.com";  
         }
          else if(group == 'WCS'){
             document.getElementById("techName").value = "WCS Group";
             document.getElementById("referTo").value = "wcs@miraclesoft.com";  
         }
          else if(group == 'B2B'){
             document.getElementById("techName").value = "B2B Group";
             document.getElementById("referTo").value = "b2b@miraclesoft.com";  
         }
 }
  function setInterviewType(){
      var status = document.getElementById("status").value;
      document.getElementById("interveiwType").value=status;
 }

function timeValidator(element) {
    var x = element;
var f=x.value;

if(f.length==1){

    f = "0"+f+":";

   }
   
var s=f.split(':');



if((s[0]>=0) && (s[0]<13) && ((s[1]>=0) && (s[1]<60)) ){


if(s[1]==""){

x.value =s[0]+':'+"00";
}
else if(s[1].length==1){
    x.value =s[0]+':'+"0"+s[1];
   // s[1]="0"+s[1];
}
else
{
x.value =s[0]+':'+s[1];
}
}
else{
    x.value ="";
    alert('Enter time in 12 hours based')
}
}
 function enterdTime(element) {
    var x = element;

var f=x.value;

if(f.length==1 && f>1){
//alert("0"+f);
f="0"+f;
}
if(f.length==2){
var s=f.substring(1,2);
//alert(s);
if(s==":" || s==" "){
x.value ="0"+f.substring(0,1)+":";
}
else{
x.value = f+":";
}
   }
else {
x.value=f;
}

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
 
 <%!
        /* Declarations */
        Connection connection;

        String accountPrimaryTeamMember;
        String userId;
        String userRoleName;
        int isUserManager;

        String queryString;
        String currentAccountId;
        String strTmp;
        String strSortCol;
        String strSortOrd;

        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
        %>
<!-- <body class="bodyGeneral"  oncontextmenu="return false;" onload="init();">  -->
<body class="bodyGeneral"  oncontextmenu="return false;"> 

<!-//START MAIN TABLE : Table for template Structure-->
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
                   
                        <li ><a href="#" class="selected" rel="issueTab"  >Consultant Details </a></li>
                </ul>
                <!--//START TABBED PANNEL : -->
               <div  style="border:1px solid gray; width:830px;height: 400px; overflow:auto; margin-bottom: 1em;">
                <!--//START TAB : -->
                <div id="issueTab" class="tabcontent"  >
                  <s:form name="frmTechReview" action="doReferToReview" theme="simple" method="POST" enctype="multipart/form-data" onsubmit="return checkManadatory();">
                      <s:hidden name="consultantId" value="%{consultantId}"/>
                      <s:hidden name="recConsultantId" value="%{recConsultantId}"/>
                      <s:hidden name="requirementId" value="%{requirementId}"/>

                    <%-- <s:hidden name="empId" value="%{consultantId}"/>  --%>
                       <% if(request.getAttribute("resultMessage") != null){
                                            out.print(request.getAttribute("resultMessage"));
                                            
                                            request.removeAttribute("resultMessage");
                                            if(session.getAttribute("updateLink")!=null){
                                             //out.print(session.getAttribute("updateLink"));
                                        %>
                                        <a style="color:red;" href="<s:url action="../../crm/requirement/getRequirement.action"><s:param name="objectId" value="%{requirementId}"/><s:param name="requirementAdminFlag" value="%{requirementAdminFlag}"/></s:url>" class="navigationText"> Click here to update</a>
                                        <%}
session.removeAttribute("updateLink");
}%>
                    <table cellpadding="2" cellspacing="0" border="0" width="100%">                                                
                        <tr align="right">
                            <td class="headerText" colspan="6">
                           
                          <%--<a href="<s:url value="consultantSearch.action"/>" style="align:center;">--%>
                           <a href="<s:url action="../../crm/requirement/getConsultantRequirement.action"><s:param name="consultId" value="%{recConsultantId}"/><s:param name="objectId" value="%{requirementId}"/><s:param name="requirementAdminFlag" value="%{requirementAdminFlag}"/></s:url>" class="navigationText">
                                                            <img alt="Back to List"
                                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                            width="66px" 
                                                            height="19px"
                                                            border="0" align="bottom"></a>
                                
                               
                                 <%--  <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">&nbsp;&nbsp;--%>
                               
                                 
                           
                            
                        </tr>    
                       
                        <tr>
                            <td class="fieldLabel">Consultant Name :</td>
                            <td class="userInfoLeft">
                                  <%-- <a href="<s:url action="../consultant/getConsultant"><s:param name="empId" value="%{consultantId}"/><s:param name="requirement" value="%{-1}"/></s:url>" class="navigationText"> <s:property value="%{fiName}"  /></a>--%>
                                   <a href="<s:url action="../../crm/requirement/getConsultantRequirement.action"><s:param name="consultId" value="%{recConsultantId}"/><s:param name="objectId" value="%{requirementId}"/><s:param name="requirementAdminFlag" value="%{requirementAdminFlag}"/></s:url>" class="navigationText"> <s:property value="%{fiName}"  /></a>
                              <%-- <s:a href="getConsultant.action?requirement=-1"> <s:property value="%{fiName}"  /> </s:a>--%>
                                <s:hidden name="fiName" value="%{fiName}"  />
                                
                            </td> 
                            <td class="fieldLabel">Title :</td>
                            <td class="userInfoLeft">
                                <s:property value="%{jobTitle}"  />
                            <s:hidden name="titleTypeId" value="%{jobTitle}"  />
                            </td> 
                            
                        </tr>
                        
                        <tr>
                           <td class="fieldLabel">Mobile :</td>
                            <td class="userInfoLeft">
                                <s:property value="%{cellPhoneNo}"  />
                                <s:hidden name="cellPhoneNo" value="%{cellPhoneNo}"  />
                            </td> 
                            <td class="fieldLabel">Email :</td>
                            <td class="userInfoLeft">
                                <s:property value="%{email2}"  />
                            <s:hidden name = "email2" value="%{email2}"  />
                            </td> 
                        </tr>  
                    <%--    <tr>
                          <td class="fieldLabel"> Techie Name:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                           <td class="userInfoLeft">
                               <s:textfield name="techName" cssClass="inputTextBlueLarge" id="techName" onkeyup="getConsultantTechReviewsName();" onchange="fieldLenghtValidator(this);"/>
                           <div style="display: none; position: absolute; overflow:auto;" id="menu-popup">
                                    <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                </div>
                           <div class="fieldLabelLeft" id="validationMessage"></div>
                           </td>     
                            
                            <td class="fieldLabel">Techie Email:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                           <td class="userInfoLeft"><s:textfield name="referTo" cssClass="inputTextBlueLarge" id="referTo" onchange="checkEmail(this);" /></td> 
                       
                        </tr> --%>
                     <tr>
                          <td class="fieldLabel"> Presales1:<FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                           <td class="userInfoLeft">
                               <s:select headerKey="0" headerValue="--Please Select--" contentEditable='true' list="preSalesMap" name="preSales1" id="preSales1" cssClass="inputSelect1" value="%{preSales1}"/>

                           
                          
                           </td>     
                            
                            <td class="fieldLabel">Presales2:</td>
                           <td class="userInfoLeft">
                               <s:select headerKey="0" headerValue="--Please Select--" contentEditable='true' list="preSalesMap" name="preSales2" id="preSales2" cssClass="inputSelect1" value="%{preSales2}"/>

                           </td> 
                       
                        </tr>
                         <tr>
                                                    <td class="fieldLabel">Scheduled&nbsp;Date:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td class="userInfoLeft">
                                                        <s:textfield name="scheduledDate" value="%{scheduledDate}" id="scheduledDate" cssClass="inputTextBlue" style="width:65px" onchange="checkDates(this);"/><a href="javascript:cal1.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                            <s:textfield name="scheduledTime" id="scheduledTime"  placeholder="HH:MM" style="width:55px" onchange="timeValidator(this);" onkeyup="enterdTime(this);"/>
                                                            <s:select id="timeFormat" name="timeFormat" list="{'AM','PM'}" cssClass="inputSelect" style="width:50px" />
                                                            <s:select id="zone" name="zone" list="{'IST','EST','CST','PST'}" cssClass="inputSelect" style="width:50px" />
                                                    </td>
                                                <td class="fieldLabel">Skype&nbsp;Id:</td>
                                                    <td> <s:textfield id="skypeId" name="skypeId" value="%{skypeId}"  /></td>
                                                </tr>
                        <tr>
                            <td class="fieldLabel">Status:<FONT color="red"  ><em>*</em></FONT></td>
                            <td colspan="3">
                                <%-- <s:select name="status" id="status"  headerKey="-1" headerValue="--Please Select--" list="{'Tech Screen  - Phone','Tech Screen  - Skype/F2F','Tech Screen Reject','Tech Screen Shortlisted'}" cssClass="inputSelect" /> --%>
                                
                                <s:select name="status" id="status" contentEditable='true' headerKey="-1" headerValue="--Please Select--" list="{'Face to Face','Tech Screen  - Phone','Tech Screen  - Skype/F2F'}" cssClass="inputSelect1" onchange="setInterviewType();" />
                                <s:hidden name="interveiwType" id="interveiwType" value="%{status}"/>
                            </td>    
                            
                        </tr>
                         <tr>
                            <td class="fieldLabel">Comments:</td>
                            <td colspan="3">
                               <s:textarea rows="5" cols="81" name="comments" id="comments"  onchange="fieldLengthValidator(this);" cssClass="inputTextarea"/>
                                
                            </td>    
                            
                        </tr>

                        <tr>
                            <td class="fieldLabel">&nbsp;</td>
                            <td > <s:submit cssClass="buttonBg" value="Forward"/></td>
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
    <script type="text/JavaScript">
                                    var cal1 = new CalendarTime(document.forms['frmTechReview'].elements['scheduledDate']);
                                    cal1.year_scroll = true;
                                    cal1.time_comp = false;
                                </script>

                                 <!-- Attachments Grid Start -->
                   
    
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
<script type="text/javascript">
		$(window).load(function(){
	init();
		});
		</script>
</body>
</html>
