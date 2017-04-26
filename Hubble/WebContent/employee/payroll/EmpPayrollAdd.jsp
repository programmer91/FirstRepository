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
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeAjax.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpDetailsValidation.js"/>"></script>   
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
  <%--  <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmpStandardClientValidations.js"/>"></script> --%>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
   <%-- <script type="text/JavaScript" src="<s:url value="/includes/javascripts/EmployeeLocation.js"/>"></script> --%>
    
    
    
    
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css"/>">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <s:include value="PayrollCalculations.jsp"/>

    <script>
        $(document).ready(function(){
            $("#calculator").click(function(){
                $("#calcDiv").slideToggle(1000);
                                
            });
        });
    </script>
    <s:include value="/includes/template/headerScript.html"/>
</head>
<%-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="validateCtc();getLocation();"> --%>
<%-- <body  class="bodyGeneral" oncontextmenu="return false;" onload="setSaveOrUpdateButton();"> --%>
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
                            <ul id="accountTabs1" class="shadetabs" >
                                <li align="center"><a href="#" class="selected" rel="employeePayrollEdit"  >Employee Edit</a></li>
                            </ul>
                            <div id="employeePayrollEdit" class="tabcontent"  style="border:1px solid gray; width:850px;height: 660px; overflow:hidden; margin-bottom: 1em;">
                                <br>
                                <ul id="accountTabs" class="shadetabs" >
                                    <li ><a href="#" id="employeeDetails1" class="selected" rel="employeeDetails"  tabindex="1">Employee Details</a></li>
                                    <li ><a href="#"  id="contactDetails1" rel="contactDetails" tabindex="23">Contact Details</a></li>
                                    <li ><a href="#"  id="payrollDetails1" rel="payrollDetails" tabindex="37">Payroll Details</a></li>
                                 <%--  <s:if test="employerId == 0">
                                         <li><a href="#" style="display:none" rel="insuranceSavingsTab"  tabindex="66">Tax Exemptions</a></li>
                                    </s:if>
                                    <s:else>
                                        <li><a href="#" rel="insuranceSavingsTab"  tabindex="66">Tax Exemptions</a></li>
                                    </s:else> --%>
                                        <s:if test="employerId == 0">
                                         <li ><a href="#" style="display:none" rel="aggregations" tabindex="37">Aggregations</a></li>
                                          </s:if>
                                    <s:else>
                                        <li><a href="#" rel="aggregations"  tabindex="37">Aggregations</a></li>
                                        </s:else>
                                    <%-- <li ><a href="#" rel="otherDetailsTab" tabindex="83">Other Details</a></li>
                                     <li ><a href="#" rel="tdsTab" tabindex="89">TDS</a></li> --%>
                                </ul>

                                <div  style="border:1px solid gray; width:850px;overflow:auto; margin-bottom: 1em;">
                                    <div id="overlayPayrollListEditTef"></div>              <!-- Start Overlay -->
                                    <div id="specialBoxPayrollListEditTef">


                                        <s:form theme="simple"  align="center" name="overlayPayrollListTef" action="%{currentAction}" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"   >
                                            <s:hidden id="tefId" name="tefId" value=""/>
                                            <s:hidden id="tefEmpId" name="tefEmpId" value=""/>
                                            <s:hidden id="flagForOverlay" name="flagForOverlay" value="2"/>
                                            <s:hidden id="categoryId" name="categoryId" value=""/>
                                             <s:hidden id="fromTef" name="fromTef" value="0"/>
                                            <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="headerLabel"></span>


                                                        </h3></td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="taxExemptionUpdatetoggleOverlay()" >
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                        </a>  

                                                    </td></tr>
                                                <tr>
                                                    <td colspan="4">
                                                        <div id="load" style="color: green;display: none;">Loading..</div>
                                                        <div id="resultMessageTefUpdate"></div>
                                                    </td>
                                                </tr>
                                                <tr><td colspan="4">
                                                        <table style="width:80%;" align="center">

                                                            <tr>
                                                                <td class="fieldLabel" >Type of Exemption :<FONT color="red"  ><em>*</em></FONT> </td>
                                                                <td id="overLayexemptionTypeTd"width="15%"> <s:select id="overLayexemptionType"  name="exemptionType"  list="exemptionTypeMap" headerKey="" headerValue="--Please Select--"   value="%{}" cssClass="inputSelect"  disabled="False"/>
                                                                    <span id="exemptionTypeValue"></span>                 
                                                                </td>
                                                                <td class="fieldLabel" >Payment&nbsp;Date:<FONT color="red"  ><em>*</em></FONT></td>
                                                                <td colspan="">
                                                                    <s:textfield id="paymentDateEmp" name="paymentDateEmp" cssClass="inputTextBlue" onchange="isValidDate(this)" value="%{}" />
                                                                    <a href="javascript:cal5.popup();">
                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                </td> 


                                                            </tr>      

                                                            <%--<tr>

                                                                <td class="fieldLabel">Year(YYYY):</td>
                                                                <td>

                                                                    <s:textfield name="yearOverlay" id="yearOverlay" maxlength="4" cssClass="inputTextBlue" value="%{yearOverlay}" onchange="getDaysForTheSelectedMonth();"/>
                                                                </td>
                                                                <td class="fieldLabel">Month:</td>
                                                                <td><s:select list="#@java.util.LinkedHashMap@{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}" name="monthOverlay" id="monthOverlay"  headerValue="select" headerKey="0" value="%{monthOverlay}" cssClass="inputSelectSmall" onchange="getDaysForTheSelectedMonth();"/></td>
                                                            </tr>--%>
                                                            <tr>

                                                                <td align="left" class="fieldLabel" >Applied Amount&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                <td>
                                                                    <s:textfield id="overlaySavingAmount" name="savingAmount" cssClass="inputTextReadOnlyPayroll" value="%{}" readonly="true"/>


                                                                </td>
                                                                <td align="left" class="fieldLabel" >Approved Amount&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                <td>
                                                                    <s:textfield id="overlayApprovedAmount" name="approvedAmount" cssClass="inputTextBlue" value="%{}" onkeyup="isNumberKeyWage(this);" onchange=""/>
                                                                    <s:hidden id="overlayApprovedAmountHidden" name="overlayApprovedAmountHidden" cssClass="inputTextBlue" value="%{}" onkeyup="isNumberKeyWage(this);" onchange=""/>


                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel" >Status :<FONT color="red"  ><em>*</em></FONT> </td>
                                                                <td  colspan="3"> <s:select id="overLaystatus"  name="status"  list="{'Approved','Rejected'}" headerKey=""    value="%{}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/>
                                                                    <span id="stateLabel"></span>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="fieldLabel" valign="top">Comments:</td>
                                                                <td colspan="3" valign="top"><s:textarea rows="3" cols="95" name="comments" cssClass="inputTextarea1" value="%{}" onchange="fieldLengthValidator(this);" id="comments"/></td>


                                                            </tr>


                                                            <%--     <tr id="uploadTr" style="display: none"> 

                                                    <td class="fieldLabel">Upload File :</td>
                                                    <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="putFileName();"/></td> 
                                                    <td  align="center" >
                                                        <input type="button" value="Save" onclick="return ajaxFileUploadTaxAssumption();" class="buttonBg" id="addButton"/> 


                                                       
                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                    </td>
                                                </tr>
                                               
                                                            --%>

                                                            <tr>
                                                                <td class="fieldLabel">Attachments :</td> 
                                                                <td colspan="">   <a class="navigationText" href="#" onclick="downloadTefAttachment();" id="downloadLink" style="display: block;">

                                                                        <span id="downloadFile"></span>

                                                                    </a>

                                                                </td> 

                                                                <td colspan="2" align="right"><div id="update" ><input type="button"  value="Update" onclick="return updatePayrollTaxExemptionForPayollList();" class="button_payroll"/> </div></td>
                                                            </tr>
                                                            <%--<tr style="display: none" id="downloadTr">

                                                    <td class="fieldLabel"></td> 
                                                    <td colspan="">   
                                                   
                                                    </td> 

                                                                    <td align="right"> <table></table></td>
                                                </tr> --%> 


                                                        </table>
                                                    </td>
                                                </tr>


                                            </table>
                                        </s:form>              

                                        <script type="text/JavaScript">
                                            /*   var cal1 = new CalendarTime(document.forms['addReviewForm'].elements['overlayReviewCreatedDate']);
                                               cal1.year_scroll = true;
                                               cal1.time_comp = false;*/
                                            var cal5 = new CalendarTime(document.forms['overlayPayrollListTef'].elements['paymentDateEmp']);
                                            cal5.year_scroll = true;
                                            cal5.time_comp = false;
                                                                                                                         
                                        </script>

                                    </div>
                                    <div id="payrollOverlay"></div>
                                    <div id="hubblePayrollOverlay" align="center">
                                        <s:form theme="simple" >
                                            <table style="width: 100%;">

                                                <tr class="overlayHeader">
                                                    <td colspan="2">

                                                        <font style="vertical-align: middle;font-family:myHeaderFonts;color: white; font-size:20px ;text-align: left;">
                                                        Payroll Add</font> 
                                                        <div  class="closeButton" align="right">
                                                            <a href="#" onclick="toggleOverlayForProfile();" style="color:#869fa3;font-family: myHeaderFonts;text-decoration: none; ">CLOSE</a>
                                                        </div> 
                                                    </td>
                                                </tr>
                                               
                                                <tr>
                                                    <td>
                                                        <br>
                                                        <table style="width:70%;">
                                                            <tr>
                                                                <td colspan="4">
                                                                    <div id="resultMessage"></div>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="overlayfieldLabelWagess">Employee&nbsp;Id:</td>
                                                                <td colspan="2">
                                                                    <s:textfield  id="empEmailId" name="empEmailId" />
                                                                </td>
                                                                <td>
                                                                    <input type="button" class="button_payroll" value="Next" onclick="checkEmpExitsForPayroll();"/>
                                                                </td>
                                                            </tr>
                                                            

                                                        </table>

                                                    </td>
                                                </tr>

                                            </table>

                                        </s:form>
                                    </div>
                                    <!-- employee Details end -->

                                    <div id="payrollResultMessage" style="color: green;font-size: 15px;"></div>
                                    <div id="loadingMessage" style="color: red;font-size: 15px;display: none;">Loading please wait..</div>
                                    <div id="loadingMessageForFreeze" style="color: red;font-size: 15px;display: none;"></div>


                                    <div id="employeeDetails" class="tabcontent"  >
                                        <br>
                                        <s:form name="employeeDetails" id="employeeDetails" action="employeeDetailsUpdate" method="post" theme="simple" >

                                            <s:hidden name="payrollFlag" id="payrollFlag" cssClass="inputTextBlueWages" value="%{payrollFlag}" />
                                            <s:hidden name="empId" id="empId" cssClass="inputTextBlueWages" value="%{empId}" />
                                            <s:hidden name="payrollAddOrUpdate" id="payrollAddOrUpdate" cssClass="inputTextBlueWages" value="%{payrollAddOrUpdate}" />


                                            <s:hidden name="homeAddressId" id="homeAddressId" cssClass="inputTextBlueWages" value="%{homeAddressId}" />
                                            <div align="center" style=" border-radius: 25px;
                                                 border: 2px solid #3e93d4;
                                                 padding: 20px;
                                                 width: 700px;
                                                 height: auto;
                                                 margin-right: 30%;
                                                 position: relative;background-color:#c5def2; ">
                                                <table cellpadding="1" cellspacing="1" border="0" width="80%">


                                                    <s:hidden name="firstName" id="firstName" cssClass="inputTextBlueWages" value="%{firstName}" />     

                                                    <s:hidden name="lastName" id="lastName" cssClass="inputTextBlueWages" value="%{lastName}" />

                                                    <s:hidden name="middleName" id="middleName" cssClass="inputTextBlueWages" value="%{middleName}" />              

                                                    <tr>
                                                        <td colspan="4" align="right">
                                                            <div style="margin-right: 7%;">
                                                                <table border="0" >
                                                                    <tr>
                                                                        <td>
                                                                            <INPUT type="button" CLASS="btnPayrollWage" value="Back to List" onClick="history.go(-1)" tabindex="">
                                                                        </td>  
                                                                        <td >
                                                                            <div id="new" >
                                                                                <input type="button" class="btnPayrollWage" value="New" id="new00" onclick="confirmForNewPayrollAdd();" tabindex=""/>
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <div id="save">
                                                                                <input type="button" class="btnPayrollWage" value="Next" id="save00" onclick="showNextTab('contactDetails1');" tabindex="22"/>
                                                                            </div>
                                                                            <div id="update" >
                                                                                <input type="button" class="btnPayrollWage" id="update00" value="Update" onclick="addPayrollDetails('EmpDetails');" tabindex="22"/>
                                                                                
                                                                            </div>
                                                                        </td>
                                                                          <td>
                                            <input type="button" class="btnPayrollWage" value="Cancel" onclick="window.location.href=window.location.href"  tabindex="24"/>
    
                                        </td>
                                                                        

                                                                    </tr>
                                                                </table>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabelWages"  align="right">Emp&nbsp;Name&nbsp;:</td>                           

                                                        <td colspan="3"><font class="navigationTextWages"><s:property value="%{employeeName}"/></font> </td>    

                                                    </tr>

                                                    <tr>
                                                        <td class="fieldLabelWages" colspan="">Organization :</td>

                                                        <td colspan="3">
                                                            <s:if test="employerId == 0">
                                                                <s:select 
                                                                    name="orgId" 
                                                                    id="orgId"
                                                                    headerKey="1"
                                                                    headerValue="-- Please Select --"
                                                                    list="orgIdMap" 
                                                                    cssClass="inputSelectWagesEmpUpdate" 
                                                                    value="%{orgId}" tabindex="2"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:select 
                                                                    name="orgId" 
                                                                    id="orgId"
                                                                    headerKey="1"
                                                                    headerValue="-- Please Select --"
                                                                    list="orgIdMap" 
                                                                    cssClass="inputSelectWagesEmpUpdate" 
                                                                    value="%{orgId}" tabindex="2"/>
                                                            </s:else>

                                                        </td>


                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabelWages"  align="right">Department&nbsp;:</td>
                                                        <td><s:select 
                                                                name="departmentId" 
                                                                id="departmentId"
                                                                headerKey=""
                                                                headerValue="All"
                                                                list="departmentIdList" cssClass="inputSelectWages" value="%{departmentId}" onchange="getEmpTitleData();getPracticeDataV1();" tabindex="3"/></td>
                                                         <td class="fieldLabelWages" align="right" >Practice Name :</td>
                                                          <td><s:select label="Select Practice Name" 
                                                  name="practiceId"  id="practiceId"
                                                  headerKey=""
                                                  headerValue="-- Please Select --"
                                                  list="practiceList" cssClass="inputSelect" value="%{practiceId}"  /> <%--onchange="getTeamData();" --%></td>  
                              
                                                        </tr>
                                                    <tr>
                                                         <td class="fieldLabelWages"  align="right" >Job&nbsp;Title&nbsp;:</td>
                                                        <td><s:select
                                                                headerKey=""
                                                                headerValue="-- Please Select --"
                                                                name="titleId" 
                                                                id="titleId"
                                                                list="titleIdList" cssClass="inputSelectWages" value="%{titleId}" onchange="" tabindex="4"/></td>
                                                   
                                                        <td class="fieldLabelWages"  align="right">Shift&nbsp;:</td>
                                                        <td><s:select 
                                                                name="shiftId" 
                                                                id="shiftId"
                                                                headerKey="0"
                                                                headerValue="All"
                                                                list="shiftList" cssClass="inputSelectWages" tabindex="5"/></td>
                                                          </tr>

                                                    <tr>

<td class="fieldLabelWages"  align="right" >Classification&nbsp;:</td>
                                                        <td><s:select 
                                                                name="classificationId" 
                                                                id="classificationId"
                                                                headerKey="0"
                                                                headerValue="All"
                                                                list="classificationList" cssClass="inputSelectWages" tabindex="6"/></td>
                                                  
                                                        <td class="fieldLabelWages" align="right">Location&nbsp;:</td>
                                                        <td><s:select 
                                                                name="locationId" 
                                                                id="locationId"
                                                                headerKey=""
                                                                headerValue="--Please Select--"
                                                                list="locationsList" cssClass="inputSelectWages" value="%{locationId}" tabindex="7"/></td>
                                                      
                                                    </tr>
                                                    <tr>

                                                        <td class="fieldLabelWages" align="right">Gender&nbsp;:</td>                           

                                                        <td><s:select 
                                                                name="gender"  id="gender"
                                                                list="genderList" cssClass="inputSelectWages" value="%{gender}" tabindex="9"/>
                                                        </td>  
                                                        <td class="fieldLabelWages"  align="right">Current&nbsp;Status:</td>                           

                                                        <td><s:select 
                                                                name="currStatus"  id="currStatus"
                                                                list="currStatusList" cssClass="inputSelectWages" value="%{currStatus}" tabindex="10" contenteditable="true"/></td>         
                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabelWages"  align="">Itg&nbsp;Batch:</td>
                                                        <td><s:textfield name="itgBatch" id="itgBatch" cssClass="inputTextBlueWages" value="%{itgBatch}" onchange="payRollFieldLengthValidator(this);"  tabindex=""/></td>


                                                        <td class="fieldLabelWages"  align="right">PayRollId:</td>                           

                                                        <td><s:textfield name="employerId" id="employerId" cssClass="inputTextReadOnlyColoredTextBoxes" value="%{employerId}" onchange="payRollFieldLengthValidator(this);" readonly="true" tabindex="12"/></td>      
                                                    </tr>
                                                    <tr>


                                                        <td class="fieldLabelWages"  align="right">PAN&nbsp;No.&nbsp;:</td>

                                                        <td><s:textfield name="ssn" id="ssn" cssClass="inputTextBlueWages" value="%{ssn}" onchange="payRollFieldLengthValidator(this);" tabindex="13"/></td>

                                                        <td class="fieldLabelWages"  align="left">Training&nbsp;Period&nbsp;:</td>                           

                                                        <td><s:textfield name="trainingPeriod" id="trainingPeriod" cssClass="inputTextBlueWages" value="%{trainingPeriod}" onchange="payRollFieldLengthValidator(this);" tabindex="16"/></td> 


                                                    </tr>
                                                    <tr>
                                                        <td class="fieldLabelWages" width="200px" align="right">PF&nbsp;Account&nbsp;No.&nbsp;:</td>                           

                                                        <td><s:textfield name="pfAccount" id="pfAccount" cssClass="inputTextBlueWages" value="%{pfAccount}" onchange="payRollFieldLengthValidator(this);" tabindex="15"/></td>      

                                                        <td class="fieldLabelWages"  align="left" >Contract&nbsp;Period&nbsp;:</td>                           

                                                        <td ><s:textfield name="contractPeriod" id="contractPeriod" cssClass="inputTextBlueWages" value="" onchange="payRollFieldLengthValidator(this);" tabindex="17"/></td> 

                                                    </tr>
                                                    <tr>

                                                        <td class="fieldLabelWages"  align="left">Passport No.&nbsp;:</td>                           

                                                        <td ><s:textfield name="passportNo" id="passportNo" cssClass="inputTextBlueWages" value="%{passportNo}" onchange="payRollFieldLengthValidator(this);" tabindex="14"/></td>               


                                                        <td class="fieldLabelWages"  align="right">Date&nbsp;Of&nbsp;Joining&nbsp;:</td>

                                                        <td><s:textfield name="dateOfJoining" id="dateOfJoining" cssClass="inputTextBlueWages" value="%{dateOfJoining}" onchange="isValidDate(this)" tabindex="18"/><a href="javascript:cal2.popup();">
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                     width="20" height="20" border="0"></td>
                                                                </tr>
                                                                <tr>




                                                                    <td class="fieldLabelWages"  align="right">UAN&nbsp;No&nbsp;:</td>                           

                                                                    <td ><s:textfield name="UANNo" id="UANNo" cssClass="inputTextBlueWages" value="%{UANNo}" onchange="payRollFieldLengthValidator(this);" tabindex="19"/></td>      

                                                                    <td class="fieldLabelWages"  align="right">Date&nbsp;Of&nbsp;Employeement&nbsp;:</td>

                                                                    <td><s:textfield name="dateOfEmployement" id="dateOfEmployement" cssClass="inputTextBlueWages" value="%{dateOfEmployement}" onchange="isValidDate(this)" tabindex=""/><a href="javascript:cal1.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                 width="20" height="20" border="0"></td>




                                                                            </tr>
                                                                            <tr>

                                                                                <td class="fieldLabelWages"  align="right">Adhar&nbsp;No:</td>

                                                                                <td><s:textfield name="adharNo" id="adharNo" cssClass="inputTextBlueWages" value="%{adharNo}" onchange="payRollFieldLengthValidator(this);" tabindex="20"/></td>

                                                                                <td id="dateOfTerminationTD1" class="fieldLabelWages"  align="">Date&nbsp;Of&nbsp;Termination&nbsp;:</td>

                                                                                <td id="dateOfTerminationTD2"><s:textfield name="dateOfTermination" id="dateOfTermination" cssClass="inputTextBlueWages" value="%{dateOfTermination}" onchange="isValidDate(this)" tabindex="" readonly="true"/><a href="javascript:cal4.popup();">
                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                             width="20" height="20" border="0"></td>


                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td class="fieldLabelWages"  align="right" >PF&nbsp;Flag&nbsp;:</td>
                                                                                            <td > <s:checkbox name="isPfFlag" id="isPfFlag"
                                                                                                        value="%{isPfFlag}" 
                                                                                                        theme="simple" tabindex="11" onchange=""/> </td>

                                                                                            <td class="fieldLabelWages"  align="right" >ESI&nbsp;Flag&nbsp;:</td>
                                                                                            <td > <s:checkbox name="esiFlag" id="esiFlag"
                                                                                                        value="%{esiFlag}" 
                                                                                                        theme="simple" tabindex="" onchange=""/> </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                          <td class="fieldLabelWages"  align="right" >No&nbsp;Salary&nbsp;:</td>
                                                                                            <td> <s:checkbox name="noSalary" id="noSalary"
                                                                                                        value="%{noSalary}" 
                                                                                                        theme="simple" tabindex="11" onchange=""/> </td>  
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td class="fieldLabelWages">Comments:</td>  
                                                                                            <td colspan="5"><s:textarea name="payRollComments" id="payRollComments" cols="85" rows="3" cssClass="inputTextArea" value="%{payRollComments}"  tabindex="" onchange="payRollFieldLengthValidator(this);"/></td>
                                                                                        </tr>
                                                                                        <tr id="resonsForLeavingTr">
                                                                                            <td class="fieldLabelWages">Reason&nbsp;for&nbsp;Leaving&nbsp;:</td>  
                                                                                            <td colspan="5"><s:textarea name="resonsForLeaving" id="resonsForLeaving" cols="85" rows="3" cssClass="inputTextArea" value="%{resonsForLeaving}" onchange="payRollFieldLengthValidator(this);" tabindex="21" readonly="true"/></td>
                                                                                        </tr>

                                                                                        </table>
                                                                                        </div>
                                                                                    </s:form>
                                                                                    <script>
                                                                                        var cal1 = new CalendarTime(document.forms['employeeDetails'].elements['dateOfEmployement']);
                                                                                        cal1.year_scroll = true;
                                                                                        cal1.time_comp = false;                       
                                                                                        var cal2 = new CalendarTime(document.forms['employeeDetails'].elements['dateOfJoining']);
                                                                                        cal2.year_scroll = true;
                                                                                        cal2.time_comp = false;
                                                                                        var cal4 = new CalendarTime(document.forms['employeeDetails'].elements['dateOfTermination']);
                                                                                        cal4.year_scroll = true;
                                                                                        cal4.time_comp = false;
                                            
                                                                                           
                                                                                    </script>

                                                                                    </div>
                                                                                    <!-- employee Details end -->

                                                                                    <!-- contact Details START -->
                                                                                    <div id="contactDetails" class="tabcontent"  >
                                                                                        <br>
                                                                                        <s:form action="contactDetailsUpdate" theme="simple" method="post" id="contactDetails" name="contactDetails" onsubmit="">
                                                                                            <div align="center" style=" border-radius: 25px;
                                                                                                 border: 2px solid #3e93d4;
                                                                                                 padding: 20px;
                                                                                                 width: 700px;
                                                                                                 height: auto;
                                                                                                 margin-right: 30%;
                                                                                                 position: relative;background-color:#c5def2; ">
                                                                                                <table cellpadding="1" cellspacing="1" border="0" width="80%">
                                                                                                    <tr>
                                                                                                        <td colspan="6"align="right">
                                                                                                            <div style="margin-right: 7%;">
                                                                                                                <table>
                                                                                                                    <tr>
                                                                                                                        <td  >
                                                                                                                            <INPUT type="button" CLASS="btnPayrollWage" value="Back to List" onClick="history.go(-1)" tabindex="">
                                                                                                                        </td>  
                                                                                                                        <td >
                                                                                                                            <div id="new1">
                                                                                                                                <input type="button" class="btnPayrollWage" value="New" id="new11" onclick="confirmForNewPayrollAdd();" tabindex=""/>
                                                                                                                            </div>
                                                                                                                        </td>
                                                                                                                        <td >
                                                                                                                            <div id="save1" >
                                                                                                                                <input type="button" class="btnPayrollWage" value="Next" id="save11" onclick="showNextTab('payrollDetails1');" tabindex="36"/>
                                                                                                                            </div>
                                                                                                                            <div id="update1" >
                                                                                                                                <input type="button" class="btnPayrollWage" id="update11" value="Update All" onclick="addPayrollDetails('ContactDetails');" tabindex="36"/>
                                                                                                                            </div>
                                                                                                                        </td>
                                                                                                                          <td>
                                            <input type="button" class="btnPayrollWage" value="Cancel" onclick="window.location.href=window.location.href"  tabindex="24"/>
    
                                        </td>

                                                                                                                    </tr>
                                                                                                                </table>
                                                                                                            </div>
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                    <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">Emp&nbsp;Name&nbsp;:</td>                           

                                                                                                        <td colspan="5"><font class="navigationTextWages"><s:property value="%{employeeName}"/></font> </td>  

                                                                                                    </tr>

                                                                                                    <tr>
                                                                                                        <td class="fieldLabelWages">Address&nbsp;:</td>  
                                                                                                        <td colspan="5"><s:textarea name="address" id="address" cols="85" rows="3" cssClass="inputTextArea" value="%{address}" onchange="payRollFieldLengthValidator(this);" tabindex="24"/></td>
                                                                                                    </tr>

                                                                                                    <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">Corporate&nbsp;Email:</td>                           

                                                                                                        <td colspan=""><s:textfield name="corporateEmail" id="corporateEmail" cssClass="inputTextBlueWages" value="%{corporateEmail}" onchange="payRollFieldLengthValidator(this); checkEmail(this);" tabindex="25"/></td>      

                                                                                                        <td class="fieldLabelWages"  align="right" colspan="3">Personal&nbsp;Email:</td>

                                                                                                        <td><s:textfield name="personalEmail" id="personalEmail" cssClass="inputTextBlueWages" value="%{personalEmail}" onchange="payRollFieldLengthValidator(this); checkEmail(this);" tabindex="26"/></td>
                                                                                                    </tr>
                                                                                                    <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">Father&nbsp;Name:</td>

                                                                                                        <td colspan=""><s:textfield name="fathername" id="fathername" cssClass="inputTextBlueWages" value="%{fathername}" onchange="payRollFieldLengthValidator(this);" tabindex="27"/></td>


                                                                                                        <td class="fieldLabelWages"  align="right" colspan="3">Father&nbsp;Title:</td>

                                                                                                        <td><s:textfield name="fatherTitle" id="fatherTitle" cssClass="inputTextBlueWages" value="%{fatherTitle}" onchange="payRollFieldLengthValidator(this);" tabindex="28"/></td>


                                                                                                    </tr>
                                                                                                    <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">City&nbsp;:</td>                           

                                                                                                        <td ><s:textfield name="city" id="city" cssClass="inputTextBlueWages" value="%{city}" onchange="payRollFieldLengthValidator(this);" tabindex="29"/></td>      

                                                                                                        <td class="fieldLabelWages"  align="right" colspan="3">State&nbsp;:</td>

                                                                                                        <td><s:textfield name="state" id="state" cssClass="inputTextBlueWages" value="%{state}" onchange="payRollFieldLengthValidator(this);" tabindex="30"/></td>
                                                                                                    </tr>
                                                                                                    <tr>
                                                                                                        <td class="fieldLabelWages"  align="left">Zip&nbsp;:</td>                           

                                                                                                        <td ><s:textfield name="zip" id="zip" cssClass="inputTextBlueWages" value="%{zip}" onchange="payRollFieldLengthValidator(this);" tabindex="31"/></td>               


                                                                                                        <td class="fieldLabelWages"  align="right" colspan="3">Father&nbsp;Phone:</td>                           

                                                                                                        <td ><s:textfield name="fatherPhone" id="fatherPhone" cssClass="inputTextBlueWages" value="%{fatherPhone}" onchange="payRollFieldLengthValidator(this);" tabindex="32"/></td>      
                                                                                                    </tr>
                                                                                                    <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">Home&nbsp;Phone:</td>

                                                                                                        <td><s:textfield name="homePhone" id="homePhone" cssClass="inputTextBlueWages" value="%{homePhone}" onchange="payRollFieldLengthValidator(this);" tabindex="33"/></td>

                                                                                                        <td class="fieldLabelWages"  align="left" colspan="3">Mobile&nbsp;Phone:</td>                           

                                                                                                        <td ><s:textfield name="mobilePhone" id="mobilePhone" cssClass="inputTextBlueWages" value="%{mobilePhone}" onchange="payRollFieldLengthValidator(this);" tabindex="34"/></td>               
                                                                                                    </tr>
                                                                                                    <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">Work&nbsp;Phone:</td>

                                                                                                        <td><s:textfield name="workPhone" id="workPhone" cssClass="inputTextBlueWages" value="%{workPhone}" onchange="payRollFieldLengthValidator(this);" tabindex="35"/></td>
                                                                                                    </tr>
                                                                                                </table>
                                                                                            </div>
                                                                                        </s:form>
                                                                                    </div>
                                                                                    <!-- contact Details end -->
                                                                                    <!-- payroll Details START -->

                                                                                    <div id="payrollDetails" class="tabcontent"  >   

                                                                                        <s:form action="" name="payrollDetails" theme="simple">
                                                                                            <div align="center" style=" border-radius: 25px;
                                                                                                 border: 2px solid #3e93d4;
                                                                                                 padding: 20px;
                                                                                                 width: 750px;
                                                                                                 height: 550px;
                                                                                                 margin-right: 30%;
                                                                                                 margin-top: 0.2%;
                                                                                                 position: relative;background-color:#c5def2;">
                                                                                                <table cellpadding="1" cellspacing="1" border="0"  align="left"  width="93%">
                                                                                                    <tr>
                                                                                                        <td colspan="6" align="right">
                                                                                                            <div style="margin-right: -7%;">
                                                                                                                <table>
                                                                                                                    <tr>
                                                                                                                        <td>
                                                                                                                            <INPUT type="button" CLASS="btnPayrollWage" value="Back to List" onClick="history.go(-1)" tabindex="">
                                                                                                                        </td>  
                                                                                                                        <td>
                                                                                                                            <div id="new2">
                                                                                                                                <input type="button" class="btnPayrollWage" value="New" id="new22" onclick="confirmForNewPayrollAdd();" tabindex=""/>
                                                                                                                            </div>
                                                                                                                        </td>
                                                                                                                        <td >
                                                                                                                            <div id="save2" >
                                                                                                                                <input type="button" class="btnPayrollWage" value="Save All" id="save22" onclick="addPayrollDetails('');" tabindex="65"/>
                                                                                                                            </div>

                                                                                                                        </td>
                                                                                                                        <td colspan="" align="right">

                                                                                                                            <input type="button" class="btnPayrollWage" value="Refresh" onclick="refreshPayroll();" />
                                                                                                                        </td>
                                                                                                                        <%-- <td>
                                                                                                                             <input type="button" CLASS="buttonBg" value="Backup" onclick="backupEmpPayRoll();" />
                                                                                                                         </td>  --%>
                                                                                                                        <td>
                                                                                                                            <div id="update2" >
                                                                                                                                <input type="button" class="btnPayrollWage" id="update22" value="Update All" onclick="addPayrollDetails('payrollDetails');" tabindex="65"/>
                                                                                                                            </div>
                                                                                                                        </td>
                                                                                                                          <td>
                                            <input type="button" class="btnPayrollWage" value="Cancel" onclick="window.location.href=window.location.href"  tabindex="24"/>
    
                                        </td>
                                                                                                                    </tr>
                                                                                                                </table>
                                                                                                            </div>
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                    <tr>
                                                                                                        <td colspan="6">
                                                                                                            <table cellpadding="1" cellspacing="1" border="0"  align="left"  width="100%">


                                                                                                                <tr><td class="fieldLabelWages"  align="right">Emp&nbsp;Name&nbsp;:</td>                           
                                                                                                                    <td colspan="5"><font class="navigationTextWages"><s:property value="%{employeeName}"/></font> </td>        

                                                                                                                </tr>
                                                                                                                <!-- start by nag -->

                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">Basic:</td>    
                                                                                                                    <td><s:textfield name="basic" id="basic" cssClass="inputTextBluePayroll" value="%{basic}"  onchange="payRollFieldLengthValidator(this);BasicChangeCheck();" onkeyup="isNumberKeyWage(this);" tabindex="38"/></td>
                                                                                                                    <td class="fieldLabelWages"  align="right">Payment&nbsp;Type:</td>    
                                                                                                                    <td><s:select 
                                                                                                                            name="paymentType" 
                                                                                                                            id="paymentType"
                                                                                                                            headerKey="All"
                                                                                                                            headerValue="All"
                                                                                                                            list="paymentTypeList" cssClass="inputSelectWages" tabindex="54"/></td>

                                                                                                                </tr>

                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">DA:</td>    
                                                                                                                    <td><s:textfield name="da" id="da" cssClass="inputTextReadOnlyPayroll" value="%{da}"  onchange="payRollFieldLengthValidator(this);"  readonly="true" /></td>
                                                                                                                    <td class="fieldLabelWages" align="right">Bank&nbsp;Name:</td>    
                                                                                                                    <td><s:select 
                                                                                                                            name="bankName" 
                                                                                                                            id="bankName"
                                                                                                                            headerKey="All"
                                                                                                                            headerValue="All"
                                                                                                                            list="bankNameList" cssClass="inputSelectWages" tabindex="55"/></td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">HRA:</td>    
                                                                                                                    <td><s:textfield name="hra" id="hra" cssClass="inputTextReadOnlyPayroll" value="%{hra}"  onchange="payRollFieldLengthValidator(this);" readonly="true"/></td>
                                                                                                                    <td class="fieldLabelWages" align="right">Bank&nbsp;Account&nbsp;No:</td>    
                                                                                                                    <td><s:textfield name="bankAccountNo" id="bankAccountNo" cssClass="inputTextBlueWages" value="%{bankAccountNo}" onkeyup="isNumberKeyWage(this);" onchange="payRollFieldLengthValidator(this);" tabindex="56"/></td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">TA:</td>    
                                                                                                                    <td><s:textfield name="ta" id="ta" cssClass="inputTextBluePayroll" value="%{ta}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="39"/></td>
                                                                                                                    <td class="fieldLabelWages" align="right">Employer&nbsp;PF&nbsp;:</td>    
                                                                                                                    <td><s:textfield name="employerPf" id="employerPf" cssClass="inputTextReadOnlyPayroll" value="%{employerPf}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" readonly="true"/></td>
                                                                                                                </tr><tr>  
                                                                                                                    <td class="fieldLabelWages"  align="right">RA:</td>    
                                                                                                                    <td><s:textfield name="ra" id="ra" cssClass="inputTextBluePayroll" value="%{ra}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="40"/></td>
                                                                                                                    <td class="fieldLabelWages" align="right">Employee&nbsp;PF&nbsp;:</td>    
                                                                                                                    <td><s:textfield name="employeePf" id="employeePf" cssClass="inputTextReadOnlyPayroll" readonly="true"  value="%{employeePf}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="57" /></td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">Entertainment:</td>    
                                                                                                                    <td><s:textfield name="entertainment" id="entertainment" cssClass="inputTextBluePayroll"  value="%{entertainment}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="41"/></td>
                                                                                                                    <td class="fieldLabelWages" align="right">Employer&nbsp;ESI&nbsp;:</td>    
                                                                                                                    <td><s:textfield name="employeresi" id="employeresi" cssClass="inputTextReadOnlyPayroll" readonly="true"  value="%{employeresi}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="58"  /></td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">Kids&nbsp;Education:</td>    
                                                                                                                    <td><s:textfield name="kidsEducation" id="kidsEducation" cssClass="inputTextBluePayroll" value="%{kidsEducation}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="42"/></td>
                                                                                                                    <td class="fieldLabelWages" align="right">Employee&nbsp;ESI&nbsp;:</td>    
                                                                                                                    <td><s:textfield name="employeeesi" id="employeeesi" cssClass="inputTextReadOnlyPayroll" readonly="true"  value="%{employeeesi}"  onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="59" /></td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">Vehicle&nbsp;Allowance:</td>    
                                                                                                                    <td><s:textfield name="vehicleAllowance" id="vehicleAllowance" cssClass="inputTextBluePayroll" value="%{vehicleAllowance}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="43"/></td>
                                                                                                                        <td class="fieldLabelWages" align="right">Life:</td>    
                                                                                                                    <td><s:textfield name="life" id="life" cssClass="inputTextBluePayroll" value="%{life}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="60"/></td>
                                                                                                               
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">CCA:</td>    
                                                                                                                    <td><s:textfield name="cca" id="cca" cssClass="inputTextBluePayroll" value="%{cca}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="44"/></td>
                                                                                                                                                                   <td class="fieldLabelWages" align="right">Health:</td>    
                                                                                                                    <td><s:textfield name="health" id="health" cssClass="inputTextBluePayroll" value="%{health}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="61"/></td>
                        
                                                                                        </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">Misc&nbsp;Pay:</td>    
                                                                                                                    <td><s:textfield name="miscPay" id="miscPay" cssClass="inputTextBluePayroll" value="%{miscPay}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="45"/></td>
                                                                                                                           <td class="fieldLabelWages" align="right">Professional Tax:</td>    
                                                                                                                    <td><s:textfield name="professionalTax" id="professionalTax" cssClass="inputTextReadOnlyPayroll" value="%{professionalTax}" onchange="payRollFieldLengthValidator(this);" readonly="true"/></td>
                                                                                                                <%-- <td class="fieldLabelWages"  align="right">Project&nbsp;End&nbsp;Date&nbsp;:</td>

                                                <td width="30%"><s:textfield name="projectEndDate" id="projectEndDate" cssClass="inputTextBlueWages" value="%{projectEndDate}" onchange="" tabindex="58"/><a href="javascript:cal3.popup();">
                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                             width="20" height="20" border="0"></td>  --%>
                                         
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages" align="right">Spl.&nbsp;Allowance:</td>    
                                                                                                                    <td><s:textfield name="splAllowance" id="splAllowance" cssClass="inputTextBluePayroll" value="%{splAllowance}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="46"/></td>
                                                                                                             </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">LongTermBonus:</td>    
                                                                                                                    <td><s:textfield name="longTermBonus" id="longTermBonus" cssClass="inputTextBluePayroll"  value="%{longTermBonus}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="47"/></td>
                                                                                                                 <td class="fieldLabelWages" align="right">Base&nbsp;Pay:</td>    
                                                                                                                    <td><s:textfield name="grossPay" id="grossPay" cssClass="inputTextReadOnlyImpPayroll" value="%{grossPay}" onchange="payRollFieldLengthValidator(this);" readonly="true"/></td>
                                                                                                                   <%--   <td class="fieldLabelWages" align="right">Other&nbsp;Deductions:</td>   --%> 
                                                                                                                    <td><s:hidden name="otherDeductions" id="otherDeductions" cssClass="inputTextBluePayroll" value="%{otherDeductions}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="62"/></td> 
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">Project&nbsp;Pay:</td>    
                                                                                                                    <td><s:textfield name="projectPay" id="projectPay" cssClass="inputTextBluePayroll"  value="%{projectPay}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="48"/></td>
                                                                                                                     <td class="fieldLabelWages" align="right">Variable&nbsp;Pay:</td>    
                                                                                                                    <td><s:textfield name="variablePay" id="variablePay" cssClass="inputTextReadOnlyImpPayroll" value="%{variablePay}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" readonly="true"/></td>
                                                                                                                <%-- <td class="fieldLabelWages" align="right">Net&nbsp;Paid:</td>    --%>
                                                                                                                     <td><s:hidden name="netPaidPayroll" id="netPaidPayroll" value="%{netPaidPayroll}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" readonly="true"/>

                                                                                                                    </td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">Attendance&nbsp;Allow:</td>    
                                                                                                                    <td><s:textfield name="attendanceAllow" id="attendanceAllow" cssClass="inputTextBluePayroll" value="%{attendanceAllow}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="49"/></td>
                                                                                                                    <td class="fieldLabelWages" align="right">Gross&nbsp;Pay:</td>    
                                                                                                                    <td><s:textfield name="newGrossPay" id="newGrossPay" cssClass="inputTextReadOnlyImpPayroll" value="%{newGrossPay}" onchange="payRollFieldLengthValidator(this);" readonly="true"/></td>
                                                                                                                 
                                                                                                             </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">OnProjectInd:</td>    

                                                                                                                    <td><s:checkbox name="onProjectInd" id="onProjectInd"
                                                                                                                                value="%{onProjectInd}" 
                                                                                                                                theme="simple" onchange=""  tabindex="49"/> <s:textfield name="onProjectIndValue1" id="onProjectIndValue1" cssClass="inputTextBlueWagesSmall" value="%{onProjectIndValue1}" onkeyup="isNumberKeyWage(this);" onchange="payRollFieldLengthValidator(this);" readonly=" " tabindex="50"/>
                                                                                                                        <s:textfield name="onProjectIndValue2" id="onProjectIndValue2" cssClass="inputTextBlueWagesSmall" value="%{onProjectIndValue2}" onchange="payRollFieldLengthValidator(this);"  onkeyup="isNumberKeyWage(this);" readonly=" "  tabindex="51"/></td>

                                                                                                                    <td class="fieldLabelWages" align="right">Total&nbsp;Cost:</td>    
                                                                                                                    <td><s:textfield name="totalCost" id="totalCost" cssClass="inputTextReadOnlyTotalCostPayroll" readonly="true" value="%{totalCost}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="63"/>
                                                                                                                        <%--   <s:hidden name="totalCostMatchedValue" id="totalCostMatchedValue" cssClass="inputTextBlueWages" value="" onchange="payRollFieldLengthValidator(this);"  readonly=""/> --%>

                                                                                                                        <div id="totalCostMatchDv"></div></td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td class="fieldLabelWages"  align="right">OnsiteInd.:</td>    

                                                                                                                    <td >  <s:checkbox name="onsiteInd" id="onsiteInd"
                                                                                                                                value="%{onsiteInd}" 
                                                                                                                                theme="simple" tabindex="52"/> 
                                                                                                                        <s:textfield name="onsiteIndV" id="onsiteIndV" cssClass="inputTextBluePayroll" value="%{onsiteIndV}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" readonly=" " tabindex="53"/></td>
                                                                                                                    <td class="fieldLabelWages" align="right">DatePay&nbsp;Revised:</td>    
                                                                                                                    <td><s:textfield name="datePayRevised" id="datePayRevised" cssClass="inputTextBluePayroll" value="%{datePayRevised}" onchange="isValidDate(this)" tabindex="64"/>
                                                                                                                        <a href="javascript:cal3.popup();">
                                                                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                 width="20" height="20" border="0">
                                                                                                                            </td>
                                                                                                                            </tr>
                                                                                                                            <tr>
                                                                                                                                <td class="fieldLabelWages" align="">Diff&nbsp;PF:</td>    
                                                                                                                                <td><s:textfield name="diffPF" id="diffPF" cssClass="inputTextBluePayroll" value="%{diffPF}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex=""/>
                                                                                                                                <td class="fieldLabelWages" align="right">Lock&nbsp;Amt.&nbsp;Start&nbsp;Date:</td>    
                                                                                                                                <td><s:textfield name="lockAmtStartDate" id="lockAmtStartDate" cssClass="inputTextBluePayroll" onchange="isValidDate(this)" value="%{lockAmtStartDate}" tabindex="64"/>
                                                                                                                                    <a href="javascript:cal5.popup();">
                                                                                                                                        <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                                                                                             width="20" height="20" border="0">
                                                                                                                                        </td>
                                                                                                                                        </tr>
                                                                                                                                        <tr>
                                                                                                                                            <td class="fieldLabelWages"  align="right"  >Is&nbsp;Fixed&nbsp;Salary</td>
                                                                                                                                            <td > <s:checkbox name="isFixedSalary" id="isFixedSalary"
                                                                                                                                                        value="%{isFixedSalary}" 
                                                                                                                                                        theme="simple" tabindex="11" onchange=""/> </td> 
                                                                                                                                            
                                                                                                                                            <td class="fieldLabelWages"  align="right"  >6&nbsp;Months&nbsp;Lock:</td>
                                                                                                                                            <td > <s:checkbox name="isSixMonthsLock" id="isSixMonthsLock"
                                                                                                                                                        value="%{isSixMonthsLock}" 
                                                                                                                                                        theme="simple" tabindex="11" onchange=""/> </td>  
                                                                                                                                               
                                                                                                                                        </tr>
                                                                                                                                        </table>
                                                                                                                                            <s:hidden   name="totalCostCalc" id="totalCostCalc" value="%{totalCost}" />

                                                                                                                                </td>
                                                                                                                            </tr>
                                                                                                                            <%-- <tr><td colspan="6"><div style="top:70px;left:63%;position: absolute;"><table><tr>
                                                                                                                                                <td colspan="">

                                                                                                                                                    <div class="verticalLine">
                                                                                                                                                    </div>

                                                                                                                                                </td>
                                                                                                                                                <td colspan="5">
                                                                                                                                                    <table>
                                                                                                                                                        <tr>
                                                                                                                                                            <td colspan="5" align="right">
                                                                                                                                                                <input type="button" class="btnPayrollWage" value="calculate" onClick="valuesToBeCalcForCompare();"/>
                                                                                                                                                            </td>
                                                                                                                                                        </tr>

                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">Total Cost :</td>
                                                                                                                                                            <td colspan="4"><s:textfield  cssClass="inputTextBluePayroll" name="totalCostCalc" id="totalCostCalc" value="%{totalCost}" size="8" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);"/></td>
                                                                                                                                                        </tr>

                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">LongTermBonus:</td>
                                                                                                                                                            <td colspan="4"> <s:textfield  cssClass="inputTextBluePayroll"  name="longBonusCalc" id="longBonusCalc" value="%{longTermBonus}" size="8" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);"/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">Employer&nbsp;PF :</td>
                                                                                                                                                            <td colspan="4"><s:textfield  cssClass="inputTextBluePayroll" name="employerPfCalc" id="employerPfCalc" value="%{employerPf}"  size="8" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);"/></td>
                                                                                                                                                        </tr>


                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">Attendance Allow:</td>
                                                                                                                                                            <td colspan="4"> <s:textfield  cssClass="inputTextBluePayroll"  name="attAllowCalc" id="attAllowCalc" value="%{attendanceAllow}" size="8" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);"/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">Health:</td>
                                                                                                                                                            <td colspan="4"> <s:textfield  cssClass="inputTextBluePayroll"  name="healthCalc" id="healthCalc" value="%{health}" size="8" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);"/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">Professional Tax:</td>
                                                                                                                                                            <td colspan="4"> <s:textfield  cssClass="inputTextBluePayroll"  name="pfTaxCalc" id="pfTaxCalc" value="%{professionalTax}" size="8" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);"/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">Monthly Salary:</td>
                                                                                                                                                            <td colspan="4"> <s:textfield  cssClass="inputTextReadOnlyColoredTextBoxes"  name="monthlySalary" id="monthlySalary" value="%{monthlySalary}" size="8" onchange="" readOnly="true" onkeyup=""/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">Basic :</td>
                                                                                                                                                            <td colspan="4"><s:textfield  cssClass="inputTextReadOnlyColoredTextBoxes" name="basicCalc" id="basicCalc" value="%{basicCalc}" size="8" onchange="" readOnly="true" onkeyup=""/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">DA :</td>
                                                                                                                                                            <td colspan="4"><s:textfield  cssClass="inputTextReadOnlyColoredTextBoxes" name="daCalac" id="daCalac" value="%{daCalac}" size="8" onchange="" readOnly="true" onkeyup=""/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">HRA :</td>
                                                                                                                                                            <td colspan="4"><s:textfield  cssClass="inputTextReadOnlyColoredTextBoxes" name="hraCalc" id="hraCalc" value="%{hraCalc}" size="8" onchange="" readOnly="true" onkeyup=""/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">TA :</td>
                                                                                                                                                            <td colspan="4"><s:textfield  cssClass="inputTextReadOnlyColoredTextBoxes" name="taCalc" id="taCalc" value="%{taCalc}" size="8" onchange="" readOnly="true" onkeyup=""/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">Project Pay:</td>
                                                                                                                                                            <td colspan="4"><s:textfield  cssClass="inputTextReadOnlyColoredTextBoxes"  name="prjPayCalc" id="prjPayCalc" value="%{prjPayCalc}" size="8" onchange="" readOnly="true" onkeyup=""/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">CCA :</td>
                                                                                                                                                            <td colspan="4"><s:textfield  cssClass="inputTextReadOnlyColoredTextBoxes" name="ccaCalc" id="ccaCalc" value="%{ccaCalc}" size="8" onchange="" readOnly="true" onkeyup=""/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">Entertainment :</td>
                                                                                                                                                            <td colspan="4"><s:textfield  cssClass="inputTextReadOnlyColoredTextBoxes" name="entertainmentCalc" id="entertainmentCalc" value="%{entertainmentCalc}" size="8" onchange="" readOnly="true" onkeyup=""/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">Vehicle Allowance :</td>
                                                                                                                                                            <td colspan="4"> <s:textfield  cssClass="inputTextReadOnlyColoredTextBoxes" name="vehAllCal" id="vehAllCal" value="%{vehAllCal}" size="8" onchange="" readOnly="true" onkeyup=""/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">Misc Pay :</td>
                                                                                                                                                            <td colspan="4"><s:textfield  cssClass="inputTextReadOnlyColoredTextBoxes" name="miscCal" id="miscCal" value="%{miscCal}" size="8" onchange="" readOnly="true" onkeyup=""/></td>
                                                                                                                                                        </tr>
                                                                                                                                                        <tr>
                                                                                                                                                            <td class="fieldLabelWages">Special Allowance :</td>
                                                                                                                                                            <td colspan="4"><s:textfield  cssClass="inputTextReadOnlyColoredTextBoxes" name="splAlloCalc" id="splAlloCalc" value="%{splAlloCalc}" size="8" readOnly="true" onchange="" onkeyup=""/></td>
                                                                                                                                                        </tr>

                                                                                                                                                            <tr>
                                                                                                                                                               <td class="fieldLabelWages">Health Insurance :</td>
                                                                                                                                                               <td colspan="4"><s:textfield  cssClass="inputTextBlueWages" name="healthDedCalc" id="healthDedCalc" value="%{healthDedCalc}" size="8" onchange="" onkeyup=""/></td>
                                                                                                                                                           </tr>
                                                                                                                                                             <tr>
                                                                                                                                                               <td class="fieldLabelWages">Professional Tax :</td>
                                                                                                                                                               <td colspan="4"><s:textfield  cssClass="inputTextBlueWages" name="proftaxCalc" id="proftaxCalc" value="%{proftaxCalc}" size="8" onchange="" onkeyup=""/></td>
                                                                                                                                                           </tr>
                                                                                                                                                             <tr>
                                                                                                                                                               <td class="fieldLabelWages">TDS :</td>
                                                                                                                                                               <td colspan="4"><s:textfield  cssClass="inputTextBlueWages" name="tdsCalc" id="tdsCalc" value="%{tdsCalc}" size="8" onchange="" onkeyup=""/></td>
                                                                                                                                                           </tr>
                                                                                                                                                             <tr>
                                                                                                                                                               <td class="fieldLabelWages">Employee PF :</td>
                                                                                                                                                               <td colspan="4"><s:textfield  cssClass="inputTextBlueWages" name="employeePfCalc" id="employeePfCalc" value="%{employeePf}" size="8" onchange="" onkeyup=""/></td>
                                                                                                                                                           </tr> 
                                                                                                                                                    </table>

                                                                                                                                                </td>

                                                                                                                                            </tr>
                                                                                                                                        </table></div>
                                                                                                                                </td>
                                                                                                                            </tr> --%>
                                                                                                            </table>
                                                                                                            <s:hidden name="payRollId" id="payRollId"  value="%{payRollId}" />
                                                                                                            <s:hidden name="tdsId" id="tdsId"  value="%{tdsId}" />
                                                                                                            </div>
                                                                                                        </s:form>

                                                                                                        </div>
                                                                                                        <script>
                                                                                          
                                                                                                            var cal3 = new CalendarTime(document.forms['payrollDetails'].elements['datePayRevised']);
                                                                                                            cal3.year_scroll = true;
                                                                                                            cal3.time_comp = false;
                                                                                                            var cal5 = new CalendarTime(document.forms['payrollDetails'].elements['lockAmtStartDate']);
                                                                                                            cal5.year_scroll = true;
                                                                                                            cal5.time_comp = false;
                                                                                           
                                                                                                        </script>  
                                                                                                        <!-- payroll Details END -->
                                                                                                        <!-- insurance Savings Tab  Start -->
                                                                                                        <div id="insuranceSavingsTab" class="tabcontent" > 
                                                                                                            <%
                                                                                                                if (session.getAttribute("resultMessage") != null) {
                                                                                                                    out.println(session.getAttribute("resultMessage"));
                                                                                                                    session.removeAttribute("resultMessage");
                                                                                                                }

                                                                                                            %>
                                                                                                            <br>
                                                                                                            <s:form name="insuranceSavingsTab" action="insuranceSavingsUpdate" theme="simple" >
                                                                                                                <div align="center" style=" border-radius: 25px;
                                                                                                                     border: 2px solid #3e93d4;
                                                                                                                     padding: 20px;
                                                                                                                     width: 780px;
                                                                                                                     height: auto;
                                                                                                                     margin-right: 30%;
                                                                                                                     position: relative;background-color:#c5def2; ">
                                                                                                                    <table cellpadding="1" cellspacing="1" border="0" width="80%">
                                                                                                                        <tr>
                                                                                                                            <td colspan="6"align="right">
                                                                                                                                <div style="margin-right: -13%;">
                                                                                                                                    <table style="padding-right: 89px;">
                                                                                                                                        <tr>
                                                                                                                                            <td  >
                                                                                                                                                <INPUT type="button" CLASS="btnPayrollWage" value="Back to List" onClick="history.go(-1)" tabindex="">
                                                                                                                                            </td>  
                                                                                                                                            <td >
                                                                                                                                                <div id="new3">
                                                                                                                                                    <input type="button" class="btnPayrollWage" value="New" id="new33" onclick="confirmForNewPayrollAdd();" tabindex=""/>
                                                                                                                                                </div>
                                                                                                                                            </td>
                                                                                                                                            <td >
                                                                                                                                                <div id="save3" >
                                                                                                                                                    <input type="button" class="btnPayrollWage" id="save33" value="Save All" onclick="addPayrollDetails();" tabindex="82"/>
                                                                                                                                                </div>
                                                                                                                                                <div id="update3" >
                                                                                                                                                    <input type="button" class="btnPayrollWage" id="update33" value="Update All" onclick="addPayrollDetails();" tabindex="82"/>
                                                                                                                                                </div>
                                                                                                                                            </td> 
                                                                                                                                            <td>
                                            <input type="button" class="btnPayrollWage" value="Cancel" onclick="window.location.href=window.location.href"  tabindex="24"/>
    
                                        </td>

                                                                                                                                        </tr>
                                                                                                                                    </table>
                                                                                                                                </div>
                                                                                                                            </td>
                                                                                                                        </tr>
                                                                                                                        <tr>
                                                                                                                            <td class="fieldLabelWages"  align="right">Emp&nbsp;Name&nbsp;:</td>                           

                                                                                                                            <td colspan="2"><font class="navigationTextWages"><s:property value="%{employeeName}"/></font> </td>        
                                                                                                                            <td class="fieldLabelWages"  align="left" colspan="">Yearly&nbsp;Gross&nbsp;:</td>                           

                                                                                                                            <td colspan="2"><s:textfield name="expectedYearlyCost" id="expectedYearlyCost" cssClass="inputTextReadOnlyPayroll" value="%{expectedYearlyCost}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="" readonly="true"/></td>               
                                                                                                                        </tr>
                                                                                                                        <%--</tr>
                                                                                                                      <tr>
                                                                                                                           <td class="fieldLabelWages"  align="right" >Prev&nbsp;YTD&nbsp;Salary:</td>                           

                                                                            <td colspan="5"><s:textfield name="prevYtdSalary" id="prevYtdSalary" cssClass="inputTextBlueWagesReg" value="%{prevYtdSalary}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="67"/></td>    
                                                                        </tr> 
                                                                        <tr>--%>
                                                                                                                        <tr>
                                                                                                                            <td class="fieldLabelWages"  align="right" >Emp&nbsp;Savings1&nbsp;(80C)&nbsp;:</td>

                                                                                                                            <td ><s:textfield name="empSaving1" id="empSaving1" cssClass="inputTextReadOnlyPayroll" value="%{empSaving1}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="68" readonly="true"/></td>
                                                                                                                            <td class="fieldLabelWages"  align="left" colspan="3">Interest&nbsp;on&nbsp;House&nbsp;Property(Housing Loan)-24(b)&nbsp;:</td>                           

                                                                                                                            <td ><s:textfield name="empSaving2" id="empSaving2" cssClass="inputTextReadOnlyPayroll" value="%{empSaving2}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="77" readonly="true"/></td>               


                                                                                                                        </tr>
                                                                                                                        <tr>
                                                                                                                            <td class="fieldLabelWages"  align="right" >Health&nbsp;Insurance(80d)&nbsp;:</td>

                                                                                                                            <td ><s:textfield name="empSaving3" id="empSaving3" cssClass="inputTextReadOnlyPayroll" value="%{empSaving3}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="68" readonly="true"/></td>
                                                                                                                            <td class="fieldLabelWages"  align="left" colspan="3">Interest&nbsp;on&nbsp;Education&nbsp;Loan(80E):</td>                           

                                                                                                                            <td ><s:textfield name="empSaving4" id="empSaving4" cssClass="inputTextReadOnlyPayroll" value="%{empSaving4}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="77" readonly="true"/></td>               


                                                                                                                        </tr>
                                                                                                                        <tr>
                                                                                                                              <td class="fieldLabelWages" align="right"  >NPS&nbsp;(80CCD):</td>
                                                                                                                              <td ><s:textfield name="empSaving6" id="empSaving6" cssClass="inputTextReadOnlyPayroll" value="%{empSaving6}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="68" readonly="true"/></td>
                                                                                                                         
                                                                                                                            <td class="fieldLabelWages"  align="left" colspan="3" >House&nbsp;Rent&nbsp;Paid-80GG:</td>

                                                                                                                            <td ><s:textfield name="empSaving5" id="empSaving5" cssClass="inputTextReadOnlyPayroll" value="%{empSaving5}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="68" readonly="true"/></td>
                                                                                                                         
                                                                                                                        </tr>
                                                                                                                        <tr>
                                                                                                                           <td colspan="4"align="right"><input type="button" class="btnPayrollWage" value="Get TEF" onclick="getTEFDetails();" tabindex=""/></td>
                                                                                                                        </tr>

                                                                                                                        <tr>
                                                                                                                            <td  colspan="6" >
                                                                                                                                <div id="loadingMessage12" style="color:red;display:none;" align="center"><b><font size="4px" >Loading...Please Wait...</font></b></div>
                                                                                                                                        <%--<div id="loadingMessage12" style="display:none;" class="error" align="center" ><b><font size="4px" >Loading...Please Wait</font></b></div>--%>
                                                                                                                            </td>
                                                                                                                        </tr>
                                                                                                                        <tr>
                                                                                                                            <td colspan="6" >
                                                                                                                                <!--style="color:#0000FF;font:italic 900 12px arial;"  bgcolor='#3E93D4'-->
                                                                                                                                <table id="tblTefList" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='550' >
                                                                                                                                    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/wz_tooltip.js"/>"></script>
                                                                                                                                    <COLGROUP ALIGN="left" >
                                                                                                                                        <COL width="200">
                                                                                                                                        <COL width="200"> 
                                                                                                                                        <COL width="200">
                                                                                                                                        <COL width="220">
                                                                                                                                        <COL width="200">
                                                                                                                                        <COL width="200">
                                                                                                                                        <COL width="200">

                                                                                                                                </table>


                                                                                                                            </td>
                                                                                                                        </tr>
                                                                                                                        <%-- <tr>

                                                                            <td class="fieldLabelWages"  align="right" >Tution&nbsp;Fees:</td>                           

                                                                            <td colspan=""><s:textfield name="tutionfees" id="tutionfees" cssClass="inputTextBlueWagesReg" value="%{tutionfees}" onchange="payRollFieldLengthValidator(this);addToEmpSavings1(this);" onkeyup="isNumberKeyWage(this);" tabindex="69"/></td>    
                                                                            <td class="fieldLabelWages"  align="left" colspan="3">HB&nbsp;Loan&nbsp;Interest:</td>                           

                                                                            <td ><s:textfield name="hbLoanInterst" id="hbLoanInterst" cssClass="inputTextBlueWagesReg" value="%{hbLoanInterst}" onchange="payRollFieldLengthValidator(this);addToEmpSavings2(this);" onkeyup="isNumberKeyWage(this);" tabindex="78"/></td>               

                                                                        </tr>
                                                                        <tr>

                                                                            <td class="fieldLabelWages"  align="right" >PPF:</td>                           

                                                                            <td colspan=""><s:textfield name="ppf" id="ppf" cssClass="inputTextBlueWagesReg" value="%{ppf}" onchange="payRollFieldLengthValidator(this);addToEmpSavings1(this);" onkeyup="isNumberKeyWage(this);" tabindex="70"/></td>    
                                                                            <td class="fieldLabelWages"  align="left" colspan="3">Medical&nbsp;Ins.:</td>                           

                                                                            <td ><s:textfield name="medicalIns" id="medicalIns" cssClass="inputTextBlueWagesReg" value="%{medicalIns}" onchange="payRollFieldLengthValidator(this);addToEmpSavings2(this);" onkeyup="isNumberKeyWage(this);" tabindex="79"/></td>               
                                                                        </tr>
                                                                        <tr>

                                                                            <td class="fieldLabelWages"  align="right">Life&nbsp;Ins.:</td>                           

                                                                            <td colspan=""><s:textfield name="lifeIns" id="lifeIns" cssClass="inputTextBlueWagesReg" value="%{lifeIns}" onchange="payRollFieldLengthValidator(this);addToEmpSavings1(this);" onkeyup="isNumberKeyWage(this);" tabindex="71"/></td>    
                                                                            <td class="fieldLabelWages"  align="left" colspan="3">HRA:</td>                           

                                                                            <td ><s:textfield name="hraLifeInsuranceSavings" id="hraLifeInsuranceSavings" cssClass="inputTextBlueWagesReg" value="%{hraLifeInsuranceSavings}" onchange="payRollFieldLengthValidator(this);addToEmpSavings2(this);" onkeyup="isNumberKeyWage(this);" tabindex="80"/></td>               
                                                                        </tr>
                                                                        <tr>

                                                                            <td class="fieldLabelWages"  align="right" >Premium:</td>                           

                                                                            <td colspan=""><s:textfield name="premium" id="premium" cssClass="inputTextBlueWagesReg" value="%{premium}" onchange="payRollFieldLengthValidator(this);addToEmpSavings1(this);" onkeyup="isNumberKeyWage(this);" tabindex="72"/></td>    

                                                                            <td class="fieldLabelWages"  align="left" colspan="3">Edu.&nbsp;Interest:</td>                           

                                                                            <td ><s:textfield name="eduInterest" id="eduInterest" cssClass="inputTextBlueWagesReg" value="%{eduInterest}" onchange="payRollFieldLengthValidator(this);addToEmpSavings2(this);" onkeyup="isNumberKeyWage(this);" tabindex="81"/></td>               
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabelWages"  align="right" colspan="">FD:</td>                           

                                                                            <td colspan="5"><s:textfield name="fd" id="fd" cssClass="inputTextBlueWagesReg" value="%{fd}" onchange="payRollFieldLengthValidator(this);addToEmpSavings1(this);" onkeyup="isNumberKeyWage(this);" tabindex="73"/></td>    
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabelWages"  align="right" colspan="">HB&nbsp;Loan&nbsp;Priciple:</td>                           

                                                                            <td colspan="5"><s:textfield name="hbLoanPrinciple" id="hbLoanPrinciple" cssClass="inputTextBlueWagesReg" value="%{hbLoanPrinciple}" onchange="payRollFieldLengthValidator(this);addToEmpSavings1(this);" onkeyup="isNumberKeyWage(this);" tabindex="74"/></td>    
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabelWages"  align="right" colspan="">Mutual&nbsp;Funds:</td>                           

                                                                            <td colspan="5"><s:textfield name="mutualFunds" id="mutualFunds" cssClass="inputTextBlueWagesReg" value="%{mutualFunds}" onchange="payRollFieldLengthValidator(this);addToEmpSavings1(this);" onkeyup="isNumberKeyWage(this);" tabindex="75"/></td>    
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabelWages"  align="right" colspan="">NSC:</td>                           

                                                                            <td colspan="5"><s:textfield name="nsc" id="nsc" cssClass="inputTextBlueWagesReg" value="%{nsc}" onchange="payRollFieldLengthValidator(this);addToEmpSavings1(this);" onkeyup="isNumberKeyWage(this);" tabindex="76"/></td>    
                                                                        </tr>

                                                                                                                        --%>
                                                                                                                        <%--   <td class="fieldLabelWages"  align="right" colspan="3">Life&nbsp;Insurance&nbsp;Amount:</td>                           

                                                                                                        <td colspan=""><s:textfield name="lifeInsureanceAmt" id="lifeInsureanceAmt" cssClass="inputTextBlueWagesReg" value="%{lifeInsureanceAmt}" onchange="payRollFieldLengthValidator(this);" tabindex="61"/></td>    
                                                                                                    </tr>
                                                                                                    <tr>
                                                                                                        <td class="fieldLabelWages"  align="right">Life&nbsp;Insurance&nbsp;Term[Years];:</td>

                                                                                                        <td><s:textfield name="lifeInsureanceTerm" id="lifeInsureanceTerm" cssClass="inputTextBlueWagesReg" value="%{lifeInsureanceTerm}" onchange="payRollFieldLengthValidator(this);" tabindex="62"/></td>
                                                                                                        <td class="fieldLabelWages"  align="left" colspan="3">Life&nbsp;Insurance&nbsp;Annual&nbsp;Premium:</td>                           

                                                                                                        <td colspan=""><s:textfield name="lifeInsureanceAnnual" id="lifeInsureanceAnnual" cssClass="inputTextBlueWagesReg" value="%{lifeInsureanceAnnual}" onchange="payRollFieldLengthValidator(this);" tabindex="63"/></td>
                                                                                                    </tr>


                                                                                                    <tr>
                                                                                                        <td class="fieldLabelWages"  align="left">Life&nbsp;Insurance&nbsp;Policy&nbsp;Number:</td>                           

                                                                                                        <td ><s:textfield name="lifeInsureancePolicy" id="lifeInsureancePolicy" cssClass="inputTextBlueWagesReg" value="%{lifeInsureancePolicy}" onchange="payRollFieldLengthValidator(this);"
                                                                                                                    tabindex="64" /></td>
                                                                                                        <td class="fieldLabelWages"  align="left" colspan="3">Health&nbsp;Insurance&nbsp;Annual&nbsp;Premium:</td>                           

                                                                                                        <td ><s:textfield name="healthInsuranceAnnual" id="healthInsuranceAnnual" cssClass="inputTextBlueWagesReg" value="%{healthInsuranceAnnual}" onchange="payRollFieldLengthValidator(this);"
                                                                                                                     tabindex="65"/></td>  
                                                                                                    </tr>
                                                                                                    <tr>
                                                                                                        <td class="fieldLabelWages"  align="left">Health&nbsp;Insurance&nbsp;Amount:</td>                           

                                                                                                        <td colspan="5"><s:textfield name="healthInsuranceAmt" id="healthInsuranceAmt" cssClass="inputTextBlueWagesReg" value="%{healthInsuranceAmt}" onchange="payRollFieldLengthValidator(this);"
                                                                                                                    tabindex="66" /></td>  
                                                                                                    </tr> 
                                                                                                                        --%>
                                                                                                                    </table>
                                                                                                                </div>
                                                                                                            </s:form>
                                                                                                        </div>
                                                                                                        <!-- insurance Savings Tab  End-->
                                                                                                        <!-- Aggregations tab starts -->
                                                                                                        <div id="aggregations" class="tabcontent" > 
                                                                                                            <%
                                                                                                                if (session.getAttribute("resultMessage") != null) {
                                                                                                                    out.println(session.getAttribute("resultMessage"));
                                                                                                                    session.removeAttribute("resultMessage");
                                                                                                                }

                                                                                                            %>
                                                                                                            <br>
                                                                                                            <s:form name="aggregations" action="aggregations" theme="simple" >
                                                                                                                <div align="center" style=" border-radius: 25px;
                                                                                                                     border: 2px solid #3e93d4;
                                                                                                                     padding: 20px;
                                                                                                                     width: 780px;
                                                                                                                     height: auto;
                                                                                                                     margin-right: 30%;
                                                                                                                     position: relative;background-color:#c5def2; ">
                                                                                                                    <table cellpadding="1" cellspacing="1" border="0" width="80%">
                                                                                                                        <tr>
                                                                                                                            <td colspan="6"align="right">
                                                                                                                                <div style="margin-right: -13%;">
                                                                                                                                    <table style="padding-right: 89px;">
                                                                                                                                        <tr>
                                                                                                                                            <td>
                                                                                                                                                <INPUT type="button" CLASS="btnPayrollWage" value="Back to List" onClick="history.go(-1)" tabindex="">
                                                                                                                                            </td>  
                                                                                                                                            
                                                                                                                                             
                                                                                                                                            <td>
                                            <input type="button" class="btnPayrollWage" value="Cancel" onclick="window.location.href=window.location.href"  tabindex="24"/>
    
                                        </td>

                                                                                                                                        </tr>
                                                                                                                                    </table>
                                                                                                                                </div>
                                                                                                                            </td>
                                                                                                                        </tr>
                                                                                                                        <tr>
                                                                                                                            <td class="fieldLabelWages"  align="right">Emp&nbsp;Name&nbsp;:</td>                           

                                                                                                                            <td colspan="2"><font class="navigationTextWages"><s:property value="%{employeeName}"/></font> </td>        
                                                                                                                                                                                                                              </tr>
                                                                                                                        
                                                                                                                        <tr>
                                                                                                                             <tr>
                                                                                                                            <td class="fieldLabelWages"  align="right">Financial&nbsp;Year&nbsp;:</td>                           

                                                                                                                            <td colspan="2"><font class="navigationTextWages">2016-2017</font> </td>        
                                                                                                                                                                                                                              </tr>
                                                                                                                        
                                                                                                                        <tr>
                                                                                                                            <td class="fieldLabelWages"  align="right" >Total&nbsp;No.&nbsp;Of&nbsp;Leaves&nbsp;:</td>

                                                                                                                            <td ><s:textfield name="noOfLeaves" id="noOfLeaves" cssClass="inputTextReadOnlyPayroll" value="%{noOfLeaves}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="68" readonly="true"/></td>
                                                                                                                            <td class="fieldLabelWages"  align="left" colspan="3">Total&nbsp;No&nbsp;Of&nbsp;Weekends&nbsp;:</td>                           

                                                                                                                            <td ><s:textfield name="noOfWeekendDays" id="noOfWeekendDays" cssClass="inputTextReadOnlyPayroll" value="%{noOfWeekendDays}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="77" readonly="true"/></td>               


                                                                                                                        </tr>
                                                                                                                        
                                                                                                                         <tr>
                                                                                                                            <td class="fieldLabelWages"  align="right" >Leaves&nbsp;Balence&nbsp;:</td>

                                                                                                                            <td ><s:textfield name="availLeaves" id="availLeaves" cssClass="inputTextReadOnlyPayroll" value="%{availLeaves}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="68" readonly="true"/></td>
                                                                                                                            <td class="fieldLabelWages"  align="left" colspan="3">Weekends&nbsp;Balance:</td>                           

                                                                                                                            <td ><s:textfield name="availWeekends" id="availWeekends" cssClass="inputTextReadOnlyPayroll" value="%{availWeekends}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="77" readonly="true"/></td>               


                                                                                                                        </tr>
                                                                                                                         <tr>
                                                                                                                            <td class="fieldLabelWages"  align="right" >Holidays&nbsp;Balance&nbsp;:</td>

                                                                                                                            <td ><s:textfield name="availHolidays" id="availHolidays" cssClass="inputTextReadOnlyPayroll" value="%{availHolidays}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="68" readonly="true"/></td>
                                                                                                                          

                                                                                                                        </tr>
                                                                                                                       
                                                                                                                    </table>
                                                                                                                </div>
                                                                                                            </s:form>
                                                                                                        </div>
                                                                                                            <!-- Aggregations Tab ends-->
                                                                                                        <!-- other Details  Tab  Start -->
                                                                                                        <%-- <div id="otherDetailsTab" class="tabcontent" > 
                                                                                                              <br>
                                                                                                              <s:form name="otherDetailsTab" action="otherDetailsUpdate" theme="simple" >
                                                                                                                  <div align="center" style=" border-radius: 25px;
                                                                                                                       border: 2px solid #3e93d4;
                                                                                                                       padding: 20px;
                                                                                                                       width: 700px;
                                                                                                                       height: 400px;
                                                                                                                       margin-right: 30%;
                                                                                                                       position: relative;background-color:#c0c0c0; ">
                                                                                                                      <table cellpadding="1" cellspacing="1" border="0" width="100%">
                                                                                                                          <tr>
                                                                                                                              <td colspan="6"align="right">
                                                                                                                                  <table>
                                                                                                                                      <tr>
                                                                                                                                          <td  >
                                                                                                                                              <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)" tabindex="">
                                                                                                                                          </td>  
                                                                                                                                          <td >
                                                                                                                                              <div id="new4" style="display: none;">
                                                                                                                                                  <input type="button" class="buttonBg" value="New" onclick="confirmForNewPayrollAdd();" tabindex=""/>
                                                                                                                                              </div>
                                                                                                                                          </td>
                                                                                                                                          <td >
                                                                                                                                              <div id="save4" style="display: none;">
                                                                                                                                                  <input type="button" class="buttonBg" value="Save All" onclick="addPayrollDetails();" tabindex="88"/>
                                                                                                                                              </div>
                                                                                                                                              <div id="update4" style="display: none;">
                                                                                                                                                  <input type="button" class="buttonBg" value="Update All" onclick="addPayrollDetails();" tabindex="88"/>
                                                                                                                                              </div>
                                                                                                                                          </td>

                                                                                    </tr>
                                                                                </table>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabelWages"  align="right">Emp&nbsp;Name&nbsp;:</td>                           

                                                                            <td colspan="3"><font class="navigationTextWages"><s:property value="%{employeeName}"/></font> </td>     

                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabelWages">Wage&nbsp;&nbsp;Comments&nbsp;:</td>  
                                                                            <td colspan="5"><s:textarea name="wagecomments" id="wagecomments" cols="105" rows="3" cssClass="inputTextArea" value="%{wagecomments}" onchange="payRollFieldLengthValidator(this);"
                                                                                        tabindex="84"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabelWages">Wage&nbsp;&nbsp;Comments1&nbsp;:</td>  
                                                                            <td colspan="5"><s:textarea name="wagecomments1" id="wagecomments1" cols="105" rows="3" cssClass="inputTextArea" value="%{wagecomments1}" onchange="payRollFieldLengthValidator(this);"
                                                                                        tabindex="85" /></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabelWages">General&nbsp;&nbsp;Comments&nbsp;:</td>  
                                                                            <td colspan="5"><s:textarea name="generalcomments" id="generalcomments" cols="105" rows="3" cssClass="inputTextArea" value="%{generalcomments}" onchange="payRollFieldLengthValidator(this);"
                                                                                        tabindex="86"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="fieldLabelWages">Reference&nbsp;&nbsp;Details&nbsp;:</td>  
                                                                            <td colspan="5"><s:textarea name="referencecomments" id="referencecomments" cols="105" rows="1" cssClass="inputTextArea" value="%{referencecomments}" onchange="payRollFieldLengthValidator(this);"
                                                                                        tabindex="87" /></td>
                                                                        </tr>

                                                                    </table>
                                                                </div>
                                                            </s:form>
                                                        </div>
                                                        <div id="tdsTab" class="tabcontent" > 
                                                            <br>
                                                            <s:form action="" name="tdsDetails" theme="simple">

                                                                <div align="center" style=" border-radius: 25px;
                                                                     border: 2px solid #3e93d4;
                                                                     padding: 20px;
                                                                     width: 700px;
                                                                     height: 850px;
                                                                     margin-right: 30%;
                                                                     position: relative;background-color:#c0c0c0; ">
                                                                    <table cellpadding="1" cellspacing="1" border="0" width="100%" align="center">
                                                                        <tr>
                                                                            <td>

                                                                                <table width="80%" border="0" align="center">
                                                                                    <tr>
                                                                                        <td colspan="6"align="right">
                                                                                            <table>
                                                                                                <tr>
                                                                                                    <td  >
                                                                                                        <INPUT type="button" CLASS="buttonBg" value="Back to List" onClick="history.go(-1)" tabindex="">
                                                                                                    </td>  
                                                                                                    <td >
                                                                                                        <div id="new5" style="display: none;">
                                                                                                            <input type="button" class="buttonBg" value="New" onclick="confirmForNewPayrollAdd();" tabindex=""/>
                                                                                                        </div>
                                                                                                    </td>
                                                                                                    <td >
                                                                                                        <div id="save5" style="display: none;">
                                                                                                            <input type="button" class="buttonBg" value="Save All" onclick="addPayrollDetails();" tabindex="115"/>
                                                                                                        </div>
                                                                                                        <div id="update5" style="display: none;">
                                                                                                            <input type="button" class="buttonBg" value="Update All" onclick="addPayrollDetails();" tabindex="115"/>
                                                                                                        </div>
                                                                                                    </td>

                                                                                                </tr>
                                                                                            </table>
                                                                                        </td>
                                                                                    </tr>

                                                                                    <tr>
                                                                                        <td class="fieldLabelWages"  align="right">Employee&nbsp;Name:</td>                           

                                                                                        <td colspan=""><font class="navigationTextWages"><s:property value="%{employeeName}"/></font> </td>  
                                                                                        <td class="fieldLabelWages"  align="right">Expected&nbsp;Yearly&nbsp;Total&nbsp;Gross: </td>                           

                                                                                        <td colspan="3" align="right"><s:textfield name="expectedYearlyCost" id="expectedYearlyCost" cssClass="inputTextBlueWages" value="%{expectedYearlyCost}" onkeyup="isNumberKeyWage(this);" tabindex=""/></td>     
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="left" colspan="2"></td>    

                                                                                        <td class="fieldLabelWages" align="left" colspan="2"><h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Investment</h2></td>    

                                                                                        <td class="fieldLabelWages" align="left" colspan="2"><h2>Deductible</h2></td>    

                                                                                    </tr> 
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="left" colspan="2"><h2>Investment&nbsp;U/S&nbsp;80C</h2></td>   
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">Life&nbsp;Insurance&nbsp;Premium:</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="lifeInsurancePremium" id="lifeInsurancePremium" cssClass="inputTextBlueWages" value="%{lifeInsurancePremium}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="90" /></td>
                                                                                        <td colspan="2" align=""></td>

                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">Housing&nbsp;Loan&nbsp;Repayment:</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="housingLoanRepay" id="housingLoanRepay" cssClass="inputTextBlueWages" value="%{housingLoanRepay}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="92"/></td>
                                                                                        <td colspan="2" align=""></td>

                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">NSC:</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="nscTds" id="nscTds" cssClass="inputTextBlueWages" value="%{nscTds}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="93"/></td>

                                                                                        <td colspan="2" align=""></td>
                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">PPF&nbsp;Contribution:</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="ppfContribution" id="ppfContribution" cssClass="inputTextBlueWages" value="%{ppfContribution}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="94"/></td>
                                                                                        <td colspan="2" align=""></td>

                                                                                    </tr> 
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">Tution&nbsp;Fee:</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="tutionFee" id="tutionFee" cssClass="inputTextBlueWages" value="%{tutionFee}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="95"/></td>

                                                                                        <td colspan="2" align=""></td>
                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">ELSS:</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="elss" id="elss" cssClass="inputTextBlueWages" value="%{elss}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="96"/></td>

                                                                                        <td colspan="2" align=""></td>
                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">Post&nbsp;Office&nbsp;Term&nbsp;Deposit:</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="postOfficeTerm" id="postOfficeTerm" cssClass="inputTextBlueWages" value="%{postOfficeTerm}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="97"/></td>
                                                                                        <td colspan="2" align=""></td>

                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">Bank&nbsp;Deposit&nbsp;(Tax&nbsp;Saving):</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="bankDepositTax" id="bankDepositTax" cssClass="inputTextBlueWages" value="%{bankDepositTax}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="98"/></td>
                                                                                        <td colspan="2" align=""></td>

                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">LIC from Salary:</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="licFromSal" id="licFromSal" cssClass="inputTextBlueWages" value="%{licFromSal}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="99"/></td>

                                                                                        <td colspan="2" align=""></td>
                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">Others:</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="othersTDS" id="othersTDS" cssClass="inputTextBlueWages" value="%{othersTDS}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="100"/></td>

                                                                                        <td colspan="2" align=""></td>
                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="" colspan="2"><h2>Investment U/S 80CCC</h2></td>    

                                                                                    </tr>   

                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">Contribution&nbsp;to&nbsp;PF:</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="contributionToPf" id="contributionToPf" cssClass="inputTextBlueWages" value="%{contributionToPf}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="101"/></td>
                                                                                        <td colspan="2" align=""></td>

                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="" colspan="2">80CCD-NPS-Employees&nbsp;contribution:</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="npsEmployeeContr" id="npsEmployeeContr" cssClass="inputTextBlueWages" value="%{npsEmployeeContr}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="102"/></td>
                                                                                        <td colspan="2" align=""></td>

                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="" colspan="2"> Total(max exemptions-1,50,000):</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="totalTds" id="totalTds" cssClass="inputTextBlueWages" value="%{totalTds}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="103"/></td>
                                                                                        <td colspan="2" align="right"><s:textfield name="totalTdsDeductable" id="totalTdsDeductable" cssClass="inputTextBlueWages" value="%{totalTdsDeductable}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="104"/></td>


                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="" colspan="2">Interest&nbsp;on&nbsp;borrowed&nbsp;capital-self&nbsp;<br>occupied&nbsp;prop(max exemptions-2,00,000):</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="interstOnBorrowed" id="interstOnBorrowed" cssClass="inputTextBlueWages" value="%{interstOnBorrowed}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="105"/></td>
                                                                                        <td colspan="2" align="right"><s:textfield name="interstOnBorrowedDeductable" id="interstOnBorrowedDeductable" cssClass="inputTextBlueWages" value="%{interstOnBorrowedDeductable}" onchange="" onkeyup="isNumberKeyWage(this);" tabindex="106"/></td>


                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="" colspan="2"><h2>Medical&nbsp;Insurance&nbsp;U/S80d</h2></td>    

                                                                                    </tr>   

                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">Insurance&nbsp;for&nbsp;parents(Paid&nbsp;for&nbsp;senior&nbsp;citizens)<br>(max exemptions-20,000):</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="insuranceForParents" id="insuranceForParents" cssClass="inputTextBlueWages" value="%{insuranceForParents}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="107"/></td>
                                                                                        <td colspan="2" align="right"><s:textfield name="insuranceForParentsDeduc" id="insuranceForParentsDeduc" cssClass="inputTextBlueWages" value="%{insuranceForParentsDeduc}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="108"/></td>


                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="" colspan="2">Insurance&nbsp;other(self,spouse and children)<br>(max exemptions-15,000):</td>    


                                                                                        <td colspan="2" align="right"><s:textfield name="insuranceOthers" id="insuranceOthers" cssClass="inputTextBlueWages" value="%{insuranceOthers}" onchange="tdsCalculation();payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="109"/></td>
                                                                                        <td colspan="2" align="right"><s:textfield name="insuranceOthersDeduc" id="insuranceOthersDeduc" cssClass="inputTextBlueWages" value="%{insuranceOthersDeduc}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="110"/></td>


                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="" colspan="2">Interest&nbsp;on&nbsp;education&nbsp;loan&nbsp;repayment(80E):</td>    

                                                                                        <td colspan="2" align=""></td>
                                                                                        <td colspan="2" align="right"><s:textfield name="interstOnEdu" id="interstOnEdu" cssClass="inputTextBlueWages" value="%{interstOnEdu}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="111"/></td>



                                                                                    </tr>  
                                                                                    <tr>
                                                                                        <td class="fieldLabelWages" align="" colspan="2">HR&nbsp;Exemptions:</td>    

                                                                                        <td colspan="2" align="right"><s:textfield name="interstOnHrAssumptionsInv" id="interstOnHrAssumptionsInv" cssClass="inputTextBlueWages" value="%{interstOnHrAssumptionsInv}" onchange="payRollFieldLengthValidator(this);showHouseRentDetails();computeHrExemtionsDed();" onkeyup="isNumberKeyWage(this);" tabindex="112"/></td>

                                                                                        <td colspan="2" align="right"><s:textfield name="interstOnHrAssumptions" id="interstOnHrAssumptions" cssClass="inputTextBlueWages" value="%{interstOnHrAssumptions}" onchange="payRollFieldLengthValidator(this);" onkeyup="isNumberKeyWage(this);" tabindex="113"/></td>


                                                                                    </tr>  

                                                                                    <tr  id ="houseOwnerDetails1" style="display: none">
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">House&nbsp;Owner&nbsp;Name:</td>    
                                                                                        <td colspan="2" align="right"><s:textfield name="houseOwnerName" id="houseOwnerName" cssClass="inputTextBlueWages" value="%{houseOwnerName}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex=""/></td>
                                                                                        <td colspan="2" align=""></td>
                                                                                    </tr>
                                                                                    <tr  id ="houseOwnerDetails2" style="display: none">          
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">House&nbsp;Owner&nbsp;PAN&nbsp;No.:</td>    
                                                                                        <td colspan="2" align="right"><s:textfield name="houseOwnerPAN" id="houseOwnerPAN" cssClass="inputTextBlueWages" value="%{houseOwnerPAN}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex=""/></td>
                                                                                        <td colspan="2" align=""></td>
                                                                                    </tr>
                                                                                    <tr  id ="houseOwnerMobileNumber" style="display: none">
                                                                                        <td class="fieldLabelWages" align="right" colspan="2">House&nbsp;Owner&nbsp;Mobile&nbsp;No:</td>    
                                                                                        <td colspan="2" align="right"><s:textfield name="houseOwnerMobile" id="houseOwnerMobile" cssClass="inputTextBlueWages" value="%{houseOwnerMobile}" onchange="checkForSpace(this);" onkeyup="isNumberKeyWage(this);" tabindex=""/></td>
                                                                                        <td colspan="2" align=""></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="6" align="right"><input type="button" class="buttonBg" value="Refresh" onclick="computeExpectedYearlyValues();" tabindex="114"/></td>
                                                                                    </tr>


                                                                                </table>

                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </div>
                                                            </s:form>
                                                        </div> --%>
                                                                                                        <script type="text/javascript">

                                                                                                            var countries=new ddtabcontent("accountTabs")
                                                                                                            countries.setpersist(false)
                                                                                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                                                                            countries.init()
                                                                                                            var countries=new ddtabcontent("accountTabs1")
                                                                                                            countries.setpersist(false)
                                                                                                            countries.setselectedClassTarget("link") //"link" or "linkparent"
                                                                                                            countries.init()

                                                                                                        </script>
                                                                                                        <!--//END TABBED PANNEL : -->

                                                                                                        <!--//END TABBED PANNEL : -->
                                                                                                        </div>
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
                                                                                                <td align="center"><s:include value="/includes/template/Footer.jsp"/></td>
                                                                                            </tr>
                                                                                            <!--//END FOOTER : Record for Footer Background and Content-->
 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>
                                                                                            </table>
                                                                                            <!--//END MAIN TABLE : Table for template Structure-->
                                                                                            </body>
                                                                                            
<script type="text/javascript">
		$(window).load(function(){
     setSaveOrUpdateButton();
		});
		</script>
                                                                                            </html>
