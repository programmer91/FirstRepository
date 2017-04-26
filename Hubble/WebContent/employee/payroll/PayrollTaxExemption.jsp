<%-- 
   Document   : PayrollTaxException
   Created on : May 26, 2015, 7:27:59 PM
   Author     : miracle
--%>

<%-- 
    Document   : TeamReview
    Created on : Feb 13, 2015, 3:13:29 PM
    Author     : miracle
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>
<%@ page import="com.freeware.gridtag.*" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mss.mirage.util.ConnectionProvider"%>
<%@ page import="com.mss.mirage.util.ApplicationConstants"%>
<%@ taglib uri="/WEB-INF/tlds/datagrid.tld" prefix="grd" %>
<html>
    <head>
        <title>Hubble Organization Portal :: Payroll Tax Exemption</title>
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css?version=1.0"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/new/x0popup.min.css"/>">
          <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>
                  
                 <s:include value="PayrollCalculations.jsp"/>
        <s:include value="/includes/template/headerScript.html"/>
    </head>
   <!-- <body class="bodyGeneral" oncontextmenu="return false;" onload="init();initForTefSearch();changeExemptionType(document.getElementById('exemptionTypeId'));"> -->
    <body class="bodyGeneral" oncontextmenu="return false;">

        <%!
            /* Declarations */
            Connection connection;
            String queryString;
            String strTmp;
            String strSortCol;
            String strSortOrd;
            String submittedFrom;
            String searchSubmitValue;
            int intSortOrd = 0;
            int intCurr;
            boolean blnSortAsc = true;
        %>

        <!--//START MAIN TABLE : Table for template Structure-->
        <table class="templateTableLogin" align="center" cellpadding="0" cellspacing="0">

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
            <table class="innerTableLogin" cellpadding="0" cellspacing="0">
                <tr>

                    <!--//START DATA COLUMN : Coloumn for LeftMenu-->
                <td width="150px;" class="leftMenuBgColor" valign="top">
                    <s:include value="/includes/template/LeftMenu.jsp"/>
                </td>

                <!--//END DATA COLUMN : Coloumn for LeftMenu-->

                <!--//START DATA COLUMN : Coloumn for Screen Content-->
                <td width="850px" class="cellBorder" valign="top" style="padding: 5px 5px 5px 5px;">
                    <!--//START TABBED PANNEL : -->
                    <ul id="accountTabs" class="shadetabs" >
                        <li ><a href="#" class="selected" rel="TaxExemptionsTab"  >Tax Exemptions</a></li>
                    </ul>
                    <div  style="border:1px solid gray;  width:100%;height: 97%; overflow:auto; margin-bottom: 1em;">   
                        <!--//START TAB : -->
                        <div id="TaxExemptionsTab" class="tabcontent"  >

                            <!-- Add Tax excemption from Payroll Overlay Start-->
                            <div id="overlayTaxAdd"></div>  
                             <div id="specialBoxTaxAdd">
                                             <s:form theme="simple"  align="center" name="addTaxForm" action="" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"   >
                                                 <s:hidden name="empSaving1" id="empSaving1"/>
                                                 <s:hidden name="empSaving2" id="empSaving2"/>
                                                 <s:hidden name="empSaving3_parents" id="empSaving3_parents"/>
                                                 <s:hidden name="empSaving4" id="empSaving4" />
                                                 <s:hidden name="empSaving5" id="empSaving5" />
                                                  <s:hidden name="empSaving6" id="empSaving6" />
                                                 <s:hidden name="categoryId" id="categoryId" value="0"/>
                                                 <s:hidden name="categoryId" id="categoryId1" />
                                                 <s:hidden name="empSaving3_self" id="empSaving3_self"/>
                                                 <s:hidden name="empSavingsHealthAmt" id="empSavingsHealthAmt"/>
                                                 <s:hidden name="taxExemptionId" id="taxExemptionId"/>
                                                   
                                                 <s:hidden name="taxStartDate" id="taxStartDate" value="%{taxStartDate}"/>
                                                 <s:hidden name="taxEndDate" id="taxEndDate" value="%{taxEndDate}"/>
                                                 <s:hidden name="empId" id="empId" />
                                                 <s:hidden name="fromTef" id="fromTef" value="1"/>
                                                    <s:hidden name="exemptionName" id="exemptionName" /> 
                                                    <s:hidden name="previousStatus" id="previousStatus" /> 
                                                     <s:hidden id="previousOverlayAmount" name="previousOverlayAmount" />
                                                      <s:hidden name="Form12BBAttachmentName" id="Form12BBAttachmentName" value="%{Form12BBAttachmentName}"/> 
                                                     <s:hidden id="AttachmentName" name="AttachmentName" value="%{AttachmentName}"/>
                                                    
                                                   
                                                    
                                                <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                    <tr>                               
                                                        <td colspan="2" style="background-color: #288AD1" >
                                                            <h3 style="color:darkblue;" align="left">
                                                                <span id="taxHeaderLabel"></span>


                                                            </h3></td>
                                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                                            <a href="#" onmousedown="taxExemptiontoggleOverlayFromPayroll()" >
                                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                                            </a>  

                                                        </td></tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <div id="loadTAxAdd" style="color: green;display: none;">Loading..</div>
                                                            <div id="resultMessageTaxAdd"></div>
                                                        </td>
                                                    </tr>
                                                    <tr><td colspan="4">
                                                            <table align="center" border="0">
                                                                <tr>
                                                                    <td colspan="4" align="center"> <s:textfield id="financialYear" name="financialYear"  value="%{financialYear}" style=" background:rgba(0,0,0,0); border:none;color:green;font-weight: bold;font-size:15px;" readonly="true" />
                                                                                    
                                                                    </td>
                                                                      
                                                                    
                                                                </tr>
                                                                 <tr id="addFieldsTr">
                                                               <%-- <td class="fieldLabel" width="200px" align="right">Department :</td>
                                                                <td><s:select 
                                                                          name="taxDepartmentId"
                                                                          id="taxDepartmentId"
                                                                          headerKey=""
                                                                          headerValue="All"
                                                                          list="departmentIdList" cssClass="inputSelect" onchange="getEmployeeNamesByDepartment(this);"/></td> --%>
                                                                <td   class="fieldLabel">Employee&nbsp;Name:<font style="color:red;">*</font></td>
                                                                <td>
                                                                <%-- <s:select list="empnamesList" id="empLoginId" name="empLoginId" headerKey="-1" headerValue="--Please Select--" cssClass="inputSelect" onchange="getEmployeeNumberByLoginId(this);"/> --%> 
                                                                <s:textfield name="employeeName" id="employeeName" value="" autocomplete="off" cssClass="inputTextBlue" onkeyup="getEmplyeeNames();" placeholder="Enter name Or Id"/><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessage"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popup">
                                                                                                    <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                                                                                <s:hidden id="empLoginId" name="empLoginId"/>
                                                                </td>
                                                                <td><span id="empNoSpan"></span></td>
                                                            </tr>
                                                          
                                                            <tr id="employeeTr" style="display: none">
                                                                  <td   class="fieldLabel">Employee&nbsp;Name:<font style="color:red;">*</font></td>
                                                            <td><span id="empNameSpan"></span></td>
                                                             
                                                            </tr>
                                                                <tr>
                                                                    <td class="fieldLabel" >Type of Exemption :<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td id="overLayexemptionTypeTd"> <s:select id="exemptionTypeId"  name="exemptionTypeId"  list="exemptionTypeMap" headerKey="" headerValue="--Please Select--" cssClass="inputSelect" onchange="getCategoryByExemptionTypeId(this);changeExemptionType(this);"/>
                                                                        <span id="exemptionTypeValue"></span>                 
                                                                    </td>
                                                                     <td class="fieldLabel">Type :<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td> <s:select id="tefType" name="tefType" list="{'Declarations','Actuals'}" headerKey="" headerValue="--Please Select--" cssClass="inputSelect"/>
                                                                </tr> 
                                                               <tr id="licPremiumTR">
																	<td class="fieldLabel" >Policy&nbsp;Number:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield id="policyNumber" name="policyNumber" cssClass="inputTextBlue" value="%{policyNumber}"  onchange="payRollFieldLengthValidator(this);"/>
                                                                        
                                                                    </td>
                                                                <td class="fieldLabel" >Premium&nbsp;Type :<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                   <s:select list="#@java.util.LinkedHashMap@{'1':'Monthly','4':'Quaterly','6':'Half Yearly','12':'Yearly'}" name="licPremium" id="licPremium"  headerValue="select" headerKey="0"  cssClass="inputSelect" /> 
                                                                    </td>
                                                                </tr>
                                                                 <tr Id="paymentDateTr">
                                                                    <td class="fieldLabel" >Payment&nbsp;Date:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="">
                                                                        <s:textfield id="paymentDateTax" name="paymentDateTax" cssClass="inputTextBlue"  onchange="checkDates(this);isValidDate(this)" />
                                                                        <a href="javascript:cal5.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </td>
                                                                     
                                                                     <td class="fieldLabel" >Validity&nbsp;Date:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="">
                                                                        <s:textfield id="validityDate" name="validityDate" cssClass="inputTextBlue" onchange="checkDates(this);isValidDate(this)" />
                                                                        <a href="javascript:cal6.popup();">
                                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                                    </td> 
                                                                </tr>
                                                                <tr id="rentDatesId">
                                                                <td class="fieldLabel" >RentStartDate&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td id="overLayexemptionTypeTd"> <s:select id="overLayrentStartDate"  name="rentStartDate"  list="rentStartDateMap"  onchange="calculateYearlyAmmount();"   value="%{rentStartDate}" cssClass="inputSelect"   disabled="false"/>
                                                                        <span id="exemptionTypeValue"></span>                 
                                                                    </td>
                                                                       <td class="fieldLabel">RentEndDate&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td> <s:select id="overLayrentEndDate" name="rentEndDate" list="rentEndDateMap"  value="%{rentEndDate}" cssClass="inputSelect" onchange="calculateYearlyAmmount();" />
                                                                   
                                                                    
                                                                </tr>
                                                                <tr id="rentAmmount">







                                                                <td class="fieldLabel" >Monthly&nbsp;Amount&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td colspan="">
                                                                        <s:textfield id="overlaymonthlyAmount" name="monthlyAmount" cssClass="inputTextBlue" value="%{monthlyAmount}" onkeyup="isNumberKey(this);" onchange="payRollFieldLengthValidator(this);calculateYearlyAmmount();" />
                                                                    </td>
                                                                    
                                                                </tr>
                                                                <tr>
                                                                    <td class="fieldLabel" > <lable id="savingAmountLable">AppliedAmount&nbsp;:</lable><FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield id="overlaySavingAmount" name="taxAmount" cssClass="inputTextBlue" onkeyup="isNumberKey(this);" onchange="payRollFieldLengthValidator(this);" />
                                                                    </td>
                                                                    <td class="fieldLabel" >ApprovedAmount&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield id="taxApprovedAmount" name="taxApprovedAmount" cssClass="inputTextBlue" onchange="payRollFieldLengthValidator(this);enableHRExemptionFieldPayroll(this);" onkeyup="isNumberKey(this);" />
                                                                    </td>
                                                                </tr>
                                                               
                                                                <tr>
                                                                   
                                                                    <td class="fieldLabel">Status :<FONT color="red"  ><em>*</em></FONT> </td>
                                                                    <td> <s:select id="taxStatus"  name="taxStatus"  list="{'Approved','Rejected','ValidityExpired'}" headerKey="" headerValue="--Please Select--" cssClass="inputSelect"/>
                                                                        
                                                                    </td>
                                                                        
                                                                </tr>
                                                                
 <tr id="panDetailsTr" style=" dispaly: none;">
                                                                    
                                                                  <td class="fieldLabel" id="ownerNamelabel" >Owner&nbsp;Name&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield id="ownerName"  name="ownerName" cssClass="inputTextBlue" onchange="payRollFieldLengthValidator(this);" />
                                                                    </td>
                                                                    <td class="fieldLabel"  id="PANNumberlabel" >PAN&nbsp;Number&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                    <td>
                                                                        <s:textfield id="PANNumber" name="PANNumber" cssClass="inputTextBlue" onchange="payRollFieldLengthValidator(this);"  />
                                                                    </td>  
                                                                    
                                                                </tr>
                                                                <tr id="houseTr" style="display: none;" >
                                                                     <td class="fieldLabel" >House&nbsp;Address&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                                   <td colspan="3" valign="top"><s:textarea  rows="3" cols="65" name="houseAddress" cssClass="inputTextarea1"   id="houseAddress" onchange="payRollFieldLengthValidator(this);"/></td>
                                                           
                                                                     
                                                                    
                                                                    
                                                                </tr>

                                                                <tr>
                                                                    <td class="fieldLabel" valign="top">Employee&nbsp;Comments:</td>
                                                                    <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="taxcomments" cssClass="inputTextarea1" readonly="true" id="employeeComents"/></td>
                                                             </tr>
                                                                <tr>
                                                                    <td class="fieldLabel" valign="top">Approver&nbsp;Comments:</td>
                                                                    <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="taxcomments" cssClass="inputTextarea1" onchange="payRollFieldLengthValidator(this);" id="taxcomments"/></td>
                                                             </tr>
                                                                <tr id="attachmentTr"> 
                                                                    <td class="fieldLabel"><span id="Attachment"></span></td>
                                                                    <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file"  onchange="ValidateSingleInput(this);" /></td> 
                                                                    <td id="uploadTr">
                                                                        <input type="button" value="Save" onclick="return ajaxFileUploadTaxAssumptionFromPayroll();" class="buttonBg" id="addButton"/> 
                                                                         <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                                    </td>
                                                                </tr>
 <tr id="uploadTrForm12BB" style="visibility: hidden;" > 
                                                                    <td class="fieldLabel"><span id="Form12BB"></span></td>
                                                                    <td colspan="2" ><s:file name="file1" label="file" cssClass="inputTextarea" id="file1"  onchange="ValidateSingleInput(this);"/></td> 
                                                                    <!--td> <a href="javascript:getSampleForm12BB();"><b>SampleForm12BB</b></a></td-->
                                                                </tr>
                                                               <tr id="downloadTrForm12BB"><td class="fieldLabel">Attachment(Form12BB):</td>
                                                               <td><span id="downloadSpanForm12BB"></span></td></tr>

                                                               <tr id="downloadAttachmentTr">
                                                               <td class="fieldLabel">Attachment :</td>
                                                               <td><span id="downloadSpan"></span></td>
                                                               <td colspan="2" align="right"> <input type="button"  value="Update" onclick="return updateTaxExemptionFromPayroll();" class="buttonBg" id="updateButton"/></td>
                                                               </tr> 


                                                            </table><br>
                                                        </td>
                                                    </tr>


                                                </table>
                                            </s:form>      
                                              <script>
                                                                    var cal5 = new CalendarTime(document.forms['addTaxForm'].elements['paymentDateTax']);
                                                                    cal5.year_scroll = true;
                                                                    cal5.time_comp = false;
                                                                      var cal6 = new CalendarTime(document.forms['addTaxForm'].elements['validityDate']);
                                                                    cal6.year_scroll = true;
                                                                    cal6.time_comp = false;
                                                                </script>
                                         </div>



                            <!-- Add Tax excemption from Payroll Overlay End-->




                        <%--    <div id="overlay"></div>              <!-- Start Overlay -->
                            <div id="specialBox">


                                <s:form theme="simple"  align="center" name="addReviewForm" action="%{currentAction}" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"   >
                                    <s:hidden id="exemptionId" name="exemptionId" value=""/>
                                    <s:hidden id="flagForOverlay" name="flagForOverlay" value="1"/>
                                    <table align="center" border="0" cellspacing="0" style="width:100%;">
                                        <tr>                               
                                        <td colspan="2" style="background-color: #288AD1" >
                                            <h3 style="color:darkblue;" align="left">
                                                <span id="headerLabel"></span>


                                            </h3></td>
                                        <td colspan="2" style="background-color: #288AD1" align="right">

                                            <a href="#" onmousedown="taxExemptiontoggleOverlay('0')" >
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/closeButton.png" /> 

                                            </a>  

                                        </td></tr>
                                        <tr>
                                        <td colspan="4">
                                            <div id="load" style="color: green;display: none;">Loading..</div>
                                            <div id="resultMessage"></div>
                                            <div id="loadingMessage12"></div>
                                        </td>
                                        </tr>


                                        <tr><td colspan="4">
                                            <table style="width:80%;" align="center">

                                                <tr>
                                                <td class="fieldLabel" >Type of Exemption :<FONT color="red"  ><em>*</em></FONT> </td>
                                                <td id="overLayexemptionTypeTd"width="15%"> <s:select id="overLayexemptionType"  name="exemptionType"  list="exemptionTypeMap" headerKey="" headerValue="--Please Select--"   value="%{exemptionType}" cssClass="inputSelect"  disabled="False"/>
                                                <span id="exemptionTypeValue"></span>                 
                                        </td>

                                        <td class="fieldLabel" >Payment&nbsp;Date:<FONT color="red"  ><em>*</em></FONT></td>
                                        <td colspan="">
                                            <s:textfield id="paymentDateEmp" name="paymentDateEmp" cssClass="inputTextBlue" value="%{paymentDateEmp}" />
                                            <a href="javascript:cal4.popup();">
                                                <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                        </td> 


                                        </tr>      


                                        <td align="left" class="fieldLabel" >Applied Amount&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                        <td>
                                            <s:textfield id="overlaySavingAmount" name="savingAmount" cssClass="inputTextBlue" value="%{savingAmount}" readonly="true"/>


                                        </td>
                                        <td align="left" class="fieldLabel" >Approved Amount&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                        <td>
                                            <s:textfield id="overlayApprovedAmount" name="approvedAmount" cssClass="inputTextBlue" value="%{savingAmount}" onkeyup="isNumberKey(this);"/>


                                        </td>
                                        </tr>
                                        <tr>
                                        <td class="fieldLabel" >Status :<FONT color="red"  ><em>*</em></FONT> </td>
                                        <td colspan="3" > <s:select id="overLaystatus"  name="status"  list="{'Approved','Rejected'}" headerKey=""     cssClass="inputSelect"  disabled="False"/>
                                        <span id="stateLabel"></span>
                                        </td>

                                        </tr>

                                        <tr>
                                        <td class="fieldLabel" valign="top">Comments:</td>
                                        <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="comments" cssClass="inputTextarea1" value="%{comments}" onchange="fieldLengthValidator(this);" id="comments"/></td>


                                        </tr>


                                        <tr style="display: none" id="downloadTr">

                                        <td class="fieldLabel"></td> 
                                        <td colspan="">   

                                        </td> 

                                        <td align="right"> <table><tr><td><div id="update" ><input type="button"  value="Update" onclick="return upadtePayrollTaxExemption();" class="buttonBg"/> </div></td>
                                                <!--                                                                    <td><div id="delete"><input type="button" class="buttonBg" onclick="deleteReview();" value="Delete" /></div></td>--></tr></table></td>
                                        </tr> 

                                        <tr style="display: none" id="overLaystatusTrForApproved">
                                        </tr>
                                        <tr style="display: none" id="overLaystatusTr">
                                        </tr>
                                    </table>
                                    </td>
                                    </tr>


                                    </table>
                                </s:form>              

                                <script type="text/JavaScript">
                                     
                                    var cal4 = new CalendarTime(document.forms['addReviewForm'].elements['paymentDateEmp']);
                                    cal4.year_scroll = true;
                                    cal4.time_comp = false;
                                                                                 
                                </script>

                            </div>  --%>

                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                <tr align="right">
                                <td class="headerText">
                                    <img alt="Home" 
                                         src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/spacer.gif" 
                                         width="100%" 
                                         height="13px" 
                                         border="0"/>
                                    <%if (request.getAttribute(ApplicationConstants.RESULT_MSG) != null) {
                                            out.println(request.getAttribute(ApplicationConstants.RESULT_MSG));
                                        }
                                        if (session.getAttribute("resultMessage") != null) {
                                            out.println(session.getAttribute("resultMessage"));
                                            session.removeAttribute("resultMessage");
                                        }

                                    %>
                                </td>
                                </tr>

                                <tr>
                                <td>
                                    <s:form name="frmTaxAssumptionSearch" action="PayrollTaxAssumptionSearch.action" theme="simple" id="nn">
                                        <s:hidden id="reviewFlag" name="reviewFlag" value="%{reviewFlag}"/>
                                        <s:hidden id="roleName" name="roleName" value="%{#session.roleName}"/>
                                        <s:hidden id="empUserId" name="empUserId" value=""/>
                                        <s:hidden id="empUserIdValue" name="empUserIdValue" value="%{userId}"/>
                                        <table cellpadding="1" cellspacing="1" border="0" width="650px" align="center">
                                            <tr>
                                            <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                            <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlueSmall" value="%{startDate}" onchange="isValidDate(this)"/><a href="javascript:cal2.popup();">
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                            </td>

                                            <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                            <td>  <s:textfield name="endDate" id="endDate" cssClass="inputTextBlueSmall" value="%{endDate}" onchange="isValidDate(this)"/><a href="javascript:cal3.popup();">
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                            </td>
                                            </tr>
                                          <%-- <tr>
                                            <td class="fieldLabel">Validity&nbsp;From&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                            <td><s:textfield name="validityStartDate" id="validityStartDate" cssClass="inputTextBlueSmall" value="%{validityStartDate}"/><a href="javascript:cal7.popup();">
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                            </td>

                                            <td class="fieldLabel">Validity&nbsp;End&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                            <td>  <s:textfield name="validityEndDate" id="validityEndDate" cssClass="inputTextBlueSmall" value="%{validityEndDate}"/><a href="javascript:cal8.popup();">
                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                            </td>
                                            </tr> --%>
                                            <tr>
                                            <td class="fieldLabel" >Type of Exemption :<FONT color="red"  ><em>*</em></FONT> </td>
                                            <td width="15%"> <s:select id="exemptionType"  name="exemptionType"  list="exemptionTypeMap" headerKey="" headerValue="All"   value="%{exemptionType}" cssClass="inputSelect"  disabled="False"/>

                                            </td>
                                            <td class="fieldLabel" >Status :<FONT color="red"  ><em>*</em></FONT> </td>
                                            <td width="15%"> <s:select id="status"  name="status"  list="{'Applied','Approved','Rejected','ValidityExpired'}" headerKey="" headerValue="All"   value="%{status}" cssClass="inputSelect"  disabled="False"/></td>

                                            </tr>
                                            <tr>
                                            <%-- <td class="fieldLabel" width="200px" align="right">Department :</td>
                                            <td><s:select label="Select Department" 
                                                      name="departmentId"
                                                      id="departmentId"
                                                      headerKey=""
                                                      headerValue="All"
                                                      list="departmentIdList" cssClass="inputSelect" value="%{departmentId}" onchange="getNamesByDesignation('1');"/></td> --%>
                                            <td   class="fieldLabel">Employee&nbsp;Name&nbsp;or&nbsp;Id :</td> 
                                            <td><%-- <s:select list="empnamesList" id="userId" name="userId" headerKey="" headerValue="All" cssClass="inputSelect" /> --%>
                                            <s:textfield name="employeeName" id="employeeName1" value="%{employeeName}" placeholder="Name or Id" autocomplete="off" cssClass="inputTextBlue" onkeyup="getEmplyeeNamesForTefSearch();"/><span id="empNoSpan"></span><label id="rejectedId"></label><div class="fieldLabelLeft" id="validationMessage1"></div>

                                                                                                <div style="display: none; position: absolute; overflow:auto; z-index: 500;" id="menu-popup1">
                                                                                                    <table id="completeTable1" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" ></table>
                                                                                                </div>
                                            <s:hidden name="userId" id="userId" value="%{userId}"/>
                                            
                                            </td> 
                                        
                                       
                                            <td class="fieldLabel">Emp No:</td>
                                            <td>

                                                <s:textfield name="empNo" id="empNo" maxlength="5" cssClass="inputTextBlue" value="%{empNo}"/>
                                            </td>
                                            </tr>
                                            <tr>
                                                <td class="fieldLabel" >Organization:</td>
                                                                
                                                                <td colspan="">
                                                                    <s:select  id="orgId" name="orgId" cssClass="inputSelect" list="orgIdMap" style="width:150%" headerKey=""
                                                        headerValue="All" class="inputSelectSmall"  value="%{orgId}" />
                                                                </td>
                                                                <td class="fieldLabel">Type :</td>
                                                                    <td> <s:select id="tefType" name="tefType" value="%{tefType}" list="{'Declarations','Actuals'}" headerKey="" headerValue="All" cssClass="inputSelect"/>
                                                               
                                            </tr>

                                        <%--    <tr>
                                            <td class="fieldLabel">Is TeamLead:</td>
                                            <td colspan="2">
                                                <s:checkbox name="isTeamLead" id="isTeamLead"
                                                            value="%{isTeamLead}" 
                                                            theme="simple" onchange="getNamesByDesignation('1');"/> 
                                            </td>  
                                            <td class="fieldLabel">Is Manager:</td>
                                            <td colspan="2">
                                                <s:checkbox name="isManager" id="isManager"
                                                            value="%{isManager}" 
                                                            theme="simple" onchange="getNamesByDesignation('1');"/> 
                                            </td> 
                                            </tr> --%>





                                            <tr>
                                            <td colspan="4" align="right">

                                                <s:submit cssClass="buttonBg"  align="right"  value="Search" />

                                            </td>
                                            </tr>
                                        </table>
                                    </s:form>

                                    <script type="text/javaScript">
                                        var cal2 = new CalendarTime(document.forms['frmTaxAssumptionSearch'].elements['startDate']);
                                        cal2.year_scroll = true;
                                        cal2.time_comp = false;
                                        var cal3 = new CalendarTime(document.forms['frmTaxAssumptionSearch'].elements['endDate']);
                                        cal3.year_scroll = true;
                                        cal3.time_comp = false;
//                                        var cal7 = new CalendarTime(document.forms['frmTaxAssumptionSearch'].elements['validityStartDate']);
//                                        cal7.year_scroll = true;
//                                        cal7.time_comp = false;
//                                        var cal8 = new CalendarTime(document.forms['frmTaxAssumptionSearch'].elements['validityEndDate']);
//                                        cal8.year_scroll = true;
//                                        cal8.time_comp = false;
                                                                                 
                                    </script>

                                </td>

                                </tr>
                                <tr>
                                <td>
                                    <%

                                        if (request.getAttribute("submitFrom") != null) {
                                            submittedFrom = request.getAttribute("submitFrom").toString();
                                        }

                                        if (!"dbGrid".equalsIgnoreCase(submittedFrom)) {
                                            searchSubmitValue = submittedFrom;
                                        }

                                    %>

                                </td>
                                </tr>
                                <tr>
                                <td>

                                </td>
                                </tr>
                                <tr>
                                <td>
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
                                            strSortCol = "Fname";
                                        }

                                        strSortOrd = request.getParameter("txtSortAsc");
                                        if (strSortOrd == null) {
                                            strSortOrd = "ASC";
                                        }


                                        try {

                                            /* Getting DataSource using Service Locator */
                                            connection = ConnectionProvider.getInstance().getConnection();

                                            String empReviewAction = "../../employee/Reviews/addMyReview.action";

                                            /* Sql query for retrieving resultset from DataBase */
                                            /*queryString  =null;*/
                                            //queryString = "SELECT ReviewType FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id)";
                                            String empId = session.getAttribute(ApplicationConstants.SESSION_EMP_ID).toString();
                                            if (request.getAttribute(ApplicationConstants.EMP_REVIEWS_LIST) != null) {
                                                queryString = request.getAttribute(ApplicationConstants.EMP_REVIEWS_LIST).toString();
                                            }
                                          //  out.println(queryString);



                                    %>

                                    <s:form action="" theme="simple" name="frmDBGrid" id="PayrollTaxAssumptionSearch">   

                                        <table cellpadding="0" cellspacing="0" width="100%" border="0">


                                            <tr>
                                            <td width="100%">





                                                <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="10"
                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                   addImage="../../includes/images/DBGrid/Add.png"  addAction="javascript:taxExemptiontoggleOverlayFromPayroll();"
                                                                   /> 
                                                    <%--    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                     
                                                                       />          --%>  

                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                    imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                    imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   

                                                    <%--  <grd:anchorcolumn dataField="EmpName" 
                                                                        headerText="EmployeeName" 
                                                                        linkUrl="javascript:getTefEmpDetails({Id})" linkText="{EmpName}" width="20"/> --%>
                                                     <grd:datecolumn dataField="EmpNo" 
                                                                    headerText="EmpId" 
                                                                    width="25"/>
                                                    <grd:anchorcolumn dataField="EmpName" 
                                                                      headerText="EmployeeName" 
                                                                      linkUrl="javascript:getTaxAssumptionFromPayroll({Id})" linkText="{EmpName}" width="20"/>
                                                    <grd:textcolumn dataField="ExemptionType" 
                                                                    headerText="Exemption" 
                                                                    width="25"/>
                                                    <grd:datecolumn dataField="SavingsType" 
                                                                                        headerText="Type" 
                                                                                        width="20"/>
                                                    <grd:datecolumn dataField="AppliedDate" 
                                                                    headerText="AppliedDate"  dataFormat="yyyy-MM-dd"  sortable="true"
                                                                    width="20"/>
                                                    <grd:datecolumn dataField="SavingsAmount" 
                                                                    headerText="Savings Amount" 
                                                                    width="20"/>

                                                    <grd:datecolumn dataField="ApprovedAmount" headerText="ApprovedAmount" width="20"/>


                                                    <grd:textcolumn dataField="Status" headerText="Status" width="18"/> 

                                                    <%--  <grd:textcolumn dataField="AttachmentName"  headerText="AttachmentName"   width="15" /> --%>


                                                    <grd:textcolumn dataField="WorkPhoneNo"  
                                                                    headerText="WorkPhoneNo" 
                                                                    width="25"/>
                                                    <grd:textcolumn dataField="ApprovedBy" headerText="ApprovedBy" width="20"/>

                                                    <%--<grd:imagecolumn headerText="Delete" width="4" HAlign="center" 
                                                                     imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                     linkUrl="deleteReview.action?reviewId={Id}" imageBorder="0"
                                                                     imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn> 
                                                    --%>

                                                    <grd:anchorcolumn dataField="ApproverComments" 
                                                                      headerText="Comments" 
                                                                      linkUrl="javascript:showApproverCommentsPopUp('{ApproverComments}')" linkText="Click TO View" width="25"/>

                                                   <%--<grd:imagecolumn headerText="Download" width="4" HAlign="center" 

                                                                     imageSrc="../../includes/images/download_11x10.jpg"

                                                                     linkUrl="javascript:taxExemptiondownloadFileFortheRolePayRoll({id})" imageBorder="0"

                                                                     imageWidth="11" imageHeight="10" alterText="Download"></grd:imagecolumn>

<grd:anchorcolumn dataField="del" 

                                                                                          headerText="Delete" 

                                                                                          linkUrl="javascript:deleteTefEmpDetails({Id})" linkText='{del}' width="25"/> --%>





 <grd:imagecolumn headerText="Delete" width="4" HAlign="center" 

                                                                                                             imageSrc="../../includes/images/DBGrid/Delete.png"

                                                                                                             linkUrl="javascript:deleteTefEmpDetails({Id})" imageBorder="0"

                                                                                                             imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn>
 </grd:dbgrid>




                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                <input type="hidden" name="submitFrom" value="dbGrid">
                                                <s:hidden name="startDate"  value="%{startDate}"/>
                                                <s:hidden name="endDate"  value="%{endDate}"/>
                                                <s:hidden   name="exemptionType"   value="%{exemptionType}"/>
                                                <s:hidden   name="status"  value="%{status}"   disabled="False"/>
                                                <s:hidden name="departmentId" value="%{departmentId}" />

                                                <s:hidden name="userId" value="%{userId}"/>
                                                <s:hidden  name="isTeamLead" value="%{isTeamLead}"/>
                                                <s:hidden  name="isManager" value="%{isManager}"/>
                                                <s:hidden  name="employeeName" value="%{employeeName}"/>
                                                <s:hidden  name="validityStartDate" value="%{validityStartDate}"/>
                                                <s:hidden  name="validityEndDate" value="%{validityEndDate}"/>
                                                <s:hidden  name="orgId" value="%{orgId}"/>
                                                <s:hidden  name="tefType" value="%{tefType}"/>
                                                <s:hidden  name="empNo" value="%{empNo}"/>
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
                                </td>
                                </tr>
                            </table>

                            <!-- End Overlay -->
                            <!-- Start Special Centered Box -->

                            <%-- </sx:div > --%>
                        </div>
                        <!--//END TAB : -->
                        <%-- </sx:tabbedpanel> --%>
                    </div>
                    <script type="text/javascript">

                        var countries=new ddtabcontent("accountTabs")
                        countries.setpersist(false)
                        countries.setselectedClassTarget("link") //"link" or "linkparent"
                        countries.init()

                    </script>
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
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
<script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
<script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  
<script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/FileUpload.js"/>"></script>

<script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/nameSuggestion.js?vesrion=1"/>"></script>
 <script type="text/JavaScript" src="<s:url value="/includes/javascripts/payroll/x0popup.min.js"/>"></script>
</table>
<!--//END MAIN TABLE : Table for template Structure-->

<script type="text/javascript">
		$(window).load(function(){
			init();
			initForTefSearch();
			changeExemptionType(document.getElementById('exemptionTypeId'));

		});
		</script>

</body>
</html>
