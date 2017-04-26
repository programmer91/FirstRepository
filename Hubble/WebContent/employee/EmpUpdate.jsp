<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
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
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/helpText.css"/>">  
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js?version=1.9"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpDetailsValidation.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeLocation.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>

    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script>
        $(function() {
            $( document ).tooltip();
        });
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

    <script type="text/JavaScript">
        
        
          
        function checkDate() {
            var startDate = document.getElementById('startDate').value;
            var endDate = document.getElementById('endDate').value;
            var empStatus=document.getElementById('empStatus').value;
            var mm = startDate.substring(0,2);
            var dd = startDate.substring(3,5);
            var yyyy = startDate.substring(6,10);
            var mm1 = endDate.substring(0,2);
            var dd1 = endDate.substring(3,5);
            var yyyy1 = endDate.substring(6,10);
            
            if(startDate == null || startDate == ''){
                alert("StartDate should not be empty");
                return false;
            }
            
            if(empStatus=='Completed'){
                //  alert("in complete");
                if(endDate == null || endDate == ''){
                    alert(" End date is mandatory for the status 'Completed'");
                    return false;
                }
            }
            if(document.getElementById("startDate").value != '' && document.getElementById("endDate").value != '') {
                if(yyyy1 < yyyy) {
                    alert('Start Date is older than End Date');
                    document.getElementById('endDate').value = '';
                    return false;
                }
                else if((yyyy1 == yyyy) && (mm1 < mm)) {
                    alert('Start Date is older than End Date');
                    document.getElementById('endDate').value = '';
                    return false;
                }
                else if((yyyy1 == yyyy) && (mm1 == mm) && (dd1 < dd)) {
                    alert('Start Date is older than End Date');
                    document.getElementById('endDate').value = '';
                    return false;
                }
            }
        }
        //For Adding & Editing Employee Status
        function setTempVar1() {
            document.empCurrState.tempVar.value = 1;
        }
        function setTempVar2() {
            document.empCurrState.tempVar.value = 2;
        }
        //new
        function fileValidation() {
            var imagePath = document.imageForm.imagePath;
            if(imagePath.value != null && (imagePath.value !="")) {
                document.imageForm.imagePath.focus();
                return (true);
            }
            return (false);
        }
           
        function territoryPopup(url) {
            newwindow=window.open(url,'name','height=600,width=500,top=200,left=250');
            if (window.focus) {newwindow.focus()}
        }
          
          




        function confdentialInfoDisplay() {
            var livingCountry = document.getElementById("country").value;
            //alert(livingCountry);
            if(livingCountry == 'India')
            {
      

                document.getElementById('confdentialInfo').style.display = "block";
            }
            else
            {
                document.getElementById('confdentialInfo').style.display = 'none';
            }
 
        }


        function isPhysicallyDisabled() {
            var physicallyDisabled = document.getElementById('phyChallenged').value;
            if(physicallyDisabled=='N') {
                document.getElementById("phyCategory").value ="";
                document.getElementById('phyCategory').disabled = true;
            }else {
                document.getElementById('phyCategory').disabled = false;
            }
        }


        function getCurrentEmployeeReport()
        {
            
            var loginId  = document.getElementById('loginId').value;
            var id  = document.getElementById('id').value;
            window.location="getCurrentEmployeeReport.action?loginId="+loginId+"&empId="+id;
                      
            return true;    
                
        }
        function showAddStatus(){
            //  alert("showAddStatus");
            var empState  = document.getElementById('empState').value;
            //  alert(empState);
            if( empState=='OnProject'){
                
                document.getElementById('addStatus').style.display='none';
            }
            else{
                document.getElementById('addStatus').style.display='';
            }
            //   showComments();
            showAttachment();
        }
        function showAttachment(){
            var empState  = document.getElementById('empState').value;
          
            
            if(empState == 'Available'){
                document.getElementById('showAttachment').style.display='';
            }
            else{
                document.getElementById('showAttachment').style.display='none';
            }
        }
      

        function showPrevExp(obj)
        {
   
            var prevYears1 =   document.getElementById("prvexpYears1").value;
            var prevMonths1  =  document.getElementById("prvexpMnths1").value;
            if(obj.checked==true){
                document.getElementById("prvExp").style.display='';
                document.getElementById("prvExpRow").style.display='';
                document.getElementById("prvexpYears").value=prevYears1;
                document.getElementById("prvexpMnths").value=prevMonths1;
     
            } 
            else{
                document.getElementById("prvExp").style.display='none';
                document.getElementById("prvExpRow").style.display='none';
                document.getElementById("prvexpYears").value='';
                document.getElementById("prvexpMnths").value='';
      
            }
        }


        function getAlertMessage(){
            alert("Resume does not exist");
        }
        function checkFileName()
        {
            // alert("putFileName");
            var imagePath =document.getElementById('attachmentFileName').value;
            // alert(imagePath);
            if(imagePath.length<30){
                // alert("imagePath-->"+imagePath);
                if(imagePath != null && (imagePath !="")) {
                    

                    var extension = imagePath.substring(imagePath.lastIndexOf('.')+1);

                    if(extension=="pdf"||extension=="odt"|| extension=="rtf"||extension=="docx"||extension=="doc"||extension=="txt"){
                        // document.imageForm.imagePath.focus();
                        var size = document.getElementById('attachmentFileName').files[0].size;
                        //alert("size-->"+size);
                        // alert("size in mb-->"+(parseInt(size)/1000000));
                        //if((parseInt(size)/1000000)<2) {
                        if(parseInt(size)<2000000) {
                            return (true);
                        }else {
                            document.getElementById('attachmentFileName').value = "";
                            // alert("File size must be less than 1 MB.");
                            alert("File size must be less than 2 MB.");
                            return (false);
                        }
                    }else {
                        document.getElementById('attachmentFileName').value = "";
                        //    document.getElementById('resultMessage').innerHTML = "<font color=red>Invalid file extension!Please select pdf or jpg or jpeg or png file.</font>"
                        alert("Invalid file extension!Please select pdf or rtf or odt or docx or doc or txt file.");
                        return (false);
                    }
                }
            }else {
                //  document.getElementById('resultMessage').innerHTML = "<font color=red>File name must be less than 30 characters!</font>"
                alert("File name must be less than 30 characters!");
                document.getElementById('attachmentFileName').value = "";
            }
            return (false);

        }

        function conformLateralFlag(){
            var lateralFlag = document.getElementById("lateralFlag");
            var prevYears1 =   document.getElementById("prvexpYears1").value;
            var prevMonths1  =  document.getElementById("prvexpMnths1").value;
            if(lateralFlag.checked==false){
                var result = confirm("Do you want to uncheck the IsLateral Flag ?");
                if(result){
                    //  alert("ok"); 
                    document.getElementById("prvExp").style.display='none';
                    document.getElementById("prvExpRow").style.display='none';
                    document.getElementById("prvexpYears").value='';
                    document.getElementById("prvexpMnths").value='';
                }
                else{
                    // alert("cancel");
                    lateralFlag.checked=true;
                    document.getElementById("prvExp").style.display='';
                    document.getElementById("prvExpRow").style.display='';
                    document.getElementById("prvexpYears").value=prevYears1;
                    document.getElementById("prvexpMnths").value=prevMonths1;
                }
            }
        } 

    </script>
    <s:include value="/includes/template/headerScript.html"/>
</head>
<%-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="validateCtc();getLocation();"> --%>
<%-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="isPhysicallyDisabled();confdentialInfoDisplay();showAddStatus();showPrevExp(lateralFlag)"> --%>
<body  class="bodyGeneral" oncontextmenu="return false;">

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
            <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px 5px 5px;">
                <ul id="accountTabs" class="shadetabs" >
                    <li ><a href="#" class="selected" rel="personalDetailsTab"  >Employee Details</a></li>
                    <li ><a href="#" rel="empCurrentState">Current Status</a></li>
                    <li ><a href="#" rel="otherDetailsTab">Other Details</a></li>
                    <li ><a href="#" rel="imageUploadTab">Image Upload</a></li>
                </ul>
                <!--//START TABBED PANNEL : -->

                <!--//START TABBED PANNEL : -->
                <%--    <sx:tabbedpanel id="employeeUpdatePannel" cssStyle="width:850px; height:510px;padding: 5px 5px 5px 5px;" doLayout="true" useSelectedTabCookie="true">--%>
                <div  style="border:1px solid gray; width:850px;height: 510px; overflow:auto; margin-bottom: 1em;">
                    <!--//START TAB : -->
                    <%-- <sx:div id="personalDetailsTab" label="Employee Details" cssStyle="overflow:auto;" > --%>
                    <div id="personalDetailsTab" class="tabcontent" >

                        <!-- Termination Details overlay start -->
                        <div id="overlay1"></div> 
                        <div id="specialBox1" style="margin-left: 10vw;">
                            <s:form theme="simple"  align="center" name="terminate">
                                <s:hidden id="employeeId" name="employeeId"/>
                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                    <tr>                               
                                    <td colspan="2" style="background-color: #288AD1" >
                                        <h3 style="color:darkblue;" align="left">
                                            <span id="headerLabel1"></span>


                                        </h3></td>
                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                        <a href="#" onmousedown="toggleCloseUploadOverlayEditPage()" >
                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                        </a>  

                                    </td></tr>
                                    <tr>
                                    <td colspan="4">
                                        <div id="load" style="color: green;display: none;">Loading..</div>
                                        <div id="resultMessage2"></div>
                                    </td>
                                    </tr>    
                                    <tr><td colspan="4">
                                        <table style="width:80%;" align="center">
                                            <tr style="font-family: arial,verdana; font-size:12px;" >
                                            <td class="fieldLabel" >Name :</td>
                                            <td align="left" class="fieldLabelLeft">
                                            <span id="employeeName"></span>
                                    </td> 
                                    </tr>

                                    <tr style="font-family: arial,verdana; font-size:12px;">

                                    <td  class="fieldLabel" >Designation :</td>
                                    <td><s:textfield name="designation" id="designation" cssClass="inputTextBlueLarge"  readonly="true"/></td>

                                    </tr>

                                    <tr style="font-family: arial,verdana; font-size:12px;">
                                    <td class="fieldLabel">Date of Join :</td>
                                    <td> <s:textfield name="dateOfJoin" id="dateOfJoin" cssClass="inputTextBlueLarge" readonly="true"/>
                                    </td>
                                    </tr>

                                    <tr style="font-family: arial,verdana; font-size:12px;">
                                    <td class="fieldLabel">Date of Termination :</td>
                                    <td><s:textfield name="dateOfTermination" id="dateOfTermination" cssClass="inputTextBlueLarge"/>
                                        <a href="javascript:calTerminate.popup();">
                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0" style="margin-bottom: -4px"></a> 
                                    </td>
                                    </tr>
                                    <tr style="font-family: arial,verdana; font-size:12px;">
                                    <td class="fieldLabel" >Reasons for Exit :</td>  
                                    <td ><s:textarea name="reasonsForTerminate" id="reasonsForExit" rows="4" cols="33" cssClass="inputTextArea" /></td>
                                    </tr>
                                    <tr>
                                    <td align="right" colspan="5">
                                        <input type="button" label="Submit" value="Save" class="buttonBg" onclick="doSaveTermainationDetails();" style="margin-right: 30px;"/>
                                    </td>
                                    </tr>
                                </table>
                                </td>
                                </tr>
                                </table>
                            </s:form>   
                            <script type="text/JavaScript">
                                                          
                                var calTerminate = new CalendarTime(document.forms['terminate'].elements['dateOfTermination']);
                                calTerminate.year_scroll = true;
                                calTerminate.time_comp = false;
                            </script>  
                        </div>


                        <!-- Termination details overlay end -->
                        <s:form name="employeeForm" id="employeeForm" action="employeeUpdate" method="post" theme="simple" onsubmit="return empUpdateValidation();">

                            <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                <tr>
                                <td colspan="6">
                                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                        <tr>
                                        <td align="left" class="headerText">Personal Details </td>

                                        <td align="right" class="headerText">
                                            <table cellpadding="0" cellspacing="0" border="0">
                                                <tr>
                                                <td align="left">
                                                    <% if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                            out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));

                                                        }%>
                                                </td>

                                                <s:if test="#request.resultMessage!=null">
                                                    <td valign="middle">
                                                        <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-2)">  
                                                    </td>
                                                </s:if>
                                                <s:else>
                                                    <td valign="middle">
                                                        <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                                    </td>
                                                </s:else>
                                                <td valign="top">
                                                    <input type="button" name="button"  value="Update" class="buttonBg" onclick="checkIfStatusInActive();"/>
                                                    <%--  <s:submit name="submit" value="Update" cssClass="buttonBg" onclick="checkIfStatusInActive();"/>  --%>
                                                    <s:hidden name="id" value="%{currentEmployee.id}"/>
                                                    <s:hidden name="loginId" value="%{currentEmployee.loginId}"/>

                                                    <s:hidden name="preCurrStatus" id="preCurrStatus" value="%{currentEmployee.currStatus}"/>
                                                    <s:hidden name="createdBy" value="%{currentEmployee.createdBy}"/>
                                                    <s:hidden name="createdDate" value="%{currentEmployee.createdDate}"/>
                                                    <s:hidden name="prjName" value="%{currentEmployee.prjName}"/>
                                                    <s:hidden name="empState" value="%{currentEmployee.empState}"/>

                                                    <s:hidden name="skillSet" value="%{currentEmployee.skillSet}"/>
                                                    <s:hidden name="comments" value="%{currentEmployee.comments}"/>
                                                    <s:hidden name="stateStartDate" value="%{currentEmployee.stateStartDate1}"/>
                                                    <s:hidden name="stateEndDate" value="%{currentEmployee.stateEndDate1}"/>
                                                    <s:hidden name="ctcPerYear" value="%{currentEmployee.ctcPerYear}"/>

                                                    <s:hidden name="invRatePerHour" value="%{currentEmployee.invRatePerHour}"/>
                                                    <s:hidden name="reasonsForTerminate" id="reasonsForTerminate" value="%{currentEmployee.reasonsForTerminate}"/>
                                                    <s:hidden  name="dateOfTermination" id="dateOfTermination" value="%{currentEmployee.dateOfTermination}" />                           


                                                    <%-- new --%>



                                                </td>
                                                </tr>
                                            </table>
                                        </td>
                                        </tr>
                                    </table>
                                </td>
                                </tr>

                                <tr>
                                <td class="fieldLabel" width="200px" align="right">First Name :</td>                           

                                <td><s:textfield name="firstName" id="firstName" cssClass="inputTextBlueNew" value="%{currentEmployee.firstName}" onchange="fieldLengthValidator(this);"/></td>      

                                <td class="fieldLabel" width="200px" align="right">Last Name :</td>

                                <td><s:textfield name="lastName" id="lastName" cssClass="inputTextBlueNew" value="%{currentEmployee.lastName}" onchange="fieldLengthValidator(this);"/></td>

                                <td class="fieldLabel" width="200px" align="right">Middle Name :</td>                           

                                <td><s:textfield name="middleName" id="middleName" cssClass="inputTextBlueNew" value="%{currentEmployee.middleName}" onchange="fieldLengthValidator(this);"/></td>               
                                </tr>
                                <tr>
                                <td class="fieldLabel" width="200px" align="right">Alias Name :</td>                           

                                <td><s:textfield name="aliasName" id="aliasName" cssClass="inputTextBlueNew" size="20" value="%{currentEmployee.aliasName}" onchange="fieldLengthValidator(this);"/></td> 

                                <td class="fieldLabel" width="200px" align="right">Gender :</td>                           

                                <td><s:select label="Select Gender" 
                                          name="gender" 
                                          list="genderList" cssClass="inputSelectNew" value="%{currentEmployee.gender}"/>
                                </td>                                                      

                                <td class="fieldLabel" width="200px" align="right">Marital Status :</td>

                                <td><s:select label="Select Marital Status" 
                                          name="maritalStatus" 
                                          list="maritalStatusList" cssClass="inputSelectNew" value="%{currentEmployee.maritalStatus}"/></td>
                                </tr>
                                <tr>
                                <td class="fieldLabel" width="200px" align="right">Live in :</td>                           

                                <td><s:select label="Select Country" 
                                          name="country" id="country" headerKey=""            
                                          headerValue="-- Please Select --"
                                          list="countryList" cssClass="inputSelectNew" value="%{currentEmployee.country}" onchange="getLocationsByCountry(this);"/></td>                  

                                <%--   <s:if test="%{#session.userId =='plokam' || #session.userId =='mlokam' || #session.userId =='sratnala' || #session.userId =='pmanuballa' }"> --%>
                                <td class="fieldLabel" width="200px" align="right">SSN/PAN :</td>                           

                                <td><s:textfield name="ssn" id="ssn" cssClass="inputTextBlueNew" size="20" value="%{currentEmployee.ssn}" onchange="fieldLengthValidator(this);"/></td>     
                                <%--     </s:if> --%>

                                <td class="fieldLabel" width="130px" align="right">NameAsPerPAN&nbsp;:</td>                           

                                <td><s:textfield name="nameAsPerPan" id="nameAsPerPan" cssClass="inputTextBlueNew" value="%{currentEmployee.nameAsPerPan}" onchange="fieldLengthValidator(this);"/></td>   



                                </tr>
                                <tr>
                                <td class="fieldLabel" align="left">Working For:</td>
                                <td><s:select label="Select Title"
                                          headerKey="All"
                                          headerValue="All"
                                          name="workingCountry" 
                                          list="countryList" cssClass="inputSelectNew"  id="workingCountry" value="%{currentEmployee.workingCountry}" />
                                </td> 
                                <td class="fieldLabel" width="200px" align="right">EMPNO:</td>                           

                                <td><s:textfield name="empno" id="empno" cssClass="inputTextBlueNew" size="20" value="%{currentEmployee.empno}" onchange="fieldLengthValidator(this);"/></td>     


                                <%--Miracle City
                                <td colspan="5"><s:select label="Select Title"
                                                               headerKey="All"
                                                               headerValue="All"
                                                               name="location" 
                                                               list="{''}" cssClass="inputSelectNew"  id="location" value="%{currentEmployee.location}"/>
                                 </td>--%>
                                <td class="fieldLabel"  width="200px" align="right">NSR NO:</td>                           

                                <td><s:textfield name="nsrno" id="nsrno" cssClass="inputTextBlueNew" size="20" value="%{currentEmployee.nsrno}" onchange="fieldLengthValidator(this);"/></td>     
                                </tr>
                                <tr>
                                <td class="fieldLabel" align="left">Location:</td>
                                <td>
                                    <%--<s:select name="location"  id="location" 
                                              cssClass="inputSelectNew" headerKey="-1" headerValue="--Please Select--" 
                                              list="{'Miracle City','Miracle Heights','LB Office','All'}" value="%{currentEmployee.location}"></s:select> --%>
                                    <s:select name="location" id="location" 
                                              cssClass="inputSelectNew" headerKey="-1" headerValue="--Please Select--" 
                                              list="locationsMap" value="%{currentEmployee.location}"></s:select> 
                                    </td>

                                    <td class="fieldLabel">Is TeamLead:</td>
                                    <td align="left">
                                    <s:checkbox name="isTeamLead" 
                                                value="%{currentEmployee.isTeamLead}" 
                                                theme="simple" /> 
                                </td>  
                                <td class="fieldLabel">Is Manager:</td>
                                <td align="left">
                                    <s:checkbox name="isManager" 
                                                value="%{currentEmployee.isManager}" 
                                                theme="simple" /> 
                                </td> 
                                </tr>
                                <tr>
                                <td class="fieldLabel" width="200px" align="right">Current Status :</td>

                                <td><s:select label="Select Current Status" 
                                          name="currStatus" id="currStatus"
                                          list="currStatusList" cssClass="inputSelectNew" value="%{currentEmployee.currStatus}"/></td>

                                <td class="fieldLabel" width="200px" align="right">Employee Type :</td>

                                <td><s:select label="Select Employee Type" 
                                          name="empTypeId" 
                                          list="empTypeIdList" cssClass="inputSelectNew" value="%{currentEmployee.empTypeId}"/></td>
                                <td class="fieldLabel" width="200px" align="right">ITG Batch :</td>
                                <td><s:textfield name="itgBatch" id="itgBatch" cssClass="inputTextBlueNew" size="20" value="%{currentEmployee.itgBatch}" onchange="fieldLengthValidator(this);"/></td>     
                                </tr>
                                <tr>
                                <td class="fieldLabel" width="200px" align="right">Organization :</td>

                                <td colspan="3"><s:select name="orgId"
                                          headerKey=""
                                          headerValue="-- Please Select --"
                                          list="orgIdList" id="orgId" cssClass="inputSelectEmpUpdate" value="%{currentEmployee.orgId}"/></td>
                                <td class="fieldLabel" width="200px" align="right">Department :</td>

                                <td><s:select label="Select Department" 
                                          name="departmentId"
                                          id="departmentId"
                                          headerKey=""
                                          headerValue="-- Please Select --"
                                          list="departmentIdList" cssClass="inputSelectNew" value="%{currentEmployee.departmentId}" onchange="getPracticeDataV1();getEmpTitleDataV1();getReportsToDataV1();"/></td>             


                                </tr>

                                <tr>
                                <td class="fieldLabel" width="200px" align="right">Practice Name :</td>

                                <td><s:select label="Select Practice Name" 
                                          name="practiceId"  id="practiceId"
                                          headerKey=""
                                          headerValue="-- Please Select --"
                                          list="practiceIdList" cssClass="inputSelectNew" value="%{currentEmployee.practiceId}" onchange="getSubPracticeData();"/> <%--onchange="getTeamData();" --%></td>  

                                <td class="fieldLabel" width="200px" align="right">SubPractice&nbsp;Name&nbsp;:</td>

                                <td nowrap>


                                  <%--  <s:select name="subPractice"  id="subPractice"
                                              headerKey=""
                                              headerValue="-- Please Select --"
                                              list="subPracticeList" cssClass="inputSelectNew" value="%{currentEmployee.subPractice}" theme="simple" onchange="getTeamData();"/> --%>
                                    <s:select name="subPractice"  id="subPractice"
                                              headerKey=""
                                              headerValue="-- Please Select --"
                                              list="subPracticeList" cssClass="inputSelectNew" value="%{currentEmployee.subPractice}" theme="simple" />
                                    <!--<div style="display:none;" id="territory_div" align="justify" theme="simple">-->

                                    <a HREF="javaScript:territoryPopup('TerritoryInfo.jsp');" id="territory_div" style="display:none;">
                                        <img SRC="../includes/images/help.gif" WIDTH=16 HEIGHT=16 BORDER=0 ALTER="Territory">
                                    </a>
                                    <!-- </div>-->
                                </td>  

                                <td class="fieldLabel" width="200px" align="right">Team Name :</td>

                                <td><s:select label="Select Team Name" 
                                          name="teamId"  id="teamId"
                                          headerKey=""
                                          headerValue="-- Please Select --"
                                          list="teamIdList" cssClass="inputSelectNew" value="%{currentEmployee.teamId}" onchange=""/></td>
                                <td></td>
                                <td></td>

                                </tr>

                                <tr>
                                <td class="fieldLabel" width="200px" align="right">Title :</td>  
                                <td><s:select label="Select Title"
                                          headerKey=""
                                          headerValue="-- Please Select --"
                                          name="titleId" 
                                          id="titleId" 
                                          list="titleIdList" cssClass="inputSelectNew" value="%{currentEmployee.titleId}" onchange=""/></td>                        

                                <td class="fieldLabel" width="200px" align="right">ReportsTo :</td>                           

                                <td><s:select label="Select Reports To" 
                                          name="reportsTo"
                                          id="reportsTo"
                                          headerKey=""
                                          headerValue="-- Please Select --"
                                          list="reportsToIdMap" 
                                          cssClass="inputSelectExtraLarge" 
                                          value="%{currentEmployee.reportsTo}"
                                          /></td> 
                                <td class="fieldLabel" width="200px" align="right">Operation Contact :</td>                           

                                <td>
                                    <%--  <s:select label="Select Point of Contact" 
                                                name="opsContactId" 
                                                headerKey="1"
                                                headerValue="-- Please Select --"
                                                list="pointOfContactMap" 
                                                cssClass="inputSelectNew" 
                                                value="%{currentEmployee.opsContactId}"/>  --%>

                                    <s:select label="Select Point of Contact" 
                                              name="opsContactId" 
                                              id="opsContactId"
                                              headerKey="1"
                                              headerValue="-- Please Select --"
                                              list="opsContactIdMap" 
                                              cssClass="inputSelectNew" 
                                              value="%{currentEmployee.opsContactId}" />
                                </td>


                                </tr>         

                                <tr>
                                <td class="fieldLabel" width="200px" align="right">Industry :</td>  

                                <td colspan="3"><s:select label="Select Industry" 
                                          name="industryId" 
                                          headerKey=""
                                          headerValue="-- Please Select --"
                                          list="industryIdList" cssClass="inputSelectEmpUpdate" value="%{currentEmployee.industryId}"/></td>                                                      
                                <td class="fieldLabel" width="200px" align="right">Date Of Hire :</td>                           

                                <td><s:textfield  name="hireDate" id="hireDate" cssClass="inputTextBlueSmall" value="%{currentEmployee.hireDate}"/><a href="javascript:cal1.popup();">
                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                             width="20" height="20" border="0"></td>


                                        </tr>

                                        <tr>
                                        <td class="fieldLabel" width="200px" align="right">DOB :</td>                           

                                        <td><s:textfield  name="birthDate" id="birthDate" cssClass="inputTextBlueSmall" value="%{currentEmployee.birthDate}"/><a href="javascript:cal2.popup();">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                     width="20" height="20" border="0"></td>                                                      

                                                <td class="fieldLabel" width="200px" align="right">Official DOB :</td>

                                                <td><s:textfield  name="offBirthDate" id="offBirthDate" cssClass="inputTextBlueSmall" value="%{currentEmployee.offBirthDate}"/><a href="javascript:cal3.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20" border="0"></td>

                                                        <td class="fieldLabel" width="200px" align="right">Date of Anniversary :</td>

                                                        <td><s:textfield  name="anniversaryDate" id="anniversaryDate" cssClass="inputTextBlueSmall" value="%{currentEmployee.anniversaryDate}"/><a href="javascript:cal4.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></td>



                                                                </tr>
                                                                <tr>
                                                                <td class="fieldLabel" width="200px" align="right">Last&nbsp;revised&nbsp;date&nbsp;:</td>                           

                                                                <td><s:textfield  name="lastRevisedDate" id="lastRevisedDate" cssClass="inputTextBlueSmall" value="%{currentEmployee.lastRevisedDate}"/><a href="javascript:cal9.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                             width="20" height="20" border="0"></td>                                                      

                                                                        <td class="fieldLabel" width="200px" align="right">Revised&nbsp;date&nbsp;:</td>

                                                                        <td><s:textfield  name="revisedDate" id="revisedDate" cssClass="inputTextBlueSmall" value="%{currentEmployee.revisedDate}"/><a href="javascript:cal10.popup();">
                                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                     width="20" height="20" border="0"></td>


                                                                                <s:if test="%{currentEmployee.currStatus=='Inactive'}">


                                                                                    <td class="fieldLabel" width="200px" align="right">Termination&nbsp;date&nbsp;:</td>
                                                                                    <td><s:textfield  name="dateOfTermination1" id="dateOfTermination1" cssClass="inputTextBlueSmall" value="%{currentEmployee.dateOfTermination}"  readonly="true"/><a href='javascript:getTerminationDetailsForWrong(id,loginId)'>
                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/ecertification/wrong.jpg" 
                                                                                                 width="13" height="13" border="0" ></a></td>


                                                                                </s:if>

                                                                                </tr>
                                                                                <tr>           
                                                                                <td class="fieldLabel" colspan="1">Is&nbsp;Working&nbsp;Onsite:</td>
                                                                                <td align="left">
                                                                                    <s:checkbox name="isOnsite" 
                                                                                                value="%{currentEmployee.isOnsite}"
                                                                                                theme="simple" /> 
                                                                                </td> 

                                                                                <!-- Newly added for cre management -->
                                                                                <td class="fieldLabel" colspan="1">Is CRE Team:</td>
                                                                                <td align="left">
                                                                                    <s:checkbox name="isCreTeam" 
                                                                                                value="%{currentEmployee.isCreTeam}"
                                                                                                theme="simple" /> 
                                                                                </td> 
                                                                                <!-- end CRE -->

                                                                                <td class="fieldLabel">Is&nbsp;Operation&nbsp;Team:</td>
                                                                                <td align="left">
                                                                                    <s:checkbox name="isOperationTeam" 
                                                                                                value="%{currentEmployee.isOperationTeam}"
                                                                                                theme="simple" /> 
                                                                                </td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <%--   <td class="fieldLabel">Is&nbsp;PMO:</td>
                                                                                       <td align="left">
                                                                                           <s:checkbox name="isPMO" 
                                                                                                       value="%{currentEmployee.isPMO}"
                                                                                                       theme="simple" /> 
                                                                                       </td> --%>
                                                                                    <!--Start New For ReportsTo -->
                                                                                <td class="fieldLabel">Reports&nbsp;To:</td>
                                                                                <td align="left">
                                                                                    <s:checkbox name="reportsToFlag" 
                                                                                                value="%{currentEmployee.reportsToFlag}"
                                                                                                theme="simple" /> 
                                                                                </td>
                                                                                <!--End New For ReportsTo -->
                                                                                <td class="fieldLabel" colspan="1">Is Lateral Hire:</td>
                                                                                <td>
                                                                                    <s:checkbox name="lateralFlag"
                                                                                                id="lateralFlag"
                                                                                                value="%{currentEmployee.lateralFlag}"
                                                                                                theme="simple"  onClick="showPrevExp(this); conformLateralFlag();"/> 
                                                                                </td>
                                                                                <s:hidden name="prvexpYears1" id="prvexpYears1" value="%{currentEmployee.prvexpYears}"/>
                                                                                <s:hidden name="prvexpMnths1" id="prvexpMnths1" value="%{currentEmployee.prvexpMnths}"/>

                                                                                <td class="fieldLabel" id="prvExp" style="display:none">Prev Exp :</td>
                                                                                <td id="prvExpRow" style="display:none">
                                                                                <s:textfield  name="prvexpYears" id="prvexpYears" cssClass="inputTextBlueSmall" value="%{currentEmployee.prvexpYears}" placeholder="Years" style="width:20px"/><label class="fieldLabel">(Y's)</label>
                                                                                <s:textfield  name="prvexpMnths" id="prvexpMnths" cssClass="inputTextBlueSmall" value="%{currentEmployee.prvexpMnths}" placeholder="Months" style="width:20px"/><label class="fieldLabel">(M's)</label>
                                                                        </td>
                                                                        </tr>

                                                                        </table>
                                                                        <div id="confdentialInfo" style="display: none">
                                                                            <br></br>
                                                                            <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                                                <thead>
                                                                                    <th colspan="6" class="headerText" align="left">
                                                                                        Confidential Information:
                                                                                    </th>
                                                                                </thead>
                                                                                <tr>

                                                                                <td class="fieldLabel" width="200px" align="right">Physically&nbsp;Challenged:</td>                           

                                                                                <td>
                                                                                    <%--    <s:textfield name="altPhoneNo" id="altPhoneNo" cssClass="inputTextBlueNew" value="%{currentEmployee.altPhoneNo}" onchange="return formatPhone(this);" onblur="return validatenumber(this)"/> --%>
                                                                                    <s:select name="phyChallenged"  id="phyChallenged" 
                                                                                              cssClass="inputSelectNew" 
                                                                                              list="#@java.util.LinkedHashMap@{'N':'No','Y':'Yes'}" value="%{currentEmployee.phyChallenged}" onchange="isPhysicallyDisabled();"></s:select> 
                                                                                    </td>       

                                                                                    <td class="fieldLabel" width="130px" align="right">Category :</td>                           

                                                                                    <td>
                                                                                    <s:select name="phyCategory"  id="phyCategory" 
                                                                                              cssClass="inputSelectNew" headerKey="" headerValue="--Please Select--" 
                                                                                              list="{'BL-Both Legs affected but not arms','BA-Both arms affected','BLA-Both legs and both arms affected','OL-One leg affected (right or left)','OA-One arm affected','BH-Stiff back and hips (Cannot sit or stoop)','MW-Muscular weakness and limited physical endurance','B-Blind','PD-Partially Deaf','D-Deaf'}" value="%{currentEmployee.phyCategory}"></s:select> 
                                                                                    <%--   <s:textfield name="homePhoneNo" id="homePhoneNo" cssClass="inputTextBlueNew" value="%{currentEmployee.homePhoneNo}" onchange="return formatPhone(this);" onblur="return validatenumber(this)"/> --%>
                                                                                </td>    
                                                                                </tr>
                                                                                <tr>
                                                                                <td class="fieldLabel" width="200px" align="right">Bank&nbsp;Name :</td>                           

                                                                                <td><s:textfield name="bankName" id="bankName" cssClass="inputTextBlueNew" value="%{currentEmployee.bankName}" onchange="fieldLengthValidator(this);"/></td>                                                      

                                                                                <td class="fieldLabel" width="200px" align="right">Bank&nbsp;Account&nbsp;No.&nbsp;:</td>                           

                                                                                <td><s:textfield name="accNum" id="accNum" cssClass="inputTextBlueNew" value="%{currentEmployee.accNum}" onchange="fieldLengthValidator(this);"/></td>       

                                                                                <td class="fieldLabel" width="130px" align="right">Name&nbsp;As&nbsp;Per&nbsp;Account&nbsp;:</td>                           

                                                                                <td><s:textfield name="nameAsPerAcc" id="nameAsPerAcc" cssClass="inputTextBlueNew" value="%{currentEmployee.nameAsPerAcc}" onchange="fieldLengthValidator(this);"/></td>    
                                                                                </tr>

                                                                                <tr>
                                                                                <td class="fieldLabel" width="200px" align="right">IFSC&nbsp;Code :</td>                           

                                                                                <td><s:textfield name="ifscCode" id="ifscCode" cssClass="inputTextBlueNew" value="%{currentEmployee.ifscCode}" onchange="fieldLengthValidator(this);"/></td>                                                      
                                                                                <td class="fieldLabel" width="200px" align="right">Aadhar&nbsp;No.&nbsp;:</td>                           

                                                                                <td><s:textfield name="aadharNum" id="aadharNum" cssClass="inputTextBlueNew" value="%{currentEmployee.aadharNum}" onchange="fieldLengthValidator(this);"/></td>                                                      

                                                                                <td class="fieldLabel" width="130px" align="right">Name&nbsp;As&nbsp;Per&nbsp;Aadhar :</td>                           

                                                                                <td><s:textfield name="aadharName" id="aadharName" cssClass="inputTextBlueNew" value="%{currentEmployee.aadharName}" onchange="fieldLengthValidator(this);"/></td>       


                                                                                </tr>
                                                                                <tr>
                                                                                <td class="fieldLabel" width="200px" align="right">UAN&nbsp;No.&nbsp;:</td> 
                                                                                <td><s:textfield name="uanNo" id="uanNo" cssClass="inputTextBlueNew" value="%{currentEmployee.uanNo}" onchange="fieldLengthValidator(this);"/></td>                                                      

                                                                                <td class="fieldLabel" width="200px" align="right">PF&nbsp;No.&nbsp;:</td>                           
                                                                                <td><s:textfield name="pfno" id="pfno" cssClass="inputTextBlueNew" value="%{currentEmployee.pfno}" onchange="fieldLengthValidator(this);"/></td>                                                                             
                                                                                <td class="fieldLabel">Is&nbsp;International worker:</td>
                                                                                <td align="left"><s:checkbox name="isInternationalWorker"  value="%{currentEmployee.isInternationalWorker}" theme="simple" /></td>

                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                        <br>
                                                                        <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                                            <thead>
                                                                                <th colspan="6" class="headerText" align="left">
                                                                                    Contact Details:
                                                                                </th>
                                                                            </thead>
                                                                            <tr>
                                                                            <td class="fieldLabel" width="200px" align="right">Work-Phone No :</td>                           

                                                                            <td><s:textfield name="workPhoneNo" id="workPhoneNo" cssClass="inputTextBlueNew" value="%{currentEmployee.workPhoneNo}" onchange="return formatPhone(this);" onblur="return validatenumber(this)"/></td>                                                      

                                                                            <td class="fieldLabel" width="200px" align="right">Emergency Phone No :</td>                           

                                                                            <td><s:textfield name="altPhoneNo" id="altPhoneNo" cssClass="inputTextBlueNew" value="%{currentEmployee.altPhoneNo}" onchange="return formatPhone(this);" onblur="return validatenumber(this)"/></td>       

                                                                            <td class="fieldLabel" width="130px" align="right">Home-Phone No :</td>                           

                                                                            <td><s:textfield name="homePhoneNo" id="homePhoneNo" cssClass="inputTextBlueNew" value="%{currentEmployee.homePhoneNo}" onchange="return formatPhone(this);" onblur="return validatenumber(this)"/></td>    
                                                                            </tr>
                                                                            <tr>
                                                                            <td class="fieldLabel" width="200px" align="right">Mobile No :</td>                           

                                                                            <td><s:textfield name="cellPhoneNo" id="cellPhoneNo" cssClass="inputTextBlueNew" value="%{currentEmployee.cellPhoneNo}" onchange="return formatPhone(this);" onblur="return validatenumber(this)"/></td>    

                                                                            <td class="fieldLabel" width="200px" align="right">Email Office :</td>

                                                                            <td colspan="2" width="40"><s:textfield name="officeEmail" id="officeEmail" cssClass="inputTextBlueEmail" size="50" value="%{currentEmployee.officeEmail}" onchange="return checkEmail(this);" onblur="return valueCheck(this);"/></td>

                                                                            <!-- <td rowspan="3"><img src="constr4.gif" alt="Construction" width="130" height="75" /> </td>-->

                                                                            </tr>
                                                                            <tr>

                                                                            <td class="fieldLabel" width="200px" align="right">Hotel-Phone No :</td>                           

                                                                            <td><s:textfield name="hotelPhoneNo" id="hotelPhoneNo" cssClass="inputTextBlueNew" value="%{currentEmployee.hotelPhoneNo}" onchange="return formatPhone(this);" onblur="return validatenumber(this)"/></td>   

                                                                            <td class="fieldLabel" width="200px"align="right">Email Personal :</td>

                                                                            <td colspan="2"><s:textfield name="personalEmail" id="personalEmail" cssClass="inputTextBlueEmail" size="50" value="%{currentEmployee.personalEmail}" onchange="return checkEmail(this);"/></td>
                                                                            </tr>
                                                                            <tr>

                                                                            <td class="fieldLabel" width="200px" align="right">India-Phone No :</td>

                                                                            <td><s:textfield name="indiaPhoneNo" id="indiaPhoneNo" cssClass="inputTextBlueNew" value="%{currentEmployee.indiaPhoneNo}" onchange="return formatPhone(this);" onblur="return validatenumber(this)"/></td>

                                                                            <td class="fieldLabel" width="200px" align="right">Email Others :</td>

                                                                            <td colspan="2"><s:textfield name="otherEmail" id="otherEmail" cssClass="inputTextBlueEmail" size="50" value="%{currentEmployee.otherEmail}"  onchange="return checkEmail(this);"/></td>
                                                                            </tr>

                                                                            <tr>
                                                                            <td class="fieldLabel" width="200px" align="right">Fax-No :</td>                           

                                                                            <td><s:textfield name="faxNo" id="faxNo" cssClass="inputTextBlueNew" value="%{currentEmployee.faxNo}" onchange="fieldLengthValidator(this);"/></td>                                                      

                                                                            <td class="fieldLabel" width="200px" align="right">Last Contact By :</td>

                                                                            <td colspan="2"><s:textfield name="lastContactBy" id="lastContactBy" cssClass="inputTextBlueEmail" size="50" value="%{currentEmployee.lastContactBy}" onchange="lastContactByValidate(document.employeeForm.lastContactBy.value);"/></td>

                                                                            <!-- <td class="fieldLabel" width="130px" align="center">Photo</td>-->
                                                                            </tr>
                                                                        </table>
                                                                    </s:form>
                                                                    <script type="text/JavaScript">
                                                                        var cal1 = new CalendarTime(document.forms['employeeForm'].elements['hireDate']);
                                                                        cal1.year_scroll = true;
                                                                        cal1.time_comp = false;
                                                                        var cal2 = new CalendarTime(document.forms['employeeForm'].elements['birthDate']);
                                                                        cal2.year_scroll = true;
                                                                        cal2.time_comp = false;
                                                                        var cal3 = new CalendarTime(document.forms['employeeForm'].elements['offBirthDate']);
                                                                        cal3.year_scroll = true;
                                                                        cal3.time_comp = false;
                                                                        var cal4 = new CalendarTime(document.forms['employeeForm'].elements['anniversaryDate']);
                                                                        cal4.year_scroll = true;
                                                                        cal4.time_comp = false;
                                                                        var cal9 = new CalendarTime(document.forms['employeeForm'].elements['lastRevisedDate']);
                                                                        cal9.year_scroll = true;
                                                                        cal9.time_comp = false;
                                                                        var cal10 = new CalendarTime(document.forms['employeeForm'].elements['revisedDate']);
                                                                        cal10.year_scroll = true;
                                                                        cal10.time_comp = false;
                                                                    </script>
                                                                    <%-- </sx:div > --%>
                                                                    </div>

                                                                    <!--//END TAB : -->

                                                                    <%--   <sx:div id="empCurrentState" label="Current Status"  > --%>
                                                                    <div id="empCurrentState" class="tabcontent"  >
                                                                        <div id="empProjectsOverlay"></div>              <!-- Start Overlay -->
                                                                        <div id="empProjectsSpecialBox">


                                                                            <s:form theme="simple"  align="center" name="projectDetailsOverlay" action="" method="post" onsubmit="return validateForm();"   >



                                                                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                                                    <tr>                               
                                                                                    <td colspan="6" style="background-color: #288AD1" >

                                                                                        <b><lable style="color: #ffffff">Project&nbsp;Details</lable></b>


                                                                                    </td>
                                                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                                                        <a href="#" onmousedown="getProjectOverlay()" >
                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                                                        </a>  

                                                                                    </td></tr>

                                                                                    <tr> 
                                                                                    <td class="fieldLabel">Start Date:</td>
                                                                                    <td><s:textfield name="projectStartDate" id="projectStartDate" cssClass="inputTextBlueSmall" value="%{currentEmployee.projectStartDate}"/><a href="javascript:cal7.popup();">
                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                 width="20" height="20" border="0" style="margin-bottom: -5px;margin-left: -3px;"></td>

                                                                                            <td class="fieldLabel">End Date:</td>
                                                                                            <td><s:textfield name="projectEndDate" id="projectEndDate" cssClass="inputTextBlueSmall" value="%{currentEmployee.projectEndDate}"/><a href="javascript:cal8.popup();">
                                                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                         width="20" height="20" border="0" style="margin-bottom: -5px;margin-left: -3px;"></td>
                                                                                                    <td class="fieldLabel" style="text-align: center;" width="200px" align="right">Status :

                                                                                                        <s:select  
                                                                                                            name="status" id="status"
                                                                                                            list="{'All','Active','InActive'}" cssClass="inputSelectNew" value="%{'Active'}" /></td>
                                                                                                    <td><input type="button" value="Search"  class="buttonBg" onclick="employeeAvailableProjects();"/></td>
                                                                                                    </tr>
                                                                                                    <tr>
                                                                                                    <td colspan="6">
                                                                                                        <center><div id="loadingMessage" style="color: red;display: none;">Loading please wait..</div></center>
                                                                                                        <div id="IssueResultMessage"></div>
                                                                                                    </td>
                                                                                                    </tr>

                                                                                                    <tr>
                                                                                                    <td colspan="6">
                                                                                                        <table id="tblEmpProjectDetails" align='center' cellpadding='1' cellspacing='1' border='0' class="gridTable" width='800' style="max-width:99%;">
                                                                                                            <COLGROUP ALIGN="left" >
                                                                                                                <COL width="3%">
                                                                                                                    <COL width="20%">
                                                                                                                        <COL width="10%">
                                                                                                                            <COL width="10%">
                                                                                                                                <COL width="7%">
                                                                                                                                    <COL width="7%">
                                                                                                                                        <COL width="7%">
                                                                                                                                            <COL width="7%">


                                                                                                                                                </table> 
                                                                                                                                                </td>
                                                                                                                                                </tr>


                                                                                                                                                </table>
                                                                                                                                            </s:form>

                                                                                                                                            <script type="text/JavaScript">
             
                                                                                                                                                var cal7 = new CalendarTime(document.forms['projectDetailsOverlay'].elements['projectStartDate']);
                                                                                                                                                cal7.year_scroll = true;
                                                                                                                                                cal7.time_comp = false;
                                                                                                                                                var cal8 = new CalendarTime(document.forms['projectDetailsOverlay'].elements['projectEndDate']);
                                                                                                                                                cal8.year_scroll = true;
                                                                                                                                                cal8.time_comp = false;
                                                                                                                                            </script>


                                                                                                                                            </div>
                                                                                                                                            <s:form action="empStateUpdate" theme="simple"  enctype="multipart/form-data" method="post" id="empCurrState" name="empCurrState" onsubmit="return checkDate();">
                                                                                                                                                <table cellpadding="1" cellspacing="1" border="0" width="100%">

                                                                                                                                                    <tr>
                                                                                                                                                    <td class="headerText" colspan="6">
                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                                                                                                                            <tr align="right">

                                                                                                                                                            <td align="left" width="670px">
                                                                                                                                                                <% if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                                                                                                                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                                                                                                                    }%>
                                                                                                                                                            </td>

                                                                                                                                                            <s:if test="#request.resultMessage!=null">
                                                                                                                                                                <td valign="middle">
                                                                                                                                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-2)">  
                                                                                                                                                                </td>
                                                                                                                                                            </s:if>
                                                                                                                                                            <s:else>
                                                                                                                                                                <td valign="middle">
                                                                                                                                                                    <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)">
                                                                                                                                                                </td>
                                                                                                                                                            </s:else>


                                                                                                                                                            <%--<td valign="right">
                                                                                                                                                                <a href="<s:url value="empBackToList.action"/>" style="align=center;">
                                                                                                                                                                    <img alt="Back to List"
                                                                                                                                                                         src="<s:url value="/includes/images/backToList_66x19.gif"/>" 
                                                                                                                                                                         width="66px" 
                                                                                                                                                                         height="19px"
                                                                                                                                                                     border="0" align="bottom"></a>
                                                                                                                                                            </td>  --%>
                                                                                                                                                            <td valign="top">
                                                                                                                                                                <s:if test="%{navId == 1}">

                                                                                                                                                                    <%-- <s:if test="%{currentEmployee.empState != 'OnProject'}">
                                                                                                                                                                       <s:submit name="submit" value="Update" cssClass="buttonBg" onclick="setTempVar1()"/>
                                                                                                                                                                       </s:if> --%>
                                                                                                                                                                    <%-- <s:if test="#session.userId =='rijju' || #session.userId =='nfatima'">

                                                                                                                                                                            <s:submit name="submit" value="Update" cssClass="buttonBg" onclick="setTempVar1()"/>

                                                                                                                                                                            </s:if><s:else>
                                                                                                                                                                            <s:if test="%{currentEmployee.empState != 'OnProject'}">
                                                                                                                                                                            <s:submit name="submit" value="Update" cssClass="buttonBg" onclick="setTempVar1()"/>
                                                                                                                                                                            </s:if>
                                                                                                                                                                            </s:else> --%>
                                                                                                                                                                    <%!                                                                                                                                                                         Map rolesMap = null;
                                                                                                                                                                    %>
                                                                                                                                                                    <%

                                                                                                                                                                        rolesMap = (Map) session.getAttribute("myRoles");
                                                                                                                                                                        //if(session.getAttribute("userId").toString().equals("rijju")||session.getAttribute("userId").toString().equals("nfatima")||rolesMap.containsValue("Admin")){
                                                                                                                                                                       if(session.getAttribute("userId").toString().equals("rijju")||session.getAttribute("userId").toString().equals("nfatima")||rolesMap.containsValue("Admin") ||session.getAttribute("userId").toString().equals("kfrance") || session.getAttribute("userId").toString().equals("kardaiz") || session.getAttribute("userId").toString().equals("mfast")){
                                                                                                                                                                    %><s:submit name="submit" value="Update" cssClass="buttonBg" onclick="setTempVar1()"/>

                                                                                                                                                                    <%                                                                                                                                                                    } else {
                                                                                                                                                                    %>
                                                                                                                                                                    <s:if test="%{currentEmployee.empState != 'OnProject'}">

                                                                                                                                                                        <s:submit name="submit" value="Update" cssClass="buttonBg" onclick="setTempVar1()"/>
                                                                                                                                                                    </s:if>
                                                                                                                                                                    <%}%>


                                                                                                                                                                </s:if>
                                                                                                                                                                <s:hidden name="id" id="id" value="%{currentEmployee.id}"/>
                                                                                                                                                                <s:hidden name="loginId" id="loginId" value="%{currentEmployee.loginId}"/>
                                                                                                                                                                <s:hidden name="previousEmpState" id="previousEmpState" value="%{currentEmployee.previousEmpState}"/>
                                                                                                                                                                <s:hidden name="currId" id="currId" value="%{currId}"/>
                                                                                                                                                                <s:hidden name="tempVar" id="tempVar" value="%{tempVar}"/>
                                                                                                                                                                <s:hidden name="navId" id="navId" value="%{navId}"/>
                                                                                                                                                                <s:hidden id="projectContactId"  name="projectContactId"  value="%{currentEmployee.projectContactId}"/>

                                                                                                                                                            </td>
                                                                                                                                                            </tr>
                                                                                                                                                        </table>
                                                                                                                                                    </td>
                                                                                                                                                    </tr>
                                                                                                                                                    <tr>
                                                                                                                                                    <td class="fieldLabel" width="200px" align="right">Current&nbsp;State:</td>                           

                                                                                                                                                    <td>
                                                                                                                                                        <s:if test="%{navId == 1}">
                                                                                                                                                            <s:select 
                                                                                                                                                                name="empState" 
                                                                                                                                                                list="empCurrentStatus" id="empState" contentEditable="true" cssClass="inputSelectNew" value="%{currentEmployee.empState}" onchange="showAddStatus();"/>                                                    
                                                                                                                                                        </s:if>
                                                                                                                                                        <s:else>
                                                                                                                                                            <s:select 
                                                                                                                                                                name="empState" 
                                                                                                                                                                list="empCurrentStatus" id="empState" cssClass="inputSelectNew" value="%{currentEmployee.empState}" onchange="showAddStatus();"/>                                                      
                                                                                                                                                        </s:else>
                                                                                                                                                    </td>  
                                                                                                                                                    <s:if test="%{( (currentEmployee.empState=='Available') || (currentEmployee.projectContactId != 0 && currentEmployee.empState=='OnProject') )}">
                                                                                                                                                         <td class="fieldLabel" width="200px" align="right">Start Date:</td>                           

                                                                                                                                                        <td><s:textfield  name="stateStartDate" id="startDate" cssClass="inputTextBlueSmall" readonly="true" value="%{currentEmployee.stateStartDate1}"/><a href="javascript:cal5.popup();">
                                                                                                                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                     width="20" height="20" border="0" style="margin-bottom: -5px;margin-left: -3px; display:none;"></td>

                                                                                                                                                                <td class="fieldLabel" width="200px" align="right">End Date :</td>

                                                                                                                                                                <td><s:textfield  name="stateEndDate" id="endDate" readonly="true" cssClass="inputTextBlueSmall" value="%{currentEmployee.stateEndDate1}"/><a href="javascript:cal6.popup();">
                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                             width="20" height="20" border="0" style="margin-bottom: -5px;margin-left: -3px;display:none;"></td>

                                                                                                                                                                        <%--   <td class="fieldLabel" width="200px" align="right">CTC/year(in&nbsp;lakhs) :</td>
                                                                                                                                                                        <td><s:textfield name="ctcPerYear" id="ctcPerYear1" readonly="true" cssClass="inputTextBlueNew" onfocus="checkDate();" value="%{currentEmployee.ctcPerYear}" onblur="validateCtc();"/></td>
                                                                                                                                                                        --%>

                                                                                                                                                                    </s:if>
                                                                                                                                                                    <s:else>

                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Start Date:</td>                           

                                                                                                                                                                        <td><s:textfield  name="stateStartDate" id="startDate" cssClass="inputTextBlueSmall" value="%{currentEmployee.stateStartDate1}"/><a href="javascript:cal5.popup();">
                                                                                                                                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                                     width="20" height="20" border="0" style="margin-bottom: -5px;margin-left: -3px; " ></td>

                                                                                                                                                                                <td class="fieldLabel" width="200px" align="right">End Date :</td>

                                                                                                                                                                                <td><s:textfield  name="stateEndDate" id="endDate" cssClass="inputTextBlueSmall" value="%{currentEmployee.stateEndDate1}"/><a href="javascript:cal6.popup();">
                                                                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                                                                             width="20" height="20" border="0" style="margin-bottom: -5px;margin-left: -3px; " ></td>

                                                                                                                                                                                        <%--    <td class="fieldLabel" width="200px" align="right">CTC/year(in&nbsp;lakhs) :</td>
                                                                                                                                                                                        <td><s:textfield name="ctcPerYear" id="ctcPerYear1" cssClass="inputTextBlueNew" onfocus="checkDate();" value="%{currentEmployee.ctcPerYear}" onblur="validateCtc();"/></td>
                                                                                                                                                                                        --%>

                                                                                                                                                                                    </s:else>
                                                                                                                                                                                    </tr>
                                                                                                                                                                                    <%--     <td class="fieldLabel" width="200px" align="right">Internal Rate/Hr:</td>

                                                                                                                                                    <td><s:textfield name="intRatePerHour"  id="intRatePerHour" cssClass="inputTextBlueNew" value="%{currentEmployee.intRatePerHour}" readonly="true"/></td>
                                                                                                                                                    <s:if test="%{(currentEmployee.empState=='Available')}">
                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Invoice&nbsp;Rate/Hr:</td>                           

                                                                                                                                                        <td><s:textfield name="invRatePerHour" id="invRatePerHour" readonly="true" cssClass="inputTextBlueNew" value="%{currentEmployee.invRatePerHour}"/></td>     

                                                                                                                                                    </s:if>
                                                                                                                                                    <s:else>
                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Invoice&nbsp;Rate/Hr:</td>                           

                                                                                                                                                        <td><s:textfield name="invRatePerHour" id="invRatePerHour" cssClass="inputTextBlueNew" value="%{currentEmployee.invRatePerHour}"/></td>     

</s:else> --%>





                                                                                                                                                                                    <s:if test="%{navId == 0}">
                                                                                                                                                                                        <tr>
                                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Project Name:</td>                           
                                                                                                                                                                                        <td colspan="3">
                                                                                                                                                                                            <s:textfield name="prjName" style="width:100%" id="prjName" value="" onfocus="checkDate();" cssClass="inputTextBlueLargeAccount" />
                                                                                                                                                                                        </td>
                                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Utilization:</td>                           
                                                                                                                                                                                        <td ><s:textfield name="utilization" id="utilization" cssClass="inputTextBlue2"  value="%{currentEmployee.utilization}" readonly="true"/><s:label cssClass="fieldLabel" value="%"/></td>

                                                                                                                                                                                        </tr>
                                                                                                                                                                                    </s:if>
                                                                                                                                                                                    <s:else>
                                                                                                                                                                                        <tr>
                                                                                                                                                                                            <s:if test="%{(currentEmployee.empState=='Available') || (currentEmployee.empState=='OnProject')}">
                                                                                                                                                                                            <td class="fieldLabel" width="200px" align="right">Project Name:</td>                           
                                                                                                                                                                                            <td colspan="3">
                                                                                                                                                                                                <s:textfield name="prjName" style="width:100%" id="prjName" value="%{currentEmployee.prjName}" readonly="true" onfocus="checkDate();" cssClass="inputTextBlueLargeAccount" />
                                                                                                                                                                                            </td>
                                                                                                                                                                                        </s:if>
                                                                                                                                                                                        <s:else>

                                                                                                                                                                                            <td class="fieldLabel" width="200px" align="right">Project Name:</td>                           
                                                                                                                                                                                            <td colspan="3">
                                                                                                                                                                                                <s:textfield name="prjName" style="width:100%" id="prjName" value="%{currentEmployee.prjName}" onfocus="checkDate();" cssClass="inputTextBlueLargeAccount" />
                                                                                                                                                                                            </td>
                                                                                                                                                                                        </s:else>

                                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Utilization:</td>                           
                                                                                                                                                                                        <td ><s:textfield name="Utilization" id="Utilization" cssClass="inputTextBlue2"  value="%{currentEmployee.utilization}" readonly="true"/><s:label cssClass="fieldLabel" value="%"/></td>

                                                                                                                                                                                        </tr>
                                                                                                                                                                                    </s:else>

                                                                                                                                                                                    <tr>
                                                                                                                                                                                        <s:if test="%{navId == 0}">

                                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Skill Set:</td>                           
                                                                                                                                                                                        <td colspan="3">
                                                                                                                                                                                            <%-- <s:textfield name="skillSet" id="skillSet" value="" onfocus="checkDate();" onchange="fieldLengthValidator(this);" cssClass="inputTextBlueExtraLarge" /> --%>
                                                                                                                                                                                            <s:textarea rows="4" cols="65" name="skillSet" cssClass="inputTextarea3" value="" id="skillSet" onchange="fieldLengthValidator(this);"/>
                                                                                                                                                                                        </td>
                                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Status:</td>
                                                                                                                                                                                        <td> <s:select 
                                                                                                                                                                                                name="empStatus" 
                                                                                                                                                                                                list="{'Active','Completed'}" id="empStatus" value="%{currentEmployee.projectStatus}" cssClass="inputSelectNew"  />                                                    
                                                                                                                                                                                        </td>
                                                                                                                                                                                    </s:if>
                                                                                                                                                                                    <s:else>

                                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Skill Set:</td>                           
                                                                                                                                                                                        <td colspan="3">
                                                                                                                                                                                            <%-- <s:textfield name="skillSet" id="skillSet" value="%{currentEmployee.skillSet}" onfocus="checkDate();" onchange="fieldLengthValidator(this);" cssClass="inputTextBlueExtraLarge" /> --%>
                                                                                                                                                                                            <s:textarea rows="4" cols="65" name="skillSet" cssClass="inputTextarea3" id="skillSet" value="%{currentEmployee.skillSet}" onchange="fieldLengthValidator(this);"/>
                                                                                                                                                                                        </td>  
                                                                                                                                                                                        <td class="fieldLabel" width="200px" align="right">Status:</td>
                                                                                                                                                                                        <s:if test="%{(currentEmployee.empState=='OnProject') || (currentEmployee.empState=='Available')}">


                                                                                                                                                                                            <td>
                                                                                                                                                                                                <s:select 
                                                                                                                                                                                                    name="empStatus" 
                                                                                                                                                                                                    list="{'Active','Completed'}" id="empStatus"   contenteditable="true" value="%{currentEmployee.projectStatus}" cssClass="inputSelectNew"  />                                                    
                                                                                                                                                                                            </td>   
                                                                                                                                                                                        </s:if>
                                                                                                                                                                                        <s:else>

                                                                                                                                                                                            <td>
                                                                                                                                                                                                <s:select 
                                                                                                                                                                                                    name="empStatus" 
                                                                                                                                                                                                    list="{'Active','Completed'}" id="empStatus"  value="%{currentEmployee.projectStatus}" cssClass="inputSelectNew"  />                                                    
                                                                                                                                                                                            </td>                             
                                                                                                                                                                                        </s:else>
                                                                                                                                                                                    </s:else>
                                                                                                                                                                                    </tr>
                                                                                                                                                                                    <tr>

                                                                                                                                                                                        <s:if test="%{navId == 0}">

                                                                                                                                                                                        <td class="fieldLabel" id="commentsField" width="200px"  onchange="fieldLengthValidator(this);" align="right">Comments:</td>                           

                                                                                                                                                                                        <td colspan="3">

                                                                                                                                                                                            <s:textarea rows="2" cols="65" name="comments" value="" onchange="fieldLengthValidator(this);"  cssClass="inputTextarea3" id="comments" />

                                                                                                                                                                                        </td>

                                                                                                                                                                                    </s:if>

                                                                                                                                                                                    <s:else>


                                                                                                                                                                                        <td class="fieldLabel" id="commentsField" width="200px" align="right">Comments:</td>                           

                                                                                                                                                                                        <td colspan="3">

                                                                                                                                                                                            <s:textarea rows="2" cols="65" name="comments"   cssClass="inputTextarea3" id="comments" value="%{currentEmployee.comments}" />

                                                                                                                                                                                        </td>


                                                                                                                                                                                    </s:else>



                                                                                                                                                                                    </tr>

                                                                                                                                                                                    <tr id="showAttachment">

                                                                                                                                                                                        <s:if test="%{navId == 0}">

                                                                                                                                                                                        <td class="fieldLabel">Attach Resume :</td>
                                                                                                                                                                                        <td colspan= "2" ><s:file name="upload" label="File" cssClass="inputTextarea" id="attachmentFileName" accept=".doc,.pdf,.txt,.odt,.rtf" onchange="checkFileName();"/> 
                                                                                                                                                                                        </td>  

                                                                                                                                                                                    </s:if>

                                                                                                                                                                                    <s:else>





                                                                                                                                                                                        <s:if test="%{currentEmployee.empState == 'Available'}">
                                                                                                                                                                                            <%--    <s:property value="currentEmployee.resumeName"/>--%>

                                                                                                                                                                                            <s:if test="%{currentEmployee.resumeName==null||currentEmployee.resumeName == ''}">
                                                                                                                                                                                                <td class="fieldLabel">Attach&nbsp;Resume&nbsp;:</td>
                                                                                                                                                                                                <td colspan= "2" ><s:file name="upload" label="File" cssClass="inputTextarea" id="attachmentFileName" accept=".doc,.pdf,.txt,.odt,.rtf" onchange="checkFileName();"/>
                                                                                                                                                                                                </s:if>
                                                                                                                                                                                                <s:else>
                                                                                                                                                                                                <td class="fieldLabel">Replace&nbsp;New&nbsp;Resume&nbsp;:</td>
                                                                                                                                                                                                <td colspan= "2" ><s:file name="upload" label="File" cssClass="inputTextarea" id="attachmentFileName" accept=".doc,.pdf,.txt,.odt,.rtf" onchange="checkFileName();"/>
                                                                                                                                                                                                </s:else>
                                                                                                                                                                                                <s:if test="%{currentEmployee.resumeName==null||currentEmployee.resumeName == ''}">
                                                                                                                                                                                                    <a href="javascript:getAlertMessage();"><img width="18" height="14" border="0" alt="download" src="../includes/images/download_11x10.jpg" title="Click here to download"></a>
                                                                                                                                                                                                    </s:if>
                                                                                                                                                                                                    <s:else>
                                                                                                                                                                                                    <a href="<s:url action="downloadEmployeeResume.action">
                                                                                                                                                                                                        <s:param name="id" value="%{currId}"/>
                                                                                                                                                                                                        <s:param name="empId" value="%{empId}"/>
                                                                                                                                                                                                        <s:param name="fileName" value="%{currentEmployee.resumeName}"/>
                                                                                                                                                                                                       </s:url>"><img width="18" height="14" border="0" alt="download" src="../includes/images/download_11x10.jpg" title="Click here to download"></a>
                                                                                                                                                                                                    </s:else>
                                                                                                                                                                                            </td>   
                                                                                                                                                                                        </s:if>


                                                                                                                                                                                    </s:else>



                                                                                                                                                                                    </tr>

                                                                                                                                                                                    </tr>
                                                                                                                                                                                    <tr>
                                                                                                                                                                                    <td></td>
                                                                                                                                                                                    <td></td>
                                                                                                                                                                                    <td></td>
                                                                                                                                                                                    <td colspan="4" valign="bottom" align="right">
                                                                                                                                                                                        <!--<input type="button" name="addNew" id="addNew" class="buttonBg" value="Add Status" onclick="addStatus();">-->
                                                                                                                                                                                        <s:if test="%{navId == 0}">
                                                                                                                                                                                            <s:submit id="addStatus" name="addStatus" value="Add Status" cssClass="buttonBg" onclick="setTempVar2()"/>
                                                                                                                                                                                        </s:if>
                                                                                                                                                                                        <!--<div id="loadMessage" style="display: none" align="center" class="error" >
                                                                                                                                                                                            <b>Loading.... Please Wait....</b>
                                                                                                                                                                                        </div>-->

                                                                                                                                                                                        <input type="button" value="Project Details" onclick="getProjectOverlay();" class="buttonBg" id="searchButton" />
                                                                                                                                                                                        <input type="button" class="buttonBg" value="Generate Excel" onclick="return getCurrentEmployeeReport();"/>
                                                                                                                                                                                    </td>
                                                                                                                                                                                    </tr>

                                                                                                                                                                                    <tr>
                                                                                                                                                                                    <tr>
                                                                                                                                                                                    <td colspan="6">
                                                                                                                                                                                        <table cellpadding="2" cellspacing="1" width="98%" class="gridTable">
                                                                                                                                                                                            <tr class="gridHeader">
                                                                                                                                                                                            <td width="4%" class="gridHeader" ALIGN="right">Edit</td>
                                                                                                                                                                                            <td width="6%" class="gridHeader" ALIGN="center">State</td>
                                                                                                                                                                                            <td width="12%" class="gridHeader">StartDate</td>
                                                                                                                                                                                            <td width="12%" class="gridHeader">EndDate</td>

                                                                                                                                                                                            <td width="15%" class="gridHeader">Skill Set</td>
                                                                                                                                                                                            <td width="16%" class="gridHeader">Project</td>
                                                                                                                                                                                            <td width="10%" class="gridHeader">Utilization</td>
                                                                                                                                                                                            <td width="10%" class="gridHeader">Status</td>
                                                                                                                                                                                            <td width="18%" class="gridHeader">Created Date</td>
                                                                                                                                                                                            <%
                                                                                                                                                                                                rolesMap = (Map) session.getAttribute("myRoles");
                                                                                                                                                                                                //   if(session.getAttribute("userId").toString().equals("rijju")||session.getAttribute("userId").toString().equals("nfatima")||rolesMap.containsValue("Admin")){
                                                                                                                                                                                                if(session.getAttribute("userId").toString().equals("rijju")||session.getAttribute("userId").toString().equals("nfatima")||rolesMap.containsValue("Admin") ||session.getAttribute("userId").toString().equals("kfrance") || session.getAttribute("userId").toString().equals("kardaiz") || session.getAttribute("userId").toString().equals("mfast")){
                                                                                                                                                                                            %>
                                                                                                                                                                                            <td width="18%" class="gridHeader">Delete</td>
                                                                                                                                                                                            <%}%>

                                                                                                                                                                                            </tr>
                                                                                                                                                                                            <s:iterator value="#request.currentStateHistoryCollection">
                                                                                                                                                                                                <s:if test="%{(projectContactId == 0 && empState=='OnProject')}">
                                                                                                                                                                                                    <tr class="gridRowEven" style="background: #CCC;">
                                                                                                                                                                                                    </s:if>
                                                                                                                                                                                                    <s:else>
                                                                                                                                                                                                    <tr class="gridRowEven">
                                                                                                                                                                                                    </s:else>

                                                                                                                                                                                                <td class="gridColumn" align="left"><a href="getEmployee.action?empId=<s:property value="EmpId"/>&currId=<s:property value="id"/>&navId=1"><img src="../includes/images/DBGrid/newEdit_17x18.png"></a></td>
                                                                                                                                                                                                <td class="gridColumn" align="left"><s:property value="empState"/></td>
                                                                                                                                                                                                <td class="gridColumn" align="left"><s:property value="stateStartDate"/></td>
                                                                                                                                                                                                <td class="gridColumn" align="left"><s:property value="stateEndDate"/></td>

                                                                                                                                                                                                <%-- <td class="gridColumn" align="left"><s:property value="skillSet"/></td> --%>
                                                                                                                                                                                                <%--  <td class="gridColumn" align="left"><a href="#" onclick="getSkillSet(<s:property value="EmpId"/>,<s:property value="id"/>);">Click To View</a></td>    --%>
                                                                                                                                                                                                <td class="gridColumn" align="left"><a href="javascript:getSkillSet(<s:property value="EmpId"/>,<s:property value="id"/>);">Click To View</a></td>  
                                                                                                                                                                                                <td class="gridColumn" align="left"><s:property value="prjName"/></td>
                                                                                                                                                                                                <s:if test="%{(empState=='OnProject')}">
                                                                                                                                                                                                    <td class="gridColumn" align="left"><s:property value="utilization"/></td>
                                                                                                                                                                                                </s:if>
                                                                                                                                                                                                <s:else>
                                                                                                                                                                                                    <td class="gridColumn" align="left"></td>
                                                                                                                                                                                                </s:else>
                                                                                                                                                                                                <td class="gridColumn" align="left"><s:property value="projectStatus"/></td>

                                                                                                                                                                                                <td class="gridColumn" align="left"><s:property value="createdDate1"/></td>
                                                                                                                                                                                                <%
                                                                                                                                                                                                    rolesMap = (Map) session.getAttribute("myRoles");
                                                                                                                                                                                                    //if(session.getAttribute("userId").toString().equals("rijju")||session.getAttribute("userId").toString().equals("nfatima")||rolesMap.containsValue("Admin")){
                                                                                                                                                                                                   if(session.getAttribute("userId").toString().equals("rijju")||session.getAttribute("userId").toString().equals("nfatima")||rolesMap.containsValue("Admin") ||session.getAttribute("userId").toString().equals("kfrance") || session.getAttribute("userId").toString().equals("kardaiz") || session.getAttribute("userId").toString().equals("mfast")){

                                                                                                                                                                                                %>
                                                                                                                                                                                                <td class="gridColumn" align="left"><a href="deleteEmpStatus.action?empId=<s:property value="EmpId"/>&currId=<s:property value="id"/>"><img src="../includes/images/DBGrid/Delete.png"></a></td>
                                                                                                                                                                                                        <%}%>
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
                                                                                                                                                                                <script type="text/JavaScript">
             
                                                                                                                                                                                    var cal5 = new CalendarTime(document.forms['empCurrState'].elements['stateStartDate']);
                                                                                                                                                                                    cal5.year_scroll = true;
                                                                                                                                                                                    cal5.time_comp = false;
                                                                                                                                                                                    var cal6 = new CalendarTime(document.forms['empCurrState'].elements['stateEndDate']);
                                                                                                                                                                                    cal6.year_scroll = true;
                                                                                                                                                                                    cal6.time_comp = false;
                                                                                                                                                                                </script>

                                                                                                                                                                                <%-- </sx:div> --%>
                                                                                                                                                                                </div>	

                                                                                                                                                                                <!-- Other Details START -->
                                                                                                                                                                                <%-- <sx:div id="otherDetailsTab" label="Other Details"> --%>
                                                                                                                                                                                <div id="otherDetailsTab" class="tabcontent"  >   
                                                                                                                                                                                    <s:form action="" name="otherDetails" theme="simple">
                                                                                                                                                                                        <table cellpadding="0" cellspacing="0" border="0" align="center" width="100%" style="padding-top:130px;">
                                                                                                                                                                                            <tr valign="middle">
                                                                                                                                                                                            <td valign="middle">
                                                                                                                                                                                                <table cellpadding="0" class="tableOfButtons" cellspacing="0" border="0" align="center">
                                                                                                                                                                                                    <tr>
                                                                                                                                                                                                    <td align="center">
                                                                                                                                                                                                        <a href="<s:url value="addressCheck.action?id=%{currentEmployee.id}"/>" 
                                                                                                                                                                                                        name="addresses" theme="simple">
                                                                                                                                                                                                        <img alt="address"
                                                                                                                                                                                                        src="<s:url value="/includes/images/address.gif"/>" 
                                                                                                                                                                                                        width="66px" 
                                                                                                                                                                                                        height="19px"
                                                                                                                                                                                                        border="0" align="bottom">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                    </td>
                                                                                                                                                                                                    <td>

                                                                                                                                                                                                    </td>
                                                                                                                                                                                                    <td align="center">
                                                                                                                                                                                                        <a href="<s:url value="ancillaryCheck.action?id=%{currentEmployee.id}"/>" name="ancillary" theme="simple">
                                                                                                                                                                                                        <img alt="ancillary"
                                                                                                                                                                                                        src="<s:url value="/includes/images/anclry.gif"/>" 
                                                                                                                                                                                                        width="66px" 
                                                                                                                                                                                                        height="19px"
                                                                                                                                                                                                        border="0" align="bottom">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                    </td>

                                                                                                                                                                                                    </tr>
                                                                                                                                                                                                    <tr>
                                                                                                                                                                                                    <td>

                                                                                                                                                                                                    </td>
                                                                                                                                                                                                    <td align="center">
                                                                                                                                                                                                        <a href="<s:url value="empTravelCheck.action?id=%{currentEmployee.id}"/>" name="travel" theme="simple">
                                                                                                                                                                                                        <img alt="travel"
                                                                                                                                                                                                        src="<s:url value="/includes/images/travel.gif"/>" 
                                                                                                                                                                                                        width="66px" 
                                                                                                                                                                                                        height="19px"
                                                                                                                                                                                                        border="0" align="bottom">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                    </td>
                                                                                                                                                                                                    <td>

                                                                                                                                                                                                    </td>
                                                                                                                                                                                                    </tr>
                                                                                                                                                                                                    <tr>
                                                                                                                                                                                                    <td align="center">
                                                                                                                                                                                                        <a href="<s:url value="insuranceCheck.action?empId=%{currentEmployee.id}"/>" name="insurance" theme="simple">
                                                                                                                                                                                                        <img alt="insurance"
                                                                                                                                                                                                        src="<s:url value="/includes/images/insr.gif"/>" 
                                                                                                                                                                                                        width="66px" 
                                                                                                                                                                                                        height="19px"
                                                                                                                                                                                                        border="0" align="bottom">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                    </td>
                                                                                                                                                                                                    <td align="center">
                                                                                                                                                                                                        <a style="text-decoration: none;" href="<s:url value="expensesCheck.action?id=%{currentEmployee.id}"/>" name="insurance" theme="simple">
                                                                                                                                                                                                        <input type="button" value="Expenses" class="buttonBg"/>
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                    </td>
                                                                                                                                                                                                    <td align="center">
                                                                                                                                                                                                        <a href="<s:url value="immigrationCheck.action?empId=%{currentEmployee.id}"/>" name="immigration" theme="simple">
                                                                                                                                                                                                        <img alt="immigration"
                                                                                                                                                                                                        src="<s:url value="/includes/images/immgrtion.gif"/>" 
                                                                                                                                                                                                        width="66px" 
                                                                                                                                                                                                        height="19px"
                                                                                                                                                                                                        border="0" align="bottom">
                                                                                                                                                                                                        </a>
                                                                                                                                                                                                    </td>
                                                                                                                                                                                                    </tr>
                                                                                                                                                                                                </table>
                                                                                                                                                                                            </td>
                                                                                                                                                                                            </tr>
                                                                                                                                                                                        </table>


                                                                                                                                                                                    </s:form>
                                                                                                                                                                                    <%--   </sx:div> --%>
                                                                                                                                                                                </div>
                                                                                                                                                                                <!-- Other Details END -->
                                                                                                                                                                                <!-- Image Upload Start -->
                                                                                                                                                                                <div id="imageUploadTab" class="tabcontent" > 
                                                                                                                                                                                    <s:form name="imageForm" action="updateImageAction" theme="simple" enctype="multipart/form-data" onsubmit="return fileValidation();">
                                                                                                                                                                                        <table border="0" width="100%" cellpadding="3" cellspacing="3">
                                                                                                                                                                                            <tr class="headerText">
                                                                                                                                                                                            <td align="left" width="670px">
                                                                                                                                                                                                <% if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                                                                                                                                                                                        out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                                                                                                                                                                                    }%>
                                                                                                                                                                                            </td>
                                                                                                                                                                                            <td align="right">
                                                                                                                                                                                                <s:hidden name="loginId" id="loginId" value="%{currentEmployee.loginId}"/>
                                                                                                                                                                                                <s:hidden name="id" id="id" value="%{currentEmployee.id}"/>
                                                                                                                                                                                                <img alt="Home" src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" width="100%" height="13px" border="0">                                    
                                                                                                                                                                                            </td>
                                                                                                                                                                                            </tr>
                                                                                                                                                                                            <tr>
                                                                                                                                                                                            <td align="center">
                                                                                                                                                                                                <input type="file" name="imagePath" id="imagePath" class="inputTextBlueLarge" onchange="fileValidation(document.imageForm.imagePath.value);"/>
                                                                                                                                                                                            </td>
                                                                                                                                                                                            </tr>
                                                                                                                                                                                            <tr>
                                                                                                                                                                                            <td style="padding-left:15px;" colspan="6" align="center"><input type="submit" value="Upload" class="buttonBg"/></td>
                                                                                                                                                                                            </tr>
                                                                                                                                                                                            <tr class="fieldLabel">
                                                                                                                                                                                            <td align="center" colspan="6">
                                                                                                                                                                                                *Image size must be less than 2MB
                                                                                                                                                                                            </td>
                                                                                                                                                                                            </tr>
                                                                                                                                                                                        </table>
                                                                                                                                                                                    </s:form>
                                                                                                                                                                                    <%--  </sx:div> --%>
                                                                                                                                                                                </div>
                                                                                                                                                                                <!-- Image Upload End-->
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
                                                                                                                                                                
                                                                                                                                                                
	
		<script type="text/javascript">
		$(window).load(function(){
		isPhysicallyDisabled();
		confdentialInfoDisplay();
		showAddStatus();
		showPrevExp(lateralFlag);

		});
		</script>
   
                                                                                                                                                                </body>
                                                                                                                                                                </html>