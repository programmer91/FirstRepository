<!--
 *******************************************************************************
 *
 * Project : Mirage V2
 *
 * Package :
 *
 * Date    :  November 20, 2007, 3:25 PM
 *
 * Author  :  Ajay Tummapala
 *
 * File    : ProjectTeamAdd.jsp
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



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hubble Organization Portal :: Add Project TeamMember</title>
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
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/IssueFill.js?version=1.1"/>"></script>
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/fillPMOList.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>  
        <script>
            function currencyPopup(url) {
                newwindow=window.open(url,'name','height=400,width=600');
                if (window.focus) {newwindow.focus()}
                return false;
            }
            function clearResourceName()
            {
                document.getElementById("assignedToUID").value = "";
                document.getElementById("preAssignEmpId").value="";
            }
            function checkName()
            {
                var empId = document.getElementById("preAssignEmpId").value=""; 
                if(empId=="" || empId==null){
                    //  alert("Select from suggestion list.");
                    document.getElementById("assignedToUID").value = "";
                }
            }
            /*
    function checkProjectTeamForm()
    {
        var empId = document.getElementById("assignedToUID").value; 
        var utilisation= document.getElementById("utilisation").value;
        var startDate = document.getElementById("dateAssigned").value;             
        var endDate =document.getElementById("dateClosed").value;
        var sDate=new Date(startDate);            
        var eDate=new Date(endDate);
        if(sDate>eDate){
            alert("StartDate must less than EndDate");
            return false;
        }
        if(empId=="" || empId==null)
            {
                alert("Please select resource name from suggestion list");
                   return false; 
            }
            else if(utilisation == "" || utilisation == null){
                alert("Please enter utilsation");
                return false;
            }
            return true;
    }*/
    
            function checkProjectTeamForm()
            {
                
                
                //alert("che");
                var empId = document.getElementById("assignedToUID").value.length;
                //alert("hellooooooo----->"+empId);
                var utilisation= document.getElementById("utilisation").value.length;
                var util= document.getElementById("utilisation").value;
                //   alert("utilisation----->"+utilisation);
                var startDate = document.getElementById("dateAssigned").value;  
                //  alert("startDate----->"+startDate);
                var endDate =document.getElementById("dateClosed").value;
                //  alert("endDate----->"+endDate);
                var projectReportsTo =document.getElementById("projectReportsTo").value;
                //  alert("projectReportsTo----->"+projectReportsTo);
                var status =document.getElementById("status").value.toString();
                var availableutil = document.getElementById("availableUtl").value;
                var hidutilisation = document.getElementById("hidutilisation").value;
                var billableStartDate = document.getElementById("billableStartDate").value;  
                var billableEndDate = document.getElementById("billableEndDate").value;  
                 
                var checkAlert = document.getElementById("checkAlert").checked;  
                //  var startDateTemp = document.getElementById("startDateTemp").value;  
              
                //  alert("util"+util+" availableutil "+availableutil);
               
                var existedStatus = document.getElementById('existedStatus').value;

                var empProjectStatus=document.getElementById("empProjectStatus").value;
                /*if(sDate>eDate){
                alert("StartDate must less than EndDate");
                return false;
            } */

                if(empId==0 || empId==null)
                {
                    alert("Please select resource name from suggestion list");
                    return false;
                }
                else if(utilisation == "" || utilisation == null){
                    alert("Please enter utilization");
                    return false;
                }
                //alert(status);
                if(status=='InActive'){
                    if(endDate.trim()==""){
                        alert("To inactive team member end date must not be empty");
                        return false;
                    }
                  
                    var comments = document.getElementById("comments").value;

                    if(comments == "" || comments == null){
                        alert("Please enter reason");
                        return false;
                    }
                }
              
                if(parseInt(util) == 0 && status=='Active') {
                                        
                    if(empProjectStatus!='Overhead'){
                        alert("Utilization can't be zero")
                        return false;
                    }
                }
                
                if(utilisation != 0 || utilisation != ""){
                    if(document.getElementById('status').value=='Active' && document.getElementById('existedStatus').value=='InActive'){
                        if(parseInt(availableutil)<parseInt(util)){
                            alert("Utilization can't be greater than Available utilization ");
                            return false;
                        }
                    }else{ 
                        if((parseInt(hidutilisation)+parseInt(availableutil))<parseInt(util)){
                            alert("Utilization can't be greater than Available utilization ");
                            return false;
                        }
                    }
                }else{
                    alert("Utilization field cannot be empty");
                    return false;
                }

    
     
                if(startDate.trim() == '' || startDate == null){
                    alert("Start Date must not be empty");
                    return false;
                }
                
                if(checkDate()==false){
                    return false;
                }
                if(checkAlert){
                    if(billableStartDate.trim() == '' || billableStartDate == null){
                        alert("Billable Start Date must not be empty");
                        return false;
                    }
                
                    if(checkBillableDate()==false){
                        return false;
                    }    
                }else{
                    document.getElementById("billableStartDate").value='';
                    document.getElementById("billableEndDate").value='';
                }
               
         
                /* else if(secProjectReportsTo!=-1 && projectReportsTo==-1) {
                    alert("Secondary reports to not allowed, without selecting primary reports to!");
                    return false;
                }
                else if(secProjectReportsTo==projectReportsTo && secProjectReportsTo!=-1) {
                    alert("Primary and secondary reports to must be different!");
                    return false;
                } */
      
      
                document.getElementById("buttonId").disabled=true;
                return true;   
            }
            
            
            function checkDate() {
                var sdate =  document.getElementById("dateAssigned").value;
                var edate =document.getElementById("dateClosed").value;
                
                var mm = sdate.substring(0,2);
                var dd = sdate.substring(3,5);
                var yyyy = sdate.substring(6,10);
                var mm1 = edate.substring(0,2);
                var dd1 = edate.substring(3,5);
                var yyyy1 = edate.substring(6,10);
    
                if(edate != "" && edate != null){
                    //alert(edate);
                    if(yyyy1 < yyyy) {
                        alert('End Date is older than Start Date');
                        return false;
                    }
                    else if((yyyy1 == yyyy) && (mm1 < mm)) {
                        alert('End Date is older than Start Date');
                        return false;
                    }
                    else if((yyyy1 == yyyy) && (mm1 == mm) && (dd1 < dd)) {
                        alert('End Date is older than Start Date');
                        return false;
                    }
                }
       
                return true;
            }

            function checkBillableDate() {
              
                var sdateBillable =  document.getElementById("billableStartDate").value;
                var edateBillable =document.getElementById("billableEndDate").value;
                
                var BillableStartMonth = sdateBillable.substring(0,2);
                var BillableStartDate = sdateBillable.substring(3,5);
                var BillableStartYear = sdateBillable.substring(6,10);
                
                var BillableEndMonth = edateBillable.substring(0,2);
                var BillableEndDate = edateBillable.substring(3,5);
                var BillableEndYear = edateBillable.substring(6,10);
                
                var sdate =  document.getElementById("dateAssigned").value;
                var edate =document.getElementById("dateClosed").value;
                
                var StartMonth = sdate.substring(0,2);
                var StartDate = sdate.substring(3,5);
                var StartYear = sdate.substring(6,10);
                var EndMonth = edate.substring(0,2);
                var EndDate = edate.substring(3,5);
                var EndYear = edate.substring(6,10);
                
                if(sdateBillable != "" && sdateBillable != null){
                    //alert(edate);
                    if(BillableStartYear < StartYear) {
                        alert('Billable Start Date  should be greater than Start Date');
                        return false;
                    }
                    else if((BillableStartYear == StartYear) && (BillableStartMonth < StartMonth)) {
                        alert('Billable Start Date  should be greater than Start Date');
                        return false;
                    }
                    else if((BillableStartYear == StartYear) && (BillableStartMonth == StartMonth) && (BillableStartDate < StartDate)) {
                        alert('Billable Start Date  should be greater than Start Date');
                        return false;
                    }
                    
                    if(edate!=null && edate!=""){
                        if(BillableStartYear > EndYear) {
                            alert('Billable Start Date should be smaller than End Date');
                            return false;
                        }
                        else if((BillableStartYear == EndYear) && (BillableStartMonth > EndMonth)) {
                            alert('Billable Start Date should be smaller than End Date');
                            return false;
                        }
                        else if((BillableStartYear == EndYear) && (BillableStartMonth == EndMonth) && (BillableStartDate > EndDate)) {
                            alert('Billable Start Date should be smaller than End Date');
                            return false;
                        }
                    }
                 
                }
                
               
    
                if(edateBillable != "" && edateBillable != null){
                    //alert(edate);
                    if(BillableStartYear > BillableEndYear) {
                        alert('Billable Start Date should be smaller than or equal to Billable End Date');
                        return false;
                    }
                    else if((BillableStartYear == BillableEndYear) && (BillableStartMonth > BillableEndMonth)) {
                        alert('Billable Start Date should be smaller than or equal to Billable End Date');
                        return false;
                    }
                    else if((BillableStartYear == EndYear) && (BillableStartMonth == BillableEndMonth) && (BillableStartDate > BillableEndDate)) {
                        alert('Billable Start Date should be smaller than or equal to Billable End Date');
                        return false;
                    }
                    
                    
                    if(edate!=null && edate!=""){   
                        if(BillableEndYear > EndYear) {
                            alert('Billable End Date Should be smaller than or equal to End Date');
                            return false;
                        }
                        else if((BillableEndYear == EndYear) && (BillableEndMonth > EndMonth)) {
                            alert('Billable End Date Should be smaller than or equal to End Date');
                            return false;
                        }
                        else if((BillableEndYear == EndYear) && (BillableEndMonth == EndMonth) && (BillableEndDate > EndDate)) {
                            alert('Billable End Date Should be smaller than or equal to End Date');
                            return false;
                        }
                    }
                }
         
                return true;
            }



            function setDualOrNot(){
                var temp = document.getElementById("temp").value;
                if(temp==1)
                {
                    document.getElementById("dual").style.display="block";
                    document.getElementById("notDual").style.display="none";
                }
                else
                {
                    document.getElementById("dual").style.display="none";
                    document.getElementById("notDual").style.display="block";  
                }
    
            }
            
            function showReason(){
                //   alert("hiii");
                var status = document.getElementById("status").value;
                //  alert("------>"+status)
                if(status == "Active"){
                    // alert("active")
                    document.getElementById('comments').style.display='none';
                    document.getElementById('commentsField').style.display='none';
                    document.getElementById('utilisation').readOnly =false;

                }
                else{
                    // alert("inactive")
                    document.getElementById('utilisation').readOnly =true;
                    var hidutilisation= document.getElementById('hidutilisation').value;
                    document.getElementById('utilisation').value =hidutilisation;


                    document.getElementById('comments').style.display='';
                    document.getElementById('commentsField').style.display='';

                }
            }


            function toggleCloseOverlay() {
                var inActiveComments=document.getElementById('inactiveComments').value;
    
                if(inActiveComments.trim().length>0){
                    document.getElementById('comments').value= inActiveComments+"\n"+document.getElementById('comments').value;
                    var overlay = document.getElementById('overlay');
                    var specialBox = document.getElementById('specialBox');

                    overlay.style.opacity = .8;
                    if(overlay.style.display == "block"){
                        overlay.style.display = "none";
                        specialBox.style.display = "none";

                        //   document.getElementById("frmDBGrid").submit();

                    }
                    else {
                        overlay.style.display = "block";
                        specialBox.style.display = "block";
                    }
        
                }else {
                    document.getElementById('resultMessage').innerHTML ='<font color=red size=2px>Please enter comments..</font>';
                }
  

         
                //document.getElementById("frmDBGrid").submit();
            }

            function getInActiveDetails(){
  
                if(document.getElementById('status').value=='Active' && document.getElementById('existedStatus').value=='InActive'&& document.getElementById('actionType').value=='doUpdateProjectTeam'){
                    // alert("getInActiveDetails.......");
                    getStatusOfTheEmployee();


                }



                if(document.getElementById('status').value=='InActive' && document.getElementById('existedStatus').value=='Active'&& document.getElementById('actionType').value=='doUpdateProjectTeam'){
                    document.getElementById('resultMessage').innerHTML ='';
  
    
     
                    document.getElementById("headerLabel").style.color="white";
                    document.getElementById("headerLabel").innerHTML="InActive Comments";
                    var overlay = document.getElementById('overlay');
                    var specialBox = document.getElementById('specialBox');
                    overlay.style.opacity = .8;
                    if(overlay.style.display == "block"){
                        overlay.style.display = "none";
                        specialBox.style.display = "none";
                    } else {
                        overlay.style.display = "block";
                        specialBox.style.display = "block";
                    }
                }
            }

            function doAppendComments() {
                var inActiveComments=document.getElementById('inactiveComments').value;
                var isValid=false;
                  
                if(inActiveComments.trim().length==0){
                    document.getElementById('resultMessage').innerHTML ='<font color=red size=2px>Please enter comments..</font>';
                    return false;
                }else if(document.getElementById('endDate').value.trim().length==0){
                    document.getElementById('resultMessage').innerHTML ='<font color=red size=2px>Please enter end date..</font>';
                    return false;
                }else if(document.getElementById('checkAlert').checked==true){
                    if(document.getElementById('endDateBillableOverlay').value.trim().length==0){
                        document.getElementById('resultMessage').innerHTML ='<font color=red size=2px>Please enter billable end date..</font>';
                        return false;
                    }
                    isValid=true;
                }else{
                  
                    isValid=true;
                }
                document.getElementById('comments').value=document.getElementById('comments').value + "\n" + "\n" + "Reason For Inactive :"+ "\n" +"---------------------" + "\n" + inActiveComments;
                document.getElementById('dateClosed').value= document.getElementById('endDate').value;
                if(document.getElementById('checkAlert').checked==true){
                     
                    document.getElementById('billableEndDate').value= document.getElementById('endDateBillableOverlay').value;
                }
                 
                //                    if(checkProjectTeamForm()){
                //                       // document.getElementById('comments').value='';
                //                        document.getElementById("projectResourceForm").submit();
                //   
                //                                     }
              
                if(isValid && fieldLengthValidator(comments)){
                    document.getElementById('comments').value= document.getElementById('comments').value;
                    document.getElementById('dateClosed').value= document.getElementById('endDate').value;
                    if(document.getElementById('checkAlert').checked==true){ 
                        document.getElementById('billableEndDate').value= document.getElementById('endDateBillableOverlay').value;
                    } 
                    if(checkProjectTeamForm()){
                        // document.getElementById('comments').value='';
                        document.getElementById("projectResourceForm").submit();
                    }
         
                }
  
            }


            function fieldLengthValidator(element){
    
                //alert("In Field Length validator OCV");
                var i = 0;
                if (element.value != null && (element.value != "")) {
                    if(element.id=='skillSet') i=500;
                    else if(element.id='comments') i=1500;
                }
                if(element.value.replace(/^\s+|\s+$/g,"").length>i){
       
                    subStringValue(i,element,"The "+element.id+" must be less than "+i+" characters");
                    return (false);
                }
                return (true);
    
            }

            function subStringValue(i,element,message) {
                str = new String(element.value);
                element.value=str.substring(0,i);
    
                alert(message);
                element.focus();
                return (false);
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

            div#overlay {
                display: none;
                z-index: 2;
                background: #000;
                position: fixed;
                width: 100%;
                height: 100%;
                top: 0px;
                left: 0px;
                text-align: center;
            }
            div#specialBox {
                display: none;
                position: absolute;
                z-index: 3;
                margin: 150px auto 0px auto;
                width: 750px; 
                height: auto;
                background: #FFF;

                color: #000;
            }
            div#wrapper {
                position:absolute;
                top: 0px;
                left: 0px;
                padding-left:24px;
            }

        </style>


    </head>
    <%!    String currentAction = null;
        int repeat = 0;

    %>
    <%



    %>
    <%!     /* Declarations */
        Connection connection;
        String accountPrimaryTeamMember;
        String userId;
        String userRoleName;
        int isUserManager;
        String queryString;
        String currentAccountId;
        String currentProjectId;
        String strTmp;
        String strSortCol;
        String strSortOrd;
        int intSortOrd = 0;
        int intCurr;
        boolean blnSortAsc = true;
    %>
    <!-- <body class="bodyGeneral" onload="init1('0');checkBillable(checkAlert);" oncontextmenu="return false;"> -->
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

                            <s:hidden name="actionType" id="actionType" value="%{currentAction}"/>
                            <!--//START DATA COLUMN : Coloumn for LeftMenu-->

                            <!--//START DATA COLUMN : Coloumn for Screen Content-->
                            <td width="850px" class="cellBorder" valign="top" style="padding:5px 5px;">

                                <span class="fieldLabel">Account Name :</span>&nbsp;
                                <%
                                    userRoleName = session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString();
                                    //System.out.println("1");
                                    if (("Employee".equalsIgnoreCase(userRoleName))) {
                                        if (request.getParameter("accountId") != null) {
                                            currentAccountId = (String) request.getParameter("accountId");
                                        }

                                        if (request.getParameter("projectId") != null) {
                                            currentProjectId = (String) request.getParameter("projectId");
                                        }

                                    }
                                    //System.out.println("2");
                                    //System.out.println(request.getParameter("accountId"));
                                    //System.out.println(currentProjectId);
%> 
                                <%
                                    if (("Employee".equalsIgnoreCase(userRoleName))) {
                                %>
                                <a class="navigationText" href="<s:url action="../employee/pmoActivity"></s:url>"><s:property value="#request.accountName"/></a>

                                    &nbsp;&nbsp;
                                    <span class="fieldLabel">Project Name :</span>&nbsp;
                                    <a class="navigationText" href="<s:url action="../employee/getCustomerProjectDetails"></s:url>?accountId=<s:property value="#request.accountId"/>&projectId=<s:property value="#request.projectId"/>&accountName=<s:property value="#request.accountName"/>&backFlag=<s:property value="#request.backFlag"/>"><s:property value="#request.projectName"/></a>    
                                <% } else {%>
                                <a href="<s:url action="../crm/accounts/getAccount">
                                       <s:param name="id" value="%{accountId}"/>
                                   </s:url>" class="navigationText"><s:property value="#request.accountName"/>
                                </a>&nbsp;&nbsp;
                                <span class="fieldLabel">Project Name :</span>&nbsp;
                                <a href="<s:url action="getProject">
                                       <s:param name="id" value="%{projectId}"/>
                                   </s:url>" class="navigationText"><s:property value="#request.projectName"/>
                                </a>
                                <% }%>

                                <ul id="accountTabs" class="shadetabs" >
                                    <s:if test="%{currentAction=='doAddProjectTeam'}">
                                        <li ><a href="#" class="selected" rel="issueTab"  >Add Project TeamMember </a></li>
                                    </s:if>
                                    <s:else>
                                        <li ><a href="#" class="selected" rel="issueTab"  >Edit Project TeamMember </a></li>
                                    </s:else>

                                </ul>
                                <!--//START TABBED PANNEL : -->
                                <div  style="border:1px solid gray; width:845px;height: 400px; overflow:auto; margin-bottom: 1em;">
                                    <!--//START TAB : -->

                                    <!-- Inactive reason overlay Start -->
                                    <div id="overlay"></div> 
                                    <div id="specialBox">
                                        <s:form theme="simple"  align="center" name="popupForm" id="popupForm">

                                            <s:hidden name="peopleId" id="peopleId"/>



                                            <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="headerLabel"></span>


                                                        </h3></td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <%--  <a href="#" onmousedown="toggleCloseOverlay()" >
                                                               <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  --%>

                                                    </td></tr>
                                                <tr>
                                                    <td colspan="4">
                                                        <div id="load" style="color: green;display: none;">Loading..</div>
                                                        <div id="resultMessage"></div>
                                                    </td>
                                                </tr>  
                                                <tr>
                                                    <td class="fieldLabel" >End&nbsp;Date<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td><s:textfield name="endDate" id="endDate" value="%{dateClosed}" cssClass="inputTextBlue"  onchange="checkDates(this);"/><a href="javascript:cal3.popup();"><img style="margin-bottom: -6px" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                                                                                                                                        width="20" height="20" border="0"></a></td>
                                                </tr>
                                                <s:if test="%{isBillable==true}">
                                                    <tr>
                                                        <td class="fieldLabel" >Billable&nbsp;End&nbsp;Date<FONT color="red"  ><em>*</em></FONT> </td>
                                                        <td><s:textfield name="endDateBillableOverlay" id="endDateBillableOverlay" value="%{billableEndDate}" cssClass="inputTextBlue"  onchange="checkDates(this);"/><a href="javascript:cal6.popup();"><img style="margin-bottom: -6px" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                                                                                                                                        width="20" height="20" border="0"></a></td>
                                                    </tr>
                                                </s:if>
                                                <tr><td colspan="4">
                                                        <table style="width:80%;" align="center">
                                                            <tr>
                                                                <td class="fieldLabel" >InActive&nbsp;Reason:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                <td colspan="3"><s:textarea id="inactiveComments" cssClass="inputTextarea" cols="100" name="inactiveComments" /></td>

                                                            </tr>


                                                            <tr > 
                                                                <td  colspan="3">
                                                                    <input type="button" value="Update" onclick="return doAppendComments();" class="buttonBg" id="updateButton"/> 
                                                                </td>
                                                            </tr>


                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>   
                                        <script type="text/JavaScript">
                                                        
                                            var cal3 = new CalendarTime(document.forms['popupForm'].elements['endDate']);
                                            cal3.year_scroll = true;
                                            cal3.time_comp = false;
                                                       
                                            //endDateBillableOverlay
                                            <s:if test="%{isBillable==true}">
                                                var cal6 = new CalendarTime(document.forms['popupForm'].elements['endDateBillableOverlay']);
                                                cal6.year_scroll = true;
                                                cal6.time_comp = false;
                                            </s:if>
  
                                        </script>
                                    </div>
                                    <!-- Inactive reason overlay Stop -->
                                    <s:if test="%{temp==1}">
                                        <div id="issueTab" class="tabcontent" style="width: 92%;" >
                                        </s:if>
                                        <s:else>
                                            <div id="issueTab" class="tabcontent" style="width: 92%;" >
                                            </s:else>
                                            <s:form name="projectResourceForm" id="projectResourceForm" action="%{currentAction}" method="POST"  theme="simple" onsubmit="return checkProjectTeamForm();">
                                                <table width="100%" cellpadding="2" cellspacing="2" border="0" >
                                                    <tr align="right">
                                                        <td class="headerTextForDualReportsToProjectAdd" colspan="4" >
                                                            <%
                                                                if (request.getAttribute("resultMessage") != null) {
                                                                    out.println(request.getAttribute("resultMessage").toString());

                                                                }
                                                            %>

                                                            <s:hidden name="accountId" value="%{accountId}" id="accountId"/>
                                                            <s:hidden name="id" id="id" value="%{id}"/>
                                                            <s:hidden name="projectId" value="%{projectId}" id="projectId"/>
                                                            <s:hidden id="currentAction" name="currentAction" value="%{currentAction}"/>
                                                            <s:hidden name="temp" id="temp" value="%{temp}"/>
                                                            <s:hidden id="projectStartDate" name="projectStartDate" value="%{projectStartDate}"/>
                                                            <s:hidden name="existedStatus" id="existedStatus" value="%{status}"/>
                                                            <input type="hidden" name="backFlag" value="<%=request.getParameter("backFlag")%>"/>
                                                            <%
                                                                if (session.getAttribute("resultMsg") != null) {
                                                                    out.println(session.getAttribute("resultMsg"));
                                                                    session.removeAttribute("resultMsg");
                                                                }
                                                            %>
                                                            <s:if test="%{temp==1}">  <div style="margin-right: 0%;"> </s:if>
                                                                <s:else>  <div style="margin-right: 0%;"> </s:else>
                                                                    <%-- <s:if test="%{currentAction == 'doAddProjectTeam'}">
                                                                         <%
                                                                         if (("Employee".equalsIgnoreCase(userRoleName))){
                                                                             String nav = "../employee/viewProjectTeam.action?projectId="+currentProjectId+"&accountId="+currentAccountId+"";
                                                                     %>   
                                                                        <a href="<%=nav%>" style="align:center;">
                                                                            <img alt="Back to List"
                                                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                                 width="66px" 
                                                                                 height="19px"
                                                                             border="0" align="center"></a>
                                                                                 <%}%>
                                                                      <s:submit label="Submit" value="Save" cssClass="buttonBg" /> 
                                                                    </s:if> --%>

                                                                    <s:if test="%{currentAction == 'doUpdateProjectTeam'}">
                                                                        <%
                                                                            if ("Employee".equalsIgnoreCase(userRoleName) && !"1".equals(request.getParameter("backFlag"))) {
                                                                                String nav = "../employee/getProjectTeamQuery.action?projectId=" + currentProjectId + "&accountId=" + currentAccountId + "&customerName=" + request.getAttribute("accountName");
                                                                        %> 
                                                                        <a href="<%=nav%>" style="align:center;">
                                                                            <img alt="Back to List"
                                                                                 src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                                 width="66px" 
                                                                                 height="19px"
                                                                                 border="0" align="center"></a>
                                                                            <%}%>

                                                                        <!--  <a href="getProject.action?id=" style="align:center;">
                                                                              <img alt="Back to List"
                                                                                   src=" <%-- <s:url value="/includes/images/backToList_66x19.gif"/>" --%>
                                                                                   width="66px"
                                                                                   height="19px"
                                                                               border="0" align="bottom"></a> -->
                                                                    </s:if> 
                                                                </div> 				
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4">

                                                            <div id="resultMessage1"></div>
                                                        </td></tr>

                                                    <tr>


                                                    <tr>
                                                        <%--<td class="fieldLabel" valign="top">Resource Type : <FONT color="red" ><em>*</em></FONT></td>
                                                        <td  colspan="4" class="fieldLabelLeft"><s:select list="{'Employee','Consultant','Customer/Vendor Contact'}"
                                                                      name="severityId"
                                                                        value="%{currentTask.severityId}"
                                                                        cssClass="inputSelect"  /></td>
                                                        <td class="fieldLabel">Resource Title :</td> --%>
                                                        <td width="200px" class="fieldLabel">Resource&nbsp;Type&nbsp;: <FONT color="red"  ><em>*</em></FONT></td>
                                                        <td width="1%">
                                                            <s:select list="#@java.util.LinkedHashMap@{'E':'Employee','C':'Customer Contact','V':'Vendor Contact'}"
                                                                      name="resType" id="resType"
                                                                      value="%{resType}"
                                                                      cssClass="inputSelectForProjects"  />

                                                        </td>
                                                        <td class="fieldLabel" >Resource&nbsp;Name&nbsp;: <FONT color="red"  ><em>*</em></FONT></td>
                                                        <s:if test="%{currentAction == 'doAddProjectTeam'}">

                                                            <td ><s:textfield name="assignedToUID" id="assignedToUID" value="%{assignedToUID}" onchange="checkName();"  onkeyup="fillEmployeeForProject();"  cssClass="inputTextBlue"  theme="simple" readonly="false"/><span style="color: green;font-size: 12px;" id="displayEmpEmail" ></span></td>
                                                        </s:if>
                                                        <s:else>
                                                            <td>  <s:textfield name="assignedToUID" id="assignedToUID" value="%{assignedToUID}"   cssClass="inputTextBlue"  theme="simple" readonly="true"/> </td>
                                                        </s:else>

                                                    <div id="authorEmpValidationMessage" style="position: absolute; overflow:hidden;"></div>  
                                                    <s:hidden name="preAssignEmpId" value="%{preAssignEmpId}" id="preAssignEmpId"/>  

                                                    </tr>
                                                    <tr>
                                                        <td width="200px" class="fieldLabel">Role : <FONT color="red"  ><em>*</em></FONT></td>
                                                        <td width="1%">
                                                            <s:select list="rolesMap"
                                                                      name="resTitle"
                                                                      value="%{resTitle}" id="resTitle"
                                                                      cssClass="inputSelectForProjects"  />
                                                        </td>
                                                        <s:if test="%{currentAction=='doAddProjectTeam'}">
                                                            <td class="fieldLabel">Status :</td>
                                                            <td><s:select list="{'Active'}"
                                                                      name="status"
                                                                      id="status"
                                                                      value="%{status}"
                                                                      cssClass="inputSelect" onchange="showReason();" />  </td>

                                                        </s:if>
                                                        <s:else>
                                                            <td class="fieldLabel">Status :</td>
                                                            <td><s:select list="{'Active','InActive'}"
                                                                      name="status"
                                                                      id="status"
                                                                      value="%{status}"
                                                                      cssClass="inputSelect" onchange="getInActiveDetails();" />  </td>

                                                        </s:else>


                                                    </tr>
                                                    <s:if test="%{temp==1}">

                                                        <tr><td class="fieldLabel" ><b>Reports&nbsp;To&nbsp;</b></td></tr>

                                                        <tr>


                                                            <td class="fieldLabel">Primary&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                            <td width="1%">
                                                                <s:select list="projectTeamReportsTo"
                                                                          name="projectReportsTo" id="projectReportsTo" headerKey="-1" headerValue="--Please Select--"
                                                                          value="%{projectReportsTo}"
                                                                          cssClass="inputSelectForProjects2"  />
                                                            </td>
                                                            <td class="fieldLabel">Secondary&nbsp;:</td>
                                                            <td width="1%">
                                                                <s:select list="projectTeamReportsTo"
                                                                          name="secProjectReportsTo" headerKey="-1" headerValue="--Please Select--"
                                                                          value="%{secProjectReportsTo}" id="secProjectReportsTo"
                                                                          cssClass="inputSelectForProjects1"  />
                                                            </td>
                                                        </tr>




                                                        <tr>
                                                            <td class="fieldLabel">Rate&nbsp;Type&nbsp;:</td>
                                                            <td><s:select list="{'Per Hour','Per Day','Per Month','Fixed Bid'}"
                                                                      name="rateType" id="rateType"
                                                                      value="%{rateType}"
                                                                      cssClass="inputSelect"  />  </td>

                                                        </tr>

                                                    </s:if><s:else>




                                                        <tr>


                                                            <td width="200px" class="fieldLabel">Reports&nbsp;To&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                            <td width="1%">
                                                                <s:select list="projectTeamReportsTo"
                                                                          name="projectReportsTo" id="projectReportsTo" headerKey="-1" headerValue="--Please Select--"
                                                                          value="%{projectReportsTo}"
                                                                          cssClass="inputSelectForProjects"  />
                                                            </td>
                                                            <td class="fieldLabel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rate&nbsp;Type&nbsp;:</td>
                                                            <td><s:select list="{'Per Hour','Per Day','Per Month','Fixed Bid'}"
                                                                      name="rateType" id="rateType"
                                                                      value="%{rateType}"
                                                                      cssClass="inputSelect"  />  </td>

                                                        </tr> 
                                                    </s:else>

                                                    <%--    <tr><td class="fieldLabel" ><b>Reports&nbsp;To&nbsp;</b></td></tr>
                                                <tr>
                                                      
                                                       <td width="200px" class="fieldLabel">Primary&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                          <td width="1%">
                                                              <s:select list="projectTeamReportsTo"
                                                                        name="projectReportsTo" id="projectReportsTo" headerKey="-1" headerValue="--Please Select--"
                                                                          value="%{projectReportsTo}"
                                                                          cssClass="inputSelectForProjects"  />
                                                          </td>
                                                           <td width="200px" class="fieldLabel">Secondary&nbsp;:</td>
                                                          <td width="1%">
                                                              <s:select list="projectTeamReportsTo"
                                                                        name="secProjectReportsTo" headerKey="-1" headerValue="--Please Select--"
                                                                          value="%{secProjectReportsTo}" id="secProjectReportsTo"
                                                                          cssClass="inputSelectForProjects"  />
                                                          </td>
                                                  </tr> --%>


                                                    <tr>
                                                        <%--   <td class="fieldLabel">Rate&nbsp;Type&nbsp;:</td>
                                                       <td><s:select list="{'Per Hour','Per Day','Per Month','Fixed Bid'}"
                                                                         name="rateType"
                                                                           value="%{rateType}"
                                                                       cssClass="inputSelect"  />  </td> --%>

                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel" valign="top">Utilization&nbsp;: <FONT color="red"  ><em>*</em></FONT></td>
                                                        <s:hidden name="availableUtl"   value="%{availableUtl}"  id="availableUtl" />
                                                        <s:if test="%{currentAction=='doAddProjectTeam'}">
                                                            <td>
                                                                <s:hidden name="hidutilisation" id="hidutilisation" value="0" />

                                                                <s:textfield name="utilisation" id="utilisation" value="%{utilisation}"  onkeypress="return isNumber(event)" onchange="checkValue();" size="2" cssClass="inputTextBlue2"/><s:label cssClass="fieldLabel" value="%"/><b class="hiddenLabel" id="availUtl" >  </b> </td>

                                                        </s:if>
                                                        <s:else>
                                                            <s:hidden name="hidutilisation" id="hidutilisation" value="%{utilisation}" />
                                                            <td>   <s:textfield name="utilisation" id="utilisation" value="%{utilisation}"  onkeypress="return isNumber(event)" onchange="checkValue();" size="2" cssClass="inputTextBlue2"/><s:label cssClass="fieldLabel" value="%"/>
                                                                <b class="hiddenLabel" id="availUtl" >Avl.Utilization:<s:property value="availableUtl"/> </b>

                                                            </td>
                                                        </s:else>
                                                        <td class="fieldLabel">Currency: <FONT color="red" SIZE="0.5"><em>*</em></FONT></td>
                                                        <td  valign="top">

                                                            <s:select list="clientCurrencyMap" name="currency" id="currency" value="%{currency}" headerKey="USD" headerValue="USD" cssClass="inputSelectSmallCurr" /><%--<FONT color="red" SIZE="0.5"><em>*</em></FONT> --%>
                                                            <%-- <s:submit value="...."  cssClass="buttonBg" onclick="return currencyPopup('../crm/greensheets/CurrencyInfo.jsp')" align="left"/> --%>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td class="fieldLabel">Work-Phone No :</td>
                                                        <td><s:textfield name="workPhone"  cssClass="inputTextBlue" onchange="return formatPhone(this);"  id="workPhone" size="16"/></td>
                                                        <td class="fieldLabel">Mobile No :</td>
                                                        <td><s:textfield name="mobilePhone"  cssClass="inputTextBlue" onchange="return formatPhone(this);"  id="mobilePhone" size="16"/></td>
                                                    </tr>
                                                    <tr>

                                                        <td class="fieldLabel" >Start&nbsp;Date&nbsp;:</td>
                                                        <td>

                                                            <s:textfield name="dateAssigned" id="dateAssigned" value="%{dateAssigned}"  onchange="checkDates(this);"
                                                                         cssClass="inputTextBlue"  />
                                                            <a href="javascript:cal1.popup();"><img style="margin-bottom: -6px" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                                    width="20" height="20" border="0"></a>
                                                        </td>
                                                        <td class="fieldLabel">End&nbsp;Date&nbsp;:</td>
                                                        <td  width="30%">

                                                            <s:textfield name="dateClosed" id="dateClosed" value="%{dateClosed}" cssClass="inputTextBlue"  onchange="checkDates(this);"
                                                            /><a href="javascript:cal2.popup();"><img style="margin-bottom: -6px" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                                    width="20" height="20" border="0"></a>
                                                        </td>
                                                    </tr>


                                                    <tr>

                                                        <td class="fieldLabel"> Is Primary :</td>
                                                        <td><s:checkbox   id="isPrimary"  name="isPrimary" value="%{isPrimary}" onclick="checkPrimary(this);"/></td>
                                                        <td class="fieldLabel" >Employee&nbsp;State:<FONT color="red"  ><em>*</em></FONT></td>
                                                        <td ><s:select list="empProjectRolls" name="empProjectStatus" id="empProjectStatus" onclick="checkProjectStatus(this);" headerKey="-1" headerValue="--Please Select--" value="%{empProjectStatus}" cssClass="inputTextBlue"  theme="simple"/></td>

                                                    </tr>
                                                    <tr id="isMainTr" style="visibility: hidden;">
                                                        <td class="fieldLabel"> Is Billable :</td>
                                                        <td><s:checkbox   id="checkAlert"  name="isBillable" value="%{isBillable}" onclick="checkBillable(this);"/></td>
                                                    </tr>


                                                    <tr id="isBillableTr">

                                                        <td class="fieldLabel" >Billable&nbsp;Start&nbsp;Date&nbsp;:</td>
                                                        <td>

                                                            <s:textfield name="billableStartDate" id="billableStartDate" value="%{billableStartDate}" cssClass="inputTextBlue"  onchange="checkDates(this);"
                                                                         />
                                                            <a href="javascript:cal4.popup();"><img style="margin-bottom: -6px" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                                    width="20" height="20" border="0"></a>
                                                        </td>
                                                        <td class="fieldLabel">Billable&nbsp;End&nbsp;Date&nbsp;:</td>
                                                        <td  width="30%">

                                                            <s:textfield name="billableEndDate" id="billableEndDate" value="%{billableEndDate}" cssClass="inputTextBlue"  onchange="checkDates(this);"
                                                            /><a href="javascript:cal5.popup();"><img style="margin-bottom: -6px" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif"
                                                                                                    width="20" height="20" border="0"></a>
                                                        </td>
                                                    </tr>


                                                    <%--  <tr>
                                                        <td class="fieldLabel"> Is PMO :</td>
                                                        <td><s:checkbox   id="checkAlert"  name="isPMO" value="%{isPMO}"/></td>
                                                    </tr> --%>
                                                    <tr>
                                                        <td class="fieldLabel" > Skill Set :</td>
                                                        <td colspan="3"><s:textarea id="skillSet" cssClass="inputTextarea" onkeypress="fieldLengthValidator(this);"  cols="88" value="%{skillSet}"  name="skillSet" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabel" id="commentsField"> Comments :</td>
                                                        <td colspan="3"><s:textarea id="comments" cssClass="inputTextarea" onkeypress="fieldLengthValidator(this);"  cols="100" value="%{comments}"  name="comments" /></td>
                                                    </tr>
                                                    </tr>

                                                    <tr>
                                                        <td></td>
                                                        <td></td>

                                                        <td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                         



                                                            <div id="doUpdateDiv" style="display:none;">
                                                                <a href="<s:url action="addProjectTeam.action">
                                                                       <s:param name="id" value="%{id}"/>
                                                                       <s:param name="accountId" value="%{accountId}"/>
                                                                       <s:param name="projectId" value="%{projectId}"/>
                                                                       <s:param name="backFlag" value="%{backFlag}"/>

                                                                   </s:url>" style="align:center;">
                                                                    <img alt="Add New Member" 
                                                                         src="  <s:url value="/includes/images/add_new_member.png"/>" 

                                                                         border="0" align="center"></a> 
                                                                    <s:submit label="Submit" value="Update" cssClass="buttonBg" />
                                                            </div>   

                                                            <s:if test="%{currentAction == 'doUpdateProjectTeam'}">

                                                                <a href="<s:url action="addProjectTeam.action">
                                                                       <s:param name="id" value="%{id}"/>
                                                                       <s:param name="accountId" value="%{accountId}"/>
                                                                       <s:param name="projectId" value="%{projectId}"/>
                                                                       <s:param name="backFlag" value="%{backFlag}"/>

                                                                   </s:url>" style="align:center;">
                                                                    <img alt="Add New Member" 
                                                                         src="  <s:url value="/includes/images/add_new_member.png"/>" 

                                                                         border="0" align="center"></a> 
                                                                    <s:submit label="Submit" id="buttonId" value="Update" cssClass="buttonBg" />
                                                                </s:if> 

                                                            <%--  <s:if test="%{currentAction == 'doUpdateProjectTeam'}">
                                                              <%
                                                              if (("Employee".equalsIgnoreCase(userRoleName))){
                                                                  String nav = "../employee/viewProjectTeam.action?projectId="+currentProjectId+"&accountId="+currentAccountId+"";
                                                         %> 
                                                           <a href="<%=nav%>" style="align:center;">
                                                                 <img alt="Back to List"
                                                                      src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                      width="66px" 
                                                                      height="19px"
                                                                  border="0" align="center"></a>
                                                                      <%}%>
                                                             <s:submit label="Submit" value="Update" cssClass="buttonBg" />
                                                             
                                                         </s:if> --%>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td>

                                                            <div id="doAddDiv" style="display:none;">
                                                                <%
                                                                    if ("Employee".equalsIgnoreCase(userRoleName) && !"1".equals(request.getParameter("backFlag"))) {
                                                                        String nav = "../employee/getProjectTeamQuery.action?projectId=" + currentProjectId + "&accountId=" + currentAccountId + "&customerName=" + request.getAttribute("accountName");;
                                                                %>   
                                                                <a href="<%=nav%>" style="align:center;">
                                                                    <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                         width="66px" 
                                                                         height="19px"
                                                                         border="0" align="center"></a>
                                                                    <%}%>
                                                                    <s:submit label="Submit" id="buttonId" value="Save" cssClass="buttonBg"/>
                                                            </div>

                                                            <s:if test="%{currentAction == 'doAddProjectTeam'}">
                                                                <%
                                                                    if ("Employee".equalsIgnoreCase(userRoleName) && !"1".equals(request.getParameter("backFlag"))) {
                                                                        String nav = "../employee/getProjectTeamQuery.action?projectId=" + currentProjectId + "&accountId=" + currentAccountId + "&customerName=" + request.getAttribute("accountName");;
                                                                %>   
                                                                <a href="<%=nav%>" style="align:center;">
                                                                    <img alt="Back to List"
                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                         width="66px" 
                                                                         height="19px"
                                                                         border="0" align="center"></a>
                                                                    <%}%>
                                                                    <s:submit label="Submit" value="Save" cssClass="buttonBg"/>
                                                                </s:if>
                                                        </td>
                                                    </tr>




                                                </table>
                                            </s:form>



                                            <script>
                                                var cal1 = new CalendarTime(document.forms['projectResourceForm'].elements['dateAssigned']);
                                                cal1.year_scroll = true;
                                                cal1.time_comp = false;
                                                           
                                                var cal2 = new CalendarTime(document.forms['projectResourceForm'].elements['dateClosed']);
                                                cal2.year_scroll = true;
                                                cal2.time_comp = false;
                                                var cal4 = new CalendarTime(document.forms['projectResourceForm'].elements['billableStartDate']);
                                                cal4.year_scroll = true;
                                                cal4.time_comp = false;
                                                           
                                                var cal5 = new CalendarTime(document.forms['projectResourceForm'].elements['billableEndDate']);
                                                cal5.year_scroll = true;
                                                cal5.time_comp = false;  
                                                        
                                                        
                                            </script>
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
                init1('0');
                checkBillable(checkAlert);
                checkEmpProjectStatus();
            });
        </script>
    </body>
</html>