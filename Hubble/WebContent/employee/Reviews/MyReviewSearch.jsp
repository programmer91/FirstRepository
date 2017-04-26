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
        <title>Hubble Organization Portal :: Employee Search</title>
    
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/mainStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/GridStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/leftMenu.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/tabedPanel.css"/>">
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/DBGrid.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/ClientValidations.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/clientValidations/EmpSearchClientValidation.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/AppConstants.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/tabedPanel.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/CalendarTime.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/jquery.js"/>"></script>   
    <script type="text/javascript" src="<s:url value="/includes/javascripts/reviews/ajaxfileupload.js"/>"></script>  
    <script type="text/JavaScript" src="<s:url value="/includes/javascripts/reviews/FileUpload.js"/>"></script>


    <s:include value="/includes/template/headerScript.html"/>
</head>
<!-- <body class="bodyGeneral" onload="getRequirementClosed()"> -->
<body class="bodyGeneral">

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
                                <li ><a href="#" class="selected" rel="employeeSearchTab"  >Review Search </a></li>
                            </ul>
                            <%--<sx:tabbedpanel id="employeeSearchPannel" cssStyle="width: 840px; height: 500px;padding: 5px 5px 5px 5px;" doLayout="true"> --%>
                            <div  style="border:1px solid gray; width:840px;height: 500px; overflow:auto; margin-bottom: 1em;">   
                                <!--//START TAB : -->
                                <%--  <sx:div id="employeeSearchTab" label="Employee Search" cssStyle="overflow:auto;"  > --%>
                                <div id="employeeSearchTab" class="tabcontent"  >


                                    <div id="overlay"></div>              <!-- Start Overlay -->
                                    <div id="specialBox">


                                        <s:form theme="simple"  align="center" name="addReviewForm" action="%{currentAction}" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"   >
                                            <s:hidden id="reviewId" name="reviewId" value=""/>
                                            <s:hidden id="reviewFlag" name="reviewFlag" value=""/>
                                             <s:hidden id="addType" name="addType" value="My"/>
                                            <table align="center" border="0" cellspacing="0" style="width:100%;">
                                                <tr>                               
                                                    <td colspan="2" style="background-color: #288AD1" >
                                                        <h3 style="color:darkblue;" align="left">
                                                            <span id="headerLabel"></span>


                                                        </h3></td>
                                                    <td colspan="2" style="background-color: #288AD1" align="right">

                                                        <a href="#" onmousedown="mytoggleOverlay()" >
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
                                                    <table style="width:80%;" align="center" border="0">
                                                <tr>


                                                    <td class="fieldLabel" >Review&nbsp;Type&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                    <td > 
                                                        <s:select id="overlayReviewType" name="overlayReviewType"  list="reviewList" headerKey="" headerValue="--Please Select--" value="%{currentReview.reviewType}" cssClass="inputSelect" onchange="getBasePoints(this);getRequirementClosed()" disabled="False" />
                                                    <span id="reviewTypeValue"></span>
                                                    </td>
                                                    <td class="fieldLabel" >Status&nbsp;:</td>
                                                    <td > 
                                                        
                                                        <s:select id="reviewStatusOverlay"  name="reviewStatusOverlay"  list="{'Opened'}" cssClass="inputSelect"  disabled="False"/>
                                                        <span id="stateLabel"></span>
                                                    </td>
                                                   
                                                </tr>
                                                <tr id="RequirementClosedTR">
                                                    <td class="fieldLabel">No.of Log's added</td>
                                                    <td><s:textfield id="overlayReviewLogAdded" name="overlayReviewLogAdded" cssClass="inputTextBlue" value="%{overlayReviewLogAdded}" onkeypress="return isNumberKeyExam(event)"/></td>
                                                    <td class="fieldLabel">Billing&nbsp;Amount&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td><s:textfield id="overlayReviewBilling" name="overlayReviewBilling" cssClass="inputTextBlue" value="%{overlayReviewBilling}" onkeypress="return isNumberKeyExam(event)"/><font color="darkblue">$</font></td>
                                                </tr>
                                                <tr>
                                                   <%-- <td align="right" class="fieldLabel" >Created&nbsp;Date:</td>
                                                    <td><s:textfield id="overlayReviewCreatedDate" name="overlayReviewCreatedDate" cssClass="inputTextBlue" value="%{endDate}" />
                                                        <a href="javascript:cal1.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                 width="20" height="20" border="0" ></a>
                                                    </td> --%>
                                                    <td align="left" class="fieldLabel" >Review&nbsp;Date&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3">
                                                        <s:textfield id="overlayReviewDate" name="overlayReviewDate" cssClass="inputTextBlue" value="%{overlayReviewDate}" />
                                                      
                                                        <a class="underscore" href="javascript:cal4.popup();">
                                                            <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" 
                                                                 width="20" height="20" border="0" ></a>
                                                    </td>
                                                   
                                                </tr>
                                                
                                                 <tr>
                                                     <td class="fieldLabel">Review&nbsp;Title&nbsp;:<font style="color:red;">*</font></td>
                                                    <td colspan="3"><s:textfield name="overlayReviewName" id="overlayReviewName" cssClass="inputTextareaOverlay" value="%{currentReview.reviewName}" onchange="reviewFieldLengthValidator(this);"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="fieldLabel" valign="top" style="vertical-align : middle;">Review&nbsp;Description&nbsp;:<FONT color="red"  ><em>*</em></FONT></td>
                                                    <td colspan="3" valign="top"><s:textarea rows="3" cols="77" name="overlayDescription" cssClass="inputTextareaOverlay" value="%{currentReview.notes}" onchange="reviewFieldLengthValidator(this);" id="overlayDescription"/></td>


                                                </tr>
                                                <tr style="display:none">
                                                     <td align="left" class="fieldLabel" >Lead&nbsp;Rating&nbsp;:</td>
                                                    <td ><s:textfield name="leadRating" id="leadRating" cssClass="inputTextBlue2" readonly="true"/>
                                                        <s:hidden id="maxPoints" name="maxPoints"/>
                                                        
                                                        <span id="leadpointsInfo"></span></td>
                                                    <td align="left" class="fieldLabel" >Hr&nbsp;Rating&nbsp;:</td>
                                                    <td ><s:textfield name="hrRating" id="hrRating" cssClass="inputTextBlue2" readonly="true"/>
                                                        
                                                        
                                                        <span id="hrpointsInfo"></span></td>
                                                    
                                                </tr>
                                                <tr style="display:none">
                                                     <td align="left" class="fieldLabel" >OnsiteMgr&nbsp;Rating&nbsp;:</td>
                                                    <td ><s:textfield name="onsiteMgrRating" id="onsiteMgrRating" cssClass="inputTextBlue2" readonly="true"/>
                                                        
                                                        
                                                        <span id="onsiteMgrpointsInfo"></span></td>
                                                </tr>
                                                
                                                 <tr id="tlApprovedDetails" style="display: none">
                                                    <td class="fieldLabel">
                                                      Approved&nbsp;By#1&nbsp;:
                                                    </td>
                                                    <td style="color:green;">
                                                        <div id="tlId" ></div>
                                                    </td>
                                                    <td class="fieldLabel">
                                                      Approved&nbsp;Date&nbsp;:
                                                    </td>
                                                    <td style="color:green;">
                                                        <div id="ApprovedBy1Date" ></div>
                                                    </td>
                                              </tr>
                                                 <tr id="tlcommentsTr" style="display:none">
                                         <td class="fieldLabel" valign="top">Lead Comments&nbsp;:</td>
                                         <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="tlComments" cssClass="inputTextareaOverlay1" value="%{currentReview.notes}" onchange="reviewFieldLengthValidator(this);" id="tlComments" readonly="true"/></td>
                                                 </tr>
                                                   <tr id="hrApprovedDetails" style="display: none">
                                                     <td class="fieldLabel">
                                                      Approved&nbsp;By#2&nbsp;:
                                                    </td>
                                                    <td style="color:green;">
                                                    <div id="hrId"></div>
                                                    </td>
                                                    <td class="fieldLabel">
                                                      Approved&nbsp;Date&nbsp;:
                                                    </td>
                                                    <td style="color:green;">
                                                        <div id="ApprovedBy2Date" ></div>
                                                    </td>
                                                  </tr>  
                                                  <tr id="hrcommentsTr" style="display:none">
                                         <td class="fieldLabel" valign="top">Hr Comments&nbsp;:</td>
                                                    <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="hrComments" cssClass="inputTextareaOverlay1" value="%{currentReview.notes}" onchange="reviewFieldLengthValidator(this);" id="hrComments" readonly="true"/></td>
                                                 </tr>
                                                 <!-- onsite mgr comments start -->
                                                 <tr id="onsiteMgrApprovedDetails" style="display: none">
                                                     <td class="fieldLabel">
                                                      Approved&nbsp;By#2&nbsp;:
                                                    </td>
                                                    <td style="color:green;">
                                                    <div id="onsiteMgrId"></div>
                                                    </td>
                                                    <td class="fieldLabel">
                                                      Approved&nbsp;Date&nbsp;:
                                                    </td>
                                                    <td style="color:green;">
                                                        <div id="ApprovedBy3Date" ></div>
                                                    </td>
                                                  </tr>  
                                                  <tr id="onsiteMgrcommentsTr" style="display:none">
                                         <td class="fieldLabel" valign="top">Onsite&nbsp;Manager Comments&nbsp;:</td>
                                                    <td colspan="3" valign="top"><s:textarea rows="3" cols="65" name="onsiteMgrComments" cssClass="inputTextareaOverlay1" onchange="reviewFieldLengthValidator(this);" id="onsiteMgrComments" readonly="true"/></td>
                                                 </tr>
                                                 <!-- onsite mgr comments end -->


                                                <tr id="uploadTr" style="display: none"> 

                                                    <td class="fieldLabel">Upload File&nbsp;:</td>
                                                    <td colspan="2" ><s:file name="file" label="file" cssClass="inputTextarea" id="file" onchange="putFileName();"/></td> 
                                                    <td  align="center" >
                                                        <input type="button" value="Save" onclick="return ajaxFileUpload();" class="buttonBg" id="addButton" style="margin-left: 28px;"/> 


                                                        <%-- <s:submit cssClass="buttonBg"  align="right" id="save" value="Save" /> --%>
                                                        <s:reset cssClass="buttonBg"  align="right" value="Reset" />

                                                    </td>
                                                </tr>
                                               
                                            
                                                    <%--  <tr id="approvedByComments1">
                                                        <td class="fieldLabel">
                                                      First&nbsp;Approved&nbsp;By&nbsp;Comments:
                                                    </td>
                                                      <td colspan="3" valign="top"><s:textarea rows="3" cols="77" name="ApprovedByComments1" cssClass="inputTextareaOverlay" value="%{currentReview.ApprovedByComments1}" onchange="fieldLengthValidator(this);" id="ApprovedByComments1"/></td>
                                                   <!-- <td style="color:green;">
                                                    <div id="ApprovedByComments1"></div>
                                                    </td> -->
                                                    </tr>
                                                     <tr id="approvedByComments2">
                                                     <td class="fieldLabel">
                                                      Second&nbsp;Approved&nbsp;By&nbsp;Comments:
                                                    </td>
                                                     <td colspan="3" valign="top"><s:textarea rows="3" cols="77" name="ApprovedByComments2" cssClass="inputTextareaOverlay" value="%{currentReview.ApprovedByComments2}" onchange="fieldLengthValidator(this);" id="ApprovedByComments2"/></td>
                                                   <!-- <td style="color:green;">
                                                    <div id="ApprovedByComments2"></div>
                                                    </td> -->
                                                </tr> --%>

                                                <tr style="display: none" id="downloadTr">

                                                    <td class="fieldLabel">Attachments&nbsp;:</td> 
                                                    <td colspan="">   <a class="navigationText" href="#" onclick="downloadReviewFile();" id="downloadLink" style="display: none">

                                                            <span id="downloadFile"></span>
                                                            
                                                        </a>
                                                    <span id="downloadFileNoAttachment"></span>
                                                    </td> 

                                                    <td align="right" colspan="3"> <table><tr><td><div id="update" ><input type="button"  value="Update" onclick="return upadteMyreview();" class="buttonBg"  style="margin-left: 28px;"/> </div></td>
                                                                    <td><div id="delete"><input type="button" class="buttonBg" onclick="deleteReview();" value="Delete" style="margin-right: 21px;"/></div></td></tr></table></td>
                                                </tr> 


                                                        </table>
                                                    </td>
                                                </tr>


                                            </table>
                                        </s:form>              

                                        <script type="text/JavaScript">
                                         /*   var cal1 = new CalendarTime(document.forms['addReviewForm'].elements['overlayReviewCreatedDate']);
                                            cal1.year_scroll = true;
                                            cal1.time_comp = false;*/
                                               var cal4 = new CalendarTime(document.forms['addReviewForm'].elements['overlayReviewDate']);
                                            cal4.year_scroll = true;
                                            cal4.time_comp = false;
                                                                                 
                                        </script>

                                    </div>





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
                                   }%>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>

                                                <s:form name="frmEmpSearch" action="reviewSearch.action" theme="simple">
                                                    <s:hidden id="reviewFlag" name="reviewFlag" value="%{reviewFlag}"/>
                                                    <s:hidden id="roleName" name="roleName" value="%{#session.roleName}"/>
                                                    <table cellpadding="1" cellspacing="1" border="0" width="650px" align="center">
                                                        <tr>
                                                            <td class="fieldLabel">Start&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                            <td><s:textfield name="startDate" id="startDate" cssClass="inputTextBlueSmall" value="%{startDate}"/><a class="underscore" href="javascript:cal2.popup();" style="text-decoration: none;">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                            </td>

                                                            <td class="fieldLabel">End&nbsp;Date&nbsp;(mm/dd/yyyy)&nbsp;:</td>
                                                            <td>  <s:textfield name="endDate" id="endDate" cssClass="inputTextBlueSmall" value="%{endDate}"/><a class="underscore" href="javascript:cal3.popup();" style="text-decoration: none;">
                                                                    <img src="/<%=ApplicationConstants.CONTEXT_PATH%>/includes/images/showCalendar.gif" width="20" height="20" border="0"></a>
                                                            </td>
                                                        </tr>
                                                            <script type="text/javaScript">
                                            var cal2 = new CalendarTime(document.forms['frmEmpSearch'].elements['startDate']);
                                            cal2.year_scroll = true;
                                            cal2.time_comp = false;
                                              var cal3 = new CalendarTime(document.forms['frmEmpSearch'].elements['endDate']);
                                            cal3.year_scroll = true;
                                            cal3.time_comp = false;
                                                                                 
                                        </script>
                                                        <tr>
                                                            <td class="fieldLabel" >Review Type&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                           <%--  <td width="15%"> <s:select id="reviewType"  name="reviewType"  list="searchReviewMap" headerKey="" headerValue="All"   value="%{reviewType}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/></td> --%>
                                                             <td width="15%"> <s:select id="reviewType"  name="reviewType"  list="reviewList" headerKey="" headerValue="All"   value="%{reviewType}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/></td>
                                                          <%--  <td class="fieldLabel" >State :<FONT color="red"  ><em>*</em></FONT> </td>
                                                            <td width="15%"> <s:select id="reviewStatus"  name="reviewStatus"  list="{'Opened','Approved','Denied'}" headerKey="" headerValue="All"   value="%{reviewStatus}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/></td> --%>
                                                       
                                                        </tr>
                                                        <td class="fieldLabel" >TL Status&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                            <td width="15%"> <s:select id="reviewTlStatus"  name="reviewTlStatus"  list="{'Opened','Approved','Denied'}" headerKey="" headerValue="All"   value="%{reviewTlStatus}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/></td>
                                                            <td class="fieldLabel" >HR Status&nbsp;:<FONT color="red"  ><em>*</em></FONT> </td>
                                                            <td width="15%"> <s:select id="reviewHrStatus"  name="reviewHrStatus"  list="{'Opened','Approved','Denied'}" headerKey="" headerValue="All"   value="%{reviewHrStatus}" cssClass="inputSelect" onchange="getAssignedReviews(this);" disabled="False"/></td>
                                                  <%--      <%if (session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Hr") || request.getAttribute("reviewFlag").toString().equals("1")) {%>

                                                        <tr>
                                                            <td   class="fieldLabel">Select EmpName :</td>
                                                            <td ><s:select list="empnamesList" id="empnameById" name="empnameById" headerKey="-1" headerValue="All" cssClass="inputSelectLarge" value="%{empnameById}"/></td> 
                                                            <td></td>
                                                        </tr>
                                                        <%}%>   --%>
                                                        <tr>
                                                            <td colspan="4" align="right">
                                                                <input type="button" class="buttonBg"  align="right"  value="Add" onclick="mytoggleOverlay();" />                     
                                                                <s:submit cssClass="buttonBg"  align="right"  value="Search" />

                                                            </td>
                                                        </tr>
                                                    </table>
                                                </s:form>
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



                                                        /* else {
                                                        if(session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Employee")){
                                                        queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewType,EmpId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.EmpId = tblEmployee.Id) WHERE tblEmpReview.EmpId="+empId;
                                                        }else if(session.getAttribute(ApplicationConstants.SESSION_ROLE_NAME).toString().equalsIgnoreCase("Hr")) {
                                                        queryString="SELECT  CONCAT(tblEmployee.FName,' ',tblEmployee.MName,'.',tblEmployee.LName) AS EmployeeName,ReviewType,EmpId,ReviewTypeId,EmpComments,tblEmpReview.CreatdBy,tblEmpReview.CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id AS Id,HrComments FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) JOIN tblEmployee ON (tblEmpReview.EmpId = tblEmployee.Id) WHERE WorkingCountry='"+session.getAttribute(ApplicationConstants.WORKING_COUNTRY).toString()+"'";                                  
                            
                                                        }
                            
                                                        }*/
                                                        // queryString="SELECT ReviewType,EmpId,ReviewTypeId,EmpComments,CreatdBy,CreatedDate,AttachmentName,AttachmentLocation,ReviewName,tblEmpReview.Id as Id FROM tblEmpReview JOIN tblLkReviews ON (ReviewTypeId = tblLkReviews.Id) where EmpId="+empId;
                                                      //out.println(queryString);

                                                        //out.println("--------"+submittedFrom);
%>

                                                <s:form action="" theme="simple" name="frmDBGrid">   

                                                    <table cellpadding="0" cellspacing="0" width="100%" border="0">


                                                        <tr>
                                                            <td width="100%">

                                                               
                                                                <grd:dbgrid id="tblStat" name="tblStat" width="98" pageSize="12"
                                                                currentPage="<%=intCurr%>" border="0" cellSpacing="1" cellPadding="2"
                                                                dataMember="<%=queryString%>" dataSource="<%=connection%>" cssClass="gridTable">

                                                                    <%--    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                       imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                       addImage="../../includes/images/DBGrid/Add.png"  addAction="<%=empReviewAction %>"
                                                                                       /> --%>
                                                                    <grd:gridpager imgFirst="../../includes/images/DBGrid/First.gif" imgPrevious="../../includes/images/DBGrid/Previous.gif" 
                                                                                   imgNext="../../includes/images/DBGrid/Next.gif" imgLast="../../includes/images/DBGrid/Last.gif"
                                                                                   addImage="../../includes/images/DBGrid/Add.png"  addAction="javascript:mytoggleOverlay()"
                                                                                   />            

                                                                    <grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>" 
                                                                                    imageAscending="../../includes/images/DBGrid/ImgAsc.gif" 
                                                                                    imageDescending="../../includes/images/DBGrid/ImgDesc.gif"/>   
                                                                   
                                                                    <grd:anchorcolumn dataField="ReviewName" 
                                                                                      headerText="Review Title" 
                                                                                      linkUrl="javascript:toggleEditOverlay({Id})" linkText="{ReviewName}" width="20"/>
                                                                    <grd:textcolumn dataField="ReviewType" headerText="ReviewType" width="18"/>

                                                                  
                                                                    <grd:textcolumn dataField="Status" headerText="TL Status" width="18"/> 
                                                                     <grd:textcolumn dataField="HrStatus" headerText="HR Status" width="18"/> 
                                                                    <grd:datecolumn dataField="ReviewDate"  headerText="ReviewDate"  HAlign="right" dataFormat="MM-dd-yyyy" width="15" /> 


                                                                   
                                                                    <%--<grd:imagecolumn headerText="Delete" width="4" HAlign="center" 
                                                                                     imageSrc="../../includes/images/DBGrid/Delete.png"
                                                                                     linkUrl="deleteReview.action?reviewId={Id}" imageBorder="0"
                                                                                     imageWidth="51" imageHeight="20" alterText="Delete"></grd:imagecolumn> 
--%>


                                                                </grd:dbgrid>
                                                               



                                                                <input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">
                                                                <input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">
                                                                <input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">

                                                                <input type="hidden" name="submitFrom" value="dbGrid">
                                                               <s:hidden  name="startDate" value="%{startDate}"/>
                                                                <s:hidden  name="endDate" value="%{endDate}"/>
                                                                <s:hidden  name="reviewType" value="%{reviewType}"/>
                                                              <%--  <s:hidden  name="reviewStatus" value="%{reviewStatus}"/> --%>
                                                                <s:hidden  name="empnameById" value="%{empnameById}"/>
                                                                <s:hidden  name="reviewTlStatus" value="%{reviewTlStatus}"/>
                                                                <s:hidden  name="reviewHrStatus" value="%{reviewHrStatus}"/>
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

    </table>
    <!--//END MAIN TABLE : Table for template Structure-->

<script type="text/javascript">
		$(window).load(function(){
           getRequirementClosed();
		});
		</script>

</body>
</html>

